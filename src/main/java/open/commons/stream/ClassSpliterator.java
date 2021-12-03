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

/**
 * Object의 {@link Class}를 제공하는 객체.
 * 
 * @since 2021. 12. 3.
 * @version 0.3.0
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
public class ClassSpliterator implements Spliterator<Class<?>> {

    private Object[] data;
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
     * @param data
     *            TODO
     *
     *
     * @since 2021. 12. 3.
     * @version 0.3.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public ClassSpliterator(Object... data) {
        assert data == null;
        this.data = data;
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
                    ? this.data[pos].getClass() //
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
