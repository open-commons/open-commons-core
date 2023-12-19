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
* Date  : 2012. 11. 6. 오후 5:46:44
*
* Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
*
*/

package open.commons.core.collection;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 추가된 순서대로 정렬되는 {@link Set}
 * 
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 * @param <E>
 */
public class FIFOSet<E> implements Set<E> {

    private AtomicInteger seqSeed = new AtomicInteger();

    private Set<E> values = new HashSet<E>();

    private TreeMap<Integer, E> orderedValues = new TreeMap<Integer, E>();

    @Override
    public synchronized boolean add(E e) {
        boolean added = false;

        if (!values.contains(e)) {
            Integer sequence = null;
            try {
                sequence = seqSeed.getAndIncrement();
                this.orderedValues.put(sequence, e);

                this.values.add(e);

                added = true;
            } catch (Exception ex) {
                if (sequence != null) {
                    seqSeed.set(sequence);
                }
            }
        }
        return added;
    }

    @Override
    public synchronized boolean addAll(Collection<? extends E> c) {

        boolean added = false;

        for (E e : c) {
            added |= add(e);
        }
        return added;
    }

    @Override
    public synchronized void clear() {
        this.values.clear();
        this.orderedValues.clear();
    }

    @Override
    public synchronized boolean contains(Object o) {
        return this.values.contains(o);
    }

    @Override
    public synchronized boolean containsAll(Collection<?> c) {
        return this.values.containsAll(c);
    }

    private boolean eq(E e, Object o) {
        if (e != null && o != null) {
            return e.equals(o);
        } else if (e != null || o != null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public synchronized boolean isEmpty() {
        return this.values.isEmpty();
    }

    @Override
    public synchronized Iterator<E> iterator() {
        return this.orderedValues.values().iterator();
    }

    @Override
    public synchronized boolean remove(Object o) {
        boolean removed = false;

        // if (!values.contains(o)) {
        if (values.contains(o)) {
            Integer sequence = null;

            for (Entry<Integer, E> entry : this.orderedValues.entrySet()) {
                if (eq(entry.getValue(), o)) {
                    sequence = entry.getKey();

                    break;
                }
            }

            if (sequence != null) {
                this.orderedValues.remove(sequence);
                this.values.remove(sequence);

                removed = true;
            }
        }
        return removed;
    }

    @Override
    public synchronized boolean removeAll(Collection<?> c) {
        boolean removed = false;

        for (Object e : c) {
            removed |= remove(e);
        }

        return removed;
    }

    @Override
    public synchronized boolean retainAll(Collection<?> c) {
        return this.values.retainAll(c);
    }

    @Override
    public synchronized int size() {
        return this.values.size();
    }

    @Override
    public synchronized Object[] toArray() {
        return this.orderedValues.values().toArray();
    }

    @Override
    public synchronized <T> T[] toArray(T[] a) {
        return this.orderedValues.values().toArray(a);
    }

    @Override
    public String toString() {
        return this.orderedValues.values().toString();
    }

}