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
* This file is generated under this project, "open-commons-core".
*
* Date  : 2014. 7. 10. 오후 12:13:59
*
* Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
* 
*/

package open.commons.core.utils;

import java.util.Objects;
import java.util.function.Function;

/**
 * 숫자 변환 및 진법 처리를 지원하는 유틸리티 클래스입니다.
 *
 * @since 2014. 7. 10.
 */
public class NumberUtils {

    public static final Function<Integer, String> INT_TO_STR = i -> String.format("%,d", i);
    public static final Function<Long, String> LONG_TO_STR = i -> String.format("%,d", i);

    // Prevent to create a new instance.
    private NumberUtils() {
    }

    /**
     * 문자열 앞에 '0x'를 붙여 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 12. 17.    parkjunhong77@gmail.com         최초 작성
     * 2026. 4. 1.      parkjunhong77@gmail.com         (개선) 불필요한 concat 래핑 제거
     * </pre>
     *
     * @param str
     *            16진수 접두사를 붙일 원본 문자열
     *
     * @return '0x'가 추가된 문자열
     *
     * @since 2020. 12. 17.
     * @version 1.8.0
     */
    public static String hex(String str) {
        Objects.requireNonNull(str);

        return "0x" + str;
    }

    /**
     * 문자열({@code value})의 형태를 분석하여 진법(Radix)을 제공합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2014. 7. 10.     parkjunhong77@gmail.com     최초 작성
     * 2026. 4. 1.      parkjunhong77@gmail.com         (개선) 빈 문자열 및 부호(+, -) 처리 로직 보강
     * </pre>
     *
     * @param value
     *            진법을 판별할 숫자 문자열
     *
     * @return 16진수(16), 8진수(8), 또는 기본 10진수(10)
     *
     * @since 2014. 7. 10.
     */
    public static int radix(String value) {
        Objects.requireNonNull(value);

        if (value.isEmpty()) {
            return 10;
        }

        // 부호(+, -)가 포함된 경우 탐색 인덱스 조정
        int start = 0;
        if (value.charAt(0) == '-' || value.charAt(0) == '+') {
            start = 1;
        }

        if (value.length() <= start) {
            return 10;
        }

        char token = value.charAt(start);

        // detect 0(zero).
        if (token == '0') {
            // real Zero value.
            if (value.length() <= start + 1) {
                return 10;
            }

            token = value.charAt(start + 1);
            // detect 'Hexa'.
            if (token == 'X' || token == 'x') {
                return 0x10;
            }

            // decide 'Octal'.
            return 010;
        }

        return 10;
    }

    /**
     * 문자열을 10진수 숫자 객체({@link Number})로 변환합니다. 기본적으로 {@link IntegerType#INTEGER}로 처리됩니다.
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2014. 7. 10.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param value
     *            변환할 숫자 문자열
     *
     * @return 변환된 숫자 객체
     * @throws NumberFormatException
     *             파라미터({@code value})가 숫자 형식이 아닌 경우 발생.
     *
     * @since 2014. 7. 10.
     *
     * @see #toDecimal(String, IntegerType)
     */
    public static Number toDecimal(String value) throws NumberFormatException {
        return toDecimal(value, IntegerType.INTEGER);
    }

    /**
     * 문자열을 지정된 타입({@link IntegerType})의 숫자 객체로 변환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2014. 7. 10.     parkjunhong77@gmail.com         최초 작성
     * 2026. 4. 1.      parkjunhong77@gmail.com         (개선) replaceFirst로 교체 및 switch 다중 라벨 최적화
     * </pre>
     *
     * @param value
     *            변환할 숫자 문자열
     * @param type
     *            반환될 숫자의 래퍼/원시 타입 명세
     *
     * @return 지정된 타입으로 변환된 숫자 객체
     * @throws NumberFormatException
     *             파라미터({@code value})가 숫자 형식이 아닌 경우 발생.
     *
     * @since 2014. 7. 10.
     */
    // 아래 내용에 적용됨.
    // - value.replaceFirst("(?i)0x", "")
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static Number toDecimal(String value, IntegerType type) throws NumberFormatException {
        AssertUtils2.notNulls(value, type);

        int radix = radix(value);
        // 접두사만 제거하고 부호/데이터 손실 방지
        value = value.replaceFirst("(?i)0x", "");

        return switch (type) {
            case BYTE, Byte -> java.lang.Byte.parseByte(value, radix);
            case SHORT, Short -> java.lang.Short.parseShort(value, radix);
            case INTEGER, Int -> java.lang.Integer.parseInt(value, radix);
            case LONG, Long -> java.lang.Long.parseLong(value, radix);
        };
    }

    public static enum IntegerType {
        // start - Wrapper Classes : 2014. 7. 10.,
        // Park_Jun_Hong_(parkjunhong77@gmail.com)
        /** The type is {@code Byte.class} */
        BYTE(Byte.class),
        /** The type is {@code Short.class} */
        SHORT(Short.class),
        /** The type is {@code Integer.class} */
        INTEGER(Integer.class),
        /** The type is {@code Long.class} */
        LONG(Long.class),
        // end - Wrapper Classes : 2014. 7. 10.

        // start - primitive Classes : 2014. 7. 10.,
        // Park_Jun_Hong_(parkjunhong77@gmail.com)
        /** The type is {@code byte.class} */
        Byte(byte.class),
        /** The type is {@code short.class} */
        Short(short.class),
        /** The type is {@code int.class} */
        Int(int.class),
        /** The type is {@code long.class} */
        Long(long.class);
        // end - primitive Classes : 2014. 7. 10.

        private Class<?> type;

        private IntegerType(Class<?> type) {
            this.type = type;
        }

        public Class<?> getNumberClass() {
            return type;
        }
    }
}