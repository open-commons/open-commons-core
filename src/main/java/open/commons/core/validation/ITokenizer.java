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
* Date  : 2014. 9. 17. 오후 12:45:50
*
* Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
* 
*/

package open.commons.core.validation;

import java.util.NoSuchElementException;

/**
 * 데이터 토큰화 처리를 위한 인터페이스입니다.
 *
 * <pre>
 * [개정이력]
 * 날짜        | 작성자                    | 내용
 * ----------------------------------------------------------------------
 * 2014. 9. 17.      parkjunhong77@gmail.com     최초 작성
 * </pre>
 *
 * @param <D>
 *            검증할 대상 데이터 타입
 * @param <T>
 *            단위 토큰 타입
 *
 * @since 2014. 9. 17.
 */
public interface ITokenizer<D, T> {

    /**
     * 검증할 대상 데이터를 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2014. 9. 17.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @return 검증 대상 데이터
     *
     * @since 2014. 9. 17.
     */
    public D getData();

    /**
     * 다음 토큰을 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2014. 9. 17.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @return 다음 토큰
     *
     * @throws NoSuchElementException
     *             더 이상 반환할 토큰이 없는 경우 발생.
     *
     * @since 2014. 9. 17.
     */
    public T getToken() throws NoSuchElementException;

    /**
     * 반환할 토큰이 남아있는지 여부를 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2014. 9. 17.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @return 토큰 존재 여부
     *
     * @since 2014. 9. 17.
     */
    public boolean hasToken();
}