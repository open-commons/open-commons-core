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
// 아래 내용에 적용됨.
// - 'Encoder, Decoder' 제공 함수
// [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
// [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
@SuppressWarnings("null")
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
     * 
     * @throws NullPointerException
     *             파라미터({@code bytes})가 {@code null}인 경우 발생.
     */
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
     * 
     * @throws NullPointerException
     *             파라미터({@code src, dst 중에 1개라도})가 {@code null}인 경우 발생.
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
     * 
     * @throws NullPointerException
     *             파라미터({@code buffer})가 {@code null}인 경우 발생.
     */
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
     * 
     * @throws NullPointerException
     *             파라미터({@code string})가 {@code null}인 경우 발생.
     */
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
     * 
     * @throws NullPointerException
     *             파라미터({@code base64EncodedString})가 {@code null}인 경우 발생.
     */
    public static String decodeFromUrlSafeString(byte[] base64EncodedString) {
        Objects.requireNonNull(base64EncodedString);

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
     * 
     * @throws NullPointerException
     *             파라미터({@code base64EncodedBytes})가 {@code null}인 경우 발생.
     */
    public static String decodeFromUrlSafeString(byte[] base64EncodedBytes, Charset charset) {
        Objects.requireNonNull(base64EncodedBytes);

        byte[] decoded = URL_DECODER.decode(base64EncodedBytes);
        return new String(decoded, Objects.requireNonNullElse(charset, StandardCharsets.UTF_8));
    }

    /**
     * URL-Safe Base64 인코딩된 문자열을 UTF-8 문자열로 디코딩합니다.
     * 
     * @param base64EncodedString
     *            URL-Safe Base64 인코딩된 문자열
     * @return 디코딩된 원본 문자열 (UTF-8)
     * 
     * @throws NullPointerException
     *             파라미터({@code base64EncodedString})가 {@code null}인 경우 발생.
     */
    public static String decodeFromUrlSafeString(String base64EncodedString) {
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
     * 
     * @throws NullPointerException
     *             파라미터({@code base64EncodedString})가 {@code null}인 경우 발생.
     */
    public static String decodeFromUrlSafeString(String base64EncodedString, Charset charset) {
        Objects.requireNonNull(base64EncodedString);

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
     * 
     * @throws NullPointerException
     *             파라미터({@code bytes})가 {@code null}인 경우 발생.
     */
    public static String decodeToString(byte[] bytes) {
        Objects.requireNonNull(bytes);

        return new String(DECODER.decode(bytes), StandardCharsets.UTF_8);
    }

    /**
     * Base64 인코딩된 문자열을 UTF-8 문자열로 디코딩합니다.
     * 
     * @param string
     *            Base64 인코딩된 문자열
     * @return 디코딩된 원본 문자열 (UTF-8)
     * 
     * @throws NullPointerException
     *             파라미터({@code string})가 {@code null}인 경우 발생.
     */
    public static String decodeToString(String string) {
        Objects.requireNonNull(string);

        return new String(DECODER.decode(string), StandardCharsets.UTF_8);
    }

    /**
     * 바이트 배열을 Base64로 인코딩합니다.
     * 
     * @param bytes
     *            원본 바이트 배열
     * @return Base64 인코딩된 바이트 배열
     * 
     * @throws NullPointerException
     *             파라미터({@code bytes})가 {@code null}인 경우 발생.
     */
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
     * 
     * @throws NullPointerException
     *             파라미터({@code buffer})가 {@code null}인 경우 발생.
     */
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
     * 
     * @throws NullPointerException
     *             파라미터({@code bytes})가 {@code null}인 경우 발생.
     */
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
     * 
     * @throws NullPointerException
     *             파라미터({@code bytes})가 {@code null}인 경우 발생.
     */
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
     * 
     * @throws NullPointerException
     *             파라미터({@code string})가 {@code null}인 경우 발생.
     */
    public static String encodeToUrlSafeString(String string) {
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
     * 
     * @throws NullPointerException
     *             파라미터({@code string})가 {@code null}인 경우 발생.
     */
    public static String encodeToUrlSafeString(String string, Charset charset) {
        Objects.requireNonNull(string);

        return URL_ENCODER.encodeToString(string.getBytes(Objects.requireNonNullElse(charset, StandardCharsets.UTF_8)));
    }
}
