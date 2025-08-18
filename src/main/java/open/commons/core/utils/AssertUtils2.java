/*
 * Copyright 2025 Park Jun-Hong (parkjunhong77@gmail.com)
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

    private static void assert0(Class<? extends RuntimeException> exClass, String msg) throws RuntimeException {
        try {
            Constructor<? extends RuntimeException> cons = exClass != null ? exClass.getConstructor(String.class) : AssertionException.class.getConstructor(String.class);
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
     * @param obj1
     * @param obj2
     * @param exClass
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void equals(Object obj1, Object obj2) {
        equals(null, obj1, obj2, AssertionException.class);
    }

    /**
     * 
     * @param obj1
     * @param obj2
     * @param exClass
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void equals(Object obj1, Object obj2, Class<? extends RuntimeException> exClass) {
        equals(null, obj1, obj2, exClass);
    }

    /**
     * 
     * @param obj1
     * @param obj2
     * @param exClass
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void equals(String msg, Object obj1, Object obj2) {
        equals(msg, obj1, obj2, AssertionException.class);
    }

    /**
     * 
     * @param obj1
     * @param obj2
     * @param exClass
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void equals(String msg, Object obj1, Object obj2, Class<? extends RuntimeException> exClass) {
        if (equals_(obj1, obj2)) {
            assert0(exClass, "The result MUST be 'equal'." + msg0(msg));
        }
    }

    private static boolean equals_(Object obj1, Object obj2) {
        if (obj1 != null && obj2 != null) {
            return obj1.equals(obj2);
        } else {
            return obj1 == null && obj2 == null;
        }
    }

    /**
     * 
     * @param childClass
     * @param parentClass
     * @param exClass
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void isClass(Class<?> childClass, Class<?> parentClass) {
        isClass(null, childClass, parentClass, AssertionException.class);
    }

    /**
     * 
     * @param childClass
     * @param parentClass
     * @param exClass
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void isClass(Class<?> childClass, Class<?> parentClass, Class<? extends RuntimeException> exClass) {
        isClass(null, childClass, parentClass, exClass);
    }

    /**
     * 
     * @param childClass
     * @param parentClass
     * @param exClass
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void isClass(String msg, Class<?> childClass, Class<?> parentClass) {
        isClass(msg, childClass, parentClass, AssertionException.class);
    }

    /**
     * 
     * @param childClass
     * @param parentClass
     * @param exClass
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void isClass(String msg, Class<?> childClass, Class<?> parentClass, Class<? extends RuntimeException> exClass) {

        notNulls("Neither parentClass and childClass MUST be null.", parentClass, childClass);

        if (!parentClass.isAssignableFrom(childClass.getClass())) {
            assert0(exClass, "childClass's type MUST be " + parentClass + " or impelemt " + parentClass + msg0(msg));
        }
    }

    public static void isFalse(boolean bool) {
        isFalse(null, bool, AssertionException.class);
    }

    /**
     * 
     * @param bool
     * @param exClass
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void isFalse(boolean bool, Class<? extends RuntimeException> exClass) {
        isFalse(null, bool, exClass);
    }

    public static void isFalse(String msg, boolean bool) {
        isFalse(msg, bool, AssertionException.class);
    }

    /**
     * 
     * @param bool
     * @param exClass
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void isFalse(String msg, boolean bool, Class<? extends RuntimeException> exClass) {
        try {
            if (bool) {
                assert0(exClass, "The result MUST be false." + msg0(msg));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void isInterface(Object object) {
        isInterface(null, object, AssertionException.class);
    }

    public static void isInterface(Object object, Class<? extends RuntimeException> exClass) {
        isInterface(null, object, exClass);
    }

    public static void isInterface(String msg, Object object) {
        isInterface(msg, object, AssertionException.class);
    }

    public static void isInterface(String msg, Object object, Class<? extends RuntimeException> exClass) {
        notNull("The object MUST NOT be null. object: null", object);

        if (!isInterface_(object)) {
            assert0(exClass, "The object is MUST be interface." + msg0(msg));
        }
    }

    private static boolean isInterface_(Object object) {
        Class<?> class_ = object instanceof Class ? (Class<?>) object : object.getClass();
        return class_.isInterface();
    }

    public static void isNull(Object object) {
        isNull(null, object, null);
    }

    public static void isNull(Object object, Class<? extends RuntimeException> exClass) {
        isNull(null, object, exClass);
    }

    public static void isNull(String msg, Object object) {
        isNull(msg, object, null);
    }

    public static void isNull(String msg, Object object, Class<? extends RuntimeException> exClass) {
        if (!isNull_(object)) {
            assert0(exClass == null ? NullPointerException.class : exClass, msg0(msg));
        }
    }

    private static boolean isNull_(Object object) {
        return object == null;
    }

    public static void isNulls(Class<? extends RuntimeException> exClass, Object... objects) {
        isNulls(null, exClass, objects);
    }

    public static void isNulls(Object... objects) {
        isNulls(null, null, objects);
    }

    public static void isNulls(String msg, Class<? extends RuntimeException> exClass, Object... objects) {
        isNull(null, objects, null);

        for (Object object : objects) {
            if (!isNull_(object)) {
                assert0(exClass == null ? NullPointerException.class : exClass, "objects: " + Arrays.toString(objects) + msg0(msg));
            }
        }
    }

    public static void isNulls(String msg, Object... objects) {
        isNulls(msg, null, objects);
    }

    public static void isTrue(boolean bool) {
        isTrue(null, bool, AssertionException.class);
    }

    /**
     * 
     * @param bool
     * @param exClass
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void isTrue(boolean bool, Class<? extends RuntimeException> exClass) {
        isTrue(null, bool, exClass);
    }

    public static void isTrue(String msg, boolean bool) {
        isTrue(msg, bool, AssertionException.class);
    }

    /**
     * 
     * @param bool
     * @param exClass
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void isTrue(String msg, boolean bool, Class<? extends RuntimeException> exClass) {
        try {
            if (!bool) {
                assert0(exClass, "The result MUST be true." + msg0(msg));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void mapNotNull(Map<?, ?> map) {
        mapNotNull(null, map, null);
    }

    public static void mapNotNull(Map<?, ?> map, Class<? extends RuntimeException> exClass) {
        mapNotNull(null, map, exClass);
    }

    public static void mapNotNull(String msg, Map<?, ?> map) {
        mapNotNull(msg, map, null);
    }

    public static void mapNotNull(String msg, Map<?, ?> map, Class<? extends RuntimeException> exClass) {
        notNull("The map MUST NOT be null. map: null", map);

        Object key = null;
        Object value = null;
        for (Entry<?, ?> entry : map.entrySet()) {
            key = entry.getKey();
            value = entry.getValue();
            notNulls("Neither key and value MUST be null. key: " + key + ", value: " + value + ", map: " + map + msg0(msg), key, value);
        }
    }

    public static void mapNull(Map<?, ?> map) {
        mapNull(null, map, null);
    }

    public static void mapNull(Map<?, ?> map, Class<? extends RuntimeException> exClass) {
        mapNull(null, map, exClass);
    }

    public static void mapNull(String msg, Map<?, ?> map) {
        mapNull(msg, map, null);
    }

    public static void mapNull(String msg, Map<?, ?> map, Class<? extends RuntimeException> exClass) {
        notNull("The map MUST NOT be null. map: null", map);

        Object key = null;
        Object value = null;
        for (Entry<?, ?> entry : map.entrySet()) {
            key = entry.getKey();
            value = entry.getValue();
            isNulls("key and value MUST be null. key: " + key + ", value: " + value + ", map: " + map + msg0(msg), key, value);
        }
    }

    private static String msg0(String msg) {
        return msg != null ? " (" + msg + ")" : "";
    }

    /**
     * 
     * @param obj1
     * @param obj2
     * @param exClass
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void notEquals(Object obj1, Object obj2) {
        notEquals(null, obj1, obj2, AssertionException.class);
    }

    /**
     * 
     * @param obj1
     * @param obj2
     * @param exClass
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void notEquals(Object obj1, Object obj2, Class<? extends RuntimeException> exClass) {
        notEquals(null, obj1, obj2, exClass);
    }

    /**
     * 
     * @param obj1
     * @param obj2
     * @param exClass
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void notEquals(String msg, Object obj1, Object obj2) {
        notEquals(msg, obj1, obj2, AssertionException.class);
    }

    /**
     * 
     * @param obj1
     * @param obj2
     * @param exClass
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void notEquals(String msg, Object obj1, Object obj2, Class<? extends RuntimeException> exClass) {
        if (!equals_(obj1, obj2)) {
            assert0(exClass, "The result MUST be NOT 'equal'." + msg0(msg));
        }
    }

    public static void notInterface(Object object) {
        notInterface(null, object, AssertionException.class);
    }

    public static void notInterface(Object object, Class<? extends RuntimeException> exClass) {
        notInterface(null, object, exClass);
    }

    public static void notInterface(String msg, Object object) {
        notInterface(msg, object, AssertionException.class);
    }

    public static void notInterface(String msg, Object object, Class<? extends RuntimeException> exClass) {
        notNull("The object MUST NOT be null. object: null", object);

        if (isInterface_(object)) {
            assert0(exClass, "The object is MUST NOT be interface." + msg0(msg));

        }
    }

    public static void notNull(Object object) {
        notNull(null, object, null);
    }

    public static void notNull(Object object, Class<? extends RuntimeException> exClass) {
        notNull(null, object, exClass);
    }

    /**
     * 검증할 객체가 'null'이 아닌지를 검증합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 13.		박준홍			최초 작성
     * </pre>
     *
     * @param object
     *            검증할 객체
     * @param exClass
     *            오류 클래스
     * @param msgFormat
     *            {@link String#format(String, Object...)} 에서 사용될 메시지 포맷
     * @param msgArgs
     *            메시지 데이터
     *
     * @since 2025. 8. 13.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static void notNull(Object object, Class<? extends RuntimeException> exClass, String msgFormat, Object... msgArgs) {
        if (isNull_(object)) {
            assert0(exClass == null ? NullPointerException.class : exClass, msg0(String.format(msgFormat, msgArgs)));
        }
    }

    public static void notNull(String msg, Object object) {
        notNull(msg, object, null);
    }

    public static void notNull(String msg, Object object, Class<? extends RuntimeException> exClass) {
        notNull(object, exClass, msg);
    }

    public static void notNulls(Class<? extends RuntimeException> exClass, Object... objects) {
        notNulls(null, exClass, objects);
    }

    public static void notNulls(Object... objects) {
        notNulls(null, null, objects);
    }

    public static void notNulls(String msg, Class<? extends RuntimeException> exClass, Object... objects) {
        notNull(msg, objects, exClass);

        for (Object object : objects) {
            if (isNull_(object)) {
                assert0(exClass == null ? NullPointerException.class : exClass, "objects: " + Arrays.toString(objects) + msg0(msg));
            }
        }
    }

    public static void notNulls(String msg, Object... objects) {
        notNulls(msg, null, objects);
    }
}
