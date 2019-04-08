/*
 * Copyright 2019 Park Jun-Hong (parkjunhong77/google/com)
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
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package open.commons.test;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

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
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
public class StopWatch {
    /** 종료 구간 식별자 */
    public static final String LAST = UUID.nameUUIDFromBytes(String.valueOf(System.nanoTime()).getBytes()).toString();
    private final ReentrantLock lock = new ReentrantLock();
    /** 경과시간 기록 */
    private final ConcurrentSkipListMap<String, Record> records = new ConcurrentSkipListMap<>();
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
     * 이름에 해당하는 기록을 제공한다. <br>
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
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
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
     * 전체 경과시간을 제공한다. <br>
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
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
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
     * 이름에 해당하는 기록을 제공한다. <br>
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
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
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
     * 전체 경과시간을 문자열로 제공한다. <br>
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
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @since 2019. 2. 20.
     */
    public String getAsPretty() {
        return pretty(get());
    }

    /**
     * 이름에 해당하는 기록을 문자열로 제공한다. <br>
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
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
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
     * 주어진 이름에 해당하는 기록 존재여부를 제공한다. <br>
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
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
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
     * 기록측정을 일시정지한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 2. 20.		박준홍			최초 작성
     * </pre>
     *
     *
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
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
     * nanosecond 를 경과시간 포맷으로 제공한다. <br>
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
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @since 2019. 2. 20.
     */
    private String pretty(Long r) {
        if (r == null) {
            return null;
        }

        return TimeUtils.toFormattedString(r, TimeUnit.NANOSECONDS);
    }

    /**
     * 주어진 이름으로 기록을 저장한다. <br>
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
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
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
        // 현재 시간 - 이전 기록 시간 - 일시정지된 만큼.
        Long r = cur - this.record - this.paused;
        records.put(name, new Record(this.record, cur, this.paused, this.pausedAcc));

        this.record = cur;
        this.paused = 0;

        return r;
    }

    /**
     * 기록을 저장하고, 일시정지를 한다. <br>
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
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
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
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
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
        } finally {
            this.lock.unlock();
        }
    }

    /**
     * 
     * 기록측정을 시작한다.<br>
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
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
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
     * 기록측정을 종료한다. <br>
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
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
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
        return "StopWatch [state=" + state + ", begin=" + begin + ", end=" + end + ", record=" + record + ", pause=" + pause + ", paused=" + paused + ", pausedAcc=" + pausedAcc
                + ", recrods=" + records + "]";
    }

    private void updatePaused(long cur) {
        this.paused += (cur - this.pause);
        this.pausedAcc += this.paused;
    }

    /**
     * 기록측정을 시작한 객체를 제공한다. <br>
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
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
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
         * @author Park_Jun_Hong_(fafanmama_at_naver_com)
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
         * @author Park_Jun_Hong_(fafanmama_at_naver_com)
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
            return "Record [start=" + start + ", stop=" + stop + ", paused=" + paused + ", pausedAcc=" + pausedAcc + "]";
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
