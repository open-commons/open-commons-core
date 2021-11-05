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

package open.commons.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import open.commons.util.DataStorageUnit;

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

    private static String concat(Object... objs) {
        StringBuffer sb = new StringBuffer();
        for (Object o : objs) {
            sb.append(o.toString());
        }

        return sb.toString();
    }

    /**
     * 메모리 값을 주어진 단위로 변환하여 제공한다. (하위 단위 미포함) <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 4.		박준홍			최초 작성
     * </pre>
     *
     * @param bytes
     *            메모리 크기 (단위: Byte)
     * @param unit
     *            변환 단위
     * @return
     *
     * @since 2021. 11. 4.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @see DataStorageUnit#convert(long, DataStorageUnit)
     */
    public static BigDecimal dataStorage(long bytes, DataStorageUnit unit) {
        return DataStorageUnit.Byte.convert(bytes, unit);
    }

    /**
     * 메모리 값을 주어진 단위로 변환하여 제공한다. (하위 단위 포함) <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 4.		박준홍			최초 작성
     * </pre>
     *
     * @param bytes
     *            메모리 크기 (단위: Byte)
     * @param unit
     *            변환 단위
     * @return
     *
     * @since 2021. 11. 4.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @see DataStorageUnit#convert(long, DataStorageUnit, boolean)
     */
    public static BigDecimal[] dataStorageAlsoSubUnit(long bytes, DataStorageUnit unit) {
        return DataStorageUnit.Byte.convert(bytes, unit, true);
    }

    /**
     * 메모리 값을 주어진 단위로 변환하여 제공한다. (하위 단위 포함) <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 4.		박준홍			최초 작성
     * </pre>
     *
     * @param bytes
     *            메모리 크기 (단위: Byte)
     * @param unit
     *            변환 단위
     * @return
     *
     * @since 2021. 11. 4.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @see #dataStorageAlsoSubUnitAsString(long, DataStorageUnit, boolean, boolean)
     */
    public static String dataStorageAlsoSubUnitAsString(long bytes, DataStorageUnit unit) {
        return dataStorageAlsoSubUnitAsString(bytes, unit, true, true);
    }

    /**
     * 메모리 값을 주어진 단위로 변환하여 제공한다. (하위 단위 포함) <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 4.		박준홍			최초 작성
     * </pre>
     *
     * @param bytes
     *            메모리 크기 (단위: Byte)
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
     * @see DataStorageUnit#convert(long, DataStorageUnit, boolean)
     */
    public static String dataStorageAlsoSubUnitAsString(long bytes, DataStorageUnit unit, boolean pretty, boolean trim) {
        return dataStorageAlsoSubUnitAsString(bytes, DataStorageUnit.Byte, unit, pretty, trim);
    }

    /**
     * 메모리 값을 주어진 단위로 변환하여 제공한다. (하위 단위 포함) <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 4.		박준홍			최초 작성
     * </pre>
     *
     * @param size
     *            메모리 크기
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
     * @see DataStorageUnit#convert(long, DataStorageUnit, boolean)
     */
    public static String dataStorageAlsoSubUnitAsString(long size, DataStorageUnit srcUnit, DataStorageUnit dstUnit, boolean pretty, boolean trim) {
        BigDecimal[] values = srcUnit.convert(size, dstUnit, true);
        List<String> s = new ArrayList<>();

        DataStorageUnit u = dstUnit;
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
     * 메모리 값을 주어진 단위로 변환하여 제공한다. (하위 단위 미포함) <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 4.		박준홍			최초 작성
     * </pre>
     *
     * @param bytes
     *            메모리 크기 (단위: Byte)
     * @param unit
     *            변환 단위
     * @return
     *
     * @since 2021. 11. 4.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @see DataStorageUnit#convert(long, DataStorageUnit)
     */
    public static String dataStorageAsString(long bytes, DataStorageUnit unit) {
        return dataStorageAsString(bytes, DataStorageUnit.Byte, unit, true);
    }

    /**
     * 메모리 값을 주어진 단위로 변환하여 제공한다. (하위 단위 미포함) <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 4.		박준홍			최초 작성
     * </pre>
     *
     * @param bytes
     *            메모리 크기 (단위: Byte)
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
     * @see DataStorageUnit#convert(long, DataStorageUnit)
     */
    public static String dataStorageAsString(long bytes, DataStorageUnit unit, boolean pretty) {
        return dataStorageAsString(bytes, DataStorageUnit.Byte, unit, pretty);
    }

    /**
     * 메모리 값을 주어진 단위로 변환하여 제공한다. (하위 단위 미포함) <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 4.		박준홍			최초 작성
     * </pre>
     *
     * @param size
     *            메모리 크기
     * @param srcUnit
     *            변환할 데이터 단위
     * @param dstUnit
     *            변환 단위
     * @param pretty
     *            천단위 콤마(,) 추가 여부
     * @return
     *
     * @since 2021. 11. 4.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @see DataStorageUnit#convert(long, DataStorageUnit)
     */
    public static String dataStorageAsString(long size, DataStorageUnit srcUnit, DataStorageUnit dstUnit, boolean pretty) {
        String val = srcUnit.convert(size, dstUnit).toString();
        if (pretty) {
            val = ADD_COMMA.apply(val);
        }
        return concat(val, " ", dstUnit.get());
    }

    public static void main(String[] args) {
        test_memory();
    }

    static void test_memory() {
        DataStorageUnit memUnit = DataStorageUnit.Byte;
        long memSize = 1 * 1024 * 1024 * 1024;

        System.out.printf("%,d %s -> %s => %s\n", memSize, memUnit.get(), DataStorageUnit.Byte.get(), memUnit.convert(memSize, DataStorageUnit.Byte).toString());
        System.out.printf("%,d %s -> %s => %s\n", memSize, memUnit.get(), DataStorageUnit.KByte.get(), memUnit.convert(memSize, DataStorageUnit.KByte).toString());
        System.out.printf("%,d %s -> %s => %,.30f\n", memSize, memUnit.get(), DataStorageUnit.KByte.get(), memUnit.convert(memSize, DataStorageUnit.KByte).doubleValue());
        System.out.printf("%,d %s -> %s => %s\n", memSize, memUnit.get(), DataStorageUnit.MByte.get(), memUnit.convert(memSize, DataStorageUnit.MByte).toString());
        System.out.printf("%,d %s -> %s => %,.30f\n", memSize, memUnit.get(), DataStorageUnit.MByte.get(), memUnit.convert(memSize, DataStorageUnit.MByte).doubleValue());
        System.out.printf("%,d %s -> %s => %s\n", memSize, memUnit.get(), DataStorageUnit.GByte.get(), memUnit.convert(memSize, DataStorageUnit.GByte).toString());
        System.out.printf("%,d %s -> %s => %,.30f\n", memSize, memUnit.get(), DataStorageUnit.GByte.get(), memUnit.convert(memSize, DataStorageUnit.GByte).doubleValue());
        System.out.printf("%,d %s -> %s => %s\n", memSize, memUnit.get(), DataStorageUnit.TByte.get(), memUnit.convert(memSize, DataStorageUnit.TByte).toString());
        System.out.printf("%,d %s -> %s => %,.30f\n", memSize, memUnit.get(), DataStorageUnit.TByte.get(), memUnit.convert(memSize, DataStorageUnit.TByte).doubleValue());
        System.out.printf("%,d %s -> %s => %s\n", memSize, memUnit.get(), DataStorageUnit.PByte.get(), memUnit.convert(memSize, DataStorageUnit.PByte).toString());
        System.out.printf("%,d %s -> %s => %,.30f\n", memSize, memUnit.get(), DataStorageUnit.PByte.get(), memUnit.convert(memSize, DataStorageUnit.PByte).doubleValue());
        System.out.printf("%,d %s -> %s => %s\n", memSize, memUnit.get(), DataStorageUnit.EByte.get(), memUnit.convert(memSize, DataStorageUnit.EByte).toString());
        System.out.printf("%,d %s -> %s => %,.30f\n", memSize, memUnit.get(), DataStorageUnit.EByte.get(), memUnit.convert(memSize, DataStorageUnit.EByte).doubleValue());
        System.out.printf("%,d %s -> %s => %s\n", memSize, memUnit.get(), DataStorageUnit.ZByte.get(), memUnit.convert(memSize, DataStorageUnit.ZByte).toString());
        System.out.printf("%,d %s -> %s => %,.30f\n", memSize, memUnit.get(), DataStorageUnit.ZByte.get(), memUnit.convert(memSize, DataStorageUnit.ZByte).doubleValue());
        System.out.printf("%,d %s -> %s => %s\n", memSize, memUnit.get(), DataStorageUnit.YByte.get(), memUnit.convert(memSize, DataStorageUnit.YByte).toString());
        System.out.printf("%,d %s -> %s => %,.100f\n", memSize, memUnit.get(), DataStorageUnit.YByte.get(), memUnit.convert(memSize, DataStorageUnit.YByte).doubleValue());

        // *--
        memSize = DataStorageUnit.GByte.convert(32, DataStorageUnit.Byte).longValueExact();
        System.out.printf("%,d >> %s -> %s => %,d\n", memSize, DataStorageUnit.Byte.get(), DataStorageUnit.MByte.get(),
                dataStorage(memSize, DataStorageUnit.MByte).longValueExact());
        System.out.printf("%,d >> %s -> %s => %s\n", memSize, DataStorageUnit.Byte.get(), DataStorageUnit.MByte.get(), dataStorageAsString(memSize, DataStorageUnit.MByte, true));
        // memSize = 12345678;
        System.out.printf("%,d >> %s -> %s => %s\n", memSize, DataStorageUnit.Byte.get(), DataStorageUnit.KByte.get(),
                dataStorageAlsoSubUnitAsString(memSize, DataStorageUnit.KByte));
        System.out.printf("%,d >> %s -> %s => %s\n", memSize, DataStorageUnit.Byte.get(), DataStorageUnit.KByte.get(),
                dataStorageAlsoSubUnitAsString(memSize, DataStorageUnit.KByte, true, false));

        // *--
        System.out.printf("%,d >> %s -> %s => %,.30f\n", 10, DataStorageUnit.GByte.get(), DataStorageUnit.KByte.get(),
                DataStorageUnit.GByte.convert(10, DataStorageUnit.KByte).doubleValue());

    }
}
