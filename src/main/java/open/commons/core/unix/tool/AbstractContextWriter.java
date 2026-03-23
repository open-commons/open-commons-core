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
* This file is igenerated under this project, "open.commons".
*
* Date  : 2013. 5. 27. 오전 10:08:38
*
* Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
* 
*/

/**
 * 
 */
package open.commons.core.unix.tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import org.jspecify.annotations.Nullable;

import open.commons.core.utils.FormatUtils;
import open.commons.core.utils.ObjectUtils;

/**
 * 
 * @since 2013. 5. 27.
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */
public abstract class AbstractContextWriter implements IFileContextWriter {

    protected static final String LINE_SEPARATOR = Objects.requireNonNull(
            // [PATCH[ JDK 표준 API의 JSpecify 미지원 우회용 임시 널 체크.
            // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 requireNonNull 래핑 제거.
            System.getProperty("line.separator") //
    );

    protected static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("[yyyy/MM/dd hh:mm:ss] ");

    protected @Nullable String TEXT_ENCODING = System.getProperty("sun.jnu.encoding");
    protected String PATTERN_FN = "(%10s): ";

    protected boolean onTimestamp = false;
    protected boolean onFilename = false;
    protected boolean onDirectory = false;

    public AbstractContextWriter() {
        PATTERN_FN = "(%" + FileMonitor.vfLength + "s): ";
    }

    /**
     * 
     * @param onTimestamp
     *            whether show a timestamp or not.
     * @param onFilename
     *            whether show a filename or not.
     * @param onDirectory
     *            whether show a directory contain the file modified.
     */
    public AbstractContextWriter(boolean onTimestamp, boolean onFilename, boolean onDirectory) {
        this();
        this.onTimestamp = onTimestamp;
        this.onFilename = onFilename;
        this.onDirectory = onDirectory;
    }

    /**
     * 
     * @see java.io.Closeable#close()
     */
    @Override
    public void close() throws IOException {

    }

    /**
     * 
     * @param context
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code context})가 {@code null}인 경우 발생.
     */
    protected final String contextToString(open.commons.core.unix.tool.IFileModifyListener.FileContext context) {
        Objects.requireNonNull(context);

        byte[] bytes = context.context;

        String date = onTimestamp ? DATE_FORMAT.format(new Date(System.currentTimeMillis())) : "";
        String fn = "";
        String ctx = "";
        String path = "";

        try {
            File file = new File(context.getFile());

            if (onFilename) {
                fn = file.getName();
            }

            ctx = new String(bytes, TEXT_ENCODING);

            if (ctx.indexOf(LINE_SEPARATOR) == 0) {
                ctx = ctx.replaceFirst(LINE_SEPARATOR, "");
            }

            if (ctx.lastIndexOf(LINE_SEPARATOR) != -1 // check none
                    && ctx.lastIndexOf(LINE_SEPARATOR) == ctx.length() - LINE_SEPARATOR.length()) {
                ctx = ctx.substring(0, ctx.length() - LINE_SEPARATOR.length());
            }

            // windows
            if (ctx.length() > 0 && ctx.charAt(ctx.length() - 1) == '\n') {
                ctx = ctx.substring(0, ctx.length() - 1);
            }

            if (onDirectory) {
                path = "\t" + file.getParent();
            }
        } catch (UnsupportedEncodingException ignored) {
            ctx = "";
        }

        StringBuilder sb = new StringBuilder();

        BufferedReader reader = new BufferedReader(new StringReader(ctx));
        String readline = null;

        try {
            while ((readline = reader.readLine()) != null) {
                sb.append(date);
                sb.append(onFilename ? FormatUtils.format(PATTERN_FN, new Object[] { fn }) : fn);
                sb.append(readline);
                sb.append(path);
                sb.append('\n');
            }
        } catch (Exception e) {
        }

        return Objects.requireNonNull(
                // [PATCH[ JDK 표준 API의 JSpecify 미지원 우회용 임시 널 체크.
                // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 requireNonNull 래핑 제거.
                sb.toString() //
        );
    }

    /**
     * 
     * @see open.commons.core.unix.tool.IFileContextWriter#isClosed()
     */
    @Override
    public boolean isClosed() {
        return false;
    }

    /**
     * @throws NullPointerException
     *             파라미터({@code out 또는 context})가 {@code null}인 경우 발생.
     */
    protected final void write(OutputStream out, open.commons.core.unix.tool.IFileModifyListener.FileContext context) throws IOException {
        ObjectUtils.requireNonNulls(out, context);

        out.write(contextToString(context).getBytes());
    }

    /**
     * @throws NullPointerException
     *             파라미터({@code out 또는 context})가 {@code null}인 경우 발생.
     */
    protected final void write(PrintStream out, open.commons.core.unix.tool.IFileModifyListener.FileContext context) {
        ObjectUtils.requireNonNulls(out, context);

        out.print(contextToString(context));
    }

    /**
     * @throws NullPointerException
     *             파라미터({@code writer 또는 context})가 {@code null}인 경우 발생.
     */
    protected final void write(Writer writer, open.commons.core.unix.tool.IFileModifyListener.FileContext context) throws IOException {
        ObjectUtils.requireNonNulls(writer, context);

        writer.write(contextToString(context));
    }
}
