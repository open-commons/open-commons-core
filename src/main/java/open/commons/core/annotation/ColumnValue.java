/*
 * Copyright 2020 Park Jun-Hong (parkjunhong77@gmail.com)
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
 * Date  : 2020. 1. 22. 오후 3:27:03
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.core.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.sql.PreparedStatement;

import open.commons.core.annotation.ColumnDef.ColumnNameType;

/**
 * Repository DTO 객체가 값을 제공해주는 메소드에 적용.<br>
 * 해당 메소드를 이용하여 @{link PreparedStatement} 에 파라미터를 설정합니다.
 * 
 * @since 2020. 1. 22.
 * @version 1.6.17
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 * @see PreparedStatement
 * @see PreparedStatement#setObject(int, Object)
 * 
 */
@Documented
@Retention(RUNTIME)
@Target({ METHOD, PARAMETER })
public @interface ColumnValue {

    /**
     * 컬럼명의 대소문자를 구분하는지 여부 <br>
     * 
     * 기본값: false
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 1. 22.      parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2020. 1. 22.
     * @version 1.6.17
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    boolean caseSensitive() default false;

    /**
     * 컬럼명을 변환하는 방식.<br>
     * 프로그래밍 언어와 DBMS 간 명명규칙이 서로 다르기 때문에 일치화 시키기 위한 정의.
     * <ul>
     * <li>{@link ColumnNameType#CAMEL_CASE}
     * <li>{@link ColumnNameType#NAME}
     * <li>{@link ColumnNameType#PASCAL_CASE}
     * <li>{@link ColumnNameType#SNAKE_CASE}
     * </ul>
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 9. 24.     parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2020. 1. 16.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @see ColumnDef#columnNameType()
     */
    ColumnNameType columnNameType() default ColumnNameType.NAME;

    /**
     * 컬럼의 데이터 유형. <br>
     * <span style="color:red; font-weight:bold;">목적: H2 DBMS 의 '? AS {column}' 구문해석 오류에 대응하기 위함.</span>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 4. 9.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2025. 4. 9.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    String columnType() default "";

    /**
     * 컬럼 정의시 'DEFAULT' 설정에 따라서 생성되는 데이터 여부 <br>
     * 이 값이 <code>true</code>인 경우, 쿼리 생성에 포함되지 않습니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 11. 1.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2022. 11. 1.
     * @version 2.0.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    boolean defaultColumn() default false;

    /**
     * 컬럼명을 제공합니다.<br>
     * 이 값이 빈문자열("")인 경우 해당 메소드 이름과 반환 타입을 이용하여 컬럼명을 추출합니다.<br>
     * 먼저 반환타입이 boolean/Boolean 인 경우 메소드이름이 알파벳 소문자 'is' 또는 'get'으로 시작하는지 확인하고<br>
     * 맞다면 'is' 또는 'get'을 제거하고 camelCase로 변경해서 처리하고, 그렇지 않으면 {@link IllegalArgumentException} 를 발생시킨다.<br>
     * 반환타입이 그 외인 경우 알파벳 소문자 메소드 이름이 알파벳 소문자 'get' 으로 시작는지 확인하고<br>
     * 맞다면 'get'을 제거하고 camelCase로 변경해서 처리하고, 그렇지 않으면 {@link IllegalArgumentException} 를 발생시킨다.<br>
     * 
     * <pre>
     * // 'isGood' -> 'good' 으로 처리
     * &#64;ColumnValue(caseSensitive=true)
     * public boolean isGood(){
     *  return ...
     * }
     * 
     * // 'getGood' -> 'good' 으로 처리
     * &#64;ColumnValue(caseSensitive=true)
     * public Boolean getGood(){
     *  return ...
     * }
     * 
     * // 'good' -> IllegalAccessException 발생
     * &#64;ColumnValue(caseSensitive=true)
     * public boolean good(){
     *  return ...
     * }
     * 
     * </pre>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 1. 22.      parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2020. 1. 22.
     * @version 1.6.17
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    String name() default "";

    /**
     * 컬럼의 순서정보를 제공합니다.<br>
     * 사용하고자 하는 컬럼정보가 없는 경우 이 데이터의 값을 기준으로 오름차순 정렬을 적용합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 1. 22.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2020. 1. 22.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    int order() default Integer.MAX_VALUE;

    /**
     * Primary Key에 속하는지 여부. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 11. 1.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2022. 11. 1.
     * @version 2.0.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    boolean primaryKey() default false;

    /**
     * 해당 컬럼이 갱신가능한지 여부를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 29.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2021. 11. 29.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    boolean updatable() default false;

    /**
     * JDBC Variable Binding 에 사용될 문자열을 제공합니다. <br>
     * 일반적으로 물음표(?)를 사용하지만, 연동하는 DBMS에서 제공하는 함수나 프로시저와 같은 정보를 사용하는 경우 지원하기 위합입니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 12. 1.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2021. 12. 1.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    String variableBinding() default "?";
}
