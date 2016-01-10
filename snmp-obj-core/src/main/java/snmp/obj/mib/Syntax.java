package snmp.obj.mib;

/**
 * SNMPv2-SMI and SNMPv2-TC
 * @author madalin
 *
 */
public enum Syntax {
	
	/**
	 * SNMPv2-SMI 
	 */
	// built-in ASN.1 types
	INTEGER, 
	OCTET_STRING, 
	OBJECT_IDENTIFIER,
	
	// indistinguishable from INTEGER, but never needs more than
	// 32-bits for a two's complement representation
	Integer32,
	// application-wide types - in network byte order
	IpAddress, // (tagged type for historical reasons)
	Counter, // wraps
	Counter32, // wraps
	Gauge, // doesn't wrap
	Gauge32, // doesn't wrap
	Unsigned32, // an unsigned 32-bit quantity, indistinguishable from Gauge32
	TimeTicks, // hundredths of seconds since an epoch
	Opaque, // for backward-compatibility only
	Counter64, // for counters that wrap in less than one hour with only 32 bits
	
	/**
	 * SNMPv2-TC
	 */
	DisplayString,// OCTET STRING (SIZE (0..255)) 
	PhysAddress,// OCTET STRING
	MacAddress,// OCTET STRING (SIZE (6)) 
	TruthValue,// INTEGER { true(1), false(2) } 
	TestAndIncr,// INTEGER (0..2147483647) 
	AutonomousType,// OBJECT IDENTIFIER 
	VariablePointer,// OBJECT IDENTIFIER 
	RowPointer,// OBJECT IDENTIFIER 
	RowStatus,// INTEGER
	TimeStamp,// TimeTicks
	TimeInterval,// INTEGER (0..2147483647)
	DateAndTime,// OCTET STRING (SIZE (8 | 11)), e.g.: "1992-5-26,13:30:15.0,-4:0" 
	StorageType,// INTEGER
	TDomain,// OBJECT IDENTIFIER
	TAddress,// OCTET STRING (SIZE (1..255)) 
	
	Boolean,// INTEGER
	KBytes,// Integer32 
	ProductID,// OBJECT IDENTIFIER 
	InternationalDisplayString;// OCTET STRING 
}
