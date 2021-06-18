/*
 * Copyright 2020 Park Jun-Hong_(parkjunhong77/google/com)
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
 * Date  : 2020. 1. 30. 오후 7:25:28
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package open.commons.function;

import java.util.Objects;
import java.util.function.Function;

/**
 * Represents a function that accepts two arguments and produces a result. This is the two-arity specialization of
 * {@link Function}.
 *
 * <p>
 * This is a <a href="package-summary.html">functional interface</a> whose functional method is
 * {@link #apply(Object, Object, Object, Object)}.
 * 
 * @since 2020. 1. 30.
 * @version 1.8.0
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
@FunctionalInterface
public interface HexaFunction<T, U, V, W, X, Y, R> {

    /**
     * Returns a composed function that first applies this function to its input, and then applies the {@code after}
     * function to the result. If evaluation of either function throws an exception, it is relayed to the caller of the
     * composed function. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 1. 30.		박준홍			최초 작성
     * </pre>
     *
     * @param <Z>
     *            the type of output of the {@code after} function, and of the composed function
     * @param after
     *            the function to apply after this function is applied
     * @return
     *
     * @since 2020. 1. 30.
     * @version 1.8.0
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    default <Z> HexaFunction<T, U, V, W, X, Y, Z> andThen(Function<? super R, ? extends Z> after) {
        Objects.requireNonNull(after);
        return (T t, U u, V v, W w, X x, Y y) -> after.apply(apply(t, u, v, w, x, y));
    }

    /**
     * Applies this function to the given arguments. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 1. 30.		박준홍			최초 작성
     * </pre>
     *
     * @param t
     * @param u
     * @param v
     * @param w
     * @param x
     * @param y
     * @return
     *
     * @since 2020. 1. 30.
     * @version 1.8.0
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    R apply(T t, U u, V v, W w, X x, Y y);
}
