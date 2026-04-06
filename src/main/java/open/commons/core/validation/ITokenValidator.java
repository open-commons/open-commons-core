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
*
* This file is generated under this project, "UUUU".
*
* Date  : 2014. 4. 10. 오후 2:03:40
*
* Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
* 
*/
package open.commons.core.validation;

import java.util.Set;

import org.jspecify.annotations.Nullable;

/**
 * 단위 데이터(토큰)의 유효성을 검증하는 인터페이스입니다.
 *
 * <pre>
 * [개정이력]
 * 날짜        | 작성자                    | 내용
 * ----------------------------------------------------------------------
 * 2014. 4. 10.      parkjunhong77@gmail.com     최초 작성
 * </pre>
 *
 * @param <T>
 *            단위 토큰 타입
 *
 * @since 2014. 4. 10.
 */
public interface ITokenValidator<T> {

    /** 특징(Feature)이 할당되지 않은 기본 상태값입니다. */
    public static final int NO_FEATURE = 0x00;

    /**
     * 유효한 토큰을 추가합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2014. 4. 10.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param token
     *            추가할 토큰
     *
     * @since 2014. 4. 10.
     */
    public void addValidToken(T token);

    /**
     * 토큰 검증기의 이름을 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2014. 4. 10.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @return 토큰 검증기 이름
     *
     * @since 2014. 4. 10.
     */
    public String getName();

    /**
     * 유효한 토큰의 집합(Set)을 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2014. 4. 10.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @return 수정 불가능한(Unmodifiable) 유효 토큰 셋
     *
     * @since 2014. 4. 10.
     */
    public @Nullable Set<T> getValidTokens();

    /**
     * 검증 결과가 양성(Positive)인지 여부를 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2014. 4. 10.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @return 양성 여부
     *
     * @since 2014. 4. 10.
     */
    public boolean isPositive();

    /**
     * 주어진 토큰의 유효성을 검증합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2014. 4. 10.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param token
     *            검증할 토큰
     *
     * @return 검증 통과 여부
     *
     * @since 2014. 4. 10.
     */
    public boolean validate(T token);

    /**
     * 검증 결과를 수신하는 리스너 인터페이스입니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2014. 4. 10.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @since 2014. 4. 10.
     */
    public interface IValidationListener {

        /**
         * 검증 결과를 수신합니다.
         *
         * <pre>
         * [개정이력]
         * 날짜        | 작성자                    | 내용
         * ----------------------------------------------------------------------
         * 2014. 4. 10.      parkjunhong77@gmail.com     최초 작성
         * </pre>
         *
         * @param valid
         *            검증 결과
         *
         * @since 2014. 4. 10.
         */
        public void listen(boolean valid);
    }
}