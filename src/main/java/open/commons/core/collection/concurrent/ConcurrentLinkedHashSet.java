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
 * Date  : 2026. 4. 10. 오후 2:21:36
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package open.commons.core.collection.concurrent;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.SequencedSet;
import java.util.concurrent.locks.ReentrantLock;

import org.jspecify.annotations.Nullable;

/**
 * 입력된 순서(FIFO)를 보장하며, 양방향 접근이 가능한 스레드 안전(Thread-Safe)한 Set 컬렉션입니다.<br>
 * 멀티 스레드 환경이 아닌 경우나 메소드 내에서 사용하는 경우 {@link LinkedHashSet} 을 사용합니다.
 *
 * <pre>
 * [개정이력]
 * 날짜        | 작성자                    | 내용
 * ----------------------------------------------------------------------
 * 2012. 11. 6.     parkjunhong77@gmail.com     최초 작성 (FIFOSet)
 * 2026. 4. 10.     parkjunhong77@gmail.com     JDK 25 SequencedSet 표준 구현 및 클래스명 변경
 * </pre>
 *
 * @param <E>
 *            유지할 요소의 타입 (Null 허용 가능)
 *
 * @since 2026. 4. 10.
 * @version 3.0.0
 * @author parkjunhong77@gmail.com
 * 
 * @see LinkedHashSet
 */
public class ConcurrentLinkedHashSet<E extends @Nullable Object> implements SequencedSet<E> {

    private final ReentrantLock mutex = new ReentrantLock();

    private final LinkedHashSet<E> internalSet = new LinkedHashSet<>();

    @Override
    public boolean add(E e) {
        // E는 JSpecify에 의해 Null을 허용하므로 검증 생략
        this.mutex.lock();
        try {
            return this.internalSet.add(e);
        } finally {
            this.mutex.unlock();
        }
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        Objects.requireNonNull(c, "컬렉션(c)은 null일 수 없습니다.");

        this.mutex.lock();
        try {
            return this.internalSet.addAll(c);
        } finally {
            this.mutex.unlock();
        }
    }

    @Override
    public void addFirst(E e) {
        this.mutex.lock();
        try {
            this.internalSet.addFirst(e);
        } finally {
            this.mutex.unlock();
        }
    }

    @Override
    public void addLast(E e) {
        this.mutex.lock();
        try {
            this.internalSet.addLast(e);
        } finally {
            this.mutex.unlock();
        }
    }

    @Override
    public void clear() {
        this.mutex.lock();
        try {
            this.internalSet.clear();
        } finally {
            this.mutex.unlock();
        }
    }

    @Override
    public boolean contains(@Nullable Object o) {
        this.mutex.lock();
        try {
            return this.internalSet.contains(o);
        } finally {
            this.mutex.unlock();
        }
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        Objects.requireNonNull(c, "컬렉션(c)은 null일 수 없습니다.");

        this.mutex.lock();
        try {
            return this.internalSet.containsAll(c);
        } finally {
            this.mutex.unlock();
        }
    }

    @Override
    public E getFirst() {
        this.mutex.lock();
        try {
            return this.internalSet.getFirst();
        } finally {
            this.mutex.unlock();
        }
    }

    @Override
    public E getLast() {
        this.mutex.lock();
        try {
            return this.internalSet.getLast();
        } finally {
            this.mutex.unlock();
        }
    }

    @Override
    public boolean isEmpty() {
        this.mutex.lock();
        try {
            return this.internalSet.isEmpty();
        } finally {
            this.mutex.unlock();
        }
    }

    @Override
    public Iterator<E> iterator() {
        this.mutex.lock();
        try {
            return List.copyOf(this.internalSet).iterator();
        } finally {
            this.mutex.unlock();
        }
    }

    @Override
    public boolean remove(@Nullable Object o) {
        if (o == null) {
            return false;
        }

        this.mutex.lock();
        try {
            return this.internalSet.remove(o);
        } finally {
            this.mutex.unlock();
        }
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        Objects.requireNonNull(c, "컬렉션(c)은 null일 수 없습니다.");

        this.mutex.lock();
        try {
            return this.internalSet.removeAll(c);
        } finally {
            this.mutex.unlock();
        }
    }

    @Override
    public E removeFirst() {
        this.mutex.lock();
        try {
            return this.internalSet.removeFirst();
        } finally {
            this.mutex.unlock();
        }
    }

    @Override
    public E removeLast() {
        this.mutex.lock();
        try {
            return this.internalSet.removeLast();
        } finally {
            this.mutex.unlock();
        }
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        Objects.requireNonNull(c, "컬렉션(c)은 null일 수 없습니다.");

        this.mutex.lock();
        try {
            return this.internalSet.retainAll(c);
        } finally {
            this.mutex.unlock();
        }
    }

    @Override
    public SequencedSet<E> reversed() {
        this.mutex.lock();
        try {
            return Collections.unmodifiableSequencedSet(new LinkedHashSet<>(this.internalSet.reversed()));
        } finally {
            this.mutex.unlock();
        }
    }

    @Override
    public int size() {
        this.mutex.lock();
        try {
            return this.internalSet.size();
        } finally {
            this.mutex.unlock();
        }
    }

    @Override
    public Object[] toArray() {
        this.mutex.lock();
        try {
            return this.internalSet.toArray();
        } finally {
            this.mutex.unlock();
        }
    }

    @Override
    public <T> T[] toArray(T[] a) {
        Objects.requireNonNull(a, "배열(a)은 null일 수 없습니다.");

        this.mutex.lock();
        try {
            return this.internalSet.toArray(a);
        } finally {
            this.mutex.unlock();
        }
    }

    @Override
    public String toString() {
        this.mutex.lock();
        try {
            return this.internalSet.toString();
        } finally {
            this.mutex.unlock();
        }
    }
}