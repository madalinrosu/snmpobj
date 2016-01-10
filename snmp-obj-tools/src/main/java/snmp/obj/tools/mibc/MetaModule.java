package snmp.obj.tools.mibc;

import java.util.ArrayList;
import java.util.List;

import com.adventnet.snmp.mibs.MibModule;

final class MetaModule implements MetaElement {

	final String name;
	
	final List<MetaObject> objects = new ArrayList<>();
	
	final List<MetaObject> notifications = new ArrayList<>();
	
	MetaModule(MibModule mibModule) {
		this.name = mibModule.getName();
	}
}
