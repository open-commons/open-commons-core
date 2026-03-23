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
import java.util.Objects;

import open.commons.core.utils.CsvUtils;

/**
 * 
 * @since 2022. 3. 17.
 * @version 1.8.0
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 * 
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
     * 
     * 
     * @see #DEFAULT_SEPARATOR
     * @see #DEFAULT_QUOTE_CHARACTER
     * @see #DEFAULT_ESCAPE_CHARACTER
     * @see #DEFAULT_CHARSET_NAME
     */
    public CsvWriteConfig() {
        this(DEFAULT_SEPARATOR, DEFAULT_QUOTE_CHARACTER, DEFAULT_ESCAPE_CHARACTER, CsvUtils.charset(DEFAULT_CHARSET_NAME));
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
     * 
     * 
     * @see #DEFAULT_QUOTE_CHARACTER
     * @see #DEFAULT_ESCAPE_CHARACTER
     * @see #DEFAULT_CHARSET_NAME
     */
    public CsvWriteConfig(char separator) {
        this(separator, DEFAULT_QUOTE_CHARACTER, DEFAULT_ESCAPE_CHARACTER, CsvUtils.charset(DEFAULT_CHARSET_NAME));
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
     * 
     * 
     * @see #DEFAULT_ESCAPE_CHARACTER
     * @see #DEFAULT_CHARSET_NAME
     */
    public CsvWriteConfig(char separator, char quotechar) {
        this(separator, quotechar, DEFAULT_ESCAPE_CHARACTER, CsvUtils.charset(DEFAULT_CHARSET_NAME));
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
     * 
     * 
     * @see #DEFAULT_CHARSET_NAME
     */
    public CsvWriteConfig(char separator, char quotechar, char escape) {
        this(separator, quotechar, escape, CsvUtils.charset(DEFAULT_CHARSET_NAME));
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
     * 
     * @throws NullPointerException
     *             파라미터({@code charset})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * 
     */
    public CsvWriteConfig(char separator, char quotechar, char escape, Charset charset) {
        this(separator, quotechar, escape, DEFAULT_LINE_END, charset);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2026. 2. 26.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param separator
     *            데이터를 구분하는 문자
     * @param quotechar
     *            문자열로 묶어주는 문자
     * @param escape
     *            escape 문자
     * @param lineEnd
     *            기본 줄바꿈 문자열
     * @param charset
     * 
     * @throws NullPointerException
     *             파라미터({@code lineEnd, charset})가 {@code null}인 경우 발생.
     *
     * @since 2026. 2. 26.
     * @version 3.0.0
     * 
     */
    public CsvWriteConfig(char separator, char quotechar, char escape, String lineEnd, Charset charset) {
        super(separator, quotechar, escape, lineEnd);

        Objects.requireNonNull(charset);

        this.charset = charset;
        this.charsetName = Objects.requireNonNull(charset.displayName());
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
     * @throws NullPointerException
     *             파라미터({@code charset})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * 
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
     * @throws NullPointerException
     *             파라미터({@code charset})가 {@code null}인 경우 발생.
     * 
     * @since 2022. 3. 17.
     * @version 1.8.0
     * 
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
     * 
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
     * 
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
     * @throws NullPointerException
     *             파라미터({@code charset})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * 
     *
     * @see #charset
     */
    public void setCharset(Charset charset) {
        Objects.requireNonNull(charset);

        this.charset = charset;
        this.charsetName = Objects.requireNonNull(charset.displayName());
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
     * @throws NullPointerException
     *             파라미터({@code charsetName})가 {@code null}인 경우 발생.
     * 
     * @since 2022. 3. 17.
     * @version 1.8.0
     * 
     *
     * @see #charsetName
     */
    public void setCharsetName(String charsetName) {
        Objects.requireNonNull(charsetName);

        this.charsetName = charsetName;
        this.charset = CsvUtils.charset(charsetName);
    }

    /**
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * 
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

        return Objects.requireNonNull(
                // [PATCH[ JDK 표준 API의 JSpecify 미지원 우회용 임시 널 체크.
                // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 requireNonNull 래핑 제거.
                builder.toString() //
        );
    }
}
