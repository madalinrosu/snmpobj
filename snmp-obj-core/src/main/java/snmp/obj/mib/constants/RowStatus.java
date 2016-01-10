package snmp.obj.mib.constants;

public enum RowStatus {

	// the following 3 values are states:
	// these values may be read or written
	Active(1),
	NotInService(2),
	// this value may be read, but not written
	NotReady(3),

	// the following 3 values are actions: 
	// these values may be written, but are never read
	CreateAndGo(4),
	CreateAndWait(5),
	Destroy(6);

	private final int value;
	private RowStatus(int value) {
		this.value = value;
	}

	public int value() {
		return this.value;
	}

}
