package snmp.obj.mib.standard.rfc1213;

import java.io.Serializable;
import snmp.obj.mib.annotations.*;

/**
 * 
 * GENERATED FROM MIB. DO NOT CHANGE MANUALLY.
 * 
 */
@MIBScalarGroup(oid = "1.3.6.1.2.1.11")
public class Snmp implements Serializable{

	private static final long serialVersionUID = 1L;

	@MIBVariable(oid = "1", name = "snmpInPkts", syntax = "Counter", readable = true, writeable = false)
	private Integer snmpInPkts;

	@MIBVariable(oid = "2", name = "snmpOutPkts", syntax = "Counter", readable = true, writeable = false)
	private Integer snmpOutPkts;

	@MIBVariable(oid = "3", name = "snmpInBadVersions", syntax = "Counter", readable = true, writeable = false)
	private Integer snmpInBadVersions;

	@MIBVariable(oid = "4", name = "snmpInBadCommunityNames", syntax = "Counter", readable = true, writeable = false)
	private Integer snmpInBadCommunityNames;

	@MIBVariable(oid = "5", name = "snmpInBadCommunityUses", syntax = "Counter", readable = true, writeable = false)
	private Integer snmpInBadCommunityUses;

	@MIBVariable(oid = "6", name = "snmpInASNParseErrs", syntax = "Counter", readable = true, writeable = false)
	private Integer snmpInASNParseErrs;

	@MIBVariable(oid = "8", name = "snmpInTooBigs", syntax = "Counter", readable = true, writeable = false)
	private Integer snmpInTooBigs;

	@MIBVariable(oid = "9", name = "snmpInNoSuchNames", syntax = "Counter", readable = true, writeable = false)
	private Integer snmpInNoSuchNames;

	@MIBVariable(oid = "10", name = "snmpInBadValues", syntax = "Counter", readable = true, writeable = false)
	private Integer snmpInBadValues;

	@MIBVariable(oid = "11", name = "snmpInReadOnlys", syntax = "Counter", readable = true, writeable = false)
	private Integer snmpInReadOnlys;

	@MIBVariable(oid = "12", name = "snmpInGenErrs", syntax = "Counter", readable = true, writeable = false)
	private Integer snmpInGenErrs;

	@MIBVariable(oid = "13", name = "snmpInTotalReqVars", syntax = "Counter", readable = true, writeable = false)
	private Integer snmpInTotalReqVars;

	@MIBVariable(oid = "14", name = "snmpInTotalSetVars", syntax = "Counter", readable = true, writeable = false)
	private Integer snmpInTotalSetVars;

	@MIBVariable(oid = "15", name = "snmpInGetRequests", syntax = "Counter", readable = true, writeable = false)
	private Integer snmpInGetRequests;

	@MIBVariable(oid = "16", name = "snmpInGetNexts", syntax = "Counter", readable = true, writeable = false)
	private Integer snmpInGetNexts;

	@MIBVariable(oid = "17", name = "snmpInSetRequests", syntax = "Counter", readable = true, writeable = false)
	private Integer snmpInSetRequests;

	@MIBVariable(oid = "18", name = "snmpInGetResponses", syntax = "Counter", readable = true, writeable = false)
	private Integer snmpInGetResponses;

	@MIBVariable(oid = "19", name = "snmpInTraps", syntax = "Counter", readable = true, writeable = false)
	private Integer snmpInTraps;

	@MIBVariable(oid = "20", name = "snmpOutTooBigs", syntax = "Counter", readable = true, writeable = false)
	private Integer snmpOutTooBigs;

	@MIBVariable(oid = "21", name = "snmpOutNoSuchNames", syntax = "Counter", readable = true, writeable = false)
	private Integer snmpOutNoSuchNames;

	@MIBVariable(oid = "22", name = "snmpOutBadValues", syntax = "Counter", readable = true, writeable = false)
	private Integer snmpOutBadValues;

	@MIBVariable(oid = "24", name = "snmpOutGenErrs", syntax = "Counter", readable = true, writeable = false)
	private Integer snmpOutGenErrs;

	@MIBVariable(oid = "25", name = "snmpOutGetRequests", syntax = "Counter", readable = true, writeable = false)
	private Integer snmpOutGetRequests;

	@MIBVariable(oid = "26", name = "snmpOutGetNexts", syntax = "Counter", readable = true, writeable = false)
	private Integer snmpOutGetNexts;

	@MIBVariable(oid = "27", name = "snmpOutSetRequests", syntax = "Counter", readable = true, writeable = false)
	private Integer snmpOutSetRequests;

	@MIBVariable(oid = "28", name = "snmpOutGetResponses", syntax = "Counter", readable = true, writeable = false)
	private Integer snmpOutGetResponses;

	@MIBVariable(oid = "29", name = "snmpOutTraps", syntax = "Counter", readable = true, writeable = false)
	private Integer snmpOutTraps;

	@MIBVariable(oid = "30", name = "snmpEnableAuthenTraps", syntax = "INTEGER", readable = true, writeable = true)
	private SnmpEnableAuthenTraps snmpEnableAuthenTraps;


	
	/**
	 * 
	 * "The total number of Messages delivered to the
	 *             SNMP entity from the transport service."
	 * 
	 */
	public Integer getSnmpInPkts(){
		return snmpInPkts;
	}

	private void setSnmpInPkts(Integer snmpInPkts){
		this.snmpInPkts = snmpInPkts;
	}

	
	/**
	 * 
	 * "The total number of SNMP Messages which were
	 *             passed from the SNMP protocol entity to the
	 *             transport service."
	 * 
	 */
	public Integer getSnmpOutPkts(){
		return snmpOutPkts;
	}

	private void setSnmpOutPkts(Integer snmpOutPkts){
		this.snmpOutPkts = snmpOutPkts;
	}

	
	/**
	 * 
	 * "The total number of SNMP Messages which were
	 *             delivered to the SNMP protocol entity and were for
	 *             an unsupported SNMP version."
	 * 
	 */
	public Integer getSnmpInBadVersions(){
		return snmpInBadVersions;
	}

	private void setSnmpInBadVersions(Integer snmpInBadVersions){
		this.snmpInBadVersions = snmpInBadVersions;
	}

	
	/**
	 * 
	 * "The total number of SNMP Messages delivered to
	 *             the SNMP protocol entity which used a SNMP
	 *             community name not known to said entity."
	 * 
	 */
	public Integer getSnmpInBadCommunityNames(){
		return snmpInBadCommunityNames;
	}

	private void setSnmpInBadCommunityNames(Integer snmpInBadCommunityNames){
		this.snmpInBadCommunityNames = snmpInBadCommunityNames;
	}

	
	/**
	 * 
	 * "The total number of SNMP Messages delivered to
	 *             the SNMP protocol entity which represented an SNMP
	 *             operation which was not allowed by the SNMP
	 *             community named in the Message."
	 * 
	 */
	public Integer getSnmpInBadCommunityUses(){
		return snmpInBadCommunityUses;
	}

	private void setSnmpInBadCommunityUses(Integer snmpInBadCommunityUses){
		this.snmpInBadCommunityUses = snmpInBadCommunityUses;
	}

	
	/**
	 * 
	 * "The total number of ASN.1 or BER errors
	 *             encountered by the SNMP protocol entity when
	 *             decoding received SNMP Messages."
	 * 
	 */
	public Integer getSnmpInASNParseErrs(){
		return snmpInASNParseErrs;
	}

	private void setSnmpInASNParseErrs(Integer snmpInASNParseErrs){
		this.snmpInASNParseErrs = snmpInASNParseErrs;
	}

	
	/**
	 * 
	 * "The total number of SNMP PDUs which were
	 *             delivered to the SNMP protocol entity and for
	 *             which the value of the error-status field is
	 *             `tooBig'."
	 * 
	 */
	public Integer getSnmpInTooBigs(){
		return snmpInTooBigs;
	}

	private void setSnmpInTooBigs(Integer snmpInTooBigs){
		this.snmpInTooBigs = snmpInTooBigs;
	}

	
	/**
	 * 
	 * "The total number of SNMP PDUs which were
	 *             delivered to the SNMP protocol entity and for
	 *             which the value of the error-status field is
	 *             `noSuchName'."
	 * 
	 */
	public Integer getSnmpInNoSuchNames(){
		return snmpInNoSuchNames;
	}

	private void setSnmpInNoSuchNames(Integer snmpInNoSuchNames){
		this.snmpInNoSuchNames = snmpInNoSuchNames;
	}

	
	/**
	 * 
	 * "The total number of SNMP PDUs which were
	 *             delivered to the SNMP protocol entity and for
	 *             which the value of the error-status field is
	 *             `badValue'."
	 * 
	 */
	public Integer getSnmpInBadValues(){
		return snmpInBadValues;
	}

	private void setSnmpInBadValues(Integer snmpInBadValues){
		this.snmpInBadValues = snmpInBadValues;
	}

	
	/**
	 * 
	 * "The total number valid SNMP PDUs which were
	 *             delivered to the SNMP protocol entity and for
	 *             which the value of the error-status field is
	 *             `readOnly'.  It should be noted that it is a
	 *             protocol error to generate an SNMP PDU which
	 *             contains the value `readOnly' in the error-status
	 *             field, as such this object is provided as a means
	 *             of detecting incorrect implementations of the
	 *             SNMP."
	 * 
	 */
	public Integer getSnmpInReadOnlys(){
		return snmpInReadOnlys;
	}

	private void setSnmpInReadOnlys(Integer snmpInReadOnlys){
		this.snmpInReadOnlys = snmpInReadOnlys;
	}

	
	/**
	 * 
	 * "The total number of SNMP PDUs which were
	 *             delivered to the SNMP protocol entity and for
	 *             which the value of the error-status field is
	 *             `genErr'."
	 * 
	 */
	public Integer getSnmpInGenErrs(){
		return snmpInGenErrs;
	}

	private void setSnmpInGenErrs(Integer snmpInGenErrs){
		this.snmpInGenErrs = snmpInGenErrs;
	}

	
	/**
	 * 
	 * "The total number of MIB objects which have been
	 *             retrieved successfully by the SNMP protocol entity
	 *             as the result of receiving valid SNMP Get-Request
	 *             and Get-Next PDUs."
	 * 
	 */
	public Integer getSnmpInTotalReqVars(){
		return snmpInTotalReqVars;
	}

	private void setSnmpInTotalReqVars(Integer snmpInTotalReqVars){
		this.snmpInTotalReqVars = snmpInTotalReqVars;
	}

	
	/**
	 * 
	 * "The total number of MIB objects which have been
	 *             altered successfully by the SNMP protocol entity
	 *             as the result of receiving valid SNMP Set-Request
	 *             PDUs."
	 * 
	 */
	public Integer getSnmpInTotalSetVars(){
		return snmpInTotalSetVars;
	}

	private void setSnmpInTotalSetVars(Integer snmpInTotalSetVars){
		this.snmpInTotalSetVars = snmpInTotalSetVars;
	}

	
	/**
	 * 
	 * "The total number of SNMP Get-Request PDUs which
	 *             have been accepted and processed by the SNMP
	 *             protocol entity."
	 * 
	 */
	public Integer getSnmpInGetRequests(){
		return snmpInGetRequests;
	}

	private void setSnmpInGetRequests(Integer snmpInGetRequests){
		this.snmpInGetRequests = snmpInGetRequests;
	}

	
	/**
	 * 
	 * "The total number of SNMP Get-Next PDUs which have
	 *             been accepted and processed by the SNMP protocol
	 *             entity."
	 * 
	 */
	public Integer getSnmpInGetNexts(){
		return snmpInGetNexts;
	}

	private void setSnmpInGetNexts(Integer snmpInGetNexts){
		this.snmpInGetNexts = snmpInGetNexts;
	}

	
	/**
	 * 
	 * "The total number of SNMP Set-Request PDUs which
	 *             have been accepted and processed by the SNMP
	 *             protocol entity."
	 * 
	 */
	public Integer getSnmpInSetRequests(){
		return snmpInSetRequests;
	}

	private void setSnmpInSetRequests(Integer snmpInSetRequests){
		this.snmpInSetRequests = snmpInSetRequests;
	}

	
	/**
	 * 
	 * "The total number of SNMP Get-Response PDUs which
	 *             have been accepted and processed by the SNMP
	 *             protocol entity."
	 * 
	 */
	public Integer getSnmpInGetResponses(){
		return snmpInGetResponses;
	}

	private void setSnmpInGetResponses(Integer snmpInGetResponses){
		this.snmpInGetResponses = snmpInGetResponses;
	}

	
	/**
	 * 
	 * "The total number of SNMP Trap PDUs which have
	 *             been accepted and processed by the SNMP protocol
	 *             entity."
	 * 
	 */
	public Integer getSnmpInTraps(){
		return snmpInTraps;
	}

	private void setSnmpInTraps(Integer snmpInTraps){
		this.snmpInTraps = snmpInTraps;
	}

	
	/**
	 * 
	 * "The total number of SNMP PDUs which were
	 *             generated by the SNMP protocol entity and for
	 *             which the value of the error-status field is
	 *             `tooBig.'"
	 * 
	 */
	public Integer getSnmpOutTooBigs(){
		return snmpOutTooBigs;
	}

	private void setSnmpOutTooBigs(Integer snmpOutTooBigs){
		this.snmpOutTooBigs = snmpOutTooBigs;
	}

	
	/**
	 * 
	 * "The total number of SNMP PDUs which were
	 *             generated by the SNMP protocol entity and for
	 *             which the value of the error-status is
	 *             `noSuchName'."
	 * 
	 */
	public Integer getSnmpOutNoSuchNames(){
		return snmpOutNoSuchNames;
	}

	private void setSnmpOutNoSuchNames(Integer snmpOutNoSuchNames){
		this.snmpOutNoSuchNames = snmpOutNoSuchNames;
	}

	
	/**
	 * 
	 * "The total number of SNMP PDUs which were
	 *             generated by the SNMP protocol entity and for
	 *             which the value of the error-status field is
	 *             `badValue'."
	 * 
	 */
	public Integer getSnmpOutBadValues(){
		return snmpOutBadValues;
	}

	private void setSnmpOutBadValues(Integer snmpOutBadValues){
		this.snmpOutBadValues = snmpOutBadValues;
	}

	
	/**
	 * 
	 * "The total number of SNMP PDUs which were
	 *             generated by the SNMP protocol entity and for
	 *             which the value of the error-status field is
	 *             `genErr'."
	 * 
	 */
	public Integer getSnmpOutGenErrs(){
		return snmpOutGenErrs;
	}

	private void setSnmpOutGenErrs(Integer snmpOutGenErrs){
		this.snmpOutGenErrs = snmpOutGenErrs;
	}

	
	/**
	 * 
	 * "The total number of SNMP Get-Request PDUs which
	 *             have been generated by the SNMP protocol entity."
	 * 
	 */
	public Integer getSnmpOutGetRequests(){
		return snmpOutGetRequests;
	}

	private void setSnmpOutGetRequests(Integer snmpOutGetRequests){
		this.snmpOutGetRequests = snmpOutGetRequests;
	}

	
	/**
	 * 
	 * "The total number of SNMP Get-Next PDUs which have
	 *             been generated by the SNMP protocol entity."
	 * 
	 */
	public Integer getSnmpOutGetNexts(){
		return snmpOutGetNexts;
	}

	private void setSnmpOutGetNexts(Integer snmpOutGetNexts){
		this.snmpOutGetNexts = snmpOutGetNexts;
	}

	
	/**
	 * 
	 * "The total number of SNMP Set-Request PDUs which
	 *             have been generated by the SNMP protocol entity."
	 * 
	 */
	public Integer getSnmpOutSetRequests(){
		return snmpOutSetRequests;
	}

	private void setSnmpOutSetRequests(Integer snmpOutSetRequests){
		this.snmpOutSetRequests = snmpOutSetRequests;
	}

	
	/**
	 * 
	 * "The total number of SNMP Get-Response PDUs which
	 *             have been generated by the SNMP protocol entity."
	 * 
	 */
	public Integer getSnmpOutGetResponses(){
		return snmpOutGetResponses;
	}

	private void setSnmpOutGetResponses(Integer snmpOutGetResponses){
		this.snmpOutGetResponses = snmpOutGetResponses;
	}

	
	/**
	 * 
	 * "The total number of SNMP Trap PDUs which have
	 *             been generated by the SNMP protocol entity."
	 * 
	 */
	public Integer getSnmpOutTraps(){
		return snmpOutTraps;
	}

	private void setSnmpOutTraps(Integer snmpOutTraps){
		this.snmpOutTraps = snmpOutTraps;
	}

	
	/**
	 * 
	 * "Indicates whether the SNMP agent process is
	 *             permitted to generate authentication-failure
	 *             traps.  The value of this object overrides any
	 *             configuration information; as such, it provides a
	 *             means whereby all authentication-failure traps may
	 *             be disabled.
	 * 
	 *             Note that it is strongly recommended that this
	 *             object be stored in non-volatile memory so that it
	 *             remains constant between re-initializations of the
	 *             network management system."
	 * 
	 */
	public SnmpEnableAuthenTraps getSnmpEnableAuthenTraps(){
		return snmpEnableAuthenTraps;
	}

	public void setSnmpEnableAuthenTraps(SnmpEnableAuthenTraps snmpEnableAuthenTraps){
		this.snmpEnableAuthenTraps = snmpEnableAuthenTraps;
	}


	public enum SnmpEnableAuthenTraps{
		enabled(1),
		disabled(2),;

		private final int value;

		private SnmpEnableAuthenTraps(int value){
			this.value = value;
		}

		public int value(){
			return value;
		}

		public static SnmpEnableAuthenTraps fromValue(int value){
			for(SnmpEnableAuthenTraps constant : values()){
				if(constant.value() == value){
					return constant;
				}
			}
			return null;
		}

	}

}
