/*
 * Copyright 2011 Park Jun-Hong (parkjunhong77/google/com)
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
*/
package open.commons;

/**
 * true/false 결과와 함께 관련 데이타를 같이 전달해주는 클래스 <BR>
 * 
 * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
 * 
 * @since 2014. 6. 27. - add {@link #message}: 성공/실패에 대한 정보
 * @since 2012. 02. 15.
 * 
 */
public class Result<T> {

    /** 결과와 관련된 데이타 */
    private T data;

    /** true/false 값 */
    private boolean result;

    /** 성공/실패에 대한 정보 */
    private String message;

    /**
     * 결과값과 데이타 값으로 기본값을 사용하는 생성자.<br>
     * <ul>
     * <li>{@link #data}: <code>null</code>
     * <li>{@link #result}: <code>false</code>
     * </ul>
     * 
     * @see Result#Result(Object, boolean)
     */
    public Result() {
        this(null, false);
    }

    /**
     * 결과 데이타를 가지고 객체를 생성한다.<br>
     * {@link #result}에 대한 값은 <code>false</code>로 설정된다.
     * 
     * @param data
     * 
     * @since 2012. 02. 15.
     * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
     * 
     * @see #Result(Object, boolean)
     */
    public Result(T data) {
        this(data, false);
    }

    /**
     * 결과 데이타와 <code>true</code>/<code>false</code>를 가지고 객체를 생성한다.<br>
     * 
     * @param data
     * @param result
     * 
     * @since 2012. 02. 15.
     * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
     */
    public Result(T data, boolean result) {
        // if (data == null) {
        // throw new IllegalArgumentException("'data' must not be null!!!");
        // }

        this.data = data;
        this.result = result;
    }

    /**
     * 결과를 <code>false</code>로 설정한 후 객체를 반환한다.
     * 
     * @return
     * @since 2012. 05. 30.
     * @author Park Jun Hong (jhpark@ymtech.kr)
     */
    public Result<T> andFalse() {
        this.result = false;

        return this;
    }

    /**
     * 결과를 <code>true</code>로 설정한 후 객체를 반환한다.
     * 
     * @return
     * @since 2012. 05. 30.
     * @author Park Jun Hong (jhpark@ymtech.kr)
     */
    public Result<T> andTrue() {
        this.result = true;

        return this;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @SuppressWarnings("rawtypes")
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Result other = (Result) obj;
        if (data == null) {
            if (other.data != null)
                return false;
        } else if (!data.equals(other.data))
            return false;
        if (message == null) {
            if (other.message != null)
                return false;
        } else if (!message.equals(other.message))
            return false;
        if (result != other.result)
            return false;
        return true;
    }

    /**
     * 결과 데이타를 반환한다.
     * 
     * @return
     * 
     * @since 2012. 02. 15.
     * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
     */
    public T getData() {
        return data;
    }

    /**
     * 
     * @return the message
     * 
     * @since 2014. 6. 27.
     */
    public String getMessage() {
        return message;
    }

    /**
     * 결과를 반환한다.
     * 
     * @return
     * 
     * @since 2012. 02. 15.
     * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
     */
    public boolean getResult() {
        return result;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((data == null) ? 0 : data.hashCode());
        result = prime * result + ((message == null) ? 0 : message.hashCode());
        result = prime * result + (this.result ? 1231 : 1237);
        return result;
    }

    /**
     * 데이타를 설정하고, 이전 데이타를 반환한다.
     * 
     * @param data
     * @return
     * 
     * @updated 2019. 2. 29. Apply chainging, 'return this' since 1.6.3. Before updating, return lastest data.
     */
    public Result<T> setData(T data) {
        this.data = data;
        return this;
    }

    /**
     * 
     * @param message
     *            the message to set
     * 
     * @return this instance.
     * 
     * @since 2014. 6. 27.
     * @updated 2019. 2. 26 Apply chaining, 'return this' since 1.6.3.
     */
    public Result<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 2. 14.		박준홍			최초 작성
     * </pre>
     *
     * @param format
     *            메시지 포맷.
     * @param args
     *            메시지 파라미터.
     * @return
     *
     * @since 2020. 2. 14.
     * @version _._._
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public Result<T> setMessage(String format, Object... args) {
        this.message = String.format(format, args);
        return this;
    }

    /**
     * 결과값을 설정하고, 이전 결과값을 반환한다.
     * 
     * @param result
     * @return
     * 
     * @since 2012. 02. 15.
     * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
     * @updated 2019. 2. 29. Apply chaining, 'return this' since 1.6.3. Before updating, returh latest result.
     */
    public boolean setResult(boolean result) {
        boolean latestResult = this.result;
        this.result = result;

        return latestResult;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 4. 11.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2020. 4. 11.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();

        builder.append("Result [data=");
        builder.append(data);
        builder.append(", result=");
        builder.append(result);
        builder.append(", message=");
        builder.append(message);
        builder.append("]");

        return builder.toString();
    }

    /**
     * 결과와 메시지를 복제한 새로운 객체를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 11. 20.		박준홍			최초 작성
     * </pre>
     *
     * @param <N>
     * @param o
     * @return
     *
     * @since 2020. 11. 20.
     * @version _._._
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public static <N> Result<N> copyOf(Result<?> o) {
        return new Result<N>(null, o.getResult()).setMessage(o.getMessage());
    }

    /**
     * "에러" 결과 객체를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 4. 11.     박준홍         최초 작성
     * </pre>
     *
     * @param <T>
     * @param errorMessage
     * @return
     *
     * @since 2020. 4. 11.
     * @version
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public static <T> Result<T> error(String errorMessage) {
        return new Result<T>().setMessage(errorMessage);
    }

    /**
     * {@link Result#getResult()} 가 <code>true</code> 인 경우의 메시지를 선택적으로 제공한다. <br>
     * {@link Result#getResult()} 가 <code>false</code> 인 경우 {@link Result#getMessage()}를 제공한다.
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 11. 3.     박준홍         최초 작성
     * </pre>
     *
     * @param <T>
     * @param result
     * @param format
     * @param args
     *            TODO
     * @return
     *
     * @since 2020. 11. 3.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public static <T> String getTrueMessage(Result<T> result, String format, Object... args) {
        return result.getResult() ? String.format(format, args) : result.getMessage();
    }

    /**
     * "성공" 결과 객체를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 4. 11.     박준홍         최초 작성
     * </pre>
     *
     * @param <T>
     * @param data
     * @return
     *
     * @since 2020. 4. 11.
     * @version
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public static <T> Result<T> success(T data) {
        return new Result<T>(data, true);
    }

}
