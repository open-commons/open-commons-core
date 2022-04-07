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
*/
package open.commons.core;

import java.io.IOException;
import java.util.Map.Entry;
import java.util.Properties;

/**
 * 공통 프로퍼티 제공 클래스 <BR>
 * 
 * @since 2012. 01. 30.
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
public class CommonProperties {

    private static final Properties COMMON_PROPS = new Properties();

    static {
        try {
            COMMON_PROPS.load(CommonProperties.class.getResourceAsStream("/properties/common_properties.properties"));
        } catch (IOException ignored) {
            System.err.println("There is no 'common_properties.properties' file.");
        } catch (Throwable throwable) {
            System.err.println("Unexpectible Case");
        }
    }

    /**
     * 주어진 키값에 해당하는 프로퍼티 설정값을 반환합니다.
     * 
     * @param key
     * @return <BR>
     * @since 2012. 01. 30.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see Properties#get(Object)
     */
    public static String getProperty(String key) {
        return COMMON_PROPS.getProperty(key);
    }

    /**
     * 
     * @param keyPrefix
     * @return <BR>
     * @since 2012. 01. 30.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static Properties subProperteis(String keyPrefix) {
        Properties prop = new Properties();

        String key = null;
        for (Entry<Object, Object> entry : COMMON_PROPS.entrySet()) {
            key = (String) entry.getKey();

            if (key.startsWith(keyPrefix)) {
                prop.put(key, entry.getValue());
            }
        }
        return prop;
    }
}
