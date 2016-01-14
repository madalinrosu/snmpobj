package snmp.obj.integrations.adventnet;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import snmp.obj.ManagedObjectInstanceException;
import snmp.obj.SNMPException;
import snmp.obj.SNMPSessionSettings;
import snmp.obj.SNMPSessionSupport;
import snmp.obj.config.Configuration;
import snmp.obj.config.PropertyAccessor;
import snmp.obj.dyn.DynamicManagedObjectFactory;
import snmp.obj.integrations.adventnet.converter.RegisteredConverters;
import snmp.obj.mib.Syntax;
import snmp.obj.mib.annotations.MIBScalarGroup;
import snmp.obj.mib.annotations.MIBTableEntry;
import snmp.obj.mib.annotations.MIBVariable;
import snmp.obj.util.converter.SNMPTypeConverter;

import com.adventnet.snmp.beans.SnmpTarget;
import com.adventnet.snmp.snmp2.SnmpNull;
import com.adventnet.snmp.snmp2.SnmpOID;
import com.adventnet.snmp.snmp2.SnmpVar;
import com.adventnet.snmp.snmp2.SnmpVarBind;

public class SNMPSessionImpl extends SNMPSessionSupport {

	private final SnmpTarget session; 

	public SNMPSessionImpl(SnmpTarget session, final Configuration configuration, final SNMPSessionSettings settings) {
		super(configuration, settings);
		this.session = session;
	}

	@Override
	public <T extends Serializable> T getScalars(Class<T> clazz) {
		MIBScalarGroup scalarGroup = clazz.getAnnotation(MIBScalarGroup.class);
		if(scalarGroup == null)
			throw new IllegalArgumentException("Class " + clazz.getName() + " is not a scalar group");
		
		Map<String, MIBVariable> variables = configuration.getVariables(scalarGroup);
		List<? extends SnmpVarBind> variableBindings = getScalars(scalarGroup.oid(), variables);
		try {
			return createManagedObjectInstance(clazz, variableBindings, variables);
		} 
		catch (Exception e) {
			throw new ManagedObjectInstanceException("Failed to create managed object instance - " + clazz.getName(), e);
		}
	}
	private List<? extends SnmpVarBind> getScalars(String baseOid, Map<String, MIBVariable> variables) throws SNMPException {
		for(MIBVariable scalar : variables.values()) {
			if(scalar.readable()) {
				session.addObjectID(String.format(".%s.%s.0", baseOid, scalar.oid()));
			}
		}
		SnmpVarBind[] variableBindings = session.snmpGetVariableBindings();
		if(session.getErrorCode() > 0) {
			throw new SNMPException(session.getErrorString());
		}

		return Arrays.asList(variableBindings);
	}

//	@Override
//	public <T extends Serializable> List<T> getTable(Class<T> clazz)  {
//		MIBTableEntry tableEntry = clazz.getAnnotation(MIBTableEntry.class);
//		if(tableEntry == null)
//			throw new IllegalArgumentException("Class " + clazz.getName() + " is not a table entry");
//		
//		Map<String, MIBVariable> variables = configuration.getVariables(tableEntry);
//		List<List<? extends VariableBinding>> resultTable = getTable(tableEntry.oid(), variables);
//		
//		List<T> instances = new ArrayList<>(resultTable.size());
//		for(List<? extends VariableBinding> tableRow : resultTable) {
//			T moInstance;
//			try {
//				moInstance = createManagedObjectInstance(clazz, tableRow, variables);
//			} 
//			catch (Exception e) {
//				throw new ManagedObjectInstanceException("Failed to create managed object instance - " + clazz.getName(), e);
//			}
//			instances.add(moInstance);
//		}
//		return instances;
//	}

	@Override
	public <T extends Serializable> List<T> getTable(final Class<T> clazz) {
		MIBTableEntry tableEntry = clazz.getAnnotation(MIBTableEntry.class);
		if(tableEntry == null)
			throw new IllegalArgumentException("Class " + clazz.getName() + " is not a table entry");

		final Map<String, MIBVariable> variables = configuration.getVariables(tableEntry);
		for(MIBVariable column : variables.values()) {
			if(column.readable()) {
				session.addObjectID(String.format(".%s.%s", tableEntry.oid(), column.oid()));
			}
		}
		SnmpVarBind[][] rows = session.snmpGetAllVariableBindings();
		//session.snmpGetBulkVariableBindings();
		if(session.getErrorCode() > 0) {
			throw new SNMPException(session.getErrorString());
		}
		List<T> instances = new ArrayList<T>(rows.length);
		for(int i = 0; i < rows.length; i++) {
			List<SnmpVarBind> variableBindings = Arrays.asList(rows[i]);
			T moInstance;
			try {
				moInstance = createManagedObjectInstance(clazz, variableBindings, variables);
			} 
			catch (Exception e) {
				throw new ManagedObjectInstanceException("Failed to create managed object instance - " + clazz.getName(), e);
			}
			instances.add(moInstance);
		}
		
		/*
		session.addSnmpTableListener(new SnmpTableListener(){

			@Override
			public void tableChanged(SnmpTableEvent event) {
				SnmpTable table = (SnmpTable)event.getSource();
				if( event.isEndOfTable() || event.getType() == 2){
					if (table.getRowCount() == 0) {
						// no rows and we're being notified
						System.err.println(table.getErrorString());
					}
					return;
				}
				// print the rows we're getting
				for (int j = event.getFirstRow(); j <= event.getLastRow(); j++) { 
					List<SnmpVarBind> variableBindings = Arrays.asList(table.getRow(j));
					T moInstance;
					try {
						moInstance = createManagedObjectInstance(clazz, variableBindings, variables);
						instances.add(moInstance);
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			} 			

		});
		session.setTableOID("." + tableEntry.oid());
		*/
		return instances;
	}
	
	@Override
	public <T extends Serializable> T getTableRow(Class<T> clazz, String index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends Serializable> T getGroup(Class<T> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	private <T extends Serializable> T createManagedObjectInstance(Class<T> clazz, List<? extends SnmpVarBind> variableBindings, 
			Map<String, MIBVariable> variables) throws Exception {
		T managedObject = DynamicManagedObjectFactory.createObject(clazz);
		int indexLength = getIndexLength(clazz, variables);

		for(SnmpVarBind vb : variableBindings) {
			if(vb == null) {
				continue;
			}
			SnmpOID oid = vb.getObjectID();
			SnmpVar var = vb.getVariable();
			// skip v2 exceptions
			if(var instanceof SnmpNull) {
				continue;
			}
			//oid.trim(indexLength);
			int[] oidArray = oid.toIntArray();
			oid = new SnmpOID(Arrays.copyOf(oidArray, oidArray.length - indexLength));
			
			String oidString = oid.toString().replaceFirst(".", "");
			MIBVariable mibVariable = variables.get(oidString);
			Syntax syntax = Syntax.valueOf(mibVariable.syntax());
			Class<?> type = configuration.getVariableType(oidString);
			PropertyAccessor accessor = configuration.getPropertyAccessor(oidString);
			SNMPTypeConverter<?, SnmpVar> converter = RegisteredConverters.<SnmpVar>getConverter(syntax, type);
			if(converter != null) {
				Object value = converter.fromVar(var);
				accessor.setValue(value, managedObject);
			}
		}

		return managedObject;
	}

}
