/*
 * Copyright 2022 Park Jun-Hong_(parkjunhong77@gmail.com)
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
 * This file is generated under this project, "open-commons-core".
 *
 * Date  : 2022. 2. 11. 오후 2:12:14
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package open.commons.net;

import java.util.ArrayList;
import java.util.List;

/**
 * DNS Record Type
 * 
 * @since 2022. 2. 11.
 * @version 1.8.0
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
public enum DnsRecordType {

    /**
     * 구분: 리소스 레코드<br>
     * 타입: A<br>
     * 타입id(이진): 1<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC1035">RFC 1035</a><br>
     * 설명: Address record<br>
     */
    A("A", 1), //
    /**
     * 구분: 리소스 레코드<br>
     * 타입: AAAA<br>
     * 타입id(이진): 28<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC3596">RFC 3596</a><br>
     * 설명: IPv6 address record<br>
     */
    AAAA("AAAA", 28), //
    /**
     * 구분: 리소스 레코드<br>
     * 타입: AFSDB<br>
     * 타입id(이진): 18<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC1183">RFC 1183</a><br>
     * 설명: AFS database record<br>
     */
    AFSDB("AFSDB", 18), //
    /**
     * 구분: 리소스 레코드<br>
     * 타입: APL<br>
     * 타입id(이진): 42<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC3123">RFC 3123</a><br>
     * 설명: Address Prefix List<br>
     */
    APL("APL", 42), //
    /**
     * 구분: 리소스 레코드<br>
     * 타입: CAA<br>
     * 타입id(이진): 257<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC6844">RFC 6844</a><br>
     * 설명: Certification Authority Authorization<br>
     */
    CAA("CAA", 257), //
    /**
     * 구분: 리소스 레코드<br>
     * 타입: CDNSKEY<br>
     * 타입id(이진): 60<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC7344">RFC 7344</a><br>
     * 설명: -<br>
     */
    CDNSKEY("CDNSKEY", 60), //
    /**
     * 구분: 리소스 레코드<br>
     * 타입: CDS<br>
     * 타입id(이진): 59<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC7344">RFC 7344</a><br>
     * 설명: Child DS<br>
     */
    CDS("CDS", 59), //
    /**
     * 구분: 리소스 레코드<br>
     * 타입: CERT<br>
     * 타입id(이진): 37<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC4398">RFC 4398</a><br>
     * 설명: Certificate record<br>
     */
    CERT("CERT", 37), //
    /**
     * 구분: 리소스 레코드<br>
     * 타입: CNAME<br>
     * 타입id(이진): 5<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC1035">RFC 1035</a><br>
     * 설명: Canonical name record<br>
     */
    CNAME("CNAME", 5), //
    /**
     * 구분: 리소스 레코드<br>
     * 타입: CSYNC<br>
     * 타입id(이진): 62<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC7477">RFC 7477</a><br>
     * 설명: Child-to-Parent Synchronization<br>
     */
    CSYNC("CSYNC", 62), //
    /**
     * 구분: 리소스 레코드<br>
     * 타입: DHCID<br>
     * 타입id(이진): 49<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC4701">RFC 4701</a><br>
     * 설명: DHCP identifier<br>
     */
    DHCID("DHCID", 49), //
    /**
     * 구분: 리소스 레코드<br>
     * 타입: DLV<br>
     * 타입id(이진): 32769<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC4431">RFC 4431</a><br>
     * 설명: DNSSEC Lookaside Validation record<br>
     */
    DLV("DLV", 32769), //
    /**
     * 구분: 리소스 레코드<br>
     * 타입: DNAME<br>
     * 타입id(이진): 39<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC6672">RFC 6672</a><br>
     * 설명: -<br>
     */
    DNAME("DNAME", 39), //
    /**
     * 구분: 리소스 레코드<br>
     * 타입: DNSKEY<br>
     * 타입id(이진): 48<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC4034">RFC 4034</a><br>
     * 설명: DNS Key record<br>
     */
    DNSKEY("DNSKEY", 48), //
    /**
     * 구분: 리소스 레코드<br>
     * 타입: DS<br>
     * 타입id(이진): 43<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC4034">RFC 4034</a><br>
     * 설명: Delegation signer<br>
     */
    DS("DS", 43), //
    /**
     * 구분: 리소스 레코드<br>
     * 타입: EUI48<br>
     * 타입id(이진): 108<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC7043">RFC 7043</a><br>
     * 설명: MAC address (EUI-48)<br>
     */
    EUI48("EUI48", 108), //
    /**
     * 구분: 리소스 레코드<br>
     * 타입: EUI64<br>
     * 타입id(이진): 109<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC7043">RFC 7043</a><br>
     * 설명: MAC address (EUI-64)<br>
     */
    EUI64("EUI64", 109), //
    /**
     * 구분: 리소스 레코드<br>
     * 타입: HINFO<br>
     * 타입id(이진): 13<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC8482">RFC 8482</a><br>
     * 설명: Host Information<br>
     */
    HINFO("HINFO", 13), //
    /**
     * 구분: 리소스 레코드<br>
     * 타입: HIP<br>
     * 타입id(이진): 55<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC8005">RFC 8005</a><br>
     * 설명: Host Identity Protocol<br>
     */
    HIP("HIP", 55), //
    /**
     * 구분: 리소스 레코드<br>
     * 타입: IPSECKEY<br>
     * 타입id(이진): 45<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC4025">RFC 4025</a><br>
     * 설명: IPsec Key<br>
     */
    IPSECKEY("IPSECKEY", 45), //
    /**
     * 구분: 리소스 레코드<br>
     * 타입: KEY<br>
     * 타입id(이진): 25<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC2535">RFC 2535</a><br>
     * <a href="https://datatracker.ietf.org/doc/html/RFC2930">RFC 2930</a><br>
     * 설명: Key record<br>
     */
    KEY("KEY", 25), //
    /**
     * 구분: 리소스 레코드<br>
     * 타입: KX<br>
     * 타입id(이진): 36<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC2230">RFC 2230</a><br>
     * 설명: Key Exchanger record<br>
     */
    KX("KX", 36), //
    /**
     * 구분: 리소스 레코드<br>
     * 타입: LOC<br>
     * 타입id(이진): 29<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC1876">RFC 1876</a><br>
     * 설명: Location record<br>
     */
    LOC("LOC", 29), //
    /**
     * 구분: 리소스 레코드<br>
     * 타입: MX<br>
     * 타입id(이진): 15<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC1035">RFC 1035</a><br>
     * <a href="https://datatracker.ietf.org/doc/html/RFC7505">RFC 7505</a><br>
     * 설명: Mail exchange record<br>
     */
    MX("MX", 15), //
    /**
     * 구분: 리소스 레코드<br>
     * 타입: NAPTR<br>
     * 타입id(이진): 35<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC3403">RFC 3403</a><br>
     * 설명: Naming Authority Pointer<br>
     */
    NAPTR("NAPTR", 35), //
    /**
     * 구분: 리소스 레코드<br>
     * 타입: NS<br>
     * 타입id(이진): 2<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC1035">RFC 1035</a><br>
     * 설명: Name server record<br>
     */
    NS("NS", 2), //
    /**
     * 구분: 리소스 레코드<br>
     * 타입: NSEC<br>
     * 타입id(이진): 47<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC4034">RFC 4034</a><br>
     * 설명: Next Secure record<br>
     */
    NSEC("NSEC", 47), //
    /**
     * 구분: 리소스 레코드<br>
     * 타입: NSEC3<br>
     * 타입id(이진): 50<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC5155">RFC 5155</a><br>
     * 설명: Next Secure record version 3<br>
     */
    NSEC3("NSEC3", 50), //
    /**
     * 구분: 리소스 레코드<br>
     * 타입: NSEC3PARAM<br>
     * 타입id(이진): 51<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC5155">RFC 5155</a><br>
     * 설명: NSEC3 parameters<br>
     */
    NSEC3PARAM("NSEC3PARAM", 51), //
    /**
     * 구분: 리소스 레코드<br>
     * 타입: OPENPGPKEY<br>
     * 타입id(이진): 61<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC7929">RFC 7929</a><br>
     * 설명: OpenPGP public key record<br>
     */
    OPENPGPKEY("OPENPGPKEY", 61), //
    /**
     * 구분: 리소스 레코드<br>
     * 타입: PTR<br>
     * 타입id(이진): 12<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC1035">RFC 1035</a><br>
     * 설명: PTR Resource Record<br>
     */
    PTR("PTR", 12), //
    /**
     * 구분: 리소스 레코드<br>
     * 타입: RRSIG<br>
     * 타입id(이진): 46<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC4034">RFC 4034</a><br>
     * 설명: DNSSEC signature<br>
     */
    RRSIG("RRSIG", 46), //
    /**
     * 구분: 리소스 레코드<br>
     * 타입: RP<br>
     * 타입id(이진): 17<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC1183">RFC 1183</a><br>
     * 설명: Responsible Person<br>
     */
    RP("RP", 17), //
    /**
     * 구분: 리소스 레코드<br>
     * 타입: SIG<br>
     * 타입id(이진): 24<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC2535">RFC 2535</a><br>
     * 설명: Signature<br>
     */
    SIG("SIG", 24), //
    /**
     * 구분: 리소스 레코드<br>
     * 타입: SMIMEA<br>
     * 타입id(이진): 53<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC8162">RFC 8162</a><br>
     * 설명: S/MIME cert association[6]<br>
     */
    SMIMEA("SMIMEA", 53), //
    /**
     * 구분: 리소스 레코드<br>
     * 타입: SOA<br>
     * 타입id(이진): 6<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC1035">RFC 1035</a><br>
     * <a href="https://datatracker.ietf.org/doc/html/RFC2308">RFC 2308</a><br>
     * 설명: Specifies authoritative information about a DNS zone, including the primary name server, the email of the
     * domain administrator, the domain serial number, and several timers relating to refreshing the zone.<br>
     */
    SOA("SOA", 6), //
    /**
     * 구분: 리소스 레코드<br>
     * 타입: SRV<br>
     * 타입id(이진): 33<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC2782">RFC 2782</a><br>
     * 설명: Service locator<br>
     */
    SRV("SRV", 33), //
    /**
     * 구분: 리소스 레코드<br>
     * 타입: SSHFP<br>
     * 타입id(이진): 44<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC4255">RFC 4255</a><br>
     * 설명: SSH Public Key Fingerprint<br>
     */
    SSHFP("SSHFP", 44), //
    /**
     * 구분: 리소스 레코드<br>
     * 타입: TA<br>
     * 타입id(이진): 32768<br>
     * RFC: -<br>
     * 설명: DNSSEC Trust Authorities<br>
     */
    TA("TA", 32768), //
    /**
     * 구분: 리소스 레코드<br>
     * 타입: TKEY<br>
     * 타입id(이진): 249<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC2930">RFC 2930</a><br>
     * 설명: Transaction Key record<br>
     */
    TKEY("TKEY", 249), //
    /**
     * 구분: 리소스 레코드<br>
     * 타입: TLSA<br>
     * 타입id(이진): 52<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC6698">RFC 6698</a><br>
     * 설명: TLSA certificate association<br>
     */
    TLSA("TLSA", 52), //
    /**
     * 구분: 리소스 레코드<br>
     * 타입: TSIG<br>
     * 타입id(이진): 250<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC2845">RFC 2845</a><br>
     * 설명: Transaction Signature<br>
     */
    TSIG("TSIG", 250), //
    /**
     * 구분: 리소스 레코드<br>
     * 타입: TXT<br>
     * 타입id(이진): 16<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC1035">RFC 1035</a><br>
     * 설명: Text record<br>
     */
    TXT("TXT", 16), //
    /**
     * 구분: 리소스 레코드<br>
     * 타입: URI<br>
     * 타입id(이진): 256<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC7553">RFC 7553</a><br>
     * 설명: Uniform Resource Identifier<br>
     */
    URI("URI", 256), //
    /**
     * 구분: 리소스 레코드<br>
     * 타입: ZONEMD<br>
     * 타입id(이진): 63<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/TBA">TBA</a><br>
     * 설명: -<br>
     */
    ZONEMD("ZONEMD", 63), //
    /**
     * 구분: 기타 유형 및 의사 리소스 레코드<br>
     * 타입: *<br>
     * 타입id(이진): 255<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC1035">RFC 1035</a><br>
     * 설명: All cached records<br>
     */
    ASTERIK("*", 255), //
    /**
     * 구분: 기타 유형 및 의사 리소스 레코드<br>
     * 타입: AXFR<br>
     * 타입id(이진): 252<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC1035">RFC 1035</a><br>
     * 설명: Authoritative Zone Transfer<br>
     */
    AXFR("AXFR", 252), //
    /**
     * 구분: 기타 유형 및 의사 리소스 레코드<br>
     * 타입: IXFR<br>
     * 타입id(이진): 251<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC1996">RFC 1996</a><br>
     * 설명: Incremental Zone Transfer<br>
     */
    IXFR("IXFR", 251), //
    /**
     * 구분: 기타 유형 및 의사 리소스 레코드<br>
     * 타입: OPT<br>
     * 타입id(이진): 41<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC6891">RFC 6891</a><br>
     * 설명: Option<br>
     */
    OPT("OPT", 41), //
    /**
     * 구분: 구식 레코드 타입<br>
     * 타입: MD<br>
     * 타입id(이진): 3<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC883">RFC 883</a><br>
     * 설명: RFC 973<br>
     */
    MD("MD", 3), //
    /**
     * 구분: 구식 레코드 타입<br>
     * 타입: MF<br>
     * 타입id(이진): 4<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC883">RFC 883</a><br>
     * 설명: RFC 973<br>
     */
    MF("MF", 4), //
    /**
     * 구분: 구식 레코드 타입<br>
     * 타입: MAILA<br>
     * 타입id(이진): 254<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC883">RFC 883</a><br>
     * 설명: RFC 973<br>
     */
    MAILA("MAILA", 254), //
    /**
     * 구분: 구식 레코드 타입<br>
     * 타입: MB<br>
     * 타입id(이진): 7<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC883">RFC 883</a><br>
     * 설명: Not formally obsoleted. Unlikely to be ever adopted (RFC 2505).<br>
     */
    MB("MB", 7), //
    /**
     * 구분: 구식 레코드 타입<br>
     * 타입: MG<br>
     * 타입id(이진): 8<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC883">RFC 883</a><br>
     * 설명: Not formally obsoleted. Unlikely to be ever adopted (RFC 2505).<br>
     */
    MG("MG", 8), //
    /**
     * 구분: 구식 레코드 타입<br>
     * 타입: MR<br>
     * 타입id(이진): 9<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC883">RFC 883</a><br>
     * 설명: Not formally obsoleted. Unlikely to be ever adopted (RFC 2505).<br>
     */
    MR("MR", 9), //
    /**
     * 구분: 구식 레코드 타입<br>
     * 타입: MINFO<br>
     * 타입id(이진): 14<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC883">RFC 883</a><br>
     * 설명: Not formally obsoleted. Unlikely to be ever adopted (RFC 2505).<br>
     */
    MINFO("MINFO", 14), //
    /**
     * 구분: 구식 레코드 타입<br>
     * 타입: MAILB<br>
     * 타입id(이진): 253<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC883">RFC 883</a><br>
     * 설명: Not formally obsoleted. Unlikely to be ever adopted (RFC 2505).<br>
     */
    MAILB("MAILB", 253), //
    /**
     * 구분: 구식 레코드 타입<br>
     * 타입: WKS<br>
     * 타입id(이진): 11<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC883">RFC 883</a><br>
     * <a href="https://datatracker.ietf.org/doc/html/RFC1035">RFC 1035</a><br>
     * 설명: Declared as "not to be relied upon" by RFC 1123 (more in RFC 1127).<br>
     */
    WKS("WKS", 11), //
    /**
     * 구분: 구식 레코드 타입<br>
     * 타입: NB<br>
     * 타입id(이진): 32<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC1002">RFC 1002</a><br>
     * 설명: -<br>
     */
    NB("NB", 32), //
    /**
     * 구분: 구식 레코드 타입<br>
     * 타입: NBSTAT<br>
     * 타입id(이진): 33<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC1002">RFC 1002</a><br>
     * 설명: -<br>
     */
    NBSTAT("NBSTAT", 33), //
    /**
     * 구분: 구식 레코드 타입<br>
     * 타입: NULL<br>
     * 타입id(이진): 10<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC883">RFC 883</a><br>
     * 설명: RFC 1035<br>
     */
    NULL("NULL", 10), //
    /**
     * 구분: 구식 레코드 타입<br>
     * 타입: A6<br>
     * 타입id(이진): 38<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC2874">RFC 2874</a><br>
     * 설명: RFC 6563<br>
     */
    A6("A6", 38), //
    /**
     * 구분: 구식 레코드 타입<br>
     * 타입: NXT<br>
     * 타입id(이진): 30<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC2065">RFC 2065</a><br>
     * 설명: RFC 3755<br>
     */
    NXT("NXT", 30), //
    // /**
    // * 구분: 구식 레코드 타입<br>
    // * 타입: KEY<br>
    // * 타입id(이진): 25<br>
    // * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC2065">RFC 2065</a><br>
    // * 설명: RFC 3755<br>
    // */
    // KEY("KEY", 25), //
    // /**
    // * 구분: 구식 레코드 타입<br>
    // * 타입: SIG<br>
    // * 타입id(이진): 24<br>
    // * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC2065">RFC 2065</a><br>
    // * 설명: RFC 3755<br>
    // */
    // SIG("SIG", 24), //
    // /**
    // * 구분: 구식 레코드 타입<br>
    // * 타입: HINFO<br>
    // * 타입id(이진): 13<br>
    // * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC883">RFC 883</a><br>
    // * 설명: Unobsoleted by RFC 8482. Currently used by Cloudflare in response to queries of the type ANY.<br>
    // */
    // HINFO("HINFO", 13), //
    // /**
    // * 구분: 구식 레코드 타입<br>
    // * 타입: RP<br>
    // * 타입id(이진): 17<br>
    // * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC1183">RFC 1183</a><br>
    // * 설명: -<br>
    // */
    // RP("RP", 17), //
    /**
     * 구분: 구식 레코드 타입<br>
     * 타입: X25<br>
     * 타입id(이진): 19<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC1183">RFC 1183</a><br>
     * 설명: -<br>
     */
    X25("X25", 19), //
    /**
     * 구분: 구식 레코드 타입<br>
     * 타입: ISDN<br>
     * 타입id(이진): 20<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC1183">RFC 1183</a><br>
     * 설명: -<br>
     */
    ISDN("ISDN", 20), //
    /**
     * 구분: 구식 레코드 타입<br>
     * 타입: RT<br>
     * 타입id(이진): 21<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC1183">RFC 1183</a><br>
     * 설명: -<br>
     */
    RT("RT", 21), //
    /**
     * 구분: 구식 레코드 타입<br>
     * 타입: NSAP<br>
     * 타입id(이진): 22<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC1706">RFC 1706</a><br>
     * 설명: -<br>
     */
    NSAP("NSAP", 22), //
    /**
     * 구분: 구식 레코드 타입<br>
     * 타입: NSAP-PTR<br>
     * 타입id(이진): 23<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC1706">RFC 1706</a><br>
     * 설명: -<br>
     */
    NSAP_PTR("NSAP-PTR", 23), //
    /**
     * 구분: 구식 레코드 타입<br>
     * 타입: PX<br>
     * 타입id(이진): 26<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC2163">RFC 2163</a><br>
     * 설명: -<br>
     */
    PX("PX", 26), //
    /**
     * 구분: 구식 레코드 타입<br>
     * 타입: EID<br>
     * 타입id(이진): 31<br>
     * RFC: -<br>
     * 설명: -<br>
     */
    EID("EID", 31), //
    /**
     * 구분: 구식 레코드 타입<br>
     * 타입: NIMLOC<br>
     * 타입id(이진): 32<br>
     * RFC: -<br>
     * 설명: -<br>
     */
    NIMLOC("NIMLOC", 32), //
    /**
     * 구분: 구식 레코드 타입<br>
     * 타입: ATMA<br>
     * 타입id(이진): 34<br>
     * RFC: -<br>
     * 설명: -<br>
     */
    ATMA("ATMA", 34), //
    // /**
    // * 구분: 구식 레코드 타입<br>
    // * 타입: APL<br>
    // * 타입id(이진): 42<br>
    // * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC3123">RFC 3123</a><br>
    // * 설명: -<br>
    // */
    // APL("APL", 42), //
    /**
     * 구분: 구식 레코드 타입<br>
     * 타입: SINK<br>
     * 타입id(이진): 40<br>
     * RFC: -<br>
     * 설명: -<br>
     */
    SINK("SINK", 40), //
    /**
     * 구분: 구식 레코드 타입<br>
     * 타입: GPOS<br>
     * 타입id(이진): 27<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC1712">RFC 1712</a><br>
     * 설명: -<br>
     */
    GPOS("GPOS", 27), //
    /**
     * 구분: 구식 레코드 타입<br>
     * 타입: UINFO<br>
     * 타입id(이진): 100<br>
     * RFC: -<br>
     * 설명: -<br>
     */
    UINFO("UINFO", 100), //
    /**
     * 구분: 구식 레코드 타입<br>
     * 타입: UID<br>
     * 타입id(이진): 101<br>
     * RFC: -<br>
     * 설명: -<br>
     */
    UID("UID", 101), //
    /**
     * 구분: 구식 레코드 타입<br>
     * 타입: GID<br>
     * 타입id(이진): 102<br>
     * RFC: -<br>
     * 설명: -<br>
     */
    GID("GID", 102), //
    /**
     * 구분: 구식 레코드 타입<br>
     * 타입: UNSPEC<br>
     * 타입id(이진): 103<br>
     * RFC: -<br>
     * 설명: -<br>
     */
    UNSPEC("UNSPEC", 103), //
    /**
     * 구분: 구식 레코드 타입<br>
     * 타입: SPF<br>
     * 타입id(이진): 99<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC4408">RFC 4408</a><br>
     * 설명: RFC 7208<br>
     */
    SPF("SPF", 99), //
    /**
     * 구분: 구식 레코드 타입<br>
     * 타입: NINFO<br>
     * 타입id(이진): 56<br>
     * RFC: -<br>
     * 설명: -<br>
     */
    NINFO("NINFO", 56), //
    /**
     * 구분: 구식 레코드 타입<br>
     * 타입: RKEY<br>
     * 타입id(이진): 57<br>
     * RFC: -<br>
     * 설명: -<br>
     */
    RKEY("RKEY", 57), //
    /**
     * 구분: 구식 레코드 타입<br>
     * 타입: TALINK<br>
     * 타입id(이진): 58<br>
     * RFC: -<br>
     * 설명: -<br>
     */
    TALINK("TALINK", 58), //
    /**
     * 구분: 구식 레코드 타입<br>
     * 타입: NID<br>
     * 타입id(이진): 104<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC6742">RFC 6742</a><br>
     * 설명: -<br>
     */
    NID("NID", 104), //
    /**
     * 구분: 구식 레코드 타입<br>
     * 타입: L32<br>
     * 타입id(이진): 105<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC6742">RFC 6742</a><br>
     * 설명: -<br>
     */
    L32("L32", 105), //
    /**
     * 구분: 구식 레코드 타입<br>
     * 타입: L64<br>
     * 타입id(이진): 106<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC6742">RFC 6742</a><br>
     * 설명: -<br>
     */
    L64("L64", 106), //
    /**
     * 구분: 구식 레코드 타입<br>
     * 타입: LP<br>
     * 타입id(이진): 107<br>
     * RFC: <a href="https://datatracker.ietf.org/doc/html/RFC6742">RFC 6742</a><br>
     * 설명: -<br>
     */
    LP("LP", 107), //
    /**
     * 구분: 구식 레코드 타입<br>
     * 타입: DOA<br>
     * 타입id(이진): 259<br>
     * RFC: -<br>
     * 설명: -<br>
     */
    DOA("DOA", 259), //
    ;

    private String type;

    private int id;

    private DnsRecordType(String type, int id) {
        this.type = type;
        this.id = id;
    }

    /**
     *
     * @return a string of an instance of {@link DnsRecordType}
     *
     * @since 2022. 2. 11.
     * @author Park_Jun_Hong (jhpark@ymtech.co.kr)
     */
    public String get() {
        return this.type;
    }

    /**
     * @since 2022. 2. 11.
     * @author Park_Jun_Hong (jhpark@ymtech.co.kr)
     *
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return String.join(":", name(), this.type);
    }

    /**
     * 
     * @param type
     *            a string for {@link DnsRecordType} instance.
     *
     * @return an instance of {@link DnsRecordType}
     *
     * @since 2022. 2. 11.
     * @author Park_Jun_Hong (jhpark@ymtech.co.kr)
     *
     * @see #get(String, boolean)
     */
    public static DnsRecordType get(String type) {
        return get(type, false);
    }

    /**
     *
     * @param type
     *            a string for an instance of {@link DnsRecordType}.
     * @param ignoreCase
     *            ignore <code><b>case-sensitive</b></code> or not.
     *
     * @return an instance of {@link DnsRecordType}
     *
     * @since 2022. 2. 11.
     * @author Park_Jun_Hong (jhpark@ymtech.co.kr)
     */
    public static DnsRecordType get(String type, boolean ignoreCase) {

        if (type == null) {
            throw new IllegalArgumentException("'type' MUST NOT be null. input: " + type);
        }

        if (ignoreCase) {
            for (DnsRecordType value : values()) {
                if (value.type.equalsIgnoreCase(type)) {
                    return value;
                }
            }
        } else {
            for (DnsRecordType value : values()) {
                if (value.type.equals(type)) {
                    return value;
                }
            }
        }

        throw new IllegalArgumentException("Unexpected 'type' value of 'DnsRecordType'. expected: " + values0() + " & Ignore case-sensitive: " + ignoreCase + ", input: " + type);
    }

    private static List<String> values0() {

        List<String> valuesStr = new ArrayList<>();

        for (DnsRecordType value : values()) {
            valuesStr.add(value.get());
        }

        return valuesStr;
    }

}
