package snmp.obj.integrations.snmp4j;

import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.snmp4j.asn1.BER;
import org.snmp4j.log.Log4jLogFactory;
import org.snmp4j.log.LogAdapter;
import org.snmp4j.log.LogFactory;

import snmp.obj.config.AnnotationConfiguration;
import snmp.obj.mib.standard.rfc1213.IfEntry;
import snmp.obj.mib.standard.snmpv2.LinkUp;
import snmp.obj.mib.standard.snmpv2.SnmpTrap;
import snmp.obj.notification.Notification;
import snmp.obj.notification.SNMPNotificationListener;
import snmp.obj.notification.SNMPTrapd;

@Ignore
public class SNMPTrapdImplTest {

	static {
		LogFactory.setLogFactory(new Log4jLogFactory());
		BER.setCheckSequenceLength(false);
	}

	private final static LogAdapter logger = LogFactory.getLogger(SNMPTrapdImplTest.class);
	
	private SNMPTrapd trapd;
	
	static Class<?>[] classes = {snmp.obj.mib.standard.rfc1213.System.class, SnmpTrap.class, 
		IfEntry.class, LinkUp.class};

	@Before
	public void setUp() {
		AnnotationConfiguration cfg = new AnnotationConfiguration();
		try {
			Properties props = new Properties();
			props.load(getClass().getResourceAsStream("test.properties"));
			trapd = cfg.setProperties(props).processAnnotations(classes).buildTrapDaemon();
			trapd.start();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void shouldReceiveTraps() throws InterruptedException {
		trapd.registerListener(new SNMPNotificationListener() {
			
			@Override
			public void onNotify(Notification notification) {
				SNMPTrapdImplTest.logger.info(notification.getClass().getSimpleName() + " : " + 
												notification.getSnmpTrapOID());
			}
		});
		Thread.sleep(60000);
	}
	
	@After
	public void tearDown() {
		trapd.stop();
	}


}
