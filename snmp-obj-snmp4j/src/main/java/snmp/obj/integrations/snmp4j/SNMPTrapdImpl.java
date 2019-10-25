package snmp.obj.integrations.snmp4j;

import java.io.IOException;
import java.io.Serializable;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.snmp4j.CommandResponder;
import org.snmp4j.CommandResponderEvent;
import org.snmp4j.MessageDispatcherImpl;
import org.snmp4j.PDU;
import org.snmp4j.PDUv1;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.asn1.BER;
import org.snmp4j.log.LogAdapter;
import org.snmp4j.log.LogFactory;
import org.snmp4j.mp.MPv1;
import org.snmp4j.mp.MPv2c;
import org.snmp4j.mp.MPv3;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.security.SecurityModels;
import org.snmp4j.security.SecurityProtocols;
import org.snmp4j.security.USM;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.Null;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.TcpAddress;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.smi.Variable;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultTcpTransportMapping;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.snmp4j.util.MultiThreadedMessageDispatcher;
import org.snmp4j.util.ThreadPool;

import snmp.obj.config.Configuration;
import snmp.obj.config.Property;
import snmp.obj.config.PropertyAccessor;
import snmp.obj.integrations.snmp4j.converter.RegisteredConverters;
import snmp.obj.integrations.snmp4j.util.log.Slf4jLoggerFactory;
import snmp.obj.mib.Syntax;
import snmp.obj.mib.annotations.MIBNotification;
import snmp.obj.mib.annotations.MIBNotificationObject;
import snmp.obj.mib.annotations.MIBTableEntry;
import snmp.obj.mib.annotations.MIBTableIndex;
import snmp.obj.mib.annotations.MIBVariable;
import snmp.obj.notification.DefaultSNMPTrapd;
import snmp.obj.notification.Notification;
import snmp.obj.notification.Trap;
import snmp.obj.util.converter.SNMPTypeConverter;

public class SNMPTrapdImpl extends DefaultSNMPTrapd {

	static {
		LogFactory.setLogFactory(new Slf4jLoggerFactory());
		BER.setCheckSequenceLength(false);
	}

	private final static LogAdapter logger = LogFactory.getLogger(SNMPTrapdImpl.class);

	private transient Snmp snmp;
	
	private final Configuration configuration;
	
	public SNMPTrapdImpl(final Configuration configuration) {
		this.configuration = configuration;
	}

	
	private void initialize() {
		if(snmp == null) {
			try {
				snmp = new Snmp(new DefaultUdpTransportMapping());//new UdpAddress(162), true));
				//snmp.listen();
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	@Override
	public void start() {
		initialize();

		snmp.addNotificationListener(GenericAddress.parse("udp:0.0.0.0/162"), new CommandResponder() {
			
			@Override
			public <A extends Address> void processPdu(CommandResponderEvent<A> event) {
				PDU pdu = event.getPDU();
				SNMPTrapdImpl.logger.info(pdu.toString());
				Notification notification = null;
				try {
					switch(pdu.getType()) {
						case PDU.TRAP:
							notification = createNotification(pdu);
							break;
						case PDU.V1TRAP:
							notification = createTrap((PDUv1)pdu);
							break;
						default:
							break;
					}
				} 
				catch (Exception e) {
					SNMPTrapdImpl.logger.error("Failed to create notification/trap object for " + pdu.toString(), e);
				}

				if(notification != null) {
					notifyAllListeners(notification);
				}
			}
		});
	}

	@Override
	public void stop() {
		if(snmp != null) {
			try {
				snmp.close();
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private OID getSnmpTrapOID(final PDU pdu) {
		VariableBinding snmpTrapOidVarBind = pdu.get(1);
		return (OID)snmpTrapOidVarBind.getVariable();
	}

	private OID getSnmpTrapOID(final PDUv1 pdu) {
		int genericTrap = pdu.getGenericTrap();
		if(genericTrap == PDUv1.ENTERPRISE_SPECIFIC) {
			return new OID(pdu.getEnterprise().toIntArray(), new int[] { 0, pdu.getSpecificTrap() });
		}
		else {
			return new OID(SnmpConstants.snmpTraps.toIntArray(), new int[] { genericTrap + 1 });
		}
	}

	private Notification createNotification(final PDU pdu) throws Exception {
		Notification notification = null;
		
		OID snmpTrapOid = getSnmpTrapOID(pdu);
		Class<?> clazz = configuration.getNotificationType(snmpTrapOid.toString());
		if(clazz != null) {
			notification = (Notification) clazz.newInstance();
			
			setProperties(notification, clazz, pdu.getVariableBindings());
		}
		
		return notification;
	}
	
	private Trap createTrap(final PDUv1 pdu) throws Exception {
		Trap trap = null;
		OID snmpTrapOid = getSnmpTrapOID(pdu);
		Class<?> clazz = configuration.getNotificationType(snmpTrapOid.toString());
		trap = (Trap) clazz.newInstance();
		if(clazz != null) {
			trap = (Trap) clazz.newInstance();
			trap.setAgentAddress(pdu.getAgentAddress().toString());
			trap.setEnterprise(pdu.getEnterprise().toString());
			trap.setGenericTrap(pdu.getGenericTrap());
			if(pdu.getGenericTrap() == PDUv1.ENTERPRISE_SPECIFIC) {
				trap.setSpecificTrap(pdu.getSpecificTrap());
			}
			trap.setTimestamp(pdu.getTimestamp());
			setProperties(trap, clazz, pdu.getVariableBindings());
		}

		return trap;
	}
	
	private void setProperties(final Notification notification, Class<?> clazz, List<? extends VariableBinding> list) throws Exception {
		MIBNotification annotation = clazz.getAnnotation(MIBNotification.class);

		List<MIBNotificationObject> objects = new LinkedList<>();
		if(!Trap.class.isAssignableFrom(clazz)) {
			objects.addAll(Arrays.asList(annotation.snmpv2()));
		}
		objects.addAll(Arrays.asList(annotation.objects()));
		assert objects.size() == list.size(); 
		
		Map<String, Property> properties = configuration.getVariables(annotation);
		int i = 0;
		for(VariableBinding vb : list) {
			if(vb == null) {
				continue;
			}
			OID oid = vb.getOid();
			Variable var = vb.getVariable();
			// skip v2 exceptions
			if(var instanceof Null) {
				continue;
			}
			int indexLength = resolveIndexLength(objects.get(i++), vb);
			if(indexLength > 0) {
				oid.trim(indexLength);
			}
			//oid.removeLast();
			Property property = properties.get(oid.toString());
			Syntax syntax = Syntax.valueOf(property.getSyntax());
			Class<?> type = property.getClazz();//configuration.getVariableType(oid.toString());
			PropertyAccessor accessor = property.getAccessor();//configuration.getNotificationPropertyAccessor(oid.toString());
			SNMPTypeConverter<?, Variable> converter = RegisteredConverters.<Variable>getConverter(syntax, type);
			if(converter != null) {
				Object value = converter.fromVar(var);
				accessor.setValue(value, notification);
			}
		}

	}
	
	private int resolveIndexLength(MIBNotificationObject obj, VariableBinding vb) {
		// FIXME : implement multiple indexes and multiple subid's indexes
		int indexLength = 0;
		Class<?> clazz = obj.objectClass();
		if(configuration.isScalarGroup(clazz) || configuration.isObjectGroup(clazz)) {
			// extra-check for scalars
			if(vb.getOid().last() == 0) {
				indexLength = 1;
			}
		}
		else if(configuration.isTableEntry(clazz)) {
			MIBTableEntry tableEntry = clazz.getAnnotation(MIBTableEntry.class);
			MIBTableIndex[] indexes = tableEntry.indexes();
			for(MIBTableIndex index : indexes) {
				if(MIBTableIndex.SELF.class.equals(index.tableEntry())) {
					indexLength += getIndexLength(index, configuration.getVariables(tableEntry));
				}
				else {
					Class<? extends Serializable> clazz2 = index.tableEntry();
					MIBTableEntry tableEntry2 = clazz2.getAnnotation(MIBTableEntry.class);
					indexLength += getIndexLength(index, configuration.getVariables(tableEntry2));
				}
			}
		}
		return indexLength;
	}
	
	private <T extends Serializable> int getIndexLength(MIBTableIndex index, Map<String, MIBVariable> variables) {
		for(MIBVariable var : variables.values()) {
			if(index.name().equals(var.name())) {
				switch(Syntax.valueOf(var.syntax())) {
					case IpAddress:
						return 4;
					default:
						return 1;
				}
			}
		}
		return 1;
	}
	static class MultiThreadedTrapReceiver implements CommandResponder {

		  // initialize Log4J logging
		/*
		  static {
		    LogFactory.setLogFactory(new Log4jLogFactory());
		    BER.setCheckSequenceLength(false);
		  }
		*/
		  private MultiThreadedMessageDispatcher dispatcher;
		  private Snmp snmp = null;
		  private Address listenAddress;
		  private ThreadPool threadPool;

		  private int n = 0;
		  private long start = -1;


		  public MultiThreadedTrapReceiver() {
//		    BasicConfigurator.configure();
		  }

		  private void init() throws UnknownHostException, IOException {
		    threadPool = ThreadPool.create("Trap", 2);
		    dispatcher =
		        new MultiThreadedMessageDispatcher(threadPool,
		                                           new MessageDispatcherImpl());
		    listenAddress =
		        GenericAddress.parse(System.getProperty("snmp4j.listenAddress",
		                                                "udp:0.0.0.0/162"));
		    TransportMapping<?> transport;
		    if (listenAddress instanceof UdpAddress) {
		      transport = new DefaultUdpTransportMapping((UdpAddress)listenAddress);
		    }
		    else {
		      transport = new DefaultTcpTransportMapping((TcpAddress)listenAddress);
		    }
		    snmp = new Snmp(dispatcher, transport);
		    snmp.getMessageDispatcher().addMessageProcessingModel(new MPv1());
		    snmp.getMessageDispatcher().addMessageProcessingModel(new MPv2c());
		    snmp.getMessageDispatcher().addMessageProcessingModel(new MPv3());
		    USM usm = new USM(SecurityProtocols.getInstance(),
		                      new OctetString(MPv3.createLocalEngineID()), 0);
		    SecurityModels.getInstance().addSecurityModel(usm);
		    snmp.listen();
		  }

		  public void run() {
		    try {
		      init();
		      snmp.addCommandResponder(this);
		    }
		    catch (Exception ex) {
		      ex.printStackTrace();
		    }
		  }

		  public static void main(String[] args) {
		    MultiThreadedTrapReceiver multithreadedtrapreceiver = new
		        MultiThreadedTrapReceiver();
		    multithreadedtrapreceiver.run();
		  }

		  public <A extends Address> void processPdu(CommandResponderEvent<A> event) {
		    if (start < 0) {
		      start = System.currentTimeMillis()-1;
		    }
//		    System.out.println(event.toString());
		    n++;
		    if ((n % 100 == 1)) {
		      System.out.println("Processed " +
		                         (n / (double)(System.currentTimeMillis() - start)) * 1000 +
		                         "/s, total="+n);
		    }
		  }
		}

}
