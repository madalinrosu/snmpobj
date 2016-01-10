package snmp.obj.test.agent;

public class SNMPAgentFactory {

	public SNMPSimpleTestAgent createSimpleAgent() throws Exception {
		return new SNMPSimpleTestAgent();
	}
	
	public SNMPConfigurableTestAgent createConfigurableAgent() throws Exception {
		return new SNMPConfigurableTestAgent();
	}
}
