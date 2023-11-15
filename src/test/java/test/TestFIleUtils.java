/*
 * Copyright 2023 Park Jun-Hong_(parkjunhong77@gmail.com)
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
 * Date  : 2023. 11. 15. 오후 5:36:29
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import open.commons.core.utils.FileUtils;
import open.commons.core.utils.StringUtils;

/**
 * 
 * @since 2023. 11. 15.
 * @version _._._
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
public class TestFIleUtils {

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2023. 11. 15.		박준홍			최초 작성
     * </pre>
     *
     *
     * @since 2023. 11. 15.
     * @version _._._
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public TestFIleUtils() {
    }

    public static void main(String[] args) {
        // File src = new File("D:\\03.WORKSPACE\\eclipse-SDK-4.2-win32-x86_64\\open.commons\\src\\open\\commons");
        // for (File file : src.listFiles()) {
        // System.out.printf("%9s: %s\n", file.isFile() ? "File" : "Directory", file.getAbsolutePath());
        // }
        // System.out.println("-------------------------------------------------------------------------------");
        // for (File file : listFiles(src)) {
        // System.out.printf("%9s: %s\n", file.isFile() ? "File" : "Directory", file.getAbsolutePath());
        // }
        // System.out.println("-------------------------------------------------------------------------------");
        // for (File file : listDirectories(src)) {
        // System.out.printf("%9s: %s\n", file.isFile() ? "File" : "Directory", file.getAbsolutePath());
        // }
        //
        // File dir = new File("D:\\03.WORKSPACE\\eclipse-SDK-4.2-win32-x86_64\\open.commons\\src - 복사본 (2)");
        // emptyDir(dir);
        //
        // String filepath = "D:\\03.WORKSPACE\\eclipse-SDK-4.2-win32-x86_64\\open.commons\\.classpath";
        // System.out.println("path: " + getFilePath(filepath));
        // System.out.println("name: " + getFileName(filepath));
        // System.out.println("ext : " + getFileExtension(filepath));

        System.out.println("abc.de".lastIndexOf('.'));
        System.out.println(StringUtils.backIndexOf("abc.de", '.'));

        Path abspath = Paths.get("/home/parkjunhong/Downloads/test-open-commons", "test.txt");

        String data = "/home/parkjunhong/Downloads/test-open-commons => " + System.currentTimeMillis() + "\n";

        try {
             FileUtils.write(abspath, data, false);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
