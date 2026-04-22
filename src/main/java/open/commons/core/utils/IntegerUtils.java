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
*/
package open.commons.core.utils;

import java.util.Arrays;
import java.util.Objects;

import open.commons.core.lang.NumString;
import open.commons.core.utils.NumberUtils.IntegerType;

/**
 * int 및 {@link Integer}에 데이타 처리를 위한 메소드 제공 클래스
 *
 * @since 2012. 2. 8.
 */
public class IntegerUtils {

    private static final int BINARY_DIGIT_LENGTH = 32;
    private static final int OCTAL_DIGIT_LENGTH = 12;
    private static final int HEX_DIGIT_LENGTH = 8;

    /**
     * <p>
     * <font color="red">[JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 메소드 <br>
     * [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거' </font>
     * </p>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2026. 4. 7.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param value
     * @return
     *
     * @since 2026. 4. 7.
     * @version 3.0.0
     */
    // 아래 내용에 적용됨.
    // - Integer.toBinaryString(value)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    private static String _toBinaryString(int value) {
        return Integer.toBinaryString(value);
    }

    /**
     * <p>
     * <font color="red">[JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 메소드 <br>
     * [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거' </font>
     * </p>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2026. 4. 7.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param value
     * @return
     *
     * @since 2026. 4. 7.
     * @version 3.0.0
     */
    // 아래 내용에 적용됨.
    // - Integer.toHexString(value)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    private static String _toHexString(int value) {
        return Integer.toHexString(value);
    }

    /**
     * 
     * <p>
     * <font color="red">[JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 메소드 <br>
     * [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거' </font>
     * </p>
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2026. 4. 7.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param value
     * @return
     *
     * @since 2026. 4. 7.
     * @version 3.0.0
     */
    // 아래 내용에 적용됨.
    // - Integer.toOctalString(value)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    private static String _toOctalString(int value) {
        return Integer.toOctalString(value);
    }

    /**
     * 길이가 4의 배수인 byte 배열을 길이 4로 구분해서, 구분된 byte-4 배열을 int 타입의 값으로 변환한 후, 이 값들로 이루어진 int 배열을 반환합니다.
     *
     * <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 2. 9.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param values
     *
     * @return
     *
     * @since 2012. 2. 9.
     *
     * @see #byteArrayToInt(byte[])
     */
    // 아래 내용에 적용됨.
    // - values[i]
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static int[] byteArraysToIntArray(byte[]... values) {
        Objects.requireNonNull(values);

        if (values.length % 4 != 0) {
            throw new IllegalArgumentException("Input parameter's length should be 4 times");
        }

        int[] returnedValue = new int[values.length / 4];

        for (int i = 0; i < values.length; i++) {
            returnedValue[i] = byteArrayToInt(values[i]);

        }

        return returnedValue;
    }

    /**
     * 길이가 4인 byte 배열(byte-4 배열)을 int 타입의 값으로 변환한 후 반환합니다.
     *
     * <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 2. 8.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param value
     *
     * @return
     *
     * @since 2012. 2. 8.
     */
    public static int byteArrayToInt(byte[] value) {
        Objects.requireNonNull(value);

        if (value.length > 4) {
            throw new IllegalArgumentException("Input parameter's length should be less than 4 or equal.");
        }

        if (value.length < 4) {
            value = ArrayUtils.merge(new byte[4 - value.length], value);
        }

        int returnedValue = ((value[0] << 24) & 0xFF000000) | ((value[1] << 16) & 0x00FF0000)
                | ((value[2] << 8) & 0x0000FF00) | ((value[3]) & 0x000000FF);

        return returnedValue;
    }

    /**
     * 문자열 앞에 '0x'를 붙여 반환합니다. <br>
     *
     * <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2020. 12. 17.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param str
     *
     * @return
     *
     * @since 2020. 12. 17.
     *
     * @version 1.8.0
     */
    // 아래 내용에 적용됨.
    // - new StringBuilder().append("0x").append(str).toString()
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static String hex(String str) {
        return new StringBuilder().append("0x").append(str).toString();
    }

    /**
     * <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2014. 7. 10.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param value
     *
     * @return
     *
     * @throws NumberFormatException
     *
     * @since 2014. 7. 10.
     */
    public static int parseInt(String value) throws IndexOutOfBoundsException, NumberFormatException {
        Objects.requireNonNull(value);

        return parseInt(value, 0, value.length());
    }

    /**
     * <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2014. 7. 10.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param value
     * @param begin
     *            포함
     *
     * @return
     *
     * @since 2014. 7. 10. - 내부 구현 변경.
     *
     * @see String#substring(int)
     */
    public static int parseInt(String value, int begin) throws IndexOutOfBoundsException, NumberFormatException {
        Objects.requireNonNull(value);

        return parseInt(value, begin, value.length());
    }

    /**
     * <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2014. 7. 10.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param value
     * @param begin
     *            포함
     * @param end
     *            제외
     *
     * @return
     *
     * @since 2014. 7. 10. - 내부 구현 변경.
     *
     * @see String#substring(int, int)
     */
    // 아래 내용에 적용됨.
    // - buf.toString()
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static int parseInt(String value, int begin, int end)
            throws IndexOutOfBoundsException, NumberFormatException {
        Objects.requireNonNull(value);

        int radix = NumberUtils.radix(value);

        StringBuffer buf = new StringBuffer();

        switch (radix) {
            case 8:
                begin += 1;
                buf.append('0');
                break;
            case 16:
                begin += 2;
                buf.append("0x");
                break;
        }

        buf.append(value.substring(begin, end));

        return NumberUtils.toDecimal(buf.toString(), IntegerType.INTEGER).intValue();
    }

    /**
     * int 타입의 데이타를 32단위의 이진법 표기로 변환한 {@code <b>char</b>} 배열로 반환합니다.
     *
     * <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 2. 9.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param value
     *
     * @return
     *
     * @since 2012. 2. 9.
     */
    public static char[] toBinary32CharArray(int value) {
        return toCharArray(_toBinaryString(value), BINARY_DIGIT_LENGTH);
    }

    /**
     * int 타입의 데이타를 원하는 길이의 32단위의 이진법 표기로 변환한 {@code <b>char</b>} 배열로 반환합니다.<br>
     * 단, 반환되는 배열은 이진법 표기의 오른쪽값에서부터 채워진다.
     *
     * <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 02. 22.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param value
     * @param length
     *            결과 길이
     *
     * @return
     *
     * @since 2012. 02. 22.
     */
    public static char[] toBinary32CharArray(int value, int length) {
        return toCharArray(_toBinaryString(value), length);
    }

    /**
     * int 타입의 데이타를 32단위의 이진법 표기로 변환한 문자열로 반환합니다.
     *
     * <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 2. 9.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param value
     *
     * @return
     *
     * @since 2012. 2. 9.
     */
    // 아래 내용에 적용됨.
    // - String.valueOf(...)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static String toBinary32String(int value) {
        return String.valueOf(toCharArray(Integer.toBinaryString(value), BINARY_DIGIT_LENGTH));
    }

    /**
     * int 타입의 데이타를 원하는 길이의 32단위의 이진법 표기로 변환한 문자열로 반환합니다.<br>
     * 단, 반환되는 문자열은 이진법 표기의 오른쪽값에서부터 원하는 길이만큼 좌측으로 추출된 값이다.
     *
     * <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 02. 22.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param value
     * @param length
     *
     * @return
     *
     * @since 2012. 02. 22.
     */
    // 아래 내용에 적용됨.
    // - String.valueOf(...)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static String toBinary32String(int value, int length) {
        return String.valueOf(toCharArray(Integer.toBinaryString(value), length));
    }

    /**
     * int 타입의 값을 길이가 4인 byte 배열로 변환해서 반환합니다.
     *
     * <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 2. 9.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param value
     *
     * @return
     *
     * @since 2012. 2. 9.
     */
    public static byte[] toByteArray(int value) {
        byte[] returnedValue = new byte[4];

        returnedValue[0] = (byte) (value >>> 24);
        returnedValue[1] = (byte) (value >>> 16);
        returnedValue[2] = (byte) (value >>> 8);
        returnedValue[3] = (byte) value;

        return returnedValue;
    }

    /**
     * int 타입의 값으로 이루어진 배열의 각 데이타들을 길이가 4인 byte 배열로 변환한 후 반환합니다.
     *
     * <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 2. 9.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param values
     *
     * @return
     *
     * @since 2012. 2. 9.
     *
     * @see #toByteArray(int)
     */
    public static byte[] toByteArray(int... values) {
        Objects.requireNonNull(values);

        byte[] returnedValue = new byte[4 * values.length];

        for (int i = 0; i < values.length; i++) {
            System.arraycopy(toByteArray(values[i]), 0, returnedValue, 4 * i, 4);
        }

        return returnedValue;
    }

    /**
     * <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 2. 8.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param value
     * @param length
     *
     * @return
     *
     * @since 2012. 2. 8.
     */
    // 아래 내용에 적용됨.
    // - Arrays.copyOfRange(barr, 4 - length, 4)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static byte[] toByteArray(int value, int length) {
        if (length < 1 || length > 4) {
            new IllegalArgumentException("Length must be gt 0 & le 4. arg: " + length);
        }

        byte[] barr = new byte[4];

        barr[0] = (byte) (value >>> 24);
        barr[1] = (byte) (value >>> 16);
        barr[2] = (byte) (value >>> 8);
        barr[3] = (byte) value;

        return Arrays.copyOfRange(barr, 4 - length, 4);
    }

    /**
     * 정수형 데이타를 원하는 길이의 {@code <b>char</b>} 배열로 변환한 후 반환합니다.
     *
     * <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 02. 22.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param value
     *            문자열로 표현된 정수형 데이타
     * @param length
     *            표현하고자 하는 문자열 길이
     *
     * @return
     *
     * @since 2012. 02. 22.
     */
    public static char[] toCharArray(String value, int length) {
        Objects.requireNonNull(value);

        char[] str = new char[length];

        Arrays.fill(str, '0');

        char[] src = value.toCharArray();

        if (length >= value.length()) {
            System.arraycopy(src, 0, str, length - src.length, src.length);
        } else {
            System.arraycopy(src, src.length - length, str, 0, length);
        }

        return str;
    }

    /**
     * 정수형 데이타를 16진수형태의 8자리 {@code <b>char</b>} 배열로 반환합니다. 접두어인 '0x'는 포함하지 않는다.
     *
     * <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 2. 8.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param value
     *
     * @return
     *
     * @since 2012. 2. 8.
     */
    public static char[] toHexCharArray(int value) {
        return toCharArray(_toHexString(value), HEX_DIGIT_LENGTH);
    }

    /**
     * 정수형 데이타를 원하는 길이의 16진수형태의 8자리 {@code <b>char</b>} 배열로 반환합니다. 접두어인 '0x'는 포함하지 않는다.<br>
     * 단, 반환되는 배열은 16진수표기의 오른쪽값에서부터 채워진다.
     *
     * <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 2. 8.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param value
     *
     * @return
     *
     * @since 2012. 2. 8.
     */
    public static char[] toHexCharArray(int value, int length) {
        return toCharArray(_toHexString(value), length);
    }

    /**
     * 정수형 데이타를 16진수형태의 8자리 문자열로 반환합니다. 접두어인 '0x'는 포함하지 않는다.
     *
     * <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 2. 8.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param value
     *
     * @return
     *
     * @since 2012. 2. 8.
     */
    // 아래 내용에 적용됨.
    // - String.valueOf(...)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static String toHexString(int value) {
        return String.valueOf(toCharArray(Integer.toHexString(value), HEX_DIGIT_LENGTH));
    }

    /**
     * 정수형 데이타를 16진수형태의 문자열로 반환합니다. 접두어인 '0x'는 포함하지 않는다.
     *
     * <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 2. 8.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param value
     * @param padding
     *            0(zero) trim 처리 여부
     *
     * @return
     *
     * @since 2012. 2. 8.
     */
    public static String toHexString(int value, boolean padding) {
        String hexValue = toHexString(value);

        if (padding) {
            String tmp = StringUtils.ltrimSpecific(hexValue, '0');
            if (tmp.length() > 0) {
                hexValue = tmp;
            } else {
                hexValue = "0";
            }
        }

        return hexValue;
    }

    /**
     * 정수형 데이타를 원하는 길이의 16진수형태의 8자리 문자열로 반환합니다. 접두어인 '0x'는 포함하지 않는다.<br>
     * 단, 반환되는 문자열은 16진수표기의 오른쪽값에서부터 원하는 길이만큼 좌측으로 추출된 값이다.
     *
     * <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 2. 8.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param value
     *
     * @return
     *
     * @since 2012. 2. 8.
     */
    // 아래 내용에 적용됨.
    // - String.valueOf(...)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static String toHexString(int value, int length) {
        return String.valueOf(toCharArray(Integer.toHexString(value), length));
    }

    /**
     * 정수형 데이타를 16진수형태의 8자리 문자열로 반환합니다. 접두어인 '0x'는 포함하지 않는다.
     *
     * <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 2. 8.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param value
     *
     * @return
     *
     * @since 2012. 2. 8.
     */
    public static String toHexString(String value) {
        Objects.requireNonNull(value);

        return toHexString(Integer.parseInt(value));
    }

    /**
     * 정수형 데이타를 16진수형태의 문자열로 반환합니다. 접두어인 '0x'는 포함하지 않는다.
     *
     * <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 2. 8.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param value
     * @param padding
     *            0(zero) trim 처리 여부
     *
     * @return
     *
     * @since 2012. 2. 8.
     */
    public static String toHexString(String value, boolean padding) {
        Objects.requireNonNull(value);

        return toHexString(Integer.parseInt(value), padding);
    }

    /**
     * 정수형 데이타를 원하는 길이의 16진수형태의 8자리 문자열로 반환합니다. 접두어인 '0x'는 포함하지 않는다.<br>
     * 단, 반환되는 문자열은 16진수표기의 오른쪽값에서부터 원하는 길이만큼 좌측으로 추출된 값이다.
     *
     * <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 2. 8.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param value
     *
     * @return
     *
     * @since 2012. 2. 8.
     */
    public static String toHexString(String value, int length) {
        Objects.requireNonNull(value);

        return toHexString(Integer.parseInt(value), length);
    }

    /**
     * int 값을 IPV4 주소 형태로 변환하여 제공합니다. <br>
     *
     * <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2020. 12. 17.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param nums
     *
     * @return
     *
     * @since 2020. 12. 17.
     *
     * @version 1.8.0
     */
    // 아래 내용에 적용됨.
    // - String.join(...)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static String toIPv4(int nums) {
        int[] ar = new int[4];
        ar[3] = nums & 0xFF;
        ar[2] = nums & 0xFF00;
        ar[1] = nums & 0xFF0000;
        ar[0] = nums & 0xFF000000;
        return String.join(".", NumString.sequence(ArrayUtils.toWrapperArray(ar)));
    }

    /**
     * int 배열을 IPV4 주소 형태로 변환하여 제공합니다. <br>
     *
     * <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2020. 12. 17.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param nums
     *
     * @return
     *
     * @since 2020. 12. 17.
     *
     * @version 1.8.0
     */
    // 아래 내용에 적용됨.
    // - String.join(...)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static String toIPv4(int[] nums) {
        Objects.requireNonNull(nums);

        int[] ar = new int[4];
        System.arraycopy(nums, 0, ar, 0, Math.min(nums.length, ar.length));
        return String.join(".", NumString.sequence(ArrayUtils.toWrapperArray(ar)));
    }

    /**
     * 정수형 데이타를 8진수 형태의 {@code <b>char</b>} 배열로 반환합니다.
     *
     * <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 02. 22.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param value
     *
     * @return
     *
     * @since 2012. 02. 22.
     */
    public static char[] toOctalCharArray(int value) {
        return toCharArray(_toOctalString(value), OCTAL_DIGIT_LENGTH);
    }

    /**
     * 정수형 데이타를 원하는 길이의 8진수 형태의 {@code <b>char</b>} 배열로 반환합니다.<br>
     * 단, 반환되는 배열은 8진수표기에서 오른쪽값에서부터 원하는 길이만큼 좌측으로 추출된 값이다.
     *
     * <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 02. 22.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param value
     *
     * @return
     *
     * @since 2012. 02. 22.
     */
    public static char[] toOctalCharArray(int value, int length) {
        return toCharArray(_toOctalString(value), length);
    }

    /**
     * 정수형 데이타를 8진수 형태의 문자열로 반환합니다.
     *
     * <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 02. 22.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param value
     *
     * @return
     *
     * @since 2012. 02. 22.
     */
    // 아래 내용에 적용됨.
    // - String.valueOf(...)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static String toOctalString(int value) {
        return String.valueOf(toCharArray(Integer.toOctalString(value), OCTAL_DIGIT_LENGTH));
    }

    /**
     * 정수형 데이타를 8진수 형태의 문자열로 반환합니다.
     *
     * <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 02. 22.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param value
     * @param padding
     *            0(zero) trim 처리 여부
     *
     * @return
     *
     * @since 2012. 02. 22.
     */
    public static String toOctalString(int value, boolean padding) {
        String octValue = toOctalString(value);

        if (padding) {
            String tmp = StringUtils.ltrimSpecific(octValue, '0');
            if (tmp.length() > 0) {
                octValue = tmp;
            } else {
                octValue = "0";
            }
        }

        return octValue;
    }

    /**
     * 정수형 데이타를 원하는 길이의 8진수 형태의 문자열로 반환합니다.<br>
     * 단, 반환되는 문자열은 8진수표기에서 오른쪽값에서부터 원하는 길이만큼 좌측으로 추출된 값이다.
     *
     * <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 02. 22.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param value
     *
     * @return
     *
     * @since 2012. 02. 22.
     */
    // 아래 내용에 적용됨.
    // - String.valueOf(...)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static String toOctalString(int value, int length) {
        return String.valueOf(toCharArray(Integer.toOctalString(value), length));
    }

    /**
     * 정수형 데이타를 8진수 형태의 문자열로 반환합니다.
     *
     * <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 02. 22.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param value
     *
     * @return
     *
     * @since 2012. 02. 22.
     */
    public static String toOctalString(String value) {
        Objects.requireNonNull(value);

        return toOctalString(Integer.parseInt(value));
    }

    /**
     * 정수형 데이타를 8진수 형태의 문자열로 반환합니다.
     *
     * <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 02. 22.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param value
     * @param padding
     *            0(zero) trim 처리 여부
     *
     * @return
     *
     * @since 2012. 02. 22.
     */
    public static String toOctalString(String value, boolean padding) {
        Objects.requireNonNull(value);

        return toOctalString(Integer.parseInt(value), padding);
    }

    /**
     * 정수형 데이타를 원하는 길이의 8진수 형태의 문자열로 반환합니다.<br>
     * 단, 반환되는 문자열은 8진수표기에서 오른쪽값에서부터 원하는 길이만큼 좌측으로 추출된 값이다.
     *
     * <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 02. 22.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param value
     *
     * @return
     *
     * @since 2012. 02. 22.
     */
    public static String toOctalString(String value, int length) {
        Objects.requireNonNull(value);

        return toOctalString(Integer.parseInt(value), length);
    }

    /**
     * int 타입의 데이타를 보기 좋은 32단위의 이진법 표기로 변환한 문자열로 반환합니다.
     *
     * <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 2. 9.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param value
     *
     * @return
     *
     * @since 2012. 2. 9.
     */
    // 아래 내용에 적용됨.
    // - StringBuilder.toString()()
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static String toWellFormed32bitBinaryString(int value) {
        char[] str = new char[32];

        Arrays.fill(str, '0');

        char[] src = Integer.toBinaryString(value).toCharArray();
        System.arraycopy(src, 0, str, 32 - src.length, src.length);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 32; i++) {
            // split for 8-bit
            if (i < 24 && (i + 1) % 8 == 0) {
                sb.append(str[i] + " | ");
            } else
            // split for 2-bit
            if ((i + 1) % 2 == 0) {
                sb.append(str[i] + " ");
            } else {
                sb.append(str[i]);
            }
        }

        return sb.toString();
    }
}
