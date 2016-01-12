package snmp.obj.proxy.cglib;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.BDDMockito;

import snmp.obj.proxy.ManagedObjectInitializerTestSupport;
import snmp.obj.proxy.ManagedObjectProxy;

public class ManagedObjectInitializerImplTest extends ManagedObjectInitializerTestSupport {	
	
	@Override
	protected ManagedObjectProxyFactoryImpl createProxyFactory() {
		return new ManagedObjectProxyFactoryImpl();
	}

	
	@Test
	public void shouldInitializeLazyMib2System() {
		// given a Mib2 tree sample
		Mib2WithSystemAsClass mib2 = new Mib2WithSystemAsClass();
		// and having System group as subtree with SysDescr parameter
		CSystem system = new CSystem();
		system.setSysDescr("This is a test system");
		// and System group being lazy initialized
		ManagedObjectProxy proxy = proxyFactory.createProxy(CSystem.class, session);
		mib2.setSystem((CSystem)proxy);
		
		BDDMockito.given(session.getGroup(Mib2WithSystemAsClass.class)).willReturn(mib2);
		BDDMockito.given(session.getManagedObject(CSystem.class)).willReturn(system);
		
		// when accessing SysDescr parameter
		String result = mib2.getSystem().getSysDescr();
		
		// then System group should have been initialized
		Assert.assertEquals(system.getSysDescr(), result);
	}
	
	
	



}
