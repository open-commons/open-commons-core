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

/**
 * 
 */
package open.commons.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <b><code>NOT Thread-safe</code></b>.
 * <p>
 * 
 * <b>Format</b>: yyyyMMdd
 * 
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */
public class Timestamp8L implements Comparable<Timestamp8L> {

    private static final String CLASS = Timestamp8L.class.getSimpleName();

    private static final String format = "yyyyMMdd";
    private static final int DATEINFO_LENGTH = 3;
    private static final String regex = "(\\d{4})" // year
            + "(\\d{2})" // month
            + "(\\d{2})" // day
    ;

    public static final int YEAR = 0x00;
    public static final int MONTH = 0x01;
    public static final int DAY_OF_YEAR = 0x02;

    private static final int[] CONVERTOR = new int[] { Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH, };

    private SimpleDateFormat sdf = new SimpleDateFormat(format);

    private Pattern regexPattern = Pattern.compile(regex);

    private String year = "0";

    private String month = "0";

    private String day = "0";

    private Date date;

    /**
     * 현재 날짜 정보를 가지고 객체를 생성한다.
     */
    public Timestamp8L() {
        this(Calendar.getInstance().getTime());

    }

    /**
     * 
     * @param calendar
     */
    public Timestamp8L(Calendar calendar) {
        setDatetime(sdf.format(calendar.getTime()));
    }

    /**
     * 
     * @param date
     *            날짜 객체
     */
    public Timestamp8L(Date date) {
        setDatetime(sdf.format(date));
    }

    /**
     * 
     * @param datetime
     *            숫자로 이루어진 14자리 일시 정보
     */
    public Timestamp8L(String datetime) {
        setDatetime(datetime);
    }

    /**
     * 
     * @see java.lang.Object#clone()
     */
    public Timestamp8L clone() {
        return new Timestamp8L(dateString());
    }

    /**
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(Timestamp8L other) {
        int rtnValue = -1;

        if (other != null) {
            rtnValue = date.compareTo(other.getDate());
        }

        return rtnValue;
    }

    public String dateString() {
        return year + month + day;
    }

    /**
     * 
     * @param other
     * @return
     */
    public long diff(Timestamp8L other) {
        long millis = date.getTime() - other.getDate().getTime();

        return TimeUnit.MILLISECONDS.toDays(millis);
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
        Timestamp8L other = (Timestamp8L) obj;
        if (day == null) {
            if (other.day != null)
                return false;
        } else if (!day.equals(other.day))
            return false;
        if (month == null) {
            if (other.month != null)
                return false;
        } else if (!month.equals(other.month))
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
                , 0 // hour
                , 0 // minute
                , 0 // second
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
        result = prime * result + ((month == null) ? 0 : month.hashCode());
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
     *            숫자로 이루어진 8자리 일시 정보
     */
    public void setDatetime(String datetime) {

        String[] dateinfo = match(datetime);

        if (dateinfo != null) {
            year = dateinfo[YEAR];
            month = dateinfo[MONTH];
            day = dateinfo[DAY_OF_YEAR];

            setDate(getCalendar());

        } else {
            throw new IllegalArgumentException("8자리로된 숫자 정보만 입력 가능합니다. datetime: " + datetime);
        }
    }

    /**
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return CLASS + " [year=" + year + ", month=" + month + ", day=" + day + ", date: " + sdf.format(date) + ", millis: " + date.getTime() + "]";
    }

    /**
     * 
     * @param field
     *            one of followings.
     *            <ul>
     *            <li>{@link #YEAR}
     *            <li>{@link #MONTH}
     *            <li>{@link #DAY_OF_YEAR}
     *            </ul>
     * @param value
     * @return
     */
    public Timestamp8L update(int field, int value) {
        Timestamp8L oldTs = clone();

        if (field >= YEAR && field <= DAY_OF_YEAR) {
            Calendar cal = getCalendar();
            cal.add(CONVERTOR[field], value);

            setDatetime(sdf.format(cal.getTime()));
        }

        return oldTs;
    }
}
