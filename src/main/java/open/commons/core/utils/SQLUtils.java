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

import java.io.ByteArrayInputStream;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public class SQLUtils {

    public static final Pattern METHOD_BOOLEAN_PATTERN = Pattern.compile("^(is|get)(.+)$");
    public static final Pattern METHOD_PATTERN = Pattern.compile("^(get)(.+)$");
    public static final Pattern METHOD_SETTER_PATTERN = Pattern.compile("^(set)(.+)$");

    /**
     * 2개의 문자열을 대/소문자 비교여부에 따라서 비교
     * 
     * @param s1
     *            비교할 문자열
     * @param s2
     *            비교할 문자열
     * @param cs
     *            대/소문자 비교 여부
     * 
     * @return 문자여 비교 결과
     */
    public static final TripleFunction<String, String, Boolean, Boolean> COLUMN_CHECKER = (s1, s2, cs) -> {
        if (cs) {
            return s1.equals(s2);
        } else {
            return s1.equalsIgnoreCase(s2);
        }
    };

    /**
     * {@link Method} 이름을 패턴 비교하여 컬럼명을 추출하여 제공합니다.
     * 
     * @param ptn
     *            {@link Method} 이름 비교 {@link Pattern}
     * @param str
     *            메소드 이름
     * 
     * @return 패턴과 매칭되지 않는 경우 <code>null</code>을 반환합니다.
     */
    public static final BiFunction<Pattern, String, String> METHOD_MATCHER = (ptn, str) -> {
        Matcher m = ptn.matcher(str);
        if (m.matches()) {
            return StringUtils.toLowerCase(m.group(2), 0);
        } else {
            return null;
        }
    };

    public static final Function<Class<?>, List<Method>> COLUMN_VALUE_METHOD_PROVIDER = typeClass -> {
        return Arrays.stream(typeClass.getMethods()) // create methods stream
                .filter(m -> {
                    ColumnValue annoCv = m.getAnnotation(ColumnValue.class);
                    // start - 컬럼 생성시 정의된 'default' 속성에 따라서 생성되는 컬럼을 제외 : 2022. 11. 1. 오후 2:28:00
                    return annoCv != null && !annoCv.defaultColumn();
                    // end - 컬럼 생성시 정의된 'default' 속성에 따라서 생성되는 컬럼을 제외 : 2022. 11. 1. 오후 2:28:00

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
     * 2020. 11. 9.		박준홍			최초 작성
     * </pre>
     *
     * @param obj
     * @return Table Column 순서에 따른 검증결과. <br>
     *         예) 00000000000000000120: 18: 길이 오류, 19th: null 오류
     *
     * @since 2020. 11. 9.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @see ColumnConstraint
     */
    public static char[] checkColumnConstraints(Object obj) {

        TreeMap<Integer, Character> validations = new TreeMap<>();

        AnnotationUtils.getAnnotatedMethodsAllAsStream(obj.getClass(), ColumnConstraint.class) //
                // 어노테이션 확인
                .sorted((o1, o2) -> {
                    ColumnConstraint cc1 = o1.getAnnotation(ColumnConstraint.class);
                    ColumnConstraint cc2 = o2.getAnnotation(ColumnConstraint.class);
                    return cc1.index() - cc2.index();
                })
                // 메소드 정렬
                .collect(Collectors.toList())
                // 데이터 검증
                .stream() //
                .forEach(m -> {
                    ColumnConstraint anno = m.getAnnotation(ColumnConstraint.class);
                    int index = anno.index();
                    char chekced = '0';
                    try {
                        Object value = m.invoke(obj);
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
                        throw ExceptionUtils.newException(RuntimeException.class, e, "데이터 검증 중 에러가 발생하였습니다. data=%s, method=%s", obj, m);
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
     * 2019. 6. 17.		박준홍			최초 작성
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @see ColumnDecl
     */
    public static <T> Map<String, TwoValueObject<Object, Object>> findDifferences(T obj1, T obj2, String... columns) throws RuntimeException {
        AssertUtils.assertNulls(obj1, obj2);

        Class<?> dataType = obj1.getClass();
        // #0. @ColumnDecl 어노테이션이 있는 메소드 조회
        Collection<Method> methods = ReflectionUtils.getAnnotatedMethods(ColumnDecl.class, dataType);

        Map<String, TwoValueObject<Object, Object>> diff = new HashMap<>();

        methods.stream() //
                .forEach(m -> {
                    Object v1 = null;
                    Object v2 = null;
                    ColumnDecl anno = null;

                    try {
                        v1 = m.invoke(obj1);
                        v2 = m.invoke(obj2);
                    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }

                    anno = m.getAnnotation(ColumnDecl.class);

                    if (v1 == null && v2 == null) {
                        return;
                    }

                    if (v1 == null // v1 is only null
                            || v2 == null // v2 is only null
                            || !v1.equals(v2) // v1 & v2 are not null and not equal
                    ) {

                        String annoValue = anno.value().trim();
                        String annoColumn = anno.column().trim();

                        if (StringUtils.isNullOrEmptyStringAnd(annoValue, annoColumn)) {
                            throw new IllegalArgumentException(String.format("컬럼명은 빈문자열이 올 수 없습니다. column=%s, value=%s", annoColumn, annoValue));
                        }

                        String column = annoValue.isEmpty() ? annoColumn : annoValue;

                        diff.put(column, new TwoValueObject<Object, Object>(v1, v2));
                    }
                });

        return diff;
    }

    /**
     * 메소드에 설정된 {@link ColumnValue}에의 컬럼명을 제공합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 09. 24.		박준홍     최초 작성
     * 2021. 11. 26.        박준홍     메소드로 별도 분리.
     * </pre>
     *
     * @param method
     * @return
     * @throws NullPointerException
     *             메소드에 {@link ColumnValue}가 설정되지 않은 경우.
     *
     * @since 2020. 09. 24.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see ColumnValue
     * @see ColumnValue#name()
     */
    public static final String getColumnName(Method method) throws NullPointerException {
        ColumnValue cv = method.getAnnotation(ColumnValue.class);
        // 설정된 컬럼명이 빈 문자열이 경우 처리
        return getColumnName(cv.name(), cv.columnNameType(), () -> {

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
                throw new IllegalArgumentException(String.format("해당 데이터에 대한 컬럼명이 설정되지 않았습니다. 설정: %s, 메소드: %s", cv, method));
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
     * 2022. 11. 25.		박준홍			최초 작성
     * </pre>
     *
     * @param clmnName
     * @param clmnNameType
     *            <code>NOT Null</code>
     * @param defaultClmnName
     *            <code>NOT Empty</code>
     * @return
     *
     * @since 2022. 11. 25.
     * @version 2.0.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static String getColumnName(String clmnName, ColumnNameType clmnNameType, String defaultClmnName) {
        return getColumnName(defaultClmnName, clmnNameType, () -> defaultClmnName);
    }

    public static String getColumnName(ColumnValue clmnValue, Method method) {
        return getColumnName(clmnValue.name(), clmnValue.columnNameType(), () -> METHOD_MATCHER.apply(METHOD_PATTERN, method.getName()));
    }

    public static String getColumnName(ColumnDef clmnDef, Method method) {
        return getColumnName(clmnDef.name(), clmnDef.columnNameType(), () -> METHOD_MATCHER.apply(METHOD_SETTER_PATTERN, method.getName()));
    }

    public static String getColumnNameByColumnValue(Method method) {
        return getColumnName(method.getAnnotation(ColumnValue.class), method);
    }

    public static String getColumnNameByColumnDef(Method method) {
        return getColumnName(method.getAnnotation(ColumnDef.class), method);
    }

    /**
     * 컬럼이름을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 11. 24.		박준홍			최초 작성
     * </pre>
     *
     * @param clmnName
     *            설정된 컬럼명
     * @param clmnNameType
     *            컬럼이름 타입.
     * @param defaultClmnName
     *            설정된 컬럼명({clmnName})이 빈 문자열일 경우 컬럼명 제공 함수.
     * @return
     *
     * @since 2022. 11. 24.
     * @version 2.0.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    private static String getColumnName(String clmnName, ColumnNameType clmnNameType, Supplier<String> defaultClmnName) {
        // 설정된 컬럼명이 빈 문자열이 경우 처리
        if (clmnName.isEmpty()) {
            clmnName = defaultClmnName.get();
            switch (clmnNameType) {
                case CAMEL_CASE:
                    return StringUtils.toLowerCase(clmnName, 0);
                case PASCAL_CASE:
                    return StringUtils.toPascalCase(clmnName);
                case SNAKE_CASE:
                    return StringUtils.toSnakeCase(clmnName);
                case KEBAB_CASE:
                    return StringUtils.toKebabCase(clmnName);
                case NAME:
                    // 그대로 사용
                    return clmnName;
                default:
                    throw new IllegalArgumentException(String.format("지원하지 않는 컬럼명 타입입니다. 지원: %s, 입력: %s", Arrays.toString(ColumnNameType.values()), clmnNameType));
            }
        } else {
            return clmnName;
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
     * 2018. 5. 30.		박준홍			최초 작성
     * </pre>
     *
     * @param rs
     * @param cdef
     *            컬럼 정의
     * @param m
     *            Method 객체
     * @param object
     *            데이터 객체
     * @throws SQLException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2018. 5. 30.
     */
    private static void invoke(ResultSet rs, ColumnDef cdef, Method m, Object object)
            throws SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        Class<?> columnType = cdef.type();
        if (Class.class.equals(columnType)) {
            columnType = m.getParameterTypes()[0];
        }

        // 컬럼명 설정
        String columnName = getColumnName(cdef.name(), cdef.columnNameType(), () -> METHOD_MATCHER.apply(METHOD_SETTER_PATTERN, m.getName()));

        Object v = null;

        if (Array.class.isAssignableFrom(columnType)) {
            v = rs.getArray(columnName);
        } //
        else if (ByteArrayInputStream.class.isAssignableFrom(columnType)) {
            v = rs.getAsciiStream(columnName);
        } //
        else if (BigDecimal.class.isAssignableFrom(columnType)) {
            v = rs.getBigDecimal(columnName);
        } //
        else if (ByteArrayInputStream.class.isAssignableFrom(columnType)) {
            v = rs.getBinaryStream(columnName);
        } //
        else if (Blob.class.isAssignableFrom(columnType)) {
            v = rs.getBlob(columnName);
        } //
        else if (boolean.class.isAssignableFrom(columnType)) {
            v = rs.getBoolean(columnName);
        } //
        else if (Boolean.class.isAssignableFrom(columnType)) {
            v = rs.getBoolean(columnName);
        } //
        else if (Byte.class.isAssignableFrom(columnType)) {
            v = rs.getByte(columnName);
        } //
        else if (byte[].class.isAssignableFrom(columnType)) {
            v = rs.getBytes(columnName);
        } //
        else if (Reader.class.isAssignableFrom(columnType)) {
            v = rs.getCharacterStream(columnName);
        } //
        else if (Clob.class.isAssignableFrom(columnType)) {
            v = rs.getClob(columnName);
        } //
        else if (Date.class.isAssignableFrom(columnType)) {
            v = rs.getDate(columnName);
        } //
        else if (double.class.isAssignableFrom(columnType)) {
            v = rs.getDouble(columnName);
        } //
        else if (Double.class.isAssignableFrom(columnType)) {
            BigDecimal d = rs.getBigDecimal(columnName);
            v = d != null ? d.doubleValue() : null;
        } //
        else if (float.class.isAssignableFrom(columnType)) {
            v = rs.getFloat(columnName);
        } //
        else if (Float.class.isAssignableFrom(columnType)) {
            BigDecimal d = rs.getBigDecimal(columnName);
            v = d != null ? d.floatValue() : null;
        } //
        else if (int.class.isAssignableFrom(columnType)) {
            v = rs.getInt(columnName);
        } //
        else if (Integer.class.isAssignableFrom(columnType)) {
            BigDecimal d = rs.getBigDecimal(columnName);
            v = d != null ? d.intValue() : null;
        } //
        else if (long.class.isAssignableFrom(columnType)) {
            v = rs.getLong(columnName);
        } //
        else if (Long.class.isAssignableFrom(columnType)) {
            BigDecimal d = rs.getBigDecimal(columnName);
            v = d != null ? d.longValue() : null;
        } //
        else if (NClob.class.isAssignableFrom(columnType)) {
            v = rs.getNClob(columnName);
        } //
        else if (short.class.isAssignableFrom(columnType)) {
            v = rs.getShort(columnName);
        } //
        else if (Short.class.isAssignableFrom(columnType)) {
            BigDecimal d = rs.getBigDecimal(columnName);
            v = d != null ? d.shortValue() : null;
        } //
        else if (String.class.isAssignableFrom(columnType)) {
            v = rs.getString(columnName);
        } //
        else if (Time.class.isAssignableFrom(columnType)) {
            v = rs.getTime(columnName);
        } //
        else if (Timestamp.class.isAssignableFrom(columnType)) {
            v = rs.getTimestamp(columnName);
        } //
        else if (URL.class.isAssignableFrom(columnType)) {
            v = rs.getURL(columnName);
        } //
        else if (Object.class.isAssignableFrom(columnType)) {
            v = rs.getObject(columnName);
        } else {
            throw new SQLException("지원하지 않는 데이터 타입입니다.");
        }

        if (cdef.nullable() && v == null) {
            return;
        }
        m.invoke(object, v);
    }

    /**
     * 주어진 DB Table Entity의 컬럼명 정렬 여부를 제공합니다. <br>
     * 컬럼명을 정렬하는 경우, 반드시 컬럼에 대응하는 메소드에 {@link ColumnValue#order()} 를 설정해야 합니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 1. 7.		박준홍			최초 작성
     * 2022. 3. 30.     박준홍         Entity 클래스애 {@link TableDef}가 설정되지 않은 경우 무조건 정렬하도록 수정
     * </pre>
     *
     * @param entityType
     * @return
     *
     * @since 2022. 1. 7.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
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
     * 2020. 11. 9.		박준홍			최초 작성
     * </pre>
     *
     * @param str
     * @return
     *
     * @since 2020. 11. 9.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
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
     * 2017. 9. 5.      박준홍         최초 작성
     * </pre>
     *
     * @param objectType
     *            생성할 데이터 타입. 파라미터를 받지 않는 기본 생성자가 반드시 있어야 합니다.
     * 
     * @param rs
     *            {@link ResultSet} 객체
     * 
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2017. 9. 5.
     */
    @SuppressWarnings("unchecked")
    public static <T> T newInstance(Class<T> objectType, ResultSet rs, final String... columns) throws SQLException {

        boolean isTagged = columns != null && columns.length > 0;

        List<String> caseSensitiveColumnsList = isTagged ? Arrays.asList(columns) : new ArrayList<>();

        List<Method> methods = Arrays.asList(objectType.getMethods()) //
                .stream() //
                .filter(m -> {
                    boolean filtered = false;
                    boolean accessible = m.isAccessible();
                    try {
                        ColumnDef def = m.getAnnotation(ColumnDef.class);
                        String clmnName = getColumnName(def.name(), def.columnNameType(), () -> {
                            return METHOD_MATCHER.apply(METHOD_SETTER_PATTERN, m.getName());
                        });
                        filtered = def != null // 컬럼 정의 어노테이션이 있는지
                                && (isTagged ? caseSensitiveColumnsList.contains(clmnName) : true);
                    } catch (Throwable t) {
                        System.err.println("objectType: " + objectType + ", rs: " + rs + ", columns: " + columns == null ? null : Arrays.toString(columns));
                        t.printStackTrace();
                    } finally {
                        m.setAccessible(accessible);
                    }

                    return filtered;
                }).collect(Collectors.toList());

        Object object = null;
        ColumnDef cdef = null;
        Object value = null;

        try {
            object = objectType.newInstance();

            if (methods.size() < 1) {
                return (T) object;
            }

            for (Method m : methods) {

                cdef = m.getAnnotation(ColumnDef.class);

                if (cdef.required()) {
                    invoke(rs, cdef, m, object);
                } else {
                    try {
                        invoke(rs, cdef, m, object);
                    } catch (SQLException ignored) {
                    }
                }
            }

            return (T) object;
        } catch (InstantiationException //
                | IllegalAccessException //
                | IllegalArgumentException //
                | InvocationTargetException //
                | SQLException e) {
            throw ExceptionUtils.newException(SQLException.class, e, "class: %s, annotation: %s, value: %s", objectType, cdef, value);
        }
    }

    /**
     * {@link PreparedStatement}에 주어진 객체에 포함된 데이터 중에서 전달받은(@param columnNames) 컬럼에 해당하는 값을 설정합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 12. 22.		박준홍			최초 작성
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static int setParameters(PreparedStatement stmt, int index, Object obj, String... columnNames) throws SQLException {
        // #1. @ColumnValue 어노테이션이 설정된 Method 조회
        Class<?> type = obj.getClass();
        List<Method> methods = COLUMN_VALUE_METHOD_PROVIDER.apply(type);

        // #2. 사용자 지정 컬럼 여부에 따른 Method 필터링
        if (columnNames == null || columnNames.length < 1) {
            // DB Entity 객체의 컬럼 정렬 여부 적용 - 2022. 1. 7. 오전 11:43:25 / Park_Jun_Hong (jhpark@ymtech.co.kr)
            sortColumns(type, methods);
        } else {
            // 메소드별 컬럼명 대/소문자 비교 여부
            final Map<String, Boolean> clmnCaseSensitive = new HashMap<>();
            // 컬럼이름/메소드
            final Map<String, Method> methodMap = CollectionUtils.toMapHSV(methods, m -> {

                String clmn = getColumnName(m);
                clmnCaseSensitive.put(clmn, m.getAnnotation(ColumnValue.class).caseSensitive());

                return clmn;
            }, m -> m);

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
                throw ExceptionUtils.newException(SQLException.class, e, "파라미터 설정 도중  에러가 발생하였습니다. 원인: %s", e.getMessage());
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
     * 2019. 1. 27.     박준홍         최초 작성
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
    public static void setValueOrNull(PreparedStatement stmt, int index, Boolean value) throws SQLException {

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
     * 2019. 1. 27.     박준홍         최초 작성
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
    public static void setValueOrNull(PreparedStatement stmt, int index, Double value) throws SQLException {

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
     * 2019. 1. 27.     박준홍         최초 작성
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
    public static void setValueOrNull(PreparedStatement stmt, int index, Float value) throws SQLException {

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
     * 2019. 1. 27.     박준홍         최초 작성
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
    public static void setValueOrNull(PreparedStatement stmt, int index, Integer value) throws SQLException {

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
     * 2019. 1. 27.     박준홍         최초 작성
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
    public static void setValueOrNull(PreparedStatement stmt, int index, Long value) throws SQLException {

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
     * 2022. 1. 7.		박준홍			최초 작성
     * </pre>
     *
     * @param entityType
     *            DB Table Entity 타입
     * @param columnBindingMethods
     *            DB Table Column과 연결된 {@link Method}
     *
     * @since 2022. 1. 7.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
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
