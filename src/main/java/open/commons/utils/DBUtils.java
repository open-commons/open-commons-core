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
*/
package open.commons.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import open.commons.CommonProperties;

/**
 * Oracle, MSSQL, MySQL DB 연결 유틸 클래스
 * 
 * <BR>
 * 
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 * @since 2012. 01. 16.
 * 
 */
@SuppressWarnings("unused")
public class DBUtils {

    private static final Map<String, String> DB_PROPS = new ConcurrentHashMap<String, String>();

    private static final String DEFAULT_HEADER = "$$";

    private static final String JDBC_MSSQL = "db.jdbc.mssql";
    private static final String JDBC_MSSQL_URL_PREFIX = "jdbc:sqlserver://";

    private static final String JDBC_MYSQL = "db.jdbc.mysql";
    private static final String JDBC_MYSQL_URL_PREFIX = "jdbc:mysql://";

    private static final String JDBC_ORACLE = "db.jdbc.oracle";
    private static final String JDBC_ORACLE_URL_PREFIX = "jdbc:oracle:thin:@";

    private static final String QRY_TMPLT_INSERT = "INSERT INTO %s ( %s ) VALUES ( %s ) %s";
    private static final String QRY_TMPLT_UPDATE = "UPDATE %s SET %s %s";

    static {
        DB_PROPS.put(JDBC_MSSQL, CommonProperties.getProperty(JDBC_MSSQL));
        DB_PROPS.put(JDBC_MYSQL, CommonProperties.getProperty(JDBC_MYSQL));
        DB_PROPS.put(JDBC_ORACLE, CommonProperties.getProperty(JDBC_ORACLE));

        DB_PROPS.put(DEFAULT_HEADER + JDBC_MSSQL, JDBC_MSSQL_URL_PREFIX);
        DB_PROPS.put(DEFAULT_HEADER + JDBC_MYSQL, JDBC_MYSQL_URL_PREFIX);
        DB_PROPS.put(DEFAULT_HEADER + JDBC_ORACLE, JDBC_ORACLE_URL_PREFIX);
    }

    /**
     * 주어진 정보를 가지고 DB 연결 객체를 생성해서 반환합니다.
     * 
     * @param driver
     *            DB 접속 드라이버 제공 클래스 전체 경로
     * @param url
     *            DB 접속 URL
     * 
     * @return 주어진 정보로 생성된 {@link Connection} 객체
     * 
     *         <BR>
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * @throws SQLException
     * @throws ClassNotFoundException
     * @since 2012. 01. 16.
     * 
     * @see Class#forName(String)
     * @see DriverManager#getConnection(String)
     */
    public static Connection getConnection(String driver, String url) throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        return DriverManager.getConnection(url);
    }

    /**
     * 주어진 정보를 가지고 DB 연결 객체를 생성해서 반환합니다.
     * 
     * @param driver
     *            DB 접속 드라이버 제공 클래스 전체 경로
     * @param url
     *            DB 접속 URL
     * @param info
     *            DB 접속 정보
     * 
     * @return 주어진 정보로 생성된 {@link Connection} 객체
     * 
     *         <BR>
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * @throws SQLException
     * @throws ClassNotFoundException
     * @since 2012. 01. 16.
     * 
     * @see Class#forName(String)
     * @see DriverManager#getConnection(String, Properties)
     */
    public static Connection getConnection(String driver, String url, Properties info) throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        return DriverManager.getConnection(url, info);
    }

    /**
     * 주어진 정보를 가지고 DB 연결 객체를 생성해서 반환합니다.
     * 
     * @param driver
     *            DB 접속 드라이버 제공 클래스 전체 경로
     * @param url
     *            DB 접속 URL
     * @param user
     *            사용자 ID
     * @param password
     *            사용자 비밀번호
     * @return 주어진 정보로 생성된 {@link Connection} 객체
     * 
     *         <BR>
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * @throws SQLException
     * @throws ClassNotFoundException
     * @since 2012. 01. 16.
     * 
     * @see Class#forName(String)
     * @see DriverManager#getConnection(String, String, String)
     */
    public static Connection getConnection(String driver, String url, String user, String password) throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        return DriverManager.getConnection(url, user, password);
    }

    /**
     * MSSQL DB 접속 객체를 반환합니다.
     * 
     * 
     * @param driver
     *            DB 접속 드라이버 제공 클래스 전체 경로
     * @param ip
     *            DB 접속 IP
     * @param port
     *            DB 접속 포트
     * @param schema
     *            DB 접속 후 연결할 스키마
     * @param user
     *            사용자 ID
     * @param password
     *            사용자 비밀번호
     * @return 주어진 정보로 생성된 {@link Connection} 객체
     * 
     *         <BR>
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * @throws SQLException
     * @throws ClassNotFoundException
     * @since 2012. 01. 16.
     * 
     * @see #getConnection(String, String, String, String)
     */
    public static Connection getMSSQLConnection(String driver, String ip, String port, String schema, String user, String password) throws ClassNotFoundException, SQLException {
        return getConnection(driver, getUrlPrefix(JDBC_MSSQL) + ip + ":" + port + ";DatabaseName=" + schema + ";", user, password);
    }

    /**
     * MySql DB 접속 객체를 반환합니다.
     * 
     * 
     * @param driver
     *            DB 접속 드라이버 제공 클래스 전체 경로
     * @param ip
     *            DB 접속 IP
     * @param port
     *            DB 접속 포트
     * @param schema
     *            DB 접속 후 연결할 스키마
     * @param user
     *            사용자 ID
     * @param password
     *            사용자 비밀번호
     * @return 주어진 정보로 생성된 {@link Connection} 객체
     * 
     *         <BR>
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * @throws SQLException
     * @throws ClassNotFoundException
     * @since 2012. 01. 16.
     * 
     * @see #getConnection(String, String, String, String)
     */
    public static Connection getMySqlConnection(String driver, String ip, String port, String schema, String user, String password) throws ClassNotFoundException, SQLException {
        return getConnection(driver, getUrlPrefix(JDBC_MYSQL) + ip + ":" + port + "/" + schema, user, password);
    }

    /**
     * ORACLE DB 접속 객체를 반환합니다.
     * 
     * 
     * @param driver
     *            DB 접속 드라이버 제공 클래스 전체 경로
     * @param ip
     *            DB 접속 IP
     * @param port
     *            DB 접속 포트
     * @param schema
     *            DB 접속 후 연결할 스키마
     * @param user
     *            사용자 ID
     * @param password
     *            사용자 비밀번호
     * @return 주어진 정보로 생성된 {@link Connection} 객체
     * 
     *         <BR>
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * @throws SQLException
     * @throws ClassNotFoundException
     * @since 2012. 01. 16.
     * 
     * @see #getConnection(String, String, String, String)
     */
    public static Connection getOracleConnection(String driver, String ip, String port, String schema, String user, String password) throws ClassNotFoundException, SQLException {
        return getConnection(driver, getUrlPrefix(JDBC_ORACLE) + ip + ":" + port + ":" + schema, user, password);
    }

    private static String getUrlPrefix(String key) {
        String prefix = DB_PROPS.get(key);

        return prefix != null ? prefix : DB_PROPS.get(DEFAULT_HEADER + key);
    }

    /**
     * Nested 되지 않은 insert 쿼리 문자열을 생성해서 반환합니다.
     * <p>
     * <b>주의: binaray 형태의 데이타를 처리하지는 않는다.</b>
     * 
     * @param table
     *            테이블 명
     * @param data
     *            데이타
     * @return insert 쿼리
     * @exception NullPointerException
     *                테이블 명이나 데이타가 <code>null</code>인 경우
     * 
     *                <BR>
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * @since 2012. 01. 17.
     */
    public static String insertQuery(String table, Map<String, ?> data) {
        return insertQuery(table, data, null);
    }

    /**
     * Nested 되지 않은 insert 쿼리 문자열을 생성해서 반환합니다.
     * <p>
     * <b>주의: binaray 형태의 데이타를 처리하지는 않는다.</b>
     * 
     * @param table
     *            테이블 명
     * @param data
     *            데이타
     * @param etc
     *            where clause 및 그 이하 ...
     * @return insert 쿼리
     * 
     * @exception NullPointerException
     *                테이블 명이나 데이타가 <code>null</code>인 경우
     * 
     *                <BR>
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * @since 2012. 01. 17.
     */
    public static String insertQuery(String table, Map<String, ?> data, String etc) {
        Set<String> colSet = data.keySet();
        Collection<?> valueCol = data.values();

        String cols = toColumns(colSet);
        String values = toValues(valueCol);

        String etcString = StringUtils.toString(etc, "");

        return String.format(QRY_TMPLT_INSERT, table, cols, values, etcString);
    }

    /**
     * 주어진 데이터로 이루어진 컬럼명 문자열을 생성해서 반환합니다.
     * 
     * @param col
     * @return <BR>
     * @since 2012. 01. 30.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static String toColumns(Collection<String> col) {
        if (col.isEmpty()) {
            return "";
        } else {
            StringBuffer sb = new StringBuffer();
            Iterator<String> itr = col.iterator();

            Object object = itr.next();
            sb.append(object);

            while (itr.hasNext()) {
                object = itr.next();

                sb.append(", " + object);
            }

            return sb.toString();
        }
    }

    /**
     * 주어진 데이터로이루어진 데이타 값 문자열을 반환합니다.
     * <p>
     * <b>주의: binary 형태의 데이터를 처리하지는 않는다.</b>
     * 
     * @param col
     * @return <BR>
     * @since 2012. 01. 30.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static String toValues(Collection<?> col) {
        if (col.isEmpty()) {
            return "";
        } else {
            StringBuffer sb = new StringBuffer();
            Iterator<?> itr = col.iterator();

            Object object = itr.next();
            if (object.getClass().equals(String.class) || object.getClass().equals(Character.class) || object.getClass().equals(char.class)) {
                sb.append("'" + object + "'");
            } else {
                sb.append(object);
            }

            while (itr.hasNext()) {
                object = itr.next();

                if (object.getClass().equals(String.class) || object.getClass().equals(Character.class) || object.getClass().equals(char.class)) {
                    sb.append(", '" + object + "'");
                } else {
                    sb.append(", " + object);
                }
            }

            return sb.toString();
        }
    }
}
