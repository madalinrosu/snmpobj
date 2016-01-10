package snmp.obj.integrations.snmp4j;

import java.util.List;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.snmp4j.log.LogFactory;

import snmp.obj.SNMPSession;
import snmp.obj.SNMPSessionFactory;
import snmp.obj.config.AnnotationConfiguration;
import snmp.obj.integrations.snmp4j.util.log.Slf4jLoggerFactory;
import snmp.obj.mib.standard.i.rfc1213mib.IfEntry;
import snmp.obj.util.SNMPObjUtil;


public class SNMPSessionImplTest2i {

	static {
		LogFactory.setLogFactory(new Slf4jLoggerFactory());
//		LogFactory.setLogFactory(new Log4jLogFactory());
	}

	SNMPSessionFactory sessionFactory;
	SNMPSession session;
	
	static Class<?>[] classes = { snmp.obj.mib.standard.i.rfc1213mib.System.class, 
									snmp.obj.mib.standard.i.rfc1213mib.IfEntry.class };
	
	@Before
	public void setUp() {
		AnnotationConfiguration cfg = new AnnotationConfiguration();
		try {
			Properties props = new Properties();
			props.load(getClass().getResourceAsStream("test.properties"));
			sessionFactory = cfg.setProperties(props).processAnnotations(classes).buildSessionFactory();
			session = sessionFactory.createSNMPv2cSession("127.0.0.1", "public", 161, 2, 5000);
//			session = sessionFactory.createSNMPv2cSession("10.99.66.127", "public", 161, 2, 5000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore
	public void shouldGetMib2System() throws Exception {
		snmp.obj.mib.standard.i.rfc1213mib.System mo = session.getScalars(snmp.obj.mib.standard.i.rfc1213mib.System.class);
		
		Assert.assertNotNull(mo);
		Assert.assertNotNull(mo.getSysDescr());
		Assert.assertNotNull(mo.getSysObjectID());
		
		System.out.println(SNMPObjUtil.toString(mo));
	}

	@Test
	public void shouldGetMib2IfTable() throws Exception {
		List<IfEntry> rows = session.getTable(IfEntry.class);
		
		Assert.assertNotNull(rows);
	
		for(IfEntry mo : rows) {
			System.out.println(SNMPObjUtil.toString(mo));
		}
	}

}