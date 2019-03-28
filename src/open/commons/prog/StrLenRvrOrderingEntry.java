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

/**
* @since 2011. 09. 18.
*/
package open.commons.prog;

/**
 * String Length Reverse Ordering Entry
 * 
 * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
 * @since 2011. 09. 18.
 * 
 */
public class StrLenRvrOrderingEntry implements Comparable<StrLenRvrOrderingEntry> {

    private final String key;

    private final int len;

    private final String value;

    public StrLenRvrOrderingEntry(String k, String v) {
        key = k.trim();
        len = k.length();

        value = v;
    }

    /**
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(StrLenRvrOrderingEntry o) {
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
