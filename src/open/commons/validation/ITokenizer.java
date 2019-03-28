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
* Date  : 2014. 9. 17. 오후 12:45:50
*
* Author: Park_Jun_Hong_(fafanmama_at_naver_com)
* 
*/

package open.commons.validation;

import java.util.NoSuchElementException;

/**
 * 
 * @param <D>
 *            data type to validate
 * @param <T>
 *            token type
 * @since 2014. 9. 17.
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
public interface ITokenizer<D, T> {

    public D getData();

    public T getToken() throws NoSuchElementException;

    public boolean hasToken();
}
