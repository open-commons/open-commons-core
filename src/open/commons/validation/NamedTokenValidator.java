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
* Date  : 2014. 4. 11. 오후 1:53:52
*
* Author: Park_Jun_Hong_(fafanmama_at_naver_com)
* 
*/

package open.commons.validation;

/**
 * 
 * @since 2014. 4. 11.
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
public abstract class NamedTokenValidator<T> implements ITokenValidator<T> {

    protected String name;

    public NamedTokenValidator(String name) {
        this.name = name;
    }

    /**
     * @see open.commons.validation.ITokenValidator#getName()
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "DefaultTokenValidator [name=" + name + "]";
    }

    /**
     * @see open.commons.validation.ITokenValidator#isPositive()
     */
    @Override
    public boolean isPositive() {
        return true;
    }
}
