package snmp.obj;

import java.io.Serializable;

public class SNMPSessionSettings implements Serializable, SNMPDefaults {

	/**
	 * 
	 */
	private static final long serialVersionUID = 66483764452971139L;

	private SNMPVersion version = DEFAULT_VERSION;
	
	private String agentAddress;
	private int port = DEFAULT_PORT;
	private int retries = DEFAULT_RETRY;
	private long timeout = DEFAULT_TIMEOUT;
	
	private String community = DEFAULT_RDONLY_COMMUNITY;

	
	public SNMPSessionSettings(String agentAddress) {
		super();
		this.agentAddress = agentAddress;
	}
	
	

	public SNMPSessionSettings(String agentAddress, int port, int retries,
			long timeout, String community) {
		super();
		this.agentAddress = agentAddress;
		this.port = port;
		this.retries = retries;
		this.timeout = timeout;
		this.community = community;
	}



	public SNMPSessionSettings(SNMPVersion version, String agentAddress,
			int port, int retries, long timeout, String community) {
		super();
		this.version = version;
		this.agentAddress = agentAddress;
		this.port = port;
		this.retries = retries;
		this.timeout = timeout;
		this.community = community;
	}



	public SNMPVersion getVersion() {
		return version;
	}

	public void setVersion(SNMPVersion version) {
		this.version = version;
	}

	public String getAgentAddress() {
		return agentAddress;
	}

	public void setAgentAddress(String agentAddress) {
		this.agentAddress = agentAddress;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getRetries() {
		return retries;
	}

	public void setRetries(int retries) {
		this.retries = retries;
	}

	public long getTimeout() {
		return timeout;
	}

	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}

	public String getCommunity() {
		return community;
	}

	public void setCommunity(String community) {
		this.community = community;
	}
	
	
}
