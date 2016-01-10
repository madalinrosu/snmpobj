package snmp.obj;

public interface SNMPSessionFactory {


	SNMPSession createSNMPv1Session(String agentAddress, String community);

	SNMPSession createSNMPv1Session(String agentAddress, String community, int port, int retries, long timeout);

	SNMPSession createSNMPv2cSession(String agentAddress, String community);

	SNMPSession createSNMPv2cSession(String agentAddress, String community, int port, int retries, long timeout);

	SNMPSession createSNMPv3Session(String agentAddress, String securityName);

	SNMPSession createSNMPv3Session(String agentAddress, String securityName, int port, int retries, long timeout);
}
