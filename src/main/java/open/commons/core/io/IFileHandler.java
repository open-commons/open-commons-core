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
 * This file is generated under this project, "open-commons-core".
 *
 * Date  : 2011. 9. 29.
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.core.io;

import java.io.File;

import org.jspecify.annotations.Nullable;

/**
 * 
 * @since 2011. 09. 29.
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */
public interface IFileHandler {

    /**
     * 파일명을 표현하는 패턴 문자열을 반환합니다.
     * 
     * @return {@link IFileHandler}가 처리할 파일 확장자. {@code null}인 경우는 모든 파일을 처리합니다.
     * @throws Exception
     * 
     * @since 2012. 01. 17.
     */
    public @Nullable String getFilenamePattern() throws Exception;

    /**
     * 주어진 파일을 처리합니다.
     * 
     * @param file
     * @throws Exception
     * 
     * @since 2012. 01. 17.
     */
    public void handleFile(File file) throws Exception;

}
