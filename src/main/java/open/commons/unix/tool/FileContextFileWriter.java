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
* This file is generated under this project, "open-commons-core".
*
* Date  : 2013. 5. 23. 오후 8:29:09
*
* Author: Park_Jun_Hong_(fafanmama_at_naver_com)
* 
*/

/**
 * 
 */
package open.commons.unix.tool;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */
public class FileContextFileWriter extends AbstractContextWriter {

    private final File file;
    private FileWriter writer;

    public FileContextFileWriter(File file) {
        this.file = file;
    }

    public FileContextFileWriter(File file, boolean ts, boolean fn, boolean d) {
        super(ts, fn, d);
        this.file = file;
    }

    /**
     * 
     * @see open.commons.unix.tool.AbstractContextWriter#close()
     */
    @Override
    public void close() throws IOException {
        if (writer != null) {
            writer.close();
        }
    }

    /**
     * 
     * @see open.commons.unix.tool.IFileContextWriter#write(open.commons.unix.tool.IFileModifyListener.FileContext)
     */
    @Override
    public void write(open.commons.unix.tool.IFileModifyListener.FileContext context) throws IOException {
        writer = new FileWriter(file, true);

        write(writer, context);
        writer.write('\n');

        writer.close();
    }
}
