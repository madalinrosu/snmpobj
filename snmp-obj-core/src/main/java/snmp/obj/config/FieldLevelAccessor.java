package snmp.obj.config;

import java.lang.reflect.Field;

final class FieldLevelAccessor extends PropertyAccessorSupport {

	Field field;
	FieldLevelAccessor(Field field) {
		this.field = field;
	}
	
	@Override
	public Object getValue(Object obj){
		try {
			setAccessible(field);
			return field.get(obj);
		}
		catch(Exception e) {
			throwPropertyAccessException("getValue", field , e);
		}
		return null;
	}

	@Override
	public void setValue(Object value, Object obj){
		try {
			setAccessible(field);
			field.set(obj, value);
		}
		catch(Exception e) {
			throwPropertyAccessException("setValue", field , e);
		}
	}
}