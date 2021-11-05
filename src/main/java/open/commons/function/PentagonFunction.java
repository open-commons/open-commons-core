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
 * Date  : 2016. 7. 26. 오전 1:31:58
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
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
 * @param <T>
 *            the type of the first argument to the function
 * @param <U>
 *            the type of the second argument to the function
 * @param <V>
 *            the type of the third argument to the function
 * @param <W>
 *            the type of the fourth argument to the function
 * @param <X>
 *            the type of the fifth argument to the function
 * @param <R>
 *            the type of the result of the function
 *
 * @see Function
 * @since 1.8
 * @since 2016. 7. 26.
 * 
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
@FunctionalInterface
public interface PentagonFunction<T, U, V, W, X, R> {

    /**
     * Returns a composed function that first applies this function to its input, and then applies the {@code after}
     * function to the result. If evaluation of either function throws an exception, it is relayed to the caller of the
     * composed function.
     *
     * @param <X>
     *            the type of output of the {@code after} function, and of the composed function
     * @param after
     *            the function to apply after this function is applied
     * @return a composed function that first applies this function and then applies the {@code after} function
     * @throws NullPointerException
     *             if after is null
     */
    default <Y> PentagonFunction<T, U, V, W, X, Y> andThen(Function<? super R, ? extends Y> after) {
        Objects.requireNonNull(after);
        return (T t, U u, V v, W w, X x) -> after.apply(apply(t, u, v, w, x));
    }

    /**
     * Applies this function to the given arguments.
     *
     * @param t
     *            the first function argument
     * @param u
     *            the second function argument
     * @param v
     *            the third function argument
     * @param w
     *            the fourth function argument
     * @param x
     *            the fifth function argument
     * @return the function result
     */
    R apply(T t, U u, V v, W w, X x);
}