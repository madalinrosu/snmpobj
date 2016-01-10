package snmp.obj.config;



import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class ConfigurationTest {

	@Test
	public void shouldProcessAnnotations() throws Exception {
		AnnotationConfiguration cfg = new AnnotationConfiguration();
		
		cfg.processAnnotations(ManagedObject1.class);
		
		PropertyAccessor accessor1 = cfg.getPropertyAccessor("1.3.6.1.2.1.1.1");
		PropertyAccessor accessor2 = cfg.getPropertyAccessor("1.3.6.1.2.1.1.2");
		Assert.assertNotNull(accessor1);
		Assert.assertNotNull(accessor2);
		Assert.assertTrue(accessor1 instanceof FieldLevelAccessor);
		Assert.assertTrue(accessor2 instanceof MethodLevelAccessor);
	}

	@Test(expected = DuplicateObjectException.class)
	public void shouldNotProcessAnnotations() throws Exception {
		AnnotationConfiguration cfg = new AnnotationConfiguration();
		
		cfg.processAnnotations(ManagedObject2.class);
	}
	
	@Test
	public void shouldScanPackagesAndProcessAnnotations() throws Exception {
		AnnotationConfiguration cfg = new AnnotationConfiguration();

		cfg.processAnnotations("snmp.obj.config");
		PropertyAccessor accessor1 = cfg.getPropertyAccessor("1.3.6.1.2.1.1.1");
		PropertyAccessor accessor2 = cfg.getPropertyAccessor("1.3.6.1.2.1.1.2");
		Assert.assertNotNull(accessor1);
		Assert.assertNotNull(accessor2);
		Assert.assertTrue(accessor1 instanceof FieldLevelAccessor);
		Assert.assertTrue(accessor2 instanceof MethodLevelAccessor);
	}
}
