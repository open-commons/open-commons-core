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
* 
* This file is generated under this project, "open-commons-core".
*
* Date  : 2013. 6. 17. 오후 2:33:22
*
* Author: Park_Jun_Hong_(fafanmama_at_naver_com)
* 
*/
package open.commons.io.encoding;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class FileConvertor extends DefaultConvertor {

    /**
     * 
     * @param srcEncoding
     *            원본 파일 인코딩
     * @param targetEncoding
     *            결과 파일 인코딩
     */
    public FileConvertor(String srcEncoding, String targetEncoding) {
        super(srcEncoding, targetEncoding);
    }

    public int convert(FileInputStream inStream, FileOutputStream outStream) throws IOException {
        return super.convert(inStream, outStream);
    }

    public void setInputStream(FileInputStream inStream) throws UnsupportedEncodingException {
        super.setInputStream(inStream);
    }

    public void setOutputStream(FileOutputStream outStream) throws UnsupportedEncodingException {
        super.setOutputStream(outStream);
    }
}
