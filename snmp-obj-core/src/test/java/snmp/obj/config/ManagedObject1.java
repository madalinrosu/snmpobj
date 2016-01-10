package snmp.obj.config;

import java.io.Serializable;

import snmp.obj.mib.Access;
import snmp.obj.mib.annotations.MIBVariable;
import snmp.obj.mib.annotations.MIBScalarGroup;

@MIBScalarGroup(oid = "1.3.6.1.2.1.1")
public class ManagedObject1 implements Serializable {

	private static final long serialVersionUID = 1559765792250098437L;

	@MIBVariable(oid = "1", syntax = "DisplayString", access = Access.ReadOnly)
	String sysDescr;
	
	String sysObjectID;

	public String getSysDescr() {
		return sysDescr;
	}

	public void setSysDescr(String sysDescr) {
		this.sysDescr = sysDescr;
	}

	@MIBVariable(oid = "2", syntax = "OBJECT_IDENTIFIER", access = Access.ReadOnly)
	public String getSysObjectID() {
		return sysObjectID;
	}

	public void setSysObjectID(String sysObjectID) {
		this.sysObjectID = sysObjectID;
	}
	
	
}
