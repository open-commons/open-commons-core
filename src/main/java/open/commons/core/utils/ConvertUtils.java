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

/**
* @title 등록 개발용 StringUtils
* @since 2011. 8. 2.
*/
package open.commons.core.utils;

import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

/**
 * @author Park Jun-Hong.(mail_to:parkjunhong77@gmail.com)
 * @since 2011. 8. 2.
 * 
 */
@SuppressWarnings("unchecked")
public class ConvertUtils {

    public static final String REGEX_BT = "( |\t)";

    public static final String REGEX_WS = "( |\t|\n|\r)";

    public static final String REGEX_COMMENT = "(//(.)*|/\\*(\\*)?(.)*\\*/)";

    /** boolean, {@link Boolean} */
    public static final byte TYPE_CONST_BOOLEAN = 0x00;
    /** byte, {@link Byte} */
    public static final byte TYPE_CONST_BYTE = 0x01;
    /** char, {@link Character} */
    public static final byte TYPE_CONST_CHAR = 0x02;
    /** short, {@link Short} */
    public static final byte TYPE_CONST_SHORT = 0x03;
    /** int, {@link Integer} */
    public static final byte TYPE_CONST_INT = 0x04;
    /** long, {@link Long} */
    public static final byte TYPE_CONST_LONG = 0x05;
    /** float, {@link Float} */
    public static final byte TYPE_CONST_FLOAT = 0x06;
    /** double, {@link Double} */
    public static final byte TYPE_CONST_DOUBLE = 0x07;
    /** Referenced Type */
    public static final byte TYPE_CONST_OBJECT = 0x08;

    private static Map<Class<?>, Byte> primitiveTypesConst = new ConcurrentHashMap<Class<?>, Byte>();
    private static Map<Class<?>, Class<?>> primitiveTypesWrapperClass = new ConcurrentHashMap<Class<?>, Class<?>>();

    static {
        putTypeConsts(TYPE_CONST_BOOLEAN, boolean.class, Boolean.class);
        putTypeConsts(TYPE_CONST_BYTE, byte.class, Byte.class);
        putTypeConsts(TYPE_CONST_CHAR, char.class, Character.class);
        putTypeConsts(TYPE_CONST_SHORT, short.class, Short.class);
        putTypeConsts(TYPE_CONST_INT, int.class, Integer.class);
        putTypeConsts(TYPE_CONST_LONG, long.class, Long.class);
        putTypeConsts(TYPE_CONST_FLOAT, float.class, Float.class);
        putTypeConsts(TYPE_CONST_DOUBLE, double.class, Double.class);
        putTypeConsts(TYPE_CONST_OBJECT, Object.class);

        primitiveTypesWrapperClass.put(boolean.class, Boolean.class);
        primitiveTypesWrapperClass.put(byte.class, Byte.class);
        primitiveTypesWrapperClass.put(char.class, Character.class);
        primitiveTypesWrapperClass.put(short.class, Short.class);
        primitiveTypesWrapperClass.put(int.class, Integer.class);
        primitiveTypesWrapperClass.put(long.class, Long.class);
        primitiveTypesWrapperClass.put(float.class, Float.class);
        primitiveTypesWrapperClass.put(double.class, Double.class);
    }

    public static <T> void assertValue(Object value, Class<T> class_) {
        assertValue(value, class_, IllegalArgumentException.class);
    }

    /**
     * 
     * @param value
     * @param clazz
     * @param occurExeption
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 합니다.
     * 
     * @throws NullPointerException
     *             파라미터({@code value 또는 clazz})가 {@code null}인 경우 발생.
     */
    @SuppressWarnings("null")
    public static <T> void assertValue(Object value, Class<T> clazz, @Nullable Class<? extends RuntimeException> occurExeption) {
        ObjectUtils.requireNonNullsWithMessage("Neither value and class_ MUST be null.", value, clazz);
        Objects.requireNonNull(value);
        Objects.requireNonNull(clazz);

        @NonNull
        Class<?> translatedClass = translateToWrapper(clazz);

        if (!translatedClass.isAssignableFrom(value.getClass())) {
            try {

                Constructor<? extends RuntimeException> cons = occurExeption != null ? occurExeption.getConstructor(String.class)
                        : IllegalArgumentException.class.getConstructor(String.class);

                throw (RuntimeException) cons.newInstance( //
                        "value's type MUST be " + clazz //
                                + (!clazz.equals(translatedClass) //
                                        ? " or " + translatedClass.getSimpleName() //
                                        : "")
                                + ". value: " + value + " (" + value.getClass() + ")" //
                );

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * @param clazz
     * @return One of followings.
     * 
     *         <ul>
     *         <li>{@link #TYPE_CONST_BOOLEAN}
     *         <li>{@link #TYPE_CONST_BYTE}
     *         <li>{@link #TYPE_CONST_CHAR}
     *         <li>{@link #TYPE_CONST_INT}
     *         <li>{@link #TYPE_CONST_SHORT}
     *         <li>{@link #TYPE_CONST_LONG}
     *         <li>{@link #TYPE_CONST_FLOAT}
     *         <li>{@link #TYPE_CONST_DOUBLE}
     *         <li>{@link #TYPE_CONST_OBJECT}
     *         </ul>
     * 
     * @throws NullPointerException
     *             파라미터({@code clazz})가 {@code null}인 경우 발생.
     */
    public static byte getTypeConst(Class<?> clazz) {
        Objects.requireNonNull(clazz);

        return primitiveTypesConst.containsKey(clazz) ? primitiveTypesConst.get(clazz) : TYPE_CONST_OBJECT;
    }

    /**
     * 
     * @param clazz
     * @return One of followings.
     *         <ul>
     *         <li>Boolean
     *         <li>Byte
     *         <li>Character
     *         <li>Integer
     *         <li>Long
     *         <li>Float
     *         <li>Double
     *         </ul>
     * 
     * @throws NullPointerException
     *             // * 파라미터({@code clazz})가 {@code null}인 경우 발생.
     */
    public static Class<?> getWrapperClass(Class<?> clazz) {
        Objects.requireNonNull(clazz);

        return primitiveTypesWrapperClass.containsKey(clazz) //
                ? Objects.requireNonNull(primitiveTypesWrapperClass.get(clazz)) //
                : clazz;
    }

    /**
     * 입력 타입이 대상 타입과 같거나 상위 타입인지 여부를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 12. 2.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     *
     * @param srcType
     *            입력 타입.
     * @param targetType
     *            대상 타입.
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code srcType 또는 targetType})가 {@code null}인 경우 발생.
     *
     * @since 2021. 12. 2.
     * @version 1.8.0
     * 
     */
    public static boolean isAssignableFrom(Class<?> srcType, Class<?> targetType) {
        ObjectUtils.requireNonNulls(srcType, targetType);

        switch (getTypeConst(srcType)) {
            case TYPE_CONST_BOOLEAN:
                return boolean.class.equals(targetType) || Boolean.class.equals(targetType);
            case TYPE_CONST_BYTE:
                return byte.class.equals(targetType) || Byte.class.equals(targetType);
            case TYPE_CONST_CHAR:
                return char.class.equals(targetType) || Character.class.equals(targetType);
            case TYPE_CONST_SHORT:
                return short.class.equals(targetType) || Short.class.equals(targetType);
            case TYPE_CONST_INT:
                return int.class.equals(targetType) || Integer.class.equals(targetType);
            case TYPE_CONST_LONG:
                return long.class.equals(targetType) || Long.class.equals(targetType);
            case TYPE_CONST_FLOAT:
                return float.class.equals(targetType) || Float.class.equals(targetType);
            case TYPE_CONST_DOUBLE:
                return double.class.equals(targetType) || Double.class.equals(targetType);
            default:
                return srcType.isAssignableFrom(targetType);
        }
    }

    /**
     * 입력 객체의 타입이 대상 객체의 타입과 같거나 상위 타입 여부를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 12. 2.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     *
     * @param srcObject
     *            입력 타입.
     * @param targetObject
     *            대상 타입.
     * @return
     *
     * @since 2021. 12. 2.
     * @version 1.8.0
     * 
     */
    @SuppressWarnings("null")
    public static <S, T> boolean isAssignableFrom(S srcObject, T targetObject) {
        ObjectUtils.requireNonNulls(srcObject, targetObject);

        return isAssignableFrom(srcObject.getClass(), targetObject.getClass());
    }

    private static void putTypeConsts(byte typeConst, Class<?>... types) {
        for (Class<?> type : types) {
            primitiveTypesConst.put(type, typeConst);
        }
    }

    /**
     * 문자열 데이터를 Primitive Type 에 해당하는 값으로 변환한다. <br>
     * 
     * @param primitiveType
     * @param value
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code primitiveType})가 {@code null}인 경우 발생.
     *
     * @since 2014. 4. 2.
     */
    public static <T> T toPrimitiveTypeValue(Class<T> primitiveType, String value) {
        ObjectUtils.requireNonNulls(primitiveType, value);

        switch (getTypeConst(primitiveType)) {
            case TYPE_CONST_BOOLEAN:
                return (T) Boolean.valueOf(value);
            case TYPE_CONST_BYTE:
                return (T) Byte.valueOf(value);
            case TYPE_CONST_CHAR:
                return (T) Character.valueOf(value.charAt(0));
            case TYPE_CONST_SHORT:
                return (T) Short.valueOf(value);
            case TYPE_CONST_INT:
                return (T) Integer.valueOf(value);
            case TYPE_CONST_LONG:
                return (T) Long.valueOf(value);
            case TYPE_CONST_FLOAT:
                return (T) Float.valueOf(value);
            case TYPE_CONST_DOUBLE:
                return (T) Double.valueOf(value);
            default:
                throw new IllegalArgumentException("The 'valus' must be a primitive value or its wrapper class instance.");
        }
    }

    /**
     * 문자열 데이터를 Primitive Type 에 해당하는 값으로 변환한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 3. 15.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     *
     * @param <T>
     * @param primitiveType
     *            데이터 타입
     * @param value
     *            데이터 문자열
     * @param unsigned
     *            <b>{@code primitiveType}</b>이 int ({@link Integer}), long ({@link Long})인 경우 unsigned 여부
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code primitiveType})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 15.
     * @version 1.8.0
     * 
     */
    public static <T> T toPrimitiveTypeValue(Class<T> primitiveType, String value, boolean unsigned) {
        Objects.requireNonNull(primitiveType);

        switch (getTypeConst(primitiveType)) {
            case TYPE_CONST_BOOLEAN:
            case TYPE_CONST_BYTE:
            case TYPE_CONST_CHAR:
            case TYPE_CONST_SHORT:
            case TYPE_CONST_FLOAT:
            case TYPE_CONST_DOUBLE:
                return toPrimitiveTypeValue(primitiveType, value);
            case TYPE_CONST_INT:
                return unsigned ? (T) ((Integer) Integer.parseUnsignedInt(value)) : toPrimitiveTypeValue(primitiveType, value);
            case TYPE_CONST_LONG:
                return unsigned ? (T) ((Long) Long.parseUnsignedLong(value)) : toPrimitiveTypeValue(primitiveType, value);
            default:
                throw new IllegalArgumentException("The 'valus' must be a primitive value or its wrapper class instance.");
        }
    }

    /**
     * 주어진 {@link Class}가 Wrapper 클래스인 경우 대응하는 Primitive 타입을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 12. 3.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     *
     * @param srcType
     * @return
     *
     * @throws NullPointerException
     *             파라미터({@code srcType})가 {@code null}인 경우 발생.
     * 
     * @since 2021. 12. 3.
     * @version 1.8.0
     * 
     */
    public static Class<?> translateToPrimitive(Class<?> srcType) {
        Objects.requireNonNull(srcType);

        if (Boolean.class.equals(srcType)) {
            return boolean.class;
        }

        if (Byte.class.equals(srcType)) {
            return byte.class;
        }

        if (Character.class.equals(srcType)) {
            return char.class;
        }

        if (Integer.class.equals(srcType)) {
            return int.class;
        }

        if (Long.class.equals(srcType)) {
            return long.class;
        }

        if (Float.class.equals(srcType)) {
            return float.class;
        }

        if (Double.class.equals(srcType)) {
            return double.class;
        }

        if (Short.class.equals(srcType)) {
            return short.class;
        }

        return srcType;
    }

    /**
     * Primitive 데이터 타입에 맞는 Wrapper 클래스 타입을 반환합니다.
     * 
     * @param primitiveType
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code primitiveType})가 {@code null}인 경우 발생.
     */
    public static Class<?> translateToWrapper(Class<?> primitiveType) {
        Objects.requireNonNull(primitiveType);

        if (boolean.class.equals(primitiveType)) {
            return Boolean.class;
        }

        if (byte.class.equals(primitiveType)) {
            return Byte.class;
        }

        if (char.class.equals(primitiveType)) {
            return Character.class;
        }

        if (int.class.equals(primitiveType)) {
            return Integer.class;
        }

        if (long.class.equals(primitiveType)) {
            return Long.class;
        }

        if (float.class.equals(primitiveType)) {
            return Float.class;
        }

        if (double.class.equals(primitiveType)) {
            return Double.class;
        }

        if (short.class.equals(primitiveType)) {
            return Short.class;
        }

        return primitiveType;
    }

    /**
     * Primitive 타입 배열을 Wrapper 클래스 배열로 반환합니다.
     * 
     * @param componentType
     * @param array
     *            Primitive 타입 배열
     * @return
     */
    public static <T> T[] wrapClassArray(Class<?> componentType, Object array) {
        ObjectUtils.requireNonNulls(componentType, array);

        if (boolean.class.equals(componentType)) {
            return (T[]) ArrayUtils.toWrapperArray((boolean[]) array);
        } else //
        if (char.class.equals(componentType)) {
            return (T[]) ArrayUtils.toWrapperArray((char[]) array);
        } else //
        if (byte.class.equals(componentType)) {
            return (T[]) ArrayUtils.toWrapperArray((byte[]) array);
        } else //
        if (short.class.equals(componentType)) {
            return (T[]) ArrayUtils.toWrapperArray((short[]) array);
        } else //
        if (int.class.equals(componentType)) {
            return (T[]) ArrayUtils.toWrapperArray((int[]) array);
        } else //
        if (long.class.equals(componentType)) {
            return (T[]) ArrayUtils.toWrapperArray((long[]) array);
        } else //
        if (float.class.equals(componentType)) {
            return (T[]) ArrayUtils.toWrapperArray((float[]) array);
        } else //
        if (double.class.equals(componentType)) {
            return (T[]) ArrayUtils.toWrapperArray((double[]) array);
        } else {
            throw new IllegalArgumentException(String.format("허용하지 않는 데이터 유형입니다. (%s)", array.getClass().getComponentType().toString()));
        }
    }
}
