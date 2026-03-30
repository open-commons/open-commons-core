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
* 
* This file is generated under this project, "open-commons-core".
*
* Date  : 2012. 03. 13. 오전 10:06:00
*
* Author: Park Jun-Hong (parkjunhong77@gmail.com)
* 
*/
package open.commons.core.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import org.jspecify.annotations.Nullable;

/**
 * @since 2012. 03. 13.
 * 
 */
public class FilenameUtils {

    /**
     * 문자열을 접두사 형태로 변환합니다.
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
     * @param s
     *            대상 문자열
     * @return 접두사 문자열
     *
     * @since 2012. 3. 13.
     */
    private static String $prefix(@Nullable String s) {
        return s == null || s.equals("") ? "" : s + "_";
    }

    /**
     * 문자열을 접미사 형태로 변환합니다.
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
     * @param s
     *            대상 문자열
     * @return 접미사 문자열
     *
     * @since 2012. 3. 13.
     */
    private static String $suffix(@Nullable String s) {
        return s == null || s.equals("") ? "" : "_" + s;
    }

    /**
     * 파일과 접미사를 이용하여 출력 디렉토리 이름을 생성합니다.
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
     *            대상 파일
     * @param suffix
     *            접미사
     * @return 출력 디렉토리 이름
     *
     * @throws NullPointerException
     *             파라미터({@code file})가 {@code null}인 경우 발생.
     *
     * @since 2012. 3. 13.
     */
    public static String toOutputDir(File file, String suffix) {
        Objects.requireNonNull(file);

        return file.getName() + "_" + new SimpleDateFormat("yyyyMMdd_hhmmss").format(new Date()) + $suffix(suffix);
    }

    /**
     * 파일, 접두사, 접미사를 이용하여 출력 디렉토리 이름을 생성합니다.
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
     * @param inputfile
     *            대상 파일
     * @param prefix
     *            접두사
     * @param suffix
     *            접미사
     * @return 출력 디렉토리 이름
     *
     * @throws NullPointerException
     *             파라미터({@code inputfile})가 {@code null}인 경우 발생.
     *
     * @since 2012. 3. 13.
     */
    public static String toOutputDir(File inputfile, String prefix, String suffix) {
        Objects.requireNonNull(inputfile);

        return $prefix(prefix) + inputfile.getName() + "_" + new SimpleDateFormat("yyyyMMdd_hhmmss").format(new Date()) + $suffix(suffix);
    }

    /**
     * 파일 이름 문자열을 이용하여 출력 디렉토리 이름을 생성합니다.
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
     * @param inputfileString
     *            대상 파일 이름 문자열
     * @return 출력 디렉토리 이름
     *
     * @throws NullPointerException
     *             파라미터({@code inputfileString})가 {@code null}인 경우 발생.
     *
     * @since 2012. 3. 13.
     */
    public static String toOutputDir(String inputfileString) {
        Objects.requireNonNull(inputfileString);

        return inputfileString + "_" + new SimpleDateFormat("yyyyMMdd_hhmmss").format(new Date());
    }

    /**
     * 파일 이름 문자열과 접미사를 이용하여 출력 디렉토리 이름을 생성합니다.
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
     * @param inputfileString
     *            대상 파일 이름 문자열
     * @param suffix
     *            접미사
     * @return 출력 디렉토리 이름
     *
     * @throws NullPointerException
     *             파라미터({@code inputfileString})가 {@code null}인 경우 발생.
     *
     * @since 2012. 3. 13.
     */
    public static String toOutputDir(String inputfileString, String suffix) {
        Objects.requireNonNull(inputfileString);

        return inputfileString + "_" + new SimpleDateFormat("yyyyMMdd_hhmmss").format(new Date()) + $suffix(suffix);
    }

    /**
     * 파일 이름 문자열, 접두사, 접미사를 이용하여 출력 디렉토리 이름을 생성합니다.
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
     * @param inputfileString
     *            대상 파일 이름 문자열
     * @param prefix
     *            접두사
     * @param suffix
     *            접미사
     * @return 출력 디렉토리 이름
     *
     * @throws NullPointerException
     *             파라미터({@code inputfileString})가 {@code null}인 경우 발생.
     *
     * @since 2012. 3. 13.
     */
    public static String toOutputDir(String inputfileString, String prefix, String suffix) {
        Objects.requireNonNull(inputfileString);

        return $prefix(prefix) + inputfileString + "_" + new SimpleDateFormat("yyyyMMdd_hhmmss").format(new Date()) + $suffix(suffix);
    }

    /**
     * 파일을 이용하여 출력 디렉토리 이름을 생성합니다.
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
     * @param inputfile
     *            대상 파일
     * @return 출력 디렉토리 이름
     *
     * @throws NullPointerException
     *             파라미터({@code inputfile})가 {@code null}인 경우 발생.
     *
     * @since 2012. 3. 13.
     */
    public static String toOutputDirename(File inputfile) {
        Objects.requireNonNull(inputfile);

        return inputfile.getName() + "_" + new SimpleDateFormat("yyyyMMdd_hhmmss").format(new Date());
    }

    /**
     * 파일과 확장자를 이용하여 출력 파일 이름을 생성합니다.
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
     * @param inputfile
     *            대상 파일
     * @param ext
     *            확장자
     * @return 출력 파일 이름
     *
     * @throws NullPointerException
     *             파라미터({@code inputfile})가 {@code null}인 경우 발생.
     *
     * @since 2012. 3. 13.
     */
    public static String toOutputFilename(File inputfile, String ext) {
        Objects.requireNonNull(inputfile);

        return inputfile.getName() + "_" + new SimpleDateFormat("yyyyMMdd_hhmmss").format(new Date()) + "." + ext;
    }

    /**
     * 파일, 접미사, 확장자를 이용하여 출력 파일 이름을 생성합니다.
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
     *            대상 파일
     * @param suffix
     *            접미사
     * @param ext
     *            확장자
     * @return 출력 파일 이름
     *
     * @throws NullPointerException
     *             파라미터({@code file})가 {@code null}인 경우 발생.
     *
     * @since 2012. 3. 13.
     */
    public static String toOutputFilename(File file, String suffix, String ext) {
        Objects.requireNonNull(file);

        return file.getName() + "_" + new SimpleDateFormat("yyyyMMdd_hhmmss").format(new Date()) + $suffix(suffix) + "." + ext;
    }

    /**
     * 파일, 접두사, 접미사, 확장자를 이용하여 출력 파일 이름을 생성합니다.
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
     * @param inputfile
     *            대상 파일
     * @param prefix
     *            접두사
     * @param suffix
     *            접미사
     * @param ext
     *            확장자
     * @return 출력 파일 이름
     *
     * @throws NullPointerException
     *             파라미터({@code inputfile})가 {@code null}인 경우 발생.
     *
     * @since 2012. 3. 13.
     */
    public static String toOutputFilename(File inputfile, String prefix, String suffix, String ext) {
        Objects.requireNonNull(inputfile);

        return $prefix(prefix) + inputfile.getName() + "_" + new SimpleDateFormat("yyyyMMdd_hhmmss").format(new Date()) + $suffix(suffix) + "." + ext;
    }

    /**
     * 파일 이름 문자열과 확장자를 이용하여 출력 파일 이름을 생성합니다.
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
     * @param inputfileString
     *            대상 파일 이름 문자열
     * @param ext
     *            확장자
     * @return 출력 파일 이름
     *
     * @throws NullPointerException
     *             파라미터({@code inputfileString})가 {@code null}인 경우 발생.
     *
     * @since 2012. 3. 13.
     */
    public static String toOutputFilename(String inputfileString, String ext) {
        Objects.requireNonNull(inputfileString);

        return inputfileString + "_" + new SimpleDateFormat("yyyyMMdd_hhmmss").format(new Date()) + "." + ext;
    }

    /**
     * 파일 이름 문자열, 접미사, 확장자를 이용하여 출력 파일 이름을 생성합니다.
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
     * @param inputfileString
     *            대상 파일 이름 문자열
     * @param suffix
     *            접미사
     * @param ext
     *            확장자
     * @return 출력 파일 이름
     *
     * @throws NullPointerException
     *             파라미터({@code inputfileString})가 {@code null}인 경우 발생.
     *
     * @since 2012. 3. 13.
     */
    public static String toOutputFilename(String inputfileString, String suffix, String ext) {
        Objects.requireNonNull(inputfileString);

        return inputfileString + "_" + new SimpleDateFormat("yyyyMMdd_hhmmss").format(new Date()) + $suffix(suffix) + "." + ext;
    }

    /**
     * 파일 이름 문자열, 접두사, 접미사, 확장자를 이용하여 출력 파일 이름을 생성합니다.
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
     * @param inputfileString
     *            대상 파일 이름 문자열
     * @param prefix
     *            접두사
     * @param suffix
     *            접미사
     * @param ext
     *            확장자
     * @return 출력 파일 이름
     *
     * @throws NullPointerException
     *             파라미터({@code inputfileString})가 {@code null}인 경우 발생.
     *
     * @since 2012. 3. 13.
     */
    public static String toOutputFilename(String inputfileString, String prefix, String suffix, String ext) {
        Objects.requireNonNull(inputfileString);

        return $prefix(prefix) + inputfileString + "_" + new SimpleDateFormat("yyyyMMdd_hhmmss").format(new Date()) + $suffix(suffix) + "." + ext;
    }
}
