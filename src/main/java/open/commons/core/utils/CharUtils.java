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
package open.commons.core.utils;

import java.lang.Character.UnicodeBlock;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import org.jspecify.annotations.Nullable;

/**
 * 문자({@code char}) 및 인코딩 처리를 위한 유틸리티 클래스입니다.
 * 
 * @since 2020. 11. 9.
 */
public class CharUtils {

    /** 한글 처리를 위한 유니코드 블록 집합 */
    public static final UnicodeBlock[] UNICODE_BLOCK_HANGUL = { //
            Character.UnicodeBlock.HANGUL_COMPATIBILITY_JAMO, //
            Character.UnicodeBlock.HANGUL_JAMO, //
            Character.UnicodeBlock.HANGUL_SYLLABLES, //
            Character.UnicodeBlock.HANGUL_JAMO_EXTENDED_A, //
            Character.UnicodeBlock.HANGUL_JAMO_EXTENDED_B, //
    };

    /**
     * JVM의 기본 문자셋(Default Charset)을 반환합니다.
     * 
     * @return 기본 {@link Charset} (절대 {@code null}을 반환하지 않음)
     */
    public static Charset defaultCharset() {
        return Objects.requireNonNull(Charset.defaultCharset());
    }

    /**
     * 문자가 한글인지 여부를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2020. 11. 9.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param c
     *            검사할 문자
     * 
     * @return 한글 유니코드 블록에 포함될 경우 {@code true}, 그렇지 않으면 {@code false}
     *
     * @since 2020. 11. 9.
     * 
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
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2020. 11. 9.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param c
     *            검사할 문자의 유니코드 코드 포인트(Code Point)
     * 
     * @return 한글 유니코드 블록에 포함될 경우 {@code true}, 그렇지 않으면 {@code false}
     *
     * @since 2020. 11. 9.
     */
    public static final boolean isKorean(int c) {
        // [PATCH] Character.UnicodeBlock.of(c)는 정의되지 않은 블록일 경우 null을 반환할 수 있으나, <br>
        // hangul.equals(b) 구조로 인해 NullPointerException 없이 안전하게 false가 반환됩니다.
        UnicodeBlock b = Character.UnicodeBlock.of(c);
        for (UnicodeBlock hangul : UNICODE_BLOCK_HANGUL) {
            if (hangul.equals(b)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 파라미터에 해당하는 {@link Charset}을 제공합니다. <br>
     * 입력값이 {@code null}이거나 유효하지 않은 경우, JVM의 기본 문자셋으로 대체(Fallback)됩니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2026. 3. 11.      parkjunhong77@gmail.com     최초 작성
     * 2026. 4. 7.       parkjunhong77@gmail.com     (3.0.0) Javadoc의 잘못된 반환 명세(또는 null) 수정 (항상 Non-Null 반환)
     * </pre>
     *
     * @param charset
     *            요청할 문자열 셋 객체 ({@code null} 허용)
     *
     * @return 파라미터의 {@link Charset}. 만약 파라미터가 {@code null}이면 JVM 기본 문자셋을 반환 (절대 {@code null}이 아님)
     *
     * @since 2026. 3. 11.
     * @version 3.0.0
     */
    public static Charset requireCharset(@Nullable Charset charset) {
        return charset != null ? charset : requireCharset((String) null);
    }

    /**
     * 파라미터에 해당하는 {@link Charset}을 제공합니다. <br>
     * 입력값이 {@code null}이거나 지원되지 않는 문자셋 이름인 경우, 안전한 문자셋으로 대체(Fallback)됩니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2026. 3. 11.      parkjunhong77@gmail.com     최초 작성
     * 2026. 4. 7.       parkjunhong77@gmail.com     (3.0.0) Javadoc의 잘못된 반환 명세(또는 null) 수정 (항상 Non-Null 반환)
     * </pre>
     *
     * @param charset
     *            문자열 셋 이름 ({@code null} 허용)
     *
     * @return 다음 조건에 따라 문자셋을 반환합니다. (절대 {@code null}이 아님)
     *         <ul>
     *         <li>지원되는 이름인 경우: 해당 {@link Charset}</li>
     *         <li>지원되지 않는 이름인 경우: {@link StandardCharsets#UTF_8}</li>
     *         <li>파라미터가 {@code null}인 경우: JVM 기본 문자셋 ({@link #defaultCharset()})</li>
     *         </ul>
     *
     * @since 2026. 3. 11.
     * @version 3.0.0
     */
    public static Charset requireCharset(@Nullable String charset) {
        return charset != null //
                ? Charset.isSupported(charset) //
                        ? Charset.forName(charset) //
                        : StandardCharsets.UTF_8 //
                : defaultCharset();
    }

    /**
     * {@code char[]} 배열을 {@code byte[]} 배열로 변환해서 반환합니다. <br>
     * 
     * <b>[경고]</b> 이 메소드는 16비트 {@code char}를 8비트 {@code byte}로 명시적 캐스팅(Truncation)하므로, <br>
     * 영문자/숫자(ASCII) 범위를 초과하는 다국어(예: 한글)가 포함될 경우 데이터가 훼손(깨짐)될 수 있습니다. <br>
     * 안전한 문자열 인코딩이 필요한 경우 {@link String#getBytes(Charset)} 사용을 권장합니다.
     * 
     * @param cs
     *            변환할 문자 배열
     * 
     * @return 하위 8비트만 추출된 바이트 배열
     * 
     * @throws NullPointerException
     *             파라미터({@code cs})가 {@code null}인 경우 발생.
     */
    public static byte[] toBytes(char[] cs) {
        Objects.requireNonNull(cs);

        byte[] rtnBytes = new byte[cs.length];

        for (int i = 0; i < cs.length; i++) {
            rtnBytes[i] = (byte) cs[i];
        }

        return rtnBytes;
    }
}