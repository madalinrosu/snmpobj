package snmp.obj.mib.standard.hostresources;

import java.io.Serializable;

import snmp.obj.mib.annotations.MIBTableEntry;
import snmp.obj.mib.annotations.MIBTableIndex;
import snmp.obj.mib.annotations.MIBVariable;

/**
 * 
 * GENERATED FROM MIB. DO NOT CHANGE MANUALLY.
 * 
 */
@MIBTableEntry(oid = "1.3.6.1.2.1.25.3.2.1", indexes = @MIBTableIndex(name = "hrDeviceIndex") )
public class HrDeviceEntry implements Serializable{

	private static final long serialVersionUID = 1L;

	@MIBVariable(oid = "1", name = "hrDeviceIndex", syntax = "Integer32", readable = true, writeable = false)
	private Integer hrDeviceIndex;

	@MIBVariable(oid = "2", name = "hrDeviceType", syntax = "AutonomousType", readable = true, writeable = false)
	private String hrDeviceType;

	@MIBVariable(oid = "3", name = "hrDeviceDescr", syntax = "DisplayString", readable = true, writeable = false)
	private String hrDeviceDescr;

	@MIBVariable(oid = "4", name = "hrDeviceID", syntax = "ProductID", readable = true, writeable = false)
	private String hrDeviceID;

	@MIBVariable(oid = "5", name = "hrDeviceStatus", syntax = "INTEGER", readable = true, writeable = false)
	private HrDeviceStatus hrDeviceStatus;

	@MIBVariable(oid = "6", name = "hrDeviceErrors", syntax = "Counter32", readable = true, writeable = false)
	private Integer hrDeviceErrors;


	
	/**
	 * 
	 * "A unique value for each device contained by the host.
	 *         The value for each device must remain constant at
	 *         least from one re-initialization of the agent to the
	 *         next re-initialization."
	 * 
	 */
	public Integer getHrDeviceIndex(){
		return hrDeviceIndex;
	}

	private void setHrDeviceIndex(Integer hrDeviceIndex){
		this.hrDeviceIndex = hrDeviceIndex;
	}

	
	/**
	 * 
	 * "An indication of the type of device.
	 * 
	 *         If this value is
	 *         `hrDeviceProcessor { hrDeviceTypes 3 }' then an entry
	 *         exists in the hrProcessorTable which corresponds to
	 *         this device.
	 * 
	 *         If this value is
	 *         `hrDeviceNetwork { hrDeviceTypes 4 }', then an entry
	 *         exists in the hrNetworkTable which corresponds to this
	 *         device.
	 * 
	 *         If this value is
	 *         `hrDevicePrinter { hrDeviceTypes 5 }', then an entry
	 *         exists in the hrPrinterTable which corresponds to this
	 *         device.
	 * 
	 *         If this value is
	 *         `hrDeviceDiskStorage { hrDeviceTypes 6 }', then an
	 *         entry exists in the hrDiskStorageTable which
	 *         corresponds to this device."
	 * 
	 */
	public String getHrDeviceType(){
		return hrDeviceType;
	}

	private void setHrDeviceType(String hrDeviceType){
		this.hrDeviceType = hrDeviceType;
	}

	
	/**
	 * 
	 * "A textual description of this device, including the
	 *         device's manufacturer and revision, and optionally,
	 *         its serial number."
	 * 
	 */
	public String getHrDeviceDescr(){
		return hrDeviceDescr;
	}

	private void setHrDeviceDescr(String hrDeviceDescr){
		this.hrDeviceDescr = hrDeviceDescr;
	}

	
	/**
	 * 
	 * "The product ID for this device."
	 * 
	 */
	public String getHrDeviceID(){
		return hrDeviceID;
	}

	private void setHrDeviceID(String hrDeviceID){
		this.hrDeviceID = hrDeviceID;
	}

	
	/**
	 * 
	 * "The current operational state of the device described
	 *         by this row of the table.  A value unknown(1)
	 *         indicates that the current state of the device is
	 *         unknown.  running(2) indicates that the device is up
	 *         and running and that no unusual error conditions are
	 *         known.  The warning(3) state indicates that agent has
	 *         been informed of an unusual error condition by the
	 *         operational software (e.g., a disk device driver) but
	 *         that the device is still 'operational'.  An example
	 *         would be a high number of soft errors on a disk.  A
	 *         value of testing(4), indicates that the device is not
	 *         available for use because it is in the testing state.
	 *         The state of down(5) is used only when the agent has
	 *         been informed that the device is not available for any
	 *         use."
	 * 
	 */
	public HrDeviceStatus getHrDeviceStatus(){
		return hrDeviceStatus;
	}

	private void setHrDeviceStatus(HrDeviceStatus hrDeviceStatus){
		this.hrDeviceStatus = hrDeviceStatus;
	}

	
	/**
	 * 
	 * "The number of errors detected on this device.  It
	 *         should be noted that as this object has a SYNTAX of
	 *         Counter32, that it does not have a defined initial
	 *         value.  However, it is recommended that this object be
	 *         initialized to zero, even though management stations
	 *         must not depend on such an initialization."
	 * 
	 */
	public Integer getHrDeviceErrors(){
		return hrDeviceErrors;
	}

	private void setHrDeviceErrors(Integer hrDeviceErrors){
		this.hrDeviceErrors = hrDeviceErrors;
	}


	public enum HrDeviceStatus{
		unknown(1),
		running(2),
		warning(3),
		testing(4),
		down(5),;

		private final int value;

		private HrDeviceStatus(int value){
			this.value = value;
		}

		public int value(){
			return value;
		}

		public static HrDeviceStatus fromValue(int value){
			for(HrDeviceStatus constant : values()){
				if(constant.value() == value){
					return constant;
				}
			}
			return null;
		}

	}

}
