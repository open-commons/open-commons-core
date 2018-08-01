/*
 * Copyright 2011 Park Jun-Hong_(fafanmama_at_naver_com)
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

    protected E get() {

        E qd = null;

        // #1. 처리할 데이터 획득.
        synchronized (mutexQueue) {
            qd = queue.poll();
            if (qd == null) {
                do {
                    try {
                        mutexQueue.wait();

                        qd = queue.poll();
                    } catch (InterruptedException ignored) {
                        ignored.printStackTrace();
                        
                        break;
                    }
                } while (qd == null);
            }
        }

        return qd;
    }

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
