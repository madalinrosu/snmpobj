package snmp.obj.mib.standard.rfc1213;

import java.io.Serializable;
import snmp.obj.mib.annotations.*;

/**
 * 
 * GENERATED FROM MIB. DO NOT CHANGE MANUALLY.
 * 
 */
@MIBTableEntry(oid = "1.3.6.1.2.1.7.5.1", 
				indexes = { @MIBTableIndex(name = "udpLocalAddress"), @MIBTableIndex(name = "udpLocalPort") })
public class UdpEntry implements Serializable{

	private static final long serialVersionUID = 1L;

	@MIBVariable(oid = "1", name = "udpLocalAddress", syntax = "IpAddress", readable = true, writeable = false)
	private String udpLocalAddress;

	@MIBVariable(oid = "2", name = "udpLocalPort", syntax = "INTEGER", readable = true, writeable = false)
	private Integer udpLocalPort;


	
	/**
	 * 
	 * "The local IP address for this UDP listener.  In
	 *             the case of a UDP listener which is willing to
	 *             accept datagrams for any IP interface associated
	 *             with the node, the value 0.0.0.0 is used."
	 * 
	 */
	public String getUdpLocalAddress(){
		return udpLocalAddress;
	}

	private void setUdpLocalAddress(String udpLocalAddress){
		this.udpLocalAddress = udpLocalAddress;
	}

	
	/**
	 * 
	 * "The local port number for this UDP listener."
	 * 
	 */
	public Integer getUdpLocalPort(){
		return udpLocalPort;
	}

	private void setUdpLocalPort(Integer udpLocalPort){
		this.udpLocalPort = udpLocalPort;
	}


}
