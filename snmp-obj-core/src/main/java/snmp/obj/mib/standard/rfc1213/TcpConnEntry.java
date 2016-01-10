package snmp.obj.mib.standard.rfc1213;

import java.io.Serializable;
import snmp.obj.mib.annotations.*;

/**
 * 
 * GENERATED FROM MIB. DO NOT CHANGE MANUALLY.
 * 
 */
@MIBTableEntry(oid = "1.3.6.1.2.1.6.13.1", 
				indexes = { @MIBTableIndex(name = "tcpConnLocalAddress"), @MIBTableIndex(name = "tcpConnLocalPort"), 
						@MIBTableIndex(name = "tcpConnRemAddress"), @MIBTableIndex(name = "tcpConnRemPort") })
public class TcpConnEntry implements Serializable{

	private static final long serialVersionUID = 1L;

	@MIBVariable(oid = "1", name = "tcpConnState", syntax = "INTEGER", readable = true, writeable = true)
	private TcpConnState tcpConnState;

	@MIBVariable(oid = "2", name = "tcpConnLocalAddress", syntax = "IpAddress", readable = true, writeable = false)
	private String tcpConnLocalAddress;

	@MIBVariable(oid = "3", name = "tcpConnLocalPort", syntax = "INTEGER", readable = true, writeable = false)
	private Integer tcpConnLocalPort;

	@MIBVariable(oid = "4", name = "tcpConnRemAddress", syntax = "IpAddress", readable = true, writeable = false)
	private String tcpConnRemAddress;

	@MIBVariable(oid = "5", name = "tcpConnRemPort", syntax = "INTEGER", readable = true, writeable = false)
	private Integer tcpConnRemPort;


	
	/**
	 * 
	 * "The state of this TCP connection.
	 * 
	 *             The only value which may be set by a management
	 *             station is deleteTCB(12).  Accordingly, it is
	 *             appropriate for an agent to return a `badValue'
	 *             response if a management station attempts to set
	 *             this object to any other value.
	 * 
	 *             If a management station sets this object to the
	 *             value deleteTCB(12), then this has the effect of
	 *             deleting the TCB (as defined in RFC 793) of the
	 *             corresponding connection on the managed node,
	 *             resulting in immediate termination of the
	 *             connection.
	 * 
	 *             As an implementation-specific option, a RST
	 *             segment may be sent from the managed node to the
	 *             other TCP endpoint (note however that RST segments
	 *             are not sent reliably)."
	 * 
	 */
	public TcpConnState getTcpConnState(){
		return tcpConnState;
	}

	public void setTcpConnState(TcpConnState tcpConnState){
		this.tcpConnState = tcpConnState;
	}

	
	/**
	 * 
	 * "The local IP address for this TCP connection.  In
	 *             the case of a connection in the listen state which
	 *             is willing to accept connections for any IP
	 *             interface associated with the node, the value
	 *             0.0.0.0 is used."
	 * 
	 */
	public String getTcpConnLocalAddress(){
		return tcpConnLocalAddress;
	}

	private void setTcpConnLocalAddress(String tcpConnLocalAddress){
		this.tcpConnLocalAddress = tcpConnLocalAddress;
	}

	
	/**
	 * 
	 * "The local port number for this TCP connection."
	 * 
	 */
	public Integer getTcpConnLocalPort(){
		return tcpConnLocalPort;
	}

	private void setTcpConnLocalPort(Integer tcpConnLocalPort){
		this.tcpConnLocalPort = tcpConnLocalPort;
	}

	
	/**
	 * 
	 * "The remote IP address for this TCP connection."
	 * 
	 */
	public String getTcpConnRemAddress(){
		return tcpConnRemAddress;
	}

	private void setTcpConnRemAddress(String tcpConnRemAddress){
		this.tcpConnRemAddress = tcpConnRemAddress;
	}

	
	/**
	 * 
	 * "The remote port number for this TCP connection."
	 * 
	 */
	public Integer getTcpConnRemPort(){
		return tcpConnRemPort;
	}

	private void setTcpConnRemPort(Integer tcpConnRemPort){
		this.tcpConnRemPort = tcpConnRemPort;
	}


	public enum TcpConnState{
		closed(1),
		listen(2),
		synSent(3),
		synReceived(4),
		established(5),
		finWait1(6),
		finWait2(7),
		closeWait(8),
		lastAck(9),
		closing(10),
		timeWait(11),
		deleteTCB(12),;

		private final int value;

		private TcpConnState(int value){
			this.value = value;
		}

		public int value(){
			return value;
		}

		public static TcpConnState fromValue(int value){
			for(TcpConnState constant : values()){
				if(constant.value() == value){
					return constant;
				}
			}
			return null;
		}

	}

}
