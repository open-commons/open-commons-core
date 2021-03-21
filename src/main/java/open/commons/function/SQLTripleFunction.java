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
 * Date  : 2020. 1. 20. 오후 3:19:26
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package open.commons.function;

import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;
import java.util.function.Function;

import open.commons.annotation.ColumnValue;
import open.commons.utils.SQLUtils;

/**
 * 
 * Represents a function that accepts two arguments and produces a result. This is the two-arity specialization of
 * {@link Function}.
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
 * 
 * @since 2020. 1. 20.
 * @version 1.6.17
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * @see Function
 */
@FunctionalInterface
public interface SQLTripleFunction<T, U, V, R> {

//    final Pattern METHOD_BOOLEAN_PATTERN = Pattern.compile("^(is|get)(.+)$");
//    final Pattern METHOD_PATTERN = Pattern.compile("^(get)(.+)$");
//
//    /**
//     * 2개의 문자열을 대/소문자 비교여부에 따라서 비교
//     * 
//     * @param s1
//     *            비교할 문자열
//     * @param s2
//     *            비교할 문자열
//     * @param cs
//     *            대/소문자 비교 여부
//     * 
//     * @return 문자여 비교 결과
//     */
//    final TripleFunction<String, String, Boolean, Boolean> COLUMN_CHECKER = (s1, s2, cs) -> {
//        if (cs) {
//            return s1.equals(s2);
//        } else {
//            return s1.equalsIgnoreCase(s2);
//        }
//    };
//
//    /**
//     * {@link Method} 이름을 패턴 비교하여 컬럼명을 추출하여 제공한다.
//     * 
//     * @param ptn
//     *            {@link Method} 이름 비교 {@link Pattern}
//     * @param str
//     *            메소드 이름
//     * 
//     * @return 패턴과 매칭되지 않는 경우 <code>null</code>을 반환한다.
//     */
//    final BiFunction<Pattern, String, String> METHOD_MATCHER = (ptn, str) -> {
//        Matcher m = ptn.matcher(str);
//        if (m.matches()) {
//            return StringUtils.toLowerCase(m.group(2), 0);
//        } else {
//            return null;
//        }
//    };
//
//    final BiFunction<Class<?>, Class<ColumnValue>, List<Method>> COLUMN_VALUE_METHOD_PROVIDER = AnnotationUtils::getAnnotatedMethodsAll;

    /**
     * Returns a composed function that first applies this function to its input, and then applies the {@code after}
     * function to the result. If evaluation of either function throws an exception, it is relayed to the caller of the
     * composed function.
     *
     * @param <W>
     *            the type of output of the {@code after} function, and of the composed function
     * @param after
     *            the function to apply after this function is applied
     * @return a composed function that first applies this function and then applies the {@code after} function
     * @throws SQLException
     *             if occurs an exception while interworking with DBMS.
     * @throws NullPointerException
     *             if after is null
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
     * 데이터를 추가하기 위해서 자동으로 {@link PreparedStatement}에 파라미터를 추가하는 함수를 제공한다. <br>
     * 해당 데이터 모델의 {@link Method}에는 {@link ColumnValue}을 이용하여 컬럼 정보를 설정해야 한다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 1. 22.		박준홍			최초 작성
     * 2020. 12. 22.    박준홍         데이터 설정 메소드 분리.
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
     *
     * @since 2020. 1. 22.
     * @version _._._
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * 
     * @see ColumnValue
     */
    public static <T> SQLTripleFunction<PreparedStatement, Integer, T, Integer> setParameters(String... columnNames) {
         return (stmt, cur, obj) -> SQLUtils.setParameters(stmt, cur, obj, columnNames);
//        return (stmt, cur, obj) -> {
//            // #1. @ColumnValue 어노테이션이 설정된 Method 조회
//            List<Method> methods = SQLUtils.COLUMN_VALUE_METHOD_PROVIDER.apply(obj.getClass(), ColumnValue.class);
//
//            // #2. 사용자 지정 컬럼 여부에 따른 Method 필터링
//            if (columnNames == null || columnNames.length < 1) {
//                // ColumnValue#order() 값에 대한 오름차순 정렬 적용
//                methods.sort(Comparator.comparing(m -> {
//                    return m.getAnnotation(ColumnValue.class).order();
//                }));
//            } else {
//                // 메소드별 컬럼명 대/소문자 비교 여부
//                final Map<String, Boolean> clmnCaseSensitive = new HashMap<>();
//                // 컬럼이름/메소드
//                final Map<String, Method> methodMap = CollectionUtils.toMapHSV(methods, m -> {
//                    ColumnValue cv = m.getClass().getAnnotation(ColumnValue.class);
//                    // 설정된 컬럼명이 빈 문자열이 경우 처리
//                    String clmn = cv.name();
//                    if (clmn.isEmpty()) {
//                        Class<?> rtnClass = m.getReturnType();
//
//                        if (boolean.class.isAssignableFrom(rtnClass) //
//                                || Boolean.class.isAssignableFrom(rtnClass)) {
//                            clmn = SQLUtils.METHOD_MATCHER.apply(SQLUtils.METHOD_BOOLEAN_PATTERN, m.getName());
//                        } else {
//                            clmn = SQLUtils.METHOD_MATCHER.apply(SQLUtils.METHOD_PATTERN, m.getName());
//                        }
//
//                        if (clmn == null) {
//                            throw new IllegalArgumentException(String.format("해당 데이터에 대한 컬럼명이 설정되지 않았습니다. 설정: %s, 메소드: %s", cv, m));
//                        }
//
//                        // begin - PATCH [2020. 9. 24.]: 컬럼명 타입에 따라 자동 변경 적용 | Park_Jun_Hong_(fafanmama_at_naver_com)
//                        switch (cv.columnNameType()) {
//                            case CAMEL_CASE:
//                                clmn = StringUtils.toLowerCase(clmn, 0);
//                                break;
//                            case PASCAL_CASE:
//                                clmn = StringUtils.toPascalCase(clmn);
//                                break;
//                            case SNAKE_CASE:
//                                clmn = StringUtils.toSnakeCase(clmn);
//                                break;
//                            case NAME:
//                                // 그대로 사용
//                                break;
//                            default:
//                                throw new IllegalArgumentException(
//                                        String.format("지원하지 않는 컬럼명 타입입니다. 지원: %s, 입력: %s", Arrays.toString(ColumnNameType.values()), cv.columnNameType()));
//                        }
//                        // end - Park_Jun_Hong_(fafanmama_at_naver_com), 2020. 9. 24.
//
//                    }
//
//                    clmnCaseSensitive.put(clmn, cv.caseSensitive());
//
//                    return clmn;
//                }, m -> m);
//
//                methods = new ArrayList<>();
//
//                for (String clmn : columnNames) {
//                    for (Entry<String, Method> entry : methodMap.entrySet()) {
//                        if (SQLUtils.COLUMN_CHECKER.apply(clmn, entry.getKey(), clmnCaseSensitive.get(entry.getKey()))) {
//                            methods.add(entry.getValue());
//                            break;
//                        }
//                    }
//                    throw new IllegalArgumentException(String.format("지원하지 않는 컬럼이름입니다. 컬럼명: %s, 메소드: %s", clmn, methodMap));
//                }
//            }
//
//            // #3. 필터링 된 Method를 이용하여 파라미터 값 설정
//            for (Method m : methods) {
//                try {
//                    stmt.setObject(++cur, m.invoke(obj));
//                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
//                    throw new SQLException("파라미터 설정 도중 에러가 발생하였습니다. 원인: " + e.getMessage(), e);
//                }
//            }
//
//            return cur;
//        };
    }
}
