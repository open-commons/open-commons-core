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
* Date  : 2013. 7. 3. 오후 6:22:25
*
* Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
* 
*/
package open.commons.log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import open.commons.utils.IOUtils;

/**
 * 파일에 로그를 기록하는 클래스.
 * 
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */
public class FileLogger extends AbstractLogger {

    private String logName;
    private String logUrl;

    /** Latest LogFile Format */
    private String logSuffix;

    private File logFile;

    private LogFileDivideRule lfdr = LogFileDivideRule.NO_DIVIDE;

    public FileLogger(String logName) {
        this(".", logName, null);
    }

    public FileLogger(String logName, LogFileDivideRule rule) {
        this(".", logName, rule);
    }

    public FileLogger(String logUrl, String logName) {
        this(logUrl, logName, null);
    }

    public FileLogger(String logUrl, String logName, LogFileDivideRule rule) {
        this.logUrl = logUrl;
        this.logName = logName;

        if (rule != null && !LogFileDivideRule.NO_DIVIDE.equals(rule)) {
            this.lfdr = rule;
        }

        // check log_file path
        File logUrlPath = new File(logUrl);

        if (!logUrlPath.exists()) {
            logUrlPath.mkdirs();
        }

        checkLogFileFormat();
    }

    /**
     * 
     * @see open.commons.log.AbstractLogger#afterWrite(PrintStream)
     */
    @Override
    protected void afterWrite(PrintStream stream) {
        close(stream);
    }

    /**
     * 파일이 존재하는지 확인하며, 없는 경우 파일이 속한 디렉터리를 생성합니다.
     */
    private File checkLogFile() {

        switch (lfdr) {
            case SIZE_1G:
            case SIZE_512M:
            case SIZE_256M:
            case SIZE_128M:
            case SIZE_64M:
            case SIZE_32M:
            case SIZE_16M:
            case SIZE_8M:
            case SIZE_4M:
            case SIZE_1M:
            case SIZE_512K:

                if (logFile.exists()) {

                    RandomAccessFile raf = null;
                    try {
                        raf = new RandomAccessFile(logFile, "r");
                        if (raf.length() >= lfdr.getValue()) {
                            logSuffix = dateFormat.format(Calendar.getInstance().getTime());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        IOUtils.close(raf);
                    }
                }

                break;
            default:
                // No need to check each logging...
                break;
        }

        logFile = new File(logUrl, getLogFileName());

        return logFile;
    }

    private void checkLogFileFormat() {
        switch (lfdr) {
            case NO_DIVIDE:
            case DATE_DAY:

                dateFormat = new SimpleDateFormat("yyyyMMdd");

                break;
            case DATE_HALF_DAY:
            case DATE_QUARTER_DAY:
            case DATE_HOUR:

                dateFormat = new SimpleDateFormat("yyyyMMddHH");

                break;
            case DATE_HALF_HOUR:
            case DATE_QUARTER_HOUR:
            case DATE_10_MINUTES:
            case DATE_MINUTES:

                dateFormat = new SimpleDateFormat("yyyyMMddHHmm");

                break;
            case SIZE_1G:
            case SIZE_512M:
            case SIZE_256M:
            case SIZE_128M:
            case SIZE_64M:
            case SIZE_32M:
            case SIZE_16M:
            case SIZE_8M:
            case SIZE_4M:
            case SIZE_1M:
            case SIZE_512K:
                dateFormat = new SimpleDateFormat("yyyyMMddHHmmss.SSS");
                break;
        }

        Calendar calendar = Calendar.getInstance();
        logSuffix = dateFormat.format(calendar.getTime());

        logFile = new File(logUrl, getLogFileName());

        if (lfdr.isDividedByDate()) {
            Timer timer = new Timer(true);
            Date firstTime = null;

            switch (lfdr) {
                case DATE_DAY:
                    calendar.add(Calendar.DAY_OF_YEAR, 1);

                    calendar.set(Calendar.HOUR_OF_DAY, 0);
                    calendar.set(Calendar.MINUTE, 0);
                    calendar.set(Calendar.SECOND, 0);
                    calendar.set(Calendar.MILLISECOND, 0);

                    firstTime = calendar.getTime();
                    break;
                case DATE_HALF_DAY:
                    calendar.add(Calendar.HOUR_OF_DAY, 12);

                    calendar.set(Calendar.MINUTE, 0);
                    calendar.set(Calendar.SECOND, 0);
                    calendar.set(Calendar.MILLISECOND, 0);

                    firstTime = calendar.getTime();
                    break;
                case DATE_QUARTER_DAY:
                    calendar.add(Calendar.HOUR_OF_DAY, 6);

                    calendar.set(Calendar.MINUTE, 0);
                    calendar.set(Calendar.SECOND, 0);
                    calendar.set(Calendar.MILLISECOND, 0);

                    firstTime = calendar.getTime();

                    break;
                case DATE_HOUR:
                    calendar.add(Calendar.HOUR_OF_DAY, 1);

                    calendar.set(Calendar.MINUTE, 0);
                    calendar.set(Calendar.SECOND, 0);
                    calendar.set(Calendar.MILLISECOND, 0);

                    firstTime = calendar.getTime();

                    break;
                case DATE_HALF_HOUR:
                    calendar.add(Calendar.MINUTE, 30);

                    calendar.set(Calendar.SECOND, 0);
                    calendar.set(Calendar.MILLISECOND, 0);

                    firstTime = calendar.getTime();

                    break;
                case DATE_QUARTER_HOUR:
                    calendar.add(Calendar.MINUTE, 15);

                    calendar.set(Calendar.SECOND, 0);
                    calendar.set(Calendar.MILLISECOND, 0);

                    firstTime = calendar.getTime();

                    break;
                case DATE_10_MINUTES:
                    calendar.add(Calendar.MINUTE, 10);

                    calendar.set(Calendar.SECOND, 0);
                    calendar.set(Calendar.MILLISECOND, 0);

                    firstTime = calendar.getTime();

                    break;
                case DATE_MINUTES:
                    calendar.add(Calendar.MINUTE, 1);

                    calendar.set(Calendar.SECOND, 0);
                    calendar.set(Calendar.MILLISECOND, 0);

                    firstTime = calendar.getTime();

                    break;
                default:
                    // ignore others.
                    break;
            }

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    FileLogger.this.logSuffix = FileLogger.this.dateFormat.format(Calendar.getInstance().getTime());
                }
            }, firstTime, lfdr.getValue());
        }
    }

    /**
     * 
     * @see open.commons.log.AbstractLogger#getCustomErrLog(java.lang.String, java.lang.Exception, int)
     */
    @Override
    protected String getCustomErrLog(String log, Exception e, int depth) {
        return format(TYPE_LOG, log, e, 1 + depth);
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
        try {
            return errOutStream = new PrintStream(new FileOutputStream(checkLogFile(), true));
        } catch (FileNotFoundException ignored) {
            errOutStream = null;
        }
        return errOutStream;
    }

    private String getLogFileName() {
        StringBuffer logFileName = new StringBuffer();
        logFileName.append(logName);
        logFileName.append('_');
        logFileName.append(logSuffix);
        logFileName.append(".log");

        return logFileName.toString();
    }

    /**
     * 
     * @see open.commons.log.AbstractLogger#getStdOutStream()
     */
    @Override
    protected PrintStream getStdOutStream() {
        try {
            return stdOutStream = new PrintStream(new FileOutputStream(checkLogFile(), true));
        } catch (FileNotFoundException ignored) {
            stdOutStream = null;
        }
        return stdOutStream;
    }

    /**
     * 
     * @see open.commons.log.AbstractLogger#setErrOutStream(java.io.PrintStream)
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

    public static enum LogFileDivideRule {
        NO_DIVIDE("none", 0) // 분리 없음
        , DATE_DAY("date", 24 * 60 * 60 * 1000) // 하루 단위
        , DATE_HALF_DAY("date", 12 * 60 * 60 * 1000) // 12시간 단위
        , DATE_QUARTER_DAY("date", 6 * 60 * 60 * 1000) // 6시간 단위
        , DATE_HOUR("date", 60 * 60 * 1000)// 1시간 단위
        , DATE_HALF_HOUR("date", 30 * 60 * 1000)// 30분 단위
        , DATE_QUARTER_HOUR("date", 15 * 60 * 1000)// 15분 단위
        , DATE_10_MINUTES("date", 10 * 60 * 1000) // 10분 단위
        , DATE_MINUTES("date", 60 * 1000)// 1분 단위
        //
        , SIZE_1G("size", 1024 * 1024 * 1024) // 1GB 단위
        , SIZE_512M("size", 512 * 1024 * 1024) // 512MB 단위
        , SIZE_256M("size", 256 * 1024 * 1024)// 256MB 단위
        , SIZE_128M("size", 128 * 1024 * 1024) // 128MB 단위
        , SIZE_64M("size", 64 * 1024 * 1024) // 64MB 단위
        , SIZE_32M("size", 32 * 1024 * 1024) // 32MB 단위
        , SIZE_16M("size", 16 * 1024 * 1024) // 16MB 단위
        , SIZE_8M("size", 8 * 1024 * 1024)// 8MB 단위
        , SIZE_4M("size", 4 * 1024 * 1024)// 4MB 단위
        , SIZE_1M("size", 1 * 1024 * 1024)// 1MB 단위
        , SIZE_512K("size", 512 * 1024)// 512KB 단위
        ;

        private String type;
        private long value;

        private LogFileDivideRule(String type, long value) {
            this.type = type;
            this.value = value;
        }

        /**
         * @return the type
         */
        public String getType() {
            return type;
        }

        /**
         * @return the value
         */
        public long getValue() {
            return value;
        }

        public boolean isDividedByDate() {
            return "date".equals(type);
        }

        public boolean isDividedBySize() {
            return "size".equals(type);
        }

        public boolean isDividedType() {
            return !this.equals(NO_DIVIDE);
        }

        /**
         * 
         * @see java.lang.Enum#toString()
         */
        @Override
        public String toString() {
            return LogFileDivideRule.class.getSimpleName() + "[type=" + type + ", value=" + value + "]";
        }
    }
}