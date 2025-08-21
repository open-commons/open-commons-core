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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
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
    private static <T> BinaryOperator<T> throwingMerger() {
        return (u, v) -> {
            throw new IllegalStateException(String.format("Duplicate key %s", u));
        };
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
     * @param <V>
     * @param <COL>
     * @param stream
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
        AssertUtils2.notNulls(stream, keyMapper, valueMapper, mergeFunction, collectionFactory);
        return stream.collect( //
                Collectors.toMap( //
                        keyMapper //
                        , valueMapper //
                        , mergeFunction //
                        , HashMap<K, V>::new)) //
                .values().stream() //
                .collect(Collectors.toCollection(collectionFactory));
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
     * @param <V>
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
    public static <K, V, U, M extends Map<K, U>> M toMap(Stream<V> stream, Function<V, K> keyMapper, BinaryOperator<V> mergeFunction, Function<V, U> transformer,
            Supplier<M> mapSupplier) {
        return toMap(stream, keyMapper, Function.identity(), mergeFunction, HashMap<K, V>::new) //
                .entrySet().stream() //
                .collect(Collectors.toMap(Map.Entry::getKey //
                        , entry -> transformer.apply(entry.getValue()) // V => U
                        , throwingMerger() //
                        , mapSupplier //
                ));
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
     * @param <V>
     * @param <U>
     * @param <M>
     * @param stream
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
        return stream.collect( //
                Collectors.toMap( //
                        keyMapper //
                        , valueFunction //
                        , mergeFunction //
                        , mapSupplier) //
        );
    }

}
