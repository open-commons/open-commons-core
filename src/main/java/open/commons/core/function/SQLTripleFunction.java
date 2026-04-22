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
 * Date  : 2020. 1. 20. 오후 3:19:26
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.core.function;

import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;
import java.util.function.Function;

import org.jspecify.annotations.Nullable;

import open.commons.core.annotation.ColumnValue;
import open.commons.core.utils.SQLUtils;

/**
 * 
 * Represents a function that accepts two arguments and produces a result. This is the two-arity
 * specialization of {@link Function}.
 *
 * <p>
 * This is a <a href="package-summary.html">functional interface</a> whose functional method is
 * {@link #apply(Object, Object, Object)}.
 *
 * @subject : Triple Argument Function
 * 
 * @param <T>
 *            the type of the first argument to the function
 * @param <U>
 *            the type of the second argument to the function
 * @param <V>
 *            the type of the third argument to the function
 * @param <R>
 *            the type of the result of the function
 *
 * @since 2020. 1. 20.
 * @version 1.6.17
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 * @see Function
 */
@FunctionalInterface
public interface SQLTripleFunction<T, U, V, R> {

    /**
     * Returns a composed function that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an
     * exception, it is relayed to the caller of the composed function.
     *
     * @param <W>
     *            the type of output of the {@code after} function, and of the composed function
     * @param after
     *            the function to apply after this function is applied
     * @return a composed function that first applies this function and then applies the
     *         {@code after} function
     * @throws SQLException
     *             if occurs an exception while interworking with DBMS.
     * @since 1.6.17
     */
    default <W> SQLTripleFunction<T, U, V, W> andThen(Function<? super R, ? extends W> after) throws SQLException {
        Objects.requireNonNull(after);

        return (T t, U u, V v) -> after.apply(apply(t, u, v));
    }

    /**
     * Applies this function to the given arguments.
     *
     * @param t
     *            the first function argument
     * @param u
     *            the second function argument
     * @param v
     *            the third function argument
     * @return the function result
     * @throws SQLException
     *             if occurs an exception while interworking with DBMS.
     * 
     * @since 1.6.17
     */
    R apply(T t, U u, V v) throws SQLException;

    /**
     * 데이터를 추가하기 위해서 자동으로 {@link PreparedStatement}에 파라미터를 추가하는 함수를 제공합니다. <br>
     * 해당 데이터 모델의 {@link Method}에는 {@link ColumnValue}을 이용하여 컬럼 정보를 설정해야 합니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 1. 22.		parkjunhong77@gmail.com			최초 작성
     * 2020. 12. 22.    parkjunhong77@gmail.com         데이터 설정 메소드 분리.
     * </pre>
     *
     * @param <T>
     *            추가하려는 데이터 타입.
     * @param columnNames
     *            추가하려는 데이터 컬럼값들.
     * @return {@link SQLTripleConsumer}
     *         <ul>
     *         <li>stmt: DB 연동 객체
     *         <li>cur: 현재까지 파라미터가 설정된 index
     *         <li>d: 저정할 데이터
     *         </ul>
     * 
     * @since 2020. 1. 22.
     * @version 1.8.0
     * 
     * @see ColumnValue
     */
    // 아래 내용에 적용됨.
    // - 'stmt, obj' of '(stmt, cur, obj) -> SQLUtils.setParameters(stmt, cur,
    // obj, columnNames)'
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static <T> SQLTripleFunction<PreparedStatement, Integer, T, Integer> setParameters(
            String @Nullable... columnNames) {
        return (stmt, cur, obj) -> SQLUtils.setParameters(stmt, cur, obj, columnNames);
    }
}
