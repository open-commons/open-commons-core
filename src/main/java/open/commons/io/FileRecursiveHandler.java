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
* @title CommonUtils
* @since 2011. 09. 29.
*/
package open.commons.io;

import java.io.File;
import java.io.FileFilter;
import java.util.HashSet;
import java.util.Set;

import open.commons.concurrent.Mutex;
import open.commons.log.LogUtils;
import open.commons.utils.ArrayUtils;

/**
 * 
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 * @since 2012. 11. 02.
 * 
 */
public class FileRecursiveHandler {

    /** 기본 디렉토리 필터 */
    private static final FileFilter DEFAULT_DIR_FILTER = new FileFilter() {

        @Override
        public boolean accept(File pathname) {
            return pathname.isDirectory();
        }
    };

    /** 기본 파일 필터 */
    private static final FileFilter DEFAULT_FILE_FILTER = new FileFilter() {

        @Override
        public boolean accept(File file) {
            return file.isFile();
        }
    };

    private FileFilter dirfilter;

    private FileFilter filefilter;

    private final Set<IFileHandler> filehandlers = new HashSet<IFileHandler>();

    private final StringBuffer logpool = new StringBuffer();

    private String rootpath;

    private long fileCount;
    private Mutex mutexFileCount = new Mutex("mutex for 'FileCount'");
    private long dirCount;
    private Mutex mutexDirCount = new Mutex("mutex for 'Directory Count'");

    /**
     * 최상위 위치 (절대경로)를 가지고 객체를 생성한다.
     * 
     * @param rootpath
     *            검색을 위한 최상위 위치의 절대 경로 문자열
     * 
     *            <BR>
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * @since 2012. 01. 18.
     * @see #FileRecursiveHandler(String, FileFilter, FileFilter)
     */
    public FileRecursiveHandler(String rootpath) {
        this(rootpath, DEFAULT_FILE_FILTER, DEFAULT_DIR_FILTER);
    }

    /**
     * 절대 경로, 파일 필터, 디렉토리 필터를 가지고 객체를 생성한다.
     * 
     * @param rootpath
     *            검색을 위한 최상위 위치의 절대 경로 문자열
     * @param filefilter
     *            하위 파일 검색을 위한 필터
     * @param dirfilter
     *            하위 디렉토리 검색을 위한 필터
     * 
     *            <BR>
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * @since 2012. 01. 18.
     */
    public FileRecursiveHandler(String rootpath, FileFilter filefilter, FileFilter dirfilter) {
        this.rootpath = rootpath;
        this.filefilter = filefilter == null ? DEFAULT_FILE_FILTER : filefilter;
        this.dirfilter = dirfilter == null ? DEFAULT_DIR_FILTER : dirfilter;
    }

    protected final void $handle$(File file) {
        if (file.isFile()) {
            handleFile(file);
        } else {
            handleDir(file);
        }
    }

    private void addErrorLog(String log) {
        logpool.append(log + "\n");
    }

    /**
     * 파일 처리 핸들러를 추가한다.
     * 
     * @param handle
     * 
     *            <BR>
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * @since 2012. 01. 18.
     */
    public void addFileHandler(IFileHandler handle) {
        filehandlers.add(handle);
    }

    private void countUpDir() {
        synchronized (mutexDirCount) {
            dirCount++;
        }
    }

    private void countUpFile() {
        synchronized (mutexFileCount) {
            fileCount++;
        }
    }

    public final String errorLog() {
        return logpool.toString();
    }

    public long getDirCount() {
        synchronized (mutexDirCount) {
            return dirCount;
        }
    }

    public long getFileCount() {
        synchronized (mutexFileCount) {
            return fileCount;
        }
    }

    public String getRootpath() {
        return rootpath;
    }

    /**
     * 최상위 절대경로에서부터 하위 구조를 탐색하면서 등록된 파일 처리 핸들러를 통해서 작업을 시작한다.
     * 
     * <BR>
     * 
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * @since 2012. 01. 18.
     * 
     */
    public void handle() {
        if (rootpath != null) {
            if (filehandlers.size() > 0) {
                File root = new File(rootpath);
                $handle$(root);
            } else {
                throw new IllegalStateException("no file handler: " + filehandlers.size());
            }

        } else {
            throw new NullPointerException("rootpath: " + rootpath);
        }
    }

    /**
     * @param dir
     *            <BR>
     * @since 2012. 03. 13.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    protected final void handleDir(File dir) {
        countUpDir();

        // File[] subfiles = dir.listFiles(filefilter);
        // File[] subdirs = dir.listFiles(dirfilter);

        /**
         * 성능 향상. 아래는 성능 테스트 결과
         * 
         * <pre>
         * >>> begin: TEST_MAIN.test(...).new Runnable() {...}.run: copyOf
         * <<< end  : TEST_MAIN.test(...).new Runnable() {...}.run: copyOf
         * >>> begin: TEST_MAIN.test(...).new Runnable() {...}.run: listFiles
         * <<< end  : TEST_MAIN.test(...).new Runnable() {...}.run: listFiles
         * [RESULT] tested Count: 1,    copyOf: 278,012 ns
         * [RESULT] tested Count: 1, listFiles: 342,831 ns
         * ------------------------------------------------
         * >>> begin: TEST_MAIN.test(...).new Runnable() {...}.run: copyOf
         * <<< end  : TEST_MAIN.test(...).new Runnable() {...}.run: copyOf
         * >>> begin: TEST_MAIN.test(...).new Runnable() {...}.run: listFiles
         * <<< end  : TEST_MAIN.test(...).new Runnable() {...}.run: listFiles
         * [RESULT] tested Count: 10,    copyOf: 1,685,276 ns
         * [RESULT] tested Count: 10, listFiles: 2,516,240 ns
         * ------------------------------------------------
         * >>> begin: TEST_MAIN.test(...).new Runnable() {...}.run: copyOf
         * <<< end  : TEST_MAIN.test(...).new Runnable() {...}.run: copyOf
         * >>> begin: TEST_MAIN.test(...).new Runnable() {...}.run: listFiles
         * <<< end  : TEST_MAIN.test(...).new Runnable() {...}.run: listFiles
         * [RESULT] tested Count: 100,    copyOf: 15,721,666 ns
         * [RESULT] tested Count: 100, listFiles: 22,459,084 ns
         * ------------------------------------------------
         * >>> begin: TEST_MAIN.test(...).new Runnable() {...}.run: copyOf
         * <<< end  : TEST_MAIN.test(...).new Runnable() {...}.run: copyOf
         * >>> begin: TEST_MAIN.test(...).new Runnable() {...}.run: listFiles
         * <<< end  : TEST_MAIN.test(...).new Runnable() {...}.run: listFiles
         * [RESULT] tested Count: 1,000,    copyOf: 128,987,516 ns
         * [RESULT] tested Count: 1,000, listFiles: 189,525,668 ns
         * ------------------------------------------------
         * </pre>
         */
        File[] readfiles = dir.listFiles();

        File[] subfiles = new File[readfiles.length];
        File[] subdirs = new File[readfiles.length];

        int i = 0, j = 0;
        for (File f : readfiles) {
            if (f.isFile() && filefilter.accept(f)) {
                subfiles[i++] = f;
            } else if (dirfilter.accept(f)) {
                subdirs[j++] = f;
            }
        }

        subfiles = ArrayUtils.copyOf(subfiles, i);
        subdirs = ArrayUtils.copyOf(subdirs, j);

        for (File $file : subfiles) {
            handleFile($file);
        }

        for (File $dir : subdirs) {
            $handle$($dir);
        }
    }

    protected final void handleFile(File file) {
        countUpFile();

        String fn = file.getName();

        String fnp = null;
        for (IFileHandler handle : filehandlers) {
            try {
                fnp = handle.getFilenamePattern();
                if (fnp == null || fn.matches(handle.getFilenamePattern())) {
                    handle.handleFile(file);
                }
            } catch (Exception ex) {
                addErrorLog(ex.getLocalizedMessage() + ", file: " + file.getAbsolutePath() + "\n" + LogUtils.logCallStack());
            }
        }
    }

    /**
     * 등록되어 있는 모든 {@link IFileHandler}를 제거한다.
     */
    public void removeAllHandlers() {
        filehandlers.clear();
    }

    /**
     * 등록되어 있던 파일 처리 핸드러를 제거한다.
     * 
     * @param handle
     * 
     *            <BR>
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * @since 2012. 01. 18.
     */
    public void removeFileHandler(IFileHandler handle) {
        filehandlers.remove(handle);
    }

    public void resetCount() {
        synchronized (mutexFileCount) {
            this.fileCount = 0;
        }

        synchronized (mutexDirCount) {
            this.dirCount = 0;
        }
    }

    /**
     * 에러 내용을 모두 삭제한다.
     * 
     * 
     * <BR>
     * 
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * @since 2012. 01. 18.
     */
    public final void resetErrorLog() {
        logpool.delete(0, logpool.length());
    }

    /**
     * 디렉토리 필터를 설정한다.
     * 
     * @param dirfilter
     *            디렉토리 필터
     * 
     *            <BR>
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * @since 2012. 01. 18.
     */
    public void setDirFilter(FileFilter dirfilter) {
        this.dirfilter = dirfilter == null ? DEFAULT_DIR_FILTER : dirfilter;
    }

    /**
     * 파일 필터를 설정한다.
     * 
     * @param filefilter
     *            파일 필터s
     * 
     *            <BR>
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * @since 2012. 01. 18.
     */
    public void setFileFilter(FileFilter filefilter) {
        this.filefilter = filefilter == null ? DEFAULT_FILE_FILTER : filefilter;
    }

    /**
     * 새로운 최상위 절대 경로를 설정한 후, 기존 경로를 반환한다.<br>
     * 
     * @param rootpath
     * @return 기존 최상위 절대 경로
     * @throws IllegalArgumentException
     *             - <code>rootpath</code> 가 <code>null</code>인 경우
     * 
     *             <BR>
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * @since 2012. 01. 18.
     */
    public String setRootPath(String rootpath) {
        if (rootpath != null) {
            String tr = this.rootpath;
            this.rootpath = rootpath;

            return tr;
        } else {
            throw new IllegalArgumentException("rootpaht must not be null: rootpath=" + rootpath);
        }
    }
}
