/*
 * Copyright 2026 Park Jun-Hong (parkjunhong77@gmail.com)
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
 * Date  : 2026. 4. 7. 오후 3:11:51
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package open.commons.core.utils;

import java.util.Locale;

import org.jspecify.annotations.Nullable;

/**
 * 로케일({@link Locale}) 처리를 위한 유틸리티 클래스입니다.<br>
 * 
 * <p>
 * <font color="red">[JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 메소드 <br>
 * [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거' </font>
 * </p>
 * 
 * @since 2026. 4. 7.
 * @version 3.0.0
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
public class LocaleUtils {

    private LocaleUtils() {
    }

    /**
     * JVM의 기본 로케일(Default Locale)을 반환합니다.
     *
     * @return 기본 {@link Locale} (절대 {@code null}을 반환하지 않음)
     * 
     * @since 2026. 4. 7.
     * @version 3.0.0
     */
    @SuppressWarnings("null")
    public static Locale defaultLocale() {
        return Locale.getDefault();
    }

    /**
     * 파라미터에 해당하는 {@link Locale}을 제공합니다. <br>
     * 입력값이 {@code null}이거나 유효하지 않은 경우, JVM의 기본 로케일로 대체(Fallback)됩니다.
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2026. 4. 7.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param locale
     *            요청할 로케일 객체 ({@code null} 허용)
     *
     * @return 파라미터의 {@link Locale}. 만약 파라미터가 {@code null}이면 JVM 기본 로케일을 반환 (절대 {@code null}이 아님)
     *
     * @since 2026. 4. 7.
     * @version 3.0.0
     */
    public static Locale requireLocale(@Nullable Locale locale) {
        return locale != null ? locale : requireLocale((String) null);
    }

    /**
     * 파라미터에 해당하는 {@link Locale}을 제공합니다. <br>
     * 입력값이 {@code null}이거나 지원되지 않는 로케일 문자열인 경우, 안전한 로케일로 대체(Fallback)됩니다.
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2026. 4. 7.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param languageTag
     *            로케일 문자열 태그 (예: "ko-KR", "en_US") ({@code null} 허용)
     *
     * @return 다음 조건에 따라 로케일을 반환합니다. (절대 {@code null}이 아님)
     *         <ul>
     *         <li>유효한 언어 태그인 경우: 파싱된 {@link Locale}</li>
     *         <li>파싱 불가능한 잘못된 태그인 경우: {@link Locale#ROOT} (언어 중립적 안전 로케일)</li>
     *         <li>파라미터가 {@code null}인 경우: JVM 기본 로케일 ({@link #defaultLocale()})</li>
     *         </ul>
     *
     * @since 2026. 4. 7.
     * @version 3.0.0
     */
    @SuppressWarnings("null")
    public static Locale requireLocale(@Nullable String languageTag) {
        if (languageTag == null) {
            return defaultLocale();
        }

        // [PATCH] 레거시 언더스코어 형식("ko_KR")을 BCP 47 표준 하이픈 형식("ko-KR")으로 치환하여 파싱 안정성 확보
        Locale parsed = Locale.forLanguageTag(languageTag.replace('_', '-'));

        // Locale.forLanguageTag는 파싱 실패 시 언어(Language) 속성이 비어있는 깡통 로케일을 반환합니다.
        // 이를 통해 유효하지 않은 쓰레기값 입력을 걸러내고 안전한 대체값(Fallback)을 제공합니다.
        return !parsed.getLanguage().isEmpty() ? parsed : Locale.ROOT;
    }
}