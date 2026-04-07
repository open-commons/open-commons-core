/*
 * Copyright 2018 Park Jun-Hong (parkjunhong77@gmail.com)
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
 * Date  : 2018. 9. 10. 오후 4:39:18
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */
package open.commons.core.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.stream.Stream;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import open.commons.core.function.IOTripleFunction;

/**
 * 압축 및 해제를 지원하는 유틸리티 클래스입니다.
 *
 * <pre>
 * [개정이력]
 * 날짜        | 작성자                    | 내용
 * ----------------------------------------------------------------------
 * 2018. 9. 10.      parkjunhong77@gmail.com     최초 작성
 * </pre>
 *
 * @since 2018. 9. 10.
 */
public class ZipUtils {

    /** 인스턴스 생성 방지 */
    private ZipUtils() {
    }

    /**
     * 압축 파일을 해제합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2023. 8. 2.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param inputFile
     *            압축 파일
     * @param inCharset
     *            압축 파일 캐릭터 셋
     * @param output
     *            압축 해제 파일 또는 디렉토리
     * @param decompressor
     *            압축 해제 함수 (입력 파일, 캐릭터 셋, 출력 파일 처리)
     *
     * @return 압축 해제 성공 여부
     *
     * @throws IOException
     *             압축 파일이 존재하지 않거나, 압축해제정보에 해당하는 파일이 존재하는 경우 발생.
     * @throws NullPointerException
     *             파라미터({@code inputFile}, {@code inCharset}, {@code output}, {@code decompressor} 중에 1개라도)가
     *             {@code null}인 경우 발생.
     *
     * @since 2023. 8. 2.
     * @version 2.0.0
     */
    private static boolean decompress(Path inputFile, Charset inCharset, Path output, IOTripleFunction<Path, Charset, Path, Boolean> decompressor) throws IOException {
        ObjectUtils.requireNonNulls(inputFile, inCharset, output, decompressor);

        if (!Files.exists(inputFile) || !Files.isRegularFile(inputFile)) {
            throw new FileNotFoundException(inputFile.toString());
        }

        if (Files.exists(output) && !Files.isDirectory(output)) {
            throw new FileNotFoundException(String.format("'%s' 정보가 올바르지 않습니다.", inputFile.toAbsolutePath()));
        }

        return decompressor.apply(inputFile, inCharset, output);
    }

    /**
     * GZ 파일 압축을 해제합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2023. 8. 2.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param inputFile
     *            압축 파일
     * @param inCharset
     *            압축 파일 캐릭터 셋
     * @param outputFile
     *            압축 해제 파일
     *
     * @return 압축 해제 성공 여부
     *
     * @throws IOException
     *             파일 입출력 중 오류가 발생한 경우.
     * @throws NullPointerException
     *             파라미터({@code inputFile}, {@code inCharset}, {@code outputFile} 중에 1개라도)가 {@code null}인 경우 발생.
     *
     * @since 2023. 8. 2.
     * @version 2.0.0
     */
    // 아래 내용에 적용됨.
    // - File.toPath()
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static boolean ungzip(File inputFile, Charset inCharset, File outputFile) throws IOException {
        ObjectUtils.requireNonNulls(inputFile, inCharset, outputFile);
        return ungzip(inputFile.toPath(), inCharset, outputFile.toPath());
    }

    /**
     * GZ 파일 압축을 해제합니다. (기본 캐릭터 셋: UTF-8)
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2023. 8. 2.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param inputFile
     *            압축 파일
     * @param outputFile
     *            압축 해제 파일
     *
     * @return 압축 해제 성공 여부
     *
     * @throws IOException
     *             파일 입출력 중 오류가 발생한 경우.
     * @throws NullPointerException
     *             파라미터({@code inputFile}, {@code outputFile} 중에 1개라도)가 {@code null}인 경우 발생.
     *
     * @since 2023. 8. 2.
     * @version 2.0.0
     */
    public static boolean ungzip(File inputFile, File outputFile) throws IOException {
        ObjectUtils.requireNonNulls(inputFile, outputFile);

        return ungzip(inputFile, CharUtils.defaultCharset(), outputFile);
    }

    /**
     * GZ 파일 압축을 해제합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2023. 8. 2.      parkjunhong77@gmail.com     최초 작성
     * 2026. 3. 10.     parkjunhong77@gmail.com     (3.0.0) JDK 25 마이그레이션: transferTo 및 Unnamed Variable 적용
     * </pre>
     *
     * @param inputFile
     *            압축 파일
     * @param inCharset
     *            압축 파일 캐릭터 셋
     * @param outputFile
     *            압축 해제 파일
     *
     * @return 압축 해제 성공 여부
     *
     * @throws IOException
     *             파일 입출력 중 오류가 발생한 경우.
     * @throws NullPointerException
     *             파라미터({@code inputFile}, {@code inCharset}, {@code outputFile} 중에 1개라도)가 {@code null}인 경우 발생.
     *
     * @since 2023. 8. 2.
     * @version 3.0.0
     */
    public static boolean ungzip(Path inputFile, Charset inCharset, Path outputFile) throws IOException {
        ObjectUtils.requireNonNulls(inputFile, inCharset, outputFile);

        return decompress(inputFile, inCharset, outputFile, (inPath, _, outPath) -> {
            try (GZIPInputStream gzipInStream = new GZIPInputStream(Files.newInputStream(inPath));
                    OutputStream outputStream = Files.newOutputStream(outPath, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {

                gzipInStream.transferTo(outputStream);
                return true;
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        });
    }

    /**
     * GZ 파일 압축을 해제합니다. (기본 캐릭터 셋: UTF-8)
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2023. 8. 2.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param inputFile
     *            압축 파일
     * @param outputFile
     *            압축 해제 파일
     *
     * @return 압축 해제 성공 여부
     *
     * @throws IOException
     *             파일 입출력 중 오류가 발생한 경우.
     * @throws NullPointerException
     *             파라미터({@code inputFile}, {@code outputFile} 중에 1개라도)가 {@code null}인 경우 발생.
     *
     * @since 2023. 8. 2.
     * @version 2.0.0
     */
    public static boolean ungzip(Path inputFile, Path outputFile) throws IOException {
        ObjectUtils.requireNonNulls(inputFile, outputFile);
        return ungzip(inputFile, CharUtils.defaultCharset(), outputFile);
    }

    /**
     * GZ 파일 압축을 해제합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2023. 8. 2.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param inputFile
     *            압축 파일 경로 문자열
     * @param inCharset
     *            압축 파일 캐릭터 셋
     * @param outputFile
     *            압축 해제 파일 경로 문자열
     *
     * @return 압축 해제 성공 여부
     *
     * @throws IOException
     *             파일 입출력 중 오류가 발생한 경우.
     * @throws NullPointerException
     *             파라미터({@code inputFile}, {@code inCharset}, {@code outputFile} 중에 1개라도)가 {@code null}인 경우 발생.
     *
     * @since 2023. 8. 2.
     * @version 2.0.0
     */
    public static boolean ungzip(String inputFile, Charset inCharset, String outputFile) throws IOException {
        ObjectUtils.requireNonNulls(inputFile, inCharset, outputFile);
        return ungzip(new File(inputFile), inCharset, new File(outputFile));
    }

    /**
     * GZ 파일 압축을 해제합니다. (기본 캐릭터 셋: UTF-8)
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2023. 8. 2.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param inputFile
     *            압축 파일 경로 문자열
     * @param outputFile
     *            압축 해제 파일 경로 문자열
     *
     * @return 압축 해제 성공 여부
     *
     * @throws IOException
     *             파일 입출력 중 오류가 발생한 경우.
     * @throws NullPointerException
     *             파라미터({@code inputFile}, {@code outputFile} 중에 1개라도)가 {@code null}인 경우 발생.
     *
     * @since 2023. 8. 2.
     * @version 2.0.0
     */
    public static boolean ungzip(String inputFile, String outputFile) throws IOException {
        ObjectUtils.requireNonNulls(inputFile, outputFile);
        return ungzip(inputFile, CharUtils.defaultCharset(), outputFile);
    }

    /**
     * Zip 파일 압축을 해제합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2021. 11. 9.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param inputFile
     *            압축(ZIP) 파일
     * @param inCharset
     *            압축 파일 캐릭터 셋
     * @param outputDir
     *            압축 해제 대상 디렉토리
     *
     * @return 압축 해제 성공 여부
     *
     * @throws IOException
     *             파일 입출력 중 오류가 발생한 경우.
     * @throws NullPointerException
     *             파라미터({@code inputFile}, {@code inCharset}, {@code outputDir} 중에 1개라도)가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 9.
     * @version 1.8.0
     */
    // 아래 내용에 적용됨.
    // - File.toPath()
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static boolean unzip(File inputFile, Charset inCharset, File outputDir) throws IOException {
        ObjectUtils.requireNonNulls(inputFile, inCharset, outputDir);
        return unzip(inputFile.toPath(), inCharset, outputDir.toPath());
    }

    /**
     * Zip 파일 압축을 해제합니다. (기본 캐릭터 셋: UTF-8)
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2021. 11. 9.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param inputFile
     *            압축(ZIP) 파일
     * @param outputDir
     *            압축 해제 대상 디렉토리
     *
     * @return 압축 해제 성공 여부
     *
     * @throws IOException
     *             파일 입출력 중 오류가 발생한 경우.
     * @throws NullPointerException
     *             파라미터({@code inputFile}, {@code outputDir} 중에 1개라도)가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 9.
     * @version 1.8.0
     */
    public static boolean unzip(File inputFile, File outputDir) throws IOException {
        ObjectUtils.requireNonNulls(inputFile, outputDir);
        return unzip(inputFile, CharUtils.defaultCharset(), outputDir);
    }

    /**
     * Zip 파일 압축을 해제합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2021. 11. 9.      parkjunhong77@gmail.com     최초 작성
     * 2026. 3. 10.     parkjunhong77@gmail.com     (3.0.0) JDK 25 마이그레이션: Zip Slip 취약점 패치 및 자원 관리 개선
     * </pre>
     *
     * @param inputFile
     *            압축(ZIP) 파일
     * @param inCharset
     *            압축 파일 캐릭터 셋
     * @param outputDir
     *            압축 해제 대상 디렉토리
     *
     * @return 압축 해제 성공 여부
     *
     * @throws IOException
     *             파일 입출력 오류 또는 Zip Slip 보안 위협이 탐지된 경우 발생.
     * @throws NullPointerException
     *             파라미터({@code inputFile}, {@code inCharset}, {@code outputDir} 중에 1개라도)가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 9.
     * @version 3.0.0
     */
    public static boolean unzip(Path inputFile, Charset inCharset, Path outputDir) throws IOException {
        ObjectUtils.requireNonNulls(inputFile, inCharset, outputDir);

        return decompress(inputFile, inCharset, outputDir, (infile, cs, outdir) -> {
            try {
                if (!Files.exists(outdir)) {
                    Files.createDirectories(outdir);
                }

                Path outdirAbs = outdir.toAbsolutePath().normalize();

                try (ZipInputStream zipInStream = new ZipInputStream(Files.newInputStream(infile), cs)) {
                    ZipEntry entry;
                    while ((entry = zipInStream.getNextEntry()) != null) {
                        Path normalized = outdirAbs.resolve(entry.getName()).normalize();

                        if (!normalized.startsWith(outdirAbs)) {
                            throw new ZipException(String.format("보안 경고 (Zip Slip): 올바르지 않은 entry 입니다. entry=%s", entry.getName()));
                        }

                        if (entry.isDirectory()) {
                            Files.createDirectories(normalized);
                        } else {
                            if (normalized.getParent() != null && !Files.exists(normalized.getParent())) {
                                Files.createDirectories(normalized.getParent());
                            }
                            try (OutputStream os = Files.newOutputStream(normalized, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
                                zipInStream.transferTo(os);
                            }
                        }
                    }
                    return true;
                }
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        });
    }

    /**
     * Zip 파일 압축을 해제합니다. (기본 캐릭터 셋: UTF-8)
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2021. 11. 9.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param inputFile
     *            압축(ZIP) 파일
     * @param outputDir
     *            압축 해제 대상 디렉토리
     *
     * @return 압축 해제 성공 여부
     *
     * @throws IOException
     *             파일 입출력 중 오류가 발생한 경우.
     * @throws NullPointerException
     *             파라미터({@code inputFile}, {@code outputDir} 중에 1개라도)가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 9.
     * @version 1.8.0
     */
    public static boolean unzip(Path inputFile, Path outputDir) throws IOException {
        ObjectUtils.requireNonNulls(inputFile, outputDir);

        return unzip(inputFile, CharUtils.defaultCharset(), outputDir);
    }

    /**
     * Zip 파일 압축을 해제합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2021. 11. 9.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param inputFile
     *            압축(ZIP) 파일 경로 문자열
     * @param inCharset
     *            압축 파일 캐릭터 셋
     * @param outputDir
     *            압축 해제 디렉토리 경로 문자열
     *
     * @return 압축 해제 성공 여부
     *
     * @throws IOException
     *             파일 입출력 중 오류가 발생한 경우.
     * @throws NullPointerException
     *             파라미터({@code inputFile}, {@code inCharset}, {@code outputDir} 중에 1개라도)가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 9.
     * @version 1.8.0
     */
    public static boolean unzip(String inputFile, Charset inCharset, String outputDir) throws IOException {
        ObjectUtils.requireNonNulls(inputFile, inCharset, outputDir);

        return unzip(new File(inputFile), inCharset, new File(outputDir));
    }

    /**
     * Zip 파일 압축을 해제합니다. (기본 캐릭터 셋: UTF-8)
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2021. 11. 9.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param inputFile
     *            압축(ZIP) 파일 경로 문자열
     * @param outputDir
     *            압축 해제 디렉토리 경로 문자열
     *
     * @return 압축 해제 성공 여부
     *
     * @throws IOException
     *             파일 입출력 중 오류가 발생한 경우.
     * @throws NullPointerException
     *             파라미터({@code inputFile}, {@code outputDir} 중에 1개라도)가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 9.
     * @version 1.8.0
     */
    public static boolean unzip(String inputFile, String outputDir) throws IOException {
        ObjectUtils.requireNonNulls(inputFile, outputDir);

        return unzip(inputFile, CharUtils.defaultCharset(), outputDir);
    }

    /**
     * 파일 또는 디렉토리를 압축합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2021. 11. 9.      parkjunhong77@gmail.com     최초 작성
     * 2026. 3. 10.     parkjunhong77@gmail.com     (3.0.0) JDK 25 마이그레이션: Files.walk 적용 및 재귀 호출 제거
     * </pre>
     *
     * @param input
     *            압축 대상 디렉토리 또는 파일
     * @param inCharset
     *            입력 파일 캐릭터 셋
     * @param output
     *            생성할 압축 파일 경로
     * @param outCharset
     *            압축 파일 캐릭터 셋
     * @param compressionLevel
     *            압축 레벨 (0 ~ 9)
     *
     * @return 압축 성공 여부
     *
     * @throws IOException
     *             파일 입출력 중 오류가 발생한 경우.
     * @throws NullPointerException
     *             파라미터({@code input}, {@code inCharset}, {@code output}, {@code outCharset} 중에 1개라도)가 {@code null}인 경우
     *             발생.
     *
     * @since 2021. 11. 9.
     * @version 3.0.0
     */
    public static boolean zip(File input, Charset inCharset, File output, Charset outCharset, int compressionLevel) throws IOException {
        ObjectUtils.requireNonNulls(input, inCharset, output, outCharset);

        if (!input.exists() || !(input.isDirectory() || input.isFile())) {
            throw new FileNotFoundException(input.getAbsolutePath());
        }

        Path inPath = input.toPath();

        try (OutputStream fos = Files.newOutputStream(output.toPath()); ZipOutputStream zos = new ZipOutputStream(fos, outCharset)) {
            zos.setLevel(Math.max(0, Math.min(9, compressionLevel)));

            if (Files.isDirectory(inPath)) {
                try (Stream<Path> paths = Files.walk(inPath)) {
                    paths.filter(path -> !Files.isDirectory(path)).forEach(path -> {
                        try {
                            String zipEntryName = inPath.relativize(path).toString().replace("\\", "/");
                            ZipEntry zentry = new ZipEntry(zipEntryName);
                            zentry.setTime(Files.getLastModifiedTime(path).toMillis());

                            zos.putNextEntry(zentry);
                            try (InputStream is = Files.newInputStream(path)) {
                                is.transferTo(zos);
                            }
                            zos.closeEntry();
                        } catch (IOException e) {
                            throw new UncheckedIOException(e);
                        }
                    });
                }
            } else {
                ZipEntry zentry = new ZipEntry(inPath.getFileName().toString());
                zentry.setTime(Files.getLastModifiedTime(inPath).toMillis());
                zos.putNextEntry(zentry);
                try (InputStream is = Files.newInputStream(inPath)) {
                    is.transferTo(zos);
                }
                zos.closeEntry();
            }

            return true;
        } catch (UncheckedIOException e) {
            throw e.getCause();
        }
    }

    /**
     * 파일 또는 디렉토리를 압축합니다. (기본 캐릭터 셋: UTF-8)
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2018. 9. 10.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param input
     *            압축 대상 디렉토리 또는 파일
     * @param output
     *            생성할 압축 파일 경로
     * @param compressionLevel
     *            압축 레벨 (0 ~ 9)
     *
     * @return 압축 성공 여부
     *
     * @throws IOException
     *             파일 입출력 중 오류가 발생한 경우.
     * @throws NullPointerException
     *             파라미터({@code input}, {@code output} 중에 1개라도)가 {@code null}인 경우 발생.
     *
     * @since 2018. 9. 10.
     */
    public static boolean zip(File input, File output, int compressionLevel) throws IOException {
        ObjectUtils.requireNonNulls(input, output);

        return zip(input, CharUtils.defaultCharset(), output, CharUtils.defaultCharset(), compressionLevel);
    }

    /**
     * 파일 또는 디렉토리를 압축합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2021. 11. 9.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param input
     *            압축 대상 디렉토리 또는 파일 경로 문자열
     * @param inCharset
     *            입력 파일 캐릭터 셋
     * @param output
     *            생성할 압축 파일 경로 문자열
     * @param outCharset
     *            압축 파일 캐릭터 셋
     * @param compressionLevel
     *            압축 레벨 (0 ~ 9)
     *
     * @return 압축 성공 여부
     *
     * @throws IOException
     *             파일 입출력 중 오류가 발생한 경우.
     * @throws NullPointerException
     *             파라미터({@code input}, {@code inCharset}, {@code output}, {@code outCharset} 중에 1개라도)가 {@code null}인 경우
     *             발생.
     *
     * @since 2021. 11. 9.
     * @version 1.8.0
     */
    public static boolean zip(String input, Charset inCharset, String output, Charset outCharset, int compressionLevel) throws IOException {
        ObjectUtils.requireNonNulls(input, inCharset, output, outCharset);

        return zip(new File(input), inCharset, new File(output), outCharset, compressionLevel);
    }

    /**
     * 파일 또는 디렉토리를 압축합니다. (기본 캐릭터 셋: UTF-8)
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2018. 9. 10.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param input
     *            압축 대상 디렉토리 또는 파일 경로 문자열
     * @param output
     *            생성할 압축 파일 경로 문자열
     * @param compressionLevel
     *            압축 레벨 (0 ~ 9)
     *
     * @return 압축 성공 여부
     *
     * @throws IOException
     *             파일 입출력 중 오류가 발생한 경우.
     * @throws NullPointerException
     *             파라미터({@code input}, {@code output} 중에 1개라도)가 {@code null}인 경우 발생.
     *
     * @since 2018. 9. 10.
     */
    public static boolean zip(String input, String output, int compressionLevel) throws IOException {
        ObjectUtils.requireNonNulls(input, output);

        return zip(input, CharUtils.defaultCharset(), output, CharUtils.defaultCharset(), compressionLevel);
    }
}