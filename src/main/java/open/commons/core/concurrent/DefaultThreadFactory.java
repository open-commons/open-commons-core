/*
 * Copyright 2020 Park Jun-Hong (parkjunhong77@gmail.com)
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
 * Date  : 2017. 9. 12. 오후 1:17:31
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.core.concurrent;

import java.util.Objects;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import org.jspecify.annotations.Nullable;

import open.commons.core.utils.AssertUtils2;

/**
 * JDK 25 환경에 최적화된 ThreadFactory 구현체입니다. *
 * 
 * <pre>
 * [개정이력]
 * 날짜      | 작성자   |   내용
 * ------------------------------------------
 * 2017. 9. 12.         parkjunhong77@gmail.com         최초 작성 (JDK 8)
 * 2026. 02. 26.        parkjunhong77@gmail.com         (3.0.0) JDK 25 마이그레이션: SecurityManager 제거 및 Thread.Builder 적용
 * </pre>
 *
 * @since 2017. 9. 12.
 * @version 3.0.0
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
public class DefaultThreadFactory implements ThreadFactory {

    private static final AtomicInteger POOL_NUMBER = new AtomicInteger(1);

    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;
    private final ThreadType threadType;
    @Nullable
    private final ThreadGroup group;

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2026. 2. 26.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param monitor
     *
     * @since 2017. 9.12
     */
    public DefaultThreadFactory(String monitor) {
        this(monitor, ThreadType.VIRTUAL);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2026. 2. 26.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param monitor
     * @param threadType
     *
     * @since 2026. 2. 26.
     * @version 3.0.0
     */
    // 아래 내용에 적용됨.
    // - String.format(...)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public DefaultThreadFactory(String monitor, ThreadType threadType) {
        AssertUtils2.notNulls(monitor, threadType);

        this.threadType = threadType;
        this.group = Thread.currentThread().getThreadGroup();
        this.namePrefix = String.format("<%s> %s-pool-%d-thread-", monitor, threadType.name().toLowerCase(), POOL_NUMBER.getAndIncrement());
    }

    /**
     * 
     * @see java.util.concurrent.ThreadFactory#newThread(java.lang.Runnable)
     */
    @SuppressWarnings("null")
    @Override
    public @Nullable Thread newThread(Runnable r) {
        Objects.requireNonNull(r);

        String threadName = namePrefix + threadNumber.getAndIncrement();

        return switch (threadType) {
            // 1. 가상 스레드 생성 (Loom)
            case VIRTUAL -> Thread.ofVirtual() //
                    .name(threadName) //
                    .unstarted(r);
            // 2. 플랫폼 스레드 생성 (Classic)
            case PLATFORM -> Thread.ofPlatform() //
                    .group(group) //
                    .name(threadName) //
                    .daemon(false) //
                    .priority(Thread.NORM_PRIORITY).unstarted(r);
        };
    }

    public enum ThreadType {
        PLATFORM, VIRTUAL
    }

}
