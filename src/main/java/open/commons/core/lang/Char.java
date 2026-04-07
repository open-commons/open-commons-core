/*
 * Copyright 2021 Park Jun-Hong (parkjunhong77@gmail.com)
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
 * Date  : 2021. 6. 18. 오후 4:11:23
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.core.lang;

/**
 * 'char'를 {@link CharSequence}로 제공하는 클래스.
 * 
 * @since 2021. 6. 18.
 * @version 1.8.0
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */
public class Char implements CharSequence {

    private final char c;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 6. 18.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     *
     * @since 2021. 6. 18.
     * @version 1.8.0
     * 
     */
    public Char(char c) {
        this.c = c;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 6. 18.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param index
     * @return
     *
     * @since 2021. 6. 18.
     * 
     *
     * @see java.lang.CharSequence#charAt(int)
     */
    @Override
    public char charAt(int index) {
        if (index > 0) {
            throw new IndexOutOfBoundsException("char=" + this.c);
        }
        return this.c;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 6. 18.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2021. 6. 18.
     * 
     *
     * @see java.lang.CharSequence#length()
     */
    @Override
    public int length() {
        return 1;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 6. 18.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param start
     * @param end
     * @return
     *
     * @since 2021. 6. 18.
     * 
     *
     * @see java.lang.CharSequence#subSequence(int, int)
     */
    @Override
    public CharSequence subSequence(int start, int end) {
        if (start != 0 || end != 1 || start >= end) {
            throw new IndexOutOfBoundsException("char=" + this.c);
        }
        return this;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 6. 18.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2021. 6. 18.
     * 
     *
     * @see java.lang.Object#toString()
     */
    // 아래 내용에 적용됨.
    // - String.valueOf(c)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    @Override
    public String toString() {
        return String.valueOf(c);
    }
}
