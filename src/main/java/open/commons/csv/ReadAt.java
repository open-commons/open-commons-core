/*
 * Copyright 2021 Park Jun-Hong_(parkjunhong77@gmail.com)
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
 * Date  : 2021. 11. 11. 오후 6:10:15
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package open.commons.csv;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * CSV 파일에서 데이터의 위치를 지정하는 클래스.<br>
 * 
 * 단, 이 어노테이션을 사용하는 메소드는 파라미터가 1개이어야 하며, 반드시 아래 데이터 타이입어야 합니다.
 * <ul>
 * <li>{@link Boolean}
 * <li>{@link Byte}
 * <li>{@link Character}
 * <li>{@link Integer}
 * <li>{@link Long}
 * <li>{@link Float}
 * <li>{@link Double}
 * <li>boolean.class
 * <li>byte.class
 * <li>char.class
 * <li>int.class
 * <li>long.class
 * <li>float.class
 * <li>double.class
 * <li>{@link String}
 * </ul>
 * 
 * @since 2021. 11. 11.
 * @version 1.8.0
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface ReadAt {
    /**
     * 읽을 데이터 index 값. (0 부터 시작). <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 11.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2021. 11. 11.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    int index();

    /**
     * 데이터가 없거나 빈 문자열인 경우 생략할 수 있는지 여부. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 11.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2021. 11. 11.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    boolean skippable() default false;
}
