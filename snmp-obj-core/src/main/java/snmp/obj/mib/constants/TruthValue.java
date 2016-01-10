package snmp.obj.mib.constants;


public enum TruthValue {
	// Represents a boolean value
	True(1), 
	False(2);

	private final int value;
	private TruthValue(int value) {
		this.value = value;
	}

	public int value() {
		return this.value;
	}

	public static TruthValue fromValue(int value){
		for(TruthValue constant : values()){
			if(constant.value() == value){
				return constant;
			}
		}
		return null;
	}

}
