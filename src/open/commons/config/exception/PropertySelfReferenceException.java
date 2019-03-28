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
* Date  : 2012. 02. 15. 오후 2:57:52
*
* Author: Park Jun-Hong (fafanmama_at_naver_dot_com)
* 
*/
package open.commons.config.exception;

/**
 * @since 2012. 02. 15.
 * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
 */
public class PropertySelfReferenceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PropertySelfReferenceException() {
        super();
    }

    public PropertySelfReferenceException(String msg) {
        super(msg);
    }

    public PropertySelfReferenceException(String msg, Throwable t) {
        super(msg, t);
    }

    public PropertySelfReferenceException(Throwable t) {
        super(t);
    }
}