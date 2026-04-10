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
 * Date  : 2021. 6. 18. 오후 5:44:37
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.core.csv;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Supplier;

import org.jspecify.annotations.Nullable;

import open.commons.core.utils.ObjectUtils;

/**
 * 
 * @since 2021. 6. 18.
 * @version 1.8.0
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
public class MethodBase implements Supplier<String> {

    private final Object owner;
    private final Method method;

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
    public MethodBase(Object owner, Method method) {
        ObjectUtils.requireNonNulls(owner, method);

        this.owner = owner;
        this.method = method;
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
            Object val = this.method.invoke(this.owner);
            return val == null ? null : val.toString();
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new RuntimeException("데이터 추출 중 에러가 발생하였습니다.", e);
        }
    }

    /**
     *
     * @since 2023. 10. 26.
     * 
     * @see java.lang.Object#toString()
     */
    // 아래 내용에 적용됨.
    // - StringBuilder.toString()
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("MethodBase [method=");
        builder.append(method);
        builder.append(", owner=");
        builder.append(owner.getClass());
        builder.append("]");

        return builder.toString();
    }
}
