/*
 * Copyright 2020 Park Jun-Hong (parkjunhong77@gmail.com)
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

package open.commons.core.utils;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.Future;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.jspecify.annotations.Nullable;

import open.commons.core.Result;
import open.commons.core.function.Runner;

/**
 * java.util.function 패키지에 있는 클래스에 대한 유틸리티 메소드 제공.
 * 
 * @since 2020. 8. 29.
 * @version 1.7.0
 * 
 * 
 */
public class FunctionUtils {

    private FunctionUtils() {
    }

    /**
     * 실행 기능을 위임받아 수행하고 그 결과를 이용하는 추가 기능 및 에러 처리 기능을 수행하는 객체를 반환합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2020. 6. 12.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param <S>
     *            실행 함수 첫 번째 파라미터 타입
     * @param <T>
     *            실행 함수 두 번째 파라미터 타입
     * @param <U>
     *            실행 함수 반환 타입
     * @param <V>
     *            성공 함수 첫 번째 파라미터 타입
     * @param <W>
     *            성공 함수 두 번째 파라미터 타입
     * @param <X>
     *            최종 반환 타입
     * @param action
     *            실행 함수
     * @param param1
     *            첫 번째 파라미터
     * @param param2
     *            두 번째 파라미터
     * @param onSuccess
     *            실행 함수가 올바르게 수행된 경우 실행할 함수
     * @param osParam1
     *            성공 함수 파라미터 1
     * @param osParam2
     *            성공 함수 파라미터 2 (action의 결과를 받음)
     * @param onError
     *            실행 함수 실행시 에러가 발생한 경우 실행할 함수
     *
     * @return 실행 및 후속 처리를 담당하는 공급자 객체
     *
     * @since 2020. 6. 12.
     */
    public static <S extends @Nullable Object, T extends @Nullable Object, U extends @Nullable Object, V extends @Nullable Object, W extends @Nullable Object, X extends @Nullable Object> //
            Supplier<X> build(BiFunction<S, T, U> action, S param1, T param2, BiFunction<V, W, X> onSuccess, V osParam1, Function<U, W> osParam2, Function<Throwable, X> onError) {
        ObjectUtils.requireNonNulls(action, onSuccess, onError);

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
     * 파라미터 2개를 받아 정의된 기능을 실행하고 에러 결과를 반환하는 객체를 제공합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2020. 6. 12.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param <T>
     *            첫 번째 파라미터 타입
     * @param <U>
     *            두 번째 파라미터 타입
     * @param <R>
     *            실행 함수 반환 데이터 타입
     * @param <X>
     *            최종 반환 데이터 타입
     * @param action
     *            실행 함수
     * @param param1
     *            첫 번째 파라미터
     * @param param2
     *            두 번째 파라미터
     * @param onSuccess
     *            실행 함수가 올바르게 수행된 경우 실행할 함수
     * @param onError
     *            실행 함수 실행시 에러가 발생한 경우 실행할 함수
     *
     * @return 실행 결과를 제공하는 공급자 객체
     *
     * @since 2020. 6. 12.
     */
    public static <T extends @Nullable Object, U extends @Nullable Object, R extends @Nullable Object, X extends @Nullable Object> //
            Supplier<X> build(BiFunction<T, U, R> action, T param1, U param2, Function<R, X> onSuccess, Function<Throwable, X> onError) {
        ObjectUtils.requireNonNulls(action, onSuccess, onError);

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
     * 기능을 지연 실행하여 그 결과를 제공하는 객체를 생성합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2020. 6. 12.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     * 
     * @param <S>
     *            입력 파라미터 타입
     * @param <T>
     *            실행 함수 반환 타입
     * @param <U>
     *            성공 함수 파라미터 타입
     * @param <X>
     *            최종 반환 타입
     * @param action
     *            실행 함수
     * @param param
     *            실행 함수에 전달할 파라미터
     * @param onSuccess
     *            실행 함수가 올바르게 수행된 경우 실행할 함수
     * @param osParam
     *            성공 함수에 전달할 파라미터를 생성하는 함수
     * @param onError
     *            실행 함수 실행시 에러가 발생한 경우 실행할 함수
     *
     * @return 실행 결과를 제공하는 공급자 객체
     *
     * @since 2020. 6. 12.
     */
    public static <S extends @Nullable Object, T extends @Nullable Object, U extends @Nullable Object, X extends @Nullable Object> //
            Supplier<X> build(Function<S, T> action, S param, Function<U, X> onSuccess, Function<T, U> osParam, Function<Throwable, X> onError) {
        ObjectUtils.requireNonNulls(action, onSuccess, onError);

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
     * 파라미터 1개를 받아 정의된 기능을 실행하고 에러 결과를 처리하는 반환 객체를 제공합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2020. 6. 12.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param <T>
     *            입력 파라미터 타입
     * @param <R>
     *            실행 함수 반환 데이터 타입
     * @param <X>
     *            최종 반환 데이터 타입
     * @param action
     *            실행 함수
     * @param param
     *            입력 파라미터
     * @param onSuccess
     *            실행 함수가 올바르게 수행된 경우 실행할 함수
     * @param onError
     *            실행 함수 실행시 에러가 발생한 경우 실행할 예외 함수
     *
     * @return 결과를 제공하는 공급자 객체
     *
     * @since 2020. 6. 12.
     * 
     */
    public static <T extends @Nullable Object, R extends @Nullable Object, X extends @Nullable Object> //
            Supplier<X> build(Function<T, R> action, T param, Function<R, X> onSuccess, Function<Throwable, X> onError) {
        ObjectUtils.requireNonNulls(action, onSuccess, onError);

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
     * 파라미터 1개를 받아 정의된 기능을 실행하고 문자열 에러 메시지를 반환하는 객체를 제공합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2020. 6. 11.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param <T>
     *            입력 파라미터 타입
     * @param <R>
     *            기능 실행 결과의 내부 데이터 타입
     * @param action
     *            기능 정의 실행 함수
     * @param param
     *            전달할 입력 파라미터
     * @param onSuccess
     *            함수 실행이 성공한 경우 실행할 소비 함수
     *
     * @return 에러 메시지를 반환하는 공급자 객체
     *
     * @since 2020. 6. 11.
     * 
     */
    public static <T extends @Nullable Object, R extends @Nullable Object> Supplier<String> build(Function<T, Result<R>> action, T param, Consumer<R> onSuccess) {
        return build(action, param, onSuccess, t -> t.getMessage());
    }

    /**
     * 파라미터 1개를 받아 지정된 기능을 실행하고 에러 메시지를 반환하는 대리 객체를 제공합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2020. 6. 12.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param <T>
     *            입력 파라미터 타입
     * @param <R>
     *            함수 실행 결과 타입
     * @param action
     *            기능 정의 함수
     * @param param
     *            실행 함수에 전달할 입력 파라미터
     * @param onSuccess
     *            함수 실행이 성공한 경우 처리할 함수
     * @param onError
     *            함수 실행이 실패한 경우 호출될 에러 처리 함수
     *
     * @return 발생한 에러 메시지를 반환하는(정상인 경우 null) 공급자 객체
     *
     * @since 2020. 6. 12.
     * 
     */
    // 아래 내용에 적용됨.
    // - r.getData()
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static <T extends @Nullable Object, R extends @Nullable Object> //
            Supplier<String> build(Function<T, Result<R>> action, T param, Consumer<R> onSuccess, Function<Throwable, String> onError) {
        ObjectUtils.requireNonNulls(action, onSuccess, onError);

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
     * 정의된 기능을 실행하고 에러 또는 최종 결과를 반환하는 대리 객체를 제공합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2020. 4. 11.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param <R>
     *            실행 함수 반환 데이터 타입
     * @param <X>
     *            최종 반환 데이터 타입
     * @param action
     *            실행할 파라미터 없는 함수
     * @param onSuccess
     *            실행 함수가 올바르게 수행된 경우 실행할 처리 함수
     * @param onError
     *            실행 함수 실행시 에러가 발생한 경우 실행할 예외 함수
     * 
     * @return 실행 및 에러 결과를 통합하여 반환하는 공급자 객체
     *
     * @since 2020. 4. 11.
     * 
     */
    public static <R extends @Nullable Object, X extends @Nullable Object> //
            Supplier<X> build(Supplier<R> action, Function<R, X> onSuccess, Function<Throwable, X> onError) {
        ObjectUtils.requireNonNulls(action, onSuccess, onError);

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
     * 기능 실행을 대리하고 에러 발생 시 문자열 형태의 에러 결과를 반환하는 객체를 제공합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2020. 4. 11.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param <R>
     *            실행 함수 내부 결과 데이터 타입
     * @param action
     *            실행할 기능 공급 함수
     * @param onSuccess
     *            실행 함수가 올바르게 수행된 경우 값을 처리할 함수
     *
     * @return 예외 메시지 등을 반환하는 문자열 공급자 객체
     *
     * @since 2020. 4. 11.
     * 
     */
    public static <R extends @Nullable Object> Supplier<String> build(Supplier<Result<R>> action, Consumer<R> onSuccess) {
        return build(action, onSuccess, t -> t.getMessage());
    }

    /**
     * 정의된 기능을 실행하고, 처리 결과나 에러 발생 여부에 따라 에러 문자열을 반환하는 객체를 제공합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2020. 4. 11.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param <R>
     *            기능 실행 반환 데이터 타입
     * @param action
     *            실행할 기능 함수
     * @param onSuccess
     *            실행 함수가 성공한 경우 실행될 처리 함수
     * @param onError
     *            실행 함수 실패 시 에러 내용을 다루는 함수
     *
     * @return 에러 메시지를 반환하거나 정상이면 null을 반환하는 공급자 객체
     *
     * @since 2020. 4. 11.
     * 
     */
    // 아래 내용에 적용됨.
    // - r.getData()
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static <R extends @Nullable Object> Supplier<String> build(Supplier<Result<R>> action, Consumer<R> onSuccess, Function<Throwable, String> onError) {
        ObjectUtils.requireNonNulls(action, onSuccess, onError);

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
     * 파라미터 2개를 받아 정의된 기능을 실행하고 에러 결과를 반환하는 객체를 제공합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2020. 4. 7.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param <R>
     *            실제 결과 데이터 타입
     * @param <T>
     *            첫 번째 파라미터 타입
     * @param <U>
     *            두 번째 파라미터 타입
     * @param param1
     *            첫 번째 파라미터
     * @param param2
     *            두 번째 파라미터
     * @param action
     *            기능 실행 함수
     * @param onSuccess
     *            실행이 성공한 경우 결과를 소모할 대리 객체
     *
     * @return 실행 과정의 에러 메시지를 제공하는 객체
     *
     * @since 2020. 4. 7.
     * 
     */
    public static <R extends @Nullable Object, T extends @Nullable Object, U extends @Nullable Object> //
            Supplier<String> build(T param1, U param2, BiFunction<T, U, Result<R>> action, Consumer<R> onSuccess) {
        return build(param1, param2, action, onSuccess, t -> t.getMessage());
    }

    /**
     * 파라미터 2개를 받아 정의된 기능을 실행하고 에러 결과를 반환하는 객체를 제공합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2020. 4. 10.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param <R>
     *            반환 데이터 타입
     * @param <T>
     *            첫 번째 파라미터 타입
     * @param <U>
     *            두 번째 파라미터 타입
     * @param param1
     *            첫 번째 파라미터
     * @param param2
     *            두 번째 파라미터
     * @param action
     *            기능 정의 함수
     * @param onSuccess
     *            함수 실행이 성공한 경우할 처리기
     * @param onError
     *            함수 실행이 실패한 경우 실행할 처리기
     *
     * @return 실행 결과를 제공하는 공급자 객체
     *
     * @since 2020. 4. 10.
     * 
     */
    // 아래 내용에 적용됨.
    // - r.getData()
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static <R extends @Nullable Object, T extends @Nullable Object, U extends @Nullable Object> //
            Supplier<String> build(T param1, U param2, BiFunction<T, U, Result<R>> action, Consumer<R> onSuccess, Function<Throwable, String> onError) {
        ObjectUtils.requireNonNulls(action, onSuccess, onError);

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
     * {@link Future}를 비동기로 실행한 결과를 제공합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2020. 6. 14.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param <R>
     *            반환할 데이터 타입
     * @param future
     *            실행할 비동기 객체
     *
     * @return 비동기 작업 결과
     *
     * @since 2020. 6. 14.
     * 
     */
    // 아래 내용에 적용됨.
    // - future.get()
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static <R> Result<R> getOnAsync(Future<Result<R>> future) {
        Objects.requireNonNull(future);

        try {
            return future.get();
        } catch (Exception e) {
            return new Result<R>().setMessage(e.getMessage());
        }
    }

    /**
     * 조건에 따라 해당하는 데이터를 제공합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2021. 5. 18.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param <T>
     *            반환할 타입
     * @param condition
     *            확인할 조건 함수
     * @param then
     *            조건 반환값이 'true'인 경우 실행할 함수
     * @param elze
     *            조건 반환값이 'false'인 경우 실행할 함수
     *
     * @return 조건에 맞는 반환 데이터
     *
     * @since 2021. 5. 18.
     * 
     */
    public static <T> T ifThenElse(Supplier<Boolean> condition, Supplier<T> then, Supplier<T> elze) {
        ObjectUtils.requireNonNulls(condition, then, elze);

        return condition.get() ? then.get() : elze.get();
    }

    /**
     * 조건에 따라 해당하는 식별된 데이터를 제공합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2021. 5. 18.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param <T>
     *            반환할 데이터 타입
     * @param condition
     *            조건 비교 함수
     * @param then
     *            조건 반환값이 'true'인 경우 제공할 값
     * @param elze
     *            조건 반환값이 'false'인 경우 제공할 값
     *
     * @return 조건에 맞는 반환 데이터
     *
     * @since 2021. 5. 18.
     * 
     */
    public static <T extends @Nullable Object> T ifThenElse(Supplier<Boolean> condition, T then, T elze) {
        Objects.requireNonNull(condition);

        return condition.get() ? then : elze;
    }

    /**
     * 객체가 {@code null}이 아닌지 검사하는 예측 함수 객체를 반환합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2020. 8. 29.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     * 
     * @return 조건 검사 함수
     * 
     * @since 2020. 8. 29.
     */
    public static Predicate<Object> isNotNull() {
        return o -> o != null;
    }

    /**
     * 주어진 값이 {@code null}이 아닌지 검사하는 공급자 객체를 반환합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2020. 8. 29.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     * 
     * @param value
     *            확인할 값
     * 
     * @return 검사하는 공급자
     * 
     * @since 2020. 8. 29.
     */
    public static Supplier<Boolean> isNotNull(@Nullable Object value) {
        return () -> value != null;
    }

    /**
     * 객체가 {@code null}인지 검사하는 예측 함수 객체를 반환합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2020. 8. 29.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     * 
     * @return 조건 검사 함수
     * 
     * @since 2020. 8. 29.
     */
    public static Predicate<Object> isNull() {
        return o -> o == null;
    }

    /**
     * 주어진 값이 {@code null}인지 검사하는 공급자 객체를 반환합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2020. 8. 29.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     * 
     * @param value
     *            확인할 값
     * 
     * @return 검사하는 공급자
     * 
     * @since 2020. 8. 29.
     */
    public static Supplier<Boolean> isNull(@Nullable Object value) {
        return () -> value == null;
    }

    /**
     * 전달받은 값('value')이 {@code null}이 아닌 경우 {@link Runner} 객체를 사용하여 조치를 수행합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2021. 11. 15.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param value
     *            확인할 값
     * @param then
     *            정상인 경우 실행할 함수
     *
     * @since 2021. 11. 15.
     * 
     */
    public static void runIf(@Nullable Object value, Runner then) {
        Objects.requireNonNull(then);

        if (value != null) {
            then.run();
        }
    }

    /**
     * 전달받은 값('value')이 {@code null}이 아닌 경우 및 일치하지 않는 경우 각기 다른 {@link Runner} 함수를 실행합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2021. 11. 15.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param value
     *            확인할 값
     * @param then
     *            {@code null}이 아닌 경우 실행할 함수
     * @param elze
     *            {@code null}인 경우 실행할 함수
     *
     * @since 2021. 11. 15.
     * 
     */
    public static void runIf(@Nullable Object value, Runner then, Runner elze) {
        ObjectUtils.requireNonNulls(then, elze);

        if (value != null) {
            then.run();
        } else {
            elze.run();
        }
    }

    /**
     * 전달받은 값('value')이 {@code null}이 아닌 경우 {@link Consumer} 통해 값을 처리합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2025. 8. 8.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param <T>
     *            파라미터 타입
     * @param value
     *            확인할 값
     * @param then
     *            정상인 경우 실행할 소비 함수
     *
     * @since 2025. 8. 8.
     * 
     */
    public static <T extends @Nullable Object> void runIf(T value, Consumer<T> then) {
        Objects.requireNonNull(then);

        if (value != null) {
            then.accept(value);
        }
    }

    /**
     * 전달받은 값('value')에 대한 검증결과가 참인 경우 대리 함수를 통해 변수를 처리합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2021. 11. 15.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param <T>
     *            파라미터 타입
     * @param value
     *            확인할 값
     * @param p
     *            조건 분석 함수 지정
     * @param then
     *            검사 통과 시 수행할 실행기
     *
     * @since 2021. 11. 15.
     * 
     */
    public static <T extends @Nullable Object> void runIf(T value, Predicate<T> p, Consumer<T> then) {
        ObjectUtils.requireNonNulls(p, then);

        if (p.test(value)) {
            then.accept(value);
        }
    }

    /**
     * 데이터 검증 결과에 따라 알맞은 대리 함수에게 처리를 위임합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2021. 11. 15.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param <T>
     *            파라미터 타입
     * @param value
     *            확인할 값
     * @param p
     *            검증 예측 함수
     * @param then
     *            검사에 통과한 경우 수행할 함수
     * @param elze
     *            실패한 경우 수행할 함수
     *
     * @since 2021. 11. 15.
     * 
     */
    public static <T extends @Nullable Object> void runIf(T value, Predicate<T> p, Consumer<T> then, Consumer<T> elze) {
        ObjectUtils.requireNonNulls(p, then, elze);

        if (p.test(value)) {
            then.accept(value);
        } else {
            elze.accept(value);
        }
    }

    /**
     * 데이터를 검증한 후 통과한 경우에만 함수의 실행 결과를 반환합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2020. 6. 14.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param <T>
     *            파라미터 타입
     * @param <R>
     *            반환 타입
     * @param value
     *            원본 입력 데이터
     * @param test
     *            데이터 제한 검증기
     * @param run
     *            조건 충족 시 실행할 함수
     *
     * @return 함수 실행 결과 (불일치 시 null)
     *
     * @since 2020. 6. 14.
     * 
     */
    public static <T extends @Nullable Object, R extends @Nullable Object> @Nullable R runIf(T value, Predicate<T> test, Function<T, R> run) {
        ObjectUtils.requireNonNulls(test, run);

        return test.test(value) ? run.apply(value) : null;
    }

    /**
     * 데이터를 검증한 후 함수의 실행 결과를 반환합니다. 데이터 검증이 실패한 경우 기본값을 반환합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2021. 11. 15.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param <T>
     *            검증 파라미터 타입
     * @param <R>
     *            결과 요소의 반환 타입
     * @param value
     *            제공되는 원시 데이터
     * @param test
     *            분석용 데이터 검증
     * @param run
     *            조건 만족 시 실행할 함수
     * @param defaultValue
     *            기본적으로 적용할 값 (불만족 시 반환)
     *
     * @return 실행된 결과 또는 기본값
     *
     * @since 2021. 11. 15.
     * 
     */
    public static <T extends @Nullable Object, R extends @Nullable Object> @Nullable R runIf(T value, Predicate<T> test, Function<T, R> run, R defaultValue) {
        ObjectUtils.requireNonNulls(test, run);

        return test.test(value) ? run.apply(value) : defaultValue;
    }

    /**
     * 데이터를 검증한 후 함수의 실행 결과를 반환합니다. 데이터 검증이 실패한 경우 기본값 공급자를 통해 반환값을 결정합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2020. 7. 21.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param <T>
     *            검증 파라미터 타입
     * @param <R>
     *            결과 데이터 타입
     * @param value
     *            대상 파라미터 데이터
     * @param test
     *            진위 판단을 위한 데이터 검증
     * @param run
     *            조건 달성 시 실행할 함수
     * @param defaultValue
     *            실패 시 실행될 기본값 제공자 함수
     *
     * @return 함수의 처리 결과 또는 기본값 공급의 결과
     *
     * @since 2020. 7. 21.
     * 
     */
    public static <T extends @Nullable Object, R extends @Nullable Object> @Nullable R runIf(T value, Predicate<T> test, Function<T, R> run, Supplier<R> defaultValue) {
        ObjectUtils.requireNonNulls(test, run);

        return test.test(value) ? run.apply(value) : defaultValue.get();
    }

    /**
     * 데이터를 검증하여 합격한 후 보조 파라미터를 계산하고 지정된 기능을 실행한 결과를 반환합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2020. 6. 14.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param <T>
     *            원본 입력 데이터 타입
     * @param <U>
     *            변환된 파라미터 데이터 타입
     * @param <R>
     *            실제 반환 데이터 타입
     * @param value
     *            대상 값
     * @param test
     *            대상 데이터 검증 함수
     * @param param
     *            데이터를 이용한 실행함수 파라미터 제공자
     * @param run
     *            변환 파라미터를 수행시킬 타겟 함수
     *
     * @return 계산 반환된 결과나 null 값 반환
     *
     * @since 2020. 6. 14.
     * 
     */
    public static <T extends @Nullable Object, U extends @Nullable Object, R extends @Nullable Object> @Nullable R runIf(T value, Predicate<T> test, Function<T, U> param,
            Function<U, R> run) {
        ObjectUtils.requireNonNulls(test, param, run);

        return test.test(value) ? run.apply(param.apply(value)) : null;
    }

    /**
     * 데이터를 검증한 후 함수의 실행 결과를 반환합니다. 데이터 검증이 실패한 경우 기본값을 반환합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2021. 11. 15.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param <T>
     *            입력 데이터 타입
     * @param <U>
     *            실행 파라미터 타입
     * @param <R>
     *            최종 결과 타입
     * @param value
     *            원본 입력 데이터
     * @param test
     *            진위 판단을 위한 검증
     * @param param
     *            데이터를 이용한 실행함수 파라미터 제공자
     * @param run
     *            파라미터를 받아 단일 변환을 수행할 실행 함수
     * @param defaultValue
     *            데이터 검증에 따른 기본값
     *
     * @return 실행 함수의 반환 결과 또는 기본값
     *
     * @since 2021. 11. 15.
     * 
     */
    public static <T extends @Nullable Object, U extends @Nullable Object, R extends @Nullable Object> //
            @Nullable R runIf(T value, Predicate<T> test, Function<T, U> param, Function<U, R> run, R defaultValue) {
        ObjectUtils.requireNonNulls(test, param, run);

        return test.test(value) ? run.apply(param.apply(value)) : defaultValue;
    }

    /**
     * 데이터를 검증한 후 함수의 실행 결과를 반환합니다. 데이터 검증이 실패한 경우 대리 객체로 기본값을 반환합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2020. 7. 22.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param <T>
     *            입력 데이터 타입
     * @param <U>
     *            파라미터 타입
     * @param <R>
     *            반환 타입
     * @param value
     *            대상 파라미터 데이터
     * @param test
     *            조건을 위한 데이터 검증식
     * @param param
     *            데이터를 이용한 실행함수 파라미터 제공자
     * @param run
     *            실행할 조작 함수
     * @param defaultValue
     *            데이터 검증 실패 시 기본값을 제공하는 공급자
     *
     * @return 성공적인 처리 결과 또는 기본값
     *
     * @since 2020. 7. 22.
     * 
     */
    public static <T extends @Nullable Object, U extends @Nullable Object, R extends @Nullable Object> //
            @Nullable R runIf(T value, Predicate<T> test, Function<T, U> param, Function<U, R> run, Supplier<R> defaultValue) {
        ObjectUtils.requireNonNulls(test, param, run);

        return test.test(value) ? run.apply(param.apply(value)) : defaultValue.get();
    }

    /**
     * 대상 파라미터 값이 지정된 조건 함수를 통과할 때에만 실행 작업을 수행합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2020. 8. 29.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param <T>
     *            파라미터 타입
     * @param value
     *            데이터 객체
     * @param p
     *            데이터 분석 및 예측 함수기
     * @param run
     *            실행 함수
     *
     * @since 2020. 8. 29.
     */
    public static <T extends @Nullable Object> void runIf(T value, Predicate<T> p, Runner run) {
        ObjectUtils.requireNonNulls(p, run);

        if (p.test(value)) {
            run.run();
        }
    }

    /**
     * 데이터가 조건을 만족하는 경우 대상 객체의 값을 이용해 추가적인 작업 처리를 수행합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2020. 8. 29.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param <T>
     *            입력 데이터 타입
     * @param <U>
     *            파라미터 타입
     * @param value
     *            분석용 원시 데이터
     * @param test
     *            진위 판단 검증기
     * @param param
     *            실행함수 처리를 위한 파라미터 공급자
     * @param run
     *            수행될 처리 함수
     *
     * @since 2020. 8. 29.
     * 
     */
    public static <T extends @Nullable Object, U extends @Nullable Object> void runIf(T value, Predicate<T> test, Supplier<U> param, Consumer<U> run) {
        ObjectUtils.requireNonNulls(test, param, run);

        if (test.test(value)) {
            run.accept(param.get());
        }
    }

    /**
     * 데이터가 검증을 통과하는 경우 다른 대상 공급자로부터 값을 얻어 함수에 적용하여 그 결과를 반환합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2020. 6. 14.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param <T>
     *            원시 데이터 타입
     * @param <U>
     *            파라미터 타입
     * @param <R>
     *            결과 타입
     * @param value
     *            분석할 데이터
     * @param test
     *            데이터 검증 조건 함수기
     * @param param
     *            실행함수 파라미터 제공 공급자
     * @param run
     *            수행될 변환 함수
     *
     * @return 처리가 된 반환 결과 또는 null
     *
     * @since 2020. 6. 14.
     * 
     */
    public static <T extends @Nullable Object, U extends @Nullable Object, R extends @Nullable Object> //
            @Nullable R runIf(T value, Predicate<T> test, Supplier<U> param, Function<U, R> run) {
        ObjectUtils.requireNonNulls(test, param, run);

        return test.test(value) ? run.apply(param.get()) : null;
    }

    /**
     * 데이터를 검증한 후 함수의 실행 결과를 반환합니다. 데이터 검증이 실패한 경우 기본값을 반환합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2021. 11. 15.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param <T>
     *            데이터 검증 파라미터 타입
     * @param <U>
     *            변환 파라미터 타입
     * @param <R>
     *            반환 타입
     * @param value
     *            분석될 데이터
     * @param test
     *            데이터 검증 함수기
     * @param param
     *            실행함수 파라미터 제공 공급자
     * @param run
     *            실행 함수
     * @param defaultValue
     *            오류나 조건 불일치 시의 기본값
     *
     * @return 최종 반환 결과 또는 기본값
     *
     * @since 2021. 11. 15.
     * 
     */
    public static <T extends @Nullable Object, U extends @Nullable Object, R extends @Nullable Object> //
            @Nullable R runIf(T value, Predicate<T> test, Supplier<U> param, Function<U, R> run, R defaultValue) {
        ObjectUtils.requireNonNulls(test, param, run);

        return test.test(value) ? run.apply(param.get()) : defaultValue;
    }

    /**
     * 데이터 검증이 성공했을 때 함수를 실행하여 결과를 반환하고 실패하면 지정된 대리 객체에서 가져온 기본값을 반환합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2020. 7. 21.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param <T>
     *            데이터 타입
     * @param <U>
     *            파라미터 타입
     * @param <R>
     *            반환 데이터 타입
     * @param value
     *            판단할 원시 데이터
     * @param test
     *            조건 검증을 진행할 함수
     * @param param
     *            실행함수 파라미터 전달 공급자
     * @param run
     *            실행 가능한 조작기 함수
     * @param defaultValue
     *            기본값을 제공하는 대리 공급자 함수
     *
     * @return 대상 결과를 처리하여 얻거나 제공받은 데이터
     *
     * @since 2020. 7. 21.
     * 
     */
    public static <T extends @Nullable Object, U extends @Nullable Object, R extends @Nullable Object> //
            @Nullable R runIf(T value, Predicate<T> test, Supplier<U> param, Function<U, R> run, Supplier<R> defaultValue) {
        ObjectUtils.requireNonNulls(test, param, run);

        return test.test(value) ? run.apply(param.get()) : defaultValue.get();
    }

    /**
     * 값을 조건에 맞춰 검증하고 참이면 전달된 파라미터 모델을 이용해 소비 함수 처리를 수행합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2020. 8. 29.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param <T>
     *            원시 데이터 형식 타입
     * @param <U>
     *            소모 파라미터 형식 타입
     * @param value
     *            판단할 데이터
     * @param test
     *            데이터 판단 검증 함수
     * @param param
     *            실행함수에 사용할 파라미터 값
     * @param run
     *            매개변수를 다루는 실제 실행 함수
     *
     * @since 2020. 8. 29.
     * 
     */
    public static <T extends @Nullable Object, U extends @Nullable Object> void runIf(T value, Predicate<T> test, U param, Consumer<U> run) {
        ObjectUtils.requireNonNulls(test, run);

        if (test.test(value)) {
            run.accept(param);
        }
    }

    /**
     * 비동기 작업 기능들을 수행 후 반환 결과 조건 필터를 만족하는 첫 번째 데이터 존재 유무를 반환합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2020. 4. 7.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     * 
     * @param <R>
     *            반환될 데이터 타입 형식
     * @param filterIn
     *            결과 포함 여부를 판단할 (positive) 데이터 검증 함수기. {@code true}인 경우 결과로 포함시킵니다.
     * @param actions
     *            비동기로 처리될 기능 정의 목록들
     *
     * @return 선택된 요소 존재 유무
     *
     * @since 2020. 4. 7.
     * 
     */
    // 아래 내용에 적용됨.
    // - ObjectUtils.requireNonNulls((Object[]) actions);
    // [PATCH] 배열 공변성/가변성에 대한 IDE 분석기의 오탐 우회
    // [TODO] 향후 IDE의 배열 데이터 흐름 분석이 고도화되거나 JSpecify가 완벽히 지원되면 '제거'
    // 아래 내용에 적용됨.
    // - Arraysl.finaAny()
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    @SafeVarargs
    public static <R extends @Nullable Object> Optional<R> runOnAsync(Predicate<R> filterIn, Supplier<R>... actions) {
        Objects.requireNonNull(filterIn);
        ObjectUtils.requireNonNulls((Object[]) actions);

        return Arrays.asList(actions).parallelStream() //
                // 실행
                .map(s -> s.get()) //
                // 반환 데이터 확인
                .filter(filterIn) //
                .findAny();
    }

    /**
     * 동기 작업 기능들을 수행 순서대로 작업하고 반환 데이터가 지정 조건 필터를 만족하면 그 데이터를 제공합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2020. 4. 7.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     * 
     * @param <R>
     *            처리 결과의 반환 데이터 타입 형식
     * @param filterIn
     *            결과를 판별할 (positive) 검증 함수. 참({@code true}) 판명된 데이터가 반환 대상이 됩니다.
     * @param actions
     *            순차적으로 처리될 기능 공급자 배열 리스트
     *
     * @return 조건에 맞는 하나 이상의 존재 여부 혹은 데이터 반환
     *
     * @since 2020. 4. 7.
     * 
     */
    // 아래 내용에 적용됨.
    // - ObjectUtils.requireNonNulls((Object[]) actions);
    // [PATCH] 배열 공변성/가변성에 대한 IDE 분석기의 오탐 우회
    // [TODO] 향후 IDE의 배열 데이터 흐름 분석이 고도화되거나 JSpecify가 완벽히 지원되면 '제거'
    // 아래 내용에 적용됨.
    // - Stream.findAny()
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    @SafeVarargs
    public static <R extends @Nullable Object> Optional<R> runOnSync(Predicate<R> filterIn, Supplier<R>... actions) {
        Objects.requireNonNull(filterIn);
        ObjectUtils.requireNonNulls((Object[]) actions);

        return Arrays.asList(actions).stream() //
                // 실행
                .map(s -> s.get()) //
                // 데이터 확인
                .filter(filterIn) //
                .findAny();
    }
}
