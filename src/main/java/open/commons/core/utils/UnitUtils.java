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
 * Date  : 2021. 11. 4. 오후 1:23:48
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.core.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import open.commons.core.util.BinaryDataUnit;
import open.commons.core.util.PrefixDataUnit;

/**
 * 단위 관련 기능을 제공.
 * 
 * @since 2021. 11. 4.
 * @version 1.8.0
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public class UnitUtils {

    /**
     * 천단위마다 콤마(,) 추가
     * 
     * @param val
     *            숫자 문자열
     * @return 천단위마다 콤마(,)가 추가된 문자열.
     */
    private static final Function<String, String> ADD_COMMA = val -> {
        String[] arr = val.split("[.]");
        String result = null;
        if (arr.length == 1) {
            result = val.replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
        } else {
            result = String.join(".", arr[0].replaceAll("\\B(?=(\\d{3})+(?!\\d))", ","), arr[1]);
        }

        return result;
    };

    // prevent to create an instance.
    private UnitUtils() {
    }

    /**
     * 데이터 크기를 주어진 단위에 맞게 변환하여 제공합니다. (하위 단위 미포함) <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 4.		박준홍			최초 작성
     * </pre>
     *
     * @param bytes
     *            데이터 크기 (단위: byte)
     * @param unit
     *            변환 단위
     * @return
     *
     * @since 2021. 11. 4.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @see BinaryDataUnit#convert(long, BinaryDataUnit)
     */
    public static BigDecimal binaryStorage(long bytes, BinaryDataUnit unit) {
        return BinaryDataUnit.BYTE.convert(bytes, unit);
    }

    /**
     * 데이터 크기를 주어진 단위에 맞게 변환하여 제공합니다. (하위 단위 포함) <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 4.		박준홍			최초 작성
     * </pre>
     *
     * @param bytes
     *            데이터 크기 (단위: byte)
     * @param unit
     *            변환 단위
     * @return
     *
     * @since 2021. 11. 4.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @see BinaryDataUnit#convert(long, BinaryDataUnit, boolean)
     */
    public static BigDecimal[] binaryStorageAlsoSubUnit(long bytes, BinaryDataUnit unit) {
        return BinaryDataUnit.BYTE.convert(bytes, unit, true);
    }

    /**
     * 데이터 크기를 주어진 단위에 맞게 변환하여 제공합니다. (하위 단위 포함) <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 4.		박준홍			최초 작성
     * </pre>
     *
     * @param bytes
     *            데이터 크기 (단위: byte)
     * @param unit
     *            변환 단위
     * @return
     *
     * @since 2021. 11. 4.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @see #binaryStorageAlsoSubUnitAsString(long, BinaryDataUnit, boolean, boolean)
     */
    public static String binaryStorageAlsoSubUnitAsString(long bytes, BinaryDataUnit unit) {
        return binaryStorageAlsoSubUnitAsString(bytes, unit, true, true);
    }

    /**
     * 데이터 크기를 주어진 단위에 맞게 변환하여 제공합니다. (하위 단위 포함) <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 4.		박준홍			최초 작성
     * </pre>
     *
     * @param size
     *            데이터 크기
     * @param srcUnit
     *            변환할 데이터 단위
     * @param dstUnit
     *            변환 단위
     * @param pretty
     *            천단위 콤마(,) 추가 여부
     * @param trim
     *            사이즈 '0'인 단위 제외 여부.
     * @return
     *
     * @since 2021. 11. 4.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @see BinaryDataUnit#convert(long, BinaryDataUnit, boolean)
     */
    public static String binaryStorageAlsoSubUnitAsString(long size, BinaryDataUnit srcUnit, BinaryDataUnit dstUnit, boolean pretty, boolean trim) {
        BigDecimal[] values = srcUnit.convert(size, dstUnit, true);
        List<String> s = new ArrayList<>();

        BinaryDataUnit u = dstUnit;
        String val = null;
        for (int i = 0; i < values.length; i++) {
            if (!trim || values[i].compareTo(BigDecimal.ZERO) != 0) {
                val = values[i].toString();
                if (pretty) {
                    val = ADD_COMMA.apply(val);
                }
                s.add(concat(val, " ", u.get()));
            }
            u = u.down();
        }

        return String.join(" ", s);
    }

    /**
     * 데이터 크기를 주어진 단위에 맞게 변환하여 제공합니다. (하위 단위 포함) <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 4.		박준홍			최초 작성
     * </pre>
     *
     * @param bytes
     *            데이터 크기 (단위: byte)
     * @param unit
     *            변환 단위
     * @param pretty
     *            천단위 콤마(,) 추가 여부
     * @param trim
     *            사이즈 '0'인 단위 제외 여부.
     * @return
     *
     * @since 2021. 11. 4.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @see BinaryDataUnit#convert(long, BinaryDataUnit, boolean)
     */
    public static String binaryStorageAlsoSubUnitAsString(long bytes, BinaryDataUnit unit, boolean pretty, boolean trim) {
        return binaryStorageAlsoSubUnitAsString(bytes, BinaryDataUnit.BYTE, unit, pretty, trim);
    }

    /**
     * 데이터 크기를 주어진 단위에 맞게 변환하여 제공합니다. (하위 단위 미포함) <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 4.		박준홍			최초 작성
     * </pre>
     *
     * @param bytes
     *            데이터 크기 (단위: byte)
     * @param unit
     *            변환 단위
     * @return
     *
     * @since 2021. 11. 4.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @see BinaryDataUnit#convert(long, BinaryDataUnit)
     */
    public static String binaryStorageAsString(long bytes, BinaryDataUnit unit) {
        return binaryStorageAsString(bytes, BinaryDataUnit.BYTE, unit, true, true);
    }

    /**
     * 데이터 크기를 주어진 단위에 맞게 변환하여 제공합니다. (하위 단위 미포함) <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 4.		박준홍			최초 작성
     * </pre>
     *
     * @param size
     *            데이터 크기
     * @param srcUnit
     *            변환할 데이터 단위
     * @param dstUnit
     *            변환 단위
     * @param pretty
     *            천단위 콤마(,) 추가 여부
     * @param attachUnitStr
     *            단위 문자열 추가 여부
     * @return
     *
     * @since 2021. 11. 4.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @see BinaryDataUnit#convert(long, BinaryDataUnit)
     */
    public static String binaryStorageAsString(long size, BinaryDataUnit srcUnit, BinaryDataUnit dstUnit, boolean pretty, boolean attachUnitStr) {
        String val = srcUnit.convert(size, dstUnit).toString();
        if (pretty) {
            val = ADD_COMMA.apply(val);
        }

        return attachUnitStr ? concat(val, " ", dstUnit.get()) : val;
    }

    /**
     * 데이터 크기를 주어진 단위에 맞게 변환하여 제공합니다. (하위 단위 미포함) <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 4.		박준홍			최초 작성
     * </pre>
     *
     * @param bytes
     *            데이터 크기 (단위: byte)
     * @param unit
     *            변환 단위
     * @param pretty
     *            천단위 콤마(,) 추가 여부
     * @return
     *
     * @since 2021. 11. 4.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @see BinaryDataUnit#convert(long, BinaryDataUnit)
     */
    public static String binaryStorageAsString(long bytes, BinaryDataUnit unit, boolean pretty) {
        return binaryStorageAsString(bytes, BinaryDataUnit.BYTE, unit, pretty, true);
    }

    /**
     * 데이터 크기를 주어진 단위에 맞게 변환하여 제공합니다. (하위 단위, 단이 문자열 미포함) <br>
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 5.		박준홍			최초 작성
     * </pre>
     *
     * @param bytes
     *            데이터 크기 (단위: byte)
     * @param unit
     *            변환 단위
     * @param pretty
     *            천단위 콤마(,) 추가 여부
     * @return
     *
     * @since 2021. 11. 5.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static String binaryStorageAsStringNoUnit(long bytes, BinaryDataUnit unit, boolean pretty) {
        return binaryStorageAsString(bytes, BinaryDataUnit.BYTE, unit, pretty, false);
    }

    private static String concat(Object... objs) {
        StringBuffer sb = new StringBuffer();
        for (Object o : objs) {
            sb.append(o.toString());
        }

        return sb.toString();
    }

    /**
     * 데이터 크기를 주어진 단위에 맞게 변환하여 제공합니다. (하위 단위 미포함) <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 4.		박준홍			최초 작성
     * </pre>
     *
     * @param bytes
     *            데이터 크기 (단위: byte)
     * @param unit
     *            변환 단위
     * @return
     *
     * @since 2021. 11. 4.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @see PrefixDataUnit#convert(long, PrefixDataUnit)
     */
    public static BigDecimal convert(long bytes, PrefixDataUnit unit) {
        return PrefixDataUnit.BASE.convert(bytes, unit);
    }

    /**
     * 데이터 크기를 주어진 단위에 맞게 변환하여 제공합니다. (하위 단위 포함) <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 4.		박준홍			최초 작성
     * </pre>
     *
     * @param bytes
     *            데이터 크기 (단위: byte)
     * @param unit
     *            변환 단위
     * @return
     *
     * @since 2021. 11. 4.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @see PrefixDataUnit#convert(long, PrefixDataUnit, boolean)
     */
    public static BigDecimal[] convertAlsoSubUnit(long bytes, PrefixDataUnit unit) {
        return PrefixDataUnit.BASE.convert(bytes, unit, true);
    }

    /**
     * 데이터 크기를 주어진 단위에 맞게 변환하여 제공합니다. (하위 단위 포함) <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 4.		박준홍			최초 작성
     * </pre>
     *
     * @param bytes
     *            데이터 크기 (단위: byte)
     * @param unit
     *            변환 단위
     * @return
     *
     * @since 2021. 11. 4.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @see #convertAlsoSubUnitAsString(long, BinaryDataUnit, boolean, boolean)
     */
    public static String convertAlsoSubUnitAsString(long bytes, PrefixDataUnit unit) {
        return convertAlsoSubUnitAsString(bytes, unit, true, true);
    }

    /**
     * 데이터 크기를 주어진 단위에 맞게 변환하여 제공합니다. (하위 단위 포함) <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 4.		박준홍			최초 작성
     * </pre>
     *
     * @param bytes
     *            데이터 크기 (단위: byte)
     * @param unit
     *            변환 단위
     * @param pretty
     *            천단위 콤마(,) 추가 여부
     * @param trim
     *            사이즈 '0'인 단위 제외 여부.
     * @return
     *
     * @since 2021. 11. 4.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @see BinaryDataUnit#convert(long, BinaryDataUnit, boolean)
     */
    public static String convertAlsoSubUnitAsString(long bytes, PrefixDataUnit unit, boolean pretty, boolean trim) {
        return convertAlsoSubUnitAsString(bytes, PrefixDataUnit.BASE, unit, pretty, trim);
    }

    /**
     * 데이터 크기를 주어진 단위에 맞게 변환하여 제공합니다. (하위 단위 포함) <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 4.		박준홍			최초 작성
     * </pre>
     *
     * @param size
     *            데이터 크기
     * @param srcUnit
     *            변환할 데이터 단위
     * @param dstUnit
     *            변환 단위
     * @param pretty
     *            천단위 콤마(,) 추가 여부
     * @param trim
     *            사이즈 '0'인 단위 제외 여부.
     * @return
     *
     * @since 2021. 11. 4.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @see PrefixDataUnit#convert(long, PrefixDataUnit, boolean)
     */
    public static String convertAlsoSubUnitAsString(long size, PrefixDataUnit srcUnit, PrefixDataUnit dstUnit, boolean pretty, boolean trim) {
        BigDecimal[] values = srcUnit.convert(size, dstUnit, true);
        List<String> s = new ArrayList<>();

        PrefixDataUnit u = dstUnit;
        String val = null;
        for (int i = 0; i < values.length; i++) {
            if (!trim || values[i].compareTo(BigDecimal.ZERO) != 0) {
                val = values[i].toString();
                if (pretty) {
                    val = ADD_COMMA.apply(val);
                }
                s.add(concat(val, " ", u.get()));
            }
            u = u.down();
        }

        return String.join(" ", s);
    }

    /**
     * 데이터 크기를 주어진 단위에 맞게 변환하여 제공합니다. (하위 단위 미포함) <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 4.		박준홍			최초 작성
     * </pre>
     *
     * @param bytes
     *            데이터 크기 (단위: byte)
     * @param unit
     *            변환 단위
     * @return
     *
     * @since 2021. 11. 4.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @see BinaryDataUnit#convert(long, BinaryDataUnit)
     */
    public static String convertAsString(long bytes, PrefixDataUnit unit) {
        return convertAsString(bytes, PrefixDataUnit.BASE, unit, true, true);
    }

    /**
     * 데이터 크기를 주어진 단위에 맞게 변환하여 제공합니다. (하위 단위 미포함) <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 4.		박준홍			최초 작성
     * </pre>
     *
     * @param bytes
     *            데이터 크기 (단위: byte)
     * @param unit
     *            변환 단위
     * @param pretty
     *            천단위 콤마(,) 추가 여부
     * @return
     *
     * @since 2021. 11. 4.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @see BinaryDataUnit#convert(long, BinaryDataUnit)
     */
    public static String convertAsString(long bytes, PrefixDataUnit unit, boolean pretty) {
        return convertAsString(bytes, PrefixDataUnit.BASE, unit, pretty, true);
    }

    /**
     * 데이터 크기를 주어진 단위에 맞게 변환하여 제공합니다. (하위 단위 미포함) <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 4.		박준홍			최초 작성
     * </pre>
     *
     * @param size
     *            데이터 크기
     * @param srcUnit
     *            변환할 데이터 단위
     * @param dstUnit
     *            변환 단위
     * @param pretty
     *            천단위 콤마(,) 추가 여부
     * @param attachUnitStr
     *            단위 문자열 추가 여부
     * @return
     *
     * @since 2021. 11. 4.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @see BinaryDataUnit#convert(long, BinaryDataUnit)
     */
    public static String convertAsString(long size, PrefixDataUnit srcUnit, PrefixDataUnit dstUnit, boolean pretty, boolean attachUnitStr) {
        String val = srcUnit.convert(size, dstUnit).toString();
        if (pretty) {
            val = ADD_COMMA.apply(val);
        }
        return attachUnitStr ? concat(val, " ", dstUnit.get()) : val;
    }

    /**
     * 데이터 크기를 주어진 단위에 맞게 변환하여 제공합니다. (하위 단위, 단이 문자열 미포함) <br>
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 5.     박준홍         최초 작성
     * </pre>
     *
     * @param bytes
     *            데이터 크기 (단위: byte)
     * @param unit
     *            변환 단위
     * @param pretty
     *            천단위 콤마(,) 추가 여부
     * @return
     *
     * @since 2021. 11. 5.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static String convertAsStringNoUnit(long bytes, PrefixDataUnit unit, boolean pretty) {
        return convertAsString(bytes, PrefixDataUnit.BASE, unit, pretty, false);
    }
}
