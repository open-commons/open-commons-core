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
* 
*
* This file is generated under this project, "open-commons-core".
*
* Date  : 2014. 4. 3. 오후 5:42:49
*
* Author: Park_Jun_Hong_(fafanmama_at_naver_com)
* 
*/

package open.commons.date;

import java.util.Calendar;
import java.util.Date;

import open.commons.utils.DateUtil;

/**
 * Only <b><code>Year, Month, Day</code></b>
 * 
 * @since 2014. 4. 3.
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
public class YearMonthDay {

    private int year;

    private int month;

    private int day;

    public YearMonthDay() {

    }

    public YearMonthDay(int y, int m, int d) {
        this.year = y;
        this.month = m;
        this.day = d;
    }

    /**
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
        YearMonthDay other = (YearMonthDay) obj;
        if (day != other.day)
            return false;
        if (month != other.month)
            return false;
        if (year != other.year)
            return false;
        return true;
    }

    /**
     * 
     * @return the day
     * 
     * @since 2014. 4. 3.
     */
    public int getDay() {
        return day;
    }

    /**
     * 
     * @return the month
     * 
     * @since 2014. 4. 3.
     */
    public int getMonth() {
        return month;
    }

    /**
     * 
     * @return the year
     * 
     * @since 2014. 4. 3.
     */
    public int getYear() {
        return year;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + day;
        result = prime * result + month;
        result = prime * result + year;
        return result;
    }

    public boolean match(Calendar calendar) {
        return year == calendar.get(Calendar.YEAR) //
                && month == calendar.get(Calendar.MONTH) //
                && day == calendar.get(Calendar.DAY_OF_MONTH); //
    }

    public boolean match(Date date) {
        return match(DateUtil.newCalendar(date));
    }

    public boolean match(long timeInMillis) {
        return match(DateUtil.newCalendar(timeInMillis));
    }

    /**
     * @param day
     *            the day to set
     * 
     * @since 2014. 4. 3.
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     * @param month
     *            the month to set
     * 
     * @since 2014. 4. 3.
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * @param year
     *            the year to set
     * 
     * @since 2014. 4. 3.
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "YearMonthDay [year=" + year + ", month=" + month + ", day=" + day + "]";
    }

    public static YearMonthDay getYMD(Calendar calendar) {
        YearMonthDay ymd = new YearMonthDay();

        ymd.year = calendar.get(Calendar.YEAR);
        ymd.month = calendar.get(Calendar.MONTH);
        ymd.day = calendar.get(Calendar.DAY_OF_MONTH);

        return ymd;
    }

}
