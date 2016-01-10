package snmp.obj.integrations.snmp4j.converter;

import org.snmp4j.smi.Integer32;

import snmp.obj.util.converter.SNMPEnumeratedConverter;

public class Integer32ToEnum extends SNMPEnumeratedConverter<Integer32> {

	public Integer32ToEnum(Class<? extends Enum<?>> enumClass) {
		super(enumClass);
	}

	@Override
	protected Integer32 fromInt(int value) {
		return new Integer32(value);
	}

	@Override
	protected int toInt(Integer32 var) {
		return var.toInt();
	}

}
