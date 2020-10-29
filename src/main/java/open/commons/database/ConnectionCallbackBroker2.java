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
 * Date  : 2019. 2. 19. 오전 10:41:11
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package open.commons.database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.function.Function;

/**
 * {@link Function}을 지원하기 위해서 추가된 클래스.
 * 
 * @param <T>
 *            {@link PreparedStatement} 에 데이터를 설정하는 객체 타입.
 * @since 2019. 2. 19.
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 * @see ConnectionCallbackBroker
 */
public abstract class ConnectionCallbackBroker2<T> implements IConnectionCallbackBroker {

    private final String query;

    private T setter;

    /**
     * 실행 쿼리가 Stored Procedure를 실행하는지 여부<br>
     * 기본설정은 {@link PreparedStatement} 를 기반으로 동작하지만, <code>true</code> 인 경우 {@link CallableStatement} 를 기반으로 동작한다.
     */
    private boolean forStoredProcedure;

    /**
     * 
     * @param query
     * @since 2019. 2. 22.
     */
    public ConnectionCallbackBroker2(String query) {
        this(query, null, false);
    }

    /**
     * 
     * @param query
     *            SQL 쿼리
     * @param setter
     *            {@link PreparedStatement}에 쿼리 파라미터를 설정하는 객체
     * @since 2019. 2. 19.
     */
    public ConnectionCallbackBroker2(String query, T setter) {
        this(query, setter, false);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 29.		박준홍			최초 작성
     * </pre>
     *
     * @param query
     *            SQL 쿼리
     * @param setter
     *            {@link PreparedStatement}에 쿼리 파라미터를 설정하는 객체
     * @param forStoredProcedure
     *            실행 쿼리가 Stored Procedure를 실행하는지 여부
     * @since 2020. 10. 29.
     */
    public ConnectionCallbackBroker2(String query, T setter, boolean forStoredProcedure) {
        this.query = query;
        this.setter = setter;
        this.forStoredProcedure = forStoredProcedure;
    }

    /**
     * @see open.commons.database.IConnectionCallbackBroker#getQuery()
     */
    @Override
    public String getQuery() {
        return query;
    }

    /**
     *
     * @return the setter
     *
     * @since 2019. 2. 22.
     */
    public T getSetter() {
        return setter;
    }

    /**
     * @throws SQLException
     * @since 2020. 10. 29.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @see open.commons.database.IConnectionCallbackBroker#getStatement(java.sql.Connection)
     */
    @Override
    public final PreparedStatement getStatement(Connection con) throws SQLException {
        return forStoredProcedure ? con.prepareCall(getQuery()) : con.prepareStatement(getQuery());
    }

    /**
     * @see open.commons.database.IConnectionCallbackBroker#set(PreparedStatement)
     */
    @Override
    public void set(PreparedStatement stmt) throws SQLException {
        if (this.setter != null) {
            set(stmt, setter);
        }
    }

    /**
     * {@link PreparedStatement}에 SQL 쿼리 파라미터를 설정한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 2. 19.		박준홍			최초 작성
     * </pre>
     *
     * @param stmt
     * @param setter
     *
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @since 2019. 2. 19.
     */
    protected abstract void set(PreparedStatement stmt, T setter) throws SQLException;

    /**
     * @param setter
     *            the setter to set
     *
     * @since 2019. 2. 22.
     */
    public void setSetter(T setter) {
        this.setter = setter;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ConnectionCallbackBroker2 [query=");
        builder.append(query);
        builder.append(", setter=");
        builder.append(setter);
        builder.append("]");
        return builder.toString();
    }
}
