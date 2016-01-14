package snmp.obj.integrations.adventnet.converter;



import snmp.obj.mib.Syntax;
import snmp.obj.util.converter.SNMPTypeConverter;
import snmp.obj.util.converter.SNMPTypeConverterRegistry;

import com.adventnet.snmp.snmp2.SnmpVar;

public final class RegisteredConverters {

	static {
		SNMPTypeConverterRegistry.DEFAULT_REGISTRY.registerConverterClass(Syntax.INTEGER, Enum.class, SnmpIntToEnum.class);
		SNMPTypeConverterRegistry.DEFAULT_REGISTRY.registerConverterClass(Syntax.Integer32, Enum.class, SnmpIntToEnum.class);
		SNMPTypeConverterRegistry.DEFAULT_REGISTRY.registerConverterClass(Syntax.TruthValue, Enum.class, SnmpIntToEnum.class);

		SNMPTypeConverterRegistry.DEFAULT_REGISTRY.registerConverter(Syntax.INTEGER, Integer.class, new SnmpIntToInteger());
		SNMPTypeConverterRegistry.DEFAULT_REGISTRY.registerConverter(Syntax.Integer32, Integer.class, new SnmpIntToInteger());
		SNMPTypeConverterRegistry.DEFAULT_REGISTRY.registerConverter(Syntax.Counter, Integer.class, new SnmpUnsignedIntToInteger());
		SNMPTypeConverterRegistry.DEFAULT_REGISTRY.registerConverter(Syntax.Counter32, Integer.class, new SnmpUnsignedIntToInteger());
		SNMPTypeConverterRegistry.DEFAULT_REGISTRY.registerConverter(Syntax.Unsigned32, Long.class, new SnmpUnsignedIntToLong());
		SNMPTypeConverterRegistry.DEFAULT_REGISTRY.registerConverter(Syntax.Gauge, Integer.class, new SnmpUnsignedIntToInteger());
		SNMPTypeConverterRegistry.DEFAULT_REGISTRY.registerConverter(Syntax.Gauge32, Integer.class, new SnmpUnsignedIntToInteger());
		SNMPTypeConverterRegistry.DEFAULT_REGISTRY.registerConverter(Syntax.Gauge32, Long.class, new SnmpUnsignedIntToLong());
		SNMPTypeConverterRegistry.DEFAULT_REGISTRY.registerConverter(Syntax.TimeTicks, Integer.class, new SnmpUnsignedIntToInteger());
		SNMPTypeConverterRegistry.DEFAULT_REGISTRY.registerConverter(Syntax.OCTET_STRING, String.class, new SnmpStringToString());
		SNMPTypeConverterRegistry.DEFAULT_REGISTRY.registerConverter(Syntax.AutonomousType, String.class, new SnmpOIDToString());
		SNMPTypeConverterRegistry.DEFAULT_REGISTRY.registerConverter(Syntax.DisplayString, String.class, new SnmpStringToString());
		SNMPTypeConverterRegistry.DEFAULT_REGISTRY.registerConverter(Syntax.OBJECT_IDENTIFIER, String.class, new SnmpOIDToString());
		SNMPTypeConverterRegistry.DEFAULT_REGISTRY.registerConverter(Syntax.IpAddress, String.class, new SnmpIpAddressToString());
		SNMPTypeConverterRegistry.DEFAULT_REGISTRY.registerConverter(Syntax.PhysAddress, String.class, new SnmpStringToString());
		SNMPTypeConverterRegistry.DEFAULT_REGISTRY.registerConverter(Syntax.DateAndTime, String.class, new SnmpStringToString());
		SNMPTypeConverterRegistry.DEFAULT_REGISTRY.registerConverter(Syntax.ProductID, String.class, new SnmpOIDToString());
		SNMPTypeConverterRegistry.DEFAULT_REGISTRY.registerConverter(Syntax.KBytes, Integer.class, new SnmpIntToInteger());
		SNMPTypeConverterRegistry.DEFAULT_REGISTRY.registerConverter(Syntax.InternationalDisplayString, String.class, new SnmpStringToString());
				
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

	public static <T extends SnmpVar> SNMPTypeConverter<?,T> getConverter(Syntax syntax, Class<?> type) {
		SNMPTypeConverter<?,T> converter = (SNMPTypeConverter<?, T>) SNMPTypeConverterRegistry.DEFAULT_REGISTRY.findConverter(syntax, type);
//		if(converter == null) {
//			converter = new AnyToString();
//		}
		return converter;
	}

}
