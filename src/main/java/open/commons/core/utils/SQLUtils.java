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
 * This file is generated under this project, "open-commons-core".
 *
 * Date  : 2017. 9. 22. 오후 5:06:44
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.core.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.TreeMap;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import open.commons.core.TwoValueObject;
import open.commons.core.annotation.ColumnDecl;
import open.commons.core.annotation.ColumnDef;
import open.commons.core.annotation.ColumnDef.ColumnNameType;
import open.commons.core.annotation.ColumnValue;
import open.commons.core.database.annotation.ColumnConstraint;
import open.commons.core.database.annotation.TableDef;
import open.commons.core.function.TripleFunction;

/**
 * 
 * @since 2017. 9. 22.
 */
public class SQLUtils {

    @SuppressWarnings({ "unused", "null" })
    private static final Logger LOGGER = LoggerFactory.getLogger(SQLUtils.class);

    @SuppressWarnings("null")
    public static final Pattern METHOD_BOOLEAN_PATTERN = Pattern.compile("^(is|get)(.+)$");
    @SuppressWarnings("null")
    public static final Pattern METHOD_PATTERN = Pattern.compile("^(get)(.+)$");
    @SuppressWarnings("null")
    public static final Pattern METHOD_SETTER_PATTERN = Pattern.compile("^(set)(.+)$");

    /**
     * 2개의 문자열을 대/소문자 비교여부에 따라서 비교
     * 
     * @param s1
     *            비교할 문자열 <b>{@code nullable}</b>
     * @param s2
     *            비교할 문자열 <b>{@code nullable}</b>
     * @param cs
     *            대/소문자 비교 여부
     * 
     * @return 문자여 비교 결과
     */
    public static final TripleFunction<String, String, Boolean, Boolean> COLUMN_CHECKER = (s1, s2, cs) -> {
        if (s1 != null && s2 != null) {
            if (cs) {
                return s1.equals(s2);
            } else {
                return s1.equalsIgnoreCase(s2);
            }
        } else if (s1 != null || s2 != null) {
            return false;
        } else {
            return true;
        }
    };

    /**
     * {@link Method} 이름을 패턴 비교하여 컬럼명을 추출하여 제공합니다.
     * 
     * @param ptn
     *            {@link Method} 이름 비교 {@link Pattern}
     *            <font color="red">(<b>{@code NOT nullable}</b>)</font>
     * @param str
     *            메소드 이름
     * 
     * @return 패턴과 매칭되지 않는 경우 {@code null}을 반환합니다.
     */
    // 아래 내용에 적용됨.
    // - Matcher.group(...)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static final BiFunction<Pattern, String, @Nullable String> METHOD_MATCHER = (ptn, str) -> {
        Matcher m = ptn.matcher(str);
        if (m.matches()) {
            return StringUtils.toLowerCase(m.group(2), 0);
        } else {
            return null;
        }
    };

    /**
     * 주어진 클래스에서 정의한 메도스에서 {@link ColumnValue}이 설정되고,
     * {@link ColumnValue#defaultColumn()}가 {@code false}인 메소드를 제공합니다.
     * 
     * @param typeClass
     * 
     * @return
     * 
     * @see ColumnValue#defaultColumn()
     */
    public static final Function<Class<?>, List<Method>> COLUMN_VALUE_METHOD_PROVIDER = typeClass -> {
        return Arrays.stream(typeClass.getMethods()) // create methods stream
                .filter(m -> {
                    ColumnValue annoCv = m.getAnnotation(ColumnValue.class);
                    // start - 컬럼 생성시 정의된 'default' 속성에 따라서 생성되는 컬럼을 제외 : 2022.
                    // 11. 1. 오후 2:28:00
                    return annoCv != null && !annoCv.defaultColumn();
                    // end - 컬럼 생성시 정의된 'default' 속성에 따라서 생성되는 컬럼을 제외 : 2022.
                    // 11. 1. 오후 2:28:00

                }) // check annotation
                .collect(Collectors.toList());
    };

    /**
     * 
     * @since 2017. 9. 22.
     */
    private SQLUtils() {
    }

    /**
     * Table Column 조건 중에 'null' 과 길이에 대한 조건을 확인하여 제공합니다. <br>
     * 코드 정의
     * <ul>
     * <li>0: 정상
     * <li>1: 길이 오류
     * <li>2: null 오류
     * </ul>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 11. 9.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param object
     * 
     * @return Table Column 순서에 따른 검증결과. <br>
     *         예) 00000000000000000120: 18: 길이 오류, 19th: null 오류
     *
     * @since 2020. 11. 9.
     * 
     * @see ColumnConstraint
     */
    // 아래 내용에 적용됨.
    // - value.toString()
    // - new ArrayList<>(validations.values()).toArray(new Character[] {})
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static char[] checkColumnConstraints(Object object) {
        Objects.requireNonNull(object);

        TreeMap<Integer, Character> validations = new TreeMap<>();

        AnnotationUtils.getAnnotatedMethodsAllAsStream(object.getClass(), ColumnConstraint.class) //
                // 어노테이션 확인
                .sorted((o1, o2) -> {
                    ColumnConstraint cc1 = Objects.requireNonNull(o1.getAnnotation(ColumnConstraint.class));
                    ColumnConstraint cc2 = Objects.requireNonNull(o2.getAnnotation(ColumnConstraint.class));
                    return cc1.index() - cc2.index();
                })
                // 메소드 정렬
                .collect(Collectors.toList())
                // 데이터 검증
                .stream() //
                .forEach(m -> {
                    @NonNull
                    ColumnConstraint anno = Objects.requireNonNull(m.getAnnotation(ColumnConstraint.class));
                    int index = anno.index();
                    char chekced = '0';
                    try {
                        Object value = m.invoke(object);
                        // #1. has 'length' & not null
                        if (anno.hasLength() && !anno.nullable()) {
                            if (value == null) {
                                chekced = '2';
                            } else if (lengthOnOracle(value.toString()) > anno.length()) {
                                chekced = '1';
                            }
                        } else
                        // #2. has 'length' & nullable
                        if (anno.hasLength()) {
                            if (value != null && lengthOnOracle(value.toString()) > anno.length()) {
                                chekced = '1';
                            }
                        } else
                        // #3. has NOT 'length' & not null
                        if (!anno.nullable()) {
                            if (value == null) {
                                chekced = '2';
                            }
                        }
                        validations.put(index, chekced);
                    } catch (Throwable e) {
                        throw ExceptionUtils.newException(RuntimeException.class, e,
                                "데이터 검증 중 에러가 발생하였습니다. data=%s, method=%s", object, m);
                    }
                });
        char[] result = ArrayUtils.toPrimitiveArray(new ArrayList<>(validations.values()).toArray(new Character[] {}));
        return result;
    }

    /**
     * 2개의 객체에서 주어진 컬럼에 해당하는 또는 모든 컬럼 값을 비교해서 서로 다른 컬럼 정보를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 6. 17.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param obj1
     * @param obj2
     * @param columns
     * @return
     * @throws RuntimeException
     *             2개의 데이터를 비교할 때 발생할 수 있는 예외.
     *             <ul>
     *             <li>IllegalAccessException
     *             <li>IllegalArgumentException
     *             <li>InvocationTargetException
     *             </ul>
     *
     * @since 2019. 6. 17.
     * 
     * @see ColumnDecl
     */
    public static <T> Map<String, TwoValueObject<Object, Object>> findDifferences(T obj1, T obj2, String... columns)
            throws RuntimeException {
        Objects.requireNonNull(obj1);
        Objects.requireNonNull(obj2);

        Class<?> dataType = obj1.getClass();
        // #0. @ColumnDecl 어노테이션이 있는 메소드 조회
        Collection<Method> methods = ReflectionUtils.getAnnotatedMethods(ColumnDecl.class, dataType);

        Map<String, TwoValueObject<Object, Object>> diff = new HashMap<>();

        methods.stream() //
                .forEach(m -> {
                    try {
                        Object v1 = m.invoke(obj1);
                        Object v2 = m.invoke(obj2);

                        if (v1 == null && v2 == null) {
                            return;
                        }

                        if (v1 == null // v1 is only null
                                || v2 == null // v2 is only null
                                || !v1.equals(v2) // v1 & v2 are not null and
                                                  // not equal
                        ) {
                            ColumnDecl anno = Objects.requireNonNull(m.getAnnotation(ColumnDecl.class));

                            String annoValue = anno.value().strip();
                            String annoColumn = anno.column().strip();

                            if (StringUtils.isNullOrEmptyStringAnd(annoValue, annoColumn)) {
                                throw new IllegalArgumentException(String
                                        .format("컬럼명은 빈문자열이 올 수 없습니다. column=%s, value=%s", annoColumn, annoValue));
                            }

                            String column = annoValue.isEmpty() ? annoColumn : annoValue;

                            diff.put(column, new TwoValueObject<Object, Object>(v1, v2));
                        }
                    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                });

        return diff;
    }

    /**
     * 컬럼이름을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 11. 24.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param clmnDef
     *            데이터를 제공할 DB 컬럼 정의 정보
     * @param method
     *            DB 조회 결과 설정 함수
     * @return
     *
     * @since 2022. 11. 24.
     * @version 2.0.0
     */
    public static String getColumnName(ColumnDef clmnDef, Method method) {
        Objects.requireNonNull(clmnDef);
        Objects.requireNonNull(method);

        return getColumnName(clmnDef.name(), clmnDef.columnNameType(),
                () -> METHOD_MATCHER.apply(METHOD_SETTER_PATTERN, method.getName()));
    }

    /**
     * 컬럼이름을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 11. 24.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param clmnValue
     *            변경할 DB 컬럼 정의 정보
     * @param method
     *            변경할 데이터 제공 함수
     * @return
     *
     * @since 2022. 11. 24.
     * @version 2.0.0
     */
    public static String getColumnName(ColumnValue clmnValue, Method method) {
        Objects.requireNonNull(clmnValue);
        Objects.requireNonNull(method);

        return getColumnName(clmnValue.name(), clmnValue.columnNameType(),
                () -> METHOD_MATCHER.apply(METHOD_PATTERN, method.getName()));
    }

    /**
     * 메소드에 설정된 {@link ColumnValue}에의 컬럼명을 제공합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 09. 24.		parkjunhong77@gmail.com     최초 작성
     * 2021. 11. 26.        parkjunhong77@gmail.com     메소드로 별도 분리.
     * </pre>
     *
     * @param method
     * @return
     *
     * @since 2020. 09. 24.
     * @version 1.8.0
     * 
     * @see ColumnValue
     * @see ColumnValue#name()
     */
    public static final @Nullable String getColumnName(Method method) throws NullPointerException {
        Objects.requireNonNull(method);

        ColumnValue cv = method.getAnnotation(ColumnValue.class);
        // 설정된 컬럼명이 빈 문자열이 경우 처리
        return cv == null //
                ? null //
                : getColumnName(cv.name(), cv.columnNameType(), () -> {

                    Class<?> rtnClass = method.getReturnType();

                    String clmnName = null;
                    if (boolean.class.isAssignableFrom(rtnClass) //
                            || Boolean.class.isAssignableFrom(rtnClass)) {
                        clmnName = METHOD_MATCHER.apply(METHOD_BOOLEAN_PATTERN, method.getName());
                    } else {
                        clmnName = METHOD_MATCHER.apply(METHOD_PATTERN, method.getName());
                    }

                    if (clmnName != null) {
                        return clmnName;
                    } else {
                        throw new IllegalArgumentException(
                                String.format("해당 데이터에 대한 컬럼명이 설정되지 않았습니다. 설정: %s, 메소드: %s", cv, method));
                    }
                });
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 11. 25.		parkjunhong77@gmail.com			최초 작성
     * 2023. 10. 19.        parkjunhong77@gmail.com         파라미터 전달 버그 수정.
     * </pre>
     *
     * @param clmnName
     * @param clmnNameType
     *            {@code NOT Null}
     * @param defaultClmnName
     *            {@code NOT Empty}
     * 
     * @return
     *
     * @since 2022. 11. 25.
     * @version 2.0.0
     */
    public static String getColumnName(String clmnName, ColumnNameType clmnNameType, String defaultClmnName) {
        AssertUtils2.notNulls(clmnName, clmnNameType, defaultClmnName);

        return getColumnName(clmnName, clmnNameType, () -> defaultClmnName);
    }

    /**
     * 컬럼이름을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 11. 24.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param clmnName
     *            설정된 컬럼명
     * @param clmnNameType
     *            컬럼이름 타입.
     * @param defaultClmnName
     *            설정된 컬럼명({clmnName})이 빈 문자열일 경우 컬럼명 제공 함수.<br>
     *            반환하는 값은 반드시
     *            <font color="red">(<b>{@code NOT nullable}</b>)</font>
     * @return
     *
     * @since 2022. 11. 24.
     * @version 2.0.0
     */
    // 아래 내용에 적용됨.
    // - Supplier.get()
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    private static String getColumnName(String clmnName, ColumnNameType clmnNameType,
            Supplier<String> defaultClmnName) {
        // 설정된 컬럼명이 빈 문자열이 경우 처리
        if (StringUtils.isNullOrEmptyString(clmnName)) {
            clmnName = defaultClmnName.get();
            return switch (clmnNameType) {
                case CAMEL_CASE -> StringUtils.toLowerCase(clmnName, 0);
                case NAME -> clmnName;
                case PASCAL_CASE -> StringUtils.toPascalCase(clmnName);
                case KEBAB_CASE -> StringUtils.toKebabCase(clmnName);
                case KEBAB_CASE_NUM -> StringUtils.toKebabCaseNum(clmnName);
                case SNAKE_CASE -> StringUtils.toSnakeCase(clmnName);
                case SNAKE_CASE_NUM -> StringUtils.toSnakeCaseNum(clmnName);
            };
        } else {
            return clmnName;
        }
    }

    /**
     * 주어진 {@link Method}에 {@link ColumnDef} 어노테이션이 설정된 경우, 컬럼이름을 제공합니다. <br>
     * 
     * @param method
     * 
     * @return 컬럼 이름 또는 {@code null}
     */
    public static @Nullable String getColumnNameByColumnDef(Method method) {
        Objects.requireNonNull(method);

        ColumnDef anno = method.getAnnotation(ColumnDef.class);
        return anno != null ? getColumnName(anno, method) : null;
    }

    /**
     * 주어진 {@link Method}에 {@link ColumnValue} 어노테이션이 설정된 경우, 컬럼이름을 제공합니다. <br>
     * 
     * @param method
     * 
     * @return 컬럼 이름 또는 {@code null}
     */
    public static @Nullable String getColumnNameByColumnValue(Method method) {
        Objects.requireNonNull(method);

        ColumnValue anno = method.getAnnotation(ColumnValue.class);
        return anno != null ? getColumnName(anno, method) : null;
    }

    /**
     * 주어진 DB Table Entity의 컬럼명 정렬 여부를 제공합니다. <br>
     * 컬럼명을 정렬하는 경우, 반드시 컬럼에 대응하는 메소드에 {@link ColumnValue#order()} 를 설정해야 합니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 1. 7.		parkjunhong77@gmail.com			최초 작성
     * 2022. 3. 30.     parkjunhong77@gmail.com         Entity 클래스애 {@link TableDef}가 설정되지 않은 경우 무조건 정렬하도록 수정
     * </pre>
     *
     * @param entityType
     * 
     * @return
     *
     * @since 2022. 1. 7.
     * @version 1.8.0
     */
    public static boolean isSortedColumns(Class<?> entityType) {
        TableDef anno = AnnotationUtils.getAnnotation(entityType, TableDef.class);
        return anno != null //
                ? anno.sortedColumns() //
                : true;
    }

    /**
     * Oracle에서 한글을 3바이트로 처리하기 때문에, 그에 맞는 문자열 길이를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 11. 9.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param str
     * @return
     *
     * @since 2020. 11. 9.
     */
    public static int lengthOnOracle(String str) {
        int len = 0;

        for (char c : str.toCharArray()) {
            len += (CharUtils.isKorean(c) ? 3 : 1);
        }
        return len;
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2017. 9. 5.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param objectType
     *            생성할 데이터 타입. 파라미터를 받지 않는 기본 생성자가 반드시 있어야 합니다.
     * @param rs
     *            {@link ResultSet} 객체
     * @param columns
     *            불러올 컬럼 목록. 없는 경우 생성하는 객체에 정의되어 있는 모든 컬럼 정보를 읽어온다.
     * 
     * @return
     * 
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws SQLException
     *
     * @since 2017. 9. 5.
     */
    public static <T> T newInstance(Class<T> objectType, ResultSet rs, final String... columns) throws SQLException {
        return ResultSetTransformer.newInstance(objectType, rs, columns);
    }

    /**
     * {@link PreparedStatement}에 주어진 객체에 포함된 데이터 중에서 전달받은(@param columnNames)
     * 컬럼에 해당하는 값을 설정합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 12. 22.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param stmt
     * @param index
     * @param obj
     * @param columnNames
     * @return
     * @throws SQLException
     *
     * @since 2020. 12. 22.
     * @version 1.8.0
     */
    // 아래 내용에 적용됨.
    // - Function.apply(...)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static int setParameters(PreparedStatement stmt, int index, Object obj, String @Nullable... columnNames)
            throws SQLException {
        Objects.requireNonNull(stmt);
        Objects.requireNonNull(obj);

        // #1. @ColumnValue 어노테이션이 설정된 Method 조회
        Class<?> type = obj.getClass();
        List<Method> methods = COLUMN_VALUE_METHOD_PROVIDER.apply(type);

        // #2. 사용자 지정 컬럼 여부에 따른 Method 필터링
        if (columnNames == null || columnNames.length < 1) {
            // DB Entity 객체의 컬럼 정렬 여부 적용 - 2022. 1. 7. 오전 11:43:25 /
            // Park_Jun_Hong (jhpark@ymtech.co.kr)
            sortColumns(type, methods);
        } else {
            // 메소드별 컬럼명 대/소문자 비교 여부
            final Map<String, Boolean> clmnCaseSensitive = new HashMap<>();
            // 컬럼이름/메소드
            final Map<@NonNull String, Method> methodMap = CollectionUtils.toMapHSV(methods //
                    , m -> {
                        String clmn = Objects.requireNonNull(getColumnName(Objects.requireNonNull(m)));
                        clmnCaseSensitive.put(clmn,
                                Objects.requireNonNull(m.getAnnotation(ColumnValue.class)).caseSensitive());

                        return clmn;
                    } //
                    , m -> m);

            methods = new ArrayList<>();

            for (String clmn : columnNames) {
                for (Entry<String, Method> entry : methodMap.entrySet()) {
                    if (COLUMN_CHECKER.apply(clmn, entry.getKey(), clmnCaseSensitive.get(entry.getKey()))) {
                        methods.add(entry.getValue());
                        break;
                    }
                }
                throw new IllegalArgumentException(String.format("지원하지 않는 컬럼이름입니다. 컬럼명: %s, 메소드: %s", clmn, methodMap));
            }
        }

        // #3. 필터링 된 Method를 이용하여 파라미터 값 설정
        for (Method m : methods) {
            try {
                stmt.setObject(++index, m.invoke(obj));
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                throw ExceptionUtils.newException(SQLException.class, e, "파라미터 설정 도중  에러가 발생하였습니다. 원인: %s",
                        e.getMessage());
            }
        }
        return index;
    }

    /**
     * Primitive Type 에 해당하는 데이터를 설정합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 1. 27.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param stmt
     * @param index
     *            Variable Binding Index
     * @param value
     *            설정값.
     * @throws SQLException
     *
     * @since 2019. 1. 27.
     */
    public static void setValueOrNull(PreparedStatement stmt, int index, @Nullable Boolean value) throws SQLException {
        Objects.requireNonNull(stmt);

        if (value != null) {
            stmt.setBoolean(index, value);
        } else {
            stmt.setObject(index, null);
        }
    }

    /**
     * Primitive Type 에 해당하는 데이터를 설정합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 1. 27.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param stmt
     * @param index
     *            Variable Binding Index
     * @param value
     *            설정값.
     * @throws SQLException
     *
     * @since 2019. 1. 27.
     */
    public static void setValueOrNull(PreparedStatement stmt, int index, @Nullable Double value) throws SQLException {
        Objects.requireNonNull(stmt);

        if (value != null) {
            stmt.setDouble(index, value);
        } else {
            stmt.setObject(index, null);
        }
    }

    /**
     * Primitive Type 에 해당하는 데이터를 설정합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 1. 27.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param stmt
     * @param index
     *            Variable Binding Index
     * @param value
     *            설정값.
     * @throws SQLException
     *
     * @since 2019. 1. 27.
     */
    public static void setValueOrNull(PreparedStatement stmt, int index, @Nullable Float value) throws SQLException {
        Objects.requireNonNull(stmt);

        if (value != null) {
            stmt.setFloat(index, value);
        } else {
            stmt.setObject(index, null);
        }
    }

    /**
     * Primitive Type 에 해당하는 데이터를 설정합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 1. 27.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param stmt
     * @param index
     *            Variable Binding Index
     * @param value
     *            설정값.
     * @throws SQLException
     *
     * @since 2019. 1. 27.
     */
    public static void setValueOrNull(PreparedStatement stmt, int index, @Nullable Integer value) throws SQLException {
        Objects.requireNonNull(stmt);

        if (value != null) {
            stmt.setInt(index, value);
        } else {
            stmt.setObject(index, null);
        }
    }

    /**
     * Primitive Type 에 해당하는 데이터를 설정합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 1. 27.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param stmt
     * @param index
     *            Variable Binding Index
     * @param value
     *            설정값.
     * @throws SQLException
     *
     * @since 2019. 1. 27.
     */
    public static void setValueOrNull(PreparedStatement stmt, int index, @Nullable Long value) throws SQLException {
        Objects.requireNonNull(stmt);

        if (value != null) {
            stmt.setLong(index, value);
        } else {
            stmt.setObject(index, null);
        }
    }

    /**
     * DB Table entity의 컬럼을 정렬합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 1. 7.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param entityType
     *            DB Table Entity 타입
     * @param columnBindingMethods
     *            DB Table Column과 연결된 {@link Method}
     *
     * @since 2022. 1. 7.
     * @version 1.8.0
     */
    public static void sortColumns(Class<?> entityType, List<Method> columnBindingMethods) {

        Function<Method, Integer> keyExtractor = null;

        if (isSortedColumns(entityType)) {
            // ColumnValue#order() 값에 대한 오름차순 정렬 적용
            keyExtractor = m -> m.getAnnotation(ColumnValue.class).order();
        } else {
            // 컬럼이름의 hash값을 이용한 임의 정렬
            keyExtractor = m -> m.getAnnotation(ColumnValue.class).name().hashCode();
        }

        columnBindingMethods.sort(Comparator.comparing(keyExtractor));
    }

}
