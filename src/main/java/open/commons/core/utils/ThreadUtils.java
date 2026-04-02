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
* This file is generated under this project, "open-commons-core".
*
* Date  : 2013. 5. 23. 오전 9:47:49
*
* Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
* 
*/

/**
 * 
 */
package open.commons.core.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.jspecify.annotations.Nullable;

/**
 * 쓰레드(Thread) 제어, 예외 추적 및 상태 정보를 제공하는 유틸리티 클래스입니다.
 *
 * @since 2013. 5. 23.
 */
// 아래 내용에 적용됨.
// - 대부분의 JDK 표준 API
// [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
// [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
@SuppressWarnings("null")
public class ThreadUtils {

    // Prevent to create a new instance.
    private ThreadUtils() {
    }

    /**
     * 이 메소드를 호출한 현재 메소드의 이름을 제공합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2013. 5. 23.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @return 현재 실행 중인 메소드 이름
     *
     * @since 2013. 5. 23.
     *
     * @see #getMethodName(int)
     */
    public static String getCurrentMethodName() {
        return getMethodName(1);
    }

    /**
     * {@link StackTraceElement} 정보를 이용하여 호출 스택 상의 특정 메소드 이름을 제공합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 12. 3.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param distance
     *            이 메소드를 호출한 메소드로부터의 거리. 자신의 메소드 이름을 얻기 위해서는 '0'을 입력합니다.
     *
     * @return 지정된 거리의 메소드 이름. 스택 거리를 초과한 경우 기본 안내 메시지 반환.
     *
     * @since 2021. 12. 3.
     * @version 1.8.0
     */
    public static String getMethodName(int distance) {
        Thread thread = Thread.currentThread();
        StackTraceElement[] stacks = thread.getStackTrace();

        return stacks.length < 3 + distance ? "Oops... I DO NOT know where here is." : stacks[2 + distance].getMethodName();
    }

    /**
     * 예외 및 에러 객체의 스택 트레이스(Stack Trace)를 문자열로 변환하여 제공합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2013. 5. 23.     parkjunhong77@gmail.com         최초 작성
     * 2026. 4. 2.      parkjunhong77@gmail.com         (개선) Exception &rarr; Throwable로 파라미터 타입 확장 및 try-with-resources 적용
     * </pre>
     *
     * @param e
     *            스택 트레이스를 추출할 최상위 예외/에러 객체
     *
     * @return 스택 트레이스가 기록된 문자열
     *
     * @throws NullPointerException
     *             파라미터({@code e})가 {@code null}인 경우 발생.
     *
     * @since 2013. 5. 23.
     */
    public static String getStackTrace(Throwable e) {
        Objects.requireNonNull(e);

        StringWriter writer = new StringWriter();
        // [최적화 & 안전성] Try-with-resources 구문으로 안전하게 자원 반납 처리
        try (PrintWriter pw = new PrintWriter(writer)) {
            e.printStackTrace(pw);
        }

        return writer.toString();
    }

    /**
     * 현재 실행 중인 {@link Thread}에 새로운 이름을 지정하고, 이전 이름을 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2013. 5. 23.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param name
     *            새롭게 지정할 쓰레드 이름
     *
     * @return 변경되기 전의 원래 쓰레드 이름
     *
     * @throws NullPointerException
     *             파라미터({@code name})가 {@code null}인 경우 발생.
     *
     * @since 2013. 5. 23.
     */
    public static String setThreadName(String name) {
        Objects.requireNonNull(name);

        Thread thread = Thread.currentThread();
        String otn = thread.getName();

        thread.setName(name);

        return otn;
    }

    /**
     * 지정된 시간(밀리초) 동안 현재 쓰레드의 실행을 일시 정지(Sleep)합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2013. 5. 23.     parkjunhong77@gmail.com         최초 작성
     * 2026. 4. 2.      parkjunhong77@gmail.com         (개선) InterruptedException 발생 시 쓰레드 인터럽트 상태 복구 로직 추가
     * </pre>
     *
     * @param millis
     *            일시 정지할 시간 (밀리초)
     *
     * @return 정상적으로 대기 시간을 채웠다면 {@code true}, 대기 중 {@link InterruptedException}이 발생하여 중단되었다면 {@code false}
     *
     * @since 2013. 5. 23.
     */
    public static boolean sleep(long millis) {
        boolean wait = true;
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            // [안전성] 쓰레드의 인터럽트 상태를 삼키지(Swallow) 않고 다시 설정(Restore)하여 상위 로직에 알림
            Thread.currentThread().interrupt();
            wait = false;
        }
        return wait;
    }

    /**
     * 여러 개의 {@link Runnable} 객체를 각각의 쓰레드로 래핑(Wrapping)하여 생성하고, 옵션에 따라 즉시 실행합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2026. 4. 2.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param autostart
     *            생성된 쓰레드들의 자동 실행({@link Thread#start()}) 여부
     * @param runnables
     *            쓰레드에서 실행할 작업 객체 가변 인자 (내부 요소가 {@code null}인 경우 해당 요소는 무시됨)
     *
     * @return 생성된 {@link Thread} 객체 목록
     *
     * @throws NullPointerException
     *             파라미터({@code runnables})가 {@code null}인 경우 발생.
     *
     * @since 2026. 4. 2.
     * @version 3.0.0
     */
    public static List<Thread> start(boolean autostart, @Nullable Runnable... runnables) {
        Objects.requireNonNull(runnables);

        List<Thread> threads = new ArrayList<Thread>(runnables.length);

        Thread thread = null;
        for (Runnable runnable : runnables) {
            if (runnable != null) {
                thread = new Thread(runnable);
                threads.add(thread);

                if (autostart) {
                    thread.start();
                }
            }
        }

        return threads;
    }
}