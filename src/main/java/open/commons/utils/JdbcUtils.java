/*
 * Copyright 2019 Park Jun-Hong (parkjunhong77@gmail.com)
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
 * Date  : 2019. 1. 29. 오전 11:24:10
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.utils;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 
 * @since 2019. 1. 27.
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 * @deprecated Use {@link SQLUtils} instead of, since 2019. 02. 18. open-commons-core-1.6.3.
 * @see SQLUtils
 */
public class JdbcUtils {

    /**
     * 
     * @since 2019. 1. 27.
     */
    private JdbcUtils() {
    }

    /**
     * Primitive Type 에 해당하는 데이터를 설정한다. <br>
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
     * 
     */
    public static void setValueOrNull(PreparedStatement stmt, int index, Boolean value) throws SQLException {

        if (value != null) {
            stmt.setBoolean(index, value);
        } else {
            stmt.setObject(index, null);
        }
    }

    /**
     * Primitive Type 에 해당하는 데이터를 설정한다. <br>
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
     * Primitive Type 에 해당하는 데이터를 설정한다. <br>
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
     * Primitive Type 에 해당하는 데이터를 설정한다. <br>
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
     * Primitive Type 에 해당하는 데이터를 설정한다. <br>
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

}
