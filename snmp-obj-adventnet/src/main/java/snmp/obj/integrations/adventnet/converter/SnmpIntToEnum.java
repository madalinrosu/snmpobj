package snmp.obj.integrations.adventnet.converter;

import snmp.obj.util.converter.SNMPEnumeratedConverter;

import com.adventnet.snmp.snmp2.SnmpInt;

public class SnmpIntToEnum extends SNMPEnumeratedConverter<SnmpInt> {

	public SnmpIntToEnum(Class<? extends Enum<?>> enumClass) {
		super(enumClass);
	}

	@Override
	protected SnmpInt fromInt(int value) {
		return new SnmpInt(value);
	}

	@Override
	protected int toInt(SnmpInt value) {
		return value.intValue();
	}

}
