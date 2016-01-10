package snmp.obj.proxy.javassist;

import java.io.Serializable;
import java.lang.reflect.Method;

import javassist.util.proxy.MethodFilter;
import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;
import snmp.obj.SNMPSession;
import snmp.obj.proxy.ManagedObjectProxy;
import snmp.obj.proxy.ManagedObjectProxyException;
import snmp.obj.proxy.ManagedObjectProxyFactory;

public class ManagedObjectProxyFactoryImpl implements ManagedObjectProxyFactory {

	private static final MethodFilter METHOD_FILTER = new MethodFilter() {
		public boolean isHandled(Method m) {
			// skip finalize methods
			// handles only getters
			if( m.getParameterTypes().length != 0) {
				return false;
			}
			String methodName = m.getName();
			return (methodName.startsWith("get") || methodName.startsWith("is"));
		}
	};

	private static final MethodFilter FINALIZE_FILTER = new MethodFilter() {
		public boolean isHandled(Method m) {
			// skip finalize methods
			return !( m.getParameterTypes().length == 0 && m.getName().equals( "finalize" ) );
		}
	};


	@Override
	public ManagedObjectProxy createProxy(Class<? extends Serializable> managedObjectClass, SNMPSession session) {
		ManagedObjectInitializerImpl initializer = new ManagedObjectInitializerImpl(managedObjectClass, session);
		ProxyFactory factory = new ProxyFactory();
		factory.setSuperclass(managedObjectClass);
		factory.setInterfaces(new Class<?>[] { ManagedObjectProxy.class });
		factory.setFilter(FINALIZE_FILTER);
		Class<?> clazz = factory.createClass();
		ManagedObjectProxy proxy = null;
		try {
			proxy = (ManagedObjectProxy) clazz.newInstance();
			((ProxyObject)proxy).setHandler(initializer);
			return proxy;
		} 
		catch (Exception e) {
			throw new ManagedObjectProxyException("Failed to create managed object Javassist proxy for " + managedObjectClass.getName(), e);
		}
	}

}
