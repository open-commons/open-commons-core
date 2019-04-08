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
*
* This file is generated under this project, "UUUU".
*
* Date  : 2014. 4. 10. 오후 5:46:34
*
* Author: Park_Jun_Hong_(fafanmama_at_naver_com)
* 
*/

package open.commons.validation.token;

import open.commons.validation.DefaultTokenValidator;

/**
 * 
 * @since 2014. 4. 10.
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
public class ExclusiveStrValidator extends DefaultTokenValidator<CharSequence> {

    /**
     * @see open.commons.validation.DefaultTokenValidator#validate(java.lang.Object)
     */
    @Override
    public boolean validate(CharSequence token) {
        return !super.validate(token);
    }

    /**
     * 
     * @since 2014. 4. 10.
     */
    public ExclusiveStrValidator() {
        super("제외시킬 문자열");
    }
}
