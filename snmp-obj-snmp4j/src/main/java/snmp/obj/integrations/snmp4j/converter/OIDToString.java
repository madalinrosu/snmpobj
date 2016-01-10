package snmp.obj.integrations.snmp4j.converter;

import org.snmp4j.smi.OID;

import snmp.obj.util.converter.SNMPTypeConversionException;
import snmp.obj.util.converter.SNMPTypeConverter;

public class OIDToString implements SNMPTypeConverter<String, OID>{

	@Override
	public OID toVar(String value) throws SNMPTypeConversionException {
		return new OID(value);
	}

	@Override
	public String fromVar(OID var) throws SNMPTypeConversionException {
		return var.toString();
	}

}
