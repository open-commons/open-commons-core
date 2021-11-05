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
* Date  : 2013. 7. 12. 오후 5:02:24
*
* Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
* 
*/

/**
 * 
 */
package open.commons.database.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ColumnConf {
    /** alias name of a native table column name */
    String alias() default "";

    /** native table column name */
    String column();

    /** user-defined column index */
    int index() default -1;

    /** whether the native column data type is referenced or not */
    boolean refer();
}
