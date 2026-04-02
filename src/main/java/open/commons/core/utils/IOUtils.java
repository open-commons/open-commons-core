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
package open.commons.core.utils;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.io.SequenceInputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedByInterruptException;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.Vector;
import java.util.function.Function;
import java.util.stream.Stream;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import open.commons.core.CommonProperties;
import open.commons.core.Result;
import open.commons.core.io.IRandomAccessible;
import open.commons.core.util.ArrayItr;

/**
 * @since 2012. 01. 10.
 */
// 아래 내용에 적용됨.
// - 대부분의 JDK 표준 API
// [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
// [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
@SuppressWarnings("null")
public class IOUtils {

    private static Logger LOGGER = LoggerFactory.getLogger(IOUtils.class);

    // 애플리케이션 로드 시점에 OS별 명령어를 캐싱 (기존 아키텍처 유지)
    private static final @Nullable String COMMAND_OPEN;

    static {
        final String PREFIX = "io.command.explorer.";
        // 오타 수정 여부는 외부 라이브러리에 종속되므로 기존 코드 유지 (subProperteis)
        Properties prop = CommonProperties.subProperteis(PREFIX);
        final String osname = System.getProperty("os.name").toLowerCase();

        // [최적화] Stream API를 활용하여 선언적으로 매핑 및 첫 번째 일치 항목 탐색
        COMMAND_OPEN = prop.entrySet().stream() //
                .filter(entry -> osname.contains(((String) entry.getKey()).replace(PREFIX, ""))) //
                .map(entry -> (String) entry.getValue()) //
                .findFirst() //
                .orElse(null);
    }

    public static final String LINE_SEPARATOR = System.lineSeparator();

    /**
     * byte[] 데이터를 제공합니다.
     *
     * @param i
     *            줄 번호
     * @param bs
     *            byte[] 형태의 줄 데이터
     */
    private static final Function<byte[], byte[]> BYTE_ACTION_BYPASS = bs -> bs;

    /**
     * {@link AutoCloseable}를 모두 닫는다.
     *
     * @param closeables
     *            {@link AutoCloseable} 객체들.
     *
     * @throws NullPointerException
     *             파라미터({@code closeables})가 {@code null}이거나 데이터 중에 {@code null}이 포함된 경우 발생.
     */
    public static void close(@Nullable AutoCloseable... closeables) {
        Objects.requireNonNull(closeables);

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
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 7. 5.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param closeables
     *            {@link AutoCloseable} 객체들.
     *
     * @throws NullPointerException
     *             파라미터({@code closeables})가 {@code null}이거나 데이터 중에 {@code null}이 포함된 경우 발생.
     *
     * @since 2021. 7. 5.
     * @version 1.8.0
     */
    public static void close(Collection<@Nullable AutoCloseable> closeables) {
        Objects.requireNonNull(closeables);

        for (AutoCloseable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (Exception ignored) {
                }
            }
        }
    }

    private static Charset defaultCharset() {
        return Objects.requireNonNull(Charset.defaultCharset());
    }

    /**
     * 주어진 {@link File}을 읽어오는 {@link BufferedReader}를 반환합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2012. 01. 10.        parkjunhong77@gmail.com     최초 작성
     * 2020. 9. 25.         parkjunhong77@gmail.com     {@link Path} 메소드로 전환
     * </pre>
     *
     * @param file
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code file})가 {@code null}인 경우 발생.
     *
     * @since 2012. 01. 10.
     */
    public static BufferedReader getReader(File file) throws IOException {
        Objects.requireNonNull(file);

        return getReader(file.toPath(), defaultCharset());
    }

    /**
     * 주어진 {@link File}을 읽어오는 {@link BufferedReader}를 반환합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 9. 25.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param file
     *            파일 객체
     * @param cs
     *            file character set
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code file} 또는 {@code cs})가 {@code null}인 경우 발생.
     *
     * @since 2020. 9. 25.
     */
    public static BufferedReader getReader(File file, Charset cs) throws IOException {
        ObjectUtils.requireNonNulls(file, cs);

        return getReader(file.toPath(), cs);
    }

    /**
     * 주어진 {@link File}을 읽어오는 {@link BufferedReader}를 반환합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 9. 25.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param file
     * @param charsetNam
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code file} 또는 {@code charsetNam})가 {@code null}인 경우 발생.
     *
     * @since 2020. 9. 25.
     */
    public static BufferedReader getReader(File file, String charsetNam) throws IOException {
        ObjectUtils.requireNonNulls(file, charsetNam);

        return getReader(file.toPath(), charsetNam);
    }

    /**
     * {@link InputStream}을 가지고 {@link BufferedReader}를 생성해서 반환합니다.<br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2014. 6. 24.         parkjunhong77@gmail.com     최초 작성
     * 2020. 9. 25.        parkjunhong77@gmail.com     내부 구현 변경.
     * </pre>
     *
     * @param inStream
     *
     * @return {@link BufferedReader} 객체, {@link InputStream}인 {@code null}인 경우 {@code null}반환.
     *
     * @throws NullPointerException
     *             파라미터({@code inStream})가 {@code null}인 경우 발생.
     */
    public static BufferedReader getReader(InputStream inStream) {
        return getReader(inStream, CsvUtils.defaultCharset());
    }

    /**
     * {@link InputStream}을 가지고 {@link BufferedReader}를 생성해서 반환합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 9. 25.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param inStream
     * @param cs
     *
     * @return {@link BufferedReader} 객체
     *
     * @throws NullPointerException
     *             파라미터({@code inStream} 또는 {@code cs})가 {@code null}인 경우 발생.
     *
     * @since 2020. 9. 25.
     */
    public static BufferedReader getReader(InputStream inStream, Charset cs) {
        ObjectUtils.requireNonNulls(inStream, cs);

        return new BufferedReader(new InputStreamReader(inStream, cs));
    }

    /**
     * {@link InputStream}을 가지고 {@link BufferedReader}를 생성해서 반환합니다.
     *
     * @param inStream
     *
     * @return {@link BufferedReader} 객체
     *
     * @throws NullPointerException
     *             파라미터({@code inStream} 또는 {@code charsetName})가 {@code null}인 경우 발생.
     *
     * @since 2014. 6. 24.
     */
    public static BufferedReader getReader(InputStream inStream, String charsetName) {
        ObjectUtils.requireNonNulls(inStream, charsetName);

        return getReader(inStream, requireCharset(charsetName));
    }

    /**
     * {@link Path} 를 이용하여 {@link BufferedReader} 를 제공합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 9. 25.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param path
     *            파일 경로
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code path})가 {@code null}인 경우 발생.
     *
     * @since 2020. 9. 25.
     */
    public static BufferedReader getReader(Path path) throws IOException {
        return getReader(path, defaultCharset());
    }

    /**
     * {@link Path} 를 이용하여 {@link BufferedReader} 를 제공합니다. <br>
     * <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 9. 25.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param path
     * @param cs
     *            file character set
     *
     * @return {@link Path} 가 {@code null} 이거나 에러가 발생할 경우 {@code null}을 제공합니다.
     *
     * @throws NullPointerException
     *             파라미터({@code path} 또는 {@code cs})가 {@code null}인 경우 발생.
     *
     * @since 2020. 9. 25.
     */
    public static BufferedReader getReader(Path path, Charset cs) throws IOException {
        ObjectUtils.requireNonNulls(path, cs);

        return Files.newBufferedReader(path, requireCharset(cs));
    }

    /**
     * {@link Path} 를 이용하여 {@link BufferedReader} 를 제공합니다. <br>
     * <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 9. 25.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param path
     * @param charsetName
     *            file character set name
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code path} 또는 {@code charsetName})가 {@code null}인 경우 발생.
     *
     * @since 2020. 9. 25.
     */
    public static BufferedReader getReader(Path path, String charsetName) throws IOException {
        ObjectUtils.requireNonNulls(path, charsetName);

        return getReader(path, requireCharset(charsetName));
    }

    /**
     * 주어진 문자열({@link String})을 읽어오는 {@link BufferedReader}를 반환합니다.
     *
     * @param string
     *
     * @return <b>{@code nullable}</b>.
     *
     * @since 2012. 01. 10.
     */
    public static @Nullable BufferedReader getReader(@Nullable String string) {
        return string != null //
                ? new BufferedReader(new StringReader(string)) //
                : null;
    }

    /**
     * 클래스가 포함되어 있는 리소스 경로를 반환합니다.
     *
     * @param container
     *
     * @return
     *
     * @since 2012. 03. 12.
     */
    public static @Nullable String getResourcePath(@Nullable Class<?> container) {
        if (container != null) {
            char @NonNull [] clazzChars = container.getName().toCharArray();
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
     * {@code container}와 같은 패키지에 존재하는 리소스에 대한 {@link InputStream}를 반환합니다.
     *
     * @param loader
     *            클래스 로더를 선택하는 클래스
     * @param container
     *            리소스와 같은 패키지에 존재하는 클래스
     * @param resourceName
     *            리소스 이름
     *
     * @return
     *
     * @since 2012. 03. 12.
     */
    public static @Nullable InputStream getResourcePath(@Nullable Class<?> loader, @Nullable Class<?> container, String resourceName) {
        if (loader != null && container != null) {
            return loader.getResourceAsStream(getResourcePath(container) + "/" + resourceName);
        } else {
            return null;
        }
    }

    /**
     * 주어진 문자열({@link String})에 저장하는 {@link BufferedWriter}를 반환합니다.
     *
     * @return
     *
     * @since 2012. 01. 10.
     */
    public static BufferedWriter getWriter() {
        return new BufferedWriter(new StringWriter());
    }

    /**
     * 주어진 {@link File}에 저장하는 {@link BufferedWriter}를 반환합니다.
     *
     * @param file
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code file})가 {@code null}인 경우 발생.
     *
     * @since 2012. 01. 10.
     */
    public static BufferedWriter getWriter(File file) throws IOException {
        Objects.requireNonNull(file);

        return Files.newBufferedWriter(file.toPath());
    }

    /**
     * {@link OutputStream}을 가지고 {@link BufferedWriter}를 생성해서 반환합니다.
     *
     * @param inStream
     *
     * @return {@link BufferedWriter} 객체, {@link OutputStream}인 {@code null}인 경우 {@code null}반환.
     */
    public static @Nullable BufferedWriter getWriter(@Nullable OutputStream outStream) {
        return outStream != null //
                ? new BufferedWriter(new OutputStreamWriter(outStream)) //
                : null;
    }

    /**
     * @param e
     *
     * @return
     *
     * @throws FileNotFoundException
     * @throws FileNotFoundException
     *             if the file does not exist, is a directory rather than a regular file, or for some other reason
     *             cannot be opened for reading.
     * @throws SecurityException
     *             if a security manager exists and its {@code checkRead} method denies read access to the file.
     * @throws NullPointerException
     *             파라미터({@code e})가 {@code null}인 경우 발생.
     *
     * @since 2012. 3. 9.
     *
     * @see {@link FileInputStream}
     */
    public static SequenceInputStream newSequenceInputStream(Enumeration<File> e) throws FileNotFoundException {
        Objects.requireNonNull(e);

        return newSequenceInputStream(e.asIterator());
    }

    /**
     * @param e
     *
     * @return
     *
     * @throws FileNotFoundException
     * @throws FileNotFoundException
     *             if the file does not exist, is a directory rather than a regular file, or for some other reason
     *             cannot be opened for reading.
     * @throws NullPointerException
     *             파라미터({@code files})가 {@code null}이거나 데이터 중에 {@code null}이 포함된 경우 발생.
     *
     * @since 2012. 3. 9.
     *
     * @see {@link FileInputStream}
     */
    public static SequenceInputStream newSequenceInputStream(File... files) throws FileNotFoundException {
        ObjectUtils.requireNonNulls((Object[]) files);

        Vector<InputStream> v = new Vector<InputStream>();

        for (File file : files) {
            v.add(new FileInputStream(file));
        }

        return new SequenceInputStream(v.elements());
    }

    /**
     * @param file1
     * @param file2
     *
     * @return
     *
     * @throws FileNotFoundException
     *             if the file does not exist, is a directory rather than a regular file, or for some other reason
     *             cannot be opened for reading.
     * @throws NullPointerException
     *             파라미터({@code file1} 또는 {@code file2})가 {@code null}인 경우 발생.
     *
     * @since 2012. 3. 9.
     *
     * @see {@link FileInputStream}
     */
    @SuppressWarnings("resource")
    public static SequenceInputStream newSequenceInputStream(File file1, File file2) throws FileNotFoundException {
        ObjectUtils.requireNonNulls(file1, file2);

        InputStream is1 = new FileInputStream(file1);
        InputStream is2 = new FileInputStream(file2);

        return new SequenceInputStream(is1, is2);
    }

    /**
     * @param e
     *
     * @return
     *
     * @throws FileNotFoundException
     * @throws FileNotFoundException
     *             if the file does not exist, is a directory rather than a regular file, or for some other reason
     *             cannot be opened for reading.
     * @throws NullPointerException
     *             파라미터({@code insts})가 {@code null}이거나 데이터 중에 {@code null}이 포함된 경우 발생.
     *
     * @since 2012. 3. 9.
     *
     * @see {@link FileInputStream}
     */
    public static SequenceInputStream newSequenceInputStream(InputStream... insts) throws FileNotFoundException {
        ObjectUtils.requireNonNulls((Object[]) insts);

        Vector<InputStream> v = new Vector<InputStream>();

        for (InputStream inst : insts) {
            v.add(inst);
        }

        return new SequenceInputStream(v.elements());
    }

    public static SequenceInputStream newSequenceInputStream(Iterator<File> e) throws FileNotFoundException {
        Objects.requireNonNull(e);

        Vector<InputStream> v = new Vector<InputStream>();

        while (e.hasNext()) {
            v.add(new FileInputStream(e.next()));
        }

        return new SequenceInputStream(v.elements());
    }

    /**
     * 대상에 해당하는 파일이나 폴더를 연다. <br>
     * <b>
     * 
     * <pre>
     * 지원
     * - Windows 기반 OS 및 외부 프로퍼티에 정의된 OS
     * </pre>
     * 
     * </b>
     *
     * <pre>
     * [개정이력]
     * 날짜          | 작성자   |   내용
     * ------------------------------------------
     * 2012. 01. 30.    parkjunhong77@gmail.com         최초 작성
     * 2026. 03. 09.    parkjunhong77@gmail.com         (3.0.0) JDK 25 마이그레이션: Stream/ProcessBuilder 적용 및 리소스 최적화
     * </pre>
     *
     * @param target
     *            {String} 대상 경로
     *
     * @since 2012. 01. 30.
     * @version 3.0.0
     */
    public static void open(@Nullable String target) {
        if (target == null || target.trim().isEmpty()) {
            return;
        }

        File file = new File(target);
        if (!file.exists()) {
            LOGGER.error("There is not a file or a directory: " + target);
            return;
        }

        try {
            if (COMMAND_OPEN != null) {
                // [안전성] 공백이 포함된 경로나 복합 명령어 처리를 위한 리스트 분리
                List<String> commandArgs = new ArrayList<>(Arrays.asList(COMMAND_OPEN.split("\\s+")));
                commandArgs.add(file.getAbsolutePath());

                ProcessBuilder pb = new ProcessBuilder(commandArgs);
                pb.redirectErrorStream(true); // 데드락 방지를 위한 에러/표준 스트림 병합

                Process proc = pb.start();

                // try-with-resources를 통한 스트림 메모리 누수 방지
                try (InputStream is = proc.getInputStream()) {
                    is.skip(Long.MAX_VALUE); // Busy-waiting 제거
                }
            } else if (Desktop.isDesktopSupported()) {
                // COMMAND_OPEN 매핑 실패 시 Java 표준 Desktop API로 Fallback 지원
                Desktop.getDesktop().open(file);
            } else {
                throw new UnsupportedOperationException("Cannot open file: OS command is not configured and Desktop API is not supported.");
            }
        } catch (IOException e) {
            throw ExceptionUtils.newException(RuntimeException.class, e, "파일(%s)을 여는 도중 오류가 발생하였습니다.", target);
        }
    }

    /**
     * {@link SocketChannel}로부터 지정된 길이만큼 데이터를 읽어 바이트 배열로 반환합니다. <br>
     * (주의: 이 메소드는 채널이 블로킹(Blocking) 모드라고 가정하고 작성되었습니다.) *
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2015. 12. 10.    parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 31.    parkjunhong77@gmail.com     DirectBuffer 오버헤드 제거 및 EOF 안전성 확보
     * </pre>
     *
     * @param channel
     *            데이터를 읽어올 소켓 채널 ({@code NOT nullable})
     * @param length
     *            읽어들일 바이트 수
     * @param close
     *            작업 완료 또는 예외 발생 시 채널을 닫을지 여부
     *
     * @return 채널에서 읽어들인 바이트 배열. 스트림의 끝(EOF)에 도달하여 요청한 길이보다 적게 읽은 경우, 실제 읽은 크기만큼의 배열 반환.
     *
     * @throws IOException
     *             I/O 에러가 발생한 경우
     * @throws NullPointerException
     *             파라미터({@code channel})가 {@code null}인 경우 발생.
     *
     * @since 2015. 12. 10.
     */
    public static byte[] read(SocketChannel channel, final int length, boolean close) throws IOException {
        ObjectUtils.requireNonNulls(channel);

        if (length <= 0) {
            return new byte[0];
        }

        // [2] 성능 최적화: Direct Buffer 대신 Heap Array를 직접 Wrap 하여 Zero-copy(In-JVM) 달성
        byte[] data = new byte[length];
        ByteBuffer buf = ByteBuffer.wrap(data);

        try {
            // [3] 안전한 읽기 루프: 버퍼에 남은 공간이 있는 동안 반복
            while (buf.hasRemaining()) {
                int count = channel.read(buf);

                if (count < 0) {
                    // EOF (End of Stream) 도달
                    break;
                }

                if (count == 0) {
                    // Non-blocking 모드 방어 로직: CPU 100% 점유 방지 (Busy-spin 완화)
                    Thread.yield();
                }
            }

            int totalRead = buf.position();

            // [4] 아무것도 읽지 못한 경우
            if (totalRead == 0) {
                return new byte[0];
            }

            // [5] 불완전한 읽기(조기 EOF) 발생 시 실제 읽은 데이터 크기만큼만 잘라서 반환
            if (totalRead < length) {
                return Arrays.copyOf(data, totalRead);
            }

            // 요청한 길이만큼 완벽하게 읽은 경우 원본 배열 반환
            return data;

        } catch (IOException e) {
            // 예외를 로그로 숨기지 않고 호출자에게 명확히 전파합니다.
            throw e;
        } finally {
            if (close) {
                IOUtils.close(channel);
            }
        }
    }

    /**
     * 텍스트 파일을 줄단위로 읽어서 지정된 형태의 데이터를 제공합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 11. 13.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <T>
     *            생성할 데이터 타입.
     * @param <R>
     *            파일 데이터에 랜덤하게 접근할 수 있는 정보
     * @param channel
     *            파일에 연결된 {@link FileChannel}
     * @param action
     *            줄 데이터를 읽어서 데이터를 제공하는 함수
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code channel} 또는 {@code action})가 {@code null}인 경우 발생. 파라미터({@code accessibles})가
     *             {@code null}이거나 데이터 중에 {@code null}이 포함된 경우 발생.
     *
     * @since 2020. 11. 13.
     * @version 1.8.0
     */
    public static <T, R extends @Nullable IRandomAccessible> List<T> readChannel(FileChannel channel, Function<byte[], T> action, Iterable<R> accessibles) throws IOException {
        ObjectUtils.requireNonNulls(channel, action, accessibles);

        List<T> data = new ArrayList<>();
        Iterator<R> itr = accessibles.iterator();

        @Nullable
        R access = null;
        while (itr.hasNext()) {
            access = itr.next();
            if (access != null) {
                data.add(readChannel(channel, action, access));
            }
        }

        return data;
    }

    /**
     * <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 18.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <T>
     *            생성할 데이터 타입.
     * @param <R>
     *            파일 데이터 랜덤 접근 데이터 타입.
     * @param channel
     *            파일에 연결된 {@link FileChannel}
     * @param action
     *            줄 데이터를 읽어서 데이터를 제공하는 함수
     * @param accessible
     *            파일 데이터에 랜덤하게 접근할 수 있는 정보
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code channel})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 18.
     * @version 1.8.0
     */
    public static <T, R extends IRandomAccessible> T readChannel(FileChannel channel, Function<byte[], T> action, R accessible) throws IOException {
        channel.position(accessible.getPosition());
        return readChannel(channel, accessible.getLength(), ByteBuffer.allocate(accessible.getLength()), action);
    }

    /**
     * 텍스트 파일을 지정된 위치의 데이터를 읽어서 지정된 형태의 데이터로 제공합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 11. 13.        parkjunhong77@gmail.com         최초 작성
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
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code channel, buf, action 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2020. 11. 13.
     * @version 1.8.0
     */
    public static <T> T readChannel(FileChannel channel, int len, ByteBuffer buf, Function<byte[], T> action) throws IOException {
        byte[] bs = new byte[len];
        channel.read(buf);
        buf.flip();
        buf.get(bs);
        buf.clear();

        return action.apply(bs);
    }

    /**
     * 텍스트 파일을 줄단위로 읽어서 지정된 형태의 데이터를 제공합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 11. 13.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <T>
     *            생성할 데이터 타입.
     * @param <R>
     *            파일 데이터 랜덤 접근 데이터 타입.
     * @param channel
     *            파일에 연결된 {@link FileChannel}
     * @param bufCapacity
     *            줄 데이터를 읽을 버퍼 크기
     * @param action
     *            줄 데이터를 읽어서 데이터를 제공하는 함수
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code channel} 또는 {@code action})가 {@code null}인 경우 발생.
     *
     * @since 2020. 11. 13.
     * @version 1.8.0
     */
    @SafeVarargs
    public static <T, R extends IRandomAccessible> List<T> readChannel(FileChannel channel, int bufCapacity, Function<byte[], T> action, R... accessibles) throws IOException {
        return readChannel(channel, action, new ArrayItr<>(accessibles));
    }

    /**
     * 텍스트 파일을 줄단위로 읽어서 지정된 형태의 데이터를 제공합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 11. 13.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <T>
     *            생성할 데이터 타입.
     * @param <R>
     *            파일 데이터 랜덤 접근 데이터 타입.
     * @param file
     *            파일
     * @param action
     *            줄 데이터를 읽어서 데이터를 제공하는 함수
     * @param accessibles
     *            줄단위 메타데이터
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code action})가 {@code null}인 경우 발생. 파라미터({@code accessibles})가 {@code null}이거나 데이터 중에
     *             {@code null}이 포함된 경우 발생.
     *
     * @since 2020. 11. 13.
     * @version 1.8.0
     */
    public static <T, R extends IRandomAccessible> Result<List<T>> readFile(File file, Function<byte[], T> action, Iterable<R> accessibles) throws IOException {
        return readFile(new RandomAccessFile(file, "r"), action, accessibles);
    }

    /**
     * 텍스트 파일을 줄단위로 읽어서 지정된 형태의 데이터를 제공합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 11. 13.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <T>
     *            생성할 데이터 타입.
     * @param <R>
     *            파일 데이터 랜덤 접근 데이터 타입.
     * @param file
     *            파일
     * @param action
     *            줄 데이터를 읽어서 데이터를 제공하는 함수
     * @param accessibles
     *            줄단위 메타데이터
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code file} 또는 {@code action})가 {@code null}인 경우 발생.
     *
     * @since 2020. 11. 13.
     * @version 1.8.0
     */
    @SafeVarargs
    public static <T, R extends IRandomAccessible> Result<List<T>> readFile(File file, Function<byte[], T> action, R... accessibles) throws IOException {
        return readFile(file, action, new ArrayItr<R>(accessibles));
    }

    /**
     * 텍스트 파일을 줄단위로 읽어서 지정된 형태의 데이터를 제공합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 11. 13.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <R>
     *            파일 데이터 랜덤 접근 데이터 타입.
     * @param file
     *            파일
     * @param accessibles
     *            줄단위 메타데이터
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code file})가 {@code null}인 경우 발생. 파라미터({@code accessibles})가 {@code null}이거나 데이터 중에
     *             {@code null}이 포함된 경우 발생.
     *
     * @since 2020. 11. 13.
     * @version 1.8.0
     */
    public static <R extends IRandomAccessible> Result<List<byte[]>> readFile(File file, Iterable<R> accessibles) throws IOException {
        return readFile(file, BYTE_ACTION_BYPASS, accessibles);
    }

    /**
     * 텍스트 파일을 줄단위로 읽어서 지정된 형태의 데이터를 제공합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 11. 13.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <R>
     *            파일 데이터 랜덤 접근 데이터 타입.
     * @param file
     *            파일
     * @param accessibles
     *            줄단위 메타데이터
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code file})가 {@code null}인 경우 발생.
     *
     * @since 2020. 11. 13.
     * @version 1.8.0
     */
    @SafeVarargs
    public static <R extends IRandomAccessible> Result<List<byte[]>> readFile(File file, R... accessibles) throws IOException {
        return readFile(file, BYTE_ACTION_BYPASS, new ArrayItr<R>(accessibles));
    }

    /**
     * 텍스트 파일을 지정된 위치의 데이터를 읽어서 지정된 형태의 데이터로 제공합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 11. 13.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <R>
     *            파일 데이터 랜덤 접근 데이터 타입.
     * @param file
     *            파일
     * @param accessible
     *            줄단위 메타데이터
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code accessible})가 {@code null}인 경우 발생.
     *
     * @since 2020. 11. 13.
     * @version 1.8.0
     */
    public static <R extends IRandomAccessible> Result<byte[]> readFile(File file, R accessible) throws IOException {
        return readFile(new RandomAccessFile(file, "r"), accessible);
    }

    /**
     * 텍스트 파일을 줄단위로 읽어서 지정된 형태의 데이터를 제공합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 11. 13.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <T>
     *            줄 데이터(byte[])를 이용하여 생성할 데이터 타입.
     * @param <R>
     *            파일 데이터 랜덤 접근 데이터 타입.
     * @param file
     *            파일
     * @param action
     *            데이터 생성 함수
     * @param accessibles
     *            줄단위 메타데이어
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code file} 또는 {@code action})가 {@code null}인 경우 발생. 파라미터({@code accessibles})가 {@code null}이거나
     *             데이터 중에 {@code null}이 포함된 경우 발생.
     *
     * @since 2020. 11. 13.
     * @version 1.8.0
     */
    public static <T, R extends IRandomAccessible> Result<List<T>> readFile(RandomAccessFile file, Function<byte[], T> action, Iterable<R> accessibles) throws IOException {
        ObjectUtils.requireNonNulls(file, action, accessibles);

        List<T> data = null;
        boolean result = true;
        String message = null;
        try (@NonNull
        FileChannel channel = file.getChannel()) {
            data = readChannel(channel, action, accessibles);
        } catch (Exception e) {
            result = false;
            message = String.format("예외타입=%s, 원인=%s", e.getClass(), e.getMessage());

            LOGGER.error("예상치 못한 에러가 발생하였습니다. 원인={}", e.getMessage(), e);

            e.printStackTrace();
        }

        return new Result<>(data, result).setMessage(message);
    }

    /**
     * 텍스트 파일을 줄단위로 읽어서 지정된 형태의 데이터를 제공합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 11. 13.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <T>
     *            줄 데이터(byte[])를 이용하여 생성할 데이터 타입.
     * @param <R>
     *            파일 데이터 랜덤 접근 데이터 타입.
     * @param file
     *            파일
     * @param action
     *            데이터 생성 함수
     * @param accessibles
     *            줄단위 메타데이어
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code file} 또는 {@code action})가 {@code null}인 경우 발생.
     *
     * @since 2020. 11. 13.
     * @version 1.8.0
     */
    @SafeVarargs
    public static <T, R extends IRandomAccessible> Result<List<T>> readFile(RandomAccessFile file, Function<byte[], T> action, R... accessibles) throws IOException {
        return readFile(file, action, new ArrayItr<R>(accessibles));
    }

    /**
     * 텍스트 파일을 지정된 위치의 데이터를 읽어서 지정된 형태의 데이터로 제공합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 11. 13.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <T>
     * @param <R>
     *            파일 데이터 랜덤 접근 데이터 타입.
     * @param file
     *            파일
     * @param action
     *            데이터 생성 함수
     * @param accessible
     *            줄단위 메타데이어
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code file} 또는 {@code action} 또는 {@code accessible})가 {@code null}인 경우 발생.
     *
     * @since 2020. 11. 13.
     * @version 1.8.0
     */
    public static <T, R extends IRandomAccessible> Result<T> readFile(RandomAccessFile file, Function<byte[], T> action, R accessible) throws IOException {
        ObjectUtils.requireNonNulls(file, action, accessible);

        boolean result = true;
        String message = null;
        @Nullable
        T data = null;

        ByteBuffer buf = null;
        int len = accessible.getLength();

        try (FileChannel channel = file.getChannel()) {
            buf = ByteBuffer.allocateDirect(len);
            channel.position(accessible.getPosition());
            data = readChannel(channel, len, buf, action);
        } catch (Exception e) {
            result = false;
            message = String.format("예외타입=%s, 원인=%s", e.getClass(), e.getMessage());
            LOGGER.error("예상치 못한 에러가 발생하였습니다. 원인={}", e.getMessage(), e);
        }

        return new Result<>(data, result).setMessage(message);
    }

    /**
     * 텍스트 파일을 지정된 위치의 데이터를 읽어서 제공합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 11. 13.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <R>
     *            파일 데이터 랜덤 접근 데이터 타입.
     * @param file
     *            파일
     * @param accessible
     *            줄단위 메타데이어
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code file} 또는 {@code accessible})가 {@code null}인 경우 발생.
     *
     * @since 2020. 11. 13.
     * @version 1.8.0
     */
    public static <R extends IRandomAccessible> Result<byte[]> readFile(RandomAccessFile file, R accessible) throws IOException {
        return readFile(file, BYTE_ACTION_BYPASS, accessible);
    }

    /**
     * 텍스트 파일을 줄단위로 읽어서 지정된 형태의 데이터를 제공합니다. <br>
     * <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 11. 13.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <T>
     *            줄 데이터(byte[])를 이용하여 생성할 데이터 타입.
     * @param <R>
     *            파일 데이터 랜덤 접근 데이터 타입.
     * @param file
     *            파일
     * @param action
     *            데이터 생성 함수
     * @param accessibles
     *            줄단위 메타데이어
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code action})가 {@code null}인 경우 발생. 파라미터({@code accessibles})가 {@code null}이거나 데이터 중에
     *             {@code null}이 포함된 경우 발생.
     *
     * @since 2020. 11. 13.
     * @version 1.8.0
     */
    public static <T, R extends IRandomAccessible> Result<List<T>> readFile(String file, Function<byte[], T> action, Iterable<R> accessibles) throws IOException {
        return readFile(new RandomAccessFile(file, "r"), action, accessibles);
    }

    /**
     * 텍스트 파일을 줄단위로 읽어서 지정된 형태의 데이터를 제공합니다. <br>
     * <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 11. 13.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <T>
     *            줄 데이터(byte[])를 이용하여 생성할 데이터 타입.
     * @param <R>
     *            파일 데이터 랜덤 접근 데이터 타입.
     * @param file
     *            파일
     * @param action
     *            데이터 생성 함수
     * @param accessibles
     *            줄단위 메타데이어
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code file} 또는 {@code action})가 {@code null}인 경우 발생.
     *
     * @since 2020. 11. 13.
     * @version 1.8.0
     */
    @SafeVarargs
    public static <T, R extends IRandomAccessible> Result<List<T>> readFile(String file, Function<byte[], T> action, R... accessibles) throws IOException {
        return readFile(file, action, new ArrayItr<R>(accessibles));
    }

    /**
     * 텍스트 파일을 줄단위로 읽어서 지정된 형태의 데이터를 제공합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 11. 13.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <R>
     *            파일 데이터 랜덤 접근 데이터 타입.
     * @param file
     *            파일
     * @param accessibles
     *            줄단위 메타데이터
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code file})가 {@code null}인 경우 발생. 파라미터({@code accessibles})가 {@code null}이거나 데이터 중에
     *             {@code null}이 포함된 경우 발생.
     *
     * @since 2020. 11. 13.
     * @version 1.8.0
     */
    public static <R extends IRandomAccessible> Result<List<byte[]>> readFile(String file, Iterable<R> accessibles) throws IOException {
        return readFile(file, BYTE_ACTION_BYPASS, accessibles);
    }

    /**
     * 텍스트 파일을 줄단위로 읽어서 지정된 형태의 데이터를 제공합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 11. 13.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <R>
     *            파일 데이터 랜덤 접근 데이터 타입.
     * @param file
     *            파일
     * @param accessibles
     *            줄단위 메타데이터
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code file})가 {@code null}인 경우 발생.
     *
     * @since 2020. 11. 13.
     * @version 1.8.0
     */
    @SafeVarargs
    public static <R extends IRandomAccessible> Result<List<byte[]>> readFile(String file, R... accessibles) throws IOException {
        return readFile(file, BYTE_ACTION_BYPASS, new ArrayItr<R>(accessibles));
    }

    /**
     * 텍스트 파일을 지정된 위치의 데이터를 읽어서 지정된 형태의 데이터로 제공합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 11. 13.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <R>
     *            파일 데이터 랜덤 접근 데이터 타입.
     * @param file
     *            파일
     * @param accessible
     *            줄단위 메타데이터
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code accessible})가 {@code null}인 경우 발생.
     *
     * @since 2020. 11. 13.
     * @version 1.8.0
     */
    public static <R extends IRandomAccessible> Result<byte[]> readFile(String file, R accessible) throws IOException {
        return readFile(new RandomAccessFile(file, "r"), accessible);
    }

    /**
     * <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2012. 1. 10.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param inStream
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inStream})가 {@code null}인 경우 발생.
     *
     * @since 2012. 01. 10.
     *
     * @see <strike>sun.misc.IOUtils.readFully(InputStream, int, boolean)</strike>
     * @see #readFully(InputStream, boolean) since 1.6.5
     * @see #readFully(InputStream, int, boolean) since 1.6.5
     */
    public static byte[] readFully(InputStream inStream) throws IOException {
        return readFully(inStream, true);
    }

    /**
     * 입력 스트림 내용을 전부 읽어 byte 배열로 반환합니다. <br>
     *
     * <pre>
     * [개정이력]
     * 날짜       | 작성자   |   내용
     * ------------------------------------------
     * 2019. 3. 21.     parkjunhong77@gmail.com         최초 작성
     * 2026. 3. 10.     parkjunhong77@gmail.com         (3.0.0) JDK 25: readAllBytes() 적용으로 수동 채널 버퍼링 제거
     * </pre>
     *
     * @param inStream
     *            입력 스트림
     * @param close
     *            자동 close 여부
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inStream})가 {@code null}인 경우 발생.
     *
     * @since 2019. 3. 21.
     * @version 1.6.5
     *
     * @see #readFully(InputStream, int, boolean)
     */
    public static byte[] readFully(InputStream inStream, final boolean close) throws IOException {
        Objects.requireNonNull(inStream);

        try {
            return inStream.readAllBytes();
        } catch (IOException e) {
            LOGGER.warn("데이터 읽는 도중 에러가 발생하였습니다. detail={}", e.getMessage(), e);
            throw e;
        } finally {
            if (close) {
                close(inStream);
            }
        }
    }

    /**
     * 입력 스트림 내용을 전부 읽어 byte 배열로 반환합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 3. 21.     parkjunhong77@gmail.com         최초 작성
     * 2019. 8. 29.     parkjunhong77@gmail.com         내부 로직 성능 향상 및 안정성 강화
     * 2020. 9. 13.     parkjunhong77@gmail.com         Channel 처리 함수를 별도 분리 후, 내부 호출
     * 2026. 3. 10.     parkjunhong77@gmail.com         기존의 bufferSize 파라미터는 하위 호환성을 위해 남기지만 내부적으로는 최적화된 {@link #readFully(InputStream, boolean)} 을 호출.
     * </pre>
     *
     * @param inStream
     *            입력 스트림
     * @param bufferSize
     *            읽기 버퍼 사이즈.
     * @param close
     *            자동 close 여부
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inStream})가 {@code null}인 경우 발생.
     *
     * @since 2019. 3. 21.
     * @version 1.6.5
     */
    public static byte[] readFully(InputStream inStream, final int bufferSize, final boolean close) throws IOException {
        return readFully(inStream, close);
    }

    /**
     * 채널에 있는 데이터를 읽어 byte 배열로 반환합니다. <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2020. 9. 13.     parkjunhong77@gmail.com     최초 작성
     * 2026. 3. 31.     parkjunhong77@gmail.com     Dead Code 제거, Heap Buffer 및 ByteArrayOutputStream 적용 최적화
     * </pre>
     *
     * @param channel
     *            데이터를 읽어올 채널 ({@code NOT nullable})
     * @param bufferSize
     *            한 번에 읽어들일 내부 버퍼의 크기
     * @param close
     *            작업 완료 또는 예외 발생 시 채널을 닫을지 여부
     *
     * @return 채널에서 읽어들인 전체 바이트 배열
     *
     * @throws IOException
     *             I/O 에러가 발생한 경우
     * @throws NullPointerException
     *             파라미터({@code channel})가 {@code null}인 경우 발생.
     *
     * @since 2020. 9. 13.
     */
    public static byte[] readFully(ReadableByteChannel channel, final int bufferSize, final boolean close) throws IOException {
        ObjectUtils.requireNonNulls(channel);

        if (bufferSize <= 0) {
            return new byte[0];
        }

        // [2] 메모리 최적화: Direct Buffer 대신 Heap Buffer 사용
        ByteBuffer buf = ByteBuffer.allocate(bufferSize);
        // 동적 크기 확장에 최적화된 표준 스트림 사용
        ByteArrayOutputStream baos = new ByteArrayOutputStream(Math.max(bufferSize, 8192));

        try {
            int count;
            // [3] 읽기 루프 (루프 종료 후 남은 데이터는 수학적으로 존재하지 않음)
            while ((count = channel.read(buf)) > 0) {
                // 배열 복사(new byte[]) 과정 없이 Heap 버퍼의 원본 배열을 스트림에 직접 씁니다. (Zero-Copy)
                baos.write(buf.array(), buf.arrayOffset(), count);
                buf.clear();
            }

            return baos.toByteArray();

        } catch (ClosedByInterruptException e) {
            LOGGER.warn("클라이언트와의 연결이 해제되었습니다. detail={}", e.getMessage());
            throw e;
        } catch (IOException e) {
            LOGGER.error("I/O 에러가 발생하였습니다. detail={}", e.getMessage());
            throw e;
        } finally {
            if (close) {
                IOUtils.close(channel);
            }
        }
    }

    /**
     * 주어진 파일을 줄단위로 읽어서 제공합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 2. 8.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param file
     *            읽을 파일
     *
     * @return
     *
     * @throws FileNotFoundException
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code file})가 {@code null}인 경우 발생.
     *
     * @since 2020. 2. 8.
     * @version 1.8.0
     *
     * @see BufferedReader
     */
    public static List<String> readLines(File file) throws FileNotFoundException, IOException {
        return readLines(file, defaultCharset());
    }

    /**
     * 주어진 파일을 줄단위로 읽어서 제공합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 2. 8.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param file
     *            읽을 파일
     * @param charset
     *            문자열 셋
     *
     * @return
     *
     * @throws FileNotFoundException
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code file})가 {@code null}인 경우 발생.
     *
     * @since 2020. 2. 8.
     * @version 1.8.0
     */
    public static List<String> readLines(File file, @Nullable Charset charset) throws FileNotFoundException, IOException {
        return readLines(file, charset, -1);
    }

    /**
     * 주어진 파일을 줄단위로 읽어서 요청한 줄수 또는 전체(요청한 줄수가 전체 라인보다 큰 경우)를 제공합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 10.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param file
     *            읽을 파일
     * @param charset
     *            문자열 셋
     * @param lineCount
     *            읽으려는 줄 수
     *
     * @return
     *
     * @throws FileNotFoundException
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code file})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 10.
     * @version 1.8.0
     */
    public static List<String> readLines(File file, @Nullable Charset charset, long lineCount) throws FileNotFoundException, IOException {
        ObjectUtils.requireNonNulls(file);

        return readLines(new FileInputStream(file), charset, lineCount);
    }

    /**
     * 주어진 파일을 줄단위로 읽어서 요청한 줄수 또는 전체(요청한 줄수가 전체 라인보다 큰 경우)를 제공합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 10.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param file
     *            읽을 파일
     * @param lineCount
     *            읽으려는 줄 수
     *
     * @return
     *
     * @throws FileNotFoundException
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code file})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 10.
     * @version 1.8.0
     */
    public static List<String> readLines(File file, long lineCount) throws FileNotFoundException, IOException {
        return readLines(file, defaultCharset(), lineCount);
    }

    /**
     * 주어진 파일을 줄단위로 읽어서 제공합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 10.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param file
     *            읽을 파일
     * @param charsetName
     *            문자열 셋
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code file})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 10.
     * @version 1.8.0
     */
    public static List<String> readLines(File file, @Nullable String charsetName) throws IOException {
        return readLines(file, charsetName, -1);
    }

    /**
     * 주어진 파일을 줄단위로 읽어서 요청한 줄수 또는 전체(요청한 줄수가 전체 라인보다 큰 경우)를 제공합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 10.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param file
     *            읽을 파일
     * @param lineCount
     *            읽으려는 줄 수
     * @param charset
     *            문자열 셋
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code file})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 10.
     * @version 1.8.0
     */
    public static List<String> readLines(File file, @Nullable String charsetName, long lineCount) throws IOException {
        return readLines(file, requireCharset(charsetName), lineCount);
    }

    /**
     * 주어진 파일을 줄단위로 읽어서 제공합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 2. 8.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param inStream
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inStream})가 {@code null}인 경우 발생.
     *
     * @since 2020. 2. 8.
     * @version 1.8.0
     */
    public static List<String> readLines(InputStream inStream) throws IOException {
        return readLines(inStream, defaultCharset(), -1);
    }

    /**
     * 주어진 파일을 줄단위로 읽어서 제공합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 2. 8.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param inStream
     *            읽을 파일
     * @param charset
     *            문자열 셋
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inStream})가 {@code null}인 경우 발생.
     *
     * @since 2020. 2. 8.
     * @version 1.8.0
     */
    public static List<String> readLines(InputStream inStream, @Nullable Charset charset) throws IOException {
        return readLines(inStream, charset, -1);
    }

    /**
     * 주어진 파일을 줄단위로 읽어서 요청한 줄수 또는 전체(요청한 줄수가 전체 라인보다 큰 경우)를 제공합니다. <br>
     * *
     *
     * <pre>
     * [개정이력]
     * 날짜          | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 10.    parkjunhong77@gmail.com         최초 작성
     * 2026. 03. 09.    parkjunhong77@gmail.com         (3.0.0) JDK 25 마이그레이션: Stream API 및 toList() 최적화 적용
     * </pre>
     *
     * @param inStream
     *            {InputStream} 읽을 파일
     * @param charset
     *            {Charset} 문자열 셋
     * @param lineCount
     *            {long} 읽으려는 줄 수 (0 미만일 경우 전체)
     *
     * @return 읽어들인 문자열 목록 (List<String>)
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inStream})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 10.
     * @version 3.0.0
     */
    public static List<String> readLines(InputStream inStream, @Nullable Charset charset, final long lineCount) throws IOException {
        Objects.requireNonNull(inStream);

        // BufferedReader 라이프사이클(close)은 호출자의 책임이므로 try-with-resources는 생략합니다.
        BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, requireCharset(charset)));

        Stream<String> linesStream = reader.lines();

        if (lineCount >= 0) {
            linesStream = linesStream.limit(lineCount);
        }

        // JDK 16+의 toList()를 사용하여 불변 컬렉션으로 깔끔하게 반환
        return linesStream.toList();
    }

    /**
     * 주어진 파일을 줄단위로 읽어서 요청한 줄수 또는 전체(요청한 줄수가 전체 라인보다 큰 경우)를 제공합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 10.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param file
     *            읽을 파일
     * @param lineCount
     *            읽으려는 줄 수
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inStream})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 10.
     * @version 1.8.0
     */
    public static List<String> readLines(InputStream inStream, long lineCount) throws IOException {
        return readLines(inStream, defaultCharset(), lineCount);
    }

    /**
     * 주어진 파일을 줄단위로 읽어서 제공합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 2. 8.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param inStream
     * @param charsetName
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inStream})가 {@code null}인 경우 발생.
     *
     * @since 2020. 2. 8.
     * @version 1.8.0
     */
    public static List<String> readLines(InputStream inStream, @Nullable String charsetName) throws IOException {
        return readLines(inStream, charsetName, -1);
    }

    /**
     * 주어진 파일을 줄단위로 읽어서 요청한 줄수 또는 전체(요청한 줄수가 전체 라인보다 큰 경우)를 제공합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 10.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param inStream
     *            읽을 파일
     * @param lineCount
     *            읽으려는 줄 수
     * @param charset
     *            문자열 셋
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inStream})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 10.
     * @version 1.8.0
     */
    public static List<String> readLines(InputStream inStream, @Nullable String charsetName, long lineCount) throws IOException {
        return readLines(inStream, requireCharset(charsetName), lineCount);
    }

    /**
     * 주어진 파일을 줄단위로 읽어서 제공합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 2. 8.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param 읽을
     *            파일.
     *
     * @return
     *
     * @throws FileNotFoundException
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code path})가 {@code null}인 경우 발생.
     *
     * @since 2020. 2. 8.
     * @version 1.8.0
     */
    public static List<String> readLines(Path path) throws FileNotFoundException, IOException {
        return readLines(path, defaultCharset(), -1);
    }

    /**
     * 주어진 파일을 줄단위로 읽어서 제공합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 10.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param inStream
     *            읽을 파일
     * @param charset
     *            문자열 셋
     *
     * @return
     *
     * @throws FileNotFoundException
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code path})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 10.
     * @version 1.8.0
     */
    public static List<String> readLines(Path path, @Nullable Charset charset) throws FileNotFoundException, IOException {
        return readLines(path, charset, -1);
    }

    /**
     * 주어진 파일을 줄단위로 읽어서 요청한 줄수 또는 전체(요청한 줄수가 전체 라인보다 큰 경우)를 제공합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 10.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param inStream
     *            읽을 파일
     * @param charset
     *            문자열 셋
     * @param lineCount
     *            읽으려는 줄 수
     *
     * @return
     *
     * @throws FileNotFoundException
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code path})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 10.
     * @version 1.8.0
     */
    public static List<String> readLines(Path path, @Nullable Charset charset, final long lineCount) throws FileNotFoundException, IOException {
        Objects.requireNonNull(path);

        return readLines(path.toFile(), charset, lineCount);
    }

    /**
     * 주어진 파일을 줄단위로 읽어서 요청한 줄수 또는 전체(요청한 줄수가 전체 라인보다 큰 경우)를 제공합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 10.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param inStream
     *            읽을 파일
     * @param lineCount
     *            읽으려는 줄 수
     *
     * @return
     *
     * @throws FileNotFoundException
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code path})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 10.
     * @version 1.8.0
     */
    public static List<String> readLines(Path path, final long lineCount) throws FileNotFoundException, IOException {
        return readLines(path, Charset.defaultCharset(), lineCount);
    }

    /**
     * 주어진 파일을 줄단위로 읽어서 제공합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 10.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param path
     *            읽을 파일
     * @param charsetName
     *            문자열 셋
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code path})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 10.
     * @version 1.8.0
     */
    public static List<String> readLines(Path path, @Nullable String charsetName) throws IOException {
        return readLines(path, charsetName, -1);
    }

    /**
     * 주어진 파일을 줄단위로 읽어서 요청한 줄수 또는 전체(요청한 줄수가 전체 라인보다 큰 경우)를 제공합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 10.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param path
     *            읽을 파일
     * @param lineCount
     *            읽으려는 줄 수
     * @param charset
     *            문자열 셋
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code path})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 10.
     * @version 1.8.0
     */
    public static List<String> readLines(Path path, @Nullable String charsetName, long lineCount) throws IOException {
        return readLines(path, requireCharset(charsetName), lineCount);
    }

    /**
     * 주어진 파일을 줄단위로 읽어서 제공합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 2. 8.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param filepath
     *
     * @return
     *
     * @throws FileNotFoundException
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code filepath})가 {@code null}인 경우 발생.
     *
     * @since 2020. 2. 8.
     * @version 1.8.0
     */
    public static List<String> readLines(String filepath) throws FileNotFoundException, IOException {
        return readLines(filepath, defaultCharset(), -1);
    }

    /**
     * 주어진 파일을 줄단위로 읽어서 제공합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 10.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param inStream
     *            읽을 파일
     * @param charset
     *            문자열 셋
     *
     * @return
     *
     * @throws FileNotFoundException
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code filepath})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 10.
     * @version 1.8.0
     */
    public static List<String> readLines(String filepath, @Nullable Charset charset) throws FileNotFoundException, IOException {
        return readLines(filepath, charset, -1);
    }

    /**
     * 주어진 파일을 줄단위로 읽어서 요청한 줄수 또는 전체(요청한 줄수가 전체 라인보다 큰 경우)를 제공합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 10.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param inStream
     *            읽을 파일
     * @param charset
     *            문자열 셋
     * @param lineCount
     *            읽으려는 줄 수
     *
     * @return
     *
     * @throws FileNotFoundException
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code filepath})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 10.
     * @version 1.8.0
     */
    public static List<String> readLines(String filepath, @Nullable Charset charset, final long lineCount) throws FileNotFoundException, IOException {
        Objects.requireNonNull(filepath);

        return readLines(new File(filepath), charset, lineCount);
    }

    /**
     * 주어진 파일을 줄단위로 읽어서 요청한 줄수 또는 전체(요청한 줄수가 전체 라인보다 큰 경우)를 제공합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 10.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param inStream
     *            읽을 파일
     * @param lineCount
     *            읽으려는 줄 수
     *
     * @return
     *
     * @throws FileNotFoundException
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code filepath})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 10.
     * @version 1.8.0
     */
    public static List<String> readLines(String filepath, final long lineCount) throws FileNotFoundException, IOException {
        return readLines(filepath, defaultCharset(), lineCount);
    }

    /**
     * 주어진 파일을 줄단위로 읽어서 제공합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 10.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param filepath
     *            읽을 파일
     * @param charsetName
     *            문자열 셋
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code filepath})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 10.
     * @version 1.8.0
     */
    public static List<String> readLines(String filepath, @Nullable String charsetName) throws IOException {
        return readLines(filepath, charsetName, -1);
    }

    /**
     * 주어진 파일을 줄단위로 읽어서 요청한 줄수 또는 전체(요청한 줄수가 전체 라인보다 큰 경우)를 제공합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 10.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param filepath
     *            읽을 파일
     * @param lineCount
     *            읽으려는 줄 수
     * @param charset
     *            문자열 셋
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code filepath})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 10.
     * @version 1.8.0
     */
    public static List<String> readLines(String filepath, @Nullable String charsetName, long lineCount) throws IOException {
        return readLines(filepath, requireCharset(charsetName), lineCount);
    }

    /**
     * Non-blocking 모드의 {@link SocketChannel}로부터 지정된 길이만큼 데이터를 읽어 바이트 배열로 반환합니다. <br>
     * CPU 100% 점유(Busy-Spin)를 방지하기 위해 데이터가 없을 경우 짧은 대기(Back-off)를 수행하며, 지정된 타임아웃 시간을 초과하면 그때까지 읽은 데이터를 반환합니다. *
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2026. 03. 31.    parkjunhong77@gmail.com     Non-blocking 모드 전용 읽기 유틸리티 추가 (Busy-Spin 방지 및 Timeout 적용)
     * </pre>
     *
     * @param channel
     *            데이터를 읽어올 Non-blocking 소켓 채널 ({@code NOT nullable})
     * @param length
     *            읽어들일 최대 바이트 수
     * @param timeoutMillis
     *            최대 대기 시간 (밀리초 단위). 이 시간을 초과하면 루프를 탈출합니다.
     * @param close
     *            작업 완료 또는 예외 발생 시 채널을 닫을지 여부
     *
     * @return 채널에서 읽어들인 바이트 배열. 타임아웃이나 EOF에 도달한 경우 실제 읽은 크기만큼 잘라서 반환합니다.
     *
     * @throws IOException
     *             I/O 에러가 발생한 경우
     * @throws InterruptedIOException
     *             읽기 대기 중 스레드 인터럽트가 발생한 경우
     * @throws NullPointerException
     *             파라미터({@code channel})가 {@code null}인 경우 발생.
     *
     * @since 2026. 03. 31.
     * @version 3.0.0
     */
    public static byte[] readNonBlocking(SocketChannel channel, final int length, long timeoutMillis, boolean close) throws IOException {
        ObjectUtils.requireNonNulls(channel);

        if (length <= 0) {
            return new byte[0];
        }

        byte[] data = new byte[length];
        ByteBuffer buf = ByteBuffer.wrap(data);
        long startTime = System.currentTimeMillis();

        try {
            while (buf.hasRemaining()) {
                int count = channel.read(buf);

                if (count < 0) {
                    // EOF (End of Stream) 도달 시 즉시 탈출
                    break;
                }

                if (count == 0) {
                    // [2] Non-blocking 방어 로직: 타임아웃 검사
                    if (System.currentTimeMillis() - startTime > timeoutMillis) {
                        break;
                    }

                    // [3] CPU 100% 점유 방지 (Back-off 전략)
                    // 데이터가 도착할 때까지 스레드를 아주 짧게(1ms) 재워 OS에 CPU 제어권을 양보합니다.
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt(); // 인터럽트 상태 복구
                        throw new InterruptedIOException("Non-blocking 읽기 대기 중 스레드가 인터럽트 되었습니다.");
                    }
                }
            }

            int totalRead = buf.position();

            // [4] 아무것도 읽지 못한 경우 빈 배열 반환
            if (totalRead == 0) {
                return new byte[0];
            }

            // [5] 타임아웃 또는 조기 EOF 발생 시 실제 읽은 만큼만 잘라서 반환
            if (totalRead < length) {
                return Arrays.copyOf(data, totalRead);
            }

            return data;

        } catch (IOException e) {
            throw e;
        } finally {
            if (close) {
                IOUtils.close(channel);
            }
        }
    }

    /**
     * 입력받은 InputStream에서 주어진 길이만큼 데이터를 읽어서 반환합니다.
     *
     * @param inStream
     * @param length
     *            읽어올 데이터 길이
     *
     * @return InputStream으로부터 읽어온 데이터. 예외가 발생하는 경우 {@code null} 반환.
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inStream})가 {@code null}인 경우 발생.
     */
    public static byte[] readStream(InputStream inStream, final int length) throws IOException {
        return readStream(inStream, length, true);
    }

    /**
     * <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2017. 9. 6.      parkjunhong77@gmail.com         최초 작성
     * 2019. 8. 29.     parkjunhong77@gmail.com         내부 로직 성능 향상 및 안정성 강화
     * 2026. 3. 10.     parkjunhong77@gmail.com         (3.0.0) JDK 25 마이그레이션
     * </pre>
     *
     * @param inStream
     * @param length
     *            a number greater than 0
     * @param close
     *            소켓 close 여부
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inStream})가 {@code null}인 경우 발생.
     *
     * @since 2017. 9. 6.
     */
    public static byte[] readStream(InputStream inStream, final int length, boolean close) throws IOException {
        Objects.requireNonNull(inStream);

        try {
            return inStream.readNBytes(length);
        } catch (IOException e) {
            LOGGER.warn("데이터 읽는 도중 에러가 발생하였습니다. detail={}", e.getMessage(), e);
            throw e;
        } finally {
            if (close) {
                close(inStream);
            }
        }
    }

    /**
     * 파라미터에 해당하는 {@link Charset}을 제공합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2026. 3. 11.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param charset
     *            문자열 셋
     *
     * @return {@link Charset} 또는 {@code null}
     *
     * @since 2026. 3. 11.
     * @version 3.0.0
     */
    private static Charset requireCharset(@Nullable Charset charset) {
        return charset != null ? charset : requireCharset((String) null);
    }

    /**
     * 파라미터에 해당하는 {@link Charset}을 제공합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2026. 3. 11.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param charset
     *            문자열 셋
     *
     * @return {@link Charset} 또는 {@code null}
     *
     * @since 2026. 3. 11.
     * @version 3.0.0
     */
    private static Charset requireCharset(@Nullable String charset) {
        return charset != null //
                ? Charset.isSupported(charset) //
                        ? Charset.forName(charset) //
                        : StandardCharsets.UTF_8 //
                : defaultCharset();
    }

    /**
     * {@link InputStream}의 내용을 {@link OutputStream} 으로 전송합니다.<br>
     * 
     * </pre>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 9. 10.     parkjunhong77@gmail.com     최초 작성
     * 2026. 3. 10.     parkjunhong77@gmail.com         (3.0.0) JDK 25 마이그레이션: transferTo() 기반 Zero-copy 적용
     * </pre>
     *
     * @param inStream
     * @param closeInput
     *            {@link InputStream#close()} 호출 여부
     * @param outStream
     * @param closeOutput
     *            {@link OutputStream#close()} 호출 여부
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inStream} 또는 {@code outStream})가 {@code null}인 경우 발생.
     *
     * @since 2018. 9. 10.
     *
     * @see InputStream#transferTo(OutputStream)
     * @see InputStream#close()
     * @see OutputStream#close()
     */
    public static int transfer(InputStream inStream, boolean closeInput, OutputStream outStream, boolean closeOutput) throws IOException {
        ObjectUtils.requireNonNulls(inStream, outStream);

        try {
            long transferred = inStream.transferTo(outStream);
            outStream.flush();
            // 2GB 이상의 데이터를 복사했을 때 int 캐스팅에 의한 음수 반환을 방지하기 위해 최대값을 반환하도록 안전 장치 추가
            return transferred > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) transferred;
        } finally {
            if (closeInput) {
                close(inStream);
            }
            if (closeOutput) {
                close(outStream);
            }
        }
    }

    /**
     * {@link InputStream}의 내용을 {@link OutputStream} 으로 전송합니다.<br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 1. 14.     parkjunhong77@gmail.com     최초 작성
     * 2026. 3. 10.     parkjunhong77@gmail.com     (3.0.0) JDK 마이그레이션 결과 "readBufferSize" 속성은 사용하지 않음.
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
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inStream} 또는 {@code closeInput} 또는 {@code outStream} 또는 {@code closeOutput})가
     *             {@code null}인 경우 발생.
     *
     * @since 2021. 1. 14.
     *
     * @see #transfer(InputStream, boolean, OutputStream, boolean)
     */
    @Deprecated(since = "3.0.0", forRemoval = true)
    public static int transfer(InputStream inStream, boolean closeInput, OutputStream outStream, boolean closeOutput, int readBufferSize) throws IOException {
        return transfer(inStream, closeInput, outStream, closeOutput);
    }

    /**
     * {@link InputStream}의 내용을 {@link OutputStream} 으로 전송합니다.<br>
     * 
     * <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2026. 3. 11.     parkjunhong77@gmail.com         최초 작성
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
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inStream} 또는 {@code inCharset} 또는 {@code closeInput} 또는 {@code outStream} 또는
     *             {@code outCharset} 또는 {@code closeOutput})가 {@code null}인 경우 발생.
     *
     * @since 2026. 3. 11.
     * @version 3.0.0
     *
     * @see #transfer(InputStream, boolean, OutputStream, boolean)
     * @see #transfer(Reader, boolean, Writer, boolean)
     * @see InputStreamReader
     * @see OutputStreamReader
     */
    public static int transfer(InputStream inStream, Charset inCharset, boolean closeInput, OutputStream outStream, Charset outCharset, boolean closeOutput) throws IOException {
        ObjectUtils.requireNonNulls(inStream, inCharset, outStream, outCharset);

        // 입력과 출력의 인코딩이 동일하다면, 문자(Char) 디코딩을 생략하고 순수 바이트(Byte) 고속 복사를 수행합니다.
        if (inCharset.equals(outCharset)) {
            return transfer(inStream, closeInput, outStream, closeOutput);
        }
        // 인코딩이 다르다면, 문자로 변환(디코딩) 후 다시 인코딩하는 Transcoding 과정을 거칩니다.
        else {
            Reader reader = new InputStreamReader(inStream, inCharset);
            Writer writer = new OutputStreamWriter(outStream, outCharset);
            return transfer(reader, closeInput, writer, closeOutput);
        }
    }

    /**
     * {@link InputStream}를 통해 얻은 데이터를 {@link Writer}로 전달합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 9. 26.     parkjunhong77@gmail.com         최초 작성
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
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inStream} 또는 {@code inCharset} 또는 {@code closeReader} 또는 {@code writer} 또는
     *             {@code closeWriter})가 {@code null}인 경우 발생.
     *
     * @since 2018. 9. 26.
     *
     * @see #transfer(Reader, boolean, Writer, boolean)
     * @see InputStreamReader
     */
    public static int transfer(InputStream inStream, Charset inCharset, boolean closeReader, Writer writer, boolean closeWriter) throws IOException {
        ObjectUtils.requireNonNulls(inStream, inCharset, writer);

        return transfer(new InputStreamReader(inStream, inCharset), closeReader, writer, closeWriter);
    }

    /**
     * {@link InputStream}를 통해 얻은 데이터를 {@link Writer}로 전달합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 1. 14.     parkjunhong77@gmail.com         최초 작성
     * 2026. 3. 10.     parkjunhong77@gmail.com     (3.0.0) JDK 마이그레이션 결과 "readBufferSize" 속성은 사용하지 않음.
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
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inStream} 또는 {@code inCharset} 또는 {@code closeReader} 또는 {@code writer} 또는
     *             {@code closeWriter})가 {@code null}인 경우 발생.
     *
     * @since 2021. 1. 14.
     *
     * @see #transfer(Reader, boolean, Writer, boolean)
     * @see InputStreamReader
     */
    @Deprecated(since = "3.0.0", forRemoval = true)
    public static int transfer(InputStream inStream, Charset inCharset, boolean closeReader, Writer writer, boolean closeWriter, int readBufferSize) throws IOException {
        ObjectUtils.requireNonNulls(inStream, inCharset, writer);

        return transfer(new InputStreamReader(inStream, inCharset), closeReader, writer, closeWriter);
    }

    /**
     * {@link InputStream}의 내용을 {@link OutputStream} 으로 전송합니다.<br>
     * 전송 후 {@link InputStream}, {@link OutputStream}은 모두 close 된다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 9. 10.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param inStream
     * @param inCharset
     *            Charset of an {@link InputStream}
     * @param outStream
     * @param outCharset
     *            Charset of an {@link OutputStream}
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inStream} 또는 {@code inCharset} 또는 {@code outStream} 또는 {@code outCharset})가 {@code null}인
     *             경우 발생.
     *
     * @since 2018. 9. 10.
     *
     * @see #transfer(InputStream, Charset, boolean, OutputStream, Charset, boolean)
     */
    public static int transfer(InputStream inStream, Charset inCharset, OutputStream outStream, Charset outCharset) throws IOException {
        return transfer(inStream, inCharset, true, outStream, outCharset, true);
    }

    /**
     * {@link InputStream}의 내용을 {@link OutputStream} 으로 전송합니다.<br>
     * 전송 후 {@link InputStream}, {@link OutputStream}은 모두 close 된다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 1. 14.     parkjunhong77@gmail.com         최초 작성
     * 2026. 3. 10.     parkjunhong77@gmail.com     (3.0.0) JDK 마이그레이션 결과 "readBufferSize" 속성은 사용하지 않음.
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
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inStream} 또는 {@code inCharset} 또는 {@code outStream} 또는 {@code outCharset})가 {@code null}인
     *             경우 발생.
     *
     * @since 2021. 1. 14.
     *
     * @see #transfer(InputStream, Charset, boolean, OutputStream, Charset, boolean)
     */
    @Deprecated(since = "3.0.0", forRemoval = true)
    public static int transfer(InputStream inStream, Charset inCharset, OutputStream outStream, Charset outCharset, int readBufferSize) throws IOException {
        return transfer(inStream, inCharset, true, outStream, outCharset, true);
    }

    /**
     * {@link InputStream}를 통해 얻은 데이터를 {@link Writer}로 전달합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 9. 26.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param inStream
     * @param inCharset
     *            Charset of an {@link InputStream}
     * @param writer
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inStream} 또는 {@code inCharset} 또는 {@code writer})가 {@code null}인 경우 발생.
     *
     * @since 2018. 9. 26.
     *
     * @see #transfer(Reader, boolean, Writer, boolean)
     */
    public static int transfer(InputStream inStream, Charset inCharset, Writer writer) throws IOException {
        ObjectUtils.requireNonNulls(inStream, inCharset, writer);

        return transfer(new InputStreamReader(inStream, inCharset), true, writer, true);
    }

    /**
     * {@link InputStream}를 통해 얻은 데이터를 {@link Writer}로 전달합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 9. 26.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param inStream
     * @param inCharset
     *            Charset of an {@link InputStream}
     * @param writer
     * @param close
     *            {@link Reader#close()}, {@link OutputStream#close()} 호출 여부
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inStream} 또는 {@code inCharset} 또는 {@code writer} 또는 {@code close})가 {@code null}인 경우 발생.
     *
     * @since 2018. 9. 26.
     *
     * @see #transfer(Reader, boolean, Writer, boolean)
     */
    public static int transfer(InputStream inStream, Charset inCharset, Writer writer, boolean close) throws IOException {
        ObjectUtils.requireNonNulls(inStream, inCharset, writer);

        return transfer(new InputStreamReader(inStream, inCharset), close, writer, close);
    }

    /**
     * {@link InputStream}를 통해 얻은 데이터를 {@link Writer}로 전달합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 1. 14.     parkjunhong77@gmail.com         최초 작성
     * 2026. 3. 10.     parkjunhong77@gmail.com     (3.0.0) JDK 마이그레이션 결과 "readBufferSize" 속성은 사용하지 않음.
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
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inStream} 또는 {@code inCharset} 또는 {@code writer} 또는 {@code close})가 {@code null}인 경우 발생.
     *
     * @since 2021. 1. 14.
     *
     * @see #transfer(Reader, boolean, Writer, boolean)
     */
    @Deprecated(since = "3.0.0", forRemoval = true)
    public static int transfer(InputStream inStream, Charset inCharset, Writer writer, boolean close, int readBufferSize) throws IOException {
        ObjectUtils.requireNonNulls(inStream, inCharset, writer);

        return transfer(new InputStreamReader(inStream, inCharset), close, writer, close);
    }

    /**
     * {@link InputStream}를 통해 얻은 데이터를 {@link Writer}로 전달합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 1. 14.     parkjunhong77@gmail.com         최초 작성
     * 2026. 3. 10.     parkjunhong77@gmail.com     (3.0.0) JDK 마이그레이션 결과 "readBufferSize" 속성은 사용하지 않음.
     * </pre>
     *
     * @param inStream
     * @param inCharset
     *            Charset of an {@link InputStream}
     * @param writer
     * @param readBufferSize
     *            데이터 읽기 버퍼 크기
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inStream} 또는 {@code inCharset} 또는 {@code writer})가 {@code null}인 경우 발생.
     *
     * @since 2021. 1. 14.
     *
     * @see #transfer(Reader, boolean, Writer, boolean)
     */
    @Deprecated(since = "3.0.0", forRemoval = true)
    public static int transfer(InputStream inStream, Charset inCharset, Writer writer, int readBufferSize) throws IOException {
        return transfer(inStream, inCharset, writer, true);
    }

    /**
     * {@link InputStream}의 내용을 {@link OutputStream} 으로 전송합니다.<br>
     * 전송 후 {@link InputStream}, {@link OutputStream}은 모두 close 된다.
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2014. 4. 14.     parkjunhong77@gmail.com         최초 작성
     * 2018. 9.10.      parkjunhong77@gmail.com         내부 메소드 호출로 변경
     * </pre>
     *
     * @param inStream
     * @param outStream
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inStream} 또는 {@code outStream})가 {@code null}인 경우 발생.
     *
     * @since 2018. 9. 10.
     * @since 2014. 4. 14.
     *
     * @see #transfer(InputStream, boolean, OutputStream, boolean)
     */
    public static int transfer(InputStream inStream, OutputStream outStream) throws IOException {
        return transfer(inStream, true, outStream, true);
    }

    /**
     * {@link InputStream}의 내용을 {@link OutputStream} 으로 전송합니다.<br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 9. 10.     parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param inStream
     * @param outStream
     * @param close
     *            {@link OutputStream#close()} 호출 여부
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inStream} 또는 {@code outStream} 또는 {@code close})가 {@code null}인 경우 발생.
     *
     * @since 2018. 9. 10.
     *
     * @see #transfer(InputStream, boolean, OutputStream, boolean)
     */
    public static int transfer(InputStream inStream, OutputStream outStream, boolean close) throws IOException {
        return transfer(inStream, close, outStream, close);
    }

    /**
     * {@link InputStream}의 내용을 {@link OutputStream} 으로 전송합니다.<br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 1. 14.     parkjunhong77@gmail.com         최초 작성
     * 2026. 3. 10.     parkjunhong77@gmail.com     (3.0.0) JDK 마이그레이션 결과 "readBufferSize" 속성은 사용하지 않음.
     * </pre>
     *
     * @param inStream
     * @param outStream
     * @param close
     *            {@link OutputStream#close()} 호출 여부
     * @param readBufferSize
     *            데이터 읽기 버퍼 크기
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inStream} 또는 {@code outStream} 또는 {@code close})가 {@code null}인 경우 발생.
     *
     * @since 2021. 1. 14.
     *
     * @see #transfer(InputStream, boolean, OutputStream, boolean)
     */
    @Deprecated(since = "3.0.0", forRemoval = true)
    public static int transfer(InputStream inStream, OutputStream outStream, boolean close, int readBufferSize) throws IOException {
        return transfer(inStream, close, outStream, close);
    }

    /**
     * {@link InputStream}의 내용을 {@link OutputStream} 으로 전송합니다.<br>
     * 전송 후 {@link InputStream}, {@link OutputStream}은 모두 close 된다.
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 9. 10.     parkjunhong77@gmail.com     최초 작성
     * 2026. 3. 11.     parkjunhong77@gmail.com     {@link InputStream}과 {@link OutputStream}에 적용되는 {@link Charset}이 동일한 경우, 인코딩/디코딩 과정 없이 byte[] 수준에서 처리.
     * </pre>
     *
     * @param inStream
     * @param outStream
     * @param charset
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inStream} 또는 {@code outStream})가 {@code null}인 경우 발생.
     *
     * @since 2018. 9. 10.
     *
     * @see #transfer(InputStream, boolean, OutputStream, boolean)
     */
    public static int transfer(InputStream inStream, OutputStream outStream, Charset charset) throws IOException {
        return transfer(inStream, true, outStream, true);
    }

    /**
     * {@link InputStream}의 내용을 {@link OutputStream} 으로 전송합니다.<br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 9. 10.     parkjunhong77@gmail.com     최초 작성
     * 2026. 3. 11.     parkjunhong77@gmail.com     {@link InputStream}과 {@link OutputStream}에 적용되는 {@link Charset}이 동일한 경우, 인코딩/디코딩 과정 없이 byte[] 수준에서 처리.
     * </pre>
     *
     * @param inStream
     * @param outStream
     * @param charset
     *            Charset of an {@link OutputStream}
     * @param close
     *            {@link OutputStream#close()} 호출 여부
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inStream} 또는 {@code outStream} 또는 {@code close})가 {@code null}인 경우 발생.
     *
     * @since 2018. 9. 10.
     *
     * @see #transfer(InputStream, String, boolean, OutputStream, String, boolean)
     */
    public static int transfer(InputStream inStream, OutputStream outStream, Charset charset, boolean close) throws IOException {
        return transfer(inStream, close, outStream, close);
    }

    /**
     * {@link InputStream}의 내용을 {@link OutputStream} 으로 전송합니다.<br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 1. 14.     parkjunhong77@gmail.com         최초 작성
     * 2026. 3. 10.     parkjunhong77@gmail.com     (3.0.0) JDK 마이그레이션 결과 "readBufferSize" 속성은 사용하지 않음.
     * 2026. 3. 11.     parkjunhong77@gmail.com     {@link InputStream}과 {@link OutputStream}에 적용되는 {@link Charset}이 동일한 경우, 인코딩/디코딩 과정 없이 byte[] 수준에서 처리.
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
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inStream} 또는 {@code outStream} 또는 {@code close})가 {@code null}인 경우 발생.
     *
     * @since 2021. 1. 14.
     *
     * @see #transfer(InputStream, boolean, OutputStream, boolean)
     */
    @Deprecated(since = "3.0.0", forRemoval = true)
    public static int transfer(InputStream inStream, OutputStream outStream, Charset charset, boolean close, int readBufferSize) throws IOException {
        return transfer(inStream, close, outStream, close);
    }

    /**
     * {@link InputStream}의 내용을 {@link OutputStream} 으로 전송합니다.<br>
     * 전송 후 {@link InputStream}, {@link OutputStream}은 모두 close 된다.
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 1. 14.     parkjunhong77@gmail.com         최초 작성
     * 2026. 3. 10.     parkjunhong77@gmail.com     (3.0.0) JDK 마이그레이션 결과 "readBufferSize" 속성은 사용하지 않음.
     * 2026. 3. 11.     parkjunhong77@gmail.com     {@link InputStream}과 {@link OutputStream}에 적용되는 {@link Charset}이 동일한 경우, 인코딩/디코딩 과정 없이 byte[] 수준에서 처리.
     * </pre>
     *
     * @param inStream
     * @param outStream
     * @param charset
     * @param readBufferSize
     *            데이터 읽기 버퍼 크기
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inStream} 또는 {@code outStream})가 {@code null}인 경우 발생.
     *
     * @since 2021. 1. 14.
     *
     * @see #transfer(InputStream, boolean, OutputStream, boolean)
     */
    @Deprecated(since = "3.0.0", forRemoval = true)
    public static int transfer(InputStream inStream, OutputStream outStream, Charset charset, int readBufferSize) throws IOException {
        return transfer(inStream, true, outStream, true);
    }

    /**
     * {@link InputStream}의 내용을 {@link OutputStream} 으로 전송합니다.<br>
     * 전송 후 {@link InputStream}, {@link OutputStream}은 모두 close 된다.
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 1. 14.     parkjunhong77@gmail.com         최초 작성
     * 2026. 3. 10.     parkjunhong77@gmail.com     (3.0.0) JDK 마이그레이션 결과 "readBufferSize" 속성은 사용하지 않음.
     * </pre>
     *
     * @param inStream
     * @param outStream
     * @param readBufferSize
     *            데이터 읽기 버퍼 크기
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inStream} 또는 {@code outStream})가 {@code null}인 경우 발생.
     *
     * @since 2018. 9. 10.
     * @since 2021. 1. 14.
     *
     * @see #transfer(InputStream, boolean, OutputStream, boolean)
     */
    @Deprecated(since = "3.0.0", forRemoval = true)
    public static int transfer(InputStream inStream, OutputStream outStream, int readBufferSize) throws IOException {
        return transfer(inStream, true, outStream, true);
    }

    /**
     * {@link InputStream}의 내용을 {@link OutputStream} 으로 전송합니다.<br>
     * 전송 후 {@link InputStream}, {@link OutputStream}은 모두 close 된다.
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2014. 4. 14.     parkjunhong77@gmail.com         최초 작성
     * 2018. 9.10.      parkjunhong77@gmail.com         내부 메소드 호출로 변경
     * 2026. 3. 11.     parkjunhong77@gmail.com     {@link InputStream}과 {@link OutputStream}에 적용되는 {@link Charset}이 동일한 경우, 인코딩/디코딩 과정 없이 byte[] 수준에서 처리.
     * </pre>
     *
     * @param inStream
     * @param outStream
     * @param charset
     *            charset
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inStream} 또는 {@code outStream})가 {@code null}인 경우 발생.
     *
     * @since 2014. 4. 14.
     *
     * @see #transfer(InputStream, String, boolean, OutputStream, String, boolean)
     */
    public static int transfer(InputStream inStream, OutputStream outStream, String charset) throws IOException {
        return transfer(inStream, true, outStream, true);
    }

    /**
     * {@link InputStream}의 내용을 {@link OutputStream} 으로 전송합니다.<br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 9. 10.     parkjunhong77@gmail.com     최초 작성
     * 2026. 3. 11.     parkjunhong77@gmail.com     {@link InputStream}과 {@link OutputStream}에 적용되는 {@link Charset}이 동일한 경우, 인코딩/디코딩 과정 없이 byte[] 수준에서 처리.
     * </pre>
     *
     * @param inStream
     * @param outStream
     * @param charset
     *            Charset of an {@link OutputStream}
     * @param close
     *            {@link OutputStream#close()} 호출 여부
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inStream} 또는 {@code outStream} 또는 {@code close})가 {@code null}인 경우 발생.
     *
     * @since 2018. 9. 10.
     *
     * @see #transfer(InputStream, boolean, OutputStream, boolean)
     */
    public static int transfer(InputStream inStream, OutputStream outStream, String charset, boolean close) throws IOException {
        return transfer(inStream, close, outStream, close);
    }

    /**
     * {@link InputStream}의 내용을 {@link OutputStream} 으로 전송합니다.<br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 1. 14.     parkjunhong77@gmail.com         최초 작성
     * 2026. 3. 10.     parkjunhong77@gmail.com     (3.0.0) JDK 마이그레이션 결과 "readBufferSize" 속성은 사용하지 않음.
     * 2026. 3. 11.     parkjunhong77@gmail.com     {@link InputStream}과 {@link OutputStream}에 적용되는 {@link Charset}이 동일한 경우, 인코딩/디코딩 과정 없이 byte[] 수준에서 처리.
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
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inStream} 또는 {@code outStream} 또는 {@code close})가 {@code null}인 경우 발생.
     *
     * @since 2021. 1. 14.
     *
     * @see #transfer(InputStream, boolean, OutputStream, boolean)
     */
    @Deprecated(since = "3.0.0", forRemoval = true)
    public static int transfer(InputStream inStream, OutputStream outStream, String charset, boolean close, int readBufferSize) throws IOException {
        return transfer(inStream, close, outStream, close);
    }

    /**
     * {@link InputStream}의 내용을 {@link OutputStream} 으로 전송합니다.<br>
     * 전송 후 {@link InputStream}, {@link OutputStream}은 모두 close 된다.
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 1. 14.     parkjunhong77@gmail.com         최초 작성
     * 2026. 3. 10.     parkjunhong77@gmail.com     (3.0.0) JDK 마이그레이션 결과 "readBufferSize" 속성은 사용하지 않음.
     * 2026. 3. 11.     parkjunhong77@gmail.com     {@link InputStream}과 {@link OutputStream}에 적용되는 {@link Charset}이 동일한 경우, 인코딩/디코딩 과정 없이 byte[] 수준에서 처리.
     * </pre>
     *
     * @param inStream
     * @param outStream
     * @param charset
     *            charset
     * @param readBufferSize
     *            데이터 읽기 버퍼 크기
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inStream} 또는 {@code outStream} 또는 {@code charset})가 {@code null}인 경우 발생.
     *
     * @since 2021. 1. 14.
     *
     * @see #transfer(InputStream, boolean, OutputStream, boolean)
     */
    @Deprecated(since = "3.0.0", forRemoval = true)
    public static int transfer(InputStream inStream, OutputStream outStream, String charset, int readBufferSize) throws IOException {
        return transfer(inStream, charset, true, outStream, charset, true);
    }

    /**
     * {@link InputStream}의 내용을 {@link OutputStream} 으로 전송합니다.<br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 9. 10.     parkjunhong77@gmail.com     최초 작성
     * 2026. 3. 11.     parkjunhong77@gmail.com     charset 정보 검증 추가.
     * 2026. 3. 11.     parkjunhong77@gmail.com     {@link InputStream}과 {@link OutputStream}에 적용되는 {@link Charset}이 동일한 경우, 인코딩/디코딩 과정 없이 byte[] 수준에서 처리.
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
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inStream} 또는 {@code inCharset} 또는 {@code closeInput} 또는 {@code outStream} 또는
     *             {@code outCharset} 또는 {@code closeOutput})가 {@code null}인 경우 발생.
     *
     * @since 2018. 9. 10.
     *
     * @see #requireCharset(String)
     * @see #transfer(Reader, boolean, Writer, boolean)
     */
    public static int transfer(InputStream inStream, String inCharset, boolean closeInput, OutputStream outStream, String outCharset, boolean closeOutput) throws IOException {
        ObjectUtils.requireNonNulls(inStream, inCharset, outStream, outCharset);

        return transfer(inStream, requireCharset(inCharset), closeInput, outStream, requireCharset(outCharset), closeOutput);
    }

    /**
     * {@link InputStream}의 내용을 {@link OutputStream} 으로 전송합니다.<br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 1. 14.     parkjunhong77@gmail.com         최초 작성
     * 2026. 3. 10.     parkjunhong77@gmail.com     (3.0.0) JDK 마이그레이션 결과 "readBufferSize" 속성은 사용하지 않음.
     * 2026. 3. 11.     parkjunhong77@gmail.com     charset 정보 검증 추가.
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
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inStream} 또는 {@code inCharset} 또는 {@code closeInput} 또는 {@code outStream} 또는
     *             {@code outCharset} 또는 {@code closeOutput})가 {@code null}인 경우 발생.
     *
     * @since 2021. 1. 14.
     *
     * @see #transfer(Reader, boolean, Writer, boolean)
     */
    @Deprecated(since = "3.0.0", forRemoval = true)
    public static int transfer(InputStream inStream, String inCharset, boolean closeInput, OutputStream outStream, String outCharset, boolean closeOutput, int readBufferSize)
            throws IOException {
        ObjectUtils.requireNonNulls(inStream, inCharset, outStream, outCharset);

        return transfer(new InputStreamReader(inStream, inCharset), closeInput, new OutputStreamWriter(outStream, outCharset), closeOutput);
    }

    /**
     * {@link InputStream}를 통해 얻는 데이터를 {@link Writer}로 전달합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 9. 26.     parkjunhong77@gmail.com         최초 작성
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
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inStream} 또는 {@code inCharset} 또는 {@code closeReader} 또는 {@code writer} 또는
     *             {@code closeWriter})가 {@code null}인 경우 발생.
     *
     * @since 2018. 9. 26.
     *
     * @see #transfer(Reader, boolean, Writer, boolean)
     */
    public static int transfer(InputStream inStream, String inCharset, boolean closeReader, Writer writer, boolean closeWriter) throws IOException {
        ObjectUtils.requireNonNulls(inStream, inCharset, writer);

        return transfer(new InputStreamReader(inStream, inCharset), closeReader, writer, closeWriter);
    }

    /**
     * {@link InputStream}를 통해 얻는 데이터를 {@link Writer}로 전달합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 1. 14.     parkjunhong77@gmail.com         최초 작성
     * 2026. 3. 10.     parkjunhong77@gmail.com     (3.0.0) JDK 마이그레이션 결과 "readBufferSize" 속성은 사용하지 않음.
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
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inStream} 또는 {@code inCharset} 또는 {@code closeReader} 또는 {@code writer} 또는
     *             {@code closeWriter})가 {@code null}인 경우 발생.
     *
     * @since 2021. 1. 14.
     *
     * @see #transfer(Reader, boolean, Writer, boolean)
     */
    @Deprecated(since = "3.0.0", forRemoval = true)
    public static int transfer(InputStream inStream, String inCharset, boolean closeReader, Writer writer, boolean closeWriter, int readBufferSize) throws IOException {
        ObjectUtils.requireNonNulls(inStream, inCharset, writer);

        return transfer(new InputStreamReader(inStream, inCharset), closeReader, writer, closeWriter);
    }

    /**
     * {@link InputStream}의 내용을 {@link OutputStream} 으로 전송합니다.<br>
     * 전송 후 {@link InputStream}, {@link OutputStream}은 모두 close 된다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 9. 10.     parkjunhong77@gmail.com         최초 작성
     * 2026. 3. 11.     parkjunhong77@gmail.com     charset 정보 검증 추가.
     * </pre>
     *
     * @param inStream
     * @param inCharset
     *            Charset of an {@link InputStream}
     * @param outStream
     * @param outCharset
     *            Charset of an {@link OutputStream}
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inStream} 또는 {@code inCharset} 또는 {@code outStream} 또는 {@code outCharset})가 {@code null}인
     *             경우 발생.
     *
     * @since 2018. 9. 10.
     *
     * @see #requireCharset(String)
     * @see #transfer(InputStream, Charset, boolean, OutputStream, Charset, boolean)
     */
    public static int transfer(InputStream inStream, String inCharset, OutputStream outStream, String outCharset) throws IOException {
        ObjectUtils.requireNonNulls(inStream, inCharset, outStream, outCharset);

        return transfer(inStream, requireCharset(inCharset), true, outStream, requireCharset(outCharset), true);
    }

    /**
     * {@link InputStream}의 내용을 {@link OutputStream} 으로 전송합니다.<br>
     * 전송 후 {@link InputStream}, {@link OutputStream}은 모두 close 된다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 1. 14.     parkjunhong77@gmail.com         최초 작성
     * 2026. 3. 10.     parkjunhong77@gmail.com     (3.0.0) JDK 마이그레이션 결과 "readBufferSize" 속성은 사용하지 않음.
     * 2026. 3. 11.     parkjunhong77@gmail.com     charset 정보 검증 추가.
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
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inStream} 또는 {@code inCharset} 또는 {@code outStream} 또는 {@code outCharset})가 {@code null}인
     *             경우 발생.
     *
     * @since 2021. 1. 14.
     *
     * @see #requireCharset(String)
     * @see #transfer(InputStream, Charset, boolean, OutputStream, Charset, boolean)
     */
    @Deprecated(since = "3.0.0", forRemoval = true)
    public static int transfer(InputStream inStream, String inCharset, OutputStream outStream, String outCharset, int readBufferSize) throws IOException {
        ObjectUtils.requireNonNulls(inStream, inCharset, outStream, outCharset);

        return transfer(inStream, requireCharset(inCharset), true, outStream, requireCharset(outCharset), true);
    }

    /**
     * {@link InputStream}를 통해 얻은 데이터를 {@link Writer}로 전달합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 9. 26.     parkjunhong77@gmail.com         최초 작성
     * 2026. 3. 11.     parkjunhong77@gmail.com     charset 정보 검증 추가.
     * </pre>
     *
     * @param inStream
     * @param inCharset
     *            Charset of an {@link InputStream}
     * @param writer
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inStream} 또는 {@code inCharset} 또는 {@code writer})가 {@code null}인 경우 발생.
     *
     * @since 2018. 9. 26.
     *
     * @see #transfer(Reader, boolean, Writer, boolean)
     */
    public static int transfer(InputStream inStream, String inCharset, Writer writer) throws IOException {
        ObjectUtils.requireNonNulls(inStream, inCharset, writer);

        return transfer(new InputStreamReader(inStream, requireCharset(inCharset)), true, writer, true);
    }

    /**
     * {@link InputStream}를 통해 얻은 데이터를 {@link Writer}로 전달합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 9. 26.     parkjunhong77@gmail.com         최초 작성
     * 2026. 3. 11.     parkjunhong77@gmail.com     charset 정보 검증 추가.
     * </pre>
     *
     * @param inStream
     * @param inCharset
     *            Charset of an {@link InputStream}
     * @param writer
     * @param close
     *            {@link Reader#close()}, {@link OutputStream#close()} 호출 여부
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inStream} 또는 {@code inCharset} 또는 {@code writer} 또는 {@code close})가 {@code null}인 경우 발생.
     *
     * @since 2018. 9. 26.
     *
     * @see #transfer(Reader, boolean, Writer, boolean)
     */
    public static int transfer(InputStream inStream, String inCharset, Writer writer, boolean close) throws IOException {
        ObjectUtils.requireNonNulls(inStream, inCharset, writer);

        return transfer(new InputStreamReader(inStream, requireCharset(inCharset)), close, writer, close);
    }

    /**
     * {@link InputStream}를 통해 얻은 데이터를 {@link Writer}로 전달합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 1. 14.     parkjunhong77@gmail.com         최초 작성
     * 2026. 3. 10.     parkjunhong77@gmail.com     (3.0.0) JDK 마이그레이션 결과 "readBufferSize" 속성은 사용하지 않음.
     * 2026. 3. 11.     parkjunhong77@gmail.com     charset 정보 검증 추가.
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
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inStream} 또는 {@code inCharset} 또는 {@code writer} 또는 {@code close})가 {@code null}인 경우 발생.
     *
     * @since 2021. 1. 14.
     *
     * @see #transfer(Reader, boolean, Writer, boolean)
     */
    @Deprecated(since = "3.0.0", forRemoval = true)
    public static int transfer(InputStream inStream, String inCharset, Writer writer, boolean close, int readBufferSize) throws IOException {
        ObjectUtils.requireNonNulls(inStream, inCharset, writer, close);

        return transfer(new InputStreamReader(inStream, requireCharset(inCharset)), close, writer, close);
    }

    /**
     * {@link InputStream}를 통해 얻은 데이터를 {@link Writer}로 전달합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 1. 14.     parkjunhong77@gmail.com         최초 작성
     * 2026. 3. 10.     parkjunhong77@gmail.com     (3.0.0) JDK 마이그레이션 결과 "readBufferSize" 속성은 사용하지 않음.
     * 2026. 3. 11.     parkjunhong77@gmail.com     charset 정보 검증 추가.
     * </pre>
     *
     * @param inStream
     * @param inCharset
     *            Charset of an {@link InputStream}
     * @param writer
     * @param readBufferSize
     *            데이터 읽기 버퍼 크기
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inStream} 또는 {@code inCharset} 또는 {@code writer})가 {@code null}인 경우 발생.
     *
     * @since 2021. 1. 14.
     *
     * @see #transfer(Reader, boolean, Writer, boolean)
     */
    @Deprecated(since = "3.0.0", forRemoval = true)
    public static int transfer(InputStream inStream, String inCharset, Writer writer, int readBufferSize) throws IOException {
        ObjectUtils.requireNonNulls(inStream, inCharset, writer);

        return transfer(new InputStreamReader(inStream, requireCharset(inCharset)), true, writer, true);
    }

    /**
     * {@link Reader}를 통해 얻은 데이타를 {@link OutputStream}으로 전달합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 9. 26.     parkjunhong77@gmail.com         최초 작성
     * 2026. 3. 11.     parkjunhong77@gmail.com     charset 정보 검증 추가.
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
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code reader} 또는 {@code closeReader} 또는 {@code outStream} 또는 {@code outCharset} 또는
     *             {@code closeOutput})가 {@code null}인 경우 발생.
     *
     * @since 2018. 9. 26.
     *
     * @see #transfer(Reader, boolean, Writer, boolean)
     */
    public static int transfer(Reader reader, boolean closeReader, OutputStream outStream, Charset outCharset, boolean closeOutput) throws IOException {
        ObjectUtils.requireNonNulls(reader, outStream, outCharset);

        return transfer(reader, closeReader, new OutputStreamWriter(outStream, requireCharset(outCharset)), closeOutput);
    }

    /**
     * {@link Reader}를 통해 얻은 데이타를 {@link OutputStream}으로 전달합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 1. 14.     parkjunhong77@gmail.com         최초 작성
     * 2026. 3. 10.     parkjunhong77@gmail.com     (3.0.0) JDK 마이그레이션 결과 "readBufferSize" 속성은 사용하지 않음.
     * 2026. 3. 11.     parkjunhong77@gmail.com     charset 정보 검증 추가.
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
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code reader} 또는 {@code closeReader} 또는 {@code outStream} 또는 {@code outCharset} 또는
     *             {@code closeOutput})가 {@code null}인 경우 발생.
     *
     * @since 2021. 1. 14.
     *
     * @see #transfer(Reader, boolean, Writer, boolean)
     */
    @Deprecated(since = "3.0.0", forRemoval = true)
    public static int transfer(Reader reader, boolean closeReader, OutputStream outStream, Charset outCharset, boolean closeOutput, int readBufferSize) throws IOException {
        ObjectUtils.requireNonNulls(reader, outStream, outCharset);

        return transfer(reader, closeReader, new OutputStreamWriter(outStream, requireCharset(outCharset)), closeOutput);
    }

    /**
     * {@link Reader}를 통해 얻는 데이터를 {@link OutputStream}로 전달합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 9. 26.     parkjunhong77@gmail.com         최초 작성
     * 2026. 3. 11.     parkjunhong77@gmail.com     charset 정보 검증 추가.
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
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code reader} 또는 {@code closeReader} 또는 {@code outStream} 또는 {@code outCharset} 또는
     *             {@code closeOutput})가 {@code null}인 경우 발생.
     *
     * @since 2018. 9. 26.
     *
     * @see #transfer(Reader, boolean, Writer, boolean)
     */
    public static int transfer(Reader reader, boolean closeReader, OutputStream outStream, String outCharset, boolean closeOutput) throws IOException {
        ObjectUtils.requireNonNulls(reader, outStream, outCharset);

        return transfer(reader, closeReader, new OutputStreamWriter(outStream, requireCharset(outCharset)), closeOutput);
    }

    /**
     * {@link Reader}를 통해 얻는 데이터를 {@link OutputStream}로 전달합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 1. 14.     parkjunhong77@gmail.com         최초 작성
     * 2026. 3. 10.     parkjunhong77@gmail.com     (3.0.0) JDK 마이그레이션 결과 "readBufferSize" 속성은 사용하지 않음.
     * 2026. 3. 11.     parkjunhong77@gmail.com     charset 정보 검증 추가.
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
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code reader} 또는 {@code closeReader} 또는 {@code outStream} 또는 {@code outCharset} 또는
     *             {@code closeOutput})가 {@code null}인 경우 발생.
     *
     * @since 2021. 1. 14.
     *
     * @see #transfer(Reader, boolean, Writer, boolean)
     */
    @Deprecated(since = "3.0.0", forRemoval = true)
    public static int transfer(Reader reader, boolean closeReader, OutputStream outStream, String outCharset, boolean closeOutput, int readBufferSize) throws IOException {
        ObjectUtils.requireNonNulls(reader, outStream, outCharset);

        return transfer(reader, closeReader, new OutputStreamWriter(outStream, requireCharset(outCharset)), closeOutput);
    }

    /**
     * {@link Reader}를 통해 얻는 데이터를 {@link Writer}로 전달합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 9. 26.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param reader
     * @param closeInput
     *            {@link InputStream#close()} 호출 여부
     * @param writer
     * @param closeOutput
     *            {@link OutputStream#close()} 호출 여부
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code reader} 또는 {@code writer})가 {@code null}인 경우 발생.
     *
     * @since 2018. 9. 26.
     */
    public static int transfer(Reader reader, boolean closeReader, Writer writer, boolean closeWriter) throws IOException {
        ObjectUtils.requireNonNulls(reader, writer);

        try {
            long transferred = reader.transferTo(writer);
            writer.flush();
            return transferred > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) transferred;
        } finally {
            if (closeReader)
                close(reader);
            if (closeWriter)
                close(writer);
        }
    }

    /**
     * {@link Reader}를 통해 얻는 데이터를 {@link Writer}로 전달합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 1. 14.     parkjunhong77@gmail.com         최초 작성
     * 2026. 3. 10.     parkjunhong77@gmail.com     (3.0.0) JDK 마이그레이션 결과 "readBufferSize" 속성은 사용하지 않음.
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
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code reader} 또는 {@code closeReader} 또는 {@code writer} 또는 {@code closeWriter})가 {@code null}인
     *             경우 발생.
     *
     * @since 2021. 1. 14.
     *
     * @see #transfer(Reader, boolean, Writer, boolean)
     */
    @Deprecated(since = "3.0.0", forRemoval = true)
    public static int transfer(Reader reader, boolean closeReader, Writer writer, boolean closeWriter, int readBufferSize) throws IOException {
        return transfer(reader, closeReader, writer, closeWriter);
    }

    /**
     * {@link Reader}를 통해 얻는 데이터를 {@link OutputStream}으로 전달합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 9. 26.     parkjunhong77@gmail.com         최초 작성
     * 2026. 3. 11.     parkjunhong77@gmail.com     charset 정보 검증 추가.
     * </pre>
     *
     * @param reader
     * @param outStream
     * @param outCharset
     *            Charset of an {@link OutputStream}
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code reader} 또는 {@code outStream} 또는 {@code outCharset})가 {@code null}인 경우 발생.
     *
     * @since 2018. 9. 26.
     *
     * @see #transfer(Reader, boolean, Writer, boolean)
     */
    public static int transfer(Reader reader, OutputStream outStream, Charset outCharset) throws IOException {
        ObjectUtils.requireNonNulls(reader, outStream, outCharset);

        return transfer(reader, true, new OutputStreamWriter(outStream, requireCharset(outCharset)), true);
    }

    /**
     * {@link Reader}를 통해 얻은 데이터를 {@link OutputStream}으로 전달합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 9. 26.     parkjunhong77@gmail.com         최초 작성
     * 2026. 3. 11.     parkjunhong77@gmail.com     charset 정보 검증 추가.
     * </pre>
     *
     * @param reader
     * @param outStream
     * @param outCharset
     *            Charset of an {@link OutputStream}
     * @param close
     *            {@link Reader#close()}, {@link OutputStream#close()} 호출 여부
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code reader} 또는 {@code outStream} 또는 {@code outCharset} 또는 {@code close})가 {@code null}인 경우
     *             발생.
     *
     * @since 2018. 9. 26.
     *
     * @see #transfer(Reader, boolean, Writer, boolean)
     */
    public static int transfer(Reader reader, OutputStream outStream, Charset outCharset, boolean close) throws IOException {
        ObjectUtils.requireNonNulls(reader, outStream, outCharset);

        return transfer(reader, close, new OutputStreamWriter(outStream, requireCharset(outCharset)), close);
    }

    /**
     * {@link Reader}를 통해 얻은 데이터를 {@link OutputStream}으로 전달합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 1. 14.     parkjunhong77@gmail.com         최초 작성
     * 2026. 3. 10.     parkjunhong77@gmail.com     (3.0.0) JDK 마이그레이션 결과 "readBufferSize" 속성은 사용하지 않음.
     * 2026. 3. 11.     parkjunhong77@gmail.com     charset 정보 검증 추가.
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
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code reader} 또는 {@code outStream} 또는 {@code outCharset} 또는 {@code close})가 {@code null}인 경우
     *             발생.
     *
     * @since 2021. 1. 14.
     *
     * @see #transfer(Reader, boolean, Writer, boolean)
     */
    @Deprecated(since = "3.0.0", forRemoval = true)
    public static int transfer(Reader reader, OutputStream outStream, Charset outCharset, boolean close, int readBufferSize) throws IOException {
        ObjectUtils.requireNonNulls(reader, outStream, outCharset);

        return transfer(reader, close, new OutputStreamWriter(outStream, requireCharset(outCharset)), close);
    }

    /**
     * {@link Reader}를 통해 얻는 데이터를 {@link OutputStream}으로 전달합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 1. 14.     parkjunhong77@gmail.com         최초 작성
     * 2026. 3. 10.     parkjunhong77@gmail.com     (3.0.0) JDK 마이그레이션 결과 "readBufferSize" 속성은 사용하지 않음.
     * 2026. 3. 11.     parkjunhong77@gmail.com     charset 정보 검증 추가.
     * </pre>
     *
     * @param reader
     * @param outStream
     * @param outCharset
     *            Charset of an {@link OutputStream}
     * @param readBufferSize
     *            데이터 읽기 버퍼 크기
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code reader} 또는 {@code outStream} 또는 {@code outCharset})가 {@code null}인 경우 발생.
     *
     * @since 2021. 1. 14.
     *
     * @see #transfer(Reader, boolean, Writer, boolean)
     */
    @Deprecated(since = "3.0.0", forRemoval = true)
    public static int transfer(Reader reader, OutputStream outStream, Charset outCharset, int readBufferSize) throws IOException {
        ObjectUtils.requireNonNulls(reader, outStream, outCharset);

        return transfer(reader, true, new OutputStreamWriter(outStream, requireCharset(outCharset)), true);
    }

    /**
     * {@link Reader}를 통해 얻은 데이타를 {@link OutputStream}으로 전달합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 9. 26.     parkjunhong77@gmail.com         최초 작성
     * 2026. 3. 11.     parkjunhong77@gmail.com     charset 정보 검증 추가.
     * </pre>
     *
     * @param reader
     * @param outStream
     * @param outCharset
     *            Charset of an {@link OutputStream}
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code reader} 또는 {@code outStream} 또는 {@code outCharset})가 {@code null}인 경우 발생.
     *
     * @since 2018. 9. 26.
     *
     * @see #transfer(Reader, boolean, Writer, boolean)
     */
    public static int transfer(Reader reader, OutputStream outStream, String outCharset) throws IOException {
        ObjectUtils.requireNonNulls(reader, outStream, outCharset);

        return transfer(reader, true, new OutputStreamWriter(outStream, requireCharset(outCharset)), true);
    }

    /**
     * {@link Reader}를 통해 얻은 데이타를 {@link OutputStream}으로 전달합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 9. 26.     parkjunhong77@gmail.com         최초 작성
     * 2026. 3. 11.     parkjunhong77@gmail.com     charset 정보 검증 추가.
     * </pre>
     *
     * @param reader
     * @param outStream
     * @param outCharset
     *            Charset of an {@link OutputStream}
     * @param close
     *            {@link Reader#close()}, {@link OutputStream#close()} 호출 여부
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code reader} 또는 {@code outStream} 또는 {@code outCharset} 또는 {@code close})가 {@code null}인 경우
     *             발생.
     *
     * @since 2018. 9. 26.
     *
     * @see #transfer(Reader, boolean, Writer, boolean)
     */
    public static int transfer(Reader reader, OutputStream outStream, String outCharset, boolean close) throws IOException {
        ObjectUtils.requireNonNulls(reader, outStream, outCharset);

        return transfer(reader, close, new OutputStreamWriter(outStream, requireCharset(outCharset)), close);
    }

    /**
     * {@link Reader}를 통해 얻은 데이타를 {@link OutputStream}으로 전달합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 1. 14.     parkjunhong77@gmail.com         최초 작성
     * 2026. 3. 10.     parkjunhong77@gmail.com     (3.0.0) JDK 마이그레이션 결과 "readBufferSize" 속성은 사용하지 않음.
     * 2026. 3. 11.     parkjunhong77@gmail.com     charset 정보 검증 추가.
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
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code reader} 또는 {@code outStream} 또는 {@code outCharset} 또는 {@code close})가 {@code null}인 경우
     *             발생.
     *
     * @since 2021. 1. 14.
     *
     * @see #transfer(Reader, boolean, Writer, boolean)
     */
    @Deprecated(since = "3.0.0", forRemoval = true)
    public static int transfer(Reader reader, OutputStream outStream, String outCharset, boolean close, int readBufferSize) throws IOException {
        ObjectUtils.requireNonNulls(reader, outStream, outCharset);

        return transfer(reader, close, new OutputStreamWriter(outStream, requireCharset(outCharset)), close);
    }

    /**
     * {@link Reader}를 통해 얻은 데이타를 {@link OutputStream}으로 전달합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 1. 14.     parkjunhong77@gmail.com         최초 작성
     * 2026. 3. 10.     parkjunhong77@gmail.com     (3.0.0) JDK 마이그레이션 결과 "readBufferSize" 속성은 사용하지 않음.
     * 2026. 3. 11.     parkjunhong77@gmail.com     charset 정보 검증 추가.
     * </pre>
     *
     * @param reader
     * @param outStream
     * @param outCharset
     *            Charset of an {@link OutputStream}
     * @param readBufferSize
     *            데이터 읽기 버퍼 크기
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code reader})가 {@code null}인 경우 발생.
     *
     * @since 2021. 1. 14.
     *
     * @see #transfer(Reader, boolean, Writer, boolean)
     */
    @Deprecated(since = "3.0.0", forRemoval = true)
    public static int transfer(Reader reader, OutputStream outStream, String outCharset, int readBufferSize) throws IOException {
        return transfer(reader, true, new OutputStreamWriter(outStream, requireCharset(outCharset)), true);
    }

    /**
     * {@link Reader}를 통해 얻는 데이터를 {@link Writer}로 전달합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 8. 7.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param reader
     * @param writer
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code reader} 또는 {@code writer})가 {@code null}인 경우 발생.
     *
     * @since 2019. 8. 7.
     */
    public static int transfer(Reader reader, Writer writer) throws IOException {
        return transfer(reader, true, writer, true);
    }

    /**
     * {@link Reader}를 통해 얻는 데이터를 {@link Writer}로 전달합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 1. 14.     parkjunhong77@gmail.com         최초 작성
     * 2026. 3. 10.     parkjunhong77@gmail.com     (3.0.0) JDK 마이그레이션 결과 "readBufferSize" 속성은 사용하지 않음.
     * </pre>
     *
     * @param reader
     * @param writer
     * @param readBufferSize
     *            데이터 읽기 버퍼 크기
     *
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code reader} 또는 {@code writer})가 {@code null}인 경우 발생.
     *
     * @since 2021. 1. 14.
     */
    @Deprecated(since = "3.0.0", forRemoval = true)
    public static int transfer(Reader reader, Writer writer, int readBufferSize) throws IOException {
        return transfer(reader, true, writer, true);
    }

    /**
     * {@link BufferedWriter}에 데이터를 바로 보낸다.
     *
     * @param writer
     * @param msg
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code writer})가 {@code null}인 경우 발생.
     */
    public static void write(BufferedWriter writer, String msg) throws IOException {
        Objects.requireNonNull(writer);

        writer.write(msg + LINE_SEPARATOR);
    }
}