package snmp.obj.config;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;

/**
 * Immutable class to handle metadata for a SNMP managed object /notification class
 * @author madalin
 *
 */
public final class ManagedObjectClass implements Serializable {

	private static final long serialVersionUID = -566601249761058577L;

	private Stereotype stereotype;
	private Class<?> type;
	private Map<String, Property> properties;

	private String oid;
	private String name;

	public enum Stereotype {
		ScalarGroup, TableRow, Table, Subtree, Notification, Trap;
	}

	public ManagedObjectClass(String oid, String name, Stereotype stereotype, Class<?> type, Map<String, Property> properties) {
		this.oid = oid;
		this.name = defaultName(name, type);
		this.stereotype = stereotype;
		this.type = type;
		this.properties =  Collections.unmodifiableMap(properties);
	}

	private String defaultName(String name, Class<?> clazz) {
		if(name == null || name.length() == 0) {
			String className = clazz.getSimpleName();
			return className.substring(0, 1).toLowerCase() + className.substring(1);
		}
		return name;
	}
	
	public Stereotype getStereotype() {
		return stereotype;
	}
	
	public Class<?> getType() {
		return type;
	}

	public Map<String, Property> getProperties() {
		return properties;
	}

	public Property getProperty(String oid) {
		return properties.get(oid);
	}

	public String getOid() {
		return oid;
	}

	public String getName() {
		return name;
	}

	public boolean isScalarGroup() {
		return ManagedObjectType.ScalarGroup.equals(type);
	}
	
	public boolean isTableRow() {
		return ManagedObjectType.TableRow.equals(type);
	}
	
	public boolean isTable() {
		return ManagedObjectType.Table.equals(type);
	}

	public boolean isSubtree() {
		return ManagedObjectType.Subtree.equals(type);
	}

}

