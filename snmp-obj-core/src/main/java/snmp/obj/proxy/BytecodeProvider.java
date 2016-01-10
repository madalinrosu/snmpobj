package snmp.obj.proxy;

public enum BytecodeProvider {

	javassist("javassist.util.proxy.ProxyObject"),
	cglib("net.sf.cglib.proxy.Enhancer");
	
	private String checkClass;
	
	private BytecodeProvider(String checkClass) {
		this.checkClass = checkClass;
	}
	
	public boolean present() {
		try {
			Class.forName(checkClass);
			return true;
		}
		catch(Throwable e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static BytecodeProvider defaultValue() {
		BytecodeProvider defaultBytecodeProvider = null;
		if(javassist.present()) {
			defaultBytecodeProvider = javassist;
		}
		else if(cglib.present()) {
			defaultBytecodeProvider = cglib;
		}
		return defaultBytecodeProvider;
	}
}
