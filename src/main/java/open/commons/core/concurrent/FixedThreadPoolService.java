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
*/
package open.commons.core.concurrent;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.jspecify.annotations.Nullable;

import open.commons.core.utils.ObjectUtils;

/**
 * 
 * @since 2012. 01. 20.
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */
public class FixedThreadPoolService implements ExecutorService {

    private final ExecutorService executor;

    /**
     * @param executor
     * 
     * @throws IllegalArgumentException
     *             {@link ExecutorService} 객체가 {@code null}인 경우
     * @since 2012. 01. 20.
     * 
     */
    public FixedThreadPoolService(ExecutorService executor) {
        Objects.requireNonNull(executor, "executor must not be 'null'");
        this.executor = executor;
    }

    /**
     * 
     * @param nThreads
     * 
     * @since 2012. 01. 20.
     * 
     * 
     * @see Executors#newFixedThreadPool(int)
     */
    public FixedThreadPoolService(int nThreads) {
        executor = Objects.requireNonNull(Executors.newFixedThreadPool(nThreads));
    }

    /**
     * 
     * @param nThreads
     * @param threadFactory
     * 
     * @since 2012. 01. 20.
     * 
     * 
     * @see Executors#newFixedThreadPool(int, ThreadFactory)
     */
    public FixedThreadPoolService(int nThreads, ThreadFactory threadFactory) {
        Objects.requireNonNull(threadFactory, "threadFactory");
        executor = Objects.requireNonNull(Executors.newFixedThreadPool(nThreads, threadFactory));
    }

    /**
     * Forwarding to a {@link ExecutorService} instance.
     * 
     * @param timeout
     * @param unit
     * @return
     * @throws InterruptedException
     * 
     * @since 2012. 01. 20.
     * 
     * 
     * @see java.util.concurrent.ExecutorService#awaitTermination(long, java.util.concurrent.TimeUnit)
     */
    @SuppressWarnings("null")
    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        Objects.requireNonNull(unit);
        return executor.awaitTermination(timeout, unit);
    }

    /**
     * Forwarding to a {@link ExecutorService} instance.
     * 
     * @param command
     * 
     * @since 2012. 01. 20.
     * 
     * 
     * @see java.util.concurrent.Executor#execute(java.lang.Runnable)
     */
    @SuppressWarnings("null")
    @Override
    public void execute(Runnable command) {
        Objects.requireNonNull(command);

        executor.execute(command);
    }

    /**
     * 실제 서비스 객체를 반환합니다.
     * 
     * @return
     * @since 2012. 01. 20.
     * 
     */
    public ExecutorService getService() {
        return executor;
    }

    /**
     * Forwarding to a {@link ExecutorService} instance.
     * 
     * @param tasks
     * @return
     * @throws InterruptedException
     * 
     * @since 2012. 01. 20.
     * 
     * 
     * @see java.util.concurrent.ExecutorService#invokeAll(java.util.Collection)
     */
    @SuppressWarnings("null")
    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
        Objects.requireNonNull(tasks);

        return Objects.requireNonNull(executor.invokeAll(tasks));
    }

    /**
     * Forwarding to a {@link ExecutorService} instance.
     * 
     * @param tasks
     * @param timeout
     * @param unit
     * @return
     * @throws InterruptedException
     * 
     * @since 2012. 01. 20.
     * 
     * 
     * @see java.util.concurrent.ExecutorService#invokeAll(java.util.Collection, long, java.util.concurrent.TimeUnit)
     */
    @SuppressWarnings("null")
    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException {
        ObjectUtils.requireNonNulls(tasks, unit);

        return Objects.requireNonNull(executor.invokeAll(tasks, timeout, unit));
    }

    /**
     * Forwarding to a {@link ExecutorService} instance.
     * 
     * @param tasks
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     * 
     * @since 2012. 01. 20.
     * 
     * 
     * @see java.util.concurrent.ExecutorService#invokeAny(java.util.Collection)
     */
    @SuppressWarnings("null")
    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
        Objects.requireNonNull(tasks);

        return executor.invokeAny(tasks);
    }

    /**
     * Forwarding to a {@link ExecutorService} instance.
     * 
     * @param tasks
     * @param timeout
     * @param unit
     * @return
     * 
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws TimeoutException
     * 
     * @since 2012. 01. 20.
     * 
     * 
     * @see java.util.concurrent.ExecutorService#invokeAny(java.util.Collection, long, java.util.concurrent.TimeUnit)
     */
    @SuppressWarnings("null")
    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        ObjectUtils.requireNonNulls(tasks, unit);

        return executor.invokeAny(tasks, timeout, unit);
    }

    /**
     * Forwarding to a {@link ExecutorService} instance.
     * 
     * @return
     * 
     * @since 2012. 01. 20.
     * 
     * 
     * @see java.util.concurrent.ExecutorService#isShutdown()
     */
    @Override
    public boolean isShutdown() {
        return executor.isShutdown();
    }

    /**
     * Forwarding to a {@link ExecutorService} instance.
     * 
     * @return
     * 
     * @since 2012. 01. 20.
     * 
     * 
     * @see java.util.concurrent.ExecutorService#isTerminated()
     */
    @Override
    public boolean isTerminated() {
        return executor.isTerminated();
    }

    /**
     * Forwarding to a {@link ExecutorService} instance.
     * 
     * @since 2012. 01. 20.
     * 
     * 
     * @see java.util.concurrent.ExecutorService#shutdown()
     */
    @Override
    public void shutdown() {
        executor.shutdown();

    }

    /**
     * Forwarding to a {@link ExecutorService} instance.
     * 
     * @return
     * 
     * @since 2012. 01. 20.
     * 
     * 
     * @see java.util.concurrent.ExecutorService#shutdownNow()
     */
    @Override
    public List<Runnable> shutdownNow() {
        return Objects.requireNonNull(executor.shutdownNow());
    }

    /**
     * Forwarding to a {@link ExecutorService} instance.
     * 
     * @param task
     * @return
     * 
     * @since 2012. 01. 20.
     * 
     * 
     * @see java.util.concurrent.ExecutorService#submit(java.util.concurrent.Callable)
     */
    @SuppressWarnings("null")
    @Override
    public <T> Future<T> submit(Callable<T> task) {
        Objects.requireNonNull(task);

        return executor.submit(task);
    }

    /**
     * Forwarding to a {@link ExecutorService} instance.
     * 
     * @param task
     * @return
     * 
     * @since 2012. 01. 20.
     * 
     * 
     * @see java.util.concurrent.ExecutorService#submit(java.lang.Runnable)
     */
    @SuppressWarnings("null")
    @Override
    public Future<?> submit(Runnable task) {
        Objects.requireNonNull(task);

        return executor.submit(task);
    }

    /**
     * Forwarding to a {@link ExecutorService} instance.
     * 
     * @param task
     * @param result
     * @return
     * 
     * @since 2012. 01. 20.
     * 
     * 
     * @see java.util.concurrent.ExecutorService#submit(java.lang.Runnable, java.lang.Object)
     */
    @SuppressWarnings("null")
    @Override
    public <T> Future<T> submit(Runnable task, @Nullable T result) {
        Objects.requireNonNull(task);

        return executor.submit(task, result);
    }

    /**
     * 현재 실행되고 있는 명령이 모두 종료한 후에 서비스를 종료시킨다.
     * 
     * 
     * 
     * @since 2012. 01. 20.
     * 
     * 
     * @see ExecutorService#isTerminated()
     * @see ExecutorService#shutdown()
     */
    public void terminateAfterComplete() {
        executor.shutdown();

        while (!executor.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ignored) {
            }
        }
    }

    /**
     * 현재 서비스를 종료 시킨다.
     * 
     * @since 2012. 01. 20.
     * 
     * 
     * @see ExecutorService#shutdownNow()
     */
    public void terminateNow() {
        for (Runnable runnable : executor.shutdownNow()) {
            new Thread(runnable).interrupt();
        }
    }

    /**
     * 실행 중인 작업을 종료합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2012. 1. 20.         parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param pool
     *
     * @since 2012. 1. 20.
     * 
     */
    public static void shutdownAndAwaitTermination(ExecutorService pool) {
        Objects.requireNonNull(pool);

        pool.shutdown(); // Disable new tasks from being submitted
        try {
            // Wait a while for existing tasks to terminate
            if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
                pool.shutdownNow(); // Cancel currently executing tasks
                // Wait a while for tasks to respond to being cancelled
                if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
                    System.err.println("Pool did not terminate");
                }
            }
        } catch (InterruptedException ie) {
            // (Re-)Cancel if current thread also interrupted
            pool.shutdownNow();
            // Preserve interrupt status
            Thread.currentThread().interrupt();
        }
    }
}
