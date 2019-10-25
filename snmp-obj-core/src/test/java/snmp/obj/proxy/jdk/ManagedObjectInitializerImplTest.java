package snmp.obj.proxy.jdk;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;

import snmp.obj.dyn.DynamicManagedObjectFactory;
import snmp.obj.proxy.ManagedObjectInitializerTestSupport;
import snmp.obj.proxy.ManagedObjectProxy;

public class ManagedObjectInitializerImplTest extends ManagedObjectInitializerTestSupport {	
	
	@Override
	protected ManagedObjectJdkProxyFactoryImpl createProxyFactory() {
		return new ManagedObjectJdkProxyFactoryImpl();
	}

	
	@Test
	public void shouldInitializeLazyMib2System() {
		// given a Mib2 tree sample
		Mib2WithSystemAsIntf mib2 = new Mib2WithSystemAsIntf();
		// and having System group as subtree with SysDescr parameter
		ISystem system = DynamicManagedObjectFactory.createObject(ISystem.class);
		system.setSysDescr("This is a test system");
		// and System group being lazy initialized
		ManagedObjectProxy proxy = proxyFactory.createProxy(ISystem.class, session);
		mib2.setSystem((ISystem)proxy);
		
		BDDMockito.given(session.getGroup(Mib2WithSystemAsIntf.class)).willReturn(mib2);
		BDDMockito.given(session.getManagedObject(ISystem.class)).willReturn(system);
		
		// when accessing SysDescr parameter
		String result = mib2.getSystem().getSysDescr();
		
		// then System group should have been initialized
		Assertions.assertEquals(system.getSysDescr(), result);
	}
	
	
	



}
