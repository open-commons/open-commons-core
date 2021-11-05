/*
 * Copyright 2018 Park Jun-Hong (parkjunhong77@gmail.com)
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
 * Date  : 2018. 11. 20. 오후 2:07:49
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * 
 * @since 2018. 11. 20.
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public class EncryptUtils {

    public static final String CHARSET = "UTF-8";

    public static final String CIPHER_TRANSFORMATION = "AES/CBC/PKCS5Padding";

    private EncryptUtils() {
    }

    /**
     * 복호화한 결과를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2018. 11. 20.		박준홍			최초 작성
     * </pre>
     *
     * @param key
     *            암호화 키. (16자리 이상)
     * @param encText
     *            암호화된 데이터
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws InvalidAlgorithmParameterException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2018. 11. 20.
     * 
     * @see #decrypt(String, String, String, byte[], String)
     * @see #CHARSET
     * @see #CIPHER_TRANSFORMATION
     */
    public static String decrypt(String key, byte[] encText) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
            InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        return decrypt("AES", key, CHARSET, encText, CHARSET);
    }

    /**
     * 복호화한 결과를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2018. 11. 20.		박준홍			최초 작성
     * </pre>
     *
     * @param key
     *            암호화 키 (16자리 이상)
     * @param keyCharset
     *            암호화 키 charset
     * @param encText
     *            암호화된 데이터
     * @param textCharset
     *            암호화된 데이터 charset
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws InvalidAlgorithmParameterException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2018. 11. 20.
     */
    public static String decrypt(String key, String keyCharset, byte[] encText, String textCharset) throws UnsupportedEncodingException, NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        return decrypt("AES", key, keyCharset, encText, textCharset);
    }

    /**
     * 복호화한 결과를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2018. 11. 20.		박준홍			최초 작성
     * </pre>
     *
     * @param algorithm
     *            암호화 알고리즘
     * @param key
     *            암호화 키 (16자리 이상)
     * @param keyCharset
     *            암호화 키 charset
     * @param encText
     *            암호화된 데이터
     * @param textCharset
     *            암호화된 데이터 charset
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws InvalidAlgorithmParameterException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2018. 11. 20.
     */
    public static String decrypt(String algorithm, String key, String keyCharset, byte[] encText, String textCharset) throws UnsupportedEncodingException, NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

        SecretKeySpec keySpec = keySpec(algorithm, key, keyCharset);

        String iv = key.substring(0, 16);

        Cipher cihper = Cipher.getInstance(CIPHER_TRANSFORMATION);

        cihper.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes()));

        return new String(cihper.doFinal(encText), textCharset);
    }

    /**
     * 암호화 결과를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 11. 20.        박준홍         최초 작성
     * </pre>
     *
     * @param key
     *            암호화 키 (16자리 이상)
     * @param plainText
     *            데이터
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws UnsupportedEncodingException
     * @throws InvalidKeyException
     * @throws InvalidAlgorithmParameterException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2018. 11. 20.
     * 
     * @see #encrypt(String, String, String, String, String)
     * @see #CHARSET
     * @see #CIPHER_TRANSFORMATION
     */
    public static byte[] encrypt(String key, String plainText) throws NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, InvalidKeyException,
            InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        return encrypt("AES", key, CHARSET, plainText, CHARSET);
    }

    /**
     * 암호화 결과를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2018. 11. 20.		박준홍			최초 작성
     * </pre>
     *
     * @param key
     *            암호화 키 (16자리 이상)
     * @param keyCharset
     *            암호화 키 charset
     * @param plainText
     *            데이터
     * @param textCharset
     *            데이터 charset
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws UnsupportedEncodingException
     * @throws InvalidKeyException
     * @throws InvalidAlgorithmParameterException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2018. 11. 20.
     */
    public static byte[] encrypt(String key, String keyCharset, String plainText, String textCharset) //
            throws NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, InvalidKeyException, InvalidAlgorithmParameterException,
            IllegalBlockSizeException, BadPaddingException {
        return encrypt("AES", key, keyCharset, plainText, textCharset);
    }

    /**
     * 암호화 결과를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2018. 11. 20.		박준홍			최초 작성
     * </pre>
     *
     * @param algorithm
     *            암호화 알고리즘
     * @param key
     *            암호화 키 (16자리 이상)
     * @param keyCharset
     *            암호화 키 charset
     * @param plainText
     *            데이터
     * @param textCharset
     *            데이터 charset
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws UnsupportedEncodingException
     * @throws InvalidKeyException
     * @throws InvalidAlgorithmParameterException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2018. 11. 20.
     */
    public static byte[] encrypt(String algorithm, String key, String keyCharset, String plainText, String textCharset) //
            throws NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, InvalidKeyException, InvalidAlgorithmParameterException,
            IllegalBlockSizeException, BadPaddingException {

        SecretKeySpec keySpec = keySpec(algorithm, key, keyCharset);

        String iv = key.substring(0, 16);

        Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORMATION);

        cipher.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes()));

        return cipher.doFinal(plainText.getBytes(textCharset));
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2018. 11. 20.		박준홍			최초 작성
     * </pre>
     *
     * @param algorithm
     *            암호화 알고리즘
     * @param key
     *            키
     * @param charset
     *            key charset
     * @return
     * @throws UnsupportedEncodingException
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2018. 11. 20.
     */
    private static SecretKeySpec keySpec(String algorithm, String key, String charset) throws UnsupportedEncodingException {

        byte[] keyBytes = key.getBytes(charset);

        byte[] keySpecBytes = new byte[16];

        System.arraycopy(keyBytes // source
                , 0 // start point
                , keySpecBytes // target
                , 0, keyBytes.length > keySpecBytes.length ? 16 : keyBytes.length // copy length
        );

        SecretKeySpec keySpec = new SecretKeySpec(keySpecBytes, algorithm);

        return keySpec;
    }
}
