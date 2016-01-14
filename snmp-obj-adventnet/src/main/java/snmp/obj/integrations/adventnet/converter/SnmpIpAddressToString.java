package snmp.obj.integrations.adventnet.converter;

import snmp.obj.util.converter.SNMPTypeConversionException;
import snmp.obj.util.converter.SNMPTypeConverter;

import com.adventnet.snmp.snmp2.SnmpIpAddress;

public class SnmpIpAddressToString implements SNMPTypeConverter<String, SnmpIpAddress> {

	@Override
	public SnmpIpAddress toVar(String value)
			throws SNMPTypeConversionException {
		return new SnmpIpAddress(value);
	}
	
	@Override
	public String fromVar(SnmpIpAddress var)
			throws SNMPTypeConversionException {
		return var.toHostName();//toString();
	}

}
