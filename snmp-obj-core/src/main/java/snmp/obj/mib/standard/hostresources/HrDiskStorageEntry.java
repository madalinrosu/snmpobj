package snmp.obj.mib.standard.hostresources;

import java.io.Serializable;

import snmp.obj.mib.annotations.MIBTableEntry;
import snmp.obj.mib.annotations.MIBTableIndex;
import snmp.obj.mib.annotations.MIBVariable;
import snmp.obj.mib.constants.TruthValue;

/**
 * 
 * GENERATED FROM MIB. DO NOT CHANGE MANUALLY.
 * 
 */
@MIBTableEntry(oid = "1.3.6.1.2.1.25.3.6.1", 
				indexes = @MIBTableIndex(name = "hrDeviceIndex", tableEntry = HrDeviceEntry.class))
public class HrDiskStorageEntry implements Serializable{

	private static final long serialVersionUID = 1L;

	@MIBVariable(oid = "1", name = "hrDiskStorageAccess", syntax = "INTEGER", readable = true, writeable = false)
	private HrDiskStorageAccess hrDiskStorageAccess;

	@MIBVariable(oid = "2", name = "hrDiskStorageMedia", syntax = "INTEGER", readable = true, writeable = false)
	private HrDiskStorageMedia hrDiskStorageMedia;

	@MIBVariable(oid = "3", name = "hrDiskStorageRemoveble", syntax = "TruthValue", readable = true, writeable = false)
	private TruthValue hrDiskStorageRemoveble;

	@MIBVariable(oid = "4", name = "hrDiskStorageCapacity", syntax = "KBytes", readable = true, writeable = false)
	private Integer hrDiskStorageCapacity;


	
	/**
	 * 
	 * "An indication if this long-term storage device is
	 *         readable and writable or only readable.  This should
	 *         reflect the media type, any write-protect mechanism,
	 *         and any device configuration that affects the entire
	 *         device."
	 * 
	 */
	public HrDiskStorageAccess getHrDiskStorageAccess(){
		return hrDiskStorageAccess;
	}

	private void setHrDiskStorageAccess(HrDiskStorageAccess hrDiskStorageAccess){
		this.hrDiskStorageAccess = hrDiskStorageAccess;
	}

	
	/**
	 * 
	 * "An indication of the type of media used in this long-
	 *         term storage device."
	 * 
	 */
	public HrDiskStorageMedia getHrDiskStorageMedia(){
		return hrDiskStorageMedia;
	}

	private void setHrDiskStorageMedia(HrDiskStorageMedia hrDiskStorageMedia){
		this.hrDiskStorageMedia = hrDiskStorageMedia;
	}

	
	/**
	 * 
	 * "Denotes whether or not the disk media may be removed
	 *         from the drive."
	 * 
	 */
	public TruthValue getHrDiskStorageRemoveble(){
		return hrDiskStorageRemoveble;
	}

	private void setHrDiskStorageRemoveble(TruthValue hrDiskStorageRemoveble){
		this.hrDiskStorageRemoveble = hrDiskStorageRemoveble;
	}

	
	/**
	 * 
	 * "The total size for this long-term storage device. If
	 *         the media is removable and is currently removed, this
	 *         value should be zero."
	 * 
	 */
	public Integer getHrDiskStorageCapacity(){
		return hrDiskStorageCapacity;
	}

	private void setHrDiskStorageCapacity(Integer hrDiskStorageCapacity){
		this.hrDiskStorageCapacity = hrDiskStorageCapacity;
	}


	public enum HrDiskStorageAccess{
		readWrite(1),
		readOnly(2),;

		private final int value;

		private HrDiskStorageAccess(int value){
			this.value = value;
		}

		public int value(){
			return value;
		}

		public static HrDiskStorageAccess fromValue(int value){
			for(HrDiskStorageAccess constant : values()){
				if(constant.value() == value){
					return constant;
				}
			}
			return null;
		}

	}

	public enum HrDiskStorageMedia{
		other(1),
		unknown(2),
		hardDisk(3),
		floppyDisk(4),
		opticalDiskROM(5),
		opticalDiskWORM(6),
		opticalDiskRW(7),
		ramDisk(8),;

		private final int value;

		private HrDiskStorageMedia(int value){
			this.value = value;
		}

		public int value(){
			return value;
		}

		public static HrDiskStorageMedia fromValue(int value){
			for(HrDiskStorageMedia constant : values()){
				if(constant.value() == value){
					return constant;
				}
			}
			return null;
		}

	}

}
