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
* This file is generated under this project, "open-commons-core".
*
* Date  : 2014. 9. 17. 오후 12:48:40
*
* Author: Park_Jun_Hong_(fafanmama_at_naver_com)
* 
*/

package open.commons.validation;

import java.util.Iterator;
import java.util.NoSuchElementException;

import open.commons.utils.AssertUtils;

public abstract class AbstractTokenizer<D, T> implements ITokenizer<D, T> {

    protected D data;

    protected Iterator<T> tokens;

    public AbstractTokenizer(D data) throws NullPointerException {
        AssertUtils.assertNull("A data MUST NOT BE null", data, NullPointerException.class);

        this.data = data;

        tokens = tokenize();
    }

    @Override
    public D getData() {
        return data;
    }

    @Override
    public T getToken() throws NoSuchElementException {
        return tokens.next();
    }

    @Override
    public boolean hasToken() {
        return tokens.hasNext();
    }

    /**
     * 데이터를 위한 토큰을 생성한다.
     * 
     *
     * @since 2014. 9. 17.
     */
    protected abstract Iterator<T> tokenize();

}
