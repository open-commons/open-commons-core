/*
 * Copyright 2020 Park Jun-Hong_(parkjunhong77/google/com)
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
 * Date  : 2020. 4. 10. 오후 1:41:53
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package open.commons.utils;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import open.commons.Result;

/**
 * 
 * @since 2020. 4. 10.
 * @version _._._
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
public class StreamUtils {

    // prevent to create a new instance.
    private StreamUtils() {
    }

    /**
     * 정의된 기능을 실행하고 에러 결과를 반환하는 객체를 제공한다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 4. 11.     박준홍         최초 작성
     * </pre>
     *
     * @param <R>
     *            실행 함수 반환 데이터
     * @param <X>
     *            최종 반환 데이터
     * @param action
     *            실행 함수
     * @param onSuccess
     *            실행 함수가 올바르게 수행된 경우 실행
     * @param onError
     *            실행 함수 실행시 에러가 발생한 경우 실행
     * @param onError
     * @return
     *
     * @since 2020. 4. 11.
     * @version
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public static <R, X> Supplier<X> build(Supplier<R> action, Function<R, X> onSuccess, Function<Throwable, X> onError) {
        return () -> {
            try {
                R r = action.get();
                return onSuccess.apply(r);
            } catch (Throwable t) {
                return onError.apply(t);
            }
        };
    }

    /**
     * 정의된 기능을 실행하고 에러 결과를 반환하는 객체를 제공한다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 4. 11.     박준홍         최초 작성
     * </pre>
     *
     * @param <R>
     *            실행 함수 반환 데이터
     * @param <X>
     *            최종 반환 데이터
     * @param action
     *            실행 함수
     * @param onSuccess
     *            실행 함수가 올바르게 수행된 경우 실행
     * @param onError
     *            실행 함수 실행시 에러가 발생한 경우 실행
     * @param onError
     * @return
     *
     * @since 2020. 4. 11.
     * @version
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public static <R> Supplier<String> build(Supplier<Result<R>> action, Consumer<R> onSuccess) {
        return () -> {
            try {
                Result<R> r = action.get();
                if (r.getResult()) {
                    onSuccess.accept(r.getData());
                    return null;
                } else {
                    return r.getMessage();
                }
            } catch (Throwable t) {
                return t.getMessage();
            }
        };
    }

    /**
     * 정의된 기능을 실행하고 에러 결과를 반환하는 객체를 제공한다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 4. 11.     박준홍         최초 작성
     * </pre>
     *
     * @param <R>
     *            실행 함수 반환 데이터
     * @param <X>
     *            최종 반환 데이터
     * @param action
     *            실행 함수
     * @param onSuccess
     *            실행 함수가 올바르게 수행된 경우 실행
     * @param onError
     *            실행 함수 실행시 에러가 발생한 경우 실행
     * @param onError
     * @return
     *
     * @since 2020. 4. 11.
     * @version
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public static <R> Supplier<String> build(Supplier<Result<R>> action, Consumer<R> onSuccess, Function<Throwable, String> onError) {
        return () -> {
            try {
                Result<R> r = action.get();
                if (r.getResult()) {
                    onSuccess.accept(r.getData());
                    return null;
                } else {
                    return r.getMessage();
                }
            } catch (Throwable t) {
                return onError.apply(t);
            }
        };
    }

    /**
     * 파라미터 1개를 받아 정의된 기능을 실행하고 에러 결과를 반환하는 객체를 제공한다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 4. 10.     박준홍         최초 작성
     * </pre>
     *
     * @param <T>
     *            parameter.
     * @param <R>
     *            실행 함수 반환 데이터
     * @param <X>
     *            최종 반환 데이터
     * @param param
     * @param action
     *            실행 함수
     * @param onSuccess
     *            실행 함수가 올바르게 수행된 경우 실행
     * @param onError
     *            실행 함수 실행시 에러가 발생한 경우 실행
     * @return
     *
     * @since 2020. 4. 10.
     * @version
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public static <T, R, X> Supplier<X> build(T param, Function<T, R> action, Function<R, X> onSuccess, Function<Throwable, X> onError) {
        return () -> {
            try {
                R r = action.apply(param);
                return onSuccess.apply(r);
            } catch (Throwable t) {
                return onError.apply(t);
            }
        };
    }

    /**
     * 파라미터 1개를 받아 정의된 기능을 실행하고 에러 결과를 반환하는 객체를 제공한다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 4. 10.     박준홍         최초 작성
     * </pre>
     *
     * @param <R>
     * @param <T>
     * @param <U>
     * @param param파라미터
     * @param action
     *            기능 정의 함수
     * @param onSuccess
     *            함수 실행이 성공한 경우
     * @param onError
     *            함수 실행이 실패한 경우
     * @return
     *
     * @since 2020. 4. 10.
     * @version
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public static <T, R> Supplier<String> build(T param, Function<T, Result<R>> action, Consumer<R> onSuccess) {
        return () -> {
            try {
                Result<R> r = action.apply(param);
                if (r.getResult()) {
                    onSuccess.accept(r.getData());
                    return null;
                } else {
                    return r.getMessage();
                }
            } catch (Throwable t) {
                return t.getMessage();
            }
        };
    }

    /**
     * 파라미터 1개를 받아 정의된 기능을 실행하고 에러 결과를 반환하는 객체를 제공한다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 4. 10.     박준홍         최초 작성
     * </pre>
     *
     * @param <R>
     * @param <T>
     * @param <U>
     * @param param
     *            파라미터
     * @param action
     *            기능 정의 함수
     * @param onSuccess
     *            함수 실행이 성공한 경우
     * @param onError
     *            함수 실행이 실패한 경우
     * @return
     *
     * @since 2020. 4. 10.
     * @version
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public static <T, R> Supplier<String> build(T param, Function<T, Result<R>> action, Consumer<R> onSuccess, Function<Throwable, String> onError) {
        return () -> {
            try {
                Result<R> r = action.apply(param);
                if (r.getResult()) {
                    onSuccess.accept(r.getData());
                    return null;
                } else {
                    return r.getMessage();
                }
            } catch (Throwable t) {
                return onError.apply(t);
            }
        };
    }

    /**
     * 파라미터 2개를 받아 정의된 기능을 실행하고 에러 결과를 반환하는 객체를 제공한다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 4. 7.      박준홍         최초 작성
     * </pre>
     *
     * @param <T>
     *            1st parameter.
     * @param <U>
     *            2nd parameter.
     * @param <R>
     *            실행 함수 반환 데이터
     * @param <X>
     *            최종 반환 데이터
     * @param param1
     * @param param2
     * @param action
     *            실행 함수
     * @param onSuccess
     *            실행 함수가 올바르게 수행된 경우 실행
     * @param onError
     *            실행 함수 실행시 에러가 발생한 경우 실행
     * @return
     *
     * @since 2020. 4. 7.
     * @version
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public static <T, U, R, X> Supplier<X> build(T param1, U param2, BiFunction<T, U, R> action, Function<R, X> onSuccess, Function<Throwable, X> onError) {
        return () -> {
            try {
                R r = action.apply(param1, param2);
                return onSuccess.apply(r);
            } catch (Throwable t) {
                return onError.apply(t);
            }
        };
    }

    /**
     * 파라미터 2개를 받아 정의된 기능을 실행하고 에러 결과를 반환하는 객체를 제공한다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 4. 7.      박준홍         최초 작성
     * </pre>
     *
     * @param <R>
     * @param <T>
     * @param <U>
     * @param param1
     * @param param2
     * @param action
     *            기능 실행 함수
     * @param onSuccess
     *            실행이 성공한 경우
     * @return
     *
     * @since 2020. 4. 7.
     * @version
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public static <R, T, U> Supplier<String> build(T param1, U param2, BiFunction<T, U, Result<R>> action, Consumer<R> onSuccess) {
        return () -> {
            try {
                Result<R> r = action.apply(param1, param2);
                if (r.getResult()) {
                    onSuccess.accept(r.getData());
                    return null;
                } else {
                    return r.getMessage();
                }
            } catch (Throwable t) {
                return t.getMessage();
            }
        };
    }

    /**
     * 파라미터 2개를 받아 정의된 기능을 실행하고 에러 결과를 반환하는 객체를 제공한다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 4. 10.     박준홍         최초 작성
     * </pre>
     *
     * @param <R>
     * @param <T>
     * @param <U>
     * @param param1
     *            1st 파라미터
     * @param param2
     *            2nd 파라미터
     * @param action
     *            기능 정의 함수
     * @param onSuccess
     *            함수 실행이 성공한 경우
     * @param onError
     *            함수 실행이 실패한 경우
     * @return
     *
     * @since 2020. 4. 10.
     * @version
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public static <R, T, U> Supplier<String> build(T param1, U param2, BiFunction<T, U, Result<R>> action, Consumer<R> onSuccess, Function<Throwable, String> onError) {
        return () -> {
            try {
                Result<R> r = action.apply(param1, param2);
                if (r.getResult()) {
                    onSuccess.accept(r.getData());
                    return null;
                } else {
                    return r.getMessage();
                }
            } catch (Throwable t) {
                return onError.apply(t);
            }
        };
    }

    /**
     * 기능 실행 후 반환 데이터의 존재 유무를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 4. 7.      박준홍         최초 작성
     * </pre>
     * 
     * @param filterIn
     *            (positive) 데이터 검증. <code>true</code>인 경우 포함된다.
     * @param actions
     *            기능 정의
     *
     * @param <R>
     *            반환 데이터
     * @return
     *
     * @since 2020. 4. 7.
     * @version
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    @SuppressWarnings("unchecked")
    public static <R> Optional<R> runOnAsync(Predicate<R> filterIn, Supplier<R>... actions) {
        if (actions == null) {
            throw new NullPointerException();
        }

        return Arrays.asList(actions).parallelStream() //
                // 실행
                .map(s -> s.get()) //
                // 반환 데이터 확인
                .filter(filterIn) //
                .findAny();
    }

    /**
     * 기능 실행 후 반환 데이터의 존재 유무를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 4. 7.      박준홍         최초 작성
     * </pre>
     * 
     * @param filterIn
     *            (positive) 데이터 검증. <code>true</code>인 경우 포함된다.
     * @param actions
     *            기능 정의
     *
     * @param <R>
     *            반환 데이터
     * @return
     *
     * @since 2020. 4. 7.
     * @version
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    @SuppressWarnings("unchecked")
    public static <R> Optional<R> runOnSync(Predicate<R> filterIn, Supplier<R>... actions) {
        if (actions == null) {
            throw new NullPointerException();
        }

        return Arrays.asList(actions).stream() //
                // 실행
                .map(s -> s.get()) //
                // 데이터 확인
                .filter(filterIn) //
                .findAny();
    }
}
