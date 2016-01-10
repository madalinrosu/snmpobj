package snmp.obj.integrations.snmp4j;

import java.io.IOException;

import org.snmp4j.CommunityTarget;
import org.snmp4j.Snmp;
import org.snmp4j.Target;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OctetString;
import org.snmp4j.transport.DefaultUdpTransportMapping;

import snmp.obj.DefaultSNMPSessionFactory;
import snmp.obj.SNMPSessionSettings;
import snmp.obj.SNMPSessionSupport;
import snmp.obj.SNMPVersion;
import snmp.obj.config.Configuration;

public class SNMPSessionFactoryImpl extends DefaultSNMPSessionFactory {

	private transient Snmp snmp;
	private final Configuration configuration;
	
	public SNMPSessionFactoryImpl(final Configuration configuration) {
		this.configuration = configuration;
	}


	@Override
	public SNMPSessionSupport createSNMPv1Session(String address,
			String community, int port, int retries, long timeout) {

		initialize();
		SNMPSessionSettings settings = createSNMPSessionSettings(SNMPVersion.v1, address, port, retries, timeout, community);
		Target target = createCommmunityTarget(SnmpConstants.version1, address, community, port, retries, timeout);
 		return new SNMPSessionImpl(snmp, target, configuration, settings);
	}

	@Override
	public SNMPSessionSupport createSNMPv2cSession(String address,
			String community, int port, int retries, long timeout) {

		initialize();
		SNMPSessionSettings settings = createSNMPSessionSettings(SNMPVersion.v2c, address, port, retries, timeout, community);
		Target target = createCommmunityTarget(SnmpConstants.version2c, address, community, port, retries, timeout);
 		return new SNMPSessionImpl(snmp, target, configuration, settings);
	}

	private synchronized void initialize() {
		if(snmp == null) {
			try {
				snmp = new Snmp(new DefaultUdpTransportMapping());
//				snmp = new Snmp();
//				SecurityProtocols.getInstance().addDefaultProtocols();
//				MessageDispatcher disp = snmp.getMessageDispatcher();
//				disp.addMessageProcessingModel(new MPv1());
//				disp.addMessageProcessingModel(new MPv2c());
//				snmp.addTransportMapping(new DefaultUdpTransportMapping());
//				snmp.addTransportMapping(new DefaultTcpTransportMapping());
//				OctetString localEngineID = new OctetString(MPv3.createLocalEngineID());
//				// For command generators, you may use the following code to avoid
//				// engine ID clashes:
//				// MPv3.createLocalEngineID(new OctetString("MyUniqueID"+System.currentTimeMillis())));
//				USM usm = new USM(SecurityProtocols.getInstance(), localEngineID, 0);
//				disp.addMessageProcessingModel(new MPv3(usm));				
				snmp.listen();
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	private Target createCommmunityTarget(int version, String address, String community, int port, int retries, long timeout) {
		Address agentAddress = GenericAddress.parse(String.format("udp:%s/%d", address, port));
		CommunityTarget target = new CommunityTarget(agentAddress, new OctetString(community));
		target.setVersion(version);
		target.setRetries(retries);
		target.setTimeout(timeout);
		return target;
	}


	@Override
	public SNMPSessionSupport createSNMPv3Session(String agentAddress,
			String securityName, int port, int retries, long timeout) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
