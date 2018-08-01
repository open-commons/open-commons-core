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

package open.commons.date;

import java.util.concurrent.TimeUnit;

import open.commons.exception.OutOfRangedValue;
import open.commons.utils.StringUtils;
import open.commons.utils.ThreadUtils;

/**
 * <b><code>HHmmssSS</code></b>의 구조를 갖는 시간 정보 클래스. <br>
 * 
 * <ul>
 * <li>hour: 0 &le; <b><code>HH</code></b> &le; 99
 * <li>minute 0 &le; <b><code>mm</code></b> &le; 59
 * <li>second 0 &le; <b><code>ss</code></b> &le; 59
 * <li>millisecond 0 &le; <b><code>SS</code></b> &le; 59
 * </ul>
 * 
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */
public class TimeInfo implements Comparable<TimeInfo> {

    protected final String NAME = getClass().getSimpleName();

    public static final int DEFAULT_MIN_DAY = 0x00;
    public static final int DEFAULT_MIN_HOUR = 0x00;
    public static final int DEFAULT_MIN_MINUTE = 0x00;
    public static final int DEFAULT_MIN_SECOND = 0x00;
    public static final int DEFAULT_MIN_MILLISECOND = 0x00;

    public static final int DEFAULT_MAX_DAY = Integer.MAX_VALUE;
    public static final int DEFAULT_MAX_HOUR = 0x18; // 24
    public static final int DEFAULT_MAX_MINUTE = 0x3C; // 59
    public static final int DEFAULT_MAX_SECOND = 0x3C; // 59
    public static final int DEFAULT_MAX_MILLISECOND = 0x03E7; // 999

    public static final int FIELD_HOUR = 0x00;
    public static final int FIELD_SECOND = 0x02;
    public static final int FIELD_MINUTE = 0x01;
    public static final int FIELD_MILLISECOND = 0x03;
    public static final int FIELD_DAY = 0x04;

    protected int MIN_DAY = DEFAULT_MIN_DAY;
    protected int MIN_HOUR = DEFAULT_MIN_HOUR;
    protected int MIN_MINUTE = DEFAULT_MIN_MINUTE;
    protected int MIN_SECOND = DEFAULT_MIN_SECOND;
    protected int MIN_MILLISECOND = DEFAULT_MIN_MILLISECOND;

    protected int MAX_DAY = DEFAULT_MAX_DAY; // 24
    protected int MAX_HOUR = DEFAULT_MAX_HOUR; // 24
    protected int MAX_MINUTE = DEFAULT_MAX_MINUTE; // 59
    protected int MAX_SECOND = DEFAULT_MAX_SECOND; // 59
    protected int MAX_MILLISECOND = DEFAULT_MAX_MILLISECOND; // 999

    private long day;

    private long hour;

    private long minute;

    private long second;

    private long millisecond;

    public TimeInfo() {

    }

    /**
     * 
     * @param second
     *            0 &le; <b><code>second</code></b> &le; 59
     * 
     * @throws IllegalArgumentException
     */
    public TimeInfo(long second) {
        this(DEFAULT_MIN_HOUR, DEFAULT_MIN_MINUTE, second, DEFAULT_MIN_MILLISECOND);
    }

    /**
     * 
     * @param minute
     *            0 &le; <b><code>second</code></b> &le; 59
     * @param second
     *            0 &le; <b><code>millisecond</code></b> &le; 59
     * @throws IllegalArgumentException
     */
    public TimeInfo(long minute, long second) {
        this(DEFAULT_MIN_HOUR, minute, second, DEFAULT_MIN_MILLISECOND);
    }

    /**
     * 
     * @param hour
     *            0 &le; <b><code>hour</code></b> &le; 99
     * @param minute
     *            0 &le; <b><code>minute</code></b> &le; 59
     * @param second
     *            0 &le; <b><code>second</code></b> &le; 59
     * @throws IllegalArgumentException
     */
    public TimeInfo(long hour, long minute, long second) {
        this(hour, minute, second, DEFAULT_MIN_MILLISECOND);
    }

    /**
     * 
     * @param hour
     *            0 &le; <b><code>hour</code></b> &le; 99
     * @param minute
     *            0 &le; <b><code>minute</code></b> &le; 59
     * @param second
     *            0 &le; <b><code>second</code></b> &le; 59
     * @param millisecond
     *            0 &le; <b><code>millisecond</code></b> &le; 59
     * @throws IllegalArgumentException
     */
    public TimeInfo(long hour, long minute, long second, long millisecond) throws IllegalArgumentException {

        try {
            calcMillisec(millisecond);
            calcSec(second);
            calcMin(minute);
            calcHour(hour);
        } catch (OutOfRangedValue e) {
            throw new IllegalArgumentException("hour: " + hour + ", minute: " + minute + ", second: " + second + ", millisecond: " + millisecond, e);
        }
    }

    /**
     * 
     * @param field
     *            one of following.<br>
     * 
     *            <ul>
     *            <li>{@link TimeInfo#FIELD_HOUR}
     *            <li>{@link TimeInfo#FIELD_MINUTE}
     *            <li>{@link TimeInfo#FIELD_SECOND}
     *            <li>{@link TimeInfo#FIELD_MILLISECOND}
     *            </ul>
     * @param value
     */
    public void add(int field, long value) {

        switch (field) {
            case FIELD_HOUR:

                calcHour(value);

                break;
            case FIELD_MINUTE:

                calcMin(value);

                break;
            case FIELD_SECOND:

                calcSec(value);

                break;
            case FIELD_MILLISECOND:

                calcMillisec(value);

                break;
            default:
                throw new IllegalArgumentException(
                        "Excected value is one of : (" + FIELD_HOUR + ", " + FIELD_MINUTE + ", " + FIELD_SECOND + ", " + FIELD_MILLISECOND + "), input: " + field);
        }
    }

    protected final void calcDay(long day) {

        long tmp = this.day + day;

        if (tmp < 0) {
            throw new OutOfRangedValue(tmp, MIN_DAY, MAX_DAY);
        } else if (tmp <= MAX_DAY) {
            this.hour = tmp;
        } else {
            throw new OutOfRangedValue(tmp, MIN_DAY, MAX_DAY);
        }
    }

    protected final void calcHour(long hour) {

        long tmp = this.hour + hour;

        if (tmp < 0) {
            throw new OutOfRangedValue(tmp, MIN_HOUR, MAX_HOUR);
        } else if (tmp <= MAX_HOUR) {
            this.hour = tmp;
        } else {
            this.hour = tmp % MAX_HOUR;

            this.day += tmp / MAX_HOUR;
        }
    }

    protected final void calcMillisec(long ms) {

        long tmp = insight(millisecond, ms, FIELD_SECOND) + ms;

        if (tmp >= MAX_MILLISECOND) {
            long q = tmp / MAX_MILLISECOND;
            millisecond = tmp % MAX_MILLISECOND;

            calcSec(q);
        } else {
            millisecond = tmp;
        }
    }

    protected final void calcMin(long min) {

        long tmp = insight(minute, min, FIELD_HOUR) + min;

        if (tmp >= MAX_MINUTE) {
            long q = tmp / MAX_MINUTE;
            minute = tmp % MAX_MINUTE;

            calcHour(q);
        } else {
            minute = tmp;
        }
    }

    protected final void calcSec(long sec) {
        long tmp = insight(second, sec, FIELD_MINUTE) + sec;

        if (tmp >= MAX_SECOND) {
            long q = tmp / MAX_SECOND;
            second = tmp % MAX_SECOND;

            calcMin(q);
        } else {
            second = tmp;
        }

    }

    /**
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(TimeInfo o) {
        return numeric() > o.numeric() ? 1 : -1;
    }

    /**
     * 시간정보 차이 값을 반환한다.<br>
     * 대소 여부는 {@link #ge(TimeInfo)} 또는 {@link #lt(TimeInfo)}를 이용한다.
     * 
     * @param other
     * @return
     */
    public TimeInfo diff(TimeInfo other) {

        long hMs = toMillisecond();
        long oMs = other.toMillisecond();

        return TimeInfo.createInstance((int) Math.abs(hMs - oMs));
    }

    /**
     * 
     * @param timeInfo
     * @return
     */
    public <T extends TimeInfo> boolean eq(T timeInfo) {
        return compareTo(timeInfo) == 0 ? true : false;
    }

    /**
     * 2자리 길이를 맞추어 반환한다.
     * 
     * @param v
     * @return
     */
    protected final String fit(long v) {
        if (v < 10) {
            return "0" + v;
        }

        return String.valueOf(v);
    }

    public String formedValue() {
        StringBuffer sb = new StringBuffer();

        sb.append(fit(day));
        sb.append(" day ");
        sb.append(fit(hour));
        sb.append(':');
        sb.append(fit(minute));
        sb.append(':');
        sb.append(fit(second));
        sb.append('.');
        sb.append(fit(millisecond));

        return sb.toString();
    }

    /**
     * 
     * @param timeInfo
     * @return
     */
    public <T extends TimeInfo> boolean ge(T timeInfo) {
        return compareTo(timeInfo) < 0 ? false : true;
    }

    public long getHour() {
        return hour;
    }

    public long getMillisecond() {
        return millisecond;
    }

    public long getMinute() {
        return minute;
    }

    public long getSecond() {
        return second;
    }

    /**
     * 
     * @param timeInfo
     * @return
     */
    public <T extends TimeInfo> boolean gt(T timeInfo) {
        return compareTo(timeInfo) > 0 ? true : false;
    }

    /**
     * 객체 내의 값과 연산을 할 값을 비교하여, 필요한 경우 상위값을 얻어온다.
     * 
     * @param currentValue
     *            현재 시간값
     * @param newValue
     *            입력 시간값
     * @param nextUpperField
     *            현재 시간보다 1단계 상위 필드.
     * @return
     * 
     * @see #add(int, int)
     */
    protected final long insight(long currentValue, long newValue, int nextUpperField) {

        long dc = 0;

        if (currentValue + newValue < 0) {

            dc = -1;

            switch (nextUpperField) {
                case FIELD_HOUR:

                    while ((currentValue += MAX_MINUTE) < -newValue) {
                        dc--;
                    }

                    break;
                case FIELD_MINUTE:

                    while ((currentValue += MAX_SECOND) < -newValue) {
                        dc--;
                    }

                    break;
                case FIELD_SECOND:

                    while ((currentValue += MAX_MILLISECOND) < -newValue) {
                        dc--;
                    }

                    break;
            }

            add(nextUpperField, dc);
        }

        return currentValue;
    }

    /**
     * 
     * @param timeInfo
     * @return
     */
    public <T extends TimeInfo> boolean le(T timeInfo) {
        return compareTo(timeInfo) > 0 ? false : true;
    }

    /**
     * 
     * @param timeInfo
     * @return
     */
    public <T extends TimeInfo> boolean lt(T timeInfo) {
        return compareTo(timeInfo) < 0 ? true : false;
    }

    /**
     * 숫자 형태의 값을 반환한다.
     * 
     * @return
     */
    public final long numeric() {
        return hour * 1000000 //
                + minute * 10000 //
                + second * 100 //
                + millisecond;
    }

    /**
     * 
     * @param v
     * @param min
     * @param max
     * @return
     * 
     * @deprecated Internally none of use.
     * 
     */
    @Deprecated
    protected final boolean out(int v, int min, int max) {
        return v < min || v > max;
    }

    /**
     * 
     * @param field
     *            one of following.<br>
     * 
     *            <ul>
     *            <li>{@link TimeInfo#FIELD_HOUR}
     *            <li>{@link TimeInfo#FIELD_MINUTE}
     *            <li>{@link TimeInfo#FIELD_SECOND}
     *            <li>{@link TimeInfo#FIELD_MILLISECOND}
     *            </ul>
     * @param value
     */
    public void set(int field, long value) {

        switch (field) {
            case FIELD_HOUR:

                try {
                    calcHour(hour);
                } catch (Exception e) {
                    throw new OutOfRangedValue(value, MIN_HOUR, MAX_HOUR);
                }

                break;

            case FIELD_MINUTE:

                try {
                    calcMin(value);
                } catch (Exception e) {
                    throw new OutOfRangedValue(value, MIN_HOUR, MAX_HOUR);
                }

                break;

            case FIELD_SECOND:

                try {
                    calcSec(value);
                } catch (Exception e) {
                    throw new OutOfRangedValue(value, MIN_HOUR, MAX_HOUR);
                }

                break;

            case FIELD_MILLISECOND:

                try {
                    calcMillisec(value);
                } catch (Exception e) {
                    throw new OutOfRangedValue(value, MIN_HOUR, MAX_HOUR);
                }

                break;
            default:
                throw new IllegalArgumentException(
                        "Excected value is one of : (" + FIELD_HOUR + ", " + FIELD_MINUTE + ", " + FIELD_SECOND + ", " + FIELD_MILLISECOND + "), input: " + field);
        }
    }

    /**
     * 시간 정보를 밀리세컨드로 반환한다.
     * 
     * @return
     */
    public long toMillisecond() {

        long ms = TimeUnit.HOURS.toMillis(hour) //
                + TimeUnit.MINUTES.toMillis(minute) //
                + TimeUnit.SECONDS.toMillis(second) //
                + millisecond;

        return ms;
    }

    /**
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "TimeInfo [NAME=" + NAME + ", day=" + day + ", hour=" + hour + ", minute=" + minute + ", second=" + second + ", millisecond=" + millisecond + ", MAX_HOUR="
                + MAX_HOUR + ", MAX_MINUTE=" + MAX_MINUTE + ", MAX_SECOND=" + MAX_SECOND + ", MAX_MILLISECOND=" + MAX_MILLISECOND + "]";
    }

    /**
     * 문자열 형태의 값을 반환한다.
     * 
     * @return
     */
    public String value() {
        StringBuffer sb = new StringBuffer();

        sb.append(fit(day));
        sb.append(fit(hour));
        sb.append(fit(minute));
        sb.append(fit(second));
        sb.append(fit(millisecond));

        return sb.toString();
    }

    /**
     * 밀리세컨드 정보를 입력받아 객체를 생성한다.<br>
     * 
     * @param ms
     * @return
     * 
     */
    public static TimeInfo createInstance(long ms) {
        TimeInfo time = new TimeInfo((int) TimeUnit.MILLISECONDS.toSeconds(ms));
        time.set(FIELD_MILLISECOND, ms % 1000);

        return time;
    }

    /**
     * 시간정보 표시 문자열.
     * 
     * @param timeInfo
     * @return
     */
    public static TimeInfo createInstance(String timeInfo) {

        TimeInfo time = null;

        if (timeInfo.length() >= 8) {
            timeInfo = timeInfo.substring(0, 8);
        } else {
            timeInfo = StringUtils.lpad(timeInfo, 8);
        }

        int h = Integer.parseInt(timeInfo.substring(0, 2));
        int m = Integer.parseInt(timeInfo.substring(2, 4));
        int s = Integer.parseInt(timeInfo.substring(4, 6));
        int ms = Integer.parseInt(timeInfo.substring(6, 8));

        try {
            time = new TimeInfo(h, m, s, ms);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                    "[input=" + timeInfo + "], hour: " + h + ", minute: " + m + ", second: " + s + ", millisecond: " + ms + "\n[cause]\n" + ThreadUtils.getStackTrace(e));

        }

        return time;

    }
}