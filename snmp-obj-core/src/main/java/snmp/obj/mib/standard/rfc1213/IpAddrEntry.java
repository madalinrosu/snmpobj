package snmp.obj.mib.standard.rfc1213;

import java.io.Serializable;
import snmp.obj.mib.annotations.*;

/**
 * 
 * GENERATED FROM MIB. DO NOT CHANGE MANUALLY.
 * 
 */
@MIBTableEntry(oid = "1.3.6.1.2.1.4.20.1", indexes = @MIBTableIndex(name = "ipAdEntAddr") )
public class IpAddrEntry implements Serializable{

	private static final long serialVersionUID = 1L;

	@MIBVariable(oid = "1", name = "ipAdEntAddr", syntax = "IpAddress", readable = true, writeable = false)
	private String ipAdEntAddr;

	@MIBVariable(oid = "2", name = "ipAdEntIfIndex", syntax = "INTEGER", readable = true, writeable = false)
	private Integer ipAdEntIfIndex;

	@MIBVariable(oid = "3", name = "ipAdEntNetMask", syntax = "IpAddress", readable = true, writeable = false)
	private String ipAdEntNetMask;

	@MIBVariable(oid = "4", name = "ipAdEntBcastAddr", syntax = "INTEGER", readable = true, writeable = false)
	private Integer ipAdEntBcastAddr;

	@MIBVariable(oid = "5", name = "ipAdEntReasmMaxSize", syntax = "INTEGER", readable = true, writeable = false)
	private Integer ipAdEntReasmMaxSize;


	
	/**
	 * 
	 * "The IP address to which this entry's addressing
	 *             information pertains."
	 * 
	 */
	public String getIpAdEntAddr(){
		return ipAdEntAddr;
	}

	private void setIpAdEntAddr(String ipAdEntAddr){
		this.ipAdEntAddr = ipAdEntAddr;
	}

	
	/**
	 * 
	 * "The index value which uniquely identifies the
	 *             interface to which this entry is applicable.  The
	 *             interface identified by a particular value of this
	 *             index is the same interface as identified by the
	 *             same value of ifIndex."
	 * 
	 */
	public Integer getIpAdEntIfIndex(){
		return ipAdEntIfIndex;
	}

	private void setIpAdEntIfIndex(Integer ipAdEntIfIndex){
		this.ipAdEntIfIndex = ipAdEntIfIndex;
	}

	
	/**
	 * 
	 * "The subnet mask associated with the IP address of
	 *             this entry.  The value of the mask is an IP
	 *             address with all the network bits set to 1 and all
	 *             the hosts bits set to 0."
	 * 
	 */
	public String getIpAdEntNetMask(){
		return ipAdEntNetMask;
	}

	private void setIpAdEntNetMask(String ipAdEntNetMask){
		this.ipAdEntNetMask = ipAdEntNetMask;
	}

	
	/**
	 * 
	 * "The value of the least-significant bit in the IP
	 *             broadcast address used for sending datagrams on
	 *             the (logical) interface associated with the IP
	 *             address of this entry.  For example, when the
	 *             Internet standard all-ones broadcast address is
	 *             used, the value will be 1.  This value applies to
	 *             both the subnet and network broadcasts addresses
	 *             used by the entity on this (logical) interface."
	 * 
	 */
	public Integer getIpAdEntBcastAddr(){
		return ipAdEntBcastAddr;
	}

	private void setIpAdEntBcastAddr(Integer ipAdEntBcastAddr){
		this.ipAdEntBcastAddr = ipAdEntBcastAddr;
	}

	
	/**
	 * 
	 * "The size of the largest IP datagram which this
	 *             entity can re-assemble from incoming IP fragmented
	 *             datagrams received on this interface."
	 * 
	 */
	public Integer getIpAdEntReasmMaxSize(){
		return ipAdEntReasmMaxSize;
	}

	private void setIpAdEntReasmMaxSize(Integer ipAdEntReasmMaxSize){
		this.ipAdEntReasmMaxSize = ipAdEntReasmMaxSize;
	}


}
