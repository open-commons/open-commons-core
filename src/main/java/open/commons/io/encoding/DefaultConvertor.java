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
* 
* 
* This file is generated under this project, "open-commons-core".
*
* Date  : 2013. 6. 17. 오후 2:31:42
*
* Author: Park_Jun_Hong_(fafanmama_at_naver_com)
* 
*/
package open.commons.io.encoding;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.CharBuffer;

public class DefaultConvertor implements IEncodingConvertor {

    protected static final int READ_BUFFER_SIZE = 512;

    protected String inputEncoding;
    protected String outputEncoding;

    protected InputStream inStream;
    protected OutputStream outStream;

    /**
     * 
     * @param inEncoding
     *            원본 인코딩
     * @param outEncoding
     *            결과물 인코딩
     */
    public DefaultConvertor(String inEncoding, String outEncoding) {
        this.inputEncoding = inEncoding;
        this.outputEncoding = outEncoding;
    }

    /**
     * 
     * @see open.commons.io.encoding.IEncodingConvertor#convert()
     */
    public int convert() throws IOException {

        if (inStream == null || outStream == null) {
            throw new IllegalStateException("Reader & Writer must be set. reader: " + inStream + ", writer: " + outStream);
        }

        if (inputEncoding == null || outputEncoding == null) {
            throw new IllegalStateException("Encoding of Readr & Writer must be set. reader: " + inputEncoding + ", writer: " + outputEncoding);
        }

        InputStreamReader reader = new InputStreamReader(inStream, inputEncoding);
        OutputStreamWriter writer = new OutputStreamWriter(outStream, outputEncoding);

        int count = 0;
        int readCount = -1;

        CharBuffer buf = CharBuffer.allocate(READ_BUFFER_SIZE);

        while ((readCount = reader.read(buf)) != -1) {
            if (buf.hasArray()) {
                writer.write(buf.array(), 0, readCount);
            }

            buf.clear();

            count += readCount;
        }

        outStream.flush();

        reader.close();
        writer.close();

        return count;
    };

    /**
     * 
     * @see open.commons.io.encoding.IEncodingConvertor#convert(java.io.InputStream, java.io.OutputStream)
     */
    public int convert(InputStream inStream, OutputStream outStream) throws IOException {
        setInputStream(inStream);
        setOutputStream(outStream);

        return convert();
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
        DefaultConvertor other = (DefaultConvertor) obj;
        if (inputEncoding == null) {
            if (other.inputEncoding != null)
                return false;
        } else if (!inputEncoding.equals(other.inputEncoding))
            return false;
        if (outputEncoding == null) {
            if (other.outputEncoding != null)
                return false;
        } else if (!outputEncoding.equals(other.outputEncoding))
            return false;
        if (inStream == null) {
            if (other.inStream != null)
                return false;
        } else if (!inStream.equals(other.inStream))
            return false;
        if (outStream == null) {
            if (other.outStream != null)
                return false;
        } else if (!outStream.equals(other.outStream))
            return false;
        return true;
    }

    /**
     * @return the inEncoding
     */
    public String getInputEncoding() {
        return inputEncoding;
    }

    /**
     * @return the reader
     */
    public InputStream getInStream() {
        return inStream;
    }

    /**
     * @return the outEncoding
     */
    public String getOutputEncoding() {
        return outputEncoding;
    }

    /**
     * @return the writer
     */
    public OutputStream getOutStream() {
        return outStream;
    }

    /**
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((inputEncoding == null) ? 0 : inputEncoding.hashCode());
        result = prime * result + ((outputEncoding == null) ? 0 : outputEncoding.hashCode());
        result = prime * result + ((inStream == null) ? 0 : inStream.hashCode());
        result = prime * result + ((outStream == null) ? 0 : outStream.hashCode());
        return result;
    }

    @Override
    public String setInputEncoding(String charset) {
        String latestEncoding = inputEncoding;
        this.inputEncoding = charset;

        return latestEncoding;
    }

    /**
     * @param inStream
     *            the input-stream to read
     * @throws UnsupportedEncodingException
     */
    public void setInputStream(InputStream inStream) throws UnsupportedEncodingException {
        this.inStream = inStream;
    }

    @Override
    public String setOutputEncoding(String charset) {
        String latestEncoding = outputEncoding;
        this.outputEncoding = charset;

        return latestEncoding;

    }

    /**
     * @param outStream
     *            the output-stream to write
     * @throws UnsupportedEncodingException
     */
    public void setOutputStream(OutputStream outStream) throws UnsupportedEncodingException {
        this.outStream = outStream;
    }

    /**
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "EncodingConvertor [inputEncoding=" + inputEncoding + ", outputEncoding=" + outputEncoding + ", reader=" + inStream + ", writer=" + outStream + "]";
    }
}
