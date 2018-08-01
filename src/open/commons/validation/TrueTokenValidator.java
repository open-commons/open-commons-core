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
* This file is generated under this project, "UUUU".
*
* Date  : 2014. 4. 11. 오후 1:53:17
*
* Author: Park_Jun_Hong_(fafanmama_at_naver_com)
* 
*/

package open.commons.validation;

import java.util.Set;

/**
 * 
 * @since 2014. 4. 11.
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
public class TrueTokenValidator<T> extends NamedTokenValidator<T> {

    public TrueTokenValidator(String name) {
        super(name);
    }

    /**
     * @see open.commons.validation.ITokenValidator#addValidToken(java.lang.Object)
     */
    @Override
    public final void addValidToken(T token) {
    }

    /**
     * @see open.commons.validation.ITokenValidator#getValidTokens()
     */
    @Override
    public final Set<T> getValidTokens() {
        return null;
    }

    /**
     * @see open.commons.validation.ITokenValidator#validate(java.lang.Object)
     */
    @Override
    public final boolean validate(T token) {
        return true;
    }
}
