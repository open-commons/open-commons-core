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
*/
package open.commons.utils;

import java.util.Comparator;
import java.util.List;

/**
 * 정렬 관련 유틸리티. <br>
 * 
 * @since 2012. 02. 15.
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
public class OrderingUtils {

    /**
     * 주어진 데이터를 정렬된 리스트에 추가합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 1. 10.     박준홍         최초 작성
     * </pre>
     * 
     * @param e
     *            신규 데이터
     * @param list
     *            정렬된 데이터
     * @param asc
     *            데이터 정렬. (true: 오름차순, false: 내림차순)
     * @param comparator
     *            비교연산자
     * @return
     *
     * @since 2019. 1. 10.
     */
    public static <E extends Comparable<E>> void addToSortedList(E e, List<E> list, boolean asc, Comparator<E> comparator) {

        if (list == null) {
            throw new IllegalArgumentException("list is NULL.");
        }

        if (list.size() < 1) {
            list.add(e);
            return;
        }

        int index = getIndex(e, list, asc, comparator, list.size(), 0);

        if (index < 0) {
            return;
        } else {
            list.add(index, e);
        }
    }

    /**
     * 데이타의 비교 결과를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 1. 10.     박준홍         최초 작성
     * </pre>
     *
     * @param o
     *            기존 데이타
     * @param n
     *            신규 데이타
     * @param comparator
     *            비교 연산자. (nullable)
     * @return
     *
     * @since 2019. 1. 10.
     * 
     * @see Comparator#compare(Object, Object)
     * @see Comparable#compareTo(Object)
     */
    private static <E extends Comparable<E>> int compare(E o, E n, Comparator<E> comparator) {
        if (o != null && n != null) {
            return comparator != null ? //
                    comparator.compare(o, n) //
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
     * 정렬된 리스트에서 새로운 데이터가 추가될 index를 제공합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 1. 10.     박준홍         최초 작성
     * </pre>
     * 
     * @param e
     *            신규 데이터
     * @param list
     *            정렬된 데이터
     * @param asc
     *            데이터 정렬. (true: 오름차순, false: 내림차순)
     * @param comparator
     *            비교연산자
     * @return
     *
     * @since 2019. 1. 10.
     */
    public static <E extends Comparable<E>> int getBSearchIndex(E e, List<E> list, boolean asc, Comparator<E> comparator) {

        if (list == null) {
            throw new IllegalArgumentException("list is NULL.");
        }

        if (list.size() < 1) {
            return 0;
        }

        return getIndex(e, list, asc, comparator, list.size(), 0);
    }

    /**
     * 
     * 데이터가 추가될 Index를 제공합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 1. 10.     박준홍         최초 작성
     * </pre>
     *
     * @param e
     *            신규 데이터
     * @param list
     *            정렬된 데이터
     * @param asc
     *            데이터 정렬. (true: 오름차순, false: 내림차순)
     * @param comparator
     *            비교연산자
     * @param size
     *            데이터 개수
     * @param pos
     *            데이터 분리 시작 위치
     * @return
     *
     * @since 2019. 1. 10.
     */
    private static <E extends Comparable<E>> int getIndex(E e, List<E> list, boolean asc, Comparator<E> comparator, int size, int pos) {
        if (list == null) {
            throw new IllegalArgumentException("list is NULL.");
        }

        // head: pos ~ pos + q x 2
        int q = size / 2;
        // tail: pos + q x 2 + 1 ~ size
        int r = size % 2;

        //
        int c = -1;
        if (q > 0) {
            c = compare(list.get(pos + q - 1), e, comparator);

            // 뒷쪽 영역에서 확인
            if (c < 0) {
                return getIndex(e, list, asc, comparator, q + r, pos + q);
            } else
            // 현재에 추가
            if (c == 0) {
                return pos + q;
            } else
            // 앞쪽에서 확인
            {
                return getIndex(e, list, asc, comparator, q, pos);
            }
        } else if (r > 0) {
            c = compare(list.get(pos + q), e, comparator);

            if (c <= 0) {
                return pos + q + 1;
            } else {
                return pos + q;
            }
        } else {
            return pos;
        }
    }

    /**
     * 숫자를 순서표현으로 변환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2012. 02. 15.		박준홍			최초 작성
     * </pre>
     *
     * @param i
     * @return
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2019. 1. 15.
     */
    public static String intToOrdinal(int i) {

        switch (i) {
            case 1:
                return "1st";
            case 2:
                return "2nd";
            case 3:
                return "3rd";
            default:
                return i + "th";
        }
    }

}
