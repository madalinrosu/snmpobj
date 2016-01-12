package snmp.obj.integrations.jre;

import snmp.obj.DefaultSNMPSessionFactory;
import snmp.obj.SNMPSessionSupport;

public class SNMPSessionFactoryImpl extends DefaultSNMPSessionFactory {

	@Override
	public SNMPSessionSupport createSNMPv1Session(String agentAddress, String community, int port, int retries,
			long timeout) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SNMPSessionSupport createSNMPv2cSession(String agentAddress, String community, int port, int retries,
			long timeout) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SNMPSessionSupport createSNMPv3Session(String agentAddress, String securityName, int port, int retries,
			long timeout) {
		// TODO Auto-generated method stub
		return null;
	}

}
