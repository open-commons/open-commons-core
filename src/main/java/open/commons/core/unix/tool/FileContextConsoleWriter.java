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
* Date  : 2013. 5. 23. 오후 8:29:09
*
* Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
* 
*/

/**
 * 
 */
package open.commons.core.unix.tool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

/**
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */
public final class FileContextConsoleWriter extends AbstractContextWriter {

    /**
     * Default constructor.
     * 
     * @see #FileContextConsoleWriter(boolean, boolean, boolean)
     */
    public FileContextConsoleWriter() {
        this(false, false, false);
    }

    /**
     * 
     * @param onTimestamp
     *            TODO
     * @param onFilename
     *            whether show a filename or not.
     * @param onDirectory
     *            TODO
     */
    public FileContextConsoleWriter(boolean onTimestamp, boolean onFilename, boolean onDirectory) {
        super(onTimestamp, onFilename, onDirectory);
    }

    /**
     * 
     * @see open.commons.core.unix.tool.IFileContextWriter#write(open.commons.core.unix.tool.IFileModifyListener.FileContext)
     */
    @Override
    public void write(open.commons.core.unix.tool.IFileModifyListener.FileContext context) throws IOException {

        BufferedReader reader = new BufferedReader(new StringReader(contextToString(context)));

        String readline = null;

        while ((readline = reader.readLine()) != null) {
            System.out.println(readline);
        }

        reader.close();
    }
}
