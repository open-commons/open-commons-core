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
 * This file is generated under this project, "open-commons-core".
 *
 * Date  : 2012. 11. 2.
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */
package open.commons.core.io;

import java.io.File;
import java.io.FileFilter;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.jspecify.annotations.Nullable;

import open.commons.core.concurrent.Mutex;
import open.commons.core.utils.ArrayUtils;

/**
 * 
 * 
 * @since 2012. 11. 02.
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */
public class FileRecursiveHandler {

    /** 기본 디렉토리 필터 */
    private static final FileFilter DEFAULT_DIR_FILTER = new FileFilter() {

        @Override
        public boolean accept(@Nullable File pathname) {
            return pathname == null ? false : pathname.isDirectory();
        }
    };

    /** 기본 파일 필터 */
    private static final FileFilter DEFAULT_FILE_FILTER = new FileFilter() {

        @Override
        public boolean accept(@Nullable File file) {
            return file == null ? false : file.isFile();
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
     * 최상위 위치 (절대경로)를 가지고 객체를 생성합니다.
     * 
     * @param rootpath
     *            검색을 위한 최상위 위치의 절대 경로 문자열
     * 
     *            <BR>
     * 
     * @since 2012. 01. 18.
     * @see #FileRecursiveHandler(String, FileFilter, FileFilter)
     */
    public FileRecursiveHandler(String rootpath) {
        this(rootpath, DEFAULT_FILE_FILTER, DEFAULT_DIR_FILTER);
    }

    /**
     * 절대 경로, 파일 필터, 디렉토리 필터를 가지고 객체를 생성합니다.
     * 
     * @param rootpath
     *            검색을 위한 최상위 위치의 절대 경로 문자열
     * @param filefilter
     *            하위 파일 검색을 위한 필터
     * @param dirfilter
     *            하위 디렉토리 검색을 위한 필터
     * 
     * @since 2012. 01. 18.
     */
    public FileRecursiveHandler(String rootpath, @Nullable FileFilter filefilter, @Nullable FileFilter dirfilter) {
        this.rootpath = rootpath;
        this.filefilter = filefilter == null ? DEFAULT_FILE_FILTER : filefilter;
        this.dirfilter = dirfilter == null ? DEFAULT_DIR_FILTER : dirfilter;
    }

    /**
     * @throws NullPointerException
     *             파라미터({@code file})가 {@code null}인 경우 발생.
     */
    protected final void $handle$(File file) {
        Objects.requireNonNull(file);

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
     * 파일 처리 핸들러를 추가합니다.
     * 
     * @param handle
     * @throws NullPointerException
     *             파라미터({@code handle})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 01. 18.
     */
    public void addFileHandler(IFileHandler handle) {
        Objects.requireNonNull(handle);

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
        return Objects.requireNonNull(
                // [PATCH[ JDK 표준 API의 JSpecify 미지원 우회용 임시 널 체크.
                // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 requireNonNull 래핑 제거.
                logpool.toString() //
        );
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
     * 최상위 절대경로에서부터 하위 구조를 탐색하면서 등록된 파일 처리 핸들러를 통해서 작업을 시작합니다.
     * 
     * <BR>
     * 
     * 
     * @since 2012. 01. 18.
     * 
     */
    public void handle() {
        if (filehandlers.size() > 0) {
            File root = new File(rootpath);
            $handle$(root);
        } else {
            throw new IllegalStateException("no file handler: " + filehandlers.size());
        }
    }

    /**
     * @param dir
     * 
     * @throws NullPointerException
     *             파라미터({@code dir})가 {@code null}인 경우 발생.
     * @since 2012. 03. 13.
     * 
     */
    protected final void handleDir(File dir) {
        Objects.requireNonNull(dir);

        countUpDir();

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
            if ($file != null) {
                handleFile($file);
            }
        }

        for (File $dir : subdirs) {
            if ($dir != null) {
                $handle$($dir);
            }
        }
    }

    /**
     * @throws NullPointerException
     *             파라미터({@code file})가 {@code null}인 경우 발생.
     */
    protected final void handleFile(File file) {
        Objects.requireNonNull(file);

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
                addErrorLog(ex.getLocalizedMessage() + ", file: " + file.getAbsolutePath());
            }
        }
    }

    /**
     * 등록되어 있는 모든 {@link IFileHandler}를 제거합니다.
     */
    public void removeAllHandlers() {
        filehandlers.clear();
    }

    /**
     * 등록되어 있던 파일 처리 핸드러를 제거합니다.
     * 
     * @param handle
     * 
     * @throws NullPointerException
     *             파라미터({@code handle})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 01. 18.
     */
    public void removeFileHandler(IFileHandler handle) {
        Objects.requireNonNull(handle);

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
     * 에러 내용을 모두 삭제합니다.
     * 
     * 
     * <BR>
     * 
     * 
     * @since 2012. 01. 18.
     */
    public final void resetErrorLog() {
        logpool.delete(0, logpool.length());
    }

    /**
     * 디렉토리 필터를 설정합니다.
     * 
     * @param dirfilter
     *            디렉토리 필터
     * 
     *            <BR>
     * 
     * @since 2012. 01. 18.
     */
    public void setDirFilter(@Nullable FileFilter dirfilter) {
        this.dirfilter = dirfilter == null ? DEFAULT_DIR_FILTER : dirfilter;
    }

    /**
     * 파일 필터를 설정합니다.
     * 
     * @param filefilter
     *            파일 필터s
     * 
     *            <BR>
     * 
     * @since 2012. 01. 18.
     */
    public void setFileFilter(@Nullable FileFilter filefilter) {
        this.filefilter = filefilter == null ? DEFAULT_FILE_FILTER : filefilter;
    }

    /**
     * 새로운 최상위 절대 경로를 설정한 후, 기존 경로를 반환합니다.<br>
     * 
     * @param rootpath
     * @return 기존 최상위 절대 경로
     * @throws NullPointerException
     *             파라미터({@code rootpath})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 01. 18.
     */
    public String setRootPath(String rootpath) {
        Objects.requireNonNull(rootpath);
        String tr = this.rootpath;
        this.rootpath = rootpath;

        return tr;
    }
}
