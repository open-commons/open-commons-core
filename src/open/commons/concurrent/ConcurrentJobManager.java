/*
 * Copyright 2019 Park Jun-Hong_(fafanmama_at_naver_com)
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
 * Date  : 2019. 2. 20. 오후 7:54:32
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package open.commons.concurrent;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

import open.commons.utils.ThreadUtils;

/**
 * 멀티쓰레드로 처리도는 서비스 결과를 관리하는 클래스.
 * 
 * @since 2019. 2. 20.
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
public abstract class ConcurrentJobManager<E> extends ConcurrentWorker<E> implements IJobFinishedListener<E> {

    private final int totalJobCount;
    private final AtomicInteger successJobCount = new AtomicInteger(0);
    private final AtomicInteger failedJobCount = new AtomicInteger(0);

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 2. 20.     박준홍         최초 작성
     * </pre>
     *
     * @param data
     *            작업결과를 저장할 {@link Collection}.
     * @since 2019. 2. 20.
     */
    public ConcurrentJobManager(int totalJobCount) {
        this.totalJobCount = totalJobCount;
    }

    /**
     * 작업결과 데이터를 처리하고, 연속 작업 여부를 제공한다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 2. 20.     박준홍         최초 작성
     * </pre>
     *
     * @param response
     *            <b><code>null</code></b> 일 수 있음.
     * @return
     *
     * @since 2019. 2. 20.
     */
    protected abstract boolean fetch(E response);

    /**
     * 실패한 작업 개수를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 2. 20.     박준홍         최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2019. 2. 20.
     */
    public int getFailedJobCount() {
        return this.failedJobCount.get();
    }

    /**
     * 전체 작업 개수를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 2. 20.     박준홍         최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2019. 2. 20.
     */
    public int getJobCount() {
        return this.totalJobCount;
    }

    /**
     * 성공한 작업 개수를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 2. 20.     박준홍         최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2019. 2. 20.
     */
    public int getSuccessJobCount() {
        return this.successJobCount.get();
    }

    protected abstract String getThreadName();

    /**
     * @see kr.re.etri.spp.interworking.job.IJobFinishedListener#handleFinished(java.lang.Object)
     */
    @Override
    public final void handleFinished(E job) {
        push(job);
    }

    protected final void incrementFail() {
        this.failedJobCount.incrementAndGet();
    }

    protected final void incrementSuccess() {
        this.successJobCount.incrementAndGet();
    }

    /**
     * 작업이 완료할 때가지 대기한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 2. 20.     박준홍         최초 작성
     * </pre>
     *
     * @param millis
     *            최대 대기시간.
     *
     * @since 2019. 2. 20.
     */
    public void join(long millis) {
        try {
            if (millis < 0) {
                this.executor.join();
            } else {
                this.executor.join(millis);
            }
        } catch (InterruptedException ignored) {
            ignored.printStackTrace();
        }
    }

    /**
     * @see open.commons.lang.AbstractRunnable#run()
     */
    @Override
    public void run() {

        final String OTN = Thread.currentThread().getName();
        String threadName = getThreadName();
        if (threadName != null) {
            ThreadUtils.setThreadName(threadName);
        }

        E e = null;

        while (isRunning() && (e = get()) != null) {
            if (!fetch(e)) {
                stop();
            }
        }

        ThreadUtils.setThreadName(OTN);
    }
}
