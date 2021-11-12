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
 * Date  : 2019. 2. 19. 오전 10:42:09
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * SQL 쿼리에 파라미터를 설정하는 역할을 정의한 인터페이스.
 * 
 * 
 * 
 * <br>
 * 
 * <pre>
 * [개정이력]
 *      날짜    	| 작성자	|	내용
 * ------------------------------------------
 * 2019. 2. 19.		박준홍        최초 작성
 * </pre>
 * 
 * @param <T>
 * @since 2019. 2. 19.
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public interface IConnectionCallbackBroker {

    /**
     * SQL 쿼리를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 2. 19.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2019. 2. 19.
     */
    String getQuery();

    /**
     * DBMS 연결정보와 내부 쿼리 정보를 이용하여 {@link PreparedStatement} 를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 29.		박준홍			최초 작성
     * </pre>
     *
     * @param <T>
     * @param con
     * @return
     * @throws SQLException
     *             TODO
     *
     * @since 2020. 10. 29.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public PreparedStatement getStatement(Connection con) throws SQLException;

    /**
     * {@link PreparedStatement}에 SQL 쿼리 파라미터를 설정합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 2. 19.		박준홍			최초 작성
     * </pre>
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @param stmt
     * @since 2019. 2. 19.
     */
    void set(PreparedStatement stmt) throws SQLException;
}
