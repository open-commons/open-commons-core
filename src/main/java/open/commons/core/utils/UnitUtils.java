/*
 * Copyright 2021 Park Jun-Hong (parkjunhong77@gmail.com)
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
 * Date  : 2021. 11. 4. 오후 1:23:48
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.core.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import open.commons.core.util.BinaryDataUnit;
import open.commons.core.util.PrefixDataUnit;

/**
 * 데이터 용량 단위 변환 및 포맷팅 기능을 제공하는 유틸리티 클래스입니다.
 *
 * @since 2021. 11. 4.
 * @version 1.8.0
 */
public class UnitUtils {

    // prevent to create an instance.
    private UnitUtils() {
    }

    /**
     * 천 단위마다 콤마(,)를 추가합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2026. 4. 2.      parkjunhong77@gmail.com         (개선) Function 객체에서 정적 메소드로 전환
     * </pre>
     *
     * @param val
     *            숫자 문자열
     *
     * @return 천 단위마다 콤마(,)가 추가된 문자열
     */
    // 아래 내용에 적용됨.
    // - String.replaceAll(...)
    // - String.join(...)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    private static String addComma(String val) {
        String[] arr = val.split("[.]");
        if (arr.length == 1) {
            return val.replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
        } else {
            return String.join(".", arr[0].replaceAll("\\B(?=(\\d{3})+(?!\\d))", ","), arr[1]);
        }
    }

    /**
     * 데이터 크기를 주어진 단위에 맞게 변환하여 제공합니다. (하위 단위 미포함)
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 4.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param bytes
     *            데이터 크기 (단위: byte)
     * @param unit
     *            변환 단위
     *
     * @return 변환된 크기의 {@link BigDecimal} 객체
     *
     * @since 2021. 11. 4.
     * @version 1.8.0
     *
     * @see BinaryDataUnit#convert(long, BinaryDataUnit)
     */
    public static BigDecimal binaryStorage(long bytes, BinaryDataUnit unit) {
        Objects.requireNonNull(unit);
        return BinaryDataUnit.BYTE.convert(bytes, unit);
    }

    /**
     * 데이터 크기를 주어진 단위에 맞게 변환하여 제공합니다. (하위 단위 포함)
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 4.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param bytes
     *            데이터 크기 (단위: byte)
     * @param unit
     *            변환 단위
     *
     * @return 하위 단위별로 분할된 {@link BigDecimal} 배열
     *
     * @since 2021. 11. 4.
     * @version 1.8.0
     *
     * @see BinaryDataUnit#convert(long, BinaryDataUnit, boolean)
     */
    public static BigDecimal[] binaryStorageAlsoSubUnit(long bytes, BinaryDataUnit unit) {
        Objects.requireNonNull(unit);
        return BinaryDataUnit.BYTE.convert(bytes, unit, true);
    }

    /**
     * 데이터 크기를 주어진 단위에 맞게 변환하여 문자열로 제공합니다. (하위 단위 포함, 천 단위 콤마 추가, 0인 단위 제외)
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 4.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param bytes
     *            데이터 크기 (단위: byte)
     * @param unit
     *            변환 단위
     *
     * @return 포맷팅된 용량 문자열
     *
     * @since 2021. 11. 4.
     * @version 1.8.0
     *
     * @see #binaryStorageAlsoSubUnitAsString(long, BinaryDataUnit, boolean, boolean)
     */
    public static String binaryStorageAlsoSubUnitAsString(long bytes, BinaryDataUnit unit) {
        return binaryStorageAlsoSubUnitAsString(bytes, unit, true, true);
    }

    /**
     * 데이터 크기를 주어진 시작 단위부터 목표 단위에 맞게 변환하여 문자열로 제공합니다. (하위 단위 포함)
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 4.     parkjunhong77@gmail.com         최초 작성
     * 2026. 4. 2.      parkjunhong77@gmail.com         (개선) ADD_COMMA 함수 호출 변경
     * </pre>
     *
     * @param size
     *            데이터 크기
     * @param srcUnit
     *            변환할 원본 데이터 단위
     * @param dstUnit
     *            변환할 목표 단위
     * @param pretty
     *            천 단위 콤마(,) 추가 여부
     * @param trim
     *            사이즈가 '0'인 하위 단위의 문자열 제외 여부
     *
     * @return 포맷팅된 용량 문자열
     *
     * @since 2021. 11. 4.
     * @version 1.8.0
     *
     * @see BinaryDataUnit#convert(long, BinaryDataUnit, boolean)
     */
    // 아래 내용에 적용됨.
    // - BigDecimal.toString()
    // - String.join(...)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static String binaryStorageAlsoSubUnitAsString(long size, BinaryDataUnit srcUnit, BinaryDataUnit dstUnit,
            boolean pretty, boolean trim) {
        Objects.requireNonNull(srcUnit);
        Objects.requireNonNull(dstUnit);

        BigDecimal[] values = srcUnit.convert(size, dstUnit, true);
        List<String> s = new ArrayList<>();

        BinaryDataUnit u = dstUnit;
        String val = null;
        for (int i = 0; i < values.length; i++) {
            if (!trim || values[i].compareTo(BigDecimal.ZERO) != 0) {
                val = values[i].toString();
                if (pretty) {
                    val = addComma(val);
                }
                s.add(concat(val, " ", u.get()));
            }
            u = u.down();
        }

        return String.join(" ", s);
    }

    /**
     * 데이터 크기를 주어진 단위에 맞게 변환하여 문자열로 제공합니다. (하위 단위 포함)
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 4.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param bytes
     *            데이터 크기 (단위: byte)
     * @param unit
     *            변환 단위
     * @param pretty
     *            천 단위 콤마(,) 추가 여부
     * @param trim
     *            사이즈가 '0'인 단위 제외 여부
     *
     * @return 포맷팅된 용량 문자열
     *
     * @since 2021. 11. 4.
     * @version 1.8.0
     *
     * @see BinaryDataUnit#convert(long, BinaryDataUnit, boolean)
     */
    public static String binaryStorageAlsoSubUnitAsString(long bytes, BinaryDataUnit unit, boolean pretty,
            boolean trim) {
        Objects.requireNonNull(unit);
        return binaryStorageAlsoSubUnitAsString(bytes, BinaryDataUnit.BYTE, unit, pretty, trim);
    }

    /**
     * 데이터 크기를 주어진 단위에 맞게 변환하여 문자열로 제공합니다. (하위 단위 미포함, 콤마 및 단위 문자열 포함)
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 4.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param bytes
     *            데이터 크기 (단위: byte)
     * @param unit
     *            변환 단위
     *
     * @return 포맷팅된 용량 문자열
     *
     * @since 2021. 11. 4.
     * @version 1.8.0
     *
     * @see BinaryDataUnit#convert(long, BinaryDataUnit)
     */
    public static String binaryStorageAsString(long bytes, BinaryDataUnit unit) {
        Objects.requireNonNull(unit);
        return binaryStorageAsString(bytes, BinaryDataUnit.BYTE, unit, true, true);
    }

    /**
     * 데이터 크기를 지정된 시작 단위부터 목표 단위에 맞게 변환하여 문자열로 제공합니다. (하위 단위 미포함)
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 4.     parkjunhong77@gmail.com         최초 작성
     * 2026. 4. 2.      parkjunhong77@gmail.com         (개선) ADD_COMMA 함수 호출 변경
     * </pre>
     *
     * @param size
     *            데이터 크기
     * @param srcUnit
     *            변환할 원본 데이터 단위
     * @param dstUnit
     *            변환할 목표 단위
     * @param pretty
     *            천 단위 콤마(,) 추가 여부
     * @param attachUnitStr
     *            단위 문자열(예: KiB) 추가 여부
     *
     * @return 포맷팅된 용량 문자열
     *
     * @since 2021. 11. 4.
     * @version 1.8.0
     *
     * @see BinaryDataUnit#convert(long, BinaryDataUnit)
     */
    // 아래 내용에 적용됨.
    // - BigDecimal.toString()
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static String binaryStorageAsString(long size, BinaryDataUnit srcUnit, BinaryDataUnit dstUnit,
            boolean pretty, boolean attachUnitStr) {
        Objects.requireNonNull(srcUnit);
        Objects.requireNonNull(dstUnit);

        String val = srcUnit.convert(size, dstUnit).toString();
        if (pretty) {
            val = addComma(val);
        }

        return attachUnitStr ? concat(val, " ", dstUnit.get()) : val;
    }

    /**
     * 데이터 크기를 주어진 단위에 맞게 변환하여 문자열로 제공합니다. (하위 단위 미포함, 단위 문자열 포함)
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 4.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param bytes
     *            데이터 크기 (단위: byte)
     * @param unit
     *            변환 단위
     * @param pretty
     *            천 단위 콤마(,) 추가 여부
     *
     * @return 포맷팅된 용량 문자열
     *
     * @since 2021. 11. 4.
     * @version 1.8.0
     *
     * @see BinaryDataUnit#convert(long, BinaryDataUnit)
     */
    public static String binaryStorageAsString(long bytes, BinaryDataUnit unit, boolean pretty) {
        Objects.requireNonNull(unit);
        return binaryStorageAsString(bytes, BinaryDataUnit.BYTE, unit, pretty, true);
    }

    /**
     * 데이터 크기를 주어진 단위에 맞게 변환하여 문자열로 제공합니다. (하위 단위 및 단위 문자열 미포함)
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 5.     parkjunhong77@gmail.com         최초 작성
     * 2026. 4. 2.      parkjunhong77@gmail.com         (수정) 오타(단이 -> 단위) 교정
     * </pre>
     *
     * @param bytes
     *            데이터 크기 (단위: byte)
     * @param unit
     *            변환 단위
     * @param pretty
     *            천 단위 콤마(,) 추가 여부
     *
     * @return 단위 식별자가 없는 숫자 형태의 포맷팅된 문자열
     *
     * @since 2021. 11. 5.
     * @version 1.8.0
     */
    public static String binaryStorageAsStringNoUnit(long bytes, BinaryDataUnit unit, boolean pretty) {
        Objects.requireNonNull(unit);
        return binaryStorageAsString(bytes, BinaryDataUnit.BYTE, unit, pretty, false);
    }

    /**
     * 데이터를 나열된 문자열로 결합합니다.
     *
     * @param objects
     *            결합할 객체 가변 인자
     *
     * @return 결합된 문자열
     */
    // 아래 내용에 적용됨.
    // - StringBuilder.toString()()
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    private static String concat(Object... objects) {
        StringBuilder sb = new StringBuilder();
        for (Object o : objects) {
            sb.append(o.toString());
        }
        return sb.toString();
    }

    /**
     * 데이터 크기(10진수 기준)를 주어진 단위에 맞게 변환하여 제공합니다. (하위 단위 미포함)
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 4.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param bytes
     *            데이터 크기 (단위: byte)
     * @param unit
     *            변환 단위
     *
     * @return 변환된 크기의 {@link BigDecimal} 객체
     *
     * @since 2021. 11. 4.
     * @version 1.8.0
     *
     * @see PrefixDataUnit#convert(long, PrefixDataUnit)
     */
    public static BigDecimal convert(long bytes, PrefixDataUnit unit) {
        Objects.requireNonNull(unit);
        return PrefixDataUnit.BASE.convert(bytes, unit);
    }

    /**
     * 데이터 크기(10진수 기준)를 주어진 단위에 맞게 변환하여 제공합니다. (하위 단위 포함)
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 4.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param bytes
     *            데이터 크기 (단위: byte)
     * @param unit
     *            변환 단위
     *
     * @return 하위 단위별로 분할된 {@link BigDecimal} 배열
     *
     * @since 2021. 11. 4.
     * @version 1.8.0
     *
     * @see PrefixDataUnit#convert(long, PrefixDataUnit, boolean)
     */
    public static BigDecimal[] convertAlsoSubUnit(long bytes, PrefixDataUnit unit) {
        Objects.requireNonNull(unit);
        return PrefixDataUnit.BASE.convert(bytes, unit, true);
    }

    /**
     * 데이터 크기(10진수 기준)를 주어진 단위에 맞게 변환하여 문자열로 제공합니다. (하위 단위 포함, 천 단위 콤마 추가, 0인 단위 제외)
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 4.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param bytes
     *            데이터 크기 (단위: byte)
     * @param unit
     *            변환 단위
     *
     * @return 포맷팅된 용량 문자열
     *
     * @since 2021. 11. 4.
     * @version 1.8.0
     *
     * @see #convertAlsoSubUnitAsString(long, PrefixDataUnit, boolean, boolean)
     */
    public static String convertAlsoSubUnitAsString(long bytes, PrefixDataUnit unit) {
        return convertAlsoSubUnitAsString(bytes, unit, true, true);
    }

    /**
     * 데이터 크기(10진수 기준)를 주어진 단위에 맞게 변환하여 문자열로 제공합니다. (하위 단위 포함)
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 4.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param bytes
     *            데이터 크기 (단위: byte)
     * @param unit
     *            변환 단위
     * @param pretty
     *            천 단위 콤마(,) 추가 여부
     * @param trim
     *            사이즈가 '0'인 단위 제외 여부
     *
     * @return 포맷팅된 용량 문자열
     *
     * @since 2021. 11. 4.
     * @version 1.8.0
     *
     * @see PrefixDataUnit#convert(long, PrefixDataUnit, boolean)
     */
    public static String convertAlsoSubUnitAsString(long bytes, PrefixDataUnit unit, boolean pretty, boolean trim) {
        Objects.requireNonNull(unit);
        return convertAlsoSubUnitAsString(bytes, PrefixDataUnit.BASE, unit, pretty, trim);
    }

    /**
     * 데이터 크기(10진수 기준)를 시작 단위부터 목표 단위에 맞게 변환하여 문자열로 제공합니다. (하위 단위 포함)
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 4.     parkjunhong77@gmail.com         최초 작성
     * 2026. 4. 2.      parkjunhong77@gmail.com         (개선) ADD_COMMA 함수 호출 변경
     * </pre>
     *
     * @param size
     *            데이터 크기
     * @param srcUnit
     *            변환할 원본 데이터 단위
     * @param dstUnit
     *            변환할 목표 단위
     * @param pretty
     *            천 단위 콤마(,) 추가 여부
     * @param trim
     *            사이즈가 '0'인 단위 제외 여부
     *
     * @return 포맷팅된 용량 문자열
     *
     * @since 2021. 11. 4.
     * @version 1.8.0
     *
     * @see PrefixDataUnit#convert(long, PrefixDataUnit, boolean)
     */
    // 아래 내용에 적용됨.
    // - BigDecimal.toString()
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static String convertAlsoSubUnitAsString(long size, PrefixDataUnit srcUnit, PrefixDataUnit dstUnit,
            boolean pretty, boolean trim) {
        Objects.requireNonNull(srcUnit);
        Objects.requireNonNull(dstUnit);

        BigDecimal[] values = srcUnit.convert(size, dstUnit, true);
        List<String> s = new ArrayList<>();

        PrefixDataUnit u = dstUnit;
        String val = null;
        for (int i = 0; i < values.length; i++) {
            if (!trim || values[i].compareTo(BigDecimal.ZERO) != 0) {
                val = values[i].toString();
                if (pretty) {
                    val = addComma(val);
                }
                s.add(concat(val, " ", u.get()));
            }
            u = u.down();
        }

        return String.join(" ", s);
    }

    /**
     * 데이터 크기(10진수 기준)를 주어진 단위에 맞게 변환하여 문자열로 제공합니다. (하위 단위 미포함, 단위 문자열 포함)
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 4.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param bytes
     *            데이터 크기 (단위: byte)
     * @param unit
     *            변환 단위
     *
     * @return 포맷팅된 용량 문자열
     *
     * @since 2021. 11. 4.
     * @version 1.8.0
     *
     * @see BinaryDataUnit#convert(long, BinaryDataUnit)
     */
    public static String convertAsString(long bytes, PrefixDataUnit unit) {
        Objects.requireNonNull(unit);
        return convertAsString(bytes, PrefixDataUnit.BASE, unit, true, true);
    }

    /**
     * 데이터 크기(10진수 기준)를 주어진 단위에 맞게 변환하여 문자열로 제공합니다. (하위 단위 미포함, 단위 문자열 포함)
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 4.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param bytes
     *            데이터 크기 (단위: byte)
     * @param unit
     *            변환 단위
     * @param pretty
     *            천 단위 콤마(,) 추가 여부
     *
     * @return 포맷팅된 용량 문자열
     *
     * @since 2021. 11. 4.
     * @version 1.8.0
     *
     * @see BinaryDataUnit#convert(long, BinaryDataUnit)
     */
    public static String convertAsString(long bytes, PrefixDataUnit unit, boolean pretty) {
        Objects.requireNonNull(unit);
        return convertAsString(bytes, PrefixDataUnit.BASE, unit, pretty, true);
    }

    /**
     * 데이터 크기(10진수 기준)를 시작 단위부터 목표 단위에 맞게 변환하여 문자열로 제공합니다. (하위 단위 미포함)
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 4.     parkjunhong77@gmail.com         최초 작성
     * 2026. 4. 2.      parkjunhong77@gmail.com         (개선) ADD_COMMA 함수 호출 변경
     * </pre>
     *
     * @param size
     *            데이터 크기
     * @param srcUnit
     *            변환할 원본 데이터 단위
     * @param dstUnit
     *            변환할 목표 단위
     * @param pretty
     *            천 단위 콤마(,) 추가 여부
     * @param attachUnitStr
     *            단위 문자열 추가 여부
     *
     * @return 포맷팅된 용량 문자열
     *
     * @since 2021. 11. 4.
     * @version 1.8.0
     *
     * @see BinaryDataUnit#convert(long, BinaryDataUnit)
     */
    // 아래 내용에 적용됨.
    // - BigDecimal.toString()
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static String convertAsString(long size, PrefixDataUnit srcUnit, PrefixDataUnit dstUnit, boolean pretty,
            boolean attachUnitStr) {
        Objects.requireNonNull(srcUnit);
        Objects.requireNonNull(dstUnit);

        String val = srcUnit.convert(size, dstUnit).toString();
        if (pretty) {
            val = addComma(val);
        }
        return attachUnitStr ? concat(val, " ", dstUnit.get()) : val;
    }

    /**
     * 데이터 크기(10진수 기준)를 주어진 단위에 맞게 변환하여 문자열로 제공합니다. (하위 단위 및 단위 문자열 미포함)
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 5.     parkjunhong77@gmail.com         최초 작성
     * 2026. 4. 2.      parkjunhong77@gmail.com         (수정) 오타(단이 -> 단위) 교정
     * </pre>
     *
     * @param bytes
     *            데이터 크기 (단위: byte)
     * @param unit
     *            변환 단위
     * @param pretty
     *            천 단위 콤마(,) 추가 여부
     *
     * @return 단위 식별자가 없는 숫자 형태의 포맷팅된 문자열
     *
     * @since 2021. 11. 5.
     * @version 1.8.0
     */
    public static String convertAsStringNoUnit(long bytes, PrefixDataUnit unit, boolean pretty) {
        Objects.requireNonNull(unit);
        return convertAsString(bytes, PrefixDataUnit.BASE, unit, pretty, false);
    }
}