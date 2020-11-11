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
 * 
 * This file is generated under this project, "open-commons-core".
 *
 * Date  : 2013. 6. 21. 오후 3:12:17
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */
package open.commons.utils;

import java.util.HashMap;
import java.util.Map;

public class FunctuationTable {

    private static Map<String, String> functuations = new HashMap<String, String>();

    static {

        functuations.put(":", "colon");
        functuations.put(";", "semicolon");
        functuations.put("!", "exclamation mark");
        functuations.put("*", "asterisk");
        functuations.put("$", "dollar sign");
        functuations.put("%", "percent sign");
        functuations.put("=", "equal sign");
        functuations.put("+", "plus sign");
        functuations.put("<>", "angle brackets");
        functuations.put("()", "parentheses / round brackets / curved brackets");
        functuations.put("[]", "square brackets");
        functuations.put("{}", "curly brackets / braces");
        functuations.put(".", "period / full stop");
        functuations.put(",", "comma");
        functuations.put("'", "quotation mark / inverted comma");
        functuations.put("\"", "double quotation mark / double inverted comma");
        functuations.put("?", "question mark");
        functuations.put("/", "slash");
        functuations.put("\\", "back slash");
        functuations.put("-", "Hyphen / dash");
        functuations.put("_", "underline / underscore");
        functuations.put("#", "hash");
        functuations.put("&", "ampersand");
        functuations.put("@", "at");
        functuations.put("^", "circumflex");
        functuations.put("`", "grave");
        functuations.put("~", "tilde");
        functuations.put("|", "vertical bar");
        functuations.put("'", "apostrophe");
    }

    private FunctuationTable() {

    }

    public static String getDesc(String mark) {
        String rtnValue = getName(mark);

        if (rtnValue != null) {
            rtnValue = rtnValue + "(" + mark + ")";
        }

        return rtnValue;
    }

    public static String getName(String mark) {
        return functuations.get(mark);
    }
}
