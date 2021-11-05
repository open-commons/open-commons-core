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
* 
* This file is generated under this project, "open-commons-core".
*
* Date  : 2013. 11. 8. 오후 1:22:40
*
* Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
* 
*/
package open.commons.database;

/**
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */
public interface IQueryParameter {

    /**
     * 컬럼 개수를 반환한다.
     * 
     * @return 컬럼 개수
     */
    public int count();

    /**
     * 컬럼 데이터를 문자열 배열로 반환한다.
     * 
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * 
     * @since 2014. 7. 17.
     */
    public String[] toParameters() throws IllegalArgumentException, IllegalAccessException;

}
