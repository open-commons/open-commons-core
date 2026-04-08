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

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import open.commons.core.annotation.Getter;
import open.commons.core.annotation.Information;
import open.commons.core.annotation.Setter;
import open.commons.core.exception.CreateInstanceFailedException;
import open.commons.core.stream.ClassSpliterator;

/**
 * Object 타입의 데이터 처리를 지원하는 유틸리티 클래스.
 * 
 * @since 2018. 1. 31.
 */
public class ObjectUtils {

    @SuppressWarnings("null")
    static final Logger LOGGER = LoggerFactory.getLogger(ObjectUtils.class);

    private static final Function<Object, Short> shortFunction = o -> Short.parseShort(o.toString());
    private static final Function<Object, Byte> byteFunction = o -> Byte.parseByte(o.toString());
    private static final Function<Object, Integer> intFunction = o -> Integer.parseInt(o.toString());
    private static final Function<Object, Long> longFunction = o -> Long.parseLong(o.toString());
    private static final Function<Object, Float> floatFunction = o -> Float.parseFloat(o.toString());
    private static final Function<Object, Double> doubleFunction = o -> Double.parseDouble(o.toString());
    private static final Function<Object, Boolean> booleanFunction = o -> Boolean.parseBoolean(o.toString());
    private static final Function<Object, String> strFunction = o -> o.toString();

    /**
     * Primitive classes
     *
     * @since 2020. 12. 22.
     * @version 1.8.0
     */
    @SuppressWarnings("null")
    private static final Set<Class<?>> PRIMITIVE_CLASSES = Set.of(boolean.class //
            , byte.class //
            , char.class //
            , double.class //
            , float.class //
            , int.class //
            , long.class //
            , short.class //
    );

    /**
     * Wrapper classes
     *
     * @since 2020. 12. 22.
     * @version 1.8.0
     */
    @SuppressWarnings("null")
    private static final Set<Class<?>> WRAPPER_CLASSES = Set.of( //
            Boolean.class //
            , Byte.class //
            , Character.class //
            , Double.class //
            , Float.class //
            , Integer.class //
            , Long.class //
            , Short.class //
    );

    // 타입별 Setter 메타데이터 및 고속 실행기(MethodHandle) 전역 캐시
    private static final Map<Class<?>, List<SetterInfo>> SETTER_CACHE = new ConcurrentHashMap<>();

    // Prevent to create a new instance.
    private ObjectUtils() {
    }

    /**
     * 검사 대상 타입이 기준 타입과 호환 가능한지 여부를 제공합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2019. 9. 3.     parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param targetType
     *            검사 대상 타입
     * @param standardType
     *            기준 타입
     *
     * @return 호환 가능 여부
     *
     * @since 2019. 9. 3.
     * @version 1.8.0
     */
    public static boolean checkType(Class<?> targetType, Class<?> standardType) {
        ObjectUtils.requireNonNulls(targetType, standardType);

        return switch (standardType.getName()) {
            case "boolean", "java.lang.Boolean" -> checkType(targetType, boolean.class, Boolean.class);
            case "byte", "java.lang.Byte" -> checkType(targetType, byte.class, Byte.class);
            case "char", "java.lang.Character" -> checkType(targetType, char.class, Character.class);
            case "short", "java.lang.Short" -> checkType(targetType, short.class, Short.class);
            case "int", "java.lang.Integer" -> checkType(targetType, int.class, Integer.class);
            case "long", "java.lang.Long" -> checkType(targetType, long.class, Long.class);
            case "float", "java.lang.Float" -> checkType(targetType, float.class, Float.class);
            case "double", "java.lang.Double" -> checkType(targetType, double.class, Double.class);
            default -> standardType.isAssignableFrom(targetType);
        };
    }

    /**
     * 검사 대상 타입이 기준 타입들과 호환되는지 여부를 제공합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2019. 9. 3.     parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param targetType
     *            검사 대상 타입
     * @param standardTypes
     *            기준 타입 목록
     *
     * @return 호환 가능 여부
     *
     * @since 2019. 9. 3.
     * @version 1.8.0
     */
    // 아래 내용에 적용됨.
    // - ObjectUtils.requireNonNulls((Object[]) standardTypes);
    // [PATCH] 배열 공변성/가변성에 대한 IDE 분석기의 오탐 우회
    // [TODO] 향후 IDE의 배열 데이터 흐름 분석이 고도화되거나 JSpecify가 완벽히 지원되면 '제거'
    @SuppressWarnings("null")
    private static boolean checkType(Class<?> targetType, Class<?>... standardTypes) {
        Objects.requireNonNull(targetType);
        ObjectUtils.requireNonNulls((Object[]) standardTypes);

        for (Class<?> standard : standardTypes) {
            if (standard.isAssignableFrom(targetType)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 주어진 배열에 {@code null}이 포함되어 있는지 여부를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 12. 3.		parkjunhong77@gmail.com			최초 작성
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
     */
    public static boolean containsNull(boolean visitValue, Collection<@Nullable ?> data) {
        Objects.requireNonNull(data);

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
     * 주어진 배열에 {@code null}이 포함되어 있는지 여부를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 12. 3.		parkjunhong77@gmail.com			최초 작성
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
     */
    // 아래 내용에 적용됨.
    // - Arays.asList(...)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static boolean containsNull(boolean visitValue, Object... array) {
        Objects.requireNonNull(array);

        return containsNull(visitValue, Arrays.asList(array));
    }

    /**
     * 주어진 배열에 {@code null}이 포함되어 있는지 여부를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 12. 3.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     * 
     * @param data
     *            배열
     *
     * @return
     *
     * @since 2021. 12. 3.
     * @version 1.8.0
     */
    public static boolean containsNull(Collection<@Nullable ?> data) {
        return containsNull(false, data);
    }

    /**
     * 주어진 배열에 {@code null}이 포함되어 있는지 여부를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 12. 3.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     * 
     * @param array
     *            배열
     *
     * @return
     *
     * @since 2021. 12. 3.
     * @version 1.8.0
     */
    public static boolean containsNull(@Nullable Object... array) {
        return containsNull(false, array);
    }

    /**
     * 주어진 값이 {@code null}인 경우, 기본값({@code sup.get()})을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 11. 24.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <T>
     * @param <R>
     * @param o
     *            사용하려는 데이터를 포함하고 있는 객체
     * @param manipulator
     *            사용하려는 데이터를 제공하는 함수
     * @param defaultValue
     *            기본값
     * 
     * @return
     *
     * @since 2025. 11. 24.
     * @version 2.1.0
     */
    public static <T extends @Nullable Object, R extends @Nullable Object> R getOrDefault(T o, Function<T, R> manipulator, R defaultValue) {
        Objects.requireNonNull(manipulator);

        return o != null ? manipulator.apply(o) : defaultValue;
    }

    /**
     * 주어진 값이 {@code null}인 경우, 기본값({@code sup.get()})을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 11. 24.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <T>
     * @param <R>
     * @param o
     *            사용하려는 데이터를 포함하고 있는 객체
     * @param manipulator
     *            사용하려는 데이터를 제공하는 함수
     * @param sup
     *            기본값을 제공하는 함수.
     * 
     * @return
     *
     * @since 2025. 11. 24.
     * @version 2.1.0
     */
    public static <T extends @Nullable Object, R extends @Nullable Object> R getOrDefault(T o, Function<T, R> manipulator, Supplier<R> sup) {
        ObjectUtils.requireNonNulls(manipulator, sup);

        return o != null ? manipulator.apply(o) : sup.get();
    }

    /**
     * 주어진 값이 {@code null}인 경우, 기본값({@code sup.get()})을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 11. 24.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <T>
     * @param o
     *            사용하려는 데이터.
     * @param sup
     *            기본값을 제공하는 함수.
     * 
     * @return
     *
     * @since 2025. 11. 24.
     * @version 2.1.0
     */
    public static <T extends @Nullable Object> T getOrDefault(T o, Supplier<T> sup) {
        Objects.requireNonNull(sup);

        return o != null ? o : sup.get();
    }

    /**
     * 주어진 값이 {@code null}인 경우, 기본값({@code defaultValue})을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 11. 24.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <T>
     * @param o
     *            사용하려는 데이터.
     * @param defaultValue
     *            기본값
     * @return
     *
     * @since 2025. 11. 24.
     * @version 2.1.0
     */
    public static <T extends @Nullable Object> T getOrDefault(T o, T defaultValue) {
        return o != null ? o : defaultValue;
    }

    /**
     * 주어진 타입 및 조건에 맞는 데이터 변환 함수를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 12. 6.     parkjunhong77@gmail.com         최초 작성
     * 2026. 3. 10.     parkjunhong77@gmail.com         (3.0.0) JDK 25 마이그레이션 및 캐시적용
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
     * @param targetClass
     *            데이터를 전달받은 객체 타입.
     * @param lookupTargetSuper
     *            대상 객체 상위 인터페이스/클래스 확장 여부
     * 
     * @return
     *
     * @since 2021. 12. 6.
     * @version 1.8.0
     */
    public static <S, T> Function<S, T> getTransformer(Class<S> srcType, boolean lookupSrcSuper, Class<T> targetClass, boolean lookupTargetSuper) throws IllegalArgumentException {
        return src -> {
            try {
                BiConsumer<S, T> copier = ObjectTransformer.getTransformer(srcType, lookupSrcSuper, targetClass, lookupTargetSuper);
                T target = targetClass.getDeclaredConstructor().newInstance();
                copier.accept(src, target);

                return target;
            } catch (Exception e) {
                throw ExceptionUtils.newException(RuntimeException.class, e, "데이터를 변환하는 도중 오류가 발생하였습니다.");
            }
        };
    }

    /**
     * 주어진 타입 및 조건에 맞는 데이터 변환 함수를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 12. 6.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <S>
     *            입력 데이터 타입 정의.
     * @param <T>
     *            신규 데이터 타입 정의.
     * @param srcType
     *            입력 데이터 타입
     * @param targetClass
     *            데이터를 전달받은 객체.
     * @return
     *
     * @since 2021. 12. 6.
     * @version 1.8.0
     */
    public static <S, T> Function<S, T> getTransformer(Class<S> srcType, Class<T> targetClass) throws NullPointerException {
        return getTransformer(srcType, false, targetClass, false);
    }

    /**
     * 주어진 타입 및 조건에 맞는 데이터 변환 함수를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 12. 6.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <S>
     *            입력 데이터 타입 정의.
     * @param <T>
     *            신규 데이터 타입 정의.
     * @param srcCol
     *            입력 데이터
     * @param lookupSrcSuper
     *            입력 데이터 클래스 상위 인터페이스/클래스 확장 여부
     * @param targetClass
     *            데이터를 전달받은 객체 타입
     * @param lookupTargetSuper
     *            대상 객체 상위 인터페이스/클래스 확장 여부
     * 
     * @return
     * @throws IllegalArgumentException
     *             {@code srcCol}이 비어 있는 경우 발생.
     *
     * @since 2021. 12. 6.
     * @version 1.8.0
     */
    public static <S, T> Function<S, T> getTransformer(Collection<S> srcCol, boolean lookupSrcSuper, Class<T> targetClass, boolean lookupTargetSuper) {
        Objects.requireNonNull(srcCol);
        Objects.requireNonNull(targetClass);

        AssertUtils2.notEmpty("'source' object MUST NOT be empty !!!", srcCol, IllegalArgumentException.class);

        @SuppressWarnings("unchecked")
        Optional<Class<S>> fromInstance = srcCol.stream() //
                .filter(Objects::nonNull) //
                .findFirst() //
                .map(e -> (Class<S>) e.getClass());

        return getTransformer(Objects.requireNonNull(fromInstance.get()), lookupSrcSuper, targetClass, lookupTargetSuper);
    }

    /**
     * 주어진 타입 및 조건에 맞는 데이터 변환 함수를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 12. 6.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <S>
     *            입력 데이터 타입 정의.
     * @param <T>
     *            신규 데이터 타입 정의.
     * @param srcCol
     *            입력 데이터
     * @param targetClass
     *            데이터를 전달받은 객체.
     * 
     * @return
     * @throws IllegalArgumentException
     *             {@code srcCol}이 비어 있는 경우 발생.
     *
     * @since 2021. 12. 6.
     * @version 1.8.0
     */
    public static <S, T> Function<S, T> getTransformer(Collection<S> srcCol, Class<T> targetClass) {
        return getTransformer(srcCol, false, targetClass, false);
    }

    /**
     * 주어진 타입 및 조건에 맞는 데이터 변환 함수를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 12. 6.     parkjunhong77@gmail.com         최초 작성
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
     * @param targetClass
     *            데이터를 전달받은 객체.
     * @param lookupTargetSuper
     *            대상 객체 상위 인터페이스/클래스 확장 여부
     * 
     * @return
     *
     * @since 2021. 12. 6.
     * @version 1.8.0
     */
    @SuppressWarnings("unchecked")
    public static <S, T> Function<S, T> getTransformer(S src, boolean lookupSrcSuper, Class<T> targetClass, boolean lookupTargetSuper) throws NullPointerException {
        Objects.requireNonNull(src, "'source' object MUST NOT be null !!!");
        Objects.requireNonNull(targetClass, "'target' type MUST NOT be null !!!");

        return getTransformer((Class<S>) src.getClass(), lookupSrcSuper, targetClass, lookupTargetSuper);
    }

    /**
     * 주어진 타입 및 조건에 맞는 데이터 변환 함수를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 1. 26.		parkjunhong77@gmail.com			최초 작성
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
     * @param targetClass
     *            데이터를 전달받은 객체 타입.
     * @param lookupTargetSuper
     *            대상 객체 상위 인터페이스/클래스 확장 여부
     * 
     * @return
     *
     * @since 2022. 1. 26.
     * @version 1.8.0
     */
    @SuppressWarnings("unchecked")
    public static <S, T> Function<S, T> getTransformer(S src, boolean lookupSrcSuper, T target, boolean lookupTargetSuper) throws IllegalArgumentException {
        Objects.requireNonNull(src, "'source' object MUST NOT be null !!!");
        Objects.requireNonNull(target, "'target' object MUST NOT be null !!!");

        return getTransformer((Class<S>) src.getClass(), lookupSrcSuper, (Class<T>) target.getClass(), lookupTargetSuper);
    }

    /**
     * 주어진 타입 및 조건에 맞는 데이터 변환 함수를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 12. 6.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <S>
     *            입력 데이터 타입 정의.
     * @param <T>
     *            신규 데이터 타입 정의.
     * @param src
     *            입력 데이터
     * @param targetClass
     *            데이터를 전달받은 객체 타입.
     * 
     * @return
     *
     * @since 2021. 12. 6.
     * @version 1.8.0
     */
    public static <S, T> Function<S, T> getTransformer(S src, Class<T> targetClass) {
        return getTransformer(src, false, targetClass, false);
    }

    /**
     * 주어진 타입 및 조건에 맞는 데이터 변환 함수를 제공합니다. <br>
     * 
     * <pre>
     *      * 
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 1. 26.		parkjunhong77@gmail.com			최초 작성
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
     * @since 2022. 1. 26.
     * @version 1.8.0
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
     * 2020. 12. 22.		parkjunhong77@gmail.com			최초 작성
     * 2022. 02. 07.        parkjunhong77@gmail.com     버그 수정.
     * </pre>
     *
     * @param type
     * 
     * @return
     *
     * @since 2020. 12. 22.
     * @version 1.8.0
     */
    public static boolean isPrimitive(Class<?> type) {
        Objects.requireNonNull(type);

        return PRIMITIVE_CLASSES.contains(type);
    }

    /**
     * 주어진 타입이 primitive, Wrapper 중에 1개라도 타입인지 여부를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 3. 22.		parkjunhong77@gmail.com			최초 작성
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
     * 2020. 12. 22.		parkjunhong77@gmail.com			최초 작성
     * 2022. 02. 07.        parkjunhong77@gmail.com     버그 수정.
     * </pre>
     *
     * @param type
     * 
     * @return
     *
     * @since 2020. 12. 22.
     * @version 1.8.0
     */
    public static boolean isWrapper(Class<?> type) {
        Objects.requireNonNull(type);

        return WRAPPER_CLASSES.contains(type);
    }

    /**
     * 객체가 wrapper class 타입인지 여부를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 12. 22.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param object
     * 
     * @return
     *
     * @since 2020. 12. 22.
     * @version 1.8.0
     */
    public static boolean isWrapper(Object object) {
        Objects.requireNonNull(object);

        return WRAPPER_CLASSES.contains(object.getClass());
    }

    /**
     * {@link Setter} 어노테이션이 기술된 메소드를 이용하여 {@link Map}으로부터 데이터를 읽어 새로운 객체를 생성합니다. <br>
     * *
     * 
     * <pre>
     * [개정이력]
     * 날짜          | 작성자   |   내용
     * ------------------------------------------
     * 2019. 9. 3.      parkjunhong77@gmail.com         최초 작성
     * 2026. 3. 11.     parkjunhong77@gmail.com         JDK 25 마이그레이션: MethodHandle 기반 캐싱 및 모듈 시스템 대응
     * </pre>
     *
     * @param <T>
     *            반환할 객체의 타입
     * @param type
     *            데이터 타입
     * @param map
     *            데이터를 가지고 있는 {@link Map} * @return 생성된 객체
     *
     * @since 2019. 9. 3.
     * @version 2.0.0
     * 
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws NoSuchMethodException
     *             생성자를 찾을 수 없는 경우
     * 
     * @see Setter
     */
    public static <T> T load(Class<T> type, Map<String, Object> map)
            throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
        Objects.requireNonNull(type);

        // [캐싱] 클래스에 대한 Setter 정보를 최초 1회만 스캔하여 저장합니다.
        // 아래 내용에 적용됨.
        // - Method.getParameterTypes()
        // - MethodHandles.Lookup.unreflect(...)
        // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
        // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
        @SuppressWarnings("null")
        List<SetterInfo> setters = SETTER_CACHE.computeIfAbsent(type, k -> {
            List<SetterInfo> list = new ArrayList<>();
            MethodHandles.Lookup lookup = MethodHandles.lookup();

            for (Method m : AnnotationUtils.getAnnotatedMethodsAll(k, Setter.class)) {
                // [안전성] 파라미터가 1개가 아닌 잘못된 Setter는 안전하게 스킵
                if (m.getParameterCount() != 1) {
                    continue;
                }

                // [보안/동시성] 가변 상태(setAccessible)를 없애고 JDK 모듈 시스템에 안전하게 접근 권한 획득
                m.trySetAccessible();

                Setter setter = m.getAnnotation(Setter.class);
                String name = setter.name();
                @NonNull
                Class<?> paramType = m.getParameterTypes()[0];

                try {
                    // Method.invoke 보다 압도적으로 빠른 MethodHandle로 변환하여 저장
                    MethodHandle handle = lookup.unreflect(m);
                    list.add(new SetterInfo(name, paramType, handle));
                } catch (IllegalAccessException e) {
                    // 권한 획득 불가 시 로깅 후 스킵 (또는 예외 정책에 따라 수정 가능)
                }
            }
            return List.copyOf(list);
        });

        // JDK 9+ 표준 방식의 객체 생성자 호출
        T obj = type.getDeclaredConstructor().newInstance();

        // [실행] 리플렉션 탐색 없이 캐시된 정보만으로 고속 매핑 수행
        for (SetterInfo info : setters) {
            Object param = map.get(info.name());
            if (param == null) {
                continue;
            }

            // 기존의 타입 체크 로직 유지 (checkType 메소드 존재 가정)
            if (!checkType(param.getClass(), info.paramType())) {
                throw new IllegalArgumentException(String.format("Required: %s, Input.type: %s, Input.value: %s", info.paramType(), param.getClass(), param));
            }

            try {
                // Method.invoke의 검증 오버헤드를 건너뛰고 네이티브 수준으로 실행
                info.handle().invoke(obj, param);
            } catch (Throwable t) {
                throw new InvocationTargetException(t, "Setter invoke 실패: " + info.name());
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
     * 2021. 12. 3.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param forceToPrimitive
     * @param objects
     * 
     * @return
     *
     * @since 2021. 12. 3.
     * @version 1.8.0
     */
    // 아래 내용에 적용됨.
    // - Stream.toArray()
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static Class<?>[] readClasses(boolean forceToPrimitive, Object... objects) {
        return readClassesAsStream(forceToPrimitive, objects).toArray(Class<?>[]::new);
    }

    /**
     * 주어진 객체의 {@link Class} 정보를 배열로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 12. 3.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param objects
     * 
     * @return
     *
     * @since 2021. 12. 3.
     * @version 1.8.0
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
     * 2021. 12. 3.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param objects
     * 
     * @return
     *
     * @since 2021. 12. 3.
     * @version 1.8.0
     */
    // 아래 내용에 적용됨.
    // - Stream.collect(...)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static List<Class<?>> readClassesAsList(boolean forceToPrimitive, Object... objects) {
        return readClassesAsStream(forceToPrimitive, objects).collect(Collectors.toList());
    }

    /**
     * 주어진 객체의 {@link Class} 정보를 {@link List} 로 제공합니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 12. 3.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param objects
     * 
     * @return
     *
     * @since 2021. 12. 3.
     * @version 1.8.0
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
     * 2021. 12. 3.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param objects
     * 
     * @return
     *
     * @since 2021. 12. 3.
     * @version 1.8.0
     */
    // 아래 내용에 적용됨.
    // - ObjectUtils.requireNonNulls((Object[]) standardTypes);
    // [PATCH] 배열 공변성/가변성에 대한 IDE 분석기의 오탐 우회
    // [TODO] 향후 IDE의 배열 데이터 흐름 분석이 고도화되거나 JSpecify가 완벽히 지원되면 '제거'
    @SuppressWarnings("null")
    public static Stream<Class<?>> readClassesAsStream(boolean forceToPrimitive, Object... objects) {
        ObjectUtils.requireNonNulls((Object[]) objects);

        return StreamSupport.stream(new ClassSpliterator(forceToPrimitive, objects), false);
    }

    /**
     * 주어진 객체의 {@link Class}정보를 {@link Stream}으로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 12. 3.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param objects
     * 
     * @return
     *
     * @since 2021. 12. 3.
     * @version 1.8.0
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
     * 2021. 11. 3.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param o
     * @return
     *
     * @since 2021. 11. 3.
     * @version 1.8.0
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
                    try {
                        f.trySetAccessible();
                        Object v = String.format(fmt, f.getName(), f.get(o));
                        value = v != null ? v.toString() : null;
                    } catch (IllegalArgumentException | IllegalAccessException e) {
                        value = e.getMessage();
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
     * 2021. 12. 2.     parkjunhong77@gmail.com         최초 작성
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
     * 
     * @since 2021. 12. 2.
     * @see ObjectTransformer#registerPropertyConverter(Class, Class, String, Class, Class, Function)
     */
    public static <SF, TF> void registerFieldConverter(Class<SF> srcFieldClass, Class<TF> targetFieldClass, Function<SF, TF> converter) throws NullPointerException {
        ObjectTransformer.registerPropertyConverter(null, srcFieldClass, null, null, targetFieldClass, converter);
    }

    /**
     * 클래스 변수 데이터 변환에 필요한 함수를 등록합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 12. 2.     parkjunhong77@gmail.com         최초 작성
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
     *
     * @since 2021. 12. 2.
     * 
     * @see ObjectTransformer#registerPropertyConverter(Class, Class, String, Class, Class, Function, Function)
     */
    public static <SF, TF> void registerFieldConverter(Class<SF> srcFieldClass, Class<TF> targetFieldClass, Function<SF, TF> srcToTarget, Function<TF, SF> targetToSrc)
            throws NullPointerException {
        ObjectTransformer.registerPropertyConverter(null, srcFieldClass, null, null, targetFieldClass, srcToTarget, targetToSrc);
    }

    /**
     * 파라미터에 {@code null}이 있는지 검증합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2026. 3. 16.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param objects
     *            검증 대상
     *
     * @since 2026. 3. 16.
     * @version 3.0.0
     */
    public static Object[] requireNonNulls(@Nullable Object @Nullable... objects) {
        return requireNonNullsWithMessage("객체 배열 자체가 null일 수 없습니다.", objects);
    }

    /**
     * 파라미터에 {@code null}이 있는지 검증합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2026. 3. 16.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param message
     *            오류 메시지
     * @param objects
     *            검증 대상
     *
     * @since 2026. 3. 16.
     * @version 3.0.0
     */
    public static Object[] requireNonNullsWithMessage(@Nullable String message, @Nullable Object @Nullable... objects) {
        if (message == null) {
            message = "객체 배열 자체가 null일 수 없습니다.";
        }

        Objects.requireNonNull(objects, message);

        // Stream 대신 성능이 압도적으로 좋은 일반 for 루프 사용
        for (int i = 0; i < objects.length; i++) {
            if (objects[i] == null) {
                throw new NullPointerException((i + 1) + "번째 인자가 null입니다.");
            }
        }

        return objects;
    }

    /**
     * 전달받은 데이터를 변환(E &rarr; V)한 후, 새로운 {@link Collection}(R)로 제공합니다.
     * 
     * <p>
     * <font color="red"><b>* 데이터(E)가 <b>{@code null}</b>인 경우 '전처리 과정'에서 제외시키므로, 데이터(E)를 처리하는 함수 객체는
     * <b>{@code null}</b>을 처리하지 않아도 됩니다.</b></font>
     * </p>
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 30.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <E>
     *            기존 데이터 유형 (Nullable). (스트림 내부의 {@code null} 요소는 전처리 과정에서 안전하게 제외됩니다)
     * @param <V>
     *            새로운 데이터 유형 (Nullable). 단, {@code collectionSupplier}가 제공하는 {@link Collection} 구현체가 {@code null}을 허용해야
     *            합니다.
     * @param <R>
     *            변환 후 데이터가 저장될 {@link Collection} 유형
     * @param objects
     *            원본 데이터 컬렉션
     * @param transformer
     *            데이터 변환 함수 (E &rarr; V). 반환값으로 {@code null}을 제공할 수 있으나, 이 경우 결과 컬렉션이 {@code null}을 허용해야 합니다.
     * @param collectionSupplier
     *            결과를 담을 새로운 {@link Collection} 객체 제공 함수
     *
     * @return 변환된 데이터가 저장된 새로운 컬렉션
     *
     * @throws UnsupportedOperationException
     *             지원하지 않는 컬렉션 연산 시 발생.
     * @throws InstantiationException
     *             컬렉션 객체 인스턴스화 실패 시 발생.
     * @throws IllegalAccessException
     *             컬렉션 객체 생성 접근 권한 위반 시 발생.
     *
     * @since 2025. 8. 30.
     * @version 2.1.0
     */
    private static <E extends @Nullable Object, V extends @Nullable Object, R extends Collection<V>> //
            R to(Collection<E> objects, Function<E, V> transformer, Supplier<R> collectionSupplier) {
        AssertUtils2.notNulls(objects, transformer, collectionSupplier);

        if (objects.size() < 1) {
            return collectionSupplier.get();
        }

        return objects.stream() //
                .filter(Objects::nonNull) //
                .map(transformer) //
                .collect(Collectors.toCollection(collectionSupplier));
    }

    /**
     * Object Type {@link Collection}을 Boolean Type {@link List}으로 변환한여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 4.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param objects
     *            변환할 데이터
     * 
     * @return
     *
     * @since 2025. 9. 4.
     * @version 2.1.0
     */
    public static List<Boolean> toBoolean(Collection<Object> objects) {
        return toBoolean(objects, booleanFunction);
    }

    /**
     * {@link Object} Type {@link Collection}을 {@link Boolean} Type {@link List}으로 변환한여 제공합니다. <br>
     * 
     * <p>
     * <font color="red"><b>* 데이터(Object)가 <b>{@code null}</b>인 경우 '전처리 과정'에서 제외시키므로, 데이터(Object)를 처리하는 함수 객체는
     * <b>{@code null}</b>을 처리하지 않아도 됩니다.</b></font>
     * </p>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 4.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param objects
     *            변환할 데이터
     * @param transfomer
     *            데이터 변환 함수 (Object => Boolean)
     * 
     * @return
     *
     * @since 2025. 9. 4.
     * @version 2.1.0
     */
    public static List<Boolean> toBoolean(Collection<Object> objects, Function<Object, Boolean> transformer) {
        return toBoolean(objects, transformer, ArrayList::new);
    }

    /**
     * {@link Object} Type {@link Collection}을 {@link Boolean} Type {@link Collection}으로 변환한여 제공합니다. <br>
     * 
     * <p>
     * <font color="red"><b>* 데이터(Object)가 <b>{@code null}</b>인 경우 '전처리 과정'에서 제외시키므로, 데이터(Object)를 처리하는 함수 객체는
     * <b>{@code null}</b>을 처리하지 않아도 됩니다.</b></font>
     * </p>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 4.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <R>
     *            새로운 {@link Collection} 유형.
     * @param objects
     *            변환할 데이터
     * @param transfomer
     *            데이터 변환 함수 (Object => Boolean). 반환값으로 {@code null}을 제공할 수 있으나, 이 경우 결과 컬렉션이 {@code null}을 허용해야 합니다.
     * @param collectionSupplier
     *            {@link Collection} 객체 제공 함수.
     * @return
     *
     * @since 2025. 9. 4.
     * @version 2.1.0
     */
    // 아래 내용에 적용됨.
    // - to(objects, transformer, collectionSupplier)
    // [PATCH] [IDE-Null] Eclipse JDT 분석기의 제네릭 & @NullMarked 치환 해석 오류 우회
    // [TODO] 향후 Eclipse IDE 정적 분석기가 JSpecify 제네릭 치환을 완벽히 지원하면 '제거'
    @SuppressWarnings("null")
    public static <R extends Collection<Boolean>> R toBoolean(Collection<Object> objects, Function<Object, Boolean> transformer, Supplier<R> collectionSupplier) {
        return to(objects, transformer, collectionSupplier);
    }

    /**
     * {@link Object} Type {@link Collection}을 {@link Boolean} Type {@link Collection}으로 변환한여 제공합니다. <br>
     * 
     * <p>
     * <font color="red"><b>* 데이터(Object)가 <b>{@code null}</b>인 경우 '전처리 과정'에서 제외시키므로, 데이터(Object)를 처리하는 함수 객체는
     * <b>{@code null}</b>을 처리하지 않아도 됩니다.</b></font>
     * </p>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 4.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <R>
     *            새로운 {@link Collection} 유형.
     * @param objects
     *            변환할 데이터
     * @param collectionSupplier
     *            {@link Collection} 객체 제공 함수.
     * 
     * @return
     *
     * @since 2025. 9. 4.
     * @version 2.1.0
     */
    public static <R extends Collection<Boolean>> R toBoolean(Collection<Object> objects, Supplier<R> collectionSupplier) {
        return toBoolean(objects, booleanFunction, collectionSupplier);
    }

    /**
     * Object Type {@link Collection}을 Byte Type {@link List}으로 변환한여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 4.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param objects
     *            변환할 데이터
     * 
     * @return
     *
     * @since 2025. 9. 4.
     * @version 2.1.0
     */
    public static List<Byte> toByte(Collection<Object> objects) {
        return toByte(objects, byteFunction);
    }

    /**
     * {@link Object} Type {@link Collection}을 {@link Byte} Type {@link List}으로 변환한여 제공합니다. <br>
     * 
     * <p>
     * <font color="red"><b>* 데이터(Object)가 <b>{@code null}</b>인 경우 '전처리 과정'에서 제외시키므로, 데이터(Object)를 처리하는 함수 객체는
     * <b>{@code null}</b>을 처리하지 않아도 됩니다.</b></font>
     * </p>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 4.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param objects
     *            변환할 데이터
     * @param transfomer
     *            데이터 변환 함수 (Object => Byte)
     * 
     * @return
     *
     * @since 2025. 9. 4.
     * @version 2.1.0
     */
    public static List<Byte> toByte(Collection<Object> objects, Function<Object, Byte> transformer) {
        return toByte(objects, transformer, ArrayList::new);
    }

    /**
     * {@link Object} Type {@link Collection}을 {@link Byte} Type {@link Collection}으로 변환한여 제공합니다. <br>
     * 
     * <p>
     * <font color="red"><b>* 데이터(Object)가 <b>{@code null}</b>인 경우 '전처리 과정'에서 제외시키므로, 데이터(Object)를 처리하는 함수 객체는
     * <b>{@code null}</b>을 처리하지 않아도 됩니다.</b></font>
     * </p>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 4.      parkjunhong77@gmail.com         최초 작성
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
     * 
     * @return
     *
     * @since 2025. 9. 4.
     * @version 2.1.0
     */
    // 아래 내용에 적용됨.
    // - to(objects, transformer, collectionSupplier)
    // [PATCH] [IDE-Null] Eclipse JDT 분석기의 제네릭 & @NullMarked 치환 해석 오류 우회
    // [TODO] 향후 Eclipse IDE 정적 분석기가 JSpecify 제네릭 치환을 완벽히 지원하면 '제거'
    @SuppressWarnings("null")
    public static <R extends Collection<Byte>> R toByte(Collection<Object> objects, Function<Object, Byte> transformer, Supplier<R> collectionSupplier) {
        return to(objects, transformer, collectionSupplier);
    }

    /**
     * {@link Object} Type {@link Collection}을 {@link Byte} Type {@link Collection}으로 변환한여 제공합니다. <br>
     * 
     * <p>
     * <font color="red"><b>* 데이터(Object)가 <b>{@code null}</b>인 경우 '전처리 과정'에서 제외시키므로, 데이터(Object)를 처리하는 함수 객체는
     * <b>{@code null}</b>을 처리하지 않아도 됩니다.</b></font>
     * </p>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 4.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <R>
     *            새로운 {@link Collection} 유형.
     * @param objects
     *            변환할 데이터
     * @param collectionSupplier
     *            {@link Collection} 객체 제공 함수.
     * 
     * @return
     *
     * @since 2025. 9. 4.
     * @version 2.1.0
     */
    public static <R extends Collection<Byte>> R toByte(Collection<Object> objects, Supplier<R> collectionSupplier) {
        return toByte(objects, byteFunction, collectionSupplier);
    }

    /**
     * Object Type {@link Collection}을 Double Type {@link List}으로 변환한여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 4.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param objects
     *            변환할 데이터
     * 
     * @return
     *
     * @since 2025. 9. 4.
     * @version 2.1.0
     */
    public static List<Double> toDouble(Collection<Object> objects) {
        return toDouble(objects, doubleFunction);
    }

    /**
     * {@link Object} Type {@link Collection}을 {@link Double} Type {@link List}으로 변환한여 제공합니다. <br>
     * 
     * <p>
     * <font color="red"><b>* 데이터(Object)가 <b>{@code null}</b>인 경우 '전처리 과정'에서 제외시키므로, 데이터(Object)를 처리하는 함수 객체는
     * <b>{@code null}</b>을 처리하지 않아도 됩니다.</b></font>
     * </p>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 4.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param objects
     *            변환할 데이터
     * @param transfomer
     *            데이터 변환 함수 (Object => Double)
     * 
     * @return
     *
     * @since 2025. 9. 4.
     * @version 2.1.0
     */
    public static List<Double> toDouble(Collection<Object> objects, Function<Object, Double> transformer) {
        return toDouble(objects, transformer, ArrayList::new);
    }

    /**
     * {@link Object} Type {@link Collection}을 {@link Double} Type {@link Collection}으로 변환한여 제공합니다. <br>
     * 
     * <p>
     * <font color="red"><b>* 데이터(Object)가 <b>{@code null}</b>인 경우 '전처리 과정'에서 제외시키므로, 데이터(Object)를 처리하는 함수 객체는
     * <b>{@code null}</b>을 처리하지 않아도 됩니다.</b></font>
     * </p>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 4.      parkjunhong77@gmail.com         최초 작성
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
     * 
     * @return
     *
     * @since 2025. 9. 4.
     * @version 2.1.0
     */
    // 아래 내용에 적용됨.
    // - to(objects, transformer, collectionSupplier)
    // [PATCH] [IDE-Null] Eclipse JDT 분석기의 제네릭 & @NullMarked 치환 해석 오류 우회
    // [TODO] 향후 Eclipse IDE 정적 분석기가 JSpecify 제네릭 치환을 완벽히 지원하면 '제거'
    @SuppressWarnings("null")
    public static <R extends Collection<Double>> R toDouble(Collection<Object> objects, Function<Object, Double> transformer, Supplier<R> collectionSupplier) {
        return to(objects, transformer, collectionSupplier);
    }

    /**
     * {@link Object} Type {@link Collection}을 {@link Double} Type {@link Collection}으로 변환한여 제공합니다. <br>
     * 
     * <p>
     * <font color="red"><b>* 데이터(Object)가 <b>{@code null}</b>인 경우 '전처리 과정'에서 제외시키므로, 데이터(Object)를 처리하는 함수 객체는
     * <b>{@code null}</b>을 처리하지 않아도 됩니다.</b></font>
     * </p>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 4.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <R>
     *            새로운 {@link Collection} 유형.
     * @param objects
     *            변환할 데이터
     * @param collectionSupplier
     *            {@link Collection} 객체 제공 함수.
     * 
     * @return
     *
     * @since 2025. 9. 4.
     * @version 2.1.0
     */
    public static <R extends Collection<Double>> R toDouble(Collection<Object> objects, Supplier<R> collectionSupplier) {
        return toDouble(objects, doubleFunction, collectionSupplier);
    }

    /**
     * Object Type {@link Collection}을 Float Type {@link List}으로 변환한여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 4.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param objects
     *            변환할 데이터
     * 
     * @return
     *
     * @since 2025. 9. 4.
     * @version 2.1.0
     */
    public static List<Float> toFloat(Collection<Object> objects) {
        return toFloat(objects, floatFunction, ArrayList::new);
    }

    /**
     * {@link Object} Type {@link Collection}을 {@link Float} Type {@link List}으로 변환한여 제공합니다. <br>
     * 
     * <p>
     * <font color="red"><b>* 데이터(Object)가 <b>{@code null}</b>인 경우 '전처리 과정'에서 제외시키므로, 데이터(Object)를 처리하는 함수 객체는
     * <b>{@code null}</b>을 처리하지 않아도 됩니다.</b></font>
     * </p>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 4.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param objects
     *            변환할 데이터
     * @param transfomer
     *            데이터 변환 함수 (Object => Float)
     * 
     * @return
     *
     * @since 2025. 9. 4.
     * @version 2.1.0
     */
    public static List<Float> toFloat(Collection<Object> objects, Function<Object, Float> transformer) {
        return toFloat(objects, transformer, ArrayList::new);
    }

    /**
     * {@link Object} Type {@link Collection}을 {@link Float} Type {@link Collection}으로 변환한여 제공합니다. <br>
     * 
     * <p>
     * <font color="red"><b>* 데이터(Object)가 <b>{@code null}</b>인 경우 '전처리 과정'에서 제외시키므로, 데이터(Object)를 처리하는 함수 객체는
     * <b>{@code null}</b>을 처리하지 않아도 됩니다.</b></font>
     * </p>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 4.      parkjunhong77@gmail.com         최초 작성
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
     * 
     * @return
     *
     * @since 2025. 9. 4.
     * @version 2.1.0
     */
    // 아래 내용에 적용됨.
    // - to(objects, transformer, collectionSupplier)
    // [PATCH] [IDE-Null] Eclipse JDT 분석기의 제네릭 & @NullMarked 치환 해석 오류 우회
    // [TODO] 향후 Eclipse IDE 정적 분석기가 JSpecify 제네릭 치환을 완벽히 지원하면 '제거'
    @SuppressWarnings("null")
    public static <R extends Collection<Float>> R toFloat(Collection<Object> objects, Function<Object, Float> transformer, Supplier<R> collectionSupplier) {
        return to(objects, transformer, collectionSupplier);
    }

    /**
     * {@link Object} Type {@link Collection}을 {@link Float} Type {@link Collection}으로 변환한여 제공합니다. <br>
     * 
     * <p>
     * <font color="red"><b>* 데이터(Object)가 <b>{@code null}</b>인 경우 '전처리 과정'에서 제외시키므로, 데이터(Object)를 처리하는 함수 객체는
     * <b>{@code null}</b>을 처리하지 않아도 됩니다.</b></font>
     * </p>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 4.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <R>
     *            새로운 {@link Collection} 유형.
     * @param objects
     *            변환할 데이터
     * @param collectionSupplier
     *            {@link Collection} 객체 제공 함수.
     * 
     * @return
     *
     * @since 2025. 9. 4.
     * @version 2.1.0
     */
    public static <R extends Collection<Float>> R toFloat(Collection<Object> objects, Supplier<R> collectionSupplier) {
        return toFloat(objects, floatFunction, collectionSupplier);
    }

    /**
     * Object Type {@link Collection}을 Integer Type {@link List}으로 변환한여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 4.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param objects
     *            변환할 데이터
     * 
     * @return
     *
     * @since 2025. 9. 4.
     * @version 2.1.0
     */
    public static List<Integer> toInteger(Collection<Object> objects) {
        return toInteger(objects, intFunction, ArrayList::new);
    }

    /**
     * {@link Object} Type {@link Collection}을 {@link Integer} Type {@link List}으로 변환한여 제공합니다. <br>
     * 
     * <p>
     * <font color="red"><b>* 데이터(Object)가 <b>{@code null}</b>인 경우 '전처리 과정'에서 제외시키므로, 데이터(Object)를 처리하는 함수 객체는
     * <b>{@code null}</b>을 처리하지 않아도 됩니다.</b></font>
     * </p>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 4.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param objects
     *            변환할 데이터
     * @param transfomer
     *            데이터 변환 함수 (Object => Integer)
     * 
     * @return
     *
     * @since 2025. 9. 4.
     * @version 2.1.0
     */
    public static List<Integer> toInteger(Collection<Object> objects, Function<Object, Integer> transformer) {
        return toInteger(objects, transformer, ArrayList::new);
    }

    /**
     * {@link Object} Type {@link Collection}을 {@link Integer} Type {@link Collection}으로 변환한여 제공합니다. <br>
     * 
     * <p>
     * <font color="red"><b>* 데이터(Object)가 <b>{@code null}</b>인 경우 '전처리 과정'에서 제외시키므로, 데이터(Object)를 처리하는 함수 객체는
     * <b>{@code null}</b>을 처리하지 않아도 됩니다.</b></font>
     * </p>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 4.      parkjunhong77@gmail.com         최초 작성
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
     * 
     * @return
     *
     * @since 2025. 9. 4.
     * @version 2.1.0
     */
    // 아래 내용에 적용됨.
    // - to(objects, transformer, collectionSupplier)
    // [PATCH] [IDE-Null] Eclipse JDT 분석기의 제네릭 & @NullMarked 치환 해석 오류 우회
    // [TODO] 향후 Eclipse IDE 정적 분석기가 JSpecify 제네릭 치환을 완벽히 지원하면 '제거'
    @SuppressWarnings("null")
    public static <R extends Collection<Integer>> R toInteger(Collection<Object> objects, Function<Object, Integer> transformer, Supplier<R> collectionSupplier) {
        return to(objects, transformer, collectionSupplier);
    }

    /**
     * {@link Object} Type {@link Collection}을 {@link Integer} Type {@link Collection}으로 변환한여 제공합니다. <br>
     * 
     * <p>
     * <font color="red"><b>* 데이터(Object)가 <b>{@code null}</b>인 경우 '전처리 과정'에서 제외시키므로, 데이터(Object)를 처리하는 함수 객체는
     * <b>{@code null}</b>을 처리하지 않아도 됩니다.</b></font>
     * </p>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 4.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <R>
     *            새로운 {@link Collection} 유형.
     * @param objects
     *            변환할 데이터
     * @param collectionSupplier
     *            {@link Collection} 객체 제공 함수.
     * 
     * @return
     *
     * @since 2025. 9. 4.
     * @version 2.1.0
     */
    public static <R extends Collection<Integer>> R toInteger(Collection<Object> objects, Supplier<R> collectionSupplier) {
        return toInteger(objects, intFunction, collectionSupplier);
    }

    /**
     * Object Type {@link Collection}을 Long Type {@link List}으로 변환한여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 4.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param objects
     *            변환할 데이터
     * 
     * @return
     *
     * @since 2025. 9. 4.
     * @version 2.1.0
     */
    public static List<Long> toLong(Collection<Object> objects) {
        return toLong(objects, longFunction, ArrayList::new);
    }

    /**
     * {@link Object} Type {@link Collection}을 {@link Long} Type {@link List}으로 변환한여 제공합니다. <br>
     * 
     * <p>
     * <font color="red"><b>* 데이터(Object)가 <b>{@code null}</b>인 경우 '전처리 과정'에서 제외시키므로, 데이터(Object)를 처리하는 함수 객체는
     * <b>{@code null}</b>을 처리하지 않아도 됩니다.</b></font>
     * </p>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 4.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param objects
     *            변환할 데이터
     * @param transfomer
     *            데이터 변환 함수 (Object => Long)
     * 
     * @return
     *
     * @since 2025. 9. 4.
     * @version 2.1.0
     */
    public static List<Long> toLong(Collection<Object> objects, Function<Object, Long> transformer) {
        return toLong(objects, transformer, ArrayList::new);
    }

    /**
     * {@link Object} Type {@link Collection}을 {@link Long} Type {@link Collection}으로 변환한여 제공합니다. <br>
     * 
     * <p>
     * <font color="red"><b>* 데이터(Object)가 <b>{@code null}</b>인 경우 '전처리 과정'에서 제외시키므로, 데이터(Object)를 처리하는 함수 객체는
     * <b>{@code null}</b>을 처리하지 않아도 됩니다.</b></font>
     * </p>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 4.      parkjunhong77@gmail.com         최초 작성
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
     * 
     * @return
     *
     * @since 2025. 9. 4.
     * @version 2.1.0
     */
    // 아래 내용에 적용됨.
    // - to(objects, transformer, collectionSupplier)
    // [PATCH] [IDE-Null] Eclipse JDT 분석기의 제네릭 & @NullMarked 치환 해석 오류 우회
    // [TODO] 향후 Eclipse IDE 정적 분석기가 JSpecify 제네릭 치환을 완벽히 지원하면 '제거'
    @SuppressWarnings("null")
    public static <R extends Collection<Long>> R toLong(Collection<Object> objects, Function<Object, Long> transformer, Supplier<R> collectionSupplier) {
        return to(objects, transformer, collectionSupplier);
    }

    /**
     * {@link Object} Type {@link Collection}을 {@link Long} Type {@link Collection}으로 변환한여 제공합니다. <br>
     * 
     * <p>
     * <font color="red"><b>* 데이터(Object)가 <b>{@code null}</b>인 경우 '전처리 과정'에서 제외시키므로, 데이터(Object)를 처리하는 함수 객체는
     * <b>{@code null}</b>을 처리하지 않아도 됩니다.</b></font>
     * </p>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 4.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <R>
     *            새로운 {@link Collection} 유형.
     * @param objects
     *            변환할 데이터
     * @param collectionSupplier
     *            {@link Collection} 객체 제공 함수.
     * 
     * @return
     *
     * @since 2025. 9. 4.
     * @version 2.1.0
     */
    public static <R extends Collection<Long>> R toLong(Collection<Object> objects, Supplier<R> collectionSupplier) {
        return toLong(objects, longFunction, collectionSupplier);
    }

    /**
     * Object Type {@link Collection}을 Short Type {@link List}으로 변환한여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 4.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param objects
     *            변환할 데이터
     * 
     * @return
     *
     * @since 2025. 9. 4.
     * @version 2.1.0
     */
    public static List<Short> toShort(Collection<Object> objects) {
        return toShort(objects, shortFunction, ArrayList::new);
    }

    /**
     * {@link Object} Type {@link Collection}을 {@link Short} Type {@link List}으로 변환한여 제공합니다. <br>
     * 
     * <p>
     * <font color="red"><b>* 데이터(Object)가 <b>{@code null}</b>인 경우 '전처리 과정'에서 제외시키므로, 데이터(Object)를 처리하는 함수 객체는
     * <b>{@code null}</b>을 처리하지 않아도 됩니다.</b></font>
     * </p>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 4.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param objects
     *            변환할 데이터
     * @param transfomer
     *            데이터 변환 함수 (Object => Short)
     * 
     * @return
     *
     * @since 2025. 9. 4.
     * @version 2.1.0
     */
    public static List<Short> toShort(Collection<Object> objects, Function<Object, Short> transformer) {
        return toShort(objects, transformer, ArrayList::new);
    }

    /**
     * {@link Object} Type {@link Collection}을 {@link Short} Type {@link Collection}으로 변환한여 제공합니다. <br>
     * 
     * <p>
     * <font color="red"><b>* 데이터(Object)가 <b>{@code null}</b>인 경우 '전처리 과정'에서 제외시키므로, 데이터(Object)를 처리하는 함수 객체는
     * <b>{@code null}</b>을 처리하지 않아도 됩니다.</b></font>
     * </p>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 4.      parkjunhong77@gmail.com         최초 작성
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
     * 
     * @return
     *
     * @since 2025. 9. 4.
     * @version 2.1.0
     */
    // 아래 내용에 적용됨.
    // - to(objects, transformer, collectionSupplier)
    // [PATCH] [IDE-Null] Eclipse JDT 분석기의 제네릭 & @NullMarked 치환 해석 오류 우회
    // [TODO] 향후 Eclipse IDE 정적 분석기가 JSpecify 제네릭 치환을 완벽히 지원하면 '제거'
    @SuppressWarnings("null")
    public static <R extends Collection<Short>> R toShort(Collection<Object> objects, Function<Object, Short> transformer, Supplier<R> collectionSupplier) {
        return to(objects, transformer, collectionSupplier);
    }

    /**
     * {@link Object} Type {@link Collection}을 {@link Short} Type {@link Collection}으로 변환한여 제공합니다. <br>
     * 
     * <p>
     * <font color="red"><b>* 데이터(Object)가 <b>{@code null}</b>인 경우 '전처리 과정'에서 제외시키므로, 데이터(Object)를 처리하는 함수 객체는
     * <b>{@code null}</b>을 처리하지 않아도 됩니다.</b></font>
     * </p>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 4.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <R>
     *            새로운 {@link Collection} 유형.
     * @param objects
     *            변환할 데이터
     * @param collectionSupplier
     *            {@link Collection} 객체 제공 함수.
     * 
     * @return
     *
     * @since 2025. 9. 4.
     * @version 2.1.0
     */
    public static <R extends Collection<Short>> R toShort(Collection<Object> objects, Supplier<R> collectionSupplier) {
        return toShort(objects, shortFunction, collectionSupplier);
    }

    /**
     * Object Type {@link Collection}을 String Type {@link List}으로 변환한여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 4.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param objects
     *            변환할 데이터
     * 
     * @return
     *
     * @since 2025. 9. 4.
     * @version 2.1.0
     */
    public static List<String> toString(Collection<Object> objects) {
        return toString(objects, strFunction, ArrayList::new);
    }

    /**
     * {@link Object} Type {@link Collection}을 {@link String} Type {@link List}으로 변환한여 제공합니다. <br>
     * 
     * <p>
     * <font color="red"><b>* 데이터(Object)가 <b>{@code null}</b>인 경우 '전처리 과정'에서 제외시키므로, 데이터(Object)를 처리하는 함수 객체는
     * <b>{@code null}</b>을 처리하지 않아도 됩니다.</b></font>
     * </p>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 4.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param objects
     *            변환할 데이터
     * @param transfomer
     *            데이터 변환 함수 (Object => String)
     * 
     * @return
     *
     * @since 2025. 9. 4.
     * @version 2.1.0
     */
    public static List<String> toString(Collection<Object> objects, Function<Object, String> transformer) {
        return toString(objects, transformer, ArrayList::new);
    }

    /**
     * {@link Object} Type {@link Collection}을 {@link String} Type {@link Collection}으로 변환한여 제공합니다. <br>
     * 
     * <p>
     * <font color="red"><b>* 데이터(Object)가 <b>{@code null}</b>인 경우 '전처리 과정'에서 제외시키므로, 데이터(Object)를 처리하는 함수 객체는
     * <b>{@code null}</b>을 처리하지 않아도 됩니다.</b></font>
     * </p>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 4.      parkjunhong77@gmail.com         최초 작성
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
     * 
     * @return
     *
     * @since 2025. 9. 4.
     * @version 2.1.0
     */
    // 아래 내용에 적용됨.
    // - to(objects, transformer, collectionSupplier)
    // [PATCH] [IDE-Null] Eclipse JDT 분석기의 제네릭 & @NullMarked 치환 해석 오류 우회
    // [TODO] 향후 Eclipse IDE 정적 분석기가 JSpecify 제네릭 치환을 완벽히 지원하면 '제거'
    @SuppressWarnings("null")
    public static <R extends Collection<String>> R toString(Collection<Object> objects, Function<Object, String> transformer, Supplier<R> collectionSupplier) {
        return to(objects, transformer, collectionSupplier);
    }

    /**
     * {@link Object} Type {@link Collection}을 {@link String} Type {@link Collection}으로 변환한여 제공합니다. <br>
     * 
     * <p>
     * <font color="red"><b>* 데이터(Object)가 <b>{@code null}</b>인 경우 '전처리 과정'에서 제외시키므로, 데이터(Object)를 처리하는 함수 객체는
     * <b>{@code null}</b>을 처리하지 않아도 됩니다.</b></font>
     * </p>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 4.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <R>
     *            새로운 {@link Collection} 유형.
     * @param objects
     *            변환할 데이터
     * @param collectionSupplier
     *            {@link Collection} 객체 제공 함수.
     * 
     * @return
     *
     * @since 2025. 9. 4.
     * @version 2.1.0
     */
    public static <R extends Collection<String>> R toString(Collection<Object> objects, Supplier<R> collectionSupplier) {
        return toString(objects, strFunction, collectionSupplier);
    }

    /**
     * 입력데이터 타입에서 정의된 메소드 중에서 {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로
     * 제공합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 4. 3.      parkjunhong77@gmail.com         최초 작성
     * 2025. 8. 30.     parkjunhong77@gmail.com         내부 구현을 {@link #transform(Collection, boolean, Supplier, boolean, Map, Supplier)}으로 전환.
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
     * @param targetClass
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
     * 
     * @return
     *
     * @since 2025. 4. 3.
     * @version 2.1.0
     */
    public static <S, T, C extends Collection<T>> C transform(Collection<S> src, boolean lookupSrcSuper, Class<T> targetClass, boolean lookupTargetSuper,
            @Nullable Map<String, Function<?, ?>> converters, Supplier<C> collectionSupplier) {
        Objects.requireNonNull(targetClass);

        return transform(src, lookupSrcSuper //
                , (Supplier<T>) () -> {
                    try {
                        return targetClass.getDeclaredConstructor().newInstance();
                    } catch (Exception e) {
                        throw new CreateInstanceFailedException(targetClass, e);
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
     * 2025. 4. 3.      parkjunhong77@gmail.com         최초 작성
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
     * @param targetClass
     *            기존 데이터 정보를 전달받을 새로운 데이터 유형.
     * @param lookupTargetSuper
     *            대상 객체 상위 인터페이스/클래스 확장 여부
     * @param collectionSupplier
     *            대상 데이터를 포함할 {@link Collection}
     * 
     * @return
     *
     * @since 2025. 4. 3.
     * @version 2.1.0
     */
    public static <S, T, C extends Collection<T>> C transform(Collection<S> src, boolean lookupSrcSuper, Class<T> targetClass, boolean lookupTargetSuper,
            Supplier<C> collectionSupplier) {
        return transform(src, lookupSrcSuper, targetClass, lookupTargetSuper, null, collectionSupplier);
    }

    /**
     * 입력데이터 타입에서 정의된 메소드 중에서 {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로
     * 제공합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 4. 3.      parkjunhong77@gmail.com         최초 작성
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
     * @param targetClass
     *            데이터를 전달받을 새로운 데이터 유형.
     * @param converters
     *            데이터 변환 함수
     *            <ul>
     *            <li>{@link #FIELD_CONVERTER_KEYGEN} 로 만들어진 식별정보
     *            <li>타입 변환 함수
     *            </ul>
     * @param collectionSupplier
     *            대상 데이터를 포함할 {@link Collection}
     * 
     * @return
     *
     * @since 2025. 4. 3.
     * @version 2.1.0
     */
    public static <S, T, C extends Collection<T>> C transform(Collection<S> src, boolean lookupSrcSuper, Class<T> targetClass, @Nullable Map<String, Function<?, ?>> converters,
            Supplier<C> collectionSupplier) {
        return transform(src, lookupSrcSuper, targetClass, false, converters, collectionSupplier);
    }

    /**
     * 입력데이터 타입에서 정의된 메소드 중에서 {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로
     * 제공합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 4. 3.      parkjunhong77@gmail.com         최초 작성
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
     * @param targetClass
     *            데이터를 전달받을 새로운 데이터 유형.
     * @param collectionSupplier
     *            대상 데이터를 포함할 {@link Collection}
     * 
     * @return
     *
     * @since 2025. 4. 3.
     * @version 2.1.0
     */
    public static <S, T, C extends Collection<T>> C transform(Collection<S> src, boolean lookupSrcSuper, Class<T> targetClass, Supplier<C> collectionSupplier) {
        return transform(src, lookupSrcSuper, targetClass, false, null, collectionSupplier);
    }

    /**
     * 입력데이터 타입에서 정의된 메소드 중에서 {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로
     * 제공합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 30.     parkjunhong77@gmail.com         최초 작성
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
     * 
     * @return
     *
     * @since 2025. 8. 30.
     * @version 2.1.0
     */
    public static <S, T, C extends Collection<T>> C transform(Collection<S> src, boolean lookupSrcSuper, Supplier<T> targetInstanceSupplier, boolean lookupTargetSuper,
            @Nullable Map<String, Function<?, ?>> converters, Supplier<C> collectionSupplier) {
        Objects.requireNonNull(src);
        Objects.requireNonNull(collectionSupplier);

        return src.stream() //
                .filter(Objects::nonNull) //
                .map(s -> ObjectTransformer.transform(s, lookupSrcSuper, targetInstanceSupplier.get(), lookupTargetSuper, converters)) //
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
     * 2025. 8. 30.     parkjunhong77@gmail.com         최초 작성
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
     * 
     * @return
     *
     * @since 2025. 8. 30.
     * @version 2.1.0
     */
    public static <S, T, C extends Collection<T>> C transform(Collection<S> src, boolean lookupSrcSuper, Supplier<T> targetInstanceSupplier, boolean lookupTargetSuper,
            Supplier<C> collectionSupplier) {
        return transform(src, lookupSrcSuper, targetInstanceSupplier, lookupTargetSuper, null, collectionSupplier);
    }

    /**
     * 입력데이터 타입에서 정의된 메소드 중에서 {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로
     * 제공합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 30.      parkjunhong77@gmail.com         최초 작성
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
     * 
     * @return
     *
     * @since 2025. 8. 30.
     * @version 2.1.0
     */
    public static <S, T, C extends Collection<T>> C transform(Collection<S> src, boolean lookupSrcSuper, Supplier<T> targetInstanceSupplier,
            @Nullable Map<String, Function<?, ?>> converters, Supplier<C> collectionSupplier) {
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
     * 2025. 8. 30.      parkjunhong77@gmail.com         최초 작성
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
     * 
     * @return
     *
     * @since 2025. 8. 30.
     * @version 2.1.0
     */
    public static <S, T, C extends Collection<T>> C transform(Collection<S> src, boolean lookupSrcSuper, Supplier<T> targetInstanceSupplier, Supplier<C> collectionSupplier) {
        return transform(src, lookupSrcSuper, targetInstanceSupplier, false, null, collectionSupplier);
    }

    /**
     * 입력데이터 타입에서 정의된 메소드 중에서 {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로
     * 제공합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 4. 3.      parkjunhong77@gmail.com         최초 작성
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
     * @param targetClass
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
     * 
     * @return
     *
     * @since 2025. 4. 3.
     * @version 2.1.0
     */
    public static <S, T, C extends Collection<T>> C transform(Collection<S> src, Class<T> targetClass, boolean lookupTargetSuper, @Nullable Map<String, Function<?, ?>> converters,
            Supplier<C> collectionSupplier) {
        return transform(src, false, targetClass, lookupTargetSuper, converters, collectionSupplier);
    }

    /**
     * 입력데이터 타입에서 정의된 메소드 중에서 {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로
     * 제공합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 4. 3.      parkjunhong77@gmail.com         최초 작성
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
     * @param targetClass
     *            데이터를 전달받을 새로운 데이터 유형.
     * @param lookupTargetSuper
     *            대상 객체 상위 인터페이스/클래스 확장 여부
     * @param collectionSupplier
     *            대상 데이터를 포함할 {@link Collection}
     * 
     * @return
     *
     * @since 2025. 4. 3.
     * @version 2.1.0
     */
    public static <S, T, C extends Collection<T>> C transform(Collection<S> src, Class<T> targetClass, boolean lookupTargetSuper, Supplier<C> collectionSupplier) {
        return transform(src, false, targetClass, lookupTargetSuper, null, collectionSupplier);
    }

    /**
     * 입력데이터 타입에서 정의된 메소드 중에서 {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로
     * 제공합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 4. 3.      parkjunhong77@gmail.com         최초 작성
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
     * @param targetClass
     *            데이터를 전달받을 새로운 데이터 유형.
     * @param converters
     *            데이터 변환 함수
     *            <ul>
     *            <li>{@link #FIELD_CONVERTER_KEYGEN} 로 만들어진 식별정보
     *            <li>타입 변환 함수
     *            </ul>
     * @param collectionSupplier
     *            대상 데이터를 포함할 {@link Collection}
     * 
     * @return
     *
     * @since 2025. 4. 3.
     * @version 2.1.0
     */
    public static <S, T, C extends Collection<T>> C transform(Collection<S> src, Class<T> targetClass, @Nullable Map<String, Function<?, ?>> converters,
            Supplier<C> collectionSupplier) {
        return transform(src, false, targetClass, false, converters, collectionSupplier);
    }

    /**
     * 입력데이터 타입에서 정의된 메소드 중에서 {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로
     * 제공합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 4. 3.      parkjunhong77@gmail.com         최초 작성
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
     * @param targetClass
     *            데이터를 전달받을 새로운 데이터 유형.
     * @param collectionSupplier
     *            대상 데이터를 포함할 {@link Collection}
     * 
     * @return
     *
     * @since 2025. 4. 3.
     * @version 2.1.0
     */
    public static <S, T, C extends Collection<T>> C transform(Collection<S> src, Class<T> targetClass, Supplier<C> collectionSupplier) {
        return transform(src, false, targetClass, false, null, collectionSupplier);
    }

    /**
     * 입력데이터 타입에서 정의된 메소드 중에서 {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로
     * 제공합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 30.      parkjunhong77@gmail.com         최초 작성
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
     * 
     * @return
     *
     * @since 2025. 8. 30.
     * @version 2.1.0
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
     * 2025. 8. 30.      parkjunhong77@gmail.com         최초 작성
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
     * 
     * @return
     *
     * @since 2025. 8. 30.
     * @version 2.1.0
     */
    public static <S, T, C extends Collection<T>> C transform(Collection<S> src, Supplier<T> targetInstanceSupplier, boolean lookupTargetSuper, Supplier<C> collectionSupplier) {
        return transform(src, false, targetInstanceSupplier, lookupTargetSuper, null, collectionSupplier);
    }

    /**
     * 입력데이터 타입에서 정의된 메소드 중에서 {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로
     * 제공합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 30.      parkjunhong77@gmail.com         최초 작성
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
     * 
     * @return
     *
     * @since 2025. 8. 30.
     * @version 2.1.0
     */
    public static <S, T, C extends Collection<T>> C transform(Collection<S> src, Supplier<T> targetInstanceSupplier, @Nullable Map<String, Function<?, ?>> converters,
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
     * 2025. 8. 30.      parkjunhong77@gmail.com         최초 작성
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
     * 
     * @return
     *
     * @since 2025. 8. 30.
     * @version 2.1.0
     */
    public static <S, T, C extends Collection<T>> C transform(Collection<S> src, Supplier<T> targetInstanceSupplier, Supplier<C> collectionSupplier) {
        return transform(src, false, targetInstanceSupplier, false, null, collectionSupplier);
    }

    /**
     * {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 7. 11.     parkjunhong77@gmail.com         최초 작성
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
     * @param targetClass
     *            변환 타입. 기본생성자가 반드시 있어야 합니다.
     * 
     * @return
     *
     * @since 2019. 7. 11.
     * 
     * @see #transform(Object, boolean, Class, boolean)
     */
    public static <S, T> T transform(S src, boolean lookupSrcSuper, Class<T> targetClass) {
        return transform(src, lookupSrcSuper, targetClass, false);
    }

    /**
     * 입력데이터 타입에서 정의된 메소드 중에서 {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로
     * 제공합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 7. 11.     parkjunhong77@gmail.com         최초 작성
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
     * @param targetClass
     *            변환 타입. 기본생성자가 반드시 있어야 합니다.
     * @param lookupTargetSuper
     *            변환 대상 클래스 상위 인터페이스/클래스 확장 여부
     * 
     * @return
     *
     * @since 2019. 7. 11.
     */
    public static <S, T> T transform(S src, boolean lookupSrcSuper, Class<T> targetClass, boolean lookupTargetSuper) {
        Objects.requireNonNull(targetClass);

        return transform(src, lookupSrcSuper //
                , (Supplier<T>) () -> {
                    try {
                        return targetClass.getDeclaredConstructor().newInstance();
                    } catch (Exception e) {
                        throw new CreateInstanceFailedException(targetClass, e);
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
     * 2021. 11. 22.        parkjunhong77@gmail.com         최초 작성
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
     * @param targetClass
     *            변환 타입. 기본생성자가 반드시 있어야 합니다.
     * @param lookupTargetSuper
     *            변환 대상 클래스 상위 인터페이스/클래스 확장 여부
     * @param converters
     *            데이터 변환 함수
     *            <ul>
     *            <li>{@link #FIELD_CONVERTER_KEYGEN} 로 만들어진 식별정보
     *            <li>타입 변환 함수
     *            </ul>
     * 
     * @return
     * 
     * @throws CreateInstanceFailedException
     *             대상 클래스({@code targetClass})의 기본 생성자를 호출하여 인스턴스를 생성하는 중 예외가 발생한 경우.
     *
     * @since 2021. 11. 22.
     * @version 1.8.0
     */
    public static <S, T> T transform(S src, boolean lookupSrcSuper, Class<T> targetClass, boolean lookupTargetSuper, @Nullable Map<String, Function<?, ?>> converters) {
        Objects.requireNonNull(targetClass);

        return transform(src, lookupSrcSuper //
                , (Supplier<T>) () -> {
                    try {
                        return targetClass.getDeclaredConstructor().newInstance();
                    } catch (Exception e) {
                        throw new CreateInstanceFailedException(targetClass, e);
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
     * 2021. 11. 22.     parkjunhong77@gmail.com         최초 작성
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
     * @param targetClass
     *            변환 타입. 기본생성자가 반드시 있어야 합니다.
     * @param converters
     *            데이터 변환 함수
     *            <ul>
     *            <li>{@link #FIELD_CONVERTER_KEYGEN} 로 만들어진 식별정보
     *            <li>타입 변환 함수
     *            </ul>
     * 
     * @return
     *
     * @since 2021. 11. 22.
     * @version 1.8.0
     * 
     * @see #transform(Object, boolean, Class, boolean)
     */
    public static <S, T> T transform(S src, boolean lookupSrcSuper, Class<T> targetClass, @Nullable Map<String, Function<?, ?>> converters) {
        return transform(src, lookupSrcSuper, targetClass, false, converters);
    }

    /**
     * {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 30.     parkjunhong77@gmail.com         최초 작성
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
     * 
     * @return
     *
     * @since 2025. 8. 30.
     * @version 2.1.0
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
     * 2025. 8. 30.     parkjunhong77@gmail.com         최초 작성
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
     * 
     * @return
     *
     * @since 2025. 8. 30.
     * @version 2.1.0
     */
    public static <S, T> T transform(S src, boolean lookupSrcSuper, Supplier<T> targetInstanceSupplier, boolean lookupTargetSuper) {
        Objects.requireNonNull(targetInstanceSupplier);

        return ObjectTransformer.transform(src, lookupSrcSuper, targetInstanceSupplier.get(), lookupTargetSuper, null);
    }

    /**
     * 입력데이터 타입에서 정의된 메소드 중에서 {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로
     * 제공합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 30.     parkjunhong77@gmail.com         최초 작성
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
     * 
     * @return
     *
     * @since 2025. 8. 30.
     * @version 2.1.0
     */
    public static <S, T> T transform(S src, boolean lookupSrcSuper, Supplier<T> targetInstanceSupplier, boolean lookupTargetSuper,
            @Nullable Map<String, Function<?, ?>> converters) {
        return ObjectTransformer.transform(src, lookupSrcSuper, targetInstanceSupplier.get(), lookupTargetSuper, converters);
    }

    /**
     * {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 30.     parkjunhong77@gmail.com         최초 작성
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
     * 
     * @return
     *
     * @since 2025. 8. 30.
     * @version 2.1.0
     * 
     * @see #transform(Object, boolean, Class, boolean)
     */
    public static <S, T> T transform(S src, boolean lookupSrcSuper, Supplier<T> targetInstanceSupplier, @Nullable Map<String, Function<?, ?>> converters) {
        return transform(src, lookupSrcSuper, targetInstanceSupplier, false, converters);
    }

    /**
     * {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 12. 08.     parkjunhong77@gmail.com         최초 작성
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
     * @param targetClass
     *            데이터를 전달받을 새로운 데이터 유형.
     * 
     * @return
     *
     * @since 2020. 12. 08.
     * 
     * @see #transform(Object, boolean, Class, boolean)
     */
    public static <S, T> T transform(S src, boolean lookupSrcSuper, T target) {
        return ObjectTransformer.transform(src, lookupSrcSuper, target, false, null);
    }

    /**
     * 입력데이터 타입에서 정의된 메소드 중에서 {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로
     * 제공합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 12. 08.     parkjunhong77@gmail.com         최초 작성
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
     * @param targetClass
     *            데이터를 전달받을 새로운 데이터 유형.
     * @param lookupTargetSuper
     *            대상 객체 상위 인터페이스/클래스 확장 여부
     * 
     * @return
     *
     * @since 2020. 12. 08.
     * 
     * @see #FIELD_CONVERTERS
     */
    public static <S, T> T transform(S src, boolean lookupSrcSuper, T target, boolean lookupTargetSuper) {
        return ObjectTransformer.transform(src, lookupSrcSuper, target, lookupTargetSuper, null);
    }

    /**
     * 입력데이터 타입에서 정의된 메소드 중에서 {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로
     * 제공합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 12. 08.     parkjunhong77@gmail.com         최초 작성
     * 2021. 11. 22.        parkjunhong77@gmail.com      Map&lt;String, Function&lt;Object, Object&gt;&gt; 추가
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
     *            데이터 변환 함수. 이 값이 {@code null}인 경우, {@link #FIELD_CONVERTERS} 값을 사용합니다.
     *            <ul>
     *            <li>{@link #FIELD_CONVERTER_KEYGEN} 로 만들어진 식별정보
     *            <li>타입 변환 함수
     *            </ul>
     * 
     * @return
     *
     * @since 2021. 11. 22.
     * @version 1.8.0
     */
    public static <S, T> T transform(S src, boolean lookupSrcSuper, T target, boolean lookupTargetSuper, @Nullable Map<String, Function<?, ?>> converters) {
        return ObjectTransformer.transform(src, lookupSrcSuper, target, lookupTargetSuper, converters);
    }

    /**
     * {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 12. 08.     parkjunhong77@gmail.com         최초 작성
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
     * @param targetClass
     *            데이터를 전달받을 새로운 데이터 유형.
     * @param converters
     *            데이터 변환 함수
     *            <ul>
     *            <li>{@link #FIELD_CONVERTER_KEYGEN} 로 만들어진 식별정보
     *            <li>타입 변환 함수
     *            </ul>
     * 
     * @return
     *
     * @since 2020. 12. 08.
     * @version 1.8.0
     * 
     * @see #transform(Object, boolean, Class, boolean)
     */
    public static <S, T> T transform(S src, boolean lookupSrcSuper, T target, @Nullable Map<String, Function<?, ?>> converters) {
        return ObjectTransformer.transform(src, lookupSrcSuper, target, false, converters);
    }

    /**
     * 입력데이터 타입에서 정의된 메소드 중에서 {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로 제공합니다.
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 6. 20.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <S>
     *            입력 데이터 타입 정의.
     * @param <T>
     *            신규 데이터 타입 정의.
     * @param src
     *            입력 데이터
     * @param targetClass
     *            변환 타입
     * 
     * @return
     *
     * @since 2019. 6. 20.
     * @version
     * 
     * @see Getter
     * @see Setter
     */
    public static <S, T> T transform(S src, Class<T> targetClass) {
        return transform(src, false, targetClass, false);
    }

    /**
     * 입력데이터 타입에서 정의된 메소드 중에서 입력데이터 타입에서 정의된 메소드 중에서 {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를
     * 변환하여 새로운 타입의 객체로 제공합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 7. 11.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <S>
     *            입력 데이터 타입 정의.
     * @param <T>
     *            신규 데이터 타입 정의.
     * @param src
     *            입력 데이터.
     * @param targetClass
     *            변환 타입. 기본생성자가 반드시 있어야 합니다.
     * @param lookupTargetSuper
     *            변환 대상 클래스 상위 인터페이스/클래스 확장 여부
     * 
     * @return
     *
     * @since 2019. 7. 11.
     */
    public static <S, T> T transform(S src, Class<T> targetClass, boolean lookupTargetSuper) {
        return transform(src, false, targetClass, lookupTargetSuper);
    }

    /**
     * 입력데이터 타입에서 정의된 메소드 중에서 입력데이터 타입에서 정의된 메소드 중에서 {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를
     * 변환하여 새로운 타입의 객체로 제공합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 22.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <S>
     *            입력 데이터 타입 정의.
     * @param <T>
     *            신규 데이터 타입 정의.
     * @param src
     *            입력 데이터.
     * @param targetClass
     *            변환 타입. 기본생성자가 반드시 있어야 합니다.
     * @param lookupTargetSuper
     *            변환 대상 클래스 상위 인터페이스/클래스 확장 여부
     * @param converters
     *            데이터 변환 함수
     *            <ul>
     *            <li>{@link #FIELD_CONVERTER_KEYGEN} 로 만들어진 식별정보
     *            <li>타입 변환 함수
     *            </ul>
     * 
     * @return
     *
     * @since 2021. 11. 22.
     * @version 1.8.0
     */
    public static <S, T> T transform(S src, Class<T> targetClass, boolean lookupTargetSuper, @Nullable Map<String, Function<?, ?>> converters) {
        return transform(src, false, targetClass, lookupTargetSuper, converters);
    }

    /**
     * 입력데이터 타입에서 정의된 메소드 중에서 {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로 제공합니다.
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 22.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <S>
     *            입력 데이터 타입 정의.
     * @param <T>
     *            신규 데이터 타입 정의.
     * @param src
     *            입력 데이터
     * @param targetClass
     *            변환 타입
     * @param converters
     *            데이터 변환 함수.
     *            <ul>
     *            <li>{@link #FIELD_CONVERTER_KEYGEN} 로 만들어진 식별정보
     *            <li>타입 변환 함수
     *            </ul>
     * 
     * @return
     *
     * @since 2021. 11. 22.
     * @version 1.8.0
     * 
     * @see Getter
     * @see Setter
     */
    public static <S, T> T transform(S src, Class<T> targetClass, @Nullable Map<String, Function<?, ?>> converters) {
        return transform(src, false, targetClass, false, converters);
    }

    /**
     * 입력데이터 타입에서 정의된 메소드 중에서 {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로 제공합니다.
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 30.     parkjunhong77@gmail.com         최초 작성
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
     * 
     * @return
     *
     * @since 2025. 8. 30.
     * @version 2.1.0
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
     * 2025. 8. 30.     parkjunhong77@gmail.com         최초 작성
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
     * 
     * @return
     *
     * @since 2025. 8. 30.
     * @version 2.1.0
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
     * 2025. 8. 30.     parkjunhong77@gmail.com         최초 작성
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
     * 
     * @return
     *
     * @since 2025. 8. 30.
     * @version 2.1.0
     */
    public static <S, T> T transform(S src, Supplier<T> targetInstanceSupplier, boolean lookupTargetSuper, @Nullable Map<String, Function<?, ?>> converters) {
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
     * 2025. 8. 30.     parkjunhong77@gmail.com         최초 작성
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
     * 
     * @return
     *
     * @since 2025. 8. 30.
     * @version 2.1.0
     * 
     * @see Getter
     * @see Setter
     */
    public static <S, T> T transform(S src, Supplier<T> targetInstanceSupplier, @Nullable Map<String, Function<?, ?>> converters) {
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
     * 2020. 12. 08.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <S>
     *            입력 데이터 타입 정의.
     * @param <T>
     *            신규 데이터 타입 정의.
     * @param src
     *            입력 데이터
     * @param targetClass
     *            데이터를 전달받을 새로운 데이터 유형.
     * 
     * @return
     *
     * @since 2020. 12. 08.
     * @version
     * 
     * @see Getter
     * @see Setter
     */
    public static <S, T> T transform(S src, T target) {
        return ObjectTransformer.transform(src, false, target, false, null);
    }

    /**
     * 입력데이터 타입에서 정의된 메소드 중에서 입력데이터 타입에서 정의된 메소드 중에서 {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를
     * 변환하여 새로운 타입의 객체로 제공합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 7. 11.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <S>
     *            입력 데이터 타입 정의.
     * @param <T>
     *            신규 데이터 타입 정의.
     * @param src
     *            입력 데이터.
     * @param targetClass
     *            데이터를 전달받을 새로운 데이터 유형.
     * @param lookupTargetSuper
     *            변환 대상 클래스 상위 인터페이스/클래스 확장 여부
     * 
     * @return
     *
     * @since 2019. 7. 11.
     */
    public static <S, T> T transform(S src, T target, boolean lookupTargetSuper) {
        return ObjectTransformer.transform(src, false, target, lookupTargetSuper, null);
    }

    /**
     * 입력데이터 타입에서 정의된 메소드 중에서 입력데이터 타입에서 정의된 메소드 중에서 {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를
     * 변환하여 새로운 타입의 객체로 제공합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 22.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <S>
     *            입력 데이터 타입 정의.
     * @param <T>
     *            신규 데이터 타입 정의.
     * @param src
     *            입력 데이터.
     * @param targetClass
     *            데이터를 전달받을 새로운 데이터 유형.
     * @param lookupTargetSuper
     *            변환 대상 클래스 상위 인터페이스/클래스 확장 여부
     * @param converters
     *            데이터 변환 함수
     *            <ul>
     *            <li>{@link #FIELD_CONVERTER_KEYGEN} 로 만들어진 식별정보
     *            <li>타입 변환 함수
     *            </ul>
     * 
     * @return
     *
     * @since 2021. 11. 22.
     * @version 1.8.0
     */
    public static <S, T> T transform(S src, T target, boolean lookupTargetSuper, @Nullable Map<String, Function<?, ?>> converters) {
        return ObjectTransformer.transform(src, false, target, lookupTargetSuper, converters);
    }

    /**
     * 입력데이터 타입에서 정의된 메소드 중에서 {@link Getter}, 대상 타입에서 정의된 메소드 중에서 {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로
     * 제공합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11.22.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <S>
     *            입력 데이터 타입 정의.
     * @param <T>
     *            신규 데이터 타입 정의.
     * @param src
     *            입력 데이터
     * @param targetClass
     *            데이터를 전달받을 새로운 데이터 유형.
     * @param converters
     *            데이터 변환 함수
     *            <ul>
     *            <li>{@link #FIELD_CONVERTER_KEYGEN} 로 만들어진 식별정보
     *            <li>타입 변환 함수
     *            </ul>
     * 
     * @return
     *
     * @since 2021. 11. 22.
     * @version 1.8.0
     * 
     * @see Getter
     * @see Setter
     */
    public static <S, T> T transform(S src, T target, @Nullable Map<String, Function<?, ?>> converters) {
        return ObjectTransformer.transform(src, false, target, false, converters);
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
     * 2025. 8. 30.     parkjunhong77@gmail.com         최초 작성
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
     * @param targetClass
     *            데이터를 전달받을 새로운 데이터 유형.
     * @param converters
     *            데이터 변환 함수
     *            <ul>
     *            <li>{@link #FIELD_CONVERTER_KEYGEN} 로 만들어진 식별정보
     *            <li>타입 변환 함수
     *            </ul>
     * @param collectionSupplier
     *            대상 데이터를 포함할 {@link Collection}
     * 
     * @return
     *
     * @since 2025. 8. 30.
     * @version 2.1.0
     * 
     * @see #transform(Object, boolean, Object, boolean, Map, Supplier)
     */
    public static <S, T, C extends Collection<T>> C transformAll(Collection<S> src, Class<T> targetClass, @Nullable Map<String, Function<?, ?>> converters,
            Supplier<C> collectionSupplier) {
        return transform(src, true, targetClass, true, converters, collectionSupplier);
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
     * 2025. 8. 30.     parkjunhong77@gmail.com         최초 작성
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
     * @param targetClass
     *            데이터를 전달받을 새로운 데이터 유형.
     * @param collectionSupplier
     *            대상 데이터를 포함할 {@link Collection}
     * 
     * @return
     *
     * @since 2025. 8. 30.
     * @version 2.1.0
     * 
     * @see #transform(Object, boolean, Object, boolean, Map, Supplier)
     */
    public static <S, T, C extends Collection<T>> C transformAll(Collection<S> src, Class<T> targetClass, Supplier<C> collectionSupplier) {
        return transform(src, true, targetClass, true, null, collectionSupplier);
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
     * 2025. 8. 30.     parkjunhong77@gmail.com         최초 작성
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
     * 
     * @return
     *
     * @since 2025. 8. 30.
     * @version 2.1.0
     * 
     * @see #transform(Object, boolean, Object, boolean, Map, Supplier)
     */
    public static <S, T, C extends Collection<T>> C transformAll(Collection<S> src, Supplier<T> targetInstanceSupplier, @Nullable Map<String, Function<?, ?>> converters,
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
     * 2025. 8. 30.     parkjunhong77@gmail.com         최초 작성
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
     * 
     * @return
     *
     * @since 2025. 8. 30.
     * @version 2.1.0
     * 
     * @see #transform(Object, boolean, Object, boolean, Map, Supplier)
     */
    public static <S, T, C extends Collection<T>> C transformAll(Collection<S> src, Supplier<T> targetInstanceSupplier, Supplier<C> collectionSupplier) {
        return transform(src, true, targetInstanceSupplier, true, null, collectionSupplier);
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
     * 2025. 8. 30.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <S>
     *            입력 데이터 타입 정의.
     * @param <T>
     *            신규 데이터 타입 정의.
     * @param src
     *            입력 데이터
     * @param targetClass
     *            변환 타입
     * 
     * @return
     *
     * @since 2025. 8. 30.
     * @version 2.1.0
     */
    public static <S, T> T transformAll(S src, Class<T> targetClass) {
        return transform(src, true, targetClass, true);
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
     * 2025. 8. 30.     parkjunhong77@gmail.com         최초 작성
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
     * 
     * @return
     *
     * @since 2025. 8. 30.
     * @version 2.1.0
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
     * 2025. 8. 30.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <S>
     *            입력 데이터 타입 정의.
     * @param <T>
     *            신규 데이터 타입 정의.
     * @param src
     *            입력 데이터
     * @param targetClass
     *            데이터를 전달받을 새로운 데이터 유형.
     * 
     * @return
     *
     * @since 2025. 8. 30.
     * @version 2.1.0
     * 
     * @see #transform(Object, boolean, Class, boolean)
     */
    public static <S, T> T transformAll(S src, T target) {
        return ObjectTransformer.transform(src, true, target, true, null);
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
     * 2025. 8. 30.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <S>
     *            입력 데이터 타입 정의.
     * @param <T>
     *            신규 데이터 타입 정의.
     * @param src
     *            입력 데이터
     * @param targetClass
     *            데이터를 전달받을 새로운 데이터 유형.
     * @param converters
     *            데이터 변환 함수
     *            <ul>
     *            <li>{@link #FIELD_CONVERTER_KEYGEN} 로 만들어진 식별정보
     *            <li>타입 변환 함수
     *            </ul>
     * 
     * @return
     *
     * @since 2025. 8. 30.
     * @version 2.1.0
     * 
     * @see #transform(Object, boolean, Object, boolean)
     */
    public static <S, T> T transformAll(S src, T target, @Nullable Map<String, Function<?, ?>> converters) {
        return ObjectTransformer.transform(src, true, target, true, converters);
    }

    // JDK 16+ Record를 활용한 불변(Immutable) 메타데이터 저장소
    private record SetterInfo(String name, Class<?> paramType, MethodHandle handle) {
    }
}
