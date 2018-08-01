/*
 * Copyright 2011 Park Jun-Hong_(fafanmama_at_naver_com)
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
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package open.commons.utils;

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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import open.commons.annotation.ColumnDef;

/**
 * 
 * @since 2017. 9. 22.
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
public class SQLUtils {

    /**
     * 
     * @since 2017. 9. 22.
     */
    private SQLUtils() {
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
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @since 2018. 5. 30.
     */
    private static void invoke(ResultSet rs, ColumnDef cdef, Method m, Object object)
            throws SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        String columnName = cdef.name();
        Class<?> columnType = cdef.type();

        if (Array.class.isAssignableFrom(columnType)) {
            m.invoke(object, rs.getArray(columnName));
        } //
        else if (ByteArrayInputStream.class.isAssignableFrom(columnType)) {
            m.invoke(object, rs.getAsciiStream(columnName));
        } //
        else if (BigDecimal.class.isAssignableFrom(columnType)) {
            m.invoke(object, rs.getBigDecimal(columnName));
        } //
        else if (ByteArrayInputStream.class.isAssignableFrom(columnType)) {
            m.invoke(object, rs.getBinaryStream(columnName));
        } //
        else if (Blob.class.isAssignableFrom(columnType)) {
            m.invoke(object, rs.getBlob(columnName));
        } //
        else if (Boolean.class.isAssignableFrom(columnType)) {
            m.invoke(object, rs.getBoolean(columnName));
        } //
        else if (Byte.class.isAssignableFrom(columnType)) {
            m.invoke(object, rs.getByte(columnName));
        } //
        else if (byte[].class.isAssignableFrom(columnType)) {
            m.invoke(object, rs.getBytes(columnName));
        } //
        else if (Reader.class.isAssignableFrom(columnType)) {
            m.invoke(object, rs.getCharacterStream(columnName));
        } //
        else if (Clob.class.isAssignableFrom(columnType)) {
            m.invoke(object, rs.getClob(columnName));
        } //
        else if (Date.class.isAssignableFrom(columnType)) {
            m.invoke(object, rs.getAsciiStream(columnName));
        } //
        else if (double.class.isAssignableFrom(columnType)) {
            m.invoke(object, rs.getDouble(columnName));
        } //
        else if (Double.class.isAssignableFrom(columnType)) {
            m.invoke(object, rs.getDouble(columnName));
        } //
        else if (float.class.isAssignableFrom(columnType)) {
            m.invoke(object, rs.getFloat(columnName));
        } //
        else if (Float.class.isAssignableFrom(columnType)) {
            m.invoke(object, rs.getFloat(columnName));
        } //
        else if (int.class.isAssignableFrom(columnType)) {
            m.invoke(object, rs.getInt(columnName));
        } //
        else if (Integer.class.isAssignableFrom(columnType)) {
            m.invoke(object, rs.getInt(columnName));
        } //
        else if (long.class.isAssignableFrom(columnType)) {
            m.invoke(object, rs.getLong(columnName));
        } //
        else if (Long.class.isAssignableFrom(columnType)) {
            m.invoke(object, rs.getLong(columnName));
        } //
        else if (NClob.class.isAssignableFrom(columnType)) {
            m.invoke(object, rs.getNClob(columnName));
        } //
        else if (Object.class.isAssignableFrom(columnType)) {
            m.invoke(object, rs.getObject(columnName));
        } //
        else if (short.class.isAssignableFrom(columnType)) {
            m.invoke(object, rs.getShort(columnName));
        } //
        else if (Short.class.isAssignableFrom(columnType)) {
            m.invoke(object, rs.getShort(columnName));
        } //
        else if (String.class.isAssignableFrom(columnType)) {
            m.invoke(object, rs.getString(columnName));
        } //
        else if (Time.class.isAssignableFrom(columnType)) {
            m.invoke(object, rs.getTime(columnName));
        } //
        else if (Timestamp.class.isAssignableFrom(columnType)) {
            m.invoke(object, rs.getTime(columnName));
        } //
        else if (URL.class.isAssignableFrom(columnType)) {
            m.invoke(object, rs.getURL(columnName));
        } else {
            throw new SQLException("지원하지 않는 데이터 타입입니다.");
        }
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
     *            생성할 데이터 타입. 파라미터를 받지 않는 기본 생성자가 반드시 있어야 한다.
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
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
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

                        filtered = def != null // 컬럼 정의 어노테이션이 있는지
                                && (isTagged ? caseSensitiveColumnsList.contains(def.name()) : true);
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
                | InvocationTargetException e) {
            throw new SQLException("annotation: " + cdef + ", value: " + value, e);
        }
    }
}
