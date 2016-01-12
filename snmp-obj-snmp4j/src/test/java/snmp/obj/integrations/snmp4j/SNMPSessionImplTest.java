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
import snmp.obj.mib.standard.rfc1213.IfEntry;
import snmp.obj.mib.standard.rfc1213.Interfaces;
import snmp.obj.mib.standard.rfc1213.Mib2;
import snmp.obj.mib.standard.usm.UsmUserEntry;
import snmp.obj.util.SNMPObjUtil;

@Ignore
public class SNMPSessionImplTest {

	static {
		LogFactory.setLogFactory(new Slf4jLoggerFactory());
//		LogFactory.setLogFactory(new Log4jLogFactory());
	}

	SNMPSessionFactory sessionFactory;
	SNMPSession session;
	
	static Class<?>[] classes = {Mib2.class, Interfaces.class, snmp.obj.mib.standard.rfc1213.System.class, IfEntry.class, UsmUserEntry.class };
	
	@Before
	public void setUp() {
		AnnotationConfiguration cfg = new AnnotationConfiguration();
		try {
			Properties props = new Properties();
			props.load(getClass().getResourceAsStream("test.properties"));
			sessionFactory = cfg.setProperties(props).processAnnotations(classes).buildSessionFactory();
//			session = sessionFactory.createSNMPv2cSession("127.0.0.1", "public", 161, 2, 5000);
			session = sessionFactory.createSNMPv2cSession("10.99.66.127", "public", 161, 2, 5000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void shouldGetMib2LazyInitializes() throws Exception {
		Mib2 mib2 = session.getGroup(Mib2.class);
		
		Assert.assertNotNull(mib2);
		Assert.assertNotNull(mib2.getSystem().getSysObjectID());
		//Assert.assertNotNull(mib2.getSystem().getSysDescr());
		Assert.assertEquals(mib2.getInterfaces().getIfNumber().longValue(), mib2.getInterfaces().getIfTable().size());
		
		//System.out.println(MIBObjectToString.toString(mib2));
	}

	@Ignore
	public void shouldGetMib2System() throws Exception {
		snmp.obj.mib.standard.rfc1213.System mo = session.getScalars(snmp.obj.mib.standard.rfc1213.System.class);
		
		Assert.assertNotNull(mo);
		Assert.assertNotNull(mo.getSysDescr());
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

//	@Test
//	public void shouldGetUsmUserTable() throws Exception {
//		List<UsmUserEntry> rows = session.getTable(UsmUserEntry.class);
//		
//		Assert.assertNotNull(rows);
//	
//		for(UsmUserEntry mo : rows) {
//			System.out.println(MIBObjectToString.toString(mo));
//		}
//	}

}
