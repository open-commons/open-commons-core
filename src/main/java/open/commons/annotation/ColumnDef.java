/*
 * Copyright 2011 Park Jun-Hong (parkjunhong77/google/com)
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
 * Date  : 2017. 9. 22. 오후 5:04:50
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package open.commons.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @since 2017. 9. 22.
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.FIELD })
public @interface ColumnDef {

    /**
     * 컬럼명의 대소문자를 구분하는지 여부 <br>
     * 
     * 기본값: false
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2017. 9. 5.      박준홍         최초 작성
     * </pre>
     *
     * @return
     *
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @since 2017. 9. 5.
     */
    boolean caseSensitive() default false;

    /**
     * 컬럼명을 제공한다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2017. 9. 5.      박준홍         최초 작성
     * </pre>
     *
     * @return
     *
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @since 2017. 9. 5.
     */
    String name();

    /**
     * 데이타의 NULL 여부. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 2. 18.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @since 2019. 2. 18. (v1.6.3)
     */
    boolean nullable() default true;

    /**
     * 반드시 있어야하는지 여부.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2018. 5. 4.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @since 2018. 5. 4.
     */
    boolean required() default true;

    /**
     * 컬럼의 데이타 타입을 제공한다.<br>
     * 
     * 기본값: String.class
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2017. 9. 5.      박준홍         최초 작성
     * </pre>
     *
     * @return
     *
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @since 2017. 9. 5.
     */
    Class<?> type() default String.class;
}
