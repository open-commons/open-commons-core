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
* Date  : 2013. 7. 3. 오후 6:22:08
*
* Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
* 
*/
package open.commons.core.log;

import java.io.PrintStream;

/**
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */
public interface ILogger {

    public static final String TYPE_ERROR = "ERR";

    public static final String TYPE_LOG = "LOG";

    /**
     * 메소드의 진입 로그를 출력합니다.
     */
    public void entry();

    /**
     * 메소드의 진입 로그를 출력합니다.
     * 
     * @param log
     */
    public void entry(Object log);

    /**
     * 메소드의 진입 로그를 출력합니다.
     * 
     * @param log
     */
    public void entry(String log);

    /**
     * 에러 로그를 출력합니다.
     * 
     * @param log
     */
    public void error(Object log);

    /**
     * 에러 로그를 출력합니다.
     * 
     * @param log
     * @param e
     */
    public void error(Object log, Exception e);

    /**
     * 에러 로그를 출력합니다.
     * 
     * @param log
     */
    public void error(String log);

    /**
     * 에러 로그를 출력합니다.
     * 
     * @param log
     * @param e
     */
    public void error(String log, Exception e);

    /**
     * 메소드 종료 로그를 출력합니다.
     */
    public void leave();

    /**
     * 메소드 종료 로그를 출력합니다.
     * 
     * @param log
     */
    public void leave(Object log);

    /**
     * 메소드 종료 로그를 출력합니다.
     * 
     * @param log
     */
    public void leave(String log);

    /**
     * 일반 로그를 출력합니다.
     * 
     * @param log
     */
    public void log(Object log);

    /**
     * 일반 로그를 출력합니다.
     * 
     * @param log
     */
    public void log(String log);

    /**
     * 로그 출력 여부를 설졍합니다.
     * 
     * @param enabled
     * @return TODO
     */
    public ILogger setEnabled(boolean enabled);

    /**
     * 에러 로그 스트림을 설정합니다.
     * 
     * @param errOutStream
     */
    public void setErrOutStream(PrintStream errOutStream);

    /**
     * 일반 로그 스트림을 설정합니다.
     * 
     * @param stdOutStream
     */
    public void setStdOutStream(PrintStream stdOutStream);

}