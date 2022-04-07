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
* Date  : 2012. 03. 13. 오전 10:06:00
*
* Author: Park Jun-Hong (parkjunhong77@gmail.com)
* 
*/
package open.commons.core.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * <BR>
 * 
 * @since 2012. 03. 13.
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
public class FilenameUtils {

    private static String $prefix(String s) {
        return s == null || s.equals("") ? "" : s + "_";
    }

    private static String $suffix(String s) {
        return s == null || s.equals("") ? "" : "_" + s;
    }

    public static String toOutputDir(File file, String suffix) {
        CheckUtils.checkNull(file);

        return file.getName() + "_" + new SimpleDateFormat("yyyyMMdd_hhmmss").format(new Date()) + $suffix(suffix);
    }

    public static String toOutputDir(File inputfile, String prefix, String suffix) {
        CheckUtils.checkNull(inputfile);

        return $prefix(prefix) + inputfile.getName() + "_" + new SimpleDateFormat("yyyyMMdd_hhmmss").format(new Date()) + $suffix(suffix);
    }

    public static String toOutputDir(String inputfileString) {
        CheckUtils.checkNull(inputfileString);

        return inputfileString + "_" + new SimpleDateFormat("yyyyMMdd_hhmmss").format(new Date());
    }

    public static String toOutputDir(String inputfileString, String suffix) {
        CheckUtils.checkNull(inputfileString);

        return inputfileString + "_" + new SimpleDateFormat("yyyyMMdd_hhmmss").format(new Date()) + $suffix(suffix);
    }

    public static String toOutputDir(String inputfileString, String prefix, String suffix) {
        CheckUtils.checkNull(inputfileString);

        return $prefix(prefix) + inputfileString + "_" + new SimpleDateFormat("yyyyMMdd_hhmmss").format(new Date()) + $suffix(suffix);
    }

    public static String toOutputDirename(File inputfile) {
        CheckUtils.checkNull(inputfile);

        return inputfile.getName() + "_" + new SimpleDateFormat("yyyyMMdd_hhmmss").format(new Date());
    }

    public static String toOutputFilename(File inputfile, String ext) {
        CheckUtils.checkNull(inputfile);

        return inputfile.getName() + "_" + new SimpleDateFormat("yyyyMMdd_hhmmss").format(new Date()) + "." + ext;
    }

    public static String toOutputFilename(File file, String suffix, String ext) {
        CheckUtils.checkNull(file);

        return file.getName() + "_" + new SimpleDateFormat("yyyyMMdd_hhmmss").format(new Date()) + $suffix(suffix) + "." + ext;
    }

    public static String toOutputFilename(File inputfile, String prefix, String suffix, String ext) {
        CheckUtils.checkNull(inputfile);

        return $prefix(prefix) + inputfile.getName() + "_" + new SimpleDateFormat("yyyyMMdd_hhmmss").format(new Date()) + $suffix(suffix) + "." + ext;
    }

    public static String toOutputFilename(String inputfileString, String ext) {
        CheckUtils.checkNull(inputfileString);

        return inputfileString + "_" + new SimpleDateFormat("yyyyMMdd_hhmmss").format(new Date()) + "." + ext;
    }

    public static String toOutputFilename(String inputfileString, String suffix, String ext) {
        CheckUtils.checkNull(inputfileString);

        return inputfileString + "_" + new SimpleDateFormat("yyyyMMdd_hhmmss").format(new Date()) + $suffix(suffix) + "." + ext;
    }

    public static String toOutputFilename(String inputfileString, String prefix, String suffix, String ext) {
        CheckUtils.checkNull(inputfileString);

        return $prefix(prefix) + inputfileString + "_" + new SimpleDateFormat("yyyyMMdd_hhmmss").format(new Date()) + $suffix(suffix) + "." + ext;
    }
}
