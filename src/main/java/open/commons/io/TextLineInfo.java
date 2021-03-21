/*
 * Copyright 2020 Park Jun-Hong_(parkjunhong77/google/com)
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
 * Date  : 2020. 11. 12. 오후 5:02:31
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package open.commons.io;

/**
 * 텍스트 파일의 줄 단위 데이터 정보
 * 
 * @since 2020. 11. 12.
 * @version _._._
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
public class TextLineInfo implements IRandomAccessible {
    /** 파일 경로 */
    private String filepath;
    /** 라인 번호 */
    private final int lineNumber;
    /** 현재 줄 시작 위치 */
    private int position;
    /** 줄 단위 데이터 크기 */
    private int length;
    /** 다음 줄 시작 위치 */
    private int nextPosition;
    /** 줄 단위 데이터를 포함한 현재까지의 데이터 크기 */
    private int totalByteCount;

    /**
     * 
     * @since 2020. 11. 12.
     */
    public TextLineInfo(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    /**
     *
     * @return the filepath
     *
     * @since 2020. 11. 12.
     */
    public String getFilepath() {
        return filepath;
    }

    /**
     * @return
     *
     * @since 2020. 11. 12.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see open.commons.io.IRandomAccessible#getLength()
     */
    @Override
    public int getLength() {
        return length;
    }

    /**
     *
     * @return the lineNumber
     *
     * @since 2020. 11. 12.
     */
    public int getLineNumber() {
        return lineNumber;
    }

    /**
     *
     * @return the nextPosition
     *
     * @since 2020. 11. 13.
     * @see open.commons.io.IRandomAccessible#getNextPosition()
     */
    @Override
    public int getNextPosition() {
        return this.nextPosition;
    }

    /**
     *
     * @return the position
     *
     * @since 2020. 11. 12.
     * @see open.commons.io.IRandomAccessible#getPosition()
     */
    @Override
    public int getPosition() {
        return position;
    }

    /**
     *
     * @return the totalByteCount
     *
     * @since 2020. 11. 12.
     */
    public int getTotalByteCount() {
        return totalByteCount;
    }

    /**
     * @param filepath
     *            the filepath to set
     *
     * @since 2020. 11. 12.
     */
    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    /**
     * @param length
     *            the length to set
     *
     * @since 2020. 11. 12.
     */
    public void setLength(int length) {
        this.length = length;
        updateNext();
    }

    /**
     * @param position
     *            the position to set
     *
     * @since 2020. 11. 12.
     */
    public void setPosition(int position) {
        this.position = position;
        updateNext();
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 11. 13.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2020. 11. 13.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("TextLineInfo [filepath=");
        builder.append(filepath);
        builder.append(", lineNumber=");
        builder.append(lineNumber);
        builder.append(", position=");
        builder.append(position);
        builder.append(", length=");
        builder.append(length);
        builder.append(", nextPosition=");
        builder.append(nextPosition);
        builder.append(", totalByteCount=");
        builder.append(totalByteCount);
        builder.append("]");
        return builder.toString();
    }

    private void updateNext() {
        this.totalByteCount = this.position + this.length;
        this.nextPosition = this.position + this.length;
    }

    public static TextLineInfo build(int lineNumber, int position, String string) {
        return build(lineNumber, position, string, null);
    }

    public static TextLineInfo build(int lineNumber, int position, String string, String filepath) {
        TextLineInfo tli = new TextLineInfo(lineNumber);

        tli.setPosition(position);
        tli.setLength(string.getBytes().length);
        tli.setFilepath(filepath);

        return tli;
    }
}
