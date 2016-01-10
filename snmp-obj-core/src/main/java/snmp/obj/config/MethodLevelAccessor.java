package snmp.obj.config;

import java.lang.reflect.Method;

final class MethodLevelAccessor extends PropertyAccessorSupport {

	Method getter, setter;
	MethodLevelAccessor(Method getter, Method setter) {
		this.getter = getter;
		this.setter = setter;
	}
	
	@Override
	public Object getValue(Object obj) {
		try {
			setAccessible(getter);
			return getter.invoke(obj);
		}
		catch(Exception e) {
			throwPropertyAccessException("getValue", getter , e);
		}
		return null;
	}

	@Override
	public void setValue(Object value, Object obj) {
		try {
			setAccessible(setter);
			setter.invoke(obj, value);
		}
		catch(Exception e) {
			throwPropertyAccessException("setValue", setter , e);
		}
	}
	
}