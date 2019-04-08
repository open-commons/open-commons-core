/*
 * Copyright 2018 Park Jun-Hong (parkjunhong77/google/com)
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
 * This file is generated under this project, "open-commons-core".
 *
 * Date  : 2018. 1. 9. 오후 4:24:47
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package open.commons.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 *
 * <pre>
 * [개정이력]
 *      날짜      | 작성자   |   내용
 * ------------------------------------------
 * 2018. 1. 9.        박준홍         최초 작성
 * 2018. 11. 15.    박준홍     TimeUnit Expression String  Builder 추가. (Default, Korea 기본 적용)
 * </pre>
 * 
 * @since 2018. 1. 9.
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
public class TimeUtils {
    /** default time unit info */
    private static final TimeUnitExpr DEFAULT_TIME_UNIT_DEF = TimeUnitBuilder.create().build();
    /** default locale */
    private static final Locale DEFAULT_LOCALE = Locale.getDefault();
    private static Locale CURRENT_LOCALE = DEFAULT_LOCALE;

    private static final HashMap<Locale, TimeUnitExpr> TIME_UNITS = new HashMap<>();
    static {
        // set Default
        TIME_UNITS.put(DEFAULT_LOCALE, DEFAULT_TIME_UNIT_DEF);

        // set Korea
        TIME_UNITS.put(Locale.KOREA, TimeUnitBuilder.create() //
                .setSecond("초") //
                .setMinute("분") //
                .setHour("시간") //
                .setDay("일") //
                .setYear("년") //
                .build());
    }

    private static final Function<TimeUnit, TimeUnitInfo[]> FN_TIME_UNITS = unit -> {

        ArrayList<TimeUnitInfo> units = new ArrayList<>();

        for (TimeUnitInfo tui : getTimeUnits().timeUnitInfo) {
            if (tui.unit.ordinal() >= unit.ordinal()) {
                units.add(tui);
            }
        }

        return units.toArray(new TimeUnitInfo[] {});
    };

    /** 제외시킬 시간 단위 */
    private static final HashSet<TimeUnit> OMITTED_TIME_UINITS = new HashSet<>();

    /** discard none. */
    public static final int DC_NONE = 0x00;

    /** discard under nanoseconds */
    public static final int DC_NANO = 0x01;

    /** discard under microseconds */
    public static final int DC_MICRO = DC_NANO << 1;
    /** discard under milliseconds */
    public static final int DC_MILLI = DC_MICRO << 1;
    /** discard under seconds */
    public static final int DC_SECOND = DC_MILLI << 1;
    /** discard under minutes */
    public static final int DC_MINUTE = DC_SECOND << 1;
    /** discard under hours */
    public static final int DC_HOUR = DC_MINUTE << 1;
    /** discard under days */
    public static final int DC_DAY = DC_HOUR << 1;

    // prevent to create an instance.
    private TimeUtils() {
    }

    /**
     * 포맷 문자열에서 제외할 시간단위를 추가한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2018. 11. 15.		박준홍			최초 작성
     * </pre>
     *
     * @param units
     *
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @since 2018. 11. 15.
     */
    public static void addOmit(TimeUnit... units) {
        for (TimeUnit unit : units) {
            if (unit == null) {
                continue;
            }

            OMITTED_TIME_UINITS.add(unit);
        }
    }

    private static final TimeUnitExpr getTimeUnits() {
        return TIME_UNITS.containsKey(CURRENT_LOCALE) ? TIME_UNITS.get(CURRENT_LOCALE) : DEFAULT_TIME_UNIT_DEF;
    }

    private static long mod(long time, TimeUnit unit) {
        switch (unit) {
            case NANOSECONDS: // to nanosecond
            case MILLISECONDS: // to microsecond
            case MICROSECONDS: // to millsecond
                return time % 1000;
            case SECONDS: // to second
                return time % 60;
            case MINUTES: // to minute
                return time % 60;
            case HOURS: // to hour
                return time % 24;
            case DAYS: // to day
                return time % 365;
            default:
                throw new IllegalArgumentException(unit.toString());
        }
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 1. 9.      박준홍         최초 작성
     * </pre>
     *
     * @param timeBuf
     * @param time
     * @param unit
     *
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @since 2018. 1. 9.
     */
    private static void prependTimeAndUnit(StringBuffer timeBuf, long time, String unit) {
        if (time < 1) {
            return;
        }

        if (timeBuf.length() > 0) {
            timeBuf.insert(0, " ");
        }

        setTimeUnit(timeBuf, unit);
        setTimeValue(timeBuf, time);
    }

    private static void prependTimeAndUnit(StringBuffer timeBuf, long time, TimeUnitInfo unitInfo) {
        prependTimeAndUnit(timeBuf, time, unitInfo.unitStr);
    }

    /**
     * 포맷 문자열 제외 목록에서 제거한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2018. 11. 15.		박준홍			최초 작성
     * </pre>
     *
     * @param units
     *
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @since 2018. 11. 15.
     */
    public static void removeOmit(TimeUnit... units) {
        for (TimeUnit unit : units) {
            if (unit == null) {
                continue;
            }

            OMITTED_TIME_UINITS.remove(unit);
        }
    }

    /**
     * Set a current locale. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2018. 11. 15.		박준홍			최초 작성
     * </pre>
     *
     * @param locale
     *
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @since 2018. 11. 15.
     */
    public static void setLocale(Locale locale) {
        CURRENT_LOCALE = locale;
    }

    private static void setTimeUnit(StringBuffer timeBuf, String unit) {
        timeBuf.insert(0, String.format("%-3s", unit));
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2018. 11. 15.		박준홍			최초 작성
     * </pre>
     *
     * @param locale
     *            등록하려는 {@link Locale} 정보
     * @param expr
     *            {@link TimeUnit} Expression
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @since 2018. 11. 15.
     */
    public static void setTimeUnitExpr(Locale locale, TimeUnitExpr expr) {
        setTimeUnitExpr(locale, expr, false);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2018. 11. 15.		박준홍			최초 작성
     * </pre>
     *
     * @param locale
     *            등록하려는 {@link Locale} 정보
     * @param expr
     *            {@link TimeUnit} Expression
     * @param setCurrentLocale
     *            현재 {@link Locale} 로 설정할지 여부.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @since 2018. 11. 15.
     */
    public static void setTimeUnitExpr(Locale locale, TimeUnitExpr expr, boolean setCurrentLocale) {
        if (setCurrentLocale) {
            CURRENT_LOCALE = locale;
        }

        TIME_UNITS.put(locale, expr);
    }

    private static void setTimeValue(StringBuffer timeBuf, long time) {
        timeBuf.insert(0, String.format("%,3d", time));
    }

    /**
     * Provide the Millisecond time value in {year}y {day}d {hour}h {minute}m {second}s {millisecond}ms {nanoseconds}ns.
     * <br>
     * Omitted if there is no value for that unit.
     * 
     * @param time
     *            time value.
     * @param timeUnit
     *            a unit of input time value.
     * @return
     *
     * @since 2018. 1. 9.
     */
    public static String toFormattedString(long time, TimeUnit timeUnit) {

        // if zero ...
        if (time < 1) {
            return "0 " + Arrays.stream(getTimeUnits().timeUnitInfo).filter(unit -> unit.unit == timeUnit).findAny().get().unitStr;
        }

        StringBuffer timeBuf = new StringBuffer();

        long mod = 0L;
        long up = time;

        for (TimeUnitInfo unit : FN_TIME_UNITS.apply(timeUnit)) {
            if (!OMITTED_TIME_UINITS.contains(unit.unit)) {
                mod = mod(up, unit.unit);
                prependTimeAndUnit(timeBuf, mod, unit);
            }

            up = up(up, unit.unit);

            if (up < 1) {
                return timeBuf.toString();
            }
        }

        prependTimeAndUnit(timeBuf, up, getTimeUnits().year);

        return timeBuf.toString();
    }

    private static long up(long time, TimeUnit unit) {
        switch (unit) {
            case NANOSECONDS: // to microsecond & above
            case MILLISECONDS: // to millsecond & above
            case MICROSECONDS: // to second & above
                return time / 1000;
            case SECONDS: // to minute & above
                return time / 60;
            case MINUTES: // to hour & above
                return time / 60;
            case HOURS: // to day & above
                return time / 24;
            case DAYS: // to year & above
                return time / 365;
            default:
                throw new IllegalArgumentException(unit.toString());
        }
    }

    /**
     * {@link TimeUnit} Expression String Builder.
     * 
     * @since 2018. 11. 15.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public static class TimeUnitBuilder {

        private TimeUnitInfo ns = new TimeUnitInfo(TimeUnit.NANOSECONDS, "ns");
        private TimeUnitInfo us = new TimeUnitInfo(TimeUnit.MICROSECONDS, "\u03bcs");
        private TimeUnitInfo ms = new TimeUnitInfo(TimeUnit.MILLISECONDS, "ms");
        private TimeUnitInfo s = new TimeUnitInfo(TimeUnit.SECONDS, "s");
        private TimeUnitInfo m = new TimeUnitInfo(TimeUnit.MINUTES, "m");
        private TimeUnitInfo h = new TimeUnitInfo(TimeUnit.HOURS, "h");
        private TimeUnitInfo d = new TimeUnitInfo(TimeUnit.DAYS, "d");
        private String year = "y";

        private TimeUnitBuilder() {
        }

        /**
         * 시간 정보를 제공한다. <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜    	| 작성자	|	내용
         * ------------------------------------------
         * 2018. 11. 15.		박준홍			최초 작성
         * </pre>
         *
         * @return
         *
         * @author Park_Jun_Hong_(fafanmama_at_naver_com)
         * @since 2018. 11. 15.
         */
        public TimeUnitExpr build() {
            return new TimeUnitExpr(year, new TimeUnitInfo[] { ns, us, ms, s, m, h, d });
        }

        /**
         * 
         * <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜    	| 작성자	|	내용
         * ------------------------------------------
         * 2018. 11. 15.		박준홍			최초 작성
         * </pre>
         *
         * @param unit
         * @param expr
         *
         * @author Park_Jun_Hong_(fafanmama_at_naver_com)
         * @since 2018. 11. 15.
         */
        public TimeUnitBuilder set(TimeUnit unit, String expr) {
            AssertUtils.assertNulls(IllegalArgumentException.class, unit, expr);

            switch (unit) {
                case NANOSECONDS:
                    ns = new TimeUnitInfo(unit, expr);
                    break;
                case MICROSECONDS:
                    us = new TimeUnitInfo(unit, expr);
                    break;
                case MILLISECONDS:
                    ms = new TimeUnitInfo(unit, expr);
                    break;
                case SECONDS:
                    s = new TimeUnitInfo(unit, expr);
                    break;
                case MINUTES:
                    m = new TimeUnitInfo(unit, expr);
                    break;
                case HOURS:
                    h = new TimeUnitInfo(unit, expr);
                    break;
                case DAYS:
                    d = new TimeUnitInfo(unit, expr);
                    break;
                default:
                    throw new IllegalArgumentException(unit.toString());
            }

            return this;
        }

        /**
         * Set a expression of Day(d). <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜    	| 작성자	|	내용
         * ------------------------------------------
         * 2018. 11. 15.		박준홍			최초 작성
         * </pre>
         *
         * @param expr
         *
         * @author Park_Jun_Hong_(fafanmama_at_naver_com)
         * @since 2018. 11. 15.
         */
        public TimeUnitBuilder setDay(String expr) {
            set(TimeUnit.DAYS, expr);
            return this;
        }

        /**
         * Set a expression of Hour(h) <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜    	| 작성자	|	내용
         * ------------------------------------------
         * 2018. 11. 15.		박준홍			최초 작성
         * </pre>
         *
         * @param expr
         *
         * @author Park_Jun_Hong_(fafanmama_at_naver_com)
         * @since 2018. 11. 15.
         */
        public TimeUnitBuilder setHour(String expr) {
            set(TimeUnit.HOURS, expr);
            return this;
        }

        /**
         * Set a expression of Microsecond(us). <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜    	| 작성자	|	내용
         * ------------------------------------------
         * 2018. 11. 15.		박준홍			최초 작성
         * </pre>
         *
         * @param expr
         *
         * @author Park_Jun_Hong_(fafanmama_at_naver_com)
         * @since 2018. 11. 15.
         */
        public TimeUnitBuilder setMicrosecond(String expr) {
            set(TimeUnit.MICROSECONDS, expr);
            return this;
        }

        /**
         * Set a expression of Millisecond(ms). <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜    	| 작성자	|	내용
         * ------------------------------------------
         * 2018. 11. 15.		박준홍			최초 작성
         * </pre>
         *
         * @param expr
         *
         * @author Park_Jun_Hong_(fafanmama_at_naver_com)
         * @since 2018. 11. 15.
         */
        public TimeUnitBuilder setMillisecond(String expr) {
            set(TimeUnit.MILLISECONDS, expr);
            return this;
        }

        /**
         * Set a expression of Minute(m). <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜    	| 작성자	|	내용
         * ------------------------------------------
         * 2018. 11. 15.		박준홍			최초 작성
         * </pre>
         *
         * @param expr
         *
         * @author Park_Jun_Hong_(fafanmama_at_naver_com)
         * @since 2018. 11. 15.
         */
        public TimeUnitBuilder setMinute(String expr) {
            set(TimeUnit.MINUTES, expr);
            return this;
        }

        /**
         * Set a expression of Nanosecond(ns). <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜    	| 작성자	|	내용
         * ------------------------------------------
         * 2018. 11. 15.		박준홍			최초 작성
         * </pre>
         *
         * @param expr
         *
         * @author Park_Jun_Hong_(fafanmama_at_naver_com)
         * @since 2018. 11. 15.
         */
        public TimeUnitBuilder setNanosecond(String expr) {
            set(TimeUnit.NANOSECONDS, expr);
            return this;
        }

        /**
         * Set a expression of Second(s). <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜    	| 작성자	|	내용
         * ------------------------------------------
         * 2018. 11. 15.		박준홍			최초 작성
         * </pre>
         *
         * @param expr
         *
         * @author Park_Jun_Hong_(fafanmama_at_naver_com)
         * @since 2018. 11. 15.
         */
        public TimeUnitBuilder setSecond(String expr) {
            set(TimeUnit.SECONDS, expr);
            return this;
        }

        public TimeUnitBuilder setYear(String expr) {
            this.year = expr;
            return this;
        }

        public static TimeUnitBuilder create() {
            return new TimeUnitBuilder();
        }

    }

    public static class TimeUnitExpr {
        private final String year;
        private final TimeUnitInfo[] timeUnitInfo;

        public TimeUnitExpr(String year, TimeUnitInfo[] timeUnits) {
            this.year = year;
            this.timeUnitInfo = timeUnits;
        }
    }

    private static class TimeUnitInfo {
        private final TimeUnit unit;
        private final String unitStr;

        public TimeUnitInfo(TimeUnit unit, String unitStr) {
            this.unit = unit;
            this.unitStr = unitStr;
        }
    }
}
