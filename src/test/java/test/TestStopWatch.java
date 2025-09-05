/*
 * Copyright 2022 Park Jun-Hong (parkjunhong77@gmail.com)
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
 * Date  : 2022. 1. 5. 오후 5:12:54
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package test;

import open.commons.core.test.StopWatch;
import open.commons.core.utils.ThreadUtils;

/**
 * 
 * @since 2022. 1. 5.
 * @version _._._
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
public class TestStopWatch {

    /**
     * <br>
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 1. 5.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     *
     *
     * @since 2022. 1. 5.
     * @version _._._
     * @author  Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public TestStopWatch() {
    }
    
    public static void main(String[] args) {
        stat();
    } 
    
    
    static void stat() {
        StopWatch watch = new StopWatch();
        watch.start();
        
        ThreadUtils.sleep(1000);
        watch.record("1234567890");
        ThreadUtils.sleep(1000);
        watch.record("12345678901234");
//        ThreadUtils.sleep(1000);
        
        watch.stop();
        
        System.out.println(watch.stats());
        
    }
}
