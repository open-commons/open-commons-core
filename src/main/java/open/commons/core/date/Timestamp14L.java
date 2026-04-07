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
* Date  : 2013. 6. 19. 오전 10:58:54
*
* Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
* 
*/

package open.commons.core.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jspecify.annotations.Nullable;

import open.commons.core.utils.StringUtils;

/**
 * <b>{@code NOT Thread-safe}</b>.
 * <p>
 * 
 * <b>Format</b>: yyyyMMddHHmmss
 * 
 * @since 2013. 6. 19.
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */
public class Timestamp14L implements Comparable<Timestamp14L> {

    // 아래 내용에 적용됨.
    // - Timestamp14L.class.getSimpleName()
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    private static final String CLASS = Timestamp14L.class.getSimpleName();

    private static final int DATEINFO_LENGTH = 6;

    public static final int YEAR = 0x00;
    public static final int MONTH = 0x01;
    public static final int DAY_OF_YEAR = 0x02;
    public static final int HOUR_OF_DAY = 0x03;
    public static final int MINUTE = 0x04;
    public static final int SECOND = 0x05;

    private static final int[] CONVERTOR = new int[] { Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH, Calendar.HOUR_OF_DAY, Calendar.MINUTE, Calendar.SECOND };

    private static final String FORMAT_YYYYMMddHHmmss = "yyyyMMddHHmmss";
    private static final String REGEX_YYYYMMddHHmmss = "(\\d{4})" // year
            + "(\\d{2})" // month
            + "(\\d{2})" // day
            + "(\\d{2})" // hour
            + "(\\d{2})" // min
            + "(\\d{2})" // sec
    ;

    private SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_YYYYMMddHHmmss);
    // 아래 내용에 적용됨.
    // - Pattern.compile(regex)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    private Pattern regexPattern = Pattern.compile(REGEX_YYYYMMddHHmmss);

    private String year = "0";
    private String month = "0";
    private String day = "0";
    private String hour = "0";
    private String min = "0";
    private String sec = "0";

    private Date date;

    /**
     * 현재 날짜 정보를 가지고 객체를 생성합니다.
     */
    public Timestamp14L() {
        this(Objects.requireNonNull( //
                Calendar.getInstance().getTime() //
        ));

    }

    /**
     * 
     * @param calendar
     * 
     * @throws NullPointerException
     *             파라미터({@code calender})가 {@code null}인 경우 발생.
     */
    public Timestamp14L(Calendar calendar) {
        this(Objects.requireNonNull( //
                calendar.getTime() //
        ));
    }

    /**
     * 
     * @param date
     *            날짜 객체
     * 
     * @throws NullPointerException
     *             파라미터({@code date})가 {@code null}인 경우 발생.
     */
    public Timestamp14L(Date date) {
        this.date = createDatetime(Objects.requireNonNull( //
                sdf.format(date) //
        ));
    }

    /**
     * 
     * @param datetime
     *            숫자로 이루어진 14자리 일시 정보
     * 
     * @throws NullPointerException
     *             파라미터({@code datetime})가 {@code null}인 경우 발생.
     */
    public Timestamp14L(String datetime) {
        Objects.requireNonNull(datetime);

        this.date = createDatetime(datetime);
    }

    /**
     * 
     * @see java.lang.Object#clone()
     */
    public Timestamp14L clone() {
        return new Timestamp14L(dateString());
    }

    /**
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(@Nullable Timestamp14L other) {
        int rtnValue = -1;

        if (other != null) {
            rtnValue = date.compareTo(other.getDate());
        }

        return rtnValue;
    }

    // 아래 내용에 적용됨.
    // - getCalendar().getTime();
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    private Date createDatetime(String datetime) {
        String[] dateinfo = match(datetime);

        this.year = dateinfo[YEAR];
        this.month = dateinfo[MONTH];
        this.day = dateinfo[DAY_OF_YEAR];
        this.hour = dateinfo[HOUR_OF_DAY];
        this.min = dateinfo[MINUTE];
        this.sec = dateinfo[SECOND];

        return getCalendar().getTime();
    }

    public String dateString() {
        return year + month + day + hour + min + sec;
    }

    /**
     * 현재 값과 입력받은 값의 차이를 반환합니다.<br>
     * 
     * @param other
     * 
     * @throws NullPointerException
     *             파라미터({@code other})가 {@code null}인 경우 발생.
     * 
     * @return
     */
    public TimeValue3L diff(Timestamp14L other) {
        Objects.requireNonNull(other);

        long millis = date.getTime() - other.getDate().getTime();

        int sign = 1;

        if (millis < 0) {
            sign = -1;
            millis *= -1;
        } else if (millis > 0) {
            sign = 1;
        }

        long s = TimeUnit.MILLISECONDS.toSeconds(millis);
        long m = TimeUnit.SECONDS.toMinutes(s);
        long h = TimeUnit.MINUTES.toHours(m);
        long d = TimeUnit.HOURS.toDays(h);

        StringBuilder tsString = new StringBuilder();

        // day
        tsString.append(d);
        // hour
        tsString.append(StringUtils.lpad(h % 24, 2));
        // minute
        tsString.append(StringUtils.lpad(m % 60, 2));
        // second
        tsString.append(StringUtils.lpad(s % 60, 2));

        return new TimeValue3L(Objects.requireNonNull( //
                tsString.toString() //
        ), sign);
    }

    /**
     *
     * @since 2026. 3. 16.
     * @version 3.0.0
     * 
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Timestamp14L other = (Timestamp14L) obj;
        return Objects.equals(day, other.day) && Objects.equals(hour, other.hour) && Objects.equals(min, other.min) && Objects.equals(month, other.month)
                && Objects.equals(sec, other.sec) && Objects.equals(year, other.year);
    }

    /**
     * 현재 시간정보를 {@link Calendar} 객체로 반환합니다.
     * 
     * @return
     */
    public Calendar getCalendar() {
        Calendar rtnCalendar = Calendar.getInstance();

        rtnCalendar.set(getYearInt() // year
                , getMonthInt() - 1 // month
                , getDayInt() // day of month
                , getHourInt() // hour
                , getMinInt() // minute
                , getSecInt() // second
        );

        rtnCalendar.set(Calendar.MILLISECOND, 0);

        return rtnCalendar;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @return the day
     */
    public String getDay() {
        return day;
    }

    /**
     * @return the day
     */
    public int getDayInt() {
        return Integer.parseInt(day);
    }

    /**
     * @return the hour
     */
    public String getHour() {
        return hour;
    }

    /**
     * @return the hour
     */
    public int getHourInt() {
        return Integer.parseInt(hour);
    }

    /**
     * @return the min
     */
    public String getMin() {
        return min;
    }

    /**
     * @return the min
     */
    public int getMinInt() {
        return Integer.parseInt(min);
    }

    /**
     * @return the month
     */
    public String getMonth() {
        return month;
    }

    /**
     * @return the month
     */
    public int getMonthInt() {
        return Integer.parseInt(month);
    }

    /**
     * @return the sec
     */
    public String getSec() {
        return sec;
    }

    /**
     * @return the sec
     */
    public int getSecInt() {
        return Integer.parseInt(sec);
    }

    /**
     * @return the year
     */
    public String getYear() {
        return year;
    }

    /**
     * @return the year
     */
    public int getYearInt() {
        return Integer.parseInt(year);
    }

    /**
     *
     * @since 2026. 3. 16.
     * @version 3.0.0
     * 
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(day, hour, min, month, sec, year);
    }

    private String[] match(String datetime) {

        Matcher m = regexPattern.matcher(datetime);

        if (m.matches() && m.groupCount() == DATEINFO_LENGTH) {
            String[] rtnValue = new String[DATEINFO_LENGTH];

            for (int i = 1; i < DATEINFO_LENGTH + 1; i++) {
                rtnValue[i - 1] = m.group(i);
            }

            return rtnValue;
        }

        throw new IllegalArgumentException("14자리로된 숫자 정보만 입력 가능합니다. datetime: " + datetime);
    }

    /**
     * 
     * @param datetime
     *            숫자로 이루어진 14자리 일시 정보
     * 
     * @throws NullPointerException
     *             파라미터({@code datetime})가 {@code null}인 경우 발생.
     */
    public void setDatetime(String datetime) {
        Objects.requireNonNull(datetime);

        this.date = createDatetime(datetime);
    }

    /**
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return CLASS + " [year=" + year + ", month=" + month + ", day=" + day + ", hour=" + hour + ", min=" + min + ", sec=" + sec + ", date: " + sdf.format(date) + ", millis: "
                + date.getTime() + "]";
    }

    /**
     * 
     * @param field
     *            one of followings.
     *            <ul>
     *            <li>{@link #YEAR}
     *            <li>{@link #MONTH}
     *            <li>{@link #DAY_OF_YEAR}
     *            <li>{@link #HOUR_OF_DAY}
     *            <li>{@link #MINUTE}
     *            <li>{@link #SECOND}
     *            </ul>
     * @param value
     * @return
     */
    public Timestamp14L update(int field, int value) {
        Timestamp14L oldTs = clone();

        if (field >= YEAR && field <= SECOND) {
            Calendar cal = getCalendar();
            cal.add(CONVERTOR[field], value);

            setDatetime(Objects.requireNonNull( //
                    sdf.format(cal.getTime()) //
            ));
        }

        return oldTs;
    }

    public static class TimeValue3L {
        // 아래 내용에 적용됨.
        // - TimeValue3L.class.getSimpleName()
        // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
        // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
        @SuppressWarnings("null")
        private static final String CLASS = TimeValue3L.class.getSimpleName();

        private static final String REGEX = "(\\d*)" // day
                + "([0-1]\\d{1}|2[0-4])" // hour
                + "([0-5]\\d{1})" // minute
                + "([0-5]\\d{1})" // second
        ;

        private static final int DATEINFO_LENGTH = 4;

        public static final int DAY_OF_YEAR = 0x00;
        public static final int HOUR_OF_DAY = 0x01;
        public static final int MINUTE = 0x02;
        public static final int SECOND = 0x03;

        // 아래 내용에 적용됨.
        // - Pattern.compile(regex)
        // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
        // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
        @SuppressWarnings("null")
        private final Pattern regexPattern = Pattern.compile(REGEX);

        private int sign = 0;

        private String day = "0";
        private String hour = "00";
        private String minute = "00";
        private String second = "00";

        public TimeValue3L(String timeValue, int sign) {
            setTimeValue(timeValue);

            this.sign = sign;
        }

        /**
         * 
         * @param day
         * @param hour
         * @param minute
         * @param second
         * @param sign
         *            TODO
         */
        public TimeValue3L(String day, String hour, String minute, String second, int sign) {
            if (StringUtils.isNullOrEmptyStringOr(day, hour, minute, second)) {
                throw new NullPointerException("입력값은 null 이 될 수 없습니다. day: " + day + ", hour: " + hour + ", minute: " + minute + ", second: " + second);
            }

            setTimeValue(day + hour + minute + second);

            this.sign = sign;
        }

        /**
         *
         * @since 2026. 3. 16.
         * @version 3.0.0
         * 
         *
         * @see java.lang.Object#equals(java.lang.Object)
         */
        @Override
        public boolean equals(@Nullable Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            TimeValue3L other = (TimeValue3L) obj;
            return Objects.equals(day, other.day) && Objects.equals(hour, other.hour) && Objects.equals(minute, other.minute) && Objects.equals(second, other.second);
        }

        /**
         * @return the day
         */
        public String getDay() {
            return day;
        }

        /**
         * @return the day
         */
        public int getDayInt() {
            return Integer.parseInt(day);
        }

        /**
         * @return the hour
         */
        public String getHour() {
            return hour;
        }

        /**
         * @return the hour
         */
        public int getHourInt() {
            return Integer.parseInt(hour);
        }

        /**
         * @return the minute
         */
        public String getMinute() {
            return minute;
        }

        /**
         * @return the minute
         */
        public int getMinuteInt() {
            return Integer.parseInt(minute);
        }

        /**
         * @return the second
         */
        public String getSecond() {
            return second;
        }

        /**
         * @return the second
         */
        public int getSecondInt() {
            return Integer.parseInt(second);
        }

        /**
         * 부호를 반환합니다.
         * 
         * @return
         */
        public int getSign() {
            return sign;
        }

        /**
         *
         * @since 2026. 3. 16.
         * @version 3.0.0
         * 
         *
         * @see java.lang.Object#hashCode()
         */
        @Override
        public int hashCode() {
            return Objects.hash(day, hour, minute, second);
        }

        private String @Nullable [] match(String datetime) {
            String[] rtnValue = null;

            Matcher m = regexPattern.matcher(datetime);

            if (m.matches() && m.groupCount() <= DATEINFO_LENGTH) {
                rtnValue = new String[DATEINFO_LENGTH];

                for (int i = 1; i < DATEINFO_LENGTH + 1; i++) {
                    rtnValue[i - 1] = m.group(i);
                }
            }

            return rtnValue;
        }

        /**
         * 
         * @param timeValue
         *            숫자로 이루어진 14자리 일시 정보
         */
        @SuppressWarnings("null")
        public void setTimeValue(String timeValue) {

            String @Nullable [] dateinfo = match(timeValue);

            if (dateinfo != null) {
                day = dateinfo[DAY_OF_YEAR] != null ? dateinfo[DAY_OF_YEAR] : "0";
                hour = dateinfo[HOUR_OF_DAY];
                minute = dateinfo[MINUTE];
                second = dateinfo[SECOND];

            } else {
                throw new IllegalArgumentException("6자리 이상으로 표현된 숫자 정보만 입력 가능합니다. timeValue: " + timeValue);
            }
        }

        /**
         * 
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return CLASS + " [day=" + day + ", hour=" + hour + ", minute=" + minute + ", second=" + second + "]";
        }
    }
}
