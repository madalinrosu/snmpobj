package snmp.obj.mib.standard.rfc1213;

import java.io.Serializable;
import snmp.obj.mib.annotations.*;

/**
 * 
 * GENERATED FROM MIB. DO NOT CHANGE MANUALLY.
 * 
 */
@MIBTableEntry(oid = "1.3.6.1.2.1.4.21.1", indexes = @MIBTableIndex(name = "ipRouteDest") )
public class IpRouteEntry implements Serializable{

	private static final long serialVersionUID = 1L;

	@MIBVariable(oid = "1", name = "ipRouteDest", syntax = "IpAddress", readable = true, writeable = true)
	private String ipRouteDest;

	@MIBVariable(oid = "2", name = "ipRouteIfIndex", syntax = "INTEGER", readable = true, writeable = true)
	private Integer ipRouteIfIndex;

	@MIBVariable(oid = "3", name = "ipRouteMetric1", syntax = "INTEGER", readable = true, writeable = true)
	private Integer ipRouteMetric1;

	@MIBVariable(oid = "4", name = "ipRouteMetric2", syntax = "INTEGER", readable = true, writeable = true)
	private Integer ipRouteMetric2;

	@MIBVariable(oid = "5", name = "ipRouteMetric3", syntax = "INTEGER", readable = true, writeable = true)
	private Integer ipRouteMetric3;

	@MIBVariable(oid = "6", name = "ipRouteMetric4", syntax = "INTEGER", readable = true, writeable = true)
	private Integer ipRouteMetric4;

	@MIBVariable(oid = "7", name = "ipRouteNextHop", syntax = "IpAddress", readable = true, writeable = true)
	private String ipRouteNextHop;

	@MIBVariable(oid = "8", name = "ipRouteType", syntax = "INTEGER", readable = true, writeable = true)
	private IpRouteType ipRouteType;

	@MIBVariable(oid = "9", name = "ipRouteProto", syntax = "INTEGER", readable = true, writeable = false)
	private IpRouteProto ipRouteProto;

	@MIBVariable(oid = "10", name = "ipRouteAge", syntax = "INTEGER", readable = true, writeable = true)
	private Integer ipRouteAge;

	@MIBVariable(oid = "11", name = "ipRouteMask", syntax = "IpAddress", readable = true, writeable = true)
	private String ipRouteMask;

	@MIBVariable(oid = "12", name = "ipRouteMetric5", syntax = "INTEGER", readable = true, writeable = true)
	private Integer ipRouteMetric5;

	@MIBVariable(oid = "13", name = "ipRouteInfo", syntax = "OBJECT_IDENTIFIER", readable = true, writeable = false)
	private String ipRouteInfo;


	
	/**
	 * 
	 * "The destination IP address of this route.  An
	 *             entry with a value of 0.0.0.0 is considered a
	 *             default route.  Multiple routes to a single
	 *             destination can appear in the table, but access to
	 *             such multiple entries is dependent on the table-
	 *             access mechanisms defined by the network
	 *             management protocol in use."
	 * 
	 */
	public String getIpRouteDest(){
		return ipRouteDest;
	}

	public void setIpRouteDest(String ipRouteDest){
		this.ipRouteDest = ipRouteDest;
	}

	
	/**
	 * 
	 * "The index value which uniquely identifies the
	 *             local interface through which the next hop of this
	 *             route should be reached.  The interface identified
	 *             by a particular value of this index is the same
	 *             interface as identified by the same value of
	 *             ifIndex."
	 * 
	 */
	public Integer getIpRouteIfIndex(){
		return ipRouteIfIndex;
	}

	public void setIpRouteIfIndex(Integer ipRouteIfIndex){
		this.ipRouteIfIndex = ipRouteIfIndex;
	}

	
	/**
	 * 
	 * "The primary routing metric for this route.  The
	 *             semantics of this metric are determined by the
	 *             routing-protocol specified in the route's
	 *             ipRouteProto value.  If this metric is not used,
	 *             its value should be set to -1."
	 * 
	 */
	public Integer getIpRouteMetric1(){
		return ipRouteMetric1;
	}

	public void setIpRouteMetric1(Integer ipRouteMetric1){
		this.ipRouteMetric1 = ipRouteMetric1;
	}

	
	/**
	 * 
	 * "An alternate routing metric for this route.  The
	 *             semantics of this metric are determined by the
	 *             routing-protocol specified in the route's
	 *             ipRouteProto value.  If this metric is not used,
	 *             its value should be set to -1."
	 * 
	 */
	public Integer getIpRouteMetric2(){
		return ipRouteMetric2;
	}

	public void setIpRouteMetric2(Integer ipRouteMetric2){
		this.ipRouteMetric2 = ipRouteMetric2;
	}

	
	/**
	 * 
	 * "An alternate routing metric for this route.  The
	 *             semantics of this metric are determined by the
	 *             routing-protocol specified in the route's
	 *             ipRouteProto value.  If this metric is not used,
	 *             its value should be set to -1."
	 * 
	 */
	public Integer getIpRouteMetric3(){
		return ipRouteMetric3;
	}

	public void setIpRouteMetric3(Integer ipRouteMetric3){
		this.ipRouteMetric3 = ipRouteMetric3;
	}

	
	/**
	 * 
	 * "An alternate routing metric for this route.  The
	 *             semantics of this metric are determined by the
	 *             routing-protocol specified in the route's
	 *             ipRouteProto value.  If this metric is not used,
	 *             its value should be set to -1."
	 * 
	 */
	public Integer getIpRouteMetric4(){
		return ipRouteMetric4;
	}

	public void setIpRouteMetric4(Integer ipRouteMetric4){
		this.ipRouteMetric4 = ipRouteMetric4;
	}

	
	/**
	 * 
	 * "The IP address of the next hop of this route.
	 *             (In the case of a route bound to an interface
	 *             which is realized via a broadcast media, the value
	 *             of this field is the agent's IP address on that
	 *             interface.)"
	 * 
	 */
	public String getIpRouteNextHop(){
		return ipRouteNextHop;
	}

	public void setIpRouteNextHop(String ipRouteNextHop){
		this.ipRouteNextHop = ipRouteNextHop;
	}

	
	/**
	 * 
	 * "The type of route.  Note that the values
	 *             direct(3) and indirect(4) refer to the notion of
	 *             direct and indirect routing in the IP
	 *             architecture.
	 * 
	 *             Setting this object to the value invalid(2) has
	 *             the effect of invalidating the corresponding entry
	 *             in the ipRouteTable object.  That is, it
	 *             effectively dissasociates the destination
	 *             identified with said entry from the route
	 *             identified with said entry.  It is an
	 *             implementation-specific matter as to whether the
	 *             agent removes an invalidated entry from the table.
	 *             Accordingly, management stations must be prepared
	 *             to receive tabular information from agents that
	 *             corresponds to entries not currently in use.
	 *             Proper interpretation of such entries requires
	 *             examination of the relevant ipRouteType object."
	 * 
	 */
	public IpRouteType getIpRouteType(){
		return ipRouteType;
	}

	public void setIpRouteType(IpRouteType ipRouteType){
		this.ipRouteType = ipRouteType;
	}

	
	/**
	 * 
	 * "The routing mechanism via which this route was
	 *             learned.  Inclusion of values for gateway routing
	 *             protocols is not intended to imply that hosts
	 *             should support those protocols."
	 * 
	 */
	public IpRouteProto getIpRouteProto(){
		return ipRouteProto;
	}

	private void setIpRouteProto(IpRouteProto ipRouteProto){
		this.ipRouteProto = ipRouteProto;
	}

	
	/**
	 * 
	 * "The number of seconds since this route was last
	 *             updated or otherwise determined to be correct.
	 *             Note that no semantics of `too old' can be implied
	 *             except through knowledge of the routing protocol
	 *             by which the route was learned."
	 * 
	 */
	public Integer getIpRouteAge(){
		return ipRouteAge;
	}

	public void setIpRouteAge(Integer ipRouteAge){
		this.ipRouteAge = ipRouteAge;
	}

	
	/**
	 * 
	 * "Indicate the mask to be logical-ANDed with the
	 *             destination address before being compared to the
	 *             value in the ipRouteDest field.  For those systems
	 *             that do not support arbitrary subnet masks, an
	 *             agent constructs the value of the ipRouteMask by
	 *             determining whether the value of the correspondent
	 *             ipRouteDest field belong to a class-A, B, or C
	 *             network, and then using one of:
	 * 
	 *                  mask           network
	 *                  255.0.0.0      class-A
	 *                  255.255.0.0    class-B
	 *                  255.255.255.0  class-C
	 *             If the value of the ipRouteDest is 0.0.0.0 (a
	 *             default route), then the mask value is also
	 *             0.0.0.0.  It should be noted that all IP routing
	 *             subsystems implicitly use this mechanism."
	 * 
	 */
	public String getIpRouteMask(){
		return ipRouteMask;
	}

	public void setIpRouteMask(String ipRouteMask){
		this.ipRouteMask = ipRouteMask;
	}

	
	/**
	 * 
	 * "An alternate routing metric for this route.  The
	 *             semantics of this metric are determined by the
	 *             routing-protocol specified in the route's
	 *             ipRouteProto value.  If this metric is not used,
	 *             its value should be set to -1."
	 * 
	 */
	public Integer getIpRouteMetric5(){
		return ipRouteMetric5;
	}

	public void setIpRouteMetric5(Integer ipRouteMetric5){
		this.ipRouteMetric5 = ipRouteMetric5;
	}

	
	/**
	 * 
	 * "A reference to MIB definitions specific to the
	 *             particular routing protocol which is responsible
	 *             for this route, as determined by the value
	 *             specified in the route's ipRouteProto value.  If
	 *             this information is not present, its value should
	 *             be set to the OBJECT IDENTIFIER { 0 0 }, which is
	 *             a syntatically valid object identifier, and any
	 *             conformant implementation of ASN.1 and BER must be
	 *             able to generate and recognize this value."
	 * 
	 */
	public String getIpRouteInfo(){
		return ipRouteInfo;
	}

	private void setIpRouteInfo(String ipRouteInfo){
		this.ipRouteInfo = ipRouteInfo;
	}


	public enum IpRouteType{
		other(1),
		invalid(2),
		direct(3),
		indirect(4),;

		private final int value;

		private IpRouteType(int value){
			this.value = value;
		}

		public int value(){
			return value;
		}

		public static IpRouteType fromValue(int value){
			for(IpRouteType constant : values()){
				if(constant.value() == value){
					return constant;
				}
			}
			return null;
		}

	}

	public enum IpRouteProto{
		other(1),
		local(2),
		netmgmt(3),
		icmp(4),
		egp(5),
		ggp(6),
		hello(7),
		rip(8),
		isis(9),
		esis(10),
		ciscoIgrp(11),
		bbnSpfIgp(12),
		ospf(13),
		bgp(14),;

		private final int value;

		private IpRouteProto(int value){
			this.value = value;
		}

		public int value(){
			return value;
		}

		public static IpRouteProto fromValue(int value){
			for(IpRouteProto constant : values()){
				if(constant.value() == value){
					return constant;
				}
			}
			return null;
		}

	}

}
