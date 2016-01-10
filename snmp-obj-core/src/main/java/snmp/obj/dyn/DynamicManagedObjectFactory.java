package snmp.obj.dyn;

import java.io.Serializable;
import java.lang.reflect.Proxy;

import snmp.obj.ManagedObjectInstanceException;

public final class DynamicManagedObjectFactory {
	
	private DynamicManagedObjectFactory() {
		
	}

	public static <T extends Serializable> T createObject(Class<T> clazz) {
		if(clazz.isInterface()) {
			return newProxyInstance(clazz);
		}
		else {
			return newInstance(clazz);
		}
	}
	
	private static <T extends Serializable> T newInstance(Class<T> clazz) {

		try {
			return clazz.newInstance();
		} 
		catch (InstantiationException | IllegalAccessException e) {
			throw new ManagedObjectInstanceException("Failed to create new instance for class : " + clazz.getName(), e);
		}
	}	
	
	private static <T extends Serializable> T newProxyInstance(Class<T> clazz) {

		try {
			return (T) Proxy.newProxyInstance(clazz.getClassLoader(), 
					new Class<?>[] { clazz }, new DynamicManagedObject());
		} 
		catch (Exception e) {
			throw new ManagedObjectInstanceException("Failed to create new instance for class : " + clazz.getName(), e);
		}
	}
}
