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
 * Date  : 2017. 12. 4. 오후 4:57:19
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.core.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.jspecify.annotations.Nullable;

/**
 * 
 * @since 2017. 12. 4.
 * 
 */
public class MathUtils {

    /**
     * 
     * @since 2017. 12. 4.
     */
    private MathUtils() {
    }

    /**
     * 조합(Combination, nCr)의 경우의 수를 제공합니다. <br>
     * 산술 오버플로우를 최소화하기 위해 대칭성({@code nCr == nC(n-r)}) 및 반복적 약분 알고리즘을 사용합니다.
     * 
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2023. 5. 22.     parkjunhong77@gmail.com         최초 작성
     * 2026. 4. 1.      parkjunhong77@gmail.com         (3.0.0) 오버플로우 방지 알고리즘 및 대칭성 최적화 적용
     * </pre>
     *
     * @param n
     *            전체 데이터 개수 (n > 0)
     * @param r
     *            선택할 데이터 개수 (r > 0, n >= r)
     * 
     * @return 계산된 조합의 경우의 수
     * 
     * @throws IllegalArgumentException
     *             {@code n}과 {@code r}의 수학적 조건이 맞지 않는 경우 발생.
     *
     * @since 2023. 5. 22.
     * @version 3.0.0
     */
    public static long combination(long n, long r) {
        if (n < 1 || r < 1 || n < r) {
            throw ExceptionUtils.newException(IllegalArgumentException.class, "n과 r은 반드시 양수이고, n은 r보다 크거나 같아야 합니다. n=%d, r=%d", n, r);
        }

        // 최적화: 조합의 대칭성 활용 (nCr == nC(n-r))
        long k = Math.min(r, n - r);
        long result = 1L;

        // 오버플로우를 최소화하는 점화식 기반 순차 계산
        for (long i = 1; i <= k; i++) {
            result = result * (n - i + 1) / i;
        }

        return result;
    }

    /**
     * 정렬된 데이터 중에서 중앙값(median)을 구하는 데 사용되는 데이터를 제공합니다. <br>
     * <ul>
     * <li>데이터의 개수(size)가 짝수인 경우: (size / 2 - 1), size / 2 번째</li>
     * <li>데이터의 개수(size)가 홀수인 경우: size / 2 번째</li>
     * </ul>
     * 
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 1. 9.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <N>
     *            데이터 유형 (Nullable)
     * @param data
     *            정렬된 데이터.
     * 
     * @return 중앙값 계산에 필요한 요소들을 담은 새로운 {@link List}
     * 
     * @throws IllegalArgumentException
     *             파라미터 데이터의 개수가 0인 경우 발생.
     * @throws NullPointerException
     *             파라미터({@code data})가 {@code null}인 경우 발생.
     *
     * @since 2019. 1. 9.
     * @version 2.1.0
     */
    public static <N extends @Nullable Object> List<N> getMedianEntries(List<N> data) {
        Objects.requireNonNull(data);

        int size = data.size();

        if (size < 1) {
            throw new IllegalArgumentException("no data. size=0");
        }

        List<N> l = new ArrayList<>();

        if (size == 1) {
            l.add(data.get(0));
        } else {
            // odd
            if ((size & 1) == 1) {
                l.add(data.get(size / 2));
            } else { // even
                int index = size / 2 - 1;
                l.add(data.get(index));
                l.add(data.get(index + 1));
            }
        }

        return l;
    }

    /**
     * 최대값을 제공합니다. (Nulls First 기준)
     * 
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2017. 12. 4.     parkjunhong77@gmail.com         최초 작성
     * 2026. 4. 1.      parkjunhong77@gmail.com         (3.0.0) 익명 클래스 메서드 참조로 교체 및 예외 방어 로직 추가
     * </pre>
     *
     * @param <T>
     *            비교 가능한 데이터 유형 (Nullable)
     * @param values
     *            값을 추출할 컬렉션 데이터
     * 
     * @return 최대값
     * 
     * @throws NullPointerException
     *             파라미터({@code values})가 {@code null}인 경우 발생.
     * @throws IllegalArgumentException
     *             컬렉션({@code values})이 비어있는 경우 발생.
     * 
     * @since 2017. 12. 4.
     * @version 3.0.0
     */
    public static <T extends @Nullable Comparable<T>> T max(Collection<T> values) {
        Objects.requireNonNull(values, "A parameter 'values' must not be 'null'.");

        return values.stream() //
                .max(ComparableUtils::compare) //
                .orElseThrow(() -> new IllegalArgumentException("Cannot find the maximum value from an empty collection."));
    }

    /**
     * 최대값을 제공합니다. (Nulls First 기준)
     * 
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 9. 2.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <T>
     *            비교 가능한 데이터 유형 (Nullable)
     * @param values
     *            값을 추출할 가변 인자 데이터
     * 
     * @return 최대값
     * 
     * @throws NullPointerException
     *             파라미터({@code values})가 {@code null}인 경우 발생.
     * @throws IllegalArgumentException
     *             파라미터({@code values})의 길이가 0인 경우 발생.
     *
     * @since 2020. 9. 2.
     * @version 3.0.0
     */
    // 아래 내용에 적용됨.
    // - Arrays.asList(values)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    @SafeVarargs
    public static <T extends @Nullable Comparable<T>> T max(T... values) {
        Objects.requireNonNull(values, "A parameter 'values' must not be 'null'.");

        return max(Arrays.asList(values));
    }

    /**
     * 최소값을 제공합니다. (Nulls First 기준)
     * 
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2017. 12. 4.     parkjunhong77@gmail.com         최초 작성
     * 2026. 4. 1.      parkjunhong77@gmail.com         (3.0.0) 익명 클래스 메서드 참조로 교체 및 예외 방어 로직 추가
     * </pre>
     *
     * @param <T>
     *            비교 가능한 데이터 유형 (Nullable)
     * @param values
     *            값을 추출할 컬렉션 데이터
     * 
     * @return 최소값
     * 
     * @throws NullPointerException
     *             파라미터({@code values})가 {@code null}인 경우 발생.
     * @throws IllegalArgumentException
     *             컬렉션({@code values})이 비어있는 경우 발생.
     * 
     * @since 2017. 12. 4.
     * @version 3.0.0
     */
    public static <T extends @Nullable Comparable<T>> T min(Collection<T> values) {
        Objects.requireNonNull(values, "A parameter 'values' must not be 'null'.");

        return values.stream() //
                .min(ComparableUtils::compare) //
                .orElseThrow(() -> new IllegalArgumentException("Cannot find the minimum value from an empty collection."));
    }

    /**
     * 최소값을 제공합니다. (Nulls First 기준)
     * 
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 9. 2.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <T>
     *            비교 가능한 데이터 유형 (Nullable)
     * @param values
     *            값을 추출할 가변 인자 데이터
     * 
     * @return 최소값
     * 
     * @throws NullPointerException
     *             파라미터({@code values})가 {@code null}인 경우 발생.
     * @throws IllegalArgumentException
     *             파라미터({@code values})의 길이가 0인 경우 발생.
     *
     * @since 2020. 9. 2.
     * @version 3.0.0
     */
    // 아래 내용에 적용됨.
    // - Arrays.asList(values)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    @SafeVarargs
    public static <T extends @Nullable Comparable<T>> T min(T... values) {
        Objects.requireNonNull(values, "A parameter 'values' must not be 'null'.");

        return min(Arrays.asList(values));
    }

    /**
     * 순열(Permutation, nPr)의 경우의 수를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2023. 5. 22.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param n
     *            전체 데이터 개수 (n > 0)
     * @param r
     *            선택할 데이터 개수 (r > 0, n >= r)
     * 
     * @return 계산된 순열의 경우의 수
     * 
     * @throws IllegalArgumentException
     *             {@code n}과 {@code r}의 수학적 조건이 맞지 않는 경우 발생.
     *
     * @since 2023. 5. 22.
     * @version 2.1.0
     */
    public static long permutation(long n, long r) {
        if (n < 1 || r < 1 || n < r) {
            throw ExceptionUtils.newException(IllegalArgumentException.class, "n과 r은 반드시 양수이고, n은 r보다 크거나 같아야 합니다. n=%d, r=%d", n, r);
        }

        long v = 1L;
        for (long i = 0; i < r; i++) { // 루프 변수 타입을 long으로 통일
            v *= (n - i);
        }
        return v;
    }
}
