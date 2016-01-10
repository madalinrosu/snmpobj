package snmp.obj.util;

import java.io.Serializable;
import java.util.Comparator;

public class ObjectIDComparator implements Comparator<String>, Serializable {

	private static final long serialVersionUID = -7627986130208726016L;

	@Override
	public int compare(String o1, String o2) {
		String[] oid1Array = o1.split("\\.");
		String[] oid2Array = o2.split("\\.");
		int length = Math.min(oid1Array.length, oid2Array.length);
		for(int i = 0; i < length; i++) {
			if(!oid1Array[i].equals(oid2Array[i])) {
				int subid1 = intValue(oid1Array[i]);
				int subid2 = intValue(oid2Array[i]);
				if (subid1 < subid2) {
					return -1;
				}
				else {
					return 1;
				}
			}
		}
		return (oid1Array.length - oid2Array.length);
	}

	private int intValue(String subid) {
		return (int) (Integer.parseInt(subid) & 0xFFFFFFFFL);
	}
}
