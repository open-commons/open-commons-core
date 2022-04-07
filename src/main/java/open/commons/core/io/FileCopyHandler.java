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
* Date  : 2013. 7. 5. 오후 5:54:41
*
* Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
* 
*/
package open.commons.core.io;

import java.io.File;

import open.commons.core.utils.FileUtils;

public class FileCopyHandler implements IFileHandler {

    private String targetDir;

    public FileCopyHandler(String targetDir) {
        this.targetDir = targetDir;
    }

    @Override
    public String getFilenamePattern() throws Exception {
        return null;
    }

    @Override
    public void handleFile(File file) throws Exception {

        File targetDir = new File(this.targetDir);
        if (!targetDir.exists()) {
            targetDir.mkdirs();
        }

        File target = new File(targetDir, file.getName());
        if (target.exists()) {
            target.delete();
        }

        FileUtils.copyFile(file, target);
    }
}