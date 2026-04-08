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
* Date  : 2014. 4. 10. 오후 5:47:36
*
* Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
* 
*/

package open.commons.core.validation;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.jspecify.annotations.Nullable;

/**
 * 기본 토큰 검증기 추상 클래스입니다.
 *
 * <pre>
 * [개정이력]
 * 날짜        | 작성자                    | 내용
 * ----------------------------------------------------------------------
 * 2014. 4. 10.      parkjunhong77@gmail.com     최초 작성
 * </pre>
 *
 * @param <T>
 *            토큰 타입
 *
 * @since 2014. 4. 10.
 */
public abstract class DefaultTokenValidator<T> implements ITokenValidator<T> {

    /** 유효 토큰 셋 (스레드 안전성 확보) */
    @SuppressWarnings("null")
    protected final Set<T> validTokens = ConcurrentHashMap.newKeySet();

    /** 검증기 식별 이름 */
    private final String name;

    /**
     * 객체를 생성합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2014. 4. 10.      parkjunhong77@gmail.com     최초 작성
     * 2026. 4. 6.       parkjunhong77@gmail.com     (3.0.0) 생성자 진입점 Null-Check 가드 클로즈 적용
     * </pre>
     *
     * @param name
     *            검증기 이름
     *
     * @since 2014. 4. 10.
     */
    public DefaultTokenValidator(String name) {
        Objects.requireNonNull(name);

        this.name = name;
    }

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
     *
     * @see open.commons.core.validation.ITokenValidator#addValidToken(java.lang.Object)
     */
    @Override
    public void addValidToken(T token) {
        Objects.requireNonNull(token);

        this.validTokens.add(token);
    }

    /**
     * 검증기 이름을 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2014. 4. 10.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @return 검증기 이름
     *
     * @since 2014. 4. 10.
     *
     * @see open.commons.core.validation.ITokenValidator#getName()
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * 유효 토큰 셋을 반환합니다.
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
     *
     * @see open.commons.core.validation.ITokenValidator#getValidTokens()
     */
    @Override
    public @Nullable Set<T> getValidTokens() {
        return Collections.unmodifiableSet(validTokens);
    }

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
     * @return 양성 여부 (항상 {@code true} 반환)
     *
     * @since 2014. 4. 10.
     *
     * @see open.commons.core.validation.ITokenValidator#isPositive()
     */
    @Override
    public boolean isPositive() {
        return true;
    }

    @Override
    public String toString() {
        return "DefaultTokenValidator [name=" + name + ", validTokens=" + validTokens + "]";
    }

    /**
     * 주어진 토큰의 유효성을 검증합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2014. 4. 10.      parkjunhong77@gmail.com     최초 작성
     * 2026. 4. 6.       parkjunhong77@gmail.com     (3.0.0) ConcurrentHashMap Null-Key 에러 방지를 위한 가드 클로즈 보강
     * </pre>
     *
     * @param token
     *            검증할 토큰
     *
     * @return 유효 토큰 셋 포함 여부
     *
     * @since 2014. 4. 10.
     *
     * @see open.commons.core.validation.ITokenValidator#validate(java.lang.Object)
     */
    @Override
    public boolean validate(T token) {
        Objects.requireNonNull(token);

        return this.validTokens.contains(token);
    }
}