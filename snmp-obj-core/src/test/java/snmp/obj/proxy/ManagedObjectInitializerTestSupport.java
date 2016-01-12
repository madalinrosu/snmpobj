package snmp.obj.proxy;

import java.io.Serializable;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import snmp.obj.SNMPSession;
import snmp.obj.mib.annotations.MIBObjectGroup;
import snmp.obj.mib.annotations.MIBScalarGroup;
import snmp.obj.mib.annotations.MIBSubtree;
import snmp.obj.mib.annotations.MIBVariable;

@RunWith(MockitoJUnitRunner.class)
public abstract class ManagedObjectInitializerTestSupport {

	@Mock
	protected SNMPSession session;

	protected ManagedObjectProxyFactory proxyFactory;

	@Before
	public void setUp() {
		proxyFactory = createProxyFactory();
	}

	protected abstract ManagedObjectProxyFactory createProxyFactory();

	@MIBObjectGroup(oid = "1.3.6.1.2.1")
	public static class Mib2WithSystemAsIntf implements Serializable {

		private static final long serialVersionUID = 1L;

		@MIBSubtree(oid = "1", lazy = true)
		ISystem system;

		public ISystem getSystem() {
			return system;
		}

		public void setSystem(ISystem system) {
			this.system = system;
		}

	}

	@MIBObjectGroup(oid = "1.3.6.1.2.1")
	public static class Mib2WithSystemAsClass implements Serializable {

		private static final long serialVersionUID = 1L;

		@MIBSubtree(oid = "1", lazy = true)
		CSystem system;

		public CSystem getSystem() {
			return system;
		}

		public void setSystem(CSystem system) {
			this.system = system;
		}

	}

	@MIBScalarGroup(oid = "1.3.6.1.2.1.1", name = "system")
	public static interface ISystem extends Serializable {

		@MIBVariable(oid = "1", name = "sysDescr", syntax = "DisplayString", readable = true, writeable = false)
		String getSysDescr();
		void setSysDescr(String sysDescr);

	}

	@MIBScalarGroup(oid = "1.3.6.1.2.1.1")
	public static class CSystem implements Serializable{

		private static final long serialVersionUID = 1L;

		@MIBVariable(oid = "1", name = "sysDescr", syntax = "DisplayString", readable = true, writeable = false)
		private String sysDescr;

		public String getSysDescr() {
			return sysDescr;
		}

		public void setSysDescr(String sysDescr) {
			this.sysDescr = sysDescr;
		}

	}
}
