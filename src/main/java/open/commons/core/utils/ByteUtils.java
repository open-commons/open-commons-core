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
* Date  : 2013. 2. 22. 오전 11:43:07
*
* Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
* 
*/
package open.commons.core.utils;

import java.nio.ByteBuffer;
import java.util.Objects;

import org.jspecify.annotations.Nullable;

public class ByteUtils {

    private static final int H1 = 0x10;

    private static final int H2 = 0x20;
    private static final int H4 = 0x40;
    private static final int H8 = 0x80;
    private static final int T1 = 0x01;

    private static final int T2 = 0x02;
    private static final int T4 = 0x04;
    private static final int T8 = 0x08;

    @SuppressWarnings("null")
    private static final char[] HEX_CODE = "0123456789ABCDEF".toCharArray();

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 12. 17.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     *
     * @param split
     *            byte 단위의 Hex 표기값을 띄워서 표시할지 여부
     * @param data
     * @return
     *
     * @since 2020. 12. 17.
     * @version 1.8.0
     * 
     * @throws NullPointerException
     *             파라미터({@code data})가 {@code null}인 경우 발생.
     */
    public static String hexBinString(boolean split, byte... data) {
        return hexBinString("", split, data);
    }

    /**
     * byte 배열을 Hexa 문자열로 반환합니다.
     * 
     * @param data
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code data})가 {@code null}인 경우 발생.
     * 
     */
    public static String hexBinString(byte... data) {
        return hexBinString("", false, data);
    }

    /**
     * 바이트 데이터를 16진수(Hex) 문자열로 변환하여 반환합니다.
     * 
     * @param prefix
     *            Hex 문자열 앞에 붙일 접두어 (예: 0x)
     * @param split
     *            바이트 단위 사이에 공백을 추가할지 여부
     * @param data
     *            변환할 바이트 데이터 (가변 인자)
     * @return 16진수로 변환된 문자열. {@code data}가 {@code null}인 경우 {@code null}을 반환합니다.
     * 
     * @throws NullPointerException
     *             파라미터({@code data})가 {@code null}인 경우 발생.
     * 
     * @since 2020. 12. 17.
     */
    public static String hexBinString(@Nullable String prefix, boolean split, byte... data) {
        return hexBinString(prefix, split ? " " : null, data);
    }

    /**
     * byte 배열을 Hex 문자열로 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 12. 17.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     *
     * @param prefix
     *            Hex 문자열 앞에 붙힐 접두어.
     * @param data
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code data})가 {@code null}인 경우 발생.
     *
     * @since 2020. 12. 17.
     * @version 1.8.0
     */
    public static String hexBinString(String prefix, byte... data) {
        return hexBinString(prefix, false, data);
    }

    /**
     * 바이트 데이터를 지정된 구분자({@code separator})를 사용하여 16진수(Hex) 문자열로 변환하여 반환합니다.
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2026. 03. 26.    parkjunhong77@gmail.com     구분자(Separator) 커스텀 기능 추가 및 로직 통합
     * </pre>
     * 
     * @param prefix
     *            Hex 문자열 앞에 붙일 접두어
     * @param separator
     *            바이트 단위 사이의 구분자 ({@code null}인 경우 구분자 없이 연결)
     * @param data
     *            변환할 바이트 데이터
     * @return 16진수로 변환된 문자열
     * 
     * @throws NullPointerException
     *             {@code data}가 {@code null}인 경우 발생 (내부 엔진용)
     * 
     * @since 2026. 03. 26.
     */
    // 아래 내용에 적용됨.
    // - return sb.toString();
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static String hexBinString(@Nullable String prefix, @Nullable String separator, byte... data) {
        Objects.requireNonNull(data);

        if (data.length == 0) {
            return prefix != null ? prefix : "";
        }

        int prefixLen = (prefix != null) ? prefix.length() : 0;
        int sepLen = (separator != null) ? separator.length() : 0;

        // 용량 계산: prefix + (데이터*2) + (구분자길이 * (데이터-1))
        int capacity = prefixLen + (data.length * 2) + (sepLen * (data.length - 1));

        StringBuilder sb = new StringBuilder(capacity);
        if (prefix != null) {
            sb.append(prefix);
        }

        for (int i = 0; i < data.length; i++) {
            // 구분자 처리: i > 0 이고 separator가 null이 아닐 때만 append
            if (i > 0 && separator != null) {
                sb.append(separator);
            }

            sb.append(HEX_CODE[(data[i] >> 4) & 0xF]);
            sb.append(HEX_CODE[data[i] & 0xF]);
        }

        return sb.toString();
    }

    /**
     * 
     * @param hexBinString
     * @return
     * 
     * 
     * @since 2014. 4. 25.: Remove colon(:)s and white-spaces in input string.
     */
    public static byte @Nullable [] hexBinStringToByteArray(@Nullable String hexBinString) {
        if (hexBinString == null) {
            return null;
        }

        String trimedHexBinString = hexBinString.replaceAll(":", "").replaceAll("\\s", "");

        if (trimedHexBinString.length() % 2 != 0) {
            throw new IllegalArgumentException("입력된 문자열 길이는 짝수(even)이어야 합니다. 길이: " + trimedHexBinString.length() + ", 입력값: " + hexBinString);
        }

        char[] cs = trimedHexBinString.toCharArray();

        byte[] bytes = new byte[cs.length / 2];

        for (int i = 0; i < cs.length - 1; i += 2) {

            int h = hexToBin(cs[i]);
            int l = hexToBin(cs[i + 1]);
            if (h == -1 || l == -1) {
                throw new IllegalArgumentException("contains illegal character for hexBinary: [" + cs[i] + ", " + cs[i + 1] + "]");
            }

            bytes[i / 2] = (byte) (h * 16 + l);
        }

        return bytes;

    }

    private static int hexToBin(char ch) {
        if ('0' <= ch && ch <= '9') {
            return ch - '0';
        }
        if ('A' <= ch && ch <= 'F') {
            return ch - 'A' + 10;
        }
        if ('a' <= ch && ch <= 'f') {
            return ch - 'a' + 10;
        }
        return -1;
    }

    /**
     * byte[]를 {@link ByteBuffer}로 변환해서 반환합니다.
     * 
     * @param bytes
     * @return <b>{@code flip mode}</b> instance.
     * 
     * @throws NullPointerException
     *             파라미터({@code bytes})가 {@code null}인 경우 발생.
     *
     * @since 2015. 12. 10.
     */
    public static ByteBuffer toByteBuffer(byte[] bytes) {
        Objects.requireNonNull(bytes);

        ByteBuffer buf = ByteBuffer.allocateDirect(bytes.length);
        buf.put(bytes);
        buf.flip();

        return buf;
    }

    /**
     * byte[]을 char[]로 변환해서 반환합니다.
     * 
     * @param bytes
     * @return
     * 
     * @see NullPointerException
     */
    public static char @Nullable [] toChars(byte @Nullable [] bytes) {
        if (bytes == null) {
            return null;
        }

        char[] rtnChars = new char[bytes.length];

        for (int i = 0; i < bytes.length; i++) {
            rtnChars[i] = (char) bytes[i];
        }

        return rtnChars;
    }

    /**
     * byte 값을 int 로 변환해 반환합니다.
     * 
     * @param b
     * @return
     */
    public static int toInt(byte b) {
        return b & 0xFF;
    }

    /**
     * 길이가 4인 byte 배열(byte-4 배열)을 int 타입의 값으로 변환한 후 반환합니다.
     * 
     * @param value
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code value})가 {@code null}인 경우 발생.
     */
    public static int toInt(byte[] value) {
        Objects.requireNonNull(value);

        if (value.length > 4) {
            throw new IllegalArgumentException("Input parameter's length should be less than 4 or equal.");
        }

        if (value.length < 4) {
            value = ArrayUtils.merge(new byte[4 - value.length], value);
        }

        int returnedValue = ((value[0] << 24) & 0xFF000000) | ((value[1] << 16) & 0x00FF0000) | ((value[2] << 8) & 0x0000FF00) | ((value[3]) & 0x000000FF);

        return returnedValue;
    }

    /**
     * byte 배열을 IPv4 주소 형태({@code 0.0.0.0})의 문자열로 변환하여 반환합니다. *
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2020. 12. 17.    parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.    parkjunhong77@gmail.com     StringBuilder 및 비트 연산을 통한 성능 최적화
     * </pre>
     * 
     * @param bytes
     *            IPv4로 변환할 바이트 배열 (최소 4바이트 권장)
     * 
     * @return IPv4 주소 문자열
     * 
     * @throws NullPointerException
     *             파라미터({@code bytes})가 {@code null}인 경우 발생.
     * @since 2020. 12. 17.
     */
    // 아래 내용에 적용됨.
    // - return sb.toString();
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static String toIPv4Expr(byte[] bytes) {
        // [1] 가드 클로즈: null 체크
        Objects.requireNonNull(bytes, "A parameter(byte[] bytes) must not be 'null'.");

        // [2] 성능 최적화: 객체 생성을 최소화하기 위해 StringBuilder 사용
        // IPv4 최대 길이: "255.255.255.255" (15자)
        StringBuilder sb = new StringBuilder(15);

        // IPv4는 4개의 옥텟(Octet)으로 구성됨
        int loopCount = Math.min(bytes.length, 4);

        for (int i = 0; i < loopCount; i++) {
            if (i > 0) {
                sb.append('.');
            }
            // [3] 비트 연산 필수: byte(-128~127)를 Unsigned(0~255) 정수로 변환
            sb.append(bytes[i] & 0xFF);
        }

        // 만약 bytes.length가 4보다 작을 경우, 나머지 부분을 0으로 채울지 여부는 정책에 따라 결정
        // 여기서는 기존 로직의 Math.min 정책을 유지하되, 부족한 부분을 0으로 채워 완성도를 높임
        for (int i = loopCount; i < 4; i++) {
            if (i > 0) {
                sb.append('.');
            }
            sb.append('0');
        }

        return sb.toString();
    }

    /**
     * 바이트 배열을 MAC 주소 형태({@code XX:XX:XX:XX:XX:XX})의 문자열로 변환하여 반환합니다.
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2020. 12. 17.    parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.    parkjunhong77@gmail.com     hexBinString 재사용 및 구분자 치환 로직 적용
     * </pre>
     * 
     * @param bytes
     *            MAC 주소로 변환할 바이트 배열
     * @return MAC 주소 문자열
     * 
     * @throws NullPointerException
     *             파라미터({@code bytes})가 {@code null}인 경우 발생.
     * 
     * @since 2020. 12. 17.
     */
    public static String toMACExpr(byte[] bytes) {
        // [1] 가드 클로즈: null 체크
        Objects.requireNonNull(bytes, "A parameter(byte[] bytes) must not be 'null'.");

        if (bytes.length == 0) {
            return "";
        }

        // [2] 재사용: hexBinString을 호출하여 콜론(:)으로 구분된 Hex 문자열 획득
        return hexBinString(null, ":", bytes);
    }

    /**
     * 
     * byte 내부의 bit 값 위치를 뒤집는다.<br>
     * 
     * <ul>
     * <li>1 <-> 8
     * <li>2 <-> 7
     * <li>3 <-> 6
     * <li>4 <-> 5
     * </ul>
     * 
     * @param b
     * @return
     */
    public static byte upset(byte b) {
        byte v = 0x00;

        v |= (byte) ((b & T1) << 7);
        v |= (byte) ((b & T2) << 5);
        v |= (byte) ((b & T4) << 3);
        v |= (byte) ((b & T8) << 1);

        v |= (byte) ((b & H1) >> 1);
        v |= (byte) ((b & H2) >> 3);
        v |= (byte) ((b & H4) >> 5);
        v |= (byte) ((b & H8) >> 7);

        return v;
    }

    /**
     * 주어진 배열에 포함된 byte 내부의 bit 값 위치를 뒤집은 후 새로운 배열로 반환합니다.<br>
     * 
     * <ul>
     * <li>1 <-> 8
     * <li>2 <-> 7
     * <li>3 <-> 6
     * <li>4 <-> 5
     * </ul>
     * 
     * @param bytes
     * @return
     * 
     * @see #upsetArray(byte[])
     */
    public static byte @Nullable [] upset(byte @Nullable [] bytes) {
        if (bytes == null) {
            return null;
        }

        byte[] upsets = new byte[bytes.length];

        for (int i = 0; i < bytes.length; i++) {
            upsets[i] = upset(bytes[i]);
        }

        return upsets;
    }

    /**
     * 주어진 배열에 포함된 byte 내부의 bit 값 위치를 뒤집는다.<br>
     * 
     * <ul>
     * <li>1 <-> 8
     * <li>2 <-> 7
     * <li>3 <-> 6
     * <li>4 <-> 5
     * </ul>
     * 
     * @param bytes
     *            <b>{@code not nullable.}</b>
     * 
     * @see #upset(byte[])
     */
    public static void upsetArray(byte[] bytes) {
        Objects.requireNonNull(bytes);

        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = upset(bytes[i]);
        }
    }

    /**
     * 두 개의 배열에서 동일한 인덱스에 존재하는 데이터의 XOR(Exclusive OR)값을 하나의 배열로 반환합니다.
     * 
     * @param b1
     * @param b2
     * @return
     */
    public static byte[] xor(byte[] b1, byte[] b2) {
        byte[] rtnBytes = new byte[b1.length];

        for (int i = 0; i < b1.length; i++) {
            rtnBytes[i] = (byte) (b1[i] ^ b2[i]);
        }
        return rtnBytes;
    }
}
