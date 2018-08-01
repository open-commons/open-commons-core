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
* 
* 
* This file is generated under this project, "open-commons-core".
*
* Date  : 2013. 2. 22. 오전 11:43:07
*
* Author: Park_Jun_Hong_(fafanmama_at_naver_com)
* 
*/
package open.commons.utils;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

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
     * Byte 배열을 Hexa 문자열로 반환한다.
     * 
     * @param data
     * @return
     * 
     */
    public static String hexBinString(byte[] data) {
        if (data == null) {
            return null;
        }

        StringBuilder r = new StringBuilder(data.length * 2);
        for (byte b : data) {
            r.append(hexCode[(b >> 4) & 0xF]);
            r.append(hexCode[(b & 0xF)]);
        }
        return r.toString();
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
     * @param args
     * @throws UnsupportedEncodingException
     */
    public static void main(String[] args) throws UnsupportedEncodingException {

        byte b = 0x01;
        System.out.println(IntegerUtils.toBinary32String(b, 8));
        System.out.println(b);

        b = upset(b);
        System.out.println(IntegerUtils.toBinary32String(b, 8));
        System.out.println(b);

        b = upset(b);
        System.out.println(IntegerUtils.toBinary32String(b, 8));
        System.out.println(b);

        // System.out.println(H8);
        //
        // System.out.println(IntegerUtil.toBinary32String(0x80));
        // System.out.println(IntegerUtil.toBinary32String(H8));

        byte[] bytes = "1234567".getBytes();

        System.out.println(new String(bytes));

        // byte[] upsets = upsetArray(bytes);
        // System.out.println(new String(upsets));

        upsetArray(bytes);
        System.out.println(new String(bytes));

        upsetArray(bytes);
        System.out.println(new String(bytes));

        b = (byte) 255;
        System.out.printf("byte(%d) -> int(%d), int=%d\n", b, toInt(b), (int) b);

        System.out.println(hexBinString(xor(new byte[] { (byte) 0xff }, new byte[] { (byte) 0x00 })));

        String hexBinStr = "01:0d:00:5d:00:01:fe:e8:ff:ff:ff:ff:ff:ff:00:08:00:00:00:08:00:08:00:00:ff:ff:ff:ff:ff:ff:be:86:7d:29:ab:be:89:42:20:00:06:04:00:02:00:00:02:07:04:00:00:00:00:00:02:04:03:02:00:08:06:02:00:78:fe:0c:00:26:e1:00:00:00:00:00:00:00:00:02:18:08:0a:21:91:79:24:5a:f8:b5:e6:01:01:00:00:01:0d:00:5d:00:01:fe:e9:ff:ff:ff:ff:ff:ff:00:08:00:00:00:08:00:05:00:00:ff:ff:ff:ff:ff:ff:2a:67:47:23:81:d5:89:42:20:00:06:04:00:02:00:00:02:07:04:00:00:00:00:00:02:04:03:02:00:05:06:02:00:78:fe:0c:00:26:e1:00:00:00:00:00:00:00:00:02:18:08:0a:21:91:79:24:5a:f8:b5:e6:01:01:00:00:01:0d:00:5d:00:01:fe:ea:ff:ff:ff:ff:ff:ff:00:08:00:00:00:08:00:04:00:00:ff:ff:ff:ff:ff:ff:22:65:7a:90:0d:af:89:42:20:00:06:04:00:02:00:00:02:07:04:00:00:00:00:00:02:04:03:02:00:04:06:02:00:78:fe:0c:00:26:e1:00:00:00:00:00:00:00:00:02:18:08:0a:21:91:79:24:5a:f8:b5:e6:01:01:00:00:01:0d:00:5d:00:01:fe:eb:ff:ff:ff:ff:ff:ff:00:08:00:00:00:08:00:07:00:00:ff:ff:ff:ff:ff:ff:ce:3a:d5:b7:a7:67:89:42:20:00:06:04:00:02:00:00:02:07:04:00:00:00:00:00:02:04:03:02:00:07:06:02:00:78:fe:0c:00:26:e1:00:00:00:00:00:00:00:00:02:18:08:0a:21:91:79:24:5a:f8:b5:e6:01:01:00:00:01:0d:00:5d:00:01:fe:ec:ff:ff:ff:ff:ff:ff:00:08:00:00:00:08:00:06:00:00:ff:ff:ff:ff:ff:ff:86:b6:42:00:fb:7b:89:42:20:00:06:04:00:02:00:00:02:07:04:00:00:00:00:00:02:04:03:02:00:06:06:02:00:78:fe:0c:00:26:e1:00:00:00:00:00:00:00:00:02:18:08:0a:21:91:79:24:5a:f8:b5:e6:01:01:00:00";

        System.out.println(new String(hexBinStringToByteArray(hexBinStr), "UTF-8"));
        System.out.println("------------------");
        System.out.println(new String(hexBinStringToByteArray(hexBinStr), "euc-kr"));
        System.out.println("------------------");
        System.out.println(new String(hexBinStringToByteArray(hexBinStr), "ISO8859-1"));
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
