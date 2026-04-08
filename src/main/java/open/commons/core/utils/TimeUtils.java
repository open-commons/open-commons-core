/*
 * Copyright 2018 Park Jun-Hong (parkjunhong77@gmail.com)
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
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.core.utils;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * 시간 및 단위(TimeUnit) 변환과 포맷팅을 지원하는 유틸리티 클래스입니다.
 *
 * <pre>
 * [개정이력]
 * 날짜      | 작성자   |   내용
 * ------------------------------------------
 * 2018. 1. 9.      parkjunhong77@gmail.com         최초 작성
 * 2018. 11. 15.    parkjunhong77@gmail.com         TimeUnit Expression String Builder 추가. (Default, Korea 기본 적용)
 * 2026. 4. 2.      parkjunhong77@gmail.com         (3.0.0) Thread-Safe 구조(Concurrent) 개편, StringBuilder 최적화, Javadoc 규격 적용
 * </pre>
 *
 * @since 2018. 1. 9.
 * @version 3.0.0
 */
public class TimeUtils {

    /** default time unit info */
    private static final TimeUnitExpr DEFAULT_TIME_UNIT_DEF = TimeUnitBuilder.create().build();

    /** default locale */
    private static final Locale DEFAULT_LOCALE = LocaleUtils.defaultLocale();

    // [안전성] 멀티 쓰레드 환경에서의 가시성(Visibility) 보장을 위한 volatile 적용
    private static volatile Locale currentLocale = DEFAULT_LOCALE;

    // [안전성] 동시성 환경에서 안전한 ConcurrentHashMap으로 변경
    private static final ConcurrentHashMap<Locale, TimeUnitExpr> TIME_UNITS = new ConcurrentHashMap<>();

    static {
        // set Default
        TIME_UNITS.put(DEFAULT_LOCALE, DEFAULT_TIME_UNIT_DEF);

        // set Korea
        TIME_UNITS.put(Locale.KOREA, TimeUnitBuilder.create().setSecond("초").setMinute("분").setHour("시간").setDay("일").setYear("년").build());
    }

    private static final Function<TimeUnit, TimeUnitInfo[]> FN_TIME_UNITS = unit -> {
        ArrayList<TimeUnitInfo> units = new ArrayList<>();

        for (TimeUnitInfo tui : getTimeUnits().timeUnitInfo) {
            if (tui.unit.ordinal() >= unit.ordinal()) {
                units.add(tui);
            }
        }

        return units.toArray(new TimeUnitInfo[0]);
    };

    /** 제외시킬 시간 단위 (Thread-Safe Set으로 변경 및 오타 교정) */
    @SuppressWarnings("null")
    private static final ConcurrentHashMap.KeySetView<TimeUnit, Boolean> OMITTED_TIME_UNITS = ConcurrentHashMap.newKeySet();

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
     * 포맷 문자열에서 제외할 시간 단위를 추가합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 11. 15.    parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param units
     *            제외할 시간 단위 가변 인자
     *
     * @since 2018. 11. 15.
     */
    // 아래 내용에 적용됨.
    // - ObjectUtils.requireNonNulls((Object[]) units);
    // [PATCH] 배열 공변성/가변성에 대한 IDE 분석기의 오탐 우회
    // [TODO] 향후 IDE의 배열 데이터 흐름 분석이 고도화되거나 JSpecify가 완벽히 지원되면 '제거'
    @SuppressWarnings("null")
    public static void addOmit(TimeUnit... units) {
        ObjectUtils.requireNonNulls((Object[]) units);

        for (TimeUnit unit : units) {
            OMITTED_TIME_UNITS.add(unit);
        }
    }

    @SuppressWarnings("null")
    private static final TimeUnitExpr getTimeUnits() {
        // [안전성/최적화] getOrDefault를 활용하여 Race Condition 방지
        return TIME_UNITS.getOrDefault(currentLocale, DEFAULT_TIME_UNIT_DEF);
    }

    private static long mod(long time, TimeUnit unit) {
        return switch (unit) {
            case NANOSECONDS, MILLISECONDS, MICROSECONDS -> time % 1000;
            case SECONDS -> time % 60;
            case MINUTES -> time % 60;
            case HOURS -> time % 24;
            case DAYS -> time % 365;
        };
    }

    /**
     * 지정된 문자열 버퍼의 맨 앞에 계산된 시간과 단위를 추가합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 1. 9.      parkjunhong77@gmail.com         최초 작성
     * 2026. 4. 2.      parkjunhong77@gmail.com         (3.0.0) StringBuffer &rarr; StringBuilder 로 최적화
     * </pre>
     *
     * @param timeBuf
     *            문자열 빌더 버퍼
     * @param time
     *            계산된 시간 값
     * @param unit
     *            시간 단위 문자열
     *
     * @since 2018. 1. 9.
     * @version 3.0.0
     */
    private static void prependTimeAndUnit(StringBuilder timeBuf, long time, String unit) {
        if (time < 1) {
            return;
        }

        if (timeBuf.length() > 0) {
            timeBuf.insert(0, " ");
        }

        setTimeUnit(timeBuf, unit);
        setTimeValue(timeBuf, time);
    }

    private static void prependTimeAndUnit(StringBuilder timeBuf, long time, TimeUnitInfo unitInfo) {
        prependTimeAndUnit(timeBuf, time, unitInfo.unitStr);
    }

    /**
     * 포맷 문자열 제외 목록에서 특정 단위를 제거합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 11. 15.    parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param units
     *            제거할 시간 단위 가변 인자
     *
     * @since 2018. 11. 15.
     */
    // 아래 내용에 적용됨.
    // - ObjectUtils.requireNonNulls((Object[]) units);
    // [PATCH] 배열 공변성/가변성에 대한 IDE 분석기의 오탐 우회
    // [TODO] 향후 IDE의 배열 데이터 흐름 분석이 고도화되거나 JSpecify가 완벽히 지원되면 '제거'
    @SuppressWarnings("null")
    public static void removeOmit(TimeUnit... units) {
        ObjectUtils.requireNonNulls((Object[]) units);

        for (TimeUnit unit : units) {
            OMITTED_TIME_UNITS.remove(unit);
        }
    }

    /**
     * 유틸리티 내에서 사용할 현재 로케일({@link Locale})을 설정합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 11. 15.    parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param locale
     *            설정하려는 로케일
     *
     * @since 2018. 11. 15.
     */
    public static void setLocale(Locale locale) {
        Objects.requireNonNull(locale);

        currentLocale = locale;
    }

    private static void setTimeUnit(StringBuilder timeBuf, String unit) {
        timeBuf.insert(0, String.format("%-3s", unit));
    }

    /**
     * 특정 로케일에 대한 {@link TimeUnit} 표현식을 등록합니다. (현재 로케일은 변경하지 않음)
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 11. 15.    parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param locale
     *            등록하려는 {@link Locale} 정보
     * @param expr
     *            등록할 {@link TimeUnit} Expression
     *
     * @since 2018. 11. 15.
     */
    public static void setTimeUnitExpr(Locale locale, TimeUnitExpr expr) {
        setTimeUnitExpr(locale, expr, false);
    }

    /**
     * 특정 로케일에 대한 {@link TimeUnit} 표현식을 등록하고, 선택적으로 현재 로케일을 변경합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 11. 15.    parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param locale
     *            등록하려는 {@link Locale} 정보
     * @param expr
     *            등록할 {@link TimeUnit} Expression
     * @param setCurrentLocale
     *            현재 {@link Locale}로 설정할지 여부
     *
     * @since 2018. 11. 15.
     */
    public static void setTimeUnitExpr(Locale locale, TimeUnitExpr expr, boolean setCurrentLocale) {
        Objects.requireNonNull(locale);
        Objects.requireNonNull(expr);

        if (setCurrentLocale) {
            currentLocale = locale;
        }

        TIME_UNITS.put(locale, expr);
    }

    private static void setTimeValue(StringBuilder timeBuf, long time) {
        timeBuf.insert(0, String.format("%,3d", time));
    }

    /**
     * 입력된 시간을 {year}y {day}d {hour}h {minute}m {second}s {millisecond}ms {nanoseconds}ns 포맷의 문자열로 제공합니다. <br>
     * 해당 단위의 값이 0이거나 Omit 목록에 추가된 경우 문자열에서 제외됩니다.
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 1. 9.      parkjunhong77@gmail.com         최초 작성
     * 2026. 4. 2.      parkjunhong77@gmail.com         (3.0.0) Stream 필터 제거 및 배열 직접 참조 최적화, StringBuilder 적용
     * </pre>
     *
     * @param time
     *            변환할 시간 값
     * @param timeUnit
     *            입력된 시간 값의 단위
     *
     * @return 포맷팅된 시간 문자열
     *
     * @since 2018. 1. 9.
     * @version 3.0.0
     */
    // 아래 내용에 적용됨.
    // - timeBuf.toString()
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static String toFormattedString(long time, TimeUnit timeUnit) {
        Objects.requireNonNull(timeUnit);

        // if zero ...
        if (time < 1) {
            // [최적화] Stream.filter() 대신 상수 시간(O(1))인 배열 인덱스 직접 참조로 변경
            return "0 " + getTimeUnits().timeUnitInfo[timeUnit.ordinal()].unitStr;
        }

        // [최적화] StringBuffer 대신 StringBuilder 사용
        StringBuilder timeBuf = new StringBuilder();

        long mod = 0L;
        long up = time;

        for (TimeUnitInfo unit : FN_TIME_UNITS.apply(timeUnit)) {
            if (!OMITTED_TIME_UNITS.contains(unit.unit)) {
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
            case MILLISECONDS: // to millisecond & above
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
     * {@link TimeUnit} 단위별 표현식을 설정하기 위한 빌더(Builder) 클래스입니다.
     *
     * @since 2018. 11. 15.
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
         * 구성된 단위 표현식 정보를 기반으로 {@link TimeUnitExpr} 객체를 생성하여 반환합니다.
         *
         * <pre>
         * [개정이력]
         * 날짜      | 작성자   |   내용
         * ------------------------------------------
         * 2018. 11. 15.    parkjunhong77@gmail.com         최초 작성
         * </pre>
         *
         * @return 생성된 {@link TimeUnitExpr} 객체
         *
         * @since 2018. 11. 15.
         */
        public TimeUnitExpr build() {
            return new TimeUnitExpr(year, new TimeUnitInfo[] { ns, us, ms, s, m, h, d });
        }

        /**
         * 특정 시간 단위({@link TimeUnit})에 대한 문자열 표현식을 설정합니다.
         *
         * <pre>
         * [개정이력]
         * 날짜      | 작성자   |   내용
         * ------------------------------------------
         * 2018. 11. 15.    parkjunhong77@gmail.com         최초 작성
         * </pre>
         *
         * @param unit
         *            설정할 시간 단위
         * @param expr
         *            설정할 문자열 표현식
         *
         * @return 빌더 자기 자신 (Method Chaining 지원)
         * @throws IllegalArgumentException
         *             지원하지 않는 시간 단위가 입력된 경우 발생.
         *
         * @since 2018. 11. 15.
         */
        public TimeUnitBuilder set(TimeUnit unit, String expr) {
            Objects.requireNonNull(unit);
            Objects.requireNonNull(expr);

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
         * 일(Day) 단위의 문자열 표현식을 설정합니다.
         *
         * <pre>
         * [개정이력]
         * 날짜      | 작성자   |   내용
         * ------------------------------------------
         * 2018. 11. 15.    parkjunhong77@gmail.com         최초 작성
         * </pre>
         *
         * @param expr
         *            설정할 문자열 표현식
         *
         * @return 빌더 자기 자신
         *
         * @since 2018. 11. 15.
         */
        public TimeUnitBuilder setDay(String expr) {
            set(TimeUnit.DAYS, expr);
            return this;
        }

        /**
         * 시간(Hour) 단위의 문자열 표현식을 설정합니다.
         *
         * <pre>
         * [개정이력]
         * 날짜      | 작성자   |   내용
         * ------------------------------------------
         * 2018. 11. 15.    parkjunhong77@gmail.com         최초 작성
         * </pre>
         *
         * @param expr
         *            설정할 문자열 표현식
         *
         * @return 빌더 자기 자신
         *
         * @since 2018. 11. 15.
         */
        public TimeUnitBuilder setHour(String expr) {
            set(TimeUnit.HOURS, expr);
            return this;
        }

        /**
         * 마이크로초(Microsecond) 단위의 문자열 표현식을 설정합니다.
         *
         * <pre>
         * [개정이력]
         * 날짜      | 작성자   |   내용
         * ------------------------------------------
         * 2018. 11. 15.    parkjunhong77@gmail.com         최초 작성
         * </pre>
         *
         * @param expr
         *            설정할 문자열 표현식
         *
         * @return 빌더 자기 자신
         *
         * @since 2018. 11. 15.
         */
        public TimeUnitBuilder setMicrosecond(String expr) {
            set(TimeUnit.MICROSECONDS, expr);
            return this;
        }

        /**
         * 밀리초(Millisecond) 단위의 문자열 표현식을 설정합니다.
         *
         * <pre>
         * [개정이력]
         * 날짜      | 작성자   |   내용
         * ------------------------------------------
         * 2018. 11. 15.    parkjunhong77@gmail.com         최초 작성
         * </pre>
         *
         * @param expr
         *            설정할 문자열 표현식
         *
         * @return 빌더 자기 자신
         *
         * @since 2018. 11. 15.
         */
        public TimeUnitBuilder setMillisecond(String expr) {
            set(TimeUnit.MILLISECONDS, expr);
            return this;
        }

        /**
         * 분(Minute) 단위의 문자열 표현식을 설정합니다.
         *
         * <pre>
         * [개정이력]
         * 날짜      | 작성자   |   내용
         * ------------------------------------------
         * 2018. 11. 15.    parkjunhong77@gmail.com         최초 작성
         * </pre>
         *
         * @param expr
         *            설정할 문자열 표현식
         *
         * @return 빌더 자기 자신 খানকে
         *
         * @since 2018. 11. 15.
         */
        public TimeUnitBuilder setMinute(String expr) {
            set(TimeUnit.MINUTES, expr);
            return this;
        }

        /**
         * 나노초(Nanosecond) 단위의 문자열 표현식을 설정합니다.
         *
         * <pre>
         * [개정이력]
         * 날짜      | 작성자   |   내용
         * ------------------------------------------
         * 2018. 11. 15.    parkjunhong77@gmail.com         최초 작성
         * </pre>
         *
         * @param expr
         *            설정할 문자열 표현식
         *
         * @return 빌더 자기 자신
         *
         * @since 2018. 11. 15.
         */
        public TimeUnitBuilder setNanosecond(String expr) {
            set(TimeUnit.NANOSECONDS, expr);
            return this;
        }

        /**
         * 초(Second) 단위의 문자열 표현식을 설정합니다.
         *
         * <pre>
         * [개정이력]
         * 날짜      | 작성자   |   내용
         * ------------------------------------------
         * 2018. 11. 15.    parkjunhong77@gmail.com         최초 작성
         * </pre>
         *
         * @param expr
         *            설정할 문자열 표현식
         *
         * @return 빌더 자기 자신
         *
         * @since 2018. 11. 15.
         */
        public TimeUnitBuilder setSecond(String expr) {
            set(TimeUnit.SECONDS, expr);
            return this;
        }

        /**
         * 연(Year) 단위의 문자열 표현식을 설정합니다.
         *
         * <pre>
         * [개정이력]
         * 날짜      | 작성자   |   내용
         * ------------------------------------------
         * 2018. 11. 15.    parkjunhong77@gmail.com         최초 작성
         * </pre>
         *
         * @param expr
         *            설정할 문자열 표현식
         *
         * @return 빌더 자기 자신
         *
         * @since 2018. 11. 15.
         */
        public TimeUnitBuilder setYear(String expr) {
            Objects.requireNonNull(expr);

            this.year = expr;
            return this;
        }

        /**
         * 새로운 {@link TimeUnitBuilder} 인스턴스를 생성하여 반환합니다.
         *
         * @return {@link TimeUnitBuilder} 인스턴스
         */
        public static TimeUnitBuilder create() {
            return new TimeUnitBuilder();
        }
    }

    /**
     * 시간 단위 및 문자열 표현식을 매핑하여 보관하는 데이터 클래스입니다.
     */
    public static class TimeUnitExpr {
        private final String year;
        private final TimeUnitInfo[] timeUnitInfo;

        // 아래 내용에 적용됨.
        // - ObjectUtils.requireNonNulls((Object[]) timeUnits);
        // [PATCH] 배열 공변성/가변성에 대한 IDE 분석기의 오탐 우회
        // [TODO] 향후 IDE의 배열 데이터 흐름 분석이 고도화되거나 JSpecify가 완벽히 지원되면 '제거'
        @SuppressWarnings("null")
        public TimeUnitExpr(String year, TimeUnitInfo[] timeUnits) {
            Objects.requireNonNull(year);
            ObjectUtils.requireNonNulls((Object[]) timeUnits);

            this.year = year;
            this.timeUnitInfo = timeUnits;
        }
    }

    /**
     * 개별 {@link TimeUnit}과 그에 매칭되는 포맷 문자열 정보를 보관하는 데이터 클래스입니다.
     */
    private static class TimeUnitInfo {
        private final TimeUnit unit;
        private final String unitStr;

        public TimeUnitInfo(TimeUnit unit, String unitStr) {
            Objects.requireNonNull(unit);
            Objects.requireNonNull(unitStr);

            this.unit = unit;
            this.unitStr = unitStr;
        }
    }
}