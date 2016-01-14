package snmp.obj.test.agent;

public class SNMPTestAgentFactory {

	public SNMPTestAgent createSimpleAgent() throws Exception {
		return new SNMPSimpleTestAgent();
	}
	
	public SNMPTestAgent createConfigurableAgent() throws Exception {
		return new SNMPConfigurableTestAgent();
	}
}
