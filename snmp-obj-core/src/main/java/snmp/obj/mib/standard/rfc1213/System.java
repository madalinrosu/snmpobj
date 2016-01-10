package snmp.obj.mib.standard.rfc1213;

import java.io.Serializable;
import snmp.obj.mib.annotations.*;

/**
 * 
 * GENERATED FROM MIB. DO NOT CHANGE MANUALLY.
 * 
 */
@MIBScalarGroup(oid = "1.3.6.1.2.1.1")
public class System implements Serializable{

	private static final long serialVersionUID = 1L;

	@MIBVariable(oid = "1", name = "sysDescr", syntax = "DisplayString", readable = true, writeable = false)
	private String sysDescr;

	@MIBVariable(oid = "2", name = "sysObjectID", syntax = "OBJECT_IDENTIFIER", readable = true, writeable = false)
	private String sysObjectID;

	@MIBVariable(oid = "3", name = "sysUpTime", syntax = "TimeTicks", readable = true, writeable = false)
	private Integer sysUpTime;

	@MIBVariable(oid = "4", name = "sysContact", syntax = "DisplayString", readable = true, writeable = true)
	private String sysContact;

	@MIBVariable(oid = "5", name = "sysName", syntax = "DisplayString", readable = true, writeable = true)
	private String sysName;

	@MIBVariable(oid = "6", name = "sysLocation", syntax = "DisplayString", readable = true, writeable = true)
	private String sysLocation;

	@MIBVariable(oid = "7", name = "sysServices", syntax = "INTEGER", readable = true, writeable = false)
	private Integer sysServices;


	
	/**
	 * 
	 * "A textual description of the entity.  This value
	 *             should include the full name and version
	 *             identification of the system's hardware type,
	 *             software operating-system, and networking
	 *             software.  It is mandatory that this only contain
	 *             printable ASCII characters."
	 * 
	 */
	public String getSysDescr(){
		return sysDescr;
	}

	private void setSysDescr(String sysDescr){
		this.sysDescr = sysDescr;
	}

	
	/**
	 * 
	 * "The vendor's authoritative identification of the
	 *             network management subsystem contained in the
	 *             entity.  This value is allocated within the SMI
	 *             enterprises subtree (1.3.6.1.4.1) and provides an
	 *             easy and unambiguous means for determining `what
	 *             kind of box' is being managed.  For example, if
	 *             vendor `Flintstones, Inc.' was assigned the
	 *             subtree 1.3.6.1.4.1.4242, it could assign the
	 *             identifier 1.3.6.1.4.1.4242.1.1 to its `Fred
	 *             Router'."
	 * 
	 */
	public String getSysObjectID(){
		return sysObjectID;
	}

	private void setSysObjectID(String sysObjectID){
		this.sysObjectID = sysObjectID;
	}

	
	/**
	 * 
	 * "The time (in hundredths of a second) since the
	 *             network management portion of the system was last
	 *             re-initialized."
	 * 
	 */
	public Integer getSysUpTime(){
		return sysUpTime;
	}

	private void setSysUpTime(Integer sysUpTime){
		this.sysUpTime = sysUpTime;
	}

	
	/**
	 * 
	 * "The textual identification of the contact person
	 *             for this managed node, together with information
	 *             on how to contact this person."
	 * 
	 */
	public String getSysContact(){
		return sysContact;
	}

	public void setSysContact(String sysContact){
		this.sysContact = sysContact;
	}

	
	/**
	 * 
	 * "An administratively-assigned name for this
	 *             managed node.  By convention, this is the node's
	 *             fully-qualified domain name."
	 * 
	 */
	public String getSysName(){
		return sysName;
	}

	public void setSysName(String sysName){
		this.sysName = sysName;
	}

	
	/**
	 * 
	 * "The physical location of this node (e.g.,
	 *             `telephone closet, 3rd floor')."
	 * 
	 */
	public String getSysLocation(){
		return sysLocation;
	}

	public void setSysLocation(String sysLocation){
		this.sysLocation = sysLocation;
	}

	
	/**
	 * 
	 * "A value which indicates the set of services that
	 *             this entity primarily offers.
	 * 
	 *             The value is a sum.  This sum initially takes the
	 *             value zero, Then, for each layer, L, in the range
	 *             1 through 7, that this node performs transactions
	 *             for, 2 raised to (L - 1) is added to the sum.  For
	 *             example, a node which performs primarily routing
	 *             functions would have a value of 4 (2^(3-1)).  In
	 *             contrast, a node which is a host offering
	 *             application services would have a value of 72
	 *             (2^(4-1) + 2^(7-1)).  Note that in the context of
	 *             the Internet suite of protocols, values should be
	 *             calculated accordingly:
	 * 
	 *                  layer  functionality
	 *                      1  physical (e.g., repeaters)
	 *                      2  datalink/subnetwork (e.g., bridges)
	 *                      3  internet (e.g., IP gateways)
	 *                      4  end-to-end  (e.g., IP hosts)
	 *                      7  applications (e.g., mail relays)
	 * 
	 *             For systems including OSI protocols, layers 5 and
	 *             6 may also be counted."
	 * 
	 */
	public Integer getSysServices(){
		return sysServices;
	}

	private void setSysServices(Integer sysServices){
		this.sysServices = sysServices;
	}


}
