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
* This file is generated under this project, "open-commons-core".
*
* Date  : 2014. 9. 17. 오후 12:48:40
*
* Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
* 
*/

package open.commons.core.validation;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.jspecify.annotations.Nullable;

/**
 * 데이터 토큰화 처리를 위한 추상 클래스입니다.
 *
 * @param <D>
 *            데이터 타입
 * @param <T>
 *            토큰 타입
 */
public abstract class AbstractTokenizer<D, T> implements ITokenizer<D, T> {

    /** 대상 데이터 */
    protected final D data;

    /** 지연 초기화되는 토큰 반복자 */
    protected @Nullable Iterator<T> tokens;

    /**
     * 객체를 생성합니다.
     *
     * @param data
     *            대상 데이터
     *
     * @throws NullPointerException
     *             파라미터({@code data})가 {@code null}인 경우 발생.
     */
    public AbstractTokenizer(D data) {
        Objects.requireNonNull(data);

        this.data = data;
    }

    /**
     * 대상 데이터를 반환합니다.
     *
     * @return 대상 데이터
     */
    @Override
    public D getData() {
        return data;
    }

    /**
     * 다음 토큰을 반환합니다.
     *
     * @return 다음 토큰
     *
     * @throws NoSuchElementException
     *             더 이상 반환할 토큰이 없는 경우 발생.
     */
    @Override
    public T getToken() throws NoSuchElementException {
        return getTokens().next();
    }

    /**
     * 토큰 반복자({@link Iterator})를 지연 초기화(Lazy Initialization)하여 반환합니다.
     *
     * @return 토큰 반복자
     */
    protected Iterator<T> getTokens() {
        if (this.tokens != null) {
            return this.tokens;
        }

        return this.tokens = Objects.requireNonNull(tokenize(), "tokenize()의 결과는 null일 수 없습니다.");
    }

    /**
     * 반환할 토큰이 남아있는지 여부를 반환합니다.
     *
     * @return 토큰 존재 여부
     */
    @Override
    public boolean hasToken() {
        return getTokens().hasNext();
    }

    /**
     * 데이터를 위한 토큰을 생성합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2014. 9. 17.      parkjunhong77@gmail.com     최초 작성
     * 2026. 4. 3.       parkjunhong77@gmail.com     (3.0.0) 생성자 내부 호출 버그 차단을 위한 지연 초기화 적용
     * </pre>
     *
     * @return 토큰 반복자
     *
     * @since 2014. 9. 17.
     */
    protected abstract Iterator<T> tokenize();
}