/*
 * Copyright 2011 Park Jun-Hong (parkjunhong77/google/com)
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
* This file is generated under this project, "open-commons-core".
*
* Date  : 2013. 6. 20. 오후 2:36:45
*
* Author: Park_Jun_Hong_(fafanmama_at_naver_com)
* 
*/

/**
 * 
 */
package open.commons.net;

import java.io.BufferedReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import open.commons.utils.Base64Utils;

/**
 * Protocol for {@link BufferedReader}.<br>
 * 
 * <pre>
 * [개정이력]
 *      날짜      | 작성자   |   내용
 * ------------------------------------------
 * 2013. 6. 20. 박준홍     최초 작성.
 * </pre>
 * 
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 * @deprecated Not Use. To be deleted.
 */
public class BRProtocol {

    private static final String CLASS = BRProtocol.class.getSimpleName();

    private String header;

    private ArrayList<String> data = new ArrayList<String>();

    public BRProtocol() {
    }

    /**
     * 
     * @param header
     *            프로토콜 헤더
     * 
     * @see NullPointerException
     */
    public BRProtocol(String header) {
        this(header, Collections.<String> emptyList());

    }

    /**
     * 
     * @param header
     *            프로토콜 헤더
     * @param data
     *            전송 데이터
     * 
     * @see NullPointerException
     */
    public BRProtocol(String header, List<String> data) {
        if (header == null || data == null) {
            throw new IllegalArgumentException("헤더와 데이터는 null이 올 수 없습니다. header: " + header + ", data: " + data);
        }
        this.header = header;
        this.data.addAll(data);
    }

    /**
     * @param data
     *            the data to set
     */
    public void addData(ArrayList<String> data) {
        this.data.addAll(data);
    }

    public void addData(String... data) {
        for (String d : data) {
            this.data.add(d);
        }
    }

    private byte[] decodeBase64(String string) throws UnsupportedEncodingException {
        return Base64Utils.decode(string.getBytes(BRPConstants.CHARSET_NAME));
    }

    private byte[] encodeBase64(String string) throws UnsupportedEncodingException {
        return Base64Utils.encode(string.getBytes(BRPConstants.CHARSET_NAME));
    }

    /**
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BRProtocol other = (BRProtocol) obj;
        if (data == null) {
            if (other.data != null)
                return false;
        } else if (!data.equals(other.data))
            return false;
        if (header == null) {
            if (other.header != null)
                return false;
        } else if (!header.equals(other.header))
            return false;
        return true;
    }

    /**
     * @return the data
     */
    public ArrayList<String> getData() {
        return data;
    }

    /**
     * @return the data
     */
    public String getData(int index) {
        return data.get(index);
    }

    /**
     * @return the header
     */
    public String getHeader() {
        return header;
    }

    /**
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((data == null) ? 0 : data.hashCode());
        result = prime * result + ((header == null) ? 0 : header.hashCode());
        return result;
    }

    public String marshall() throws UnsupportedEncodingException {

        byte[] header = encodeBase64(this.header);
        byte[][] data = new byte[this.data.size()][];

        int dataLength = 0;
        int i = 0;
        for (String d : this.data) {
            data[i] = encodeBase64(d);

            dataLength += data[i].length;

            i++;
        }

        byte[] rtnData = new byte[header.length + dataLength + BRPConstants.DATA_DELIMITER.length * i];

        // copy header
        System.arraycopy(header, 0, rtnData, 0, header.length);

        int dataPos = header.length;
        for (int j = 0; j < data.length; j++) {
            // copy delimiter
            System.arraycopy(BRPConstants.DATA_DELIMITER, 0, rtnData, dataPos, BRPConstants.DATA_DELIMITER.length);
            dataPos += BRPConstants.DATA_DELIMITER.length;

            // copy data
            System.arraycopy(data[j], 0, rtnData, dataPos, data[j].length);
            dataPos += data[j].length;
        }

        return new String(rtnData, BRPConstants.CHARSET_NAME);
    }

    /**
     * @param header
     *            the header to set
     */
    public void setHeader(String header) {
        this.header = header;
    }

    /**
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return CLASS + " [header=" + header + ", data=" + data + "]";
    }

    public void unmarshall(String data) throws UnsupportedEncodingException {

        String[] dataStr = data.split("[" + new String(BRPConstants.DATA_DELIMITER) + "]");

        this.header = new String(decodeBase64(dataStr[0]), BRPConstants.CHARSET_NAME);

        for (int i = 1; i < dataStr.length; i++) {
            this.data.add(new String(decodeBase64(dataStr[i]), BRPConstants.CHARSET_NAME));
        }
    }
}