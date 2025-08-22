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
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 
 * @since 2015. 1. 6.
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public class MapUtils {

    @SuppressWarnings("rawtypes")
    public static void clear(Map... maps) {
        for (Map map : maps) {
            if (map != null) {
                map.clear();
            }
        }
    }

    /**
     * 2개이 {@link Map}을 하나의 {@link List}로 병합하여 {@link Stream} 형태로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 20.     박준홍         최초 작성
     * </pre>
     *
     * @param <V>
     *            데이터 유형
     * @param single
     * @param multi
     * @return
     *
     * @since 2025. 8. 20.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <K, V> Stream<V> flat(Map<K, V> single, Map<K, List<V>> multi) {
        AssertUtils2.notNulls(single, multi);
        return Stream //
                .of(single.values().stream() //
                        , multi.values().stream().flatMap(List::stream) //
                ) //
                .flatMap(s -> s);
    }

    /**
     * {@link Map}에서 주어진 <code>key</code>에 해당하는 값을 제공합니다.<br>
     * 존재하지 않는 경우 기본값(<code>defaultValue</code>)을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 3. 29.     박준홍         최초 작성
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
     * @return
     *
     * @since 1.7.0
     * @since 2020. 10. 23.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <K, V> V getOrDefault(Map<K, V> map, K key, Supplier<V> defaultValue, boolean insertIfNot) {

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
     * {@link Map}에서 주어진 <code>key</code>에 해당하는 값을 제공합니다.<br>
     * 존재하지 않는 경우 기본값(<code>defaultValue</code>)을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 3. 29.     박준홍         최초 작성
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
     * @since v1.6.18
     * @since 2020. 3. 29.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @see Map#getOrDefault(Object, Object)
     */
    public static <K, V> V getOrDefault(Map<K, V> map, K key, V defaultValue, boolean insertIfNot) {
        V v = map.getOrDefault(key, defaultValue);

        if (!map.containsKey(key) && insertIfNot) {
            map.put(key, v);
        }

        return v;
    }

    /**
     * 주어진 {@link Map} 객체가 <code>null</code>이거나 비어 있는지 ({@link Map#isEmpty()}) 여부를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 8.		박준홍			최초 작성
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
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <K, V> boolean isNullOrEmpty(Map<K, V> map) {
        return map == null || map.size() < 1;
    }

    /**
     * {@link Map}에 포함된 데이터를 주어진 정보에 맞게 처리하여 새로운 {@link Map}을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 6. 16.		박준홍			최초 작성
     * </pre>
     *
     * @param <K>
     *            데이터 식별정보 유형
     * @param <V>
     *            데이터 유형
     * @param <NK>
     *            새로운 데이터 식별정보
     * @param <NV>
     *            새로운 데이터 유형
     * @param <C>
     *            새로운 데이터를 담는 {@link Collection} 유형
     * @param <M>
     *            결과 {@link Map} 유형 결과 {@link Map} 유형
     * @param src
     *            원본 데이터
     * @param keyGen
     * @param valueGen
     * @param mapClass
     * @param colClass
     * @return
     * @throws RuntimeException
     *             {@link Map} 객체 또는 {@link Collection} 객체 생성을 실패했을 경우
     *
     * @since 2021. 6. 16.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @deprecated 대체 메소드: {@link #map(Map, Function, Function, Supplier, Supplier)}.<br>
     *             <font color="red">다음 배포시 삭제 예정</font>
     */
    public static <K, V, NK, NV, C extends Collection<NV>, M extends Map<NK, C>> M map(Map<K, V> src, Function<Entry<K, V>, NK> keyGen, Function<Entry<K, V>, NV> valueGen,
            Class<M> mapClass, Class<C> colClass) throws RuntimeException {

        M newMap = null;
        NK nk = null;

        try {
            Supplier<C> dnv = () -> {
                try {
                    return (C) colClass.newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    throw ExceptionUtils.newException(RuntimeException.class, e, "Collection 객체 생성 도중 에러가 발생하였습니다. 원인=%s", e.getMessage());
                }
            };

            newMap = mapClass.newInstance();
            for (Entry<K, V> entry : src.entrySet()) {
                nk = keyGen.apply(entry);
                if (nk == null) {
                    continue;
                }

                getOrDefault(newMap, nk, dnv, true) //
                        .add(valueGen.apply(entry));
            }
            return newMap;
        } catch (InstantiationException | IllegalAccessException e) {
            throw ExceptionUtils.newException(RuntimeException.class, e, "Map 객체 생성 중 에러가 발생하였습니다.");
        }
    }

    /**
     * {@link Map} 데이터를 새로운 식별정보(<code>keyMapper</code>), 새로운 유형(<code>valueFunction</code>)로 변환하여 새로운 {@link Map}를
     * 제공합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 21.     박준홍         최초 작성
     * </pre>
     *
     * @param <K>
     *            데이터 식별정보 유형
     * @param <V>
     *            데이터 유형
     * @param <NK>
     *            새로운 데이터 식별정보
     * @param <NV>
     *            새로운 데이터 유형
     * @param <M>
     *            결과 {@link Map} 유형 결과 {@link Map} 유형
     * @param map
     *            데이터 객체
     * @param keyMapper
     *            데이터 식별정보 제공 함수. (K => NK)
     * @param valueFunction
     *            데이터 변환 함수. (V => NV)
     * @param mapSupplier
     *            결과 {@link Map} 객체 제공 함수.
     * @return
     *
     * @since 2025. 8. 21.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <K, V, NK, NV, M extends Map<NK, List<NV>>> M map(Map<K, V> map, Function<Entry<K, V>, NK> keyMapper, Function<Entry<K, V>, NV> valueFunction,
            Supplier<M> mapSupplier) {
        return map(map, keyMapper, valueFunction, mapSupplier, (Supplier<List<NV>>) ArrayList::new);
    }

    /**
     * {@link Map} 데이터를 새로운 식별정보(<code>keyMapper</code>), 새로운 유형(<code>valueFunction</code>)로 변환하여 새로운 {@link Map}를
     * 제공합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 21.		박준홍			최초 작성
     * </pre>
     *
     * @param <K>
     *            데이터 식별정보 유형
     * @param <V>
     *            데이터 유형
     * @param <NK>
     *            새로운 데이터 식별정보
     * @param <NV>
     *            새로운 데이터 유형
     * @param <C>
     *            새로운 데이터를 담는 {@link Collection} 유형
     * @param <M>
     *            결과 {@link Map} 유형
     * @param map
     *            데이터 객체
     * @param keyMapper
     *            데이터 식별정보 제공 함수. (K => NK)
     * @param valueFunction
     *            데이터 변환 함수. (V => NV)
     * @param mapSupplier
     *            결과 {@link Map} 객체 제공 함수.
     * @param colSupplier
     *            동일한 식별정보(NK)에 해당하는 데이터(NK)를 담는 {@link Collection} 객체 제공 함수.
     * @return
     *
     * @since 2025. 8. 21.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <K, V, NK, NV, C extends Collection<NV>, M extends Map<NK, C>> M map(Map<K, V> map, Function<Entry<K, V>, NK> keyMapper, Function<Entry<K, V>, NV> valueFunction,
            Supplier<M> mapSupplier, Supplier<C> colSupplier) {
        AssertUtils2.notNulls(map, keyMapper, valueFunction, mapSupplier, colSupplier);
        return map.entrySet().stream() //
                .collect(Collectors.groupingBy( //
                        entry -> keyMapper.apply(entry) //
                        , mapSupplier //
                        , Collectors.mapping( //
                                entry -> valueFunction.apply(entry), Collectors.toCollection(colSupplier)) //
                ) //
                );
    }

    /**
     * {@link Map} 데이터를 <code>값(V)</code> 데이터만 새로운 유형으로 변환하여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 20.     박준홍         최초 작성
     * </pre>
     *
     * @param <K>
     *            데이터 식별정보 유형
     * @param <V>
     *            데이터 유형
     * @param <U>
     *            새로운 데이터 유형
     * @param map
     * @param transformer
     *            새로운 객체를 제공하는 함수. (V => U)
     * @return
     *
     * @since 2025. 8. 20.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <K, V, U> Map<K, U> map(Map<K, V> map, Function<V, U> transformer) {
        return map(map, transformer, (Supplier<Map<K, U>>) HashMap<K, U>::new);
    }

    /**
     * {@link Map} 데이터를 <code>값(V)</code> 데이터만 새로운 유형으로 변환하여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 20.     박준홍         최초 작성
     * </pre>
     *
     * @param <K>
     *            데이터 식별정보 유형
     * @param <V>
     *            데이터 유형
     * @param <U>
     *            새로운 데이터 유형
     * @param <M>
     *            결과 {@link Map} 유형
     * @param map
     * @param transformer
     *            새로운 객체를 제공하는 함수. (V => U)
     * @param mapSupplier
     *            {@link Map} 제공함수.
     * @return
     *
     * @since 2025. 8. 20.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <K, V, U, M extends Map<K, U>> M map(Map<K, V> map, Function<V, U> transformer, Supplier<M> mapSupplier) {
        return map.entrySet().stream() //
                .collect(Collectors.toMap( //
                        Map.Entry::getKey //
                        , entry -> transformer.apply(entry.getValue()) // V => U
                        , StreamUtils.throwingMerger() //
                        , mapSupplier //
                ));
    }

    /**
     * 2개의 {@link Map}에 포함된 값을 새로운 형태로 변환하여 하나의 {@link Map}로 묶어서 제공합니다. <br>
     * 단, <code>keyMapper</code>에 해당하는 값이 동일한 경우 <code>mergeFunction</code>를 통해서 객체를 하나로 병합 (V + V => V) 합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 20.     박준홍         최초 작성
     * </pre>
     *
     * @param <NK>
     *            데이터 식별정보 유형
     * @param <V>
     *            데이터 유형
     * @param <M>
     *            결과 {@link Map} 유형
     * @param single
     * @param multi
     * @param keyMapper
     *            객체의 식별정보를 제공하는 함수.
     * @param mergeFunction
     *            2개의 객체 정보를 하나로 병합하는 함수. (V + V => V)
     * @return
     *
     * @since 2025. 8. 20.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <K, V, NK> Map<NK, V> map(Map<K, V> single, Map<K, List<V>> multi, Function<V, NK> keyMapper, BinaryOperator<V> mergeFunction) {
        return StreamUtils.toMap(flat(single, multi), keyMapper, d -> d, mergeFunction, (Supplier<Map<NK, V>>) HashMap<NK, V>::new);
    }

    /**
     * 2개의 {@link Map}에 포함된 값을 새로운 형태로 변환하여 하나의 {@link Map}로 묶어서 제공합니다. <br>
     * 단, <code>keyMapper</code>에 해당하는 값이 동일한 경우 <code>mergeFunction</code>를 통해서 객체를 하나로 병합 ('V + V => V' => U) 합니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 20.     박준홍         최초 작성
     * </pre>
     *
     * @param <NK>
     *            데이터 식별정보 유형
     * @param <V>
     *            데이터 유형
     * @param <U>
     *            새로운 데이터 유형
     * @param <M>
     *            결과 {@link Map} 유형
     * @param single
     * @param multi
     * @param keyMapper
     *            객체의 식별정보를 제공하는 함수.
     * @param mergeFunction
     *            2개의 객체 정보를 하나로 병합하는 함수. (V + V => V)
     * @param transformer
     *            새로운 객체를 제공하는 함수. (V => U)
     * @return
     *
     * @since 2025. 8. 20.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <K, V, NK, U> Map<NK, U> map(Map<K, V> single, Map<K, List<V>> multi, Function<V, NK> keyMapper, BinaryOperator<V> mergeFunction, Function<V, U> transformer) {
        return map(single, multi, keyMapper, mergeFunction, transformer, (Supplier<Map<NK, U>>) HashMap<NK, U>::new);
    }

    /**
     * 2개의 {@link Map}에 포함된 값을 새로운 형태로 변환하여 하나의 {@link Map}로 묶어서 제공합니다. <br>
     * 단, <code>keyMapper</code>에 해당하는 값이 동일한 경우 <code>mergeFunction</code>를 통해서 객체를 하나로 병합 ('V + V => V' => U) 합니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 20.     박준홍         최초 작성
     * </pre>
     *
     * @param <NK>
     *            데이터 식별정보 유형
     * @param <V>
     *            데이터 유형
     * @param <U>
     *            새로운 데이터 유형
     * @param <M>
     *            결과 {@link Map} 유형
     * @param single
     * @param multi
     * @param keyMapper
     *            객체의 식별정보를 제공하는 함수.
     * @param mergeFunction
     *            2개의 객체 정보를 하나로 병합하는 함수. (V + V => V)
     * @param transformer
     *            새로운 객체를 제공하는 함수. (V => U)
     * @param mapSupplier
     *            {@link Map} 제공함수.
     * @return
     *
     * @since 2025. 8. 20.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <K, V, NK, U, M extends Map<NK, U>> M map(Map<K, V> single, Map<K, List<V>> multi, Function<V, NK> keyMapper, BinaryOperator<V> mergeFunction,
            Function<V, U> transformer, Supplier<M> mapSupplier) {
        return StreamUtils.toMap(flat(single, multi), keyMapper, mergeFunction, transformer, mapSupplier);
    }

    /**
     * 2개의 {@link Map}에 포함된 값을 새로운 형태로 변환하여 하나의 {@link Map}로 묶어서 제공합니다. <br>
     * 단, <code>keyMapper</code>에 해당하는 값이 동일한 경우 <code>mergeFunction</code>를 통해서 객체를 하나로 병합 (V + V => V) 합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 20.     박준홍         최초 작성
     * </pre>
     *
     * @param <NK>
     *            데이터 식별정보 유형
     * @param <V>
     *            데이터 유형
     * @param <M>
     *            결과 {@link Map} 유형
     * @param single
     * @param multi
     * @param keyMapper
     *            객체의 식별정보를 제공하는 함수.
     * @param mergeFunction
     *            2개의 객체 정보를 하나로 병합하는 함수. ( V + V => V)
     * @param mapSupplier
     *            {@link Map} 제공함수.
     * @return
     *
     * @since 2025. 8. 20.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <K, V, NK, M extends Map<NK, V>> M map(Map<K, V> single, Map<K, List<V>> multi, Function<V, NK> keyMapper, BinaryOperator<V> mergeFunction,
            Supplier<M> mapSupplier) {
        return map(single, multi, keyMapper, d -> d, mergeFunction, mapSupplier);
    }

    /**
     * 2개의 {@link Map}에 포함된 값을 새로운 형태로 변환하여 하나의 {@link Map}로 묶어서 제공합니다. <br>
     * 단, <code>keyMapper</code>에 해당하는 값이 동일한 경우 <code>mergeFunction</code>를 통해서 객체를 하나로 병합 ('V => U' + U => U) 합니다.
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 20.     박준홍         최초 작성
     * </pre>
     *
     * @param <NK>
     *            데이터 식별정보 유형
     * @param <V>
     *            데이터 유형
     * @param <U>
     *            새로운 데이터 유형
     * @param <M>
     *            결과 {@link Map} 유형
     * @param single
     * @param multi
     * @param keyMapper
     *            객체의 식별정보를 제공하는 함수.
     * @param valueFunction
     *            새로운 객체를 제공하는 함수. (V => U)
     * @param mergeFunction
     *            2개의 객체 정보를 하나로 병합하는 함수. ( U + U => U)
     * @return
     *
     * @since 2025. 8. 20.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <K, V, NK, U, M> Map<NK, U> map(Map<K, V> single, Map<K, List<V>> multi, Function<V, NK> keyMapper, Function<V, U> valueFunction,
            BinaryOperator<U> mergeFunction) {
        return map(single, multi, keyMapper, valueFunction, mergeFunction, (Supplier<Map<NK, U>>) HashMap<NK, U>::new);
    }

    /**
     * 2개의 {@link Map}에 포함된 값을 새로운 형태로 변환하여 하나의 {@link Map}로 묶어서 제공합니다. <br>
     * 단, <code>keyMapper</code>에 해당하는 값이 동일한 경우 <code>mergeFunction</code>를 통해서 객체를 하나로 병합 ( 'V => U' + U => U) 합니다.
     * <br>
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 20.     박준홍         최초 작성
     * </pre>
     *
     * @param <NK>
     *            데이터 식별정보 유형
     * @param <V>
     *            데이터 유형
     * @param <U>
     *            새로운 데이터 유형
     * @param <M>
     *            결과 {@link Map} 유형
     * @param single
     * @param multi
     * @param keyMapper
     *            객체의 식별정보를 제공하는 함수.
     * @param valueFunction
     *            새로운 객체를 제공하는 함수. (V => U)
     * @param mergeFunction
     *            2개의 객체 정보를 하나로 병합하는 함수. (U + U => U)
     * @param mapSupplier
     *            {@link Map} 제공함수.
     * @return
     *
     * @since 2025. 8. 20.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <K, V, NK, U, M extends Map<NK, U>> M map(Map<K, V> single, Map<K, List<V>> multi, Function<V, NK> keyMapper, Function<V, U> valueFunction,
            BinaryOperator<U> mergeFunction, Supplier<M> mapSupplier) {
        return StreamUtils.toMap(flat(single, multi), keyMapper, valueFunction, mergeFunction, mapSupplier);
    }

    /**
     * 2개의 {@link Map}을 동일한 키를 값는 값을 새로운 형태의 데이터로 변환하여 새로운 {@link Map}을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2023. 12. 6.		박준홍			최초 작성
     * </pre>
     *
     * @param <K>
     *            데이터 식별정보 유형
     * @param <V>
     *            데이터 유형
     * @param <NV>
     *            새로운 값의 데이터 타입
     * @param bucket
     *            기존 {@link Map}
     * @param newData
     *            새로운 데이터 {@link Map}
     * @param aggrValue
     *            값 병합 함수
     * 
     *            <ul>
     *            <li>@param o 기존 값
     *            <li>@param n 새로운 값
     *            <li>@return 병합결과
     *            </ul>
     * @param newBucket
     *            새로운 {@link Map}
     * @return
     *
     * @since 2023. 12. 6.
     * @version 2.0.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <K, V, NV> Map<K, NV> merge(Map<K, V> bucket, Map<K, V> newData, BiFunction<V, V, NV> aggrValue, Map<K, NV> newBucket) {
        if (newBucket == null) {
            newBucket = new HashMap<K, NV>();
        }

        K k = null;
        V o = null;
        V n = null;
        for (Entry<K, V> entry : newData.entrySet()) {
            // 키
            k = entry.getKey();
            // 새로운 값
            n = entry.getValue();
            // 기존 값
            o = bucket.get(k);
            // 데이터 갱신
            newBucket.put(k, aggrValue.apply(o, n));
        }
        return newBucket;
    }

    /**
     * 기존 {@link Map}에 새로운 {@link Map} 데이터를 병합합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2023. 12. 6.		박준홍			최초 작성
     * </pre>
     *
     * @param <K>
     *            데이터 식별정보 유형
     * @param <V>
     *            데이터 유형
     * @param bucket
     *            기존 {@link Map}
     * @param newData
     *            새로운 데이터 {@link Map}
     * @param aggrValue
     *            값 병합 함수
     * 
     *            <ul>
     *            <li>@param o 기존 값
     *            <li>@param n 새로운 값
     *            <li>@return 병합결과
     *            </ul>
     * @since 2023. 12. 6.
     * @version 2.0.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <K, V> void merge(Map<K, V> bucket, Map<K, V> newData, BiFunction<V, V, V> aggrValue) {
        merge(bucket, newData, aggrValue, bucket);
    }

    /**
     * 주어진 {@link List}에서 정해진 개수만큼 데이터를 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2017. 10. 18.        박준홍         최초 작성
     * </pre>
     *
     * @param <K>
     *            데이터 식별정보 유형
     * @param <V>
     *            데이터 유형
     * @param map
     * @param readCount
     *            읽어올 개수
     * @return
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2017. 10. 18.
     */
    public static <K, V> Map<K, V> read(Map<K, V> map, int readCount) {

        Map<K, V> read = new HashMap<>();

        // #1. 데이터의 개수가 읽어올 개수보다 작거나 같은 경우
        // 모든 데이터 읽은 후 데이터 삭제
        if (readCount >= map.size()) {
            read.putAll(map);
            map.clear();

            return read;
        }

        // 읽혀진 데이터 키 저장
        List<K> keys = new ArrayList<>();

        // #2. 정해진 개수만큼 데이터 읽기
        int i = 0;
        for (Entry<K, V> entry : map.entrySet()) {

            read.put(entry.getKey(), entry.getValue());
            keys.add(entry.getKey());

            i++;

            if (i >= readCount) {
                break;
            }
        }

        // #3. 읽혀진 데이터 삭제
        for (K key : keys) {
            map.remove(key);
        }

        return read;
    }

    /**
     * 2개의 {@link Map}에 포함된 값을 하나의 {@link Collection} 구현체로 묶어서 제공합니다. <br>
     * 단, <code>keyMapper</code>에 해당하는 값이 동일한 경우 <code>mergeFunction</code>를 통해서 객체를 하나로 병합합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 20.     박준홍         최초 작성
     * </pre>
     *
     * @param <NK>
     *            데이터 식별정보 유형
     * @param <V>
     *            데이터 유형
     * @param <COL>
     *            결과 {@link Collection} 유형
     * @param single
     * @param multi
     * @param keyMapper
     *            객체의 식별정보를 제공하는 함수.
     * @param mergeFunction
     *            2개의 객체 정보를 하나로 병합하는 함수. (V + V => V)
     * @param collectionFactory
     *            {@link Collection} 객체 제공 함수.
     * @return
     *
     * @since 2025. 8. 20.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <K, V, NK, COL extends Collection<V>> COL toCollection(Map<K, V> single, Map<K, List<V>> multi, Function<V, NK> keyMapper, BinaryOperator<V> mergeFunction,
            Supplier<COL> collectionFactory) {
        return toCollection(single, multi, keyMapper, d -> d, mergeFunction, collectionFactory);
    }

    /**
     * 2개의 {@link Map}에 포함된 값을 하나의 {@link Collection} 구현체로 묶어서 제공합니다. <br>
     * 단, <code>keyMapper</code>에 해당하는 값이 동일한 경우 <code>mergeFunction</code>를 통해서 객체를 하나로 병합합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 20.     박준홍         최초 작성
     * </pre>
     *
     * @param <NK>
     *            데이터 식별정보 유형
     * @param <V>
     *            데이터 유형
     * @param <COL>
     *            결과 {@link Collection} 유형
     * @param single
     * @param multi
     * @param keyMapper
     *            객체의 식별정보를 제공하는 함수.
     * @param valueMapper
     *            객체의 복제 또는 새로운 객체로 제공하는 함수.
     * @param mergeFunction
     *            2개의 객체 정보를 하나로 병합하는 함수. (V + V => V)
     * @param collectionFactory
     *            {@link Collection} 객체 제공 함수.
     * @return
     *
     * @since 2025. 8. 20.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <K, V, NK, COL extends Collection<V>> COL toCollection(Map<K, V> single, Map<K, List<V>> multi, Function<V, NK> keyMapper, Function<V, V> valueMapper,
            BinaryOperator<V> mergeFunction, Supplier<COL> collectionFactory) {
        return StreamUtils.toCollection(flat(single, multi), keyMapper, valueMapper, mergeFunction, collectionFactory);
    }

    /**
     * 2개의 {@link Map}에 포함된 값을 하나의 {@link Collection} 구현체로 묶어서 제공합니다. <br>
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 20.     박준홍         최초 작성
     * </pre>
     *
     * @param <V>
     *            데이터 유형
     * @param <COL>
     *            결과 {@link Collection} 유형
     * @param single
     * @param multi
     * @param collectionFactory
     * @return
     *
     * @since 2025. 8. 20.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <K, V, COL extends Collection<V>> COL toCollection(Map<K, V> single, Map<K, List<V>> multi, Supplier<COL> collectionFactory) {
        AssertUtils2.notNulls(single, multi, collectionFactory);
        return flat(single, multi).collect(Collectors.toCollection(collectionFactory));
    }

    /**
     * 2개의 {@link Map}에 포함된 값을 하나의 {@link List}로 묶어서 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 20.     박준홍         최초 작성
     * </pre>
     *
     * @param <V>
     *            데이터 유형
     * @param single
     * @param multi
     * @return
     *
     * @since 2025. 8. 20.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <K, V> List<V> toList(Map<K, V> single, Map<K, List<V>> multi) {
        return toCollection(single, multi, ArrayList<V>::new);
    }

    /**
     * 2개의 {@link Map}에 포함된 값을 하나의 {@link List}로 묶어서 제공합니다. <br>
     * 단, <code>keyMapper</code>에 해당하는 값이 동일한 경우 <code>mergeFunction</code>를 통해서 객체를 하나로 병합합니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 20.     박준홍         최초 작성
     * </pre>
     *
     * @param <NK>
     *            데이터 식별정보 유형
     * @param <V>
     *            데이터 유형
     * @param single
     * @param multi
     * @param keyMapper
     *            객체의 식별정보를 제공하는 함수.
     * @param mergeFunction
     *            2개의 객체 정보를 하나로 병합하는 함수. ( V + V => V)
     * @return
     *
     * @since 2025. 8. 20.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <K, V, NK> List<V> toList(Map<K, V> single, Map<K, List<V>> multi, Function<V, NK> keyMapper, BinaryOperator<V> mergeFunction) {
        return toCollection(single, multi, keyMapper, d -> d, mergeFunction, ArrayList<V>::new);
    }

    /**
     * 2개의 {@link Map}에 포함된 값을 하나의 {@link List}로 묶어서 제공합니다. <br>
     * 단, <code>keyMapper</code>에 해당하는 값이 동일한 경우 <code>mergeFunction</code>를 통해서 객체를 하나로 병합합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 20.     박준홍         최초 작성
     * </pre>
     *
     * @param <NK>
     *            데이터 식별정보 유형
     * @param <V>
     *            데이터 유형
     * @param <LIST>
     *            결과 {@link List} 유형
     * @param single
     * @param multi
     * @param keyMapper
     *            객체의 식별정보를 제공하는 함수.
     * @param mergeFunction
     *            2개의 객체 정보를 하나로 병합하는 함수. ( V + V => V)
     * @param listFactory
     *            {@link List} 객체를 제공하는 함수.
     * @return
     *
     * @since 2025. 8. 20.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <K, V, NK, LIST extends List<V>> LIST toList(Map<K, V> single, Map<K, List<V>> multi, Function<V, NK> keyMapper, BinaryOperator<V> mergeFunction,
            Supplier<LIST> listFactory) {
        return toCollection(single, multi, keyMapper, d -> d, mergeFunction, listFactory);
    }

    /**
     * 2개의 {@link Map}에 포함된 값을 하나의 {@link List}로 묶어서 제공합니다. <br>
     * 단, <code>keyMapper</code>에 해당하는 값이 동일한 경우 <code>mergeFunction</code>를 통해서 객체를 하나로 병합합니다. <br>
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 20.     박준홍         최초 작성
     * </pre>
     *
     * @param <NK>
     *            데이터 식별정보 유형
     * @param <V>
     *            데이터 유형
     * @param <LIST>
     *            결과 {@link List} 유형
     * @param single
     * @param multi
     *            * @param keyMapper 객체의 식별정보를 제공하는 함수.
     * @param valueMapper
     *            객체의 복제 또는 새로운 객체로 제공하는 함수.
     * @param mergeFunction
     *            2개의 객체 정보를 하나로 병합하는 함수. (V + V => V)
     * @return
     *
     * @since 2025. 8. 20.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <K, V, NK> List<V> toList(Map<K, V> single, Map<K, List<V>> multi, Function<V, NK> keyMapper, Function<V, V> valueMapper, BinaryOperator<V> mergeFunction) {
        return toCollection(single, multi, keyMapper, valueMapper, mergeFunction, ArrayList<V>::new);
    }

    /**
     * 2개의 {@link Map}에 포함된 값을 하나의 {@link List}로 묶어서 제공합니다. <br>
     * 단, <code>keyMapper</code>에 해당하는 값이 동일한 경우 <code>mergeFunction</code>를 통해서 객체를 하나로 병합합니다. <br>
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 20.     박준홍         최초 작성
     * </pre>
     *
     * @param <NK>
     *            데이터 식별정보 유형
     * @param <V>
     *            데이터 유형
     * @param <LIST>
     *            결과 {@link List} 유형
     * @param single
     * @param multi
     *            * @param keyMapper 객체의 식별정보를 제공하는 함수.
     * @param valueMapper
     *            객체의 복제 또는 새로운 객체로 제공하는 함수.
     * @param mergeFunction
     *            2개의 객체 정보를 하나로 병합하는 함수. (V + V => V)
     * @param listFactory
     *            {@link List} 객체를 제공하는 함수.
     * @return
     *
     * @since 2025. 8. 20.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <K, V, NK, LIST extends List<V>> LIST toList(Map<K, V> single, Map<K, List<V>> multi, Function<V, NK> keyMapper, Function<V, V> valueMapper,
            BinaryOperator<V> mergeFunction, Supplier<LIST> listFactory) {
        return toCollection(single, multi, keyMapper, valueMapper, mergeFunction, listFactory);
    }

    /**
     * 2개의 {@link Map}에 포함된 값을 하나의 {@link Set}로 묶어서 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 20.     박준홍         최초 작성
     * </pre>
     *
     * @param <V>
     *            데이터 유형
     * @param single
     * @param multi
     * @return
     *
     * @since 2025. 8. 20.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <K, V> Set<V> toSet(Map<K, V> single, Map<K, List<V>> multi) {
        return toCollection(single, multi, (Supplier<Set<V>>) HashSet<V>::new);
    }

    /**
     * 2개의 {@link Map}에 포함된 값을 하나의 {@link Set}로 묶어서 제공합니다. <br>
     * 단, <code>keyMapper</code>에 해당하는 값이 동일한 경우 <code>mergeFunction</code>를 통해서 객체를 하나로 병합합니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 20.     박준홍         최초 작성
     * </pre>
     *
     * @param <NK>
     *            데이터 식별정보 유형
     * @param <V>
     *            데이터 유형
     * @param single
     * @param multi
     * @param keyMapper
     *            객체의 식별정보를 제공하는 함수.
     * @param mergeFunction
     *            2개의 객체 정보를 하나로 병합하는 함수. ( V + V => V)
     * @return
     *
     * @since 2025. 8. 20.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <K, V, NK> Set<V> toSet(Map<K, V> single, Map<K, List<V>> multi, Function<V, NK> keyMapper, BinaryOperator<V> mergeFunction) {
        return toCollection(single, multi, keyMapper, d -> d, mergeFunction, (Supplier<Set<V>>) HashSet<V>::new);
    }

    /**
     * 2개의 {@link Map}에 포함된 값을 하나의 {@link Set}로 묶어서 제공합니다. <br>
     * 단, <code>keyMapper</code>에 해당하는 값이 동일한 경우 <code>mergeFunction</code>를 통해서 객체를 하나로 병합합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 20.     박준홍         최초 작성
     * </pre>
     *
     * @param <NK>
     *            데이터 식별정보 유형
     * @param <V>
     *            데이터 유형
     * @param <SET>
     *            결과 {@link Set} 유형
     * @param single
     * @param multi
     * @param keyMapper
     *            객체의 식별정보를 제공하는 함수.
     * @param mergeFunction
     *            2개의 객체 정보를 하나로 병합하는 함수. ( V + V => V)
     * @param setFactory
     *            {@link Set} 객체를 제공하는 함수.
     * @return
     *
     * @since 2025. 8. 20.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <K, V, NK, SET extends Set<V>> SET toSet(Map<K, V> single, Map<K, List<V>> multi, Function<V, NK> keyMapper, BinaryOperator<V> mergeFunction,
            Supplier<SET> setFactory) {
        return toCollection(single, multi, keyMapper, d -> d, mergeFunction, setFactory);
    }

    /**
     * 2개의 {@link Map}에 포함된 값을 하나의 {@link Set}로 묶어서 제공합니다. <br>
     * 단, <code>keyMapper</code>에 해당하는 값이 동일한 경우 <code>mergeFunction</code>를 통해서 객체를 하나로 병합합니다. <br>
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 20.     박준홍         최초 작성
     * </pre>
     *
     * @param <NK>
     *            데이터 식별정보 유형
     * @param <V>
     *            데이터 유형
     * @param <SET>
     *            결과 {@link Set} 유형
     * @param single
     * @param multi
     *            * @param keyMapper 객체의 식별정보를 제공하는 함수.
     * @param valueMapper
     *            객체의 복제 또는 새로운 객체로 제공하는 함수.
     * @param mergeFunction
     *            2개의 객체 정보를 하나로 병합하는 함수. (V + V => V)
     * @return
     *
     * @since 2025. 8. 20.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <K, V, NK> Set<V> toSet(Map<K, V> single, Map<K, List<V>> multi, Function<V, NK> keyMapper, Function<V, V> valueMapper, BinaryOperator<V> mergeFunction) {
        return toCollection(single, multi, keyMapper, valueMapper, mergeFunction, (Supplier<Set<V>>) HashSet<V>::new);
    }

    /**
     * 2개의 {@link Map}에 포함된 값을 하나의 {@link Set}로 묶어서 제공합니다. <br>
     * 단, <code>keyMapper</code>에 해당하는 값이 동일한 경우 <code>mergeFunction</code>를 통해서 객체를 하나로 병합합니다. <br>
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 20.     박준홍         최초 작성
     * </pre>
     *
     * @param <NK>
     *            데이터 식별정보 유형
     * @param <V>
     *            데이터 유형
     * @param <SET>
     *            결과 {@link Set} 유형
     * @param single
     * @param multi
     * @param keyMapper
     *            객체의 식별정보를 제공하는 함수.
     * @param valueMapper
     *            객체의 복제 또는 새로운 객체로 제공하는 함수.
     * @param mergeFunction
     *            2개의 객체 정보를 하나로 병합하는 함수. (V + V => V)
     * @param setFactory
     *            {@link Set} 객체를 제공하는 함수.
     * @return
     *
     * @since 2025. 8. 20.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <K, V, NK, SET extends Set<V>> SET toSet(Map<K, V> single, Map<K, List<V>> multi, Function<V, NK> keyMapper, Function<V, V> valueMapper,
            BinaryOperator<V> mergeFunction, Supplier<SET> setFactory) {
        return toCollection(single, multi, keyMapper, valueMapper, mergeFunction, setFactory);
    }

}
