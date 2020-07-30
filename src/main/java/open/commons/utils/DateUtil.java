/*
 * Copyright 2011 Park Jun-Hong (parkjunhong77/google/com)
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

package open.commons.utils;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import open.commons.TwoValueObject;
import open.commons.date.YearMonthDay;

/**
 * [클래스 개요]
 * <p>
 * <b>NOTE:</b> [설명 및 주의사항]
 * 
 * @author Park Jun-Hong
 * @since 2011. 07. 12.
 * @see
 * 
 *      <pre>
 * == 개정이력(Modification Information) ==
 * 
 * 수정일                		수정자 	 			수정내용
 * ------------		---------------		---------------------------
 * 2011. 07. 12.      Park Jun-Hong	           최초 생성
 *      </pre>
 */
public class DateUtil {

    private static ConcurrentSkipListMap<String, SimpleDateFormat> formats = new ConcurrentSkipListMap<String, SimpleDateFormat>();

    public static final String REGEX_yyyyMMDD_HHmmss = "(\\d{4})\\s*.\\s*(\\d{1,2})\\s*.\\s*(\\d{1,2})\\s*\\s?\\s*(\\d{1,2})\\s*.\\s*(\\d{1,2})\\s*.\\s*(\\d{1,2}).?";
    public static final String REGEX_yyyyMMDD_HHmm = "(\\d{4})\\s*.\\s*(\\d{1,2})\\s*.\\s*(\\d{1,2})\\s*.\\s*(\\d{1,2})\\s*.\\s*(\\d{1,2}).?";
    public static final String REGEX_yyyyMMDD = "(\\d{4})\\s*.\\s*(\\d{1,2})\\s*.\\s*(\\d{1,2}).?";

    public static final String REGEX_ISO_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";
    public static final String REGEX_ISO_FORMAT_NO_TZ = "yyyy-MM-dd'T'HH:mm:ss";

    public static final String MESSAGE_ISO_FORMAT_NO_TZ = "{0}-{1}-{3}T{4}:{5}:{6}";

    public static final SimpleDateFormat ISO_FORMAT = new SimpleDateFormat(REGEX_ISO_FORMAT);
    public static final SimpleDateFormat ISO_FORMAT_NO_TZ = new SimpleDateFormat(REGEX_ISO_FORMAT_NO_TZ);

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

    /**
     * 주어진 시간이 경과한 후에 현재 시간 이후인지 여부를 제공한다.
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
     * 주어진 시간이 경과한 후에 현재 시간 이전인지 여부를 제공한다.
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
     * 비교하려는 날짜 문자열이 날짜 구간의 어느 부분에 속하는지를 반환한다.
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
     * 날짜수 차이를 반환한다.
     * 
     * @param cal1
     * @param cal2
     * @return 양수인 경우 첫번째 날짜가 크고, 음수인 경우 두번째 날짜가 큰 경우이다.
     * 
     * @since 2014. 4. 2.
     */
    public static int diffDay(Calendar cal1, Calendar cal2) {

        Calendar c1 = newCalendar(cal1, Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH);
        Calendar c2 = newCalendar(cal2, Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH);

        long time1 = c1.getTimeInMillis();
        long time2 = c2.getTimeInMillis();

        if (time1 == time2) {
            return 0;
        }

        boolean sig = time1 > time2 ? true : false;

        long diff = Math.abs(time1 - time2);

        return (int) ((sig ? 1 : -1) * TimeUnit.MILLISECONDS.toDays(diff));
    }

    public static int diffDay(Date date1, Date date2) {
        return diffDay(newCalendar(date1), newCalendar(date2));
    }

    /**
     * 현재 날짜의 년월일 정보를 반환한다.<br>
     * 예) 현재 날짜가 2011년 1월 1일인 경우 "20110101'
     * 
     * @return
     */
    public static String getDateString() {
        return new SimpleDateFormat("yyyyMMdd").format(new Date());
    }

    /**
     * 날짜의 년월일 정보를 반환한다.<br>
     * 예) 현재 날짜가 2011년 1월 1일인 경우 "20110101'
     * 
     * @return
     */
    public static String getDateString(Date date) {
        return new SimpleDateFormat("yyyyMMdd").format(date);
    }

    /**
     * 현재 날짜의 년월일 정보를 반환한다.<br>
     * 예) 현재 날짜가 2011년 1월 1일 1시 1분 1초인 경우 "20110101010101'
     * 
     * @return
     */
    public static String getDateTimeString() {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }

    /**
     * 날짜의 년월일 정보를 반환한다.<br>
     * 예) 날짜가 2011년 1월 1일 1시 1분 1초인 경우 "20110101010101'
     * 
     * @return
     */
    public static String getDateTimeString(Date date) {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(date);
    }

    /**
     * 현재 날짜의 년월일 정보를 반환한다.<br>
     * 예) 현재 날짜가 2011년 1월 1일인 경우 "20110101'
     * 
     * @return
     */
    public static String getDay() {
        return new SimpleDateFormat("dd").format(new Date());
    }

    /**
     * 현재 날짜의 년월일 정보를 반환한다.<br>
     * 예) 현재 날짜가 2011년 1월 1일인 경우 "20110101'
     * 
     * @return
     */
    public static String getDay(Date date) {
        return new SimpleDateFormat("dd").format(date);
    }

    /**
     * 두 날짜 사이의 요일을 반환한다.<br>
     * 두 날짜가 같은 경우 해당 요일을 반환한다.
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
     * 두 날짜 사이의 요일을 반환한다.<br>
     * 두 날짜가 같은 경우 해당 요일을 반환한다.
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
     * 두 날짜 사이의 요일을 반환한다.<br>
     * 두 날짜가 같은 경우 해당 요일을 반환한다.
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
     * 두 날짜 사이의 요일을 반환한다.<br>
     * 두 날짜가 같은 경우 해당 요일을 반환한다.
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
     * 두 날짜 사이의 요일을 반환한다.<br>
     * 두 날짜가 같은 경우 해당 요일을 반환한다.
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
     * 두 날짜 사이의 요일을 반환한다.<br>
     * 두 날짜가 같은 경우 해당 요일을 반환한다.
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
     * 두 날짜 사이의 요일을 반환한다.<br>
     * 두 날짜가 같은 경우 해당 요일을 반환한다.
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
     * 두 날짜 사이의 요일을 반환한다.<br>
     * 두 날짜가 같은 경우 해당 요일을 반환한다.
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
     * 현재 시간의 시분 정보를 반환한다. <br>
     * 예) 오후 3시 45분 12초인 경우: 1545
     * 
     * @return
     */
    public static String getHHmm() {
        return new SimpleDateFormat("HHmm").format(new Date());
    }

    /**
     * 시간의 시분초 시간 정보를 반환한다. <br>
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
     * 현재 날짜의 년월일 정보를 반환한다.<br>
     * 예) 현재 날짜가 2011년 1월 1일인 경우 "20110101'
     * 
     * @return
     */
    public static String getMonth() {
        return new SimpleDateFormat("MM").format(new Date());
    }

    /**
     * 현재 날짜의 년월일 정보를 반환한다.<br>
     * 예) 현재 날짜가 2011년 1월 1일인 경우 "20110101'
     * 
     * @return
     */
    public static String getMonth(Date date) {
        return new SimpleDateFormat("MM").format(date);
    }

    /**
     * 현재 시간의 시분초 시간 정보를 반환한다. <br>
     * 예) 2011년 7월 24일 오후 3시 20분 12초: 0724152012
     * 
     * @return
     */
    public static String getTimeModeString() {
        return new SimpleDateFormat("MMddHHmmss").format(new Date());
    }

    /**
     * 시간의 시분초 시간 정보를 반환한다. <br>
     * 예) 2011년 7월 24일 오후 3시 20분 12초: 0724152012
     * 
     * @return
     */
    public static String getTimeModeString(Date date) {
        return new SimpleDateFormat("MMddHHmmss").format(date);
    }

    /**
     * 현재 시간의 시분초 시간 정보를 반환한다. <br>
     * 예) 오후 3시 20분 12초: 152012
     * 
     * @return
     */
    public static String getTimeString() {
        return new SimpleDateFormat("HHmmss").format(new Date());
    }

    /**
     * 시간의 시분초 시간 정보를 반환한다. <br>
     * 예) 오후 3시 20분 12초: 152012
     * 
     * @return
     */
    public static String getTimeString(Date date) {
        return new SimpleDateFormat("HHmmss").format(date);
    }

    /**
     * 해당 지역의 타임존 오프셋을 시간표기로 반환한다.
     * 
     * @param calendar
     * @return
     */
    public static String getTimezoneOffset(Calendar calendar) {
        long timezoneOffset = (calendar.get(Calendar.ZONE_OFFSET) + calendar.get(Calendar.DST_OFFSET)) / (60 * 1000);
        return StringUtils.lpad(timezoneOffset / 60, 2) + ":" + StringUtils.lpad(timezoneOffset % 60, 2);
    }

    /**
     * 현재 날짜의 년월일 정보를 반환한다.<br>
     * 예) 현재 날짜가 2011년 1월 1일인 경우 "20110101'
     * 
     * @return
     */
    public static String getYear() {
        return new SimpleDateFormat("yyyy").format(new Date());
    }

    /**
     * 현재 날짜의 년월일 정보를 반환한다.<br>
     * 예) 현재 날짜가 2011년 1월 1일인 경우 "20110101'
     * 
     * @return
     */
    public static String getYear(Date date) {
        return new SimpleDateFormat("yyyy").format(date);
    }

    /**
     * 주어진 시간(<b><code>date</code></b>)에서 주어진 값(<b><code>timeValue</code></b>, <b><code>timeField</code></b>) 이상 경과 했는지를
     * 반환한다.
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
     * 경과 했는지를 반환한다.
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
     * 주어진 {@link Calendar}에서 설정된 필드의 정보만 있는 새로운 {@link Calendar} 객체를 반환한다.
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
     * 주어진 {@link Date} 정보에 맞는 {@link Calendar} 객체를 반환한다.
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
     * 주어진 밀리초 정보에 맞는 {@link Calendar} 객체를 반환한다.
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
     * 해당 필드의 값을 0으로 설정한다.
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

        return ISO_FORMAT.format(calendar.getTime());
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

        return ISO_FORMAT_NO_TZ.format(calendar.getTime());
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
     * 주어진 날짜를 주어진 패턴에 맞추어 문자열로 표현된 정보를 반환한다.
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
        SimpleDateFormat format = formats.get(pattern);
        if (format == null) {
            format = new SimpleDateFormat(pattern);

            formats.put(pattern, format);
        }

        return format.format(date);
    }

    /**
     * 현재 날짜를 주어진 패턴에 맞추어 문자열로 표현된 정보를 반환한다.
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
        SimpleDateFormat format = formats.get(pattern);
        if (format == null) {
            format = new SimpleDateFormat(pattern);

            formats.put(pattern, format);
        }

        return format.format(new Date());
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
