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
* This file is generated under this project, "open-commons-core".
*
* Date  : 2014. 9. 17. 오후 1:27:43
*
* Author: Park_Jun_Hong_(fafanmama_at_naver_com)
* 
*/

package open.commons.validation.support;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import open.commons.validation.AbstractTokenizer;

public class StringTokenizer extends AbstractTokenizer<CharSequence, Character> {

    public StringTokenizer(CharSequence data) throws NullPointerException {
        super(data);
    }

    @Override
    protected Iterator<Character> tokenize() {

        List<Character> cs = new ArrayList<>();

        for (int i = 0; i < data.length(); i++) {
            cs.add(data.charAt(i));
        }

        return cs.iterator();
    }
}
