/*
 * Copyright 2019 Park Jun-Hong (parkjunhong77/google/com)
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
 * Date  : 2019. 2. 20. 오후 7:50:23
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package open.commons.concurrent;

/**
 * 
 * @param <E>
 *            작업 타입.
 * 
 * @since 2019. 2. 20.
 * @version 1.6.3
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */
public interface IJobFinishedListener<E> {

    /**
     * 종료된 작업의 결과 데이타를 처리한다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 2. 20.     박준홍         최초 작성
     * </pre>
     *
     * @param data
     *
     * @since 2019. 2. 20.
     */
    public void handleFinished(E job);
}
