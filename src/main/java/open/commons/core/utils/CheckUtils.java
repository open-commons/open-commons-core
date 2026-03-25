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

/**
* 
*/
package open.commons.core.utils;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;

import org.jspecify.annotations.Nullable;

import open.commons.core.DefaultEquivalent;
import open.commons.core.IEquivalent;

/**
 * @author Park Jun-Hong.(mail_to:parkjunhong77@gmail.com)
 * 
 * @date
 * @title [등록] 화면방식 상세항목 분기 매핑테이블 관련내용
 * 
 */
public class CheckUtils {

    /**
     * 파라미터들에 대한 {@code null} 확인을 합니다.
     * 
     * @param col
     *            확인하고자 하는 데이타 <BR>
     * 
     * @throws NullPointerException
     *             파라미터({@code col})가 {@code null}인 경우 발생.
     * @since 2012. 02. 16.
     * 
     */
    public static <T> void checkNull(@Nullable Collection<T> col) {
        Objects.requireNonNull(col);

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
    }

    /**
     * 파라미터들에 대한 {@code null} 확인을 합니다.
     * 
     * @param objs
     *            확인하고자 하는 데이타 <BR>
     * @throws NullPointerException
     *             파라미터({@code objs})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 02. 16.
     *
     * @see ObjectUtils#requireNonNulls(Object...)
     */
    public static void checkNull(Object @Nullable... objs) {
        ObjectUtils.requireNonNulls((Object[]) objs);
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
     * 
     */
    public static <T extends @Nullable Object> boolean equals(T t1, T t2) {
        return equals(t1, t2, null);
    }

    /**
     * 
     * @param t1
     * @param t2
     * @param equivalent
     * @return <BR>
     * @since 2012. 03. 21.
     * 
     */
    public static <T extends @Nullable Object> boolean equals(T t1, T t2, @Nullable IEquivalent<T> equivalent) {

        if (equivalent == null) {
            equivalent = new DefaultEquivalent<T>();
        }

        return equivalent.equals(t1, t2);
    }
}
