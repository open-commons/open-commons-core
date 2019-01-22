/*
 * Copyright 2019 Park Jun-Hong_(fafanmama_at_naver_com)
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
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package open.commons.util;

import java.util.Collection;
import java.util.Comparator;
import java.util.Vector;

/**
 * List using Binary Sort.
 * 
 * @since 2019. 1. 22.
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
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
     * 2019. 1. 9.      박준홍         최초 작성
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
     * 2019. 1. 9.      박준홍         최초 작성
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
     * 2019. 1. 9.      박준홍         최초 작성
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
    public boolean add(E e) {

        if (size() < 1) {
            return super.add(e);
        }

        int index = getIndex(e, size(), 0);
        if (index < 0) {
            return false;
        }

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
    public boolean addAll(Collection<? extends E> c) {
        if (c == null) {
            return false;
        }

        for (E e : c) {
            if (!add(e)) {
                return false;
            }
        }

        return true;
    }

    /**
     * @see java.util.ArrayList#addAll(int, java.util.Collection)
     */
    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException("CANNOT set an index of an element. Use addAll(Collection<E>)");
    }

    /**
     * 기존 데이터와 새로운 데이터의 오름차순 정렬 기준 비교 결과를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 1. 8.      박준홍         최초 작성
     * </pre>
     *
     * @param o
     *            기존 데이터
     * @param n
     *            새로운 데이터
     * @return
     *
     * @since 2019. 1. 8.
     */
    private int compare(E o, E n) {
        if (o != null && n != null) {
            return this.comparator != null ? //
                    this.comparator.compare(o, n) //
                    : o.compareTo(n);
        }

        if (o != null) {
            return -1;
        } else if (n != null) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * 정렬하기 위한 인덱스를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 1. 8.      박준홍         최초 작성
     * </pre>
     *
     * @param e
     *            새로운 데이타
     * @param size
     *            나눌 데이터 크기
     * @param pos
     *            나눌 데이터의 첫번째 데이터 인덱스
     * @return 데이터를 추가할 위치. -1 인 경우 데이터를 추가하지 않음.
     *
     * @since 2019. 1. 8.
     */
    private int getIndex(E e, int size, int pos) {
        if (e == null) {
            return -1;
        }

        // head: pos ~ pos + q x 2
        int q = size / 2;
        // tail: pos + q x 2 + 1 ~ size
        int r = size % 2;

        //
        int c = -1;
        if (q > 0) {
            c = compare(get(pos + q - 1), e) * (asc ? 1 : -1);

            // 뒷쪽 영역에서 확인
            if (c < 0) {
                return getIndex(e, q + r, pos + q);
            } else
            // 현재에 추가
            if (c == 0) {
                return pos + q;
            } else
            // 앞쪽에서 확인
            {
                return getIndex(e, q, pos);
            }
        } else if (r > 0) {
            c = compare(get(pos + q), e) * (asc ? 1 : -1);

            if (c < 0) {
                return pos + q + 1;
            } else {
                return pos + q;
            }
        } else {
            return pos;
        }
    }

    private void init(boolean asc, Comparator<E> comparator) {
        this.asc = asc;
        this.comparator = comparator != null ? comparator : DEFAULT_COMPARATOR;
    }

}
