/*
 * Copyright 2011 Park Jun-Hong_(fafanmama_at_naver_com)
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
* Author: Park_Jun_Hong_(fafanmama_at_naver_com)
* 
*/

package open.commons.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import open.commons.utils.StringUtils;

/**
 * <b><code>NOT Thread-safe</code></b>.
 * <p>
 * 
 * <b>Format</b>: yyyyMMddHHmmss
 * 
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */
public class Timestamp14L implements Comparable<Timestamp14L> {

    private static final String CLASS = Timestamp14L.class.getSimpleName();

    private static final String format = "yyyyMMddHHmmss";
    private static final int DATEINFO_LENGTH = 6;
    private static final String regex = "(\\d{4})" // year
            + "(\\d{2})" // month
            + "(\\d{2})" // day
            + "(\\d{2})" // hour
            + "(\\d{2})" // min
            + "(\\d{2})" // sec
    ;

    public static final int YEAR = 0x00;
    public static final int MONTH = 0x01;
    public static final int DAY_OF_YEAR = 0x02;
    public static final int HOUR_OF_DAY = 0x03;
    public static final int MINUTE = 0x04;
    public static final int SECOND = 0x05;

    private static final int[] CONVERTOR = new int[] { Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH, Calendar.HOUR_OF_DAY, Calendar.MINUTE, Calendar.SECOND };

    private SimpleDateFormat sdf = new SimpleDateFormat(format);

    private Pattern regexPattern = Pattern.compile(regex);

    private String year = "0";

    private String month = "0";

    private String day = "0";

    private String hour = "0";

    private String min = "0";

    private String sec = "0";

    private Date date;

    /**
     * 현재 날짜 정보를 가지고 객체를 생성한다.
     */
    public Timestamp14L() {
        this(Calendar.getInstance().getTime());

    }

    /**
     * 
     * @param calendar
     */
    public Timestamp14L(Calendar calendar) {
        setDatetime(sdf.format(calendar.getTime()));
    }

    /**
     * 
     * @param date
     *            날짜 객체
     */
    public Timestamp14L(Date date) {
        setDatetime(sdf.format(date));
    }

    /**
     * 
     * @param datetime
     *            숫자로 이루어진 14자리 일시 정보
     */
    public Timestamp14L(String datetime) {
        setDatetime(datetime);
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
    public int compareTo(Timestamp14L other) {
        int rtnValue = -1;

        if (other != null) {
            rtnValue = date.compareTo(other.getDate());
        }

        return rtnValue;
    }

    public String dateString() {
        return year + month + day + hour + min + sec;
    }

    /**
     * 현재 값과 입력받은 값의 차이를 반환한다.<br>
     * 
     * @param other
     * @return
     */
    public TimeValue3L diff(Timestamp14L other) {
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

        StringBuffer tsString = new StringBuffer();

        // day
        tsString.append(d);
        // hour
        tsString.append(StringUtils.lpad(h % 24, 2));
        // minute
        tsString.append(StringUtils.lpad(m % 60, 2));
        // second
        tsString.append(StringUtils.lpad(s % 60, 2));

        return new TimeValue3L(tsString.toString(), sign);
    }

    /**
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Timestamp14L other = (Timestamp14L) obj;
        if (day == null) {
            if (other.day != null)
                return false;
        } else if (!day.equals(other.day))
            return false;
        if (hour == null) {
            if (other.hour != null)
                return false;
        } else if (!hour.equals(other.hour))
            return false;
        if (min == null) {
            if (other.min != null)
                return false;
        } else if (!min.equals(other.min))
            return false;
        if (month == null) {
            if (other.month != null)
                return false;
        } else if (!month.equals(other.month))
            return false;
        if (sec == null) {
            if (other.sec != null)
                return false;
        } else if (!sec.equals(other.sec))
            return false;
        if (year == null) {
            if (other.year != null)
                return false;
        } else if (!year.equals(other.year))
            return false;
        return true;
    }

    /**
     * 현재 시간정보를 {@link Calendar} 객체로 반환한다.
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
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((day == null) ? 0 : day.hashCode());
        result = prime * result + ((hour == null) ? 0 : hour.hashCode());
        result = prime * result + ((min == null) ? 0 : min.hashCode());
        result = prime * result + ((month == null) ? 0 : month.hashCode());
        result = prime * result + ((sec == null) ? 0 : sec.hashCode());
        result = prime * result + ((year == null) ? 0 : year.hashCode());
        return result;
    }

    private String[] match(String datetime) {
        String[] rtnValue = null;

        Matcher m = regexPattern.matcher(datetime);

        if (m.matches() && m.groupCount() == DATEINFO_LENGTH) {
            rtnValue = new String[DATEINFO_LENGTH];

            for (int i = 1; i < DATEINFO_LENGTH + 1; i++) {
                rtnValue[i - 1] = m.group(i);
            }
        }

        return rtnValue;
    }

    private void setDate(Calendar calendar) {
        this.date = calendar.getTime();
    }

    /**
     * 
     * @param datetime
     *            숫자로 이루어진 14자리 일시 정보
     */
    public void setDatetime(String datetime) {

        String[] dateinfo = match(datetime);

        if (dateinfo != null) {
            year = dateinfo[YEAR];
            month = dateinfo[MONTH];
            day = dateinfo[DAY_OF_YEAR];
            hour = dateinfo[HOUR_OF_DAY];
            min = dateinfo[MINUTE];
            sec = dateinfo[SECOND];

            setDate(getCalendar());

        } else {
            throw new IllegalArgumentException("14자리로된 숫자 정보만 입력 가능합니다. datetime: " + datetime);
        }
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

            setDatetime(sdf.format(cal.getTime()));
        }

        return oldTs;
    }

    public static class TimeValue3L {
        private static final String CLASS = TimeValue3L.class.getSimpleName();

        private static final String regex = "(\\d*)" // day
                + "([0-1]\\d{1}|2[0-4])" // hour
                + "([0-5]\\d{1})" // minute
                + "([0-5]\\d{1})" // second
        ;

        private static final int DATEINFO_LENGTH = 4;

        public static final int DAY_OF_YEAR = 0x00;
        public static final int HOUR_OF_DAY = 0x01;
        public static final int MINUTE = 0x02;
        public static final int SECOND = 0x03;

        private final Pattern regexPattern = Pattern.compile(regex);

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
         * @see java.lang.Object#equals(java.lang.Object)
         */
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            TimeValue3L other = (TimeValue3L) obj;
            if (day == null) {
                if (other.day != null)
                    return false;
            } else if (!day.equals(other.day))
                return false;
            if (hour == null) {
                if (other.hour != null)
                    return false;
            } else if (!hour.equals(other.hour))
                return false;
            if (minute == null) {
                if (other.minute != null)
                    return false;
            } else if (!minute.equals(other.minute))
                return false;
            if (second == null) {
                if (other.second != null)
                    return false;
            } else if (!second.equals(other.second))
                return false;
            return true;
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
         * 부호를 반환한다.
         * 
         * @return
         */
        public int getSign() {
            return sign;
        }

        /**
         * 
         * @see java.lang.Object#hashCode()
         */
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((day == null) ? 0 : day.hashCode());
            result = prime * result + ((hour == null) ? 0 : hour.hashCode());
            result = prime * result + ((minute == null) ? 0 : minute.hashCode());
            result = prime * result + ((second == null) ? 0 : second.hashCode());
            return result;
        }

        private String[] match(String datetime) {
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
        public void setTimeValue(String timeValue) {

            String[] dateinfo = match(timeValue);

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
