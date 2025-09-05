/*
 * Copyright 2025 Park Jun-Hong (parkjunhong77@gmail.com)
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
 * Date  : 2025. 6. 24. 오후 1:07:46
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package open.commons.core.lang;

import java.util.Collections;
import java.util.Map;
import java.util.function.Supplier;

/**
 * 
 * @since 2025. 6. 24.
 * @version 2.1.0
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
public interface IThreadLocalContext {

    /**
     * 특정 {@link Thread}내에서 사용중인 데이터를 삭제합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 6. 24.     parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     *
     * @since 2025. 6. 24.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public void clear();

    /**
     * 식별정보에 해당하는 데이터가 존재하는지 여부를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 6. 24.     parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param key
     *            데이터 식별정보
     * @return
     *
     * @since 2025. 6. 24.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public boolean contains(Object key);

    /**
     * 식별정보에 해당하는 데이터가 존재하지 않는지 여부를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 6. 24.     parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param key
     *            데이터 식별정보
     * @return
     *
     * @since 2025. 6. 24.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    default boolean containsNot(Object key) {
        return !contains(key);
    }

    /**
     * 현재 {@link Thread}내에서 사용 중인 정보를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 6. 24.     parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2025. 6. 24.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see Collections#unmodifiableMap(Map)
     */
    public Map<Object, Object> context();

    /**
     * 식별정보에 해당하는 데이터를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 6. 24.     parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param <T>
     * @param key
     *            데이터 식별정보
     * @return
     *
     * @since 2025. 6. 24.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public Object get(Object key);

    /**
     * 식별정보에 해당하는 데이터를 반환합니다.<br>
     * 단, {@link #get0()} 에 존재하지 않는 경우 <code>supplier</code> 값을 {@link #CONTEXT}에 추가하고 그 값을 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 6. 24.     parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param <T>
     * @param key
     * @param supplier
     * @return
     *
     * @since 2025. 6. 24.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    default Object getOrCompute(Object key, Supplier<Object> supplier) {
        Object o = get(key);
        if (o == null) {
            o = supplier.get();
            set(key, o);
        }
        return o;
    }

    /**
     * 데이터를 삭제합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 6. 24.     parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param key
     *            데이터 식별정보
     *
     * @since 2025. 6. 24.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public Object remove(Object key);

    /**
     * 새로운 데이터를 설정합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 6. 24.     parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param <T>
     * @param key
     *            데이터 식별정보
     * @param value
     *            데이터
     *
     * @since 2025. 6. 24.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see Map#put(Object, Object)
     */
    public void set(Object key, Object value);

}
