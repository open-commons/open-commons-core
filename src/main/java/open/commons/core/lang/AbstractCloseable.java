/*
 * Copyright 2019 Park Jun-Hong (parkjunhong77@gmail.com)
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
 * Date  : 2019. 2. 19. 오전 11:53:15
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.core.lang;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 자원을 자동으로 해제하는 클래스.
 * 
 * <br>
 * 
 * <pre>
 * [개정이력]
 *      날짜      | 작성자   |   내용
 * ------------------------------------------
 * xxxx.xx.xx       xxx         최초작성
 * 2019. 10. 17.        parkjunohng77@gmail.com         Logger 교체. org.apache.logging.log4j.Logger -> org.slf4j.Logger 로 교체
 * </pre>
 * 
 * @since 2019. 2. 19.
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */
public class AbstractCloseable implements CloseableContainer {

    protected Logger logger = Objects.requireNonNull(
            // [PATCH[ JDK 표준 API의 JSpecify 미지원 우회용 임시 널 체크.
            // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 requireNonNull 래핑 제거.
            LoggerFactory.getLogger(getClass()) //
    );

    /**
     * 
     * @since 2019. 2. 19.
     */
    public AbstractCloseable() {
    }
}
