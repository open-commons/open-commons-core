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
*/
package open.commons.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.io.SequenceInputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ClosedByInterruptException;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Vector;
import java.util.function.Function;
import java.util.function.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import open.commons.CommonProperties;
import open.commons.Result;
import open.commons.io.IRandomAccessible;
import open.commons.util.ArrayItr;

/**
 * 
 * 
 * <BR>
 * 
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 * @since 2012. 01. 10.
 * 
 */
public class IOUtils {

    private static Logger logger = LoggerFactory.getLogger(IOUtils.class);

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
     * byte[] 데이터를 제공한다.
     * 
     * @param i
     *            줄 번호
     * @param bs
     *            byte[] 형태의 줄 데이터
     */
    private static final Function<byte[], byte[]> BYTE_ACTION_BYPASS = bs -> bs;

    public static final int BUFFER_SIZE_32B = 32;
    public static final int BUFFER_SIZE_1KB = 1024;
    public static final int BUFFER_SIZE_1MB = 1024 * 1024;
    public static final int BUFFER_SIZE_10MB = 1024 * 1024 * 10;
    public static final int BUFFER_SIZE_1GB = 1024 * 1024 * 1024;

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
     * {@link AutoCloseable}를 모두 닫는다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 7. 5.		박준홍			최초 작성
     * </pre>
     *
     * 
     * @param closeables
     *            {@link AutoCloseable} 객체들.
     *
     * @since 2021. 7. 5.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static void close(Collection<AutoCloseable> closeables) {
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
     * 주어진 {@link File}을 읽어오는 {@link BufferedReader}를 반환한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2012. 01. 10.        박준홍     최초 작성
     * 2020. 9. 25.         박준홍     {@link Path} 메소드로 전환
     * </pre>
     * 
     * @param file
     * @return {@link File}이 <code>null</code> 이거나 에러가 발생할 경우 <code>null</code>을 제공한다.
     * 
     * @since 2012. 01. 10.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static BufferedReader getReader(File file) {
        return file != null ? getReader(file.toPath(), (Charset) null) : null;
    }

    /**
     * 주어진 {@link File}을 읽어오는 {@link BufferedReader}를 반환한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 9. 25.		박준홍			최초 작성
     * </pre>
     *
     * @param file
     *            파일 객체
     * @param cs
     *            file character set
     * @return {@link File}이 <code>null</code> 이거나 에러가 발생할 경우 <code>null</code>을 제공한다.
     *
     * @since 2020. 9. 25.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static BufferedReader getReader(File file, Charset cs) {
        return file != null ? getReader(file.toPath(), cs) : null;
    }

    /**
     * 주어진 {@link File}을 읽어오는 {@link BufferedReader}를 반환한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 9. 25.		박준홍			최초 작성
     * </pre>
     *
     * @param file
     * @param charsetNam
     * @return {@link File}이 <code>null</code> 이거나 에러가 발생할 경우 <code>null</code>을 제공한다.
     *
     * @since 2020. 9. 25.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static BufferedReader getReader(File file, String charsetNam) {
        return file != null ? getReader(file.toPath(), charsetNam) : null;
    }

    /**
     * {@link InputStream}을 가지고 {@link BufferedReader}를 생성해서 반환한다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2014. 6. 24.         박준홍     최초 작성
     * 2020. 9. 25.		   박준홍     내부 구현 변경.
     * </pre>
     * 
     * @param inStream
     * @return {@link BufferedReader} 객체, {@link InputStream}인 <code>null</code>인 경우 <code>null</code>반환.
     */
    public static BufferedReader getReader(InputStream inStream) {
        return getReader(inStream, (Charset) null);
    }

    /**
     * {@link InputStream}을 가지고 {@link BufferedReader}를 생성해서 반환한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 9. 25.		박준홍			최초 작성
     * </pre>
     *
     * @param inStream
     * @param cs
     * @return {@link BufferedReader} 객체, {@link InputStream}인 <code>null</code>인 경우 <code>null</code>반환.
     *
     * @since 2020. 9. 25.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static BufferedReader getReader(InputStream inStream, Charset cs) {
        BufferedReader reader = null;

        if (inStream != null) {
            reader = new BufferedReader(cs != null ? new InputStreamReader(inStream, cs) : new InputStreamReader(inStream));
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
    public static BufferedReader getReader(InputStream inStream, String charsetName) {
        return getReader(inStream, charsetName != null ? Charset.forName(charsetName) : null);
    }

    /**
     * {@link Path} 를 이용하여 {@link BufferedReader} 를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 9. 25.		박준홍			최초 작성
     * </pre>
     *
     * @param path
     *            파일 경로
     * @return 에러가 발생할 경우 <code>null</code>을 제공한다.
     *
     * @since 2020. 9. 25.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static BufferedReader getReader(Path path) {
        return getReader(path, (Charset) null);
    }

    /**
     * {@link Path} 를 이용하여 {@link BufferedReader} 를 제공한다. <br>
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 9. 25.		박준홍			최초 작성
     * </pre>
     *
     * @param path
     * @param cs
     *            file character set
     * @return {@link Path} 가 <code>null</code> 이거나 에러가 발생할 경우 <code>null</code>을 제공한다.
     *
     * @since 2020. 9. 25.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static BufferedReader getReader(Path path, Charset cs) {
        BufferedReader reader = null;

        if (path != null) {
            try {
                reader = getReader(Files.newInputStream(path), cs);
            } catch (IOException e) {
                logger.warn("reader 생성시 에러가 발생하였습니다. 원인={}", e.getMessage());
            }
        }

        return reader;
    }

    /**
     * {@link Path} 를 이용하여 {@link BufferedReader} 를 제공한다. <br>
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 9. 25.		박준홍			최초 작성
     * </pre>
     *
     * @param path
     * @param charsetName
     *            file character set name
     * @return 에러가 발생할 경우 <code>null</code>을 제공한다.
     *
     * @since 2020. 9. 25.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static BufferedReader getReader(Path path, String charsetName) {
        return getReader(path, charsetName != null ? Charset.forName(charsetName) : (Charset) null);
    }

    /**
     * 주어진 문자열({@link String})을 읽어오는 {@link BufferedReader}를 반환한다.
     * 
     * @param string
     * @return <b><code>nullable</code></b>.
     * 
     * @since 2012. 01. 10.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
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
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
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
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
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
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
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
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
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
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
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
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
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
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
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
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
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
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
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
     * 텍스트 파일을 줄단위로 읽어서 지정된 형태의 데이터를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 11. 13.		박준홍			최초 작성
     * </pre>
     *
     * @param <T>
     *            생성할 데이터 타입.
     * @param channel
     *            파일에 연결된 {@link FileChannel}
     * @param action
     *            줄 데이터를 읽어서 데이터를 제공하는 함수
     * @return
     * @throws IOException
     *
     * @since 2020. 11. 13.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <T, R extends IRandomAccessible> List<T> readChannel(FileChannel channel, Function<byte[], T> action, Iterable<R> accessibles) throws IOException {
        List<T> data = new ArrayList<>();
        T datum = null;

        Iterator<R> itr = accessibles.iterator();
        R access = null;
        if (itr.hasNext()) {
            access = itr.next();
            channel.position(access.getPosition());
            do {
                datum = readChannel(channel, access.getLength(), ByteBuffer.allocate(access.getLength()), action);
                data.add(datum);
            } while (itr.hasNext() && (access = itr.next()) != null);
        }

        return data;
    }

    /**
     * 텍스트 파일을 지정된 위치의 데이터를 읽어서 지정된 형태의 데이터로 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 11. 13.		박준홍			최초 작성
     * </pre>
     *
     * @param <T>
     *            생성할 데이터 타입.
     * @param channel
     *            파일에 연결된 {@link FileChannel}
     * @param len
     *            읽을 byte 길이
     * @param buf
     *            데이터 버퍼
     * @param action
     *            줄 데이터를 읽어서 데이터를 제공하는 함수
     * @return
     * @throws IOException
     *
     * @since 2020. 11. 13.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    private static <T> T readChannel(FileChannel channel, int len, ByteBuffer buf, Function<byte[], T> action) throws IOException {
        byte[] bs = new byte[len];
        channel.read(buf);
        buf.flip();
        buf.get(bs);
        buf.clear();

        return action.apply(bs);
    }

    /**
     * 텍스트 파일을 줄단위로 읽어서 지정된 형태의 데이터를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 11. 13.		박준홍			최초 작성
     * </pre>
     *
     * @param <T>
     *            생성할 데이터 타입.
     * @param channel
     *            파일에 연결된 {@link FileChannel}
     * @param bufCapacity
     *            줄 데이터를 읽을 버퍼 크기
     * @param action
     *            줄 데이터를 읽어서 데이터를 제공하는 함수
     * @return
     * @throws IOException
     *
     * @since 2020. 11. 13.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    @SafeVarargs
    public static <T, R extends IRandomAccessible> List<T> readChannel(FileChannel channel, int bufCapacity, Function<byte[], T> action, R... accessibles) throws IOException {
        return readChannel(channel, action, new ArrayItr<>(accessibles));
    }

    /**
     * 텍스트 파일을 줄단위로 읽어서 지정된 형태의 데이터를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 11. 13.		박준홍			최초 작성
     * </pre>
     *
     * @param <T>
     *            생성할 데이터 타입.
     * @param file
     *            파일
     * @param action
     *            줄 데이터를 읽어서 데이터를 제공하는 함수
     * @param accessibles
     *            줄단위 메타데이터
     * @return
     * @throws IOException
     *
     * @since 2020. 11. 13.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <T, R extends IRandomAccessible> Result<List<T>> readFile(File file, Function<byte[], T> action, Iterable<R> accessibles) throws IOException {
        return readFile(new RandomAccessFile(file, "r"), action, accessibles);
    }

    /**
     * 텍스트 파일을 줄단위로 읽어서 지정된 형태의 데이터를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 11. 13.		박준홍			최초 작성
     * </pre>
     *
     * @param <T>
     *            생성할 데이터 타입.
     * @param file
     *            파일
     * @param action
     *            줄 데이터를 읽어서 데이터를 제공하는 함수
     * @param accessibles
     *            줄단위 메타데이터
     * @return
     * @throws IOException
     *
     * @since 2020. 11. 13.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    @SafeVarargs
    public static <T, R extends IRandomAccessible> Result<List<T>> readFile(File file, Function<byte[], T> action, R... accessibles) throws IOException {
        return readFile(file, action, new ArrayItr<R>(accessibles));
    }

    /**
     * 텍스트 파일을 줄단위로 읽어서 지정된 형태의 데이터를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 11. 13.		박준홍			최초 작성
     * </pre>
     *
     * @param file
     *            파일
     * @param accessibles
     *            줄단위 메타데이터
     * @return
     * @throws IOException
     *
     * @since 2020. 11. 13.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <R extends IRandomAccessible> Result<List<byte[]>> readFile(File file, Iterable<R> accessibles) throws IOException {
        return readFile(file, BYTE_ACTION_BYPASS, accessibles);
    }

    /**
     * 텍스트 파일을 줄단위로 읽어서 지정된 형태의 데이터를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 11. 13.		박준홍			최초 작성
     * </pre>
     *
     * @param file
     *            파일
     * @param accessibles
     *            줄단위 메타데이터
     * @return
     * @throws IOException
     *
     * @since 2020. 11. 13.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    @SafeVarargs
    public static <R extends IRandomAccessible> Result<List<byte[]>> readFile(File file, R... accessibles) throws IOException {
        return readFile(file, BYTE_ACTION_BYPASS, new ArrayItr<R>(accessibles));
    }

    /**
     * 텍스트 파일을 지정된 위치의 데이터를 읽어서 지정된 형태의 데이터로 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 11. 13.		박준홍			최초 작성
     * </pre>
     *
     * @param file
     *            파일
     * @param accessible
     *            줄단위 메타데이터
     * @return
     * @throws IOException
     *
     * @since 2020. 11. 13.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <R extends IRandomAccessible> Result<byte[]> readFile(File file, R accessible) throws IOException {
        return readFile(new RandomAccessFile(file, "r"), accessible);
    }

    /**
     * 텍스트 파일을 줄단위로 읽어서 지정된 형태의 데이터를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 11. 13.		박준홍			최초 작성
     * </pre>
     *
     * @param <T>
     *            줄 데이터(byte[])를 이용하여 생성할 데이터 타입.
     * @param file
     *            파일
     * @param action
     *            데이터 생성 함수
     * @param accessibles
     *            줄단위 메타데이어
     * @return
     * @throws IOException
     *
     * @since 2020. 11. 13.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <T, R extends IRandomAccessible> Result<List<T>> readFile(RandomAccessFile file, Function<byte[], T> action, Iterable<R> accessibles) throws IOException {
        List<T> data = null;
        boolean result = true;
        String message = null;
        try (FileChannel channel = file.getChannel()) {
            data = readChannel(channel, action, accessibles);
        } catch (Exception e) {
            result = false;
            message = String.format("예외타입=%s, 원인=%s", e.getClass(), e.getMessage());

            logger.error("예상치 못한 에러가 발생하였습니다. 원인={}", e.getMessage(), e);

            e.printStackTrace();
        }

        return new Result<>(data, result).setMessage(message);
    }

    /**
     * 텍스트 파일을 줄단위로 읽어서 지정된 형태의 데이터를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 11. 13.		박준홍			최초 작성
     * </pre>
     *
     * @param <T>
     *            줄 데이터(byte[])를 이용하여 생성할 데이터 타입.
     * @param file
     *            파일
     * @param action
     *            데이터 생성 함수
     * @param accessibles
     *            줄단위 메타데이어
     * @return
     * @throws IOException
     *
     * @since 2020. 11. 13.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    @SafeVarargs
    public static <T, R extends IRandomAccessible> Result<List<T>> readFile(RandomAccessFile file, Function<byte[], T> action, R... accessibles) throws IOException {
        return readFile(file, action, new ArrayItr<R>(accessibles));
    }

    /**
     * 텍스트 파일을 지정된 위치의 데이터를 읽어서 지정된 형태의 데이터로 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 11. 13.		박준홍			최초 작성
     * </pre>
     *
     * @param <T>
     * @param file
     *            파일
     * @param action
     *            데이터 생성 함수
     * @param accessibles
     *            줄단위 메타데이어
     * @return
     * @throws IOException
     *
     * @since 2020. 11. 13.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <T, R extends IRandomAccessible> Result<T> readFile(RandomAccessFile file, Function<byte[], T> action, R accessibles) throws IOException {

        boolean result = true;
        String message = null;
        T data = null;

        ByteBuffer buf = null;
        int len = accessibles.getLength();

        try (FileChannel channel = file.getChannel()) {
            buf = ByteBuffer.allocateDirect(len);
            channel.position(accessibles.getPosition());
            data = readChannel(channel, len, buf, action);
        } catch (Exception e) {
            result = false;
            message = String.format("예외타입=%s, 원인=%s", e.getClass(), e.getMessage());
            logger.error("예상치 못한 에러가 발생하였습니다. 원인={}", e.getMessage(), e);
        }

        return new Result<>(data, result).setMessage(message);
    }

    /**
     * 텍스트 파일을 지정된 위치의 데이터를 읽어서 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 11. 13.		박준홍			최초 작성
     * </pre>
     *
     * @param file
     *            파일
     * @param accessible
     *            줄단위 메타데이어
     * @return
     * @throws IOException
     *
     * @since 2020. 11. 13.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <R extends IRandomAccessible> Result<byte[]> readFile(RandomAccessFile file, R accessible) throws IOException {
        return readFile(file, BYTE_ACTION_BYPASS, accessible);
    }

    /**
     * 텍스트 파일을 줄단위로 읽어서 지정된 형태의 데이터를 제공한다. <br>
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 11. 13.		박준홍			최초 작성
     * </pre>
     *
     * @param <T>
     *            줄 데이터(byte[])를 이용하여 생성할 데이터 타입.
     * @param file
     *            파일
     * @param action
     *            데이터 생성 함수
     * @param accessibles
     *            줄단위 메타데이어
     * @return
     * @throws IOException
     *
     * @since 2020. 11. 13.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <T, R extends IRandomAccessible> Result<List<T>> readFile(String file, Function<byte[], T> action, Iterable<R> accessibles) throws IOException {
        return readFile(new RandomAccessFile(file, "r"), action, accessibles);
    }

    /**
     * 텍스트 파일을 줄단위로 읽어서 지정된 형태의 데이터를 제공한다. <br>
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 11. 13.		박준홍			최초 작성
     * </pre>
     *
     * @param <T>
     *            줄 데이터(byte[])를 이용하여 생성할 데이터 타입.
     * @param file
     *            파일
     * @param action
     *            데이터 생성 함수
     * @param accessibles
     *            줄단위 메타데이어
     * @return
     * @throws IOException
     *
     * @since 2020. 11. 13.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    @SafeVarargs
    public static <T, R extends IRandomAccessible> Result<List<T>> readFile(String file, Function<byte[], T> action, R... accessibles) throws IOException {
        return readFile(file, action, new ArrayItr<R>(accessibles));
    }

    /**
     * 텍스트 파일을 줄단위로 읽어서 지정된 형태의 데이터를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 11. 13.		박준홍			최초 작성
     * </pre>
     *
     * @param file
     *            파일
     * @param accessibles
     *            줄단위 메타데이터
     * @return
     * @throws IOException
     *
     * @since 2020. 11. 13.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <R extends IRandomAccessible> Result<List<byte[]>> readFile(String file, Iterable<R> accessibles) throws IOException {
        return readFile(file, BYTE_ACTION_BYPASS, accessibles);
    }

    /**
     * 텍스트 파일을 줄단위로 읽어서 지정된 형태의 데이터를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 11. 13.		박준홍			최초 작성
     * </pre>
     *
     * @param file
     *            파일
     * @param accessibles
     *            줄단위 메타데이터
     * @return
     * @throws IOException
     *
     * @since 2020. 11. 13.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    @SafeVarargs
    public static <R extends IRandomAccessible> Result<List<byte[]>> readFile(String file, R... accessibles) throws IOException {
        return readFile(file, BYTE_ACTION_BYPASS, new ArrayItr<R>(accessibles));
    }

    /**
     * 텍스트 파일을 지정된 위치의 데이터를 읽어서 지정된 형태의 데이터로 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 11. 13.		박준홍			최초 작성
     * </pre>
     *
     * @param file
     *            파일
     * @param accessible
     *            줄단위 메타데이터
     * @return
     * @throws IOException
     *
     * @since 2020. 11. 13.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <R extends IRandomAccessible> Result<byte[]> readFile(String file, R accessible) throws IOException {
        return readFile(new RandomAccessFile(file, "r"), accessible);
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
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
     * 2019. 8. 29.     박준홍         내부 로직 성능 향상 및 안정성 강화
     * 2020. 9. 13.     박준홍         Channel 처리 함수를 별도 분리 후, 내부 호출
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static byte[] readFully(InputStream inStream, final int bufferSize, final boolean close) {
        return readFully(Channels.newChannel(inStream), bufferSize, close);
    }

    /**
     * 채널에 있는 데이터를 읽어 byte 배열로 반환한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 9. 13.		박준홍			최초 작성
     * </pre>
     *
     * @param channel
     * @param bufferSize
     * @param close
     * @return
     *
     * @since 2020. 9. 13.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static byte[] readFully(ReadableByteChannel channel, final int bufferSize, final boolean close) {

        ByteBuffer buf = ByteBuffer.allocateDirect(bufferSize);

        int count = 0;
        try {
            ArrayList<byte[]> store = new ArrayList<>();
            byte[] bs = null;
            while ((count = channel.read(buf)) > 0) {
                buf.flip();
                buf.get(bs = new byte[count]);
                store.add(bs);
                buf.clear();
            }

            // 버퍼에 남은 데이터 읽기
            if (count > 0) {
                buf.flip();
                buf.get(bs = new byte[count]);
                store.add(bs);
            }

            return ArrayUtils.merge(store.toArray(new byte[0][]));
        } catch (ClosedByInterruptException e) {
            logger.info("클라이언트와의 연결이 해제되었습니다. detail={}", e.getMessage());
        } catch (IOException e) {
            logger.info("I/O 에러가 발생하였습니다. detail={}", e.getMessage());
        } catch (Throwable e) {
            logger.warn("데이터 읽는 도중 에러가 발생하였습니다. detail={}", e.getMessage(), e);
        } finally {
            if (close) {
                IOUtils.close(channel);
            }
        }

        return null;
    }

    /**
     * 주어진 파일을 줄단위로 읽어서 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 2. 8.		박준홍			최초 작성
     * </pre>
     *
     * @param file
     *            읽을 파일
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     *
     * @since 2020. 2. 8.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @see BufferedReader
     */
    public static List<String> readLines(File file) throws FileNotFoundException, IOException {
        return readLines(file, Charset.defaultCharset());
    }

    /**
     * 주어진 파일을 줄단위로 읽어서 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 2. 8.		박준홍			최초 작성
     * </pre>
     *
     * @param file
     *            읽을 파일
     * @param charset
     *            문자열 셋
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     *
     * @since 2020. 2. 8.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static List<String> readLines(File file, Charset charset) throws FileNotFoundException, IOException {
        return readLines(file, charset, -1);
    }

    /**
     * 주어진 파일을 줄단위로 읽어서 요청한 줄수 또는 전체(요청한 줄수가 전체 라인보다 큰 경우)를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 10.		박준홍			최초 작성
     * </pre>
     *
     * @param file
     *            읽을 파일
     * @param charset
     *            문자열 셋
     * @param lineCount
     *            읽으려는 줄 수
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     *
     * @since 2021. 11. 10.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static List<String> readLines(File file, Charset charset, long lineCount) throws FileNotFoundException, IOException {
        return readLines(new FileInputStream(file), charset, lineCount);
    }

    /**
     * 주어진 파일을 줄단위로 읽어서 요청한 줄수 또는 전체(요청한 줄수가 전체 라인보다 큰 경우)를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 10.        박준홍         최초 작성
     * </pre>
     *
     * @param file
     *            읽을 파일
     * @param lineCount
     *            읽으려는 줄 수
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     *
     * @since 2021. 11. 10.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static List<String> readLines(File file, long lineCount) throws FileNotFoundException, IOException {
        return readLines(file, Charset.defaultCharset(), lineCount);
    }

    /**
     * 주어진 파일을 줄단위로 읽어서 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 10.      박준홍         최초 작성
     * </pre>
     *
     * @param file
     *            읽을 파일
     * @param charsetName
     *            문자열 셋
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 10.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static List<String> readLines(File file, String charsetName) throws IOException {
        return readLines(file, charsetName, -1);
    }

    /**
     * 주어진 파일을 줄단위로 읽어서 요청한 줄수 또는 전체(요청한 줄수가 전체 라인보다 큰 경우)를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 10.        박준홍         최초 작성
     * </pre>
     *
     * @param file
     *            읽을 파일
     * @param lineCount
     *            읽으려는 줄 수
     * @param charset
     *            문자열 셋
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 10.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static List<String> readLines(File file, String charsetName, long lineCount) throws IOException {
        Charset charset = Charset.isSupported(charsetName) //
                ? Charset.forName(charsetName) //
                : Charset.defaultCharset();
        return readLines(file, charset, lineCount);
    }

    /**
     * 주어진 파일을 줄단위로 읽어서 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 2. 8.		박준홍			최초 작성
     * </pre>
     *
     * @param inStream
     * @return
     * @throws IOException
     *
     * @since 2020. 2. 8.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static List<String> readLines(InputStream inStream) throws IOException {
        return readLines(inStream, Charset.defaultCharset(), -1);
    }

    /**
     * 주어진 파일을 줄단위로 읽어서 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 2. 8.		박준홍			최초 작성
     * </pre>
     *
     * @param inStream
     *            읽을 파일
     * @param charset
     *            문자열 셋
     * @return
     * @throws IOException
     *
     * @since 2020. 2. 8.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static List<String> readLines(InputStream inStream, Charset charset) throws IOException {
        return readLines(inStream, charset, -1);
    }

    /**
     * 주어진 파일을 줄단위로 읽어서 요청한 줄수 또는 전체(요청한 줄수가 전체 라인보다 큰 경우)를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 10.		박준홍			최초 작성
     * </pre>
     *
     * @param inStream
     *            읽을 파일
     * @param charset
     *            문자열 셋
     * @param lineCount
     *            읽으려는 줄 수
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 10.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static List<String> readLines(InputStream inStream, Charset charset, final long lineCount) throws IOException {
        List<String> lines = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, charset));
        String readline = null;

        Predicate<Long> counter = lineCount < 0 //
                ? l -> true // 0보다 작은 경우 전체
                : l -> l < lineCount //
        ;

        long readCount = 0;
        while (counter.test(readCount) && (readline = reader.readLine()) != null) {
            lines.add(readline);
            readCount++;
        }

        return lines;
    }

    /**
     * 주어진 파일을 줄단위로 읽어서 요청한 줄수 또는 전체(요청한 줄수가 전체 라인보다 큰 경우)를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 10.        박준홍         최초 작성
     * </pre>
     *
     * @param file
     *            읽을 파일
     * @param lineCount
     *            읽으려는 줄 수
     * 
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 10.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static List<String> readLines(InputStream inStream, long lineCount) throws IOException {
        return readLines(inStream, Charset.defaultCharset(), lineCount);
    }

    /**
     * 주어진 파일을 줄단위로 읽어서 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 2. 8.		박준홍			최초 작성
     * </pre>
     *
     * @param inStream
     * @param charsetName
     * @return
     * @throws IOException
     *
     * @since 2020. 2. 8.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static List<String> readLines(InputStream inStream, String charsetName) throws IOException {
        return readLines(inStream, charsetName, -1);
    }

    /**
     * 주어진 파일을 줄단위로 읽어서 요청한 줄수 또는 전체(요청한 줄수가 전체 라인보다 큰 경우)를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 10.        박준홍         최초 작성
     * </pre>
     *
     * @param inStream
     *            읽을 파일
     * @param lineCount
     *            읽으려는 줄 수
     * @param charset
     *            문자열 셋
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 10.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static List<String> readLines(InputStream inStream, String charsetName, long lineCount) throws IOException {
        Charset charset = Charset.isSupported(charsetName) //
                ? Charset.forName(charsetName) //
                : Charset.defaultCharset();
        return readLines(inStream, charset, lineCount);
    }

    /**
     * 주어진 파일을 줄단위로 읽어서 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 2. 8.		박준홍			최초 작성
     * </pre>
     *
     * @param 읽을
     *            파일.
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     *
     * @since 2020. 2. 8.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static List<String> readLines(Path path) throws FileNotFoundException, IOException {
        return readLines(path, Charset.defaultCharset(), -1);
    }

    /**
     * 주어진 파일을 줄단위로 읽어서 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 10.        박준홍         최초 작성
     * </pre>
     *
     * @param inStream
     *            읽을 파일
     * @param charset
     *            문자열 셋
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     *
     * @since 2021. 11. 10.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static List<String> readLines(Path path, Charset charset) throws FileNotFoundException, IOException {
        return readLines(path, charset, -1);
    }

    /**
     * 주어진 파일을 줄단위로 읽어서 요청한 줄수 또는 전체(요청한 줄수가 전체 라인보다 큰 경우)를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 10.        박준홍         최초 작성
     * </pre>
     *
     * @param inStream
     *            읽을 파일
     * @param charset
     *            문자열 셋
     * @param lineCount
     *            읽으려는 줄 수
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     *
     * @since 2021. 11. 10.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static List<String> readLines(Path path, Charset charset, final long lineCount) throws FileNotFoundException, IOException {
        return readLines(path.toFile(), charset, lineCount);
    }

    /**
     * 주어진 파일을 줄단위로 읽어서 요청한 줄수 또는 전체(요청한 줄수가 전체 라인보다 큰 경우)를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 10.        박준홍         최초 작성
     * </pre>
     *
     * @param inStream
     *            읽을 파일
     * @param lineCount
     *            읽으려는 줄 수
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     *
     * @since 2021. 11. 10.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static List<String> readLines(Path path, final long lineCount) throws FileNotFoundException, IOException {
        return readLines(path, Charset.defaultCharset(), lineCount);
    }

    /**
     * 주어진 파일을 줄단위로 읽어서 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 10.      박준홍         최초 작성
     * </pre>
     *
     * @param path
     *            읽을 파일
     * @param charsetName
     *            문자열 셋
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 10.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static List<String> readLines(Path path, String charsetName) throws IOException {
        return readLines(path, charsetName, -1);
    }

    /**
     * 주어진 파일을 줄단위로 읽어서 요청한 줄수 또는 전체(요청한 줄수가 전체 라인보다 큰 경우)를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 10.        박준홍         최초 작성
     * </pre>
     *
     * @param path
     *            읽을 파일
     * @param lineCount
     *            읽으려는 줄 수
     * @param charset
     *            문자열 셋
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 10.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static List<String> readLines(Path path, String charsetName, long lineCount) throws IOException {
        Charset charset = Charset.isSupported(charsetName) //
                ? Charset.forName(charsetName) //
                : Charset.defaultCharset();
        return readLines(path, charset, lineCount);
    }

    /**
     * 주어진 파일을 줄단위로 읽어서 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 2. 8.		박준홍			최초 작성
     * </pre>
     *
     * @param filepath
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     *
     * @since 2020. 2. 8.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static List<String> readLines(String filepath) throws FileNotFoundException, IOException {
        return readLines(filepath, Charset.defaultCharset(), -1);
    }

    /**
     * 주어진 파일을 줄단위로 읽어서 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 10.        박준홍         최초 작성
     * </pre>
     *
     * @param inStream
     *            읽을 파일
     * @param charset
     *            문자열 셋
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     *
     * @since 2021. 11. 10.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static List<String> readLines(String filepath, Charset charset) throws FileNotFoundException, IOException {
        return readLines(filepath, charset, -1);
    }

    /**
     * 주어진 파일을 줄단위로 읽어서 요청한 줄수 또는 전체(요청한 줄수가 전체 라인보다 큰 경우)를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 10.        박준홍         최초 작성
     * </pre>
     *
     * @param inStream
     *            읽을 파일
     * @param charset
     *            문자열 셋
     * @param lineCount
     *            읽으려는 줄 수
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     *
     * @since 2021. 11. 10.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static List<String> readLines(String filepath, Charset charset, final long lineCount) throws FileNotFoundException, IOException {
        return readLines(new File(filepath), charset, lineCount);
    }

    /**
     * 주어진 파일을 줄단위로 읽어서 요청한 줄수 또는 전체(요청한 줄수가 전체 라인보다 큰 경우)를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 10.        박준홍         최초 작성
     * </pre>
     *
     * @param inStream
     *            읽을 파일
     * @param lineCount
     *            읽으려는 줄 수
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     *
     * @since 2021. 11. 10.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static List<String> readLines(String filepath, final long lineCount) throws FileNotFoundException, IOException {
        return readLines(filepath, Charset.defaultCharset(), lineCount);
    }

    /**
     * 주어진 파일을 줄단위로 읽어서 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 10.      박준홍         최초 작성
     * </pre>
     *
     * @param filepath
     *            읽을 파일
     * @param charsetName
     *            문자열 셋
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 10.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static List<String> readLines(String filepath, String charsetName) throws IOException {
        return readLines(filepath, charsetName, -1);
    }

    /**
     * 주어진 파일을 줄단위로 읽어서 요청한 줄수 또는 전체(요청한 줄수가 전체 라인보다 큰 경우)를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 10.        박준홍         최초 작성
     * </pre>
     *
     * @param filepath
     *            읽을 파일
     * @param lineCount
     *            읽으려는 줄 수
     * @param charset
     *            문자열 셋
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 10.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static List<String> readLines(String filepath, String charsetName, long lineCount) throws IOException {
        Charset charset = Charset.isSupported(charsetName) //
                ? Charset.forName(charsetName) //
                : Charset.defaultCharset();
        return readLines(filepath, charset, lineCount);
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
     * 2019. 8. 29.		박준홍			내부 로직 성능 향상 및 안정성 강화
     * </pre>
     *
     * @param inStream
     * @param length
     *            a number greater than 0
     * @param close
     *            소켓 close 여부
     * @return
     *
     * @since 2017. 9. 6.
     */
    public static byte[] readStream(InputStream inStream, final int length, boolean close) {

        // buffer size
        int bufferSize = 1024 * 1024;

        // assign a real buffer.
        ByteBuffer buf = ByteBuffer.allocateDirect(bufferSize < length ? bufferSize : length);

        ReadableByteChannel reader = Channels.newChannel(inStream);

        int total = 0;
        int read = 0;
        int over = 0;

        ArrayList<byte[]> store = new ArrayList<>();
        byte[] bs = null;

        try {
            do {
                read = reader.read(buf);

                if (read < 1) {
                    break;
                }

                // check overed data.
                over = (length - total) - read;
                // remained data to read.
                if (over < 0) {
                    read -= over;
                }

                buf.flip();

                buf.get(bs = new byte[read]);
                store.add(bs);

                buf.clear();

                total += read;
            } while (total < length);

            return ArrayUtils.merge(store.toArray(new byte[0][]));

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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2018. 9. 10.
     * 
     * @see InputStream#close()
     * @see OutputStream#close()
     */
    public static int transfer(InputStream inStream, boolean closeInput, OutputStream outStream, boolean closeOutput) throws IOException {
        return transfer(inStream, closeInput, outStream, closeOutput, BUFFER_SIZE_32B);
    }

    /**
     * {@link InputStream}의 내용을 {@link OutputStream} 으로 전송한다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 1. 14.     박준홍         최초 작성
     * </pre>
     *
     * @param inStream
     * @param closeInput
     *            {@link InputStream#close()} 호출 여부
     * @param outStream
     * @param closeOutput
     *            {@link OutputStream#close()} 호출 여부
     * @param readBufferSize
     *            데이터 읽기 버퍼 크기
     * @return
     * @throws IOException
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2021. 1. 14.
     * 
     * @see InputStream#close()
     * @see OutputStream#close()
     */
    public static int transfer(InputStream inStream, boolean closeInput, OutputStream outStream, boolean closeOutput, int readBufferSize) throws IOException {

        int rcvCount = 0;

        ReadableByteChannel reader = null;
        WritableByteChannel writer = null;

        try {

            reader = Channels.newChannel(inStream);
            writer = Channels.newChannel(outStream);

            ByteBuffer buf = ByteBuffer.allocateDirect(readBufferSize);

            int readCount = -1;
            while ((readCount = reader.read(buf)) > 0) {

                buf.flip();

                writer.write(buf);

                outStream.flush();

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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2018. 9. 26.
     * 
     * @see #transfer(Reader, boolean, Writer, boolean)
     */
    public static int transfer(InputStream inStream, Charset inCharset, boolean closeInput, Writer writer, boolean closeOutput) throws IOException {
        return transfer(Channels.newReader(Channels.newChannel(inStream), inCharset.name()), closeInput, writer, closeOutput);
    }

    /**
     * {@link InputStream}를 통해 얻은 데이터를 {@link Writer}로 전달한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 1. 14.     박준홍         최초 작성
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
     * @param readBufferSize
     *            데이터 읽기 버퍼 크기
     * @return
     * @throws IOException
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2021. 1. 14.
     * 
     * @see #transfer(Reader, boolean, Writer, boolean)
     */
    public static int transfer(InputStream inStream, Charset inCharset, boolean closeInput, Writer writer, boolean closeOutput, int readBufferSize) throws IOException {
        return transfer(Channels.newReader(Channels.newChannel(inStream), inCharset.name()), closeInput, writer, closeOutput, readBufferSize);
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2018. 9. 10.
     * 
     * @see InputStream#close()
     * @see OutputStream#close()
     */
    public static int transfer(InputStream inStream, Charset inCharset, OutputStream outStream, Charset outCharset) throws IOException {
        return transfer(inStream, inCharset.name(), true, outStream, outCharset.name(), true);
    }

    /**
     * {@link InputStream}의 내용을 {@link OutputStream} 으로 전송한다.<br>
     * 전송 후 {@link InputStream}, {@link OutputStream}은 모두 close 된다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 1. 14.		박준홍			최초 작성
     * </pre>
     *
     * @param inStream
     * @param inCharset
     *            Charset of an {@link InputStream}
     * @param outStream
     * @param outCharset
     *            Charset of an {@link OutputStream}
     * @param readBufferSize
     *            데이터 읽기 버퍼 크기
     * @return
     * @throws IOException
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2021. 1. 14.
     * 
     * @see InputStream#close()
     * @see OutputStream#close()
     */
    public static int transfer(InputStream inStream, Charset inCharset, OutputStream outStream, Charset outCharset, int readBufferSize) throws IOException {
        return transfer(inStream, inCharset.name(), true, outStream, outCharset.name(), true, readBufferSize);
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2018. 9. 26.
     * 
     * @see #transfer(Reader, boolean, Writer, boolean)
     */
    public static int transfer(InputStream inStream, Charset inCharset, Writer writer, boolean close) throws IOException {
        return transfer(Channels.newReader(Channels.newChannel(inStream), inCharset.name()), close, writer, close);
    }

    /**
     * {@link InputStream}를 통해 얻은 데이터를 {@link Writer}로 전달한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 1. 14.     박준홍         최초 작성
     * </pre>
     *
     * @param inStream
     * @param inCharset
     *            Charset of an {@link InputStream}
     * @param writer
     * @param close
     *            {@link Reader#close()}, {@link OutputStream#close()} 호출 여부
     * @param readBufferSize
     *            데이터 읽기 버퍼 크기
     * @return
     * @throws IOException
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2021. 1. 14.
     * 
     * @see #transfer(Reader, boolean, Writer, boolean)
     */
    public static int transfer(InputStream inStream, Charset inCharset, Writer writer, boolean close, int readBufferSize) throws IOException {
        return transfer(Channels.newReader(Channels.newChannel(inStream), inCharset.name()), close, writer, close, readBufferSize);
    }

    /**
     * {@link InputStream}를 통해 얻은 데이터를 {@link Writer}로 전달한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 1. 14.		박준홍			최초 작성
     * </pre>
     *
     * @param inStream
     * @param inCharset
     *            Charset of an {@link InputStream}
     * @param writer
     * @param readBufferSize
     *            데이터 읽기 버퍼 크기
     * @return
     * @throws IOException
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2021. 1. 14.
     * 
     * @see #transfer(Reader, boolean, Writer, boolean)
     */
    public static int transfer(InputStream inStream, Charset inCharset, Writer writer, int readBufferSize) throws IOException {
        return transfer(Channels.newReader(Channels.newChannel(inStream), inCharset.name()), true, writer, true, readBufferSize);
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
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
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 1. 14.     박준홍         최초 작성
     * </pre>
     *
     * @param inStream
     * @param outStream
     * @param close
     *            {@link OutputStream#close()} 호출 여부
     * @param readBufferSize
     *            데이터 읽기 버퍼 크기
     * @return
     * @throws IOException
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2021. 1. 14.
     * 
     * @see InputStream#close()
     * @see OutputStream#close()
     * @see #transfer(InputStream, boolean, OutputStream, boolean)
     */
    public static int transfer(InputStream inStream, OutputStream outStream, boolean close, int readBufferSize) throws IOException {
        return transfer(inStream, close, outStream, close, readBufferSize);
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
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
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 1. 14.     박준홍         최초 작성
     * </pre>
     *
     * @param inStream
     * @param outStream
     * @param charset
     *            Charset of an {@link OutputStream}
     * @param close
     *            {@link OutputStream#close()} 호출 여부
     * @param readBufferSize
     *            데이터 읽기 버퍼 크기
     * @return
     * @throws IOException
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2021. 1. 14.
     * 
     * @see InputStream#close()
     * @see OutputStream#close()
     * @see #transfer(InputStream, String, boolean, OutputStream, String, boolean)
     */
    public static int transfer(InputStream inStream, OutputStream outStream, Charset charset, boolean close, int readBufferSize) throws IOException {
        return transfer(inStream, charset.name(), close, outStream, charset.name(), close, readBufferSize);
    }

    /**
     * {@link InputStream}의 내용을 {@link OutputStream} 으로 전송한다.<br>
     * 전송 후 {@link InputStream}, {@link OutputStream}은 모두 close 된다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 1. 14.		박준홍			최초 작성
     * </pre>
     *
     * @param inStream
     * @param outStream
     * @param charset
     * @param readBufferSize
     *            데이터 읽기 버퍼 크기
     * @return
     * @throws IOException
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2021. 1. 14.
     * 
     * @see InputStream#close()
     * @see OutputStream#close()
     * @see #transfer(InputStream, String, boolean, OutputStream, String, boolean)
     * 
     */
    public static int transfer(InputStream inStream, OutputStream outStream, Charset charset, int readBufferSize) throws IOException {
        return transfer(inStream, charset.name(), true, outStream, charset.name(), true, readBufferSize);
    }

    /**
     * {@link InputStream}의 내용을 {@link OutputStream} 으로 전송한다.<br>
     * 전송 후 {@link InputStream}, {@link OutputStream}은 모두 close 된다.
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 1. 14.     박준홍         최초 작성
     * </pre>
     * 
     * @param inStream
     * @param outStream
     * @param readBufferSize
     *            데이터 읽기 버퍼 크기
     * @return
     * @throws IOException
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2018. 9. 10.
     * 
     * @since 2021. 1. 14.
     * 
     * @see InputStream#close()
     * @see OutputStream#close()
     * @see #transfer(InputStream, boolean, OutputStream, boolean)
     */
    public static int transfer(InputStream inStream, OutputStream outStream, int readBufferSize) throws IOException {
        return transfer(inStream, true, outStream, true, readBufferSize);
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
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
     * 2021. 1. 14.     박준홍         최초 작성
     * </pre>
     *
     * @param inStream
     * @param outStream
     * @param charset
     *            Charset of an {@link OutputStream}
     * @param close
     *            {@link OutputStream#close()} 호출 여부
     * @param readBufferSize
     *            데이터 읽기 버퍼 크기
     * @return
     * @throws IOException
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2021. 1. 14.
     * 
     * @see InputStream#close()
     * @see OutputStream#close()
     * @see #transfer(InputStream, String, boolean, OutputStream, String, boolean)
     */
    public static int transfer(InputStream inStream, OutputStream outStream, String charset, boolean close, int readBufferSize) throws IOException {
        return transfer(inStream, charset, close, outStream, charset, close, readBufferSize);
    }

    /**
     * {@link InputStream}의 내용을 {@link OutputStream} 으로 전송한다.<br>
     * 전송 후 {@link InputStream}, {@link OutputStream}은 모두 close 된다.
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 1. 14.     박준홍         최초 작성
     * </pre>
     * 
     * @param inStream
     * @param outStream
     * @param charset
     *            charset
     * @param readBufferSize
     *            데이터 읽기 버퍼 크기
     * @return
     * @throws IOException
     * 
     * @since 2021. 1. 14.
     * 
     * @see InputStream#close()
     * @see OutputStream#close()
     * @see #transfer(InputStream, String, boolean, OutputStream, String, boolean)
     * 
     */
    public static int transfer(InputStream inStream, OutputStream outStream, String charset, int readBufferSize) throws IOException {
        return transfer(inStream, charset, true, outStream, charset, true, readBufferSize);
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
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
     * {@link InputStream}의 내용을 {@link OutputStream} 으로 전송한다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 1. 14.     박준홍         최초 작성
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
     * @param readBufferSize
     *            데이터 읽기 버퍼 크기
     * @return
     * @throws IOException
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2021. 1. 14.
     * 
     * @see InputStream#close()
     * @see OutputStream#close()
     * @see #transfer(Reader, boolean, Writer, boolean)
     */
    public static int transfer(InputStream inStream, String inCharset, boolean closeInput, OutputStream outStream, String outCharset, boolean closeOutput, int readBufferSize)
            throws IOException {
        return transfer(Channels.newReader(Channels.newChannel(inStream), inCharset), closeInput, Channels.newWriter(Channels.newChannel(outStream), outCharset), closeOutput,
                readBufferSize);
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2018. 9. 26.
     */
    public static int transfer(InputStream inStream, String inCharset, boolean closeInput, Writer writer, boolean closeOutput) throws IOException {
        return transfer(Channels.newReader(Channels.newChannel(inStream), inCharset), closeInput, writer, closeOutput);
    }

    /**
     * {@link InputStream}를 통해 얻는 데이터를 {@link Writer}로 전달한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 1. 14.		박준홍			최초 작성
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
     * @param readBufferSize
     *            데이터 읽기 버퍼 크기
     * @return
     * @throws IOException
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2021. 1. 14.
     */
    public static int transfer(InputStream inStream, String inCharset, boolean closeInput, Writer writer, boolean closeOutput, int readBufferSize) throws IOException {
        return transfer(Channels.newReader(Channels.newChannel(inStream), inCharset), closeInput, writer, closeOutput, readBufferSize);
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2018. 9. 10.
     * 
     * @see InputStream#close()
     * @see OutputStream#close()
     */
    public static int transfer(InputStream inStream, String inCharset, OutputStream outStream, String outCharset) throws IOException {
        return transfer(inStream, inCharset, true, outStream, outCharset, true);
    }

    /**
     * {@link InputStream}의 내용을 {@link OutputStream} 으로 전송한다.<br>
     * 전송 후 {@link InputStream}, {@link OutputStream}은 모두 close 된다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 1. 14.		박준홍			최초 작성
     * </pre>
     *
     * @param inStream
     * @param inCharset
     *            Charset of an {@link InputStream}
     * @param outStream
     * @param outCharset
     *            Charset of an {@link OutputStream}
     * @param readBufferSize
     *            데이터 읽기 버퍼 크기
     * @return
     * @throws IOException
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2021. 1. 14.
     * 
     * @see InputStream#close()
     * @see OutputStream#close()
     */
    public static int transfer(InputStream inStream, String inCharset, OutputStream outStream, String outCharset, int readBufferSize) throws IOException {
        return transfer(inStream, inCharset, true, outStream, outCharset, true, readBufferSize);
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2018. 9. 26.
     * 
     * @see #transfer(Reader, boolean, Writer, boolean)
     */
    public static int transfer(InputStream inStream, String inCharset, Writer writer, boolean close) throws IOException {
        return transfer(Channels.newReader(Channels.newChannel(inStream), inCharset), close, writer, close);
    }

    /**
     * {@link InputStream}를 통해 얻은 데이터를 {@link Writer}로 전달한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 1. 14.     박준홍         최초 작성
     * </pre>
     *
     * @param inStream
     * @param inCharset
     *            Charset of an {@link InputStream}
     * @param writer
     * @param close
     *            {@link Reader#close()}, {@link OutputStream#close()} 호출 여부
     * @param readBufferSize
     *            데이터 읽기 버퍼 크기
     * @return
     * @throws IOException
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2021. 1. 14.
     * 
     * @see #transfer(Reader, boolean, Writer, boolean)
     */
    public static int transfer(InputStream inStream, String inCharset, Writer writer, boolean close, int readBufferSize) throws IOException {
        return transfer(Channels.newReader(Channels.newChannel(inStream), inCharset), close, writer, close, readBufferSize);
    }

    /**
     * {@link InputStream}를 통해 얻은 데이터를 {@link Writer}로 전달한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 1. 14.     박준홍         최초 작성
     * </pre>
     *
     * @param inStream
     * @param inCharset
     *            Charset of an {@link InputStream}
     * @param writer
     * @param readBufferSize
     *            데이터 읽기 버퍼 크기
     * @return
     * @throws IOException
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2021. 1. 14.
     * 
     * @see #transfer(Reader, boolean, Writer, boolean)
     */
    public static int transfer(InputStream inStream, String inCharset, Writer writer, int readBufferSize) throws IOException {
        return transfer(Channels.newReader(Channels.newChannel(inStream), inCharset), true, writer, true, readBufferSize);
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2018. 9. 26.
     * 
     * @see #transfer(Reader, boolean, Writer, boolean)
     */
    public static int transfer(Reader reader, boolean closeInput, OutputStream outStream, Charset outCharset, boolean closeOutput) throws IOException {
        return transfer(reader, closeInput, Channels.newWriter(Channels.newChannel(outStream), outCharset.name()), closeOutput);
    }

    /**
     * {@link Reader}를 통해 얻은 데이타를 {@link OutputStream}으로 전달한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 1. 14.		박준홍			최초 작성
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
     * @param readBufferSize
     *            데이터 읽기 버퍼 크기
     * @return
     * @throws IOException
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2021. 1. 14.
     * 
     * @see #transfer(Reader, boolean, Writer, boolean)
     */
    public static int transfer(Reader reader, boolean closeInput, OutputStream outStream, Charset outCharset, boolean closeOutput, int readBufferSize) throws IOException {
        return transfer(reader, closeInput, Channels.newWriter(Channels.newChannel(outStream), outCharset.name()), closeOutput, readBufferSize);
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2018. 9. 26.
     * 
     * @see #transfer(Reader, boolean, Writer, boolean)
     */
    public static int transfer(Reader reader, boolean closeInput, OutputStream outStream, String outCharset, boolean closeOutput) throws IOException {
        return transfer(reader, closeInput, Channels.newWriter(Channels.newChannel(outStream), outCharset), closeOutput);
    }

    /**
     * {@link Reader}를 통해 얻는 데이터를 {@link OutputStream}로 전달한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 1. 14.     박준홍         최초 작성
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
     * @param readBufferSize
     *            데이터 읽기 버퍼 크기
     * @return
     * @throws IOException
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2021. 1. 14.
     * 
     * @see #transfer(Reader, boolean, Writer, boolean)
     */
    public static int transfer(Reader reader, boolean closeInput, OutputStream outStream, String outCharset, boolean closeOutput, int readBufferSize) throws IOException {
        return transfer(reader, closeInput, Channels.newWriter(Channels.newChannel(outStream), outCharset), closeOutput, readBufferSize);
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2018. 9. 26.
     */
    public static int transfer(Reader reader, boolean closeInput, Writer writer, boolean closeOutput) throws IOException {
        return transfer(reader, closeInput, writer, closeOutput, BUFFER_SIZE_32B);
    }

    /**
     * {@link Reader}를 통해 얻는 데이터를 {@link Writer}로 전달한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 1. 14.     박준홍         최초 작성
     * </pre>
     *
     * @param reader
     * @param closeInput
     *            {@link InputStream#close()} 호출 여부
     * @param writer
     * @param closeOutput
     *            {@link OutputStream#close()} 호출 여부
     * @param readBufferSize
     *            데이터 읽기 버퍼 크기
     * @return
     * @throws IOException
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2021. 1. 14.
     */
    public static int transfer(Reader reader, boolean closeInput, Writer writer, boolean closeOutput, int readBufferSize) throws IOException {

        int rcvCount = 0;

        try {

            CharBuffer buf = CharBuffer.allocate(readBufferSize);

            int readCount = -1;
            while ((readCount = reader.read(buf)) > 0) {

                buf.flip();

                writer.write(buf.array(), 0, readCount);
                writer.flush();

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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2018. 9. 26.
     * 
     * @see #transfer(Reader, boolean, Writer, boolean)
     */
    public static int transfer(Reader reader, OutputStream outStream, Charset outCharset, boolean close) throws IOException {
        return transfer(reader, close, Channels.newWriter(Channels.newChannel(outStream), outCharset.name()), close);
    }

    /**
     * {@link Reader}를 통해 얻은 데이터를 {@link OutputStream}으로 전달한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 1. 14.		박준홍			최초 작성
     * </pre>
     *
     * @param reader
     * @param outStream
     * @param outCharset
     *            Charset of an {@link OutputStream}
     * @param close
     *            {@link Reader#close()}, {@link OutputStream#close()} 호출 여부
     * @param readBufferSize
     *            데이터 읽기 버퍼 크기
     * @return
     * @throws IOException
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2021. 1. 14.
     * 
     * @see #transfer(Reader, boolean, Writer, boolean)
     */
    public static int transfer(Reader reader, OutputStream outStream, Charset outCharset, boolean close, int readBufferSize) throws IOException {
        return transfer(reader, close, Channels.newWriter(Channels.newChannel(outStream), outCharset.name()), close, readBufferSize);
    }

    /**
     * {@link Reader}를 통해 얻는 데이터를 {@link OutputStream}으로 전달한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 1. 14.		박준홍			최초 작성
     * </pre>
     *
     * @param reader
     * @param outStream
     * @param outCharset
     *            Charset of an {@link OutputStream}
     * @param readBufferSize
     *            데이터 읽기 버퍼 크기
     * @return
     * @throws IOException
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2021. 1. 14.
     * 
     * @see #transfer(Reader, boolean, Writer, boolean)
     */
    public static int transfer(Reader reader, OutputStream outStream, Charset outCharset, int readBufferSize) throws IOException {
        return transfer(reader, true, Channels.newWriter(Channels.newChannel(outStream), outCharset.name()), true, readBufferSize);
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2018. 9. 26.
     * 
     * @see #transfer(Reader, boolean, Writer, boolean)
     */
    public static int transfer(Reader reader, OutputStream outStream, String outCharset, boolean close) throws IOException {
        return transfer(reader, close, Channels.newWriter(Channels.newChannel(outStream), outCharset), close);
    }

    /**
     * {@link Reader}를 통해 얻은 데이타를 {@link OutputStream}으로 전달한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 1. 14.		박준홍			최초 작성
     * </pre>
     *
     * @param reader
     * @param outStream
     * @param outCharset
     *            Charset of an {@link OutputStream}
     * @param close
     *            {@link Reader#close()}, {@link OutputStream#close()} 호출 여부
     * @param readBufferSize
     *            데이터 읽기 버퍼 크기
     * @return
     * @throws IOException
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2021. 1. 14.
     * 
     * @see #transfer(Reader, boolean, Writer, boolean)
     */
    public static int transfer(Reader reader, OutputStream outStream, String outCharset, boolean close, int readBufferSize) throws IOException {
        return transfer(reader, close, Channels.newWriter(Channels.newChannel(outStream), outCharset), close, readBufferSize);
    }

    /**
     * {@link Reader}를 통해 얻은 데이타를 {@link OutputStream}으로 전달한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 1. 14.		박준홍			최초 작성
     * </pre>
     *
     * @param reader
     * @param outStream
     * @param outCharset
     *            Charset of an {@link OutputStream}
     * @param readBufferSize
     *            데이터 읽기 버퍼 크기
     * @return
     * @throws IOException
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2021. 1. 14.
     * 
     * @see #transfer(Reader, boolean, Writer, boolean)
     */
    public static int transfer(Reader reader, OutputStream outStream, String outCharset, int readBufferSize) throws IOException {
        return transfer(reader, true, Channels.newWriter(Channels.newChannel(outStream), outCharset), true, readBufferSize);
    }

    /**
     * {@link Reader}를 통해 얻는 데이터를 {@link Writer}로 전달한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 8. 7.		박준홍			최초 작성
     * </pre>
     *
     * @param reader
     * @param writer
     * @return
     * @throws IOException
     *
     * @since 2019. 8. 7.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static int transfer(Reader reader, Writer writer) throws IOException {
        return transfer(reader, true, writer, true);
    }

    /**
     * {@link Reader}를 통해 얻는 데이터를 {@link Writer}로 전달한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 1. 14.		박준홍			최초 작성
     * </pre>
     *
     * @param reader
     * @param writer
     * @param readBufferSize
     *            데이터 읽기 버퍼 크기
     * @return
     * @throws IOException
     *
     * @since 2021. 1. 14.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static int transfer(Reader reader, Writer writer, int readBufferSize) throws IOException {
        return transfer(reader, true, writer, true, readBufferSize);
    }

    /**
     * {@link BufferedWriter}에 데이터를 바로 보낸다.
     * 
     * @param writer
     * @param msg
     * @throws IOException
     */
    public static void write(BufferedWriter writer, String msg) throws IOException {
        writer.write(msg + LINE_SEPARATOR);
    }
}
