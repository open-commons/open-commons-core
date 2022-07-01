/*
 * Copyright 2022 Park Jun-Hong_(parkjunhong77@gmail.com)
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
 * Date  : 2022. 5. 13. 오후 7:31:24
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package open.commons.core.log4j.appender;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.zip.Deflater;

import org.apache.logging.log4j.ThreadContext;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Core;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractOutputStreamAppender;
import org.apache.logging.log4j.core.appender.rolling.DefaultRolloverStrategy;
import org.apache.logging.log4j.core.appender.rolling.DirectFileRolloverStrategy;
import org.apache.logging.log4j.core.appender.rolling.DirectWriteRolloverStrategy;
import org.apache.logging.log4j.core.appender.rolling.RollingFileManager;
import org.apache.logging.log4j.core.appender.rolling.RolloverStrategy;
import org.apache.logging.log4j.core.appender.rolling.TriggeringPolicy;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderFactory;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;
import org.apache.logging.log4j.core.net.Advertiser;
import org.apache.logging.log4j.core.util.Booleans;
import org.apache.logging.log4j.core.util.Integers;

/**
 * 동일한 실행파일의 프로세스별로 서로 다른 로그파일을 생성할 수 있도록 지원하는 클래스.<br>
 * <p>
 * logj42 설정 파일에 customer appender 경로를 추가해야 한다.
 * 
 * <pre>
 * Configuration:
 *   packages: open.commons.core.log4j.appender
 *   ...
 * </pre>
 * 
 * 로그파일명이나 로그패턴명에 {@value #PROCESS_CONTEXT_HOLDER}를 포함시킨다.
 * </p>
 * 
 * @since 2022. 5. 13.
 * @version 2.0.0
 * @author Park_Jun_Hong (jhpark@ymtech.co.kr)
 * 
 */
@Plugin(name = ProcessRollingFileAppender.PLUGIN_NAME, category = Core.CATEGORY_NAME, elementType = Appender.ELEMENT_TYPE, printObject = true)
public final class ProcessRollingFileAppender extends AbstractOutputStreamAppender<RollingFileManager> {

    public static final String PLUGIN_NAME = "ProcessRollingFile";
    /**
     * 프로세스 컨텍스트 속성명
     */
    public static final String PROCESS_CONTEXT = "process.rollingfile.appender";
    /**
     * 로그 파일명과 로그 파일패턴에 적용되는 실제 값에 매칭하는 속성명
     */
    public static final String PROCESS_CONTEXT_HOLDER = "#process-context-holder#";

    private static final int DEFAULT_BUFFER_SIZE = 8192;

    private final String fileName;
    private final String filePattern;
    private Object advertisement;
    private final Advertiser advertiser;

    private ProcessRollingFileAppender(final String name, final Layout<? extends Serializable> layout, final Filter filter, final RollingFileManager manager, final String fileName,
            final String filePattern, final boolean ignoreExceptions, final boolean immediateFlush, final Advertiser advertiser) {
        super(name, layout, filter, ignoreExceptions, immediateFlush, null, manager);
        if (advertiser != null) {
            final Map<String, String> configuration = new HashMap<>(layout.getContentFormat());
            configuration.put("contentType", layout.getContentType());
            configuration.put("name", name);
            advertisement = advertiser.advertise(configuration);
        }
        this.fileName = fileName;
        this.filePattern = filePattern;
        this.advertiser = advertiser;
    }

    /**
     * Writes the log entry rolling over the file when required.
     * 
     * @param event
     *            The LogEvent.
     */
    @Override
    public void append(final LogEvent event) {
        getManager().checkRollover(event);
        super.append(event);
    }

    /**
     * Returns the File name for the Appender.
     * 
     * @return The file name.
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Returns the file pattern used when rolling over.
     * 
     * @return The file pattern.
     */
    public String getFilePattern() {
        return filePattern;
    }

    /**
     * Returns the triggering policy.
     * 
     * @param <T>
     *            TriggeringPolicy type
     * @return The TriggeringPolicy
     */
    public <T extends TriggeringPolicy> T getTriggeringPolicy() {
        return getManager().getTriggeringPolicy();
    }

    @Override
    public boolean stop(final long timeout, final TimeUnit timeUnit) {
        setStopping();
        final boolean stopped = super.stop(timeout, timeUnit, false);
        if (advertiser != null) {
            advertiser.unadvertise(advertisement);
        }
        setStopped();
        return stopped;
    }

    /**
     * Creates a ProcessRollingFileAppender.
     * 
     * @param fileName
     *            The name of the file that is actively written to. (required).
     * @param filePattern
     *            The pattern of the file name to use on rollover. (required).
     * @param append
     *            If true, events are appended to the file. If false, the file is overwritten when opened. Defaults to
     *            "true"
     * @param name
     *            The name of the Appender (required).
     * @param bufferedIO
     *            When true, I/O will be buffered. Defaults to "true".
     * @param bufferSizeStr
     *            buffer size for buffered IO (default is 8192).
     * @param immediateFlush
     *            When true, events are immediately flushed. Defaults to "true".
     * @param policy
     *            The triggering policy. (required).
     * @param strategy
     *            The rollover strategy. Defaults to DefaultRolloverStrategy.
     * @param layout
     *            The layout to use (defaults to the default PatternLayout).
     * @param filter
     *            The Filter or null.
     * @param ignore
     *            If {@code "true"} (default) exceptions encountered when appending events are logged; otherwise they
     *            are propagated to the caller.
     * @param advertise
     *            "true" if the appender configuration should be advertised, "false" otherwise.
     * @param advertiseUri
     *            The advertised URI which can be used to retrieve the file contents.
     * @param config
     *            The Configuration.
     * @return A ProcessRollingFileAppender.
     * @deprecated Use {@link #newBuilder()}.
     */
    @Deprecated
    public static <B extends Builder<B>> ProcessRollingFileAppender createAppender(
            // @formatter:off
            final String fileName, final String filePattern, final String append, final String name, final String bufferedIO, final String bufferSizeStr,
            final String immediateFlush, final TriggeringPolicy policy, final RolloverStrategy strategy, final Layout<? extends Serializable> layout, final Filter filter,
            final String ignore, final String advertise, final String advertiseUri, final Configuration config) {
        // @formatter:on
        final int bufferSize = Integers.parseInt(bufferSizeStr, DEFAULT_BUFFER_SIZE);
        // @formatter:off
        return ProcessRollingFileAppender.<B> newBuilder().withAdvertise(Boolean.parseBoolean(advertise)).withAdvertiseUri(advertiseUri)
                .withAppend(Booleans.parseBoolean(append, true)).setBufferedIo(Booleans.parseBoolean(bufferedIO, true)).setBufferSize(bufferSize).setConfiguration(config)
                .withFileName(fileName).withFilePattern(filePattern).setFilter(filter).setIgnoreExceptions(Booleans.parseBoolean(ignore, true))
                .setImmediateFlush(Booleans.parseBoolean(immediateFlush, true)).setLayout(layout).withCreateOnDemand(false).withLocking(false).setName(name).withPolicy(policy)
                .withStrategy(strategy).build();
        // @formatter:on
    }

    /**
     * Creates a new Builder.
     * 
     * @return a new Builder.
     * @since 2.7
     */
    @PluginBuilderFactory
    public static <B extends Builder<B>> B newBuilder() {
        return new Builder<B>().asBuilder();
    }

    /**
     * Builds FileAppender instances.
     * 
     * @param <B>
     *            The type to build
     * @since 2.7
     */
    public static class Builder<B extends Builder<B>> extends AbstractOutputStreamAppender.Builder<B>
            implements org.apache.logging.log4j.core.util.Builder<ProcessRollingFileAppender> {

        @PluginBuilderAttribute
        private String fileName;

        @PluginBuilderAttribute
        @Required
        private String filePattern;

        @PluginBuilderAttribute
        private boolean append = true;

        @PluginBuilderAttribute
        private boolean locking;

        @PluginElement("Policy")
        @Required
        private TriggeringPolicy policy;

        @PluginElement("Strategy")
        private RolloverStrategy strategy;

        @PluginBuilderAttribute
        private boolean advertise;

        @PluginBuilderAttribute
        private String advertiseUri;

        @PluginBuilderAttribute
        private boolean createOnDemand;

        @PluginBuilderAttribute
        private String filePermissions;

        @PluginBuilderAttribute
        private String fileOwner;

        @PluginBuilderAttribute
        private String fileGroup;

        @Override
        public ProcessRollingFileAppender build() {
            // Even though some variables may be annotated with @Required, we must still perform validation here for
            // call sites that build builders programmatically.
            final boolean isBufferedIo = isBufferedIo();
            final int bufferSize = getBufferSize();
            if (getName() == null) {
                LOGGER.error("ProcessRollingFileAppender '{}': No name provided.", getName());
                return null;
            }

            if (!isBufferedIo && bufferSize > 0) {
                LOGGER.warn("ProcessRollingFileAppender '{}': The bufferSize is set to {} but bufferedIO is not true", getName(), bufferSize);
            }

            if (filePattern == null) {
                LOGGER.error("ProcessRollingFileAppender '{}': No file name pattern provided.", getName());
                return null;
            }

            if (policy == null) {
                LOGGER.error("ProcessRollingFileAppender '{}': No TriggeringPolicy provided.", getName());
                return null;
            }

            if (strategy == null) {
                if (fileName != null) {
                    strategy = DefaultRolloverStrategy.newBuilder().withCompressionLevelStr(String.valueOf(Deflater.DEFAULT_COMPRESSION)).withConfig(getConfiguration()).build();
                } else {
                    strategy = DirectWriteRolloverStrategy.newBuilder().withCompressionLevelStr(String.valueOf(Deflater.DEFAULT_COMPRESSION)).withConfig(getConfiguration())
                            .build();
                }
            } else if (fileName == null && !(strategy instanceof DirectFileRolloverStrategy)) {
                LOGGER.error("ProcessRollingFileAppender '{}': When no file name is provided a DirectFilenameRolloverStrategy must be configured");
                return null;
            }

            if (this.fileName != null && this.fileName.contains(PROCESS_CONTEXT_HOLDER)) {
                String context = ThreadContext.get(PROCESS_CONTEXT);
                if (context != null) {
                    this.fileName = this.fileName.replace(PROCESS_CONTEXT_HOLDER, context);
                }
            }
            if (this.filePattern != null && this.filePattern.contains(PROCESS_CONTEXT_HOLDER)) {
                String context = ThreadContext.get(PROCESS_CONTEXT);
                if (context != null) {
                    this.filePattern = this.filePattern.replace(PROCESS_CONTEXT_HOLDER, context);
                }
            }

            final Layout<? extends Serializable> layout = getOrCreateLayout();
            final RollingFileManager manager = RollingFileManager.getFileManager(fileName, filePattern, append, isBufferedIo, policy, strategy, advertiseUri, layout, bufferSize,
                    isImmediateFlush(), createOnDemand, filePermissions, fileOwner, fileGroup, getConfiguration());
            if (manager == null) {
                return null;
            }

            manager.initialize();

            return new ProcessRollingFileAppender(getName(), layout, getFilter(), manager, fileName, filePattern, isIgnoreExceptions(), isImmediateFlush(),
                    advertise ? getConfiguration().getAdvertiser() : null);
        }

        public String getAdvertiseUri() {
            return advertiseUri;
        }

        public String getFileGroup() {
            return fileGroup;
        }

        public String getFileName() {
            return fileName;
        }

        public String getFileOwner() {
            return fileOwner;
        }

        public String getFilePattern() {
            return filePattern;
        }

        public String getFilePermissions() {
            return filePermissions;
        }

        public TriggeringPolicy getPolicy() {
            return policy;
        }

        public RolloverStrategy getStrategy() {
            return strategy;
        }

        public boolean isAdvertise() {
            return advertise;
        }

        public boolean isAppend() {
            return append;
        }

        public boolean isCreateOnDemand() {
            return createOnDemand;
        }

        public boolean isLocking() {
            return locking;
        }

        public B withAdvertise(final boolean advertise) {
            this.advertise = advertise;
            return asBuilder();
        }

        public B withAdvertiseUri(final String advertiseUri) {
            this.advertiseUri = advertiseUri;
            return asBuilder();
        }

        public B withAppend(final boolean append) {
            this.append = append;
            return asBuilder();
        }

        public B withCreateOnDemand(final boolean createOnDemand) {
            this.createOnDemand = createOnDemand;
            return asBuilder();
        }

        public B withFileGroup(final String fileGroup) {
            this.fileGroup = fileGroup;
            return asBuilder();
        }

        public B withFileName(final String fileName) {
            this.fileName = fileName;
            return asBuilder();
        }

        public B withFileOwner(final String fileOwner) {
            this.fileOwner = fileOwner;
            return asBuilder();
        }

        public B withFilePattern(final String filePattern) {
            this.filePattern = filePattern;
            return asBuilder();
        }

        public B withFilePermissions(final String filePermissions) {
            this.filePermissions = filePermissions;
            return asBuilder();
        }

        public B withLocking(final boolean locking) {
            this.locking = locking;
            return asBuilder();
        }

        public B withPolicy(final TriggeringPolicy policy) {
            this.policy = policy;
            return asBuilder();
        }

        public B withStrategy(final RolloverStrategy strategy) {
            this.strategy = strategy;
            return asBuilder();
        }

    }
}
