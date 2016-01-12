package snmp.obj.tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.adventnet.snmp.mibs.LeafSyntax;
import com.adventnet.snmp.mibs.MibModule;
import com.adventnet.snmp.mibs.MibNode;
import com.adventnet.snmp.mibs.MibOperations;
import com.adventnet.snmp.mibs.MibTC;
import com.adventnet.snmp.mibs.MibTrap;
import com.adventnet.snmp.mibs.NotificationType;

public class MIBCompiler {

	private final static String STANDARD_BASE_PKG = "snmp.obj.mib.standard";
	private final static String ENTERPRISE_BASE_PKG = "snmp.obj.mib.enterprise";
	
	private static PrintWriter output;
	
	public static String outputDir;
	
	private static String basePackage = STANDARD_BASE_PKG;
	private static String asn1 = null;
	private static boolean generateInterfaces = true;
	private static boolean annotateFields = true;
//	private static boolean flatHierarchy = false;
	private static boolean nestedEnums = true;
	

	
	private static final Map<String, String> SMI_TYPES_MAP = new HashMap<>();
	static {
		SMI_TYPES_MAP.put("INTEGER", "Integer");
		SMI_TYPES_MAP.put("OBJECT IDENTIFIER", "String");
		SMI_TYPES_MAP.put("OCTET STRING", "String");
		SMI_TYPES_MAP.put("Integer32", "Integer");
		SMI_TYPES_MAP.put("IpAddress", "String");
		SMI_TYPES_MAP.put("NetworkAddress", "String");
		SMI_TYPES_MAP.put("MacAddress", "String");
		SMI_TYPES_MAP.put("PhysAddress", "String");
		SMI_TYPES_MAP.put("DisplayString", "String");
		SMI_TYPES_MAP.put("Gauge", "Integer");
		SMI_TYPES_MAP.put("Gauge32", "Integer");
		SMI_TYPES_MAP.put("Unsigned32", "Long");
		SMI_TYPES_MAP.put("Counter", "Integer");
		SMI_TYPES_MAP.put("Counter32", "Integer");
		SMI_TYPES_MAP.put("Counter64", "Long");
		SMI_TYPES_MAP.put("TimeTicks", "Integer");
		SMI_TYPES_MAP.put("TimeStamp", "Integer");
		SMI_TYPES_MAP.put("TestAndIncr", "Integer");
		SMI_TYPES_MAP.put("RowPointer", "String");
		SMI_TYPES_MAP.put("AutonomousType", "String");
		SMI_TYPES_MAP.put("DateAndTime", "String");

		SMI_TYPES_MAP.put("TDomain", "String");
		SMI_TYPES_MAP.put("TAddress", "String");

		
		/**
		 * SNMPv2-TC
		 
		DisplayString,// OCTET STRING (SIZE (0..255)) 
		PhysAddress,// OCTET STRING
		MacAddress,// OCTET STRING (SIZE (6)) 
		TruthValue,// INTEGER { true(1), false(2) } 
		TestAndIncr,// INTEGER (0..2147483647) 
		AutonomousType,// OBJECT IDENTIFIER 
		VariablePointer,// OBJECT IDENTIFIER 
		RowPointer,// OBJECT IDENTIFIER 
		RowStatus,// INTEGER
		TimeStamp,// TimeTicks
		TimeInterval,// INTEGER (0..2147483647)
		DateAndTime,// OCTET STRING (SIZE (8 | 11)), e.g.: "1992-5-26,13:30:15.0,-4:0" 
		StorageType,// INTEGER
		TDomain,// OBJECT IDENTIFIER
		TAddress,// OCTET STRING (SIZE (1..255)) 
		
		Boolean,// INTEGER
		KBytes,// Integer32 
		ProductID,// OBJECT IDENTIFIER 
		InternationalDisplayString;// OCTET STRING 
		*/
//		SYNTAX.put("TenthdB", "TenthdB");
	}

	private static MibOperations op;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
        
        File input = null;
        
        // parse command line arguments
        for(String arg : args) {
        	switch(arg) {
	    		case "-i": generateInterfaces = true; break;
	    		case "-c": generateInterfaces = false; break;
	    		case "-std": basePackage = STANDARD_BASE_PKG; break;
	    		case "-ent": basePackage = ENTERPRISE_BASE_PKG; break;
	    		default:
	    			if(arg.startsWith("-pkg")) {
	    				basePackage = arg.replace("-pkg", "");
	    			}
	    			else if(arg.startsWith("-asn1")) {
	    				asn1 = arg.replace("-asn1", "");
	    			}
	    			else if(arg.startsWith("-f")) {
	    				String path = arg.replace("-f", "");
	    				input = new File(path);
	    			}
	    			break;
        	}
        }
        
        basePackage += generateInterfaces ? ".i" : ".c";
        if(asn1 != null) {
        	basePackage += ".asn1_" + asn1;
        }
        
        op = new MibOperations();
        op.setImportsParsingLevel((byte) 0);
        
        if(input.isFile()) {
    		try {
                MibModule mibModule = op.loadMibModule(input.getPath());
            	createOutputDir(mibModule);
        		parseMib(mibModule);
        	}
        	catch(Throwable t){
        		System.err.println(t.getMessage());
        	}        		
        	
        }
        else if(input.isDirectory()) {
        	for(File mibFile : input.listFiles()) {
        		try {
                    MibModule mibModule = op.loadMibModule(mibFile.getPath());
                	createOutputDir(mibModule);
            		parseMib(mibModule);
            	}
            	catch(Throwable t){
            		System.err.println(t.getMessage());
            	}        		
        	}
        }

//        try {
//        	MibModule mibModule = null;
//        	switch(args.length) {
//        		case 2:
//                	basePackage = args[1];
//        		case 1:
//                	File file = new File(args[0]);
//                	if(file.isDirectory()) {
//    					for(File mibFile : file.listFiles()) {
//    	                	try {
//        	                    mibModule = op.loadMibModule(mibFile.getPath());
//    	            			basePackage = "snmp.obj.mib.enterprise." + mibModule.getName();
//        	                	createOutputDir(mibModule);
//    	                		parseMib(mibModule);
//    	                	}
//    	                	catch(Throwable t){
//    	                		System.err.println(t.getMessage());
//    	                	}
//    					}
//                	}
//                	else {
//	                    mibModule = op.loadMibModule(args[0]);
//            			basePackage = "snmp.obj.mib.enterprise." + 
//            							mibModule.getName().replace("-", "").toLowerCase() +
//            							(generateInterfaces ? ".i" : ".c");
//	                	createOutputDir(mibModule);
//	        			parseMib(mibModule);
//                	}
//        			break;
//        		default:
//        			File[] mibs = new File("../mibs/").listFiles();
//					for(File dir : mibs) {
//        				if(dir.isDirectory()) {
//        					for(File mibFile : dir.listFiles()) {
//        	                	try {
//            	                    mibModule = op.loadMibModule(mibFile.getPath());
//        	            			basePackage = "snmp.obj.mib.enterprise." + mibModule.getName();
//            	                	createOutputDir(mibModule);
//        	                		parseMib(mibModule);
//        	                	}
//        	                	catch(Throwable t){
//        	                		System.err.println(t.getMessage());
//        	                	}
//        					}
//        				}
//        			}
//        			break;
//        		
//        	}
//        }
//        catch(Exception e) {
//            e.printStackTrace();
//        }
	}

	static void createOutputDir(MibModule mibModule) {
	
//		String[] subdirs = basePackage.split("\\.");
		String path = basePackage.replaceAll("\\.", "/") + "/" + mibModule.getName().replace("-", "").toLowerCase();

//		outputDir = "output";
		File file = new File(outputDir + "/" + path);
		if(!file.exists())
			file.mkdirs();

//		for(String subdir : subdirs) {
//			outputDir += "/" + subdir;
//			file = new File(outputDir);
//			if(!file.exists())
//				file.mkdir();
//		}
	}
	
	@SuppressWarnings("unchecked")
	static void parseMib(MibModule mibModule) {
		System.out.println("Parsing ... " + mibModule.getName());
        // resolve textual conventions
		System.out.println("Defined Textual Convensions: ");
		Enumeration<MibTC> definedTCs = mibModule.getDefinedTCs();
        while(definedTCs.hasMoreElements()) {
        	MibTC tc = definedTCs.nextElement();
			System.out.println(String.format("%s %s %s", tc.getName(), tc.getSyntax(), tc.getDisplayHint()));
			if(!SMI_TYPES_MAP.containsKey(tc.getName())) {
				SMI_TYPES_MAP.put(tc.getName(), SMI_TYPES_MAP.get(tc.getSyntax()));
			}
        }
        
        // traps
		System.out.println("Defined Traps: ");
		Enumeration<MibTrap> definedTraps = mibModule.getDefinedTraps();
        while(definedTraps.hasMoreElements()) {
        	MibTrap trap = definedTraps.nextElement();
			System.out.println(String.format("%s", trap.getName()));
			
        }
        
        // notifications
		System.out.println("Defined Notification Types: ");
		Enumeration<NotificationType> definedNotifications = mibModule.getDefinedNotificationTypes();
        while(definedNotifications.hasMoreElements()) {
        	NotificationType notificationType = definedNotifications.nextElement();
			System.out.println(String.format("%s", notificationType.getName()));
			parseNotification(notificationType, mibModule);
        }
        
        // groups
		System.out.println("Defined Object Groups: ");
        Enumeration<?> objectGroups = mibModule.getDefinedObjectGroups();
        while(objectGroups.hasMoreElements()) {
        	Object objectGroup = objectGroups.nextElement();
			System.out.println(String.format("%s", objectGroup));
			
        }
        
		parseNode(mibModule.getRootNode());
	}
	
	static void parseNotification(NotificationType notification, MibModule mibModule) {

		String content = formatClass(notification, mibModule);
		if(content != null) {
			
			try {
				String javaFilename = String.format(FORMAT_FILENAME, capitalizeFirstLetter(notification.getName()));
				String path = outputDir + "/" + basePackage.replaceAll("\\.", "/") + "/" + 
						mibModule.getName().replace("-", "").toLowerCase() + "/" + javaFilename;
				System.out.println("Generate file : " + path);
				output = new PrintWriter(path);
				
				//System.out.println(mibNode.getLabel());
				output.print(content);
				output.flush();
				output.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	static void parseNode(MibNode mibNode) {
		if(!(mibNode.isLeaf() || mibNode.isTableEntry() || mibNode.isTable())) {
			System.out.println(mibNode.getLabel());
		}
		String content = formatClass(mibNode);
		if(content != null) {
			
			try {
				String javaFilename = String.format(FORMAT_FILENAME, capitalizeFirstLetter(mibNode.getLabel()));
				String path = outputDir + "/" + basePackage.replaceAll("\\.", "/") + "/" + 
						mibNode.getModuleName().replace("-", "").toLowerCase() + "/" + javaFilename;
				System.out.println("Generate file : " + path);
				output = new PrintWriter(path);
				
				//System.out.println(mibNode.getLabel());
				output.print(content);
				output.flush();
				output.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
        Vector<MibNode> children = mibNode.getChildList();
        for(MibNode child : children) {
            parseNode(child);
        }
	}
	
    static String capitalizeFirstLetter( String word )
    {
        String result = "";
        if ( null != word && 0 < word.length() )
        {
            result = word.substring( 0, 1 ).toUpperCase();
            if ( 1 < word.length() )
            {
                result += word.substring( 1 );
            }
        }
        return result;
    }

    
    private final static String FORMAT_FILENAME = "%s.java";
    
    
    
    private final static String FORMAT_ANNO_MIB_SCALAR_GROUP = "@MIBScalarGroup(oid = \"%s\", name = \"%s\")";
    private final static String FORMAT_ANNO_MIB_TABLE_ENTRY = "@MIBTableEntry(oid = \"%s\", name = \"%s\", indexes = { %s })";
    private final static String FORMAT_ANNO_MIB_TABLE_INDEX = "@MIBTableIndex(name = \"%s\")";
    private final static String FORMAT_ANNO_MIB_NOTIFICATION = "@MIBNotification(oid = \"%s\", name = \"%s\", objects = { %s })";
    private final static String FORMAT_ANNO_MIB_NOTIF_OBJECT = "@MIBNotificationObject(name = \"%s\")";
    private final static String FORMAT_ANNO_MIB_VARIABLE = "@MIBVariable(oid = \"%s\", name = \"%s\", syntax = \"%s\", readable = %s, writeable = %s)";

    
    private final static String FORMAT_PACKAGE_DECL = "package %s.%s;";
    private final static String FORMAT_CLASS_DECL = "public class %s implements Serializable";
    private final static String FORMAT_INTERFACE_DECL = "public interface %s extends Serializable";
    private final static String FORMAT_serialVersionUID = "private static final long serialVersionUID = %dL;";
	
    private final static String FORMAT_ATTR_DECL = "private %s %s;";
    private final static String FORMAT_GETTER_DECL = "%s %s get%s()";
    private final static String FORMAT_GETTER_BODY = "return %s;";
    private final static String FORMAT_SETTER_DECL = "%s void set%s(%s %s)";
    private final static String FORMAT_SETTER_BODY = "this.%s = %s;";
   
    private final static String FORMAT_ENUM_DECL = "public enum %s";
    private final static String FORMAT_ENUM_CONST = "%s(%d),";
    private final static String FORMAT_ENUM_CTOR = "private %s(%s %s)";
    private final static String FORMAT_ENUM_FIELD = "private final %s %s;";
    private final static String FORMAT_ENUM_METHOD = "public %s %s()";
    private final static String FORMAT_ENUM_STATIC_METHOD = "public static %s %s(%s %s)";

    
    private final static String JAVA_DOC_BEGIN = "/**";
    private final static String JAVA_DOC_LINE  = " * ";
    private final static String JAVA_DOC_END   = " */";
    private final static String BLOCK_DECL_BEGIN = "{";
    private final static String BLOCK_DECL_END = "}";
    private final static String LINE_DECL_END = ";";
    private final static String NEW_LINE = "\n";
    private final static String TAB = "\t";
    

    static String formatClass(NotificationType notification, MibModule mibModule) {
    	StringBuilder buffer = new StringBuilder();

    	StringBuilder imports = new StringBuilder();
    	imports.append(String.format(FORMAT_PACKAGE_DECL, basePackage,
    			mibModule.getName().replace("-", "").toLowerCase()));
    	imports.append(NEW_LINE).append(NEW_LINE);
    	imports.append("import java.io.Serializable;");
    	imports.append(NEW_LINE);
    	imports.append("import snmp.obj.mib.annotations.*;");
    	imports.append(NEW_LINE);
    	imports.append("import snmp.obj.mib.constants.*;");
    	imports.append(NEW_LINE);

		StringBuilder annotations = new StringBuilder();
		annotations.append(NEW_LINE);
		String oid = notification.getNumberedOIDString().substring(1);
		String label = notification.getName();
			
		List<String> objects = notification.getObjects();
			StringBuilder objectString = new StringBuilder();
			int i = 0;
			String formattedIndex = String.format(FORMAT_ANNO_MIB_NOTIF_OBJECT, objects.get(i++));
			objectString.append(formattedIndex);
			for(; i < objects.size() - 1; i++) {
				formattedIndex = String.format(FORMAT_ANNO_MIB_NOTIF_OBJECT, objects.get(i));
				objectString.append(", ").append(formattedIndex);
			}
			annotations.append(String.format(FORMAT_ANNO_MIB_NOTIFICATION, oid, label, objectString));
		annotations.append(NEW_LINE);

		StringBuilder attributes = new StringBuilder();
		StringBuilder methods = new StringBuilder();
		StringBuilder enums = new StringBuilder();
		
		
		// attributes/getters/setters
        for(String object : objects)
        {
    		MibNode child = null;//mibModule.getMibNodeByName(object);
    		List<MibModule> mibModules = Collections.list(op.getMibModules());
    		for(MibModule module : mibModules) {
    			child = module.getMibNodeByName(object);
    			if(child != null) {
    				break;
    			}
    		}
    		if(child == null) {
    			System.err.println("Node not found: " + object);
    			continue;
    		}
        	if(child.isLeaf() && (child.isScalar() || child.isTableColumn()))
        	{
        		LeafSyntax syntax = child.getSyntax();
        		
        		attributes.append(formatAttribute(child));

        		methods.append(formatGetter(child));
        		methods.append(formatSetter(child));
        		
        		if(syntax.isEnumerated()) 
        		{
        			String formattedEnum = formatEnum(child);
        			if(formattedEnum != null)
        				enums.append(formattedEnum);
        		}
        	}
        }
        
    	buffer.append(imports.toString());
    	
    	buffer.append(NEW_LINE).append(JAVA_DOC_BEGIN);
        buffer.append(NEW_LINE).append(JAVA_DOC_LINE);
    	buffer.append(NEW_LINE).append(JAVA_DOC_LINE).append("GENERATED FROM MIB. DO NOT CHANGE MANUALLY.");
        buffer.append(NEW_LINE).append(JAVA_DOC_LINE);
        String[] lines = notification.getDescription().split("\n");
        for(i = 0; i < lines.length; i++)
        	buffer.append(NEW_LINE).append(JAVA_DOC_LINE).append(lines[i]);
        buffer.append(NEW_LINE).append(JAVA_DOC_LINE);

    	buffer.append(NEW_LINE).append(JAVA_DOC_END);

    	buffer.append(annotations.toString());
    	if(generateInterfaces) {
	    	buffer.append(String.format(FORMAT_INTERFACE_DECL, capitalizeFirstLetter(label)));
    	}
    	else {
	    	buffer.append(String.format(FORMAT_CLASS_DECL, capitalizeFirstLetter(label)));
    	}
    	buffer.append(BLOCK_DECL_BEGIN);
        buffer.append(NEW_LINE);
    	if(!generateInterfaces) {
    		buffer.append(NEW_LINE).append(TAB);
    		buffer.append(String.format(FORMAT_serialVersionUID, label.hashCode()));
	        buffer.append(NEW_LINE);
	        buffer.append(attributes.toString());
	        buffer.append(NEW_LINE);
    	}
        buffer.append(methods.toString());
        buffer.append(NEW_LINE);
        
        if(nestedEnums) {
	        buffer.append(enums.toString());
	        buffer.append(NEW_LINE);
        }
        
        buffer.append(BLOCK_DECL_END);
        buffer.append(NEW_LINE);
    	
    	return buffer.toString();
    }
    
    static String formatClass(MibNode mibNode) {
        // check for table entry or scalar group
        boolean isTableEntry = mibNode.isTableEntry();
        boolean isScalarGroup = false;
//        boolean isDynamicTable = false;

        if(!isTableEntry)
        {
            for(MibNode child : (Vector<MibNode>)mibNode.getChildList())
            {
            	if(child.isLeaf())
            	{
            		isScalarGroup = true;
            		break;
            	}
            }
        }

        if(isTableEntry || isScalarGroup)
        {
        	StringBuilder buffer = new StringBuilder();
        	
        	StringBuilder imports = new StringBuilder();
        	imports.append(String.format(FORMAT_PACKAGE_DECL, basePackage,
        									mibNode.getModuleName().replace("-", "").toLowerCase()));
        	imports.append(NEW_LINE).append(NEW_LINE);
        	imports.append("import java.io.Serializable;");
        	imports.append(NEW_LINE);
        	imports.append("import snmp.obj.mib.annotations.*;");
        	imports.append(NEW_LINE);
        	imports.append("import snmp.obj.mib.constants.*;");
        	imports.append(NEW_LINE);

			StringBuilder annotations = new StringBuilder();
			annotations.append(NEW_LINE);
			String oid = mibNode.getNumberedOIDString().substring(1);
			String label = mibNode.getLabel();
			if(isTableEntry) {
				List<String> indexes = mibNode.getIndexNames();
				StringBuilder indexString = new StringBuilder();
				int i = 0;
				String formattedIndex = String.format(FORMAT_ANNO_MIB_TABLE_INDEX, indexes.get(i++));
				indexString.append(formattedIndex);
				for(; i < indexes.size() - 1; i++) {
					formattedIndex = String.format(FORMAT_ANNO_MIB_TABLE_INDEX, indexes.get(i));
					indexString.append(", ").append(formattedIndex);
				}
				annotations.append(String.format(FORMAT_ANNO_MIB_TABLE_ENTRY, oid, label, indexString));
//						indexString.substring(1, indexString.length() - 1)));
			}
			else if(isScalarGroup){
				annotations.append(String.format(FORMAT_ANNO_MIB_SCALAR_GROUP, oid, label));
			}
			annotations.append(NEW_LINE);

			StringBuilder attributes = new StringBuilder();
			StringBuilder methods = new StringBuilder();
			StringBuilder enums = new StringBuilder();
			
			
			// attributes/getters/setters
	        for(MibNode child : (Vector<MibNode>)mibNode.getChildList())
	        {
	        	if(child.isLeaf() && (child.isScalar() || child.isTableColumn()))
	        	{
	        		LeafSyntax syntax = child.getSyntax();
	        		
	        		attributes.append(formatAttribute(child));

	        		methods.append(formatGetter(child));
	        		methods.append(formatSetter(child));
	        		
	        		if(syntax.isEnumerated()) 
	        		{
	        			String formattedEnum = formatEnum(child);
	        			if(formattedEnum != null)
	        				enums.append(formattedEnum);
	        		}
	        	}
	        }
	        
	    	buffer.append(imports.toString());
	    	
	    	buffer.append(NEW_LINE).append(JAVA_DOC_BEGIN);
	        buffer.append(NEW_LINE).append(JAVA_DOC_LINE);
	    	buffer.append(NEW_LINE).append(JAVA_DOC_LINE).append("GENERATED FROM MIB. DO NOT CHANGE MANUALLY.");
	        buffer.append(NEW_LINE).append(JAVA_DOC_LINE);
	    	buffer.append(NEW_LINE).append(JAVA_DOC_END);

	    	buffer.append(annotations.toString());
	    	if(generateInterfaces) {
		    	buffer.append(String.format(FORMAT_INTERFACE_DECL, capitalizeFirstLetter(label)));
	    	}
	    	else {
		    	buffer.append(String.format(FORMAT_CLASS_DECL, capitalizeFirstLetter(label)));
	    	}
	    	buffer.append(BLOCK_DECL_BEGIN);
	        buffer.append(NEW_LINE);
	    	if(!generateInterfaces) {
	    		buffer.append(NEW_LINE).append(TAB);
	    		buffer.append(String.format(FORMAT_serialVersionUID, label.hashCode()));
		        buffer.append(NEW_LINE);
		        buffer.append(attributes.toString());
		        buffer.append(NEW_LINE);
	    	}
	        buffer.append(methods.toString());
	        buffer.append(NEW_LINE);
	        
	        if(nestedEnums) {
		        buffer.append(enums.toString());
		        buffer.append(NEW_LINE);
	        }
	        
	        buffer.append(BLOCK_DECL_END);
	        buffer.append(NEW_LINE);
	    	
	    	return buffer.toString();
        }
        
        return null;
    }
    
    static String formatAttribute(MibNode mibNode) {
    	
    	StringBuilder buffer = new StringBuilder();
    	
    	LeafSyntax syntax = mibNode.getSyntax();
		String label = mibNode.getLabel();
		
		buffer.append(NEW_LINE).append(TAB);
		if(annotateFields) {
	    	buffer.append(String.format(FORMAT_ANNO_MIB_VARIABLE, mibNode.getSubID(), label, syntax.getName().replace(' ', '_'),
	    			mibNode.isReadable() ? true : false, mibNode.isWriteable() ? true : false));
		}
		String type = formatType(mibNode);
		
    	buffer.append(formatAttribute(type, label));
    	
    	return buffer.toString();
    }

    static String formatAttribute(String type, String name) {
    	StringBuilder buffer = new StringBuilder();
    	
    	buffer.append(NEW_LINE).append(TAB);
    	buffer.append(String.format(FORMAT_ATTR_DECL, type, name));
    	buffer.append(NEW_LINE);
    	
    	return buffer.toString();
    }
    
    static String formatGetter(MibNode mibNode) {
    	StringBuilder buffer = new StringBuilder();

    	LeafSyntax syntax = mibNode.getSyntax();
		String label = mibNode.getLabel();

    	buffer.append(NEW_LINE).append(TAB);
    	buffer.append(NEW_LINE).append(TAB).append(JAVA_DOC_BEGIN);
        buffer.append(NEW_LINE).append(TAB).append(JAVA_DOC_LINE);
        String[] lines = mibNode.getDescription().split("\n");
        for(int i = 0; i < lines.length; i++)
        	buffer.append(NEW_LINE).append(TAB).append(JAVA_DOC_LINE).append(lines[i]);
        buffer.append(NEW_LINE).append(TAB).append(JAVA_DOC_LINE);
    	buffer.append(NEW_LINE).append(TAB).append(JAVA_DOC_END);
    
		if(generateInterfaces || !annotateFields) {
			buffer.append(NEW_LINE).append(TAB);
	    	buffer.append(String.format(FORMAT_ANNO_MIB_VARIABLE, mibNode.getSubID(), label, syntax.getName().replace(' ', '_'),
	    			mibNode.isReadable() ? true : false, mibNode.isWriteable() ? true : false));
		}

    	String type = formatType(mibNode);
		buffer.append(formatGetter(type, label));
		
		return buffer.toString();
    }

    static String formatGetter(String type, String name) {
    	StringBuilder buffer = new StringBuilder();
    	
    	buffer.append(NEW_LINE).append(TAB);
    	buffer.append(String.format(FORMAT_GETTER_DECL, generateInterfaces ? "" : "public",
    			type, capitalizeFirstLetter(name)));
    	if(generateInterfaces) {
    		buffer.append(LINE_DECL_END);
    	}
    	else {
	    	buffer.append(BLOCK_DECL_BEGIN);
	    	buffer.append(NEW_LINE).append(TAB).append(TAB);
	    	buffer.append(String.format(FORMAT_GETTER_BODY, name));
	    	buffer.append(NEW_LINE).append(TAB);
	    	buffer.append(BLOCK_DECL_END);
    	}
    	buffer.append(NEW_LINE);
    	
    	return buffer.toString();
    }

    static String formatSetter(MibNode mibNode) {
    	return formatSetter(formatType(mibNode), mibNode.getLabel(), mibNode.isWriteable());
    }
    
    static String formatSetter(String type, String name, boolean accessible) {
    	StringBuilder buffer = new StringBuilder();
    	
    	buffer.append(NEW_LINE).append(TAB);
    	buffer.append(String.format(FORMAT_SETTER_DECL, 
    			generateInterfaces ? "" : (accessible ? "public" : "private"), 
    			capitalizeFirstLetter(name), type, name));
    	if(generateInterfaces) {
    		buffer.append(LINE_DECL_END);
    	}
    	else {
	    	buffer.append(BLOCK_DECL_BEGIN);
	    	buffer.append(NEW_LINE).append(TAB).append(TAB);
	    	buffer.append(String.format(FORMAT_SETTER_BODY, name, name));
	    	buffer.append(NEW_LINE).append(TAB);
	    	buffer.append(BLOCK_DECL_END);
    	}
    	buffer.append(NEW_LINE);
    	
    	return buffer.toString();
    }

    static String formatType(MibNode mibNode) {
       	LeafSyntax syntax = mibNode.getSyntax();
       	String syntaxName = syntax.getName();
       	String label = mibNode.getLabel();
		String type = SMI_TYPES_MAP.get(syntaxName);
		if(syntax.isEnumerated()) {
	    	if("RowStatus".equals(syntaxName)) 
	    		type = "RowStatus";
	    	else if("StorageType".equals(syntaxName)) 
	    		type = "StorageType";
	    	else if("TruthValue".equals(syntaxName)) 
	    		type = "TruthValue";
	    	else
	    		type = capitalizeFirstLetter(label);
		}
		else {
			
			if(type == null) {
				while(syntax instanceof MibTC) {
					syntax = syntax.getSyntax();
				}
				if(syntax != null) {
					syntaxName = syntax.getName();
					type = SMI_TYPES_MAP.get(syntaxName);
					if(type == null) {
						type = "%"+syntax.getName()+"%";
						System.err.println(syntax.getName());
					}
				}
			}
		}
		
		return type;
    }
    
    static String formatEnum(MibNode mibNode) {

    	LeafSyntax syntax = mibNode.getSyntax();
    	if(Arrays.asList("RowStatus", "StorageType", "TruthValue").contains(syntax.getName())) {
    		return null;
    	}

    	StringBuilder buffer = new StringBuilder();
    	
    	buffer.append(NEW_LINE).append(TAB);
    	String name = mibNode.getLabel();
		String capitalizedName = capitalizeFirstLetter(name);
		buffer.append(String.format(FORMAT_ENUM_DECL, capitalizedName));
    	buffer.append(BLOCK_DECL_BEGIN);
    	
    	
    	String[] enumLabels = syntax.getEnumlabels();
    	int[] enumValues = syntax.getEnumint();
        for (int i = 0; i < enumValues.length; i++)
        {
        	buffer.append(NEW_LINE).append(TAB).append(TAB);
        	String label = "_" + enumLabels[i].replaceAll("-", "_");
//        	if(Arrays.asList("volatile", "true", "false").contains(label)) {
//        		label = "_" + label;
//        	}
			buffer.append(String.format(FORMAT_ENUM_CONST, label, enumValues[i]));
        }
        
    	buffer.append(";");
    	buffer.append(NEW_LINE);
    	
    	buffer.append(NEW_LINE).append(TAB).append(TAB);
    	buffer.append(String.format(FORMAT_ENUM_FIELD, "int", "value"));
    	buffer.append(NEW_LINE);
    	
    	buffer.append(NEW_LINE).append(TAB).append(TAB);
    	buffer.append(String.format(FORMAT_ENUM_CTOR, capitalizedName, "int", "value"));
    	buffer.append(BLOCK_DECL_BEGIN);
    	buffer.append(NEW_LINE).append(TAB).append(TAB).append(TAB);
    	buffer.append(String.format(FORMAT_SETTER_BODY, "value", "value"));
    	buffer.append(NEW_LINE).append(TAB).append(TAB);
    	buffer.append(BLOCK_DECL_END);
    	buffer.append(NEW_LINE);

    	buffer.append(NEW_LINE).append(TAB).append(TAB);
    	buffer.append(String.format(FORMAT_ENUM_METHOD, "int", "value"));
    	buffer.append(BLOCK_DECL_BEGIN);
    	buffer.append(NEW_LINE).append(TAB).append(TAB).append(TAB);
    	buffer.append(String.format(FORMAT_GETTER_BODY, "value"));
    	buffer.append(NEW_LINE).append(TAB).append(TAB);
    	buffer.append(BLOCK_DECL_END);
    	buffer.append(NEW_LINE);

    	buffer.append(NEW_LINE).append(TAB).append(TAB);
    	buffer.append(String.format(FORMAT_ENUM_STATIC_METHOD, capitalizedName, "fromValue", "int", "value"));
    	buffer.append(BLOCK_DECL_BEGIN);
    	buffer.append(NEW_LINE).append(TAB).append(TAB).append(TAB);
    	buffer.append("for(").append(capitalizedName).append(" constant : values())");
    	buffer.append(BLOCK_DECL_BEGIN);
    	buffer.append(NEW_LINE).append(TAB).append(TAB).append(TAB).append(TAB);
    	buffer.append("if(constant.value() == value)");
    	buffer.append(BLOCK_DECL_BEGIN);
    	buffer.append(NEW_LINE).append(TAB).append(TAB).append(TAB).append(TAB).append(TAB);
    	buffer.append("return constant;");
    	buffer.append(NEW_LINE).append(TAB).append(TAB).append(TAB).append(TAB);
    	buffer.append(BLOCK_DECL_END);
    	buffer.append(NEW_LINE).append(TAB).append(TAB).append(TAB);
    	buffer.append(BLOCK_DECL_END);
    	buffer.append(NEW_LINE).append(TAB).append(TAB).append(TAB);
    	buffer.append("return null;");
    	buffer.append(NEW_LINE).append(TAB).append(TAB);
    	buffer.append(BLOCK_DECL_END);
    	buffer.append(NEW_LINE);

    	buffer.append(NEW_LINE).append(TAB);
    	buffer.append(BLOCK_DECL_END);
    	buffer.append(NEW_LINE);
    
        return buffer.toString();
    }
}
