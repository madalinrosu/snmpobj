package snmp.obj.mib.standard.rfc1213;

import java.io.Serializable;

import snmp.obj.mib.annotations.MIBTableEntry;
import snmp.obj.mib.annotations.MIBTableIndex;
import snmp.obj.mib.annotations.MIBVariable;

/**
 * 
 * GENERATED FROM MIB. DO NOT CHANGE MANUALLY.
 * 
 */
@MIBTableEntry(oid = "1.3.6.1.2.1.2.2.1", indexes = @MIBTableIndex(name = "ifIndex") )
public class IfEntry implements Serializable{

	private static final long serialVersionUID = 1L;

	@MIBVariable(oid = "1", name = "ifIndex", syntax = "INTEGER", readable = true, writeable = false)
	private Integer ifIndex;

	@MIBVariable(oid = "2", name = "ifDescr", syntax = "DisplayString", readable = true, writeable = false)
	private String ifDescr;

	@MIBVariable(oid = "3", name = "ifType", syntax = "INTEGER", readable = true, writeable = false)
	private IfType ifType;

	@MIBVariable(oid = "4", name = "ifMtu", syntax = "INTEGER", readable = true, writeable = false)
	private Integer ifMtu;

	@MIBVariable(oid = "5", name = "ifSpeed", syntax = "Gauge", readable = true, writeable = false)
	private Integer ifSpeed;

	@MIBVariable(oid = "6", name = "ifPhysAddress", syntax = "PhysAddress", readable = true, writeable = false)
	private String ifPhysAddress;

	@MIBVariable(oid = "7", name = "ifAdminStatus", syntax = "INTEGER", readable = true, writeable = true)
	private IfAdminStatus ifAdminStatus;

	@MIBVariable(oid = "8", name = "ifOperStatus", syntax = "INTEGER", readable = true, writeable = false)
	private IfOperStatus ifOperStatus;

	@MIBVariable(oid = "9", name = "ifLastChange", syntax = "TimeTicks", readable = true, writeable = false)
	private Integer ifLastChange;

	@MIBVariable(oid = "10", name = "ifInOctets", syntax = "Counter", readable = true, writeable = false)
	private Integer ifInOctets;

	@MIBVariable(oid = "11", name = "ifInUcastPkts", syntax = "Counter", readable = true, writeable = false)
	private Integer ifInUcastPkts;

	@MIBVariable(oid = "12", name = "ifInNUcastPkts", syntax = "Counter", readable = true, writeable = false)
	private Integer ifInNUcastPkts;

	@MIBVariable(oid = "13", name = "ifInDiscards", syntax = "Counter", readable = true, writeable = false)
	private Integer ifInDiscards;

	@MIBVariable(oid = "14", name = "ifInErrors", syntax = "Counter", readable = true, writeable = false)
	private Integer ifInErrors;

	@MIBVariable(oid = "15", name = "ifInUnknownProtos", syntax = "Counter", readable = true, writeable = false)
	private Integer ifInUnknownProtos;

	@MIBVariable(oid = "16", name = "ifOutOctets", syntax = "Counter", readable = true, writeable = false)
	private Integer ifOutOctets;

	@MIBVariable(oid = "17", name = "ifOutUcastPkts", syntax = "Counter", readable = true, writeable = false)
	private Integer ifOutUcastPkts;

	@MIBVariable(oid = "18", name = "ifOutNUcastPkts", syntax = "Counter", readable = true, writeable = false)
	private Integer ifOutNUcastPkts;

	@MIBVariable(oid = "19", name = "ifOutDiscards", syntax = "Counter", readable = true, writeable = false)
	private Integer ifOutDiscards;

	@MIBVariable(oid = "20", name = "ifOutErrors", syntax = "Counter", readable = true, writeable = false)
	private Integer ifOutErrors;

	@MIBVariable(oid = "21", name = "ifOutQLen", syntax = "Gauge", readable = true, writeable = false)
	private Integer ifOutQLen;

	@MIBVariable(oid = "22", name = "ifSpecific", syntax = "OBJECT_IDENTIFIER", readable = true, writeable = false)
	private String ifSpecific;


	
	/**
	 * 
	 * "A unique value for each interface.  Its value
	 *             ranges between 1 and the value of ifNumber.  The
	 *             value for each interface must remain constant at
	 *             least from one re-initialization of the entity's
	 *             network management system to the next re-
	 *             initialization."
	 * 
	 */
	public Integer getIfIndex(){
		return ifIndex;
	}

	public void setIfIndex(Integer ifIndex){
		this.ifIndex = ifIndex;
	}

	
	/**
	 * 
	 * "A textual string containing information about the
	 *             interface.  This string should include the name of
	 *             the manufacturer, the product name and the version
	 *             of the hardware interface."
	 * 
	 */
	public String getIfDescr(){
		return ifDescr;
	}

	private void setIfDescr(String ifDescr){
		this.ifDescr = ifDescr;
	}

	
	/**
	 * 
	 * "The type of interface, distinguished according to
	 *             the physical/link protocol(s) immediately `below'
	 *             the network layer in the protocol stack."
	 * 
	 */
	public IfType getIfType(){
		return ifType;
	}

	private void setIfType(IfType ifType){
		this.ifType = ifType;
	}

	
	/**
	 * 
	 * "The size of the largest datagram which can be
	 *             sent/received on the interface, specified in
	 *             octets.  For interfaces that are used for
	 *             transmitting network datagrams, this is the size
	 *             of the largest network datagram that can be sent
	 *             on the interface."
	 * 
	 */
	public Integer getIfMtu(){
		return ifMtu;
	}

	private void setIfMtu(Integer ifMtu){
		this.ifMtu = ifMtu;
	}

	
	/**
	 * 
	 * "An estimate of the interface's current bandwidth
	 *             in bits per second.  For interfaces which do not
	 *             vary in bandwidth or for those where no accurate
	 *             estimation can be made, this object should contain
	 *             the nominal bandwidth."
	 * 
	 */
	public Integer getIfSpeed(){
		return ifSpeed;
	}

	private void setIfSpeed(Integer ifSpeed){
		this.ifSpeed = ifSpeed;
	}

	
	/**
	 * 
	 * "The interface's address at the protocol layer
	 *             immediately `below' the network layer in the
	 *             protocol stack.  For interfaces which do not have
	 *             such an address (e.g., a serial line), this object
	 *             should contain an octet string of zero length."
	 * 
	 */
	public String getIfPhysAddress(){
		return ifPhysAddress;
	}

	private void setIfPhysAddress(String ifPhysAddress){
		this.ifPhysAddress = ifPhysAddress;
	}

	
	/**
	 * 
	 * "The desired state of the interface.  The
	 *             testing(3) state indicates that no operational
	 *             packets can be passed."
	 * 
	 */
	public IfAdminStatus getIfAdminStatus(){
		return ifAdminStatus;
	}

	public void setIfAdminStatus(IfAdminStatus ifAdminStatus){
		this.ifAdminStatus = ifAdminStatus;
	}

	
	/**
	 * 
	 * "The current operational state of the interface.
	 *             The testing(3) state indicates that no operational
	 *             packets can be passed."
	 * 
	 */
	public IfOperStatus getIfOperStatus(){
		return ifOperStatus;
	}

	private void setIfOperStatus(IfOperStatus ifOperStatus){
		this.ifOperStatus = ifOperStatus;
	}

	
	/**
	 * 
	 * "The value of sysUpTime at the time the interface
	 *             entered its current operational state.  If the
	 *             current state was entered prior to the last re-
	 *             initialization of the local network management
	 *             subsystem, then this object contains a zero
	 *             value."
	 * 
	 */
	public Integer getIfLastChange(){
		return ifLastChange;
	}

	private void setIfLastChange(Integer ifLastChange){
		this.ifLastChange = ifLastChange;
	}

	
	/**
	 * 
	 * "The total number of octets received on the
	 *             interface, including framing characters."
	 * 
	 */
	public Integer getIfInOctets(){
		return ifInOctets;
	}

	private void setIfInOctets(Integer ifInOctets){
		this.ifInOctets = ifInOctets;
	}

	
	/**
	 * 
	 * "The number of subnetwork-unicast packets
	 *             delivered to a higher-layer protocol."
	 * 
	 */
	public Integer getIfInUcastPkts(){
		return ifInUcastPkts;
	}

	private void setIfInUcastPkts(Integer ifInUcastPkts){
		this.ifInUcastPkts = ifInUcastPkts;
	}

	
	/**
	 * 
	 * "The number of non-unicast (i.e., subnetwork-
	 *             broadcast or subnetwork-multicast) packets
	 *             delivered to a higher-layer protocol."
	 * 
	 */
	public Integer getIfInNUcastPkts(){
		return ifInNUcastPkts;
	}

	private void setIfInNUcastPkts(Integer ifInNUcastPkts){
		this.ifInNUcastPkts = ifInNUcastPkts;
	}

	
	/**
	 * 
	 * "The number of inbound packets which were chosen
	 *             to be discarded even though no errors had been
	 *             detected to prevent their being deliverable to a
	 *             higher-layer protocol.  One possible reason for
	 *             discarding such a packet could be to free up
	 *             buffer space."
	 * 
	 */
	public Integer getIfInDiscards(){
		return ifInDiscards;
	}

	private void setIfInDiscards(Integer ifInDiscards){
		this.ifInDiscards = ifInDiscards;
	}

	
	/**
	 * 
	 * "The number of inbound packets that contained
	 *             errors preventing them from being deliverable to a
	 *             higher-layer protocol."
	 * 
	 */
	public Integer getIfInErrors(){
		return ifInErrors;
	}

	private void setIfInErrors(Integer ifInErrors){
		this.ifInErrors = ifInErrors;
	}

	
	/**
	 * 
	 * "The number of packets received via the interface
	 *             which were discarded because of an unknown or
	 *             unsupported protocol."
	 * 
	 */
	public Integer getIfInUnknownProtos(){
		return ifInUnknownProtos;
	}

	private void setIfInUnknownProtos(Integer ifInUnknownProtos){
		this.ifInUnknownProtos = ifInUnknownProtos;
	}

	
	/**
	 * 
	 * "The total number of octets transmitted out of the
	 *             interface, including framing characters."
	 * 
	 */
	public Integer getIfOutOctets(){
		return ifOutOctets;
	}

	private void setIfOutOctets(Integer ifOutOctets){
		this.ifOutOctets = ifOutOctets;
	}

	
	/**
	 * 
	 * "The total number of packets that higher-level
	 *             protocols requested be transmitted to a
	 *             subnetwork-unicast address, including those that
	 *             were discarded or not sent."
	 * 
	 */
	public Integer getIfOutUcastPkts(){
		return ifOutUcastPkts;
	}

	private void setIfOutUcastPkts(Integer ifOutUcastPkts){
		this.ifOutUcastPkts = ifOutUcastPkts;
	}

	
	/**
	 * 
	 * "The total number of packets that higher-level
	 *             protocols requested be transmitted to a non-
	 *             unicast (i.e., a subnetwork-broadcast or
	 *             subnetwork-multicast) address, including those
	 *             that were discarded or not sent."
	 * 
	 */
	public Integer getIfOutNUcastPkts(){
		return ifOutNUcastPkts;
	}

	private void setIfOutNUcastPkts(Integer ifOutNUcastPkts){
		this.ifOutNUcastPkts = ifOutNUcastPkts;
	}

	
	/**
	 * 
	 * "The number of outbound packets which were chosen
	 *             to be discarded even though no errors had been
	 *             detected to prevent their being transmitted.  One
	 *             possible reason for discarding such a packet could
	 *             be to free up buffer space."
	 * 
	 */
	public Integer getIfOutDiscards(){
		return ifOutDiscards;
	}

	private void setIfOutDiscards(Integer ifOutDiscards){
		this.ifOutDiscards = ifOutDiscards;
	}

	
	/**
	 * 
	 * "The number of outbound packets that could not be
	 *             transmitted because of errors."
	 * 
	 */
	public Integer getIfOutErrors(){
		return ifOutErrors;
	}

	private void setIfOutErrors(Integer ifOutErrors){
		this.ifOutErrors = ifOutErrors;
	}

	
	/**
	 * 
	 * "The length of the output packet queue (in
	 *             packets)."
	 * 
	 */
	public Integer getIfOutQLen(){
		return ifOutQLen;
	}

	private void setIfOutQLen(Integer ifOutQLen){
		this.ifOutQLen = ifOutQLen;
	}

	
	/**
	 * 
	 * "A reference to MIB definitions specific to the
	 *             particular media being used to realize the
	 *             interface.  For example, if the interface is
	 *             realized by an ethernet, then the value of this
	 *             object refers to a document defining objects
	 *             specific to ethernet.  If this information is not
	 *             present, its value should be set to the OBJECT
	 *             IDENTIFIER { 0 0 }, which is a syntatically valid
	 *             object identifier, and any conformant
	 *             implementation of ASN.1 and BER must be able to
	 *             generate and recognize this value."
	 * 
	 */
	public String getIfSpecific(){
		return ifSpecific;
	}

	private void setIfSpecific(String ifSpecific){
		this.ifSpecific = ifSpecific;
	}


	public enum IfType{
		other(1),
		regular1822(2),
		hdh1822(3),
		ddnX25(4),
		rfc877x25(5),
		ethernetCsmacd(6),
		iso88023Csmacd(7),
		iso88024TokenBus(8),
		iso88025TokenRing(9),
		iso88026Man(10),
		starLan(11),
		proteon10Mbit(12),
		proteon80Mbit(13),
		hyperchannel(14),
		fddi(15),
		lapb(16),
		sdlc(17),
		ds1(18),
		e1(19),
		basicISDN(20),
		primaryISDN(21),
		propPointToPointSerial(22),
		ppp(23),
		softwareLoopback(24),
		eon(25),
		ethernet3Mbit(26),
		nsip(27),
		slip(28),
		ultra(29),
		ds3(30),
		sip(31),
		frameRelay(32),
		rs232(33),
		para(34),
		arcnet(35),
		arcnetPlus(36),
		atm(37),
		miox25(38),
		sonet(39),
		x25ple(40),
		iso88022llc(41),
		localTalk(42),
		smdsDxi(43),
		frameRelayService(44),
		v35(45),
		hssi(46),
		hippi(47),
		modem(48),
		aal5(49),
		sonetPath(50),
		sonetVT(51),
		smdsIcip(52),
		propVirtual(53),
		propMultiplexor(54),
		ieee80212(55),
		fibreChannel(56),
		hippiInterface(57),
		frameRelayInterconnect(58),
		aflane8023(59),
		aflane8025(60),
		cctEmul(61),
		fastEther(62),
		isdn(63),
		v11(64),
		v36(65),
		g703at64k(66),
		g703at2mb(67),
		qllc(68),
		fastEtherFX(69),
		channel(70),
		ieee80211(71),
		ibm370parChan(72),
		escon(73),
		dlsw(74),
		isdns(75),
		isdnu(76),
		lapd(77),
		ipSwitch(78),
		rsrb(79),
		atmLogical(80),
		ds0(81),
		ds0Bundle(82),
		bsc(83),
		async(84),
		cnr(85),
		iso88025Dtr(86),
		eplrs(87),
		arap(88),
		propCnls(89),
		hostPad(90),
		termPad(91),
		frameRelayMPI(92),
		x213(93),
		adsl(94),
		radsl(95),
		sdsl(96),
		vdsl(97),
		iso88025CRFPInt(98),
		myrinet(99),
		voiceEM(100),
		voiceFXO(101),
		voiceFXS(102),
		voiceEncap(103),
		voiceOverIp(104),
		atmDxi(105),
		atmFuni(106),
		atmIma(107),
		pppMultilinkBundle(108),
		ipOverCdlc(109),
		ipOverClaw(110),
		stackToStack(111),
		virtualIpAddress(112),
		mpc(113),
		ipOverAtm(114),
		iso88025Fiber(115),
		tdlc(116),
		gigabitEthernet(117),
		hdlc(118),
		lapf(119),
		v37(120),
		x25mlp(121),
		x25huntGroup(122),
		trasnpHdlc(123),
		interleave(124),
		fast(125),
		ip(126),
		docsCableMaclayer(127),
		docsCableDownstream(128),
		docsCableUpstream(129),
		a12MppSwitch(130),
		tunnel(131),
		coffee(132),
		ces(133),
		atmSubInterface(134),
		l2vlan(135),
		l3ipvlan(136),
		l3ipxvlan(137),
		digitalPowerline(138),
		mediaMailOverIp(139),
		dtm(140),
		dcn(141),
		ipForward(142),
		msdsl(143),
		ieee1394(144),
		ifgsn(145),
		dvbRccMacLayer(146),
		dvbRccDownstream(147),
		dvbRccUpstream(148),
		atmVirtual(149),
		mplsTunnel(150),
		srp(151),
		voiceOverAtm(152),
		voiceOverFrameRelay(153),
		idsl(154),
		compositeLink(155),
		ss7SigLink(156),
		propWirelessP2P(157),
		frForward(158),
		rfc1483(159),
		usb(160),
		ieee8023adLag(161),
		bgppolicyaccounting(162),
		frf16MfrBundle(163),
		h323Gatekeeper(164),
		h323Proxy(165),
		mpls(166),
		mfSigLink(167),
		hdsl2(168),
		shdsl(169),
		ds1FDL(170),
		pos(171),
		dvbAsiIn(172),
		dvbAsiOut(173),
		plc(174),
		nfas(175),
		tr008(176),
		gr303RDT(177),
		gr303IDT(178),
		isup(179),
		propDocsWirelessMaclayer(180),
		propDocsWirelessDownstream(181),
		propDocsWirelessUpstream(182),
		hiperlan2(183),
		propBWAp2Mp(184),
		sonetOverheadChannel(185),
		digitalWrapperOverheadChannel(186),
		aal2(187),
		radioMAC(188),
		atmRadio(189),
		imt(190),
		mvl(191),
		reachDSL(192),
		frDlciEndPt(193),
		atmVciEndPt(194),
		opticalChannel(195),
		opticalTransport(196),
		propAtm(197),
		voiceOverCable(198),
		infiniband(199),
		teLink(200),
		q2931(201),
		virtualTg(202),
		sipTg(203),
		sipSig(204),
		docsCableUpstreamChannel(205),
		econet(206),
		pon155(207),
		pon622(208),
		bridge(209),
		linegroup(210),
		voiceEMFGD(211),
		voiceFGDEANA(212),
		voiceDID(213),
		mpegTransport(214),
		sixToFour(215),
		gtp(216),
		pdnEtherLoop1(217),
		pdnEtherLoop2(218),
		opticalChannelGroup(219),
		homepna(220),
		gfp(221),
		ciscoISLvlan(222),
		actelisMetaLOOP(223),
		fcipLink(224),
		rpr(225),
		qam(226),
		lmp(227),
		cblVectaStar(228),
		docsCableMCmtsDownstream(229),
		adsl2(230),
		macSecControlledIF(231),
		macSecUncontrolledIF(232),
		aviciOpticalEther(233),
		atmbond(234),
		voiceFGDOS(235),
		mocaVersion1(236),
		ieee80216WMAN(237),
		adsl2plus(238),
		dvbRcsMacLayer(239),
		dvbTdm(240),
		dvbRcsTdma(241),;

		private final int value;

		private IfType(int value){
			this.value = value;
		}

		public int value(){
			return value;
		}

		public static IfType fromValue(int value){
			for(IfType constant : values()){
				if(constant.value() == value){
					return constant;
				}
			}
			return null;
		}

	}

	public enum IfAdminStatus{
		up(1),
		down(2),
		testing(3),;

		private final int value;

		private IfAdminStatus(int value){
			this.value = value;
		}

		public int value(){
			return value;
		}

		public static IfAdminStatus fromValue(int value){
			for(IfAdminStatus constant : values()){
				if(constant.value() == value){
					return constant;
				}
			}
			return null;
		}

	}

	public enum IfOperStatus{
		up(1),
		down(2),
		testing(3),
		unknown(4),
		dormant(5),
		notPresent(6),
		lowerLayerDown(7),;

		private final int value;

		private IfOperStatus(int value){
			this.value = value;
		}

		public int value(){
			return value;
		}

		public static IfOperStatus fromValue(int value){
			for(IfOperStatus constant : values()){
				if(constant.value() == value){
					return constant;
				}
			}
			return null;
		}

	}

}
