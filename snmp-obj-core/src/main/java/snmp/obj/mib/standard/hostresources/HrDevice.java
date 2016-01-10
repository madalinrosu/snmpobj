package snmp.obj.mib.standard.hostresources;

import java.io.Serializable;
import java.util.List;

import snmp.obj.mib.annotations.MIBObjectGroup;
import snmp.obj.mib.annotations.MIBTable;

@MIBObjectGroup(oid = "1.3.6.1.2.1.25.3")
public class HrDevice implements Serializable {

	private static final long serialVersionUID = 1L;

	@MIBTable(oid = "2", lazy = false, tableEntry = HrDeviceEntry.class)
	private List<HrDeviceEntry> hrDeviceTable;

	@MIBTable(oid = "3", lazy = false, tableEntry = HrProcessorEntry.class)
	private List<HrProcessorEntry> hrProcessorTable;

	@MIBTable(oid = "6", lazy = false, tableEntry = HrDiskStorageEntry.class)
	private List<HrDiskStorageEntry> hrDiskStorageTable;

	public List<HrDiskStorageEntry> getHrDiskStorageTable() {
		return hrDiskStorageTable;
	}

	public void setHrDiskStorageTable(List<HrDiskStorageEntry> hrDiskStorageTable) {
		this.hrDiskStorageTable = hrDiskStorageTable;
	}

	public List<HrDeviceEntry> getHrDeviceTable() {
		return hrDeviceTable;
	}

	public void setHrDeviceTable(List<HrDeviceEntry> hrDeviceTable) {
		this.hrDeviceTable = hrDeviceTable;
	}

	public List<HrProcessorEntry> getHrProcessorTable() {
		return hrProcessorTable;
	}

	public void setHrProcessorTable(List<HrProcessorEntry> hrProcessorTable) {
		this.hrProcessorTable = hrProcessorTable;
	}

	
}
