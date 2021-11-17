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
* This file is generated under this project, "open-commons-core".
*
* Date  : 2015. 3. 4. 오후 6:23:47
*
* Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
* 
*/

package open.commons.utils;

import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;

/**
 * 
 * 
 * @since 2015. 3. 4.
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public class NetUtils {
    /**
     * Regular Expression from
     * <a href="https://www.oreilly.com/library/view/regular-expressions-cookbook/9780596802837/ch07s16.html">here</a>.
     */
    public static final String REGEX_IPV4 = "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";
    /**
     * IPv6 Regular Expression from 'http://www.java2s.com/example/java/java.util.regex/is-ipv6-address-by-regex.html'.
     */
    public static final String REGEX_IPV6 = "^(?:[0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}" // IPv6 Standard
            + "|" //
            + "((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)::((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)" // IPv6 HEX
                                                                                                             // Compressed
                                                                                                             // Pattern
    ;

    /**
     * Vald Port Range (1 ~ 65535).<br>
     * Regular Expression from
     * <a href="https://github.com/cusspvz/proxywrap/blob/master/lib/proxy-protocol.regexp.js#L85">here</a>.
     */
    public static final String REGEX_PORT = "([1-9][0-9]{0,3}|[1-5][0-9]{4}|6[0-4][0-9]{3}|65[0-4][0-9]{2}|655[0-2][0-9]|6553[0-5])";

    /**
     * Valid Domain Name.<br>
     * Regular Expression from
     * <a href="https://www.oreilly.com/library/view/regular-expressions-cookbook/9781449327453/ch08s15.html"></a>
     */
    public static final String REGEX_DOMAIN = "\\b((?=[a-z0-9-]{1,63}\\.)(xn--)?[a-z0-9]+(-[a-z0-9]+)*\\.)+[a-z]{2,63}\\b";

    /** @see #REGEX_IPV4 */
    public static final String REGEX_IPV4_STRICT = String.join(REGEX_IPV4, "^", "S");
    /** @see #REGEX_IPV6 */
    public static final String REGEX_IPV6_STRICT = String.join(REGEX_IPV6, "^", "S");
    /**
     * @see #REGEX_IPV4
     * @see #REGEX_IPV6
     */
    public static final String REGEX_IPV4_IPV6 = String.join("|", REGEX_IPV4, REGEX_IPV6);
    /**
     * @see #REGEX_IPV4
     * @see #REGEX_IPV6
     */
    public static final String REGEX_IPV4_IPV6_STRICT = String.join(REGEX_IPV4_IPV6, "^", "$");
    /** @see #REGEX_PORT */
    public static final String REGEX_PORT_STRICT = String.join(REGEX_PORT, "^", "$");
    /** @see #REGEX_DOMAIN */
    public static final String REGEX_DOMAIN_STRICT = String.join(REGEX_DOMAIN, "^", "$");

    /**
     * {@link NetworkInterface}에 포함된 {@link InterfaceAddress}중에서 IPV4에 해당하는 {@link InterfaceAddress}를 반환한다.
     * 
     * @param ni
     * @return <b><code>nullable</code></b>.
     *
     * @since 2015. 3. 4.
     */
    public static InterfaceAddress getInet4Address(NetworkInterface ni) {
        for (InterfaceAddress infAddr : ni.getInterfaceAddresses()) {
            if (infAddr.getAddress().getHostAddress().matches(REGEX_IPV4_STRICT)) {
                return infAddr;
            }
        }

        return null;
    }

    /**
     * {@link NetworkInterface}에 포함된 {@link InterfaceAddress}중에서 IPV4에 해당하는 {@link InterfaceAddress}를 반환한다.
     * 
     * @param name
     * @return <b><code>nullable</code></b>.
     * 
     * @throws SocketException
     *
     * @since 2015. 3. 4.
     */
    public static InterfaceAddress getInet4Address(String name) throws SocketException {
        NetworkInterface ni = NetworkInterface.getByName(name);

        for (InterfaceAddress infAddr : ni.getInterfaceAddresses()) {
            if (infAddr.getAddress().getHostAddress().matches(REGEX_IPV4_STRICT)) {
                return infAddr;
            }
        }

        return null;
    }

    /**
     * IPv4 범위에 포함되는 {@link Integer}값을 IPv4 포맷 문자열로 변경한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2015. 3. 4.		박준홍			최초 작성
     * </pre>
     *
     * @param ip
     * @return
     *
     * @since 2015. 3. 4.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @deprecated Use {@link NetUtils#intToIPv4(int)}.
     */
    public static String intToIp(int ip) {
        StringBuffer sb = new StringBuffer();

        sb.append(ip >> 24 & 0xFF);
        sb.append('.');
        sb.append(ip >> 16 & 0xFF);
        sb.append('.');
        sb.append(ip >> 8 & 0xFF);
        sb.append('.');
        sb.append(ip & 0xFF);

        return sb.toString();
    }

    /**
     * IPv4 범위에 포함되는 {@link Integer}값을 IPv4 포맷 문자열로 변경한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2015. 3. 4.		박준홍			최초 작성
     * </pre>
     *
     * @param ip
     * @return
     *
     * @since 2015. 3. 4.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static String intToIPv4(int ip) {
        StringBuffer sb = new StringBuffer();

        sb.append(ip >> 24 & 0xFF);
        sb.append('.');
        sb.append(ip >> 16 & 0xFF);
        sb.append('.');
        sb.append(ip >> 8 & 0xFF);
        sb.append('.');
        sb.append(ip & 0xFF);

        return sb.toString();
    }

    /**
     * IPv4 주소 여부를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 7. 12.		박준홍			최초 작성
     * </pre>
     *
     * @param ipAddr
     * @return
     *
     * @since 2021. 7. 12.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @see #REGEX_IPV4
     * @see #REGEX_IPV4_STRICT
     */
    public static boolean isIPv4(String ipAddr) {
        return ipAddr == null ? false : ipAddr.matches(REGEX_IPV4_STRICT);
    }

    /**
     * IPv4 또는 IPv6 주소 여부를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 7. 12.		박준홍			최초 작성
     * </pre>
     *
     * @param ipAddr
     * @return
     *
     * @since 2021. 7. 12.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @see #REGEX_IPV4_IPV6
     * @see #REGEX_IPV4_IPV6_STRICT
     */
    public static boolean isIPv4OrIPv6(String ipAddr) {
        return ipAddr == null ? false : ipAddr.matches(REGEX_IPV4_IPV6_STRICT);
    }

    /**
     * IPv6 주소 여부를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 7. 12.		박준홍			최초 작성
     * </pre>
     *
     * @param ipAddr
     * @return
     *
     * @since 2021. 7. 12.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @see #REGEX_IPV6
     * @see #REGEX_IPV6_STRICT
     */
    public static boolean isIPv6(String ipAddr) {
        return ipAddr == null ? false : ipAddr.matches(REGEX_IPV6_STRICT);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2015. 3. 4.      박준홍         최초 작성
     * </pre>
     *
     * @param mac
     * @return
     *
     * @since 2015. 3. 4.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static String toPrettyString(byte[] mac) {

        String macStr = toString(mac);

        StringBuffer sb = new StringBuffer();
        int pos = 0;
        for (char c : macStr.toCharArray()) {
            if (pos > 0 && pos % 2 == 0) {
                sb.append(':');
            }

            sb.append(c);

            pos++;
        }

        return sb.toString();
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2015. 3. 4.		박준홍			최초 작성
     * </pre>
     *
     * @param mac
     * @return
     *
     * @since 2015. 3. 4.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static String toString(byte[] mac) {
        return ByteUtils.hexBinString(mac);
    }
}
