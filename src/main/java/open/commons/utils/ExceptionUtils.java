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

package open.commons.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Supplier;

/**
 * 
 * @since 2015. 1. 13.
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public class ExceptionUtils {

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 7. 5.		박준홍			최초 작성
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <E extends Throwable> E newException(Class<E> type, Class<?>[] argTypes, Object[] args, String format, Object... msgArgs) {
        return newException(type, () -> argTypes, args, format, msgArgs);
    }

    /**
     * 예외객체를 생성한다. <br>
     * 단 예외타입은 문자열 1개를 파라미터로 받는 생성자가 필요하다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 15.		박준홍			최초 작성
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <E extends Throwable> E newException(Class<E> type, String format, Object... args) {
        return newException(type, () -> null, null, format, args);
    }

    /**
     * 예외객체를 생성한다. <br>
     * 단 예외타입은 문자열 1개를 파라미터로 받는 생성자가 필요하다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 7. 5.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     * @param type
     * @param argTypes
     * @param args
     * @param format
     * @param msgArgs
     * @return
     *
     * @since 2021. 7. 5.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <E extends Throwable> E newException(Class<E> type, Supplier<Class<?>[]> argTypes, Object[] args, String format, Object... msgArgs) {
        try {
            Constructor<E> c = null;
            Class<?>[] paramTypes = null;
            if (argTypes != null && format != null) {
                paramTypes = ArrayUtils.add(argTypes.get(), String.class);
                c = type.getConstructor(paramTypes);
                args = ArrayUtils.add(args, String.format(format, msgArgs));
            } else if (argTypes != null) {
                c = type.getConstructor(argTypes.get());
            } else if (format != null) {
                c = type.getConstructor(String.class);
                args = new Object[] { String.format(format, msgArgs) };
            } else {
                c = type.getConstructor();
            }
            c = type.getConstructor(paramTypes);
            return c.newInstance(args);
        } catch (Throwable e) {
            throw new RuntimeException(String.format("예외생성 도중 에러가 발생하였습니다. 원인=%s", e.getMessage()));
        }
    }

    /**
     * 예외객체를 생성한다. <br>
     * 단 예외타입은 문자열 1개를 파라미터로 받는 생성자가 필요하다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 15.		박준홍			최초 작성
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <E extends Throwable> E newException(Class<E> type, Throwable parent, String format, Object... args) {
        try {
            Constructor<E> c = type.getConstructor(String.class, Throwable.class);
            return c.newInstance(String.format(format, args), parent);
        } catch (Throwable e) {
            throw new RuntimeException(String.format("예외생성 도중 에러가 발생하였습니다. 원인=%s, parent=%s", e.getMessage(), e));
        }
    }

    /**
     * 예외상황 객체의 메시지가 주어진 문자열로 시작하는지 여부를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 28.		박준홍			최초 작성
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    private static boolean startsWith(String errorMsg, String expected, boolean ignoreCase) {
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
     * 예외상황 객체의 메시지가 주어진 문자열로 시작하는지 여부를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 10. 28.        박준홍         최초 작성
     * </pre>
     *
     * @param e
     *            예외상황 객체
     * @param expected
     *            확인할 '시작 문자열'
     * @return
     *
     * @since 2020. 10. 28.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static boolean startsWith(Throwable e, String expected) {
        return startsWith(e.getMessage(), expected, false);
    }

    /**
     * 예외상황 객체의 메시지가 주어진 문자열로 시작하는지 여부를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 10. 28.        박준홍         최초 작성
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static boolean startsWith(Throwable e, String expected, int toffset) {
        return startsWith(e.getMessage().substring(toffset), expected, false);
    }

    /**
     * * 예외상황 객체의 메시지가 주어진 문자열로 시작하는지 여부를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 10. 28.        박준홍         최초 작성
     * </pre>
     *
     * @param e
     *            예외상황 객체
     * @param expected
     *            확인할 '시작 문자열'
     * @return
     *
     * @since 2020. 10. 28.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static boolean startsWithIgnoreCase(Throwable e, String expected) {
        return startsWith(e.getMessage(), expected, true);
    }

    /**
     * * 예외상황 객체의 메시지가 주어진 문자열로 시작하는지 여부를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 10. 28.        박준홍         최초 작성
     * </pre>
     *
     * @param e
     *            예외상황 객체
     * @param expected
     *            확인할 '시작 문자열'
     * @return
     *
     * @since 2020. 10. 28.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static boolean startsWithIgnoreCase(Throwable e, String expected, int toffset) {
        return startsWith(e.getMessage().substring(toffset), expected, true);
    }

    public static String toString(StackTraceElement... stacks) {
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

    public static String toString(Throwable e) {
        StringWriter writer = new StringWriter();
        e.printStackTrace(new PrintWriter(writer));

        return writer.toString();
    }
}
