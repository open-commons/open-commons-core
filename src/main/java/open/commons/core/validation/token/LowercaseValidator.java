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
* Date  : 2014. 4. 10. 오후 5:46:34
*
* Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
* 
*/

package open.commons.core.validation.token;

import open.commons.core.validation.DefaultTokenValidator;

/**
 * 
 * @since 2014. 4. 10.
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public class LowercaseValidator extends DefaultTokenValidator<Character> {

    {
        for (char c = 'a'; c <= 'z'; c++) {
            validTokens.add(c);
        }
    }

    /**
     * 
     * @since 2014. 4. 10.
     */
    public LowercaseValidator() {
        super("소문자");
    }
}
