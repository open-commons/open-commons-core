/*
 * Copyright 2011 Park Jun-Hong_(fafanmama_at_naver_com)
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
*/
package open.commons.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.SequenceInputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Vector;

import open.commons.CommonProperties;

/**
 * 
 * 
 * <BR>
 * 
 * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
 * @since 2012. 01. 10.
 * 
 */
public class IOUtils {

    private static final String COMMAND_OPEN;

    static {
        final String PREFIX = "io.command.explorer.";
        Properties prop = CommonProperties.subProperteis(PREFIX);

        final String osname = System.getProperty("os.name").toLowerCase();

        String tmpcmd = null;

        String key = null;
        for (Entry<Object, Object> entry : prop.entrySet()) {
            if (tmpcmd != null) {
                break;
            }

            key = (String) entry.getKey();

            if (osname.contains(key.replace(PREFIX, ""))) {
                tmpcmd = (String) entry.getValue();
            }
        }

        COMMAND_OPEN = tmpcmd;
    }

    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

    /**
     * {@link BufferedWriter}에 데이터를 바로 보낸다.
     * 
     * @param writer
     * @param msg
     * @throws IOException
     */
    public void write(BufferedWriter writer, String msg) throws IOException {
        writer.write(msg + LINE_SEPARATOR);
    }

    /**
     * {@link AutoCloseable}를 모두 닫는다.
     * 
     * @param closeables
     *            {@link AutoCloseable} 객체들.
     */
    public static void close(AutoCloseable... closeables) {
        for (AutoCloseable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (Exception ignored) {
                }
            }
        }
    }

    /**
     * 주어진 {@link File}을 읽어오는 {@link BufferedReader}를 반환한다.
     * 
     * @param file
     * @return 예외가 발생하는 경우 <code>null</code>을 반환한다.
     * @throws FileNotFoundException
     *             파일이 없는 경우 발생하지만, 실제로는 <code>null</code>을 반환한다.
     * 
     * @since 2012. 01. 10.
     * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
     */
    public static BufferedReader getReader(File file) {
        BufferedReader reader = null;

        if (file != null) {
            try {
                reader = new BufferedReader(new FileReader(file));
            } catch (FileNotFoundException ignored) {
            }
        }

        return reader;
    }

    /**
     * {@link InputStream}을 가지고 {@link BufferedReader}를 생성해서 반환한다.
     * 
     * @param inStream
     * @return {@link BufferedReader} 객체, {@link InputStream}인 <code>null</code>인 경우 <code>null</code>반환.
     */
    public static BufferedReader getReader(InputStream inStream) {
        BufferedReader reader = null;

        if (inStream != null) {
            reader = new BufferedReader(new InputStreamReader(inStream));
        }

        return reader;
    }

    /**
     * {@link InputStream}을 가지고 {@link BufferedReader}를 생성해서 반환한다.
     * 
     * @param inStream
     * @return {@link BufferedReader} 객체, {@link InputStream}인 <code>null</code>인 경우 <code>null</code>반환.
     * 
     * @since 2014. 6. 24.
     */
    public static BufferedReader getReader(InputStream inStream, String charset) {
        BufferedReader reader = null;

        if (inStream != null) {
            try {
                reader = new BufferedReader(new InputStreamReader(inStream, charset));
            } catch (UnsupportedEncodingException e) {
            }
        }

        return reader;
    }

    /**
     * 주어진 문자열({@link String})을 읽어오는 {@link BufferedReader}를 반환한다.
     * 
     * @param string
     * @return <b><code>nullable</code></b>.
     * 
     * @since 2012. 01. 10.
     * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
     */
    public static BufferedReader getReader(String string) {
        BufferedReader reader = null;

        if (string != null) {
            reader = new BufferedReader(new StringReader(string));
        }

        return reader;
    }

    /**
     * 클래스가 포함되어 있는 리소스 경로를 반환한다.
     * 
     * @param container
     * @return <BR>
     * @since 2012. 03. 12.
     * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
     */
    public static String getResourcePath(Class<?> container) {
        if (container != null) {
            char[] clazzChars = container.getName().toCharArray();

            int[] indice = ArrayUtils.indiceOf(clazzChars, '.');

            for (int i : indice) {
                clazzChars[i] = '/';
            }

            return new String(ArrayUtils.prepend(clazzChars, '/'), 0, indice[indice.length - 1] + 1);
        } else {
            return null;
        }
    }

    /**
     * <code>container</code>와 같은 패키지에 존재하는 리소스에 대한 {@link InputStream}를 반환한다.
     * 
     * @param loader
     *            클래스 로더를 선택하는 클래스
     * @param container
     *            리소스와 같은 패키지에 존재하는 클래스
     * @param resourceName
     *            리소스 이름
     * @return <BR>
     * @since 2012. 03. 12.
     * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
     */
    public static InputStream getResourcePath(Class<?> loader, Class<?> container, String resourceName) {
        if (loader != null && container != null) {
            return loader.getResourceAsStream(getResourcePath(container) + "/" + resourceName);
        } else {
            return null;
        }
    }

    /**
     * 주어진 문자열({@link String})에 저장하는 {@link BufferedWriter}를 반환한다.
     * 
     * @return 예외가 발생하는 경우 <code>null</code>을 반환한다.
     * 
     * @since 2012. 01. 10.
     * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
     */
    public static BufferedWriter getWriter() {
        try {
            return new BufferedWriter(new StringWriter());
        } catch (Exception ignored) {
            return null;
        }
    }

    /**
     * 주어진 {@link File}에 저장하는 {@link BufferedWriter}를 반환한다.
     * 
     * @param file
     * @return 예외가 발생하는 경우 <code>null</code>을 반환한다.
     * @throws IOException
     * 
     * @since 2012. 01. 10.
     * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
     */
    public static BufferedWriter getWriter(File file) {
        try {
            return new BufferedWriter(new FileWriter(file));
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * {@link OutputStream}을 가지고 {@link BufferedWriter}를 생성해서 반환한다.
     * 
     * @param inStream
     * @return {@link BufferedWriter} 객체, {@link OutputStream}인 <code>null</code>인 경우 <code>null</code>반환.
     */
    public static BufferedWriter getWriter(OutputStream outStream) {
        BufferedWriter writer = null;

        if (outStream != null) {
            writer = new BufferedWriter(new OutputStreamWriter(outStream));
        }

        return writer;
    }

    /**
     * 
     * @param e
     * @return
     * @throws FileNotFoundException
     * @exception FileNotFoundException
     *                if the file does not exist, is a directory rather than a regular file, or for some other reason
     *                cannot be opened for reading.
     * @exception SecurityException
     *                if a security manager exists and its <code>checkRead</code> method denies read access to the file.
     * @see {@link FileInputStream} <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
     */
    public static SequenceInputStream newSequenceInputStream(Enumeration<File> e) throws FileNotFoundException {

        Vector<InputStream> v = new Vector<InputStream>();

        while (e.hasMoreElements()) {
            v.add(new FileInputStream(e.nextElement()));
        }

        return new SequenceInputStream(v.elements());
    }

    /**
     * 
     * @param e
     * @return
     * @throws FileNotFoundException
     * @exception FileNotFoundException
     *                if the file does not exist, is a directory rather than a regular file, or for some other reason
     *                cannot be opened for reading.
     * @exception SecurityException
     *                if a security manager exists and its <code>checkRead</code> method denies read access to the file.
     * @see {@link FileInputStream} <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
     */
    public static SequenceInputStream newSequenceInputStream(File... files) throws FileNotFoundException {

        Vector<InputStream> v = new Vector<InputStream>();

        for (File file : files) {
            v.add(new FileInputStream(file));
        }

        return new SequenceInputStream(v.elements());
    }

    /**
     * 
     * @param file1
     * @param file2
     * @return
     * @exception FileNotFoundException
     *                if the file does not exist, is a directory rather than a regular file, or for some other reason
     *                cannot be opened for reading.
     * @exception SecurityException
     *                if a security manager exists and its <code>checkRead</code> method denies read access to the file.
     * @see {@link FileInputStream}
     * 
     *      <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
     */
    @SuppressWarnings("resource")
    public static SequenceInputStream newSequenceInputStream(File file1, File file2) throws FileNotFoundException {
        InputStream is1 = new FileInputStream(file1);
        InputStream is2 = new FileInputStream(file2);

        return new SequenceInputStream(is1, is2);
    }

    /**
     * 
     * @param e
     * @return
     * @throws FileNotFoundException
     * @exception FileNotFoundException
     *                if the file does not exist, is a directory rather than a regular file, or for some other reason
     *                cannot be opened for reading.
     * @exception SecurityException
     *                if a security manager exists and its <code>checkRead</code> method denies read access to the file.
     * @see {@link FileInputStream} <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
     */
    public static SequenceInputStream newSequenceInputStream(InputStream... insts) throws FileNotFoundException {

        Vector<InputStream> v = new Vector<InputStream>();

        for (InputStream inst : insts) {
            v.add(inst);
        }

        return new SequenceInputStream(v.elements());
    }

    /**
     * 대상에 해당하는 파일이나 폴더를 연다.
     * 
     * <b>
     * 
     * <pre>
     * 지원
     * - Windows 기반 OS
     * </pre>
     * 
     * </b>
     * 
     * @param target
     *            <BR>
     * @since 2012. 01. 30.
     * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
     */
    public static void open(String target) {
        try {
            if (new File(target).exists()) {
                Process proc = Runtime.getRuntime().exec(COMMAND_OPEN + " " + target);
                InputStreamReader reader = new InputStreamReader(proc.getInputStream());

                while (reader.read() != -1)
                    ;
                proc.destroy();
            } else {
                System.err.println("There is not a file or a directory");
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * 
     * @param channel
     * @param length
     * @param close
     * @return
     *
     * @since 2015. 12. 10.
     */
    public static byte[] read(SocketChannel channel, final int length, boolean close) {

        ByteBuffer buf = ByteBuffer.allocateDirect(length);

        int total = 0;
        int count = 0;

        try {
            while (total < length && (count = channel.read(buf)) > 0) {
                total += count;
            }

            byte[] read = new byte[length];

            buf.clear();
            buf.get(read);

            return read;

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (close) {
                IOUtils.close(channel);
            }
        }

        return null;
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2012. 1. 10.		박준홍			최초 작성
     * </pre>
     *
     * @param inStream
     * @return
     *
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @since 2012. 01. 10.
     * 
     * @see <strike>sun.misc.IOUtils.readFully(InputStream, int, boolean)</strike>
     * @see #readFully(InputStream, boolean) since 1.6.5
     * @see #readFully(InputStream, int, boolean) since 1.6.5
     */
    public static byte[] readFully(InputStream inStream) {
        return readFully(inStream, true);
    }

    /**
     * 입력 스트림 내용을 전부 읽어 byte 배열로 반환한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 3. 21.		박준홍			최초 작성
     * </pre>
     *
     * @param inStream
     *            입력 스트림
     * @param close
     *            자동 close 여부
     * @return
     *
     * @since 2019. 3. 21.
     * @version 1.6.5
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @see #readFully(InputStream, int, boolean)
     */
    public static byte[] readFully(InputStream inStream, final boolean close) {
        return readFully(inStream, 1048576 /* 1024 * 1024 */, close);
    }

    /**
     * 입력 스트림 내용을 전부 읽어 byte 배열로 반환한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 3. 21.		박준홍			최초 작성
     * </pre>
     *
     * @param inStream
     *            입력 스트림
     * @param bufferSize
     *            읽기 버퍼 사이즈.
     * @param close
     *            자동 close 여부
     * @return
     *
     * @since 2019. 3. 21.
     * @version 1.6.5
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public static byte[] readFully(InputStream inStream, final int bufferSize, final boolean close) {

        ByteBuffer buf = ByteBuffer.allocateDirect(bufferSize);

        ReadableByteChannel reader = Channels.newChannel(inStream);

        int count = 0;
        try {
            ArrayList<byte[]> store = new ArrayList<>();
            byte[] bs = null;
            while ((count = reader.read(buf)) > 0 && count == bufferSize) {
                buf.clear();
                buf.get(bs = new byte[bufferSize]);
                store.add(bs);
            }

            // 버퍼에 남은 데이터 읽기
            if (count > 0) {
                buf.flip();
                buf.get(bs = new byte[count]);
                store.add(bs);
            }

            return ArrayUtils.merge(store.toArray(new byte[0][]));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (close) {
                IOUtils.close(inStream);
            }
        }

        return null;
    }

    /**
     * 입력받은 InputStream에서 주어진 길이만큼 데이터를 읽어서 반환한다.
     * 
     * @param inStream
     * @param length
     *            읽어올 데이터 길이
     * @return InputStream으로부터 읽어온 데이터. 예외가 발생하는 경우 <code>null</code> 반환.
     */
    public static byte[] readStream(InputStream inStream, final int length) {
        return readStream(inStream, length, true);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2017. 9. 6.		박준홍			최초 작성
     * </pre>
     *
     * @param inStream
     * @param length
     * @param close
     *            소켓 close 여부
     * @return
     *
     * @since 2017. 9. 6.
     */
    public static byte[] readStream(InputStream inStream, final int length, boolean close) {

        ByteBuffer buf = ByteBuffer.allocateDirect(length);

        ReadableByteChannel reader = Channels.newChannel(inStream);

        int total = 0;
        int count = 0;

        try {
            while (total < length && (count = reader.read(buf)) > 0) {
                total += count;
            }

            byte[] read = new byte[length];

            buf.clear();
            buf.get(read);

            return read;

        } catch (Throwable e) {
            e.printStackTrace();
        } finally {

            if (close) {
                IOUtils.close(inStream, reader);
            }
        }

        return null;
    }

    /**
     * {@link InputStream}의 내용을 {@link OutputStream} 으로 전송한다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 9. 10.     박준홍         최초 작성
     * </pre>
     *
     * @param inStream
     * @param closeInput
     *            {@link InputStream#close()} 호출 여부
     * @param outStream
     * @param closeOutput
     *            {@link OutputStream#close()} 호출 여부
     * @return
     * @throws IOException
     *
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @since 2018. 9. 10.
     * 
     * @see InputStream#close()
     * @see OutputStream#close()
     */
    public static int transfer(InputStream inStream, boolean closeInput, OutputStream outStream, boolean closeOutput) throws IOException {

        int rcvCount = 0;

        ReadableByteChannel reader = null;
        WritableByteChannel writer = null;

        try {

            reader = Channels.newChannel(inStream);
            writer = Channels.newChannel(outStream);

            ByteBuffer buf = ByteBuffer.allocateDirect(1024 * 10);

            int readCount = -1;
            while ((readCount = reader.read(buf)) > 0) {

                buf.flip();

                writer.write(buf);

                buf.clear();

                rcvCount += readCount;
            }

            return rcvCount;
        } finally {
            if (closeInput) {
                IOUtils.close(reader);
            }

            if (closeOutput) {
                IOUtils.close(writer);
            }
        }
    }

    /**
     * {@link InputStream}를 통해 얻은 데이터를 {@link Writer}로 전달한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 9. 26.     박준홍         최초 작성
     * </pre>
     *
     * @param inStream
     * @param inCharset
     *            Charset of an {@link InputStream}
     * @param closeInput
     *            {@link InputStream#close()} 호출 여부
     * @param writer
     * @param closeOutput
     *            {@link OutputStream#close()} 호출 여부
     * @return
     * @throws IOException
     *
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @since 2018. 9. 26.
     * 
     * @see #transfer(Reader, boolean, Writer, boolean)
     */
    public static int transfer(InputStream inStream, Charset inCharset, boolean closeInput, Writer writer, boolean closeOutput) throws IOException {
        return transfer(Channels.newReader(Channels.newChannel(inStream), inCharset.name()), closeInput, writer, closeOutput);
    }

    /**
     * {@link InputStream}의 내용을 {@link OutputStream} 으로 전송한다.<br>
     * 전송 후 {@link InputStream}, {@link OutputStream}은 모두 close 된다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2018. 9. 10.		박준홍			최초 작성
     * </pre>
     *
     * @param inStream
     * @param inCharset
     *            Charset of an {@link InputStream}
     * @param outStream
     * @param outCharset
     *            Charset of an {@link OutputStream}
     * @return
     * @throws IOException
     *
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @since 2018. 9. 10.
     * 
     * @see InputStream#close()
     * @see OutputStream#close()
     */
    public static int transfer(InputStream inStream, Charset inCharset, OutputStream outStream, Charset outCharset) throws IOException {
        return transfer(inStream, inCharset.name(), true, outStream, outCharset.name(), true);
    }

    /**
     * {@link InputStream}를 통해 얻은 데이터를 {@link Writer}로 전달한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2018. 9. 26.		박준홍			최초 작성
     * </pre>
     *
     * @param inStream
     * @param inCharset
     *            Charset of an {@link InputStream}
     * @param writer
     * @return
     * @throws IOException
     *
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @since 2018. 9. 26.
     * 
     * @see #transfer(Reader, boolean, Writer, boolean)
     */
    public static int transfer(InputStream inStream, Charset inCharset, Writer writer) throws IOException {
        return transfer(Channels.newReader(Channels.newChannel(inStream), inCharset.name()), true, writer, true);
    }

    /**
     * {@link InputStream}를 통해 얻은 데이터를 {@link Writer}로 전달한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 9. 26.     박준홍         최초 작성
     * </pre>
     *
     * @param inStream
     * @param inCharset
     *            Charset of an {@link InputStream}
     * @param writer
     * @param close
     *            {@link Reader#close()}, {@link OutputStream#close()} 호출 여부
     * @return
     * @throws IOException
     *
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @since 2018. 9. 26.
     * 
     * @see #transfer(Reader, boolean, Writer, boolean)
     */
    public static int transfer(InputStream inStream, Charset inCharset, Writer writer, boolean close) throws IOException {
        return transfer(Channels.newReader(Channels.newChannel(inStream), inCharset.name()), close, writer, close);
    }

    /**
     * {@link InputStream}의 내용을 {@link OutputStream} 으로 전송한다.<br>
     * 전송 후 {@link InputStream}, {@link OutputStream}은 모두 close 된다.
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2014. 4. 14.     박준홍         최초 작성
     * 2018. 9.10.      박준홍         내부 메소드 호출로 변경
     * </pre>
     * 
     * @param inStream
     * @param outStream
     * @return
     * @throws IOException
     *
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @since 2018. 9. 10.
     * 
     * @since 2014. 4. 14.
     * 
     * @see InputStream#close()
     * @see OutputStream#close()
     * @see #transfer(InputStream, boolean, OutputStream, boolean)
     */
    public static int transfer(InputStream inStream, OutputStream outStream) throws IOException {
        return transfer(inStream, true, outStream, true);
    }

    /**
     * {@link InputStream}의 내용을 {@link OutputStream} 으로 전송한다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 9. 10.     박준홍         최초 작성
     * </pre>
     *
     * @param inStream
     * @param outStream
     * @param close
     *            {@link OutputStream#close()} 호출 여부
     * @return
     * @throws IOException
     *
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @since 2018. 9. 10.
     * 
     * @see InputStream#close()
     * @see OutputStream#close()
     * @see #transfer(InputStream, boolean, OutputStream, boolean)
     */
    public static int transfer(InputStream inStream, OutputStream outStream, boolean close) throws IOException {
        return transfer(inStream, close, outStream, close);
    }

    /**
     * {@link InputStream}의 내용을 {@link OutputStream} 으로 전송한다.<br>
     * 전송 후 {@link InputStream}, {@link OutputStream}은 모두 close 된다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2018. 9. 10.		박준홍			최초 작성
     * </pre>
     *
     * @param inStream
     * @param outStream
     * @param charset
     * @return
     * @throws IOException
     *
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @since 2018. 9. 10.
     * 
     * @see InputStream#close()
     * @see OutputStream#close()
     * @see #transfer(InputStream, String, boolean, OutputStream, String, boolean)
     * 
     */
    public static int transfer(InputStream inStream, OutputStream outStream, Charset charset) throws IOException {
        return transfer(inStream, charset.name(), true, outStream, charset.name(), true);
    }

    /**
     * {@link InputStream}의 내용을 {@link OutputStream} 으로 전송한다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 9. 10.     박준홍         최초 작성
     * </pre>
     *
     * @param inStream
     * @param outStream
     * @param charset
     *            Charset of an {@link OutputStream}
     * @param close
     *            {@link OutputStream#close()} 호출 여부
     * @return
     * @throws IOException
     *
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @since 2018. 9. 10.
     * 
     * @see InputStream#close()
     * @see OutputStream#close()
     * @see #transfer(InputStream, String, boolean, OutputStream, String, boolean)
     */
    public static int transfer(InputStream inStream, OutputStream outStream, Charset charset, boolean close) throws IOException {
        return transfer(inStream, charset.name(), close, outStream, charset.name(), close);
    }

    /**
     * {@link InputStream}의 내용을 {@link OutputStream} 으로 전송한다.<br>
     * 전송 후 {@link InputStream}, {@link OutputStream}은 모두 close 된다.
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2014. 4. 14.     박준홍         최초 작성
     * 2018. 9.10.      박준홍         내부 메소드 호출로 변경
     * </pre>
     * 
     * @param inStream
     * @param outStream
     * @param charset
     *            charset
     * @return
     * @throws IOException
     * 
     * @since 2014. 4. 14.
     * 
     * @see InputStream#close()
     * @see OutputStream#close()
     * @see #transfer(InputStream, String, boolean, OutputStream, String, boolean)
     * 
     */
    public static int transfer(InputStream inStream, OutputStream outStream, String charset) throws IOException {
        return transfer(inStream, charset, true, outStream, charset, true);
    }

    /**
     * {@link InputStream}의 내용을 {@link OutputStream} 으로 전송한다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 9. 10.     박준홍         최초 작성
     * </pre>
     *
     * @param inStream
     * @param outStream
     * @param charset
     *            Charset of an {@link OutputStream}
     * @param close
     *            {@link OutputStream#close()} 호출 여부
     * @return
     * @throws IOException
     *
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @since 2018. 9. 10.
     * 
     * @see InputStream#close()
     * @see OutputStream#close()
     * @see #transfer(InputStream, String, boolean, OutputStream, String, boolean)
     */
    public static int transfer(InputStream inStream, OutputStream outStream, String charset, boolean close) throws IOException {
        return transfer(inStream, charset, close, outStream, charset, close);
    }

    /**
     * {@link InputStream}의 내용을 {@link OutputStream} 으로 전송한다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 9. 10.     박준홍         최초 작성
     * </pre>
     *
     * @param inStream
     * @param inCharset
     *            Charset of an {@link InputStream}
     * @param closeInput
     *            {@link InputStream#close()} 호출 여부
     * @param outStream
     * @param outCharset
     *            Charset of an {@link OutputStream}
     * @param closeOutput
     *            {@link OutputStream#close()} 호출 여부
     * @return
     * @throws IOException
     *
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @since 2018. 9. 10.
     * 
     * @see InputStream#close()
     * @see OutputStream#close()
     * @see #transfer(Reader, boolean, Writer, boolean)
     */
    public static int transfer(InputStream inStream, String inCharset, boolean closeInput, OutputStream outStream, String outCharset, boolean closeOutput) throws IOException {
        return transfer(Channels.newReader(Channels.newChannel(inStream), inCharset), closeInput, Channels.newWriter(Channels.newChannel(outStream), outCharset), closeOutput);
    }

    /**
     * {@link InputStream}를 통해 얻는 데이터를 {@link Writer}로 전달한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2018. 9. 26.		박준홍			최초 작성
     * </pre>
     *
     * @param inStream
     * @param inCharset
     *            Charset of an {@link InputStream}
     * @param closeInput
     *            {@link InputStream#close()} 호출 여부
     * @param writer
     * @param closeOutput
     *            {@link OutputStream#close()} 호출 여부
     * @return
     * @throws IOException
     *
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @since 2018. 9. 26.
     */
    public static int transfer(InputStream inStream, String inCharset, boolean closeInput, Writer writer, boolean closeOutput) throws IOException {
        return transfer(Channels.newReader(Channels.newChannel(inStream), inCharset), closeInput, writer, closeOutput);
    }

    /**
     * {@link InputStream}의 내용을 {@link OutputStream} 으로 전송한다.<br>
     * 전송 후 {@link InputStream}, {@link OutputStream}은 모두 close 된다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2018. 9. 10.		박준홍			최초 작성
     * </pre>
     *
     * @param inStream
     * @param inCharset
     *            Charset of an {@link InputStream}
     * @param outStream
     * @param outCharset
     *            Charset of an {@link OutputStream}
     * @return
     * @throws IOException
     *
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @since 2018. 9. 10.
     * 
     * @see InputStream#close()
     * @see OutputStream#close()
     */
    public static int transfer(InputStream inStream, String inCharset, OutputStream outStream, String outCharset) throws IOException {
        return transfer(inStream, inCharset, true, outStream, outCharset, true);
    }

    /**
     * {@link InputStream}를 통해 얻은 데이터를 {@link Writer}로 전달한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 9. 26.     박준홍         최초 작성
     * </pre>
     *
     * @param inStream
     * @param inCharset
     *            Charset of an {@link InputStream}
     * @param writer
     * @return
     * @throws IOException
     *
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @since 2018. 9. 26.
     * 
     * @see #transfer(Reader, boolean, Writer, boolean)
     */
    public static int transfer(InputStream inStream, String inCharset, Writer writer) throws IOException {
        return transfer(Channels.newReader(Channels.newChannel(inStream), inCharset), true, writer, true);
    }

    /**
     * {@link InputStream}를 통해 얻은 데이터를 {@link Writer}로 전달한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 9. 26.     박준홍         최초 작성
     * </pre>
     *
     * @param inStream
     * @param inCharset
     *            Charset of an {@link InputStream}
     * @param writer
     * @param close
     *            {@link Reader#close()}, {@link OutputStream#close()} 호출 여부
     * @return
     * @throws IOException
     *
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @since 2018. 9. 26.
     * 
     * @see #transfer(Reader, boolean, Writer, boolean)
     */
    public static int transfer(InputStream inStream, String inCharset, Writer writer, boolean close) throws IOException {
        return transfer(Channels.newReader(Channels.newChannel(inStream), inCharset), close, writer, close);
    }

    /**
     * {@link Reader}를 통해 얻은 데이타를 {@link OutputStream}으로 전달한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2018. 9. 26.		박준홍			최초 작성
     * </pre>
     *
     * @param reader
     * @param closeInput
     *            {@link InputStream#close()} 호출 여부
     * @param outStream
     * @param outCharset
     *            Charset of an {@link OutputStream}
     * @param closeOutput
     *            {@link OutputStream#close()} 호출 여부
     * @return
     * @throws IOException
     *
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @since 2018. 9. 26.
     * 
     * @see #transfer(Reader, boolean, Writer, boolean)
     */
    public static int transfer(Reader reader, boolean closeInput, OutputStream outStream, Charset outCharset, boolean closeOutput) throws IOException {
        return transfer(reader, closeInput, Channels.newWriter(Channels.newChannel(outStream), outCharset.name()), closeOutput);
    }

    /**
     * {@link Reader}를 통해 얻는 데이터를 {@link OutputStream}로 전달한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 9. 26.     박준홍         최초 작성
     * </pre>
     *
     * @param reader
     * @param closeInput
     *            {@link InputStream#close()} 호출 여부
     * @param outStream
     * @param outCharset
     *            Charset of an {@link OutputStream}
     * @param closeOutput
     *            {@link OutputStream#close()} 호출 여부
     * @return
     * @throws IOException
     *
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @since 2018. 9. 26.
     * 
     * @see #transfer(Reader, boolean, Writer, boolean)
     */
    public static int transfer(Reader reader, boolean closeInput, OutputStream outStream, String outCharset, boolean closeOutput) throws IOException {
        return transfer(reader, closeInput, Channels.newWriter(Channels.newChannel(outStream), outCharset), closeOutput);
    }

    /**
     * {@link Reader}를 통해 얻는 데이터를 {@link Writer}로 전달한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2018. 9. 26.		박준홍			최초 작성
     * </pre>
     *
     * @param reader
     * @param closeInput
     *            {@link InputStream#close()} 호출 여부
     * @param writer
     * @param closeOutput
     *            {@link OutputStream#close()} 호출 여부
     * @return
     * @throws IOException
     *
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @since 2018. 9. 26.
     */
    public static int transfer(Reader reader, boolean closeInput, Writer writer, boolean closeOutput) throws IOException {

        int rcvCount = 0;

        try {

            CharBuffer buf = CharBuffer.allocate(1024 * 10);

            int readCount = -1;
            while ((readCount = reader.read(buf)) > 0) {

                buf.flip();

                writer.write(buf.array());

                buf.clear();

                rcvCount += readCount;
            }
        } finally {
            if (closeInput) {
                IOUtils.close(reader);
            }

            if (closeOutput) {
                IOUtils.close(writer);
            }
        }

        return rcvCount;
    }

    /**
     * {@link Reader}를 통해 얻는 데이터를 {@link OutputStream}으로 전달한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2018. 9. 26.		박준홍			최초 작성
     * </pre>
     *
     * @param reader
     * @param outStream
     * @param outCharset
     *            Charset of an {@link OutputStream}
     * @return
     * @throws IOException
     *
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @since 2018. 9. 26.
     * 
     * @see #transfer(Reader, boolean, Writer, boolean)
     */
    public static int transfer(Reader reader, OutputStream outStream, Charset outCharset) throws IOException {
        return transfer(reader, true, Channels.newWriter(Channels.newChannel(outStream), outCharset.name()), true);
    }

    /**
     * {@link Reader}를 통해 얻은 데이터를 {@link OutputStream}으로 전달한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2018. 9. 26.		박준홍			최초 작성
     * </pre>
     *
     * @param reader
     * @param outStream
     * @param outCharset
     *            Charset of an {@link OutputStream}
     * @param close
     *            {@link Reader#close()}, {@link OutputStream#close()} 호출 여부
     * @return
     * @throws IOException
     *
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @since 2018. 9. 26.
     * 
     * @see #transfer(Reader, boolean, Writer, boolean)
     */
    public static int transfer(Reader reader, OutputStream outStream, Charset outCharset, boolean close) throws IOException {
        return transfer(reader, close, Channels.newWriter(Channels.newChannel(outStream), outCharset.name()), close);
    }

    /**
     * {@link Reader}를 통해 얻은 데이타를 {@link OutputStream}으로 전달한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2018. 9. 26.		박준홍			최초 작성
     * </pre>
     *
     * @param reader
     * @param outStream
     * @param outCharset
     *            Charset of an {@link OutputStream}
     * @return
     * @throws IOException
     *
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @since 2018. 9. 26.
     * 
     * @see #transfer(Reader, boolean, Writer, boolean)
     */
    public static int transfer(Reader reader, OutputStream outStream, String outCharset) throws IOException {
        return transfer(reader, true, Channels.newWriter(Channels.newChannel(outStream), outCharset), true);
    }

    /**
     * {@link Reader}를 통해 얻은 데이타를 {@link OutputStream}으로 전달한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2018. 9. 26.		박준홍			최초 작성
     * </pre>
     *
     * @param reader
     * @param outStream
     * @param outCharset
     *            Charset of an {@link OutputStream}
     * @param close
     *            {@link Reader#close()}, {@link OutputStream#close()} 호출 여부
     * @return
     * @throws IOException
     *
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @since 2018. 9. 26.
     * 
     * @see #transfer(Reader, boolean, Writer, boolean)
     */
    public static int transfer(Reader reader, OutputStream outStream, String outCharset, boolean close) throws IOException {
        return transfer(reader, close, Channels.newWriter(Channels.newChannel(outStream), outCharset), close);
    }
}
