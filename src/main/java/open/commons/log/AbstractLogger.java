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
* Date  : 2013. 7. 3. 오후 6:22:15
*
* Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
* 
*/
package open.commons.log;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Timer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.atomic.AtomicInteger;

import open.commons.utils.FormatUtils;
import open.commons.utils.StringUtils;

/**
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */
public abstract class AbstractLogger implements ILogger {

    private static int INDENT_MAX = 200;
    private static int INDENT_ALLOW = INDENT_MAX / 2;
    private static final char[] INDENT_ARRAY = new char[INDENT_MAX];

    private static final int INDENT_DEC = -1;
    private static final int INDENT_INC = 1;

    private static final ConcurrentHashMap<Thread, AtomicInteger> indents = new ConcurrentHashMap<Thread, AtomicInteger>();

    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    private static final Thread runner;

    static {

        Arrays.fill(INDENT_ARRAY, ' ');

        runner = new Thread(new Runnable() {
            boolean running = true;

            /**
             * 
             * 
             * @see java.lang.Runnable#run()
             */
            @Override
            public void run() {
                Thread thread = Thread.currentThread();
                String otn = thread.getName();
                thread.setName("INDENT_THREAD_KILLER");

                while (running) {

                    for (Thread t : indents.keySet()) {
                        if (!t.isAlive()) {
                            indents.remove(t);

                            System.err.println("flushed......." + t.getName());
                        }
                    }

                    try {
                        // Thread.sleep(5 * 60 * 1000);
                        Thread.sleep(5000);
                    } catch (Exception ignored) {
                        break;
                    }
                }

                Thread.currentThread().setName(otn);
            }
        });

        runner.start();
    }

    protected static final ConcurrentSkipListSet<Timer> timers = new ConcurrentSkipListSet<Timer>();

    protected static final String PROP_TYPE = "type";
    protected static final String PROP_LOG = "log";

    protected static final String PROP_FORMAT_HEADER_LEN = "format.header.len";

    protected static SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    protected SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

    protected PrintStream stdOutStream;
    protected PrintStream errOutStream;

    private boolean enabled = true;

    /**
     * /**
     * <ul>
     * <li>0: datetime
     * <li>1: thread name
     * <li>2: type
     * <li>3: class name (simple) + method name
     * </ul>
     */
    static final String LOG_FORMAT_HEADER = "[%s] (%5s) | %-5s | %-27s >\t";
    /**
     * <ul>
     * <li>0: log
     * </ul>
     */
    static final String LOG_FORMAT = LOG_FORMAT_HEADER + "%s";
    /**
     * <ul>
     * <li>0: log
     * <li>1: class name
     * <li>2: line number
     * </ul>
     */
    static final String LOG_FORMAT_TRACEABLE = LOG_FORMAT_HEADER + "%s (%s.java:%s)";

    /**
     * 
     */
    public AbstractLogger() {
        super();
    }

    /**
     * 로그를 기록한 이후에 진행되어야 하는 부분을 정의합니다.
     * 
     * @param stream
     */
    protected abstract void afterWrite(PrintStream stream);

    protected void close(AutoCloseable... autoCloseables) {
        for (AutoCloseable autoCloseable : autoCloseables) {
            try {
                if (autoCloseable != null) {
                    autoCloseable.close();
                }
            } catch (Exception ignored) {
                // ignore a Exception.
            }
        }
    }

    @Override
    public void entry() {
        if (!enabled) {
            return;
        }

        PrintStream stream = getStdOutStream();
        write(stream, getCustomStdLog("(entry)", 1));

        updateIndent(Thread.currentThread(), INDENT_INC);

        afterWrite(stream);
    }

    /**
     * 
     * @see open.commons.log.ILogger#entry(java.lang.Object)
     */
    @Override
    public void entry(Object log) {
        if (!enabled) {
            return;
        }

        PrintStream stream = getStdOutStream();
        write(stream, getCustomStdLog("(entry) " + log, 1));

        updateIndent(Thread.currentThread(), INDENT_INC);

        afterWrite(stream);
    }

    /**
     * 
     * @see open.commons.log.ILogger#entry(java.lang.String)
     */
    @Override
    public void entry(String log) {
        if (!enabled) {
            return;
        }

        PrintStream stream = getStdOutStream();
        write(stream, getCustomStdLog("(entry) " + log, 1));

        updateIndent(Thread.currentThread(), INDENT_INC);

        afterWrite(stream);
    }

    /**
     * 
     * @see open.commons.log.ILogger#error(java.lang.Object)
     */
    @Override
    public void error(Object log) {
        if (!enabled) {
            return;
        }

        PrintStream stream = getErrOutStream();
        String logStr = getCustomErrLog(log != null ? log.toString() : null, 1);

        write(stream, logStr);
    }

    /**
     * 
     * @see open.commons.log.ILogger#error(java.lang.Object, java.lang.Exception)
     */
    @Override
    public void error(Object log, Exception e) {
        if (!enabled) {
            return;
        }

        PrintStream stream = getErrOutStream();
        String logStr = getCustomErrLog(log != null ? log.toString() : null, e, 1);

        write(stream, logStr);
    }

    /**
     * 
     * @see open.commons.log.ILogger#error(java.lang.String)
     */
    @Override
    public void error(String log) {
        if (!enabled) {
            return;
        }

        PrintStream stream = getErrOutStream();
        log = getCustomErrLog(log, 1);

        write(stream, log);
    }

    /**
     * 
     * @see open.commons.log.ILogger#error(java.lang.String, java.lang.Exception)
     */
    @Override
    public void error(String log, Exception e) {
        if (!enabled) {
            return;
        }

        PrintStream stream = getErrOutStream();
        log = getCustomErrLog(log, e, 1);

        write(stream, log);
    }

    /**
     * 포맷화된 로그를 반영합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2013. 7. 3.	박준홍		최초 작성.
     * </pre>
     * 
     * @param type
     *            <ul>
     *            <li>{@link ILogger#TYPE_LOG}
     *            <li>{@link ILogger#TYPE_ERROR}
     *            </ul>
     * @param logMessage
     * @param exception
     * @param depth
     *            TODO
     * @return
     */
    protected final String format(String type, String logMessage, Exception exception, int depth) {

        Thread thread = Thread.currentThread();
        StackTraceElement stack = thread.getStackTrace()[2 + depth];

        String datetime = datetimeFormat.format(Calendar.getInstance().getTime());
        String threadName = thread.getName();
        String methodName = stack.getMethodName();
        String className = stack.getClassName();
        String lineNumber = Integer.toString(stack.getLineNumber());

        // String log = MessageFormat.format(LOG_FORMAT, datetime, type, threadName, methodName, indent(thread)
        // + logMessage, className, lineNumber);

        String logHeader = FormatUtils.format(LOG_FORMAT_HEADER, datetime, type, threadName, StringUtils.getLast(className, ".") + "." + methodName + "()");
        char[] cbuf = new char[logHeader.length() + indent(thread).length()];
        Arrays.fill(cbuf, ' ');

        // Exception StackTrace
        String stackTrace = LINE_SEPARATOR + StringUtils.rtrim(getExceptionStackTrace(new String(cbuf), exception));

        return FormatUtils.format(LOG_FORMAT_TRACEABLE, datetime, type, threadName, StringUtils.getLast(className, ".") + "." + methodName + "()",
                indent(thread) + logMessage + stackTrace, className, lineNumber);
    }

    /**
     * 포맷화된 로그를 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2013. 7. 2.  박준홍     최초 작성.
     * </pre>
     * 
     * @param type
     *            <ul>
     *            <li>{@link ILogger#TYPE_LOG}
     *            <li>{@link ILogger#TYPE_ERROR}
     *            </ul>
     * @param logMessage
     * @param depth
     *            로그를 찍으려는 메소드부터 이 메소드를 호출하는 메소드 사이의 호출 개수.
     * @return
     */
    protected final String format(String type, String logMessage, int depth) {

        Thread thread = Thread.currentThread();
        StackTraceElement stack = thread.getStackTrace()[2 + depth];

        String datetime = datetimeFormat.format(Calendar.getInstance().getTime());
        String threadName = thread.getName();
        String methodName = stack.getMethodName();
        String className = stack.getClassName();
        String lineNumber = Integer.toString(stack.getLineNumber());

        return FormatUtils.format(LOG_FORMAT_TRACEABLE, datetime, type, threadName, StringUtils.getLast(className, ".") + "." + methodName + "()", indent(thread) + logMessage,
                className, lineNumber);
    }

    protected abstract String getCustomErrLog(String log, Exception e, int depth);

    protected abstract String getCustomErrLog(String log, int depth);

    protected abstract String getCustomStdLog(String log, int depth);

    protected abstract PrintStream getErrOutStream();

    protected final String getExceptionStackTrace(Exception e) {
        StackTraceWriter stw = new StackTraceWriter();
        PrintWriter writer = new PrintWriter(stw);

        e.printStackTrace(writer);

        return stw.toString();
    }

    protected final String getExceptionStackTrace(String prefix, Exception e) {
        StackTraceWriter stw = new StackTraceWriter(prefix);
        PrintWriter writer = new PrintWriter(stw);

        e.printStackTrace(writer);

        return stw.toString();
    }

    protected abstract PrintStream getStdOutStream();

    @Override
    public void leave() {
        if (!enabled) {
            return;
        }

        updateIndent(Thread.currentThread(), INDENT_DEC);

        PrintStream stream = getStdOutStream();
        write(stream, getCustomStdLog("(leave) ", 1));

        afterWrite(stream);
    }

    /**
     * 
     * @see open.commons.log.ILogger#leave(java.lang.Object)
     */
    @Override
    public void leave(Object log) {
        if (!enabled) {
            return;
        }

        updateIndent(Thread.currentThread(), INDENT_DEC);

        PrintStream stream = getStdOutStream();
        write(stream, getCustomStdLog("(leave) " + log, 1));

        afterWrite(stream);
    }

    /**
     * 
     * @see open.commons.log.ILogger#leave(java.lang.String)
     */
    @Override
    public void leave(String log) {
        if (!enabled) {
            return;
        }

        updateIndent(Thread.currentThread(), INDENT_DEC);

        PrintStream stream = getStdOutStream();
        write(stream, getCustomStdLog("(leave) " + log, 1));

        afterWrite(stream);
    }

    /**
     * 
     * @see open.commons.log.ILogger#log(java.lang.Object)
     */
    @Override
    public void log(Object log) {
        if (!enabled) {
            return;
        }

        PrintStream stream = getStdOutStream();
        String logStr = getCustomStdLog(log != null ? log.toString() : null, 1);

        write(stream, logStr);
    }

    /**
     * 
     * @see open.commons.log.ILogger#log(java.lang.String)
     */
    @Override
    public void log(String log) {
        if (!enabled) {
            return;
        }

        PrintStream stream = getStdOutStream();
        log = getCustomStdLog(log, 1);

        write(stream, log);
    }

    /**
     * 
     * @see open.commons.log.ILogger#setEnabled(boolean)
     */
    @Override
    public ILogger setEnabled(boolean enabled) {
        this.enabled = enabled;

        return this;
    }

    /**
     * 
     * @see open.commons.log.ILogger#setErrOutStream(PrintStream)
     */
    @Override
    public void setErrOutStream(PrintStream errOutStream) {
        this.errOutStream = errOutStream;
    }

    /**
     * 
     * @see open.commons.log.ILogger#setStdOutStream(PrintStream)
     */
    @Override
    public void setStdOutStream(PrintStream stdOutStream) {
        this.stdOutStream = stdOutStream;
    }

    /**
     * 하위 클래스에서 생성한 {@link PrintStream}에 로그 내용을 기록합니다. <b>
     * 
     * 이 메소드를 이용하는 이유는 동일한 {@link ILogger}를 이용하는 대상들이 {@link Thread}로 구분되어 별도의 들여쓰기(Indentation)를 적용받기 위해서이다.
     * 
     * @param stream
     * @param log
     */
    protected void write(PrintStream stream, String log) {
        stream.println(log);

        afterWrite(stream);
    }

    private static String indent(Thread thread) {

        AtomicInteger indent = null;

        int length = 0;
        synchronized (indents) {
            indent = indents.get(thread);
            if (indent == null) {
                indent = new AtomicInteger();

                indents.put(thread, indent);

            } else {
                length = indent.intValue();
            }
        }

        return new String(INDENT_ARRAY, 0, length * 2);
    }

    protected static void registerTimer(Timer timer) {
        timers.add(timer);
    }

    static final void stop() {
        try {
            runner.interrupt();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Timer timer : timers) {
            timer.cancel();
        }
    }

    private static void updateIndent(Thread thread, int type) {

        AtomicInteger indent = null;

        synchronized (indents) {
            indent = indents.get(thread);
            if (indent == null) {
                indent = new AtomicInteger(0);

                indents.put(thread, indent);
            } else {
                switch (type) {
                    case INDENT_DEC:
                        if (indent.get() > 0) {
                            indent.decrementAndGet();
                        }
                        break;
                    case INDENT_INC:
                        if (indent.get() < INDENT_ALLOW) {
                            indent.incrementAndGet();
                        }
                        break;
                }
            }
        }
    }

    /**
     * 
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     */
    class StackTraceWriter extends StringWriter {
        private String prefix;

        public StackTraceWriter() {
            this("");
        }

        public StackTraceWriter(String prefix) {
            this.prefix = prefix;
        }

        /**
         * 
         * @see java.io.Writer#write(char[])
         */
        @Override
        public void write(char[] cbuf) throws IOException {
            super.write(this.prefix);
            super.write(cbuf);
        }

        /**
         * 
         * @see java.io.StringWriter#write(char[], int, int)
         */
        @Override
        public void write(char[] cbuf, int off, int len) {
            super.write(this.prefix);
            super.write(cbuf, off, len);
        }

        /**
         * 
         * @see java.io.StringWriter#write(int)
         */
        @Override
        public void write(int c) {
            super.write(this.prefix);
            super.write(c);
        }

        /**
         * 
         * @see java.io.StringWriter#write(java.lang.String)
         */
        @Override
        public void write(String str) {
            super.write(this.prefix);
            super.write(str);
        }

        /**
         * 
         * @see java.io.StringWriter#write(java.lang.String, int, int)
         */
        @Override
        public void write(String str, int off, int len) {
            super.write(this.prefix);
            super.write(str, off, len);
        }
    }
}