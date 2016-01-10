package snmp.obj.mib.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import snmp.obj.mib.Access;

@Documented
@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@MIBObject
public @interface MIBVariable {
	String oid();
	String name() default "";
	String syntax() default "";
	Access access() default Access.ReadOnly;
	boolean readable() default true;
	boolean writeable() default false;
	String defaultValue() default "";
}
