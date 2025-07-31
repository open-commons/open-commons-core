/*
 * Copyright 2025 Park Jun-Hong_(parkjunhong77@gmail.com)
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
 * Date  : 2025. 6. 24. 오후 1:30:33
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package open.commons.core.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;

import open.commons.core.exception.AssertionException;

/**
 * 'assert'의 단어적 의미에 부합되는 기능을 제공하는 클래스.<br>
 * {@link AssertUtils}가 'assert'의 단어적 의미와 상반되는 기능을 제공하여 더 이상 사용하지 않음.
 * 
 * @since 2025. 6. 24.
 * @version 2.1.0
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
public class AssertUtils2 {

    private AssertUtils2() {
    }

    private static void assert0(Class<? extends RuntimeException> clazz, String msg) throws RuntimeException {
        try {
            Constructor<? extends RuntimeException> cons = clazz != null ? clazz.getConstructor(String.class) : AssertionException.class.getConstructor(String.class);
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
     * @param arg1
     * @param arg2
     * @param occurExeption
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void equals(Object arg1, Object arg2) {
        equals(null, arg1, arg2, AssertionException.class);
    }

    /**
     * 
     * @param arg1
     * @param arg2
     * @param occurExeption
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void equals(Object arg1, Object arg2, Class<? extends RuntimeException> occurExeption) {
        equals(null, arg1, arg2, occurExeption);
    }

    /**
     * 
     * @param arg1
     * @param arg2
     * @param occurExeption
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void equals(String assertMsg, Object arg1, Object arg2) {
        equals(assertMsg, arg1, arg2, AssertionException.class);
    }

    /**
     * 
     * @param arg1
     * @param arg2
     * @param occurExeption
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void equals(String assertMsg, Object arg1, Object arg2, Class<? extends RuntimeException> occurExeption) {
        if (equals_(arg1, arg2)) {
            assert0(occurExeption, "The result MUST be 'equal'." + msg0(assertMsg));
        }
    }

    private static boolean equals_(Object arg1, Object arg2) {
        if (arg1 != null && arg2 != null) {
            return arg1.equals(arg2);
        } else {
            return arg1 == null && arg2 == null;
        }
    }

    /**
     * 
     * @param descendant
     * @param ancestor
     * @param occurExeption
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void isClass(Class<?> descendant, Class<?> ancestor) {
        isClass(null, descendant, ancestor, AssertionException.class);
    }

    /**
     * 
     * @param descendant
     * @param ancestor
     * @param occurExeption
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void isClass(Class<?> descendant, Class<?> ancestor, Class<? extends RuntimeException> occurExeption) {
        isClass(null, descendant, ancestor, occurExeption);
    }

    /**
     * 
     * @param descendant
     * @param ancestor
     * @param occurExeption
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void isClass(String assertMsg, Class<?> descendant, Class<?> ancestor) {
        isClass(assertMsg, descendant, ancestor, AssertionException.class);
    }

    /**
     * 
     * @param descendant
     * @param ancestor
     * @param occurExeption
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void isClass(String assertMsg, Class<?> descendant, Class<?> ancestor, Class<? extends RuntimeException> occurExeption) {

        notNulls("Neither ancestor and descendant MUST be null.", ancestor, descendant);

        if (!ancestor.isAssignableFrom(descendant.getClass())) {
            assert0(occurExeption, "descendant's type MUST be " + ancestor + " or impelemt " + ancestor + msg0(assertMsg));
        }
    }

    public static void isFalse(boolean bool) {
        isFalse(null, bool, AssertionException.class);
    }

    /**
     * 
     * @param bool
     * @param occurExeption
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void isFalse(boolean bool, Class<? extends RuntimeException> occurExeption) {
        isFalse(null, bool, occurExeption);
    }

    public static void isFalse(String assertMsg, boolean bool) {
        isFalse(assertMsg, bool, AssertionException.class);
    }

    /**
     * 
     * @param bool
     * @param occurExeption
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void isFalse(String assertMsg, boolean bool, Class<? extends RuntimeException> occurExeption) {
        try {
            if (bool) {
                assert0(occurExeption, "The result MUST be false." + msg0(assertMsg));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void isInterface(Object object) {
        isInterface(null, object, AssertionException.class);
    }

    public static void isInterface(Object object, Class<? extends RuntimeException> occurExeption) {
        isInterface(null, object, occurExeption);
    }

    public static void isInterface(String assertMsg, Object object) {
        isInterface(assertMsg, object, AssertionException.class);
    }

    public static void isInterface(String assertMsg, Object object, Class<? extends RuntimeException> occurExeption) {
        notNull("The object MUST NOT be null. object: null", object);

        if (!isInterface_(object)) {
            assert0(occurExeption, "The object is MUST be interface." + msg0(assertMsg));
        }
    }

    private static boolean isInterface_(Object object) {
        Class<?> class_ = object instanceof Class ? (Class<?>) object : object.getClass();
        return class_.isInterface();
    }

    public static void isNull(Object object) {
        isNull(null, object, null);
    }

    public static void isNull(Object object, Class<? extends RuntimeException> occurException) {
        isNull(null, object, occurException);
    }

    public static void isNull(String assertMsg, Object object) {
        isNull(assertMsg, object, null);
    }

    public static void isNull(String assertMsg, Object object, Class<? extends RuntimeException> occurException) {
        if (!isNull_(object)) {
            assert0(occurException == null ? NullPointerException.class : occurException, msg0(assertMsg));
        }
    }

    private static boolean isNull_(Object object) {
        return object == null;
    }

    public static void isNulls(Class<? extends RuntimeException> occurException, Object... objects) {
        isNulls(null, occurException, objects);
    }

    public static void isNulls(Object... objects) {
        isNulls(null, null, objects);
    }

    public static void isNulls(String assertMsg, Class<? extends RuntimeException> occurException, Object... objects) {
        isNull(null, objects, null);

        for (Object object : objects) {
            if (!isNull_(object)) {
                assert0(occurException == null ? NullPointerException.class : occurException, "objects: " + Arrays.toString(objects) + msg0(assertMsg));
            }
        }
    }

    public static void isNulls(String assertMsg, Object... objects) {
        isNulls(assertMsg, null, objects);
    }

    public static void isTrue(boolean bool) {
        isTrue(null, bool, AssertionException.class);
    }

    /**
     * 
     * @param bool
     * @param occurExeption
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void isTrue(boolean bool, Class<? extends RuntimeException> occurExeption) {
        isTrue(null, bool, occurExeption);
    }

    public static void isTrue(String assertMsg, boolean bool) {
        isTrue(assertMsg, bool, AssertionException.class);
    }

    /**
     * 
     * @param bool
     * @param occurExeption
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void isTrue(String assertMsg, boolean bool, Class<? extends RuntimeException> occurExeption) {
        try {
            if (!bool) {
                assert0(occurExeption, "The result MUST be true." + msg0(assertMsg));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void mapNotNull(Map<?, ?> map) {
        mapNotNull(null, map, null);
    }

    public static void mapNotNull(Map<?, ?> map, Class<? extends RuntimeException> occurException) {
        mapNotNull(null, map, occurException);
    }

    public static void mapNotNull(String assertMsg, Map<?, ?> map) {
        mapNotNull(assertMsg, map, null);
    }

    public static void mapNotNull(String assertMsg, Map<?, ?> map, Class<? extends RuntimeException> occurException) {
        notNull("The map MUST NOT be null. map: null", map);

        Object key = null;
        Object value = null;
        for (Entry<?, ?> entry : map.entrySet()) {
            key = entry.getKey();
            value = entry.getValue();
            notNulls("Neither key and value MUST be null. key: " + key + ", value: " + value + ", map: " + map + msg0(assertMsg), key, value);
        }
    }

    public static void mapNull(Map<?, ?> map) {
        mapNull(null, map, null);
    }

    public static void mapNull(Map<?, ?> map, Class<? extends RuntimeException> occurException) {
        mapNull(null, map, occurException);
    }

    public static void mapNull(String assertMsg, Map<?, ?> map) {
        mapNull(assertMsg, map, null);
    }

    public static void mapNull(String assertMsg, Map<?, ?> map, Class<? extends RuntimeException> occurException) {
        notNull("The map MUST NOT be null. map: null", map);

        Object key = null;
        Object value = null;
        for (Entry<?, ?> entry : map.entrySet()) {
            key = entry.getKey();
            value = entry.getValue();
            isNulls("key and value MUST be null. key: " + key + ", value: " + value + ", map: " + map + msg0(assertMsg), key, value);
        }
    }

    private static String msg0(String assertMsg) {
        return assertMsg != null ? " (" + assertMsg + ")" : "";
    }

    /**
     * 
     * @param arg1
     * @param arg2
     * @param occurExeption
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void notEquals(Object arg1, Object arg2) {
        notEquals(null, arg1, arg2, AssertionException.class);
    }

    /**
     * 
     * @param arg1
     * @param arg2
     * @param occurExeption
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void notEquals(Object arg1, Object arg2, Class<? extends RuntimeException> occurExeption) {
        notEquals(null, arg1, arg2, occurExeption);
    }

    /**
     * 
     * @param arg1
     * @param arg2
     * @param occurExeption
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void notEquals(String assertMsg, Object arg1, Object arg2) {
        notEquals(assertMsg, arg1, arg2, AssertionException.class);
    }

    /**
     * 
     * @param arg1
     * @param arg2
     * @param occurExeption
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void notEquals(String assertMsg, Object arg1, Object arg2, Class<? extends RuntimeException> occurExeption) {
        if (!equals_(arg1, arg2)) {
            assert0(occurExeption, "The result MUST be NOT 'equal'." + msg0(assertMsg));
        }
    }

    public static void notInterface(Object object) {
        notInterface(null, object, AssertionException.class);
    }

    public static void notInterface(Object object, Class<? extends RuntimeException> occurExeption) {
        notInterface(null, object, occurExeption);
    }

    public static void notInterface(String assertMsg, Object object) {
        notInterface(assertMsg, object, AssertionException.class);
    }

    public static void notInterface(String assertMsg, Object object, Class<? extends RuntimeException> occurExeption) {
        notNull("The object MUST NOT be null. object: null", object);

        if (isInterface_(object)) {
            assert0(occurExeption, "The object is MUST NOT be interface." + msg0(assertMsg));

        }
    }

    public static void notNull(Object object) {
        notNull(null, object, null);
    }

    public static void notNull(Object object, Class<? extends RuntimeException> occurException) {
        notNull(null, object, occurException);
    }

    public static void notNull(String assertMsg, Object object) {
        notNull(assertMsg, object, null);
    }

    public static void notNull(String assertMsg, Object object, Class<? extends RuntimeException> occurException) {
        if (isNull_(object)) {
            assert0(occurException == null ? NullPointerException.class : occurException, msg0(assertMsg));
        }
    }

    public static void notNulls(Class<? extends RuntimeException> occurException, Object... objects) {
        notNulls(null, occurException, objects);
    }

    public static void notNulls(Object... objects) {
        notNulls(null, null, objects);
    }

    public static void notNulls(String assertMsg, Class<? extends RuntimeException> occurException, Object... objects) {
        notNull(assertMsg, objects, occurException);

        for (Object object : objects) {
            if (isNull_(object)) {
                assert0(occurException == null ? NullPointerException.class : occurException, "objects: " + Arrays.toString(objects) + msg0(assertMsg));
            }
        }
    }

    public static void notNulls(String assertMsg, Object... objects) {
        notNulls(assertMsg, null, objects);
    }
}
