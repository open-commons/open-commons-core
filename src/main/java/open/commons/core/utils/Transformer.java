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
 * Date  : 2025. 9. 5. 오후 3:05:28
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package open.commons.core.utils;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.time.temporal.Temporal;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import open.commons.core.annotation.Getter;
import open.commons.core.annotation.Setter;
import open.commons.core.exception.TransformationFailedException;
import open.commons.core.function.PentagonFunction;
import open.commons.core.utils.Transformer.StepPlan.ContainerKind;

/**
 * 
 * @since 2025. 9. 5.
 * @version 2.1.0
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
public class Transformer {

    private static final Logger LOGGER = LoggerFactory.getLogger(Transformer.class);

    /**
     * 데이터 제공 메소드 이름 유형
     * <li>is: boolean
     * <li>get: 일반적으로 전체
     */
    private static final Pattern METHOD_GETTER = Pattern.compile("^(is|get)(.+)$");
    /**
     * 데이터 설정 메소드 유형
     * 
     * <li>add: {@link Collection}
     * <li>put: {@link Map}
     * <li>set: 일반적으로 전체
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 12. 22.    parkjunhong77@gmail.com     최초 작성
     * 2023. 12. 5.     parkjunhong77@gmail.com     데이터 설정함수 prefix 추가 (addXXX 지원)
     * 2025. 9. 5.      parkjunhong77@gmail.com     데이터 설정함수 prefix 추가 (putXXX 지원)
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
     * 
     */
    private static final Map<CopierKey, BiConsumer<Object, Object>> COPIER_CACHE = new ConcurrentHashMap<>();

    private Transformer() {
    }

    /** Type을 가능한 한 Class로 환산한다. 불가하면 null */
    private static Class<?> asClass(Type t) {
        if (t instanceof Class) {
            return (Class<?>) t;
        } else if (t instanceof ParameterizedType) {
            // 예: List<Foo>라면 rawType = List.class를 반환
            Type rt = ((ParameterizedType) t).getRawType();
            return (rt instanceof Class) ? (Class<?>) rt : null;
        } else if (t instanceof WildcardType) {
            // ? extends X 또는 ? super X -> 우선 상한(upper bound) 사용
            Type[] ub = ((WildcardType) t).getUpperBounds();
            if (ub != null && ub.length > 0)
                return asClass(ub[0]);
            Type[] lb = ((WildcardType) t).getLowerBounds();
            if (lb != null && lb.length > 0)
                return asClass(lb[0]);
            return null;
        } else if (t instanceof TypeVariable) {
            // T extends X & Y ... -> 첫 상한을 사용
            Type[] bounds = ((TypeVariable<?>) t).getBounds();
            if (bounds != null && bounds.length > 0)
                return asClass(bounds[0]);
            return null;
        }
        return null;
    }

    /**
     * 데이터 이관 함수를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 8.		박준홍			최초 작성
     * </pre>
     *
     * @param srcClass
     *            원본 데이터 유형
     * @param lookupSrcSuper
     *            원본 데이터 상위 클래스 정보 이관 여부
     * @param targetClass
     *            대상 데이터 유형
     * @param lookupTargetSuper
     *            대상 데이터 상위 클래스 정보 이관 여부
     * @param converters
     *            데이터 변환 함수
     * @return
     *
     * @since 2025. 9. 8.
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
        MethodHandle setter = null;
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
                getter = MethodHandles.explicitCastArguments(getter, MethodType.methodType(Object.class, Object.class));

                // ===== 1) deep=false 또는 SCALAR =====
                if (!plan.deepConvert || plan.kind == ContainerKind.SCALAR) {
                    setter = lookup.unreflect(plan.setter);
                    setter = MethodHandles.explicitCastArguments(setter, MethodType.methodType(void.class, Object.class, Object.class));

                    Class<?> srcType = plan.getter.getReturnType();
                    Class<?> dstType = plan.setter.getParameterTypes()[0];

                    conv = buildValueConverterMH(lookup, srcType, dstType, lookupSrcSuper, lookupTargetSuper, converters, plan.property, plan.converter, plan.deepConvert);

                    converted = MethodHandles.filterReturnValue(getter, conv); // (a)->val'
                    step = MethodHandles.collectArguments(setter, 1, converted); // (b,a)->void
                    step = MethodHandles.explicitCastArguments(step, MethodType.methodType(void.class, Object.class, Object.class));
                    mhSteps.add(step);
                    continue;
                }

                // ===== 2) ARRAY =====
                if (plan.kind == ContainerKind.ARRAY) {
                    Class<?> srcElem = componentTypeOfReturn(plan.getter);
                    Class<?> dstElem = plan.setter.getParameterTypes()[0].getComponentType();
                    MethodHandle elemConv = buildElementConverterMH(lookup, srcElem, dstElem, lookupSrcSuper, lookupTargetSuper, converters, plan.property, plan.deepConvert);

                    setter = lookup.unreflect(plan.setter);
                    step = makeDeepArrayStep(lookup, getter, setter, elemConv, dstElem);
                    mhSteps.add(step);
                    continue;
                }

                // ===== 3) COLLECTION =====
                if (plan.kind == ContainerKind.COLLECTION) {

                    // 1) 컨테이너 단위 컨버터 우선 (setXxx(Collection) 1-파라미터일 때만)
                    if (plan.setter.getParameterCount() == 1 //
                            && plan.converter != null //
                            && plan.converter != StepPlan.IDENTITY_CONVERT) {

                        // (Object)->Object 로 바인드
                        MethodHandle convContainer = lookup.findVirtual(Function.class //
                                , "apply" //
                                , MethodType.methodType(Object.class, Object.class)) //
                                .bindTo(plan.converter);

                        setter = lookup.unreflect(plan.setter);
                        setter = MethodHandles.explicitCastArguments(setter, MethodType.methodType(void.class, Object.class, Object.class));

                        // (a)->convertedContainer
                        converted = MethodHandles.filterReturnValue(getter, convContainer);
                        // (b,a)->void
                        step = MethodHandles.collectArguments(setter, 1, converted);
                        step = MethodHandles.explicitCastArguments(step, MethodType.methodType(void.class, Object.class, Object.class));
                        mhSteps.add(step);
                        continue; // deep 경로 건너뜀
                    }

                    // 2) adder or 컨테이너 컨버터 없음 → deep 요소 변환
                    if (plan.adderStyle) {
                        // addXxx(E) — 요소 단위 추가
                        Class<?> srcElem = elementTypeOfGetter(plan.getter);
                        Class<?> dstElem = plan.setter.getParameterTypes()[0];
                        MethodHandle elemConv = buildElementConverterMH(lookup, srcElem, dstElem, lookupSrcSuper, lookupTargetSuper, converters, plan.property, plan.deepConvert);

                        setter = lookup.unreflect(plan.setter); // addXxx(E)
                        step = makeDeepCollectionAddStep(lookup, getter, setter, elemConv);
                        mhSteps.add(step);
                    } else {
                        // setXxx(Collection<E>)
                        Class<?> srcElem = elementTypeOfGetter(plan.getter);
                        Class<?> dstParam = plan.setter.getParameterTypes()[0];
                        Class<?> dstElem = genericElementTypeOfSetter(plan.setter).orElse(Object.class);
                        MethodHandle elemConv = buildElementConverterMH(lookup, srcElem, dstElem, lookupSrcSuper, lookupTargetSuper, converters, plan.property, plan.deepConvert);

                        @SuppressWarnings("rawtypes")
                        Class<? extends Collection> dstImpl = pickCollectionImpl(dstParam);
                        setter = lookup.unreflect(plan.setter);
                        step = makeDeepCollectionSetStep(lookup, getter, setter, elemConv, dstImpl);
                        mhSteps.add(step);
                    }
                    continue;
                }

                // ===== 4) MAP =====
                if (plan.kind == ContainerKind.MAP) {
                    if (plan.putStyle) {
                        // putXxx(K,V): 항목 단위 삽입
                        Class<?>[] pts = plan.setter.getParameterTypes();
                        Class<?> kDst = pts[0], vDst = pts[1];
                        Class<?> kSrc = mapKeyTypeOfGetter(plan.getter);
                        Class<?> vSrc = mapValTypeOfGetter(plan.getter);

                        MethodHandle kConv = buildElementConverterMH(lookup, kSrc, kDst, lookupSrcSuper, lookupTargetSuper, converters, plan.property, plan.deepConvert);
                        MethodHandle vConv = buildElementConverterMH(lookup, vSrc, vDst, lookupSrcSuper, lookupTargetSuper, converters, plan.property, plan.deepConvert);

                        setter = lookup.unreflect(plan.setter); // putXxx(K,V)
                        step = makeDeepMapPutStep(lookup, getter, setter, kConv, vConv);
                        mhSteps.add(step);
                    } else {
                        // setXxx(Map<K,V>)
                        Class<?> dstParam = plan.setter.getParameterTypes()[0];
                        Class<?> kDst = mapKeyTypeOfSetter(plan.setter).orElse(Object.class);
                        Class<?> vDst = mapValTypeOfSetter(plan.setter).orElse(Object.class);
                        Class<?> kSrc = mapKeyTypeOfGetter(plan.getter);
                        Class<?> vSrc = mapValTypeOfGetter(plan.getter);

                        MethodHandle kConv = buildValueConverterMH(lookup, kSrc, kDst, lookupSrcSuper, lookupTargetSuper, converters, plan.property);
                        MethodHandle vConv = buildValueConverterMH(lookup, vSrc, vDst, lookupSrcSuper, lookupTargetSuper, converters, plan.property);

                        @SuppressWarnings("rawtypes")
                        Class<? extends Map> dstImpl = pickMapImpl(dstParam);
                        setter = lookup.unreflect(plan.setter);
                        step = makeDeepMapSetStep(lookup, getter, setter, kConv, vConv, dstImpl);
                        mhSteps.add(step);
                    }
                }
            }

            // 3-2) 루프 본체: (target, src, mhStepArr)->void
            MethodHandle impl = //
                    lookup.findStatic( //
                            // 또는 별도 Fast 클래스
                            Transformer.class, "runSteps" //
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
            LOGGER.error("getter    = {}", getter);
            LOGGER.error("setter    = {}", setter);
            LOGGER.error("converter = {}, conver.after = {}", conv, converted);
            LOGGER.error("step      = {}", step);
            throw new RuntimeException("Failed to build copier: " + srcClass + "->" + targetClass, t);
        }
    }

    // 추가: forceDeepSameType 플래그
    private static MethodHandle buildElementConverterMH(MethodHandles.Lookup lookup, Class<?> srcElem, Class<?> dstElem, boolean lookupSrcSuper, boolean lookupTargetSuper,
            Map<String, Function<?, ?>> converters, String property, boolean deepConvert) throws Exception {

        boolean sameOrWiden = wrap(dstElem).isAssignableFrom(wrap(srcElem));

        // same-type 이고 deep을 강제한다면 nested copier로 내려간다
        if (sameOrWiden && deepConvert && isPojo(dstElem)) {
            return makeNestedCopierAsConverter(lookup, srcElem, dstElem, lookupSrcSuper, lookupTargetSuper, converters);
        }

        // 평소 정책: 동일/호환 → identity
        if (sameOrWiden) {
            return MethodHandles.identity(Object.class);
        }

        // 등록 컨버터 우선
        Function<?, ?> f = getFieldConverter(srcElem, srcElem, property, dstElem, dstElem, converters);
        if (f != null) {
            return lookup.findVirtual(Function.class, "apply", MethodType.methodType(Object.class, Object.class)).bindTo(f);
        }

        // 나머지는 nested copier fallback
        return makeNestedCopierAsConverter(lookup, srcElem, dstElem, lookupSrcSuper, lookupTargetSuper, converters);
    }

    // 스칼라/요소 공통: (Object)->Object 변환기 MethodHandle
    private static MethodHandle buildValueConverterMH(MethodHandles.Lookup lookup, Class<?> srcElem, Class<?> dstElem, boolean lookupSrcSuper, boolean lookupTargetSuper,
            Map<String, Function<?, ?>> converters, String property) throws Exception {

        Class<?> ws = wrap(srcElem), wd = wrap(dstElem);
        if (wd.isAssignableFrom(ws)) {
            return MethodHandles.identity(Object.class); // (Object)->Object
        }

        // 등록된 필드 컨버터 우선
        Function<?, ?> f = getFieldConverter(srcElem, srcElem, property, dstElem, dstElem, converters);
        if (f != null) {
            return lookup.findVirtual(Function.class //
                    , "apply" //
                    , MethodType.methodType(Object.class, Object.class)) //
                    .bindTo(f);
        }

        // POJO -> POJO: nested Copier 사용
        BiConsumer<Object, Object> nested = COPIER_CACHE.computeIfAbsent(
                new CopierKey(srcElem, dstElem, lookupSrcSuper, lookupTargetSuper, /* converters version/id */ 0) //
                , k -> buildCopier(srcElem, lookupSrcSuper, dstElem, lookupTargetSuper, converters));

        MethodHandle ctor = constructorMH(lookup, dstElem); // ()->Object
        MethodHandle impl = //
                lookup.findStatic(//
                        Transformer.class //
                        , "convertPojoElem" //
                        , MethodType.methodType(Object.class, Object.class, BiConsumer.class, MethodHandle.class) //
                );
        impl = MethodHandles.insertArguments(impl, 1, new Object[] { nested, ctor });
        return MethodHandles.explicitCastArguments(impl, MethodType.methodType(Object.class, Object.class));
    }

    private static MethodHandle buildValueConverterMH(MethodHandles.Lookup lookup, Class<?> srcType, Class<?> dstType, boolean lookupSrcSuper, boolean lookupTargetSuper,
            Map<String, Function<?, ?>> converters, String property, Function<?, ?> planConverter, boolean deepConvert) throws Exception {

        Class<?> ws = wrap(srcType), wd = wrap(dstType);
        boolean sameOrWiden = wd.isAssignableFrom(ws);

        // ★ deep 요청이고 동일/호환 타입이며 POJO라면, identity 대신 nested copier로 강제
        if (sameOrWiden && deepConvert && isPojo(dstType)) {
            return makeNestedCopierAsConverter(lookup, srcType, dstType, lookupSrcSuper, lookupTargetSuper, converters);
        }

        // 0) 동일/호환 → 기본은 identity (deep 강제 조건에 걸리지 않았을 때)
        if (sameOrWiden) {
            return MethodHandles.identity(Object.class);
        }

        // 1) 플랜에 명시 컨버터가 있으면 우선
        if (planConverter != null && planConverter != StepPlan.IDENTITY_CONVERT) {
            return lookup.findVirtual(Function.class //
                    , "apply" //
                    , MethodType.methodType(Object.class, Object.class)).bindTo(planConverter);
        }

        // 2) 필드 컨버터 레지스트리에서 조회 (선택: 플랜과 동일 정책이면 생략 가능)
        Function<?, ?> f = getFieldConverter(srcType, srcType, property, dstType, dstType, checkConvertersOrDefault(converters));
        if (f != null) {
            return lookup.findVirtual(Function.class //
                    , "apply" //
                    , MethodType.methodType(Object.class, Object.class)).bindTo(f);
        }

        // 3) POJO ↔ POJO fallback: nested copier
        return makeNestedCopierAsConverter(lookup, srcType, dstType, lookupSrcSuper, lookupTargetSuper, converters);
    }

    private static Map<String, Function<?, ?>> checkConvertersOrDefault(Map<String, Function<?, ?>> converters) {
        return MapUtils.isNullOrEmpty(converters) ? FIELD_CONVERTERS : converters;
    }

    private static Class<?> componentTypeOfReturn(Method getter) {
        Class<?> rt = getter.getReturnType();
        if (rt.isArray())
            return rt.getComponentType();
        Type gt = getter.getGenericReturnType();
        if (gt instanceof ParameterizedType) {
            Type[] args = ((ParameterizedType) gt).getActualTypeArguments();
            if (args.length == 1 && args[0] instanceof Class)
                return (Class<?>) args[0];
        }
        return Object.class;
    }

    private static MethodHandle constructorMH(MethodHandles.Lookup lookup, Class<?> cls) throws NoSuchMethodException, IllegalAccessException {
        MethodHandle h = lookup.findConstructor(cls, MethodType.methodType(void.class)); // ()V
        return h.asType(MethodType.methodType(Object.class));
    }

    /**
     * {@link #buildValueConverterMH(java.lang.invoke.MethodHandles.Lookup, Class, Class, boolean, boolean, Map, String)},
     * {@link #buildValueConverterMH(java.lang.invoke.MethodHandles.Lookup, Class, Class, boolean, boolean, Map, String, Function, boolean)}에서
     * 호출하는 'MethodHandle' 대상. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 8.		박준홍			최초 작성
     * </pre>
     *
     * @param src
     * @param copier
     * @param ctor
     * @return
     *
     * @since 2025. 9. 8.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    @SuppressWarnings("unused")
    private static Object convertPojoElem(Object src, BiConsumer<Object, Object> copier, MethodHandle ctor) {
        if (src == null) {
            return null;
        }
        Object dst = null;
        try {
            dst = ctor.invoke(); // ()->Object
            copier.accept(dst, src);
            return dst;
        } catch (Throwable t) {
            throw new TransformationFailedException(src.getClass(), dst != null ? dst.getClass() : null, t);
        }
    }

    /**
     * {@link #makeDeepArrayStep(java.lang.invoke.MethodHandles.Lookup, MethodHandle, MethodHandle, MethodHandle, Class)}에서
     * 호출하는 'MethodHandle' 대상. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 8.		박준홍			최초 작성
     * </pre>
     *
     * @param b
     * @param a
     * @param getter
     * @param setter
     * @param elemConv
     * @param dstElem
     *
     * @since 2025. 9. 8.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    @SuppressWarnings("unused")
    private static void deepCopyArray(Object b, Object a, MethodHandle getter, MethodHandle setter, MethodHandle elemConv, Class<?> dstElem) {
        try {
            Object srcArr = getter.invoke(a);
            if (srcArr == null) {
                setter.invoke(b, (Object) null);
                return;
            }
            int len = Array.getLength(srcArr);
            Object dstArr = Array.newInstance(dstElem, len);
            for (int i = 0; i < len; i++) {
                Object sv = Array.get(srcArr, i);
                Object dv = elemConv.invoke(sv);
                Array.set(dstArr, i, dv);
            }
            setter.invoke(b, dstArr);
        } catch (Throwable t) {
            throw new TransformationFailedException(t);
        }
    }

    /**
     * {@link #makeDeepCollectionAddStep(java.lang.invoke.MethodHandles.Lookup, MethodHandle, MethodHandle, MethodHandle)}에서
     * 호출하는 'MethodHandle' 대상. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 8.		박준홍			최초 작성
     * </pre>
     *
     * @param b
     * @param a
     * @param getter
     * @param adder
     * @param elemConv
     *
     * @since 2025. 9. 8.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    @SuppressWarnings({ "rawtypes", "unused" })
    private static void deepCopyCollectionAdd(Object b, Object a, MethodHandle getter, MethodHandle adder, MethodHandle elemConv) {
        try {
            Object src = getter.invoke(a);
            if (src == null) {
                return;
            }
            if (src.getClass().isArray()) {
                int len = Array.getLength(src);
                for (int i = 0; i < len; i++) {
                    Object dv = elemConv.invoke(Array.get(src, i));
                    adder.invoke(b, dv);
                }
            } else {
                for (Object sv : (java.lang.Iterable) src) {
                    Object dv = elemConv.invoke(sv);
                    adder.invoke(b, dv);
                }
            }
        } catch (Throwable t) {
            throw new TransformationFailedException(t);
        }
    }

    /**
     * {@link #makeDeepCollectionSetStep(java.lang.invoke.MethodHandles.Lookup, MethodHandle, MethodHandle, MethodHandle, Class)}
     * 메소드가 호출하는 'MethodHandle' 대상. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 8.		박준홍			최초 작성
     * </pre>
     *
     * @param b
     * @param a
     * @param getter
     * @param setter
     * @param elemConv
     * @param dstImpl
     *
     * @since 2025. 9. 8.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    @SuppressWarnings({ "rawtypes", "unchecked", "unused" })
    private static void deepCopyCollectionSet(Object b, Object a, MethodHandle getter, MethodHandle setter, MethodHandle elemConv, Class<? extends Collection> dstImpl) {
        try {
            Object src = getter.invoke(a);
            if (src == null) {
                setter.invoke(b, (Object) null);
                return;
            }
            Collection dst = (Collection) dstImpl.newInstance();
            if (src.getClass().isArray()) {
                int len = Array.getLength(src);
                for (int i = 0; i < len; i++) {
                    Object dv = elemConv.invoke(Array.get(src, i));
                    dst.add(dv);
                }
            } else {
                for (Object sv : (Iterable) src) {
                    Object dv = elemConv.invoke(sv);
                    dst.add(dv);
                }
            }
            setter.invoke(b, dst);
        } catch (Throwable t) {
            throw new TransformationFailedException(t);
        }
    }

    /**
     * {@link #makeDeepMapPutStep(java.lang.invoke.MethodHandles.Lookup, MethodHandle, MethodHandle, MethodHandle, MethodHandle)}에서
     * 호출하는 'MethodHandle' 대상 <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 8.		박준홍			최초 작성
     * </pre>
     *
     * @param b
     * @param a
     * @param getter
     * @param put
     * @param kConv
     * @param vConv
     *
     * @since 2025. 9. 8.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    @SuppressWarnings({ "rawtypes", "unused" })
    private static void deepCopyMapPut(Object b, Object a, MethodHandle getter, MethodHandle put, MethodHandle kConv, MethodHandle vConv) {
        try {
            Object srcObj = getter.invoke(a);
            if (srcObj == null) {
                return;
            }
            Map src = (Map) srcObj;
            // put는 (Object,Object,Object)->void 로 어댑트되어 있어야 함
            put = MethodHandles.explicitCastArguments(put, MethodType.methodType(void.class, Object.class, Object.class, Object.class));
            for (Object eObj : src.entrySet()) {
                Map.Entry e = (Map.Entry) eObj;
                Object k2 = kConv.invoke(e.getKey());
                Object v2 = vConv.invoke(e.getValue());
                put.invoke(b, k2, v2);
            }
        } catch (Throwable t) {
            throw new TransformationFailedException(t);
        }
    }

    /**
     * {@link #makeDeepMapSetStep(java.lang.invoke.MethodHandles.Lookup, MethodHandle, MethodHandle, MethodHandle, MethodHandle, Class)}
     * 내부에서 호출하는 'MethodHandle' 대상. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 8.		박준홍			최초 작성
     * </pre>
     *
     * @param b
     * @param a
     * @param getter
     * @param setter
     * @param kConv
     * @param vConv
     * @param dstImpl
     *
     * @since 2025. 9. 8.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    @SuppressWarnings({ "rawtypes", "unchecked", "unused" })
    private static void deepCopyMapSet(Object b, Object a, MethodHandle getter, MethodHandle setter, MethodHandle kConv, MethodHandle vConv, Class<? extends Map> dstImpl) {
        try {
            Object srcObj = getter.invoke(a);
            if (srcObj == null) {
                setter.invoke(b, (Object) null);
                return;
            }
            Map src = (Map) srcObj;
            Map dst = dstImpl.newInstance();
            for (Object eObj : src.entrySet()) {
                Map.Entry e = (Map.Entry) eObj;
                Object k2 = kConv.invoke(e.getKey());
                Object v2 = vConv.invoke(e.getValue());
                dst.put(k2, v2);
            }
            setter.invoke(b, dst);
        } catch (Throwable t) {
            throw new TransformationFailedException(t);
        }
    }

    private static Class<?> elementTypeOfGetter(Method getter) {
        return componentTypeOfReturn(getter);
    }

    /**
     * setXxx(Collection<E>) 형태의 세터에서 E의 런타임 Class를 추출한다. - 파라미터가 1개가 아니거나, Collection이 아니거나, E를 Class로 확정할 수 없으면
     * Optional.empty() - E가 ? extends X / ? super X / T(타입변수)인 경우, 상한/첫 바운드를 Class로 환산해 반환 시도
     */
    private static Optional<Class<?>> genericElementTypeOfSetter(Method setter) {
        // putXxx(K,V) 같은 2-파라미터 세터는 대상 아님
        if (setter.getParameterCount() != 1) {
            return Optional.empty();
        }
        Type p = setter.getGenericParameterTypes()[0];

        // 파라미터 자체가 Collection이어야 함
        Class<?> raw = rawTypeOf(p);
        if (raw == null || !Collection.class.isAssignableFrom(raw)) {
            return Optional.empty();
        }

        // 파라미터가 제네릭이면 첫 번째 타입 인수를 추출
        if (p instanceof ParameterizedType) {
            Type[] args = ((ParameterizedType) p).getActualTypeArguments();
            if (args.length == 1) {
                Class<?> elem = asClass(args[0]);
                return Optional.ofNullable(elem);
            }
        }

        // 제네릭 정보가 없거나 알 수 없으면 비워둠 (호출부에서 Object.class로 대체)
        return Optional.empty();
    }

    /**
     * 주어진 정보에 맞는 데이터 변환 함수를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 22.     parkjunohng77@gmail.com         최초 작성
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
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 22.     parkjunohng77@gmail.com         최초 작성
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

        int is = ObjectUtils.isPrimitiveOrWrapper(type);
        if (is > 0) {
            types.add(ConvertUtils.translateToWrapper(type));
        } else if (is < 0) {
            types.add(ConvertUtils.translateToPrimitive(type));
        }

        return types;
    }

    private static boolean isPojo(Class<?> c) {
        return !(c.isPrimitive() //
                || Number.class.isAssignableFrom(c) //
                || c == Boolean.class //
                || c == Character.class
                // String 포함
                || CharSequence.class.isAssignableFrom(c) //
                || Enum.class.isAssignableFrom(c) //
                || Date.class.isAssignableFrom(c) //
                || Temporal.class.isAssignableFrom(c) //
        );
    }

    /* ========= Array ========= */
    private static MethodHandle makeDeepArrayStep(MethodHandles.Lookup lookup, MethodHandle getter, MethodHandle setter, MethodHandle elemConv, Class<?> dstElem) throws Exception {

        MethodHandle impl = //
                lookup.findStatic(//
                        Transformer.class //
                        , "deepCopyArray" //
                        , MethodType.methodType(void.class, Object.class, Object.class, MethodHandle.class, MethodHandle.class, MethodHandle.class, Class.class) //
                );
        impl = MethodHandles.insertArguments(impl, 2, new Object[] { getter, setter, elemConv, dstElem });
        return MethodHandles.explicitCastArguments(impl, MethodType.methodType(void.class, Object.class, Object.class));
    }

    /* ========= Collection:add ========= */
    private static MethodHandle makeDeepCollectionAddStep(MethodHandles.Lookup lookup, MethodHandle getter, MethodHandle adder, MethodHandle elemConv) throws Exception {

        MethodHandle impl = //
                lookup.findStatic(//
                        Transformer.class //
                        , "deepCopyCollectionAdd" //
                        , MethodType.methodType(void.class, Object.class, Object.class, MethodHandle.class, MethodHandle.class, MethodHandle.class) //
                );
        impl = MethodHandles.insertArguments(impl, 2, new Object[] { getter, adder, elemConv });
        return MethodHandles.explicitCastArguments(impl, MethodType.methodType(void.class, Object.class, Object.class));
    }

    /* ========= Collection:set ========= */
    @SuppressWarnings("rawtypes")
    private static MethodHandle makeDeepCollectionSetStep(MethodHandles.Lookup lookup, MethodHandle getter, MethodHandle setter, MethodHandle elemConv,
            Class<? extends Collection> dstImpl) throws Exception {

        MethodHandle impl = //
                lookup.findStatic(//
                        Transformer.class //
                        , "deepCopyCollectionSet" //
                        , MethodType.methodType(void.class, Object.class, Object.class, MethodHandle.class, MethodHandle.class, MethodHandle.class, Class.class) //
                );
        impl = MethodHandles.insertArguments(impl, 2, new Object[] { getter, setter, elemConv, dstImpl });
        return MethodHandles.explicitCastArguments(impl, MethodType.methodType(void.class, Object.class, Object.class));
    }

    /* ========= Map:put ========= */
    private static MethodHandle makeDeepMapPutStep(MethodHandles.Lookup lookup, MethodHandle getter, MethodHandle put, MethodHandle kConv, MethodHandle vConv) throws Exception {

        MethodHandle impl = //
                lookup.findStatic(//
                        Transformer.class //
                        , "deepCopyMapPut" //
                        , MethodType.methodType(void.class, Object.class, Object.class, MethodHandle.class, MethodHandle.class, MethodHandle.class) //
                );
        impl = MethodHandles.insertArguments(impl, 2, new Object[] { getter, put, kConv, vConv });
        return MethodHandles.explicitCastArguments(impl, MethodType.methodType(void.class, Object.class, Object.class));
    }

    /* ========= Map:set ========= */
    @SuppressWarnings("rawtypes")
    private static MethodHandle makeDeepMapSetStep(MethodHandles.Lookup lookup, MethodHandle getter, MethodHandle setter, MethodHandle keyConv, MethodHandle valConv,
            Class<? extends Map> dstImpl) throws Exception {

        MethodHandle impl = //
                lookup.findStatic(//
                        Transformer.class //
                        , "deepCopyMapSet",
                        MethodType.methodType(void.class, Object.class, Object.class, MethodHandle.class, MethodHandle.class, MethodHandle.class, MethodHandle.class, Class.class) //
                );
        impl = MethodHandles.insertArguments(impl, 2, new Object[] { getter, setter, keyConv, valConv, dstImpl });
        return MethodHandles.explicitCastArguments(impl, MethodType.methodType(void.class, Object.class, Object.class));
    }

    private static MethodHandle makeNestedCopierAsConverter(MethodHandles.Lookup lookup, Class<?> src, Class<?> dst, boolean lookupSrcSuper, boolean lookupTargetSuper,
            Map<String, Function<?, ?>> converters) throws Exception {
        BiConsumer<Object, Object> nested = COPIER_CACHE.computeIfAbsent(new CopierKey(src, dst, lookupSrcSuper, lookupTargetSuper, 0),
                k -> buildCopier(src, lookupSrcSuper, dst, lookupTargetSuper, converters));

        MethodHandle ctor = constructorMH(lookup, dst); // ()->Object
        MethodHandle impl = //
                lookup.findStatic(//
                        Transformer.class //
                        , "convertPojoElem" //
                        , MethodType.methodType(Object.class, Object.class, BiConsumer.class, MethodHandle.class) //
                );
        impl = MethodHandles.insertArguments(impl, 1, new Object[] { nested, ctor });
        return MethodHandles.explicitCastArguments(impl, MethodType.methodType(Object.class, Object.class));
    }

    private static Class<?> mapKeyType(Type t) {
        if (t instanceof ParameterizedType) {
            Type[] a = ((ParameterizedType) t).getActualTypeArguments();
            if (a.length == 2 && a[0] instanceof Class)
                return (Class<?>) a[0];
        }
        return Object.class;
    }

    private static Class<?> mapKeyTypeOfGetter(Method getter) {
        return mapKeyType(getter.getGenericReturnType());
    }

    private static Optional<Class<?>> mapKeyTypeOfSetter(Method setter) {
        Type[] pts = setter.getGenericParameterTypes();
        if (pts.length == 1)
            return Optional.ofNullable(mapKeyType(pts[0]));
        return Optional.empty();
    }

    private static Class<?> mapValType(Type t) {
        if (t instanceof ParameterizedType) {
            Type[] a = ((ParameterizedType) t).getActualTypeArguments();
            if (a.length == 2 && a[1] instanceof Class)
                return (Class<?>) a[1];
        }
        return Object.class;
    }

    private static Class<?> mapValTypeOfGetter(Method getter) {
        return mapValType(getter.getGenericReturnType());
    }

    private static Optional<Class<?>> mapValTypeOfSetter(Method setter) {
        Type[] pts = setter.getGenericParameterTypes();
        if (pts.length == 1)
            return Optional.ofNullable(mapValType(pts[0]));
        return Optional.empty();
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private static Class<? extends Collection> pickCollectionImpl(Class<?> declared) {
        if (!Collection.class.isAssignableFrom(declared)) {
            throw new IllegalArgumentException("Not a Collection: " + declared);
        }
        if (declared.isInterface()) {
            if (List.class.isAssignableFrom(declared))
                return ArrayList.class;
            if (Set.class.isAssignableFrom(declared))
                return LinkedHashSet.class;
            if (Queue.class.isAssignableFrom(declared))
                return ArrayDeque.class;
            return ArrayList.class; // 기본
        }
        return (Class<? extends Collection>) declared;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private static Class<? extends Map> pickMapImpl(Class<?> declared) {
        if (!Map.class.isAssignableFrom(declared)) {
            throw new IllegalArgumentException("Not a Map: " + declared);
        }
        if (declared.isInterface()) {
            if (SortedMap.class.isAssignableFrom(declared))
                return TreeMap.class;
            if (ConcurrentMap.class.isAssignableFrom(declared))
                return ConcurrentHashMap.class;
            return LinkedHashMap.class; // 기본(순서를 보존)
        }
        return (Class<? extends Map>) declared;
    }

    private static <S, T> List<StepPlan> planSteps(Class<S> srcClass, boolean lookupSrcSuper, Class<T> targetClass, boolean lookupTargetSuper,
            Map<String, Function<?, ?>> converters) {

        List<Method> getters = lookupSrcSuper ? AnnotationUtils.getAnnotatedMethodsAll(srcClass, Getter.class) : AnnotationUtils.getAnnotatedMethods(srcClass, Getter.class);
        List<Method> setters = lookupTargetSuper ? AnnotationUtils.getAnnotatedMethodsAll(targetClass, Setter.class)
                : AnnotationUtils.getAnnotatedMethods(targetClass, Setter.class);

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

            // 메타 정보 처리
            Setter annoSetter = setter.getAnnotation(Setter.class);
            boolean deepConvert = annoSetter.deepConvert();
            // setter parameter count
            int pc = setter.getParameterCount();
            // setter 1번째 파라미터 유형
            Class<?> p0 = pc >= 1 ? setter.getParameterTypes()[0] : null;

            // 메소드 제공 데이터 유형 정보
            boolean isArray = (p0 != null) && p0.isArray();
            boolean isCollection = (p0 != null) && Collection.class.isAssignableFrom(p0);
            boolean isMapParam = (p0 != null) && Map.class.isAssignableFrom(p0);
            boolean putStyle = (pc == 2) && setter.getName().startsWith("put");
            boolean adderStyle = (pc == 1) && setter.getName().startsWith("add");

            ContainerKind kind;
            if (putStyle) {
                kind = ContainerKind.MAP;
            } else if (isMapParam) {
                kind = ContainerKind.MAP;
            } else if (isArray) {
                kind = ContainerKind.ARRAY;
            } else if (isCollection) {
                kind = ContainerKind.COLLECTION;
            } else {
                kind = ContainerKind.SCALAR;
            }

            // 변환 함수
            Class<?> setterParamClass = pc == 1 ? PARAMETER_TYPE.apply(setter) : Object.class;
            converter = getFieldConverter(srcClass, RETURN_TYPE.apply(getter), property, targetClass, setterParamClass, converters);

            plans.add(new StepPlan(property, getter, setter, converter, deepConvert, kind, adderStyle, putStyle));
        }

        return plans;
    }

    /** Type의 raw class를 반환. 없으면 null */
    private static Class<?> rawTypeOf(Type t) {
        if (t instanceof Class)
            return (Class<?>) t;
        if (t instanceof ParameterizedType) {
            Type rt = ((ParameterizedType) t).getRawType();
            return (rt instanceof Class) ? (Class<?>) rt : null;
        }
        return null;
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 22.     parkjunohng77@gmail.com         최초 작성
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
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 22.     parkjunohng77@gmail.com         최초 작성
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
     * {@link #buildCopier(Class, boolean, Class, boolean, Map)}에서 호출하는 'MethodHandle' 대상으로, 데이터를 이관하는 메소드들의 실행을
     * 담당합니다.<br>
     * <code>LMF가 호출할 루프 본체</code>로써 실제 데이터 형변환을 실행합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 4.      parkjunohng77@gmail.com         최초 작성
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
     * 입력데이터 타입에서 정의된 메소드 중에서 {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로
     * 제공합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 12. 08.     parkjunohng77@gmail.com         최초 작성
     * 2021. 11. 22.        parkjunohng77@gmail.com      Map&lt;String, Function&lt;Object, Object&gt;&gt; 추가
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

        BiConsumer<Object, Object> copier = COPIER_CACHE.computeIfAbsent(key,
                k -> buildCopier(src.getClass(), lookupSrcSuper, target.getClass(), lookupTargetSuper, checkConvertersOrDefault(converters)));
        copier.accept(target, src);

        return target;
    }

    private static Class<?> wrap(Class<?> c) {
        if (!c.isPrimitive())
            return c;
        if (c == int.class)
            return Integer.class;
        if (c == long.class)
            return Long.class;
        if (c == double.class)
            return Double.class;
        if (c == float.class)
            return Float.class;
        if (c == boolean.class)
            return Boolean.class;
        if (c == byte.class)
            return Byte.class;
        if (c == short.class)
            return Short.class;
        if (c == char.class)
            return Character.class;
        return c;
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

        /** '배열, {@link Collection}, {@link Map}' (이하 Container) 데이터 변환 여부 */
        final boolean deepConvert;
        final ContainerKind kind;
        /** addXXX(E) (1 param) */
        final boolean adderStyle;
        /** putXxx(K,V) (2 params) */
        final boolean putStyle;

        /**
         * <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜      | 작성자   |   내용
         * ------------------------------------------
         * 2025. 9. 4.      parkjunohng77@gmail.com         최초 작성
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
         * @param deepConvert
         *            '배열, {@link Collection}, {@link Map}' (이하 Container) 데이터 변환 여부
         * @param containerKind
         *            'container' 유형
         * @param addStyle
         *            'container' 데이터 추가 'addXXX' 스타일
         * @param putStyle
         *            'container' 데이터 추가 'putXXX' 스타일
         * @param converterOrIdentity
         *            데이터 형변환 함수.
         * @since 2025. 9. 4.
         * @version 2.1.0
         * @author Park Jun-Hong (parkjunhong77@gmail.com)
         */
        public StepPlan(String property, Method getter, Method setter, Function<?, ?> converterOrIdentity) {
            this(property, getter, setter, converterOrIdentity, false, null, false, false);
        }

        /**
         * <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜      | 작성자   |   내용
         * ------------------------------------------
         * 2025. 9. 4.      parkjunohng77@gmail.com         최초 작성
         * 2025. 9. 5.      parkjunhong77@gmail.com     deepConvert, containerKind, addStyple, putStyle 추가
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
         * @param converterOrIdentity
         *            데이터 형변환 함수.
         * @param deepConvert
         *            '배열, {@link Collection}, {@link Map}' (이하 Container) 데이터 변환 여부
         * @param containerKind
         *            'container' 유형
         * @param addStyle
         *            'container' 데이터 추가 'addXXX' 스타일
         * @param putStyle
         *            'container' 데이터 추가 'putXXX' 스타일
         * @since 2025. 9. 5.
         * @version 2.1.0
         * @author Park Jun-Hong (parkjunhong77@gmail.com)
         */
        public StepPlan(String property, Method getter, Method setter, Function<?, ?> converterOrIdentity, boolean deepConvert, ContainerKind containerKind, boolean addStyle,
                boolean putStyle) {
            this.property = property;
            this.getter = getter;
            this.setter = setter;
            this.converter = converterOrIdentity != null ? converterOrIdentity : IDENTITY_CONVERT;
            this.deepConvert = deepConvert;
            this.kind = containerKind;
            this.adderStyle = addStyle;
            this.putStyle = putStyle;
        }

        static enum ContainerKind {
            SCALAR, ARRAY, COLLECTION, MAP
        }
    }
}
