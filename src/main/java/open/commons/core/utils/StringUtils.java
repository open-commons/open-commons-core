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
* 
* This file is generated under this project, "open-commons-core".
*
* Date  : 2011. 06. 23. 오전 10:02:49
*
* Author: Park Jun-Hong (parkjunhong77@gmail.com)
* 
*/
package open.commons.core.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import open.commons.core.prog.StrLenRvrOrderingEntry;

/**
 * 
 * 
 * 
 * @since 2011. 06. 23.
 * 
 */
public class StringUtils {

    @SuppressWarnings("null")
    private static final Logger LOGGER = LoggerFactory.getLogger(StringUtils.class);

    static final Map<String, RegExTokenEscape> map = new HashMap<String, RegExTokenEscape>();

    static {
        init();
    }

    private static void append(StringBuilder buf, String concatenator, Optional<String> data) {
        if (data.isPresent()) {
            buf.append(concatenator);
            buf.append(data.get());
        }
    }

    /**
     * 문자열 뒤에 빈칸을 추가합니다.
     *
     * @param string
     *            문자열
     *
     * @return 빈칸이 추가된 문자열
     */
    public static String appendBlank(@Nullable String string) {
        if (string != null && !string.isEmpty()) {
            return string + " ";
        }

        return " ";
    }

    /**
     * 각각의 문자열들 뒤에 빈칸을 추가합니다.
     *
     * @param strings
     *            문자열 배열
     *
     * @return 각 문자열에 빈칸이 추가된 배열
     *
     * @throws NullPointerException
     *             파라미터({@code strings})가 {@code null}인 경우 발생.
     */
    public static String[] appendBlank(@Nullable String... strings) {
        Objects.requireNonNull(strings);

        for (int i = 0; i < strings.length; i++) {
            strings[i] = appendBlank(strings[i]);
        }
        return strings;
    }

    /**
     * 문자열에 포함되어 있는 캐릭터의 뒤로부터의 첫번째 발생 인덱스값을 반환합니다.
     *
     * @param string
     *            문자열
     * @param ch
     *            문자
     *
     * @return 발생 인덱스. 존재하지 않으면 -1을 반환합니다.
     *
     * @throws NullPointerException
     *             파라미터({@code string})가 {@code null}인 경우 발생.
     */
    public static int backIndexOf(String string, char ch) {
        Objects.requireNonNull(string);

        char[] chs = string.toCharArray();
        for (int i = chs.length - 1; i > -1; i--) {
            if (chs[i] == ch) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 주어진 문자열 안에 찾고자하는 문자의 인덱스들을 반환합니다. 인덱스들은 뒤에서부터 나타나는 첫문자의 순서이다.<br>
     * 문자가 존재하지 않는 경우 길이가 0인 배열을 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 02. 21.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * <pre>
     * 예: 
     * indiceOfFromBack("1234512345", '2') &rarr; {3, 8}
     * * [Syllable]
     * - SL: String's <b>Length</b>
     * - NI: <b>Normal-Index</b> of a character in a String
     * - BI: <b>Back-Index</b> of a character in a String
     * * [Formula]
     * - {@code <b>NI + BI + 1 = SL</b>}
     * * [Background]
     * string        : 1 | 2 | 3 | 4 | 5 | 1 | 2 | 3 | 4 | 5
     * * increasing &rarr;
     * normal   index: 0   1   2   3   4   5   6   7   8   9
     * backward index: 9   8   7   6   5   4   3   2   1   0
     * &larr; increasing
     * </pre>
     *
     * @param string
     *            문자열
     * @param c
     *            문자
     *
     * @return 인덱스 배열
     *
     * @throws NullPointerException
     *             파라미터({@code string})가 {@code null}인 경우 발생.
     *
     * @since 2012. 02. 21.
     */
    // 아래 내용에 적용됨.
    // - Arrays.copyOf(...)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static int[] backIndiceOf(String string, char c) {
        Objects.requireNonNull(string);

        if (string.indexOf(c) < 0) {
            return new int[0];
        }

        int[] result = new int[string.length()];
        int index = 0;
        char[] ca = string.toCharArray();
        for (int i = ca.length - 1; i > -1; i--) {
            if (ca[i] == c) {
                result[index++] = i;
            }
        }

        result = Arrays.copyOf(result, index);
        backwarding(result, string.length());

        return result;
    }

    /**
     * 주어진 문자열 안에 찾고자하는 문자열의 첫문자 인덱스들을 반환합니다. 인덱스들은 뒤에서부터 첫문자의 순서이다.<br>
     * 찾고자 하는 문자열이 존재하지 않는 경우 길이가 0인 배열을 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 02. 22.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * <pre>
     * 예: 
     * indiceOfFromBack("1234512345", "12") &rarr; {4, 9}
     * * [Syllable]
     * - SL: String's <b>Length</b>
     * - NI: <b>Normal-Index</b> of a character in a String
     * - BI: <b>Back-Index</b> of a character in a String
     * * [Formula]
     * - {@code <b>NI + BI + 1 = SL</b>}
     * * [Background]
     * string        : 1 | 2 | 3 | 4 | 5 | 1 | 2 | 3 | 4 | 5
     * * increasing &rarr;
     * normal   index: 0   1   2   3   4   5   6   7   8   9
     * backward index: 9   8   7   6   5   4   3   2   1   0
     * &larr; increasing
     * </pre>
     *
     * @param sourceString
     *            대상 문자열
     * @param searchedString
     *            검색할 문자열
     *
     * @return 인덱스 배열
     *
     * @throws NullPointerException
     *             파라미터({@code sourceString}, {@code searchedString} 중에 1개라도)가 {@code null}인 경우 발생.
     *
     * @since 2012. 02. 22.
     */
    public static int[] backIndiceOf(String sourceString, String searchedString) {
        Objects.requireNonNull(sourceString);
        Objects.requireNonNull(searchedString);

        if (searchedString.length() == 1) {
            return backIndiceOf(sourceString, searchedString.charAt(0));
        }

        if (sourceString.indexOf(searchedString) < 0) {
            return new int[0];
        }

        int[] result = indiceOf(sourceString, searchedString);
        if (result.length == 0) {
            return new int[0];
        }

        result = ArrayUtils.reverse(result);
        backwarding(result, sourceString.length());
        return result;
    }

    /**
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 02. 22.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * <pre>
     * 예: 
     * int[] array = {1,3,6,8,12};
     * backwarding(array, 15);
     * System.out.println(Arrays.toString(array));
     * * 결과: {13, 11, 8, 6, 2}
     * * [Syllable]
     * - SL: String's <b>Length</b>
     * - NI: <b>Normal-Index</b> of a character in a String
     * - BI: <b>Back-Index</b> of a character in a String
     * * [Formula]
     * - {@code <b>NI + BI + 1 = SL</b>}
     * * [Background]
     * string        : 1 | 2 | 3 | 4 | 5 | 1 | 2 | 3 | 4 | 5
     * * increasing &rarr;
     * normal   index: 0   1   2   3   4   5   6   7   8   9
     * backward index: 9   8   7   6   5   4   3   2   1   0
     * &larr; increasing
     * </pre>
     *
     * @param array
     *            문자열의 캐릭터 배열
     * @param SL
     *            문자열의 길이
     *
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     *
     * @since 2012. 02. 22.
     */
    private static void backwarding(int[] array, final int SL) {
        Objects.requireNonNull(array);

        for (int i = 0; i < array.length; i++) {
            array[i] = SL - array[i] - 1;
        }
    }

    /**
     * 주어진 문자열의 길이가 제한길이보다 긴 경우 (...)으로 표현된 문자열을 반환합니다. <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2022. 1. 5.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param str
     *            문자열
     * @param len
     *            제한길이
     *
     * @return 압축된 문자열
     *
     * @throws NullPointerException
     *             파라미터({@code str})가 {@code null}인 경우 발생.
     *
     * @since 2022. 1. 5.
     * @version 1.8.0
     */
    // 아래 내용에 적용됨.
    // - StringBuilder().toString()
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static String compact(String str, int len) {
        Objects.requireNonNull(str);

        AssertUtils2.isFalse(len < 1);

        String trimmed = str.strip();
        if (trimmed.length() <= len) {
            return trimmed;
        }

        return new StringBuilder().append(trimmed.substring(0, len - 3)).append("...").toString();
    }

    /**
     * 주어진 문자열을 하나의 문자열로 연결하여 제공합니다. <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2019. 10. 15.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param strings
     *            문자열 리스트
     * @param delimiter
     *            문자열 연결자
     * @param startsWithDelimeter
     *            구분자로 시작하는지 여부
     * @param trim
     *            String.strip() 처리 여부
     * @param addNulpty
     *            문자열이 Null 또는 빈문자열(Empty)인 경우 추가 여부
     *
     * @return 연결된 문자열
     *
     * @throws NullPointerException
     *             파라미터({@code delimiter}, {@code strings} 중에 1개라도)가 {@code null}인 경우 발생.
     *
     * @since 2020. 1. 16
     */
    // 아래 내용에 적용됨.
    // - StringBuilder.toString()
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static String concat(List<String> strings, String delimiter, boolean startsWithDelimeter, boolean trim, boolean addNulpty) {
        Objects.requireNonNull(delimiter);
        Objects.requireNonNull(strings);

        StringBuilder buf = new StringBuilder();
        Iterator<String> itr = strings.iterator();

        if (itr.hasNext()) {
            append(buf, startsWithDelimeter ? delimiter : "", next(itr.next(), trim, addNulpty));
        }

        while (itr.hasNext()) {
            append(buf, buf.length() < 1 && !startsWithDelimeter ? "" : delimiter, next(itr.next(), trim, addNulpty));
        }

        return buf.toString();
    }

    /**
     * 주어진 문자열을 하나의 문자열로 연결하여 제공합니다. <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2019. 10. 15.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param delimiter
     *            문자열 연결자
     * @param startsWithDelimeter
     *            구분자로 시작하는지 여부
     * @param trim
     *            String.strip() 처리 여부
     * @param addNulpty
     *            문자열이 Null 또는 빈문자열(Empty)인 경우 추가 여부
     * @param strings
     *            문자열 가변 인자
     *
     * @return 연결된 문자열
     *
     * @throws NullPointerException
     *             파라미터({@code delimiter}, {@code strings} 중에 1개라도)가 {@code null}인 경우 발생.
     *
     * @since 2019. 10. 15.
     */
    // 아래 내용에 적용됨.
    // - Arrays.asList(...)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static String concat(String delimiter, boolean startsWithDelimeter, boolean trim, boolean addNulpty, String... strings) {
        Objects.requireNonNull(delimiter);
        Objects.requireNonNull(strings);

        return concat(Arrays.asList(strings), delimiter, startsWithDelimeter, trim, addNulpty);
    }

    /**
     * 문자열들({@code strings}) 사이에 구분자({@code delimiter})를 추가합니다. <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2019. 6. 21.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param delimiter
     *            구분자
     * @param startsWithDelimeter
     *            구분자를 제일 앞에 넣을지 여부
     * @param data
     *            데이터
     *
     * @return 연결된 문자열
     *
     * @throws NullPointerException
     *             파라미터({@code delimiter}, {@code data} 중에 1개라도)가 {@code null}인 경우 발생.
     *
     * @since 2019. 6. 21.
     */
    public static <T> String concatenate(String delimiter, boolean startsWithDelimeter, Collection<T> data) {
        return concatenate(delimiter, false, data, StreamUtils.identity());
    }

    /**
     * 문자열들({@code data}) 사이에 구분자({@code delimiter})를 추가하여 결합합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2019. 6. 21.      parkjunhong77@gmail.com     최초 작성
     * 2026. 4. 2.       parkjunhong77@gmail.com     (3.0.0) JDK 25 마이그레이션: 가드 클로즈 및 Stream API 적용
     * </pre>
     *
     * @param <T>
     *            데이터 타입
     * @param <R>
     *            변환된 결과 타입
     * @param delimiter
     *            구분자
     * @param startsWithDelimeter
     *            구분자를 제일 앞에 넣을지 여부
     * @param data
     *            데이터
     * @param gen
     *            데이터 변환함수
     *
     * @return 결합된 문자열. 만약 {@code data}가 비어있다면 빈 문자열("")을 반환합니다.
     *
     * @throws NullPointerException
     *             파라미터({@code delimiter}, {@code data}, {@code gen} 중에 1개라도)가 {@code null}인 경우 발생.
     *
     * @since 2019. 6. 21.
     * @version 3.0.0
     */
    // 아래 내용에 적용됨.
    // - Stream.collect(...)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static <T, R> String concatenate(String delimiter, boolean startsWithDelimeter, Collection<T> data, Function<T, R> gen) {
        Objects.requireNonNull(delimiter);
        Objects.requireNonNull(data);
        Objects.requireNonNull(gen);

        if (data.isEmpty()) {
            return "";
        }

        String joined = data.stream().map(gen).map(String::valueOf).collect(Collectors.joining(delimiter));

        return startsWithDelimeter ? delimiter + joined : joined;
    }

    /**
     * 문자열들({@code strings}) 사이에 구분자({@code delimiter})를 추가합니다. <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2019. 6. 21.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param delimiter
     *            구분자
     * @param startsWithDelimeter
     *            구분자를 제일 앞에 넣을지 여부
     * @param data
     *            데이터
     * @param gen
     *            데이터 변환함수
     *
     * @return 연결된 문자열
     *
     * @throws NullPointerException
     *             파라미터({@code delimiter}, {@code data}, {@code gen} 중에 1개라도)가 {@code null}인 경우 발생.
     *
     * @since 2019. 6. 21.
     */
    // 아래 내용에 적용됨.
    // - Map.entrySet()
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static <K, V, R> String concatenate(String delimiter, boolean startsWithDelimeter, Map<K, V> data, Function<Entry<K, V>, R> gen) {
        Objects.requireNonNull(data);

        return concatenate(delimiter, startsWithDelimeter, data.entrySet(), gen);
    }

    /**
     * 문자열들({@code strings}) 사이에 구분자({@code delimiter})를 추가합니다. <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2019. 6. 21.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param delimiter
     *            구분자
     * @param startsWithDelimeter
     *            구분자를 제일 앞에 넣을지 여부
     * @param objects
     *            데이터
     *
     * @return 연결된 문자열
     *
     * @throws NullPointerException
     *             파라미터({@code delimiter}, {@code objects} 중에 1개라도)가 {@code null}인 경우 발생.
     *
     * @since 2019. 6. 21.
     */
    // 아래 내용에 적용됨.
    // - Arrays.asList(...)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static String concatenate(String delimiter, boolean startsWithDelimeter, Object... objects) {
        Objects.requireNonNull(delimiter);
        Objects.requireNonNull(objects);

        return concatenate(delimiter, startsWithDelimeter, Arrays.asList(objects), StreamUtils.identity());
    }

    /**
     * 문자열들({@code strings}) 사이에 구분자({@code delimiter})를 추가합니다. <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2019. 6. 21.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param delimiter
     *            구분자
     * @param startsWithDelimeter
     *            구분자를 제일 앞에 넣을지 여부
     * @param strings
     *            데이터
     *
     * @return 연결된 문자열
     *
     * @throws NullPointerException
     *             파라미터({@code delimiter}, {@code strings} 중에 1개라도)가 {@code null}인 경우 발생.
     *
     * @since 2019. 6. 21.
     */
    // 아래 내용에 적용됨.
    // - Arrays.asList(...)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static String concatenate(String delimiter, boolean startsWithDelimeter, String... strings) {
        Objects.requireNonNull(delimiter);
        Objects.requireNonNull(strings);

        return concatenate(delimiter, startsWithDelimeter, Arrays.asList(strings), StreamUtils.identity());
    }

    /**
     * 문자열들({@code strings}) 사이에 구분자({@code delimiter})를 추가합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 01. 17.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param delimiter
     *            구분자
     * @param data
     *            데이터
     *
     * @return 연결된 문자열
     *
     * @throws NullPointerException
     *             파라미터({@code delimiter}, {@code data} 중에 1개라도)가 {@code null}인 경우 발생.
     *
     * @since 2012. 01. 17.
     */
    public static <T> String concatenate(String delimiter, Collection<T> data) {
        return concatenate(delimiter, false, data, StreamUtils.identity());
    }

    /**
     * 문자열들({@code strings}) 사이에 구분자({@code delimiter})를 추가합니다. <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2019. 6. 21.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param delimiter
     *            구분자
     * @param strings
     *            데이터
     * @param gen
     *            데이터 변환함수
     *
     * @return 연결된 문자열
     *
     * @throws NullPointerException
     *             파라미터({@code delimiter}, {@code strings}, {@code gen} 중에 1개라도)가 {@code null}인 경우 발생.
     *
     * @since 2019. 6. 21.
     *
     * @see #concatenate(String, boolean, Collection, Function)
     */
    public static <T, R> String concatenate(String delimiter, Collection<T> strings, Function<T, R> gen) {
        return concatenate(delimiter, false, strings, gen);
    }

    /**
     * 문자열들({@code strings}) 사이에 구분자({@code delimiter})를 추가합니다. <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2019. 6. 21.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param delimiter
     *            구분자
     * @param data
     *            데이터
     * @param gen
     *            데이터 변환함수
     *
     * @return 연결된 문자열
     *
     * @throws NullPointerException
     *             파라미터({@code delimiter}, {@code data}, {@code gen} 중에 1개라도)가 {@code null}인 경우 발생.
     *
     * @since 2019. 6. 21.
     */
    public static <K, V, R> String concatenate(String delimiter, Map<K, V> data, Function<Entry<K, V>, R> gen) {
        return concatenate(delimiter, false, data, gen);
    }

    /**
     * 문자열들({@code strings}) 사이에 구분자({@code delimiter})를 추가합니다.
     *
     * @param delimiter
     *            구분자
     * @param data
     *            데이터
     *
     * @return 연결된 문자열
     *
     * @throws NullPointerException
     *             파라미터({@code delimiter})가 {@code null}인 경우, 파라미터({@code data})가 {@code null}이거나 {@code data}에
     *             {@code null}이 포함된 경우 발생.
     */
    // 아래 내용에 적용됨.
    // - ObjectUtils.requireNonNulls((Object[]) data);
    // [PATCH] 배열 공변성/가변성에 대한 IDE 분석기의 오탐 우회
    // [TODO] 향후 IDE의 배열 데이터 흐름 분석이 고도화되거나 JSpecify가 완벽히 지원되면 '제거'
    @SuppressWarnings("null")
    public static String concatenate(String delimiter, Object... data) {
        Objects.requireNonNull(delimiter);
        ObjectUtils.requireNonNulls((Object[]) data);

        return concatenate(delimiter, false, Arrays.asList(data), StreamUtils.identity());
    }

    /**
     * 문자열들({@code strings}) 사이에 구분자({@code delimiter})를 추가합니다.
     *
     * @param delimiter
     *            구분자
     * @param strings
     *            데이터
     *
     * @return 연결된 문자열
     *
     * @throws NullPointerException
     *             파라미터({@code delimiter}, {@code strings} 중에 1개라도)가 {@code null}인 경우 발생.
     */
    // 아래 내용에 적용됨.
    // - Arrays.asList(...)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static String concatenate(String delimiter, String... strings) {
        Objects.requireNonNull(delimiter);
        Objects.requireNonNull(strings);

        return concatenate(delimiter, false, Arrays.asList(strings), StreamUtils.identity());
    }

    /**
     * 대상 문자열에 주어진 문자열이 포함되어 있는지 여부를 제공합니다. <br>
     * 단 하나의 문자열만 포함되어 있어도 {@code true}를 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2020. 11. 9.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param string
     *            대상 문자열
     * @param strs
     *            포함여부를 확인할 문자열
     *
     * @return 포함 여부
     *
     * @throws NullPointerException
     *             파라미터({@code string})가 {@code null}인 경우, 파라미터({@code strs})가 {@code null}이거나 {@code strs}에
     *             {@code null}이 포함된 경우 발생.
     *
     * @since 2020. 11. 9.
     */
    // 아래 내용에 적용됨.
    // - ObjectUtils.requireNonNulls((Object[]) strs);
    // [PATCH] 배열 공변성/가변성에 대한 IDE 분석기의 오탐 우회
    // [TODO] 향후 IDE의 배열 데이터 흐름 분석이 고도화되거나 JSpecify가 완벽히 지원되면 '제거'
    @SuppressWarnings("null")
    public static boolean contains(String string, CharSequence... strs) {
        Objects.requireNonNull(string);
        ObjectUtils.requireNonNulls((Object[]) strs);

        for (CharSequence str : strs) {
            if (string.contains(str)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 문자열이 배열에 포함된 문자열을 모두 포함하고 있는지 여부를 제공합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 1. 6.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param string
     *            문자열
     * @param strs
     *            포함여부를 확인할 문자열
     *
     * @return 모두 포함 여부
     *
     * @throws NullPointerException
     *             파라미터({@code string})가 {@code null}인 경우, 파라미터({@code strs})가 {@code null}이거나 {@code strs}에
     *             {@code null}이 포함된 경우 발생.
     *
     * @since 2012. 1. 6.
     */
    // 아래 내용에 적용됨.
    // - ObjectUtils.requireNonNulls((Object[]) strs);
    // [PATCH] 배열 공변성/가변성에 대한 IDE 분석기의 오탐 우회
    // [TODO] 향후 IDE의 배열 데이터 흐름 분석이 고도화되거나 JSpecify가 완벽히 지원되면 '제거'
    @SuppressWarnings("null")
    public static boolean containsAll(String string, CharSequence... strs) {
        Objects.requireNonNull(string);
        ObjectUtils.requireNonNulls((Object[]) strs);

        for (CharSequence str : strs) {
            if (!string.contains(str)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 문자열이 숫자를 포함하고 있는지 여부를 반환합니다.
     *
     * @param string
     *            문자열
     *
     * @return 숫자 포함 여부
     *
     * @throws NullPointerException
     *             파라미터({@code string})가 {@code null}인 경우 발생.
     *
     * @see #containsWhat(String, Predicate)
     */
    // 아래 내용에 적용됨.
    // - Character::isDigit
    // [PATCH] [IDE-Null] Eclipse JDT 분석기의 제네릭 & @NullMarked 치환 해석 오류 우회
    // [TODO] 향후 Eclipse IDE 정적 분석기가 JSpecify 제네릭 치환을 완벽히 지원하면 '제거'
    @SuppressWarnings("null")
    public static boolean containsDigit(String string) {
        return containsWhat(string, Character::isDigit);
    }

    /**
     * 첫번째 문자열이 두번째 문자열을 대소문자 구분 없이 포함하고 있는지 여부를 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 7. 7.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param container
     *            대상 문자열
     * @param string
     *            문자열
     *
     * @return 포함 여부
     *
     * @since 2012. 7. 7.
     */
    public static boolean containsIgnorecase(@Nullable String container, @Nullable String string) {
        if (container == null || string == null) {
            return false;
        }

        return container.toLowerCase().contains(string.toLowerCase());
    }

    /**
     * 소문자가 있는지 여부를 반환합니다.
     *
     * @param string
     *            문자열
     *
     * @return 소문자 포함 여부
     *
     * @throws NullPointerException
     *             파라미터({@code string})가 {@code null}인 경우 발생.
     */
    // 아래 내용에 적용됨.
    // - Character::isLowerCase
    // [PATCH] [IDE-Null] Eclipse JDT 분석기의 제네릭 & @NullMarked 치환 해석 오류 우회
    // [TODO] 향후 Eclipse IDE 정적 분석기가 JSpecify 제네릭 치환을 완벽히 지원하면 '제거'
    @SuppressWarnings("null")
    public static boolean containsLowcase(String string) {
        return containsWhat(string, Character::isLowerCase);
    }

    /**
     * 문자열이 {@code chs}에 포함된 캐릭터들 중에 1개인지 여부를 반환합니다. 당근 길이는 1 인 문자열이다.
     *
     * @param string
     *            문자열
     * @param chs
     *            비교할 캐릭터 배열
     *
     * @return 1개 일치 여부
     *
     * @throws NullPointerException
     *             파라미터({@code string}, {@code chs} 중에 1개라도)가 {@code null}인 경우 발생.
     */
    public static boolean containsOnlyParameters(String string, char... chs) {
        Objects.requireNonNull(string);
        Objects.requireNonNull(chs);

        String trimmed = string.strip();
        if (trimmed.length() != 1) {
            return false;
        }

        char c = trimmed.charAt(0);
        for (char ch : chs) {
            if (ch == c) {
                return true;
            }
        }
        return false;
    }

    /**
     * 대문자를 포함하고 있는지 여부를 반환합니다.
     *
     * @param string
     *            문자열
     *
     * @return 대문자 포함 여부
     *
     * @throws NullPointerException
     *             파라미터({@code string})가 {@code null}인 경우 발생.
     */
    // 아래 내용에 적용됨.
    // - Character::isUpperCase
    // [PATCH] [IDE-Null] Eclipse JDT 분석기의 제네릭 & @NullMarked 치환 해석 오류 우회
    // [TODO] 향후 Eclipse IDE 정적 분석기가 JSpecify 제네릭 치환을 완벽히 지원하면 '제거'
    @SuppressWarnings("null")
    public static boolean containsUppercase(String string) {
        return containsWhat(string, Character::isUpperCase);
    }

    /**
     * 문자열을 구성하는 문자가 주어진 검증기({@link Predicate})를 통과하는지를 제공합니다. <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2026. 4. 2.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param string
     *            문자열
     * @param validator
     *            검증기
     *
     * @return 검증기 통과 여부
     *
     * @throws NullPointerException
     *             파라미터({@code string}, {@code validator} 중에 1개라도)가 {@code null}인 경우 발생.
     *
     * @since 2026. 4. 2.
     * @version 3.0.0
     */
    private static boolean containsWhat(String string, Predicate<Character> validator) {
        Objects.requireNonNull(string);
        Objects.requireNonNull(validator);

        for (char c : string.toCharArray()) {
            if (validator.test(c)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 주어진 문자열에 Whitespace가 포함되어 있는지 확인합니다. <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2019. 6. 28.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param string
     *            문자열
     *
     * @return Whitespace 포함 여부
     *
     * @throws NullPointerException
     *             파라미터({@code string})가 {@code null}인 경우 발생.
     *
     * @since 2019. 6. 28.
     */
    // 아래 내용에 적용됨.
    // - Character::isWhitespace
    // [PATCH] [IDE-Null] Eclipse JDT 분석기의 제네릭 & @NullMarked 치환 해석 오류 우회
    // [TODO] 향후 Eclipse IDE 정적 분석기가 JSpecify 제네릭 치환을 완벽히 지원하면 '제거'
    @SuppressWarnings("null")
    public static boolean containsWhitespace(String string) {
        return containsWhat(string, Character::isWhitespace);
    }

    /**
     * 문자열({@code string})에 포함되어 있는 문자({@code ch}) 개수를 반환합니다.
     *
     * @param string
     *            문자열
     * @param ch
     *            찾을 문자
     *
     * @return 문자({@code ch})의 개수
     *
     * @throws NullPointerException
     *             파라미터({@code string})가 {@code null}인 경우 발생.
     *
     */
    public static int countOf(String string, char ch) {
        Objects.requireNonNull(string);

        int count = 0;
        int len = string.length();
        for (int i = 0; i < len; i++) {
            if (string.charAt(i) == ch) {
                count++;
            }
        }

        return count;
    }

    /**
     * 문자열({@code string})에 포함되어 있는 대상 문자열({@code target}) 개수를 반환합니다.
     *
     * @param string
     *            문자열
     * @param target
     *            찾을 대상 문자열
     *
     * @return 대상 문자열({@code target})의 개수
     *
     * @throws NullPointerException
     *             파라미터({@code string}, {@code target} 중에 1개라도)가 {@code null}인 경우 발생.
     */
    public static int countOf(String string, String target) {
        Objects.requireNonNull(string);
        Objects.requireNonNull(target);

        if (target.isEmpty()) {
            return 0;
        }

        int count = 0;
        int idx = 0;
        while ((idx = string.indexOf(target, idx)) != -1) {
            count++;
            idx += target.length();
        }

        return count;
    }

    /**
     * 주어진 길이만큼 문자열을 자르고, 뒤에 " ..."을 붙여 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2017. 1. 5.       parkjunhong77@gmail.com     최초 작성
     * 2026. 4. 2.       parkjunhong77@gmail.com     (3.0.0) JDK 25 마이그레이션: 가드 클로즈 적용 및 불필요한 StringBuilder 제거
     * </pre>
     *
     * @param string
     *            문자열
     * @param length
     *            반환될 문자열의 전체 길이 (" ..." 포함)
     *
     * @return 잘린 문자열 뒤에 " ..."이 붙은 문자열. {@code string}이 {@code null}이면 "null" 반환.
     *
     * @since 2017. 1. 5.
     */
    // 아래 내용에 적용됨.
    // - StringBuilder.toString()()
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static String cutOff(@Nullable String string, int length) {
        if (string == null) {
            return "null";
        }

        if (string.length() <= length - 4) {
            return string;
        }

        int cutLength = Math.max(0, length - 4);

        StringBuilder sb = new StringBuilder(cutLength + 4);

        sb.append(string, 0, cutLength);
        sb.append(" ...");

        return sb.toString();
    }

    /**
     * {@code pre}와 {@code suf}로 둘러싸인 가장 큰 구간의 문자열을 반환합니다.
     *
     * @param string
     *            문자열
     * @param pre
     *            시작 식별자
     * @param suf
     *            종료 식별자
     *
     * @return 추출된 문자열. 조건을 만족하지 못할 경우 {@code null} 반환.
     *
     * @throws NullPointerException
     *             파라미터({@code string}, {@code pre}, {@code suf} 중에 1개라도)가 {@code null}인 경우 발생.
     */
    public static @Nullable String enclosingLargestString(String string, String pre, String suf) {
        ObjectUtils.requireNonNulls(string, pre, suf);

        int preIndex = string.indexOf(pre);
        if (preIndex < 0) {
            return null;
        }

        int countOfSuf = countOf(string, suf);
        if (pre.equals(suf) && countOfSuf < 2) {
            return null;
        }

        int sufIndex = indexOf(string, suf, countOfSuf);
        if (sufIndex < 0 || sufIndex < preIndex + pre.length()) {
            return null;
        }

        return string.substring(preIndex + pre.length(), sufIndex);
    }

    /**
     * {@code pre}와 {@code suf}로 둘러싸인 가장 작은 구간의 문자열을 반환합니다.
     *
     * @param string
     *            문자열
     * @param prefix
     *            시작 식별자
     * @param suffix
     *            종료 식별자
     *
     * @return 추출된 문자열. 조건을 만족하지 못할 경우 {@code null} 반환.
     *
     * @throws NullPointerException
     *             파라미터({@code string}, {@code prefix}, {@code suffix} 중에 1개라도)가 {@code null}인 경우 발생.
     */
    public static @Nullable String enclosingSmallestString(String string, String prefix, String suffix) {
        ObjectUtils.requireNonNulls(string, prefix, suffix);

        int preIndex = string.indexOf(prefix);
        if (preIndex < 0) {
            return null;
        }

        int sufIndex = prefix.equals(suffix) ? indexOf(string, "\"".equals(prefix) ? "\"" : suffix, 2) : string.indexOf(suffix, preIndex + prefix.length());

        if (sufIndex < 0) {
            return null;
        }

        return preIndex + prefix.length() < sufIndex ? string.substring(preIndex + prefix.length(), sufIndex) : null;
    }

    /**
     * 문자열이 지정된 접미사({@code suffix})로 끝나는지 여부를 반환합니다. (대소문자 관계없이)
     *
     * @param string
     *            문자열
     * @param suffix
     *            접미사
     *
     * @return 대소문자 관계없이 접미사로 끝나면 {@code true}, 그렇지 않으면 {@code false}
     *
     * @see String#endsWith(String)
     */
    public static boolean endsWithIgnoreCase(@Nullable String string, @Nullable String suffix) {
        if (string == null || suffix == null) {
            return false;
        }

        return string.regionMatches(true, string.length() - suffix.length(), suffix, 0, suffix.length());
    }

    /**
     * 문자열({@code string})이 접미사({@code suffixes})들 중에 하나로 끝나는지 여부를 반환합니다. (대소문자 관계없이)
     * 
     * @param string
     *            문자열
     * @param suffixes
     *            접미사 목록
     * 
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code suffixes})가 {@code null}인 경우 발생.
     */
    public static boolean endsWithIgnoreCaseOneOf(@Nullable String string, @Nullable String... suffixes) {
        Objects.requireNonNull(suffixes);

        for (String suffix : suffixes) {
            if (endsWithIgnoreCase(string, suffix)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 문자열({@code string})이 접미어({@code suffixes})들 중에 하나로 끝나는지 여부를 반환합니다.
     *
     * @param string
     *            문자열
     * @param suffixes
     *            접미어 가변 인자
     *
     * @return 접미어 중 하나로 끝나는지 여부
     *
     * @throws NullPointerException
     *             파라미터({@code string}, {@code suffixes} 중에 1개라도)가 {@code null}인 경우 발생.
     */
    public static boolean endsWithOneOf(String string, String... suffixes) {
        Objects.requireNonNull(string);
        Objects.requireNonNull(suffixes);

        for (String suffix : suffixes) {
            if (string.endsWith(suffix)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 주어진 문자열이 포함되었는지 여부를 제공합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2020. 9. 25.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param target
     *            대상 문자열
     * @param strings
     *            문자열 컬렉션
     *
     * @return 포함 여부
     *
     * @throws NullPointerException
     *             파라미터({@code target}, {@code strings} 중에 1개라도)가 {@code null}인 경우 발생.
     *
     * @since 2020. 9. 25.
     */
    public static boolean exists(String target, Collection<@Nullable String> strings) {
        Objects.requireNonNull(target);
        Objects.requireNonNull(strings);

        for (String str : strings) {
            if (target.equals(str)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 주어진 문자열이 포함되었는지 여부를 제공합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2020. 9. 25.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param target
     *            대상 문자열
     * @param strings
     *            문자열 가변 인자
     *
     * @return 포함 여부
     *
     * @throws NullPointerException
     *             파라미터({@code target}, {@code strings} 중에 1개라도)가 {@code null}인 경우 발생.
     *
     * @since 2020. 9. 25.
     */
    // 아래 내용에 적용됨.
    // - Arrays.asList(...)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static boolean exists(String target, @Nullable String... strings) {
        Objects.requireNonNull(target);
        Objects.requireNonNull(strings);

        return exists(target, Arrays.asList(strings));
    }

    /**
     * 주어진 문자열이 포함되었는지 여부를 제공합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2020. 9. 25.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param target
     *            대상 문자열
     * @param strings
     *            문자열 컬렉션
     *
     * @return 포함 여부
     *
     * @throws NullPointerException
     *             파라미터({@code target}, {@code strings} 중에 1개라도)가 {@code null}인 경우 발생.
     *
     * @since 2020. 9. 25.
     */
    public static boolean existsIgnoreCase(String target, Collection<@Nullable String> strings) {
        Objects.requireNonNull(target);
        Objects.requireNonNull(strings);

        for (String str : strings) {
            if (target.equalsIgnoreCase(str)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 주어진 문자열이 포함되었는지 여부를 제공합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2020. 9. 25.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param target
     *            대상 문자열
     * @param strings
     *            문자열 가변 인자
     *
     * @return 포함 여부
     *
     * @throws NullPointerException
     *             파라미터({@code target}, {@code strings} 중에 1개라도)가 {@code null}인 경우 발생.
     *
     * @since 2020. 9. 25.
     */
    // 아래 내용에 적용됨.
    // - Arrays.asList(...)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static boolean existsIgnoreCase(String target, @Nullable String... strings) {
        Objects.requireNonNull(target);
        Objects.requireNonNull(strings);

        return existsIgnoreCase(target, Arrays.asList(strings));
    }

    /**
     * 버퍼({@code buf})에 있는 데이터를 백업({@code list})하고 비웁니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2023. 9. 27.      parkjunhong77@gmail.com     최초 작성
     * 2023. 12. 15.     parkjunhong77@gmail.com     접근자 변경 (private &rarr; public)
     * </pre>
     *
     * @param buf
     *            문자열 버퍼
     * @param list
     *            백업할 리스트
     *
     * @throws NullPointerException
     *             파라미터({@code buf}, {@code list} 중에 1개라도)가 {@code null}인 경우 발생.
     *
     * @since 2023. 9. 27.
     * @version 2.0.0
     */
    public static void flushBuffer(StringBuffer buf, ArrayList<String> list) {
        Objects.requireNonNull(buf);
        Objects.requireNonNull(list);

        if (buf.isEmpty()) {
            return;
        }

        list.add(buf.toString());
        buf.setLength(0);
    }

    /**
     * 다수의 문자열 값을 하나의 byte 배열로 반환합니다.
     *
     * @param strings
     *            문자열 가변 인자
     *
     * @return 병합된 바이트 배열
     *
     * @throws NullPointerException
     *             파라미터({@code strings})가 {@code null}인 경우 발생.
     */
    // 아래 내용에 적용됨.
    // - Arrays.merge(...)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static byte[] getBytes(@Nullable String... strings) {
        Objects.requireNonNull(strings);

        byte[][] bytes = new byte[strings.length][];

        for (int i = 0; i < strings.length; i++) {
            String str = strings[i];
            if (str != null) {
                bytes[i] = str.getBytes();
            }
        }

        return ArrayUtils.merge(bytes);
    }

    /**
     * 문자열을 구분자로 나눈 후 제일 마지막 값을 반환합니다.
     *
     * @param string
     *            문자열
     * @param delimiter
     *            구분자
     *
     * @return 마지막 문자열. 구분자가 존재하지 않는 경우 {@code null}을 반환합니다.
     *
     * @throws NullPointerException
     *             파라미터({@code string}, {@code delimiter} 중에 1개라도)가 {@code null}인 경우 발생.
     */
    public static @Nullable String getLast(String string, String delimiter) {
        Objects.requireNonNull(string);
        Objects.requireNonNull(delimiter);

        int ps = string.lastIndexOf(delimiter);
        if (ps > -1) {
            // [PATCH] 버그 수정: 구분자의 길이가 2 이상일 경우를 대비해 delimiter.length() 반영
            return string.substring(ps + delimiter.length());
        }

        return null;
    }

    /**
     * 문자열에서 찾고 싶은 문자가 지정한 횟수만큼 나오는 경우 원래 문자열에서 몇번째에 시작되는지를 반환합니다.<br>
     * 지정한 횟수만큼 발생하지 않거나 예외가 발생하면 -1을 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 02. 21.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * <pre>
     * e.g.
     * indexOf("안녕하세요. 나는 하하입니다.", '하', 2) &rarr; 11
     * indexOf("안녕하세요. 나는 하하입니다.", '하', 4) &rarr; -1
     * </pre>
     *
     * @param sourceString
     *            대상 문자열
     * @param c
     *            검색할 문자
     * @param ordinal
     *            검색 문자가 나오는 횟수 (1부터 시작)
     *
     * @return 인덱스 위치. 조건을 만족하지 않으면 -1을 반환합니다.
     *
     * @throws NullPointerException
     *             파라미터({@code sourceString})가 {@code null}인 경우 발생.
     *
     * @since 2012. 02. 21.
     */
    public static int indexOf(String sourceString, char c, int ordinal) {
        Objects.requireNonNull(sourceString);

        if (ordinal < 1) {
            return -1;
        }

        int[] indice = indiceOf(sourceString, c);
        if (indice.length < ordinal) {
            return -1;
        }

        return indice[ordinal - 1];
    }

    /**
     * 문자열에서 찾고 싶은 문자열이 지정한 횟수만큼 나오는 경우 원래 문자열에서 몇번째에 시작되는지를 반환합니다.<br>
     * 지정한 횟수만큼 발생하지 않거나 예외가 발생하면 -1을 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 1. 6.       parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * <pre>
     * e.g.
     * indexOf("안녕하세요. 나는 하하입니다.", "하", 2) &rarr; 11
     * indexOf("안녕하세요. 나는 하하입니다.", "하", 4) &rarr; -1
     * </pre>
     *
     * @param sourceString
     *            대상 문자열
     * @param searchedString
     *            검색 문자열
     * @param ordinal
     *            검색 문자열이 나오는 횟수 (1부터 시작)
     *
     * @return 인덱스 위치. 조건을 만족하지 않으면 -1을 반환합니다.
     *
     * @throws NullPointerException
     *             파라미터({@code sourceString}, {@code searchedString} 중에 1개라도)가 {@code null}인 경우 발생.
     *
     * @since 2012. 1. 6.
     */
    public static int indexOf(String sourceString, String searchedString, int ordinal) {
        Objects.requireNonNull(sourceString);
        Objects.requireNonNull(searchedString);

        if (ordinal < 1) {
            return -1;
        }

        int[] indice = indiceOf(sourceString, searchedString);
        if (indice.length < ordinal) {
            return -1;
        }

        // [PATCH] 버그 수정: 0-based 배열이므로 ordinal - 1 위치를 참조해야 안전함.
        return indice[ordinal - 1];
    }

    /**
     * {@code prefix}와 {@code suffix}로 둘러싸인 문자열을 확보하기 위해서 앞뒤 문자의 인덱스를 길이 2인 배열로 반환합니다.
     *
     * @param string
     *            문자열
     * @param prefix
     *            시작 문자열
     * @param suffix
     *            종료 문자열
     *
     * @return 길이 2인 배열. 만족하지 않는 경우 {@code null} 반환
     *
     * @throws NullPointerException
     *             파라미터({@code string}, {@code prefix}, {@code suffix} 중에 1개라도)가 {@code null}인 경우 발생.
     */
    public static int @Nullable [] indexOf(String string, String prefix, String suffix) {
        Objects.requireNonNull(string);
        Objects.requireNonNull(prefix);
        Objects.requireNonNull(suffix);

        int preIndex = string.indexOf(prefix);
        if (preIndex < 0) {
            return null;
        }

        int sufIndex = string.indexOf(suffix);
        if (sufIndex < 0) {
            return null;
        }

        return preIndex < sufIndex ? new int[] { preIndex, sufIndex } : null;
    }

    /**
     * 주어진 2개의 문자열이 서로 다른 시작부분의 인덱스를 반환합니다.
     *
     * @param str1
     *            대상 문자열 1
     * @param str2
     *            대상 문자열 2
     * @param begin
     *            비교 시작 위치
     *
     * @return 서로 다른 위치의 인덱스. 다음과 같은 경우 -1 반환:
     *         <ul>
     *         <li>비교 시작 값이 음수</li>
     *         <li>주어진 문자열 길이가 비교 시작값보다 작은 경우</li>
     *         </ul>
     *
     * @throws NullPointerException
     *             파라미터({@code str1}, {@code str2} 중에 1개라도)가 {@code null}인 경우 발생.
     */
    public static int indexOfDifferent(String str1, String str2, int begin) {
        Objects.requireNonNull(str1);
        Objects.requireNonNull(str2);

        if (begin < 0 || str1.length() < begin || str2.length() < begin) {
            return -1;
        }

        char[] cs1 = str1.toCharArray();
        char[] cs2 = str2.toCharArray();
        int cl = Math.min(cs1.length, cs2.length);

        // [PATCH] 버그 수정: i = 0이 아니라 파라미터로 받은 begin부터 시작하도록 수정
        int i = begin;
        for (; i < cl; i++) {
            if (cs1[i] != cs2[i]) {
                return i;
            }
        }

        return i - 1;
    }

    /**
     * 주어진 2개의 문자열이 처음부터 서로 다른 인덱스를 반환합니다.
     *
     * @param str1
     *            대상 문자열 1
     * @param str2
     *            대상 문자열 2
     *
     * @return 서로 다른 위치의 인덱스.
     *
     * @see #indexOfDifferent(String, String, int)
     */
    public static int indexOfDifferentAtFirst(String str1, String str2) {
        // 순수 위임이므로 Null-check는 Core 메소드인 indexOfDifferent에 일임
        return indexOfDifferent(str1, str2, 0);
    }

    /**
     * 주어진 문자열 안에 찾고자하는 문자의 인덱스들을 반환합니다.<br>
     * 캐릭터가 존재하지 않는 경우 길이가 0 인 배열을 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2011. 11. 06.     parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param string
     *            문자열
     * @param c
     *            검색할 문자
     *
     * @return 인덱스 배열
     *
     * @throws NullPointerException
     *             파라미터({@code string})가 {@code null}인 경우 발생.
     *
     * @since 2011. 11. 06.
     */
    // 아래 내용에 적용됨.
    // - String.toCharArray()
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static int[] indiceOf(String string, char c) {
        Objects.requireNonNull(string);

        if (string.indexOf(c) < 0) {
            return new int[0];
        }

        return ArrayUtils.indiceOf(string.toCharArray(), c);
    }

    /**
     * 주어진 문자열 안에 찾고자 하는 문자열의 인덱스들을 반환합니다.<br>
     * 찾고자 하는 문자열이 존재하지 않는 경우 길이가 0인 배열을 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 02. 21.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param sourceString
     *            대상 문자열
     * @param searchedString
     *            검색 문자열
     *
     * @return 인덱스 배열
     *
     * @throws NullPointerException
     *             파라미터({@code sourceString}, {@code searchedString} 중에 1개라도)가 {@code null}인 경우 발생.
     *
     * @since 2012. 02. 21.
     */
    // 아래 내용에 적용됨.
    // - Arrays.copyOf(...)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static int[] indiceOf(String sourceString, String searchedString) {
        Objects.requireNonNull(sourceString);
        Objects.requireNonNull(searchedString);

        if (searchedString.length() == 1) {
            return indiceOf(sourceString, searchedString.charAt(0));
        }

        if (sourceString.indexOf(searchedString) < 0) {
            return new int[0];
        }

        int[] result = new int[sourceString.length() / searchedString.length()];
        int index = 0;
        int start = 0;
        int searchedIndex;

        // [PATCH] 불필요한 전체 순회를 방지하고 발견 시에만 건너뛰도록 while 문으로 최적화
        while ((searchedIndex = sourceString.indexOf(searchedString, start)) > -1) {
            result[index++] = searchedIndex;
            start = searchedIndex + searchedString.length();
        }

        return result.length == index ? result : Arrays.copyOf(result, index);
    }

    // 아래 내용에 적용됨.
    // - (String) entry.getValue()
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    private static void init() {
        try {
            Properties regExTokenEscape = new Properties();
            regExTokenEscape.load(StringUtils.class.getResourceAsStream("/properties/RegExTokenEscape.properties"));

            for (Entry<Object, Object> entry : regExTokenEscape.entrySet()) {
                map.put((String) entry.getKey(), new RegExTokenEscape(((String) entry.getKey()).charAt(0), (String) entry.getValue()));
            }
        } catch (Exception ignored) {
        }
    }

    /**
     * 주어진 문자열이 모두 소문자인지 여부를 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 01. 19.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param string
     *            문자열
     *
     * @return 소문자로만 구성되어 있는지 여부
     *
     * @throws NullPointerException
     *             파라미터({@code string})가 {@code null}인 경우 발생.
     *
     * @since 2012. 01. 19.
     */
    public static boolean isAllLowcase(String string) {
        Objects.requireNonNull(string);

        return !containsUppercase(string);
    }

    /**
     * 주어진 문자열이 모두 대문자인지 여부를 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 01. 19.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param string
     *            문자열
     *
     * @return 대문자로만 구성되어 있는지 여부
     *
     * @throws NullPointerException
     *             파라미터({@code string})가 {@code null}인 경우 발생.
     *
     * @since 2012. 01. 19.
     */
    public static boolean isAllUppercase(String string) {
        Objects.requireNonNull(string);

        return !containsLowcase(string);
    }

    /**
     * 주어진 문자열이 10진수 숫자인지 여부를 반환합니다.
     *
     * @param string
     *            문자열
     *
     * @return 10진수 숫자 여부
     *
     * @throws NullPointerException
     *             파라미터({@code str})가 {@code null}인 경우 발생.
     */
    public static boolean isDecimalNumber(String string) {
        Objects.requireNonNull(string);

        String trimmed = string.strip();
        if (trimmed.isEmpty()) {
            return false;
        }

        char[] cs = trimmed.toCharArray();
        int pos = 0;

        // 음수 기호 처리
        if (cs[0] == '-') {
            // 문자열이 "-" 단독일 경우 숫자가 아님 (기존 버그 수정)
            if (cs.length == 1) {
                return false;
            }
            pos = 1;
        }

        // 아스키코드 기반 10진수 숫자('0' ~ '9') 검증
        for (; pos < cs.length; pos++) {
            char c = cs[pos];
            if (c < '0' || c > '9') {
                return false;
            }
        }

        return true;
    }

    /**
     * 주어진 문자열이 {@code null}이거나 빈 문자열(공백 포함)인지 여부를 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 01. 11.     parkjunhong77@gmail.com     최초 작성
     * 2026. 4. 3.       parkjunhong77@gmail.com     (3.0.0) JDK 11+ isBlank() 로직으로 교체
     * </pre>
     *
     * @param string
     *            문자열
     *
     * @return {@code null}이거나 빈 문자열인 경우 {@code true}
     *
     * @since 2012. 01. 11.
     */
    public static boolean isNullOrEmptyString(@Nullable String string) {
        return string == null || string.isBlank();
    }

    /**
     * 주어진 문자열들이 모두 {@code null}이거나 빈 문자열(공백 포함)인지 여부를 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 01. 19.     parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param strings
     *            문자열 가변 인자
     *
     * @return 모두 조건을 만족하는 경우 {@code true}
     *
     * @throws NullPointerException
     *             파라미터({@code strings})가 {@code null}인 경우 발생.
     *
     * @since 2012. 01. 19.
     */
    public static boolean isNullOrEmptyStringAnd(@Nullable String... strings) {
        Objects.requireNonNull(strings);

        for (String string : strings) {
            if (!isNullOrEmptyString(string)) {
                return false;
            }
        }

        return true;
    }

    /**
     * 주어진 문자열들 중에 {@code null}이거나 빈 문자열(공백 포함)이 1개라도 포함되어 있는지 여부를 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 01. 19.     parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param strings
     *            문자열 가변 인자
     *
     * @return 1개라도 조건을 만족하는 경우 {@code true}
     *
     * @throws NullPointerException
     *             파라미터({@code strings})가 {@code null}인 경우 발생.
     *
     * @since 2012. 01. 19.
     */
    public static boolean isNullOrEmptyStringOr(@Nullable String... strings) {
        Objects.requireNonNull(strings);

        for (String string : strings) {
            if (isNullOrEmptyString(string)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 문자열이 동일한 단 하나의 문자로만 이루어져 있는지 확인하여 객체로 반환합니다.
     *
     * @param string
     *            검증할 문자열
     *
     * @return 단일 문자로만 구성된 경우 객체 반환, 그렇지 않은 경우 {@code null} 반환
     *
     * @throws NullPointerException
     *             파라미터({@code string})가 {@code null}인 경우 발생.
     * @throws IllegalArgumentException
     *             파라미터({@code string})가 빈 문자열인 경우 발생.
     */
    public static @Nullable OneCharacterString isOneCharacter(String string) {
        Objects.requireNonNull(string);

        if (string.isEmpty()) {
            throw new IllegalArgumentException("\"string\" must not be empty");
        }

        char[] cs = string.toCharArray();
        for (int i = 0; i < cs.length - 1; i++) {
            if (cs[i] != cs[i + 1]) {
                return null;
            }
        }

        return new OneCharacterString(string);
    }

    /**
     * 문자열이 공백 문자로만 이루어져 있는지 여부를 반환합니다. (빈 문자열은 제외)
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 6. 28.      parkjunhong77@gmail.com     최초 작성
     * 2026. 4. 3.       parkjunhong77@gmail.com     (3.0.0) 로직 오류 수정 및 isBlank() 적용
     * </pre>
     *
     * @param string
     *            문자열
     *
     * @return 공백으로만 이루어진 경우 {@code true}
     *
     * @since 2012. 6. 28.
     */
    public static boolean isWhiteSpace(@Nullable String string) {
        if (string == null || string.isEmpty()) {
            return false;
        }

        return string.isBlank();
    }

    /**
     * 문자열에서 찾고자하는 문자의 가장 마지막 위치를 제공합니다. <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2023. 11. 27.     parkjunhong77@gmail.com     최초 작성
     * 2026. 4. 3.       parkjunhong77@gmail.com     (3.0.0) 표준 API(lastIndexOf)로 위임하여 성능 최적화
     * </pre>
     *
     * @param string
     *            대상 문자열
     * @param c
     *            검색할 문자
     *
     * @return 문자의 가장 마지막 위치. 없는 경우 -1.
     *
     * @throws NullPointerException
     *             파라미터({@code string})가 {@code null}인 경우 발생.
     *
     * @since 2023. 11. 27.
     * @version 2.0.0
     */
    public static int lastIndexOf(String string, char c) {
        Objects.requireNonNull(string);

        return string.lastIndexOf(c);
    }

    /**
     * 주어진 문자열에 'Left-Padding'을 적용하여 반환합니다. <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2025. 9. 19.      parkjunhong77@gmail.com     최초 작성
     * 2026. 4. 3.       parkjunhong77@gmail.com     (3.0.0) String.repeat() 로직 적용
     * </pre>
     *
     * @param string
     *            대상 문자열
     * @param padding
     *            전체 길이
     * @param padChar
     *            채울 문자
     *
     * @return 패딩이 적용된 문자열
     *
     * @throws NullPointerException
     *             파라미터({@code string})가 {@code null}인 경우 발생.
     *
     * @since 2025. 9. 19.
     * @version 2.1.0
     */
    public static String leftPad(String string, int padding, char padChar) {
        Objects.requireNonNull(string);

        int strLength = string.length();
        if (strLength >= padding) {
            return string;
        }

        return String.valueOf(padChar).repeat(padding - strLength) + string;
    }

    /**
     * <b>{@code long}</b> 타입의 데이터를 주어진 길이만큼 Left Zero-Padding을 적용시켜 문자열로 반환시킨다.<br>
     * 단, 데이터가 길이보다 긴 경우 원본 데이터를 문자열로 반환합니다.
     *
     * @param l
     *            변환할 숫자
     * @param length
     *            전체 길이
     *
     * @return 패딩이 적용된 문자열
     */
    // 아래 내용에 적용됨.
    // - String.valueOf(...)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static String lpad(long l, int length) {
        // 순수 위임 (Core Method로 전달)
        return lpad(String.valueOf(l), length, false);
    }

    /**
     * <b>{@code long}</b> 타입의 데이터를 주어진 길이만큼 Left Zero-Padding을 적용시켜 문자열로 반환시킨다.<br>
     *
     * @param l
     *            변환할 숫자
     * @param length
     *            전체 길이
     * @param ommit
     *            길이가 초과될 때 앞부분을 생략(omit)할지 여부
     *
     * @return 패딩이 적용된 문자열
     */
    // 아래 내용에 적용됨.
    // - String.valueOf(...)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static String lpad(long l, int length, boolean ommit) {
        // 순수 위임 (Core Method로 전달)
        return lpad(String.valueOf(l), length, ommit);
    }

    /**
     * 문자열을 주어진 길이만큼 Left Zero-Padding 을 적용시켜 반환합니다.<br>
     * 단, 문자열의 길이가 길이보다 긴 경우 원본 데이터를 반환합니다.
     *
     * @param string
     *            대상 문자열
     * @param length
     *            전체 길이
     *
     * @return 패딩이 적용된 문자열
     *
     * @throws NullPointerException
     *             파라미터({@code string})가 {@code null}인 경우 발생.
     */
    public static String lpad(String string, int length) {
        return lpad(string, length, false);
    }

    /**
     * 문자열을 주어진 길이만큼 Left Zero-Padding 을 적용시켜 반환합니다.<br>
     *
     * @param string
     *            대상 문자열
     * @param length
     *            전체 길이
     * @param ommit
     *            길이가 초과될 때 앞부분을 잘라낼지 여부
     *
     * @return 패딩이 적용된 문자열
     *
     * @throws NullPointerException
     *             파라미터({@code string})가 {@code null}인 경우 발생.
     */
    // 아래 내용에 적용됨.
    // - String.substring(...)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static String lpad(String string, int length, boolean ommit) {
        Objects.requireNonNull(string);

        if (length < 0) {
            return string;
        }

        int diff = length - string.length();
        if (diff <= 0) {
            return ommit ? string.substring(-diff) : string;
        }

        return "0".repeat(diff) + string;
    }

    /**
     * 주어진 문자열에 대해서 왼쪽 trim 결과를 반환합니다.
     *
     * @param string
     *            문자열
     *
     * @return 왼쪽 공백이 제거된 문자열
     *
     * @throws NullPointerException
     *             파라미터({@code string})가 {@code null}인 경우 발생.
     */
    // 아래 내용에 적용됨.
    // - String.stripLeading()
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static String ltrim(String string) {
        Objects.requireNonNull(string);

        return string.stripLeading();
    }

    /**
     * 주어진 문자열의 앞에서부터 지우고자 하는 문자가 제거된 문자열을 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 02. 16.     parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param string
     *            문자열
     * @param c
     *            제거할 문자
     *
     * @return 대상 문자가 제거된 문자열
     *
     * @throws NullPointerException
     *             파라미터({@code string})가 {@code null}인 경우 발생.
     *
     * @since 2012. 02. 16.
     */
    // 아래 내용에 적용됨.
    // - Strinbg.substring(...)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static String ltrimSpecific(String string, char c) {
        Objects.requireNonNull(string);

        char[] cStr = string.toCharArray();
        int i = 0;
        for (; i < cStr.length; i++) {
            if (cStr[i] != c) {
                break;
            }
        }

        return string.substring(i);
    }

    /**
     * 주어진 문자열의 앞에서부터 지우고자하는 문자열이 제거된 문자열을 반환합니다.<br>
     * <br>
     * ltrimSpecific("안녕하세요안녕하세요방갑습니다", "안녕하세요") &rarr; 방갑습니다
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 02. 21.     parkjunhong77@gmail.com     최초 작성
     * 2026. 4. 3.       parkjunhong77@gmail.com     (3.0.0) O(N) while 루프 기반으로 로직 최적화
     * </pre>
     *
     * @param sourceString
     *            주어진 문자열
     * @param targetString
     *            지우고자 하는 문자열
     *
     * @return 새로운 문자열
     *
     * @throws NullPointerException
     *             파라미터({@code sourceString}, {@code targetString} 중에 1개라도)가 {@code null}인 경우 발생.
     *
     * @since 2012. 02. 21.
     */
    // 아래 내용에 적용됨.
    // - String.substring(...)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static String ltrimSpecific(String sourceString, String targetString) {
        Objects.requireNonNull(sourceString);
        Objects.requireNonNull(targetString);

        if (targetString.isEmpty()) {
            return sourceString;
        }

        while (sourceString.startsWith(targetString)) {
            sourceString = sourceString.substring(targetString.length());
        }

        return sourceString;
    }

    /**
     * 조건에 따라 문자열을 변환하여 {@link Optional}로 감싸서 반환합니다.
     *
     * @param str
     *            변환할 문자열
     * @param trim
     *            양끝 공백 제거 여부
     * @param addNulpty
     *            {@code null}이거나 빈 문자열일 때 대체 값을 추가할지 여부
     *
     * @return 변환된 문자열의 {@code Optional} 객체.
     */
    // 아래 내용에 적용됨.
    // - Optional.ofNullable(...)
    // - Optional.of(...)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    private static Optional<@Nullable String> next(@Nullable String str, boolean trim, boolean addNulpty) {
        if (str == null) {
            return Optional.ofNullable(addNulpty ? "null" : null);
        }

        if (str.isBlank()) {
            return Optional.ofNullable(addNulpty ? (trim ? "" : str) : null);
        }

        return Optional.of(trim ? str.strip() : str);
    }

    /**
     * 주어진 문자열에서 문자를 변경합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 03. 30.     parkjunhong77@gmail.com     최초 작성
     * 2026. 4. 3.       parkjunhong77@gmail.com     (3.0.0) 표준 API(replace) 위임 처리
     * </pre>
     *
     * @param string
     *            변경할 대상 문자열
     * @param o
     *            변경될 대상 문자
     * @param n
     *            새로운 문자
     *
     * @return 변경된 문자열
     *
     * @throws NullPointerException
     *             파라미터({@code string})가 {@code null}인 경우 발생.
     *
     * @since 2012. 03. 30.
     */
    // 아래 내용에 적용됨.
    // - String.replace(...)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static String replace(String string, char o, char n) {
        Objects.requireNonNull(string);

        return string.replace(o, n);
    }

    /**
     * 주어진 문자열에서 인덱스 배열에 해당하는 위치의 문자를 변경합니다.
     *
     * @param string
     *            문자열
     * @param t
     *            바꿀 문자
     * @param indice
     *            변경할 위치의 인덱스 배열
     *
     * @return 변경된 문자열
     *
     * @throws NullPointerException
     *             파라미터({@code string}, {@code indice} 중에 1개라도)가 {@code null}인 경우 발생.
     */
    public static String replace(String string, char t, int... indice) {
        Objects.requireNonNull(string);
        Objects.requireNonNull(indice);

        char[] cs = string.toCharArray();
        for (int i : indice) {
            if (i >= 0 && i < cs.length) {
                cs[i] = t;
            }
        }

        return new String(cs);
    }

    /**
     * 주어진 문자열에 포함된 문자를 새로운 문자열로 변경합니다.
     *
     * @param string
     *            문자열
     * @param o
     *            변경될 문자
     * @param newStr
     *            새로운 문자열
     *
     * @return 변경된 문자열
     *
     * @throws NullPointerException
     *             파라미터({@code string}, {@code newStr} 중에 1개라도)가 {@code null}인 경우 발생.
     */
    // 아래 내용에 적용됨.
    // - String.relace(...)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static String replace(String string, char o, String newStr) {
        Objects.requireNonNull(string);
        Objects.requireNonNull(newStr);

        return string.replace(String.valueOf(o), newStr);
    }

    /**
     * 문자열을 {@code map}의 매핑 정보에 맞추어 동시에 1-Pass로 변환한 후 반환합니다. <br>
     * 이중 치환(Double Replacement)을 방지하며, 길이가 긴 키워드부터 우선 매칭됩니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2026. 4. 3.       parkjunhong77@gmail.com     최초 작성 (이중 치환 버그 수정 및 1-Pass 로직 적용)
     * </pre>
     *
     * @param string
     *            원본 문자열
     * @param map
     *            치환할 대상(Key)과 결과(Value)를 담은 매핑 정보
     *
     * @return 치환이 완료된 문자열. {@code map}이 비어있으면 원본을 그대로 반환합니다.
     *
     * @throws NullPointerException
     *             파라미터({@code string}, {@code map} 중에 1개라도)가 {@code null}인 경우 발생.
     *
     * @version 3.0.0
     */
    // 아래 내용에 적용됨.
    // - EntrySet.getKey(), EntrySet.getValue()
    // - Matcher.replaceAll(...)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static String replace(String string, Map<String, String> map) {
        Objects.requireNonNull(string);
        Objects.requireNonNull(map);

        if (map.isEmpty() || string.isEmpty()) {
            return string;
        }

        // 1. 기존의 StrLenRvrOrderingEntry를 활용하여 가장 긴 문자열부터 매칭되도록 정렬
        // (예: "AppleTree"가 "Apple"보다 먼저 매칭되도록 보장)
        Set<StrLenRvrOrderingEntry> set = new ConcurrentSkipListSet<>();
        for (Entry<String, String> entry : map.entrySet()) {
            set.add(new StrLenRvrOrderingEntry(entry.getKey(), entry.getValue()));
        }

        // 2. 정규식 다중 매칭(Alternation) 패턴 생성: (key1|key2|key3...)
        // [PATCH] 기존 커스텀 toRegExString() 대신 더 완벽한 JVM 표준 Pattern.quote() 적용
        String regex = set.stream().map(entry -> Pattern.quote(entry.getKey())).collect(Collectors.joining("|"));

        Pattern pattern = Pattern.compile(regex);

        // 3. JDK 9+ Matcher.replaceAll(Function)을 활용한 1-Pass 동시 치환
        // 매칭된 키워드를 찾아 즉시 치환하며, 이미 치환된 영역은 다시 스캔하지 않음.
        return pattern.matcher(string).replaceAll(match -> {
            String replacement = map.get(match.group());
            // [방어적 프로그래밍] map의 value가 null인 경우 빈 문자열로 처리
            return replacement != null ? replacement : "";
        });
    }

    /**
     * 주어진 문자열에 'Right-Padding'을 적용하여 반환합니다. <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2025. 9. 19.      parkjunhong77@gmail.com     최초 작성
     * 2026. 4. 3.       parkjunhong77@gmail.com     (3.0.0) String.repeat() 로직으로 교체
     * </pre>
     *
     * @param string
     *            대상 문자열
     * @param padding
     *            전체 길이
     * @param padChar
     *            채울 문자
     *
     * @return 패딩이 적용된 문자열
     *
     * @throws NullPointerException
     *             파라미터({@code string})가 {@code null}인 경우 발생.
     *
     * @since 2025. 9. 19.
     * @version 2.1.0
     */
    public static String rightPad(String string, int padding, char padChar) {
        Objects.requireNonNull(string);

        int strLength = string.length();
        if (strLength >= padding) {
            return string;
        }

        return string + String.valueOf(padChar).repeat(padding - strLength);
    }

    /**
     * 주어진 문자열에 대해서 오른쪽 trim 결과를 반환합니다.
     *
     * @param string
     *            문자열
     *
     * @return 오른쪽 공백이 제거된 문자열
     *
     * @throws NullPointerException
     *             파라미터({@code string})가 {@code null}인 경우 발생.
     * 
     * @see String#stripTrailing();
     */
    // 아래 내용에 적용됨.
    // - String.stripTrailing()
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static String rtrim(String string) {
        Objects.requireNonNull(string);

        return string.stripTrailing();
    }

    /**
     * 주어진 문자열의 끝에서부터 지우고자하는 문자가 제거된 문자열을 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 02. 16.     parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param string
     *            문자열
     * @param c
     *            지우고자 하는 문자
     *
     * @return 문자가 제거된 문자열
     *
     * @throws NullPointerException
     *             파라미터({@code string})가 {@code null}인 경우 발생.
     *
     * @since 2012. 02. 16.
     */
    // 아래 내용에 적용됨.
    // - String.substring(...)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static String rtrimSpecific(String string, char c) {
        Objects.requireNonNull(string);

        int i = string.length() - 1;
        while (i >= 0 && string.charAt(i) == c) {
            i--;
        }

        return string.substring(0, i + 1);
    }

    /**
     * 주어진 문자열의 끝에서부터 지우고자하는 문자열이 제거된 문자열을 반환합니다.<br>
     * <br>
     * ltrimSpecific("안녕하세요안녕하세요방갑습니다", "방갑습니다") &rarr; 안녕하세요안녕하세요
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 02. 21.     parkjunhong77@gmail.com     최초 작성
     * 2026. 4. 3.       parkjunhong77@gmail.com     (3.0.0) endsWith() 기반의 while 루프로 로직 최적화
     * </pre>
     *
     * @param sourceString
     *            주어진 문자열
     * @param targetString
     *            지우고자 하는 문자열
     *
     * @return 새로운 문자열
     *
     * @throws NullPointerException
     *             파라미터({@code sourceString}, {@code targetString} 중에 1개라도)가 {@code null}인 경우 발생.
     *
     * @since 2012. 02. 21.
     */
    // 아래 내용에 적용됨.
    // - String.substring(...)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static String rtrimSpecific(String sourceString, String targetString) {
        Objects.requireNonNull(sourceString);
        Objects.requireNonNull(targetString);

        if (targetString.isEmpty()) {
            return sourceString;
        }

        while (sourceString.endsWith(targetString)) {
            sourceString = sourceString.substring(0, sourceString.length() - targetString.length());
        }

        return sourceString;
    }

    /**
     * 주어진 문자열을 구분자로 분리한 후 배열을 반환합니다.
     *
     * @param string
     *            문자열
     * @param delim
     *            문자열 구분자
     * @param trim
     *            문자열 {@link String#trim()} 여부
     *
     * @return 분리된 문자열 배열
     */
    public static String[] split(String string, String delim, boolean trim) {
        return split(string, delim, trim, 0);
    }

    /**
     * 주어진 문자열을 구분자로 분리한 후 배열을 반환합니다.
     *
     * @param string
     *            문자열
     * @param delimiter
     *            문자열 구분자
     * @param trim
     *            문자열 {@link String#trim()} 여부
     * @param limit
     *            최대 데이터 개수
     *
     * @return 분리된 문자열 배열
     *
     * @throws NullPointerException
     *             파라미터({@code string}, {@code delimiter} 중에 1개라도)가 {@code null}인 경우 발생.
     */
    // 아래 내용에 적용됨.
    // - String.split(...)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static String[] split(String string, String delimiter, boolean trim, int limit) {
        Objects.requireNonNull(string);
        Objects.requireNonNull(delimiter);

        String[] rtnStrings = string.split("[" + delimiter + "]", limit);

        if (trim) {
            for (int i = 0; i < rtnStrings.length; i++) {
                rtnStrings[i] = rtnStrings[i].trim();
            }
        }

        return rtnStrings;
    }

    /**
     * 주어진 문자열을 구분자로 분리한 후 배열을 반환합니다. <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2025. 4. 2.       parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param string
     *            문자열
     * @param delim
     *            문자열 구분자
     * @param post
     *            문자열 후처리 함수. 반환 결과에 따라 배열 원소가 {@code null}이 될 수 있습니다.
     *
     * @return 분리된 문자열 배열. 후처리 함수 결과에 따라 {@code null}이 포함될 수 있습니다.
     *
     * @since 2025. 4. 2.
     * @version 2.1.0
     */
    public static @Nullable String[] split(String string, String delim, @Nullable Function<String, @Nullable String> post) {
        return split(string, delim, post, 0);
    }

    /**
     * 주어진 문자열을 구분자로 분리한 후 배열을 반환합니다. <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2025. 4. 2.       parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param string
     *            문자열
     * @param delimiter
     *            문자열 구분자
     * @param post
     *            문자열 후처리 함수. 반환 결과에 따라 배열 원소가 {@code null}이 될 수 있습니다.
     * @param limit
     *            최대 데이터 개수
     *
     * @return 분리된 문자열 배열. 후처리 함수 결과에 따라 {@code null}이 포함될 수 있습니다.
     *
     * @throws NullPointerException
     *             파라미터({@code string}, {@code delimiter} 중에 1개라도)가 {@code null}인 경우 발생.
     *
     * @since 2025. 4. 2.
     * @version 2.1.0
     */
    // 아래 내용에 적용됨.
    // - String.split(...)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static @Nullable String[] split(String string, String delimiter, @Nullable Function<String, @Nullable String> post, int limit) {
        Objects.requireNonNull(string);
        Objects.requireNonNull(delimiter);

        String[] rtnStrings = string.split("[" + delimiter + "]", limit);

        if (post == null) {
            return rtnStrings;
        }

        String[] result = new String[rtnStrings.length];
        for (int i = 0; i < rtnStrings.length; i++) {
            result[i] = post.apply(rtnStrings[i]);
        }
        return result;
    }

    /**
     * 주어진 문자열을 일정크기로 나눈 후, 구분자로 연결한 결과를 반환합니다. <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2023. 8. 24.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param string
     *            문자열
     * @param size
     *            나눌 크기
     * @param delimiter
     *            구분자
     *
     * @return 연결된 문자열
     *
     * @throws NullPointerException
     *             파라미터({@code string}, {@code delimiter} 중에 1개라도)가 {@code null}인 경우 발생.
     *
     * @since 2023. 8. 24.
     * @version 2.0.0
     */
    public static String splitAndDelimiter(String string, int size, String delimiter) {
        Objects.requireNonNull(string);
        Objects.requireNonNull(delimiter);

        List<String> splited = new ArrayList<>();
        int len = string.length();
        int begin = 0;
        int end = Math.min(size, len);

        while (begin < len) {
            splited.add(string.substring(begin, end));
            begin = end;
            end = Math.min(end + size, len);
        }

        return concatenate(delimiter, splited);
    }

    /**
     * 문자열을 {@code splitRegEx}에 맞추어 배열로 만든 후, 구분자를 나누어진 문자들 사이에 추가한 후 반환합니다.
     *
     * @param string
     *            문자열
     * @param splitRegEx
     *            분리할 정규식
     * @param delimiter
     *            구분자
     *
     * @return 연결된 문자열
     *
     * @throws NullPointerException
     *             파라미터({@code string}, {@code splitRegEx}, {@code delimiter} 중에 1개라도)가 {@code null}인 경우 발생.
     */
    // 아래 내용에 적용됨.
    // - String.split(...)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static String splitAndDelimiter(String string, String splitRegEx, String delimiter) {
        Objects.requireNonNull(string);
        Objects.requireNonNull(splitRegEx);
        Objects.requireNonNull(delimiter);

        String[] strings = string.split(splitRegEx);
        return concatenate(delimiter, strings);
    }

    /**
     * 파라미터 문자열을 주어진 정규식 표현으로 나눈 후에, 주어진 인덱스에 해당하는 순서의 값을 반복적으로 나눈다.<br>
     *
     * @param string
     *            문자열
     * @param regExs
     *            String.split(String) 메소드의 파라미터로 쓰일 정규식들의 배열
     * @param selectedIndice
     *            정규식으로 나누어진 문자열 배열에서 선택할 문자열의 순서
     *
     * @return 찾아낸 문자열
     *
     * @throws NullPointerException
     *             파라미터({@code string}, {@code selectedIndice}, {@code regExs} 중에 1개라도)가 {@code null}이거나,
     *             파라미터({@code regExs})에 {@code null}이 포함된 경우 발생.
     * @throws IllegalArgumentException
     *             {@code regExs}의 길이와 {@code selectedIndice}의 길이가 다른 경우 발생.
     */
    // 아래 내용에 적용됨.
    // - ObjectUtils.requireNonNulls((Object[]) regExs);
    // [PATCH] 배열 공변성/가변성에 대한 IDE 분석기의 오탐 우회
    // [TODO] 향후 IDE의 배열 데이터 흐름 분석이 고도화되거나 JSpecify가 완벽히 지원되면 '제거'
    @SuppressWarnings("null")
    public static String splitAndGet(String string, String[] regExs, int[] selectedIndice) {
        Objects.requireNonNull(string);
        Objects.requireNonNull(selectedIndice);
        ObjectUtils.requireNonNulls((Object[]) regExs);

        if (regExs.length != selectedIndice.length) {
            throw new IllegalArgumentException("배열의 길이가 일치하지 않습니다. regExs: " + regExs.length + ", selectedIndice: " + selectedIndice.length);
        }

        for (int i = 0; i < regExs.length; i++) {
            string = string.split(regExs[i])[selectedIndice[i]];
        }

        return string;
    }

    /**
     * 주어진 문자열을 구분자로 분리한 후 Collection을 반환합니다.
     *
     * @param string
     *            문자열
     * @param delimiter
     *            문자열 구분자
     * @param trim
     *            문자열 {@link String#trim()} 여부
     *
     * @return 분리된 문자열 컬렉션
     *
     * @throws NullPointerException
     *             파라미터({@code string}, {@code delimiter} 중에 1개라도)가 {@code null}인 경우 발생.
     */
    // 아래 내용에 적용됨.
    // - Arrays.asList(...)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static Collection<String> splitAsCollection(String string, String delimiter, boolean trim) {
        // 반환 타입 생성 위임이 발생하므로 Null-check 수행
        Objects.requireNonNull(string);
        Objects.requireNonNull(delimiter);

        return Arrays.asList(split(string, delimiter, trim));
    }

    /**
     * 주어진 문자열을 구분자로 분리한 후 Collection을 반환합니다. <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2025. 4. 2.       parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param string
     *            문자열
     * @param delimiter
     *            문자열 구분자
     * @param post
     *            문자열 후처리 함수. 반환 결과에 따라 원소가 {@code null}이 될 수 있습니다.
     *
     * @return 분리된 문자열 컬렉션. 후처리 함수 결과에 따라 {@code null}이 포함될 수 있습니다.
     *
     * @throws NullPointerException
     *             파라미터({@code string}, {@code delimiter} 중에 1개라도)가 {@code null}인 경우 발생.
     *
     * @since 2025. 4. 2.
     * @version 2.1.0
     */
    // 아래 내용에 적용됨.
    // - Arrays.asList(...)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static Collection<@Nullable String> splitAsCollection(String string, String delimiter, @Nullable Function<String, @Nullable String> post) {
        Objects.requireNonNull(string);
        Objects.requireNonNull(delimiter);

        return Arrays.asList(split(string, delimiter, post));
    }

    /**
     * 주어진 문자열을 구분자로 분리한 후 {@link Set}을 반환합니다.
     *
     * @param string
     *            문자열
     * @param delimiter
     *            문자열 구분자
     * @param trim
     *            구분된 문자열 {@link String#trim()} 여부
     *
     * @return 분리된 문자열 Set
     *
     * @throws NullPointerException
     *             파라미터({@code string}, {@code delimiter} 중에 1개라도)가 {@code null}인 경우 발생.
     */
    public static Set<String> splitAsSet(String string, String delimiter, boolean trim) {
        Objects.requireNonNull(string);
        Objects.requireNonNull(delimiter);

        return new HashSet<>(Arrays.asList(split(string, delimiter, trim)));
    }

    /**
     * 주어진 문자열을 구분자로 분리한 후 {@link Set}을 반환합니다. <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2025. 4. 2.       parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param string
     *            문자열
     * @param delimiter
     *            문자열 구분자
     * @param post
     *            문자열 후처리 함수. 반환 결과에 따라 원소가 {@code null}이 될 수 있습니다.
     *
     * @return 분리된 문자열 Set. 후처리 함수 결과에 따라 {@code null}이 포함될 수 있습니다.
     *
     * @throws NullPointerException
     *             파라미터({@code string}, {@code delimiter} 중에 1개라도)가 {@code null}인 경우 발생.
     *
     * @since 2025. 4. 2.
     * @version 2.1.0
     */
    public static Set<@Nullable String> splitAsSet(String string, String delimiter, @Nullable Function<String, @Nullable String> post) {
        Objects.requireNonNull(string);
        Objects.requireNonNull(delimiter);

        return new HashSet<>(Arrays.asList(split(string, delimiter, post)));
    }

    /**
     * 주어진 문자열을 구분자로 분리한 후 배열을 반환합니다.
     *
     * @param string
     *            문자열
     * @param delimiter
     *            문자열 구분자
     * @param trim
     *            문자열 {@link String#trim()} 여부
     *
     * @return 분리된 문자열 배열
     */
    public static String[] splitWithoutBracket(String string, String delimiter, boolean trim) {
        return splitWithoutBracket(string, delimiter, trim, 0);
    }

    /**
     * 주어진 문자열을 구분자로 분리한 후 배열을 반환합니다.
     *
     * @param string
     *            문자열
     * @param delimiter
     *            문자열 구분자
     * @param trim
     *            문자열 {@link String#trim()} 여부
     * @param limit
     *            데이터 최대 개수
     *
     * @return 분리된 문자열 배열
     *
     * @throws NullPointerException
     *             파라미터({@code string}, {@code delimiter} 중에 1개라도)가 {@code null}인 경우 발생.
     */
    // 아래 내용에 적용됨.
    // - String.split(...)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static String[] splitWithoutBracket(String string, String delimiter, boolean trim, int limit) {
        Objects.requireNonNull(string);
        Objects.requireNonNull(delimiter);

        String[] rtnStrings = string.split("[" + delimiter + "]", limit);

        if (trim) {
            for (int i = 0; i < rtnStrings.length; i++) {
                rtnStrings[i] = rtnStrings[i].trim();
            }
        }

        return rtnStrings;
    }

    /**
     * 주어진 문자열을 구분자로 분리한 후 배열을 반환합니다. <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2025. 4. 2.       parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param string
     *            문자열
     * @param delim
     *            문자열 구분자
     * @param post
     *            문자열 후처리 함수. 반환 결과에 따라 배열 원소가 {@code null}이 될 수 있습니다.
     *
     * @return 분리된 문자열 배열. 후처리 함수 결과에 따라 {@code null}이 포함될 수 있습니다.
     *
     * @since 2025. 4. 2.
     * @version 2.1.0
     */
    public static @Nullable String[] splitWithoutBracket(String string, String delim, @Nullable Function<String, @Nullable String> post) {
        return splitWithoutBracket(string, delim, post, 0);
    }

    /**
     * 주어진 문자열을 구분자로 분리한 후 배열을 반환합니다. <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2025. 4. 2.       parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param string
     *            문자열
     * @param delim
     *            문자열 구분자
     * @param post
     *            문자열 후처리 함수. 반환 결과에 따라 배열 원소가 {@code null}이 될 수 있습니다.
     * @param limit
     *            데이터 최대 개수
     *
     * @return 분리된 문자열 배열. 후처리 함수 결과에 따라 {@code null}이 포함될 수 있습니다.
     *
     * @throws NullPointerException
     *             파라미터({@code string}, {@code delim} 중에 1개라도)가 {@code null}인 경우 발생.
     *
     * @since 2025. 4. 2.
     * @version 2.1.0
     */
    // 아래 내용에 적용됨.
    // - String.split(...)
    // - new String[]
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static @Nullable String[] splitWithoutBracket(String string, String delim, @Nullable Function<String, @Nullable String> post, int limit) {
        Objects.requireNonNull(string);
        Objects.requireNonNull(delim);

        String[] rtnStrings = string.split("[" + delim + "]", limit);

        if (post == null) {
            return rtnStrings;
        }

        String[] result = new String[rtnStrings.length];
        for (int i = 0; i < rtnStrings.length; i++) {
            result[i] = post.apply(rtnStrings[i]);
        }
        return result;
    }

    /**
     * 문자열이 숫자로 시작하는지 여부를 제공합니다. <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2021. 6. 21.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param string
     *            문자열
     *
     * @return 숫자로 시작하는지 여부
     *
     * @throws NullPointerException
     *             파라미터({@code string})가 {@code null}인 경우 발생.
     *
     * @since 2021. 6. 21.
     * @version 1.8.0
     */
    public static boolean startsWithDigit(String string) {
        Objects.requireNonNull(string);

        if (string.isEmpty()) {
            return false;
        }

        char c = string.charAt(0);
        return c >= '0' && c <= '9';
    }

    /**
     * 주어진 문자열이 한글로 시작하는지 여부를 제공합니다. <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2022. 4. 1.       parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param string
     *            문자열
     *
     * @return 한글로 시작하는지 여부
     *
     * @throws NullPointerException
     *             파라미터({@code string})가 {@code null}인 경우 발생.
     *
     * @since 2022. 4. 1.
     * @version 1.8.0
     */
    public static boolean startswithHangul(String string) {
        Objects.requireNonNull(string);

        if (string.isEmpty()) {
            return false;
        }
        return CharUtils.isKorean(string.charAt(0));
    }

    /**
     * 주어진 문자열이 한글로 시작하는지 여부를 제공합니다. <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2022. 4. 1.       parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param string
     *            문자열
     * @param trim
     *            {@link String#strip()} 적용 여부
     *
     * @return 한글로 시작하는지 여부
     *
     * @throws NullPointerException
     *             파라미터({@code string})가 {@code null}인 경우 발생.
     *
     * @since 2022. 4. 1.
     * @version 1.8.0
     */
    // 아래 내용에 적용됨.
    // - String.strip()
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static boolean startswithHangul(String string, boolean trim) {
        Objects.requireNonNull(string);

        if (trim) {
            string = string.strip();
        }

        return startswithHangul(string);
    }

    /**
     * 문자열이 주어진 <b>{@code prefix}</b>로 시작하는지 여부를 반환합니다. (대소문자 관계없이)
     *
     * @param string
     *            대상 문자열
     * @param prefix
     *            확인할 접두어
     *
     * @return 접두어로 시작하는지 여부
     *
     * @throws NullPointerException
     *             파라미터({@code string}, {@code prefix} 중에 1개라도)가 {@code null}인 경우 발생.
     */
    public static boolean startsWithIgnoreCase(String string, String prefix) {
        Objects.requireNonNull(string);
        Objects.requireNonNull(prefix);

        if (string.length() < prefix.length()) {
            return false;
        }

        // [PATCH] substring 생성 오버헤드 방지 및 고속 비교를 위한 표준 API(regionMatches) 활용
        return string.regionMatches(true, 0, prefix, 0, prefix.length());
    }

    /**
     * 문자열이 주어진 접두어들 중에 하나로 시작하는지 여부를 반환합니다. (대소문자 관계없이) <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2025. 7. 30.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param string
     *            문자열
     * @param prefixes
     *            접두어들
     *
     * @return 접두어 중 하나로 시작하는지 여부
     *
     * @throws NullPointerException
     *             파라미터({@code string}, {@code prefixes} 중에 1개라도)가 {@code null}이거나, 파라미터({@code prefixes})에
     *             {@code null}이 포함된 경우 발생.
     *
     * @since 2025. 7. 30.
     * @version 2.1.0f
     */
    public static boolean startsWithIgnoreCaseOneOf(String string, Collection<@Nullable String> prefixes) {
        Objects.requireNonNull(string);
        Objects.requireNonNull(prefixes);

        for (String prefix : prefixes) {
            if (prefix != null && startsWithIgnoreCase(string, prefix)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 문자열이 주어진 접두어들 중에 하나로 시작하는지 여부를 반환합니다. (대소문자 관계없이)
     *
     * @param string
     *            문자열
     * @param prefixes
     *            접두어 가변 인자
     *
     * @return 접두어 중 하나로 시작하는지 여부
     *
     * @throws NullPointerException
     *             파라미터({@code string}, {@code prefixes} 중에 1개라도)가 {@code null}이거나, 파라미터({@code prefixes})에
     *             {@code null}이 포함된 경우 발생.
     */
    public static boolean startsWithIgnoreCaseOneOf(String string, @Nullable String... prefixes) {
        Objects.requireNonNull(string);
        Objects.requireNonNull(prefixes);

        for (String prefix : prefixes) {
            if (prefix != null && startsWithIgnoreCase(string, prefix)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 문자열이 주어진 접두어들 중에 하나로 시작하는지 여부를 반환합니다. <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2025. 7. 30.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param string
     *            문자열
     * @param prefixes
     *            접두어들
     *
     * @return 접두어 중 하나로 시작하는지 여부
     *
     * @throws NullPointerException
     *             파라미터({@code string}, {@code prefixes} 중에 1개라도)가 {@code null}이거나, 파라미터({@code prefixes})에
     *             {@code null}이 포함된 경우 발생.
     *
     * @since 2025. 7. 30.
     * @version 2.1.0
     */
    public static boolean startsWithOneOf(String string, Collection<@Nullable String> prefixes) {
        Objects.requireNonNull(string);
        Objects.requireNonNull(prefixes);

        for (String prefix : prefixes) {
            if (prefix != null && string.startsWith(prefix)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 문자열이 주어진 접두어들 중에 하나로 시작하는지 여부를 반환합니다.
     *
     * @param string
     *            문자열
     * @param prefixes
     *            접두어 가변 인자
     *
     * @return 접두어 중 하나로 시작하는지 여부
     *
     * @throws NullPointerException
     *             파라미터({@code string}, {@code prefixes} 중에 1개라도)가 {@code null}이거나, 파라미터({@code prefixes})에
     *             {@code null}이 포함된 경우 발생.
     */
    public static boolean startsWithOneOf(String string, @Nullable String... prefixes) {
        Objects.requireNonNull(string);
        Objects.requireNonNull(prefixes);

        for (String prefix : prefixes) {
            if (prefix != null && string.startsWith(prefix)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 파라미터로 받은 문자열 중에서 sIndex와 eIndex에 사이에 해당하는 부분을 제외한 나머지 문자열을 반환합니다.
     *
     * @param string
     *            대상 문자열
     * @param sIndex
     *            잘라낼 부분의 시작 인덱스 (inclusive)
     * @param eIndex
     *            잘라낸 부분의 마지막 인덱스 (exclusive)
     *
     * @return 제외 후 병합된 문자열
     *
     * @throws NullPointerException
     *             파라미터({@code string})가 {@code null}인 경우 발생.
     */
    public static String substring(String string, int sIndex, int eIndex) {
        Objects.requireNonNull(string);

        if (sIndex > -1 && eIndex > -1 && eIndex > sIndex) {
            return string.substring(0, sIndex) + string.substring(eIndex);
        }

        return string;
    }

    /**
     * 문자열에서 {@code boundary} 문자열 다음부터 시작하는 문자열을 반환합니다.
     *
     * @param string
     *            문자열
     * @param boundary
     *            기준 문자열 (exclusive)
     *
     * @return 추출된 문자열. {@code boundary}가 존재하지 않으면 원본을 반환합니다.
     *
     * @throws NullPointerException
     *             파라미터({@code string}, {@code boundary} 중에 1개라도)가 {@code null}인 경우 발생.
     */
    // 아래 내용에 적용됨.
    // - String.substring(...)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static String substringAfter(String string, String boundary) {
        Objects.requireNonNull(string);
        Objects.requireNonNull(boundary);

        int index = string.indexOf(boundary);
        if (index < 0) {
            return string;
        }

        return string.substring(index + boundary.length());
    }

    /**
     * 문자열에서 {@code boundary} 문자열 직전까지 문자열을 반환합니다.
     *
     * @param string
     *            문자열
     * @param boundary
     *            기준 문자열 (exclusive)
     *
     * @return 추출된 문자열. {@code boundary}가 존재하지 않으면 원본을 반환합니다.
     *
     * @throws NullPointerException
     *             파라미터({@code string}, {@code boundary} 중에 1개라도)가 {@code null}인 경우 발생.
     */
    // 아래 내용에 적용됨.
    // - String.substring(...)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static String substringBefore(String string, String boundary) {
        Objects.requireNonNull(string);
        Objects.requireNonNull(boundary);

        int index = string.indexOf(boundary);
        if (index < 0) {
            return string;
        }

        return string.substring(0, index);
    }

    /**
     * camelCase 문자열을 kebab-case 문자열로 변경합니다.<br>
     * 예)
     * <ul>
     * <li>camelCase &rarr; camel-case</li>
     * <li>camelCase1 &rarr; camel-case1</li>
     * <li>camel1Case &rarr; camel1-case</li>
     * </ul>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2020. 1. 16.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param camelCase
     *            카멜케이스 문자열
     *
     * @return 케밥케이스 문자열
     *
     * @since 2020. 1. 16.
     */
    public static String toKebabCase(String camelCase) {
        // 순수 위임 (Core Method로 전달)
        return toKebabCase0(camelCase, false);
    }

    /**
     * camelCase 문자열을 kebab-case 문자열로 변경합니다.<br>
     * 예)
     * <ul>
     * 숫자 별도처리: false
     * <li>camelCase &rarr; camel-case</li>
     * <li>camelCase1 &rarr; camel-case1</li>
     * <li>camel1Case &rarr; camel1-case</li>
     * </ul>
     * <ul>
     * 숫자 별도처리: true
     * <li>camelCase &rarr; camel-case</li>
     * <li>camelCase1 &rarr; camel-case-1</li>
     * <li>camel1Case &rarr; camel-1-case</li>
     * </ul>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2023. 9. 27.      parkjunhong77@gmail.com     최초 작성
     * 2026. 4. 3.       parkjunhong77@gmail.com     (3.0.0) 불필요한 ArrayList 할당 제거 및 단일 루프로 O(1) 공간 복잡도 최적화
     * </pre>
     *
     * @param camelCase
     *            문자열
     * @param isSplitNum
     *            숫자 별도 처리 여부
     *
     * @return 케밥케이스 문자열
     *
     * @throws NullPointerException
     *             파라미터({@code camelCase})가 {@code null}인 경우 발생.
     *
     * @since 2023. 9. 27.
     * @version 3.0.0
     */
    // 아래 내용에 적용됨.
    // - StringBuilder.toString()
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    private static String toKebabCase0(String camelCase, boolean isSplitNum) {
        Objects.requireNonNull(camelCase);

        if (camelCase.isEmpty()) {
            return camelCase;
        }

        StringBuilder sb = new StringBuilder(camelCase.length() + 5);
        for (int i = 0; i < camelCase.length(); i++) {
            char c = camelCase.charAt(i);
            // 대문자이거나, 숫자별도처리가 켜져있는 상태에서 숫자인 경우 하이픈 추가
            if (i > 0 && (Character.isUpperCase(c) || (isSplitNum && Character.isDigit(c)))) {
                sb.append('-');
            }
            sb.append(Character.toLowerCase(c));
        }

        return sb.toString();
    }

    /**
     * camelCase 문자열을 kebab-case 문자열로 변경합니다. (숫자 분리 적용)<br>
     * 예)
     * <ul>
     * <li>camelCase &rarr; camel-case</li>
     * <li>camelCase1 &rarr; camel-case-1</li>
     * <li>camel1Case &rarr; camel-1-case</li>
     * </ul>
     *
     * <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2023. 9. 27.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param camelCase
     *            카멜케이스 문자열
     *
     * @return 숫자 분리가 적용된 케밥케이스 문자열
     *
     * @since 2023. 9. 27.
     * @version 2.0.0
     */
    public static String toKebabCaseNum(String camelCase) {
        // 순수 위임 (Core Method로 전달)
        return toKebabCase0(camelCase, true);
    }

    /**
     * 주어진 문자열들을 조합하여 LCC(Lower Camel Case) 형태의 단일 문자열을 반환합니다.<br>
     * 예: "TO", "lOwER", "camel", "casE" &rarr; "toLowerCamelCase" *
     * <p>
     * <b>[참고]</b> 가변 인자 내부에 포함된 {@code null} 또는 빈 문자열({@code ""})은 조합 과정에서 안전하게 무시됩니다. 첫 번째로 발견되는 '유효한' 문자열이 Lower
     * Camel Case의 시작 단어가 됩니다.
     * </p>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 01. 10.     parkjunhong77@gmail.com     최초 작성
     * 2026. 4. 7.       parkjunhong77@gmail.com     (3.0.0) 가변 인자 내 null/empty 처리 로직 개선 및 상태 기반 루프 적용
     * </pre>
     *
     * @param strings
     *            문자열 가변 인자 (배열 자체 및 내부 요소로 {@code null} 허용)
     *
     * @return 로어 카멜 케이스로 조합된 문자열. 유효한 문자열이 하나도 없는 경우 빈 문자열({@code ""}) 반환.
     *
     * @throws NullPointerException
     *             파라미터 배열({@code strings}) 자체가 {@code null}인 경우 발생.
     *
     * @since 2012. 01. 10.
     */
    // 아래 내용에 적용됨.
    // - StringBuilder.toString()
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static String toLowerCamelCase(@Nullable String... strings) {
        Objects.requireNonNull(strings);

        if (strings.length == 0) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        // [PATCH] 인덱스(0, 1...)에 의존하지 않고, '첫 번째 유효 단어'인지 추적하는 플래그 도입
        boolean isFirstValidWord = true;

        for (String str : strings) {
            // null 및 빈 문자열은 철저히 무시 (Skip)
            if (str != null && !str.isEmpty()) {
                String lower = str.toLowerCase();

                if (isFirstValidWord) {
                    // 첫 번째 유효 단어는 무조건 전체 소문자로 append
                    result.append(lower);
                    isFirstValidWord = false;
                } else {
                    // 두 번째 유효 단어부터는 첫 글자만 대문자로 변경 (Upper Camel Case 처리)
                    result.append(Character.toUpperCase(lower.charAt(0)));
                    result.append(lower.substring(1));
                }
            }
        }

        return result.toString();
    }

    /**
     * 문자열에서 {@code index}에 해당하는 캐릭터를 소문자로 변경한 후 반환합니다.
     *
     * @param string
     *            문자열
     * @param index
     *            인덱스
     *
     * @return 변경된 문자열. 인덱스가 범위를 벗어나면 원본 문자열 반환.
     *
     * @throws NullPointerException
     *             파라미터({@code string})가 {@code null}인 경우 발생.
     */
    public static String toLowerCase(String string, int index) {
        Objects.requireNonNull(string);

        if (index >= 0 && index < string.length()) {
            char[] cs = string.toCharArray();
            cs[index] = Character.toLowerCase(cs[index]);
            return new String(cs);
        }

        LOGGER.error("index={}, string: {}", index, string);
        return string;
    }

    /**
     * camelCase 문자열을 PascalCase 문자열로 변경합니다.<br>
     * 예) camelCase &rarr; CamelCase <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2020. 1. 16.      parkjunhong77@gmail.com     최초 작성
     * 2026. 4. 3.       parkjunhong77@gmail.com     (3.0.0) 루프 및 객체 생성 오버헤드를 제거한 O(1) 로직으로 최적화
     * </pre>
     *
     * @param camelCase
     *            카멜케이스 문자열
     *
     * @return 파스칼케이스 문자열
     *
     * @throws NullPointerException
     *             파라미터({@code camelCase})가 {@code null}인 경우 발생.
     *
     * @since 2020. 1. 16.
     * @version 3.0.0
     */
    public static String toPascalCase(String camelCase) {
        Objects.requireNonNull(camelCase);

        if (camelCase.isEmpty()) {
            return camelCase;
        }

        // [PATCH] 문자열의 첫 글자만 대문자로 올리면 원본의 의도를 100% 만족하며 불필요한 연산이 제거됨
        return Character.toUpperCase(camelCase.charAt(0)) + camelCase.substring(1);
    }

    /**
     * 문자열을 정규식에 사용할 수 있도록 변환(Escape)한 후 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2026. 4. 3.       parkjunhong77@gmail.com     (3.0.0) 커스텀 이스케이프 맵 대신 JDK 표준 Pattern.quote() 적용
     * </pre>
     *
     * @param string
     *            원본 문자열
     *
     * @return 정규식 이스케이프 처리가 완료된 문자열
     *
     * @throws NullPointerException
     *             파라미터({@code string})가 {@code null}인 경우 발생.
     */
    // 아래 내용에 적용됨.
    // - Pattern.quote(...)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static String toRegExString(String string) {
        Objects.requireNonNull(string);

        // [PATCH] 레거시 커스텀 치환 엔진을 버리고 가장 안전한 JVM 표준 엔진으로 위임
        return Pattern.quote(string);
    }

    /**
     * camelCase 문자열을 snake_case 문자열로 변경합니다.<br>
     * 예)
     * <ul>
     * <li>camelCase &rarr; camel_case</li>
     * <li>camel1Case &rarr; camel1_case</li>
     * <li>camelCase1 &rarr; camel_case1</li>
     * </ul>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2020. 1. 16.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param camelCase
     *            카멜케이스 문자열
     *
     * @return 스네이크케이스 문자열
     *
     * @since 2020. 1. 16.
     */
    public static String toSnakeCase(String camelCase) {
        // 순수 위임 (Core Method로 전달)
        return toSnakeCase0(camelCase, false);
    }

    /**
     * camelCase(또는 PascalCase) 문자열을 snake_case 문자열로 변경합니다.<br>
     * *
     * <p>
     * <b>[변환 예시]</b>
     * </p>
     * <ul>
     * <li>{@code "camelCase"} &rarr; {@code "camel_case"}</li>
     * <li>{@code "PascalCase"} &rarr; {@code "pascal_case"}</li>
     * <li>{@code "user123Id"}, {@code isSplitNum = false} &rarr; {@code "user123_id"}</li>
     * <li>{@code "user123Id"}, {@code isSplitNum = true} &rarr; {@code "user_1_2_3_id"}</li>
     * </ul>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2023. 9. 27.      parkjunhong77@gmail.com     최초 작성
     * 2026. 4. 3.       parkjunhong77@gmail.com     (3.0.0) 불필요한 ArrayList 할당 제거 및 최적화
     * 2026. 4. 7.       parkjunhong77@gmail.com     (3.0.0) isSplitNum 동작 방식에 대한 Javadoc 예시 보강
     * </pre>
     *
     * @param camelCase
     *            변환할 원본 문자열 (절대 {@code null} 불가)
     * @param isSplitNum
     *            숫자를 만났을 때 별도의 단어로 취급하여 언더스코어({@code _})로 분리할지 여부
     *
     * @return 소문자와 언더스코어로 구성된 스네이크 케이스 문자열
     *
     * @throws NullPointerException
     *             파라미터({@code camelCase})가 {@code null}인 경우 발생.
     *
     * @since 2023. 9. 27.
     * @version 3.0.0
     */
    // 아래 내용에 적용됨.
    // - StringBuilder.toString()
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    private static String toSnakeCase0(String camelCase, boolean isSplitNum) {
        Objects.requireNonNull(camelCase);

        if (camelCase.isEmpty()) {
            return camelCase;
        }

        StringBuilder sb = new StringBuilder(camelCase.length() + 5);
        for (int i = 0; i < camelCase.length(); i++) {
            char c = camelCase.charAt(i);

            // 첫 글자가 아니면서, 대문자이거나 (숫자 분리 옵션이 켜져있고 숫자인 경우) 언더스코어 추가
            if (i > 0 && (Character.isUpperCase(c) || (isSplitNum && Character.isDigit(c)))) {
                sb.append('_');
            }
            sb.append(Character.toLowerCase(c));
        }

        return sb.toString();
    }

    /**
     * camelCase 문자열을 snake_case 문자열로 변경합니다. (숫자 분리 적용)<br>
     * 예)
     * <ul>
     * <li>camelCase &rarr; camel_case</li>
     * <li>camel1Case &rarr; camel_1_case</li>
     * <li>camelCase1 &rarr; camel_case_1</li>
     * </ul>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2023. 9. 27.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param camelCase
     *            카멜케이스 문자열
     *
     * @return 숫자 분리가 적용된 스네이크케이스 문자열
     *
     * @since 2023. 9. 27.
     * @version 2.0.0
     */
    public static String toSnakeCaseNum(String camelCase) {
        // 순수 위임 (Core Method로 전달)
        return toSnakeCase0(camelCase, true);
    }

    /**
     * 주어진 문자열이 {@code null}이거나 빈문자열인 경우 {@code defaultValue}에 해당하는 값을 반환하고, 그렇지 않은 경우 주어진 문자열을 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 01. 17.     parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param string
     *            확인할 문자열
     * @param defaultValue
     *            대체할 기본값
     *
     * @return 결과 문자열
     *
     * @since 2012. 01. 17.
     */
    public static @Nullable String toString(@Nullable String string, @Nullable String defaultValue) {
        // 파라미터 null 허용 정책이 명확하므로 별도의 Objects.requireNonNull 검사는 생략
        return isNullOrEmptyString(string) ? defaultValue : string;
    }

    /**
     * 주어진 가변 인자 객체들을 하나의 문자열로 결합하여 반환합니다.
     *
     * @param objects
     *            결합할 객체 가변 인자
     *
     * @return 결합된 문자열
     *
     * @throws NullPointerException
     *             파라미터({@code objects})가 {@code null}인 경우 발생.
     */
    // 아래 내용에 적용됨.
    // - Object.toString()
    // - String.strip()
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static String toStrings(@Nullable Object... objects) {
        Objects.requireNonNull(objects);

        if (objects.length == 0) {
            return "";
        }
        if (objects.length == 1) {
            Object o = objects[0];
            return o == null ? "null" : o.toString();
        }

        StringBuilder sb = new StringBuilder();
        for (Object obj : objects) {
            sb.append(obj);
        }
        return sb.toString().strip();
    }

    /**
     * 주어진 문자열들을 조합하여 UCC(Upper Camel Case, Pascal Case) 형태의 단일 문자열을 반환합니다.<br>
     * 예: "upper", "camel", "case" &rarr; "UpperCamelCase" *
     * <p>
     * <b>[참고]</b> 가변 인자 내부에 포함된 {@code null} 또는 빈 문자열({@code ""})은 조합 과정에서 안전하게 무시됩니다.
     * </p>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 01. 10.     parkjunhong77@gmail.com     최초 작성
     * 2026. 4. 7.       parkjunhong77@gmail.com     (3.0.0) 향상된 for 루프(Enhanced for-loop) 적용 및 Javadoc 보강
     * </pre>
     *
     * @param strings
     *            문자열 가변 인자 (배열 자체 및 내부 요소로 {@code null} 허용)
     *
     * @return 어퍼 카멜 케이스(파스칼 케이스)로 조합된 문자열. 유효한 문자열이 하나도 없는 경우 빈 문자열({@code ""}) 반환.
     *
     * @throws NullPointerException
     *             파라미터 배열({@code strings}) 자체가 {@code null}인 경우 발생.
     *
     * @since 2012. 01. 10.
     * @version 3.0.0
     */
    // 아래 내용에 적용됨.
    // - StringBuilder.toString()
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static String toUpperCamelCase(@Nullable String... strings) {
        Objects.requireNonNull(strings);

        if (strings.length == 0) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        for (String str : strings) {
            if (str != null && !str.isEmpty()) {
                String lower = str.toLowerCase();
                result.append(Character.toUpperCase(lower.charAt(0)));
                result.append(lower.substring(1));
            }
        }

        return result.toString();
    }

    /**
     * 문자열에서 {@code index}에 해당하는 캐릭터를 대문자로 변경한 후 반환합니다.
     *
     * @param string
     *            문자열
     * @param index
     *            인덱스
     *
     * @return 변경된 문자열. 인덱스가 범위를 벗어나면 원본 문자열 반환.
     *
     * @throws NullPointerException
     *             파라미터({@code string})가 {@code null}인 경우 발생.
     */
    public static String toUpperCase(String string, int index) {
        Objects.requireNonNull(string);

        if (index >= 0 && index < string.length()) {
            char[] cs = string.toCharArray();
            cs[index] = Character.toUpperCase(cs[index]);
            return new String(cs);
        }

        LOGGER.error("index={}, string: {}", index, string);
        return string;
    }

    /**
     * 하나의 문자로 이루어진 문자열
     * 
     * 
     * 
     * @since 2012. 3. 5.
     */
    public static final class OneCharacterString {
        private final String string;
        private final String c2str;
        private final char c;

        // 아래 내용에 적용됨.
        // - String.valueOf(...)
        // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
        // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
        @SuppressWarnings("null")
        OneCharacterString(String string) {
            this.string = string;
            this.c = string.charAt(0);
            this.c2str = String.valueOf(c);
        }

        /**
         * 문자열을 이루는 캐릭터를 문자열로 반환합니다.
         * 
         * @return
         * 
         * @since 2012. 3. 5.
         * 
         */
        public String c2str() {
            return c2str;
        }

        /**
         * @return the c
         * 
         * 
         * @since 2012. 3. 5.
         * 
         */
        public final char getC() {
            return c;
        }

        /**
         * @return the string
         * 
         * 
         * 
         * @since 2012. 3. 5.
         */
        public final String getString() {
            return string;
        }

        /**
         * @return
         * 
         * @since 2012. 3. 5.
         * 
         * 
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return "[" + c + "] " + string;
        }
    }

    /**
     * 정규식 토큰 이스케이프 정보를 담는 불변(Immutable) 데이터 클래스입니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2026. 4. 7.       parkjunhong77@gmail.com     (3.0.0) Zero-Allocation 파싱 적용 및 불변성 강제
     * </pre>
     *
     * @since 2026. 4. 7.
     * @version 3.0.0
     */
    static final class RegExTokenEscape {

        /** 이스케이프 대상 토큰 문자 */
        final char token;
        /** 이스케이프 문자열 */
        final String escape;
        /** 이스케이프 적용 횟수 */
        final int escCount;

        /**
         * 속성 키와 값을 파싱하여 객체를 생성합니다.
         *
         * @param propKey
         *            토큰 문자
         * @param propValue
         *            콤마({@code ,})로 구분된 이스케이프 문자열 및 횟수 (예: {@code "\\,2"})
         *
         * @throws NullPointerException
         *             파라미터({@code propValue})가 {@code null}인 경우 발생.
         * @throws IllegalArgumentException
         *             {@code propValue} 형식이 올바르지 않은 경우 (콤마 구분자 누락)
         * @throws NumberFormatException
         *             이스케이프 횟수를 정수로 변환할 수 없는 경우 발생.
         */
        // 아래 내용에 적용됨.
        // - String.substring(...)
        // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
        // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
        @SuppressWarnings("null")
        RegExTokenEscape(char propKey, String propValue) {
            Objects.requireNonNull(propValue, "propValue는 null일 수 없습니다.");

            this.token = propKey;

            // [PATCH] 정규식(split) 엔진 구동 및 String[] 배열 할당 오버헤드를 완전히 제거한 파싱 로직
            int commaIndex = propValue.indexOf(',');

            // Fail-Fast: 콤마가 없는 비정상 데이터 방어
            if (commaIndex < 0) {
                throw new IllegalArgumentException("올바르지 않은 속성 값 형식입니다. (콤마 구분자 누락): " + propValue);
            }

            this.escape = propValue.substring(0, commaIndex);
            this.escCount = Integer.parseInt(propValue.substring(commaIndex + 1));
        }
    }
}
