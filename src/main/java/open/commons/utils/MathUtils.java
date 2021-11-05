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
 * This file is generated under this project, "open-commons-core".
 *
 * Date  : 2017. 12. 4. 오후 4:57:19
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * 
 * @since 2017. 12. 4.
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public class MathUtils {

    /**
     * 
     * @since 2017. 12. 4.
     */
    private MathUtils() {
    }

    /**
     * 정렬된 데이타 중에서 중앙값(media)을 구하는데 사용되는 데이타를 제공한다. <br>
     * <ul>
     * 데이타의 개수(size)가
     * <li>짝수인 경우: (size / 2 - 1), size / 2 번째
     * <li>홀수인 경우: size / 2 번째
     * </ul>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 1. 9.      박준홍         최초 작성
     * </pre>
     *
     * @param data
     *            정렬된 데이터.
     * @return
     *
     * @since 2019. 1. 9.
     */
    public static <N> List<N> getMedianEntries(List<N> data) {

        int size = data.size();

        // len == 0
        if (size < 1) {
            throw new IllegalArgumentException("no data. size=0");
        }

        List<N> l = new ArrayList<>();

        // len == 1
        if (size == 1) {
            l.add(data.get(0));
        } else {
            // odd
            if ((size & 1) == 1) {
                l.add(data.get(size / 2));
            } else
            // even
            {
                int index = size / 2 - 1;

                l.add(data.get(index));
                l.add(data.get(index + 1));
            }
        }

        return l;
    }

    /**
     * 최대값을 제공한다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2017. 12. 4.		박준홍			최초 작성
     * </pre>
     *
     * @param values
     * @return
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2017. 12. 4.
     */
    public static <T extends Comparable<T>> T max(Collection<T> values) {

        Optional<T> max = values.stream().max(new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                return o1.compareTo(o2);
            }
        });

        return max.get();
    }

    /**
     * 최대값을 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 9. 2.		박준홍			최초 작성
     * </pre>
     *
     * @param <T>
     * @param values
     * @return
     *
     * @since 2020. 9. 2.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    @SafeVarargs
    public static <T extends Comparable<T>> T max(T... values) {
        return max(Arrays.asList(values));
    }

    /**
     * 최소값을 제공한다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2017. 12. 4.		박준홍			최초 작성
     * </pre>
     *
     * @param values
     * @return
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2017. 12. 4.
     */
    public static <T extends Comparable<T>> T min(Collection<T> values) {

        Optional<T> min = values.stream().min(new Comparator<T>() {

            @Override
            public int compare(T o1, T o2) {
                return o1.compareTo(o2);
            }
        });

        return min.get();
    }

    /**
     * 최소값을 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 9. 2.		박준홍			최초 작성
     * </pre>
     *
     * @param <T>
     * @param values
     * @return
     *
     * @since 2020. 9. 2.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    @SafeVarargs
    public static <T extends Comparable<T>> T min(T... values) {
        return max(Arrays.asList(values));
    }
}
