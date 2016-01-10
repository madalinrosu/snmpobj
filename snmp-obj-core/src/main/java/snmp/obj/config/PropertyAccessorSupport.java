package snmp.obj.config;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Member;

abstract class PropertyAccessorSupport implements PropertyAccessor {

	
	protected final void setAccessible(AccessibleObject accessibleObject) {
		if(!accessibleObject.isAccessible()) {
			accessibleObject.setAccessible(true);
		}
	}

	protected final void throwPropertyAccessException(String operation, Member member, Exception cause) throws PropertyAccessException {
		String message = String.format("%s failed when accessing %s.%s", operation, member.getDeclaringClass().getSimpleName(), member.getName());
		throw new PropertyAccessException(message, cause);
	}
	
	
}
