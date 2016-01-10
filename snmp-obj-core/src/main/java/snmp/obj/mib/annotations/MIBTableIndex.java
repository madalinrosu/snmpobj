package snmp.obj.mib.annotations;

import java.io.Serializable;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target({})
@Retention(RetentionPolicy.RUNTIME)
public @interface MIBTableIndex {
	public interface SELF extends Serializable {}
	
	String name();
	Class<? extends Serializable> tableEntry() default SELF.class;
}
