package snmp.obj.config;


public interface PropertyAccessor {
	Object getValue(Object obj);
	void setValue(Object value, Object obj);
}
