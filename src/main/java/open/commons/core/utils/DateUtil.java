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

package open.commons.core.utils;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import open.commons.core.TwoValueObject;
import open.commons.core.date.YearMonthDay;

/**
 * <br>
 * 
 * <pre>
 * [개정이력]
 *      날짜    	| 작성자	|	내용
 * ------------------------------------------
 * 2011. 7. 12.         박준홍        최초 작성
 * 2023. 11. 10.		박준홍			static 으로 사용하던 {@link SimpleDateFormat} 제거
 * </pre>
 * 
 * @since 2011. 07. 12.
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
public class DateUtil {

    public static final String REGEX_yyyyMMDD_HHmmss = "(\\d{4})\\s*.\\s*(\\d{1,2})\\s*.\\s*(\\d{1,2})\\s*\\s?\\s*(\\d{1,2})\\s*.\\s*(\\d{1,2})\\s*.\\s*(\\d{1,2}).?";
    public static final String REGEX_yyyyMMDD_HHmm = "(\\d{4})\\s*.\\s*(\\d{1,2})\\s*.\\s*(\\d{1,2})\\s*.\\s*(\\d{1,2})\\s*.\\s*(\\d{1,2}).?";
    public static final String REGEX_yyyyMMDD = "(\\d{4})\\s*.\\s*(\\d{1,2})\\s*.\\s*(\\d{1,2}).?";

    public static final String REGEX_ISO_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";
    public static final String REGEX_ISO_FORMAT_NO_TZ = "yyyy-MM-dd'T'HH:mm:ss";

    public static final String MESSAGE_ISO_FORMAT_NO_TZ = "{0}-{1}-{3}T{4}:{5}:{6}";

    /**
     * ISO 8601 표기
     * 
     * @since 1.8.0
     */
    public static final String REGEX_ISO_8601_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSX";
    /**
     * 
     * ISO 8601 표기
     * 
     * @since 1.8.0
     */
    public static final SimpleDateFormat ISO_8601_FORMAT = new SimpleDateFormat(REGEX_ISO_8601_FORMAT);

    /**
     * 포맷: {@value #REGEX_yyyyMMDD}
     * 
     * @see #REGEX_yyyyMMDD
     */
    public static final Pattern yyyyMMDD = Pattern.compile(REGEX_yyyyMMDD);

    /**
     * 포맷: {@value #REGEX_yyyyMMDD_HHmm}
     * 
     * @see #REGEX_yyyyMMDD_HHmm
     */
    public static final Pattern yyyyMMDD_HHmm = Pattern.compile(REGEX_yyyyMMDD_HHmm);

    /**
     * 포맷: {@value #REGEX_yyyyMMDD_HHmmss}
     * 
     * @see #REGEX_yyyyMMDD_HHmmss
     */
    public static final Pattern yyyyMMDD_HHmmss = Pattern.compile(REGEX_yyyyMMDD_HHmmss);

    /** 날짜를 구성하는 문자형 데이터를 정수형 데이터로 제공합니다. */
    private static final Function<String, Integer> CAL_S2I = s -> s != null ? Integer.parseInt(s) : 0;

    /**
     * 주어진 시간이 경과한 후에 현재 시간 이후인지 여부를 제공합니다.
     * 
     * @param timestamp
     *            비교하려는 시간
     * @param calenderField
     *            경과 단위
     * @param value
     *            값
     * @return
     *
     * @since 2017. 1. 9.
     */
    public static boolean afterNow(long timestamp, int calenderField, int value) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestamp);
        cal.add(calenderField, value);

        return cal.getTimeInMillis() > System.currentTimeMillis();
    }

    /**
     * 주어진 시간이 경과한 후에 현재 시간 이전인지 여부를 제공합니다.
     * 
     * @param timestamp
     *            비교하려는 시간
     * @param calenderField
     *            경과 단위
     * @param value
     *            값
     * @return
     *
     * @since 2017. 1. 9.
     */
    public static boolean beforeNow(long timestamp, int calenderField, int value) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestamp);
        cal.add(calenderField, value);

        return cal.getTimeInMillis() < System.currentTimeMillis();
    }

    /**
     * 비교하려는 날짜 문자열이 날짜 구간의 어느 부분에 속하는지를 반환합니다.
     * 
     * @param date
     * @param sDate
     * @param eDate
     * @return
     *         <ul>
     *         <li>음수: 시작날짜 이하
     *         <li>0: 시작구간 초과 종료구간 이하
     *         <li>양수: 종료구간 초과
     *         </ul>
     */
    public static int compare(String date, String sDate, String eDate) {

        int resultValue = date.compareTo(sDate);

        if (resultValue <= 0) {
            return -1;
        }

        resultValue = date.compareTo(eDate);

        if (resultValue <= 0) {
            return 0;
        }

        return 1;
    }

    /**
     * 
     * @param value
     * @param timeunut
     * @return
     */
    public static TwoValueObject<Integer, Integer> convertToUpperTimeUnit(long value, TimeUnit upTimeunit) {

        return null;
    }

    /**
     * 날짜수 차이를 반환합니다. <br>
     * 
     * @param cal1
     * @param cal2
     * @return 양수인 경우 첫번째 날짜가 크고, 음수인 경우 두번째 날짜가 큰 경우이다.
     * 
     * @since 2014. 4. 2.
     */
    public static int diffDay(Calendar cal1, Calendar cal2) {
        return diffDay0(newCalendar(cal1, Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH), newCalendar(cal2, Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH));

    }

    /**
     * 날짜수 차이를 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2014. 4. 2.		박준홍			최초 작성
     * </pre>
     *
     * @param date1
     * @param date2
     * @return
     *
     * @since 2014. 4. 2.
     */
    public static int diffDay(Date date1, Date date2) {
        return diffDay0(newCalendar(date1), newCalendar(date2));
    }

    /**
     * 주어진 시간과 오늘과의 일수 차이를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 10. 26.		박준홍			최초 작성
     * </pre>
     *
     * @param y1
     * @param m1
     *            월 (0 ~ 11)
     * @param d1
     * @param y2
     * @param m2
     *            월 (0 ~ 11)
     * @param d2
     * @return
     *
     * @since 2022. 10. 26.
     * @version 2.0.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static int diffDay(int y1, int m1, int d1, int y2, int m2, int d2) {
        return diffDay0(newCalendar(y1, m1, d1), newCalendar(y2, m2, d2));
    }

    /**
     * 주어진 시간과 오늘과의 일수 차이를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 10. 26.		박준홍			최초 작성
     * </pre>
     *
     * @param y1
     * @param m1
     *            월 (0 ~ 11)
     * @param d1
     * @param y2
     * @param m2
     *            월 (0 ~ 11)
     * @param d2
     * @return
     *
     * @since 2022. 10. 26.
     * @version 2.0.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static int diffDay(String y1, String m1, String d1, String y2, String m2, String d2) {
        return diffDay0(newCalendar(y1, m1, d1), newCalendar(y2, m2, d2));
    }

    /**
     * 두 날짜의 일수 차이를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 10. 26.		박준홍			최초 작성
     * </pre>
     *
     * @param cal1
     * @param cal2
     * 
     * @return 양수인 경우 첫번째 날짜가 크고, 음수인 경우 두번째 날짜가 큰 경우이다.
     *
     * @since 2022. 10. 26.
     * @version 2.0.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    private static int diffDay0(Calendar cal1, Calendar cal2) {
        long time1 = cal1.getTimeInMillis();
        long time2 = cal2.getTimeInMillis();

        if (time1 == time2) {
            return 0;
        }

        boolean sig = time1 > time2 ? true : false;

        long diff = Math.abs(time1 - time2);

        return (int) ((sig ? 1 : -1) * TimeUnit.MILLISECONDS.toDays(diff));
    }

    /**
     * 주어진 시간과 오늘과의 일수 차이를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 10. 26.		박준홍			최초 작성
     * </pre>
     *
     * @param cal
     * @return
     *
     * @since 2022. 10. 26.
     * @version 2.0.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static int diffDayToNow(Calendar cal) {
        return diffDay(cal, Calendar.getInstance());
    }

    /**
     * 주어진 시간과 오늘과의 일수 차이를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 10. 26.		박준홍			최초 작성
     * </pre>
     *
     * @param date
     * @return
     *
     * @since 2022. 10. 26.
     * @version 2.0.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static int diffDayToNow(Date date) {
        return diffDay0(newCalendar(date), Calendar.getInstance());
    }

    /**
     * 주어진 시간과 오늘과의 일수 차이를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 10. 26.		박준홍			최초 작성
     * </pre>
     *
     * @param year
     * @param month
     *            월 (0 ~ 11)
     * @param day
     * @return
     *
     * @since 2022. 10. 26.
     * @version 2.0.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static int diffDayToNow(int year, int month, int day) {
        return diffDay0(newCalendar(year, month, day), Calendar.getInstance());
    }

    /**
     * 주어진 시간과 오늘과의 일수 차이를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 10. 26.		박준홍			최초 작성
     * </pre>
     *
     * @param year
     * @param month
     *            월 (0 ~ 11)
     * @param day
     * @return
     *
     * @since 2022. 10. 26.
     * @version 2.0.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static int diffDayToNow(String year, String month, String day) {
        return diffDay0(newCalendar(year, month, day), Calendar.getInstance());
    }

    /**
     * 현재 시간을 기준으로 주어진 정보에 맞게 변경된 정보를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 9. 10.		박준홍			최초 작성
     * </pre>
     *
     * @param field
     * @param amount
     * @return
     *
     * @since 2020. 9. 10.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static Calendar getCalendar(int field, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.add(field, amount);
        return cal;
    }

    /**
     * 현재 시간을 기준으로 주어진 정보에 맞게 변경된 정보를 제공합니다. <br>
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 9. 10.		박준홍			최초 작성
     * </pre>
     *
     * @param field
     * @param amount
     * @return
     *
     * @since 2020. 9. 10.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static Date getDate(int field, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.add(field, amount);
        return cal.getTime();
    }

    /**
     * 현재 날짜의 년월일 정보를 반환합니다.<br>
     * 예) 현재 날짜가 2011년 1월 1일인 경우 "20110101'
     * 
     * @return
     */
    public static String getDateString() {
        return getDateString(new Date());
    }

    /**
     * 날짜의 년월일 정보를 반환합니다.<br>
     * 예) 현재 날짜가 2011년 1월 1일인 경우 "20110101'
     * 
     * @return
     */
    public static String getDateString(Date date) {
        return new SimpleDateFormat("yyyyMMdd").format(date);
    }

    /**
     * 현재 날짜의 년월일 정보를 반환합니다.<br>
     * 예) 현재 날짜가 2011년 1월 1일 1시 1분 1초인 경우 "20110101010101'
     * 
     * @return
     */
    public static String getDateTimeString() {
        return getDateTimeString(new Date());
    }

    /**
     * 날짜의 년월일 정보를 반환합니다.<br>
     * 예) 날짜가 2011년 1월 1일 1시 1분 1초인 경우 "20110101010101'
     * 
     * @return
     */
    public static String getDateTimeString(Date date) {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(date);
    }

    /**
     * 현재 날짜의 년월일 정보를 반환합니다.<br>
     * 예) 현재 날짜가 2011년 1월 1일인 경우 "01'
     * 
     * @return
     */
    public static String getDay() {
        return getDay(new Date());
    }

    /**
     * 현재 날짜의 년월일 정보를 반환합니다.<br>
     * 예) 현재 날짜가 2011년 1월 1일인 경우 "01'
     * 
     * @return
     */
    public static String getDay(Date date) {
        return new SimpleDateFormat("dd").format(date);
    }

    /**
     * 두 날짜 사이의 요일을 반환합니다.<br>
     * 두 날짜가 같은 경우 해당 요일을 반환합니다.
     * 
     * @param cal1
     * @param cal2
     * @param weekend
     *            토/일 포함 여부.
     * @param style
     *            <ul>
     *            <li>{@link Calendar#SHORT}
     *            <li>{@link Calendar#LONG}
     *            </ul>
     * @param locale
     * @param discards
     *            제외시키는 일자
     * @return
     * 
     * @since 2014. 4. 3.
     */
    public static List<String> getDisplayNameOfDays(Calendar cal1, Calendar cal2, boolean weekend, int style, Locale locale, YearMonthDay... discards) {

        List<String> displayNames = new ArrayList<String>();

        int diff = diffDay(cal1, cal2);

        if (diff == 0) {
            displayNames.add(cal1.getDisplayName(Calendar.DAY_OF_WEEK, style, locale));

            return displayNames;
        }

        Calendar begin = null;

        if (diff > 0) {
            begin = (Calendar) cal2.clone();
        } else {
            begin = (Calendar) cal1.clone();
        }

        if (diff < 0) {
            diff *= -1;
        }

        for (int i = 0; i < diff; i++) {
            int dow = 0;
            // check 'discards'
            for (YearMonthDay ymd : discards) {
                if (ymd.match(begin)) {
                    begin.add(Calendar.DAY_OF_MONTH, 1);
                    continue;
                }
            }

            // check 'weekend'
            dow = begin.get(Calendar.DAY_OF_WEEK);
            if (weekend //
                    || (dow != Calendar.SATURDAY && dow != Calendar.SUNDAY)) {
                displayNames.add(begin.getDisplayName(Calendar.DAY_OF_WEEK, style, locale));
            }

            begin.add(Calendar.DAY_OF_MONTH, 1);
        }

        return displayNames;
    }

    /**
     * 두 날짜 사이의 요일을 반환합니다.<br>
     * 두 날짜가 같은 경우 해당 요일을 반환합니다.
     * 
     * @param cal1
     * @param cal2
     * @param weekend
     *            토/일 포함 여부.
     * @param style
     *            <ul>
     *            <li>{@link Calendar#SHORT}
     *            <li>{@link Calendar#LONG}
     *            </ul>
     * @param discards
     *            제외시키는 일자
     * @return
     * 
     * @since 2014. 4. 3.
     */
    public static List<String> getDisplayNameOfDays(Calendar cal1, Calendar cal2, boolean weekend, int style, YearMonthDay... discards) {
        return getDisplayNameOfDays(cal1, cal2, weekend, style, Locale.getDefault(), discards);
    }

    /**
     * 두 날짜 사이의 요일을 반환합니다.<br>
     * 두 날짜가 같은 경우 해당 요일을 반환합니다.
     * 
     * @param cal1
     * @param cal2
     * @param weekend
     *            토/일 포함 여부.
     * @param locale
     * @param discards
     *            제외시키는 일자
     * @return
     * 
     * @since 2014. 4. 3.
     */
    public static List<String> getDisplayNameOfDays(Calendar cal1, Calendar cal2, boolean weekend, Locale locale, YearMonthDay... discards) {
        return getDisplayNameOfDays(cal1, cal2, weekend, Calendar.LONG, locale, discards);
    }

    /**
     * 두 날짜 사이의 요일을 반환합니다.<br>
     * 두 날짜가 같은 경우 해당 요일을 반환합니다.
     * 
     * @param cal1
     * @param cal2
     * @param weekend
     *            토/일 포함 여부.
     * @param discards
     *            제외시키는 일자
     * @return
     * 
     * @since 2014. 4. 3.
     */
    public static List<String> getDisplayNameOfDays(Calendar cal1, Calendar cal2, boolean weekend, YearMonthDay... discards) {
        return getDisplayNameOfDays(cal1, cal2, weekend, Calendar.LONG, Locale.getDefault(), discards);
    }

    /**
     * 두 날짜 사이의 요일을 반환합니다.<br>
     * 두 날짜가 같은 경우 해당 요일을 반환합니다.
     * 
     * @param cal1
     * @param cal2
     * @param style
     *            <ul>
     *            <li>{@link Calendar#SHORT}
     *            <li>{@link Calendar#LONG}
     *            </ul>
     * @param locale
     * @param discards
     *            제외시키는 일자
     * @return
     * 
     * @since 2014. 4. 3.
     */
    public static List<String> getDisplayNameOfDays(Calendar cal1, Calendar cal2, int style, Locale locale, YearMonthDay... discards) {
        return getDisplayNameOfDays(cal1, cal2, true, style, locale, discards);
    }

    /**
     * 두 날짜 사이의 요일을 반환합니다.<br>
     * 두 날짜가 같은 경우 해당 요일을 반환합니다.
     * 
     * @param cal1
     * @param cal2
     * @param style
     *            <ul>
     *            <li>{@link Calendar#SHORT}
     *            <li>{@link Calendar#LONG}
     *            </ul>
     * @param discards
     *            제외시키는 일자
     * @return
     * 
     * @since 2014. 4. 3.
     */
    public static List<String> getDisplayNameOfDays(Calendar cal1, Calendar cal2, int style, YearMonthDay... discards) {
        return getDisplayNameOfDays(cal1, cal2, true, style, Locale.getDefault(), discards);
    }

    /**
     * 두 날짜 사이의 요일을 반환합니다.<br>
     * 두 날짜가 같은 경우 해당 요일을 반환합니다.
     * 
     * @param cal1
     * @param cal2
     * @param locale
     * @param discards
     *            제외시키는 일자
     * @return
     * 
     * @since 2014. 4. 3.
     */
    public static List<String> getDisplayNameOfDays(Calendar cal1, Calendar cal2, Locale locale, YearMonthDay... discards) {
        return getDisplayNameOfDays(cal1, cal2, true, Calendar.LONG, locale, discards);
    }

    /**
     * 두 날짜 사이의 요일을 반환합니다.<br>
     * 두 날짜가 같은 경우 해당 요일을 반환합니다.
     * 
     * @param cal1
     * @param cal2
     * @param discards
     *            제외시키는 일자
     * @return
     * 
     * @since 2014. 4. 3.
     */
    public static List<String> getDisplayNameOfDays(Calendar cal1, Calendar cal2, YearMonthDay... discards) {
        return getDisplayNameOfDays(cal1, cal2, true, Calendar.LONG, Locale.getDefault(), discards);
    }

    /**
     * 현재 시간의 시분 정보를 반환합니다. <br>
     * 예) 오후 3시 45분 12초인 경우: 1545
     * 
     * @return
     */
    public static String getHHmm() {
        return new SimpleDateFormat("HHmm").format(new Date());
    }

    /**
     * 시간의 시분초 시간 정보를 반환합니다. <br>
     * 예) 오후 3시 20분 12초: 1520
     * 
     * @param date
     * @return
     * 
     */
    public static String getHHmm(Date date) {
        return new SimpleDateFormat("HHmm").format(date);
    }

    /**
     * 현재 날짜의 년월일 정보를 반환합니다.<br>
     * 예) 현재 날짜가 2011년 1월 1일인 경우 "01'
     * 
     * @return
     */
    public static String getMonth() {
        return getMonth(new Date());
    }

    /**
     * 현재 날짜의 년월일 정보를 반환합니다.<br>
     * 예) 현재 날짜가 2011년 1월 1일인 경우 "01'
     * 
     * @return
     */
    public static String getMonth(Date date) {
        return new SimpleDateFormat("MM").format(date);
    }

    /**
     * 현재 시간의 시분초 시간 정보를 반환합니다. <br>
     * 예) 2011년 7월 24일 오후 3시 20분 12초: 0724152012
     * 
     * @return
     */
    public static String getTimeModeString() {
        return new SimpleDateFormat("MMddHHmmss").format(new Date());
    }

    /**
     * 시간의 시분초 시간 정보를 반환합니다. <br>
     * 예) 2011년 7월 24일 오후 3시 20분 12초: 0724152012
     * 
     * @return
     */
    public static String getTimeModeString(Date date) {
        return new SimpleDateFormat("MMddHHmmss").format(date);
    }

    /**
     * 현재 시간을 기준으로 주어진 정보에 맞게 변경된 정보를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 8. 27.		박준홍			최초 작성
     * </pre>
     *
     * @param cal
     * @param field
     *            날짜 구성 영역.
     * @param amount
     *            날짜 데이터
     * @return
     *
     * @since 2021. 8. 27.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static long getTimestamp(Calendar cal, int field, int amount) {
        cal.add(field, amount);
        return cal.getTime().getTime();
    }

    /**
     * 현재 시간을 기준으로 주어진 정보에 맞게 변경된 정보를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 9. 10.		박준홍			최초 작성
     * </pre>
     *
     * @param field
     *            날짜 구성 영역.
     * @param amount
     *            날짜 데이터
     * @return
     *
     * @since 2020. 9. 10.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static long getTimestamp(int field, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.add(field, amount);
        return cal.getTime().getTime();
    }

    /**
     * 시간의 시분초 시간 정보를 주어진 포맷에 맞추어 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 8. 27.     박준홍         최초 작성
     * </pre>
     *
     * @param date
     *            시간 객체
     * @param dateFormat
     *            시분초 표기포맷.
     * @return
     *
     * @since 2021. 8. 27.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @see SimpleDateFormat
     */
    public static String getTimestampString(Calendar cal, SimpleDateFormat dateFormat) {
        return dateFormat.format(cal.getTime());
    }

    /**
     * 시간의 시분초 시간 정보를 주어진 포맷에 맞추어 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 8. 27.     박준홍         최초 작성
     * </pre>
     *
     * @param date
     *            시간 객체
     * @param format
     *            시분초 표기포맷.
     * @return
     *
     * @since 2021. 8. 27.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @see SimpleDateFormat
     */
    public static String getTimestampString(Calendar cal, String format) {
        return new SimpleDateFormat(format).format(cal.getTime());
    }

    /**
     * 시간의 시분초 시간 정보를 주어진 포맷에 맞추어 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 8. 27.     박준홍         최초 작성
     * </pre>
     *
     * @param date
     *            시간 객체
     * @param format
     *            시분초 표기포맷.
     * @return
     *
     * @since 2021. 8. 27.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @see SimpleDateFormat
     */
    public static String getTimestampString(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 시간의 시분초 시간 정보를 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 8. 27.		박준홍			최초 작성
     * </pre>
     *
     * @param timestamp
     *            시간정보 (단위: ms)
     * @param dateFormat
     *            시간 표기 포맷.
     * @return
     *
     * @since 2021. 8. 27.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static String getTimestampString(long timestamp, SimpleDateFormat dateFormat) {
        return dateFormat.format(new Date(timestamp));
    }

    /**
     * 시간의 시분초 시간 정보를 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 8. 27.		박준홍			최초 작성
     * </pre>
     *
     * @param timestamp
     *            시간정보 (단위: ms)
     * @param format
     *            시간 표기 포맷.
     * @return
     *
     * @since 2021. 8. 27.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static String getTimestampString(long timestamp, String format) {
        return new SimpleDateFormat(format).format(new Date(timestamp));
    }

    /**
     * 현재 시간의 시분초 시간 정보를 주어진 포맷에 맞추어 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 8. 27.		박준홍			최초 작성
     * </pre>
     *
     * @param format
     *            시분초 표기포맷.
     * @return
     *
     * @since 2021. 8. 27.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @see SimpleDateFormat
     */
    public static String getTimestampString(String format) {
        return new SimpleDateFormat(format).format(new Date());
    }

    /**
     * 현재 시간의 시분초 시간 정보를 반환합니다. <br>
     * 예) 오후 3시 20분 12초: 152012
     * 
     * @return
     */
    public static String getTimeString() {
        return new SimpleDateFormat("HHmmss").format(new Date());
    }

    /**
     * 시간의 시분초 시간 정보를 주어진 포맷에 맞추어 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 8. 27.     박준홍         최초 작성
     * </pre>
     *
     * @param cal
     *            시간 객체
     * @return
     *
     * @since 2021. 8. 27.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @see SimpleDateFormat
     */
    public static String getTimeString(Calendar cal) {
        return new SimpleDateFormat("HHmmss").format(cal.getTime());
    }

    /**
     * 시간의 시분초 시간 정보를 반환합니다. <br>
     * 예) 오후 3시 20분 12초: 152012
     * 
     * @return
     */
    public static String getTimeString(Date date) {
        return new SimpleDateFormat("HHmmss").format(date);
    }

    /**
     * 시간의 시분초 시간 정보를 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 8. 27.		박준홍			최초 작성
     * </pre>
     *
     * @param timestamp
     *            시간정보 (단위: ms)
     * @return
     *
     * @since 2021. 8. 27.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static String getTimeString(long timestamp) {
        return new SimpleDateFormat("HHmmss").format(new Date(timestamp));
    }

    /**
     * 해당 지역의 타임존 오프셋을 시간표기로 반환합니다.
     * 
     * @param calendar
     * @return
     */
    public static String getTimezoneOffset(Calendar calendar) {
        long timezoneOffset = (calendar.get(Calendar.ZONE_OFFSET) + calendar.get(Calendar.DST_OFFSET)) / (60 * 1000);
        return StringUtils.lpad(timezoneOffset / 60, 2) + ":" + StringUtils.lpad(timezoneOffset % 60, 2);
    }

    /**
     * 현재 날짜의 년월일 정보를 반환합니다.<br>
     * 예) 현재 날짜가 2011년 1월 1일인 경우 "20110101'
     * 
     * @return
     */
    public static String getYear() {
        return new SimpleDateFormat("yyyy").format(new Date());
    }

    /**
     * 현재 날짜의 년월일 정보를 반환합니다.<br>
     * 예) 현재 날짜가 2011년 1월 1일인 경우 "20110101'
     * 
     * @return
     */
    public static String getYear(Date date) {
        return new SimpleDateFormat("yyyy").format(date);
    }

    /**
     * 주어진 시간(<b><code>date</code></b>)에서 주어진 값(<b><code>timeValue</code></b>, <b><code>timeField</code></b>) 이상 경과 했는지를
     * 반환합니다.
     * 
     * @param calendar
     *            TODO
     * @param date
     *            시간 정보
     * @param timeField
     *            시간 구역
     * @param timeValue
     *            시간 값
     * 
     * @return
     * 
     * @exception NullPointerException
     *                if <b><code>calendar</code></b> is null.
     */
    public static boolean isPast(Calendar calendar, Date date, int timeField, int timeValue) {
        calendar.set(timeField, timeValue);
        return date == null || calendar.getTime().after(date);
    }

    /**
     * 현재 시간이 주어진 시간(<b><code>date</code></b>)에서 주어진 값(<b><code>timeValue</code></b>, <b><code>timeField</code></b>) 이상
     * 경과 했는지를 반환합니다.
     * 
     * @param date
     *            시간 정보
     * @param timeValue
     *            시간 값
     * @param timeField
     *            시간 구역
     * @return
     */
    public static boolean isPast(Date date, int timeValue, int timeField) {
        Calendar calendar = Calendar.getInstance();

        calendar.set(timeField, timeValue);

        return date == null || calendar.getTime().after(date);
    }

    /**
     * 주어진 {@link Calendar}에서 설정된 필드의 정보만 있는 새로운 {@link Calendar} 객체를 반환합니다.
     * 
     * @param calendar
     * @param fields
     * @return
     * 
     * @since 2014. 4. 2.
     */
    public static Calendar newCalendar(Calendar calendar, int... fields) {
        Calendar cal = Calendar.getInstance();
        cal.clear();

        for (int field : fields) {
            cal.set(field, calendar.get(field));
        }

        return cal;
    }

    /**
     * 주어진 {@link Date} 정보에 맞는 {@link Calendar} 객체를 반환합니다.
     * 
     * @param timeInMillis
     * @return
     * 
     * @since 2014. 4. 2.
     */
    public static Calendar newCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date.getTime());

        return calendar;
    }

    /**
     * 주어진 정보에 맞는 시간객체를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 11. 5.     박준홍         최초 작성
     * </pre>
     *
     * @param year
     *            년도
     * @param month
     *            월 (0 ~ 11)
     * @param date
     *            날짜
     * @return
     *
     * @since 2020. 11. 5.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static Calendar newCalendar(int year, int month, int date) {
        return newCalendar(year, month, date, 0, 0, 0);
    }

    /**
     * 주어진 정보에 맞는 시간객체를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 11. 5.     박준홍         최초 작성
     * </pre>
     *
     * @param year
     *            년도
     * @param month
     *            월 (0 ~ 11)
     * @param date
     *            날짜
     * @param hourOfDay
     *            시간
     * @param minute
     *            분
     * @param second
     *            초
     * @return
     *
     * @since 2020. 11. 5.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static Calendar newCalendar(int year, int month, int date, int hourOfDay, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, date, hourOfDay, minute, second);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

    /**
     * 주어진 밀리초 정보에 맞는 {@link Calendar} 객체를 반환합니다.
     * 
     * @param timeInMillis
     * @return
     * 
     * @since 2014. 4. 2.
     */
    public static Calendar newCalendar(long timeInMillis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeInMillis);

        return calendar;
    }

    /**
     * 주어진 정보에 맞는 시간객체를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 11. 5.     박준홍         최초 작성
     * </pre>
     *
     * @param year
     *            년도
     * @param month
     *            월 (0 ~ 11)
     * @param date
     *            날짜
     * @return
     *
     * @since 2020. 11. 5.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static Calendar newCalendar(String year, String month, String date) {
        return newCalendar(year, month, date, null, null, null);
    }

    /**
     * 주어진 정보에 맞는 시간객체를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 11. 5.     박준홍         최초 작성
     * </pre>
     *
     * @param year
     *            년도
     * @param month
     *            월 (0 ~ 11)
     * @param date
     *            날짜
     * @param hourOfDay
     *            시간
     * @param minute
     *            분
     * @param second
     *            초
     * @return
     *
     * @since 2020. 11. 5.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static Calendar newCalendar(String year, String month, String date, String hourOfDay, String minute, String second) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(CAL_S2I.apply(year), CAL_S2I.apply(month), CAL_S2I.apply(date), CAL_S2I.apply(hourOfDay), CAL_S2I.apply(minute), CAL_S2I.apply(second));
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

    private static String pad(int i) {
        StringBuffer sb = new StringBuffer();

        if (i < 10) {
            sb.append('0');
        }
        return sb.append(i).toString();
    }

    /**
     * 주어진 {@link Calendar}에서 설정된 필드의 정보만 남기고 나머지 정보를 초기화시킨다.
     * 
     * @param calendar
     * @param fields
     * @return
     * 
     * @since 2014. 4. 2.
     */
    public static void renewCalendar(Calendar calendar, int... fields) {
        Map<Integer, Integer> calendarInfo = new HashMap<Integer, Integer>();

        for (int field : fields) {
            calendarInfo.put(field, calendar.get(field));
        }

        calendar.clear();

        for (int field : fields) {
            calendar.set(field, calendarInfo.get(field));
        }
    }

    /**
     * 
     * @param calendar
     * @param dateFields
     *            e.g. {@link Calendar#HOUR_OF_DAY} , {@link Calendar#MINUTE} , ...
     * 
     * @since 2014. 4. 2.
     */
    public static void resetDateFields(Calendar calendar, int... dateFields) {

        for (int field : dateFields) {
            calendar.set(field, 0);
        }
    }

    /**
     * 해당 필드의 값을 0으로 설정합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 2. 18.		박준홍			최초 작성
     * </pre>
     *
     * @param dateFields
     *            e.g. {@link Calendar#HOUR_OF_DAY} , {@link Calendar#MINUTE} , ...
     * @return
     *
     * @since 2021. 2. 18.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static Calendar resetDateFields(int... dateFields) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        resetDateFields(calendar, dateFields);

        return calendar;
    }

    /**
     * 해당 필드의 값을 0으로 설정합니다.
     * 
     * @param calendar
     * @param dateFields
     *            e.g. {@link Calendar#HOUR_OF_DAY} , {@link Calendar#MINUTE} , ...
     * 
     * @since 2014. 4. 2.
     */
    public static Calendar resetDateFields(long timeInMillis, int... dateFields) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeInMillis);

        resetDateFields(calendar, dateFields);

        return calendar;
    }

    /**
     * 
     * @param date
     *            포맷은 다음과 같다.<br>
     *            {@value #REGEX_yyyyMMDD_HHmmss}
     *            <ul>
     *            <li>2014/12/12 13:12:21
     *            <li>2014-12-12 12/12/12
     *            <li>2014년12월12일 12시12분12초
     *            </ul>
     * @return
     * 
     * @since 2014. 4. 2.
     */
    public static Calendar toCalendar(String date) {
        Matcher m = yyyyMMDD_HHmmss.matcher(date);

        AssertUtils.assertFalse("날짜 정보가 잘못되었습니다. 값: " + date, m.matches(), IllegalArgumentException.class);

        int year = Integer.parseInt(m.group(1));
        int month = Integer.parseInt(m.group(2));
        int dayOfMonth = Integer.parseInt(m.group(3));
        int hour = Integer.parseInt(m.group(4));
        int minute = Integer.parseInt(m.group(5));
        int second = Integer.parseInt(m.group(6));

        Calendar calendar = Calendar.getInstance();
        calendar.clear();

        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);

        return calendar;
    }

    /**
     * @param calendar
     * @return
     * 
     * @since 2014. 4. 7.
     * 
     * @see {@value #REGEX_ISO_FORMAT}
     */
    public static String toISOFormat(Calendar calendar) {
        AssertUtils.assertNull(calendar);
        return new SimpleDateFormat(REGEX_ISO_FORMAT).format(calendar.getTime());
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 11. 5.		박준홍			최초 작성
     * </pre>
     *
     * @param timestamp
     * @return
     *
     * @since 2020. 11. 5.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static String toISOFormat(Long timestamp) {
        AssertUtils.assertNull(timestamp);
        return new SimpleDateFormat(REGEX_ISO_FORMAT).format(newCalendar(timestamp).getTime());
    }

    /**
     * @param calendar
     * @return
     * 
     * @since 2014. 4. 7.
     * 
     * @see {@value #REGEX_ISO_FORMAT_NO_TZ}
     */
    public static String toISOFormatNoTZ(Calendar calendar) {
        AssertUtils.assertNull(calendar);
        return new SimpleDateFormat(REGEX_ISO_FORMAT_NO_TZ).format(calendar.getTime());
    }

    /**
     * 
     * @param year
     * @param month
     * @param dayOfMonth
     * @return
     * 
     * @since 2014. 4. 8.
     */
    public static String toISOFormatNoTZ(int year, int month, int dayOfMonth) {
        return toISOFormatNoTZ(year, month, dayOfMonth, 0, 0, 0);
    }

    /**
     * 
     * @param year
     * @param month
     * @param dayOfMonth
     * @param hourOfDay
     * @param min
     * @param sec
     * @return
     * 
     * @since 2014. 4. 7.
     * @see {@value #MESSAGE_ISO_FORMAT_NO_TZ}
     */
    public static String toISOFormatNoTZ(int year, int month, int dayOfMonth, int hourOfDay, int min, int sec) {
        return toISOFormatNoTZ(pad(year), pad(month), pad(dayOfMonth), pad(hourOfDay), pad(min), pad(sec));
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 11. 5.		박준홍			최초 작성
     * </pre>
     *
     * @param timestamp
     * @return
     *
     * @since 2020. 11. 5.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static String toISOFormatNoTZ(Long timestamp) {
        AssertUtils.assertNull(timestamp);
        return new SimpleDateFormat(REGEX_ISO_FORMAT_NO_TZ).format(newCalendar(timestamp).getTime());
    }

    /**
     * 
     * @param dateStr
     *            . 포맷: {@value #REGEX_yyyyMMDD}
     * @return
     * 
     * @since 2014. 4. 8.
     */
    public static String toISOFormatNoTZ(String dateStr) {
        Matcher m = yyyyMMDD.matcher(dateStr);
        AssertUtils.assertFalse("날짜 정보가 잘못되었습니다. 값: " + dateStr, m.matches(), IllegalArgumentException.class);

        return toISOFormatNoTZ(m.group(1), m.group(2), m.group(3));
    }

    /**
     * 
     * @param year
     * @param month
     * @param dayOfMonth
     * @return
     * 
     * @since 2014. 4. 8.
     */
    public static String toISOFormatNoTZ(String year, String month, String dayOfMonth) {
        return toISOFormatNoTZ(year, month, dayOfMonth, "00", "00", "00");
    }

    /**
     * 
     * @param year
     * @param month
     * @param dayOfMonth
     * @param hourOfDay
     * @param min
     * @param sec
     * @return
     * 
     * @since 2014. 4. 7.
     * @see {@value #MESSAGE_ISO_FORMAT_NO_TZ}
     */
    public static String toISOFormatNoTZ(String year, String month, String dayOfMonth, String hourOfDay, String min, String sec) {
        return MessageFormat.format(MESSAGE_ISO_FORMAT_NO_TZ, year, month, dayOfMonth, hourOfDay, min, sec);
    }

    /**
     * 주어진 날짜를 주어진 패턴에 맞추어 문자열로 표현된 정보를 반환합니다.
     * 
     * <p>
     * <table border="1" width="500">
     * <tr>
     * <td width="100%" colspan="4">
     * <p align="center">
     * <b>패턴 표현</b></td>
     * </tr>
     * <tr>
     * <td width="14%"><b>yyyy</b></td>
     * <td width="36%">년도</td>
     * <td width="14%"><b>MM</b></td>
     * <td width="36%">월</td>
     * </tr>
     * <tr>
     * <td width="14%"><b>dd</b></td>
     * <td width="36%">한달 중 날짜</td>
     * <td width="14%"><b>DD</b></td>
     * <td width="36%">1년 중 일수</td>
     * </tr>
     * <tr>
     * <td width="14%"><b>hh</b></td>
     * <td width="36%">시간 (12시 표기)</td>
     * <td width="14%"><b>HH</b></td>
     * <td width="36%">시간 (24시 표기)</td>
     * </tr>
     * <tr>
     * <td width="14%"><b>mm</b></td>
     * <td width="36%">분</td>
     * <td width="14%"><b>ss</b></td>
     * <td width="36%">초</td>
     * </tr>
     * <tr>
     * <td width="14%"><b>S</b></td>
     * <td width="36%">밀리초 (1/1000)</td>
     * <td colspan="2">&nbsp;</td>
     * </tr>
     * <tr>
     * </table>
     * </p>
     * 
     * 
     * <table border="1" width="500">
     * <tr>
     * <td width="100%" colspan="2">날짜가 &quot;2012년 10월 17일 오후 4시 23분 12초&quot;인 경우</td>
     * </tr>
     * <tr>
     * <td width="41%">패턴</font></td>
     * <td width="59%">결과</font></td>
     * </tr>
     * <tr>
     * <td width="41%"><font face="Courier New" >yyyy-MM-dd <b><font color="#FF0000">hh</font></b>:mm:ss</font></td>
     * <td width="59%"><font face="Courier New" >2012-10-10 <b><font color="#FF0000">04</font></b>:23:12</font></td>
     * </tr>
     * <tr>
     * <td width="41%"><font face="Courier New" >yyyy-MM-dd <b><font color="#0000FF">HH</font></b>:mm:ss</font></td>
     * <td width="59%"><font face="Courier New" >2012-10-10 <b><font color="#0000FF">16</font></b>:23:12</font></td>
     * </tr>
     * </table>
     * 
     * @param date
     *            날짜 정보
     * @param pattern
     *            패턴
     * @return
     */
    public static String toString(Date date, String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 11. 5.		박준홍			최초 작성
     * </pre>
     *
     * @param timestamp
     * @param pattern
     * @return
     *
     * @since 2020. 11. 5.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static String toString(Long timestamp, String pattern) {
        return new SimpleDateFormat(pattern).format(new Date(timestamp));
    }

    /**
     * 현재 날짜를 주어진 패턴에 맞추어 문자열로 표현된 정보를 반환합니다.
     * <p>
     * <table border="1" width="500">
     * <tr>
     * <td width="100%" colspan="4">
     * <p align="center">
     * <b>패턴 표현</b></td>
     * </tr>
     * <tr>
     * <td width="14%"><b>yyyy</b></td>
     * <td width="36%">년도</td>
     * <td width="14%"><b>MM</b></td>
     * <td width="36%">월</td>
     * </tr>
     * <tr>
     * <td width="14%"><b>dd</b></td>
     * <td width="36%">한달 중 날짜</td>
     * <td width="14%"><b>DD</b></td>
     * <td width="36%">1년 중 일수</td>
     * </tr>
     * <tr>
     * <td width="14%"><b>hh</b></td>
     * <td width="36%">시간 (12시 표기)</td>
     * <td width="14%"><b>HH</b></td>
     * <td width="36%">시간 (24시 표기)</td>
     * </tr>
     * <tr>
     * <td width="14%"><b>mm</b></td>
     * <td width="36%">분</td>
     * <td width="14%"><b>ss</b></td>
     * <td width="36%">초</td>
     * </tr>
     * <tr>
     * <td width="14%"><b>S</b></td>
     * <td width="36%">밀리초 (1/1000)</td>
     * <td colspan="2">&nbsp;</td>
     * </tr>
     * <tr>
     * </table>
     * </p>
     * 
     * 
     * <table border="1" width="500">
     * <tr>
     * <td width="100%" colspan="2">날짜가 &quot;2012년 10월 17일 오후 4시 23분 12초&quot;인 경우</td>
     * </tr>
     * <tr>
     * <td width="41%">패턴</font></td>
     * <td width="59%">결과</font></td>
     * </tr>
     * <tr>
     * <td width="41%"><font face="Courier New" >yyyy-MM-dd <b><font color="#FF0000">hh</font></b>:mm:ss</font></td>
     * <td width="59%"><font face="Courier New" >2012-10-10 <b><font color="#FF0000">04</font></b>:23:12</font></td>
     * </tr>
     * <tr>
     * <td width="41%"><font face="Courier New" >yyyy-MM-dd <b><font color="#0000FF">HH</font></b>:mm:ss</font></td>
     * <td width="59%"><font face="Courier New" >2012-10-10 <b><font color="#0000FF">16</font></b>:23:12</font></td>
     * </tr>
     * </table>
     * 
     * @param date
     *            날짜 정보
     * @param pattern
     *            패턴
     * @return
     */
    public static String toString(String pattern) {
        return new SimpleDateFormat(pattern).format(new Date());
    }

    public static String toTimeString(int time) {
        // s
        // m
        // h
        // d
        // M
        // Y
        return null;
    }
}
