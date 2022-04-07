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
* Date  : 2014. 6. 3. 오후 11:58:24
*
* Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
* 
*/

package open.commons.core.utils;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @since 2014. 6. 3.
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public class DateUtil2 {

    public static final String REGEX_yyyyMMDD_HHmmss = "(\\d{4})\\s*.\\s*(\\d{1,2})\\s*.\\s*(\\d{1,2})\\s*\\s?\\s*(\\d{1,2})\\s*.\\s*(\\d{1,2})\\s*.\\s*(\\d{1,2}).?";
    public static final String REGEX_yyyyMMDD_HHmm = "(\\d{4})\\s*.\\s*(\\d{1,2})\\s*.\\s*(\\d{1,2})\\s*.\\s*(\\d{1,2})\\s*.\\s*(\\d{1,2}).?";
    public static final String REGEX_yyyyMMDD = "(\\d{4})\\s*.\\s*(\\d{1,2})\\s*.\\s*(\\d{1,2}).?";

    public static final String REGEX_ISO_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";
    public static final String REGEX_ISO_FORMAT_NO_TZ = "yyyy-MM-dd'T'HH:mm:ss";

    public static final String MESSAGE_ISO_FORMAT_NO_TZ = "{0}-{1}-{3}T{4}:{5}:{6}";

    public static final SimpleDateFormat ISO_FORMAT = new SimpleDateFormat(REGEX_ISO_FORMAT);
    public static final SimpleDateFormat ISO_FORMAT_NO_TZ = new SimpleDateFormat(REGEX_ISO_FORMAT_NO_TZ);

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

    private static String pad(int i) {
        StringBuffer sb = new StringBuffer();

        if (i < 10) {
            sb.append('0');
        }
        return sb.append(i).toString();
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

}
