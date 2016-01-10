package snmp.obj.mib.standard.rfc1213;

import java.io.Serializable;
import java.util.List;

import snmp.obj.mib.annotations.*;

/**
 * 
 * GENERATED FROM MIB. DO NOT CHANGE MANUALLY.
 * 
 */
@MIBObjectGroup(oid = "1.3.6.1.2.1.4")
public class Ip implements Serializable{

	private static final long serialVersionUID = 1L;

	@MIBVariable(oid = "1", name = "ipForwarding", syntax = "INTEGER", readable = true, writeable = true)
	private IpForwarding ipForwarding;

	@MIBVariable(oid = "2", name = "ipDefaultTTL", syntax = "INTEGER", readable = true, writeable = true)
	private Integer ipDefaultTTL;

	@MIBVariable(oid = "3", name = "ipInReceives", syntax = "Counter", readable = true, writeable = false)
	private Integer ipInReceives;

	@MIBVariable(oid = "4", name = "ipInHdrErrors", syntax = "Counter", readable = true, writeable = false)
	private Integer ipInHdrErrors;

	@MIBVariable(oid = "5", name = "ipInAddrErrors", syntax = "Counter", readable = true, writeable = false)
	private Integer ipInAddrErrors;

	@MIBVariable(oid = "6", name = "ipForwDatagrams", syntax = "Counter", readable = true, writeable = false)
	private Integer ipForwDatagrams;

	@MIBVariable(oid = "7", name = "ipInUnknownProtos", syntax = "Counter", readable = true, writeable = false)
	private Integer ipInUnknownProtos;

	@MIBVariable(oid = "8", name = "ipInDiscards", syntax = "Counter", readable = true, writeable = false)
	private Integer ipInDiscards;

	@MIBVariable(oid = "9", name = "ipInDelivers", syntax = "Counter", readable = true, writeable = false)
	private Integer ipInDelivers;

	@MIBVariable(oid = "10", name = "ipOutRequests", syntax = "Counter", readable = true, writeable = false)
	private Integer ipOutRequests;

	@MIBVariable(oid = "11", name = "ipOutDiscards", syntax = "Counter", readable = true, writeable = false)
	private Integer ipOutDiscards;

	@MIBVariable(oid = "12", name = "ipOutNoRoutes", syntax = "Counter", readable = true, writeable = false)
	private Integer ipOutNoRoutes;

	@MIBVariable(oid = "13", name = "ipReasmTimeout", syntax = "INTEGER", readable = true, writeable = false)
	private Integer ipReasmTimeout;

	@MIBVariable(oid = "14", name = "ipReasmReqds", syntax = "Counter", readable = true, writeable = false)
	private Integer ipReasmReqds;

	@MIBVariable(oid = "15", name = "ipReasmOKs", syntax = "Counter", readable = true, writeable = false)
	private Integer ipReasmOKs;

	@MIBVariable(oid = "16", name = "ipReasmFails", syntax = "Counter", readable = true, writeable = false)
	private Integer ipReasmFails;

	@MIBVariable(oid = "17", name = "ipFragOKs", syntax = "Counter", readable = true, writeable = false)
	private Integer ipFragOKs;

	@MIBVariable(oid = "18", name = "ipFragFails", syntax = "Counter", readable = true, writeable = false)
	private Integer ipFragFails;

	@MIBVariable(oid = "19", name = "ipFragCreates", syntax = "Counter", readable = true, writeable = false)
	private Integer ipFragCreates;

	@MIBVariable(oid = "23", name = "ipRoutingDiscards", syntax = "Counter", readable = true, writeable = false)
	private Integer ipRoutingDiscards;


	@MIBTable(oid = "20", lazy = true, tableEntry = IpAddrEntry.class)
	private List<IpAddrEntry> ipAddrTable;

	@MIBTable(oid = "21", lazy = true, tableEntry = IpRouteEntry.class)
	private List<IpRouteEntry> ipRouteTable;
	
	@MIBTable(oid = "22", lazy = true, tableEntry = IpNetToMediaEntry.class)
	private List<IpNetToMediaEntry> ipNetToMediaTable;

	/**
	 * 
	 * "The indication of whether this entity is acting
	 *             as an IP gateway in respect to the forwarding of
	 *             datagrams received by, but not addressed to, this
	 *             entity.  IP gateways forward datagrams.  IP hosts
	 *             do not (except those source-routed via the host).
	 * 
	 *             Note that for some managed nodes, this object may
	 *             take on only a subset of the values possible.
	 *             Accordingly, it is appropriate for an agent to
	 *             return a `badValue' response if a management
	 *             station attempts to change this object to an
	 *             inappropriate value."
	 * 
	 */
	public IpForwarding getIpForwarding(){
		return ipForwarding;
	}

	public void setIpForwarding(IpForwarding ipForwarding){
		this.ipForwarding = ipForwarding;
	}

	
	/**
	 * 
	 * "The default value inserted into the Time-To-Live
	 *             field of the IP header of datagrams originated at
	 *             this entity, whenever a TTL value is not supplied
	 *             by the transport layer protocol."
	 * 
	 */
	public Integer getIpDefaultTTL(){
		return ipDefaultTTL;
	}

	public void setIpDefaultTTL(Integer ipDefaultTTL){
		this.ipDefaultTTL = ipDefaultTTL;
	}

	
	/**
	 * 
	 * "The total number of input datagrams received from
	 *             interfaces, including those received in error."
	 * 
	 */
	public Integer getIpInReceives(){
		return ipInReceives;
	}

	private void setIpInReceives(Integer ipInReceives){
		this.ipInReceives = ipInReceives;
	}

	
	/**
	 * 
	 * "The number of input datagrams discarded due to
	 *             errors in their IP headers, including bad
	 *             checksums, version number mismatch, other format
	 *             errors, time-to-live exceeded, errors discovered
	 *             in processing their IP options, etc."
	 * 
	 */
	public Integer getIpInHdrErrors(){
		return ipInHdrErrors;
	}

	private void setIpInHdrErrors(Integer ipInHdrErrors){
		this.ipInHdrErrors = ipInHdrErrors;
	}

	
	/**
	 * 
	 * "The number of input datagrams discarded because
	 *             the IP address in their IP header's destination
	 *             field was not a valid address to be received at
	 *             this entity.  This count includes invalid
	 *             addresses (e.g., 0.0.0.0) and addresses of
	 *             unsupported Classes (e.g., Class E).  For entities
	 *             which are not IP Gateways and therefore do not
	 *             forward datagrams, this counter includes datagrams
	 *             discarded because the destination address was not
	 *             a local address."
	 * 
	 */
	public Integer getIpInAddrErrors(){
		return ipInAddrErrors;
	}

	private void setIpInAddrErrors(Integer ipInAddrErrors){
		this.ipInAddrErrors = ipInAddrErrors;
	}

	
	/**
	 * 
	 * "The number of input datagrams for which this
	 *             entity was not their final IP destination, as a
	 *             result of which an attempt was made to find a
	 *             route to forward them to that final destination.
	 *             In entities which do not act as IP Gateways, this
	 *             counter will include only those packets which were
	 *             Source-Routed via this entity, and the Source-
	 *             Route option processing was successful."
	 * 
	 */
	public Integer getIpForwDatagrams(){
		return ipForwDatagrams;
	}

	private void setIpForwDatagrams(Integer ipForwDatagrams){
		this.ipForwDatagrams = ipForwDatagrams;
	}

	
	/**
	 * 
	 * "The number of locally-addressed datagrams
	 *             received successfully but discarded because of an
	 *             unknown or unsupported protocol."
	 * 
	 */
	public Integer getIpInUnknownProtos(){
		return ipInUnknownProtos;
	}

	private void setIpInUnknownProtos(Integer ipInUnknownProtos){
		this.ipInUnknownProtos = ipInUnknownProtos;
	}

	
	/**
	 * 
	 * "The number of input IP datagrams for which no
	 *             problems were encountered to prevent their
	 *             continued processing, but which were discarded
	 *             (e.g., for lack of buffer space).  Note that this
	 *             counter does not include any datagrams discarded
	 *             while awaiting re-assembly."
	 * 
	 */
	public Integer getIpInDiscards(){
		return ipInDiscards;
	}

	private void setIpInDiscards(Integer ipInDiscards){
		this.ipInDiscards = ipInDiscards;
	}

	
	/**
	 * 
	 * "The total number of input datagrams successfully
	 *             delivered to IP user-protocols (including ICMP)."
	 * 
	 */
	public Integer getIpInDelivers(){
		return ipInDelivers;
	}

	private void setIpInDelivers(Integer ipInDelivers){
		this.ipInDelivers = ipInDelivers;
	}

	
	/**
	 * 
	 * "The total number of IP datagrams which local IP
	 *             user-protocols (including ICMP) supplied to IP in
	 *             requests for transmission.  Note that this counter
	 *             does not include any datagrams counted in
	 *             ipForwDatagrams."
	 * 
	 */
	public Integer getIpOutRequests(){
		return ipOutRequests;
	}

	private void setIpOutRequests(Integer ipOutRequests){
		this.ipOutRequests = ipOutRequests;
	}

	
	/**
	 * 
	 * "The number of output IP datagrams for which no
	 *             problem was encountered to prevent their
	 *             transmission to their destination, but which were
	 *             discarded (e.g., for lack of buffer space).  Note
	 *             that this counter would include datagrams counted
	 *             in ipForwDatagrams if any such packets met this
	 *             (discretionary) discard criterion."
	 * 
	 */
	public Integer getIpOutDiscards(){
		return ipOutDiscards;
	}

	private void setIpOutDiscards(Integer ipOutDiscards){
		this.ipOutDiscards = ipOutDiscards;
	}

	
	/**
	 * 
	 * "The number of IP datagrams discarded because no
	 *             route could be found to transmit them to their
	 *             destination.  Note that this counter includes any
	 *             packets counted in ipForwDatagrams which meet this
	 *             `no-route' criterion.  Note that this includes any
	 *             datagarms which a host cannot route because all of
	 *             its default gateways are down."
	 * 
	 */
	public Integer getIpOutNoRoutes(){
		return ipOutNoRoutes;
	}

	private void setIpOutNoRoutes(Integer ipOutNoRoutes){
		this.ipOutNoRoutes = ipOutNoRoutes;
	}

	
	/**
	 * 
	 * "The maximum number of seconds which received
	 *             fragments are held while they are awaiting
	 *             reassembly at this entity."
	 * 
	 */
	public Integer getIpReasmTimeout(){
		return ipReasmTimeout;
	}

	private void setIpReasmTimeout(Integer ipReasmTimeout){
		this.ipReasmTimeout = ipReasmTimeout;
	}

	
	/**
	 * 
	 * "The number of IP fragments received which needed
	 *             to be reassembled at this entity."
	 * 
	 */
	public Integer getIpReasmReqds(){
		return ipReasmReqds;
	}

	private void setIpReasmReqds(Integer ipReasmReqds){
		this.ipReasmReqds = ipReasmReqds;
	}

	
	/**
	 * 
	 * "The number of IP datagrams successfully re-
	 *             assembled."
	 * 
	 */
	public Integer getIpReasmOKs(){
		return ipReasmOKs;
	}

	private void setIpReasmOKs(Integer ipReasmOKs){
		this.ipReasmOKs = ipReasmOKs;
	}

	
	/**
	 * 
	 * "The number of failures detected by the IP re-
	 *             assembly algorithm (for whatever reason: timed
	 *             out, errors, etc).  Note that this is not
	 *             necessarily a count of discarded IP fragments
	 *             since some algorithms (notably the algorithm in
	 *             RFC 815) can lose track of the number of fragments
	 *             by combining them as they are received."
	 * 
	 */
	public Integer getIpReasmFails(){
		return ipReasmFails;
	}

	private void setIpReasmFails(Integer ipReasmFails){
		this.ipReasmFails = ipReasmFails;
	}

	
	/**
	 * 
	 * "The number of IP datagrams that have been
	 *             successfully fragmented at this entity."
	 * 
	 */
	public Integer getIpFragOKs(){
		return ipFragOKs;
	}

	private void setIpFragOKs(Integer ipFragOKs){
		this.ipFragOKs = ipFragOKs;
	}

	
	/**
	 * 
	 * "The number of IP datagrams that have been
	 *             discarded because they needed to be fragmented at
	 *             this entity but could not be, e.g., because their
	 *             Don't Fragment flag was set."
	 * 
	 */
	public Integer getIpFragFails(){
		return ipFragFails;
	}

	private void setIpFragFails(Integer ipFragFails){
		this.ipFragFails = ipFragFails;
	}

	
	/**
	 * 
	 * "The number of IP datagram fragments that have
	 *             been generated as a result of fragmentation at
	 *             this entity."
	 * 
	 */
	public Integer getIpFragCreates(){
		return ipFragCreates;
	}

	private void setIpFragCreates(Integer ipFragCreates){
		this.ipFragCreates = ipFragCreates;
	}

	
	/**
	 * 
	 * "The number of routing entries which were chosen
	 *             to be discarded even though they are valid.  One
	 *             possible reason for discarding such an entry could
	 *             be to free-up buffer space for other routing
	 *             entries."
	 * 
	 */
	public Integer getIpRoutingDiscards(){
		return ipRoutingDiscards;
	}

	private void setIpRoutingDiscards(Integer ipRoutingDiscards){
		this.ipRoutingDiscards = ipRoutingDiscards;
	}


	public enum IpForwarding{
		forwarding(1),
		notforwarding(2),;

		private final int value;

		private IpForwarding(int value){
			this.value = value;
		}

		public int value(){
			return value;
		}

		public static IpForwarding fromValue(int value){
			for(IpForwarding constant : values()){
				if(constant.value() == value){
					return constant;
				}
			}
			return null;
		}

	}


	public List<IpAddrEntry> getIpAddrTable() {
		return ipAddrTable;
	}

	public void setIpAddrTable(List<IpAddrEntry> ipAddrTable) {
		this.ipAddrTable = ipAddrTable;
	}

	public List<IpRouteEntry> getIpRouteTable() {
		return ipRouteTable;
	}

	public void setIpRouteTable(List<IpRouteEntry> ipRouteTable) {
		this.ipRouteTable = ipRouteTable;
	}

	public List<IpNetToMediaEntry> getIpNetToMediaTable() {
		return ipNetToMediaTable;
	}

	public void setIpNetToMediaTable(List<IpNetToMediaEntry> ipNetToMediaTable) {
		this.ipNetToMediaTable = ipNetToMediaTable;
	}

	
}
