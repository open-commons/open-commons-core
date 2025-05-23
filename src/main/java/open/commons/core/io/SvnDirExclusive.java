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

/**
* @title CommonUtils
* @since 2011. 10. 07.
*/
package open.commons.core.io;

import java.io.File;
import java.io.FileFilter;

/***
 * 
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 * @since 2011. 10. 07.
 * 
 */
public class SvnDirExclusive implements FileFilter {

    /**
     * 
     * @see java.io.FileFilter#accept(java.io.File)
     */
    @Override
    public boolean accept(File pathname) {
        return pathname.isDirectory() && !pathname.getAbsolutePath().endsWith(".svn");
    }

}
