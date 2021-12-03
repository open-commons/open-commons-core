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
* 
* This file is generated under this project, "open-commons-core".
*
* Date  : 2013. 7. 3. 오후 6:21:57
*
* Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
* 
*/
package open.commons.log;

import java.io.File;
import java.io.PrintStream;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */
public class LoggerFactory {

    private static ILogger singleton;

    private static ConcurrentHashMap<Integer, ILogger> logs = new ConcurrentHashMap<Integer, ILogger>();

    public static boolean contains(String name) {
        return logs.contains(name);
    }

    public static ILogger getInstance() {
        if (singleton == null) {
            singleton = new ConsoleLogger();
        }

        return singleton;
    }

    public static ILogger getInstance(Object obj) {
        return getLogger(obj.hashCode());
    }

    public static ILogger getInstance(String name) {
        return getLogger(name.hashCode());
    }

    /**
     * 
     * @param logUrl
     *            로그 파일 저장 경로
     * @param logName
     *            로그 이름
     * @return
     * 
     * @see AbstractLogger#dateFormat
     */
    public static ILogger getInstance(String logUrl, String logName) {
        int hashcode = (logUrl + File.separator + logName).hashCode();

        ILogger log = logs.get(hashcode);
        if (log == null) {
            log = new FileLogger(logUrl, logName);

            logs.put(hashcode, log);
        }

        return log;
    }

    /**
     * 
     * @param logUrl
     *            로그 파일 저장 경로
     * @param logName
     *            로그 이름
     * @param logFileDiviedRule
     *            로그파일 분할 규칙
     * @return
     * 
     * @see AbstractLogger#dateFormat
     */
    public static ILogger getInstance(String logUrl, String logName, open.commons.log.FileLogger.LogFileDivideRule logFileDiviedRule) {
        int hashcode = (logUrl + File.separator + logName).hashCode();

        ILogger log = logs.get(hashcode);
        if (log == null) {
            log = new FileLogger(logUrl, logName, logFileDiviedRule);

            logs.put(hashcode, log);
        }

        return log;
    }

    private static ILogger getLogger(int hashCode) {
        ILogger log = logs.get(hashCode);
        if (log == null) {
            log = new ConsoleLogger();

            logs.put(hashCode, log);
        }

        return log;
    }

    /**
     * 새로운 {@link ILogger}를 등록합니다.
     * 
     * @param name
     *            {@link ILogger} 이름.
     * @param log
     * @return 등록 여부. 동일한 이름(<b><code>name</code></b>)이 존재하는 경우 <code>false</code>.
     */
    public static boolean registerLog(String name, ILogger log) {

        boolean rtnRegistered = false;

        int hashCode = name.hashCode();

        if (!logs.contains(hashCode)) {
            logs.put(hashCode, log);

            rtnRegistered = true;
        }

        return rtnRegistered;
    }

    public static final void shutdown() {
        if (singleton != null) {
            AbstractLogger.stop();
        } else if (logs.size() > 0) {
            AbstractLogger.stop();
        }
    }

    /**
     * 표준 출력/에러 출력에 로그를 기록하는 클래스.
     * 
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     */
    static class ConsoleLogger extends AbstractLogger {
        /**
         * 
         */
        public ConsoleLogger() {
            stdOutStream = System.out;
            errOutStream = System.err;
        }

        /**
         * 
         * @see open.commons.log.AbstractLogger#afterWrite(PrintStream)
         */
        @Override
        protected void afterWrite(PrintStream stream) {
        }

        /**
         * 
         * @see open.commons.log.AbstractLogger#getCustomErrLog(java.lang.String, java.lang.Exception, int)
         */
        @Override
        protected String getCustomErrLog(String log, Exception e, int depth) {
            return format(TYPE_ERROR, log, e, 1 + depth);
        }

        /**
         * 
         * @see open.commons.log.AbstractLogger#getCustomErrLog(java.lang.String, int)
         */
        @Override
        protected String getCustomErrLog(String log, int depth) {
            return format(TYPE_ERROR, log, 1 + depth);
        }

        /**
         * 
         * @see open.commons.log.AbstractLogger#getCustomStdLog(java.lang.String, int)
         */
        @Override
        protected String getCustomStdLog(String log, int depth) {
            return format(TYPE_LOG, log, 1 + depth);
        }

        /**
         * 
         * @see open.commons.log.AbstractLogger#getErrOutStream()
         */

        @Override
        protected PrintStream getErrOutStream() {
            return errOutStream;
        }

        /**
         * 
         * @see open.commons.log.AbstractLogger#getStdOutStream()
         */
        @Override
        protected PrintStream getStdOutStream() {
            return stdOutStream;
        }

        /**
         * 
         * @see open.commons.log.AbstractLogger#setErrOutStream(PrintStream)
         */
        @Override
        public void setErrOutStream(PrintStream errOutStream) {
        }

        /**
         * 
         * @see open.commons.log.AbstractLogger#setStdOutStream(PrintStream)
         */
        @Override
        public void setStdOutStream(PrintStream stdOutStream) {
        }
    }
}