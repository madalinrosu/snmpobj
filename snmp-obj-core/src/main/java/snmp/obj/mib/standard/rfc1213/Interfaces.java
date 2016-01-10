package snmp.obj.mib.standard.rfc1213;

import java.io.Serializable;
import java.util.List;

import snmp.obj.mib.Access;
import snmp.obj.mib.annotations.MIBObjectGroup;
import snmp.obj.mib.annotations.MIBTable;
import snmp.obj.mib.annotations.MIBVariable;

@MIBObjectGroup(oid = "1.3.6.1.2.1.2")
public class Interfaces implements Serializable {

	private static final long serialVersionUID = 7063089880707874389L;

	@MIBVariable(oid = "1", name = "ifNumber", syntax = "INTEGER", access = Access.ReadOnly)
	Integer ifNumber;
	
	@MIBTable(oid = "2", lazy = false, tableEntry = IfEntry.class)
	List<IfEntry> ifTable;

	public Integer getIfNumber() {
		return ifNumber;
	}

	public void setIfNumber(Integer ifNumber) {
		this.ifNumber = ifNumber;
	}

	public List<IfEntry> getIfTable() {
		return ifTable;
	}

	public void setIfTable(List<IfEntry> ifTable) {
		this.ifTable = ifTable;
	}
	
	
}
