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
* This file is generated under this project, "UUUU".
*
* Date  : 2014. 4. 10. 오후 5:46:34
*
* Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
* 
*/

package open.commons.core.validation.token;

import open.commons.core.validation.DefaultTokenValidator;

/**
 * 
 * @since 2014. 4. 10.
 * 
 */
public class ExclusiveStrValidator extends DefaultTokenValidator<CharSequence> {

    /**
     * 
     * @since 2014. 4. 10.
     */
    public ExclusiveStrValidator() {
        super("제외시킬 문자열");
    }

    /**
     * @see open.commons.core.validation.DefaultTokenValidator#validate(java.lang.Object)
     */
    // 아래 내용에 적용됨.
    // - CharSequence token
    // [PATCH] Eclipse JDT 분석기의 제네릭 & @NullMarked 치환 해석 오류 우회
    // [TODO] 향후 Eclipse IDE 정적 분석기가 JSpecify 제네릭 치환을 완벽히 지원하면 '제거'
    @SuppressWarnings("null")
    @Override
    public boolean validate(CharSequence token) {
        return !super.validate(token);
    }
}
