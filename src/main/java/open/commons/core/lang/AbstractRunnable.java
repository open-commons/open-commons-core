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
* This file is generated under this project, "open-commons-core".
*
* Date  : 2013. 6. 20. 오전 11:47:42
*
* Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
* 
*/

/**
 * 
 */
package open.commons.core.lang;

import open.commons.core.concurrent.Mutex;

/**
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */
public abstract class AbstractRunnable implements IRunnable {

    /**
     * 외부 실행 여부.<br>
     * 예)
     * 
     * <pre>
     * AbstractRunnable runnable = new XXXX(...);
     * Thread t = new Thread(runnable);
     * t.start();
     * </pre>
     */
    protected boolean startedExternally = false;

    /**
     * 내부 실행 여부.<br>
     * 예)
     * 
     * <pre>
     * AbstractRunnable runnable = new XXXX(...);
     * runnable.start();
     * </pre>
     */
    protected boolean startedInternally = false;

    protected boolean stoppedNormally = false;

    protected Thread executor;
    protected Mutex mutexExecutor = new Mutex("executor");

    protected boolean isRunning = true;

    /**
     * 서비스 구동 후에 실행된다.
     */
    protected abstract void afterStartup();

    /**
     * 서비스 종료 후에 실행된다.
     */
    protected abstract void afterStop();

    /**
     * 서비스 구동 전에 실행된다.
     */
    protected abstract void beforeStartup();

    /**
     * 서비스 종료 전에 실행된다.
     */
    protected abstract void beforeStop();

    /**
     * @see open.commons.core.lang.IControllable#isRunning()
     */
    @Override
    public boolean isRunning() {
        return isRunning;
    }

    /**
     * 현재 작업이 종료될 때까지 대기합니다. <br>
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
    public void join() {
        join(0, 0);
    }

    /**
     * 현재 작업이 종료될 때까지 대기합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 10. 2.		박준홍			최초 작성
     * </pre>
     *
     * @param millis
     *
     * @since 2019. 10. 2.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public void join(long millis) {
        join(millis, 0);
    }

    /**
     * 현재 작업이 종료될 때까지 대기합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 10. 2.		박준홍			최초 작성
     * </pre>
     *
     * @param millis
     * @param nanos
     *
     * @since 2019. 10. 2.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public void join(long millis, int nanos) {
        try {
            if (executor == null) {
                Thread.currentThread().join(millis, nanos);
            } else {
                this.executor.join(millis, nanos);
            }
        } catch (InterruptedException ignored) {
        }
    }

    /**
     * @see open.commons.core.lang.IControllable#ready()
     */
    @Override
    public void ready() {
        isRunning = true;
    }

    /**
     * {@link Runnable#run()}을 overriding 하는 메소드이다.<br>
     * 이 클래스를 사용하는데 있어서 보다 유용한 사용은 {@link #run()}에서 서비스를 제공하기 전에 {@link #startInternally()}를 실행하는 것이다.
     * 
     * @see java.lang.Runnable#run()
     */
    @Override
    public abstract void run();

    /**
     * 
     * @see open.commons.core.lang.IControllable#start()
     * @see #beforeStartup()
     * @see #afterStartup()
     */
    @Override
    public void start() {
        start(false);
    }

    /**
     * @see open.commons.core.lang.IRunnable#start(boolean)
     */
    @Override
    public void start(boolean daemon) {
        beforeStartup();

        if (startedExternally) {
            executor = Thread.currentThread();
        } else {
            executor = new Thread(this);
            executor.setDaemon(daemon);
            startedInternally = true;
            executor.start();
        }

        afterStartup();
    }

    /**
     * 서비스 시작 전에 {@link #start()}를 통해서 시작시킨다.
     */
    protected final void startInternally() {
        if (!startedInternally) {
            startedExternally = true;

            start();
        }
    }

    /**
     * 
     * @see open.commons.core.lang.IControllable#stop()
     * @see #beforeStartup()
     * @see #afterStartup()
     */
    @Override
    public void stop() {

        isRunning = false;

        beforeStop();

        synchronized (mutexExecutor) {
            try {
                if (executor != null) {
                    executor.interrupt();
                }
            } catch (Exception ignored) {
            }
        }

        afterStop();
    }

    protected void stopNormally() {
        if (!stoppedNormally) {
            stop();
        }
    }

}
