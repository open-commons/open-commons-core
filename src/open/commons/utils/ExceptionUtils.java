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
* Date  : 2015. 1. 13. 오전 8:37:55
*
* Author: Park_Jun_Hong_(fafanmama_at_naver_com)
* 
*/

package open.commons.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Iterator;

/**
 * 
 * @since 2015. 1. 13.
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
public class ExceptionUtils {

    public static String toString(StackTraceElement... stacks) {
        StringBuffer buf = new StringBuffer();

        Iterator<StackTraceElement> itr = Arrays.asList(stacks).iterator();

        if (itr.hasNext()) {
            buf.append(itr.next().toString());

            while (itr.hasNext()) {
                buf.append('\n');
                buf.append(itr.next().toString());
            }
        }
        buf.append('\n');

        return buf.toString();
    }

    public static String toString(Throwable e) {
        StringWriter writer = new StringWriter();
        e.printStackTrace(new PrintWriter(writer));

        return writer.toString();
    }
}
