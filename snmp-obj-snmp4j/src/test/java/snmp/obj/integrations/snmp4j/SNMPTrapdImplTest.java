package snmp.obj.integrations.snmp4j;

import java.util.Properties;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.snmp4j.asn1.BER;
import org.snmp4j.log.LogAdapter;
import org.snmp4j.log.LogFactory;

import snmp.obj.config.AnnotationConfiguration;
import snmp.obj.integrations.snmp4j.util.log.Slf4jLoggerFactory;
import snmp.obj.mib.standard.rfc1213.IfEntry;
import snmp.obj.mib.standard.snmpv2.LinkUp;
import snmp.obj.mib.standard.snmpv2.SnmpTrap;
import snmp.obj.notification.Notification;
import snmp.obj.notification.SNMPNotificationListener;
import snmp.obj.notification.SNMPTrapd;

@Disabled
public class SNMPTrapdImplTest {

	static {
		LogFactory.setLogFactory(new Slf4jLoggerFactory());
		BER.setCheckSequenceLength(false);
	}

	private final static LogAdapter logger = LogFactory.getLogger(SNMPTrapdImplTest.class);
	
	private SNMPTrapd trapd;
	
	static Class<?>[] classes = {snmp.obj.mib.standard.rfc1213.System.class, SnmpTrap.class, 
		IfEntry.class, LinkUp.class};

	@BeforeEach
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

	@AfterEach
	public void tearDown() {
		trapd.stop();
	}

	@DisplayName("test trap receiver")
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
	


}
