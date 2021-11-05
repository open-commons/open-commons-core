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
 * Date  : 2017. 9. 28. 오후 1:57:38
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.function;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * 
 * Represents an operation that accepts three input arguments and returns no result. This is the two-arity
 * specialization of {@link Consumer}. Unlike most other functional interfaces, {@code BiConsumer} is expected to
 * operate via side-effects.
 *
 * <p>
 * This is a <a href="package-summary.html">functional interface</a> whose functional method is
 * {@link #accept(Object, Object)}.
 *
 * @param <T>
 *            the type of the first argument to the operation
 * @param <U>
 *            the type of the second argument to the operation
 * @param <V>
 *            the type of the third argument to the operation
 *
 * @see Consumer
 * @since 1.8
 * 
 * @since 2017. 7. 20.
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
@FunctionalInterface
public interface TripleConsumer<T, U, V> {

    /**
     * Performs this operation on the given arguments.
     *
     * @param t
     *            the first input argument
     * @param u
     *            the second input argument
     * @param u
     *            the thrid input argument
     */
    void accept(T t, U u, V v);

    /**
     * Returns a composed {@code BiConsumer} that performs, in sequence, this operation followed by the {@code after}
     * operation. If performing either operation throws an exception, it is relayed to the caller of the composed
     * operation. If performing this operation throws an exception, the {@code after} operation will not be performed.
     *
     * @param after
     *            the operation to perform after this operation
     * @return a composed {@code BiConsumer} that performs in sequence this operation followed by the {@code after}
     *         operation
     * @throws NullPointerException
     *             if {@code after} is null
     */
    default TripleConsumer<T, U, V> andThen(TripleConsumer<? super T, ? super U, ? super V> after) {
        Objects.requireNonNull(after);

        return (l, r, u) -> {
            accept(l, r, u);
            after.accept(l, r, u);
        };
    }
}
