/*
 * Copyright 2020 Park Jun-Hong (parkjunhong77@gmail.com)
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
 * This file is generated under this project, "open-commons-core".
 *
 * Date  : 2020. 12. 17. 오후 1:36:27
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package test;

import java.io.UnsupportedEncodingException;
import java.util.Collections;

import open.commons.core.utils.ByteUtils;
import open.commons.core.utils.IntegerUtils;

/**
 * 
 * @since 2020. 12. 17.
 * @version 1.8.0
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public class TestByteUtils {

    void hexBinStringToByteArray() throws UnsupportedEncodingException {

        String hexBinStr = "01:0d:00:5d:00:01:fe:e8:ff:ff:ff:ff:ff:ff:00:08:00:00:00:08:00:08:00:00:ff:ff:ff:ff:ff:ff:be:86:7d:29:ab:be:89:42:20:00:06:04:00:02:00:00:02:07:04:00:00:00:00:00:02:04:03:02:00:08:06:02:00:78:fe:0c:00:26:e1:00:00:00:00:00:00:00:00:02:18:08:0a:21:91:79:24:5a:f8:b5:e6:01:01:00:00:01:0d:00:5d:00:01:fe:e9:ff:ff:ff:ff:ff:ff:00:08:00:00:00:08:00:05:00:00:ff:ff:ff:ff:ff:ff:2a:67:47:23:81:d5:89:42:20:00:06:04:00:02:00:00:02:07:04:00:00:00:00:00:02:04:03:02:00:05:06:02:00:78:fe:0c:00:26:e1:00:00:00:00:00:00:00:00:02:18:08:0a:21:91:79:24:5a:f8:b5:e6:01:01:00:00:01:0d:00:5d:00:01:fe:ea:ff:ff:ff:ff:ff:ff:00:08:00:00:00:08:00:04:00:00:ff:ff:ff:ff:ff:ff:22:65:7a:90:0d:af:89:42:20:00:06:04:00:02:00:00:02:07:04:00:00:00:00:00:02:04:03:02:00:04:06:02:00:78:fe:0c:00:26:e1:00:00:00:00:00:00:00:00:02:18:08:0a:21:91:79:24:5a:f8:b5:e6:01:01:00:00:01:0d:00:5d:00:01:fe:eb:ff:ff:ff:ff:ff:ff:00:08:00:00:00:08:00:07:00:00:ff:ff:ff:ff:ff:ff:ce:3a:d5:b7:a7:67:89:42:20:00:06:04:00:02:00:00:02:07:04:00:00:00:00:00:02:04:03:02:00:07:06:02:00:78:fe:0c:00:26:e1:00:00:00:00:00:00:00:00:02:18:08:0a:21:91:79:24:5a:f8:b5:e6:01:01:00:00:01:0d:00:5d:00:01:fe:ec:ff:ff:ff:ff:ff:ff:00:08:00:00:00:08:00:06:00:00:ff:ff:ff:ff:ff:ff:86:b6:42:00:fb:7b:89:42:20:00:06:04:00:02:00:00:02:07:04:00:00:00:00:00:02:04:03:02:00:06:06:02:00:78:fe:0c:00:26:e1:00:00:00:00:00:00:00:00:02:18:08:0a:21:91:79:24:5a:f8:b5:e6:01:01:00:00";

        System.out.println(new String(ByteUtils.hexBinStringToByteArray(hexBinStr), "UTF-8"));
        System.out.println("------------------");
        System.out.println(new String(ByteUtils.hexBinStringToByteArray(hexBinStr), "euc-kr"));
        System.out.println("------------------");
        System.out.println(new String(ByteUtils.hexBinStringToByteArray(hexBinStr), "ISO8859-1"));
    }

    void toIPV4(byte[] bytes) {
        System.out.println(ByteUtils.toIPv4Expr(bytes));
    }

    void upset() {
        byte b = 0x01;
        System.out.println(IntegerUtils.toBinary32String(b, 8));
        System.out.println(b);

        b = ByteUtils.upset(b);
        System.out.println(IntegerUtils.toBinary32String(b, 8));
        System.out.println(b);

        b = ByteUtils.upset(b);
        System.out.println(IntegerUtils.toBinary32String(b, 8));
        System.out.println(b);
    }

    void upsetArray() {
        byte[] bytes = "1234567".getBytes();

        System.out.println(new String(bytes));

        // byte[] upsets = upsetArray(bytes);
        // System.out.println(new String(upsets));

        ByteUtils.upsetArray(bytes);
        System.out.println(new String(bytes));

        ByteUtils.upsetArray(bytes);
        System.out.println(new String(bytes));
    }

    void xor() {
        byte b = (byte) 255;
        System.out.printf("byte(%d) -> int(%d), int=%d\n", b, ByteUtils.toInt(b), (int) b);

        System.out.println(ByteUtils.hexBinString(ByteUtils.xor(new byte[] { (byte) 0xff }, new byte[] { (byte) 0x00 })));
    }

    void hexString(byte[] bytes) {
        System.out.println(ByteUtils.hexBinString("0x", bytes));
    }

    void macExprString(byte[] bytes) {
        System.out.println(ByteUtils.toMACExpr(bytes));
    }

    /**
     * @param args
     * @throws UnsupportedEncodingException
     */
    public static void main(String[] args) throws UnsupportedEncodingException {

        TestByteUtils tester = new TestByteUtils();
        tester.upset();
        System.out.println(String.join("", Collections.nCopies(50, ".")));
        byte[] bytes = new byte[] { (byte) 0xC0, (byte) 0xA8, 0, 1 };
        tester.toIPV4(bytes);
        System.out.println(String.join("", Collections.nCopies(50, ".")));
        tester.hexString(bytes);
        System.out.println(String.join("", Collections.nCopies(50, ".")));
        tester.macExprString(bytes);

    }
}
