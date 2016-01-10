package snmp.obj.integrations.snmp4j.converter;

import org.snmp4j.smi.UnsignedInteger32;

import snmp.obj.util.converter.SNMPTypeConversionException;
import snmp.obj.util.converter.SNMPTypeConverter;

public class Unsigned32ToInteger implements SNMPTypeConverter<Integer, UnsignedInteger32> {

	@Override
	public UnsignedInteger32 toVar(Integer value)
			throws SNMPTypeConversionException {
		return new UnsignedInteger32(value);
	}

	@Override
	public Integer fromVar(UnsignedInteger32 var)
			throws SNMPTypeConversionException {
		return var.toInt();
	}

}
