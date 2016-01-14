package snmp.obj.integrations.adventnet.converter;

import snmp.obj.util.converter.SNMPTypeConversionException;
import snmp.obj.util.converter.SNMPTypeConverter;

import com.adventnet.snmp.snmp2.SnmpInt;

public class SnmpIntToInteger implements SNMPTypeConverter<Integer, SnmpInt> {


	@Override
	public SnmpInt toVar(Integer value) throws SNMPTypeConversionException {
		return new SnmpInt(value);
	}

	@Override
	public Integer fromVar(SnmpInt var) throws SNMPTypeConversionException {
		return var.intValue();
	}

	
}
