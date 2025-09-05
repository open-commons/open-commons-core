/*
 * Copyright 2021 Park Jun-Hong (parkjunhong77@gmail.com)
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
 * Date  : 2021. 6. 18. 오후 5:15:58
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.core.csv;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

import open.commons.core.database.annotation.AQueryIndex;
import open.commons.core.lang.Char;
import open.commons.core.utils.ExceptionUtils;

/**
 * 
 * @since 2021. 6. 18.
 * @version 1.8.0
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public abstract class AbstractCsvData {

    /** 데이터 구분자 */
    protected final char delimiter;
    /** Quotation 문자 */
    protected final char quote;
    /** Escaping 문자 */
    protected final char escape;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 6. 18.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     *
     *
     * @since 2021. 6. 18.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public AbstractCsvData() {
        this(CsvConfig.DEFAULT_SEPARATOR, CsvConfig.DEFAULT_QUOTE_CHARACTER, CsvConfig.DEFAULT_ESCAPE_CHARACTER);
    }

    /**
     *
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 6. 18.     parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param separator
     *            데이터 구분자
     *
     * @since 2021. 6. 18.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public AbstractCsvData(char separator) {
        this(separator, CsvConfig.DEFAULT_QUOTE_CHARACTER, CsvConfig.DEFAULT_ESCAPE_CHARACTER);
    }

    /**
     *
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 6. 18.     parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param separator
     *            데이터 구분자
     * @param quote
     *            Quotaion 문자
     * @param escape
     *            Escaping 문자
     *
     * @since 2021. 6. 18.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public AbstractCsvData(char separator, char quote, char escape) {
        this.delimiter = separator;
        this.quote = quote;
        this.escape = escape;
    }

    /**
     * 객체의 데이터를 CSV 문자열로 변환하여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 6. 18.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     *
     * @param delim
     *            CSV 데이터 구분자
     * @param quote
     *            CSV 데이터 wrapping character
     * @param escape
     *            Escape character
     * @param nullValue
     *            'null' 데이터 표기 문자열
     * @return
     *
     * @since 2021. 6. 18.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public final String csv(char delim, char quote, char escape, String nullValue) {
        List<Supplier<String>> providers = getValues();

        final List<String> csvStr = new ArrayList<>();
        handleValue(providers, delim, quote, escape, nullValue, (str, idx) -> {
            csvStr.add(str);
        });

        return String.join(new Char(delim), csvStr);
    }

    /**
     * 객체가 포함한 데이터를 CSV 파일 문자열 형태로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 11. 4.     parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param delim
     *            CSV 데이터 구분자
     * @param nullValue
     *            <code>null</code>인 경우 데이터
     * @return
     *
     * @since 2020. 11. 4.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @see AQueryIndex
     * @see #csv(char, char, char, String)
     */
    public final String csv(char delim, String nullValue) {
        return csv(delim, this.quote, this.escape, nullValue);
    }

    /**
     * 
     *
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 6. 18.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     *
     * @param nullValue
     * @return
     *
     * @since 2021. 6. 18.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @see #csv(char, char, char, String)
     */
    public final String csv(String nullValue) {
        return csv(this.delimiter, this.quote, this.escape, nullValue);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 6. 18.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     *
     * @param delim
     * @param quote
     * @param escape
     * @param nullValue
     * @return
     *
     * @since 2021. 6. 18.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public String[] csvArray(char delim, char quote, char escape, String nullValue) {
        List<Supplier<String>> providers = getValues();

        final String[] csvArr = new String[providers.size()];
        handleValue(providers, delim, quote, escape, nullValue, (str, idx) -> {
            csvArr[idx] = str;
        });

        return csvArr;
    }

    /**
     * 객체가 포함한 데이터를 CSV 파일 문자열 형태로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 11. 4.     parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param delim
     *            CSV 데이터 구분자
     * @param nullValue
     *            <code>null</code>인 경우 데이터
     * @return
     *
     * @since 2020. 11. 4.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @see AQueryIndex
     * @see #csv(char, char, char, String)
     */
    public final String[] csvArray(char delim, String nullValue) {
        return csvArray(delim, this.quote, this.escape, nullValue);
    }

    /**
     * 
     *
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 6. 18.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     *
     * @param nullValue
     * @return
     *
     * @since 2021. 6. 18.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @see #csv(char, char, char, String)
     */
    public final String[] csvArray(String nullValue) {
        return csvArray(this.delimiter, this.quote, this.escape, nullValue);
    }

    /**
     *
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 6. 18.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     * 
     * @return the delimiter
     *
     * @since 2021. 6. 18.
     * 
     * @see #delimiter
     */
    public char getDelimiter() {
        return delimiter;
    }

    /**
     *
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 6. 18.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     * 
     * @return the escape
     *
     * @since 2021. 6. 18.
     * 
     * @see #escape
     */
    public char getEscape() {
        return escape;
    }

    /**
     * CSV 데이터 헤더 제공자를 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 6. 18.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2021. 6. 18.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public abstract List<String> getHeaders();

    /**
     *
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 6. 18.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     * 
     * @return the quote
     *
     * @since 2021. 6. 18.
     * 
     * @see #quote
     */
    public char getQuote() {
        return quote;
    }

    /**
     * CSV 데이터 제공자를 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 6. 18.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2021. 6. 18.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public abstract List<Supplier<String>> getValues();

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 6. 18.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     *
     * @param orderedValues
     *            정렬된 데이터
     * @param delimiter
     *            데이터 구분자
     * @param quote
     *            Quotaion 문자
     * @param escape
     *            Escaping 문자
     * @param nullValue
     *            'null' 대체 문자열
     * @param consumer
     *            데이터 처리 함수
     * 
     *            <ul>
     *            <li>1st: 데이터 문자열
     *            <li>2nd: 정렬 순서 (zero-based)
     *            </ul>
     *
     * @since 2021. 6. 18.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    private <T extends Supplier<String>> void handleValue(List<T> orderedValues, char delim, char quote, char escape, String nullValue, BiConsumer<String, Integer> consumer) {
        int index = 0;
        String str = null;
        for (Supplier<String> value : orderedValues) {
            consumer.accept(processString((str = value.get()) != null ? str : nullValue, delim, quote, escape), index);
            index++;
        }
    }

    /**
     * 특수한 경우의 문자열을 처리합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 6. 18.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     *
     * @param str
     * @param delim
     *            데이터 구분자
     * @param quote
     *            CSV 데이터 wrapping character
     * @param escape
     *            Escape character
     * @return
     *
     * @since 2021. 6. 18.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    private String processString(String str, char delim, char quote, char escape) {
        if (str == null) {
            return null;
        }

        if (str.indexOf(delim) == -1 && str.indexOf(quote) == -1 && str.indexOf(escape) == -1) {
            return str;
        }

        StringBuilder buf = new StringBuilder();
        if (str.indexOf(delim) != -1) {
            buf.append(quote);
        }

        for (char c : str.toCharArray()) {
            if (c == quote || c == escape) {
                buf.append(escape);
            }
            buf.append(c);
        }

        if (str.indexOf(delim) != -1) {
            buf.append(quote);
        }

        return buf.toString();
    }

    /**
     * 컬럼명을 순서대로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 6. 18.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     *
     * @param <T>
     * @param target
     * @return
     *
     * @since 2021. 6. 18.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <T extends AbstractCsvData> List<String> columns(Class<T> target) {
        try {
            return columns(target.newInstance());
        } catch (NullPointerException e) {
            throw ExceptionUtils.newException(IllegalArgumentException.class, "올바르지 않은 데이터 입니다. target=%s", target);
        } catch (InstantiationException | IllegalAccessException e) {
            throw ExceptionUtils.newException(IllegalArgumentException.class, "대상 클래스는 반드시 기본생성자가 있어야 합니다. target=%s", target);
        }
    }

    /**
     * 컬럼명을 순서대로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 11. 4.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     * 
     * @param target
     * 
     * @return
     *
     * @since 2020. 11. 4.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @see AQueryIndex
     */
    public static <T extends AbstractCsvData> List<String> columns(T target) {
        if (target == null) {
            throw ExceptionUtils.newException(IllegalArgumentException.class, "올바르지 않은 데이터 입니다. target=%s", target);
        }
        return target.getHeaders();
    }
}