package snmp.obj.config;



import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

@Disabled
public class ConfigurationTest {

	@Test
	public void shouldProcessAnnotations() throws Exception {
		AnnotationConfiguration cfg = new AnnotationConfiguration();
		
		cfg.processAnnotations(ManagedObject1.class);
		
		PropertyAccessor accessor1 = cfg.getPropertyAccessor("1.3.6.1.2.1.1.1");
		PropertyAccessor accessor2 = cfg.getPropertyAccessor("1.3.6.1.2.1.1.2");
		Assertions.assertNotNull(accessor1);
		Assertions.assertNotNull(accessor2);
		Assertions.assertTrue(accessor1 instanceof FieldLevelAccessor);
		Assertions.assertTrue(accessor2 instanceof MethodLevelAccessor);
	}

	@Test//(expected = DuplicateObjectException.class)
	public void shouldNotProcessAnnotations() throws Exception {
		final AnnotationConfiguration cfg = new AnnotationConfiguration();
		
		Assertions.assertThrows(DuplicateObjectException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				cfg.processAnnotations(ManagedObject2.class);
			}
		});
	}
	
	@Test
	public void shouldScanPackagesAndProcessAnnotations() throws Exception {
		AnnotationConfiguration cfg = new AnnotationConfiguration();

		cfg.processAnnotations("snmp.obj.config");
		PropertyAccessor accessor1 = cfg.getPropertyAccessor("1.3.6.1.2.1.1.1");
		PropertyAccessor accessor2 = cfg.getPropertyAccessor("1.3.6.1.2.1.1.2");
		Assertions.assertNotNull(accessor1);
		Assertions.assertNotNull(accessor2);
		Assertions.assertTrue(accessor1 instanceof FieldLevelAccessor);
		Assertions.assertTrue(accessor2 instanceof MethodLevelAccessor);
	}
}
