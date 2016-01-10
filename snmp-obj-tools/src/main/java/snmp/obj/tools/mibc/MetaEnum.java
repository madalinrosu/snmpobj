package snmp.obj.tools.mibc;



import com.adventnet.snmp.mibs.LeafSyntax;
import com.adventnet.snmp.mibs.MibNode;

final class MetaEnum implements MetaElement {

	final String name;
	
	MetaEnum(MibNode mibNode) {
		this.name = mibNode.getLabel();
		LeafSyntax syntax = mibNode.getSyntax();
		if(syntax.isEnumerated()) {
			syntax.getEnumint();
			
		}
	}
}
