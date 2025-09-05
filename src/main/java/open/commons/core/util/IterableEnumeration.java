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
 * Date  : 2019. 6. 28. 오후 1:58:50
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.core.util;

import java.util.Enumeration;
import java.util.Iterator;

/**
 * {@link Enumeration}을 감싼 {@link Iterable} 클래스.
 * 
 * @since 2019. 6. 28.
 * @version
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public class IterableEnumeration<T> implements Iterable<T> {

    private Iterator<T> itr;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 6. 28.     parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @since 2019. 6. 28.
     * @version
     */
    public IterableEnumeration(Enumeration<T> enumeration) {
        this.itr = new EnumerationItr(enumeration);
    }

    /**
     * @see java.lang.Iterable#iterator()
     */
    @Override
    public Iterator<T> iterator() {
        return this.itr;
    }

    class EnumerationItr implements Iterator<T> {

        private final Enumeration<T> enumeration;

        /**
         * <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜      | 작성자   |   내용
         * ------------------------------------------
         * 2019. 6. 28.     parkjunohng77@gmail.com         최초 작성
         * </pre>
         *
         * @since 2019. 6. 28.
         * @version
         */
        public EnumerationItr(Enumeration<T> enumeration) {
            this.enumeration = enumeration;
        }

        /**
         * @see java.util.Iterator#hasNext()
         */
        @Override
        public boolean hasNext() {
            return this.enumeration.hasMoreElements();
        }

        /**
         * @see java.util.Iterator#next()
         */
        @Override
        public T next() {
            return this.enumeration.nextElement();
        }
    }
}
