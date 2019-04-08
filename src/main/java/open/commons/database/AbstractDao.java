/*
 * Copyright 2018 Park Jun-Hong (parkjunhong77/google/com)
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
 * Date  : 2018. 5. 23. 오전 11:22:28
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package open.commons.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLNonTransientConnectionException;
import java.sql.SQLSyntaxErrorException;
import java.sql.SQLTransactionRollbackException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import open.commons.function.SQLBiFunction;
import open.commons.utils.IOUtils;
import open.commons.utils.SQLUtils;

/**
 * JDBC Connector를 이용하여 SQL을 직접 적용하여 DBMS와 연동하는 클래스.
 * 
 * @since 2018. 5. 23.
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
public abstract class AbstractDao implements AutoCloseable {

    private final ConcurrentSkipListMap<String, SQLBiFunction<ResultSet, Integer, ?>> CREATORS = new ConcurrentSkipListMap<>();

    protected Logger logger = LogManager.getLogger(getClass());

    private final String driver;
    private final String url;
    private final String username;

    private final String password;

    /** DBMS 접속 정보 */
    private Connection con;

    private final boolean autocommit;

    private int retryMaxCount = 3;

    /**
     * 
     * @param autocommit
     * 
     * @since 2018. 5. 23.
     */
    public AbstractDao(String driver, String url, String username, String password, boolean autocommit) {

        this.driver = driver;
        this.url = url;
        this.username = username;
        this.password = password;
        this.autocommit = autocommit;

        try {
            Class.forName(this.driver);

            connect();
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private void checkConnection() {
        int retry = 0;
        while (retry < retryMaxCount) {
            try {
                if (this.con == null || this.con.isClosed()) {
                    connect();
                }
                break;
            } catch (Throwable e) {

                retry++;

                logger.warn(toLog("[DBMS 재전송 시도]", "접속정보: ", this.url, ", 시도횟수: ", retry));
            }
        }
    }

    /**
     * @throws SQLException
     * @see java.lang.AutoCloseable#close()
     */
    @Override
    public void close() throws SQLException {
        try {
            if (this.con != null && !this.con.isClosed()) {
                this.con.close();
            }
        } catch (SQLException ignored) {

            logger.error(ignored);

            throw ignored;
        }
    }

    public final void commit() throws SQLException {
        try {
            con.commit();
        } catch (SQLException e) {
            logger.error("'commit' 처리 중 에러가 발생하였습니다.", e);
            throw e;
        }
    }

    private void connect() throws SQLException {

        this.con = DriverManager.getConnection(url, username, password);

        this.con.setAutoCommit(autocommit);
    }

    /**
     * 데이터를 제공한다. <br>
     * 
     * <b>명령어</b> - select
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 5. 23.     박준홍         최초 작성
     * </pre>
     *
     * @param broker
     * @param entity
     *            DB에서 읽어올 데이터 모델
     * @param colums
     *            Entity 클래스에서 읽어올 컬럼들. 설정되지 않는 경우 모든 컬럼 정보를 조회.
     * @return
     * @throws SQLException
     *
     * @since 2018. 5. 23.
     */
    @SuppressWarnings("unchecked")
    public final <R> List<R> executeQuery(ConnectionCallbackBroker broker, Class<R> entity, String... colums) throws SQLException {

        SQLBiFunction<ResultSet, Integer, R> creator = (SQLBiFunction<ResultSet, Integer, R>) CREATORS.get(entity.getName());

        if (creator == null) {
            creator = (rs, rowNum) -> {
                return SQLUtils.newInstance(entity, rs, colums);
            };
            CREATORS.put(entity.getName(), creator);
        }

        return executeQuery(broker, creator);
    }

    /**
     * 데이터를 제공한다. <br>
     * 
     * <b>명령어</b> - select
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 5. 23.     박준홍         최초 작성
     * </pre>
     *
     * @param broker
     * @param creator
     * @return
     * @throws SQLException
     *
     * @since 2018. 5. 23.
     */
    public final <R> List<R> executeQuery(ConnectionCallbackBroker broker, SQLBiFunction<ResultSet, Integer, R> creator) throws SQLException {

        ArrayList<R> result = null;

        PreparedStatement stmt = null;

        try {

            String query = broker.getQuery();

            if (logger.isTraceEnabled()) {
                logger.trace("query: " + query);
            }

            checkConnection();

            stmt = con.prepareStatement(query);

            IConnectionCallbackSetter setter = broker.getSetter();

            if (setter != null) {
                setter.set(stmt);
            }

            ResultSet rs = stmt.executeQuery();

            result = new ArrayList<>();

            int i = 1;
            while (rs.next()) {
                result.add(creator.apply(rs, i++));
            }

        } catch (SQLSyntaxErrorException e) {
            logger.error("broker: " + broker, e);
            throw e;
        } catch (SQLException e) {
            logger.error("broker: " + broker, e);
            throw e;
        } finally {
            IOUtils.close(stmt);
        }

        return result;
    }

    /**
     * 데이터를 제공한다. <br>
     * 
     * <b>명령어</b> - select
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 2. 19.		박준홍			최초 작성
     * </pre>
     *
     * @param broker
     * @param entity
     * @param colums
     * @return
     * @throws SQLException
     *
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @since 2019. 2. 19.
     */
    @SuppressWarnings("unchecked")
    public final <R, T> List<R> executeQuery(ConnectionCallbackBroker2<T> broker, Class<R> entity, String... colums) throws SQLException {

        SQLBiFunction<ResultSet, Integer, R> creator = (SQLBiFunction<ResultSet, Integer, R>) CREATORS.get(entity.getName());

        if (creator == null) {
            creator = (rs, rowNum) -> {
                return SQLUtils.newInstance(entity, rs, colums);
            };
            CREATORS.put(entity.getName(), creator);
        }

        return executeQuery(broker, creator);
    }

    /**
     * 데이터를 제공한다. <br>
     * 
     * <b>명령어</b> - select
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 2. 19.     박준홍         최초 작성
     * </pre>
     *
     * @param broker
     * @param creator
     * @return
     * @throws SQLException
     *
     * @since 2019. 2. 19.
     */
    public final <R, T> List<R> executeQuery(ConnectionCallbackBroker2<T> broker, SQLBiFunction<ResultSet, Integer, R> creator) throws SQLException {

        ArrayList<R> result = null;

        PreparedStatement stmt = null;

        try {

            String query = broker.getQuery();

            if (logger.isTraceEnabled()) {
                logger.trace("query: " + query);
            }

            checkConnection();

            stmt = con.prepareStatement(query);

            broker.set(stmt);

            ResultSet rs = stmt.executeQuery();

            result = new ArrayList<>();

            int i = 1;
            while (rs.next()) {
                result.add(creator.apply(rs, i++));
            }

        } catch (SQLSyntaxErrorException e) {
            logger.error("broker: " + broker, e);
            throw e;
        } catch (SQLException e) {
            logger.error("broker: " + broker, e);
            throw e;
        } finally {
            IOUtils.close(stmt);
        }

        return result;
    }

    /**
     * 데이터를 추가/삭제/변경한다. <br>
     * 
     * <b>명령어</b> - insert, update, delete
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 5. 23.     박준홍         최초 작성
     * </pre>
     * 
     * @param broker
     * 
     * @return 데이터 개수. -1인 경우 에러 발생
     * 
     * @throws SQLException
     *
     * @since 2018. 5. 23.
     */
    public final int executeUpdate(ConnectionCallbackBroker broker) throws SQLException {

        int count = -1;

        PreparedStatement stmt = null;

        try {

            String query = broker.getQuery();

            if (logger.isTraceEnabled()) {
                logger.trace("query: " + query);
            }

            while (true) {

                checkConnection();

                try {
                    stmt = con.prepareStatement(query);

                    IConnectionCallbackSetter setter = broker.getSetter();

                    if (setter != null) {
                        setter.set(stmt);
                    }

                    count = stmt.executeUpdate();

                    break;
                } catch (SQLTransactionRollbackException | SQLNonTransientConnectionException e) {
                    if (logger.isWarnEnabled()) {
                        logger.warn(toLog("[Transaction Error]", e.getMessage()));
                    }
                }
            }

        } catch (SQLSyntaxErrorException e) {
            logger.error("broker: " + broker, e);
            throw e;
        } catch (SQLException e) {
            logger.error("broker: " + broker, e);
            throw e;
        } finally {
            IOUtils.close(stmt);
        }

        return count;
    }

    /**
     * 데이터를 추가/삭제/변경한다. <br>
     * 
     * <b>명령어</b> - insert, update, delete
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 2. 19.     박준홍         최초 작성
     * </pre>
     * 
     * @param broker
     * 
     * @return 데이터 개수. -1인 경우 에러 발생
     * 
     * @throws SQLException
     *
     * @since 2019. 2. 19.
     */
    public final <T> int executeUpdate(ConnectionCallbackBroker2<T> broker) throws SQLException {

        int count = -1;

        PreparedStatement stmt = null;

        try {

            String query = broker.getQuery();

            if (logger.isTraceEnabled()) {
                logger.trace("query: " + query);
            }

            while (true) {

                checkConnection();

                try {
                    stmt = con.prepareStatement(query);

                    broker.set(stmt);

                    count = stmt.executeUpdate();

                    break;
                } catch (SQLTransactionRollbackException | SQLNonTransientConnectionException e) {
                    if (logger.isWarnEnabled()) {
                        logger.warn(toLog("[Transaction Error]", e.getMessage()));
                    }
                }
            }

        } catch (SQLSyntaxErrorException e) {
            logger.error("broker: " + broker, e);
            throw e;
        } catch (SQLException e) {
            logger.error("broker: " + broker, e);
            throw e;
        } finally {
            IOUtils.close(stmt);
        }

        return count;
    }

    /**
     *
     * @return the driver
     *
     * @since 2018. 5. 23.
     * 
     * @see #driver
     */
    public String getDriver() {
        return driver;
    }

    /**
     *
     * @return the password
     *
     * @since 2018. 5. 23.
     * 
     * @see #password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2018. 5. 23.		박준홍			최초 작성
     * </pre>
     *
     * @param name
     * @return
     *
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @since 2018. 5. 23.
     */
    public abstract String getQuery(String name);

    /**
     *
     * @return 재접속 시도 횟수.
     *
     * @since 2019. 2. 18.
     */
    public int getRetryMaxCount() {
        return retryMaxCount;
    }

    /**
     *
     * @return the url
     *
     * @since 2018. 5. 23.
     * 
     * @see #url
     */
    public String getUrl() {
        return url;
    }

    /**
     *
     * @return the username
     *
     * @since 2018. 5. 23.
     * 
     * @see #username
     */
    public String getUsername() {
        return username;
    }

    public final void rollback() {
        try {
            if (this.con != null && !this.con.isClosed()) {
                this.con.rollback();
            }
        } catch (SQLException ignored) {
            logger.error(ignored);
        }
    }

    /**
     * 재접속 시도 횟수를 설정한다.
     * 
     * @param retryMaxCount
     *            the retryMaxCount to set
     *
     * @since 2019. 2. 18.
     */
    public void setRetryMaxCount(int retryMaxCount) {
        this.retryMaxCount = retryMaxCount;
    }

    protected static String toLog(String title, Object... messages) {

        StringBuffer buf = new StringBuffer(title);
        buf.append('\t');

        for (Object msg : messages) {
            buf.append(msg);
        }

        return buf.toString();
    }

}
