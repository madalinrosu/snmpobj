package snmp.obj.integrations.snmp4j.converter;

import org.snmp4j.smi.Variable;

import snmp.obj.util.converter.SNMPTypeConversionException;
import snmp.obj.util.converter.SNMPTypeConverter;

public class AnyToString implements SNMPTypeConverter<String, Variable> {

	@Override
	public Variable toVar(String value)
			throws SNMPTypeConversionException {
		return null;
	}

	@Override
	public String fromVar(Variable var)
			throws SNMPTypeConversionException {
		return var.toString();
	}


}
