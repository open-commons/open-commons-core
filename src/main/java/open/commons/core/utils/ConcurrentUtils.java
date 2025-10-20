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
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;

/**
 * Concurrent/Parallel/Async Programming을 위한 유틸 클래스 <BR>
 * 
 * @since 2025. 9. 30.
 * @version 2.1.0
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
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
     * @since 2025. 9. 30.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <T, U> List<U> executeAsync(@Nonnull Collection<T> data, @Nonnull Function<T, U> actor) {
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
     * @since 2025. 9. 30.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <T, U> List<U> executeAsync(@Nonnull Collection<T> data, @Nonnull Function<T, U> actor, @Nonnull Executor executor) {
        // #1. 작업을 병렬로 실행
        List<CompletableFuture<U>> jobs = data.stream() //
                .map(d -> CompletableFuture.supplyAsync(() -> actor.apply(d), executor))//
                .collect(Collectors.toList());
        // #2. 작업 완료 후 처리
        return waitAndApply(jobs);
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
     * @param bucket
     *            처리 결과를 적용할 객체
     * @param actors
     *            수행할 작업들
     * @param executor
     *            작업 실행 환경
     *
     * @since 2025. 10. 1.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <T> void executeAsync(@Nonnull T bucket, @Nonnull Collection<Consumer<T>> actors) {
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
     * @param bucket
     *            처리 결과를 적용할 객체
     * @param actors
     *            수행할 작업들
     * @param executor
     *            작업 실행 환경
     *
     * @since 2025. 10. 1.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <T> void executeAsync(@Nonnull T bucket, @Nonnull Collection<Consumer<T>> actors, @Nonnull Executor executor) {
        // #1. 작업을 병렬로 실행
        List<CompletableFuture<Object>> jobs = actors.stream() //
                .map(actor -> CompletableFuture.supplyAsync(() -> {
                    actor.accept(bucket);
                    return null;
                }, executor)) //
                .collect(Collectors.toList());
        // #2. 작업 완료 후 처리
        waitAndApply(jobs);
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
     * @param jobs
     * @return
     *
     * @since 2025. 10. 1.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    private static <U> List<U> waitAndApply(List<CompletableFuture<U>> jobs) {
        // #2. 모든 작업 완료 대기
        CompletableFuture<Void> allDone = CompletableFuture.allOf(jobs.toArray(new CompletableFuture[0]));
        // #3. 결과 수집
        return allDone.thenApply(v -> jobs.stream().map(CompletableFuture::join).collect(Collectors.toList())).join();
    }
}
