package snmp.obj.proxy;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import snmp.obj.SNMPSession;

public abstract class ManagedObjectInitializer implements Serializable {
	
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	protected Class<? extends Serializable> managedObjectClass;
	protected SNMPSession session;
	private Object target;
	private boolean initialized;
	private boolean overridesEquals;
	
	protected ManagedObjectInitializer() {
		
	}
	
	protected ManagedObjectInitializer(Class<? extends Serializable> managedObjectClass, SNMPSession session) {
		this.managedObjectClass = managedObjectClass;
		this.session = session;
	}
	
	
	public SNMPSession getSession() {
		return session;
	}


	public void setSession(SNMPSession session) {
		this.session = session;
	}


	public Object getTarget() {
		return target;
	}


	public void setTarget(Serializable target) {
		this.target = target;
	}


	public boolean isInitialized() {
		return initialized;
	}


	public void setInitialized(boolean initialized) {
		this.initialized = initialized;
	}


	public Class<? extends Serializable> getManagedObjectClass() {
		return managedObjectClass;
	}


	public void setManagedObjectClass(Class<? extends Serializable> managedObjectClass) {
		this.managedObjectClass = managedObjectClass;
	}

	public Object getManagedObject() {
		initialize();
		return target;
	}
	
	protected final void initialize() {
		if(!initialized) {
			if(session == null) {
				throw new RuntimeException("could not initialize proxy - no Session");
			}
			else {
				target = loadManagedObject();
				initialized = true;
				checkTargetState();
			}
		}
		else {
			checkTargetState();
		}
	}
	
	protected Object loadManagedObject() {
		logger.info("Initialize proxy of " + managedObjectClass);
		return session.getManagedObject(managedObjectClass);
	}
	
	protected final boolean isPublic(Method method) {
		return 	Modifier.isPublic(method.getModifiers()) && Modifier.isPublic(managedObjectClass.getModifiers());
	}
	
	private void checkTargetState() {
		
	}
	
	protected final Object invoke(Method method, Object[] args, Object proxy) throws Throwable {

		String methodName = method.getName();
		int params = args != null ? args.length : 0;

		if(params == 0) {

			if("writeReplace".equals(methodName)) {
				return null;//getReplacement();
			}
			else if(!overridesEquals && "hashCode".equals(methodName)) {
				return System.identityHashCode(proxy);
			}
//			else if ( isUninitialized() && method.equals(getIdentifierMethod) ) {
//				return getIdentifier();
//			}
			else if("getInitializer".equals(methodName)) {
				return this;
			}

		}
		else if(params == 1) {

			if(!overridesEquals && "equals".equals(methodName)) {
				return (args[0] == proxy);
			}
//			else if ( method.equals(setIdentifierMethod) ) {
//				initialize();
//				setIdentifier( (Serializable) args[0] );
//				return INVOKE_IMPLEMENTATION;
//			}

		}

		//if it is a property of an embedded component, invoke on the "identifier"
//		if ( componentIdType!=null && componentIdType.isMethodOf(method) ) {
//			return method.invoke( getIdentifier(), args );
//		}

		// otherwise:
		return null;//INVOKE_IMPLEMENTATION;

	}

}
