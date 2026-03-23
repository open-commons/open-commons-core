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
 * Date  : 2025. 6. 24. 오후 1:23:27
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package open.commons.core.lang;

import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

import open.commons.core.concurrent.Mutex;
import open.commons.core.utils.MapUtils;

/**
 * 상황별로 {@link ThreadLocal}에 기반한 컨텍스트 정보를 관리합니다.
 * 
 * @since 2025. 6. 24.
 * @version 2.1.0
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */
public class ThreadLocalContextService {

    private static final ConcurrentHashMap<Object, IThreadLocalContext> CONTEXTS = new ConcurrentHashMap<>();
    private static final Mutex mutex = new Mutex("thread-context-service");

    private ThreadLocalContextService() {
    }

    /**
     * 주어진 상황에 맞는 {@link ThreadLocal} 컨텍스트 정보를 삭제합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 24.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     *
     * @param type
     * 
     * @throws NullPointerException
     *             파라미터({@code type})가 {@code null}인 경우 발생.
     *
     * @since 2025. 6. 24.
     * @version 2.1.0
     * 
     */
    public static void clear(Object type) {
        Objects.requireNonNull(type, "Thread Context 의 식별정보는 null 일 수 없습니다.");

        synchronized (mutex) {
            IThreadLocalContext tc = CONTEXTS.remove(type);
            if (tc != null) {
                tc.clear();
            }
        }
    }

    /**
     * 모든 {@link ThreadLocal} 컨텍스트 정보를 삭제합니다. <br>
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 24.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     *
     *
     * @since 2025. 6. 24.
     * @version 2.1.0
     * 
     */
    public static void clearAll() {
        synchronized (mutex) {
            CONTEXTS.values().forEach(c -> c.clear());
            CONTEXTS.clear();
        }
    }

    /**
     * 주어진 상황에 맞는 {@link ThreadLocal} 컨텍스트 정보를 제공합니다. <br>
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 24.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     *
     * @param type
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code type})가 {@code null}인 경우 발생.
     *
     * @since 2025. 6. 24.
     * @version 2.1.0
     * 
     */
    @SuppressWarnings("null") // apply to 's' as parameter of MapUtils.getOrDefault(...)
    public static IThreadLocalContext context(Object type) {
        Objects.requireNonNull(type, "Thread Context 의 식별정보는 null 일 수 없습니다.");

        synchronized (mutex) {
            Supplier<IThreadLocalContext> s = () -> new ThreadLocalContext(Objects.requireNonNull( //
                    ThreadLocal.withInitial(HashMap::new) //
            ));
            return MapUtils.getOrDefault(CONTEXTS, type, s, true);
        }
    }
}
