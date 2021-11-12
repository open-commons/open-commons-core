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
 * Date  : 2021. 6. 25. 오후 12:55:44
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.csv;

/**
 * CSV 데이터 설정 클래스.
 * 
 * @since 2021. 6. 25.
 * @version 1.8.0
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public class CsvConfig {

    public static final char DEFAULT_SEPARATOR = ',';
    public static final char DEFAULT_QUOTE_CHARACTER = '"';
    public static final char DEFAULT_ESCAPE_CHARACTER = '\\';
    public static final boolean DEFAULT_STRICT_QUOTES = false;
    public static final boolean DEFAULT_IGNORE_LEADING_WHITESPACE = true;

    /** 데이터를 구분하는 문자 */
    protected char separator;
    /** 문자열로 묶어주는 문자 */
    protected char quotechar;
    /** escape 문자 */
    protected char escape;
    /** 무조건 문자열로 묶는 여부 */
    protected boolean strictQuotes;
    /** 데이터 앞쪽의 whitespace 제거 여부 */
    protected boolean ignoreLeadingWhiteSpace;

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
     */
    public CsvConfig() {
        this(DEFAULT_SEPARATOR, DEFAULT_QUOTE_CHARACTER, DEFAULT_ESCAPE_CHARACTER, DEFAULT_STRICT_QUOTES, DEFAULT_IGNORE_LEADING_WHITESPACE);
    }

    /**
     * 
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
     *            데이터 구분 문자
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
    public CsvConfig(char separator) {
        this(separator, DEFAULT_QUOTE_CHARACTER, DEFAULT_ESCAPE_CHARACTER, DEFAULT_STRICT_QUOTES, DEFAULT_IGNORE_LEADING_WHITESPACE);
    }

    /**
     * 
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
     *            데이터 구분 문자
     * @param quotechar
     *            문자열로 묶는 문자
     *
     * @since 2021. 6. 25.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @see #DEFAULT_ESCAPE_CHARACTER
     * @see #DEFAULT_STRICT_QUOTES
     * @see #DEFAULT_IGNORE_LEADING_WHITESPACE
     */
    public CsvConfig(char separator, char quotechar) {
        this(separator, quotechar, DEFAULT_ESCAPE_CHARACTER, DEFAULT_STRICT_QUOTES, DEFAULT_IGNORE_LEADING_WHITESPACE);
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
     * @since 2021. 6. 25.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public CsvConfig(char separator, char quotechar, char escape) {
        this(separator, quotechar, escape, DEFAULT_STRICT_QUOTES, DEFAULT_IGNORE_LEADING_WHITESPACE);
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
     * @since 2021. 6. 25.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public CsvConfig(char separator, char quotechar, char escape, boolean strictQuotes) {
        this(separator, quotechar, escape, strictQuotes, DEFAULT_IGNORE_LEADING_WHITESPACE);
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
     * @since 2021. 6. 25.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public CsvConfig(char separator, char quotechar, char escape, boolean strictQuotes, boolean ignoreLeadingWhiteSpace) {
        this.separator = separator;
        this.quotechar = quotechar;
        this.escape = escape;
        this.strictQuotes = strictQuotes;
        this.ignoreLeadingWhiteSpace = ignoreLeadingWhiteSpace;
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
     * @return the escape
     *
     * @since 2021. 6. 25.
     * @version 1.8.0
     * @see #escape
     */

    public char getEscape() {
        return escape;
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
     * @return the quotechar
     *
     * @since 2021. 6. 25.
     * @version 1.8.0
     * @see #quotechar
     */

    public char getQuotechar() {
        return quotechar;
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
     * @return the separator
     *
     * @since 2021. 6. 25.
     * @version 1.8.0
     * @see #separator
     */

    public char getSeparator() {
        return separator;
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
     * @return the ignoreLeadingWhiteSpace
     *
     * @since 2021. 6. 25.
     * @version 1.8.0
     * @see #ignoreLeadingWhiteSpace
     */

    public boolean isIgnoreLeadingWhiteSpace() {
        return ignoreLeadingWhiteSpace;
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
     * @return the strictQuotes
     *
     * @since 2021. 6. 25.
     * @version 1.8.0
     * @see #strictQuotes
     */

    public boolean isStrictQuotes() {
        return strictQuotes;
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
     * @param escape
     *            the escape to set
     *
     * @since 2021. 6. 25.
     * @version 1.8.0
     * @see #escape
     */
    public void setEscape(char escape) {
        this.escape = escape;
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
     * @param ignoreLeadingWhiteSpace
     *            the ignoreLeadingWhiteSpace to set
     *
     * @since 2021. 6. 25.
     * @version 1.8.0
     * @see #ignoreLeadingWhiteSpace
     */
    public void setIgnoreLeadingWhiteSpace(boolean ignoreLeadingWhiteSpace) {
        this.ignoreLeadingWhiteSpace = ignoreLeadingWhiteSpace;
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
     * @param quotechar
     *            the quotechar to set
     *
     * @since 2021. 6. 25.
     * @version 1.8.0
     * @see #quotechar
     */
    public void setQuotechar(char quotechar) {
        this.quotechar = quotechar;
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
     *            the separator to set
     *
     * @since 2021. 6. 25.
     * @version 1.8.0
     * @see #separator
     */
    public void setSeparator(char separator) {
        this.separator = separator;
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
     * @param strictQuotes
     *            the strictQuotes to set
     *
     * @since 2021. 6. 25.
     * @version 1.8.0
     * @see #strictQuotes
     */
    public void setStrictQuotes(boolean strictQuotes) {
        this.strictQuotes = strictQuotes;
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
        builder.append("CsvConfig [separator=");
        builder.append(separator);
        builder.append(", quotechar=");
        builder.append(quotechar);
        builder.append(", escape=");
        builder.append(escape);
        builder.append(", strictQuotes=");
        builder.append(strictQuotes);
        builder.append(", ignoreLeadingWhiteSpace=");
        builder.append(ignoreLeadingWhiteSpace);
        builder.append("]");
        return builder.toString();
    }

}
