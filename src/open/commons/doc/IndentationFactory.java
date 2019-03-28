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
*/
package open.commons.doc;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * 
 * <BR>
 * 
 * @since 2012. 2. 7.
 * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
 */
public class IndentationFactory {
    private static Object mutex = new Object();

    private static Map<String, Indentation> pool = new ConcurrentSkipListMap<String, Indentation>();

    public static Indentation newInstance(String str) {
        Indentation indent = null;

        synchronized (mutex) {
            indent = pool.get(str);
            if (indent == null) {
                indent = new Indentation(str);

                pool.put(str, indent);
            }
        }

        return indent;
    }

    public static class Indentation {
        private final String STRING;

        private Object mutex = new Object();

        private Map<Integer, String> strings = new ConcurrentHashMap<Integer, String>();

        private int depth = 0;

        private StringBuffer strGen = new StringBuffer();

        private Indentation(String str) {
            STRING = str;

            strings.put(0, "");
        }

        /**
         * 
         * 
         * 
         * @since 2012. 2. 7.
         * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
         */
        public void dec() {
            synchronized (mutex) {
                depth--;
            }
        }

        public String decAndToString() {
            synchronized (mutex) {
                depth--;

                return toString0();
            }
        }

        /**
         * @return the depth
         * 
         *         <BR>
         * @since 2012. 2. 7.
         * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
         */
        public final int getDepth() {
            return depth;
        }

        public void inc() {
            synchronized (mutex) {
                depth++;
            }
        }

        public String incAndToString() {
            synchronized (mutex) {
                depth++;

                return toString0();
            }
        }

        public String toString() {
            synchronized (mutex) {
                return toString0();
            }
        }

        /**
         * This method is not thread-safe. Using this method, a caller should be ensure thread-safety.
         * 
         * @return
         * 
         * @since 2012. 2. 7.
         * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
         */
        private String toString0() {
            String str = strings.get(depth);
            if (str == null && depth > 0) {
                for (int i = 0; i < depth; i++) {
                    strGen.append(STRING);
                }
                str = strGen.toString();
                strGen.delete(0, str.length());

                strings.put(depth, str);
            }
            return str;
        }
    }
}
