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
* Date  : 2013. 6. 17. 오후 2:47:36
*
* Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
* 
*/
package open.commons.io.encoding;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import open.commons.io.FileRecursiveHandler;

public abstract class FilesInDirConvertor implements IEncodingConvertor {

    protected FileRecursiveHandler handler;

    protected String inputDir;
    protected String outputDir;

    protected String inputEncoding;
    protected String outputEncoding;

    public FilesInDirConvertor(String inputDir, String outputDir) {
        handler = new FileRecursiveHandler(inputDir);

        this.inputDir = inputDir;
        this.outputDir = outputDir;

    }

    /**
     * No Supports.
     * 
     * @see open.commons.io.encoding.IEncodingConvertor#convert(java.io.InputStream, java.io.OutputStream)
     */
    @Override
    public int convert(InputStream inStream, OutputStream outStream) throws IOException {
        return 0;
    }

    /**
     * @return the inputDir
     */
    public String getInputDir() {
        return inputDir;
    }

    /**
     * @return the outputDir
     */
    public String getOutputDir() {
        return outputDir;
    }

    @Override
    public String setInputEncoding(String charset) {
        String latestEncoding = inputEncoding;
        this.inputEncoding = charset;

        return latestEncoding;
    }

    @Override
    public String setOutputEncoding(String charset) {
        String latestEncoding = outputEncoding;
        this.outputEncoding = charset;

        return latestEncoding;
    }

}
