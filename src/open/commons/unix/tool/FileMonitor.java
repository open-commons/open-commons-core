/*
 * Copyright 2011 Park Jun-Hong_(fafanmama_at_naver_com)
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
* Date  : 2013. 5. 23. 오후 5:00:24
*
* Author: Park_Jun_Hong_(fafanmama_at_naver_com)
* 
*/

/**
 * 
 */
package open.commons.unix.tool;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent.Kind;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentSkipListSet;

import open.commons.concurrent.Mutex;
import open.commons.log.LogUtils;
import open.commons.utils.ArrayUtils;
import open.commons.utils.IOUtils;
import open.commons.utils.ThreadUtils;

/**
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */
public class FileMonitor implements IRunnable, IFileWatchListener, IFileModifyListener {

    public static final String ARGS_DIR = "-d";
    public static final String ARGS_FILE = "-f";
    public static final String ARGS_OUTPUT_FILES = "-o";
    public static final String ARGS_OUTPUT_CONSOLE = "-c";

    /** verbose all */
    public static final String ARGS_VA = "-v";
    /** verbose timestamp */
    public static final String ARGS_VT = "-vt";
    /** verbose filename */
    public static final String ARGS_VF = "-vf";
    /** verbose directory */
    public static final String ARGS_VD = "-vd";

    static final String CONFIG_DIRS = "config.directories";
    static final String CONFIG_FILES = "config.files";
    static final String CONFIG_OUTPUT_FILES = "config.output.files";
    static final String CONFIG_OUTPUT_CONSOLE = "config.output.console";
    static final String CONFIG_VERBOSE_ALL = "config.verbose.all";
    static final String CONFIG_VERBOSE_TIMESTAMP = "config.verbose.timestamp";
    static final String CONFIG_VERBOSE_FILENAME = "config.verbose.filename";
    static final String CONFIG_VERBOSE_DIRECTORY = "config.verbose.directory";

    private static final String HELP_MESSAGE;

    static {
        StringBuffer sb = new StringBuffer();

        sb.append("This is for logging files via java\n");
        sb.append("\n");
        sb.append("Usage\n");
        sb.append("[Java]\n");
        sb.append(" java -jar jtail.jar [option]\n");
        sb.append(" e.g. java -jar jtail.jar -d . -o c:\\log.txt -vf\n");
        sb.append("      java -jar jtail.jar -d . -o c:\\log.txt -vf\n");
        sb.append("\n");
        sb.append("[Wrapper]\n");
        sb.append(" jtail [option]\n");
        sb.append(" e.g. jtail -d . -o \"c:\\log.txt -vf\"\n");
        sb.append("      jtail -f \".\\log.txt\" . -o \"c:\\log.txt\" -vf\n");
        sb.append("\n");
        sb.append("[Requriemtns]\n");
        sb.append("JRE         : JRE 1.7 or higher\n");
        sb.append("\n");
        sb.append("[Option]\n");
        sb.append(" -d [value] : directories. Values are delimited by ");
        sb.append(FmConstants.DIR_DELIMITER_DESC);
        sb.append(".\n");
        sb.append("     format : directory");
        sb.append(FmConstants.DIR_OPT_DELIMITER);
        sb.append("{recursive}.\n");
        sb.append("              'recursive' is 0/1 and optional, default is 0(Zero). 0(Zero) means false, 1 true.\n");
        sb.append("              '/home/usr' is equal to '/home/usr");
        sb.append(FmConstants.DIR_OPT_DELIMITER);
        sb.append("0'.\n");
        sb.append("              ex) /usr/" + FmConstants.DIR_OPT_DELIMITER + "0" + FmConstants.DIR_DELIMITER + "/bin/");
        sb.append(FmConstants.DIR_OPT_DELIMITER + "1,/usr,/opt" + FmConstants.DIR_OPT_DELIMITER);
        sb.append("1\n");
        sb.append(" -f [value] : files. Values are delimited by " + FmConstants.FILE_DELIMITER_DESC);
        sb.append(".\n");
        sb.append("              ex) c:\\windows\\abc.txt" + FmConstants.FILE_DELIMITER);
        sb.append("d:\\myhome\\xyz.log\n");
        sb.append(" -o [value] : log files. Log files are delimited by " + FmConstants.LOG_DELIMITER_DESC);
        sb.append(". Default is System-out.\n");
        sb.append("              Directories of log files don't be contained in '-d' values.\n");
        sb.append("              It is to prevent infinite loop to log.\n");
        sb.append("              ex) c:\\log\\myapp_log.txt" + FmConstants.LOG_DELIMITER + "d:\\myapp\\monitor.log\n");
        sb.append(" -c         : Console output.\n");
        sb.append(" -v         : set all verbose option. (-vt, -vf, -vd)\n");
        sb.append(" -vt        : set enable timestamp verbose.\n");
        sb.append(" -vf        : set enable filename verbose.\n");
        sb.append("     format : -vf" + FmConstants.FILENAME_VERBOSE_DELIMITER + "{filename_length}\n");
        sb.append("              ex) -vf" + FmConstants.FILENAME_VERBOSE_DELIMITER + "20\n");
        sb.append("              Default is infinite.\n");
        sb.append(" -vd        : set enable directory verbose.\n");

        HELP_MESSAGE = sb.toString();
    }

    private final HashMap<String, Object> configs = new HashMap<String, Object>();

    private boolean verboseTimestamp = false;
    private boolean verboseFilename = false;
    static String vfLength = "";
    private boolean verboseDirectory = false;

    // -------------

    private Mutex dqMutex = new Mutex("DELETE-QUEUE");
    private ConcurrentLinkedQueue<String> deleteQueue = new ConcurrentLinkedQueue<String>();

    private Mutex mqMutex = new Mutex("MODIFY-QUEUE");
    private ConcurrentLinkedQueue<String> modifyQueue = new ConcurrentLinkedQueue<String>();

    private Mutex cqMutex = new Mutex("CREATE-QUEUE");
    private ConcurrentLinkedQueue<String> createQueue = new ConcurrentLinkedQueue<String>();

    private Mutex fpMutex = new Mutex("FILE_POINTS");
    private ConcurrentHashMap<String, Long> filePointers = new ConcurrentHashMap<String, Long>();

    private Mutex ctxMutex = new Mutex("CONTEXT-QUEUE");
    private ConcurrentLinkedQueue<FileContext> contextQueue = new ConcurrentLinkedQueue<FileContext>();

    private Mutex fcwMutex = new Mutex("FILE_CONTEXT_WRITER");
    private Set<IFileContextWriter> fcWriters = new HashSet<IFileContextWriter>();
    private IFileContextWriter defaultFcWriter;

    private Mutex wsMutex = new Mutex("WATCH_SERVICE");
    private ConcurrentHashMap<String, DirectoryWatchService> watchServices = new ConcurrentHashMap<String, DirectoryWatchService>();
    private ConcurrentHashMap<String, Boolean> wsRecursive = new ConcurrentHashMap<String, Boolean>();

    /** file modify handler */
    private FileModifyHandler fmHandler;
    /** file create handler */
    private FileCreateHandler fcHandler;
    /** file delete handler */
    private FileDeleteHandler fdHandler;

    private boolean running = true;

    public FileMonitor() {

    }

    /**
     * Add the new {@link IFileContextWriter} object.
     * 
     * @param writer
     *            writer to add.
     * 
     * @return whether to add or not.
     */
    public boolean addFileContextWriter(IFileContextWriter writer) {
        boolean added = false;
        if (writer != null) {
            synchronized (fcwMutex) {
                added = fcWriters.add(writer);
            }
        }

        return added;
    }

    /**
     * Add new {@link IFileContextWriter} objects.
     * 
     * @param writers
     *            writers to add.
     */
    public void addFileContextWriter(IFileContextWriter... writers) {
        for (IFileContextWriter writer : writers) {
            if (writer != null) {
                addFileContextWriter(writer);
            }
        }
    }

    /**
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(IFileModifyListener o) {
        return name().compareTo(o.name());
    }

    FileContext getContext() {
        FileContext context = null;
        while (true) {
            synchronized (ctxMutex) {
                context = contextQueue.poll();

                if (running && context == null) {
                    try {
                        ctxMutex.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    break;
                }
            }
        }

        return running ? context : null;
    }

    public boolean init(String[] args) {
        String arg = null;
        boolean ready = false;
        for (int i = 0; i <= args.length - 1;) {
            arg = args[i];

            // parse '-d'
            if (ARGS_DIR.equals(arg)) {
                setConfig(CONFIG_DIRS, args[i + 1]);

                ready = true;

                i += 2;

            } else
            // parse '-f'
            if (ARGS_FILE.equals(arg)) {
                setConfig(CONFIG_FILES, args[i + 1]);

                ready = true;

                i += 2;

            } else
            // parse '-o'
            if (ARGS_OUTPUT_FILES.equals(arg)) {
                setConfig(CONFIG_OUTPUT_FILES, args[i + 1]);

                i += 2;
            } else
            // parse '-c'
            if (ARGS_OUTPUT_CONSOLE.equals(arg)) {
                setConfig(CONFIG_OUTPUT_CONSOLE, true);

                i++;
            } else
            // parse -v
            if (ARGS_VA.equals(arg)) {
                setConfig(CONFIG_VERBOSE_ALL, true);

                i++;
            } else
            // parse -vt
            if (ARGS_VT.equals(arg)) {
                setConfig(CONFIG_VERBOSE_TIMESTAMP, true);

                i++;
            } else
            // parse -vf(:(\d)+)?
            if (arg.startsWith(ARGS_VF)) {
                String[] vf = arg.split(":");
                if (vf.length > 1) {
                    setConfig(CONFIG_VERBOSE_FILENAME, vf[1]);
                }

                i++;
            } else
            // parse -vd
            if (ARGS_VD.equals(arg)) {
                setConfig(CONFIG_VERBOSE_DIRECTORY, true);

                i++;
            } else {
                i++;
            }
        }

        return ready;
    }

    public boolean isVerboseDirectory() {
        return configs.get(CONFIG_VERBOSE_DIRECTORY) != null;
    }

    public boolean isVerboseFilename() {
        return configs.get(CONFIG_VERBOSE_FILENAME) != null;
    }

    public boolean isVerboseTimestamp() {
        return configs.get(CONFIG_VERBOSE_TIMESTAMP) != null;
    }

    /**
     * 
     * @see open.commons.unix.tool.IFileModifyListener#modify(FileContext)
     */
    @Override
    public void modify(FileContext context) {

        synchronized (ctxMutex) {
            contextQueue.add(context);

            ctxMutex.notifyAll();
        }
    }

    /**
     * 
     * @see open.commons.unix.tool.IFileModifyListener#name()
     */
    @Override
    public String name() {
        return toString();
    }

    /**
     * 
     * @param pathname
     * @param queue
     * @param mtx
     */
    private void queue(String pathname, ConcurrentLinkedQueue<String> queue, Mutex mtx) {

        synchronized (mtx) {
            queue.add(pathname);

            mtx.notifyAll();
        }
    }

    /**
     * Register files or files in directories.
     * 
     * @param files
     *            files or files in directories to register.
     * @throws IOException
     * 
     * @see {@link #register(File)}
     */
    public void register(File... files) throws IOException {
        for (File file : files) {
            register(file);
        }
    }

    /**
     * Register file or files in the directory.
     * 
     * @param file
     *            directory to register
     * @throws IOException
     * 
     * @see {@link #registerFile(File, boolean)}<br>
     *      {@link #registerDirectory(File)}
     */
    public void register(File file) throws IOException {

        if (file.isFile()) {
            registerFile(file, true);
        } else if (file.isDirectory()) {
            registerDirectory(file);
        }
    }

    /**
     * Register files or files in directories indicated by the arguments.
     * 
     * @param files
     *            files or files in directories to register.
     * @throws IOException
     */
    public void register(String... files) throws IOException {
        for (String file : files) {
            register(file);
        }
    }

    /**
     * Register file or files in a directory is the fully qualified path.
     * 
     * @param file
     *            fully qualified path to register.
     * @throws IOException
     * 
     * @see {@link #register(File)}
     */
    public void register(String file) throws IOException {
        register(new File(file));
    }

    /**
     * Register files in directories.
     * 
     * @param recursive
     *            whether sub-directories is registered or not.
     * @param directories
     *            directories to register.
     * @throws IOException
     * 
     * @see {@link #registerDirectory(File, boolean)}
     */
    public void registerDirectory(boolean recursive, File... directories) throws IOException {
        for (File dir : directories) {
            if (dir.isDirectory()) {
                registerDirectory(dir, recursive);
            }
        }
    }

    /**
     * Register files in the directory.
     * 
     * @param directory
     *            directory to contain files to register.
     * @throws IOException
     * @see {@link #registerDirectory(File, boolean)}
     */
    public void registerDirectory(File directory) throws IOException {
        registerDirectory(directory, false);
    }

    /**
     * Register directories.
     * 
     * @param directories
     *            directories to register
     * @throws IOException
     * 
     * @see {@link #registerDirectory(boolean, File...)}
     */
    public void registerDirectory(File... directories) throws IOException {
        for (File dir : directories) {
            if (dir.isDirectory()) {
                registerDirectory(dir);
            }
        }
    }

    /**
     * Register files in the directory.
     * 
     * @param directory
     *            directory to contain files to register.
     * @param recursive
     *            whether sub-directories is registered or not.
     * @throws IOException
     * 
     * @see {@link #register(File)}<br>
     *      {@link #registerDirectory(boolean, File...)}<br>
     *      {@link #registerFile(boolean, File...)}
     */
    public void registerDirectory(File directory, boolean recursive) throws IOException {

        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory + " is not a directory.");
        }

        // watch service
        DirectoryWatchService service = registerWatchService(directory.getCanonicalPath(), recursive, false);

        if (service.isDedicated()) {
            service.setDedicated(false);
        }

        final ArrayList<File> subFiles = new ArrayList<File>();
        final ArrayList<File> subDirs = new ArrayList<File>();

        directory.listFiles(new FileFilter() {

            @Override
            public boolean accept(File pathname) {
                if (pathname.isFile()) {
                    subFiles.add(pathname);
                } else if (pathname.isDirectory()) {
                    subDirs.add(pathname);
                }

                return false;
            }
        });

        registerFile(false, subFiles.toArray(new File[0]));

        if (recursive) {
            registerDirectory(true, subDirs.toArray(new File[0]));
        }
    }

    /**
     * 디렉토리에 포함된 파일을 감시한다.
     * 
     * @param dir
     *            등록할 디렉토리
     * @param recursive
     *            하위 디렉토리 포함 여부
     * @throws IOException
     * 
     * @see {@link #registerDirectory(File, boolean)}
     */
    public void registerDirectory(String dir, boolean recursive) throws IOException {
        registerDirectory(new File(dir).getCanonicalFile(), recursive);
    }

    /**
     * Register files. Allow not directory.
     * 
     * @param dedicated
     *            TODO
     * @param files
     *            to register.
     * 
     * @see {@link #register(File)}
     */
    public void registerFile(boolean dedicated, File... files) {
        for (File file : files) {
            if (file.isFile()) {
                registerFile(file, dedicated);
            }
        }
    }

    /**
     * Register files. Allow not a directory.
     * 
     * @param dedicated
     *            TODO
     * @param files
     *            to register.
     * 
     * @see {@link #register(File)}
     */
    public void registerFile(boolean dedicated, String... files) {
        for (String file : files) {
            registerFile(new File(file), dedicated);
        }
    }

    /**
     * Register file.
     * 
     * @param file
     *            file to register.
     * @param dedicated
     */
    public void registerFile(File file, boolean dedicated) {
        if (!file.isFile()) {
            throw new IllegalArgumentException(file + " is not a file.");
        }

        // register a watch service to directory contains this file.
        DirectoryWatchService service = registerWatchService(file.getParentFile().getAbsolutePath(), false, dedicated);
        if (service.isDedicated()) {
            service.addFiles(file.getAbsolutePath());
        }

        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(file, "r");

            synchronized (fpMutex) {
                filePointers.put(file.getAbsolutePath(), raf.length());
            }

            raf.close();
        } catch (Exception e) {
            System.err.println("(fail to regiser) file: " + file.getAbsolutePath() + ", cause: " + e.getMessage());
        } finally {
            IOUtils.close(raf);
        }
    }

    /**
     * Register a file. Allow not a directory.
     * 
     * @param file
     * @param dedicated
     *            TODO
     * @throws IOException
     * 
     * @see @ link #registerFile(File)}
     */
    public void registerFile(String file, boolean dedicated) throws IOException {
        registerFile(new File(file).getCanonicalFile(), dedicated);
    }

    /**
     * Register
     * 
     * @param path
     * @throws IOException
     * 
     * @see {@link #register(File)}
     */
    public void registerPath(Path path) throws IOException {
        register(path.toFile());
    }

    /**
     * 
     * @param directory
     *            <b><code>NOT nullable</code></b>.
     * @param recursive
     *            whether monitor sub-directories are current or newly or not.
     * @param dedicated
     *            whether monitor only one file or not.
     * @return
     * @return
     */
    private DirectoryWatchService registerWatchService(String directory, boolean recursive, boolean dedicated) {

        DirectoryWatchService service = null;

        synchronized (wsMutex) {
            service = watchServices.get(directory);

            if (service == null) {
                service = new DirectoryWatchService(this, directory, recursive);
                service.setDedicated(dedicated);

                watchServices.put(directory, service);
                wsRecursive.put(directory, recursive);

                if (service != null) {
                    new Thread(service).start();
                }

                LogUtils.log("(new 'Service')", "service: " + service + ", directory: " + directory + ", recursive: " + recursive);
            }
        }

        return service;
    }

    /**
     * Remove the {@link IFileContextWriter} objects.
     * 
     * @param writers
     *            writers to remove.
     */
    public void removeFileContextWriter(IFileContextWriter... writers) {
        for (IFileContextWriter writer : writers) {
            if (writer != null) {
                removeFileContextWriter(writer);
            }
        }
    }

    /**
     * Remove the {@link IFileContextWriter} object.
     * 
     * @param writer
     *            writer to remove.
     * 
     * @return whether to remove or not.
     */
    public boolean removeFileContextWriter(IFileContextWriter writer) {
        boolean removed = false;

        if (writer != null) {
            synchronized (fcwMutex) {
                removed = fcWriters.remove(writer);
            }
        }

        return removed;
    }

    /**
     * 
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {

        String otn = ThreadUtils.setThreadName(FileMonitor.class.getSimpleName() + "(Main)");

        // start to monitor file modify
        fmHandler = new FileModifyHandler();
        fmHandler.addFileModifyListener(this);
        // start to monitor file create.
        fcHandler = new FileCreateHandler();
        // monitor file delete.
        fdHandler = new FileDeleteHandler();

        ThreadUtils.start(true, fcHandler, fmHandler, fdHandler);

        while (running) {
            try {
                FileContext context = getContext();

                if (context == null) {
                    continue;
                }

                writeContext(context);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
            }
        }

        Thread.currentThread().setName(otn);
    }

    public void setConfig(String key, Object value) {
        if (key != null) {
            configs.put(key, value);
        }
    }

    private void setDirectories(String dirs) throws IOException {
        String[] dirArr = dirs.split("[" + FmConstants.DIR_DELIMITER + "]");

        String[] dirArgs = null;
        for (int i = 0; i < dirArr.length; i++) {
            dirArgs = dirArr[i].trim().split("[" + FmConstants.DIR_OPT_DELIMITER + "]");

            registerDirectory(dirArgs[0].trim() // directory
                    , dirArgs.length > 1 ? // check recursive
                            dirArgs[1].equals("0") ? false : true : false);
        }
    }

    private void setFiles(String files) throws IOException {
        String[] fileArr = files.split("[" + FmConstants.FILE_DELIMITER + "]");

        for (int i = 0; i < fileArr.length; i++) {
            registerFile(fileArr[i].trim(), true);
        }
    }

    private void setOutputs(String outputs) {
        String[] outputArr = outputs.split(",");

        IFileContextWriter fcWriter = null;
        File file = null;

        for (int i = 0; i < outputArr.length; i++) {
            file = new File(outputArr[i]);

            if (!file.exists()) {
                file.getParentFile().mkdirs();

                try {
                    file.createNewFile();
                } catch (IOException ignored) {
                }
            }

            if (file.exists()) {
                for (String wsKey : watchServices.keySet()) {
                    if (!file.getAbsolutePath().startsWith(wsKey)) {
                        fcWriter = new FileContextFileWriter(file, verboseTimestamp, verboseFilename, verboseDirectory);
                        addFileContextWriter(fcWriter);
                        break;
                    } else {
                        LogUtils.error("(Cause a looping) watch: " + wsKey + ", file: " + file.getAbsolutePath());
                    }
                }
            }
        }
    }

    public void start() throws IOException {
        boolean debugging = LogUtils.setEnabled(true);

        Object config = null;

        // set verbose all
        config = configs.get(CONFIG_VERBOSE_ALL);
        if (config != null) {
            verboseTimestamp = true;
            verboseFilename = true;
            verboseDirectory = true;
        }

        // set verbose timestamp
        config = configs.get(CONFIG_VERBOSE_TIMESTAMP);
        if (config != null) {
            verboseTimestamp = true;
        }

        // set verbose filename
        config = configs.get(CONFIG_VERBOSE_FILENAME);
        if (config != null) {
            verboseFilename = true;
            vfLength = (String) config;
        }

        // set verbose directory
        config = configs.get(CONFIG_VERBOSE_DIRECTORY);
        if (config != null) {
            verboseDirectory = true;

        }

        // set directories
        config = configs.get(CONFIG_DIRS);
        if (config != null) {
            setDirectories((String) config);
        }

        // set files
        config = configs.get(CONFIG_FILES);
        if (config != null) {
            setFiles((String) config);
        }

        // set outputs
        config = configs.get(CONFIG_OUTPUT_FILES);
        if (config != null) {
            setOutputs((String) config);

            config = configs.get(CONFIG_OUTPUT_CONSOLE);
            if (config != null) {
                addFileContextWriter(new FileContextConsoleWriter(verboseTimestamp, verboseFilename, verboseDirectory));
            }

        } else {
            defaultFcWriter = new FileContextConsoleWriter(verboseTimestamp, verboseFilename, verboseDirectory);
        }

        LogUtils.setEnabled(debugging);

        new Thread(this).start();
    }

    /**
     * 
     * @see open.commons.unix.tool.IRunnable#stop(String)
     */
    @Override
    public void stop(String pathname) {

        running = false;

        if (fmHandler != null) {
            fmHandler.stop(null);
        }
        if (fcHandler != null) {
            fcHandler.stop(null);
        }
        if (fdHandler != null) {
            fdHandler.stop(null);
        }

        for (DirectoryWatchService dws : watchServices.values()) {
            dws.stop(pathname);
        }

        for (IFileContextWriter fcWriter : fcWriters) {
            try {
                fcWriter.close();
            } catch (IOException ignored) {
            }
        }

        synchronized (ctxMutex) {
            ctxMutex.notifyAll();
        }
    }

    /**
     * Unregister file or files in the directory.
     * 
     * @param file
     *            directory to unregister
     * 
     * @throws FileNotFoundException
     * 
     * @see {@link #unregisterFile(File)}<br>
     *      {@link #unregisterDirectory(File)}
     */
    public void unregister(File file) throws FileNotFoundException {

        if (file.isFile()) {
            unregisterFile(file);
        } else {
            unregisterDirectory(file);
        }
    }

    /**
     * Unregister file or files in a directory is the fully qualified path.
     * 
     * @param file
     *            fully qualified path to unregister.
     * 
     * @throws FileNotFoundException
     * 
     * @see {@link #unregister(File)}
     */
    public void unregister(String file) throws FileNotFoundException {
        unregister(new File(file));
    }

    /**
     * Unregister files in directories.
     * 
     * @param recursive
     *            whether sub-directories is unregistered or not.
     * @param directories
     *            directories to unregister.
     * 
     * @throws FileNotFoundException
     * 
     * @see {@link #unregisterDirectory(File, boolean)}
     */
    public void unregisterDirectory(boolean recursive, File... directories) throws FileNotFoundException {
        for (File dir : directories) {
            if (dir.isDirectory()) {
                unregisterDirectory(dir, recursive);
            }
        }
    }

    /**
     * Unregister files in the directory.
     * 
     * @param directory
     *            directory to contain files to unregister.
     * @throws FileNotFoundException
     */
    public void unregisterDirectory(File directory) throws FileNotFoundException {
        unregisterDirectory(directory, false);
    }

    /**
     * Unregister directories.
     * 
     * @param directories
     *            directories to unregister
     * 
     * @throws FileNotFoundException
     * 
     * @see {@link #unregisterDirectory(boolean, File...)}
     */
    public void unregisterDirectory(File... directories) throws FileNotFoundException {
        for (File dir : directories) {
            unregisterDirectory(dir);
        }
    }

    /**
     * Unregister files in the directory.
     * 
     * @param directory
     *            directory to contain files to unregister.
     * @param recursive
     *            whether sub-directories is unregistered or not.
     * 
     * @throws FileNotFoundException
     * 
     * @see {@link #unregister(File)}<br>
     *      {@link #unregisterDirectory(boolean, File...)}<br>
     *      {@link #unregisterFile(File...)}
     */
    public void unregisterDirectory(File directory, boolean recursive) throws FileNotFoundException {

        if (directory.isFile()) {
            throw new IllegalArgumentException(directory + " is not a directory.");
        }

        // watch servicie
        unregisterWatchService(directory.getAbsolutePath());

        final ArrayList<File> subFiles = new ArrayList<File>();
        final ArrayList<File> subDirs = new ArrayList<File>();

        directory.listFiles(new FileFilter() {

            @Override
            public boolean accept(File pathname) {
                if (pathname.isFile()) {
                    subFiles.add(pathname);
                } else {
                    subDirs.add(pathname);
                }

                return false;
            }
        });

        unregisterFile(subFiles.toArray(new File[0]));

        if (recursive) {
            unregisterDirectory(true, subDirs.toArray(new File[0]));
        }
    }

    /**
     * 디렉토리에 포함된 파일을 감시한다.
     * 
     * @param dir
     *            등록할 디렉토리
     * @param recursive
     *            하위 디렉토리 포함 여부
     * 
     * @throws FileNotFoundException
     * 
     * @see {@link #unregisterDirectory(File, boolean)}
     */
    public void unregisterDirectory(String dir, boolean recursive) throws FileNotFoundException {
        unregisterDirectory(new File(dir), recursive);
    }

    private void unregisterDirectory0(String directory) {

        unregisterWatchService(directory);

        synchronized (fpMutex) {
            for (String fp : filePointers.keySet()) {
                if (fp.startsWith(directory)) {
                    filePointers.remove(directory);
                }
            }
        }
    }

    /**
     * Unregister files. Allow not directory.
     * 
     * @param files
     *            to unregister.
     * 
     * @see {@link #unregister(File)}
     */
    public void unregisterFile(File... files) {
        for (File file : files) {
            if (file.isFile()) {
                unregisterFile(file);
            }
        }
    }

    /**
     * Unregister file.
     * 
     * @param file
     *            file to unregister.
     */
    public void unregisterFile(File file) {
        if (file.isDirectory()) {
            throw new IllegalArgumentException(file + " is not a file.");
        }

        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(file, "r");

            synchronized (fpMutex) {
                filePointers.remove(file.getAbsolutePath());
            }

            raf.close();
        } catch (Exception e) {
            System.err.println("(fail to regiser) file: " + file.getAbsolutePath() + ", cause: " + e.getMessage());
        } finally {
            IOUtils.close(raf);
        }
    }

    /**
     * Unregister
     * 
     * @param path
     * 
     * @throws FileNotFoundException
     * 
     * @see {@link #unregister(File)}
     */
    public void unregisterPath(Path path) throws FileNotFoundException {
        unregister(path.toFile());
    }

    /**
     * 
     * @param directory
     *            <b><code>NOT nullable</code></b>.
     */
    void unregisterWatchService(String directory) {

        DirectoryWatchService service = null;

        synchronized (wsMutex) {
            service = watchServices.remove(directory);
        }

        if (service != null) {
            wsRecursive.remove(directory);

            service.stop(null);

            LogUtils.log("(unregister 'Service')", "service: " + service.toString() + ", directory: " + directory);
        }
    }

    /**
     * 
     * @see open.commons.unix.tool.IFileWatchListener#watch(String, java.nio.file.WatchEvent.Kind)
     */
    @Override
    public void watch(String pathname, Kind<?> kind) {

        if (StandardWatchEventKinds.ENTRY_CREATE.equals(kind)) {
            queue(pathname, createQueue, cqMutex);
        } else if (StandardWatchEventKinds.ENTRY_MODIFY.equals(kind)) {
            queue(pathname, modifyQueue, mqMutex);
        } else if (StandardWatchEventKinds.ENTRY_DELETE.equals(kind)) {
            queue(pathname, deleteQueue, dqMutex);
        }
    }

    private void writeContext(FileContext context) {

        synchronized (fcwMutex) {
            if (fcWriters.size() < 1) {
                try {
                    defaultFcWriter.write(context);
                } catch (IOException ignored) {
                }
            } else {
                for (IFileContextWriter writer : fcWriters) {
                    try {
                        writer.write(context);
                    } catch (IOException ignored) {
                    }
                }
            }
        }
    }

    /**
     * <pre>
     * -d : directories. Delimited by comma(,).
     *      format: directory:{recursive}.
     *              recursive is 0/1 and optional, default is 0(Zero). 0(Zero) means false, 1 true.
     *              '/home/usr' is equal to '/home/usr;0'.
     *                
     *      ex) /usr/;0,/bin/;1,/usr,/opt;1
     *      
     * -f : files. Delimited by comma(,).
     * 
     * -o : log files. Delimited by comma(,). Default is System-out.
     * -v : set all verbose option.
     *     -vt, -vf, -vd
     * -vt: set enable timestamp verbose.
     * -vf: set enable filename verbose.     
     * -vd: set enable directory verbose.
     * </pre>
     * 
     * <pre>
     * java -jar FileMonintor.jar -d /usr:0,/opt -f  -o
     * 
     * </pre>
     * 
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {

        try {
            FileMonitor fileMonitor = new FileMonitor();

            boolean ready = fileMonitor.init(args);

            if (ready) {
                fileMonitor.start();
            } else {
                showHelp();
            }

        } catch (Exception e) {
            e.printStackTrace();
            showHelp();
            System.exit(1);
        }
    }

    public static void showHelp() {
        System.out.println(HELP_MESSAGE);
    }

    /**
     * File Create Handler
     * 
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * 
     */
    class FileCreateHandler implements IRunnable {

        boolean running = true;

        String getPathname() {
            String pathname = null;

            while (true) {
                synchronized (cqMutex) {
                    pathname = createQueue.poll();

                    if (running && pathname == null) {
                        try {
                            cqMutex.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        break;
                    }
                }
            }

            return running ? pathname : null;
        }

        /**
         * 
         * @see java.lang.Runnable#run()
         */
        @Override
        public void run() {

            String otn = ThreadUtils.setThreadName(FileCreateHandler.class.getSimpleName());

            String pathname = null;

            while (running) {
                pathname = getPathname();

                if (pathname == null) {
                    break;
                }

                File file = new File(pathname);
                if (file.isFile()) {
                    registerFile(file, false);
                } else if (file.isDirectory()) {
                    try {
                        Boolean recursive = null;
                        synchronized (wsMutex) {
                            recursive = wsRecursive.get(file.getParent());
                        }
                        registerDirectory(file, recursive != null ? recursive : false);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }

            Thread.currentThread().setName(otn);
        }

        /**
         * 
         * @see open.commons.unix.tool.IRunnable#stop(String)
         */
        @Override
        public void stop(String pathname) {
            running = false;

            synchronized (cqMutex) {
                cqMutex.notifyAll();
            }
        }
    }

    /**
     * File Delete Handler
     * 
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * 
     */
    class FileDeleteHandler implements IRunnable {

        boolean running = true;

        String getPathname() {
            String pathname = null;

            while (true) {
                synchronized (dqMutex) {
                    pathname = deleteQueue.poll();

                    if (running && pathname == null) {
                        try {
                            dqMutex.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        break;
                    }
                }
            }

            return running ? pathname : null;
        }

        /**
         * 
         * @see java.lang.Runnable#run()
         */
        @Override
        public void run() {

            String otn = ThreadUtils.setThreadName(FileDeleteHandler.class.getSimpleName());

            String pathname = null;

            while (running) {
                pathname = getPathname();

                if (pathname == null) {
                    break;
                }

                PathType deleteType = PathType.UNPREDICTIBLE;

                synchronized (fpMutex) {
                    synchronized (wsMutex) {
                        deleteType = watchServices.containsKey(pathname) ? PathType.DIRECTORY //
                                : filePointers.containsKey(pathname) ? PathType.FILE //
                                        : PathType.UNPREDICTIBLE;
                    }
                }

                switch (deleteType) {
                    case FILE:
                        synchronized (fpMutex) {
                            filePointers.remove(pathname);
                        }
                        break;
                    case DIRECTORY:
                        unregisterDirectory0(pathname);
                        break;
                    case UNPREDICTIBLE:
                        break;
                }
            }

            Thread.currentThread().setName(otn);
        }

        /**
         * 
         * @see open.commons.unix.tool.IRunnable#stop(String)
         */
        @Override
        public void stop(String pathname) {
            running = false;

            synchronized (dqMutex) {
                dqMutex.notifyAll();
            }
        }
    }

    /**
     * File Modify Handler
     * 
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * 
     */
    class FileModifyHandler implements IRunnable {

        final int BUF_SIZE = 0xFF;

        ConcurrentSkipListSet<IFileModifyListener> listeners = new ConcurrentSkipListSet<IFileModifyListener>();

        boolean running = true;

        void addFileModifyListener(IFileModifyListener l) {
            if (l != null) {
                listeners.add(l);
            }
        }

        String getPathname() {
            String pathname = null;

            while (true) {
                synchronized (mqMutex) {
                    pathname = modifyQueue.poll();

                    if (running && pathname == null) {
                        try {
                            mqMutex.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        break;
                    }
                }
            }

            return running ? pathname : null;
        }

        /**
         * 
         * @see java.lang.Runnable#run()
         */
        @Override
        public void run() {

            String otn = ThreadUtils.setThreadName(FileModifyHandler.class.getSimpleName());

            String pathname = null;
            Long fp = null;

            RandomAccessFile raf = null;
            byte[] data = null;
            byte[] readBuf = new byte[BUF_SIZE];

            int l = -1;
            long fl = -1;

            while (running) {
                pathname = getPathname();

                if (pathname == null) {
                    break;
                }

                PathType modifyType = PathType.UNPREDICTIBLE;

                synchronized (fpMutex) {
                    synchronized (wsMutex) {
                        modifyType = !new File(pathname).exists() ? PathType.UNPREDICTIBLE
                                : watchServices.containsKey(pathname) ? PathType.DIRECTORY //
                                        : filePointers.containsKey(pathname) ? PathType.FILE //
                                                : PathType.UNPREDICTIBLE;
                    }
                }

                switch (modifyType) {
                    case FILE:
                    case DIRECTORY:
                        // get a file-pointer
                        synchronized (fpMutex) {
                            fp = filePointers.get(pathname);
                        }

                        // if no exits file
                        if (fp == null) {
                            continue;
                        }

                        try {
                            raf = new RandomAccessFile(pathname, "r");

                            // if a length of file decreased.
                            if ((fl = raf.length()) < fp) {
                                filePointers.put(pathname, fl);
                                continue;
                            } else {
                                raf.seek(fp);
                            }

                            // read data
                            while ((l = raf.read(readBuf)) != -1) {
                                data = ArrayUtils.merge(data, readBuf, l);
                            }

                            if (data != null) {
                                // handle data
                                for (IFileModifyListener listener : listeners) {
                                    listener.modify(new FileContext(pathname, data));
                                }
                            }

                            // assign new file-pointer
                            synchronized (fpMutex) {
                                filePointers.put(pathname, fl);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            IOUtils.close(raf);
                        }
                        break;

                    case UNPREDICTIBLE:
                        break;
                }

                data = null;
            }

            Thread.currentThread().setName(otn);
        }

        /**
         * 
         * @see open.commons.unix.tool.IRunnable#stop(String)
         */
        @Override
        public void stop(String pathname) {
            running = false;

            synchronized (mqMutex) {
                mqMutex.notifyAll();
            }
        }
    }

    enum PathType {
        /** none */
        UNPREDICTIBLE,
        /**
         * Indicates a file.
         * 
         * @see File#isFile()
         */
        FILE,
        /**
         * Indicates a directory.
         * 
         * @see File#isDirectory()
         */
        DIRECTORY,;
    }
}