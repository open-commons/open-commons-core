/*
 * Copyright 2011 Park Jun-Hong_(fafanmama_at_naver_com)
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
* Date  : 2013. 5. 31. 오전 11:39:40
*
* Author: Park_Jun_Hong_(fafanmama_at_naver_com)
* 
*/

/**
 * 
 */
package open.commons.utils;

/**
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */
public class CharUtils {

    /**
     * char[]을 byte[]로 변환해서 반환한다.
     * 
     * @param cs
     * @return
     * 
     * @see NullPointerException
     */
    public static byte[] toBytes(char[] cs) {
        byte[] rtnBytes = null;

        if (cs != null) {
            rtnBytes = new byte[cs.length];

            for (int i = 0; i < cs.length; i++) {
                rtnBytes[i] = (byte) cs[i];
            }
        }

        return rtnBytes;
    }
}
