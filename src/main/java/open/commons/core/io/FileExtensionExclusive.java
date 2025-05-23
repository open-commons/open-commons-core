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
*/
package open.commons.core.io;

import java.io.File;
import java.io.FileFilter;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import open.commons.core.utils.StringUtils;

/**
 * 
 * <BR>
 * 
 * @since 2012. 01. 20.
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
public class FileExtensionExclusive implements FileFilter {

    private Set<String> extensioins = new HashSet<String>();

    public FileExtensionExclusive() {
    }

    /**
     * 
     * @param exts
     *            파일 확장자
     * @since 2012. 01. 20.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see Set#addAll(Collection)
     */
    public FileExtensionExclusive(Collection<String> exts) {
        extensioins.addAll(exts);
    }

    /**
     * 
     * @param exts
     *            파일 확장자
     * @since 2012. 01. 20.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public FileExtensionExclusive(String... exts) {
        for (String ext : exts) {
            extensioins.add(ext);
        }
    }

    /**
     * @param pathname
     * @return
     * 
     * @since 2012. 01. 20.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see java.io.FileFilter#accept(java.io.File)
     */
    @Override
    public boolean accept(File pathname) {
        if (!pathname.isFile()) {
            return false;
        } else {
            return !StringUtils.endsWithOneOf(pathname.getName(), extensioins.toArray(new String[] {}));
        }
    }

    public void add(String ext) {
        synchronized (extensioins) {
            if (ext != null) {
                extensioins.add(ext);
            }
        }
    }

    public void add(String... exts) {
        synchronized (extensioins) {
            for (String ext : exts) {
                extensioins.add(ext);
            }
        }
    }

    public void remove(String ext) {
        synchronized (extensioins) {
            if (ext != null) {
                extensioins.remove(ext);
            }
        }
    }
}
