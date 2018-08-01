/*
 * Copyright 2011 Park Jun-Hong_(fafanmama_at_naver_com)
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
* Author: Park_Jun_Hong_(fafanmama_at_naver_com)
* 
*/

package open.commons.validation;

/**
 * 
 * @param <D>
 *            {data} 검증할 데이터 타입
 * @param <T>
 *            {data}
 * @since 2014. 9. 17.
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
public interface IValidator<D, T> {

    /**
     * 등록된 {@link IValidator}들에 의한 검증 후 실행된다.
     * 
     * @param data
     * @return TODO
     * 
     * @since 2014. 4. 11.
     */
    public boolean postValid(ITokenizer<D, T> data);

    /**
     * 등록된 {@link IValidator}들에 의한 검증 전 실행된다.
     * 
     * @param data
     * @return TODO
     * 
     * @since 2014. 4. 11.
     */
    public boolean preValid(ITokenizer<D, T> data);

    /**
     * 
     * @return
     * 
     * @since 2014. 4. 11.
     * 
     * @see #validate(ITokenizer<D, T>, boolean)
     */
    public boolean validate();

    /**
     * 
     * @param fully
     *            구성데이터 전체 확인 여부.<br>
     *            false 인 경우 부적합한 경우가 발견되는 즉시 종료한다.
     * @return
     * 
     * @since 2014. 4. 11.
     */
    public boolean validate(boolean fully);

}
