/*
 * Copyright 2021 Park Jun-Hong_(parkjunhong77/google/com)
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
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package open.commons.csv;

import open.commons.utils.ExceptionUtils;

/**
 * CSV 파일 설정 클래스.
 * 
 * @since 2021. 6. 25.
 * @version 1.8.0
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
public class CsvFileConfig extends CsvConfig {

    public static final int DEFAULT_SKIP_LINE_COUNT = 0;

    /** 파일을 읽을 때 최초 무시할 라인 개수 */
    private int skip;

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
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public CsvFileConfig() {
        this(DEFAULT_SEPARATOR, DEFAULT_QUOTE_CHARACTER, DEFAULT_ESCAPE_CHARACTER, DEFAULT_STRICT_QUOTES, DEFAULT_IGNORE_LEADING_WHITESPACE, DEFAULT_SKIP_LINE_COUNT);
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
     *
     * @since 2021. 6. 25.
     * @version 1.8.0
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public CsvFileConfig(char separator) {
        this(separator, DEFAULT_QUOTE_CHARACTER, DEFAULT_ESCAPE_CHARACTER, DEFAULT_STRICT_QUOTES, DEFAULT_IGNORE_LEADING_WHITESPACE, DEFAULT_SKIP_LINE_COUNT);
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
     * @param quotechar
     *
     * @since 2021. 6. 25.
     * @version 1.8.0
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public CsvFileConfig(char separator, char quotechar) {
        this(separator, quotechar, DEFAULT_ESCAPE_CHARACTER, DEFAULT_STRICT_QUOTES, DEFAULT_IGNORE_LEADING_WHITESPACE, DEFAULT_SKIP_LINE_COUNT);
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
     * @param quotechar
     * @param escape
     * @param strictQuotes
     * @param ignoreLeadingWhiteSpace
     * @param skip
     *
     * @since 2021. 6. 25.
     * @version 1.8.0
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public CsvFileConfig(char separator, char quotechar, char escape, boolean strictQuotes, boolean ignoreLeadingWhiteSpace, int skip) {
        super(separator, quotechar, escape, strictQuotes, ignoreLeadingWhiteSpace);
        setSkip(skip);

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
     * @param quotechar
     * @param skip
     *
     * @since 2021. 6. 25.
     * @version 1.8.0
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public CsvFileConfig(char separator, char quotechar, int skip) {
        this(separator, quotechar, DEFAULT_ESCAPE_CHARACTER, DEFAULT_STRICT_QUOTES, DEFAULT_IGNORE_LEADING_WHITESPACE, skip);
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
     * @param skip
     *
     * @since 2021. 6. 25.
     * @version 1.8.0
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public CsvFileConfig(char separator, int skip) {
        this(separator, DEFAULT_QUOTE_CHARACTER, DEFAULT_ESCAPE_CHARACTER, DEFAULT_STRICT_QUOTES, DEFAULT_IGNORE_LEADING_WHITESPACE, skip);
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

}
