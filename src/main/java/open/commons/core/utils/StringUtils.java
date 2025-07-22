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
import java.util.Optional;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.function.Function;

import open.commons.core.log.LogUtils;
import open.commons.core.prog.StrLenRvrOrderingEntry;

/**
 * 
 * <BR>
 * 
 * @since 2011. 06. 23.
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
public class StringUtils {
    public static final String MULTI_LINED_COMMENT_BEGIN = "^(\\s*)/\\*(.*)$";

    public static final String MULTI_LINED_COMMENT_END = "^(.*)\\*/.*$";

    static Map<String, RegExTokenEscape> map = new HashMap<String, RegExTokenEscape>();

    static {
        try {
            Properties regExTokenEscape = new Properties();
            regExTokenEscape.load(StringUtils.class.getResourceAsStream("/properties/RegExTokenEscape.properties"));

            for (Entry<Object, Object> entry : regExTokenEscape.entrySet()) {
                map.put((String) entry.getKey(), new RegExTokenEscape(((String) entry.getKey()).charAt(0), (String) entry.getValue()));
            }
        } catch (Exception ignored) {
        }
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
     * @return
     */
    public static String appendBlank(String string) {
        if (string != null && !string.isEmpty()) {
            return string + " ";
        } else {
            return " ";
        }
    }

    /**
     * 각각의 문자열들 뒤에 빈칸을 추가합니다.
     * 
     * @param strings
     * @return
     */
    public static String[] appendBlank(String... strings) {
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
     * @return
     */
    public static int backIndexOf(String string, char ch) {
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
     * 예: 
     * indiceOfFromBack("1234512345", '2') -> {3, 8}
     * 
     * [Syllable]
     *  - SL: String's <b>Length</b>
     *  - NI: <b>Normal-Index</b> of a character in a String
     *  - BI: <b>Back-Index</b> of a character in a String
     * 
     * [Formula]
     *  - <code><b>NI + BI + 1 = SL</b></code>
     * 
     * [Background]
     * string        : 1 | 2 | 3 | 4 | 5 | 1 | 2 | 3 | 4 | 5
     * 
     *                 increasing -->
     * normal   index: 0   1   2   3   4   5   6   7   8   9
     * backward index: 9   8   7   6   5   4   3   2   1   0
     *                                        <-- increasing
     * </pre>
     * 
     * @param string
     *            문자열
     * @param c
     * @return <BR>
     * @since 2012. 02. 21.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static int[] backIndiceOf(String string, char c) {
        if (string.indexOf(c) < 0) {
            return new int[0];
        } else {
            int[] result = new int[string.length()];

            int index = 0;
            char[] ca = string.toCharArray();
            for (int i = ca.length - 1; i > -1; i--) {
                if (ca[i] == c) {
                    result[index++] = i;
                }
            }

            /**
             * <pre>
             * 최초 배열의 길이는 주어진 문자열의 길이와 동일함으로 역순화하기 위해서 실제 데이타가 존재하는 길이의 새로운 배열을 생성합니다. 여기에서 'index'의 값을 길이로 사용할 수 있는 이유는
             * 'index'가 찾고자 하는 문자가 존재하는 경우만 증가되었기 때문이다.
             * 
             */
            result = ArrayUtils.copyOf(result, index);

            backwarding(result, string.length());

            return result;
        }
    }

    /**
     * 주어진 문자열 안에 찾고자하는 문자열의 첫문자 인덱스들을 반환합니다. 인덱스들은 뒤에서부터 첫문자의 순서이다.<br>
     * 찾고자 하는 문자열이 존재하지 않는 경우 길이가 0인 배열을 반환합니다.
     * 
     * <pre>
     * 예: 
     * indiceOfFromBack("1234512345", "12") -> {4, 9}
     * 
     * [Syllable]
     *  - SL: String's <b>Length</b>
     *  - NI: <b>Normal-Index</b> of a character in a String
     *  - BI: <b>Back-Index</b> of a character in a String
     * 
     * [Formula]
     *  - <code><b>NI + BI + 1 = SL</b></code>
     * 
     * [Background]
     * string        : 1 | 2 | 3 | 4 | 5 | 1 | 2 | 3 | 4 | 5
     * 
     *                 increasing -->
     * normal   index: 0   1   2   3   4   5   6   7   8   9
     * backward index: 9   8   7   6   5   4   3   2   1   0
     *                                        <-- increasing
     * </pre>
     * 
     * @param sourceString
     * @param searchedString
     * @return <BR>
     * @since 2012. 02. 22.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static int[] backIndiceOf(String sourceString, String searchedString) {
        if (searchedString.length() == 1) {
            return backIndiceOf(sourceString, searchedString.charAt(0));
        } else if (sourceString.indexOf(searchedString) < 0) {
            return new int[0];
        } else {
            int[] result = indiceOf(sourceString, searchedString);

            if (result.length < 0) {
                return new int[0];
            } else {
                result = ArrayUtils.reverse(result);
                backwarding(result, sourceString.length());
                return result;
            }
        }
    }

    /**
     * <pre>
     * 예: 
     * int[] array = {1,3,6,8,12};
     * backwarding(array, 15);
     * System.out.println(Arrays.toString(array));
     * 
     * 결과: {13, 11, 8, 6, 2}
     * 
     * [Syllable]
     *  - SL: String's <b>Length</b>
     *  - NI: <b>Normal-Index</b> of a character in a String
     *  - BI: <b>Back-Index</b> of a character in a String
     * 
     * [Formula]
     *  - <code><b>NI + BI + 1 = SL</b></code>
     * 
     * [Background]
     * string        : 1 | 2 | 3 | 4 | 5 | 1 | 2 | 3 | 4 | 5
     * 
     *                 increasing -->
     * normal   index: 0   1   2   3   4   5   6   7   8   9
     * backward index: 9   8   7   6   5   4   3   2   1   0
     *                                        <-- increasing
     * </pre>
     * 
     * @param array
     *            the character array of a string
     * @param SL
     *            the length of a string <BR>
     * @exception NullPointerException
     *                if an <code>array</code> is null.
     * 
     * @since 2012. 02. 22.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    private static void backwarding(int[] array, final int SL) {
        /**
         * [Formula] - <code><b>NI + BI + 1 = SL</b></code>
         */
        for (int i = 0; i < array.length; i++) {
            array[i] = SL - array[i] - 1;
        }
    }

    /**
     * 주어진 문자열의 길이가 제한길이보다 긴 경우 (...)으로 표현된 문자열을 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 1. 5.		박준홍			최초 작성
     * </pre>
     *
     * @param str
     *            문자열
     * @param len
     *            제한길이
     * @return
     *
     * @since 2022. 1. 5.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static String compact(String str, int len) {
        AssertUtils2.assertNotNull(str);
        AssertUtils2.assertFalse(len < 1);

        str = str.trim();
        if (str.length() <= len) {
            return str;
        } else {
            return new StringBuilder().append(str.substring(0, len - 3)).append("...").toString();
        }
    }

    /**
     * 주어진 문자열을 하나의 문자열로 연결하여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 10. 15.        박준홍         최초 작성
     * </pre>
     *
     * @param strings
     *            문자열
     * @param delimeter
     *            문자열 연결자
     * @param startsWithDelimeter
     *            구분자로 시작하는지 여부
     * @param trim
     *            String.trim() 처리 여부
     * @param addNulpty
     *            문자열이 Null 또는 빈문자열(Empty)인 경우 추가 여부
     * @return
     *
     * @since 2020. 1. 16
     * @version
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static String concat(List<String> strings, String delimeter, boolean startsWithDelimeter, boolean trim, boolean addNulpty) {
        AssertUtils2.assertNotNull(delimeter);
        AssertUtils2.assertNotNull(strings);

        StringBuilder buf = new StringBuilder();

        Iterator<String> itr = strings.iterator();
        append(buf, startsWithDelimeter ? delimeter : "", next(itr.next(), trim, addNulpty));

        while (itr.hasNext()) {
            append(buf, buf.length() < 1 && !startsWithDelimeter ? "" : delimeter, next(itr.next(), trim, addNulpty));
        }

        return buf.toString();
    }

    /**
     * 주어진 문자열을 하나의 문자열로 연결하여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 10. 15.        박준홍         최초 작성
     * </pre>
     *
     * @param delimeter
     *            문자열 연결자
     * @param startsWithDelimeter
     *            구분자로 시작하는지 여부
     * @param trim
     *            String.trim() 처리 여부
     * @param addNulpty
     *            문자열이 Null 또는 빈문자열(Empty)인 경우 추가 여부
     * @param strings
     *            문자열
     * @return
     *
     * @since 2019. 10. 15.
     * @version
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static String concat(String delimeter, boolean startsWithDelimeter, boolean trim, boolean addNulpty, String... strings) {
        AssertUtils2.assertNotNull(delimeter);
        AssertUtils2.assertNotNull(strings);

        StringBuilder buf = new StringBuilder();

        Iterator<String> itr = Arrays.asList(strings).iterator();
        append(buf, startsWithDelimeter ? delimeter : "", next(itr.next(), trim, addNulpty));

        while (itr.hasNext()) {
            append(buf, buf.length() < 1 && !startsWithDelimeter ? "" : delimeter, next(itr.next(), trim, addNulpty));
        }

        return buf.toString();
    }

    /**
     * 문자열들({@code strings}) 사이에 구분자({@code delimeter})를 추가합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 6. 21.		박준홍			최초 작성
     * </pre>
     *
     * @param delimeter
     *            구분자
     * @param startsWithDelimeter
     *            구분자를 제일 앞에 넣을지 여부
     * @param data
     *            데이터
     * @return
     *
     * @since 2019. 6. 21.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <T> String concatenate(String delimeter, boolean startsWithDelimeter, Collection<T> data) {
        if (data.isEmpty()) {
            return "";
        } else {
            StringBuffer sb = new StringBuffer();
            Iterator<T> itr = data.iterator();

            Object datum = itr.next();
            if (startsWithDelimeter) {
                sb.append(delimeter);
            }
            sb.append(datum);

            while (itr.hasNext()) {
                datum = itr.next();

                sb.append(delimeter);
                sb.append(datum);
            }

            return sb.toString();
        }
    }

    /**
     * 문자열들({@code strings}) 사이에 구분자({@code delimeter})를 추가합니다. <br>
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 6. 21.		박준홍			최초 작성
     * </pre>
     *
     * @param delimeter
     *            구분자
     * @param startsWithDelimeter
     *            구분자를 제일 앞에 넣을지 여부
     * @param data
     *            데이터
     * @param gen
     *            데이터 변환함수
     * @return
     *
     * @since 2019. 6. 21.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <T, R> String concatenate(String delimeter, boolean startsWithDelimeter, Collection<T> data, Function<T, R> gen) {
        if (data.isEmpty()) {
            return "";
        } else {
            StringBuffer sb = new StringBuffer();
            Iterator<T> itr = data.iterator();

            T datum = itr.next();
            if (startsWithDelimeter) {
                sb.append(delimeter);
            }
            sb.append(gen.apply(datum));

            while (itr.hasNext()) {
                datum = itr.next();

                sb.append(delimeter);
                sb.append(gen.apply(datum));
            }

            return sb.toString();
        }
    }

    /**
     * 문자열들({@code strings}) 사이에 구분자({@code delimeter})를 추가합니다. <br>
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 6. 21.		박준홍			최초 작성
     * </pre>
     *
     * @param delimeter
     *            구분자
     * @param startsWithDelimeter
     *            구분자를 제일 앞에 넣을지 여부
     * @param data
     *            데이터
     * @param gen
     *            데이터 변환함수
     * @return
     *
     * @since 2019. 6. 21.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <K, V, R> String concatenate(String delimeter, boolean startsWithDelimeter, Map<K, V> data, Function<Entry<K, V>, R> gen) {
        return concatenate(delimeter, startsWithDelimeter, data.entrySet(), gen);
    }

    /**
     * 문자열들({@code strings}) 사이에 구분자({@code delimeter})를 추가합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 6. 21.		박준홍			최초 작성
     * </pre>
     *
     * @param delimeter
     *            구분자
     * @param startsWithDelimeter
     *            구분자를 제일 앞에 넣을지 여부
     * @param strings
     *            데이터
     * @return
     *
     * @since 2019. 6. 21.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static String concatenate(String delimeter, boolean startsWithDelimeter, Object... strings) {
        if (strings.length < 1) {
            return "";
        }

        StringBuffer sb = new StringBuffer();
        if (startsWithDelimeter) {
            sb.append(delimeter);
        }
        sb.append(strings[0]);

        for (int i = 1; i < strings.length; i++) {
            sb.append(delimeter);
            sb.append(strings[i]);
        }

        return sb.toString().trim();
    }

    /**
     * 문자열들({@code strings}) 사이에 구분자({@code delimeter})를 추가합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 6. 21.		박준홍			최초 작성
     * </pre>
     *
     * @param delimeter
     *            구분자
     * @param startsWithDelimeter
     *            구분자를 제일 앞에 넣을지 여부
     * @param strings
     *            데이터
     * @return
     *
     * @since 2019. 6. 21.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static String concatenate(String delimeter, boolean startsWithDelimeter, String... strings) {
        if (strings.length < 1) {
            return "";
        }

        StringBuffer sb = new StringBuffer();
        if (startsWithDelimeter) {
            sb.append(delimeter);
        }
        sb.append(strings[0]);

        for (int i = 1; i < strings.length; i++) {
            sb.append(delimeter);
            sb.append(strings[i]);
        }

        return sb.toString().trim();
    }

    /**
     * 문자열들({@code strings}) 사이에 구분자({@code delimeter})를 추가합니다.
     * 
     * @param delimeter
     *            구분자
     * @param data
     *            데이터
     * @return
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * @since 2012. 01. 17.
     */
    public static <T> String concatenate(String delimeter, Collection<T> data) {
        return concatenate(delimeter, false, data);
    }

    /**
     * 문자열들({@code strings}) 사이에 구분자({@code delimeter})를 추가합니다. <br>
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 6. 21.     박준홍         최초 작성
     * </pre>
     *
     * @param delimeter
     *            구분자
     * @param data
     *            데이터
     * @param gen
     *            데이터 변환함수
     * @return
     *
     * @since 2019. 6. 21.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @see #concatenate(String, boolean, Collection, Function)
     */
    public static <T, R> String concatenate(String delimeter, Collection<T> strings, Function<T, R> gen) {
        return concatenate(delimeter, false, strings, gen);
    }

    /**
     * 문자열들({@code strings}) 사이에 구분자({@code delimeter})를 추가합니다. <br>
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 6. 21.		박준홍			최초 작성
     * </pre>
     *
     * @param delimeter
     *            구분자
     * @param data
     *            데이터
     * @param gen
     *            데이터 변환함수
     * @return
     *
     * @since 2019. 6. 21.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <K, V, R> String concatenate(String delimeter, Map<K, V> data, Function<Entry<K, V>, R> gen) {
        return concatenate(delimeter, false, data, gen);
    }

    /**
     * 문자열들({@code strings}) 사이에 구분자({@code delimeter})를 추가합니다.
     * 
     * @param delimeter
     *            구분자
     * @param data
     *            데이터
     * @return
     */
    public static String concatenate(String delimeter, Object... data) {
        if (data.length < 1) {
            return "";
        }

        StringBuffer sb = new StringBuffer();
        sb.append(data[0]);

        for (int i = 1; i < data.length; i++) {
            sb.append(delimeter);
            sb.append(data[i]);
        }

        return sb.toString().trim();
    }

    /**
     * 문자열들({@code strings}) 사이에 구분자({@code delimeter})를 추가합니다.
     * 
     * @param delimeter
     *            구분자
     * @param strings
     *            데이터
     * @return
     */
    public static String concatenate(String delimeter, String... strings) {
        return concatenate(delimeter, false, strings);
    }

    /**
     * 대상 문자열에 주어진 문자열이 포함되어 있는지 여부를 제공합니다. <br>
     * 단 하나의 문자열만 포함되어 있어도 <code>true</code>를 반환합니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 11. 9.		박준홍			최초 작성
     * </pre>
     *
     * @param string
     *            대상 문자열
     * @param strs
     *            포함여부를 확인할 문자열
     * @return
     *
     * @since 2020. 11. 9.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static boolean contains(String string, CharSequence... strs) {
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
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * @date 2012. 1. 6.
     * 
     * @param string
     *            문자열
     * @param strs
     * @return
     */
    public static boolean containsAll(String string, CharSequence... strs) {
        for (CharSequence str : strs) {
            if (!string.contains(str))
                return false;
        }
        return true;
    }

    /**
     * 문자열이 숫자를 포함하고 있는지 여부를 반환합니다.
     * 
     * @param string
     * @return
     * @see do not check {@code null}
     */
    public static boolean containsDigit(String string) {
        for (char ch : string.toCharArray()) {
            if (Character.isDigit(ch)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 첫번째 문자열이 두번째 문자열을 대소문자 구분 없이 포함하고 있는지 여부를 반환합니다.
     * 
     * @param container
     * @param string
     *            문자열
     * @return
     * @since 2012. 7. 7.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static boolean containsIgnorecase(String container, String string) {
        if (container == null || string == null)
            return false;

        return container.toLowerCase().contains(string.toLowerCase());
    }

    /**
     * 소문자가 있는지 여부를 반환합니다.
     * 
     * @param string
     *            문자열
     * @return
     */
    public static boolean containsLowcase(String string) {
        for (char c : string.toCharArray()) {
            if (Character.isLowerCase(c))
                return true;
        }
        return false;
    }

    /**
     * 문자열이 {@code chs}에 포함된 캐릭터들 중에 1개인지 여부를 반환합니다. 당근 길이는 1 인 문자열이다.
     * 
     * @param string
     *            문자열
     * @param chs
     * @return
     */
    public static boolean containsOnlyParameters(String string, char... chs) {
        if (string.trim().length() > 1) {
            return false;
        } else {
            char c = string.trim().toCharArray()[0];
            for (char ch : chs) {
                if (ch == c) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * 대문자를 포함하고 있는지 여부를 반환합니다.
     * 
     * @param string
     *            문자열
     * @return
     */
    public static boolean containsUppercase(String string) {
        for (char c : string.toCharArray()) {
            if (Character.isUpperCase(c))
                return true;
        }
        return false;
    }

    /**
     * 주어진 문자열에 Whitespace가 포함되어 있는지 확인합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 6. 28.		박준홍			최초 작성
     * </pre>
     *
     * @param string
     *            문자열
     * @return
     *
     * @since 2019. 6. 28.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static boolean containsWhitespace(String string) {
        for (char c : string.toCharArray()) {
            if (Character.isWhitespace(c)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 문자열({@code string})에 포함되어 있는 캐릭터({@code ch}) 개수를 반환합니다.
     * 
     * @param string
     *            문자열
     * @param ch
     * @return
     */
    public static int countOf(String string, char ch) {
        int retValue = 0;

        for (char c : string.toCharArray()) {
            if (ch == c)
                retValue++;
        }
        return retValue;
    }

    /**
     * 문자열({@code string})에 포함되어 있는 문자열({@code target}) 개수를 반환합니다.
     * 
     * @param string
     *            문자열
     * @param target
     * @return
     */
    public static int countOf(String string, String target) {
        int count = 0;
        int sIndex = -1;
        while ((sIndex = string.indexOf(target)) != -1) {
            count++;
            string = string.substring(sIndex + target.length());
        }

        return count;
    }

    /**
     * 주어진 길이만큼 문자열을 자르고, 뒤에 " ..." 을 붙어 반환합니다.
     * 
     * @param string
     *            문자열
     * @param length
     *            (... 포함된 길이)
     * @return
     *
     * @since 2017. 1. 5.
     */
    public static String cutOff(String string, int length) {

        if (string == null) {
            return "null";
        }

        int strLen = string.length();

        if (strLen < length - 3) { // length - 4 + 1
            return string;
        }

        StringBuffer sb = new StringBuffer();

        sb.append(string.substring(0, length - 4));
        sb.append(" ...");

        return sb.toString();
    }

    /**
     * {@code pre}와 {@code suf}로 둘어싸인 제일 큰 문자열을 반환합니다.
     * 
     * @param string
     *            문자열
     * @param pre
     * @param suf
     * @return
     */
    public static String enclosingLargestString(String string, String pre, String suf) {
        int preIndex = string.indexOf(pre);
        if (preIndex < 0) {
            return null;
        }

        int countOf = countOf(string, suf);

        if (pre.equals(suf) && countOf < 2) {
            return null;
        }

        int sufIndex = indexOf(string, suf, countOf);

        if (sufIndex < 0 || sufIndex < preIndex + pre.length()) {
            return null;
        }

        return (String) string.subSequence(preIndex + pre.length(), sufIndex);

    }

    /**
     * {@code pre}와 {@code suf}로 둘어싸인 제일 작은 문자열을 반환합니다.
     * 
     * @param string
     *            문자열
     * @param pre
     * @param suf
     * @return 길이 2인 배열. 만족하지 않는 경우 {@code null} 반환
     */
    public static String enclosingSmallestString(String string, String pre, String suf) {
        int preIndex = string.indexOf(pre);
        if (preIndex < 0) {
            return null;
        }

        int sufIndex = -1;// string.indexOf(suf);

        if (pre.equals(suf)) {
            if ("\"".equals(pre)) {
                sufIndex = indexOf(string, "\"", 2);
            } else {
                sufIndex = indexOf(string, suf, 2);
            }
        } else {
            sufIndex = string.indexOf(suf);
        }

        if (sufIndex < 0) {
            return null;
        }

        return preIndex + pre.length() < sufIndex ? (String) string.subSequence(preIndex + pre.length(), sufIndex) : null;
    }

    /**
     * 문자열이 주어진 <b><code>suffix</code></b>로 끝나는지 여부를 반환합니다. (대소문자 관계없이)
     * 
     * @param string
     *            문자열
     * @param suffix
     * @return
     * 
     * @see String#endsWith(String)
     */
    public static boolean endsWithIgnoreCase(String string, String suffix) {
        boolean endsWith = false;

        if (string != null && suffix != null //
                && string.length() >= suffix.length() // check lengths of two strings.
        ) {
            String tailStr = string.substring(string.length() - suffix.length());
            endsWith = tailStr.equalsIgnoreCase(suffix);
        }

        return endsWith;
    }

    /**
     * 문자열({@code string})이 접두어({@code suffixes})들 중에 하나로 끝나는지 여부를 반환합니다. (대소문자 관계없이)
     * 
     * @param string
     *            문자열
     * @param suffixes
     * @return
     */
    public static boolean endsWithIgnoreCaseOneOf(String string, String... suffixes) {

        for (String suffix : suffixes) {
            if (endsWithIgnoreCase(string, suffix)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 문자열({@code string})이 접두어({@code suffixes})들 중에 하나로 끝나는지 여부를 반환합니다.
     * 
     * @param string
     *            문자열
     * @param suffixes
     * @return
     */
    public static boolean endsWithOneOf(String string, String... suffixes) {

        for (String suffix : suffixes) {
            if (string.endsWith(suffix)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 주어진 문자열이 포함되었는지 여부를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 9. 25.		박준홍			최초 작성
     * </pre>
     *
     * @param target
     * @param strings
     * @return
     *
     * @since 2020. 9. 25.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static boolean exists(String target, Collection<String> strings) {
        for (String str : strings) {
            if (str.equals(target)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 주어진 문자열이 포함되었는지 여부를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 9. 25.		박준홍			최초 작성
     * </pre>
     *
     * @param target
     * @param strings
     * @return
     *
     * @since 2020. 9. 25.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static boolean exists(String target, String... strings) {
        return strings != null ? exists(target, Arrays.asList(strings)) : false;
    }

    /**
     * 주어진 문자열이 포함되었는지 여부를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 9. 25.		박준홍			최초 작성
     * </pre>
     *
     * @param target
     * @param strings
     * @return
     *
     * @since 2020. 9. 25.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static boolean existsIgnoreCase(String target, Collection<String> strings) {
        for (String str : strings) {
            if (str.equalsIgnoreCase(target)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 주어진 문자열이 포함되었는지 여부를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 9. 25.		박준홍			최초 작성
     * </pre>
     *
     * @param target
     * @param strings
     * @return
     *
     * @since 2020. 9. 25.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static boolean existsIgnoreCase(String target, String... strings) {
        return strings != null ? existsIgnoreCase(target, Arrays.asList(strings)) : false;
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2023. 9. 27.     	박준홍			최초 작성
     * 2023. 12. 15.		박준홍			접근자 변경 (private -> public)
     * </pre>
     *
     * @param buf
     * @param list
     *
     * @since 2023. 9. 27.
     * @version 2.0.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static void flushBuffer(StringBuffer buf, ArrayList<String> list) {
        if (buf.length() < 1) {
            return;
        }

        list.add(buf.toString());
        buf.setLength(0);
    }

    /**
     * 다수의 문자열 값을 하나의 byte 배열로 반환합니다.
     * 
     * @param strings
     * @return
     */
    public static byte[] getBytes(String... strings) {
        byte[][] bytes = new byte[strings.length][];

        for (int i = 0; i < strings.length; i++) {
            bytes[i] = strings[i].getBytes();
        }

        return ArrayUtils.merge(bytes);
    }

    /**
     * 문자열 중에서 자바 및 C/C++ 코멘트 부분을 추출합니다.<br>
     * 
     * @param string
     *            문자열
     * @return
     */
    public static String getComment(String string) {
        final int[] sIndex = getCommentType(string);
        int eIndex = -1;
        switch (sIndex[0]) {
            case 1: // type is '/**'
                eIndex = string.indexOf("*/");
                if (eIndex > sIndex[1]) {
                    return string.substring(sIndex[1] + 3, eIndex);
                } else {
                    return "";
                }
            case 2: // type is '/*'
                eIndex = string.indexOf("*/");
                if (eIndex > sIndex[1]) {
                    return string.substring(sIndex[1] + 2, eIndex);
                } else {
                    return "";
                }
            case 3: // type is '//'
                return string.substring(sIndex[1] + 2);
            default:
                return "";
        }
    }

    private static int[] getCommentType(String string) {
        int sIndex = string.indexOf("/**");
        if (sIndex > -1) {
            return new int[] { 1, sIndex };
        }

        sIndex = string.indexOf("/*");
        if (sIndex > -1) {
            return new int[] { 2, sIndex };
        }

        sIndex = string.indexOf("//");
        if (sIndex > -1) {
            return new int[] { 3, sIndex };
        }

        return new int[] { 0, sIndex };
    }

    /**
     * 문자열에서 큰따옴표로 묶인 문자열을 반환합니다.
     * 
     * @param string
     *            문자열
     * @return 큰따옴표로 묶인 문자열. 없는 경우 {@code null} 반환.
     */
    public static String getDoubleQuotationString(String string) {
        if (string != null) {
            int sIndex = -1;
            if ((sIndex = string.indexOf("\"")) > -1) {
                int eIndex = indexOf(string, "\"", 2);
                if (eIndex > -1) {
                    return string.substring(sIndex + 1, eIndex);
                }
            }
        }
        return null;
    }

    /**
     * 문자열을 구분자로 나눈 후 제일 마지막 값을 반환합니다.
     * 
     * @param string
     *            문자열
     * @param delimiter
     * @return 마지막 문자열. 구분자가 존재하지 않는 경우 <code>null</code>을 반환합니다.
     */
    public static String getLast(String string, String delimiter) {
        String rtnString = null;
        int ps = string.lastIndexOf(delimiter);
        if (ps > -1) {
            rtnString = string.substring(ps + 1, string.length());
        }
        return rtnString;
    }

    /**
     * 문자열에서 찾고 싶은 문자열이 지정한 횟수만큼 나오는 경우 원래 문자열에서 몇번째에 시작되는지를 반환합니다.<br>
     * 지정한 횟수만큼 발생하지 않거나 예외가 발생하면 -1을 반환합니다. <br>
     * 
     * <pre>
     * e.g.
     * indexOf("안녕하세요. 나는 하하입니다.", '하', 2) -> 11
     * indexOf("안녕하세요. 나는 하하입니다.", '하', 4) -> -1
     * </pre>
     * 
     * @param sourceString
     * @param c
     * @param ordinal
     * @return <BR>
     * @since 2012. 02. 21.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static int indexOf(String sourceString, char c, int ordinal) {
        int[] indice = indiceOf(sourceString, c);

        if (indice.length < ordinal || ordinal < 1) {
            return -1;
        } else {
            return indice[ordinal - 1];
        }
    }

    /**
     * 문자열에서 찾고 싶은 문자열이 지정한 횟수만큼 나오는 경우 원래 문자열에서 몇번째에 시작되는지를 반환합니다.<br>
     * 지정한 횟수만큼 발생하지 않거나 예외가 발생하면 -1을 반환합니다.
     * 
     * <pre>
     * e.g.
     * indexOf("안녕하세요. 나는 하하입니다.", "하", 2) -> 11
     * indexOf("안녕하세요. 나는 하하입니다.", "하", 4) -> -1
     * </pre>
     * 
     * 결과:
     * 
     * @param sourceString
     *            대상 문자열
     * @param searchedString
     *            검색 문자열
     * @param ordinal
     *            검색 문자열이 나오는 횟수
     * @return
     * 
     *         <BR>
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * @since 2012. 1. 6.
     */
    public static int indexOf(String sourceString, String searchedString, int ordinal) {
        int[] indice = indiceOf(sourceString, searchedString);
        if (indice.length < ordinal || ordinal < 1) {
            return -1;
        } else {
            return indice[ordinal];
        }
    }

    /**
     * {@code pre}와 {@code suf}로 둘어싸인 문자열을 확보하기 위해서 앞뒤 문자의 인덱스를 길이 2인 배열로 반환합니다.
     * 
     * @param string
     *            문자열
     * @param pre
     * @param suf
     * @return 길이 2인 배열. 만족하지 않는 경우 {@code null} 반환
     */
    public static int[] indexOf(String string, String pre, String suf) {
        int preIndex = string.indexOf(pre);
        if (preIndex < 0) {
            return null;
        }

        int sufIndex = string.indexOf(suf);
        if (sufIndex < 0) {
            return null;
        }

        return preIndex < sufIndex ? new int[] { preIndex, sufIndex } : null;
    }

    /**
     * 주어진 2개의 문자열이 서로 다른 시작부분의 인덱스를 반환합니다.
     * 
     * @param str1
     * @param str2
     * @param begin
     *            비교 시작 위치
     * @return 서로 다른 위치의 인덱스. -1은 다음과 같은 경우에 발생합니다.
     *         <ul>
     *         <li>주어진 문자열이 <code>null</code>
     *         <li>비교 시작 값이 음수
     *         <li>주어진 문자열이 길이가 비교 시작값보다 작은 경우
     *         <li>주어진 문자열의 길이가 0
     *         </ul>
     */
    public static int indexOfDifferent(String str1, String str2, int begin) {

        if (str1 == null || str2 == null || begin < 0 //
                || str1.length() < begin || str2.length() < begin) {
            return -1;
        }

        char[] cs1 = str1.toCharArray();
        char[] cs2 = str2.toCharArray();

        // compare length
        int cl = cs1.length < cs2.length ? cs1.length : cs2.length;

        int i = 0;
        for (; i < cl; i++) {
            if (cs1[i] != cs2[i]) {
                return i;
            }
        }

        return i - 1;
    }

    /**
     * 주어진 2개의 문자열이 서로 다른 시작부분의 인덱스를 반환합니다.
     * 
     * @param str1
     * @param str2
     * @return 서로 다른 위치의 인덱스. -1은 다음과 같은 경우에 발생합니다.
     *         <ul>
     *         <li>주어진 문자열이 <code>null</code>
     *         <li>주어진 문자열의 길이가 0
     *         </ul>
     * 
     * @see #indexOfDifferent(String, String, int)
     */
    public static int indexOfDifferentAtFirst(String str1, String str2) {
        return indexOfDifferent(str1, str2, 0);
    }

    /**
     * 주어진 문자열 안에 찾고자하는 문자의 인덱스들을 반환합니다.<br>
     * 캐릭터가 존재하지 않는 경우 길이가 0 인 배열을 반환합니다.
     * 
     * @param string
     *            문자열
     * @param c
     * @return
     * 
     * @throws NullPointerException
     *             <code>string</code>값이 <code>null</code>인 경우 <BR>
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * @since 2011. 11. 06.
     */
    public static int[] indiceOf(String string, char c) {
        if (string.indexOf(c) < 0) {
            return new int[0];
        } else {
            return ArrayUtils.indiceOf(string.toCharArray(), c);
        }
    }

    /**
     * 주어진 문자열 안에 찾고자 하는 문자열의 인덱스들을 반환합니다.<br>
     * 찾고자 하는 문자열이 존재하지 않는 경우 길이가 0인 배열을 반환합니다.
     * 
     * @param sourceString
     * @param searchedString
     * @return <BR>
     * @since 2012. 02. 21.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static int[] indiceOf(String sourceString, String searchedString) {
        if (searchedString.length() == 1) {
            return indiceOf(sourceString, searchedString.charAt(0));
        } else if (sourceString.indexOf(searchedString) < 0) {
            return new int[0];
        } else {
            int[] result = new int[sourceString.length() / searchedString.length()];

            int index = 0;
            int searchedIndex = -1;
            for (int i = 0; i < sourceString.length();) {
                if ((searchedIndex = sourceString.indexOf(searchedString, i)) > -1) {
                    result[index++] = searchedIndex;
                    i = searchedIndex + searchedString.length();
                } else {
                    i++;
                }
            }

            if (result.length == index) {
                return result;
            } else {
                return ArrayUtils.copyOf(result, index);
            }
        }
    }

    /**
     * 주어진 문자열이 모두 소문자인지 여부를 반환합니다.
     * 
     * @param string
     *            문자열
     * @return <BR>
     * @since 2012. 01. 19.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static boolean isAllLowcase(String string) {
        return !containsUppercase(string);
    }

    /**
     * 주어진 문자열이 모두 대문자인지 여부를 반환합니다.
     * 
     * @param string
     *            문자열
     * @return <BR>
     * @since 2012. 01. 19.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static boolean isAllUppercase(String string) {
        return !containsLowcase(string);
    }

    /**
     * 주어진 문자열이 10진수 숫자인지 여부를 반환합니다.
     * 
     * @param str
     * @return
     */
    public static boolean isDecimalNumber(String str) {
        if (str == null || (str = str.trim()).length() < 1) {
            return false;
        }

        char[] cs = str.toCharArray();

        int pos = 0;

        if (cs[0] == '-') {
            pos = 1;
        }

        for (; pos < cs.length; pos++) {
            if (cs[pos] < 48 || cs[pos] > 57) {
                return false;
            }
        }

        // for (char n : str.toCharArray()) {
        //
        // if (n < 48 || n > 57) {
        // return false;
        // }
        // }

        return true;
    }

    /**
     * 빈 문자열인지 코멘트인지 여부를 반환합니다.
     * 
     * @param string
     *            문자열
     * @return
     */
    public static boolean isEmptyOrComment(String string) {
        // getComment(string).trim().isEmpty();
        return string.trim().isEmpty() || removeComment(string).trim().isEmpty();
    }

    /**
     * 문자열이 자바변수인지 여부를 반환합니다.
     * 
     * @param string
     *            문자열
     * @return
     */
    public static boolean isJavaIdentifier(String string) {
        if (string != null && !string.isEmpty()) {
            char[] chs = string.toCharArray();
            if (Character.isJavaIdentifierStart(chs[0])) {
                for (int i = 1; i < chs.length; i++) {
                    if (!Character.isJavaIdentifierPart(chs[i])) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    public static boolean isMultiLinedCommenEnded(String string) {
        try {
            return string.matches(MULTI_LINED_COMMENT_END);
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isMultiLinedCommentBegan(String string) {
        try {
            return string.matches(MULTI_LINED_COMMENT_BEGIN);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 주어진 문자열이 <code>null</code>이거나 trim() 처리후 빈 문자열인지 여부를 반환합니다.
     * 
     * @param string
     *            문자열
     * @return
     * 
     *         <BR>
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * @since 2012. 01. 11.
     */
    public static boolean isNullOrEmptyString(String string) {
        if (isWhiteSpace(string)) {
            return false;
        } else {
            return string == null || string.trim().isEmpty();
        }
    }

    /**
     * 주어진 문자열들 모두 <code>null</code>이거나 trim() 처리후 빈 문자열인지 여부를 반환합니다.
     * 
     * @param strings
     * @return <BR>
     * @since 2012. 01. 19.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static boolean isNullOrEmptyStringAnd(String... strings) {
        for (String string : strings) {
            if (!isNullOrEmptyString(string)) {
                return false;
            }
        }

        return true;
    }

    /**
     * 주어진 문자열들 중에 <code>null</code>이거나 trim() 처리후 빈 문자열인지 포함되어 있는지 여부를 반환합니다.
     * 
     * @param strings
     * @return <BR>
     * @since 2012. 01. 19.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static boolean isNullOrEmptyStringOr(String... strings) {
        for (String string : strings) {
            if (isNullOrEmptyString(string)) {
                return true;
            }
        }

        return false;
    }

    public static OneCharacterString isOneCharacter(String string) {
        if (string == null || string.length() < 1) {
            throw new IllegalArgumentException("\"string\" must not be null and the length must be longer that zereo");
        }
        char[] cs = string.toCharArray();

        for (int i = 0; i < cs.length - 1; i++) {
            if (cs[i] != cs[i + 1]) {
                return null;
            }
        }

        return new OneCharacterString(string);
    }

    public static boolean isSingleLinedComment(String string) {
        string = string.trim();
        return string.startsWith("//") || (string.startsWith("/*") && string.endsWith("*/"));
    }

    /**
     * Returns whether <b><code>string</code></b> is a whitespace or not.
     * 
     * @param string
     *            문자열
     * @return
     * @since 2012. 6. 28.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static boolean isWhiteSpace(String string) {
        if (string != null) {
            string = string.trim();
            if (string.length() > 1) {
                return false;
            } else {
                return string.isEmpty() ? false : Character.isWhitespace(string.charAt(0));
            }
        } else {
            return false;
        }
    }

    /**
     * 문자열에서 찾고자하는 문자의 가장 마지막 위치를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2023. 11. 27.		박준홍			최초 작성
     * </pre>
     *
     * @param string
     * @param c
     * @return 문자의 가장 마지막 위치. 없는 경우 -1.
     *
     * @since 2023. 11. 27.
     * @version 2.0.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static int lastIndexOf(String string, char c) {

        if (string == null || string.isEmpty()) {
            return -1;
        }

        for (int idx = string.length() - 1; idx > -1; idx--) {
            if (string.charAt(idx) == c) {
                return idx;
            }
        }

        return -1;
    }

    /**
     * <b><code>long</code></b> 타입의 데이터를 주어진 길이만큼 Left Zero-Padding을 적용시켜 문자열로 반환시킨다.<br>
     * 단, 데이터가 길이보다 긴 경우 원본 데이터를 문자열로 반환합니다.
     * 
     * @param l
     * @param length
     * @return
     */
    public static String lpad(long l, int length) {
        return lpad(String.valueOf(l), length);
    }

    /**
     * <b><code>long</code></b> 타입의 데이터를 주어진 길이만큼 Left Zero-Padding을 적용시켜 문자열로 반환시킨다.<br>
     * 
     * @param l
     * @param length
     * @return
     */
    public static String lpad(long l, int length, boolean ommit) {
        return lpad(String.valueOf(l), length, ommit);
    }

    /**
     * 문자열을 주어진 길이만큼 Left Zero-Padding 을 적용시켜 반환합니다.<br>
     * 단, 문자열의 길이가 길이보다 긴 경우 원본 데이터를 반환합니다.
     * 
     * @param str
     * @param length
     * @return
     */
    public static String lpad(String str, int length) {
        if (str == null || str.length() >= length || length < 0) {
            return str;
        }

        char[] cs = new char[length];

        int i = 0;
        for (; i < length - str.length(); i++) {
            cs[i] = '0';
        }

        System.arraycopy(str.toCharArray(), 0, cs, i, str.length());

        return new String(cs);
    }

    /**
     * 문자열을 주어진 길이만큼 Left Zero-Padding 을 적용시켜 반환합니다.<br>
     * 
     * @param str
     * @param length
     * @param ommit
     *            남는 부분 제거 여부
     * @return
     */
    public static String lpad(String str, int length, boolean ommit) {
        if (str == null || length < 0 || (ommit ? false : str.length() >= length)) {
            return str;
        }

        char[] cs = new char[length];

        int i = 0;
        for (; i < length - str.length(); i++) {
            cs[i] = '0';
        }

        System.arraycopy(str.toCharArray(), str.length() - length + i, cs, i, length - i);

        return new String(cs);
    }

    /**
     * 주어진 문자열에 대해서 왼쪽 trim 결과를 반환합니다.
     * 
     * @param string
     *            문자열
     * @return
     */
    public static String ltrim(String string) {
        char[] cStr = string.toCharArray();

        int i = 0;
        for (; i < cStr.length; i++) {
            if (!Character.isWhitespace(cStr[i])) {
                break;
            }
        }

        return string.substring(i);
    }

    /**
     * 주어진 문자열의 앞에서부터 지우고자 하는 문자가 제거된 문자열을 반환합니다.
     * 
     * @param string
     *            문자열
     * @return <BR>
     * @since 2012. 02. 16.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static String ltrimSpecific(String string, char c) {
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
     * ltrimSpecific("안녕하세요안녕하세요방갑습니다", "안녕하세요") -> 방갑습니다
     * 
     * @param sourceString
     *            주어진 문자열
     * @param targetString
     *            지우고자 하는 문자열
     * @return 새로운 문자열<BR>
     * @since 2012. 02. 21.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static String ltrimSpecific(String sourceString, String targetString) {
        if (sourceString.indexOf(targetString) != 0) {
            return sourceString;
        } else {
            int[] indice = indiceOf(sourceString, targetString);

            if (indice.length < 2) {
                return sourceString.substring(targetString.length());
            } else {
                // tsl: Targeted String Length
                int tsl = targetString.length();
                int i = 0;
                for (; i < indice.length - 1; i++) {
                    if (indice[i] + tsl != indice[i + 1]) {
                        break;
                    }
                }

                return sourceString.substring(indice[i] + tsl);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("ocurrence.none : " + replace("1234567890", 'x', "abcdefg"));
        System.out.println("ocurrence.first: " + replace("1234567890", '1', "abcdefg"));
        System.out.println("ocurrence.last : " + replace("1234567890", '0', "abcdefg"));
        System.out.println("ocurrence.multi: " + replace("2123452627282920222222222222221", '2', "abcdefg"));
        System.out.println("ocurrence.seq  : " + replace("1233435637890", '3', "abcdefg"));

        System.out.println(" '01239' is decimal ? " + isDecimalNumber("01239"));
        System.out.println("'-01239' is decimal ? " + isDecimalNumber("-01239"));

        System.out.println(">" + rtrim("11111111       ") + "<");

        System.out.println(lpad("1", 2, true));

        String camelCase = "camelCase";
        System.out.println("[K] " + camelCase + " -> " + toKebabCase(camelCase));
        System.out.println("[P] " + camelCase + " -> " + toPascalCase(camelCase));
        System.out.println("[S] " + camelCase + " -> " + toSnakeCase(camelCase));

        System.out.println(splitAndDelimiter("aabbccddeeff", 4, "."));

    }

    private static Optional<String> next(String str, boolean trim, boolean addNulpty) {
        // null 확인
        if (str == null) {
            return Optional.ofNullable(addNulpty ? "null" : null);
        } else
        // 빈 문자열 확인
        if (str.trim().isEmpty()) {
            return Optional.ofNullable(addNulpty ? (trim ? "" : str) : null);
        } else {
            return Optional.of(trim ? str.trim() : str);
        }
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2011. 6. 23.		박준홍			최초 작성
     * 2019. 1. 24.		박준홍			복사 dest pos 버그 수정. (i * n -> i * strArray.length)
     * </pre>
     *
     * @param string
     *            문자열
     * @param n
     * @return
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2011. 6. 23.
     */
    public static String nTimesString(String string, int n) {
        char[] cs = new char[string.length() * n];

        char[] strArray = string.toCharArray();

        for (int i = 0; i < n; i++) {
            System.arraycopy(strArray, 0, cs, i * strArray.length, strArray.length);
        }

        return new String(cs);
    }

    /**
     * 문자열에서 코멘트 부분을 삭제한 문자열을 반환합니다.
     * 
     * @param string
     *            문자열
     * @return
     */
    public static String removeComment(String string) {
        int sIndex = -1;
        // check '/**'
        sIndex = string.indexOf("/**");
        if (sIndex < 0) {
            // check '/*'
            sIndex = string.indexOf("/*");
        }
        if (sIndex > -1) {
            int eIndex = string.indexOf("*/");

            if (eIndex > sIndex) {
                return substring(string, sIndex, eIndex + 2);
            }
        } else
        // check '//'
        if ((sIndex = string.indexOf("//")) > -1) {
            return string.substring(0, sIndex);
        }

        return string;
    }

    /**
     * '_'을 제거하고 바로 다음 문자를 대문자로 변경시킨다.<br>
     * 예: {@code rk_disapp_info -> RkDisappInfo}
     * 
     * @param string
     *            문자열
     * @return
     */
    public static String removeUnderlineAndNextUppercase(String string) {

        StringBuffer sb = new StringBuffer();
        String[] strings = string.split(toRegExString("_"));

        for (int i = 0; i < strings.length; i++) {
            if (strings[i].length() > 0) {
                sb.append(toUpperCase(strings[i], 0));
            } else {
                System.err.println("Oops! zero_string_element: " + string + "'s " + i + "th");
                System.err.println(LogUtils.logCallStack());
            }
        }

        return sb.toString();
    }

    /**
     * '_'을 제거하고 바로 다음 문자만 대문자로 변경시킨다. 나머지 문자는 모두 소문자이다.<br>
     * 예: {@code INT_VALUE -> IntValue}
     * 
     * @param string
     *            문자열
     * @return
     */
    public static String removeUnderlineAndNextUppercaseOtherLowcase(String string) {
        return removeUnderlineAndNextUppercase(string.toLowerCase());
    }

    /**
     * 주어진 문자열에서 문자를 변경합니다.
     * 
     * @param string
     *            변경할 대상 문자열
     * @param o
     *            변경될 대상 문자
     * @param n
     *            새로운 문자
     * @return
     * 
     * @since 2012. 03. 30.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static String replace(String string, char o, char n) {
        if (string != null) {
            char[] cs = string.toCharArray();

            for (int i = 0; i < cs.length; i++) {
                if (cs[i] == o) {
                    cs[i] = n;
                }
            }

            return new String(cs);
        } else {
            return null;
        }
    }

    /**
     * 주어진 문자열에서 인덱스 배열에 해당하는 위치의 문자를 변경합니다.
     * 
     * @param string
     *            문자열
     * @param t
     * @param indice
     * @return
     */
    public static String replace(String string, char t, int... indice) {
        char[] cs = string.toCharArray();

        for (int i = 0; i < indice.length; i++) {
            if (indice[i] < cs.length) {
                cs[indice[i]] = t;
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
     * @param n
     *            새로운 문자열
     * @return
     */
    public static String replace(String string, char o, String n) {
        if (string != null && n != null && n.length() > 0) {
            // If a new string contains only 1 character.
            if (n.length() < 2) {
                return replace(string, o, n.charAt(0));
            }

            int[] oIndice = indiceOf(string, o);

            if (oIndice.length > 0) {
                char[] cs = string.toCharArray();
                char[] ncs = n.toCharArray();

                char[] newcs = new char[cs.length + (ncs.length - 1) * oIndice.length];

                int newcsCpIndex = 0; // The index to be copied of 'char[] newcs'
                int csCpIndex = 0; // The index to be copied of 'char[] cs'
                int lIndiceIndex = -1; // The latest index of 'char o'
                int i = 0;
                for (; i < oIndice.length; i++) {
                    // step.1 : Copy strings between characters to be replaced.
                    if ((lIndiceIndex = oIndice[i] - lIndiceIndex) > 1) {
                        System.arraycopy(cs, csCpIndex, newcs, newcsCpIndex, lIndiceIndex - 1);
                        // Increase the copied index of 'char[] newcs'
                        newcsCpIndex += lIndiceIndex - 1;
                    }
                    // Change a latest index of 'char o'
                    lIndiceIndex = oIndice[i];
                    // Change a next index to be copied of 'char[] cs'
                    csCpIndex = oIndice[i] + 1;

                    // step.2 : Copy a new string
                    System.arraycopy(ncs, 0, newcs, newcsCpIndex, ncs.length);

                    // step.3 : Increase the copied index of 'char[] newcs'
                    newcsCpIndex += ncs.length;
                }

                // step.4: Copy remained strings to 'char[] newcs'
                if (oIndice[--i] < cs.length - 1) {
                    System.arraycopy(cs, oIndice[i] + 1, newcs, newcsCpIndex, cs.length - 1 - oIndice[i]);
                }

                return new String(newcs);

            }
        }

        return string;
    }

    /**
     * 문자열에서 {@code olds}의 내용들을 {@code news}의 내용들로 순서대로 변환한 문자열을 반환합니다.
     * 
     * @param string
     *            문자열
     * @param olds
     * @param news
     * @return 2개의 {@link Collection}의 길이가 다르면 문자열을 그대로 반환합니다.
     */
    public static String replace(String string, Collection<String> olds, Collection<String> news) {
        if (string != null && olds != null && news != null && olds.size() == news.size()) {
            Iterator<String> itrOlds = olds.iterator();
            Iterator<String> itrNews = news.iterator();

            if (itrOlds.hasNext()) {
                string = string.replace(itrOlds.next(), itrNews.next());
            }
            return string;
        } else {
            return string;
        }
    }

    /**
     * 문자열을 {@code map}의 내용에 맞추어 변환한 후 반환합니다.
     * 
     * @param string
     *            문자열
     * @param map
     * @return {@code map}이 {@code null}이거나 문자열이 {@code null}인 경우 문자열을 그대로 반환합니다.
     */
    public static String replace(String string, Map<String, String> map) {
        if (string != null && map != null) {
            Set<StrLenRvrOrderingEntry> set = new ConcurrentSkipListSet<StrLenRvrOrderingEntry>();

            for (Entry<String, String> entry : map.entrySet()) {
                set.add(new StrLenRvrOrderingEntry(entry.getKey(), entry.getValue()));
            }

            for (StrLenRvrOrderingEntry entry : set) {
                string = string.replaceAll(toRegExString(entry.getKey()), entry.getValue());
            }

            // for ( Entry<String, String> entry : map.entrySet() ) {
            // string = string.replace(entry.getKey(), entry.getValue());
            // }
            return string;
        } else {
            return string;
        }
    }

    /**
     * 문자열의 내용을 변경한 후, 구분자로 나눈 문자열 배열을 반환합니다.
     * 
     * @param string
     *            작업 대상 문자열
     * @param oldString
     *            이전 문자열
     * @param newString
     *            새로운 문자열
     * @param delimeter
     *            구분자
     * @return
     */
    public static String[] replaceAndSplit(String string, String oldString, String newString, String delimeter) {
        return string.replace(oldString, newString).split(delimeter);
    }

    /**
     * @param string
     *            문자열
     * @param oldString
     * @param newString
     * @param delimeter
     * @return
     */
    public static String replaceAndSplitAndToString(String string, String oldString, String newString, String delimeter) {
        String[] strings = replaceAndSplit(string, oldString, newString, delimeter);
        return concatenate(delimeter, strings);
    }

    /**
     * 주어진 문자열에서 정해진 문자열들을 모두 빈칸으로 변환한 후 반환합니다.
     * <p>
     * <b> 단, 변경할 문자열들은 입력되는 순서대로 처리된다. </b>
     * </p>
     * 
     * @param string
     *            문자열
     * @param oldStrings
     *            정해진 문자열
     * @return
     */
    public static String replaceToEmptyString(String string, CharSequence... oldStrings) {

        for (CharSequence oldString : oldStrings) {
            string = string.replace(oldString, "").trim();
        }

        return string;
    }

    /**
     * 주어진 문자열에 대해서 오른쪽 trim 결과를 반환합니다.
     * 
     * @param string
     *            문자열
     * @return
     */
    public static String rtrim(String string) {
        char[] cStr = string.toCharArray();

        int idx = cStr.length;
        int i = cStr.length - 1;
        for (; i > -1; i--) {
            if (!Character.isWhitespace(cStr[i])) {
                break;
            }
            idx = i;
        }

        return string.substring(0, idx);
    }

    /**
     * 주어진 문자열의 끝에서부터 지우고자하는 문자가 제거된 문자열을 반환합니다.
     * 
     * @param string
     *            문자열
     * @param c
     * @return <BR>
     * @since 2012. 02. 16.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static String rtrimSpecific(String string, char c) {
        char[] cStr = string.toCharArray();

        int idx = cStr.length;
        int i = cStr.length - 1;
        for (; i > -1; i--) {
            if (cStr[i] != c) {
                break;
            }
            idx = i;
        }

        return string.substring(0, idx);
    }

    /**
     * 주어진 문자열의 끝에서부터 지우고자하는 문자열이 제거된 문자열을 반환합니다.<br>
     * <br>
     * ltrimSpecific("안녕하세요안녕하세요방갑습니다", "방갑습니다") -> 안녕하세요안녕하세요
     * 
     * @param sourceString
     *            주어진 문자열
     * @param targetString
     *            지우고자 하는 문자열
     * @return 새로운 문자열<BR>
     * @since 2012. 02. 21.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static String rtrimSpecific(String sourceString, String targetString) {
        if (sourceString.lastIndexOf(targetString) != sourceString.length() - targetString.length()) {
            return sourceString;
        } else {
            int[] indice = indiceOf(sourceString, targetString);

            if (indice.length < 2) {
                return sourceString.substring(0, indice[0]);
            } else {
                // tsl: Targeted String Length
                int tsl = targetString.length();
                int i = indice.length - 1;
                for (; i > 0; i--) {
                    if (indice[i] - tsl != indice[i - 1]) {
                        break;
                    }
                }

                return sourceString.substring(0, indice[i]);
            }
        }
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
     * @return
     */
    public static String[] split(String string, String delim, boolean trim) {
        return split(string, delim, trim, 0);
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
     * @return
     */
    public static String[] split(String string, String delim, boolean trim, int limit) {
        String[] rtnStrings = string.split("[" + delim + "]", limit);

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
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 4. 2.		박준홍			최초 작성
     * </pre>
     *
     * @param string
     *            문자열
     * @param delim
     *            문자열 구분자
     * @param post
     *            문자열 후처리 함수.
     * @return
     *
     * @since 2025. 4. 2.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static String[] split(String string, String delim, Function<String, String> post) {
        return split(string, delim, post, 0);
    }

    /**
     * 주어진 문자열을 구분자로 분리한 후 배열을 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 4. 2.		박준홍			최초 작성
     * </pre>
     *
     * @param string
     *            문자열
     * @param delim
     *            문자열 구분자
     * @param post
     *            문자열 후처리 함수.
     * @param limit
     *            최대 데이터 개수
     * @return
     *
     * @since 2025. 4. 2.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static String[] split(String string, String delim, Function<String, String> post, int limit) {

        String[] rtnStrings = string.split("[" + delim + "]", limit);

        if (post != null) {
            for (int i = 0; i < rtnStrings.length; i++) {
                rtnStrings[i] = post.apply(rtnStrings[i]);
            }
        }
        return rtnStrings;
    }

    /**
     * 주어진 문자열을 일정크기로 나눈 후, 구분자로 연결한 결과를 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2023. 8. 24.		박준홍			최초 작성
     * </pre>
     *
     * @param string
     *            문자열
     * @param size
     * @param delimeter
     *            구분자
     * @return
     *
     * @since 2023. 8. 24.
     * @version 2.0.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static String splitAndDelimiter(String string, int size, String delimeter) {
        List<String> splited = new ArrayList<>();
        int len = string.length();
        int begin = 0;
        int end = Math.min(size, len);
        while (begin < len) {
            splited.add(string.substring(begin, end));
            begin = end;
            end = Math.min(end + size, len);
        }

        return concatenate(delimeter, splited);
    }

    /**
     * 문자열을 {@code splitRegEx}에 맞추어 배열로 만든 후, 구분자를 나누어진 문자들 사이에 추가한 후 반환합니다.
     * 
     * @param string
     *            문자열
     * @param splitRegEx
     * @param delimeter
     * @return
     */
    public static String splitAndDelimiter(String string, String splitRegEx, String delimeter) {
        String[] strings = string.split(splitRegEx);
        return concatenate(delimeter, strings);
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
     * @return
     */
    public static String splitAndGet(String string, String[] regExs, int[] selectedIndice) {

        if (regExs.length != selectedIndice.length)
            throw new RuntimeException(StringUtils.class.getName() + ".splitAndget(String, String[], int[]): params=" + string + ", " + regExs + ", " + selectedIndice);

        for (int i = 0; i < regExs.length; i++) {
            string = string.split(regExs[i])[selectedIndice[i]];
        }

        return string;
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
     * @return
     */
    public static Collection<String> splitAsCollection(String string, String delim, boolean trim) {
        Collection<String> col = new ArrayList<String>();

        String[] rtnStrings = split(string, delim, trim);

        for (String str : rtnStrings) {
            col.add(str);
        }

        return col;
    }

    /**
     * 주어진 문자열을 구분자로 분리한 후 배열을 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 4. 2.		박준홍			최초 작성
     * </pre>
     *
     * @param string
     *            문자열
     * @param delim
     *            문자열 구분자@param string
     * @param post
     *            문자열 후처리 함수.
     * @return
     *
     * @since 2025. 4. 2.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static Collection<String> splitAsCollection(String string, String delim, Function<String, String> post) {
        Collection<String> col = new ArrayList<String>();

        String[] rtnStrings = split(string, delim, post);

        for (String str : rtnStrings) {
            col.add(str);
        }

        return col;
    }

    /**
     * 주어진 문자열을 구분자로 분리한 후 {@link Set}을 반환합니다.
     * 
     * @param string
     *            문자열
     * @param delim
     *            문자열 구분자
     * @param trim
     *            구분된 문자열 {@link String#trim()} 여부
     * @return
     */
    public static Set<String> splitAsSet(String string, String delim, boolean trim) {
        HashSet<String> set = new HashSet<String>();

        String[] rtnStrings = split(string, delim, trim);

        for (String str : rtnStrings) {
            set.add(str);
        }

        return set;
    }

    /**
     * 주어진 문자열을 구분자로 분리한 후 {@link Set}을 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 4. 2.		박준홍			최초 작성
     * </pre>
     *
     * @param string
     *            문자열
     * @param delim
     *            문자열 구분자
     * @param post
     *            문자열 후처리 함수
     * @return
     *
     * @since 2025. 4. 2.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static Set<String> splitAsSet(String string, String delim, Function<String, String> post) {
        HashSet<String> set = new HashSet<String>();

        String[] rtnStrings = split(string, delim, post);

        for (String str : rtnStrings) {
            set.add(str);
        }

        return set;
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
     * @return
     */
    public static String[] splitWithoutBracket(String string, String delim, boolean trim) {
        return splitWithoutBracket(string, delim, trim, 0);
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
     * @return
     */
    public static String[] splitWithoutBracket(String string, String delim, boolean trim, int limit) {
        String[] rtnStrings = string.split("[" + delim + "]", limit);

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
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 4. 2.		박준홍			최초 작성
     * </pre>
     *
     * @param string
     *            문자열
     * @param delim
     *            문자열 구분자
     * @param post
     *            문자열 후처리 함수
     * @return
     *
     * @since 2025. 4. 2.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static String[] splitWithoutBracket(String string, String delim, Function<String, String> post) {
        return splitWithoutBracket(string, delim, post, 0);
    }

    /**
     * 주어진 문자열을 구분자로 분리한 후 배열을 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 4. 2.		박준홍			최초 작성
     * </pre>
     *
     * @param string
     *            문자열
     * @param delim
     *            문자열 구분자
     * @param post
     *            문자열 후처리 함수
     * @param limit
     *            데이터 최대 개수
     * @return
     *
     * @since 2025. 4. 2.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static String[] splitWithoutBracket(String string, String delim, Function<String, String> post, int limit) {
        String[] rtnStrings = string.split("[" + delim + "]", limit);

        if (post != null) {
            for (int i = 0; i < rtnStrings.length; i++) {
                rtnStrings[i] = post.apply(rtnStrings[i]);
            }
        }

        return rtnStrings;
    }

    /**
     * 문자열이 숫자로 시작하는지 여부를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 6. 21.		박준홍			최초 작성
     * </pre>
     *
     * @param string
     *            문자열
     * @return
     *
     * @since 2021. 6. 21.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static boolean startsWithDigit(String string) {
        if (string == null || string.trim().isEmpty()) {
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
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 4. 1.		박준홍			최초 작성
     * </pre>
     *
     * @param string
     *            문자열
     * @return
     *
     * @since 2022. 4. 1.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static boolean startswithHangul(String string) {
        if (string == null) {
            return false;
        }
        return string.isEmpty() //
                ? false //
                : CharUtils.isKorean(string.charAt(0));
    }

    /**
     * 주어진 문자열이 한글로 시작하는지 여부를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 4. 1.      박준홍         최초 작성
     * </pre>
     *
     * @param string
     *            문자열
     * @param 문자열
     *            trime 여부
     * @return
     *
     * @since 2022. 4. 1.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static boolean startswithHangul(String string, boolean trim) {
        if (string == null) {
            return false;
        } else if (trim) {
            string = string.trim();
        }

        return startswithHangul(string);
    }

    /**
     * 문자열이 주어진 <b><code>prefix</code></b>로 시작하는지 여부를 반환합니다. (대소문자 관계없이)
     * 
     * @param string
     * @param prefix
     * @return
     */
    public static boolean startsWithIgnoreCase(String string, String prefix) {
        boolean startsWith = false;

        if (string != null && prefix != null //
                && string.length() >= prefix.length() // check lengths of two strings.
        ) {
            String headStr = string.substring(0, prefix.length());
            startsWith = headStr.equalsIgnoreCase(prefix);
        }

        return startsWith;
    }

    /**
     * 문자열이 주어진 접두어들 중에 하나로 시작하는지 여부를 반환합니다. (대소문자 관계없이)
     * 
     * @param string
     *            문자열
     * @param prefixes
     *            접두어들
     * @return
     */
    public static boolean startsWithIgnoreCaseOneOf(String string, String... prefixes) {

        for (String prefix : prefixes) {
            if (startsWithIgnoreCase(string, prefix)) {
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
     *            접두어들
     * @return
     */
    public static boolean startsWithOneOf(String string, String... prefixes) {

        for (String prefix : prefixes) {
            if (string.startsWith(prefix)) {
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
     * @return
     */
    public static String substring(String string, int sIndex, int eIndex) {

        if (sIndex > -1 && eIndex > -1 && eIndex > sIndex) {
            String s1 = string.substring(0, sIndex);
            String s2 = string.substring(eIndex, string.length());

            return s1 + s2;
        } else {
            return string;
        }
    }

    /**
     * 문자열에서 {@code boundary} 문자열 다음부터 시작하는 문자열을 반환합니다.
     * 
     * @param string
     *            문자열
     * @param boundary
     *            <i>exclusive</i>
     * @return {@code null} 은 {@code cutter}이 문자열에 포함되어 있지 않은 경우.
     */
    public static String substringAfter(String string, String boundary) {
        int index = string.indexOf(boundary);
        if (index < 0) {
            return string;
        }

        if (index + boundary.length() < string.length()) {
            return string.substring(index + boundary.length());
        } else {
            return "";
        }
    }

    /**
     * 문자열에서 {@code boundary} 문자열 직전까지 문자열을 반환합니다.
     * 
     * @param string
     *            문자열
     * @param boundary
     *            <i>exclusive</i>
     * @return {@code null} 은 {@code cutter}이 문자열에 포함되어 있지 않은 경우.
     */
    public static String substringBefore(String string, String boundary) {
        int index = string.indexOf(boundary);
        if (index < 0) {
            return string;
        }

        return string.substring(0, index);
    }

    /**
     * <pre>
     * e.g. toBeanGetter("money") -> getMoney()
     * </pre>
     * 
     * @param field
     * @return
     */
    public static String toBeanGetter(String field) {
        return "get" + toUpperCase(field, 0) + "()";
    }

    /**
     * <pre>
     * e.g. toBeanGetterName("money") -> getMoney
     * </pre>
     * 
     * @param field
     * @return <BR>
     * @since 2012. 2. 6.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static String toBeanGetterName(String field) {
        return "get" + toUpperCase(field, 0);
    }

    /**
     * <pre>
     * e.g. toBeanSetter("int", "money") -> setMoney(int money)
     * </pre>
     * 
     * @param type
     * @param field
     * @return
     */
    public static String toBeanSetter(String type, String field) {
        return "set" + toUpperCase(field, 0) + "( " + type + " " + field + " )";
    }

    /**
     * <pre>
     * e.g. toBeanSetter("money") -> setMoney
     * </pre>
     * 
     * @param field
     * @return <BR>
     * @since 2012. 2. 6.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static String toBeanSetterName(String field) {
        return "set" + toUpperCase(field, 0);
    }

    /**
     * 
     * @param string
     *            문자열
     * @return <BR>
     * @since 2011. xx. xx.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @deprecated 기존 특정한 프로젝트에서 사용하기 위해서 만든 메소드. 이후 이 클래스가 범용적으로 사용되면서 함께 유지되었던 것인데 더 이상의 지원은 없으며 기능보장을 하지 않음.
     */
    public static String toFieldname(String string) {
        if (string.length() < 2) {
            return string.toLowerCase();
        } else {

            if (string.contains(".")) {
                int dot = indexOf(string, '.', 1);
                return toFieldname(string.substring(0, dot)) + ".get" + toUpperCase(toFieldname(string.substring(dot + 1)), 0) + "()";
            } else if (string.contains("->")) {
                int arrow = string.indexOf("->");
                return toFieldname(string.substring(0, arrow)) + ".get" + toUpperCase(toFieldname(string.substring(arrow + 2)), 0) + "()";
            } else {
                if (isAllUppercase(string.replace("_", ""))) {
                    return string;
                } else {
                    // count of '_'
                    int ulCount = countOf(string, '_');
                    if (ulCount == string.length())
                        return string;
                    else {
                        if (string.contains("_"))
                            return toLowerCase(removeUnderlineAndNextUppercaseOtherLowcase(string), 0);
                        else {
                            if (isAllUppercase(string))
                                return string.toLowerCase();
                            return toLowerCase(string, 0);
                        }
                    }
                }
            }
        }
    };

    /**
     * camelCase 문자열을 kebab_case 문자열로 변경합니다.<br>
     * 예)
     * <ul>
     * <li>camelCase -> camel-case
     * <li>camelCase1 -> camel-case1
     * <li>camel1Case -> camel1-case
     * </ul>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 1. 16.		박준홍			최초 작성
     * </pre>
     *
     * @param camelCase
     * @return
     *
     * @since 2020. 1. 16.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static String toKebabCase(String camelCase) {
        return toKebabCase0(camelCase, false);
    }

    /**
     * camelCase 문자열을 kebab_case 문자열로 변경합니다.<br>
     * 
     * 예)
     * <ul>
     * 숫자 별도처리: false
     * <li>camelCase -> camel-case
     * <li>camelCase1 -> camel-case1
     * <li>camel1Case -> camel1-case
     * </ul>
     * <ul>
     * 숫자 별도처리: true
     * <li>camelCase -> camel-case
     * <li>camelCase1 -> camel-case-1
     * <li>camel1Case -> camel-1-case
     * </ul>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2023. 9. 27.		박준홍			최초 작성
     * </pre>
     *
     * @param camelCase
     *            문자열
     * @param isSplitNum
     *            숫자 별도 처리 여부
     * @return
     *
     * @since 2023. 9. 27.
     * @version 2.0.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    private static String toKebabCase0(String camelCase, boolean isSplitNum) {

        ArrayList<String> strs = new ArrayList<>();

        StringBuffer buf = new StringBuffer();
        for (char c : camelCase.toCharArray()) {
            if (Character.isUpperCase(c) // 문자가 대문자인 경우
                    || (isSplitNum && Character.isDigit(c)) // 숫자별도처리이고 문자가 숫자인 경우
            ) {
                flushBuffer(buf, strs);
            }

            buf.append(Character.toLowerCase(c));
        }

        flushBuffer(buf, strs);

        return StringUtils.concat(strs, "-", false, false, false);
    }

    /**
     * camelCase 문자열을 kebab_case 문자열로 변경합니다.<br>
     * 예)
     * <ul>
     * <li>camelCase -> camel-Case
     * <li>camelCase1 -> camel-Case-1
     * <li>camel1Case -> camel-1-Case
     * </ul>
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2023. 9. 27.		박준홍			최초 작성
     * </pre>
     *
     * @param camelCase
     * @return
     *
     * @since 2023. 9. 27.
     * @version 2.0.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static String toKebabCaseNum(String camelCase) {
        return toKebabCase0(camelCase, true);
    }

    /**
     * 주어진 문자열들을 가지고 LCC(Lower Camel Case)형태로 표현한 하나의 문자열을 반환합니다.<br>
     * 예: TO, lOwER, camel, casE -> toLowerCamelCase
     * 
     * @param strings
     * @return
     * 
     *         <BR>
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * @since 2012. 01. 10.
     */
    public static String toLowerCamelCase(String... strings) {
        if (strings == null || strings.length < 1) {
            return "";
        } else {
            StringBuffer result = new StringBuffer();
            result.append(strings[0].toLowerCase());
            for (int i = 1; i < strings.length; i++) {
                result.append(toUpperCase(strings[i].toLowerCase(), 0));
            }
            return result.toString();
        }
    }

    /**
     * 문자열에서 {@code index}에 해당하는 캐릭터를 소문자로 변경한 후 반환합니다.
     * 
     * @param string
     *            문자열
     * @param index
     * @return
     */
    public static String toLowerCase(String string, int index) {

        if (index > -1 && index < string.length()) {
            char[] cs = string.toCharArray();
            cs[index] = Character.toLowerCase(cs[index]);
            return new String(cs);
        } else {
            System.err.println("index=" + index + ", string: " + string);
            System.err.println(LogUtils.logCallStack());
            return string;
        }
    }

    /**
     * camelCase 문자열을 PascalCase 문자열로 변경합니다.<br>
     * 예) camelCase -> CamelCase <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 1. 16.		박준홍			최초 작성
     * </pre>
     *
     * @param camelCase
     * @return
     *
     * @since 2020. 1. 16.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static String toPascalCase(String camelCase) {
        ArrayList<String> strs = new ArrayList<>();

        StringBuffer buf = new StringBuffer();
        for (char c : camelCase.toCharArray()) {
            if (Character.isUpperCase(c)) {
                flushBuffer(buf, strs);
            }

            buf.append(buf.length() < 1 ? Character.toUpperCase(c) : Character.toLowerCase(c));
        }

        flushBuffer(buf, strs);

        return StringUtils.concat(strs, "", false, false, false);
    }

    /**
     * 문자열을 정규식에 사용할 수 있도록 변환한 후 반환합니다.
     * 
     * @param string
     *            문자열
     * @return
     */
    public static String toRegExString(String string) {
        char[] cs = string.toCharArray();
        StringBuffer sb = new StringBuffer();

        StringBuffer builder = new StringBuffer();
        String cstr = null;
        for (char c : cs) {
            cstr = builder.append(c).toString();
            if (map.containsKey(cstr)) {
                RegExTokenEscape esc = map.get(cstr);
                sb.append(nTimesString(esc.escape, esc.escCount) + c);
            } else {
                sb.append(c);
            }
            builder.setLength(0);
        }

        return sb.toString();
    }

    /**
     * camelCase 문자열을 snake_case 문자열로 변경합니다.<br>
     * 예)
     * <ul>
     * <li>camelCase -> camel_case
     * <li>camel1Case -> camel1_case
     * <li>camelCase1 -> camel_case1
     * </ul>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 1. 16.		박준홍			최초 작성
     * </pre>
     *
     * @param camelCase
     * @return
     *
     * @since 2020. 1. 16.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static String toSnakeCase(String camelCase) {
        return toSnakeCase0(camelCase, false);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2023. 9. 27.		박준홍			최초 작성
     * </pre>
     *
     * @param camelCase
     *            문자열
     * @param isSplitNum
     *            숫자 별도 처리 여부
     * @return
     *
     * @since 2023. 9. 27.
     * @version 2.0.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    private static String toSnakeCase0(String camelCase, boolean isSplitNum) {
        ArrayList<String> strs = new ArrayList<>();

        StringBuffer buf = new StringBuffer();
        for (char c : camelCase.toCharArray()) {
            if (Character.isUpperCase(c) // 문자가 대문자인 경우
                    || (isSplitNum && Character.isDigit(c)) // 숫자별도처리이고 문자가 숫자인 경우
            ) {
                flushBuffer(buf, strs);
            }

            buf.append(Character.toLowerCase(c));
        }

        flushBuffer(buf, strs);

        return StringUtils.concat(strs, "_", false, false, false);
    }

    /**
     * camelCase 문자열을 snake_case 문자열로 변경합니다.<br>
     * 예)
     * <ul>
     * <li>camelCase -> camel_case
     * <li>camel1Case -> camel_1_case
     * <li>camelCase1 -> camel_case_1
     * </ul>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2023. 9. 27.		박준홍			최초 작성
     * </pre>
     *
     * @param camelCase
     * @return
     *
     * @since 2023. 9. 27.
     * @version 2.0.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static String toSnakeCaseNum(String camelCase) {
        return toSnakeCase0(camelCase, true);
    }

    /**
     * 주어진 문자열이 <code>null</code>이거나 빈문자열인 경우<code>defaultValue</code>에 해당하는 값을 반환하고, 그렇지 않은 경우 주어진 문자열을 반환합니다.
     * 
     * @param string
     *            문자열
     * @param defaultValue
     * @return
     * 
     *         <BR>
     * @since 2012. 01. 17.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static String toString(String string, String defaultValue) {
        return isNullOrEmptyString(string) ? defaultValue : string;
    }

    public static String toStrings(Object... objects) {
        switch (objects.length) {
            case 0:
                return "";
            case 1:
                return objects[0].toString();
            default:
                StringBuffer sb = new StringBuffer();
                sb.append(objects[0]);
                for (int i = 1; i < objects.length; i++) {
                    sb.append(objects[i]);
                }
                return sb.toString().trim();
        }
    }

    /**
     * 주어진 문자열들을 가지고 UCC(Upper Camel Case)형태로 표현한 하나의 문자열을 반환합니다.<br>
     * 예: upper, camel, case -> UpperCamelCase
     * 
     * @param strings
     * @return
     * 
     *         <BR>
     * @since 2012. 01. 10.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static String toUpperCamelCase(String... strings) {
        if (strings == null || strings.length < 1) {
            return "";
        } else {
            StringBuffer result = new StringBuffer();
            for (int i = 0; i < strings.length; i++) {
                result.append(toUpperCase(strings[i].toLowerCase(), 0));
            }
            return result.toString();
        }
    }

    /**
     * 문자열에서 {@code index}에 해당하는 캐릭터를 대문자로 변경한 후 반환합니다.
     * 
     * @param string
     *            문자열
     * @param index
     * @return
     */
    public static String toUpperCase(String string, int index) {

        if (index > -1 && index < string.length()) {
            char[] cs = string.toCharArray();
            cs[index] = Character.toUpperCase(cs[index]);
            return new String(cs);
        } else {
            System.err.println("index=" + index + ", string: " + string + " " + LogUtils.logCallStack());
            return string;
        }
    }

    /**
     * VO 클래스 이름으로 변환한 결과를 반환합니다.
     * 
     * @param string
     *            문자열
     * @return
     */
    public static String toVoClassName(String string) {
        return StringUtils.toUpperCase(StringUtils.removeUnderlineAndNextUppercaseOtherLowcase(string.trim()), 0) + "VO";
    }

    /**
     * 하나의 문자로 이루어진 문자열
     * 
     * <BR>
     * 
     * @since 2012. 3. 5.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static final class OneCharacterString {
        private final String string;
        private final String c2str;
        private final char c;

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
         * @author Park Jun-Hong (parkjunhong77@gmail.com)
         */
        public String c2str() {
            return c2str;
        }

        /**
         * @return the c
         * 
         *         <BR>
         * @since 2012. 3. 5.
         * @author Park Jun-Hong (parkjunhong77@gmail.com)
         */
        public final char getC() {
            return c;
        }

        /**
         * @return the string
         * 
         *         <BR>
         * @author Park Jun-Hong (parkjunhong77@gmail.com)
         * @since 2012. 3. 5.
         */
        public final String getString() {
            return string;
        }

        /**
         * @return
         * 
         * @since 2012. 3. 5.
         * @author Park Jun-Hong (parkjunhong77@gmail.com)
         * 
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return "[" + c + "] " + string;
        }
    }

    static class RegExTokenEscape {

        String escape;
        int escCount;
        char token;

        RegExTokenEscape(char propKey, String propValue) {
            token = propKey;

            String[] tmp = propValue.split(toRegExString(","));
            escape = tmp[0];
            escCount = Integer.parseInt(tmp[1]);
        }
    }
}
