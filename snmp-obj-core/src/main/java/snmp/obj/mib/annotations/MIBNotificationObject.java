package snmp.obj.mib.annotations;

import java.io.Serializable;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target({})
@Retention(RetentionPolicy.RUNTIME)
public @interface MIBNotificationObject {

	String name();
	Class<? extends Serializable> objectClass();

}
