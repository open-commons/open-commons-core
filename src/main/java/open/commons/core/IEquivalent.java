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
* Date  : 2012. 03. 20. 오후 7:15:58
*
* Author: Park Jun-Hong (parkjunhong77@gmail.com)
* 
*/
package open.commons.core;

/**
 * 같은 타입의 2개의 객체가 동일한지를 판단하는 메소드를 제공하는 인터페이스 <BR>
 * 
 * @since 2012. 03. 20.
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
@FunctionalInterface
public interface IEquivalent<T> {

    public boolean equals(T t1, T t2);

}
