package snmp.obj.mib.standard.rfc1213;

import java.io.Serializable;
import snmp.obj.mib.annotations.*;

/**
 * 
 * GENERATED FROM MIB. DO NOT CHANGE MANUALLY.
 * 
 */
@MIBScalarGroup(oid = "1.3.6.1.2.1.7")
public class Udp implements Serializable{

	private static final long serialVersionUID = 1L;

	@MIBVariable(oid = "1", name = "udpInDatagrams", syntax = "Counter", readable = true, writeable = false)
	private Integer udpInDatagrams;

	@MIBVariable(oid = "2", name = "udpNoPorts", syntax = "Counter", readable = true, writeable = false)
	private Integer udpNoPorts;

	@MIBVariable(oid = "3", name = "udpInErrors", syntax = "Counter", readable = true, writeable = false)
	private Integer udpInErrors;

	@MIBVariable(oid = "4", name = "udpOutDatagrams", syntax = "Counter", readable = true, writeable = false)
	private Integer udpOutDatagrams;


	
	/**
	 * 
	 * "The total number of UDP datagrams delivered to
	 *             UDP users."
	 * 
	 */
	public Integer getUdpInDatagrams(){
		return udpInDatagrams;
	}

	private void setUdpInDatagrams(Integer udpInDatagrams){
		this.udpInDatagrams = udpInDatagrams;
	}

	
	/**
	 * 
	 * "The total number of received UDP datagrams for
	 *             which there was no application at the destination
	 *             port."
	 * 
	 */
	public Integer getUdpNoPorts(){
		return udpNoPorts;
	}

	private void setUdpNoPorts(Integer udpNoPorts){
		this.udpNoPorts = udpNoPorts;
	}

	
	/**
	 * 
	 * "The number of received UDP datagrams that could
	 *             not be delivered for reasons other than the lack
	 *             of an application at the destination port."
	 * 
	 */
	public Integer getUdpInErrors(){
		return udpInErrors;
	}

	private void setUdpInErrors(Integer udpInErrors){
		this.udpInErrors = udpInErrors;
	}

	
	/**
	 * 
	 * "The total number of UDP datagrams sent from this
	 *             entity."
	 * 
	 */
	public Integer getUdpOutDatagrams(){
		return udpOutDatagrams;
	}

	private void setUdpOutDatagrams(Integer udpOutDatagrams){
		this.udpOutDatagrams = udpOutDatagrams;
	}


}
