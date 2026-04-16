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
 * Date  : 2026. 4. 16. 오전 10:50:04
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package open.commons.core.function;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.jspecify.annotations.Nullable;

/**
 * {@link Consumer}, {@link BiConsumer} 관련 기능을 제공합니다.
 * 
 * @since 2026. 4. 16.
 * @version 3.0.0
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
public class Consumers {
    // prevent to create an instance.
    private Consumers() {
    }

    /**
     * {@link BiConsumer} 관련 기능을 제공합니다.
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
         * 아무 것도 하지 않는 {@link BiConsumer}를 제공합니다.O <br>
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
        public static <T extends @Nullable Object, U extends @Nullable Object> BiConsumer<T, U> doNothing() {
            return (_, _) -> {
            };
        }
    }

    /**
     * {@link Consumer} 관련 기능을 제공합니다.
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
         * 아무 것도 하지 않는 {@link Consumer}를 제공합니다. <br>
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
        public static <T extends @Nullable Object> Consumer<T> doNothing() {
            return _ -> {
            };
        }
    }
}
