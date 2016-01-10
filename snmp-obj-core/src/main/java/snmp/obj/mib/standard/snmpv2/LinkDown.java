package snmp.obj.mib.standard.snmpv2;

import snmp.obj.mib.annotations.MIBNotification;
import snmp.obj.mib.annotations.MIBNotificationObject;
import snmp.obj.mib.annotations.MIBVariable;
import snmp.obj.mib.standard.rfc1213.IfEntry;
import snmp.obj.mib.standard.rfc1213.IfEntry.IfAdminStatus;
import snmp.obj.mib.standard.rfc1213.IfEntry.IfOperStatus;
import snmp.obj.notification.Notification;

@MIBNotification(oid = "1.3.6.1.6.3.1.1.5.3", name = "linkDown", 
			objects = { @MIBNotificationObject(name = "ifIndex", objectClass = IfEntry.class),
		 				@MIBNotificationObject(name = "ifAdminStatus", objectClass = IfEntry.class),
		 				@MIBNotificationObject(name = "ifOperStatus", objectClass = IfEntry.class) })
public class LinkDown extends Notification {
	private static final long serialVersionUID = 1L;

	@MIBVariable(oid = "1.3.6.1.2.1.2.2.1.1", name = "ifIndex", syntax = "INTEGER", readable = true, writeable = false)
	private Integer ifIndex;

	@MIBVariable(oid = "1.3.6.1.2.1.2.2.1.7", name = "ifAdminStatus", syntax = "INTEGER", readable = true, writeable = true)
	private IfAdminStatus ifAdminStatus;

	@MIBVariable(oid = "1.3.6.1.2.1.2.2.1.8", name = "ifOperStatus", syntax = "INTEGER", readable = true, writeable = false)
	private IfOperStatus ifOperStatus;

	public LinkDown() {
	}

	public Integer getIfIndex() {
		return ifIndex;
	}

	private void setIfIndex(Integer ifIndex) {
		this.ifIndex = ifIndex;
	}

	public IfAdminStatus getIfAdminStatus() {
		return ifAdminStatus;
	}

	private void setIfAdminStatus(IfAdminStatus ifAdminStatus) {
		this.ifAdminStatus = ifAdminStatus;
	}

	public IfOperStatus getIfOperStatus() {
		return ifOperStatus;
	}

	private void setIfOperStatus(IfOperStatus ifOperStatus) {
		this.ifOperStatus = ifOperStatus;
	}

	
}
