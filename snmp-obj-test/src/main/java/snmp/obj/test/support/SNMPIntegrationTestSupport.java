package snmp.obj.test.support;

import java.io.IOException;
import java.util.Properties;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import snmp.obj.SNMPSession;
import snmp.obj.SNMPSessionFactory;
import snmp.obj.config.AnnotationConfiguration;
import snmp.obj.test.agent.SNMPSimpleTestAgent;


public abstract class SNMPIntegrationTestSupport {


	protected SNMPSession session;
	
	private static SNMPSimpleTestAgent agent;


	@BeforeAll
	public static void createAgent() throws Exception {
		agent = new SNMPSimpleTestAgent();
		agent.start();
	}
	
	@AfterAll
	public static void releaseAgent() throws Exception {
		if(agent != null) {
			agent.stop();
			agent= null;
		}
	}
	
	@BeforeEach
	public void setUp() {
		try {
			AnnotationConfiguration cfg = new AnnotationConfiguration();
			Properties properties = getProperties();
			properties.list(System.out);
			SNMPSessionFactory sessionFactory = cfg.setProperties(properties).processAnnotations(getClasses()).buildSessionFactory();
			session = sessionFactory.createSNMPv2cSession("127.0.0.1", "public", 10161, 2, 5000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@AfterEach
	public void tearDown() {
	}

	protected abstract Properties getProperties() throws IOException;
	
	protected abstract Class<?>[] getClasses();


}
