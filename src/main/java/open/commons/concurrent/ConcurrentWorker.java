/*
 * Copyright 2011 Park Jun-Hong (parkjunhong77/google/com)
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
 * Date  : 2018. 5. 29. 오후 6:28:42
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package open.commons.concurrent;

import java.util.concurrent.ConcurrentLinkedQueue;

import open.commons.lang.DefaultRunnable;

/**
 * Consumer & Provider 형식의 데이터 공유를 지원하는 클래스.
 * 
 * @since 2018. 5. 29.
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
public abstract class ConcurrentWorker<E> extends DefaultRunnable {

    private ConcurrentLinkedQueue<E> queue = new ConcurrentLinkedQueue<>();
    private Mutex mutexQueue = new Mutex("mutex for 'Queue'");

    /**
     * 
     * @since 2018. 5. 29.
     */
    public ConcurrentWorker() {
    }

    /**
     * @see open.commons.lang.DefaultRunnable#afterStop()
     */
    @Override
    protected void afterStop() {

        super.afterStop();

        synchronized (mutexQueue) {
            queue.clear();

            mutexQueue.notifyAll();
        }
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 1. 25.		박준홍			최초 작성
     * 2019. 3. 1.		박준홍			데이터 존재 유부를 get() 후에 데이터가 null이 아닌 경우로 판단한 버그 수정. null 이 데이타 일 수도 있음.
     * </pre>
     *
     * @return
     *
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @since 2019. 1. 25.
     */
    protected E get() {
        // #1. 처리할 데이터 획득.
        synchronized (mutexQueue) {
            if (queue.size() < 1) {
                do {
                    try {
                        mutexQueue.wait();
                    } catch (InterruptedException ignored) {
                        System.err.println("Explicitly Interrupted by EXTERNAL.");
                        break;
                    }
                } while (queue.size() < 1);
            }
            return queue.poll();
        }
    }

    /**
     * 데이터 큐를 위한 Mutex 객체를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 1. 25.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @since 2019. 1. 25.
     */
    protected final Object getMutexForQueue() {
        return this.mutexQueue;
    }

    /**
     * 데이터를 추가한다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 1. 25.		박준홍			최초 작성
     * </pre>
     *
     * @param data
     *
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @since 2019. 1. 25.
     */
    public void push(E data) {

        if (!isRunning()) {
            return;
        }

        synchronized (mutexQueue) {

            queue.add(data);

            mutexQueue.notifyAll();
        }
    }

    protected int size() {
        return queue.size();
    }

}
