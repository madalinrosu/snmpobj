package snmp.obj.notification;

import java.beans.Transient;


public abstract class Trap extends Notification {

	private static final long serialVersionUID = 1806500065601242282L;

//	public static final int COLDSTART = 0;
//	public static final int WARMSTART = 1;
//	public static final int LINKDOWN = 2;
//	public static final int LINKUP = 3;
//	public static final int AUTHENTICATIONFAILURE = 4;
	private static final int ENTERPRISE_SPECIFIC = 6;
	private static final String SNMPV2_TRAPS = "1.3.6.1.6.3.1.1.5";

	private String enterprise;
	private String agentAddress;
	private Integer genericTrap;
	private Integer specificTrap;
	private Long timestamp;

	public Trap() {
		
	}

	public Trap(String enterprise,String agentAddress,Integer genericTrap,Integer specificTrap,Long timestamp) {
		this.enterprise = enterprise;
		this.agentAddress = agentAddress;
		this.genericTrap = genericTrap;
		this.specificTrap = specificTrap;
		this.timestamp = timestamp;
	}

	@Override
	@Transient
	public boolean isTrapv1() {
		return true;
	}


	@Override
	public Integer getSysUpTime() {
		return timestamp.intValue();
	}


	@Override
	public String getSnmpTrapOID() {
		if(genericTrap == ENTERPRISE_SPECIFIC) {
			return String.format("%s.0.%d", enterprise, specificTrap);
		}
		return String.format("%s.%d", SNMPV2_TRAPS, (genericTrap + 1));
	}



	public String getEnterprise() {
		return enterprise;
	}


	public void setEnterprise(String enterprise) {
		this.enterprise = enterprise;
	}


	public String getAgentAddress() {
		return agentAddress;
	}


	public void setAgentAddress(String agentAddress) {
		this.agentAddress = agentAddress;
	}


	public Integer getGenericTrap() {
		return genericTrap;
	}


	public void setGenericTrap(Integer genericTrap) {
		this.genericTrap = genericTrap;
	}


	public Integer getSpecificTrap() {
		return specificTrap;
	}


	public void setSpecificTrap(Integer specificTrap) {
		this.specificTrap = specificTrap;
	}


	public Long getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}


}
