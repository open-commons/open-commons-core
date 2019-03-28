/*
 * Copyright 2011 Park Jun-Hong (parkjunhong77/google/com)
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
*/
package open.commons.test;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;

import open.commons.collection.OrderingKey;
import open.commons.utils.FormatUtils;

/**
 * 
 * <BR>
 * 
 * @since 2012. 02. 21.
 * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
 */
public class RecursiveExecutor {

    public PrintWriter writer;

    public final DecimalFormat df = new DecimalFormat("#,###");

    protected Map<OrderingKey<Long>, String> results = new ConcurrentSkipListMap<OrderingKey<Long>, String>();

    protected Map<Comparable<?>, Set<Comparable<?>>> graphData = new ConcurrentSkipListMap<Comparable<?>, Set<Comparable<?>>>();

    protected final String GRAPH_HEADER_KEY_STRING = "";

    private int COUNTS = 1;

    private int COUNTS_TOTAL = 1;

    public RecursiveExecutor() {
        writer = new PrintWriter(System.out);
    }

    public RecursiveExecutor(OutputStream outStream) {
        writer = new PrintWriter(outStream);
    }

    public void close() {
        if (writer != null) {
            writer.close();
        }
    }

    @SuppressWarnings("unchecked")
    private Set<Comparable<?>> getGraphData(Object key) {
        for (Comparable<?> rKey : graphData.keySet()) {
            if (((OrderingKey<Long>) rKey).getValue().equals(key)) {
                return graphData.get(rKey);
            }
        }

        return null;
    }

    public void invoke(String name, Object... objs) {
        Class<?> clazz = getClass();

        Method method = null;
        if (objs.length > 0) {
            Class<?>[] parameterTypes = new Class<?>[objs.length];

            for (int i = 0; i < objs.length; i++) {
                parameterTypes[i] = objs[i].getClass();
            }

            try {
                method = clazz.getMethod(name, parameterTypes);
            } catch (SecurityException e) {

                e.printStackTrace();
            } catch (NoSuchMethodException e) {

                e.printStackTrace();
            }
        } else {
            try {
                method = clazz.getMethod(name);
            } catch (SecurityException e) {

                e.printStackTrace();
            } catch (NoSuchMethodException e) {

                e.printStackTrace();
            }
        }

        if (method != null) {
            try {
                method.invoke(this, objs);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    private void makeGraphData(int count, int maxLength) {
        // check 'count'
        Set<Comparable<?>> rowData = getGraphData(GRAPH_HEADER_KEY_STRING);
        if (rowData == null) {
            rowData = new ConcurrentSkipListSet<Comparable<?>>();

            graphData.put(new OrderingKey<Long>(GRAPH_HEADER_KEY_STRING, System.nanoTime()), rowData);
        }

        rowData.add(new OrderingKey<Integer>(maxLength, count));

        // check 'categories'
        for (Entry<OrderingKey<Long>, String> entry : results.entrySet()) {

            rowData = getGraphData(entry.getKey().getValue());

            if (rowData == null) {
                rowData = new ConcurrentSkipListSet<Comparable<?>>();

                graphData.put(new OrderingKey<Long>(entry.getKey().getValue(), System.nanoTime()), rowData);
            }

            rowData.add(new OrderingKey<Long>(Integer.toString(count), entry.getKey().getOrder()));
        }
    }

    private void print(Object obj) {
        writer.write(obj + "\n");
        writer.flush();
    }

    public void print(PrintWriter writer) {

    }

    @SuppressWarnings("unchecked")
    public void printGraphData() {

        // Set<Comparable<?>> header = removeGraphData(GRAPH_HEADER_KEY_STRING);
        Set<Comparable<?>> header = getGraphData(GRAPH_HEADER_KEY_STRING);

        if (header != null) {

            List<Integer> lengths = new ArrayList<Integer>();
            for (Comparable<?> key : graphData.keySet()) {
                lengths.add(((String) ((OrderingKey<Long>) key).getValue()).length());
            }
            int categoryMaxLength = Collections.max(lengths);

            StringBuffer stringBuf = new StringBuffer();
            stringBuf.append("%" + categoryMaxLength + "s");
            for (Comparable<?> value : header) {
                stringBuf.append("\t%" + ((OrderingKey<Integer>) value).getValue() + "s");
            }

            String formatString = stringBuf.toString();

            stringBuf.delete(0, stringBuf.length());

            Object[] objs = null;
            for (Entry<Comparable<?>, Set<Comparable<?>>> entry : graphData.entrySet()) {
                String key = (String) ((OrderingKey<Long>) entry.getKey()).getValue();
                Set<Comparable<?>> values = entry.getValue();

                objs = new Object[values.size() + 1];
                objs[0] = key;
                int i = 1;
                for (Comparable<?> value : values) {
                    objs[i++] = ((OrderingKey<Long>) value).getOrder();
                }

                print(String.format(formatString, objs));
            }
        } else {
            System.err.println("THERE IS NO HEADER!!!!");
        }
    }

    @SuppressWarnings("unchecked")
    private Set<Comparable<?>> removeGraphData(Object key) {
        for (Comparable<?> rKey : graphData.keySet()) {
            if (((OrderingKey<Long>) rKey).getValue().equals(key)) {
                return graphData.remove(rKey);
            }
        }

        return null;
    }

    public void test(final int count, boolean yesLog, final IExecutable... execs) {

        for (IExecutable exec : execs) {
            if (exec != null) {
                test(count, exec, yesLog);
            }
        }

        List<Integer> lengths = new ArrayList<Integer>();
        for (OrderingKey<Long> key : results.keySet()) {
            lengths.add(((String) key.getValue()).length());
        }

        int categoryMaxLength = Collections.max(lengths);

        lengths.clear();
        for (String value : results.values()) {
            lengths.add(value.length());
        }

        int elapsedMaxLength = Collections.max(lengths);

        String resultFormat = "%" + categoryMaxLength + "s: %" + elapsedMaxLength + "s ns";

        for (Entry<OrderingKey<Long>, String> entry : results.entrySet()) {
            print("[RESULT] tested Count: " + FormatUtils.toThousandExpr(count) + ", " + String.format(resultFormat, entry.getKey().getValue(), entry.getValue()));
        }

        makeGraphData(count, elapsedMaxLength);

        results.clear();
    }

    public void test(final int count, final IExecutable... execs) {
        test(count, true, execs);
    }

    /**
     * 
     * @param count
     *            실행 횟수
     * @param exec
     *            실행 대상 <BR>
     * @param yesLog
     * 
     * @since 2012. 3. 7.
     * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
     */
    protected void test(final int count, final IExecutable exec, boolean yesLog) {

        if (yesLog) {
            print(">>> begin: TEST_MAIN.test(...).new Runnable() {...}.run: " + exec.category());
        }

        try {
            long stime = 0;
            long etime = 0;
            long elapsed = 0;

            stime = System.nanoTime();
            for (int i = 0; i < count; i++) {
                if (yesLog) {
                    System.out.println("(begin ) [" + COUNTS + "/" + COUNTS_TOTAL + "] " + i + "/" + count);
                }

                exec.run();

                if (yesLog) {
                    System.out.println("(finish) [" + COUNTS + "/" + COUNTS_TOTAL + "] " + i + "/" + count);
                }
            }
            etime = System.nanoTime();

            elapsed = etime - stime;

            results.put(new OrderingKey<Long>(exec.category(), elapsed), String.valueOf(elapsed));// FormatUtils.toThousandExpr(elapsed));
        } catch (Exception e) {
            System.err.println("category: " + exec.category() + ", cause: " + e.getMessage());
        }

        if (yesLog) {
            print("<<< end  : TEST_MAIN.test(...).new Runnable() {...}.run: " + exec.category());
        }
    }

    public void test(int[] counts, boolean yesLog, final IExecutable... execs) {

        COUNTS_TOTAL = counts.length;
        for (int i = 0; i < counts.length; i++) {
            COUNTS = i;
            test(counts[i], yesLog, execs);

            print("------------------------------------------------");
        }
    }

    public void test(int[] counts, final IExecutable... execs) {
        test(counts, true, execs);
    }

    public static void println() {
        System.out.println();
    }

    public static void println(boolean[] obj) {
        System.out.println(Arrays.toString(obj));
    }

    public static void println(byte[] obj) {
        System.out.println(Arrays.toString(obj));
    }

    public static void println(char[] obj) {
        System.out.println(Arrays.toString(obj));
    }

    public static void println(double[] obj) {
        System.out.println(Arrays.toString(obj));
    }

    public static void println(float[] obj) {
        System.out.println(Arrays.toString(obj));
    }

    public static void println(int[] obj) {
        System.out.println(Arrays.toString(obj));
    }

    public static void println(long[] obj) {

        System.out.println(Arrays.toString(obj));
    }

    public static void println(Object obj) {
        if (obj.getClass().isArray()) {
            println((Object[]) obj);
        } else {
            System.out.println(obj);
        }
    }

    public static void println(Object[] obj) {
        System.out.println(Arrays.toString(obj));
    }

    public abstract class AbstractExecutable implements IExecutable {
        protected Object[] objs;

        public AbstractExecutable() {
            this(new Object[0]);
        }

        public AbstractExecutable(Object... objs) {
            this.objs = objs;
        }
    }

    public interface IExecutable {
        String category();

        void run();
    }
}
