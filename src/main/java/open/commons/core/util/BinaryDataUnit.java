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
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import open.commons.core.utils.ObjectUtils;

/**
 * 컴퓨터 저장장치 용량 단위. (예: 디스크, 메모리, ...)<br>
 * 용량 데이터 타입을 <b>{@code long({@link Long})}</b>으로 하려고 했으나, 데이터 타입의 한계로 인하여 {@link BigDecimal}를 이용.
 * 
 * @since 2021. 11. 4.
 * @version 1.8.0
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 * 
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
     * <li>UP: {@link #KILO}
     * <li>DOWN: {@code null}
     * </ul>
     */
    BYTE("byte", BigDecimal.valueOf(2).pow(0)),
    /**
     * <b>Ki</b>lo <b>bi</b>nary byte, KiB = 2 ^ 10 bytes<br>
     * <ul>
     * <li>UP: {@link #MEGA}
     * <li>DOWN: {@link #BASE}
     * </ul>
     */
    KIBI("KiB", BigDecimal.valueOf(2).pow(10)),
    /**
     * <b>Me</b>ga <b>bi</b>nary byte, MiB = 2 ^ 20 bytes<br>
     * <ul>
     * <li>UP: {@link #GIGA}
     * <li>DOWN: {@link #KILO}
     * </ul>
     */
    MEBI("MiB", BigDecimal.valueOf(2).pow(20)),
    /**
     * <b>Gi</b>ga <b>bi</b>nary byte, GiB = 2 ^ 30 bytes<br>
     * <ul>
     * <li>UP: {@link #TERA}
     * <li>DOWN: {@link #MEGA}
     * </ul>
     */
    GIBI("GiB", BigDecimal.valueOf(2).pow(30)),
    /**
     * <b>Te</b>ra <b>bi</b>nary byte, TiB = 2 ^ 40 bytes<br>
     * <ul>
     * <li>UP: {@link #PETA}
     * <li>DOWN: {@link #GIGA}
     * </ul>
     */
    TEBI("TiB", BigDecimal.valueOf(2).pow(40)),
    /**
     * <b>Pe</b>ta <b>bi</b>nary byte, PiB = 2 ^ 50 bytes<br>
     * <ul>
     * <li>UP: {@link #EXA}
     * <li>DOWN: {@link #TERA}
     * </ul>
     */
    PEBI("PiB", BigDecimal.valueOf(2).pow(50)),
    /**
     * <b>Ex</b>a <b>bi</b>nary byte, EiB = 2 ^ 60 bytes<br>
     * <ul>
     * <li>UP: {@link #ZETTA}
     * <li>DOWN: {@link #PETA}
     * </ul>
     */
    EXBI("EiB", BigDecimal.valueOf(2).pow(60)),
    /**
     * <b>Ze</b>tta <b>bi</b>nary byte, ZiB = 2 ^ 70 bytes<br>
     * <ul>
     * <li>UP: {@link #YOTTA}
     * <li>DOWN: {@link #EXA}
     * </ul>
     */
    ZIBI("ZiB", BigDecimal.valueOf(2).pow(70)),
    /**
     * <b>Yo</b>tta <b>bi</b>nary byte, YiB = 2 ^ 80 bytes<br>
     * <ul>
     * <li>UP: {@code null}
     * <li>DOWN: {@link #ZETTA}
     * </ul>
     */
    YOBI("YiB", BigDecimal.valueOf(2).pow(80)),
    //
    ;

    /** 크기에 따른 오름차순 정렬 */
    static final List<BinaryDataUnit> BOTTOM_UP;
    /** 크기에 따른 내림차순 정렬 */
    static final List<BinaryDataUnit> TOP_DOWN;
    static {
        List<BinaryDataUnit> units = Arrays.asList(values());
        // 오름차순
        Collections.sort(units, (u1, u2) -> u1.num.compareTo(u2.num));
        BOTTOM_UP = Collections.unmodifiableList(units);
        // 내림차순
        units = Arrays.asList(values());
        Collections.sort(units, (u1, u2) -> u1.num.compareTo(u2.num) * -1);
        TOP_DOWN = Collections.unmodifiableList(units);
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
     * 주어진 데이터를 주어진 단위에 맞게 변환하여 제공합니다. (소숫점 이하 포함).
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 4.     parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param size
     *            데이터 크기
     * @param unit
     *            변환 단위
     * @return
     *
     * @since 2021. 11. 4.
     * @version 1.8.0
     * 
     * 
     * @see #convertHasRemain(long, BinaryDataUnit)
     */
    public BigDecimal convert(long size, BinaryDataUnit unit) {
        return Objects.requireNonNull(convert(size, unit, false)[0]);
    }

    /**
     * 주어진 데이터를 주어진 단위에 맞게 변환하여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 4.     parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param size
     *            데이터 크기
     * @param bigUnit
     *            변환범위 처음 단위.
     * @param littleUnit
     *            변환범위 끝 단위.
     * @return
     *
     * @throws NullPointerException
     *             파라미터({@code bigUnit 또는 littleUnit})가 {@code null}인 경우 발생.
     * @since 2021. 11. 4.
     * @version 1.8.0
     * 
     */
    public BigDecimal[] convert(long size, BinaryDataUnit bigUnit, BinaryDataUnit littleUnit) {
        ObjectUtils.requireNonNulls(bigUnit, littleUnit);

        if (bigUnit.num.compareTo(littleUnit.num) < 0) {
            throw new IllegalArgumentException(String.format("변환단위가 잘못되었습니다. big=%s, little=%s", bigUnit, littleUnit));
        }

        Deque<BigDecimal> converted = new LinkedList<>();

        // Bytes 변환
        BigDecimal bytes = BigDecimal.valueOf(size).multiply(this.num).setScale(10, RoundingMode.HALF_UP);

        List<BinaryDataUnit> units = TOP_DOWN.stream() //
                .filter(u -> !(u.num.compareTo(bigUnit.num) > 0 || u.num.compareTo(littleUnit.num) < 0)) //
                .collect(Collectors.toList());

        BigDecimal[] calc = null;
        int i = 0;
        for (; i < units.size() - 1; i++) {
            // 몫 계산
            calc = bytes.divideAndRemainder(units.get(i).num);
            converted.add(calc[0]);
            // 나머지, 다음 단위의 계산
            bytes = calc[1];
        }

        converted.add(bytes.divide(units.get(i).num));

        return Objects.requireNonNull( // [PATCH] JDK가 JSpecify 를 지원하지 않는 시점에 적용.
                converted.toArray(new BigDecimal[0]) //
        );
    }

    /**
     * 데이터 크기를 주어진 단위에 맞게 변환하여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 4.     parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param size
     *            데이터 크기
     * @param unit
     *            변환 단위
     * @param alsoSubUnit
     *            하위 단위 포함 변환 여부.
     * @return
     *
     * @throws NullPointerException
     *             파라미터({@code unit})가 {@code null}인 경우 발생.
     * @since 2021. 11. 4.
     * @version 1.8.0
     * 
     */
    public BigDecimal[] convert(long size, BinaryDataUnit unit, boolean alsoSubUnit) {
        Objects.requireNonNull(unit);

        return alsoSubUnit //
                ? convert(size, unit, BinaryDataUnit.BYTE) //
                : convert(size, unit, unit);
    }

    /**
     * 하위 단위를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 4.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     *
     * @return 하위 단위. 현재 단위가 가장 하위인 경우 <b><i>{@code {@link #BYTE} (자신)}</i></b>.
     *
     * @since 2021. 11. 4.
     * @version 1.8.0
     * 
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
     *
     * @return a string of an instance of {@link BinaryDataUnit}
     *
     * @since 2021. 11. 4.
     * 
     */
    public String get() {
        return this.str;
    }

    /**
     * @since 2021. 11. 4.
     * 
     *
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return String.join(":", name(), this.str, this.num.toString());
    }

    /**
     * 상위 단위를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 4.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     *
     * @return 상위 단위. 현재 단위가 가장 상위인 경우 {@code null} 반환.
     *
     * @since 2021. 11. 4.
     * @version 1.8.0
     * 
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
     * 
     * @param str
     *            a string for {@link BinaryDataUnit} instance.
     *
     * @return an instance of {@link BinaryDataUnit}
     *
     * @since 2021. 11. 4.
     * 
     *
     * @see #get(String, boolean)
     */
    public static BinaryDataUnit get(String str) {
        return get(str, false);
    }

    /**
     *
     * @param unitStr
     *            a string for an instance of {@link BinaryDataUnit}.
     * @param ignoreCase
     *            ignore {@code <b>case-sensitive</b>} or not.
     *
     * @return an instance of {@link BinaryDataUnit}
     * 
     * @throws NullPointerException
     *             파라미터({@code unitStr})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 4.
     * 
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
