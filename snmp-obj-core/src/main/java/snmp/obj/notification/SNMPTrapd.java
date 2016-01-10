package snmp.obj.notification;

public interface SNMPTrapd {

	void start();
	void stop();
	
	void registerListener(SNMPNotificationListener listener);
	void unregisterListener(SNMPNotificationListener listener);
	void unregisterAllListeners();
}
