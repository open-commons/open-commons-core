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
* Date  : 2013. 6. 20. 오후 2:35:40
*
* Author: Park_Jun_Hong_(fafanmama_at_naver_com)
* 
*/

/**
 * 
 */
package open.commons.net;

/**
 * Constants for {@link BRProdReader}. <br>
 * 
 * <pre>
 * [개정이력]
 *      날짜      | 작성자   |   내용
 * ------------------------------------------
 * 2013. 6. 20. 박준홍     최초 작성.
 * </pre>
 * 
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 * @deprecated Not Use. To be deleted.
 */

public class BRPConstants {
    /** String <-> byte [] 변환시 사용할 Character Set. */
    public static final String CHARSET_NAME = "UTF-8";

    /** 데이터 구분자 */
    public static final byte[] DATA_DELIMITER = "|".getBytes();

    /** 전송 단위 구분자 */
    public static final String TRANSFER_DELIMITER = System.getProperty("line.separator");

    /** 단일 데이터 전송 */
    public static final String DATA_SCALAR = "AAAA";
    /** 다수 데이터 전송 */
    public static final String DATA_VECTOR = "BBBB";
    /** 전송 종료 */
    public static final String DATA_END = "CCCC";
    /** 에러 전송 */
    public static final String DATA_ERROR = "FFFF";

    /**
     * prevent create an instance.
     */
    private BRPConstants() {
    }
}