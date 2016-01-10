package snmp.obj.proxy;

import java.io.Serializable;

import snmp.obj.SNMPSession;

public interface TableProxyFactory extends Serializable {
	TableProxy createTableProxy(Class<? extends Serializable> managedObjectClass, Class<?> tableHolderClass, SNMPSession session);
}
