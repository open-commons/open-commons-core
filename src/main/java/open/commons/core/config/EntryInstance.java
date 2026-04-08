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
* This file is generated under this project, "open-commons-core".
*
* Date  : 2013. 5. 15. 오후 3:38:04
*
* Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
* 
*/

/**
 */
package open.commons.core.config;

import java.util.Map.Entry;
import java.util.Objects;

import org.jspecify.annotations.Nullable;

/**
 * 
 * @since 2013. 5. 15.
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
public class EntryInstance<K, V> implements Entry<K, V> {

    private K key;

    private @Nullable V value;

    /**
     */
    public EntryInstance(K key, @Nullable V value) {
        Objects.requireNonNull(key);

        this.key = key;
        this.value = value;
    }

    /**
     *
     * @since 2026. 3. 13.
     * @version 3.0.0
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        @SuppressWarnings("rawtypes")
        EntryInstance other = (EntryInstance) obj;
        return Objects.equals(key, other.key) && Objects.equals(value, other.value);
    }

    /**
     * 
     * @see java.util.Map.Entry#getKey()
     */
    @Override
    public K getKey() {
        return key;
    }

    /**
     * 
     * @see java.util.Map.Entry#getValue()
     */
    @SuppressWarnings("null")
    @Override
    public V getValue() {
        return value;
    }

    /**
     *
     * @since 2026. 3. 13.
     * @version 3.0.0
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }

    /**
     * 
     * @see java.util.Map.Entry#setValue(java.lang.Object)
     */
    @SuppressWarnings("null")
    @Override
    public V setValue(@Nullable V value) {
        @Nullable
        V old = this.value;
        this.value = value;

        return old;
    }
}
