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
* Date  : 2013. 6. 20. 오후 3:00:34
*
* Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
* 
*/

/**
 * 
 */
package open.commons.core.lang;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

import org.apache.logging.log4j.ThreadContext;

import open.commons.core.utils.ThreadUtils;

/**
 * <br>
 * 
 * <pre>
 * [개정이력]
 *      날짜      | 작성자   |   내용
 * ------------------------------------------
 * xxxx.xx.xx       xxx         최초작성
 * 2019. 10. 17.        박준홍         {@link #beforeStartup()}에 Process ID 를 {@link ThreadContext}에 'pid'란 이름으로 추가.
 * </pre>
 * 
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */
public abstract class DefaultRunnable extends AbstractRunnable implements AutoCloseable {

    /**
     * 
     * @see open.commons.core.lang.AbstractRunnable#afterStartup()
     */
    @Override
    protected void afterStartup() {
    }

    /**
     * 
     * @see open.commons.core.lang.AbstractRunnable#afterStop()
     */
    @Override
    protected void afterStop() {
    }

    /**
     * {@link #runInternal()} 메소드 실행 전에 수행되는 기능을 구현합니다.<br>
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 10. 17.		박준홍			최초 작성
     * </pre>
     *
     *
     * @since 2019. 10. 17.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    protected void beforeRun() {
    }

    /**
     * 
     * @see open.commons.core.lang.AbstractRunnable#beforeStartup()
     */
    @Override
    protected void beforeStartup() {
    }

    /**
     * 
     * @see open.commons.core.lang.AbstractRunnable#beforeStop()
     */
    @Override
    protected void beforeStop() {
    }

    /**
     * @see java.lang.AutoCloseable#close()
     */
    @Override
    public void close() throws Exception {
    }

    protected String getThreadName() {
        return null;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * xxxx.xx.xx.      박준홍     최초 작성
     * 2022. 9. 1.		박준홍     final 제거. 아래 내용을지 원하기 위함.
     *                              - Unable to proxy interface-implementing method [public final void open.commons.core.lang.DefaultRunnable.run()] 
     *                                because it is marked as final: Consider using interface-based JDK proxies instead! (CglibAopProxy.java)
     * </pre>
     *
     * @see open.commons.core.lang.AbstractRunnable#run()
     */
    @Override
    public void run() {

        String OTN = Thread.currentThread().getName();
        String tn = getThreadName();
        if (tn != null) {
            ThreadUtils.setThreadName(tn);
        }

        // begin - PATCH [2019. 10. 17.]: Process ID를 ThreadContext에 'pid' 라는 이름으로 설정<br>
        // Log4j(1/2) 사용지 Pattern에 %X{pid} 라는 설정으로 사용할 수 있습니다.
        // Park_Jun_Hong_(parkjunhong77@gmail.com)
        RuntimeMXBean rt = ManagementFactory.getRuntimeMXBean();
        StringBuffer buf = new StringBuffer();
        for (char ch : rt.getName().toCharArray()) {
            if (ch >= '0' && ch <= '9') {
                buf.append(ch);
            }
        }
        ThreadContext.put("pid", buf.toString());
        // end - Park_Jun_Hong_(parkjunhong77@gmail.com), 2019. 10. 17.

        if (!startedInternally) {
            startInternally();
        }

        beforeRun();

        runInternal();

        if (OTN != null) {
            ThreadUtils.setThreadName(OTN);
        }
    }

    /**
     * {@link Thread}에 Process ID 정보를 설정하기 위해서, {@link Runnable#run()} 를 final 로 정의하였다.<br>
     * 이를 대체하기 위해서 실제 기능 구현을 하는 메소드이다. <br>
     * Log4j(1,2)에서 Pattern에 %X{pid} 로 설정함으로써 로그메시지에 Process ID 를 출력할 수 있습니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 10. 17.		박준홍			최초 작성
     * </pre>
     *
     *
     * @since 2019. 10. 17.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    protected abstract void runInternal();
}
