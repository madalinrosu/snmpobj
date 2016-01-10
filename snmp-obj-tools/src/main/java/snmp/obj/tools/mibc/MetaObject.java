package snmp.obj.tools.mibc;

import java.util.ArrayList;
import java.util.List;

import com.adventnet.snmp.mibs.MibNode;

final class MetaObject implements MetaElement {

	final String name;
	
	final String oid;
	
	final List<MetaVariable> variables = new ArrayList<>();

	final List<MetaVariable> enumerations = new ArrayList<>();
	
	MetaObject(MibNode mibNode) {
		this.name = mibNode.getLabel();
		this.oid = mibNode.getNumberedOIDString();
	}
}
