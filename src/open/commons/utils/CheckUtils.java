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

/**
* 
*/
package open.commons.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import open.commons.DefaultEquivalent;
import open.commons.IEquivalent;

/**
 * @author Park Jun-Hong.(mail_to:fafanmama_at_naver_dot_com)
 * 
 * @date
 * @title [등록] 화면방식 상세항목 분기 매핑테이블 관련내용
 * 
 */
public class CheckUtils {

    /**
     * 파라미터들에 대한 <code>null</code> 확인을 한다.
     * 
     * @param col
     *            확인하고자 하는 데이타 <BR>
     * @since 2012. 02. 16.
     * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
     * 
     * @see NullPointerException 파라미터가 <code>null</code>인 경우.
     */
    public static <T> void checkNull(Collection<T> col) {
        if (col != null) {
            if (col.size() > 0) {
                Iterator<T> itr = col.iterator();

                int i = 0;
                do {
                    T value = itr.next();

                    if (value == null) {
                        throw new NullPointerException("An element is null: index=" + i + ", col=" + col);
                    }
                    i++;

                } while (itr.hasNext());
            }
        } else {
            throw new NullPointerException("A parameter(T... objs) must not be null.");
        }
    }

    /**
     * 파라미터들에 대한 <code>null</code> 확인을 한다.
     * 
     * @param objs
     *            확인하고자 하는 데이타 <BR>
     * @since 2012. 02. 16.
     * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
     * 
     * @see NullPointerException 파라미터가 <code>null</code>인 경우.
     */
    public static void checkNull(Object... objs) {
        if (objs != null) {
            for (int i = 0; i < objs.length; i++) {
                if (objs[i] == null)
                    throw new NullPointerException("An element is null: index=" + i + ", objs=" + Arrays.toString(objs));
            }
        } else {
            throw new NullPointerException("A parameter(T... objs) must not be null.");
        }
    }

    public static boolean containsNull(Object... objs) {
        boolean rtnValue = false;

        for (Object obj : objs) {
            if (obj == null) {
                rtnValue = true;

                break;
            }
        }

        return rtnValue;
    }

    /**
     * 
     * @param t1
     * @param t2
     * @return <BR>
     * @since 2012. 03. 21.
     * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
     */
    public static <T> boolean equals(T t1, T t2) {
        return equals(t1, t2, null);
    }

    /**
     * 
     * @param t1
     * @param t2
     * @param equivalent
     * @return <BR>
     * @since 2012. 03. 21.
     * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
     */
    public static <T> boolean equals(T t1, T t2, IEquivalent<T> equivalent) {

        if (equivalent == null) {
            equivalent = new DefaultEquivalent<T>();
        }

        return equivalent.equals(t1, t2);
    }
}
