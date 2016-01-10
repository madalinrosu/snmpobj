package snmp.obj.proxy.javassist;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javassist.util.proxy.MethodHandler;
import snmp.obj.SNMPSession;
import snmp.obj.proxy.ManagedObjectInitializer;

public class ManagedObjectInitializerImpl extends ManagedObjectInitializer implements MethodHandler {

	public ManagedObjectInitializerImpl() {
		super();
	}

	public ManagedObjectInitializerImpl(Class<? extends Serializable> managedObjectClass, SNMPSession session) {
		super(managedObjectClass, session);
	}

	@Override
	public Object invoke(Object self, Method thisMethod, Method proceed, Object[] args) throws Throwable {
		Object result = super.invoke(thisMethod, args, self);
		if(result instanceof ManagedObjectInitializer) {
			return result;
		}
		Object target = getManagedObject();
		final Object returnValue;
		try {
			if(isPublic(thisMethod)) {
				if(!thisMethod.getDeclaringClass().isInstance(target)) {
					throw new ClassCastException(target.getClass().getName());
				}
				returnValue = thisMethod.invoke(target, args);
			}
			else {
				if(!thisMethod.isAccessible()) {
					thisMethod.setAccessible(true);
				}
				returnValue = thisMethod.invoke(target, args);
			}
			return returnValue == target ? self : returnValue;
		}
		catch ( InvocationTargetException ite ) {
			throw ite.getTargetException();
		}
	}

}
