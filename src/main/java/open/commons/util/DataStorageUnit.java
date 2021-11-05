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
 * Date  : 2021. 11. 4. 오후 3:09:23
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

import open.commons.utils.ExceptionUtils;

/**
 * 컴퓨터 저장장치 용량 단위. (예: 디스크, 메모리, ...)
 * 
 * @since 2021. 11. 4.
 * @version 1.8.0
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public enum DataStorageUnit {
    /**
     * Byte = 2 * 1 Byte (8 bits)<br>
     * <ul>
     * <li>UP: {@link #KByte}
     * <li>DOWN: <code>null</code>
     * </ul>
     */
    Byte("Byte", BigDecimal.valueOf(2).pow(0)),
    /**
     * KByte = 2 ^ 10 Bytes<br>
     * <ul>
     * <li>UP: {@link #MByte}
     * <li>DOWN: {@link #Byte}
     * </ul>
     */
    KByte("KB", BigDecimal.valueOf(2).pow(10)),
    /**
     * MByte = 2 ^ 20 Bytes<br>
     * <ul>
     * <li>UP: {@link #GByte}
     * <li>DOWN: {@link #KByte}
     * </ul>
     */
    MByte("MB", BigDecimal.valueOf(2).pow(20)),
    /**
     * GByte = 2 ^ 30 Bytes<br>
     * <ul>
     * <li>UP: {@link #TByte}
     * <li>DOWN: {@link #MByte}
     * </ul>
     */
    GByte("GB", BigDecimal.valueOf(2).pow(30)),
    /**
     * TByte = 2 ^ 40 Bytes<br>
     * <ul>
     * <li>UP: {@link #PByte}
     * <li>DOWN: {@link #GByte}
     * </ul>
     */
    TByte("TB", BigDecimal.valueOf(2).pow(40)),
    /**
     * PByte = 2 ^ 50 Bytes <br>
     * <ul>
     * <li>UP: {@link #EByte}
     * <li>DOWN: {@link #TByte}
     * </ul>
     */
    PByte("PB", BigDecimal.valueOf(2).pow(50)),
    /**
     * EByte = 2 ^ 60 Bytes <br>
     * <ul>
     * <li>UP: {@link #ZByte}
     * <li>DOWN: {@link #PByte}
     * </ul>
     */
    EByte("EB", BigDecimal.valueOf(2).pow(60)),
    /**
     * ZByte = 2 ^ 70 Bytes <br>
     * <ul>
     * <li>UP: {@link #YByte}
     * <li>DOWN: {@link #EByte}
     * </ul>
     */
    ZByte("ZB", BigDecimal.valueOf(2).pow(70)),
    /**
     * YByte = 2 ^ 80 Bytes <br>
     * <ul>
     * <li>UP: <code>null</code>
     * <li>DOWN: {@link #ZByte}
     * </ul>
     */
    YByte("YB", BigDecimal.valueOf(2).pow(80)),
    //
    ;

    /** 크기에 따른 오름차순 정렬 */
    static final List<DataStorageUnit> BOTTOM_UP;
    /** 크기에 따른 내림차순 정렬 */
    static final List<DataStorageUnit> TOP_DOWN;
    static {
        List<DataStorageUnit> units = Arrays.asList(values());
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
    /** Byte 기준 크기 */
    private BigDecimal num;

    private DataStorageUnit(String str, BigDecimal num) {
        this.str = str;
        this.num = num.setScale(10, RoundingMode.HALF_UP);
    }

    /**
     * 메모리 값을 주어진 크기로 변환하여 제공한다. (소숫점 이하 포함).
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 4.     박준홍         최초 작성
     * </pre>
     *
     * @param size
     *            메모리 크기
     * @param unit
     *            메모리 단위
     * @return
     *
     * @since 2021. 11. 4.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @see #convertHasRemain(long, DataStorageUnit)
     */
    public BigDecimal convert(long size, DataStorageUnit unit) {
        return convert(size, unit, false)[0];
    }

    /**
     * 메모리 값을 주어진 단위로 변환하여 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 4.     박준홍         최초 작성
     * </pre>
     *
     * @param size
     *            메모리 크기
     * @param unit
     *            메모리 단위
     * @param alsoSubUnit
     *            하위 단위 포함 변환 여부.
     * @return
     *
     * @since 2021. 11. 4.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public BigDecimal[] convert(long size, DataStorageUnit unit, boolean alsoSubUnit) {
        return alsoSubUnit //
                ? convert(size, unit, DataStorageUnit.Byte) //
                : convert(size, unit, unit);
    }

    /**
     * 메모리 값을 주어진 단위로 변환하여 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 4.     박준홍         최초 작성
     * </pre>
     *
     * @param size
     *            메모리 크기
     * @param unit
     *            메모리 단위
     * @param bigUnit
     *            변환범위 처음 단위.
     * @param littleUnit
     *            변환범위 끝 단위.
     * @return
     *
     * @since 2021. 11. 4.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public BigDecimal[] convert(long size, DataStorageUnit bigUnit, DataStorageUnit littleUnit) {
        if (bigUnit.num.compareTo(littleUnit.num) < 0) {
            throw ExceptionUtils.newException(IllegalArgumentException.class, "변환단위가 잘못되었습니다. big=%s, little=%s", bigUnit, littleUnit);
        }

        Deque<BigDecimal> converted = new LinkedList<>();

        // Bytes 변환
        BigDecimal bytes = BigDecimal.valueOf(size).multiply(this.num).setScale(10, RoundingMode.HALF_UP);

        List<DataStorageUnit> units = TOP_DOWN.stream() //
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
     * 2021. 11. 4.		박준홍			최초 작성
     * </pre>
     *
     * @return 하위 단위. 현재 단위가 가장 하위인 경우 <code>null</code> 반환.
     *
     * @since 2021. 11. 4.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public DataStorageUnit down() {
        switch (this) {
            case Byte:
                return null;
            case KByte:
                return Byte;
            case MByte:
                return KByte;
            case GByte:
                return MByte;
            case TByte:
                return GByte;
            case PByte:
                return TByte;
            case EByte:
                return PByte;
            case ZByte:
                return EByte;
            case YByte:
                return ZByte;
            default:
                // unreachable code
                throw new IllegalArgumentException("Unexpected 'str' value of 'DataStorageUnit'");
        }
    }

    /**
     *
     * @return a string of an instance of {@link DataStorageUnit}
     *
     * @since 2021. 11. 4.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public String get() {
        return this.str;
    }

    /**
     * @since 2021. 11. 4.
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
     * 2021. 11. 4.		박준홍			최초 작성
     * </pre>
     *
     * @return 상위 단위. 현재 단위가 가장 상위인 경우 <code>null</code> 반환.
     *
     * @since 2021. 11. 4.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public DataStorageUnit up() {
        switch (this) {
            case Byte:
                return KByte;
            case KByte:
                return MByte;
            case MByte:
                return GByte;
            case GByte:
                return TByte;
            case TByte:
                return PByte;
            case PByte:
                return EByte;
            case EByte:
                return ZByte;
            case ZByte:
                return YByte;
            case YByte:
                return null;
            default:
                // unreachable code
                throw new IllegalArgumentException("Unexpected 'str' value of 'DataStorageUnit'");
        }
    }

    /**
     * 
     * @param str
     *            a string for {@link DataStorageUnit} instance.
     *
     * @return an instance of {@link DataStorageUnit}
     *
     * @since 2021. 11. 4.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see #get(String, boolean)
     */
    public static DataStorageUnit get(String str) {
        return get(str, false);
    }

    /**
     *
     * @param str
     *            a string for an instance of {@link DataStorageUnit}.
     * @param ignoreCase
     *            ignore <code><b>case-sensitive</b></code> or not.
     *
     * @return an instance of {@link DataStorageUnit}
     *
     * @since 2021. 11. 4.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static DataStorageUnit get(String str, boolean ignoreCase) {

        if (str == null) {
            throw new IllegalArgumentException("'str' MUST NOT be null. input: " + str);
        }

        if (ignoreCase) {
            for (DataStorageUnit value : values()) {
                if (value.str.equalsIgnoreCase(str)) {
                    return value;
                }
            }
        } else {
            for (DataStorageUnit value : values()) {
                if (value.str.equals(str)) {
                    return value;
                }
            }
        }

        throw new IllegalArgumentException("Unexpected 'str' value of 'DataStorageUnit'. expected: " + values0() + " & Ignore case-sensitive: " + ignoreCase + ", input: " + str);
    }

    private static List<String> values0() {

        List<String> valuesStr = new ArrayList<>();

        for (DataStorageUnit value : values()) {
            valuesStr.add(value.get());
        }

        return valuesStr;
    }
}
