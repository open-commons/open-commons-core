/*
 * Copyright 2018 Park Jun-Hong (parkjunhong77@gmail.com)
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
 * Date  : 2018. 9. 10. 오후 4:37:14
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.core.io;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Consumer;

import javax.annotation.Resource;

/**
 * 
 * <pre>
 * [개정이력]
 *      날짜      | 작성자   |   내용
 * ------------------------------------------
 * 2018. 9. 10.     parkjunohng77@gmail.com     최초 작성
 * 2019. 2. 19      parkjunohng77@gmail.com     {@link Resource} 추가
 * </pre>
 * 
 * @since 2018. 9. 10.
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
@Resource
public class Consumers<T> implements Closeable {

    private final Consumer<T> consumer;

    private ArrayList<T> resources = new ArrayList<>();

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 9. 10.     parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @since 2018. 9. 10.
     */
    public Consumers(Consumer<T> consumer) {
        this.consumer = consumer;
    }

    public void add(T removable) {
        if (removable == null) {
            return;
        }

        if (this.resources == null) {
            this.resources = new ArrayList<>();
        }

        this.resources.add(removable);
    }

    public void addAll(Collection<T> removables) {
        if (removables == null) {
            return;
        }

        if (this.resources == null) {
            this.resources = new ArrayList<>();
        }

        this.resources.addAll(removables);
    }

    @SafeVarargs
    public final void addAll(T... removables) {
        if (removables == null) {
            return;
        }

        if (this.resources == null) {
            this.resources = new ArrayList<>();
        }

        this.resources.addAll(Arrays.asList(removables));
    }

    /**
     * @see java.io.Closeable#close()
     */
    @Override
    public void close() throws IOException {
        for (T t : resources) {
            consumer.accept(t);
        }
    }

}
