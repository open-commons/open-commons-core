/*
 * Copyright 2021 Park Jun-Hong_(parkjunhong77@gmail.com)
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

package open.commons.stream;

import java.util.Arrays;
import java.util.Spliterator;
import java.util.function.Consumer;

import open.commons.utils.AssertUtils;
import open.commons.utils.ConvertUtils;

/**
 * Object의 {@link Class}를 제공하는 객체.
 * 
 * @since 2021. 12. 3.
 * @version 0.3.0
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
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
     * 2021. 12. 3.		박준홍			최초 작성
     * </pre>
     * 
     * @param forceToPrimitive
     *            Wrapper {@link Class}를 강제로 Primitive 타입으로 변경할지 여부.
     * @param data
     *            데이터
     * 
     * @since 2021. 12. 3.
     * @version 0.3.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public ClassSpliterator(boolean forceToPrimitive, Object... data) {
        AssertUtils.assertNulls(data);
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
     * 2021. 12. 3.		박준홍			최초 작성
     * </pre>
     * 
     * @param data
     *            데이터
     * 
     * @since 2021. 12. 3.
     * @version 0.3.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public ClassSpliterator(Object... data) {
        this(false, data);
    }

    /**
     *
     * @since 2021. 12. 3.
     * @version 0.3.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
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
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     *
     * @see java.util.Spliterator#estimateSize()
     */
    @Override
    public long estimateSize() {
        return this.data.length;
    }

    /**
     *
     * @since 2021. 12. 3.
     * @version 0.3.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     *
     * @see java.util.Spliterator#tryAdvance(java.util.function.Consumer)
     */
    @Override
    public boolean tryAdvance(Consumer<? super Class<?>> action) {

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
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     *
     * @see java.util.Spliterator#trySplit()
     */
    @Override
    public Spliterator<Class<?>> trySplit() {
        return null;
    }
}
