/*
 * Copyright 2019 Park Jun-Hong (parkjunhong77@gmail.com)
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
 * Date  : 2019. 6. 20. 오후 6:06:22
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.core.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

/**
 * 데이터를 설정하는 대상을 기술.<br>
 * 생성되는 객체의 메소드에 설정합니다.
 * 
 * @since 2019. 6. 20.
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 * @see Getter
 */
@Documented
@Retention(RUNTIME)
@Target({ FIELD, METHOD })
public @interface Setter {

    /**
     * 제공받는 데이터가 '배열'이나 '컬렉션'처럼 여러 개이 데이터를 소유하는 데이터 유형인 경우, 내부 데이터까지 재귀적으로 변환을 할지 여부. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 5.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2025. 9. 5.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    boolean deepConvert() default true;

    /** 데이터 이름 */
    String name() default "";

    /**
     * 데이터 타입
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 202. 6. 20.      parkjunohng77@gmail.com     최초 작성
     * 2022. 11. 3.     parkjunohng77@gmail.com    기본값을 {@link Class}.class 로 변경하고 이에 대한 방어 코드로 {@link Method}의 Return Type을 통해서 직접 정보를 획득.
     * </pre>
     * 
     * @deprecated (since 2022/11/03) 사용하지 않음. 기본값을 {@link Class}.class 로 변경하고 이에 대한 방어 코드를 적용함.
     */
    Class<?> type() default Class.class;

}
