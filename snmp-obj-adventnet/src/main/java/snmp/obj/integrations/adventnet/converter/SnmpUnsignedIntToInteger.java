package snmp.obj.integrations.adventnet.converter;

import snmp.obj.util.converter.SNMPTypeConversionException;
import snmp.obj.util.converter.SNMPTypeConverter;

import com.adventnet.snmp.snmp2.SnmpUnsignedInt;

public class SnmpUnsignedIntToInteger implements SNMPTypeConverter<Integer, SnmpUnsignedInt> {

	@Override
	public SnmpUnsignedInt toVar(Integer value)
			throws SNMPTypeConversionException {
		return new SnmpUnsignedInt(value);
	}

	@Override
	public Integer fromVar(SnmpUnsignedInt var)
			throws SNMPTypeConversionException {
		return (int)var.longValue();
	}

}
