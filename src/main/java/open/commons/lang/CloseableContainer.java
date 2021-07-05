/*
 * Copyright 2021 Park Jun-Hong_(parkjunhong77/google/com)
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
 * Date  : 2021. 7. 1. 오후 2:11:35
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package open.commons.lang;

import java.lang.reflect.Field;
import java.util.HashSet;

import javax.annotation.Resource;

import open.commons.utils.IOUtils;

/**
 * {@link Resource}와 함께 정의된 {@link AutoCloseable} Instance Field 들을 자동으로 해제({@link AutoCloseable#close()})하는 기능을 제공.
 * 
 * @since 2021. 7. 1.
 * @version 1.8.0
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
public interface CloseableContainer extends AutoCloseable {

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 7. 1.		박준홍			최초 작성
     * </pre>
     *
     * @throws Exception
     *
     * @since 2021. 7. 1.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see java.lang.AutoCloseable#close()
     */
    @Override
    default void close() throws Exception {
        
        HashSet<AutoCloseable> resources = new HashSet<>();

        Field[] fields = getClass().getDeclaredFields();

        Class<?> fieldClass = null;
        boolean accessible = false;
        for (Field field : fields) {
            fieldClass = field.getType();
            if (fieldClass.isAnnotationPresent(Resource.class) //
                    && AutoCloseable.class.isAssignableFrom(fieldClass)) {
                accessible = field.isAccessible();
                try {
                    field.setAccessible(true);
                    resources.add((AutoCloseable) field.get(this));
                } catch (Exception e) {
                    throw new RuntimeException("Fail to initialize a " + Resource.class.getName() + " instance.", e);
                } finally {
                    field.setAccessible(accessible);
                }
            }
        }

        // Release Resources.
        IOUtils.close(resources.toArray(new AutoCloseable[0]));
    }
}
