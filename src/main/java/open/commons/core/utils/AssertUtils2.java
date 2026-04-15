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
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import org.jspecify.annotations.Nullable;

import open.commons.core.exception.AssertionException;

/**
 * 'assert'의 단어적 의미에 부합되는 기능을 제공하는 클래스.<br>
 * 
 * <p style="color:red">
 * 기존 {@code AssertUtils} 클래스가 'assert'의 단어적 의미와 상반되는 기능을 제공하였기 때문에,<br>
 * {@code 3.0.0} 이전 버전의 {@code AssertUtils}를 사용한 코드는 기능을 수정해야 합니다.
 * </p>
 * 
 * @since 2025. 6. 24.
 * @version 2.1.0
 */
public class AssertUtils2 {

    private AssertUtils2() {
    }

    // 아래 내용에 적용됨.
    // - cons.newInstance(msg)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    private static RuntimeException assert0(@Nullable Class<? extends RuntimeException> exClass, @Nullable String msg) {
        try {
            Constructor<? extends RuntimeException> cons = exClass != null //
                    ? exClass.getDeclaredConstructor(String.class) //
                    : AssertionException.class.getDeclaredConstructor(String.class);
            return cons.newInstance(msg);
        } catch (NoSuchMethodException e) {
            return new RuntimeException(e);
        } catch (InstantiationException e) {
            return new RuntimeException(e);
        } catch (IllegalAccessException e) {
            return new RuntimeException(e);
        } catch (InvocationTargetException e) {
            return new RuntimeException(e);
        }
    }

    /**
     * 
     * @param obj1
     * @param obj2
     * @param exClass
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void equals(@Nullable Object obj1, @Nullable Object obj2) {
        equals(null, obj1, obj2, AssertionException.class);
    }

    /**
     * 
     * @param obj1
     * @param obj2
     * @param exClass
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void equals(@Nullable Object obj1, @Nullable Object obj2, @Nullable Class<? extends RuntimeException> exClass) {
        equals(null, obj1, obj2, exClass);
    }

    /**
     * 
     * @param obj1
     * @param obj2
     * @param exClass
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void equals(@Nullable String msg, @Nullable Object obj1, @Nullable Object obj2) {
        equals(msg, obj1, obj2, AssertionException.class);
    }

    /**
     * 
     * @param obj1
     * @param obj2
     * @param exClass
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void equals(@Nullable String msg, @Nullable Object obj1, @Nullable Object obj2, @Nullable Class<? extends RuntimeException> exClass) {
        if (equals_(obj1, obj2)) {
            throw assert0(exClass, "The result MUST be 'equal'." + msg0(msg));
        }
    }

    private static boolean equals_(@Nullable Object obj1, @Nullable Object obj2) {
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
    public static void isClass(Class<?> childClass, Class<?> parentClass, @Nullable Class<? extends RuntimeException> exClass) {
        isClass(null, childClass, parentClass, exClass);
    }

    /**
     * 
     * @param childClass
     * @param parentClass
     * @param exClass
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void isClass(@Nullable String msg, Class<?> childClass, Class<?> parentClass) {
        isClass(msg, childClass, parentClass, AssertionException.class);
    }

    /**
     * 
     * @param childClass
     * @param parentClass
     * @param exClass
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void isClass(@Nullable String msg, Class<?> childClass, Class<?> parentClass, @Nullable Class<? extends RuntimeException> exClass) {

        notNulls("Neither parentClass and childClass MUST be null.", parentClass, childClass);

        if (!parentClass.isAssignableFrom(childClass.getClass())) {
            throw assert0(exClass, "childClass's type MUST be " + parentClass + " or impelemt " + parentClass + msg0(msg));
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
    public static void isFalse(boolean bool, @Nullable Class<? extends RuntimeException> exClass) {
        isFalse(null, bool, exClass);
    }

    public static void isFalse(@Nullable String msg, boolean bool) {
        isFalse(msg, bool, AssertionException.class);
    }

    /**
     * 
     * @param bool
     * @param exClass
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void isFalse(@Nullable String msg, boolean bool, @Nullable Class<? extends RuntimeException> exClass) {
        if (bool) {
            throw assert0(exClass, "The result MUST be false." + msg0(msg));
        }
    }

    public static void isInterface(@Nullable Object object) {
        isInterface(null, object, AssertionException.class);
    }

    public static void isInterface(@Nullable Object object, @Nullable Class<? extends RuntimeException> exClass) {
        isInterface(null, object, exClass);
    }

    public static void isInterface(@Nullable String msg, @Nullable Object object) {
        isInterface(msg, object, AssertionException.class);
    }

    public static void isInterface(@Nullable String msg, @Nullable Object object, @Nullable Class<? extends RuntimeException> exClass) {
        Objects.requireNonNull(object, "The object MUST NOT be null. object: null");

        if (!isInterface_(object)) {
            throw assert0(exClass, "The object is MUST be interface." + msg0(msg));
        }
    }

    private static boolean isInterface_(Object object) {
        Class<?> class_ = object instanceof Class ? (Class<?>) object : object.getClass();
        return class_.isInterface();
    }

    public static void isNull(@Nullable Object object) {
        isNull(null, object, null);
    }

    public static void isNull(@Nullable Object object, @Nullable Class<? extends RuntimeException> exClass) {
        isNull(null, object, exClass);
    }

    public static void isNull(@Nullable String msg, @Nullable Object object) {
        isNull(msg, object, null);
    }

    public static void isNull(@Nullable String msg, @Nullable Object object, @Nullable Class<? extends RuntimeException> exClass) {
        if (object != null) {
            throw assert0(resolveExceptionClass(exClass, IllegalArgumentException.class), msg0(msg));
        }
    }

    public static void isNulls(@Nullable Class<? extends RuntimeException> exClass, @Nullable Object @Nullable... objects) {
        isNulls(null, exClass, objects);
    }

    public static void isNulls(@Nullable Object @Nullable... objects) {
        isNulls(null, null, objects);
    }

    public static void isNulls(@Nullable String msg, @Nullable Class<? extends RuntimeException> exClass, @Nullable Object @Nullable... objects) {
        if (objects == null) {
            return;
        }

        for (Object object : objects) {
            if (object != null) {
                throw assert0(resolveExceptionClass(exClass, IllegalArgumentException.class), "objects: " + Arrays.toString(objects) + msg0(msg));
            }
        }
    }

    public static void isNulls(@Nullable String msg, @Nullable Object @Nullable... objects) {
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
    public static void isTrue(boolean bool, @Nullable Class<? extends RuntimeException> exClass) {
        isTrue(null, bool, exClass);
    }

    public static void isTrue(@Nullable String msg, boolean bool) {
        isTrue(msg, bool, AssertionException.class);
    }

    /**
     * 
     * @param bool
     * @param exClass
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void isTrue(@Nullable String msg, boolean bool, @Nullable Class<? extends RuntimeException> exClass) {
        if (!bool) {
            throw assert0(exClass, "The result MUST be true." + msg0(msg));
        }
    }

    public static Object mapNotNull(Map<?, ?> map) {
        return mapNotNull(null, map, null);
    }

    public static Object mapNotNull(Map<?, ?> map, @Nullable Class<? extends RuntimeException> exClass) {
        return mapNotNull(null, map, exClass);
    }

    public static Object mapNotNull(@Nullable String msg, Map<?, ?> map) {
        return mapNotNull(msg, map, null);
    }

    public static Object mapNotNull(@Nullable String msg, Map<?, ?> map, @Nullable Class<? extends RuntimeException> exClass) {
        notNull(map, "The map MUST NOT be null. map: null");

        Object key = null;
        Object value = null;
        for (Entry<?, ?> entry : map.entrySet()) {
            key = entry.getKey();
            value = entry.getValue();
            notNulls("Neither key and value MUST be null. key: " + key + ", value: " + value + ", map: " + map + msg0(msg), key, value);
        }

        return map;
    }

    private static String msg0(@Nullable String msg) {
        return msg != null ? " (" + msg + ")" : "";
    }

    public static void notBlank(@Nullable String string) {
        notBlank(string, "주어진 문자열은 비어 있거나 whitespace로만 이루어져 있습니다. 문자열=%s", string);
    }

    @SuppressWarnings("null")
    public static void notBlank(@Nullable String string, @Nullable Class<? extends RuntimeException> exClass, @Nullable String msgFormat, @Nullable Object @Nullable... msgArgs) {
        notNull(string, exClass);

        if (string.isBlank()) {
            throw assert0(resolveExceptionClass(exClass, IllegalArgumentException.class), msg0(msgFormat != null ? String.format(msgFormat, msgArgs) : null));
        }
    }

    public static void notBlank(@Nullable String string, @Nullable String msgFormat, @Nullable Object @Nullable... msgArgs) {
        notBlank(string, IllegalArgumentException.class, msgFormat, msgArgs);
    }

    public static <T extends @Nullable Object> Collection<T> notEmpty(@Nullable Collection<T> object) {
        return notEmpty(object, (Class<? extends RuntimeException>) null, (String) null);
    }

    public static <T extends @Nullable Object> Collection<T> notEmpty(@Nullable Collection<T> object, @Nullable Class<? extends RuntimeException> exClass) {
        return notEmpty(object, exClass, (String) null);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2026. 4. 1.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <T>
     * @param object
     *            검증할 객체
     * @param exClass
     *            오류 클래스
     * @param msgFormat
     *            {@link String#format(String, Object...)} 에서 사용될 메시지 포맷
     * @param msgArgs
     *            메시지 데이터
     * 
     * @return
     *
     * @since 2026. 4. 1.
     * @version 3.0.0
     */
    public static <T extends @Nullable Object> Collection<T> notEmpty(@Nullable Collection<T> object, @Nullable Class<? extends RuntimeException> exClass,
            @Nullable String msgFormat, Object... msgArgs) {
        try {
            Objects.requireNonNull(object);
            if (object.isEmpty()) {
                throw assert0(resolveExceptionClass(exClass, IllegalArgumentException.class), msg0(msgFormat != null ? String.format(msgFormat, msgArgs) : null));
            } else {
                return object;
            }
        } catch (NullPointerException e) {
            throw assert0(resolveExceptionClass(exClass, NullPointerException.class), msg0(msgFormat != null ? String.format(msgFormat, msgArgs) : null));
        }
    }

    public static <T extends @Nullable Object> Collection<T> notEmpty(@Nullable Collection<T> object, @Nullable String msg) {
        return notEmpty(object, null, msg);
    }

    public static <T extends @Nullable Object> Collection<T> notEmpty(@Nullable String msg, @Nullable Collection<T> object, @Nullable Class<? extends RuntimeException> exClass) {
        return notEmpty(object, exClass, msg);
    }

    /**
     * 
     * @param obj1
     * @param obj2
     * @param exClass
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void notEquals(@Nullable Object obj1, @Nullable Object obj2) {
        notEquals(null, obj1, obj2, AssertionException.class);
    }

    /**
     * 
     * @param obj1
     * @param obj2
     * @param exClass
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void notEquals(@Nullable Object obj1, @Nullable Object obj2, @Nullable Class<? extends RuntimeException> exClass) {
        notEquals(null, obj1, obj2, exClass);
    }

    /**
     * 
     * @param obj1
     * @param obj2
     * @param exClass
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void notEquals(@Nullable String msg, @Nullable Object obj1, @Nullable Object obj2) {
        notEquals(msg, obj1, obj2, AssertionException.class);
    }

    /**
     * 
     * @param obj1
     * @param obj2
     * @param exClass
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void notEquals(@Nullable String msg, @Nullable Object obj1, @Nullable Object obj2, @Nullable Class<? extends RuntimeException> exClass) {
        if (!equals_(obj1, obj2)) {
            throw assert0(exClass, "The result MUST be NOT 'equal'." + msg0(msg));
        }
    }

    public static void notExistNull(@Nullable Collection<? extends @Nullable Object> col) {
        notExistNull(null, col, null);
    }

    public static void notExistNull(@Nullable Collection<? extends @Nullable Object> col, @Nullable Class<? extends RuntimeException> exClass) {
        notExistNull(null, col, exClass);
    }

    public static void notExistNull(@Nullable Object @Nullable... objects) {
        notExistNull(null, Arrays.asList(objects), null);
    }

    public static void notExistNull(@Nullable String msg, @Nullable Collection<? extends @Nullable Object> col) {
        notExistNull(msg, col, null);
    }

    public static void notExistNull(@Nullable String msg, @Nullable Collection<? extends @Nullable Object> col, @Nullable Class<? extends RuntimeException> exClass) {
        Objects.requireNonNull(col);

        for (Object o : col) {
            notNull(o, null, msg);
        }
    }

    public static void notInterface(@Nullable Object object) {
        notInterface(null, object, AssertionException.class);
    }

    public static void notInterface(@Nullable Object object, @Nullable Class<? extends RuntimeException> exClass) {
        notInterface(null, object, exClass);
    }

    public static void notInterface(@Nullable String msg, @Nullable Object object) {
        notInterface(msg, object, AssertionException.class);
    }

    public static void notInterface(@Nullable String msg, @Nullable Object object, @Nullable Class<? extends RuntimeException> exClass) {
        Objects.requireNonNull(object, "The object MUST NOT be null. object: null");

        if (isInterface_(object)) {
            throw assert0(resolveExceptionClass(exClass, IllegalArgumentException.class), "The object is MUST NOT be interface." + msg0(msg));

        }
    }

    public static Object notNull(@Nullable Object object) {
        return notNull(object, (Class<? extends RuntimeException>) null, (String) null);
    }

    public static Object notNull(@Nullable Object object, @Nullable Class<? extends RuntimeException> exClass) {
        return notNull(object, exClass, (String) null);
    }

    /**
     * 검증할 객체가 'null'이 아닌지를 검증합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 13.		parkjunhong77@gmail.com			최초 작성
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
     */
    public static Object notNull(@Nullable Object object, @Nullable Class<? extends RuntimeException> exClass, @Nullable String msgFormat, Object... msgArgs) {
        try {
            Objects.requireNonNull(object);
            return object;
        } catch (NullPointerException e) {
            throw assert0(resolveExceptionClass(exClass, NullPointerException.class), msg0(msgFormat != null ? String.format(msgFormat, msgArgs) : null));
        }
    }

    public static Object notNull(@Nullable Object object, @Nullable String msg) {
        return notNull(object, null, msg);
    }

    public static Object notNull(@Nullable String msg, @Nullable Object object, @Nullable Class<? extends RuntimeException> exClass) {
        return notNull(object, exClass, msg);
    }

    public static Object notNulls(@Nullable Class<? extends RuntimeException> exClass, @Nullable Object @Nullable... objects) {
        return notNulls((String) null, exClass, objects);
    }

    public static Object notNulls(@Nullable Object @Nullable... objects) {
        return notNulls((String) null, (Class<? extends RuntimeException>) null, objects);
    }

    public static Object notNulls(@Nullable String msg, @Nullable Class<? extends RuntimeException> exClass, Object @Nullable... objects) {
        try {
            Objects.requireNonNull(objects);

            for (Object object : objects) {
                Objects.requireNonNull(object);
            }
            return objects;
        } catch (NullPointerException e) {
            throw assert0(resolveExceptionClass(exClass, NullPointerException.class), "objects: " + Arrays.toString(objects) + msg0(msg));
        }

    }

    public static Object notNulls(@Nullable String msg, @Nullable Object @Nullable... objects) {
        return notNulls(msg, (Class<? extends RuntimeException>) null, objects);
    }

    private static final Class<? extends RuntimeException> resolveExceptionClass(@Nullable Class<? extends RuntimeException> exClass,
            Class<? extends RuntimeException> defaultExceptionClass) {
        AssertUtils2.notNull(defaultExceptionClass);

        return exClass != null ? exClass : defaultExceptionClass;
    }
}
