package snmp.obj.integrations.adventnet;

import snmp.obj.config.Configuration;
import snmp.obj.notification.DefaultSNMPTrapd;

public class SNMPTrapdImpl extends DefaultSNMPTrapd {

	
	private final Configuration configuration;
	
	public SNMPTrapdImpl(final Configuration configuration) {
		this.configuration = configuration;
	}

	
	private void initialize() {

	}
	@Override
	public void start() {
		initialize();


	}

	@Override
	public void stop() {
	}

//	private OID getSnmpTrapOID(final PDU pdu) {
//		VariableBinding snmpTrapOidVarBind = pdu.get(1);
//		return (OID)snmpTrapOidVarBind.getVariable();
//	}
//
//	private OID getSnmpTrapOID(final PDUv1 pdu) {
//		int genericTrap = pdu.getGenericTrap();
//		if(genericTrap == PDUv1.ENTERPRISE_SPECIFIC) {
//			return new OID(pdu.getEnterprise().toIntArray(), new int[] { 0, pdu.getSpecificTrap() });
//		}
//		else {
//			return new OID(SnmpConstants.snmpTraps.toIntArray(), new int[] { genericTrap + 1 });
//		}
//	}
//
//	private Notification createNotification(final PDU pdu) throws Exception {
//		Notification notification = null;
//		
//		OID snmpTrapOid = getSnmpTrapOID(pdu);
//		Class<?> clazz = configuration.getNotificationType(snmpTrapOid.toString());
//		if(clazz != null) {
//			notification = (Notification) clazz.newInstance();
//			
//			setProperties(notification, clazz, pdu.getVariableBindings());
//		}
//		
//		return notification;
//	}
//	
//	private Trap createTrap(final PDUv1 pdu) throws Exception {
//		Trap trap = null;
//		OID snmpTrapOid = getSnmpTrapOID(pdu);
//		Class<?> clazz = configuration.getNotificationType(snmpTrapOid.toString());
//		trap = (Trap) clazz.newInstance();
//		if(clazz != null) {
//			trap = (Trap) clazz.newInstance();
//			trap.setAgentAddress(pdu.getAgentAddress().toString());
//			trap.setEnterprise(pdu.getEnterprise().toString());
//			trap.setGenericTrap(pdu.getGenericTrap());
//			if(pdu.getGenericTrap() == PDUv1.ENTERPRISE_SPECIFIC) {
//				trap.setSpecificTrap(pdu.getSpecificTrap());
//			}
//			trap.setTimestamp(pdu.getTimestamp());
//			setProperties(trap, clazz, pdu.getVariableBindings());
//		}
//
//		return trap;
//	}
//	
//	private void setProperties(final Notification notification, Class<?> clazz, Vector<? extends VariableBinding> variableBindings) throws Exception {
//		MIBNotification annotation = clazz.getAnnotation(MIBNotification.class);
//
//		List<MIBNotificationObject> objects = new LinkedList<>();
//		if(!Trap.class.isAssignableFrom(clazz)) {
//			objects.addAll(Arrays.asList(annotation.snmpv2()));
//		}
//		objects.addAll(Arrays.asList(annotation.objects()));
//		assert objects.size() == variableBindings.size(); 
//		
//		Map<String, Property> properties = configuration.getVariables(annotation);
//		int i = 0;
//		for(VariableBinding vb : variableBindings) {
//			if(vb == null) {
//				continue;
//			}
//			OID oid = vb.getOid();
//			Variable var = vb.getVariable();
//			// skip v2 exceptions
//			if(var instanceof Null) {
//				continue;
//			}
//			int indexLength = resolveIndexLength(objects.get(i++), vb);
//			if(indexLength > 0) {
//				oid.trim(indexLength);
//			}
//			//oid.removeLast();
//			Property property = properties.get(oid.toString());
//			Syntax syntax = Syntax.valueOf(property.getSyntax());
//			Class<?> type = property.getClazz();//configuration.getVariableType(oid.toString());
//			PropertyAccessor accessor = property.getAccessor();//configuration.getNotificationPropertyAccessor(oid.toString());
//			SNMPTypeConverter<?, Variable> converter = RegisteredConverters.<Variable>getConverter(syntax, type);
//			if(converter != null) {
//				Object value = converter.fromVar(var);
//				accessor.setValue(value, notification);
//			}
//		}
//
//	}
	
//	private int resolveIndexLength(MIBNotificationObject obj, VariableBinding vb) {
//		// FIXME : implement multiple indexes and multiple subid's indexes
//		int indexLength = 0;
//		Class<?> clazz = obj.objectClass();
//		if(configuration.isScalarGroup(clazz) || configuration.isObjectGroup(clazz)) {
//			// extra-check for scalars
//			if(vb.getOid().last() == 0) {
//				indexLength = 1;
//			}
//		}
//		else if(configuration.isTableEntry(clazz)) {
//			MIBTableEntry tableEntry = clazz.getAnnotation(MIBTableEntry.class);
//			MIBTableIndex[] indexes = tableEntry.indexes();
//			for(MIBTableIndex index : indexes) {
//				if(MIBTableIndex.SELF.class.equals(index.tableEntry())) {
//					indexLength += getIndexLength(index, configuration.getVariables(tableEntry));
//				}
//				else {
//					Class<? extends Serializable> clazz2 = index.tableEntry();
//					MIBTableEntry tableEntry2 = clazz2.getAnnotation(MIBTableEntry.class);
//					indexLength += getIndexLength(index, configuration.getVariables(tableEntry2));
//				}
//			}
//		}
//		return indexLength;
//	}
//	
//	private <T extends Serializable> int getIndexLength(MIBTableIndex index, Map<String, MIBVariable> variables) {
//		for(MIBVariable var : variables.values()) {
//			if(index.name().equals(var.name())) {
//				switch(Syntax.valueOf(var.syntax())) {
//					case IpAddress:
//						return 4;
//					default:
//						return 1;
//				}
//			}
//		}
//		return 1;
//	}

}
