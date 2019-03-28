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
* Date  : 2013. 6. 3. 오전 10:10:11
*
* Author: Park_Jun_Hong_(fafanmama_at_naver_com)
* 
*/

/**
 * 
 */
package open.commons.utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

/**
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */
public class RandUtils {

    /**
     * 
     * @param string
     * @return
     *
     * @since 2015. 2. 6.
     * @see {@link String#hashCode()}
     */
    public static long hash(String string) {
        long h = 1125899906842597L; // prime
        int len = string.length();

        for (int i = 0; i < len; i++) {
            h = 31 * h + string.charAt(i);
        }
        return h;
    }

    public static long x64HashCode(String string) {
        return hash(string) * 31 + hash(string);
    }

    /**
     * 0부터 주어진 (한계값 - 1) 사이에서 무작위로 하나의 값을 선택해서 반환한다.
     * 
     * @param seed
     *            랜덤함수 seed
     * @param limit
     *            한계값.
     * @return
     */
    public static int randomNumber(byte[] seed, int limit) {

        int rn = 0;

        try {
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            sr.setSeed(seed);

            rn = sr.nextInt() % limit;

        } catch (NoSuchAlgorithmException e) {
            rn = new Random(System.currentTimeMillis()).nextInt() % limit;
        }

        return rn;
    }

    /**
     * 0부터 주어진 (한계값 - 1) 사이에서 무작위로 하나의 값을 선택해서 반환한다.
     * 
     * @param limit
     *            한계값
     * @return
     */
    public static int randomNumber(int limit) {
        int rn = 0;
        try {
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");

            rn = sr.nextInt() % limit;

        } catch (NoSuchAlgorithmException e) {

            rn = new Random(System.currentTimeMillis()).nextInt() % limit;
        }

        return rn;
    }

    /**
     * 0부터 주어진 (한계값 - 1) 사이에서 무작위로 하나의 값을 선택해서 반환한다.
     * 
     * @param seed
     *            랜덤함수 seed
     * @param limit
     *            한계값.
     * @return
     */
    public static int randomNumber(long seed, int limit) {
        int rn = 0;
        try {
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            sr.setSeed(seed);

            rn = sr.nextInt() % limit;

        } catch (NoSuchAlgorithmException e) {

            rn = new Random(System.currentTimeMillis()).nextInt() % limit;
        }

        return rn;
    }
}
