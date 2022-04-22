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

package open.commons.core.log;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.event.Level;

import open.commons.core.utils.ArrayUtils;
import open.commons.core.utils.StringUtils;

/**
 * @author Park Jun-Hong.(mail_to:parkjunhong77@gmail.com)
 * 
 * @date
 * 
 */
public class LogUtils {

    private static boolean enabled = false;

    private static final String TIMESTAMP_FORMAT_STRING = "yyyy-MM-dd hh:mm:ss.SSS";

    private static final SimpleDateFormat TIMESTAMP_FORMAT = new SimpleDateFormat(TIMESTAMP_FORMAT_STRING);

    // static final String LOG_FORMAT_2 = "{" + CATEGORY + "} %s%-20s\t (%s)";
    // static final String LOG_FORMAT_3 = "{" + CATEGORY + "} %s%-20s: %s\t (%s)";
    /**
     * <ul>
     * <li>0: time-stamp
     * <li>1: thread name
     * <li>2: log depth indentation
     * <li>3: message
     * <li>4: trace info
     * </ul>
     */
    static final String LOG_FORMAT_2 = "[%s] {%s} %s%10s\t (%s)";

    /**
     * 
     * <ul>
     * <li>0: time-stamp
     * <li>1: thread name
     * <li>2: log depth indentation
     * <li>3: name
     * <li>4: message
     * <li>5: trace info
     * </ul>
     */
    static final String LOG_FORMAT_3 = "[%s] {%s} %s%10s: %s\t (%s)";

    // static final String LOG_FORMAT_EM_MSG_1 = "{" + CATEGORY + "}%s메소드 진입 [ %-20s ] (%s)";
    // static final String LOG_FORMAT_EM_MSG_2 = "{" + CATEGORY + "}%s메소드 진입 >>> : %-20s (%s)";
    // static final String LOG_FORMAT_LM_MSG_1 = "{" + CATEGORY + "} %s<<< 메소드 종료 [ %-20s ] <<< (%s)";
    // static final String LOG_FORMAT_LM_MSG_2 = "{" + CATEGORY + "} %s<<< 메소드 종료 <<< : %-20s (%s)";
    /**
     * <ul>
     * <li>0: time-stamp
     * <li>1: thread name
     * <li>2: log depth indentation
     * <li>3: method name
     * <li>4: trace info
     * </ul>
     */
    static final String LOG_FORMAT_EM_MSG_1 = "[%s] {%s}%s >>> | [ %-20s ] (%s)";
    /**
     * <ul>
     * <li>0: time-stamp
     * <li>1: thread name
     * <li>2: log depth indentation
     * <li>3: message
     * <li>4: trace info
     * </ul>
     */
    static final String LOG_FORMAT_EM_MSG_2 = "[%s] {%s}%s >>> | : %-20s  (%s)";
    /**
     * <ul>
     * <li>0: time-stamp
     * <li>1: thread name
     * <li>2: log depth indentation
     * <li>3: method name
     * <li>4: trace info
     * </ul>
     */
    static final String LOG_FORMAT_LM_MSG_1 = "[%s] {%s}%s <<< | [ %-20s ] (%s)";
    /**
     * <ul>
     * <li>0: time-stamp
     * <li>1: thread name
     * <li>2: log depth indentation
     * <li>3: message
     * <li>4: trace info
     * </ul>
     */
    static final String LOG_FORMAT_LM_MSG_2 = "[%s] {%s}%s <<< | : %-20s  (%s)";

    static int callDepth = 0;
    static long ttl = 0;

    public static String callLineTrace() {
        StackTraceElement[] stes = Thread.currentThread().getStackTrace();
        return toTraceableString(stes[2]);
    }

    public static String[] callStack(int depth) {
        return callStack(1, depth);
    }

    /**
     * 
     * @param start
     *            the index of starting
     * @param depth
     *            the depth from the parameter <b><code>start</code></b>.
     * @return
     * @since 2012. 7. 5.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static String[] callStack(int start, int depth) {
        if (depth < 1) {
            return new String[] { "None call stacks." };
        }

        StackTraceElement[] stackTraceElems = Thread.currentThread().getStackTrace();

        if (stackTraceElems.length < start + 3) {
            return new String[] { "None call statcks." };
        }

        stackTraceElems = (StackTraceElement[]) ArrayUtils.subArrays(Thread.currentThread().getStackTrace(), 2 + start, false)[1];

        if (stackTraceElems.length < 1) {
            return new String[] { "None call statcks." };
        }

        int length = depth < stackTraceElems.length ? depth : stackTraceElems.length;

        String[] stackTraces = new String[length];
        for (int i = 0; i < stackTraces.length; i++) {
            stackTraces[i] = toTraceableString(stackTraceElems[i]) + "\n";
        }

        return stackTraces;
    }

    /**
     * 
     * @param start
     * @param depth
     * @return
     */
    public static String callStackToString(int start, int depth) {
        return "\n\t\t--- call stack ---\n\t\t" + StringUtils.concatenate("\t\t" //
                , LogUtils.callStack(start + 1, depth));
    }

    /**
     * 
     * <b>[메소드 개요]</b>
     * <p>
     * 메소드 진입 메시지 생성
     * 
     * @return
     * 
     *         <br>
     * 
     *         <pre>
     * == 개정이력(Modification Information) ==
     * 
     * 수정일                		수정자 	 			수정내용
     * ------------		---------------		---------------------------
     * 2011. 9. 4.      Park Jun-Hong	           최초 생성
     *         </pre>
     */
    public static String emMsg() {
        return emMsg(1);
    }

    private static String emMsg(int depth) {
        StackTraceElement ste = Thread.currentThread().getStackTrace()[2 + depth];

        return emMsg(ste.getMethodName(), depth + 1);
    }

    /**
     * 
     * <b>[메소드 개요]</b>
     * <p>
     * 메소드 진입 메시지 생성
     * 
     * @param comment
     * @return
     * 
     *         <br>
     * 
     *         <pre>
     * == 개정이력(Modification Information) ==
     * 
     * 수정일                		수정자 	 			수정내용
     * ------------		---------------		---------------------------
     * 2011. 9. 4.      Park Jun-Hong	           최초 생성
     *         </pre>
     */
    public static String emMsg(Object comment) {
        return emMsg(comment, 1);
    }

    private static String emMsg(Object comment, int depth) {
        String callDepthString = logIndentation();
        callDepth++;

        String timestamp = TIMESTAMP_FORMAT.format(Calendar.getInstance().getTime());
        String threadName = Thread.currentThread().getName();
        StackTraceElement ste = Thread.currentThread().getStackTrace()[2 + depth];

        return String.format(LOG_FORMAT_EM_MSG_1 // log format
                , timestamp // timestamp
                , threadName // thread name
                , callDepthString // log depth indentation
                , comment != null ? comment : "" // message
                , ste.getClassName() + ".java:" + ste.getLineNumber() // source trace info
        );
    }

    public static void entryLog() {
        log(System.out, emMsg(1));
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 4. 20.		박준홍			최초 작성
     * </pre>
     *
     * @param logger
     * @param level
     *
     * @since 2022. 4. 20.
     * @version 2.0.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static void entryLog(Logger logger, Level level) {
        log(logger, level, emMsg(1));
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 4. 20.		박준홍			최초 작성
     * </pre>
     *
     * @param logger
     * @param level
     * @param message
     *
     * @since 2022. 4. 20.
     * @version 2.0.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static void entryLog(Logger logger, Level level, Object message) {
        log(logger, level, emMsg(message, 1));
    }

    public static void entryLog(Object log) {
        log(System.out, emMsg(log, 1));
    }

    public static void entryLog(PrintStream outStream) {
        log(outStream, emMsg(1));
    }

    public static void entryLog(PrintStream outStream, Object log) {
        log(outStream, emMsg(log, 1));
    }

    public static void error(String log) {
        String timestamp = TIMESTAMP_FORMAT.format(Calendar.getInstance().getTime());
        String threadName = Thread.currentThread().getName();
        StackTraceElement ste = Thread.currentThread().getStackTrace()[2];

        log(System.err, String.format(LOG_FORMAT_2 // log format
                , timestamp // timestamp
                , threadName // thread name
                , logIndentation() // log depth indentation
                , log //
                , ste.getClassName() + ".java:" + ste.getLineNumber() // source traceable info
        ));
    }

    public static void error(String name, String log) {
        String timestamp = TIMESTAMP_FORMAT.format(Calendar.getInstance().getTime());
        String threadName = Thread.currentThread().getName();
        StackTraceElement ste = Thread.currentThread().getStackTrace()[2];

        log(System.err, String.format(LOG_FORMAT_3 // log format
                , timestamp // timestamp
                , threadName // thread name
                , logIndentation() // log depth indentation
                , name // category
                , log // mesasge
                , ste.getClassName() + ".java:" + ste.getLineNumber() // source traceable info
        ));
    }

    public static void leaveLog() {
        log(System.out, lmMsg(1));
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 4. 20.     박준홍         최초 작성
     * </pre>
     *
     * @param logger
     * @param level
     *
     * @since 2022. 4. 20.
     * @version 2.0.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static void leaveLog(Logger logger, Level level) {
        log(logger, level, lmMsg(1));
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 4. 20.     박준홍         최초 작성
     * </pre>
     *
     * @param logger
     * @param level
     * @param message
     *
     * @since 2022. 4. 20.
     * @version 2.0.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static void leaveLog(Logger logger, Level level, Object message) {
        log(logger, level, lmMsg(message, 1));
    }

    public static void leaveLog(Object log) {
        log(System.out, lmMsg(log, 1));
    }

    public static void leaveLog(PrintStream outStream) {
        log(outStream, lmMsg(1));
    }

    public static void leaveLog(PrintStream outStream, Object log) {
        log(outStream, lmMsg(log, 1));
    }

    /**
     * 
     * <b>[메소드 개요]</b>
     * <p>
     * 메소드 종료 메시지 생성
     * 
     * @return
     * 
     *         <br>
     * 
     *         <pre>
     * == 개정이력(Modification Information) ==
     * 
     * 수정일                		수정자 	 			수정내용
     * ------------		---------------		---------------------------
     * 2011. 9. 4.      Park Jun-Hong	           최초 생성
     *         </pre>
     */
    public static String lmMsg() {
        return lmMsg(1);
    }

    private static String lmMsg(int depth) {
        StackTraceElement ste = Thread.currentThread().getStackTrace()[2 + depth];
        return lmMsg(ste.getMethodName(), depth + 1);
    }

    /**
     * 
     * <b>[메소드 개요]</b>
     * <p>
     * 메소드 종료 메시지 생성
     * 
     * @param comment
     * @return
     * 
     *         <br>
     * 
     *         <pre>
     * == 개정이력(Modification Information) ==
     * 
     * 수정일                		수정자 	 			수정내용
     * ------------		---------------		---------------------------
     * 2011. 9. 4.      Park Jun-Hong	           최초 생성
     *         </pre>
     */
    public static String lmMsg(Object comment) {
        return lmMsg(comment, 1);
    }

    private static String lmMsg(Object comment, int depth) {
        callDepth--;
        String callDepthString = logIndentation();

        String timestamp = TIMESTAMP_FORMAT.format(Calendar.getInstance().getTime());
        String threadName = Thread.currentThread().getName();
        StackTraceElement ste = Thread.currentThread().getStackTrace()[2 + depth];

        return String.format(LOG_FORMAT_LM_MSG_2 // log format
                , timestamp // timestamp
                , threadName // thread name
                , callDepthString // log depth indentation
                , comment != null ? comment : "" // message
                , ste.getClassName() + ".java:" + ste.getLineNumber() // source trace info
        );
    }

    private static void log(Logger logger, Level level, Object log) {
        if (!enabled) {
            return;
        }

        switch (level) {
            case ERROR:
                logger.error("{}", log);
                break;
            case WARN:
                logger.warn("{}", log);
                break;
            case INFO:
                logger.info("{}", log);
                break;
            case DEBUG:
                logger.debug("{}", log);
                break;
            case TRACE:
                logger.trace("{}", log);
                break;
            default:
                throw new UnsupportedOperationException("지원하지 않는 레벨입니다. 입력=" + level);
        }
    }

    public static void log(Object obj) {
        log(obj != null ? obj.toString() : null);
    }

    private static void log(PrintStream out, Object log) {
        if (enabled) {
            out.println(log);
        }
    }

    public static void log(String log) {
        String timestamp = TIMESTAMP_FORMAT.format(Calendar.getInstance().getTime());
        String threadName = Thread.currentThread().getName();
        StackTraceElement ste = Thread.currentThread().getStackTrace()[2];

        log(System.out, String.format(LOG_FORMAT_2 // log format
                , timestamp // timestamp
                , threadName // thread name
                , logIndentation() // log depth indentation
                , log //
                , ste.getClassName() + ".java:" + ste.getLineNumber() // source traceable info
        ));
    }

    public static void log(String name, String log) {
        String timestamp = TIMESTAMP_FORMAT.format(Calendar.getInstance().getTime());
        String threadName = Thread.currentThread().getName();
        StackTraceElement ste = Thread.currentThread().getStackTrace()[2];

        log(System.out, String.format(LOG_FORMAT_3 // log format
                , timestamp // timestamp
                , threadName // thread name
                , logIndentation() // log depth indentation
                , name // category
                , log // mesasge
                , ste.getClassName() + ".java:" + ste.getLineNumber() // source traceable info
        ));
    }

    public static String logCallStack() {
        StackTraceElement[] stes = Thread.currentThread().getStackTrace();

        StringBuffer sb = new StringBuffer();

        for (int i = 2; i < stes.length; i++) {
            sb.append(stes[i] + "\n");
        }

        return sb.toString();
    }

    private static String logIndentation() {
        long cttl = System.currentTimeMillis();

        if (cttl - ttl > 20000)
            callDepth = 0;

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < callDepth; i++) {
            sb.append('\t');
        }

        ttl = System.currentTimeMillis();
        return sb.toString();
    }

    /**
     * Formats the name and the message with a format is {@code "%20s} and returns it.
     * 
     * @param value
     * @param message
     * 
     * @return
     */
    public static String logMessage(Object message) {

        String timestamp = TIMESTAMP_FORMAT.format(Calendar.getInstance().getTime());
        String threadName = Thread.currentThread().getName();
        StackTraceElement ste = Thread.currentThread().getStackTrace()[2];

        return String.format(LOG_FORMAT_2 // log format
                , timestamp // timestamp
                , threadName // thread name
                , logIndentation() // log depth indentation
                , message //
                , ste.getClassName() + ".java:" + ste.getLineNumber() // source traceable info
        );
    }

    /**
     * Formats the name and the message with a format is {@code "%20s: %s} and returns it.
     * 
     * @param name
     * @param message
     * 
     * @return
     */
    public static String logMessage(String name, Object message) {
        String timestamp = TIMESTAMP_FORMAT.format(Calendar.getInstance().getTime());
        String threadName = Thread.currentThread().getName();
        StackTraceElement ste = Thread.currentThread().getStackTrace()[2];

        return String.format(LOG_FORMAT_3 // log format
                , timestamp // timestamp
                , threadName // thread name
                , logIndentation() // log depth indentation
                , name // category
                , message // mesasge
                , ste.getClassName() + ".java:" + ste.getLineNumber() // source traceable info
        );
    }

    public static boolean setEnabled(boolean enabled) {
        boolean o = LogUtils.enabled;

        LogUtils.enabled = enabled;

        return o;
    }

    private static String toTraceableString(StackTraceElement stack) {
        return stack.getClassName() + "." + stack.getMethodName() + " (" + stack.getFileName() + ":" + stack.getLineNumber() + ")";
    }

    // public static
}
