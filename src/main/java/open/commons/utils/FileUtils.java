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

/**
* 
*/
package open.commons.utils;

import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.CopyOption;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiFunction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import open.commons.io.Consumers;

/**
 * @author Park Jun-Hong.(mail_to:parkjunhong77@gmail.com)
 * 
 */
public class FileUtils {

    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

    static final FileFilter READ_FILE = new FileFilter() {

        @Override
        public boolean accept(File pathname) {
            return pathname.isFile();
        }
    };

    static final FileFilter READ_DIR = new FileFilter() {

        @Override
        public boolean accept(File pathname) {
            return pathname.isDirectory();
        }
    };

    /**
     * Delete all files and directories in the specific directory.<br>
     * DO NOT delete the specifi directory. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 8. 8.		박준홍			최초 작성
     * </pre>
     *
     * @param dir
     * @return
     *
     * @since 2019. 8. 8.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static boolean clearDirectory(File dir) {
        if (dir == null || dir.isFile() || dir.list().length < 1) {
            return true;
        }

        File[] files = listFiles(dir);
        File[] dirs = listDirectories(dir);

        deleteFiles(files);
        deleteDirs(true, dirs);

        return true;
    }

    /**
     * Delete all files and directories in the specific directory.<br>
     * DO NOT delete the specifi directory. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 8. 8.		박준홍			최초 작성
     * </pre>
     *
     * @param dir
     * @return
     *
     * @since 2019. 8. 8.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static boolean clearDirectory(String dir) {
        return clearDirectory(new File(dir));
    }

    /**
     * 
     * @param inStream
     *            원본 파일
     * @param srcEncoding
     *            원본 파일 인코딩
     * @param outStream
     *            대상 파일
     * @param targetEncoding
     *            대상 파일 인코딩
     * @return
     * @throws IOException
     */
    public static boolean converEnconding(InputStream inStream, String srcEncoding, OutputStream outStream, String targetEncoding) throws IOException {

        InputStreamReader isr = null;
        OutputStreamWriter osw = null;

        try {
            isr = new InputStreamReader(inStream, srcEncoding);

            osw = new OutputStreamWriter(outStream, targetEncoding);

            char[] buf = new char[1024];
            int readLen = -1;

            while ((readLen = isr.read(buf)) != -1) {
                osw.write(buf, 0, readLen);
            }

            osw.flush();

        } finally {
            IOUtils.close(inStream, isr, outStream, osw);
        }

        return true;
    }

    public static void copyFile(File src, File target) throws IOException {
        IOUtils.transfer(new FileInputStream(src), new FileOutputStream(target));
    }

    public static void copyFile(String src, String target) throws IOException {
        IOUtils.transfer(new FileInputStream(src), new FileOutputStream(target));
    }

    /**
     * 파일이 존재하지 않는 경우 생성합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 11. 5.		박준홍			최초 작성
     * </pre>
     *
     * @param first
     * @param more
     * @return
     *
     * @since 2020. 11. 5.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @throws IOException
     * @see Paths#get(String, String...)
     */
    public static Path createFileIfNotExist(String first, String... more) throws IOException {
        Path file = Paths.get(first, more);
        if (!Files.exists(file)) {
            Files.createDirectories(file.getParent());
            Files.createFile(file);
        }

        return file;
    }

    /**
     * Delete a file or a directory denoted by a given abstract pathname and return whether or not a file or directory
     * is deleted.
     * 
     * @param file
     *            a file or a directory to deleted.
     * @return whether or not a file or directory is deleted.
     * 
     * @see #delete(File, boolean)
     */
    public static boolean delete(File file) {
        return delete(file, true);
    }

    /**
     * Delete a file or a directory denoted by a given abstract pathname and whether or not a file or directory is
     * deleted.
     * 
     * @param f
     *            a file or a directory to deleted
     * @param forced
     *            when a {@link File} is a directory, whether or not delete it if the file contains other files or
     *            directories.
     * @return whether or not a file or directory is deleted.
     * 
     * @exception IllegalArgumentException
     *                If {@link File} instance is <code>null</code>.
     * @since 2012. 03. 13.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static boolean delete(File f, boolean forced) {
        if (f != null) {
            if (!f.exists()) {
                return true;
            }

            if (f.isFile()) {
                return f.delete();
            } else {
                if (forced) {
                    deleteDir(f, true);

                    try {
                        return f.delete();
                    } catch (Exception e) {
                        logger.error(e.getMessage());
                        return false;
                    }
                } else {
                    if (f.listFiles().length < 1) {
                        return f.delete();
                    } else {
                        return false;
                    }
                }
            }
        } else {
            throw new IllegalArgumentException("A parameter(File f) must not be null.", new NullPointerException("f=null"));
        }
    }

    /**
     * Delete a directory denoted by a given abstract pathname
     * 
     * @param dir
     * @param forced
     */
    private static void deleteDir(File dir, boolean forced) {
        File[] readFiles = dir.listFiles();

        if (forced) {
            if (readFiles.length > 0) {

                File[] files = listFiles(dir);
                File[] dirs = listDirectories(dir);

                deleteFiles(files);
                deleteDirs(forced, dirs);
            }

            dir.delete();

        } else {
            if (readFiles.length < 1) {
                dir.delete();
            }
        }
    }

    private static void deleteDirs(boolean forced, File... dirs) {
        for (File dir : dirs) {
            deleteDir(dir, forced);
        }
    }

    private static void deleteFiles(File... files) {
        for (File f : files) {
            try {
                f.delete();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    /**
     * 디렉토리에 포함된 하위 파일 및 디렉토리를 삭제합니다.
     * 
     * @param dir
     *            삭제할 대상 디렉토리
     */
    public static void emptyDir(File dir) {
        if (dir == null || !dir.exists() || dir.isFile()) {
            return;
        }

        deleteFiles(listFiles(dir));
        deleteDirs(true, listDirectories(dir));
    }

    /**
     * 
     * @param file
     * @return file-extension or <code>null</code> if <code>file</code> is <code>null</code> or not a file.
     * 
     * @since 2012. 3. 7.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static String getFileExtension(File file) {
        if (file == null) {
            return null;
        }

        if (file.isFile()) {
            return getFileExtension(file.getName());
        } else {
            return null;
        }
    }

    /**
     * 
     * @param file
     * @return file-extension or <code>null</code> if <code>file</code> is <code>null</code>.
     * 
     * @since 2012. 3. 7.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static String getFileExtension(String file) {
        if (file == null) {
            return null;
        }

        String filename = getFileName(file);

        int index = filename.lastIndexOf('.');

        return index > -1 ? filename.substring(index + 1) : "";
    }

    /**
     * 
     * @param file
     * @return filename or <code>null</code> if <code>file</code> is <code>null</code>.
     * 
     * @since 2012. 3. 7.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static String getFileName(String file) {
        if (file == null) {
            return null;
        }

        file = StringUtils.rtrimSpecific(file, File.separatorChar);

        int backIndex = StringUtils.backIndexOf(file, File.separatorChar);
        return file.substring(backIndex + 1);
    }

    /**
     * 
     * @param file
     * @return filename or <code>null</code> if <code>file</code> is <code>null</code>.
     * 
     * @since 2012. 3. 7.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static String getFileNameNoExtension(File file) {
        if (file == null) {
            return null;
        }

        String filename = file.getName();

        int index = filename.lastIndexOf('.');

        return index > -1 ? filename.substring(0, index) : filename;
    }

    /**
     * 
     * @param file
     * @return filename or <code>null</code> if <code>file</code> is <code>null</code>.
     * 
     * @since 2012. 3. 7.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static String getFileNameNoExtension(String file) {
        String filename = getFileName(file);

        if (filename == null) {
            return null;
        }

        int index = filename.lastIndexOf('.');

        return index > -1 ? filename.substring(0, index) : filename;
    }

    /**
     * 
     * @param file
     * @return filepath or <code>null</code> if <code>file</code> is <code>null</code>.
     * 
     * @since 2012. 3. 7.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static String getFilePath(File file) {
        if (file == null) {
            return null;
        }

        String filepath = file.getAbsolutePath();
        if (file.isFile()) {
            int backIndex = StringUtils.backIndexOf(filepath, File.separatorChar);
            return filepath.substring(0, backIndex);
        } else {
            return filepath;
        }
    }

    /**
     * 
     * @param file
     * @return filepath or <code>null</code> if <code>file</code> is <code>null</code>.
     * 
     * @see File#getParent()
     * 
     * @since 2012. 3. 7.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static String getFilePath(String file) {
        if (file == null) {
            return null;
        }

        int backIndex = StringUtils.backIndexOf(file, File.separatorChar);
        return file.substring(0, backIndex);
    }

    /**
     * Return an array of abstract pathnames denoting the directories in the directory denoted by a given {@link File}
     * (abstract pathaname).
     * 
     * @param dir
     * 
     * @return an array of abstract pathnames.
     */
    public static File[] listDirectories(File dir) {
        if (dir == null || dir.isFile()) {
            return new File[0];
        }

        return dir.listFiles(READ_DIR);
    }

    /**
     * Return an array of abstract pathnames denoting the files in the directory denoted by a given {@link File} (a
     * abstract pathname).
     * 
     * @param dir
     * @return an array of abstract pathnames.
     */
    public static File[] listFiles(File dir) {
        if (dir == null || dir.isFile()) {
            return new File[0];
        }

        return dir.listFiles(READ_FILE);
    }

    /**
     * 주어진 경로에서 조건에 맞는 파일 정보를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 2. 8.		박준홍			최초 작성
     * </pre>
     *
     * @param directory
     *            검색할 최상위 디렉토리
     * @param maxDepth
     *            내부 디렉토리 검색 레벨.
     * @param filter
     *            파일 또는 디렉토리 필터
     * @return
     * @throws IOException
     *
     * @since 2021. 2. 8.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static Set<Path> listFiles(Path directory, int maxDepth, BiFunction<Path, BasicFileAttributes, Boolean> filter) throws IOException {
        if (!Files.exists(directory)) {
            throw ExceptionUtils.newException(IllegalArgumentException.class, "존재하지 않는 경로입니다. directory=%s", directory.toString());
        }

        final Set<Path> files = new HashSet<>();
        Files.walkFileTree(directory, EnumSet.noneOf(FileVisitOption.class), maxDepth, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (filter.apply(file, attrs)) {
                    files.add(file);
                }

                return FileVisitResult.CONTINUE;
            }
        });

        return files;
    }

    public static void main(String[] args) {
        // File src = new File("D:\\03.WORKSPACE\\eclipse-SDK-4.2-win32-x86_64\\open.commons\\src\\open\\commons");
        // for (File file : src.listFiles()) {
        // System.out.printf("%9s: %s\n", file.isFile() ? "File" : "Directory", file.getAbsolutePath());
        // }
        // System.out.println("-------------------------------------------------------------------------------");
        // for (File file : listFiles(src)) {
        // System.out.printf("%9s: %s\n", file.isFile() ? "File" : "Directory", file.getAbsolutePath());
        // }
        // System.out.println("-------------------------------------------------------------------------------");
        // for (File file : listDirectories(src)) {
        // System.out.printf("%9s: %s\n", file.isFile() ? "File" : "Directory", file.getAbsolutePath());
        // }
        //
        // File dir = new File("D:\\03.WORKSPACE\\eclipse-SDK-4.2-win32-x86_64\\open.commons\\src - 복사본 (2)");
        // emptyDir(dir);
        //
        // String filepath = "D:\\03.WORKSPACE\\eclipse-SDK-4.2-win32-x86_64\\open.commons\\.classpath";
        // System.out.println("path: " + getFilePath(filepath));
        // System.out.println("name: " + getFileName(filepath));
        // System.out.println("ext : " + getFileExtension(filepath));

        System.out.println("abc.de".lastIndexOf('.'));
        System.out.println(StringUtils.backIndexOf("abc.de", '.'));
    }

    /**
     * 파일을 이동시키거나 이름을 변경합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 2. 10.     박준홍         최초 작성
     * </pre>
     *
     * @param source
     *            복사할 파일 경로
     * @param target
     *            복사될 위치 경로
     * @param options
     *            복사 또는 이동 설정
     * 
     * @return
     *
     * @since 2021. 2. 10.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @throws IOException
     * 
     * @see {@link Files#move(Path, Path, CopyOption...)}
     */
    public static Path move(File source, File target, CopyOption... options) throws IOException {
        return Files.move(source.toPath(), target.toPath(), options);
    }

    /**
     * 파일을 이동시키거나 이름을 변경합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 2. 18.		박준홍			최초 작성
     * </pre>
     *
     * @param source
     *            복사할 파일 경로
     * @param target
     *            복사될 위치 경로
     * @param options
     *            복사 또는 이동 설정
     * @return
     * @throws IOException
     *
     * @since 2021. 2. 18.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static Path move(Path source, Path target, CopyOption... options) throws IOException {
        return Files.move(source, target, options);
    }

    /**
     * 파일을 이동시키거나 이름을 변경합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 2. 10.     박준홍         최초 작성
     * </pre>
     *
     * @param source
     *            복사할 파일 경로
     * @param target
     *            복사될 위치 경로
     * @param options
     *            복사 또는 이동 설정
     * @return
     *
     * @since 2021. 2. 10.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @throws IOException
     * 
     * @see {@link Files#move(Path, Path, CopyOption...)}
     */
    public static Path move(Path source, String target, CopyOption... options) throws IOException {
        return Files.move(source, Paths.get(target), options);
    }

    /**
     * 파일을 이동시키거나 이름을 변경합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 2. 10.     박준홍         최초 작성
     * </pre>
     *
     * @param source
     *            복사할 파일 경로
     * @param target
     *            복사될 위치 경로
     * @param options
     *            복사 또는 이동 설정
     * @return
     *
     * @since 2021. 2. 10.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @throws IOException
     * 
     * @see {@link Files#move(Path, Path, CopyOption...)}
     */
    public static Path move(String source, Path target, CopyOption... options) throws IOException {
        return Files.move(Paths.get(source), target, options);
    }

    /**
     * 파일을 이동시키거나 이름을 변경합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 2. 10.		박준홍			최초 작성
     * </pre>
     *
     * @param source
     *            복사할 파일 경로
     * @param target
     *            복사될 위치 경로
     * @param options
     *            복사 또는 이동 설정
     * @return
     *
     * @since 2021. 2. 10.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @throws IOException
     * 
     * @see {@link Files#move(Path, Path, CopyOption...)}
     */
    public static Path move(String source, String target, CopyOption... options) throws IOException {
        return Files.move(Paths.get(source), Paths.get(target), options);
    }

    /**
     * Read up to length of bytes from in until EOF is detected.
     * 
     * @param inStream
     *            input stream, must not be null
     * @param length
     *            number of bytes to read, -1 or Integer.MAX_VALUE means read as much as possible
     * @param readAll
     *            if true, an EOFException will be thrown if not enough bytes are read. Ignored when length is -1 or
     *            Integer.MAX_VALUE
     * @return
     * @throws IOException
     *             Any IO error or a premature EOF is detected
     */
    public static byte[] readFully(InputStream inStream, int length, boolean readAll) throws IOException {
        byte[] output = {};
        if (length == -1) {
            length = Integer.MAX_VALUE;
        }

        int pos = 0;

        while (pos < length) {
            int bytesToRead;
            if (pos >= output.length) { // Only expand when there's no room
                bytesToRead = Math.min(length - pos, output.length + 1024);
                if (output.length < pos + bytesToRead) {
                    output = Arrays.copyOf(output, pos + bytesToRead);
                }
            } else {
                bytesToRead = output.length - pos;
            }

            int cc = inStream.read(output, pos, bytesToRead);

            if (cc < 0) {
                if (readAll && length != Integer.MAX_VALUE) {
                    throw new EOFException("Detect premature EOF");
                } else {
                    if (output.length != pos) {
                        output = Arrays.copyOf(output, pos);
                    }
                    break;
                }
            }
            pos += cc;
        }

        return output;
    }

    /**
     * {@link File}을 <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 10. 26.        박준홍         최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2018. 10. 26.
     * @see Closeable
     * @see AutoCloseable
     */
    public static Consumers<File> removableFiles() {
        return new Consumers<>(f -> {
            if (f != null) {
                FileUtils.delete(f, true);

                if (logger.isInfoEnabled()) {
                    logger.info("[ deleted ] {}", f.getAbsolutePath());
                }
            }
        });
    }

    /**
     * 
     * @param pathname
     *            저장할 파일.
     * @param data
     * @return
     */
    public static boolean save(File file, String data) {
        boolean saved = true;

        InputStream inStream = null;
        OutputStream outStream = null;

        try {
            inStream = new ByteArrayInputStream(data.getBytes());
            outStream = new FileOutputStream(file);

            IOUtils.transfer(inStream, outStream);
        } catch (IOException e) {
            saved = false;
        } finally {
            IOUtils.close(inStream, outStream);
        }

        return saved;
    }

    /**
     * 
     * @param pathname
     *            저장할 파일.
     * @param data
     * @return
     */
    public static boolean save(File file, String data, String charset) {
        boolean saved = true;

        InputStream inStream = null;
        OutputStream outStream = null;

        try {
            inStream = new ByteArrayInputStream(data.getBytes());
            outStream = new FileOutputStream(file);

            IOUtils.transfer(inStream, outStream, charset);
        } catch (IOException e) {
            saved = false;
        } finally {
            IOUtils.close(inStream, outStream);
        }

        return saved;
    }

    /**
     * 
     * @param pathname
     *            저장할 파일.
     * @param data
     * @return
     */
    public static boolean save(String pathname, String data) {
        return save(new File(pathname), data);
    }

    /**
     * 
     * @param pathname
     *            저장할 파일.
     * @param data
     * @return
     */
    public static boolean save(String pathname, String data, String charset) {
        return save(new File(pathname), data, charset);
    }

    public static String toFilepath(String... strings) {
        if (strings.length > 0) {
            StringBuffer sb = new StringBuffer();
            int i = 0;
            for (; i < strings.length - 1; i++) {
                sb.append(strings[i] + File.separator);
            }
            return sb.append(strings[i]).toString();
        } else {
            return "";
        }
    }
}
