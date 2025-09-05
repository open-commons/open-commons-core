/*
 * Copyright 2019 Park Jun-Hong (parkjunhong77@gmail.com)
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
 * Date  : 2019. 1. 22. 오후 2:34:11
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.core.collection;

import java.util.Collection;
import java.util.Comparator;
import java.util.Vector;

/**
 * List using Binary Sort.
 * 
 * @since 2025. 03. 11 from {@link open.commons.core.util.BinarySortedList}
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public class BinarySortedList<E extends Comparable<E>> extends Vector<E> {

    private static final long serialVersionUID = 1L;

    private final Comparator<E> DEFAULT_COMPARATOR = new Comparator<E>() {
        @Override
        public int compare(E o1, E o2) {
            return o1.compareTo(o2);
        }
    };

    private boolean asc = true;

    private Comparator<E> comparator;

    public BinarySortedList() {
        this(true, null);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 1. 9.      parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param asc
     *            오름차순 정렬 여부
     * @since 2019. 1. 9.
     */
    public BinarySortedList(boolean asc) {
        this(asc, null);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 1. 9.      parkjunohng77@gmail.com         최초 작성
     * </pre>
     * 
     * @param asc
     *            오름차순 정렬 여부
     * @param comparator
     *            데이터 비교 연산자
     *
     * @since 2019. 1. 9.
     */
    public BinarySortedList(boolean asc, Comparator<E> comparator) {
        init(asc, comparator);
    }

    /**
     * @param c
     * @since 2019. 1. 22.
     */
    public BinarySortedList(Collection<? extends E> c) {
        this(true, null);

        boolean inserted = addAll(c);

        if (!inserted) {
            throw new IllegalStateException("fail to add all. c=" + c);
        }
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 1. 9.      parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param comparator
     *            데이터 비교 연산자
     * @since 2019. 1. 9.
     */
    public BinarySortedList(Comparator<E> comparator) {
        this(true, comparator);
    }

    /**
     * @param initialCapacity
     * @since 2019. 1. 22.
     */
    public BinarySortedList(int initialCapacity) {
        super(initialCapacity);
        init(true, null);
    }

    /**
     * @param initialCapacity
     * @param capacityIncrement
     * @since 2019. 1. 22.
     */
    public BinarySortedList(int initialCapacity, int capacityIncrement) {
        super(initialCapacity, capacityIncrement);
        init(true, null);
    }

    /**
     * @see java.util.ArrayList#add(java.lang.Object)
     */
    @Override
    public synchronized boolean add(E e) {

        if (e == null) {
            return false;
        }

        int index = getIndex(e, 0, size());
        super.add(index, e);
        return true;
    }

    /**
     * @see java.util.ArrayList#add(int, java.lang.Object)
     */
    @Override
    public void add(int index, E element) {
        throw new UnsupportedOperationException("CANNOT set an index of an element. Use add(E)");
    }

    /**
     * @see java.util.ArrayList#addAll(java.util.Collection)
     */
    @Override
    public synchronized boolean addAll(Collection<? extends E> c) {
        if (c == null || c.isEmpty()) {
            return false;
        }

        boolean modified = false;
        for (E e : c) {
            modified |= add(e);
        }
        return modified;
    }

    /**
     * @see java.util.ArrayList#addAll(int, java.util.Collection)
     */
    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException("CANNOT set an index of an element. Use addAll(Collection<E>)");
    }

    /**
     * 정렬하기 위한 인덱스를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 1. 8.      parkjunohng77@gmail.com         최초 작성
     * 2025. 3. 10.     parkjunohng77@gmail.com     ChatGTP를 통한 성능 검증으로 구현 수정
     * </pre>
     *
     * @param e
     *            새로운 데이타
     * @param low
     *            나눌 데이터의 첫번째 데이터 인덱스
     * @param high
     *            나눌 데이터 크기
     * @return 데이터를 추가할 위치. -1 인 경우 데이터를 추가하지 않음.
     *
     * @since 2019. 1. 8.
     */
    private int getIndex(E e, int low, int high) {
        if (e == null) {
            return -1;
        }

        while (low < high) {
            // 중간 위치 계산
            int mid = low + (high - low) / 2;
            E midVal = get(mid);
            int cmp = comparator.compare(midVal, e) * (this.asc ? 1 : -1);

            // 새 값이 크면 오른쪽 탐색
            if (cmp < 0) {
                low = mid + 1;
            } else
            // 새 값이 작거나 같으면 왼쪽 탐색
            {
                high = mid;
            }
        }
        // 최적의 삽입 위치 반환
        return low;
    }

    private void init(boolean asc, Comparator<E> comparator) {
        this.asc = asc;
        this.comparator = comparator != null ? comparator : DEFAULT_COMPARATOR;
    }

    /**
     * @see java.util.Vector#insertElementAt(java.lang.Object, int)
     * 
     * @since 2019. 4. 12.
     * @version 1.6.5
     */
    @Override
    public final synchronized void insertElementAt(E e, int index) {
        throw new UnsupportedOperationException("CANNOT set an index of an element. Use add(E)");
    }

}
