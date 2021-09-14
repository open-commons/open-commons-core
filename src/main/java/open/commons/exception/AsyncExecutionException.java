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
 * Date  : 2021. 9. 14. 오후 1:26:50
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package open.commons.exception;

/**
 * 비동기 작업 실행 도중 발생하는 예외 클래스.
 * 
 * @since 2021. 9. 14.
 * @version 1.8.0
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
public class AsyncExecutionException extends RuntimeException {

    private static final long serialVersionUID = 7653698955226516800L;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 9. 14.		박준홍			최초 작성
     * </pre>
     *
     *
     * @since 2021. 9. 14.
     * @version 1.8.0
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public AsyncExecutionException() {
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 9. 14.		박준홍			최초 작성
     * </pre>
     *
     * @param message
     *
     * @since 2021. 9. 14.
     * @version 1.8.0
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public AsyncExecutionException(String message) {
        super(message);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 9. 14.		박준홍			최초 작성
     * </pre>
     *
     * @param message
     * @param cause
     *
     * @since 2021. 9. 14.
     * @version 1.8.0
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public AsyncExecutionException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 9. 14.		박준홍			최초 작성
     * </pre>
     *
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     *
     * @since 2021. 9. 14.
     * @version 1.8.0
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public AsyncExecutionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 9. 14.		박준홍			최초 작성
     * </pre>
     *
     * @param cause
     *
     * @since 2021. 9. 14.
     * @version 1.8.0
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public AsyncExecutionException(Throwable cause) {
        super(cause);
    }

}
