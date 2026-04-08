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
* Date  : 2014. 9. 17. 오후 1:27:43
*
* Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
* 
*/

package open.commons.core.validation.support;

import java.util.Iterator;
import java.util.NoSuchElementException;

import open.commons.core.validation.AbstractTokenizer;

/**
 * 문자열({@link CharSequence})을 문자({@link Character}) 단위로 토큰화하는 클래스입니다.
 */
public class StringTokenizer extends AbstractTokenizer<CharSequence, Character> {

    /**
     * 객체를 생성합니다.
     *
     * @param data
     *            토큰화할 대상 문자열 데이터
     */
    public StringTokenizer(CharSequence data) {
        super(data);
    }

    /**
     * 데이터를 위한 토큰 반복자를 생성하여 반환합니다.
     *
     * @return 문자 토큰 반복자
     */
    @Override
    protected Iterator<Character> tokenize() {
        // [PATCH] ArrayList 객체 복사를 제거하고, 원본 데이터에 직접 접근하는 Zero-Allocation Iterator 구현
        return new Iterator<Character>() {
            private int cursor = 0;

            @Override
            public boolean hasNext() {
                return cursor < data.length();
            }

            @Override
            public Character next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("더 이상 읽을 문자가 없습니다.");
                }
                return data.charAt(cursor++);
            }
        };
    }
}