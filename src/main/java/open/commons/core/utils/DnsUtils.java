/*
 * Copyright 2022 Park Jun-Hong_(parkjunhong77@gmail.com)
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
 * Date  : 2022. 2. 11. 오후 2:11:38
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package open.commons.core.utils;

import java.util.regex.Pattern;

/**
 * DNS(Domain Name System) 관련 기능을 제공하는 클래스.
 * 
 * @since 2022. 2. 11.
 * @version 1.8.0
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
public class DnsUtils {

    /**
     * '도메인 이름' 정규식. <br>
     * Reference: <a href=
     * "https://mkyong.com/regular-expressions/domain-name-regular-expression-example/">https://mkyong.com/regular-expressions/domain-name-regular-expression-example/</a>
     */
    public static final String REGEX_DOMAIN_NAME = "((?!-)[_A-Za-z0-9-]{1,63}(?<!-)\\.)+[A-Za-z]{2,6}";

    /**
     * '도메인 이름'만을 위한 정규식. <br>
     * Reference: <a href=
     * "https://mkyong.com/regular-expressions/domain-name-regular-expression-example/">https://mkyong.com/regular-expressions/domain-name-regular-expression-example/</a>
     */
    public static final String REGEX_DOMAIN_NAME_STRICT = "^" + REGEX_DOMAIN_NAME + "$";

    private static final Pattern PATTERN_DOMAIN_NAME = Pattern.compile(REGEX_DOMAIN_NAME_STRICT);

    /**
     * 주어진 문자열이 '도메인 이름' 규칙에 맞는지 여부를 제공합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 2. 11.		박준홍			최초 작성
     * </pre>
     *
     * @param domainName
     *            도메인 이름
     * @return
     *
     * @since 2022. 2. 11.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static boolean isValid(String domainName) {
        if (domainName == null) {
            return false;
        }

        return PATTERN_DOMAIN_NAME.matcher(domainName).matches();
    }

}
