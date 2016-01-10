package snmp.obj.mib.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import snmp.obj.mib.standard.rfc1213.System;
import snmp.obj.mib.standard.snmpv2.SnmpTrap;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@MIBObject
public @interface MIBNotification {
	String oid();
	String name() default "";
	MIBNotificationObject[] objects() default {};
	
	MIBNotificationObject[] snmpv2() default {
			@MIBNotificationObject(name = "sysUpTime", objectClass = System.class),
			@MIBNotificationObject(name = "snmpTrapOID", objectClass = SnmpTrap.class),
			//@MIBNotificationObject(name = "snmpTrapEnterprise", objectClass = SnmpTrap.class)
	};
	
}
