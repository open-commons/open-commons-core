/*
 * Copyright 2011 Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
* 
*
* This file is generated under this project, "open-commons-core".
*
* Date  : 2014. 5. 27. 오전 11:32:56
*
* Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
* 
*/

package open.commons.core.net;

/**
 * <h1>Assigned Internet Protocol Numbers</h1><br>
 * 
 * <b>Registration Procedure(s)</b><br>
 * &nbsp;&nbsp;&nbsp;&nbsp;IESG Approval or Standards Action<br>
 * 
 * <b>Reference</b><br>
 * &nbsp;&nbsp;&nbsp;&nbsp;[<a href="http://www.iana.org/go/rfc5237">RFC5237</a>][
 * <a href="http://www.iana.org/go/rfc7045">RFC7045</a>]<br>
 * 
 * <b>Note</b><br>
 * &nbsp;&nbsp;&nbsp;&nbsp;In the Internet Protocol version 4 (IPv4) [<a href="http://www.iana.org/go/rfc791">RFC791</a>
 * ] there is a field<br>
 * called "Protocol" to identify the next level protocol. This is an 8<br>
 * bit field. In Internet Protocol version 6 (IPv6) [<a href="http://www.iana.org/go/rfc2460">RFC2460</a>], this field
 * <br>
 * is called the "Next Header" field.<br>
 * 
 * <b>Note</b><br>
 * &nbsp;&nbsp;&nbsp;&nbsp;Values that are also IPv6 Extension Header Types should be listed in the<br>
 * IPv6 Extension Header Types registry at [<a href="http://www.iana.org/assignments/ipv6-parameters">IANA registry
 * ipv6-parameters</a>].<br>
 * 
 * @since 2014. 5. 26.
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public class Protocols {

    /**
     * <b>Protocol</b>: IPv6 Hop-by-Hop Option<br>
     * <b>References</b>: [<a href="http://www.iana.org/go/rfc2460">RFC2460</a>]
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_HOPOPT = 0x0000; // 0

    /**
     * <b>Protocol</b>: Internet Control Message<br>
     * <b>References</b>: [<a href="http://www.iana.org/go/rfc792">RFC792</a>]
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_ICMP = 0x0001; // 1

    /**
     * <b>Protocol</b>: Internet Group Management<br>
     * <b>References</b>: [<a href="http://www.iana.org/go/rfc1112">RFC1112</a>]
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_IGMP = 0x0002; // 2

    /**
     * <b>Protocol</b>: Gateway-to-Gateway<br>
     * <b>References</b>: [<a href="http://www.iana.org/go/rfc823">RFC823</a>]
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_GGP = 0x0003; // 3

    /**
     * <b>Protocol</b>: IPv4 encapsulation<br>
     * <b>References</b>: [<a href="http://www.iana.org/go/rfc2003">RFC2003</a>]
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_IPv4 = 0x0004; // 4

    /**
     * <b>Protocol</b>: Stream<br>
     * <b>References</b>:
     * <ul>
     * <li>[<a href="http://www.iana.org/go/rfc1190">RFC1190</a>]<br>
     * <li>[<a href="http://www.iana.org/go/rfc1819">RFC1819</a>]<br>
     * </ul>
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_ST = 0x0005; // 5

    /**
     * <b>Protocol</b>: Transmission Control<br>
     * <b>References</b>: [<a href="http://www.iana.org/go/rfc793">RFC793</a>]
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_TCP = 0x0006; // 6

    /**
     * <b>Protocol</b>: CBT<br>
     * <b>References</b>: Tony Ballardie(mailto:A.Ballardie@cs.ucl.ac.uk / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_CBT = 0x0007; // 7

    /**
     * <b>Protocol</b>: Exterior Gateway Protocol<br>
     * <b>References</b>:
     * <ul>
     * <li>[<a href="http://www.iana.org/go/rfc888">RFC888</a>]<br>
     * <li>David Mills(mailto:Mills@huey.udel.edu / )<br>
     * </ul>
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_EGP = 0x0008; // 8

    /**
     * <b>Protocol</b>: any private interior gateway (used by Cisco for their IGRP)<br>
     * <b>References</b>: Internet Assigned Numbers Authority(mailto:iana@iana.org / Jun-95)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_IGP = 0x0009; // 9

    /**
     * <b>Protocol</b>: BBN RCC Monitoring<br>
     * <b>References</b>: Steve Chipman(mailto:Chipman@f.bbn.com / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_BBN_RCC_MON = 0x000A; // 10

    /**
     * <b>Protocol</b>: Network Voice Protocol<br>
     * <b>References</b>:
     * <ul>
     * <li>[<a href="http://www.iana.org/go/rfc741">RFC741</a>]<br>
     * <li>Steve Casner(mailto:casner@isi.edu / )<br>
     * </ul>
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_NVP_II = 0x000B; // 11

    /**
     * <b>Protocol</b>: PUP<br>
     * <b>References</b>:
     * <ul>
     * <li>Boggs, D., J. Shoch, E. Taft, and R. Metcalfe, "PUP: An Internetwork Architecture", XEROX Palo Alto Research
     * Center, CSL-79-10, July 1979; also in IEEE Transactions on Communication, Volume COM-28, Number 4, April 1980.
     * <br>
     * <li>XEROX<br>
     * </ul>
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_PUP = 0x000C; // 12

    /**
     * <b>Protocol</b>: ARGUS<br>
     * <b>References</b>: Robert W. Scheifler(mailto:RWS@xx.lcs.mit.edu / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_ARGUS = 0x000D; // 13

    /**
     * <b>Protocol</b>: EMCON<br>
     * <b>References</b>: &lt;mystery contact&gt;
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_EMCON = 0x000E; // 14

    /**
     * <b>Protocol</b>: Cross Net Debugger<br>
     * <b>References</b>:
     * <ul>
     * <li>Haverty, J., "XNET Formats for Internet Protocol Version 4", IEN 158, October 1980.<br>
     * <li>Jack Haverty(mailto:jhaverty@oracle.com / )<br>
     * </ul>
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_XNET = 0x000F; // 15

    /**
     * <b>Protocol</b>: Chaos<br>
     * <b>References</b>: J. Noel Chiappa(mailto:JNC@xx.lcs.mit.edu / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_CHAOS = 0x0010; // 16

    /**
     * <b>Protocol</b>: User Datagram<br>
     * <b>References</b>:
     * <ul>
     * <li>[<a href="http://www.iana.org/go/rfc768">RFC768</a>]<br>
     * <li>Jon Postel(mailto:postel@isi.edu / )<br>
     * </ul>
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_UDP = 0x0011; // 17

    /**
     * <b>Protocol</b>: Multiplexing<br>
     * <b>References</b>:
     * <ul>
     * <li>Cohen, D. and J. Postel, "Multiplexing Protocol", IEN 90, USC/Information Sciences Institute, May 1979.<br>
     * <li>Jon Postel(mailto:postel@isi.edu / )<br>
     * </ul>
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_MUX = 0x0012; // 18

    /**
     * <b>Protocol</b>: DCN Measurement Subsystems<br>
     * <b>References</b>: David Mills(mailto:Mills@huey.udel.edu / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_DCN_MEAS = 0x0013; // 19

    /**
     * <b>Protocol</b>: Host Monitoring<br>
     * <b>References</b>:
     * <ul>
     * <li>[<a href="http://www.iana.org/go/rfc869">RFC869</a>]<br>
     * <li>Bob Hinden(mailto:bob.hinden@gmail.com / 2013-02-17)<br>
     * </ul>
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_HMP = 0x0014; // 20

    /**
     * <b>Protocol</b>: Packet Radio Measurement<br>
     * <b>References</b>: Zaw-Sing Su(mailto:ZSu@tsca.istc.sri. / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_PRM = 0x0015; // 21

    /**
     * <b>Protocol</b>: XEROX NS IDP<br>
     * <b>References</b>:
     * <ul>
     * <li>"The Ethernet, A Local Area Network: Data Link Layer and Physical Layer Specification", AA-K759B-TK, Digital
     * Equipment Corporation, Maynard, MA. Also as: "The Ethernet - A Local Area Network", Version 1.0, Digital
     * Equipment Corporation, Intel Corporation, Xerox Corporation, September 1980. And: "The Ethernet, A Local Area
     * Network: Data Link Layer and Physical Layer Specifications", Digital, Intel and Xerox, November 1982. And: XEROX,
     * "The Ethernet, A Local Area Network: Data Link Layer and Physical Layer Specification", X3T51/80-50, Xerox
     * Corporation, Stamford, CT., October 1980.<br>
     * <li>XEROX<br>
     * </ul>
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_XNS_IDP = 0x0016; // 22

    /**
     * <b>Protocol</b>: Trunk-1<br>
     * <b>References</b>: Barry Boehm(mailto:boehm@arpa.mil / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_TRUNK_1 = 0x0017; // 23

    /**
     * <b>Protocol</b>: Trunk-2<br>
     * <b>References</b>: Barry Boehm(mailto:boehm@arpa.mil / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_TRUNK_2 = 0x0018; // 24

    /**
     * <b>Protocol</b>: Leaf-1<br>
     * <b>References</b>: Barry Boehm(mailto:boehm@arpa.mil / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_LEAF_1 = 0x0019; // 25

    /**
     * <b>Protocol</b>: Leaf-2<br>
     * <b>References</b>: Barry Boehm(mailto:boehm@arpa.mil / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_LEAF_2 = 0x001A; // 26

    /**
     * <b>Protocol</b>: Reliable Data Protocol<br>
     * <b>References</b>:
     * <ul>
     * <li>[<a href="http://www.iana.org/go/rfc908">RFC908</a>]<br>
     * <li>Bob Hinden(mailto:bob.hinden@gmail.com / 2013-02-17)<br>
     * </ul>
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_RDP = 0x001B; // 27

    /**
     * <b>Protocol</b>: Internet Reliable Transaction<br>
     * <b>References</b>:
     * <ul>
     * <li>[<a href="http://www.iana.org/go/rfc938">RFC938</a>]<br>
     * <li>Trudy Miller(mailto:Trudy@acc.com / )<br>
     * </ul>
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_IRTP = 0x001C; // 28

    /**
     * <b>Protocol</b>: ISO Transport Protocol Class 4<br>
     * <b>References</b>:
     * <ul>
     * <li>[<a href="http://www.iana.org/go/rfc905">RFC905</a>]<br>
     * <li>&lt;mystery contact&gt;<br>
     * </ul>
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_ISO_TP4 = 0x001D; // 29

    /**
     * <b>Protocol</b>: Bulk Data Transfer Protocol<br>
     * <b>References</b>:
     * <ul>
     * <li>[<a href="http://www.iana.org/go/rfc969">RFC969</a>]<br>
     * <li>David Clark(mailto:ddc@lcs.mit.edu / )<br>
     * </ul>
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_NETBLT = 0x001E; // 30

    /**
     * <b>Protocol</b>: MFE Network Services Protocol<br>
     * <b>References</b>:
     * <ul>
     * <li>Shuttleworth, B., "A Documentary of MFENet, a National Computer Network", UCRL-52317, Lawrence Livermore
     * Labs, Livermore, California, June 1977.<br>
     * <li>Barry Howard(mailto:Howard@nmfecc.llnl.gov / )<br>
     * </ul>
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_MFE_NSP = 0x001F; // 31

    /**
     * <b>Protocol</b>: MERIT Internodal Protocol<br>
     * <b>References</b>: Hans-Werner Braun(mailto:HWB@mcr.umich.edu / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_MERIT_INP = 0x0020; // 32

    /**
     * <b>Protocol</b>: Datagram Congestion Control Protocol<br>
     * <b>References</b>: [<a href="http://www.iana.org/go/rfc4340">RFC4340</a>]
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_DCCP = 0x0021; // 33

    /**
     * <b>Protocol</b>: Third Party Connect Protocol<br>
     * <b>References</b>: Stuart A. Friedberg(mailto:stuart@cs.wisc.edu / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_3PC = 0x0022; // 34

    /**
     * <b>Protocol</b>: Inter-Domain Policy Routing Protocol<br>
     * <b>References</b>: Martha Steenstrup(mailto:MSteenst@bbn.com / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_IDPR = 0x0023; // 35

    /**
     * <b>Protocol</b>: XTP<br>
     * <b>References</b>: Greg Chesson(mailto:Greg@sgi.com / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_XTP = 0x0024; // 36

    /**
     * <b>Protocol</b>: Datagram Delivery Protocol<br>
     * <b>References</b>: Wesley Craig(mailto:Wesley.Craig@terminator.cc.umich.edu / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_DDP = 0x0025; // 37

    /**
     * <b>Protocol</b>: IDPR Control Message Transport Proto<br>
     * <b>References</b>: Martha Steenstrup(mailto:MSteenst@bbn.com / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_IDPR_CMTP = 0x0026; // 38

    /**
     * <b>Protocol</b>: TP++ Transport Protocol<br>
     * <b>References</b>: Dirk Fromhein(mailto:df@watershed.com / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_TP = 0x0027; // 39

    /**
     * <b>Protocol</b>: IL Transport Protocol<br>
     * <b>References</b>: Dave Presotto(mailto:presotto@plan9.att.com / Jul-95)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_IL = 0x0028; // 40

    /**
     * <b>Protocol</b>: IPv6 encapsulation<br>
     * <b>References</b>: [<a href="http://www.iana.org/go/rfc2473">RFC2473</a>]
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_IPv6 = 0x0029; // 41

    /**
     * <b>Protocol</b>: Source Demand Routing Protocol<br>
     * <b>References</b>: Deborah Estrin(mailto:estrin@usc.edu / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_SDRP = 0x002A; // 42

    /**
     * <b>Protocol</b>: Routing Header for IPv6<br>
     * <b>References</b>: Steve Deering(mailto:deering@parc.xerox.com / Mar-95)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_IPv6_Route = 0x002B; // 43

    /**
     * <b>Protocol</b>: Fragment Header for IPv6<br>
     * <b>References</b>: Steve Deering(mailto:deering@parc.xerox.com / Mar-95)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_IPv6_Frag = 0x002C; // 44

    /**
     * <b>Protocol</b>: Inter-Domain Routing Protocol<br>
     * <b>References</b>: Sue Hares(mailto:skh@merit.edu / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_IDRP = 0x002D; // 45

    /**
     * <b>Protocol</b>: Reservation Protocol<br>
     * <b>References</b>:
     * <ul>
     * <li>[<a href="http://www.iana.org/go/rfc2205">RFC2205</a>]<br>
     * <li>[<a href="http://www.iana.org/go/rfc3209">RFC3209</a>]<br>
     * <li>Bob Braden(mailto:braden@isi.edu / Jul-97)<br>
     * </ul>
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_RSVP = 0x002E; // 46

    /**
     * <b>Protocol</b>: Generic Routing Encapsulation<br>
     * <b>References</b>:
     * <ul>
     * <li>[<a href="http://www.iana.org/go/rfc1701">RFC1701</a>]<br>
     * <li>Tony Li(mailto:tony.li@tony.li / 2012-10-17)<br>
     * </ul>
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_GRE = 0x002F; // 47

    /**
     * <b>Protocol</b>: Dynamic Source Routing Protocol<br>
     * <b>References</b>: [<a href="http://www.iana.org/go/rfc4728">RFC4728</a>]
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_DSR = 0x0030; // 48

    /**
     * <b>Protocol</b>: BNA<br>
     * <b>References</b>: Gary Salamon
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_BNA = 0x0031; // 49

    /**
     * <b>Protocol</b>: Encap Security Payload<br>
     * <b>References</b>: [<a href="http://www.iana.org/go/rfc4303">RFC4303</a>]
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_ESP = 0x0032; // 50

    /**
     * <b>Protocol</b>: Authentication Header<br>
     * <b>References</b>: [<a href="http://www.iana.org/go/rfc4302">RFC4302</a>]
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_AH = 0x0033; // 51

    /**
     * <b>Protocol</b>: Integrated Net Layer Security TUBA<br>
     * <b>References</b>: K. Robert Glenn(mailto:glenn@osi.ncsl.nist.gov / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_I_NLSP = 0x0034; // 52

    /**
     * <b>Protocol</b>: IP with Encryption<br>
     * <b>References</b>: John Ioannidis(mailto:ji@cs.columbia.edu / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_SWIPE = 0x0035; // 53

    /**
     * <b>Protocol</b>: NBMA Address Resolution Protocol<br>
     * <b>References</b>: [<a href="http://www.iana.org/go/rfc1735">RFC1735</a>]
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_NARP = 0x0036; // 54

    /**
     * <b>Protocol</b>: IP Mobility<br>
     * <b>References</b>: Charlie Perkins(mailto:perk@watson.ibm.com / Oct-94)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_MOBILE = 0x0037; // 55

    /**
     * <b>Protocol</b>: Transport Layer Security Protocol using Kryptonet key management<br>
     * <b>References</b>: Christer Oberg(mailto:chg@bull.se / Oct-94)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_TLSP = 0x0038; // 56

    /**
     * <b>Protocol</b>: SKIP<br>
     * <b>References</b>: Tom Markson(mailto:markson@osmosys.ingog.com / Sep-95)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_SKIP = 0x0039; // 57

    /**
     * <b>Protocol</b>: ICMP for IPv6<br>
     * <b>References</b>: [<a href="http://www.iana.org/go/rfc2460">RFC2460</a>]
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_IPv6_ICMP = 0x003A; // 58

    /**
     * <b>Protocol</b>: No Next Header for IPv6<br>
     * <b>References</b>: [<a href="http://www.iana.org/go/rfc2460">RFC2460</a>]
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_IPv6_NoNxt = 0x003B; // 59

    /**
     * <b>Protocol</b>: Destination Options for IPv6<br>
     * <b>References</b>: [<a href="http://www.iana.org/go/rfc2460">RFC2460</a>]
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_IPv6_Opts = 0x003C; // 60

    /**
     * <b>Protocol</b>: any host internal protocol<br>
     * <b>References</b>: Internet Assigned Numbers Authority(mailto:iana@iana.org / Jun-95)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_UNSIGNED_0 = 0x003D; // 61

    /**
     * <b>Protocol</b>: CFTP<br>
     * <b>References</b>:
     * <ul>
     * <li>Forsdick, H., "CFTP", Network Message, Bolt Beranek and Newman, January 1982.<br>
     * <li>Harry Forsdick(mailto:Forsdick@bbn.com / )<br>
     * </ul>
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_CFTP = 0x003E; // 62

    /**
     * <b>Protocol</b>: any local network<br>
     * <b>References</b>: Internet Assigned Numbers Authority(mailto:iana@iana.org / Jun-95)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_UNSIGNED_1 = 0x003F; // 63

    /**
     * <b>Protocol</b>: SATNET and Backroom EXPAK<br>
     * <b>References</b>: Steven Blumenthal(mailto:BLUMENTHAL@vax.bbn.com / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_SAT_EXPAK = 0x0040; // 64

    /**
     * <b>Protocol</b>: Kryptolan<br>
     * <b>References</b>: Paul Liu
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_KRYPTOLAN = 0x0041; // 65

    /**
     * <b>Protocol</b>: MIT Remote Virtual Disk Protocol<br>
     * <b>References</b>: Michael Greenwald(mailto:Greenwald@scrc-stony-brook.symbolics.com / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_RVD = 0x0042; // 66

    /**
     * <b>Protocol</b>: Internet Pluribus Packet Core<br>
     * <b>References</b>: Steven Blumenthal(mailto:BLUMENTHAL@vax.bbn.com / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_IPPC = 0x0043; // 67

    /**
     * <b>Protocol</b>: any distributed file system<br>
     * <b>References</b>: Internet Assigned Numbers Authority(mailto:iana@iana.org / Jun-95)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_UNSIGNED_2 = 0x0044; // 68

    /**
     * <b>Protocol</b>: SATNET Monitoring<br>
     * <b>References</b>: Steven Blumenthal(mailto:BLUMENTHAL@vax.bbn.com / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_SAT_MON = 0x0045; // 69

    /**
     * <b>Protocol</b>: VISA Protocol<br>
     * <b>References</b>: Gene Tsudik(mailto:tsudik@usc.edu / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_VISA = 0x0046; // 70

    /**
     * <b>Protocol</b>: Internet Packet Core Utility<br>
     * <b>References</b>: Steven Blumenthal(mailto:BLUMENTHAL@vax.bbn.com / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_IPCV = 0x0047; // 71

    /**
     * <b>Protocol</b>: Computer Protocol Network Executive<br>
     * <b>References</b>: David Mittnacht
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_CPNX = 0x0048; // 72

    /**
     * <b>Protocol</b>: Computer Protocol Heart Beat<br>
     * <b>References</b>: David Mittnacht
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_CPHB = 0x0049; // 73

    /**
     * <b>Protocol</b>: Wang Span Network<br>
     * <b>References</b>: Victor Dafoulas
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_WSN = 0x004A; // 74

    /**
     * <b>Protocol</b>: Packet Video Protocol<br>
     * <b>References</b>: Steve Casner(mailto:casner@isi.edu / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_PVP = 0x004B; // 75

    /**
     * <b>Protocol</b>: Backroom SATNET Monitoring<br>
     * <b>References</b>: Steven Blumenthal(mailto:BLUMENTHAL@vax.bbn.com / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_BR_SAT_MON = 0x004C; // 76

    /**
     * <b>Protocol</b>: SUN ND PROTOCOL-Temporary<br>
     * <b>References</b>: William Melohn(mailto:Melohn@sun.com / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_SUN_ND = 0x004D; // 77

    /**
     * <b>Protocol</b>: WIDEBAND Monitoring<br>
     * <b>References</b>: Steven Blumenthal(mailto:BLUMENTHAL@vax.bbn.com / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_WB_MON = 0x004E; // 78

    /**
     * <b>Protocol</b>: WIDEBAND EXPAK<br>
     * <b>References</b>: Steven Blumenthal(mailto:BLUMENTHAL@vax.bbn.com / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_WB_EXPAK = 0x004F; // 79

    /**
     * <b>Protocol</b>: ISO Internet Protocol<br>
     * <b>References</b>: Marshall T. Rose(mailto:mrose@dbc.mtview.ca.us / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_ISO_IP = 0x0050; // 80

    /**
     * <b>Protocol</b>: VMTP<br>
     * <b>References</b>: Dave Cheriton(mailto:cheriton@pescadero.stanford.edu / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_VMTP = 0x0051; // 81

    /**
     * <b>Protocol</b>: SECURE-VMTP<br>
     * <b>References</b>: Dave Cheriton(mailto:cheriton@pescadero.stanford.edu / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_SECURE_VMTP = 0x0052; // 82

    /**
     * <b>Protocol</b>: VINES<br>
     * <b>References</b>: Brian Horn
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_VINES = 0x0053; // 83

    /**
     * <b>Protocol</b>: Transaction Transport Protocol<br>
     * <b>References</b>: Jim Stevens(mailto:jasteven@rockwellcollins.com / 2011-01-26)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_TTP = 0x0054; // 84

    /**
     * <b>Protocol</b>: Internet Protocol Traffic Manager<br>
     * <b>References</b>: Jim Stevens(mailto:jasteven@rockwellcollins.com / 2011-01-26)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_IPTM = 0x0054; // 84

    /**
     * <b>Protocol</b>: NSFNET-IGP<br>
     * <b>References</b>: Hans-Werner Braun(mailto:HWB@mcr.umich.edu / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_NSFNET_IGP = 0x0055; // 85

    /**
     * <b>Protocol</b>: Dissimilar Gateway Protocol<br>
     * <b>References</b>:
     * <ul>
     * <li>M/A-COM Government Systems, "Dissimilar Gateway Protocol Specification, Draft Version", Contract no.
     * CS901145, November 16, 1987.<br>
     * <li>Mike Little(mailto:little@macom4.arpa / )<br>
     * </ul>
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_DGP = 0x0056; // 86

    /**
     * <b>Protocol</b>: TCF<br>
     * <b>References</b>: Guillermo A. Loyola(mailto:LOYOLA@ibm.com / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_TCF = 0x0057; // 87

    /**
     * <b>Protocol</b>: EIGRP<br>
     * <b>References</b>:
     * <ul>
     * <li>Cisco Systems, "Gateway Server Reference Manual", Manual Revision B, January 10, 1988.<br>
     * <li>Guenther Schreiner(mailto:snmp-admin@ira.uka.de / )<br>
     * </ul>
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_EIGRP = 0x0058; // 88

    /**
     * <b>Protocol</b>: OSPFIGP<br>
     * <b>References</b>:
     * <ul>
     * <li>[<a href="http://www.iana.org/go/rfc1583">RFC1583</a>]<br>
     * <li>[<a href="http://www.iana.org/go/rfc2328">RFC2328</a>]<br>
     * <li>[<a href="http://www.iana.org/go/rfc5340">RFC5340</a>]<br>
     * <li>John Moy(mailto:jmoy@proteon.com / )<br>
     * </ul>
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_OSPFIGP = 0x0059; // 89

    /**
     * <b>Protocol</b>: Sprite RPC Protocol<br>
     * <b>References</b>:
     * <ul>
     * <li>Welch, B., "The Sprite Remote Procedure Call System", Technical Report, UCB/Computer Science Dept., 86/302,
     * University of California at Berkeley, June 1986.<br>
     * <li>Bruce Willins<br>
     * </ul>
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_Sprite_RPC = 0x005A; // 90

    /**
     * <b>Protocol</b>: Locus Address Resolution Protocol<br>
     * <b>References</b>: Brian Horn
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_LARP = 0x005B; // 91

    /**
     * <b>Protocol</b>: Multicast Transport Protocol<br>
     * <b>References</b>: Susie Armstrong(mailto:Armstrong.wbst128@xerox.com / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_MTP = 0x005C; // 92

    /**
     * <b>Protocol</b>: AX.25 Frames<br>
     * <b>References</b>: Brian Kantor(mailto:brian@ucsd.edu / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_AX_25 = 0x005D; // 93

    /**
     * <b>Protocol</b>: IP-within-IP Encapsulation Protocol<br>
     * <b>References</b>: John Ioannidis(mailto:ji@cs.columbia.edu / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_IPIP = 0x005E; // 94

    /**
     * <b>Protocol</b>: Mobile Internetworking Control Pro.<br>
     * <b>References</b>: John Ioannidis(mailto:ji@cs.columbia.edu / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_MICP = 0x005F; // 95

    /**
     * <b>Protocol</b>: Semaphore Communications Sec. Pro.<br>
     * <b>References</b>: Howard Hart(mailto:hch@hybrid.com / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_SCC_SP = 0x0060; // 96

    /**
     * <b>Protocol</b>: Ethernet-within-IP Encapsulation<br>
     * <b>References</b>: [<a href="http://www.iana.org/go/rfc3378">RFC3378</a>]
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_ETHERIP = 0x0061; // 97

    /**
     * <b>Protocol</b>: Encapsulation Header<br>
     * <b>References</b>:
     * <ul>
     * <li>[<a href="http://www.iana.org/go/rfc1241">RFC1241</a>]<br>
     * <li>Robert Woodburn(mailto:woody@cseic.saic.com / )<br>
     * </ul>
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_ENCAP = 0x0062; // 98

    /**
     * <b>Protocol</b>: any private encryption scheme<br>
     * <b>References</b>: Internet Assigned Numbers Authority(mailto:iana@iana.org / Jun-95)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_UNSIGNED_3 = 0x0063; // 99

    /**
     * <b>Protocol</b>: GMTP<br>
     * <b>References</b>: RXB5
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_GMTP = 0x0064; // 100

    /**
     * <b>Protocol</b>: Ipsilon Flow Management Protocol<br>
     * <b>References</b>:
     * <ul>
     * <li>Bob Hinden(mailto:bob.hinden@gmail.com / 2013-02-17)<br>
     * <li>November 1995, 1997.<br>
     * </ul>
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_IFMP = 0x0065; // 101

    /**
     * <b>Protocol</b>: PNNI over IP<br>
     * <b>References</b>: Ross Callon(mailto:rcallon@baynetworks.com / Dec-95)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_PNNI = 0x0066; // 102

    /**
     * <b>Protocol</b>: Protocol Independent Multicast<br>
     * <b>References</b>:
     * <ul>
     * <li>[<a href="http://www.iana.org/go/rfc4601">RFC4601</a>]<br>
     * <li>Dino Farinacci(mailto:dino@cisco.com / Mar-96)<br>
     * </ul>
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_PIM = 0x0067; // 103

    /**
     * <b>Protocol</b>: ARIS<br>
     * <b>References</b>: Nancy Feldman(mailto:nkf@vnet.ibm.com / Jan-97)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_ARIS = 0x0068; // 104

    /**
     * <b>Protocol</b>: SCPS<br>
     * <b>References</b>: Robert Durst(mailto:durst@mitre.org / Mar-97)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_SCPS = 0x0069; // 105

    /**
     * <b>Protocol</b>: QNX<br>
     * <b>References</b>: Michael Hunter(mailto:mphunter@qnx.com / Jul-97)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_QNX = 0x006A; // 106

    /**
     * <b>Protocol</b>: Active Networks<br>
     * <b>References</b>: Bob Braden(mailto:braden@isi.edu / Jul-97)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_AN = 0x006B; // 107

    /**
     * <b>Protocol</b>: IP Payload Compression Protocol<br>
     * <b>References</b>: [<a href="http://www.iana.org/go/rfc2393">RFC2393</a>]
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_IPComp = 0x006C; // 108

    /**
     * <b>Protocol</b>: Sitara Networks Protocol<br>
     * <b>References</b>: Manickam R. Sridhar(mailto:msridhar@sitaranetworks.com / Sep-97)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_SNP = 0x006D; // 109

    /**
     * <b>Protocol</b>: Compaq Peer Protocol<br>
     * <b>References</b>: Victor Volpe(mailto:vvolpe@smtp.microcom.com / Oct-97)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_Compaq_Peer = 0x006E; // 110

    /**
     * <b>Protocol</b>: IPX in IP<br>
     * <b>References</b>: CJ Lee(mailto:cj_lee@novell.com / Oct-97)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_IPX_in_IP = 0x006F; // 111

    /**
     * <b>Protocol</b>: Virtual Router Redundancy Protocol<br>
     * <b>References</b>: [<a href="http://www.iana.org/go/rfc5798">RFC5798</a>]
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_VRRP = 0x0070; // 112

    /**
     * <b>Protocol</b>: PGM Reliable Transport Protocol<br>
     * <b>References</b>: Tony Speakman(mailto:speakman@cisco.com / Jan-98)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_PGM = 0x0071; // 113

    /**
     * <b>Protocol</b>: any 0-hop protocol<br>
     * <b>References</b>: Internet Assigned Numbers Authority(mailto:iana@iana.org / Jun-95)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_UNSIGNED_4 = 0x0072; // 114

    /**
     * <b>Protocol</b>: Layer Two Tunneling Protocol<br>
     * <b>References</b>:
     * <ul>
     * <li>[<a href="http://www.iana.org/go/rfc3931">RFC3931</a>]<br>
     * <li>Bernard Aboba(mailto:bernarda@microsoft.com / Apr-98)<br>
     * </ul>
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_L2TP = 0x0073; // 115

    /**
     * <b>Protocol</b>: D-II Data Exchange (DDX)<br>
     * <b>References</b>: John Worley(mailto:worley@milehigh.net / Jun-98)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_DDX = 0x0074; // 116

    /**
     * <b>Protocol</b>: Interactive Agent Transfer Protocol<br>
     * <b>References</b>: John Murphy(mailto:john.m.murphy@mci.com / Oct-98)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_IATP = 0x0075; // 117

    /**
     * <b>Protocol</b>: Schedule Transfer Protocol<br>
     * <b>References</b>: Jean-Michel Pittet(mailto:jmp@gandalf.engr.sgi.com / Nov-98)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_STP = 0x0076; // 118

    /**
     * <b>Protocol</b>: SpectraLink Radio Protocol<br>
     * <b>References</b>: Mark Hamilton(mailto:mah@spectralink.com / Nov-98)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_SRP = 0x0077; // 119

    /**
     * <b>Protocol</b>: UTI<br>
     * <b>References</b>: Peter Lothberg(mailto:roll@stupi.se / Mar-99)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_UTI = 0x0078; // 120

    /**
     * <b>Protocol</b>: Simple Message Protocol<br>
     * <b>References</b>: Leif Ekblad(mailto:leif@rdos.net / 2012-08-21)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_SMP = 0x0079; // 121

    /**
     * <b>Protocol</b>: Simple Multicast Protocol<br>
     * <b>References</b>:
     * <ul>
     * <li>Jon Crowcroft(mailto:jon@cs.ucl.ac.uk / Jun-99)<br>
     * <li>draft-perlman-simple-multicast<br>
     * </ul>
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_SM = 0x007A; // 122

    /**
     * <b>Protocol</b>: Performance Transparency Protocol<br>
     * <b>References</b>: Michael Welzl(mailto:michael@tk.uni-linz.ac.at / Aug-99)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_PTP = 0x007B; // 123

    /**
     * <b>Protocol</b>: <br>
     * <b>References</b>: Tony Przygienda(mailto:prz@siara.com / Aug-99)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_ISIS_over_IPv4 = 0x007C; // 124

    /**
     * <b>Protocol</b>: <br>
     * <b>References</b>: Criag Partridge(mailto:craig@bbn.com / Aug-99)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_FIRE = 0x007D; // 125

    /**
     * <b>Protocol</b>: Combat Radio Transport Protocol<br>
     * <b>References</b>: Robert Sautter(mailto:rsautter@acdnj.itt.com / Aug-99)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_CRTP = 0x007E; // 126

    /**
     * <b>Protocol</b>: Combat Radio User Datagram<br>
     * <b>References</b>: Robert Sautter(mailto:rsautter@acdnj.itt.com / Aug-99)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_CRUDP = 0x007F; // 127

    /**
     * <b>Protocol</b>: <br>
     * <b>References</b>: Kurt Waber(mailto:kurt.waber@swisscom.com / Aug-99)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_SSCOPMCE = 0x0080; // 128

    /**
     * <b>Protocol</b>: <br>
     * <b>References</b>: Hollbach
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_IPLT = 0x0081; // 129

    /**
     * <b>Protocol</b>: Secure Packet Shield<br>
     * <b>References</b>: Bill McIntosh(mailto:BMcIntosh@fortresstech.com / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_SPS = 0x0082; // 130

    /**
     * <b>Protocol</b>: Private IP Encapsulation within IP<br>
     * <b>References</b>: Bernhard Petri(mailto:bernhard.petri@siemens.com / 2012-07-09)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_PIPE = 0x0083; // 131

    /**
     * <b>Protocol</b>: Stream Control Transmission Protocol<br>
     * <b>References</b>: Randall R. Stewart(mailto:rrs@lakerest.net / Apr-00)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_SCTP = 0x0084; // 132

    /**
     * <b>Protocol</b>: Fibre Channel<br>
     * <b>References</b>:
     * <ul>
     * <li>Murali Rajagopal(mailto:murali@gadzoox.com / May-00)<br>
     * <li>[<a href="http://www.iana.org/go/rfc6172">RFC6172</a>]<br>
     * </ul>
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_FC = 0x0085; // 133

    /**
     * <b>Protocol</b>: <br>
     * <b>References</b>: [<a href="http://www.iana.org/go/rfc3175">RFC3175</a>]
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_RSVP_E2E_IGNORE = 0x0086; // 134

    /**
     * <b>Protocol</b>: <br>
     * <b>References</b>: [<a href="http://www.iana.org/go/rfc6275">RFC6275</a>]
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_Mobility_Header = 0x0087; // 135

    /**
     * <b>Protocol</b>: <br>
     * <b>References</b>: [<a href="http://www.iana.org/go/rfc3828">RFC3828</a>]
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_UDPLite = 0x0088; // 136

    /**
     * <b>Protocol</b>: <br>
     * <b>References</b>: [<a href="http://www.iana.org/go/rfc4023">RFC4023</a>]
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_MPLS_in_IP = 0x0089; // 137

    /**
     * <b>Protocol</b>: MANET Protocols<br>
     * <b>References</b>: [<a href="http://www.iana.org/go/rfc5498">RFC5498</a>]
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_manet = 0x008A; // 138

    /**
     * <b>Protocol</b>: Host Identity Protocol<br>
     * <b>References</b>: [<a href="http://www.iana.org/go/rfc5201">RFC5201</a>]
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_HIP = 0x008B; // 139

    /**
     * <b>Protocol</b>: Shim6 Protocol<br>
     * <b>References</b>: [<a href="http://www.iana.org/go/rfc5533">RFC5533</a>]
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_Shim6 = 0x008C; // 140

    /**
     * <b>Protocol</b>: Wrapped Encapsulating Security Payload<br>
     * <b>References</b>: [<a href="http://www.iana.org/go/rfc5840">RFC5840</a>]
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_WESP = 0x008D; // 141

    /**
     * <b>Protocol</b>: Robust Header Compression<br>
     * <b>References</b>: [<a href="http://www.iana.org/go/rfc5858">RFC5858</a>]
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_ROHC = 0x008E; // 142

    /**
     * <b>Protocol</b>: Use for experimentation and testing<br>
     * <b>References</b>: [<a href="http://www.iana.org/go/rfc3692">RFC3692</a>]
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_UNSIGNED_5 = 0x00FD; // 253

    /**
     * <b>Protocol</b>: Use for experimentation and testing<br>
     * <b>References</b>: [<a href="http://www.iana.org/go/rfc3692">RFC3692</a>]
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_UNSIGNED_6 = 0x00FE; // 254

    /**
     * <b>Protocol</b>: <br>
     * <b>References</b>: Internet Assigned Numbers Authority(mailto:iana@iana.org / Jun-95)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final int PROTOCOL_Reserved = 0x00FF; // 255

    /**
     * <b>Protocol</b>: IPv6 Hop-by-Hop Option<br>
     * <b>References</b>: [<a href="http://www.iana.org/go/rfc2460">RFC2460</a>]
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0000 = "HOPOPT: IPv6 Hop-by-Hop Option"; // 0

    /**
     * <b>Protocol</b>: Internet Control Message<br>
     * <b>References</b>: [<a href="http://www.iana.org/go/rfc792">RFC792</a>]
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0001 = "ICMP: Internet Control Message"; // 1

    /**
     * <b>Protocol</b>: Internet Group Management<br>
     * <b>References</b>: [<a href="http://www.iana.org/go/rfc1112">RFC1112</a>]
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0002 = "IGMP: Internet Group Management"; // 2

    /**
     * <b>Protocol</b>: Gateway-to-Gateway<br>
     * <b>References</b>: [<a href="http://www.iana.org/go/rfc823">RFC823</a>]
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0003 = "GGP: Gateway-to-Gateway"; // 3

    /**
     * <b>Protocol</b>: IPv4 encapsulation<br>
     * <b>References</b>: [<a href="http://www.iana.org/go/rfc2003">RFC2003</a>]
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0004 = "IPv4: IPv4 encapsulation"; // 4

    /**
     * <b>Protocol</b>: Stream<br>
     * <b>References</b>:
     * <ul>
     * <li>[<a href="http://www.iana.org/go/rfc1190">RFC1190</a>]<br>
     * <li>[<a href="http://www.iana.org/go/rfc1819">RFC1819</a>]<br>
     * </ul>
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0005 = "ST: Stream"; // 5

    /**
     * <b>Protocol</b>: Transmission Control<br>
     * <b>References</b>: [<a href="http://www.iana.org/go/rfc793">RFC793</a>]
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0006 = "TCP: Transmission Control"; // 6

    /**
     * <b>Protocol</b>: CBT<br>
     * <b>References</b>: Tony Ballardie(mailto:A.Ballardie@cs.ucl.ac.uk / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0007 = "CBT: CBT"; // 7

    /**
     * <b>Protocol</b>: Exterior Gateway Protocol<br>
     * <b>References</b>:
     * <ul>
     * <li>[<a href="http://www.iana.org/go/rfc888">RFC888</a>]<br>
     * <li>David Mills(mailto:Mills@huey.udel.edu / )<br>
     * </ul>
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0008 = "EGP: Exterior Gateway Protocol"; // 8

    /**
     * <b>Protocol</b>: any private interior gateway (used by Cisco for their IGRP)<br>
     * <b>References</b>: Internet Assigned Numbers Authority(mailto:iana@iana.org / Jun-95)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0009 = "IGP: any private interior gateway (used by Cisco for their IGRP)"; // 9

    /**
     * <b>Protocol</b>: BBN RCC Monitoring<br>
     * <b>References</b>: Steve Chipman(mailto:Chipman@f.bbn.com / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x000A = "BBN_RCC_MON: BBN RCC Monitoring"; // 10

    /**
     * <b>Protocol</b>: Network Voice Protocol<br>
     * <b>References</b>:
     * <ul>
     * <li>[<a href="http://www.iana.org/go/rfc741">RFC741</a>]<br>
     * <li>Steve Casner(mailto:casner@isi.edu / )<br>
     * </ul>
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x000B = "NVP_II: Network Voice Protocol"; // 11

    /**
     * <b>Protocol</b>: PUP<br>
     * <b>References</b>:
     * <ul>
     * <li>Boggs, D., J. Shoch, E. Taft, and R. Metcalfe, "PUP: An Internetwork Architecture", XEROX Palo Alto Research
     * Center, CSL-79-10, July 1979; also in IEEE Transactions on Communication, Volume COM-28, Number 4, April 1980.
     * <br>
     * <li>XEROX<br>
     * </ul>
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x000C = "PUP: PUP"; // 12

    /**
     * <b>Protocol</b>: ARGUS<br>
     * <b>References</b>: Robert W. Scheifler(mailto:RWS@xx.lcs.mit.edu / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x000D = "ARGUS: ARGUS"; // 13

    /**
     * <b>Protocol</b>: EMCON<br>
     * <b>References</b>: &lt;mystery contact&gt;
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x000E = "EMCON: EMCON"; // 14

    /**
     * <b>Protocol</b>: Cross Net Debugger<br>
     * <b>References</b>:
     * <ul>
     * <li>Haverty, J., "XNET Formats for Internet Protocol Version 4", IEN 158, October 1980.<br>
     * <li>Jack Haverty(mailto:jhaverty@oracle.com / )<br>
     * </ul>
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x000F = "XNET: Cross Net Debugger"; // 15

    /**
     * <b>Protocol</b>: Chaos<br>
     * <b>References</b>: J. Noel Chiappa(mailto:JNC@xx.lcs.mit.edu / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0010 = "CHAOS: Chaos"; // 16

    /**
     * <b>Protocol</b>: User Datagram<br>
     * <b>References</b>:
     * <ul>
     * <li>[<a href="http://www.iana.org/go/rfc768">RFC768</a>]<br>
     * <li>Jon Postel(mailto:postel@isi.edu / )<br>
     * </ul>
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0011 = "UDP: User Datagram"; // 17

    /**
     * <b>Protocol</b>: Multiplexing<br>
     * <b>References</b>:
     * <ul>
     * <li>Cohen, D. and J. Postel, "Multiplexing Protocol", IEN 90, USC/Information Sciences Institute, May 1979.<br>
     * <li>Jon Postel(mailto:postel@isi.edu / )<br>
     * </ul>
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0012 = "MUX: Multiplexing"; // 18

    /**
     * <b>Protocol</b>: DCN Measurement Subsystems<br>
     * <b>References</b>: David Mills(mailto:Mills@huey.udel.edu / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0013 = "DCN_MEAS: DCN Measurement Subsystems"; // 19

    /**
     * <b>Protocol</b>: Host Monitoring<br>
     * <b>References</b>:
     * <ul>
     * <li>[<a href="http://www.iana.org/go/rfc869">RFC869</a>]<br>
     * <li>Bob Hinden(mailto:bob.hinden@gmail.com / 2013-02-17)<br>
     * </ul>
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0014 = "HMP: Host Monitoring"; // 20

    /**
     * <b>Protocol</b>: Packet Radio Measurement<br>
     * <b>References</b>: Zaw-Sing Su(mailto:ZSu@tsca.istc.sri. / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0015 = "PRM: Packet Radio Measurement"; // 21

    /**
     * <b>Protocol</b>: XEROX NS IDP<br>
     * <b>References</b>:
     * <ul>
     * <li>"The Ethernet, A Local Area Network: Data Link Layer and Physical Layer Specification", AA-K759B-TK, Digital
     * Equipment Corporation, Maynard, MA. Also as: "The Ethernet - A Local Area Network", Version 1.0, Digital
     * Equipment Corporation, Intel Corporation, Xerox Corporation, September 1980. And: "The Ethernet, A Local Area
     * Network: Data Link Layer and Physical Layer Specifications", Digital, Intel and Xerox, November 1982. And: XEROX,
     * "The Ethernet, A Local Area Network: Data Link Layer and Physical Layer Specification", X3T51/80-50, Xerox
     * Corporation, Stamford, CT., October 1980.<br>
     * <li>XEROX<br>
     * </ul>
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0016 = "XNS_IDP: XEROX NS IDP"; // 22

    /**
     * <b>Protocol</b>: Trunk-1<br>
     * <b>References</b>: Barry Boehm(mailto:boehm@arpa.mil / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0017 = "TRUNK_1: Trunk-1"; // 23

    /**
     * <b>Protocol</b>: Trunk-2<br>
     * <b>References</b>: Barry Boehm(mailto:boehm@arpa.mil / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0018 = "TRUNK_2: Trunk-2"; // 24

    /**
     * <b>Protocol</b>: Leaf-1<br>
     * <b>References</b>: Barry Boehm(mailto:boehm@arpa.mil / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0019 = "LEAF_1: Leaf-1"; // 25

    /**
     * <b>Protocol</b>: Leaf-2<br>
     * <b>References</b>: Barry Boehm(mailto:boehm@arpa.mil / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x001A = "LEAF_2: Leaf-2"; // 26

    /**
     * <b>Protocol</b>: Reliable Data Protocol<br>
     * <b>References</b>:
     * <ul>
     * <li>[<a href="http://www.iana.org/go/rfc908">RFC908</a>]<br>
     * <li>Bob Hinden(mailto:bob.hinden@gmail.com / 2013-02-17)<br>
     * </ul>
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x001B = "RDP: Reliable Data Protocol"; // 27

    /**
     * <b>Protocol</b>: Internet Reliable Transaction<br>
     * <b>References</b>:
     * <ul>
     * <li>[<a href="http://www.iana.org/go/rfc938">RFC938</a>]<br>
     * <li>Trudy Miller(mailto:Trudy@acc.com / )<br>
     * </ul>
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x001C = "IRTP: Internet Reliable Transaction"; // 28

    /**
     * <b>Protocol</b>: ISO Transport Protocol Class 4<br>
     * <b>References</b>:
     * <ul>
     * <li>[<a href="http://www.iana.org/go/rfc905">RFC905</a>]<br>
     * <li>&lt;mystery contact&gt;<br>
     * </ul>
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x001D = "ISO_TP4: ISO Transport Protocol Class 4"; // 29

    /**
     * <b>Protocol</b>: Bulk Data Transfer Protocol<br>
     * <b>References</b>:
     * <ul>
     * <li>[<a href="http://www.iana.org/go/rfc969">RFC969</a>]<br>
     * <li>David Clark(mailto:ddc@lcs.mit.edu / )<br>
     * </ul>
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x001E = "NETBLT: Bulk Data Transfer Protocol"; // 30

    /**
     * <b>Protocol</b>: MFE Network Services Protocol<br>
     * <b>References</b>:
     * <ul>
     * <li>Shuttleworth, B., "A Documentary of MFENet, a National Computer Network", UCRL-52317, Lawrence Livermore
     * Labs, Livermore, California, June 1977.<br>
     * <li>Barry Howard(mailto:Howard@nmfecc.llnl.gov / )<br>
     * </ul>
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x001F = "MFE_NSP: MFE Network Services Protocol"; // 31

    /**
     * <b>Protocol</b>: MERIT Internodal Protocol<br>
     * <b>References</b>: Hans-Werner Braun(mailto:HWB@mcr.umich.edu / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0020 = "MERIT_INP: MERIT Internodal Protocol"; // 32

    /**
     * <b>Protocol</b>: Datagram Congestion Control Protocol<br>
     * <b>References</b>: [<a href="http://www.iana.org/go/rfc4340">RFC4340</a>]
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0021 = "DCCP: Datagram Congestion Control Protocol"; // 33

    /**
     * <b>Protocol</b>: Third Party Connect Protocol<br>
     * <b>References</b>: Stuart A. Friedberg(mailto:stuart@cs.wisc.edu / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0022 = "3PC: Third Party Connect Protocol"; // 34

    /**
     * <b>Protocol</b>: Inter-Domain Policy Routing Protocol<br>
     * <b>References</b>: Martha Steenstrup(mailto:MSteenst@bbn.com / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0023 = "IDPR: Inter-Domain Policy Routing Protocol"; // 35

    /**
     * <b>Protocol</b>: XTP<br>
     * <b>References</b>: Greg Chesson(mailto:Greg@sgi.com / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0024 = "XTP: XTP"; // 36

    /**
     * <b>Protocol</b>: Datagram Delivery Protocol<br>
     * <b>References</b>: Wesley Craig(mailto:Wesley.Craig@terminator.cc.umich.edu / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0025 = "DDP: Datagram Delivery Protocol"; // 37

    /**
     * <b>Protocol</b>: IDPR Control Message Transport Proto<br>
     * <b>References</b>: Martha Steenstrup(mailto:MSteenst@bbn.com / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0026 = "IDPR_CMTP: IDPR Control Message Transport Proto"; // 38

    /**
     * <b>Protocol</b>: TP++ Transport Protocol<br>
     * <b>References</b>: Dirk Fromhein(mailto:df@watershed.com / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0027 = "TP++: TP++ Transport Protocol"; // 39

    /**
     * <b>Protocol</b>: IL Transport Protocol<br>
     * <b>References</b>: Dave Presotto(mailto:presotto@plan9.att.com / Jul-95)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0028 = "IL: IL Transport Protocol"; // 40

    /**
     * <b>Protocol</b>: IPv6 encapsulation<br>
     * <b>References</b>: [<a href="http://www.iana.org/go/rfc2473">RFC2473</a>]
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0029 = "IPv6: IPv6 encapsulation"; // 41

    /**
     * <b>Protocol</b>: Source Demand Routing Protocol<br>
     * <b>References</b>: Deborah Estrin(mailto:estrin@usc.edu / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x002A = "SDRP: Source Demand Routing Protocol"; // 42

    /**
     * <b>Protocol</b>: Routing Header for IPv6<br>
     * <b>References</b>: Steve Deering(mailto:deering@parc.xerox.com / Mar-95)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x002B = "IPv6_Route: Routing Header for IPv6"; // 43

    /**
     * <b>Protocol</b>: Fragment Header for IPv6<br>
     * <b>References</b>: Steve Deering(mailto:deering@parc.xerox.com / Mar-95)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x002C = "IPv6_Frag: Fragment Header for IPv6"; // 44

    /**
     * <b>Protocol</b>: Inter-Domain Routing Protocol<br>
     * <b>References</b>: Sue Hares(mailto:skh@merit.edu / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x002D = "IDRP: Inter-Domain Routing Protocol"; // 45

    /**
     * <b>Protocol</b>: Reservation Protocol<br>
     * <b>References</b>:
     * <ul>
     * <li>[<a href="http://www.iana.org/go/rfc2205">RFC2205</a>]<br>
     * <li>[<a href="http://www.iana.org/go/rfc3209">RFC3209</a>]<br>
     * <li>Bob Braden(mailto:braden@isi.edu / Jul-97)<br>
     * </ul>
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x002E = "RSVP: Reservation Protocol"; // 46

    /**
     * <b>Protocol</b>: Generic Routing Encapsulation<br>
     * <b>References</b>:
     * <ul>
     * <li>[<a href="http://www.iana.org/go/rfc1701">RFC1701</a>]<br>
     * <li>Tony Li(mailto:tony.li@tony.li / 2012-10-17)<br>
     * </ul>
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x002F = "GRE: Generic Routing Encapsulation"; // 47

    /**
     * <b>Protocol</b>: Dynamic Source Routing Protocol<br>
     * <b>References</b>: [<a href="http://www.iana.org/go/rfc4728">RFC4728</a>]
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0030 = "DSR: Dynamic Source Routing Protocol"; // 48

    /**
     * <b>Protocol</b>: BNA<br>
     * <b>References</b>: Gary Salamon
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0031 = "BNA: BNA"; // 49

    /**
     * <b>Protocol</b>: Encap Security Payload<br>
     * <b>References</b>: [<a href="http://www.iana.org/go/rfc4303">RFC4303</a>]
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0032 = "ESP: Encap Security Payload"; // 50

    /**
     * <b>Protocol</b>: Authentication Header<br>
     * <b>References</b>: [<a href="http://www.iana.org/go/rfc4302">RFC4302</a>]
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0033 = "AH: Authentication Header"; // 51

    /**
     * <b>Protocol</b>: Integrated Net Layer Security TUBA<br>
     * <b>References</b>: K. Robert Glenn(mailto:glenn@osi.ncsl.nist.gov / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0034 = "I_NLSP: Integrated Net Layer Security  TUBA"; // 52

    /**
     * <b>Protocol</b>: IP with Encryption<br>
     * <b>References</b>: John Ioannidis(mailto:ji@cs.columbia.edu / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0035 = "SWIPE: IP with Encryption"; // 53

    /**
     * <b>Protocol</b>: NBMA Address Resolution Protocol<br>
     * <b>References</b>: [<a href="http://www.iana.org/go/rfc1735">RFC1735</a>]
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0036 = "NARP: NBMA Address Resolution Protocol"; // 54

    /**
     * <b>Protocol</b>: IP Mobility<br>
     * <b>References</b>: Charlie Perkins(mailto:perk@watson.ibm.com / Oct-94)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0037 = "MOBILE: IP Mobility"; // 55

    /**
     * <b>Protocol</b>: Transport Layer Security Protocol using Kryptonet key management<br>
     * <b>References</b>: Christer Oberg(mailto:chg@bull.se / Oct-94)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0038 = "TLSP: Transport Layer Security Protocol using Kryptonet key management"; // 56

    /**
     * <b>Protocol</b>: SKIP<br>
     * <b>References</b>: Tom Markson(mailto:markson@osmosys.ingog.com / Sep-95)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0039 = "SKIP: SKIP"; // 57

    /**
     * <b>Protocol</b>: ICMP for IPv6<br>
     * <b>References</b>: [<a href="http://www.iana.org/go/rfc2460">RFC2460</a>]
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x003A = "IPv6_ICMP: ICMP for IPv6"; // 58

    /**
     * <b>Protocol</b>: No Next Header for IPv6<br>
     * <b>References</b>: [<a href="http://www.iana.org/go/rfc2460">RFC2460</a>]
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x003B = "IPv6_NoNxt: No Next Header for IPv6"; // 59

    /**
     * <b>Protocol</b>: Destination Options for IPv6<br>
     * <b>References</b>: [<a href="http://www.iana.org/go/rfc2460">RFC2460</a>]
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x003C = "IPv6_Opts: Destination Options for IPv6"; // 60

    /**
     * <b>Protocol</b>: any host internal protocol<br>
     * <b>References</b>: Internet Assigned Numbers Authority(mailto:iana@iana.org / Jun-95)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x003D = "UNSIGNED_0: any host internal protocol"; // 61

    /**
     * <b>Protocol</b>: CFTP<br>
     * <b>References</b>:
     * <ul>
     * <li>Forsdick, H., "CFTP", Network Message, Bolt Beranek and Newman, January 1982.<br>
     * <li>Harry Forsdick(mailto:Forsdick@bbn.com / )<br>
     * </ul>
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x003E = "CFTP: CFTP"; // 62

    /**
     * <b>Protocol</b>: any local network<br>
     * <b>References</b>: Internet Assigned Numbers Authority(mailto:iana@iana.org / Jun-95)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x003F = "UNSIGNED_1: any local network"; // 63

    /**
     * <b>Protocol</b>: SATNET and Backroom EXPAK<br>
     * <b>References</b>: Steven Blumenthal(mailto:BLUMENTHAL@vax.bbn.com / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0040 = "SAT_EXPAK: SATNET and Backroom EXPAK"; // 64

    /**
     * <b>Protocol</b>: Kryptolan<br>
     * <b>References</b>: Paul Liu
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0041 = "KRYPTOLAN: Kryptolan"; // 65

    /**
     * <b>Protocol</b>: MIT Remote Virtual Disk Protocol<br>
     * <b>References</b>: Michael Greenwald(mailto:Greenwald@scrc-stony-brook.symbolics.com / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0042 = "RVD: MIT Remote Virtual Disk Protocol"; // 66

    /**
     * <b>Protocol</b>: Internet Pluribus Packet Core<br>
     * <b>References</b>: Steven Blumenthal(mailto:BLUMENTHAL@vax.bbn.com / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0043 = "IPPC: Internet Pluribus Packet Core"; // 67

    /**
     * <b>Protocol</b>: any distributed file system<br>
     * <b>References</b>: Internet Assigned Numbers Authority(mailto:iana@iana.org / Jun-95)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0044 = "UNSIGNED_2: any distributed file system"; // 68

    /**
     * <b>Protocol</b>: SATNET Monitoring<br>
     * <b>References</b>: Steven Blumenthal(mailto:BLUMENTHAL@vax.bbn.com / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0045 = "SAT_MON: SATNET Monitoring"; // 69

    /**
     * <b>Protocol</b>: VISA Protocol<br>
     * <b>References</b>: Gene Tsudik(mailto:tsudik@usc.edu / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0046 = "VISA: VISA Protocol"; // 70

    /**
     * <b>Protocol</b>: Internet Packet Core Utility<br>
     * <b>References</b>: Steven Blumenthal(mailto:BLUMENTHAL@vax.bbn.com / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0047 = "IPCV: Internet Packet Core Utility"; // 71

    /**
     * <b>Protocol</b>: Computer Protocol Network Executive<br>
     * <b>References</b>: David Mittnacht
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0048 = "CPNX: Computer Protocol Network Executive"; // 72

    /**
     * <b>Protocol</b>: Computer Protocol Heart Beat<br>
     * <b>References</b>: David Mittnacht
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0049 = "CPHB: Computer Protocol Heart Beat"; // 73

    /**
     * <b>Protocol</b>: Wang Span Network<br>
     * <b>References</b>: Victor Dafoulas
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x004A = "WSN: Wang Span Network"; // 74

    /**
     * <b>Protocol</b>: Packet Video Protocol<br>
     * <b>References</b>: Steve Casner(mailto:casner@isi.edu / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x004B = "PVP: Packet Video Protocol"; // 75

    /**
     * <b>Protocol</b>: Backroom SATNET Monitoring<br>
     * <b>References</b>: Steven Blumenthal(mailto:BLUMENTHAL@vax.bbn.com / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x004C = "BR_SAT_MON: Backroom SATNET Monitoring"; // 76

    /**
     * <b>Protocol</b>: SUN ND PROTOCOL-Temporary<br>
     * <b>References</b>: William Melohn(mailto:Melohn@sun.com / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x004D = "SUN_ND: SUN ND PROTOCOL-Temporary"; // 77

    /**
     * <b>Protocol</b>: WIDEBAND Monitoring<br>
     * <b>References</b>: Steven Blumenthal(mailto:BLUMENTHAL@vax.bbn.com / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x004E = "WB_MON: WIDEBAND Monitoring"; // 78

    /**
     * <b>Protocol</b>: WIDEBAND EXPAK<br>
     * <b>References</b>: Steven Blumenthal(mailto:BLUMENTHAL@vax.bbn.com / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x004F = "WB_EXPAK: WIDEBAND EXPAK"; // 79

    /**
     * <b>Protocol</b>: ISO Internet Protocol<br>
     * <b>References</b>: Marshall T. Rose(mailto:mrose@dbc.mtview.ca.us / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0050 = "ISO_IP: ISO Internet Protocol"; // 80

    /**
     * <b>Protocol</b>: VMTP<br>
     * <b>References</b>: Dave Cheriton(mailto:cheriton@pescadero.stanford.edu / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0051 = "VMTP: VMTP"; // 81

    /**
     * <b>Protocol</b>: SECURE-VMTP<br>
     * <b>References</b>: Dave Cheriton(mailto:cheriton@pescadero.stanford.edu / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0052 = "SECURE_VMTP: SECURE-VMTP"; // 82

    /**
     * <b>Protocol</b>: VINES<br>
     * <b>References</b>: Brian Horn
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0053 = "VINES: VINES"; // 83

    /**
     * <b>Protocol</b>:
     * <ul>
     * <li>Transaction Transport Protocol<br>
     * <li>Internet Protocol Traffic Manager<br>
     * </ul>
     * <b>References</b>: Jim Stevens(mailto:jasteven@rockwellcollins.com / 2011-01-26)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0054 = "TTP: Transaction Transport Protocol, IPTM: Internet Protocol Traffic Manager"; // 84

    /**
     * <b>Protocol</b>: NSFNET-IGP<br>
     * <b>References</b>: Hans-Werner Braun(mailto:HWB@mcr.umich.edu / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0055 = "NSFNET_IGP: NSFNET-IGP"; // 85

    /**
     * <b>Protocol</b>: Dissimilar Gateway Protocol<br>
     * <b>References</b>:
     * <ul>
     * <li>M/A-COM Government Systems, "Dissimilar Gateway Protocol Specification, Draft Version", Contract no.
     * CS901145, November 16, 1987.<br>
     * <li>Mike Little(mailto:little@macom4.arpa / )<br>
     * </ul>
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0056 = "DGP: Dissimilar Gateway Protocol"; // 86

    /**
     * <b>Protocol</b>: TCF<br>
     * <b>References</b>: Guillermo A. Loyola(mailto:LOYOLA@ibm.com / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0057 = "TCF: TCF"; // 87

    /**
     * <b>Protocol</b>: EIGRP<br>
     * <b>References</b>:
     * <ul>
     * <li>Cisco Systems, "Gateway Server Reference Manual", Manual Revision B, January 10, 1988.<br>
     * <li>Guenther Schreiner(mailto:snmp-admin@ira.uka.de / )<br>
     * </ul>
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0058 = "EIGRP: EIGRP"; // 88

    /**
     * <b>Protocol</b>: OSPFIGP<br>
     * <b>References</b>:
     * <ul>
     * <li>[<a href="http://www.iana.org/go/rfc1583">RFC1583</a>]<br>
     * <li>[<a href="http://www.iana.org/go/rfc2328">RFC2328</a>]<br>
     * <li>[<a href="http://www.iana.org/go/rfc5340">RFC5340</a>]<br>
     * <li>John Moy(mailto:jmoy@proteon.com / )<br>
     * </ul>
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0059 = "OSPFIGP: OSPFIGP"; // 89

    /**
     * <b>Protocol</b>: Sprite RPC Protocol<br>
     * <b>References</b>:
     * <ul>
     * <li>Welch, B., "The Sprite Remote Procedure Call System", Technical Report, UCB/Computer Science Dept., 86/302,
     * University of California at Berkeley, June 1986.<br>
     * <li>Bruce Willins<br>
     * </ul>
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x005A = "Sprite_RPC: Sprite RPC Protocol"; // 90

    /**
     * <b>Protocol</b>: Locus Address Resolution Protocol<br>
     * <b>References</b>: Brian Horn
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x005B = "LARP: Locus Address Resolution Protocol"; // 91

    /**
     * <b>Protocol</b>: Multicast Transport Protocol<br>
     * <b>References</b>: Susie Armstrong(mailto:Armstrong.wbst128@xerox.com / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x005C = "MTP: Multicast Transport Protocol"; // 92

    /**
     * <b>Protocol</b>: AX.25 Frames<br>
     * <b>References</b>: Brian Kantor(mailto:brian@ucsd.edu / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x005D = "AX_25: AX.25 Frames"; // 93

    /**
     * <b>Protocol</b>: IP-within-IP Encapsulation Protocol<br>
     * <b>References</b>: John Ioannidis(mailto:ji@cs.columbia.edu / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x005E = "IPIP: IP-within-IP Encapsulation Protocol"; // 94

    /**
     * <b>Protocol</b>: Mobile Internetworking Control Pro.<br>
     * <b>References</b>: John Ioannidis(mailto:ji@cs.columbia.edu / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x005F = "MICP: Mobile Internetworking Control Pro."; // 95

    /**
     * <b>Protocol</b>: Semaphore Communications Sec. Pro.<br>
     * <b>References</b>: Howard Hart(mailto:hch@hybrid.com / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0060 = "SCC_SP: Semaphore Communications Sec. Pro."; // 96

    /**
     * <b>Protocol</b>: Ethernet-within-IP Encapsulation<br>
     * <b>References</b>: [<a href="http://www.iana.org/go/rfc3378">RFC3378</a>]
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0061 = "ETHERIP: Ethernet-within-IP Encapsulation"; // 97

    /**
     * <b>Protocol</b>: Encapsulation Header<br>
     * <b>References</b>:
     * <ul>
     * <li>[<a href="http://www.iana.org/go/rfc1241">RFC1241</a>]<br>
     * <li>Robert Woodburn(mailto:woody@cseic.saic.com / )<br>
     * </ul>
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0062 = "ENCAP: Encapsulation Header"; // 98

    /**
     * <b>Protocol</b>: any private encryption scheme<br>
     * <b>References</b>: Internet Assigned Numbers Authority(mailto:iana@iana.org / Jun-95)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0063 = "UNSIGNED_3: any private encryption scheme"; // 99

    /**
     * <b>Protocol</b>: GMTP<br>
     * <b>References</b>: RXB5
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0064 = "GMTP: GMTP"; // 100

    /**
     * <b>Protocol</b>: Ipsilon Flow Management Protocol<br>
     * <b>References</b>:
     * <ul>
     * <li>Bob Hinden(mailto:bob.hinden@gmail.com / 2013-02-17)<br>
     * <li>November 1995, 1997.<br>
     * </ul>
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0065 = "IFMP: Ipsilon Flow Management Protocol"; // 101

    /**
     * <b>Protocol</b>: PNNI over IP<br>
     * <b>References</b>: Ross Callon(mailto:rcallon@baynetworks.com / Dec-95)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0066 = "PNNI: PNNI over IP"; // 102

    /**
     * <b>Protocol</b>: Protocol Independent Multicast<br>
     * <b>References</b>:
     * <ul>
     * <li>[<a href="http://www.iana.org/go/rfc4601">RFC4601</a>]<br>
     * <li>Dino Farinacci(mailto:dino@cisco.com / Mar-96)<br>
     * </ul>
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0067 = "PIM: Protocol Independent Multicast"; // 103

    /**
     * <b>Protocol</b>: ARIS<br>
     * <b>References</b>: Nancy Feldman(mailto:nkf@vnet.ibm.com / Jan-97)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0068 = "ARIS: ARIS"; // 104

    /**
     * <b>Protocol</b>: SCPS<br>
     * <b>References</b>: Robert Durst(mailto:durst@mitre.org / Mar-97)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0069 = "SCPS: SCPS"; // 105

    /**
     * <b>Protocol</b>: QNX<br>
     * <b>References</b>: Michael Hunter(mailto:mphunter@qnx.com / Jul-97)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x006A = "QNX: QNX"; // 106

    /**
     * <b>Protocol</b>: Active Networks<br>
     * <b>References</b>: Bob Braden(mailto:braden@isi.edu / Jul-97)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x006B = "AN: Active Networks"; // 107

    /**
     * <b>Protocol</b>: IP Payload Compression Protocol<br>
     * <b>References</b>: [<a href="http://www.iana.org/go/rfc2393">RFC2393</a>]
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x006C = "IPComp: IP Payload Compression Protocol"; // 108

    /**
     * <b>Protocol</b>: Sitara Networks Protocol<br>
     * <b>References</b>: Manickam R. Sridhar(mailto:msridhar@sitaranetworks.com / Sep-97)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x006D = "SNP: Sitara Networks Protocol"; // 109

    /**
     * <b>Protocol</b>: Compaq Peer Protocol<br>
     * <b>References</b>: Victor Volpe(mailto:vvolpe@smtp.microcom.com / Oct-97)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x006E = "Compaq_Peer: Compaq Peer Protocol"; // 110

    /**
     * <b>Protocol</b>: IPX in IP<br>
     * <b>References</b>: CJ Lee(mailto:cj_lee@novell.com / Oct-97)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x006F = "IPX_in_IP: IPX in IP"; // 111

    /**
     * <b>Protocol</b>: Virtual Router Redundancy Protocol<br>
     * <b>References</b>: [<a href="http://www.iana.org/go/rfc5798">RFC5798</a>]
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0070 = "VRRP: Virtual Router Redundancy Protocol"; // 112

    /**
     * <b>Protocol</b>: PGM Reliable Transport Protocol<br>
     * <b>References</b>: Tony Speakman(mailto:speakman@cisco.com / Jan-98)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0071 = "PGM: PGM Reliable Transport Protocol"; // 113

    /**
     * <b>Protocol</b>: any 0-hop protocol<br>
     * <b>References</b>: Internet Assigned Numbers Authority(mailto:iana@iana.org / Jun-95)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0072 = "UNSIGNED_4: any 0-hop protocol"; // 114

    /**
     * <b>Protocol</b>: Layer Two Tunneling Protocol<br>
     * <b>References</b>:
     * <ul>
     * <li>[<a href="http://www.iana.org/go/rfc3931">RFC3931</a>]<br>
     * <li>Bernard Aboba(mailto:bernarda@microsoft.com / Apr-98)<br>
     * </ul>
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0073 = "L2TP: Layer Two Tunneling Protocol"; // 115

    /**
     * <b>Protocol</b>: D-II Data Exchange (DDX)<br>
     * <b>References</b>: John Worley(mailto:worley@milehigh.net / Jun-98)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0074 = "DDX: D-II Data Exchange (DDX)"; // 116

    /**
     * <b>Protocol</b>: Interactive Agent Transfer Protocol<br>
     * <b>References</b>: John Murphy(mailto:john.m.murphy@mci.com / Oct-98)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0075 = "IATP: Interactive Agent Transfer Protocol"; // 117

    /**
     * <b>Protocol</b>: Schedule Transfer Protocol<br>
     * <b>References</b>: Jean-Michel Pittet(mailto:jmp@gandalf.engr.sgi.com / Nov-98)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0076 = "STP: Schedule Transfer Protocol"; // 118

    /**
     * <b>Protocol</b>: SpectraLink Radio Protocol<br>
     * <b>References</b>: Mark Hamilton(mailto:mah@spectralink.com / Nov-98)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0077 = "SRP: SpectraLink Radio Protocol"; // 119

    /**
     * <b>Protocol</b>: UTI<br>
     * <b>References</b>: Peter Lothberg(mailto:roll@stupi.se / Mar-99)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0078 = "UTI: UTI"; // 120

    /**
     * <b>Protocol</b>: Simple Message Protocol<br>
     * <b>References</b>: Leif Ekblad(mailto:leif@rdos.net / 2012-08-21)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0079 = "SMP: Simple Message Protocol"; // 121

    /**
     * <b>Protocol</b>: Simple Multicast Protocol<br>
     * <b>References</b>:
     * <ul>
     * <li>Jon Crowcroft(mailto:jon@cs.ucl.ac.uk / Jun-99)<br>
     * <li>draft-perlman-simple-multicast<br>
     * </ul>
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x007A = "SM: Simple Multicast Protocol"; // 122

    /**
     * <b>Protocol</b>: Performance Transparency Protocol<br>
     * <b>References</b>: Michael Welzl(mailto:michael@tk.uni-linz.ac.at / Aug-99)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x007B = "PTP: Performance Transparency Protocol"; // 123

    /**
     * <b>Protocol</b>: <br>
     * <b>References</b>: Tony Przygienda(mailto:prz@siara.com / Aug-99)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x007C = "ISIS_over_IPv4"; // 124

    /**
     * <b>Protocol</b>: <br>
     * <b>References</b>: Criag Partridge(mailto:craig@bbn.com / Aug-99)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x007D = "FIRE"; // 125

    /**
     * <b>Protocol</b>: Combat Radio Transport Protocol<br>
     * <b>References</b>: Robert Sautter(mailto:rsautter@acdnj.itt.com / Aug-99)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x007E = "CRTP: Combat Radio Transport Protocol"; // 126

    /**
     * <b>Protocol</b>: Combat Radio User Datagram<br>
     * <b>References</b>: Robert Sautter(mailto:rsautter@acdnj.itt.com / Aug-99)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x007F = "CRUDP: Combat Radio User Datagram"; // 127

    /**
     * <b>Protocol</b>: <br>
     * <b>References</b>: Kurt Waber(mailto:kurt.waber@swisscom.com / Aug-99)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0080 = "SSCOPMCE"; // 128

    /**
     * <b>Protocol</b>: <br>
     * <b>References</b>: Hollbach
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0081 = "IPLT"; // 129

    /**
     * <b>Protocol</b>: Secure Packet Shield<br>
     * <b>References</b>: Bill McIntosh(mailto:BMcIntosh@fortresstech.com / )
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0082 = "SPS: Secure Packet Shield"; // 130

    /**
     * <b>Protocol</b>: Private IP Encapsulation within IP<br>
     * <b>References</b>: Bernhard Petri(mailto:bernhard.petri@siemens.com / 2012-07-09)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0083 = "PIPE: Private IP Encapsulation within IP"; // 131

    /**
     * <b>Protocol</b>: Stream Control Transmission Protocol<br>
     * <b>References</b>: Randall R. Stewart(mailto:rrs@lakerest.net / Apr-00)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0084 = "SCTP: Stream Control Transmission Protocol"; // 132

    /**
     * <b>Protocol</b>: Fibre Channel<br>
     * <b>References</b>:
     * <ul>
     * <li>Murali Rajagopal(mailto:murali@gadzoox.com / May-00)<br>
     * <li>[<a href="http://www.iana.org/go/rfc6172">RFC6172</a>]<br>
     * </ul>
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0085 = "FC: Fibre Channel"; // 133

    /**
     * <b>Protocol</b>: <br>
     * <b>References</b>: [<a href="http://www.iana.org/go/rfc3175">RFC3175</a>]
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0086 = "RSVP_E2E_IGNORE"; // 134

    /**
     * <b>Protocol</b>: <br>
     * <b>References</b>: [<a href="http://www.iana.org/go/rfc6275">RFC6275</a>]
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0087 = "Mobility_Header"; // 135

    /**
     * <b>Protocol</b>: <br>
     * <b>References</b>: [<a href="http://www.iana.org/go/rfc3828">RFC3828</a>]
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0088 = "UDPLite"; // 136

    /**
     * <b>Protocol</b>: <br>
     * <b>References</b>: [<a href="http://www.iana.org/go/rfc4023">RFC4023</a>]
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x0089 = "MPLS_in_IP"; // 137

    /**
     * <b>Protocol</b>: MANET Protocols<br>
     * <b>References</b>: [<a href="http://www.iana.org/go/rfc5498">RFC5498</a>]
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x008A = "manet: MANET Protocols"; // 138

    /**
     * <b>Protocol</b>: Host Identity Protocol<br>
     * <b>References</b>: [<a href="http://www.iana.org/go/rfc5201">RFC5201</a>]
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x008B = "HIP: Host Identity Protocol"; // 139

    /**
     * <b>Protocol</b>: Shim6 Protocol<br>
     * <b>References</b>: [<a href="http://www.iana.org/go/rfc5533">RFC5533</a>]
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x008C = "Shim6: Shim6 Protocol"; // 140

    /**
     * <b>Protocol</b>: Wrapped Encapsulating Security Payload<br>
     * <b>References</b>: [<a href="http://www.iana.org/go/rfc5840">RFC5840</a>]
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x008D = "WESP: Wrapped Encapsulating Security Payload"; // 141

    /**
     * <b>Protocol</b>: Robust Header Compression<br>
     * <b>References</b>: [<a href="http://www.iana.org/go/rfc5858">RFC5858</a>]
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x008E = "ROHC: Robust Header Compression"; // 142

    /**
     * <b>Protocol</b>: Use for experimentation and testing<br>
     * <b>References</b>: [<a href="http://www.iana.org/go/rfc3692">RFC3692</a>]
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x00FD = "UNSIGNED_5: Use for experimentation and testing"; // 253

    /**
     * <b>Protocol</b>: Use for experimentation and testing<br>
     * <b>References</b>: [<a href="http://www.iana.org/go/rfc3692">RFC3692</a>]
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x00FE = "UNSIGNED_6: Use for experimentation and testing"; // 254

    /**
     * <b>Protocol</b>: <br>
     * <b>References</b>: Internet Assigned Numbers Authority(mailto:iana@iana.org / Jun-95)
     * 
     * @see <a href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers, IANA
     *      </a>
     */
    public static final String PROTOCOL_0x00FF = "Reserved"; // 255

}
