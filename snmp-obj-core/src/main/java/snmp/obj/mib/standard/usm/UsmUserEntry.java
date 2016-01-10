package snmp.obj.mib.standard.usm;

import java.io.Serializable;

import snmp.obj.mib.annotations.MIBTableEntry;
import snmp.obj.mib.annotations.MIBTableIndex;
import snmp.obj.mib.annotations.MIBVariable;
import snmp.obj.mib.constants.RowStatus;
import snmp.obj.mib.constants.StorageType;

/**
 * 
 * GENERATED FROM MIB. DO NOT CHANGE MANUALLY.
 * 
 */
@MIBTableEntry(oid = "1.3.6.1.6.3.15.1.2.2.1", 
				indexes = { @MIBTableIndex(name = "usmUserEngineID"), @MIBTableIndex(name = "usmUserName") })
public class UsmUserEntry implements Serializable{

	private static final long serialVersionUID = 1L;

	@MIBVariable(oid = "1", name = "usmUserEngineID", syntax = "SnmpEngineID", readable = false, writeable = false)
	private String usmUserEngineID;

	@MIBVariable(oid = "2", name = "usmUserName", syntax = "SnmpAdminString", readable = false, writeable = false)
	private String usmUserName;

	@MIBVariable(oid = "3", name = "usmUserSecurityName", syntax = "SnmpAdminString", readable = true, writeable = false)
	private String usmUserSecurityName;

	@MIBVariable(oid = "4", name = "usmUserCloneFrom", syntax = "RowPointer", readable = true, writeable = true)
	private String usmUserCloneFrom;

	@MIBVariable(oid = "5", name = "usmUserAuthProtocol", syntax = "AutonomousType", readable = true, writeable = true)
	private String usmUserAuthProtocol;

	@MIBVariable(oid = "6", name = "usmUserAuthKeyChange", syntax = "KeyChange", readable = true, writeable = true)
	private String usmUserAuthKeyChange;

	@MIBVariable(oid = "7", name = "usmUserOwnAuthKeyChange", syntax = "KeyChange", readable = true, writeable = true)
	private String usmUserOwnAuthKeyChange;

	@MIBVariable(oid = "8", name = "usmUserPrivProtocol", syntax = "AutonomousType", readable = true, writeable = true)
	private String usmUserPrivProtocol;

	@MIBVariable(oid = "9", name = "usmUserPrivKeyChange", syntax = "KeyChange", readable = true, writeable = true)
	private String usmUserPrivKeyChange;

	@MIBVariable(oid = "10", name = "usmUserOwnPrivKeyChange", syntax = "KeyChange", readable = true, writeable = true)
	private String usmUserOwnPrivKeyChange;

	@MIBVariable(oid = "11", name = "usmUserPublic", syntax = "OCTET_STRING", readable = true, writeable = true)
	private String usmUserPublic;

	@MIBVariable(oid = "12", name = "usmUserStorageType", syntax = "StorageType", readable = true, writeable = true)
	private StorageType usmUserStorageType;

	@MIBVariable(oid = "13", name = "usmUserStatus", syntax = "RowStatus", readable = true, writeable = true)
	private RowStatus usmUserStatus;


	
	/**
	 * 
	 * "An SNMP engine's administratively-unique identifier.
	 * 
	 *                  In a simple agent, this value is always that agent's
	 *                  own snmpEngineID value.
	 * 
	 *                  The value can also take the value of the snmpEngineID
	 *                  of a remote SNMP engine with which this user can
	 *                  communicate.
	 * 
	 *                 "
	 * 
	 */
	public String getUsmUserEngineID(){
		return usmUserEngineID;
	}

	private void setUsmUserEngineID(String usmUserEngineID){
		this.usmUserEngineID = usmUserEngineID;
	}

	
	/**
	 * 
	 * "A human readable string representing the name of
	 *                  the user.
	 * 
	 *                  This is the (User-based Security) Model dependent
	 *                  security ID.
	 *                 "
	 * 
	 */
	public String getUsmUserName(){
		return usmUserName;
	}

	private void setUsmUserName(String usmUserName){
		this.usmUserName = usmUserName;
	}

	
	/**
	 * 
	 * "A human readable string representing the user in
	 *                  Security Model independent format.
	 * 
	 *                  The default transformation of the User-based Security
	 *                  Model dependent security ID to the securityName and
	 *                  vice versa is the identity function so that the
	 *                  securityName is the same as the userName.
	 *                 "
	 * 
	 */
	public String getUsmUserSecurityName(){
		return usmUserSecurityName;
	}

	private void setUsmUserSecurityName(String usmUserSecurityName){
		this.usmUserSecurityName = usmUserSecurityName;
	}

	
	/**
	 * 
	 * "A pointer to another conceptual row in this
	 *                  usmUserTable.  The user in this other conceptual
	 *                  row is called the clone-from user.
	 * 
	 *                  When a new user is created (i.e., a new conceptual
	 *                  row is instantiated in this table), the privacy and
	 *                  authentication parameters of the new user must be
	 *                  cloned from its clone-from user. These parameters are:
	 *                    - authentication protocol (usmUserAuthProtocol)
	 *                    - privacy protocol (usmUserPrivProtocol)
	 *                  They will be copied regardless of what the current
	 *                  value is.
	 * 
	 *                  Cloning also causes the initial values of the secret
	 *                  authentication key (authKey) and the secret encryption
	 * 
	 *                  key (privKey) of the new user to be set to the same
	 *                  values as the corresponding secrets of the clone-from
	 *                  user to allow the KeyChange process to occur as
	 *                  required during user creation.
	 * 
	 *                  The first time an instance of this object is set by
	 *                  a management operation (either at or after its
	 *                  instantiation), the cloning process is invoked.
	 *                  Subsequent writes are successful but invoke no
	 *                  action to be taken by the receiver.
	 *                  The cloning process fails with an 'inconsistentName'
	 *                  error if the conceptual row representing the
	 *                  clone-from user does not exist or is not in an active
	 *                  state when the cloning process is invoked.
	 * 
	 *                  When this object is read, the ZeroDotZero OID
	 *                  is returned.
	 *                 "
	 * 
	 */
	public String getUsmUserCloneFrom(){
		return usmUserCloneFrom;
	}

	public void setUsmUserCloneFrom(String usmUserCloneFrom){
		this.usmUserCloneFrom = usmUserCloneFrom;
	}

	
	/**
	 * 
	 * "An indication of whether messages sent on behalf of
	 *                  this user to/from the SNMP engine identified by
	 *                  usmUserEngineID, can be authenticated, and if so,
	 *                  the type of authentication protocol which is used.
	 * 
	 *                  An instance of this object is created concurrently
	 *                  with the creation of any other object instance for
	 *                  the same user (i.e., as part of the processing of
	 *                  the set operation which creates the first object
	 *                  instance in the same conceptual row).
	 * 
	 *                  If an initial set operation (i.e. at row creation time)
	 *                  tries to set a value for an unknown or unsupported
	 *                  protocol, then a 'wrongValue' error must be returned.
	 * 
	 *                  The value will be overwritten/set when a set operation
	 *                  is performed on the corresponding instance of
	 *                  usmUserCloneFrom.
	 * 
	 *                  Once instantiated, the value of such an instance of
	 *                  this object can only be changed via a set operation to
	 *                  the value of the usmNoAuthProtocol.
	 * 
	 *                  If a set operation tries to change the value of an
	 * 
	 *                  existing instance of this object to any value other
	 *                  than usmNoAuthProtocol, then an 'inconsistentValue'
	 *                  error must be returned.
	 * 
	 *                  If a set operation tries to set the value to the
	 *                  usmNoAuthProtocol while the usmUserPrivProtocol value
	 *                  in the same row is not equal to usmNoPrivProtocol,
	 *                  then an 'inconsistentValue' error must be returned.
	 *                  That means that an SNMP command generator application
	 *                  must first ensure that the usmUserPrivProtocol is set
	 *                  to the usmNoPrivProtocol value before it can set
	 *                  the usmUserAuthProtocol value to usmNoAuthProtocol.
	 *                 "
	 * 
	 */
	public String getUsmUserAuthProtocol(){
		return usmUserAuthProtocol;
	}

	public void setUsmUserAuthProtocol(String usmUserAuthProtocol){
		this.usmUserAuthProtocol = usmUserAuthProtocol;
	}

	
	/**
	 * 
	 * "An object, which when modified, causes the secret
	 *                  authentication key used for messages sent on behalf
	 *                  of this user to/from the SNMP engine identified by
	 *                  usmUserEngineID, to be modified via a one-way
	 *                  function.
	 * 
	 *                  The associated protocol is the usmUserAuthProtocol.
	 *                  The associated secret key is the user's secret
	 *                  authentication key (authKey). The associated hash
	 *                  algorithm is the algorithm used by the user's
	 *                  usmUserAuthProtocol.
	 * 
	 *                  When creating a new user, it is an 'inconsistentName'
	 *                  error for a set operation to refer to this object
	 *                  unless it is previously or concurrently initialized
	 *                  through a set operation on the corresponding instance
	 *                  of usmUserCloneFrom.
	 * 
	 *                  When the value of the corresponding usmUserAuthProtocol
	 *                  is usmNoAuthProtocol, then a set is successful, but
	 *                  effectively is a no-op.
	 * 
	 *                  When this object is read, the zero-length (empty)
	 *                  string is returned.
	 * 
	 *                  The recommended way to do a key change is as follows:
	 * 
	 *                    1) GET(usmUserSpinLock.0) and save in sValue.
	 *                    2) generate the keyChange value based on the old
	 *                       (existing) secret key and the new secret key,
	 *                       let us call this kcValue.
	 * 
	 *                  If you do the key change on behalf of another user:
	 * 
	 *                    3) SET(usmUserSpinLock.0=sValue,
	 *                           usmUserAuthKeyChange=kcValue
	 *                           usmUserPublic=randomValue)
	 * 
	 *                  If you do the key change for yourself:
	 * 
	 *                    4) SET(usmUserSpinLock.0=sValue,
	 *                           usmUserOwnAuthKeyChange=kcValue
	 *                           usmUserPublic=randomValue)
	 * 
	 *                  If you get a response with error-status of noError,
	 *                  then the SET succeeded and the new key is active.
	 *                  If you do not get a response, then you can issue a
	 *                  GET(usmUserPublic) and check if the value is equal
	 *                  to the randomValue you did send in the SET. If so, then
	 *                  the key change succeeded and the new key is active
	 *                  (probably the response got lost). If not, then the SET
	 *                  request probably never reached the target and so you
	 *                  can start over with the procedure above.
	 *                 "
	 * 
	 */
	public String getUsmUserAuthKeyChange(){
		return usmUserAuthKeyChange;
	}

	public void setUsmUserAuthKeyChange(String usmUserAuthKeyChange){
		this.usmUserAuthKeyChange = usmUserAuthKeyChange;
	}

	
	/**
	 * 
	 * "Behaves exactly as usmUserAuthKeyChange, with one
	 *                  notable difference: in order for the set operation
	 *                  to succeed, the usmUserName of the operation
	 *                  requester must match the usmUserName that
	 *                  indexes the row which is targeted by this
	 *                  operation.
	 *                  In addition, the USM security model must be
	 *                  used for this operation.
	 * 
	 *                  The idea here is that access to this column can be
	 *                  public, since it will only allow a user to change
	 *                  his own secret authentication key (authKey).
	 *                  Note that this can only be done once the row is active.
	 * 
	 *                  When a set is received and the usmUserName of the
	 *                  requester is not the same as the umsUserName that
	 *                  indexes the row which is targeted by this operation,
	 *                  then a 'noAccess' error must be returned.
	 * 
	 *                  When a set is received and the security model in use
	 *                  is not USM, then a 'noAccess' error must be returned.
	 *                 "
	 * 
	 */
	public String getUsmUserOwnAuthKeyChange(){
		return usmUserOwnAuthKeyChange;
	}

	public void setUsmUserOwnAuthKeyChange(String usmUserOwnAuthKeyChange){
		this.usmUserOwnAuthKeyChange = usmUserOwnAuthKeyChange;
	}

	
	/**
	 * 
	 * "An indication of whether messages sent on behalf of
	 *                  this user to/from the SNMP engine identified by
	 *                  usmUserEngineID, can be protected from disclosure,
	 *                  and if so, the type of privacy protocol which is used.
	 * 
	 *                  An instance of this object is created concurrently
	 *                  with the creation of any other object instance for
	 *                  the same user (i.e., as part of the processing of
	 *                  the set operation which creates the first object
	 *                  instance in the same conceptual row).
	 * 
	 *                  If an initial set operation (i.e. at row creation time)
	 *                  tries to set a value for an unknown or unsupported
	 *                  protocol, then a 'wrongValue' error must be returned.
	 * 
	 *                  The value will be overwritten/set when a set operation
	 *                  is performed on the corresponding instance of
	 *                  usmUserCloneFrom.
	 * 
	 *                  Once instantiated, the value of such an instance of
	 *                  this object can only be changed via a set operation to
	 *                  the value of the usmNoPrivProtocol.
	 * 
	 *                  If a set operation tries to change the value of an
	 *                  existing instance of this object to any value other
	 *                  than usmNoPrivProtocol, then an 'inconsistentValue'
	 *                  error must be returned.
	 * 
	 *                  Note that if any privacy protocol is used, then you
	 *                  must also use an authentication protocol. In other
	 *                  words, if usmUserPrivProtocol is set to anything else
	 *                  than usmNoPrivProtocol, then the corresponding instance
	 *                  of usmUserAuthProtocol cannot have a value of
	 * 
	 *                  usmNoAuthProtocol. If it does, then an
	 *                  'inconsistentValue' error must be returned.
	 *                 "
	 * 
	 */
	public String getUsmUserPrivProtocol(){
		return usmUserPrivProtocol;
	}

	public void setUsmUserPrivProtocol(String usmUserPrivProtocol){
		this.usmUserPrivProtocol = usmUserPrivProtocol;
	}

	
	/**
	 * 
	 * "An object, which when modified, causes the secret
	 *                  encryption key used for messages sent on behalf
	 *                  of this user to/from the SNMP engine identified by
	 *                  usmUserEngineID, to be modified via a one-way
	 *                  function.
	 * 
	 *                  The associated protocol is the usmUserPrivProtocol.
	 *                  The associated secret key is the user's secret
	 *                  privacy key (privKey). The associated hash
	 *                  algorithm is the algorithm used by the user's
	 *                  usmUserAuthProtocol.
	 * 
	 *                  When creating a new user, it is an 'inconsistentName'
	 *                  error for a set operation to refer to this object
	 *                  unless it is previously or concurrently initialized
	 *                  through a set operation on the corresponding instance
	 *                  of usmUserCloneFrom.
	 * 
	 *                  When the value of the corresponding usmUserPrivProtocol
	 *                  is usmNoPrivProtocol, then a set is successful, but
	 *                  effectively is a no-op.
	 * 
	 *                  When this object is read, the zero-length (empty)
	 *                  string is returned.
	 *                  See the description clause of usmUserAuthKeyChange for
	 *                  a recommended procedure to do a key change.
	 *                 "
	 * 
	 */
	public String getUsmUserPrivKeyChange(){
		return usmUserPrivKeyChange;
	}

	public void setUsmUserPrivKeyChange(String usmUserPrivKeyChange){
		this.usmUserPrivKeyChange = usmUserPrivKeyChange;
	}

	
	/**
	 * 
	 * "Behaves exactly as usmUserPrivKeyChange, with one
	 *                  notable difference: in order for the Set operation
	 *                  to succeed, the usmUserName of the operation
	 *                  requester must match the usmUserName that indexes
	 * 
	 *                  the row which is targeted by this operation.
	 *                  In addition, the USM security model must be
	 *                  used for this operation.
	 * 
	 *                  The idea here is that access to this column can be
	 *                  public, since it will only allow a user to change
	 *                  his own secret privacy key (privKey).
	 *                  Note that this can only be done once the row is active.
	 * 
	 *                  When a set is received and the usmUserName of the
	 *                  requester is not the same as the umsUserName that
	 *                  indexes the row which is targeted by this operation,
	 *                  then a 'noAccess' error must be returned.
	 * 
	 *                  When a set is received and the security model in use
	 *                  is not USM, then a 'noAccess' error must be returned.
	 *                 "
	 * 
	 */
	public String getUsmUserOwnPrivKeyChange(){
		return usmUserOwnPrivKeyChange;
	}

	public void setUsmUserOwnPrivKeyChange(String usmUserOwnPrivKeyChange){
		this.usmUserOwnPrivKeyChange = usmUserOwnPrivKeyChange;
	}

	
	/**
	 * 
	 * "A publicly-readable value which can be written as part
	 *                  of the procedure for changing a user's secret
	 *                  authentication and/or privacy key, and later read to
	 *                  determine whether the change of the secret was
	 *                  effected.
	 *                 "
	 * 
	 */
	public String getUsmUserPublic(){
		return usmUserPublic;
	}

	public void setUsmUserPublic(String usmUserPublic){
		this.usmUserPublic = usmUserPublic;
	}

	
	/**
	 * 
	 * "The storage type for this conceptual row.
	 * 
	 *                  Conceptual rows having the value 'permanent' must
	 *                  allow write-access at a minimum to:
	 * 
	 *                  - usmUserAuthKeyChange, usmUserOwnAuthKeyChange
	 *                    and usmUserPublic for a user who employs
	 *                    authentication, and
	 *                  - usmUserPrivKeyChange, usmUserOwnPrivKeyChange
	 *                    and usmUserPublic for a user who employs
	 *                    privacy.
	 * 
	 *                  Note that any user who employs authentication or
	 *                  privacy must allow its secret(s) to be updated and
	 *                  thus cannot be 'readOnly'.
	 * 
	 *                  If an initial set operation tries to set the value to
	 *                  'readOnly' for a user who employs authentication or
	 *                  privacy, then an 'inconsistentValue' error must be
	 *                  returned.  Note that if the value has been previously
	 *                  set (implicit or explicit) to any value, then the rules
	 *                  as defined in the StorageType Textual Convention apply.
	 * 
	 *                  It is an implementation issue to decide if a SET for
	 *                  a readOnly or permanent row is accepted at all. In some
	 *                  contexts this may make sense, in others it may not. If
	 *                  a SET for a readOnly or permanent row is not accepted
	 *                  at all, then a 'wrongValue' error must be returned.
	 *                 "
	 * 
	 */
	public StorageType getUsmUserStorageType(){
		return usmUserStorageType;
	}

	public void setUsmUserStorageType(StorageType usmUserStorageType){
		this.usmUserStorageType = usmUserStorageType;
	}

	
	/**
	 * 
	 * "The status of this conceptual row.
	 * 
	 *                  Until instances of all corresponding columns are
	 *                  appropriately configured, the value of the
	 *                  corresponding instance of the usmUserStatus column
	 *                  is 'notReady'.
	 * 
	 *                  In particular, a newly created row for a user who
	 *                  employs authentication, cannot be made active until the
	 *                  corresponding usmUserCloneFrom and usmUserAuthKeyChange
	 *                  have been set.
	 * 
	 *                  Further, a newly created row for a user who also
	 *                  employs privacy, cannot be made active until the
	 *                  usmUserPrivKeyChange has been set.
	 * 
	 *                  The RowStatus TC [RFC2579] requires that this
	 *                  DESCRIPTION clause states under which circumstances
	 *                  other objects in this row can be modified:
	 * 
	 *                  The value of this object has no effect on whether
	 *                  other objects in this conceptual row can be modified,
	 *                  except for usmUserOwnAuthKeyChange and
	 *                  usmUserOwnPrivKeyChange. For these 2 objects, the
	 * 
	 *                  value of usmUserStatus MUST be active.
	 *                 "
	 * 
	 */
	public RowStatus getUsmUserStatus(){
		return usmUserStatus;
	}

	public void setUsmUserStatus(RowStatus usmUserStatus){
		this.usmUserStatus = usmUserStatus;
	}


}
