package snmp.obj.config;

import java.io.File;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;

import snmp.obj.DefaultSNMPSessionFactory;
import snmp.obj.SNMPSession;
import snmp.obj.SNMPSessionFactory;
import snmp.obj.config.ManagedObjectClass.Stereotype;
import snmp.obj.mib.annotations.MIBNotification;
import snmp.obj.mib.annotations.MIBObjectGroup;
import snmp.obj.mib.annotations.MIBScalarGroup;
import snmp.obj.mib.annotations.MIBSubtree;
import snmp.obj.mib.annotations.MIBTable;
import snmp.obj.mib.annotations.MIBTableEntry;
import snmp.obj.mib.annotations.MIBVariable;
import snmp.obj.notification.DefaultSNMPTrapd;
import snmp.obj.notification.Notification;
import snmp.obj.notification.SNMPNotificationListener;
import snmp.obj.notification.SNMPTrapd;
import snmp.obj.notification.Trap;
import snmp.obj.proxy.ManagedObjectProxyFactory;
import snmp.obj.proxy.TableProxyFactory;
import snmp.obj.proxy.TableProxyFactoryImpl;
import snmp.obj.util.ObjectIDComparator;

public abstract class Configuration implements Serializable {

	private static final long serialVersionUID = 4068625671531414918L;

	protected Properties properties;
	
	// managed objects / notifications type cache
	protected Map<String, Class<?>> manageObjectClasses = new LinkedHashMap<>();
	protected Map<String, Class<?>> notificationClasses = new LinkedHashMap<>();
	
	// scalar groups meta-data cache
	protected Map<String, Map<String,MIBVariable>> scalarGroups = new LinkedHashMap<>();
	// table entries meta-data cache
	protected Map<String, Map<String,MIBVariable>> tableEntries = new LinkedHashMap<>();
	
	// aggregate managed objects (partial subtrees) meta-data cache
	protected Map<String, Map<String,MIBVariable>> objectGroupsScalars = new LinkedHashMap<>();
	protected Map<String, Map<String,MIBTable>> objectGroupsTables = new LinkedHashMap<>();
	protected Map<String, Map<String,MIBSubtree>> objectGroupsSubtrees = new LinkedHashMap<>();
	
	// notification / trap meta-data cache
	protected Map<Class<?>, Map<String,Property>> notificationMetadata = new LinkedHashMap<>();

	// managed objects property accessors cache
	protected transient Map<String, PropertyAccessor> propertyAccessors = new LinkedHashMap<>();
	
	// notifications property accessors cache
//	protected transient Map<Class<?>, PropertyAccessor> notificationPropertyAccessors = new LinkedHashMap<>();
	
	protected Map<String, Class<?>> propertyTypes = new LinkedHashMap<>();

	public ManagedObjectClass getMetadata(Class<?> clazz) {
		ManagedObjectClass moClass = null;
		if(isScalarGroup(clazz)) {
			ScalarGroupMetadataBuilder builder = new ScalarGroupMetadataBuilder(clazz);
			MIBScalarGroup meta = clazz.getAnnotation(MIBScalarGroup.class);
			for(MIBVariable var : getVariables(meta).values()) {
				builder.appendProperty(var);
			}
			moClass = builder.toManagedObjectClass();
		}
		else if(isTableEntry(clazz)) {
			TableEntryMetadataBuilder builder = new TableEntryMetadataBuilder(clazz);
			MIBTableEntry meta = clazz.getAnnotation(MIBTableEntry.class);
			for(MIBVariable var : getVariables(meta).values()) {
				builder.appendProperty(var);
			}
			moClass = builder.toManagedObjectClass();
		}
		else if(isObjectGroup(clazz)) {
			ObjectGroupMetadataBuilder builder = new ObjectGroupMetadataBuilder(clazz);
			MIBObjectGroup meta = clazz.getAnnotation(MIBObjectGroup.class);
			for(MIBVariable var : getVariables(meta).values()) {
				builder.appendProperty(var);
			}
			moClass = builder.toManagedObjectClass();
		}
		return moClass;
	}
	
	public Map<String,MIBVariable> getVariables(MIBScalarGroup scalarGroup) {
		return scalarGroups.get(scalarGroup.oid());
	}

	public Map<String,MIBVariable> getVariables(MIBTableEntry tableEntry) {
		return tableEntries.get(tableEntry.oid());
	}

	public Map<String,MIBVariable> getVariables(MIBObjectGroup objectGroup) {
		return scalarGroups.get(objectGroup.oid());
	}

	public Map<String,Property> getVariables(MIBNotification notification) {
		return notificationMetadata.get(notificationClasses.get(notification.oid()));
	}

	public Map<String,MIBVariable> getVariables(MIBTable table) {
		return tableEntries.get(table.oid() + ".1");
	}

	public Map<String,MIBTable> getTables(MIBObjectGroup objectGroup) {
		return objectGroupsTables.get(objectGroup.oid());
	}

	public Map<String,MIBSubtree> getSubtries(MIBObjectGroup objectGroup) {
		return objectGroupsSubtrees.get(objectGroup.oid());
	}


	public PropertyAccessor getPropertyAccessor(String oid) {
		return propertyAccessors.get(oid);
	}

//	public PropertyAccessor getNotificationPropertyAccessor(String oid) {
//		return notificationPropertyAccessors.get(oid);
//	}

	public Class<?> getManagedObjectType(String oid) {
		return manageObjectClasses.get(oid);
	}

	public Class<?> getNotificationType(String oid) {
		return notificationClasses.get(oid);
	}

	public Class<?> getVariableType(String oid) {
		return propertyTypes.get(oid);
	}

	protected PropertyAccessor putPropertyAccessor(String oid, PropertyAccessor accessor, Map<String, PropertyAccessor> accessorMap) throws DuplicateObjectException {
		if(accessorMap.containsKey(oid)) {
			//throw new DuplicateObjectException(oid);
		}
		return accessorMap.put(oid, accessor);
	}

	public SNMPTrapd buildTrapDaemon() throws Exception {
		
		return new SNMPTrapd() {
			DefaultSNMPTrapd delegatedDaemon = createDelegatedDaemon(properties.getProperty("snmp.obj.trapDaemon"));

			private DefaultSNMPTrapd createDelegatedDaemon(String className) throws Exception {
				Class<?> clazz = Class.forName(className);
				Constructor<?> ctor = clazz.getConstructor(Configuration.class);
				return (DefaultSNMPTrapd) ctor.newInstance(Configuration.this);
			}

			@Override
			public void start() {
				delegatedDaemon.start();
			}

			@Override
			public void stop() {
				delegatedDaemon.stop();
			}

			@Override
			public void registerListener(SNMPNotificationListener listener) {
				delegatedDaemon.registerListener(listener);
			}

			@Override
			public void unregisterListener(SNMPNotificationListener listener) {
				delegatedDaemon.unregisterListener(listener);
			}

			@Override
			public void unregisterAllListeners() {
				delegatedDaemon.unregisterAllListeners();
			}
			
		};
	}
	public SNMPSessionFactory buildSessionFactory() throws Exception {
		// anonymous implementation for future use as proxy or decorator
		return new SNMPSessionFactory (){

			DefaultSNMPSessionFactory delegatedFactory = createDelegatedFactory(properties.getProperty("snmp.obj.sessionFactory"));
			ManagedObjectProxyFactory proxyFactory = createProxyFactory(properties.getProperty("snmp.obj.bytecodeProvider"));
			TableProxyFactory tableProxyFactory = createTableProxyFactory();
			
			private DefaultSNMPSessionFactory createDelegatedFactory(String className) throws Exception {
				Class<?> clazz = Class.forName(className);
				Constructor<?> ctor = clazz.getConstructor(Configuration.class);
				return (DefaultSNMPSessionFactory) ctor.newInstance(Configuration.this);
			}

			private ManagedObjectProxyFactory createProxyFactory(String bytecodeProvider) throws Exception {
				Class<?> clazz = Class.forName(String.format("snmp.obj.proxy.%s.ManagedObjectProxyFactoryImpl", bytecodeProvider));
				return (ManagedObjectProxyFactory) clazz.newInstance();
			}

			private TableProxyFactory createTableProxyFactory() throws Exception {
				return new TableProxyFactoryImpl();
			}

			@Override
			public SNMPSession createSNMPv1Session(String agentAddress,	String community) {
				return delegatedFactory.createSNMPv1Session(agentAddress, community).setProxyFactory(proxyFactory).setTableProxyFactory(tableProxyFactory);
			}

			@Override
			public SNMPSession createSNMPv1Session(String agentAddress, String community, int port, int retries, long timeout) {
				return delegatedFactory.createSNMPv1Session(agentAddress, community, port, retries, timeout).setProxyFactory(proxyFactory).setTableProxyFactory(tableProxyFactory);
			}

			@Override
			public SNMPSession createSNMPv2cSession(String agentAddress, String community) {
				return delegatedFactory.createSNMPv2cSession(agentAddress, community).setProxyFactory(proxyFactory).setTableProxyFactory(tableProxyFactory);
			}

			@Override
			public SNMPSession createSNMPv2cSession(String agentAddress, String community, int port, int retries, long timeout) {
				return delegatedFactory.createSNMPv2cSession(agentAddress, community, port, retries, timeout).setProxyFactory(proxyFactory).setTableProxyFactory(tableProxyFactory);
			}

			@Override
			public SNMPSession createSNMPv3Session(String agentAddress,	String securityName) {
				return delegatedFactory.createSNMPv3Session(agentAddress, securityName).setProxyFactory(proxyFactory).setTableProxyFactory(tableProxyFactory);
			}

			@Override
			public SNMPSession createSNMPv3Session(String agentAddress,	String securityName, int port, int retries, long timeout) {
				return delegatedFactory.createSNMPv3Session(agentAddress, securityName, port, retries, timeout).setProxyFactory(proxyFactory).setTableProxyFactory(tableProxyFactory);
			}
			
		};
	}

	public Configuration setProperties(Properties properties) {
		this.properties = properties;
		return this;
	}

	public Configuration addProperties(Properties extraProperties) {
		this.properties.putAll(extraProperties);
		return this;
	}

	
	public boolean isScalarGroup(Class<?> clazz) {
		return clazz.isAnnotationPresent(MIBScalarGroup.class);
	}

	public boolean isTableEntry(Class<?> clazz) {
		return clazz.isAnnotationPresent(MIBTableEntry.class);
	}
	
	public boolean isObjectGroup(Class<?> clazz) {
		return clazz.isAnnotationPresent(MIBObjectGroup.class);
	}

	public boolean isNotification(Class<?> clazz) {
		return clazz.isAnnotationPresent(MIBNotification.class);
	}

	public boolean isVariable(Field field) {
		return field.isAnnotationPresent(MIBVariable.class);
	}
	public boolean isTable(Field field) {
		return field.isAnnotationPresent(MIBTable.class);
	}
	public boolean isSubtree(Field field) {
		return field.isAnnotationPresent(MIBSubtree.class);
	}
	public boolean isVariable(Method method) {
		return method.isAnnotationPresent(MIBVariable.class);
	}
	public boolean isTable(Method method) {
		return method.isAnnotationPresent(MIBTable.class);
	}
	public boolean isSubtree(Method method) {
		return method.isAnnotationPresent(MIBSubtree.class);
	}
	



	
	protected boolean isVariableField(Field field) {
		Class<?> type = field.getType();
		return isVariableType(type);
	}
	protected boolean isTableField(Field field) {
		Class<?> type = field.getType();
		return isTableType(type);
	}
	protected boolean isSubtreeField(Field field) {
		Class<?> type = field.getType();
		return isSubtreeType(type);
	}
	
	protected boolean isVariableGetter(Method method) {
		Class<?> paramTypes[] = method.getParameterTypes();
		if(paramTypes.length == 0) {
			String methodName = method.getName();
			Class<?> retType = method.getReturnType();
			if(methodName.startsWith("get")) {
				if(isVariableType(retType)) {
					return true;
				}
			}
			else if(methodName.startsWith("is")) {
				return boolean.class.isAssignableFrom(retType);
			}
		}
		return false;
	}

	protected boolean isVariableSetter(Method method) {
		Class<?> paramTypes[] = method.getParameterTypes();
		if(paramTypes.length == 1) {
			String methodName = method.getName();
			Class<?> retType = method.getReturnType();
			if(methodName.startsWith("set")) {
				if(isVariableType(paramTypes[0]) && void.class.isAssignableFrom(retType)) {
					return true;
				}
			}
		}
		return false;
	}

	protected boolean isTableGetter(Method method) {
		Class<?> paramTypes[] = method.getParameterTypes();
		if(paramTypes.length == 0) {
			String methodName = method.getName();
			Class<?> retType = method.getReturnType();
			if(methodName.startsWith("get")) {
				if(isTableType(retType)) {
					return true;
				}
			}
		}
		return false;
	}

	protected boolean isTableSetter(Method method) {
		Class<?> paramTypes[] = method.getParameterTypes();
		if(paramTypes.length == 1) {
			String methodName = method.getName();
			Class<?> retType = method.getReturnType();
			if(methodName.startsWith("set")) {
				if(isTableType(paramTypes[0]) && void.class.isAssignableFrom(retType)) {
					return true;
				}
			}
		}
		return false;
	}

	protected boolean isSubtreeGetter(Method method) {
		Class<?> paramTypes[] = method.getParameterTypes();
		if(paramTypes.length == 0) {
			String methodName = method.getName();
			Class<?> retType = method.getReturnType();
			if(methodName.startsWith("get")) {
				if(isSubtreeType(retType)) {
					return true;
				}
			}
		}
		return false;
	}

	protected boolean isSubtreeSetter(Method method) {
		Class<?> paramTypes[] = method.getParameterTypes();
		if(paramTypes.length == 1) {
			String methodName = method.getName();
			Class<?> retType = method.getReturnType();
			if(methodName.startsWith("set")) {
				if(isSubtreeType(paramTypes[0]) && void.class.isAssignableFrom(retType)) {
					return true;
				}
			}
		}
		return false;
	}

	protected boolean isVariableType(Class<?> type) {
		if(Integer.class.isAssignableFrom(type))
			return true;
		else if(Long.class.isAssignableFrom(type))
			return true;
		else if(String.class.isAssignableFrom(type))
			return true;
		else if(Boolean.class.isAssignableFrom(type))
			return true;
		if(Enum.class.isAssignableFrom(type))
			return true;
		return false;
	}

	protected boolean isTableType(Class<?> type) {
		if(List.class.isAssignableFrom(type))
			return true;
		else if(Set.class.isAssignableFrom(type))
			return true;
		else if(Map.class.isAssignableFrom(type))
			return true;
		return false;
	}

	protected boolean isSubtreeType(Class<?> type) {
		return true;
	}
	
	// FIXME: is not working for classes in jars
	public Configuration processAnnotations(String...args) throws Exception {
		for(String name : args) {
			// TODO: scan packages from classpath
//			String pattern = String.format("classpath*:%s/**/*.class", name.replace(".", "/"));
			String pattern = String.format("%s/**/*.class", name.replace(".", "/"));
//			Enumeration<URL> resources = ClassLoader.getSystemClassLoader().getResources(name.replace(".", "/"));
			ClassLoader classLoader = getClass().getClassLoader();
			Enumeration<URL> resources = classLoader.getResources(name.replace(".", "/"));
			while(resources.hasMoreElements()) {
				URL url = resources.nextElement();
				System.out.println(url);
				File pkg = new File(url.toURI());
				if(pkg.isDirectory()) {
					File[] files = pkg.listFiles();
					for(File file : files) {
						System.out.println(file.getName());
						if(file.isDirectory()) {
							File[] files2 = file.listFiles();
							for(File file2 : files2) {
								System.out.println(file2.getName());
								Class<?> clazz = classLoader.loadClass(name + "." + file.getName() + "." + file2.getName().replaceAll(".class", ""));
								processAnnotations(clazz);
							}
						}
					}
				}
			}
		}
		return this;
	}

	public Configuration processAnnotations(Class<?>...args) throws Exception {
		for(Class<?> clazz : args) {
			processClass(clazz);
		}
		return this;
	}

	private void processClass(Class<?> clazz) throws Exception {
		if(clazz.isAnnotationPresent(MIBScalarGroup.class)) {
			MIBScalarGroup scalarGroup = clazz.getAnnotation(MIBScalarGroup.class);
			processScalarGroup(scalarGroup, clazz);
		}
		else if(clazz.isAnnotationPresent(MIBTableEntry.class)) {
			MIBTableEntry tableEntry = clazz.getAnnotation(MIBTableEntry.class);
			processTableEntry(tableEntry, clazz);
		}
		else if(clazz.isAnnotationPresent(MIBObjectGroup.class)) {
			MIBObjectGroup objectGroup = clazz.getAnnotation(MIBObjectGroup.class);
			processObjectGroup(objectGroup, clazz);
		}
		else if(clazz.isAnnotationPresent(MIBNotification.class)) {
			MIBNotification notification = clazz.getAnnotation(MIBNotification.class);
			processNotification(notification, clazz);
		}
		else {

		}
	}

	private Map<Class<? extends Annotation>, List<Field>> getAnnotatedFields(Class<?> clazz) {
		Map<Class<? extends Annotation>, List<Field>> fieldMap = new HashMap<>();
		ArrayList<Field> variables = new ArrayList<>();
		ArrayList<Field> tables = new ArrayList<>();
		ArrayList<Field> subtrees = new ArrayList<>();

		Field[] fields = clazz.getDeclaredFields();
		for(Field field : fields) {
			if(field.isAnnotationPresent(MIBVariable.class)) {
				variables.add(field);
			}
			else if(field.isAnnotationPresent(MIBTable.class)) {
				tables.add(field);
			}
			else if(field.isAnnotationPresent(MIBSubtree.class)) {
				subtrees.add(field);
			}
		}
		
		fieldMap.put(MIBVariable.class, variables);
		fieldMap.put(MIBTable.class, tables);
		fieldMap.put(MIBSubtree.class, subtrees);
		return fieldMap;
	}

	private Map<Class<? extends Annotation>, List<Method>> getAnnotatedGetters(Class<?> clazz) {
		Map<Class<? extends Annotation>, List<Method>> methodMap = new HashMap<>();
		ArrayList<Method> variables = new ArrayList<>();
		ArrayList<Method> tables = new ArrayList<>();
		ArrayList<Method> subtrees = new ArrayList<>();

		Method[] methods = clazz.getDeclaredMethods();
		for(Method method : methods) {
			if(method.isAnnotationPresent(MIBVariable.class)) {
				variables.add(method);
			}
			else if(method.isAnnotationPresent(MIBTable.class)) {
				tables.add(method);
			}
			else if(method.isAnnotationPresent(MIBSubtree.class)) {
				subtrees.add(method);
			}
		}
		
		methodMap.put(MIBVariable.class, variables);
		methodMap.put(MIBTable.class, tables);
		methodMap.put(MIBSubtree.class, subtrees);
		return methodMap;
	}

	private Map<String, Property> processNotificationVariableFields(String oid, List<Field> fields) throws DuplicateObjectException {
		Map<String,Property> properties = new TreeMap<>(new ObjectIDComparator());
		for(Field field : fields) {
			MIBVariable variable = field.getAnnotation(MIBVariable.class);
			Property property = new Property(PropertyType.NotificationObject, field.getType(), variable);
			String varOid = variable.oid();
			if(isVariableField(field)) {
				PropertyAccessor accessor = new FieldLevelAccessor(field);
				property.setAccessor(accessor);
				//putPropertyAccessor(varOid, accessor, notificationPropertyAccessors);
				//propertyTypes.put(varOid, field.getType());
				properties.put(varOid, property);
			}
			else {
				throw new RuntimeException();
			}
		}	
		return properties;
	}

	private Map<String, MIBVariable> processVariableFields(String oid, List<Field> fields) throws DuplicateObjectException {
		Map<String,MIBVariable> variables = new TreeMap<>(new ObjectIDComparator());
		for(Field field : fields) {
			MIBVariable variable = field.getAnnotation(MIBVariable.class);
			String varOid = String.format("%s.%s", oid, variable.oid());
			if(isVariableField(field)) {
				PropertyAccessor accessor = new FieldLevelAccessor(field);
				putPropertyAccessor(varOid, accessor, propertyAccessors);
				propertyTypes.put(varOid, field.getType());
				variables.put(varOid, variable);
			}
			else {
				throw new RuntimeException();
			}
		}	
		return variables;
	}

	private Map<String, MIBTable> processTableFields(String oid, List<Field> fields) throws DuplicateObjectException {
		Map<String,MIBTable> tables = new TreeMap<>(new ObjectIDComparator());
		for(Field field : fields) {
			MIBTable table = field.getAnnotation(MIBTable.class);
			String tableOid = String.format("%s.%s", oid, table.oid());
			if(isTableField(field)) {
				PropertyAccessor accessor = new FieldLevelAccessor(field);
				putPropertyAccessor(tableOid, accessor, propertyAccessors);
				propertyTypes.put(tableOid, field.getType());
				tables.put(tableOid, table);
			}
			else {
				throw new RuntimeException();
			}
		}	
		return tables;
	}

	private Map<String, MIBSubtree> processSubtreeFields(String oid, List<Field> fields) throws DuplicateObjectException {
		Map<String,MIBSubtree> subtrees = new TreeMap<>(new ObjectIDComparator());
		for(Field field : fields) {
			MIBSubtree subtree = field.getAnnotation(MIBSubtree.class);
			String subtreeOid = String.format("%s.%s", oid, subtree.oid());
			if(isSubtreeField(field)) {
				PropertyAccessor accessor = new FieldLevelAccessor(field);
				putPropertyAccessor(subtreeOid, accessor, propertyAccessors);
				propertyTypes.put(subtreeOid, field.getType());
				subtrees.put(subtreeOid, subtree);
			}
			else {
				throw new RuntimeException();
			}
		}	
		return subtrees;
	}

	private Map<String, Property> processNotificationVariableMethods(String oid, List<Method> methods, Class<?> clazz) throws DuplicateObjectException {
		Map<String,Property> properties = new TreeMap<>(new ObjectIDComparator());
		for(Method getter : methods) {
			MIBVariable variable = getter.getAnnotation(MIBVariable.class);
			Property property = new Property(PropertyType.NotificationObject, getter.getReturnType(), variable);
			String varOid = variable.oid();
			if(isVariableGetter(getter)) {
				Method setter = findSetter(getter, clazz);
				if(setter != null && isVariableSetter(setter)) {
					PropertyAccessor accessor = new MethodLevelAccessor(getter, setter);
					property.setAccessor(accessor);
//					putPropertyAccessor(varOid, accessor, notificationPropertyAccessors);
//					propertyTypes.put(varOid, getter.getReturnType());
					properties.put(varOid, property);
				}
			}
			else {
				throw new RuntimeException();
			}
		}	
		return properties;
	}

	private Map<String, MIBVariable> processVariableMethods(String oid, List<Method> methods, Class<?> clazz) throws DuplicateObjectException {
		Map<String,MIBVariable> variables = new TreeMap<>(new ObjectIDComparator());
		for(Method getter : methods) {
			MIBVariable variable = getter.getAnnotation(MIBVariable.class);
			String varOid = String.format("%s.%s", oid, variable.oid());
			if(isVariableGetter(getter)) {
				Method setter = findSetter(getter, clazz);
				if(setter != null && isVariableSetter(setter)) {
					PropertyAccessor accessor = new MethodLevelAccessor(getter, setter);
					putPropertyAccessor(varOid, accessor, propertyAccessors);
					propertyTypes.put(varOid, getter.getReturnType());
					variables.put(varOid, variable);
				}
			}
			else {
				throw new RuntimeException();
			}
		}	
		return variables;
	}

	private Map<String, MIBTable> processTableMethods(String oid, List<Method> methods, Class<?> clazz) throws DuplicateObjectException {
		Map<String,MIBTable> tables = new TreeMap<>(new ObjectIDComparator());
		for(Method getter : methods) {
			MIBTable table = getter.getAnnotation(MIBTable.class);
			String tableOid = String.format("%s.%s", oid, table.oid());
			if(isTableGetter(getter)) {
				Method setter = findSetter(getter, clazz);
				if(setter != null && isTableSetter(setter)) {
					PropertyAccessor accessor = new MethodLevelAccessor(getter, setter);
					putPropertyAccessor(tableOid, accessor, propertyAccessors);
					propertyTypes.put(tableOid, getter.getReturnType());
					tables.put(tableOid, table);
				}
			}
		}	
		return tables;
	}

	private Map<String, MIBSubtree> processSubtreeMethods(String oid, List<Method> methods, Class<?> clazz) throws DuplicateObjectException {
		Map<String,MIBSubtree> subtrees = new TreeMap<>(new ObjectIDComparator());
		for(Method getter : methods) {
			MIBSubtree subtree = getter.getAnnotation(MIBSubtree.class);
			String subtreeOid = String.format("%s.%s", oid, subtree.oid());
			if(isSubtreeGetter(getter)) {
				Method setter = findSetter(getter, clazz);
				if(setter != null && isSubtreeSetter(setter)) {
					PropertyAccessor accessor = new MethodLevelAccessor(getter, setter);
					putPropertyAccessor(subtreeOid, accessor, propertyAccessors);
					propertyTypes.put(subtreeOid, getter.getReturnType());
					subtrees.put(subtreeOid, subtree);
				}
			}
		}	
		return subtrees;
	}


	private void processTableEntry(MIBTableEntry tableEntry, Class<?> clazz) throws Exception {
		Map<String,MIBVariable> variables = new TreeMap<>(new ObjectIDComparator());

		String oid = tableEntry.oid();
		if(!clazz.isInterface()) {
			variables.putAll(processVariableFields(oid, getAnnotatedFields(clazz).get(MIBVariable.class)));
		}
		variables.putAll(processVariableMethods(oid, getAnnotatedGetters(clazz).get(MIBVariable.class), clazz));
		
		tableEntries.put(oid, variables);
		manageObjectClasses.put(oid, clazz);
	}
	
	private void processScalarGroup(MIBScalarGroup scalarGroup, Class<?> clazz) throws Exception {
		Map<String,MIBVariable> variables = new TreeMap<>(new ObjectIDComparator());

		String oid = scalarGroup.oid();
		if(!clazz.isInterface()) {
			variables.putAll(processVariableFields(oid, getAnnotatedFields(clazz).get(MIBVariable.class)));
		}
		variables.putAll(processVariableMethods(oid, getAnnotatedGetters(clazz).get(MIBVariable.class), clazz));
		
		scalarGroups.put(oid, variables);
		manageObjectClasses.put(oid, clazz);
	}

	private void processObjectGroup(MIBObjectGroup objectGroup, Class<?> clazz) throws Exception {
		Map<String,MIBVariable> variables = new TreeMap<>(new ObjectIDComparator());
		Map<String,MIBTable> tables = new TreeMap<>(new ObjectIDComparator());
		Map<String,MIBSubtree> subtrees = new TreeMap<>(new ObjectIDComparator());

		String oid = objectGroup.oid();
		
		Map<Class<? extends Annotation>, List<Field>> fieldMap = getAnnotatedFields(clazz);
		variables.putAll(processVariableFields(oid, fieldMap.get(MIBVariable.class)));
		tables.putAll(processTableFields(oid, fieldMap.get(MIBTable.class)));
		subtrees.putAll(processSubtreeFields(oid, fieldMap.get(MIBSubtree.class)));

		Map<Class<? extends Annotation>, List<Method>> methodMap = getAnnotatedGetters(clazz);
		variables.putAll(processVariableMethods(oid, methodMap.get(MIBVariable.class), clazz));
		tables.putAll(processTableMethods(oid, methodMap.get(MIBTable.class), clazz));
		subtrees.putAll(processSubtreeMethods(oid, methodMap.get(MIBSubtree.class), clazz));
		
		scalarGroups.put(oid, variables);
		objectGroupsScalars.put(oid, variables);
		objectGroupsTables.put(oid, tables);
		objectGroupsSubtrees.put(oid, subtrees);
		manageObjectClasses.put(oid, clazz);
	}
	
	private void processNotification(MIBNotification notification, Class<?> clazz) throws Exception {
		Map<String,Property> properties = new LinkedHashMap<>();

		String oid = notification.oid();
		if(!Trap.class.isAssignableFrom(clazz)) {
			properties.putAll(processNotificationVariableFields(oid, getAnnotatedFields(Notification.class).get(MIBVariable.class)));
			//variables.putAll(processNotificationVariableMethods(oid, getAnnotatedGetters(clazz).get(MIBVariable.class), clazz));
		}
		properties.putAll(processNotificationVariableFields(oid, getAnnotatedFields(clazz).get(MIBVariable.class)));
		properties.putAll(processNotificationVariableMethods(oid, getAnnotatedGetters(clazz).get(MIBVariable.class), clazz));
		
		notificationClasses.put(oid, clazz);
		notificationMetadata.put(clazz, properties);
	}

	private Method findSetter(Method getter, Class<?> clazz) {
		String getterName = getter.getName();
		Class<?> type = getter.getReturnType();
		String setterName = "";
		if(getterName.startsWith("get")) {
			setterName = "set" + getterName.substring(3);
		}
		else if(getterName.startsWith("is")) {
			setterName = "set" + getterName.substring(2);
		}
		
		try {
			return clazz.getDeclaredMethod(setterName, type);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return null;
	}

	// TODO: needs refactoring or maybe redesign
	abstract class MetadataBuilder implements Serializable {
		
		Stereotype stereotype;
		Class<?> type;
		Map<String, Property> properties = new TreeMap<>(new ObjectIDComparator());
		
		MetadataBuilder(Stereotype stereotype, Class<?> type) {
			this.stereotype = stereotype;
			this.type = type;
		}
		
		abstract MetadataBuilder appendProperty(MIBVariable annotation);
		
		abstract ManagedObjectClass toManagedObjectClass();
	}
	
	final class ScalarGroupMetadataBuilder extends MetadataBuilder {
		
		MIBScalarGroup scalarGroup;
		
		ScalarGroupMetadataBuilder(Class<?> type) {
			super(Stereotype.ScalarGroup, type);
			this.scalarGroup = type.getAnnotation(MIBScalarGroup.class);
		}

		ManagedObjectClass toManagedObjectClass() {
			return new ManagedObjectClass(scalarGroup.oid(), scalarGroup.name(), stereotype, type, properties);
		}

		@Override
		MetadataBuilder appendProperty(MIBVariable annotation) {
			String oid = scalarGroup.oid() + "." + annotation.oid();
			Property prop = new Property(PropertyType.Scalar, getVariableType(oid), annotation);
			properties.put(oid, prop);
			return this;
		}		
	}

	final class TableEntryMetadataBuilder extends MetadataBuilder {
		
		MIBTableEntry tableEntry;
		
		TableEntryMetadataBuilder(Class<?> type) {
			super(Stereotype.TableRow, type);
			this.tableEntry = type.getAnnotation(MIBTableEntry.class);
		}

		ManagedObjectClass toManagedObjectClass() {
			return new ManagedObjectClass(tableEntry.oid(), tableEntry.name(), stereotype, type, properties);
		}

		@Override
		MetadataBuilder appendProperty(MIBVariable annotation) {
			String oid = tableEntry.oid() + "." + annotation.oid();
			Property prop = new Property(PropertyType.Column, getVariableType(oid), annotation);
			properties.put(oid, prop);
			return this;
		}		
	}

	final class ObjectGroupMetadataBuilder extends MetadataBuilder {
		
		MIBObjectGroup objectGroup;
		
		ObjectGroupMetadataBuilder(Class<?> type) {
			super(Stereotype.Subtree, type);
			this.objectGroup = type.getAnnotation(MIBObjectGroup.class);
		}

		ManagedObjectClass toManagedObjectClass() {
			return new ManagedObjectClass(objectGroup.oid(), objectGroup.name(), stereotype, type, properties);
		}

		@Override
		ObjectGroupMetadataBuilder appendProperty(MIBVariable annotation) {
			String oid = objectGroup.oid() + "." + annotation.oid();
			Property prop = new Property(PropertyType.Scalar, getVariableType(oid), annotation);
			properties.put(oid, prop);
			return this;
		}		

		ObjectGroupMetadataBuilder appendProperty(Class<?> clazz2, MIBTable annotation) {
			String oid = objectGroup.oid() + "." + annotation.oid();
			Property prop = new Property(PropertyType.Table, getVariableType(oid), annotation);
			properties.put(oid, prop);
			return this;
		}		

		ObjectGroupMetadataBuilder appendProperty(Class<?> clazz2, MIBSubtree annotation) {
			String oid = objectGroup.oid() + "." + annotation.oid();
			Property prop = new Property(PropertyType.Subtree, getVariableType(oid), annotation);
			properties.put(oid, prop);
			return this;
		}		

	}

}
