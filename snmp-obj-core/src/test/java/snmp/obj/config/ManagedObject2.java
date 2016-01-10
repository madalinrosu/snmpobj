package snmp.obj.config;

import java.io.Serializable;

import snmp.obj.mib.Access;
import snmp.obj.mib.annotations.MIBScalarGroup;
import snmp.obj.mib.annotations.MIBVariable;

@MIBScalarGroup(oid = "1.3.6.1.2.1.1")
public class ManagedObject2 implements Serializable {

	private static final long serialVersionUID = 1559765792250098437L;

	@MIBVariable(oid = "1", syntax = "DisplayString", access = Access.ReadOnly)
	String sysDescr;
	
	@MIBVariable(oid = "2", syntax = "OBJECT_IDENTIFIER", access = Access.ReadOnly)
	String sysObjectID;

	@MIBVariable(oid = "1", syntax = "DisplayString", access = Access.ReadOnly)
	public String getSysDescr() {
		return sysDescr;
	}

	public void setSysDescr(String sysDescr) {
		this.sysDescr = sysDescr;
	}
	
	public String getSysObjectID() {
		return sysObjectID;
	}

	public void setSysObjectID(String sysObjectID) {
		this.sysObjectID = sysObjectID;
	}

	
}
