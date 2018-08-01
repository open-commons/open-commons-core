/*
 * Copyright 2011 Park Jun-Hong_(fafanmama_at_naver_com)
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
* Author: Park Jun-Hong (fafanmama_at_naver_dot_com)
* 
*/
package open.commons.utils;

import java.lang.reflect.Array;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Arrays;
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
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.function.Function;

import open.commons.collection.IKeyExtractor;

/**
 * 
 * <BR>
 * 
 * @author Park Jun-Hong (fafanmama_at_naver_dot_com) since 2011. 10. 24.
 * 
 */
@SuppressWarnings("unchecked")
public class CollectionUtils {

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

    public static <C extends Collection<E>, E> C addAll(C col, Class<? extends C> clazz, E[] elems) {

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

    public static <C extends Collection<E>, E> C addAllIfNotNull(C col, Class<? extends C> clazz, E[] elems) {

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
     * 추가하려는 데이터가 <code>null</code>이 아닌 경우 추가한다.
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
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @since 2017. 12. 13.
     */
    public static <C extends Collection<E>, E> C addIfNotNull(C col, Class<? extends C> clazz, E elem) {

        if (elem == null) {
            return col;
        }

        return add(col, clazz, elem);
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
     * 문자열을 키로 하는 맵에서 문자열의 대/소문자를 여부에 관계없이 키의 존재 여부를 반환한다.
     * 
     * @param map
     * @param keyIgnoreCase
     * 
     * @return <BR>
     * @since 2012. 02. 22.
     * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
     */
    public static boolean containsKeyIgnoreCase(Map<String, ?> map, String keyIgnoreCase) {
        for (String key : map.keySet()) {
            if (key.equalsIgnoreCase(keyIgnoreCase))
                return true;
        }

        return false;
    }

    /**
     * Transform {@link Collection} to {@link Map}.
     * 
     * @param col
     * @param extrator
     * @return
     *
     * @since 2017. 7. 6.
     */
    public static <K, V> Map<K, List<V>> elementToListValuedMap(Collection<V> col, Function<V, K> extrator) {
        Map<K, List<V>> map = new HashMap<>();

        K k = null;
        List<V> vs = null;
        for (V v : col) {
            k = extrator.apply(v);

            vs = map.get(k);
            if (vs == null) {
                vs = new ArrayList<>();
                map.put(k, vs);
            }

            vs.add(v);
        }

        return map;
    }

    /**
     * Transform {@link Collection} to {@link Map}.
     * 
     * @param col
     * @param extrator
     * @return
     *
     * @since 2014. 10. 17.
     */
    public static <K, V> Map<K, List<V>> elementToListValuedMap(Collection<V> col, IKeyExtractor<K, V> extrator) {
        Map<K, List<V>> map = new HashMap<>();

        K k = null;
        List<V> vs = null;
        for (V v : col) {
            k = extrator.getKey(v);

            vs = map.get(k);
            if (vs == null) {
                vs = new ArrayList<>();
                map.put(k, vs);
            }

            vs.add(v);
        }

        return map;
    }

    public static <T> Collection<T> getIgnoreCase(Map<String, T> map, String keyIgnoreCase) {
        ArrayList<T> result = new ArrayList<>();

        for (String key : map.keySet()) {
            if (key.equalsIgnoreCase(keyIgnoreCase)) {
                result.add(map.get(key));
            }
        }

        return result;
    }

    public static <T> boolean isNullOrEmpty(Collection<T> col) {
        return col == null || col.size() < 1;
    }

    /**
     * Transform {@link Collection} to {@link Map}.
     * 
     * @param col
     * @param extrator
     * @return
     *
     * @since 2017. 7. 6.
     */
    public static <K, V> Map<K, List<V>> listElementToListValuedMap(Collection<List<V>> col, Function<V, K> extrator) {
        Map<K, List<V>> map = new HashMap<>();

        K k = null;
        List<V> vs;
        for (List<V> list : col) {
            for (V v : list) {
                k = extrator.apply(v);

                vs = map.get(k);
                if (vs == null) {
                    vs = new ArrayList<>();
                    map.put(k, vs);
                }

                vs.add(v);
            }
        }

        return map;
    }

    /**
     * Transform {@link Collection} to {@link Map}.
     * 
     * @param col
     * @param extrator
     * @return
     *
     * @since 2014. 10. 17
     */
    public static <K, V> Map<K, List<V>> listElementToListValuedMap(Collection<List<V>> col, IKeyExtractor<K, V> extrator) {
        Map<K, List<V>> map = new HashMap<>();

        K k = null;
        List<V> vs;
        for (List<V> list : col) {
            for (V v : list) {
                k = extrator.getKey(v);

                vs = map.get(k);
                if (vs == null) {
                    vs = new ArrayList<>();
                    map.put(k, vs);
                }

                vs.add(v);
            }
        }

        return map;
    }

    /**
     * 2개의 배열을 합쳐서 하나의 배열로 반환한다.
     * 
     * @param t1
     * @param t2
     * @return <BR>
     * @since 2012. 2. 1.
     * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
     * 
     * @see NullPointerException
     * @deprecated 2014. 6. 30. Instead of, use {@link ArrayUtils#merge(Object[], Object[])}.
     */
    public static <T> T[] merge(T[] t1, T[] t2) {
        T[] t3 = (T[]) Array.newInstance(t1.getClass(), t1.length + t2.length);

        System.arraycopy(t1, 0, t3, 0, t1.length);
        System.arraycopy(t2, 0, t3, t1.length, t2.length);

        return t3;
    }

    public static <T> List<T> newList(T... elems) {
        List<T> list = new ArrayList<T>();
        if (elems != null) {
            list.addAll(Arrays.asList(elems));
        }

        return list;
    }

    public static <T> Set<T> newSet(T... elems) {
        Set<T> set = new HashSet<T>();
        if (elems != null) {
            set.addAll(Arrays.asList(elems));
        }

        return set;
    }

    public static <T> Vector<T> newVector(T... elems) {
        Vector<T> vector = new Vector<T>();
        if (elems != null) {
            vector.addAll(Arrays.asList(elems));
        }

        return vector;
    }

    /**
     * 주어진 {@link Collection}을 정렬해서 {@link Set}으로 반환한다.
     * 
     * @param col
     * @param asc
     *            정렬 방향. <br>
     *            <b><code>true: asc, false: desc</code></b>
     * @return
     */
    public static <T extends Comparable<T>> Collection<T> order(Collection<T> col, boolean asc) {
        ConcurrentSkipListSet<T> orderedSet = new ConcurrentSkipListSet<T>(asc ? new AscComparator<T>() : new DescComparator<T>());
        orderedSet.addAll(col);
        return orderedSet;
    }

    /**
     * 주어진 {@link Collection}을 정렬해서 {@link Set}으로 반환한다.
     * 
     * @param col
     * @param asc
     *            정렬 방향. <br>
     *            <b><code>true: asc, false: desc</code></b>
     * @return
     */
    public static <T extends Comparable<T>> Collection<T> order(Collection<T> col, Comparator<T> comparator) {
        ConcurrentSkipListSet<T> orderedSet = new ConcurrentSkipListSet<T>(comparator);
        orderedSet.addAll(col);
        return orderedSet;
    }

    /**
     * 주어진 {@link Set}을 정렬한 후 새로운 {@link Set}을 반환한다.
     * 
     * @param set
     * @return 정렬된 새로운 객체
     * 
     * @since 2012. 02. 15.
     * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
     */
    public static <K extends Comparable<K>, V> Set<Entry<K, V>> orderEntrySet(Set<Entry<K, V>> set) {
        Set<Entry<K, V>> returnedSet = new ConcurrentSkipListSet<Map.Entry<K, V>>();
        returnedSet.addAll(set);

        return returnedSet;
    }

    /**
     * 주어진 {@link Map}을 정렬한 후 새로운 {@link Map}을 반환한다.
     * 
     * @param map
     * @param asc
     *            TODO
     * @return 정렬된 새로운 객체
     * 
     * @since 2012. 02. 15.
     * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
     */
    public static <K extends Comparable<K>, V> Map<K, V> orderMap(Map<K, V> map, boolean asc) {
        Map<K, V> returnedMap = new ConcurrentSkipListMap<K, V>(asc ? new AscComparator<K>() : new DescComparator<K>());
        returnedMap.putAll(map);

        return returnedMap;
    }

    /**
     * 주어진 {@link Map}을 정렬한 후 새로운 {@link Map}을 반환한다.
     * 
     * @param map
     * @param asc
     *            TODO
     * @return 정렬된 새로운 객체
     * 
     * @since 2012. 02. 15.
     * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
     */
    public static <K extends Comparable<K>, V> Map<K, V> orderMap(Map<K, V> map, Comparator<K> comparator) {
        Map<K, V> returnedMap = new ConcurrentSkipListMap<K, V>(comparator);
        returnedMap.putAll(map);

        return returnedMap;
    }

    /**
     * 주어진 {@link Collection}에 포함된 첫번째 데이터를 반환한다.
     * 
     * @param col
     * @return 첫번째 데이터. 없는 경우 <code>null</code>.
     */
    public static <T> T pick(Collection<T> col) {
        T elem = null;

        Iterator<T> itr = col.iterator();
        if (itr.hasNext()) {
            elem = itr.next();
        }

        return elem;
    }

    /**
     * 주어진 {@link List}에서 정해진 개수만큼 데이터를 반환한다. <br>
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
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @since 2017. 10. 18.
     */
    public static <T> List<T> read(List<T> list, int pos, int maxCount) {

        List<T> read = new ArrayList<>();

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
     * 주어진 {@link List}에서 정해진 개수만큼 데이터를 반환한다. <br>
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
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @since 2017. 10. 18.
     */
    public static <T> T[] readAsArray(List<T> list, int pos, int maxCount, Class<T> type) {

        if (pos >= list.size()) {
            return (T[]) Array.newInstance(type, 0);
        }

        int len = Math.min(list.size() - pos, maxCount);

        int readCount = 0;

        T[] read = (T[]) Array.newInstance(type, len);

        for (int i = pos; i < list.size() && readCount < maxCount; i++, readCount++) {
            read[readCount] = list.get(i);
        }

        return read;
    }

    /**
     * {@link List}에서 주어진 위치(<code>s</code>)부터 시작하는 원소들을 포함하는 새로운 {@link List}를 반환한다.
     * 
     * @param <T>
     * @param list
     * @param s
     *            시작 위치 inclusive
     * @return
     */
    public static <T> List<T> subCollection(List<T> list, int s) {
        if (s > list.size() - 1) {
            return Collections.emptyList();
        }

        List<T> newList = new ArrayList<T>();

        for (int i = s; i < list.size(); i++) {
            newList.add(list.get(i));
        }

        return newList;
    }

    /**
     * {@link List}에서 주어진 범위(<code>s</code> ~ <code>e</code>) 내의 원소들을 포함하는 새로운 {@link List}를 반환한다.
     * 
     * @param <T>
     * @param list
     * @param s
     *            시작 위치 inclusive
     * @param e
     *            종료 위치 exclusive
     * @return
     */
    public static <T> List<T> subCollection(List<T> list, int s, int e) {
        if (s > list.size() - 1 || s >= e || e < 0) {
            return Collections.emptyList();
        }

        List<T> newList = new ArrayList<T>();

        for (int i = s; i < e; i++) {
            newList.add(list.get(i));
        }

        return newList;
    }

    /**
     * 주어진 {@link Collection}에 포함된 데이터를 Array에 넣어서 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2018. 4. 18.		박준홍			최초 작성
     * </pre>
     *
     * @param T
     *            데이터 타입
     * @param col
     *            {@link Collection}
     * @param type
     *            데이터 타입 Class
     * @return
     *
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @since 2018. 4. 18.
     */
    public static <T> T[] toArray(Collection<T> col, Class<T> type) {

        int len = col.size();

        T[] array = type == Object.class //
                ? (T[]) new Object[len] //
                : (T[]) Array.newInstance(type, len);

        int i = 0;
        for (T t : col) {
            array[i++] = t;
        }

        return array;
    }

    /**
     * Transform {@link Collection} to {@link ArrayList} that has an another element type.
     * 
     * @param col
     * @return
     *
     * @since 2014. 10. 17.
     */
    public static <E> List<E> toList(Collection<E> col) {
        return toList(col, ArrayList.class);
    }

    /**
     * Transform {@link Collection} to {@link ArrayList} that has an another element type.
     * 
     * @param <E>
     * @param <NE>
     * @param <L>
     * @param col
     * @param transformer
     * @return
     *
     * @since 2017. 7. 6.
     * 
     * @see #toList(Collection, Function, Class)
     */
    public static <E, NE> List<NE> toList(Collection<E> col, Function<E, NE> transformer) {
        return toList(col, transformer, ArrayList.class);
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
     * @param implClass
     *            MUST be class. Not allow an interface.
     * @return
     *
     * @since 2017. 7. 27.
     * 
     * @see Class#newInstance()
     */
    public static <E, NE, L extends List<NE>> L toList(Collection<E> col, Function<E, NE> transformer, Class<L> implClass) {

        L list = null;

        try {

            list = implClass.newInstance();
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
     * Transform {@link Collection} to extension of {@link List}.
     * 
     * @param <T>
     *            type of an element
     * @param <L>
     *            type of extension of {@link List}
     * @param col
     * @param implClass
     *            MUST be class. Not allow an interface.
     * @return
     *
     * @since 2014. 10. 17.
     * 
     * @see Class#newInstance()
     */
    public static <T, L extends List<T>> List<T> toList(Collection<T> col, Class<L> implClass) {

        List<T> list = null;

        try {
            list = implClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (col != null) {
            list.addAll(col);
        }

        return list;
    }

    /**
     * Tranform {@link Collection} to {@link Map}.
     * 
     * @param <K>
     * @ppram <V>
     * @param col
     * @param extrator
     * @return {@link HashMap}
     *
     * @since 2017. 7. 6.
     */
    public static <K, V> Map<K, V> toMap(Collection<V> col, Function<V, K> extrator) {
        return toMap(col, extrator, HashMap.class);
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
     * @param col
     * @param extrator
     * @param implClass
     * @return
     *
     * @since 2017. 9. 11.
     */
    public static <K, V, M extends Map<K, V>> M toMap(Collection<V> col, Function<V, K> extrator, Class<M> implClass) {

        M map = null;

        try {
            map = implClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        for (V v : col) {
            map.put(extrator.apply(v), v);
        }

        return map;
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
     * @param col
     *            elements.
     * @param fnKey
     *            a function to extract a key from an element.
     * @param fnValue
     *            a function to extrace a value from an element.
     * @return
     *
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @since 2018. 2. 8.
     */
    public static <K, V, E, M extends Map<K, List<E>>> M toMap(Collection<V> col, Function<V, K> fnKey, Function<V, E> fnValue) {
        return (M) toMap(col, fnKey, fnValue, HashMap.class);
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
     * @param col
     *            elements.
     * @param fnKey
     *            a function to extract a key from an element.
     * @param fnValue
     *            a function to extrace a value from an element.
     * @param implClass
     *            the extended class of a {@link Map}.
     * @return
     *
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @since 2018. 2. 8.
     */
    public static <K, V, E, M extends Map<K, List<E>>> M toMap(Collection<V> col, Function<V, K> fnKey, Function<V, E> fnValue, Class<M> implClass) {

        M map = null;

        try {
            map = implClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        K key = null;
        List<E> values = null;

        for (V v : col) {

            key = fnKey.apply(v);

            values = map.get(key);

            if (values == null) {
                values = new ArrayList<>();

                map.put(key, values);
            }

            values.add(fnValue.apply(v));
        }

        return map;
    }

    /**
     * Tranform {@link Collection} to {@link Map}.
     * 
     * @param <K>
     * @ppram <V>
     * @param col
     * @param extrator
     * @return {@link HashMap}
     *
     * @since 2014. 10. 17.
     */
    public static <K, V> Map<K, V> toMap(Collection<V> col, IKeyExtractor<K, V> extrator) {
        return toMap(col, extrator, HashMap.class);
    }

    /**
     * Transform {@link Collection} to extension of {@link Map}.
     * 
     * @param <K>
     * @param <V>
     * @param <M>
     * @param col
     * @param extrator
     * @param implClass
     *            MUST be class. Not allow an interface.
     * @return
     *
     * @since 2017. 7. 27.
     */
    public static <K, V, M extends Map<K, V>> M toMap(Collection<V> col, IKeyExtractor<K, V> extrator, Class<M> implClass) {

        M map = null;

        try {
            map = implClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        for (V v : col) {
            map.put(extrator.getKey(v), v);
        }

        return map;
    }

    /**
     * Tranform {@link Enumeration} to {@link Map}.
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
     * @param col
     * @param extrator
     * @return
     *
     * @since 2017. 9. 11.
     */
    public static <K, V> Map<K, V> toMap(Enumeration<V> col, Function<V, K> extrator) {
        return toMap(col, extrator, HashMap.class);
    }

    /**
     * Tranform {@link Enumeration} to the specified {@link Map}.
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
     * @param col
     * @param extrator
     * @param implClass
     * @return
     *
     * @since 2017. 9. 11.
     */
    public static <K, V, M extends Map<K, V>> M toMap(Enumeration<V> col, Function<V, K> extrator, Class<M> implClass) {

        M map = null;

        try {
            map = implClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        V v = null;

        while (col.hasMoreElements()) {
            v = col.nextElement();

            map.put(extrator.apply(v), v);
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
     * </pre>
     *
     * @param col
     * @param extrator
     * @return
     *
     * @since 2017. 9. 11.
     */
    public static <K, V> Map<K, V> toMap(Enumeration<V> col, IKeyExtractor<K, V> extrator) {
        return toMap(col, extrator, HashMap.class);
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
     * </pre>
     *
     * @param col
     * @param extrator
     * @param implClass
     * @return
     *
     * @since 2017. 9. 11.
     */
    public static <K, V, M extends Map<K, V>> M toMap(Enumeration<V> col, IKeyExtractor<K, V> extrator, Class<M> implClass) {

        M map = null;

        try {
            map = implClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        V v = null;

        while (col.hasMoreElements()) {
            v = col.nextElement();

            map.put(extrator.getKey(v), v);
        }

        return map;
    }

    /**
     * Transform {@link Set} to {@link Set} that has an another element type.
     * 
     * @param <E>
     * @param <NE>
     * @param col
     * @param transformer
     * @return
     *
     * @since 2017. 7. 6.
     */
    public static <E, NE> Set<NE> toSet(Collection<E> col, Function<E, NE> transformer) {

        Set<NE> set = toSet(null, HashSet.class);

        for (E c : col) {
            set.add(transformer.apply(c));
        }

        return set;
    }

    /**
     * Transform {@link Collection} to extended {@link Set} that has an another element type.
     * 
     * @param <E>
     *            Element type
     * @param <NE>
     *            new Element Type
     * @param <NS>
     *            new Set type
     * 
     * @param col
     * @param transformer
     * @param implClass
     *            MUST be class. Not allow an interface.
     * @return
     *
     * @since 2017. 7. 27.
     */
    public static <E, NE, NS extends Set<NE>> NS toSet(Collection<E> col, Function<E, NE> transformer, Class<NS> implClass) {

        NS set = null;

        try {
            set = implClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        for (E c : col) {
            set.add(transformer.apply(c));
        }

        return set;
    }

    /**
     * Transform {@link Set} to {@link Set} that has an another element type.
     * 
     * @param col
     * @return
     *
     * @since 2014. 10. 17.
     */
    public static <T> Set<T> toSet(Collection<T> col) {
        return toSet(col, HashSet.class);
    }

    /**
     * Transform {@link Set} to extension of a {@link Set}.
     * 
     * @param <T>
     * @param <S>
     * @param col
     * @param implClass
     *            MUST be class. Not allow an interface.
     * @return
     *
     * @since 2014. 10. 17.
     */
    public static <T, S extends Set<T>> Set<T> toSet(Collection<T> col, Class<S> implClass) {
        Set<T> set = null;

        if (implClass == null) {
            set = new HashSet<>();
        } else {
            try {
                set = implClass.newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        if (col != null) {
            set.addAll(col);
        }

        return set;
    }

    /**
     * {@link Collection} 내부의 원소들을 표현하는 하나의 문자열을 반환한다.
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
     * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
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
     * {@link Collection} 내부의 원소들을 주어진 구분자를 이용해서 표현한 하나의 문자열을 반환한다.
     * 
     * @param col
     * @param delim
     * @return <BR>
     * @since 2012. 02. 22.
     * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
     * 
     * @see #toString(Collection, String, String, String)
     */
    public static <T> String toString(Collection<T> col, String delim) {
        return toString(col, delim, "", "");
    }

    /**
     * {@link Collection} 내부의 원소들을 주어진 구분자를 이용해서 표현한 하나의 문자열을 반환한다.<br>
     * 각 원소는 <code>prefix</code> 및 <code>suffix</code>로 둘러싸여진다.
     * 
     * @param col
     * @param delim
     * @param prefix
     * @param suffix
     * @return <BR>
     * @since 2012. 02. 22.
     * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
     */
    public static <T> String toString(Collection<T> col, String delim, String prefix, String suffix) {
        StringBuffer sb = new StringBuffer();
        if (col != null && col.size() > 0) {

            Iterator<T> itr = col.iterator();

            T t = itr.next();

            sb.append(prefix + t.toString() + suffix);

            while (itr.hasNext()) {
                t = itr.next();

                sb.append(delim + prefix + t.toString() + suffix);
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
}
