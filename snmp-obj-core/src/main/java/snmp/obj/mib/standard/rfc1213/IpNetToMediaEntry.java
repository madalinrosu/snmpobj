package snmp.obj.mib.standard.rfc1213;

import java.io.Serializable;
import snmp.obj.mib.annotations.*;

/**
 * 
 * GENERATED FROM MIB. DO NOT CHANGE MANUALLY.
 * 
 */
@MIBTableEntry(oid = "1.3.6.1.2.1.4.22.1", 
				indexes = { @MIBTableIndex(name = "ipNetToMediaIfIndex"), @MIBTableIndex(name = "ipNetToMediaNetAddress") })
public class IpNetToMediaEntry implements Serializable{

	private static final long serialVersionUID = 1L;

	@MIBVariable(oid = "1", name = "ipNetToMediaIfIndex", syntax = "INTEGER", readable = true, writeable = true)
	private Integer ipNetToMediaIfIndex;

	@MIBVariable(oid = "2", name = "ipNetToMediaPhysAddress", syntax = "PhysAddress", readable = true, writeable = true)
	private String ipNetToMediaPhysAddress;

	@MIBVariable(oid = "3", name = "ipNetToMediaNetAddress", syntax = "IpAddress", readable = true, writeable = true)
	private String ipNetToMediaNetAddress;

	@MIBVariable(oid = "4", name = "ipNetToMediaType", syntax = "INTEGER", readable = true, writeable = true)
	private IpNetToMediaType ipNetToMediaType;


	
	/**
	 * 
	 * "The interface on which this entry's equivalence
	 *             is effective.  The interface identified by a
	 *             particular value of this index is the same
	 *             interface as identified by the same value of
	 *             ifIndex."
	 * 
	 */
	public Integer getIpNetToMediaIfIndex(){
		return ipNetToMediaIfIndex;
	}

	public void setIpNetToMediaIfIndex(Integer ipNetToMediaIfIndex){
		this.ipNetToMediaIfIndex = ipNetToMediaIfIndex;
	}

	
	/**
	 * 
	 * "The media-dependent `physical' address."
	 * 
	 */
	public String getIpNetToMediaPhysAddress(){
		return ipNetToMediaPhysAddress;
	}

	public void setIpNetToMediaPhysAddress(String ipNetToMediaPhysAddress){
		this.ipNetToMediaPhysAddress = ipNetToMediaPhysAddress;
	}

	
	/**
	 * 
	 * "The IpAddress corresponding to the media-
	 *             dependent `physical' address."
	 * 
	 */
	public String getIpNetToMediaNetAddress(){
		return ipNetToMediaNetAddress;
	}

	public void setIpNetToMediaNetAddress(String ipNetToMediaNetAddress){
		this.ipNetToMediaNetAddress = ipNetToMediaNetAddress;
	}

	
	/**
	 * 
	 * "The type of mapping.
	 * 
	 *             Setting this object to the value invalid(2) has
	 *             the effect of invalidating the corresponding entry
	 *             in the ipNetToMediaTable.  That is, it effectively
	 *             dissasociates the interface identified with said
	 *             entry from the mapping identified with said entry.
	 *             It is an implementation-specific matter as to
	 *             whether the agent removes an invalidated entry
	 *             from the table.  Accordingly, management stations
	 *             must be prepared to receive tabular information
	 *             from agents that corresponds to entries not
	 *             currently in use.  Proper interpretation of such
	 *             entries requires examination of the relevant
	 *             ipNetToMediaType object."
	 * 
	 */
	public IpNetToMediaType getIpNetToMediaType(){
		return ipNetToMediaType;
	}

	public void setIpNetToMediaType(IpNetToMediaType ipNetToMediaType){
		this.ipNetToMediaType = ipNetToMediaType;
	}


	public enum IpNetToMediaType{
		other(1),
		invalid(2),
		dynamic(3),
		_static(4),;

		private final int value;

		private IpNetToMediaType(int value){
			this.value = value;
		}

		public int value(){
			return value;
		}

		public static IpNetToMediaType fromValue(int value){
			for(IpNetToMediaType constant : values()){
				if(constant.value() == value){
					return constant;
				}
			}
			return null;
		}

	}

}
