package snmp.obj.tools.mibc;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.adventnet.snmp.mibs.MibException;
import com.adventnet.snmp.mibs.MibModule;
import com.adventnet.snmp.mibs.MibOperations;

public class MIBCompiler {
	private static String basePackage = "snmp.obj.mib.enterprise";
	private static boolean generateInterfaces = false;
	private static boolean useFields = false;
	private static boolean flatHierarchy = false;
	private static boolean nestedEnums = false;
	
	private MibOperations op;
	
    public MIBCompiler() {
        MibOperations op = new MibOperations();
        op.setImportsParsingLevel((byte) 0);
    }
    
	public void compile(String filename) {
		MibModule mibModule = loadMib(filename);
		
		MetaModule metaModule = parseMib(mibModule);
		
		generateCode(metaModule);
	}
	
	private MibModule loadMib(String filename) {
		try {
			return op.loadMibModule(filename);
		} 
		catch (MibException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private MetaModule parseMib(MibModule mibModule) {
		MetaModule metaModule = new MetaModule(mibModule);
		return null;
	}
	
	private void generateCode(MetaModule metaModule) {
		
	}
}
