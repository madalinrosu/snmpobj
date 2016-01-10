package snmp.obj.proxy;

import java.io.Serializable;

import snmp.obj.SNMPSession;

public interface ManagedObjectProxyFactory extends Serializable {
	
	ManagedObjectProxy createProxy(Class<? extends Serializable> managedObjectClass, SNMPSession session);

}
