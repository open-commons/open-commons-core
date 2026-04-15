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
* This file is generated under this project, "open-commons-core".
*
* Date  : 2015. 1. 13. 오전 8:37:55
*
* Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
* 
*/

package open.commons.core.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Supplier;

import org.jspecify.annotations.Nullable;

/**
 * 
 * @since 2015. 1. 13.
 */
public class ExceptionUtils {

    private static String getMessage(Throwable e) {
        String msg = e.getMessage();
        return msg != null ? msg : "null";
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 7. 5.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <E>
     * @param type
     * @param args
     * @param format
     * @param msgArgs
     * @return
     *
     * @since 2021. 7. 5.
     * @version 1.8.0
     */
    public static <E extends Throwable> E newException(Class<E> type, Class<?> @Nullable [] argTypes, @Nullable Object @Nullable [] args, @Nullable String format,
            @Nullable Object @Nullable... msgArgs) {
        return newException(type, () -> argTypes, args, format, msgArgs);
    }

    /**
     * 예외객체를 생성합니다. <br>
     * 단 예외타입은 문자열 1개를 파라미터로 받는 생성자가 필요하다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 15.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <E>
     * @param type
     *            예외 타입
     * @param format
     *            로그 포맷
     * @param args
     *            로그 데이터
     * @return
     *
     * @since 2020. 10. 15.
     */
    public static <E extends Throwable> E newException(Class<E> type, @Nullable String format, @Nullable Object @Nullable... args) {
        return newException(type, () -> null, null, format, args);
    }

    /**
     * 리플렉션을 이용하여 다양한 생성자 조건에 맞춰 동적으로 예외 객체를 생성합니다. <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2021. 7. 5.      parkjunhong77@gmail.com     최초 작성
     * 2026. 3. 30.     parkjunhong77@gmail.com     생성자 덮어쓰기 버그 수정 및 예외 체이닝(Chaining) 적용
     * </pre>
     * 
     * @param <E>
     *            예외 타입
     * @param type
     *            생성할 예외 클래스 타입 ({@code NOT nullable})
     * @param argTypes
     *            예외 생성자에 전달할 파라미터 타입 목록 제공자 (선택)
     * @param args
     *            예외 생성자에 전달할 파라미터 값 목록 (선택)
     * @param format
     *            예외 메시지 포맷 문자열 (선택)
     * @param msgArgs
     *            포맷 문자열에 매핑될 인자들
     * @return 조건에 맞춰 동적으로 생성된 예외 객체
     * 
     * @since 2021. 7. 5.
     * 
     * @version 1.8.00
     */
    public static <E extends Throwable> E newException(Class<E> type, @Nullable Supplier<@Nullable Class<?> @Nullable []> argTypes, @Nullable Object @Nullable [] args,
            @Nullable String format, @Nullable Object @Nullable... msgArgs) {

        AssertUtils2.notNulls(type);

        try {
            Constructor<E> c;
            Object[] initArgs;

            if (argTypes != null && format != null) {
                // 케이스 1: 커스텀 파라미터 + 포맷 메시지
                Class<?>[] paramTypes = ArrayUtils.add(argTypes.get(), String.class);
                c = type.getConstructor(paramTypes);
                initArgs = ArrayUtils.add(args, String.format(format, msgArgs));

            } else if (argTypes != null) {
                // 케이스 2: 커스텀 파라미터만 존재
                c = type.getConstructor(argTypes.get());
                initArgs = args != null ? args : new Object[0];

            } else if (format != null) {
                // 케이스 3: 포맷 메시지만 존재
                c = type.getConstructor(String.class);
                initArgs = new Object[] { String.format(format, msgArgs) };

            } else {
                // 케이스 4: 파라미터가 없는 기본 생성자
                c = type.getConstructor();
                initArgs = new Object[0];
            }

            return c.newInstance(initArgs);

        } catch (ReflectiveOperationException | SecurityException e) {
            // [2] 예외 최적화: Throwable 대신 정확한 리플렉션 예외 포착 및 원본 예외 체이닝(e)
            String errMsg = String.format("예외객체(%s) 생성 도중 에러가 발생하였습니다. 원인=%s", type.getName(), e.getMessage());
            throw new RuntimeException(errMsg, e);
        }
    }

    /**
     * 예외객체를 생성합니다. <br>
     * 단 예외타입은 문자열 1개를 파라미터로 받는 생성자가 필요하다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 15.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <E>
     * @param type
     *            예외 타입
     * @param parent
     *            상위 예외발생 정보
     * @param format
     *            로그 포맷
     * @param args
     *            로그 데이터
     * @return
     *
     * @since 2020. 10. 20.
     */
    public static <E extends Throwable> E newException(Class<E> type, Throwable parent, String format, Object @Nullable... args) {
        AssertUtils2.notNulls(type, parent, format);

        try {
            Constructor<E> c = type.getConstructor(String.class, Throwable.class);
            return c.newInstance(String.format(format, args), parent);
        } catch (Throwable e) {
            throw new RuntimeException(String.format("예외생성 도중 에러가 발생하였습니다. 원인=%s, parent=%s", e.getMessage(), e));
        }
    }

    /**
     * 예외상황 객체의 메시지가 주어진 문자열로 시작하는지 여부를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 28.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param e
     *            예외상황 객체
     * @param expected
     *            확인할 '시작 문자열'
     * @param ignoreCase
     *            대소문자 무시 여부
     * @return
     *
     * @since 2020. 10. 28.
     */
    private static boolean startsWith(String errorMsg, String expected, boolean ignoreCase) {
        AssertUtils2.notNulls(errorMsg, expected);

        if (errorMsg.length() < expected.length()) {
            return false;
        }

        if (ignoreCase) {
            return errorMsg.substring(0, errorMsg.length()).equalsIgnoreCase(expected);
        } else {
            return errorMsg.startsWith(expected);
        }
    }

    /**
     * 예외상황 객체의 메시지가 주어진 문자열로 시작하는지 여부를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 10. 28.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param e
     *            예외상황 객체
     * @param expected
     *            확인할 '시작 문자열'
     * @return
     *
     * @since 2020. 10. 28.
     */
    public static boolean startsWith(Throwable e, String expected) {
        return startsWith(getMessage(e), expected, false);
    }

    /**
     * 예외상황 객체의 메시지가 주어진 문자열로 시작하는지 여부를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 10. 28.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param e
     *            예외상황 객체
     * @param expected
     *            확인할 '시작 문자열'
     * @param toffset
     *            비교 시작 위치
     * @return
     *
     * @since 2020. 10. 28.
     */
    // 아래 내용에 적용됨.
    // - getMessage(e).substring(toffset)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static boolean startsWith(Throwable e, String expected, int toffset) {
        return startsWith(getMessage(e).substring(toffset), expected, false);
    }

    /**
     * 예외상황 객체의 메시지가 주어진 문자열로 시작하는지 여부를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 10. 28.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param e
     *            예외상황 객체
     * @param expected
     *            확인할 '시작 문자열'
     * @return
     *
     * @since 2020. 10. 28.
     */
    public static boolean startsWithIgnoreCase(Throwable e, String expected) {
        return startsWith(getMessage(e), expected, true);
    }

    /**
     * 예외상황 객체의 메시지가 주어진 문자열로 시작하는지 여부를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 10. 28.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param e
     *            예외상황 객체
     * @param expected
     *            확인할 '시작 문자열'
     * 
     * @return
     *
     * @since 2020. 10. 28.
     */
    // 아래 내용에 적용됨.
    // - getMessage(e).substring(toffset)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static boolean startsWithIgnoreCase(Throwable e, String expected, int toffset) {
        return startsWith(getMessage(e).substring(toffset), expected, true);
    }

    /**
     * @param stacks
     * 
     * @return
     */
    // 아래 내용에 적용됨.
    // - buf.toString();
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static String toString(StackTraceElement... stacks) {
        Objects.requireNonNull(stacks);

        StringBuffer buf = new StringBuffer();

        Iterator<StackTraceElement> itr = Arrays.asList(stacks).iterator();

        if (itr.hasNext()) {
            buf.append(itr.next().toString());

            while (itr.hasNext()) {
                buf.append('\n');
                buf.append(itr.next().toString());
            }
        }
        buf.append('\n');

        return buf.toString();
    }

    /**
     *
     * @param e
     * 
     * @return
     */
    // 아래 내용에 적용됨.
    // - writer.toString();
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static String toString(Throwable e) {
        Objects.requireNonNull(e);

        StringWriter writer = new StringWriter();
        e.printStackTrace(new PrintWriter(writer));

        return writer.toString();
    }
}
