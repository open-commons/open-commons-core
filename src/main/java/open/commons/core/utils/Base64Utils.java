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
* Date  : 2013. 3. 5. 오전 8:31:36
*
* Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
* 
*/
package open.commons.core.utils;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.Objects;

/**
 * {@link Decoder}, {@link Encoder} 를 이용하여 문자열 인코딩/디코딩 기능을 제공합니다.
 * 
 * @since 2026. 3. 12.
 * @version 3.0.0
 * 
 */
public class Base64Utils {

    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    private static final Encoder URL_ENCODER = Base64.getUrlEncoder();
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    private static final Decoder URL_DECODER = Base64.getUrlDecoder();
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    private static final Encoder ENCODER = Base64.getEncoder();
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    private static final Decoder DECODER = Base64.getDecoder();

    /**
     * Base64 인코딩된 바이트 배열을 디코딩합니다.
     * 
     * @param bytes
     *            Base64 인코딩된 바이트 배열
     * @return 디코딩된 바이트 배열
     */
    // 아래 내용에 적용됨.
    // - DECODER.decode(bytes);
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static byte[] decode(byte[] bytes) {
        Objects.requireNonNull(bytes);

        return DECODER.decode(bytes);
    }

    /**
     * Base64 인코딩된 소스 바이트 배열을 디코딩하여 대상 바이트 배열에 기록합니다.
     * 
     * @param src
     *            소스 바이트 배열
     * @param dst
     *            대상 바이트 배열
     * @return 대상 바이트 배열에 기록된 바이트 수
     */
    public static int decode(byte[] src, byte[] dst) {
        ObjectUtils.requireNonNulls(src, dst);

        return DECODER.decode(src, dst);
    }

    /**
     * {@link ByteBuffer} 내의 Base64 인코딩된 데이터를 디코딩합니다.
     * 
     * @param buffer
     *            인코딩된 데이터가 포함된 ByteBuffer
     * @return 디코딩된 데이터를 포함하는 새로운 ByteBuffer
     */
    // 아래 내용에 적용됨.
    // - DECODER.decode(buffer)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static ByteBuffer decode(ByteBuffer buffer) {
        Objects.requireNonNull(buffer);

        return DECODER.decode(buffer);
    }

    /**
     * Base64 인코딩된 문자열을 디코딩합니다.
     * 
     * @param string
     *            Base64 인코딩된 문자열
     * @return 디코딩된 바이트 배열
     */
    // 아래 내용에 적용됨.
    // - DECODER.decode(string);
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static byte[] decode(String string) {
        Objects.requireNonNull(string);

        return DECODER.decode(string);
    }

    /**
     * URL-Safe Base64 인코딩된 데이터를 UTF-8 문자열로 디코딩합니다.
     * 
     * @param base64EncodedString
     *            URL-Safe Base64 인코딩된 바이트 배열
     * @return 디코딩된 원본 문자열 (UTF-8)
     */
    public static String decodeFromUrlSafeString(byte[] base64EncodedString) {
        Objects.requireNonNull(base64EncodedString);

        return decodeFromUrlSafeString(base64EncodedString, CharUtils.defaultCharset());
    }

    /**
     * URL-Safe Base64 인코딩된 데이터를 지정된 {@link Charset} 문자열로 디코딩합니다.
     * 
     * @param base64EncodedBytes
     *            URL-Safe Base64 인코딩된 바이트 배열
     * @param charset
     *            원본 문자열 복원에 사용할 캐릭터셋 (null인 경우 UTF-8 사용)
     * @return 디코딩된 원본 문자열
     */
    public static String decodeFromUrlSafeString(byte[] base64EncodedBytes, Charset charset) {
        Objects.requireNonNull(base64EncodedBytes);

        byte[] decoded = URL_DECODER.decode(base64EncodedBytes);
        return new String(decoded, Objects.requireNonNullElse(charset, CharUtils.defaultCharset()));
    }

    /**
     * URL-Safe Base64 인코딩된 문자열을 UTF-8 문자열로 디코딩합니다.
     * 
     * @param base64EncodedString
     *            URL-Safe Base64 인코딩된 문자열
     * @return 디코딩된 원본 문자열 (UTF-8)
     */
    public static String decodeFromUrlSafeString(String base64EncodedString) {
        return decodeFromUrlSafeString(base64EncodedString, CharUtils.defaultCharset());
    }

    /**
     * URL-Safe Base64 인코딩된 문자열을 지정된 {@link Charset} 문자열로 디코딩합니다.
     * 
     * @param base64EncodedString
     *            URL-Safe Base64 인코딩된 문자열
     * @param charset
     *            원본 문자열 복원에 사용할 캐릭터셋 (null인 경우 UTF-8 사용)
     * @return 디코딩된 원본 문자열
     */
    public static String decodeFromUrlSafeString(String base64EncodedString, Charset charset) {
        Objects.requireNonNull(base64EncodedString);

        byte[] base64Bytes = base64EncodedString.getBytes(StandardCharsets.ISO_8859_1);
        byte[] decoded = URL_DECODER.decode(base64Bytes);

        return new String(decoded, Objects.requireNonNullElse(charset, CharUtils.defaultCharset()));
    }

    /**
     * Base64 인코딩된 바이트 배열을 UTF-8 문자열로 디코딩합니다.
     * 
     * @param bytes
     *            Base64 인코딩된 바이트 배열
     * @return 디코딩된 원본 문자열 (UTF-8)
     */
    public static String decodeToString(byte[] bytes) {
        Objects.requireNonNull(bytes);

        return new String(DECODER.decode(bytes), CharUtils.defaultCharset());
    }

    /**
     * Base64 인코딩된 문자열을 UTF-8 문자열로 디코딩합니다.
     * 
     * @param string
     *            Base64 인코딩된 문자열
     * @return 디코딩된 원본 문자열 (UTF-8)
     */
    public static String decodeToString(String string) {
        Objects.requireNonNull(string);

        return new String(DECODER.decode(string), CharUtils.defaultCharset());
    }

    /**
     * 바이트 배열을 Base64로 인코딩합니다.
     * 
     * @param bytes
     *            원본 바이트 배열
     * @return Base64 인코딩된 바이트 배열
     */
    // 아래 내용에 적용됨.
    // - ENCODER.encode(bytes);
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static byte[] encode(byte[] bytes) {
        Objects.requireNonNull(bytes);

        return ENCODER.encode(bytes);
    }

    /**
     * {@link ByteBuffer}의 데이터를 Base64로 인코딩합니다.
     * 
     * @param buffer
     *            원본 데이터를 포함하는 ByteBuffer
     * @return Base64 인코딩된 데이터를 포함하는 새로운 ByteBuffer
     */
    // 아래 내용에 적용됨.
    // - ENCODER.encode(buffer);
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static ByteBuffer encode(ByteBuffer buffer) {
        Objects.requireNonNull(buffer);

        return ENCODER.encode(buffer);
    }

    /**
     * 바이트 배열을 Base64 문자열로 인코딩합니다.
     * 
     * @param bytes
     *            원본 바이트 배열
     * @return Base64 인코딩된 문자열
     */
    // 아래 내용에 적용됨.
    // - ENCODER.encodeToString(bytes);
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static String encodeToString(byte[] bytes) {
        Objects.requireNonNull(bytes);

        return ENCODER.encodeToString(bytes);
    }

    /**
     * 바이트 배열을 URL-Safe Base64 문자열로 인코딩합니다.
     * 
     * @param bytes
     *            원본 바이트 배열
     * @return URL-Safe Base64 인코딩된 문자열
     */
    // 아래 내용에 적용됨.
    // - URL_ENCODER.encodeToString(bytes);
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static String encodeToUrlSafeString(byte[] bytes) {
        Objects.requireNonNull(bytes);

        return URL_ENCODER.encodeToString(bytes);
    }

    /**
     * 문자열을 지정된 {@link Charset}으로 바이트화한 후 URL-Safe Base64 문자열로 인코딩합니다.
     * 
     * @param string
     *            원본 문자열
     * @param charset
     *            문자열 인코딩에 사용할 캐릭터셋 (null인 경우 UTF-8 사용)
     * @return URL-Safe Base64 인코딩된 문자열
     */
    public static String encodeToUrlSafeString(String string) {
        return encodeToUrlSafeString(string, CharUtils.defaultCharset());
    }

    /**
     * 문자열을 지정된 {@link Charset}으로 바이트화한 후 URL-Safe Base64 문자열로 인코딩합니다.
     * 
     * @param string
     *            원본 문자열
     * @param charset
     *            문자열 인코딩에 사용할 캐릭터셋 (null인 경우 UTF-8 사용)
     * @return URL-Safe Base64 인코딩된 문자열
     */
    // 아래 내용에 적용됨.
    // - URL_ENCODER.encodeToString(string.getBytes(CharUtils.requireCharset(charset)));
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static String encodeToUrlSafeString(String string, Charset charset) {
        Objects.requireNonNull(string);

        return URL_ENCODER.encodeToString(string.getBytes(CharUtils.requireCharset(charset)));
    }
}
