package snmp.obj.integrations.snmp4j;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.Target;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.smi.Null;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.Variable;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.util.DefaultPDUFactory;
import org.snmp4j.util.TableEvent;
import org.snmp4j.util.TableUtils;

import snmp.obj.ManagedObjectInstanceException;
import snmp.obj.SNMPException;
import snmp.obj.SNMPSessionSettings;
import snmp.obj.SNMPSessionSupport;
import snmp.obj.config.Configuration;
import snmp.obj.config.PropertyAccessor;
import snmp.obj.dyn.DynamicManagedObjectFactory;
import snmp.obj.integrations.snmp4j.converter.RegisteredConverters;
import snmp.obj.mib.Syntax;
import snmp.obj.mib.annotations.MIBObjectGroup;
import snmp.obj.mib.annotations.MIBScalarGroup;
import snmp.obj.mib.annotations.MIBSubtree;
import snmp.obj.mib.annotations.MIBTable;
import snmp.obj.mib.annotations.MIBTableEntry;
import snmp.obj.mib.annotations.MIBVariable;
import snmp.obj.proxy.ManagedObjectProxy;
import snmp.obj.util.converter.SNMPTypeConverter;


public class SNMPSessionImpl extends SNMPSessionSupport {

	private static final long serialVersionUID = 6917042523733839875L;
	
	private final transient Snmp session;
	private final transient TableUtils tableHelper;
	private final Target target;
	
	public SNMPSessionImpl(final Snmp session, final Target target, final Configuration configuration, SNMPSessionSettings settings) {
		super(configuration, settings);
		this.session = session;
		this.target = target;
		this.tableHelper = new TableUtils(session, new DefaultPDUFactory());
	}
	
	@Override
	public <T extends Serializable> T getScalars(Class<T> clazz) {
		MIBScalarGroup scalarGroup = clazz.getAnnotation(MIBScalarGroup.class);
		if(scalarGroup == null)
			throw new IllegalArgumentException("Class " + clazz.getName() + " is not a scalar group");
		
		Map<String, MIBVariable> variables = configuration.getVariables(scalarGroup);
		List<? extends VariableBinding> variableBindings = getScalars(scalarGroup.oid(), variables);
		try {
			return createManagedObjectInstance(clazz, variableBindings, variables);
		} 
		catch (Exception e) {
			throw new ManagedObjectInstanceException("Failed to create managed object instance - " + clazz.getName(), e);
		}
	}
	
	@Override
	public <T extends Serializable> T getTableRow(Class<T> clazz, String index) {
		MIBTableEntry tableEntry = clazz.getAnnotation(MIBTableEntry.class);
		if(tableEntry == null)
			throw new IllegalArgumentException("Class " + clazz.getName() + " is not a table entry");
		
		Map<String, MIBVariable> variables = configuration.getVariables(tableEntry);
		List<? extends VariableBinding> variableBindings = getTableRow(tableEntry.oid(), index, variables);
		try {
			return createManagedObjectInstance(clazz, variableBindings, variables);
		} 
		catch (Exception e) {
			throw new ManagedObjectInstanceException("Failed to create managed object instance - " + clazz.getName(), e);
		}
	}

	@Override
	public <T extends Serializable> List<T> getTable(Class<T> clazz)  {
		MIBTableEntry tableEntry = clazz.getAnnotation(MIBTableEntry.class);
		if(tableEntry == null)
			throw new IllegalArgumentException("Class " + clazz.getName() + " is not a table entry");
		
		Map<String, MIBVariable> variables = configuration.getVariables(tableEntry);
		List<List<? extends VariableBinding>> resultTable = getTable(tableEntry.oid(), variables);
		
		List<T> instances = new ArrayList<>(resultTable.size());
		for(List<? extends VariableBinding> tableRow : resultTable) {
			T moInstance;
			try {
				moInstance = createManagedObjectInstance(clazz, tableRow, variables);
			} 
			catch (Exception e) {
				throw new ManagedObjectInstanceException("Failed to create managed object instance - " + clazz.getName(), e);
			}
			instances.add(moInstance);
		}
		return instances;
	}

	@Override
	public <T extends Serializable> T getGroup(Class<T> clazz) {
		MIBObjectGroup objectGroup = clazz.getAnnotation(MIBObjectGroup.class);
		if(objectGroup == null)
			throw new IllegalArgumentException("Class " + clazz.getName() + " is not a group (partial subtree)");

		T moInstance = null;

		Map<String, MIBVariable> variables = configuration.getVariables(objectGroup);
		if(variables != null) {
			List<? extends VariableBinding> variableBindings = getScalars(objectGroup.oid(), variables);
			try {
				moInstance = createManagedObjectInstance(clazz, variableBindings, variables);
			} 
			catch (Exception e) {
				throw new ManagedObjectInstanceException("Failed to create managed object instance - " + clazz.getName(), e);
			}
		}
		
		Map<String, MIBTable> tables = configuration.getTables(objectGroup);
		for(Map.Entry<String, MIBTable> entry : tables.entrySet()) {
			String tableOid = entry.getKey();
			MIBTable table = entry.getValue();
			PropertyAccessor accessor = configuration.getPropertyAccessor(tableOid);
			// should be a Collection (List or Set) or a Map
			Class<?> tableHolderClass = configuration.getVariableType(tableOid);
			if(table.lazy() && supportsLazyInitialization()) {
				// create a virtual proxy
				ManagedObjectProxy proxy = getTableProxyFactory().createTableProxy(table.tableEntry(), tableHolderClass, this);
				accessor.setValue(proxy, moInstance);
			}
			else {
				Class<? extends Serializable> tableEntryClass = (Class<? extends Serializable>) table.tableEntry();
				if(configuration.isTableEntry(tableEntryClass)) {
					List<? extends Serializable> rows = getTable(tableEntryClass);
					accessor.setValue(rows, moInstance);
				}
			}
		}
		
		Map<String, MIBSubtree> subtrees = configuration.getSubtries(objectGroup);
		for(Map.Entry<String, MIBSubtree> entry : subtrees.entrySet()) {
			String subtreeOid = entry.getKey();
			MIBSubtree subtree = entry.getValue();
			Class<? extends Serializable> subtreeClass = (Class<? extends Serializable>) configuration.getManagedObjectType(subtreeOid);
			PropertyAccessor accessor = configuration.getPropertyAccessor(subtreeOid);
			if(subtree.lazy() && supportsLazyInitialization()) {
				// create a virtual proxy
				ManagedObjectProxy proxy = getProxyFactory().createProxy(subtreeClass, this);
				accessor.setValue(proxy, moInstance);
			}
			else {
				if(configuration.isScalarGroup(subtreeClass)) {
					Serializable subtreeInstance = getScalars(subtreeClass);
					accessor.setValue(subtreeInstance, moInstance);
				}
				else if(configuration.isObjectGroup(subtreeClass)) {
					Serializable subtreeInstance = getGroup(subtreeClass);
					accessor.setValue(subtreeInstance, moInstance);
				}
			}
		}
		
		return moInstance;
	}


	private List<? extends VariableBinding> getScalars(String baseOid, Map<String, MIBVariable> variables) throws SNMPException {
		PDU request = DefaultPDUFactory.createPDU(target, PDU.GET);
		
		for(MIBVariable scalar : variables.values()) {
			if(scalar.readable()) {
				OID oid = new OID(String.format("%s.%s.0", baseOid, scalar.oid()));
				VariableBinding vb = new VariableBinding(oid);
				request.add(vb);
			}
		}
		ResponseEvent responseEvent = null;
		try {
			responseEvent = session.get(request, target);
		} 
		catch (IOException e) {
			throw new SNMPException(e);
		}
		PDU response = responseEvent.getResponse();
		if(response == null) {
			throw new SNMPException("Request timed out.");
		}
		if(response.getErrorStatus() != PDU.noError) {
			List<? extends VariableBinding> varbinds = response.getVariableBindings();
			throw new SNMPException(response.getErrorStatusText() + " - " + varbinds.get(response.getErrorIndex()).getOid());
		}
		
		return response.getVariableBindings();
	}

	private List<? extends VariableBinding> getTableRow(String baseOid, String index, Map<String, MIBVariable> variables) throws SNMPException {
		PDU request = DefaultPDUFactory.createPDU(target, PDU.GET);
		
		for(MIBVariable column : variables.values()) {
			if(column.readable()) {
				OID oid = new OID(String.format("%s.%s.%s", baseOid, column.oid(), index));
				VariableBinding vb = new VariableBinding(oid);
				request.add(vb);
			}
		}
		ResponseEvent responseEvent = null;
		try {
			responseEvent = session.get(request, target);
		} 
		catch (IOException e) {
			throw new SNMPException(e);
		}
		PDU response = responseEvent.getResponse();
		if(response == null) {
			throw new SNMPException("Request timed out.");
		}
		if(response.getErrorStatus() != PDU.noError) {
			List<? extends VariableBinding> varbinds = response.getVariableBindings();
			throw new SNMPException(response.getErrorStatusText() + " - " + varbinds.get(response.getErrorIndex()).getOid());
		}
		
		return response.getVariableBindings();
	}

	private List<List<? extends VariableBinding>> getTable(String baseOid, Map<String, MIBVariable> variables) throws SNMPException {
		List<List<? extends VariableBinding>> result = new ArrayList<>();
		
		OID columnOIDs[] = new OID[variables.size()]; 
		OID lowerBoundIndex = null;
		OID upperBoundIndex = null;
		int i = 0;
		for(MIBVariable column : variables.values()) {
			if(column.readable()) {
				columnOIDs[i++] = new OID(String.format("%s.%s", baseOid, column.oid()));
			}
		}
		List<TableEvent> response = tableHelper.getTable(target, columnOIDs, lowerBoundIndex, upperBoundIndex);
		
		for(TableEvent tableRow : response) {
			if(!tableRow.isError()) {
				List<VariableBinding> variableBindings = Arrays.asList(tableRow.getColumns());
				result.add(variableBindings);
			}
			else {
				throw new SNMPException(tableRow.getErrorMessage(), tableRow.getException());
			}
		}
		return result;
	}

		
	private <T extends Serializable> T createManagedObjectInstance(Class<T> clazz, List<? extends VariableBinding> variableBindings, 
			Map<String, MIBVariable> variables) throws Exception {
		T managedObject = DynamicManagedObjectFactory.createObject(clazz);
		int indexLength = getIndexLength(clazz, variables);
		
//		if(configuration.isScalarGroup(clazz) || configuration.isObjectGroup(clazz)) {
//			indexLength = 1;
//		}
//		else if(configuration.isTableEntry(clazz)) {
//			MIBTableEntry tableEntry = clazz.getAnnotation(MIBTableEntry.class);
//			MIBTableIndex[] indexes = tableEntry.indexes();
//			for(MIBTableIndex index : indexes) {
//				if(MIBTableIndex.SELF.class.equals(index.tableEntry())) {
//					indexLength += getIndexLength(index, variables);
//				}
//				else {
//					Class<? extends Serializable> clazz2 = index.tableEntry();
//					MIBTableEntry tableEntry2 = clazz2.getAnnotation(MIBTableEntry.class);
//					indexLength += getIndexLength(index, configuration.getVariables(tableEntry2));
//				}
//			}
//		}
		for(VariableBinding vb : variableBindings) {
			if(vb == null) {
				continue;
			}
			OID oid = vb.getOid();
			Variable var = vb.getVariable();
			// skip v2 exceptions
			if(var instanceof Null) {
				continue;
			}
			oid.trim(indexLength);
			MIBVariable mibVariable = variables.get(oid.toString());
			Syntax syntax = Syntax.valueOf(mibVariable.syntax());
			Class<?> type = configuration.getVariableType(oid.toString());
			PropertyAccessor accessor = configuration.getPropertyAccessor(oid.toString());
			SNMPTypeConverter<?, Variable> converter = RegisteredConverters.<Variable>getConverter(syntax, type);
			if(converter != null) {
				Object value = converter.fromVar(var);
				accessor.setValue(value, managedObject);
			}
		}

		return managedObject;
	}
	
	
}
