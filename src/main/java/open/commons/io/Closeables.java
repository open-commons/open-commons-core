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
 * Date  : 2018. 9. 11. 오전 11:49:50
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.io;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.annotation.Resource;

import open.commons.utils.ArrayUtils;

/**
 * 다수 개의 {@link Closeable}를 한번에 {@link #close()} 할 수 있도록 지원하는 클래스.
 * 
 * <pre>
 * [개정이력]
 *      날짜      | 작성자   |   내용
 * ------------------------------------------
 * 2018. 9. 10.     박준홍     최초 작성
 * 2019. 2. 19      박준홍     {@link Resource} 추가
 * </pre>
 * 
 * @since 2018. 9. 10.
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
@Resource
public class Closeables implements Closeable {

    private ArrayList<Closeable> closeables = new ArrayList<>();

    /** 예외 발생 여부 */
    private final boolean quietly;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 9. 10.     박준홍         최초 작성
     * </pre>
     *
     * @since 2018. 9. 10.
     */
    private Closeables(boolean quietly) {
        this.quietly = quietly;
    }

    public void addAll(Closeable... closeables) {
        if (closeables == null) {
            return;
        }

        if (this.closeables == null) {
            this.closeables = new ArrayList<>();
        }

        this.closeables.addAll(Arrays.asList(closeables));
    }

    /**
     * @see java.io.Closeable#close()
     */
    @Override
    public void close() throws IOException {

        if (quietly) {
            closeQuietly();
        } else {
            for (Closeable c : closeables) {
                if (c != null) {
                    c.close();
                }
            }
        }

        closeables.clear();
    }

    private void closeQuietly() {
        for (Closeable c : closeables) {
            if (c != null) {
                try {
                    c.close();
                } catch (IOException ignored) {
                }
            }
        }
    }

    /**
     * 데이터를 배열의 앞쪽에 추가합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 9. 10.     박준홍         최초 작성
     * </pre>
     *
     * @param closeables
     *
     * @since 2018. 9. 10.
     */
    public void prepend(Closeable... closeables) {
        if (closeables == null) {
            return;
        }

        // 입력데이타 순서 뒤집기
        closeables = ArrayUtils.reverse(closeables);

        if (this.closeables == null) {
            this.closeables = new ArrayList<>();
        } else {
            // 기존 데이타 순서 뒤집기
            Collections.reverse(this.closeables);
        }

        // 뒤집힌 순서대로 추가
        this.closeables.addAll(Arrays.asList(closeables));

        // 전체 데이타 순서 뒤집기
        Collections.reverse(this.closeables);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 9. 11.     박준홍         최초 작성
     * </pre>
     *
     * @param closeable
     *            {@link Closeable} 객체
     * @param quiet
     *            예외발생 여부
     * @return
     *
     * @since 2018. 9. 11.
     */
    public static Closeable closeable(Closeable closeable, boolean quiet) {
        return new ThrowableCloseable(closeable, quiet);
    }

    /**
     * {@link Closeable}를 보관하는 객체를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 9. 10.     박준홍         최초 작성
     * </pre>
     *
     * @param quietly
     *            예외발생 여부
     * @param closeables
     *            {@link Closeable} ...
     * @return
     *
     * @since 2018. 9. 10.
     */
    public static Closeables list(boolean quietly, Closeable... closeables) {
        Closeables c = new Closeables(quietly);
        c.addAll(closeables);

        return c;
    }

    /**
     * {@link Closeable}를 보관하는 객체를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 9. 10.     박준홍         최초 작성
     * </pre>
     *
     * @param closeables
     *            {@link Closeable} ...
     * @return
     *
     * @since 2018. 9. 10.
     */
    public static Closeables list(Closeable... closeables) {
        Closeables c = new Closeables(false);
        c.addAll(closeables);

        return c;
    }

    public static class ThrowableCloseable implements Closeable {

        private final Closeable closeable;

        private final boolean quiet;

        /**
         * <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜      | 작성자   |   내용
         * ------------------------------------------
         * 2018. 9. 11.     박준홍         최초 작성
         * </pre>
         *
         * @since 2018. 9. 11.
         */
        private ThrowableCloseable(Closeable closeable, boolean quiet) {
            if (closeable == null) {
                throw new IllegalArgumentException("Closeable instance MUST NOT be null.");
            }

            this.closeable = closeable;
            this.quiet = quiet;
        }

        /**
         * @see java.io.Closeable#close()
         */
        @Override
        public void close() throws IOException {

            try {
                closeable.close();
            } catch (Exception e) {
                if (!this.quiet) {
                    throw e;
                }
            }
        }
    }
}