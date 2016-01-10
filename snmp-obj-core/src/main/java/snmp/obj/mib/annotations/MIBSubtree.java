package snmp.obj.mib.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Partial Subtrees
 *
 */
@Documented
@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@MIBObject
public @interface MIBSubtree {
	String oid();
	String name() default "";
	boolean readonly() default true;
	boolean lazy() default true;
	boolean getBulk() default false;
}
