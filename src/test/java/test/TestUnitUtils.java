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
 * Date  : 2021. 11. 5. 오후 2:00:33
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package test;

import open.commons.core.util.BinaryDataUnit;
import open.commons.core.util.PrefixDataUnit;
import open.commons.core.utils.UnitUtils;

/**
 * 
 * @since 2021. 11. 5.
 * @version _._._
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
public class TestUnitUtils {

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 5.		박준홍			최초 작성
     * </pre>
     *
     *
     * @since 2021. 11. 5.
     * @version _._._
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public TestUnitUtils() {
    }

    public static void main(String[] args) {
        test_binary();
        test_prefix();
    }

    static void test_binary() {
        BinaryDataUnit srcUnit = BinaryDataUnit.BYTE;
        long srcSize = 1 * 1024 * 1024 * 1024;

        System.out.printf("%,d %s -> %s => %s\n", srcSize, srcUnit.get(), BinaryDataUnit.BYTE.get(), srcUnit.convert(srcSize, BinaryDataUnit.BYTE).toString());
        System.out.printf("%,d %s -> %s => %s\n", srcSize, srcUnit.get(), BinaryDataUnit.KIBI.get(), srcUnit.convert(srcSize, BinaryDataUnit.KIBI).toString());
        System.out.printf("%,d %s -> %s => %,.30f\n", srcSize, srcUnit.get(), BinaryDataUnit.KIBI.get(), srcUnit.convert(srcSize, BinaryDataUnit.KIBI).doubleValue());
        System.out.printf("%,d %s -> %s => %s\n", srcSize, srcUnit.get(), BinaryDataUnit.MEBI.get(), srcUnit.convert(srcSize, BinaryDataUnit.MEBI).toString());
        System.out.printf("%,d %s -> %s => %,.30f\n", srcSize, srcUnit.get(), BinaryDataUnit.MEBI.get(), srcUnit.convert(srcSize, BinaryDataUnit.MEBI).doubleValue());
        System.out.printf("%,d %s -> %s => %s\n", srcSize, srcUnit.get(), BinaryDataUnit.GIBI.get(), srcUnit.convert(srcSize, BinaryDataUnit.GIBI).toString());
        System.out.printf("%,d %s -> %s => %,.30f\n", srcSize, srcUnit.get(), BinaryDataUnit.GIBI.get(), srcUnit.convert(srcSize, BinaryDataUnit.GIBI).doubleValue());
        System.out.printf("%,d %s -> %s => %s\n", srcSize, srcUnit.get(), BinaryDataUnit.TEBI.get(), srcUnit.convert(srcSize, BinaryDataUnit.TEBI).toString());
        System.out.printf("%,d %s -> %s => %,.30f\n", srcSize, srcUnit.get(), BinaryDataUnit.TEBI.get(), srcUnit.convert(srcSize, BinaryDataUnit.TEBI).doubleValue());
        System.out.printf("%,d %s -> %s => %s\n", srcSize, srcUnit.get(), BinaryDataUnit.PEBI.get(), srcUnit.convert(srcSize, BinaryDataUnit.PEBI).toString());
        System.out.printf("%,d %s -> %s => %,.30f\n", srcSize, srcUnit.get(), BinaryDataUnit.PEBI.get(), srcUnit.convert(srcSize, BinaryDataUnit.PEBI).doubleValue());
        System.out.printf("%,d %s -> %s => %s\n", srcSize, srcUnit.get(), BinaryDataUnit.EXBI.get(), srcUnit.convert(srcSize, BinaryDataUnit.EXBI).toString());
        System.out.printf("%,d %s -> %s => %,.30f\n", srcSize, srcUnit.get(), BinaryDataUnit.EXBI.get(), srcUnit.convert(srcSize, BinaryDataUnit.EXBI).doubleValue());
        System.out.printf("%,d %s -> %s => %s\n", srcSize, srcUnit.get(), BinaryDataUnit.ZIBI.get(), srcUnit.convert(srcSize, BinaryDataUnit.ZIBI).toString());
        System.out.printf("%,d %s -> %s => %,.30f\n", srcSize, srcUnit.get(), BinaryDataUnit.ZIBI.get(), srcUnit.convert(srcSize, BinaryDataUnit.ZIBI).doubleValue());
        System.out.printf("%,d %s -> %s => %s\n", srcSize, srcUnit.get(), BinaryDataUnit.YOBI.get(), srcUnit.convert(srcSize, BinaryDataUnit.YOBI).toString());
        System.out.printf("%,d %s -> %s => %,.100f\n", srcSize, srcUnit.get(), BinaryDataUnit.YOBI.get(), srcUnit.convert(srcSize, BinaryDataUnit.YOBI).doubleValue());

        // *--
        srcSize = BinaryDataUnit.GIBI.convert(32, BinaryDataUnit.BYTE).longValueExact();
        System.out.printf("%,d >> %s -> %s => %,d\n", srcSize, BinaryDataUnit.BYTE.get(), BinaryDataUnit.MEBI.get(),
                UnitUtils.binaryStorage(srcSize, BinaryDataUnit.MEBI).longValueExact());
        System.out.printf("%,d >> %s -> %s => %s\n", srcSize, BinaryDataUnit.BYTE.get(), BinaryDataUnit.MEBI.get(),
                UnitUtils.binaryStorageAsString(srcSize, BinaryDataUnit.MEBI, true));
        // memSize = 12345678;
        System.out.printf("%,d >> %s -> %s => %s\n", srcSize, BinaryDataUnit.BYTE.get(), BinaryDataUnit.KIBI.get(),
                UnitUtils.binaryStorageAlsoSubUnitAsString(srcSize, BinaryDataUnit.KIBI));
        System.out.printf("%,d >> %s -> %s => %s\n", srcSize, BinaryDataUnit.BYTE.get(), BinaryDataUnit.KIBI.get(),
                UnitUtils.binaryStorageAlsoSubUnitAsString(srcSize, BinaryDataUnit.KIBI, true, false));

        // *--
        System.out.printf("%,d >> %s -> %s => %,.30f\n", 10, BinaryDataUnit.GIBI.get(), BinaryDataUnit.KIBI.get(),
                BinaryDataUnit.GIBI.convert(10, BinaryDataUnit.KIBI).doubleValue());

    }

    static void test_prefix() {
        PrefixDataUnit srcUnit = PrefixDataUnit.TERA;
        long srcSize = 1 * 1000 * 1000 * 1000;

        System.out.printf("%,d %s -> %s => %s\n", srcSize, srcUnit.get(), PrefixDataUnit.BASE.get(), srcUnit.convert(srcSize, PrefixDataUnit.BASE).toString());
        System.out.printf("%,d %s -> %s => %s\n", srcSize, srcUnit.get(), PrefixDataUnit.KILO.get(), srcUnit.convert(srcSize, PrefixDataUnit.KILO).toString());
        System.out.printf("%,d %s -> %s => %,.30f\n", srcSize, srcUnit.get(), PrefixDataUnit.KILO.get(), srcUnit.convert(srcSize, PrefixDataUnit.KILO).doubleValue());
        System.out.printf("%,d %s -> %s => %s\n", srcSize, srcUnit.get(), PrefixDataUnit.MEGA.get(), srcUnit.convert(srcSize, PrefixDataUnit.MEGA).toString());
        System.out.printf("%,d %s -> %s => %,.30f\n", srcSize, srcUnit.get(), PrefixDataUnit.MEGA.get(), srcUnit.convert(srcSize, PrefixDataUnit.MEGA).doubleValue());
        System.out.printf("%,d %s -> %s => %s\n", srcSize, srcUnit.get(), PrefixDataUnit.GIGA.get(), srcUnit.convert(srcSize, PrefixDataUnit.GIGA).toString());
        System.out.printf("%,d %s -> %s => %,.30f\n", srcSize, srcUnit.get(), PrefixDataUnit.GIGA.get(), srcUnit.convert(srcSize, PrefixDataUnit.GIGA).doubleValue());
        System.out.printf("%,d %s -> %s => %s\n", srcSize, srcUnit.get(), PrefixDataUnit.TERA.get(), srcUnit.convert(srcSize, PrefixDataUnit.TERA).toString());
        System.out.printf("%,d %s -> %s => %,.30f\n", srcSize, srcUnit.get(), PrefixDataUnit.TERA.get(), srcUnit.convert(srcSize, PrefixDataUnit.TERA).doubleValue());
        System.out.printf("%,d %s -> %s => %s\n", srcSize, srcUnit.get(), PrefixDataUnit.PETA.get(), srcUnit.convert(srcSize, PrefixDataUnit.PETA).toString());
        System.out.printf("%,d %s -> %s => %,.30f\n", srcSize, srcUnit.get(), PrefixDataUnit.PETA.get(), srcUnit.convert(srcSize, PrefixDataUnit.PETA).doubleValue());
        System.out.printf("%,d %s -> %s => %s\n", srcSize, srcUnit.get(), PrefixDataUnit.EXA.get(), srcUnit.convert(srcSize, PrefixDataUnit.EXA).toString());
        System.out.printf("%,d %s -> %s => %,.30f\n", srcSize, srcUnit.get(), PrefixDataUnit.EXA.get(), srcUnit.convert(srcSize, PrefixDataUnit.EXA).doubleValue());
        System.out.printf("%,d %s -> %s => %s\n", srcSize, srcUnit.get(), PrefixDataUnit.ZETTA.get(), srcUnit.convert(srcSize, PrefixDataUnit.ZETTA).toString());
        System.out.printf("%,d %s -> %s => %,.30f\n", srcSize, srcUnit.get(), PrefixDataUnit.ZETTA.get(), srcUnit.convert(srcSize, PrefixDataUnit.ZETTA).doubleValue());
        System.out.printf("%,d %s -> %s => %s\n", srcSize, srcUnit.get(), PrefixDataUnit.YOTTA.get(), srcUnit.convert(srcSize, PrefixDataUnit.YOTTA).toString());
        System.out.printf("%,d %s -> %s => %,.100f\n", srcSize, srcUnit.get(), PrefixDataUnit.YOTTA.get(), srcUnit.convert(srcSize, PrefixDataUnit.YOTTA).doubleValue());

        // *--
        srcSize = PrefixDataUnit.GIGA.convert(32, PrefixDataUnit.BASE).longValueExact();
        System.out.printf("%,d >> %s -> %s => %,d\n", srcSize, PrefixDataUnit.BASE.get(), PrefixDataUnit.MEGA.get(),
                UnitUtils.convert(srcSize, PrefixDataUnit.MEGA).longValueExact());
        System.out.printf("%,d >> %s -> %s => %s\n", srcSize, PrefixDataUnit.BASE.get(), PrefixDataUnit.MEGA.get(), UnitUtils.convertAsString(srcSize, PrefixDataUnit.MEGA, true));
        // memSize = 12345678;
        System.out.printf("%,d >> %s -> %s => %s\n", srcSize, PrefixDataUnit.BASE.get(), PrefixDataUnit.KILO.get(),
                UnitUtils.convertAlsoSubUnitAsString(srcSize, PrefixDataUnit.KILO));
        System.out.printf("%,d >> %s -> %s => %s\n", srcSize, PrefixDataUnit.BASE.get(), PrefixDataUnit.KILO.get(),
                UnitUtils.convertAlsoSubUnitAsString(srcSize, PrefixDataUnit.KILO, true, false));

        // *--
        System.out.printf("%,d >> %s -> %s => %,.30f\n", 10, PrefixDataUnit.GIGA.get(), PrefixDataUnit.KILO.get(),
                PrefixDataUnit.GIGA.convert(10, PrefixDataUnit.KILO).doubleValue());

    }

}
