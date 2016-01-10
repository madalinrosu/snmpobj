package snmp.obj.mib.standard.snmpv2;

import snmp.obj.mib.annotations.MIBNotification;
import snmp.obj.notification.Notification;

@MIBNotification(oid = "1.3.6.1.6.3.1.1.5.1", name = "coldStart")
public final class ColdStart extends Notification {
	private static final long serialVersionUID = 1L;

	public ColdStart() {
	}

}
