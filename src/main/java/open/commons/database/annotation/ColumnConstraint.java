/*
 * Copyright 2020 Park Jun-Hong_(parkjunhong77/google/com)
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
 * Date  : 2020. 11. 9. 오후 1:29:32
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package open.commons.database.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 컬럼의 제한조건을 설정하는 어노테이션.
 * 
 * @since 2020. 11. 9.
 * @version
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
@Retention(RUNTIME)
@Target({ FIELD, METHOD })
public @interface ColumnConstraint {
    /** 데이터의 길이 검증 여부 */
    boolean hasLength() default true;

    /** 컬럼 순서 */
    int index();

    /** 데이터 길이 */
    int length() default -1;

    /** 데이터의 null 허용 여부 */
    boolean nullable() default true;

    /** Java 정규식에 기반한 데이터 패턴 */
    String pattern() default "";
}
