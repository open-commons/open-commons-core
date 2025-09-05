/*
 * Copyright 2022 Park Jun-Hong (parkjunhong77@gmail.com)
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
 * Date  : 2022. 3. 17. 오후 5:21:20
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package open.commons.core.csv;

import java.nio.charset.Charset;

/**
 * 
 * @since 2022. 3. 17.
 * @version 1.8.0
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
public class CsvWriteConfig extends CsvCommon {

    public static final String DEFAULT_CHARSET_NAME = "UTF-8";

    /** 파일 Charset */
    private Charset charset;
    private String charsetName;

    /**
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 3. 17.		parkjunohng77@gmail.com			최초 작성
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
     * @see #DEFAULT_CHARSET_NAME
     */
    public CsvWriteConfig() {
        this(DEFAULT_SEPARATOR, DEFAULT_QUOTE_CHARACTER, DEFAULT_ESCAPE_CHARACTER, Charset.forName(DEFAULT_CHARSET_NAME));
    }

    /**
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 17.     parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param separator
     *            데이터를 구분하는 문자
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see #DEFAULT_QUOTE_CHARACTER
     * @see #DEFAULT_ESCAPE_CHARACTER
     * @see #DEFAULT_CHARSET_NAME
     */
    public CsvWriteConfig(char separator) {
        this(separator, DEFAULT_QUOTE_CHARACTER, DEFAULT_ESCAPE_CHARACTER, Charset.forName(DEFAULT_CHARSET_NAME));
    }

    /**
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 17.     parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param separator
     *            데이터를 구분하는 문자
     * @param quotechar
     *            문자열로 묶어주는 문자
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see #DEFAULT_ESCAPE_CHARACTER
     * @see #DEFAULT_CHARSET_NAME
     */
    public CsvWriteConfig(char separator, char quotechar) {
        this(separator, quotechar, DEFAULT_ESCAPE_CHARACTER, Charset.forName(DEFAULT_CHARSET_NAME));
    }

    /**
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 17.     parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param separator
     *            데이터를 구분하는 문자
     * @param quotechar
     *            문자열로 묶어주는 문자
     * @param escape
     *            escape 문자
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see #DEFAULT_CHARSET_NAME
     */
    public CsvWriteConfig(char separator, char quotechar, char escape) {
        this(separator, quotechar, escape, Charset.forName(DEFAULT_CHARSET_NAME));
    }

    /**
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 3. 17.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     *
     * @param separator
     *            데이터를 구분하는 문자
     * @param quotechar
     *            문자열로 묶어주는 문자
     * @param escape
     *            escape 문자
     * @param charset
     *            파일 Charset
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public CsvWriteConfig(char separator, char quotechar, char escape, Charset charset) {
        super(separator, quotechar, escape);
        setCharset(charset);
    }

    /**
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 17.     parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param separator
     *            데이터를 구분하는 문자
     * @param quotechar
     *            문자열로 묶어주는 문자
     * @param charset
     *            파일 Charset
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see #DEFAULT_ESCAPE_CHARACTER
     */
    public CsvWriteConfig(char separator, char quotechar, Charset charset) {
        this(separator, quotechar, DEFAULT_ESCAPE_CHARACTER, charset);
    }

    /**
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 17.     parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param separator
     *            데이터를 구분하는 문자
     * @param charset
     *            파일 Charset
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see #DEFAULT_QUOTE_CHARACTER
     * @see #DEFAULT_ESCAPE_CHARACTER
     */
    public CsvWriteConfig(char separator, Charset charset) {
        this(separator, DEFAULT_QUOTE_CHARACTER, DEFAULT_ESCAPE_CHARACTER, charset);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 3. 17.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     * 
     * @return the charset
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     *
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
     * 2022. 3. 17.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     * 
     * @return the charsetName
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     *
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
     * 2022. 3. 17.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     *
     * @param charset
     *            the charset to set
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     *
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
     * 2022. 3. 17.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     *
     * @param charsetName
     *            the charsetName to set
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     *
     * @see #charsetName
     */
    public void setCharsetName(String charsetName) {
        this.charsetName = charsetName;
        this.charset = Charset.forName(charsetName);
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
        builder.append("CsvWriteConfig [separator=");
        builder.append(separator);
        builder.append(", quotechar=");
        builder.append(quotechar);
        builder.append(", escape=");
        builder.append(escape);
        builder.append(", charset=");
        builder.append(charset);
        builder.append(", charsetName=");
        builder.append(charsetName);
        builder.append("]");
        return builder.toString();
    }
}
