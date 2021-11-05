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
* This file is generated under this project, "open-commons-core".
*
* Date  : 2013. 5. 23. 오전 9:47:49
*
* Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
* 
*/

/**
 * 
 */
package open.commons.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class ThreadUtils {

    public static String getStackTrace(Exception e) {

        StringWriter writer = new StringWriter();
        PrintWriter pw = new PrintWriter(writer);
        e.printStackTrace(pw);

        pw.close();

        return writer.toString();
    }

    /**
     * 현재 {@link Thread}에 이름을 지정하고, 이전 이름을 반환한다.
     * 
     * @param name
     * @return
     */
    public static String setThreadName(String name) {
        Thread thread = Thread.currentThread();

        String otn = thread.getName();

        thread.setName(name);

        return otn;
    }

    public static boolean sleep(long millis) {
        boolean wait = true;
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            wait = false;
        }
        return wait;
    }

    public static List<Thread> start(boolean autostart, Runnable... runnables) {
        List<Thread> threads = new ArrayList<Thread>();

        Thread thread = null;
        for (Runnable runnable : runnables) {
            if (runnable != null) {
                thread = new Thread(runnable);
                threads.add(thread);

                if (autostart) {
                    thread.start();
                }
            }
        }

        return threads;
    }

    public static String getCurrentMethodName() {
        Thread thread = Thread.currentThread();

        StackTraceElement[] stacks = thread.getStackTrace();

        return stacks.length < 3 ? "Oops... I DO NOT know where here is." : stacks[2].getMethodName();
    }
}