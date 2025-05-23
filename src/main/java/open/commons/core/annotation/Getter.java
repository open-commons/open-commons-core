/*
 * Copyright 2019 Park Jun-Hong_(parkjunhong77@gmail.com)
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
 * Date  : 2019. 6. 20. 오후 6:06:16
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
 * 데이터를 제공하는 대상을 기술하며, 데이터를 제공하는 타입에 설정합니다.
 * 
 * @since 2019. 6. 20.
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 * @see Setter
 */
@Documented
@Retention(RUNTIME)
@Target({ FIELD, METHOD })
public @interface Getter {

    /** 데이터 이름 */
    String name() default "";

    /**
     * 데이터 타입 <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 202. 6. 20.      박준홍     최초 작성
     * 2022. 11. 3.		박준홍    기본값을 {@link Class}.class 로 변경하고 이에 대한 방어 코드로 {@link Method}의 Return Type을 통해서 직접 정보를 획득.
     * </pre>
     * 
     * @deprecated (since 2022/11/03) 사용하지 않음. 기본값을 {@link Class}.class 로 변경하고 이에 대한 방어 코드를 적용함.
     */
    Class<?> type() default Class.class;
}
