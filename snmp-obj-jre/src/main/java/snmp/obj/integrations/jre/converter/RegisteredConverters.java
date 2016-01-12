package snmp.obj.integrations.jre.converter;

import org.snmp4j.smi.Variable;

import snmp.obj.mib.Syntax;
import snmp.obj.util.converter.SNMPTypeConverter;
import snmp.obj.util.converter.SNMPTypeConverterRegistry;

public final class RegisteredConverters {

	static {

	}

	private RegisteredConverters() {
	}

	public static <T extends Variable> SNMPTypeConverter<?,T> getConverter(Syntax syntax, Class<?> type) {
		@SuppressWarnings("unchecked")
		SNMPTypeConverter<?,T> converter = (SNMPTypeConverter<?, T>) SNMPTypeConverterRegistry.DEFAULT_REGISTRY.findConverter(syntax, type);

		return converter;
	}

}
