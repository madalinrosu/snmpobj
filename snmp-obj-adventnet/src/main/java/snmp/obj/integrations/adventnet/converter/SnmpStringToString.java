package snmp.obj.integrations.adventnet.converter;

import snmp.obj.util.converter.SNMPTypeConversionException;
import snmp.obj.util.converter.SNMPTypeConverter;

import com.adventnet.snmp.snmp2.SnmpString;

public class SnmpStringToString implements SNMPTypeConverter<String, SnmpString> {

	@Override
	public SnmpString toVar(String value)
			throws SNMPTypeConversionException {
		return new SnmpString(value);
	}

	@Override
	public String fromVar(SnmpString var)
			throws SNMPTypeConversionException {
		return var.toString();
	}


}
