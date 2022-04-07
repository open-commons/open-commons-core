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
 * Date  : 2022. 3. 17. 오후 4:26:19
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package test;

import java.util.Arrays;

import open.commons.core.csv.WriteAt;
import open.commons.core.utils.CsvUtils;

/**
 * 
 * @since 2022. 3. 17.
 * @version _._._
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
public class TestCsvWriteAt {

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 3. 17.		박준홍			최초 작성
     * </pre>
     *
     *
     * @since 2022. 3. 17.
     * @version _._._
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public TestCsvWriteAt() {
    }

    public static void main(String[] args) {

        WriteAtClass wac = new WriteAtClass();

        String[] arr = CsvUtils.objectToArray(wac);

        System.out.println(Arrays.toString(arr));

    }

    static class WriteAtClass {

        @WriteAt(index = 0)
        public String name() {
            return "name";
        }

        @WriteAt(index = 1, nullIsEmpty = false)
        public String nullIsNull() {
            return null;
        }

        @WriteAt(index = 2, nullIsEmpty = true)
        public String nullIsEmpty() {
            return null;
        }

        @WriteAt(index = 3)
        public String addr() {
            return "addr";
        }
    }
}
