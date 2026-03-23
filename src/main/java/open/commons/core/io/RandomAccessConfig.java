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
 * Date  : 2021. 11. 19. 오후 3:12:30
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package open.commons.core.io;

import java.util.Objects;

/**
 * 
 * @since 2021. 11. 19.
 * @version 1.8.0
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */
public class RandomAccessConfig implements IRandomAccessible {

    /** 읽을 시작 위치 */
    private final int position;
    /** 읽을 데이터 길이 */
    private final int length;
    /** 다음 번 읽을 위치 */
    private final int nextPosition;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 19.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     *
     * @param position
     * @param length
     *
     * @since 2021. 11. 19.
     * @version 1.8.0
     * 
     */
    public RandomAccessConfig(int position, int length) {
        this(position, length, -1);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 19.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     *
     * @param position
     * @param length
     * @param nextPosition
     *
     * @since 2021. 11. 19.
     * @version 1.8.0
     * 
     */
    public RandomAccessConfig(int position, int length, int nextPosition) {
        this.position = position;
        this.length = length;
        this.nextPosition = nextPosition;
    }

    /**
     *
     * @since 2021. 11. 19.
     * @version 1.8.0
     * 
     *
     * @see open.commons.core.io.IRandomAccessible#getLength()
     */
    @Override
    public int getLength() {
        return this.length;
    }

    /**
     *
     * @since 2021. 11. 19.
     * @version 1.8.0
     * 
     *
     * @see open.commons.core.io.IRandomAccessible#getNextPosition()
     */
    @Override
    public int getNextPosition() {
        return this.nextPosition;
    }

    /**
     *
     * @since 2021. 11. 19.
     * @version 1.8.0
     * 
     *
     * @see open.commons.core.io.IRandomAccessible#getPosition()
     */
    @Override
    public int getPosition() {
        return this.position;
    }

    /**
     *
     * @since 2021. 11. 19.
     * @version 1.8.0
     * 
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("RandomAccessConfig [position=");
        builder.append(position);
        builder.append(", length=");
        builder.append(length);
        builder.append(", nextPosition=");
        builder.append(nextPosition);
        builder.append("]");

        return Objects.requireNonNull(
                // [PATCH[ JDK 표준 API의 JSpecify 미지원 우회용 임시 널 체크.
                // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 requireNonNull 래핑 제거.
                builder.toString() //
        );
    }

}
