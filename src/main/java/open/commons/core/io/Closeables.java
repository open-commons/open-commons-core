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

package open.commons.core.io;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

import jakarta.annotation.Resource;

import org.jspecify.annotations.Nullable;

import open.commons.core.utils.ArrayUtils;
import open.commons.core.utils.ObjectUtils;

/**
 * 다수 개의 {@link AutoCloseable}를 한번에 {@link #close()} 할 수 있도록 지원하는 클래스.
 * 
 * <pre>
 * [개정이력]
 *      날짜      | 작성자   |   내용
 * ------------------------------------------
 * 2018. 9. 10.     parkjunohng77@gmail.com     최초 작성
 * 2019. 2. 19.     parkjunohng77@gmail.com     {@link Resource} 추가
 * 2022. 5. 31.     parkjunohng77@gmail.com     implements interface 변경 (Closeable -> AutoCloseable)
 * </pre>
 * 
 * @since 2018. 9. 10.
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */
@Resource
public class Closeables implements AutoCloseable {

    private ArrayList<AutoCloseable> closeables = new ArrayList<>();

    /** 예외 발생 여부 */
    private final boolean quietly;

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
    private Closeables(boolean quietly) {
        this.quietly = quietly;
    }

    /**
     * @throws NullPointerException
     *             파라미터({@code closeables})에 'null'이 포함된 경우 발생.
     */
    public void addAll(AutoCloseable @Nullable... closeables) {
        if (closeables == null) {
            return;
        }

        ObjectUtils.requireNonNulls((Object[]) closeables);

        this.closeables.addAll(Arrays.asList(closeables));
    }

    /**
     * @see java.lang.AutoCloseable#close()
     */
    @Override
    public void close() throws Exception {

        if (quietly) {
            closeQuietly();
        } else {
            for (AutoCloseable c : closeables) {
                if (c != null) {
                    c.close();
                }
            }
        }

        closeables.clear();
    }

    private void closeQuietly() {
        for (AutoCloseable c : closeables) {
            if (c != null) {
                try {
                    c.close();
                } catch (Exception ignored) {
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
     * 2018. 9. 10.     parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param closeables
     *
     * @since 2018. 9. 10.
     */
    public void prepend(AutoCloseable @Nullable... closeables) {
        if (closeables == null) {
            return;
        }

        // 기존 데이타 순서 뒤집기
        Collections.reverse(this.closeables);

        // 입력데이타 순서 뒤집기
        closeables = ArrayUtils.reverse(closeables);

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
     * 2018. 9. 11.     parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param closeable
     *            {@link Closeable} 객체
     * @param quiet
     *            예외발생 여부
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code closeable})가 {@code null}인 경우 발생.
     *
     * @since 2018. 9. 11.
     */
    public static AutoCloseable closeable(AutoCloseable closeable, boolean quiet) {
        Objects.requireNonNull(closeable);

        return new ThrowableCloseable(closeable, quiet);
    }

    /**
     * {@link Closeable}를 보관하는 객체를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 9. 10.     parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param closeables
     *            {@link Closeable} ...
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code closeables})가 {@code null}인 경우 발생.
     *
     * @since 2018. 9. 10.
     */
    public static Closeables list(AutoCloseable... closeables) {
        ObjectUtils.requireNonNulls((Object[]) closeables);

        Closeables c = new Closeables(false);
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
     * 2018. 9. 10.     parkjunohng77@gmail.com         최초 작성
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
    public static Closeables list(boolean quietly, AutoCloseable... closeables) {
        Closeables c = new Closeables(quietly);
        c.addAll(closeables);

        return c;
    }

    public static class ThrowableCloseable implements AutoCloseable {

        private final AutoCloseable closeable;

        private final boolean quiet;

        /**
         * <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜      | 작성자   |   내용
         * ------------------------------------------
         * 2018. 9. 11.     parkjunohng77@gmail.com         최초 작성
         * </pre>
         *
         * @since 2018. 9. 11.
         */
        private ThrowableCloseable(AutoCloseable closeable, boolean quiet) {
            this.closeable = closeable;
            this.quiet = quiet;
        }

        /**
         * @see java.io.AutoCloseable#close()
         */
        @Override
        public void close() throws Exception {

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