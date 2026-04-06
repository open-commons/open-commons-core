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
* This file is generated under this project, "open-commons-core".
*
* Date  : 2014. 3. 28. 오후 3:48:23
*
* Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
* 
*/

package open.commons.core.validation;

/**
 * 데이터 검증 처리를 위한 인터페이스입니다.
 *
 * <pre>
 * [개정이력]
 * 날짜        | 작성자                    | 내용
 * ----------------------------------------------------------------------
 * 2014. 9. 17.      parkjunhong77@gmail.com     최초 작성
 * </pre>
 *
 * @param <D>
 *            검증할 데이터 타입
 * @param <T>
 *            단위 토큰 타입
 *
 * @since 2014. 9. 17.
 */
public interface IValidator<D, T> {

    /**
     * 등록된 검증기들에 의한 본 검증이 완료된 후 실행됩니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2014. 4. 11.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param data
     *            검증할 데이터가 포함된 토크나이저
     *
     * @return 사후 검증 통과 여부
     *
     * @since 2014. 4. 11.
     */
    public boolean postValid(ITokenizer<D, T> data);

    /**
     * 등록된 검증기들에 의한 본 검증이 시작되기 전 실행됩니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2014. 4. 11.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param data
     *            검증할 데이터가 포함된 토크나이저
     *
     * @return 사전 검증 통과 여부
     *
     * @since 2014. 4. 11.
     */
    public boolean preValid(ITokenizer<D, T> data);

    /**
     * 데이터 검증을 수행합니다. (Fail-Fast 방식으로 부적합 발견 시 즉시 종료)
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2014. 4. 11.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @return 검증 통과 여부
     *
     * @since 2014. 4. 11.
     *
     * @see #validate(boolean)
     */
    public boolean validate();

    /**
     * 지정된 방식에 따라 데이터 검증을 수행합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2014. 4. 11.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param fully
     *            구성데이터 전체 확인 여부.<br>
     *            {@code false}인 경우 부적합한 경우가 발견되는 즉시 종료(Fail-Fast)합니다.
     *
     * @return 검증 통과 여부
     *
     * @since 2014. 4. 11.
     */
    public boolean validate(boolean fully);

}