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

/**
*/
package open.commons.core.utils;

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.jspecify.annotations.Nullable;

import open.commons.core.annotation.ComparableValue;

/**
 * 객체의 비교 연산을 지원하는 유틸리티 클래스입니다.
 *
 * @author Park Jun-Hong.(mail_to:parkjunhong77@gmail.com)
 * @since 2014. 11. 7.
 */
public class ComparableUtils {

    @SuppressWarnings("null")
    private static final Comparator<Field> COMPARABLE_VALUE_SORTER = Comparator
            .comparingInt(field -> field.getAnnotation(ComparableValue.class).order());

    // 클래스 메타데이터 전역 캐시 (Thread-Safe)
    private static final Map<Class<?>, List<Field>> FIELD_CACHE = new ConcurrentHashMap<>();

    private ComparableUtils() {
        // 유틸리티 클래스 인스턴스화 방지
    }

    /**
     * 두 배열에 대한 순차적 비교 결과를 제공합니다.
     */
    public static <T extends Comparable<T>> int comparable(T @Nullable [] obj1, T @Nullable [] obj2) {
        if (obj1 == obj2)
            return 0;
        if (obj1 == null)
            return -1;
        if (obj2 == null)
            return 1;

        int len = Math.min(obj1.length, obj2.length);
        for (int i = 0; i < len; i++) {
            int retValue = compare(obj1[i], obj2[i]); // 일관성을 위해 compare 재사용
            if (retValue != 0) {
                return retValue;
            }
        }
        return Integer.compare(obj1.length, obj2.length);
    }

    /**
     * 두 객체에 대한 비교 결과(Nulls First 기준)를 제공합니다. <br>
     * *
     * 
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 8. 15.     parkjunhong77@gmail.com         최초 작성
     * 2026. 3. 9.      parkjunhong77@gmail.com         (3.0.0) Null 평가 논리 일관성 통일 (Nulls First 기준)
     * </pre>
     *
     * @param <T>
     *            데이터 타입
     * @param o1
     *            비교 대상 1
     * @param o2
     *            비교 대상 2
     * @return 비교 결과
     *
     * @since 2021. 8. 15.
     * @version 3.0.0
     */
    public static <T extends @Nullable Comparable<T>> int compare(T o1, T o2) {
        if (o1 == o2) {
            return 0; // 둘 다 null이거나 같은 객체 참조인 경우
        } else if (o1 == null) {
            return -1; // Null을 더 작은 값(Nulls First)으로 일관성 통일
        } else if (o2 == null) {
            return 1;
        } else {
            return o1.compareTo(o2);
        }
    }

    /**
     * {@link ComparableValue}이 사용된 필드만 이용해서 동적으로 비교 결과(Nulls First 기준)를 반환합니다.
     * 단, 해당 필드는 {@link Comparable}을 구현해야만 합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2014. 11. 7.     parkjunhong77@gmail.com         최초 작성
     * 2026. 3. 9.      parkjunhong77@gmail.com         (3.0.0) 리플렉션 캐싱 적용, 동시성 버그 수정, Both Null 버그 수정
     * </pre>
     * 
     * * @param obj1 비교 대상 1
     * 
     * @param obj2
     *            비교 대상 2
     * @return 비교 결과
     *
     * @since 2014. 11. 7.
     * @version 3.0.0
     * @see ComparableValue
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static <T extends @Nullable Object> int compareTo(T obj1, T obj2) {
        if (obj1 == obj2)
            return 0;
        if (obj1 == null)
            return -1;
        if (obj2 == null)
            return 1;

        // 클래스 검증 (서로 다른 클래스인 경우 비교 불가)
        if (obj1.getClass() != obj2.getClass()) {
            throw new IllegalArgumentException("서로 다른 타입의 객체는 비교할 수 없습니다.");
        }

        // 캐시에서 정렬 및 접근 허용이 완료된 필드 목록을 즉시 가져옵니다. (O(1) 성능)
        List<Field> fields = getSortedComparableFields(obj1.getClass());

        try {
            for (Field field : fields) {
                Object value1 = field.get(obj1);
                Object value2 = field.get(obj2);

                int c;
                if (value1 == value2) {
                    // [버그 수정] 둘 다 null이거나 동일 객체일 경우 동등(0)으로 판단
                    c = 0;
                } else if (value1 == null) {
                    c = -1;
                } else if (value2 == null) {
                    c = 1;
                } else {
                    c = ((Comparable) value1).compareTo((Comparable) value2);
                }

                if (c != 0) {
                    return c;
                }
            }
        } catch (IllegalAccessException e) {
            // 캐시 생성 시점에 trySetAccessible()을 호출했음에도 실패하는 강력한 모듈 캡슐화(JPMS) 환경의
            // 런타임 오류
            throw new RuntimeException("필드 값에 접근할 수 없습니다. (모듈 시스템의 exports/opens 설정 확인 필요)", e);
        }

        return 0;
    }

    /**
     * 캐시에서 클래스의 정렬된 필드 목록을 조회하거나 초기화합니다.
     */
    // 아래 내용에 적용됨.
    // - 전체
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    private static List<Field> getSortedComparableFields(Class<?> clazz) {
        return FIELD_CACHE.computeIfAbsent(clazz, k -> {
            // AnnotationUtils는 사용자의 외부 유틸리티라 가정합니다.
            List<Field> fields = AnnotationUtils.getAnnotatedFields(k, ComparableValue.class);
            fields.sort(COMPARABLE_VALUE_SORTER);

            // 루프 내부에서 스위칭하지 않고, 캐싱 시점에 단 한 번만 접근 권한을 획득하여 동시성 장애 원천 차단
            for (Field field : fields) {
                // JDK 9+: 강제 접근보다 안전한 trySetAccessible 사용
                field.trySetAccessible();
            }
            return List.copyOf(fields); // 불변 리스트로 반환하여 안정성 보장
        });
    }
}