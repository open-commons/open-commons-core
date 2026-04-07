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
 * Date  : 2026. 3. 25. 오후 1:48:50
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package open.commons.core.utils;

/**
 * 
 * @since 2026. 3. 25.
 * @version 3.0.0
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
public class ClassUtils {

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2026. 3. 25.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     *
     * @since 2026. 3. 25.
     * @version 3.0.0
     */
    private ClassUtils() {
    }

    // 아래 내용에 적용됨.
    // - clazz.getSimpleName();
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static String getSimpleName(Class<?> clazz) {
        return clazz.getSimpleName();
    }

}
