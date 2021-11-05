/*
 * Copyright 2021 Park Jun-Hong_(parkjunhong77@gmail.com)
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
 * Date  : 2021. 6. 25. 오후 1:25:39
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.csv;

import java.nio.charset.Charset;

import open.commons.utils.ExceptionUtils;

/**
 * CSV 파일 설정 클래스.
 * 
 * @since 2021. 6. 25.
 * @version 1.8.0
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public class CsvFileConfig extends CsvConfig {

    public static final int DEFAULT_SKIP_LINE_COUNT = 0;
    public static final String DEFAULT_CHARSET_NAME = "UTF-8";

    /** 파일을 읽을 때 최초 무시할 라인 개수 */
    private int skip;
    /** 파일 Charset */
    private Charset charset;
    private String charsetName;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 6. 25.		박준홍			최초 작성
     * </pre>
     *
     *
     * @since 2021. 6. 25.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @see #DEFAULT_SEPARATOR
     * @see #DEFAULT_QUOTE_CHARACTER
     * @see #DEFAULT_ESCAPE_CHARACTER
     * @see #DEFAULT_STRICT_QUOTES
     * @see #DEFAULT_IGNORE_LEADING_WHITESPACE
     * @see #DEFAULT_SKIP_LINE_COUNT
     * @see #DEFAULT_CHARSET_NAME
     */
    public CsvFileConfig() {
        this(DEFAULT_SEPARATOR, DEFAULT_QUOTE_CHARACTER, DEFAULT_ESCAPE_CHARACTER, DEFAULT_STRICT_QUOTES, DEFAULT_IGNORE_LEADING_WHITESPACE, DEFAULT_SKIP_LINE_COUNT,
                Charset.forName(DEFAULT_CHARSET_NAME));
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 6. 25.		박준홍			최초 작성
     * </pre>
     *
     * @param separator
     *            데이터를 구분하는 문자
     *
     * @since 2021. 6. 25.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @see #DEFAULT_QUOTE_CHARACTER
     * @see #DEFAULT_ESCAPE_CHARACTER
     * @see #DEFAULT_STRICT_QUOTES
     * @see #DEFAULT_IGNORE_LEADING_WHITESPACE
     * @see #DEFAULT_SKIP_LINE_COUNT
     * @see #DEFAULT_CHARSET_NAME
     */
    public CsvFileConfig(char separator) {
        this(separator, DEFAULT_QUOTE_CHARACTER, DEFAULT_ESCAPE_CHARACTER, DEFAULT_STRICT_QUOTES, DEFAULT_IGNORE_LEADING_WHITESPACE, DEFAULT_SKIP_LINE_COUNT,
                Charset.forName(DEFAULT_CHARSET_NAME));
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 6. 25.		박준홍			최초 작성
     * </pre>
     *
     * @param separator
     *            데이터를 구분하는 문자
     * @param quotechar
     *            문자열로 묶어주는 문자
     *
     * @since 2021. 6. 25.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @see #DEFAULT_ESCAPE_CHARACTER
     * @see #DEFAULT_STRICT_QUOTES
     * @see #DEFAULT_IGNORE_LEADING_WHITESPACE
     * @see #DEFAULT_SKIP_LINE_COUNT
     * @see #DEFAULT_CHARSET_NAME
     */
    public CsvFileConfig(char separator, char quotechar) {
        this(separator, quotechar, DEFAULT_ESCAPE_CHARACTER, DEFAULT_STRICT_QUOTES, DEFAULT_IGNORE_LEADING_WHITESPACE, DEFAULT_SKIP_LINE_COUNT,
                Charset.forName(DEFAULT_CHARSET_NAME));
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 6. 25.		박준홍			최초 작성
     * </pre>
     *
     * @param separator
     *            데이터를 구분하는 문자
     * @param quotechar
     *            문자열로 묶어주는 문자
     * @param escape
     *            escape 문자
     * @param strictQuotes
     *            무조건 문자열로 묶는 여부
     * @param ignoreLeadingWhiteSpace
     *            데이터 앞쪽의 whitespace 제거 여부
     * @param skip
     *            파일을 읽을 때 최초 무시할 라인 개수
     * @param charset
     *            파일 Charset
     * @since 2021. 6. 25.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public CsvFileConfig(char separator, char quotechar, char escape, boolean strictQuotes, boolean ignoreLeadingWhiteSpace, int skip, Charset charset) {
        super(separator, quotechar, escape, strictQuotes, ignoreLeadingWhiteSpace);
        setCharset(charset);
        setSkip(skip);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 6. 25.     박준홍         최초 작성
     * </pre>
     *
     * @param separator
     *            데이터를 구분하는 문자
     * @param quotechar
     *            문자열로 묶어주는 문자
     * @param escape
     *            escape 문자
     * @param strictQuotes
     *            무조건 문자열로 묶는 여부
     * @param ignoreLeadingWhiteSpace
     *            데이터 앞쪽의 whitespace 제거 여부
     * @param skip
     *            파일을 읽을 때 최초 무시할 라인 개수
     * @param charset
     *            파일 Charset
     * @since 2021. 6. 25.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public CsvFileConfig(char separator, char quotechar, char escape, boolean strictQuotes, boolean ignoreLeadingWhiteSpace, int skip, String charsetName) {
        this(separator, quotechar, escape, strictQuotes, ignoreLeadingWhiteSpace, skip, Charset.forName(charsetName));
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 6. 25.		박준홍			최초 작성
     * </pre>
     *
     * @param separator
     *            데이터를 구분하는 문자
     * @param quotechar
     *            문자열로 묶어주는 문자
     * @param charset
     *            문자열 셋
     *
     * @since 2021. 6. 25.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @see #DEFAULT_ESCAPE_CHARACTER
     * @see #DEFAULT_STRICT_QUOTES
     * @see #DEFAULT_IGNORE_LEADING_WHITESPACE
     * @see #DEFAULT_SKIP_LINE_COUNT
     */
    public CsvFileConfig(char separator, char quotechar, Charset charset) {
        this(separator, quotechar, DEFAULT_ESCAPE_CHARACTER, DEFAULT_STRICT_QUOTES, DEFAULT_IGNORE_LEADING_WHITESPACE, DEFAULT_SKIP_LINE_COUNT, charset);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 6. 25.		박준홍			최초 작성
     * </pre>
     *
     * @param separator
     *            데이터를 구분하는 문자
     * @param quotechar
     *            문자열로 묶어주는 문자
     * @param skip
     *            파일을 읽을 때 최초 무시할 라인 개수
     * @since 2021. 6. 25.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @see #DEFAULT_ESCAPE_CHARACTER
     * @see #DEFAULT_STRICT_QUOTES
     * @see #DEFAULT_IGNORE_LEADING_WHITESPACE
     * @see #DEFAULT_CHARSET_NAME
     */
    public CsvFileConfig(char separator, char quotechar, int skip) {
        this(separator, quotechar, DEFAULT_ESCAPE_CHARACTER, DEFAULT_STRICT_QUOTES, DEFAULT_IGNORE_LEADING_WHITESPACE, skip, Charset.forName(DEFAULT_CHARSET_NAME));
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 6. 25.		박준홍			최초 작성
     * </pre>
     *
     * @param separator
     *            데이터를 구분하는 문자
     * @param quotechar
     *            문자열로 묶어주는 문자
     * @param skip
     *            파일을 읽을 때 최초 무시할 라인 개수
     * @param charsetName
     *            문자열 셋
     * @since 2021. 6. 25.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @see #DEFAULT_ESCAPE_CHARACTER
     * @see #DEFAULT_STRICT_QUOTES
     * @see #DEFAULT_IGNORE_LEADING_WHITESPACE
     */
    public CsvFileConfig(char separator, char quotechar, int skip, Charset charset) {
        this(separator, quotechar, DEFAULT_ESCAPE_CHARACTER, DEFAULT_STRICT_QUOTES, DEFAULT_IGNORE_LEADING_WHITESPACE, skip, charset);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 6. 25.		박준홍			최초 작성
     * </pre>
     *
     * @param separator
     *            데이터를 구분하는 문자
     * @param quotechar
     *            문자열로 묶어주는 문자
     * @param skip
     *            파일을 읽을 때 최초 무시할 라인 개수
     * @param charsetName
     *            문자열 셋
     * @since 2021. 6. 25.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @see #DEFAULT_ESCAPE_CHARACTER
     * @see #DEFAULT_STRICT_QUOTES
     * @see #DEFAULT_IGNORE_LEADING_WHITESPACE
     */
    public CsvFileConfig(char separator, char quotechar, int skip, String charsetName) {
        this(separator, quotechar, DEFAULT_ESCAPE_CHARACTER, DEFAULT_STRICT_QUOTES, DEFAULT_IGNORE_LEADING_WHITESPACE, skip, Charset.forName(charsetName));
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 6. 25.		박준홍			최초 작성
     * </pre>
     *
     * @param separator
     *            데이터를 구분하는 문자
     * @param quotechar
     *            문자열로 묶어주는 문자
     * @param charsetName
     *            문자열 셋
     *
     * @since 2021. 6. 25.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @see #DEFAULT_ESCAPE_CHARACTER
     * @see #DEFAULT_STRICT_QUOTES
     * @see #DEFAULT_IGNORE_LEADING_WHITESPACE
     * @see #DEFAULT_SKIP_LINE_COUNT
     */
    public CsvFileConfig(char separator, char quotechar, String charsetName) {
        this(separator, quotechar, DEFAULT_ESCAPE_CHARACTER, DEFAULT_STRICT_QUOTES, DEFAULT_IGNORE_LEADING_WHITESPACE, DEFAULT_SKIP_LINE_COUNT, Charset.forName(charsetName));
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 6. 25.		박준홍			최초 작성
     * </pre>
     *
     * @param separator
     *            데이터를 구분하는 문자
     * @param charsetName
     *            문자열 셋
     *
     * @since 2021. 6. 25.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @see #DEFAULT_QUOTE_CHARACTER
     * @see #DEFAULT_ESCAPE_CHARACTER
     * @see #DEFAULT_STRICT_QUOTES
     * @see #DEFAULT_IGNORE_LEADING_WHITESPACE
     * @see #DEFAULT_SKIP_LINE_COUNT
     */
    public CsvFileConfig(char separator, Charset charset) {
        this(separator, DEFAULT_QUOTE_CHARACTER, DEFAULT_ESCAPE_CHARACTER, DEFAULT_STRICT_QUOTES, DEFAULT_IGNORE_LEADING_WHITESPACE, DEFAULT_SKIP_LINE_COUNT, charset);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 6. 25.		박준홍			최초 작성
     * </pre>
     *
     * @param separator
     *            데이터를 구분하는 문자
     * @param skip
     *            파일을 읽을 때 최초 무시할 라인 개수
     *
     * @since 2021. 6. 25.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @see #DEFAULT_QUOTE_CHARACTER
     * @see #DEFAULT_ESCAPE_CHARACTER
     * @see #DEFAULT_STRICT_QUOTES
     * @see #DEFAULT_IGNORE_LEADING_WHITESPACE
     * @see #DEFAULT_CHARSET_NAME
     */
    public CsvFileConfig(char separator, int skip) {
        this(separator, DEFAULT_QUOTE_CHARACTER, DEFAULT_ESCAPE_CHARACTER, DEFAULT_STRICT_QUOTES, DEFAULT_IGNORE_LEADING_WHITESPACE, skip, Charset.forName(DEFAULT_CHARSET_NAME));
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 6. 25.		박준홍			최초 작성
     * </pre>
     *
     * @param separator
     *            데이터를 구분하는 문자
     * @param skip
     *            파일을 읽을 때 최초 무시할 라인 개수
     * @param charsetName
     *            문자열 셋
     *
     * @since 2021. 6. 25.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @see #DEFAULT_QUOTE_CHARACTER
     * @see #DEFAULT_ESCAPE_CHARACTER
     * @see #DEFAULT_STRICT_QUOTES
     * @see #DEFAULT_IGNORE_LEADING_WHITESPACE
     */
    public CsvFileConfig(char separator, int skip, Charset charset) {
        this(separator, DEFAULT_QUOTE_CHARACTER, DEFAULT_ESCAPE_CHARACTER, DEFAULT_STRICT_QUOTES, DEFAULT_IGNORE_LEADING_WHITESPACE, skip, charset);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 6. 25.		박준홍			최초 작성
     * </pre>
     *
     * @param separator
     *            데이터를 구분하는 문자
     * @param skip
     *            파일을 읽을 때 최초 무시할 라인 개수
     * @param charsetName
     *            문자열 셋
     *
     * @since 2021. 6. 25.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @see #DEFAULT_QUOTE_CHARACTER
     * @see #DEFAULT_ESCAPE_CHARACTER
     * @see #DEFAULT_STRICT_QUOTES
     * @see #DEFAULT_IGNORE_LEADING_WHITESPACE
     */
    public CsvFileConfig(char separator, int skip, String charsetName) {
        this(separator, DEFAULT_QUOTE_CHARACTER, DEFAULT_ESCAPE_CHARACTER, DEFAULT_STRICT_QUOTES, DEFAULT_IGNORE_LEADING_WHITESPACE, skip, Charset.forName(charsetName));
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 6. 25.		박준홍			최초 작성
     * </pre>
     *
     * @param separator
     *            데이터를 구분하는 문자
     * @param charsetName
     *            문자열 셋
     *
     * @since 2021. 6. 25.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @see #DEFAULT_QUOTE_CHARACTER
     * @see #DEFAULT_ESCAPE_CHARACTER
     * @see #DEFAULT_STRICT_QUOTES
     * @see #DEFAULT_IGNORE_LEADING_WHITESPACE
     * @see #DEFAULT_SKIP_LINE_COUNT
     */
    public CsvFileConfig(char separator, String charsetName) {
        this(separator, DEFAULT_QUOTE_CHARACTER, DEFAULT_ESCAPE_CHARACTER, DEFAULT_STRICT_QUOTES, DEFAULT_IGNORE_LEADING_WHITESPACE, DEFAULT_SKIP_LINE_COUNT,
                Charset.forName(charsetName));
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 6. 25.		박준홍			최초 작성
     * </pre>
     * 
     * @return the charset
     *
     * @since 2021. 6. 25.
     * @version _._._
     * @see #charset
     */

    public Charset getCharset() {
        return charset;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 6. 25.		박준홍			최초 작성
     * </pre>
     * 
     * @return the charsetName
     *
     * @since 2021. 6. 25.
     * @version _._._
     * @see #charsetName
     */

    public String getCharsetName() {
        return charsetName;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 6. 25.		박준홍			최초 작성
     * </pre>
     * 
     * @return the skip
     *
     * @since 2021. 6. 25.
     * @version 1.8.0
     * @see #skip
     */

    public int getSkip() {
        return skip;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 6. 25.		박준홍			최초 작성
     * </pre>
     *
     * @param charset
     *            the charset to set
     *
     * @since 2021. 6. 25.
     * @version _._._
     * @see #charset
     */
    public void setCharset(Charset charset) {
        this.charset = charset;
        this.charsetName = this.charset.displayName();
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 6. 25.		박준홍			최초 작성
     * </pre>
     *
     * @param charsetName
     *            the charsetName to set
     *
     * @since 2021. 6. 25.
     * @version _._._
     * @see #charsetName
     */
    public void setCharsetName(String charsetName) {
        this.charsetName = charsetName;
        this.charset = Charset.forName(charsetName);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 6. 25.		박준홍			최초 작성
     * </pre>
     *
     * @param skip
     *            the skip to set
     *
     * @since 2021. 6. 25.
     * @version 1.8.0
     * @see #skip
     */
    public void setSkip(int skip) {
        if (skip < 0) {
            throw ExceptionUtils.newException(IllegalArgumentException.class, "무시하려는 줄 수는 0 이거나 양의 정수이어야 합니다. 입력=%,d", skip);
        }
        this.skip = skip;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 6. 25.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2021. 6. 25.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("CsvFileConfig [separator=");
        builder.append(separator);
        builder.append(", quotechar=");
        builder.append(quotechar);
        builder.append(", escape=");
        builder.append(escape);
        builder.append(", strictQuotes=");
        builder.append(strictQuotes);
        builder.append(", ignoreLeadingWhiteSpace=");
        builder.append(ignoreLeadingWhiteSpace);
        builder.append(", skip=");
        builder.append(skip);
        builder.append(", charset=");
        builder.append(charset);
        builder.append(", charsetName=");
        builder.append(charsetName);
        builder.append("]");
        return builder.toString();
    }

}
