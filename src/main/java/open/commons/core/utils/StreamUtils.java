/*
 * Copyright 2020 Park Jun-Hong (parkjunhong77@gmail.com)
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
 * Date  : 2020. 4. 10. 오후 1:41:53
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
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 'deprecated' 된 메소드들은 {@link FunctionUtils}를 통해서 제공됩니다.
 * 
 * <pre>
 * open.commons.core.utils.StreamUtils.build(BiFunction&lt;S, T, U&gt;, S, T, BiFunction&lt;V, W, X&gt;, V, Function&lt;U, W&gt;, Function&lt;Throwable, X&gt;): {@link FunctionUtils#build(BiFunction, Object, Object, BiFunction, Object, Function, Function)}
 * open.commons.core.utils.StreamUtils.build(BiFunction&lt;T, U, R&gt;, T, U, Function&lt;R, X&gt;, Function&lt;Throwable, X&gt;): {@link FunctionUtils#build(BiFunction, Object, Object, Function, Function)}
 * open.commons.core.utils.StreamUtils.build(Function&lt;S, T&gt;, S, Function&lt;U, X&gt;, Function&lt;T, U&gt;, Function&lt;Throwable, X&gt;): {@link FunctionUtils#build(Function, Object, Function, Function, Function)}
 * open.commons.core.utils.StreamUtils.build(Function&lt;T, R&gt;, T, Function&lt;R, X&gt;, Function&lt;Throwable, X&gt;): {@link FunctionUtils#build(Function, Function, Function)}
 * open.commons.core.utils.StreamUtils.build(Function&lt;T, Result&lt;R&gt;&gt;, T, Consumer&lt;R&gt;): {@link FunctionUtils#build(Function, Object, Consumer)}
 * open.commons.core.utils.StreamUtils.build(Function&lt;T, Result&lt;R&gt;&gt;, T, Consumer&lt;R&gt;, Function&lt;Throwable, String&gt;): {@link FunctionUtils#build(Function, Object, Consumer, Function)}
 * open.commons.core.utils.StreamUtils.build(Supplier&lt;R&gt;, Function&lt;R, X&gt;, Function&lt;Throwable, X&gt;): {@link FunctionUtils#build(Supplier, Function, Function)}
 * open.commons.core.utils.StreamUtils.build(Supplier&lt;Result&lt;R&gt;&gt;, Consumer&lt;R&gt;): {@link FunctionUtils#build(Supplier, Consumer)}
 * open.commons.core.utils.StreamUtils.build(Supplier&lt;Result&lt;R&gt;&gt;, Consumer&lt;R&gt;, Function&lt;Throwable, String&gt;): {@link FunctionUtils#build(Supplier, Consumer, Function)}
 * open.commons.core.utils.StreamUtils.build(T, Function&lt;T, R&gt;, Function&lt;R, X&gt;, Function&lt;Throwable, X&gt;): {@link #build(Function, Object, Function, Function)}
 * open.commons.core.utils.StreamUtils.build(T, Function&lt;T, Result&lt;R&gt;&gt;, Consumer&lt;R&gt;): {@link #build(Function, Object, Consumer)}
 * open.commons.core.utils.StreamUtils.build(T, Function&lt;T, Result&lt;R&gt;&gt;, Consumer&lt;R&gt;, Function&lt;Throwable, String&gt;): {@link #build(Function, Object, Consumer, Function)}
 * open.commons.core.utils.StreamUtils.build(T, U, BiFunction&lt;T, U, R&gt;, Function&lt;R, X&gt;, Function&lt;Throwable, X&gt;): {@link #build(BiFunction, Object, Object, Function, Function)}
 * open.commons.core.utils.StreamUtils.build(T, U, BiFunction&lt;T, U, Result&lt;R&gt;&gt;, Consumer&lt;R&gt;): {@link FunctionUtils#build(Object, Object, BiFunction, Consumer)}
 * open.commons.core.utils.StreamUtils.build(T, U, BiFunction&lt;T, U, Result&lt;R&gt;&gt;, Consumer&lt;R&gt;, Function&lt;Throwable, String&gt;): {@link FunctionUtils#build(Object, Object, BiFunction, Consumer, Function)}
 * open.commons.core.utils.StreamUtils.getOnAsync(Future&lt;Result&lt;R&gt;&gt;): {@link FunctionUtils#getOnAsync(Future)}
 * open.commons.core.utils.StreamUtils.runIf(T, Predicate&lt;T&gt;, Function&lt;T, R&gt;): {@link FunctionUtils#runIf(Object, Predicate, Function)}
 * open.commons.core.utils.StreamUtils.runIf(T, Predicate&lt;T&gt;, Function&lt;T, R&gt;, Supplier&lt;R&gt;): {@link FunctionUtils#runIf(Object, Predicate, Function, Supplier)}
 * open.commons.core.utils.StreamUtils.runIf(T, Predicate&lt;T&gt;, Function&lt;T, U&gt;, Function&lt;U, R&gt;): {@link FunctionUtils#runIf(Object, Predicate, Function, Function)}
 * open.commons.core.utils.StreamUtils.runIf(T, Predicate&lt;T&gt;, Function&lt;T, U&gt;, Function&lt;U, R&gt;, Supplier&lt;R&gt;): {@link FunctionUtils#runIf(Object, Predicate, Function, Function, Supplier)}
 * open.commons.core.utils.StreamUtils.runIf(T, Predicate&lt;T&gt;, Supplier&lt;U&gt;, Function&lt;U, R&gt;): {@link FunctionUtils#runIf(Object, Predicate, Supplier, Function)}
 * open.commons.core.utils.StreamUtils.runIf(T, Predicate&lt;T&gt;, Supplier&lt;U&gt;, Function&lt;U, R&gt;, Supplier&lt;R&gt;): {@link FunctionUtils#runIf(Object, Predicate, Supplier, Function, Supplier)}
 * open.commons.core.utils.StreamUtils.runOnAsync(Predicate&lt;R&gt;, Supplier&lt;R&gt;...): {@link FunctionUtils#runOnAsync(Predicate, Supplier...)}
 * open.commons.core.utils.StreamUtils.runOnSync(Predicate&lt;R&gt;, Supplier&lt;R&gt;...): {@link FunctionUtils#runOnSync(Predicate, Supplier...)}
 * </pre>
 * 
 * 
 * @since 2020. 4. 10.
 * @version 1.8.0
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */
public class StreamUtils {

    // prevent to create a new instance.
    private StreamUtils() {
    }

    static final <T> Function<T, T> ID() {
        return o -> o;
    }

    /**
     * Returns a merge function, suitable for use in {@link Map#merge(Object, Object, BiFunction) Map.merge()} or
     * {@link #toMap(Function, Function, BinaryOperator) toMap()}, which always throws {@code IllegalStateException}.
     * This can be used to enforce the assumption that the elements being collected are distinct.
     *
     * @param <T>
     *            the type of input arguments to the merge function
     * @return a merge function which always throw {@code IllegalStateException}
     * 
     * @see Collectors#throwingMerger()
     */
    static <T> BinaryOperator<T> throwingMerger() {
        return (u, v) -> {
            throw new IllegalStateException(String.format("Duplicate key %s", u));
        };
    }

    /**
     * 데이터(E)를 새로운 데이터 유형(NE)로 변환하여 제공(COL)합니다. <br>
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
     *            결과 {@link Collection} 유형.
     * @param stream
     *            데이터 제공 객체
     * @param transformer
     *            데이터 변환 함수 (E => NE)
     * @param collectionSupplier
     *            결과 {@link Collection} 객체 제공 함수.
     * @return
     *
     * @since 2025. 8. 21.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E, NE, COL extends Collection<NE>> COL toCollection(Stream<E> stream, Function<E, NE> transformer, Supplier<COL> collectionSupplier) {
        return toCollection(stream, TRUE(), transformer, collectionSupplier);
    }

    /**
     * 데이터(E)를 새로운 데이터 유형(NE)로 변환하여 제공(COL)합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 26.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     *            데이터 유형
     * @param <NE>
     *            새로운 데이터 유형
     * @param <COL>
     *            결과 {@link Collection} 유형.
     * @param stream
     *            데이터 제공 객체
     * @param filter
     *            처리 제외 함수
     * @param transformer
     *            데이터 변환 함수 (E => NE)
     * @param collectionSupplier
     *            결과 {@link Collection} 객체 제공 함수.
     * @return
     *
     * @since 2025. 8. 26.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E, NE, COL extends Collection<NE>> COL toCollection(Stream<E> stream, Predicate<E> filter, Function<E, NE> transformer, Supplier<COL> collectionSupplier) {
        AssertUtils2.notNulls(stream, filter, transformer, collectionSupplier);
        return stream.filter(filter).map(transformer).collect(Collectors.toCollection(collectionSupplier));
    }

    /**
     * {@link Stream} 데이터를 {@link Collection}으로 담아서 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 26.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     *            데이터 유형
     * @param <COL>
     *            결과 {@link Collection} 유형
     * @param stream
     *            데이터 제공 객체
     * @param filter
     *            처리 제외 함수.
     * @param collectionSupplier
     *            결과 {@link Collection} 객체 제공 함수.
     * @return
     *
     * @since 2025. 8. 26.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E, COL extends Collection<E>> COL toCollection(Stream<E> stream, Predicate<E> filter, Supplier<COL> collectionSupplier) {
        return toCollection(stream, filter, ID(), collectionSupplier);
    }

    /**
     * {@link Stream} 데이터를 {@link Collection}으로 담아서 제공합니다. <br>
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
     * @param <COL>
     *            결과 {@link Collection} 유형
     * @param stream
     *            데이터 제공 객체
     * @param collectionSupplier
     *            결과 {@link Collection} 객체 제공 함수.
     * @return
     *
     * @since 2025. 8. 21.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E, COL extends Collection<E>> COL toCollection(Stream<E> stream, Supplier<COL> collectionSupplier) {
        return toCollection(stream, TRUE(), ID(), collectionSupplier);
    }

    /**
     * 전달받은 {@link Collection} 데이터를 처리하여 새로운 {@link Collection} 구현체로 묶어서 제공합니다. <br>
     * 단, <code>keyMapper</code>에 해당하는 값이 동일한 경우 <code>mergeFunction</code>를 통해서 객체를 하나로 병합합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 20.		박준홍			최초 작성
     * </pre>
     *
     * @param <K>
     *            데이터 식별정보
     * @param <V>
     *            데이터 유형
     * @param <COL>
     *            결과 {@link Collection} 유형
     * @param stream
     *            데이터 제공 객체
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
    public static <K, V, COL extends Collection<V>> COL toCollection(Stream<V> stream, Function<V, K> keyMapper, Function<V, V> valueMapper, BinaryOperator<V> mergeFunction,
            Supplier<COL> collectionFactory) {
        return toCollection(stream, TRUE(), keyMapper, valueMapper, mergeFunction, (Supplier<Map<K, V>>) HashMap<K, V>::new, collectionFactory);
    }

    /**
     * 전달받은 {@link Collection} 데이터를 처리하여 새로운 {@link Collection} 구현체로 묶어서 제공합니다. <br>
     * 단, <code>keyMapper</code>에 해당하는 값이 동일한 경우 <code>mergeFunction</code>를 통해서 객체를 하나로 병합합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 21.		박준홍			최초 작성
     * </pre>
     *
     * @param <K>
     *            데이터 식별정보
     * @param <V>
     *            데이터 유형
     * @param <COL>
     *            결과 {@link Collection} 유형
     * @param <M>
     *            내부 데이터 변환 {@link Map} 유형
     * @param stream
     *            데이터 제공 객체
     * @param keyMapper
     *            객체의 식별정보를 제공하는 함수.
     * @param valueMapper
     *            객체의 복제 또는 새로운 객체로 제공하는 함수.
     * @param mergeFunction
     *            2개의 객체 정보를 하나로 병합하는 함수. (V + V => V)
     * @param mapSupplier
     *            {@link Map} 객체 제공 함수
     * @param collectionFactory
     *            {@link Collection} 객체 제공 함수.
     * @return
     *
     * @since 2025. 8. 21.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <K, V, COL extends Collection<V>, M extends Map<K, V>> COL toCollection(Stream<V> stream, Function<V, K> keyMapper, Function<V, V> valueMapper,
            BinaryOperator<V> mergeFunction, Supplier<M> mapSupplier, Supplier<COL> collectionFactory) {
        return toCollection(stream, TRUE(), keyMapper, valueMapper, mergeFunction, mapSupplier, collectionFactory);
    }

    /**
     * 전달받은 {@link Collection} 데이터를 처리하여 새로운 {@link Collection} 구현체로 묶어서 제공합니다. <br>
     * 단, <code>keyMapper</code>에 해당하는 값이 동일한 경우 <code>mergeFunction</code>를 통해서 객체를 하나로 병합합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 26.		박준홍			최초 작성
     * </pre>
     *
     * @param <K>
     *            데이터 식별정보
     * @param <V>
     *            데이터 유형
     * @param <COL>
     *            결과 {@link Collection} 유형
     * @param stream
     *            데이터 제공 객체
     * @param filter
     *            처리 제외 함수.
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
     * @since 2025. 8. 26.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <K, V, COL extends Collection<V>> COL toCollection(Stream<V> stream, Predicate<V> filter, Function<V, K> keyMapper, Function<V, V> valueMapper,
            BinaryOperator<V> mergeFunction, Supplier<COL> collectionFactory) {
        return toCollection(stream, filter, keyMapper, valueMapper, mergeFunction, (Supplier<Map<K, V>>) HashMap<K, V>::new, collectionFactory);
    }

    /**
     * 전달받은 {@link Collection} 데이터를 처리하여 새로운 {@link Collection} 구현체로 묶어서 제공합니다. <br>
     * 단, <code>keyMapper</code>에 해당하는 값이 동일한 경우 <code>mergeFunction</code>를 통해서 객체를 하나로 병합합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 26.		박준홍			최초 작성
     * </pre>
     *
     * @param <K>
     *            데이터 식별정보
     * @param <V>
     *            데이터 유형
     * @param <COL>
     *            결과 {@link Collection} 유형
     * @param <M>
     *            내부 데이터 변환 {@link Map} 유형
     * @param stream
     *            데이터 제공 객체
     * @param filter
     *            처리 제외 함수.
     * @param keyMapper
     *            객체의 식별정보를 제공하는 함수.
     * @param valueMapper
     *            객체의 복제 또는 새로운 객체로 제공하는 함수.
     * @param mergeFunction
     *            2개의 객체 정보를 하나로 병합하는 함수. (V + V => V)
     * @param mapSupplier
     *            {@link Map} 객체 제공 함수
     * @param collectionFactory
     *            {@link Collection} 객체 제공 함수.
     * @return
     *
     * @since 2025. 8. 26.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <K, V, COL extends Collection<V>, M extends Map<K, V>> COL toCollection(Stream<V> stream, Predicate<V> filter, Function<V, K> keyMapper,
            Function<V, V> valueMapper, BinaryOperator<V> mergeFunction, Supplier<M> mapSupplier, Supplier<COL> collectionFactory) {
        AssertUtils2.notNulls(stream, filter, keyMapper, valueMapper, mergeFunction, mapSupplier, collectionFactory);
        return stream//
                .filter(filter) //
                .collect( //
                        Collectors.toMap( //
                                keyMapper //
                                , valueMapper //
                                , mergeFunction //
                                , mapSupplier)) //
                .values().stream() //
                .collect(Collectors.toCollection(collectionFactory));
    }

    /**
     * {@link Stream}에 포함된 데이터를 변형(<code>transformer</code>)하여 {@link List}에 담아 제공합니다. <br>
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
     * @param <LIST>
     *            결과 {@link List} 유형
     * @param stream
     *            데이터 제공 객체
     * @param transformer
     *            새로운 데이터 제공 함수
     * @return
     *
     * @since 2025. 8. 21.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E, NE> List<NE> toList(Stream<E> stream, Function<E, NE> transformer) {
        return toCollection(stream, TRUE(), transformer, (Supplier<List<NE>>) ArrayList<NE>::new);
    }

    /**
     * {@link Stream}에 포함된 데이터를 변형(<code>transformer</code>)하여 {@link List}에 담아 제공합니다. <br>
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
     * @param <LIST>
     *            결과 {@link List} 유형
     * @param stream
     *            데이터 제공 객체
     * @param transformer
     *            새로운 데이터 제공 함수
     * @param listSupplier
     *            결과 {@link List} 객체 제공 함수.
     * @return
     *
     * @since 2025. 8. 21.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E, NE, LIST extends List<NE>> LIST toList(Stream<E> stream, Function<E, NE> transformer, Supplier<LIST> listSupplier) {
        return toCollection(stream, TRUE(), transformer, listSupplier);
    }

    /**
     * {@link Stream}에 포함된 데이터를 변형(<code>transformer</code>)하여 {@link List}에 담아 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 26.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     *            데이터 유형
     * @param <NE>
     *            새로운 데이터 유형
     * @param <LIST>
     *            결과 {@link List} 유형
     * @param stream
     *            데이터 제공 객체
     * @param filter
     *            처리 제외 함수.
     * @param transformer
     *            새로운 데이터 제공 함수
     * @return
     *
     * @since 2025. 8. 26.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E, NE> List<NE> toList(Stream<E> stream, Predicate<E> filter, Function<E, NE> transformer) {
        return toCollection(stream, filter, transformer, (Supplier<List<NE>>) ArrayList<NE>::new);
    }

    /**
     * {@link Stream}에 포함된 데이터를 변형(<code>transformer</code>)하여 {@link List}에 담아 제공합니다. <br>
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
     * @param <LIST>
     *            결과 {@link List} 유형
     * @param stream
     *            데이터 제공 객체
     * @param filter
     *            처리 제외 함수.
     * @param transformer
     *            새로운 데이터 제공 함수
     * @param listSupplier
     *            결과 {@link List} 객체 제공 함수.
     * @return
     *
     * @since 2025. 8. 21.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E, NE, LIST extends List<NE>> LIST toList(Stream<E> stream, Predicate<E> filter, Function<E, NE> transformer, Supplier<LIST> listSupplier) {
        return toCollection(stream, filter, transformer, listSupplier);
    }

    /**
     * {@link Collection} 데이터를 새로운 형태로 변환하여 하나의 {@link Map}로 묶어서 제공합니다. <br>
     * 단, <code>keyMapper</code>에 해당하는 값이 동일한 경우 <code>mergeFunction</code>를 통해서 객체를 하나로 병합 ('V + V => V' => U) 합니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 20.		박준홍			최초 작성
     * </pre>
     *
     * @param <K>
     *            데이터 식별정보
     * @param <V>
     *            데이터 유형
     * @param <U>
     *            새로운 데이터 유형
     * @param <M>
     *            결과 {@link Map} 유형
     * @param stream
     *            데이터 제공 객체
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
    public static <K, V, U, M extends Map<K, U>> M toMap(Stream<V> stream, Function<V, K> keyMapper, BinaryOperator<V> mergeFunction, Function<V, U> transformer,
            Supplier<M> mapSupplier) {
        return toMap(stream, TRUE(), keyMapper, mergeFunction, transformer, mapSupplier, (Supplier<Map<K, V>>) HashMap<K, V>::new);
    }

    /**
     * {@link Collection} 데이터를 새로운 형태로 변환하여 하나의 {@link Map}로 묶어서 제공합니다. <br>
     * 단, <code>keyMapper</code>에 해당하는 값이 동일한 경우 <code>mergeFunction</code>를 통해서 객체를 하나로 병합 ('V + V => V' => U) 합니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 21.		박준홍			최초 작성
     * </pre>
     *
     * @param <K>
     *            데이터 식별정보
     * @param <V>
     *            데이터 유형
     * @param <U>
     *            새로운 데이터 유형
     * @param <M>
     *            결과 {@link Map} 유형
     * @param stream
     *            데이터 제공 객체
     * @param keyMapper
     *            객체의 식별정보를 제공하는 함수.
     * @param mergeFunction
     *            2개의 객체 정보를 하나로 병합하는 함수. (V + V => V)
     * @param transformer
     *            새로운 객체를 제공하는 함수. (V => U)
     * @param mapSupplier
     *            최종 결과 {@link Map} 객체를 제공하는 함수.
     * @param mergeMapSupplier
     *            데이터를 동일한 식별정보(<code>keyMapper</code>)로 병합할 때 사용하는 내부처리용 {@link Map} 객체를 제공하는 함수.
     * @return
     *
     * @since 2025. 8. 21.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <K, V, U, M extends Map<K, U>> M toMap(Stream<V> stream, Function<V, K> keyMapper, BinaryOperator<V> mergeFunction, Function<V, U> transformer,
            Supplier<M> mapSupplier, Supplier<? extends Map<K, V>> mergeMapSupplier) {
        return toMap(stream, TRUE(), keyMapper, mergeFunction, transformer, mapSupplier, mergeMapSupplier);
    }

    /**
     * {@link Stream} 데이터(V)를 동일한 식별정보(K)를 갖는 데이터끼리 묶은 후, 새로운 형태(U)로 변환(<code>V => U by 'valueFunction'</code>)하여
     * {@link Map}으로 제공합니다. <br>
     * 단, <code>keyMapper</code>에 해당하는 값이 동일한 경우 <code>listSupplier</code>를 통해서 제공되는 {@link List} 객체에 추가됩니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 21.		박준홍			최초 작성
     * </pre>
     *
     * @param <K>
     *            데이터 식별정보
     * @param <V>
     *            데이터 유형
     * @param <U>
     *            새로운 데이터 유형
     * @param stream
     *            데이터 제공 객체
     * @param keyMapper
     *            객체의 식별정보를 제공하는 함수.
     * @param valueFunction
     *            새로운 객체를 제공하는 함수. (V => U)
     * @return
     *
     * @since 2025. 8. 21.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <K, V, U> Map<K, List<U>> toMap(Stream<V> stream, Function<V, K> keyMapper, Function<V, U> valueFunction) {
        return toMap(stream, TRUE(), keyMapper, valueFunction, (Supplier<HashMap<K, List<U>>>) HashMap<K, List<U>>::new, (Supplier<List<U>>) ArrayList<U>::new);
    }

    /**
     * {@link Stream} 데이터를 새로운 형태로 변환하여 하나의 {@link Map}로 묶어서 제공합니다. <br>
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
     *            데이터 식별정보
     * @param <V>
     *            데이터 유형
     * @param <U>
     *            새로운 데이터 유형
     * @param <M>
     *            결과 {@link Map} 유형
     * @param stream
     *            데이터 제공 객체
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
    public static <K, V, U, M extends Map<K, U>> M toMap(Stream<V> stream, Function<V, K> keyMapper, Function<V, U> valueFunction, BinaryOperator<U> mergeFunction,
            Supplier<M> mapSupplier) {
        return toMap(stream, TRUE(), keyMapper, valueFunction, mergeFunction, mapSupplier);
    }

    /**
     * {@link Stream} 데이터(V)를 동일한 식별정보(K)를 갖는 데이터끼리 묶은 후, 새로운 형태(U)로 변환(<code>V => U by 'valueFunction'</code>)하여
     * {@link Map}으로 제공합니다. <br>
     * 단, <code>keyMapper</code>에 해당하는 값이 동일한 경우 <code>listSupplier</code>를 통해서 제공되는 {@link List} 객체에 추가됩니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 21.     박준홍         최초 작성
     * </pre>
     *
     * @param <K>
     *            데이터 식별정보
     * @param <V>
     *            데이터 유형
     * @param <U>
     *            새로운 데이터 유형
     * @param <M>
     *            결과 {@link Map} 유형
     * @param stream
     *            데이터 제공 객체
     * @param keyMapper
     *            객체의 식별정보를 제공하는 함수.
     * @param valueFunction
     *            새로운 객체를 제공하는 함수. (V => U)
     * @param mapSupplier
     *            {@link Map} 객체를 제공하는 함수.
     * @return
     *
     * @since 2025. 8. 21.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <K, V, U, M extends Map<K, List<U>>> M toMap(Stream<V> stream, Function<V, K> keyMapper, Function<V, U> valueFunction, Supplier<M> mapSupplier) {
        return toMap(stream, keyMapper, valueFunction, mapSupplier, (Supplier<List<U>>) ArrayList<U>::new);
    }

    /**
     * {@link Stream} 데이터(V)를 동일한 식별정보(K)를 갖는 데이터끼리 묶은 후, 새로운 형태(U)로 변환(<code>V => U by 'valueFunction'</code>)하여
     * {@link Map}으로 제공합니다. <br>
     * 단, <code>keyMapper</code>에 해당하는 값이 동일한 경우 <code>listSupplier</code>를 통해서 제공되는 {@link List} 객체에 추가됩니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 21.		박준홍			최초 작성
     * </pre>
     *
     * @param <K>
     *            데이터 식별정보
     * @param <V>
     *            데이터 유형
     * @param <U>
     *            새로운 데이터 유형
     * @param <COL>
     *            동일한 식별정보에 해당하는 데이터를 담는 {@link Collection} 유형
     * @param <M>
     *            결과 {@link Map} 유형
     * @param stream
     *            데이터 제공 객체
     * @param keyMapper
     *            객체의 식별정보를 제공하는 함수.
     * @param valueFunction
     *            새로운 객체를 제공하는 함수. (V => U)
     * @param mapSupplier
     *            {@link Map} 객체를 제공하는 함수.
     * @param collectionSupplier
     *            {@link Collection} 객체를 제공하는 함수.
     * @return
     *
     * @since 2025. 8. 21.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <K, V, U, COL extends Collection<U>, M extends Map<K, COL>> M toMap(Stream<V> stream, Function<V, K> keyMapper, Function<V, U> valueFunction,
            Supplier<M> mapSupplier, Supplier<COL> collectionSupplier) {
        return toMap(stream, TRUE(), keyMapper, valueFunction, mapSupplier, collectionSupplier);
    }

    /**
     * {@link Collection} 데이터를 새로운 형태로 변환하여 하나의 {@link Map}로 묶어서 제공합니다. <br>
     * 단, <code>keyMapper</code>에 해당하는 값이 동일한 경우 <code>mergeFunction</code>를 통해서 객체를 하나로 병합 ('V + V => V' => U) 합니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 20.		박준홍			최초 작성
     * </pre>
     *
     * @param <K>
     *            데이터 식별정보
     * @param <V>
     *            데이터 유형
     * @param <U>
     *            새로운 데이터 유형
     * @param <M>
     *            결과 {@link Map} 유형
     * @param stream
     *            데이터 제공 객체
     * @param filter
     *            처리 제외 함수.
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
    public static <K, V, U, M extends Map<K, U>> M toMap(Stream<V> stream, Predicate<V> filter, Function<V, K> keyMapper, BinaryOperator<V> mergeFunction,
            Function<V, U> transformer, Supplier<M> mapSupplier) {
        return toMap(stream, filter, keyMapper, mergeFunction, transformer, mapSupplier, (Supplier<Map<K, V>>) HashMap<K, V>::new);
    }

    /**
     * {@link Collection} 데이터를 새로운 형태로 변환하여 하나의 {@link Map}로 묶어서 제공합니다. <br>
     * 단, <code>keyMapper</code>에 해당하는 값이 동일한 경우 <code>mergeFunction</code>를 통해서 객체를 하나로 병합 ('V + V => V' => U) 합니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 26.		박준홍			최초 작성
     * </pre>
     *
     * @param <K>
     *            데이터 식별정보
     * @param <V>
     *            데이터 유형
     * @param <U>
     *            새로운 데이터 유형
     * @param <M>
     *            결과 {@link Map} 유형
     * @param stream
     *            데이터 제공 객체
     * @param filter
     *            처리 제외 함수.
     * @param keyMapper
     *            객체의 식별정보를 제공하는 함수.
     * @param mergeFunction
     *            2개의 객체 정보를 하나로 병합하는 함수. (V + V => V)
     * @param transformer
     *            새로운 객체를 제공하는 함수. (V => U)
     * @param mapSupplier
     *            최종 결과 {@link Map} 객체를 제공하는 함수.
     * @param mergeMapSupplier
     *            데이터를 동일한 식별정보(<code>keyMapper</code>)로 병합할 때 사용하는 내부처리용 {@link Map} 객체를 제공하는 함수.
     * @return
     *
     * @since 2025. 8. 26.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <K, V, U, M extends Map<K, U>> M toMap(Stream<V> stream, Predicate<V> filter, Function<V, K> keyMapper, BinaryOperator<V> mergeFunction,
            Function<V, U> transformer, Supplier<M> mapSupplier, Supplier<? extends Map<K, V>> mergeMapSupplier) {
        AssertUtils2.notNulls(transformer, mapSupplier);
        return toMap(stream, filter, keyMapper, ID(), mergeFunction, mergeMapSupplier) //
                .entrySet().stream() //
                .collect(Collectors.toMap( //
                        Map.Entry::getKey //
                        , entry -> Objects.requireNonNull( //
                                transformer.apply(entry.getValue()) // V => U
                                , "transformer returned 'null'") //
                        , (v1, v2) -> v1 // never occure.
                        , mapSupplier //
                ));
    }

    /**
     * {@link Stream} 데이터(V)를 동일한 식별정보(K)를 갖는 데이터끼리 묶은 후, 새로운 형태(U)로 변환(<code>V => U by 'valueFunction'</code>)하여
     * {@link Map}으로 제공합니다. <br>
     * 단, <code>keyMapper</code>에 해당하는 값이 동일한 경우 <code>listSupplier</code>를 통해서 제공되는 {@link List} 객체에 추가됩니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 21.		박준홍			최초 작성
     * </pre>
     *
     * @param <K>
     *            데이터 식별정보
     * @param <V>
     *            데이터 유형
     * @param <U>
     *            새로운 데이터 유형
     * @param stream
     *            데이터 제공 객체
     * @param filter
     *            처리 제외 함수.
     * @param keyMapper
     *            객체의 식별정보를 제공하는 함수.
     * @param valueFunction
     *            새로운 객체를 제공하는 함수. (V => U)
     * @return
     *
     * @since 2025. 8. 21.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <K, V, U> Map<K, List<U>> toMap(Stream<V> stream, Predicate<V> filter, Function<V, K> keyMapper, Function<V, U> valueFunction) {
        return toMap(stream, filter, keyMapper, valueFunction, (Supplier<HashMap<K, List<U>>>) HashMap<K, List<U>>::new, (Supplier<List<U>>) ArrayList<U>::new);
    }

    /**
     * {@link Stream} 데이터를 새로운 형태로 변환하여 하나의 {@link Map}로 묶어서 제공합니다. <br>
     * 단, <code>keyMapper</code>에 해당하는 값이 동일한 경우 <code>mergeFunction</code>를 통해서 객체를 하나로 병합 ( 'V => U' + U => U) 합니다.
     * <br>
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 26.     박준홍         최초 작성
     * </pre>
     *
     * @param <K>
     *            데이터 식별정보
     * @param <V>
     *            데이터 유형
     * @param <U>
     *            새로운 데이터 유형
     * @param <M>
     *            결과 {@link Map} 유형
     * @param stream
     *            데이터 제공 객체
     * @param filter
     *            처리 제외 함수.
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
     * @since 2025. 8. 26.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <K, V, U, M extends Map<K, U>> M toMap(Stream<V> stream, Predicate<V> filter, Function<V, K> keyMapper, Function<V, U> valueFunction,
            BinaryOperator<U> mergeFunction, Supplier<M> mapSupplier) {
        AssertUtils2.notNulls(stream, filter, keyMapper, valueFunction, mergeFunction, mapSupplier);
        return stream//
                .filter(filter) //
                .collect( //
                        Collectors.toMap( //
                                v -> Objects.requireNonNull( //
                                        keyMapper.apply(v) // V -@-> K
                                        , "keyMapper returned 'null'") //
                                , v -> Objects.requireNonNull( //
                                        valueFunction.apply(v) // V => U
                                        , "valueFunction returned 'null'") //
                                , (a, b) -> Objects.requireNonNull( //
                                        mergeFunction.apply(a, b) // U + U => U
                                        , "mergeFunction returned 'null'") //
                                , mapSupplier) //
                );
    }

    /**
     * {@link Stream} 데이터(V)를 동일한 식별정보(K)를 갖는 데이터끼리 묶은 후, 새로운 형태(U)로 변환(<code>V => U by 'valueFunction'</code>)하여
     * {@link Map}으로 제공합니다. <br>
     * 단, <code>keyMapper</code>에 해당하는 값이 동일한 경우 <code>listSupplier</code>를 통해서 제공되는 {@link List} 객체에 추가됩니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 26.		박준홍			최초 작성
     * </pre>
     *
     * @param <K>
     *            데이터 식별정보
     * @param <V>
     *            데이터 유형
     * @param <U>
     *            새로운 데이터 유형
     * @param <COL>
     *            동일한 식별정보에 해당하는 데이터를 담는 {@link Collection} 유형
     * @param <M>
     *            결과 {@link Map} 유형
     * @param stream
     *            데이터 제공 객체
     * @param filter
     *            제외 처리 함수.
     * @param keyMapper
     *            객체의 식별정보를 제공하는 함수.
     * @param valueFunction
     *            새로운 객체를 제공하는 함수. (V => U)
     * @param mapSupplier
     *            {@link Map} 객체를 제공하는 함수.
     * @param collectionSupplier
     *            {@link Collection} 객체를 제공하는 함수.
     * @return
     *
     * @since 2025. 8. 26.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <K, V, U, COL extends Collection<U>, M extends Map<K, COL>> M toMap(Stream<V> stream, Predicate<V> filter, Function<V, K> keyMapper, Function<V, U> valueFunction,
            Supplier<M> mapSupplier, Supplier<COL> collectionSupplier) {
        AssertUtils2.notNulls(stream, keyMapper, valueFunction, mapSupplier, collectionSupplier);
        return stream //
                .filter(filter) //
                .collect( //
                        Collectors.groupingBy( //
                                keyMapper //
                                , mapSupplier //
                                , Collectors.mapping(valueFunction, Collectors.toCollection(collectionSupplier)) //
                        ) //
                );
    }

    /**
     * {@link Stream}에 포함된 데이터를 변형(<code>transformer</code>)하여 {@link Set}에 담아 제공합니다. <br>
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
     * @param <SET>
     *            결과 {@link Set} 유형
     * @param stream
     *            데이터 제공 객체
     * @param transformer
     *            새로운 데이터 제공 함수
     * @return
     *
     * @since 2025. 8. 21.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E, NE> Set<NE> toSet(Stream<E> stream, Function<E, NE> transformer) {
        return toCollection(stream, TRUE(), transformer, (Supplier<Set<NE>>) HashSet<NE>::new);
    }

    /**
     * {@link Stream}에 포함된 데이터를 변형(<code>transformer</code>)하여 {@link Set}에 담아 제공합니다. <br>
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
     * @param <SET>
     *            결과 {@link Set} 유형
     * @param stream
     *            데이터 제공 객체
     * @param transformer
     *            새로운 데이터 제공 함수
     * @param setSupplier
     *            결과 {@link Set} 객체 제공 함수.
     * @return
     *
     * @since 2025. 8. 21.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E, NE, SET extends Set<NE>> SET toSet(Stream<E> stream, Function<E, NE> transformer, Supplier<SET> setSupplier) {
        return toCollection(stream, TRUE(), transformer, setSupplier);
    }

    /**
     * {@link Stream}에 포함된 데이터를 변형(<code>transformer</code>)하여 {@link Set}에 담아 제공합니다. <br>
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
     * @param <SET>
     *            결과 {@link Set} 유형
     * @param stream
     *            데이터 제공 객체
     * @param filter
     *            처리 제외 함수.
     * @param transformer
     *            새로운 데이터 제공 함수
     * @return
     *
     * @since 2025. 8. 21.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E, NE> Set<NE> toSet(Stream<E> stream, Predicate<E> filter, Function<E, NE> transformer) {
        return toCollection(stream, filter, transformer, (Supplier<Set<NE>>) HashSet<NE>::new);
    }

    /**
     * {@link Stream}에 포함된 데이터를 변형(<code>transformer</code>)하여 {@link Set}에 담아 제공합니다. <br>
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
     * @param <SET>
     *            결과 {@link Set} 유형
     * @param stream
     *            데이터 제공 객체
     * @param filter
     *            처리 제외 함수.
     * @param transformer
     *            새로운 데이터 제공 함수
     * @param setSupplier
     *            결과 {@link Set} 객체 제공 함수.
     * @return
     *
     * @since 2025. 8. 21.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E, NE, SET extends Set<NE>> SET toSet(Stream<E> stream, Predicate<E> filter, Function<E, NE> transformer, Supplier<SET> setSupplier) {
        return toCollection(stream, filter, transformer, setSupplier);
    }

    static final <T> Predicate<T> TRUE() {
        return o -> true;
    }
}
