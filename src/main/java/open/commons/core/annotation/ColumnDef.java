/*
 * Copyright 2011 Park Jun-Hong (parkjunhong77@gmail.com)
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
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.sql.ResultSet;

/**
 * {@link ResultSet}으로부터 데이터를 추출해 객체를 생성하기 위한 어노테이션.
 * 
 * @since 2017. 9. 22.
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2017. 9. 5.
     */
    boolean caseSensitive() default false;

    /**
     * 컬럼명을 변환하는 방식.<br>
     * 프로그래밍 언어와 DBMS 간 명명규칙이 상이하기
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 1. 16.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2020. 1. 16.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @see ColumnNameType
     */
    ColumnNameType columnNameType() default ColumnNameType.NAME;

    /**
     * 컬럼명을 제공합니다.
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2018. 5. 4.
     */
    boolean required() default true;

    /**
     * 컬럼의 데이타 타입을 제공합니다.<br>
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2017. 9. 5.
     */
    Class<?> type() default String.class;

    /**
     * DBMS 컬럼명 표기 타입
     * 
     * @since 2020. 1. 16.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static enum ColumnNameType {
        /**
         * camelCase: 각 단어의 첫문자를 대문자로 표기하고 붙여쓰되, 맨처음 문자는 소문자로 표기함 띄어쓰기 대신 대문자로 단어를 구분하는 표기 방식<br>
         * 예) backgroundColor, typeName, iPhone
         */
        CAMEL_CASE,
        /**
         * kebab-case: 하이픈으로 단어를 연결하는 표기법<br>
         * 예) backgroud-color, type-name, i-phone
         */
        KEBAB_CASE,
        /**
         * 설정된 컬럼명과 동일하게 사용
         * 
         * @see ColumnDef#name()
         */
        NAME,
        /**
         * PascalCase: 첫 단어를 대문자로 시작하는 표기법<br>
         * 예): BackgroundColor, TypeName, PowerPoint
         */
        PASCAL_CASE,
        /**
         * snake_case: 단어를 밑줄문자로 구분하는 표기법<br>
         * 예) backgroud_color, type_name, power_point
         */
        SNAKE_CASE,
        //
        ;
    }
}
