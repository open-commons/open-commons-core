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
 * Date  : 2025. 8. 30. 오후 3:17:21
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package open.commons.core.exception;

import java.util.Objects;

import org.jspecify.annotations.Nullable;

/**
 * {@link Class#newInstance()}를 이용하여 새로운 객체를 생성하는 것이 실패할 경우 발생하는 {@link RuntimeException}.
 * 
 * @since 2025. 8. 30.
 * @version 2.1.0
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 * @see Class#newInstance()
 * @see InstantiationException
 * @see IllegalAccessException
 */
public class CreateInstanceFailedException extends RuntimeException {

    private static final long serialVersionUID = 2914914119106001745L;

    /** 데이터 유형 */
    private final Class<?> objectType;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 30.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     * 
     * @param objectType
     *            데이터 유형.
     * @param message
     *            오류 메시지.
     * @param cause
     *            오류 정보.
     *
     * @since 2025. 8. 30.
     * @version 2.1.0
     */
    public CreateInstanceFailedException(Class<?> objectType, @Nullable String message, @Nullable Throwable cause) {
        Objects.requireNonNull(objectType);

        super(message, cause);

        this.objectType = objectType;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 30.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     * 
     * @param objectType
     *            데이터 유형.
     * @param cause
     *            오류 정보.
     *
     * @since 2025. 8. 30.
     * @version 2.1.0
     */
    public CreateInstanceFailedException(Class<?> objectType, @Nullable Throwable cause) {
        Objects.requireNonNull(objectType);

        super(cause);
        this.objectType = objectType;
    }

    /**
     *
     * @since 2025. 8. 30.
     * @version 2.1.0
     * 
     * @see java.lang.Object#toString()
     */
    // 아래 내용에 적용됨.
    // - builder.toString()
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("CreateInstanceFailedException [objectType=");
        builder.append(objectType);
        String msg = getLocalizedMessage();
        if (msg != null) {
            builder.append(", cause=");
            builder.append(msg);
        }
        builder.append("]");
        return builder.toString();
    }

}
