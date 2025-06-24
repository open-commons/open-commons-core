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
 * Date  : 2025. 6. 24. 오전 10:46:27
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package open.commons.core.lang;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * {@link ThreadLocal}를 보다 편하게 사용하기 위한 클래스.
 * 
 * @since 2025. 6. 24.
 * @version 2.1.0
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
public class ThreadLocalContext2 {

    /** 특정 {@link Thread}내에서 사용하는 정보 */
    private static final ThreadLocal<Map<String, Object>> CONTEXT = ThreadLocal.withInitial(HashMap::new);

    protected ThreadLocalContext2() {
    }

    /**
     * 특정 {@link Thread}내에서 사용중인 데이터를 삭제합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 24.		박준홍			최초 작성
     * </pre>
     *
     *
     * @since 2025. 6. 24.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static void clear() {
        CONTEXT.remove();
    }

    /**
     * 식별정보에 해당하는 데이터가 존재하는지 여부를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 24.		박준홍			최초 작성
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
    public static boolean contains(String key) {
        return get0().containsKey(key);
    }

    /**
     * 식별정보에 해당하는 데이터가 존재하지 않는지 여부를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 24.		박준홍			최초 작성
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
    public static boolean containsNot(String key) {
        return !contains(key);
    }

    /**
     * 현재 {@link Thread}내에서 사용 중인 정보를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 24.		박준홍			최초 작성
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
    public static Map<String, Object> context() {
        return Collections.unmodifiableMap(get0());
    }

    /**
     * 식별정보에 해당하는 데이터를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 24.		박준홍			최초 작성
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
    @SuppressWarnings("unchecked")
    public static <T> T get(String key) {
        return (T) get0().get(key);
    }

    private static Map<String, Object> get0() {
        return CONTEXT.get();
    }

    /**
     * 식별정보에 해당하는 데이터를 반환합니다.<br>
     * 단, {@link #get0()} 에 존재하지 않는 경우 <code>supplier</code> 값을 {@link #CONTEXT}에 추가하고 그 값을 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 24.		박준홍			최초 작성
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
    @SuppressWarnings("unchecked")
    public static <T> T getOrCompute(String key, Supplier<T> supplier) {
        Object o = get(key);
        if (o == null) {
            o = supplier.get();
            set(key, o);
        }
        return (T) o;
    }

    /**
     * 데이터를 삭제합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 24.		박준홍			최초 작성
     * </pre>
     *
     * @param key
     *            데이터 식별정보
     *
     * @since 2025. 6. 24.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static void remove(String key) {
        get0().remove(key);
    }

    /**
     * 새로운 데이터를 설정합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 24.		박준홍			최초 작성
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
    public static void set(String key, Object value) {
        get0().put(key, value);
    }
}
