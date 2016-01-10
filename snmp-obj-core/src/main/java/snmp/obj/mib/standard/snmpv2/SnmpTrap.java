package snmp.obj.mib.standard.snmpv2;

import java.io.Serializable;

import snmp.obj.mib.Access;
import snmp.obj.mib.annotations.MIBScalarGroup;
import snmp.obj.mib.annotations.MIBVariable;

@MIBScalarGroup(oid = "1.3.6.1.6.3.1.1.4", name = "snmpTrap")
public abstract class SnmpTrap implements Serializable {

	private static final long serialVersionUID = 1L;

	@MIBVariable(oid = "1", name = "snmpTrapOID", syntax = "OBJECT_IDENTIFIER", access = Access.AccessibleforNotify)
	private String snmpTrapOID;
	
	@MIBVariable(oid = "3", name = "snmpTrapEnterprise", syntax = "OBJECT_IDENTIFIER", access = Access.AccessibleforNotify)
	private String snmpTrapEnterprise;

	private SnmpTrap() {
	}

}
