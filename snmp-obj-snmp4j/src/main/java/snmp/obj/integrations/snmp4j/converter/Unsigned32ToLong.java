package snmp.obj.integrations.snmp4j.converter;

import org.snmp4j.smi.UnsignedInteger32;

import snmp.obj.util.converter.SNMPTypeConversionException;
import snmp.obj.util.converter.SNMPTypeConverter;

public class Unsigned32ToLong implements SNMPTypeConverter<Long, UnsignedInteger32> {

	@Override
	public UnsignedInteger32 toVar(Long value)
			throws SNMPTypeConversionException {
		return new UnsignedInteger32(value);
	}

	@Override
	public Long fromVar(UnsignedInteger32 var)
			throws SNMPTypeConversionException {
		return var.toLong();
	}

}

