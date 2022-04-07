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
* 
* This file is generated under this project, "open-commons-core".
*
* Date  : 2013. 6. 21. 오후 3:01:48
*
* Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
* 
*/
package open.commons.core.unix.tool;

import open.commons.core.utils.FunctuationTable;

/**
 * Constants File Monitor
 * 
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */
public class FmConstants {

    public static final String DIR_DELIMITER = ",";
    public static final String DIR_OPT_DELIMITER = ";";
    public static final String FILE_DELIMITER = ",";
    public static final String LOG_DELIMITER = ",";
    public static final String FILENAME_VERBOSE_DELIMITER = ":";

    public static final String DIR_DELIMITER_DESC = FunctuationTable.getDesc(DIR_DELIMITER);
    public static final String DIR_OPT_DELIMITER_DESC = FunctuationTable.getDesc(DIR_OPT_DELIMITER);
    public static final String FILE_DELIMITER_DESC = FunctuationTable.getDesc(FILE_DELIMITER);
    public static final String LOG_DELIMITER_DESC = FunctuationTable.getDesc(LOG_DELIMITER);
    public static final String FILENAME_VERBOSE_DELIMITER_DESC = FunctuationTable.getDesc(FILENAME_VERBOSE_DELIMITER);

    // prevent to create a new instance.
    private FmConstants() {
    }

}
