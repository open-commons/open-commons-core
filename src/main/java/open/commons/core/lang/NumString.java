/*
 * Copyright 2020 Park Jun-Hong_(parkjunhong77@gmail.com)
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
 * Date  : 2020. 12. 17. 오후 1:19:03
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.core.lang;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

import open.commons.core.utils.AssertUtils2;
import open.commons.core.utils.CollectionUtils;

/**
 * 
 * @since 2020. 12. 17.
 * @version 1.8.0
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public class NumString<N extends Number> implements CharSequence {

    private final N number;

    private String string;

    /**
     * 
     * @param number
     *            TODO
     * @since 2020. 12. 17.
     */
    public NumString(N number) {
        AssertUtils2.notNull(null, number, null);
        this.number = number;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 12. 17.		박준홍			최초 작성
     * </pre>
     *
     * @param index
     * @return
     *
     * @since 2020. 12. 17.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see java.lang.CharSequence#charAt(int)
     */
    @Override
    public char charAt(int index) {
        return string().charAt(index);
    }

    /**
     *
     * @return the number
     *
     * @since 2020. 12. 17.
     */
    public N getNumber() {
        return number;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 12. 17.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2020. 12. 17.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see java.lang.CharSequence#length()
     */
    @Override
    public int length() {
        return string().length();
    }

    private String string() {
        return string != null ? string : (string = this.number.toString());
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 12. 17.		박준홍			최초 작성
     * </pre>
     *
     * @param start
     * @param end
     * @return
     *
     * @since 2020. 12. 17.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see java.lang.CharSequence#subSequence(int, int)
     */
    @Override
    public CharSequence subSequence(int start, int end) {
        return string().subSequence(start, end);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 12. 17.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2020. 12. 17.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return string();
    }

    /**
     * 숫자형 데이터를 문자형 데이터를 변경하여 제공합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 12. 17.		박준홍			최초 작성
     * </pre>
     *
     * @param <N>
     * @param nums
     * @return
     *
     * @since 2020. 12. 17.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <N extends Number> List<NumString<N>> sequence(Collection<N> nums) {
        return sequence(n -> new NumString<N>(n), nums);
    }

    /**
     * 숫자형 데이터를 문자형 데이터를 변경하여 제공합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 12. 17.		박준홍			최초 작성
     * </pre>
     *
     * @param <T>
     * @param f
     * @param nums
     * @return
     *
     * @since 2020. 12. 17.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <T extends Number, N extends Number> List<NumString<N>> sequence(Function<T, NumString<N>> f, Collection<T> nums) {
        ArrayList<NumString<N>> list = new ArrayList<>();
        for (T n : nums) {
            list.add(f.apply(n));
        }

        return list;
    }

    /**
     * 숫자형 데이터를 문자형 데이터를 변경하여 제공합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 12. 17.		박준홍			최초 작성
     * </pre>
     *
     * @param <T>
     * @param f
     * @param nums
     * @return
     *
     * @since 2020. 12. 17.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    @SafeVarargs
    public static <T extends Number, N extends Number> List<NumString<N>> sequence(Function<T, NumString<N>> f, T... nums) {
        return sequence(f, CollectionUtils.newList(nums));
    }

    /**
     * 숫자형 데이터를 문자형 데이터를 변경하여 제공합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 12. 17.		박준홍			최초 작성
     * </pre>
     *
     * @param <N>
     * @param nums
     * @return
     *
     * @since 2020. 12. 17.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    @SafeVarargs
    public static <N extends Number> List<NumString<N>> sequence(N... nums) {
        return sequence(CollectionUtils.newList(nums));
    }
}
