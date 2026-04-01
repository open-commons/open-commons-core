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

package open.commons.core.utils;

import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Objects;

import org.jspecify.annotations.Nullable;

/**
 * 네트워크 관련 처리(IP, MAC, Port, Domain)를 지원하는 유틸리티 클래스입니다.
 * 
 * @since 2015. 3. 4.
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
            + "((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)::((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)"; // IPv6
                                                                                                              // HEX
                                                                                                              // Compressed
                                                                                                              // Pattern
    /**
     * Valid Port Range (1 ~ 65535).<br>
     * Regular Expression from
     * <a href="https://github.com/cusspvz/proxywrap/blob/master/lib/proxy-protocol.regexp.js#L85">here</a>.
     */
    public static final String REGEX_PORT = "([1-9][0-9]{0,3}|[1-5][0-9]{4}|6[0-4][0-9]{3}|65[0-4][0-9]{2}|655[0-2][0-9]|6553[0-5])";

    /**
     * Valid Domain Name.<br>
     * Regular Expression from
     * <a href="https://www.oreilly.com/library/view/regular-expressions-cookbook/9781449327453/ch08s15.html">here</a>
     */
    public static final String REGEX_DOMAIN = "\\b((?=[a-z0-9-]{1,63}\\.)(xn--)?[a-z0-9]+(-[a-z0-9]+)*\\.)+[a-z]{2,63}\\b";

    /** @see #REGEX_IPV4 */
    public static final String REGEX_IPV4_STRICT = "^" + REGEX_IPV4 + "$";

    /** @see #REGEX_IPV6 */
    public static final String REGEX_IPV6_STRICT = "^" + REGEX_IPV6 + "$";
    /**
     * @see #REGEX_IPV4
     * @see #REGEX_IPV6
     */
    public static final String REGEX_IPV4_IPV6 = REGEX_IPV4 + "|" + REGEX_IPV6;
    /**
     * @see #REGEX_IPV4
     * @see #REGEX_IPV6
     */
    public static final String REGEX_IPV4_IPV6_STRICT = "^" + REGEX_IPV4_IPV6 + "$";
    /** @see #REGEX_PORT */
    public static final String REGEX_PORT_STRICT = "^" + REGEX_PORT + "$";
    /** @see #REGEX_DOMAIN */
    public static final String REGEX_DOMAIN_STRICT = "^" + REGEX_DOMAIN + "$";

    /**
     * 유틸리티 클래스의 인스턴스화 방지
     */
    private NetUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    /**
     * {@link NetworkInterface}에 포함된 {@link InterfaceAddress} 중에서 IPv4에 해당하는 {@link InterfaceAddress}를 반환합니다.
     * 
     * @param ni
     *            검색할 대상 네트워크 인터페이스
     * 
     * @return IPv4에 해당하는 주소 객체. 존재하지 않을 경우 {@code null} 반환.
     * 
     * @throws NullPointerException
     *             파라미터({@code ni})가 {@code null}인 경우 발생.
     *
     * @since 2015. 3. 4.
     */
    public static @Nullable InterfaceAddress getInet4Address(NetworkInterface ni) {
        Objects.requireNonNull(ni, "A parameter 'ni' must not be 'null'.");

        for (InterfaceAddress infAddr : ni.getInterfaceAddresses()) {
            if (infAddr.getAddress().getHostAddress().matches(REGEX_IPV4_STRICT)) {
                return infAddr;
            }
        }

        return null;
    }

    /**
     * 인터페이스 이름에 해당하는 {@link NetworkInterface}를 찾아, 포함된 {@link InterfaceAddress} 중에서 IPv4에 해당하는 주소를 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2015. 3. 4.      parkjunohng77@gmail.com         최초 작성
     * 2026. 4. 1.      parkjunhong77@gmail.com         (3.0.0) 존재하지 않는 인터페이스에 대한 NPE 예외 방어 추가
     * </pre>
     *
     * @param name
     *            검색할 네트워크 인터페이스의 이름 (예: "eth0", "en0")
     * 
     * @return IPv4에 해당하는 주소 객체. 이름에 해당하는 인터페이스가 없거나 IPv4 주소가 없으면 {@code null} 반환.
     * 
     * @throws NullPointerException
     *             파라미터({@code name})가 {@code null}인 경우 발생.
     * @throws SocketException
     *             네트워크 인터페이스 검색 중 입출력 오류가 발생한 경우.
     *
     * @since 2015. 3. 4.
     * @version 3.0.0
     */
    public static @Nullable InterfaceAddress getInet4Address(String name) throws SocketException {
        Objects.requireNonNull(name, "A parameter 'name' must not be 'null'.");

        NetworkInterface ni = NetworkInterface.getByName(name);
        // [안전성] 존재하지 않는 네트워크 인터페이스 이름 방어
        if (ni == null) {
            return null;
        }

        for (InterfaceAddress infAddr : ni.getInterfaceAddresses()) {
            if (infAddr.getAddress().getHostAddress().matches(REGEX_IPV4_STRICT)) {
                return infAddr;
            }
        }

        return null;
    }

    /**
     * IPv4 범위에 포함되는 정수({@link Integer})값을 IPv4 포맷 문자열로 변경합니다. <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2015. 3. 4.      parkjunohng77@gmail.com         최초 작성
     * 2026. 4. 1.      parkjunhong77@gmail.com         (3.0.0) Signed Integer 특성으로 인한 음수 IP 오류 해결(ip < 1 검증 제거)
     * </pre>
     *
     * @param ip
     *            변환할 32비트 정수형 IP 데이터
     * 
     * @return "xxx.xxx.xxx.xxx" 포맷의 IPv4 문자열
     *
     * @since 2015. 3. 4.
     * @version 3.0.0
     */
    // 아래 내용에 적용됨.
    // - return sb.toString();
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static String intToIPv4(int ip) {
        // [수정] 자바의 int는 부호가 있어 128.0.0.0 이상은 음수로 표현되므로 예외 검증을 제거했습니다.
        StringBuilder sb = new StringBuilder(15);

        sb //
                .append((ip >> 24) & 0xFF).append('.') //
                .append((ip >> 16) & 0xFF).append('.') //
                .append((ip >> 8) & 0xFF).append('.') //
                .append(ip & 0xFF);

        return sb.toString();
    }

    /**
     * IPv4 주소 형식 일치 여부를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 7. 12.     parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param ipAddr
     *            검증할 문자열 형태의 IP 주소 (Nullable)
     * 
     * @return IPv4 형식에 맞으면 {@code true}, 그렇지 않으면 {@code false}
     *
     * @since 2021. 7. 12.
     * @version 1.8.0
     * 
     * @see #REGEX_IPV4
     * @see #REGEX_IPV4_STRICT
     */
    public static boolean isIPv4(@Nullable String ipAddr) {
        return ipAddr == null ? false : ipAddr.matches(REGEX_IPV4_STRICT);
    }

    /**
     * IPv4 또는 IPv6 주소 형식 일치 여부를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 7. 12.     parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param ipAddr
     *            검증할 문자열 형태의 IP 주소 (Nullable)
     * 
     * @return IPv4 또는 IPv6 형식에 맞으면 {@code true}, 그렇지 않으면 {@code false}
     *
     * @since 2021. 7. 12.
     * @version 1.8.0
     * 
     * @see #REGEX_IPV4_IPV6
     * @see #REGEX_IPV4_IPV6_STRICT
     */
    public static boolean isIPv4OrIPv6(@Nullable String ipAddr) {
        return ipAddr == null ? false : ipAddr.matches(REGEX_IPV4_IPV6_STRICT);
    }

    /**
     * IPv6 주소 형식 일치 여부를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 7. 12.     parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param ipAddr
     *            검증할 문자열 형태의 IP 주소 (Nullable)
     * 
     * @return IPv6 형식에 맞으면 {@code true}, 그렇지 않으면 {@code false}
     *
     * @since 2021. 7. 12.
     * @version 1.8.0
     * 
     * @see #REGEX_IPV6
     * @see #REGEX_IPV6_STRICT
     */
    public static boolean isIPv6(@Nullable String ipAddr) {
        return ipAddr == null ? false : ipAddr.matches(REGEX_IPV6_STRICT);
    }

    /**
     * 주어진 MAC 주소 바이트 배열을 "00:11:22:33:44:55" 포맷의 읽기 쉬운 문자열로 변환하여 반환합니다.<br>
     * 
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2015. 3. 4.      parkjunohng77@gmail.com         최초 작성
     * 2026. 4. 1.      parkjunhong77@gmail.com         (3.0.0) 문자열 파싱 루프 및 메모리 최적화
     * </pre>
     *
     * @param mac
     *            변환할 대상의 MAC 주소 바이트 배열
     * 
     * @return 콜론(:)으로 구분된 MAC 주소 문자열
     * 
     * @throws NullPointerException
     *             파라미터({@code mac})가 {@code null}인 경우 발생.
     *
     * @since 2015. 3. 4.
     * @version 3.0.0
     */
    // 아래 내용에 적용됨.
    // - return sb.toString();
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static String toPrettyString(byte[] mac) {
        Objects.requireNonNull(mac, "A parameter 'mac' must not be 'null'.");
        
        String macStr = toString(mac);

        // [최적화] toCharArray 복사 제거 및 Capacity 명시를 통한 Resizing 방지
        StringBuilder sb = new StringBuilder(macStr.length() + (macStr.length() / 2));

        for (int i = 0; i < macStr.length(); i++) {
            if (i > 0 && i % 2 == 0) {
                sb.append(':');
            }
            sb.append(macStr.charAt(i));
        }

        return sb.toString();
    }

    /**
     * 주어진 MAC 주소 바이트 배열을 구분자 없는 16진수 문자열로 변환하여 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2015. 3. 4.      parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param mac
     *            변환할 대상의 MAC 주소 바이트 배열
     * 
     * @return 16진수로 변환된 문자열
     * 
     * @throws NullPointerException
     *             파라미터({@code mac})가 {@code null}인 경우 발생.
     *
     * @since 2015. 3. 4.
     * @version 1.8.0
     */
    public static String toString(byte[] mac) {
        return ByteUtils.hexBinString(mac);
    }

    /**
     * 주어진 문자열 IP 주소의 버전(유형)을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2023. 1. 5.      parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param ipAddr
     *            버전을 확인할 문자열 형태의 IP 주소 (Nullable)
     * @return "IPv4", "IPv6" 또는 포맷에 맞지 않을 경우 {@code null}
     *
     * @since 2023. 1. 5.
     * @version 2.0.0
     */
    public static @Nullable String typeOf(@Nullable String ipAddr) {
        return ipAddr == null ? null : isIPv4(ipAddr) ? "IPv4" : isIPv6(ipAddr) ? "IPv6" : null;
    }
}