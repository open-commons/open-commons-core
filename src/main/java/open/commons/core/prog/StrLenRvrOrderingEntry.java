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
* @since 2011. 09. 18.
*/
package open.commons.core.prog;

import java.util.Objects;

import org.jspecify.annotations.Nullable;

import open.commons.core.utils.ObjectUtils;

/**
 * String Length Reverse Ordering Entry
 * 
 * 
 * @since 2011. 09. 18.
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */
public class StrLenRvrOrderingEntry implements Comparable<StrLenRvrOrderingEntry> {

    private final String key;

    private final int len;

    private final String value;

    public StrLenRvrOrderingEntry(String k, String v) {
        ObjectUtils.requireNonNulls(k, v);

        key = Objects.requireNonNull(
                // [PATCH[ JDK 표준 API의 JSpecify 미지원 우회용 임시 널 체크.
                // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 requireNonNull 래핑 제거.
                k.trim() //
        );
        len = k.length();

        value = v;
    }

    /**
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(@Nullable StrLenRvrOrderingEntry o) {
        if (o == null)
            return -1;

        if (len != o.len) {
            return o.len - len;
        } else {
            return o.key.compareTo(key);
        }
    }

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @return the len
     */
    public int getLen() {
        return len;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }
}
