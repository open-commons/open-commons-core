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
 * Date  : 2026. 3. 27. 오후 1:58:52
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package open.commons.core.function;

import java.util.function.BinaryOperator;

/**
 * {@link BinaryOperator}의 특별한 경우를 제공하는 클래스.
 * 
 * <ul>
 * <li>무조건 이전 데이터를 제공 ({@link #first()})
 * <li>무조건 이후 데이터를 ㅈ공 ({@link #last()})
 * </ul>
 * 
 * @since 2026. 3. 27.
 * @version 3.0.0
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
public class BinaryOperators<T> {

    private BinaryOperators() {
    }

    /**
     * 무조건 이전 데이터를 제공하는 함수<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2026. 3. 27.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <T>
     * @return
     *
     * @since 2026. 3. 27.
     * @version 3.0.0
     */
    public static <T> BinaryOperator<T> first() {
        return (a, _) -> a;
    }

    /**
     * 무조건 이후 데이터를 제공하는 함수 <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2026. 3. 27.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <T>
     * @return
     *
     * @since 2026. 3. 27.
     * @version 3.0.0
     */
    public static <T> BinaryOperator<T> last() {
        return (_, b) -> b;
    }
}
