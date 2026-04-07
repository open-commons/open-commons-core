/*
 * Copyright 2021 Park Jun-Hong (parkjunhong77@gmail.com)
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
 * Date  : 2021. 12. 3. 오후 1:28:22
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package open.commons.core.stream;

import java.util.Arrays;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;

import org.jspecify.annotations.Nullable;

import open.commons.core.utils.ConvertUtils;
import open.commons.core.utils.ObjectUtils;

/**
 * Object의 {@link Class}를 제공하는 객체.
 * 
 * @since 2021. 12. 3.
 * @version 0.3.0
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */
public class ClassSpliterator implements Spliterator<Class<?>> {

    private final boolean forceToPrimitive;
    private final Object[] data;
    private int pos = 0;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 12. 3.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     * 
     * @param forceToPrimitive
     *            Wrapper {@link Class}를 강제로 Primitive 타입으로 변경할지 여부.
     * @param data
     *            데이터
     * 
     * @throws NullPointerException
     *             파라미터({@code data})가 {@code null}인 경우 발생.
     * 
     * @since 2021. 12. 3.
     * @version 0.3.0
     * 
     */
    // 아래 내용에 적용됨.
    // - ObjectUtils.requireNonNulls((Object[]) data);
    // [PATCH] 배열 공변성/가변성에 대한 IDE 분석기의 오탐 우회
    // [TODO] 향후 IDE의 배열 데이터 흐름 분석이 고도화되거나 JSpecify가 완벽히 지원되면 '제거'
    @SuppressWarnings("null")
    public ClassSpliterator(boolean forceToPrimitive, Object... data) {
        ObjectUtils.requireNonNulls((Object[]) data);

        this.forceToPrimitive = forceToPrimitive;
        this.data = data;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 12. 3.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     * 
     * @param data
     *            데이터
     * 
     * @throws NullPointerException
     *             파라미터({@code data})가 {@code null}인 경우 발생.
     * 
     * @since 2021. 12. 3.
     * @version 0.3.0
     * 
     */
    public ClassSpliterator(Object... data) {
        this(false, data);
    }

    /**
     *
     * @since 2021. 12. 3.
     * @version 0.3.0
     * 
     *
     * @see java.util.Spliterator#characteristics()
     */
    @Override
    public int characteristics() {
        return Arrays.stream(this.data).spliterator().characteristics();
    }

    /**
     *
     * @since 2021. 12. 3.
     * @version 0.3.0
     * 
     *
     * @see java.util.Spliterator#estimateSize()
     */
    @Override
    public long estimateSize() {
        return this.data.length;
    }

    /**
     * @throws NullPointerException
     *             파라미터({@code action})가 {@code null}인 경우 발생.
     *
     * @since 2021. 12. 3.
     * @version 0.3.0
     * 
     * @see java.util.Spliterator#tryAdvance(java.util.function.Consumer)
     */
    @SuppressWarnings("null")
    @Override
    public boolean tryAdvance(Consumer<? super Class<?>> action) {
        Objects.requireNonNull(action);

        if (pos < this.data.length) {
            action.accept(this.data[pos] != null //
                    ? forceToPrimitive //
                            ? ConvertUtils.translateToPrimitive(this.data[pos].getClass()) //
                            : this.data[pos].getClass() //
                    : null);
            pos++;
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @since 2021. 12. 3.
     * @version 0.3.0
     * 
     *
     * @see java.util.Spliterator#trySplit()
     */
    @Override
    public @Nullable Spliterator<Class<?>> trySplit() {
        return null;
    }
}
