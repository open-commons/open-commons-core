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

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
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
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
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
import open.commons.core.exception.CreateInstanceFailedException;
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
    private static final ConcurrentHashMap<String, Function<?, ?>> FIELD_CONVERTERS = new ConcurrentHashMap<>();

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
    private static final ConcurrentHashMap<Integer, Function<?, ?>> TYPE_CONVERTERS = new ConcurrentHashMap<>();

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

    /**
     * 
     */
    private static final Map<CopierKey, BiConsumer<Object, Object>> COPIER_CACHE = new ConcurrentHashMap<>();

    // Prevent to create a new instance.
    private ObjectUtils() {
    }

    /**
     * 데이터 이관을 진행할 객체를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 4.		박준홍			최초 작성
     * </pre>
     *
     * @param srcClass
     *            원본 데이터 유형
     * @param lookupSrcSuper
     *            원본 데이터 상위 정보 이관 여부
     * @param targetClass
     *            대상 데이터 유형
     * @param lookupTargetSuper
     *            대상 데이터 상위 정보 이관 여부
     * @param converters
     * @return
     *
     * @since 2025. 9. 4.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    private static BiConsumer<Object, Object> buildCopier(Class<?> srcClass, boolean lookupSrcSuper, Class<?> targetClass, boolean lookupTargetSuper,
            Map<String, Function<?, ?>> converters) {

        List<StepPlan> steps = planSteps(srcClass, lookupSrcSuper, targetClass, lookupTargetSuper, converters);

        final MethodHandles.Lookup lookup = MethodHandles.lookup();
        // 3-1) 각 step을 (b,a)->void 로 “미리” 합성
        final List<MethodHandle> mhSteps = new ArrayList<>(steps.size());

        MethodHandle getter = null;
        MethodType GETTER_OBJ = null;
        MethodHandle setter = null;
        MethodType SETTER_OBJ = null;
        MethodHandle conv = null;
        MethodHandle converted = null;
        MethodHandle step = null;
        try {
            for (StepPlan plan : steps) {
                // (A)->val
                if (!plan.getter.isAccessible()) {
                    plan.getter.setAccessible(true);
                }
                getter = lookup.unreflect(plan.getter);
                // (Object)->Object 로 어댑터
                GETTER_OBJ = MethodType.methodType(Object.class, Object.class);
                getter = MethodHandles.explicitCastArguments(getter, GETTER_OBJ);

                // converter(Function.apply)
                if (plan.converter == StepPlan.IDENTITY_CONVERT) {
                    conv = MethodHandles.identity(Object.class); // (Object)->Object
                } else {
                    MethodHandle apply = lookup.findVirtual(Function.class, "apply", MethodType.methodType(Object.class, Object.class)).bindTo(plan.converter);
                    conv = apply; // (Object)->Object
                }

                // (a)->val2
                converted = MethodHandles.filterReturnValue(getter, conv);

                // (B,val)->void
                if (!plan.setter.isAccessible()) {
                    plan.setter.setAccessible(true);
                }
                setter = lookup.unreflect(plan.setter);
                SETTER_OBJ = MethodType.methodType(void.class, Object.class, Object.class);
                setter = MethodHandles.explicitCastArguments(setter, SETTER_OBJ);

                // (b,a)->void : setter(b, converted(a))
                step = MethodHandles.collectArguments(setter, 1, converted);
                // (선택) 명시 캐스팅 — 사실상 이미 (Object,Object)->void 일 것이라 불필요하지만, 놔둬도 무해
                step = MethodHandles.explicitCastArguments( //
                        step //
                        , MethodType.methodType(void.class, Object.class, Object.class) //
                );

                mhSteps.add(step);
            }

            // 3-2) 루프 본체: (target, src, mhStepArr)->void
            MethodHandle impl = lookup.findStatic( //
                    // 또는 별도 Fast 클래스
                    ObjectUtils.class, "runSteps" //
                    , MethodType.methodType(void.class, MethodHandle[].class, Object.class, Object.class) //
            );

            // 3-3) LMF로 BiConsumer<Object,Object> 생성
            CallSite site = LambdaMetafactory.metafactory( //
                    lookup //
                    , "accept"
                    // 팩토리 invokedType에 캡처할 인자(MethodHandle[] steps)를 추가
                    , MethodType.methodType(BiConsumer.class, MethodHandle[].class) // (steps) -> BiConsumer
                    , MethodType.methodType(void.class, Object.class, Object.class) // erased SAM: (b,a)->void
                    , impl // direct: (b,a,steps)->void
                    , MethodType.methodType(void.class, Object.class, Object.class) // instantiated SAM
            );

            // 캡처할 배열을 정확 타입으로 전달 (invokeExact 권장)
            MethodHandle[] mhStepArr = mhSteps.toArray(new MethodHandle[0]);
            return (BiConsumer<Object, Object>) site.getTarget().invoke(mhStepArr);
        } catch (Throwable t) {
            LOGGER.error("getter    = {}, getter.type  = {}", getter, GETTER_OBJ);
            LOGGER.error("setter    = {}, setter.type  = {}", setter, SETTER_OBJ);
            LOGGER.error("converter = {}, conver.after = {}", conv, converted);
            LOGGER.error("step      = {}", step);
            throw new RuntimeException("Failed to build copier: " + srcClass + "->" + targetClass, t);
        }
    }

    private static Map<String, Function<?, ?>> checkConvertersOrDefault(Map<String, Function<?, ?>> converters) {
        return MapUtils.isNullOrEmpty(converters) ? FIELD_CONVERTERS : converters;
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

        AssertUtils2.notNulls("'source' type or 'target' type MUST NOT be null !!!", IllegalArgumentException.class, srcType, targetType);

        int key = TYPE_CONVERTER_KEYGEN.apply(srcType, lookupSrcSuper, targetType, lookupTargetSuper);
        return (Function<S, T>) MapUtils.getOrDefault(TYPE_CONVERTERS, key,
                (Supplier<Function<?, ?>>) () -> (Function<?, ?>) value -> transform(value, lookupSrcSuper, targetType, lookupTargetSuper), true);
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

        AssertUtils2.notNulls("'source' object or 'target' type MUST NOT be null !!!", IllegalArgumentException.class, src, targetType);
        AssertUtils2.isFalse("'source' object MUST NOT be empty !!!", src.isEmpty(), IllegalArgumentException.class);

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

        AssertUtils2.notNulls("'source' object or 'target' type MUST NOT be null !!!", IllegalArgumentException.class, src, target);
        AssertUtils2.isFalse("'source' object MUST NOT be empty !!!", src.isEmpty(), IllegalArgumentException.class);

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

        AssertUtils2.notNulls("'source' type or 'target' type MUST NOT be null !!!", IllegalArgumentException.class, srcType, targetType);

        return (Function<S, T>) MapUtils.getOrDefault(TYPE_CONVERTERS, typeConverterKey,
                (Supplier<Function<?, ?>>) () -> (Function<?, ?>) value -> transform(value, lookupSrcSuper, targetType, lookupTargetSuper), true);
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

        AssertUtils2.notNulls("'source' object or 'target' type MUST NOT be null !!!", IllegalArgumentException.class, src, targetType);

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

        AssertUtils2.notNulls("'source' type or 'target' type MUST NOT be null !!!", IllegalArgumentException.class, src, target);

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
        AssertUtils2.notNull(type);
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
        AssertUtils2.notNull(obj);
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
        AssertUtils2.notNull(type);
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
        AssertUtils2.notNull(obj);
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
    @SuppressWarnings("deprecation")
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
     * 데이터를 이관할 메소드 정보를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 4.      박준홍         최초 작성
     * </pre>
     *
     * @param srcClass
     *            원본 데이터 유형
     * @param lookupSrcSuper
     *            원본 데이터 상위 정보 이관 여부
     * @param targetClass
     *            대상 데이터 유형
     * @param lookupTargetSuper
     *            대상 데이터 상위 정보 이관 여부
     * @param converters
     * @return
     *
     * @since 2025. 9. 4.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    private static <S, T> List<StepPlan> planSteps(Class<S> srcClass, boolean lookupSrcSuper, Class<T> targetClass, boolean lookupTargetSuper,
            Map<String, Function<?, ?>> converters) {

        List<Method> getters = lookupSrcSuper ? AnnotationUtils.getAnnotatedMethodsAll(srcClass, Getter.class) : AnnotationUtils.getAnnotatedMethods(srcClass, Getter.class);
        List<Method> setters = lookupTargetSuper ? AnnotationUtils.getAnnotatedMethodsAll(targetClass, Setter.class)
                : AnnotationUtils.getAnnotatedMethods(targetClass, Setter.class);

        // 데이터 변환함수가 null 인 경우
        converters = checkConvertersOrDefault(converters);

        // #0. Setter 메소드 재정렬
        // key: Setter#name()
        // value: Method
        final Map<String, Method> setterMap = setters.stream().collect(Collectors.toMap(SETTER_KEYGEN, m -> m));

        // #1. Setter와 연결되는 Getter 메소드 재정렬
        final Map<String, Method> getterMap = getters.stream()
                // Setter와 동일한 식별정보를 갖는 Getter 추출
                .filter(m -> setterMap.containsKey(GETTER_KEYGEN.apply(m))) //
                .collect(Collectors.toMap(GETTER_KEYGEN, m -> m));

        // method key
        String property = null;
        // getter method
        Method getter = null;
        // setter method
        Method setter = null;
        // 제공되는 데이터 변환 함수
        Function<?, ?> converter = null;

        List<StepPlan> plans = new ArrayList<>();
        for (Entry<String, Method> entry : setterMap.entrySet()) {
            property = entry.getKey();

            // 데이터 제공 함수
            getter = getterMap.get(property);

            // 대상 타입에 정의된 Setter에 해당하는 Getter이 없는 경우
            if (getter == null) {
                continue;
            }

            // 데이터 설정 함수
            setter = entry.getValue();

            // 변환 함수
            converter = getFieldConverter(srcClass, RETURN_TYPE.apply(getter), property, targetClass, PARAMETER_TYPE.apply(setter), converters);
            plans.add(new StepPlan(property, getter, setter, converter));
        }

        return plans;
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

        AssertUtils2.notNulls("타입 및 함수 정보는 반드시 있어야 합니다.", srcPropertyClass, targetPropertyClass, converter);

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
     * <code>LMF가 호출할 루프 본체</code>로써 실제 데이터 형변환을 실행합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 4.		박준홍			최초 작성
     * </pre>
     *
     * @param steps
     * @param target
     *            대상 데이터 객체
     * @param src
     *            원본 데이터 객체
     *
     * @since 2025. 9. 4.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    @SuppressWarnings("unused")
    private static void runSteps(MethodHandle[] steps, Object target, Object src) {
        try {
            for (int i = 0; i < steps.length; i++) {
                steps[i].invokeExact(target, src);
            }
        } catch (Throwable t) {
            throw (t instanceof RuntimeException) ? (RuntimeException) t : new RuntimeException(t);
        }
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
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
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
     * 전달받은 데이터를 변환(T => U)한 후, 새로운 {@link Collection}(R)로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 30.		박준홍			최초 작성
     * </pre>
     *
     * @param <T>
     *            기존 데이터 유형
     * @param <U>
     *            새로운 데이터 유형
     * @param <R>
     *            변환 후 데이터가 저장될 {@link Collection} 유형
     * @param objects
     * @param function
     *            데이터 변환 함수
     * @param collectionSupplier
     * @return
     * @throws UnsupportedOperationException
     * @throws InstantiationException
     * @throws IllegalAccessException
     *
     * @since 2025. 8. 30.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    private static <T, U, R extends Collection<U>> R to(Collection<T> objects, Function<T, U> transformer, Supplier<R> collectionSupplier) {
        AssertUtils2.notNulls(objects, transformer, collectionSupplier);

        if (objects.size() < 1) {
            return collectionSupplier.get();
        }

        return objects.stream().map(transformer).collect(Collectors.toCollection(collectionSupplier));
    }

    /**
     * Object Type {@link Collection}을 Boolean Type {@link List}으로 변환한여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 4.		박준홍			최초 작성
     * </pre>
     *
     * @param objects
     *            변환할 데이터
     * @return
     *
     * @since 2025. 9. 4.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static List<Boolean> toBoolean(Collection<Object> objects) {
        return to(objects, booleanFunction, ArrayList::new);
    }

    /**
     * {@link Object} Type {@link Collection}을 {@link Boolean} Type {@link Collection}으로 변환한여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 1. 31.     박준홍         최초 작성
     * </pre>
     * 
     * @param <R>
     *            새로운 {@link Collection} 유형
     * @param objects
     *            변환할 데이터
     * @param classType
     *            새로운 {@link Collection} 객체
     * @return
     * @throws NullPointerException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws UnsupportedOperationException
     * 
     * @deprecated 2025. 9. 4. {@link #toBoolean(Collection, Function, Supplier)} 또는 편의성에 따라 다른
     *             <code>toBoolean(...)</code>를 사용하기 바랍니다. <code>Class</code>를 이용하여 {@link Collection} 객체를 생성하는 방식은 여러
     *             가지 제약과 오류의 소지가 확인되었습니다. <br>
     *             <font color="red">다음 배포시 삭제 예정</font>
     *
     * @since 2018. 1. 31.
     */
    public static <R extends Collection<Boolean>> R toBoolean(Collection<Object> objects, Class<? extends R> classType)
            throws NullPointerException, UnsupportedOperationException, InstantiationException, IllegalAccessException {
        return to(objects, classType, booleanFunction);
    }

    /**
     * {@link Object} Type {@link Collection}을 {@link Boolean} Type {@link List}으로 변환한여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 4.		박준홍			최초 작성
     * </pre>
     *
     * @param objects
     *            변환할 데이터
     * @param transfomer
     *            데이터 변환 함수 (Object => Boolean)
     * @return
     *
     * @since 2025. 9. 4.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static List<Boolean> toBoolean(Collection<Object> objects, Function<Object, Boolean> transformer) {
        return to(objects, transformer, ArrayList::new);
    }

    /**
     * {@link Object} Type {@link Collection}을 {@link Boolean} Type {@link Collection}으로 변환한여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 4.      박준홍         최초 작성
     * </pre>
     *
     * @param <R>
     *            새로운 {@link Collection} 유형.
     * @param objects
     *            변환할 데이터
     * @param transfomer
     *            데이터 변환 함수 (Object => Boolean)
     * @param collectionSupplier
     *            {@link Collection} 객체 제공 함수.
     * @return
     *
     * @since 2025. 9. 4.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <R extends Collection<Boolean>> R toBoolean(Collection<Object> objects, Function<Object, Boolean> transformer, Supplier<R> collectionSupplier) {
        return to(objects, transformer, collectionSupplier);
    }

    /**
     * {@link Object} Type {@link Collection}을 {@link Boolean} Type {@link Collection}으로 변환한여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 4.      박준홍         최초 작성
     * </pre>
     *
     * @param <R>
     *            새로운 {@link Collection} 유형.
     * @param objects
     *            변환할 데이터
     * @param collectionSupplier
     *            {@link Collection} 객체 제공 함수.
     * @return
     *
     * @since 2025. 9. 4.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <R extends Collection<Boolean>> R toBoolean(Collection<Object> objects, Supplier<R> collectionSupplier) {
        return to(objects, booleanFunction, collectionSupplier);
    }

    /**
     * Object Type {@link Collection}을 Byte Type {@link List}으로 변환한여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 4.      박준홍         최초 작성
     * </pre>
     *
     * @param objects
     *            변환할 데이터
     * @return
     *
     * @since 2025. 9. 4.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static List<Byte> toByte(Collection<Object> objects) {
        return to(objects, byteFunction, ArrayList::new);
    }

    /**
     * {@link Object} Type {@link Collection}을 {@link Byte} Type {@link Collection}으로 변환한여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 1. 31.     박준홍         최초 작성
     * </pre>
     *
     * @param <R>
     *            새로운 {@link Collection} 유형
     * @param objects
     *            변환할 데이터
     * @param classType
     *            새로운 {@link Collection} 객체
     * @return
     * @throws NullPointerException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws UnsupportedOperationException
     * 
     * @deprecated 2025. 9. 4. {@link #toByte(Collection, Function, Supplier)} 또는 편의성에 따라 다른 <code>toByte(...)</code>를
     *             사용하기 바랍니다. <code>Class</code>를 이용하여 {@link Collection} 객체를 생성하는 방식은 여러 가지 제약과 오류의 소지가 확인되었습니다. <br>
     *             <font color="red">다음 배포시 삭제 예정</font>
     *
     * @since 2018. 1. 31.
     */
    public static <R extends Collection<Byte>> R toByte(Collection<Object> objects, Class<? extends R> classType)
            throws NullPointerException, UnsupportedOperationException, InstantiationException, IllegalAccessException {
        return to(objects, classType, byteFunction);
    }

    /**
     * {@link Object} Type {@link Collection}을 {@link Byte} Type {@link List}으로 변환한여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 4.      박준홍         최초 작성
     * </pre>
     *
     * @param objects
     *            변환할 데이터
     * @param transfomer
     *            데이터 변환 함수 (Object => Byte)
     * @return
     *
     * @since 2025. 9. 4.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static List<Byte> toByte(Collection<Object> objects, Function<Object, Byte> transformer) {
        return to(objects, transformer, ArrayList::new);
    }

    /**
     * {@link Object} Type {@link Collection}을 {@link Byte} Type {@link Collection}으로 변환한여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 4.      박준홍         최초 작성
     * </pre>
     *
     * @param <R>
     *            새로운 {@link Collection} 유형.
     * @param objects
     *            변환할 데이터
     * @param transfomer
     *            데이터 변환 함수 (Object => Byte)
     * @param collectionSupplier
     *            {@link Collection} 객체 제공 함수.
     * @return
     *
     * @since 2025. 9. 4.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <R extends Collection<Byte>> R toByte(Collection<Object> objects, Function<Object, Byte> transformer, Supplier<R> collectionSupplier) {
        return to(objects, transformer, collectionSupplier);
    }

    /**
     * {@link Object} Type {@link Collection}을 {@link Byte} Type {@link Collection}으로 변환한여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 4.      박준홍         최초 작성
     * </pre>
     *
     * @param <R>
     *            새로운 {@link Collection} 유형.
     * @param objects
     *            변환할 데이터
     * @param collectionSupplier
     *            {@link Collection} 객체 제공 함수.
     * @return
     *
     * @since 2025. 9. 4.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <R extends Collection<Byte>> R toByte(Collection<Object> objects, Supplier<R> collectionSupplier) {
        return to(objects, byteFunction, collectionSupplier);
    }

    /**
     * Object Type {@link Collection}을 Double Type {@link List}으로 변환한여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 4.      박준홍         최초 작성
     * </pre>
     *
     * @param objects
     *            변환할 데이터
     * @return
     *
     * @since 2025. 9. 4.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static List<Double> toDouble(Collection<Object> objects) {
        return to(objects, doubleFunction, ArrayList::new);
    }

    /**
     * {@link Object} Type {@link Collection}을 {@link Double} Type {@link Collection}으로 변환한여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 1. 31.     박준홍         최초 작성
     * </pre>
     *
     * @param <R>
     *            새로운 {@link Collection} 유형
     * @param objects
     *            변환할 데이터
     * @param classType
     *            새로운 {@link Collection} 객체
     * @return
     * @throws NullPointerException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws UnsupportedOperationException
     * 
     * @deprecated 2025. 9. 4. {@link #toDouble(Collection, Function, Supplier)} 또는 편의성에 따라 다른
     *             <code>toDouble(...)</code>를 사용하기 바랍니다. <code>Class</code>를 이용하여 {@link Collection} 객체를 생성하는 방식은 여러 가지
     *             제약과 오류의 소지가 확인되었습니다. <br>
     *             <font color="red">다음 배포시 삭제 예정</font>
     *
     * @since 2018. 1. 31.
     */
    public static <R extends Collection<Double>> R toDouble(Collection<Object> objects, Class<? extends R> classType)
            throws NullPointerException, UnsupportedOperationException, InstantiationException, IllegalAccessException {
        return to(objects, classType, doubleFunction);
    }

    /**
     * {@link Object} Type {@link Collection}을 {@link Double} Type {@link List}으로 변환한여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 4.      박준홍         최초 작성
     * </pre>
     *
     * @param objects
     *            변환할 데이터
     * @param transfomer
     *            데이터 변환 함수 (Object => Double)
     * @return
     *
     * @since 2025. 9. 4.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static List<Double> toDouble(Collection<Object> objects, Function<Object, Double> transformer) {
        return to(objects, transformer, ArrayList::new);
    }

    /**
     * {@link Object} Type {@link Collection}을 {@link Double} Type {@link Collection}으로 변환한여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 4.      박준홍         최초 작성
     * </pre>
     *
     * @param <R>
     *            새로운 {@link Collection} 유형.
     * @param objects
     *            변환할 데이터
     * @param transfomer
     *            데이터 변환 함수 (Object => Double)
     * @param collectionSupplier
     *            {@link Collection} 객체 제공 함수.
     * @return
     *
     * @since 2025. 9. 4.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <R extends Collection<Double>> R toDouble(Collection<Object> objects, Function<Object, Double> transformer, Supplier<R> collectionSupplier) {
        return to(objects, transformer, collectionSupplier);
    }

    /**
     * {@link Object} Type {@link Collection}을 {@link Double} Type {@link Collection}으로 변환한여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 4.      박준홍         최초 작성
     * </pre>
     *
     * @param <R>
     *            새로운 {@link Collection} 유형.
     * @param objects
     *            변환할 데이터
     * @param collectionSupplier
     *            {@link Collection} 객체 제공 함수.
     * @return
     *
     * @since 2025. 9. 4.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <R extends Collection<Double>> R toDouble(Collection<Object> objects, Supplier<R> collectionSupplier) {
        return to(objects, doubleFunction, collectionSupplier);
    }

    /**
     * Object Type {@link Collection}을 Float Type {@link List}으로 변환한여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 4.      박준홍         최초 작성
     * </pre>
     *
     * @param objects
     *            변환할 데이터
     * @return
     *
     * @since 2025. 9. 4.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static List<Float> toFloat(Collection<Object> objects) {
        return to(objects, floatFunction, ArrayList::new);
    }

    /**
     * {@link Object} Type {@link Collection}을 {@link Float} Type {@link Collection}으로 변환한여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 1. 31.     박준홍         최초 작성
     * </pre>
     *
     * @param <R>
     *            새로운 {@link Collection} 유형
     * @param objects
     *            변환할 데이터
     * @param classType
     *            새로운 {@link Collection} 객체
     * @return
     * @throws NullPointerException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws UnsupportedOperationException
     * 
     * @deprecated 2025. 9. 4. {@link #toFloat(Collection, Function, Supplier)} 또는 편의성에 따라 다른 <code>toFloat(...)</code>를
     *             사용하기 바랍니다. <code>Class</code>를 이용하여 {@link Collection} 객체를 생성하는 방식은 여러 가지 제약과 오류의 소지가 확인되었습니다. <br>
     *             <font color="red">다음 배포시 삭제 예정</font>
     *
     * @since 2018. 1. 31.
     */
    public static <R extends Collection<Float>> R toFloat(Collection<Object> objects, Class<? extends R> classType)
            throws NullPointerException, UnsupportedOperationException, InstantiationException, IllegalAccessException {
        return to(objects, classType, floatFunction);
    }

    /**
     * {@link Object} Type {@link Collection}을 {@link Float} Type {@link List}으로 변환한여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 4.      박준홍         최초 작성
     * </pre>
     *
     * @param objects
     *            변환할 데이터
     * @param transfomer
     *            데이터 변환 함수 (Object => Float)
     * @return
     *
     * @since 2025. 9. 4.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static List<Float> toFloat(Collection<Object> objects, Function<Object, Float> transformer) {
        return to(objects, transformer, ArrayList::new);
    }

    /**
     * {@link Object} Type {@link Collection}을 {@link Float} Type {@link Collection}으로 변환한여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 4.      박준홍         최초 작성
     * </pre>
     *
     * @param <R>
     *            새로운 {@link Collection} 유형.
     * @param objects
     *            변환할 데이터
     * @param transfomer
     *            데이터 변환 함수 (Object => Float)
     * @param collectionSupplier
     *            {@link Collection} 객체 제공 함수.
     * @return
     *
     * @since 2025. 9. 4.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <R extends Collection<Float>> R toFloat(Collection<Object> objects, Function<Object, Float> transformer, Supplier<R> collectionSupplier) {
        return to(objects, transformer, collectionSupplier);
    }

    /**
     * {@link Object} Type {@link Collection}을 {@link Float} Type {@link Collection}으로 변환한여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 4.      박준홍         최초 작성
     * </pre>
     *
     * @param <R>
     *            새로운 {@link Collection} 유형.
     * @param objects
     *            변환할 데이터
     * @param collectionSupplier
     *            {@link Collection} 객체 제공 함수.
     * @return
     *
     * @since 2025. 9. 4.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <R extends Collection<Float>> R toFloat(Collection<Object> objects, Supplier<R> collectionSupplier) {
        return to(objects, floatFunction, collectionSupplier);
    }

    /**
     * Object Type {@link Collection}을 Integer Type {@link List}으로 변환한여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 4.      박준홍         최초 작성
     * </pre>
     *
     * @param objects
     *            변환할 데이터
     * @return
     *
     * @since 2025. 9. 4.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static List<Integer> toInteger(Collection<Object> objects) {
        return to(objects, intFunction, ArrayList::new);
    }

    /**
     * {@link Object} Type {@link Collection}을 {@link Integer} Type {@link Collection}으로 변환한여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 1. 31.     박준홍         최초 작성
     * </pre>
     *
     * @param <R>
     *            새로운 {@link Collection} 유형
     * @param objects
     *            변환할 데이터
     * @param classType
     *            새로운 {@link Collection} 객체
     * @return
     * @throws NullPointerException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws UnsupportedOperationException
     * 
     * @deprecated 2025. 9. 4. {@link #toInteger(Collection, Function, Supplier)} 또는 편의성에 따라 다른
     *             <code>toInteger(...)</code>를 사용하기 바랍니다. <code>Class</code>를 이용하여 {@link Collection} 객체를 생성하는 방식은 여러
     *             가지 제약과 오류의 소지가 확인되었습니다. <br>
     *             <font color="red">다음 배포시 삭제 예정</font>
     *
     * @since 2018. 1. 31.
     */
    public static <R extends Collection<Integer>> R toInteger(Collection<Object> objects, Class<? extends R> classType)
            throws NullPointerException, UnsupportedOperationException, InstantiationException, IllegalAccessException {
        return to(objects, classType, intFunction);
    }

    /**
     * {@link Object} Type {@link Collection}을 {@link Integer} Type {@link List}으로 변환한여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 4.      박준홍         최초 작성
     * </pre>
     *
     * @param objects
     *            변환할 데이터
     * @param transfomer
     *            데이터 변환 함수 (Object => Integer)
     * @return
     *
     * @since 2025. 9. 4.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static List<Integer> toInteger(Collection<Object> objects, Function<Object, Integer> transformer) {
        return to(objects, transformer, ArrayList::new);
    }

    /**
     * {@link Object} Type {@link Collection}을 {@link Integer} Type {@link Collection}으로 변환한여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 4.      박준홍         최초 작성
     * </pre>
     *
     * @param <R>
     *            새로운 {@link Collection} 유형.
     * @param objects
     *            변환할 데이터
     * @param transfomer
     *            데이터 변환 함수 (Object => Integer)
     * @param collectionSupplier
     *            {@link Collection} 객체 제공 함수.
     * @return
     *
     * @since 2025. 9. 4.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <R extends Collection<Integer>> R toInteger(Collection<Object> objects, Function<Object, Integer> transformer, Supplier<R> collectionSupplier) {
        return to(objects, transformer, collectionSupplier);
    }

    /**
     * {@link Object} Type {@link Collection}을 {@link Integer} Type {@link Collection}으로 변환한여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 4.      박준홍         최초 작성
     * </pre>
     *
     * @param <R>
     *            새로운 {@link Collection} 유형.
     * @param objects
     *            변환할 데이터
     * @param collectionSupplier
     *            {@link Collection} 객체 제공 함수.
     * @return
     *
     * @since 2025. 9. 4.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <R extends Collection<Integer>> R toInteger(Collection<Object> objects, Supplier<R> collectionSupplier) {
        return to(objects, intFunction, collectionSupplier);
    }

    /**
     * Object Type {@link Collection}을 Long Type {@link List}으로 변환한여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 4.      박준홍         최초 작성
     * </pre>
     *
     * @param objects
     *            변환할 데이터
     * @return
     *
     * @since 2025. 9. 4.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static List<Long> toLong(Collection<Object> objects) {
        return to(objects, longFunction, ArrayList::new);
    }

    /**
     * {@link Object} Type {@link Collection}을 {@link Long} Type {@link Collection}으로 변환한여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 1. 31.     박준홍         최초 작성
     * </pre>
     *
     * @param <R>
     *            새로운 {@link Collection} 유형
     * @param objects
     *            변환할 데이터
     * @param classType
     *            새로운 {@link Collection} 객체
     * @return
     * @throws NullPointerException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws UnsupportedOperationException
     * 
     * @deprecated 2025. 9. 4. {@link #toLong(Collection, Function, Supplier)} 또는 편의성에 따라 다른 <code>toLong(...)</code>를
     *             사용하기 바랍니다. <code>Class</code>를 이용하여 {@link Collection} 객체를 생성하는 방식은 여러 가지 제약과 오류의 소지가 확인되었습니다. <br>
     *             <font color="red">다음 배포시 삭제 예정</font>
     *
     * @since 2018. 1. 31.
     */
    public static <R extends Collection<Long>> R toLong(Collection<Object> objects, Class<? extends R> classType)
            throws NullPointerException, UnsupportedOperationException, InstantiationException, IllegalAccessException {
        return to(objects, classType, longFunction);
    }

    /**
     * {@link Object} Type {@link Collection}을 {@link Long} Type {@link List}으로 변환한여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 4.      박준홍         최초 작성
     * </pre>
     *
     * @param objects
     *            변환할 데이터
     * @param transfomer
     *            데이터 변환 함수 (Object => Long)
     * @return
     *
     * @since 2025. 9. 4.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static List<Long> toLong(Collection<Object> objects, Function<Object, Long> transformer) {
        return to(objects, transformer, ArrayList::new);
    }

    /**
     * {@link Object} Type {@link Collection}을 {@link Long} Type {@link Collection}으로 변환한여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 4.      박준홍         최초 작성
     * </pre>
     *
     * @param <R>
     *            새로운 {@link Collection} 유형.
     * @param objects
     *            변환할 데이터
     * @param transfomer
     *            데이터 변환 함수 (Object => Long)
     * @param collectionSupplier
     *            {@link Collection} 객체 제공 함수.
     * @return
     *
     * @since 2025. 9. 4.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <R extends Collection<Long>> R toLong(Collection<Object> objects, Function<Object, Long> transformer, Supplier<R> collectionSupplier) {
        return to(objects, transformer, collectionSupplier);
    }

    /**
     * {@link Object} Type {@link Collection}을 {@link Long} Type {@link Collection}으로 변환한여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 4.      박준홍         최초 작성
     * </pre>
     *
     * @param <R>
     *            새로운 {@link Collection} 유형.
     * @param objects
     *            변환할 데이터
     * @param collectionSupplier
     *            {@link Collection} 객체 제공 함수.
     * @return
     *
     * @since 2025. 9. 4.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <R extends Collection<Long>> R toLong(Collection<Object> objects, Supplier<R> collectionSupplier) {
        return to(objects, longFunction, collectionSupplier);
    }

    /**
     * Object Type {@link Collection}을 Short Type {@link List}으로 변환한여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 4.      박준홍         최초 작성
     * </pre>
     *
     * @param objects
     *            변환할 데이터
     * @return
     *
     * @since 2025. 9. 4.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static List<Short> toShort(Collection<Object> objects) {
        return to(objects, shortFunction, ArrayList::new);
    }

    /**
     * {@link Object} Type {@link Collection}을 {@link Double} Type {@link Collection}으로 변환한여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 1. 31.     박준홍         최초 작성
     * </pre>
     *
     * @param <R>
     *            새로운 {@link Collection} 유형
     * @param objects
     *            변환할 데이터
     * @param classType
     *            새로운 {@link Collection} 객체
     * @return
     * @throws NullPointerException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws UnsupportedOperationException
     * 
     * @deprecated 2025. 9. 4. {@link #toShort(Collection, Function, Supplier)} 또는 편의성에 따라 다른 <code>toShort(...)</code>를
     *             사용하기 바랍니다. <code>Class</code>를 이용하여 {@link Collection} 객체를 생성하는 방식은 여러 가지 제약과 오류의 소지가 확인되었습니다. <br>
     *             <font color="red">다음 배포시 삭제 예정</font>
     *
     * @since 2018. 1. 31.
     */
    public static <R extends Collection<Short>> R toShort(Collection<Object> objects, Class<? extends R> classType)
            throws NullPointerException, UnsupportedOperationException, InstantiationException, IllegalAccessException {
        return to(objects, classType, shortFunction);
    }

    /**
     * {@link Object} Type {@link Collection}을 {@link Short} Type {@link List}으로 변환한여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 4.      박준홍         최초 작성
     * </pre>
     *
     * @param objects
     *            변환할 데이터
     * @param transfomer
     *            데이터 변환 함수 (Object => Short)
     * @return
     *
     * @since 2025. 9. 4.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static List<Short> toShort(Collection<Object> objects, Function<Object, Short> transformer) {
        return to(objects, transformer, ArrayList::new);
    }

    /**
     * {@link Object} Type {@link Collection}을 {@link Short} Type {@link Collection}으로 변환한여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 4.      박준홍         최초 작성
     * </pre>
     *
     * @param <R>
     *            새로운 {@link Collection} 유형.
     * @param objects
     *            변환할 데이터
     * @param transfomer
     *            데이터 변환 함수 (Object => Short)
     * @param collectionSupplier
     *            {@link Collection} 객체 제공 함수.
     * @return
     *
     * @since 2025. 9. 4.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <R extends Collection<Short>> R toShort(Collection<Object> objects, Function<Object, Short> transformer, Supplier<R> collectionSupplier) {
        return to(objects, transformer, collectionSupplier);
    }

    /**
     * {@link Object} Type {@link Collection}을 {@link Short} Type {@link Collection}으로 변환한여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 4.      박준홍         최초 작성
     * </pre>
     *
     * @param <R>
     *            새로운 {@link Collection} 유형.
     * @param objects
     *            변환할 데이터
     * @param collectionSupplier
     *            {@link Collection} 객체 제공 함수.
     * @return
     *
     * @since 2025. 9. 4.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <R extends Collection<Short>> R toShort(Collection<Object> objects, Supplier<R> collectionSupplier) {
        return to(objects, shortFunction, collectionSupplier);
    }

    /**
     * Object Type {@link Collection}을 String Type {@link List}으로 변환한여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 4.      박준홍         최초 작성
     * </pre>
     *
     * @param objects
     *            변환할 데이터
     * @return
     *
     * @since 2025. 9. 4.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static List<String> toString(Collection<Object> objects) {
        return to(objects, strFunction, ArrayList::new);
    }

    /**
     * {@link Object} Type {@link Collection}을 {@link String} Type {@link Collection}으로 변환한여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 1. 31.     박준홍         최초 작성
     * </pre>
     *
     * @param <R>
     *            새로운 {@link Collection} 유형
     * @param objects
     *            변환할 데이터
     * @param classType
     *            새로운 {@link Collection} 객체
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws UnsupportedOperationException
     * 
     * @deprecated 2025. 9. 4. {@link #toString(Collection, Function, Supplier)} 또는 편의성에 따라 다른
     *             <code>toString(...)</code>를 사용하기 바랍니다. <code>Class</code>를 이용하여 {@link Collection} 객체를 생성하는 방식은 여러 가지
     *             제약과 오류의 소지가 확인되었습니다. <br>
     *             <font color="red">다음 배포시 삭제 예정</font>
     *
     * @since 2018. 1. 31.
     */
    public static <R extends Collection<String>> R toString(Collection<Object> objects, Class<? extends R> classType)
            throws UnsupportedOperationException, InstantiationException, IllegalAccessException {
        return to(objects, classType, strFunction);
    }

    /**
     * {@link Object} Type {@link Collection}을 {@link String} Type {@link List}으로 변환한여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 4.      박준홍         최초 작성
     * </pre>
     *
     * @param objects
     *            변환할 데이터
     * @param transfomer
     *            데이터 변환 함수 (Object => String)
     * @return
     *
     * @since 2025. 9. 4.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static List<String> toString(Collection<Object> objects, Function<Object, String> transformer) {
        return to(objects, transformer, ArrayList::new);
    }

    /**
     * {@link Object} Type {@link Collection}을 {@link String} Type {@link Collection}으로 변환한여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 4.      박준홍         최초 작성
     * </pre>
     *
     * @param <R>
     *            새로운 {@link Collection} 유형.
     * @param objects
     *            변환할 데이터
     * @param transfomer
     *            데이터 변환 함수 (Object => String)
     * @param collectionSupplier
     *            {@link Collection} 객체 제공 함수.
     * @return
     *
     * @since 2025. 9. 4.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <R extends Collection<String>> R toString(Collection<Object> objects, Function<Object, String> transformer, Supplier<R> collectionSupplier) {
        return to(objects, transformer, collectionSupplier);
    }

    /**
     * {@link Object} Type {@link Collection}을 {@link String} Type {@link Collection}으로 변환한여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 4.      박준홍         최초 작성
     * </pre>
     *
     * @param <R>
     *            새로운 {@link Collection} 유형.
     * @param objects
     *            변환할 데이터
     * @param collectionSupplier
     *            {@link Collection} 객체 제공 함수.
     * @return
     *
     * @since 2025. 9. 4.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <R extends Collection<String>> R toString(Collection<Object> objects, Supplier<R> collectionSupplier) {
        return to(objects, strFunction, collectionSupplier);
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
     * 2025. 8. 30.     박준홍         내부 구현을 {@link #transform(Collection, boolean, Supplier, boolean, Map, Supplier)}으로 전환.
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
     * @param targetType
     *            데이터를 전달받을 새로운 데이터 유형..
     * @param lookupTargetSuper
     *            대상 객체 상위 인터페이스/클래스 확장 여부
     * @param converters
     *            데이터 변환 함수
     *            <ul>
     *            <li>{@link #FIELD_CONVERTER_KEYGEN} 로 만들어진 식별정보
     *            <li>타입 변환 함수
     *            </ul>
     * @param collectionSupplier
     *            대상 데이터를 포함할 {@link Collection} 제공 함수
     * @return
     *
     * @since 2025. 4. 3.
     * @version 2.1.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <S, T, C extends Collection<T>> C transform(Collection<S> src, boolean lookupSrcSuper, Class<T> targetType, boolean lookupTargetSuper,
            Map<String, Function<?, ?>> converters, Supplier<C> collectionSupplier) {
        return transform(src, lookupSrcSuper //
                , (Supplier<T>) () -> {
                    try {
                        return (T) targetType.newInstance();
                    } catch (Exception e) {
                        throw new CreateInstanceFailedException(targetType, e);
                    }
                }, lookupTargetSuper //
                , converters, collectionSupplier);
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
     * @param targetType
     *            기존 데이터 정보를 전달받을 새로운 데이터 유형.
     * @param lookupTargetSuper
     *            대상 객체 상위 인터페이스/클래스 확장 여부
     * @param collectionSupplier
     *            대상 데이터를 포함할 {@link Collection}
     * @return
     *
     * @since 2025. 4. 3.
     * @version 2.1.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <S, T, C extends Collection<T>> C transform(Collection<S> src, boolean lookupSrcSuper, Class<T> targetType, boolean lookupTargetSuper,
            Supplier<C> collectionSupplier) {
        return transform(src, lookupSrcSuper, targetType, lookupTargetSuper, FIELD_CONVERTERS, collectionSupplier);
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
     * @param targetType
     *            데이터를 전달받을 새로운 데이터 유형.
     * @param converters
     *            데이터 변환 함수
     *            <ul>
     *            <li>{@link #FIELD_CONVERTER_KEYGEN} 로 만들어진 식별정보
     *            <li>타입 변환 함수
     *            </ul>
     * @param collectionSupplier
     *            대상 데이터를 포함할 {@link Collection}
     * @return
     *
     * @since 2025. 4. 3.
     * @version 2.1.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <S, T, C extends Collection<T>> C transform(Collection<S> src, boolean lookupSrcSuper, Class<T> targetType, Map<String, Function<?, ?>> converters,
            Supplier<C> collectionSupplier) {
        return transform(src, lookupSrcSuper, targetType, false, converters, collectionSupplier);
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
     * @param targetType
     *            데이터를 전달받을 새로운 데이터 유형.
     * @param collectionSupplier
     *            대상 데이터를 포함할 {@link Collection}
     * @return
     *
     * @since 2025. 4. 3.
     * @version 2.1.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <S, T, C extends Collection<T>> C transform(Collection<S> src, boolean lookupSrcSuper, Class<T> targetType, Supplier<C> collectionSupplier) {
        return transform(src, lookupSrcSuper, targetType, false, FIELD_CONVERTERS, collectionSupplier);
    }

    /**
     * 입력데이터 타입에서 정의된 메소드 중에서 {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로
     * 제공합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 30.     박준홍         최초 작성
     * </pre>
     *
     * * @param <S> 입력 데이터 타입
     * 
     * @param <T>
     *            대상 데이터 타입
     * @param <C>
     *            변환 후 데이터 {@link Collection}
     * @param src
     *            입력 데이터
     * @param lookupSrcSuper
     *            입력 데이터 클래스 상위 인터페이스/클래스 확장 여부
     * @param targetInstanceSupplier
     *            새로운 데이터 객체 제공 함수.
     * @param lookupTargetSuper
     *            대상 객체 상위 인터페이스/클래스 확장 여부
     * @param converters
     *            데이터 변환 함수
     *            <ul>
     *            <li>{@link #FIELD_CONVERTER_KEYGEN} 로 만들어진 식별정보
     *            <li>타입 변환 함수
     *            </ul>
     * @param collectionSupplier
     *            대상 데이터를 포함할 {@link Collection} 제공 함수
     * @return
     *
     * @since 2025. 8. 30.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <S, T, C extends Collection<T>> C transform(Collection<S> src, boolean lookupSrcSuper, Supplier<T> targetInstanceSupplier, boolean lookupTargetSuper,
            Map<String, Function<?, ?>> converters, Supplier<C> collectionSupplier) {
        return src.stream() //
                .map(s -> transform(s, lookupSrcSuper, targetInstanceSupplier.get(), lookupTargetSuper, converters)) //
                .collect(Collectors.toCollection(collectionSupplier));
    }

    /**
     * 입력데이터 타입에서 정의된 메소드 중에서 {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로
     * 제공합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 30.     박준홍         최초 작성
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
     * @param targetInstanceSupplier
     *            새로운 데이터 객체 제공 함수.
     * @param lookupTargetSuper
     *            대상 객체 상위 인터페이스/클래스 확장 여부
     * @param collectionSupplier
     *            대상 데이터를 포함할 {@link Collection}
     * @return
     *
     * @since 2025. 8. 30.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <S, T, C extends Collection<T>> C transform(Collection<S> src, boolean lookupSrcSuper, Supplier<T> targetInstanceSupplier, boolean lookupTargetSuper,
            Supplier<C> collectionSupplier) {
        return transform(src, lookupSrcSuper, targetInstanceSupplier, lookupTargetSuper, FIELD_CONVERTERS, collectionSupplier);
    }

    /**
     * 입력데이터 타입에서 정의된 메소드 중에서 {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로
     * 제공합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 30.      박준홍         최초 작성
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
     * @param targetInstanceSupplier
     *            새로운 데이터 개체 제공함수.
     * @param converters
     *            데이터 변환 함수
     *            <ul>
     *            <li>{@link #FIELD_CONVERTER_KEYGEN} 로 만들어진 식별정보
     *            <li>타입 변환 함수
     *            </ul>
     * @param collectionSupplier
     *            대상 데이터를 포함할 {@link Collection}
     * @return
     *
     * @since 2025. 8. 30.
     * @version 2.1.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <S, T, C extends Collection<T>> C transform(Collection<S> src, boolean lookupSrcSuper, Supplier<T> targetInstanceSupplier, Map<String, Function<?, ?>> converters,
            Supplier<C> collectionSupplier) {
        return transform(src, lookupSrcSuper, targetInstanceSupplier, false, converters, collectionSupplier);
    }

    /**
     * 입력데이터 타입에서 정의된 메소드 중에서 {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로
     * 제공합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 30.      박준홍         최초 작성
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
     * @param targetInstanceSupplier
     *            새로운 데이터 객체 제공 함수.
     * @param collectionSupplier
     *            대상 데이터를 포함할 {@link Collection}
     * @return
     *
     * @since 2025. 8. 30.
     * @version 2.1.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <S, T, C extends Collection<T>> C transform(Collection<S> src, boolean lookupSrcSuper, Supplier<T> targetInstanceSupplier, Supplier<C> collectionSupplier) {
        return transform(src, lookupSrcSuper, targetInstanceSupplier, false, FIELD_CONVERTERS, collectionSupplier);
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
     * @param targetType
     *            데이터를 전달받은 객체.
     * @param lookupTargetSuper
     *            대상 객체 상위 인터페이스/클래스 확장 여부
     * @param converters
     *            데이터 변환 함수
     *            <ul>
     *            <li>{@link #FIELD_CONVERTER_KEYGEN} 로 만들어진 식별정보
     *            <li>타입 변환 함수
     *            </ul>
     * @param collectionSupplier
     *            대상 데이터를 포함할 {@link Collection}
     * @return
     *
     * @since 2025. 4. 3.
     * @version 2.1.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <S, T, C extends Collection<T>> C transform(Collection<S> src, Class<T> targetType, boolean lookupTargetSuper, Map<String, Function<?, ?>> converters,
            Supplier<C> collectionSupplier) {
        return transform(src, false, targetType, lookupTargetSuper, converters, collectionSupplier);
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
     * @param targetType
     *            데이터를 전달받을 새로운 데이터 유형.
     * @param lookupTargetSuper
     *            대상 객체 상위 인터페이스/클래스 확장 여부
     * @param collectionSupplier
     *            대상 데이터를 포함할 {@link Collection}
     * @return
     *
     * @since 2025. 4. 3.
     * @version 2.1.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <S, T, C extends Collection<T>> C transform(Collection<S> src, Class<T> targetType, boolean lookupTargetSuper, Supplier<C> collectionSupplier) {
        return transform(src, false, targetType, lookupTargetSuper, FIELD_CONVERTERS, collectionSupplier);
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
     * @param targetType
     *            데이터를 전달받을 새로운 데이터 유형.
     * @param converters
     *            데이터 변환 함수
     *            <ul>
     *            <li>{@link #FIELD_CONVERTER_KEYGEN} 로 만들어진 식별정보
     *            <li>타입 변환 함수
     *            </ul>
     * @param collectionSupplier
     *            대상 데이터를 포함할 {@link Collection}
     * @return
     *
     * @since 2025. 4. 3.
     * @version 2.1.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <S, T, C extends Collection<T>> C transform(Collection<S> src, Class<T> targetType, Map<String, Function<?, ?>> converters, Supplier<C> collectionSupplier) {
        return transform(src, false, targetType, false, converters, collectionSupplier);
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
     * @param targetType
     *            데이터를 전달받을 새로운 데이터 유형.
     * @param collectionSupplier
     *            대상 데이터를 포함할 {@link Collection}
     * @return
     *
     * @since 2025. 4. 3.
     * @version 2.1.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <S, T, C extends Collection<T>> C transform(Collection<S> src, Class<T> targetType, Supplier<C> collectionSupplier) {
        return transform(src, false, targetType, false, FIELD_CONVERTERS, collectionSupplier);
    }

    /**
     * 입력데이터 타입에서 정의된 메소드 중에서 {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로
     * 제공합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 30.      박준홍         최초 작성
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
     * @param targetInstanceSupplier
     *            새로운 데이터 객체 제공 함수.
     * @param lookupTargetSuper
     *            대상 객체 상위 인터페이스/클래스 확장 여부
     * @param converters
     *            데이터 변환 함수
     *            <ul>
     *            <li>{@link #FIELD_CONVERTER_KEYGEN} 로 만들어진 식별정보
     *            <li>타입 변환 함수
     *            </ul>
     * @param collectionSupplier
     *            대상 데이터를 포함할 {@link Collection}
     * @return
     *
     * @since 2025. 8. 30.
     * @version 2.1.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <S, T, C extends Collection<T>> C transform(Collection<S> src, Supplier<T> targetInstanceSupplier, boolean lookupTargetSuper,
            Map<String, Function<?, ?>> converters, Supplier<C> collectionSupplier) {
        return transform(src, false, targetInstanceSupplier, lookupTargetSuper, converters, collectionSupplier);
    }

    /**
     * 입력데이터 타입에서 정의된 메소드 중에서 {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로
     * 제공합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 30.      박준홍         최초 작성
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
     * @param targetInstanceSupplier
     *            새로운 데이터 객체 제공 함수.
     * @param lookupTargetSuper
     *            대상 객체 상위 인터페이스/클래스 확장 여부
     * @param collectionSupplier
     *            대상 데이터를 포함할 {@link Collection}
     * @return
     *
     * @since 2025. 8. 30.
     * @version 2.1.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <S, T, C extends Collection<T>> C transform(Collection<S> src, Supplier<T> targetInstanceSupplier, boolean lookupTargetSuper, Supplier<C> collectionSupplier) {
        return transform(src, false, targetInstanceSupplier, lookupTargetSuper, FIELD_CONVERTERS, collectionSupplier);
    }

    /**
     * 입력데이터 타입에서 정의된 메소드 중에서 {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로
     * 제공합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 30.      박준홍         최초 작성
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
     * @param targetInstanceSupplier
     *            새로운 데이터 객체 제공 함수.
     * @param converters
     *            데이터 변환 함수
     *            <ul>
     *            <li>{@link #FIELD_CONVERTER_KEYGEN} 로 만들어진 식별정보
     *            <li>타입 변환 함수
     *            </ul>
     * @param collectionSupplier
     *            대상 데이터를 포함할 {@link Collection}
     * @return
     *
     * @since 2025. 8. 30.
     * @version 2.1.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <S, T, C extends Collection<T>> C transform(Collection<S> src, Supplier<T> targetInstanceSupplier, Map<String, Function<?, ?>> converters,
            Supplier<C> collectionSupplier) {
        return transform(src, false, targetInstanceSupplier, false, converters, collectionSupplier);
    }

    /**
     * 입력데이터 타입에서 정의된 메소드 중에서 {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로
     * 제공합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 30.      박준홍         최초 작성
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
     * @param targetInstanceSupplier
     *            새로운 데이터 객체 제공 함수.
     * @param collectionSupplier
     *            대상 데이터를 포함할 {@link Collection}
     * @return
     *
     * @since 2025. 8. 30.
     * @version 2.1.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <S, T, C extends Collection<T>> C transform(Collection<S> src, Supplier<T> targetInstanceSupplier, Supplier<C> collectionSupplier) {
        return transform(src, false, targetInstanceSupplier, false, FIELD_CONVERTERS, collectionSupplier);
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
     * @param lookupTargetSuper
     *            변환 대상 클래스 상위 인터페이스/클래스 확장 여부
     * @return
     *
     * @since 2019. 7. 11.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <S, T> T transform(S src, boolean lookupSrcSuper, Class<T> targetType, boolean lookupTargetSuper) {
        return transform(src, lookupSrcSuper //
                , (Supplier<T>) () -> {
                    try {
                        return (T) targetType.newInstance();
                    } catch (Exception e) {
                        throw new CreateInstanceFailedException(targetType, e);
                    }
                }, lookupTargetSuper);
    }

    /**
     * 입력데이터 타입에서 정의된 메소드 중에서 {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로
     * 제공합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 22.        박준홍         최초 작성
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
        return transform(src, lookupSrcSuper //
                , (Supplier<T>) () -> {
                    try {
                        return targetType.newInstance();
                    } catch (Exception e) {
                        throw new CreateInstanceFailedException(targetType, e);
                    }
                }, lookupTargetSuper //
                , converters);
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
     * 2025. 8. 30.     박준홍         최초 작성
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
     * @param targetInstanceSupplier
     *            새로운 데이터 객체 제공 함수.
     * @return
     *
     * @since 2025. 8. 30.
     * @version 2.1.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @see #transform(Object, boolean, Class, boolean)
     */
    public static <S, T> T transform(S src, boolean lookupSrcSuper, Supplier<T> targetInstanceSupplier) {
        return transform(src, lookupSrcSuper, targetInstanceSupplier, false);
    }

    /**
     * 입력데이터 타입에서 정의된 메소드 중에서 {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로
     * 제공합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 30.     박준홍         최초 작성
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
     * @param targetInstanceSupplier
     *            새로운 데이터 객체 제공 함수.
     * @param lookupTargetSuper
     *            변환 대상 클래스 상위 인터페이스/클래스 확장 여부
     * @return
     *
     * @since 2025. 8. 30.
     * @version 2.1.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <S, T> T transform(S src, boolean lookupSrcSuper, Supplier<T> targetInstanceSupplier, boolean lookupTargetSuper) {
        return transform(src, lookupSrcSuper, targetInstanceSupplier.get(), lookupTargetSuper);
    }

    /**
     * 입력데이터 타입에서 정의된 메소드 중에서 {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로
     * 제공합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 30.     박준홍         최초 작성
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
     * @param targetInstanceSupplier
     *            새로운 데이터 객체 제공 함수.
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
     * @since 2025. 8. 30.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <S, T> T transform(S src, boolean lookupSrcSuper, Supplier<T> targetInstanceSupplier, boolean lookupTargetSuper, Map<String, Function<?, ?>> converters) {
        return transform(src, lookupSrcSuper, targetInstanceSupplier.get(), lookupTargetSuper, converters);
    }

    /**
     * {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 30.     박준홍         최초 작성
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
     * @param targetInstanceSupplier
     *            새로운 데이터 객체 제공 함수.
     * @param converters
     *            데이터 변환 함수
     *            <ul>
     *            <li>{@link #FIELD_CONVERTER_KEYGEN} 로 만들어진 식별정보
     *            <li>타입 변환 함수
     *            </ul>
     * @return
     *
     * @since 2025. 8. 30.
     * @version 2.1.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @see #transform(Object, boolean, Class, boolean)
     */
    public static <S, T> T transform(S src, boolean lookupSrcSuper, Supplier<T> targetInstanceSupplier, Map<String, Function<?, ?>> converters) {
        return transform(src, lookupSrcSuper, targetInstanceSupplier, false, converters);
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
     * @param targetType
     *            데이터를 전달받을 새로운 데이터 유형.
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
     * @param targetType
     *            데이터를 전달받을 새로운 데이터 유형.
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
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 12. 08.     박준홍         최초 작성
     * 2021. 11. 22.        박준홍      Map&lt;String, Function&lt;Object, Object&gt;&gt; 추가
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
     *            데이터를 전달받을 새로운 객체.
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
    public static <S, T> T transform(S src, boolean lookupSrcSuper, T target, boolean lookupTargetSuper, Map<String, Function<?, ?>> converters) {

        AssertUtils2.notNulls("'source' object or 'target' type must NOT be null !!!", IllegalArgumentException.class, src, target);

        // 데이터 변환함수가 null 인 경우
        CopierKey key = new CopierKey(src.getClass(), target.getClass(), lookupSrcSuper, lookupTargetSuper,
                /* convertersId */ System.identityHashCode(checkConvertersOrDefault(converters)) // 또는 고정 Registry ID
        );

        BiConsumer<Object, Object> copier = COPIER_CACHE.computeIfAbsent(key, k -> buildCopier(src.getClass(), lookupSrcSuper, target.getClass(), lookupTargetSuper, converters));
        copier.accept(target, src);

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
     * @param targetType
     *            데이터를 전달받을 새로운 데이터 유형.
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
     * 2025. 8. 30.     박준홍         최초 작성
     * </pre>
     *
     * @param <S>
     *            입력 데이터 타입 정의.
     * @param <T>
     *            신규 데이터 타입 정의.
     * @param src
     *            입력 데이터
     * @param targetInstanceSupplier
     *            새로운 데이터 객체 제공 함수.
     * @return
     *
     * @since 2025. 8. 30.
     * @version 2.1.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @see Getter
     * @see Setter
     */
    public static <S, T> T transform(S src, Supplier<T> targetInstanceSupplier) {
        return transform(src, false, targetInstanceSupplier, false);
    }

    /**
     * 입력데이터 타입에서 정의된 메소드 중에서 입력데이터 타입에서 정의된 메소드 중에서 {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를
     * 변환하여 새로운 타입의 객체로 제공합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 30.     박준홍         최초 작성
     * </pre>
     *
     * @param <S>
     *            입력 데이터 타입 정의.
     * @param <T>
     *            신규 데이터 타입 정의.
     * @param src
     *            입력 데이터.
     * @param targetInstanceSupplier
     *            새로운 데이터 객체 제공 함수.
     * @param lookupTargetSuper
     *            변환 대상 클래스 상위 인터페이스/클래스 확장 여부
     * @return
     *
     * @since 2025. 8. 30.
     * @version 2.1.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <S, T> T transform(S src, Supplier<T> targetInstanceSupplier, boolean lookupTargetSuper) {
        return transform(src, false, targetInstanceSupplier, lookupTargetSuper);
    }

    /**
     * 입력데이터 타입에서 정의된 메소드 중에서 입력데이터 타입에서 정의된 메소드 중에서 {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를
     * 변환하여 새로운 타입의 객체로 제공합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 30.     박준홍         최초 작성
     * </pre>
     *
     * @param <S>
     *            입력 데이터 타입 정의.
     * @param <T>
     *            신규 데이터 타입 정의.
     * @param src
     *            입력 데이터.
     * @param targetInstanceSupplier
     *            새로운 데이터 객체 제공 함수.
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
     * @since 2025. 8. 30.
     * @version 2.1.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <S, T> T transform(S src, Supplier<T> targetInstanceSupplier, boolean lookupTargetSuper, Map<String, Function<?, ?>> converters) {
        return transform(src, false, targetInstanceSupplier, lookupTargetSuper, converters);
    }

    /**
     * 입력데이터 타입에서 정의된 메소드 중에서 {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로 제공합니다.
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 30.     박준홍         최초 작성
     * </pre>
     *
     * @param <S>
     *            입력 데이터 타입 정의.
     * @param <T>
     *            신규 데이터 타입 정의.
     * @param src
     *            입력 데이터
     * @param targetInstanceSupplier
     *            변환 타입
     * @param converters
     *            데이터 변환 함수.
     *            <ul>
     *            <li>{@link #FIELD_CONVERTER_KEYGEN} 로 만들어진 식별정보
     *            <li>타입 변환 함수
     *            </ul>
     * @return
     *
     * @since 2025. 8. 30.
     * @version 2.1.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @see Getter
     * @see Setter
     */
    public static <S, T> T transform(S src, Supplier<T> targetInstanceSupplier, Map<String, Function<?, ?>> converters) {
        return transform(src, false, targetInstanceSupplier, false, converters);
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
     * @param targetType
     *            데이터를 전달받을 새로운 데이터 유형.
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
     * @param targetType
     *            데이터를 전달받을 새로운 데이터 유형.
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
     * @param targetType
     *            데이터를 전달받을 새로운 데이터 유형.
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
     * @param targetType
     *            데이터를 전달받을 새로운 데이터 유형.
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

    /**
     * 입력데이터 타입에서 정의된 메소드 중에서 {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로 제공합니다.
     * <br>
     * 상위 클래스에서 정의한 내용도 이관합니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 30.     박준홍         최초 작성
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
     * @param targetType
     *            데이터를 전달받을 새로운 데이터 유형.
     * @param converters
     *            데이터 변환 함수
     *            <ul>
     *            <li>{@link #FIELD_CONVERTER_KEYGEN} 로 만들어진 식별정보
     *            <li>타입 변환 함수
     *            </ul>
     * @param collectionSupplier
     *            대상 데이터를 포함할 {@link Collection}
     * @return
     *
     * @since 2025. 8. 30.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see #transform(Object, boolean, Object, boolean, Map, Supplier)
     */
    public static <S, T, C extends Collection<T>> C transformAll(Collection<S> src, Class<T> targetType, Map<String, Function<?, ?>> converters, Supplier<C> collectionSupplier) {
        return transform(src, true, targetType, true, converters, collectionSupplier);
    }

    /**
     * 입력데이터 타입에서 정의된 메소드 중에서 {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로 제공합니다.
     * <br>
     * 상위 클래스에서 정의한 내용도 이관합니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 30.     박준홍         최초 작성
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
     * @param targetType
     *            데이터를 전달받을 새로운 데이터 유형.
     * @param collectionSupplier
     *            대상 데이터를 포함할 {@link Collection}
     * @return
     *
     * @since 2025. 8. 30.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see #transform(Object, boolean, Object, boolean, Map, Supplier)
     */
    public static <S, T, C extends Collection<T>> C transformAll(Collection<S> src, Class<T> targetType, Supplier<C> collectionSupplier) {
        return transform(src, true, targetType, true, FIELD_CONVERTERS, collectionSupplier);
    }

    /**
     * 입력데이터 타입에서 정의된 메소드 중에서 {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로 제공합니다.
     * <br>
     * 상위 클래스에서 정의한 내용도 이관합니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 30.     박준홍         최초 작성
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
     * @param targetInstanceSupplier
     *            새로운 데이터 객체 제공 함수.
     * @param converters
     *            데이터 변환 함수
     *            <ul>
     *            <li>{@link #FIELD_CONVERTER_KEYGEN} 로 만들어진 식별정보
     *            <li>타입 변환 함수
     *            </ul>
     * @param collectionSupplier
     *            대상 데이터를 포함할 {@link Collection}
     * @return
     *
     * @since 2025. 8. 30.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see #transform(Object, boolean, Object, boolean, Map, Supplier)
     */
    public static <S, T, C extends Collection<T>> C transformAll(Collection<S> src, Supplier<T> targetInstanceSupplier, Map<String, Function<?, ?>> converters,
            Supplier<C> collectionSupplier) {
        return transform(src, true, targetInstanceSupplier, true, converters, collectionSupplier);
    }

    /**
     * 입력데이터 타입에서 정의된 메소드 중에서 {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로 제공합니다.
     * <br>
     * 상위 클래스에서 정의한 내용도 이관합니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 30.     박준홍         최초 작성
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
     * @param targetInstanceSupplier
     *            새로운 데이터 객체 제공 함수.
     * @param collectionSupplier
     *            대상 데이터를 포함할 {@link Collection}
     * @return
     *
     * @since 2025. 8. 30.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see #transform(Object, boolean, Object, boolean, Map, Supplier)
     */
    public static <S, T, C extends Collection<T>> C transformAll(Collection<S> src, Supplier<T> targetInstanceSupplier, Supplier<C> collectionSupplier) {
        return transform(src, true, targetInstanceSupplier, true, FIELD_CONVERTERS, collectionSupplier);
    }

    /**
     * 입력데이터 타입에서 정의된 메소드 중에서 {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로 제공합니다.
     * <br>
     * 상위 클래스에서 정의한 내용도 이관합니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 30.     박준홍         최초 작성
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
     * @since 2025. 8. 30.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <S, T> T transformAll(S src, Class<T> targetType) {
        return transform(src, true, targetType, true);
    }

    /**
     * 입력데이터 타입에서 정의된 메소드 중에서 {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로 제공합니다.
     * <br>
     * 상위 클래스에서 정의한 내용도 이관합니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 30.     박준홍         최초 작성
     * </pre>
     *
     * @param <S>
     *            입력 데이터 타입 정의.
     * @param <T>
     *            신규 데이터 타입 정의.
     * @param src
     *            입력 데이터
     * @param targetInstanceSupplier
     *            새로운 데이터 객체 제공 함수.
     * @return
     *
     * @since 2025. 8. 30.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <S, T> T transformAll(S src, Supplier<T> targetInstanceSupplier) {
        return transform(src, true, targetInstanceSupplier, true);
    }

    /**
     * 입력데이터 타입에서 정의된 메소드 중에서 {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로 제공합니다.
     * <br>
     * 상위 클래스에서 정의한 내용도 이관합니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 30.     박준홍         최초 작성
     * </pre>
     *
     * @param <S>
     *            입력 데이터 타입 정의.
     * @param <T>
     *            신규 데이터 타입 정의.
     * @param src
     *            입력 데이터
     * @param targetType
     *            데이터를 전달받을 새로운 데이터 유형.
     * @return
     *
     * @since 2025. 8. 30.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see #transform(Object, boolean, Class, boolean)
     */
    public static <S, T> T transformAll(S src, T target) {
        return transform(src, true, target, true);
    }

    /**
     * 입력데이터 타입에서 정의된 메소드 중에서 {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로 제공합니다.
     * <br>
     * 상위 클래스에서 정의한 내용도 이관합니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 30.     박준홍         최초 작성
     * </pre>
     *
     * @param <S>
     *            입력 데이터 타입 정의.
     * @param <T>
     *            신규 데이터 타입 정의.
     * @param src
     *            입력 데이터
     * @param targetType
     *            데이터를 전달받을 새로운 데이터 유형.
     * @param converters
     *            데이터 변환 함수
     *            <ul>
     *            <li>{@link #FIELD_CONVERTER_KEYGEN} 로 만들어진 식별정보
     *            <li>타입 변환 함수
     *            </ul>
     * @return
     *
     * @since 2025. 8. 30.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see #transform(Object, boolean, Object, boolean)
     */
    public static <S, T> T transformAll(S src, T target, Map<String, Function<?, ?>> converters) {
        return transform(src, true, target, true, converters);
    }

    static final class CopierKey {
        final Class<?> src;
        final Class<?> dst;
        final boolean lookupSrcSuper;
        final boolean lookupDstSuper;
        final int convertersId;

        /**
         *
         * @param src
         *            원본 데이터 유형
         * @param dst
         *            변환 데이터 유형
         * @param lookupSrcSuper
         *            원본 데이터 상위 클래스 정보 전환 여부
         * @param lookupDstSuper
         *            변환 데이터 상위 클래스 정보 전환 여부
         * @param convertersId
         *            변환기 식별정보
         *
         * @since 2025. 9. 4.
         * @version 2.1.0
         * @author Park Jun-Hong (parkjunhong77@gmail.com)
         */
        public CopierKey(Class<?> src, Class<?> dst, boolean lookupSrcSuper, boolean lookupDstSuper, int convertersId) {
            this.src = src;
            this.dst = dst;
            this.lookupSrcSuper = lookupSrcSuper;
            this.lookupDstSuper = lookupDstSuper;
            this.convertersId = convertersId;
        }

        /**
         *
         * @since 2025. 9. 4.
         * @version 2.1.0
         * @author Park Jun-Hong (parkjunhong77@gmail.com)
         *
         * @see java.lang.Object#equals(java.lang.Object)
         */
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            CopierKey other = (CopierKey) obj;
            return convertersId == other.convertersId && Objects.equals(dst, other.dst) && lookupDstSuper == other.lookupDstSuper && lookupSrcSuper == other.lookupSrcSuper
                    && Objects.equals(src, other.src);
        }

        /**
         *
         * @since 2025. 9. 4.
         * @version 2.1.0
         * @author Park Jun-Hong (parkjunhong77@gmail.com)
         *
         * @see java.lang.Object#hashCode()
         */
        @Override
        public int hashCode() {
            return Objects.hash(convertersId, dst, lookupDstSuper, lookupSrcSuper, src);
        }
    }

    static final class StepPlan {

        static final Function<?, ?> IDENTITY_CONVERT = o -> o;
        /**
         * 속성 이름<br>
         * <li>{@link Getter#name()}
         * <li>{@link Setter#name()}
         */
        final String property;
        /** 원본 클래스의 'getter' 메소드. */
        final Method getter;
        /** 대상 클래스의 'setter' 메소드 */
        final Method setter;
        /** 데이터 형변환 함수. */
        final Function<?, ?> converter;

        /**
         * <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜      | 작성자   |   내용
         * ------------------------------------------
         * 2025. 9. 4.      박준홍         최초 작성
         * </pre>
         * 
         * @param property
         *            속성 이름<br>
         *            <li>{@link Getter#name()}
         *            <li>{@link Setter#name()}
         * @param getter
         *            원본 클래스의 'getter' 메소드.
         * @param setter
         *            대상 클래스의 'setter' 메소드
         * @param converter
         *            데이터 형변환 함수.
         *
         * @since 2025. 9. 4.
         * @version 2.1.0
         * @author Park Jun-Hong (parkjunhong77@gmail.com)
         */
        public StepPlan(String property, Method getter, Method setter, Function<?, ?> converterOrIdentity) {
            this.property = property;
            this.getter = getter;
            this.setter = setter;
            this.converter = converterOrIdentity != null ? converterOrIdentity : IDENTITY_CONVERT;
        }
    }

}
