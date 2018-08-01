/*
 * Copyright 2011 Park Jun-Hong_(fafanmama_at_naver_com)
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
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package open.commons.utils;

import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;

/**
 * 
 * @since 2017. 12. 4.
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
public class MathUtils {

    /**
     * 
     * @since 2017. 12. 4.
     */
    private MathUtils() {
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
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
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
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
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
}
