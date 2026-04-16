/*
 * Copyright 2026 Park Jun-Hong (parkjunhong77@gmail.com)
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
 * Date  : 2026. 4. 16. 오전 10:57:46
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package open.commons.core.function;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;

import org.jspecify.annotations.Nullable;

/**
 * {@link Function}, {@link BiFunction} 관련 기능을 제공합니다.
 * 
 * @since 2026. 4. 16.
 * @version 3.0.0
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
public class Functions {
    // prevent to create an instance.
    private Functions() {
    }

    /**
     * {@link BiFunction} 관련 기능을 제공합니다.
     * 
     * @since 2026. 4. 16.
     * @version 3.0.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static class Binary {
        // prevent to create an instance.
        private Binary() {
        }

        /**
         * 무조건 '첫번째' 데이터를 반환합니다.
         * 
         * <pre>
         * [개정이력]
         *      날짜        | 작성자    |    내용
         * ------------------------------------------
         * 2026. 4. 16.     parkjunhong77@gmail.com     최초 작성
         * </pre>
         *
         * @param <T>
         * @param <U>
         * @return
         *
         * @since 2026. 4. 16.
         * @version 3.0.0
         */
        public static <T extends @Nullable Object> BinaryOperator<T> first() {
            return (a, _) -> a;
        }

        /**
         * 무조건 '두번째' 데이터를 반환합니다.
         * 
         * <pre>
         * [개정이력]
         *      날짜        | 작성자    |    내용
         * ------------------------------------------
         * 2026. 4. 16.     parkjunhong77@gmail.com     최초 작성
         * </pre>
         *
         * @param <T>
         * @param <U>
         * @return
         *
         * @since 2026. 4. 16.
         * @version 3.0.0
         */
        public static <T extends @Nullable Object> BinaryOperator<T> last() {
            return (_, b) -> b;
        }
    }

    /**
     * {@link Function} 관련 기능을 제공합니다.
     * 
     * @since 2026. 4. 16.
     * @version 3.0.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static class Unary {
        // prevent to create an instance.
        private Unary() {
        }

        /**
         * 아무 것도 하지 않는 {@link Function}를 제공합니다. <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜        | 작성자    |    내용
         * ------------------------------------------
         * 2026. 4. 16.     parkjunhong77@gmail.com     최초 작성
         * </pre>
         *
         * @param <T>
         * @return
         *
         * @since 2026. 4. 16.
         * @version 3.0.0
         */
        public static <T extends @Nullable Object> Function<T, T> identity() {
            return o -> o;
        }
    }
}
