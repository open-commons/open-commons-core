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
* Date  : 2013. 5. 23. 오후 5:18:25
*
* Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
* 
*/

/**
 * 
 */
package open.commons.unix.tool;

import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;

/**
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */
public interface IFileWatchListener {
    /**
     * 
     * @param pathname
     * @param kind
     *            <ul>
     *            <li>{@link StandardWatchEventKinds#ENTRY_CREATE}
     *            <li>{@link StandardWatchEventKinds#ENTRY_MODIFY}
     *            <li>{@link StandardWatchEventKinds#ENTRY_DELETE}
     *            </ul>
     */
    void watch(String pathname, WatchEvent.Kind<?> kind);
}