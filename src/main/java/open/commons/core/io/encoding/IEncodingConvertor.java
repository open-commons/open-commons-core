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
* Date  : 2013. 6. 17. 오후 2:41:06
*
* Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
* 
*/
package open.commons.core.io.encoding;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface IEncodingConvertor {

    /**
     * 
     * @return 변환된 데이터 개수.
     * @throws IOException
     */
    public int convert() throws IOException;

    /**
     * 
     * @param inStream
     *            원본 데이터
     * @param outStream
     *            결과물 데이터
     * @return 읽은 문자 수.
     * @throws IOException
     */
    public int convert(InputStream inStream, OutputStream outStream) throws IOException;

    /**
     * 원본 데이터에 대한 인코딩을 설정합니다.
     * 
     * @param charset
     * @return
     */
    public String setInputEncoding(String charset);

    /**
     * 결과물에 대한 인코딩을 설정합니다.
     * 
     * @param charset
     * @return
     */
    public String setOutputEncoding(String charset);

}
