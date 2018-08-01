/*
 * Copyright 2011 Park Jun-Hong_(fafanmama_at_naver_com)
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
* Date  : 2015. 3. 4. 오후 6:23:47
*
* Author: Park_Jun_Hong_(fafanmama_at_naver_com)
* 
*/

package open.commons.utils;

import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;

/**
 * 
 * 
 * @since 2015. 3. 4.
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
public class NetUtils {
    private static final String REGEX_IPV4 = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."//
            + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."//
            + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."//
            + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

    /**
     * {@link NetworkInterface}에 포함된 {@link InterfaceAddress}중에서 IPV4에 해당하는 {@link InterfaceAddress}를 반환한다.
     * 
     * @param ni
     * @return <b><code>nullable</code></b>.
     *
     * @since 2015. 3. 4.
     */
    public static InterfaceAddress getInet4Address(NetworkInterface ni) {
        for (InterfaceAddress infAddr : ni.getInterfaceAddresses()) {
            if (infAddr.getAddress().getHostAddress().matches(REGEX_IPV4)) {
                return infAddr;
            }
        }

        return null;
    }

    /**
     * {@link NetworkInterface}에 포함된 {@link InterfaceAddress}중에서 IPV4에 해당하는 {@link InterfaceAddress}를 반환한다.
     * 
     * @param name
     * @return <b><code>nullable</code></b>.
     * 
     * @throws SocketException
     *
     * @since 2015. 3. 4.
     */
    public static InterfaceAddress getInet4Address(String name) throws SocketException {
        NetworkInterface ni = NetworkInterface.getByName(name);

        for (InterfaceAddress infAddr : ni.getInterfaceAddresses()) {
            if (infAddr.getAddress().getHostAddress().matches(REGEX_IPV4)) {
                return infAddr;
            }
        }

        return null;
    }

    public static String toPrettyString(byte[] mac) {

        String macStr = toString(mac);

        StringBuffer sb = new StringBuffer();
        int pos = 0;
        for (char c : macStr.toCharArray()) {
            if (pos > 0 && pos % 2 == 0) {
                sb.append(':');
            }

            sb.append(c);

            pos++;
        }

        return sb.toString();
    }

    public static String toString(byte[] mac) {
        return ByteUtils.hexBinString(mac);
    }
}
