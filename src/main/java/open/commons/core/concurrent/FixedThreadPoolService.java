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
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 
 * <BR>
 * 
 * @since 2012. 01. 20.
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
public class FixedThreadPoolService implements ExecutorService {

    private final ExecutorService executor;

    /**
     * @param executor
     * 
     * @exception IllegalArgumentException
     *                {@link ExecutorService} 객체가 <code>null</code>인 경우 <BR>
     * @since 2012. 01. 20.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public FixedThreadPoolService(ExecutorService executor) {
        if (executor == null) {
            throw new IllegalArgumentException("executor must not be 'null': executor=" + executor);
        }

        this.executor = executor;
    }

    /**
     * 
     * @param nThreads
     *            <BR>
     * @since 2012. 01. 20.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see Executors#newFixedThreadPool(int)
     */
    public FixedThreadPoolService(int nThreads) {
        executor = Executors.newFixedThreadPool(nThreads);
    }

    /**
     * 
     * @param nThreads
     * @param threadFactory
     *            <BR>
     * @since 2012. 01. 20.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see Executors#newFixedThreadPool(int, ThreadFactory)
     */
    public FixedThreadPoolService(int nThreads, ThreadFactory threadFactory) {
        executor = Executors.newFixedThreadPool(nThreads, threadFactory);
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
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see java.util.concurrent.ExecutorService#awaitTermination(long, java.util.concurrent.TimeUnit)
     */
    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return executor.awaitTermination(timeout, unit);
    }

    /**
     * Forwarding to a {@link ExecutorService} instance.
     * 
     * @param command
     * 
     * @since 2012. 01. 20.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see java.util.concurrent.Executor#execute(java.lang.Runnable)
     */
    @Override
    public void execute(Runnable command) {
        executor.execute(command);
    }

    /**
     * 실제 서비스 객체를 반환합니다.
     * 
     * @return <BR>
     * @since 2012. 01. 20.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
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
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see java.util.concurrent.ExecutorService#invokeAll(java.util.Collection)
     */
    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
        return executor.invokeAll(tasks);
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
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see java.util.concurrent.ExecutorService#invokeAll(java.util.Collection, long, java.util.concurrent.TimeUnit)
     */
    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException {
        return executor.invokeAll(tasks, timeout, unit);
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
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see java.util.concurrent.ExecutorService#invokeAny(java.util.Collection)
     */
    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
        return executor.invokeAny(tasks);
    }

    /**
     * Forwarding to a {@link ExecutorService} instance.
     * 
     * @param tasks
     * @param timeout
     * @param unit
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws TimeoutException
     * 
     * @since 2012. 01. 20.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see java.util.concurrent.ExecutorService#invokeAny(java.util.Collection, long, java.util.concurrent.TimeUnit)
     */
    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return executor.invokeAny(tasks, timeout, unit);
    }

    /**
     * Forwarding to a {@link ExecutorService} instance.
     * 
     * @return
     * 
     * @since 2012. 01. 20.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
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
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
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
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
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
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see java.util.concurrent.ExecutorService#shutdownNow()
     */
    @Override
    public List<Runnable> shutdownNow() {
        return executor.shutdownNow();
    }

    /**
     * Forwarding to a {@link ExecutorService} instance.
     * 
     * @param task
     * @return
     * 
     * @since 2012. 01. 20.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see java.util.concurrent.ExecutorService#submit(java.util.concurrent.Callable)
     */
    @Override
    public <T> Future<T> submit(Callable<T> task) {
        return executor.submit(task);
    }

    /**
     * Forwarding to a {@link ExecutorService} instance.
     * 
     * @param task
     * @return
     * 
     * @since 2012. 01. 20.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see java.util.concurrent.ExecutorService#submit(java.lang.Runnable)
     */
    @Override
    public Future<?> submit(Runnable task) {
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
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see java.util.concurrent.ExecutorService#submit(java.lang.Runnable, java.lang.Object)
     */
    @Override
    public <T> Future<T> submit(Runnable task, T result) {
        return executor.submit(task, result);
    }

    /**
     * 현재 실행되고 있는 명령이 모두 종료한 후에 서비스를 종료시킨다.
     * 
     * <BR>
     * 
     * @since 2012. 01. 20.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
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
     * <BR>
     * 
     * @since 2012. 01. 20.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see ExecutorService#shutdownNow()
     */
    public void terminateNow() {
        for (Runnable runnable : executor.shutdownNow()) {
            new Thread(runnable).interrupt();
        }
    }

    public static void shutdownAndAwaitTermination(ExecutorService pool) {
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
