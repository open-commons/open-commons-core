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
 * Date  : 2026. 3. 17. 오전 11:04:54
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package open.commons.core.function;

import java.util.function.Predicate;

/**
 * 
 * @since 2026. 3. 17.
 * @version 3.0.0
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
public class Predicates {

    private Predicates() {
    }

    /**
     * 언제나 <b><i>{@code false}</i></b>를 제공합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2026. 3. 17.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <T>
     * @return
     *
     * @since 2026. 3. 17.
     * @version 3.0.0
     */
    public static <T> Predicate<T> alwaysFalse() {
        return _ -> false;
    }

    /**
     * 언제나 <b><i>{@code true}</i></b>를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2026. 3. 17.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <T>
     * @return
     *
     * @since 2026. 3. 17.
     * @version 3.0.0
     */
    public static <T> Predicate<T> alwaysTrue() {
        return _ -> true;
    }
}
