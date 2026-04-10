/*
 * Copyright 2026 Park Jun-Hong (parkjunhong77@gmail.com)
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
 * Date  : 2026. 4. 10. 오후 2:15:45
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package open.commons.core.collection.concurrent;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.SequencedCollection;
import java.util.SequencedMap;
import java.util.SequencedSet;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

import org.jspecify.annotations.Nullable;

/**
 * 입력된 순서(FIFO)를 보장하며, 양방향 접근이 가능한 스레드 안전(Thread-Safe)한 맵 컬렉션입니다.<br>
 * 멀티 스레드 환경이 아닌 경우나 메소드 내에서 사용하는 경우 {@link LinkedHashMap} 을 사용합니다.
 *
 * <pre>
 * [개정이력]
 * 날짜        | 작성자                    | 내용
 * ----------------------------------------------------------------------
 * 2012. 11. 6.     parkjunhong77@gmail.com     최초 작성 (FIFOMap)
 * 2026. 4. 10.     parkjunhong77@gmail.com     JDK 25 SequencedMap 표준 구현 및 클래스명 변경
 * </pre>
 *
 * @param <K>
 *            키의 타입 (Null 비허용)
 * @param <V>
 *            값의 타입 (Null 허용 가능)
 *
 * @since 2026. 4. 10.
 * @version 3.0.0
 * @author parkjunhong77@gmail.com
 * 
 * @see LinkedHashMap
 */
public class ConcurrentLinkedHashMap<K extends @Nullable Object, V extends @Nullable Object> implements SequencedMap<K, V> {

    private final ReentrantLock mutex = new ReentrantLock();

    private final LinkedHashMap<K, V> internalMap = new LinkedHashMap<>();

    @Override
    public void clear() {
        this.mutex.lock();
        try {
            this.internalMap.clear();
        } finally {
            this.mutex.unlock();
        }
    }

    @Override
    public boolean containsKey(@Nullable Object key) {
        this.mutex.lock();
        try {
            return this.internalMap.containsKey(key);
        } finally {
            this.mutex.unlock();
        }
    }

    @Override
    public boolean containsValue(@Nullable Object value) {
        this.mutex.lock();
        try {
            return this.internalMap.containsValue(value);
        } finally {
            this.mutex.unlock();
        }
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        this.mutex.lock();
        try {
            // [PATCH] Map.entry()는 null을 허용하지 않으므로 SimpleImmutableEntry 사용
            return Collections
                    .unmodifiableSet(this.internalMap.entrySet().stream().map(AbstractMap.SimpleImmutableEntry::new).collect(Collectors.toCollection(LinkedHashSet::new)));
        } finally {
            this.mutex.unlock();
        }
    }

    @Override
    public @Nullable Entry<K, V> firstEntry() {
        this.mutex.lock();
        try {
            return this.internalMap.firstEntry();
        } finally {
            this.mutex.unlock();
        }
    }

    // 아래 내용에 적용됨.
    // - @Nullable V
    // [PATCH] [IDE-Null] Eclipse JDT 분석기의 제네릭 & @NullMarked 치환 해석 오류 우회
    // [TODO] 향후 Eclipse IDE 정적 분석기가 JSpecify 제네릭 치환을 완벽히 지원하면 '제거'
    @SuppressWarnings("null")
    @Override
    public @Nullable V get(@Nullable Object key) {
        this.mutex.lock();
        try {
            return this.internalMap.get(key);
        } finally {
            this.mutex.unlock();
        }
    }

    @Override
    public boolean isEmpty() {
        this.mutex.lock();
        try {
            return this.internalMap.isEmpty();
        } finally {
            this.mutex.unlock();
        }
    }

    @Override
    public Set<K> keySet() {
        this.mutex.lock();
        try {
            // [PATCH] Set.copyOf()는 null을 허용하지 않으므로 LinkedHashSet으로 복사 후 불변 래핑
            return Collections.unmodifiableSet(new LinkedHashSet<>(this.internalMap.keySet()));
        } finally {
            this.mutex.unlock();
        }
    }

    @Override
    public @Nullable Entry<K, V> lastEntry() {
        this.mutex.lock();
        try {
            return this.internalMap.lastEntry();
        } finally {
            this.mutex.unlock();
        }
    }

    @Override
    public @Nullable Entry<K, V> pollFirstEntry() {
        this.mutex.lock();
        try {
            return this.internalMap.pollFirstEntry();
        } finally {
            this.mutex.unlock();
        }
    }

    @Override
    public @Nullable Entry<K, V> pollLastEntry() {
        this.mutex.lock();
        try {
            return this.internalMap.pollLastEntry();
        } finally {
            this.mutex.unlock();
        }
    }

    // 아래 내용에 적용됨.
    // - @Nullable V
    // [PATCH] [IDE-Null] Eclipse JDT 분석기의 제네릭 & @NullMarked 치환 해석 오류 우회
    // [TODO] 향후 Eclipse IDE 정적 분석기가 JSpecify 제네릭 치환을 완벽히 지원하면 '제거'
    @SuppressWarnings("null")
    @Override
    public @Nullable V put(K key, V value) {
        this.mutex.lock();
        try {
            return this.internalMap.put(key, value);
        } finally {
            this.mutex.unlock();
        }
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        Objects.requireNonNull(m, "m은 null일 수 없습니다.");

        this.mutex.lock();
        try {
            this.internalMap.putAll(m);
        } finally {
            this.mutex.unlock();
        }
    }

    // 아래 내용에 적용됨.
    // - @Nullable V
    // [PATCH] [IDE-Null] Eclipse JDT 분석기의 제네릭 & @NullMarked 치환 해석 오류 우회
    // [TODO] 향후 Eclipse IDE 정적 분석기가 JSpecify 제네릭 치환을 완벽히 지원하면 '제거'
    @SuppressWarnings("null")
    @Override
    public @Nullable V putFirst(K key, V value) {
        this.mutex.lock();
        try {
            return this.internalMap.putFirst(key, value);
        } finally {
            this.mutex.unlock();
        }
    }

    // 아래 내용에 적용됨.
    // - @Nullable V
    // [PATCH] [IDE-Null] Eclipse JDT 분석기의 제네릭 & @NullMarked 치환 해석 오류 우회
    // [TODO] 향후 Eclipse IDE 정적 분석기가 JSpecify 제네릭 치환을 완벽히 지원하면 '제거'
    @SuppressWarnings("null")
    @Override
    public @Nullable V putLast(K key, V value) {
        this.mutex.lock();
        try {
            return this.internalMap.putLast(key, value);
        } finally {
            this.mutex.unlock();
        }
    }

    // 아래 내용에 적용됨.
    // - @Nullable V
    // [PATCH] [IDE-Null] Eclipse JDT 분석기의 제네릭 & @NullMarked 치환 해석 오류 우회
    // [TODO] 향후 Eclipse IDE 정적 분석기가 JSpecify 제네릭 치환을 완벽히 지원하면 '제거'
    @SuppressWarnings("null")
    @Override
    public @Nullable V remove(@Nullable Object key) {
        this.mutex.lock();
        try {
            return this.internalMap.remove(key);
        } finally {
            this.mutex.unlock();
        }
    }

    @Override
    public SequencedMap<K, V> reversed() {
        this.mutex.lock();
        try {
            return Collections.unmodifiableSequencedMap(new LinkedHashMap<>(this.internalMap.reversed()));
        } finally {
            this.mutex.unlock();
        }
    }

    @Override
    public SequencedSet<Map.Entry<K, V>> sequencedEntrySet() {
        this.mutex.lock();
        try {
            LinkedHashSet<Map.Entry<K, V>> snapshot = new LinkedHashSet<>();
            for (Map.Entry<K, V> entry : this.internalMap.sequencedEntrySet()) {
                // [PATCH] null을 안전하게 지원하는 엔트리 복사
                snapshot.add(new AbstractMap.SimpleImmutableEntry<>(entry));
            }
            return Collections.unmodifiableSequencedSet(snapshot);
        } finally {
            this.mutex.unlock();
        }
    }

    @Override
    public SequencedSet<K> sequencedKeySet() {
        this.mutex.lock();
        try {
            return Collections.unmodifiableSequencedSet(new LinkedHashSet<>(this.internalMap.sequencedKeySet()));
        } finally {
            this.mutex.unlock();
        }
    }

    @Override
    public SequencedCollection<V> sequencedValues() {
        this.mutex.lock();
        try {
            return Collections.unmodifiableSequencedCollection(new ArrayList<>(this.internalMap.sequencedValues()));
        } finally {
            this.mutex.unlock();
        }
    }

    @Override
    public int size() {
        this.mutex.lock();
        try {
            return this.internalMap.size();
        } finally {
            this.mutex.unlock();
        }
    }

    @Override
    public String toString() {
        this.mutex.lock();
        try {
            return this.internalMap.toString();
        } finally {
            this.mutex.unlock();
        }
    }

    @Override
    public Collection<V> values() {
        this.mutex.lock();
        try {
            return Collections.unmodifiableCollection(new ArrayList<>(this.internalMap.values()));
        } finally {
            this.mutex.unlock();
        }
    }
}