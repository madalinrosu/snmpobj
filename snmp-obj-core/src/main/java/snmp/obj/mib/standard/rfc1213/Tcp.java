package snmp.obj.mib.standard.rfc1213;

import java.io.Serializable;
import snmp.obj.mib.annotations.*;

/**
 * 
 * GENERATED FROM MIB. DO NOT CHANGE MANUALLY.
 * 
 */
@MIBScalarGroup(oid = "1.3.6.1.2.1.6")
public class Tcp implements Serializable{

	private static final long serialVersionUID = 1L;

	@MIBVariable(oid = "1", name = "tcpRtoAlgorithm", syntax = "INTEGER", readable = true, writeable = false)
	private TcpRtoAlgorithm tcpRtoAlgorithm;

	@MIBVariable(oid = "2", name = "tcpRtoMin", syntax = "INTEGER", readable = true, writeable = false)
	private Integer tcpRtoMin;

	@MIBVariable(oid = "3", name = "tcpRtoMax", syntax = "INTEGER", readable = true, writeable = false)
	private Integer tcpRtoMax;

	@MIBVariable(oid = "4", name = "tcpMaxConn", syntax = "INTEGER", readable = true, writeable = false)
	private Integer tcpMaxConn;

	@MIBVariable(oid = "5", name = "tcpActiveOpens", syntax = "Counter", readable = true, writeable = false)
	private Integer tcpActiveOpens;

	@MIBVariable(oid = "6", name = "tcpPassiveOpens", syntax = "Counter", readable = true, writeable = false)
	private Integer tcpPassiveOpens;

	@MIBVariable(oid = "7", name = "tcpAttemptFails", syntax = "Counter", readable = true, writeable = false)
	private Integer tcpAttemptFails;

	@MIBVariable(oid = "8", name = "tcpEstabResets", syntax = "Counter", readable = true, writeable = false)
	private Integer tcpEstabResets;

	@MIBVariable(oid = "9", name = "tcpCurrEstab", syntax = "Gauge", readable = true, writeable = false)
	private Integer tcpCurrEstab;

	@MIBVariable(oid = "10", name = "tcpInSegs", syntax = "Counter", readable = true, writeable = false)
	private Integer tcpInSegs;

	@MIBVariable(oid = "11", name = "tcpOutSegs", syntax = "Counter", readable = true, writeable = false)
	private Integer tcpOutSegs;

	@MIBVariable(oid = "12", name = "tcpRetransSegs", syntax = "Counter", readable = true, writeable = false)
	private Integer tcpRetransSegs;

	@MIBVariable(oid = "14", name = "tcpInErrs", syntax = "Counter", readable = true, writeable = false)
	private Integer tcpInErrs;

	@MIBVariable(oid = "15", name = "tcpOutRsts", syntax = "Counter", readable = true, writeable = false)
	private Integer tcpOutRsts;


	
	/**
	 * 
	 * "The algorithm used to determine the timeout value
	 *             used for retransmitting unacknowledged octets."
	 * 
	 */
	public TcpRtoAlgorithm getTcpRtoAlgorithm(){
		return tcpRtoAlgorithm;
	}

	private void setTcpRtoAlgorithm(TcpRtoAlgorithm tcpRtoAlgorithm){
		this.tcpRtoAlgorithm = tcpRtoAlgorithm;
	}

	
	/**
	 * 
	 * "The minimum value permitted by a TCP
	 *             implementation for the retransmission timeout,
	 *             measured in milliseconds.  More refined semantics
	 *             for objects of this type depend upon the algorithm
	 *             used to determine the retransmission timeout.  In
	 *             particular, when the timeout algorithm is rsre(3),
	 *             an object of this type has the semantics of the
	 *             LBOUND quantity described in RFC 793."
	 * 
	 */
	public Integer getTcpRtoMin(){
		return tcpRtoMin;
	}

	private void setTcpRtoMin(Integer tcpRtoMin){
		this.tcpRtoMin = tcpRtoMin;
	}

	
	/**
	 * 
	 * "The maximum value permitted by a TCP
	 *             implementation for the retransmission timeout,
	 *             measured in milliseconds.  More refined semantics
	 *             for objects of this type depend upon the algorithm
	 *             used to determine the retransmission timeout.  In
	 *             particular, when the timeout algorithm is rsre(3),
	 *             an object of this type has the semantics of the
	 *             UBOUND quantity described in RFC 793."
	 * 
	 */
	public Integer getTcpRtoMax(){
		return tcpRtoMax;
	}

	private void setTcpRtoMax(Integer tcpRtoMax){
		this.tcpRtoMax = tcpRtoMax;
	}

	
	/**
	 * 
	 * "The limit on the total number of TCP connections
	 *             the entity can support.  In entities where the
	 *             maximum number of connections is dynamic, this
	 *             object should contain the value -1."
	 * 
	 */
	public Integer getTcpMaxConn(){
		return tcpMaxConn;
	}

	private void setTcpMaxConn(Integer tcpMaxConn){
		this.tcpMaxConn = tcpMaxConn;
	}

	
	/**
	 * 
	 * "The number of times TCP connections have made a
	 *             direct transition to the SYN-SENT state from the
	 *             CLOSED state."
	 * 
	 */
	public Integer getTcpActiveOpens(){
		return tcpActiveOpens;
	}

	private void setTcpActiveOpens(Integer tcpActiveOpens){
		this.tcpActiveOpens = tcpActiveOpens;
	}

	
	/**
	 * 
	 * "The number of times TCP connections have made a
	 *             direct transition to the SYN-RCVD state from the
	 *             LISTEN state."
	 * 
	 */
	public Integer getTcpPassiveOpens(){
		return tcpPassiveOpens;
	}

	private void setTcpPassiveOpens(Integer tcpPassiveOpens){
		this.tcpPassiveOpens = tcpPassiveOpens;
	}

	
	/**
	 * 
	 * "The number of times TCP connections have made a
	 *             direct transition to the CLOSED state from either
	 *             the SYN-SENT state or the SYN-RCVD state, plus the
	 *             number of times TCP connections have made a direct
	 *             transition to the LISTEN state from the SYN-RCVD
	 *             state."
	 * 
	 */
	public Integer getTcpAttemptFails(){
		return tcpAttemptFails;
	}

	private void setTcpAttemptFails(Integer tcpAttemptFails){
		this.tcpAttemptFails = tcpAttemptFails;
	}

	
	/**
	 * 
	 * "The number of times TCP connections have made a
	 *             direct transition to the CLOSED state from either
	 *             the ESTABLISHED state or the CLOSE-WAIT state."
	 * 
	 */
	public Integer getTcpEstabResets(){
		return tcpEstabResets;
	}

	private void setTcpEstabResets(Integer tcpEstabResets){
		this.tcpEstabResets = tcpEstabResets;
	}

	
	/**
	 * 
	 * "The number of TCP connections for which the
	 *             current state is either ESTABLISHED or CLOSE-
	 *             WAIT."
	 * 
	 */
	public Integer getTcpCurrEstab(){
		return tcpCurrEstab;
	}

	private void setTcpCurrEstab(Integer tcpCurrEstab){
		this.tcpCurrEstab = tcpCurrEstab;
	}

	
	/**
	 * 
	 * "The total number of segments received, including
	 *             those received in error.  This count includes
	 *             segments received on currently established
	 *             connections."
	 * 
	 */
	public Integer getTcpInSegs(){
		return tcpInSegs;
	}

	private void setTcpInSegs(Integer tcpInSegs){
		this.tcpInSegs = tcpInSegs;
	}

	
	/**
	 * 
	 * "The total number of segments sent, including
	 *             those on current connections but excluding those
	 *             containing only retransmitted octets."
	 * 
	 */
	public Integer getTcpOutSegs(){
		return tcpOutSegs;
	}

	private void setTcpOutSegs(Integer tcpOutSegs){
		this.tcpOutSegs = tcpOutSegs;
	}

	
	/**
	 * 
	 * "The total number of segments retransmitted - that
	 *             is, the number of TCP segments transmitted
	 *             containing one or more previously transmitted
	 *             octets."
	 * 
	 */
	public Integer getTcpRetransSegs(){
		return tcpRetransSegs;
	}

	private void setTcpRetransSegs(Integer tcpRetransSegs){
		this.tcpRetransSegs = tcpRetransSegs;
	}

	
	/**
	 * 
	 * "The total number of segments received in error
	 *             (e.g., bad TCP checksums)."
	 * 
	 */
	public Integer getTcpInErrs(){
		return tcpInErrs;
	}

	private void setTcpInErrs(Integer tcpInErrs){
		this.tcpInErrs = tcpInErrs;
	}

	
	/**
	 * 
	 * "The number of TCP segments sent containing the
	 *             RST flag."
	 * 
	 */
	public Integer getTcpOutRsts(){
		return tcpOutRsts;
	}

	private void setTcpOutRsts(Integer tcpOutRsts){
		this.tcpOutRsts = tcpOutRsts;
	}


	public enum TcpRtoAlgorithm{
		other(1),
		constant(2),
		rsre(3),
		vanj(4),;

		private final int value;

		private TcpRtoAlgorithm(int value){
			this.value = value;
		}

		public int value(){
			return value;
		}

		public static TcpRtoAlgorithm fromValue(int value){
			for(TcpRtoAlgorithm constant : values()){
				if(constant.value() == value){
					return constant;
				}
			}
			return null;
		}

	}

}
