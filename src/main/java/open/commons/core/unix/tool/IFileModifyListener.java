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
* This file is generated under this project, "open-commons-core".
*
* Date  : 2013. 5. 23. 오후 7:54:15
*
* Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
* 
*/

/**
 * 
 */
package open.commons.core.unix.tool;

import java.util.Arrays;

import open.commons.core.utils.ObjectUtils;

/**
 * 
 * @since 2013. 5. 23.
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */
public interface IFileModifyListener extends Comparable<IFileModifyListener> {

    void modify(FileContext context);

    String name();

    public class FileContext {
        @SuppressWarnings("null")
        private static final String CLASS = FileContext.class.getSimpleName();

        final String file;
        final byte[] context;

        /**
         * 
         * <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜    	| 작성자	|	내용
         * ------------------------------------------
         * 2013. 5. 23.         parkjunhong77@gmail.com			최초 작성
         * </pre>
         *
         * @param f
         * @param c
         * 
         * @throws NullPointerException
         *             파라미터({@code f, c 중에 1개라도})가 {@code null}인 경우 발생.
         *
         * @since 2013. 5. 23.
         */
        public FileContext(String f, byte[] c) {
            ObjectUtils.requireNonNulls(f, c);

            file = f;
            context = c;
        }

        /**
         * @return the context
         */
        public byte[] getContext() {
            return context;
        }

        /**
         * @return the file
         */
        public String getFile() {
            return file;
        }

        /**
         * 
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return CLASS + "[file=" + file + ", context=" + Arrays.toString(context) + "]";
        }
    }
}
