package snmp.obj.mib.constants;

public enum StorageType {

	Other(1),       // ???
	Volatile(2),    // e.g., in RAM
	NonVolatile(3), // e.g., in NVRAM
	Permanent(4),   // e.g., partially in ROM
	ReadOnly(5);    // e.g., completely in ROM

	private final int value;
	private StorageType(int value) {
		this.value = value;
	}

	public int value() {
		return this.value;
	}
}
