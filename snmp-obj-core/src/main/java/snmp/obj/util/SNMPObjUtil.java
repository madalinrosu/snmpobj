package snmp.obj.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collection;
import java.util.Map;

import snmp.obj.mib.annotations.MIBNotification;
import snmp.obj.mib.annotations.MIBObjectGroup;
import snmp.obj.mib.annotations.MIBScalarGroup;
import snmp.obj.mib.annotations.MIBSubtree;
import snmp.obj.mib.annotations.MIBTable;
import snmp.obj.mib.annotations.MIBTableEntry;
import snmp.obj.mib.annotations.MIBTableIndex;
import snmp.obj.mib.annotations.MIBVariable;
import snmp.obj.proxy.ManagedObjectInitializer;
import snmp.obj.proxy.ManagedObjectProxy;

public final class SNMPObjUtil {

	private SNMPObjUtil() {
	}

	public static boolean isScalarGroup(Class<?> clazz) {
		return clazz.isAnnotationPresent(MIBScalarGroup.class);
	}

	public static boolean isTableEntry(Class<?> clazz) {
		return clazz.isAnnotationPresent(MIBTableEntry.class);
	}

	public static boolean isObjectGroup(Class<?> clazz) {
		return clazz.isAnnotationPresent(MIBObjectGroup.class);
	}

	public static boolean isNotification(Class<?> clazz) {
		return clazz.isAnnotationPresent(MIBNotification.class);
	}
	
    static String capitalizeFirstLetter( String word )
    {
        String result = "";
        if ( null != word && 0 < word.length() )
        {
            result = word.substring( 0, 1 ).toUpperCase();
            if ( 1 < word.length() )
            {
                result += word.substring( 1 );
            }
        }
        return result;
    }
    
	public static String indexValue(Object obj, Class<?> clazz) {
		MIBTableEntry tableEntry = clazz.getAnnotation(MIBTableEntry.class);
		if(tableEntry != null) {
			StringBuilder buffer = new StringBuilder();
			MIBTableIndex[] indexes = tableEntry.indexes();
			for(MIBTableIndex index : indexes) {
				try {
//					Field field = clazz.getDeclaredField(index.name());
//					if(field != null) { // might be an external index
//						if(!field.isAccessible()) {
//							field.setAccessible(true);
//						}
//						Object value = field.get(obj);
//						buffer.append(".").append(value);
//					}
					
					Method getter = clazz.getDeclaredMethod("get" + capitalizeFirstLetter(index.name()));
					if(getter != null) { // might be an external index
						Object value = getter.invoke(obj);
						buffer.append(".").append(value);
					}
					
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
			return buffer.substring(1);
		}
		return "0";
	}

	public static String toString(Object obj) {
		return toString(obj, new StringBuilder());
	}

	public static String toString(Object obj, StringBuilder indent) {
		if(obj == null) {
			return "";
		}
		if(obj instanceof ManagedObjectProxy) {
			ManagedObjectInitializer initializer = ManagedObjectProxy.class.cast(obj).getInitializer();
			return toString(initializer.getTarget(), indent);
		}
		Class<? extends Object> clazz = obj.getClass();
		if(Proxy.isProxyClass(clazz)) {
			clazz = clazz.getInterfaces()[0];
		}
		MIBScalarGroup scalarGroup = clazz.getAnnotation(MIBScalarGroup.class);
		MIBTableEntry tableEntry = clazz.getAnnotation(MIBTableEntry.class);
		MIBObjectGroup objectrGroup = clazz.getAnnotation(MIBObjectGroup.class);
		StringBuilder buffer = new StringBuilder();
		if(scalarGroup != null || tableEntry != null || objectrGroup != null) {
			buffer.append(clazz.getSimpleName()).append(" [\n");
			String index = indexValue(obj, clazz);
			Field[] fields = clazz.getDeclaredFields();
			// sort fields by OID ???
			for(Field field : fields) {
				MIBVariable variable = field.getAnnotation(MIBVariable.class);
				MIBTable table = field.getAnnotation(MIBTable.class);
				MIBSubtree subtree = field.getAnnotation(MIBSubtree.class);
				if(variable != null || subtree != null || table != null) {
					Object value = null;
					try {
						if(!field.isAccessible()) {
							field.setAccessible(true);
						}
						value = field.get(obj);
					}
					catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(variable != null) {
						buffer.append(indent).append("\t").append(field.getName()).append(".").append(index).append(" = ").append(value != null ? value.toString() : "").append("\n");
					}
					else if(subtree != null) {
						buffer.append(indent).append("\t").append(field.getName()).append(" = ").append(value != null ? toString(value, indent.append("\t")) : "").append("\n");
						indent.deleteCharAt(0);
					}
					else if(table != null) {
						buffer.append(indent).append("\t").append(field.getName()).append(" = {\n");
						indent.append("\t");
						if(value instanceof Collection) {
							for(Object item : ((Collection<?>)value)) {
								indent.append("\t");
								buffer.append(indent).append(toString(item, indent).replaceFirst("]\n", "], \n"));
								indent.deleteCharAt(0);
							}
						}
						else if(value instanceof Map) {
							for(Object item : ((Map<?,?>)value).values()) {
								buffer.append(indent).append(toString(item, indent)).append(", ");
							}
						}
						indent.deleteCharAt(0);
						buffer.append(indent).append("\t").append("}\n");
					}
				}
			}
			
			Method[] methods = clazz.getDeclaredMethods();
			for(Method method : methods) {
				MIBVariable variable = method.getAnnotation(MIBVariable.class);
				if(variable != null) {
					Object value = null;
					try {
						if(!method.isAccessible()) {
							method.setAccessible(true);
						}
						value = method.invoke(obj);
					}
					catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String methodName = method.getName();
					if(methodName.startsWith("get"))
						methodName = methodName.substring(3);
					else if(methodName.startsWith("is"))
						methodName = methodName.substring(2);
					methodName = Character.toLowerCase(methodName.charAt(0)) + methodName.substring(1);
					buffer.append(methodName).append(".").append(index).append(" = ").append(value != null ? value.toString() : "").append("\n");
				}
			}
			
			buffer.append(indent).append("]\n");
		}
		return buffer.toString();
	}

}
