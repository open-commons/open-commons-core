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
 * This file is generated under this project, "open-commons-core".
 *
 * Date  : 2017. 12. 29. 오후 5:18:49
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.database;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @since 2017. 12. 29.
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public interface IRowMapperSetter<T> {

    /**
     * ResultSet을 이용하여 객체를 생성해서 제공한다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2017. 12. 29.		박준홍			최초 작성
     * </pre>
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2017. 12. 29.
     */
    public T set(ResultSet rs, int rowNum) throws SQLException;
}
