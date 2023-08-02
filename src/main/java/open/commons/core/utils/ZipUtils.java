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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import open.commons.core.function.IOTripleFunction;

/**
 * 
 * @since 2018. 9. 10.
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public class ZipUtils {

    private static final int BUFFER_SIZE = 1024 * 2;

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
     * 2023. 8. 2.		박준홍			최초 작성
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
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
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
     * 2023. 8. 2.		박준홍			최초 작성
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
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
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
     * 2023. 8. 2.		박준홍			최초 작성
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
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static boolean ungzip(File inputFile, File outputFile) throws IOException {
        return ungzip(inputFile, StandardCharsets.UTF_8, outputFile);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2023. 8. 2.		박준홍			최초 작성
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
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static boolean ungzip(Path inputFile, Charset inCharset, Path outputFile) throws IOException {
        return decompress(inputFile, inCharset, outputFile, (inPath, cs, outPath) -> {
            // GZip 파일 입력
            GZIPInputStream gzipInStream = null;
            BufferedOutputStream outputStream = null;
            try {
                // 압축해제 파일 객체 생성
                Files.createFile(outPath);
                outputStream = new BufferedOutputStream(new FileOutputStream(outPath.toFile()));
                gzipInStream = new GZIPInputStream(new FileInputStream(inPath.toFile()));

                byte[] buffer = new byte[BUFFER_SIZE];
                int readCnt = -1;
                while ((readCnt = gzipInStream.read(buffer, 0, BUFFER_SIZE)) != -1) {
                    outputStream.write(buffer, 0, readCnt);
                }
                return true;
            } finally {
                IOUtils.close(gzipInStream, outputStream);
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
     * 2023. 8. 2.      박준홍         최초 작성
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
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
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
     * 2023. 8. 2.      박준홍         최초 작성
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
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
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
     * 2023. 8. 2.      박준홍         최초 작성
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
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
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
     * 2021. 11. 9.     박준홍         최초 작성
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
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
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
     * 2021. 11. 9.		박준홍			최초 작성
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
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static boolean unzip(File inputFile, File outputDir) throws IOException {
        return unzip(inputFile, StandardCharsets.UTF_8, outputDir);
    }

    /**
     * Zip 파일 압축을 해제합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 9.		박준홍			최초 작성
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
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static boolean unzip(Path inputFile, Charset inCharset, Path outputDir) throws IOException {
        return decompress(inputFile, inCharset, outputDir, (infile, cs, outdir) -> {

            // #3. 압축해제 디렉토리 생성
            if (!Files.exists(outdir)) {
                Files.createDirectories(outdir);
            }

            // Zip 파일 입력
            ZipInputStream zipInStream = null;
            // Zip 파일 내부 데이터
            ZipEntry entry = null;
            try {
                zipInStream = new ZipInputStream(new FileInputStream(infile.toFile()), cs);
                Path normalized = null;
                while ((entry = zipInStream.getNextEntry()) != null) {
                    // entry.getName()값이 상대경로(../../..) 표기인 경우 검증
                    normalized = outdir.resolve(entry.getName()).normalize();
                    if (!normalized.startsWith(outdir)) {
                        throw new ZipException(String.format("올바르지 않은 entry 입니다. entry=%s, normailzed=%s, output.dir=%s", entry.getName(), normalized, outdir));
                    }
                    // entry의 상위 디렉토리 생성
                    if (normalized.getParent() != null && !Files.exists(normalized.getParent())) {
                        Files.createDirectories(normalized.getParent());
                    }

                    // 파일 생성
                    Files.copy(zipInStream, normalized, StandardCopyOption.REPLACE_EXISTING);
                }
                return true;
            } finally {
                IOUtils.close(zipInStream);
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
     * 2021. 11. 9.     박준홍         최초 작성
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
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
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
     * 2021. 11. 9.     박준홍         최초 작성
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
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
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
     * 2021. 11. 9.		박준홍			최초 작성
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
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
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
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 9.		박준홍			최초 작성
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
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static boolean zip(File input, Charset inCharset, File output, Charset outCharset, int compressionLevel) throws IOException {

        if (!input.exists() || !(input.isDirectory() || input.isFile())) {
            throw new FileNotFoundException(input.getAbsolutePath());
        }

        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        ZipOutputStream zos = null;

        try {
            fos = new FileOutputStream(output); // FileOutputStream
            bos = new BufferedOutputStream(fos); // BufferedStream
            zos = new ZipOutputStream(bos, outCharset); // ZipOutputStream
            zos.setLevel(compressionLevel < 0 //
                    ? 0 //
                    : compressionLevel > 9 //
                            ? 9
                            : compressionLevel); // 압축 레벨
            zipEntry(input, input.getAbsolutePath(), zos); // Zip 파일 생성
            zos.finish(); // ZipOutputStream finish

            return true;
        } finally {
            IOUtils.close(zos, bos, fos);
        }
    }

    /**
     * 파일 또는 디렉토리를 압축합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 9. 10.     박준홍         최초 작성
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
     * 2021. 11. 9.		박준홍			최초 작성
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
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
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
     * 2018. 9. 10.     박준홍         최초 작성
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

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 9.		박준홍			최초 작성
     * </pre>
     *
     * @param sourceFile
     * @param sourcePath
     * @param zos
     * @throws IOException
     *
     * @since 2018. 9. 10.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    private static void zipEntry(File sourceFile, String sourcePath, ZipOutputStream zos) throws IOException {
        // sourceFile 이 디렉토리인 경우 하위 파일 리스트 가져와 재귀호출
        if (sourceFile.isDirectory()) {
            File[] fileArray = sourceFile.listFiles(); // sourceFile 의 하위 파일 리스트
            for (int i = 0; i < fileArray.length; i++) {
                zipEntry(fileArray[i], sourcePath, zos); // 재귀 호출
            }
        } else { // sourcehFile 이 디렉토리가 아닌 경우
            BufferedInputStream bis = null;
            try {
                String sFilePath = sourceFile.getPath();
                String zipEntryName = null;
                if (sFilePath.equals(sourcePath)) {
                    zipEntryName = FileUtils.getFileName(sourcePath);
                } else {
                    zipEntryName = sFilePath.substring(sourcePath.length() + 1, sFilePath.length());
                }

                bis = new BufferedInputStream(new FileInputStream(sourceFile));
                ZipEntry zentry = new ZipEntry(zipEntryName);
                zentry.setTime(sourceFile.lastModified());
                zos.putNextEntry(zentry);

                byte[] buffer = new byte[BUFFER_SIZE];
                int cnt = 0;
                while ((cnt = bis.read(buffer, 0, BUFFER_SIZE)) != -1) {
                    zos.write(buffer, 0, cnt);
                }
                zos.closeEntry();
            } finally {
                IOUtils.close(bis);
            }
        }
    }
}
