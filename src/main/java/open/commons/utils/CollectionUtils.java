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
package open.commons.utils;

import java.lang.reflect.Array;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import open.commons.collection.IKeyExtractor;

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
     * 새로운 데이터를 {@link Collection}에 추가한다.
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
     * 새로운 데이터를 {@link Collection}에 추가한다.
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
     * 새로운 데이터를 {@link Collection}에 추가한다.
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
     * 새로운 데이터를 {@link Collection}에 추가한다.
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
     * 새로운 데이터를 {@link Collection}에 추가한다.
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
     *            a type of a key.
     * @param col
     *            elements
     * @param keyGen
     *            a function to create a key using an element.
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
     *            a type of a key.
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
     * 전체 데이터 중에 조건에 맞는 데이터만 새로운 {@link Collection}에 추가한다. <br>
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
     *            a type of a key.
     * @param col
     *            elements
     * @param keyGen
     *            a function to create a key using an element.
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
     *            a type of a key.
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
     * 2개의 배열을 합쳐서 하나의 배열로 반환한다.
     * 
     * @param t1
     * @param t2
     * @return <BR>
     * @since 2012. 2. 1.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see NullPointerException
     * @deprecated 2014. 6. 30. Instead of, use {@link ArrayUtils#merge(Object[], Object[])}.
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
        for (char e : elems) {
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
        for (double e : elems) {
            list.add(e);
        }

        return list;
    }

    /**
     * 배열에 포함된 데이터를 {@link List} 로 반환한다. <br>
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
        List<E> list = new ArrayList<E>();
        if (elems != null) {
            list.addAll(Arrays.asList(elems));
        }

        return list;
    }

    /**
     * 데이터를 추가하여 새로운 {@link List} 로 반환한다. <br>
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
     * @param col
     *            기존 데이터
     * @return
     *
     * @since 2020. 12. 21.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <E> List<E> newList(E elem, Collection<E> col) {
        List<E> newList = newList(elem);
        if (col != null) {
            newList.addAll(col);
        }

        return newList;
    }

    /**
     * 데이터를 추가하여 새로운 {@link List} 로 반환한다. <br>
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
     * @param list
     *            기존 데이터
     * @return
     *
     * @since 2020. 12. 21.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <E> List<E> newList(E elem, List<E> list) {
        List<E> newList = newList(elem);
        if (list != null) {
            newList.addAll(list);
        }

        return newList;
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
        for (int e : elems) {
            list.add(e);
        }

        return list;
    }

    /**
     * 데이터를 추가하여 새로운 {@link List} 로 반환한다. <br>
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
        List<E> newList = (List<E>) newList(list.toArray());
        if (elems != null) {
            newList.addAll(Arrays.asList(elems));
        }

        return newList;
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
        for (char e : elems) {
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
        for (double e : elems) {
            set.add(e);
        }

        return set;
    }

    /**
     * 배열 데이터를 새로운 {@link Set} 로 반환한다. <br>
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
            set.addAll(Arrays.asList(elems));
        }

        return set;
    }

    /**
     * 데이터를 추가하여 새로운 {@link Set} 로 반환한다. <br>
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
     * @param col
     *            기존 데이터
     * @return
     *
     * @since 2020. 12. 21.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <E> Set<E> newSet(E elem, Collection<E> col) {
        Set<E> newSet = newSet(elem);
        if (col != null) {
            newSet.addAll(col);
        }

        return newSet;
    }

    /**
     * 데이터를 추가하여 새로운 {@link Set} 로 반환한다. <br>
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
     * @param set
     *            기존 데이터
     * @return
     *
     * @since 2020. 12. 21.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <E> Set<E> newSet(E elem, Set<E> set) {
        Set<E> newSet = newSet(elem);
        if (set != null) {
            newSet.addAll(set);
        }

        return newSet;
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
        for (long e : elems) {
            set.add(e);
        }

        return set;
    }

    /**
     * 데이터를 추가하여 새로운 {@link Set} 로 반환한다. <br>
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
        Set<E> newSet = (Set<E>) newSet(set.toArray());
        if (elems != null) {
            newSet.addAll(Arrays.asList(elems));
        }

        return newSet;
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
        for (short e : elems) {
            set.add(e);
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
        for (char e : elems) {
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
        for (double e : elems) {
            vector.add(e);
        }

        return vector;
    }

    /**
     * 배열 데이터를 새로운 {@link Vector} 로 반환한다. <br>
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
            vector.addAll(Arrays.asList(elems));
        }

        return vector;
    }

    /**
     * 데이터를 추가하여 새로운 {@link Vector} 로 반환한다. <br>
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
     * @param col
     *            기존 데이터
     * @return
     *
     * @since 2020. 12. 21.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <E> Vector<E> newVector(E elem, Collection<E> col) {
        Vector<E> newVector = newVector(elem);
        if (col != null) {
            newVector.addAll(col);
        }

        return newVector;
    }

    /**
     * 데이터를 추가하여 새로운 {@link Vector} 로 반환한다. <br>
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
        Vector<E> newVector = newVector(elem);
        if (vector != null) {
            newVector.addAll(vector);
        }

        return newVector;
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
        for (short e : elems) {
            vector.add(e);
        }

        return vector;
    }

    /**
     * 데이터를 추가하여 새로운 {@link Vector} 로 반환한다. <br>
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
        Vector<E> newVector = (Vector<E>) newVector(vector.toArray());
        if (elems != null) {
            newVector.addAll(Arrays.asList(elems));
        }

        return newVector;
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
    public static <E extends Comparable<E>> Collection<E> order(Collection<E> col, boolean asc) {
        ConcurrentSkipListSet<E> orderedSet = new ConcurrentSkipListSet<E>(asc ? new AscComparator<E>() : new DescComparator<E>());
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
    public static <E extends Comparable<E>> Collection<E> order(Collection<E> col, Comparator<E> comparator) {
        ConcurrentSkipListSet<E> orderedSet = new ConcurrentSkipListSet<E>(comparator);
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
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
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
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
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
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
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
    public static <E> E pick(Collection<E> col) {
        E elem = null;

        Iterator<E> itr = col.iterator();
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
     * {@link List}에서 주어진 위치(<code>s</code>)부터 시작하는 원소들을 포함하는 새로운 {@link List}를 반환한다.
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
     * {@link List}에서 주어진 범위(<code>s</code> ~ <code>e</code>) 내의 원소들을 포함하는 새로운 {@link List}를 반환한다.
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
     * @param listClass
     *            MUST be class. Not allow an interface.
     * @return
     *
     * @since 2017. 7. 27.
     * 
     * @see Class#newInstance()
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
     * Transform {@link Collection} to extension of {@link List}.
     * 
     * @param <T>
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
     */
    public static <T, L extends List<T>> List<T> toList(Collection<T> col, Class<L> listClass) {

        List<T> list = null;

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
        return stream.map(transformer).collect(Collectors.toList());
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
     * @param mapClass
     * @return
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2018. 9. 12.
     */
    public static <E, NE, L extends List<NE>> L toList(Stream<E> stream, Function<E, NE> transformer, Class<L> mapClass) {
        L list = null;

        try {
            list = mapClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        list.addAll(stream.map(transformer).collect(Collectors.toList()));

        return list;
    }

    /**
     * Tranform {@link Collection} to {@link Map}. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2017. 7. 60.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     *            a type of an element.
     * @param <K>
     *            a type of a key.
     * @param col
     *            elements.
     * @param keyGen
     *            a function to create a key using an element.
     * @return
     *
     * @since 2017. 7. 6.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     */
    public static <E, K> Map<K, E> toMap(Collection<E> col, Function<E, K> keyGen) {
        return toMap(col, keyGen, HashMap.class);
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
     *            key로 사용될 데이터 타입
     * @param <M>
     *            새로운 {@link Map} 타입
     * @param col
     *            elements
     * @param keyGen
     *            a function to create a key using an element.
     * @param mapClass
     *            a subclass of {@link Map}
     * @return
     *
     * @since 2017. 9. 11.
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
     *            입력 데이터 타입
     * @param <K>
     *            key로 사용될 데이터 타입
     * @param <V>
     *            새로운 value로 사용될 데이터 타입
     * @param <M>
     *            새로운 {@link Map} 타입
     * @param col
     *            elements.
     * @param keyGen
     *            a function to create a key using an element.
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
     *            key로 사용될 데이터 타입
     * @param <V>
     *            새로운 value로 사용될 데이터 타입
     * @param <M>
     *            새로운 {@link Map} 타입
     * @param col
     *            elements.
     * @param keyGen
     *            a function to create a key using an element.
     * @param valueGen
     *            a function to create a new element using an old element.
     * @param mapClass
     *            the subclass of a {@link Map}.
     * @return
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2018. 2. 8.
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
     *            key로 사용될 데이터 타입
     * @param <V>
     *            새로운 value로 사용될 데이터 타입
     * @param <C>
     *            {@link Collection} 타입
     * @param <M>
     *            새로운 {@link Map} 타입
     * 
     * @param col
     *            elements.
     * @param keyGen
     *            a function to create a key using an element.
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
     *            a type of a key.
     * @param <V>
     *            a type of a new element.
     * @param <M>
     *            a type of subclass of {@link Map}, not interface.
     * @param col
     *            elements.
     * @param keyGen
     *            a function to create a key using an element.
     * @param valueGen
     *            a function to create a new element using an old element.
     * @param map
     *            an instance of {@link Map}.
     * @param colClass
     *            the subclass of a {@link Collection}.
     * @return
     *
     * @since 2020. 1. 30.
     * @version 1.6.17
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
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
     *            a type of a key.
     * @param <M>
     *            a type of a {@link Map}.
     * @param col
     *            elements.
     * @param keyGen
     *            a function to create a key using an element.
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
     *            a type of a key.
     * @param col
     *            elements.
     * @param keyGen
     *            a function to create a key using an element.
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
     *            a type of a key.
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
     * Tranform {@link Enumeration} to {@link Map}. <br>
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
     *            a type of a key.
     * @param col
     *            elements.
     * @param keyGen
     *            a function to create a key using an element.
     * @return
     *
     * @since 2017. 9. 11.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <E, K> Map<K, E> toMap(Enumeration<E> col, Function<E, K> keyGen) {
        return toMap(col, keyGen, HashMap.class);
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
     *            a type of a key.
     * @param <M>
     *            a type of subclass of {@link Map}, not interface.
     * @param col
     *            elements.
     * @param keyGen
     *            a function to create a key using an element.
     * @param mapClass
     *            a sub-{@link Class} of a {@link Map}.
     * @return
     *
     * @since 2017. 9. 11.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
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
     *            a type of a key.
     * @param <M>
     *            a type of subclass of {@link Map}, not interface.
     * @param col
     *            elements.
     * @param keyGen
     *            a function to create a key using an element.
     * @param map
     *            an instance of a {@link Map}.
     * 
     * @return
     *
     * @since 2020. 1. 30.
     * @version 1.6.17
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
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
     *            a type of a key.
     * @param col
     *            elements.
     * @param keyGen
     *            a function to create a key using an element.
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
     *            a type of a key.
     * @param <M>
     *            a type of subclass of {@link Map}, not interface.
     * @param col
     *            elements.
     * @param keyGen
     *            a function to create a key using an element.
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
     *            a type of an element.
     * @param <K>
     *            a type of a key.
     * @param <V>
     *            a type of a new element.
     * @param <M>
     *            a type of subclass of {@link Map}, not interface.
     * @param col
     *            elements.
     * @param keyGen
     *            a function to create a key using an element.
     * @param valueGen
     *            a function to create a new element using an element.
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
     *            a type of a key.
     * @param <V>
     *            a type of a new element.
     * @param <M>
     *            a type of subclass of {@link Map}, not interface.
     * @param col
     *            elements.
     * @param keyGen
     *            a function to create a key using an element.
     * @param valueGen
     *            a function to create a new element using an element.
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
     *            a type of a key.
     * @param <V>
     *            a type of a new element.
     * @param <M>
     *            a type of subclass of {@link Map}, not interface.
     * @param col
     *            elements.
     * @param keyGen
     *            a function to create a key using an element.
     * @param valueGen
     *            a function to create a new element using an element.
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
     *            a type of a key.
     * @param <V>
     *            a type of a new element.
     * @param <M>
     *            a type of subclass of {@link Map}, not interface.
     * @param col
     *            elements.
     * @param keyGen
     *            a function to create a key using an element.
     * @param valueGen
     *            a function to create a new element using an element.
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
     *            a type of a key.
     * @param <V>
     *            a type of a new element.
     * @param <M>
     *            a type of subclass of {@link Map}, not interface.
     * @param col
     *            elements.
     * @param keyGen
     *            a function to create a key using an element.
     * @param valueGen
     *            a function to create a new element using an element.
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
     *            a type of a key.
     * @param <V>
     *            a type of a new element.
     * @param <M>
     *            a type of subclass of {@link Map}, not interface.
     * @param col
     *            elements.
     * @param keyGen
     *            a function to create a key using an element.
     * @param valueGen
     *            a function to create a new element using an element.
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
     *            a type of a key.
     * @param <V>
     *            a type of a new element.
     * @param <M>
     *            a type of subclass of {@link Map}, not interface.
     * @param col
     *            elements.
     * @param keyGen
     *            a function to create a key using an element.
     * @param valueGen
     *            a function to create a new element using an element.
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
     *            a type of a key.
     * @param <V>
     *            a type of a new element.
     * @param <M>
     *            a type of subclass of {@link Map}, not interface.
     * @param col
     *            elements.
     * @param keyGen
     *            a function to create a key using an element.
     * @param valueGen
     *            a function to create a new element using an element.
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
     *            a type of a key.
     * @param <V>
     *            a type of a new element.
     * @param <M>
     *            a type of subclass of {@link Map}, not interface.
     * @param col
     *            elements.
     * @param keyGen
     *            a function to create a key using an element.
     * @param valueGen
     *            a function to create a new element using an element.
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
     *            a type of a key.
     * @param <V>
     *            a type of a new element.
     * @param <M>
     *            a type of subclass of {@link Map}, not interface.
     * @param col
     *            elements.
     * @param keyGen
     *            a function to create a key using an element.
     * @param valueGen
     *            a function to create a new element using an element.
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
     *            a type of a key.
     * @param <V>
     *            a type of a new element.
     * @param <M>
     *            a type of subclass of {@link Map}, not interface.
     * @param col
     *            elements.
     * @param keyGen
     *            a function to create a key using an element.
     * @param valueGen
     *            a function to create a new element using an element.
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
     *            a type of a key.
     * @param <V>
     *            a type of a new element.
     * @param <M>
     *            a type of subclass of {@link Map}, not interface.
     * @param col
     *            elements.
     * @param keyGen
     *            a function to create a key using an element.
     * @param valueGen
     *            a function to create a new element using an element.
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
     * Transform {@link Set} to {@link Set} that has an another element type.
     * 
     * @param <E>
     *            a type of an element.
     * @param col
     *            elements.
     * @return
     *
     * @since 2014. 10. 17.
     */
    public static <E> Set<E> toSet(Collection<E> col) {
        return toSet(col, HashSet.class);
    }

    /**
     * Transform {@link Set} to extension of a {@link Set}.
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
     */
    public static <E, S extends Set<E>> Set<E> toSet(Collection<E> col, Class<S> setClass) {
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

        return toSet(col, set);
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
     * @param setClass
     *            MUST be class. Not allow an interface.
     * @return
     *
     * @since 2017. 7. 27.
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
     * </pre>
     *
     * @param <E>
     *            a type of an element.
     * @param <S>
     *            a type of subclass of {@link Set}, not interface.
     * @param col
     *            elements.
     * @param set
     *            a instance of of a {@link Set}.
     * @return
     *
     * @since 2020. 1. 30.
     * @version 1.6.17
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <E, S extends Set<E>> Set<E> toSet(Collection<E> col, S set) {

        if (col != null) {
            set.addAll(col);
        }

        return set;
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
     * {@link Collection} 내부의 원소들을 주어진 구분자를 이용해서 표현한 하나의 문자열을 반환한다.
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
     * {@link Collection} 내부의 원소들을 주어진 구분자를 이용해서 표현한 하나의 문자열을 반환한다.<br>
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

}
