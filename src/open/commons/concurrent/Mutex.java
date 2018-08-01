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
* Date  : 2012. 10. 31. 오후 9:23:45
*
* Author: Park_Jun_Hong_(fafanmama_at_naver_com)
* 
*/
package open.commons.concurrent;

/**
 * Mutual Exclusive Object with a name.
 * 
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */
public final class Mutex {
    /**
     * Simple Class Name
     */
    public static final String SCN = Mutex.class.getSimpleName();

    public final Object mutex = new Object();
    public final String name;

    public Mutex(String name) {
        this.name = name;
    }

    /**
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return SCN + " [name=" + name + ", mutex=" + mutex + "]";
    }

}
