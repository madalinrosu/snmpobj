package snmp.obj.proxy.jdk;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import snmp.obj.SNMPSession;
import snmp.obj.proxy.ManagedObjectInitializer;

public class ManagedObjectInitializerImpl extends ManagedObjectInitializer implements InvocationHandler {

	public ManagedObjectInitializerImpl() {
		super();
	}

	public ManagedObjectInitializerImpl(Class<? extends Serializable> managedObjectClass, SNMPSession session) {
		super(managedObjectClass, session);
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object result = super.invoke(method, args, proxy);
		if(result instanceof ManagedObjectInitializer) {
			return result;
		}
		Object target = getManagedObject();
		final Object returnValue;
		try {
			if(isPublic(method)) {
				if(!method.getDeclaringClass().isInstance(target)) {
					throw new ClassCastException(target.getClass().getName());
				}
				returnValue = method.invoke(target, args);
			}
			else {
				if(!method.isAccessible()) {
					method.setAccessible(true);
				}
				returnValue = method.invoke(target, args);
			}
			return returnValue == target ? proxy : returnValue;
		}
		catch ( InvocationTargetException ite ) {
			throw ite.getTargetException();
		}	
	}

}
