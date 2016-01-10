package snmp.obj.dyn;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

class DynamicManagedObject implements InvocationHandler {

	private final Map<String, Object> attributes = new HashMap<>();
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		
		String methodName = method.getName();
		if(methodName.startsWith("get")) {
			String attributeName = methodName.substring(3);
			return attributes.get(attributeName);
		}
		else if(methodName.startsWith("set")) {
			String attributeName = methodName.substring(3);
			attributes.put(attributeName, args[0]);
		}
		
		// TODO Auto-generated method stub
		return null;
	}

}
