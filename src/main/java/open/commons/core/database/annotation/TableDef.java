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
* This file is generated under this project, "open-commons-core".
*
* Date  : 2013. 7. 12. 오후 5:02:47
*
* Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
* 
*/

/**
 * 
 */
package open.commons.core.database.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import open.commons.core.annotation.ColumnValue;

/**
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface TableDef {
    /**
     * 컬럼 정렬 여부. <br>
     * 설정값이 <code>true</code>인 경우 {@link ColumnValue#order()}을 이용하여 컬럼을 정렬합니다.<br>
     * <code>false</code>인 경우 {@link ColumnValue#name()}의 값을 오름차순 정렬을 합니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 1. 7.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2022. 1. 7.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see ColumnValue
     */
    boolean sortedColumns() default false;

    /**
     * 테이블 이름<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2013. 7. 12.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2013. 7. 12.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    String table();
}
