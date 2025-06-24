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
* 
*
* This file is generated under this project, "open-commons-core".
*
* Date  : 2014. 3. 25. 오전 11:10:46
*
* Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
* 
*/

package open.commons.core.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Map.Entry;

import open.commons.core.exception.AssertionException;

/**
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 *
 * @deprecated 'assert'의 단어적 의미와 구현기능이 상반되는 이유로 사용을 중지합니다.<br>
 *             'assert'의 단어적 의미에 부합하는 기능을 제공하는 {@link AssertUtils2}를 사용하기 바랍니다.ㅏ
 */

public class AssertUtils {

    private static void assert0(Class<? extends RuntimeException> class_, String msg) throws RuntimeException {
        try {
            Constructor<? extends RuntimeException> cons = class_ != null ? class_.getConstructor(String.class) : AssertionException.class.getConstructor(String.class);
            throw cons.newInstance(msg);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 
     * @param descendant
     * @param ancestor
     * @param occurExeption
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void assertClass(Class<?> descendant, Class<?> ancestor) {
        AssertUtils2.assertClass(null, descendant, ancestor, AssertionException.class);
    }

    /**
     * 
     * @param descendant
     * @param ancestor
     * @param occurExeption
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void assertClass(Class<?> descendant, Class<?> ancestor, Class<? extends RuntimeException> occurExeption) {
        AssertUtils2.assertClass(null, descendant, ancestor, occurExeption);
    }

    /**
     * 
     * @param descendant
     * @param ancestor
     * @param occurExeption
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void assertClass(String assertMsg, Class<?> descendant, Class<?> ancestor) {
        AssertUtils2.assertClass(assertMsg, descendant, ancestor, AssertionException.class);
    }

    /**
     * 
     * @param descendant
     * @param ancestor
     * @param occurExeption
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void assertClass(String assertMsg, Class<?> descendant, Class<?> ancestor, Class<? extends RuntimeException> occurExeption) {
        AssertUtils2.assertClass(assertMsg, descendant, ancestor, occurExeption);
    }

    /**
     * 
     * @param arg1
     * @param arg2
     * @param occurExeption
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void assertEqual(Object arg1, Object arg2) {
        AssertUtils2.assertNotEqual(null, arg1, arg2, AssertionException.class);
    }

    /**
     * 
     * @param arg1
     * @param arg2
     * @param occurExeption
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void assertEqual(Object arg1, Object arg2, Class<? extends RuntimeException> occurExeption) {
        AssertUtils2.assertNotEqual(null, arg1, arg2, occurExeption);
    }

    /**
     * 
     * @param arg1
     * @param arg2
     * @param occurExeption
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void assertEqual(String assertMsg, Object arg1, Object arg2) {
        AssertUtils2.assertNotEqual(assertMsg, arg1, arg2, AssertionException.class);
    }

    /**
     * 
     * @param arg1
     * @param arg2
     * @param occurExeption
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void assertEqual(String assertMsg, Object arg1, Object arg2, Class<? extends RuntimeException> occurExeption) {
        AssertUtils2.assertNotEqual(assertMsg, arg1, arg2, occurExeption);
    }

    public static void assertFalse(boolean bool) {
        AssertUtils2.assertTrue(null, bool, AssertionException.class);
    }

    /**
     * 
     * @param bool
     * @param occurExeption
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void assertFalse(boolean bool, Class<? extends RuntimeException> occurExeption) {
        AssertUtils2.assertTrue(null, bool, occurExeption);
    }

    public static void assertFalse(String assertMsg, boolean bool) {
        AssertUtils2.assertTrue(assertMsg, bool, AssertionException.class);
    }

    /**
     * 
     * @param bool
     * @param occurExeption
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void assertFalse(String assertMsg, boolean bool, Class<? extends RuntimeException> occurExeption) {
        AssertUtils2.assertTrue(assertMsg, bool, occurExeption);
    }

    public static void assertInterface(Object object) {
        AssertUtils2.assertNotInterface(null, object, AssertionException.class);
    }

    public static void assertInterface(Object object, Class<? extends RuntimeException> occurExeption) {
        AssertUtils2.assertNotInterface(null, object, occurExeption);
    }

    public static void assertInterface(String assertMsg, Object object) {
        AssertUtils2.assertNotInterface(assertMsg, object, AssertionException.class);
    }

    public static void assertInterface(String assertMsg, Object object, Class<? extends RuntimeException> occurExeption) {
        AssertUtils2.assertNotInterface(assertMsg, object, occurExeption);
    }

    public static void assertMapNull(Map<?, ?> map) {
        assertMapNull(null, map, null);
    }

    public static void assertMapNull(Map<?, ?> map, Class<? extends RuntimeException> occurException) {
        assertMapNull(null, map, occurException);
    }

    public static void assertMapNull(String assertMsg, Map<?, ?> map) {
        assertMapNull(assertMsg, map, null);
    }

    public static void assertMapNull(String assertMsg, Map<?, ?> map, Class<? extends RuntimeException> occurException) {
        assertNull("The map MUST NOT be null. map: null", map);

        Object key = null;
        Object value = null;
        for (Entry<?, ?> entry : map.entrySet()) {
            key = entry.getKey();
            value = entry.getValue();
            assertNulls("Neither key and value MUST be null. key: " + key + ", value: " + value + ", map: " + map + msg0(assertMsg), key, value);
        }
    }

    public static void assertNull(Object object) {
        AssertUtils2.assertNotNull(null, object, null);
    }

    public static void assertNull(Object object, Class<? extends RuntimeException> occurException) {
        AssertUtils2.assertNotNull(null, object, occurException);
    }

    public static void assertNull(String assertMsg, Object object) {
        AssertUtils2.assertNotNull(assertMsg, object, null);
    }

    public static void assertNull(String assertMsg, Object object, Class<? extends RuntimeException> occurException) {
        AssertUtils2.assertNotNull(assertMsg, object, occurException);
    }

    public static void assertNulls(Class<? extends RuntimeException> occurException, Object... objects) {
        AssertUtils2.assertNotNulls(null, occurException, objects);
    }

    public static void assertNulls(Object... objects) {
        AssertUtils2.assertNotNulls(null, null, objects);
    }

    public static void assertNulls(String assertMsg, Class<? extends RuntimeException> occurException, Object... objects) {
        AssertUtils2.assertNotNulls(assertMsg, occurException, objects);
    }

    public static void assertNulls(String assertMsg, Object... objects) {
        AssertUtils2.assertNotNulls(assertMsg, objects);
    }

    public static void assertTrue(boolean bool) {
        AssertUtils2.assertFalse(null, bool, AssertionException.class);
    }

    /**
     * 
     * @param bool
     * @param occurExeption
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void assertTrue(boolean bool, Class<? extends RuntimeException> occurExeption) {
        AssertUtils2.assertFalse(null, bool, occurExeption);
    }

    public static void assertTrue(String assertMsg, boolean bool) {
        AssertUtils2.assertFalse(assertMsg, bool, AssertionException.class);
    }

    /**
     * 
     * @param bool
     * @param occurExeption
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void assertTrue(String assertMsg, boolean bool, Class<? extends RuntimeException> occurExeption) {
        AssertUtils2.assertFalse(assertMsg, bool, occurExeption);
    }

    private static String msg0(String assertMsg) {
        return assertMsg != null ? " (" + assertMsg + ")" : "";
    }
}
