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

/**
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

/**
 * <b>{@code NOT Thread-safe}</b>.
 * <p>
 * 
 * <b>Format</b>: yyyyMMdd
 * 
 * @since 2013. 6. 19.
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
public class Timestamp8L implements Comparable<Timestamp8L> {

    // 아래 내용에 적용됨.
    // - Timestamp8L.class.getSimpleName()
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    private static final String CLASS = Timestamp8L.class.getSimpleName();

    private static final int DATEINFO_LENGTH = 3;

    public static final int YEAR = 0x00;
    public static final int MONTH = 0x01;
    public static final int DAY_OF_YEAR = 0x02;

    private static final int[] CONVERTOR = new int[] { Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH, };

    private static final String FORMAT_YYYYMMdd = "yyyyMMdd";
    private static final String REGEX_YYYYMMdd = "(\\d{4})" // year
            + "(\\d{2})" // month
            + "(\\d{2})" // day
    ;

    private SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_YYYYMMdd);
    // 아래 내용에 적용됨.
    // - Pattern.compile(regex)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    private Pattern regexPattern = Pattern.compile(REGEX_YYYYMMdd);

    private String year = "0";

    private String month = "0";

    private String day = "0";

    private Date date;

    /**
     * 현재 날짜 정보를 가지고 객체를 생성합니다.
     */
    public Timestamp8L() {
        this(Objects.requireNonNull( //
                Calendar.getInstance().getTime() //
        ));

    }

    /**
     * 
     * @param calendar
     */
    public Timestamp8L(Calendar calendar) {
        this(Objects.requireNonNull( //
                calendar.getTime() //
        ));
    }

    /**
     * 
     * @param date
     *            날짜 객체
     */
    public Timestamp8L(Date date) {
        this.date = createDatetime(Objects.requireNonNull( //
                sdf.format(date) //
        ));
    }

    /**
     * 
     * @param datetime
     *            숫자로 이루어진 14자리 일시 정보
     */
    public Timestamp8L(String datetime) {
        Objects.requireNonNull(datetime);

        this.date = createDatetime(datetime);
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
    public int compareTo(@Nullable Timestamp8L other) {
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

        year = dateinfo[YEAR];
        month = dateinfo[MONTH];
        day = dateinfo[DAY_OF_YEAR];

        return getCalendar().getTime();
    }

    public String dateString() {
        return year + month + day;
    }

    /**
     * 
     * @param other
     * 
     * @return
     */
    public long diff(Timestamp8L other) {
        Objects.requireNonNull(other);

        long millis = date.getTime() - other.getDate().getTime();

        return TimeUnit.MILLISECONDS.toDays(millis);
    }

    /**
     *
     * @since 2026. 3. 16.
     * @version 3.0.0
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
        Timestamp8L other = (Timestamp8L) obj;
        return Objects.equals(day, other.day) && Objects.equals(month, other.month) && Objects.equals(year, other.year);
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
     * @since 2026. 3. 16.
     * @version 3.0.0
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(day, month, year);
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
     *            숫자로 이루어진 8자리 일시 정보
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

            setDatetime(Objects.requireNonNull( //
                    sdf.format(cal.getTime()) //
            ));
        }

        return oldTs;
    }
}
