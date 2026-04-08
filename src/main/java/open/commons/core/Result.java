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
*/
package open.commons.core;

import java.util.Objects;

import org.jspecify.annotations.Nullable;

import open.commons.core.utils.ObjectUtils;

/**
 * true/false 결과와 함께 관련 데이타를 같이 전달해주는 클래스 <BR>
 * 
 * <br>
 * 
 * <pre>
 * [개정이력]
 *      날짜    	| 작성자			|	내용
 * ------------------------------------------
 * 2012. 2. 15.     parkjunhong77@gmail.com     최초 작성
 * 2014. 6. 27.     parkjunhong77@gmail.com     {@link #message}: 성공/실패에 대한 정보
 * </pre>
 * 
 * @since 2012. 02. 15.
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */
public class Result<T> {

    /** 결과와 관련된 데이타 */
    private @Nullable T data;

    /** true/false 값 */
    private boolean result;

    /** 성공/실패에 대한 정보 */
    private @Nullable String message;

    /**
     * 결과값과 데이타 값으로 기본값을 사용하는 생성자.<br>
     * <ul>
     * <li>{@link #data}: {@code null}
     * <li>{@link #result}: {@code false}
     * </ul>
     * 
     * @see Result#Result(Object, boolean)
     */
    public Result() {
        this(null, false);
    }

    /**
     * 결과 데이타를 가지고 객체를 생성합니다.<br>
     * {@link #result}에 대한 값은 {@code false}로 설정된다.
     * 
     * @param data
     * 
     * @since 2012. 02. 15.
     * 
     * 
     * @see #Result(Object, boolean)
     */
    public Result(@Nullable T data) {
        this(data, false);
    }

    /**
     * 결과 데이타와 {@code true}/{@code false}를 가지고 객체를 생성합니다.<br>
     * 
     * @param data
     * @param result
     * 
     * @since 2012. 02. 15.
     * 
     */
    public Result(@Nullable T data, boolean result) {
        this.data = data;
        this.result = result;
    }

    /**
     * 결과를 {@code false}로 설정한 후 객체를 반환합니다.
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
     * 결과를 {@code true}로 설정한 후 객체를 반환합니다.
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
     *
     * @since 2026. 3. 13.
     * @version 3.0.0
     * 
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Result<T> other = (Result<T>) obj;
        return Objects.equals(data, other.data) && Objects.equals(message, other.message) && result == other.result;
    }

    /**
     * 결과 데이타를 반환합니다.
     * 
     * @return
     * 
     * @since 2012. 02. 15.
     * 
     */
    public @Nullable T getData() {
        return this.data;
    }

    /**
     * 
     * @return the message
     * 
     * @since 2014. 6. 27.
     */
    public @Nullable String getMessage() {
        return this.message;
    }

    /**
     * 결과를 반환합니다.<br>
     * 좀 더 명확한 의미를 얻고자 한다면,
     * <ul>
     * <li>{@link #isFail()}: 실패/에러
     * <li>{@link #isSuccess()}: 성공
     * </ul>
     * 을 사용합니다.
     * 
     * @return
     * 
     * @since 2012. 02. 15.
     * 
     */
    public boolean getResult() {
        return this.result;
    }

    /**
     *
     * @since 2026. 3. 13.
     * @version 3.0.0
     * 
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(data, message, result);
    }

    /**
     * 실패 여부를 제공합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 10. 28.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2021. 10. 28.
     * @version 1.8.0
     * 
     */
    public boolean isError() {
        return !this.getResult();
    }

    /**
     * 성공 여부를 제공합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 10. 28.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2021. 10. 28.
     * @version 1.8.0
     * 
     * @see {@link Result#getResult()} 와 동일한 기능.
     */
    public boolean isSuccess() {
        return this.getResult();
    }

    /**
     * 데이타를 설정하고, 이전 데이타를 반환합니다.
     * 
     * @param data
     * @return
     * 
     * @updated 2019. 2. 29. Apply chainging, 'return this' since 1.6.3. Before updating, return lastest data.
     */
    public Result<T> setData(@Nullable T data) {
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
    public Result<T> setMessage(@Nullable String message) {
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
     * 2020. 2. 14.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param format
     *            메시지 포맷.
     * @param args
     *            메시지 파라미터.
     * @return
     *
     * @since 2020. 2. 14.
     * @version 1.8.0
     * 
     */
    public Result<T> setMessage(String format, @Nullable Object... args) {
        Objects.requireNonNull(format);
        Objects.requireNonNull(args);

        this.message = String.format(format, args);
        return this;
    }

    /**
     * 결과값을 설정하고, 이전 결과값을 반환합니다.
     * 
     * @param result
     * @return
     * 
     * @since 2012. 02. 15.
     * 
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
     * 2020. 4. 11.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2020. 4. 11.
     * 
     * @see java.lang.Object#toString()
     */
    // 아래 내용에 적용됨.
    // - StringBuilder.toString()
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
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
     * 결과와 메시지를 복제한 새로운 객체를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 11. 20.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <N>
     * @param o
     * 
     * @return
     *
     * @since 2020. 11. 20.
     * @version 1.8.0
     * 
     */
    public static <N> Result<N> copyOf(Result<?> o) {
        Objects.requireNonNull(o);

        return new Result<N>(null, o.getResult()).setMessage(o.getMessage());
    }

    /**
     * "에러" 결과 객체를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 4. 11.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <T>
     * @param errorMessage
     * @return
     *
     * @since 2020. 4. 11.
     * @version
     * 
     */
    public static <T> Result<T> error(@Nullable String errorMessage) {
        return new Result<T>().setMessage(errorMessage);
    }

    /**
     * '에러' 결과 객체를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 7. 14.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <T>
     * @param format
     *            메시지 포맷
     * @param args
     *            메시지 정보
     * @return
     *
     * @since 2021. 7. 14.
     * @version 1.8.0
     * 
     */
    public static <T> Result<T> error(String format, @Nullable Object... args) {
        return new Result<T>().setMessage(format, args);
    }

    /**
     * {@link Result#getResult()} 가 {@code true} 인 경우의 메시지를 선택적으로 제공합니다. <br>
     * {@link Result#getResult()} 가 {@code false} 인 경우 {@link Result#getMessage()}를 제공합니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 11. 3.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <T>
     * @param result
     * @param format
     * @param args
     * 
     * @return
     *
     * @since 2020. 11. 3.
     * 
     */
    public static <T> @Nullable String getTrueMessage(Result<T> result, String format, @Nullable Object... args) {
        ObjectUtils.requireNonNulls(result, format, args);

        return result.getResult() ? String.format(format, args) : result.getMessage();
    }

    /**
     * '성공' 결과 객체를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2023. 12. 6.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <T>
     * @param format
     *            메시지 포맷
     * @param args
     *            메시지 정보
     * @return
     *
     * @since 2023. 12. 6.
     * @version 2.0.0
     * 
     */
    public static <T> Result<T> success(String format, @Nullable Object... args) {
        return success(null, format, args);
    }

    /**
     * "성공" 결과 객체를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 4. 11.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <T>
     * @param data
     * @return
     *
     * @since 2020. 4. 11.
     * @version
     * 
     */
    public static <T> Result<T> success(@Nullable T data) {
        return new Result<T>(data, true);
    }

    /**
     * '성공' 결과 객체를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2023. 12. 6.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <T>
     * @param data
     *            결과 데이터
     * @param format
     *            메시지 포맷
     * @param args
     *            메시지 정보
     * @return
     *
     * @since 2023. 12. 6.
     * @version 2.0.0
     * 
     */
    public static <T> Result<T> success(@Nullable T data, String format, @Nullable Object... args) {
        return Result.success(data).setMessage(format, args);
    }

}
