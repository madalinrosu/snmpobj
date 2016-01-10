package snmp.obj.util.converter;

import java.lang.reflect.Method;

public abstract class SNMPEnumeratedConverter<V> implements SNMPTypeConverter<Enum<?>, V> {

	private final Class<? extends Enum<?>> enumClass;
	
	public SNMPEnumeratedConverter(Class<? extends Enum<?>> enumClass) {
		this.enumClass = enumClass;
	}
	
	@Override
	public V toVar(Enum<?> value) throws SNMPTypeConversionException {
		if(!enumClass.isAssignableFrom(value.getClass())) {
			throw new SNMPTypeConversionException("Wrong enum type");
		}
		try {
			Method valueMethod = enumClass.getMethod("value");
			int result = (Integer)valueMethod.invoke(value);
			return fromInt(result);
		}
		catch(Exception e) {
			throw new SNMPTypeConversionException(e);
		}
	}

	@Override
	public Enum<?> fromVar(V var) throws SNMPTypeConversionException {
		try {
			Method fromValueMethod = enumClass.getMethod("fromValue", int.class);
			return (Enum<?>)fromValueMethod.invoke(enumClass, toInt(var));
		}
		catch(Exception e) {
			throw new SNMPTypeConversionException(e);
		}
	}

	protected abstract V fromInt(int value);
	protected abstract int toInt(V value);
	
}
