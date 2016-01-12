package snmp.obj.proxy.jdk;

import java.io.Serializable;
import java.lang.reflect.Proxy;

import snmp.obj.ManagedObjectInstanceException;
import snmp.obj.SNMPSession;
import snmp.obj.proxy.ManagedObjectProxy;
import snmp.obj.proxy.ManagedObjectProxyFactory;

public class ManagedObjectJdkProxyFactoryImpl implements ManagedObjectProxyFactory {

	@Override
	public ManagedObjectProxy createProxy(Class<? extends Serializable> managedObjectClass, SNMPSession session) {
		if(managedObjectClass.isInterface()) {
			ManagedObjectInitializerImpl initializer = new ManagedObjectInitializerImpl(managedObjectClass, session);

			return (ManagedObjectProxy) Proxy.newProxyInstance(managedObjectClass.getClassLoader(), 
											new Class<?>[] { managedObjectClass, ManagedObjectProxy.class }, initializer);		
		}
		else {
			throw new ManagedObjectInstanceException("Cannot create JDK Proxy for class " + managedObjectClass.getName());
		}
	}

}
