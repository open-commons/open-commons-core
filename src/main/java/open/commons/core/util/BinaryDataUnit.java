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
 * Date  : 2021. 11. 4. 오후 3:09:23
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.core.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import open.commons.core.utils.AssertUtils2;

/**
 * 컴퓨터 저장장치 용량 단위. (예: 디스크, 메모리, ...)<br>
 * 용량 데이터 타입을 <b>{@code long({@link Long})}</b>으로 하려고 했으나, 데이터 타입의 한계로 인하여 {@link BigDecimal}를 이용.
 *
 * @since 2021. 11. 4.
 * @version 1.8.0
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 *
 * @see <a href="https://en.wikipedia.org/wiki/International_Electrotechnical_Commission">IEC</a>
 */
// [PATCH] JDK 표준 API의 JSpecify 미지원 우회용
// [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 제거.
@SuppressWarnings("null")
public enum BinaryDataUnit {
    /**
     * byte = 2 ^ 0 byte (8 bits)<br>
     * <ul>
     * <li>UP: {@link #KIBI}
     * <li>DOWN: {@code null}
     * </ul>
     */
    BYTE("byte", BigDecimal.valueOf(2).pow(0)),
    /**
     * <b>Ki</b>lo <b>bi</b>nary byte, KiB = 2 ^ 10 bytes<br>
     * <ul>
     * <li>UP: {@link #MEBI}
     * <li>DOWN: {@link #BYTE}
     * </ul>
     */
    KIBI("KiB", BigDecimal.valueOf(2).pow(10)),
    /**
     * <b>Me</b>ga <b>bi</b>nary byte, MiB = 2 ^ 20 bytes<br>
     * <ul>
     * <li>UP: {@link #GIBI}
     * <li>DOWN: {@link #KIBI}
     * </ul>
     */
    MEBI("MiB", BigDecimal.valueOf(2).pow(20)),
    /**
     * <b>Gi</b>ga <b>bi</b>nary byte, GiB = 2 ^ 30 bytes<br>
     * <ul>
     * <li>UP: {@link #TEBI}
     * <li>DOWN: {@link #MEBI}
     * </ul>
     */
    GIBI("GiB", BigDecimal.valueOf(2).pow(30)),
    /**
     * <b>Te</b>ra <b>bi</b>nary byte, TiB = 2 ^ 40 bytes<br>
     * <ul>
     * <li>UP: {@link #PEBI}
     * <li>DOWN: {@link #GIBI}
     * </ul>
     */
    TEBI("TiB", BigDecimal.valueOf(2).pow(40)),
    /**
     * <b>Pe</b>ta <b>bi</b>nary byte, PiB = 2 ^ 50 bytes<br>
     * <ul>
     * <li>UP: {@link #EXBI}
     * <li>DOWN: {@link #TEBI}
     * </ul>
     */
    PEBI("PiB", BigDecimal.valueOf(2).pow(50)),
    /**
     * <b>Ex</b>a <b>bi</b>nary byte, EiB = 2 ^ 60 bytes<br>
     * <ul>
     * <li>UP: {@link #ZIBI}
     * <li>DOWN: {@link #PEBI}
     * </ul>
     */
    EXBI("EiB", BigDecimal.valueOf(2).pow(60)),
    /**
     * <b>Ze</b>tta <b>bi</b>nary byte, ZiB = 2 ^ 70 bytes<br>
     * <ul>
     * <li>UP: {@link #YOBI}
     * <li>DOWN: {@link #EXBI}
     * </ul>
     */
    ZIBI("ZiB", BigDecimal.valueOf(2).pow(70)),
    /**
     * <b>Yo</b>tta <b>bi</b>nary byte, YiB = 2 ^ 80 bytes<br>
     * <ul>
     * <li>UP: {@code null}
     * <li>DOWN: {@link #ZIBI}
     * </ul>
     */
    YOBI("YiB", BigDecimal.valueOf(2).pow(80));

    /** 크기에 따른 오름차순 정렬 */
    static final List<BinaryDataUnit> BOTTOM_UP;
    /** 크기에 따른 내림차순 정렬 */
    static final List<BinaryDataUnit> TOP_DOWN;

    static {
        // [최적화] 모던 자바의 List.sort 및 Comparator.comparing 활용
        List<BinaryDataUnit> ascUnits = Arrays.asList(values());
        ascUnits.sort(Comparator.comparing(u -> u.num));
        BOTTOM_UP = Collections.unmodifiableList(ascUnits);

        List<BinaryDataUnit> descUnits = Arrays.asList(values());
        descUnits.sort((u1, u2) -> u2.num.compareTo(u1.num));
        TOP_DOWN = Collections.unmodifiableList(descUnits);
    }

    /** 표기 문자열 */
    private String str;
    /** BASE 기준 크기 */
    private BigDecimal num;

    private BinaryDataUnit(String str, BigDecimal num) {
        this.str = str;
        this.num = num.setScale(10, RoundingMode.HALF_UP);
    }

    /**
     * 주어진 데이터를 주어진 단위에 맞게 변환하여 제공합니다. (소수점 이하 포함).
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 4.     parkjunhong77@gmail.com         최초 작성
     * 2026. 4. 2.      parkjunhong77@gmail.com         (3.0.0) Javadoc 규격 및 NPE 명세 적용
     * </pre>
     *
     * @param size
     *            데이터 크기
     * @param unit
     *            변환 단위
     *
     * @return 변환된 크기의 {@link BigDecimal} 값
     *
     * @since 2021. 11. 4.
     * @version 3.0.0
     *
     * @see #convert(long, BinaryDataUnit, boolean)
     */
    public BigDecimal convert(long size, BinaryDataUnit unit) {
        return convert(size, unit, false)[0];
    }

    /**
     * 주어진 데이터를 시작 단위부터 끝 단위까지의 범위에 맞게 순차적으로 변환하여 제공합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 4.     parkjunhong77@gmail.com         최초 작성
     * 2026. 4. 2.      parkjunhong77@gmail.com         (3.0.0) 스트림 긍정 논리식 최적화 및 ArithmeticException 방지
     * </pre>
     *
     * @param size
     *            데이터 크기
     * @param bigUnit
     *            변환 범위 처음 단위
     * @param littleUnit
     *            변환 범위 끝 단위
     *
     * @return 단위별로 나누어진 크기들의 배열
     * @throws IllegalArgumentException
     *             변환 단위의 순서(크기)가 잘못 지정된 경우 발생.
     *
     * @since 2021. 11. 4.
     * @version 3.0.0
     */
    public BigDecimal[] convert(long size, BinaryDataUnit bigUnit, BinaryDataUnit littleUnit) {
        AssertUtils2.notNulls(bigUnit, littleUnit);

        if (bigUnit.num.compareTo(littleUnit.num) < 0) {
            throw new IllegalArgumentException(String.format("변환단위가 잘못되었습니다. big=%s, little=%s", bigUnit, littleUnit));
        }

        Deque<BigDecimal> converted = new LinkedList<>();

        // Bytes 변환
        BigDecimal bytes = BigDecimal.valueOf(size).multiply(this.num).setScale(10, RoundingMode.HALF_UP);

        // [최적화] 가독성을 위해 드모르간의 법칙을 적용하여 긍정 논리식(Positive Logic)으로 변경
        List<BinaryDataUnit> units = TOP_DOWN.stream().filter(u -> u.num.compareTo(bigUnit.num) <= 0 && u.num.compareTo(littleUnit.num) >= 0).collect(Collectors.toList());

        BigDecimal[] calc = null;
        int i = 0;
        for (; i < units.size() - 1; i++) {
            // 몫 계산
            calc = bytes.divideAndRemainder(units.get(i).num);
            converted.add(calc[0]);
            // 나머지, 다음 단위의 계산
            bytes = calc[1];
        }

        // [안전성] 무한 소수 발생 시 ArithmeticException을 방지하기 위해 RoundingMode 명시
        converted.add(bytes.divide(units.get(i).num, 10, RoundingMode.HALF_UP));

        return converted.toArray(new BigDecimal[0]);
    }

    /**
     * 데이터 크기를 주어진 단위에 맞게 변환하여 제공합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 4.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param size
     *            데이터 크기
     * @param unit
     *            변환 단위
     * @param alsoSubUnit
     *            하위 단위 포함 변환 여부
     *
     * @return 단위별로 변환된 데이터 크기 배열
     *
     * @since 2021. 11. 4.
     * @version 1.8.0
     */
    public BigDecimal[] convert(long size, BinaryDataUnit unit, boolean alsoSubUnit) {
        Objects.requireNonNull(unit);

        return alsoSubUnit ? convert(size, unit, BinaryDataUnit.BYTE) : convert(size, unit, unit);
    }

    /**
     * 하위 단위를 제공합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 4.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @return 하위 단위. 현재 단위가 가장 하위인 경우 <b><i>{@code {@link #BYTE} (자신)}</i></b>.
     *
     * @since 2021. 11. 4.
     * @version 1.8.0
     */
    public BinaryDataUnit down() {
        return switch (this) {
            case BYTE -> BYTE;
            case KIBI -> BYTE;
            case MEBI -> KIBI;
            case GIBI -> MEBI;
            case TEBI -> GIBI;
            case PEBI -> TEBI;
            case EXBI -> PEBI;
            case ZIBI -> EXBI;
            case YOBI -> ZIBI;
        };
    }

    /**
     * 단위의 표기 문자열(String)을 반환합니다.
     *
     * @return {@link BinaryDataUnit}의 문자열 값
     *
     * @since 2021. 11. 4.
     */
    public String get() {
        return this.str;
    }

    /**
     * @since 2021. 11. 4.
     *
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return String.join(":", name(), this.str, this.num.toString());
    }

    /**
     * 상위 단위를 제공합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 4.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @return 상위 단위. 현재 단위가 가장 상위인 경우 <b><i>{@code {@link #YOBI} (자신)}</i></b>.
     *
     * @since 2021. 11. 4.
     * @version 1.8.0
     */
    public BinaryDataUnit up() {
        return switch (this) {
            case BYTE -> KIBI;
            case KIBI -> MEBI;
            case MEBI -> GIBI;
            case GIBI -> TEBI;
            case TEBI -> PEBI;
            case PEBI -> EXBI;
            case EXBI -> ZIBI;
            case ZIBI -> YOBI;
            case YOBI -> YOBI;
        };
    }

    /**
     * 문자열과 일치하는 단위를 반환합니다. 대소문자를 구분합니다.
     *
     * @param str
     *            {@link BinaryDataUnit} 인스턴스를 찾기 위한 문자열
     *
     * @return 일치하는 {@link BinaryDataUnit} 인스턴스
     * @throws IllegalArgumentException
     *             매칭되는 단위를 찾을 수 없는 경우 발생.
     *
     * @since 2021. 11. 4.
     *
     * @see #get(String, boolean)
     */
    public static BinaryDataUnit get(String str) {
        return get(str, false);
    }

    /**
     * 문자열과 일치하는 단위를 반환합니다. 옵션에 따라 대소문자 구분 여부를 결정합니다.
     *
     * @param unitStr
     *            {@link BinaryDataUnit} 인스턴스를 찾기 위한 문자열
     * @param ignoreCase
     *            대소문자 무시 여부 ({@code true}면 무시)
     *
     * @return 일치하는 {@link BinaryDataUnit} 인스턴스
     * @throws IllegalArgumentException
     *             매칭되는 단위를 찾을 수 없는 경우 발생.
     *
     * @since 2021. 11. 4.
     */
    public static BinaryDataUnit get(String unitStr, boolean ignoreCase) {
        Objects.requireNonNull(unitStr);

        if (ignoreCase) {
            for (BinaryDataUnit value : values()) {
                if (value.str.equalsIgnoreCase(unitStr)) {
                    return value;
                }
            }
        } else {
            for (BinaryDataUnit value : values()) {
                if (value.str.equals(unitStr)) {
                    return value;
                }
            }
        }

        throw new IllegalArgumentException(
                "Unexpected 'str' value of 'BinaryDataUnit'. expected: " + values0() + " & Ignore case-sensitive: " + ignoreCase + ", input: " + unitStr);
    }

    private static List<String> values0() {
        List<String> valuesStr = new ArrayList<>();

        for (BinaryDataUnit value : values()) {
            valuesStr.add(value.get());
        }

        return valuesStr;
    }
}