package snmp.obj.proxy.cglib;

import java.io.Serializable;
import java.lang.reflect.Method;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.CallbackFilter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;
import net.sf.cglib.proxy.NoOp;
import snmp.obj.SNMPSession;
import snmp.obj.proxy.ManagedObjectProxy;
import snmp.obj.proxy.ManagedObjectProxyException;
import snmp.obj.proxy.ManagedObjectProxyFactory;

public class ManagedObjectProxyFactoryImpl implements ManagedObjectProxyFactory {

	private static final CallbackFilter FINALIZE_FILTER = new CallbackFilter() {
		public int accept(Method method) {
			if ( method.getParameterTypes().length == 0 && method.getName().equals("finalize") ){
				return 1;
			}
			else {
				return 0;
			}
		}
	};


	@Override
	public ManagedObjectProxy createProxy(Class<? extends Serializable> managedObjectClass, SNMPSession session) {
		ManagedObjectInitializerImpl initializer = new ManagedObjectInitializerImpl(managedObjectClass, session);
		
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(managedObjectClass);
		enhancer.setInterfaces(new Class<?>[] { ManagedObjectProxy.class });
		enhancer.setCallbackTypes(new Class<?>[] { InvocationHandler.class, NoOp.class });
  		enhancer.setCallbackFilter(FINALIZE_FILTER);
  		enhancer.setUseFactory(false);
		enhancer.setInterceptDuringConstruction(false);
		Class<?> clazz = enhancer.createClass();

		ManagedObjectProxy proxy = null;
		try {
			Enhancer.registerCallbacks(clazz, new Callback[]{ initializer, null });
			proxy = (ManagedObjectProxy) clazz.newInstance();
			return proxy;
		} 
		catch(Exception e) {
			throw new ManagedObjectProxyException("Failed to create managed object Cglib proxy for " + managedObjectClass.getName(), e);
		}
		finally {
			// make sure the callback gets cleared, otherwise the instance stays in a static thread local.
			Enhancer.registerCallbacks(clazz, null);
		}

	}

}
