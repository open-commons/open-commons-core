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

package open.commons.core.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import open.commons.core.annotation.Getter;
import open.commons.core.annotation.Information;
import open.commons.core.annotation.Setter;
import open.commons.core.function.PentagonFunction;
import open.commons.core.function.QuadFunction;
import open.commons.core.stream.ClassSpliterator;

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

    private static final Pattern METHOD_GETTER = Pattern.compile("^(is|get)(.+)$");
    /**
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 12. 22.        Park_Jun_Hong (parkjunhong77@gmail.com) 최초 작성
     * 2023. 12. 5.		Park_Jun_Hong (parkjunhong77@gmail.com)			데이터 설정함수 prefix 추가 (addXXX 지원)
     * </pre>
     */
    private static final Pattern METHOD_SETTER = Pattern.compile("^(set|add)(.+)$");

    /**
     * Method에 설정된 {@link Setter} 어노테이션 값을 이용해서 "속성" 이름을 제공합니다.<br>
     * 
     * @param method
     *            {@link Method}
     * 
     * @return 속성 이름
     */
    private static Function<Method, String> GETTER_KEYGEN = method -> {
        Getter annoGetter = method.getAnnotation(Getter.class);
        String name = annoGetter.name();
        if (name.trim().isEmpty()) {
            name = method.getName();
            Matcher m = METHOD_GETTER.matcher(name);
            if (m.matches()) {
                if ("is".equals(m.group(1))) {
                    name = StringUtils.toLowerCase(name.substring(2), 0);
                } else /* if ("get".equals(m.group(1))) { */
                {
                    name = StringUtils.toLowerCase(name.substring(3), 0);
                }
            } else {
                // 지원하지 않는 메소드
                name = UUID.randomUUID().toString();
            }
        }
        // return getPropertyKey(annoGetter.name(), annoGetter.type());
        return name;
    };

    /**
     * Method에 설정된 {@link Setter} 어노테이션 값을 이용해서 "속성" 이름을 제공합니다.<br>
     * 
     * @param method
     *            {@link Method}
     * 
     * @return 속성 이름
     */
    private static Function<Method, String> SETTER_KEYGEN = method -> {
        Setter annoSetter = method.getAnnotation(Setter.class);
        String name = annoSetter.name();
        if (name.trim().isEmpty()) {
            name = method.getName();
            Matcher m = METHOD_SETTER.matcher(name);
            if (m.matches()) {
                name = StringUtils.toLowerCase(name.substring(3), 0);
            } else {
                // 지원하지 않는 메소드
                name = UUID.randomUUID().toString();
            }
        }
        // return getPropertyKey(annoSetter.name(), annoSetter.type());
        return name;
    };

    private static final Function<Method, Class<?>> RETURN_TYPE = m -> m.getReturnType();
    private static final Function<Method, Class<?>> PARAMETER_TYPE = m -> m.getParameterTypes()[0];

    /**
     * <ul>
     * <li>key: Source 타입 정보와 Target 타입 정보를 이용하여 생성한 식별정보
     * <li>value: Source 객체를 Target 객체로 변환하는데 필요한 데이터 변환 함수들.
     * </ul>
     */
    private static final ConcurrentSkipListMap<String, Function<?, ?>> FIELD_CONVERTERS = new ConcurrentSkipListMap<>();

    /**
     * 기본 데이터 변환 키 생성 함수.
     * 
     * @param srcFieldClass
     *            변환 이전 속성 데이터 타입
     * @param targetFieldClass
     *            변환 이후 속성 데이터 타입
     */
    // public static final BiFunction<Class<?>, Class<?>, String> FIELD_CONVERTER_KEYGEN = (srcFieldClass,
    // targetFieldClass) -> String.join(" -> ", srcFieldClass.toGenericString(),
    // targetFieldClass.toGenericString());
    /**
     * 기본 데이터 변환 키 생성 함수.
     * 
     * @param srcClass
     *            변환 이전 데이터 타입
     * @param srcPropertyClass
     *            변환 이전 속성 데이터 타입
     * @param property
     *            속성명
     * @param targetClass
     *            변환 이후 데이터 타입
     * @param targetPropertyClass
     *            변환 이후 속성 데이터 타입
     */
    public static final PentagonFunction<Class<?>, Class<?>, String, Class<?>, Class<?>, String> FIELD_CONVERTER_KEYGEN = //
            (srcClass, srcPropertyClass, property, targetClass, targetPropertyClass) //
            -> String.join(" -> " //
                    , String.join("#", Objects.toString(srcClass, "null"), Objects.toString(srcPropertyClass, "null")) //
                    , property //
                    , String.join("#", Objects.toString(targetClass, "null"), Objects.toString(targetPropertyClass, "null")) //
            );

    /**
     * <ul>
     * <li>key: 변환 전/후 데이터 타입 및 상위 클래스 정보 적용 여부가 합쳐진 식별정보
     * <li>value: 변환 함수.
     * </ul>
     */
    private static final ConcurrentSkipListMap<Integer, Function<?, ?>> TYPE_CONVERTERS = new ConcurrentSkipListMap<>();

    /**
     * @param srcClass
     *            입력 데이터 타입
     * @param lookupSrcSuper
     *            입력 데이터 클래스 상위 인터페이스/클래스 확장 여부
     * @param targetClass
     *            데이터를 전달받은 타입.
     * @param lookupTargetSuper
     *            대상 객체 상위 인터페이스/클래스 확장 여부
     */
    public static final QuadFunction<Class<?>, Boolean, Class<?>, Boolean, Integer> TYPE_CONVERTER_KEYGEN //
            = (srcClass, lookupSrcSuper, targetClass, lookupTargetSuper) -> new StringBuffer() //
                    .append(srcClass.toGenericString()).append(":").append(lookupSrcSuper) //
                    .append("->") //
                    .append(targetClass.toGenericString()).append(lookupTargetSuper)//
                    .toString().hashCode();

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
     * 주어진 배열에 <code>null</code>이 포함되어 있는지 여부를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 12. 3.		박준홍			최초 작성
     * </pre>
     * 
     * @param visitValue
     *            배열에 포함된 데이터가 {@link Collection} 또는 배열인 경우 확인 여부
     * @param data
     *            배열
     *
     * @return
     *
     * @since 2021. 12. 3.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static boolean containsNull(boolean visitValue, Collection<?> data) {
        for (Object value : data) {
            if (value == null) {
                return true;
            } else if (visitValue) {
                boolean contains = false;
                if (Collection.class.isAssignableFrom(value.getClass())) {
                    contains = containsNull(visitValue, (Collection<?>) value);
                } //
                else if (value.getClass().isArray()) {
                    contains = containsNull(visitValue, (Object[]) value);
                }

                if (contains) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 주어진 배열에 <code>null</code>이 포함되어 있는지 여부를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 12. 3.		박준홍			최초 작성
     * </pre>
     * 
     * @param visitValue
     *            배열에 포함된 데이터가 {@link Collection} 또는 배열인 경우 확인 여부
     * @param array
     *            배열
     *
     * @return
     *
     * @since 2021. 12. 3.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static boolean containsNull(boolean visitValue, Object... array) {
        return containsNull(visitValue, Arrays.asList(array));
    }

    /**
     * 주어진 배열에 <code>null</code>이 포함되어 있는지 여부를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 12. 3.		박준홍			최초 작성
     * </pre>
     * 
     * @param data
     *            배열
     *
     * @return
     *
     * @since 2021. 12. 3.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static boolean containsNull(Collection<?> data) {
        return containsNull(false, data);
    }

    /**
     * 주어진 배열에 <code>null</code>이 포함되어 있는지 여부를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 12. 3.		박준홍			최초 작성
     * </pre>
     * 
     * @param array
     *            배열
     *
     * @return
     *
     * @since 2021. 12. 3.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static boolean containsNull(Object... array) {
        return containsNull(false, array);
    }

    /**
     * 주어진 정보에 맞는 데이터 변환 함수를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 3. 22.		박준홍			최초 작성
     * </pre>
     *
     * @param <S>
     *            Source Type
     * @param <SF>
     *            Source Field Type
     * @param <T>
     *            Target Type
     * @param <TF>
     *            Target Field Type
     * @param srcClass
     *            변환 이전 데이터 타입
     * @param srcPropertyClass
     *            변환 이전 속성 데이터 타입
     * @param property
     *            속성명
     * @param targetClass
     *            변환 이후 데이터 타입
     * @param targetPropertyClass
     *            변환 이후 속성 데이터 타입
     * @param converters
     *            변환 함수들
     * @return
     *
     * @since 2022. 3. 22.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    private static <S, SF, T, TF> Function<?, ?> getFieldConverter(Class<S> srcClass, Class<SF> srcPropertyClass, String property, Class<T> targetClass,
            Class<TF> targetPropertyClass, Map<String, Function<?, ?>> converters) {

        Function<?, ?> converter = null;
        String funcKey = FIELD_CONVERTER_KEYGEN.apply(srcClass, srcPropertyClass, property, targetClass, targetPropertyClass);
        if ((converter = converters.get(funcKey)) != null) {
            return converter;
        }

        return converters.get(FIELD_CONVERTER_KEYGEN.apply(null, srcPropertyClass, null, null, targetPropertyClass));
    }

    /**
     * 주어진 타입에 대한 추가 타입을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 3. 22.		박준홍			최초 작성
     * </pre>
     *
     * @param type
     * @return
     *
     * @since 2022. 3. 22.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    private static Set<Class<?>> getPropertyConvertedTypes(Class<?> type) {
        Set<Class<?>> types = new HashSet<>();
        types.add(type);

        int is = isPrimitiveOrWrapper(type);
        if (is > 0) {
            types.add(ConvertUtils.translateToWrapper(type));
        } else if (is < 0) {
            types.add(ConvertUtils.translateToPrimitive(type));
        }

        return types;
    }

    /**
     * {@link Getter}와 {@link Setter}의 식별정보를 생성하여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 22.		박준홍			최초 작성
     * 2021. 12. 02.        박준홍     메소드를 찾는 키를 필드 속성명으로 고정.
     * 2022. 3. 22.         박준홍     더 이상 사용하지 않음.
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
     * 
     * @deprecated No Use since 2022.03.22.
     */
    public static final String getPropertyKey(String name, Class<?> type) throws NullPointerException {
        return name;
    }

    /**
     * 주어진 타입 및 조건에 맞는 데이터 변환 함수를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 12. 6.     박준홍         최초 작성
     * </pre>
     *
     * @param <S>
     *            입력 데이터 타입 정의.
     * @param <T>
     *            신규 데이터 타입 정의.
     * @param srcType
     *            입력 데이터 타입
     * @param lookupSrcSuper
     *            입력 데이터 클래스 상위 인터페이스/클래스 확장 여부
     * @param targetType
     *            데이터를 전달받은 객체 타입.
     * @param lookupTargetSuper
     *            대상 객체 상위 인터페이스/클래스 확장 여부
     * @return
     * @throws IllegalArgumentException
     *             입력 데이터 또는 대상 타입이 <code>null</code>인 경우
     *
     * @since 2021. 12. 6.
     * @version 1.8.0
     * @author parkjunhong77@gmail.com
     */
    @SuppressWarnings("unchecked")
    public static <S, T> Function<S, T> getTransformer(Class<S> srcType, boolean lookupSrcSuper, Class<T> targetType, boolean lookupTargetSuper) throws IllegalArgumentException {

        AssertUtils.assertNulls("'source' type or 'target' type MUST NOT be null !!!", IllegalArgumentException.class, srcType, targetType);

        int key = TYPE_CONVERTER_KEYGEN.apply(srcType, lookupSrcSuper, targetType, lookupTargetSuper);
        return (Function<S, T>) MapUtils.getOrDefault(TYPE_CONVERTERS, key,
                (Supplier<Function<?, ?>>) () -> (Function<?, ?>) value -> ObjectUtils.transform(value, lookupSrcSuper, targetType, lookupTargetSuper), true);
    }

    /**
     * 주어진 타입 및 조건에 맞는 데이터 변환 함수를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 12. 6.     박준홍         최초 작성
     * </pre>
     *
     * @param <S>
     *            입력 데이터 타입 정의.
     * @param <T>
     *            신규 데이터 타입 정의.
     * @param srcType
     *            입력 데이터 타입
     * @param target
     *            데이터를 전달받은 객체.
     * @return
     * @throws IllegalArgumentException
     *             입력 데이터 또는 대상 타입이 <code>null</code>인 경우
     *
     * @since 2021. 12. 6.
     * @version 1.8.0
     * @author parkjunhong77@gmail.com
     */
    public static <S, T> Function<S, T> getTransformer(Class<S> srcType, Class<T> target) throws NullPointerException {
        return getTransformer(srcType, false, target, false);
    }

    /**
     * 주어진 타입 및 조건에 맞는 데이터 변환 함수를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 12. 6.		박준홍			최초 작성
     * </pre>
     *
     * @param <S>
     *            입력 데이터 타입 정의.
     * @param <T>
     *            신규 데이터 타입 정의.
     * @param src
     *            입력 데이터
     * @param lookupSrcSuper
     *            입력 데이터 클래스 상위 인터페이스/클래스 확장 여부
     * @param targetType
     *            데이터를 전달받은 객체 타입
     * @param lookupTargetSuper
     *            대상 객체 상위 인터페이스/클래스 확장 여부
     * @return
     * 
     * @throws IllegalArgumentException
     *             입력 데이터 또는 대상 타입이 <code>null</code>인 경우
     *
     * @since 2021. 12. 6.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <S, T> Function<S, T> getTransformer(Collection<S> src, boolean lookupSrcSuper, Class<T> targetType, boolean lookupTargetSuper) {

        AssertUtils.assertNulls("'source' object or 'target' type MUST NOT be null !!!", IllegalArgumentException.class, src, targetType);
        AssertUtils.assertTrue("'source' object MUST NOT be empty !!!", src.isEmpty(), IllegalArgumentException.class);

        return getTransformer(src.iterator().next(), lookupSrcSuper, targetType, lookupTargetSuper);
    }

    /**
     * 주어진 타입 및 조건에 맞는 데이터 변환 함수를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 12. 6.		박준홍			최초 작성
     * </pre>
     *
     * @param <S>
     *            입력 데이터 타입 정의.
     * @param <T>
     *            신규 데이터 타입 정의.
     * @param src
     *            입력 데이터
     * @param target
     *            데이터를 전달받은 객체.
     * @return
     * 
     * @throws IllegalArgumentException
     *             입력 데이터 또는 대상 타입이 <code>null</code>인 경우
     *
     * @since 2021. 12. 6.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <S, T> Function<S, T> getTransformer(Collection<S> src, Class<T> target) {

        AssertUtils.assertNulls("'source' object or 'target' type MUST NOT be null !!!", IllegalArgumentException.class, src, target);
        AssertUtils.assertTrue("'source' object MUST NOT be empty !!!", src.isEmpty(), IllegalArgumentException.class);

        return getTransformer(src, false, target, false);
    }

    /**
     * 주어진 식별정보 맞는 데이터 변환 함수를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 12. 30.     박준홍         최초 작성
     * </pre>
     *
     * @param <S>
     *            입력 데이터 타입 정의.
     * @param <T>
     *            신규 데이터 타입 정의.
     * 
     * @param typeConverterKey
     *            데이터 변환함수 식별정보
     * @return
     * @throws IllegalArgumentException
     *             입력 데이터 또는 대상 타입이 <code>null</code>인 경우
     *
     * @since 2021. 12. 30.
     * @version 1.8.0
     * @author parkjunhong77@gmail.com
     * 
     * @see #TYPE_CONVERTER_KEYGEN
     */
    @SuppressWarnings("unchecked")
    public static <S, T> Function<S, T> getTransformer(int typeConverterKey) throws NullPointerException {
        return (Function<S, T>) TYPE_CONVERTERS.get(typeConverterKey);
    }

    /**
     * 주어진 타입 및 조건에 맞는 데이터 변환 함수를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 12. 30.     박준홍         최초 작성
     * </pre>
     *
     * @param <S>
     *            입력 데이터 타입 정의.
     * @param <T>
     *            신규 데이터 타입 정의.
     * 
     * @param typeConverterKey
     *            데이터 변환함수 식별정보
     * @param srcType
     *            입력 데이터 타입
     * @param lookupSrcSuper
     *            입력 데이터 클래스 상위 인터페이스/클래스 확장 여부
     * @param targetType
     *            데이터를 전달받은 객체.
     * @param lookupTargetSuper
     *            대상 객체 상위 인터페이스/클래스 확장 여부
     * @return
     * @throws IllegalArgumentException
     *             입력 데이터 또는 대상 타입이 <code>null</code>인 경우
     *
     * @since 2021. 12. 30.
     * @version 1.8.0
     * @author parkjunhong77@gmail.com
     */
    @SuppressWarnings("unchecked")
    public static <S, T> Function<S, T> getTransformer(int typeConverterKey, Class<S> srcType, boolean lookupSrcSuper, Class<T> targetType, boolean lookupTargetSuper)
            throws NullPointerException {

        AssertUtils.assertNulls("'source' type or 'target' type MUST NOT be null !!!", IllegalArgumentException.class, srcType, targetType);

        return (Function<S, T>) MapUtils.getOrDefault(TYPE_CONVERTERS, typeConverterKey,
                (Supplier<Function<?, ?>>) () -> (Function<?, ?>) value -> ObjectUtils.transform(value, lookupSrcSuper, targetType, lookupTargetSuper), true);
    }

    /**
     * 주어진 타입 및 조건에 맞는 데이터 변환 함수를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 12. 6.     박준홍         최초 작성
     * </pre>
     *
     * @param <S>
     *            입력 데이터 타입 정의.
     * @param <T>
     *            신규 데이터 타입 정의.
     * @param src
     *            입력 데이터
     * @param lookupSrcSuper
     *            입력 데이터 클래스 상위 인터페이스/클래스 확장 여부
     * @param targetType
     *            데이터를 전달받은 객체.
     * @param lookupTargetSuper
     *            대상 객체 상위 인터페이스/클래스 확장 여부
     * @return
     * @throws IllegalArgumentException
     *             입력 데이터 또는 대상 타입이 <code>null</code>인 경우
     *
     * @since 2021. 12. 6.
     * @version 1.8.0
     * @author parkjunhong77@gmail.com
     */
    @SuppressWarnings("unchecked")
    public static <S, T> Function<S, T> getTransformer(S src, boolean lookupSrcSuper, Class<T> targetType, boolean lookupTargetSuper) throws NullPointerException {

        AssertUtils.assertNulls("'source' object or 'target' type MUST NOT be null !!!", IllegalArgumentException.class, src, targetType);

        return (Function<S, T>) getTransformer(src.getClass(), lookupSrcSuper, targetType, lookupTargetSuper);
    }

    /**
     * 주어진 타입 및 조건에 맞는 데이터 변환 함수를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 1. 26.		박준홍			최초 작성
     * </pre>
     *
     * *
     * 
     * @param <S>
     *            입력 데이터 타입 정의.
     * @param <T>
     *            신규 데이터 타입 정의.
     * @param srcType
     *            입력 데이터 타입
     * @param lookupSrcSuper
     *            입력 데이터 클래스 상위 인터페이스/클래스 확장 여부
     * @param targetType
     *            데이터를 전달받은 객체 타입.
     * @param lookupTargetSuper
     *            대상 객체 상위 인터페이스/클래스 확장 여부
     * @return
     * @throws IllegalArgumentException
     *             입력 데이터 또는 대상 타입이 <code>null</code>인 경우
     *
     * @since 2022. 1. 26.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    @SuppressWarnings("unchecked")
    public static <S, T> Function<S, T> getTransformer(S src, boolean lookupSrcSuper, T target, boolean lookupTargetSuper) throws IllegalArgumentException {

        AssertUtils.assertNulls("'source' type or 'target' type MUST NOT be null !!!", IllegalArgumentException.class, src, target);

        return getTransformer((Class<S>) src.getClass(), lookupSrcSuper, (Class<T>) target.getClass(), lookupTargetSuper);
    }

    /**
     * 주어진 타입 및 조건에 맞는 데이터 변환 함수를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 12. 6.		박준홍			최초 작성
     * </pre>
     *
     * @param <S>
     *            입력 데이터 타입 정의.
     * @param <T>
     *            신규 데이터 타입 정의.
     * @param src
     *            입력 데이터
     * @param targetType
     *            데이터를 전달받은 객체 타입.
     * @return
     * 
     * @throws IllegalArgumentException
     *             입력 데이터 또는 대상 타입이 <code>null</code>인 경우
     *
     * @since 2021. 12. 6.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <S, T> Function<S, T> getTransformer(S src, Class<T> targetType) {
        return getTransformer(src, false, targetType, false);
    }

    /**
     * 주어진 타입 및 조건에 맞는 데이터 변환 함수를 제공합니다. <br>
     * 
     * <pre>
     *      * 
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 1. 26.		박준홍			최초 작성
     * </pre>
     *
     * @param <S>
     *            입력 데이터 타입 정의.
     * @param <T>
     *            신규 데이터 타입 정의.
     * @param src
     *            입력 데이터
     * @param target
     *            데이터를 전달받은 객체.
     * @return
     * 
     * @throws IllegalArgumentException
     *             입력 데이터 또는 대상 타입이 <code>null</code>인 경우
     *
     * @since 2022. 1. 26.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <S, T> Function<S, T> getTransformer(S src, T target) {
        return getTransformer(src, false, target, false);
    }

    /**
     * primitive 타입인지 여부를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 12. 22.		박준홍			최초 작성
     * 2022. 02. 07.        박준홍     버그 수정.
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
        AssertUtils.assertNull(type);
        return PRIMITIVES.contains(type);
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
     * 
     * @deprecated DO NOT USE. 잘못 정의된 메소드.
     */
    public static boolean isPrimitive(Object obj) {
        AssertUtils.assertNull(obj);
        return PRIMITIVES.contains(obj.getClass());
    }

    /**
     * 주어진 타입이 primitive 또는 Wrapper 타입인지 여부를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 3. 22.		박준홍			최초 작성
     * </pre>
     *
     * @param type
     * @return
     *         <ul>
     *         <li>> 0: Primitive type
     *         <li>= 0: Reference type
     *         <li>< 0: Wrapper type
     *         </ul>
     *
     * @since 2022. 3. 22.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static int isPrimitiveOrWrapper(Class<?> type) {
        return isPrimitive(type) //
                ? 1 //
                : isWrapper(type) ? -1 //
                        : 0;
    }

    /**
     * wrapper class 타입인지 여부를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 12. 22.		박준홍			최초 작성
     * 2022. 02. 07.        박준홍     버그 수정.
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
        AssertUtils.assertNull(type);
        return WRAPPER_TYPES.contains(type);
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
        AssertUtils.assertNull(obj);
        return WRAPPER_TYPES.contains(obj.getClass());
    }

    /**
     * {@link Setter} 어노테이션이 기술된 메소드를 이용하여 {@link Map}으로부터 데이터를 읽어 새로운 객체를 생성합니다. <br>
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
     * 주어진 객체의 {@link Class} 정보를 배열로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 12. 3.		박준홍			최초 작성
     * </pre>
     *
     * @param forceToPrimitive
     * @param objects
     * @return
     *
     * @since 2021. 12. 3.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static Class<?>[] readClasses(boolean forceToPrimitive, Object... objects) {
        return readClassesAsStream(forceToPrimitive, objects) //
                .toArray(Class<?>[]::new);
    }

    /**
     * 주어진 객체의 {@link Class} 정보를 배열로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 12. 3.		박준홍			최초 작성
     * </pre>
     *
     * @param objects
     * @return
     *
     * @since 2021. 12. 3.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static Class<?>[] readClasses(Object... objects) {
        return readClasses(false, objects);
    }

    /**
     * 주어진 객체의 {@link Class} 정보를 {@link List} 로 제공합니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 12. 3.		박준홍			최초 작성
     * </pre>
     *
     * @param objects
     * @return
     *
     * @since 2021. 12. 3.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static List<Class<?>> readClassesAsList(boolean forceToPrimitive, Object... objects) {
        return readClassesAsStream(forceToPrimitive, objects) //
                .collect(Collectors.toList());
    }

    /**
     * 주어진 객체의 {@link Class} 정보를 {@link List} 로 제공합니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 12. 3.		박준홍			최초 작성
     * </pre>
     *
     * @param objects
     * @return
     *
     * @since 2021. 12. 3.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static List<Class<?>> readClassesAsList(Object... objects) {
        return readClassesAsList(false, objects);
    }

    /**
     * 주어진 객체의 {@link Class}정보를 {@link Stream}으로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 12. 3.		박준홍			최초 작성
     * </pre>
     *
     * @param objects
     * @return
     *
     * @since 2021. 12. 3.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static Stream<Class<?>> readClassesAsStream(boolean forceToPrimitive, Object... objects) {
        return StreamSupport.stream(new ClassSpliterator(forceToPrimitive, objects), false);
    }

    /**
     * 주어진 객체의 {@link Class}정보를 {@link Stream}으로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 12. 3.		박준홍			최초 작성
     * </pre>
     *
     * @param objects
     * @return
     *
     * @since 2021. 12. 3.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static Stream<Class<?>> readClassesAsStream(Object... objects) {
        return readClassesAsStream(false, objects);
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
     * 클래스 변수 데이터 변환에 필요한 함수를 등록합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 12. 2.     박준홍         최초 작성
     * </pre>
     *
     * @param <SF>
     *            Source Field Type
     * @param <TF>
     *            Target Field Type
     * @param srcFieldClass
     *            변환 이전 속성 데이터 타입
     * @param targetFieldClass
     *            변환 이후 속성 데이터 타입
     * @param converter
     *            '이전 타입 -> 이후 타입' 변환 함수
     * @return
     * @throws NullPointerException
     *
     * @since 2021. 12. 2.
     * @author Park_Jun_Hong (parkjunhong77@gmail.com)
     * @see #registerPropertyConverter(Class, Class, String, Class, Class, Function)
     */
    public static <SF, TF> Object registerFieldConverter(Class<SF> srcFieldClass, Class<TF> targetFieldClass, Function<SF, TF> converter) throws NullPointerException {
        return registerPropertyConverter(null, srcFieldClass, null, null, targetFieldClass, converter);
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
     * @param <SF>
     *            Source Field Type
     * @param <TF>
     *            Target Field Type
     * @param srcFieldClass
     *            변환 이전 속성 데이터 타입
     * @param targetFieldClass
     *            변환 이후 속성 데이터 타입
     * @param srcToTarget
     *            '이전 속성 타입 -> 이후 속성 타입' 변환 함수
     * @param targetToSrc
     *            '이후 속성 타입 -> 이번 속성 타입' 변환 함수
     * @throws NullPointerException
     *
     * @since 2021. 12. 2.
     * @author Park_Jun_Hong (parkjunhong77@gmail.com)
     * 
     * @see #registerPropertyConverter(Class, Class, String, Class, Class, Function, Function)
     */
    public static <SF, TF> void registerFieldConverter(Class<SF> srcFieldClass, Class<TF> targetFieldClass, Function<SF, TF> srcToTarget, Function<TF, SF> targetToSrc)
            throws NullPointerException {
        registerPropertyConverter(null, srcFieldClass, null, null, targetFieldClass, srcToTarget, targetToSrc);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 3. 22.		박준홍			최초 작성
     * </pre>
     *
     * @param <S>
     *            Source Type
     * @param <SF>
     *            Source Field Type
     * @param <T>
     *            Target Type
     * @param <TF>
     *            Target Field Type
     * @param srcClass
     *            변환 이전 데이터 타입
     * @param srcPropertyClass
     *            변환 이전 속성 데이터 타입
     * @param property
     *            속성명
     * @param targetClass
     *            변환 이후 데이터 타입
     * @param targetPropertyClass
     *            변환 이후 속성 데이터 타입
     * @param converter
     *            '이전 속성 타입 -> 이후 속성 타입' 변환 함수
     * @return
     * @throws NullPointerException
     *
     * @since 2022. 3. 22.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <S, SF, T, TF> Object registerPropertyConverter(Class<S> srcClass, Class<SF> srcPropertyClass, String property, Class<T> targetClass,
            Class<TF> targetPropertyClass, Function<SF, TF> converter) throws NullPointerException {

        AssertUtils.assertNulls("타입 및 함수 정보는 반드시 있어야 합니다.", srcPropertyClass, targetPropertyClass, converter);

        // primitive 타입, wrapper 타입인 경우 추가 자동 등록
        Set<Class<?>> srcPropertyTypes = getPropertyConvertedTypes(srcPropertyClass);
        Set<Class<?>> targetPropertyTypes = getPropertyConvertedTypes(targetPropertyClass);

        for (Class<?> srcPropType : srcPropertyTypes) {
            for (Class<?> targetPropType : targetPropertyTypes) {
                FIELD_CONVERTERS.put(FIELD_CONVERTER_KEYGEN.apply(srcClass, srcPropType, property, targetClass, targetPropType), converter);
            }
        }

        return null;
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 3. 22.		박준홍			최초 작성
     * </pre>
     *
     * @param <S>
     *            Source Type
     * @param <SF>
     *            Source Field Type
     * @param <T>
     *            Target Type
     * @param <TF>
     *            Target Field Type
     * @param srcClass
     *            변환 이전 데이터 타입
     * @param srcPropertyClass
     *            변환 이전 속성 데이터 타입
     * @param property
     *            속성명
     * @param targetClass
     *            변환 이후 데이터 타입
     * @param targetPropertyClass
     *            변환 이후 속성 데이터 타입
     * @param srcToTarget
     *            '이전 속성 타입 -> 이후 속성 타입' 변환 함수
     * @param targetToSrc
     *            '이후 속성 타입 -> 이번 속성 타입' 변환 함수
     * @throws NullPointerException
     *
     * @since 2022. 3. 22.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <S, SF, T, TF> void registerPropertyConverter(Class<S> srcClass, Class<SF> srcPropertyClass, String property, Class<T> targetClass, Class<TF> targetPropertyClass,
            Function<SF, TF> srcToTarget, Function<TF, SF> targetToSrc) throws NullPointerException {
        // register 'src' to 'target'
        registerPropertyConverter(srcClass, srcPropertyClass, property, targetClass, targetPropertyClass, srcToTarget);
        // register 'target' to 'src'
        registerPropertyConverter(targetClass, targetPropertyClass, property, srcClass, srcPropertyClass, targetToSrc);
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
                    "Only support a class type not interface type!!! at " + ObjectUtils.class.getName() + "." + ThreadUtils.getCurrentMethodName() + "()");
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
     * 입력데이터 타입에서 정의된 메소드 중에서 {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로
     * 제공합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 4. 3.      박준홍         최초 작성
     * </pre>
     *
     * @param <S>
     *            입력 데이터 타입
     * @param <T>
     *            대상 데이터 타입
     * @param <C>
     *            변환 후 데이터 {@link Collection}
     * @param src
     *            입력 데이터
     * @param lookupSrcSuper
     *            입력 데이터 클래스 상위 인터페이스/클래스 확장 여부
     * @param target
     *            데이터를 전달받은 객체.
     * @param lookupTargetSuper
     *            대상 객체 상위 인터페이스/클래스 확장 여부
     * @param converters
     *            데이터 변환 함수
     *            <ul>
     *            <li>{@link #FIELD_CONVERTER_KEYGEN} 로 만들어진 식별정보
     *            <li>타입 변환 함수
     *            </ul>
     * @param colsType
     *            대상 데이터를 포함한 {@link Collection}
     * @return
     *
     * @since 2025. 4. 3.
     * @version 2.1.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <S, T, C extends Collection<T>> C transform(Collection<S> src, boolean lookupSrcSuper, Class<T> targetType, boolean lookupTargetSuper,
            Map<String, Function<?, ?>> converters, Supplier<C> supplier) {
        return src.stream().map(s -> {
            try {
                return ObjectUtils.transform(s, lookupSrcSuper, targetType.newInstance(), lookupTargetSuper, converters);
            } catch (InstantiationException | IllegalAccessException e) {
                throw new IllegalStateException(e);
            }
        }).collect(Collectors.toCollection(supplier));
    }

    /**
     * 입력데이터 타입에서 정의된 메소드 중에서 {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로
     * 제공합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 4. 3.      박준홍         최초 작성
     * </pre>
     *
     * @param <S>
     *            입력 데이터 타입
     * @param <T>
     *            대상 데이터 타입
     * @param <C>
     *            변환 후 데이터 {@link Collection}
     * @param src
     *            입력 데이터
     * @param lookupSrcSuper
     *            입력 데이터 클래스 상위 인터페이스/클래스 확장 여부
     * @param target
     *            데이터를 전달받은 객체.
     * @param lookupTargetSuper
     *            대상 객체 상위 인터페이스/클래스 확장 여부
     * @param colsType
     *            대상 데이터를 포함한 {@link Collection}
     * @return
     *
     * @since 2025. 4. 3.
     * @version 2.1.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <S, T, C extends Collection<T>> C transform(Collection<S> src, boolean lookupSrcSuper, Class<T> targetType, boolean lookupTargetSuper, Supplier<C> supplier) {
        return transform(src, lookupSrcSuper, targetType, lookupTargetSuper, FIELD_CONVERTERS, supplier);
    }

    /**
     * 입력데이터 타입에서 정의된 메소드 중에서 {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로
     * 제공합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 4. 3.      박준홍         최초 작성
     * </pre>
     *
     * @param <S>
     *            입력 데이터 타입
     * @param <T>
     *            대상 데이터 타입
     * @param <C>
     *            변환 후 데이터 {@link Collection}
     * @param src
     *            입력 데이터
     * @param lookupSrcSuper
     *            입력 데이터 클래스 상위 인터페이스/클래스 확장 여부
     * @param target
     *            데이터를 전달받은 객체.
     * @param converters
     *            데이터 변환 함수
     *            <ul>
     *            <li>{@link #FIELD_CONVERTER_KEYGEN} 로 만들어진 식별정보
     *            <li>타입 변환 함수
     *            </ul>
     * @param colsType
     *            대상 데이터를 포함한 {@link Collection}
     * @return
     *
     * @since 2025. 4. 3.
     * @version 2.1.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <S, T, C extends Collection<T>> C transform(Collection<S> src, boolean lookupSrcSuper, Class<T> targetType, Map<String, Function<?, ?>> converters,
            Supplier<C> supplier) {
        return transform(src, lookupSrcSuper, targetType, false, converters, supplier);
    }

    /**
     * 입력데이터 타입에서 정의된 메소드 중에서 {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로
     * 제공합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 4. 3.      박준홍         최초 작성
     * </pre>
     *
     * @param <S>
     *            입력 데이터 타입
     * @param <T>
     *            대상 데이터 타입
     * @param <C>
     *            변환 후 데이터 {@link Collection}
     * @param src
     *            입력 데이터
     * @param lookupSrcSuper
     *            입력 데이터 클래스 상위 인터페이스/클래스 확장 여부
     * @param target
     *            데이터를 전달받은 객체.
     * @param colsType
     *            대상 데이터를 포함한 {@link Collection}
     * @return
     *
     * @since 2025. 4. 3.
     * @version 2.1.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <S, T, C extends Collection<T>> C transform(Collection<S> src, boolean lookupSrcSuper, Class<T> targetType, Supplier<C> supplier) {
        return transform(src, lookupSrcSuper, targetType, false, FIELD_CONVERTERS, supplier);
    }

    /**
     * 입력데이터 타입에서 정의된 메소드 중에서 {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로
     * 제공합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 4. 3.      박준홍         최초 작성
     * </pre>
     *
     * @param <S>
     *            입력 데이터 타입
     * @param <T>
     *            대상 데이터 타입
     * @param <C>
     *            변환 후 데이터 {@link Collection}
     * @param src
     *            입력 데이터
     * @param target
     *            데이터를 전달받은 객체.
     * @param lookupTargetSuper
     *            대상 객체 상위 인터페이스/클래스 확장 여부
     * @param converters
     *            데이터 변환 함수
     *            <ul>
     *            <li>{@link #FIELD_CONVERTER_KEYGEN} 로 만들어진 식별정보
     *            <li>타입 변환 함수
     *            </ul>
     * @param colsType
     *            대상 데이터를 포함한 {@link Collection}
     * @return
     *
     * @since 2025. 4. 3.
     * @version 2.1.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <S, T, C extends Collection<T>> C transform(Collection<S> src, Class<T> targetType, boolean lookupTargetSuper, Map<String, Function<?, ?>> converters,
            Supplier<C> supplier) {
        return transform(src, false, targetType, lookupTargetSuper, converters, supplier);
    }

    /**
     * 입력데이터 타입에서 정의된 메소드 중에서 {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로
     * 제공합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 4. 3.      박준홍         최초 작성
     * </pre>
     *
     * @param <S>
     *            입력 데이터 타입
     * @param <T>
     *            대상 데이터 타입
     * @param <C>
     *            변환 후 데이터 {@link Collection}
     * @param src
     *            입력 데이터
     * @param target
     *            데이터를 전달받은 객체.
     * @param lookupTargetSuper
     *            대상 객체 상위 인터페이스/클래스 확장 여부
     * @param colsType
     *            대상 데이터를 포함한 {@link Collection}
     * @return
     *
     * @since 2025. 4. 3.
     * @version 2.1.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <S, T, C extends Collection<T>> C transform(Collection<S> src, Class<T> targetType, boolean lookupTargetSuper, Supplier<C> supplier) {
        return transform(src, false, targetType, lookupTargetSuper, FIELD_CONVERTERS, supplier);
    }

    /**
     * 입력데이터 타입에서 정의된 메소드 중에서 {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로
     * 제공합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 4. 3.      박준홍         최초 작성
     * </pre>
     *
     * @param <S>
     *            입력 데이터 타입
     * @param <T>
     *            대상 데이터 타입
     * @param <C>
     *            변환 후 데이터 {@link Collection}
     * @param src
     *            입력 데이터
     * @param target
     *            데이터를 전달받은 객체.
     * @param converters
     *            데이터 변환 함수
     *            <ul>
     *            <li>{@link #FIELD_CONVERTER_KEYGEN} 로 만들어진 식별정보
     *            <li>타입 변환 함수
     *            </ul>
     * @param colsType
     *            대상 데이터를 포함한 {@link Collection}
     * @return
     *
     * @since 2025. 4. 3.
     * @version 2.1.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <S, T, C extends Collection<T>> C transform(Collection<S> src, Class<T> targetType, Map<String, Function<?, ?>> converters, Supplier<C> supplier) {
        return transform(src, false, targetType, false, converters, supplier);
    }

    /**
     * 입력데이터 타입에서 정의된 메소드 중에서 {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로
     * 제공합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 4. 3.      박준홍         최초 작성
     * </pre>
     *
     * @param <S>
     *            입력 데이터 타입
     * @param <T>
     *            대상 데이터 타입
     * @param <C>
     *            변환 후 데이터 {@link Collection}
     * @param src
     *            입력 데이터
     * @param target
     *            데이터를 전달받은 객체.
     * @param colsType
     *            대상 데이터를 포함한 {@link Collection}
     * @return
     *
     * @since 2025. 4. 3.
     * @version 2.1.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <S, T, C extends Collection<T>> C transform(Collection<S> src, Class<T> targetType, Supplier<C> supplier) {
        return transform(src, false, targetType, false, FIELD_CONVERTERS, supplier);
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
     * @param <T>
     *            신규 데이터 타입 정의.
     * @param src
     *            입력 데이터
     * @param lookupSrcSuper
     *            입력 데이터 클래스 상위 인터페이스/클래스 확장 여부
     * @param targetType
     *            변환 타입. 기본생성자가 반드시 있어야 합니다.
     * @return
     *
     * @since 2019. 7. 11.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @see #transform(Object, boolean, Class, boolean)
     */
    public static <S, T> T transform(S src, boolean lookupSrcSuper, Class<T> targetType) {
        return transform(src, lookupSrcSuper, targetType, false);
    }

    /**
     * 입력데이터 타입에서 정의된 메소드 중에서 {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로
     * 제공합니다.<br>
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
     * @param <T>
     *            신규 데이터 타입 정의.
     * @param src
     *            입력 데이터
     * @param lookupSrcSuper
     *            입력 데이터 클래스 상위 인터페이스/클래스 확장 여부
     * @param targetType
     *            변환 타입. 기본생성자가 반드시 있어야 합니다.
     * @param lookupTargetSuper
     *            변환 대상 클래스 상위 인터페이스/클래스 확장 여부
     * @return
     *
     * @since 2019. 7. 11.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <S, T> T transform(S src, boolean lookupSrcSuper, Class<T> targetType, boolean lookupTargetSuper) {
        try {
            return transform(src, lookupSrcSuper, targetType.newInstance(), lookupTargetSuper);
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * 입력데이터 타입에서 정의된 메소드 중에서 {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로
     * 제공합니다.<br>
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
     * @param <T>
     *            신규 데이터 타입 정의.
     * @param src
     *            입력 데이터
     * @param lookupSrcSuper
     *            입력 데이터 클래스 상위 인터페이스/클래스 확장 여부
     * @param targetType
     *            변환 타입. 기본생성자가 반드시 있어야 합니다.
     * @param lookupTargetSuper
     *            변환 대상 클래스 상위 인터페이스/클래스 확장 여부
     * @param converters
     *            데이터 변환 함수
     *            <ul>
     *            <li>{@link #FIELD_CONVERTER_KEYGEN} 로 만들어진 식별정보
     *            <li>타입 변환 함수
     *            </ul>
     * @return
     *
     * @since 2021. 11. 22.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <S, T> T transform(S src, boolean lookupSrcSuper, Class<T> targetType, boolean lookupTargetSuper, Map<String, Function<?, ?>> converters) {
        try {
            return transform(src, lookupSrcSuper, targetType.newInstance(), lookupTargetSuper, converters);
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
     * @param <T>
     *            신규 데이터 타입 정의.
     * @param src
     *            입력 데이터
     * @param lookupSrcSuper
     *            입력 데이터 클래스 상위 인터페이스/클래스 확장 여부
     * @param targetType
     *            변환 타입. 기본생성자가 반드시 있어야 합니다.
     * @param converters
     *            데이터 변환 함수
     *            <ul>
     *            <li>{@link #FIELD_CONVERTER_KEYGEN} 로 만들어진 식별정보
     *            <li>타입 변환 함수
     *            </ul>
     * @return
     *
     * @since 2021. 11. 22.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @see #transform(Object, boolean, Class, boolean)
     */
    public static <S, T> T transform(S src, boolean lookupSrcSuper, Class<T> targetType, Map<String, Function<?, ?>> converters) {
        return transform(src, lookupSrcSuper, targetType, false, converters);
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
     * @param <T>
     *            신규 데이터 타입 정의.
     * @param src
     *            입력 데이터
     * @param lookupSrcSuper
     *            입력 데이터 클래스 상위 인터페이스/클래스 확장 여부
     * @param target
     *            데이터를 전달받은 객체.
     * @return
     *
     * @since 2020. 12. 08.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @see #transform(Object, boolean, Class, boolean)
     */
    public static <S, T> T transform(S src, boolean lookupSrcSuper, T target) {
        return transform(src, lookupSrcSuper, target, false);
    }

    /**
     * 입력데이터 타입에서 정의된 메소드 중에서 {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로
     * 제공합니다.<br>
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
     * @param <T>
     *            신규 데이터 타입 정의.
     * @param src
     *            입력 데이터
     * @param lookupSrcSuper
     *            입력 데이터 클래스 상위 인터페이스/클래스 확장 여부
     * @param target
     *            데이터를 전달받은 객체.
     * @param lookupTargetSuper
     *            대상 객체 상위 인터페이스/클래스 확장 여부
     * @return
     *
     * @since 2020. 12. 08.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @see #FIELD_CONVERTERS
     */
    public static <S, T> T transform(S src, boolean lookupSrcSuper, T target, boolean lookupTargetSuper) {
        return transform(src, lookupSrcSuper, target, lookupTargetSuper, FIELD_CONVERTERS);
    }

    /**
     * 입력데이터 타입에서 정의된 메소드 중에서 {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로
     * 제공합니다.<br>
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
     * @param <T>
     *            신규 데이터 타입 정의.
     * @param src
     *            입력 데이터
     * @param lookupSrcSuper
     *            입력 데이터 클래스 상위 인터페이스/클래스 확장 여부
     * @param target
     *            데이터를 전달받은 객체.
     * @param lookupTargetSuper
     *            대상 객체 상위 인터페이스/클래스 확장 여부
     * @param converters
     *            데이터 변환 함수. 이 값이 <code>null</code>인 경우, {@link #FIELD_CONVERTERS} 값을 사용합니다.
     *            <ul>
     *            <li>{@link #FIELD_CONVERTER_KEYGEN} 로 만들어진 식별정보
     *            <li>타입 변환 함수
     *            </ul>
     * 
     * @throws IllegalArgumentException
     *             입력 데이터 또는 대상 타입이 <code>null</code>인 경우
     * @return
     *
     * @since 2021. 11. 22.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    @SuppressWarnings("unchecked")
    public static <S, T> T transform(S src, boolean lookupSrcSuper, T target, boolean lookupTargetSuper, Map<String, Function<?, ?>> converters) {

        AssertUtils.assertNulls("'source' object or 'target' type MUST NOT be null !!!", IllegalArgumentException.class, src, target);

        List<Method> getters = lookupSrcSuper ? AnnotationUtils.getAnnotatedMethodsAll(src, Getter.class) : AnnotationUtils.getAnnotatedMethods(src, Getter.class);
        if (getters.size() < 1) {
            return target;
        }

        List<Method> setters = lookupTargetSuper ? AnnotationUtils.getAnnotatedMethodsAll(target, Setter.class) : AnnotationUtils.getAnnotatedMethods(target, Setter.class);
        if (setters.size() < 1) {
            return target;
        }

        // 데이터 변환함수가 null 인 경우
        if (converters == null) {
            converters = FIELD_CONVERTERS;
        }

        // #0. Setter 메소드 재정렬
        // key: Setter#name()
        // value: Method
        final Map<String, Method> setterMap = CollectionUtils.toMapHSV(setters, SETTER_KEYGEN, m -> m);

        // #1. Setter와 동일한 식별정보를 갖는 Getter 추출
        List<Method> gettersFiltered = getters.stream().filter(m -> {
            return setterMap.containsKey(GETTER_KEYGEN.apply(m));
        }).collect(Collectors.toList());

        // #2. Setter와 연결되는 Getter 메소드 재정렬
        Map<String, Method> getterMap = CollectionUtils.toMapHSV(gettersFiltered, GETTER_KEYGEN, m -> m);

        // method key
        String property = null;
        // getter method
        Method getter = null;
        boolean getterAccessible = false;
        // setter method
        Method setter = null;
        boolean setterAccessible = false;

        Object o = null;
        Class<?> srcClass = src.getClass();
        Class<?> srcPropertyClass = null;
        Class<?> targetClass = target.getClass();
        Class<?> targetPropertyClass = null;
        Function<?, ?> converter = null;
        for (Entry<String, Method> entry : setterMap.entrySet()) {
            try {
                property = entry.getKey();

                // 데이터 제공 함수
                getter = getterMap.get(property);

                // 대상 타입에 정의된 Setter에 해당하는 Getter이 없는 경우
                if (getter == null) {
                    continue;
                }

                // 데이터 읽기
                getterAccessible = getter.isAccessible();
                getter.setAccessible(true);

                // PATCH [2021. 11. 22.]: 데이터 변환 | Park_Jun_Hong_(fafanmama_at_naver_com)
                o = getter.invoke(src);

                // 데이터 쓰기
                setter = entry.getValue();
                setterAccessible = setter.isAccessible();
                setter.setAccessible(true);

                srcPropertyClass = RETURN_TYPE.apply(getter);
                targetPropertyClass = PARAMETER_TYPE.apply(setter);

                // srcType과 targetType이 호환여부 확인
                // if (!checkType(srcPropertyClass, targetPropertyClass)) {
                // 타입 변환 함수가 존재하는 경우
                if ((converter = getFieldConverter(srcClass, srcPropertyClass, property, targetClass, targetPropertyClass, converters)) != null) {
                    o = ((Function<Object, ?>) converter).apply(o);
                }
                // }

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
     * @param <T>
     *            신규 데이터 타입 정의.
     * @param src
     *            입력 데이터
     * @param lookupSrcSuper
     *            입력 데이터 클래스 상위 인터페이스/클래스 확장 여부
     * @param target
     *            데이터를 전달받은 객체.
     * @param converters
     *            데이터 변환 함수
     *            <ul>
     *            <li>{@link #FIELD_CONVERTER_KEYGEN} 로 만들어진 식별정보
     *            <li>타입 변환 함수
     *            </ul>
     * @return
     *
     * @since 2020. 12. 08.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @see #transform(Object, boolean, Class, boolean)
     */
    public static <S, T> T transform(S src, boolean lookupSrcSuper, T target, Map<String, Function<?, ?>> converters) {
        return transform(src, lookupSrcSuper, target, false, converters);
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
     * @param <T>
     *            신규 데이터 타입 정의.
     * @param src
     *            입력 데이터
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
    public static <S, T> T transform(S src, Class<T> targetType) {
        return transform(src, false, targetType, false);
    }

    /**
     * 입력데이터 타입에서 정의된 메소드 중에서 입력데이터 타입에서 정의된 메소드 중에서 {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를
     * 변환하여 새로운 타입의 객체로 제공합니다.<br>
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
     * @param <T>
     *            신규 데이터 타입 정의.
     * @param src
     *            입력 데이터.
     * @param targetType
     *            변환 타입. 기본생성자가 반드시 있어야 합니다.
     * @param lookupTargetSuper
     *            변환 대상 클래스 상위 인터페이스/클래스 확장 여부
     * @return
     *
     * @since 2019. 7. 11.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <S, T> T transform(S src, Class<T> targetType, boolean lookupTargetSuper) {
        return transform(src, false, targetType, lookupTargetSuper);
    }

    /**
     * 입력데이터 타입에서 정의된 메소드 중에서 입력데이터 타입에서 정의된 메소드 중에서 {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를
     * 변환하여 새로운 타입의 객체로 제공합니다.<br>
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
     * @param <T>
     *            신규 데이터 타입 정의.
     * @param src
     *            입력 데이터.
     * @param targetType
     *            변환 타입. 기본생성자가 반드시 있어야 합니다.
     * @param lookupTargetSuper
     *            변환 대상 클래스 상위 인터페이스/클래스 확장 여부
     * @param converters
     *            데이터 변환 함수
     *            <ul>
     *            <li>{@link #FIELD_CONVERTER_KEYGEN} 로 만들어진 식별정보
     *            <li>타입 변환 함수
     *            </ul>
     * @return
     *
     * @since 2021. 11. 22.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <S, T> T transform(S src, Class<T> targetType, boolean lookupTargetSuper, Map<String, Function<?, ?>> converters) {
        return transform(src, false, targetType, lookupTargetSuper, converters);
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
     * @param <T>
     *            신규 데이터 타입 정의.
     * @param src
     *            입력 데이터
     * @param targetType
     *            변환 타입
     * @param converters
     *            데이터 변환 함수.
     *            <ul>
     *            <li>{@link #FIELD_CONVERTER_KEYGEN} 로 만들어진 식별정보
     *            <li>타입 변환 함수
     *            </ul>
     * @return
     *
     * @since 2021. 11. 22.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @see Getter
     * @see Setter
     */
    public static <S, T> T transform(S src, Class<T> targetType, Map<String, Function<?, ?>> converters) {
        return transform(src, false, targetType, false, converters);
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
     * @param <T>
     *            신규 데이터 타입 정의.
     * @param src
     *            입력 데이터
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
    public static <S, T> T transform(S src, T target) {
        return transform(src, false, target, false);
    }

    /**
     * 입력데이터 타입에서 정의된 메소드 중에서 입력데이터 타입에서 정의된 메소드 중에서 {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를
     * 변환하여 새로운 타입의 객체로 제공합니다.<br>
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
     * @param <T>
     *            신규 데이터 타입 정의.
     * @param src
     *            입력 데이터.
     * @param target
     *            데이터를 전달받은 객체.
     * @param lookupTargetSuper
     *            변환 대상 클래스 상위 인터페이스/클래스 확장 여부
     * @return
     *
     * @since 2019. 7. 11.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <S, T> T transform(S src, T target, boolean lookupTargetSuper) {
        return transform(src, false, target, lookupTargetSuper);
    }

    /**
     * 입력데이터 타입에서 정의된 메소드 중에서 입력데이터 타입에서 정의된 메소드 중에서 {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를
     * 변환하여 새로운 타입의 객체로 제공합니다.<br>
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
     * @param <T>
     *            신규 데이터 타입 정의.
     * @param src
     *            입력 데이터.
     * @param target
     *            데이터를 전달받은 객체.
     * @param lookupTargetSuper
     *            변환 대상 클래스 상위 인터페이스/클래스 확장 여부
     * @param converters
     *            데이터 변환 함수
     *            <ul>
     *            <li>{@link #FIELD_CONVERTER_KEYGEN} 로 만들어진 식별정보
     *            <li>타입 변환 함수
     *            </ul>
     * @return
     *
     * @since 2021. 11. 22.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <S, T> T transform(S src, T target, boolean lookupTargetSuper, Map<String, Function<?, ?>> converters) {
        return transform(src, false, target, lookupTargetSuper, converters);
    }

    /**
     * 입력데이터 타입에서 정의된 메소드 중에서 {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로
     * 제공합니다.<br>
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
     * @param <T>
     *            신규 데이터 타입 정의.
     * @param src
     *            입력 데이터
     * @param target
     *            데이터를 전달받은 객체.
     * @param converters
     *            데이터 변환 함수
     *            <ul>
     *            <li>{@link #FIELD_CONVERTER_KEYGEN} 로 만들어진 식별정보
     *            <li>타입 변환 함수
     *            </ul>
     * @return
     *
     * @since 2021. 11. 22.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @see Getter
     * @see Setter
     */
    public static <S, T> T transform(S src, T target, Map<String, Function<?, ?>> converters) {
        return transform(src, false, target, false, converters);
    }

}
