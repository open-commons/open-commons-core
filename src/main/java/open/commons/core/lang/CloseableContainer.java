/*
 * Copyright 2021 Park Jun-Hong (parkjunhong77@gmail.com)
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
 * Date  : 2021. 7. 1. 오후 2:11:35
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.core.lang;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import jakarta.annotation.Resource;

import open.commons.core.utils.IOUtils;

/**
 * {@link Resource}와 함께 정의된 {@link AutoCloseable} Instance Field 들을 자동으로 해제({@link AutoCloseable#close()})하는 기능을 제공.
 * 
 * @since 2021. 7. 1.
 * @version 1.8.0
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
public interface CloseableContainer extends AutoCloseable {

    /**
     * <br>
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 7. 1.      parkjunhong77@gmail.com         최초 작성
     * 2026. 3. 4.      parkjunhong77@gmail.com         (3.0.0) JDK 25 마이그레이션: 캐싱 적용 및 잠재적 어노테이션 타겟 버그 수정
     * </pre>
     *
     * @throws Exception
     *             자원 해제 중 예외 발생 시
     *
     * @since 2021. 7. 1.
     * @version 3.0.0
     * 
     * @see java.lang.AutoCloseable#close()
     */
    // 아래 내용에 적용됨.
    // - IOUtils.close(resources);
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    @Override
    default void close() throws Exception {

        // 1. 캐시에서 리소스 필드 목록 획득 (최초 1회만 리플렉션 스캔 수행)
        List<Field> resourceFields = CacheHolder.FIELDS_CACHE.computeIfAbsent(getClass(), clazz -> Arrays.stream(clazz.getDeclaredFields())
                // 기존 fieldClass.isAnnotationPresent -> field.isAnnotationPresent 로 수정
                .filter(f -> f.isAnnotationPresent(Resource.class) && AutoCloseable.class.isAssignableFrom(f.getType())) //
                // JDK 25: 캡슐화 대응 및 실행 시점 성능 극대화
                .peek(f -> f.trySetAccessible()).toList());

        // 2. 캐시된 필드를 사용하여 인스턴스 값 추출 및 중복 제거
        AutoCloseable[] resources = resourceFields.stream().map(f -> {
            try {
                return (AutoCloseable) f.get(this);
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Fail to access a " + Resource.class.getName() + " instance field: " + f.getName(), e);
            }
        }).filter(Objects::nonNull)
                // 기존 HashSet을 대체하는 Stream API 중복 제거
                .distinct() //
                .toArray(AutoCloseable[]::new) //
        ;

        // 3. 자원 해제
        IOUtils.close(resources);
    }

    /**
     * 클래스별 닫기 가능한 리소스 필드 메타데이터 캐시가 private 으로 선언되어 외부 클래스나 구현체에서는 절대 접근 불가
     * 
     * @since 2026. 3. 4.
     * @version 3.0.0.
     */
    static final class CacheHolder {
        private static final Map<Class<?>, List<Field>> FIELDS_CACHE = new ConcurrentHashMap<>();

        private CacheHolder() {
        };
    }
}
