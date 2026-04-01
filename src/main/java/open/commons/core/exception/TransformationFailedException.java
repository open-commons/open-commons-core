/*
 * Copyright 2025 Park Jun-Hong (parkjunhong77@gmail.com)
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
 * Date  : 2025. 9. 8. 오후 12:47:17
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package open.commons.core.exception;

import org.jspecify.annotations.Nullable;

import open.commons.core.utils.ObjectUtils;

/**
 * 데이터 '형 변환' 도중 오류가 발생했을 때.
 * 
 * @since 2025. 9. 8.
 * @version 2.1.0
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */
public class TransformationFailedException extends RuntimeException {

    private static final long serialVersionUID = -2384889821860943584L;
    /** 원본 데이터 유형 */
    private final Class<?> srcClass;
    /** 대상 데이터 유형 */
    private final Class<?> targetClass;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 8.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     * 
     * @param srcClass
     *            원본 데이터 유형
     * @param targetClass
     *            대상 데이터 유형
     * 
     * @throws NullPointerException
     *             파라미터({@code srcClass, targetClass})가 {@code null}인 경우 발생.
     *
     * @since 2025. 9. 8.
     * @version 2.1.0
     * 
     */
    public TransformationFailedException(Class<?> srcClass, Class<?> targetClass) {
        this(srcClass, targetClass, null, null, true, true);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 8.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     * 
     * @param srcClass
     *            원본 데이터 유형
     * @param targetClass
     *            대상 데이터 유형
     * @param message
     * 
     * @throws NullPointerException
     *             파라미터({@code srcClass, targetClass})가 {@code null}인 경우 발생.
     *
     * @since 2025. 9. 8.
     * @version 2.1.0
     * 
     */
    public TransformationFailedException(Class<?> srcClass, Class<?> targetClass, @Nullable String message) {
        this(srcClass, targetClass, message, null, true, true);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 8.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     * 
     * @param srcClass
     *            원본 데이터 유형
     * @param targetClass
     *            대상 데이터 유형
     * @param message
     * @param cause
     * 
     * @throws NullPointerException
     *             파라미터({@code srcClass, targetClass})가 {@code null}인 경우 발생.
     *
     * @since 2025. 9. 8.
     * @version 2.1.0
     * 
     */
    public TransformationFailedException(Class<?> srcClass, Class<?> targetClass, @Nullable String message, @Nullable Throwable cause) {
        this(srcClass, targetClass, message, cause, true, true);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 8.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     * 
     * @param srcClass
     *            원본 데이터 유형
     * @param targetClass
     *            대상 데이터 유형
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     * 
     * @throws NullPointerException
     *             파라미터({@code srcClass, targetClass})가 {@code null}인 경우 발생.
     *
     * @since 2025. 9. 8.
     * @version 2.1.0
     * 
     */
    public TransformationFailedException(Class<?> srcClass, Class<?> targetClass, @Nullable String message, @Nullable Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        ObjectUtils.requireNonNulls(srcClass, targetClass);

        super(message, cause, enableSuppression, writableStackTrace);
        this.srcClass = srcClass;
        this.targetClass = targetClass;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 8.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     * 
     * @param srcClass
     *            원본 데이터 유형
     * @param targetClass
     *            대상 데이터 유형
     * @param cause
     *
     * @since 2025. 9. 8.
     * @version 2.1.0
     * 
     */
    public TransformationFailedException(Class<?> srcClass, Class<?> targetClass, Throwable cause) {
        this(srcClass, targetClass, null, cause, true, true);
    }

    /**
     *
     * @since 2025. 9. 8.
     * @version 2.1.0
     * 
     *
     * @see java.lang.Throwable#getLocalizedMessage()
     */
    // 아래 내용에 적용됨.
    // - targets
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    @Override
    public String getLocalizedMessage() {

        StringBuilder builder = new StringBuilder();
        builder.append("src=");
        builder.append(this.srcClass.getName());
        builder.append(", target=");
        builder.append(this.targetClass.getName());

        Object o = null;
        if ((o = getCause()) != null) {
            builder.append(", cause=");
            builder.append(o);
        }
        if ((o = super.getMessage()) != null) {
            builder.append(", message=");
            builder.append(o);
        }

        return builder.toString();
    }

    /**
     *
     * @since 2025. 9. 9.
     * @version 2.1.0
     * 
     *
     * @see java.lang.Throwable#getMessage()
     */
    @Override
    public String getMessage() {
        return getLocalizedMessage();
    }
}
