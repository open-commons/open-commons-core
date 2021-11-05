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
* Date  : 2012. 7. 30. 오후 7:26:36
*
* Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
*
* File  : AContainer.java 
* 
*/
package open.commons.collection;

public abstract class AContainer<T> implements IContainer<T> {

    /**
     * 두 객체에 대한 <code>null</code> 확인 결과를 반환한다.<br>
     * 
     * @param container
     * @param contained
     * @return
     */
    protected boolean checkNull(T container, T contained) {

        // 1. 'null' cannot contain anything.
        // 2. 'not-null object' does not contain 'null'. - [Park Jun-Hong]: 2012. 7. 30.
        if (container == null || contained == null) {
            return false;
        }

        return true;
    }
}
