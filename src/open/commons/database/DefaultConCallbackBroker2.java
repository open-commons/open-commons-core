/*
 * Copyright 2019 Park Jun-Hong_(fafanmama_at_naver_com)
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
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package open.commons.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import open.commons.function.SQLConsumer;

/**
 * 
 * @since 2019. 2. 22.
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
public class DefaultConCallbackBroker2 extends ConnectionCallbackBroker2<SQLConsumer<PreparedStatement>> {

    /**
     * @param query
     * @since 2019. 2. 22.
     */
    public DefaultConCallbackBroker2(String query) {
        super(query);
    }

    /**
     * 
     * @param query
     * @param setter
     * @since 2019. 2. 22.
     */
    public DefaultConCallbackBroker2(String query, SQLConsumer<PreparedStatement> setter) {
        super(query, setter);
    }

    /**
     * @see open.commons.database.ConnectionCallbackBroker2#set(java.sql.PreparedStatement, java.lang.Object)
     */
    @Override
    protected void set(PreparedStatement stmt, SQLConsumer<PreparedStatement> setter) throws SQLException {
        setter.accept(stmt);
    }

    /**
     * @see java.lang.Object#toString()
     */
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
