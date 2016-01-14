package snmp.obj.integrations.snmp4j;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.snmp4j.log.LogFactory;

import snmp.obj.integrations.snmp4j.util.log.Slf4jLoggerFactory;
import snmp.obj.mib.standard.c.rfc1213mib.IfEntry;
import snmp.obj.test.support.SNMPIntegrationTestSupport;
import snmp.obj.util.SNMPObjUtil;


public class SNMPSessionImplTest2c extends SNMPIntegrationTestSupport {

	static {
		LogFactory.setLogFactory(new Slf4jLoggerFactory());
		//		LogFactory.setLogFactory(new Log4jLogFactory());
	}

	static Class<?>[] classes = { snmp.obj.mib.standard.c.rfc1213mib.System.class, 
		snmp.obj.mib.standard.c.rfc1213mib.IfEntry.class };

	@Override
	protected Properties getProperties() throws IOException {
		Properties props = new Properties();
		props.load(getClass().getResourceAsStream("test.properties"));
		return props;
	}

	@Override
	protected Class<?>[] getClasses() {
		return classes;
	}


	@Test
	public void shouldGetMib2System() throws Exception {
		snmp.obj.mib.standard.c.rfc1213mib.System mo = session.getScalars(snmp.obj.mib.standard.c.rfc1213mib.System.class);

		Assert.assertNotNull(mo);
		Assert.assertTrue(mo.getSysDescr().startsWith("SNMP4J-Agent"));
		Assert.assertNotNull(mo.getSysObjectID());

		System.out.println(SNMPObjUtil.toString(mo));
	}

	@Ignore
	public void shouldGetMib2IfTable() throws Exception {
		List<IfEntry> rows = session.getTable(IfEntry.class);

		Assert.assertNotNull(rows);

		for(IfEntry mo : rows) {
			System.out.println(SNMPObjUtil.toString(mo));
		}
	}


}
