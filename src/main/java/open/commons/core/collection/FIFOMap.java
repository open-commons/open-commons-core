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
* Date  : 2012. 11. 6. 오후 4:35:32
*
* Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
*
*/
package open.commons.core.collection;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.jspecify.annotations.Nullable;

/**
 * 입력된 순서대로 정렬되는 {@link Map}
 * 
 * @param <K>
 * @param <V>
 * 
 * @since 2012. 11. 6.
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
public class FIFOMap<K, V> implements Map<K, V> {

    private AtomicInteger seqSeed = new AtomicInteger();

    private Map<K, Integer> keys = new HashMap<K, Integer>();

    /** ordered keys */
    private TreeMap<Integer, K> orderedKeys = new TreeMap<Integer, K>();

    /** ordered values */
    private TreeMap<Integer, V> orderedValues = new TreeMap<Integer, V>();

    public @Nullable Entry<K, V> atFirst() {
        Iterator<Entry<K, V>> itr = entrySet().iterator();

        return itr.hasNext() ? itr.next() : null;
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 5. 18.		parkjunhong77@gmail.com			데이터({@link #orderedValues}) 미삭제 버그 수정
     * </pre>
     *
     * @since 2021. 5. 18.
     * 
     * @see java.util.Map#clear()
     */
    @Override
    public synchronized void clear() {
        this.seqSeed.set(0);
        this.keys.clear();
        this.orderedKeys.clear();
        this.orderedValues.clear();
    }

    @Override
    public synchronized boolean containsKey(@Nullable Object key) {
        return this.orderedKeys.containsValue(key);
    }

    @Override
    public synchronized boolean containsValue(@Nullable Object value) {
        return this.orderedValues.containsValue(value);
    }

    @Override
    public synchronized Set<Map.Entry<K, V>> entrySet() {
        Set<Entry<K, V>> entrySet = new FIFOSet<Map.Entry<K, V>>();

        for (Entry<Integer, K> keyEntry : this.orderedKeys.entrySet()) {
            K key = keyEntry.getValue();
            V value = this.orderedValues.get(keyEntry.getKey());

            entrySet.add(new FIFOEntry(key, value));
        }

        return entrySet;
    }

    @SuppressWarnings("null")
    @Override
    public synchronized @Nullable V get(@Nullable Object key) {
        Integer sequence = this.keys.get(key);

        return sequence != null ? this.orderedValues.get(sequence) : null;
    }

    @Override
    public synchronized boolean isEmpty() {
        return this.orderedKeys.isEmpty();
    }

    @Override
    public synchronized Set<K> keySet() {
        Set<K> keySet = new FIFOSet<K>();

        keySet.addAll(orderedKeys.values());

        return keySet;
    }

    @SuppressWarnings("null")
    @Override
    public synchronized @Nullable V put(K key, V value) {
        if (key != null) {
            Integer sequence = null;
            if (keys.containsKey(key)) {
                sequence = this.keys.get(key);
                return this.orderedValues.put(sequence, value);
            } else {
                try {
                    sequence = this.seqSeed.getAndIncrement();

                    this.keys.put(key, sequence);
                    this.orderedKeys.put(sequence, key);
                    return this.orderedValues.put(sequence, value);
                } catch (Exception e) {
                    if (sequence != null) {
                        this.seqSeed.set(sequence);
                    }
                }
            }
        }

        return null;
    }

    @Override
    public synchronized void putAll(@Nullable Map<? extends K, ? extends V> m) {
        if (m != null) {
            for (Entry<? extends K, ? extends V> entry : m.entrySet()) {
                put(entry.getKey(), entry.getValue());
            }
        }
    }

    @SuppressWarnings("null")
    @Override
    public synchronized @Nullable V remove(@Nullable Object key) {
        if (key != null) {
            Integer sequence = this.keys.remove(key);

            if (sequence != null) {
                this.orderedKeys.remove(sequence);
                return this.orderedValues.remove(sequence);
            }
        }

        return null;
    }

    @Override
    public synchronized int size() {
        return this.orderedKeys.size();
    }

    // 아래 내용에 적용됨.
    // - StringBuilder.toString()();
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    @Override
    public String toString() {
        Iterator<Entry<K, V>> itr = entrySet().iterator();

        if (!itr.hasNext()) {
            return "{}";
        }

        StringBuilder sb = new StringBuilder();
        sb.append('{');

        Entry<K, V> entry = null;
        do {
            entry = itr.next();
            K key = entry.getKey();
            V value = entry.getValue();

            sb.append(key == this ? "(this Map)" : key);
            sb.append('=');
            sb.append(value == this ? "(this Map)" : value);

            if (itr.hasNext()) {
                sb.append(',');
                sb.append(' ');
            } else {
                break;
            }
        } while (true);

        sb.append('}');

        return sb.toString();
    }

    @Override
    public synchronized Collection<V> values() {
        return Objects.requireNonNull(this.orderedValues.values());
    }

    public class FIFOEntry implements Entry<K, V> {

        private K key;
        private V value;

        private FIFOEntry(K k, V v) {
            this.key = k;
            this.value = v;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

    }
}
