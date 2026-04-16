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
 * Date  : 2019. 2. 22. 오후 2:18:57
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.core.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.jspecify.annotations.Nullable;

import open.commons.core.function.SQLConsumer;
import open.commons.core.utils.AssertUtils2;

/**
 * 
 * @since 2019. 2. 22.
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
public class DefaultConCallbackBroker2 extends ConnectionCallbackBroker2<SQLConsumer<PreparedStatement>> {

    /**
     * @param query
     * 
     * @since 2019. 2. 22.
     */
    public DefaultConCallbackBroker2(String query) {
        this(query, null, false);
    }

    /**
     * 
     * @param query
     * @param setter
     * 
     * @since 2019. 2. 22.
     */
    public DefaultConCallbackBroker2(String query, @Nullable SQLConsumer<PreparedStatement> setter) {
        this(query, setter, false);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 10. 29.        parkjunhong77@gmail.com         최초 작성
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
    public DefaultConCallbackBroker2(String query, @Nullable SQLConsumer<PreparedStatement> setter, boolean forStoredProcedure) {
        super(query, setter, forStoredProcedure);
    }

    /**
     * @see open.commons.core.database.ConnectionCallbackBroker2#set(java.sql.PreparedStatement, java.lang.Object)
     */
    @Override
    protected void set(PreparedStatement stmt, SQLConsumer<PreparedStatement> setter) throws SQLException {
        AssertUtils2.notNulls(stmt, setter);

        setter.accept(stmt);
    }

    /**
     * @see java.lang.Object#toString()
     */
    // 아래 내용에 적용됨.
    // - StringBuilder.toString()
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("DefaultConCallbackBroker2 [query=");
        builder.append(getQuery());
        builder.append(", setter=");
        builder.append(getSetter());
        builder.append("]");
        return builder.toString();
    }
}
