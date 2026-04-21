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
 * Date  : 2019. 6. 28. 오전 11:23:15
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.core.utils;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jspecify.annotations.Nullable;

/**
 * 
 * @since 2019. 6. 28.
 */
public class LangUtils {

    private static final Predicate<Object> NULL = o -> o == null;
    private static final Predicate<Object> NOT_NULL = o -> o != null;
    private static final Predicate<Object> NOT_NULL_EMPTY = o -> o != null && !((String) o).isBlank();

    @SuppressWarnings("null")
    private static final Pattern NUMBER_VALUE = Pattern.compile("(\\d+)(b|kb|mb|gb|tb|pb)?", Pattern.CASE_INSENSITIVE);

    private LangUtils() {
    }

    /**
     * {@link Predicate} 가 {@code true} 인 경우, 데이타를 {@link Consumer}에게 전달합니다.
     * <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 10. 26.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <T>
     *            데이타 타입
     * @param value
     *            데이타
     * @param p
     *            판단 함수
     * @param c
     *            실행 함수
     *
     * @since 2018. 10. 26.
     */
    public static final <T extends @Nullable Object> void executeIf(T value, Predicate<Object> p, Consumer<T> c) {
        AssertUtils2.notNulls(p, c);

        if (p.test((Object) value)) {
            c.accept(value);
        }
    }

    /**
     * {@link Predicate} 가 {@code true} 인 경우 데이타를, {@code false}인 경우 기본값을
     * {@link Consumer}에게 전달합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 10. 26.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <T>
     *            데이타 타입
     * @param value
     *            데이타
     * @param defaultValue
     *            기본값
     * @param p
     *            판단 함수
     * @param c
     *            실행 함수
     *
     * @since 2018. 10. 26.
     */
    public static final <T extends @Nullable Object> void executeIf(T value, T defaultValue, Predicate<Object> p,
            Consumer<T> c) {
        AssertUtils2.notNulls(p, c);

        if (p.test((Object) value)) {
            c.accept(value);
        } else {
            c.accept(defaultValue);
        }
    }

    /**
     * 데이타가 {@code null}이 아닌 경우, {@link Consumer}에게 전달합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 10. 26.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <T>
     *            데이타 타입
     * @param value
     *            데이타
     * @param c
     *            실행함수
     *
     * @since 2018. 10. 26.
     */
    public static final <T extends @Nullable Object> void executeIfNotNull(T value, Consumer<T> c) {
        executeIf(value, NOT_NULL, c);
    }

    /**
     * 데이타가 {@code null}이 아닌 경우, {@link Consumer}에게 전달합니다. <br>
     * {@code null}인 경우 기본값을 전달합니다.
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 10. 26.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <T>
     *            데이타 타입
     * @param value
     *            데이타
     * @param defaultValue
     *            기본값
     * @param c
     *            실행함수
     *
     * @since 2018. 10. 26.
     */
    public static final <T extends @Nullable Object> void executeIfNotNull(T value, T defaultValue, Consumer<T> c) {
        executeIf(value, defaultValue, NOT_NULL, c);
    }

    /**
     * 문자열이 {@code null}이 아닌 경우, {@link Consumer}에게 전달합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 10. 26.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param value
     *            문자열
     * @param c
     *            실행함수
     *
     * @since 2018. 10. 26.
     */
    public static final void executeIfNotNullEmpty(@Nullable String value, Consumer<@Nullable String> c) {
        executeIf(value, NOT_NULL_EMPTY, c);
    }

    /**
     * 문자열이 {@code null}이 아닌 경우, {@link Consumer}에게 전달합니다.<br>
     * {@code null} 인 경우 기본값을 전달합니다.
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 10. 26.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param value
     *            문자열
     * @param defaultValue
     *            기본값
     * @param c
     *            실행함수
     *
     * @since 2018. 10. 26.
     */
    public static final void executeIfNotNullEmpty(@Nullable String value, @Nullable String defaultValue,
            Consumer<@Nullable String> c) {
        executeIf(value, defaultValue, NOT_NULL_EMPTY, c);
    }

    /**
     * 데이타가 {@code null} 인 경우, 기본값을 전달합니다. <br>
     * {@code null}이 아닌 경우 기본값을 전달합니다.
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 10. 26.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <T>
     *            데이타 타입
     * @param value
     *            데이타
     * @param defaultValue
     *            기본값
     * @param c
     *            실행함수
     *
     * @since 2018. 10. 26.
     */
    public static final <T extends @Nullable Object> void executeIfNull(T value, T defaultValue, Consumer<T> c) {
        executeIf(value, defaultValue, NULL, c);
    }

    /**
     * 데이타가 {@code null} 인 경우, {@link Consumer}에게 전달합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 10. 26.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <T>
     *            데이타 타입
     * @param value
     *            데이타
     * @param c
     *            실행함수
     *
     * @since 2018. 10. 26.
     */
    public static final <T extends @Nullable Object> void executeIfNullEmpty(T value, Consumer<T> c) {
        executeIf(value, NULL, c);
    }

    /**
     * 캐릭터형 타입 여부를 반환합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 10. 26.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param clazz
     *            클래스 타입
     *
     * @return 캐릭터형 타입 여부
     *
     * @since 2018. 10. 26.
     */
    public static boolean isCharBased(Class<?> clazz) {
        Objects.requireNonNull(clazz);

        return Character.class.isAssignableFrom(clazz) || "char".equals(clazz.getName());
    }

    /**
     * 숫자형 타입 여부를 반환합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 10. 26.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param clazz
     *            클래스 타입
     *
     * @return 숫자형 타입 여부
     *
     * @since 2018. 10. 26.
     */
    public static boolean isNumber(Class<?> clazz) {
        Objects.requireNonNull(clazz);

        if (Number.class.isAssignableFrom(clazz)) {
            return true;
        } else {
            switch (clazz.getName()) {
                case "byte":
                case "short":
                case "int":
                case "long":
                case "float":
                case "double":
                    return true;
            }
        }

        return false;
    }

    /**
     * 주어진 문자열을 숫자로 변환하여 제공합니다. <br>
     * 단위:
     * <ul>
     * <li>PB / pb
     * <li>TB / tb
     * <li>GB / gb
     * <li>MB / mb
     * <li>KB / kb
     * <li>B / b
     * </ul>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 9. 26.     parkjunhong77@gmail.com         최초 작성
     * 2019. 8. 27.     parkjunhong77@gmail.com         숫자값 초기화 누락 버그 수정.
     * </pre>
     *
     * @param value
     *            문자열
     *
     * @return 계산된 바이트 단위의 숫자
     * @throws IllegalArgumentException
     *             단위 문자열 형식이 잘못된 경우 발생.
     *
     * @since 2018. 9. 26.
     */
    public static long toNumber(String value) {
        Objects.requireNonNull(value);

        Matcher m = NUMBER_VALUE.matcher(value.strip().toLowerCase());

        if (m.matches()) {
            String number = m.group(1);
            String unit = m.group(2);

            long splitSizeByte = Long.parseLong(number);

            if (unit != null) {
                switch (unit) {
                    case "pb":
                        splitSizeByte *= 1024;
                    case "tb":
                        splitSizeByte *= 1024;
                    case "gb":
                        splitSizeByte *= 1024;
                    case "mb":
                        splitSizeByte *= 1024;
                    case "kb":
                        splitSizeByte *= 1024;
                    case "b":
                        break;
                    default:
                        throw new IllegalArgumentException("Illegal Value. value: " + value);
                }
            }

            return splitSizeByte;
        } else {
            throw new IllegalArgumentException("Illegal Value. value: " + value);
        }
    }

}
