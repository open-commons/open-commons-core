/*
 * Copyright 2011 Park Jun-Hong (parkjunhong77/google/com)
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
* This file is generated under this project, "open-commons-core".
*
* Date  : 2013. 7. 12. 오후 5:03:09
*
* Author: Park_Jun_Hong_(fafanmama_at_naver_com)
* 
*/

/**
 * 
 */
package open.commons.database;

import java.lang.reflect.Field;
import java.util.Collection;

/**
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */
public interface ITableEntity extends Comparable<ITableEntity> {

    /**
     * 컬럼 개수를 반환한다.
     * 
     * @return 컬럼 개수
     */
    public int count();

    /**
     * <b><b><code>insert</code></b> 쿼리를 생성한다.
     * 
     * @return
     */
    public String createInsertQuery();

    /**
     * <b><b><code>insert</code></b> 쿼리를 생성한다.
     * 
     * @param table
     * 
     * @return
     */
    public String createInsertQuery(String table);

    /**
     * <b><code>select</code></b> 쿼리를 생성한다.
     * 
     * @param selects
     *            가져올 컬럼명
     * @param where
     *            <b><code>where</code></b> clause에 사용될 컬럼.
     * @return
     */
    public String createSelectQuery(Collection<Integer> selects, int... where);

    /**
     * <b><code>select</code></b> 쿼리를 생성한다.
     * 
     * @param where
     *            <b><code>where</code></b> clause에 사용될 컬럼
     * 
     * @return
     */
    public String createSelectQuery(int... where);

    /**
     * <b><code>select</code></b> 쿼리를 생성한다.
     * 
     * @param table
     * @param selects
     *            가져올 컬럼명
     * @param where
     *            <b><code>where</code></b> clause에 사용될 컬럼.
     * @return
     */
    public String createSelectQuery(String table, Collection<Integer> selects, int... where);

    /**
     * <b><code>select</code></b> 쿼리를 생성한다.
     * 
     * @param table
     * @param where
     *            <b><code>where</code></b> clause에 사용될 컬럼
     * 
     * @return
     */
    public String createSelectQuery(String table, int... where);

    /**
     * 
     * @param updates
     *            갱신될 컬럼
     * @param where
     *            <b><code>where</code></b> clause에 사용될 컬럼
     * 
     * @return
     */
    public String createUpdateQuery(Collection<Integer> updates, int... where);

    /**
     * <b><code>update</code></b> 쿼리를 생성한다.
     * 
     * @param where
     *            <b><code>where</code></b>clause에 사용될 컬럼
     * 
     * @return
     */
    public String createUpdateQuery(int... where);

    /**
     * <b><code>Update</code></b> 쿼리를 생성한다.
     * 
     * @param table
     * 
     * @param updates
     *            갱신될 컬럼
     * @param where
     *            <b><code>where</code></b> clause에 사용될 컬럼
     * 
     * @return
     */
    public String createUpdateQuery(String table, Collection<Integer> updates, int... where);

    /**
     * <b><b><code>update</code></b> 쿼리를 생성한다.
     * 
     * @param table
     * @param where
     *            <b><code>where</code></b>clause에 사용될 컬럼
     * 
     * @return
     */
    public String createUpdateQuery(String table, int... where);

    /**
     * 주어진 <b><code>index</code></b>에 해당하는 {@link Field}에 값을 설정한다.
     * 
     * @param index
     * @param value
     * @return
     */
    public boolean setValue(int index, Object value);

    /**
     * 컬럼 데이터를 문자열 배열로 반환한다.
     * 
     * @return
     */
    public String[] toParameters();
}
