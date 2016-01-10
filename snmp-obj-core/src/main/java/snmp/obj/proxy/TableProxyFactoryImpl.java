package snmp.obj.proxy;

import java.io.Serializable;
import java.lang.reflect.Proxy;

import snmp.obj.SNMPSession;

public class TableProxyFactoryImpl implements TableProxyFactory {

	public TableProxyFactoryImpl() {
	}

	@Override
	public TableProxy createTableProxy(Class<? extends Serializable> tableEntryClass, Class<?> tableHolderClass, SNMPSession session) {
		if(!tableHolderClass.isInterface()) {
			throw new ManagedObjectProxyException("Failed to create table JDK dynamic proxy for " + tableHolderClass.getName() + " : not an interface.");
		}
		try {
			TableProxy proxy = (TableProxy) Proxy.newProxyInstance(TableProxy.class.getClassLoader(), 
									new Class<?>[] { TableProxy.class, tableHolderClass }, 
									new TableInitializerImpl(tableEntryClass, tableHolderClass, session));
			return proxy;
		}
		catch(Exception e) {
			throw new ManagedObjectProxyException("Failed to create table JDK dynamic proxy for " + tableHolderClass.getName(), e);
		}
	}

}
