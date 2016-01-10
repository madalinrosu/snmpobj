package snmp.obj.proxy;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import snmp.obj.SNMPSession;

public class TableInitializerImpl extends ManagedObjectInitializer implements InvocationHandler {

	private Class<?> tableHolderClass;

	public TableInitializerImpl() {
	}

	public TableInitializerImpl(Class<? extends Serializable> managedObjectClass, SNMPSession session) {
		this(managedObjectClass, List.class, session);
	}

	public TableInitializerImpl(Class<? extends Serializable> managedObjectClass, Class<?> tableHolderClass, SNMPSession session) {
		super(managedObjectClass, session);
		this.tableHolderClass = tableHolderClass;
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

	@Override
	protected Object loadManagedObject() {
		logger.info(String.format("Initialize proxy of %s<%s>", tableHolderClass.getSimpleName(), managedObjectClass.getName()));
		
		List<?> rows = session.getTable(managedObjectClass);
		
		if(tableHolderClass.isAssignableFrom(List.class)) {
			return Collections.unmodifiableList(new LinkedList<>(rows));
		}
		else if(tableHolderClass.isAssignableFrom(Set.class)) {
			return Collections.unmodifiableSet(new LinkedHashSet<>(rows));
		}
		else if(tableHolderClass.isAssignableFrom(Map.class)) {
			return Collections.unmodifiableMap(new LinkedHashMap<>());
		}
		return rows;
	}
	
	

}
