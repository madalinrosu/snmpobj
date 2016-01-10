package snmp.obj.integrations.snmp4j.converter;

import org.snmp4j.smi.IpAddress;

import snmp.obj.util.converter.SNMPTypeConversionException;
import snmp.obj.util.converter.SNMPTypeConverter;

public class IPAddressToString implements SNMPTypeConverter<String, IpAddress> {

	@Override
	public IpAddress toVar(String value)
			throws SNMPTypeConversionException {
		return new IpAddress(value);
	}
	
	@Override
	public String fromVar(IpAddress var)
			throws SNMPTypeConversionException {
		return var.toString();
	}

}
