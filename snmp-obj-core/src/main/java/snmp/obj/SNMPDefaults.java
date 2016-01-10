package snmp.obj;

public interface SNMPDefaults {
	SNMPVersion DEFAULT_VERSION = SNMPVersion.v1;
	
	int DEFAULT_PORT = 161;
	int DEFAULT_TRAP_PORT = 162;
	int DEFAULT_RETRY = 2;
	long DEFAULT_TIMEOUT = 5000;
	
	String DEFAULT_RDONLY_COMMUNITY = "public";
	String DEFAULT_RDWR_COMMUNITY = "private";
	String DEFAULT_TRAP_COMMUNITY = "public";

	// TODO
	// DEFAULT_SECURITY_LEVEL
	// DEFAULT_AUTH_ALG;
	// DEFAULT_PRIV_ALG;
}
