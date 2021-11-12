/*
 * Copyright 2021 Park Jun-Hong_(parkjunhong77@gmail.com)
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
 * Date  : 2021. 11. 5. 오후 1:09:23
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 거리/무게 등 여러 가지 단위에 대한 접두어.<br>
 * 용량 데이터 타입을 <b><code>long({@link Long})</code></b>으로 하려고 했으나, 데이터 타입의 한계로 인하여 {@link BigDecimal}를 이용.
 * 
 * @since 2021. 11. 5.
 * @version 1.8.0
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public enum PrefixDataUnit {
    /**
     * base = 1<br>
     * <ul>
     * <li>UP: {@link #KILO}
     * <li>DOWN: <code>null</code>
     * </ul>
     */
    BASE("", BigDecimal.valueOf(10).pow(0)),
    /**
     * <b>K</b>ilo, K = 10 ^ 3<br>
     * <ul>
     * <li>UP: {@link #MEGA}
     * <li>DOWN: {@link #BASE}
     * </ul>
     */
    KILO("K", BigDecimal.valueOf(10).pow(3)),
    /**
     * <b>M</b>ega, M = 10 ^ 6<br>
     * <ul>
     * <li>UP: {@link #GIGA}
     * <li>DOWN: {@link #KILO}
     * </ul>
     */
    MEGA("M", BigDecimal.valueOf(10).pow(6)),
    /**
     * <b>G</b>iga, G = 10 ^ 9<br>
     * <ul>
     * <li>UP: {@link #TERA}
     * <li>DOWN: {@link #MEGA}
     * </ul>
     */
    GIGA("G", BigDecimal.valueOf(10).pow(9)),
    /**
     * <b>T</b>era, T = 10 ^ 12<br>
     * <ul>
     * <li>UP: {@link #PETA}
     * <li>DOWN: {@link #GIGA}
     * </ul>
     */
    TERA("T", BigDecimal.valueOf(10).pow(12)),
    /**
     * <b>P</b>eta, P = 10 ^ 15<br>
     * <ul>
     * <li>UP: {@link #EXA}
     * <li>DOWN: {@link #TERA}
     * </ul>
     */
    PETA("P", BigDecimal.valueOf(10).pow(15)),
    /**
     * <b>E</b>xa, E = 10 ^ 18<br>
     * <ul>
     * <li>UP: {@link #ZETTA}
     * <li>DOWN: {@link #PETA}
     * </ul>
     */
    EXA("E", BigDecimal.valueOf(10).pow(18)),
    /**
     * <b>Z</b>etta, Z = 10 ^ 21<br>
     * <ul>
     * <li>UP: {@link #YOTTA}
     * <li>DOWN: {@link #EXA}
     * </ul>
     */
    ZETTA("Z", BigDecimal.valueOf(10).pow(21)),
    /**
     * <b>Y</b>otta, Y = 10 ^ 24<br>
     * <ul>
     * <li>UP: <code>null</code>
     * <li>DOWN: {@link #ZETTA}
     * </ul>
     */
    YOTTA("Y", BigDecimal.valueOf(10).pow(24)),
    //
    ;

    /** 크기에 따른 오름차순 정렬 */
    static final List<PrefixDataUnit> BOTTOM_UP;
    /** 크기에 따른 내림차순 정렬 */
    static final List<PrefixDataUnit> TOP_DOWN;
    static {
        List<PrefixDataUnit> units = Arrays.asList(values());
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

    private PrefixDataUnit(String str, BigDecimal num) {
        this.str = str;
        this.num = num.setScale(10, RoundingMode.HALF_UP);
    }

    /**
     * 데이터 값을 주어진 단위에 맞게 변환하여 제공합니다. (소숫점 이하 포함).
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 5.     박준홍         최초 작성
     * </pre>
     *
     * @param size
     *            데이터 크기
     * @param unit
     *            변환 단위
     * @return
     *
     * @since 2021. 11. 5.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @see #convertHasRemain(long, PrefixDataUnit)
     */
    public BigDecimal convert(long size, PrefixDataUnit unit) {
        return convert(size, unit, false)[0];
    }

    /**
     * 메모리 값을 주어진 단위로 변환하여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 5.     박준홍         최초 작성
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
     * @since 2021. 11. 5.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public BigDecimal[] convert(long size, PrefixDataUnit unit, boolean alsoSubUnit) {
        return alsoSubUnit //
                ? convert(size, unit, PrefixDataUnit.BASE) //
                : convert(size, unit, unit);
    }

    /**
     * 메모리 값을 주어진 단위로 변환하여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 5.     박준홍         최초 작성
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
     * @since 2021. 11. 5.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public BigDecimal[] convert(long size, PrefixDataUnit bigUnit, PrefixDataUnit littleUnit) {
        if (bigUnit.num.compareTo(littleUnit.num) < 0) {
            throw new IllegalArgumentException(String.format("변환단위가 잘못되었습니다. big=%s, little=%s", bigUnit, littleUnit));
        }

        Deque<BigDecimal> converted = new LinkedList<>();

        // Bytes 변환
        BigDecimal bytes = BigDecimal.valueOf(size).multiply(this.num).setScale(10, RoundingMode.HALF_UP);

        List<PrefixDataUnit> units = TOP_DOWN.stream() //
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

        return converted.toArray(new BigDecimal[0]);
    }

    /**
     * 하위 단위를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 5.		박준홍			최초 작성
     * </pre>
     *
     * @return 하위 단위. 현재 단위가 가장 하위인 경우 <code>null</code> 반환.
     *
     * @since 2021. 11. 5.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public PrefixDataUnit down() {
        switch (this) {
            case BASE:
                return null;
            case KILO:
                return BASE;
            case MEGA:
                return KILO;
            case GIGA:
                return MEGA;
            case TERA:
                return GIGA;
            case PETA:
                return TERA;
            case EXA:
                return PETA;
            case ZETTA:
                return EXA;
            case YOTTA:
                return ZETTA;
            default:
                // unreachable code
                throw new IllegalArgumentException("Unexpected 'str' value of 'BinaryDataUnit'");
        }
    }

    /**
     *
     * @return a string of an instance of {@link PrefixDataUnit}
     *
     * @since 2021. 11. 5.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public String get() {
        return this.str;
    }

    /**
     * @since 2021. 11. 5.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
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
     * 2021. 11. 5.		박준홍			최초 작성
     * </pre>
     *
     * @return 상위 단위. 현재 단위가 가장 상위인 경우 <code>null</code> 반환.
     *
     * @since 2021. 11. 5.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public PrefixDataUnit up() {
        switch (this) {
            case BASE:
                return KILO;
            case KILO:
                return MEGA;
            case MEGA:
                return GIGA;
            case GIGA:
                return TERA;
            case TERA:
                return PETA;
            case PETA:
                return EXA;
            case EXA:
                return ZETTA;
            case ZETTA:
                return YOTTA;
            case YOTTA:
                return null;
            default:
                // unreachable code
                throw new IllegalArgumentException("Unexpected 'str' value of 'BinaryDataUnit'");
        }
    }

    /**
     * 
     * @param str
     *            a string for {@link PrefixDataUnit} instance.
     *
     * @return an instance of {@link PrefixDataUnit}
     *
     * @since 2021. 11. 5.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see #get(String, boolean)
     */
    public static PrefixDataUnit get(String str) {
        return get(str, false);
    }

    /**
     *
     * @param str
     *            a string for an instance of {@link PrefixDataUnit}.
     * @param ignoreCase
     *            ignore <code><b>case-sensitive</b></code> or not.
     *
     * @return an instance of {@link PrefixDataUnit}
     *
     * @since 2021. 11. 5.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static PrefixDataUnit get(String str, boolean ignoreCase) {

        if (str == null) {
            throw new IllegalArgumentException("'str' MUST NOT be null. input: " + str);
        }

        if (ignoreCase) {
            for (PrefixDataUnit value : values()) {
                if (value.str.equalsIgnoreCase(str)) {
                    return value;
                }
            }
        } else {
            for (PrefixDataUnit value : values()) {
                if (value.str.equals(str)) {
                    return value;
                }
            }
        }

        throw new IllegalArgumentException("Unexpected 'str' value of 'BinaryDataUnit'. expected: " + values0() + " & Ignore case-sensitive: " + ignoreCase + ", input: " + str);
    }

    private static List<String> values0() {

        List<String> valuesStr = new ArrayList<>();

        for (PrefixDataUnit value : values()) {
            valuesStr.add(value.get());
        }

        return valuesStr;
    }
}
