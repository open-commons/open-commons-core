/*
 * Copyright 2025 Park Jun-Hong (parkjunhong77@gmail.com)
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
 * Date  : 2025. 9. 30. 오후 4:35:08
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package open.commons.core.utils;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Consumer;
import java.util.function.Function;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

/**
 * Concurrent/Parallel/Async Programming을 위한 유틸 클래스 <BR>
 * 
 * @since 2025. 9. 30.
 * @version 2.1.0
 * 
 */
public class ConcurrentUtils {

    private ConcurrentUtils() {
    }

    /**
     * 여러 개의 입력데이터에 대한 작업을 병렬로 수행한 후 결과를 합쳐서 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 30.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <T>
     *            입력 데이터 유형
     * @param <U>
     *            결과 데이터 유형
     * @param data
     *            입력 데이터
     * @param actor
     *            수행할 작업.
     * @return
     * 
     * @throws NullPointerException
     *             파라미터중에 1개라도 {@code null}인 경우 발생.
     *
     * @since 2025. 9. 30.
     * @version 2.1.0
     * 
     */
    // 아래 내용에 적용됨.
    // - ForkJoinPool.commonPool()
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static <T, U extends @Nullable Object> List<U> executeAsync(Collection<T> data, Function<T, U> actor) {
        return executeAsync(data, actor, ForkJoinPool.commonPool());
    }

    /**
     * 여러 개의 입력데이터에 대한 작업을 병렬로 수행한 후 결과를 합쳐서 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 30.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     * 
     * @param <T>
     *            입력 데이터 유형
     * @param <U>
     *            결과 데이터 유형
     * @param data
     *            입력 데이터
     * @param actor
     *            수행할 작업
     * @param executor
     *            작업 실행 환경
     * @return
     *
     * @throws NullPointerException
     *             파라미터중에 1개라도 {@code null}인 경우 발생.
     * 
     * @since 2025. 9. 30.
     * @version 2.1.0
     */
    public static <T, U extends @Nullable Object> List<U> executeAsync(Collection<T> data, Function<T, U> actor, Executor executor) {
        ObjectUtils.requireNonNulls(data, actor, executor);

        // #1. 작업을 병렬로 실행 (supplyAsync)
        @SuppressWarnings("null")
        @NonNull
        List<CompletableFuture<U>> jobs = data.stream() //
                .map(d -> CompletableFuture.supplyAsync(() -> actor.apply(d), executor)) //
                .toList();

        // #2. 작업 완료 후 처리
        return waitAndApply(jobs);
    }

    /**
     * 여러 개의 처리 결과를 하나의 데이터에 적용하는 작업을 병렬로 수행합니다. <br>
     * <p>
     * <b>[동시성(Concurrency) 주의사항]</b><br>
     * 다수의 스레드가 동시에 {@code bucket} 객체의 상태를 변경하므로, 전달되는 {@code bucket}은 반드시 <b>스레드 안전(Thread-Safe)한 객체</b>여야 합니다. (예:
     * {@link java.util.concurrent.ConcurrentHashMap}, 락이 구현된 객체 등)
     * </p>
     *
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 10. 1.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <T>
     *            입력 데이터 유형
     * @param bucket
     *            처리 결과를 적용할 타겟 객체 (Thread-Safe 권장)
     * @param actors
     *            수행할 작업들
     * 
     * @throws NullPointerException
     *             파라미터({@code bucket 또는 actors})가 {@code null}인 경우 발생.
     *
     * @since 2025. 10. 1.
     * @version 2.1.0
     */
    // 아래 내용에 적용됨.
    // - ForkJoinPool.commonPool()
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static <T> void executeAsync(T bucket, Collection<Consumer<T>> actors) {
        executeAsync(bucket, actors, ForkJoinPool.commonPool());
    }

    /**
     * 여러 개의 처리 결과를 하나의 데이터에 적용하는 작업을 병렬로 수행합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 10. 1.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <T>
     *            입력 데이터 유형
     * @param bucket
     *            처리 결과를 적용할 타겟 객체 (Thread-Safe 권장)
     * @param actors
     *            수행할 작업들
     * @param executor
     *            작업 실행 환경
     *
     * @throws NullPointerException
     *             파라미터중에 1개라도 {@code null}인 경우 발생.
     * 
     * @since 2025. 10. 1.
     * @version 2.1.0
     */
    public static <T> void executeAsync(T bucket, Collection<Consumer<T>> actors, Executor executor) {
        ObjectUtils.requireNonNulls(bucket, actors, executor);

        // #1. 작업을 병렬로 실행
        CompletableFuture<?>[] jobs = actors.stream() //
                .map(actor -> CompletableFuture.runAsync(() -> actor.accept(bucket), executor)) //
                .toArray(CompletableFuture[]::new);

        // #2. 모든 작업 완료 대기
        CompletableFuture.allOf(jobs).join();
    }

    /**
     * 전달받은 모든 작업이 완료된 후 결과를 합쳐서 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 10. 1.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <U>
     *            결과 데이터 유형
     * @param jobs
     *            대기할 비동기 작업 목록
     * @return 모든 작업의 결과 목록
     * 
     * @throws NullPointerException
     *             파라미터({@code jobs})가 {@code null}인 경우 발생.
     *
     * @since 2025. 10. 1.
     * @version 2.1.0
     * 
     */
    @SuppressWarnings("null")
    private static <U> List<U> waitAndApply(List<CompletableFuture<U>> jobs) {
        Objects.requireNonNull(jobs);

        // #2. 모든 작업 완료 대기
        CompletableFuture<Void> allDone = CompletableFuture.allOf(jobs.toArray(CompletableFuture[]::new));

        // #3. 결과 수집 (Stream.toList()를 활용하여 불변 리스트 반환)
        return allDone.thenApply(_ -> jobs.stream().map(CompletableFuture::join).toList()).join();
    }
}
