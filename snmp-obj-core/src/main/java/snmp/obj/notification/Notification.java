package snmp.obj.notification;

import java.beans.Transient;
import java.io.Serializable;

import snmp.obj.mib.Access;
import snmp.obj.mib.annotations.MIBVariable;

public abstract class Notification implements Serializable {

	private static final long serialVersionUID = 403421015622433857L;

	@MIBVariable(oid = "1.3.6.1.2.1.1.3", name = "sysUpTime", syntax = "TimeTicks")
	private Integer sysUpTime;

	@MIBVariable(oid = "1.3.6.1.6.3.1.1.4.1", name = "snmpTrapOID", syntax = "OBJECT_IDENTIFIER", access = Access.AccessibleforNotify)
	private String snmpTrapOID;
	
	@MIBVariable(oid = "1.3.6.1.6.3.1.1.4.3", name = "snmpTrapEnterprise", syntax = "OBJECT_IDENTIFIER", access = Access.AccessibleforNotify)
	private String snmpTrapEnterprise;
	
	
	@Transient
	public boolean isTrapv1() {
		return false;
	}


	public Integer getSysUpTime() {
		return sysUpTime;
	}


	public void setSysUpTime(Integer sysUpTime) {
		this.sysUpTime = sysUpTime;
	}


	public String getSnmpTrapOID() {
		return snmpTrapOID;
	}


	public void setSnmpTrapOID(String snmpTrapOID) {
		this.snmpTrapOID = snmpTrapOID;
	}


	public String getSnmpTrapEnterprise() {
		return snmpTrapEnterprise;
	}


	void setSnmpTrapEnterprise(String snmpTrapEnterprise) {
		this.snmpTrapEnterprise = snmpTrapEnterprise;
	}
	
	
}
