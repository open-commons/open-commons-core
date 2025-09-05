/*
 * Copyright 2022 Park Jun-Hong (parkjunhong77@gmail.com)
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
 * Date  : 2022. 7. 12. 오후 4:04:29
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package open.commons.core.concurrent;

import java.util.concurrent.ConcurrentLinkedQueue;

import open.commons.core.lang.DefaultRunnable;

/**
 * 
 * @since 2022. 7. 12.
 * @version 2.0.0
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
public abstract class AsyncWorker<E> extends DefaultRunnable {

    protected final ConcurrentLinkedQueue<E> queue = new ConcurrentLinkedQueue<>();

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 7. 12.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     *
     *
     * @since 2022. 7. 12.
     * @version 2.0.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public AsyncWorker() {
    }

    /**
     * 새로운 데이터를 추가합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 7. 12.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     *
     * @param data
     *
     * @since 2022. 7. 12.
     * @version 2.0.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public void add(E data) {
        if (data == null) {
            return;
        }

        this.queue.add(data);
    }

    /**
     * 쓰레드 종료 후 작업을 실행합니다.
     * 
     * @since 2022. 7. 12.
     * @version 2.0.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     *
     * @see open.commons.core.lang.DefaultRunnable#afterStop()
     */
    @Override
    public void afterStop() {
        super.afterStop();
        this.queue.clear();
    }

    /**
     * 모든 데이터를 삭제합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 7. 12.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     *
     *
     * @since 2022. 7. 12.
     * @version 2.0.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public void clear() {
        this.queue.clear();
    }

    /**
     * 종료합니다.
     * 
     * @since 2022. 7. 12.
     * @version 2.0.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     *
     * @see open.commons.core.lang.DefaultRunnable#close()
     */
    @Override
    public void close() throws Exception {
        super.close();
        this.queue.clear();
    }

    /**
     * 첫번째 데이터를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 7. 12.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     *
     * @return 첫번째 데이터. 비어 있는 경우 <code>null</code>.
     *
     * @since 2022. 7. 12.
     * @version 2.0.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    protected final E get() {
        return this.queue.poll();
    }

    /**
     * 현재 데이터 개수를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 7. 12.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2022. 7. 12.
     * @version 2.0.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    protected final int size() {
        return this.queue.size();
    }
}
