package snmp.obj;

import java.io.Serializable;
import java.util.Map;

import snmp.obj.config.Configuration;
import snmp.obj.mib.Syntax;
import snmp.obj.mib.annotations.MIBScalarGroup;
import snmp.obj.mib.annotations.MIBTableEntry;
import snmp.obj.mib.annotations.MIBTableIndex;
import snmp.obj.mib.annotations.MIBVariable;
import snmp.obj.proxy.ManagedObjectProxyFactory;
import snmp.obj.proxy.TableProxyFactory;

public abstract class SNMPSessionSupport implements SNMPSession {

	protected final Configuration configuration;
	protected final SNMPSessionSettings settings;
	private ManagedObjectProxyFactory proxyFactory;
	private TableProxyFactory tableProxyFactory;
	
	protected SNMPSessionSupport(Configuration configuration, SNMPSessionSettings settings) {
		this.configuration = configuration;
		this.settings = settings;
	}

	@Override
	public SNMPSessionSettings getSettings() {
		return settings;
	}

	@Override
	public <T extends Serializable> Object getMetadata(Class<T> clazz) {
		return configuration.getMetadata(clazz);
	}

	@Override
	public <T extends Serializable> Object getManagedObject(Class<T> clazz, Object...args) {
		if(configuration.isScalarGroup(clazz)) {
			return getScalars(clazz);
		}
		else if(configuration.isObjectGroup(clazz)) {
			return getGroup(clazz);
		}
		else if(configuration.isTableEntry(clazz)) {
			if(args == null || args.length == 0) {
				return getTable(clazz);
			}
			else {
				StringBuilder index = new StringBuilder(args[0].toString());
				for(int i = 1; i < args.length; i++) {
					index.append(args[i]);
				}
				return getTableRow(clazz, index.toString());
			}
		}
		return null;
	}

	public ManagedObjectProxyFactory getProxyFactory() {
		return proxyFactory;
	}

	public SNMPSessionSupport setProxyFactory(ManagedObjectProxyFactory proxyFactory) {
		this.proxyFactory = proxyFactory;
		return this;
	}

	public TableProxyFactory getTableProxyFactory() {
		return tableProxyFactory;
	}

	public SNMPSessionSupport setTableProxyFactory(TableProxyFactory tableProxyFactory) {
		this.tableProxyFactory = tableProxyFactory;
		return this;
	}

	public boolean supportsLazyInitialization() {
		return (proxyFactory != null);
	}
	
	public boolean supportsGetBulk() {
		return (SNMPVersion.v1.compareTo(settings.getVersion()) == -1);
	}
	
	protected <T extends Serializable> int getIndexLength(Class<T> clazz, Map<String, MIBVariable> variables) {
		// FIXME : implement multiple indexes and multiple subid's indexes
		int indexLength = 0;
		if(configuration.isScalarGroup(clazz) || configuration.isObjectGroup(clazz)) {
			indexLength = 1;
		}
		else if(configuration.isTableEntry(clazz)) {
			MIBTableEntry tableEntry = clazz.getAnnotation(MIBTableEntry.class);
			MIBTableIndex[] indexes = tableEntry.indexes();
			for(MIBTableIndex index : indexes) {
				if(MIBTableIndex.SELF.class.equals(index.tableEntry())) {
					indexLength += getIndexLength(index, variables);
				}
				else {
					Class<? extends Serializable> clazz2 = index.tableEntry();
					MIBTableEntry tableEntry2 = clazz2.getAnnotation(MIBTableEntry.class);
					indexLength += getIndexLength(index, configuration.getVariables(tableEntry2));
				}
			}
		}

		return indexLength;
	}
	
	private int getIndexLength(MIBTableIndex index, Map<String, MIBVariable> variables) {
		for(MIBVariable var : variables.values()) {
			if(index.name().equals(var.name())) {
				switch(Syntax.valueOf(var.syntax())) {
					case IpAddress:
						return 4;
					case PhysAddress:
					case MacAddress:
						return 6;
					default:
						return 1;
				}
			}
		}
		return 1;
	}

}
