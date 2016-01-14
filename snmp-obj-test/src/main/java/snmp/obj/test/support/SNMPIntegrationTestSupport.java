package snmp.obj.test.support;

import java.util.Properties;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import snmp.obj.SNMPSession;
import snmp.obj.SNMPSessionFactory;
import snmp.obj.config.AnnotationConfiguration;
import snmp.obj.test.agent.SNMPSimpleTestAgent;


public abstract class SNMPIntegrationTestSupport {


	protected SNMPSession session;
	
	private static SNMPSimpleTestAgent agent;


	@BeforeClass
	public static void createAgent() throws Exception {
		agent = new SNMPSimpleTestAgent();
		agent.start();
	}
	
	@AfterClass
	public static void releaseAgent() throws Exception {
		if(agent != null) {
			agent.stop();
			agent= null;
		}
	}
	
	@Before
	public void setUp() {
		try {
			AnnotationConfiguration cfg = new AnnotationConfiguration();
			SNMPSessionFactory sessionFactory = cfg.setProperties(getProperties()).processAnnotations(getClasses()).buildSessionFactory();
			session = sessionFactory.createSNMPv2cSession("127.0.0.1", "public", 10161, 2, 5000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected abstract Properties getProperties();
	
	protected abstract Class<?>[] getClasses();

	@Before
	public void tearDown() {
	}

}
