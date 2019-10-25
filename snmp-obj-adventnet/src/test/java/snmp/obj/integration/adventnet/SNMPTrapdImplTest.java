package snmp.obj.integration.adventnet;

import java.util.Properties;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import snmp.obj.config.AnnotationConfiguration;
import snmp.obj.mib.standard.rfc1213.IfEntry;
import snmp.obj.mib.standard.snmpv2.LinkUp;
import snmp.obj.mib.standard.snmpv2.SnmpTrap;
import snmp.obj.notification.SNMPTrapd;

@Disabled
public class SNMPTrapdImplTest {

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

	@Test
	public void shouldReceiveTraps() throws InterruptedException {
	}
	


}
