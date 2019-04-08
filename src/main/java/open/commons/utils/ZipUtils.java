/*
 * Copyright 2018 Park Jun-Hong (parkjunhong77/google/com)
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
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package open.commons.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 
 * @since 2018. 9. 10.
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
public class ZipUtils {

    private static final int BUFFER_SIZE = 1024 * 2;

    // prevent to create an instance.
    private ZipUtils() {
    }

    /**
     * 파일 또는 디렉토리를 압축한다. <br>
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

        if (!input.exists() || !(input.isDirectory() || input.isFile())) {
            throw new FileNotFoundException(input.getAbsolutePath());
        }

        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        ZipOutputStream zos = null;

        try {
            fos = new FileOutputStream(output); // FileOutputStream
            bos = new BufferedOutputStream(fos); // BufferedStream
            zos = new ZipOutputStream(bos); // ZipOutputStream
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
                String zipEntryName = sFilePath.substring(sourcePath.length() + 1, sFilePath.length());

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
