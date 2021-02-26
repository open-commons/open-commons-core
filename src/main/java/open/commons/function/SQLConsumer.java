/*
 * Copyright 2019 Park Jun-Hong (parkjunhong77/google/com)
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
 * Date  : 2019. 2. 19. 오전 10:33:53
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package open.commons.function;

import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

import open.commons.annotation.ColumnValue;
import open.commons.utils.ArrayUtils;
import open.commons.utils.ObjectUtils;
import open.commons.utils.SQLUtils;

/**
 * Represents an operation that accepts a single input argument and returns no result. Unlike most other functional
 * interfaces, {@code Consumer} is expected to operate via side-effects.
 *
 * <p>
 * This is a <a href="package-summary.html">functional interface</a> whose functional method is {@link #accept(Object)}.
 *
 * @param <T>
 *            the type of the input to the operation
 *
 * @since 1.8
 */
@FunctionalInterface
public interface SQLConsumer<T> {

    /**
     * Performs this operation on the given argument.
     *
     * @param t
     *            the input argument
     */
    void accept(T t) throws SQLException;

    /**
     * Returns a composed {@code Consumer} that performs, in sequence, this operation followed by the {@code after}
     * operation. If performing either operation throws an exception, it is relayed to the caller of the composed
     * operation. If performing this operation throws an exception, the {@code after} operation will not be performed.
     *
     * @param after
     *            the operation to perform after this operation
     * @return a composed {@code Consumer} that performs in sequence this operation followed by the {@code after}
     *         operation
     * @throws NullPointerException
     *             if {@code after} is null
     */
    default SQLConsumer<T> andThen(SQLConsumer<? super T> after) {
        Objects.requireNonNull(after);
        return (T t) -> {
            accept(t);
            after.accept(t);
        };
    }

    /**
     * {@link PreparedStatement}에 데이터를 설정한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 1. 22.		박준홍			최초 작성
     * 2020. 8. 13.		박준홍			파라미터 자원 release
     * </pre>
     *
     * @param params
     *            쿼리 파라미터.
     * @return
     *
     * @since 2020. 1. 22.
     * @version 1.6.17
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public static SQLConsumer<PreparedStatement> setParameters(Object... params) {
        return stmt -> {
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
                params[i] = null;
            }
        };
    }

    /**
     * {@link PreparedStatement}에 데이터를 설정한다. <br>
     * 해당 객체가 Primitive Type, Wrapper class Type 또는 {@link String} 인 경우 {@link #setParameters(Object...)} 로 전달되고,<br>
     * 그외 Reference Type인 경우 {@link ColumnValue}가 적용된 {@link Method}를 이용하여 파라미터를 추출한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 12. 22.        박준홍     최초 작성
     * 2021. 02. 26.        박준홍     버그 수정. (파라미터가 문자열만 오는 경우 판단 오류 수정. Reported by. 'jhlee@ymtech.kr'
     * </pre>
     *
     * @param param
     * @return
     *
     * @since 2020. 12. 22.
     * @version 1.8.0
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @see SQLUtils#setParameters(PreparedStatement, int, Object, String...)
     */
    public static SQLConsumer<PreparedStatement> setParameters(Object param, String... columnNames) {
        if (ObjectUtils.isPrimitive(param) //
                || ObjectUtils.isWrapper(param) //
                || String.class.equals(param.getClass()) //
        ) {
            return setParameters( //
                    columnNames != null && columnNames.length > 0 //
                            ? ArrayUtils.prepend(columnNames, (String) param)//
                            : new Object[] { param } //
            );
        } else {
            return stmt -> SQLUtils.setParameters(stmt, 0, param, columnNames);
        }
    }
}