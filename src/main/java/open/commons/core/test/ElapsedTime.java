/*
 * Copyright 2018 Park Jun-Hong (parkjunhong77@gmail.com)
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
 * Date  : 2018. 9. 28. 오후 4:36:04
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.core.test;

import java.text.MessageFormat;
import java.util.concurrent.TimeUnit;

import open.commons.core.utils.TimeUtils;

/**
 * 
 * @since 2018. 9. 28.
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public class ElapsedTime {

    private long start = 0L;

    private long end = 0L;

    private boolean running = false;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 9. 28.     parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @since 2018. 9. 28.
     */
    public ElapsedTime() {
    }

    /**
     * 현재 상태와 주어진 상태를 비교합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2018. 10. 1.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     *
     * @param expected
     *            상태.
     *            <ul>
     *            <li>true: 실행 중
     *            <li>false: 종료
     *            </ul>
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @throws IllegalStateException
     *             현재 상태와 비교상태가 다른 경우.
     * @since 2018. 10. 1.
     */
    private void checkState(boolean expected) throws IllegalStateException {
        if (this.running != expected) {
            throw new IllegalStateException(MessageFormat.format("current.state={0}, expected=[1}" //
                    , running ? "running" : "stopped" //
                    , expected ? "running" : "stopped") //
            );
        }
    }

    /**
     * 경과값을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2018. 9. 28.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     *
     * @return
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @throws IllegalStateException
     *             측정 중인 경우
     * @since 2018. 9. 28.
     */
    public long elapsed() throws IllegalStateException {
        checkState(false);

        return this.end - this.start;
    }

    /**
     * 시간 측정을 종료하고 종료시간값을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2018. 9. 28.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     *
     * @return
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2018. 9. 28.
     */
    public long end() {
        checkState(true);

        this.end = System.nanoTime();
        setRunning(false);

        return this.end;
    }

    /**
     * 종료 시간값을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2018. 9. 28.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     *
     * @return
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2018. 9. 28.
     */
    public long getEnd() {
        checkState(false);

        return end;
    }

    /**
     * 시작 시간값을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2018. 9. 28.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     *
     * @return
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2018. 9. 28.
     */
    public long getStart() {
        checkState(true);

        return start;
    }

    /**
     * 시간측정 여부를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2018. 9. 28.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     *
     * @return
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2018. 9. 28.
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * 초기화시킨다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2018. 9. 28.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     *
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2018. 9. 28.
     */
    public void reset() {
        this.start = 0L;
        this.end = 0L;
        setRunning(false);
    }

    /**
     * 측정상태를 설정합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2018. 9. 28.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     *
     * @param running
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2018. 9. 28.
     */
    private void setRunning(boolean running) {
        this.running = running;
    }

    /**
     * 측정을 시작하고 시작시간값을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2018. 9. 28.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     *
     * @return
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2018. 9. 28.
     */
    public long start() {

        checkState(false);

        this.start = System.nanoTime();
        setRunning(true);

        return this.start;
    }

    /**
     * 측정을 종료하고 경과값을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2018. 9. 28.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     *
     * @return
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2018. 9. 28.
     * 
     * @throws IllegalStateException
     *             측정하지 않는 경우
     */
    public long stop() {

        this.end();

        return elapsed();
    }

    /**
     * 경과값을 시간값으로 변경하여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2018. 9. 28.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     *
     * @return
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2018. 9. 28.
     */
    public String toFormattedElapsed() {
        return TimeUtils.toFormattedString(this.elapsed(), TimeUnit.NANOSECONDS);
    }

    @Override
    public String toString() {
        return "ElapsedTime [start=" + start + ", end=" + end + ", running=" + running + "]";
    }

}
