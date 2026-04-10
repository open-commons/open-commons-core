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
* 
* This file is generated under this project, "open-commons-core".
*
* Date  : 2011. 10. 24. 오전 10:13:26
*
* Author: Park Jun-Hong (parkjunhong77@gmail.com)
* 
*/
package open.commons.core.utils;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import open.commons.core.TwoValueObject;
import open.commons.core.function.BinaryOperators;
import open.commons.core.function.Predicates;
import open.commons.core.utils.CollectionUtils.TopN.TopNStrategy;

/**
 * 
 * @since 2011. 10. 24.
 */
// 아래 내용에 적용됨.
// - 대부분의 JDK 표준 API
// [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
// [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
@SuppressWarnings({ "null", "unchecked" })
public class CollectionUtils {

    private static final BiConsumer<StringBuilder, String[]> APPENDER_STR = (sb, strs) -> {
        for (String str : strs) {
            sb.append(str);
        }
    };

    /**
     * 새로운 데이터를 {@link Collection}에 추가합니다.
     * 
     * @param col
     * @param clazz
     *            {@code col}이 {@code null} 인 경우 생성할 {@link Collection} 타입.
     * @param elem
     *            새로운 데이터
     * @return 전달받은 {@code col}이 {@code null}인 경우, 새로운 객체.
     */
    public static <C extends @Nullable Collection<E>, E extends @Nullable Object> C add(C col, Class<C> clazz, E elem) {
        if (col == null) {
            try {
                col = clazz.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return addAll(col, elem);
    }

    /**
     * 새로운 데이터를 {@link Collection}에 추가합니다.
     * 
     * @param col
     * @param clazz
     *            {@code col}이 {@code null} 인 경우 생성할 {@link Collection} 타입.
     * @param elems
     *            새로운 데이터
     * @return 전달받은 {@code col}이 {@code null}인 경우, 새로운 객체.
     */
    public static <C extends @Nullable Collection<E>, E extends @Nullable Object> C addAll(C col, Class<C> clazz, E... elems) {
        if (col == null) {
            try {
                col = clazz.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return addAll(col, elems);
    }

    /**
     * 새로운 데이터를 {@link Collection}에 추가합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2026. 3. 26.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <C>
     * @param <E>
     * @param col
     * @param elems
     *            새로운 데이터
     * @return 전달받은 {@code col}이 {@code null}인 경우, 새로운 객체.
     *
     * @since 2026. 3. 26.
     * @version 3.0.0
     */
    public static <E extends @Nullable Object, C extends Collection<E>> C addAll(C col, Collection<E> elems) {
        Objects.requireNonNull(col);
        Objects.requireNonNull(elems);

        for (E elem : elems) {
            if (elem != null) {
                col.add(elem);
            }
        }

        return col;
    }

    /**
     * 새로운 데이터를 {@link Collection}에 추가합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 7. 4.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param col
     * @param elems
     * @return
     *
     * @since 2019. 7. 4.
     */
    public static <E extends @Nullable Object, C extends Collection<E>> C addAll(C col, E... elems) {
        Objects.requireNonNull(col);
        Objects.requireNonNull(elems);

        for (E elem : elems) {
            if (elem != null) {
                col.add(elem);
            }
        }

        return col;
    }

    /**
     * 새로운 데이터를 {@link Collection}에 추가합니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2017. 12. 13.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     * 
     * @param col
     * @param clazz
     *            {@code col}이 {@code null} 인 경우 생성할 {@link Collection} 타입.
     * @param elems
     *            새로운 데이터
     * @return 전달받은 {@code col}이 {@code null}인 경우, 새로운 객체.
     *
     * @since 2017. 12. 13.
     */
    public static <E extends @Nullable Object, C extends @Nullable Collection<E>> C addAllIfNotNull(C col, Class<C> clazz, Collection<E> elems) {
        if (col == null) {
            try {
                col = clazz.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return addAll(col, elems);
    }

    /**
     * 새로운 데이터를 {@link Collection}에 추가합니다.
     * 
     * @param col
     * @param clazz
     *            {@code col}이 {@code null} 인 경우 생성할 {@link Collection} 타입.
     * @param elems
     *            새로운 데이터
     * @return 전달받은 {@code col}이 {@code null}인 경우, 새로운 객체.
     */
    public static <C extends Collection<E>, E extends @Nullable Object> C addAllIfNotNull(C col, Class<C> clazz, E... elems) {
        return addAll(col, clazz, elems);
    }

    /***
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2017. 12. 13.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <T>
     * @param list
     * @param value
     * @return
     *
     * @since 2017. 12. 13.
     * @version 1.8.0
     */
    public static <T extends @Nullable Object> boolean addIfAbsent(List<T> list, T value) {
        Objects.requireNonNull(list);

        if (list.contains(value)) {
            return false;
        } else {
            return list.add(value);
        }
    }

    /**
     * 추가하려는 데이터가 {@code null}이 아닌 경우 추가합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2017. 12. 13.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param col
     * @param clazz
     * @param elem
     * @return
     *
     * @since 2017. 12. 13.
     */
    public static <C extends @Nullable Collection<E>, E extends @Nullable Object> C addIfNotNull(C col, Class<C> clazz, E elem) {
        if (elem == null) {
            return col;
        }

        return add(col, clazz, elem);
    }

    /**
     * 2개의 데이터를 동일한 기준으로 양쪽 순서를 맞추어 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 1.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <E>
     *            데이터 유형
     * @param <KEY>
     *            식별정보 유형
     * @param <R>
     *            새로운 데이터 유형 (E &rarr; R)
     * @param data1
     *            정렬된 데이터#1
     * @param data2
     *            정렬된 데이터#2
     * @param keyProvider
     *            데이터 식별정보 제공 함수
     * @param transformer
     *            데이터 변환 함수 (E &rarr; R)
     * @param emptyCreator
     *            빈 데이터 제공 함수 ({} &rarr; R)
     * @return
     *
     * @since 2025. 9. 1.
     * @version 2.1.0
     */
    public static <E, KEY extends Comparable<KEY>, R> TwoValueObject<List<R>, List<R>> alignBy( //
            List<E> data1, List<E> data2 //
            , Function<E, KEY> keyProvider, Function<E, R> transformer, Function<KEY, R> emptyCreator //
    ) {

        return alignBy(data1, keyProvider, transformer, emptyCreator, data2, keyProvider, transformer, emptyCreator);
    }

    /**
     * 2개의 데이터를 동일한 기준으로 양쪽 순서를 맞추어 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 1.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <E>
     *            데이터 유형
     * @param <KEY>
     *            식별정보 유형
     * @param data1
     *            정렬된 데이터#1
     * @param data2
     *            정렬된 데이터#2
     * @param keyProvider
     *            데이터 식별정보 제공 함수
     * @param emptyCreator
     *            빈 데이터 제공 함수 ({} &rarr; R)
     * @return
     *
     * @since 2025. 9. 1.
     * @version 2.1.0
     */
    public static <E, KEY extends Comparable<KEY>> TwoValueObject<List<E>, List<E>> alignBy( //
            List<E> data1, List<E> data2 //
            , Function<E, KEY> keyProvider, Function<KEY, E> emptyCreator //
    ) {
        return alignBy(data1, keyProvider, StreamUtils.identity(), emptyCreator, data2, keyProvider, StreamUtils.identity(), emptyCreator);
    }

    /**
     * 2개의 데이터를 동일한 기준으로 양쪽 순서를 맞추어 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 1.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <E1>
     *            데이터 유형1
     * @param <E2>
     *            데이터 유형2
     * @param <KEY>
     *            식별정보 유형
     * @param <R>
     *            새로운 데이터 유형 (E1 &rarr; R, E2 &rarr; R)
     * @param data1
     *            정렬된 데이터#1
     * @param keyProvider1
     *            데이터#1 식별정보 제공 함수
     * @param transformer1
     *            데이터#1 변환 함수 (E1 &rarr; R)
     * @param data2
     *            정렬된 데이터#2
     * @param keyProvider2
     *            데이터#2 식별정보 제공 함수
     * @param transformer2
     *            데이터#2 변환 함수 (E2 &rarr; R)
     * @param emptyCreator
     *            빈 데이터 제공 함수 ({} &rarr; R)
     * @return
     *
     * @since 2025. 9. 1.
     * @version 2.1.0
     */
    public static <E1, E2, KEY extends Comparable<KEY>, R> TwoValueObject<List<R>, List<R>> alignBy( //
            List<E1> data1, Function<E1, KEY> keyProvider1, Function<E1, R> transformer1 //
            , List<E2> data2, Function<E2, KEY> keyProvider2, Function<E2, R> transformer2 //
            , Function<KEY, R> emptyCreator //
    ) {
        return alignBy(data1, keyProvider1, transformer1, emptyCreator, data2, keyProvider2, transformer2, emptyCreator);
    }

    /**
     * 2개의 데이터를 동일한 기준으로 양쪽 순서를 맞추어 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 1.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <E1>
     *            데이터 유형1
     * @param <E2>
     *            데이터 유형2
     * @param <KEY>
     *            식별정보 유형
     * @param <R1>
     *            새로운 데이터 유형1 (E1 &rarr; R1)
     * @param <R2>
     *            새로운 데이터 유형2 (E2 &rarr; R2)
     * @param data1
     *            정렬된 데이터#1
     * @param keyProvider1
     *            데이터#1 식별정보 제공 함수
     * @param transformer1
     *            데이터#1 변환 함수 (E1 &rarr; R1)
     * @param emptyCreator1
     *            빈 데이터 제공 함수 ( {} &rarr; R1)
     * @param data2
     *            정렬된 데이터#2
     * @param keyProvider2
     *            데이터#2 식별정보 제공 함수
     * @param transformer2
     *            데이터#2 변환 함수 (E2 &rarr; R2)
     * @param emptyCreator2
     *            빈 데이터 제공 함수 ({} &rarr; R2)
     * @return
     *
     * @since 2025. 9. 1.
     * @version 2.1.0
     */
    public static <E1, E2, KEY extends Comparable<KEY>, R1, R2> TwoValueObject<List<R1>, List<R2>> alignBy( //
            List<E1> data1, Function<E1, KEY> keyProvider1, Function<E1, R1> transformer1, Function<KEY, R1> emptyCreator1 //
            , List<E2> data2, Function<E2, KEY> keyProvider2, Function<E2, R2> transformer2, Function<KEY, R2> emptyCreator2 //
    ) {
        ObjectUtils.requireNonNulls(data1, keyProvider1, transformer1, emptyCreator1, data2, keyProvider2, transformer2, emptyCreator2);

        List<R1> resultData1 = new ArrayList<>();
        List<R2> resultData2 = new ArrayList<>();

        Iterator<E1> itrData1 = data1.iterator();
        @Nullable
        E1 d1 = null;
        @Nullable
        KEY key1 = null;

        Iterator<E2> itrData2 = data2.iterator();
        @Nullable
        E2 d2 = null;
        @Nullable
        KEY key2 = null;

        int compared = -1;
        while (itrData1.hasNext() || itrData2.hasNext()) {
            if (d1 == null && itrData1.hasNext()) {
                d1 = itrData1.next();
            }
            if (d2 == null && itrData2.hasNext()) {
                d2 = itrData2.next();
            }

            if (d1 != null && d2 != null) {
                key1 = keyProvider1.apply(d1);
                key2 = keyProvider2.apply(d2);
                compared = key1.compareTo(key2);
            } else if (d1 != null) {
                key1 = keyProvider1.apply(d1);
                compared = -1;
            } else if (d2 != null) {
                key2 = keyProvider2.apply(d2);
                compared = 1;
            } else {
                break;
            }

            if (compared < 0) {
                resultData1.add(transformer1.apply(d1));
                d1 = null;
                resultData2.add(emptyCreator2.apply(key1));
            } else if (compared > 0) {
                resultData1.add(emptyCreator1.apply(key2));
                resultData2.add(transformer2.apply(d2));
                d2 = null;
            } else {
                resultData1.add(transformer1.apply(d1));
                d1 = null;
                resultData2.add(transformer2.apply(d2));
                d2 = null;
            }
        }

        return new TwoValueObject<>(resultData1, resultData2);
    }

    /**
     * Clear {@link Collection}s if each of them are not null.
     * 
     * @param cols
     * 
     * @since 2014. 6. 30.
     */
    public static void clear(Collection<?> @Nullable... cols) {
        if (cols == null) {
            return;
        }

        for (Collection<?> col : cols) {
            if (col != null) {
                col.clear();
            }
        }
    }

    /**
     * 여러 개의 데이터를 하나의 형태로 취합합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 12. 14.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <S>
     * @param <T>
     * @param data
     *            데이터
     * @param transformer
     *            데이터 변환 함수
     * @param aggregator
     *            데이터 취합함수
     * 
     * @since 2021. 12. 14.
     * @version 1.8.0
     */
    public static <S, T> void concatenate(Collection<S> data, Function<S, T> transformer, Consumer<T> aggregator) {
        ObjectUtils.requireNonNulls(data, transformer, aggregator);

        data.stream().map(transformer).forEach(aggregator);
    }

    /**
     * 여러 개의 데이터를 하나의 형태로 취합합니다. <br>
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 12. 20.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <S>
     * @param <T>
     * @param <U>
     * @param data
     *            데이터
     * @param transformer
     *            데이터 변환 함수
     * @param aggregator
     *            데이터 취합함수
     * @return
     *
     * @since 2021. 12. 20.
     * @version 1.8.0
     */
    public static <S, T, U> U concatenate(Collection<S> data, Function<S, T> transformer, Function<List<T>, U> aggregator) {
        ObjectUtils.requireNonNulls(data, transformer, aggregator);

        return aggregator.apply(data.stream().map(transformer).collect(Collectors.toList()));
    }

    /**
     * 문자열을 키로 하는 맵에서 문자열의 대/소문자를 여부에 관계없이 키의 존재 여부를 반환합니다.
     * 
     * @param map
     * @param keyIgnoreCase
     * 
     * @return
     * 
     * @since 2012. 02. 22.
     */
    public static boolean containsKeyIgnoreCase(Map<String, ?> map, String keyIgnoreCase) {
        Objects.requireNonNull(map);

        for (String key : map.keySet()) {
            if (key.equalsIgnoreCase(keyIgnoreCase))
                return true;
        }

        return false;
    }

    /**
     * 주어진 데이터를 'Tree' 형태로 구성하여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 1.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <E>
     *            데이터 유형
     * @param <TREE>
     *            'Tree' 데이터 유형
     * @param <KEY>
     *            'Tree' 데이터 식별정보 유형
     * @param data
     *            데이터
     * @param keyProvider
     *            식별정보 제공자
     * @param parentKeyProvider
     *            상위객체 식별정보 제공자
     * @param transformer
     *            데이터 변환 함수 (E &rarr; TREE)
     * @param addChild
     *            'TREE'에 데이터 추가 함수.
     *            <li>첫번재: 상위 객체
     *            <li>두번째: 하위 객체
     * @return
     *
     * @since 2025. 9. 1.
     * @version 2.1.0
     */
    public static <E, TREE, KEY> List<TREE> createTree(//
            List<E> data, Function<E, KEY> keyProvider, Function<E, KEY> parentKeyProvider //
            , Function<E, TREE> transformer, BiConsumer<TREE, TREE> addChild //
    ) {
        ObjectUtils.requireNonNulls(data, keyProvider, parentKeyProvider, transformer, addChild);

        Map<KEY, TREE> top = new LinkedHashMap<>();
        Map<KEY, TREE> elements = new HashMap<>();

        for (E d : data) {
            KEY key = keyProvider.apply(d);
            TREE tree = transformer.apply(d);
            KEY parentKey = parentKeyProvider.apply(d);
            if (elements.containsKey(parentKey)) {
                // 상위 객체에 추가.
                addChild.accept(elements.get(parentKey), tree);
            } else {
                top.put(key, tree);
            }
            // 자신 객체를 Map에 추가. 하위 객체가 자신을 찾을 수 있게.
            elements.put(key, tree);
        }

        return new ArrayList<>(top.values());
    }

    /**
     * Transform {@link Collection} to {@link Map}. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2017. 7. 6.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <E>
     *            a type of an element.
     * @param <K>
     *            데이터 식별정보 유형 a type of a key.
     * @param col
     *            elements
     * @param keyGen
     *            데이터 식별정보 제공 함수.
     * @return
     *
     * @since 2017. 7. 6.
     * @version 1.6.17
     */
    public static <E, K> Map<K, List<E>> elementToListValuedMap(Collection<E> col, Function<E, K> keyGen) {
        ObjectUtils.requireNonNulls(col, keyGen);

        return StreamUtils.toMap(col.stream(), keyGen, v -> v);
    }

    /**
     * 전체 데이터 중에 조건에 맞는 데이터만 새로운 {@link Collection}에 추가합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 7. 13.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <E>
     * @param <C>
     * @param col
     * @param p
     * @param newCol
     *
     * @since 2021. 7. 13.
     * @version 1.8.0
     */
    public static <E, C extends Collection<E>> void get(Collection<E> col, Predicate<E> p, C newCol) {
        ObjectUtils.requireNonNulls(col, newCol);

        for (E e : col) {
            if (p.test(e)) {
                newCol.add(e);
            }
        }
    }

    /**
     * 전체 데이터 중에 조건에 맞는 데이터만 새로운 {@link Collection}으로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 7. 13.     parkjunhong77@gmail.com         최초 작성
     * 2026. 3. 4.      parkjunhong77@gmail.com         내부 구현 변경 (Class.newInstance() -> Class.getDeclaredConstructor().newInstance());
     * </pre>
     *
     * @param <E>
     * @param <C>
     * @param col
     * @param p
     * @param type
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     *
     * @since 2021. 7. 13.
     * @version 1.8.0
     */
    public static <E, C extends Collection<E>> C get(Collection<E> col, Predicate<E> p, Class<C> type) throws InstantiationException, IllegalAccessException {
        ObjectUtils.requireNonNulls(col, p, type);

        try {
            // Stream API와 Collectors.toCollection을 활용한 선언적 파이프라인
            return col.stream().filter(p).collect(Collectors.toCollection(() -> {
                try {
                    // JDK 9+ 표준: getDeclaredConstructor().newInstance() 사용
                    return type.getDeclaredConstructor().newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    // Supplier 내부에서는 Checked Exception을 던질 수 없으므로 RuntimeException으로 래핑
                    throw new RuntimeException(e);
                } catch (NoSuchMethodException | InvocationTargetException e) {
                    throw new IllegalArgumentException("기본 생성자를 호출할 수 없거나 예외가 발생했습니다: " + type.getName(), e);
                }
            }));
        } catch (RuntimeException e) {
            // 기존 API 호출부의 호환성(throws 시그니처)을 유지하기 위한 예외 언래핑(Unwrapping)
            // Java 16+의 패턴 매칭을 활용하여 간결하게 처리
            if (e.getCause() instanceof InstantiationException ie) {
                throw ie;
            } else if (e.getCause() instanceof IllegalAccessException iae) {
                throw iae;
            }
            throw e; // 그 외의 예상치 못한 런타임 예외는 그대로 전파
        }
    }

    /**
     * {@link Map} 데이터의 'key'가 파라미터와 동일(대소문자 무시)한 값들을 제공합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 1. 30.     parkjunhong77@gmail.com     최초 작성
     * 2026. 3. 4.      parkjunhong77@gmail.com     구현을 Stream API 적용.
     * </pre>
     *
     * @param <E>
     *            a type of a value.
     * @param map
     *            elements.
     * @param keyIgnoreCase
     * @return
     *
     * @since 2020. 1. 30.
     * @version 1.6.17
     */
    public static <E> Collection<E> getIgnoreCase(Map<String, E> map, String keyIgnoreCase) {
        Objects.requireNonNull(map);

        return map.entrySet().stream() //
                .filter(entry -> entry.getKey().equalsIgnoreCase(keyIgnoreCase)) //
                .map(entry -> entry.getValue()) //
                .collect(Collectors.toList());
    }

    /**
     * {@link Collection}에 포함된 데이터 중에 {@link Predicate}를 만족하는 데이터가 있는지 여부를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 7. 13.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <E>
     * @param col
     *            데이터
     * @param p
     *            조건
     * @return
     *
     * @since 2021. 7. 13.
     * @version 1.8.0
     */
    public static <E> boolean has(Collection<E> col, Predicate<E> p) {
        ObjectUtils.requireNonNulls(col, p);
        return col.parallelStream().anyMatch(p);
    }

    public static <T> boolean isNullOrEmpty(@Nullable Collection<T> col) {
        return col == null || col.size() < 1;
    }

    /**
     * Transform {@link Collection} to {@link Map}. <br>
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2017. 7. 6.      parkjunhong77@gmail.com         최초 작성
     * 2026. 3. 4.      parkjunhong77@gmail.com         Stream API(flatMap, groupingBy) 적용
     * </pre>
     *
     * @param <E>
     *            a type of an element.
     * @param <K>
     *            데이터 식별정보 유형 a type of a key.
     * @param col
     *            elements
     * @param keyGen
     *            데이터 식별정보 제공 함수.
     * @return 요소가 키별로 그룹화된 맵
     *
     * @since 2017. 7. 6.
     */
    public static <E, K> Map<K, List<E>> listElementToListValuedMap(Collection<List<E>> col, Function<E, K> keyGen) {
        ObjectUtils.requireNonNulls(col, keyGen);

        // 1. col.stream() : Collection<List<E>>를 Stream<List<E>>로 변환
        return col.stream()
                // 2. flatMap(Collection::stream) : 중첩된 리스트들을 평탄화하여 하나의 Stream<E>로 병합
                .flatMap(Collection::stream)
                // 3. collect(Collectors.groupingBy(keyGen)) : keyGen 함수를 기준으로 요소를 Map<K, List<E>>로 자동 그룹화
                .collect(Collectors.groupingBy(keyGen));
    }

    /**
     * Primitive Array 를 Wrapper List 형태로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 12. 15.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param elems
     * @return
     *
     * @since 2020. 12. 15.
     * @version 1.8.0
     */
    @SafeVarargs
    public static List<Boolean> newList(boolean @Nullable... elems) {
        // null이거나 빈 배열일 경우 불필요한 객체 생성 방지
        if (elems == null || elems.length == 0) {
            return new ArrayList<>();
        }

        // 요소의 개수만큼 ArrayList의 초기 용량(Capacity)을 지정하여,
        // 데이터가 추가될 때 발생하는 내부 배열 복사(Resizing) 오버헤드 제거
        List<Boolean> list = new ArrayList<>(elems.length);

        for (boolean e : elems) {
            list.add(e);
        }

        return list;
    }

    /**
     * Primitive Array 를 Wrapper List 형태로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 12. 15.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param elems
     * @return
     *
     * @since 2020. 12. 15.
     * @version 1.8.0
     */
    public static List<Byte> newList(byte @Nullable... elems) {
        if (elems == null || elems.length == 0) {
            return new ArrayList<>();
        }

        List<Byte> list = new ArrayList<>(elems.length);

        for (byte e : elems) {
            list.add(e);
        }

        return list;
    }

    /**
     * Primitive Array 를 Wrapper List 형태로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 12. 15.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param elems
     * @return
     *
     * @since 2020. 12. 15.
     * @version 1.8.0
     */
    public static List<Character> newList(char @Nullable... elems) {
        if (elems == null || elems.length == 0) {
            return new ArrayList<>();
        }

        List<Character> list = new ArrayList<>(elems.length);

        for (char e : elems) {
            list.add(e);
        }

        return list;
    }

    /**
     * 데이터를 추가하여 새로운 {@link List}를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 12. 30.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <E>
     *            데이터 타입
     * @param list
     *            기존 {@link Collection}
     * @param elem
     *            새로운 데이터
     * @return
     *
     * @since 2022. 12. 30.
     */
    public static <E extends @Nullable Object> List<E> newList(@Nullable Collection<E> col, E elem) {
        return newList(col, elem, true);
    }

    /**
     * 데이터를 추가하여 새로운 {@link List}를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 12. 30.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <E>
     *            데이터 타입
     * @param col
     *            기존 {@link Collection}
     * @param elem
     *            새로운 데이터
     * @param addIfNull
     *            새로운 데이터(elem)이 {@code null}인 경우 추가 여부.
     * @return
     *
     * @since 2022. 12. 30.
     */
    public static <E extends @Nullable Object> List<E> newList(@Nullable Collection<E> col, E elem, boolean addIfNull) {
        if (elem == null && !addIfNull) {
            if (col != null) {
                return new ArrayList<>(col);
            } else {
                return new ArrayList<>();
            }
        } else {
            if (col != null) {
                List<E> newList = new ArrayList<>(col);
                newList.add(elem);
                return newList;
            } else {
                return newList(elem);
            }
        }
    }

    /**
     * Primitive Array 를 Wrapper List 형태로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 12. 15.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param elems
     * @return
     *
     * @since 2020. 12. 15.
     * @version 1.8.0
     */
    public static List<Double> newList(double @Nullable... elems) {
        if (elems == null || elems.length == 0) {
            return new ArrayList<>();
        }

        List<Double> list = new ArrayList<>(elems.length);

        for (double e : elems) {
            list.add(e);
        }

        return list;
    }

    /**
     * 배열에 포함된 데이터를 {@link List} 로 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 12. 21.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <E>
     *            데이터 타입
     * @param elems
     *            데이터
     * @return
     *
     * @since 2020. 12. 21.
     * @version 1.8.0
     */
    public static <E extends @Nullable Object> List<E> newList(E @Nullable... elems) {
        if (elems == null || elems.length == 0) {
            return new ArrayList<>();
        }

        return Stream.of(elems).toList();
    }

    /**
     * 데이터를 추가하여 새로운 {@link List} 로 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 12. 21.        parkjunhong77@gmail.com         최초 작성
     * 2022. 12. 30.        parkjunhong77@gmail.com     내부 구현 변경 (see {@link #newList(Collection, Object, boolean)})
     * </pre>
     *
     * @param <E>
     *            데이터 타입
     * @param elem
     *            데이터
     * @param col
     *            기존 데이터
     * @return
     *
     * @since 2020. 12. 21.
     * @version 1.8.0
     */
    public static <E extends @Nullable Object> List<E> newList(E elem, @Nullable Collection<E> col) {
        return newList(col, elem, true);
    }

    /**
     * 데이터를 추가하여 새로운 {@link List} 로 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 12. 21.        parkjunhong77@gmail.com         최초 작성
     * 2022. 12. 30.        parkjunhong77@gmail.com     내부 구현 변경 (see {@link #newList(Collection, Object, boolean)})
     * </pre>
     *
     * @param <E>
     *            데이터 타입
     * @param elem
     *            데이터
     * @param list
     *            기존 데이터
     * @return
     *
     * @since 2020. 12. 21.
     * @version 1.8.0
     */
    public static <E extends @Nullable Object> List<E> newList(E elem, @Nullable List<E> list) {
        return newList(list, elem, true);
    }

    /**
     * Primitive Array 를 Wrapper List 형태로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 12. 15.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param elems
     * @return
     *
     * @since 2020. 12. 15.
     * @version 1.8.0
     */
    public static List<Integer> newList(int @Nullable... elems) {
        if (elems == null || elems.length == 0) {
            return new ArrayList<>();
        }

        List<Integer> list = new ArrayList<>(elems.length);

        for (int e : elems) {
            list.add(e);
        }

        return list;
    }

    /**
     * 데이터를 추가하여 새로운 {@link List}를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 12. 30.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <E>
     *            데이터 타입
     * @param list
     *            기존 {@link List}
     * @param elem
     *            새로운 데이터
     * @return
     *
     * @since 2022. 12. 30.
     */
    public static <E extends @Nullable Object> List<E> newList(@Nullable List<E> list, E elem) {
        return newList(list, elem, true);
    }

    /**
     * 데이터를 추가하여 새로운 {@link List} 로 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 12. 21.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <E>
     *            데이터 타입
     * @param list
     *            기존 데이터
     * @param elems
     *            새로운 데이터
     * @return
     *
     * @since 2020. 12. 21.
     * @version 1.8.0
     */
    public static <E extends @Nullable Object> List<E> newList(@Nullable List<E> list, E @Nullable... elems) {
        if (list == null && elems == null) {
            return new ArrayList<>();
        } else if (elems == null) {
            return new ArrayList<>(list);
        } else if (list == null) {
            return newList(elems);
        } else {
            List<E> newList = new ArrayList<>(list);
            for (E e : elems) {
                newList.add(e);
            }
            return newList;
        }
    }

    /**
     * Primitive Array 를 Wrapper List 형태로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 12. 15.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param elems
     * @return
     *
     * @since 2020. 12. 15.
     * @version 1.8.0
     */
    public static List<Long> newList(long @Nullable... elems) {
        if (elems == null || elems.length == 0) {
            return new ArrayList<>();
        }

        List<Long> list = new ArrayList<>(elems.length);

        for (long e : elems) {
            list.add(e);
        }

        return list;
    }

    /**
     * Primitive Array 를 Wrapper List 형태로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 12. 15.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param elems
     * @return
     *
     * @since 2020. 12. 15.
     * @version 1.8.0
     */
    public static List<Short> newList(short @Nullable... elems) {
        if (elems == null || elems.length == 0) {
            return new ArrayList<>();
        }

        List<Short> list = new ArrayList<>(elems.length);

        for (short e : elems) {
            list.add(e);
        }

        return list;
    }

    /**
     * Primitive Array 를 Wrapper Set 형태로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 12. 15.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param elems
     * @return
     *
     * @since 2020. 12. 15.
     * @version 1.8.0
     */
    public static Set<Boolean> newSet(boolean @Nullable... elems) {
        if (elems == null || elems.length == 0) {
            return new HashSet<>();
        }

        Set<Boolean> set = new HashSet<>(elems.length);

        for (boolean e : elems) {
            set.add(e);
        }

        return set;
    }

    /**
     * Primitive Array 를 Wrapper Set 형태로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 12. 15.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param elems
     * @return
     *
     * @since 2020. 12. 15.
     * @version 1.8.0
     */
    public static Set<Byte> newSet(byte @Nullable... elems) {
        if (elems == null || elems.length == 0) {
            return new HashSet<>();
        }

        Set<Byte> set = new HashSet<>(elems.length);

        for (byte e : elems) {
            set.add(e);
        }

        return set;
    }

    /**
     * Primitive Array 를 Wrapper Set 형태로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 12. 15.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param elems
     * @return
     *
     * @since 2020. 12. 15.
     * @version 1.8.0
     */
    public static Set<Character> newSet(char @Nullable... elems) {
        if (elems == null || elems.length == 0) {
            return new HashSet<>();
        }

        Set<Character> set = new HashSet<>(elems.length);

        for (char e : elems) {
            set.add(e);
        }

        return set;
    }

    /**
     * 데이터를 추가하여 새로운 {@link Set}를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 12. 30.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <E>
     *            데이터 타입
     * @param list
     *            기존 {@link Collection}
     * @param elem
     *            새로운 데이터
     * @return
     *
     * @since 2022. 12. 30.
     */
    public static <E extends @Nullable Object> Set<E> newSet(@Nullable Collection<E> col, E elem) {
        return newSet(col, elem, true);
    }

    /**
     * 데이터를 추가하여 새로운 {@link Set}를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 12. 30.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <E>
     *            데이터 타입
     * @param col
     *            기존 {@link Collection}
     * @param elem
     *            새로운 데이터
     * @param addIfNull
     *            새로운 데이터(elem)이 {@code null}인 경우 추가 여부.
     * @return
     *
     * @since 2022. 12. 30.
     */
    public static <E extends @Nullable Object> Set<E> newSet(@Nullable Collection<E> col, E elem, boolean addIfNull) {
        if (elem == null && !addIfNull) {
            if (col != null) {
                return new HashSet<>(col);
            } else {
                return new HashSet<>();
            }
        } else {
            if (col != null) {
                Set<E> set = new HashSet<>(col);
                set.add(elem);
                return set;
            } else {
                return newSet(elem);
            }
        }
    }

    /**
     * Primitive Array 를 Wrapper Set 형태로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 12. 15.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param elems
     * @return
     *
     * @since 2020. 12. 15.
     * @version 1.8.0
     */
    public static Set<Double> newSet(double @Nullable... elems) {
        if (elems == null || elems.length == 0) {
            return new HashSet<>();
        }

        Set<Double> set = new HashSet<>(elems.length);

        for (double e : elems) {
            set.add(e);
        }

        return set;
    }

    /**
     * 배열 데이터를 새로운 {@link Set} 로 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 12. 21.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <E>
     *            데이터 타입
     * @param elems
     *            데이터
     * @return
     *
     * @since 2020. 12. 21.
     * @version 1.8.0
     */
    public static <E extends @Nullable Object> Set<E> newSet(E... elems) {
        Objects.requireNonNull(elems);

        return Stream.of(elems).collect(Collectors.toCollection(HashSet<E>::new));
    }

    /**
     * 데이터를 추가하여 새로운 {@link Set} 로 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 12. 21.        parkjunhong77@gmail.com         최초 작성
     * 2022. 12. 30.        parkjunhong77@gmail.com     내부 구현 변경 (see {@link #newSet(Collection, Object, boolean)}).
     * </pre>
     *
     * @param <E>
     *            데이터 타입
     * @param elem
     *            데이터
     * @param col
     *            기존 데이터
     * @return
     *
     * @since 2020. 12. 21.
     * @version 1.8.0
     */
    public static <E extends @Nullable Object> Set<E> newSet(E elem, @Nullable Collection<E> col) {
        return newSet(col, elem, true);
    }

    /**
     * 데이터를 추가하여 새로운 {@link Set} 로 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 12. 21.        parkjunhong77@gmail.com         최초 작성
     * 2022. 12. 30.        parkjunhong77@gmail.com     내부 구현 변경 (see {@link #newSet(Collection, Object, boolean)}).
     * </pre>
     *
     * @param <E>
     *            데이터 타입
     * @param elem
     *            데이터
     * @param set
     *            기존 데이터
     * @return
     *
     * @since 2020. 12. 21.
     * @version 1.8.0
     */
    public static <E extends @Nullable Object> Set<E> newSet(E elem, @Nullable Set<E> set) {
        return newSet(set, elem, true);
    }

    /**
     * Primitive Array 를 Wrapper Set 형태로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 12. 15.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param elems
     * @return
     *
     * @since 2020. 12. 15.
     * @version 1.8.0
     */
    public static Set<Integer> newSet(int @Nullable... elems) {
        if (elems == null || elems.length == 0) {
            return new HashSet<>();
        }

        Set<Integer> set = new HashSet<>(elems.length);

        for (int e : elems) {
            set.add(e);
        }

        return set;
    }

    /**
     * Primitive Array 를 Wrapper Set 형태로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 12. 15.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param elems
     * @return
     *
     * @since 2020. 12. 15.
     * @version 1.8.0
     */
    public static Set<Long> newSet(long @Nullable... elems) {
        if (elems == null || elems.length == 0) {
            return new HashSet<>();
        }

        Set<Long> set = new HashSet<>(elems.length);

        for (long e : elems) {
            set.add(e);
        }

        return set;
    }

    /**
     * 데이터를 추가하여 새로운 {@link Set}를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 12. 30.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <E>
     *            데이터 타입
     * @param set
     *            기존 {@link Set}
     * @param elem
     *            새로운 데이터
     * @return
     *
     * @since 2022. 12. 30.
     */
    public static <E extends @Nullable Object> Set<E> newSet(@Nullable Set<E> set, E elem) {
        return newSet(set, elem, true);
    }

    /**
     * 데이터를 추가하여 새로운 {@link Set} 로 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 12. 21.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <E>
     *            데이터 타입
     * @param set
     *            기존 데이터
     * @param elems
     *            데이터
     * @return
     *
     * @since 2020. 12. 21.
     * @version 1.8.0
     */
    public static <E extends @Nullable Object> Set<E> newSet(@Nullable Set<E> set, E @Nullable... elems) {
        if (set == null && elems == null) {
            return new HashSet<>();
        } else if (elems == null) {
            return new HashSet<>(set);
        } else if (set == null) {
            return newSet(elems);
        } else {
            Set<E> newList = new HashSet<>(set);
            for (E e : elems) {
                newList.add(e);
            }
            return newList;
        }
    }

    /**
     * Primitive Array 를 Wrapper Set 형태로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 12. 15.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param elems
     * @return
     *
     * @since 2020. 12. 15.
     * @version 1.8.0
     */
    public static Set<Short> newSet(short @Nullable... elems) {
        Set<Short> set = new HashSet<>();
        if (elems != null) {
            for (short e : elems) {
                set.add(e);
            }
        }

        return set;
    }

    /**
     * Primitive Array 를 Wrapper Vector 형태로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 12. 15.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param elems
     * @return
     *
     * @since 2020. 12. 15.
     * @version 1.8.0
     */
    public static Vector<Boolean> newVector(boolean @Nullable... elems) {
        if (elems == null || elems.length == 0) {
            return new Vector<>();
        }

        Vector<Boolean> vector = new Vector<>(elems.length);

        for (boolean e : elems) {
            vector.add(e);
        }

        return vector;
    }

    /**
     * Primitive Array 를 Wrapper Vector 형태로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 12. 15.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param elems
     * @return
     *
     * @since 2020. 12. 15.
     * @version 1.8.0
     */
    public static Vector<Byte> newVector(byte @Nullable... elems) {
        if (elems == null || elems.length == 0) {
            return new Vector<>();
        }

        Vector<Byte> vector = new Vector<>(elems.length);

        for (byte e : elems) {
            vector.add(e);
        }

        return vector;
    }

    /**
     * Primitive Array 를 Wrapper Vector 형태로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 12. 15.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param elems
     * @return
     *
     * @since 2020. 12. 15.
     * @version 1.8.0
     */
    public static Vector<Character> newVector(char @Nullable... elems) {
        if (elems == null || elems.length == 0) {
            return new Vector<>();
        }

        Vector<Character> vector = new Vector<>(elems.length);

        for (char e : elems) {
            vector.add(e);
        }

        return vector;
    }

    /**
     * 데이터를 추가하여 새로운 {@link Vector}를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 12. 30.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <E>
     *            데이터 타입
     * @param list
     *            기존 {@link Collection}
     * @param elem
     *            새로운 데이터
     * @return
     *
     * @since 2022. 12. 30.
     */
    public static <E extends @Nullable Object> Vector<E> newVector(@Nullable Collection<E> col, E elem) {
        return newVector(col, elem, true);
    }

    /**
     * 데이터를 추가하여 새로운 {@link Vector}를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 12. 30.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <E>
     *            데이터 타입
     * @param col
     *            기존 {@link Collection}
     * @param elem
     *            새로운 데이터
     * @param addIfNull
     *            새로운 데이터(elem)이 {@code null}인 경우 추가 여부.
     * @return
     *
     * @since 2022. 12. 30.
     */
    public static <E extends @Nullable Object> Vector<E> newVector(@Nullable Collection<E> col, E elem, boolean addIfNull) {
        if (elem == null && !addIfNull) {
            if (col != null) {
                return new Vector<>(col);
            } else {
                return new Vector<>();
            }
        } else {
            if (col != null) {
                Vector<E> newList = new Vector<>(col);
                newList.add(elem);
                return newList;
            } else {
                return newVector(elem);
            }
        }
    }

    /**
     * Primitive Array 를 Wrapper Vector 형태로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 12. 15.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param elems
     * @return
     *
     * @since 2020. 12. 15.
     * @version 1.8.0
     */
    public static Vector<Double> newVector(double @Nullable... elems) {
        if (elems == null || elems.length == 0) {
            return new Vector<>();
        }

        Vector<Double> vector = new Vector<>(elems.length);

        for (double e : elems) {
            vector.add(e);
        }

        return vector;
    }

    /**
     * 배열 데이터를 새로운 {@link Vector} 로 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 12. 21.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <E>
     *            데이터 타입
     * @param elems
     *            데이터
     * @return
     *
     * @since 2020. 12. 21.
     * @version 1.8.0
     */
    public static <E extends @Nullable Object> Vector<E> newVector(E... elems) {
        Objects.requireNonNull(elems);

        return Stream.of(elems).collect(Collectors.toCollection(Vector<E>::new));
    }

    /**
     * 데이터를 추가하여 새로운 {@link Vector} 로 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 12. 21.        parkjunhong77@gmail.com         최초 작성
     * 2022. 12. 30.        parkjunhong77@gmail.com     내부 구현 변경 (see {@link #newVector(Collection, Object, boolean)})
     * </pre>
     *
     * @param <E>
     *            데이터 타입
     * @param elem
     *            데이터
     * @param col
     *            기존 데이터
     * @return
     *
     * @since 2020. 12. 21.
     * @version 1.8.0
     */
    public static <E extends @Nullable Object> Vector<E> newVector(E elem, @Nullable Collection<E> col) {
        return newVector(col, elem, true);
    }

    /**
     * 데이터를 추가하여 새로운 {@link Vector} 로 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 12. 21.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <E>
     *            데이터 타입
     * @param elem
     *            데이터
     * @param vector
     *            기존 데이터
     * @return
     *
     * @since 2020. 12. 21.
     * @version 1.8.0
     */
    public static <E extends @Nullable Object> Vector<E> newVector(E elem, @Nullable Vector<E> vector) {
        return newVector(vector, elem, true);
    }

    /**
     * Primitive Array 를 Wrapper Vector 형태로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 12. 15.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param elems
     * @return
     *
     * @since 2020. 12. 15.
     * @version 1.8.0
     */
    public static Vector<Integer> newVector(int @Nullable... elems) {
        if (elems == null || elems.length == 0) {
            return new Vector<>();
        }

        Vector<Integer> vector = new Vector<>(elems.length);

        for (int e : elems) {
            vector.add(e);
        }

        return vector;
    }

    /**
     * Primitive Array 를 Wrapper Vector 형태로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 12. 15.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param elems
     * @return
     *
     * @since 2020. 12. 15.
     * @version 1.8.0
     */
    public static Vector<Long> newVector(long @Nullable... elems) {
        if (elems == null || elems.length == 0) {
            return new Vector<>();
        }

        Vector<Long> vector = new Vector<>(elems.length);

        for (long e : elems) {
            vector.add(e);
        }

        return vector;
    }

    /**
     * Primitive Array 를 Wrapper Vector 형태로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 12. 15.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param elems
     * @return
     *
     * @since 2020. 12. 15.
     * @version 1.8.0
     */
    public static Vector<Short> newVector(short @Nullable... elems) {
        if (elems == null || elems.length == 0) {
            return new Vector<>();
        }

        Vector<Short> vector = new Vector<>(elems.length);

        for (short e : elems) {
            vector.add(e);
        }

        return vector;
    }

    /**
     * 데이터를 추가하여 새로운 {@link Vector}를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 12. 30.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <E>
     *            데이터 타입
     * @param vector
     *            기존 {@link Vector}
     * @param elem
     *            새로운 데이터
     * @return
     *
     * @since 2022. 12. 30.
     */
    public static <E extends @Nullable Object> Vector<E> newVector(@Nullable Vector<E> vector, E elem) {
        return newVector(vector, elem, true);
    }

    /**
     * 데이터를 추가하여 새로운 {@link Vector} 로 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 12. 21.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <E>
     *            데이터 타입
     * @param vector
     *            기존 데이터
     * @param elems
     *            데이터
     * @return
     *
     * @since 2020. 12. 21.
     * @version 1.8.0
     */
    public static <E extends @Nullable Object> Vector<E> newVector(@Nullable Vector<E> vector, E @Nullable... elems) {
        if (vector == null && elems == null) {
            return new Vector<>();
        } else if (elems == null) {
            return new Vector<>(vector);
        } else if (vector == null) {
            return newVector(elems);
        } else {
            Vector<E> newVector = new Vector<>(vector);
            for (E e : elems) {
                newVector.add(e);
            }
            return newVector;
        }
    }

    /**
     * 주어진 {@link Collection}을 정렬해서 {@link Set}으로 반환합니다.
     * 
     * @param col
     * @param asc
     *            정렬 방향. <br>
     *            <b>{@code true: asc, false: desc}</b>
     * @return
     */
    public static <E extends @Nullable Comparable<E>> Collection<E> order(Collection<E> col, boolean asc) {
        Objects.requireNonNull(col);

        ConcurrentSkipListSet<E> orderedSet = new ConcurrentSkipListSet<E>(asc ? new AscComparator<E>() : new DescComparator<E>());
        orderedSet.addAll(col);
        return orderedSet;
    }

    /**
     * 주어진 {@link Collection}을 정렬해서 {@link Set}으로 반환합니다.
     * 
     * @param col
     * @param asc
     *            정렬 방향. <br>
     *            <b>{@code true: asc, false: desc}</b>
     * @return
     */
    public static <E extends Comparable<E>> Collection<E> order(Collection<E> col, Comparator<E> comparator) {
        ConcurrentSkipListSet<E> orderedSet = new ConcurrentSkipListSet<E>(comparator);
        orderedSet.addAll(col);
        return orderedSet;
    }

    /**
     * 주어진 {@link Set}을 정렬한 후 새로운 {@link Set}을 반환합니다.
     * 
     * @param set
     * 
     * @return 정렬된 새로운 객체
     * 
     * @since 2012. 02. 15.
     */
    public static <K extends Comparable<K>, V> Set<Entry<K, V>> orderEntrySet(Set<Entry<K, V>> set) {
        Objects.requireNonNull(set);

        Set<Entry<K, V>> returnedSet = new ConcurrentSkipListSet<Map.Entry<K, V>>();
        returnedSet.addAll(set);

        return returnedSet;
    }

    /**
     * 주어진 {@link Map}을 정렬한 후 새로운 {@link Map}을 반환합니다.
     * 
     * @param map
     * @param asc
     * 
     * @return 정렬된 새로운 객체
     * 
     * @since 2012. 02. 15.
     */
    public static <K extends Comparable<K>, V> Map<K, V> orderMap(Map<K, V> map, boolean asc) {
        Objects.requireNonNull(map);

        Map<K, V> returnedMap = new ConcurrentSkipListMap<K, V>(asc ? new AscComparator<K>() : new DescComparator<K>());
        returnedMap.putAll(map);

        return returnedMap;
    }

    /**
     * 주어진 {@link Map}을 정렬한 후 새로운 {@link Map}을 반환합니다.
     * 
     * @param map
     * @param comparator
     * 
     * @return 정렬된 새로운 객체
     * 
     * @since 2012. 02. 15.
     */
    public static <K extends Comparable<K>, V> Map<K, V> orderMap(Map<K, V> map, Comparator<K> comparator) {
        Objects.requireNonNull(map);

        Map<K, V> returnedMap = new ConcurrentSkipListMap<K, V>(comparator);
        returnedMap.putAll(map);

        return returnedMap;
    }

    /**
     * 조건에 맞는 데이터를 정렬하고 지정된 개수만큼 반환합니다. (원본 유지) <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 3..        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <T>
     *            데이터 타입
     * @param data
     *            원본 데이터
     * @param sorter
     *            정렬 함수
     * @return
     *
     * @since 2025. 9. 3..
     * @version 2.1.0
     */
    public static <T> List<T> parallelSort(Collection<T> data, Comparator<T> sorter) {
        ObjectUtils.requireNonNulls(data, sorter);

        return data.parallelStream() //
                .sorted(sorter)//
                .collect(Collectors.toList());
    }

    /**
     * 조건에 맞는 데이터를 정렬하고 지정된 개수만큼 반환합니다. (원본 유지) <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 3..        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <T>
     *            데이터 타입
     * @param data
     *            원본 데이터
     * @param filter
     *            데이터 필터
     * @param sorter
     *            정렬 함수
     * @return
     *
     * @since 2025. 9. 3..
     * @version 2.1.0
     */
    public static <T> List<T> parallelSort(Collection<T> data, Predicate<T> filter, Comparator<T> sorter) {
        ObjectUtils.requireNonNulls(data, filter, sorter);

        return data.parallelStream() //
                .filter(filter) // 필터 적용
                .sorted(sorter)// 정렬
                .collect(Collectors.toList());
    }

    /**
     * 정렬되지 않은 2개의 {@link Collection}를 상호 정렬하여 하나의 {@link List}로 제공합니다. <br>
     * 모두 정렬되어 있다면, {@link #sort(List, List, Function)}를 사용하기 바랍니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 3.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     * 
     * @param <E>
     *            데이터 유형
     * @param <KEY>
     *            식별정보 유형
     * @param data1
     *            데이터#1
     * @param data2
     *            데이터#2
     * @param keyProvider
     *            데이터 식별정보 제공 함수
     * @return
     *
     * @since 2025. 9. 3.
     * @version 2.1.0
     */
    public static <E, KEY extends Comparable<KEY>> List<E> parallelSortAndMerge(Collection<E> data1, Collection<E> data2, Function<E, KEY> keyProvider) {
        return parallelSortAndMerge(data1, keyProvider, StreamUtils.identity(), data2, keyProvider, StreamUtils.identity(), Comparator.naturalOrder());
    }

    /**
     * 정렬되지 않은 2개의 {@link Collection}를 상호 정렬하여 하나의 {@link List}로 제공합니다. <br>
     * 모두 정렬되어 있다면, {@link #sort(List, List, Function, Function)}를 사용하기 바랍니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 3.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     * 
     * @param <E>
     *            데이터 유형
     * @param <KEY>
     *            식별정보 유형
     * @param <R>
     *            새로운 데이터 유형 (E &rarr; R)
     * @param data1
     *            데이터#1
     * @param data2
     *            데이터#2
     * @param keyProvider
     *            데이터 식별정보 제공 함수
     * @param transformer
     *            데이터 변환 함수 (E &rarr; R)
     *
     * @return
     *
     * @since 2025. 9. 3.
     * @version 2.1.0
     */
    public static <E, KEY extends Comparable<KEY>, R> List<R> parallelSortAndMerge(Collection<E> data1, Collection<E> data2, Function<E, KEY> keyProvider,
            Function<E, R> transformer) {
        return parallelSortAndMerge(data1, keyProvider, transformer, data2, keyProvider, transformer, Comparator.naturalOrder());
    }

    /**
     * 정렬되지 않은 2개의 {@link Collection}를 상호 정렬하여 하나의 {@link List}로 제공합니다. <br>
     * 모두 정렬되어 있다면, {@link #sort(List, Function, Function, List, Function, Function)}를 사용하기 바랍니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 3.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <E1>
     *            데이터 유형1
     * @param <E2>
     *            데이터 유형2
     * @param <KEY>
     *            식별정보 유형
     * @param <R>
     *            새로운 데이터 유형 (E1 &rarr; R, E2 &rarr; R)
     * @param data1
     *            데이터#1
     * @param keyProvider1
     *            데이터#1 식별정보 제공 함수
     * @param transformer1
     *            데이터#1 변환 함수 (E1 &rarr; R)
     * @param data2
     *            데이터#2
     * @param keyProvider2
     *            데이터#2 식별정보 제공 함수
     * @param transformer2
     *            데이터#2 변환 함수 (E2 &rarr; R)
     * @return
     *
     * @since 2025. 9. 3.
     * @version 2.1.0
     */
    public static <E1, E2, KEY extends Comparable<KEY>, R> List<R> parallelSortAndMerge( //
            Collection<E1> data1, Function<E1, KEY> keyProvider1, Function<E1, R> transformer1 //
            , Collection<E2> data2, Function<E2, KEY> keyProvider2, Function<E2, R> transformer2 //
    ) {
        return parallelSortAndMerge(data1, keyProvider1, transformer1, data2, keyProvider2, transformer2, Comparator.naturalOrder());
    }

    /**
     * 정렬되지 않은 2개의 {@link Collection}를 상호 정렬하여 하나의 {@link List}로 제공합니다. <br>
     * 모두 정렬되어 있다면, {@link #sort(List, Function, Function, List, Function, Function)}를 사용하기 바랍니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 3.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <E1>
     *            데이터 유형1
     * @param <E2>
     *            데이터 유형2
     * @param <KEY>
     *            식별정보 유형
     * @param <R>
     *            새로운 데이터 유형 (E1 &rarr; R, E2 &rarr; R)
     * @param data1
     *            데이터#1
     * @param keyProvider1
     *            데이터#1 식별정보 제공 함수
     * @param transformer1
     *            데이터#1 변환 함수 (E1 &rarr; R)
     * @param data2
     *            데이터#2
     * @param keyProvider2
     *            데이터#2 식별정보 제공 함수
     * @param transformer2
     *            데이터#2 변환 함수 (E2 &rarr; R)
     * @return
     *
     * @since 2025. 9. 3.
     * @version 2.1.0
     */
    public static <E1, E2, KEY extends Comparable<KEY>, R> List<R> parallelSortAndMerge( //
            Collection<E1> data1, Function<E1, KEY> keyProvider1, Function<E1, R> transformer1 //
            , Collection<E2> data2, Function<E2, KEY> keyProvider2, Function<E2, R> transformer2 //
            , Comparator<KEY> comparator //
    ) {
        @NonNull
        List<E1> sortedData1 = data1.parallelStream().sorted((o1, o2) -> keyProvider1.apply(o1).compareTo(keyProvider1.apply(o2))).collect(Collectors.toList());
        @NonNull
        List<E2> sortedData2 = data2.parallelStream().sorted((o1, o2) -> keyProvider2.apply(o1).compareTo(keyProvider2.apply(o2))).collect(Collectors.toList());

        return sort(sortedData1, keyProvider1, transformer1, sortedData2, keyProvider2, transformer2, comparator);
    }

    /**
     * 주어진 {@link Collection}에 포함된 첫번째 데이터를 반환합니다.
     * 
     * @param col
     * @return 첫번째 데이터. 없는 경우 {@code null}.
     */
    public static <E extends @Nullable Object> @Nullable E pick(Collection<E> col) {
        Objects.requireNonNull(col);

        Iterator<E> itr = col.iterator();
        if (itr.hasNext()) {
            return itr.next();
        }

        return null;
    }

    /**
     * 주어진 {@link List}에서 정해진 개수만큼 데이터를 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2017. 10. 18.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param list
     * @param pos
     *            시작 위치
     * @param maxCount
     *            읽어올 개수
     * @return
     * 
     * @since 2017. 10. 18.
     */
    public static <E extends @Nullable Object> List<E> read(List<E> list, int pos, int maxCount) {
        Objects.requireNonNull(list);

        List<E> read = new ArrayList<>();

        if (pos >= list.size()) {
            return read;
        }

        int limit = Math.min(list.size(), pos + maxCount);

        for (int i = pos; i < limit; i++) {
            read.add(list.get(i));
        }

        return read;
    }

    /**
     * 주어진 {@link List}에서 정해진 개수만큼 데이터를 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2017. 10. 18.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param list
     * @param pos
     *            시작 위치
     * @param maxCount
     *            읽어올 개수
     * @param type
     *            {@link List}에 포함되어 있는 데이터 타입
     * 
     * @return {@link List}가 {@code null}이거나 없는 경우
     * 
     * @since 2017. 10. 18.
     */
    public static <E extends @Nullable Object> E[] readAsArray(List<E> list, int pos, int maxCount, Class<E> type) {
        ObjectUtils.requireNonNulls(list, type);

        if (pos >= list.size()) {
            return (E[]) Array.newInstance(type, 0);
        }

        int len = Math.min(list.size() - pos, maxCount);
        E[] read = (E[]) Array.newInstance(type, len);

        return list.subList(pos, pos + len).toArray(read);
    }

    /**
     * 조건에 맞는 데이터를 정렬하고 지정된 개수만큼 반환합니다. (원본 유지) <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2023. 12. 13.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <T>
     *            데이터 타입
     * @param data
     *            원본 데이터
     * @param sorter
     *            정렬 함수
     * @return
     *
     * @since 2023. 12. 13.
     * @version 2.0.0
     */
    public static <T extends @Nullable Object> List<T> sort(Collection<T> data, Comparator<T> sorter) {
        return sort(data, Predicates.alwaysTrue(), sorter);
    }

    /**
     * 조건에 맞는 데이터를 정렬하고 지정된 개수만큼 반환합니다. (원본 유지) <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2023. 12. 13.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <T>
     *            데이터 타입
     * @param data
     *            원본 데이터
     * @param filter
     *            데이터 필터
     * @param sorter
     *            정렬 함수
     * @return
     *
     * @since 2023. 12. 13.
     * @version 2.0.0
     */
    public static <T extends @Nullable Object> List<T> sort(Collection<T> data, Predicate<T> filter, Comparator<T> sorter) {
        ObjectUtils.requireNonNulls(data, filter, sorter);

        return data.stream() //
                .filter(filter) // 필터 적용
                .sorted(sorter)// 정렬
                .collect(Collectors.toList());
    }

    /**
     * 정렬된 2개의 {@link List}를 상호 정렬하여 하나의 {@link List}로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 3.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <E>
     *            데이터 유형
     * @param <KEY>
     *            식별정보 유형
     * @param data1
     *            정렬된 데이터#1
     * @param data2
     *            정렬된 데이터#2
     * @param keyProvider
     *            데이터 식별정보 제공 함수
     * @return
     *
     * @since 2025. 9. 3.
     * @version 2.1.0
     */
    public static <E, KEY extends Comparable<KEY>> List<E> sort(List<E> data1, List<E> data2, Function<E, KEY> keyProvider) {
        return sort(data1, keyProvider, StreamUtils.identity(), data2, keyProvider, StreamUtils.identity(), Comparator.naturalOrder());
    }

    /**
     * 정렬된 2개의 {@link List}를 상호 정렬하여 하나의 {@link List}로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 3.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <E>
     *            데이터 유형
     * @param <KEY>
     *            식별정보 유형
     * @param <R>
     *            새로운 데이터 유형 (E &rarr; R)
     * @param data1
     *            정렬된 데이터#1
     * @param data2
     *            정렬된 데이터#2
     * @param keyProvider
     *            데이터 식별정보 제공 함수
     * @param transforme
     *            데이터 변환 함수 (E &rarr; R)
     * @return
     *
     * @since 2025. 9. 3.
     * @version 2.1.0
     */
    public static <E, KEY extends Comparable<KEY>, R> List<R> sort(List<E> data1, List<E> data2, Function<E, KEY> keyProvider, Function<E, R> transformer) {
        return sort(data1, keyProvider, transformer, data2, keyProvider, transformer, Comparator.naturalOrder());
    }

    /**
     * 정렬된 2개의 {@link List}를 상호 정렬하여 하나의 {@link List}로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 3.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <E>
     *            데이터 유형
     * @param <KEY>
     *            식별정보 유형
     * @param <R>
     *            새로운 데이터 유형 (E1 &rarr; R, E2 &rarr; R)
     * @param data1
     *            정렬된 데이터#1
     * @param data2
     *            정렬된 데이터#2
     * @param keyProvider
     *            데이터#1 식별정보 제공 함수
     * @param transformer
     *            데이터#1 변환 함수 (E1 &rarr; R)
     * @param comparator
     *            'KEY' 값 우선순위 비교 함수
     * @return
     *
     * @since 2025. 9. 3.
     * @version 2.1.0
     */
    public static <E, KEY, R> List<R> sort(List<E> data1, List<E> data2, Function<E, KEY> keyProvider, Function<E, R> transformer, Comparator<KEY> comparator) {
        return sort(data1, keyProvider, transformer, data2, keyProvider, transformer, comparator);
    }

    /**
     * 정렬된 2개의 {@link List}를 상호 정렬하여 하나의 {@link List}로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 3.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <E1>
     *            데이터 유형1
     * @param <E2>
     *            데이터 유형2
     * @param <KEY>
     *            식별정보 유형
     * @param <R>
     *            새로운 데이터 유형 (E1 &rarr; R, E2 &rarr; R)
     * @param data1
     *            정렬된 데이터#1
     * @param keyProvider1
     *            데이터#1 식별정보 제공 함수
     * @param transformer1
     *            데이터#1 변환 함수 (E1 &rarr; R)
     * @param data2
     *            정렬된 데이터#2
     * @param keyProvider2
     *            데이터#2 식별정보 제공 함수
     * @param transformer2
     *            데이터#2 변환 함수 (E2 &rarr; R)
     * @return
     *
     * @since 2025. 9. 3.
     * @version 2.1.0
     */
    public static <E1, E2, KEY extends Comparable<KEY>, R> List<R> sort( //
            List<E1> data1, Function<E1, KEY> keyProvider1, Function<E1, R> transformer1 //
            , List<E2> data2, Function<E2, KEY> keyProvider2, Function<E2, R> transformer2 //
    ) {
        return sort(data1, keyProvider1, transformer1, data2, keyProvider2, transformer2, Comparator.naturalOrder());
    }

    /**
     * 정렬된 2개의 {@link List}를 상호 정렬하여 하나의 {@link List}로 제공합니다. <br>
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 3.      parkjunhong77@gmail.com         최초 작성
     * 2026. 3. 5.      parkjunhong77@gmail.com         (3.0.0) ArrayList 용량 사전 할당 및 null 데이터 대응 개선, 동시성 제약 Javadoc 명시
     * </pre>
     *
     * <p>
     * <b>[동시성(Concurrency) 주의사항]</b><br>
     * 이 메소드는 성능 최적화를 위해 내부적으로 방어적 복사(Defensive Copy)를 수행하지 않고 원본 리스트의 {@link Iterator}를 직접 사용합니다. 따라서 멀티스레드 환경에서 병합 작업
     * 도중 원본 리스트({@code data1}, {@code data2})에 구조적인 변경(추가/삭제 등)이 발생할 경우
     * {@link java.util.ConcurrentModificationException}이 발생할 수 있습니다. 동시 수정이 예상되는 환경에서는 호출자가 외부에서 동기화(Lock)를 보장하거나,
     * {@link java.util.concurrent.CopyOnWriteArrayList}와 같은 스레드 안전한 컬렉션을 전달해야 합니다.
     * </p>
     *
     * @param <E1>
     *            데이터 유형1
     * @param <E2>
     *            데이터 유형2
     * @param <KEY>
     *            식별정보 유형
     * @param <R>
     *            새로운 데이터 유형 (E1 &rarr; R, E2 &rarr; R)
     * @param data1
     *            정렬된 데이터#1
     * @param keyProvider1
     *            데이터#1 식별정보 제공 함수
     * @param transformer1
     *            데이터#1 변환 함수 (E1 &rarr; R)
     * @param data2
     *            정렬된 데이터#2
     * @param keyProvider2
     *            데이터#2 식별정보 제공 함수
     * @param transformer2
     *            데이터#2 변환 함수 (E2 &rarr; R)
     * @param comparator
     *            'KEY' 값 우선순위 비교 함수
     * @return 병합 및 정렬된 새로운 리스트
     *
     * @since 2025. 9. 3.
     * @version 3.0.0
     */
    public static <E1, E2, KEY, R> List<R> sort( //
            List<E1> data1, Function<E1, KEY> keyProvider1, Function<E1, R> transformer1, //
            List<E2> data2, Function<E2, KEY> keyProvider2, Function<E2, R> transformer2, //
            Comparator<KEY> comparator //
    ) {
        ObjectUtils.requireNonNulls(data1, keyProvider1, transformer1, data2, keyProvider2, transformer2, comparator);

        // 두 리스트의 크기 합산만큼 초기 용량(Capacity)을 할당하여 Resizing 오버헤드 완벽 제거
        List<R> sorted = new ArrayList<>(data1.size() + data2.size());

        Iterator<E1> itrData1 = data1.iterator();
        Iterator<E2> itrData2 = data2.iterator();

        // 리스트 내부에 실제 null 값이 들어있을 경우를 대비하여,
        // 값의 null 여부가 아닌 Iterator의 상태 자체를 boolean으로 추적
        boolean hasD1 = itrData1.hasNext();
        boolean hasD2 = itrData2.hasNext();
        @Nullable
        E1 d1 = hasD1 ? itrData1.next() : null;
        @Nullable
        E2 d2 = hasD2 ? itrData2.next() : null;

        while (hasD1 && hasD2) {
            int compared = comparator.compare(keyProvider1.apply(d1), keyProvider2.apply(d2));
            if (compared < 0) {
                sorted.add(transformer1.apply(d1));
                hasD1 = itrData1.hasNext();
                if (hasD1)
                    d1 = itrData1.next();
            } else if (compared > 0) {
                sorted.add(transformer2.apply(d2));
                hasD2 = itrData2.hasNext();
                if (hasD2)
                    d2 = itrData2.next();
            } else {
                sorted.add(transformer1.apply(d1));
                sorted.add(transformer2.apply(d2));
                hasD1 = itrData1.hasNext();
                if (hasD1) {
                    d1 = itrData1.next();
                }
                hasD2 = itrData2.hasNext();
                if (hasD2) {
                    d2 = itrData2.next();
                }
            }
        }

        // forEachRemaining을 활용하여 잔여 데이터 처리 로직을 간결하고 빠르게 최적화
        if (hasD1) {
            sorted.add(transformer1.apply(d1));
            itrData1.forEachRemaining(e -> sorted.add(transformer1.apply(e)));
        } else if (hasD2) {
            sorted.add(transformer2.apply(d2));
            itrData2.forEachRemaining(e -> sorted.add(transformer2.apply(e)));
        }

        return sorted;
    }

    /**
     * 정렬되지 않은 2개의 {@link Collection}를 상호 정렬하여 하나의 {@link List}로 제공합니다. <br>
     * 모두 정렬되어 있다면, {@link #sort(List, List, Function)}를 사용하기 바랍니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 3.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     * 
     * @param <E>
     *            데이터 유형
     * @param <KEY>
     *            식별정보 유형
     * @param data1
     *            데이터#1
     * @param data2
     *            데이터#2
     * @param keyProviders
     *            데이터 식별정보 제공 함수
     * @return
     *
     * @since 2025. 9. 3.
     * @version 2.1.0
     */
    public static <E, KEY extends Comparable<KEY>> List<E> sortAndMerge(Collection<E> data1, Collection<E> data2, Function<E, KEY> keyProvider) {
        return sortAndMerge(data1, keyProvider, StreamUtils.identity(), data2, keyProvider, StreamUtils.identity(), Comparator.naturalOrder());
    }

    /**
     * 정렬되지 않은 2개의 {@link Collection}를 상호 정렬하여 하나의 {@link List}로 제공합니다. <br>
     * 모두 정렬되어 있다면, {@link #sort(List, List, Function, Function)}를 사용하기 바랍니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 3.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     * 
     * @param <E>
     *            데이터 유형
     * @param <KEY>
     *            식별정보 유형
     * @param <R>
     *            새로운 데이터 유형 (E &rarr; R)
     * @param data1
     *            데이터#1
     * @param data2
     *            데이터#2
     * @param keyProvider
     *            데이터 식별정보 제공 함수
     * @param transformer
     *            데이터 변환 함수 (E &rarr; R)
     *
     * @return
     *
     * @since 2025. 9. 3.
     * @version 2.1.0
     */
    public static <E, KEY extends Comparable<KEY>, R> List<R> sortAndMerge(Collection<E> data1, Collection<E> data2, Function<E, KEY> keyProvider, Function<E, R> transformer) {
        return sortAndMerge(data1, keyProvider, transformer, data2, keyProvider, transformer, Comparator.naturalOrder());
    }

    /**
     * 정렬되지 않은 2개의 {@link Collection}를 상호 정렬하여 하나의 {@link List}로 제공합니다. <br>
     * 모두 정렬되어 있다면, {@link #sort(List, Function, Function, List, Function, Function)}를 사용하기 바랍니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 3.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <E1>
     *            데이터 유형1
     * @param <E2>
     *            데이터 유형2
     * @param <KEY>
     *            식별정보 유형
     * @param <R>
     *            새로운 데이터 유형 (E1 &rarr; R, E2 &rarr; R)
     * @param data1
     *            데이터#1
     * @param keyProvider1
     *            데이터#1 식별정보 제공 함수
     * @param transformer1
     *            데이터#1 변환 함수 (E1 &rarr; R)
     * @param data2
     *            데이터#2
     * @param keyProvider2
     *            데이터#2 식별정보 제공 함수
     * @param transformer2
     *            데이터#2 변환 함수 (E2 &rarr; R)
     * @return
     *
     * @since 2025. 9. 3.
     * @version 2.1.0
     */
    public static <E1, E2, KEY extends Comparable<KEY>, R> List<R> sortAndMerge( //
            Collection<E1> data1, Function<E1, KEY> keyProvider1, Function<E1, R> transformer1 //
            , Collection<E2> data2, Function<E2, KEY> keyProvider2, Function<E2, R> transformer2 //
    ) {
        return sortAndMerge(data1, keyProvider1, transformer1, data2, keyProvider2, transformer2, Comparator.naturalOrder());
    }

    /**
     * 정렬되지 않은 2개의 {@link Collection}를 상호 정렬하여 하나의 {@link List}로 제공합니다. <br>
     * 모두 정렬되어 있다면, {@link #sort(List, Function, Function, List, Function, Function, Comparator)}를 사용하기 바랍니다.
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 3.      parkjunhong77@gmail.com         최초 작성
     * 2026. 3. 9.      parkjunhong77@gmail.com         (3.0.0) JDK 25 마이그레이션: 정렬 기준(Comparator) 누락 버그 수정 및 Stream 최적화
     * </pre>
     *
     * @param <E1>
     *            데이터 유형1
     * @param <E2>
     *            데이터 유형2
     * @param <KEY>
     *            식별정보 유형
     * @param <R>
     *            새로운 데이터 유형 (E1 &rarr; R, E2 &rarr; R)
     * @param data1
     *            데이터#1
     * @param keyProvider1
     *            데이터#1 식별정보 제공 함수
     * @param transformer1
     *            데이터#1 변환 함수 (E1 &rarr; R)
     * @param data2
     *            데이터#2
     * @param keyProvider2
     *            데이터#2 식별정보 제공 함수
     * @param transformer2
     *            데이터#2 변환 함수 (E2 &rarr; R)
     * @param comparator
     *            'KEY' 값 우선순위 비교 함수
     * @return 병합 및 정렬된 새로운 리스트
     *
     * @since 2025. 9. 3.
     * @version 3.0.0
     */
    public static <E1, E2, KEY, R> List<R> sortAndMerge( //
            Collection<E1> data1, Function<E1, KEY> keyProvider1, Function<E1, R> transformer1 //
            , Collection<E2> data2, Function<E2, KEY> keyProvider2, Function<E2, R> transformer2 //
            , Comparator<KEY> comparator //
    ) {
        ObjectUtils.requireNonNulls(data1, keyProvider1, transformer1, data2, keyProvider2, transformer2, comparator);

        // 1. 파라미터로 전달받은 comparator를 적용하여 일관된 정렬 보장
        // 2. Stream.toList()를 활용하여 불필요한 가변 ArrayList 생성 방지
        @NonNull
        List<E1> sortedData1 = data1.stream().sorted(Comparator.comparing(keyProvider1, comparator)).toList();
        @NonNull
        List<E2> sortedData2 = data2.stream().sorted(Comparator.comparing(keyProvider2, comparator)).toList();

        return sort(sortedData1, keyProvider1, transformer1, sortedData2, keyProvider2, transformer2, comparator);
    }

    /**
     * {@link List}에서 주어진 범위({@code begin} ~ {@code end}) 내의 원소들을 포함하는 새로운 독립된 {@link List}를 반환합니다.<br>
     * 동일한 기능을 제공하는 {@link List#subList(int, int)}가 있는데, 일반적으로 전달받은 {@link List} 객체를 내부적으로 유지하는 {@code delegate} 방식으로
     * 제공되어 메모리 누수의 원인이 될 수 있으므로, 이 메소드는 새로운 복사본을 생성합니다.
     * 
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2011. 10. 24.    parkjunhong77@gmail.com         최초 작성 (기존 코드 마이그레이션)
     * 2026. 3. 9.      parkjunhong77@gmail.com         (3.0.0) JDK 25 최적화: LinkedList O(N^2) 병목 제거 및 네이티브 배열 복사 적용
     * </pre>
     *
     * @param <E>
     *            데이터 유형
     * @param list
     *            원본 리스트
     * @param begin
     *            시작 위치 (inclusive)
     * @return 지정된 범위의 데이터를 갖는 새로운 리스트
     *
     * @since 2011. 10. 24.
     * @version 3.0.0
     */
    public static <E extends @Nullable Object> List<E> subCollection(List<E> list, int begin) {
        return subCollection(list, begin, list.size());
    }

    /**
     * {@link List}에서 주어진 범위({@code begin} ~ {@code end}) 내의 원소들을 포함하는 새로운 독립된 {@link List}를 반환합니다.<br>
     * 동일한 기능을 제공하는 {@link List#subList(int, int)}가 있는데, 일반적으로 전달받은 {@link List} 객체를 내부적으로 유지하는 {@code delegate} 방식으로
     * 제공되어 메모리 누수의 원인이 될 수 있으므로, 이 메소드는 새로운 복사본을 생성합니다.
     * 
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2011. 10. 24.    parkjunhong77@gmail.com         최초 작성 (기존 코드 마이그레이션)
     * 2026. 3. 9.      parkjunhong77@gmail.com         (3.0.0) JDK 25 최적화: LinkedList O(N^2) 병목 제거 및 네이티브 배열 복사 적용
     * </pre>
     *
     * @param <E>
     *            데이터 유형
     * @param list
     *            원본 리스트
     * @param begin
     *            시작 위치 (inclusive)
     * @param end
     *            종료 위치 (exclusive)
     * @return 지정된 범위의 데이터를 갖는 새로운 리스트
     *
     * @since 2011. 10. 24.
     * @version 3.0.0
     */
    public static <E extends @Nullable Object> List<E> subCollection(List<E> list, int begin, int end) {
        Objects.requireNonNull(list);

        if (list.size() <= begin) {
            return new ArrayList<>();
        }

        // 인덱스 정규화 (IndexOutOfBoundsException 원천 차단)
        int validBegin = Math.max(0, begin);
        int validEnd = Math.min(list.size(), end);

        // 유효하지 않은 범위일 경우 빈 리스트 반환
        if (validBegin >= validEnd) {
            return new ArrayList<>();
        }

        // RandomAccess(ArrayList)와 SequentialAccess(LinkedList) 모두에서
        // 최상의 성능(O(N))을 내며 내부 배열(System.arraycopy)을 복사하는 가장 우아한 방법
        return new ArrayList<>(list.subList(validBegin, validEnd));
    }

    /**
     * 주어진 {@link Collection}에 포함된 데이터를 Array에 넣어서 제공합니다. <br>
     *
     * <p>
     * <b>[제네릭 타입 소거(Type Erasure) 및 아키텍처 주의사항]</b><br>
     * 자바의 제네릭 구조는 컴파일 타임에만 유효하며, 런타임(실행 시점)에는 제네릭 타입 정보({@code <E>})가 모두 소거됩니다. 따라서 타입 파라미터가 없는
     * {@link Collection#toArray()}를 호출할 경우, 컬렉션은 자신의 데이터 타입을 알지 못해 무조건 부모 타입인 {@code Object[]} 메모리 블록을 할당하여 반환합니다.<br>
     * 이를 강제로 다운캐스팅({@code (E[]) col.toArray()})하여 반환할 경우, 컴파일 경고로 끝나지 않고 호출부에서 해당 배열을 사용할 때 런타임에 치명적인
     * {@link ClassCastException} 장애가 발생합니다.<br>
     * 본 유틸리티 메소드는 이러한 언어적 한계를 안전하게 극복하기 위해, {@code Class<E> type} 파라미터(타입 토큰)를 외부에서 명시적으로 주입받아 런타임에 정확한 타입의 네이티브 배열을 동적
     * 할당({@link java.lang.reflect.Array#newInstance})합니다.
     * </p>
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 4. 18.     parkjunhong77@gmail.com         최초 작성
     * 2026. 3. 9.      parkjunhong77@gmail.com         (3.0.0) JDK 25 마이그레이션: IntFunction 기반 최적화 및 타입 소거 제약 Javadoc 명시
     * </pre>
     *
     * @param <E>
     *            데이터 타입
     * @param col
     *            {@link Collection} 원본 데이터
     * @param type
     *            데이터 타입 Class (런타임 배열 생성을 위한 타입 토큰)
     *
     * @return 컬렉션의 데이터가 담긴 정확한 타입({@code E[]})의 새로운 배열
     *
     * @since 2018. 4. 18.
     * @version 2.1.0
     */
    public static <E> E[] toArray(Collection<E> col, Class<E> type) {
        Objects.requireNonNull(col);

        // JDK 11+: 컬렉션 구현체 내부의 System.arraycopy를 활용한 네이티브 고속 복사
        return col.toArray(size -> (E[]) Array.newInstance(type, size));
    }

    /**
     * 전달받은 {@link Collection} 데이터를 처리하여 새로운 {@link Collection} 구현체로 묶어서 제공합니다. <br>
     * <p>
     * <font color="red"><b>* 데이터(E)가 <b>{@code null}</b>인 경우 '전처리 과정'에서 제외시키므로, 데이터(E)를 처리하는 함수 객체는
     * <b>{@code null}</b>을 처리하지 않아도 됩니다.</b></font>
     * </p>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 21.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <E>
     *            데이터 유형 (Nullable)
     * @param <NE>
     *            새로운 데이터 유형 (Nullable). <br>
     *            단, {@code collectionSupplier}가 제공하는 {@link Collection} 구현체가 {@code null}을 허용해야 합니다.
     * @param <C>
     *            결과 {@link Collection} 유형
     * @param col
     *            데이터
     * @param transformer
     *            데이터 변환 함수
     * @param collectionSupplier
     *            결과 {@link Collection} 객체 제공 함수.
     * @return
     *
     * @since 2025. 8. 21.
     * @version 2.1.0
     */
    public static <E extends @Nullable Object, NE extends @Nullable Object, C extends Collection<NE>> //
            C toCollection(Collection<E> col, Function<E, NE> transformer, Supplier<C> collectionSupplier) {
        return StreamUtils.toCollection(col.stream(), transformer, collectionSupplier);
    }

    /**
     * 전달받은 {@link Collection} 데이터를 처리하여 새로운 {@link Collection} 구현체로 묶어서 제공합니다. <br>
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
     * @param <C>
     *            결과 {@link Collection} 유형
     * @param col
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
     * @since 2025. 8. 20.
     * @version 2.1.0
     */
    public static <K extends @Nullable Object, V, C extends Collection<V>> //
            C toCollection(Collection<V> col, Function<V, K> keyMapper, Function<V, V> valueMapper, BinaryOperator<V> mergeFunction, Supplier<C> collectionFactory) {
        return StreamUtils.toCollection(col.stream(), keyMapper, valueMapper, mergeFunction, collectionFactory);
    }

    /**
     * {@link Collection}에 포함된 데이터를 새로운 {@link List}에 담아 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 21.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <E>
     *            데이터 유형 (Nullable)
     * @param col
     *            데이터 제공 객체
     * @return
     *
     * @since 2025. 8. 21.
     * @version 2.1.0
     */
    public static <E extends @Nullable Object> List<E> toList(Collection<E> col) {
        return toList(col, (Supplier<List<E>>) ArrayList<E>::new);
    }

    /**
     * <p>
     * <font color="red"><b>* 데이터(E)가 <b>{@code null}</b>인 경우 '전처리 과정'에서 제외시키므로, 데이터(E)를 처리하는 함수 객체는
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
     * @param <E>
     *            데이터 유형
     * @param <NE>
     *            새로운 데이터 유형
     * @param col
     *            데이터
     * @param transformer
     *            데이터 변환 함수
     * @return
     *
     * @since 2025. 8. 21.
     * @version 2.1.0
     */
    public static <E extends @Nullable Object, NE extends @Nullable Object> List<NE> toList(Collection<E> col, Function<E, NE> transformer) {
        return toCollection(col, transformer, (Supplier<List<NE>>) ArrayList<NE>::new);
    }

    /**
     * <p>
     * <font color="red"><b>* 데이터(E)가 <b>{@code null}</b>인 경우 '전처리 과정'에서 제외시키므로, 데이터(E)를 처리하는 함수 객체는
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
     * @param <E>
     *            데이터 유형 (Nullable)
     * @param <NE>
     *            새로운 데이터 유형 (Nullable). <br>
     *            단, {@code listSupplier}가 제공하는 {@link List} 구현체가 {@code null}을 허용해야 합니다.
     * @param <L>
     *            결과 {@link List} 유형
     * @param col
     *            데이터 제공 객체
     * @param transformer
     *            데이터 변환 함수. (E &rarr; NE) <br>
     *            반환값으로 {@code null}을 제공할 수 있으나, 이 경우 제공된 결과 컬렉션이 {@code null}을 허용해야 합니다.
     * @param listSupplier
     *            결과 {@link Listr} 객체 제공 함수.
     * @return
     *
     * @since 2025. 8. 21.
     * @version 2.1.0
     */
    public static <E extends @Nullable Object, NE extends @Nullable Object, L extends List<NE>> L toList(Collection<E> col, Function<E, NE> transformer, Supplier<L> listSupplier) {
        return toCollection(col, transformer, listSupplier);
    }

    /**
     * {@link Collection}에 포함된 데이터를 새로운 {@link List}에 담아 제공합니다. <br>
     * 
     * <p>
     * <font color="red"><b>* 데이터(E)가 <b>{@code null}</b>인 경우 '전처리 과정'에서 제외시키므로, 데이터(V)를 처리하는 함수 객체는
     * <b>{@code null}</b>을 처리하지 않아도 됩니다.</b></font>
     * </p>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 21.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <E>
     *            데이터 유형 (Nullable)<br>
     *            단, {@code collectionSupplier}가 제공하는 {@link Collection} 구현체가 {@code null}을 허용해야 합니다.
     * @param <C>
     *            결과 {@link List} 유형
     * @param col
     *            데이터 제공 객체
     * @param collectionSupplier
     *            결과 {@link Collection} 객체 제공 함수.
     * @return
     *
     * @since 2025. 8. 21.
     * @version 2.1.0
     */
    public static <E extends @Nullable Object, C extends Collection<E>> C toList(Collection<E> col, Supplier<C> collectionSupplier) {
        return toCollection(col, StreamUtils.identity(), collectionSupplier);
    }

    /**
     * {@link Collection}에 포함된 값을 하나의 {@link List}로 묶어서 제공합니다. <br>
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
     * @param col
     *            데이터 제공 객체. (내부의 {@code null} 요소는 전처리 과정에서 안전하게 제거됩니다)
     * @param keyMapper
     *            객체의 식별정보를 제공하는 함수. (V &rarr; K)
     * @param mergeFunction
     *            2개의 객체 정보를 하나로 병합하는 함수. (V + V &rarr; V) <br>
     *            <font color="red"><b>주의: 반환값으로 절대 {@code null}을 제공해서는 안 됩니다.</b></font>
     * 
     * @return 중복이 병합된 결과 데이터가 담긴 새로운 컬렉션
     * 
     * @since 2025. 8. 20.
     * @version 2.1.0
     */
    public static <K extends @Nullable Object, V> List<V> toList(Collection<V> col, Function<V, K> keyMapper, BinaryOperator<V> mergeFunction) {
        return toCollection(col, keyMapper, StreamUtils.identity(), mergeFunction, (Supplier<List<V>>) ArrayList<V>::new);
    }

    /**
     * {@link Collection}에 포함된 값을 하나의 {@link List}로 묶어서 제공합니다. <br>
     * 단, {@code keyMapper}에 해당하는 값이 동일한 경우 {@code mergeFunction}를 통해서 객체를 하나로 병합합니다. <br>
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
     * @param <L>
     *            결과 {@link List} 유형
     * @param col
     *            데이터 제공 객체. (내부의 {@code null} 요소는 전처리 과정에서 안전하게 제거됩니다)
     * @param keyMapper
     *            객체의 식별정보를 제공하는 함수. (V &rarr; K)
     * @param mergeFunction
     *            2개의 객체 정보를 하나로 병합하는 함수. (V + V &rarr; V) <br>
     *            <font color="red"><b>주의: 반환값으로 절대 {@code null}을 제공해서는 안 됩니다.</b></font>
     * @param listFactory
     *            {@link List} 객체를 제공하는 함수.
     * 
     * @return 중복이 병합된 결과 데이터가 담긴 새로운 컬렉션
     *
     * @since 2025. 8. 20.
     * @version 2.1.0
     */
    public static <K extends @Nullable Object, V, L extends List<V>> L toList(Collection<V> col, Function<V, K> keyMapper, BinaryOperator<V> mergeFunction,
            Supplier<L> listFactory) {
        return toCollection(col, keyMapper, StreamUtils.identity(), mergeFunction, listFactory);
    }

    /**
     * {@link Collection}에 포함된 값을 하나의 {@link List}로 묶어서 제공합니다. <br>
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
     * @param col
     *            데이터 제공 객체. (내부의 {@code null} 요소는 전처리 과정에서 안전하게 제거됩니다)
     * @param valueMapper
     *            객체의 복제 또는 새로운 객체로 제공하는 함수. (V &rarr; V) <br>
     *            <font color="red"><b>주의: 반환값으로 절대 {@code null}을 제공해서는 안 됩니다.</b></font>
     * @param mergeFunction
     *            2개의 객체 정보를 하나로 병합하는 함수. (V + V &rarr; V) <br>
     *            <font color="red"><b>주의: 반환값으로 절대 {@code null}을 제공해서는 안 됩니다.</b></font>
     * 
     * @return 중복이 병합된 결과 데이터가 담긴 새로운 컬렉션
     *
     * @since 2025. 8. 20.
     * @version 2.1.0
     */
    public static <K extends @Nullable Object, V> List<V> toList(Collection<V> col, Function<V, K> keyMapper, Function<V, V> valueMapper, BinaryOperator<V> mergeFunction) {
        return toCollection(col, keyMapper, valueMapper, mergeFunction, (Supplier<List<V>>) ArrayList<V>::new);
    }

    /**
     * {@link Collection}에 포함된 값을 하나의 {@link List}로 묶어서 제공합니다. <br>
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
     * @param <L>
     *            결과 {@link List} 유형
     * @param col
     *            데이터 제공 객체. (내부의 {@code null} 요소는 전처리 과정에서 안전하게 제거됩니다)
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
     * @since 2025. 8. 20.
     * @version 2.1.0
     */
    public static <K extends @Nullable Object, V, L extends List<V>> L toList(Collection<V> col, Function<V, K> keyMapper, Function<V, V> valueMapper,
            BinaryOperator<V> mergeFunction, Supplier<L> listFactory) {
        return toCollection(col, keyMapper, valueMapper, mergeFunction, listFactory);
    }

    /**
     * <p>
     * <font color="red"><b>* 데이터(E)가 <b>{@code null}</b>인 경우 '전처리 과정'에서 제외시키므로, 데이터(E)를 처리하는 함수 객체는
     * <b>{@code null}</b>을 처리하지 않아도 됩니다.</b></font>
     * </p>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2018. 9. 12.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <E>
     *            데이터 유형 (Nullable)
     * @param <NE>
     *            새로운 데이터 유형 (Nullable).
     * @param stream
     *            데이터 제공 객체
     * @param transformer
     *            데이터 변환 함수. (E &rarr; NE) <br>
     * 
     * @return
     * 
     * @since 2018. 9. 12.
     */
    public static <E extends @Nullable Object, NE extends @Nullable Object> List<NE> toList(Stream<E> stream, Function<E, NE> transformer) {
        return StreamUtils.toCollection(stream, transformer, (Supplier<List<NE>>) ArrayList<NE>::new);
    }

    /**
     * {@link Collection} 데이터를 {@code keyMapper}로 구분되는 {@link Map} 형테로 제공합니다.<br>
     * 단, {@code keyMapper} 결과 값이 동일한 데이터의 경우 나중에 추가되는 데이터만 존재합니다.<br>
     * {@code keyMapper} 결과 값이 동일한 경우에 대해서 제어하고 싶은 경우,<br>
     * {@link StreamUtils#toMap(Stream, Function, Function, BinaryOperator, Supplier)} 또는 <br>
     * {@link StreamUtils#toMap(Stream, Function, BinaryOperator, Function, Supplier)} 를 사용하기 바랍니다.<br>
     * 데이터를 병합하지 않고 모두 유지하려는 경우<br>
     * {@link StreamUtils#toMap(Stream, Function, Function)},<br>
     * {@link StreamUtils#toMap(Stream, Function, Function, Supplier)},<br>
     * {@link StreamUtils#toMap(Stream, Function, Function, Supplier, Supplier)} 를 사용하기 바랍니다.<br>
     * <font color="red">단, 반환데이터 유형이 Map&lt;K,List&lt;E&gt;&gt; 형태로 현재 메소드이 반환 데이터와는 다른 점</font>을 유의하기 바랍니다.
     * <p>
     * <font color="red"><b>* 데이터(V)가 <b>{@code null}</b>인 경우 '전처리 과정'에서 제외시키므로, 데이터(V)를 처리하는 함수 객체는
     * <b>{@code null}</b>을 처리하지 않아도 됩니다.</b></font>
     * </p>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2017. 7. 60.		parkjunhong77@gmail.com			최초 작성
     * 2025. 8. 21.     parkjunhong77@gmail.com         {@link Stream} API로 변환. 
     * </pre>
     *
     * @param <E>
     *            데이터 식별정보 유형 (Nullable).
     * @param <K>
     *            데이터 식별정보 유형 (Nullable).
     * @param col
     *            데이터 제공 객체.
     * @param keyMapper
     *            데이터 식별정보 제공 함수.
     * @return
     * 
     * @since 2017. 7. 6.
     */
    public static <E extends @Nullable Object, K extends @Nullable Object> Map<K, E> toMap(Collection<E> col, Function<E, K> keyMapper) {
        return toMap(col, keyMapper, (Supplier<Map<K, E>>) HashMap<K, E>::new);
    }

    /**
     * Transform {@link Collection} to the specified {@link Map}.
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 2. 8.      parkjunhong77@gmail.com         최초 작성
     * 2026. 3. 9.      parkjunhong77@gmail.com         (3.0.0) JDK 25 마이그레이션: StreamUtils 위임 및 와일드카드(?, ?)를 통한 타입 안정성 확보
     * </pre>
     *
     * @param <E>
     *            데이터 유형
     * @param <K>
     *            데이터 식별정보 유형
     * @param <V>
     *            새로운 데이터 유형 (E &rarr; V)
     * @param <M>
     *            새로운 {@link Map} 타입
     * @param col
     *            원본 데이터 컬렉션
     * @param keyGen
     *            데이터 식별정보 제공 함수
     * @param valueGen
     *            원본 데이터로 새로운 데이터를 생성하는 함수 (E &rarr; V)
     * @return 키별로 그룹화된 컬렉션을 담은 맵
     *
     * @since 2018. 2. 8.
     */
    public static <E, K, V, M extends Map<K, Collection<V>>> M toMap(Collection<E> col, Function<E, K> keyGen, Function<E, V> valueGen) {
        ObjectUtils.requireNonNulls(col, keyGen, valueGen);

        // 비한정적 와일드카드(?, ?)를 사용하여 원시 타입(Raw Type) 경고를 방지하고 제네릭 안정성을 유지합니다.
        return (M) (Map<?, ?>) StreamUtils.toMap(col.stream(), keyGen, valueGen, HashMap::new);
    }

    /**
     * Transform {@link Collection} to the specified {@link Map}. <br>
     * *
     * <p>
     * <b>[데이터 병합 정책]</b><br>
     * {@code keyGen} 함수를 통해 생성된 키(Key)가 이미 Map에 존재하는 경우(키 중복), 원본 컬렉션의 <b>나중에 순회되는 요소가 이전 요소를 덮어씁니다(Overwrite).</b>
     * </p>
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 1. 30.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <E>
     *            원본 데이터 유형 (a type of a value)
     * @param <K>
     *            데이터 식별정보 유형 (a type of a key)
     * @param <M>
     *            대상 {@link Map} 유형 (a type of a Map)
     * @param col
     *            데이터를 추출할 원본 컬렉션
     * @param keyGen
     *            데이터 식별정보 제공 함수
     * @param map
     *            데이터가 담길 대상 Map 인스턴스
     * 
     * @return 데이터가 추가된 대상 Map 인스턴스
     *
     * @since 2020. 1. 30.
     * @version 1.6.17
     */
    public static <E extends @Nullable Object, K extends @Nullable Object, M extends Map<K, E>> M toMap(Collection<E> col, Function<E, K> keyGen, M map) {
        ObjectUtils.requireNonNulls(col, keyGen, map);

        // 컬렉션 내부 최적화를 활용하는 내부 반복자(Internal Iterator) 적용
        col.forEach(v -> map.put(keyGen.apply(v), v));

        return map;
    }

    /**
     * {@link Collection} 데이터를 {@code keyMapper}로 구분되는 {@link Map} 형테로 제공합니다.<br>
     * 단, {@code keyMapper} 결과 값이 동일한 데이터의 경우 나중에 추가되는 데이터만 존재합니다.<br>
     * {@code keyMapper} 결과 값이 동일한 경우에 대해서 제어하고 싶은 경우,<br>
     * {@link StreamUtils#toMap(Stream, Function, Function, BinaryOperator, Supplier)} 또는 <br>
     * {@link StreamUtils#toMap(Stream, Function, BinaryOperator, Function, Supplier)} 를 사용하기 바랍니다.<br>
     * 데이터를 병합하지 않고 모두 유지하려는 경우<br>
     * {@link StreamUtils#toMap(Stream, Function, Function)},<br>
     * {@link StreamUtils#toMap(Stream, Function, Function, Supplier)},<br>
     * {@link StreamUtils#toMap(Stream, Function, Function, Supplier, Supplier)} 를 사용하기 바랍니다.<br>
     * <font color="red">단, 반환데이터 유형이 Map&lt;K,List&lt;E&gt;&gt; 형태로 현재 메소드이 반환 데이터와는 다른 점</font>을 유의하기 바랍니다.
     * <p>
     * <font color="red"><b>* 데이터(V)가 <b>{@code null}</b>인 경우 '전처리 과정'에서 제외시키므로, 데이터(V)를 처리하는 함수 객체는
     * <b>{@code null}</b>을 처리하지 않아도 됩니다.</b></font>
     * </p>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2017. 7. 60.     parkjunhong77@gmail.com         최초 작성
     * 2025. 8. 21.     parkjunhong77@gmail.com         {@link Stream} API로 변환. 
     * </pre>
     *
     * @param <E>
     *            데이터 유형 (Nullable).
     * @param <K>
     *            데이터 식별정보 유형 (Nullable). <br>
     *            단, {@code mapSupplier}가 제공하는 {@link Map} 구현체가 {@code null} 키를 허용해야 합니다.
     * @param <M>
     *            결과 {@link Map} 유형
     * @param col
     *            데이터 제공 객체.
     * @param keyMapper
     *            객체의 식별정보를 제공하는 함수. (E &rarr; K)
     * @param mapSupplier
     *            결과 {@link Map} 객체 제공함수.
     * @return
     *
     * @since 2017. 7. 6.
     * @version 2.1.0
     */
    public static <E extends @Nullable Object, K extends @Nullable Object, M extends Map<K, E>> M toMap(Collection<E> col, Function<E, K> keyMapper, Supplier<M> mapSupplier) {
        return StreamUtils.toMap(col.stream(), keyMapper, StreamUtils.identity(), BinaryOperators.last(), mapSupplier);
    }

    /**
     * {@link Collection} 데이터를 새로운 형태로 변환하여 하나의 {@link Map}로 묶어서 제공합니다. <br>
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
     *            스트림 원본 데이터 유형 (Nullable)
     * @param <M>
     * @param col
     * @param keyMapper
     *            객체의 식별정보를 제공하는 함수.
     * @param mergeFunction
     *            2개의 객체 정보를 하나로 병합하는 함수. (V + V &rarr; V) <br>
     *            <font color="red"><b>주의: 반환값으로 절대 {@code null}을 제공해서는 안 됩니다.</b></font>
     * 
     * @return
     *
     * @since 2025. 8. 20.
     * @version 2.1.0
     */
    public static <K extends @Nullable Object, V extends @Nullable Object> Map<K, V> toMap(Collection<V> col, Function<V, K> keyMapper, BinaryOperator<V> mergeFunction) {
        return StreamUtils.toMap(col.stream(), keyMapper, StreamUtils.identity(), mergeFunction, (Supplier<Map<K, V>>) HashMap<K, V>::new);
    }

    /**
     * {@link Collection} 데이터를 새로운 형태로 변환하여 하나의 {@link Map}로 묶어서 제공합니다. <br>
     * 단, {@code keyMapper}에 해당하는 값이 동일한 경우 {@code mergeFunction}를 통해서 객체를 하나로 병합 ('V + V &rarr; V' &rarr; U) 합니다.
     * <p>
     * <font color="red"><b>* 데이터(V)가 <b>{@code null}</b>인 경우 '전처리 과정'에서 제외시키므로, 데이터(V)를 처리하는 함수 객체는
     * <b>{@code null}</b>을 처리하지 않아도 됩니다.</b></font>
     * </p>
     * 
     * <pre>
     * [개정이력]
     * 날짜 | 작성자 | 내용
     * ------------------------------------------
     * 2025. 8. 20. parkjunhong77@gmail.com 최초 작성
     * </pre>
     *
     * @param <K>
     *            데이터 식별정보 유형 (Nullable).
     * @param <V>
     *            스트림 원본 데이터 유형 (Nullable).
     * @param <U>
     *            변환된 새로운 데이터 유형 (Nullable).
     * @param <M>
     *            결과 {@link Map} 유형
     * @param col
     *            데이터 제공 객체
     * @param keyMapper
     *            객체의 식별정보를 제공하는 함수. (V &rarr; K)
     * @param mergeFunction
     *            2개의 객체 정보를 하나로 병합하는 함수. (V + V &rarr; V) <br>
     *            <font color="red"><b>주의: 중간 처리기({@link Collectors#toMap})의 제약으로 인해 반환값으로 절대 {@code null}을 제공해서는 안
     *            됩니다.</b></font>
     * @param transformer
     *            새로운 객체를 제공하는 함수. (V &rarr; U)
     * @return
     *
     * @since 2025. 8. 20.
     * @version 2.1.0
     */
    public static <K extends @Nullable Object, V, U> Map<K, U> toMap(Collection<V> col, Function<V, K> keyMapper, BinaryOperator<V> mergeFunction, Function<V, U> transformer) {
        return toMap(col, keyMapper, mergeFunction, transformer, (Supplier<Map<K, U>>) HashMap<K, U>::new);
    }

    /**
     * {@link Collection} 데이터를 새로운 형태로 변환하여 하나의 {@link Map}로 묶어서 제공합니다. <br>
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
     *            데이터 식별정보 유형 (Nullable). <br>
     *            단, {@code mapSupplier}가 제공하는 {@link Map} 구현체가 {@code null} 키를 허용해야 합니다.
     * @param <V>
     *            스트림 원본 데이터 유형 (Nullable).
     * @param <U>
     *            변환된 새로운 데이터 유형 (Nullable). <br>
     *            최종적으로 {@link Map#put}을 통해 할당되므로, {@code mapSupplier}가 제공하는 맵이 허용한다면 {@code null}이 가능합니다.
     * @param <M>
     *            결과 {@link Map} 유형
     * @param col
     *            데이터 제공 객체
     * @param keyMapper
     *            객체의 식별정보를 제공하는 함수. (V &rarr; K)
     * @param mergeFunction
     *            2개의 객체 정보를 하나로 병합하는 함수. (V + V &rarr; V) <br>
     *            <font color="red"><b>주의: 중간 처리기({@link Collectors#toMap})의 제약으로 인해 반환값으로 절대 {@code null}을 제공해서는 안
     *            됩니다.</b></font>
     * @param transformer
     *            새로운 객체를 제공하는 함수. (V &rarr; U) <br>
     *            {@code mapSupplier}의 구현체가 지원할 경우 반환값으로 {@code null} 제공이 가능합니다.
     * @param mapSupplier
     *            {@link Map} 제공함수.
     * 
     * @return
     *
     * @since 2025. 8. 20.
     * @version 2.1.0
     */
    public static <K extends @Nullable Object, V extends @Nullable Object, U extends @Nullable Object, M extends Map<K, U>> //
            M toMap(Collection<V> col, Function<V, K> keyMapper, BinaryOperator<V> mergeFunction, Function<V, U> transformer, Supplier<M> mapSupplier) {
        return StreamUtils.toMap(col.stream(), keyMapper, mergeFunction, transformer, mapSupplier);
    }

    /**
     * {@link Collection} 데이터를 새로운 형태로 변환하여 하나의 {@link Map}로 묶어서 제공합니다. <br>
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
     * @param <M>
     *            결과 {@link Map} 유형
     * @param col
     *            데이터 제공 객체
     * @param keyMapper
     *            객체의 식별정보를 제공하는 함수. (V &rarr; K)
     * @param mergeFunction
     *            2개의 객체 정보를 하나로 병합하는 함수. ( V + V &rarr; V)<br>
     *            <font color="red"><b>주의: 반환값으로 절대 {@code null}을 제공해서는 안 됩니다.</b></font>
     * @param mapSupplier
     *            {@link Map} 제공함수.
     * @return
     *
     * @since 2025. 8. 20.
     * @version 2.1.0
     */
    public static <K extends @Nullable Object, V extends @Nullable Object, M extends Map<K, V>> //
            M toMap(Collection<V> col, Function<V, K> keyMapper, BinaryOperator<V> mergeFunction, Supplier<M> mapSupplier) {
        return StreamUtils.toMap(col.stream(), keyMapper, StreamUtils.identity(), mergeFunction, mapSupplier);
    }

    /**
     * {@link Collection} 데이터를 새로운 형태로 변환하여 하나의 {@link Map}로 묶어서 제공합니다. <br>
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
     *            스트림 원본 데이터 유형 (Nullable)
     * @param <U>
     *            변환된 새로운 데이터 유형 (<b>{@code NOT nullable}</b>). <br>
     *            JDK 내부 제약({@link Collectors#toMap})으로 인해 {@code null}을 허용하지 않습니다.
     * @param <M>
     *            결과 {@link Map} 유형
     * @param col
     *            데이터 제공 객체
     * @param keyMapper
     *            객체의 식별정보를 제공하는 함수. (V &rarr; K)
     * @param valueFunction
     *            새로운 객체를 제공하는 변환 함수. (V &rarr; U) <br>
     *            <font color="red"><b>주의: 반환값으로 절대 {@code null}을 제공해서는 안 됩니다.</b></font>
     * @param mergeFunction
     *            2개의 객체 정보를 하나로 병합하는 함수. (U + U &rarr; U) <br>
     *            <font color="red"><b>주의: 반환값으로 절대 {@code null}을 제공해서는 안 됩니다.</b></font>
     * 
     * @return
     *
     * @since 2025. 8. 20.
     * @version 2.1.0
     */
    public static <K extends @Nullable Object, V extends @Nullable Object, U, M> //
            Map<K, U> toMap(Collection<V> col, Function<V, K> keyMapper, Function<V, U> valueFunction, BinaryOperator<U> mergeFunction) {
        return StreamUtils.toMap(col.stream(), keyMapper, valueFunction, mergeFunction, (Supplier<Map<K, U>>) HashMap<K, U>::new);
    }

    /**
     * {@link Collection} 데이터를 새로운 형태로 변환하여 하나의 {@link Map}로 묶어서 제공합니다. <br>
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
     * @param <U>
     *            변환된 새로운 데이터 유형 (<b>{@code NOT nullable}</b>). <br>
     *            JDK 내부 제약({@link Collectors#toMap})으로 인해 {@code null}을 허용하지 않습니다.
     * @param <M>
     *            결과 {@link Map} 유형
     * @param col
     *            데이터 제공 객체
     * @param keyMapper
     *            객체의 식별정보를 제공하는 함수. (V &rarr; K)
     * @param valueFunction
     *            새로운 객체를 제공하는 변환 함수. (V &rarr; U) <br>
     *            <font color="red"><b>주의: 반환값으로 절대 {@code null}을 제공해서는 안 됩니다.</b></font>
     * @param mergeFunction
     *            2개의 객체 정보를 하나로 병합하는 함수. (U + U &rarr; U) <br>
     *            <font color="red"><b>주의: 반환값으로 절대 {@code null}을 제공해서는 안 됩니다.</b></font>
     * @param mapSupplier
     *            {@link Map} 제공함수.
     * 
     * @return
     *
     * @since 2025. 8. 20.
     * @version 2.1.0
     */
    public static <K extends @Nullable Object, V extends @Nullable Object, U, M extends Map<K, U>> //
            M toMap(Collection<V> col, Function<V, K> keyMapper, Function<V, U> valueFunction, BinaryOperator<U> mergeFunction, Supplier<M> mapSupplier) {
        return StreamUtils.toMap(col.stream(), keyMapper, valueFunction, mergeFunction, mapSupplier);
    }

    /**
     * {@link Enumera} 데이터를 {@code keyGen}로 구분되는 {@link Map} 형테로 제공합니다.<br>
     * 단, {@code keyMapper} 결과 값이 동일한 데이터의 경우 나중에 추가되는 데이터만 존재합니다.<br>
     * {@code keyMapper} 결과 값이 동일한 경우에 대해서 제어하고 싶은 경우,<br>
     * {@link StreamUtils#toMap(Stream, Function, Function, BinaryOperator, Supplier)} 또는 <br>
     * {@link StreamUtils#toMap(Stream, Function, BinaryOperator, Function, Supplier)} 를 사용하기 바랍니다.<br>
     * 데이터를 병합하지 않고 모두 유지하려는 경우<br>
     * {@link StreamUtils#toMap(Stream, Function, Function)},<br>
     * {@link StreamUtils#toMap(Stream, Function, Function, Supplier)},<br>
     * {@link StreamUtils#toMap(Stream, Function, Function, Supplier, Supplier)} 를 사용하기 바랍니다.<br>
     * <font color="red">단, 반환데이터 유형이 Map&lt;K,List&lt;E&gt;&gt; 형태로 현재 메소드이 반환 데이터와는 다른 점</font>을 유의하기 바랍니다.
     * 
     * <p>
     * <font color="red"><b>* 데이터(V)가 <b>{@code null}</b>인 경우 '전처리 과정'에서 제외시키므로, 데이터(V)를 처리하는 함수 객체는
     * <b>{@code null}</b>을 처리하지 않아도 됩니다.</b></font>
     * </p>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2017. 9. 11.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <E>
     *            데이터 유형 (Nullable).
     * @param <K>
     *            데이터 식별정보 유형 (Nullable).
     * @param col
     *            데이터 제공 객체
     * @param keyMapper
     *            객체의 식별정보를 제공하는 함수. (V &rarr; K)
     * 
     * @return
     *
     * @since 2017. 9. 11.
     * 
     * @see #toMap(Collection, Function)
     */
    public static <E extends @Nullable Object, K extends @Nullable Object> Map<K, E> toMap(Enumeration<E> col, Function<E, K> keyMapper) {
        return toMap(Collections.list(col), keyMapper);
    }

    /**
     * 지정한 {@link Map}으로 {@link Collection}을 변환합니다. 각 키는 단일 값을 가집니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 8. 8.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <E>
     *            데이터 유형
     * @param <K>
     *            데이터 식별정보 유형
     * @param <V>
     *            새로운 데이터 유형
     * @param <M>
     *            결과 데이터 {@link Map} 유형
     * @param col
     *            데이터 제공 객체
     * @param keyGen
     *            데이터 식별정보 제공 함수.
     * @param valueGen
     *            새로운 데이터 변환 함수
     * @return
     *
     * @since 2019. 8. 8.
     */
    public static <E, K extends @Nullable Object, V, M extends Map<K, V>> M toMapHSV(Collection<E> col, BiFunction<E, Integer, K> keyGen, BiFunction<E, Integer, V> valueGen) {
        return (M) toMapHSV(col, keyGen, valueGen, new HashMap<>());
    }

    /**
     * 지정한 {@link Map}으로 {@link Collection}을 변환합니다. 각 키는 단일 값을 가집니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 1. 30.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <E>
     *            a type of an element.
     * @param <K>
     *            데이터 식별정보 유형 a type of a key.
     * @param <V>
     *            데이터 유형 a type of a new element.
     * @param <M>
     *            a type of subclass of {@link Map}, not interface.
     * @param col
     *            elements.
     * @param keyGen
     *            데이터 식별정보 제공 함수.
     * @param valueGen
     *            새로운 데이터 변환 함수
     * @param map
     *            an instance of a {@link Map}.
     * @return
     *
     * @since 2020. 1. 30.
     * @version 1.6.17
     */
    public static <E, K extends @Nullable Object, V, M extends Map<K, V>> //
            M toMapHSV(Collection<E> col, BiFunction<E, Integer, K> keyGen, BiFunction<E, Integer, V> valueGen, M map) {
        ObjectUtils.requireNonNulls(col, keyGen, valueGen, map);

        int i = 0;
        for (E e : col) {
            map.put(keyGen.apply(e, i), valueGen.apply(e, i));
            i++;
        }

        return map;
    }

    /**
     * Transform {@link Collection} to the specified {@link Map} that each key has a single value. <br>
     * 원본 컬렉션의 요소와 해당 요소의 인덱스(0부터 시작)를 함께 고려하여 Key-Value 쌍을 생성합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 206. 4. 6.       parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <E>
     *            원본 데이터 유형 (a type of an element)
     * @param <K>
     *            데이터 식별정보 유형 (a type of a key)
     * @param <V>
     *            새로운 데이터 유형 (a type of a new element)
     * @param <M>
     *            생성할 대상 {@link Map} 구현체 유형 (not interface)
     * @param col
     *            원본 데이터 컬렉션
     * @param keyGen
     *            데이터와 인덱스를 받아 식별정보를 제공하는 함수
     * @param valueGen
     *            데이터와 인덱스를 받아 새로운 데이터를 생성하는 변환 함수
     * @param mapSupplier
     *            동적으로 인스턴스를 생성할 {@link Map} 제공 함수
     * 
     * @return 동적으로 생성되고 데이터가 추가된 대상 Map 인스턴스
     * 
     * @throws IllegalArgumentException
     *             Map 인스턴스를 동적으로 생성할 수 없는 경우 (기본 생성자 부재, 접근 권한 등)
     * 
     * @since 2026. 4. 6.
     * @version 3.0.0
     */
    public static <E, K extends @Nullable Object, V, M extends Map<K, V>> //
            M toMapHSV(Collection<E> col, BiFunction<E, Integer, K> keyGen, BiFunction<E, Integer, V> valueGen, Supplier<M> mapSupplier) {
        ObjectUtils.requireNonNulls(col, keyGen, valueGen, mapSupplier);

        return toMapHSV(col, keyGen, valueGen, mapSupplier.get());
    }

    /**
     * 지정한 {@link Map}으로 {@link Collection}을 변환합니다. 각 키는 단일 값을 가집니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 8. 8.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <E>
     *            a type of an element.
     * @param <K>
     *            데이터 식별정보 유형 a type of a key.
     * @param <V>
     *            데이터 유형 a type of a new element.
     * @param <M>
     *            a type of subclass of {@link Map}, not interface.
     * @param col
     *            elements.
     * @param keyGen
     *            데이터 식별정보 제공 함수.
     * @param valueGen
     *            새로운 데이터 변환 함수
     * @return
     *
     * @since 2019. 8. 8.
     */
    public static <E, K extends @Nullable Object, V, M extends Map<K, V>> M toMapHSV(Collection<E> col, BiFunction<E, Integer, K> keyGen, Function<E, V> valueGen) {
        return (M) toMapHSV(col, keyGen, valueGen, new HashMap<>());
    }

    /**
     * 지정한 {@link Map}으로 {@link Collection}을 변환합니다. 각 키는 단일 값을 가집니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 1. 30.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <E>
     *            a type of an element.
     * @param <K>
     *            데이터 식별정보 유형 a type of a key.
     * @param <V>
     *            데이터 유형 a type of a new element.
     * @param <M>
     *            a type of subclass of {@link Map}, not interface.
     * @param col
     *            elements.
     * @param keyGen
     *            데이터 식별정보 제공 함수.
     * @param valueGen
     *            새로운 데이터 변환 함수
     * @param map
     *            an instance of a {@link Map}.
     * 
     * @return
     *
     * @since 2020. 1. 30.
     * @version 1.6.17
     */
    public static <E, K extends @Nullable Object, V, M extends Map<K, V>> M toMapHSV(Collection<E> col, BiFunction<E, Integer, K> keyGen, Function<E, V> valueGen, M map) {
        ObjectUtils.requireNonNulls(col, keyGen, valueGen, map);

        int i = 0;
        for (E e : col) {
            map.put(keyGen.apply(e, i), valueGen.apply(e));
            i++;
        }

        return map;
    }

    /**
     * 지정한 {@link Map}으로 {@link Collection}을 변환합니다. 각 키는 단일 값을 가집니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2026. 4. 6.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <E>
     *            a type of an element.
     * @param <K>
     *            데이터 식별정보 유형 a type of a key.
     * @param <V>
     *            데이터 유형 a type of a new element.
     * @param <M>
     *            a type of subclass of {@link Map}, not interface.
     * @param col
     *            elements.
     * @param keyGen
     *            데이터 식별정보 제공 함수.
     * @param valueGen
     *            새로운 데이터 변환 함수
     * @param mapSupplier
     *            {@link Map} 제공 함수
     * 
     * @return
     *
     * @since 2026. 4. 6.
     * @version 3.0.0
     */
    public static <E, K extends @Nullable Object, V, M extends Map<K, V>> M toMapHSV(Collection<E> col, BiFunction<E, Integer, K> keyGen, Function<E, V> valueGen,
            Supplier<M> mapSupplier) {
        ObjectUtils.requireNonNulls(col, keyGen, valueGen, mapSupplier);

        return toMapHSV(col, keyGen, valueGen, mapSupplier.get());
    }

    /**
     * 지정한 {@link Map}으로 {@link Collection}을 변환합니다. 각 키는 단일 값을 가집니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 8. 8.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <E>
     *            a type of an element.
     * @param <K>
     *            데이터 식별정보 유형 a type of a key.
     * @param <V>
     *            데이터 유형 a type of a new element.
     * @param <M>
     *            a type of subclass of {@link Map}, not interface.
     * @param col
     *            elements.
     * @param keyGen
     *            데이터 식별정보 제공 함수.
     * @param valueGen
     *            새로운 데이터 변환 함수
     * @return
     *
     * @since 2019. 8. 8.
     */
    public static <E, K extends @Nullable Object, V, M extends Map<K, V>> M toMapHSV(Collection<E> col, Function<E, K> keyGen, BiFunction<E, Integer, V> valueGen) {
        return (M) toMapHSV(col, keyGen, valueGen, new HashMap<>());
    }

    /**
     * 지정한 {@link Map}으로 {@link Collection}을 변환합니다. 각 키는 단일 값을 가집니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 1. 30.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <E>
     *            a type of an element.
     * @param <K>
     *            데이터 식별정보 유형 a type of a key.
     * @param <V>
     *            데이터 유형 a type of a new element.
     * @param <M>
     *            a type of subclass of {@link Map}, not interface.
     * @param col
     *            elements.
     * @param keyGen
     *            데이터 식별정보 제공 함수.
     * @param valueGen
     *            새로운 데이터 변환 함수
     * @param map
     *            an instance of a {@link Map}.
     * @return
     *
     * @since 2020. 1. 30.
     * @version 1.6.17
     */
    public static <E, K extends @Nullable Object, V, M extends Map<K, V>> M toMapHSV(Collection<E> col, Function<E, K> keyGen, BiFunction<E, Integer, V> valueGen, M map) {
        ObjectUtils.requireNonNulls(col, keyGen, valueGen, map);

        int i = 0;
        for (E e : col) {
            map.put(keyGen.apply(e), valueGen.apply(e, i));
            i++;
        }

        return map;
    }

    /**
     * 지정한 {@link Map}으로 {@link Collection}을 변환합니다. 각 키는 단일 값을 가집니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2026. 4. 6.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <E>
     *            a type of an element.
     * @param <K>
     *            데이터 식별정보 유형 a type of a key.
     * @param <V>
     *            데이터 유형 a type of a new element.
     * @param <M>
     *            a type of subclass of {@link Map}, not interface.
     * @param col
     *            elements.
     * @param keyGen
     *            데이터 식별정보 제공 함수.
     * @param valueGen
     *            새로운 데이터 변환 함수
     * @param mapSupplier
     *            a sub-{@link Class} of a {@link Map}.
     * 
     * @return
     *
     * @since 2026. 4. 6.
     * @version 3.0.0
     */
    public static <E, K extends @Nullable Object, V, M extends Map<K, V>> //
            M toMapHSV(Collection<E> col, Function<E, K> keyGen, BiFunction<E, Integer, V> valueGen, Supplier<M> mapSupplier) {
        ObjectUtils.requireNonNulls(col, keyGen, valueGen, mapSupplier);

        return toMapHSV(col, keyGen, valueGen, mapSupplier.get());
    }

    /**
     * 지정한 {@link Map}으로 {@link Collection}을 변환합니다. 각 키는 단일 값을 가집니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 1. 15.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <E>
     *            a type of an element.
     * @param <K>
     *            데이터 식별정보 유형 a type of a key.
     * @param <V>
     *            데이터 유형 a type of a new element.
     * @param <M>
     *            a type of subclass of {@link Map}, not interface.
     * @param col
     *            elements.
     * @param keyGen
     *            데이터 식별정보 제공 함수.
     * @param valueGen
     *            새로운 데이터 변환 함수
     * @return
     * 
     * @since 2019. 1. 15.
     */
    public static <E, K extends @Nullable Object, V> Map<K, V> toMapHSV(Collection<E> col, Function<E, K> keyGen, Function<E, V> valueGen) {
        return toMapHSV(col, keyGen, valueGen, new HashMap<K, V>());
    }

    /**
     * Transform {@link Collection} to the specified {@link Map} that each key has a single value. <br>
     *
     * <p>
     * <b>[데이터 병합 정책]</b><br>
     * {@code keyGen} 함수를 통해 생성된 키(Key)가 이미 Map에 존재하는 경우(키 중복), 원본 컬렉션의 <b>나중에 처리되는 요소의 변환 값(valueGen)이 이전 값을
     * 덮어씁니다(Overwrite).</b>
     * </p>
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 1. 30.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <E>
     *            원본 데이터 유형 (a type of an element)
     * @param <K>
     *            데이터 식별정보 유형 (a type of a key)
     * @param <V>
     *            새로운 데이터 유형 (E &rarr; V)
     * @param <M>
     *            대상 {@link Map} 구현체 유형 (not interface)
     * @param col
     *            원본 데이터 컬렉션
     * @param keyGen
     *            데이터 식별정보 제공 함수
     * @param valueGen
     *            새로운 데이터 변환 함수 (E &rarr; V)
     * @param map
     *            데이터가 담길 대상 Map 인스턴스
     * 
     * @return 데이터가 추가된 대상 Map 인스턴스
     *
     * @since 2020. 1. 30.
     * @version 2.0.0
     */
    public static <E, K extends @Nullable Object, V, M extends Map<K, V>> M toMapHSV(Collection<E> col, Function<E, K> keyGen, Function<E, V> valueGen, M map) {
        ObjectUtils.requireNonNulls(col, keyGen, valueGen, map);

        col.forEach(e -> map.put(keyGen.apply(e), valueGen.apply(e)));

        return map;
    }

    /**
     * 지정한 {@link Map}으로 {@link Collection}을 변환합니다. 각 키는 단일 값을 가집니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2026. 4. 6.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <E>
     *            a type of an element.
     * @param <K>
     *            데이터 식별정보 유형 a type of a key.
     * @param <V>
     *            데이터 유형 a type of a new element.
     * @param <M>
     *            a type of subclass of {@link Map}, not interface.
     * @param col
     *            elements.
     * @param keyGen
     *            데이터 식별정보 제공 함수.
     * @param valueGen
     *            새로운 데이터 변환 함수
     * @param mapSupplier
     *            {@link Map}.제공함수.
     * 
     * @return
     *
     * @since 2026. 4. 6.
     * @version 3.0.0
     */
    public static <E, K extends @Nullable Object, V, M extends Map<K, V>> M toMapHSV(Collection<E> col, Function<E, K> keyGen, Function<E, V> valueGen, Supplier<M> mapSupplier) {
        ObjectUtils.requireNonNulls(col, keyGen, valueGen, mapSupplier);

        return toMapHSV(col, keyGen, valueGen, mapSupplier.get());
    }

    /**
     * 조건에 맞는 데이터를 정렬하고 지정된 개수만큼 반환합니다. (원본 유지) <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2023. 12. 13.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <T>
     *            데이터 타입
     * @param data
     *            원본 데이터
     * @param sorter
     *            정렬 함수
     * @param limit
     *            데이터 개수
     * @return
     *
     * @since 2023. 12. 13.
     * @version 2.0.0
     */
    public static <T> List<T> topN(Collection<T> data, Comparator<T> sorter, int limit) {
        return topN(data, Predicates.alwaysTrue(), sorter, limit);
    }

    /**
     * 조건에 맞는 데이터를 정렬하고 지정된 개수만큼 반환합니다. (원본 유지) <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2023. 12. 13.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <T>
     *            데이터 타입
     * @param data
     *            원본 데이터
     * @param filter
     *            데이터 필터
     * @param sorter
     *            정렬 함수
     * @param limit
     *            데이터 개수
     * @return
     *
     * @since 2023. 12. 13.
     * @version 2.0.0
     */
    public static <T> List<T> topN(Collection<T> data, Predicate<T> filter, Comparator<T> sorter, int limit) {
        return topN(data, filter, sorter, limit, TopNStrategy.AUTO, false);
    }

    /**
     * 조건에 맞는 데이터를 정렬하고 지정된 개수만큼 반환합니다. (원본 유지) <br>
     * 원본 데이터에서 {@code filter}를 통과한 데이터의 개수(M)와 실제 선택하려는 개수({@code limit}, N)의 값에 따라서 세부적인 구현이 분기됩니다.<br>
     * 자세한 내용은 {@link TopN#decideStrategy(int, int, boolean)} 를 참조하기 바랍니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 3.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <T>
     *            데이터 타입
     * @param data
     *            원본 데이터
     * @param filter
     *            데이터 필터
     * @param sorter
     *            정렬 함수
     * @param limit
     *            데이터 개수
     * @param strategy
     *            정렬 방식
     * @param expensiveComparator
     *            비교 함수의 고비용 여부
     * @return
     *
     * @since 2025. 9. 3.
     * @version 2.1.0
     * 
     * @see {@link TopN#setAutoConfiguration(int, double, int, double)} 을 통해서 {@link TopNStrategy}를 선택하는 설정값을 변경할 수
     *      있습니다.
     */
    public static <T> List<T> topN(Collection<T> data, Predicate<T> filter, Comparator<T> sorter, int limit, TopNStrategy strategy, boolean expensiveComparator) {
        ObjectUtils.requireNonNulls(data, filter, sorter);
        AssertUtils2.isTrue(limit > -1);

        if (limit <= 0 || data == null || data.isEmpty()) {
            return Collections.emptyList();
        }

        // 1) 필터 통과 개수 M 산정
        int filteredCount = 0;
        for (T t : data) {
            if (filter.test(t)) {
                filteredCount++;
            }
        }
        if (filteredCount == 0) {
            return Collections.emptyList();
        }
        if (limit >= filteredCount) {
            // 전부 필요 → 모아서 한 번 정렬 후 반환
            return topnByFullSort(data, filter, sorter, filteredCount);
        }

        // 2) 전략 결정
        TopNStrategy chosen = strategy == TopNStrategy.AUTO ? TopN.decideStrategy(filteredCount, limit, expensiveComparator) : strategy;

        // 3) 전략 실행
        switch (chosen) {
            case HEAP_SORT:
                return topnByHeap(data, filter, sorter, limit);
            case FULL_SORT:
                return topnByFullSort(data, filter, sorter, limit);
            case QUICKSELECT:
                return topnByQuickselect(data, filter, sorter, limit);
            default:
                // 방어적 기본
                return topnByHeap(data, filter, sorter, limit);
        }
    }

    /**
     * 조건에 맞는 데이터를 정렬하고 지정된 개수만큼 반환합니다. (원본 유지) <br>
     * 원본 데이터에서 {@code filter}를 통과한 데이터의 개수(M)와 실제 선택하려는 개수({@code limit}, N)의 값에 따라서 세부적인 구현이 분기되어 사용되는 메소드입니다.<br>
     * 이 메소드를 직접 사용하기 보다는 {@link #topN(Collection, Comparator, int)} 메소드를 사용하는 것을 권장하며,<br>
     * 직접 이 메소드를 하는 경우에는 {@link TopN#decideStrategy(int, int, boolean)} 를 참조하기 바랍니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 3.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <T>
     *            데이터 타입
     * @param data
     *            원본 데이터
     * @param filter
     *            데이터 필터
     * @param sorter
     *            정렬 함수
     * @param limit
     *            데이터 개수
     * @return
     *
     * @since 2025. 9. 3.
     * @version 2.1.0
     */
    public static <T> List<T> topnByFullSort(Collection<T> data, Predicate<T> filter, Comparator<T> sorter, int limit) {
        ObjectUtils.requireNonNulls(data, filter, sorter);
        AssertUtils2.isTrue(limit > -1);

        return data.parallelStream() //
                .filter(filter) //
                .sorted(sorter) //
                .limit(limit) //
                .collect(Collectors.toList());
    }

    /**
     * 조건에 맞는 데이터를 정렬하고 지정된 개수만큼 반환합니다. (원본 유지) <br>
     * 원본 데이터에서 {@code filter}를 통과한 데이터의 개수(M)와 실제 선택하려는 개수({@code limit}, N)의 값에 따라서 세부적인 구현이 분기되어 사용되는 메소드입니다.<br>
     * 이 메소드를 직접 사용하기 보다는 {@link #topN(Collection, Comparator, int)} 메소드를 사용하는 것을 권장하며,<br>
     * 직접 이 메소드를 하는 경우에는 {@link TopN#decideStrategy(int, int, boolean)} 를 참조하기 바랍니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 3.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <T>
     *            데이터 타입
     * @param data
     *            원본 데이터
     * @param filter
     *            데이터 필터
     * @param sorter
     *            정렬 함수
     * @param limit
     *            데이터 개수
     * @return
     *
     * @since 2025. 9. 3.
     * @version 2.1.0
     */
    public static <T> List<T> topnByHeap(Collection<T> data, Predicate<T> filter, Comparator<T> sorter, int limit) {
        ObjectUtils.requireNonNulls(data, filter, sorter);
        AssertUtils2.isTrue(limit > -1);

        // N개 중 "최악"이 루트가 되도록: cmp의 자연스런 최소힙을 쓰고, 들어온 e가 루트보다 "더 낫다"(>0)면 교체합니다.
        PriorityQueue<T> filtered = new PriorityQueue<>(limit, sorter.reversed());

        for (T e : data) {
            if (!filter.test(e)) {
                continue;
            }
            if (filtered.size() < limit) {
                filtered.add(e);
            } else {
                T worst = filtered.peek(); // 현재 N개 중 최악
                // e가 더 "좋으면" 교체
                if (sorter.compare(e, worst) < 0) {
                    filtered.poll();
                    filtered.add(e);
                }
            }
        }

        // 최종 결과는 정렬해서 반환
        return filtered.stream() //
                .sorted(sorter) //
                .collect(Collectors.toList());
    }

    /**
     * 조건에 맞는 데이터를 정렬하고 지정된 개수만큼 반환합니다. (원본 유지) <br>
     * 원본 데이터에서 {@code filter}를 통과한 데이터의 개수(M)와 실제 선택하려는 개수({@code limit}, N)의 값에 따라서 세부적인 구현이 분기되어 사용되는 메소드입니다.<br>
     * 이 메소드를 직접 사용하기 보다는 {@link #topN(Collection, Comparator, int)} 메소드를 사용하는 것을 권장하며,<br>
     * 직접 이 메소드를 하는 경우에는 {@link TopN#decideStrategy(int, int, boolean)} 를 참조하기 바랍니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 3.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <T>
     *            데이터 타입
     * @param data
     *            원본 데이터
     * @param filter
     *            데이터 필터
     * @param sorter
     *            정렬 함수
     * @param limit
     *            데이터 개수
     * @return
     *
     * @since 2025. 9. 3.
     * @version 2.1.0
     */
    public static <T> List<T> topnByQuickselect(Collection<T> data, Predicate<T> filter, Comparator<T> sorter, int limit) {
        ObjectUtils.requireNonNulls(data, filter, sorter);
        AssertUtils2.isTrue(limit > -1);

        // 1) 필터 통과본만 ArrayList로 수집
        ArrayList<T> filtered = new ArrayList<>();
        for (T e : data) {
            if (filter.test(e)) {
                filtered.add(e);
            }
        }
        final int filteredCount = filtered.size();
        if (filteredCount == 0) {
            return Collections.emptyList();
        }
        if (limit >= filteredCount) {
            filtered.sort(sorter);
            return filtered;
        }

        // 2) Quickselect로 "상위 N 기준" 파티셔닝
        // partition 규칙: cmp.compare(a[i], pivot) < 0 이면 "더 좋음"으로 간주하여 왼쪽군에 둠
        TopN.quickselectTopNInPlace(filtered, sorter, limit);

        // 3) 상위 N 구간만 최종 정렬
        return filtered.subList(0, limit).stream() //
                .sorted(sorter) //
                .collect(Collectors.toList());
    }

    /**
     * {@link Collection}에 포함된 데이터를 {@link Set}에 담아 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2014. 10. 17.		parkjunhong77@gmail.com			최초 작성
     * 2025. 8. 21.         parkjunhong77@gmail.com         내부 구현 개선. (Stream API 적용)
     * </pre>
     *
     * @param <E>
     *            데이터 유형
     * @param col
     *            데이터 제공 객체
     * @return
     *
     * @since 2014. 10. 17.
     * @version 1.6.17
     */
    public static <E extends @Nullable Object> Set<E> toSet(Collection<E> col) {
        return toSet(col, (Supplier<Set<E>>) HashSet<E>::new);
    }

    /**
     * {@link Collection}에 포함된 데이터를 변환({@code transformer})하여 {@link Set}에 담아 제공합니다. <br>
     * <p>
     * <font color="red"><b>* 데이터(E)가 <b>{@code null}</b>인 경우 '전처리 과정'에서 제외시키므로, 데이터(E)를 처리하는 함수 객체는
     * <b>{@code null}</b>을 처리하지 않아도 됩니다.</b></font>
     * </p>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2017. 7. 6.		parkjunhong77@gmail.com			최초 작성
     * 2025. 8. 21.     parkjunhong77@gmail.com         내구 구현 개선 (Stream API 적용)
     * </pre>
     *
     * @param <E>
     *            데이터 유형 (Nullable)
     * @param <NE>
     *            새로운 데이터 유형 (Nullable).
     * @param stream
     *            데이터 제공 객체
     * @param transformer
     *            데이터 변환 함수. (E &rarr; NE) <br>
     * @return
     *
     * @since 2017. 7. 6.
     * @version
     */
    public static <E extends @Nullable Object, NE extends @Nullable Object> Set<NE> toSet(Collection<E> col, Function<E, NE> transformer) {
        return StreamUtils.toCollection(col.stream(), transformer, (Supplier<Set<NE>>) HashSet<NE>::new);
    }

    /**
     * {@link Collection}에 포함된 데이터를 {@link Set}에 담아 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 21.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <E>
     *            데이터 유형 (Nullable). <br>
     *            단, {@code setSupplier}가 제공하는 {@link Set} 구현체가 {@code null}을 허용해야 합니다.
     * @param <S>
     *            결과 {@link Set} 유형
     * @param col
     *            데이터 제공 객체
     * @param setSupplier
     *            결과 {@link Set} 객체 제공 함수.
     * @return
     *
     * @since 2025. 8. 21.
     * @version 2.1.0
     */
    public static <E extends @Nullable Object, S extends Set<E>> S toSet(Collection<E> col, Supplier<S> setSupplier) {
        return StreamUtils.toCollection(col.stream(), setSupplier);
    }

    /**
     * {@link Collection} 데이터를 하나의 {@link Set}로 묶어서 제공합니다. <br>
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
     * @param col
     *            데이터 제공 객체. (내부의 {@code null} 요소는 전처리 과정에서 안전하게 제거됩니다)
     * @param keyMapper
     *            객체의 식별정보를 제공하는 함수. (V &rarr; K)
     * @param mergeFunction
     *            2개의 객체 정보를 하나로 병합하는 함수. (V + V &rarr; V) <br>
     *            <font color="red"><b>주의: 반환값으로 절대 {@code null}을 제공해서는 안 됩니다.</b></font>
     * 
     * @return 중복이 병합된 결과 데이터가 담긴 새로운 컬렉션
     *
     * @since 2025. 8. 20.
     * @version 2.1.0
     */
    public static <K extends @Nullable Object, V> Set<V> toSet(Collection<V> col, Function<V, K> keyMapper, BinaryOperator<V> mergeFunction) {
        return toCollection(col, keyMapper, StreamUtils.identity(), mergeFunction, (Supplier<Set<V>>) HashSet<V>::new);
    }

    /**
     * {@link Collection} 데이터를 하나의 {@link Set}로 묶어서 제공합니다. <br>
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
     * @param <S>
     *            결과 {@link Set} 유형
     * @param col
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
     * @since 2025. 8. 20.
     * @version 2.1.0
     */
    public static <K extends @Nullable Object, V, S extends Set<V>> S toSet(Collection<V> col, Function<V, K> keyMapper, BinaryOperator<V> mergeFunction, Supplier<S> setFactory) {
        return toCollection(col, keyMapper, StreamUtils.identity(), mergeFunction, setFactory);
    }

    /**
     * {@link Collection} 데이터를 하나의 {@link Set}로 묶어서 제공합니다. <br>
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
     * @param col
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
     * @since 2025. 8. 20.
     * @version 2.1.0
     */
    public static <K extends @Nullable Object, V> Set<V> toSet(Collection<V> col, Function<V, K> keyMapper, Function<V, V> valueMapper, BinaryOperator<V> mergeFunction) {
        return toCollection(col, keyMapper, valueMapper, mergeFunction, (Supplier<Set<V>>) HashSet<V>::new);
    }

    /**
     * {@link Collection} 데이터를 하나의 {@link Set}로 묶어서 제공합니다. <br>
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
     * @param <S>
     *            결과 {@link Set} 유형
     * @param col
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
     * @since 2025. 8. 20.
     * @version 2.1.0
     */
    public static <K extends @Nullable Object, V, S extends Set<V>> S toSet(Collection<V> col, Function<V, K> keyMapper, Function<V, V> valueMapper,
            BinaryOperator<V> mergeFunction, Supplier<S> setFactory) {
        return toCollection(col, keyMapper, valueMapper, mergeFunction, setFactory);
    }

    /**
     * {@link Collection} 내부의 원소들을 표현하는 하나의 문자열을 반환합니다.
     * 
     * <pre>
     * Collection<String> col = new ArrayList<String>();
     * col.add("1");
     * col.add("2");
     * col.add("3");
     * col.add("4");
     * col.add("5");
     * 
     * System.out.println(CollectionUtils.toString(col));
     * 
     * 결과: 12345
     * </pre>
     * 
     * @param col
     * 
     * @return
     * 
     * @since 2012. 02. 22.
     * 
     * @see {@link Object#toString()}, {@link AbstractCollection#toString()}
     */
    public static String toString(Collection<?> col) {
        Objects.requireNonNull(col);

        StringBuilder sb = new StringBuilder();

        for (Object obj : col) {
            sb.append(obj);
        }

        return sb.toString();
    }

    /**
     * {@link Collection} 내부의 원소들을 주어진 구분자를 이용해서 표현한 하나의 문자열을 반환합니다.
     * 
     * @param col
     * @param delim
     * 
     * @return
     * 
     * @since 2012. 02. 22.
     * 
     * @see #toString(Collection, String, String, String)
     */
    public static <T> String toString(Collection<T> col, String delim) {
        return toString(col, delim, "", "");
    }

    /**
     * {@link Collection} 내부의 원소들을 주어진 구분자를 이용해서 표현한 하나의 문자열을 반환합니다.<br>
     * 각 원소는 {@code prefix} 및 {@code suffix}로 둘러싸여진다.
     * 
     * @param col
     * @param delim
     * @param prefix
     * @param suffix
     * 
     * @return
     * 
     * @since 2012. 02. 22.
     */
    public static <T> String toString(Collection<T> col, String delim, String prefix, String suffix) {
        ObjectUtils.requireNonNulls(col, delim, prefix, suffix);

        StringBuilder sb = new StringBuilder();
        if (col.size() > 0) {
            Iterator<T> itr = col.iterator();

            APPENDER_STR.accept(sb, new String[] { prefix, itr.next().toString(), suffix });

            while (itr.hasNext()) {
                APPENDER_STR.accept(sb, new String[] { delim, prefix, itr.next().toString(), suffix });
            }

            return sb.toString();
        } else {
            return "";
        }
    }

    private static class AscComparator<T extends Comparable<T>> implements java.util.Comparator<T> {
        @Override
        public int compare(T o1, T o2) {
            return ComparableUtils.compare(o1, o2);
        }
    }

    private static class DescComparator<T extends Comparable<T>> implements java.util.Comparator<T> {
        @Override
        public int compare(T o1, T o2) {
            return -1 * ComparableUtils.compare(o1, o2);

        }
    }

    /**
     * 전체 데이터(M 개)에서 'N' 개를 추출하는 기능을 지원.
     * 
     * @since 2025. 9. 3.
     * @version 2.1.0
     */
    public static class TopN {

        // ---- 하이브리드 의사결정 임계값(시작점). JMH로 환경에 맞게 미세 조정하세요.
        /** 전체 데이터 개수(M)이 작으면 FULL_SORT 유리 */
        private static int fullSortThreadhold = 20_000;
        /** N/M ≤ 10% → HEAP_SORT */
        private static double heapSortRatio = 0.10;
        /** N/M ≥ 40% → FULL_SORT */
        private static double fullSortRatio = 0.40;
        /** 아주 작은 N은 HEAP_SORT 가중 */
        private static int heapSortThreadhold = 32;

        private static final Random RNG = new Random();

        private TopN() {
        }

        /**
         * 정렬 전략을 결정합니다. <br>
         * 우선순위
         * <li>1. 전체 데이터 개수 (M)
         * <ul>
         * <li>{@link TopN#fullSortThreadhold} 보다 작으면({@code &lt;}) {@link TopNStrategy#FULL_SORT}
         * </ul>
         * <li>2. 선택하려는 데이터 개수 (N) 와 N/M의 비율 고정값 (0.25)
         * <ul>
         * <li>{@link TopN#heapSortThreadhold} 보다 작으고, ({@code &lt;}) 비율이 0.25 보다 작거나 같으면 {@link TopNStrategy#HEAP_SORT}
         * </ul>
         * <li>3. N/M 의 비율
         * <ul>
         * 설정값
         * <li>{@link TopN#heapSortRatio} 보다 작으면({@code &lt;}) {@link TopNStrategy#HEAP_SORT}
         * <li>{@link TopN#fullSortRatio} 보다 크거나 같으면({@code >=}) {@link TopNStrategy#FULL_SORT}
         * </ul>
         * <li>4. 정렬 비교 함수의 비용에 따라
         * <ul>
         * <li>비싸면? {@link TopNStrategy#FULL_SORT}
         * <li>그렇지 않으면? {@link TopNStrategy#QUICKSELECT}
         * </ul>
         * 
         * <pre>
         * [개정이력]
         *      날짜    	| 작성자	|	내용
         * ------------------------------------------
         * 2025. 9. 3.		parkjunhong77@gmail.com			최초 작성
         * </pre>
         *
         * @param fullCount
         *            전체 데이터 개수 (M)
         * @param limit
         *            선택하려는 데이터 개수 (N)
         * @param expensiveComparator
         *            비교 함수의 고비용 여부
         * @return
         *
         * @since 2025. 9. 3.
         * @version 2.1.0
         */
        private static TopNStrategy decideStrategy(int fullCount, int limit, boolean expensiveComparator) {
            // 작은 M 보호장치: 전체 정렬이 대체로 이득
            if (fullCount < fullSortThreadhold) {
                return TopNStrategy.FULL_SORT;
            }

            double ratio = (double) limit / (double) fullCount;

            // 아주 작은 N은 힙 쪽 가중
            if (limit <= heapSortThreadhold && ratio <= 0.25) {
                return TopNStrategy.HEAP_SORT;
            } else if (ratio < heapSortRatio) {
                return TopNStrategy.HEAP_SORT;
            } else if (ratio >= fullSortRatio) {
                return TopNStrategy.FULL_SORT;
            } else
            // 중간 구간: 비교자 비용이 크면 FULL_SORT 가중, 아니면 QUICKSELECT 권장
            if (expensiveComparator) {
                return TopNStrategy.FULL_SORT;
            } else {
                return TopNStrategy.QUICKSELECT;
            }
        }

        /**
         * 파티션: sorter.compare(x, pivot) < 0 인 x를 왼쪽(상위)으로 보냄. 반환값은 pivot의 최종 위치. <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜    	| 작성자	|	내용
         * ------------------------------------------
         * 2025. 9. 3.		parkjunhong77@gmail.com			최초 작성
         * </pre>
         *
         * @param <T>
         * @param data
         * @param sorter
         * @param left
         * @param right
         * @param pivotIdx
         * @return
         *
         * @since 2025. 9. 3.
         * @version 2.1.0
         */
        private static <T> int partitionByComparator(List<T> data, Comparator<T> sorter, int left, int right, int pivotIdx) {
            T pivotVal = data.get(pivotIdx);
            swap(data, pivotIdx, right);
            int store = left;
            for (int i = left; i < right; i++) {
                // "더 좋음"을 왼쪽으로 모음
                if (sorter.compare(data.get(i), pivotVal) < 0) {
                    swap(data, store++, i);
                }
            }
            swap(data, store, right);
            return store;
        }

        /**
         * 데이터를 제자리에서 파티셔닝하여, sorter기준 상위 N개가 [0..N-1]에 오도록 만듭니다.<br>
         * (내림/오름은 sorter 정의에 따릅니다. sorter가 "큰 값 우선"이라면 큰 값들이 앞으로 모입니다.) <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜    	| 작성자	|	내용
         * ------------------------------------------
         * 2025. 9. 3.		parkjunhong77@gmail.com			최초 작성
         * </pre>
         *
         * @param <T>
         * @param data
         * @param sorter
         * @param limit
         *
         * @since 2025. 9. 3.
         * @version 2.1.0
         */
        private static <T> void quickselectTopNInPlace(List<T> data, Comparator<T> sorter, int limit) {
            int left = 0;
            int right = data.size() - 1;
            int target = limit - 1;

            while (left <= right) {
                int pivotIdx = left + RNG.nextInt(right - left + 1);
                int newIdx = partitionByComparator(data, sorter, left, right, pivotIdx);
                if (newIdx == target) {
                    return;
                } else if (newIdx > target) {
                    // 상위(더 좋은 값) 구간이 target을 넘어섰으므로 왼쪽으로 좁힘
                    right = newIdx - 1;
                } else {
                    left = newIdx + 1;
                }
            }
        }

        /**
         * 하이브리드 의사결정 임계값(시작점)을 설정합니다.<br>
         * JMH로 환경에 맞게 미세 조정하세요. <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜      | 작성자   |   내용
         * ------------------------------------------
         * 2025. 9. 3.      parkjunhong77@gmail.com         최초 작성
         * </pre>
         *
         * @param fullSortThreshold
         *            설정 개수 이하인 경우 {@link TopNStrategy#FULL_SORT} 적용
         * @param fullSortRatio
         *            {@link TopNStrategy#FULL_SORT}가 적용되는 전체 개수(M)와 선택하려는 개수(N)의 비율 (>=)
         * @param heapSortThreshold
         *            설정 개수 이하인 경우 {@link TopNStrategy#HEAP_SORT} 적용
         * @param heapSortRatio
         *            {@link TopNStrategy#HEAP_SORT}가 적용되는 전체 개수(M)와 선택하려는 개수(N)의 비율 (<)
         * @since 2025. 9. 3.
         * @version 2.1.0
         */
        public static void setAutoConfiguration(int fullSortThreshold, double fullSortRatio, int heapSortThreshold, double heapSortRatio) {
            TopN.fullSortThreadhold = fullSortThreshold;
            TopN.fullSortRatio = fullSortRatio;
            TopN.heapSortThreadhold = heapSortThreshold;
            TopN.heapSortRatio = heapSortRatio;
        }

        private static <T> void swap(List<T> data, int from, int to) {
            if (from != to) {
                T t = data.get(from);
                data.set(from, data.get(to));
                data.set(to, t);
            }
        }

        public enum TopNStrategy {
            /** */
            AUTO,
            /** */
            HEAP_SORT,
            /** */
            FULL_SORT,
            /** */
            QUICKSELECT
            //
            ;
        }
    }
}
