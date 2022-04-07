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
* Date  : 2014. 6. 17. 오후 3:03:00
*
* Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
* 
*/

package open.commons.core.reflect;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import open.commons.core.concurrent.Mutex;

/**
 * Generic 을 사용하는 클래스들의 Generic Type Variable Name Literal을 제공하는 클래스.
 * 
 * @since 2014. 6. 17.
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
@SuppressWarnings("unused")
public class TypeVariableNames {

    /** key: qualified name of a class, value: a list of TypeVariable Name string */
    private static ConcurrentHashMap<String, List<String>> typeVarNames = new ConcurrentHashMap<String, List<String>>();
    private static Mutex mutexTypeVarNames = new Mutex("typeVarNames");

    static {

    }

    /**
     * Prevent to create a new instance.
     * 
     * @since 2014. 6. 17.
     */
    private TypeVariableNames() {
    }

    public static GenericTypeVariable getGenericTypeVariable(Class<?> typeClass) {
        String qname = typeClass.getName();

        return null;
    }
}
