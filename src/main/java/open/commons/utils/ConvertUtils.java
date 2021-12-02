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
package open.commons.utils;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import open.commons.annotation.Getter;
import open.commons.annotation.Setter;
import open.commons.io.IMarshaller;
import open.commons.lang.JavaField;

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

    /**
     * <ul>
     * <li>key: Source 타입 정보와 Target 타입 정보를 이용하여 생성한 식별정보
     * <li>value: Source 객체를 Target 객체로 변환하는데 필요한 데이터 변환 함수들.
     * </ul>
     */
    private static final ConcurrentSkipListMap<String, Function<?, ?>> FIELD_CONVERTERS = new ConcurrentSkipListMap<>();

    /**
     * @param srcClass
     *            변환 이전 데이터 타입
     * @param targetClass
     *            변환 이후 데이터 타입
     */
    private static final BiFunction<Class<?>, Class<?>, String> FC_KEYGEN = (srcClass, targetClass) -> String.join(" -> ", srcClass.toGenericString(),
            targetClass.toGenericString());

    public static <T> void assertValue(Object value, Class<T> class_) {
        assertValue(value, class_, IllegalArgumentException.class);
    }

    /**
     * 
     * @param value
     * @param class_
     * @param occurExeption
     *            {@link String}를 파라미터로 받는 생성자를 제공해야 한다.
     */
    public static <T> void assertValue(Object value, Class<T> class_, Class<? extends RuntimeException> occurExeption) {

        AssertUtils.assertNulls("Neither value and class_ MUST be null.", value, class_);

        Class<?> translatedClass = translateToWrapper(class_);

        if (!translatedClass.isAssignableFrom(value.getClass())) {
            try {

                Constructor<? extends RuntimeException> cons = occurExeption != null ? occurExeption.getConstructor(String.class)
                        : IllegalArgumentException.class.getConstructor(String.class);

                throw (RuntimeException) cons.newInstance("value's type MUST be " + class_ + (!class_.equals(translatedClass) ? " or " + translatedClass.getSimpleName() : "")
                        + ". value: " + value + " (" + value.getClass() + ")");

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 변환 이전/이후 데이터 타입에 맞는 함수목록을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 12. 2.     박준홍         최초 작성
     * </pre>
     *
     * @param <S>
     *            Source Type
     * @param <T>
     *            Target Type
     * @param srcClass
     *            변환 이전 데이터 타입
     * @param targetClass
     *            변환 이후 데이터 타입
     * @return
     *
     * @since 2021. 12. 2.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public static <S, T> Map<String, Function<?, ?>> createConverter(Class<S> srcClass, Class<T> targetClass) {

        Map<String, Class<?>> srcFieldsMeta = AnnotationUtils.getAnnotatedFieldsAll(srcClass, Getter.class).stream() //
                .map(field -> field.getAnnotation(Getter.class)) //
                .collect(Collectors.toMap(anno -> anno.name(), anno -> anno.type()));

        Map<String, Class<?>> targetFieldsMeta = AnnotationUtils.getAnnotatedFieldsAll(targetClass, Setter.class).stream() //
                .map(field -> field.getAnnotation(Setter.class)) //
                .collect(Collectors.toMap(anno -> anno.name(), anno -> anno.type()));

        Map<String, Function<?, ?>> converters = new HashMap<>();

        Class<?> srcType = null;
        Class<?> targetType = null;
        Function<?, ?> converter = null;
        for (Entry<String, Class<?>> entry : targetFieldsMeta.entrySet()) {
            // srcClass에 동일한 이름을 갖는 필드가 없거나
            if ((srcType = srcFieldsMeta.get(entry.getKey())) == null //
                    || // targetClass 필드와 타입이 같은 경우
                    (targetType = entry.getValue()).equals(srcType) //
                    || // targetClass 필드 타입이 srcClass 필드 타입의 상위인 경우
                    isAssignableFrom(targetClass, srcType)) {
                continue;
            }

            // 타입 변환 함수가 존재하는 경우
            if ((converter = FIELD_CONVERTERS.get(FC_KEYGEN.apply(srcType, targetType))) != null) {
                converters.put(ObjectUtils.getGSMethodKey(entry.getKey(), entry.getValue()), converter);
            }
        }

        return converters;
    }

    public static JavaField defineToJavaField(String type, String string) {
        String tmpString = string.trim();
        String comment = StringUtils.getComment(tmpString);
        tmpString = StringUtils.removeComment(tmpString).trim();
        String accessor = "public static final";
        String var = StringUtils.enclosingSmallestString(tmpString, "#define", "(").trim();
        String value = StringUtils.enclosingLargestString(tmpString, "(", ")").trim();

        return new JavaField(accessor, type, var, value, comment);
    }

    public static String defineToJavaIntField(String string) {
        string = string.replace("#define", "public static final int");
        string = string.replaceAll("( |\t)*\\(( |\t)*", " = ");
        string = string.replaceAll("( |\t)*\\)( |\t)*", ";");
        String comment = StringUtils.getComment(string);
        if (comment != null && !comment.isEmpty()) {
            comment = "/** " + comment.trim() + " */\n";
        } else {
            comment = "";
        }

        return comment + StringUtils.removeComment(string);
    }

    public static String defineToJavaStringField(String string) {
        String str1 = string.replace("#define", "public static final String");
        String str2 = str1.replaceAll("( |\t)*\\(( |\t)*", " = ");
        String str3 = str2.replaceAll("( |\t)*\\)( |\t)*", ";");
        String comment = StringUtils.getComment(string);
        if (comment != null && !comment.isEmpty()) {
            comment = "/** " + comment.trim() + " */\n";
        } else {
            comment = "";
        }
        return comment + StringUtils.removeComment(str3);
    }

    /**
     * @param class_
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
     */
    public static byte getTypeConst(Class<?> class_) {
        return primitiveTypesConst.containsKey(class_) ? primitiveTypesConst.get(class_) : TYPE_CONST_OBJECT;
    }

    /**
     * 
     * @param class_
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
     */
    public static Class<?> getWrapperClass(Class<?> class_) {
        return primitiveTypesWrapperClass.containsKey(class_) ? primitiveTypesWrapperClass.get(class_) : class_;
    }

    /**
     * 입력 타입이 대상 타입과 같거나 상위 타입인지 여부를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 12. 2.		박준홍			최초 작성
     * </pre>
     *
     * @param srcType
     *            입력 타입.
     * @param targetType
     *            대상 타입.
     * @return
     *
     * @since 2021. 12. 2.
     * @version _._._
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static boolean isAssignableFrom(Class<?> srcType, Class<?> targetType) {

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
     * 2021. 12. 2.		박준홍			최초 작성
     * </pre>
     *
     * @param srcObject
     *            입력 타입.
     * @param targetObject
     *            대상 타입.
     * @return
     *
     * @since 2021. 12. 2.
     * @version _._._
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <S, T> boolean isAssignableFrom(S srcObject, T targetObject) {
        return isAssignableFrom(srcObject.getClass(), targetObject.getClass());
    }

    public static boolean isDefineInteger(String string) {
        String regEx = "( |\t)*#define( |\t)+[a-zA-Z_$][a-zA-Z_$0-9]*( |\t)*\\(( |\t)*(-)?[0-9]+( |\t)*\\)" + REGEX_BT + "*" + REGEX_COMMENT + "?" + REGEX_BT + "*";
        return Pattern.matches(regEx, string);
    }

    public static boolean isDefineString(String string) {
        String regEx = "( |\t)*#define( |\t)+[a-zA-Z_$][a-zA-Z_$0-9]*( |\t)*\\(( |\t)*\"(.)*\"( |\t)*\\)" + REGEX_BT + "*" + REGEX_COMMENT + "?" + REGEX_BT + "*";
        return Pattern.matches(regEx, string);
    }

    /**
     * 
     * @param value
     * @param marshaller
     *            변환기
     * @return
     */
    public static <T> Object marshall(T value, IMarshaller<T> marshaller) {
        return marshaller != null ? marshaller.marshall(value) : value;
    }

    private static void putTypeConsts(byte typeConst, Class<?>... types) {
        for (Class<?> type : types) {
            primitiveTypesConst.put(type, typeConst);
        }
    }

    /**
     * 클래스 변수 데이터 변환에 필요한 함수를 등록합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 12. 2.     박준홍         최초 작성
     * </pre>
     *
     * @param <S>
     *            Source Type
     * @param <T>
     *            Target Type
     * @param srcClass
     *            변환 이전 데이터 타입
     * @param targetClass
     *            변환 이후 데이터 타입
     * @param converter
     *            속성 변환함수.
     * @return
     * @throws NullPointerException
     *             TODO
     *
     * @since 2021. 12. 2.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public static <S, T> Object registerFieldConverter(Class<S> srcClass, Class<T> targetClass, Function<S, T> converter) throws NullPointerException {

        AssertUtils.assertNulls("타입 및 함수 정보는 반드시 있어야 합니다.", srcClass, targetClass, converter);

        return FIELD_CONVERTERS.put(FC_KEYGEN.apply(srcClass, targetClass), converter);
    }

    /**
     * 
     * @param arr
     * @return
     * 
     * @deprecated Use {@link ArrayUtils#toWrapperArray(boolean[])}, instead of.
     */
    public static Boolean[] toArray(boolean[] arr) {
        Boolean[] array = new Boolean[arr.length];

        for (int i = 0; i < arr.length; i++) {
            array[i] = arr[i];
        }

        return array;
    }

    /**
     * 
     * @param arr
     * @return
     * 
     * @deprecated Use {@link ArrayUtils#toWrapperArray(byte[])}, instead of.
     */
    public static Byte[] toArray(byte[] arr) {
        Byte[] array = new Byte[arr.length];

        for (int i = 0; i < arr.length; i++) {
            array[i] = arr[i];
        }

        return array;
    }

    /**
     * 
     * @param arr
     * @return
     * 
     * @deprecated Use {@link ArrayUtils#toWrapperArray(char[])}, instead of.
     */
    public static Character[] toArray(char[] arr) {
        Character[] array = new Character[arr.length];

        for (int i = 0; i < arr.length; i++) {
            array[i] = arr[i];
        }

        return array;
    }

    /**
     * 
     * @param arr
     * @return
     * 
     * @deprecated Use {@link ArrayUtils#toWrapperArray(double[])}, instead of.
     */
    public static Double[] toArray(double[] arr) {
        Double[] array = new Double[arr.length];

        for (int i = 0; i < arr.length; i++) {
            array[i] = arr[i];
        }

        return array;
    }

    /**
     * 
     * @param arr
     * @return
     * 
     * @deprecated Use {@link ArrayUtils#toWrapperArray(float[])}, instead of.
     */
    public static Float[] toArray(float[] arr) {
        Float[] array = new Float[arr.length];

        for (int i = 0; i < arr.length; i++) {
            array[i] = arr[i];
        }

        return array;
    }

    /**
     * 
     * @param arr
     * @return
     * 
     * @deprecated Use {@link ArrayUtils#toWrapperArray(int[])}, instead of.
     */
    public static Integer[] toArray(int[] arr) {
        Integer[] array = new Integer[arr.length];

        for (int i = 0; i < arr.length; i++) {
            array[i] = arr[i];
        }

        return array;
    }

    /**
     * 
     * @param arr
     * @return
     * 
     * @deprecated Use {@link ArrayUtils#toWrapperArray(long[])}, instead of.
     */
    public static Long[] toArray(long[] arr) {
        Long[] array = new Long[arr.length];

        for (int i = 0; i < arr.length; i++) {
            array[i] = arr[i];
        }

        return array;
    }

    /**
     * 
     * @param arr
     * @return
     * 
     * @deprecated Use {@link ArrayUtils#toWrapperArray(Object)}, instead of.
     */
    public static <T> T[] toArray(Object array) {

        Class<?> componentType = array.getClass().getComponentType();

        if (componentType.isPrimitive()) {

            if (boolean.class.equals(componentType)) {
                return (T[]) toArray((boolean[]) array);
            }

            if (byte.class.equals(componentType)) {
                return (T[]) toArray((byte[]) array);
            }

            if (char.class.equals(componentType)) {
                return (T[]) toArray((char[]) array);
            }

            if (short.class.equals(componentType)) {
                return (T[]) toArray((short[]) array);
            }

            if (int.class.equals(componentType)) {
                return (T[]) toArray((int[]) array);
            }

            if (long.class.equals(componentType)) {
                return (T[]) toArray((long[]) array);
            }

            if (float.class.equals(componentType)) {
                return (T[]) toArray((float[]) array);
            }

            if (double.class.equals(componentType)) {
                return (T[]) toArray((double[]) array);
            }

            return null;

        } else {
            return (T[]) array;
        }
    }

    /**
     * 
     * @param arr
     * @return
     * 
     * @deprecated Use {@link ArrayUtils#toWrapperArray(short[])}, instead of.
     */
    public static Short[] toArray(short[] arr) {
        Short[] array = new Short[arr.length];

        for (int i = 0; i < arr.length; i++) {
            array[i] = arr[i];
        }

        return array;
    }

    public static String toJavaDocSingleLineComment(String string) {
        return "/** " + string + " */";
    }

    /**
     * 
     * @param primitiveType
     * @param value
     * @return
     *
     * @since 2014. 4. 2.
     */
    public static <T> T toPrimitiveTypeValue(Class<T> primitiveType, String value) {

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
     * Primitive 데이터 타입에 맞는 Wrapper 클래스 타입을 반환한다.
     * 
     * @param primitiveType
     * @return
     */
    public static Class<?> translateToWrapper(Class<?> primitiveType) {

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
     * 
     * @param value
     * @param marshaller
     *            변환기
     * @return
     */
    public static <T> T unmarshall(Object value, IMarshaller<T> marshaller) {
        return marshaller != null ? marshaller.unmarshall(value) : (T) value;
    }

    /**
     * Primitive 타입 배열을 Wrapper 클래스 배열로 반환한다.
     * 
     * @param componentType
     * @param array
     *            Primitive 타입 배열
     * @return
     */
    public static <T> T[] wrapClassArray(Class<?> componentType, Object array) {

        T[] wrapperClassArray = null;

        if (boolean.class.equals(componentType)) {
            wrapperClassArray = (T[]) toArray((boolean[]) array);
        } else //
        if (char.class.equals(componentType)) {
            wrapperClassArray = (T[]) toArray((char[]) array);
        } else //
        if (byte.class.equals(componentType)) {
            wrapperClassArray = (T[]) toArray((byte[]) array);
        } else //
        if (short.class.equals(componentType)) {
            wrapperClassArray = (T[]) toArray((short[]) array);
        } else //
        if (int.class.equals(componentType)) {
            wrapperClassArray = (T[]) toArray((int[]) array);
        } else //
        if (long.class.equals(componentType)) {
            wrapperClassArray = (T[]) toArray((long[]) array);
        } else //
        if (float.class.equals(componentType)) {
            wrapperClassArray = (T[]) toArray((float[]) array);
        } else //
        if (double.class.equals(componentType)) {
            wrapperClassArray = (T[]) toArray((double[]) array);
        } else {
            wrapperClassArray = (T[]) array;
        }

        return wrapperClassArray;
    }
}
