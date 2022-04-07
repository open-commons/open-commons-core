/*
 * Copyright 2019 Park Jun-Hong_(parkjunhong77@gmail.com)
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
 * Date  : 2019. 11. 26. 오후 1:59:43
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.core.database;

/**
 * 
 * @since 2019. 11. 26.
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public class AbstractOracleInsertDao extends AbstractInsertDao {

    /**
     * @param driver
     * @param url
     * @param username
     * @param password
     * @param autocommit
     * @since 2019. 11. 26.
     */
    public AbstractOracleInsertDao(String driver, String url, String username, String password, boolean autocommit) {
        super(driver, url, username, password, autocommit);
    }

    /**
     * @see open.commons.core.database.AbstractInsertDao#createInsertAllQuery(java.lang.String, java.lang.String, int)
     */
    @Override
    protected String createInsertAllQuery(String insertAllQuery, String insertAllQueryValues, int dataCount) {

        if (dataCount < 1) {
            throw new IllegalArgumentException("The count of data MUST BE bigger than 0.");
        }

        StringBuffer queryBuf = new StringBuffer(insertAllQuery);

        queryBuf.append(' ');
        queryBuf.append(insertAllQueryValues);

        for (int i = 1; i < dataCount; i++) {
            queryBuf.append(" ");
            queryBuf.append(insertAllQueryValues);
        }

        queryBuf.append(" SELECT 1 FROM DUAL");

        return queryBuf.toString();
    }

    /**
     * @see open.commons.core.database.AbstractInsertDao#getMaxCountPerInsert()
     */
    @Override
    protected int getMaxCountPerInsert() {
        return 0;
    }

    /**
     * @see open.commons.core.database.AbstractDao#getQuery(java.lang.String)
     */
    @Override
    public String getQuery(String name) {
        return null;
    }

}
