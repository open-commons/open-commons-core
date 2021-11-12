/*
 * Copyright 2011 Park Jun-Hong (parkjunhong77@gmail.com)
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
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.concurrent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

import open.commons.lang.DefaultRunnable;

/**
 * Consumer & Provider 형식의 데이터 공유를 지원하는 클래스.
 * 
 * @since 2018. 5. 29.
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public abstract class ConcurrentWorker<E> extends DefaultRunnable {

    /** 작업할 데이터 큐 */
    private ConcurrentLinkedQueue<E> queue = new ConcurrentLinkedQueue<>();
    private Mutex mutexQueue = new Mutex("mutex for 'Queue'");

    /** 작업 예정 또는 작업 중인 데이터 개수 */
    private AtomicInteger workJobCounter = new AtomicInteger();
    private ReentrantLock mutexWJC = new ReentrantLock();

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

        synchronized (this.mutexQueue) {
            queue.clear();

            mutexQueue.notifyAll();
        }
    }

    /**
     * 데이터가 포함여부를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 9. 14.		박준홍			최초 작성
     * </pre>
     *
     * @param o
     * @return
     *
     * @since 2021. 9. 14.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public boolean contains(E o) {
        synchronized (this.mutexQueue) {
            return this.queue.contains(o);
        }
    }

    /**
     * 작업큐에서 꺼낸 작업이 완료되어 작업개수를 감소한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 10. 2.		박준홍			최초 작성
     * </pre>
     *
     *
     * @since 2019. 10. 2.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    protected void doneJob() {
        mutexWJC.lock();
        workJobCounter.decrementAndGet();
        mutexWJC.unlock();
    }

    /**
     * {@link Queue}의 모든 데이터를 제공합니다. 기존 {@link Queue} 의 데이터는 제거된다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 8. 17.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2020. 8. 17.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @see Queue#poll()
     */
    protected Collection<E> flush() {
        Collection<E> all = null;
        synchronized (this.mutexQueue) {
            all = new ArrayList<>(this.queue);
            this.queue.clear();

            mutexWJC.lock();
            workJobCounter.set(0);
            mutexWJC.unlock();
        }

        return all;
    }

    /**
     * {@link Queue}의 첫번째 데이터를 제공합니다. <br>
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2019. 1. 25.
     * @see Queue#poll()
     */
    protected E get() {
        // #1. 처리할 데이터 획득.
        synchronized (this.mutexQueue) {
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
     * {@link Queue}의 첫번째 데이타를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 8. 17.		박준홍			최초 작성
     * </pre>
     *
     * @param nowait
     *            데이터가 없는 경우 {@link Object#wait()} 여부
     * @return
     *
     * @since 2020. 8. 17.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @see Queue#poll()
     */
    protected E get(boolean nowait) {
        // #1. 처리할 데이터 획득.
        synchronized (this.mutexQueue) {
            if (nowait) {
                return queue.size() < 1 ? null : queue.poll();
            } else {
                return get();
            }
        }
    }

    /**
     * 현재 남아있는 작업개수를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 2. 19.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2021. 2. 19.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public int getJobCount() {
        mutexWJC.lock();
        try {
            return workJobCounter.get();
        } finally {
            mutexWJC.unlock();
        }
    }

    /**
     * 데이터 큐를 위한 Mutex 객체를 제공합니다. <br>
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2019. 1. 25.
     */
    protected final Object getMutexForQueue() {
        return this.mutexQueue;
    }

    public void push(Collection<E> data) {

        if (!isRunning()) {
            return;
        }

        mutexWJC.lock();
        workJobCounter.addAndGet(data.size());
        mutexWJC.unlock();

        synchronized (this.mutexQueue) {
            this.queue.addAll(data);
            mutexQueue.notifyAll();
        }
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2019. 1. 25.
     */
    public void push(E data) {

        if (!isRunning()) {
            return;
        }

        mutexWJC.lock();
        workJobCounter.incrementAndGet();
        mutexWJC.unlock();

        synchronized (this.mutexQueue) {
            queue.add(data);
            mutexQueue.notifyAll();
        }

    }

    /**
     * 처리 중인 작업이 있는지를 알려준다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 10. 2.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2019. 10. 2.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public boolean remainsWorkJob() {
        mutexWJC.lock();
        try {
            return workJobCounter.get() > 0;
        } finally {
            mutexWJC.unlock();
        }
    }

    /**
     * 데이터를 삭제한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 9. 14.		박준홍			최초 작성
     * </pre>
     *
     * @param o
     * @return
     *
     * @since 2021. 9. 14.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public boolean remove(E o) {
        synchronized (this.mutexQueue) {
            return this.queue.remove(o);
        }
    }

    /**
     * {@link #run()} 메소드를 구현하여 {@link #queue}에서 꺼낸 데이터를 처리한 후, 작업완료를 명시적으로 기록하기 위해서 {@link #doneJob()}를 사용할 수 있다.<br>
     * 
     * @see open.commons.lang.DefaultRunnable#runInternal()
     */
    @Override
    protected abstract void runInternal();

    /**
     * 작업큐에 남은 개수를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2018. 5. 29.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2018. 5. 29.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    protected int size() {
        return queue.size();
    }

}
