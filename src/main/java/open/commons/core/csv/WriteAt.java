/*
 * Copyright 2022 Park Jun-Hong (parkjunhong77@gmail.com)
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
 * Date  : 2022. 3. 17. 오후 3:23:47
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package open.commons.core.csv;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

/**
 * CSV 파일에 쓰여질 데이터 위치를 지정.<br>
 * {@link Method}에 설정함으로써 객체의 데이터를 String[]로 자동으로 만드는데 사용된다.
 * 
 * @since 2022. 3. 17.
 * @version 1.8.0
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface WriteAt {

    /**
     * 쓰여질 데이터 위치. (0부터 시작). <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 3. 17.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    int index();

    /**
     * 데이터가 <code>null</code>인 경우 '빈 문자열' <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 3. 17.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    boolean nullIsEmpty() default true;
}
