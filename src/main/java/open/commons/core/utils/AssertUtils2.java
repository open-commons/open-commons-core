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
        equals(obj1, obj2, AssertionException.class, null);
    }

    /**
     * 
     * @param obj1
     * @param obj2
     * @param exClass
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void equals(@Nullable Object obj1, @Nullable Object obj2, @Nullable Class<? extends RuntimeException> exClass) {
        equals(obj1, obj2, exClass, null);
    }

    /**
     * 
     * @param obj1
     * @param obj2
     * @param exClass
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void equals(@Nullable Object obj1, @Nullable Object obj2, @Nullable Class<? extends RuntimeException> exClass, @Nullable String msg) {
        if (equals0(obj1, obj2)) {
            throw assert0(exClass, "The result MUST be 'equal'." + msg0(msg));
        }
    }

    /**
     * 
     * @param obj1
     * @param obj2
     * @param exClass
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void equals(@Nullable Object obj1, @Nullable Object obj2, @Nullable String msg) {
        equals(obj1, obj2, AssertionException.class, msg);
    }

    private static boolean equals0(@Nullable Object obj1, @Nullable Object obj2) {
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
        isClass(childClass, parentClass, AssertionException.class, null);
    }

    /**
     * 
     * @param childClass
     * @param parentClass
     * @param exClass
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void isClass(Class<?> childClass, Class<?> parentClass, @Nullable Class<? extends RuntimeException> exClass) {
        isClass(childClass, parentClass, exClass, null);
    }

    /**
     * 
     * @param childClass
     * @param parentClass
     * @param exClass
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void isClass(Class<?> childClass, Class<?> parentClass, @Nullable Class<? extends RuntimeException> exClass, @Nullable String msg) {

        notNulls("Neither parentClass and childClass MUST be null.", parentClass, childClass);

        if (!parentClass.isAssignableFrom(childClass.getClass())) {
            throw assert0(exClass, "childClass's type MUST be " + parentClass + " or impelemt " + parentClass + msg0(msg));
        }
    }

    /**
     * 
     * @param childClass
     * @param parentClass
     * @param exClass
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void isClass(Class<?> childClass, Class<?> parentClass, @Nullable String msg) {
        isClass(childClass, parentClass, AssertionException.class, msg);
    }

    public static void isFalse(boolean bool) {
        isFalse(bool, AssertionException.class, null);
    }

    /**
     * 
     * @param bool
     * @param exClass
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void isFalse(boolean bool, @Nullable Class<? extends RuntimeException> exClass) {
        isFalse(bool, exClass, null);
    }

    /**
     * 
     * @param bool
     * @param exClass
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void isFalse(boolean bool, @Nullable Class<? extends RuntimeException> exClass, @Nullable String msg) {
        if (bool) {
            throw assert0(exClass, "The result MUST be false." + msg0(msg));
        }
    }

    public static void isFalse(boolean bool, @Nullable String msg) {
        isFalse(bool, AssertionException.class, msg);
    }

    public static void isInterface(@Nullable Object object) {
        isInterface(object, AssertionException.class, null);
    }

    public static void isInterface(@Nullable Object object, @Nullable Class<? extends RuntimeException> exClass) {
        isInterface(object, exClass, null);
    }

    public static void isInterface(@Nullable Object object, @Nullable Class<? extends RuntimeException> exClass, @Nullable String msg) {
        Objects.requireNonNull(object, "The object MUST NOT be null. object: null");

        if (!isInterface0(object)) {
            throw assert0(exClass, "The object is MUST be interface." + msg0(msg));
        }
    }

    public static void isInterface(@Nullable Object object, @Nullable String msg) {
        isInterface(object, AssertionException.class, msg);
    }

    private static boolean isInterface0(Object object) {
        Class<?> class_ = object instanceof Class ? (Class<?>) object : object.getClass();
        return class_.isInterface();
    }

    public static void isNull(@Nullable Object object) {
        isNull(object, null, null);
    }

    public static void isNull(@Nullable Object object, @Nullable Class<? extends RuntimeException> exClass) {
        isNull(object, exClass, null);
    }

    public static void isNull(@Nullable Object object, @Nullable Class<? extends RuntimeException> exClass, @Nullable String msg) {
        if (object != null) {
            throw assert0(resolveExceptionClass(exClass, IllegalArgumentException.class), msg0(msg));
        }
    }

    public static void isNull(@Nullable Object object, @Nullable String msg) {
        isNull(object, null, msg);
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

    public static void isTrue(boolean bool) {
        isTrue(bool, AssertionException.class, null);
    }

    /**
     * 
     * @param bool
     * @param exClass
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void isTrue(boolean bool, @Nullable Class<? extends RuntimeException> exClass) {
        isTrue(bool, exClass, null);
    }

    /**
     * 
     * @param bool
     * @param exClass
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void isTrue(boolean bool, @Nullable Class<? extends RuntimeException> exClass, @Nullable String msg) {
        if (!bool) {
            throw assert0(exClass, "The result MUST be true." + msg0(msg));
        }
    }

    public static void isTrue(boolean bool, @Nullable String msg) {
        isTrue(bool, AssertionException.class, msg);
    }

    private static String msg0(@Nullable String msg) {
        return msg != null ? " (" + msg + ")" : "";
    }

    public static void notBlank(@Nullable String string) {
        notBlank(string, "주어진 문자열은 비어 있거나 whitespace로만 이루어져 있습니다. 문자열=%s", string);
    }

    public static void notBlank(@Nullable String string, @Nullable Class<? extends RuntimeException> exClass) {
        notBlank(string, exClass, null, (Object[]) null);
    }

    @SuppressWarnings("null")
    public static void notBlank(@Nullable String string, @Nullable Class<? extends RuntimeException> exClass, @Nullable String msgFormat, @Nullable Object @Nullable... msgArgs) {
        notNull(string, exClass, msgFormat, msgArgs);

        if (string.isBlank()) {
            throw assert0(resolveExceptionClass(exClass, IllegalArgumentException.class), msg0(msgFormat != null ? String.format(msgFormat, msgArgs) : null));
        }
    }

    public static void notBlank(@Nullable String string, @Nullable String msgFormat, @Nullable Object @Nullable... msgArgs) {
        notBlank(string, IllegalArgumentException.class, msgFormat, msgArgs);
    }

    public static void notBlanks(@Nullable Collection<@Nullable String> strings) {
        notBlanks(String.format("주어진 문자열은 비어 있거나 whitespace로만 이루어져 있습니다."), strings);
    }

    @SafeVarargs
    public static void notBlanks(@Nullable Collection<@Nullable String> @Nullable... stringsArray) {
        Objects.requireNonNull(stringsArray);

        for (Collection<@Nullable String> strings : stringsArray) {
            notBlanks(strings);
        }
    }

    public static void notBlanks(@Nullable String @Nullable... strings) {
        notBlanks(String.format("주어진 문자열은 비어 있거나 whitespace로만 이루어져 있습니다."), strings);
    }

    public static void notBlanks(@Nullable String msg, @Nullable Collection<@Nullable String> strings) {
        Objects.requireNonNull(strings);

        for (String string : strings) {
            notBlank(string, msg);
        }
    }

    public static void notBlanks(@Nullable String msg, @Nullable String @Nullable... strings) {
        Objects.requireNonNull(strings);

        for (String string : strings) {
            notBlank(string, msg);
        }
    }

    public static void notBlanks(@Nullable String @Nullable [] @Nullable... stringsArray) {
        Objects.requireNonNull(stringsArray);

        for (@Nullable
        String[] strings : stringsArray) {
            notBlanks(strings);
        }
    }

    public static void notEmpty(@Nullable Collection<? extends @Nullable Object> col) {
        notEmpty(col, (Class<? extends RuntimeException>) null, (String) null);
    }

    public static void notEmpty(@Nullable Collection<? extends @Nullable Object> col, @Nullable Class<? extends RuntimeException> exClass) {
        notEmpty(col, exClass, (String) null);
    }

    public static void notEmpty(@Nullable Collection<? extends @Nullable Object> col, @Nullable Class<? extends RuntimeException> exClass, @Nullable String msg) {
        notEmpty(col, exClass, msg);
    }

    public static void notEmpty(@Nullable Collection<? extends @Nullable Object> col, @Nullable String msg) {
        notEmpty(col, null, msg);
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
     * @param col
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
    public static <T extends @Nullable Object> Collection<T> notEmpty(@Nullable Collection<T> col, @Nullable Class<? extends RuntimeException> exClass, @Nullable String msgFormat,
            Object... msgArgs) {
        try {
            Objects.requireNonNull(col);
            if (col.isEmpty()) {
                throw assert0(resolveExceptionClass(exClass, IllegalArgumentException.class), msg0(msgFormat != null ? String.format(msgFormat, msgArgs) : null));
            } else {
                return col;
            }
        } catch (NullPointerException e) {
            throw assert0(resolveExceptionClass(exClass, NullPointerException.class), msg0(msgFormat != null ? String.format(msgFormat, msgArgs) : null));
        }
    }

    public static @Nullable Object @Nullable [] notEmpty(@Nullable Object @Nullable... objects) {
        notNull(objects);
        notEmpty(Arrays.asList(objects));

        return objects;
    }

    /**
     * 
     * @param obj1
     * @param obj2
     * @param exClass
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void notEquals(@Nullable Object obj1, @Nullable Object obj2) {
        notEquals(obj1, obj2, AssertionException.class, null);
    }

    /**
     * 
     * @param obj1
     * @param obj2
     * @param exClass
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void notEquals(@Nullable Object obj1, @Nullable Object obj2, @Nullable Class<? extends RuntimeException> exClass) {
        notEquals(obj1, obj2, exClass, null);
    }

    /**
     * 
     * @param obj1
     * @param obj2
     * @param exClass
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void notEquals(@Nullable Object obj1, @Nullable Object obj2, @Nullable Class<? extends RuntimeException> exClass, @Nullable String msg) {
        if (!equals0(obj1, obj2)) {
            throw assert0(exClass, "The result MUST be NOT 'equal'." + msg0(msg));
        }
    }

    /**
     * 
     * @param obj1
     * @param obj2
     * @param exClass
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     */
    public static void notEquals(@Nullable Object obj1, @Nullable Object obj2, @Nullable String msg) {
        notEquals(obj1, obj2, AssertionException.class, msg);
    }

    public static void notInterface(@Nullable Object object) {
        notInterface(object, AssertionException.class, null);
    }

    public static void notInterface(@Nullable Object object, @Nullable Class<? extends RuntimeException> exClass) {
        notInterface(object, exClass, null);
    }

    public static void notInterface(@Nullable Object object, @Nullable Class<? extends RuntimeException> exClass, @Nullable String msg) {
        Objects.requireNonNull(object, "The object MUST NOT be null. object: null");

        if (isInterface0(object)) {
            throw assert0(resolveExceptionClass(exClass, IllegalArgumentException.class), "The object is MUST NOT be interface." + msg0(msg));

        }
    }

    public static void notInterface(@Nullable Object object, @Nullable String msg) {
        notInterface(object, AssertionException.class, msg);
    }

    public static void notNull(@Nullable Object object) {
        notNull(object, (Class<? extends RuntimeException>) null, (String) null);
    }

    public static void notNull(@Nullable Object object, @Nullable Class<? extends RuntimeException> exClass) {
        notNull(object, exClass, (String) null);
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
    public static void notNull(@Nullable Object object, @Nullable Class<? extends RuntimeException> exClass, @Nullable String msgFormat, Object... msgArgs) {
        try {
            Objects.requireNonNull(object);
        } catch (NullPointerException e) {
            throw assert0(resolveExceptionClass(exClass, NullPointerException.class), msg0(msgFormat != null ? String.format(msgFormat, msgArgs) : null));
        }
    }

    public static void notNull(@Nullable Object object, @Nullable String msg) {
        notNull(object, null, msg);
    }

    public static void notNulls(@Nullable Class<? extends RuntimeException> exClass, @Nullable Object @Nullable... objects) {
        notNulls((String) null, exClass, objects);
    }

    public static void notNulls(@Nullable Class<? extends RuntimeException> exClass, @Nullable Object @Nullable [] @Nullable... objectsArray) {
        notNulls((String) null, exClass, objectsArray);
    }

    public static void notNulls(@Nullable Collection<? extends @Nullable Object> col) {
        notNulls(col, null, null);
    }

    public static void notNulls(@Nullable Collection<? extends @Nullable Object> col, @Nullable Class<? extends RuntimeException> exClass) {
        notNulls(col, exClass, null);
    }

    public static void notNulls(@Nullable Collection<? extends @Nullable Object> col, @Nullable Class<? extends RuntimeException> exClass, @Nullable String msg) {
        Objects.requireNonNull(col);

        for (Object o : col) {
            notNull(o, exClass, msg);
        }
    }

    public static void notNulls(@Nullable Collection<? extends @Nullable Object> col, @Nullable String msg) {
        notNulls(col, null, msg);
    }

    public static void notNulls(@Nullable Map<?, ? extends @Nullable Object> map) {
        notNulls(map, null, null);
    }

    public static void notNulls(@Nullable Map<?, ? extends @Nullable Object> map, @Nullable Class<? extends RuntimeException> exClass) {
        notNulls(map, exClass, null);
    }

    @SuppressWarnings("null")
    public static void notNulls(@Nullable Map<?, ? extends @Nullable Object> map, @Nullable Class<? extends RuntimeException> exClass, @Nullable String msg) {
        notNulls(map, "The map MUST NOT be null. map: null");

        Object key = null;
        Object value = null;
        for (Entry<?, ?> entry : map.entrySet()) {
            key = entry.getKey();
            value = entry.getValue();
            notNulls("Neither key and value MUST be null. key: " + key + ", value: " + value + ", map: " + map + msg0(msg), key, value);
        }
    }

    public static void notNulls(@Nullable Map<?, ? extends @Nullable Object> map, @Nullable String msg) {
        notNulls(map, null, msg);
    }

    public static void notNulls(@Nullable Object @Nullable... objects) {
        notNulls((String) null, (Class<? extends RuntimeException>) null, objects);
    }

    public static void notNulls(@Nullable Object @Nullable [] @Nullable... objectsArray) {
        notNulls((String) null, (Class<? extends RuntimeException>) null, objectsArray);
    }

    public static void notNulls(@Nullable String msg, @Nullable Class<? extends RuntimeException> exClass, @Nullable Object @Nullable... objects) {
        try {
            Objects.requireNonNull(objects);

            for (Object object : objects) {
                Objects.requireNonNull(object);
            }
        } catch (NullPointerException e) {
            throw assert0(resolveExceptionClass(exClass, NullPointerException.class), "objects: " + Arrays.toString(objects) + msg0(msg));
        }

    }

    @SuppressWarnings("null")
    public static void notNulls(@Nullable String msg, @Nullable Class<? extends RuntimeException> exClass, @Nullable Object @Nullable [] @Nullable... objectsArray) {
        Objects.requireNonNull(objectsArray);

        for (Object[] objects : objectsArray) {
            notNulls(msg, exClass, objects);
        }
    }

    private static final Class<? extends RuntimeException> resolveExceptionClass(@Nullable Class<? extends RuntimeException> exClass,
            Class<? extends RuntimeException> defaultExceptionClass) {
        AssertUtils2.notNull(defaultExceptionClass);

        return exClass != null ? exClass : defaultExceptionClass;
    }
}
