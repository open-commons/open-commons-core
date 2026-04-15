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
*/
package open.commons.core.utils;

import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.CopyOption;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import open.commons.core.io.Consumers;

/**
 * 
 * @since 2019. 8. 8.
 */
public class FileUtils {

    @SuppressWarnings("null")
    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

    static final FileFilter READ_FILE = new FileFilter() {

        @Override
        public boolean accept(@Nullable File pathname) {
            if (pathname == null) {
                return false;
            }
            return pathname.isFile();
        }
    };

    static final FileFilter READ_DIR = new FileFilter() {

        @Override
        public boolean accept(@Nullable File pathname) {
            if (pathname == null) {
                return false;
            }
            return pathname.isDirectory();
        }
    };

    /**
     * 지정된 디렉토리 내의 모든 파일과 하위 디렉토리를 삭제합니다.<br>
     * 지정된 디렉토리 자체는 삭제하지 않습니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 8. 8.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param dir
     *            대상 디렉토리
     *
     * @return 성공 여부
     *
     * @since 2019. 8. 8.
     */
    public static boolean clearDirectory(@Nullable File dir) {
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
     * 지정된 디렉토리 내의 모든 파일과 하위 디렉토리를 삭제합니다.<br>
     * 지정된 디렉토리 자체는 삭제하지 않습니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 8. 8.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param dir
     *            대상 디렉토리 경로
     *
     * @return 성공 여부
     *
     * @since 2019. 8. 8.
     */
    public static boolean clearDirectory(String dir) {
        Objects.requireNonNull(dir);

        return clearDirectory(new File(dir));
    }

    /**
     * 파일을 복사합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 8. 8.          parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param src
     *            원본 파일
     * @param target
     *            대상 파일
     *
     * @throws IOException
     *             I/O 오류가 발생한 경우.
     *
     * @since 2019. 8. 8.
     */
    public static void copyFile(File src, File target) throws IOException {
        AssertUtils2.notNulls(src, target);

        Files.copy(src.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

    /**
     * 파일을 지정된 대상으로 복사합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 8. 8.          parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param src
     *            원본 파일 경로
     * @param target
     *            대상 파일 경로
     *
     * @throws IOException
     *             I/O 오류가 발생한 경우.
     *
     * @since 2019. 8. 8.
     */
    public static void copyFile(String src, String target) throws IOException {
        AssertUtils2.notNulls(src, target);

        Files.copy(Paths.get(src), Paths.get(target), StandardCopyOption.REPLACE_EXISTING);
    }

    /**
     * 파일이 존재하지 않는 경우 생성합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 11. 5.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param first
     *            경로 첫 부분
     * @param more
     *            경로 나머지 부분
     *
     * @return 생성된 파일 경로 객체
     *
     * @throws IOException
     *             I/O 오류가 발생한 경우.
     *
     * @since 2020. 11. 5.
     * 
     * @see Paths#get(String, String...)
     */
    // 아래 내용에 적용됨.
    // - Paths.get(first, more);
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static Path createFileIfNotExist(String first, String... more) throws IOException {
        Objects.requireNonNull(first);

        Path file = Paths.get(first, more);
        if (!Files.exists(file)) {
            Files.createDirectories(file.getParent());
            Files.createFile(file);
        }

        return file;
    }

    /**
     * 주어진 추상 경로명으로 표시된 파일이나 디렉토리를 삭제하고 삭제 성공 여부를 반환합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 3. 13.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param file
     *            삭제할 파일 또는 디렉토리
     *
     * @return 파일이나 디렉토리의 삭제 성공 여부
     *
     * @since 2019. 8. 8.
     * 
     * @see #delete(File, boolean)
     */
    public static boolean delete(File file) {
        return delete(file, true);
    }

    /**
     * 주어진 추상 경로명으로 표시된 파일이나 디렉토리를 삭제하고 삭제 성공 여부를 반환합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 3. 13.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param file
     *            삭제할 파일 또는 디렉토리
     * @param forced
     *            {@link File} 인스턴스가 디렉토리인 경우, 다른 파일이나 디렉토리가 포함되어 있어도 강제로 삭제할지 여부
     *
     * @return 파일이나 디렉토리의 삭제 성공 여부
     *
     * @since 2012. 03. 13.
     */
    public static boolean delete(File file, boolean forced) {
        Objects.requireNonNull(file);

        if (!file.exists()) {
            return true;
        }

        if (file.isFile()) {
            return file.delete();
        } else {
            if (forced) {
                deleteDir(file, true);

                try {
                    return file.delete();
                } catch (Exception e) {
                    logger.error(e.getMessage());
                    return false;
                }
            } else {
                if (file.listFiles().length < 1) {
                    return file.delete();
                } else {
                    return false;
                }
            }
        }
    }

    /**
     * 주어진 추상 경로명으로 표시된 디렉토리를 삭제합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 3. 13.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param dir
     *            삭제할 디렉토리
     * @param forced
     *            디렉토리 내 파일 존재 여부와 무관하게 강제로 삭제할지 여부
     *
     * @since 2019. 8. 8.
     */
    private static void deleteDir(File dir, boolean forced) {
        Objects.requireNonNull(dir);

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

    /**
     * 여러 디렉토리를 삭제합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 3. 13.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param forced
     *            강제 삭제 여부
     * @param dirs
     *            삭제할 디렉토리 배열
     *
     * @since 2019. 8. 8.
     */
    // 아래 내용에 적용됨.
    // - AssertUtils2.notNulls((Object[]) dirs);
    // [PATCH] 배열 공변성/가변성에 대한 IDE 분석기의 오탐 우회
    // [TODO] 향후 IDE의 배열 데이터 흐름 분석이 고도화되거나 JSpecify가 완벽히 지원되면 '제거'
    @SuppressWarnings("null")
    private static void deleteDirs(boolean forced, File... dirs) {
        AssertUtils2.notNulls((Object[]) dirs);

        for (File dir : dirs) {
            deleteDir(dir, forced);
        }
    }

    /**
     * 여러 파일을 삭제합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 3. 13.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param files
     *            삭제할 파일 배열
     *
     * @since 2019. 8. 8.
     */
    // 아래 내용에 적용됨.
    // - AssertUtils2.notNulls((Object[]) files);
    // [PATCH] 배열 공변성/가변성에 대한 IDE 분석기의 오탐 우회
    // [TODO] 향후 IDE의 배열 데이터 흐름 분석이 고도화되거나 JSpecify가 완벽히 지원되면 '제거'
    @SuppressWarnings("null")
    private static void deleteFiles(File... files) {
        AssertUtils2.notNulls((Object[]) files);

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
     * <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 3. 13.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param dir
     *            삭제할 대상 디렉토리
     *
     * @since 2019. 8. 8.
     */
    public static void emptyDir(File dir) {
        Objects.requireNonNull(dir);

        if (!dir.exists() || dir.isFile()) {
            return;
        }

        deleteFiles(listFiles(dir));
        deleteDirs(true, listDirectories(dir));
    }

    /**
     * 파일의 확장자를 반환합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 3. 13.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param file
     *            확장자를 추출할 파일 객체
     *
     * @return 파일 확장자. 대상이 파일이 아닌 경우 {@code ""} 반환.
     *
     * @since 2012. 3. 7.
     */
    // 아래 내용에 적용됨.
    // - file.getName()
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static String getFileExtension(File file) {
        Objects.requireNonNull(file);

        if (file.isFile()) {
            return getFileExtension(file.getName());
        } else {
            return "";
        }
    }

    /**
     * 파일명 문자열에서 확장자를 반환합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 3. 13.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param file
     *            확장자를 추출할 파일명 문자열
     *
     * @return 파일 확장자. 확장자가 없는 경우 {@code ""}를 반환.
     *
     * @since 2012. 3. 7.
     */
    // 아래 내용에 적용됨.
    // - filename.substring(index + 1)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static String getFileExtension(String file) {
        Objects.requireNonNull(file);

        String filename = getFileName(file);

        int index = filename.lastIndexOf('.');

        return index > -1 ? filename.substring(index + 1) : "";
    }

    /**
     * 경로 문자열에서 파일명 부분만 반환합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 3. 13.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param file
     *            파일명 또는 경로 문자열
     *
     * @return 추출된 파일명
     *
     * @since 2012. 3. 7.
     */
    // 아래 내용에 적용됨.
    // - file.substring(backIndex + 1);
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static String getFileName(String file) {
        Objects.requireNonNull(file);

        file = StringUtils.rtrimSpecific(file, File.separatorChar);

        int backIndex = StringUtils.backIndexOf(file, File.separatorChar);
        return file.substring(backIndex + 1);
    }

    /**
     * 파일 객체에서 확장자를 제외한 파일명 부분만 반환합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 3. 13.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param file
     *            대상 파일 객체
     *
     * @return 확장자가 제외된 파일명
     *
     * @since 2012. 3. 7.
     */
    // 아래 내용에 적용됨.
    // - filename.substring(0, index)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static String getFileNameNoExtension(File file) {
        Objects.requireNonNull(file);

        String filename = file.getName();

        int index = filename.lastIndexOf('.');

        return index > -1 ? filename.substring(0, index) : filename;
    }

    /**
     * 경로 문자열에서 확장자를 제외한 파일명 부분만 반환합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 3. 13.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param file
     *            파일명 또는 경로 문자열
     *
     * @return 확장자가 제외된 파일명
     *
     * @since 2012. 3. 7.
     */
    // 아래 내용에 적용됨.
    // - filename.substring(0, index)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static String getFileNameNoExtension(String file) {
        String filename = getFileName(file);

        int index = filename.lastIndexOf('.');

        return index > -1 ? filename.substring(0, index) : filename;
    }

    /**
     * 시스템별 파일 경로를 반환합니다. 해당 객체가 파일인 경우, 부모 디렉토리의 절대 경로를 반환합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 3. 13.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param file
     *            대상 파일 객체
     *
     * @return 파일 경로 문자열
     *
     * @since 2012. 3. 7.
     */
    // 아래 내용에 적용됨.
    // - file.getAbsolutePath();
    // - filename.substring(0, backIndex)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static String getFilePath(File file) {
        Objects.requireNonNull(file);

        String filepath = file.getAbsolutePath();
        if (file.isFile()) {
            int backIndex = StringUtils.backIndexOf(filepath, File.separatorChar);
            return filepath.substring(0, backIndex);
        } else {
            return filepath;
        }
    }

    /**
     * 주어진 파일 경로 문자열의 디렉토리 경로 부분을 반환합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 3. 13.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param file
     *            파일명 또는 경로 문자열
     *
     * @return 디렉토리 경로 문자열
     *
     * @since 2012. 3. 7.
     * 
     * @see File#getParent()
     */
    // 아래 내용에 적용됨.
    // - file.substring(0, backIndex)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static String getFilePath(String file) {
        Objects.requireNonNull(file);

        int backIndex = StringUtils.backIndexOf(file, File.separatorChar);
        return file.substring(0, backIndex);
    }

    /**
     * 지정된 경로 내의 조건에 맞는 파일 중에 가장 최근에 수정된 파일을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2024. 8. 14.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param absoluteDirPath
     *            디렉토리 경로
     * @param fileFilters
     *            파일 필터 조건
     *
     * @return <b>{@code nullable}</b>
     *
     * @since 2024. 8. 14.
     * @version 2.0.0
     * 
     * @see File#lastModified()
     */
    // 아래 내용에 적용됨.
    // - AssertUtils2.notNulls((Object[]) fileFilters);
    // [PATCH] 배열 공변성/가변성에 대한 IDE 분석기의 오탐 우회
    // [TODO] 향후 IDE의 배열 데이터 흐름 분석이 고도화되거나 JSpecify가 완벽히 지원되면 '제거'
    @SuppressWarnings("null")
    @SafeVarargs
    public static Path getLatestFilepath(String absoluteDirPath, Predicate<Path>... fileFilters) {
        Objects.requireNonNull(absoluteDirPath);
        AssertUtils2.notNulls((Object[]) fileFilters);

        Path dir = Paths.get(absoluteDirPath);
        try {
            Optional<Path> latestFilepath = Files.list(dir) //
                    .filter(path -> {
                        for (Predicate<Path> filter : fileFilters) {
                            if (!filter.test(path)) {
                                return false;
                            }
                        }
                        return true;
                    }) //
                    .max(Comparator.comparingLong(f -> f.toFile().lastModified()));

            return latestFilepath.isPresent() ? latestFilepath.get() : null;
        } catch (IOException e) {
            logger.warn("전달받은 디렉토리({})에 파일이 존재하지 않습니다.", absoluteDirPath);
            return null;
        }
    }

    /**
     * 지정된 경로 내의 조건에 맞는 파일 중에 가장 오래 전에 수정된 파일을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2024. 8. 14.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param absoluteDirPath
     *            디렉토리 경로
     * @param fileFilters
     *            파일 필터 조건
     *
     * @return <b>{@code nullable}</b>
     *
     * @since 2024. 8. 14.
     * @version 2.0.0
     * 
     * @see File#lastModified()
     */
    // 아래 내용에 적용됨.
    // - AssertUtils2.notNulls((Object[]) fileFilters);
    // [PATCH] 배열 공변성/가변성에 대한 IDE 분석기의 오탐 우회
    // [TODO] 향후 IDE의 배열 데이터 흐름 분석이 고도화되거나 JSpecify가 완벽히 지원되면 '제거'
    @SuppressWarnings("null")
    @SafeVarargs
    public static Path getOldestFilepath(String absoluteDirPath, Predicate<Path>... fileFilters) {
        Objects.requireNonNull(absoluteDirPath);
        AssertUtils2.notNulls((Object[]) fileFilters);

        Path dir = Paths.get(absoluteDirPath);
        try {
            Optional<Path> latestFilepath = Files.list(dir) //
                    .filter(path -> {
                        for (Predicate<Path> filter : fileFilters) {
                            if (!filter.test(path)) {
                                return false;
                            }
                        }
                        return true;
                    }) //
                    .min(Comparator.comparingLong(f -> f.toFile().lastModified()));

            return latestFilepath.isPresent() ? latestFilepath.get() : null;
        } catch (IOException e) {
            logger.warn("전달받은 디렉토리({})에 파일이 존재하지 않습니다.", absoluteDirPath);
            return null;
        }
    }

    /**
     * 주어진 디렉토리 경로에 포함된 하위 디렉토리 목록을 반환합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 3. 13.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param dir
     *            탐색할 디렉토리
     *
     * @return 하위 디렉토리 배열
     *
     * @since 2019. 8. 8.
     */
    // 아래 내용에 적용됨.
    // - dir.listFiles(READ_DIR)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static File[] listDirectories(File dir) {
        Objects.requireNonNull(dir);

        if (dir.isFile()) {
            return new File[0];
        }

        return dir.listFiles(READ_DIR);
    }

    /**
     * 주어진 디렉토리 경로에 포함된 하위 파일 목록을 반환합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 3. 13.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param dir
     *            탐색할 디렉토리
     *
     * @return 하위 파일 배열
     *
     * @since 2019. 8. 8.
     */
    // 아래 내용에 적용됨.
    // - dir.listFiles(READ_FILE)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static File[] listFiles(File dir) {
        Objects.requireNonNull(dir);

        if (dir.isFile()) {
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
     * 2021. 2. 8.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param directory
     *            검색할 최상위 디렉토리
     * @param maxDepth
     *            내부 디렉토리 검색 레벨.
     * @param filter
     *            파일 또는 디렉토리 필터
     *
     * @return 조건에 맞는 파일 집합
     *
     * @throws IOException
     *             I/O 예외 발생 시
     *
     * @since 2021. 2. 8.
     * @version 1.8.0
     */
    public static Set<Path> listFiles(Path directory, int maxDepth, BiFunction<Path, BasicFileAttributes, Boolean> filter) throws IOException {
        AssertUtils2.notNulls(directory, filter);

        if (!Files.exists(directory)) {
            throw ExceptionUtils.newException(IllegalArgumentException.class, "존재하지 않는 경로입니다. directory=%s", directory.toString());
        }

        final Set<Path> files = new HashSet<>();
        Files.walkFileTree(directory, EnumSet.noneOf(FileVisitOption.class), maxDepth, new SimpleFileVisitor<Path>() {
            // 아래 내용에 적용됨.
            // - public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
            // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
            @SuppressWarnings("null")
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

    /**
     * 주어진 디렉토리의 하위 파일/디렉토리 목록을 제공합니다. <br>
     * {@link Files#list(Path)} 의 경우 {@link Stream}에 포함된 {@link Path} 객체에 대해 OS에서 IO 객체를 유지합니다.<br>
     * 이에 대한 IO 를 제거하기 위해서 {@link Stream#close()}을 호출합니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2023. 11. 15.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <T>
     *            반환 타입 파라미터
     * @param directory
     *            조회할 디렉토리
     * @param filter
     *            파일 필터 조건
     * @param collector
     *            결과 데이터 생성 함수
     *
     * @return 필터링 및 변환이 완료된 결과 객체
     *
     * @throws IOException
     *             I/O 예외 발생 시
     *
     * @since 2023. 11. 15.
     * @version 2.0.0
     */
    public static <T> T listFiles(Path directory, Predicate<Path> filter, Function<Stream<Path>, T> collector) throws IOException {
        AssertUtils2.notNulls(directory, filter, collector);

        try (Stream<Path> stream = Files.list(directory).filter(filter)) {
            return collector.apply(stream);
        }
    }

    /**
     * 주어진 디렉토리의 하위 파일/디렉토리 목록을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2023. 11. 15.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param directory
     *            조회할 디렉토리
     *
     * @return 하위 파일 정보를 나타내는 배열
     *
     * @throws IOException
     *             I/O 예외 발생 시
     *
     * @since 2023. 11. 15.
     * @version 2.0.0
     * 
     * @see #listFiles(Path, Predicate, Function)
     */
    public static Path[] listFilesAsArray(Path directory) throws IOException {
        return listFilesAsArray(directory, _ -> true);
    }

    /**
     * 주어진 디렉토리의 하위 파일/디렉토리 목록을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2023. 11. 15.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param directory
     *            조회할 디렉토리
     * @param filter
     *            파일 필터 조건
     *
     * @return 필터링된 파일 정보를 담는 배열
     *
     * @throws IOException
     *             I/O 예외 발생 시
     *
     * @since 2023. 11. 15.
     * @version 2.0.0
     * 
     * @see #listFiles(Path, Predicate, Function)
     */
    // 아래 내용에 적용됨.
    // - stream.toArray(Path[]::new)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static Path[] listFilesAsArray(Path directory, Predicate<Path> filter) throws IOException {
        return listFiles(directory, filter, stream -> stream.toArray(Path[]::new));
    }

    /**
     * 주어진 디렉토리의 하위 파일/디렉토리 목록을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2023. 11. 15.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param directory
     *            조회할 디렉토리
     *
     * @return 하위 파일 정보를 나타내는 리스트
     *
     * @throws IOException
     *             I/O 예외 발생 시
     *
     * @since 2023. 11. 15.
     * @version 2.0.0
     * 
     * @see #listFiles(Path, Predicate, Function)
     */
    public static List<Path> listFilesAsList(Path directory) throws IOException {
        return listFilesAsList(directory, _ -> true);
    }

    /**
     * 주어진 디렉토리의 하위 파일/디렉토리 목록을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2023. 11. 15.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param directory
     *            조회할 디렉토리
     * @param filter
     *            파일 필터 조건
     *
     * @return 필터링된 파일 정보를 담는 리스트
     *
     * @throws IOException
     *             I/O 예외 발생 시
     *
     * @since 2023. 11. 15.
     * @version 2.0.0
     * 
     * @see #listFiles(Path, Predicate, Function)
     */
    // 아래 내용에 적용됨.
    // - Collectors.toList()
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static List<Path> listFilesAsList(Path directory, Predicate<Path> filter) throws IOException {
        return listFiles(directory, filter, stream -> stream.collect(Collectors.toList()));
    }

    /**
     * 주어진 디렉토리의 하위 파일/디렉토리 목록을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2023. 11. 15.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param directory
     *            조회할 디렉토리
     *
     * @return 하위 파일 정보를 나타내는 집합(Set)
     *
     * @throws IOException
     *             I/O 예외 발생 시
     *
     * @since 2023. 11. 15.
     * @version 2.0.0
     * 
     * @see #listFiles(Path, Predicate, Function)
     */
    public static Set<Path> listFilesAsSet(Path directory) throws IOException {
        return listFilesAsSet(directory, _ -> true);
    }

    /**
     * 주어진 디렉토리의 하위 파일/디렉토리 목록을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2023. 11. 15.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param directory
     *            조회할 디렉토리
     * @param filter
     *            파일 필터 조건
     *
     * @return 필터링된 파일 정보를 담는 집합(Set)
     *
     * @throws IOException
     *             I/O 예외 발생 시
     *
     * @since 2023. 11. 15.
     * 
     * @version 2.0.0
     * 
     * @see #listFiles(Path, Predicate, Function)
     */
    // 아래 내용에 적용됨.
    // - Collectors.toSet()
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static Set<Path> listFilesAsSet(Path directory, Predicate<Path> filter) throws IOException {
        return listFiles(directory, filter, stream -> stream.collect(Collectors.toSet()));
    }

    /**
     * 파일을 이동시키거나 이름을 변경합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 2. 10.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param source
     *            복사할 파일 경로
     * @param target
     *            복사될 위치 경로
     * @param options
     *            복사 또는 이동 설정
     * 
     * @return 변경된 파일 경로 객체
     *
     * @throws IOException
     *             I/O 오류가 발생한 경우.
     *
     * @since 2021. 2. 10.
     * @version 1.8.0
     * 
     * @see Files#move(Path, Path, CopyOption...)
     */
    // 아래 내용에 적용됨.
    // - Files.move(source.toPath(), target.toPath(), options)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static Path move(File source, File target, CopyOption... options) throws IOException {
        AssertUtils2.notNulls(source, target, options);

        return Files.move(source.toPath(), target.toPath(), options);
    }

    /**
     * 파일을 이동시키거나 이름을 변경합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 2. 18.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param source
     *            복사할 파일 경로
     * @param target
     *            복사될 위치 경로
     * @param options
     *            복사 또는 이동 설정
     *
     * @return 변경된 파일 경로 객체
     *
     * @throws IOException
     *             I/O 오류가 발생한 경우.
     *
     * @since 2021. 2. 18.
     * @version 1.8.0
     * 
     * @see Files#move(Path, Path, CopyOption...)
     */
    // 아래 내용에 적용됨.
    // - Files.move(source, target, options)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static Path move(Path source, Path target, CopyOption... options) throws IOException {
        AssertUtils2.notNulls(source, target, options);

        return Files.move(source, target, options);
    }

    /**
     * 파일을 이동시키거나 이름을 변경합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 2. 10.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param source
     *            복사할 파일 경로
     * @param target
     *            복사될 위치 경로
     * @param options
     *            복사 또는 이동 설정
     *
     * @return 변경된 파일 경로 객체
     *
     * @throws IOException
     *             I/O 오류가 발생한 경우.
     *
     * @since 2021. 2. 10.
     * @version 1.8.0
     * 
     * @see Files#move(Path, Path, CopyOption...)
     */
    // 아래 내용에 적용됨.
    // - Files.move(source, Paths.get(target), options)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static Path move(Path source, String target, CopyOption... options) throws IOException {
        AssertUtils2.notNulls(source, target, options);

        return Files.move(source, Paths.get(target), options);
    }

    /**
     * 파일을 이동시키거나 이름을 변경합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 2. 10.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param source
     *            복사할 파일 경로
     * @param target
     *            복사될 위치 경로
     * @param options
     *            복사 또는 이동 설정
     *
     * @return 변경된 파일 경로 객체
     *
     * @throws IOException
     *             I/O 오류가 발생한 경우.
     *
     * @since 2021. 2. 10.
     * @version 1.8.0
     * 
     * @see Files#move(Path, Path, CopyOption...)
     */
    // 아래 내용에 적용됨.
    // - Files.move(Paths.get(source), target, options)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static Path move(String source, Path target, CopyOption... options) throws IOException {
        AssertUtils2.notNulls(source, target, options);

        return Files.move(Paths.get(source), target, options);
    }

    /**
     * 파일을 이동시키거나 이름을 변경합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 2. 10.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param source
     *            복사할 파일 경로
     * @param target
     *            복사될 위치 경로
     * @param options
     *            복사 또는 이동 설정
     *
     * @return 변경된 파일 경로 객체
     *
     * @throws IOException
     *             I/O 오류가 발생한 경우.
     *
     * @since 2021. 2. 10.
     * @version 1.8.0
     * 
     * @see Files#move(Path, Path, CopyOption...)
     */
    // 아래 내용에 적용됨.
    // - Files.move(Paths.get(source), Paths.get(target), options)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static Path move(String source, String target, CopyOption... options) throws IOException {
        AssertUtils2.notNulls(source, target, options);

        return Files.move(Paths.get(source), Paths.get(target), options);
    }

    /**
     * InputStream으로부터 EOF에 도달하거나 지정된 길이만큼 바이트를 읽어 배열로 반환합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2026. 3. 30.     parkjunhong77@gmail.com         수동 버퍼 로직 제거 및 JDK 표준 I/O API 적용으로 O(N^2) 성능 장애 해결
     * </pre>
     *
     * @param inStream
     *            입력 스트림 ({@code NOT nullable})
     * @param length
     *            읽어들일 바이트 수. -1 또는 {@link Integer#MAX_VALUE}인 경우 가능한 모든 바이트를 읽음.
     * @param readAll
     *            {@code true}인 경우, 지정된 {@code length}만큼 읽지 못하고 EOF를 만나면 {@link EOFException}을 발생시킴. ({@code length}가
     *            -1이거나 {@link Integer#MAX_VALUE}인 경우 무시됨)
     * 
     * @return 스트림에서 읽어들인 바이트 배열
     *
     * @throws IOException
     *             I/O 예외가 발생하거나 조기 EOF(premature EOF)가 감지된 경우
     *
     * @since 2019. 8. 8.
     */
    // 아래 내용에 적용됨.
    // - inStream.readAllBytes()
    // - inStream.readNBytes(length)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static byte[] readFully(InputStream inStream, int length, boolean readAll) throws IOException {
        Objects.requireNonNull(inStream);

        if (length < 0 || length == Integer.MAX_VALUE) {
            return inStream.readAllBytes();
        }

        byte[] output = inStream.readNBytes(length);

        // [4] EOF 및 readAll 조건 검증 (예외 발생 시 디버깅 정보 포함)
        if (readAll && output.length < length) {
            throw new EOFException(String.format("Detected premature EOF. Expected: %d bytes, Read: %d bytes", length, output.length));
        }

        return output;
    }

    /**
     * {@link File} 객체를 제거하는 동작을 가진 Consumer를 반환합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 10. 26.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @return 삭제 기능을 수행하는 Consumer 반환
     *
     * @since 2018. 10. 26.
     * 
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
     * 대상 파일 객체에 문자열 데이터를 저장합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 3. 13.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param file
     *            저장할 객체
     * @param data
     *            저장할 데이터
     *
     * @return 저장 성공 여부
     *
     * @since 2019. 8. 8.
     */
    public static boolean save(File file, String data) {
        AssertUtils2.notNulls(file, data);

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
     * 대상 파일 객체에 특정 인코딩으로 문자열 데이터를 저장합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 3. 13.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param file
     *            저장할 대상 파일
     * @param data
     *            저장할 데이터
     * @param charset
     *            파일 인코딩 설정
     *
     * @return 저장 성공 여부
     *
     * @since 2019. 8. 8.
     */
    public static boolean save(File file, String data, String charset) {
        AssertUtils2.notNulls(file, data, charset);

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
     * 주어진 대상 경로 문자열에 문자열 데이터를 저장합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 3. 13.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param pathname
     *            저장할 파일 경로 명
     * @param data
     *            저장할 데이터
     *
     * @return 저장 성공 여부
     *
     * @since 2019. 8. 8.
     */
    public static boolean save(String pathname, String data) {
        AssertUtils2.notNulls(pathname, data);

        return save(new File(pathname), data);
    }

    /**
     * 주어진 대상 경로 문자열에 특정 인코딩으로 문자열 데이터를 저장합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 3. 13.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param pathname
     *            저장할 대상 경로 명
     * @param data
     *            저장할 데이터
     * @param charset
     *            파일 인코딩 설정
     *
     * @return 저장 성공 여부
     *
     * @since 2019. 8. 8.
     */
    public static boolean save(String pathname, String data, String charset) {
        AssertUtils2.notNulls(pathname, data, charset);

        return save(new File(pathname), data, charset);
    }

    /**
     * 가변 길이의 문자열을 조합하여 파일 경로를 생성합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 3. 13.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param strings
     *            경로 요소 문자열 배열
     *
     * @return 조합된 파일 경로 문자열
     *
     * @since 2019. 8. 8.
     */
    // 아래 내용에 적용됨.
    // - sb.append(strings[i]).toString()
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static String toFilepath(String... strings) {
        Objects.requireNonNull(strings);

        if (strings.length > 0) {
            StringBuilder sb = new StringBuilder();
            int i = 0;
            for (; i < strings.length - 1; i++) {
                sb.append(strings[i] + File.separator);
            }
            return sb.append(strings[i]).toString();
        } else {
            return "";
        }
    }

    /**
     * 지정된 경로에 데이터를 저장합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2023. 11. 15.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param filepath
     *            절대경로 파일
     * @param data
     *            데이터
     * @param append
     *            기존 파일에 추가 여부
     *
     * @return 데이터가 쓰인 파일의 경로 객체
     *
     * @throws IOException
     *             I/O 오류가 발생한 경우.
     *
     * @since 2023. 11. 15.
     * @version 2.0.0
     */
    // 아래 내용에 적용됨.
    // - options.toArray(new OpenOption[0])
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static Path write(Path filepath, String data, boolean append) throws IOException {
        AssertUtils2.notNulls(filepath, data);

        // 디렉토리 검증
        Path dir = filepath.getParent();
        if (!Files.exists(dir)) {
            Files.createDirectories(dir);
        }

        // 파일 검증
        if (!Files.exists(filepath)) {
            Files.createFile(filepath);
        }

        List<OpenOption> options = new ArrayList<>();
        options.add(StandardOpenOption.CREATE);
        options.add(StandardOpenOption.WRITE);
        if (append) {
            options.add(StandardOpenOption.APPEND);
        } else {
            options.add(StandardOpenOption.TRUNCATE_EXISTING);
        }

        return write(filepath, data, options.toArray(new OpenOption[0]));
    }

    /**
     * 지정된 경로에 데이터를 저장합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2023. 11. 15.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param path
     *            절대경로 파일
     * @param data
     *            데이터
     * @param options
     *            파일 열기/쓰기 옵션
     *
     * @return 데이터가 쓰인 파일의 경로 객체
     *
     * @throws IOException
     *             I/O 오류가 발생한 경우.
     *
     * @since 2023. 11. 15.
     * @version 2.0.0
     */
    // 아래 내용에 적용됨.
    // - Files.write(path, data.getBytes(), options)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static Path write(Path path, String data, OpenOption... options) throws IOException {
        AssertUtils2.notNulls(path, data, options);

        return Files.write(path, data.getBytes(), options);
    }

    /**
     * 지정된 경로에 데이터를 저장합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2023. 11. 7.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param dirpath
     *            디렉토리 경로
     * @param filename
     *            파일이름
     * @param data
     *            저장할 데이터
     * @param append
     *            데이터 추가 여부
     *
     * @return 데이터가 쓰인 파일의 경로 객체
     *
     * @throws IOException
     *             I/O 오류가 발생한 경우.
     *
     * @since 2023. 11. 7.
     * @version 2.0.0
     */
    // 아래 내용에 적용됨.
    // - Paths.get(dirpath, filename)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static Path write(String dirpath, String filename, String data, boolean append) throws IOException {
        AssertUtils2.notNulls(dirpath, filename, data);

        return write(Paths.get(dirpath, filename), data, append);
    }

    /**
     * 지정된 경로에 데이터를 저장합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2023. 11. 15.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param filepath
     *            파일 경로
     * @param data
     *            저장할 데이터
     *
     * @return 데이터가 쓰인 파일의 경로 객체
     *
     * @throws IOException
     *             I/O 오류가 발생한 경우.
     *
     * @since 2023. 11. 15.
     * @version 2.0.0
     */
    public static Path writeAppend(Path filepath, String data) throws IOException {
        return write(filepath, data, true);
    }

    /**
     * 지정된 경로에 데이터를 저장합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2023. 11. 7.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param filepath
     *            파일 경로
     * @param data
     *            저장할 데이터
     *
     * @return 데이터가 쓰인 파일의 경로 객체
     *
     * @throws IOException
     *             I/O 오류가 발생한 경우.
     *
     * @since 2023. 11. 7.
     * @version 2.0.0
     */
    // 아래 내용에 적용됨.
    // - Paths.get(filepath)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static Path writeAppend(String filepath, String data) throws IOException {
        return write(Paths.get(filepath), data, true);
    }

    /**
     * 지정된 경로에 데이터를 저장합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2023. 11. 7.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param dirpath
     *            디렉토리 경로
     * @param filename
     *            파일이름
     * @param data
     *            저장할 데이터
     *
     * @return 데이터가 쓰인 파일의 경로 객체
     *
     * @throws IOException
     *             I/O 오류가 발생한 경우.
     *
     * @since 2023. 11. 7.
     * @version 2.0.0
     */
    public static Path writeAppend(String dirpath, String filename, String data) throws IOException {
        return write(dirpath, filename, data, true);
    }

    /**
     * 지정된 경로에 데이터를 저장합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2023. 11. 7.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param filepath
     *            파일 경로
     * @param data
     *            저장할 데이터
     *
     * @return 데이터가 쓰인 파일의 경로 객체
     *
     * @throws IOException
     *             I/O 오류가 발생한 경우.
     *
     * @since 2023. 11. 7.
     * @version 2.0.0
     */
    // 아래 내용에 적용됨.
    // - Paths.get(filepath)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static Path writeNew(String filepath, String data) throws IOException {
        return write(Paths.get(filepath), data, false);
    }

    /**
     * 지정된 경로에 데이터를 저장합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2023. 11. 7.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param dirpath
     *            디렉토리 경로
     * @param filename
     *            파일이름
     * @param data
     *            저장할 데이터
     *
     * @return 데이터가 쓰인 파일의 경로 객체
     *
     * @throws IOException
     *             I/O 오류가 발생한 경우.
     *
     * @since 2023. 11. 7.
     * @version 2.0.0
     */
    public static Path writeNew(String dirpath, String filename, String data) throws IOException {
        return write(dirpath, filename, data, false);
    }
}
