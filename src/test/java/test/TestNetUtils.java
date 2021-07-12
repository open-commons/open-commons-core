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
 * Date  : 2021. 7. 12. 오후 7:06:36
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package test;

import open.commons.utils.NetUtils;

/**
 * 
 * @since 2021. 7. 12.
 * @version _._._
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
public class TestNetUtils {

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 7. 12.		박준홍			최초 작성
     * </pre>
     *
     *
     * @since 2021. 7. 12.
     * @version _._._
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public TestNetUtils() {
    }

    public static void main(String[] args) {
        String[] ipAddrs = { "127.0.0.1", "::1" };
        for (String ip : ipAddrs) {
            System.out.printf("%s is %s IP address.\n", ip, NetUtils.isIPv4OrIPv6(ip) ? "valid" + (NetUtils.isIPv4(ip) ? " (IPv4)" : " (IPv6)") : "invalid");
        }
    }
}
