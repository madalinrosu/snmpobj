package snmp.obj.config;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import snmp.obj.mib.annotations.MIBObjectGroup;
import snmp.obj.mib.annotations.MIBScalarGroup;
import snmp.obj.mib.annotations.MIBSubtree;
import snmp.obj.mib.annotations.MIBTable;
import snmp.obj.mib.annotations.MIBTableEntry;
import snmp.obj.mib.annotations.MIBVariable;

public class AnnotationConfiguration extends Configuration {

	// managed objects type cache
	protected transient Map<String, ManagedObjectClass> mappings = new LinkedHashMap<>();
	
	public Configuration processAnnotations(Class<?>...args) throws Exception {
		super.processAnnotations(args);
//		for(Class<?> clazz : args) {
//			processClass(clazz);
//		}
		return this;
	}

	private void processClass(Class<?> clazz) throws Exception {
		
		MIBScalarGroup scalarGroup = clazz.getAnnotation(MIBScalarGroup.class);
		if(scalarGroup != null) {
//			processScalarGroup(scalarGroup, clazz);
		}
		else {
			MIBTableEntry tableEntry = clazz.getAnnotation(MIBTableEntry.class);
			if(tableEntry != null) {
//				processTableEntry(tableEntry, clazz);
			}
			else {
				MIBObjectGroup objectGroup = clazz.getAnnotation(MIBObjectGroup.class);
				if(objectGroup != null) {
//					processObjectGroup(objectGroup, clazz);
				}
				
			}
		}
	}

	

}
