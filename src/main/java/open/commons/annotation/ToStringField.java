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
* Date  : 2012. 10. 29. 오전 1:09:25
*
* Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
*
*/
package open.commons.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * DAO 클래스 필드 중 ToString() 메소드에서 사용되는 필드를 지정.
 * 
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ToStringField {
    /**
     * 
     * @return
     */
    String name() default "";

    Class<? extends IToStringizer> stringizer() default open.commons.annotation.IToStringizer.NullToStringizer.class;
}
