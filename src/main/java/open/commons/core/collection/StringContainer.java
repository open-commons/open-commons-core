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
* Date  : 2012. 7. 30. 오후 2:53:18
*
* Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
*
* File  : StringContainer.java 
* 
*/
package open.commons.core.collection;

import org.jspecify.annotations.Nullable;

/**
 * @since 2012. 7. 30.
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
public class StringContainer extends AContainer<String> {

    // 아래 내용에 적용됨.
    // - container.contains()
    // [PATCH] [IDE-Null] Eclipse JDT 분석기의 제네릭 & @NullMarked 치환 해석 오류 우회
    // [TODO] 향후 Eclipse IDE 정적 분석기가 JSpecify 제네릭 치환을 완벽히 지원하면 '제거'
    @SuppressWarnings("null")
    @Override
    public boolean contains(@Nullable String container, @Nullable String contained) {
        return checkNull(container, contained) && container.contains(contained);
    }
}
