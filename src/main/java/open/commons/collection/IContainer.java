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
* Date  : 2012. 7. 30. 오후 2:50:32
*
* Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
*
* File  : IContainer.java 
* 
*/
package open.commons.collection;

/**
 * 이 인터페이스의 목적은 동일한 타입의 객체간 포함관계에 대한 확인 기능을 지원하는 것이다.<br>
 * 
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 * @param <T>
 */
public interface IContainer<T> {
    /**
     * Return whether or not a <b><code>contained</code></b> is belonged to a <b><code>container</code></b>.
     * 
     * @param container
     * @param contained
     * @return
     */
    public boolean contains(T container, T contained);
}
