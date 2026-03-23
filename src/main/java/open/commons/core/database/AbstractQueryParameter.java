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
* Date  : 2013. 11. 8. 오후 1:23:13
*
* Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
* 
*/
package open.commons.core.database;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import open.commons.core.database.annotation.AQueryIndex;

/**
 * 
 * 
 * @since 2013. 11. 8.
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
public abstract class AbstractQueryParameter implements IQueryParameter {

    /** 클래스별 쿼리 인덱스 필드 메타데이터 캐시 */
    private static final Map<Class<?>, List<QueryFieldMeta>> CACHE = new ConcurrentHashMap<>();

    /**
     * 컬럼명을 순서대로 제공합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 6. 18.         parkjunhong77@gmail.com         최초 작성
     * 2026. 2. 27.         parkjunhong77@gmail.com         JDK 25 마이그레이션: 캐싱 및 Record 적용, 성능 최적화
     * </pre>
     *
     * @return 쿼리 파라미터 배열
     * @throws IllegalArgumentException
     *             파라미터 설정 오류 시 발생
     * @throws IllegalAccessException
     *             필드 접근 실패 시 발생
     *
     * @since 2021. 6. 18.
     * @version 1.9.0
     * 
     * @see open.commons.core.database.IQueryParameter#toParameters()
     */
    @Override
    public String[] toParameters() throws IllegalArgumentException, IllegalAccessException {
        Class<?> clazz = getClass();

        // 1. 캐시 확인 및 없으면 생성 (ComputeIfAbsent는 원자성 보장)
        List<QueryFieldMeta> metas = //
                CACHE.computeIfAbsent(clazz, key -> Arrays.stream(key.getDeclaredFields()) //
                        .filter(f -> f.isAnnotationPresent(AQueryIndex.class)) //
                        // 한 번만 수행하여 성능 최적화
                        .peek(f -> f.trySetAccessible()) //
                        .map(f -> new QueryFieldMeta(f, f.getAnnotation(AQueryIndex.class).index())) //
                        .toList());

        String[] params = new String[count()];

        // 2. 캐싱된 메타데이터를 사용하여 값 추출
        for (QueryFieldMeta meta : metas) {
            Object value = meta.field().get(this);
            params[meta.index()] = Objects.toString(value, null);
        }

        return params;
    }

    /**
     * 필드와 인덱스 정보를 담는 불변 레코드입니다.
     *
     * @param field
     *            리플렉션 필드 객체
     * @param index
     *            AQueryIndex에 설정된 인덱스 번호
     */
    private record QueryFieldMeta(Field field, int index) {
    }
}
