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
* Date  : 2014. 5. 2. 오후 1:42:41
*
* Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
* 
*/

package open.commons.core.util;

/**
 * 
 * @since 2014. 5. 2.
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public interface IFilter<T> {

    /**
     * 
     * @param target
     *            a target filtered.
     * @param objects
     *            a data for filtering.
     * @return
     * 
     * @since 2014. 5. 2.
     */
    public boolean filter(T target, Object... objects);

    public static class FalseFilter<T> implements IFilter<T> {
        /**
         * @see open.commons.core.util.IFilter#filter(java.lang.Object, Object...)
         */
        @Override
        public boolean filter(T target, Object... objects) {
            return false;
        }
    }

    public static class TrueFilter<T> implements IFilter<T> {
        /**
         * @see open.commons.core.util.IFilter#filter(java.lang.Object, Object...)
         */
        @Override
        public boolean filter(T target, Object... objects) {
            return true;
        }
    }
}
