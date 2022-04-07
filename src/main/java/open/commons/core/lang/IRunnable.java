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
* This file is generated under this project, "open-commons-core".
*
* Date  : 2013. 6. 20. 오전 11:47:28
*
* Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
* 
*/

/**
 * 
 */
package open.commons.core.lang;

/**
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */
public interface IRunnable extends Runnable, IControllable {
    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 10. 10.		박준홍			최초 작성
     * </pre>
     *
     * @param daemon
     *            데몬형태 구동 여부
     *
     * @since 2019. 10. 10.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @version 1.6.16
     */
    public void start(boolean daemon);
}
