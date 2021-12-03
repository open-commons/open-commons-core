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

package open.commons.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;

import open.commons.exception.AssertionException;

/**
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
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
        assertClass(null, descendant, ancestor, AssertionException.class);
    }

    /**
     * 
     * @param descendant
     * @param ancestor
     * @param occurExeption
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void assertClass(Class<?> descendant, Class<?> ancestor, Class<? extends RuntimeException> occurExeption) {
        assertClass(null, descendant, ancestor, occurExeption);
    }

    /**
     * 
     * @param descendant
     * @param ancestor
     * @param occurExeption
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void assertClass(String assertMsg, Class<?> descendant, Class<?> ancestor) {
        assertClass(assertMsg, descendant, ancestor, AssertionException.class);
    }

    /**
     * 
     * @param descendant
     * @param ancestor
     * @param occurExeption
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void assertClass(String assertMsg, Class<?> descendant, Class<?> ancestor, Class<? extends RuntimeException> occurExeption) {

        AssertUtils.assertNulls("Neither ancestor and descendant MUST be null.", ancestor, descendant);

        if (!ancestor.isAssignableFrom(descendant.getClass())) {
            assert0(occurExeption, "descendant's type MUST be " + ancestor + " or impelemt " + ancestor + msg0(assertMsg));
        }
    }

    /**
     * 
     * @param arg1
     * @param arg2
     * @param occurExeption
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void assertEqual(Object arg1, Object arg2) {
        assertEqual(null, arg1, arg2, AssertionException.class);
    }

    /**
     * 
     * @param arg1
     * @param arg2
     * @param occurExeption
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void assertEqual(Object arg1, Object arg2, Class<? extends RuntimeException> occurExeption) {
        assertEqual(null, arg1, arg2, occurExeption);
    }

    /**
     * 
     * @param arg1
     * @param arg2
     * @param occurExeption
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void assertEqual(String assertMsg, Object arg1, Object arg2) {
        assertEqual(assertMsg, arg1, arg2, AssertionException.class);
    }

    /**
     * 
     * @param arg1
     * @param arg2
     * @param occurExeption
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void assertEqual(String assertMsg, Object arg1, Object arg2, Class<? extends RuntimeException> occurExeption) {

        if (arg1 == null && arg2 == null) {
            assert0(occurExeption, "The result is 'equal'." + msg0(assertMsg));
        } else if (arg1 != null && arg2 != null && arg1.equals(arg2)) {
            assert0(occurExeption, "The result is 'equal'." + msg0(assertMsg));
        }
    }

    public static void assertFalse(boolean bool) {
        assertFalse(null, bool, AssertionException.class);
    }

    /**
     * 
     * @param bool
     * @param occurExeption
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void assertFalse(boolean bool, Class<? extends RuntimeException> occurExeption) {
        assertFalse(null, bool, occurExeption);
    }

    public static void assertFalse(String assertMsg, boolean bool) {
        assertFalse(assertMsg, bool, AssertionException.class);
    }

    /**
     * 
     * @param bool
     * @param occurExeption
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void assertFalse(String assertMsg, boolean bool, Class<? extends RuntimeException> occurExeption) {
        try {
            if (!bool) {
                assert0(occurExeption, "The result is false." + msg0(assertMsg));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void assertInterface(Object object) {
        assertInterface(null, object, AssertionException.class);
    }

    public static void assertInterface(Object object, Class<? extends RuntimeException> occurExeption) {
        assertInterface(null, object, occurExeption);
    }

    public static void assertInterface(String assertMsg, Object object) {
        assertInterface(assertMsg, object, AssertionException.class);
    }

    public static void assertInterface(String assertMsg, Object object, Class<? extends RuntimeException> occurExeption) {
        assertNull("The object MUST NOT be null. object: null", object);

        Class<?> class_ = object instanceof Class ? (Class<?>) object : object.getClass();

        if (class_.isInterface()) {
            assert0(occurExeption, "The object is a interface." + msg0(assertMsg));

        }
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
        assertNull(null, object, null);
    }

    public static void assertNull(Object object, Class<? extends RuntimeException> occurException) {
        assertNull(null, object, occurException);
    }

    public static void assertNull(String assertMsg, Object object) {
        assertNull(assertMsg, object, null);
    }

    public static void assertNull(String assertMsg, Object object, Class<? extends RuntimeException> occurException) {
        if (object == null) {
            assert0(occurException == null ? NullPointerException.class : occurException, msg0(assertMsg));
        }
    }

    public static void assertNulls(Class<? extends RuntimeException> occurException, Object... objects) {
        assertNulls(null, occurException, objects);
    }

    public static void assertNulls(Object... objects) {
        assertNulls(null, null, objects);
    }

    public static void assertNulls(String assertMsg, Class<? extends RuntimeException> occurException, Object... objects) {
        assertNull(null, objects, null);

        for (Object object : objects) {
            if (object == null) {
                assert0(occurException == null ? NullPointerException.class : occurException, "objects: " + Arrays.toString(objects) + msg0(assertMsg));
            }
        }
    }

    public static void assertNulls(String assertMsg, Object... objects) {
        assertNulls(assertMsg, null, objects);
    }

    public static void assertTrue(boolean bool) {
        assertTrue(null, bool, AssertionException.class);
    }

    /**
     * 
     * @param bool
     * @param occurExeption
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void assertTrue(boolean bool, Class<? extends RuntimeException> occurExeption) {
        assertTrue(null, bool, occurExeption);
    }

    public static void assertTrue(String assertMsg, boolean bool) {
        assertTrue(assertMsg, bool, AssertionException.class);
    }

    /**
     * 
     * @param bool
     * @param occurExeption
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void assertTrue(String assertMsg, boolean bool, Class<? extends RuntimeException> occurExeption) {
        try {
            if (bool) {
                assert0(occurExeption, "The result is true." + msg0(assertMsg));
            }
        } catch (Exception e) {
            throw e;
        }
    }

    private static String msg0(String assertMsg) {
        return assertMsg != null ? " (" + assertMsg + ")" : "";
    }
}
