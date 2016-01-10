package snmp.obj.notification;

import java.util.List;
import java.util.Vector;

import snmp.obj.SNMPDefaults;

public abstract class DefaultSNMPTrapd implements SNMPTrapd, SNMPDefaults {
 
	protected List<SNMPNotificationListener> listeners = new Vector<>();
	
	public DefaultSNMPTrapd() {
	}

	@Override
	public void registerListener(SNMPNotificationListener listener) {
		listeners.add(listener);
	}

	@Override
	public void unregisterListener(SNMPNotificationListener listener) {
		listeners.remove(listener);
	}

	@Override
	public void unregisterAllListeners() {
		listeners.clear();
	}

	protected void notifyAllListeners(final Notification notification) {
		for(SNMPNotificationListener listener : listeners) {
			listener.onNotify(notification);
		}
	}
}
