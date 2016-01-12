package snmp.obj.integrations.snmp4j.converter;

import org.snmp4j.smi.Variable;

import snmp.obj.mib.Syntax;
import snmp.obj.util.converter.SNMPTypeConverter;
import snmp.obj.util.converter.SNMPTypeConverterRegistry;

public final class RegisteredConverters {

	static {
		SNMPTypeConverterRegistry.DEFAULT_REGISTRY.registerConverterClass(Syntax.INTEGER, Enum.class, Integer32ToEnum.class);
		SNMPTypeConverterRegistry.DEFAULT_REGISTRY.registerConverterClass(Syntax.Integer32, Enum.class, Integer32ToEnum.class);
		SNMPTypeConverterRegistry.DEFAULT_REGISTRY.registerConverterClass(Syntax.TruthValue, Enum.class, Integer32ToEnum.class);

		SNMPTypeConverterRegistry.DEFAULT_REGISTRY.registerConverter(Syntax.INTEGER, Integer.class, new Integer32ToInteger());
		SNMPTypeConverterRegistry.DEFAULT_REGISTRY.registerConverter(Syntax.Integer32, Integer.class, new Integer32ToInteger());
		SNMPTypeConverterRegistry.DEFAULT_REGISTRY.registerConverter(Syntax.Counter, Integer.class, new Unsigned32ToInteger());
		SNMPTypeConverterRegistry.DEFAULT_REGISTRY.registerConverter(Syntax.Counter32, Integer.class, new Unsigned32ToInteger());
		SNMPTypeConverterRegistry.DEFAULT_REGISTRY.registerConverter(Syntax.Unsigned32, Long.class, new Unsigned32ToLong());
		SNMPTypeConverterRegistry.DEFAULT_REGISTRY.registerConverter(Syntax.Gauge, Integer.class, new Unsigned32ToInteger());
		SNMPTypeConverterRegistry.DEFAULT_REGISTRY.registerConverter(Syntax.Gauge32, Integer.class, new Unsigned32ToInteger());
		SNMPTypeConverterRegistry.DEFAULT_REGISTRY.registerConverter(Syntax.Gauge32, Long.class, new Unsigned32ToLong());
		SNMPTypeConverterRegistry.DEFAULT_REGISTRY.registerConverter(Syntax.TimeTicks, Integer.class, new Unsigned32ToInteger());
		SNMPTypeConverterRegistry.DEFAULT_REGISTRY.registerConverter(Syntax.OCTET_STRING, String.class, new OctetStringToString());
		SNMPTypeConverterRegistry.DEFAULT_REGISTRY.registerConverter(Syntax.AutonomousType, String.class, new OIDToString());
		SNMPTypeConverterRegistry.DEFAULT_REGISTRY.registerConverter(Syntax.DisplayString, String.class, new DisplayStringToString());
		SNMPTypeConverterRegistry.DEFAULT_REGISTRY.registerConverter(Syntax.OBJECT_IDENTIFIER, String.class, new OIDToString());
		SNMPTypeConverterRegistry.DEFAULT_REGISTRY.registerConverter(Syntax.IpAddress, String.class, new IPAddressToString());
		SNMPTypeConverterRegistry.DEFAULT_REGISTRY.registerConverter(Syntax.PhysAddress, String.class, new OctetStringToString());
		SNMPTypeConverterRegistry.DEFAULT_REGISTRY.registerConverter(Syntax.DateAndTime, String.class, new OctetStringToString());
		SNMPTypeConverterRegistry.DEFAULT_REGISTRY.registerConverter(Syntax.ProductID, String.class, new OIDToString());
		SNMPTypeConverterRegistry.DEFAULT_REGISTRY.registerConverter(Syntax.KBytes, Integer.class, new Integer32ToInteger());
		SNMPTypeConverterRegistry.DEFAULT_REGISTRY.registerConverter(Syntax.InternationalDisplayString, String.class, new DisplayStringToString());
				
/*		
		Counter, // wraps
		Counter32, // wraps
		Gauge, // doesn't wrap
		Gauge32, // doesn't wrap
		Unsigned32, // an unsigned 32-bit quantity, indistinguishable from Gauge32
*/
		
	}

	private RegisteredConverters() {
	}

	public static <T extends Variable> SNMPTypeConverter<?,T> getConverter(Syntax syntax, Class<?> type) {
		@SuppressWarnings("unchecked")
		SNMPTypeConverter<?,T> converter = (SNMPTypeConverter<?, T>) SNMPTypeConverterRegistry.DEFAULT_REGISTRY.findConverter(syntax, type);
//		if(converter == null) {
//			converter = new AnyToString();
//		}
		return converter;
	}

}
