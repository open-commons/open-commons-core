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
* Date  : 2014. 4. 10. 오후 5:47:36
*
* Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
* 
*/

package open.commons.core.validation;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import open.commons.core.concurrent.Mutex;
import open.commons.core.utils.AssertUtils2;

/**
 * 
 * @since 2014. 4. 10.
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public abstract class DefaultTokenValidator<T> implements ITokenValidator<T> {

    protected Set<T> validTokens = new HashSet<T>();
    protected Mutex mutexValidTokens = new Mutex("validTokens");

    private String name;

    public DefaultTokenValidator(String name) {
        this.name = name;
    }

    /**
     * @see open.commons.core.validation.ITokenValidator#addValidToken(java.lang.Object)
     */
    @Override
    public void addValidToken(T token) {
        AssertUtils2.assertNotNull(token);

        synchronized (mutexValidTokens) {
            validTokens.add(token);
        }
    }

    /**
     * @see open.commons.core.validation.ITokenValidator#getName()
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @see open.commons.core.validation.ITokenValidator#getValidTokens()
     */
    @Override
    public Set<T> getValidTokens() {
        synchronized (mutexValidTokens) {
            return Collections.unmodifiableSet(validTokens);
        }
    }

    /**
     * @see open.commons.core.validation.ITokenValidator#isPositive()
     */
    @Override
    public boolean isPositive() {
        return true;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "DefaultTokenValidator [name=" + name + ", validTokens=" + validTokens + "]";
    }

    /**
     * @see open.commons.core.validation.ITokenValidator#validate(java.lang.Object)
     */
    @Override
    public boolean validate(T token) {
        synchronized (mutexValidTokens) {
            return validTokens.contains(token);
        }
    }

}
