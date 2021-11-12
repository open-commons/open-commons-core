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
* Date  : 2013. 2. 22. 오전 11:43:07
*
* Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
* 
*/
package open.commons.utils;

import java.nio.ByteBuffer;
import java.util.StringJoiner;

import open.commons.lang.NumString;
import open.commons.util.ArrayItr;

public class ByteUtils {

    private static final int H1 = 0x10;

    private static final int H2 = 0x20;
    private static final int H4 = 0x40;
    private static final int H8 = 0x80;
    private static final int T1 = 0x01;

    private static final int T2 = 0x02;
    private static final int T4 = 0x04;
    private static final int T8 = 0x08;

    private static final char[] hexCode = "0123456789ABCDEF".toCharArray();

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 12. 17.		박준홍			최초 작성
     * </pre>
     *
     * @param split
     *            byte 단위의 Hex 표기값을 띄워서 표시할지 여부
     * @param data
     * @return
     *
     * @since 2020. 12. 17.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static String hexBinString(boolean split, byte... data) {
        return hexBinString("", split, data);
    }

    /**
     * byte 배열을 Hexa 문자열로 반환한다.
     * 
     * @param data
     * @return
     * 
     */
    public static String hexBinString(byte... data) {
        return hexBinString("", false, data);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 12. 17.		박준홍			최초 작성
     * </pre>
     *
     * @param prefix
     *            Hex 문자열 앞에 붙힐 접두어.
     * @param split
     *            byte 단위의 Hex 표기값을 띄워서 표시할지 여부
     * @param data
     * @return
     *
     * @since 2020. 12. 17.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static String hexBinString(String prefix, boolean split, byte... data) {
        if (data == null) {
            return null;
        }

        ArrayItr<Byte> itr = new ArrayItr<>(ArrayUtils.toWrapperArray(data));
        StringBuilder sb = new StringBuilder(prefix);
        Byte b = 0x00;
        if (itr.hasNext()) {
            b = itr.next();
            sb.append(hexCode[(b >> 4) & 0xF]);
            sb.append(hexCode[(b & 0xF)]);

            while (itr.hasNext() && (b = itr.next()) != null) {
                sb.append(split ? " " : "");
                sb.append(hexCode[(b >> 4) & 0xF]);
                sb.append(hexCode[(b & 0xF)]);
            }
        }

        return sb.toString();
    }

    /**
     * byte 배열을 Hex 문자열로 반환한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 12. 17.		박준홍			최초 작성
     * </pre>
     *
     * @param prefix
     *            Hex 문자열 앞에 붙힐 접두어.
     * @param data
     * @return
     *
     * @since 2020. 12. 17.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static String hexBinString(String prefix, byte... data) {
        return hexBinString(prefix, false, data);
    }

    /**
     * 
     * @param hexBinString
     * @return
     * 
     * 
     * @since 2014. 4. 25.: Remove colon(:)s and white-spaces in input string.
     */
    public static byte[] hexBinStringToByteArray(String hexBinString) {
        if (hexBinString == null) {
            return null;
        }

        hexBinString = hexBinString.replaceAll(":", "").replaceAll("\\s", "");

        if (hexBinString.length() % 2 != 0) {
            throw new IllegalArgumentException("입력된 문자열 길이는 짝수(even)이어야 합니다. 길이: " + hexBinString.length() + ", 입력값: " + hexBinString);
        }

        char[] cs = hexBinString.toCharArray();

        byte[] bytes = new byte[cs.length / 2];

        for (int i = 0; i < cs.length - 1; i += 2) {

            int h = hexToBin(cs[i]);
            int l = hexToBin(cs[i + 1]);
            if (h == -1 || l == -1) {
                throw new IllegalArgumentException("contains illegal character for hexBinary: [" + cs[i] + ", " + cs[i + 1] + "]");
            }

            bytes[i / 2] = (byte) (h * 16 + l);
        }

        return bytes;

    }

    private static int hexToBin(char ch) {
        if ('0' <= ch && ch <= '9') {
            return ch - '0';
        }
        if ('A' <= ch && ch <= 'F') {
            return ch - 'A' + 10;
        }
        if ('a' <= ch && ch <= 'f') {
            return ch - 'a' + 10;
        }
        return -1;
    }

    /**
     * byte[]를 {@link ByteBuffer}로 변환해서 반환한다.
     * 
     * @param bytes
     * @return <b><code>flip mode</code></b> instance.
     * 
     * @throws NullPointerException
     *
     * @since 2015. 12. 10.
     */
    public static ByteBuffer toByteBuffer(byte[] bytes) throws NullPointerException {

        ByteBuffer buf = ByteBuffer.allocateDirect(bytes.length);
        buf.put(bytes);
        buf.flip();

        return buf;
    }

    /**
     * byte[]을 char[]로 변환해서 반환한다.
     * 
     * @param bytes
     * @return
     * 
     * @see NullPointerException
     */
    public static char[] toChars(byte[] bytes) {
        char[] rtnChars = null;

        if (bytes != null) {
            rtnChars = new char[bytes.length];

            for (int i = 0; i < bytes.length; i++) {
                rtnChars[i] = (char) bytes[i];
            }
        }

        return rtnChars;
    }

    /**
     * byte 값을 int 로 변환해 반환한다.
     * 
     * @param b
     * @return
     */
    public static int toInt(byte b) {
        return b & 0xFF;
    }

    /**
     * 길이가 4인 byte 배열(byte-4 배열)을 int 타입의 값으로 변환한 후 반환한다.
     * 
     * @param value
     * @return
     */
    public static int toInt(byte[] value) {
        if (value.length > 4) {
            throw new IllegalArgumentException("Input parameter's length should be less than 4 or equal.");
        }

        if (value.length < 4) {
            value = ArrayUtils.merge(new byte[4 - value.length], value);
        }

        int returnedValue = ((value[0] << 24) & 0xFF000000) | ((value[1] << 16) & 0x00FF0000) | ((value[2] << 8) & 0x0000FF00) | ((value[3]) & 0x000000FF);

        return returnedValue;
    }

    /**
     * byte 배열을 IPV4 주소 형태로 변환하여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 12. 17.		박준홍			최초 작성
     * </pre>
     *
     * @param bytes
     * @return
     *
     * @since 2020. 12. 17.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static String toIPv4Expr(byte[] bytes) {
        byte[] bs = new byte[4];
        System.arraycopy(bytes, 0, bs, 0, Math.min(bytes.length, bs.length));
        return String.join(".", NumString.sequence(b -> new NumString<Integer>(toInt(b)), ArrayUtils.toWrapperArray(bs)));
    }

    /**
     * byte 배열을 MAC 주소 형태로 변환하여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 12. 17.		박준홍			최초 작성
     * </pre>
     *
     * @param bytes
     * @return
     *
     * @since 2020. 12. 17.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static String toMACExpr(byte[] bytes) {
        StringJoiner join = new StringJoiner(":");
        for (byte b : bytes) {
            join.add(hexBinString(b));
        }
        return join.toString();
    }

    /**
     * 
     * byte 내부의 bit 값 위치를 뒤집는다.<br>
     * 
     * <ul>
     * <li>1 <-> 8
     * <li>2 <-> 7
     * <li>3 <-> 6
     * <li>4 <-> 5
     * </ul>
     * 
     * @param b
     * @return
     */
    public static byte upset(byte b) {
        byte v = 0x00;

        v |= (byte) ((b & T1) << 7);
        v |= (byte) ((b & T2) << 5);
        v |= (byte) ((b & T4) << 3);
        v |= (byte) ((b & T8) << 1);

        v |= (byte) ((b & H1) >> 1);
        v |= (byte) ((b & H2) >> 3);
        v |= (byte) ((b & H4) >> 5);
        v |= (byte) ((b & H8) >> 7);

        return v;
    }

    /**
     * 주어진 배열에 포함된 byte 내부의 bit 값 위치를 뒤집은 후 새로운 배열로 반환한다.<br>
     * 
     * <ul>
     * <li>1 <-> 8
     * <li>2 <-> 7
     * <li>3 <-> 6
     * <li>4 <-> 5
     * </ul>
     * 
     * @param bytes
     * @return
     * 
     * @see #upsetArray(byte[])
     */
    public static byte[] upset(byte[] bytes) {
        if (bytes == null) {
            return null;
        }

        byte[] upsets = new byte[bytes.length];

        for (int i = 0; i < bytes.length; i++) {
            upsets[i] = upset(bytes[i]);
        }

        return upsets;
    }

    /**
     * 주어진 배열에 포함된 byte 내부의 bit 값 위치를 뒤집는다.<br>
     * 
     * <ul>
     * <li>1 <-> 8
     * <li>2 <-> 7
     * <li>3 <-> 6
     * <li>4 <-> 5
     * </ul>
     * 
     * @param bytes
     *            <b><code>not nullable.</code></b>
     * 
     * @see #upset(byte[])
     */
    public static void upsetArray(byte[] bytes) {
        if (bytes == null) {
            throw new IllegalArgumentException(new NullPointerException("파라미터는 null일 수 없음. bytes: " + bytes));
        }

        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = upset(bytes[i]);
        }
    }

    /**
     * 두 개의 배열에서 동일한 인덱스에 존재하는 데이터의 XOR(Exclusive OR)값을 하나의 배열로 반환한다.
     * 
     * @param b1
     * @param b2
     * @return
     */
    public static byte[] xor(byte[] b1, byte[] b2) {
        byte[] rtnBytes = new byte[b1.length];

        for (int i = 0; i < b1.length; i++) {
            rtnBytes[i] = (byte) (b1[i] ^ b2[i]);
        }
        return rtnBytes;
    }
}
