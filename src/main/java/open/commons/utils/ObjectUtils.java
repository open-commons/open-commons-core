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
 * This file is generated under this project, "open-commons-core".
 *
 * Date  : 2018. 1. 31. 오후 1:39:21
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import open.commons.annotation.Getter;
import open.commons.annotation.Information;
import open.commons.annotation.Setter;
import open.commons.lang.Char;

/**
 * Object 타입의 데이터 처리를 지원하는 유틸리티 클래스.
 * 
 * @since 2018. 1. 31.
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public class ObjectUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ObjectUtils.class);

    private static final Function<Object, Short> shortFunction = o -> Short.parseShort(o.toString());
    private static final Function<Object, Byte> byteFunction = o -> Byte.parseByte(o.toString());
    private static final Function<Object, Integer> intFunction = o -> Integer.parseInt(o.toString());
    private static final Function<Object, Long> longFunction = o -> Long.parseLong(o.toString());
    private static final Function<Object, Float> floatFunction = o -> Float.parseFloat(o.toString());
    private static final Function<Object, Double> doubleFunction = o -> Double.parseDouble(o.toString());
    private static final Function<Object, Boolean> booleanFunction = o -> Boolean.parseBoolean(o.toString());
    private static final Function<Object, String> strFunction = o -> o.toString();

    /**
     * Primitive type classes
     * 
     * @since 2020. 12. 22.
     * @version 1.8.0
     */
    private static final Set<Class<?>> PRIMITIVES = new HashSet<>();

    /**
     * Wrapper type classes
     * 
     * @since 2020. 12. 22.
     * @version 1.8.0
     */
    private static final Set<Class<?>> WRAPPER_TYPES = new HashSet<>();
    static {
        // begin: primitive types
        PRIMITIVES.add(boolean.class);
        PRIMITIVES.add(byte.class);
        PRIMITIVES.add(char.class);
        PRIMITIVES.add(double.class);
        PRIMITIVES.add(float.class);
        PRIMITIVES.add(int.class);
        PRIMITIVES.add(long.class);
        PRIMITIVES.add(short.class);
        // end: primitive types

        // begin: wrapper types
        WRAPPER_TYPES.add(Boolean.class);
        WRAPPER_TYPES.add(Byte.class);
        WRAPPER_TYPES.add(Character.class);
        WRAPPER_TYPES.add(Double.class);
        WRAPPER_TYPES.add(Float.class);
        WRAPPER_TYPES.add(Integer.class);
        WRAPPER_TYPES.add(Long.class);
        WRAPPER_TYPES.add(Short.class);
        // end: wrapper types
    }

    private static Function<Method, String> GETTER_KEYGEN = m -> {
        Getter annoGetter = m.getAnnotation(Getter.class);
        return getGSMethodKey(annoGetter.name(), annoGetter.type());
    };

    private static Function<Method, String> SETTER_KEYGEN = m -> {
        Setter annoGetter = m.getAnnotation(Setter.class);
        return getGSMethodKey(annoGetter.name(), annoGetter.type());
    };;

    // Prevent to create a new instance.
    private ObjectUtils() {
    }

    /**
     * 검사 대상 타입이 기준타입과 호환가능한지 여부를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 9. 3.		박준홍			최초 작성
     * </pre>
     *
     * @param target
     *            검사 대상 타입
     * @param standard
     *            기준 타입
     * @return
     *
     * @since 2019. 9. 3.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static boolean checkType(Class<?> target, Class<?> standard) {

        switch (standard.getName()) {
            case "boolean":
            case "java.lang.Boolean":
                return checkType(target, boolean.class, Boolean.class);
            case "byte":
            case "java.lang.Byte":
                return checkType(target, byte.class, Byte.class);
            case "char":
            case "java.lang.Character":
                return checkType(target, char.class, Character.class);
            case "short":
            case "java.lang.Short":
                return checkType(target, short.class, Short.class);
            case "int":
            case "java.lang.Integer":
                return checkType(target, int.class, Integer.class);
            case "long":
            case "java.lang.Long":
                return checkType(target, long.class, Long.class);
            case "float":
            case "java.lang.Float":
                return checkType(target, float.class, Float.class);
            case "double":
            case "java.lang.Double":
                return checkType(target, double.class, Double.class);
            default:
                return standard.isAssignableFrom(target);
        }
    }

    /**
     * 검사 대상 타입이 기준 타입과 호환되는지 여부를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 9. 3.		박준홍			최초 작성
     * </pre>
     *
     * @param target
     *            검사 대상 타입
     * @param standards
     *            기준 타입
     * @return
     *
     * @since 2019. 9. 3.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    private static boolean checkType(Class<?> target, Class<?>... standards) {
        for (Class<?> standard : standards) {
            if (standard.isAssignableFrom(target)) {
                return true;
            }
        }
        return false;
    }

    /**
     * {@link Getter}와 {@link Setter}의 식별정보를 생성하여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 22.		박준홍			최초 작성
     * </pre>
     *
     * @param name
     *            데이터 이름
     * @param type
     *            데이터 타입.
     * @return
     * @throws NullPointerException
     *             데이터 이름 또는 타입이 <code>null</code>인 경우.
     *
     * @since 2021. 11. 22.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static final String getGSMethodKey(String name, Class<?> type) throws NullPointerException {
        return String.join(new Char('-'), name, type.toString());
    }

    /**
     * primitive 타입인지 여부를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 12. 22.		박준홍			최초 작성
     * </pre>
     *
     * @param type
     * @return
     *
     * @since 2020. 12. 22.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static boolean isPrimitive(Class<?> type) {
        return PRIMITIVES.contains(type.getClass());
    }

    /**
     * 객체가 primitive 타입인지 여부를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 12. 22.		박준홍			최초 작성
     * </pre>
     *
     * @param obj
     * @return
     *
     * @since 2020. 12. 22.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static boolean isPrimitive(Object obj) {
        return PRIMITIVES.contains(obj.getClass());
    }

    /**
     * wrapper class 타입인지 여부를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 12. 22.		박준홍			최초 작성
     * </pre>
     *
     * @param type
     * @return
     *
     * @since 2020. 12. 22.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static boolean isWrapper(Class<?> type) {
        return WRAPPER_TYPES.contains(type.getClass());
    }

    /**
     * 객체가 wrapper class 타입인지 여부를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 12. 22.		박준홍			최초 작성
     * </pre>
     *
     * @param obj
     * @return
     *
     * @since 2020. 12. 22.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static boolean isWrapper(Object obj) {
        return WRAPPER_TYPES.contains(obj.getClass());
    }

    /**
     * {@link Setter} 어노테이션이 기술된 메소드를 이용하여 {@link Map}으로부터 데이터를 읽어 새로운 객체를 생성한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 9. 3.      박준홍         최초 작성
     * </pre>
     *
     * @param <T>
     * @param type
     *            데이터 타입
     * @param map
     *            데이터를 가지고 있는 {@link Map}
     * @return
     *
     * @since 2019. 9. 3.
     * @version
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * 
     * @see Setter
     */
    public static <T> T load(Class<T> type, Map<String, Object> map) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        T obj = type.newInstance();

        List<Method> methods = AnnotationUtils.getAnnotatedMethodsAll(type, Setter.class);
        boolean accessible = false;
        Setter setter = null;
        String name = null;
        Class<?> paramType = null;
        Object param = null;
        for (Method m : methods) {
            try {
                accessible = m.isAccessible();
                setter = m.getAnnotation(Setter.class);
                name = setter.name();
                paramType = setter.type();

                // Map에 데이터가 없는 경우
                if ((param = map.get(name)) == null) {
                    continue;
                }

                if (!checkType(param.getClass(), paramType)) {
                    throw new IllegalArgumentException(String.format("Required: %s, Input.type: %s, Input.value: %s", paramType, param.getClass(), param));
                }

                m.invoke(obj, param);
            } finally {
                m.setAccessible(accessible);
            }
        }

        return obj;
    }

    /**
     * {@link Information}이 있는 필드 정보를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 3.		박준홍			최초 작성
     * </pre>
     *
     * @param o
     * @return
     *
     * @since 2021. 11. 3.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static List<String> readInformation(Object o) {
        final String fmt = "%-15s = %s";
        final ArrayList<String> info = new ArrayList<>();

        AnnotationUtils.getAnnotatedFieldsAllAsStream(o, Information.class) //
                .sorted((f1, f2) -> {
                    return f1.getName().compareTo(f2.getName());
                }) //
                .forEach(f -> {
                    String value = null;
                    boolean accessible = f.isAccessible();
                    try {
                        f.setAccessible(true);
                        Object v = String.format(fmt, f.getName(), f.get(o));
                        value = v != null ? v.toString() : null;
                    } catch (IllegalArgumentException | IllegalAccessException e) {
                        value = e.getMessage();
                    } finally {
                        f.setAccessible(accessible);
                    }
                    info.add(value);
                }) //
        ;

        return info;
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 1. 31.     박준홍         최초 작성
     * </pre>
     *
     * @param objects
     * @param classType
     * @param function
     * @return
     * @throws UnsupportedOperationException
     * @throws IllegalAccessException
     * @throws InstantiationException
     *
     * @since 2018. 1. 31.
     */
    private static <C extends Collection<Object>, T, R extends Collection<T>> R to(C objects, Class<R> classType, Function<Object, T> function)
            throws UnsupportedOperationException, InstantiationException, IllegalAccessException {

        if (classType.isInterface()) {
            throw new UnsupportedOperationException(
                    "Only support a class type not interface type!!! at " + Number.class.getName() + "." + ThreadUtils.getCurrentMethodName() + "()");
        }

        R r = classType.newInstance();

        if (objects.size() < 1) {
            return r;
        }

        objects.forEach(o -> r.add(function.apply(o)));

        return r;
    }

    /**
     * Object Type {@link Collection}을 Boolean Type {@link Collection}으로 변환한여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 1. 31.     박준홍         최초 작성
     * </pre>
     *
     * @param objects
     * @param classType
     * @return
     * @throws NullPointerException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws UnsupportedOperationException
     *
     * @since 2018. 1. 31.
     */
    public static <C extends Collection<Object>, R extends Collection<Boolean>> R toBoolean(C objects, Class<? extends R> classType)
            throws NullPointerException, UnsupportedOperationException, InstantiationException, IllegalAccessException {
        return to(objects, classType, booleanFunction);
    }

    /**
     * Object Type {@link Collection}을 Double Type {@link Collection}으로 변환한여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 1. 31.     박준홍         최초 작성
     * </pre>
     *
     * @param objects
     * @param classType
     * @return
     * @throws NullPointerException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws UnsupportedOperationException
     *
     * @since 2018. 1. 31.
     */
    public static <C extends Collection<Object>, R extends Collection<Byte>> R toByte(C objects, Class<? extends R> classType)
            throws NullPointerException, UnsupportedOperationException, InstantiationException, IllegalAccessException {
        return to(objects, classType, byteFunction);
    }

    /**
     * Object Type {@link Collection}을 Double Type {@link Collection}으로 변환한여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 1. 31.     박준홍         최초 작성
     * </pre>
     *
     * @param objects
     * @param classType
     * @return
     * @throws NullPointerException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws UnsupportedOperationException
     *
     * @since 2018. 1. 31.
     */
    public static <C extends Collection<Object>, R extends Collection<Double>> R toDouble(C objects, Class<? extends R> classType)
            throws NullPointerException, UnsupportedOperationException, InstantiationException, IllegalAccessException {
        return to(objects, classType, doubleFunction);
    }

    /**
     * Object Type {@link Collection}을 Float Type {@link Collection}으로 변환한여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 1. 31.     박준홍         최초 작성
     * </pre>
     *
     * @param objects
     * @param classType
     * @return
     * @throws NullPointerException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws UnsupportedOperationException
     *
     * @since 2018. 1. 31.
     */
    public static <C extends Collection<Object>, R extends Collection<Float>> R toFloat(C objects, Class<? extends R> classType)
            throws NullPointerException, UnsupportedOperationException, InstantiationException, IllegalAccessException {
        return to(objects, classType, floatFunction);
    }

    /**
     * Object Type {@link Collection}을 Integer Type {@link Collection}으로 변환한여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 1. 31.     박준홍         최초 작성
     * </pre>
     *
     * @param objects
     * @param classType
     * @return
     * @throws NullPointerException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws UnsupportedOperationException
     *
     * @since 2018. 1. 31.
     */
    public static <C extends Collection<Object>, R extends Collection<Integer>> R toInteger(C objects, Class<? extends R> classType)
            throws NullPointerException, UnsupportedOperationException, InstantiationException, IllegalAccessException {
        return to(objects, classType, intFunction);
    }

    /**
     * Object Type {@link Collection}을 Long Type {@link Collection}으로 변환한여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 1. 31.     박준홍         최초 작성
     * </pre>
     *
     * @param objects
     * @param classType
     * @return
     * @throws NullPointerException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws UnsupportedOperationException
     *
     * @since 2018. 1. 31.
     */
    public static <C extends Collection<Object>, R extends Collection<Long>> R toLong(C objects, Class<? extends R> classType)
            throws NullPointerException, UnsupportedOperationException, InstantiationException, IllegalAccessException {
        return to(objects, classType, longFunction);
    }

    /**
     * Object Type {@link Collection}을 Double Type {@link Collection}으로 변환한여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 1. 31.     박준홍         최초 작성
     * </pre>
     *
     * @param objects
     * @param classType
     * @return
     * @throws NullPointerException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws UnsupportedOperationException
     *
     * @since 2018. 1. 31.
     */
    public static <C extends Collection<Object>, R extends Collection<Short>> R toShort(C objects, Class<? extends R> classType)
            throws NullPointerException, UnsupportedOperationException, InstantiationException, IllegalAccessException {
        return to(objects, classType, shortFunction);
    }

    /**
     * Object Type {@link Collection}을 String Type {@link Collection}으로 변환한여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 1. 31.     박준홍         최초 작성
     * </pre>
     *
     * @param objects
     * @param classType
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws UnsupportedOperationException
     *
     * @since 2018. 1. 31.
     */
    public static <C extends Collection<Object>, R extends Collection<String>> R toString(C objects, Class<? extends R> classType)
            throws UnsupportedOperationException, InstantiationException, IllegalAccessException {
        return to(objects, classType, strFunction);
    }

    /**
     * {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 7. 11.     박준홍         최초 작성
     * </pre>
     *
     * @param <S>
     *            입력 데이터 타입 정의.
     * @param <D>
     *            신규 데이터 타입 정의.
     * @param src
     *            입력 데이타
     * @param lookupSrcSuper
     *            입력 데이타 클래스 상위 인터페이스/클래스 확장 여부
     * @param targetType
     *            변환 타입. 기본생성자가 반드시 있어야 한다.
     * @return
     *
     * @since 2019. 7. 11.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @see #transform(Object, boolean, Class, boolean)
     */
    public static <S, D> D transform(S src, boolean lookupSrcSuper, Class<D> targetType) {
        return transform(src, lookupSrcSuper, targetType, false);
    }

    /**
     * {@link Getter}, {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 7. 11.		박준홍			최초 작성
     * </pre>
     *
     * @param <S>
     *            입력 데이터 타입 정의.
     * @param <D>
     *            신규 데이터 타입 정의.
     * @param src
     *            입력 데이타
     * @param lookupSrcSuper
     *            입력 데이타 클래스 상위 인터페이스/클래스 확장 여부
     * @param targetType
     *            변환 타입. 기본생성자가 반드시 있어야 한다.
     * @param lookupTargetSuper
     *            변환 대상 클래스 상위 인터페이스/클래스 확장 여부
     * @return
     *
     * @since 2019. 7. 11.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <S, D> D transform(S src, boolean lookupSrcSuper, Class<D> targetType, boolean lookupTargetSuper) {
        try {
            return transform(src, lookupSrcSuper, targetType.newInstance(), lookupTargetSuper);
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * {@link Getter}, {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 22.		박준홍			최초 작성
     * </pre>
     *
     * @param <S>
     *            입력 데이터 타입 정의.
     * @param <D>
     *            신규 데이터 타입 정의.
     * @param src
     *            입력 데이타
     * @param lookupSrcSuper
     *            입력 데이타 클래스 상위 인터페이스/클래스 확장 여부
     * @param targetType
     *            변환 타입. 기본생성자가 반드시 있어야 한다.
     * @param lookupTargetSuper
     *            변환 대상 클래스 상위 인터페이스/클래스 확장 여부
     * @param converter
     *            데이터 변환 함수
     * @return
     *
     * @since 2021. 11. 22.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <S, D> D transform(S src, boolean lookupSrcSuper, Class<D> targetType, boolean lookupTargetSuper, Map<String, Function<Object, Object>> converter) {
        try {
            return transform(src, lookupSrcSuper, targetType.newInstance(), lookupTargetSuper, converter);
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 22.     박준홍         최초 작성
     * </pre>
     *
     * @param <S>
     *            입력 데이터 타입 정의.
     * @param <D>
     *            신규 데이터 타입 정의.
     * @param src
     *            입력 데이타
     * @param lookupSrcSuper
     *            입력 데이타 클래스 상위 인터페이스/클래스 확장 여부
     * @param targetType
     *            변환 타입. 기본생성자가 반드시 있어야 한다.
     * @param converter
     *            데이터 변환 함수
     * @return
     *
     * @since 2021. 11. 22.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @see #transform(Object, boolean, Class, boolean)
     */
    public static <S, D> D transform(S src, boolean lookupSrcSuper, Class<D> targetType, Map<String, Function<Object, Object>> converter) {
        return transform(src, lookupSrcSuper, targetType, false, converter);
    }

    /**
     * {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 12. 08.     박준홍         최초 작성
     * </pre>
     *
     * @param <S>
     *            입력 데이터 타입 정의.
     * @param <D>
     *            신규 데이터 타입 정의.
     * @param src
     *            입력 데이타
     * @param lookupSrcSuper
     *            입력 데이타 클래스 상위 인터페이스/클래스 확장 여부
     * @param target
     *            데이터를 전달받은 객체.
     * @return
     *
     * @since 2020. 12. 08.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @see #transform(Object, boolean, Class, boolean)
     */
    public static <S, D> D transform(S src, boolean lookupSrcSuper, D target) {
        return transform(src, lookupSrcSuper, target, false);
    }

    /**
     * {@link Getter}, {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 12. 08.     박준홍         최초 작성
     * </pre>
     *
     * @param <S>
     *            입력 데이터 타입 정의.
     * @param <D>
     *            신규 데이터 타입 정의.
     * @param src
     *            입력 데이타
     * @param lookupSrcSuper
     *            입력 데이타 클래스 상위 인터페이스/클래스 확장 여부
     * @param target
     *            데이터를 전달받은 객체.
     * @param lookupTargetSuper
     *            대상 객체 상위 인터페이스/클래스 확장 여부
     * @return
     *
     * @since 2020. 12. 08.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <S, D> D transform(S src, boolean lookupSrcSuper, D target, boolean lookupTargetSuper) {
        return transform(src, lookupSrcSuper, target, lookupTargetSuper, null);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 12. 08.     박준홍         최초 작성
     * 2021. 11. 22.		박준홍      Map&lt;String, Function&lt;Object, Object&gt;&gt; 추가
     * </pre>
     *
     * @param <S>
     *            입력 데이터 타입 정의.
     * @param <D>
     *            신규 데이터 타입 정의.
     * @param src
     *            입력 데이타
     * @param lookupSrcSuper
     *            입력 데이타 클래스 상위 인터페이스/클래스 확장 여부
     * @param target
     *            데이터를 전달받은 객체.
     * @param lookupTargetSuper
     *            대상 객체 상위 인터페이스/클래스 확장 여부
     * @param converter
     *            데이터 변환 함수
     * @return
     *
     * @since 2021. 11. 22.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <S, D> D transform(S src, boolean lookupSrcSuper, D target, boolean lookupTargetSuper, Map<String, Function<Object, Object>> converter) {
        AssertUtils.assertNulls("'source' object or 'target' type MUST NOT be null !!!", IllegalArgumentException.class, src, target);

        List<Method> getters = lookupSrcSuper ? AnnotationUtils.getAnnotatedMethodsAll(src, Getter.class) : AnnotationUtils.getAnnotatedMethods(src, Getter.class);
        if (getters.size() < 1) {
            return null;
        }

        List<Method> setters = lookupTargetSuper ? AnnotationUtils.getAnnotatedMethodsAll(target, Setter.class) : AnnotationUtils.getAnnotatedMethods(target, Setter.class);
        if (setters.size() < 1) {
            return target;
        }

        // 데이터 변환함수가 null 인 경우
        if (converter == null) {
            converter = new HashMap<>();
        }

        // #0. Setter 메소드 재정렬
        // key: #getGSMethodKey(String, Class)
        // value: Method
        final Map<String, Method> setterMap = CollectionUtils.toMapHSV(setters, SETTER_KEYGEN, m -> m);

        // #1. Setter와 동일한 식별정보를 갖는 Getter 추출
        List<Method> gettersFiltered = getters.stream().filter(m -> {
            return setterMap.containsKey(GETTER_KEYGEN.apply(m));
        }).collect(Collectors.toList());

        // #2. Setter와 연결되는 Getter 메소드 재정렬
        Map<String, Method> getterMap = CollectionUtils.toMapHSV(gettersFiltered, GETTER_KEYGEN, m -> m);

        // method key
        String methodKey = null;
        // getter method
        Method getter = null;
        boolean getterAccessible = false;
        // setter method
        Method setter = null;
        boolean setterAccessible = false;

        Object o = null;
        for (Entry<String, Method> entry : setterMap.entrySet()) {
            try {
                methodKey = entry.getKey();

                // 데이터 제공 함수
                getter = getterMap.get(methodKey);

                // 대상 타입에 정의된 Setter에 해당하는 Getter이 없는 경우
                if (getter == null) {
                    continue;
                }

                // 데이터 읽기
                getterAccessible = getter.isAccessible();
                getter.setAccessible(true);

                // PATCH [2021. 11. 22.]: 데이터 변환 | Park_Jun_Hong_(fafanmama_at_naver_com)
                o = getter.invoke(src);
                if (converter.containsKey(methodKey)) {
                    o = ((Function<Object, Object>) converter.get(methodKey)).apply(o);
                }

                // 데이터 쓰기
                setter = entry.getValue();
                setterAccessible = setter.isAccessible();
                setter.setAccessible(true);

                setter.invoke(target, o);

            } catch (Exception e) {
                String errMsg = String.format("src.type=%s, src.getter=%s, target.type=%s, target.setter=%s, argument=%s, 원인=%s" //
                        , src.getClass(), getter, target.getClass(), setter, o, e.getMessage());
                LOGGER.error(errMsg, e);
                throw new IllegalArgumentException(errMsg, e);
            } finally {
                if (getter != null) {
                    getter.setAccessible(getterAccessible);
                }
                if (setter != null) {
                    setter.setAccessible(setterAccessible);
                }
            }
        }

        return target;
    }

    /**
     * {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 12. 08.     박준홍         최초 작성
     * </pre>
     *
     * @param <S>
     *            입력 데이터 타입 정의.
     * @param <D>
     *            신규 데이터 타입 정의.
     * @param src
     *            입력 데이타
     * @param lookupSrcSuper
     *            입력 데이타 클래스 상위 인터페이스/클래스 확장 여부
     * @param target
     *            데이터를 전달받은 객체.
     * @param converter
     *            데이터 변환 함수
     * @return
     *
     * @since 2020. 12. 08.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @see #transform(Object, boolean, Class, boolean)
     */
    public static <S, D> D transform(S src, boolean lookupSrcSuper, D target, Map<String, Function<Object, Object>> converter) {
        return transform(src, lookupSrcSuper, target, false, converter);
    }

    /**
     * 입력데이터 타입에서 정의된 메소드 중에서 {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로 제공합니다.
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 6. 20.     박준홍         최초 작성
     * </pre>
     *
     * @param <S>
     *            입력 데이터 타입 정의.
     * @param <D>
     *            신규 데이터 타입 정의.
     * @param src
     *            입력 데이타
     * @param targetType
     *            변환 타입
     * @return
     *
     * @since 2019. 6. 20.
     * @version
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @see Getter
     * @see Setter
     */
    public static <S, D> D transform(S src, Class<D> targetType) {
        return transform(src, false, targetType, false);
    }

    /**
     * 입력데이터 타입에서 정의된 메소드 중에서 {@link Getter}, {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 7. 11.     박준홍         최초 작성
     * </pre>
     *
     * @param <S>
     *            입력 데이터 타입 정의.
     * @param <D>
     *            신규 데이터 타입 정의.
     * @param src
     *            입력 데이타.
     * @param targetType
     *            변환 타입. 기본생성자가 반드시 있어야 한다.
     * @param lookupTargetSuper
     *            변환 대상 클래스 상위 인터페이스/클래스 확장 여부
     * @return
     *
     * @since 2019. 7. 11.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <S, D> D transform(S src, Class<D> targetType, boolean lookupTargetSuper) {
        return transform(src, false, targetType, lookupTargetSuper);
    }

    /**
     * 입력데이터 타입에서 정의된 메소드 중에서 {@link Getter}, {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 22.     박준홍         최초 작성
     * </pre>
     *
     * @param <S>
     *            입력 데이터 타입 정의.
     * @param <D>
     *            신규 데이터 타입 정의.
     * @param src
     *            입력 데이타.
     * @param targetType
     *            변환 타입. 기본생성자가 반드시 있어야 한다.
     * @param lookupTargetSuper
     *            변환 대상 클래스 상위 인터페이스/클래스 확장 여부
     * @param converter
     *            데이터 변환 함수
     * @return
     *
     * @since 2021. 11. 22.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <S, D> D transform(S src, Class<D> targetType, boolean lookupTargetSuper, Map<String, Function<Object, Object>> converter) {
        return transform(src, false, targetType, lookupTargetSuper, converter);
    }

    /**
     * 입력데이터 타입에서 정의된 메소드 중에서 {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로 제공합니다.
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 22.     박준홍         최초 작성
     * </pre>
     *
     * @param <S>
     *            입력 데이터 타입 정의.
     * @param <D>
     *            신규 데이터 타입 정의.
     * @param src
     *            입력 데이타
     * @param targetType
     *            변환 타입
     * @param converter
     *            데이터 변환 함수.
     * @return
     *
     * @since 2021. 11. 22.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @see Getter
     * @see Setter
     */
    public static <S, D> D transform(S src, Class<D> targetType, Map<String, Function<Object, Object>> converter) {
        return transform(src, false, targetType, false, converter);
    }

    /**
     * 입력데이터 타입에서 정의된 메소드 중에서 {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로 제공합니다.
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 12. 08.     박준홍         최초 작성
     * </pre>
     *
     * @param <S>
     *            입력 데이터 타입 정의.
     * @param <D>
     *            신규 데이터 타입 정의.
     * @param src
     *            입력 데이타
     * @param target
     *            데이터를 전달받은 객체.
     * @return
     *
     * @since 2020. 12. 08.
     * @version
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @see Getter
     * @see Setter
     */
    public static <S, D> D transform(S src, D target) {
        return transform(src, false, target, false);
    }

    /**
     * 입력데이터 타입에서 정의된 메소드 중에서 {@link Getter}, {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 7. 11.     박준홍         최초 작성
     * </pre>
     *
     * @param <S>
     *            입력 데이터 타입 정의.
     * @param <D>
     *            신규 데이터 타입 정의.
     * @param src
     *            입력 데이타.
     * @param target
     *            데이터를 전달받은 객체.
     * @param lookupTargetSuper
     *            변환 대상 클래스 상위 인터페이스/클래스 확장 여부
     * @return
     *
     * @since 2019. 7. 11.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <S, D> D transform(S src, D target, boolean lookupTargetSuper) {
        return transform(src, false, target, lookupTargetSuper);
    }

    /**
     * 입력데이터 타입에서 정의된 메소드 중에서 {@link Getter}, {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 22.     박준홍         최초 작성
     * </pre>
     *
     * @param <S>
     *            입력 데이터 타입 정의.
     * @param <D>
     *            신규 데이터 타입 정의.
     * @param src
     *            입력 데이타.
     * @param target
     *            데이터를 전달받은 객체.
     * @param lookupTargetSuper
     *            변환 대상 클래스 상위 인터페이스/클래스 확장 여부
     * @param converter
     *            데이터 변환 함수
     * @return
     *
     * @since 2021. 11. 22.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <S, D> D transform(S src, D target, boolean lookupTargetSuper, Map<String, Function<Object, Object>> converter) {
        return transform(src, false, target, lookupTargetSuper, converter);
    }

    /**
     * 입력데이터 타입에서 정의된 메소드 중에서 {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로 제공합니다.
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11.22.     박준홍         최초 작성
     * </pre>
     *
     * @param <S>
     *            입력 데이터 타입 정의.
     * @param <D>
     *            신규 데이터 타입 정의.
     * @param src
     *            입력 데이타
     * @param target
     *            데이터를 전달받은 객체.
     * @param converter
     *            데이터 변환 함수
     * @return
     *
     * @since 2021. 11. 22.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @see Getter
     * @see Setter
     */
    public static <S, D> D transform(S src, D target, Map<String, Function<Object, Object>> converter) {
        return transform(src, false, target, false, converter);
    }
}
