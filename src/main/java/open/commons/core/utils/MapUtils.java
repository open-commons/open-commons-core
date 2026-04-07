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
* Date  : 2015. 1. 6. 오후 4:00:34
*
* Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
* 
*/

package open.commons.core.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jspecify.annotations.Nullable;

/**
 * 
 * @since 2015. 1. 6.
 * 
 */
public class MapUtils {

    public static void clear(Map<?, ?>... maps) {
        Objects.requireNonNull(maps);

        for (Map<?, ?> map : maps) {
            if (map != null) {
                map.clear();
            }
        }
    }

    /**
     * {@link Collection} 형태의 값을 갖는 {@link Map} 정보를 하나의 1차원 형태의 {@link Stream}으로 제공합니다. <br>
     *
     * 
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 10. 21.    parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <K>
     *            데이터 식별정보 유형 (Nullable)
     * @param <V>
     *            데이터 유형 (Nullable)
     * @param <C>
     *            데이터를 담고 있는 {@link Collection} 유형
     * @param multi
     *            1차원으로 변환할 다차원 {@link Map} 데이터
     * 
     * @return 1차원으로 펼쳐진 데이터 {@link Stream}
     * 
     * @throws NullPointerException
     *             파라미터({@code multi})가 {@code null}인 경우 발생.
     *
     * @since 2025. 10. 21.
     * @version 2.1.0
     */
    // 아래 내용에 적용됨.
    // - Stream.flatMap(...)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static <K extends @Nullable Object, V extends @Nullable Object, C extends Collection<V>> Stream<V> flat(Map<K, C> multi) {
        Objects.requireNonNull(multi, "A parameter 'multi' must not be 'null'.");

        return multi.values().stream().filter(Objects::nonNull).flatMap(Collection::stream);
    }

    /**
     * 2개의 {@link Map}을 하나의 {@link List}로 병합하여 {@link Stream} 형태로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 20.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <K>
     *            데이터 식별정보 유형 (Nullable)
     * @param <V>
     *            데이터 유형 (Nullable)
     * @param <C>
     *            데이터를 담고 있는 {@link Collection} 유형
     * @param single
     *            단일 값을 갖는 {@link Map} 데이터
     * @param multi
     *            다중 값을 갖는 {@link Map} 데이터
     * 
     * @return 두 맵의 값들이 병합된 1차원 데이터 {@link Stream}
     * 
     * @throws NullPointerException
     *             파라미터({@code single, multi 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2025. 8. 20.
     * @version 2.1.0
     */
    // 아래 내용에 적용됨.
    // - Stream.concat(...)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static <K extends @Nullable Object, V extends @Nullable Object, C extends Collection<V>> Stream<V> flat(Map<K, V> single, Map<K, C> multi) {
        ObjectUtils.requireNonNulls(single, multi);

        // 불필요한 Stream.of() 및 flatMap() 래핑을 제거하고, 가장 빠르고 표준적인 concat 적용
        return Stream.concat(single.values().stream(), flat(multi));
    }

    /**
     * {@link Map}에서 주어진 {@code key}에 해당하는 값을 제공합니다.<br>
     * 존재하지 않는 경우 기본값({@code defaultValue})을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 3. 29.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <K>
     *            데이터 식별정보 유형
     * @param <V>
     *            데이터 유형
     * @param map
     * @param key
     *            찾고자하는 데이터 키
     * @param defaultValue
     *            기본값 ({@link Supplier#get()) MUST NOT be null.)
     * @param insertIfNot
     *            {@link Map}에 존재하지 않는 경우 추가할지 여부
     * 
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code map})가 {@code null}인 경우 발생.
     *
     * @since 2020. 10. 23.
     * @version 1.7.0
     */
    public static <K extends @Nullable Object, V extends @Nullable Object> V getOrDefault(Map<K, V> map, K key, Supplier<V> defaultValue, boolean insertIfNot) {
        Objects.requireNonNull(map);

        V v = map.get(key);
        if (v != null) {
            return v;
        }

        v = defaultValue.get();
        if (insertIfNot) {
            map.put(key, v);
        }

        return v;
    }

    /**
     * {@link Map}에서 주어진 {@code key}에 해당하는 값을 제공합니다.<br>
     * 존재하지 않는 경우 기본값({@code defaultValue})을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 3. 29.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <K>
     *            데이터 식별정보 유형
     * @param <V>
     *            데이터 유형
     * @param map
     * @param key
     *            찾고자하는 데이터 키
     * @param defaultValue
     *            기본값
     * @param insertIfNot
     *            {@link Map}에 존재하지 않는 경우 추가할지 여부
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code map})가 {@code null}인 경우 발생.
     *
     * @since v1.6.18
     * @since 2020. 3. 29.
     * 
     * @see Map#getOrDefault(Object, Object)
     */
    public static <K extends @Nullable Object, V extends @Nullable Object> V getOrDefault(Map<K, V> map, K key, V defaultValue, boolean insertIfNot) {
        Objects.requireNonNull(map);

        V v = map.getOrDefault(key, defaultValue);

        if (!map.containsKey(key) && insertIfNot) {
            map.put(key, v);
        }

        return v;
    }

    /**
     * 주어진 {@link Map} 객체가 {@code null}이거나 비어 있는지 ({@link Map#isEmpty()}) 여부를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 8.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <K>
     *            데이터 식별정보 유형
     * @param <V>
     *            데이터 유형
     * @param map
     * @return
     *
     * @since 2025. 8. 8.
     * @version 2.1.0
     */
    public static <K, V> boolean isNullOrEmpty(@Nullable Map<K, V> map) {
        return map == null || map.size() < 1;
    }

    /**
     * {@link Map} 데이터를 새로운 식별정보({@code keyMapper}), 새로운 유형({@code valueFunction})로 변환하여 새로운 {@link Map}를 제공합니다.<br>
     * 
     * <p>
     * <font color="red"><b>* 데이터(V)가 <b>{@code null}</b>인 경우 '전처리 과정'에서 제외시키므로, 데이터(V)를 처리하는 함수 객체는
     * <b>{@code null}</b>을 처리하지 않아도 됩니다.</b></font>
     * </p>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 21.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <K>
     *            데이터 식별정보 유형 (Nullable)
     * 
     * @param <V>
     *            데이터 유형 (Nullable)
     * @param <NK>
     *            새로운 데이터 식별정보 (<b>{@code NOT nullable}</b>: {@link Collectors#groupingBy} 제약사항)
     * @param <NV>
     *            새로운 데이터 유형 (Nullable).
     * @param <M>
     *            결과 {@link Map} 유형 * @param map 데이터 객체
     * @param keyMapper
     *            데이터 식별정보 제공 함수. (K &rarr; NK) <br>
     *            <font color="red"><b>주의: 반환값으로 절대 {@code null}을 제공해서는 안 됩니다.</b></font>
     * @param valueFunction
     *            데이터 변환 함수. (V &rarr; NV) <br>
     *            반환값으로 {@code null}을 제공할 수 있으나, 이 경우 {@code colSupplier}가 제공하는 컬렉션이 {@code null}을 허용해야 합니다.
     * @param mapSupplier
     *            결과 {@link Map} 객체 제공 함수.
     * 
     * @return 변환 및 그룹화가 완료된 새로운 Map 객체
     * 
     * @throws NullPointerException
     *             파라미터 중에 1개라도 {@code null}인 경우 발생. <br>
     *             또한 {@code keyMapper}의 반환값이 {@code null}인 경우 발생.
     *
     * @since 2025. 8. 21.
     * @version 2.1.0
     */
    public static <K extends @Nullable Object, V extends @Nullable Object, NK, NV extends @Nullable Object, M extends Map<NK, List<NV>>> //
            M map(Map<K, V> map, Function<Entry<K, V>, NK> keyMapper, Function<Entry<K, V>, NV> valueFunction, Supplier<M> mapSupplier) {
        return map(map, keyMapper, valueFunction, mapSupplier, (Supplier<List<NV>>) ArrayList<NV>::new);
    }

    /**
     * {@link Map} 데이터를 새로운 식별정보({@code keyMapper}), 새로운 유형({@code valueFunction})으로 변환하여 새로운 {@link Map}를 제공합니다.<br>
     * 
     * <p>
     * <font color="red"><b>* 데이터(V)가 <b>{@code null}</b>인 경우 '전처리 과정'에서 제외시키므로, 데이터(V)를 처리하는 함수 객체는
     * <b>{@code null}</b>을 처리하지 않아도 됩니다.</b></font>
     * </p>
     * 
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 21.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <K>
     *            데이터 식별정보 유형 (Nullable)
     * @param <V>
     *            데이터 유형 (Nullable)
     * @param <NK>
     *            새로운 데이터 식별정보 (<b>{@code NOT nullable}</b>: {@link Collectors#groupingBy} 제약사항)
     * @param <NV>
     *            새로운 데이터 유형 (Nullable). <br>
     *            단, {@code colSupplier}가 제공하는 {@link Collection} 구현체가 {@code null}을 허용해야 합니다.
     * @param <C>
     *            새로운 데이터를 담는 {@link Collection} 유형
     * @param <M>
     *            결과 {@link Map} 유형
     * @param map
     *            데이터 객체
     * @param keyMapper
     *            데이터 식별정보 제공 함수. (K &rarr; NK) <br>
     *            <font color="red"><b>주의: 반환값으로 절대 {@code null}을 제공해서는 안 됩니다.</b></font>
     * @param valueFunction
     *            데이터 변환 함수. (V &rarr; NV) <br>
     *            반환값으로 {@code null}을 제공할 수 있으나, 이 경우 {@code colSupplier}가 제공하는 컬렉션이 {@code null}을 허용해야 합니다.
     * @param mapSupplier
     *            결과 {@link Map} 객체 제공 함수.
     * @param colSupplier
     *            동일한 식별정보(NK)에 해당하는 데이터(NV)를 담는 {@link Collection} 객체 제공 함수.
     * 
     * @return 변환 및 그룹화가 완료된 새로운 Map 객체
     * 
     * @throws NullPointerException
     *             파라미터 중에 1개라도 {@code null}인 경우 발생. <br>
     *             또한 {@code keyMapper}의 반환값이 {@code null}인 경우 발생.
     *
     * @since 2025. 8. 21.
     * @version 2.1.0
     * 
     * @see Collectors#groupingBy(Function, Supplier, java.util.stream.Collector)
     * @see Collectors#mapping(Function, java.util.stream.Collector)
     */
    public static <K extends @Nullable Object, V extends @Nullable Object, NK, NV extends @Nullable Object, C extends Collection<NV>, M extends Map<NK, C>> //
            M map(Map<K, V> map, Function<Entry<K, V>, NK> keyMapper, Function<Entry<K, V>, NV> valueFunction, Supplier<M> mapSupplier, Supplier<C> colSupplier) {
        ObjectUtils.requireNonNulls(map, keyMapper, valueFunction, mapSupplier, colSupplier);

        return map.entrySet().stream() //
                .filter(entry -> entry.getValue() != null) //
                .collect(Collectors.groupingBy( //
                        entry -> keyMapper.apply(entry) //
                        , mapSupplier //
                        , Collectors.mapping( //
                                entry -> valueFunction.apply(entry), Collectors.toCollection(colSupplier)) //
                ) //
                );
    }

    /**
     * {@link Map} 데이터를 {@code 값(V)} 데이터만 새로운 유형으로 변환하여 제공합니다. <br>
     * 
     * <p>
     * <font color="red"><b>* 데이터(V)가 <b>{@code null}</b>인 경우 '전처리 과정'에서 제외시키므로, 데이터(V)를 처리하는 함수 객체는
     * <b>{@code null}</b>을 처리하지 않아도 됩니다.</b></font>
     * </p>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 20.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <K>
     *            데이터 식별정보 유형 (Nullable). <br>
     *            단, {@code mapSupplier}가 제공하는 {@link Map} 구현체가 {@code null} 키를 허용해야 합니다.
     * @param <V>
     *            데이터 유형 (Nullable)
     * @param <U>
     *            새로운 데이터 유형 (<b>{@code NOT nullable}</b>). <br>
     *            JDK 내부 제약({@link Collectors#toMap})으로 인해 {@code null}을 허용하지 않습니다.
     * @param map
     *            원본 데이터 객체
     * @param transformer
     *            새로운 객체를 제공하는 함수. (V &rarr; U) <br>
     *            <font color="red"><b>주의: 반환값으로 절대 {@code null}을 제공해서는 안 됩니다.</b></font>
     * 
     * @return 값이 변환된 새로운 Map 객체
     *
     * @throws NullPointerException
     *             파라미터중에 1개라도 {@code null}인 경우 발생. <br>
     *             또한 {@code transformer}의 반환값이 {@code null}인 경우 발생.
     *
     * @since 2025. 8. 20.
     * @version 2.1.0
     */
    // 아래 내용에 적용됨.
    // - (Supplier<Map<K, U>>) HashMap<K, U>::new
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static <K extends @Nullable Object, V extends @Nullable Object, U> Map<K, U> map(Map<K, V> map, Function<V, U> transformer) {
        // M extends Map<K, U>
        return map(map, transformer, (Supplier<Map<K, U>>) HashMap<K, U>::new);
    }

    /**
     * {@link Map} 데이터를 {@code 값(V)} 데이터만 새로운 유형으로 변환하여 제공합니다. <br>
     * 
     * <p>
     * <font color="red"><b>* 데이터(V)가 <b>{@code null}</b>인 경우 '전처리 과정'에서 제외시키므로, 데이터(V)를 처리하는 함수 객체는
     * <b>{@code null}</b>을 처리하지 않아도 됩니다.</b></font>
     * </p>
     * 
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 20.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <K>
     *            데이터 식별정보 유형 (Nullable). <br>
     *            단, {@code mapSupplier}가 제공하는 {@link Map} 구현체가 {@code null} 키를 허용해야 합니다.
     * @param <V>
     *            데이터 유형 (Nullable)
     * @param <U>
     *            새로운 데이터 유형 (<b>{@code NOT nullable}</b>). <br>
     *            JDK 내부 제약({@link Collectors#toMap})으로 인해 {@code null}을 허용하지 않습니다.
     * @param <M>
     *            결과 {@link Map} 유형
     * @param map
     *            원본 데이터 객체
     * @param transformer
     *            새로운 객체를 제공하는 함수. (V &rarr; U) <br>
     *            <font color="red"><b>주의: 반환값으로 절대 {@code null}을 제공해서는 안 됩니다.</b></font>
     * @param mapSupplier
     *            {@link Map} 제공함수.
     * 
     * @return 값이 변환된 새로운 Map 객체
     *
     * @throws NullPointerException
     *             파라미터중에 1개라도 {@code null}인 경우 발생. <br>
     *             또한 {@code transformer}의 반환값이 {@code null}인 경우 발생.
     *
     * @since 2025. 8. 20.
     * @version 2.1.0
     */
    public static <K extends @Nullable Object, V extends @Nullable Object, U, M extends Map<K, U>> M map(Map<K, V> map, Function<V, U> transformer, Supplier<M> mapSupplier) {
        ObjectUtils.requireNonNulls(map, transformer, mapSupplier);

        return map.entrySet().stream() //
                .filter(entry -> entry.getValue() != null) //
                .collect(Collectors.toMap( //
                        Map.Entry::getKey //
                        , entry -> transformer.apply(entry.getValue()) // V &rarr; U
                        , StreamUtils.throwingMerger() //
                        , mapSupplier //
                ));
    }

    /**
     * 2개의 {@link Map}에 포함된 값을 새로운 형태로 변환하여 하나의 {@link Map}로 묶어서 제공합니다. <br>
     * <p>
     * <font color="red"><b>* 데이터(V)가 <b>{@code null}</b>인 경우 '전처리 과정'에서 제외시키므로, 데이터(V)를 처리하는 함수 객체는
     * <b>{@code null}</b>을 처리하지 않아도 됩니다.</b></font>
     * </p>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 20.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <K>
     *            데이터 식별정보 유형 (Nullable). <br>
     * @param <V>
     *            스트림 원본 데이터 유형 (Nullable)
     * @param <NK>
     *            결과 {@link Map} 유형
     * @param single
     * @param multi
     * @param keyMapper
     *            객체의 식별정보를 제공하는 함수.
     * @param mergeFunction
     *            2개의 객체 정보를 하나로 병합하는 함수. (U + U &rarr; U) <br>
     *            <font color="red"><b>주의: 반환값으로 절대 {@code null}을 제공해서는 안 됩니다.</b></font>
     * @return
     *
     * @since 2025. 8. 20.
     * @version 2.1.0
     */
    // 아래 내용에 적용됨.
    // - (Supplier<Map<NK, V>>) HashMap<NK, V>::new
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static <K extends @Nullable Object, V extends @Nullable Object, NK> //
            Map<NK, V> map(Map<K, V> single, Map<K, ? extends Collection<V>> multi, Function<V, NK> keyMapper, BinaryOperator<V> mergeFunction) {
        return StreamUtils.toMap(flat(single, multi), keyMapper, StreamUtils.identity(), mergeFunction, (Supplier<Map<NK, V>>) HashMap<NK, V>::new);
    }

    /**
     * 2개의 {@link Map}에 포함된 값을 새로운 형태로 변환하여 하나의 {@link Map}로 묶어서 제공합니다. <br>
     * 단, {@code keyMapper}에 해당하는 값이 동일한 경우 {@code mergeFunction}를 통해서 객체를 하나로 병합 ('V + V &rarr; V' &rarr; U) 합니다.
     * 
     * <p>
     * <font color="red"><b>* 데이터(V)가 <b>{@code null}</b>인 경우 '전처리 과정'에서 제외시키므로, 데이터(V)를 처리하는 함수 객체는
     * <b>{@code null}</b>을 처리하지 않아도 됩니다.</b></font>
     * </p>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 20.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <K>
     *            데이터 식별정보 유형 (Nullable).
     * @param <V>
     *            데이터 유형 (Nullable).
     * @param <NK>
     *            새로운 데이터 식별정보 유형. (Nullable).
     * @param <U>
     *            새로운 데이터 유형 (Nullable).
     * @param <M>
     *            결과 {@link Map} 유형
     * @param single
     * @param multi
     * @param keyMapper
     *            객체의 식별정보를 제공하는 함수. (V &rarr; NK)
     * @param mergeFunction
     *            2개의 객체 정보를 하나로 병합하는 함수. (V + V &rarr; V) <br>
     *            <font color="red"><b>주의: 중간 처리기({@link Collectors#toMap})의 제약으로 인해 반환값으로 절대 {@code null}을 제공해서는 안
     *            됩니다.</b></font>
     * @param transformer
     *            새로운 객체를 제공하는 함수. (V &rarr; U)
     * 
     * @return
     * 
     * @throws NullPointerException
     *             파라미터중에 1개라도 {@code null}인 경우 발생.
     *
     * @since 2025. 8. 20.
     * @version 2.1.0
     */
    // 아래 내용에 적용됨.
    // - (Supplier<Map<NK, U>>) HashMap<NK, U>::new
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static <K extends @Nullable Object, V extends @Nullable Object, NK extends @Nullable Object, U extends @Nullable Object> //
            Map<NK, U> map(Map<K, V> single, Map<K, ? extends Collection<V>> multi, Function<V, NK> keyMapper, BinaryOperator<V> mergeFunction, Function<V, U> transformer) {
        return map(single, multi, keyMapper, mergeFunction, transformer, (Supplier<Map<NK, U>>) HashMap<NK, U>::new);
    }

    /**
     * 2개의 {@link Map}에 포함된 값을 새로운 형태로 변환하여 하나의 {@link Map}로 묶어서 제공합니다. <br>
     * <p>
     * <font color="red"><b>* 데이터(V)가 <b>{@code null}</b>인 경우 '전처리 과정'에서 제외시키므로, 데이터(V)를 처리하는 함수 객체는
     * <b>{@code null}</b>을 처리하지 않아도 됩니다.</b></font>
     * </p>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 20.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <K>
     *            데이터 식별정보 유형 (Nullable). <br>
     * @param <V>
     *            스트림 원본 데이터 유형 (Nullable)
     * @param <NK>
     *            새로운 데이터 식별정보 유형
     * @param <U>
     *            새로운 데이터 유형
     * @param <M>
     *            결과 {@link Map} 유형
     * @param single
     * @param multi
     * @param keyMapper
     *            객체의 식별정보를 제공하는 함수.
     * @param valueFunction
     *            새로운 객체를 제공하는 변환 함수. (V &rarr; U) <br>
     *            <font color="red"><b>주의: 반환값으로 절대 {@code null}을 제공해서는 안 됩니다.</b></font>
     * @param mergeFunction
     *            2개의 객체 정보를 하나로 병합하는 함수. (U + U &rarr; U) <br>
     *            <font color="red"><b>주의: 반환값으로 절대 {@code null}을 제공해서는 안 됩니다.</b></font>
     * @return
     *
     * @since 2025. 8. 20.
     * @version 2.1.0
     */
    // 아래 내용에 적용됨.
    // - (Supplier<Map<NK, U>>) HashMap<NK, U>::new
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static <K extends @Nullable Object, V extends @Nullable Object, NK, U, M> //
            Map<NK, U> map(Map<K, V> single, Map<K, ? extends Collection<V>> multi, Function<V, NK> keyMapper, Function<V, U> valueFunction, BinaryOperator<U> mergeFunction) {
        return map(single, multi, keyMapper, valueFunction, mergeFunction, (Supplier<Map<NK, U>>) HashMap<NK, U>::new);
    }

    /**
     * 2개의 {@link Map}에 포함된 값을 새로운 형태로 변환하여 하나의 {@link Map}로 묶어서 제공합니다. <br>
     * 단, {@code keyMapper}에 해당하는 값이 동일한 경우 {@code mergeFunction}를 통해서 객체를 하나로 병합 ('V + V &rarr; V' &rarr; U) 합니다.
     * <p>
     * <font color="red"><b>* 데이터(V)가 <b>{@code null}</b>인 경우 '전처리 과정'에서 제외시키므로, 데이터(V)를 처리하는 함수 객체는
     * <b>{@code null}</b>을 처리하지 않아도 됩니다.</b></font>
     * </p>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 20.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <K>
     *            데이터 식별정보 유형 (Nullable).
     * @param <V>
     *            데이터 유형 (Nullable).
     * @param <NK>
     *            새로운 데이터 식별정보 유형 (Nullable).<br>
     *            단, {@code mapSupplier}가 제공하는 {@link Map} 구현체가 {@code null} 키를 허용해야 합니다.
     * @param <U>
     *            새로운 데이터 유형
     * @param <M>
     *            결과 {@link Map} 유형
     * @param single
     * @param multi
     * @param keyMapper
     *            객체의 식별정보를 제공하는 함수. (V &rarr; NK)
     * @param mergeFunction
     *            2개의 객체 정보를 하나로 병합하는 함수. (V + V &rarr; V) <br>
     *            <font color="red"><b>주의: 중간 처리기({@link Collectors#toMap})의 제약으로 인해 반환값으로 절대 {@code null}을 제공해서는 안
     *            됩니다.</b></font>
     * @param transformer
     *            새로운 객체를 제공하는 함수. (V &rarr; U) <br>
     *            {@code mapSupplier}의 구현체가 지원할 경우 반환값으로 {@code null} 제공이 가능합니다.
     * @param mapSupplier
     *            최종 결과 {@link Map} 객체를 제공하는 함수.
     * 
     * @return
     * 
     * @throws NullPointerException
     *             파라미터중에 1개라도 {@code null}인 경우 발생.
     *
     * @since 2025. 8. 20.
     * @version 2.1.0
     */
    public static <K extends @Nullable Object, V extends @Nullable Object, NK extends @Nullable Object, U extends @Nullable Object, M extends Map<NK, U>, C extends Collection<V>> //
            M map(Map<K, V> single, Map<K, C> multi, Function<V, NK> keyMapper, BinaryOperator<V> mergeFunction, Function<V, U> transformer, Supplier<M> mapSupplier) {
        return StreamUtils.toMap(flat(single, multi), keyMapper, mergeFunction, transformer, mapSupplier);
    }

    /**
     * 2개의 {@link Map}에 포함된 값을 새로운 형태로 변환하여 하나의 {@link Map}로 묶어서 제공합니다. <br>
     * <p>
     * <font color="red"><b>* 데이터(V)가 <b>{@code null}</b>인 경우 '전처리 과정'에서 제외시키므로, 데이터(V)를 처리하는 함수 객체는
     * <b>{@code null}</b>을 처리하지 않아도 됩니다.</b></font>
     * </p>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 20.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <K>
     *            데이터 식별정보 유형 (Nullable). <br>
     *            단, {@code mapSupplier}가 제공하는 {@link Map} 구현체가 {@code null} 키를 허용해야 합니다.
     * @param <V>
     *            데이터 유형
     * @param <NK>
     *            새로운 데이터 식별정보 유형
     * @param <M>
     *            결과 {@link Map} 유형
     * @param single
     * @param multi
     * @param keyMapper
     *            객체의 식별정보를 제공하는 함수.
     * @param mergeFunction
     *            2개의 객체 정보를 하나로 병합하는 함수. ( V + V &rarr; V) <br>
     *            <font color="red"><b>주의: 반환값으로 절대 {@code null}을 제공해서는 안 됩니다.</b></font>
     * @param mapSupplier
     *            {@link Map} 제공함수.
     * @return
     *
     * @since 2025. 8. 20.
     * @version 2.1.0
     */
    public static <K extends @Nullable Object, V, NK, M extends Map<NK, V>, C extends Collection<V>> //
            M map(Map<K, V> single, Map<K, C> multi, Function<V, NK> keyMapper, BinaryOperator<V> mergeFunction, Supplier<M> mapSupplier) {
        return map(single, multi, keyMapper, StreamUtils.identity(), mergeFunction, mapSupplier);
    }

    /**
     * 2개의 {@link Map}에 포함된 값을 새로운 형태로 변환하여 하나의 {@link Map}로 묶어서 제공합니다. <br>
     * <p>
     * <font color="red"><b>* 데이터(V)가 <b>{@code null}</b>인 경우 '전처리 과정'에서 제외시키므로, 데이터(V)를 처리하는 함수 객체는
     * <b>{@code null}</b>을 처리하지 않아도 됩니다.</b></font>
     * </p>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 20.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <K>
     *            데이터 식별정보 유형 (Nullable). <br>
     *            단, {@code mapSupplier}가 제공하는 {@link Map} 구현체가 {@code null} 키를 허용해야 합니다.
     * @param <V>
     *            스트림 원본 데이터 유형 (Nullable)
     * @param <NK>
     *            새로운 데이터 식별정보 유형
     * @param <U>
     *            변환된 새로운 데이터 유형 (<b>{@code NOT nullable}</b>). <br>
     *            JDK 내부 제약({@link Collectors#toMap})으로 인해 {@code null}을 허용하지 않습니다.
     * @param <M>
     *            결과 {@link Map} 유형
     * @param single
     * @param multi
     * 
     * @param keyMapper
     *            객체의 식별정보를 제공하는 함수.
     * @param valueFunction
     *            새로운 객체를 제공하는 변환 함수. (V &rarr; U) <br>
     *            <font color="red"><b>주의: 반환값으로 절대 {@code null}을 제공해서는 안 됩니다.</b></font>
     * @param mergeFunction
     *            2개의 객체 정보를 하나로 병합하는 함수. (U + U &rarr; U) <br>
     *            <font color="red"><b>주의: 반환값으로 절대 {@code null}을 제공해서는 안 됩니다.</b></font>
     * @param mapSupplier
     *            {@link Map} 제공함수.
     * @return
     * 
     * @throws NullPointerException
     *             파라미터 중에 1개라도 {@code null}인 경우 발생. <br>
     *             또한 {@code valueFunction}이나 {@code mergeFunction}의 결과가 {@code null}인 경우 발생.
     *
     * @since 2025. 8. 20.
     * @version 2.1.0
     */
    public static <K extends @Nullable Object, V extends @Nullable Object, NK, U, M extends Map<NK, U>, C extends Collection<V>> //
            M map(Map<K, V> single, Map<K, C> multi, Function<V, NK> keyMapper, Function<V, U> valueFunction, BinaryOperator<U> mergeFunction, Supplier<M> mapSupplier) {
        return StreamUtils.toMap(flat(single, multi), keyMapper, valueFunction, mergeFunction, mapSupplier);
    }

    /**
     * 2개의 {@link Map}에서 동일한 키를 갖는 값을 새로운 형태의 데이터로 변환하여 새로운 {@link Map}을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2023. 12. 6.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <K>
     *            데이터 식별정보 유형 (Nullable). <br>
     *            단, 결과 맵({@code newBucket})이 {@code null} 키를 지원해야 합니다.
     * @param <V>
     *            데이터 유형 (Nullable)
     * @param <NV>
     *            새로운 값의 데이터 타입 (Nullable). <br>
     *            단, 결과 맵({@code newBucket})이 {@code null} 값을 지원해야 합니다.
     * @param bucket
     *            기존 {@link Map} (<b>{@code NOT nullable}</b>)
     * @param newData
     *            새로운 데이터 {@link Map} (<b>{@code NOT nullable}</b>)
     * @param aggrValue
     *            값 병합 함수 (V + V &rarr; NV) (<b>{@code NOT nullable}</b>).
     *            <ul>
     *            <li>첫 번째 인자: 기존 값 (<b>주의: 기존 {@code bucket}에 키가 없을 경우 {@code null}이 전달됩니다</b>)</li>
     *            <li>두 번째 인자: 새로운 값</li>
     *            <li>반환값: 병합 결과</li>
     *            </ul>
     * @param newBucket
     *            결과를 담을 새로운 {@link Map} (Nullable). <br>
     *            {@code null}로 전달할 경우 내부에서 새로운 {@link HashMap}을 생성하여 사용합니다.
     * 
     * @return 데이터가 병합된 새로운 {@link Map}
     * 
     * @throws NullPointerException
     *             필수 파라미터({@code bucket, newData, aggrValue}) 중 하나라도 {@code null}인 경우 발생.
     *
     * @since 2023. 12. 6.
     * @version 2.0.0
     */
    public static <K extends @Nullable Object, V extends @Nullable Object, NV extends @Nullable Object> Map<K, NV> merge(Map<K, V> bucket, Map<K, V> newData,
            BiFunction<V, V, NV> aggrValue, @Nullable Map<K, NV> newBucket) {

        ObjectUtils.requireNonNulls(bucket, newData, aggrValue);

        if (newBucket == null) {
            newBucket = new HashMap<>();
        }

        // 변수 스코프(Scope)를 루프 내부로 제한하여 메모리 및 가독성 최적화
        for (Entry<K, V> entry : newData.entrySet()) {
            K k = entry.getKey();
            V n = entry.getValue();

            // 기존 Map에 키가 없다면 null이 반환됨 (aggrValue에서 이를 처리해야 함)
            V o = bucket.get(k);

            // 데이터 갱신
            newBucket.put(k, aggrValue.apply(o, n));
        }

        return newBucket;
    }

    /**
     * 2개의 {@link Map}에서 동일한 키를 갖는 값을 새로운 형태의 데이터로 변환하여 새로운 {@link Map}을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2023. 12. 6.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <K>
     *            데이터 식별정보 유형 (Nullable). <br>
     *            단, 결과 맵({@code newBucket})이 {@code null} 키를 지원해야 합니다.
     * @param <V>
     *            데이터 유형 (Nullable)
     * @param bucket
     *            기존 {@link Map} (<b>{@code NOT nullable}</b>)
     * @param newData
     *            새로운 데이터 {@link Map} (<b>{@code NOT nullable}</b>)
     * @param aggrValue
     *            값 병합 함수 (V + V &rarr; NV) (<b>{@code NOT nullable}</b>).
     *            <ul>
     *            <li>첫 번째 인자: 기존 값 (<b>주의: 기존 {@code bucket}에 키가 없을 경우 {@code null}이 전달됩니다</b>)</li>
     *            <li>두 번째 인자: 새로운 값</li>
     *            <li>반환값: 병합 결과</li>
     *            </ul>
     * 
     * @return 데이터가 병합된 새로운 {@link Map}
     * 
     * @throws NullPointerException
     *             필수 파라미터({@code bucket, newData, aggrValue}) 중 하나라도 {@code null}인 경우 발생.
     * 
     * @since 2023. 12. 6.
     * @version 2.0.0
     */
    public static <K, V> void merge(Map<K, V> bucket, Map<K, V> newData, BiFunction<V, V, V> aggrValue) {
        merge(bucket, newData, aggrValue, bucket);
    }

    /**
     * 주어진 {@link Map}에서 정해진 개수만큼 데이터를 추출하여 반환합니다. <br>
     * 추출된 데이터는 원본 {@link Map}에서 삭제됩니다.
     * 
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2017. 10. 18.    parkjunhong77@gmail.com         최초 작성
     * 2026. 3. 31.     parkjunhong77@gmail.com         (3.0.0) Iterator를 이용한 성능 최적화 및 readCount 예외 방어 로직 추가
     * </pre>
     *
     * @param <K>
     *            데이터 식별정보 유형 (Nullable)
     * @param <V>
     *            데이터 유형 (Nullable)
     * @param map
     *            데이터를 추출할 원본 {@link Map} 객체 (<b>{@code NOT nullable}</b>)
     * @param readCount
     *            읽어올 데이터의 개수
     * 
     * @return 원본에서 추출된 정해진 개수만큼의 데이터를 담은 새로운 {@link Map}
     * 
     * @throws NullPointerException
     *             파라미터 {@code map}이 {@code null}인 경우 발생.
     *
     * @since 2017. 10. 18.
     * @version 3.0.0
     */
    public static <K extends @Nullable Object, V extends @Nullable Object> Map<K, V> read(Map<K, V> map, int readCount) {
        Objects.requireNonNull(map, "A parameter 'map' must not be 'null'.");

        if (readCount <= 0) {
            return new HashMap<>(0);
        }

        Map<K, V> read = new HashMap<>(Math.min(readCount, map.size()));

        if (readCount >= map.size()) {
            read.putAll(map);
            map.clear();
            return read;
        }

        // [3] Iterator를 활용한 단일 루프 최적화 (In-place removal)
        Iterator<Entry<K, V>> iterator = map.entrySet().iterator();
        int count = 0;

        while (iterator.hasNext() && count < readCount) {
            Entry<K, V> entry = iterator.next();
            read.put(entry.getKey(), entry.getValue());

            iterator.remove();
            count++;
        }

        return read;
    }

    /**
     * 2개의 {@link Map}에 포함된 값을 하나의 {@link Collection} 구현체로 묶어서 제공합니다. <br>
     * 단, {@code keyMapper}에 해당하는 값이 동일한 경우 {@code mergeFunction}를 통해서 객체를 하나로 병합합니다.<br>
     * 
     * <p>
     * <font color="red"><b>* 데이터(V)가 <b>{@code null}</b>인 경우 '전처리 과정'에서 제외시키므로, 데이터(V)를 처리하는 함수 객체는
     * <b>{@code null}</b>을 처리하지 않아도 됩니다.</b></font>
     * </p>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 20.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <K>
     *            데이터 식별정보 (Nullable).
     * @param <V>
     *            데이터 유형 (<b>{@code NOT nullable}</b>). <br>
     *            JDK 내부 제약({@link Collectors#toMap})으로 인해 {@code null}을 허용하지 않습니다.
     * @param <NK>
     *            새로운 데이터 식별정보 유형 (Nullable).
     * @param <C>
     *            결과 {@link Collection} 유형
     * @param single
     *            데이터 제공 객체. (내부의 {@code null} 요소는 전처리 과정에서 안전하게 제거됩니다)
     * @param multi
     *            데이터 제공 객체. (내부의 {@code null} 요소는 전처리 과정에서 안전하게 제거됩니다)
     * @param keyMapper
     *            객체의 식별정보를 제공하는 함수. (V &rarr; K)
     * @param mergeFunction
     *            2개의 객체 정보를 하나로 병합하는 함수. (V + V &rarr; V) <br>
     *            <font color="red"><b>주의: 반환값으로 절대 {@code null}을 제공해서는 안 됩니다.</b></font>
     * @param collectionFactory
     *            {@link Collection} 객체 제공 함수.
     * 
     * @return 중복이 병합된 결과 데이터가 담긴 새로운 컬렉션
     *
     * @throws NullPointerException
     *             파라미터중에 1개라도 {@code null}인 경우 발생.
     *
     * @since 2025. 8. 20.
     * @version 2.1.0
     */
    public static <K extends @Nullable Object, V, NK extends @Nullable Object, C extends Collection<V>> //
            C toCollection(Map<K, V> single, Map<K, ? extends Collection<V>> multi, Function<V, NK> keyMapper, BinaryOperator<V> mergeFunction, Supplier<C> collectionFactory) {
        return toCollection(single, multi, keyMapper, d -> d, mergeFunction, collectionFactory);
    }

    /**
     * 2개의 {@link Map}에 포함된 값을 하나의 {@link Collection} 구현체로 묶어서 제공합니다. <br>
     * 단, {@code keyMapper}에 해당하는 값이 동일한 경우 {@code mergeFunction}를 통해서 객체를 하나로 병합합니다. <br>
     * 
     * <p>
     * <font color="red"><b>* 데이터(V)가 <b>{@code null}</b>인 경우 '전처리 과정'에서 제외시키므로, 데이터(V)를 처리하는 함수 객체는
     * <b>{@code null}</b>을 처리하지 않아도 됩니다.</b></font>
     * </p>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 20.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <K>
     *            데이터 식별정보 (Nullable).
     * @param <V>
     *            데이터 유형 (<b>{@code NOT nullable}</b>). <br>
     *            JDK 내부 제약({@link Collectors#toMap})으로 인해 {@code null}을 허용하지 않습니다.
     * @param <NK>
     *            새로운 데이터 식별정보 유형 (Nullable).
     * @param <C>
     *            결과 {@link Collection} 유형
     * @param single
     *            데이터 제공 객체. (내부의 {@code null} 요소는 전처리 과정에서 안전하게 제거됩니다)
     * @param multi
     *            데이터 제공 객체. (내부의 {@code null} 요소는 전처리 과정에서 안전하게 제거됩니다)
     * @param keyMapper
     *            객체의 식별정보를 제공하는 함수. (V &rarr; K)
     * @param valueMapper
     *            객체의 복제 또는 새로운 객체로 제공하는 함수. (V &rarr; V) <br>
     *            <font color="red"><b>주의: 반환값으로 절대 {@code null}을 제공해서는 안 됩니다.</b></font>
     * @param mergeFunction
     *            2개의 객체 정보를 하나로 병합하는 함수. (V + V &rarr; V) <br>
     *            <font color="red"><b>주의: 반환값으로 절대 {@code null}을 제공해서는 안 됩니다.</b></font>
     * @param collectionFactory
     *            {@link Collection} 객체 제공 함수.
     * 
     * @return 중복이 병합된 결과 데이터가 담긴 새로운 컬렉션
     *
     * @throws NullPointerException
     *             파라미터중에 1개라도 {@code null}인 경우 발생.
     * 
     * @since 2025. 8. 20.
     * @version 2.1.0
     */
    public static <K extends @Nullable Object, V, NK extends @Nullable Object, C extends Collection<V>> //
            C toCollection(Map<K, V> single, Map<K, ? extends Collection<V>> multi, Function<V, NK> keyMapper, Function<V, V> valueMapper //
                    , BinaryOperator<V> mergeFunction, Supplier<C> collectionFactory) {
        return StreamUtils.toCollection(flat(single, multi), keyMapper, valueMapper, mergeFunction, collectionFactory);
    }

    /**
     * 2개의 {@link Map}에 포함된 값을 하나의 {@link Collection} 구현체로 묶어서 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 20.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <K>
     *            데이터 식별정보 유형 (Nullable)
     * @param <V>
     *            데이터 유형 (Nullable). <br>
     *            단, {@code collectionFactory}가 제공하는 {@link Collection} 구현체가 {@code null}을 허용해야 합니다.
     * @param <C>
     *            결과 {@link Collection} 유형
     * @param single
     *            단일 값을 갖는 기존 {@link Map} 객체
     * @param multi
     *            다중 값(Collection)을 갖는 새로운 데이터 {@link Map} 객체
     * @param collectionFactory
     *            결과를 담을 새로운 {@link Collection} 객체 제공 함수. <br>
     *            <font color="red"><b>주의: 반환값으로 절대 {@code null}을 제공해서는 안 됩니다.</b></font>
     * 
     * @return 두 맵의 값들이 모두 병합되어 담긴 새로운 컬렉션
     *
     * @throws NullPointerException
     *             파라미터 중에 1개라도 {@code null}인 경우 발생. <br>
     *             또한 {@code collectionFactory}의 반환값이 {@code null}인 경우 발생.
     *
     * @since 2025. 8. 20.
     * @version 2.1.0
     */
    public static <K extends @Nullable Object, V, C extends Collection<V>> //
            C toCollection(Map<K, V> single, Map<K, ? extends Collection<V>> multi, Supplier<C> collectionFactory) {
        ObjectUtils.requireNonNulls(single, multi, collectionFactory);

        return flat(single, multi).collect(Collectors.toCollection(collectionFactory));
    }

    /**
     * 2개의 {@link Map}에 포함된 값을 하나의 {@link List}로 묶어서 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 20.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <K>
     *            데이터 식별정보 유형 (Nullable)
     * @param <V>
     *            데이터 유형 (Nullable).
     * @param single
     *            단일 값을 갖는 기존 {@link Map} 객체
     * @param multi
     *            다중 값(Collection)을 갖는 새로운 데이터 {@link Map} 객체
     * 
     * @return 두 맵의 값들이 모두 병합되어 담긴 새로운 컬렉션
     *
     * @throws NullPointerException
     *             파라미터 중에 1개라도 {@code null}인 경우 발생. <br>
     *             또한 {@code collectionFactory}의 반환값이 {@code null}인 경우 발생.
     *
     * @since 2025. 8. 20.
     * @version 2.1.0
     */
    public static <K, V> List<V> toList(Map<K, V> single, Map<K, ? extends Collection<V>> multi) {
        return toCollection(single, multi, ArrayList<V>::new);
    }

    /**
     * 2개의 {@link Map}에 포함된 값을 하나의 {@link List}로 묶어서 제공합니다. <br>
     * 단, {@code keyMapper}에 해당하는 값이 동일한 경우 {@code mergeFunction}를 통해서 객체를 하나로 병합합니다.
     * 
     * <p>
     * <font color="red"><b>* 데이터(V)가 <b>{@code null}</b>인 경우 '전처리 과정'에서 제외시키므로, 데이터(V)를 처리하는 함수 객체는
     * <b>{@code null}</b>을 처리하지 않아도 됩니다.</b></font>
     * </p>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 20.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <K>
     *            데이터 식별정보 (Nullable).
     * @param <V>
     *            데이터 유형 (<b>{@code NOT nullable}</b>). <br>
     *            JDK 내부 제약({@link Collectors#toMap})으로 인해 {@code null}을 허용하지 않습니다.
     * @param <NK>
     *            새로운 데이터 식별정보 유형 (Nullable).
     * @param single
     *            데이터 제공 객체. (내부의 {@code null} 요소는 전처리 과정에서 안전하게 제거됩니다)
     * @param multi
     *            데이터 제공 객체. (내부의 {@code null} 요소는 전처리 과정에서 안전하게 제거됩니다)
     * @param keyMapper
     *            객체의 식별정보를 제공하는 함수. (V &rarr; K)
     * @param mergeFunction
     *            2개의 객체 정보를 하나로 병합하는 함수. (V + V &rarr; V) <br>
     *            <font color="red"><b>주의: 반환값으로 절대 {@code null}을 제공해서는 안 됩니다.</b></font>
     * 
     * @return 중복이 병합된 결과 데이터가 담긴 새로운 컬렉션
     *
     * @throws NullPointerException
     *             파라미터중에 1개라도 {@code null}인 경우 발생.
     *
     * @since 2025. 8. 20.
     * @version 2.1.0
     */
    public static <K extends @Nullable Object, V, NK extends @Nullable Object> //
            List<V> toList(Map<K, V> single, Map<K, ? extends Collection<V>> multi, Function<V, NK> keyMapper, BinaryOperator<V> mergeFunction) {
        return toCollection(single, multi, keyMapper, d -> d, mergeFunction, ArrayList<V>::new);
    }

    /**
     * 2개의 {@link Map}에 포함된 값을 하나의 {@link List}로 묶어서 제공합니다. <br>
     * 단, {@code keyMapper}에 해당하는 값이 동일한 경우 {@code mergeFunction}를 통해서 객체를 하나로 병합합니다. <br>
     * 
     * <p>
     * <font color="red"><b>* 데이터(V)가 <b>{@code null}</b>인 경우 '전처리 과정'에서 제외시키므로, 데이터(V)를 처리하는 함수 객체는
     * <b>{@code null}</b>을 처리하지 않아도 됩니다.</b></font>
     * </p>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 20.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <K>
     *            데이터 식별정보 (Nullable).
     * @param <V>
     *            데이터 유형 (<b>{@code NOT nullable}</b>). <br>
     *            JDK 내부 제약({@link Collectors#toMap})으로 인해 {@code null}을 허용하지 않습니다.
     * @param <NK>
     *            새로운 데이터 식별정보 유형 (Nullable).
     * @param <C>
     *            결과 {@link Collection} 유형
     * @param single
     *            데이터 제공 객체. (내부의 {@code null} 요소는 전처리 과정에서 안전하게 제거됩니다)
     * @param multi
     *            데이터 제공 객체. (내부의 {@code null} 요소는 전처리 과정에서 안전하게 제거됩니다)
     * @param keyMapper
     *            객체의 식별정보를 제공하는 함수. (V &rarr; K)
     * @param mergeFunction
     *            2개의 객체 정보를 하나로 병합하는 함수. (V + V &rarr; V) <br>
     *            <font color="red"><b>주의: 반환값으로 절대 {@code null}을 제공해서는 안 됩니다.</b></font>
     * @param listFactory
     *            {@link List} 객체 제공 함수.
     * 
     * @return 중복이 병합된 결과 데이터가 담긴 새로운 컬렉션
     *
     * @throws NullPointerException
     *             파라미터중에 1개라도 {@code null}인 경우 발생.
     * 
     * @since 2025. 8. 20.
     * @version 2.1.0
     */
    public static <K extends @Nullable Object, V, NK extends @Nullable Object, L extends List<V>> //
            L toList(Map<K, V> single, Map<K, ? extends Collection<V>> multi, Function<V, NK> keyMapper, BinaryOperator<V> mergeFunction, Supplier<L> listFactory) {
        return toCollection(single, multi, keyMapper, d -> d, mergeFunction, listFactory);
    }

    /**
     * 2개의 {@link Map}에 포함된 값을 하나의 {@link List}로 묶어서 제공합니다. <br>
     * 단, {@code keyMapper}에 해당하는 값이 동일한 경우 {@code mergeFunction}를 통해서 객체를 하나로 병합합니다. <br>
     * 
     * <p>
     * <font color="red"><b>* 데이터(V)가 <b>{@code null}</b>인 경우 '전처리 과정'에서 제외시키므로, 데이터(V)를 처리하는 함수 객체는
     * <b>{@code null}</b>을 처리하지 않아도 됩니다.</b></font>
     * </p>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 20.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <K>
     *            데이터 식별정보 (Nullable).
     * @param <V>
     *            데이터 유형 (<b>{@code NOT nullable}</b>). <br>
     *            JDK 내부 제약({@link Collectors#toMap})으로 인해 {@code null}을 허용하지 않습니다.
     * @param <NK>
     *            새로운 데이터 식별정보 유형 (Nullable).
     * @param single
     *            데이터 제공 객체. (내부의 {@code null} 요소는 전처리 과정에서 안전하게 제거됩니다)
     * @param multi
     *            데이터 제공 객체. (내부의 {@code null} 요소는 전처리 과정에서 안전하게 제거됩니다)
     * @param keyMapper
     *            객체의 식별정보를 제공하는 함수. (V &rarr; K)
     * @param valueMapper
     *            객체의 복제 또는 새로운 객체로 제공하는 함수. (V &rarr; V) <br>
     *            <font color="red"><b>주의: 반환값으로 절대 {@code null}을 제공해서는 안 됩니다.</b></font>
     * @param mergeFunction
     *            2개의 객체 정보를 하나로 병합하는 함수. (V + V &rarr; V) <br>
     *            <font color="red"><b>주의: 반환값으로 절대 {@code null}을 제공해서는 안 됩니다.</b></font>
     * 
     * @return 중복이 병합된 결과 데이터가 담긴 새로운 컬렉션
     * 
     * @throws NullPointerException
     *             파라미터중에 1개라도 {@code null}인 경우 발생.
     *
     * @since 2025. 8. 20.
     * @version 2.1.0
     */
    public static <K extends @Nullable Object, V, NK extends @Nullable Object> //
            List<V> toList(Map<K, V> single, Map<K, ? extends Collection<V>> multi, Function<V, NK> keyMapper, Function<V, V> valueMapper, BinaryOperator<V> mergeFunction) {
        return toCollection(single, multi, keyMapper, valueMapper, mergeFunction, ArrayList<V>::new);
    }

    /**
     * 2개의 {@link Map}에 포함된 값을 하나의 {@link List}로 묶어서 제공합니다. <br>
     * 단, {@code keyMapper}에 해당하는 값이 동일한 경우 {@code mergeFunction}를 통해서 객체를 하나로 병합합니다. <br>
     * 
     * <p>
     * <font color="red"><b>* 데이터(V)가 <b>{@code null}</b>인 경우 '전처리 과정'에서 제외시키므로, 데이터(V)를 처리하는 함수 객체는
     * <b>{@code null}</b>을 처리하지 않아도 됩니다.</b></font>
     * </p>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 20.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <K>
     *            데이터 식별정보 (Nullable).
     * @param <V>
     *            데이터 유형 (<b>{@code NOT nullable}</b>). <br>
     *            JDK 내부 제약({@link Collectors#toMap})으로 인해 {@code null}을 허용하지 않습니다.
     * @param <NK>
     *            새로운 데이터 식별정보 유형 (Nullable).
     * @param <L>
     *            결과 {@link List} 유형
     * @param single
     *            데이터 제공 객체. (내부의 {@code null} 요소는 전처리 과정에서 안전하게 제거됩니다)
     * @param multi
     *            데이터 제공 객체. (내부의 {@code null} 요소는 전처리 과정에서 안전하게 제거됩니다)
     * @param keyMapper
     *            객체의 식별정보를 제공하는 함수. (V &rarr; K)
     * @param valueMapper
     *            객체의 복제 또는 새로운 객체로 제공하는 함수. (V &rarr; V) <br>
     *            <font color="red"><b>주의: 반환값으로 절대 {@code null}을 제공해서는 안 됩니다.</b></font>
     * @param mergeFunction
     *            2개의 객체 정보를 하나로 병합하는 함수. (V + V &rarr; V) <br>
     *            <font color="red"><b>주의: 반환값으로 절대 {@code null}을 제공해서는 안 됩니다.</b></font>
     * @param listFactory
     *            {@link List} 객체를 제공하는 함수.
     * 
     * @return 중복이 병합된 결과 데이터가 담긴 새로운 컬렉션
     *
     * @throws NullPointerException
     *             파라미터중에 1개라도 {@code null}인 경우 발생.
     *
     * @since 2025. 8. 20.
     * @version 2.1.0
     */
    public static <K extends @Nullable Object, V, NK extends @Nullable Object, L extends List<V>> //
            L toList(Map<K, V> single, Map<K, ? extends Collection<V>> multi, Function<V, NK> keyMapper, Function<V, V> valueMapper//
                    , BinaryOperator<V> mergeFunction, Supplier<L> listFactory) {
        return toCollection(single, multi, keyMapper, valueMapper, mergeFunction, listFactory);
    }

    /**
     * 2개의 {@link Map}에 포함된 값을 하나의 {@link Set}로 묶어서 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 20.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <K>
     *            데이터 식별정보 유형 (Nullable)
     * @param <V>
     *            데이터 유형 (Nullable).
     * @param single
     *            단일 값을 갖는 기존 {@link Map} 객체
     * @param multi
     *            다중 값(Collection)을 갖는 새로운 데이터 {@link Map} 객체
     * 
     * @return 두 맵의 값들이 모두 병합되어 담긴 새로운 컬렉션
     *
     * @throws NullPointerException
     *             파라미터 중에 1개라도 {@code null}인 경우 발생. <br>
     *             또한 {@code collectionFactory}의 반환값이 {@code null}인 경우 발생.
     *
     * @since 2025. 8. 20.
     * @version 2.1.0
     */
    // 아래 내용에 적용됨.
    // - (Supplier<Set<V>>) HashSet<V>::new
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static <K, V> Set<V> toSet(Map<K, V> single, Map<K, ? extends Collection<V>> multi) {
        return toCollection(single, multi, (Supplier<Set<V>>) HashSet<V>::new);
    }

    /**
     * 2개의 {@link Map}에 포함된 값을 하나의 {@link Set}로 묶어서 제공합니다. <br>
     * 단, {@code keyMapper}에 해당하는 값이 동일한 경우 {@code mergeFunction}를 통해서 객체를 하나로 병합합니다.
     * 
     * <p>
     * <font color="red"><b>* 데이터(V)가 <b>{@code null}</b>인 경우 '전처리 과정'에서 제외시키므로, 데이터(V)를 처리하는 함수 객체는
     * <b>{@code null}</b>을 처리하지 않아도 됩니다.</b></font>
     * </p>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 20.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <K>
     *            데이터 식별정보 (Nullable).
     * @param <V>
     *            데이터 유형 (<b>{@code NOT nullable}</b>). <br>
     *            JDK 내부 제약({@link Collectors#toMap})으로 인해 {@code null}을 허용하지 않습니다.
     * @param <NK>
     *            새로운 데이터 식별정보 유형 (Nullable).
     * @param <C>
     *            결과 {@link Collection} 유형
     * @param single
     *            데이터 제공 객체. (내부의 {@code null} 요소는 전처리 과정에서 안전하게 제거됩니다)
     * @param multi
     *            데이터 제공 객체. (내부의 {@code null} 요소는 전처리 과정에서 안전하게 제거됩니다)
     * @param keyMapper
     *            객체의 식별정보를 제공하는 함수. (V &rarr; K)
     * @param mergeFunction
     *            2개의 객체 정보를 하나로 병합하는 함수. (V + V &rarr; V) <br>
     *            <font color="red"><b>주의: 반환값으로 절대 {@code null}을 제공해서는 안 됩니다.</b></font>
     * 
     * @return 중복이 병합된 결과 데이터가 담긴 새로운 컬렉션
     *
     * @throws NullPointerException
     *             파라미터중에 1개라도 {@code null}인 경우 발생.
     *
     * @since 2025. 8. 20.
     * @version 2.1.0
     */
    // 아래 내용에 적용됨.
    // - (Supplier<Set<V>>) HashSet<V>::new
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static <K extends @Nullable Object, V, NK extends @Nullable Object> Set<V> toSet(Map<K, V> single, Map<K, ? extends Collection<V>> multi, Function<V, NK> keyMapper,
            BinaryOperator<V> mergeFunction) {
        return toCollection(single, multi, keyMapper, d -> d, mergeFunction, (Supplier<Set<V>>) HashSet<V>::new);
    }

    /**
     * 2개의 {@link Map}에 포함된 값을 하나의 {@link Set}로 묶어서 제공합니다. <br>
     * 단, {@code keyMapper}에 해당하는 값이 동일한 경우 {@code mergeFunction}를 통해서 객체를 하나로 병합합니다. <br>
     * 
     * <p>
     * <font color="red"><b>* 데이터(V)가 <b>{@code null}</b>인 경우 '전처리 과정'에서 제외시키므로, 데이터(V)를 처리하는 함수 객체는
     * <b>{@code null}</b>을 처리하지 않아도 됩니다.</b></font>
     * </p>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 20.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <K>
     *            데이터 식별정보 (Nullable).
     * @param <V>
     *            데이터 유형 (<b>{@code NOT nullable}</b>). <br>
     *            JDK 내부 제약({@link Collectors#toMap})으로 인해 {@code null}을 허용하지 않습니다.
     * @param <NK>
     *            새로운 데이터 식별정보 유형 (Nullable).
     * @param <S>
     *            결과 {@link Set} 유형
     * @param single
     *            데이터 제공 객체. (내부의 {@code null} 요소는 전처리 과정에서 안전하게 제거됩니다)
     * @param multi
     *            데이터 제공 객체. (내부의 {@code null} 요소는 전처리 과정에서 안전하게 제거됩니다)
     * @param keyMapper
     *            객체의 식별정보를 제공하는 함수. (V &rarr; K)
     * @param mergeFunction
     *            2개의 객체 정보를 하나로 병합하는 함수. (V + V &rarr; V) <br>
     *            <font color="red"><b>주의: 반환값으로 절대 {@code null}을 제공해서는 안 됩니다.</b></font>
     * @param setFactory
     *            {@link Set} 객체를 제공하는 함수.
     * 
     * @return 중복이 병합된 결과 데이터가 담긴 새로운 컬렉션
     *
     * @throws NullPointerException
     *             파라미터중에 1개라도 {@code null}인 경우 발생.
     *
     * @since 2025. 8. 20.
     * @version 2.1.0
     */
    public static <K extends @Nullable Object, V, NK extends @Nullable Object, S extends Set<V>> S toSet(Map<K, V> single, Map<K, ? extends Collection<V>> multi,
            Function<V, NK> keyMapper, BinaryOperator<V> mergeFunction, Supplier<S> setFactory) {
        return toCollection(single, multi, keyMapper, d -> d, mergeFunction, setFactory);
    }

    /**
     * 2개의 {@link Map}에 포함된 값을 하나의 {@link Set}로 묶어서 제공합니다. <br>
     * 단, {@code keyMapper}에 해당하는 값이 동일한 경우 {@code mergeFunction}를 통해서 객체를 하나로 병합합니다. <br>
     * 
     * <p>
     * <font color="red"><b>* 데이터(V)가 <b>{@code null}</b>인 경우 '전처리 과정'에서 제외시키므로, 데이터(V)를 처리하는 함수 객체는
     * <b>{@code null}</b>을 처리하지 않아도 됩니다.</b></font>
     * </p>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 20.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     *
     * @param <K>
     *            데이터 식별정보 (Nullable).
     * @param <V>
     *            데이터 유형 (<b>{@code NOT nullable}</b>). <br>
     *            JDK 내부 제약({@link Collectors#toMap})으로 인해 {@code null}을 허용하지 않습니다.
     * @param <NK>
     *            새로운 데이터 식별정보 유형 (Nullable).
     * @param single
     *            데이터 제공 객체. (내부의 {@code null} 요소는 전처리 과정에서 안전하게 제거됩니다)
     * @param multi
     *            데이터 제공 객체. (내부의 {@code null} 요소는 전처리 과정에서 안전하게 제거됩니다)
     * @param keyMapper
     *            객체의 식별정보를 제공하는 함수. (V &rarr; K)
     * @param valueMapper
     *            객체의 복제 또는 새로운 객체로 제공하는 함수. (V &rarr; V) <br>
     *            <font color="red"><b>주의: 반환값으로 절대 {@code null}을 제공해서는 안 됩니다.</b></font>
     * @param mergeFunction
     *            2개의 객체 정보를 하나로 병합하는 함수. (V + V &rarr; V) <br>
     *            <font color="red"><b>주의: 반환값으로 절대 {@code null}을 제공해서는 안 됩니다.</b></font>
     * 
     * @return 중복이 병합된 결과 데이터가 담긴 새로운 컬렉션
     *
     * @throws NullPointerException
     *             파라미터중에 1개라도 {@code null}인 경우 발생.
     *
     * @since 2025. 8. 20.
     * @version 2.1.0
     */
    // 아래 내용에 적용됨.
    // - (Supplier<Set<V>>) HashSet<V>::new
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static <K extends @Nullable Object, V, NK extends @Nullable Object> Set<V> toSet(Map<K, V> single, Map<K, ? extends Collection<V>> multi, Function<V, NK> keyMapper,
            Function<V, V> valueMapper, BinaryOperator<V> mergeFunction) {
        return toCollection(single, multi, keyMapper, valueMapper, mergeFunction, (Supplier<Set<V>>) HashSet<V>::new);
    }

    /**
     * 2개의 {@link Map}에 포함된 값을 하나의 {@link Set}로 묶어서 제공합니다. <br>
     * 단, {@code keyMapper}에 해당하는 값이 동일한 경우 {@code mergeFunction}를 통해서 객체를 하나로 병합합니다. <br>
     * 
     * <p>
     * <font color="red"><b>* 데이터(V)가 <b>{@code null}</b>인 경우 '전처리 과정'에서 제외시키므로, 데이터(V)를 처리하는 함수 객체는
     * <b>{@code null}</b>을 처리하지 않아도 됩니다.</b></font>
     * </p>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 20.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <K>
     *            데이터 식별정보 (Nullable).
     * @param <V>
     *            데이터 유형 (<b>{@code NOT nullable}</b>). <br>
     *            JDK 내부 제약({@link Collectors#toMap})으로 인해 {@code null}을 허용하지 않습니다.
     * @param <NK>
     *            새로운 데이터 식별정보 유형 (Nullable).
     * @param <S>
     *            결과 {@link Set} 유형
     * @param single
     *            데이터 제공 객체. (내부의 {@code null} 요소는 전처리 과정에서 안전하게 제거됩니다)
     * @param multi
     *            데이터 제공 객체. (내부의 {@code null} 요소는 전처리 과정에서 안전하게 제거됩니다)
     * @param keyMapper
     *            객체의 식별정보를 제공하는 함수. (V &rarr; K)
     * @param valueMapper
     *            객체의 복제 또는 새로운 객체로 제공하는 함수. (V &rarr; V) <br>
     *            <font color="red"><b>주의: 반환값으로 절대 {@code null}을 제공해서는 안 됩니다.</b></font>
     * @param mergeFunction
     *            2개의 객체 정보를 하나로 병합하는 함수. (V + V &rarr; V) <br>
     *            <font color="red"><b>주의: 반환값으로 절대 {@code null}을 제공해서는 안 됩니다.</b></font>
     * @param setFactory
     *            {@link Set} 객체를 제공하는 함수.
     * 
     * @return 중복이 병합된 결과 데이터가 담긴 새로운 컬렉션
     *
     * @throws NullPointerException
     *             파라미터중에 1개라도 {@code null}인 경우 발생.
     *
     * @since 2025. 8. 20.
     * @version 2.1.0
     */
    public static <K, V, NK, S extends Set<V>> S toSet(Map<K, V> single, Map<K, ? extends Collection<V>> multi, Function<V, NK> keyMapper, Function<V, V> valueMapper,
            BinaryOperator<V> mergeFunction, Supplier<S> setFactory) {
        return toCollection(single, multi, keyMapper, valueMapper, mergeFunction, setFactory);
    }

}
