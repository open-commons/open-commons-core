/*
 * Copyright 2021 Park Jun-Hong (parkjunhong77@gmail.com)
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
 * Date  : 2021. 6. 18. 오후 5:43:45
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.core.csv;

import java.lang.reflect.Field;
import java.util.function.Supplier;

import org.jspecify.annotations.Nullable;

import open.commons.core.utils.AssertUtils2;

/**
 * 
 * @since 2021. 6. 18.
 * @version 1.8.0
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
public class FieldBase implements Supplier<String> {

    private final Object owner;
    private final Field field;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 6. 18.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param owner
     * @param method
     * 
     * @since 2021. 6. 18.
     * @version 1.8.0
     */
    public FieldBase(Object owner, Field field) {
        AssertUtils2.notNulls(owner, field);

        this.owner = owner;
        this.field = field;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 6. 18.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2021. 6. 18.
     * 
     * @see java.util.function.Supplier#get()
     */
    @Override
    public @Nullable String get() {
        try {
            Object val = this.field.get(this.owner);
            return val == null ? null : val.toString();
        } catch (IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException("데이터 추출 중 에러가 발생하였습니다.", e);
        }
    }
}
