/*
 * Copyright 2019 Park Jun-Hong_(fafanmama_at_naver_com)
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
 * Date  : 2019. 2. 19. 오전 11:53:15
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package open.commons.lang;

import java.lang.reflect.Field;
import java.util.HashSet;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 자원을 자동으로 해제하는 클래스.
 * 
 * @since 2019. 2. 19.
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
public class AbstractCloseable implements AutoCloseable {

    protected Logger logger = LogManager.getLogger(getClass());

    /**
     * 
     * @since 2019. 2. 19.
     */
    public AbstractCloseable() {
    }

    /**
     * @see java.lang.AutoCloseable#close()
     */
    @Override
    public void close() throws Exception {

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
                    logger.error("Fail to initialize a " + Resource.class.getName() + " instance.", e);
                    throw new RuntimeException("Fail to initialize a " + Resource.class.getName() + " instance.", e);
                } finally {
                    field.setAccessible(accessible);
                }
            }
        }

        // Release Resources.
        try {
            for (AutoCloseable c : resources) {
                c.close();
            }
        } catch (Exception ignored) {
            logger.warn(ignored);
        }
    }
}
