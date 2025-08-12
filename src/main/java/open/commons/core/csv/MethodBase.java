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

/**
 * 
 * @since 2021. 6. 18.
 * @version 1.8.0
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
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
     * 2021. 6. 18.     박준홍         최초 작성
     * </pre>
     *
     * @param owner
     * @param method
     * @since 2021. 6. 18.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public MethodBase(Object owner, Method method) {
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
     * 2021. 6. 18.     박준홍         최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2021. 6. 18.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see java.util.function.Supplier#get()
     */
    @Override
    public String get() {
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
     * @version _._._
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     *
     * @see java.lang.Object#toString()
     */
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
