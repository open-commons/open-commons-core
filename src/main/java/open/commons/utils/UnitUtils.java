/*
 * Copyright 2021 Park Jun-Hong_(parkjunhong77/google/com)
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
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package open.commons.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import open.commons.util.MemoryUnit;

/**
 * 단위 관련 기능을 제공.
 * 
 * @since 2021. 11. 4.
 * @version 1.8.0
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
public class UnitUtils {

    /**
     * 천단위마다 콤마(,) 추가
     * 
     * @param val
     *            숫자 문자열
     * @return 천단위마다 콤마(,)가 추가된 문자열.
     */
    private static final Function<String, String> ADD_COMMA = val -> val.replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");

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

    public static void main(String[] args) {
        test_memory();
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
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * 
     * @see MemoryUnit#convert(long, MemoryUnit)
     */
    public static BigDecimal memory(long bytes, MemoryUnit unit) {
        return MemoryUnit.Byte.convert(bytes, unit);
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
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * 
     * @see MemoryUnit#convert(long, MemoryUnit, boolean)
     */
    public static BigDecimal[] memoryAlsoSubUnit(long bytes, MemoryUnit unit) {
        return MemoryUnit.Byte.convert(bytes, unit, true);
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
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * 
     * @see MemoryUnit#convert(long, MemoryUnit)
     */
    public static String memoryString(long bytes, MemoryUnit unit) {
        return memoryString(bytes, MemoryUnit.Byte, unit, true);
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
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * 
     * @see MemoryUnit#convert(long, MemoryUnit)
     */
    public static String memoryString(long bytes, MemoryUnit unit, boolean pretty) {
        return memoryString(bytes, MemoryUnit.Byte, unit, pretty);
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
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * 
     * @see MemoryUnit#convert(long, MemoryUnit)
     */
    public static String memoryString(long size, MemoryUnit srcUnit, MemoryUnit dstUnit, boolean pretty) {
        String val = srcUnit.convert(size, dstUnit).toString();
        if (pretty) {
            val = ADD_COMMA.apply(val);
        }
        return concat(val, dstUnit.get());
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
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @see #memoryStringAlsoSubUnit(long, MemoryUnit, boolean, boolean)
     */
    public static String memoryStringAlsoSubUnit(long bytes, MemoryUnit unit) {
        return memoryStringAlsoSubUnit(bytes, unit, true, true);
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
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @see MemoryUnit#convert(long, MemoryUnit, boolean)
     */
    public static String memoryStringAlsoSubUnit(long bytes, MemoryUnit unit, boolean pretty, boolean trim) {
        return memoryStringAlsoSubUnit(bytes, MemoryUnit.Byte, unit, pretty, trim);
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
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @see MemoryUnit#convert(long, MemoryUnit, boolean)
     */
    public static String memoryStringAlsoSubUnit(long size, MemoryUnit srcUnit, MemoryUnit dstUnit, boolean pretty, boolean trim) {
        BigDecimal[] values = srcUnit.convert(size, dstUnit, true);
        List<String> s = new ArrayList<>();

        MemoryUnit u = dstUnit;
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

    static void test_memory() {
        MemoryUnit memUnit = MemoryUnit.Byte;
        long memSize = 1 * 1024 * 1024 * 1024;

        System.out.printf("%,d %s -> %s => %s\n", memSize, memUnit.get(), MemoryUnit.Byte.get(), memUnit.convert(memSize, MemoryUnit.Byte).toString());
        System.out.printf("%,d %s -> %s => %s\n", memSize, memUnit.get(), MemoryUnit.KByte.get(), memUnit.convert(memSize, MemoryUnit.KByte).toString());
        System.out.printf("%,d %s -> %s => %,.30f\n", memSize, memUnit.get(), MemoryUnit.KByte.get(), memUnit.convert(memSize, MemoryUnit.KByte).doubleValue());
        System.out.printf("%,d %s -> %s => %s\n", memSize, memUnit.get(), MemoryUnit.MByte.get(), memUnit.convert(memSize, MemoryUnit.MByte).toString());
        System.out.printf("%,d %s -> %s => %,.30f\n", memSize, memUnit.get(), MemoryUnit.MByte.get(), memUnit.convert(memSize, MemoryUnit.MByte).doubleValue());
        System.out.printf("%,d %s -> %s => %s\n", memSize, memUnit.get(), MemoryUnit.GByte.get(), memUnit.convert(memSize, MemoryUnit.GByte).toString());
        System.out.printf("%,d %s -> %s => %,.30f\n", memSize, memUnit.get(), MemoryUnit.GByte.get(), memUnit.convert(memSize, MemoryUnit.GByte).doubleValue());
        System.out.printf("%,d %s -> %s => %s\n", memSize, memUnit.get(), MemoryUnit.TByte.get(), memUnit.convert(memSize, MemoryUnit.TByte).toString());
        System.out.printf("%,d %s -> %s => %,.30f\n", memSize, memUnit.get(), MemoryUnit.TByte.get(), memUnit.convert(memSize, MemoryUnit.TByte).doubleValue());
        System.out.printf("%,d %s -> %s => %s\n", memSize, memUnit.get(), MemoryUnit.PByte.get(), memUnit.convert(memSize, MemoryUnit.PByte).toString());
        System.out.printf("%,d %s -> %s => %,.30f\n", memSize, memUnit.get(), MemoryUnit.PByte.get(), memUnit.convert(memSize, MemoryUnit.PByte).doubleValue());
        System.out.printf("%,d %s -> %s => %s\n", memSize, memUnit.get(), MemoryUnit.EByte.get(), memUnit.convert(memSize, MemoryUnit.EByte).toString());
        System.out.printf("%,d %s -> %s => %,.30f\n", memSize, memUnit.get(), MemoryUnit.EByte.get(), memUnit.convert(memSize, MemoryUnit.EByte).doubleValue());
        System.out.printf("%,d %s -> %s => %s\n", memSize, memUnit.get(), MemoryUnit.ZByte.get(), memUnit.convert(memSize, MemoryUnit.ZByte).toString());
        System.out.printf("%,d %s -> %s => %,.30f\n", memSize, memUnit.get(), MemoryUnit.ZByte.get(), memUnit.convert(memSize, MemoryUnit.ZByte).doubleValue());
        System.out.printf("%,d %s -> %s => %s\n", memSize, memUnit.get(), MemoryUnit.YByte.get(), memUnit.convert(memSize, MemoryUnit.YByte).toString());
        System.out.printf("%,d %s -> %s => %,.100f\n", memSize, memUnit.get(), MemoryUnit.YByte.get(), memUnit.convert(memSize, MemoryUnit.YByte).doubleValue());

        // *--
        memSize = MemoryUnit.GByte.convert(32, MemoryUnit.Byte).longValueExact();
        System.out.printf("%,d >> %s -> %s => %,d\n", memSize, MemoryUnit.Byte.get(), MemoryUnit.MByte.get(), memory(memSize, MemoryUnit.MByte).longValueExact());
        System.out.printf("%,d >> %s -> %s => %s\n", memSize, MemoryUnit.Byte.get(), MemoryUnit.MByte.get(), memoryString(memSize, MemoryUnit.MByte, true));
        // memSize = 12345678;
        System.out.printf("%,d >> %s -> %s => %s\n", memSize, MemoryUnit.Byte.get(), MemoryUnit.KByte.get(), memoryStringAlsoSubUnit(memSize, MemoryUnit.KByte));
        System.out.printf("%,d >> %s -> %s => %s\n", memSize, MemoryUnit.Byte.get(), MemoryUnit.KByte.get(), memoryStringAlsoSubUnit(memSize, MemoryUnit.KByte, true, false));

        // *--
        System.out.printf("%,d >> %s -> %s => %,.30f\n", 10, MemoryUnit.GByte.get(), MemoryUnit.KByte.get(), MemoryUnit.GByte.convert(10, MemoryUnit.KByte).doubleValue());

    }
}
