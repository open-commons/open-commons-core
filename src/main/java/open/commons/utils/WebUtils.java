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

package open.commons.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 * @since 2012. 1. 4.
 * 
 */
public class WebUtils {

    /**
     * 
     * HTTP URL 문자열에서 파라미터에 해당하는 부분의 내용을 맵으로 전환해서 반환한다.<br>
     * 
     * @param urlLocation
     *            HTTP URL에서 파라미터에 해당하는 부분
     * @return
     * 
     *         <BR>
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * @since 2012. 1. 4.
     */
    public static Map<String, String> getParams(String urlLocation) {
        String[] url_param = urlLocation.split("[?]");

        urlLocation = url_param.length > 1 ? url_param[1] : urlLocation;

        Map<String, String> paramMap = new ConcurrentHashMap<String, String>();

        String[] paramArr = urlLocation.split("[&]");
        for (String param : paramArr) {
            String[] kv = param.split("=");
            if (kv.length > 0) {
                paramMap.put(kv[0], (kv.length > 1 ? kv[1] : ""));
            }
        }

        return paramMap;
    }
}
