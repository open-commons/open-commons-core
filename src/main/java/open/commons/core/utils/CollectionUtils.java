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
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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

import javax.annotation.Nonnull;

import open.commons.core.TwoValueObject;
import open.commons.core.collection.FIFOMap;
import open.commons.core.collection.IKeyExtractor;
import open.commons.core.utils.CollectionUtils.TopN.TopNStrategy;

/**
 * 
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 * @since 2011. 10. 24.
 * 
 */
@SuppressWarnings("unchecked")
public class CollectionUtils {

    private static final BiConsumer<StringBuffer, String[]> APPENDER_STR = (sb, strs) -> {
        for (String str : strs) {
            sb.append(str);
        }
    };

    /**
     * 새로운 데이터를 {@link Collection}에 추가합니다.
     * 
     * @param col
     * @param clazz
     *            <code>col</code>이 <code>null</code> 인 경우 생성할 {@link Collection} 타입.
     * @param elem
     *            새로운 데이터
     * @return 전달받은 <code>col</code>이 <code>null</code>인 경우, 새로운 객체.
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <C extends Collection<E>, E> C add(C col, Class<? extends C> clazz, E elem) {

        if (col == null) {
            try {
                col = clazz.newInstance();
            } catch (Exception e) {
                return null;
            }
        }

        col.add(elem);

        return col;
    }

    /**
     * 새로운 데이터를 {@link Collection}에 추가합니다.
     * 
     * @param col
     * @param clazz
     *            <code>col</code>이 <code>null</code> 인 경우 생성할 {@link Collection} 타입.
     * @param elems
     *            새로운 데이터
     * @return 전달받은 <code>col</code>이 <code>null</code>인 경우, 새로운 객체.
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <C extends Collection<E>, E> C addAll(C col, Class<? extends C> clazz, E... elems) {

        if (col == null) {
            try {
                col = clazz.newInstance();
            } catch (Exception e) {
                return null;
            }
        }

        for (E elem : elems) {
            col.add(elem);
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
     * 2019. 7. 4.		박준홍			최초 작성
     * </pre>
     *
     * @param col
     * @param elems
     * @return
     *
     * @since 2019. 7. 4.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <C extends Collection<E>, E> C addAll(C col, E... elems) {
        for (E elem : elems) {
            col.add(elem);
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
     * 2017. 12. 13.        박준홍         최초 작성
     * </pre>
     * 
     * @param col
     * @param clazz
     *            <code>col</code>이 <code>null</code> 인 경우 생성할 {@link Collection} 타입.
     * @param elems
     *            새로운 데이터
     * @return 전달받은 <code>col</code>이 <code>null</code>인 경우, 새로운 객체.
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2017. 12. 13.
     */
    public static <C extends Collection<E>, E> C addAllIfNotNull(C col, Class<? extends C> clazz, Collection<E> elems) {

        if (col == null) {
            try {
                col = clazz.newInstance();
            } catch (Exception e) {
                return null;
            }
        }

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
     * @param col
     * @param clazz
     *            <code>col</code>이 <code>null</code> 인 경우 생성할 {@link Collection} 타입.
     * @param elems
     *            새로운 데이터
     * @return 전달받은 <code>col</code>이 <code>null</code>인 경우, 새로운 객체.
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <C extends Collection<E>, E> C addAllIfNotNull(C col, Class<? extends C> clazz, E... elems) {

        if (col == null) {
            try {
                col = clazz.newInstance();
            } catch (Exception e) {
                return null;
            }
        }

        for (E elem : elems) {
            if (elem != null) {
                col.add(elem);
            }
        }

        return col;
    }

    /***
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2017. 12. 13.		박준홍			최초 작성
     * </pre>
     *
     * @param <T>
     * @param list
     * @param value
     * @return
     *
     * @since 2017. 12. 13.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <T> boolean addIfAbsent(List<T> list, T value) {
        if (list == null) {
            return false;
        }

        if (!list.contains(value)) {
            list.add(value);

            return true;
        } else {
            return false;
        }
    }

    /**
     * 추가하려는 데이터가 <code>null</code>이 아닌 경우 추가합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2017. 12. 13.		박준홍			최초 작성
     * </pre>
     *
     * @param col
     * @param clazz
     * @param elem
     * @return
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2017. 12. 13.
     */
    public static <C extends Collection<E>, E> C addIfNotNull(C col, Class<? extends C> clazz, E elem) {

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
     * 2025. 9. 1.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     *            데이터 유형
     * @param <KEY>
     *            식별정보 유형
     * @param <R>
     *            새로운 데이터 유형 (E => R)
     * @param data1
     *            정렬된 데이터#1
     * @param data2
     *            정렬된 데이터#2
     * @param keyProvider
     *            데이터 식별정보 제공 함수
     * @param transformer
     *            데이터 변환 함수 (E => R)
     * @param emptyCreator
     *            빈 데이터 제공 함수 ({} => R)
     * @return
     *
     * @since 2025. 9. 1.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E, KEY extends Comparable<KEY>, R> TwoValueObject<List<R>, List<R>> alignBy( //
            @Nonnull List<E> data1, @Nonnull List<E> data2 //
            , @Nonnull Function<E, KEY> keyProvider, @Nonnull Function<E, R> transformer, @Nonnull Function<KEY, R> emptyCreator //
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
     * 2025. 9. 1.		박준홍			최초 작성
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
     *            빈 데이터 제공 함수 ({} => R)
     * @return
     *
     * @since 2025. 9. 1.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E, KEY extends Comparable<KEY>> TwoValueObject<List<E>, List<E>> alignBy( //
            @Nonnull List<E> data1, @Nonnull List<E> data2 //
            , @Nonnull Function<E, KEY> keyProvider, @Nonnull Function<KEY, E> emptyCreator //
    ) {
        return alignBy(data1, keyProvider, d -> d, emptyCreator, data2, keyProvider, d -> d, emptyCreator);
    }

    /**
     * 2개의 데이터를 동일한 기준으로 양쪽 순서를 맞추어 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 1.		박준홍			최초 작성
     * </pre>
     *
     * @param <E1>
     *            데이터 유형1
     * @param <E2>
     *            데이터 유형2
     * @param <KEY>
     *            식별정보 유형
     * @param <R>
     *            새로운 데이터 유형 (E1 => R, E2 => R)
     * @param data1
     *            정렬된 데이터#1
     * @param keyProvider1
     *            데이터#1 식별정보 제공 함수
     * @param transformer1
     *            데이터#1 변환 함수 (E1 => R)
     * @param data2
     *            정렬된 데이터#2
     * @param keyProvider2
     *            데이터#2 식별정보 제공 함수
     * @param transformer2
     *            데이터#2 변환 함수 (E2 => R)
     * @param emptyCreator
     *            빈 데이터 제공 함수 ({} => R)
     * @return
     *
     * @since 2025. 9. 1.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E1, E2, KEY extends Comparable<KEY>, R> TwoValueObject<List<R>, List<R>> alignBy( //
            @Nonnull List<E1> data1, @Nonnull Function<E1, KEY> keyProvider1, @Nonnull Function<E1, R> transformer1 //
            , @Nonnull List<E2> data2, @Nonnull Function<E2, KEY> keyProvider2, @Nonnull Function<E2, R> transformer2 //
            , @Nonnull Function<KEY, R> emptyCreator //
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
     * 2025. 9. 1.		박준홍			최초 작성
     * </pre>
     *
     * @param <E1>
     *            데이터 유형1
     * @param <E2>
     *            데이터 유형2
     * @param <KEY>
     *            식별정보 유형
     * @param <R1>
     *            새로운 데이터 유형1 (E1 => R1)
     * @param <R2>
     *            새로운 데이터 유형2 (E2 => R2)
     * @param data1
     *            정렬된 데이터#1
     * @param keyProvider1
     *            데이터#1 식별정보 제공 함수
     * @param transformer1
     *            데이터#1 변환 함수 (E1 => R1)
     * @param emptyCreator1
     *            빈 데이터 제공 함수 ( {} => R1)
     * @param data2
     *            정렬된 데이터#2
     * @param keyProvider2
     *            데이터#2 식별정보 제공 함수
     * @param transformer2
     *            데이터#2 변환 함수 (E2 => R2)
     * @param emptyCreator2
     *            빈 데이터 제공 함수 ({} => R2)
     * @return
     *
     * @since 2025. 9. 1.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E1, E2, KEY extends Comparable<KEY>, R1, R2> TwoValueObject<List<R1>, List<R2>> alignBy( //
            @Nonnull List<E1> data1, @Nonnull Function<E1, KEY> keyProvider1, @Nonnull Function<E1, R1> transformer1, @Nonnull Function<KEY, R1> emptyCreator1 //
            , @Nonnull List<E2> data2, @Nonnull Function<E2, KEY> keyProvider2, @Nonnull Function<E2, R2> transformer2, @Nonnull Function<KEY, R2> emptyCreator2 //
    ) {
        AssertUtils2.notNulls(data1, keyProvider1, transformer1, emptyCreator1, data2, keyProvider2, transformer2, emptyCreator2);

        List<R1> resultData1 = new ArrayList<>();
        List<R2> resultData2 = new ArrayList<>();

        Iterator<E1> itrData1 = data1.iterator();
        E1 d1 = null;
        KEY key1 = null;
        Iterator<E2> itrData2 = data2.iterator();
        E2 d2 = null;
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
    public static void clear(Collection<?>... cols) {

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
     * 2021. 12. 14.		박준홍			최초 작성
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
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <S, T> void concatenate(Collection<S> data, Function<S, T> transformer, Consumer<T> aggregator) {
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
     * 2021. 12. 20.		박준홍			최초 작성
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
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <S, T, U> U concatenate(Collection<S> data, Function<S, T> transformer, Function<List<T>, U> aggregator) {
        return aggregator.apply(data.stream().map(transformer).collect(Collectors.toList()));
    }

    /**
     * 문자열을 키로 하는 맵에서 문자열의 대/소문자를 여부에 관계없이 키의 존재 여부를 반환합니다.
     * 
     * @param map
     * @param keyIgnoreCase
     * 
     * @return <BR>
     * @since 2012. 02. 22.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static boolean containsKeyIgnoreCase(Map<String, ?> map, String keyIgnoreCase) {
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
     * 2025. 9. 1.		박준홍			최초 작성
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
     *            데이터 변환 함수 (E => TREE)
     * @param addChild
     *            'TREE'에 데이터 추가 함수.
     *            <li>첫번재: 상위 객체
     *            <li>두번째: 하위 객체
     * @return
     *
     * @since 2025. 9. 1.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E, TREE, KEY> List<TREE> createTree(//
            @Nonnull List<E> data, @Nonnull Function<E, KEY> keyProvider, @Nonnull Function<E, KEY> parentKeyProvider //
            , @Nonnull Function<E, TREE> transformer, @Nonnull BiConsumer<TREE, TREE> addChild //
    ) {
        AssertUtils2.notNulls(data, keyProvider, parentKeyProvider, transformer, addChild);

        Map<KEY, TREE> top = new FIFOMap<>();
        Map<KEY, TREE> elements = new HashMap<>();

        KEY key = null;
        KEY parentKey = null;
        TREE tree = null;
        for (E d : data) {
            key = keyProvider.apply(d);
            tree = transformer.apply(d);
            parentKey = parentKeyProvider.apply(d);
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
     * 2017. 7. 6.		박준홍			최초 작성
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <E, K> Map<K, List<E>> elementToListValuedMap(Collection<E> col, Function<E, K> keyGen) {
        Map<K, List<E>> map = new HashMap<>();

        K k = null;
        List<E> vs = null;
        for (E e : col) {
            k = keyGen.apply(e);

            vs = map.get(k);
            if (vs == null) {
                vs = new ArrayList<>();
                map.put(k, vs);
            }

            vs.add(e);
        }

        return map;
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2014. 10. 17.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     *            a type of an element.
     * @param <K>
     *            데이터 식별정보 유형 a type of a key.
     * @param col
     *            elements
     * @param extractor
     *            a instance to create a key using an element.
     * @return
     *
     * @since 2014. 10. 17.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @deprecated Use {@link #elementToListValuedMap(Collection, Function)}, if supports JDK 1.8 or higher.
     */
    public static <E, K> Map<K, List<E>> elementToListValuedMap(Collection<E> col, IKeyExtractor<K, E> extrator) {
        Map<K, List<E>> map = new HashMap<>();

        K k = null;
        List<E> vs = null;
        for (E e : col) {
            k = extrator.getKey(e);

            vs = map.get(k);
            if (vs == null) {
                vs = new ArrayList<>();
                map.put(k, vs);
            }

            vs.add(e);
        }

        return map;
    }

    /**
     * 전체 데이터 중에 조건에 맞는 데이터만 새로운 {@link Collection}에 추가합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 7. 13.		박준홍			최초 작성
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <E, C extends Collection<E>> void get(Collection<E> col, Predicate<E> p, C newCol) {
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
     * 2021. 7. 13.		박준홍			최초 작성
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <E, C extends Collection<E>> C get(Collection<E> col, Predicate<E> p, Class<C> type) throws InstantiationException, IllegalAccessException {
        C ret = type.newInstance();
        get(col, p, ret);
        return ret;
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 1. 30.		박준홍			최초 작성
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <E> Collection<E> getIgnoreCase(Map<String, E> map, String keyIgnoreCase) {
        ArrayList<E> result = new ArrayList<>();

        for (String key : map.keySet()) {
            if (key.equalsIgnoreCase(keyIgnoreCase)) {
                result.add(map.get(key));
            }
        }

        return result;
    }

    /**
     * {@link Collection}에 포함된 데이터 중에 {@link Predicate}를 만족하는 데이터가 있는지 여부를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 7. 13.		박준홍			최초 작성
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <E> boolean has(Collection<E> col, Predicate<E> p) {
        return col.parallelStream().anyMatch(p);
    }

    public static <T> boolean isNullOrEmpty(Collection<T> col) {
        return col == null || col.size() < 1;
    }

    /**
     * Transform {@link Collection} to {@link Map}. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2017. 7. 6.		박준홍			최초 작성
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <E, K> Map<K, List<E>> listElementToListValuedMap(Collection<List<E>> col, Function<E, K> keyGen) {
        Map<K, List<E>> map = new HashMap<>();

        K k = null;
        List<E> vs;
        for (List<E> list : col) {
            for (E e : list) {
                k = keyGen.apply(e);

                vs = map.get(k);
                if (vs == null) {
                    vs = new ArrayList<>();
                    map.put(k, vs);
                }

                vs.add(e);
            }
        }

        return map;
    }

    /**
     * Transform {@link Collection} to {@link Map}. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2014. 10. 17.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     *            a type of an element.
     * @param <K>
     *            데이터 식별정보 유형 a type of a key.
     * @param col
     *            elements
     * @param extractor
     *            a instance to create a key using an element.
     * @return
     *
     * @since 2014. 10. 17
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @deprecated Use {@link #listElementToListValuedMap(Collection, Function)}, if supports JDK 1.8 or higher.
     */
    public static <E, K> Map<K, List<E>> listElementToListValuedMap(Collection<List<E>> col, IKeyExtractor<K, E> extrator) {
        Map<K, List<E>> map = new HashMap<>();

        K k = null;
        List<E> vs;
        for (List<E> list : col) {
            for (E e : list) {
                k = extrator.getKey(e);

                vs = map.get(k);
                if (vs == null) {
                    vs = new ArrayList<>();
                    map.put(k, vs);
                }

                vs.add(e);
            }
        }

        return map;
    }

    /**
     * 2개의 배열을 합쳐서 하나의 배열로 반환합니다.
     * 
     * @param t1
     * @param t2
     * @return <BR>
     * @since 2012. 2. 1.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see NullPointerException
     * @deprecated 2014. 6. 30., 대체 메소드: {@link ArrayUtils#merge(Object[], Object[])}.<br>
     *             <font color="red">다음 배포시 삭제 예정</font>
     */
    public static <E> E[] merge(E[] t1, E[] t2) {
        E[] t3 = (E[]) Array.newInstance(t1.getClass(), t1.length + t2.length);

        System.arraycopy(t1, 0, t3, 0, t1.length);
        System.arraycopy(t2, 0, t3, t1.length, t2.length);

        return t3;
    }

    /**
     * Primitive Array 를 Wrapper List 형태로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 12. 15.        박준홍         최초 작성
     * </pre>
     *
     * @param elems
     * @return
     *
     * @since 2020. 12. 15.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static List<Boolean> newList(boolean... elems) {
        List<Boolean> list = new ArrayList<>();
        if (elems != null) {
            for (boolean e : elems) {
                list.add(e);
            }
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
     * 2020. 12. 15.        박준홍         최초 작성
     * </pre>
     *
     * @param elems
     * @return
     *
     * @since 2020. 12. 15.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static List<Byte> newList(byte... elems) {
        List<Byte> list = new ArrayList<>();
        if (elems != null) {
            for (byte e : elems) {
                list.add(e);
            }
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
     * 2020. 12. 15.        박준홍         최초 작성
     * </pre>
     *
     * @param elems
     * @return
     *
     * @since 2020. 12. 15.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static List<Character> newList(char... elems) {
        List<Character> list = new ArrayList<>();
        if (elems != null) {
            for (char e : elems) {
                list.add(e);
            }
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
     * 2022. 12. 30.		박준홍			최초 작성
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
     * @version _._._
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> List<E> newList(Collection<E> col, E elem) {
        return newList(col, elem, true);
    }

    /**
     * 데이터를 추가하여 새로운 {@link List}를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 12. 30.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     *            데이터 타입
     * @param col
     *            기존 {@link Collection}
     * @param elem
     *            새로운 데이터
     * @param addIfNull
     *            새로운 데이터(elem)이 <code>null</code>인 경우 추가 여부.
     * @return
     *
     * @since 2022. 12. 30.
     * @version _._._
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> List<E> newList(Collection<E> col, E elem, boolean addIfNull) {
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
     * 2020. 12. 15.        박준홍         최초 작성
     * </pre>
     *
     * @param elems
     * @return
     *
     * @since 2020. 12. 15.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static List<Double> newList(double... elems) {
        List<Double> list = new ArrayList<>();
        if (elems != null) {
            for (double e : elems) {
                list.add(e);
            }
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
     * 2020. 12. 21.		박준홍			최초 작성
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <E> List<E> newList(E... elems) {
        List<E> list = new ArrayList<>();
        if (elems != null) {
            for (E e : elems) {
                list.add(e);
            }
        }

        return list;
    }

    /**
     * 데이터를 추가하여 새로운 {@link List} 로 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 12. 21.        박준홍         최초 작성
     * 2022. 12. 30.        박준홍     내부 구현 변경 (see {@link #newList(Collection, Object, boolean)})
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <E> List<E> newList(E elem, Collection<E> col) {
        return newList(col, elem, true);
    }

    /**
     * 데이터를 추가하여 새로운 {@link List} 로 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 12. 21.        박준홍         최초 작성
     * 2022. 12. 30.        박준홍     내부 구현 변경 (see {@link #newList(Collection, Object, boolean)})
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <E> List<E> newList(E elem, List<E> list) {
        return newList(list, elem, true);
    }

    /**
     * Primitive Array 를 Wrapper List 형태로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 12. 15.        박준홍         최초 작성
     * </pre>
     *
     * @param elems
     * @return
     *
     * @since 2020. 12. 15.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static List<Integer> newList(int... elems) {
        List<Integer> list = new ArrayList<>();
        if (elems != null) {
            for (int e : elems) {
                list.add(e);
            }
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
     * 2022. 12. 30.		박준홍			최초 작성
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
     * @version _._._
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> List<E> newList(List<E> list, E elem) {
        return newList(list, elem, true);
    }

    /**
     * 데이터를 추가하여 새로운 {@link List} 로 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 12. 21.        박준홍         최초 작성
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <E> List<E> newList(List<E> list, E... elems) {
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
     * 2020. 12. 15.        박준홍         최초 작성
     * </pre>
     *
     * @param elems
     * @return
     *
     * @since 2020. 12. 15.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static List<Long> newList(long... elems) {
        List<Long> list = new ArrayList<>();
        if (elems != null) {
            for (long e : elems) {
                list.add(e);
            }
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
     * 2020. 12. 15.		박준홍			최초 작성
     * </pre>
     *
     * @param elems
     * @return
     *
     * @since 2020. 12. 15.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static List<Short> newList(short... elems) {
        List<Short> list = new ArrayList<>();
        if (elems != null) {
            for (short e : elems) {
                list.add(e);
            }
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
     * 2020. 12. 15.        박준홍         최초 작성
     * </pre>
     *
     * @param elems
     * @return
     *
     * @since 2020. 12. 15.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static Set<Boolean> newSet(boolean... elems) {
        Set<Boolean> set = new HashSet<>();
        if (elems != null) {
            for (boolean e : elems) {
                set.add(e);
            }
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
     * 2020. 12. 15.        박준홍         최초 작성
     * </pre>
     *
     * @param elems
     * @return
     *
     * @since 2020. 12. 15.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static Set<Byte> newSet(byte... elems) {
        Set<Byte> set = new HashSet<>();
        if (elems != null) {
            for (byte e : elems) {
                set.add(e);
            }
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
     * 2020. 12. 15.        박준홍         최초 작성
     * </pre>
     *
     * @param elems
     * @return
     *
     * @since 2020. 12. 15.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static Set<Character> newSet(char... elems) {
        Set<Character> set = new HashSet<>();
        if (elems != null) {
            for (char e : elems) {
                set.add(e);
            }
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
     * 2022. 12. 30.        박준홍         최초 작성
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
     * @version _._._
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Set<E> newSet(Collection<E> col, E elem) {
        return newSet(col, elem, true);
    }

    /**
     * 데이터를 추가하여 새로운 {@link Set}를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 12. 30.        박준홍         최초 작성
     * </pre>
     *
     * @param <E>
     *            데이터 타입
     * @param col
     *            기존 {@link Collection}
     * @param elem
     *            새로운 데이터
     * @param addIfNull
     *            새로운 데이터(elem)이 <code>null</code>인 경우 추가 여부.
     * @return
     *
     * @since 2022. 12. 30.
     * @version _._._
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Set<E> newSet(Collection<E> col, E elem, boolean addIfNull) {
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
     * 2020. 12. 15.        박준홍         최초 작성
     * </pre>
     *
     * @param elems
     * @return
     *
     * @since 2020. 12. 15.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static Set<Double> newSet(double... elems) {
        Set<Double> set = new HashSet<>();
        if (elems != null) {
            for (double e : elems) {
                set.add(e);
            }
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
     * 2020. 12. 21.        박준홍         최초 작성
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <E> Set<E> newSet(E... elems) {
        Set<E> set = new HashSet<E>();
        if (elems != null) {
            for (E e : elems) {
                set.add(e);
            }
        }

        return set;
    }

    /**
     * 데이터를 추가하여 새로운 {@link Set} 로 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 12. 21.        박준홍         최초 작성
     * 2022. 12. 30.        박준홍     내부 구현 변경 (see {@link #newSet(Collection, Object, boolean)}).
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <E> Set<E> newSet(E elem, Collection<E> col) {
        return newSet(col, elem, true);
    }

    /**
     * 데이터를 추가하여 새로운 {@link Set} 로 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 12. 21.        박준홍         최초 작성
     * 2022. 12. 30.        박준홍     내부 구현 변경 (see {@link #newSet(Collection, Object, boolean)}).
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <E> Set<E> newSet(E elem, Set<E> set) {
        return newSet(set, elem, true);
    }

    /**
     * Primitive Array 를 Wrapper Set 형태로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 12. 15.        박준홍         최초 작성
     * </pre>
     *
     * @param elems
     * @return
     *
     * @since 2020. 12. 15.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static Set<Integer> newSet(int... elems) {
        Set<Integer> set = new HashSet<>();
        if (elems != null) {
            for (int e : elems) {
                set.add(e);
            }
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
     * 2020. 12. 15.        박준홍         최초 작성
     * </pre>
     *
     * @param elems
     * @return
     *
     * @since 2020. 12. 15.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static Set<Long> newSet(long... elems) {
        Set<Long> set = new HashSet<>();
        if (elems != null) {
            for (long e : elems) {
                set.add(e);
            }
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
     * 2022. 12. 30.        박준홍         최초 작성
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
     * @version _._._
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Set<E> newSet(Set<E> set, E elem) {
        return newSet(set, elem, true);
    }

    /**
     * 데이터를 추가하여 새로운 {@link Set} 로 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 12. 21.        박준홍         최초 작성
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <E> Set<E> newSet(Set<E> set, E... elems) {
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
     * 2020. 12. 15.        박준홍         최초 작성
     * </pre>
     *
     * @param elems
     * @return
     *
     * @since 2020. 12. 15.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static Set<Short> newSet(short... elems) {
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
     * 2020. 12. 15.        박준홍         최초 작성
     * </pre>
     *
     * @param elems
     * @return
     *
     * @since 2020. 12. 15.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static Vector<Boolean> newVector(boolean... elems) {
        Vector<Boolean> vector = new Vector<>();
        if (elems != null) {
            for (boolean e : elems) {
                vector.add(e);
            }
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
     * 2020. 12. 15.        박준홍         최초 작성
     * </pre>
     *
     * @param elems
     * @return
     *
     * @since 2020. 12. 15.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static Vector<Byte> newVector(byte... elems) {
        Vector<Byte> vector = new Vector<>();
        if (elems != null) {
            for (byte e : elems) {
                vector.add(e);
            }
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
     * 2020. 12. 15.        박준홍         최초 작성
     * </pre>
     *
     * @param elems
     * @return
     *
     * @since 2020. 12. 15.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static Vector<Character> newVector(char... elems) {
        Vector<Character> vector = new Vector<>();
        if (elems != null) {
            for (char e : elems) {
                vector.add(e);
            }
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
     * 2022. 12. 30.        박준홍         최초 작성
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
     * @version _._._
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Vector<E> newVector(Collection<E> col, E elem) {
        return newVector(col, elem, true);
    }

    /**
     * 데이터를 추가하여 새로운 {@link Vector}를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 12. 30.        박준홍         최초 작성
     * </pre>
     *
     * @param <E>
     *            데이터 타입
     * @param col
     *            기존 {@link Collection}
     * @param elem
     *            새로운 데이터
     * @param addIfNull
     *            새로운 데이터(elem)이 <code>null</code>인 경우 추가 여부.
     * @return
     *
     * @since 2022. 12. 30.
     * @version _._._
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Vector<E> newVector(Collection<E> col, E elem, boolean addIfNull) {
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
     * 2020. 12. 15.        박준홍         최초 작성
     * </pre>
     *
     * @param elems
     * @return
     *
     * @since 2020. 12. 15.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static Vector<Double> newVector(double... elems) {
        Vector<Double> vector = new Vector<>();
        if (elems != null) {
            for (double e : elems) {
                vector.add(e);
            }
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
     * 2020. 12. 21.        박준홍         최초 작성
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <E> Vector<E> newVector(E... elems) {
        Vector<E> vector = new Vector<E>();
        if (elems != null) {
            for (E e : elems) {
                vector.add(e);
            }
        }

        return vector;
    }

    /**
     * 데이터를 추가하여 새로운 {@link Vector} 로 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 12. 21.        박준홍         최초 작성
     * 2022. 12. 30.        박준홍     내부 구현 변경 (see {@link #newVector(Collection, Object, boolean)})
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <E> Vector<E> newVector(E elem, Collection<E> col) {
        return newVector(col, elem, true);
    }

    /**
     * 데이터를 추가하여 새로운 {@link Vector} 로 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 12. 21.        박준홍         최초 작성
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <E> Vector<E> newVector(E elem, Vector<E> vector) {
        return newVector(vector, elem, true);
    }

    /**
     * Primitive Array 를 Wrapper Vector 형태로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 12. 15.        박준홍         최초 작성
     * </pre>
     *
     * @param elems
     * @return
     *
     * @since 2020. 12. 15.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static Vector<Integer> newVector(int... elems) {
        Vector<Integer> vector = new Vector<>();
        if (elems != null) {
            for (int e : elems) {
                vector.add(e);
            }
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
     * 2020. 12. 15.        박준홍         최초 작성
     * </pre>
     *
     * @param elems
     * @return
     *
     * @since 2020. 12. 15.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static Vector<Long> newVector(long... elems) {
        Vector<Long> vector = new Vector<>();
        if (elems != null) {
            for (long e : elems) {
                vector.add(e);
            }
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
     * 2020. 12. 15.        박준홍         최초 작성
     * </pre>
     *
     * @param elems
     * @return
     *
     * @since 2020. 12. 15.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static Vector<Short> newVector(short... elems) {
        Vector<Short> vector = new Vector<>();
        if (elems != null) {
            for (short e : elems) {
                vector.add(e);
            }
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
     * 2022. 12. 30.        박준홍         최초 작성
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
     * @version _._._
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Vector<E> newVector(Vector<E> vector, E elem) {
        return newVector(vector, elem, true);
    }

    /**
     * 데이터를 추가하여 새로운 {@link Vector} 로 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 12. 21.        박준홍         최초 작성
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <E> Vector<E> newVector(Vector<E> vector, E... elems) {
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
     *            <b><code>true: asc, false: desc</code></b>
     * @return
     */
    public static <E extends Comparable<E>> Collection<E> order(Collection<E> col, boolean asc) {
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
     *            <b><code>true: asc, false: desc</code></b>
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
     * @return 정렬된 새로운 객체
     * 
     * @since 2012. 02. 15.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <K extends Comparable<K>, V> Set<Entry<K, V>> orderEntrySet(Set<Entry<K, V>> set) {
        Set<Entry<K, V>> returnedSet = new ConcurrentSkipListSet<Map.Entry<K, V>>();
        returnedSet.addAll(set);

        return returnedSet;
    }

    /**
     * 주어진 {@link Map}을 정렬한 후 새로운 {@link Map}을 반환합니다.
     * 
     * @param map
     * @param asc
     * @return 정렬된 새로운 객체
     * 
     * @since 2012. 02. 15.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <K extends Comparable<K>, V> Map<K, V> orderMap(Map<K, V> map, boolean asc) {
        Map<K, V> returnedMap = new ConcurrentSkipListMap<K, V>(asc ? new AscComparator<K>() : new DescComparator<K>());
        returnedMap.putAll(map);

        return returnedMap;
    }

    /**
     * 주어진 {@link Map}을 정렬한 후 새로운 {@link Map}을 반환합니다.
     * 
     * @param map
     * @param comparator
     * @return 정렬된 새로운 객체
     * 
     * @since 2012. 02. 15.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <K extends Comparable<K>, V> Map<K, V> orderMap(Map<K, V> map, Comparator<K> comparator) {
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
     * 2025. 9. 3..        박준홍         최초 작성
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
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <T> List<T> parallelSort(Collection<T> data, Comparator<T> sorter) {
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
     * 2025. 9. 3..        박준홍         최초 작성
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
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <T> List<T> parallelSort(Collection<T> data, Predicate<T> filter, Comparator<T> sorter) {
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
     * 2025. 9. 3.		박준홍			최초 작성
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
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E, KEY extends Comparable<KEY>> List<E> parallelSortAndMerge(Collection<E> data1, Collection<E> data2, Function<E, KEY> keyProvider) {
        return parallelSortAndMerge(data1, keyProvider, d -> d, data2, keyProvider, d -> d, (Comparator<KEY>) (k1, k2) -> k1.compareTo(k2));
    }

    /**
     * 정렬되지 않은 2개의 {@link Collection}를 상호 정렬하여 하나의 {@link List}로 제공합니다. <br>
     * 모두 정렬되어 있다면, {@link #sort(List, List, Function, Function)}를 사용하기 바랍니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 3.		박준홍			최초 작성
     * </pre>
     * 
     * @param <E>
     *            데이터 유형
     * @param <KEY>
     *            식별정보 유형
     * @param <R>
     *            새로운 데이터 유형 (E => R)
     * @param data1
     *            데이터#1
     * @param data2
     *            데이터#2
     * @param keyProvider
     *            데이터 식별정보 제공 함수
     * @param transformer
     *            데이터 변환 함수 (E => R)
     *
     * @return
     *
     * @since 2025. 9. 3.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E, KEY extends Comparable<KEY>, R> List<R> parallelSortAndMerge(Collection<E> data1, Collection<E> data2, Function<E, KEY> keyProvider,
            Function<E, R> transformer) {
        return parallelSortAndMerge(data1, keyProvider, transformer, data2, keyProvider, transformer, (Comparator<KEY>) (k1, k2) -> k1.compareTo(k2));
    }

    /**
     * 정렬되지 않은 2개의 {@link Collection}를 상호 정렬하여 하나의 {@link List}로 제공합니다. <br>
     * 모두 정렬되어 있다면, {@link #sort(List, Function, Function, List, Function, Function)}를 사용하기 바랍니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 3.		박준홍			최초 작성
     * </pre>
     *
     * @param <E1>
     *            데이터 유형1
     * @param <E2>
     *            데이터 유형2
     * @param <KEY>
     *            식별정보 유형
     * @param <R>
     *            새로운 데이터 유형 (E1 => R, E2 => R)
     * @param data1
     *            데이터#1
     * @param keyProvider1
     *            데이터#1 식별정보 제공 함수
     * @param transformer1
     *            데이터#1 변환 함수 (E1 => R)
     * @param data2
     *            데이터#2
     * @param keyProvider2
     *            데이터#2 식별정보 제공 함수
     * @param transformer2
     *            데이터#2 변환 함수 (E2 => R)
     * @return
     *
     * @since 2025. 9. 3.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E1, E2, KEY extends Comparable<KEY>, R> List<R> parallelSortAndMerge( //
            Collection<E1> data1, Function<E1, KEY> keyProvider1, Function<E1, R> transformer1 //
            , Collection<E2> data2, Function<E2, KEY> keyProvider2, Function<E2, R> transformer2 //
    ) {
        return parallelSortAndMerge(data1, keyProvider1, transformer1, data2, keyProvider2, transformer2, (Comparator<KEY>) (k1, k2) -> k1.compareTo(k2));
    }

    /**
     * 정렬되지 않은 2개의 {@link Collection}를 상호 정렬하여 하나의 {@link List}로 제공합니다. <br>
     * 모두 정렬되어 있다면, {@link #sort(List, Function, Function, List, Function, Function)}를 사용하기 바랍니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 3.		박준홍			최초 작성
     * </pre>
     *
     * @param <E1>
     *            데이터 유형1
     * @param <E2>
     *            데이터 유형2
     * @param <KEY>
     *            식별정보 유형
     * @param <R>
     *            새로운 데이터 유형 (E1 => R, E2 => R)
     * @param data1
     *            데이터#1
     * @param keyProvider1
     *            데이터#1 식별정보 제공 함수
     * @param transformer1
     *            데이터#1 변환 함수 (E1 => R)
     * @param data2
     *            데이터#2
     * @param keyProvider2
     *            데이터#2 식별정보 제공 함수
     * @param transformer2
     *            데이터#2 변환 함수 (E2 => R)
     * @return
     *
     * @since 2025. 9. 3.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E1, E2, KEY extends Comparable<KEY>, R> List<R> parallelSortAndMerge( //
            Collection<E1> data1, Function<E1, KEY> keyProvider1, Function<E1, R> transformer1 //
            , Collection<E2> data2, Function<E2, KEY> keyProvider2, Function<E2, R> transformer2 //
            , Comparator<KEY> comparator //
    ) {
        List<E1> sortedData1 = data1.parallelStream().sorted((o1, o2) -> keyProvider1.apply(o1).compareTo(keyProvider1.apply(o2))).collect(Collectors.toList());
        List<E2> sortedData2 = data2.parallelStream().sorted((o1, o2) -> keyProvider2.apply(o1).compareTo(keyProvider2.apply(o2))).collect(Collectors.toList());

        return sort(sortedData1, keyProvider1, transformer1, sortedData2, keyProvider2, transformer2, comparator);
    }

    /**
     * 주어진 {@link Collection}에 포함된 첫번째 데이터를 반환합니다.
     * 
     * @param col
     * @return 첫번째 데이터. 없는 경우 <code>null</code>.
     */
    public static <E> E pick(Collection<E> col) {
        E elem = null;

        Iterator<E> itr = col.iterator();
        if (itr.hasNext()) {
            elem = itr.next();
        }

        return elem;
    }

    /**
     * 주어진 {@link List}에서 정해진 개수만큼 데이터를 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2017. 10. 18.		박준홍			최초 작성
     * </pre>
     *
     * @param list
     * @param pos
     *            시작 위치
     * @param maxCount
     *            읽어올 개수
     * @return
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2017. 10. 18.
     */
    public static <E> List<E> read(List<E> list, int pos, int maxCount) {

        List<E> read = new ArrayList<>();

        if (pos >= list.size()) {
            return read;
        }

        int readCount = 0;

        for (int i = pos; i < list.size() && readCount < maxCount; i++, readCount++) {
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
     * 2017. 10. 18.		박준홍			최초 작성
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
     * @return {@link List}가 <code>null</code>이거나 없는 경우
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2017. 10. 18.
     */
    public static <E> E[] readAsArray(List<E> list, int pos, int maxCount, Class<E> type) {

        if (pos >= list.size()) {
            return (E[]) Array.newInstance(type, 0);
        }

        int len = Math.min(list.size() - pos, maxCount);

        int readCount = 0;

        E[] read = (E[]) Array.newInstance(type, len);

        for (int i = pos; i < list.size() && readCount < maxCount; i++, readCount++) {
            read[readCount] = list.get(i);
        }

        return read;
    }

    /**
     * 조건에 맞는 데이터를 정렬하고 지정된 개수만큼 반환합니다. (원본 유지) <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2023. 12. 13.        박준홍         최초 작성
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
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <T> List<T> sort(Collection<T> data, Comparator<T> sorter) {
        return sort(data, o -> true, sorter);
    }

    /**
     * 조건에 맞는 데이터를 정렬하고 지정된 개수만큼 반환합니다. (원본 유지) <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2023. 12. 13.        박준홍         최초 작성
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
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <T> List<T> sort(Collection<T> data, Predicate<T> filter, Comparator<T> sorter) {
        AssertUtils2.notNulls(data, filter, sorter);
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
     * 2025. 9. 3.      박준홍         최초 작성
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
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E, KEY extends Comparable<KEY>> List<E> sort(List<E> data1, List<E> data2, Function<E, KEY> keyProvider) {
        return sort(data1, keyProvider, d -> d, data2, keyProvider, d -> d, (Comparator<KEY>) (k1, k2) -> k1.compareTo(k2));
    }

    /**
     * 정렬된 2개의 {@link List}를 상호 정렬하여 하나의 {@link List}로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 3.      박준홍         최초 작성
     * </pre>
     *
     * @param <E>
     *            데이터 유형
     * @param <KEY>
     *            식별정보 유형
     * @param <R>
     *            새로운 데이터 유형 (E => R)
     * @param data1
     *            정렬된 데이터#1
     * @param data2
     *            정렬된 데이터#2
     * @param keyProvider
     *            데이터 식별정보 제공 함수
     * @param transforme
     *            데이터 변환 함수 (E => R)
     * @return
     *
     * @since 2025. 9. 3.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E, KEY extends Comparable<KEY>, R> List<R> sort(List<E> data1, List<E> data2, Function<E, KEY> keyProvider, Function<E, R> transformer) {
        return sort(data1, keyProvider, transformer, data2, keyProvider, transformer, (Comparator<KEY>) (k1, k2) -> k1.compareTo(k2));
    }

    /**
     * 정렬된 2개의 {@link List}를 상호 정렬하여 하나의 {@link List}로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 3.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     *            데이터 유형
     * @param <KEY>
     *            식별정보 유형
     * @param <R>
     *            새로운 데이터 유형 (E1 => R, E2 => R)
     * @param data1
     *            정렬된 데이터#1
     * @param data2
     *            정렬된 데이터#2
     * @param keyProvider
     *            데이터#1 식별정보 제공 함수
     * @param transformer
     *            데이터#1 변환 함수 (E1 => R)
     * @param comparator
     *            'KEY' 값 우선순위 비교 함수
     * @return
     *
     * @since 2025. 9. 3.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
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
     * 2025. 9. 3.		박준홍			최초 작성
     * </pre>
     *
     * @param <E1>
     *            데이터 유형1
     * @param <E2>
     *            데이터 유형2
     * @param <KEY>
     *            식별정보 유형
     * @param <R>
     *            새로운 데이터 유형 (E1 => R, E2 => R)
     * @param data1
     *            정렬된 데이터#1
     * @param keyProvider1
     *            데이터#1 식별정보 제공 함수
     * @param transformer1
     *            데이터#1 변환 함수 (E1 => R)
     * @param data2
     *            정렬된 데이터#2
     * @param keyProvider2
     *            데이터#2 식별정보 제공 함수
     * @param transformer2
     *            데이터#2 변환 함수 (E2 => R)
     * @return
     *
     * @since 2025. 9. 3.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E1, E2, KEY extends Comparable<KEY>, R> List<R> sort( //
            List<E1> data1, Function<E1, KEY> keyProvider1, Function<E1, R> transformer1 //
            , List<E2> data2, Function<E2, KEY> keyProvider2, Function<E2, R> transformer2 //
    ) {
        return sort(data1, keyProvider1, transformer1, data2, keyProvider2, transformer2, (Comparator<KEY>) (k1, k2) -> k1.compareTo(k2));
    }

    /**
     * 정렬된 2개의 {@link List}를 상호 정렬하여 하나의 {@link List}로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 3.		박준홍			최초 작성
     * </pre>
     *
     * @param <E1>
     *            데이터 유형1
     * @param <E2>
     *            데이터 유형2
     * @param <KEY>
     *            식별정보 유형
     * @param <R>
     *            새로운 데이터 유형 (E1 => R, E2 => R)
     * @param data1
     *            정렬된 데이터#1
     * @param keyProvider1
     *            데이터#1 식별정보 제공 함수
     * @param transformer1
     *            데이터#1 변환 함수 (E1 => R)
     * @param data2
     *            정렬된 데이터#2
     * @param keyProvider2
     *            데이터#2 식별정보 제공 함수
     * @param transformer2
     *            데이터#2 변환 함수 (E2 => R)
     * @param comparator
     *            'KEY' 값 우선순위 비교 함수
     * @return
     *
     * @since 2025. 9. 3.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E1, E2, KEY, R> List<R> sort( //
            List<E1> data1, Function<E1, KEY> keyProvider1, Function<E1, R> transformer1 //
            , List<E2> data2, Function<E2, KEY> keyProvider2, Function<E2, R> transformer2 //
            , Comparator<KEY> comparator //
    ) {
        AssertUtils2.notNulls(data1, keyProvider1, transformer1, data2, keyProvider2, transformer2, comparator);

        List<R> sorted = new ArrayList<>();

        Iterator<E1> itrData1 = data1.iterator();
        Iterator<E2> itrData2 = data2.iterator();

        E1 d1 = itrData1.hasNext() ? itrData1.next() : null;
        E2 d2 = itrData2.hasNext() ? itrData2.next() : null;
        int compared = -1;
        while (d1 != null && d2 != null) {
            compared = comparator.compare(keyProvider1.apply(d1), keyProvider2.apply(d2));
            if (compared < 0) {
                sorted.add(transformer1.apply(d1));
                d1 = itrData1.hasNext() ? itrData1.next() : null;
            } else if (compared > 0) {
                sorted.add(transformer2.apply(d2));
                d2 = itrData2.hasNext() ? itrData2.next() : null;
            } else {
                sorted.add(transformer1.apply(d1));
                sorted.add(transformer2.apply(d2));
                d1 = itrData1.hasNext() ? itrData1.next() : null;
                d2 = itrData2.hasNext() ? itrData2.next() : null;
            }
        }

        while (d1 != null) {
            sorted.add(transformer1.apply(d1));
            d1 = itrData1.hasNext() ? itrData1.next() : null;
        }
        while (d2 != null) {
            sorted.add(transformer2.apply(d2));
            d2 = itrData2.hasNext() ? itrData2.next() : null;
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
     * 2025. 9. 3.		박준홍			최초 작성
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
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E, KEY extends Comparable<KEY>> List<E> sortAndMerge(Collection<E> data1, Collection<E> data2, Function<E, KEY> keyProvider) {
        return sortAndMerge(data1, keyProvider, d -> d, data2, keyProvider, d -> d, (Comparator<KEY>) (k1, k2) -> k1.compareTo(k2));
    }

    /**
     * 정렬되지 않은 2개의 {@link Collection}를 상호 정렬하여 하나의 {@link List}로 제공합니다. <br>
     * 모두 정렬되어 있다면, {@link #sort(List, List, Function, Function)}를 사용하기 바랍니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 3.		박준홍			최초 작성
     * </pre>
     * 
     * @param <E>
     *            데이터 유형
     * @param <KEY>
     *            식별정보 유형
     * @param <R>
     *            새로운 데이터 유형 (E => R)
     * @param data1
     *            데이터#1
     * @param data2
     *            데이터#2
     * @param keyProvider
     *            데이터 식별정보 제공 함수
     * @param transformer
     *            데이터 변환 함수 (E => R)
     *
     * @return
     *
     * @since 2025. 9. 3.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E, KEY extends Comparable<KEY>, R> List<R> sortAndMerge(Collection<E> data1, Collection<E> data2, Function<E, KEY> keyProvider, Function<E, R> transformer) {
        return sortAndMerge(data1, keyProvider, transformer, data2, keyProvider, transformer, (Comparator<KEY>) (k1, k2) -> k1.compareTo(k2));
    }

    /**
     * 정렬되지 않은 2개의 {@link Collection}를 상호 정렬하여 하나의 {@link List}로 제공합니다. <br>
     * 모두 정렬되어 있다면, {@link #sort(List, Function, Function, List, Function, Function)}를 사용하기 바랍니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 3.		박준홍			최초 작성
     * </pre>
     *
     * @param <E1>
     *            데이터 유형1
     * @param <E2>
     *            데이터 유형2
     * @param <KEY>
     *            식별정보 유형
     * @param <R>
     *            새로운 데이터 유형 (E1 => R, E2 => R)
     * @param data1
     *            데이터#1
     * @param keyProvider1
     *            데이터#1 식별정보 제공 함수
     * @param transformer1
     *            데이터#1 변환 함수 (E1 => R)
     * @param data2
     *            데이터#2
     * @param keyProvider2
     *            데이터#2 식별정보 제공 함수
     * @param transformer2
     *            데이터#2 변환 함수 (E2 => R)
     * @return
     *
     * @since 2025. 9. 3.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E1, E2, KEY extends Comparable<KEY>, R> List<R> sortAndMerge( //
            Collection<E1> data1, Function<E1, KEY> keyProvider1, Function<E1, R> transformer1 //
            , Collection<E2> data2, Function<E2, KEY> keyProvider2, Function<E2, R> transformer2 //
    ) {
        return sortAndMerge(data1, keyProvider1, transformer1, data2, keyProvider2, transformer2, (Comparator<KEY>) (k1, k2) -> k1.compareTo(k2));
    }

    /**
     * 정렬되지 않은 2개의 {@link Collection}를 상호 정렬하여 하나의 {@link List}로 제공합니다. <br>
     * 모두 정렬되어 있다면, {@link #sort(List, Function, Function, List, Function, Function)}를 사용하기 바랍니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 3.		박준홍			최초 작성
     * </pre>
     *
     * @param <E1>
     *            데이터 유형1
     * @param <E2>
     *            데이터 유형2
     * @param <KEY>
     *            식별정보 유형
     * @param <R>
     *            새로운 데이터 유형 (E1 => R, E2 => R)
     * @param data1
     *            데이터#1
     * @param keyProvider1
     *            데이터#1 식별정보 제공 함수
     * @param transformer1
     *            데이터#1 변환 함수 (E1 => R)
     * @param data2
     *            데이터#2
     * @param keyProvider2
     *            데이터#2 식별정보 제공 함수
     * @param transformer2
     *            데이터#2 변환 함수 (E2 => R)
     * @return
     *
     * @since 2025. 9. 3.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E1, E2, KEY extends Comparable<KEY>, R> List<R> sortAndMerge( //
            Collection<E1> data1, Function<E1, KEY> keyProvider1, Function<E1, R> transformer1 //
            , Collection<E2> data2, Function<E2, KEY> keyProvider2, Function<E2, R> transformer2 //
            , Comparator<KEY> comparator //
    ) {
        List<E1> sortedData1 = data1.stream().sorted((o1, o2) -> keyProvider1.apply(o1).compareTo(keyProvider1.apply(o2))).collect(Collectors.toList());
        List<E2> sortedData2 = data2.stream().sorted((o1, o2) -> keyProvider2.apply(o1).compareTo(keyProvider2.apply(o2))).collect(Collectors.toList());

        return sort(sortedData1, keyProvider1, transformer1, sortedData2, keyProvider2, transformer2, comparator);
    }

    /**
     * {@link List}에서 주어진 위치(<code>s</code>)부터 시작하는 원소들을 포함하는 새로운 {@link List}를 반환합니다.
     * 
     * @param <E>
     * @param list
     * @param begin
     *            시작 위치 inclusive
     * @return
     */
    public static <E> List<E> subCollection(List<E> list, int begin) {
        if (begin > list.size() - 1) {
            return new ArrayList<E>();
        }

        List<E> newList = new ArrayList<E>();

        for (int i = begin; i < list.size(); i++) {
            newList.add(list.get(i));
        }

        return newList;
    }

    /**
     * {@link List}에서 주어진 범위(<code>s</code> ~ <code>e</code>) 내의 원소들을 포함하는 새로운 {@link List}를 반환합니다.
     * 
     * @param <E>
     * @param list
     * @param begin
     *            시작 위치 inclusive
     * @param end
     *            종료 위치 exclusive
     * @return
     */
    public static <E> List<E> subCollection(List<E> list, int begin, int end) {
        if (begin > list.size() - 1 || begin >= end || end < 0) {
            return new ArrayList<E>();
        }

        List<E> newList = new ArrayList<E>();

        for (int i = begin; i < end; i++) {
            newList.add(list.get(i));
        }

        return newList;
    }

    /**
     * 주어진 {@link Collection}에 포함된 데이터를 Array에 넣어서 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2018. 4. 18.		박준홍			최초 작성
     * </pre>
     *
     * @param E
     *            데이터 타입
     * @param col
     *            {@link Collection}
     * @param type
     *            데이터 타입 Class
     * @return
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2018. 4. 18.
     */
    public static <E> E[] toArray(Collection<E> col, Class<E> type) {

        int len = col.size();

        E[] array = type == Object.class //
                ? (E[]) new Object[len] //
                : (E[]) Array.newInstance(type, len);

        int i = 0;
        for (E t : col) {
            array[i++] = t;
        }

        return array;
    }

    /**
     * 전달받은 {@link Collection} 데이터를 처리하여 새로운 {@link Collection} 구현체로 묶어서 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 21.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     *            데이터 유형
     * @param <NE>
     *            새로운 데이터 유형
     * @param <COL>
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
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E, NE, COL extends Collection<NE>> COL toCollection(Collection<E> col, Function<E, NE> transformer, Supplier<COL> collectionSupplier) {
        return StreamUtils.toCollection(col.stream(), transformer, collectionSupplier);
    }

    /**
     * 전달받은 {@link Collection} 데이터를 처리하여 새로운 {@link Collection} 구현체로 묶어서 제공합니다. <br>
     * 단, <code>keyMapper</code>에 해당하는 값이 동일한 경우 <code>mergeFunction</code>를 통해서 객체를 하나로 병합합니다. <br>
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
     * @param <COL>
     * @param col
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
    public static <K, V, COL extends Collection<V>> COL toCollection(Collection<V> col, Function<V, K> keyMapper, Function<V, V> valueMapper, BinaryOperator<V> mergeFunction,
            Supplier<COL> collectionFactory) {
        return StreamUtils.toCollection(col.stream(), keyMapper, valueMapper, mergeFunction, collectionFactory);
    }

    /**
     * 
     * Transform {@link Collection} to extension of {@link List}.
     * 
     * @param <E>
     *            type of an element
     * @param <L>
     *            type of extension of {@link List}
     * @param col
     * @param listClass
     *            MUST be class. Not allow an interface.
     * @return
     *
     * @since 2014. 10. 17.
     * 
     * @see Class#newInstance()
     * @deprecated 2025. 8. 21., 대체 메소드: {@link #toList(Collection, Supplier)}.<br>
     *             <font color="red">다음 배포시 삭제 예정</font>
     */
    public static <E, L extends List<E>> List<E> toList(Collection<E> col, Class<L> listClass) {

        List<E> list = null;

        try {
            list = listClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (col != null) {
            list.addAll(col);
        }

        return list;
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 21.     박준홍         최초 작성
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
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E, NE> List<NE> toList(Collection<E> col, Function<E, NE> transformer) {
        return toCollection(col, transformer, (Supplier<List<NE>>) ArrayList<NE>::new);
    }

    /**
     * Transform {@link Collection} to extension of {@link List} that has an another element type.
     *
     * @param <E>
     *            a source element tyhpe
     * @param <NE>
     *            a new element type
     * @param <L>
     *            extends of {@link List}
     * @param col
     * @param transformer
     * @param listClass
     *            MUST be class. Not allow an interface.
     * @return
     *
     * @since 2017. 7. 27.
     * 
     * @see Class#newInstance()
     * 
     * @deprecated 2025. 8. 21., 대체 메소드: {@link #toList(Collection, Function, Supplier)}.<br>
     *             <font color="red">다음 배포시 삭제 예정</font>
     */
    public static <E, NE, L extends List<NE>> L toList(Collection<E> col, Function<E, NE> transformer, Class<L> listClass) {

        L list = null;

        try {

            list = listClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        for (E c : col) {
            list.add(transformer.apply(c));
        }

        return list;
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 21.     박준홍         최초 작성
     * </pre>
     *
     * @param <E>
     *            데이터 유형
     * @param <NE>
     *            새로운 데이터 유형
     * @param <L>
     *            결과 {@link List} 유형
     * @param col
     *            데이터
     * @param transformer
     *            데이터 변환 함수
     * @param listSupplier
     *            결과 {@link Listr} 객체 제공 함수.
     * @return
     *
     * @since 2025. 8. 21.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E, NE, L extends List<NE>> L toList(Collection<E> col, Function<E, NE> transformer, Supplier<L> listSupplier) {
        return toCollection(col, transformer, listSupplier);
    }

    /**
     * {@link Collection}에 포함된 데이터를 새로운 {@link List}에 담아 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 21.		박준홍			최초 작성
     * </pre>
     *
     * @param <T>
     *            데이터 유형
     * @param <LIST>
     *            결과 {@link List} 유형
     * @param col
     * @param listSupplier
     * @return
     *
     * @since 2025. 8. 21.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <T> List<T> toList(Collection<T> col) {
        return toList(col, (Supplier<List<T>>) ArrayList::new);
    }

    /**
     * {@link Collection}에 포함된 데이터를 새로운 {@link List}에 담아 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 21.		박준홍			최초 작성
     * </pre>
     *
     * @param <T>
     *            데이터 유형
     * @param <COL>
     *            결과 {@link List} 유형
     * @param col
     * @param collectionSupplier
     * @return
     *
     * @since 2025. 8. 21.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <T, COL extends Collection<T>> COL toList(Collection<T> col, Supplier<COL> collectionSupplier) {
        return toCollection(col, d -> d, collectionSupplier);
    }

    /**
     * {@link Collection}에 포함된 값을 하나의 {@link List}로 묶어서 제공합니다. <br>
     * 단, <code>keyMapper</code>에 해당하는 값이 동일한 경우 <code>mergeFunction</code>를 통해서 객체를 하나로 병합합니다.
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
     * @param col
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
    public static <K, V> List<V> toList(Collection<V> col, Function<V, K> keyMapper, BinaryOperator<V> mergeFunction) {
        return toCollection(col, keyMapper, d -> d, mergeFunction, (Supplier<List<V>>) ArrayList<V>::new);
    }

    /**
     * {@link Collection}에 포함된 값을 하나의 {@link List}로 묶어서 제공합니다. <br>
     * 단, <code>keyMapper</code>에 해당하는 값이 동일한 경우 <code>mergeFunction</code>를 통해서 객체를 하나로 병합합니다. <br>
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
     * @param <LIST>
     *            결과 {@link List} 유형
     * @param col
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
    public static <K, V, LIST extends List<V>> LIST toList(Collection<V> col, Function<V, K> keyMapper, BinaryOperator<V> mergeFunction, Supplier<LIST> listFactory) {
        return toCollection(col, keyMapper, d -> d, mergeFunction, listFactory);
    }

    /**
     * {@link Collection}에 포함된 값을 하나의 {@link List}로 묶어서 제공합니다. <br>
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
     * @param <K>
     *            데이터 식별정보 유형
     * @param <V>
     *            데이터 유형
     * @param <LIST>
     * @param col
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
    public static <K, V> List<V> toList(Collection<V> col, Function<V, K> keyMapper, Function<V, V> valueMapper, BinaryOperator<V> mergeFunction) {
        return toCollection(col, keyMapper, valueMapper, mergeFunction, (Supplier<List<V>>) ArrayList<V>::new);
    }

    /**
     * {@link Collection}에 포함된 값을 하나의 {@link List}로 묶어서 제공합니다. <br>
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
     * @param <K>
     *            데이터 식별정보 유형
     * @param <V>
     *            데이터 유형
     * @param <LIST>
     * @param col
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
    public static <K, V, LIST extends List<V>> LIST toList(Collection<V> col, Function<V, K> keyMapper, Function<V, V> valueMapper, BinaryOperator<V> mergeFunction,
            Supplier<LIST> listFactory) {
        return toCollection(col, keyMapper, valueMapper, mergeFunction, listFactory);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2018. 9. 12.		박준홍			최초 작성
     * </pre>
     *
     * @param stream
     * @param transformer
     * @return
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2018. 9. 12.
     */
    public static <E, NE> List<NE> toList(Stream<E> stream, Function<E, NE> transformer) {
        return StreamUtils.toCollection(stream, transformer, (Supplier<List<NE>>) ArrayList<NE>::new);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2018. 9. 12.		박준홍			최초 작성
     * </pre>
     *
     * @param stream
     * @param transformer
     * @param listClass
     * @return
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2018. 9. 12.
     * 
     * @deprecated 2025. 8. 21.<br>
     *             대체 메소드: {@link StreamUtils#toCollection(Stream, Function, Supplier)}.
     * 
     *             <pre>
     *            // '코드 전환' 예시
     *            toList(stream, transformaer, ArrayList.class)
     *             => StreamUtils.toCollection(stream, transformer, (Supplier<List<NE>>) ArrayList<NE>::new);
     *             : stream -> stream
     *             : transformer -> transfomer
     *             : ArrayList.class ->  (Supplier<List<NE>>) ArrayList<NE>::new) // '(Supplier&lt;List&lt;NE&gt;&gt;)'는 Generic 정보를 명확하게 전달하기 위함.
     *             </pre>
     * 
     *             <br>
     *             <font color="red">다음 배포시 삭제 예정</font>
     */
    public static <E, NE, L extends List<NE>> L toList(Stream<E> stream, Function<E, NE> transformer, Class<L> listClass) {
        L list = null;

        try {
            list = listClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        list.addAll(stream.map(transformer).collect(Collectors.toList()));

        return list;
    }

    /**
     * {@link Collection} 데이터를 <code>keyMapper</code>로 구분되는 {@link Map} 형테로 제공합니다.<br>
     * 단, <code>keyMapper</code> 결과 값이 동일한 데이터의 경우 나중에 추가되는 데이터만 존재합니다.<br>
     * <code>keyMapper</code> 결과 값이 동일한 경우에 대해서 제어하고 싶은 경우,<br>
     * {@link StreamUtils#toMap(Stream, Function, Function, BinaryOperator, Supplier)} 또는 <br>
     * {@link StreamUtils#toMap(Stream, Function, BinaryOperator, Function, Supplier)} 를 사용하기 바랍니다.<br>
     * 데이터를 병합하지 않고 모두 유지하려는 경우<br>
     * {@link StreamUtils#toMap(Stream, Function, Function)},<br>
     * {@link StreamUtils#toMap(Stream, Function, Function, Supplier)},<br>
     * {@link StreamUtils#toMap(Stream, Function, Function, Supplier, Supplier)} 를 사용하기 바랍니다.<br>
     * <font color="red">단, 반환데이터 유형이 Map&lt;K,List&lt;E&gt;&gt; 형태로 현재 메소드이 반환 데이터와는 다른 점</font>을 유의하기 바랍니다.
     * 
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2017. 7. 60.		박준홍			최초 작성
     * 2025. 8. 21.     박준홍         {@link Stream} API로 변환. 
     * </pre>
     *
     * @param <E>
     *            데이터 유형
     * @param <K>
     *            데이터 식별정보 유형
     * @param col
     *            데이터 제공 객체.
     * @param keyMapper
     *            데이터 식별정보 제공 함수.
     * @return
     *
     * @since 2017. 7. 6.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     */
    public static <E, K> Map<K, E> toMap(Collection<E> col, Function<E, K> keyMapper) {
        return toMap(col, keyMapper, (Supplier<Map<K, E>>) HashMap<K, E>::new);
    }

    /**
     * Tranform {@link Collection} to the specified {@link Map}.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2017. 9. 11.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     *            입력 데이터 타입
     * @param <K>
     *            데이터 식별정보 유형
     * @param <M>
     *            새로운 {@link Map} 타입
     * @param col
     *            elements
     * @param keyGen
     *            데이터 식별정보 제공 함수.
     * @param mapClass
     *            a subclass of {@link Map}
     * @return
     *
     * @since 2017. 9. 11.
     * 
     * @deprecated 2025. 8. 21., 대체 메소드: {@link #toMap(Collection, Function, Supplier)}.<br>
     *             <font color="red">다음 배포시 삭제 예정</font>
     */
    public static <E, K, M extends Map<K, E>> M toMap(Collection<E> col, Function<E, K> keyGen, Class<M> mapClass) {

        M map = null;

        try {
            map = mapClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return toMap(col, keyGen, map);
    }

    /**
     * Tranform {@link Collection} to the specified {@link Map}.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2018. 2. 8.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     *            데이터 유형
     * @param <K>
     *            데이터 식별정보 유형
     * @param <V>
     *            새로운 데이터 유형
     * @param <M>
     *            새로운 {@link Map} 타입
     * @param col
     *            elements.
     * @param keyGen
     *            데이터 식별정보 제공 함수.
     * @param valueGen
     *            a function to create a new element using an old element.
     * @return
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2018. 2. 8.
     */
    public static <E, K, V, M extends Map<K, Collection<V>>> M toMap(Collection<E> col, Function<E, K> keyGen, Function<E, V> valueGen) {
        return (M) toMap(col, keyGen, valueGen, HashMap.class);
    }

    /**
     * Tranform {@link Collection} to the specified {@link Map}.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2018. 2. 8.		박준홍			최초 작성
     * </pre>
     * 
     * @param <E>
     *            입력 데이터 타입
     * @param <K>
     *            데이터 식별정보 유형
     * @param <V>
     *            데이터 유형 새로운 value로 사용될 데이터 타입
     * @param <M>
     *            새로운 {@link Map} 타입
     * @param col
     *            elements.
     * @param keyGen
     *            데이터 식별정보 제공 함수.
     * @param valueGen
     *            a function to create a new element using an old element.
     * @param mapClass
     *            the subclass of a {@link Map}.
     * @return
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2018. 2. 8.
     * 
     * @deprecated 2025. 8. 21., 대체 메소드 목록은 아래와 같습니다.
     *             <ul>
     *             <li>{@link StreamUtils#toMap(Stream, Function, Function)}
     *             <li>{@link StreamUtils#toMap(Stream, Function, Function, Supplier)}
     *             <li>{@link StreamUtils#toMap(Stream, Function, Function, Supplier, Supplier)}
     *             </ul>
     * 
     *             <code>
     *            // '코드 전환' 예시<br>
     *            toMap(col, keyGen, valueGen, map, mapClass)<br>
     *             => StreamUtils.toMap(stream, keyMapper, valueFunction, mapSupplier, collectionSupplier);<br>
     *             : col => col.stream() ->  stream<br>
     *             : keyGen -> keyMapper<br>
     *             : valueGen -> valueFunction<br>
     *             : mapClass => (Supplier&lt;Map&lt;K, List&lt;V&gt;&gt;&gt;) HashMap&lt;K, List&lt;V&gt;&gt;::new 또는 (Supplier&lt;Map&lt;K, Set&lt;V&gt;&gt;&gt;) HashMap&lt;K, Set&lt;V&gt;&gt;::new, ...<br>
     *               -> mapSupplier // 'Supplier&lt;Map&lt;K, List&lt;V&gt;&gt;&gt;' 는 Generic 정보를 명확하게 전달하기 위함.<br>
     *             :  X => (Supplier&lt;List&lt;V&gt;&gt;) ArrayList&lt;V&gt;::new 또는 (Supplier&lt;Set&lt;V&gt;&gt;) HashSet&lt;V&gt;::new, ...<br>
     *               -> collectionSupplier // 'Supplier&lt;List&lt;V&gt;&gt;' 는 Generic 정보를 명확하게 전달하기 위함.<br>
     *             </code>
     * 
     *             <font color="red">다음 배포시 삭제 예정</font>
     */
    public static <E, K, V, M extends Map<K, Collection<V>>> M toMap(Collection<E> col, Function<E, K> keyGen, Function<E, V> valueGen, Class<M> mapClass) {
        return (M) toMap(col, keyGen, valueGen, mapClass, ArrayList.class);
    }

    /**
     * Transfor a Collection to the specified {@link Map}. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 11. 28.		박준홍			최초 작성
     * </pre>
     * 
     * @param <E>
     *            입력 데이터 타입
     * @param <K>
     *            데이터 식별정보 유형
     * @param <V>
     *            데이터 유형 새로운 value로 사용될 데이터 타입
     * @param <C>
     *            {@link Collection} 타입
     * @param <M>
     *            새로운 {@link Map} 타입
     * 
     * @param col
     *            elements.
     * @param keyGen
     *            데이터 식별정보 제공 함수.
     * @param valueGen
     *            a function to create a new element using an old element.
     * @param mapClass
     *            the subclass of a {@link Map}.
     * @param colClass
     *            the subclass of a {@link Collection}
     * 
     * @return
     *
     * @since 2019. 11. 28.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @deprecated 2025. 8. 21., 대체 메소드 목록은 아래와 같습니다.
     *             <ul>
     *             <li>{@link StreamUtils#toMap(Stream, Function, Function)}
     *             <li>{@link StreamUtils#toMap(Stream, Function, Function, Supplier)}
     *             <li>{@link StreamUtils#toMap(Stream, Function, Function, Supplier, Supplier)}
     *             </ul>
     * 
     *             <code>
     *            // '코드 전환' 예시<br>
     *            toMap(col, keyGen, valueGen, map, mapClass, colClass)<br>
     *             => StreamUtils.toMap(stream, keyMapper, valueFunction, mapSupplier, collectionSupplier);<br>
     *             : col => col.stream() ->  stream<br>
     *             : keyGen -> keyMapper<br>
     *             : valueGen -> valueFunction<br>
     *             : mapClass => (Supplier&lt;Map&lt;K, List&lt;V&gt;&gt;&gt;) HashMap&lt;K, List&lt;V&gt;&gt;::new 또는 (Supplier&lt;Map&lt;K, Set&lt;V&gt;&gt;&gt;) HashMap&lt;K, Set&lt;V&gt;&gt;::new, ...<br>
     *               -> mapSupplier // 'Supplier&lt;Map&lt;K, List&lt;V&gt;&gt;&gt;' 는 Generic 정보를 명확하게 전달하기 위함.<br>
     *             : colClass => (Supplier&lt;List&lt;V&gt;&gt;) ArrayList&lt;V&gt;::new 또는 (Supplier&lt;Set&lt;V&gt;&gt;) HashSet&lt;V&gt;::new, ...<br>
     *               -> collectionSupplier // 'Supplier&lt;List&lt;V&gt;&gt;' 는 Generic 정보를 명확하게 전달하기 위함.<br>
     *             </code>
     * 
     *             <font color="red">다음 배포시 삭제 예정</font>
     */
    public static <E, K, V, C extends Collection<V>, M extends Map<K, Collection<V>>> M toMap(Collection<E> col, Function<E, K> keyGen, Function<E, V> valueGen, Class<M> mapClass,
            Class<C> colClass) {

        M map = null;

        try {
            map = mapClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return toMap(col, keyGen, valueGen, map, colClass);
    }

    /**
     * Transfor a Collection to the specified {@link Map}. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 1. 30.		박준홍			최초 작성
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
     *            데이터 식별정보 제공 함수. ( E =@=> K)
     * @param valueGen
     *            새로운 데이터 제공 함수 (E => V)
     * @param map
     *            an instance of {@link Map}.
     * @param colClass
     *            the subclass of a {@link Collection}.
     * @return
     *
     * @since 2020. 1. 30.
     * @version 1.6.17
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @deprecated 2025. 8. 21., 대체 메소드 목록은 아래와 같습니다.
     *             <ul>
     *             <li>{@link StreamUtils#toMap(Stream, Function, Function)}
     *             <li>{@link StreamUtils#toMap(Stream, Function, Function, Supplier)}
     *             <li>{@link StreamUtils#toMap(Stream, Function, Function, Supplier, Supplier)}
     *             </ul>
     * 
     *             <code>
     *             // '코드 전환' 예시<br>
     *             toMap(col, keyGen, valueGen, map, colClass)<br>
     *             => StreamUtils.toMap(stream, keyMapper, valueFunction, mapSupplier, collectionSupplier);<br>
     *             : col => col.stream() -> stream<br>
     *             : keyGen -> keyMapper<br>
     *             : valueGen -> valueFunction<br>
     *             : map => () -> map -> mapSupplier<br>
     *             : colClass => (Supplier&lt;List&lt;V&gt;&gt;) ArrayList&lt;V&gt;::new 또는 (Supplier&lt;Set&lt;V&gt;&gt;) HashSet&lt;V&gt;::new, ...<br>
     *             -> collectionSupplier // 'Supplier&lt;List&lt;V&gt;&gt;' 는 Generic 정보를 명확하게 전달하기 위함.<br>
     *             </code>
     * 
     *             <font color="red">다음 배포시 삭제 예정</font>
     */
    public static <E, K, V, C extends Collection<V>, M extends Map<K, Collection<V>>> M toMap(Collection<E> col, Function<E, K> keyGen, Function<E, V> valueGen, M map,
            Class<C> colClass) {

        K key = null;
        Collection<V> values = null;

        for (E e : col) {
            key = keyGen.apply(e);
            values = map.get(key);

            if (values == null) {
                try {
                    values = colClass.newInstance();
                } catch (InstantiationException //
                        | IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                }

                map.put(key, values);
            }

            values.add(valueGen.apply(e));
        }

        return map;
    }

    /**
     * Tranform {@link Collection} to the specified {@link Map}. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 1. 30.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     *            a type of a value.
     * @param <K>
     *            데이터 식별정보 유형 a type of a key.
     * @param <M>
     *            a type of a {@link Map}.
     * @param col
     *            elements.
     * @param keyGen
     *            데이터 식별정보 제공 함수.
     * @param map
     * @return
     *
     * @since 2020. 1. 30.
     * @version 1.6.17
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <E, K, M extends Map<K, E>> M toMap(Collection<E> col, Function<E, K> keyGen, M map) {

        for (E v : col) {
            map.put(keyGen.apply(v), v);
        }

        return map;
    }

    /**
     * {@link Collection} 데이터를 <code>keyMapper</code>로 구분되는 {@link Map} 형테로 제공합니다.<br>
     * 단, <code>keyMapper</code> 결과 값이 동일한 데이터의 경우 나중에 추가되는 데이터만 존재합니다.<br>
     * <code>keyMapper</code> 결과 값이 동일한 경우에 대해서 제어하고 싶은 경우,<br>
     * {@link StreamUtils#toMap(Stream, Function, Function, BinaryOperator, Supplier)} 또는 <br>
     * {@link StreamUtils#toMap(Stream, Function, BinaryOperator, Function, Supplier)} 를 사용하기 바랍니다.<br>
     * 데이터를 병합하지 않고 모두 유지하려는 경우<br>
     * {@link StreamUtils#toMap(Stream, Function, Function)},<br>
     * {@link StreamUtils#toMap(Stream, Function, Function, Supplier)},<br>
     * {@link StreamUtils#toMap(Stream, Function, Function, Supplier, Supplier)} 를 사용하기 바랍니다.<br>
     * <font color="red">단, 반환데이터 유형이 Map&lt;K,List&lt;E&gt;&gt; 형태로 현재 메소드이 반환 데이터와는 다른 점</font>을 유의하기 바랍니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2017. 7. 60.     박준홍         최초 작성
     * 2025. 8. 21.     박준홍         {@link Stream} API로 변환. 
     * </pre>
     *
     * @param <E>
     *            데이터 유형
     * @param <K>
     *            데이터 식별정보 유형
     * @param <M>
     *            결과 {@link Map} 유형
     * @param col
     *            데이터 제공 객체.
     * @param keyMapper
     *            데이터 식별정보 제공 함수.
     * @param mapSupplier
     *            결과 {@link Map} 객체 제공함수.
     * @return
     *
     * @since 2017. 7. 6.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     */
    public static <E, K, M extends Map<K, E>> M toMap(Collection<E> col, Function<E, K> keyMapper, Supplier<M> mapSupplier) {
        return StreamUtils.toMap(col.stream(), keyMapper, d -> d, (d1, d2) -> d2, (Supplier<M>) mapSupplier);
    }

    /**
     * Tranform {@link Collection} to {@link Map}.
     * 
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------ 
     * 2014. 10. 17.        박준홍         최초작성
     * 2020. 1. 30.         박준홍         deprecated.
     * </pre>
     * 
     * @ppram <E> a type of an element.
     * @param <K>
     *            데이터 식별정보 유형 a type of a key.
     * @param col
     *            elements.
     * @param keyGen
     *            데이터 식별정보 제공 함수.
     * @return {@link HashMap}
     *
     * @since 2014. 10. 17.
     * 
     * @deprecated Use {@link {@link #toMap(Collection, Function)}, if supports JDK 1.8 or higher.
     */
    public static <E, K> Map<K, E> toMap(Collection<E> col, IKeyExtractor<K, E> keyGen) {
        return toMap(col, keyGen, HashMap.class);
    }

    /**
     * Transform {@link Collection} to extension of {@link Map}.
     * 
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2017. 7. 27.        박준홍         최초작성
     * 2020. 1. 30.         박준홍         deprecated.
     * </pre>
     * 
     * @param <E>
     *            a type of an element.
     * @param <K>
     *            데이터 식별정보 유형 a type of a key.
     * @param <M>
     *            a type of a {@link Map}.
     * @param col
     * @param keyGen
     * @param mapClass
     *            MUST be class. Not allow an interface.
     * @return
     *
     * @since 2017. 7. 27.
     * 
     * @deprecated Use {@link #toMap(Collection, Function, Class)}, if supports JDK 1.8 or higher.
     */
    public static <E, K, M extends Map<K, E>> M toMap(Collection<E> col, IKeyExtractor<K, E> keyGen, Class<M> mapClass) {

        M map = null;

        try {
            map = mapClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        for (E e : col) {
            map.put(keyGen.getKey(e), e);
        }

        return map;
    }

    /**
     * {@link Collection} 데이터를 새로운 형태로 변환하여 하나의 {@link Map}로 묶어서 제공합니다. <br>
     * 단, <code>keyMapper</code>에 해당하는 값이 동일한 경우 <code>mergeFunction</code>를 통해서 객체를 하나로 병합 (V + V => V) 합니다. <br>
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
     * @param <M>
     * @param col
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
    public static <K, V> Map<K, V> toMap(Collection<V> col, Function<V, K> keyMapper, BinaryOperator<V> mergeFunction) {
        return StreamUtils.toMap(col.stream(), keyMapper, d -> d, mergeFunction, (Supplier<Map<K, V>>) HashMap<K, V>::new);
    }

    /**
     * {@link Collection} 데이터를 새로운 형태로 변환하여 하나의 {@link Map}로 묶어서 제공합니다. <br>
     * 단, <code>keyMapper</code>에 해당하는 값이 동일한 경우 <code>mergeFunction</code>를 통해서 객체를 하나로 병합 ('V + V => V' => U) 합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜 | 작성자 | 내용
     * ------------------------------------------
     * 2025. 8. 20. 박준홍 최초 작성
     * </pre>
     *
     * @param <K>
     *            데이터 식별정보 유형
     * @param <V>
     *            데이터 유형
     * @param <U>
     * @param <M>
     * @param col
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
    public static <K, V, U> Map<K, U> toMap(Collection<V> col, Function<V, K> keyMapper, BinaryOperator<V> mergeFunction, Function<V, U> transformer) {
        return toMap(col, keyMapper, mergeFunction, transformer, (Supplier<Map<K, U>>) HashMap<K, U>::new);
    }

    /**
     * {@link Collection} 데이터를 새로운 형태로 변환하여 하나의 {@link Map}로 묶어서 제공합니다. <br>
     * 단, <code>keyMapper</code>에 해당하는 값이 동일한 경우 <code>mergeFunction</code>를 통해서 객체를 하나로 병합 ('V + V => V' => U) 합니다.
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
     * @param <M>
     * @param single
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
    public static <K, V, U, M extends Map<K, U>> M toMap(Collection<V> col, Function<V, K> keyMapper, BinaryOperator<V> mergeFunction, Function<V, U> transformer,
            Supplier<M> mapSupplier) {
        return StreamUtils.toMap(col.stream(), keyMapper, mergeFunction, transformer, mapSupplier);
    }

    /**
     * {@link Collection} 데이터를 새로운 형태로 변환하여 하나의 {@link Map}로 묶어서 제공합니다. <br>
     * 단, <code>keyMapper</code>에 해당하는 값이 동일한 경우 <code>mergeFunction</code>를 통해서 객체를 하나로 병합 (V + V => V) 합니다. <br>
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
     * @param <M>
     * @param col
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
    public static <K, V, M extends Map<K, V>> M toMap(Collection<V> col, Function<V, K> keyMapper, BinaryOperator<V> mergeFunction, Supplier<M> mapSupplier) {
        return StreamUtils.toMap(col.stream(), keyMapper, d -> d, mergeFunction, mapSupplier);
    }

    /**
     * {@link Collection} 데이터를 새로운 형태로 변환하여 하나의 {@link Map}로 묶어서 제공합니다. <br>
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
     * @param <K>
     *            데이터 식별정보 유형
     * @param <V>
     *            데이터 유형
     * @param <U>
     * @param <M>
     * @param col
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
    public static <K, V, U, M> Map<K, U> toMap(Collection<V> col, Function<V, K> keyMapper, Function<V, U> valueFunction, BinaryOperator<U> mergeFunction) {
        return StreamUtils.toMap(col.stream(), keyMapper, valueFunction, mergeFunction, (Supplier<Map<K, U>>) HashMap<K, U>::new);
    }

    /**
     * {@link Collection} 데이터를 새로운 형태로 변환하여 하나의 {@link Map}로 묶어서 제공합니다. <br>
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
     * @param <K>
     *            데이터 식별정보 유형
     * @param <V>
     *            데이터 유형
     * @param <U>
     * @param <M>
     * @param col
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
    public static <K, V, U, M extends Map<K, U>> M toMap(Collection<V> col, Function<V, K> keyMapper, Function<V, U> valueFunction, BinaryOperator<U> mergeFunction,
            Supplier<M> mapSupplier) {
        return StreamUtils.toMap(col.stream(), keyMapper, valueFunction, mergeFunction, mapSupplier);
    }

    /**
     * {@link Enumera} 데이터를 <code>keyGen</code>로 구분되는 {@link Map} 형테로 제공합니다.<br>
     * 단, <code>keyMapper</code> 결과 값이 동일한 데이터의 경우 나중에 추가되는 데이터만 존재합니다.<br>
     * <code>keyMapper</code> 결과 값이 동일한 경우에 대해서 제어하고 싶은 경우,<br>
     * {@link StreamUtils#toMap(Stream, Function, Function, BinaryOperator, Supplier)} 또는 <br>
     * {@link StreamUtils#toMap(Stream, Function, BinaryOperator, Function, Supplier)} 를 사용하기 바랍니다.<br>
     * 데이터를 병합하지 않고 모두 유지하려는 경우<br>
     * {@link StreamUtils#toMap(Stream, Function, Function)},<br>
     * {@link StreamUtils#toMap(Stream, Function, Function, Supplier)},<br>
     * {@link StreamUtils#toMap(Stream, Function, Function, Supplier, Supplier)} 를 사용하기 바랍니다.<br>
     * <font color="red">단, 반환데이터 유형이 Map&lt;K,List&lt;E&gt;&gt; 형태로 현재 메소드이 반환 데이터와는 다른 점</font>을 유의하기 바랍니다.
     * 
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2017. 9. 11.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     *            데이터 유형
     * @param <K>
     *            데이터 식별정보 유형
     * @param col
     *            ㄷ이터 제공 객체
     * @param keyGen
     *            데이터 식별정보 제공 함수.
     * @return
     *
     * @since 2017. 9. 11.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @see #toMap(Collection, Function)
     */
    public static <E, K> Map<K, E> toMap(Enumeration<E> col, Function<E, K> keyGen) {
        return toMap(Collections.list(col), keyGen);
    }

    /**
     * Tranform {@link Enumeration} to the specified {@link Map}. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2017. 9. 11.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     *            a type of an element.
     * @param <K>
     *            데이터 식별정보 유형 a type of a key.
     * @param <M>
     *            a type of subclass of {@link Map}, not interface.
     * @param col
     *            elements.
     * @param keyGen
     *            데이터 식별정보 제공 함수.
     * @param mapClass
     *            a sub-{@link Class} of a {@link Map}.
     * @return
     *
     * @since 2017. 9. 11.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @deprecated 2025. 8. 21., 대체 메소드: {@link #toMap(Collection, Function, Supplier)}.<br>
     *             <code>
     *              // '코드 전환' 예시<br>
     *             toMap(col, keyGen, mapClass)<br>
     *             => toMap(col, keyMapper, mapSupplier);<br>
     *             : col => col.stream() -> stream<br>
     *             : keyGen -> keyMapper<br>
     *             : mapClass => (Supplier&lt;Map&lt;K, V&gt;&gt;) HashMap&lt;K, V&gt;::new <br>
     *               -> mapSupplier // 'Supplier&lt;Map&lt;K, V&gt;&gt;' 는 Generic 정보를 명확하게 전달하기 위함.<br>
     *             </code>
     * 
     *             <font color="red">다음 배포시 삭제 예정</font>
     */
    public static <E, K, M extends Map<K, E>> M toMap(Enumeration<E> col, Function<E, K> keyGen, Class<M> mapClass) {

        M map = null;

        try {
            map = mapClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return toMap(col, keyGen, map);
    }

    /**
     * Tranform {@link Enumeration} to the specified {@link Map}. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 1. 30.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     *            a type of an element.
     * @param <K>
     *            데이터 식별정보 유형 a type of a key.
     * @param <M>
     *            a type of subclass of {@link Map}, not interface.
     * @param col
     *            elements.
     * @param keyGen
     *            데이터 식별정보 제공 함수.
     * @param map
     *            an instance of a {@link Map}.
     * 
     * @return
     *
     * @since 2020. 1. 30.
     * @version 1.6.17
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @deprecated 2025. 8. 21., 대체 메소드: {@link #toMap(Collection, Function, Supplier)}.<br>
     *             <code>
     *             // '코드 전환' 예시<br>
     *             toMap(col, keyGen, valueGen, map)<br>
     *             => toMap(stream, keyMapper, mapSupplier);<br>
     *             : col => col.stream() -> stream<br>
     *             : keyGen -> keyMapper<br>
     *             : map => () -> map -> mapSupplier<br>
     *             </code>
     * 
     *             <font color="red">다음 배포시 삭제 예정</font>
     */
    public static <E, K, M extends Map<K, E>> M toMap(Enumeration<E> col, Function<E, K> keyGen, M map) {

        E v = null;

        while (col.hasMoreElements()) {
            v = col.nextElement();

            map.put(keyGen.apply(v), v);
        }

        return map;
    }

    /**
     * Tranform {@link Collection} to {@link Map}.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2017. 9. 11.		박준홍			최초 작성
     * 2020. 1. 30.     박준홍         deprecated.
     * </pre>
     *
     * @param <E>
     *            a type of an element.
     * @param <K>
     *            데이터 식별정보 유형 a type of a key.
     * @param col
     *            elements.
     * @param keyGen
     *            데이터 식별정보 제공 함수.
     * @return
     *
     * @since 2017. 9. 11.
     * 
     * @deprecated Use {@link #toMap(Enumeration, Function, Class)}, if supports JDK 1.8 or higher.
     */
    public static <E, K> Map<K, E> toMap(Enumeration<E> col, IKeyExtractor<K, E> keyGen) {
        return toMap(col, keyGen, HashMap.class);
    }

    /**
     * Transform {@link Enumeration} to extension of {@link Map}.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2017. 9. 11.		박준홍			최초 작성
     * 2020. 1. 30.     박준홍         deprecated.
     * </pre>
     *
     * @param <E>
     *            a type of an element.
     * @param <K>
     *            데이터 식별정보 유형 a type of a key.
     * @param <M>
     *            a type of subclass of {@link Map}, not interface.
     * @param col
     *            elements.
     * @param keyGen
     *            데이터 식별정보 제공 함수.
     * @param mapClass
     *            a sub-{@link Class} of a {@link Map}.
     * @return
     *
     * @since 2017. 9. 11.
     * 
     * @deprecated Use {@link #toMap(Enumeration, Function, Class)}, if supports JDK 1.8 or higher.
     */
    public static <E, K, M extends Map<K, E>> M toMap(Enumeration<E> col, IKeyExtractor<K, E> keyGen, Class<M> mapClass) {

        M map = null;

        try {
            map = mapClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        E e = null;

        while (col.hasMoreElements()) {
            e = col.nextElement();

            map.put(keyGen.getKey(e), e);
        }

        return map;
    }

    /**
     * Tranform {@link Collection} to the specified {@link Map} that each key has a single value. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 8. 8.      박준홍         최초 작성
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <E, K, V, M extends Map<K, V>> M toMapHSV(Collection<E> col, BiFunction<E, Integer, K> keyGen, BiFunction<E, Integer, V> valueGen) {
        return (M) toMapHSV(col, keyGen, valueGen, HashMap.class);
    }

    /**
     * Tranform {@link Collection} to the specified {@link Map} that each key has a single value. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 8. 8.      박준홍         최초 작성
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
     * @param mapClass
     *            a sub-{@link Class} of a {@link Map}.
     * @return
     *
     * @since 2019. 8. 8.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <E, K, V, M extends Map<K, V>> M toMapHSV(Collection<E> col, BiFunction<E, Integer, K> keyGen, BiFunction<E, Integer, V> valueGen, Class<M> mapClass) {
        M map = null;

        try {
            map = mapClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return toMapHSV(col, keyGen, valueGen, map);
    }

    /**
     * Tranform {@link Collection} to the specified {@link Map} that each key has a single value. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 1. 30.		박준홍			최초 작성
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <E, K, V, M extends Map<K, V>> M toMapHSV(Collection<E> col, BiFunction<E, Integer, K> keyGen, BiFunction<E, Integer, V> valueGen, M map) {

        int i = 0;
        for (E e : col) {
            map.put(keyGen.apply(e, i), valueGen.apply(e, i));
            i++;
        }

        return map;
    }

    /**
     * Tranform {@link Collection} to the specified {@link Map} that each key has a single value. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 8. 8.      박준홍         최초 작성
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <E, K, V, M extends Map<K, V>> M toMapHSV(Collection<E> col, BiFunction<E, Integer, K> keyGen, Function<E, V> valueGen) {
        return (M) toMapHSV(col, keyGen, valueGen, HashMap.class);
    }

    /**
     * Tranform {@link Collection} to the specified {@link Map} that each key has a single value. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 8. 8.      박준홍         최초 작성
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
     * @param mapClass
     *            a sub-{@link Class} of a {@link Map}.
     * @return
     *
     * @since 2019. 8. 8.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <E, K, V, M extends Map<K, V>> M toMapHSV(Collection<E> col, BiFunction<E, Integer, K> keyGen, Function<E, V> valueGen, Class<M> mapClass) {
        M map = null;

        try {
            map = mapClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return toMapHSV(col, keyGen, valueGen, map);
    }

    /**
     * Tranform {@link Collection} to the specified {@link Map} that each key has a single value. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 1. 30.		박준홍			최초 작성
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <E, K, V, M extends Map<K, V>> M toMapHSV(Collection<E> col, BiFunction<E, Integer, K> keyGen, Function<E, V> valueGen, M map) {

        int i = 0;
        for (E e : col) {
            map.put(keyGen.apply(e, i), valueGen.apply(e));
            i++;
        }

        return map;
    }

    /**
     * Tranform {@link Collection} to the specified {@link Map} that each key has a single value. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 8. 8.      박준홍         최초 작성
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <E, K, V, M extends Map<K, V>> M toMapHSV(Collection<E> col, Function<E, K> keyGen, BiFunction<E, Integer, V> valueGen) {
        return (M) toMapHSV(col, keyGen, valueGen, HashMap.class);
    }

    /**
     * Tranform {@link Collection} to the specified {@link Map} that each key has a single value. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 8. 8.      박준홍         최초 작성
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
     * @param mapClass
     *            a sub-{@link Class} of a {@link Map}.
     * @return
     *
     * @since 2019. 8. 8.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <E, K, V, M extends Map<K, V>> M toMapHSV(Collection<E> col, Function<E, K> keyGen, BiFunction<E, Integer, V> valueGen, Class<M> mapClass) {
        M map = null;

        try {
            map = mapClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return toMapHSV(col, keyGen, valueGen, map);
    }

    /**
     * Tranform {@link Collection} to the specified {@link Map} that each key has a single value. <br>
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 1. 30.		박준홍			최초 작성
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <E, K, V, M extends Map<K, V>> M toMapHSV(Collection<E> col, Function<E, K> keyGen, BiFunction<E, Integer, V> valueGen, M map) {

        int i = 0;
        for (E e : col) {
            map.put(keyGen.apply(e), valueGen.apply(e, i));
            i++;
        }

        return map;
    }

    /**
     * Tranform {@link Collection} to the specified {@link Map} that each key has a single value. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 1. 15.		박준홍			최초 작성
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2019. 1. 15.
     */
    public static <E, K, V, M extends Map<K, V>> M toMapHSV(Collection<E> col, Function<E, K> keyGen, Function<E, V> valueGen) {
        return (M) toMapHSV(col, keyGen, valueGen, HashMap.class);
    }

    /**
     * Tranform {@link Collection} to the specified {@link Map} that each key has a single value. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 1. 15.		박준홍			최초 작성
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
     * @param mapClass
     *            a sub-{@link Class} of a {@link Map}.
     * @return
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2019. 1. 15.
     */
    public static <E, K, V, M extends Map<K, V>> M toMapHSV(Collection<E> col, Function<E, K> keyGen, Function<E, V> valueGen, Class<M> mapClass) {

        M map = null;

        try {
            map = mapClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return toMapHSV(col, keyGen, valueGen, map);
    }

    /**
     * Tranform {@link Collection} to the specified {@link Map} that each key has a single value. <br>
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 1. 30.		박준홍			최초 작성
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <E, K, V, M extends Map<K, V>> M toMapHSV(Collection<E> col, Function<E, K> keyGen, Function<E, V> valueGen, M map) {
        for (E e : col) {
            map.put(keyGen.apply(e), valueGen.apply(e));
        }
        return map;
    }

    /**
     * 조건에 맞는 데이터를 정렬하고 지정된 개수만큼 반환합니다. (원본 유지) <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2023. 12. 13.        박준홍         최초 작성
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
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <T> List<T> topN(Collection<T> data, Comparator<T> sorter, int limit) {
        return topN(data, o -> true, sorter, limit);
    }

    /**
     * 조건에 맞는 데이터를 정렬하고 지정된 개수만큼 반환합니다. (원본 유지) <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2023. 12. 13.		박준홍			최초 작성
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
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <T> List<T> topN(Collection<T> data, Predicate<T> filter, Comparator<T> sorter, int limit) {
        return topN(data, filter, sorter, limit, TopNStrategy.AUTO, false);
    }

    /**
     * 조건에 맞는 데이터를 정렬하고 지정된 개수만큼 반환합니다. (원본 유지) <br>
     * 원본 데이터에서 <code>filter</code>를 통과한 데이터의 개수(M)와 실제 선택하려는 개수(<code>limit</code>, N)의 값에 따라서 세부적인 구현이 분기됩니다.<br>
     * 자세한 내용은 {@link TopN#decideStrategy(int, int, boolean)} 를 참조하기 바랍니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 3.		박준홍			최초 작성
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
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see {@link TopN#setAutoConfiguration(int, double, int, double)} 을 통해서 {@link TopNStrategy}를 선택하는 설정값을 변경할 수
     *      있습니다.
     */
    public static <T> List<T> topN(Collection<T> data, Predicate<T> filter, Comparator<T> sorter, int limit, TopNStrategy strategy, boolean expensiveComparator) {
        AssertUtils2.notNulls(data, filter, sorter);
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
        TopNStrategy chosen = (strategy == TopNStrategy.AUTO) ? TopN.decideStrategy(filteredCount, limit, expensiveComparator) : strategy;

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
     * 원본 데이터에서 <code>filter</code>를 통과한 데이터의 개수(M)와 실제 선택하려는 개수(<code>limit</code>, N)의 값에 따라서 세부적인 구현이 분기되어 사용되는
     * 메소드입니다.<br>
     * 이 메소드를 직접 사용하기 보다는 {@link #topN(Collection, Comparator, int)} 메소드를 사용하는 것을 권장하며,<br>
     * 직접 이 메소드를 하는 경우에는 {@link TopN#decideStrategy(int, int, boolean)} 를 참조하기 바랍니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 3.      박준홍         최초 작성
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
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <T> List<T> topnByFullSort(Collection<T> data, Predicate<T> filter, Comparator<T> sorter, int limit) {
        AssertUtils2.notNulls(data, filter, sorter);
        AssertUtils2.isTrue(limit > -1);

        return data.parallelStream() //
                .filter(filter) //
                .sorted(sorter) //
                .limit(limit) //
                .collect(Collectors.toList());
    }

    /**
     * 조건에 맞는 데이터를 정렬하고 지정된 개수만큼 반환합니다. (원본 유지) <br>
     * 원본 데이터에서 <code>filter</code>를 통과한 데이터의 개수(M)와 실제 선택하려는 개수(<code>limit</code>, N)의 값에 따라서 세부적인 구현이 분기되어 사용되는
     * 메소드입니다.<br>
     * 이 메소드를 직접 사용하기 보다는 {@link #topN(Collection, Comparator, int)} 메소드를 사용하는 것을 권장하며,<br>
     * 직접 이 메소드를 하는 경우에는 {@link TopN#decideStrategy(int, int, boolean)} 를 참조하기 바랍니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 3.      박준홍         최초 작성
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
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <T> List<T> topnByHeap(Collection<T> data, Predicate<T> filter, Comparator<T> sorter, int limit) {
        AssertUtils2.notNulls(data, filter, sorter);
        AssertUtils2.isTrue(limit > -1);

        // N개 중 "최악"이 루트가 되도록: cmp의 자연스런 최소힙을 쓰고, 들어온 e가 루트보다 "더 낫다"(>0)면 교체합니다.
        PriorityQueue<T> filtered = new PriorityQueue<>(limit, sorter);

        for (T e : data) {
            if (!filter.test(e)) {
                continue;
            }
            if (filtered.size() < limit) {
                filtered.add(e);
            } else {
                T worst = filtered.peek(); // 현재 N개 중 최악
                // e가 더 "좋으면" 교체
                if (sorter.compare(e, worst) > 0) {
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
     * 원본 데이터에서 <code>filter</code>를 통과한 데이터의 개수(M)와 실제 선택하려는 개수(<code>limit</code>, N)의 값에 따라서 세부적인 구현이 분기되어 사용되는
     * 메소드입니다.<br>
     * 이 메소드를 직접 사용하기 보다는 {@link #topN(Collection, Comparator, int)} 메소드를 사용하는 것을 권장하며,<br>
     * 직접 이 메소드를 하는 경우에는 {@link TopN#decideStrategy(int, int, boolean)} 를 참조하기 바랍니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 3.      박준홍         최초 작성
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
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <T> List<T> topnByQuickselect(Collection<T> data, Predicate<T> filter, Comparator<T> sorter, int limit) {
        AssertUtils2.notNulls(data, filter, sorter);
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
        // partition 규칙: cmp.compare(a[i], pivot) > 0 이면 "더 좋음"으로 간주하여 왼쪽군에 둠
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
     * 2014. 10. 17.		박준홍			최초 작성
     * 2025. 8. 21.         박준홍         내부 구현 개선. (Stream API 적용)
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
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Set<E> toSet(Collection<E> col) {
        return toSet(col, (Supplier<Set<E>>) HashSet<E>::new);
    }

    /**
     * Transform {@link Set} to extension of a {@link Set}.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2014. 10. 17.        박준홍     최초 작성
     * 2023. 7. 27.         박준홍     Return Type 변경. Set&lt;E&gt; -> S
     * </pre>
     * 
     * @param <E>
     *            a type of an element.
     * @param <S>
     *            a type of subclass of {@link Set}, not interface.
     * @param col
     *            elements.
     * @param setClass
     *            a sub-{@link Class} of a {@link Set}.
     * @return
     *
     * @since 2014. 10. 17.
     * 
     * @deprecated 2025. 8. 21., 대체 메소드: {@link #toSet(Collection, Supplier)}.<br>
     *             <code>
     *            // '코드 전환' 예시<br>
     *            toSet(col, setClass)<br>
     *             => toSet(col, setSupplier);<br>
     *             : col -> col<br>
     *             : setClass => (Supplier&lt;Set&lt;E&gt;&gt;) Set&lt;E&gt;::new -> setSupplier // 'Supplier&lt;Set&lt;E&gt;&gt;' 는 Generic 정보를 명확하게 전달하기 위함.<br>
     *             </code>
     * 
     *             <font color="red">다음 배포시 삭제 예정</font>
     */
    public static <E, S extends Set<E>> S toSet(Collection<E> col, Class<S> setClass) {
        Set<E> set = null;

        if (setClass == null) {
            set = new HashSet<>();
        } else {
            try {
                set = setClass.newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return (S) toSet(col, set);
    }

    /**
     * {@link Collection}에 포함된 데이터를 변환(<code>transformer</code>)하여 {@link Set}에 담아 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2017. 7. 6.		박준홍			최초 작성
     * 2025. 8. 21.     박준홍         내구 구현 개선 (Stream API 적용)
     * </pre>
     *
     * @param <E>
     *            데이터 유형
     * @param <NE>
     *            새로운 데이터 유형
     * @param col
     *            데이터 제공 객체
     * @param transformer
     *            새로운 데이터 제공 함수
     * @return
     *
     * @since 2017. 7. 6.
     * @version
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E, NE> Set<NE> toSet(Collection<E> col, Function<E, NE> transformer) {
        return StreamUtils.toCollection(col.stream(), transformer, (Supplier<Set<NE>>) HashSet<NE>::new);
    }

    /**
     * {@link Collection}에 포함된 데이터를 변환(<code>transformer</code>)하여 {@link Set}에 담아 제공합니다. <br>
     * 
     * @param <E>
     *            데이터 유형
     * @param <NE>
     *            새로운 데이터 유형
     * @param <NS>
     *            결과 {@link Set} 유형
     * 
     * @param col
     *            데이터 제공 함수
     * @param transformer
     *            새로운 데이터 제공 함수
     * @param setClass
     *            결과 {@link Set} 유형.
     * @return
     *
     * @since 2017. 7. 27.
     * 
     * @deprecated 2025. 8. 21., 대체 메소드: {@link StreamUtils#toCollection(Stream, Function, Supplier)}.<br>
     *             <code>
     *            // '코드 전환' 예시<br>
     *            toSet(col, transformer, setClass)<br>
     *             => StreamUtils.toCollection(stream, transformer, collectionSupplier);<br>
     *             : col => col.stream() ->  stream<br>
     *             : transformer -> transformer<br>
     *             : setClass => (Supplier&lt;Set&lt;E&gt;&gt;) Set&lt;E&gt;::new -> setSupplier // 'Supplier&lt;Set&lt;E&gt;&gt;' 는 Generic 정보를 명확하게 전달하기 위함.<br>
     *             </code>
     * 
     *             <font color="red">다음 배포시 삭제 예정</font>
     */
    public static <E, NE, NS extends Set<NE>> NS toSet(Collection<E> col, Function<E, NE> transformer, Class<NS> setClass) {

        NS set = null;

        try {
            set = setClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        for (E c : col) {
            set.add(transformer.apply(c));
        }

        return set;
    }

    /**
     * Transform {@link Set} to extension of a {@link Set}. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 1. 30.		박준홍			최초 작성
     * 2023. 7. 27.         박준홍     Return Type 변경. Set&lt;E&gt; -> S
     * </pre>
     *
     * @param <E>
     *            데이터 유형
     * @param <S>
     * 
     * @param col
     *            데이터 제공 객체
     * @param set
     *            데이터가 모아지는 {@link Set} 객체.
     * @return
     *
     * @since 2020. 1. 30.
     * @version 1.6.17
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @deprecated 2025. 8. 21., 대체 메소드: {@link Collection#addAll(Collection)}.<br>
     *             <font color="red">다음 배포시 삭제 예정</font>
     */
    public static <E, S extends Set<E>> S toSet(Collection<E> col, S set) {

        if (col != null) {
            set.addAll(col);
        }

        return set;
    }

    /**
     * {@link Collection}에 포함된 데이터를 {@link Set}에 담아 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 21.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     *            데이터 유형
     * @param <SET>
     *            결과 {@link Set} 유형
     * @param col
     *            데이터 제공 객체
     * @param setSupplier
     *            결과 {@link Set} 객체 제공 함수.
     * @return
     *
     * @since 2025. 8. 21.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E, SET extends Set<E>> SET toSet(Collection<E> col, Supplier<SET> setSupplier) {
        return StreamUtils.toCollection(col.stream(), setSupplier);
    }

    /**
     * {@link Collection} 데이터를 하나의 {@link Set}로 묶어서 제공합니다. <br>
     * 단, <code>keyMapper</code>에 해당하는 값이 동일한 경우 <code>mergeFunction</code>를 통해서 객체를 하나로 병합합니다.
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
     * @param col
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
    public static <K, V> Set<V> toSet(Collection<V> col, Function<V, K> keyMapper, BinaryOperator<V> mergeFunction) {
        return toCollection(col, keyMapper, d -> d, mergeFunction, (Supplier<Set<V>>) HashSet<V>::new);
    }

    /**
     * {@link Collection} 데이터를 하나의 {@link Set}로 묶어서 제공합니다. <br>
     * 단, <code>keyMapper</code>에 해당하는 값이 동일한 경우 <code>mergeFunction</code>를 통해서 객체를 하나로 병합합니다. <br>
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
     * @param <SET>
     * @param col
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
    public static <K, V, SET extends Set<V>> SET toSet(Collection<V> col, Function<V, K> keyMapper, BinaryOperator<V> mergeFunction, Supplier<SET> setFactory) {
        return toCollection(col, keyMapper, d -> d, mergeFunction, setFactory);
    }

    /**
     * {@link Collection} 데이터를 하나의 {@link Set}로 묶어서 제공합니다. <br>
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
     * @param <K>
     *            데이터 식별정보 유형
     * @param <V>
     *            데이터 유형
     * @param <SET>
     * @param col
     * @param keyMapper
     *            객체의 식별정보를 제공하는 함수.
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
    public static <K, V> Set<V> toSet(Collection<V> col, Function<V, K> keyMapper, Function<V, V> valueMapper, BinaryOperator<V> mergeFunction) {
        return toCollection(col, keyMapper, valueMapper, mergeFunction, (Supplier<Set<V>>) HashSet<V>::new);
    }

    /**
     * {@link Collection} 데이터를 하나의 {@link Set}로 묶어서 제공합니다. <br>
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
     * @param <K>
     *            데이터 식별정보 유형
     * @param <V>
     *            데이터 유형
     * @param <SET>
     * @param col
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
    public static <K, V, SET extends Set<V>> SET toSet(Collection<V> col, Function<V, K> keyMapper, Function<V, V> valueMapper, BinaryOperator<V> mergeFunction,
            Supplier<SET> setFactory) {
        return toCollection(col, keyMapper, valueMapper, mergeFunction, setFactory);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2018. 9. 12.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     *            a type of an element.
     * @param <NE>
     *            a type of a new element.
     * @param stream
     * @param transformer
     * @return
     *
     * @since 2018. 9. 12.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @deprecated 2025. 8. 21., 대체 메소드: {@link StreamUtils#toSet(Stream, Function)}.<br>
     *             <font color="red">다음 배포시 삭제 예정</font>
     */
    public static <E, NE> Set<NE> toSet(Stream<E> stream, Function<E, NE> transformer) {
        return stream.map(transformer).collect(Collectors.toSet());
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2018. 9. 12.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     *            a type of an element.
     * @param <NE>
     *            a type of a new element.
     * @param <S>
     *            a type of a subclass of {@link Set}, not interface.
     * @param stream
     * @param transformer
     * @param setClass
     * @return
     *
     * @since 2019. 9. 12.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * 
     * @deprecated 2025. 8. 21., 대체 메소드: {@link StreamUtils#toSet(Stream, Function, Supplier)}.<br>
     *             <code>
     *            // '코드 전환' 예시<br>
     *            toSet(stream, transformer, setClass)<br>
     *             => StreamUtils.toSet(col, transformer, setSupplier);<br>
     *             : col -> col<br>
     *             : transformer -> transformer<br>
     *             : setClass => (Supplier&lt;Set&lt;E&gt;&gt;) Set&lt;E&gt;::new -> setSupplier // 'Supplier&lt;Set&lt;E&gt;&gt;' 는 Generic 정보를 명확하게 전달하기 위함.<br>
     *             </code> <font color="red">다음 배포시 삭제 예정</font>
     */
    public static <E, NE, S extends Set<NE>> S toSet(Stream<E> stream, Function<E, NE> transformer, Class<S> setClass) {

        S set = null;

        try {
            set = setClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        set.addAll(stream.map(transformer).collect(Collectors.toSet()));

        return set;
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
     * @return <BR>
     * @since 2012. 02. 22.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see {@link Object#toString()}, {@link AbstractCollection#toString()}
     */
    public static String toString(Collection<?> col) {
        StringBuffer sb = new StringBuffer();

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
     * @return <BR>
     * @since 2012. 02. 22.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see #toString(Collection, String, String, String)
     */
    public static <T> String toString(Collection<T> col, String delim) {
        return toString(col, delim, "", "");
    }

    /**
     * {@link Collection} 내부의 원소들을 주어진 구분자를 이용해서 표현한 하나의 문자열을 반환합니다.<br>
     * 각 원소는 <code>prefix</code> 및 <code>suffix</code>로 둘러싸여진다.
     * 
     * @param col
     * @param delim
     * @param prefix
     * @param suffix
     * @return <BR>
     * @since 2012. 02. 22.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <T> String toString(Collection<T> col, String delim, String prefix, String suffix) {
        StringBuffer sb = new StringBuffer();
        if (col != null && col.size() > 0) {

            Iterator<T> itr = col.iterator();

            T t = itr.next();
            APPENDER_STR.accept(sb, new String[] { prefix, t.toString(), suffix });

            while (itr.hasNext()) {
                t = itr.next();
                APPENDER_STR.accept(sb, new String[] { delim, prefix, t.toString(), suffix });
            }

            return sb.toString();
        } else {
            return "";
        }
    }

    private static class AscComparator<T extends Comparable<T>> implements java.util.Comparator<T> {
        @Override
        public int compare(T o1, T o2) {
            return o1.compareTo(o2);
        }
    }

    private static class DescComparator<T extends Comparable<T>> implements java.util.Comparator<T> {
        @Override
        public int compare(T o1, T o2) {
            return -1 * o1.compareTo(o2);

        }
    }

    /**
     * 전체 데이터(M 개)에서 'N' 개를 추출하는 기능을 지원.
     * 
     * @since 2025. 9. 3.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static class TopN {

        // ---- 하이브리드 의사결정 임계값(시작점). JMH로 환경에 맞게 미세 조정하세요.
        /** 전체 데이터 개수(M)이 작으면 FULL_SORT 유리 */
        private static int FULL_SORT_M_THREADHOLD = 20_000;
        /** N/M ≤ 10% → HEAP_SORT */
        private static double HEAP_SORT_RATIO = 0.10;
        /** N/M ≥ 40% → FULL_SORT */
        private static double FULL_SORT_RATIO = 0.40;
        /** 아주 작은 N은 HEAP_SORT 가중 */
        private static int HEAP_SORT_N_THRESHOLD = 32;

        private static final Random RNG = new Random();

        private TopN() {
        }

        /**
         * 정렬 전략을 결정합니다. <br>
         * 우선순위
         * <li>1. 전체 데이터 개수 (M)
         * <ul>
         * <li>{@link TopN#FULL_SORT_M_THREADHOLD} 보다 작으면(<code>&lt;</code>) {@link TopNStrategy#FULL_SORT}
         * </ul>
         * <li>2. 선택하려는 데이터 개수 (N) 와 N/M의 비율 고정값 (0.25)
         * <ul>
         * <li>{@link TopN#HEAP_SORT_N_THRESHOLD} 보다 작으고, (<code>&lt;</code>) 비율이 0.25 보다 작거나 같으면
         * {@link TopNStrategy#HEAP_SORT}
         * </ul>
         * <li>3. N/M 의 비율
         * <ul>
         * 설정값
         * <li>{@link TopN#HEAP_SORT_RATIO} 보다 작으면(<code>&lt;</code>) {@link TopNStrategy#HEAP_SORT}
         * <li>{@link TopN#FULL_SORT_RATIO} 보다 크거나 같으면(<code>>=</code>) {@link TopNStrategy#FULL_SORT}
         * </ul>
         * <li>4. 정렬 비교 함수의 비용에 따라
         * <ul>
         * <li>비싸면? {@link TopNStrategy#FULL_SORT}
         * <li>그렇지 않으면? {@link TopNStrategy#QUICKSELECT}
         * </ul>
         * 
         * 
         * <pre>
         * [개정이력]
         *      날짜    	| 작성자	|	내용
         * ------------------------------------------
         * 2025. 9. 3.		박준홍			최초 작성
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
         * @author Park Jun-Hong (parkjunhong77@gmail.com)
         */
        private static TopNStrategy decideStrategy(int fullCount, int limit, boolean expensiveComparator) {
            // 작은 M 보호장치: 전체 정렬이 대체로 이득
            if (fullCount < FULL_SORT_M_THREADHOLD) {
                return TopNStrategy.FULL_SORT;
            }

            double ratio = (double) limit / (double) fullCount;

            // 아주 작은 N은 힙 쪽 가중
            if (limit <= HEAP_SORT_N_THRESHOLD && ratio <= 0.25) {
                return TopNStrategy.HEAP_SORT;
            } else if (ratio < HEAP_SORT_RATIO) {
                return TopNStrategy.HEAP_SORT;
            } else if (ratio >= FULL_SORT_RATIO) {
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
         * 파티션: sorter.compare(x, pivot) > 0 인 x를 왼쪽(상위)으로 보냄. 반환값은 pivot의 최종 위치. <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜    	| 작성자	|	내용
         * ------------------------------------------
         * 2025. 9. 3.		박준홍			최초 작성
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
         * @author Park Jun-Hong (parkjunhong77@gmail.com)
         */
        private static <T> int partitionByComparator(List<T> data, Comparator<T> sorter, int left, int right, int pivotIdx) {
            T pivotVal = data.get(pivotIdx);
            swap(data, pivotIdx, right);
            int store = left;
            for (int i = left; i < right; i++) {
                // "더 좋음"을 왼쪽으로 모음
                if (sorter.compare(data.get(i), pivotVal) > 0) {
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
         * 2025. 9. 3.		박준홍			최초 작성
         * </pre>
         *
         * @param <T>
         * @param data
         * @param sorter
         * @param limit
         *
         * @since 2025. 9. 3.
         * @version 2.1.0
         * @author Park Jun-Hong (parkjunhong77@gmail.com)
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
         * 2025. 9. 3.      박준홍         최초 작성
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
         * @author Park Jun-Hong (parkjunhong77@gmail.com)
         */
        public static void setAutoConfiguration(int fullSortThreshold, double fullSortRatio, int heapSortThreshold, double heapSortRatio) {
            TopN.FULL_SORT_M_THREADHOLD = fullSortThreshold;
            TopN.FULL_SORT_RATIO = fullSortRatio;
            TopN.HEAP_SORT_N_THRESHOLD = heapSortThreshold;
            TopN.HEAP_SORT_RATIO = heapSortRatio;
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
