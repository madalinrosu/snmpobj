package snmp.obj.mib.annotations;

import java.io.Serializable;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@MIBObject
public @interface MIBTable {
	String oid();
	String name() default "";
	boolean readonly() default true;
	boolean lazy() default true;
	Class<? extends Serializable> tableEntry();
	//boolean getBulk() default false;
}

