package snmp.obj.config;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import snmp.obj.mib.annotations.MIBSubtree;
import snmp.obj.mib.annotations.MIBTable;
import snmp.obj.mib.annotations.MIBVariable;

/**
 * Immutable class to handle metadata for a SNMP managed object property
 * @author madalin
 *
 */
public final class Property implements Serializable {

	private static final long serialVersionUID = -6223570148627030983L;

	private PropertyType type;
	private Class<?> clazz;
	
	private String oid;
	private String name;
	private String syntax; // maybe use Enum<?> instead ?
	private boolean readable; // maybe just a flag to mark as read only instead of both rd/rw ? 
	private boolean writeable;

	private transient PropertyAccessor accessor;

	public Property(PropertyType type, Class<?> clazz, MIBVariable annotation) {
		this.type = type;
		this.clazz = clazz;
		
		this.oid = annotation.oid();
		this.name = annotation.name();
		this.syntax = annotation.syntax();
		this.readable = annotation.readable();
		this.writeable = annotation.writeable();
	}

	public Property(PropertyType type, Class<?> clazz, MIBTable annotation) {
		this.type = type;
		this.clazz = clazz;
		
		this.oid = annotation.oid();
		this.name = annotation.name();
		this.readable = annotation.readonly();
		this.writeable = !annotation.readonly();
	}

	public Property(PropertyType type, Class<?> clazz, MIBSubtree annotation) {
		this.type = type;
		this.clazz = clazz;
		
		this.oid = annotation.oid();
		this.name = annotation.name();
		this.readable = annotation.readonly();
		this.writeable = !annotation.readonly();
	}

	
	public PropertyType getType() {
		return type;
	}

	public Class<?> getClazz() {
		return clazz;
	}
	
	public String getOid() {
		return oid;
	}

	public String getName() {
		return name;
	}

	public String getSyntax() {
		return syntax;
	}

	public boolean isReadable() {
		return readable;
	}

	public boolean isWriteable() {
		return writeable;
	}

	public boolean isNotAccessible() {
		return !readable && !writeable;
	}

	public boolean isReadOnly() {
		return readable && !writeable;
	}

	public boolean isWriteOnly() {
		return !readable && writeable;
	}
	
	public boolean isReadWrite() {
		return readable && writeable;
	}

	public boolean isEnumerated() {
		return Enum.class.isAssignableFrom(clazz);
	}

	public List<Enum<?>> getEnumValues() {
		try {
			Method method = clazz.getDeclaredMethod("values");
			Enum<?>[] values = (Enum<?>[]) method.invoke(clazz);
			return Arrays.asList(values);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}
	
	public boolean isScalar() {
		return PropertyType.Scalar.equals(type);
	}
	
	public boolean isTableColumn() {
		return PropertyType.Column.equals(type);
	}
	
	public boolean isTableIndex() {
		return PropertyType.Index.equals(type);
	}
	
	public boolean isTable() {
		return PropertyType.Table.equals(type);
	}

	public boolean isTableRow() {
		return false;
	}

	public boolean isSubtree() {
		return PropertyType.Subtree.equals(type);
	}

	public PropertyAccessor getAccessor() {
		return accessor;
	}

	public void setAccessor(PropertyAccessor accessor) {
		this.accessor = accessor;
	}

}
