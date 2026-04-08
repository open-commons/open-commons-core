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
* 
* This file is generated under this project, "open-commons-core".
*
* Date  : 2012. 3. 9. 오후 3:05:00
*
* Author: Park Jun-Hong (parkjunhong77@gmail.com)
* 
*/
package open.commons.core.path;

import java.io.File;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import org.jspecify.annotations.Nullable;

import open.commons.core.EquivalentFactory;
import open.commons.core.utils.ArrayUtils;
import open.commons.core.utils.AssertUtils2;
import open.commons.core.utils.ObjectUtils;
import open.commons.core.utils.StringUtils;

/**
 * 패스 정보를 보다 빠르게 처리하기 위해 제공되는 클래스 <BR>
 * 
 * @since 2012. 3. 9.
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */
public class PathElement implements Iterable<String> {

    // 아래 내용에 적용됨.
    // - File.separator
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    private static final String DEFAULT_SEPARATOR = File.separator;

    /** 구분자 */
    private String separator;

    /** 구분자 문자 배열 */
    private char[] sepChar;

    /** 경로들의 문자배열 */
    private char[] elems = new char[0];

    /**
     * 각 경로값의 길이에 대한 정보<br>
     * 2차 배열로 구성되어 있으며, 0: 이전까지의 경로 길이, 1: 현재 경로 길이, 2: 현재까지의 경로 길이
     */
    private int[][] elemLength = new int[0][];

    /** 경로 개수 */
    private int elemCount = 0;

    private final Object mtxPaths = new Object();

    private String @Nullable [] paths;

    /**
     * 시스템의 파일 경로 구분자를 사용하는 기본 생성자.
     * 
     * <BR>
     * 
     * @since 2012. 03. 12.
     * 
     * 
     * @see PathElement#PathElement(String)
     */
    public PathElement() {
        this(DEFAULT_SEPARATOR);
    }

    /**
     * 주어진 문자를 구분자로 사용해서 새로운 객체를 생성합니다.
     * 
     * @param delimiter
     *            경로 구분자 <BR>
     * @since 2012. 03. 15.
     * 
     */
    public PathElement(char separator) {
        this(Objects.requireNonNull( //
                String.valueOf(separator) //
        ));
    }

    /**
     * 
     * @param elems
     * @param elemLength
     * 
     *            <BR>
     * @since 2012. 03. 15.
     * 
     */
    public PathElement(char[] elems, int[][] elemLength) {
        this(DEFAULT_SEPARATOR, elems, elemLength);
    }

    /**
     * 기본 경로 구분자와 주어진 문자열을 경로값으로 하는 객체를 생성합니다.
     * 
     * @param elems
     *            경로를 구성하는 문자열들 <BR>
     * @since 2012. 03. 15.
     * 
     */
    // 아래 내용에 적용됨.
    // - ObjectUtils.requireNonNulls((Object[]) elems);
    // [PATCH] 배열 공변성/가변성에 대한 IDE 분석기의 오탐 우회
    // [TODO] 향후 IDE의 배열 데이터 흐름 분석이 고도화되거나 JSpecify가 완벽히 지원되면 '제거'
    @SuppressWarnings("null")
    public PathElement(String... elems) {
        ObjectUtils.requireNonNulls((Object[]) elems);

        this(DEFAULT_SEPARATOR);

        for (String elem : elems) {
            add(elem);
        }
    }

    /**
     * 생성자
     * 
     * @param delimiter
     *            경로 구분자.
     * 
     *            <BR>
     * @since 2012. 03. 12.
     * 
     */
    public PathElement(String separator) {
        Objects.requireNonNull(separator, "A delimiter must not be 'null': delimiter=null");

        if (StringUtils.isNullOrEmptyString(separator)) {
            throw new IllegalArgumentException("A delimiter must not be 'empty string': delimiter=" + separator);
        }

        this(separator, new char[0], new int[0][]);
    }

    /**
     * 
     * @param delimiter
     * @param elems
     *            경로값들의 문자열 배열
     * @param elemLength
     *            경로값들에 대한 인덱스 정보 배열
     * 
     *            <BR>
     * @since 2012. 03. 15.
     * 
     */
    // 아래 내용에 적용됨.
    // - this.sepChar = separator.toCharArray();
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public PathElement(String separator, char[] elems, int[][] elemLength) {
        AssertUtils2.notNulls("Parameters (char[] elems, int[][] elemLength) must not be null both of them: elems=" + Arrays.toString(elems) + ", elemLength=" + elemLength //
                , PathElementException.class //
                , elems, elemLength);

        if (elems.length != elemLength[elemLength.length - 1][2]) {
            throw new PathElementException("A character array of 'Path element's does not match to 'Path element's Length info: elems.length=" + elems.length + ", final-Length="
                    + elemLength[elemLength.length - 1][2]);
        }

        ObjectUtils.requireNonNulls((Object[]) elemLength);

        int[] el = null;
        for (int i = 0; i < elemLength.length; i++) {
            el = elemLength[i];

            if (el[0] + el[1] != el[2]) {
                throw new PathElementException("A total length value is not equal to the sum of a latest-length and a current string. info=" + Arrays.toString(el));
            }
        }

        this.separator = separator;
        this.sepChar = separator.toCharArray();

        this.elems = elems;
        this.elemLength = elemLength;
        this.elemCount = elemLength.length;
    }

    /**
     * 새로운 경로를 추가합니다.
     * 
     * @param path
     *            <BR>
     * @since 2012. 03. 12.
     * 
     */
    public void add(char path) {
        char[] npc = new char[] { path };
        synchronized (mtxPaths) {
            createPathValue(npc);
        }
    }

    /**
     * 새로운 경로를 추가합니다.
     * 
     * @param path
     *            <BR>
     * @since 2012. 03. 12.
     * 
     */
    // 아래 내용에 적용됨.
    // - createPathValue(npc); // char[] npc = path.toCharArray(); 에서 @NonNull이 보장되지 않음.
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public void add(@Nullable String path) {
        if (path != null) {
            char[] npc = path.toCharArray();
            synchronized (mtxPaths) {
                createPathValue(npc);
            }
        }
    }

    /**
     * 새로운 경로들을 추가합니다.
     * 
     * @param paths
     *            <BR>
     * @since 2012. 03. 15.
     * 
     * 
     * @see #add(String)
     */
    public void addAll(char @Nullable... paths) {
        if (paths != null) {
            synchronized (mtxPaths) {
                for (char path : paths) {
                    createPathValue(new char[] { path });
                }
            }
        }
    }

    /**
     * 새로운 경로들을 추가합니다.
     * 
     * @param paths
     *            <BR>
     * @since 2012. 03. 15.
     * 
     * 
     * @see #add(String)
     */
    public void addAll(@Nullable List<String> paths) {

        if (paths != null) {
            synchronized (mtxPaths) {
                for (String path : paths) {
                    createPathValue(Objects.requireNonNull( //
                            path.toCharArray() //
                    ));
                }
            }
        }
    }

    /**
     * 새로운 경로들을 추가합니다.
     * 
     * @param paths
     *            <BR>
     * @since 2012. 03. 15.
     * 
     * 
     * @see #add(String)
     */
    public void addAll(String @Nullable... paths) {

        if (paths != null) {
            synchronized (mtxPaths) {
                for (String path : paths) {
                    createPathValue(Objects.requireNonNull( //
                            path.toCharArray() //
                    ));
                }
            }
        }
    }

    private void checkIndex(int index) {
        if (elemCount < 1) {
            throw new IllegalStateException("There is no path element: elemCount=" + elemCount);
        }
        if (index < 0 || index > elemCount - 1) {
            throw new ArrayIndexOutOfBoundsException("An parameter(int index) must be in 0 ~ " + (elemCount - 1) + ". index=" + index);
        }
    }

    // 아래 내용에 적용됨.
    // - sepChar = separator.toCharArray();
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public void clear() {
        synchronized (mtxPaths) {

            /** 구분자 */
            separator = DEFAULT_SEPARATOR;

            /** 구분자 문자 배열 */
            sepChar = separator.toCharArray();

            /** 경로들의 문자배열 */
            elems = new char[0];

            /**
             * 각 경로값의 길이에 대한 정보<br>
             * 2차 배열로 구성되어 있으며, 0: 이전까지의 경로 길이, 1: 현재 경로 길이, 2: 현재까지의 경로 길이
             */
            elemLength = new int[0][];

            /** 경로 개수 */
            elemCount = 0;

            paths = null;
        }
    }

    /**
     * 
     * @return
     * 
     * @since 2012. 03. 22.
     * 
     * 
     * @see java.lang.Object#clone()
     */
    @SuppressWarnings("null")
    public PathElement clone() {
        PathElement clone = new PathElement(this.separator);

        clone.elemCount = this.elemCount;

        clone.elemLength = (int[][]) Array.newInstance(this.elemLength.getClass().getComponentType(), this.elemLength.length);
        System.arraycopy(this.elemLength, 0, clone.elemLength, 0, this.elemLength.length);

        clone.elems = new char[this.elems.length];
        System.arraycopy(this.elems, 0, clone.elems, 0, this.elems.length);

        return clone;
    }

    /**
     * 주어진 경로를 포함하고 있는지 여부를 반환합니다.
     * 
     * @param path
     * @return <BR>
     * @since 2012. 03. 12.
     * 
     */
    public boolean contains(@Nullable String path) {
        if (path != null) {
            int pl = path.length();

            // evaluated elemLength
            int[] evalElemLen = new int[elemLength.length];
            int evalLength = 0;
            // elemLength Index
            int eli = 0;
            for (int[] v : elemLength) {
                if (v[1] == pl) {
                    evalElemLen[evalLength++] = eli;
                }
                eli++;
            }

            if (evalLength < 1) {
                return false;
            }

            evalElemLen = Arrays.copyOf(evalElemLen, evalLength);
            int[] el = null;

            char[] elemChars = null;
            for (int eel : evalElemLen) {
                el = elemLength[eel];

                elemChars = new char[pl];

                System.arraycopy(elems, el[0], elemChars, 0, el[1]);

                if (equalsChars(Objects.requireNonNull( //
                        path.toCharArray() //
                ), elemChars)) {
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }
    }

    /**
     * New Path Character
     * 
     * @param npc
     *            <BR>
     * @since 2012. 03. 15.
     * 
     */
    private void createPathValue(char[] npc) {
        elems = ArrayUtils.merge(elems, npc);
        int latestLen = elemCount > 0 ? elemLength[elemCount - 1][2] : 0;
        int[] ell = new int[3];
        ell[0] = latestLen;
        ell[1] = npc.length;
        ell[2] = latestLen + npc.length;

        elemLength = ArrayUtils.add(elemLength, ell);
        elemCount++;
        paths = null;
    }

    /**
     * @param obj
     * @return
     * 
     * @since 2012. 03. 22.
     * 
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PathElement other = (PathElement) obj;
        if (elemCount != other.elemCount)
            return false;

        if (!ArrayUtils.equals(elemLength, other.elemLength, EquivalentFactory.intArrayEquiv())) {
            return false;
        }

        if (!Arrays.equals(elems, other.elems))
            return false;

        if (!separator.equals(other.separator))
            return false;

        return true;
    }

    /**
     * 길이가 동일한 char 배열이 동일한지를 판단합니다.
     * 
     * @param c1
     * @param c2
     * @return <BR>
     * @since 2012. 03. 12.
     * 
     */
    private boolean equalsChars(char[] c1, char[] c2) {
        for (int i = 0; i < c1.length; i++) {
            if (c1[i] != c2[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 경로 개수를 반환합니다.
     * 
     * @return <BR>
     * @since 2012. 03. 12.
     * 
     */
    public int getElemCount() {
        synchronized (mtxPaths) {
            return elemCount;
        }
    }

    /**
     * 경로 정보에 관한 인덱스 정보를 반환합니다.
     * 
     * @return <BR>
     * @since 2012. 03. 12.
     * 
     */
    // 아래 내용에 적용됨.
    // - Arrays.copyOf(elemLength, elemLength.length);
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public int[][] getElementLengths() {
        synchronized (mtxPaths) {
            return Arrays.copyOf(elemLength, elemLength.length);
        }
    }

    /**
     * 경로 정보 문자열 배열을 반환합니다.
     * 
     * @return <BR>
     * @since 2012. 03. 12.
     * 
     */
    // 아래 내용에 적용됨.
    // - Arrays.copyOf(elems, elems.length);
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public char[] getElements() {
        synchronized (mtxPaths) {
            return Arrays.copyOf(elems, elems.length);
        }
    }

    /**
     * 인덱스에 해당하는 구분자를 반환합니다.
     * 
     * @param index
     * @return <BR>
     * @since 2012. 03. 12.
     * 
     */
    // 아래 내용에 적용됨.
    // - this.paths[index];
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public String getPath(int index) {
        synchronized (mtxPaths) {
            checkIndex(index);

            if (this.paths != null) {
                return this.paths[index];
            }

            char[] path = new char[this.elemLength[index][1]];
            System.arraycopy(this.elems, this.elemLength[index][0], path, 0, path.length);
            return new String(path);
        }
    }

    /**
     * 전체 경로값을 문자열 배열로 반환합니다.
     * 
     * @return <BR>
     * @since 2012. 03. 14.
     * 
     */
    // 아래 내용에 적용됨.
    // - Arrays.copyOf(this.paths, this.paths.length);
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public String[] getPaths() {
        synchronized (mtxPaths) {
            if (elemCount < 1) {
                return new String[0];
            }

            if (this.paths != null) {
                return Arrays.copyOf(this.paths, this.paths.length);
            } else {
                this.paths = getPaths_internal(this.elemCount);
                return this.paths;
            }
        }
    }

    /**
     * 주어진 인덱스까지의 경로값을 반환합니다.
     * 
     * @param index
     *            inclusive
     * @return <BR>
     * @since 2012. 03. 15.
     * 
     */
    // 아래 내용에 적용됨.
    // - Arrays.copyOf(this.paths, index + 1);
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public String[] getPaths(int index) {
        synchronized (mtxPaths) {
            checkIndex(index);

            if (this.paths != null) {
                return Arrays.copyOf(this.paths, index + 1);
            } else {
                return getPaths_internal(index + 1);
            }
        }
    }

    /**
     * 주어진 개수만큼의 경로값을 배열로 반환합니다.
     * 
     * @param count
     * @return <BR>
     * @since 2012. 03. 15.
     * 
     */
    private String[] getPaths_internal(int count) {
        String[] paths = new String[count];

        int[] el = null;
        for (int i = 0; i < count; i++) {
            el = elemLength[i];
            paths[i] = new String(Arrays.copyOfRange(elems, el[0], el[2]));
        }

        return paths;
    }

    /**
     * 경로 구분자를 문자열로 반환합니다.
     * 
     * @return <BR>
     * @since 2012. 03. 14.
     * 
     */
    public String getSeparator() {
        return separator;
    }

    /**
     * @return
     * 
     * @since 2012. 03. 22.
     * 
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + elemCount;
        result = prime * result + Arrays.hashCode(elemLength);
        result = prime * result + Arrays.hashCode(elems);
        result = prime * result + separator.hashCode();
        return result;
    }

    /**
     * 경로값들을 {@link Iterator}객체로 반환합니다.
     * 
     * @return
     * 
     * @since 2012. 03. 14.
     * 
     * 
     * @see java.lang.Iterable#iterator()
     */
    public Iterator<String> iterator() {
        return new StringItr(getPaths());
    }

    /**
     * 맨 마지막 경로 값을 반환합니다.
     * 
     * @return
     * 
     * @throws PathElementException
     *             경로값이 없는 경우.
     * 
     *             <BR>
     * @since 2012. 03. 14.
     * 
     */
    // 아래 내용에 적용됨.
    // - this.elems = Arrays.copyOf(this.elems, this.elemLength[this.elemCount - 2][2]);
    // - this.elemLength = Arrays.copyOf(this.elemLength, this.elemCount - 1);
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public String remove() {
        synchronized (mtxPaths) {
            String removed = null;
            if (this.elemCount < 1) {
                throw new PathElementException("There is no a Path-Element");
            } else if (this.elemCount > 1) {
                removed = new String(this.elems, this.elemLength[this.elemCount - 1][0], this.elemLength[this.elemCount - 1][1]);

                this.elems = Arrays.copyOf(this.elems, this.elemLength[this.elemCount - 2][2]);
                this.elemLength = Arrays.copyOf(this.elemLength, this.elemCount - 1);
                this.elemCount--;
            } else {
                removed = new String(this.elems);

                this.elems = new char[0];
                this.elemLength = new int[0][];
                this.elemCount = 0;
            }
            return removed;
        }
    }

    /**
     * 경로 구분자를 변경한 후, 이전 구분자를 반환합니다. <br>
     * 구분자는 {@code null}이 될 수 없다.
     * 
     * @param delimiter
     * @return 이전 구분자.
     * 
     *         <BR>
     * @since 2012. 03. 12.
     * 
     */
    public String setSeparator(char separator) {
        synchronized (mtxPaths) {
            String latestValue = this.separator;
            this.sepChar = new char[] { separator };
            this.separator = new String(sepChar);
            return latestValue;
        }
    }

    /**
     * 경로 구분자를 변경한 후, 이전 구분자를 반환합니다. <br>
     * 구분자는 {@code null}이 될 수 없다.
     * 
     * @param delimiter
     * @return 이전 구분자. 파라미터가 {@code null}인 경우 변경을 하지 않고, {@code null}을 반환하다. <BR>
     * @since 2012. 03. 12.
     * 
     */
    // 아래 내용에 적용됨.
    // - this.sepChar = separator.toCharArray();
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public String setSeparator(String separator) {
        Objects.requireNonNull(separator);

        synchronized (mtxPaths) {
            String latestValue = this.separator;
            this.separator = separator;
            this.sepChar = separator.toCharArray();
            return latestValue;
        }
    }

    /**
     * @return
     * 
     * @since 2012. 03. 12.
     * 
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        if (elemCount < 1) {
            return "";
        } else {
            synchronized (mtxPaths) {
                char[] strChar = new char[elems.length + elemCount * sepChar.length];
                int[] el = null;
                int copiedLength = 0;
                for (int i = 0; i < elemLength.length; i++) {
                    el = elemLength[i];
                    System.arraycopy(sepChar, 0, strChar, copiedLength, sepChar.length);
                    copiedLength += sepChar.length;

                    System.arraycopy(elems, el[0], strChar, copiedLength, el[1]);
                    copiedLength += el[1];
                }

                return new String(strChar);
            }
        }
    }

    public static class PathElementException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public PathElementException() {
            super();
        }

        public PathElementException(String message) {
            super(message);
        }

        public PathElementException(String message, Throwable throwable) {
            super(message, throwable);
        }

        public PathElementException(Throwable throwable) {
            super(throwable);
        }
    }

    class StringItr implements Iterator<String> {

        final String[] itrArray;
        int pos = -1;
        final int length;

        private Object mtx = new Object();

        StringItr(String[] elems) {
            itrArray = elems;
            length = itrArray.length;
        }

        /**
         * @return
         * 
         * @since 2012. 03. 14.
         * 
         * 
         * @see java.util.Iterator#hasNext()
         */
        @Override
        public boolean hasNext() {
            synchronized (mtx) {
                return pos < length - 1;
            }
        }

        /**
         * @return
         * 
         * @except ArrayIndexOutOfBoundsException
         * 
         * @since 2012. 03. 14.
         * 
         * 
         * @see java.util.Iterator#next()
         */
        // 아래 내용에 적용됨.
        // - this.itrArray[pos];
        // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
        // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
        @SuppressWarnings("null")
        @Override
        public String next() {
            synchronized (mtx) {
                remove();
                return this.itrArray[pos];
            }
        }

        /**
         * 
         * 
         * @since 2012. 03. 14.
         * 
         * 
         * @see java.util.Iterator#remove()
         */
        @Override
        public void remove() {
            synchronized (mtx) {
                pos++;
            }
        }
    }
}
