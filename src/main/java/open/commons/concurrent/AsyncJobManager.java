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
 * Date  : 2020. 11. 10. 오후 6:01:08
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package open.commons.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import open.commons.utils.ExceptionUtils;

/**
 * 비동기 작업({@link Future})을 각각의 식별정보를 이용하여 관리하고 제공하는 클래스.
 * 
 * @param <K>
 *            식별정보 데이터 타입.
 * @param <V>
 *            {@link Future}가 제공하는 데이터 타입.
 * @since 2020. 11. 10.
 * @version _._._
 */
public class AsyncJobManager<K, V> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    // private HashMap<K, Future<V>> ASYNC_JOBS = new HashMap<>();
    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 11. 10     박준홍     최초 작성
     * 2021. 2. 9.		박준홍     Future의 Generic 타입을 wildcard로 변경
     * </pre>
     */
    private HashMap<K, Future<?>> ASYNC_JOBS = new HashMap<>();

    private ReentrantLock LOCK = new ReentrantLock();

    /**
     * 비동기 작업을 등록한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 11. 10.        박준홍         최초 작성
     * </pre>
     *
     * @since 2020. 11. 10.
     */
    private AsyncJobManager() {
    }

    /**
     * <K>에 해당하는 <V> 를 반환한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 11. 10.		박준홍			최초 작성
     * 2021. 2. 9.      박준홍     Future의 Generic 타입을 wildcard로 변경
     * </pre>
     *
     * @param key
     * @return
     *
     * @since 2020. 11. 10.
     * @version _._._
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    // public Future<V> get(K key) {
    public Future<?> get(K key) {
        if (key == null) {
            return null;
        }

        ReentrantLock lock = LOCK;
        lock.lock();
        try {
            return ASYNC_JOBS.get(key);
        } catch (Exception e) {
            logger.warn("비동기 작업 조회 중 에러가 발생하였습니다. key={}, cause={}", key, e.getMessage(), e);
            return null;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 식별정보에 해당하는 비동기 작업이 있는지 확인한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 11. 10.        박준홍         최초 작성
     * </pre>
     *
     * @param key
     * @return
     *
     * @since 2020. 11. 10.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public boolean has(K key) {
        if (key == null) {
            return true;
        }

        ReentrantLock lock = LOCK;
        lock.lock();
        try {
            return ASYNC_JOBS.containsKey(key);
        } catch (Exception e) {
            logger.warn("비동기 작업 조회 중 에러가 발생하였습니다. key={}, cause={}", key, e.getMessage(), e);
            return false;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 비동기 작업을 등록한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 11. 10.        박준홍         최초 작성
     * 2021. 2. 9.      박준홍     Future의 Generic 타입을 wildcard로 변경
     * </pre>
     *
     * @param key
     * @param job
     * @return
     *
     * @since 2020. 11. 10.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    // public boolean register(K key, Future<V> job) {
    public boolean register(K key, Future<?> job) {
        if (key == null || job == null) {
            return false;
        }

        ReentrantLock lock = LOCK;
        lock.lock();
        try {

            if (ASYNC_JOBS.containsKey(key)) {
                return false;
            }

            ASYNC_JOBS.put(key, job);

            return true;
        } catch (Exception e) {
            logger.warn("비동기 작업 등록 중 에러가 발생하였습니다. key={}, job={}, cause={}", key, job, e.getMessage(), e);
            return false;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 식별정보에 해당하는 비동기 작업을 반환한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 11. 10.        박준홍         최초 작성
     * 2021. 2. 9.      박준홍     Future의 Generic 타입을 wildcard로 변경
     * </pre>
     *
     * @param <T>
     * @param key
     * @return
     *
     * @since 2020. 11. 10.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    // public Future<V> unregister(K key) {
    public Future<?> unregister(K key) {
        if (key == null) {
            return null;
        }

        ReentrantLock lock = LOCK;
        lock.lock();
        try {
            return ASYNC_JOBS.remove(key);
        } catch (Exception e) {
            logger.warn("비동기 작업 제거 중 에러가 발생하였습니다. key={}, cause={}", key, e.getMessage(), e);
            return null;
        } finally {
            lock.unlock();
        }
    }

    /**
     * {@link AsyncJobManager} 를 생성하는 클래스.
     * 
     * @since 2020. 11. 11.
     * @version _._._
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public static class Builder {

        private static final Map<Object, AsyncJobManager<?, ?>> SINGLETON = new HashMap<>();

        private static ReentrantLock LOCK = new ReentrantLock();

        /**
         * 주어진 key에 해당하는 {@link AsyncJobManager} 객체를 반환한다. <br>
         * 존재하지 않는 새로운 객체를 생성/등록 후 반환한다. <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜      | 작성자   |   내용
         * ------------------------------------------
         * 2020. 11. 10.    박준홍     최초 작성
         * 2021. 2. 9.      박준홍     AsyncJobManager의 두번째 Generic 타입을 wildcard로 변경
         * </pre>
         *
         * @param <K>
         * @param <V>
         * @param key
         * @return
         *
         * @since 2020. 11. 10.
         * @version _._._
         * @author Park_Jun_Hong_(fafanmama_at_naver_com)
         */
        @SuppressWarnings("unchecked")
        // public static <K, V> AsyncJobManager<K, V> getManager(Object key) {
        public static <K> AsyncJobManager<K, ?> getManager(Object key) {

            ReentrantLock lock = LOCK;
            lock.lock();

            try {
                AsyncJobManager<K, ?> m = null;
                if (SINGLETON.containsKey(key)) {
                    m = (AsyncJobManager<K, ?>) SINGLETON.get(key);
                } else {
                    m = new AsyncJobManager<K, Object>();
                    SINGLETON.put(key, m);
                }
                return m;
            } catch (Throwable t) {
                throw ExceptionUtils.newException(RuntimeException.class, t, "Occurs an unknown case");
            } finally {
                lock.unlock();
            }
        }
    }
}
