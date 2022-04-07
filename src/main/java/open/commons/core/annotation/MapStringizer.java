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
* Date  : 2012. 11. 10. 오후 4:27:11
*
* Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
*
*/
package open.commons.core.annotation;

import java.util.Map;

public class MapStringizer implements IToStringizer {

    @Override
    public String fieldToString(Object fieldValue) {
        String toString = null;
        if (fieldValue != null) {
            Map<?, ?> map = (Map<?, ?>) fieldValue;

            int size = map.size();
            toString = "(this Map) key.size=" + size + ", value.size=" + size;
        }
        return toString;
    }
}
