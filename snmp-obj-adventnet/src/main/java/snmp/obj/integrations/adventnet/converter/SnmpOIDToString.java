package snmp.obj.integrations.adventnet.converter;

import snmp.obj.util.converter.SNMPTypeConversionException;
import snmp.obj.util.converter.SNMPTypeConverter;

import com.adventnet.snmp.snmp2.SnmpOID;

public class SnmpOIDToString implements SNMPTypeConverter<String, SnmpOID>{

	@Override
	public SnmpOID toVar(String value) throws SNMPTypeConversionException {
		return new SnmpOID(value);
	}

	@Override
	public String fromVar(SnmpOID var) throws SNMPTypeConversionException {
		return var.toString();
	}

}
