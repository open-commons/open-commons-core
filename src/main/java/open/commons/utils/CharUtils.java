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
* Date  : 2013. 5. 31. 오전 11:39:40
*
* Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
* 
*/

/**
 * 
 */
package open.commons.utils;

import java.lang.Character.UnicodeBlock;

/**
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */
public class CharUtils {

    public static final UnicodeBlock[] UNICODE_BLOCK_HANGUL = { //
            Character.UnicodeBlock.HANGUL_COMPATIBILITY_JAMO, //
            Character.UnicodeBlock.HANGUL_JAMO, //
            Character.UnicodeBlock.HANGUL_SYLLABLES, //
            Character.UnicodeBlock.HANGUL_JAMO_EXTENDED_A, //
            Character.UnicodeBlock.HANGUL_JAMO_EXTENDED_B, //
    };

    /**
     * 문자가 한글인지 여부를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 11. 9.		박준홍			최초 작성
     * </pre>
     *
     * @param c
     * @return
     *
     * @since 2020. 11. 9.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @see #isKorean(int)
     */
    public static final boolean isKorean(char c) {
        return isKorean((int) c);
    }

    /**
     * 문자가 한글인지 여부를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 11. 9.		박준홍			최초 작성
     * </pre>
     *
     * @param c
     * @return
     *
     * @since 2020. 11. 9.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static final boolean isKorean(int c) {
        UnicodeBlock b = Character.UnicodeBlock.of(c);
        for (UnicodeBlock hangul : UNICODE_BLOCK_HANGUL) {
            if (hangul.equals(b)) {
                return true;
            }
        }
        return false;
    }

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
