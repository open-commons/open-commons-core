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
 * Date  : 2025. 6. 24. 오후 1:15:28
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package open.commons.core.lang;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

import org.jspecify.annotations.Nullable;

/**
 * {@link ThreadLocal}를 보다 편하게 사용하기 위한 클래스.<br>
 * 이 클래스의 객체가 생성될 때마다 내부적으로 새로운 {@link ThreadLocal} 객체를 사용하기 때문에<br>
 * 동일한 영역에서는 {@code singleton} 형태로 사용하는 것을 추천합니다.
 * 
 * @since 2025. 6. 24.
 * @version 2.1.0
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */
public class ThreadLocalContext implements IThreadLocalContext {

    private final ThreadLocal<Map<Object, Object>> context;

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 24.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param context
     *            {@link ThreadLocal} 객체. ({@code NOT NULL})
     * @throws NullPointerException
     *             파라미터({@code context})가 {@code null}인 경우 발생.
     * 
     * @since 2025. 6. 24.
     * @version 2.1.0
     * 
     */
    public ThreadLocalContext(ThreadLocal<Map<Object, Object>> context) {
        Objects.requireNonNull(context);

        this.context = context;
    }

    /**
     *
     * @since 2025. 6. 24.
     * @version 2.1.0
     *
     * @see open.commons.core.lang.IThreadLocalContext#clear()
     */
    @Override
    public void clear() {
        this.context.remove();
    }

    /**
     * @throws NullPointerException
     *             파라미터({@code key})가 {@code null}인 경우 발생.
     * 
     * @since 2025. 6. 24.
     * @version 2.1.0
     *
     * @see open.commons.core.lang.IThreadLocalContext#contains(java.lang.String)
     */
    @Override
    public boolean contains(Object key) {
        Objects.requireNonNull(key);

        return get0().containsKey(key);
    }

    /**
     *
     * @since 2025. 6. 24.
     * @version 2.1.0
     * 
     *
     * @see open.commons.core.lang.IThreadLocalContext#context()
     */
    // 아래 내용에 적용됨.
    // - Collections.unmodifiableMap(get0())
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    @Override
    public Map<Object, Object> context() {
        return Collections.unmodifiableMap(get0());
    }

    /**
     *
     * @since 2025. 6. 24.
     * @version 2.1.0
     * 
     *
     * @see open.commons.core.lang.IThreadLocalContext#get(java.lang.Object)
     */
    @Override
    public @Nullable Object get(Object key) {
        return get0().get(key);
    }

    // 아래 내용에 적용됨.
    // - this.context.get()
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    private Map<Object, Object> get0() {
        return this.context.get();
    }

    /**
     *
     * @since 2025. 6. 24.
     * @version 2.1.0
     * 
     *
     * @see open.commons.core.lang.IThreadLocalContext#remove(java.lang.Object)
     */
    @Override
    public @Nullable Object remove(Object key) {
        return get0().remove(key);
    }

    /**
     * @throws NullPointerException
     *             파라미터({@code key})가 {@code null}인 경우 발생.
     * 
     * @since 2025. 6. 24.
     * @version 2.1.0
     * 
     *
     * @see open.commons.core.lang.IThreadLocalContext#set(java.lang.Object, java.lang.Object)
     */
    @Override
    public void set(Object key, Object value) {
        Objects.requireNonNull(key);

        get0().put(key, value);
    }

}
