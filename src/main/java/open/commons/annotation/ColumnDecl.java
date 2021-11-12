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
 * Date  : 2019. 6. 17. 오후 4:56:56
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import open.commons.Result;

/**
 * {@link Result}으로부터 만들어진 객체에서 컬럼 값을 얻기 위한 어노테이션.
 * 
 * @since 2019. 6. 17..
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
@Documented
@Retention(RUNTIME)
@Target(METHOD)
public @interface ColumnDecl {

    /**
     * 컬럼명의 대소문자를 구분하는지 여부 <br>
     * 
     * 기본값: false
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 6. 17.      박준홍         최초 작성
     * </pre>
     *
     * @return
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2019. 6. 17.
     */
    boolean caseSensitive() default false;

    /**
     * 컬럼명을 제공합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 6. 17.      박준홍         최초 작성
     * </pre>
     *
     * @return
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2019. 6. 17.
     */
    @AliasFor("value")
    String column() default "";

    @AliasFor("column")
    String value() default "";

}
