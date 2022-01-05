/*
 * Copyright 2019 Park Jun-Hong (parkjunhong77@gmail.com)
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
 * Date  : 2019. 2. 20. 오후 4:40:29
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.test;

import java.util.Map;
import java.util.UUID;
import java.util.Vector;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import open.commons.utils.StringUtils;
import open.commons.utils.TimeUtils;

/**
 * Nanosecond 단위로 경과시간을 기록하는 클래스.<br>
 * <ul>
 * <li>시작: {@link #start()}
 * <li>기록: {@link #record}
 * <li>기록확인: {@link #get(String)}
 * <li>종료: {@link #stop()}
 * <li>마지막 기록확인: {@link #LAST}를 {@link #get(String)} 파라미터로 이용.
 * <li>전체 경과시간: {@link #get()}
 * <li>초기화: {@link #reset()}
 * </ul>
 * 
 * @since 2019. 2. 20.
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public class StopWatch {
    /** 종료 구간 식별자 */
    public static final String LAST = UUID.nameUUIDFromBytes(String.valueOf(System.nanoTime()).getBytes()).toString();
    private final ReentrantLock lock = new ReentrantLock();
    /** 경과시간 기록 */
    private final ConcurrentSkipListMap<String, Record> records = new ConcurrentSkipListMap<>();
    /** 기록된 순서대로 이름을 저장 */
    private final Vector<String> recordNames = new Vector<>();
    /** 상태 */
    private State state = State.READY;
    /** 시작 시간 */
    private long begin;
    /** 기록 저장 시간 */
    private long record;
    /** 종료 시간 */
    private long end;
    /** 일시 정지된 시간 */
    private long pause;
    /** 구간별 일시 정지된 시간 */
    private long paused;
    /** 전체 일시 정지된 시간 */
    private long pausedAcc;

    private boolean expect(State... states) {
        for (State state : states) {
            if (this.state.equals(state)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 이름에 해당하는 기록을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 2. 20.		박준홍			최초 작성
     * </pre>
     *
     * @param name
     * @return
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2019. 2. 20.
     * 
     * @see Map#get(Object)
     */
    public Long fromBegin(String name) {
        this.lock.lock();
        try {
            Record r = records.get(name);
            return r == null ? null : r.fromBegin(this.begin);
        } finally {
            this.lock.unlock();
        }
    }

    public String fromBeginAsPretty(String name) {
        return pretty(fromBegin(name));
    }

    /**
     * 전체 경과시간을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 2. 20.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2019. 2. 20.
     */
    public Long get() {
        this.lock.lock();
        try {
            if (!expect(State.STOPPED)) {
                throw new IllegalStateException("StopWatch is not stopped. state=" + toString());
            }
            return this.end - this.begin;
        } finally {
            this.lock.unlock();
        }
    }

    /**
     * 이름에 해당하는 기록을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 2. 20.		박준홍			최초 작성
     * </pre>
     *
     * @param name
     * @return
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2019. 2. 20.
     * 
     * @see Map#get(Object)
     */
    public Long get(String name) {
        this.lock.lock();
        try {
            Record r = records.get(name);
            return r == null ? null : r.get();
        } finally {
            this.lock.unlock();
        }
    }

    /**
     * 전체 경과시간을 문자열로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 2. 20.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2019. 2. 20.
     */
    public String getAsPretty() {
        return pretty(get());
    }

    /**
     * 이름에 해당하는 기록을 문자열로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 2. 20.		박준홍			최초 작성
     * </pre>
     *
     * @param name
     * @return
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2019. 2. 20.
     */
    public String getAsPretty(String name) {
        Long r = get(name);
        if (r == null) {
            return null;
        }

        return pretty(r);
    }

    /**
     * 주어진 이름에 해당하는 기록 존재여부를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 2. 20.		박준홍			최초 작성
     * </pre>
     *
     * @param name
     * @return
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2019. 2. 20.
     * 
     * @see Map#containsKey(Object)
     */
    public boolean has(String name) {
        this.lock.lock();
        try {
            return records.containsKey(name);
        } finally {
            this.lock.unlock();
        }
    }

    /**
     * 기록측정을 일시정지합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 2. 20.		박준홍			최초 작성
     * </pre>
     *
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2019. 2. 20.
     */
    public void pause() {
        this.lock.lock();
        try {
            if (!expect(State.RUNNING)) {
                throw new IllegalStateException("StopWatch is not running. state=" + toString());
            }
            this.pause = System.nanoTime();
            this.state = State.PAUSED;
        } finally {
            this.lock.unlock();
        }

    }

    /**
     * nanosecond 를 경과시간 포맷으로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 2. 20.		박준홍			최초 작성
     * </pre>
     *
     * @param r
     * @return
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2019. 2. 20.
     */
    private String pretty(Long r) {
        if (r == null) {
            return null;
        }

        return TimeUtils.toFormattedString(r, TimeUnit.NANOSECONDS);
    }

    /**
     * 주어진 이름으로 기록을 저장합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 2. 20.		박준홍			최초 작성
     * </pre>
     *
     * @param name
     * @return 기록 정보.
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2019. 2. 20.
     */
    public Long record(String name) {
        this.lock.lock();
        try {
            long cur = System.nanoTime();
            if (!expect(State.RUNNING)) {
                throw new IllegalStateException("StopWatch is not running. state=" + toString());
            }
            return record(name, cur);
        } finally {
            this.lock.unlock();
        }
    }

    private Long record(String name, long cur) {
        assert name == null;
        assert cur < 1;

        // 현재 시간 - 이전 기록 시간 - 일시정지된 만큼.
        Long r = cur - this.record - this.paused;
        this.records.put(name, new Record(this.record, cur, this.paused, this.pausedAcc));

        this.record = cur;
        this.paused = 0;

        // 기록이름 저장. 2022.01.05
        this.recordNames.add(name);

        return r;
    }

    /**
     * 기록을 저장하고, 일시정지를 합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 2. 20.		박준홍			최초 작성
     * </pre>
     *
     * @param name
     * @return
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2019. 2. 20.
     */
    public Long recordAndPause(String name) {
        this.lock.lock();
        try {
            Long r = record(name);
            pause();
            return r;
        } finally {
            this.lock.unlock();
        }
    }

    /**
     * 모든 정보를 초기화 시킨다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 2. 20.		박준홍			최초 작성
     * </pre>
     *
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2019. 2. 20.
     */
    public void reset() {
        this.lock.lock();
        try {
            this.begin = 0;
            this.end = 0;
            this.record = 0;
            this.pause = 0;
            this.paused = 0;
            this.state = State.READY;

            this.records.clear();
            this.recordNames.clear();
        } finally {
            this.lock.unlock();
        }
    }

    /**
     * 
     * 기록측정을 시작합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 2. 20.		박준홍			최초 작성
     * </pre>
     * 
     * @return
     *
     * @throws IllegalStateException
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2019. 2. 20.
     */
    public StopWatch start() throws IllegalStateException {
        this.lock.lock();
        try {
            long cur = System.nanoTime();
            switch (this.state) {
                case READY:
                    this.begin = this.record = cur;
                    break;
                case PAUSED:
                    updatePaused(cur);
                    break;
                default:
                    throw new IllegalStateException("StopWatch is not ready. state=" + toString());
            }

            this.state = State.RUNNING;

            return this;
        } finally {
            this.lock.unlock();
        }
    }

    /**
     * 모든 기록내용을 보여줍니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 1. 5.		박준홍			최초 작성
     * </pre>
     * 
     * @return
     *
     * @since 2022. 1. 5.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see #stats(boolean)
     */
    public String stats() {
        return stats(false);
    }

    /**
     * 모든 기록내용을 보여줍니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 1. 5.		박준홍			최초 작성
     * </pre>
     * 
     * @param alsoLast
     *            마지막 작업 포함 여부
     *
     * @return
     *
     * @since 2022. 1. 5.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public String stats(boolean alsoLast) {
        this.lock.lock();
        try {
            if (!expect(State.STOPPED)) {
                throw new IllegalStateException("StopWatch is not stopped. state=" + toString());
            }

            StringBuilder buf = new StringBuilder();
            buf.append(String.format("%-10s\t%4s\t%s", "작업이름", "비율(%)", "경과시간(ns)"));
            buf.append("\n--------------------------------------------------");
            String fmt = "%-10s\t%3.1f%%\t%d (%s)";
            final long total = this.get();
            this.recordNames.forEach(rn -> {
                if (!alsoLast && LAST.equals(rn)) {
                    return;
                }

                buf.append("\n");
                long t = get(rn);
                buf.append(String.format(fmt //
                , StringUtils.compact(LAST.equals(rn) ? "마지막" : rn, 10) // 작업이름
                , (double) t / total * 100 // 전체 소유시간 대비 비율
                , t // 경과 시간 (단위: nano seconds)
                , getAsPretty(rn).trim().replaceAll("\\s{2,}", " ")));
            });

            return buf.toString();
        } finally {
            this.lock.unlock();
        }
    }

    // private static final

    /**
     * 기록측정을 종료합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 2. 20.		박준홍			최초 작성
     * </pre>
     *
     *
     * @return
     * @throws IllegalStateException
     * 
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2019. 2. 20.
     */
    public StopWatch stop() throws IllegalStateException {
        this.lock.lock();
        try {
            long cur = System.nanoTime();
            switch (this.state) {
                case PAUSED:
                    updatePaused(cur);
                case RUNNING:
                    this.end = cur;
                    record(LAST, cur);
                    this.paused = 0;
                    break;
                default:
                    throw new IllegalStateException("StopWatch is not running. state=" + toString());
            }

            this.state = State.STOPPED;

            return this;
        } finally {
            this.lock.unlock();
        }
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("StopWatch [state=");
        builder.append(state);
        builder.append(", begin=");
        builder.append(begin);
        builder.append(", end=");
        builder.append(end);
        builder.append(", pause=");
        builder.append(pause);
        builder.append(", paused=");
        builder.append(paused);
        builder.append(", pausedAcc=");
        builder.append(pausedAcc);
        builder.append(", record=");
        builder.append(record);
        builder.append(", records=");
        builder.append(records);
        builder.append("]");
        return builder.toString();
    }

    private void updatePaused(long cur) {
        this.paused += (cur - this.pause);
        this.pausedAcc += this.paused;
    }

    /**
     * 기록측정을 시작한 객체를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 2. 20.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2019. 2. 20.
     */
    public static StopWatch startNow() {
        StopWatch watch = new StopWatch();
        watch.start();
        return watch;
    }

    private class Record {
        final long start;
        final long stop;
        final long paused;
        final long pausedAcc;

        /**
         * 
         * @param pausedAcc
         *            TODO
         * @since 2019. 2. 20.
         */
        public Record(long start, long stop, long paused, long pausedAcc) {
            this.start = start;
            this.stop = stop;
            this.paused = paused;
            this.pausedAcc = pausedAcc;
        }

        /**
         * 시작부터 구간까지 경과시간. <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜    	| 작성자	|	내용
         * ------------------------------------------
         * 2019. 2. 20.		박준홍			최초 작성
         * </pre>
         *
         * @param begin
         * @return
         *
         * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
         * @since 2019. 2. 20.
         */
        public long fromBegin(long begin) {
            return this.stop - begin - this.pausedAcc;
        }

        /**
         * 경과시간. <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜    	| 작성자	|	내용
         * ------------------------------------------
         * 2019. 2. 20.		박준홍			최초 작성
         * </pre>
         *
         * @return
         *
         * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
         * @since 2019. 2. 20.
         */
        public long get() {
            return this.stop - this.start - this.paused;
        }

        /**
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("Record [start=");
            builder.append(start);
            builder.append(", stop=");
            builder.append(stop);
            builder.append(", paused=");
            builder.append(paused);
            builder.append(", pausedAcc=");
            builder.append(pausedAcc);
            builder.append("]");
            return builder.toString();
        }
    }

    @SuppressWarnings("unused")
    private static enum State {
        READY(0), RUNNING(1), PAUSED(2), STOPPED(3);

        private int state;

        private State(int s) {
            this.state = s;
        }

        public int get() {
            return this.state;
        }
    }

}
