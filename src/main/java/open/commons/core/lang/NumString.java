/*
 * Copyright 2020 Park Jun-Hong (parkjunhong77@gmail.com)
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
import java.util.Objects;
import java.util.function.Function;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import open.commons.core.utils.AssertUtils2;
import open.commons.core.utils.CollectionUtils;

/**
 * 
 * @since 2020. 12. 17.
 * @version 1.8.0
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
public class NumString<N extends Number> implements CharSequence {

    private final @NonNull N number;

    private @Nullable String string;

    /**
     * 
     * @param number
     * 
     * @since 2020. 12. 17.
     */
    public NumString(N number) {
        Objects.requireNonNull(number);

        this.number = number;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 12. 17.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param index
     * @return
     *
     * @since 2020. 12. 17.
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
     * 2020. 12. 17.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2020. 12. 17.
     * 
     * @see java.lang.CharSequence#length()
     */
    @Override
    public int length() {
        return string().length();
    }

    // 아래 내용에 적용됨.
    // - Number.toString()
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    private String string() {
        if (string != null) {
            return string;
        } else {
            return string = this.number.toString();
        }
    }

    /**
     * @since 2020. 12. 17.
     *
     * @see java.lang.CharSequence#subSequence(int, int)
     */
    // 아래 내용에 적용됨.
    // - string().subSequence(start, end)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
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
     * 2020. 12. 17.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2020. 12. 17.
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
     * 2020. 12. 17.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <N>
     * @param nums
     * @return
     * 
     * @since 2020. 12. 17.
     * @version 1.8.0
     */
    public static <N extends Number> List<NumString<N>> sequence(Collection<N> nums) {
        Objects.requireNonNull(nums);

        return sequence(n -> new NumString<N>(n), nums);
    }

    /**
     * 숫자형 데이터를 문자형 데이터를 변경하여 제공합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 12. 17.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <T>
     * @param f
     * @param nums
     * @return
     *
     * @since 2020. 12. 17.
     * @version 1.8.0
     */
    public static <T extends Number, N extends Number> List<NumString<N>> sequence(Function<T, NumString<N>> f, Collection<T> nums) {
        Objects.requireNonNull(f);
        AssertUtils2.notExistNull(nums);

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
     * 2020. 12. 17.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <T>
     * @param f
     * @param nums
     * @return
     * @since 2020. 12. 17.
     * @version 1.8.0
     */
    @SafeVarargs
    public static <T extends Number, N extends Number> List<NumString<N>> sequence(Function<T, NumString<N>> f, T @Nullable... nums) {
        Objects.requireNonNull(f);

        return sequence(f, CollectionUtils.newList(nums));
    }

    /**
     * 숫자형 데이터를 문자형 데이터를 변경하여 제공합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 12. 17.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <N>
     * @param nums
     * @return
     *
     * @since 2020. 12. 17.
     * @version 1.8.0
     */
    @SafeVarargs
    public static <N extends Number> List<NumString<N>> sequence(N @Nullable... nums) {
        return sequence(CollectionUtils.newList(nums));
    }
}
