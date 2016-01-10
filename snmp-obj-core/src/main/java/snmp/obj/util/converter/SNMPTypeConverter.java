package snmp.obj.util.converter;

public interface SNMPTypeConverter<T, V> {

	V toVar(T value) throws SNMPTypeConversionException;
	T fromVar(V var) throws SNMPTypeConversionException;
}
