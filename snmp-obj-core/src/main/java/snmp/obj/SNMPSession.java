package snmp.obj;

import java.io.Serializable;
import java.util.List;

public interface SNMPSession extends Serializable {

	SNMPSessionSettings getSettings();

	<T extends Serializable> Object getMetadata(Class<T> clazz);
	
	<T extends Serializable> Object getManagedObject(Class<T> clazz, Object...args);

	<T extends Serializable> T getScalars(Class<T> clazz);
	<T extends Serializable> T getTableRow(Class<T> clazz, String index);
	<T extends Serializable> List<T> getTable(Class<T> clazz);
	<T extends Serializable> T getGroup(Class<T> clazz);
}
