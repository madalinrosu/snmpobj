package snmp.obj.mib.standard.rfc1213;

import java.io.Serializable;

import snmp.obj.mib.annotations.MIBObjectGroup;
import snmp.obj.mib.annotations.MIBSubtree;

@MIBObjectGroup(oid = "1.3.6.1.2.1")
public class Mib2 implements Serializable {

	private static final long serialVersionUID = -7847234681696720716L;

	@MIBSubtree(oid = "1", lazy = true)
	System system;

	@MIBSubtree(oid = "2", lazy = true, getBulk = true)
	Interfaces interfaces;

	public System getSystem() {
		return system;
	}

	public void setSystem(System system) {
		this.system = system;
	}

	public Interfaces getInterfaces() {
		return interfaces;
	}

	public void setInterfaces(Interfaces interfaces) {
		this.interfaces = interfaces;
	}

	
}
