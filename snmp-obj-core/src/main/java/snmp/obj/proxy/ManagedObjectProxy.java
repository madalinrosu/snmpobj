package snmp.obj.proxy;

import java.io.Serializable;

public interface ManagedObjectProxy extends Serializable {

	ManagedObjectInitializer getInitializer();
}
