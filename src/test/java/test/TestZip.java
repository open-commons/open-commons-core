/*
 * Copyright 2021 Park Jun-Hong (parkjunhong77@gmail.com)
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
 * Date  : 2021. 11. 9. 오후 3:54:11
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package test;

import java.io.File;
import java.io.IOException;

import open.commons.core.utils.ZipUtils;

/**
 * 
 * @since 2021. 11. 9.
 * @version _._._
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
public class TestZip {

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 9.		박준홍			최초 작성
     * </pre>
     *
     *
     * @since 2021. 11. 9.
     * @version _._._
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public TestZip() {
    }

    public static void main(String[] args) throws IOException {
        String inPathname = "/home/parkjunhong/dev/master-workspace/open-commons/open-commons-core/src/main/java/open/commons/utils";
        String outPathname = "/home/parkjunhong/Downloads/open-commons-utils.zip";

        inPathname = "/home/parkjunhong/dev/master-workspace/open-commons/open-commons-core/src/main/java/open/commons/utils/ZipUtils.java";
        outPathname = "/home/parkjunhong/Downloads/open-commons-utils-ZipUtils.zip";

        inPathname = "/home/parkjunhong/dev/master-workspace/open-commons/open-commons-core/src/main/java/open/commons/validation";
        outPathname = "/home/parkjunhong/Downloads/open-commons-utils-validation.zip";

        File input = new File(inPathname);
        File output = new File(outPathname);

        ZipUtils.zip(input, output, 5);

        // unzip
        String unzipPathname = "/home/parkjunhong/Downloads";
        File unzipDir = new File(unzipPathname);
        ZipUtils.unzip(output, unzipDir);
    }

}
