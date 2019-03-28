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

/**
* @title CommonUtils
* @since 2011. 10. 01.
*/
package open.commons.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
 * @since 2011. 10. 01.
 * 
 */
public class RegularExprTester {

    public static void println(Object obj) {
        System.out.println(obj);
    }

    public static void test(Matcher m, String category) {
        System.out.println("---------- " + category + " --------------->>");
        if (m.matches()) {
            System.out.println("<< success >> " + m);

            for (int i = 0; i <= m.groupCount(); i++) {
                System.out.println(i + ": >" + m.group(i) + "<");
            }
        } else {
            System.out.println("<<   fffffffffffffffffffffffffffffffffff ail  >> " + m);
        }

        System.out.println("<<-------------------------");
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2011. 10. 01.
     * </pre>
     *
     * @param category
     * @param input
     * @param regex
     *            The expression to be compiled
     *
     * @param flags
     *            Match flags, a bit mask that may include {@link #CASE_INSENSITIVE}, {@link #MULTILINE},
     *            {@link #DOTALL}, {@link #UNICODE_CASE}, {@link #CANON_EQ}, {@link #UNIX_LINES}, {@link #LITERAL},
     *            {@link #UNICODE_CHARACTER_CLASS} and {@link #COMMENTS}
     * @return
     *
     * @since 2011. 10. 01.
     */
    public static Matcher test(String category, String input, String regex, int flag) {
        System.out.println("---------- " + category + " --------------->>");
        Pattern p = Pattern.compile(regex, flag);
        Matcher m = p.matcher(input);

        if (m.matches()) {
            System.out.println("<< success >> input: " + input + ", regex: " + regex);
            for (int i = 0; i <= m.groupCount(); i++) {
                System.out.println(i + ": >" + m.group(i) + "<");
            }
        } else {
            System.out.println("<<   fffffffffffffffffffffffffffffffffff ail  >> input: " + input + ", regex: " + regex);
        }

        System.out.println("<<-------------------------");
        return m;
    }

}
