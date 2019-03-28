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
* 
* This file is generated under this project, "open-commons-core".
*
* Date  : 2013. 11. 8. 오후 1:23:13
*
* Author: Park_Jun_Hong_(fafanmama_at_naver_com)
* 
*/
package open.commons.database;

import java.lang.reflect.Field;

import open.commons.database.annotation.AQueryIndex;
import open.commons.utils.AnnotationUtils;

/**
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */
public abstract class DefaultQueryParameter implements IQueryParameter {

    /**
     * 
     * @see open.commons.database.IQueryParameter#toParameters()
     */
    @Override
    public String[] toParameters() throws IllegalArgumentException, IllegalAccessException {

        Class<?> clazz = getClass();

        Field[] fields = clazz.getDeclaredFields();
        AQueryIndex qi = null;

        String[] params = new String[count()];

        boolean accessible = false;
        for (Field field : fields) {
            accessible = field.isAccessible();

            field.setAccessible(true);
            qi = AnnotationUtils.getAnnotation(field, AQueryIndex.class);

            if (qi != null) {
                params[qi.index()] = field.get(this).toString();
            }

            field.setAccessible(accessible);
        }

        return params;
    }

}
