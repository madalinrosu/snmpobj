package snmp.obj.integrations.snmp4j.converter;

import org.snmp4j.smi.OctetString;

import snmp.obj.util.converter.SNMPTypeConversionException;
import snmp.obj.util.converter.SNMPTypeConverter;

public class OctetStringToString implements SNMPTypeConverter<String, OctetString> {

	@Override
	public OctetString toVar(String value)
			throws SNMPTypeConversionException {
		return new OctetString(value);
	}

	@Override
	public String fromVar(OctetString var)
			throws SNMPTypeConversionException {
		return var.toString();
	}


}
