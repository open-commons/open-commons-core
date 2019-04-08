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
* This file is generated under this project, "open-commons-core".
*
* Date  : 2013. 6. 20. 오전 10:53:56
*
* Author: Park_Jun_Hong_(fafanmama_at_naver_com)
* 
*/

/**
 * 
 */
package open.commons.collection;

import java.util.Comparator;

/**
 * 문자열로 표현된 integer/long 값에 대한 {@link Comparator}
 * 
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */
public class StringIntegerComparator implements Comparator<String> {

    /**
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    @Override
    public int compare(String o1, String o2) {
        int i1 = Integer.parseInt(o1);
        int i2 = Integer.parseInt(o2);

        return i1 - i2;
    }
}