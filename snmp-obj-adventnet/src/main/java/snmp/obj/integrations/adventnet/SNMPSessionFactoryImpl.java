package snmp.obj.integrations.adventnet;

import snmp.obj.DefaultSNMPSessionFactory;
import snmp.obj.SNMPSessionSettings;
import snmp.obj.SNMPSessionSupport;
import snmp.obj.SNMPVersion;
import snmp.obj.config.Configuration;

import com.adventnet.snmp.beans.SnmpTable;
import com.adventnet.snmp.beans.SnmpTarget;

public class SNMPSessionFactoryImpl extends DefaultSNMPSessionFactory {

	private final Configuration configuration;
	
	public SNMPSessionFactoryImpl(final Configuration configuration) {
		this.configuration = configuration;
	}


	@Override
	public SNMPSessionSupport createSNMPv1Session(String address,
			String community, int port, int retries, long timeout) {
		SnmpTarget target = createCommmunityTarget(SnmpTarget.VERSION1, address, community, port, retries, timeout);
		SNMPSessionSettings settings = createSNMPSessionSettings(SNMPVersion.v1, address, port, retries, timeout, community);
		return new SNMPSessionImpl(target, configuration, settings);
	}

	@Override
	public SNMPSessionSupport createSNMPv2cSession(String address,
			String community, int port, int retries, long timeout) {
		SnmpTarget target = createCommmunityTarget(SnmpTarget.VERSION2C, address, community, port, retries, timeout);
		SNMPSessionSettings settings = createSNMPSessionSettings(SNMPVersion.v2c, address, port, retries, timeout, community);
		return new SNMPSessionImpl(target, configuration, settings);
	}

	private SnmpTarget createCommmunityTarget(int version, String address, String community, int port, int retries, long timeout) {
		SnmpTable target = new SnmpTable();
		target.setSnmpVersion( version ); 
		target.setTargetHost( address );  // set the agent hostname
		target.setTargetPort( port );
		target.setCommunity( community );
		target.setRetries( retries );
		target.setTimeout( (int)timeout ); 		   
		return target;
	}


	@Override
	public SNMPSessionSupport createSNMPv3Session(String agentAddress,
			String securityName, int port, int retries, long timeout) {
		// TODO Auto-generated method stub
		return null;
	}

}
