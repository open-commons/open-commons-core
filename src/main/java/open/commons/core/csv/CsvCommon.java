/*
 * Copyright 2022 Park Jun-Hong_(parkjunhong77@gmail.com)
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
 * Date  : 2022. 3. 17. 오후 6:13:43
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package open.commons.core.csv;

/**
 * CSV 파일 읽기/쓰기 공통 설정
 * 
 * @since 2022. 3. 17.
 * @version 1.8.0
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
public abstract class CsvCommon {

    public static final char DEFAULT_SEPARATOR = ',';
    public static final char DEFAULT_QUOTE_CHARACTER = '"';
    public static final char DEFAULT_ESCAPE_CHARACTER = '\\';

    /** 데이터를 구분하는 문자 */
    protected char separator;
    /** 문자열로 묶어주는 문자 */
    protected char quotechar;
    /** escape 문자 */
    protected char escape;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 3. 17.		박준홍			최초 작성
     * </pre>
     *
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see #DEFAULT_SEPARATOR
     * @see #DEFAULT_QUOTE_CHARACTER
     * @see #DEFAULT_ESCAPE_CHARACTER
     */
    public CsvCommon() {
        this(DEFAULT_SEPARATOR, DEFAULT_QUOTE_CHARACTER, DEFAULT_ESCAPE_CHARACTER);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 3. 17.		박준홍			최초 작성
     * </pre>
     *
     * @param separator
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see #DEFAULT_QUOTE_CHARACTER
     * @see #DEFAULT_ESCAPE_CHARACTER
     */
    public CsvCommon(char separator) {
        this(separator, DEFAULT_QUOTE_CHARACTER, DEFAULT_ESCAPE_CHARACTER);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 3. 17.		박준홍			최초 작성
     * </pre>
     *
     * @param separator
     * @param quotechar
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see #DEFAULT_ESCAPE_CHARACTER
     */
    public CsvCommon(char separator, char quotechar) {
        this(separator, quotechar, DEFAULT_ESCAPE_CHARACTER);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 3. 17.		박준홍			최초 작성
     * </pre>
     *
     * @param separator
     * @param quotechar
     * @param escape
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public CsvCommon(char separator, char quotechar, char escape) {
        this.separator = separator;
        this.quotechar = quotechar;
        this.escape = escape;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 3. 17.		박준홍			최초 작성
     * </pre>
     * 
     * @return the escape
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     *
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
     * 2022. 3. 17.		박준홍			최초 작성
     * </pre>
     * 
     * @return the quotechar
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     *
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
     * 2022. 3. 17.		박준홍			최초 작성
     * </pre>
     * 
     * @return the separator
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     *
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
     * 2022. 3. 17.		박준홍			최초 작성
     * </pre>
     *
     * @param escape
     *            the escape to set
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     *
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
     * 2022. 3. 17.		박준홍			최초 작성
     * </pre>
     *
     * @param quotechar
     *            the quotechar to set
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     *
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
     * 2022. 3. 17.		박준홍			최초 작성
     * </pre>
     *
     * @param separator
     *            the separator to set
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     *
     * @see #separator
     */
    public void setSeparator(char separator) {
        this.separator = separator;
    }

    /**
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("CsvCommon [separator=");
        builder.append(separator);
        builder.append(", quotechar=");
        builder.append(quotechar);
        builder.append(", escape=");
        builder.append(escape);
        builder.append("]");
        return builder.toString();
    }

}
