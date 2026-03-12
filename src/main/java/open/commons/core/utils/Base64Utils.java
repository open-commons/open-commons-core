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

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.NullMarked;

/**
 * {@link Decoder}, {@link Encoder} 를 이용하여 문자열 인코딩/디코딩 기능을 제공합니다.
 * 
 * @since 2026. 3. 12.
 * @version 3.0.0
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
@NullMarked
public class Base64Utils {

    private static final Encoder URL_ENCODER = Base64.getUrlEncoder();
    private static final Decoder URL_DECODER = Base64.getUrlDecoder();
    private static final Encoder ENCODER = Base64.getEncoder();
    private static final Decoder DECODER = Base64.getDecoder();

    /**
     * Base64 인코딩된 바이트 배열을 디코딩합니다.
     * 
     * @param bytes
     *            Base64 인코딩된 바이트 배열
     * @return 디코딩된 바이트 배열
     */
    public static byte[] decode(@NonNull byte[] bytes) {
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
    public static int decode(@NonNull byte[] src, @NonNull byte[] dst) {
        return DECODER.decode(src, dst);
    }

    /**
     * {@link ByteBuffer} 내의 Base64 인코딩된 데이터를 디코딩합니다.
     * 
     * @param buffer
     *            인코딩된 데이터가 포함된 ByteBuffer
     * @return 디코딩된 데이터를 포함하는 새로운 ByteBuffer
     */
    public static ByteBuffer decode(@NonNull ByteBuffer buffer) {
        return DECODER.decode(buffer);
    }

    /**
     * Base64 인코딩된 문자열을 디코딩합니다.
     * 
     * @param string
     *            Base64 인코딩된 문자열
     * @return 디코딩된 바이트 배열
     */
    public static byte[] decode(@NonNull String string) {
        return DECODER.decode(string);
    }

    /**
     * URL-Safe Base64 인코딩된 데이터를 UTF-8 문자열로 디코딩합니다.
     * 
     * @param base64EncodedString
     *            URL-Safe Base64 인코딩된 바이트 배열
     * @return 디코딩된 원본 문자열 (UTF-8)
     */
    public static String decodeFromUrlSafeString(@NonNull byte[] base64EncodedString) {
        return decodeFromUrlSafeString(base64EncodedString, StandardCharsets.UTF_8);
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
    public static String decodeFromUrlSafeString(@NonNull byte[] base64EncodedBytes, Charset charset) {
        byte[] decoded = URL_DECODER.decode(base64EncodedBytes);
        return new String(decoded, Objects.requireNonNullElse(charset, StandardCharsets.UTF_8));
    }

    /**
     * URL-Safe Base64 인코딩된 문자열을 UTF-8 문자열로 디코딩합니다.
     * 
     * @param base64EncodedString
     *            URL-Safe Base64 인코딩된 문자열
     * @return 디코딩된 원본 문자열 (UTF-8)
     */
    public static String decodeFromUrlSafeString(@NonNull String base64EncodedString) {
        return decodeFromUrlSafeString(base64EncodedString, StandardCharsets.UTF_8);
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
    public static String decodeFromUrlSafeString(@NonNull String base64EncodedString, Charset charset) {
        byte[] base64Bytes = base64EncodedString.getBytes(StandardCharsets.ISO_8859_1);
        byte[] decoded = URL_DECODER.decode(base64Bytes);

        return new String(decoded, Objects.requireNonNullElse(charset, StandardCharsets.UTF_8));
    }

    /**
     * Base64 인코딩된 바이트 배열을 UTF-8 문자열로 디코딩합니다.
     * 
     * @param bytes
     *            Base64 인코딩된 바이트 배열
     * @return 디코딩된 원본 문자열 (UTF-8)
     */
    public static String decodeToString(@NonNull byte[] bytes) {
        return new String(DECODER.decode(bytes), StandardCharsets.UTF_8);
    }

    /**
     * Base64 인코딩된 문자열을 UTF-8 문자열로 디코딩합니다.
     * 
     * @param string
     *            Base64 인코딩된 문자열
     * @return 디코딩된 원본 문자열 (UTF-8)
     */
    public static String decodeToString(@NonNull String string) {
        return new String(DECODER.decode(string), StandardCharsets.UTF_8);
    }

    /**
     * 바이트 배열을 Base64로 인코딩합니다.
     * 
     * @param bytes
     *            원본 바이트 배열
     * @return Base64 인코딩된 바이트 배열
     */
    public static byte[] encode(@NonNull byte[] bytes) {
        return ENCODER.encode(bytes);
    }

    /**
     * {@link ByteBuffer}의 데이터를 Base64로 인코딩합니다.
     * 
     * @param buffer
     *            원본 데이터를 포함하는 ByteBuffer
     * @return Base64 인코딩된 데이터를 포함하는 새로운 ByteBuffer
     */
    public static ByteBuffer encode(@NonNull ByteBuffer buffer) {
        return ENCODER.encode(buffer);
    }

    /**
     * 바이트 배열을 Base64 문자열로 인코딩합니다.
     * 
     * @param bytes
     *            원본 바이트 배열
     * @return Base64 인코딩된 문자열
     */
    public static String encodeToString(@NonNull byte[] bytes) {
        return ENCODER.encodeToString(bytes);
    }

    /**
     * 바이트 배열을 URL-Safe Base64 문자열로 인코딩합니다.
     * 
     * @param bytes
     *            원본 바이트 배열
     * @return URL-Safe Base64 인코딩된 문자열
     */
    public static String encodeToUrlSafeString(@NonNull byte[] bytes) {
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
    public static String encodeToUrlSafeString(@NonNull String string) {
        return encodeToUrlSafeString(string, StandardCharsets.UTF_8);
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
    public static String encodeToUrlSafeString(@NonNull String string, Charset charset) {
        return URL_ENCODER.encodeToString(string.getBytes(Objects.requireNonNullElse(charset, StandardCharsets.UTF_8)));
    }
}
