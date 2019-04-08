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
* Date  : 2014. 9. 26. 오후 1:39:53
*
* Author: Park_Jun_Hong_(fafanmama_at_naver_com)
* 
*/

package open.commons.utils;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import open.commons.annotation.Hide;
import open.commons.reflect.IAccessibleObjectAction;

public class DataUtils {

    public static void hide(Collection<?> objects) throws IllegalArgumentException, IllegalAccessException {
        for (Object object : objects) {
            hide(object);
        }
    }

    public static void hide(final Object object) throws IllegalArgumentException, IllegalAccessException {
        if (object == null) {
            return;
        }

        IAccessibleObjectAction<Field> action = new IAccessibleObjectAction<Field>() {
            @Override
            public Object act(Object instance, Iterator<Field> itr) {
                boolean accessible = false;
                Field field = null;
                try {
                    while (itr.hasNext()) {
                        field = itr.next();
                        accessible = field.isAccessible();

                        if (field.getAnnotation(Hide.class) == null) {
                            continue;
                        }

                        ReflectionUtils.resetFieldForced(object, field);

                    }
                } catch (Exception e) {

                } finally {
                    field.setAccessible(accessible);
                }

                return Void.TYPE;
            }
        };

        resolve(object.getClass(), object, action);

    }

    @SuppressWarnings("unchecked")
    private static <T extends AccessibleObject> Object resolve(Class<?> _class_, Object instance, IAccessibleObjectAction<T> action)
            throws IllegalArgumentException, IllegalAccessException {

        Class<?> superClass = _class_.getSuperclass();
        if (superClass != null && !superClass.isInterface() && !Object.class.equals(superClass)) {
            resolve(superClass, instance, action);
        }

        return action.act(instance, (Iterator<T>) Arrays.asList(_class_.getDeclaredFields()).iterator());
    }

}
