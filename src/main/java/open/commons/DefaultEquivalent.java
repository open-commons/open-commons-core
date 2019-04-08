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
* Date  : 2012. 03. 20. 오후 7:17:58
*
* Author: Park Jun-Hong (fafanmama_at_naver_dot_com)
* 
*/
package open.commons;

/**
 * 
 * <BR>
 * 
 * @since 2012. 03. 20.
 * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
 */
public class DefaultEquivalent<T> implements IEquivalent<T> {

    /**
     * @param t1
     * @param t2
     * @return
     * 
     * @since 2012. 03. 20.
     * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
     * 
     * @see open.commons.IEquivalent#equals(java.lang.Object, java.lang.Object)
     */
    @Override
    public boolean equals(T t1, T t2) {
        if (t1 != null && t2 != null) {
            return t1.equals(t2);
        } else {
            return nullEquals(t1, t2);
        }
    }

    /**
     * 주어진 객체가 모두 <code>null</code>이 아닌 경우를 제외한 경우 비교하는 기능성 메소드<br>
     * 결과는 모두 <code>null</code>이면 <code>true</code>, 그렇지 않으면 <code>false</code> 를 반환한다.
     * 
     * @param t1
     * @param t2
     * @return <BR>
     * @since 2012. 03. 20.
     * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
     */
    protected final boolean nullEquals(T t1, T t2) {
        if (t1 == null && t2 == null) {
            return true;
        } else {
            return false;
        }
    }
}
