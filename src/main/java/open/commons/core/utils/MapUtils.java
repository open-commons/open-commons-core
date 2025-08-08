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
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

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
     * @param <V>
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
     * @param <V>
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
     * @param <V>
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
     * @param <V>
     * @param <NK>
     * @param <NV>
     * @param <C>
     * @param <M>
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
     *            키 데이터 타입
     * @param <V>
     *            값 데이터 타입
     * @param <NV>
     *            새로운 값의 이터 타입
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
     *            키 데이터 타입
     * @param <V>
     *            값 데이터 타입
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
}
