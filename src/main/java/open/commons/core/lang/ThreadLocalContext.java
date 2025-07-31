/*
 * Copyright 2025 Park Jun-Hong_(parkjunhong77@gmail.com)
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

import open.commons.core.utils.AssertUtils2;

/**
 * {@link ThreadLocal}를 보다 편하게 사용하기 위한 클래스.<br>
 * 이 클래스의 객체가 생성될 때마다 내부적으로 새로운 {@link ThreadLocal} 객체를 사용하기 때문에<br>
 * 동일한 영역에서는 <code>singleton</code> 형태로 사용하는 것을 추천합니다.
 * 
 * @since 2025. 6. 24.
 * @version 2.1.0
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
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
     * 2025. 6. 24.		박준홍			최초 작성
     * </pre>
     *
     * @param context
     *            {@link ThreadLocal} 객체. (<code>NOT NULL</code>)
     *
     * @since 2025. 6. 24.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public ThreadLocalContext(ThreadLocal<Map<Object, Object>> context) {
        AssertUtils2.notNull(context);
        this.context = context;
    }

    /**
     *
     * @since 2025. 6. 24.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     *
     * @see open.commons.core.lang.IThreadLocalContext#clear()
     */
    @Override
    public void clear() {
        this.context.remove();
    }

    /**
     *
     * @since 2025. 6. 24.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     *
     * @see open.commons.core.lang.IThreadLocalContext#contains(java.lang.String)
     */
    @Override
    public boolean contains(Object key) {
        return get0().containsKey(key);
    }

    /**
     *
     * @since 2025. 6. 24.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     *
     * @see open.commons.core.lang.IThreadLocalContext#context()
     */
    @Override
    public Map<Object, Object> context() {
        return Collections.unmodifiableMap(get0());
    }

    /**
     *
     * @since 2025. 6. 24.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     *
     * @see open.commons.core.lang.IThreadLocalContext#get(java.lang.Object)
     */
    @Override
    public Object get(Object key) {
        return get0().get(key);
    }

    private Map<Object, Object> get0() {
        return this.context.get();
    }

    /**
     *
     * @since 2025. 6. 24.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     *
     * @see open.commons.core.lang.IThreadLocalContext#remove(java.lang.Object)
     */
    @Override
    public void remove(Object key) {
        get0().remove(key);
    }

    /**
     *
     * @since 2025. 6. 24.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     *
     * @see open.commons.core.lang.IThreadLocalContext#set(java.lang.Object, java.lang.Object)
     */
    @Override
    public void set(Object key, Object value) {
        get0().put(key, value);
    }

}
