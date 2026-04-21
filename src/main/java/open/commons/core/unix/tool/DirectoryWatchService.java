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
* This file is generated under this project, "open-commons-core".
*
* Date  : 2013. 5. 24. 오전 11:51:19
*
* Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
* 
*/

/**
 */
package open.commons.core.unix.tool;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.HashSet;
import java.util.Objects;

import org.jspecify.annotations.Nullable;

import open.commons.core.concurrent.Mutex;
import open.commons.core.utils.AssertUtils2;
import open.commons.core.utils.FileUtils;
import open.commons.core.utils.IOUtils;
import open.commons.core.utils.ThreadUtils;

/**
 * 
 * @since 2013. 5. 24.
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
public class DirectoryWatchService implements IRunnable {

    private FileMonitor fileMonitor;

    private String directory;

    private boolean running = true;

    private @Nullable WatchService watchService;

    private boolean recursive = false;

    private Mutex filesMutex = new Mutex("WATCH_SERVICE-FILES");
    private HashSet<String> files = new HashSet<String>();

    private boolean dedicated = false;

    /**
     * 
     * @param fileMonitor
     * @param directory
     *            must be <b>{@code ABSOLUTE PATHNAME}</b>
     * @param resurvice
     */
    public DirectoryWatchService(FileMonitor fileMonitor, String directory, boolean resurvice) {
        AssertUtils2.notNulls(fileMonitor, directory);

        this.fileMonitor = fileMonitor;
        this.directory = directory;
        this.recursive = resurvice;
    }

    /**
     * Add a files to monitor
     * 
     * @param files
     *            files to monitor
     */
    // 아래 내용에 적용됨.
    // - AssertUtils2.notNulls((Object[]) files);
    // [PATCH] 배열 공변성/가변성에 대한 IDE 분석기의 오탐 우회
    // [TODO] 향후 IDE의 배열 데이터 흐름 분석이 고도화되거나 JSpecify가 완벽히 지원되면 '제거'
    @SuppressWarnings("null")
    public void addFiles(String... files) {
        AssertUtils2.notNulls((Object[]) files);

        synchronized (filesMutex) {
            if (dedicated) {
                for (String file : files) {
                    if (file != null) {
                        this.files.add(file);
                    }
                }
            }
        }
    }

    public int getFileSize() {
        synchronized (filesMutex) {
            return files.size();
        }
    }

    /**
     * Return whether monitor only one file or not.
     * 
     * @return
     */
    public boolean isDedicated() {
        return dedicated;
    }

    public boolean isRecursive() {
        return recursive;
    }

    /**
     * 
     * @see java.lang.Runnable#run()
     */
    @SuppressWarnings("null")
    @Override
    public void run() {

        String otn = null;
        Path path = null;

        try {

            otn = ThreadUtils.setThreadName(FileUtils.getFileName(directory));

            // create a WatcherService object for monitoring directories
            this.watchService = FileSystems.getDefault().newWatchService();

            Objects.requireNonNull(this.watchService, "'Watch Service'는 반드시 설정되어야 합니다.");

            // getting the path object for the directory given by users
            path = Paths.get(directory);

            // register the events to be notified by the program
            path.register(this.watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_MODIFY,
                    StandardWatchEventKinds.ENTRY_DELETE);

            // LogStringContainer log = LogStringFactory.getContainer(10, 5, 0,
            // "\n");

            // infinite loop will take events
            while (running) {

                WatchKey key = this.watchService.take();

                for (WatchEvent<?> watchEvent : key.pollEvents()) {

                    // getting the type of the event
                    WatchEvent.Kind<?> kind = watchEvent.kind();

                    // getting the file name from the WatchEvent object
                    Path file = (Path) watchEvent.context();

                    File realfile = path.resolve(file).toFile();

                    boolean monitor = true;

                    if (StandardWatchEventKinds.ENTRY_CREATE.equals(kind) //
                            || StandardWatchEventKinds.ENTRY_MODIFY.equals(kind)) {

                        // whether monitor a new directory or not
                        if (realfile.isDirectory()) {
                            monitor = recursive;
                        } else
                        // whether monitor a new file or not
                        if (dedicated && realfile.isFile()) {
                            synchronized (filesMutex) {
                                monitor = files.contains(realfile.toString());
                            }
                        }
                    } else if (StandardWatchEventKinds.ENTRY_DELETE.equals(kind)) {
                        if (dedicated) {
                            synchronized (filesMutex) {
                                monitor = files.contains(realfile.toString());
                            }
                        }
                    }

                    if (monitor) {
                        fileMonitor.watch(path.resolve(file).toString(), kind);
                    }
                }

                // reset the current event and wait for other events
                key.reset();

            }
        } catch (Exception ignored) {
            if (running) {
                fileMonitor.unregisterWatchService(directory);
            }

            IOUtils.close(this.watchService);
        }

        files.clear();

        Thread.currentThread().setName(otn);
    }

    public void setDedicated(boolean dedicated) {
        synchronized (filesMutex) {
            if (files.size() > 1 && dedicated) {
                files.clear();
            }
        }

        this.dedicated = dedicated;
    }

    public boolean setRecursive(boolean recursive) {
        boolean old = recursive;

        this.recursive = recursive;

        return old;
    }

    /**
     * 
     * @see open.commons.core.unix.tool.IRunnable#stop(String)
     */
    @SuppressWarnings("null")
    @Override
    public void stop(@Nullable String pathname) {

        if (running) {

            running = false;

            synchronized (this.watchService) {

                try {
                    this.watchService.close();
                    this.watchService.notifyAll();

                } catch (IOException ignored) {
                    ignored.printStackTrace();
                }
            }
        }
    }
}
