package snmp.obj.integrations.adventnet.converter;

import snmp.obj.util.converter.SNMPTypeConversionException;
import snmp.obj.util.converter.SNMPTypeConverter;

import com.adventnet.snmp.snmp2.SnmpUnsignedInt;

public class SnmpUnsignedIntToLong implements SNMPTypeConverter<Long, SnmpUnsignedInt> {

	@Override
	public SnmpUnsignedInt toVar(Long value)
			throws SNMPTypeConversionException {
		return new SnmpUnsignedInt(value);
	}

	@Override
	public Long fromVar(SnmpUnsignedInt var)
			throws SNMPTypeConversionException {
		return var.longValue();
	}

}

