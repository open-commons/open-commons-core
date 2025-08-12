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
 * Date  : 2019. 10. 24. 오후 12:58:44
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.core.util;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Array을 Iterator로 감싼 클래스.
 * 
 * @since 2019. 10. 24.
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public class ArrayItr<E> implements Iterator<E>, Iterable<E> {

    private final E[] array;
    private int cursor = 0;
    private ReentrantLock lock = new ReentrantLock();

    public ArrayItr(E[] array) throws NullPointerException {
        this.array = array;
    }

    /**
     * @see java.util.Iterator#hasNext()
     */
    @Override
    public boolean hasNext() {
        try {
            lock.lock();
            return this.cursor < this.array.length;
        } finally {
            lock.unlock();
        }
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 11. 13.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2020. 11. 13.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see java.lang.Iterable#iterator()
     */
    @Override
    public Iterator<E> iterator() {
        return this;
    }

    /**
     * @see java.util.Iterator#next()
     */
    @Override
    public E next() throws NoSuchElementException {
        try {
            lock.lock();
            if (hasNext()) {
                return this.array[cursor++];
            } else {
                throw new NoSuchElementException();
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * 커서의 위치를 초기화 시킨다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 11. 13.		박준홍			최초 작성
     * </pre>
     *
     *
     * @since 2020. 11. 13.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public void reset() {
        try {
            lock.lock();
            this.cursor = 0;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 데이터 커서 위치를 초기화 시킨다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 10. 24.		박준홍			최초 작성
     * </pre>
     *
     *
     * @since 2019. 10. 24.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public void rollback() {
        try {
            lock.lock();
            this.cursor = 0;
        } finally {
            lock.unlock();
        }
    }
}
