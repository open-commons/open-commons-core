/*
 * Copyright 2020 Park Jun-Hong_(parkjunhong77@gmail.com)
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
 * Date  : 2020. 8. 29. 오전 1:33:57
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.utils;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.Future;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import open.commons.Result;
import open.commons.function.Runner;

/**
 * java.util.function 패키지에 있는 클래스에 대한 유틸리티 메소드 제공.
 * 
 * @since 2020. 8. 29.
 * @version 1.7.0
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */
public class FunctionUtils {

    private FunctionUtils() {
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 6. 12.     박준홍         최초 작성
     * </pre>
     *
     * @param <S>
     * @param <T>
     * @param <U>
     * @param <V>
     * @param <W>
     * @param <X>
     * @param action
     *            실행 함수
     * @param param1
     *            1st 파라미터
     * @param param2
     *            2nd 파라미터
     * @param onSuccess
     *            실행 함수가 올바르게 수행된 경우 실행
     * @param osParam1
     *            성공함수 1st 파라미터.
     * @param osParam2
     *            성공함수 2nd 파라미터.
     * @param onError
     *            실행 함수 실행시 에러가 발생한 경우 실행
     * @return
     *
     * @since 2020. 6. 12.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <S, T, U, V, W, X> Supplier<X> build(BiFunction<S, T, U> action, S param1, T param2 //
            , BiFunction<V, W, X> onSuccess, V osParam1, Function<U, W> osParam2 //
            , Function<Throwable, X> onError) {
        return () -> {
            try {
                U r = action.apply(param1, param2);
                return onSuccess.apply(osParam1, osParam2.apply(r));
            } catch (Throwable t) {
                t.printStackTrace();
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
     * @param action
     *            실행 함수
     * @param param1
     * @param param2
     * @param onSuccess
     *            실행 함수가 올바르게 수행된 경우 실행
     * @param onError
     *            실행 함수 실행시 에러가 발생한 경우 실행
     * @return
     *
     * @since 2020. 6. 12.
     * @version
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <T, U, R, X> Supplier<X> build(BiFunction<T, U, R> action, T param1, U param2 //
            , Function<R, X> onSuccess //
            , Function<Throwable, X> onError) {
        return () -> {
            try {
                R r = action.apply(param1, param2);
                return onSuccess.apply(r);
            } catch (Throwable t) {
                t.printStackTrace();
                return onError.apply(t);
            }
        };
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 6. 12.     박준홍         최초 작성
     * </pre>
     * 
     * @param <S>
     * @param <T>
     * @param <U>
     * @param <X>
     * @param action
     *            실행 함수
     * @param param
     *            실행 함수 파라미터.
     * @param onSuccess
     *            실행 함수가 올바르게 수행된 경우 실행
     * @param osParam
     *            성공함수 파라미터.
     * @param onError
     *            실행 함수 실행시 에러가 발생한 경우 실행
     *
     * @return
     *
     * @since 2020. 6. 12.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <S, T, U, X> Supplier<X> build(Function<S, T> action, S param, Function<U, X> onSuccess, Function<T, U> osParam, Function<Throwable, X> onError) {
        return () -> {
            try {
                T r = action.apply(param);
                return onSuccess.apply(osParam.apply(r));
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
     * 2020. 6. 12.     박준홍         최초 작성
     * </pre>
     *
     * @param <T>
     *            parameter.
     * @param <R>
     *            실행 함수 반환 데이터
     * @param <X>
     *            최종 반환 데이터
     * @param action
     *            실행 함수
     * @param param
     * @param onSuccess
     *            실행 함수가 올바르게 수행된 경우 실행
     * @param onError
     *            실행 함수 실행시 에러가 발생한 경우 실행
     * @return
     *
     * @since 2020. 6. 12.
     * @version
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <T, R, X> Supplier<X> build(Function<T, R> action, T param, Function<R, X> onSuccess, Function<Throwable, X> onError) {
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
     * 2020. 6. 12.     박준홍         최초 작성
     * </pre>
     *
     * @param <R>
     * @param <T>
     * @param <U>
     * @param action
     *            기능 정의 함수
     * @param param
     *            파라미터
     * @param onSuccess
     *            함수 실행이 성공한 경우
     * @param onError
     *            함수 실행이 실패한 경우
     * @return
     *
     * @since 2020. 6. 11.
     * @version
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <T, R> Supplier<String> build(Function<T, Result<R>> action, T param, Consumer<R> onSuccess) {
        return build(action, param, onSuccess, t -> t.getMessage());
    }

    /**
     * 파라미터 1개를 받아 정의된 기능을 실행하고 에러 결과를 반환하는 객체를 제공한다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 6. 12.     박준홍         최초 작성
     * </pre>
     *
     * @param <R>
     * @param <T>
     * @param <U>
     * @param action
     *            기능 정의 함수
     * @param param
     *            파라미터
     * @param onSuccess
     *            함수 실행이 성공한 경우
     * @param onError
     *            함수 실행이 실패한 경우
     * @return
     *
     * @since 2020. 6. 12.
     * @version
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <T, R> Supplier<String> build(Function<T, Result<R>> action, T param, Consumer<R> onSuccess, Function<Throwable, String> onError) {
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <R> Supplier<String> build(Supplier<Result<R>> action, Consumer<R> onSuccess) {
        return build(action, onSuccess, t -> t.getMessage());
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <R, T, U> Supplier<String> build(T param1, U param2, BiFunction<T, U, Result<R>> action, Consumer<R> onSuccess) {
        return build(param1, param2, action, onSuccess, t -> t.getMessage());
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
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
     * {@link Future}를 실행한 결과를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 6. 14.     박준홍         최초 작성
     * </pre>
     *
     * @param <R>
     *            반환할 데이터 타입
     * @param future
     *            실행 객체
     * @return
     *
     * @since 2020. 6. 14.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <R> Result<R> getOnAsync(Future<Result<R>> future) {
        if (future == null) {
            return null;
        }

        try {
            return future.get();
        } catch (Exception e) {
            return new Result<R>().setMessage(e.getMessage());
        }
    }

    /**
     * 조건에 맞는 데이터를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 5. 18.		박준홍			최초 작성
     * </pre>
     *
     * @param <T>
     * @param condition
     *            비교
     * @param then
     *            조건이 'true'인 경우
     * @param elze
     *            조건이 'false'인 경우
     * @return
     *
     * @since 2021. 5. 18.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <T> T ifThenElse(Supplier<Boolean> condition, Supplier<T> then, Supplier<T> elze) {
        return condition.get() ? then.get() : elze.get();
    }

    /**
     * 조건에 맞는 데이터를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 5. 18.		박준홍			최초 작성
     * </pre>
     *
     * @param <T>
     * @param condition
     *            비교
     * @param then
     *            조건이 'true'인 경우
     * @param elze
     *            조건이 'false'인 경우
     * @return
     *
     * @since 2021. 5. 18.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <T> T ifThenElse(Supplier<Boolean> condition, T then, T elze) {
        return condition.get() ? then : elze;
    }

    public static Predicate<Object> isNotNull() {
        return o -> o != null;
    }

    public static Supplier<Boolean> isNotNull(Object value) {
        return () -> value != null;
    }

    public static Predicate<Object> isNull() {
        return o -> o == null;
    }

    public static Supplier<Boolean> isNull(Object value) {
        return () -> value == null;
    }

    public static void runIf(Object value, Runner then) {
        if (value != null) {
            then.run();
        }
    }

    public static void runIf(Object value, Runner then, Runner elze) {
        if (value != null) {
            then.run();
        } else {
            elze.run();
        }
    }

    public static <T> void runIf(T value, Predicate<T> p, Consumer<T> then) {
        if (p.test(value)) {
            then.accept(value);
        }
    }

    public static <T> void runIf(T value, Predicate<T> p, Consumer<T> then, Consumer<T> elze) {
        if (p.test(value)) {
            then.accept(value);
        } else {
            elze.accept(value);
        }
    }

    /**
     * 데이터를 검증한 후 함수의 실행 결과를 반환한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 6. 14.     박준홍         최초 작성
     * </pre>
     *
     * @param <T>
     * @param <R>
     * @param value
     *            데이터
     * @param test
     *            데이터 검증
     * @param run
     *            실행할 함수
     * @return
     *
     * @since 2020. 6. 14.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <T, R> R runIf(T value, Predicate<T> test, Function<T, R> run) {
        return test.test(value) ? run.apply(value) : null;
    }

    /**
     * 데이터를 검증한 후 함수의 실행 결과를 반환한다. 데이터 검증이 실패한 경우 기본값을 반환한다. <br>
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 7. 21.     박준홍         최초 작성
     * </pre>
     *
     * @param <T>
     * @param <R>
     * @param value
     *            데이터
     * @param test
     *            데이터 검증
     * @param run
     *            실행할 함수
     * @param defaultValue
     *            기본값 제공자
     * @return
     *
     * @since 2020. 7. 21.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <T, R> R runIf(T value, Predicate<T> test, Function<T, R> run, Supplier<R> defaultValue) {
        return test.test(value) ? run.apply(value) : defaultValue.get();
    }

    /**
     * 데이터를 검증한 후 함수의 실행 결과를 반환한다. 데이터 검증이 실패한 경우 기본값을 반환한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 6. 14.     박준홍         최초 작성
     * </pre>
     *
     * @param <T>
     * @param <R>
     * @param value
     *            데이터
     * @param test
     *            데이터 검증
     * @param run
     *            실행할 함수
     * @param defaultValue
     * @return
     *
     * @since 2020. 6. 14.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    // public static <T, R> R runIf(T value, Predicate<T> test, Function<T, R> run, R defaultValue) {
    // return test.test(value) ? run.apply(value) : defaultValue;
    // }

    /**
     * 데이터를 검증한 후 함수의 실행 결과를 반환한다. <br>
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 6. 14.     박준홍         최초 작성
     * </pre>
     *
     * @param <T>
     * @param <U>
     * @param <R>
     * @param value
     *            데이터
     * @param test
     *            데이터 검증
     * @param param
     *            데이터를 이용한 실행함수 파라미터 제공자
     * @param run
     *            실행 함수
     * @return
     *
     * @since 2020. 6. 14.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <T, U, R> R runIf(T value, Predicate<T> test, Function<T, U> param, Function<U, R> run) {
        return test.test(value) ? run.apply(param.apply(value)) : null;
    }

    /**
     * 데이터를 검증한 후 함수의 실행 결과를 반환한다. 데이터 검증이 실패한 경우 기본값을 반환한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 7. 22.     박준홍         최초 작성
     * </pre>
     *
     * @param <T>
     * @param <U>
     * @param <R>
     * @param value
     *            데이터
     * @param test
     *            데이터 검증
     * @param param
     *            데이터를 이용한 실행함수 파라미터 제공자
     * @param run
     *            실행 함수
     * @param defualtValue
     *            데이터 검증에 따른 기본값
     * @return
     *
     * @since 2020. 7. 22.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <T, U, R> R runIf(T value, Predicate<T> test, Function<T, U> param, Function<U, R> run, Supplier<R> defaultValue) {
        return test.test(value) ? run.apply(param.apply(value)) : defaultValue.get();
    }

    /**
     * 데이터를 검증한 후 함수의 실행 결과를 반환한다. 데이터 검증이 실패한 경우 기본값을 반환한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 6. 14.     박준홍         최초 작성
     * </pre>
     *
     * @param <T>
     * @param <U>
     * @param <R>
     * @param value
     *            데이터
     * @param test
     *            데이터 검증
     * @param param
     *            데이터를 이용한 실행함수 파라미터 제공자
     * @param run
     *            실행 함수
     * @param defualtValue
     *            데이터 검증에 따른 기본값
     * @return
     *
     * @since 2020. 6. 14.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    // public static <T, U, R> R runIf(T value, Predicate<T> test, Function<T, U> param, Function<U, R> run, R
    // defaultValue) {
    // return test.test(value) ? run.apply(param.apply(value)) : defaultValue;
    // }

    public static <T> void runIf(T value, Predicate<T> p, Runner run) {
        if (p.test(value)) {
            run.run();
        }
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 8. 29.		박준홍			최초 작성
     * </pre>
     *
     * @param <T>
     * @param <U>
     * @param value
     *            데이터
     * @param test
     *            데이터 검증
     * @param param
     *            실행함수 파라미터
     * @param run
     *            실행 함수
     *
     * @since 2020. 8. 29.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <T, U> void runIf(T value, Predicate<T> test, Supplier<U> param, Consumer<U> run) {
        if (test.test(value)) {
            run.accept(param.get());
        }
    }

    /**
     * 데이터를 검증한 후 함수의 실행 결과를 반환한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 6. 14.     박준홍         최초 작성
     * </pre>
     *
     * @param <T>
     * @param <U>
     * @param <R>
     * @param value
     *            데이터
     * @param test
     *            데이터 검증
     * @param param
     *            실행함수 파라미터 제공자
     * @param run
     *            실행 함수
     * @return
     *
     * @since 2020. 6. 14.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <T, U, R> R runIf(T value, Predicate<T> test, Supplier<U> param, Function<U, R> run) {
        return test.test(value) ? run.apply(param.get()) : null;
    }

    /**
     * 데이터를 검증한 후 함수의 실행 결과를 반환한다. 데이터 검증이 실패한 경우 기본값을 반환한다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 7. 21.     박준홍         최초 작성
     * </pre>
     *
     * @param <T>
     * @param <U>
     * @param <R>
     * @param value
     *            데이터
     * @param test
     *            데이터 검증
     * @param param
     *            실행함수 파라미터 제공자
     * @param run
     *            실행 함수
     * @param defaultValue
     *            기본값.
     * @return
     *
     * @since 2020. 7. 21.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <T, U, R> R runIf(T value, Predicate<T> test, Supplier<U> param, Function<U, R> run, Supplier<R> defaultValue) {
        return test.test(value) ? run.apply(param.get()) : defaultValue.get();
    }

    /**
     * 데이터를 검증한 후 함수의 실행 결과를 반환한다. 데이터 검증이 실패한 경우 기본값을 반환한다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 6. 14.     박준홍         최초 작성
     * </pre>
     *
     * @param <T>
     * @param <U>
     * @param <R>
     * @param value
     *            데이터
     * @param test
     *            데이터 검증
     * @param param
     *            실행함수 파라미터 제공자
     * @param run
     *            실행 함수
     * @param defaultValue
     *            기본값.
     * @return
     *
     * @since 2020. 6. 14.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    // public static <T, U, R> R runIf(T value, Predicate<T> test, Supplier<U> param, Function<U, R> run, R
    // defaultValue) {
    // return test.test(value) ? run.apply(param.get()) : defaultValue;
    // }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 8. 29.		박준홍			최초 작성
     * </pre>
     *
     * @param <T>
     * @param <U>
     * @param value
     *            데이터
     * @param test
     *            데이터 검증
     * @param param
     *            실행함수 파라미터
     * @param run
     *            실행 함수
     *
     * @since 2020. 8. 29.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <T, U> void runIf(T value, Predicate<T> test, U param, Consumer<U> run) {
        if (test.test(value)) {
            run.accept(param);
        }
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
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
