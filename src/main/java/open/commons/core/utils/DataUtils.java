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
* This file is generated under this project, "open-commons-core".
*
* Date  : 2014. 9. 26. 오후 1:39:53
*
* Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
* 
*/

package open.commons.core.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import open.commons.core.annotation.Hide;

public class DataUtils {

    /** 클래스별 {@link Hide} 필드 리스트 전역 캐시 (상속 계층 포함) */
    private static final Map<Class<?>, List<Field>> HIDE_FIELD_CACHE = new ConcurrentHashMap<>();

    private DataUtils() {
    }

    /**
     * 컬렉션에 포함된 모든 객체의 {@link Hide} 어노테이션 필드를 초기화합니다. <br>
     * *
     * 
     * <pre>
     * [개정이력]
     * 날짜          | 작성자   |   내용
     * ------------------------------------------
     * 2014. 9. 26.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param objects
     *            {Collection<?>} 대상 컬렉션
     *
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2014. 9. 26.
     */
    public static void hide(Collection<?> objects) throws IllegalArgumentException, IllegalAccessException {
        if (objects == null || objects.isEmpty()) {
            return;
        }
        for (Object object : objects) {
            hide(object);
        }
    }

    /**
     * 객체에서 {@link Hide} 어노테이션이 선언된 필드의 값을 강제로 초기화합니다. <br>
     * *
     * 
     * <pre>
     * [개정이력]
     * 날짜          | 작성자   |   내용
     * ------------------------------------------
     * 2014. 9. 26.     parkjunhong77@gmail.com         최초 작성
     * 2026. 3. 9.      parkjunhong77@gmail.com         (3.0.0) JDK 25 마이그레이션: 스캔과 실행 분리 및 필드 캐싱 적용
     * </pre>
     *
     * @param object
     *            {Object} 대상 객체
     *
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     *
     * @since 2014. 9. 26.
     * @version 3.0.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static void hide(final Object object) throws IllegalArgumentException, IllegalAccessException {
        if (object == null) {
            return;
        }

        Class<?> clazz = object.getClass();

        // [최적화] 클래스 메타데이터 추출(Scan)과 실제 데이터 초기화(Reset)를 분리
        List<Field> hideFields = HIDE_FIELD_CACHE.computeIfAbsent(clazz, k -> {
            List<Field> collected = new ArrayList<>();
            Class<?> current = k;

            // 상속 계층을 직접 순회하며 @Hide 필드 수집 (최초 1회만 실행)
            while (current != null && !Object.class.equals(current)) {
                for (Field f : current.getDeclaredFields()) {
                    if (f.getAnnotation(Hide.class) != null) {
                        // 런타임 성능을 위해 캐싱 시점에 미리 접근 권한 획득
                        f.trySetAccessible();
                        collected.add(f);
                    }
                }
                current = current.getSuperclass();
            }
            return List.copyOf(collected);
        });

        // [실행] 추출된 필드 정보를 바탕으로 데이터 리셋 수행 (컴파일 오류 수정)
        for (Field field : hideFields) {
            ReflectionUtils.resetFieldForced(object, field);
        }
    }

}
