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
* Date  : 2014. 5. 30. 오전 10:56:26
*
* Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
* 
*/

package open.commons.net;

/**
 * 
 * @since 2014. 5. 30.
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public class EtherType {

    /**
     * <b>Protocol</b>: Internet Protocol version 4 (IPv4)<br>
     * <b>References</b>:
     * <a href="http://en.wikipedia.org/wiki/QNX#Transparent_Distributed_Processing">http://en.wikipedia.org/wiki/QNX#
     * Transparent_Distributed_Processing</a><br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final String _0x0800 = "IPv4"; // 2048

    /**
     * <b>Protocol</b>: Address Resolution Protocol (ARP)<br>
     * <b>References</b>:
     * <a href="http://en.wikipedia.org/wiki/Internet_Protocol_Version_6">http://en.wikipedia.org/wiki/
     * Internet_Protocol_Version_6</a><br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final String _0x0806 = "ARP"; // 2054

    /**
     * <b>Protocol</b>: Wake-on-LAN[3]<br>
     * <b>References</b>:
     * <a href="http://en.wikipedia.org/wiki/Wake-on-LAN">http://en.wikipedia.org/wiki/Wake-on-LAN</a><br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final String _0x0842 = "WAKE_ON_LAN"; // 2114

    /**
     * <b>Protocol</b>: IETF TRILL Protocol<br>
     * <b>References</b>:
     * <a href="http://en.wikipedia.org/wiki/TRILL_(computing)">http://en.wikipedia.org/wiki/TRILL_(computing)</a><br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final String _0x22F3 = "TRILL"; // 8947

    /**
     * <b>Protocol</b>: DECnet Phase IV<br>
     * <b>References</b>: <a href="http://en.wikipedia.org/wiki/DECnet">http://en.wikipedia.org/wiki/DECnet</a><br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final String _0x6003 = "DECNET_IV"; // 24579

    /**
     * <b>Protocol</b>: Reverse Address Resolution Protocol<br>
     * <b>References</b>:
     * <a href="http://en.wikipedia.org/wiki/Reverse_Address_Resolution_Protocol">http://en.wikipedia.org/wiki/
     * Reverse_Address_Resolution_Protocol</a><br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final String _0x8035 = "REV_ARP"; // 32821

    /**
     * <b>Protocol</b>: AppleTalk (Ethertalk)<br>
     * <b>References</b>: <a href="http://en.wikipedia.org/wiki/AppleTalk">http://en.wikipedia.org/wiki/AppleTalk</a>
     * <br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final String _0x809B = "APPLE_TALK"; // 32923

    /**
     * <b>Protocol</b>: AppleTalk Address Resolution Protocol (AARP)<br>
     * <b>References</b>: <a href="http://en.wikipedia.org/wiki/AppleTalk">http://en.wikipedia.org/wiki/AppleTalk</a>
     * <br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final String _0x80F3 = "APPLE_TALK_ARP"; // 33011

    /**
     * <b>Protocol</b>: VLAN-tagged frame (IEEE 802.1Q) &#38; Shortest Path Bridging IEEE 802.1aq[4]<br>
     * <b>References</b>:
     * <ul>
     * <li><a href="http://en.wikipedia.org/wiki/IEEE_802.1Q">http://en.wikipedia.org/wiki/IEEE_802.1Q</a>
     * <li><a href="http://en.wikipedia.org/wiki/IEEE_802.1aq">http://en.wikipedia.org/wiki/IEEE_802.1aq</a>
     * </ul>
     * <br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final String _0x8100 = "VLAN_FRAME"; // 33024

    /**
     * <b>Protocol</b>: IPX<br>
     * <b>References</b>: <a href="http://en.wikipedia.org/wiki/IPX">http://en.wikipedia.org/wiki/IPX</a><br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final String _0x8137 = "IPX_8137"; // 33079

    /**
     * <b>Protocol</b>: IPX<br>
     * <b>References</b>: <a href="http://en.wikipedia.org/wiki/IPX">http://en.wikipedia.org/wiki/IPX</a><br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final String _0x8138 = "IPX_8138"; // 33080

    /**
     * <b>Protocol</b>: QNX Qnet<br>
     * <b>References</b>:
     * <a href="http://en.wikipedia.org/wiki/QNX#Transparent_Distributed_Processing">http://en.wikipedia.org/wiki/QNX#
     * Transparent_Distributed_Processing</a><br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final String _0x8204 = "QNX"; // 33284

    /**
     * <b>Protocol</b>: Internet Protocol Version 6 (IPv6)<br>
     * <b>References</b>:
     * <a href="http://en.wikipedia.org/wiki/Internet_Protocol_Version_6">http://en.wikipedia.org/wiki/
     * Internet_Protocol_Version_6</a><br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final String _0x86DD = "IPv6"; // 34525

    /**
     * <b>Protocol</b>: Ethernet flow control<br>
     * <b>References</b>:
     * <a href="http://en.wikipedia.org/wiki/Ethernet_flow_control">http://en.wikipedia.org/wiki/Ethernet_flow_control
     * </a><br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final String _0x8808 = "ETH_FLOW"; // 34824

    /**
     * <b>Protocol</b>: Slow Protocols (IEEE 802.3)<br>
     * <b>References</b>: <a href="http://en.wikipedia.org/wiki/IEEE_802.3">http://en.wikipedia.org/wiki/IEEE_802.3</a>
     * <br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final String _0x8809 = "SLOW_PROTOCOLS"; // 34825

    /**
     * <b>Protocol</b>: CobraNet<br>
     * <b>References</b>: <a href="http://en.wikipedia.org/wiki/CobraNet">http://en.wikipedia.org/wiki/CobraNet</a><br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final String _0x8819 = "COBRANET"; // 34841

    /**
     * <b>Protocol</b>: MPLS unicast<br>
     * <b>References</b>:
     * <a href="http://en.wikipedia.org/wiki/Multiprotocol_Label_Switching">http://en.wikipedia.org/wiki/
     * Multiprotocol_Label_Switching</a><br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final String _0x8847 = "MPLS_UNICAST"; // 34887

    /**
     * <b>Protocol</b>: MPLS multicast<br>
     * <b>References</b>:
     * <a href="http://en.wikipedia.org/wiki/Multiprotocol_Label_Switching">http://en.wikipedia.org/wiki/
     * Multiprotocol_Label_Switching</a><br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final String _0x8848 = "MPLS_MULTICAST"; // 34888

    /**
     * <b>Protocol</b>: PPPoE Discovery Stage<br>
     * <b>References</b>:
     * <a href="http://en.wikipedia.org/wiki/Point-to-Point_Protocol_over_Ethernet">http://en.wikipedia.org/wiki/Point-
     * to-Point_Protocol_over_Ethernet</a><br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final String _0x8863 = "PPPoE_DISCOVERY"; // 34915

    /**
     * <b>Protocol</b>: PPPoE Session Stage<br>
     * <b>References</b>:
     * <a href="http://en.wikipedia.org/wiki/Point-to-Point_Protocol_over_Ethernet">http://en.wikipedia.org/wiki/Point-
     * to-Point_Protocol_over_Ethernet</a><br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final String _0x8864 = "PPPoE_SESSION"; // 34916

    /**
     * <b>Protocol</b>: Jumbo Frames[2]<br>
     * <b>References</b>:
     * <a href="http://en.wikipedia.org/wiki/Jumbo_frame">http://en.wikipedia.org/wiki/Jumbo_frame</a><br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final String _0x8870 = "JUMBO_FRAMES"; // 34928

    /**
     * <b>Protocol</b>: HomePlug 1.0 MME<br>
     * <b>References</b>: <a href="http://en.wikipedia.org/wiki/HomePlug">http://en.wikipedia.org/wiki/HomePlug</a><br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final String _0x887B = "HOMEPLUG_10"; // 34939

    /**
     * <b>Protocol</b>: EAP over LAN (IEEE 802.1X)<br>
     * <b>References</b>:
     * <a href="http://en.wikipedia.org/wiki/IEEE_802.1X">http://en.wikipedia.org/wiki/IEEE_802.1X</a><br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final String _0x888E = "EAP_OVER_LAN"; // 34958

    /**
     * <b>Protocol</b>: PROFINET Protocol<br>
     * <b>References</b>: <a href="http://en.wikipedia.org/wiki/PROFINET">http://en.wikipedia.org/wiki/PROFINET</a><br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final String _0x8892 = "PROFINET"; // 34962

    /**
     * <b>Protocol</b>: HyperSCSI (SCSI over Ethernet)<br>
     * <b>References</b>: <a href="http://en.wikipedia.org/wiki/HyperSCSI">http://en.wikipedia.org/wiki/HyperSCSI</a>
     * <br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final String _0x889A = "HYPERSCSI"; // 34970

    /**
     * <b>Protocol</b>: ATA over Ethernet<br>
     * <b>References</b>:
     * <a href="http://en.wikipedia.org/wiki/ATA_over_Ethernet">http://en.wikipedia.org/wiki/ATA_over_Ethernet</a><br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final String _0x88A2 = "ATA_OVER_ETH"; // 34978

    /**
     * <b>Protocol</b>: EtherCAT Protocol<br>
     * <b>References</b>: <a href="http://en.wikipedia.org/wiki/EtherCAT">http://en.wikipedia.org/wiki/EtherCAT</a><br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final String _0x88A4 = "ETHERCAT"; // 34980

    /**
     * <b>Protocol</b>: Provider Bridging (IEEE 802.1ad) &#38; Shortest Path Bridging IEEE 802.1aq[5]<br>
     * <b>References</b>:
     * <a href="http://en.wikipedia.org/wiki/IEEE_802.1ad">http://en.wikipedia.org/wiki/IEEE_802.1ad</a><br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final String _0x88A8 = "BRIDGING"; // 34984

    /**
     * <b>Protocol</b>: Ethernet Powerlink[citation needed]<br>
     * <b>References</b>:
     * <a href="http://en.wikipedia.org/wiki/Ethernet_Powerlink">http://en.wikipedia.org/wiki/Ethernet_Powerlink</a><br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final String _0x88AB = "POWERLINK"; // 34987

    /**
     * <b>Protocol</b>: Link Layer Discovery Protocol (LLDP)<br>
     * <b>References</b>:
     * <a href="http://en.wikipedia.org/wiki/Link_Layer_Discovery_Protocol">http://en.wikipedia.org/wiki/
     * Link_Layer_Discovery_Protocol</a><br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final String _0x88CC = "LLDP"; // 35020

    /**
     * <b>Protocol</b>: SERCOS III<br>
     * <b>References</b>: <a href="http://en.wikipedia.org/wiki/SERCOS_III">http://en.wikipedia.org/wiki/SERCOS_III</a>
     * <br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final String _0x88CD = "SERCOS"; // 35021

    /**
     * <b>Protocol</b>: HomePlug AV MME[citation needed]<br>
     * <b>References</b>: <a href="http://en.wikipedia.org/wiki/HomePlug">http://en.wikipedia.org/wiki/HomePlug</a><br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final String _0x88E1 = "HOMEPLUG_AV"; // 35041

    /**
     * <b>Protocol</b>: Media Redundancy Protocol (IEC62439-2)<br>
     * <b>References</b>: <a href="http://en.wikipedia.org/wiki/Media_Redundancy_Protocol">http://en.wikipedia.org/wiki/
     * Media_Redundancy_Protocol</a><br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final String _0x88E3 = "MRP"; // 35043

    /**
     * <b>Protocol</b>: MAC security (IEEE 802.1AE)<br>
     * <b>References</b>:
     * <a href="http://en.wikipedia.org/wiki/IEEE_802.1AE">http://en.wikipedia.org/wiki/IEEE_802.1AE</a><br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final String _0x88E5 = "MAC_SEC"; // 35045

    /**
     * <b>Protocol</b>: Precision Time Protocol (IEEE 1588)<br>
     * <b>References</b>: <a href="http://en.wikipedia.org/wiki/Precision_Time_Protocol">http://en.wikipedia.org/wiki/
     * Precision_Time_Protocol</a><br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final String _0x88F7 = "PTP"; // 35063

    /**
     * <b>Protocol</b>: IEEE 802.1ag Connectivity Fault Management (CFM) Protocol / ITU-T Recommendation Y.1731 (OAM)
     * <br>
     * <b>References</b>:
     * <ul>
     * <li><a href="http://en.wikipedia.org/wiki/IEEE_802.1ag">http://en.wikipedia.org/wiki/IEEE_802.1ag</a>
     * <li><a href="http://en.wikipedia.org/wiki/Connectivity_Fault_Management">http://en.wikipedia.org/wiki/
     * Connectivity_Fault_Management</a>
     * <li><a href="http://en.wikipedia.org/wiki/ITU-T_Recommendation_Y.1731">http://en.wikipedia.org/wiki/ITU-
     * T_Recommendation_Y.1731</a>
     * <li><a href="http://en.wikipedia.org/wiki/OA%26M">http://en.wikipedia.org/wiki/OA%26M</a>
     * </ul>
     * <br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final String _0x8902 = "CFM"; // 35074

    /**
     * <b>Protocol</b>: Fibre Channel over Ethernet (FCoE)<br>
     * <b>References</b>:
     * <a href="http://en.wikipedia.org/wiki/Fibre_Channel_over_Ethernet">http://en.wikipedia.org/wiki/
     * Fibre_Channel_over_Ethernet</a><br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final String _0x8906 = "FCoE"; // 35078

    /**
     * <b>Protocol</b>: FCoE Initialization Protocol<br>
     * <b>References</b>:
     * <a href="http://en.wikipedia.org/wiki/Fibre_Channel_over_Ethernet">http://en.wikipedia.org/wiki/
     * Fibre_Channel_over_Ethernet</a><br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final String _0x8914 = "FCoE_INIT"; // 35092

    /**
     * <b>Protocol</b>: RDMA over Converged Ethernet (RoCE)<br>
     * <b>References</b>:
     * <a href="http://en.wikipedia.org/wiki/RDMA_over_Converged_Ethernet">http://en.wikipedia.org/wiki/
     * RDMA_over_Converged_Ethernet</a><br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final String _0x8915 = "RoCE"; // 35093

    /**
     * <b>Protocol</b>: High-availability Seamless Redundancy (HSR)<br>
     * <b>References</b>:
     * <a href="http://en.wikipedia.org/wiki/High-availability_Seamless_Redundancy">http://en.wikipedia.org/wiki/High-
     * availability_Seamless_Redundancy</a><br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final String _0x892F = "HSR"; // 35119

    /**
     * <b>Protocol</b>: Ethernet Configuration Testing Protocol[6]<br>
     * <b>References</b>:
     * <a href="http://en.wikipedia.org/wiki/Ethernet_Configuration_Testing_Protocol">http://en.wikipedia.org/wiki/
     * Ethernet_Configuration_Testing_Protocol</a><br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final String _0x9000 = "CONF_TEST"; // 36864

    /**
     * <b>Protocol</b>: Q-in-Q<br>
     * <b>References</b>: <a href="http://en.wikipedia.org/wiki/Q-in-Q">http://en.wikipedia.org/wiki/Q-in-Q</a><br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final String _0x9100 = "Q_IN_Q"; // 37120

    /**
     * <b>Protocol</b>: Veritas Low Latency Transport (LLT)[7] for Veritas Cluster Server<br>
     * <b>References</b>: <a href="http://wiki.wireshark.org/LLT">http://wiki.wireshark.org/LLT</a><br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final String _0xCAFE = "LLT"; // 51966

    /**
     * <b>Protocol</b>: Internet Protocol version 4 (IPv4)<br>
     * <b>References</b>:
     * <a href="http://en.wikipedia.org/wiki/QNX#Transparent_Distributed_Processing">http://en.wikipedia.org/wiki/QNX#
     * Transparent_Distributed_Processing</a><br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final int IPv4 = 0x0800; // 2048

    /**
     * <b>Protocol</b>: Address Resolution Protocol (ARP)<br>
     * <b>References</b>:
     * <a href="http://en.wikipedia.org/wiki/Internet_Protocol_Version_6">http://en.wikipedia.org/wiki/
     * Internet_Protocol_Version_6</a><br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final int ARP = 0x0806; // 2054

    /**
     * <b>Protocol</b>: Wake-on-LAN[3]<br>
     * <b>References</b>:
     * <a href="http://en.wikipedia.org/wiki/Wake-on-LAN">http://en.wikipedia.org/wiki/Wake-on-LAN</a><br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final int WAKE_ON_LAN = 0x0842; // 2114

    /**
     * <b>Protocol</b>: IETF TRILL Protocol<br>
     * <b>References</b>:
     * <a href="http://en.wikipedia.org/wiki/TRILL_(computing)">http://en.wikipedia.org/wiki/TRILL_(computing)</a><br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final int TRILL = 0x22F3; // 8947

    /**
     * <b>Protocol</b>: DECnet Phase IV<br>
     * <b>References</b>: <a href="http://en.wikipedia.org/wiki/DECnet">http://en.wikipedia.org/wiki/DECnet</a><br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final int DECNET_IV = 0x6003; // 24579

    /**
     * <b>Protocol</b>: Reverse Address Resolution Protocol<br>
     * <b>References</b>:
     * <a href="http://en.wikipedia.org/wiki/Reverse_Address_Resolution_Protocol">http://en.wikipedia.org/wiki/
     * Reverse_Address_Resolution_Protocol</a><br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final int REV_ARP = 0x8035; // 32821

    /**
     * <b>Protocol</b>: AppleTalk (Ethertalk)<br>
     * <b>References</b>: <a href="http://en.wikipedia.org/wiki/AppleTalk">http://en.wikipedia.org/wiki/AppleTalk</a>
     * <br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final int APPLE_TALK = 0x809B; // 32923

    /**
     * <b>Protocol</b>: AppleTalk Address Resolution Protocol (AARP)<br>
     * <b>References</b>: <a href="http://en.wikipedia.org/wiki/AppleTalk">http://en.wikipedia.org/wiki/AppleTalk</a>
     * <br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final int APPLE_TALK_ARP = 0x80F3; // 33011

    /**
     * <b>Protocol</b>: VLAN-tagged frame (IEEE 802.1Q) &#38; Shortest Path Bridging IEEE 802.1aq[4]<br>
     * <b>References</b>:
     * <ul>
     * <li><a href="http://en.wikipedia.org/wiki/IEEE_802.1Q">http://en.wikipedia.org/wiki/IEEE_802.1Q</a>
     * <li><a href="http://en.wikipedia.org/wiki/IEEE_802.1aq">http://en.wikipedia.org/wiki/IEEE_802.1aq</a>
     * </ul>
     * <br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final int VLAN_FRAME = 0x8100; // 33024

    /**
     * <b>Protocol</b>: IPX<br>
     * <b>References</b>: <a href="http://en.wikipedia.org/wiki/IPX">http://en.wikipedia.org/wiki/IPX</a><br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final int IPX_8137 = 0x8137; // 33079

    /**
     * <b>Protocol</b>: IPX<br>
     * <b>References</b>: <a href="http://en.wikipedia.org/wiki/IPX">http://en.wikipedia.org/wiki/IPX</a><br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final int IPX_8138 = 0x8138; // 33080

    /**
     * <b>Protocol</b>: QNX Qnet<br>
     * <b>References</b>:
     * <a href="http://en.wikipedia.org/wiki/QNX#Transparent_Distributed_Processing">http://en.wikipedia.org/wiki/QNX#
     * Transparent_Distributed_Processing</a><br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final int QNX = 0x8204; // 33284

    /**
     * <b>Protocol</b>: Internet Protocol Version 6 (IPv6)<br>
     * <b>References</b>:
     * <a href="http://en.wikipedia.org/wiki/Internet_Protocol_Version_6">http://en.wikipedia.org/wiki/
     * Internet_Protocol_Version_6</a><br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final int IPv6 = 0x86DD; // 34525

    /**
     * <b>Protocol</b>: Ethernet flow control<br>
     * <b>References</b>:
     * <a href="http://en.wikipedia.org/wiki/Ethernet_flow_control">http://en.wikipedia.org/wiki/Ethernet_flow_control
     * </a><br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final int ETH_FLOW = 0x8808; // 34824

    /**
     * <b>Protocol</b>: Slow Protocols (IEEE 802.3)<br>
     * <b>References</b>: <a href="http://en.wikipedia.org/wiki/IEEE_802.3">http://en.wikipedia.org/wiki/IEEE_802.3</a>
     * <br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final int SLOW_PROTOCOLS = 0x8809; // 34825

    /**
     * <b>Protocol</b>: CobraNet<br>
     * <b>References</b>: <a href="http://en.wikipedia.org/wiki/CobraNet">http://en.wikipedia.org/wiki/CobraNet</a><br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final int COBRANET = 0x8819; // 34841

    /**
     * <b>Protocol</b>: MPLS unicast<br>
     * <b>References</b>:
     * <a href="http://en.wikipedia.org/wiki/Multiprotocol_Label_Switching">http://en.wikipedia.org/wiki/
     * Multiprotocol_Label_Switching</a><br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final int MPLS_UNICAST = 0x8847; // 34887

    /**
     * <b>Protocol</b>: MPLS multicast<br>
     * <b>References</b>:
     * <a href="http://en.wikipedia.org/wiki/Multiprotocol_Label_Switching">http://en.wikipedia.org/wiki/
     * Multiprotocol_Label_Switching</a><br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final int MPLS_MULTICAST = 0x8848; // 34888

    /**
     * <b>Protocol</b>: PPPoE Discovery Stage<br>
     * <b>References</b>:
     * <a href="http://en.wikipedia.org/wiki/Point-to-Point_Protocol_over_Ethernet">http://en.wikipedia.org/wiki/Point-
     * to-Point_Protocol_over_Ethernet</a><br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final int PPPoE_DISCOVERY = 0x8863; // 34915

    /**
     * <b>Protocol</b>: PPPoE Session Stage<br>
     * <b>References</b>:
     * <a href="http://en.wikipedia.org/wiki/Point-to-Point_Protocol_over_Ethernet">http://en.wikipedia.org/wiki/Point-
     * to-Point_Protocol_over_Ethernet</a><br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final int PPPoE_SESSION = 0x8864; // 34916

    /**
     * <b>Protocol</b>: Jumbo Frames[2]<br>
     * <b>References</b>:
     * <a href="http://en.wikipedia.org/wiki/Jumbo_frame">http://en.wikipedia.org/wiki/Jumbo_frame</a><br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final int JUMBO_FRAMES = 0x8870; // 34928

    /**
     * <b>Protocol</b>: HomePlug 1.0 MME<br>
     * <b>References</b>: <a href="http://en.wikipedia.org/wiki/HomePlug">http://en.wikipedia.org/wiki/HomePlug</a><br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final int HOMEPLUG_10 = 0x887B; // 34939

    /**
     * <b>Protocol</b>: EAP over LAN (IEEE 802.1X)<br>
     * <b>References</b>:
     * <a href="http://en.wikipedia.org/wiki/IEEE_802.1X">http://en.wikipedia.org/wiki/IEEE_802.1X</a><br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final int EAP_OVER_LAN = 0x888E; // 34958

    /**
     * <b>Protocol</b>: PROFINET Protocol<br>
     * <b>References</b>: <a href="http://en.wikipedia.org/wiki/PROFINET">http://en.wikipedia.org/wiki/PROFINET</a><br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final int PROFINET = 0x8892; // 34962

    /**
     * <b>Protocol</b>: HyperSCSI (SCSI over Ethernet)<br>
     * <b>References</b>: <a href="http://en.wikipedia.org/wiki/HyperSCSI">http://en.wikipedia.org/wiki/HyperSCSI</a>
     * <br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final int HYPERSCSI = 0x889A; // 34970

    /**
     * <b>Protocol</b>: ATA over Ethernet<br>
     * <b>References</b>:
     * <a href="http://en.wikipedia.org/wiki/ATA_over_Ethernet">http://en.wikipedia.org/wiki/ATA_over_Ethernet</a><br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final int ATA_OVER_ETH = 0x88A2; // 34978

    /**
     * <b>Protocol</b>: EtherCAT Protocol<br>
     * <b>References</b>: <a href="http://en.wikipedia.org/wiki/EtherCAT">http://en.wikipedia.org/wiki/EtherCAT</a><br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final int ETHERCAT = 0x88A4; // 34980

    /**
     * <b>Protocol</b>: Provider Bridging (IEEE 802.1ad) &#38; Shortest Path Bridging IEEE 802.1aq[5]<br>
     * <b>References</b>:
     * <a href="http://en.wikipedia.org/wiki/IEEE_802.1ad">http://en.wikipedia.org/wiki/IEEE_802.1ad</a><br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final int BRIDGING = 0x88A8; // 34984

    /**
     * <b>Protocol</b>: Ethernet Powerlink[citation needed]<br>
     * <b>References</b>:
     * <a href="http://en.wikipedia.org/wiki/Ethernet_Powerlink">http://en.wikipedia.org/wiki/Ethernet_Powerlink</a><br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final int POWERLINK = 0x88AB; // 34987

    /**
     * <b>Protocol</b>: Link Layer Discovery Protocol (LLDP)<br>
     * <b>References</b>:
     * <a href="http://en.wikipedia.org/wiki/Link_Layer_Discovery_Protocol">http://en.wikipedia.org/wiki/
     * Link_Layer_Discovery_Protocol</a><br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final int LLDP = 0x88CC; // 35020

    /**
     * <b>Protocol</b>: SERCOS III<br>
     * <b>References</b>: <a href="http://en.wikipedia.org/wiki/SERCOS_III">http://en.wikipedia.org/wiki/SERCOS_III</a>
     * <br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final int SERCOS = 0x88CD; // 35021

    /**
     * <b>Protocol</b>: HomePlug AV MME[citation needed]<br>
     * <b>References</b>: <a href="http://en.wikipedia.org/wiki/HomePlug">http://en.wikipedia.org/wiki/HomePlug</a><br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final int HOMEPLUG_AV = 0x88E1; // 35041

    /**
     * <b>Protocol</b>: Media Redundancy Protocol (IEC62439-2)<br>
     * <b>References</b>: <a href="http://en.wikipedia.org/wiki/Media_Redundancy_Protocol">http://en.wikipedia.org/wiki/
     * Media_Redundancy_Protocol</a><br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final int MRP = 0x88E3; // 35043

    /**
     * <b>Protocol</b>: MAC security (IEEE 802.1AE)<br>
     * <b>References</b>:
     * <a href="http://en.wikipedia.org/wiki/IEEE_802.1AE">http://en.wikipedia.org/wiki/IEEE_802.1AE</a><br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final int MAC_SEC = 0x88E5; // 35045

    /**
     * <b>Protocol</b>: Precision Time Protocol (IEEE 1588)<br>
     * <b>References</b>: <a href="http://en.wikipedia.org/wiki/Precision_Time_Protocol">http://en.wikipedia.org/wiki/
     * Precision_Time_Protocol</a><br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final int PTP = 0x88F7; // 35063

    /**
     * <b>Protocol</b>: IEEE 802.1ag Connectivity Fault Management (CFM) Protocol / ITU-T Recommendation Y.1731 (OAM)
     * <br>
     * <b>References</b>:
     * <ul>
     * <li><a href="http://en.wikipedia.org/wiki/IEEE_802.1ag">http://en.wikipedia.org/wiki/IEEE_802.1ag</a>
     * <li><a href="http://en.wikipedia.org/wiki/Connectivity_Fault_Management">http://en.wikipedia.org/wiki/
     * Connectivity_Fault_Management</a>
     * <li><a href="http://en.wikipedia.org/wiki/ITU-T_Recommendation_Y.1731">http://en.wikipedia.org/wiki/ITU-
     * T_Recommendation_Y.1731</a>
     * <li><a href="http://en.wikipedia.org/wiki/OA%26M">http://en.wikipedia.org/wiki/OA%26M</a>
     * </ul>
     * <br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final int CFM = 0x8902; // 35074

    /**
     * <b>Protocol</b>: Fibre Channel over Ethernet (FCoE)<br>
     * <b>References</b>:
     * <a href="http://en.wikipedia.org/wiki/Fibre_Channel_over_Ethernet">http://en.wikipedia.org/wiki/
     * Fibre_Channel_over_Ethernet</a><br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final int FCoE = 0x8906; // 35078

    /**
     * <b>Protocol</b>: FCoE Initialization Protocol<br>
     * <b>References</b>:
     * <a href="http://en.wikipedia.org/wiki/Fibre_Channel_over_Ethernet">http://en.wikipedia.org/wiki/
     * Fibre_Channel_over_Ethernet</a><br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final int FCoE_INIT = 0x8914; // 35092

    /**
     * <b>Protocol</b>: RDMA over Converged Ethernet (RoCE)<br>
     * <b>References</b>:
     * <a href="http://en.wikipedia.org/wiki/RDMA_over_Converged_Ethernet">http://en.wikipedia.org/wiki/
     * RDMA_over_Converged_Ethernet</a><br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final int RoCE = 0x8915; // 35093

    /**
     * <b>Protocol</b>: High-availability Seamless Redundancy (HSR)<br>
     * <b>References</b>:
     * <a href="http://en.wikipedia.org/wiki/High-availability_Seamless_Redundancy">http://en.wikipedia.org/wiki/High-
     * availability_Seamless_Redundancy</a><br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final int HSR = 0x892F; // 35119

    /**
     * <b>Protocol</b>: Ethernet Configuration Testing Protocol[6]<br>
     * <b>References</b>:
     * <a href="http://en.wikipedia.org/wiki/Ethernet_Configuration_Testing_Protocol">http://en.wikipedia.org/wiki/
     * Ethernet_Configuration_Testing_Protocol</a><br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final int CONF_TEST = 0x9000; // 36864

    /**
     * <b>Protocol</b>: Q-in-Q<br>
     * <b>References</b>: <a href="http://en.wikipedia.org/wiki/Q-in-Q">http://en.wikipedia.org/wiki/Q-in-Q</a><br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final int Q_IN_Q = 0x9100; // 37120

    /**
     * <b>Protocol</b>: Veritas Low Latency Transport (LLT)[7] for Veritas Cluster Server<br>
     * <b>References</b>: <a href="http://wiki.wireshark.org/LLT">http://wiki.wireshark.org/LLT</a><br>
     * 
     * @see <a href="http://en.wikipedia.org/wiki/EtherType">http://en.wikipedia.org/wiki/EtherType</a>
     */
    public static final int LLT = 0xCAFE; // 51966

}
