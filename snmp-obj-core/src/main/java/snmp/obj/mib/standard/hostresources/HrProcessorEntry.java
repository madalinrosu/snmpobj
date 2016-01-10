package snmp.obj.mib.standard.hostresources;

import java.io.Serializable;
import snmp.obj.mib.annotations.*;

/**
 * 
 * GENERATED FROM MIB. DO NOT CHANGE MANUALLY.
 * 
 */
@MIBTableEntry(oid = "1.3.6.1.2.1.25.3.3.1", 
				indexes = @MIBTableIndex(name = "hrDeviceIndex", tableEntry = HrDeviceEntry.class))
public class HrProcessorEntry implements Serializable{

	private static final long serialVersionUID = 1L;

	@MIBVariable(oid = "1", name = "hrProcessorFrwID", syntax = "ProductID", readable = true, writeable = false)
	private String hrProcessorFrwID;

	@MIBVariable(oid = "2", name = "hrProcessorLoad", syntax = "Integer32", readable = true, writeable = false)
	private Integer hrProcessorLoad;


	
	/**
	 * 
	 * "The product ID of the firmware associated with the
	 *         processor."
	 * 
	 */
	public String getHrProcessorFrwID(){
		return hrProcessorFrwID;
	}

	private void setHrProcessorFrwID(String hrProcessorFrwID){
		this.hrProcessorFrwID = hrProcessorFrwID;
	}

	
	/**
	 * 
	 * "The average, over the last minute, of the percentage
	 *         of time that this processor was not idle.
	 *         Implementations may approximate this one minute
	 *         smoothing period if necessary."
	 * 
	 */
	public Integer getHrProcessorLoad(){
		return hrProcessorLoad;
	}

	private void setHrProcessorLoad(Integer hrProcessorLoad){
		this.hrProcessorLoad = hrProcessorLoad;
	}


}
