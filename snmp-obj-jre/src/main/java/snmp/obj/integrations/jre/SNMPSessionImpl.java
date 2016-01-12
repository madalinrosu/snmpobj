package snmp.obj.integrations.jre;

import java.io.Serializable;
import java.util.List;

import snmp.obj.SNMPSessionSettings;
import snmp.obj.SNMPSessionSupport;
import snmp.obj.config.Configuration;

@SuppressWarnings("serial")
public class SNMPSessionImpl extends SNMPSessionSupport {

	protected SNMPSessionImpl(Configuration configuration, SNMPSessionSettings settings) {
		super(configuration, settings);
		// TODO Auto-generated constructor stub
	}

	@Override
	public <T extends Serializable> T getScalars(Class<T> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends Serializable> T getTableRow(Class<T> clazz, String index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends Serializable> List<T> getTable(Class<T> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends Serializable> T getGroup(Class<T> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

}
