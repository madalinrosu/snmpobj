package snmp.obj.mib.standard.hostresources;

import java.io.Serializable;
import snmp.obj.mib.annotations.*;

/**
 * 
 * GENERATED FROM MIB. DO NOT CHANGE MANUALLY.
 * 
 */
@MIBScalarGroup(oid = "1.3.6.1.2.1.25.1")
public class HrSystem implements Serializable{

	private static final long serialVersionUID = 1L;

	@MIBVariable(oid = "1", name = "hrSystemUptime", syntax = "TimeTicks", readable = true, writeable = false)
	private Integer hrSystemUptime;

	@MIBVariable(oid = "2", name = "hrSystemDate", syntax = "DateAndTime", readable = true, writeable = true)
	private String hrSystemDate;

	@MIBVariable(oid = "3", name = "hrSystemInitialLoadDevice", syntax = "Integer32", readable = true, writeable = true)
	private Integer hrSystemInitialLoadDevice;

	@MIBVariable(oid = "4", name = "hrSystemInitialLoadParameters", syntax = "InternationalDisplayString", readable = true, writeable = true)
	private String hrSystemInitialLoadParameters;

	@MIBVariable(oid = "5", name = "hrSystemNumUsers", syntax = "Gauge32", readable = true, writeable = false)
	private Integer hrSystemNumUsers;

	@MIBVariable(oid = "6", name = "hrSystemProcesses", syntax = "Gauge32", readable = true, writeable = false)
	private Integer hrSystemProcesses;

	@MIBVariable(oid = "7", name = "hrSystemMaxProcesses", syntax = "Integer32", readable = true, writeable = false)
	private Integer hrSystemMaxProcesses;


	
	/**
	 * 
	 * "The amount of time since this host was last
	 *         initialized.  Note that this is different from
	 *         sysUpTime in the SNMPv2-MIB [RFC1907] because
	 *         sysUpTime is the uptime of the network management
	 *         portion of the system."
	 * 
	 */
	public Integer getHrSystemUptime(){
		return hrSystemUptime;
	}

	private void setHrSystemUptime(Integer hrSystemUptime){
		this.hrSystemUptime = hrSystemUptime;
	}

	
	/**
	 * 
	 * "The host's notion of the local date and time of day."
	 * 
	 */
	public String getHrSystemDate(){
		return hrSystemDate;
	}

	public void setHrSystemDate(String hrSystemDate){
		this.hrSystemDate = hrSystemDate;
	}

	
	/**
	 * 
	 * "The index of the hrDeviceEntry for the device from
	 *         which this host is configured to load its initial
	 *         operating system configuration (i.e., which operating
	 *         system code and/or boot parameters).
	 * 
	 *         Note that writing to this object just changes the
	 *         configuration that will be used the next time the
	 *         operating system is loaded and does not actually cause
	 *         the reload to occur."
	 * 
	 */
	public Integer getHrSystemInitialLoadDevice(){
		return hrSystemInitialLoadDevice;
	}

	public void setHrSystemInitialLoadDevice(Integer hrSystemInitialLoadDevice){
		this.hrSystemInitialLoadDevice = hrSystemInitialLoadDevice;
	}

	
	/**
	 * 
	 * "This object contains the parameters (e.g. a pathname
	 *         and parameter) supplied to the load device when
	 *         requesting the initial operating system configuration
	 *         from that device.
	 * 
	 *      Note that writing to this object just changes the
	 *      configuration that will be used the next time the
	 *      operating system is loaded and does not actually cause
	 *      the reload to occur."
	 * 
	 */
	public String getHrSystemInitialLoadParameters(){
		return hrSystemInitialLoadParameters;
	}

	public void setHrSystemInitialLoadParameters(String hrSystemInitialLoadParameters){
		this.hrSystemInitialLoadParameters = hrSystemInitialLoadParameters;
	}

	
	/**
	 * 
	 * "The number of user sessions for which this host is
	 *         storing state information.  A session is a collection
	 *         of processes requiring a single act of user
	 *         authentication and possibly subject to collective job
	 *         control."
	 * 
	 */
	public Integer getHrSystemNumUsers(){
		return hrSystemNumUsers;
	}

	private void setHrSystemNumUsers(Integer hrSystemNumUsers){
		this.hrSystemNumUsers = hrSystemNumUsers;
	}

	
	/**
	 * 
	 * "The number of process contexts currently loaded or
	 *         running on this system."
	 * 
	 */
	public Integer getHrSystemProcesses(){
		return hrSystemProcesses;
	}

	private void setHrSystemProcesses(Integer hrSystemProcesses){
		this.hrSystemProcesses = hrSystemProcesses;
	}

	
	/**
	 * 
	 * "The maximum number of process contexts this system
	 *         can support.  If there is no fixed maximum, the value
	 *         should be zero.  On systems that have a fixed maximum,
	 *         this object can help diagnose failures that occur when
	 *         this maximum is reached."
	 * 
	 */
	public Integer getHrSystemMaxProcesses(){
		return hrSystemMaxProcesses;
	}

	private void setHrSystemMaxProcesses(Integer hrSystemMaxProcesses){
		this.hrSystemMaxProcesses = hrSystemMaxProcesses;
	}


}
