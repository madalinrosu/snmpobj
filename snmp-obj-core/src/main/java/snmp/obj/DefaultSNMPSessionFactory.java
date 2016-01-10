package snmp.obj;

public abstract class DefaultSNMPSessionFactory implements SNMPSessionFactory, SNMPDefaults {

	@Override
	public SNMPSessionSupport createSNMPv1Session(String address, String community) {
		return createSNMPv1Session(address, community, DEFAULT_PORT, DEFAULT_RETRY, DEFAULT_TIMEOUT);
	}

	@Override
	public SNMPSessionSupport createSNMPv2cSession(String address, String community) {
		return createSNMPv2cSession(address, community, DEFAULT_PORT, DEFAULT_RETRY, DEFAULT_TIMEOUT);
	}

	@Override
	public SNMPSessionSupport createSNMPv3Session(String agentAddress,	String securityName) {
		return createSNMPv3Session(agentAddress, securityName, DEFAULT_PORT, DEFAULT_RETRY, DEFAULT_TIMEOUT);
	}
	
	public abstract SNMPSessionSupport createSNMPv1Session(String agentAddress, String community, int port, int retries, long timeout);

	public abstract SNMPSessionSupport createSNMPv2cSession(String agentAddress, String community, int port, int retries, long timeout);

	public abstract SNMPSessionSupport createSNMPv3Session(String agentAddress, String securityName, int port, int retries, long timeout);

	protected SNMPSessionSettings createSNMPSessionSettings(SNMPVersion version, String address,
			int port, int retries, long timeout, String community) {
		return new SNMPSessionSettings(version, address, port, retries, timeout, community);
	}

	
}
