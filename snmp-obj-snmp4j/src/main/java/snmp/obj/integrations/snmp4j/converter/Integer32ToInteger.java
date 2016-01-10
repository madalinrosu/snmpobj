package snmp.obj.integrations.snmp4j.converter;

import org.snmp4j.smi.Integer32;

import snmp.obj.util.converter.SNMPTypeConverter;

public class Integer32ToInteger implements SNMPTypeConverter<Integer, Integer32> {

	@Override
	public Integer32 toVar(Integer value) {
		return new Integer32(value);
	}

	@Override
	public Integer fromVar(Integer32 var) {
		return var.getValue();
	}

	
}
