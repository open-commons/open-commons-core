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
 *
 * This file is generated under this project, "open-commons-core".
 *
 * Date  : 2017. 12. 29. 오후 5:20:34
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package open.commons.database;

/**
 * 
 * @since 2017. 12. 29.
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
public class ConnectionCallbackBroker {

    private final String query;

    private final IConnectionCallbackSetter setter;

    /**
     * 
     * @param query
     * @param setter
     * @since 2017. 12. 13.
     */
    public ConnectionCallbackBroker(String query, IConnectionCallbackSetter setter) {
        this.query = query;
        this.setter = setter;
    }

    /**
     *
     * @return the query
     *
     * @auth Park_Jun_Hong_(fafanmama_at_naver_com)
     * @since 2017. 12. 13.
     */
    public String getQuery() {
        return query;
    }

    /**
     *
     * @return the setter
     *
     * @auth Park_Jun_Hong_(fafanmama_at_naver_com)
     * @since 2017. 12. 13.
     */
    public IConnectionCallbackSetter getSetter() {
        return setter;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ConnectionCallbackBroker [query=" + query + ", setter=" + setter + "]";
    }
}
