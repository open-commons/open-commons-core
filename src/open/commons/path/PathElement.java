/*
 * Copyright 2011 Park Jun-Hong (parkjunhong77/google/com)
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
* Author: Park Jun-Hong (fafanmama_at_naver_dot_com)
* 
*/
package open.commons.path;

import java.io.File;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import open.commons.EquivalentFactory;
import open.commons.utils.ArrayUtils;
import open.commons.utils.CheckUtils;
import open.commons.utils.StringUtils;

/**
 * 패스 정보를 보다 빠르게 처리하기 위해 제공되는 클래스 <BR>
 * 
 * @since 2012. 3. 9.
 * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
 */
public class PathElement implements Iterable<String> {

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

    Object mtxPaths = new Object();

    String[] paths;

    /**
     * 시스템의 파일 경로 구분자를 사용하는 기본 생성자.
     * 
     * <BR>
     * 
     * @since 2012. 03. 12.
     * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
     * 
     * @see PathElement#PathElement(String)
     */
    public PathElement() {
        this(File.separator);
    }

    /**
     * 주어진 문자를 구분자로 사용해서 새로운 객체를 생성한다.
     * 
     * @param separator
     *            경로 구분자 <BR>
     * @since 2012. 03. 15.
     * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
     */
    public PathElement(char separator) {

        sepChar = new char[] { separator };

        this.separator = new String(sepChar);
    }

    /**
     * 
     * @param elems
     * @param elemLength
     * 
     *            <BR>
     * @since 2012. 03. 15.
     * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
     */
    public PathElement(char[] elems, int[][] elemLength) {
        this(File.separator, elems, elemLength);
    }

    /**
     * 기본 경로 구분자와 주어진 문자열을 경로값으로 하는 객체를 생성한다.
     * 
     * @param elems
     *            경로를 구성하는 문자열들 <BR>
     * @since 2012. 03. 15.
     * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
     */
    public PathElement(String... elems) {
        this(File.separator);

        CheckUtils.checkNull((Object[]) elems);

        for (String elem : elems) {
            add(elem);
        }
    }

    /**
     * 생성자
     * 
     * @param separator
     *            경로 구분자.
     * 
     *            <BR>
     * @since 2012. 03. 12.
     * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
     */
    public PathElement(String separator) {
        if (separator == null) {
            throw new IllegalArgumentException(new NullPointerException("A separator must not be 'null': separator=null"));
        } else if (StringUtils.isNullOrEmptyString(separator)) {
            throw new IllegalArgumentException("A separator must not be 'empty string': separator=" + separator);
        }

        this.separator = separator;
        sepChar = separator.toCharArray();
    }

    /**
     * 
     * @param separator
     * @param elems
     *            경로값들의 문자열 배열
     * @param elemLength
     *            경로값들에 대한 인덱스 정보 배열
     * 
     *            <BR>
     * @since 2012. 03. 15.
     * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
     */
    public PathElement(String separator, char[] elems, int[][] elemLength) {
        this(separator);

        if (elems == null || elemLength == null) {
            throw new PathElementException(
                    "Parameters (char[] elems, int[][] elemLength) must not be null both of them: elems=" + Arrays.toString(elems) + ", elemLength=" + elemLength);
        }

        if (elems.length != elemLength[elemLength.length - 1][2]) {
            throw new PathElementException("A character array of 'Path element's does not match to 'Path element's Length info: elems.length=" + elems.length + ", final-Length="
                    + elemLength[elemLength.length - 1][2]);
        }

        CheckUtils.checkNull((Object[]) elemLength);

        int[] el = null;
        for (int i = 0; i < elemLength.length; i++) {
            el = elemLength[i];

            if (el[0] + el[1] != el[2]) {
                throw new PathElementException("A total length value is not equal to the sum of a latest-length and a current string. info=" + Arrays.toString(el));
            }
        }

        this.elems = elems;
        this.elemLength = elemLength;
        this.elemCount = elemLength.length;
    }

    /**
     * 새로운 경로를 추가한다.
     * 
     * @param path
     *            <BR>
     * @since 2012. 03. 12.
     * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
     */
    public void add(char path) {
        char[] npc = new char[] { path };
        synchronized (mtxPaths) {
            createPathValue(npc);
        }
    }

    /**
     * 새로운 경로를 추가한다.
     * 
     * @param path
     *            <BR>
     * @since 2012. 03. 12.
     * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
     */
    public void add(String path) {
        if (path != null) {
            char[] npc = path.toCharArray();
            synchronized (mtxPaths) {
                createPathValue(npc);
            }
        }
    }

    /**
     * 새로운 경로들을 추가한다.
     * 
     * @param paths
     *            <BR>
     * @since 2012. 03. 15.
     * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
     * 
     * @see #add(String)
     */
    public void addAll(char... paths) {
        CheckUtils.checkNull(paths);

        if (paths != null) {
            synchronized (mtxPaths) {
                for (char path : paths) {
                    createPathValue(new char[] { path });
                }
            }
        }
    }

    /**
     * 새로운 경로들을 추가한다.
     * 
     * @param paths
     *            <BR>
     * @since 2012. 03. 15.
     * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
     * 
     * @see #add(String)
     */
    public void addAll(List<String> paths) {
        CheckUtils.checkNull(paths);

        if (paths != null) {
            synchronized (mtxPaths) {
                for (String path : paths) {
                    createPathValue(path.toCharArray());
                }
            }
        }
    }

    /**
     * 새로운 경로들을 추가한다.
     * 
     * @param paths
     *            <BR>
     * @since 2012. 03. 15.
     * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
     * 
     * @see #add(String)
     */
    public void addAll(String... paths) {
        CheckUtils.checkNull((Object[]) paths);

        if (paths != null) {
            synchronized (mtxPaths) {
                for (String path : paths) {
                    createPathValue(path.toCharArray());
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

    public void clear() {
        synchronized (mtxPaths) {

            /** 구분자 */
            separator = File.separator;

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
     * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
     * 
     * @see java.lang.Object#clone()
     */
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
     * 주어진 경로를 포함하고 있는지 여부를 반환한다.
     * 
     * @param path
     * @return <BR>
     * @since 2012. 03. 12.
     * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
     */
    public boolean contains(String path) {
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

            evalElemLen = ArrayUtils.copyOf(evalElemLen, evalLength);
            int[] el = null;

            char[] elemChars = null;
            for (int eel : evalElemLen) {
                el = elemLength[eel];

                elemChars = new char[pl];

                System.arraycopy(elems, el[0], elemChars, 0, el[1]);

                if (equalsChars(path.toCharArray(), elemChars)) {
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
     * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
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
     * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
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

        if (separator == null) {
            if (other.separator != null)
                return false;
        } else if (!separator.equals(other.separator))
            return false;

        return true;
    }

    /**
     * 길이가 동일한 char 배열이 동일한지를 판단한다.
     * 
     * @param c1
     * @param c2
     * @return <BR>
     * @since 2012. 03. 12.
     * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
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
     * 경로 개수를 반환한다.
     * 
     * @return <BR>
     * @since 2012. 03. 12.
     * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
     */
    public int getElemCount() {
        synchronized (mtxPaths) {
            return elemCount;
        }
    }

    /**
     * 경로 정보에 관한 인덱스 정보를 반환한다.
     * 
     * @return <BR>
     * @since 2012. 03. 12.
     * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
     */
    public int[][] getElementLengths() {
        synchronized (mtxPaths) {
            return ArrayUtils.copyOf(elemLength, elemLength.length);
        }
    }

    /**
     * 경로 정보 문자열 배열을 반환한다.
     * 
     * @return <BR>
     * @since 2012. 03. 12.
     * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
     */
    public char[] getElements() {
        synchronized (mtxPaths) {
            return ArrayUtils.copyOf(elems, elems.length);
        }
    }

    /**
     * 인덱스에 해당하는 구분자를 반환한다.
     * 
     * @param index
     * @return <BR>
     * @since 2012. 03. 12.
     * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
     */
    public String getPath(int index) {
        synchronized (mtxPaths) {
            checkIndex(index);

            if (paths != null) {
                return paths[index];
            }

            char[] path = new char[elemLength[index][1]];
            System.arraycopy(elems, elemLength[index][0], path, 0, path.length);
            return new String(path);
        }
    }

    /**
     * 전체 경로값을 문자열 배열로 반환한다.
     * 
     * @return <BR>
     * @since 2012. 03. 14.
     * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
     */
    public String[] getPaths() {

        synchronized (mtxPaths) {
            if (elemCount < 1) {
                return null;
            }

            if (paths != null) {
                return ArrayUtils.copyOf(paths, paths.length);
            } else {
                paths = getPaths_internal(elemCount);
                return paths;
            }
        }
    }

    /**
     * 주어진 인덱스까지의 경로값을 반환한다.
     * 
     * @param index
     *            inclusive
     * @return <BR>
     * @since 2012. 03. 15.
     * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
     */
    public String[] getPaths(int index) {
        synchronized (mtxPaths) {
            checkIndex(index);

            if (paths != null) {
                return ArrayUtils.copyOf(paths, index + 1);
            } else {
                return getPaths_internal(index + 1);
            }
        }
    }

    /**
     * 주어진 개수만큼의 경로값을 배열로 반환한다.
     * 
     * @param count
     * @return <BR>
     * @since 2012. 03. 15.
     * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
     */
    private String[] getPaths_internal(int count) {
        String[] paths = new String[count];

        int[] el = null;
        for (int i = 0; i < count; i++) {
            el = elemLength[i];
            paths[i] = new String(ArrayUtils.copyOfRange(elems, el[0], el[2]));
        }

        return paths;
    }

    /**
     * 경로 구분자를 문자열로 반환한다.
     * 
     * @return <BR>
     * @since 2012. 03. 14.
     * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
     */
    public String getSeparator() {
        return separator;
    }

    /**
     * @return
     * 
     * @since 2012. 03. 22.
     * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
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
        result = prime * result + ((separator == null) ? 0 : separator.hashCode());
        return result;
    }

    /**
     * 경로값들을 {@link Iterator}객체로 반환한다.
     * 
     * @return
     * 
     * @since 2012. 03. 14.
     * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
     * 
     * @see java.lang.Iterable#iterator()
     */
    public Iterator<String> iterator() {
        return new StringItr(getPaths());
    }

    /**
     * 맨 마지막 경로 값을 반환한다.
     * 
     * @return
     * 
     * @exception PathElementException
     *                경로값이 없는 경우.
     * 
     *                <BR>
     * @since 2012. 03. 14.
     * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
     */
    public String remove() {
        synchronized (mtxPaths) {
            String removed = null;
            if (elemCount < 1) {
                throw new PathElementException("There is no a Path-Element");
            } else if (elemCount > 1) {
                removed = new String(elems, elemLength[elemCount - 1][0], elemLength[elemCount - 1][1]);

                elems = ArrayUtils.copyOf(elems, elemLength[elemCount - 2][2]);
                elemLength = ArrayUtils.copyOf(elemLength, elemCount - 1);
                elemCount--;
            } else {
                removed = new String(elems);

                elems = new char[0];
                elemLength = new int[0][];
                elemCount = 0;
            }
            return removed;
        }
    }

    /**
     * 경로 구분자를 변경한 후, 이전 구분자를 반환한다. <br>
     * 구분자는 <code>null</code>이 될 수 없다.
     * 
     * @param separator
     * @return 이전 구분자.
     * 
     *         <BR>
     * @since 2012. 03. 12.
     * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
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
     * 경로 구분자를 변경한 후, 이전 구분자를 반환한다. <br>
     * 구분자는 <code>null</code>이 될 수 없다.
     * 
     * @param separator
     * @return 이전 구분자. 파라미터가 <code>null</code>인 경우 변경을 하지 않고, <code>null</code>을 반환하다. <BR>
     * @since 2012. 03. 12.
     * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
     */
    public String setSeparator(String separator) {
        if (separator != null) {
            synchronized (mtxPaths) {
                String latestValue = this.separator;
                this.separator = separator;
                this.sepChar = separator.toCharArray();
                return latestValue;
            }
        } else {
            return null;
        }
    }

    /**
     * @return
     * 
     * @since 2012. 03. 12.
     * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
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
         * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
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
         * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
         * 
         * @see java.util.Iterator#next()
         */
        @Override
        public String next() {
            synchronized (mtx) {
                remove();
                return itrArray[pos];
            }
        }

        /**
         * 
         * 
         * @since 2012. 03. 14.
         * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
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
