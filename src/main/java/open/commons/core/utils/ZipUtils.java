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
import java.nio.charset.StandardCharsets;
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
 * @since 2018. 9. 10.
 * 
 */
public class ZipUtils {

    // prevent to create an instance.
    private ZipUtils() {
    }

    /**
     * 압축 파일을 해제합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2023. 8. 2.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     *
     * @param inputFile
     *            압축 파일
     * @param inCharset
     *            압축 파일 캐릭터 셋
     * @param output
     *            압축 해제 파일 또는 디렉토리
     * @param decompressor
     *            압축 해제 함수.
     *            <ul>
     *            <li>Path: 압축 파일
     *            <li>Charset: 파일 캐릭터 셋
     *            <li>Path: 압축 해제 파일
     *            </ul>
     * @return
     * @throws IOException
     *             압축 파일이 존재하지 않거나, 압축해제정보에 해당하는 파일이 존재하는 경우
     *
     * @since 2023. 8. 2.
     * @version 2.0.0
     * 
     */
    private static boolean decompress(Path inputFile, Charset inCharset, Path output, IOTripleFunction<Path, Charset, Path, Boolean> decompressor) throws IOException {

        // #1. 입력 파일 확인
        if (!Files.exists(inputFile) || !Files.isRegularFile(inputFile)) {
            throw new FileNotFoundException(inputFile.toString());
        }

        // #2. 압축해제파일 검증
        if (Files.exists(output) && !Files.isDirectory(output)) {
            throw new FileNotFoundException(String.format("'%s' 정보가 올바르지 않습니다.", inputFile.toAbsolutePath()));
        }

        return decompressor.apply(inputFile, inCharset, output);
    }

    /**
     * GZ 파일 압축을 해제 합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2023. 8. 2.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     *
     * @param inputFile
     *            압축 파일
     * @param inCharset
     *            압축 파일 캐릭터 셋
     * @param outputFile
     *            압축 해제 파일
     * @return
     * @throws IOException
     *
     * @since 2023. 8. 2.
     * @version 2.0.0
     * 
     */
    public static boolean ungzip(File inputFile, Charset inCharset, File outputFile) throws IOException {
        return ungzip(inputFile.toPath(), inCharset, outputFile.toPath());
    }

    /**
     * GZ 파일 압축을 해제 합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2023. 8. 2.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     *
     * @param inputFile
     *            압축 파일
     * @param outputFile
     *            압축 해제 파일
     * @return
     * @throws IOException
     *
     * @since 2023. 8. 2.
     * @version 2.0.0
     * 
     */
    public static boolean ungzip(File inputFile, File outputFile) throws IOException {
        return ungzip(inputFile, StandardCharsets.UTF_8, outputFile);
    }

    /**
     * GZ 파일 압축을 해제 합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2023. 8. 2.		    parkjunohng77@gmail.com			최초 작성
     * 2026. 3. 10.         parkjunhong77@gmail.com         (3.0.0) JDK 25 마이그레이션: transferTo 및 try-with-resources 적용
     * </pre>
     *
     * @param inputFile
     *            압축 파일
     * @param inCharset
     *            압축 파일 캐릭터 셋
     * @param outputFile
     *            압축 해제 파일
     * @return
     * @throws IOException
     *
     * @since 2023. 8. 2.
     * @version 3.0.0
     * 
     */
    public static boolean ungzip(Path inputFile, Charset inCharset, Path outputFile) throws IOException {
        return decompress(inputFile, inCharset, outputFile, (inPath, _, outPath) -> {
            // [최적화] try-with-resources 적용 및 수동 루프를 transferTo()로 교체
            try (GZIPInputStream gzipInStream = new GZIPInputStream(Files.newInputStream(inPath)); //
                    OutputStream outputStream = Files.newOutputStream(outPath, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {

                gzipInStream.transferTo(outputStream);
                return true;
            }
        });
    }

    /**
     * GZ 파일 압축을 해제 합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2023. 8. 2.      parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param inputFile
     *            압축 파일
     * @param outputFile
     *            압축 해제 파일
     * @return
     * @throws IOException
     *
     * @since 2023. 8. 2.
     * @version 2.0.0
     * 
     */
    public static boolean ungzip(Path inputFile, Path outputFile) throws IOException {
        return ungzip(inputFile, StandardCharsets.UTF_8, outputFile);
    }

    /**
     * GZ 파일 압축을 해제 합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2023. 8. 2.      parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param inputFile
     *            압축 파일
     * @param inCharset
     *            압축 파일 캐릭터 셋
     * @param outputFile
     *            압축 해제 파일
     * @return
     * @throws IOException
     *
     * @since 2023. 8. 2.
     * @version 2.0.0
     * 
     */
    public static boolean ungzip(String inputFile, Charset inCharset, String outputFile) throws IOException {
        return ungzip(new File(inputFile), inCharset, new File(outputFile));
    }

    /**
     * GZ 파일 압축을 해제 합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2023. 8. 2.      parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param inputFile
     *            압축 파일
     * @param outputFile
     *            압축 해제 파일
     * @return
     * @throws IOException
     *
     * @since 2023. 8. 2.
     * @version _._._
     * 
     */
    public static boolean ungzip(String inputFile, String outputFile) throws IOException {
        return ungzip(inputFile, StandardCharsets.UTF_8, outputFile);
    }

    /**
     * Zip 파일 압축을 해제합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 9.     parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param inputFile
     *            압축(ZIP) 파일
     * @param inCharset
     *            압축 파일 캐릭터 셋
     * @param outputDir
     *            압축 해제 경로
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 9.
     * @version 1.8.0
     * 
     */
    public static boolean unzip(File inputFile, Charset inCharset, File outputDir) throws IOException {
        return unzip(inputFile.toPath(), inCharset, outputDir.toPath());
    }

    /**
     * Zip 파일 압축을 해제합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 9.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     *
     * @param inputFile
     *            압축(ZIP) 파일
     * @param outputDir
     *            압축 해제 경로
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 9.
     * @version 1.8.0
     * 
     */
    public static boolean unzip(File inputFile, File outputDir) throws IOException {
        return unzip(inputFile, StandardCharsets.UTF_8, outputDir);
    }

    /**
     * Zip 파일 압축을 해제합니다. <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜       | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 9.     parkjunohng77@gmail.com         최초 작성
     * 2026. 3. 10.     parkjunhong77@gmail.com         (3.0.0) JDK 25 마이그레이션: Zip Slip 취약점 패치 및 자원 관리 개선
     * </pre>
     *
     * @param inputFile
     *            압축(ZIP) 파일
     * @param inCharset
     *            압축 파일 캐릭터 셋
     * @param outputDir
     *            압축 해제 경로
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 9.
     * @version 3.0.0
     * 
     */
    public static boolean unzip(Path inputFile, Charset inCharset, Path outputDir) throws IOException {
        return decompress(inputFile, inCharset, outputDir, (infile, cs, outdir) -> {

            // #3. 압축해제 디렉토리 생성
            if (!Files.exists(outdir)) {
                Files.createDirectories(outdir);
            }

            // [보안] Zip Slip 방지를 위해 절대 경로로 정규화
            Path outdirAbs = outdir.toAbsolutePath().normalize();

            // [최적화] try-with-resources 적용
            try (ZipInputStream zipInStream = new ZipInputStream(Files.newInputStream(infile), cs)) {
                ZipEntry entry;
                while ((entry = zipInStream.getNextEntry()) != null) {
                    Path normalized = outdirAbs.resolve(entry.getName()).normalize();

                    // [보안] 상위 경로 접근을 통한 악의적인 파일 덮어쓰기 방지
                    if (!normalized.startsWith(outdirAbs)) {
                        throw new ZipException(String.format("보안 경고 (Zip Slip): 올바르지 않은 entry 입니다. entry=%s", entry.getName()));
                    }

                    if (entry.isDirectory()) {
                        Files.createDirectories(normalized);
                    } else {
                        if (normalized.getParent() != null && !Files.exists(normalized.getParent())) {
                            Files.createDirectories(normalized.getParent());
                        }
                        // Files.copy를 transferTo와 newOutputStream으로 최적화 교체
                        try (OutputStream os = Files.newOutputStream(normalized, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
                            zipInStream.transferTo(os);
                        }
                    }
                }
                return true;
            }
        });
    }

    /**
     * Zip 파일 압축을 해제합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 9.     parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param inputFile
     *            압축(ZIP) 파일
     * @param outputDir
     *            압축 해제 경로
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 9.
     * @version 1.8.0
     * 
     */
    public static boolean unzip(Path inputFile, Path outputDir) throws IOException {
        return unzip(inputFile, StandardCharsets.UTF_8, outputDir);
    }

    /**
     * Zip 파일 압축을 해제합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 9.     parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param inputFile
     *            압축(ZIP) 파일
     * @param inCharset
     *            압축 파일 캐릭터 셋
     * @param outputDir
     *            압축 해제 경로
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 9.
     * @version 1.8.0
     * 
     */
    public static boolean unzip(String inputFile, Charset inCharset, String outputDir) throws IOException {
        return unzip(new File(inputFile), inCharset, new File(outputDir));
    }

    /**
     * Zip 파일 압축을 해제합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 9.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     *
     * @param inputFile
     *            압축(ZIP) 파일
     * @param outputDir
     *            압축 해제 경로
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 9.
     * @version 1.8.0
     * 
     */
    public static boolean unzip(String inputFile, String outputDir) throws IOException {
        return unzip(inputFile, StandardCharsets.UTF_8, outputDir);
    }

    /**
     * 파일 또는 디렉토리를 압축합니다. <br>
     * <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜       | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 9.     parkjunohng77@gmail.com         최초 작성
     * 2026. 3. 10.     parkjunhong77@gmail.com         (3.0.0) JDK 25 마이그레이션: Files.walk 적용 및 재귀 호출 제거
     * </pre>
     *
     * @param input
     *            압축대상 디렉토리 또는 파일
     * @param inCharset
     *            입력파일 캐릭터셋
     * @param output
     *            압축파일 경로
     * @param outCharset
     *            압축파일 캐릭터셋
     * @param compressionLevel
     *            압축레벨, 0 ~ 9
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 9.
     * @version 3.0.0
     * 
     */
    public static boolean zip(File input, Charset inCharset, File output, Charset outCharset, int compressionLevel) throws IOException {

        if (!input.exists() || !(input.isDirectory() || input.isFile())) {
            throw new FileNotFoundException(input.getAbsolutePath());
        }

        Path inPath = input.toPath();

        // [최적화] 중첩된 스트림을 try-with-resources에서 한 번에 안전하게 생성
        try (OutputStream fos = Files.newOutputStream(output.toPath()); ZipOutputStream zos = new ZipOutputStream(fos, outCharset)) {

            // 안전한 압축 레벨 바인딩 (수학적 치환)
            zos.setLevel(Math.max(0, Math.min(9, compressionLevel)));

            if (Files.isDirectory(inPath)) {
                // [최적화] 재귀 함수(zipEntry) 대신 Stream API 기반의 Files.walk() 사용
                try (Stream<Path> paths = Files.walk(inPath)) {
                    paths.filter(path -> !Files.isDirectory(path)).forEach(path -> {
                        try {
                            // OS 상관없이 Zip 표준에 맞게 디렉토리 구분자를 '/'로 변환
                            String zipEntryName = inPath.relativize(path).toString().replace("\\", "/");
                            ZipEntry zentry = new ZipEntry(zipEntryName);
                            zentry.setTime(Files.getLastModifiedTime(path).toMillis());

                            zos.putNextEntry(zentry);
                            try (InputStream is = Files.newInputStream(path)) {
                                is.transferTo(zos);
                            }
                            zos.closeEntry();
                        } catch (IOException e) {
                            // Stream 루프 내부의 IOException을 Unchecked로 감싸서 던짐
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
            // 내부 Stream에서 발생한 IO 오류 복원
            throw e.getCause();
        }
    }

    /**
     * 파일 또는 디렉토리를 압축합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 9. 10.     parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param input
     *            압축대상 디렉토리 또는 파일
     * @param output
     *            압축파일 경로
     * @param compressionLevel
     *            압축레벨, 0 ~ 9
     * @return
     *
     * @since 2018. 9. 10.
     */
    public static boolean zip(File input, File output, int compressionLevel) throws IOException {
        return zip(input, StandardCharsets.UTF_8, output, StandardCharsets.UTF_8, compressionLevel);
    }

    /**
     * 파일 또는 디렉토리를 압축합니다. <br>
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 9.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     *
     * @param input
     *            압축대상 디렉토리 또는 파일
     * @param inCharset
     *            입력파일 캐릭터셋
     * @param output
     *            압축파일 경로
     * @param outCharset
     *            압축파일 캐릭터셋
     * @param compressionLevel
     *            압축레벨, 0 ~ 9
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 9.
     * @version 1.8.0
     * 
     */
    public static boolean zip(String input, Charset inCharset, String output, Charset outCharset, int compressionLevel) throws IOException {
        return zip(new File(input), inCharset, new File(output), outCharset, compressionLevel);
    }

    /**
     * 파일 또는 디렉토리를 압축합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 9. 10.     parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param input
     *            압축대상 디렉토리 또는 파일
     * @param output
     *            압축파일 경로
     * @param compressionLevel
     *            압축레벨, 0 ~ 9
     * @return
     *
     * @since 2018. 9. 10.
     */
    public static boolean zip(String input, String output, int compressionLevel) throws IOException {
        return zip(input, StandardCharsets.UTF_8, output, StandardCharsets.UTF_8, compressionLevel);
    }
}
