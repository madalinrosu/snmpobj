package snmp.obj.mib.standard.rfc1213;

import java.io.Serializable;
import snmp.obj.mib.annotations.*;

/**
 * 
 * GENERATED FROM MIB. DO NOT CHANGE MANUALLY.
 * 
 */
@MIBScalarGroup(oid = "1.3.6.1.2.1.5")
public class Icmp implements Serializable{

	private static final long serialVersionUID = 1L;

	@MIBVariable(oid = "1", name = "icmpInMsgs", syntax = "Counter", readable = true, writeable = false)
	private Integer icmpInMsgs;

	@MIBVariable(oid = "2", name = "icmpInErrors", syntax = "Counter", readable = true, writeable = false)
	private Integer icmpInErrors;

	@MIBVariable(oid = "3", name = "icmpInDestUnreachs", syntax = "Counter", readable = true, writeable = false)
	private Integer icmpInDestUnreachs;

	@MIBVariable(oid = "4", name = "icmpInTimeExcds", syntax = "Counter", readable = true, writeable = false)
	private Integer icmpInTimeExcds;

	@MIBVariable(oid = "5", name = "icmpInParmProbs", syntax = "Counter", readable = true, writeable = false)
	private Integer icmpInParmProbs;

	@MIBVariable(oid = "6", name = "icmpInSrcQuenchs", syntax = "Counter", readable = true, writeable = false)
	private Integer icmpInSrcQuenchs;

	@MIBVariable(oid = "7", name = "icmpInRedirects", syntax = "Counter", readable = true, writeable = false)
	private Integer icmpInRedirects;

	@MIBVariable(oid = "8", name = "icmpInEchos", syntax = "Counter", readable = true, writeable = false)
	private Integer icmpInEchos;

	@MIBVariable(oid = "9", name = "icmpInEchoReps", syntax = "Counter", readable = true, writeable = false)
	private Integer icmpInEchoReps;

	@MIBVariable(oid = "10", name = "icmpInTimestamps", syntax = "Counter", readable = true, writeable = false)
	private Integer icmpInTimestamps;

	@MIBVariable(oid = "11", name = "icmpInTimestampReps", syntax = "Counter", readable = true, writeable = false)
	private Integer icmpInTimestampReps;

	@MIBVariable(oid = "12", name = "icmpInAddrMasks", syntax = "Counter", readable = true, writeable = false)
	private Integer icmpInAddrMasks;

	@MIBVariable(oid = "13", name = "icmpInAddrMaskReps", syntax = "Counter", readable = true, writeable = false)
	private Integer icmpInAddrMaskReps;

	@MIBVariable(oid = "14", name = "icmpOutMsgs", syntax = "Counter", readable = true, writeable = false)
	private Integer icmpOutMsgs;

	@MIBVariable(oid = "15", name = "icmpOutErrors", syntax = "Counter", readable = true, writeable = false)
	private Integer icmpOutErrors;

	@MIBVariable(oid = "16", name = "icmpOutDestUnreachs", syntax = "Counter", readable = true, writeable = false)
	private Integer icmpOutDestUnreachs;

	@MIBVariable(oid = "17", name = "icmpOutTimeExcds", syntax = "Counter", readable = true, writeable = false)
	private Integer icmpOutTimeExcds;

	@MIBVariable(oid = "18", name = "icmpOutParmProbs", syntax = "Counter", readable = true, writeable = false)
	private Integer icmpOutParmProbs;

	@MIBVariable(oid = "19", name = "icmpOutSrcQuenchs", syntax = "Counter", readable = true, writeable = false)
	private Integer icmpOutSrcQuenchs;

	@MIBVariable(oid = "20", name = "icmpOutRedirects", syntax = "Counter", readable = true, writeable = false)
	private Integer icmpOutRedirects;

	@MIBVariable(oid = "21", name = "icmpOutEchos", syntax = "Counter", readable = true, writeable = false)
	private Integer icmpOutEchos;

	@MIBVariable(oid = "22", name = "icmpOutEchoReps", syntax = "Counter", readable = true, writeable = false)
	private Integer icmpOutEchoReps;

	@MIBVariable(oid = "23", name = "icmpOutTimestamps", syntax = "Counter", readable = true, writeable = false)
	private Integer icmpOutTimestamps;

	@MIBVariable(oid = "24", name = "icmpOutTimestampReps", syntax = "Counter", readable = true, writeable = false)
	private Integer icmpOutTimestampReps;

	@MIBVariable(oid = "25", name = "icmpOutAddrMasks", syntax = "Counter", readable = true, writeable = false)
	private Integer icmpOutAddrMasks;

	@MIBVariable(oid = "26", name = "icmpOutAddrMaskReps", syntax = "Counter", readable = true, writeable = false)
	private Integer icmpOutAddrMaskReps;


	
	/**
	 * 
	 * "The total number of ICMP messages which the
	 *             entity received.  Note that this counter includes
	 *             all those counted by icmpInErrors."
	 * 
	 */
	public Integer getIcmpInMsgs(){
		return icmpInMsgs;
	}

	private void setIcmpInMsgs(Integer icmpInMsgs){
		this.icmpInMsgs = icmpInMsgs;
	}

	
	/**
	 * 
	 * "The number of ICMP messages which the entity
	 *             received but determined as having ICMP-specific
	 *             errors (bad ICMP checksums, bad length, etc.)."
	 * 
	 */
	public Integer getIcmpInErrors(){
		return icmpInErrors;
	}

	private void setIcmpInErrors(Integer icmpInErrors){
		this.icmpInErrors = icmpInErrors;
	}

	
	/**
	 * 
	 * "The number of ICMP Destination Unreachable
	 *             messages received."
	 * 
	 */
	public Integer getIcmpInDestUnreachs(){
		return icmpInDestUnreachs;
	}

	private void setIcmpInDestUnreachs(Integer icmpInDestUnreachs){
		this.icmpInDestUnreachs = icmpInDestUnreachs;
	}

	
	/**
	 * 
	 * "The number of ICMP Time Exceeded messages
	 *             received."
	 * 
	 */
	public Integer getIcmpInTimeExcds(){
		return icmpInTimeExcds;
	}

	private void setIcmpInTimeExcds(Integer icmpInTimeExcds){
		this.icmpInTimeExcds = icmpInTimeExcds;
	}

	
	/**
	 * 
	 * "The number of ICMP Parameter Problem messages
	 *             received."
	 * 
	 */
	public Integer getIcmpInParmProbs(){
		return icmpInParmProbs;
	}

	private void setIcmpInParmProbs(Integer icmpInParmProbs){
		this.icmpInParmProbs = icmpInParmProbs;
	}

	
	/**
	 * 
	 * "The number of ICMP Source Quench messages
	 *             received."
	 * 
	 */
	public Integer getIcmpInSrcQuenchs(){
		return icmpInSrcQuenchs;
	}

	private void setIcmpInSrcQuenchs(Integer icmpInSrcQuenchs){
		this.icmpInSrcQuenchs = icmpInSrcQuenchs;
	}

	
	/**
	 * 
	 * "The number of ICMP Redirect messages received."
	 * 
	 */
	public Integer getIcmpInRedirects(){
		return icmpInRedirects;
	}

	private void setIcmpInRedirects(Integer icmpInRedirects){
		this.icmpInRedirects = icmpInRedirects;
	}

	
	/**
	 * 
	 * "The number of ICMP Echo (request) messages
	 *             received."
	 * 
	 */
	public Integer getIcmpInEchos(){
		return icmpInEchos;
	}

	private void setIcmpInEchos(Integer icmpInEchos){
		this.icmpInEchos = icmpInEchos;
	}

	
	/**
	 * 
	 * "The number of ICMP Echo Reply messages received."
	 * 
	 */
	public Integer getIcmpInEchoReps(){
		return icmpInEchoReps;
	}

	private void setIcmpInEchoReps(Integer icmpInEchoReps){
		this.icmpInEchoReps = icmpInEchoReps;
	}

	
	/**
	 * 
	 * "The number of ICMP Timestamp (request) messages
	 *             received."
	 * 
	 */
	public Integer getIcmpInTimestamps(){
		return icmpInTimestamps;
	}

	private void setIcmpInTimestamps(Integer icmpInTimestamps){
		this.icmpInTimestamps = icmpInTimestamps;
	}

	
	/**
	 * 
	 * "The number of ICMP Timestamp Reply messages
	 *             received."
	 * 
	 */
	public Integer getIcmpInTimestampReps(){
		return icmpInTimestampReps;
	}

	private void setIcmpInTimestampReps(Integer icmpInTimestampReps){
		this.icmpInTimestampReps = icmpInTimestampReps;
	}

	
	/**
	 * 
	 * "The number of ICMP Address Mask Request messages
	 *             received."
	 * 
	 */
	public Integer getIcmpInAddrMasks(){
		return icmpInAddrMasks;
	}

	private void setIcmpInAddrMasks(Integer icmpInAddrMasks){
		this.icmpInAddrMasks = icmpInAddrMasks;
	}

	
	/**
	 * 
	 * "The number of ICMP Address Mask Reply messages
	 *             received."
	 * 
	 */
	public Integer getIcmpInAddrMaskReps(){
		return icmpInAddrMaskReps;
	}

	private void setIcmpInAddrMaskReps(Integer icmpInAddrMaskReps){
		this.icmpInAddrMaskReps = icmpInAddrMaskReps;
	}

	
	/**
	 * 
	 * "The total number of ICMP messages which this
	 *             entity attempted to send.  Note that this counter
	 *             includes all those counted by icmpOutErrors."
	 * 
	 */
	public Integer getIcmpOutMsgs(){
		return icmpOutMsgs;
	}

	private void setIcmpOutMsgs(Integer icmpOutMsgs){
		this.icmpOutMsgs = icmpOutMsgs;
	}

	
	/**
	 * 
	 * "The number of ICMP messages which this entity did
	 *             not send due to problems discovered within ICMP
	 *             such as a lack of buffers.  This value should not
	 *             include errors discovered outside the ICMP layer
	 *             such as the inability of IP to route the resultant
	 *             datagram.  In some implementations there may be no
	 *             types of error which contribute to this counter's
	 *             value."
	 * 
	 */
	public Integer getIcmpOutErrors(){
		return icmpOutErrors;
	}

	private void setIcmpOutErrors(Integer icmpOutErrors){
		this.icmpOutErrors = icmpOutErrors;
	}

	
	/**
	 * 
	 * "The number of ICMP Destination Unreachable
	 *             messages sent."
	 * 
	 */
	public Integer getIcmpOutDestUnreachs(){
		return icmpOutDestUnreachs;
	}

	private void setIcmpOutDestUnreachs(Integer icmpOutDestUnreachs){
		this.icmpOutDestUnreachs = icmpOutDestUnreachs;
	}

	
	/**
	 * 
	 * "The number of ICMP Time Exceeded messages sent."
	 * 
	 */
	public Integer getIcmpOutTimeExcds(){
		return icmpOutTimeExcds;
	}

	private void setIcmpOutTimeExcds(Integer icmpOutTimeExcds){
		this.icmpOutTimeExcds = icmpOutTimeExcds;
	}

	
	/**
	 * 
	 * "The number of ICMP Parameter Problem messages
	 *             sent."
	 * 
	 */
	public Integer getIcmpOutParmProbs(){
		return icmpOutParmProbs;
	}

	private void setIcmpOutParmProbs(Integer icmpOutParmProbs){
		this.icmpOutParmProbs = icmpOutParmProbs;
	}

	
	/**
	 * 
	 * "The number of ICMP Source Quench messages sent."
	 * 
	 */
	public Integer getIcmpOutSrcQuenchs(){
		return icmpOutSrcQuenchs;
	}

	private void setIcmpOutSrcQuenchs(Integer icmpOutSrcQuenchs){
		this.icmpOutSrcQuenchs = icmpOutSrcQuenchs;
	}

	
	/**
	 * 
	 * "The number of ICMP Redirect messages sent.  For a
	 *             host, this object will always be zero, since hosts
	 *             do not send redirects."
	 * 
	 */
	public Integer getIcmpOutRedirects(){
		return icmpOutRedirects;
	}

	private void setIcmpOutRedirects(Integer icmpOutRedirects){
		this.icmpOutRedirects = icmpOutRedirects;
	}

	
	/**
	 * 
	 * "The number of ICMP Echo (request) messages sent."
	 * 
	 */
	public Integer getIcmpOutEchos(){
		return icmpOutEchos;
	}

	private void setIcmpOutEchos(Integer icmpOutEchos){
		this.icmpOutEchos = icmpOutEchos;
	}

	
	/**
	 * 
	 * "The number of ICMP Echo Reply messages sent."
	 * 
	 */
	public Integer getIcmpOutEchoReps(){
		return icmpOutEchoReps;
	}

	private void setIcmpOutEchoReps(Integer icmpOutEchoReps){
		this.icmpOutEchoReps = icmpOutEchoReps;
	}

	
	/**
	 * 
	 * "The number of ICMP Timestamp (request) messages
	 *             sent."
	 * 
	 */
	public Integer getIcmpOutTimestamps(){
		return icmpOutTimestamps;
	}

	private void setIcmpOutTimestamps(Integer icmpOutTimestamps){
		this.icmpOutTimestamps = icmpOutTimestamps;
	}

	
	/**
	 * 
	 * "The number of ICMP Timestamp Reply messages
	 *             sent."
	 * 
	 */
	public Integer getIcmpOutTimestampReps(){
		return icmpOutTimestampReps;
	}

	private void setIcmpOutTimestampReps(Integer icmpOutTimestampReps){
		this.icmpOutTimestampReps = icmpOutTimestampReps;
	}

	
	/**
	 * 
	 * "The number of ICMP Address Mask Request messages
	 *             sent."
	 * 
	 */
	public Integer getIcmpOutAddrMasks(){
		return icmpOutAddrMasks;
	}

	private void setIcmpOutAddrMasks(Integer icmpOutAddrMasks){
		this.icmpOutAddrMasks = icmpOutAddrMasks;
	}

	
	/**
	 * 
	 * "The number of ICMP Address Mask Reply messages
	 *             sent."
	 * 
	 */
	public Integer getIcmpOutAddrMaskReps(){
		return icmpOutAddrMaskReps;
	}

	private void setIcmpOutAddrMaskReps(Integer icmpOutAddrMaskReps){
		this.icmpOutAddrMaskReps = icmpOutAddrMaskReps;
	}


}
