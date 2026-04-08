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
import java.util.Objects;

import jakarta.annotation.Resource;

import org.jspecify.annotations.Nullable;

import open.commons.core.utils.ObjectUtils;

/**
 * 다수 개의 {@link AutoCloseable}를 한번에 {@link #close()} 할 수 있도록 지원하는 클래스.
 * 
 * <pre>
 * [개정이력]
 *      날짜      | 작성자   |   내용
 * ------------------------------------------
 * 2018. 9. 10.     parkjunhong77@gmail.com     최초 작성
 * 2019. 2. 19.     parkjunhong77@gmail.com     {@link Resource} 추가
 * 2022. 5. 31.     parkjunhong77@gmail.com     implements interface 변경 (Closeable -> AutoCloseable)
 * </pre>
 * 
 * @since 2018. 9. 10.
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
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
     * 2018. 9. 10.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @since 2018. 9. 10.
     */
    private Closeables(boolean quietly) {
        this.quietly = quietly;
    }

    /**
     */
    // 아래 내용에 적용됨.
    // - ObjectUtils.requireNonNulls((Object[]) closeables);
    // [PATCH] 배열 공변성/가변성에 대한 IDE 분석기의 오탐 우회
    // [TODO] 향후 IDE의 배열 데이터 흐름 분석이 고도화되거나 JSpecify가 완벽히 지원되면 '제거'
    @SuppressWarnings("null")
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
     * 자원 해제 객체들을 목록의 가장 앞쪽에 추가합니다. (LIFO 순서 보장용)
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2018. 9. 10.     parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.    parkjunhong77@gmail.com     리스트 뒤집기 제거 및 인덱스 삽입으로 성능 최적화
     * </pre>
     * 
     * @param closeables
     *            추가할 자원 해제 객체들 (가변 인자)
     * 
     * @since 2018. 9. 10.
     */
    public void prepend(AutoCloseable @Nullable... closeables) {
        // [1] 가드 클로즈
        if (closeables == null || closeables.length == 0) {
            return;
        }

        // [2] 성능 최적화: 전체 리스트를 뒤집지 않고,
        // 입력받은 데이터를 역순으로 리스트의 0번 인덱스에 순차적으로 삽입합니다.
        // 예: 입력 [A, B] -> B 삽입(0번), A 삽입(0번) => 결과 [A, B, 기존데이터...]
        for (int i = closeables.length - 1; i >= 0; i--) {
            AutoCloseable closeable = closeables[i];
            if (closeable != null) {
                this.closeables.add(0, closeable);
            }
        }
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 9. 11.     parkjunhong77@gmail.com         최초 작성
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
     * 2018. 9. 10.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param closeables
     *            {@link Closeable} ...
     * @return
     *
     * @since 2018. 9. 10.
     */
    // 아래 내용에 적용됨.
    // - ObjectUtils.requireNonNulls((Object[]) closeables);
    // [PATCH] 배열 공변성/가변성에 대한 IDE 분석기의 오탐 우회
    // [TODO] 향후 IDE의 배열 데이터 흐름 분석이 고도화되거나 JSpecify가 완벽히 지원되면 '제거'
    @SuppressWarnings("null")
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
     * 2018. 9. 10.     parkjunhong77@gmail.com         최초 작성
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
         * 2018. 9. 11.     parkjunhong77@gmail.com         최초 작성
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