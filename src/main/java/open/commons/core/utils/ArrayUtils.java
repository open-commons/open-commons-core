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
* Date  : 2012. 02. 21. 오전 10:13:26
*
* Author: Park Jun-Hong (parkjunhong77@gmail.com)
* 
*/
package open.commons.core.utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import open.commons.core.DefaultEquivalent;
import open.commons.core.EquivalentFactory;
import open.commons.core.IEquivalent;
import open.commons.core.Result;
import open.commons.core.collection.DefaultContainer;
import open.commons.core.collection.IContainer;

/**
 * 배열에 대한 기능성 메소드를 제공하는 클래스 <BR>
 * 
 * @since 2012. 02. 21.
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
@SuppressWarnings("unchecked")
public class ArrayUtils {

    /**
     * 기존 배열에 새로운 데이타를 맨 뒤에 추가한 후, 새로운 배열을 반환합니다.
     * 
     * @param array
     * @param value
     * @return
     * 
     *         <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static boolean[] add(boolean[] array, boolean value) {

        boolean[] newArray = null;

        if (array != null && array.length > 0) {
            newArray = new boolean[array.length + 1];

            System.arraycopy(array, 0, newArray, 0, array.length);
        } else {
            newArray = new boolean[1];
        }

        newArray[newArray.length - 1] = value;
        return newArray;
    }

    /**
     * 기존 배열에 새로운 데이타를 맨 뒤에 추가한 후, 새로운 배열을 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 7. 4.		박준홍			최초 작성
     * </pre>
     *
     * @param array
     * @param values
     * @return
     *
     * @since 2019. 7. 4.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static boolean[] add(boolean[] array, boolean... values) {
        return merge(array, values);
    }

    /**
     * 기존 배열에 새로운 데이타를 맨 뒤에 추가한 후, 새로운 배열을 반환합니다.
     * 
     * @param array
     * @param value
     * @return
     * 
     *         <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static byte[] add(byte[] array, byte value) {
        byte[] newArray = null;

        if (array != null && array.length > 0) {
            newArray = new byte[array.length + 1];

            System.arraycopy(array, 0, newArray, 0, array.length);
        } else {
            newArray = new byte[1];
        }

        newArray[newArray.length - 1] = value;
        return newArray;
    }

    /**
     * 기존 배열에 새로운 데이타를 맨 뒤에 추가한 후, 새로운 배열을 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 7. 4.		박준홍			최초 작성
     * </pre>
     *
     * @param array
     * @param values
     * @return
     *
     * @since 2019. 7. 4.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static byte[] add(byte[] array, byte... values) {
        return merge(array, values);
    }

    /**
     * 기존 배열에 새로운 데이타를 맨 뒤에 추가한 후, 새로운 배열을 반환합니다.
     * 
     * @param array
     * @param value
     * @return
     * 
     *         <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static char[] add(char[] array, char value) {
        char[] newArray = null;

        if (array != null && array.length > 0) {
            newArray = new char[array.length + 1];

            System.arraycopy(array, 0, newArray, 0, array.length);
        } else {
            newArray = new char[1];
        }

        newArray[newArray.length - 1] = value;
        return newArray;
    }

    /**
     * 기존 배열에 새로운 데이타를 맨 뒤에 추가한 후, 새로운 배열을 반환합니다. <br>
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 7. 4.		박준홍			최초 작성
     * </pre>
     *
     * @param array
     * @param values
     * @return
     *
     * @since 2019. 7. 4.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static char[] add(char[] array, char... values) {
        return merge(array, values);
    }

    /**
     * 기존 배열에 새로운 데이타를 맨 뒤에 추가한 후, 새로운 배열을 반환합니다.
     * 
     * @param array
     * @param value
     * @return
     * 
     *         <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static double[] add(double[] array, double value) {
        double[] newArray = null;

        if (array != null && array.length > 0) {
            newArray = new double[array.length + 1];

            System.arraycopy(array, 0, newArray, 0, array.length);
        } else {
            newArray = new double[1];
        }

        newArray[newArray.length - 1] = value;
        return newArray;
    }

    /**
     * 기존 배열에 새로운 데이타를 맨 뒤에 추가한 후, 새로운 배열을 반환합니다. <br>
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 7. 4.      박준홍         최초 작성
     * </pre>
     *
     * @param array
     * @param values
     * @return
     *
     * @since 2019. 7. 4.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static double[] add(double[] array, double... values) {
        return merge(array, values);
    }

    /**
     * 기존 배열에 새로운 데이타를 맨 뒤에 추가한 후, 새로운 배열을 반환합니다.
     * 
     * @param array
     * @param value
     * @return
     * 
     *         <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static float[] add(float[] array, float value) {
        float[] newArray = null;

        if (array != null && array.length > 0) {
            newArray = new float[array.length + 1];

            System.arraycopy(array, 0, newArray, 0, array.length);
        } else {
            newArray = new float[1];
        }

        newArray[newArray.length - 1] = value;
        return newArray;
    }

    /**
     * 기존 배열에 새로운 데이타를 맨 뒤에 추가한 후, 새로운 배열을 반환합니다. <br>
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 7. 4.      박준홍         최초 작성
     * </pre>
     *
     * @param array
     * @param values
     * @return
     *
     * @since 2019. 7. 4.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static float[] add(float[] array, float... values) {
        return merge(array, values);
    }

    /**
     * 기존 배열에 새로운 데이타를 맨 뒤에 추가한 후, 새로운 배열을 반환합니다.
     * 
     * @param array
     * @param value
     * @return
     * 
     *         <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static int[] add(int[] array, int value) {
        int[] newArray = null;

        if (array != null && array.length > 0) {
            newArray = new int[array.length + 1];

            System.arraycopy(array, 0, newArray, 0, array.length);
        } else {
            newArray = new int[1];
        }

        newArray[newArray.length - 1] = value;
        return newArray;
    }

    /**
     * 기존 배열에 새로운 데이타를 맨 뒤에 추가한 후, 새로운 배열을 반환합니다. <br>
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 7. 4.      박준홍         최초 작성
     * </pre>
     *
     * @param array
     * @param values
     * @return
     *
     * @since 2019. 7. 4.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static int[] add(int[] array, int... values) {
        return merge(array, values);
    }

    /**
     * 기존 배열에 새로운 데이타를 맨 뒤에 추가한 후, 새로운 배열을 반환합니다.
     * 
     * @param array
     * @param value
     * @return
     * 
     *         <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static long[] add(long[] array, long value) {
        long[] newArray = null;

        if (array != null && array.length > 0) {
            newArray = new long[array.length + 1];

            System.arraycopy(array, 0, newArray, 0, array.length);
        } else {
            newArray = new long[1];
        }

        newArray[newArray.length - 1] = value;
        return newArray;
    }

    /**
     * 기존 배열에 새로운 데이타를 맨 뒤에 추가한 후, 새로운 배열을 반환합니다. <br>
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 7. 4.      박준홍         최초 작성
     * </pre>
     *
     * @param array
     * @param values
     * @return
     *
     * @since 2019. 7. 4.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static long[] add(long[] array, long... values) {
        return merge(array, values);
    }

    /**
     * 기존 배열에 새로운 데이타를 맨 뒤에 추가한 후, 새로운 배열을 반환합니다.
     * 
     * @param array
     * @param value
     * @return
     * 
     *         <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static short[] add(short[] array, short value) {
        short[] newArray = null;

        if (array != null && array.length > 0) {
            newArray = new short[array.length + 1];

            System.arraycopy(array, 0, newArray, 0, array.length);
        } else {
            newArray = new short[1];
        }

        newArray[newArray.length - 1] = value;
        return newArray;
    }

    /**
     * 기존 배열에 새로운 데이타를 맨 뒤에 추가한 후, 새로운 배열을 반환합니다. <br>
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 7. 4.      박준홍         최초 작성
     * </pre>
     *
     * @param array
     * @param values
     * @return
     *
     * @since 2019. 7. 4.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static short[] add(short[] array, short... values) {
        return merge(array, values);
    }

    /**
     * 기존 배열에 새로운 데이타를 맨 뒤에 추가한 후, 새로운 배열을 반환합니다.
     * 
     * @param array
     * @param value
     * @return a new array contains new <code>value</code> or null if both of parameters are <code>null</code>.
     * 
     * @Exception {@link ArrayStoreException} - <code>T</code> is Wrapper Class of primitive types and
     *            <code>value</code> is the primitive type's value.
     *            <p>
     *            e.g. add(new Boolean[] { true }, false);
     *            </p>
     * 
     *            <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <T> T[] add(T[] array, T value) {

        T[] newArray = null;

        if (array != null && value != null) {

            assertComponentType(array, value.getClass());

            newArray = (T[]) Array.newInstance(array.getClass().getComponentType(), array.length + 1);
            System.arraycopy(array, 0, newArray, 0, array.length);
            newArray[newArray.length - 1] = value;
        } else if (array != null) {
            newArray = (T[]) Array.newInstance(array.getClass().getComponentType(), 1);
        } else if (value != null) {
            newArray = (T[]) Array.newInstance(value.getClass(), 1);
            newArray[0] = value;
        } else {
            throw new IllegalArgumentException(new NullPointerException("All parameters(T[] array, T value) must not be null: arr=null, value=null"));
        }

        return newArray;
    }

    /**
     * 기존 배열에 새로운 데이타를 맨 뒤에 추가한 후, 새로운 배열을 반환합니다. <br>
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 7. 4.      박준홍         최초 작성
     * </pre>
     *
     * @param array
     * @param values
     * @return
     *
     * @since 2019. 7. 4.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <T> T[] add(T[] array, T... values) {
        return merge(array, values);
    }

    /**
     * 기존 배열에 새로운 데이타를 맨 뒤에 추가한 후, 새로운 배열을 반환합니다.
     * 
     * @param array
     * @param value
     * @return
     * 
     *         <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static boolean[] addIfAbsent(boolean[] array, boolean value) {
        if (contains(array, value)) {
            return copyOf(array, array.length);
        }

        return add(array, value);
    }

    /**
     * 기존 배열에 새로운 데이타를 맨 뒤에 추가한 후, 새로운 배열을 반환합니다.
     * 
     * @param array
     * @param value
     * @return
     * 
     *         <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static byte[] addIfAbsent(byte[] array, byte value) {
        if (contains(array, value)) {
            return copyOf(array, array.length);
        }

        return add(array, value);
    }

    /**
     * 기존 배열에 새로운 데이타를 맨 뒤에 추가한 후, 새로운 배열을 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 7. 4.		박준홍			최초 작성
     * </pre>
     *
     * @param array
     * @param values
     * @return
     *
     * @since 2019. 7. 4.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static byte[] addIfAbsent(byte[] array, byte... values) {
        if (values == null || values.length < 1) {
            return copyOf(array, array.length);
        }

        List<Byte> indice = new ArrayList<>();
        for (byte value : values) {
            if (!contains(array, value)) {
                indice.add(value);
            }
        }

        return merge(array, toPrimitiveArray(indice.toArray(new Byte[0])));
    }

    /**
     * 기존 배열에 새로운 데이타를 맨 뒤에 추가한 후, 새로운 배열을 반환합니다.
     * 
     * @param array
     * @param value
     * @return
     * 
     *         <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static char[] addIfAbsent(char[] array, char value) {
        if (contains(array, value)) {
            return copyOf(array, array.length);
        }

        return add(array, value);
    }

    /**
     * 기존 배열에 새로운 데이타를 맨 뒤에 추가한 후, 새로운 배열을 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 7. 4.		박준홍			최초 작성
     * </pre>
     *
     * @param array
     * @param values
     * @return
     *
     * @since 2019. 7. 4.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static char[] addIfAbsent(char[] array, char... values) {
        if (values == null || values.length < 1) {
            return copyOf(array, array.length);
        }

        List<Character> indice = new ArrayList<>();
        for (char value : values) {
            if (!contains(array, value)) {
                indice.add(value);
            }
        }

        return merge(array, toPrimitiveArray(indice.toArray(new Character[0])));
    }

    /**
     * 기존 배열에 새로운 데이타를 맨 뒤에 추가한 후, 새로운 배열을 반환합니다.
     * 
     * @param array
     * @param value
     * @return
     * 
     *         <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static double[] addIfAbsent(double[] array, double value) {
        if (contains(array, value)) {
            return copyOf(array, array.length);
        }

        return add(array, value);
    }

    /**
     * 기존 배열에 새로운 데이타를 맨 뒤에 추가한 후, 새로운 배열을 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 7. 4.		박준홍			최초 작성
     * </pre>
     *
     * @param array
     * @param values
     * @return
     *
     * @since 2019. 7. 4.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static double[] addIfAbsent(double[] array, double... values) {
        if (values == null || values.length < 1) {
            return copyOf(array, array.length);
        }

        List<Double> indice = new ArrayList<>();
        for (double value : values) {
            if (!contains(array, value)) {
                indice.add(value);
            }
        }

        return merge(array, toPrimitiveArray(indice.toArray(new Double[0])));
    }

    /**
     * 기존 배열에 새로운 데이타를 맨 뒤에 추가한 후, 새로운 배열을 반환합니다.
     * 
     * @param array
     * @param value
     * @return
     * 
     *         <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static float[] addIfAbsent(float[] array, float value) {
        if (contains(array, value)) {
            return copyOf(array, array.length);
        }

        return add(array, value);
    }

    /**
     * 기존 배열에 새로운 데이타를 맨 뒤에 추가한 후, 새로운 배열을 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 7. 4.		박준홍			최초 작성
     * </pre>
     *
     * @param array
     * @param values
     * @return
     *
     * @since 2019. 7. 4.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static float[] addIfAbsent(float[] array, float... values) {
        if (values == null || values.length < 1) {
            return copyOf(array, array.length);
        }

        List<Float> indice = new ArrayList<>();
        for (float value : values) {
            if (!contains(array, value)) {
                indice.add(value);
            }
        }

        return merge(array, toPrimitiveArray(indice.toArray(new Float[0])));
    }

    /**
     * 기존 배열에 새로운 데이타를 맨 뒤에 추가한 후, 새로운 배열을 반환합니다.
     * 
     * @param array
     * @param value
     * @return
     * 
     *         <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static int[] addIfAbsent(int[] array, int value) {
        if (contains(array, value)) {
            return copyOf(array, array.length);
        }

        return add(array, value);
    }

    /**
     * 기존 배열에 새로운 데이타를 맨 뒤에 추가한 후, 새로운 배열을 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 7. 4.		박준홍			최초 작성
     * </pre>
     *
     * @param array
     * @param values
     * @return
     *
     * @since 2019. 7. 4.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static int[] addIfAbsent(int[] array, int... values) {
        if (values == null || values.length < 1) {
            return copyOf(array, array.length);
        }

        List<Integer> indice = new ArrayList<>();
        for (int value : values) {
            if (!contains(array, value)) {
                indice.add(value);
            }
        }

        return merge(array, toPrimitiveArray(indice.toArray(new Integer[0])));
    }

    /**
     * 기존 배열에 새로운 데이타를 맨 뒤에 추가한 후, 새로운 배열을 반환합니다.
     * 
     * @param array
     * @param value
     * @return
     * 
     *         <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static long[] addIfAbsent(long[] array, long value) {
        if (contains(array, value)) {
            return copyOf(array, array.length);
        }

        return add(array, value);
    }

    /**
     * 기존 배열에 새로운 데이타를 맨 뒤에 추가한 후, 새로운 배열을 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 7. 4.		박준홍			최초 작성
     * </pre>
     *
     * @param array
     * @param values
     * @return
     *
     * @since 2019. 7. 4.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static long[] addIfAbsent(long[] array, long... values) {
        if (values == null || values.length < 1) {
            return copyOf(array, array.length);
        }

        List<Long> indice = new ArrayList<>();
        for (long value : values) {
            if (!contains(array, value)) {
                indice.add(value);
            }
        }

        return merge(array, toPrimitiveArray(indice.toArray(new Long[0])));
    }

    /**
     * 기존 배열에 새로운 데이타를 맨 뒤에 추가한 후, 새로운 배열을 반환합니다.
     * 
     * @param array
     * @param value
     * @return
     * 
     *         <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static short[] addIfAbsent(short[] array, short value) {
        if (contains(array, value)) {
            return copyOf(array, array.length);
        }

        return add(array, value);
    }

    /**
     * 기존 배열에 새로운 데이타를 맨 뒤에 추가한 후, 새로운 배열을 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 7. 4.		박준홍			최초 작성
     * </pre>
     *
     * @param array
     * @param values
     * @return
     *
     * @since 2019. 7. 4.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static short[] addIfAbsent(short[] array, short... values) {
        if (values == null || values.length < 1) {
            return copyOf(array, array.length);
        }

        List<Short> indice = new ArrayList<>();
        for (short value : values) {
            if (!contains(array, value)) {
                indice.add(value);
            }
        }

        return merge(array, toPrimitiveArray(indice.toArray(new Short[0])));
    }

    /**
     * 기존 배열에 새로운 데이타를 맨 뒤에 추가한 후, 새로운 배열을 반환합니다.
     * 
     * @param array
     * @param value
     * @return a new array contains new <code>value</code> or null if both of parameters are <code>null</code>.
     * 
     * @Exception {@link ArrayStoreException} - <code>T</code> is Wrapper Class of primitive types and
     *            <code>value</code> is the primitive type's value.
     *            <p>
     *            e.g. add(new Boolean[] { true }, false);
     *            </p>
     * 
     *            <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <T> T[] addIfAbsent(T[] array, T value) {
        if (contains(array, value)) {
            return copyOf(array, array.length);
        }

        return add(array, value);
    }

    /**
     * 주어진 길이(<b><code>length</code></b>)만큼 주어진 배열(<b><code>classes</code></b>) 로부터 데이터를 복사하여 새로운 배열을 반환합니다.<br>
     * 주어진 배열(<b><code>classes</code></b>)의 길이가 주어진 길이(<b><code>length</code></b>)보다 작은 경우, 마지막 데이터로 모두 채운다.
     * 
     * @param length
     * @param classes
     * @return
     * 
     * @since 2014. 6. 18.
     */
    public static Class<?>[] adjustByLength(int length, Class<?>... classes) {

        Class<?>[] newArray = new Class<?>[length];
        System.arraycopy(classes, 0, newArray, 0, Math.min(length, classes.length));

        if (length > classes.length) {
            for (int i = classes.length; i < length; i++) {
                newArray[i] = classes[classes.length - 1];
            }
        }

        return newArray;
    }

    public static List<Boolean> asList(boolean[] array) {
        ArrayList<Boolean> list = new ArrayList<Boolean>();

        for (boolean value : array) {
            list.add(value);
        }

        return list;
    }

    /**
     * 배열을 {@link List}로 변환하여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2023. 7. 25.		박준홍			최초 작성
     * </pre>
     *
     * @param <T>
     * @param arr
     * @return {@link List} 또는 <code>null</code>.
     *
     * @since 2023. 7. 25.
     * @version 2.0.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <T> List<T> asList(T[] arr) {
        return arr != null ? Arrays.asList(arr) : null;
    }

    /**
     * 데이터를 변환하여 {@link List}로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2023. 7. 25.		박준홍			최초 작성
     * </pre>
     *
     * @param <T>
     * @param <R>
     * @param arr
     *            데이터 배열
     * @param f
     *            데이터 변환 함수
     * @return
     *
     * @since 2023. 7. 25.
     * @version 2.0.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <T, R> List<R> asList(T[] arr, Function<T, R> f) {
        if (arr == null) {
            return null;
        }

        List<R> list = new ArrayList<>();
        for (T t : arr) {
            list.add(f.apply(t));
        }

        return list;
    }

    private static <T> void assertComponentType(Class<?> arr1, Class<?> arr2) {
        if (!arr1.isAssignableFrom(arr2)) {
            throw ExceptionUtils.newException(ArrayStoreException.class, "두 개의 데이터가 상호적이지 않습니다. array.component-type=%s, value.type=%s", arr1, arr2);
        }
    }

    private static <T> void assertComponentType(T[] arr1, Class<?> valueType) {
        assertComponentType(arr1.getClass().getComponentType(), valueType);
    }

    private static <T> void assertComponentType(T[] arr1, T[] arr2) {
        assertComponentType(arr1.getClass().getComponentType(), arr2.getClass().getComponentType());
    }

    /**
     * 
     * @param min
     *            최소값
     * @param max
     *            최대값
     * @param value
     *            값 <BR>
     * @since 2012. 03. 13.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    private static void checkRange(int min, int max, int... values) {
        for (int index : values) {
            if (index < min || index > max) {
                throw new IllegalArgumentException("An index(int index) must be between " + min + " to " + max + ". value=" + Arrays.toString(values));
            }
        }
    }

    /**
     * 
     * @param v1
     * @param v2
     * @return
     * 
     * @throws NullPointerException
     *             두 개의 입력값 중 하나라도 <code>null</code>인 경우.
     */
    public static int comparable(byte[] v1, byte[] v2) {

        if (v1 == null || v2 == null) {
            throw new NullPointerException("arg1: " + v1 + ", arg2: " + v2);
        }

        if (v1.length != v2.length) {
            throw new IllegalArgumentException("두 입력값의 길이가 다릅니다. arg1.length: " + v1.length + ", arg2.length: " + v2.length);
        }

        if (v1 == v2) {
            return 0;
        }

        for (int i = 0; i < v1.length; i++) {
            if (v1[i] != v2[i]) {
                return v1[i] - v2[i];
            }
        }

        return 0;
    }

    /**
     * 
     * @param v1
     * @param v2
     * @return
     * 
     * @throws NullPointerException
     *             두 개의 입력값 중 하나라도 <code>null</code>인 경우.
     */
    public static int comparable(char[] v1, char[] v2) {

        if (v1 == null || v2 == null) {
            throw new NullPointerException("arg1: " + Arrays.toString(v1) + ", arg2: " + Arrays.toString(v2));
        }

        if (v1.length != v2.length) {
            throw new IllegalArgumentException("두 입력값의 길이가 다릅니다. arg1.length: " + v1.length + ", arg2.length: " + v2.length);
        }

        if (v1 == v2) {
            return 0;
        }

        for (int i = 0; i < v1.length; i++) {
            if (v1[i] != v2[i]) {
                return v1[i] - v2[i];
            }
        }

        return 0;
    }

    /**
     * 
     * @param v1
     * @param v2
     * @return
     * 
     * @throws NullPointerException
     *             두 개의 입력값 중 하나라도 <code>null</code>인 경우.
     */
    public static int comparable(double[] v1, double[] v2) {

        if (v1 == null || v2 == null) {
            throw new NullPointerException("arg1: " + v1 + ", arg2: " + v2);
        }

        if (v1.length != v2.length) {
            throw new IllegalArgumentException("두 입력값의 길이가 다릅니다. arg1.length: " + v1.length + ", arg2.length: " + v2.length);
        }

        if (v1 == v2) {
            return 0;
        }

        for (int i = 0; i < v1.length; i++) {
            if (v1[i] != v2[i]) {
                return v1[i] > v2[i] ? 1 : -1;
            }
        }

        return 0;
    }

    /**
     * 
     * @param v1
     * @param v2
     * @return
     * 
     * @throws NullPointerException
     *             두 개의 입력값 중 하나라도 <code>null</code>인 경우.
     */
    public static int comparable(float[] v1, float[] v2) {

        if (v1 == null || v2 == null) {
            throw new NullPointerException("arg1: " + v1 + ", arg2: " + v2);
        }

        if (v1.length != v2.length) {
            throw new IllegalArgumentException("두 입력값의 길이가 다릅니다. arg1.length: " + v1.length + ", arg2.length: " + v2.length);
        }

        if (v1 == v2) {
            return 0;
        }

        for (int i = 0; i < v1.length; i++) {
            if (v1[i] != v2[i]) {
                return v1[i] > v2[i] ? 1 : -1;
            }
        }

        return 0;
    }

    /**
     * 
     * @param v1
     * @param v2
     * @return
     * 
     * @throws NullPointerException
     *             두 개의 입력값 중 하나라도 <code>null</code>인 경우.
     */
    public static int comparable(int[] v1, int[] v2) {

        if (v1 == null || v2 == null) {
            throw new NullPointerException("arg1: " + v1 + ", arg2: " + v2);
        }

        if (v1.length != v2.length) {
            throw new IllegalArgumentException("두 입력값의 길이가 다릅니다. arg1.length: " + v1.length + ", arg2.length: " + v2.length);
        }

        if (v1 == v2) {
            return 0;
        }

        for (int i = 0; i < v1.length; i++) {
            if (v1[i] != v2[i]) {
                return v1[i] - v2[i];
            }
        }

        return 0;
    }

    /**
     * 
     * @param v1
     * @param v2
     * @return
     * 
     * @throws NullPointerException
     *             두 개의 입력값 중 하나라도 <code>null</code>인 경우.
     */
    public static <T extends Comparable<T>> int comparable(T[] v1, T[] v2) {

        if (v1 == null || v2 == null) {
            throw new NullPointerException("arg1: " + v1 + ", arg2: " + v2);
        }

        if (v1.length != v2.length) {
            throw new IllegalArgumentException("두 입력값의 길이가 다릅니다. arg1.length: " + v1.length + ", arg2.length: " + v2.length);
        }

        if (v1 == v2) {
            return 0;
        }

        int c = 0;
        for (int i = 0; i < v1.length; i++) {
            if ((c = v1[i].compareTo(v2[i])) != 0) {
                return c;
            }
        }

        return 0;
    }

    public static String concatenate(byte[] array, char delimiter) {
        if (array == null || array.length < 1) {
            return null;
        }

        StringBuffer sb = new StringBuffer();

        int i = 0;

        sb.append(array[i++]);

        for (; i < array.length; i++) {
            sb.append(delimiter);
            sb.append(array[i]);
        }

        return sb.toString();
    }

    public static String concatenate(char[] array, char delimiter) {
        if (array == null || array.length < 1) {
            return null;
        }

        char[] cs = new char[2 * array.length - 1];
        int i = 0;

        cs[i] = array[i++];

        for (; i < array.length; i++) {
            cs[2 * i - 1] = delimiter;
            cs[2 * i] = array[i];
        }

        return String.valueOf(cs);
    }

    public static String concatenate(float[] array, char delimiter) {
        if (array == null || array.length < 1) {
            return null;
        }

        StringBuffer sb = new StringBuffer();

        int i = 0;

        sb.append(array[i++]);

        for (; i < array.length; i++) {
            sb.append(delimiter);
            sb.append(array[i]);
        }

        return sb.toString();
    }

    public static String concatenate(int[] array, char delimiter) {
        if (array == null || array.length < 1) {
            return null;
        }

        StringBuffer sb = new StringBuffer();

        int i = 0;

        sb.append(array[i++]);

        for (; i < array.length; i++) {
            sb.append(delimiter);
            sb.append(array[i]);
        }

        return sb.toString();
    }

    /**
     * Returns a concatenated string of elements of <b><code>the given array</code></b>.
     * 
     * @param array
     * @param delimiter
     * @return
     */
    public static <T> String concatenate(T[] array, char delimiter) {
        if (array == null || array.length < 1) {
            return null;
        }

        char[][] chars = new char[array.length][];

        chars[0] = (array[0] != null ? array[0] : "null").toString().toCharArray();

        for (int i = 1; i < array.length; i++) {
            chars[i] = prepend((array[i] != null ? array[i] : "null").toString().trim().toCharArray(), delimiter);
        }

        return new String(merge(chars));
    }

    /**
     * Returns a concatenated string of elements of <b><code>the given array</code></b>.
     * 
     * @param array
     * @param delimiter
     * @return
     */
    public static <T> String concatenate(T[] array, String delimiter) {
        if (array == null || array.length < 1) {
            return null;
        }

        char[] delim = delimiter != null ? delimiter.toCharArray() : new char[] {};
        char[][] chars = new char[array.length][];

        chars[0] = (array[0] != null ? array[0] : "null").toString().toCharArray();

        for (int i = 1; i < array.length; i++) {
            chars[i] = merge(delim, (array[i] != null ? array[i] : "null").toString().trim().toCharArray());
        }

        return new String(merge(chars));
    }

    /**
     * 주어진 배열에 찾고자 하는 값이 있는지 여부를 반환합니다.
     * 
     * @param array
     * @param value
     * @return
     */
    public static boolean contains(boolean[] array, boolean value) {
        if (array == null) {
            return false;
        }

        for (boolean b : array) {
            if (b == value) {
                return true;
            }
        }

        return false;
    }

    /**
     * 주어진 배열에 찾고자 하는 값이 있는지 여부를 반환합니다.
     * 
     * @param array
     * @param value
     * @return
     */
    public static boolean contains(byte[] array, byte value) {
        if (array == null) {
            return false;
        }

        for (byte b : array) {
            if (b == value) {
                return true;
            }
        }

        return false;
    }

    /**
     * 주어진 배열에 찾고자 하는 값이 있는지 여부를 반환합니다.
     * 
     * @param array
     * @param value
     * @return
     */
    public static boolean contains(char[] array, char value) {
        if (array == null) {
            return false;
        }

        for (char c : array) {
            if (c == value) {
                return true;
            }
        }

        return false;
    }

    /**
     * 주어진 배열에 찾고자 하는 값이 있는지 여부를 반환합니다.
     * 
     * @param array
     * @param value
     * @return
     */
    public static boolean contains(double[] array, double value) {
        if (array == null) {
            return false;
        }

        for (double d : array) {
            if (d == value) {
                return true;
            }
        }

        return false;
    }

    /**
     * 주어진 배열에 찾고자 하는 값이 있는지 여부를 반환합니다.
     * 
     * @param array
     * @param value
     * @return
     */
    public static boolean contains(float[] array, float value) {
        if (array == null) {
            return false;
        }

        for (float f : array) {
            if (f == value) {
                return true;
            }
        }

        return false;
    }

    /**
     * 주어진 배열에 찾고자 하는 값이 있는지 여부를 반환합니다.
     * 
     * @param array
     * @param value
     * @return
     */
    public static boolean contains(int[] array, int value) {
        if (array == null) {
            return false;
        }

        for (int i : array) {
            if (i == value) {
                return true;
            }
        }

        return false;
    }

    /**
     * 주어진 배열에 찾고자 하는 값이 있는지 여부를 반환합니다.
     * 
     * @param array
     * @param value
     * @return
     */
    public static boolean contains(long[] array, long value) {
        if (array == null) {
            return false;
        }

        for (long l : array) {
            if (l == value) {
                return true;
            }
        }

        return false;
    }

    /**
     * 주어진 배열에 찾고자 하는 값이 있는지 여부를 반환합니다.
     * 
     * @param array
     * @param value
     * @return
     */
    public static boolean contains(short[] array, short value) {
        if (array == null) {
            return false;
        }

        for (short s : array) {
            if (s == value) {
                return true;
            }
        }

        return false;
    }

    /**
     * 주어진 배열에 찾고자 하는 값이 있는지 여부를 반환합니다.
     * 
     * @param array
     * @param value
     * @return
     */
    public static <T> boolean contains(T[] array, T value) {
        if (array == null) {
            return false;
        }

        for (T t : array) {
            if (CheckUtils.equals(t, value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 주어진 배열 내의 원소들과 주어진 원소사이의 포함관계여부를 반환합니다.<br>
     * 
     * @param array
     * @param t
     * @param container
     * @return
     */
    public static <T> Result<T> containsPart(T[] array, T t, IContainer<T> container) {
        if (array == null || t == null) {
            return new Result<T>();
        }

        if (container == null) {
            container = new DefaultContainer<T>();
        }

        for (T e : array) {
            if (container.contains(e, t)) {
                return new Result<T>(e, true);
            }
        }
        return new Result<T>();
    }

    /**
     * 배열에서 원하는 index에 해당하는 데이터로 이루어진 배열을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2023. 8. 2.		박준홍			최초 작성
     * </pre>
     *
     * @param arr
     *            복사할 배열
     * @param indice
     *            복사할 데이터의 index.
     * @return
     * @throws ArrayIndexOutOfBoundsException
     * 
     * @since 2023. 8. 2.
     * @version 2.0.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static boolean[] copy(boolean[] arr, int... indice) throws ArrayIndexOutOfBoundsException {
        boolean[] newArr = new boolean[indice.length];

        for (int i = 0; i < indice.length; i++) {
            newArr[i] = arr[indice[i]];
        }

        return newArr;
    }

    /**
     * 배열에서 원하는 index에 해당하는 데이터로 이루어진 배열을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2023. 8. 2.		박준홍			최초 작성
     * </pre>
     *
     * @param arr
     *            복사할 배열
     * @param indice
     *            복사할 데이터의 index.
     * @return
     * @throws ArrayIndexOutOfBoundsException
     * 
     * @since 2023. 8. 2.
     * @version 2.0.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static byte[] copy(byte[] arr, int... indice) throws ArrayIndexOutOfBoundsException {
        byte[] newArr = new byte[indice.length];

        for (int i = 0; i < indice.length; i++) {
            newArr[i] = arr[indice[i]];
        }

        return newArr;
    }

    /**
     * 배열에서 원하는 index에 해당하는 데이터로 이루어진 배열을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2023. 8. 2.		박준홍			최초 작성
     * </pre>
     *
     * @param arr
     *            복사할 배열
     * @param indice
     *            복사할 데이터의 index.
     * @return
     * @throws ArrayIndexOutOfBoundsException
     * 
     * @since 2023. 8. 2.
     * @version 2.0.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static char[] copy(char[] arr, int... indice) throws ArrayIndexOutOfBoundsException {
        char[] newArr = new char[indice.length];

        for (int i = 0; i < indice.length; i++) {
            newArr[i] = arr[indice[i]];
        }

        return newArr;
    }

    /**
     * 배열에서 원하는 index에 해당하는 데이터로 이루어진 배열을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2023. 8. 2.		박준홍			최초 작성
     * </pre>
     *
     * @param arr
     *            복사할 배열
     * @param indice
     *            복사할 데이터의 index.
     * @return
     * @throws ArrayIndexOutOfBoundsException
     * 
     * @since 2023. 8. 2.
     * @version 2.0.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static double[] copy(double[] arr, int... indice) throws ArrayIndexOutOfBoundsException {
        double[] newArr = new double[indice.length];

        for (int i = 0; i < indice.length; i++) {
            newArr[i] = arr[indice[i]];
        }

        return newArr;
    }

    /**
     * 배열에서 원하는 index에 해당하는 데이터로 이루어진 배열을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2023. 8. 2.		박준홍			최초 작성
     * </pre>
     *
     * @param arr
     *            복사할 배열
     * @param indice
     *            복사할 데이터의 index.
     * @return
     * @throws ArrayIndexOutOfBoundsException
     * 
     * @since 2023. 8. 2.
     * @version 2.0.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static float[] copy(float[] arr, int... indice) throws ArrayIndexOutOfBoundsException {
        float[] newArr = new float[indice.length];

        for (int i = 0; i < indice.length; i++) {
            newArr[i] = arr[indice[i]];
        }

        return newArr;
    }

    /**
     * 배열에서 원하는 index에 해당하는 데이터로 이루어진 배열을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2023. 8. 2.		박준홍			최초 작성
     * </pre>
     *
     * @param arr
     *            복사할 배열
     * @param indice
     *            복사할 데이터의 index.
     * @return
     * @throws ArrayIndexOutOfBoundsException
     * 
     * @since 2023. 8. 2.
     * @version 2.0.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static int[] copy(int[] arr, int... indice) throws ArrayIndexOutOfBoundsException {
        int[] newArr = new int[indice.length];

        for (int i = 0; i < indice.length; i++) {
            newArr[i] = arr[indice[i]];
        }

        return newArr;
    }

    /**
     * 배열에서 원하는 index에 해당하는 데이터로 이루어진 배열을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2023. 8. 2.		박준홍			최초 작성
     * </pre>
     *
     * @param arr
     *            복사할 배열
     * @param indice
     *            복사할 데이터의 index.
     * @return
     * @throws ArrayIndexOutOfBoundsException
     * 
     * @since 2023. 8. 2.
     * @version 2.0.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static long[] copy(long[] arr, int... indice) throws ArrayIndexOutOfBoundsException {
        long[] newArr = new long[indice.length];

        for (int i = 0; i < indice.length; i++) {
            newArr[i] = arr[indice[i]];
        }

        return newArr;
    }

    /**
     * 배열에서 원하는 index에 해당하는 데이터로 이루어진 배열을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2023. 8. 2.		박준홍			최초 작성
     * </pre>
     *
     * @param arr
     *            복사할 배열
     * @param indice
     *            복사할 데이터의 index.
     * @return
     * @throws ArrayIndexOutOfBoundsException
     * 
     * @since 2023. 8. 2.
     * @version 2.0.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static short[] copy(short[] arr, int... indice) throws ArrayIndexOutOfBoundsException {
        short[] newArr = new short[indice.length];

        for (int i = 0; i < indice.length; i++) {
            newArr[i] = arr[indice[i]];
        }

        return newArr;
    }

    /**
     * 배열에서 원하는 index에 해당하는 데이터로 이루어진 배열을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2023. 8. 2.		박준홍			최초 작성
     * </pre>
     *
     * @param arr
     *            복사할 배열
     * @param indice
     *            복사할 데이터의 index.
     * @return
     * @throws ArrayIndexOutOfBoundsException
     * 
     * @since 2023. 8. 2.
     * @version 2.0.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <T> T[] copy(T[] arr, int... indice) throws ArrayIndexOutOfBoundsException {
        T[] newArr = (T[]) Array.newInstance(arr.getClass().getComponentType().getComponentType(), indice.length);

        for (int i = 0; i < indice.length; i++) {
            newArr[i] = arr[indice[i]];
        }

        return newArr;
    }

    /**
     * <p>
     * <b><font color="RED">free against JDK 1.6 </font></b>
     * </p>
     * 
     * Copies the specified array, truncating or padding with <tt>false</tt> (if necessary) so the copy has the
     * specified length. For all indices that are valid in both the original array and the copy, the two arrays will
     * contain identical values. For any indices that are valid in the copy but not the original, the copy will contain
     * <tt>false</tt>. Such indices will exist if and only if the specified length is greater than that of the original
     * array.
     * 
     * @param original
     *            the array to be copied
     * @param newLength
     *            the length of the copy to be returned
     * @return a copy of the original array, truncated or padded with false elements to obtain the specified length
     * @throws NegativeArraySizeException
     *             if <tt>newLength</tt> is negative
     * @throws NullPointerException
     *             if <tt>original</tt> is null
     * 
     */
    public static boolean[] copyOf(boolean[] original, int newLength) {
        boolean[] copy = new boolean[newLength];
        System.arraycopy(original, 0, copy, 0, Math.min(original.length, newLength));
        return copy;
    }

    /**
     * <p>
     * <b><font color="RED">free against JDK 1.6 </font></b>
     * </p>
     * 
     * Copies the specified array, truncating or padding with zeros (if necessary) so the copy has the specified length.
     * For all indices that are valid in both the original array and the copy, the two arrays will contain identical
     * values. For any indices that are valid in the copy but not the original, the copy will contain <tt>(byte)0</tt>.
     * Such indices will exist if and only if the specified length is greater than that of the original array.
     * 
     * @param original
     *            the array to be copied
     * @param newLength
     *            the length of the copy to be returned
     * @return a copy of the original array, truncated or padded with zeros to obtain the specified length
     * @throws NegativeArraySizeException
     *             if <tt>newLength</tt> is negative
     * @throws NullPointerException
     *             if <tt>original</tt> is null
     * 
     */
    public static byte[] copyOf(byte[] original, int newLength) {
        byte[] copy = new byte[newLength];
        System.arraycopy(original, 0, copy, 0, Math.min(original.length, newLength));
        return copy;
    }

    /**
     * <p>
     * <b><font color="RED">free against JDK 1.6 </font></b>
     * </p>
     * 
     * Copies the specified array, truncating or padding with null characters (if necessary) so the copy has the
     * specified length. For all indices that are valid in both the original array and the copy, the two arrays will
     * contain identical values. For any indices that are valid in the copy but not the original, the copy will contain
     * <tt>'\\u000'</tt>. Such indices will exist if and only if the specified length is greater than that of the
     * original array.
     * 
     * @param original
     *            the array to be copied
     * @param newLength
     *            the length of the copy to be returned
     * @return a copy of the original array, truncated or padded with null characters to obtain the specified length
     * @throws NegativeArraySizeException
     *             if <tt>newLength</tt> is negative
     * @throws NullPointerException
     *             if <tt>original</tt> is null
     * 
     */
    public static char[] copyOf(char[] original, int newLength) {
        char[] copy = new char[newLength];
        System.arraycopy(original, 0, copy, 0, Math.min(original.length, newLength));
        return copy;
    }

    /**
     * <p>
     * <b><font color="RED">free against JDK 1.6 </font></b>
     * </p>
     * 
     * Copies the specified array, truncating or padding with zeros (if necessary) so the copy has the specified length.
     * For all indices that are valid in both the original array and the copy, the two arrays will contain identical
     * values. For any indices that are valid in the copy but not the original, the copy will contain <tt>0d</tt>. Such
     * indices will exist if and only if the specified length is greater than that of the original array.
     * 
     * @param original
     *            the array to be copied
     * @param newLength
     *            the length of the copy to be returned
     * @return a copy of the original array, truncated or padded with zeros to obtain the specified length
     * @throws NegativeArraySizeException
     *             if <tt>newLength</tt> is negative
     * @throws NullPointerException
     *             if <tt>original</tt> is null
     * 
     */
    public static double[] copyOf(double[] original, int newLength) {
        double[] copy = new double[newLength];
        System.arraycopy(original, 0, copy, 0, Math.min(original.length, newLength));
        return copy;
    }

    /**
     * <p>
     * <b><font color="RED">free against JDK 1.6 </font></b>
     * </p>
     * 
     * Copies the specified array, truncating or padding with zeros (if necessary) so the copy has the specified length.
     * For all indices that are valid in both the original array and the copy, the two arrays will contain identical
     * values. For any indices that are valid in the copy but not the original, the copy will contain <tt>0f</tt>. Such
     * indices will exist if and only if the specified length is greater than that of the original array.
     * 
     * @param original
     *            the array to be copied
     * @param newLength
     *            the length of the copy to be returned
     * @return a copy of the original array, truncated or padded with zeros to obtain the specified length
     * @throws NegativeArraySizeException
     *             if <tt>newLength</tt> is negative
     * @throws NullPointerException
     *             if <tt>original</tt> is null
     * 
     */
    public static float[] copyOf(float[] original, int newLength) {
        float[] copy = new float[newLength];
        System.arraycopy(original, 0, copy, 0, Math.min(original.length, newLength));
        return copy;
    }

    /**
     * <p>
     * <b><font color="RED">free against JDK 1.6 </font></b>
     * </p>
     * 
     * Copies the specified array, truncating or padding with zeros (if necessary) so the copy has the specified length.
     * For all indices that are valid in both the original array and the copy, the two arrays will contain identical
     * values. For any indices that are valid in the copy but not the original, the copy will contain <tt>0</tt>. Such
     * indices will exist if and only if the specified length is greater than that of the original array.
     * 
     * @param original
     *            the array to be copied
     * @param newLength
     *            the length of the copy to be returned
     * @return a copy of the original array, truncated or padded with zeros to obtain the specified length
     * @throws NegativeArraySizeException
     *             if <tt>newLength</tt> is negative
     * @throws NullPointerException
     *             if <tt>original</tt> is null
     * 
     */
    public static int[] copyOf(int[] original, int newLength) {
        int[] copy = new int[newLength];
        System.arraycopy(original, 0, copy, 0, Math.min(original.length, newLength));
        return copy;
    }

    /**
     * <p>
     * <b><font color="RED">free against JDK 1.6 </font></b>
     * </p>
     * 
     * Copies the specified array, truncating or padding with zeros (if necessary) so the copy has the specified length.
     * For all indices that are valid in both the original array and the copy, the two arrays will contain identical
     * values. For any indices that are valid in the copy but not the original, the copy will contain <tt>0L</tt>. Such
     * indices will exist if and only if the specified length is greater than that of the original array.
     * 
     * @param original
     *            the array to be copied
     * @param newLength
     *            the length of the copy to be returned
     * @return a copy of the original array, truncated or padded with zeros to obtain the specified length
     * @throws NegativeArraySizeException
     *             if <tt>newLength</tt> is negative
     * @throws NullPointerException
     *             if <tt>original</tt> is null
     * 
     */
    public static long[] copyOf(long[] original, int newLength) {
        long[] copy = new long[newLength];
        System.arraycopy(original, 0, copy, 0, Math.min(original.length, newLength));
        return copy;
    }

    /**
     * <p>
     * <b><font color="RED">free against JDK 1.6 </font></b>
     * </p>
     * 
     * Copies the specified array, truncating or padding with zeros (if necessary) so the copy has the specified length.
     * For all indices that are valid in both the original array and the copy, the two arrays will contain identical
     * values. For any indices that are valid in the copy but not the original, the copy will contain <tt>(short)0</tt>.
     * Such indices will exist if and only if the specified length is greater than that of the original array.
     * 
     * @param original
     *            the array to be copied
     * @param newLength
     *            the length of the copy to be returned
     * @return a copy of the original array, truncated or padded with zeros to obtain the specified length
     * @throws NegativeArraySizeException
     *             if <tt>newLength</tt> is negative
     * @throws NullPointerException
     *             if <tt>original</tt> is null
     * 
     */
    public static short[] copyOf(short[] original, int newLength) {
        short[] copy = new short[newLength];
        System.arraycopy(original, 0, copy, 0, Math.min(original.length, newLength));
        return copy;
    }

    /**
     * <p>
     * <b><font color="RED">free against JDK 1.6 </font></b>
     * </p>
     * 
     * Copies the specified array, truncating or padding with nulls (if necessary) so the copy has the specified length.
     * For all indices that are valid in both the original array and the copy, the two arrays will contain identical
     * values. For any indices that are valid in the copy but not the original, the copy will contain <tt>null</tt>.
     * Such indices will exist if and only if the specified length is greater than that of the original array. The
     * resulting array is of exactly the same class as the original array.
     * 
     * @param original
     *            the array to be copied
     * @param newLength
     *            the length of the copy to be returned
     * @return a copy of the original array, truncated or padded with nulls to obtain the specified length
     * @throws NegativeArraySizeException
     *             if <tt>newLength</tt> is negative
     * @throws NullPointerException
     *             if <tt>original</tt> is null
     * 
     * 
     */
    public static <T> T[] copyOf(T[] original, int newLength) {
        return (T[]) copyOf(original, newLength, original.getClass());
    }

    /**
     * deep copy <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 8. 19.		박준홍			최초 작성
     * </pre>
     *
     * @param <T>
     * @param original
     * @param newLength
     * @param clone
     *            deep copy 함수
     * @return
     *
     * @since 2021. 8. 19.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <T> T[] copyOf(T[] original, int newLength, Function<T, T> clone) {
        return copyOf(original, newLength, (Class<T[]>) original.getClass(), clone);
    }

    /**
     * <p>
     * <b><font color="RED">free against JDK 1.6 </font></b>
     * </p>
     * 
     * Copies the specified array, truncating or padding with nulls (if necessary) so the copy has the specified length.
     * For all indices that are valid in both the original array and the copy, the two arrays will contain identical
     * values. For any indices that are valid in the copy but not the original, the copy will contain <tt>null</tt>.
     * Such indices will exist if and only if the specified length is greater than that of the original array. The
     * resulting array is of the class <tt>newType</tt>.
     * 
     * @param original
     *            the array to be copied
     * @param newLength
     *            the length of the copy to be returned
     * @param newType
     *            the class of the copy to be returned
     * @return a copy of the original array, truncated or padded with nulls to obtain the specified length
     * @throws NegativeArraySizeException
     *             if <tt>newLength</tt> is negative
     * @throws NullPointerException
     *             if <tt>original</tt> is null
     * @throws ArrayStoreException
     *             if an element copied from <tt>original</tt> is not of a runtime type that can be stored in an array
     *             of class <tt>newType</tt>
     * 
     */
    public static <T, U> T[] copyOf(U[] original, int newLength, Class<? extends T[]> newType) {
        T[] copy = ((Object) newType == (Object) Object[].class) ? (T[]) new Object[newLength] : (T[]) Array.newInstance(newType.getComponentType(), newLength);
        System.arraycopy(original, 0, copy, 0, Math.min(original.length, newLength));
        return copy;
    }

    /**
     * deep copyl <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 8. 19.		박준홍			최초 작성
     * </pre>
     *
     * @param <T>
     * @param <U>
     * @param original
     * @param newLength
     * @param newType
     * @param clone
     *            deep copy 함수
     * @return
     *
     * @since 2021. 8. 19.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <T, U> T[] copyOf(U[] original, int newLength, Class<? extends T[]> newType, Function<U, T> clone) {
        T[] copy = ((Object) newType == (Object) Object[].class) //
                ? (T[]) new Object[newLength] //
                : (T[]) Array.newInstance(newType.getComponentType(), newLength);

        final int newArrlen = Math.min(original.length, newLength);
        for (int i = 0; i < newArrlen; i++) {
            copy[i] = clone.apply(original[i]);
        }

        return copy;
    }

    /**
     * <p>
     * <b><font color="RED">free against JDK 1.6 </font></b>
     * </p>
     * 
     * Copies the specified range of the specified array into a new array. The initial index of the range (<tt>from</tt>
     * ) must lie between zero and <tt>original.length</tt>, inclusive. The value at <tt>original[from]</tt> is placed
     * into the initial element of the copy (unless <tt>from == original.length</tt> or <tt>from == to</tt>). Values
     * from subsequent elements in the original array are placed into subsequent elements in the copy. The final index
     * of the range (<tt>to</tt>), which must be greater than or equal to <tt>from</tt>, may be greater than
     * <tt>original.length</tt>, in which case <tt>false</tt> is placed in all elements of the copy whose index is
     * greater than or equal to <tt>original.length - from</tt>. The length of the returned array will be
     * <tt>to - from</tt>.
     * 
     * @param original
     *            the array from which a range is to be copied
     * @param from
     *            the initial index of the range to be copied, inclusive
     * @param to
     *            the final index of the range to be copied, exclusive. (This index may lie outside the array.)
     * @return a new array containing the specified range from the original array, truncated or padded with false
     *         elements to obtain the required length
     * @throws ArrayIndexOutOfBoundsException
     *             if <tt>from &lt; 0</tt> or <tt>from &gt; original.length()</tt>
     * @throws IllegalArgumentException
     *             if <tt>from &gt; to</tt>
     * @throws NullPointerException
     *             if <tt>original</tt> is null
     * 
     */
    public static boolean[] copyOfRange(boolean[] original, int from, int to) {
        int newLength = to - from;
        if (newLength < 0)
            throw new IllegalArgumentException(from + " > " + to);
        boolean[] copy = new boolean[newLength];
        System.arraycopy(original, from, copy, 0, Math.min(original.length - from, newLength));
        return copy;
    }

    /**
     * <p>
     * <b><font color="RED">free against JDK 1.6 </font></b>
     * </p>
     * 
     * Copies the specified range of the specified array into a new array. The initial index of the range (<tt>from</tt>
     * ) must lie between zero and <tt>original.length</tt>, inclusive. The value at <tt>original[from]</tt> is placed
     * into the initial element of the copy (unless <tt>from == original.length</tt> or <tt>from == to</tt>). Values
     * from subsequent elements in the original array are placed into subsequent elements in the copy. The final index
     * of the range (<tt>to</tt>), which must be greater than or equal to <tt>from</tt>, may be greater than
     * <tt>original.length</tt>, in which case <tt>(byte)0</tt> is placed in all elements of the copy whose index is
     * greater than or equal to <tt>original.length - from</tt>. The length of the returned array will be
     * <tt>to - from</tt>.
     * 
     * @param original
     *            the array from which a range is to be copied
     * @param from
     *            the initial index of the range to be copied, inclusive
     * @param to
     *            the final index of the range to be copied, exclusive. (This index may lie outside the array.)
     * @return a new array containing the specified range from the original array, truncated or padded with zeros to
     *         obtain the required length
     * @throws ArrayIndexOutOfBoundsException
     *             if <tt>from &lt; 0</tt> or <tt>from &gt; original.length()</tt>
     * @throws IllegalArgumentException
     *             if <tt>from &gt; to</tt>
     * @throws NullPointerException
     *             if <tt>original</tt> is null
     * 
     */
    public static byte[] copyOfRange(byte[] original, int from, int to) {
        int newLength = to - from;
        if (newLength < 0)
            throw new IllegalArgumentException(from + " > " + to);
        byte[] copy = new byte[newLength];
        System.arraycopy(original, from, copy, 0, Math.min(original.length - from, newLength));
        return copy;
    }

    /**
     * <p>
     * <b><font color="RED">free against JDK 1.6 </font></b>
     * </p>
     * 
     * Copies the specified range of the specified array into a new array. The initial index of the range (<tt>from</tt>
     * ) must lie between zero and <tt>original.length</tt>, inclusive. The value at <tt>original[from]</tt> is placed
     * into the initial element of the copy (unless <tt>from == original.length</tt> or <tt>from == to</tt>). Values
     * from subsequent elements in the original array are placed into subsequent elements in the copy. The final index
     * of the range (<tt>to</tt>), which must be greater than or equal to <tt>from</tt>, may be greater than
     * <tt>original.length</tt>, in which case <tt>'\\u000'</tt> is placed in all elements of the copy whose index is
     * greater than or equal to <tt>original.length - from</tt>. The length of the returned array will be
     * <tt>to - from</tt>.
     * 
     * @param original
     *            the array from which a range is to be copied
     * @param from
     *            the initial index of the range to be copied, inclusive
     * @param to
     *            the final index of the range to be copied, exclusive. (This index may lie outside the array.)
     * @return a new array containing the specified range from the original array, truncated or padded with null
     *         characters to obtain the required length
     * @throws ArrayIndexOutOfBoundsException
     *             if <tt>from &lt; 0</tt> or <tt>from &gt; original.length()</tt>
     * @throws IllegalArgumentException
     *             if <tt>from &gt; to</tt>
     * @throws NullPointerException
     *             if <tt>original</tt> is null
     * 
     */
    public static char[] copyOfRange(char[] original, int from, int to) {
        int newLength = to - from;
        if (newLength < 0)
            throw new IllegalArgumentException(from + " > " + to);
        char[] copy = new char[newLength];
        System.arraycopy(original, from, copy, 0, Math.min(original.length - from, newLength));
        return copy;
    }

    /**
     * <p>
     * <b><font color="RED">free against JDK 1.6 </font></b>
     * </p>
     * 
     * Copies the specified range of the specified array into a new array. The initial index of the range (<tt>from</tt>
     * ) must lie between zero and <tt>original.length</tt>, inclusive. The value at <tt>original[from]</tt> is placed
     * into the initial element of the copy (unless <tt>from == original.length</tt> or <tt>from == to</tt>). Values
     * from subsequent elements in the original array are placed into subsequent elements in the copy. The final index
     * of the range (<tt>to</tt>), which must be greater than or equal to <tt>from</tt>, may be greater than
     * <tt>original.length</tt>, in which case <tt>0d</tt> is placed in all elements of the copy whose index is greater
     * than or equal to <tt>original.length - from</tt>. The length of the returned array will be <tt>to - from</tt>.
     * 
     * @param original
     *            the array from which a range is to be copied
     * @param from
     *            the initial index of the range to be copied, inclusive
     * @param to
     *            the final index of the range to be copied, exclusive. (This index may lie outside the array.)
     * @return a new array containing the specified range from the original array, truncated or padded with zeros to
     *         obtain the required length
     * @throws ArrayIndexOutOfBoundsException
     *             if <tt>from &lt; 0</tt> or <tt>from &gt; original.length()</tt>
     * @throws IllegalArgumentException
     *             if <tt>from &gt; to</tt>
     * @throws NullPointerException
     *             if <tt>original</tt> is null
     * 
     */
    public static double[] copyOfRange(double[] original, int from, int to) {
        int newLength = to - from;
        if (newLength < 0)
            throw new IllegalArgumentException(from + " > " + to);
        double[] copy = new double[newLength];
        System.arraycopy(original, from, copy, 0, Math.min(original.length - from, newLength));
        return copy;
    }

    /**
     * <p>
     * <b><font color="RED">free against JDK 1.6 </font></b>
     * </p>
     * 
     * Copies the specified range of the specified array into a new array. The initial index of the range (<tt>from</tt>
     * ) must lie between zero and <tt>original.length</tt>, inclusive. The value at <tt>original[from]</tt> is placed
     * into the initial element of the copy (unless <tt>from == original.length</tt> or <tt>from == to</tt>). Values
     * from subsequent elements in the original array are placed into subsequent elements in the copy. The final index
     * of the range (<tt>to</tt>), which must be greater than or equal to <tt>from</tt>, may be greater than
     * <tt>original.length</tt>, in which case <tt>0f</tt> is placed in all elements of the copy whose index is greater
     * than or equal to <tt>original.length - from</tt>. The length of the returned array will be <tt>to - from</tt>.
     * 
     * @param original
     *            the array from which a range is to be copied
     * @param from
     *            the initial index of the range to be copied, inclusive
     * @param to
     *            the final index of the range to be copied, exclusive. (This index may lie outside the array.)
     * @return a new array containing the specified range from the original array, truncated or padded with zeros to
     *         obtain the required length
     * @throws ArrayIndexOutOfBoundsException
     *             if <tt>from &lt; 0</tt> or <tt>from &gt; original.length()</tt>
     * @throws IllegalArgumentException
     *             if <tt>from &gt; to</tt>
     * @throws NullPointerException
     *             if <tt>original</tt> is null
     * 
     */
    public static float[] copyOfRange(float[] original, int from, int to) {
        int newLength = to - from;
        if (newLength < 0)
            throw new IllegalArgumentException(from + " > " + to);
        float[] copy = new float[newLength];
        System.arraycopy(original, from, copy, 0, Math.min(original.length - from, newLength));
        return copy;
    }

    /**
     * <p>
     * <b><font color="RED">free against JDK 1.6 </font></b>
     * </p>
     * 
     * Copies the specified range of the specified array into a new array. The initial index of the range (<tt>from</tt>
     * ) must lie between zero and <tt>original.length</tt>, inclusive. The value at <tt>original[from]</tt> is placed
     * into the initial element of the copy (unless <tt>from == original.length</tt> or <tt>from == to</tt>). Values
     * from subsequent elements in the original array are placed into subsequent elements in the copy. The final index
     * of the range (<tt>to</tt>), which must be greater than or equal to <tt>from</tt>, may be greater than
     * <tt>original.length</tt>, in which case <tt>0</tt> is placed in all elements of the copy whose index is greater
     * than or equal to <tt>original.length - from</tt>. The length of the returned array will be <tt>to - from</tt>.
     * 
     * @param original
     *            the array from which a range is to be copied
     * @param from
     *            the initial index of the range to be copied, inclusive
     * @param to
     *            the final index of the range to be copied, exclusive. (This index may lie outside the array.)
     * @return a new array containing the specified range from the original array, truncated or padded with zeros to
     *         obtain the required length
     * @throws ArrayIndexOutOfBoundsException
     *             if <tt>from &lt; 0</tt> or <tt>from &gt; original.length()</tt>
     * @throws IllegalArgumentException
     *             if <tt>from &gt; to</tt>
     * @throws NullPointerException
     *             if <tt>original</tt> is null
     * 
     */
    public static int[] copyOfRange(int[] original, int from, int to) {
        int newLength = to - from;
        if (newLength < 0)
            throw new IllegalArgumentException(from + " > " + to);
        int[] copy = new int[newLength];
        System.arraycopy(original, from, copy, 0, Math.min(original.length - from, newLength));
        return copy;
    }

    /**
     * <p>
     * <b><font color="RED">free against JDK 1.6 </font></b>
     * </p>
     * 
     * Copies the specified range of the specified array into a new array. The initial index of the range (<tt>from</tt>
     * ) must lie between zero and <tt>original.length</tt>, inclusive. The value at <tt>original[from]</tt> is placed
     * into the initial element of the copy (unless <tt>from == original.length</tt> or <tt>from == to</tt>). Values
     * from subsequent elements in the original array are placed into subsequent elements in the copy. The final index
     * of the range (<tt>to</tt>), which must be greater than or equal to <tt>from</tt>, may be greater than
     * <tt>original.length</tt>, in which case <tt>0L</tt> is placed in all elements of the copy whose index is greater
     * than or equal to <tt>original.length - from</tt>. The length of the returned array will be <tt>to - from</tt>.
     * 
     * @param original
     *            the array from which a range is to be copied
     * @param from
     *            the initial index of the range to be copied, inclusive
     * @param to
     *            the final index of the range to be copied, exclusive. (This index may lie outside the array.)
     * @return a new array containing the specified range from the original array, truncated or padded with zeros to
     *         obtain the required length
     * @throws ArrayIndexOutOfBoundsException
     *             if <tt>from &lt; 0</tt> or <tt>from &gt; original.length()</tt>
     * @throws IllegalArgumentException
     *             if <tt>from &gt; to</tt>
     * @throws NullPointerException
     *             if <tt>original</tt> is null
     * 
     */
    public static long[] copyOfRange(long[] original, int from, int to) {
        int newLength = to - from;
        if (newLength < 0)
            throw new IllegalArgumentException(from + " > " + to);
        long[] copy = new long[newLength];
        System.arraycopy(original, from, copy, 0, Math.min(original.length - from, newLength));
        return copy;
    }

    /**
     * <p>
     * <b><font color="RED">free against JDK 1.6 </font></b>
     * </p>
     * 
     * Copies the specified range of the specified array into a new array. The initial index of the range (<tt>from</tt>
     * ) must lie between zero and <tt>original.length</tt>, inclusive. The value at <tt>original[from]</tt> is placed
     * into the initial element of the copy (unless <tt>from == original.length</tt> or <tt>from == to</tt>). Values
     * from subsequent elements in the original array are placed into subsequent elements in the copy. The final index
     * of the range (<tt>to</tt>), which must be greater than or equal to <tt>from</tt>, may be greater than
     * <tt>original.length</tt>, in which case <tt>(short)0</tt> is placed in all elements of the copy whose index is
     * greater than or equal to <tt>original.length - from</tt>. The length of the returned array will be
     * <tt>to - from</tt>.
     * 
     * @param original
     *            the array from which a range is to be copied
     * @param from
     *            the initial index of the range to be copied, inclusive
     * @param to
     *            the final index of the range to be copied, exclusive. (This index may lie outside the array.)
     * @return a new array containing the specified range from the original array, truncated or padded with zeros to
     *         obtain the required length
     * @throws ArrayIndexOutOfBoundsException
     *             if <tt>from &lt; 0</tt> or <tt>from &gt; original.length()</tt>
     * @throws IllegalArgumentException
     *             if <tt>from &gt; to</tt>
     * @throws NullPointerException
     *             if <tt>original</tt> is null
     * 
     */
    public static short[] copyOfRange(short[] original, int from, int to) {
        int newLength = to - from;
        if (newLength < 0)
            throw new IllegalArgumentException(from + " > " + to);
        short[] copy = new short[newLength];
        System.arraycopy(original, from, copy, 0, Math.min(original.length - from, newLength));
        return copy;
    }

    /**
     * <p>
     * <b><font color="RED">free against JDK 1.6 </font></b>
     * </p>
     * 
     * Copies the specified range of the specified array into a new array. The initial index of the range (<tt>from</tt>
     * ) must lie between zero and <tt>original.length</tt>, inclusive. The value at <tt>original[from]</tt> is placed
     * into the initial element of the copy (unless <tt>from == original.length</tt> or <tt>from == to</tt>). Values
     * from subsequent elements in the original array are placed into subsequent elements in the copy. The final index
     * of the range (<tt>to</tt>), which must be greater than or equal to <tt>from</tt>, may be greater than
     * <tt>original.length</tt>, in which case <tt>null</tt> is placed in all elements of the copy whose index is
     * greater than or equal to <tt>original.length - from</tt>. The length of the returned array will be
     * <tt>to - from</tt>.
     * <p>
     * The resulting array is of exactly the same class as the original array.
     * 
     * @param original
     *            the array from which a range is to be copied
     * @param from
     *            the initial index of the range to be copied, inclusive
     * @param to
     *            the final index of the range to be copied, exclusive. (This index may lie outside the array.)
     * @return a new array containing the specified range from the original array, truncated or padded with nulls to
     *         obtain the required length
     * @throws ArrayIndexOutOfBoundsException
     *             if <tt>from &lt; 0</tt> or <tt>from &gt; original.length()</tt>
     * @throws IllegalArgumentException
     *             if <tt>from &gt; to</tt>
     * @throws NullPointerException
     *             if <tt>original</tt> is null
     * 
     */
    public static <T> T[] copyOfRange(T[] original, int from, int to) {
        return copyOfRange(original, from, to, (Class<T[]>) original.getClass());
    }

    /**
     * deep copy <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 8. 19.		박준홍			최초 작성
     * </pre>
     *
     * @param <T>
     * @param original
     * @param from
     * @param to
     * @param clone
     *            deep copy 함수
     * @return
     *
     * @since 2021. 8. 19.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <T> T[] copyOfRange(T[] original, int from, int to, Function<T, T> clone) {
        return copyOfRange(original, from, to, (Class<T[]>) original.getClass(), clone);
    }

    /**
     * <p>
     * <b><font color="RED">free against JDK 1.6 </font></b>
     * </p>
     * 
     * Copies the specified range of the specified array into a new array. The initial index of the range (<tt>from</tt>
     * ) must lie between zero and <tt>original.length</tt>, inclusive. The value at <tt>original[from]</tt> is placed
     * into the initial element of the copy (unless <tt>from == original.length</tt> or <tt>from == to</tt>). Values
     * from subsequent elements in the original array are placed into subsequent elements in the copy. The final index
     * of the range (<tt>to</tt>), which must be greater than or equal to <tt>from</tt>, may be greater than
     * <tt>original.length</tt>, in which case <tt>null</tt> is placed in all elements of the copy whose index is
     * greater than or equal to <tt>original.length - from</tt>. The length of the returned array will be
     * <tt>to - from</tt>. The resulting array is of the class <tt>newType</tt>.
     * 
     * @param original
     *            the array from which a range is to be copied
     * @param from
     *            the initial index of the range to be copied, inclusive
     * @param to
     *            the final index of the range to be copied, exclusive. (This index may lie outside the array.)
     * @param newType
     *            the class of the copy to be returned
     * @return a new array containing the specified range from the original array, truncated or padded with nulls to
     *         obtain the required length
     * @throws ArrayIndexOutOfBoundsException
     *             if <tt>from &lt; 0</tt> or <tt>from &gt; original.length()</tt>
     * @throws IllegalArgumentException
     *             if <tt>from &gt; to</tt>
     * @throws NullPointerException
     *             if <tt>original</tt> is null
     * @throws ArrayStoreException
     *             if an element copied from <tt>original</tt> is not of a runtime type that can be stored in an array
     *             of class <tt>newType</tt>.
     * 
     */
    public static <T, U> T[] copyOfRange(U[] original, int from, int to, Class<? extends T[]> newType) {
        int newLength = to - from;
        if (newLength < 0)
            throw new IllegalArgumentException(from + " > " + to);
        T[] copy = ((Object) newType == (Object) Object[].class) ? (T[]) new Object[newLength] : (T[]) Array.newInstance(newType.getComponentType(), newLength);
        System.arraycopy(original, from, copy, 0, Math.min(original.length - from, newLength));
        return copy;
    }

    /**
     * deep copy. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 8. 19.		박준홍			최초 작성
     * </pre>
     *
     * @param <T>
     * @param <U>
     * @param original
     * @param from
     * @param to
     * @param newType
     * @param clone
     *            deep copy 함수
     * @return
     *
     * @since 2021. 8. 19.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <T, U> T[] copyOfRange(U[] original, int from, int to, Class<? extends T[]> newType, Function<U, T> clone) {
        int newLength = to - from;
        if (newLength < 0)
            throw new IllegalArgumentException(from + " > " + to);
        T[] copy = ((Object) newType == (Object) Object[].class) ? (T[]) new Object[newLength] : (T[]) Array.newInstance(newType.getComponentType(), newLength);

        final int newArrlen = Math.min(original.length, newLength);
        for (int i = 0; i < newArrlen; i++) {
            copy[i] = clone.apply(original[i]);
        }

        return copy;
    }

    /**
     * 배열에 주어진 값이 포함되어 있는 개수를 반환합니다.
     * 
     * @param array
     * @param value
     * @return <BR>
     * @since 2012. 03. 27.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static int countOf(boolean[] array, boolean value) {

        if (array != null) {
            int count = 0;

            for (boolean v : array) {
                if (v == value) {
                    count++;
                }
            }

            return count;
        } else {
            return 0;
        }
    }

    /**
     * 배열에 주어진 값이 포함되어 있는 개수를 반환합니다.
     * 
     * @param array
     * @param value
     * @return <BR>
     * @since 2012. 03. 27.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static int countOf(byte[] array, byte value) {

        if (array != null) {
            int count = 0;

            for (byte v : array) {
                if (v == value) {
                    count++;
                }
            }

            return count;
        } else {
            return 0;
        }
    }

    /**
     * 배열에 주어진 값이 포함되어 있는 개수를 반환합니다.
     * 
     * @param array
     * @param value
     * @return <BR>
     * @since 2012. 03. 27.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static int countOf(char[] array, char value) {

        if (array != null) {
            int count = 0;

            for (char v : array) {
                if (v == value) {
                    count++;
                }
            }

            return count;
        } else {
            return 0;
        }
    }

    /**
     * 배열에 주어진 값이 포함되어 있는 개수를 반환합니다.
     * 
     * @param array
     * @param value
     * @return <BR>
     * @since 2012. 03. 27.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static int countOf(double[] array, double value) {

        if (array != null) {
            int count = 0;

            for (double v : array) {
                if (v == value) {
                    count++;
                }
            }

            return count;
        } else {
            return 0;
        }
    }

    /**
     * 배열에 주어진 값이 포함되어 있는 개수를 반환합니다.
     * 
     * @param array
     * @param value
     * @return <BR>
     * @since 2012. 03. 27.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static int countOf(float[] array, float value) {

        if (array != null) {
            int count = 0;

            for (float v : array) {
                if (v == value) {
                    count++;
                }
            }

            return count;
        } else {
            return 0;
        }
    }

    /**
     * 배열에 주어진 값이 포함되어 있는 개수를 반환합니다.
     * 
     * @param array
     * @param value
     * @return <BR>
     * @since 2012. 03. 27.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static int countOf(int[] array, int value) {

        if (array != null) {
            int count = 0;

            for (int v : array) {
                if (v == value) {
                    count++;
                }
            }

            return count;
        } else {
            return 0;
        }
    }

    /**
     * 배열에 주어진 값이 포함되어 있는 개수를 반환합니다.
     * 
     * @param array
     * @param value
     * @return <BR>
     * @since 2012. 03. 27.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static int countOf(long[] array, long value) {

        if (array != null) {
            int count = 0;

            for (long v : array) {
                if (v == value) {
                    count++;
                }
            }

            return count;
        } else {
            return 0;
        }
    }

    /**
     * 배열에 주어진 값이 포함되어 있는 개수를 반환합니다.
     * 
     * @param array
     * @param value
     * @return <BR>
     * @since 2012. 03. 27.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static int countOf(short[] array, short value) {

        if (array != null) {
            int count = 0;

            for (short v : array) {
                if (v == value) {
                    count++;
                }
            }

            return count;
        } else {
            return 0;
        }
    }

    /**
     * 배열에 주어진 값이 포함되어 있는 개수를 반환합니다.
     * 
     * @param array
     * @param value
     * @return <BR>
     * @since 2012. 03. 27.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <T> int countOf(T[] array, T value) {
        return countOf(array, value, null);
    }

    /**
     * 배열에 주어진 값이 포함되어 있는 개수를 반환합니다.
     * 
     * @param array
     * @param value
     * @return <BR>
     * @since 2012. 03. 27.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <T> int countOf(T[] array, T value, IEquivalent<T> equivalent) {

        if (array != null) {
            int count = 0;

            if (equivalent == null) {
                equivalent = new DefaultEquivalent<T>();
            }

            for (T v : array) {
                if (equivalent.equals(v, value)) {
                    count++;
                }
            }

            return count;
        } else {
            return 0;
        }
    }

    public static <T> String elementString(T[] array, String delimiter) {
        StringBuffer sb = new StringBuffer();
        if (array.length > 0) {
            sb.append(array[0]);
        }

        for (int i = 1; i < array.length; i++) {
            sb.append(delimiter);
            sb.append(array[i]);
        }

        return sb.toString();
    }

    /**
     * 주어진 배열에 대해서 index와 데이터 정보를 {@link List}로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 8. 15.		박준홍			최초 작성
     * </pre>
     *
     * @param <T>
     * @param array
     * @return
     *
     * @since 2021. 8. 15.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <T> List<Entry<Integer, T>> entrySet(T[] array) {
        if (array == null) {
            return null;
        }

        List<Entry<Integer, T>> entries = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            entries.add(new EntryValue<Integer, T>(i, array[i]));
        }

        return entries;
    }

    /**
     * 2개의 배열이 동일한지 여부를 확인합니다.
     * 
     * @param array1
     * @param array2
     * 
     * @return
     * 
     * 
     *         <BR>
     * @since 2012. 03. 14.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <T> boolean equals(T[] array1, T[] array2) {
        return equals(array1, array2, null);

    }

    /**
     * 2개의 배열이 동일한지 여부를 확인합니다.
     * 
     * 예제:
     * 
     * <pre>
     * public static void main(String[] args) {
     * 		int[] intArr1 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 };
     * 		int[] intArr2 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 };
     * 		int[] intArr3 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 };
     * 		int[] intArr4 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 };
     * 		int[] intArr5 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 };
     * 		int[] intArr6 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 };
     * 		int[] intArr7 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 };
     * 		int[] intArr8 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 };
     * 		int[] intArr9 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 };
     * 		int[] intArr0 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 };
     * 
     * 		int[][] intArrAll1 = { intArr1, intArr2, intArr3, intArr4, intArr5, intArr6, intArr7, intArr8, intArr9, intArr0 };
     * 
     * 		for( int i = 0; i < intArrAll1.length - 1; i++ ) {
     * 			System.out.println(Arrays.equals(intArrAll1[i], intArrAll1[i + 1]) + "\narray1: "
     * 					+ Arrays.toString(intArrAll1[i]) + "\narray2: " + Arrays.toString(intArrAll1[i + 1]) + "\n---");
     * 		}
     * 
     * 		int[][] intArr11 = { intArr1, intArr6 };
     * 		int[][] intArr22 = { intArr2, intArr7 };
     * 		int[][] intArr33 = { intArr3, intArr8 };
     * 		int[][] intArr44 = { intArr4, intArr9 };
     * 		int[][] intArr55 = { intArr5, intArr0 };
     * 
     * 		int[][][] intArrAll2 = { intArr11, intArr22, intArr33, intArr44, intArr55 };
     * 		for( int i = 0; i < intArrAll2.length - 1; i++ ) {
     * 			System.out.println(ArrayUtils.equals(intArrAll2[i], intArrAll2[i + 1],
     * 					PrimitiveTypeArrayEquivFactory.intEquiv())
     * 					+ "\narray1: "
     * 					+ Arrays.toString(intArrAll2[i])
     * 					+ "\narray2: "
     * 					+ Arrays.toString(intArrAll2[i + 1])
     * 					+ "\n---");
     * 		}
     * 
     * 		int[][][] intArr111 = { intArr11, intArr22 };
     * 		int[][][] intArr222 = { intArr33, intArr44 };
     * 
     * 		IEquivalent<int[][]> eq = new DefaultEquivalent<int[][]>() {
     * 			public boolean equals(int[][] t1, int[][] t2) {
     * 				return ArrayUtils.equals(t1, t2, PrimitiveTypeArrayEquivFactory.intEquiv());
     * 			}
     * 		};
     * 
     * 		System.out.println(ArrayUtils.equals(intArr111, intArr222));
     * 		
     * 		System.out.println(ArrayUtils.equals(intArr111, intArr222, eq));
     * 		
     * 	}
     * 	
     * 	결과:
     * 		
     * 		true
     * 		array1: [1, 2, 3, 4, 5, 6, 7, 8, 9, 0]
     * 		array2: [1, 2, 3, 4, 5, 6, 7, 8, 9, 0]
     * 		---
     * 		true
     * 		array1: [1, 2, 3, 4, 5, 6, 7, 8, 9, 0]
     * 		array2: [1, 2, 3, 4, 5, 6, 7, 8, 9, 0]
     * 		---
     * 		true
     * 		array1: [1, 2, 3, 4, 5, 6, 7, 8, 9, 0]
     * 		array2: [1, 2, 3, 4, 5, 6, 7, 8, 9, 0]
     * 		---
     * 		true
     * 		array1: [1, 2, 3, 4, 5, 6, 7, 8, 9, 0]
     * 		array2: [1, 2, 3, 4, 5, 6, 7, 8, 9, 0]
     * 		---
     * 		true
     * 		array1: [1, 2, 3, 4, 5, 6, 7, 8, 9, 0]
     * 		array2: [1, 2, 3, 4, 5, 6, 7, 8, 9, 0]
     * 		---
     * 		true
     * 		array1: [1, 2, 3, 4, 5, 6, 7, 8, 9, 0]
     * 		array2: [1, 2, 3, 4, 5, 6, 7, 8, 9, 0]
     * 		---
     * 		true
     * 		array1: [1, 2, 3, 4, 5, 6, 7, 8, 9, 0]
     * 		array2: [1, 2, 3, 4, 5, 6, 7, 8, 9, 0]
     * 		---
     * 		true
     * 		array1: [1, 2, 3, 4, 5, 6, 7, 8, 9, 0]
     * 		array2: [1, 2, 3, 4, 5, 6, 7, 8, 9, 0]
     * 		---
     * 		true
     * 		array1: [1, 2, 3, 4, 5, 6, 7, 8, 9, 0]
     * 		array2: [1, 2, 3, 4, 5, 6, 7, 8, 9, 0]
     * 		---
     * 		true
     * 		array1: [[I@173a10f, [I@530daa]
     * 		array2: [[I@a62fc3, [I@89ae9e]
     * 		---
     * 		true
     * 		array1: [[I@a62fc3, [I@89ae9e]
     * 		array2: [[I@1270b73, [I@60aeb0]
     * 		---
     * 		true
     * 		array1: [[I@1270b73, [I@60aeb0]
     * 		array2: [[I@16caf43, [I@66848c]
     * 		---
     * 		true
     * 		array1: [[I@16caf43, [I@66848c]
     * 		array2: [[I@8813f2, [I@1d58aae]
     * 		---
     * 		false
     * 		true
     * 
     * </pre>
     * 
     * @param array1
     * @param array2
     * @param equivalent
     *            클래스 또는 타입 T의 equals(T obj) 메소드가 아닌 다른 비교 기준을 제공하는 객체
     * @return
     * 
     * 
     *         <BR>
     * @since 2012. 03. 14.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <T> boolean equals(T[] array1, T[] array2, IEquivalent<T> equivalent) {
        if (array1 != null && array2 != null) {
            if (array1.length != array2.length) {
                return false;
            }

            if (equivalent == null) {
                equivalent = new DefaultEquivalent<T>();
            }

            for (int i = 0; i < array1.length; i++) {
                if (!equivalent.equals(array1[i], array2[i])) {
                    return false;
                }
            }

            return true;
        } else if (array1 != null) {
            return false;
        } else if (array2 != null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 주어진 배열들 중에서 첫번째 배열의 마지막번째부터 마지막 배열의 0번째 값의 마지막 인덱스에 해당하는 값을 추출해 하나의 배열로 반환합니다.<br>
     * 
     * <pre>
     * byte[] b1 = {1,2,3};
     * byte[] b2 = {4,6,9};
     * byte[] b3 = {12,567,2};
     * 
     * byte[] b4 = {@link ArrayUtils}.getIncElements(b1, b2, b3);
     * 
     * 결과:
     * b4 = {3, 6, 12}
     * </pre>
     * 
     * @param bytes
     * @see ArrayIndexOutOfBoundsException
     */
    public static byte[] getDecElements(byte[][] bytes) {
        byte[] rtnBytes = new byte[bytes.length];

        for (int i = 0; i < bytes.length; i++) {
            rtnBytes[i] = bytes[i][i];
        }

        return rtnBytes;
    }

    /**
     * 주어진 배열에서 첫번째 데이터를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 10. 26.		박준홍			최초 작성
     * </pre>
     *
     * @param <T>
     * @param array
     * @return
     * @throws NullPointerException
     *             배열이 null 인 경우
     * @throws ArrayIndexOutOfBoundsException
     *             배열의 길이가 0인 경우
     *
     * @since 2022. 10. 26.
     * @version 2.0.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <T> T getFirst(T[] array) {
        Objects.requireNonNull(array);

        int count = array.length;
        if (count < 1) {
            throw new ArrayIndexOutOfBoundsException("주어진 배열의 길이가 0 입니다.");
        } else {
            return array[0];
        }
    }

    /**
     * 주어진 배열들 중에서 첫번째 배열의 0번째부터 마지막 배열의 마지막 인덱스에 해당하는 값을 추출해 하나의 배열로 반환합니다.<br>
     * 
     * <pre>
     * byte[] b1 = {1,2,3};
     * byte[] b2 = {4,6,9};
     * byte[] b3 = {12,567,2};
     * 
     * byte[] b4 = {@link ArrayUtils}.getIncElements(b1, b2, b3);
     * 
     * 결과:
     * b4 = {1,6,2}
     * </pre>
     * 
     * @param bytes
     * @see ArrayIndexOutOfBoundsException
     */
    public static byte[] getIncElements(byte[][] bytes) {
        byte[] rtnBytes = new byte[bytes.length];

        for (int i = 0; i < bytes.length; i++) {
            rtnBytes[i] = bytes[i][i];
        }

        return rtnBytes;
    }

    /**
     * 배열에서 찾고자하는 데이터의 인덱스를 반환합니다.
     * 
     * @param objs
     * @param obj
     * @return 데이터의 인덱스, 없는 경우 -1.
     */
    public static int getIndex(Object[] objs, Object obj) {
        int rtnValue = -1;

        for (int i = 0; i < objs.length; i++) {
            if (objs[i] == obj // check for primitive-type
                    || objs[i].equals(obj) // check for referenced-type
            ) {
                rtnValue = i;

                break;
            }
        }

        return rtnValue;
    }

    /**
     * 주어진 배열에서 마지막 데이터를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 10. 26.		박준홍			최초 작성
     * </pre>
     *
     * @param <T>
     * @param array
     * @return
     * @throws NullPointerException
     *             배열이 null 인 경우
     * @throws ArrayIndexOutOfBoundsException
     *             배열의 길이가 0인 경우
     *
     * @since 2022. 10. 26.
     * @version 2.0.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <T> T getLast(T[] array) throws NullPointerException, ArrayIndexOutOfBoundsException {
        Objects.requireNonNull(array);

        int count = array.length;
        if (count < 1) {
            throw new ArrayIndexOutOfBoundsException("주어진 배열의 길이가 0 입니다.");
        } else {
            return array[count - 1];
        }
    }

    /**
     * 주어진 배열들 중에서 인덱스에 해당하는 값들만 추출해 하나의 배열로 반환합니다.
     * 
     * @param bytes
     * @param index
     * 
     * @see ArrayIndexOutOfBoundsException
     */
    public static byte[] getPlainElements(byte[][] bytes, int index) {
        byte[] rtnBytes = new byte[bytes.length];

        for (int i = 0; i < bytes.length; i++) {
            rtnBytes[i] = bytes[i][index];
        }

        return rtnBytes;
    }

    /**
     * 조건에 맞는 데이터가 처음 발생하는 위치(index)를 반환합니다. 없는 경우 -1을 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 6. 21.     박준홍         최초 작성
     * </pre>
     *
     * @param <T>
     * @param array
     * @param c
     * @return
     *
     * @since 2021. 6. 21.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static int indexOf(boolean[] array, Function<Boolean, Boolean> c) {
        for (int i = 0; i < array.length; i++) {
            if (c.apply(array[i])) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 조건에 맞는 데이터가 처음 발생하는 위치(index)를 반환합니다. 없는 경우 -1을 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 6. 21.     박준홍         최초 작성
     * </pre>
     *
     * @param <T>
     * @param array
     * @param c
     * @return
     *
     * @since 2021. 6. 21.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static int indexOf(byte[] array, Function<Byte, Boolean> c) {
        for (int i = 0; i < array.length; i++) {
            if (c.apply(array[i])) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 조건에 맞는 데이터가 처음 발생하는 위치(index)를 반환합니다. 없는 경우 -1을 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 6. 21.     박준홍         최초 작성
     * </pre>
     *
     * @param <T>
     * @param array
     * @param c
     * @return
     *
     * @since 2021. 6. 21.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static int indexOf(char[] array, Function<Character, Boolean> c) {
        for (int i = 0; i < array.length; i++) {
            if (c.apply(array[i])) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 조건에 맞는 데이터가 처음 발생하는 위치(index)를 반환합니다. 없는 경우 -1을 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 6. 21.		박준홍			최초 작성
     * </pre>
     *
     * @param <T>
     * @param array
     * @param c
     * @return
     *
     * @since 2021. 6. 21.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static int indexOf(double[] array, Function<Double, Boolean> c) {
        for (int i = 0; i < array.length; i++) {
            if (c.apply(array[i])) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 조건에 맞는 데이터가 처음 발생하는 위치(index)를 반환합니다. 없는 경우 -1을 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 6. 21.     박준홍         최초 작성
     * </pre>
     *
     * @param <T>
     * @param array
     * @param c
     * @return
     *
     * @since 2021. 6. 21.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static int indexOf(float[] array, Function<Float, Boolean> c) {
        for (int i = 0; i < array.length; i++) {
            if (c.apply(array[i])) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 조건에 맞는 데이터가 처음 발생하는 위치(index)를 반환합니다. 없는 경우 -1을 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 6. 21.		박준홍			최초 작성
     * </pre>
     *
     * @param <T>
     * @param array
     * @param c
     * @return
     *
     * @since 2021. 6. 21.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static int indexOf(int[] array, Function<Integer, Boolean> c) {
        for (int i = 0; i < array.length; i++) {
            if (c.apply(array[i])) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 조건에 맞는 데이터가 처음 발생하는 위치(index)를 반환합니다. 없는 경우 -1을 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 6. 21.     박준홍         최초 작성
     * </pre>
     *
     * @param <T>
     * @param array
     * @param c
     * @return
     *
     * @since 2021. 6. 21.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static int indexOf(long[] array, Function<Long, Boolean> c) {
        for (int i = 0; i < array.length; i++) {
            if (c.apply(array[i])) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 조건에 맞는 데이터가 처음 발생하는 위치(index)를 반환합니다. 없는 경우 -1을 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 6. 21.     박준홍         최초 작성
     * </pre>
     *
     * @param <T>
     * @param array
     * @param c
     * @return
     *
     * @since 2021. 6. 21.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static int indexOf(short[] array, Function<Short, Boolean> c) {
        for (int i = 0; i < array.length; i++) {
            if (c.apply(array[i])) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 조건에 맞는 데이터가 처음 발생하는 위치(index)를 반환합니다. 없는 경우 -1을 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 6. 21.		박준홍			최초 작성
     * </pre>
     *
     * @param <T>
     * @param array
     * @param c
     * @return
     *
     * @since 2021. 6. 21.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <T> int indexOf(T[] array, Function<T, Boolean> c) {
        for (int i = 0; i < array.length; i++) {
            if (c.apply(array[i])) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 주어진 배열에서 찾고자 하는 값이 존재하는 인덱스 배열을 반환합니다.<br>
     * 배열이 <code>null</code>인 경우 <code>null</code>을 반환합니다.
     * 
     * @param array
     * @param value
     * @return <BR>
     * @since 2012. 03. 12.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static int[] indiceOf(boolean[] array, boolean value) {
        if (array != null) {
            int[] indice = new int[array.length];

            int count = 0;
            for (int i = 0; i < array.length; i++) {
                if (array[i] == value) {
                    indice[count++] = i;
                }
            }

            if (count > 0) {
                return copyOf(indice, count);
            } else {
                return new int[0];
            }

        } else {
            throw new IllegalArgumentException(new NullPointerException("A parameter(boolean[] array) must not be 'null': array=null"));
        }
    }

    /**
     * 주어진 배열에서 찾고자 하는 배열들의 위치 정보를 반환하다.<br>
     * 반환되는 배열의 길이는 0을 포함한 짝수이며, (홀/짝) 쌍을 이루는 값이 (시작/끝)의 정보를 표현합니다.
     * 
     * @param array
     *            대상 배열
     * @param values
     *            찾고자 하는 배열
     * @return
     * 
     * @since 2012. 03. 30.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static int[] indiceOf(boolean[] array, boolean[] values) {
        if (array != null && values != null) {
            if (values != null && array.length >= values.length) {

                int[] indice = new int[array.length / values.length * 2];
                int indicePos = 0;

                IEquivalent<boolean[]> equi = EquivalentFactory.booleanArrayEquiv();

                int arrayLength = array.length;
                int targetLength = values.length;

                int remLength = arrayLength;
                // target's first character
                final boolean tfc = values[0];

                int arrayPos = 0;
                while (arrayPos < arrayLength && remLength >= targetLength) {
                    if (array[arrayPos] == tfc) {
                        remLength = arrayLength - arrayPos;

                        if (remLength >= targetLength) {
                            if (equi.equals(copyOfRange(array, arrayPos, arrayPos + targetLength), values)) {
                                indice[indicePos++] = arrayPos;
                                indice[indicePos++] = arrayPos + targetLength - 1;

                                arrayPos += targetLength;
                            }
                        } else {
                            break;
                        }
                    } else {
                        arrayPos++;
                    }
                }

                return copyOf(indice, indicePos);
            } else {
                return new int[0];
            }
        } else {
            throw new IllegalArgumentException(new NullPointerException(
                    "One of parameters(boolean[] array, boolean[] values) must not be 'null': array=" + array.toString() + ", values=" + values.toString()));
        }
    }

    /**
     * 주어진 배열에서 찾고자 하는 값이 존재하는 인덱스 배열을 반환합니다.<br>
     * 배열이 <code>null</code>인 경우 <code>null</code>을 반환합니다.
     * 
     * @param array
     * @param value
     * @return <BR>
     * @since 2012. 03. 12.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static int[] indiceOf(byte[] array, byte value) {
        if (array != null) {
            int[] indice = new int[array.length];

            int count = 0;
            for (int i = 0; i < array.length; i++) {
                if (array[i] == value) {
                    indice[count++] = i;
                }
            }

            if (count > 0) {
                return copyOf(indice, count);
            } else {
                return new int[0];
            }

        } else {
            throw new IllegalArgumentException(new NullPointerException("A parameter(byte[] array) must not be 'null': array=null"));
        }
    }

    /**
     * 주어진 배열에서 찾고자 하는 배열들의 위치 정보를 반환하다.<br>
     * 반환되는 배열의 길이는 0을 포함한 짝수이며, (홀/짝) 쌍을 이루는 값이 (시작/끝)의 정보를 표현합니다.
     * 
     * @param array
     *            대상 배열
     * @param values
     *            찾고자 하는 배열
     * @return
     * 
     * @since 2012. 03. 30.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static int[] indiceOf(byte[] array, byte[] values) {
        if (array != null && values != null) {
            if (values != null && array.length >= values.length) {

                int[] indice = new int[array.length / values.length * 2];
                int indicePos = 0;

                IEquivalent<byte[]> equi = EquivalentFactory.byteArrayEquiv();

                int arrayLength = array.length;
                int targetLength = values.length;

                int remLength = arrayLength;
                // target's first character
                final byte tfc = values[0];

                int arrayPos = 0;
                while (arrayPos < arrayLength && remLength >= targetLength) {
                    if (array[arrayPos] == tfc) {
                        remLength = arrayLength - arrayPos;

                        if (remLength >= targetLength) {
                            if (equi.equals(copyOfRange(array, arrayPos, arrayPos + targetLength), values)) {
                                indice[indicePos++] = arrayPos;
                                indice[indicePos++] = arrayPos + targetLength - 1;

                                arrayPos += targetLength;
                            }
                        } else {
                            break;
                        }
                    } else {
                        arrayPos++;
                    }
                }

                return copyOf(indice, indicePos);
            } else {
                return new int[0];
            }
        } else {
            throw new IllegalArgumentException(
                    new NullPointerException("One of parameters(byte[] array, byte[] values) must not be 'null': array=" + array + ", values=" + values.toString()));
        }
    }

    /**
     * 주어진 배열에서 찾고자 하는 값이 존재하는 인덱스 배열을 반환합니다.<br>
     * 배열이 <code>null</code>인 경우 <code>null</code>을 반환합니다.
     * 
     * @param array
     * @param value
     * @return <BR>
     * @since 2012. 03. 12.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static int[] indiceOf(char[] array, char value) {
        if (array != null) {
            int[] indice = new int[array.length];

            int count = 0;
            for (int i = 0; i < array.length; i++) {
                if (array[i] == value) {
                    indice[count++] = i;
                }
            }

            if (count > 0) {
                return copyOf(indice, count);
            } else {
                return new int[0];
            }

        } else {
            throw new IllegalArgumentException(new NullPointerException("A parameter(char[] array) must not be 'null': array=null"));
        }
    }

    /**
     * 주어진 배열에서 찾고자 하는 배열들의 위치 정보를 반환하다.<br>
     * 반환되는 배열의 길이는 0을 포함한 짝수이며, (홀/짝) 쌍을 이루는 값이 (시작/끝)의 정보를 표현합니다.
     * 
     * @param array
     *            대상 배열
     * @param values
     *            찾고자 하는 배열
     * @return
     * 
     * @since 2012. 03. 30.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static int[] indiceOf(char[] array, char[] values) {
        if (array != null && values != null) {
            if (values != null && array.length >= values.length) {

                int[] indice = new int[array.length / values.length * 2];
                int indicePos = 0;

                IEquivalent<char[]> equi = EquivalentFactory.charArrayEquiv();

                int arrayLength = array.length;
                int targetLength = values.length;

                int remLength = arrayLength;
                // target's first character
                final char tfc = values[0];

                int arrayPos = 0;
                while (arrayPos < arrayLength && remLength >= targetLength) {
                    if (array[arrayPos] == tfc) {
                        remLength = arrayLength - arrayPos;

                        if (remLength >= targetLength) {
                            if (equi.equals(copyOfRange(array, arrayPos, arrayPos + targetLength), values)) {
                                indice[indicePos++] = arrayPos;
                                indice[indicePos++] = arrayPos + targetLength - 1;

                                arrayPos += targetLength;
                            }
                        } else {
                            break;
                        }
                    } else {
                        arrayPos++;
                    }
                }

                return copyOf(indice, indicePos);
            } else {
                return new int[0];
            }
        } else {
            throw new IllegalArgumentException(new NullPointerException("One of parameters(char[] array, char[] values) must not be 'null': array="
                    + (array != null ? Arrays.toString(array) : null) + ", values=" + (values != null ? Arrays.toString(values) : null)));
        }
    }

    /**
     * 주어진 배열에서 찾고자 하는 값이 존재하는 인덱스 배열을 반환합니다.<br>
     * 배열이 <code>null</code>인 경우 <code>null</code>을 반환합니다.
     * 
     * @param array
     * @param value
     * @return <BR>
     * @since 2012. 03. 12.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static int[] indiceOf(double[] array, double value) {
        if (array != null) {
            int[] indice = new int[array.length];

            int count = 0;
            for (int i = 0; i < array.length; i++) {
                if (array[i] == value) {
                    indice[count++] = i;
                }
            }

            if (count > 0) {
                return copyOf(indice, count);
            } else {
                return new int[0];
            }

        } else {
            throw new IllegalArgumentException(new NullPointerException("A parameter(double[] array) must not be 'null': array=null"));
        }
    }

    /**
     * 주어진 배열에서 찾고자 하는 배열들의 위치 정보를 반환하다.<br>
     * 반환되는 배열의 길이는 0을 포함한 짝수이며, (홀/짝) 쌍을 이루는 값이 (시작/끝)의 정보를 표현합니다.
     * 
     * @param array
     *            대상 배열
     * @param values
     *            찾고자 하는 배열
     * @return
     * 
     * @since 2012. 03. 30.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static int[] indiceOf(double[] array, double[] values) {
        if (array != null && values != null) {
            if (values != null && array.length >= values.length) {

                int[] indice = new int[array.length / values.length * 2];
                int indicePos = 0;

                IEquivalent<double[]> equi = EquivalentFactory.doubleArrayEquiv();

                int arrayLength = array.length;
                int targetLength = values.length;

                int remLength = arrayLength;
                // target's first character
                final double tfc = values[0];

                int arrayPos = 0;
                while (arrayPos < arrayLength && remLength >= targetLength) {
                    if (array[arrayPos] == tfc) {
                        remLength = arrayLength - arrayPos;

                        if (remLength >= targetLength) {
                            if (equi.equals(copyOfRange(array, arrayPos, arrayPos + targetLength), values)) {
                                indice[indicePos++] = arrayPos;
                                indice[indicePos++] = arrayPos + targetLength - 1;

                                arrayPos += targetLength;
                            }
                        } else {
                            break;
                        }
                    } else {
                        arrayPos++;
                    }
                }

                return copyOf(indice, indicePos);
            } else {
                return new int[0];
            }
        } else {
            throw new IllegalArgumentException(
                    new NullPointerException("One of parameters(double[] array, double[] values) must not be 'null': array=" + array + ", values=" + values.toString()));
        }
    }

    /**
     * 주어진 배열에서 찾고자 하는 값이 존재하는 인덱스 배열을 반환합니다.<br>
     * 배열이 <code>null</code>인 경우 <code>null</code>을 반환합니다.
     * 
     * @param array
     * @param value
     * @return <BR>
     * @since 2012. 03. 12.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static int[] indiceOf(float[] array, float value) {
        if (array != null) {
            int[] indice = new int[array.length];

            int count = 0;
            for (int i = 0; i < array.length; i++) {
                if (array[i] == value) {
                    indice[count++] = i;
                }
            }

            if (count > 0) {
                return copyOf(indice, count);
            } else {
                return new int[0];
            }

        } else {
            throw new IllegalArgumentException(new NullPointerException("A parameter(float[] array) must not be 'null': array=null"));
        }
    }

    /**
     * 주어진 배열에서 찾고자 하는 배열들의 위치 정보를 반환하다.<br>
     * 반환되는 배열의 길이는 0을 포함한 짝수이며, (홀/짝) 쌍을 이루는 값이 (시작/끝)의 정보를 표현합니다.
     * 
     * @param array
     *            대상 배열
     * @param values
     *            찾고자 하는 배열
     * @return
     * 
     * @since 2012. 03. 30.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static int[] indiceOf(float[] array, float[] values) {
        if (array != null && values != null) {
            if (values != null && array.length >= values.length) {

                int[] indice = new int[array.length / values.length * 2];
                int indicePos = 0;

                IEquivalent<float[]> equi = EquivalentFactory.floatArrayEquiv();

                int arrayLength = array.length;
                int targetLength = values.length;

                int remLength = arrayLength;
                // target's first character
                final float tfc = values[0];

                int arrayPos = 0;
                while (arrayPos < arrayLength && remLength >= targetLength) {
                    if (array[arrayPos] == tfc) {
                        remLength = arrayLength - arrayPos;

                        if (remLength >= targetLength) {
                            if (equi.equals(copyOfRange(array, arrayPos, arrayPos + targetLength), values)) {
                                indice[indicePos++] = arrayPos;
                                indice[indicePos++] = arrayPos + targetLength - 1;

                                arrayPos += targetLength;
                            }
                        } else {
                            break;
                        }
                    } else {
                        arrayPos++;
                    }
                }

                return copyOf(indice, indicePos);
            } else {
                return new int[0];
            }
        } else {
            throw new IllegalArgumentException(
                    new NullPointerException("One of parameters(float[] array, float[] values) must not be 'null': array=" + array + ", values=" + values.toString()));
        }
    }

    /**
     * 주어진 배열에서 찾고자 하는 값이 존재하는 인덱스 배열을 반환합니다.<br>
     * 배열이 <code>null</code>인 경우 <code>null</code>을 반환합니다.
     * 
     * @param array
     * @param value
     * @return <BR>
     * @since 2012. 03. 12.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static int[] indiceOf(int[] array, int value) {
        if (array != null) {
            int[] indice = new int[array.length];

            int count = 0;
            for (int i = 0; i < array.length; i++) {
                if (array[i] == value) {
                    indice[count++] = i;
                }
            }

            if (count > 0) {
                return copyOf(indice, count);
            } else {
                return new int[0];
            }

        } else {
            throw new IllegalArgumentException(new NullPointerException("A parameter(int[] array) must not be 'null': array=null"));
        }
    }

    /**
     * 주어진 배열에서 찾고자 하는 배열들의 위치 정보를 반환하다.<br>
     * 반환되는 배열의 길이는 0을 포함한 짝수이며, (홀/짝) 쌍을 이루는 값이 (시작/끝)의 정보를 표현합니다.
     * 
     * @param array
     *            대상 배열
     * @param values
     *            찾고자 하는 배열
     * @return
     * 
     * @since 2012. 03. 30.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static int[] indiceOf(int[] array, int[] values) {
        if (array != null && values != null) {
            if (values != null && array.length >= values.length) {

                int[] indice = new int[array.length / values.length * 2];
                int indicePos = 0;

                IEquivalent<int[]> equi = EquivalentFactory.intArrayEquiv();

                int arrayLength = array.length;
                int targetLength = values.length;

                int remLength = arrayLength;
                // target's first character
                final int tfc = values[0];

                int arrayPos = 0;
                while (arrayPos < arrayLength && remLength >= targetLength) {
                    if (array[arrayPos] == tfc) {
                        remLength = arrayLength - arrayPos;

                        if (remLength >= targetLength) {
                            if (equi.equals(copyOfRange(array, arrayPos, arrayPos + targetLength), values)) {
                                indice[indicePos++] = arrayPos;
                                indice[indicePos++] = arrayPos + targetLength - 1;

                                arrayPos += targetLength;
                            }
                        } else {
                            break;
                        }
                    } else {
                        arrayPos++;
                    }
                }

                return copyOf(indice, indicePos);
            } else {
                return new int[0];
            }
        } else {
            throw new IllegalArgumentException(
                    new NullPointerException("One of parameters(int[] array, int[] values) must not be 'null': array=" + array + ", values=" + values.toString()));
        }
    }

    /**
     * 주어진 배열에서 찾고자 하는 값이 존재하는 인덱스 배열을 반환합니다.<br>
     * 배열이 <code>null</code>인 경우 <code>null</code>을 반환합니다.
     * 
     * @param array
     * @param value
     * @return <BR>
     * @since 2012. 03. 12.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static int[] indiceOf(long[] array, long value) {
        if (array != null) {
            int[] indice = new int[array.length];

            int count = 0;
            for (int i = 0; i < array.length; i++) {
                if (array[i] == value) {
                    indice[count++] = i;
                }
            }

            if (count > 0) {
                return copyOf(indice, count);
            } else {
                return new int[0];
            }

        } else {
            throw new IllegalArgumentException(new NullPointerException("A parameter(long[] array) must not be 'null': array=null"));
        }
    }

    /**
     * 주어진 배열에서 찾고자 하는 배열들의 위치 정보를 반환하다.<br>
     * 반환되는 배열의 길이는 0을 포함한 짝수이며, (홀/짝) 쌍을 이루는 값이 (시작/끝)의 정보를 표현합니다.
     * 
     * @param array
     *            대상 배열
     * @param values
     *            찾고자 하는 배열
     * @return
     * 
     * @since 2012. 03. 30.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static int[] indiceOf(long[] array, long[] values) {
        if (array != null && values != null) {
            if (values != null && array.length >= values.length) {

                int[] indice = new int[array.length / values.length * 2];
                int indicePos = 0;

                IEquivalent<long[]> equi = EquivalentFactory.longArrayEquiv();

                int arrayLength = array.length;
                int targetLength = values.length;

                int remLength = arrayLength;
                // target's first character
                final long tfc = values[0];

                int arrayPos = 0;
                while (arrayPos < arrayLength && remLength >= targetLength) {
                    if (array[arrayPos] == tfc) {
                        remLength = arrayLength - arrayPos;

                        if (remLength >= targetLength) {
                            if (equi.equals(copyOfRange(array, arrayPos, arrayPos + targetLength), values)) {
                                indice[indicePos++] = arrayPos;
                                indice[indicePos++] = arrayPos + targetLength - 1;

                                arrayPos += targetLength;
                            }
                        } else {
                            break;
                        }
                    } else {
                        arrayPos++;
                    }
                }

                return copyOf(indice, indicePos);
            } else {
                return new int[0];
            }
        } else {
            throw new IllegalArgumentException(
                    new NullPointerException("One of parameters(long[] array, long[] values) must not be 'null': array=" + array + ", values=" + values.toString()));
        }
    }

    /**
     * 주어진 배열에서 찾고자 하는 값이 존재하는 인덱스 배열을 반환합니다.<br>
     * 배열이 <code>null</code>인 경우 <code>null</code>을 반환합니다.
     * 
     * @param array
     * @param value
     * @return <BR>
     * @since 2012. 03. 12.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static int[] indiceOf(short[] array, short value) {
        if (array != null) {
            int[] indice = new int[array.length];

            int count = 0;
            for (int i = 0; i < array.length; i++) {
                if (array[i] == value) {
                    indice[count++] = i;
                }
            }

            if (count > 0) {
                return copyOf(indice, count);
            } else {
                return new int[0];
            }

        } else {
            throw new IllegalArgumentException(new NullPointerException("A parameter(long[] array) must not be 'null': array=null"));
        }
    }

    /**
     * 주어진 배열에서 찾고자 하는 배열들의 위치 정보를 반환하다.<br>
     * 반환되는 배열의 길이는 0을 포함한 짝수이며, (홀/짝) 쌍을 이루는 값이 (시작/끝)의 정보를 표현합니다.
     * 
     * @param array
     *            대상 배열
     * @param values
     *            찾고자 하는 배열
     * @return
     * 
     * @since 2012. 03. 30.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static int[] indiceOf(short[] array, short[] values) {
        if (array != null && values != null) {
            if (values != null && array.length >= values.length) {

                int[] indice = new int[array.length / values.length * 2];
                int indicePos = 0;

                IEquivalent<short[]> equi = EquivalentFactory.shortArrayEquiv();

                int arrayLength = array.length;
                int targetLength = values.length;

                int remLength = arrayLength;
                // target's first character
                final short tfc = values[0];

                int arrayPos = 0;
                while (arrayPos < arrayLength && remLength >= targetLength) {
                    if (array[arrayPos] == tfc) {
                        remLength = arrayLength - arrayPos;

                        if (remLength >= targetLength) {
                            if (equi.equals(copyOfRange(array, arrayPos, arrayPos + targetLength), values)) {
                                indice[indicePos++] = arrayPos;
                                indice[indicePos++] = arrayPos + targetLength - 1;

                                arrayPos += targetLength;
                            }
                        } else {
                            break;
                        }
                    } else {
                        arrayPos++;
                    }
                }

                return copyOf(indice, indicePos);
            } else {
                return new int[0];
            }
        } else {
            throw new IllegalArgumentException(
                    new NullPointerException("One of parameters(short[] array, short[] values) must not be 'null': array=" + array + ", values=" + values.toString()));
        }
    }

    /**
     * 주어진 배열에서 찾고자 하는 값이 존재하는 인덱스 배열을 반환합니다.<br>
     * 배열이 <code>null</code>인 경우 <code>null</code>을 반환합니다.
     * 
     * @param array
     * @param value
     * @return
     * 
     *         <BR>
     * @since 2012. 03. 12.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <T> int[] indiceOf(T[] array, T value) {
        return indiceOf(array, value, null);
    }

    /**
     * 주어진 배열에서 찾고자 하는 값이 존재하는 인덱스 배열을 반환합니다.<br>
     * 배열이 <code>null</code>인 경우 <code>null</code>을 반환합니다.
     * 
     * @param array
     * @param value
     * @param equivalent
     *            클래스 또는 타입 T의 equals(T obj) 메소드가 아닌 다른 비교 기준을 제공하는 객체
     * @return
     * 
     *         <BR>
     * @since 2012. 03. 12.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <T> int[] indiceOf(T[] array, T value, IEquivalent<T> equivalent) {
        if (array != null) {
            int[] indice = new int[array.length];

            if (equivalent == null) {
                equivalent = new DefaultEquivalent<T>();
            }

            int count = 0;
            for (int i = 0; i < array.length; i++) {
                if (equivalent.equals(array[i], value)) {
                    indice[count++] = i;
                }
            }

            if (count > 0) {
                return copyOf(indice, count);
            } else {
                return new int[0];
            }

        } else {
            throw new IllegalArgumentException(new NullPointerException("A parameter(T[] array) must not be 'null': array=null"));
        }
    }

    /**
     * 주어진 배열에서 찾고자 하는 배열들의 위치 정보를 반환하다.<br>
     * 반환되는 배열의 길이는 0을 포함한 짝수이며, (홀/짝) 쌍을 이루는 값이 (시작/끝)의 정보를 표현합니다.
     * 
     * @param array
     *            대상 배열
     * @param values
     *            찾고자 하는 배열
     * @return
     * 
     * @since 2012. 03. 30.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <T> int[] indiceOf(T[] array, T[] values) {
        if (array != null && values != null) {
            return indiceOfArray(array, values, null);
        } else {
            throw new IllegalArgumentException(new NullPointerException("One of parameter(T[] array, T[] values) must not be 'null': array=" + array + ", values=" + values));
        }
    }

    /**
     * 주어진 배열에서 찾고자 하는 배열들의 위치 정보를 반환하다.<br>
     * 반환되는 배열의 길이는 0을 포함한 짝수이며, (홀/짝) 쌍을 이루는 값이 (시작/끝)의 정보를 표현합니다.
     * 
     * @param array
     *            대상 배열
     * @param values
     *            찾고자 하는 배열
     * @return
     * 
     * @since 2012. 03. 30.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <T> int[] indiceOfArray(T[] array, T[] values, IEquivalent<T[]> equi) {
        if (array != null && values != null) {
            if (values != null && array.length >= values.length) {

                int[] indice = new int[array.length / values.length * 2];
                int indicePos = 0;

                if (equi == null) {
                    equi = EquivalentFactory.getDefault();
                }

                int arrayLength = array.length;
                int targetLength = values.length;

                int remLength = arrayLength;
                // target's first character
                final T tfc = values[0];

                int arrayPos = 0;
                while (arrayPos < arrayLength && remLength >= targetLength) {
                    if (array[arrayPos] == tfc) {
                        remLength = arrayLength - arrayPos;

                        if (remLength >= targetLength) {
                            if (equi.equals(copyOfRange(array, arrayPos, arrayPos + targetLength), values)) {
                                indice[indicePos++] = arrayPos;
                                indice[indicePos++] = arrayPos + targetLength - 1;

                                arrayPos += targetLength;
                            }
                        } else {
                            break;
                        }
                    } else {
                        arrayPos++;
                    }
                }

                return copyOf(indice, indicePos);
            } else {
                return new int[0];
            }
        } else {
            throw new IllegalArgumentException(new NullPointerException("One of parameter(T[] array, T[] values) must not be 'null': array=" + array + ", values=" + values));
        }
    }

    /**
     * 주어진 값으로 채워지는, 길이 <b><code>l</code></b>인 배열을 생성합니다.
     * 
     * @param length
     *            배열의 길이
     * @param initValue
     *            초기값
     * @return
     */
    public static boolean[] initArray(int length, boolean initValue) {
        boolean[] array = new boolean[length];

        for (int i = 0; i < length; i++) {
            array[i] = initValue;
        }

        return array;
    }

    /**
     * 주어진 값으로 채워지는, 길이 <b><code>l</code></b>인 배열을 생성합니다.
     * 
     * @param length
     *            배열의 길이
     * @param initValue
     *            초기값
     * @return
     */
    public static byte[] initArray(int length, byte initValue) {
        byte[] array = new byte[length];

        for (int i = 0; i < length; i++) {
            array[i] = initValue;
        }

        return array;
    }

    /**
     * 주어진 값으로 채워지는, 길이 <b><code>l</code></b>인 배열을 생성합니다.
     * 
     * @param length
     *            배열의 길이
     * @param initValue
     *            초기값
     * @return
     */
    public static char[] initArray(int length, char initValue) {
        char[] array = new char[length];

        for (int i = 0; i < length; i++) {
            array[i] = initValue;
        }

        return array;
    }

    /**
     * 주어진 값으로 채워지는, 길이 <b><code>l</code></b>인 배열을 생성합니다.
     * 
     * @param length
     *            배열의 길이
     * @param initValue
     *            초기값
     * @return
     */
    public static double[] initArray(int length, double initValue) {
        double[] array = new double[length];

        for (int i = 0; i < length; i++) {
            array[i] = initValue;
        }

        return array;
    }

    /**
     * 주어진 값으로 채워지는, 길이 <b><code>l</code></b>인 배열을 생성합니다.
     * 
     * @param length
     *            배열의 길이
     * @param initValue
     *            초기값
     * @return
     */
    public static float[] initArray(int length, float initValue) {
        float[] array = new float[length];

        for (int i = 0; i < length; i++) {
            array[i] = initValue;
        }

        return array;
    }

    /**
     * 주어진 값으로 채워지는, 길이 <b><code>l</code></b>인 배열을 생성합니다.
     * 
     * @param length
     *            배열의 길이
     * @param initValue
     *            초기값
     * @return
     */
    public static int[] initArray(int length, int initValue) {
        int[] array = new int[length];

        for (int i = 0; i < length; i++) {
            array[i] = initValue;
        }

        return array;
    }

    /**
     * 주어진 값으로 채워지는, 길이 <b><code>l</code></b>인 배열을 생성합니다.
     * 
     * @param length
     *            배열의 길이
     * @param initValue
     *            초기값
     * @return
     */
    public static long[] initArray(int length, long initValue) {
        long[] array = new long[length];

        for (int i = 0; i < length; i++) {
            array[i] = initValue;
        }

        return array;
    }

    /**
     * 주어진 값으로 채워지는, 길이 <b><code>l</code></b>인 배열을 생성합니다.
     * 
     * @param length
     *            배열의 길이
     * @param initValue
     *            초기값
     * @return
     */
    public static short[] initArray(int length, short initValue) {
        short[] array = new short[length];

        for (int i = 0; i < length; i++) {
            array[i] = initValue;
        }

        return array;
    }

    /**
     * 주어진 값으로 채워지는, 길이 <b><code>l</code></b>인 배열을 생성합니다.
     * 
     * @param length
     *            배열의 길이
     * @param initValue
     *            초기값
     * @return
     */
    public static <T> T[] initArray(int length, T initValue) {
        Class<?> _class = initValue.getClass();
        T[] array = (T[]) Array.newInstance(String.class.isAssignableFrom(_class) ? _class : _class.getComponentType(), length);

        for (int i = 0; i < length; i++) {
            array[i] = initValue;
        }

        return array;
    }

    /**
     * 새로운 값을 원하는 위치에 삽입합니다.
     * 
     * @param array
     * @param value
     * @param index
     * @return
     * 
     * @exception IllegalArgumentException
     *                주어진 배열이 <code>null</code>인 경우 <BR>
     * @since 2012. 03. 13.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static boolean[] insert(boolean[] array, boolean value, int index) {
        if (array != null) {
            if (index < 0 || index > array.length) {
                throw new IllegalArgumentException("An index(int index) must be between 0 to " + array.length);
            } else if (index == 0) {
                return prepend(array, value);
            } else if (index == array.length) {
                return add(array, value);
            } else {
                boolean[] newArray = new boolean[array.length + 1];

                System.arraycopy(array, 0, newArray, 0, index);

                // insert a new value
                newArray[index] = value;

                System.arraycopy(array, index, newArray, index + 1, array.length - index);

                return newArray;
            }
        } else {
            throw new IllegalArgumentException("A parameter(boolean[] array) must not be null.", new NullPointerException("array=null"));
        }
    }

    /**
     * 새로운 값을 원하는 여러 위치에 삽입합니다.
     * 
     * @param array
     * @param value
     * @param indice
     * @return <BR>
     * @since 2012. 03. 13.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static boolean[] insert(boolean[] array, boolean value, int... indice) {
        if (array != null && indice != null) {
            if (indice.length < 1) {
                return array;
            } else if (indice.length > 1) {
                checkRange(0, array.length, indice);

                boolean[] newArray = new boolean[array.length + indice.length];

                int srcPos = 0;
                int destPos = 0;
                int latestIndex = -1;
                // Real-time Existing Values Copied-Length
                int revcl = 0;
                for (int index : indice) {
                    // copy existing values
                    revcl = index - latestIndex - 1;
                    System.arraycopy(array, srcPos, newArray, destPos, revcl);

                    destPos += revcl;
                    // copy a new value at index
                    newArray[destPos++] = value;

                    srcPos += revcl;

                    latestIndex = index;
                }

                System.arraycopy(array, srcPos, newArray, destPos, array.length - srcPos);

                return newArray;
            } else {
                return insert(array, value, indice[0]);
            }

        } else {
            throw new IllegalArgumentException("Parameters(boolean[] array, int index) must not be null.", new NullPointerException("array=null, indice=null"));
        }
    }

    /**
     * 새로운 값을 원하는 위치에 삽입합니다.
     * 
     * @param array
     * @param value
     * @param index
     * @return
     * 
     * @exception IllegalArgumentException
     *                주어진 배열이 <code>null</code>인 경우 <BR>
     * @since 2012. 03. 13.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static byte[] insert(byte[] array, byte value, int index) {
        if (array != null) {
            if (index < 0 || index > array.length) {
                throw new IllegalArgumentException("An index(int index) must be between 0 to " + array.length);
            } else if (index == 0) {
                return prepend(array, value);
            } else if (index == array.length) {
                return add(array, value);
            } else {
                byte[] newArray = new byte[array.length + 1];

                System.arraycopy(array, 0, newArray, 0, index);

                // insert a new value
                newArray[index] = value;

                System.arraycopy(array, index, newArray, index + 1, array.length - index);

                return newArray;
            }
        } else {
            throw new IllegalArgumentException("A parameter(byte[] array) must not be null.", new NullPointerException("array=null"));
        }
    }

    /**
     * 새로운 값을 원하는 여러 위치에 삽입합니다.
     * 
     * @param array
     * @param value
     * @param indice
     * @return <BR>
     * @since 2012. 03. 13.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static byte[] insert(byte[] array, byte value, int... indice) {
        if (array != null && indice != null) {
            if (indice.length < 1) {
                return array;
            } else if (indice.length > 1) {
                checkRange(0, array.length, indice);

                byte[] newArray = new byte[array.length + indice.length];

                int srcPos = 0;
                int destPos = 0;
                int latestIndex = -1;
                // Real-time Existing Values Copied-Length
                int revcl = 0;
                for (int index : indice) {
                    // copy existing values
                    revcl = index - latestIndex - 1;
                    System.arraycopy(array, srcPos, newArray, destPos, revcl);

                    destPos += revcl;
                    // copy a new value at index
                    newArray[destPos++] = value;

                    srcPos += revcl;

                    latestIndex = index;
                }

                System.arraycopy(array, srcPos, newArray, destPos, array.length - srcPos);

                return newArray;
            } else {
                return insert(array, value, indice[0]);
            }

        } else {
            throw new IllegalArgumentException("Parameters(byte[] array, int index) must not be null.", new NullPointerException("array=null, indice=null"));
        }
    }

    /**
     * 새로운 값을 원하는 위치에 삽입합니다.
     * 
     * @param array
     * @param value
     * @param index
     * @return
     * 
     * @exception IllegalArgumentException
     *                주어진 배열이 <code>null</code>인 경우 <BR>
     * @since 2012. 03. 13.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static char[] insert(char[] array, char value, int index) {
        if (array != null) {
            if (index < 0 || index > array.length) {
                throw new IllegalArgumentException("An index(int index) must be between 0 to " + array.length);
            } else if (index == 0) {
                return prepend(array, value);
            } else if (index == array.length) {
                return add(array, value);
            } else {
                char[] newArray = new char[array.length + 1];

                System.arraycopy(array, 0, newArray, 0, index);

                // insert a new value
                newArray[index] = value;

                System.arraycopy(array, index, newArray, index + 1, array.length - index);

                return newArray;
            }
        } else {
            throw new IllegalArgumentException("A parameter(char[] array) must not be null.", new NullPointerException("array=null"));
        }
    }

    /**
     * 새로운 값을 원하는 여러 위치에 삽입합니다.
     * 
     * @param array
     * @param value
     * @param indice
     * @return <BR>
     * @since 2012. 03. 13.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static char[] insert(char[] array, char value, int... indice) {
        if (array != null && indice != null) {
            if (indice.length < 1) {
                return array;
            } else if (indice.length > 1) {
                checkRange(0, array.length, indice);

                char[] newArray = new char[array.length + indice.length];

                int srcPos = 0;
                int destPos = 0;
                int latestIndex = -1;
                // Real-time Existing Values Copied-Length
                int revcl = 0;
                for (int index : indice) {
                    // copy existing values
                    revcl = index - latestIndex - 1;
                    System.arraycopy(array, srcPos, newArray, destPos, revcl);

                    destPos += revcl;
                    // copy a new value at index
                    newArray[destPos++] = value;

                    srcPos += revcl;

                    latestIndex = index;
                }

                System.arraycopy(array, srcPos, newArray, destPos, array.length - srcPos);

                return newArray;
            } else {
                return insert(array, value, indice[0]);
            }

        } else {
            throw new IllegalArgumentException("Parameters(char[] array, int index) must not be null.", new NullPointerException("array=null, indice=null"));
        }
    }

    /**
     * 새로운 값을 원하는 위치에 삽입합니다.
     * 
     * @param array
     * @param value
     * @param index
     * @return
     * 
     * @exception IllegalArgumentException
     *                주어진 배열이 <code>null</code>인 경우 <BR>
     * @since 2012. 03. 13.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static double[] insert(double[] array, double value, int index) {
        if (array != null) {
            if (index < 0 || index > array.length) {
                throw new IllegalArgumentException("An index(int index) must be between 0 to " + array.length);
            } else if (index == 0) {
                return prepend(array, value);
            } else if (index == array.length) {
                return add(array, value);
            } else {
                double[] newArray = new double[array.length + 1];

                System.arraycopy(array, 0, newArray, 0, index);

                // insert a new value
                newArray[index] = value;

                System.arraycopy(array, index, newArray, index + 1, array.length - index);

                return newArray;
            }
        } else {
            throw new IllegalArgumentException("A parameter(double[] array) must not be null.", new NullPointerException("array=null"));
        }
    }

    /**
     * 새로운 값을 원하는 여러 위치에 삽입합니다.
     * 
     * @param array
     * @param value
     * @param indice
     * @return <BR>
     * @since 2012. 03. 13.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static double[] insert(double[] array, double value, int... indice) {
        if (array != null && indice != null) {
            if (indice.length < 1) {
                return array;
            } else if (indice.length > 1) {
                checkRange(0, array.length, indice);

                double[] newArray = new double[array.length + indice.length];

                int srcPos = 0;
                int destPos = 0;
                int latestIndex = -1;
                // Real-time Existing Values Copied-Length
                int revcl = 0;
                for (int index : indice) {
                    // copy existing values
                    revcl = index - latestIndex - 1;
                    System.arraycopy(array, srcPos, newArray, destPos, revcl);

                    destPos += revcl;
                    // copy a new value at index
                    newArray[destPos++] = value;

                    srcPos += revcl;

                    latestIndex = index;
                }

                System.arraycopy(array, srcPos, newArray, destPos, array.length - srcPos);

                return newArray;
            } else {
                return insert(array, value, indice[0]);
            }

        } else {
            throw new IllegalArgumentException("Parameters(double[] array, int index) must not be null.", new NullPointerException("array=null, indice=null"));
        }
    }

    /**
     * 새로운 값을 원하는 위치에 삽입합니다.
     * 
     * @param array
     * @param value
     * @param index
     * @return
     * 
     * @exception IllegalArgumentException
     *                주어진 배열이 <code>null</code>인 경우 <BR>
     * @since 2012. 03. 13.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static float[] insert(float[] array, float value, int index) {
        if (array != null) {
            if (index < 0 || index > array.length) {
                throw new IllegalArgumentException("An index(int index) must be between 0 to " + array.length);
            } else if (index == 0) {
                return prepend(array, value);
            } else if (index == array.length) {
                return add(array, value);
            } else {
                float[] newArray = new float[array.length + 1];

                System.arraycopy(array, 0, newArray, 0, index);

                // insert a new value
                newArray[index] = value;

                System.arraycopy(array, index, newArray, index + 1, array.length - index);

                return newArray;
            }
        } else {
            throw new IllegalArgumentException("A parameter(float[] array) must not be null.", new NullPointerException("array=null"));
        }
    }

    /**
     * 새로운 값을 원하는 여러 위치에 삽입합니다.
     * 
     * @param array
     * @param value
     * @param indice
     * @return <BR>
     * @since 2012. 03. 13.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static float[] insert(float[] array, float value, int... indice) {
        if (array != null && indice != null) {
            if (indice.length < 1) {
                return array;
            } else if (indice.length > 1) {
                checkRange(0, array.length, indice);

                float[] newArray = new float[array.length + indice.length];

                int srcPos = 0;
                int destPos = 0;
                int latestIndex = -1;
                // Real-time Existing Values Copied-Length
                int revcl = 0;
                for (int index : indice) {
                    // copy existing values
                    revcl = index - latestIndex - 1;
                    System.arraycopy(array, srcPos, newArray, destPos, revcl);

                    destPos += revcl;
                    // copy a new value at index
                    newArray[destPos++] = value;

                    srcPos += revcl;

                    latestIndex = index;
                }

                System.arraycopy(array, srcPos, newArray, destPos, array.length - srcPos);

                return newArray;
            } else {
                return insert(array, value, indice[0]);
            }

        } else {
            throw new IllegalArgumentException("Parameters(float[] array, int index) must not be null.", new NullPointerException("array=null, indice=null"));
        }
    }

    /**
     * 새로운 값을 원하는 위치에 삽입합니다.
     * 
     * @param array
     * @param value
     * @param index
     * @return
     * 
     * @exception IllegalArgumentException
     *                주어진 배열이 <code>null</code>인 경우 <BR>
     * @since 2012. 03. 13.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static int[] insert(int[] array, int value, int index) {
        if (array != null) {
            if (index < 0 || index > array.length) {
                throw new IllegalArgumentException("An index(int index) must be between 0 to " + array.length);
            } else if (index == 0) {
                return prepend(array, value);
            } else if (index == array.length) {
                return add(array, value);
            } else {
                int[] newArray = new int[array.length + 1];

                System.arraycopy(array, 0, newArray, 0, index);

                // insert a new value
                newArray[index] = value;

                System.arraycopy(array, index, newArray, index + 1, array.length - index);

                return newArray;
            }
        } else {
            throw new IllegalArgumentException("A parameter(int[] array) must not be null.", new NullPointerException("array=null"));
        }
    }

    /**
     * 새로운 값을 원하는 여러 위치에 삽입합니다.
     * 
     * @param array
     * @param value
     * @param indice
     * @return <BR>
     * @since 2012. 03. 13.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static int[] insert(int[] array, int value, int... indice) {
        if (array != null && indice != null) {
            if (indice.length < 1) {
                return array;
            } else if (indice.length > 1) {
                checkRange(0, array.length, indice);

                int[] newArray = new int[array.length + indice.length];

                int srcPos = 0;
                int destPos = 0;
                int latestIndex = -1;
                // Real-time Existing Values Copied-Length
                int revcl = 0;
                for (int index : indice) {
                    // copy existing values
                    revcl = index - latestIndex - 1;
                    System.arraycopy(array, srcPos, newArray, destPos, revcl);

                    destPos += revcl;
                    // copy a new value at index
                    newArray[destPos++] = value;

                    srcPos += revcl;

                    latestIndex = index;
                }

                System.arraycopy(array, srcPos, newArray, destPos, array.length - srcPos);

                return newArray;
            } else {
                return insert(array, value, indice[0]);
            }

        } else {
            throw new IllegalArgumentException("Parameters(int[] array, int index) must not be null.", new NullPointerException("array=null, indice=null"));
        }
    }

    /**
     * 새로운 값을 원하는 위치에 삽입합니다.
     * 
     * @param array
     * @param value
     * @param index
     * @return
     * 
     * @exception IllegalArgumentException
     *                주어진 배열이 <code>null</code>인 경우 <BR>
     * @since 2012. 03. 13.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static long[] insert(long[] array, long value, int index) {
        if (array != null) {
            if (index < 0 || index > array.length) {
                throw new IllegalArgumentException("An index(int index) must be between 0 to " + array.length);
            } else if (index == 0) {
                return prepend(array, value);
            } else if (index == array.length) {
                return add(array, value);
            } else {
                long[] newArray = new long[array.length + 1];

                System.arraycopy(array, 0, newArray, 0, index);

                // insert a new value
                newArray[index] = value;

                System.arraycopy(array, index, newArray, index + 1, array.length - index);

                return newArray;
            }
        } else {
            throw new IllegalArgumentException("A parameter(long[] array) must not be null.", new NullPointerException("array=null"));
        }
    }

    /**
     * 새로운 값을 원하는 여러 위치에 삽입합니다.
     * 
     * @param array
     * @param value
     * @param indice
     * @return <BR>
     * @since 2012. 03. 13.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static long[] insert(long[] array, long value, int... indice) {
        if (array != null && indice != null) {
            if (indice.length < 1) {
                return array;
            } else if (indice.length > 1) {
                checkRange(0, array.length, indice);

                long[] newArray = new long[array.length + indice.length];

                int srcPos = 0;
                int destPos = 0;
                int latestIndex = -1;
                // Real-time Existing Values Copied-Length
                int revcl = 0;
                for (int index : indice) {
                    // copy existing values
                    revcl = index - latestIndex - 1;
                    System.arraycopy(array, srcPos, newArray, destPos, revcl);

                    destPos += revcl;
                    // copy a new value at index
                    newArray[destPos++] = value;

                    srcPos += revcl;

                    latestIndex = index;
                }

                System.arraycopy(array, srcPos, newArray, destPos, array.length - srcPos);

                return newArray;
            } else {
                return insert(array, value, indice[0]);
            }

        } else {
            throw new IllegalArgumentException("Parameters(long[] array, int index) must not be null.", new NullPointerException("array=null, indice=null"));
        }
    }

    /**
     * 새로운 값을 원하는 여러 위치에 삽입합니다.
     * 
     * @param array
     * @param value
     * @param indice
     * @return <BR>
     * @since 2012. 03. 13.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static short[] insert(short[] array, short value, int... indice) {
        if (array != null && indice != null) {
            if (indice.length < 1) {
                return array;
            } else if (indice.length > 1) {
                checkRange(0, array.length, indice);

                short[] newArray = new short[array.length + indice.length];

                int srcPos = 0;
                int destPos = 0;
                int latestIndex = -1;
                // Real-time Existing Values Copied-Length
                int revcl = 0;
                for (int index : indice) {
                    // copy existing values
                    revcl = index - latestIndex - 1;
                    System.arraycopy(array, srcPos, newArray, destPos, revcl);

                    destPos += revcl;
                    // copy a new value at index
                    newArray[destPos++] = value;

                    srcPos += revcl;

                    latestIndex = index;
                }

                System.arraycopy(array, srcPos, newArray, destPos, array.length - srcPos);

                return newArray;
            } else {
                return insert(array, value, indice[0]);
            }

        } else {
            throw new IllegalArgumentException("Parameters(short[] array, int ... index) must not be null.", new NullPointerException("array=null, indice=null"));
        }
    }

    /**
     * 새로운 값을 원하는 위치에 삽입합니다.
     * 
     * @param array
     * @param value
     * @param index
     * @return
     * 
     * @exception IllegalArgumentException
     *                주어진 배열이 <code>null</code>인 경우 <BR>
     * @since 2012. 03. 13.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static short[] insert(short[] array, short value, int index) {
        if (array != null) {
            if (index < 0 || index > array.length) {
                throw new IllegalArgumentException("An index(int index) must be between 0 to " + array.length);
            } else if (index == 0) {
                return prepend(array, value);
            } else if (index == array.length) {
                return add(array, value);
            } else {
                short[] newArray = new short[array.length + 1];

                System.arraycopy(array, 0, newArray, 0, index);

                // insert a new value
                newArray[index] = value;

                System.arraycopy(array, index, newArray, index + 1, array.length - index);

                return newArray;
            }
        } else {
            throw new IllegalArgumentException("A parameter(long[] array) must not be null.", new NullPointerException("array=null"));
        }
    }

    /**
     * 새로운 값을 원하는 위치에 삽입합니다.
     * 
     * @param array
     * @param value
     * @param index
     * @return
     * 
     * @exception IllegalArgumentException
     *                주어진 배열이 <code>null</code>인 경우 <BR>
     * @since 2012. 03. 13.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <T> T[] insert(T[] array, T value, int index) {
        if (array != null) {
            if (index < 0 || index > array.length) {
                throw new IllegalArgumentException("An index(int index) must be between 0 to " + array.length);
            } else if (index == 0) {
                return prepend(array, value);
            } else if (index == array.length) {
                return add(array, value);
            } else {
                T[] newArray = (T[]) Array.newInstance(array.getClass().getComponentType(), array.length + 1);

                System.arraycopy(array, 0, newArray, 0, index);

                // insert a new value
                newArray[index] = value;

                System.arraycopy(array, index, newArray, index + 1, array.length - index);

                return newArray;
            }
        } else {
            throw new IllegalArgumentException("A parameter(T[] array) must not be null.", new NullPointerException("array=null"));
        }
    }

    /**
     * 새로운 값을 원하는 여러 위치에 삽입합니다.
     * 
     * @param array
     * @param value
     * @param indice
     * @return <BR>
     * @since 2012. 03. 13.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <T> T[] insert(T[] array, T value, int... indice) {
        if (array != null && indice != null) {
            if (indice.length < 1) {
                return array;
            } else if (indice.length > 1) {
                checkRange(0, array.length, indice);

                T[] newArray = (T[]) Array.newInstance(array.getClass().getComponentType(), array.length + 1);

                int srcPos = 0;
                int destPos = 0;
                int latestIndex = -1;
                // Real-time Existing Values Copied-Length
                int revcl = 0;
                for (int index : indice) {
                    // copy existing values
                    revcl = index - latestIndex - 1;
                    System.arraycopy(array, srcPos, newArray, destPos, revcl);

                    destPos += revcl;
                    // copy a new value at index
                    newArray[destPos++] = value;

                    srcPos += revcl;

                    latestIndex = index;
                }

                System.arraycopy(array, srcPos, newArray, destPos, array.length - srcPos);

                return newArray;
            } else {
                return insert(array, value, indice[0]);
            }

        } else {
            throw new IllegalArgumentException("Parameters(T[] array, int index) must not be null.", new NullPointerException("array=null, indice=null"));
        }
    }

    /**
     * 여러 개의 배열을 하나의 배열로 합쳐서 반환합니다.
     * 
     * @param arrays
     * @return 합쳐진 배열. 모든 배열이 <code>null</code>인 경우 <code>null</code>을 반환합니다.
     * 
     *         <BR>
     * @since 2012. 03. 12.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static boolean[] merge(boolean[]... arrays) {

        // null 이 아닌 배열의 인덱스
        int[] evalArr = new int[arrays.length];

        int evalIndex = 0;
        // null이 아닌 배열의 인덱스를 보관합니다.
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i] != null) {
                evalArr[evalIndex++] = i;
            }
        }

        // 실제 데이타를 포함하고 있는 배열들의 길이의 합
        int mergeIndex = 0;
        for (int i = 0; i < evalIndex; i++) {
            mergeIndex += arrays[evalArr[i]].length;
        }

        if (mergeIndex != 0) {
            boolean[] mergedArray = new boolean[mergeIndex];

            int copiedLength = 0;
            for (int i = 0; i < evalIndex; i++) {
                System.arraycopy(arrays[evalArr[i]], 0, mergedArray, copiedLength, arrays[evalArr[i]].length);

                copiedLength += arrays[evalArr[i]].length;
            }

            return mergedArray;
        }

        return null;
    }

    /**
     * 2개의 배열을 하나의 배열로 합쳐서 반환합니다.
     * 
     * @param arr1
     * @param arr2
     * @return
     * 
     * @exception NullPointerException
     *                Either of parameters is null
     * 
     *                <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static boolean[] merge(boolean[] arr1, boolean[] arr2) {
        if (arr1 != null && arr2 != null) {
            boolean[] merged = new boolean[arr1.length + arr2.length];

            System.arraycopy(arr1, 0, merged, 0, arr1.length);
            System.arraycopy(arr2, 0, merged, arr1.length, arr2.length);

            return merged;
        } else if (arr1 != null) {
            return arr1;
        } else if (arr2 != null) {
            return arr2;
        } else {
            throw new IllegalArgumentException(new NullPointerException("All parameters(boolean[] arr1, boolean[] arr2) must not be null: arr1=null, arr2=null"));
        }
    }

    /**
     * 여러 개의 배열을 하나의 배열로 합쳐서 반환합니다.
     * 
     * @param arrays
     * @return 합쳐진 배열. 모든 배열이 <code>null</code>인 경우 <code>null</code>을 반환합니다.
     * 
     *         <BR>
     * @since 2012. 03. 12.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static byte[] merge(byte[]... arrays) {

        // null 이 아닌 배열의 인덱스
        int[] evalArr = new int[arrays.length];

        int evalIndex = 0;
        // null이 아닌 배열의 인덱스를 보관합니다.
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i] != null) {
                evalArr[evalIndex++] = i;
            }
        }

        // 실제 데이타를 포함하고 있는 배열들의 길이의 합
        int mergeIndex = 0;
        for (int i = 0; i < evalIndex; i++) {
            mergeIndex += arrays[evalArr[i]].length;
        }

        if (mergeIndex != 0) {
            byte[] mergedArray = new byte[mergeIndex];

            int copiedLength = 0;
            for (int i = 0; i < evalIndex; i++) {
                System.arraycopy(arrays[evalArr[i]], 0, mergedArray, copiedLength, arrays[evalArr[i]].length);

                copiedLength += arrays[evalArr[i]].length;
            }

            return mergedArray;
        }

        return null;
    }

    /**
     * 2개의 배열을 하나의 배열로 합쳐서 반환합니다.
     * 
     * @param arr1
     * @param arr2
     * @return
     * 
     * @exception NullPointerException
     *                Either of parameters is null
     * 
     *                <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static byte[] merge(byte[] arr1, byte[] arr2) {
        if (arr1 != null && arr2 != null) {
            byte[] merged = new byte[arr1.length + arr2.length];

            System.arraycopy(arr1, 0, merged, 0, arr1.length);
            System.arraycopy(arr2, 0, merged, arr1.length, arr2.length);

            return merged;
        } else if (arr1 != null) {
            return arr1;
        } else if (arr2 != null) {
            return arr2;
        } else {
            throw new IllegalArgumentException(new NullPointerException("All parameters(byte[] arr1, byte[] arr2) must not be null: arr1=null, arr2=null"));
        }
    }

    /**
     * 2개의 배열을 하나의 배열로 합쳐서 반환합니다.
     * 
     * @param arr1
     * @param arr2
     * @param length
     *            2번째 배열에서 복사할 크기
     * @return
     * 
     * @exception NullPointerException
     *                Either of parameters is null
     * 
     *                <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (fafanmama@naver.com)
     */
    public static byte[] merge(byte[] arr1, byte[] arr2, int length) {
        if (arr1 != null && arr2 != null) {
            byte[] merged = new byte[arr1.length + length];

            System.arraycopy(arr1, 0, merged, 0, arr1.length);
            System.arraycopy(arr2, 0, merged, arr1.length, length);

            return merged;
        } else if (arr1 != null) {
            return arr1;
        } else if (arr2 != null) {
            return copyOf(arr2, length);
        } else {
            throw new IllegalArgumentException(new NullPointerException("All parameters(byte[] arr1, byte[] arr2) must not be null: arr1=null, arr2=null"));
        }
    }

    /**
     * 여러 개의 배열을 하나의 배열로 합쳐서 반환합니다.
     * 
     * @param arrays
     * @return 합쳐진 배열. 모든 배열이 <code>null</code>인 경우 <code>null</code>을 반환합니다.
     * 
     *         <BR>
     * @since 2012. 03. 12.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static char[] merge(char[]... arrays) {

        // null 이 아닌 배열의 인덱스
        int[] evalArr = new int[arrays.length];

        int evalIndex = 0;
        // null이 아닌 배열의 인덱스를 보관합니다.
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i] != null) {
                evalArr[evalIndex++] = i;
            }
        }

        // 실제 데이타를 포함하고 있는 배열들의 길이의 합
        int mergeIndex = 0;
        for (int i = 0; i < evalIndex; i++) {
            mergeIndex += arrays[evalArr[i]].length;
        }

        if (mergeIndex != 0) {
            char[] mergedArray = new char[mergeIndex];

            int copiedLength = 0;
            for (int i = 0; i < evalIndex; i++) {
                System.arraycopy(arrays[evalArr[i]], 0, mergedArray, copiedLength, arrays[evalArr[i]].length);

                copiedLength += arrays[evalArr[i]].length;
            }

            return mergedArray;
        }

        return null;
    }

    /**
     * 2개의 배열을 하나의 배열로 합쳐서 반환합니다.
     * 
     * @param arr1
     * @param arr2
     * @return
     * 
     * @exception NullPointerException
     *                Either of parameters is null
     * 
     *                <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static char[] merge(char[] arr1, char[] arr2) {
        if (arr1 != null && arr2 != null) {
            char[] merged = new char[arr1.length + arr2.length];

            System.arraycopy(arr1, 0, merged, 0, arr1.length);
            System.arraycopy(arr2, 0, merged, arr1.length, arr2.length);

            return merged;
        } else if (arr1 != null) {
            return arr1;
        } else if (arr2 != null) {
            return arr2;
        } else {
            throw new IllegalArgumentException(new NullPointerException("All parameters(char[] arr1, char[] arr2) must not be null: arr1=null, arr2=null"));
        }
    }

    /**
     * 여러 개의 배열을 하나의 배열로 합쳐서 반환합니다.
     * 
     * @param arrays
     * @return 합쳐진 배열. 모든 배열이 <code>null</code>인 경우 <code>null</code>을 반환합니다.
     * 
     *         <BR>
     * @since 2012. 03. 12.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static double[] merge(double[]... arrays) {

        // null 이 아닌 배열의 인덱스
        int[] evalArr = new int[arrays.length];

        int evalIndex = 0;
        // null이 아닌 배열의 인덱스를 보관합니다.
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i] != null) {
                evalArr[evalIndex++] = i;
            }
        }

        // 실제 데이타를 포함하고 있는 배열들의 길이의 합
        int mergeIndex = 0;
        for (int i = 0; i < evalIndex; i++) {
            mergeIndex += arrays[evalArr[i]].length;
        }

        if (mergeIndex != 0) {
            double[] mergedArray = new double[mergeIndex];

            int copiedLength = 0;
            for (int i = 0; i < evalIndex; i++) {
                System.arraycopy(arrays[evalArr[i]], 0, mergedArray, copiedLength, arrays[evalArr[i]].length);

                copiedLength += arrays[evalArr[i]].length;
            }

            return mergedArray;
        }

        return null;
    }

    /**
     * 2개의 배열을 하나의 배열로 합쳐서 반환합니다.
     * 
     * @param arr1
     * @param arr2
     * @return
     * @exception NullPointerException
     *                Either of parameters is null
     * 
     *                <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static double[] merge(double[] arr1, double[] arr2) {
        if (arr1 != null && arr2 != null) {
            double[] merged = new double[arr1.length + arr2.length];

            System.arraycopy(arr1, 0, merged, 0, arr1.length);
            System.arraycopy(arr2, 0, merged, arr1.length, arr2.length);

            return merged;
        } else if (arr1 != null) {
            return arr1;
        } else if (arr2 != null) {
            return arr2;
        } else {
            throw new IllegalArgumentException(new NullPointerException("All parameters(double[] arr1, double[] arr2) must not be null: arr1=null, arr2=null"));
        }
    }

    /**
     * 여러 개의 배열을 하나의 배열로 합쳐서 반환합니다.
     * 
     * @param arrays
     * @return 합쳐진 배열. 모든 배열이 <code>null</code>인 경우 <code>null</code>을 반환합니다.
     * 
     *         <BR>
     * @since 2012. 03. 12.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static float[] merge(float[]... arrays) {

        // null 이 아닌 배열의 인덱스
        int[] evalArr = new int[arrays.length];

        int evalIndex = 0;
        // null이 아닌 배열의 인덱스를 보관합니다.
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i] != null) {
                evalArr[evalIndex++] = i;
            }
        }

        // 실제 데이타를 포함하고 있는 배열들의 길이의 합
        int mergeIndex = 0;
        for (int i = 0; i < evalIndex; i++) {
            mergeIndex += arrays[evalArr[i]].length;
        }

        if (mergeIndex != 0) {
            float[] mergedArray = new float[mergeIndex];

            int copiedLength = 0;
            for (int i = 0; i < evalIndex; i++) {
                System.arraycopy(arrays[evalArr[i]], 0, mergedArray, copiedLength, arrays[evalArr[i]].length);

                copiedLength += arrays[evalArr[i]].length;
            }

            return mergedArray;
        }

        return null;
    }

    /**
     * 2개의 배열을 하나의 배열로 합쳐서 반환합니다.
     * 
     * @param arr1
     * @param arr2
     * @return
     * @exception NullPointerException
     *                Either of parameters is null
     * 
     *                <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static float[] merge(float[] arr1, float[] arr2) {
        if (arr1 != null && arr2 != null) {
            float[] merged = new float[arr1.length + arr2.length];

            System.arraycopy(arr1, 0, merged, 0, arr1.length);
            System.arraycopy(arr2, 0, merged, arr1.length, arr2.length);

            return merged;
        } else if (arr1 != null) {
            return arr1;
        } else if (arr2 != null) {
            return arr2;
        } else {
            throw new IllegalArgumentException(new NullPointerException("All parameters(float[] arr1, float[] arr2) must not be null: arr1=null, arr2=null"));
        }
    }

    /**
     * 여러 개의 배열을 하나의 배열로 합쳐서 반환합니다.
     * 
     * @param arrays
     * @return 합쳐진 배열. 모든 배열이 <code>null</code>인 경우 <code>null</code>을 반환합니다.
     * 
     *         <BR>
     * @since 2012. 03. 12.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static int[] merge(int[]... arrays) {

        // null 이 아닌 배열의 인덱스
        int[] evalArr = new int[arrays.length];

        int evalIndex = 0;
        // null이 아닌 배열의 인덱스를 보관합니다.
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i] != null) {
                evalArr[evalIndex++] = i;
            }
        }

        // 실제 데이타를 포함하고 있는 배열들의 길이의 합
        int mergeIndex = 0;
        for (int i = 0; i < evalIndex; i++) {
            mergeIndex += arrays[evalArr[i]].length;
        }

        if (mergeIndex != 0) {
            int[] mergedArray = new int[mergeIndex];

            int copiedLength = 0;
            for (int i = 0; i < evalIndex; i++) {
                System.arraycopy(arrays[evalArr[i]], 0, mergedArray, copiedLength, arrays[evalArr[i]].length);

                copiedLength += arrays[evalArr[i]].length;
            }

            return mergedArray;
        }

        return null;
    }

    /**
     * 2개의 배열을 하나의 배열로 합쳐서 반환합니다.
     * 
     * @param arr1
     * @param arr2
     * @return
     * @exception NullPointerException
     *                Either of parameters is null
     * 
     *                <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static int[] merge(int[] arr1, int[] arr2) {
        if (arr1 != null && arr2 != null) {
            int[] merged = new int[arr1.length + arr2.length];

            System.arraycopy(arr1, 0, merged, 0, arr1.length);
            System.arraycopy(arr2, 0, merged, arr1.length, arr2.length);

            return merged;
        } else if (arr1 != null) {
            return arr1;
        } else if (arr2 != null) {
            return arr2;
        } else {
            throw new IllegalArgumentException(new NullPointerException("All parameters(int[] arr1, int[] arr2) must not be null: arr1=null, arr2=null"));
        }
    }

    /**
     * 여러 개의 배열을 하나의 배열로 합쳐서 반환합니다.
     * 
     * @param arrays
     * @return 합쳐진 배열. 모든 배열이 <code>null</code>인 경우 <code>null</code>을 반환합니다.
     * 
     *         <BR>
     * @since 2012. 03. 12.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static long[] merge(long[]... arrays) {

        // null 이 아닌 배열의 인덱스
        int[] evalArr = new int[arrays.length];

        int evalIndex = 0;
        // null이 아닌 배열의 인덱스를 보관합니다.
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i] != null) {
                evalArr[evalIndex++] = i;
            }
        }

        // 실제 데이타를 포함하고 있는 배열들의 길이의 합
        int mergeIndex = 0;
        for (int i = 0; i < evalIndex; i++) {
            mergeIndex += arrays[evalArr[i]].length;
        }

        if (mergeIndex != 0) {
            long[] mergedArray = new long[mergeIndex];

            int copiedLength = 0;
            for (int i = 0; i < evalIndex; i++) {
                System.arraycopy(arrays[evalArr[i]], 0, mergedArray, copiedLength, arrays[evalArr[i]].length);

                copiedLength += arrays[evalArr[i]].length;
            }

            return mergedArray;
        }

        return null;
    }

    /**
     * 2개의 배열을 하나의 배열로 합쳐서 반환합니다.
     * 
     * @param arr1
     * @param arr2
     * @return
     * @exception NullPointerException
     *                Either of parameters is null
     * 
     *                <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static long[] merge(long[] arr1, long[] arr2) {
        if (arr1 != null && arr2 != null) {
            long[] merged = new long[arr1.length + arr2.length];

            System.arraycopy(arr1, 0, merged, 0, arr1.length);
            System.arraycopy(arr2, 0, merged, arr1.length, arr2.length);

            return merged;
        } else if (arr1 != null) {
            return arr1;
        } else if (arr2 != null) {
            return arr2;
        } else {
            throw new IllegalArgumentException(new NullPointerException("All parameters(long[] arr1, long[] arr2) must not be null: arr1=null, arr2=null"));
        }
    }

    /**
     * 여러 개의 배열을 하나의 배열로 합쳐서 반환합니다.
     * 
     * @param arrays
     * @return 합쳐진 배열. 모든 배열이 <code>null</code>인 경우 <code>null</code>을 반환합니다.
     * 
     *         <BR>
     * @since 2012. 03. 12.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static short[] merge(short[]... arrays) {

        // null 이 아닌 배열의 인덱스
        int[] evalArr = new int[arrays.length];

        int evalIndex = 0;
        // null이 아닌 배열의 인덱스를 보관합니다.
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i] != null) {
                evalArr[evalIndex++] = i;
            }
        }

        // 실제 데이타를 포함하고 있는 배열들의 길이의 합
        int mergeIndex = 0;
        for (int i = 0; i < evalIndex; i++) {
            mergeIndex += arrays[evalArr[i]].length;
        }

        if (mergeIndex != 0) {
            short[] mergedArray = new short[mergeIndex];

            int copiedLength = 0;
            for (int i = 0; i < evalIndex; i++) {
                System.arraycopy(arrays[evalArr[i]], 0, mergedArray, copiedLength, arrays[evalArr[i]].length);

                copiedLength += arrays[evalArr[i]].length;
            }

            return mergedArray;
        }

        return null;
    }

    /**
     * 2개의 배열을 하나의 배열로 합쳐서 반환합니다.
     * 
     * @param arr1
     * @param arr2
     * @return
     * @exception NullPointerException
     *                Either of parameters is null
     * 
     *                <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static short[] merge(short[] arr1, short[] arr2) {
        if (arr1 != null && arr2 != null) {
            short[] merged = new short[arr1.length + arr2.length];

            System.arraycopy(arr1, 0, merged, 0, arr1.length);
            System.arraycopy(arr2, 0, merged, arr1.length, arr2.length);

            return merged;
        } else if (arr1 != null) {
            return arr1;
        } else if (arr2 != null) {
            return arr2;
        } else {
            throw new IllegalArgumentException(new NullPointerException("All parameters(short[] arr1, short[] arr2) must not be null: arr1=null, arr2=null"));
        }
    }

    /**
     * 여러 개의 배열을 하나의 배열로 합쳐서 반환합니다.
     * 
     * @param arrays
     * @return 합쳐진 배열. 모든 배열이 <code>null</code>인 경우 <code>null</code>을 반환합니다.
     * 
     *         <BR>
     * @since 2012. 03. 12.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <T> T[] merge(T[]... arrays) {

        // null 이 아닌 배열의 인덱스
        int[] evalArr = new int[arrays.length];

        int evalIndex = 0;
        // null이 아닌 배열의 인덱스를 보관합니다.
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i] != null) {
                evalArr[evalIndex++] = i;
            }
        }

        // 실제 데이타를 포함하고 있는 배열들의 길이의 합
        int mergeIndex = 0;
        for (int i = 0; i < evalIndex; i++) {
            mergeIndex += arrays[evalArr[i]].length;
        }

        if (mergeIndex != 0) {
            T[] mergedArray = (T[]) Array.newInstance(arrays.getClass().getComponentType().getComponentType(), mergeIndex);

            int copiedLength = 0;
            for (int i = 0; i < evalIndex; i++) {
                System.arraycopy(arrays[evalArr[i]], 0, mergedArray, copiedLength, arrays[evalArr[i]].length);

                copiedLength += arrays[evalArr[i]].length;
            }

            return mergedArray;
        }

        return null;
    }

    /**
     * 2개의 배열을 하나의 배열로 합쳐서 반환합니다.
     * 
     * @param arr1
     * @param arr2
     * @return
     * @exception NullPointerException
     *                Either of parameters is null
     * 
     *                <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * @see {@link Array#newInstance(Class, int)}
     */

    public static <T> T[] merge(T[] arr1, T[] arr2) {

        if (arr1 != null && arr2 != null) {

            assertComponentType(arr1, arr2);

            T[] merged = ((T[]) Array.newInstance(arr1.getClass().getComponentType(), arr1.length + arr2.length));

            System.arraycopy(arr1, 0, merged, 0, arr1.length);
            System.arraycopy(arr2, 0, merged, arr1.length, arr2.length);

            return merged;
        } else if (arr1 != null) {
            return arr1;
        } else if (arr2 != null) {
            return arr2;
        } else {
            throw new IllegalArgumentException(new NullPointerException("All parameters(T[] arr1, T[] arr2) must not be null: arr1=null, arr2=null"));
        }
    }

    /**
     * 타입이 서로 다른 2개의 배열을 하나의 배열로 합쳐서 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 12. 28.        박준홍         최초 작성
     * </pre>
     *
     * @param array
     * @param values
     * @return
     * @exception NullPointerException
     *                Either of parameters is null
     *
     * @since 2021. 12. 28.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     */
    public static Object[] objectArray(Object value, Object[] array) {

        if (array != null && value != null) {
            Object[] newarr = new Object[array.length + 1];
            newarr[0] = value;
            System.arraycopy(array, 0, newarr, 1, array.length);

            return newarr;
        } else if (array != null) {
            return array;
        } else if (value != null) {
            return new Object[] { value };
        } else {
            throw new IllegalArgumentException(new NullPointerException("All parameters(T[] arr, T[] values) must not be null: arr=null, values=null"));
        }
    }

    /**
     * 타입이 서로 다른 2개의 배열을 하나의 배열로 합쳐서 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 12. 28.		박준홍			최초 작성
     * </pre>
     *
     * @param array
     * @param values
     * @return
     * @exception NullPointerException
     *                Either of parameters is null
     *
     * @since 2021. 12. 28.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     */
    public static Object[] objectArray(Object[] array, Object... values) {

        if (array != null && values != null) {
            Object[] newarr = new Object[array.length + values.length];
            System.arraycopy(array, 0, newarr, 0, array.length);
            System.arraycopy(values, 0, newarr, array.length, values.length);

            return newarr;
        } else if (array != null) {
            return array;
        } else if (values != null) {
            return values;
        } else {
            throw new IllegalArgumentException(new NullPointerException("All parameters(T[] arr, T[] values) must not be null: arr=null, values=null"));
        }
    }

    /**
     * 기존 배열에 새로운 데이터를 맨 앞에 추가한 후, 새로운 배열을 반환합니다.<br>
     * 배열이 <code>null</code>인 경우 새로운 배열을 생성한 후 추가합니다.
     * 
     * @param array
     * @param value
     * 
     * @return <BR>
     * @since 2012. 03. 12.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static boolean[] prepend(boolean[] array, boolean value) {
        boolean[] newArray = null;

        if (array != null && array.length > 0) {
            newArray = new boolean[array.length + 1];

            System.arraycopy(array, 0, newArray, 1, array.length);
        } else {
            newArray = new boolean[1];
        }

        newArray[0] = value;
        return newArray;
    }

    /**
     * 기존 배열에 새로운 데이터를 맨 앞에 추가한 후, 새로운 배열을 반환합니다.<br>
     * 배열이 <code>null</code>인 경우 새로운 배열을 생성한 후 추가합니다.
     * 
     * @param array
     * @param value
     * 
     * @return <BR>
     * @since 2012. 03. 12.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static byte[] prepend(byte[] array, byte value) {
        byte[] newArray = null;

        if (array != null && array.length > 0) {
            newArray = new byte[array.length + 1];

            System.arraycopy(array, 0, newArray, 1, array.length);
        } else {
            newArray = new byte[1];
        }

        newArray[0] = value;
        return newArray;
    }

    /**
     * 기존 배열에 새로운 데이터를 맨 앞에 추가한 후, 새로운 배열을 반환합니다.<br>
     * 배열이 <code>null</code>인 경우 새로운 배열을 생성한 후 추가합니다.
     * 
     * @param array
     * @param value
     * 
     * @return <BR>
     * @since 2012. 03. 12.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static char[] prepend(char[] array, char value) {
        char[] newArray = null;

        if (array != null && array.length > 0) {
            newArray = new char[array.length + 1];

            System.arraycopy(array, 0, newArray, 1, array.length);
        } else {
            newArray = new char[1];
        }

        newArray[0] = value;
        return newArray;
    }

    /**
     * 기존 배열에 새로운 데이터를 맨 앞에 추가한 후, 새로운 배열을 반환합니다.<br>
     * 배열이 <code>null</code>인 경우 새로운 배열을 생성한 후 추가합니다.
     * 
     * @param array
     * @param value
     * 
     * @return <BR>
     * @since 2012. 03. 12.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static double[] prepend(double[] array, double value) {
        double[] newArray = null;

        if (array != null && array.length > 0) {
            newArray = new double[array.length + 1];

            System.arraycopy(array, 0, newArray, 1, array.length);
        } else {
            newArray = new double[1];
        }

        newArray[0] = value;
        return newArray;
    }

    /**
     * 기존 배열에 새로운 데이터를 맨 앞에 추가한 후, 새로운 배열을 반환합니다.<br>
     * 배열이 <code>null</code>인 경우 새로운 배열을 생성한 후 추가합니다.
     * 
     * @param array
     * @param value
     * 
     * @return <BR>
     * @since 2012. 03. 12.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static float[] prepend(float[] array, float value) {
        float[] newArray = null;

        if (array != null && array.length > 0) {
            newArray = new float[array.length + 1];

            System.arraycopy(array, 0, newArray, 1, array.length);
        } else {
            newArray = new float[1];
        }

        newArray[0] = value;
        return newArray;
    }

    /**
     * 기존 배열에 새로운 데이터를 맨 앞에 추가한 후, 새로운 배열을 반환합니다.<br>
     * 배열이 <code>null</code>인 경우 새로운 배열을 생성한 후 추가합니다.
     * 
     * @param array
     * @param value
     * 
     * @return <BR>
     * @since 2012. 03. 12.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static int[] prepend(int[] array, int value) {
        int[] newArray = null;

        if (array != null && array.length > 0) {
            newArray = new int[array.length + 1];

            System.arraycopy(array, 0, newArray, 1, array.length);
        } else {
            newArray = new int[1];
        }

        newArray[0] = value;
        return newArray;
    }

    /**
     * 기존 배열에 새로운 데이터를 맨 앞에 추가한 후, 새로운 배열을 반환합니다.<br>
     * 배열이 <code>null</code>인 경우 새로운 배열을 생성한 후 추가합니다.
     * 
     * @param array
     * @param value
     * 
     * @return <BR>
     * @since 2012. 03. 12.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static long[] prepend(long[] array, long value) {
        long[] newArray = null;

        if (array != null && array.length > 0) {
            newArray = new long[array.length + 1];

            System.arraycopy(array, 0, newArray, 1, array.length);
        } else {
            newArray = new long[1];
        }

        newArray[0] = value;
        return newArray;
    }

    /**
     * 기존 배열에 새로운 데이터를 맨 앞에 추가한 후, 새로운 배열을 반환합니다.<br>
     * 배열이 <code>null</code>인 경우 새로운 배열을 생성한 후 추가합니다.
     * 
     * @param array
     * @param value
     * 
     * @return <BR>
     * @since 2012. 03. 12.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static short[] prepend(short[] array, short value) {
        short[] newArray = null;

        if (array != null && array.length > 0) {
            newArray = new short[array.length + 1];

            System.arraycopy(array, 0, newArray, 1, array.length);
        } else {
            newArray = new short[1];
        }

        newArray[0] = value;
        return newArray;
    }

    /**
     * 기존 배열에 새로운 데이터를 맨 앞에 추가한 후, 새로운 배열을 반환합니다.<br>
     * 배열이 <code>null</code>인 경우 새로운 배열을 생성한 후 추가합니다.
     * 
     * @param array
     * @param value
     * 
     * @return <BR>
     * @since 2012. 03. 12.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <T> T[] prepend(T[] array, T value) {

        T[] newArray = null;

        if (array != null && value != null) {

            assertComponentType(array, value.getClass());

            newArray = (T[]) Array.newInstance(array.getClass().getComponentType(), array.length + 1);
            System.arraycopy(array, 0, newArray, 1, array.length);
            newArray[0] = value;
        } else if (array != null) {
            newArray = (T[]) Array.newInstance(array.getClass().getComponentType(), 1);
        } else if (value != null) {
            newArray = (T[]) Array.newInstance(value.getClass(), 1);
            newArray[0] = value;
        } else {
            throw new IllegalArgumentException(new NullPointerException("All parameters(T[] array, T value) must not be null: arr=null, value=null"));
        }

        return newArray;
    }

    /**
     * 주어진 배열에서 대상 값과 같은 모든 값을 제거한 후 배열을 반환합니다.
     * 
     * @param array
     * @param value
     * @return
     * 
     * @exception IllegalArgumentException
     *                주어진 배열이 <code>null</code>인 경우
     * 
     *                <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static boolean[] removeAll(boolean[] array, boolean value) {

        if (array == null) {
            throw new IllegalArgumentException(new NullPointerException("A parameter(boolean[] array) must not be 'null': array=null"));
        }

        int[] delIndice = new int[array.length];
        int delCount = 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                delIndice[delCount++] = i;
            }
        }

        if (delCount < 1) {
            return array;
        }

        delIndice = copyOf(delIndice, delCount);

        boolean[] newArray = new boolean[array.length - delCount];

        int si = 0;
        int bi = -1;
        int copiedLength = 0;
        int i = 0;
        for (; i < delCount; i++) {
            System.arraycopy(array, si, newArray, copiedLength, delIndice[i] - bi - 1);
            si = delIndice[i] + 1;
            copiedLength += delIndice[i] - bi - 1;
            bi = delIndice[i];
        }

        if (delIndice[delCount - 1] < array.length - 1) {
            System.arraycopy(array, si, newArray, copiedLength, newArray.length - copiedLength);
        }

        return newArray;

    }

    /**
     * 주어진 배열에서 대상 값과 같은 모든 값을 제거한 후 배열을 반환합니다.
     * 
     * @param array
     * @param value
     * @return
     * 
     * @exception IllegalArgumentException
     *                주어진 배열이 <code>null</code>인 경우
     * 
     *                <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static byte[] removeAll(byte[] array, byte value) {

        if (array == null) {
            throw new IllegalArgumentException(new NullPointerException("A parameter(byte[] array) must not be 'null': array=null"));
        }

        int[] delIndice = new int[array.length];
        int delCount = 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                delIndice[delCount++] = i;
            }
        }

        if (delCount < 1) {
            return array;
        }

        delIndice = copyOf(delIndice, delCount);

        byte[] newArray = new byte[array.length - delCount];

        int si = 0;
        int bi = -1;
        int copiedLength = 0;
        int i = 0;
        for (; i < delCount; i++) {
            System.arraycopy(array, si, newArray, copiedLength, delIndice[i] - bi - 1);
            si = delIndice[i] + 1;
            copiedLength += delIndice[i] - bi - 1;
            bi = delIndice[i];
        }

        if (delIndice[delCount - 1] < array.length - 1) {
            System.arraycopy(array, si, newArray, copiedLength, newArray.length - copiedLength);
        }

        return newArray;

    }

    /**
     * 주어진 배열에서 대상 값과 같은 모든 값을 제거한 후 배열을 반환합니다.
     * 
     * @param array
     * @param value
     * @return
     * 
     * @exception IllegalArgumentException
     *                주어진 배열이 <code>null</code>인 경우
     * 
     *                <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static char[] removeAll(char[] array, char value) {

        if (array == null) {
            throw new IllegalArgumentException(new NullPointerException("A parameter(char[] array) must not be 'null': array=null"));
        }

        int[] delIndice = new int[array.length];
        int delCount = 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                delIndice[delCount++] = i;
            }
        }

        if (delCount < 1) {
            return array;
        }

        delIndice = copyOf(delIndice, delCount);

        char[] newArray = new char[array.length - delCount];

        int si = 0;
        int bi = -1;
        int copiedLength = 0;
        int i = 0;
        for (; i < delCount; i++) {
            System.arraycopy(array, si, newArray, copiedLength, delIndice[i] - bi - 1);
            si = delIndice[i] + 1;
            copiedLength += delIndice[i] - bi - 1;
            bi = delIndice[i];
        }

        if (delIndice[delCount - 1] < array.length - 1) {
            System.arraycopy(array, si, newArray, copiedLength, newArray.length - copiedLength);
        }

        return newArray;
    }

    /**
     * 주어진 배열에서 대상 값과 같은 모든 값을 제거한 후 배열을 반환합니다.
     * 
     * @param array
     * @param value
     * @return
     * 
     * @exception IllegalArgumentException
     *                주어진 배열이 <code>null</code>인 경우
     * 
     *                <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static double[] removeAll(double[] array, double value) {

        if (array == null) {
            throw new IllegalArgumentException(new NullPointerException("A parameter(double[] array) must not be 'null': array=null"));
        }

        int[] delIndice = new int[array.length];
        int delCount = 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                delIndice[delCount++] = i;
            }
        }

        if (delCount < 1) {
            return array;
        }

        delIndice = copyOf(delIndice, delCount);

        double[] newArray = new double[array.length - delCount];

        int si = 0;
        int bi = -1;
        int copiedLength = 0;
        int i = 0;
        for (; i < delCount; i++) {
            System.arraycopy(array, si, newArray, copiedLength, delIndice[i] - bi - 1);
            si = delIndice[i] + 1;
            copiedLength += delIndice[i] - bi - 1;
            bi = delIndice[i];
        }

        if (delIndice[delCount - 1] < array.length - 1) {
            System.arraycopy(array, si, newArray, copiedLength, newArray.length - copiedLength);
        }

        return newArray;
    }

    /**
     * 주어진 배열에서 대상 값과 같은 모든 값을 제거한 후 배열을 반환합니다.
     * 
     * @param array
     * @param value
     * @return
     * 
     * @exception IllegalArgumentException
     *                주어진 배열이 <code>null</code>인 경우
     * 
     *                <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static float[] removeAll(float[] array, float value) {

        if (array == null) {
            throw new IllegalArgumentException(new NullPointerException("A parameter(float[] array) must not be 'null': array=null"));
        }

        int[] delIndice = new int[array.length];
        int delCount = 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                delIndice[delCount++] = i;
            }
        }

        if (delCount < 1) {
            return array;
        }

        delIndice = copyOf(delIndice, delCount);

        float[] newArray = new float[array.length - delCount];

        int si = 0;
        int bi = -1;
        int copiedLength = 0;
        int i = 0;
        for (; i < delCount; i++) {
            System.arraycopy(array, si, newArray, copiedLength, delIndice[i] - bi - 1);
            si = delIndice[i] + 1;
            copiedLength += delIndice[i] - bi - 1;
            bi = delIndice[i];
        }

        if (delIndice[delCount - 1] < array.length - 1) {
            System.arraycopy(array, si, newArray, copiedLength, newArray.length - copiedLength);
        }

        return newArray;
    }

    /**
     * 주어진 배열에서 대상 값과 같은 모든 값을 제거한 후 배열을 반환합니다.
     * 
     * @param array
     * @param value
     * @return
     * 
     * @exception IllegalArgumentException
     *                주어진 배열이 <code>null</code>인 경우
     * 
     *                <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static int[] removeAll(int[] array, int value) {

        if (array == null) {
            throw new IllegalArgumentException(new NullPointerException("A parameter(int[] array) must not be 'null': array=null"));
        }

        int[] delIndice = new int[array.length];
        int delCount = 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                delIndice[delCount++] = i;
            }
        }

        if (delCount < 1) {
            return array;
        }

        delIndice = copyOf(delIndice, delCount);

        int[] newArray = new int[array.length - delCount];

        int si = 0;
        int bi = -1;
        int copiedLength = 0;
        int i = 0;
        for (; i < delCount; i++) {
            System.arraycopy(array, si, newArray, copiedLength, delIndice[i] - bi - 1);
            si = delIndice[i] + 1;
            copiedLength += delIndice[i] - bi - 1;
            bi = delIndice[i];
        }

        if (delIndice[delCount - 1] < array.length - 1) {
            System.arraycopy(array, si, newArray, copiedLength, newArray.length - copiedLength);
        }

        return newArray;
    }

    /**
     * 주어진 배열에서 대상 값과 같은 모든 값을 제거한 후 배열을 반환합니다.
     * 
     * @param array
     * @param value
     * @return
     * 
     * @exception IllegalArgumentException
     *                주어진 배열이 <code>null</code>인 경우
     * 
     *                <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static long[] removeAll(long[] array, long value) {

        if (array == null) {
            throw new IllegalArgumentException(new NullPointerException("A parameter(long[] array) must not be 'null': array=null"));
        }

        int[] delIndice = new int[array.length];
        int delCount = 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                delIndice[delCount++] = i;
            }
        }

        if (delCount < 1) {
            return array;
        }

        delIndice = copyOf(delIndice, delCount);

        long[] newArray = new long[array.length - delCount];

        int si = 0;
        int bi = -1;
        int copiedLength = 0;
        int i = 0;
        for (; i < delCount; i++) {
            System.arraycopy(array, si, newArray, copiedLength, delIndice[i] - bi - 1);
            si = delIndice[i] + 1;
            copiedLength += delIndice[i] - bi - 1;
            bi = delIndice[i];
        }

        if (delIndice[delCount - 1] < array.length - 1) {
            System.arraycopy(array, si, newArray, copiedLength, newArray.length - copiedLength);
        }

        return newArray;
    }

    /**
     * 주어진 배열에서 대상 값과 같은 모든 값을 제거한 후 배열을 반환합니다.
     * 
     * @param array
     * @param value
     * @return
     * 
     * @exception IllegalArgumentException
     *                주어진 배열이 <code>null</code>인 경우
     * 
     *                <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static short[] removeAll(short[] array, short value) {

        if (array == null) {
            throw new IllegalArgumentException(new NullPointerException("A parameter(short[] array) must not be 'null': array=null"));
        }

        int[] delIndice = new int[array.length];
        int delCount = 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                delIndice[delCount++] = i;
            }
        }

        if (delCount < 1) {
            return array;
        }

        delIndice = copyOf(delIndice, delCount);

        short[] newArray = new short[array.length - delCount];

        int si = 0;
        int bi = -1;
        int copiedLength = 0;
        int i = 0;
        for (; i < delCount; i++) {
            System.arraycopy(array, si, newArray, copiedLength, delIndice[i] - bi - 1);
            si = delIndice[i] + 1;
            copiedLength += delIndice[i] - bi - 1;
            bi = delIndice[i];
        }

        if (delIndice[delCount - 1] < array.length - 1) {
            System.arraycopy(array, si, newArray, copiedLength, newArray.length - copiedLength);
        }

        return newArray;
    }

    public static <T> T[] removeAll(T[] array, Predicate<T> filter) {
        if (array == null) {
            throw new IllegalArgumentException(new NullPointerException("A parameter(T[] array) must not be 'null': array=null"));
        }

        if (filter == null) {
            throw new IllegalArgumentException(new NullPointerException("A parameter(Function<T, Boolean> filter ) must not be 'null': filter=null"));
        }

        int[] delIndice = new int[array.length];
        int delCount = 0;

        for (int i = 0; i < array.length; i++) {
            if (filter.test(array[i])) {
                delIndice[delCount++] = i;
            }
        }

        if (delCount < 1) {
            return array;
        }

        delIndice = copyOf(delIndice, delCount);

        T[] newArray = (T[]) Array.newInstance(array.getClass().getComponentType(), array.length - delCount);

        int si = 0;
        int bi = -1;
        int copiedLength = 0;
        int i = 0;
        for (; i < delCount; i++) {
            System.arraycopy(array, si, newArray, copiedLength, delIndice[i] - bi - 1);
            si = delIndice[i] + 1;
            copiedLength += delIndice[i] - bi - 1;
            bi = delIndice[i];
        }

        if (delIndice[delCount - 1] < array.length - 1) {
            System.arraycopy(array, si, newArray, copiedLength, newArray.length - copiedLength);
        }

        return newArray;
    }

    /**
     * 주어진 배열에서 대상 값과 같은 모든 값을 제거한 후 배열을 반환합니다.
     * 
     * @param array
     * @param value
     * 
     * @return
     * 
     * @exception IllegalArgumentException
     *                주어진 배열이 <code>null</code>인 경우
     * 
     *                <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <T> T[] removeAll(T[] array, T value) {
        return removeAll(array, value, null);
    }

    /**
     * 주어진 배열에서 대상 값과 같은 모든 값을 제거한 후 배열을 반환합니다.
     * 
     * @param array
     * @param value
     * @param equivalent
     *            클래스 또는 타입 T의 equals(T obj) 메소드가 아닌 다른 비교 기준을 제공하는 객체
     * @return
     * 
     * @exception IllegalArgumentException
     *                주어진 배열이 <code>null</code>인 경우
     * 
     *                <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <T> T[] removeAll(T[] array, T value, IEquivalent<T> equivalent) {

        if (array == null) {
            throw new IllegalArgumentException(new NullPointerException("A parameter(T[] array) must not be 'null': array=null"));
        }

        if (equivalent == null) {
            equivalent = new DefaultEquivalent<T>();
        }

        int[] delIndice = new int[array.length];
        int delCount = 0;

        for (int i = 0; i < array.length; i++) {
            if (equivalent.equals(array[i], value)) {
                delIndice[delCount++] = i;
            }
        }

        if (delCount < 1) {
            return array;
        }

        delIndice = copyOf(delIndice, delCount);

        T[] newArray = (T[]) Array.newInstance(array.getClass().getComponentType(), array.length - delCount);

        int si = 0;
        int bi = -1;
        int copiedLength = 0;
        int i = 0;
        for (; i < delCount; i++) {
            System.arraycopy(array, si, newArray, copiedLength, delIndice[i] - bi - 1);
            si = delIndice[i] + 1;
            copiedLength += delIndice[i] - bi - 1;
            bi = delIndice[i];
        }

        if (delIndice[delCount - 1] < array.length - 1) {
            System.arraycopy(array, si, newArray, copiedLength, newArray.length - copiedLength);
        }

        return newArray;
    }

    /**
     * 주어진 배열에서 <code>index</code>에 해당하는 값을 제거한 배열을 반환합니다.
     * 
     * @param array
     * @param index
     * @return <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static boolean[] removeAt(boolean[] array, int index) {
        if (index > -1 && index < array.length) {
            boolean[] newArray = new boolean[array.length - 1];

            System.arraycopy(array, 0, newArray, 0, index);
            System.arraycopy(array, index + 1, newArray, index, newArray.length - index);

            return newArray;
        } else {
            return array;
        }
    }

    /**
     * 주어진 배열에서 <code>index</code>에 해당하는 값을 제거한 배열을 반환합니다.
     * 
     * @param array
     * @param index
     * @return <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static byte[] removeAt(byte[] array, int index) {
        if (index > -1 && index < array.length) {
            byte[] newArray = new byte[array.length - 1];

            System.arraycopy(array, 0, newArray, 0, index);
            System.arraycopy(array, index + 1, newArray, index, newArray.length - index);

            return newArray;
        } else {
            return array;
        }
    }

    /**
     * 주어진 배열에서 <code>index</code>에 해당하는 값을 제거한 배열을 반환합니다.
     * 
     * @param array
     * @param index
     * @return <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static char[] removeAt(char[] array, int index) {
        if (index > -1 && index < array.length) {
            char[] newArray = new char[array.length - 1];

            System.arraycopy(array, 0, newArray, 0, index);
            System.arraycopy(array, index + 1, newArray, index, newArray.length - index);

            return newArray;
        } else {
            return array;
        }
    }

    /**
     * 주어진 배열에서 <code>index</code>에 해당하는 값을 제거한 배열을 반환합니다.
     * 
     * @param array
     * @param index
     * @return <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static double[] removeAt(double[] array, int index) {
        if (index > -1 && index < array.length) {
            double[] newArray = new double[array.length - 1];

            System.arraycopy(array, 0, newArray, 0, index);
            System.arraycopy(array, index + 1, newArray, index, newArray.length - index);

            return newArray;
        } else {
            return array;
        }
    }

    /**
     * 주어진 배열에서 <code>index</code>에 해당하는 값을 제거한 배열을 반환합니다.
     * 
     * @param array
     * @param index
     * @return <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static float[] removeAt(float[] array, int index) {
        if (index > -1 && index < array.length) {
            float[] newArray = new float[array.length - 1];

            System.arraycopy(array, 0, newArray, 0, index);
            System.arraycopy(array, index + 1, newArray, index, newArray.length - index);

            return newArray;
        } else {
            return array;
        }
    }

    /**
     * 주어진 배열에서 <code>index</code>에 해당하는 값을 제거한 배열을 반환합니다.
     * 
     * @param array
     * @param index
     * @return <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static int[] removeAt(int[] array, int index) {
        if (index > -1 && index < array.length) {
            int[] newArray = new int[array.length - 1];

            System.arraycopy(array, 0, newArray, 0, index);
            System.arraycopy(array, index + 1, newArray, index, newArray.length - index);

            return newArray;
        } else {
            return array;
        }
    }

    /**
     * 주어진 배열에서 <code>index</code>에 해당하는 값을 제거한 배열을 반환합니다.
     * 
     * @param array
     * @param index
     * @return <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static long[] removeAt(long[] array, int index) {
        if (index > -1 && index < array.length) {
            long[] newArray = new long[array.length - 1];

            System.arraycopy(array, 0, newArray, 0, index);
            System.arraycopy(array, index + 1, newArray, index, newArray.length - index);

            return newArray;
        } else {
            return array;
        }
    }

    /**
     * 주어진 배열에서 <code>index</code>에 해당하는 값을 제거한 배열을 반환합니다.
     * 
     * @param array
     * @param index
     * @return <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static short[] removeAt(short[] array, int index) {
        if (index > -1 && index < array.length) {
            short[] newArray = new short[array.length - 1];

            System.arraycopy(array, 0, newArray, 0, index);
            System.arraycopy(array, index + 1, newArray, index, newArray.length - index);

            return newArray;
        } else {
            return array;
        }
    }

    /**
     * 주어진 배열에서 <code>index</code>에 해당하는 값을 제거한 배열을 반환합니다.
     * 
     * @param array
     * @param index
     * @return <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */

    public static <T> T[] removeAt(T[] array, int index) {
        if (index > -1 && index < array.length) {
            T[] newArray = (T[]) Array.newInstance(array.getClass().getComponentType(), array.length - 1);

            System.arraycopy(array, 0, newArray, 0, index);
            System.arraycopy(array, index + 1, newArray, index, newArray.length - index);

            return newArray;
        } else {
            return array;
        }
    }

    /**
     * 주어진 배열에서 대상 값과 같은 첫번째 값을 제거한 후 배열을 반환합니다.
     * 
     * @param array
     * @param value
     * @return
     * 
     * @exception IllegalArgumentException
     *                주어진 배열이 <code>null</code>인 경우
     * 
     *                <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static boolean[] removeFirst(boolean[] array, boolean value) {
        if (array != null) {
            int i = 0;
            for (; i < array.length; i++) {
                if (array[i] == value) {
                    break;
                }
            }

            return removeAt(array, i);

        } else {
            throw new IllegalArgumentException(new NullPointerException("A parameter(boolean[] array) must not be 'null': array=null"));
        }
    }

    /**
     * 주어진 배열에서 대상 값과 같은 첫번째 값을 제거한 후 배열을 반환합니다.
     * 
     * @param array
     * @param value
     * @return
     * 
     * @exception IllegalArgumentException
     *                주어진 배열이 <code>null</code>인 경우
     * 
     *                <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static byte[] removeFirst(byte[] array, byte value) {
        if (array != null) {
            int i = 0;
            for (; i < array.length; i++) {
                if (array[i] == value) {
                    break;
                }
            }

            return removeAt(array, i);

        } else {
            throw new IllegalArgumentException(new NullPointerException("A parameter(byte[] array) must not be 'null': array=null"));
        }
    }

    /**
     * 주어진 배열에서 대상 값과 같은 첫번째 값을 제거한 후 배열을 반환합니다.
     * 
     * @param array
     * @param value
     * @return
     * 
     * @exception IllegalArgumentException
     *                주어진 배열이 <code>null</code>인 경우
     * 
     *                <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static char[] removeFirst(char[] array, char value) {
        if (array != null) {
            int i = 0;
            for (; i < array.length; i++) {
                if (array[i] == value) {
                    break;
                }
            }

            return removeAt(array, i);

        } else {
            throw new IllegalArgumentException(new NullPointerException("A parameter(char[] array) must not be 'null': array=null"));
        }
    }

    /**
     * 주어진 배열에서 대상 값과 같은 첫번째 값을 제거한 후 배열을 반환합니다.
     * 
     * @param array
     * @param value
     * @return
     * 
     * @exception IllegalArgumentException
     *                주어진 배열이 <code>null</code>인 경우
     * 
     *                <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static double[] removeFirst(double[] array, double value) {
        if (array != null) {
            int i = 0;
            for (; i < array.length; i++) {
                if (array[i] == value) {
                    break;
                }
            }

            return removeAt(array, i);

        } else {
            throw new IllegalArgumentException(new NullPointerException("A parameter(double[] array) must not be 'null': array=null"));
        }
    }

    /**
     * 주어진 배열에서 대상 값과 같은 첫번째 값을 제거한 후 배열을 반환합니다.
     * 
     * @param array
     * @param value
     * @return
     * 
     * @exception IllegalArgumentException
     *                주어진 배열이 <code>null</code>인 경우
     * 
     *                <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static float[] removeFirst(float[] array, float value) {
        if (array != null) {
            int i = 0;
            for (; i < array.length; i++) {
                if (array[i] == value) {
                    break;
                }
            }

            return removeAt(array, i);

        } else {
            throw new IllegalArgumentException(new NullPointerException("A parameter(float[] array) must not be 'null': array=null"));
        }
    }

    /**
     * 주어진 배열에서 대상 값과 같은 첫번째 값을 제거한 후 배열을 반환합니다.
     * 
     * @param array
     * @param value
     * @return
     * 
     * @exception IllegalArgumentException
     *                주어진 배열이 <code>null</code>인 경우
     * 
     *                <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static int[] removeFirst(int[] array, int value) {
        if (array != null) {
            int i = 0;
            for (; i < array.length; i++) {
                if (array[i] == value) {
                    break;
                }
            }

            return removeAt(array, i);

        } else {
            throw new IllegalArgumentException(new NullPointerException("A parameter(int[] array) must not be 'null': array=null"));
        }
    }

    /**
     * 주어진 배열에서 대상 값과 같은 첫번째 값을 제거한 후 배열을 반환합니다.
     * 
     * @param array
     * @param value
     * @return
     * 
     * @exception IllegalArgumentException
     *                주어진 배열이 <code>null</code>인 경우
     * 
     *                <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static long[] removeFirst(long[] array, long value) {
        if (array != null) {
            int i = 0;
            for (; i < array.length; i++) {
                if (array[i] == value) {
                    break;
                }
            }

            return removeAt(array, i);

        } else {
            throw new IllegalArgumentException(new NullPointerException("A parameter(long[] array) must not be 'null': array=null"));
        }
    }

    /**
     * 주어진 배열에서 대상 값과 같은 첫번째 값을 제거한 후 배열을 반환합니다.
     * 
     * @param array
     * @param value
     * @return
     * 
     * @exception IllegalArgumentException
     *                주어진 배열이 <code>null</code>인 경우
     * 
     *                <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static short[] removeFirst(short[] array, short value) {
        if (array != null) {
            int i = 0;
            for (; i < array.length; i++) {
                if (array[i] == value) {
                    break;
                }
            }

            return removeAt(array, i);

        } else {
            throw new IllegalArgumentException(new NullPointerException("A parameter(short[] array) must not be 'null': array=null"));
        }
    }

    /**
     * 추가할 메소드의 제네닉 메소드
     */

    /**
     * 주어진 배열에서 대상 값과 같은 첫번째 값을 제거한 후 배열을 반환합니다.
     * 
     * @param array
     * @param value
     * 
     * @return
     * 
     * @exception IllegalArgumentException
     *                주어진 배열이 <code>null</code>인 경우
     * 
     *                <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <T> T[] removeFirst(T[] array, T value) {
        return removeFirst(array, value, null);
    }

    /**
     * 주어진 배열에서 대상 값과 같은 첫번째 값을 제거한 후 배열을 반환합니다.
     * 
     * @param array
     * @param value
     * @param equivalent
     *            클래스 또는 타입 T의 equals(T obj) 메소드가 아닌 다른 비교 기준을 제공하는 객체
     * @return
     * 
     * @exception IllegalArgumentException
     *                주어진 배열이 <code>null</code>인 경우
     * 
     *                <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <T> T[] removeFirst(T[] array, T value, IEquivalent<T> equivalent) {
        if (array != null) {
            if (equivalent == null) {
                equivalent = new DefaultEquivalent<T>();
            }
            int i = 0;
            for (; i < array.length; i++) {
                if (equivalent.equals(array[i], value)) {
                    break;
                }
            }

            return removeAt(array, i);

        } else {
            throw new IllegalArgumentException(new NullPointerException("A parameter(T[] array) must not be 'null': array=null"));
        }
    }

    /**
     * 주어진 배열에서 대상 값과 같은 마지막 값을 제거한 후 배열을 반환합니다.
     * 
     * @param array
     * @param value
     * @return
     * 
     * @exception IllegalArgumentException
     *                주어진 배열이 <code>null</code>인 경우
     * 
     *                <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static boolean[] removeLast(boolean[] array, boolean value) {
        if (array != null) {
            int i = array.length - 1;
            for (; i > -1; i--) {
                if (array[i] == value) {
                    break;
                }
            }

            return removeAt(array, i);

        } else {
            throw new IllegalArgumentException(new NullPointerException("A parameter(boolean[] array) must not be 'null': array=null"));
        }
    }

    /**
     * 주어진 배열에서 대상 값과 같은 마지막 값을 제거한 후 배열을 반환합니다.
     * 
     * @param array
     * @param value
     * @return
     * 
     * @exception IllegalArgumentException
     *                주어진 배열이 <code>null</code>인 경우
     * 
     *                <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static byte[] removeLast(byte[] array, byte value) {
        if (array != null) {
            int i = array.length - 1;
            for (; i > -1; i--) {
                if (array[i] == value) {
                    break;
                }
            }

            return removeAt(array, i);

        } else {
            throw new IllegalArgumentException(new NullPointerException("A parameter(byte[] array) must not be 'null': array=null"));
        }
    }

    /**
     * 주어진 배열에서 대상 값과 같은 마지막 값을 제거한 후 배열을 반환합니다.
     * 
     * @param array
     * @param value
     * @return
     * 
     * @exception IllegalArgumentException
     *                주어진 배열이 <code>null</code>인 경우
     * 
     *                <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static char[] removeLast(char[] array, char value) {
        if (array != null) {
            int i = array.length - 1;
            for (; i > -1; i--) {
                if (array[i] == value) {
                    break;
                }
            }

            return removeAt(array, i);

        } else {
            throw new IllegalArgumentException(new NullPointerException("A parameter(char[] array) must not be 'null': array=null"));
        }
    }

    /**
     * 주어진 배열에서 대상 값과 같은 마지막 값을 제거한 후 배열을 반환합니다.
     * 
     * @param array
     * @param value
     * @return
     * 
     * @exception IllegalArgumentException
     *                주어진 배열이 <code>null</code>인 경우
     * 
     *                <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static double[] removeLast(double[] array, double value) {
        if (array != null) {
            int i = array.length - 1;
            for (; i > -1; i--) {
                if (array[i] == value) {
                    break;
                }
            }

            return removeAt(array, i);

        } else {
            throw new IllegalArgumentException(new NullPointerException("A parameter(double[] array) must not be 'null': array=null"));
        }
    }

    /**
     * 주어진 배열에서 대상 값과 같은 마지막 값을 제거한 후 배열을 반환합니다.
     * 
     * @param array
     * @param value
     * @return
     * 
     * @exception IllegalArgumentException
     *                주어진 배열이 <code>null</code>인 경우
     * 
     *                <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static float[] removeLast(float[] array, float value) {
        if (array != null) {
            int i = array.length - 1;
            for (; i > -1; i--) {
                if (array[i] == value) {
                    break;
                }
            }

            return removeAt(array, i);

        } else {
            throw new IllegalArgumentException(new NullPointerException("A parameter(float[] array) must not be 'null': array=null"));
        }
    }

    /**
     * 주어진 배열에서 대상 값과 같은 마지막 값을 제거한 후 배열을 반환합니다.
     * 
     * @param array
     * @param value
     * @return
     * 
     * @exception IllegalArgumentException
     *                주어진 배열이 <code>null</code>인 경우
     * 
     *                <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static int[] removeLast(int[] array, int value) {
        if (array != null) {
            int i = array.length - 1;
            for (; i > -1; i--) {
                if (array[i] == value) {
                    break;
                }
            }

            return removeAt(array, i);

        } else {
            throw new IllegalArgumentException(new NullPointerException("A parameter(int[] array) must not be 'null': array=null"));
        }
    }

    /**
     * 주어진 배열에서 대상 값과 같은 마지막 값을 제거한 후 배열을 반환합니다.
     * 
     * @param array
     * @param value
     * @return
     * 
     * @exception IllegalArgumentException
     *                주어진 배열이 <code>null</code>인 경우
     * 
     *                <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static long[] removeLast(long[] array, long value) {
        if (array != null) {
            int i = array.length - 1;
            for (; i > -1; i--) {
                if (array[i] == value) {
                    break;
                }
            }

            return removeAt(array, i);

        } else {
            throw new IllegalArgumentException(new NullPointerException("A parameter(long[] array) must not be 'null': array=null"));
        }
    }

    /**
     * 주어진 배열에서 대상 값과 같은 마지막 값을 제거한 후 배열을 반환합니다.
     * 
     * @param array
     * @param value
     * @return
     * 
     * @exception IllegalArgumentException
     *                주어진 배열이 <code>null</code>인 경우
     * 
     *                <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static short[] removeLast(short[] array, short value) {
        if (array != null) {
            int i = array.length - 1;
            for (; i > -1; i--) {
                if (array[i] == value) {
                    break;
                }
            }

            return removeAt(array, i);

        } else {
            throw new IllegalArgumentException(new NullPointerException("A parameter(short[] array) must not be 'null': array=null"));
        }
    }

    /**
     * 주어진 배열에서 대상 값과 같은 마지막 값을 제거한 후 배열을 반환합니다.
     * 
     * @param array
     * @param value
     * 
     * @return
     * 
     * @exception IllegalArgumentException
     *                주어진 배열이 <code>null</code>인 경우
     * 
     *                <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <T> T[] removeLast(T[] array, T value) {
        return removeLast(array, value, null);
    }

    /**
     * 주어진 배열에서 대상 값과 같은 마지막 값을 제거한 후 배열을 반환합니다.
     * 
     * @param array
     * @param value
     * @param equivalent
     *            클래스 또는 타입 T의 equals(T obj) 메소드가 아닌 다른 비교 기준을 제공하는 객체
     * @return
     * 
     * @exception IllegalArgumentException
     *                주어진 배열이 <code>null</code>인 경우
     * 
     *                <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <T> T[] removeLast(T[] array, T value, IEquivalent<T> equivalent) {
        if (array != null) {
            if (equivalent == null) {
                equivalent = new DefaultEquivalent<T>();
            }
            int i = array.length - 1;
            for (; i > -1; i--) {
                if (equivalent.equals(array[i], value)) {
                    break;
                }
            }

            return removeAt(array, i);

        } else {
            throw new IllegalArgumentException(new NullPointerException("A parameter(T[] array) must not be 'null': array=null"));
        }
    }

    /**
     * 주어진 배열의 데이타를 새로운 데이타 배열로 변경한 새로운 배열을 반환합니다.
     * 
     * @param array
     * @param target
     *            변경될 대상 데이타
     * @param data
     *            새로운 데이타 배열
     * @return
     * 
     * @since 2012. 03. 30.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static boolean[] replaceAll(boolean[] array, boolean target, boolean[] data) {
        if (array != null && data != null) {
            int[] indice = indiceOf(array, target);

            if (indice.length < 1) {
                return array;
            }

            // newArray's length: array.length - indice.length + (indice.length * data.length)
            boolean[] newArray = new boolean[array.length + indice.length * (data.length - 1)];

            int srcPost = 0;
            int desPost = 0;
            int dataLength = data.length;

            int copiedLength = 0;
            int i = 0;
            for (; i < indice.length; i++) {
                // 기존 배열에서 데이타 복사
                copiedLength = indice[i] - srcPost;
                System.arraycopy(array, srcPost, newArray, desPost, copiedLength);
                srcPost = indice[i] + 1;
                desPost += copiedLength;

                // 새롭게 추가되는 데이타 복사
                System.arraycopy(data, 0, newArray, desPost, dataLength);

                desPost += dataLength;
            }
            // 마지막 남은 데이타 복사
            if (srcPost < array.length) {
                System.arraycopy(array, srcPost, newArray, desPost, array.length - srcPost);
            }

            return newArray;
        } else {
            throw new IllegalArgumentException(new NullPointerException("One of parameters(char[] array, char[] data) must not be 'null': array=" + array + ", data=" + data));
        }
    }

    /**
     * 주어진 배열의 데이타 배열을 새로운 데이타로 변경한 새로운 배열을 반환합니다.
     * 
     * @param array
     * @param target
     * @param data
     * @return
     * 
     * @since 2012. 03. 30.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static boolean[] replaceAll(boolean[] array, boolean[] target, boolean data) {
        if (array != null && target != null) {
            return replaceAll(array, target, new boolean[] { data });
        } else {
            throw new IllegalArgumentException(
                    new NullPointerException("One ofparameters(boolean[] array, boolean[] target) must not be 'null': array=" + array + ", target=" + target));
        }
    }

    /**
     * 주어진 배열의 데이타 배열을 새로운 데이타로 변경한 새로운 배열을 반환합니다.
     * 
     * @param array
     * @param target
     * @param data
     * @return
     * 
     * @since 2012. 03. 30.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static boolean[] replaceAll(boolean[] array, boolean[] target, boolean[] data) {
        if (array != null && target != null && data != null) {
            int[] matchingIndice = indiceOf(array, target);

            if (matchingIndice.length > 1) {
                // New Array Max Length
                // 전체 길이에서 동일한 경우가 생길 수 있는 최대횟수 * 길이를 빼고 발생한 횟수만큼 추가할 데이타의 길이
                int namc = array.length - matchingIndice.length / 2 * (target.length - data.length);

                boolean[] newArray = new boolean[namc];

                int srcPos = 0;
                int desPos = 0;
                int indiceLength = matchingIndice.length;
                int dataLength = data.length;

                int copiedLength = 0;
                int i = 0;

                while (i < indiceLength - 1) {
                    // 기존 배열에서 복사
                    copiedLength = matchingIndice[i] - srcPos;
                    System.arraycopy(array, srcPos, newArray, desPos, copiedLength);
                    srcPos = matchingIndice[i + 1] + 1;
                    desPos += copiedLength;

                    // 새롭게 추가되는 데이타 복사
                    System.arraycopy(data, 0, newArray, desPos, dataLength);
                    desPos += dataLength;

                    i += 2;
                }

                // 마지막 남은 데이타 복사
                if (srcPos < array.length) {
                    System.arraycopy(array, srcPos, newArray, desPos, array.length - srcPos);
                }

                return newArray;
            } else {
                return array;
            }
        } else {
            throw new IllegalArgumentException(new NullPointerException(
                    "One of parameters(boolean[] array, boolean[] target, boolean[] data) must not be 'null': array=" + array + ", target=" + target + ", data=" + data));
        }
    }

    /**
     * 주어진 배열의 데이타를 새로운 데이타 배열로 변경한 새로운 배열을 반환합니다.
     * 
     * @param array
     * @param target
     *            변경될 대상 데이타
     * @param data
     *            새로운 데이타 배열
     * @return
     * 
     * @since 2012. 03. 30.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static byte[] replaceAll(byte[] array, byte target, byte[] data) {
        if (array != null && data != null) {
            int[] indice = indiceOf(array, target);

            if (indice.length < 1) {
                return array;
            }

            // newArray's length: array.length - indice.length + (indice.length * data.length)
            byte[] newArray = new byte[array.length + indice.length * (data.length - 1)];

            int srcPost = 0;
            int desPost = 0;
            int dataLength = data.length;

            int copiedLength = 0;
            int i = 0;
            for (; i < indice.length; i++) {
                // 기존 배열에서 데이타 복사
                copiedLength = indice[i] - srcPost;
                System.arraycopy(array, srcPost, newArray, desPost, copiedLength);
                srcPost = indice[i] + 1;
                desPost += copiedLength;

                // 새롭게 추가되는 데이타 복사
                System.arraycopy(data, 0, newArray, desPost, dataLength);

                desPost += dataLength;
            }
            // 마지막 남은 데이타 복사
            if (srcPost < array.length) {
                System.arraycopy(array, srcPost, newArray, desPost, array.length - srcPost);
            }

            return newArray;
        } else {
            throw new IllegalArgumentException(new NullPointerException("One of parameters(char[] array, char[] data) must not be 'null': array=" + array + ", data=" + data));
        }
    }

    /**
     * 주어진 배열의 데이타 배열을 새로운 데이타로 변경한 새로운 배열을 반환합니다.
     * 
     * @param array
     * @param target
     * @param data
     * @return
     * 
     * @since 2012. 03. 30.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static byte[] replaceAll(byte[] array, byte[] target, byte data) {
        if (array != null && target != null) {
            return replaceAll(array, target, new byte[] { data });
        } else {
            throw new IllegalArgumentException(new NullPointerException("One ofparameters(byte[] array, byte[] target) must not be 'null': array=" + array + ", target=" + target));
        }
    }

    /**
     * 주어진 배열의 데이타 배열을 새로운 데이타로 변경한 새로운 배열을 반환합니다.
     * 
     * @param array
     * @param target
     * @param data
     * @return
     * 
     * @since 2012. 03. 30.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static byte[] replaceAll(byte[] array, byte[] target, byte[] data) {
        if (array != null && target != null && data != null) {
            int[] matchingIndice = indiceOf(array, target);

            if (matchingIndice.length > 1) {
                // New Array Max Length
                // 전체 길이에서 동일한 경우가 생길 수 있는 최대횟수 * 길이를 빼고 발생한 횟수만큼 추가할 데이타의 길이
                int namc = array.length - matchingIndice.length / 2 * (target.length - data.length);

                byte[] newArray = new byte[namc];

                int srcPos = 0;
                int desPos = 0;
                int indiceLength = matchingIndice.length;
                int dataLength = data.length;

                int copiedLength = 0;
                int i = 0;

                while (i < indiceLength - 1) {
                    // 기존 배열에서 복사
                    copiedLength = matchingIndice[i] - srcPos;
                    System.arraycopy(array, srcPos, newArray, desPos, copiedLength);
                    srcPos = matchingIndice[i + 1] + 1;
                    desPos += copiedLength;

                    // 새롭게 추가되는 데이타 복사
                    System.arraycopy(data, 0, newArray, desPos, dataLength);
                    desPos += dataLength;

                    i += 2;
                }

                // 마지막 남은 데이타 복사
                if (srcPos < array.length) {
                    System.arraycopy(array, srcPos, newArray, desPos, array.length - srcPos);
                }

                return newArray;
            } else {
                return array;
            }
        } else {
            throw new IllegalArgumentException(new NullPointerException(
                    "One of parameters(byte[] array, byte[] target, byte[] data) must not be 'null': array=" + array + ", target=" + target + ", data=" + data));
        }
    }

    /**
     * 주어진 배열의 데이타를 새로운 데이타 배열로 변경한 새로운 배열을 반환합니다.
     * 
     * @param array
     * @param target
     *            변경될 대상 데이타
     * @param data
     *            새로운 데이타 배열
     * @return
     * 
     * @since 2012. 03. 30.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static char[] replaceAll(char[] array, char target, char[] data) {
        if (array != null && data != null) {
            int[] indice = indiceOf(array, target);

            if (indice.length < 1) {
                return array;
            }

            // newArray's length: array.length - indice.length + (indice.length * data.length)
            char[] newArray = new char[array.length + indice.length * (data.length - 1)];

            int srcPos = 0;
            int desPos = 0;
            int dataLength = data.length;

            int copiedLength = 0;
            int i = 0;
            while (i < indice.length) {
                // 기존 배열에서 데이타 복사
                copiedLength = indice[i] - srcPos;
                System.arraycopy(array, srcPos, newArray, desPos, copiedLength);
                srcPos = indice[i] + 1;
                desPos += copiedLength;

                // 새롭게 추가되는 데이타 복사
                System.arraycopy(data, 0, newArray, desPos, dataLength);
                desPos += dataLength;

                i++;
            }
            // 마지막 남은 데이타 복사
            if (srcPos < array.length) {
                System.arraycopy(array, srcPos, newArray, desPos, array.length - srcPos);
            }

            return newArray;
        } else {
            throw new IllegalArgumentException(new NullPointerException("One of parameters(char[] array, char[] data) must not be 'null': array="
                    + (array != null ? Arrays.toString(array) : null) + ", data=" + (data != null ? Arrays.toString(data) : null)));
        }
    }

    /**
     * 주어진 배열의 데이타 배열을 새로운 데이타로 변경한 새로운 배열을 반환합니다.
     * 
     * @param array
     * @param target
     * @param data
     * @return
     * 
     * @since 2012. 03. 30.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static char[] replaceAll(char[] array, char[] target, char data) {
        if (array != null && target != null) {
            return replaceAll(array, target, new char[] { data });
        } else {
            throw new IllegalArgumentException(new NullPointerException("One ofparameters(char[] array, char[] target) must not be 'null': array="
                    + (array != null ? Arrays.toString(array) : null) + ", target=" + (target != null ? Arrays.toString(target) : null)));
        }
    }

    /**
     * 주어진 배열의 데이타 배열을 새로운 데이타로 변경한 새로운 배열을 반환합니다.
     * 
     * @param array
     * @param target
     * @param data
     * @return
     * 
     * @since 2012. 03. 30.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static char[] replaceAll(char[] array, char[] target, char[] data) {
        if (array != null && target != null && data != null) {
            int[] matchingIndice = indiceOf(array, target);

            if (matchingIndice.length > 1) {
                // New Array Max Length
                // 전체 길이에서 동일한 경우가 생길 수 있는 최대횟수 * 길이를 빼고 발생한 횟수만큼 추가할 데이타의 길이
                int namc = array.length - matchingIndice.length / 2 * (target.length - data.length);

                char[] newArray = new char[namc];

                int srcPos = 0;
                int desPos = 0;
                int indiceLength = matchingIndice.length;
                int dataLength = data.length;

                int copiedLength = 0;
                int i = 0;

                while (i < indiceLength - 1) {
                    // 기존 배열에서 복사
                    copiedLength = matchingIndice[i] - srcPos;
                    System.arraycopy(array, srcPos, newArray, desPos, copiedLength);
                    srcPos = matchingIndice[i + 1] + 1;
                    desPos += copiedLength;

                    // 새롭게 추가되는 데이타 복사
                    System.arraycopy(data, 0, newArray, desPos, dataLength);
                    desPos += dataLength;

                    i += 2;
                }

                // 마지막 남은 데이타 복사
                if (srcPos < array.length) {
                    System.arraycopy(array, srcPos, newArray, desPos, array.length - srcPos);
                }

                return newArray;
            } else {
                return array;
            }
        } else {
            throw new IllegalArgumentException(new NullPointerException(
                    "One of parameters(char[] array, char[] target, char[] data) must not be 'null': array=" + (array != null ? Arrays.toString(array) : null) + ", target="
                            + (target != null ? Arrays.toString(target) : null) + ", data=" + (data != null ? Arrays.toString(data) : null)));
        }
    }

    /**
     * 주어진 배열의 데이타를 새로운 데이타 배열로 변경한 새로운 배열을 반환합니다.
     * 
     * @param array
     * @param target
     *            변경될 대상 데이타
     * @param data
     *            새로운 데이타 배열
     * @return
     * 
     * @since 2012. 03. 30.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static double[] replaceAll(double[] array, double target, double[] data) {
        if (array != null && data != null) {
            int[] indice = indiceOf(array, target);

            if (indice.length < 1) {
                return array;
            }

            // newArray's length: array.length - indice.length + (indice.length * data.length)
            double[] newArray = new double[array.length + indice.length * (data.length - 1)];

            int srcPost = 0;
            int desPost = 0;
            int dataLength = data.length;

            int copiedLength = 0;
            int i = 0;
            for (; i < indice.length; i++) {
                // 기존 배열에서 데이타 복사
                copiedLength = indice[i] - srcPost;
                System.arraycopy(array, srcPost, newArray, desPost, copiedLength);
                srcPost = indice[i] + 1;
                desPost += copiedLength;

                // 새롭게 추가되는 데이타 복사
                System.arraycopy(data, 0, newArray, desPost, dataLength);

                desPost += dataLength;
            }
            // 마지막 남은 데이타 복사
            if (srcPost < array.length) {
                System.arraycopy(array, srcPost, newArray, desPost, array.length - srcPost);
            }

            return newArray;
        } else {
            throw new IllegalArgumentException(new NullPointerException("One of parameters(char[] array, char[] data) must not be 'null': array=" + array + ", data=" + data));
        }
    }

    /**
     * 주어진 배열의 데이타 배열을 새로운 데이타로 변경한 새로운 배열을 반환합니다.
     * 
     * @param array
     * @param target
     * @param data
     * @return
     * 
     * @since 2012. 03. 30.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static double[] replaceAll(double[] array, double[] target, double data) {
        if (array != null && target != null) {
            return replaceAll(array, target, new double[] { data });
        } else {
            throw new IllegalArgumentException(
                    new NullPointerException("One ofparameters(double[] array, double[] target) must not be 'null': array=" + array + ", target=" + target));
        }
    }

    /**
     * 주어진 배열의 데이타 배열을 새로운 데이타로 변경한 새로운 배열을 반환합니다.
     * 
     * @param array
     * @param target
     * @param data
     * @return
     * 
     * @since 2012. 03. 30.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static double[] replaceAll(double[] array, double[] target, double[] data) {
        if (array != null && target != null && data != null) {
            int[] matchingIndice = indiceOf(array, target);

            if (matchingIndice.length > 1) {
                // New Array Max Length
                // 전체 길이에서 동일한 경우가 생길 수 있는 최대횟수 * 길이를 빼고 발생한 횟수만큼 추가할 데이타의 길이
                int namc = array.length - matchingIndice.length / 2 * (target.length - data.length);

                double[] newArray = new double[namc];

                int srcPos = 0;
                int desPos = 0;
                int indiceLength = matchingIndice.length;
                int dataLength = data.length;

                int copiedLength = 0;
                int i = 0;

                while (i < indiceLength - 1) {
                    // 기존 배열에서 복사
                    copiedLength = matchingIndice[i] - srcPos;
                    System.arraycopy(array, srcPos, newArray, desPos, copiedLength);
                    srcPos = matchingIndice[i + 1] + 1;
                    desPos += copiedLength;

                    // 새롭게 추가되는 데이타 복사
                    System.arraycopy(data, 0, newArray, desPos, dataLength);
                    desPos += dataLength;

                    i += 2;
                }

                // 마지막 남은 데이타 복사
                if (srcPos < array.length) {
                    System.arraycopy(array, srcPos, newArray, desPos, array.length - srcPos);
                }

                return newArray;
            } else {
                return array;
            }
        } else {
            throw new IllegalArgumentException(new NullPointerException(
                    "One of parameters(double[] array, double[] target, double[] data) must not be 'null': array=" + array + ", target=" + target + ", data=" + data));
        }
    }

    /**
     * 주어진 배열의 데이타를 새로운 데이타 배열로 변경한 새로운 배열을 반환합니다.
     * 
     * @param array
     * @param target
     *            변경될 대상 데이타
     * @param data
     *            새로운 데이타 배열
     * @return
     * 
     * @since 2012. 03. 30.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static float[] replaceAll(float[] array, float target, float[] data) {
        if (array != null && data != null) {
            int[] indice = indiceOf(array, target);

            if (indice.length < 1) {
                return array;
            }

            // newArray's length: array.length - indice.length + (indice.length * data.length)
            float[] newArray = new float[array.length + indice.length * (data.length - 1)];

            int srcPost = 0;
            int desPost = 0;
            int dataLength = data.length;

            int copiedLength = 0;
            int i = 0;
            for (; i < indice.length; i++) {
                // 기존 배열에서 데이타 복사
                copiedLength = indice[i] - srcPost;
                System.arraycopy(array, srcPost, newArray, desPost, copiedLength);
                srcPost = indice[i] + 1;
                desPost += copiedLength;

                // 새롭게 추가되는 데이타 복사
                System.arraycopy(data, 0, newArray, desPost, dataLength);

                desPost += dataLength;
            }
            // 마지막 남은 데이타 복사
            if (srcPost < array.length) {
                System.arraycopy(array, srcPost, newArray, desPost, array.length - srcPost);
            }

            return newArray;
        } else {
            throw new IllegalArgumentException(new NullPointerException("One of parameters(char[] array, char[] data) must not be 'null': array=" + array + ", data=" + data));
        }
    }

    /**
     * 주어진 배열의 데이타 배열을 새로운 데이타로 변경한 새로운 배열을 반환합니다.
     * 
     * @param array
     * @param target
     * @param data
     * @return
     * 
     * @since 2012. 03. 30.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static float[] replaceAll(float[] array, float[] target, float data) {
        if (array != null && target != null) {
            return replaceAll(array, target, new float[] { data });
        } else {
            throw new IllegalArgumentException(
                    new NullPointerException("One ofparameters(float[] array, float[] target) must not be 'null': array=" + array + ", target=" + target));
        }
    }

    /**
     * 주어진 배열의 데이타 배열을 새로운 데이타로 변경한 새로운 배열을 반환합니다.
     * 
     * @param array
     * @param target
     * @param data
     * @return
     * 
     * @since 2012. 03. 30.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static float[] replaceAll(float[] array, float[] target, float[] data) {
        if (array != null && target != null && data != null) {
            int[] matchingIndice = indiceOf(array, target);

            if (matchingIndice.length > 1) {
                // New Array Max Length
                // 전체 길이에서 동일한 경우가 생길 수 있는 최대횟수 * 길이를 빼고 발생한 횟수만큼 추가할 데이타의 길이
                int namc = array.length - matchingIndice.length / 2 * (target.length - data.length);

                float[] newArray = new float[namc];

                int srcPos = 0;
                int desPos = 0;
                int indiceLength = matchingIndice.length;
                int dataLength = data.length;

                int copiedLength = 0;
                int i = 0;

                while (i < indiceLength - 1) {
                    // 기존 배열에서 복사
                    copiedLength = matchingIndice[i] - srcPos;
                    System.arraycopy(array, srcPos, newArray, desPos, copiedLength);
                    srcPos = matchingIndice[i + 1] + 1;
                    desPos += copiedLength;

                    // 새롭게 추가되는 데이타 복사
                    System.arraycopy(data, 0, newArray, desPos, dataLength);
                    desPos += dataLength;

                    i += 2;
                }

                // 마지막 남은 데이타 복사
                if (srcPos < array.length) {
                    System.arraycopy(array, srcPos, newArray, desPos, array.length - srcPos);
                }

                return newArray;
            } else {
                return array;
            }
        } else {
            throw new IllegalArgumentException(new NullPointerException(
                    "One of parameters(float[] array, float[] target, float[] data) must not be 'null': array=" + array + ", target=" + target + ", data=" + data));
        }
    }

    /**
     * 주어진 배열의 데이타를 새로운 데이타 배열로 변경한 새로운 배열을 반환합니다.
     * 
     * @param array
     * @param target
     *            변경될 대상 데이타
     * @param data
     *            새로운 데이타 배열
     * @return
     * 
     * @since 2012. 03. 30.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static int[] replaceAll(int[] array, int target, int[] data) {
        if (array != null && data != null) {
            int[] indice = indiceOf(array, target);

            if (indice.length < 1) {
                return array;
            }

            // newArray's length: array.length - indice.length + (indice.length * data.length)
            int[] newArray = new int[array.length + indice.length * (data.length - 1)];

            int srcPost = 0;
            int desPost = 0;
            int dataLength = data.length;

            int copiedLength = 0;
            int i = 0;
            for (; i < indice.length; i++) {
                // 기존 배열에서 데이타 복사
                copiedLength = indice[i] - srcPost;
                System.arraycopy(array, srcPost, newArray, desPost, copiedLength);
                srcPost = indice[i] + 1;
                desPost += copiedLength;

                // 새롭게 추가되는 데이타 복사
                System.arraycopy(data, 0, newArray, desPost, dataLength);

                desPost += dataLength;
            }
            // 마지막 남은 데이타 복사
            if (srcPost < array.length) {
                System.arraycopy(array, srcPost, newArray, desPost, array.length - srcPost);
            }

            return newArray;
        } else {
            throw new IllegalArgumentException(new NullPointerException("One of parameters(char[] array, char[] data) must not be 'null': array=" + array + ", data=" + data));
        }
    }

    /**
     * 주어진 배열의 데이타 배열을 새로운 데이타로 변경한 새로운 배열을 반환합니다.
     * 
     * @param array
     * @param target
     * @param data
     * @return
     * 
     * @since 2012. 03. 30.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static int[] replaceAll(int[] array, int[] target, int data) {
        if (array != null && target != null) {
            return replaceAll(array, target, new int[] { data });
        } else {
            throw new IllegalArgumentException(new NullPointerException("One ofparameters(int[] array, int[] target) must not be 'null': array=" + array + ", target=" + target));
        }
    }

    /**
     * 주어진 배열의 데이타 배열을 새로운 데이타로 변경한 새로운 배열을 반환합니다.
     * 
     * @param array
     * @param target
     * @param data
     * @return
     * 
     * @since 2012. 03. 30.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static int[] replaceAll(int[] array, int[] target, int[] data) {
        if (array != null && target != null && data != null) {
            int[] matchingIndice = indiceOf(array, target);

            if (matchingIndice.length > 1) {
                // New Array Max Length
                // 전체 길이에서 동일한 경우가 생길 수 있는 최대횟수 * 길이를 빼고 발생한 횟수만큼 추가할 데이타의 길이
                int namc = array.length - matchingIndice.length / 2 * (target.length - data.length);

                int[] newArray = new int[namc];

                int srcPos = 0;
                int desPos = 0;
                int indiceLength = matchingIndice.length;
                int dataLength = data.length;

                int copiedLength = 0;
                int i = 0;

                while (i < indiceLength - 1) {
                    // 기존 배열에서 복사
                    copiedLength = matchingIndice[i] - srcPos;
                    System.arraycopy(array, srcPos, newArray, desPos, copiedLength);
                    srcPos = matchingIndice[i + 1] + 1;
                    desPos += copiedLength;

                    // 새롭게 추가되는 데이타 복사
                    System.arraycopy(data, 0, newArray, desPos, dataLength);
                    desPos += dataLength;

                    i += 2;
                }

                // 마지막 남은 데이타 복사
                if (srcPos < array.length) {
                    System.arraycopy(array, srcPos, newArray, desPos, array.length - srcPos);
                }

                return newArray;
            } else {
                return array;
            }
        } else {
            throw new IllegalArgumentException(new NullPointerException(
                    "One of parameters(int[] array, int[] target, int[] data) must not be 'null': array=" + array + ", target=" + target + ", data=" + data));
        }
    }

    /**
     * 주어진 배열의 데이타를 새로운 데이타 배열로 변경한 새로운 배열을 반환합니다.
     * 
     * @param array
     * @param target
     *            변경될 대상 데이타
     * @param data
     *            새로운 데이타 배열
     * @return
     * 
     * @since 2012. 03. 30.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static long[] replaceAll(long[] array, long target, long[] data) {
        if (array != null && data != null) {
            int[] indice = indiceOf(array, target);

            if (indice.length < 1) {
                return array;
            }

            // newArray's length: array.length - indice.length + (indice.length * data.length)
            long[] newArray = new long[array.length + indice.length * (data.length - 1)];

            int srcPost = 0;
            int desPost = 0;
            int dataLength = data.length;

            int copiedLength = 0;
            int i = 0;
            for (; i < indice.length; i++) {
                // 기존 배열에서 데이타 복사
                copiedLength = indice[i] - srcPost;
                System.arraycopy(array, srcPost, newArray, desPost, copiedLength);
                srcPost = indice[i] + 1;
                desPost += copiedLength;

                // 새롭게 추가되는 데이타 복사
                System.arraycopy(data, 0, newArray, desPost, dataLength);

                desPost += dataLength;
            }
            // 마지막 남은 데이타 복사
            if (srcPost < array.length) {
                System.arraycopy(array, srcPost, newArray, desPost, array.length - srcPost);
            }

            return newArray;
        } else {
            throw new IllegalArgumentException(new NullPointerException("One of parameters(char[] array, char[] data) must not be 'null': array=" + array + ", data=" + data));
        }
    }

    /**
     * 주어진 배열의 데이타 배열을 새로운 데이타로 변경한 새로운 배열을 반환합니다.
     * 
     * @param array
     * @param target
     * @param data
     * @return
     * 
     * @since 2012. 03. 30.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static long[] replaceAll(long[] array, long[] target, long data) {
        if (array != null && target != null) {
            return replaceAll(array, target, new long[] { data });
        } else {
            throw new IllegalArgumentException(new NullPointerException("One ofparameters(long[] array, long[] target) must not be 'null': array=" + array + ", target=" + target));
        }
    }

    /**
     * 주어진 배열의 데이타 배열을 새로운 데이타로 변경한 새로운 배열을 반환합니다.
     * 
     * @param array
     * @param target
     * @param data
     * @return
     * 
     * @since 2012. 03. 30.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static long[] replaceAll(long[] array, long[] target, long[] data) {
        if (array != null && target != null && data != null) {
            int[] matchingIndice = indiceOf(array, target);

            if (matchingIndice.length > 1) {
                // New Array Max Length
                // 전체 길이에서 동일한 경우가 생길 수 있는 최대횟수 * 길이를 빼고 발생한 횟수만큼 추가할 데이타의 길이
                int namc = array.length - matchingIndice.length / 2 * (target.length - data.length);

                long[] newArray = new long[namc];

                int srcPos = 0;
                int desPos = 0;
                int indiceLength = matchingIndice.length;
                int dataLength = data.length;

                int copiedLength = 0;
                int i = 0;

                while (i < indiceLength - 1) {
                    // 기존 배열에서 복사
                    copiedLength = matchingIndice[i] - srcPos;
                    System.arraycopy(array, srcPos, newArray, desPos, copiedLength);
                    srcPos = matchingIndice[i + 1] + 1;
                    desPos += copiedLength;

                    // 새롭게 추가되는 데이타 복사
                    System.arraycopy(data, 0, newArray, desPos, dataLength);
                    desPos += dataLength;

                    i += 2;
                }

                // 마지막 남은 데이타 복사
                if (srcPos < array.length) {
                    System.arraycopy(array, srcPos, newArray, desPos, array.length - srcPos);
                }

                return newArray;
            } else {
                return array;
            }
        } else {
            throw new IllegalArgumentException(new NullPointerException(
                    "One of parameters(long[] array, long[] target, long[] data) must not be 'null': array=" + array + ", target=" + target + ", data=" + data));
        }
    }

    /**
     * 주어진 배열의 데이타를 새로운 데이타 배열로 변경한 새로운 배열을 반환합니다.
     * 
     * @param array
     * @param target
     *            변경될 대상 데이타
     * @param data
     *            새로운 데이타 배열
     * @return
     * 
     * @since 2012. 03. 30.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static short[] replaceAll(short[] array, short target, short[] data) {
        if (array != null && data != null) {
            int[] indice = indiceOf(array, target);

            if (indice.length < 1) {
                return array;
            }

            // newArray's length: array.length - indice.length + (indice.length * data.length)
            short[] newArray = new short[array.length + indice.length * (data.length - 1)];

            int srcPost = 0;
            int desPost = 0;
            int dataLength = data.length;

            int copiedLength = 0;
            int i = 0;
            for (; i < indice.length; i++) {
                // 기존 배열에서 데이타 복사
                copiedLength = indice[i] - srcPost;
                System.arraycopy(array, srcPost, newArray, desPost, copiedLength);
                srcPost = indice[i] + 1;
                desPost += copiedLength;

                // 새롭게 추가되는 데이타 복사
                System.arraycopy(data, 0, newArray, desPost, dataLength);

                desPost += dataLength;
            }
            // 마지막 남은 데이타 복사
            if (srcPost < array.length) {
                System.arraycopy(array, srcPost, newArray, desPost, array.length - srcPost);
            }

            return newArray;
        } else {
            throw new IllegalArgumentException(new NullPointerException("One of parameters(short[] array, short[] data) must not be 'null': array=" + array + ", data=" + data));
        }
    }

    /**
     * 주어진 배열의 데이타 배열을 새로운 데이타로 변경한 새로운 배열을 반환합니다.
     * 
     * @param array
     * @param target
     * @param data
     * @return
     * 
     * @since 2012. 03. 30.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static short[] replaceAll(short[] array, short[] target, short data) {
        if (array != null && target != null) {
            return replaceAll(array, target, new short[] { data });
        } else {
            throw new IllegalArgumentException(
                    new NullPointerException("One ofparameters(short[] array, short[] target) must not be 'null': array=" + array + ", target=" + target));
        }
    }

    /**
     * 주어진 배열의 데이타 배열을 새로운 데이타로 변경한 새로운 배열을 반환합니다.
     * 
     * @param array
     * @param target
     * @param data
     * @return
     * 
     * @since 2012. 03. 30.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static short[] replaceAll(short[] array, short[] target, short[] data) {
        if (array != null && target != null && data != null) {
            int[] matchingIndice = indiceOf(array, target);

            if (matchingIndice.length > 1) {
                // New Array Max Length
                // 전체 길이에서 동일한 경우가 생길 수 있는 최대횟수 * 길이를 빼고 발생한 횟수만큼 추가할 데이타의 길이
                int namc = array.length - matchingIndice.length / 2 * (target.length - data.length);

                short[] newArray = new short[namc];

                int srcPos = 0;
                int desPos = 0;
                int indiceLength = matchingIndice.length;
                int dataLength = data.length;

                int copiedLength = 0;
                int i = 0;

                while (i < indiceLength - 1) {
                    // 기존 배열에서 복사
                    copiedLength = matchingIndice[i] - srcPos;
                    System.arraycopy(array, srcPos, newArray, desPos, copiedLength);
                    srcPos = matchingIndice[i + 1] + 1;
                    desPos += copiedLength;

                    // 새롭게 추가되는 데이타 복사
                    System.arraycopy(data, 0, newArray, desPos, dataLength);
                    desPos += dataLength;

                    i += 2;
                }

                // 마지막 남은 데이타 복사
                if (srcPos < array.length) {
                    System.arraycopy(array, srcPos, newArray, desPos, array.length - srcPos);
                }

                return newArray;
            } else {
                return array;
            }
        } else {
            throw new IllegalArgumentException(new NullPointerException(
                    "One of parameters(short[] array, short[] target, short[] data) must not be 'null': array=" + array + ", target=" + target + ", data=" + data));
        }
    }

    /**
     * 주어진 배열의 데이타를 새로운 데이타 배열로 변경한 새로운 배열을 반환합니다.
     * 
     * @param array
     * @param target
     *            변경될 대상 데이타
     * @param data
     *            새로운 데이타 배열
     * @return
     * 
     * @since 2012. 03. 30.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <T> T[] replaceAll(T[] array, T target, T[] data) {
        return replaceAllToArray(array, target, data, null);
    }

    /**
     * 주어진 배열의 데이타 배열을 새로운 데이타로 변경한 새로운 배열을 반환합니다.
     * 
     * @param array
     * @param target
     * @param data
     * @return
     * 
     * @since 2012. 03. 30.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <T> T[] replaceAll(T[] array, T[] target, T data) {
        return replaceAllToData(array, target, data, null);
    }

    /**
     * 주어진 배열의 데이타 배열을 새로운 데이타로 변경한 새로운 배열을 반환합니다.
     * 
     * @param array
     * @param target
     * @param data
     * @return
     * 
     * @since 2012. 03. 30.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <T> T[] replaceAll(T[] array, T[] target, T[] data) {
        return replaceAll(array, target, data, null);
    }

    /**
     * 주어진 배열의 데이타 배열을 새로운 데이타로 변경한 새로운 배열을 반환합니다.
     * 
     * @param array
     * @param target
     * @param data
     * @return
     * 
     * @since 2012. 03. 30.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <T> T[] replaceAll(T[] array, T[] target, T[] data, IEquivalent<T[]> equivalent) {
        if (array != null && target != null && data != null) {
            int[] matchingIndice = indiceOfArray(array, target, equivalent);

            if (matchingIndice.length > 1) {
                // New Array Max Length
                // 전체 길이에서 동일한 경우가 생길 수 있는 최대횟수 * 길이를 빼고 발생한 횟수만큼 추가할 데이타의 길이
                int namc = array.length - matchingIndice.length / 2 * (target.length - data.length);

                T[] newArray = (T[]) Array.newInstance(array.getClass().getComponentType(), namc);

                int srcPos = 0;
                int desPos = 0;
                int indiceLength = matchingIndice.length;
                int dataLength = data.length;

                int copiedLength = 0;
                int i = 0;

                while (i < indiceLength - 1) {
                    // 기존 배열에서 복사
                    copiedLength = matchingIndice[i] - srcPos;
                    System.arraycopy(array, srcPos, newArray, desPos, copiedLength);
                    srcPos = matchingIndice[i + 1] + 1;
                    desPos += copiedLength;

                    // 새롭게 추가되는 데이타 복사
                    System.arraycopy(data, 0, newArray, desPos, dataLength);
                    desPos += dataLength;

                    i += 2;
                }

                // 마지막 남은 데이타 복사
                if (srcPos < array.length) {
                    System.arraycopy(array, srcPos, newArray, desPos, array.length - srcPos);
                }

                return newArray;
            } else {
                return array;
            }
        } else {
            throw new IllegalArgumentException(new NullPointerException(
                    "One of parameters(T[] array, T[] target, T[] data) must not be 'null': array=" + array + ", target=" + target.toString() + ", data=" + data));
        }
    }

    /**
     * 주어진 배열의 데이타를 새로운 데이타 배열로 변경한 새로운 배열을 반환합니다.
     * 
     * @param array
     * @param target
     *            변경될 대상 데이타
     * @param data
     *            새로운 데이타 배열
     * @param equivalent
     *            <code>T</code>가 동일한지를 판단하는 비교자
     * @return
     * 
     * @since 2012. 03. 30.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <T> T[] replaceAllToArray(T[] array, T target, T[] data, IEquivalent<T> equivalent) {
        if (array != null && data != null) {
            int[] indice = indiceOf(array, target, equivalent);

            if (indice.length < 1) {
                return array;
            }

            // newArray's length: array.length - indice.length + (indice.length * data.length)
            T[] newArray = (T[]) Array.newInstance(array.getClass().getComponentType(), array.length + indice.length * (data.length - 1));

            int srcPost = 0;
            int desPost = 0;
            int dataLength = data.length;

            int copiedLength = 0;
            int i = 0;
            for (; i < indice.length; i++) {
                // 기존 배열에서 데이타 복사
                copiedLength = indice[i] - srcPost;
                System.arraycopy(array, srcPost, newArray, desPost, copiedLength);
                srcPost = indice[i] + 1;
                desPost += copiedLength;

                // 새롭게 추가되는 데이타 복사
                System.arraycopy(data, 0, newArray, desPost, dataLength);

                desPost += dataLength;
            }
            // 마지막 남은 데이타 복사
            if (srcPost < array.length) {
                System.arraycopy(array, srcPost, newArray, desPost, array.length - srcPost);
            }

            return newArray;
        } else {
            throw new IllegalArgumentException(new NullPointerException("One of parameters(char[] array, char[] data) must not be 'null': array=" + array + ", data=" + data));
        }
    }

    /**
     * 주어진 배열의 데이타 배열을 새로운 데이타로 변경한 새로운 배열을 반환합니다.
     * 
     * @param array
     * @param target
     * @param data
     * @return
     * 
     * @since 2012. 03. 30.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <T> T[] replaceAllToData(T[] array, T[] target, T data, IEquivalent<T[]> equivalent) {
        if (array != null && target != null) {
            int[] matchingIndice = indiceOfArray(array, target, equivalent);

            if (matchingIndice.length > 1) {
                // New Array Max Length
                // 전체 길이에서 동일한 경우가 생길 수 있는 최대횟수 * 길이를 빼고 발생한 횟수만큼 추가할 데이타의 길이
                int namc = array.length - matchingIndice.length / 2 * (target.length - 1);

                T[] newArray = (T[]) Array.newInstance(array.getClass().getComponentType(), namc);

                int srcPos = 0;
                int desPos = 0;
                int indiceLength = matchingIndice.length;

                int copiedLength = 0;
                int i = 0;

                while (i < indiceLength - 1) {
                    // 기존 배열에서 복사
                    copiedLength = matchingIndice[i] - srcPos;
                    System.arraycopy(array, srcPos, newArray, desPos, copiedLength);
                    srcPos = matchingIndice[i + 1] + 1;
                    desPos += copiedLength;

                    // 새롭게 추가되는 데이타 복사
                    newArray[desPos] = data;
                    desPos++;

                    i += 2;
                }

                // 마지막 남은 데이타 복사
                if (srcPos < array.length) {
                    System.arraycopy(array, srcPos, newArray, desPos, array.length - srcPos);
                }

                return newArray;
            } else {
                return array;
            }
        } else {
            throw new IllegalArgumentException(new NullPointerException("One ofparameters(T[] array, T[] target) must not be 'null': array=" + array + ", target=" + target));
        }
    }

    /**
     * <code><b>boolean</b></code> 데이타를 포함하고 있는 배열의 순서를 역순으로 변경한 후 새로운 객체로 반환합니다.
     * 
     * @param array
     * @return <BR>
     * @since 2012. 02. 22.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static boolean[] reverse(boolean[] array) {

        if (array != null) {
            boolean[] reversed = new boolean[array.length];

            final int MAX_INDEX = array.length - 1;
            for (int i = 0; i < array.length; i++) {
                reversed[MAX_INDEX - i] = array[i];
            }

            return reversed;
        } else {
            throw new IllegalArgumentException(new NullPointerException("A parameter(boolean[] array) must not be 'null': array=null"));
        }
    }

    /**
     * <code><b>byte</b></code> 데이타를 포함하고 있는 배열의 순서를 역순으로 변경한 후 새로운 객체로 반환합니다.
     * 
     * @param array
     * @return <BR>
     * @since 2012. 02. 22.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static byte[] reverse(byte[] array) {

        if (array != null) {
            byte[] reversed = new byte[array.length];

            final int MAX_INDEX = array.length - 1;
            for (int i = 0; i < array.length; i++) {
                reversed[MAX_INDEX - i] = array[i];
            }

            return reversed;
        } else {
            throw new IllegalArgumentException(new NullPointerException("A parameter(byte[] array) must not be 'null': array=null"));
        }
    }

    /**
     * <code><b>char</b></code> 데이타를 포함하고 있는 배열의 순서를 역순으로 변경한 후 새로운 객체로 반환합니다.
     * 
     * @param array
     * @return <BR>
     * @since 2012. 02. 22.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static char[] reverse(char[] array) {

        if (array != null) {
            char[] reversed = new char[array.length];

            final int MAX_INDEX = array.length - 1;
            for (int i = 0; i < array.length; i++) {
                reversed[MAX_INDEX - i] = array[i];
            }

            return reversed;
        } else {
            throw new IllegalArgumentException(new NullPointerException("A parameter(char[] array) must not be 'null': array=null"));
        }
    }

    /**
     * <code><b>double</b></code> 데이타를 포함하고 있는 배열의 순서를 역순으로 변경한 후 새로운 객체로 반환합니다.
     * 
     * @param array
     * @return <BR>
     * @since 2012. 02. 22.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static double[] reverse(double[] array) {

        if (array != null) {
            double[] reversed = new double[array.length];

            final int MAX_INDEX = array.length - 1;
            for (int i = 0; i < array.length; i++) {
                reversed[MAX_INDEX - i] = array[i];
            }

            return reversed;
        } else {
            throw new IllegalArgumentException(new NullPointerException("A parameter(double[] array) must not be 'null': array=null"));
        }
    }

    /**
     * <code><b>float</b></code> 데이타를 포함하고 있는 배열의 순서를 역순으로 변경한 후 새로운 객체로 반환합니다.
     * 
     * @param array
     * @return <BR>
     * @since 2012. 02. 22.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static float[] reverse(float[] array) {

        if (array != null) {
            float[] reversed = new float[array.length];

            final int MAX_INDEX = array.length - 1;
            for (int i = 0; i < array.length; i++) {
                reversed[MAX_INDEX - i] = array[i];
            }

            return reversed;
        } else {
            throw new IllegalArgumentException(new NullPointerException("A parameter(float[] array) must not be 'null': array=null"));
        }
    }

    /**
     * <code><b>int</b></code> 데이타를 포함하고 있는 배열의 순서를 역순으로 변경한 후 새로운 객체로 반환합니다.
     * 
     * @param array
     * @return <BR>
     * @since 2012. 02. 22.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static int[] reverse(int[] array) {

        if (array != null) {
            int[] reversed = new int[array.length];

            final int MAX_INDEX = array.length - 1;
            for (int i = 0; i < array.length; i++) {
                reversed[MAX_INDEX - i] = array[i];
            }

            return reversed;
        } else {
            throw new IllegalArgumentException(new NullPointerException("A parameter(int[] array) must not be 'null': array=null"));
        }
    }

    /**
     * <code><b>long</b></code> 데이타를 포함하고 있는 배열의 순서를 역순으로 변경한 후 새로운 객체로 반환합니다.
     * 
     * @param array
     * @return <BR>
     * @since 2012. 02. 22.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static long[] reverse(long[] array) {

        if (array != null) {
            long[] reversed = new long[array.length];

            final int MAX_INDEX = array.length - 1;
            for (int i = 0; i < array.length; i++) {
                reversed[MAX_INDEX - i] = array[i];
            }

            return reversed;
        } else {
            throw new IllegalArgumentException(new NullPointerException("A parameter(long[] array) must not be 'null': array=null"));
        }
    }

    /**
     * <code><b>long</b></code> 데이타를 포함하고 있는 배열의 순서를 역순으로 변경한 후 새로운 객체로 반환합니다.
     * 
     * @param array
     * @return <BR>
     * @since 2012. 02. 22.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static short[] reverse(short[] array) {

        if (array != null) {
            short[] reversed = new short[array.length];

            final int MAX_INDEX = array.length - 1;
            for (int i = 0; i < array.length; i++) {
                reversed[MAX_INDEX - i] = array[i];
            }

            return reversed;
        } else {
            throw new IllegalArgumentException(new NullPointerException("A parameter(long[] array) must not be 'null': array=null"));
        }
    }

    /**
     * 배열의 순서를 변경한 후 반환합니다.
     * 
     * @param array
     * @return <BR>
     * @since 2012. 02. 22.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */

    public static <T> T[] reverse(T[] array) {

        if (array != null) {
            T[] reversed = (T[]) Array.newInstance(array.getClass().getComponentType(), array.length);

            final int MAX_INDEX = array.length - 1;
            for (int i = 0; i < array.length; i++) {
                reversed[MAX_INDEX - i] = array[i];
            }

            return reversed;
        } else {
            throw new IllegalArgumentException(new NullPointerException("A parameter(T[] array) must not be 'null': array=null"));
        }
    }

    /**
     * 주어진 배열 내의 데이터 순서를 뒤집는다.
     * 
     * @param array
     */
    public static void reverseArray(boolean[] array) {

        if (array != null) {
            // quotient: 몫
            final int quo = array.length / 2;
            final int maxIndex = array.length - 1;

            // b ^= a;
            // a ^= b
            // b ^= a;
            for (int i = 0; i < quo; i++) {
                array[i] ^= array[maxIndex - i];
                array[maxIndex - i] ^= array[i];
                array[i] ^= array[maxIndex - i];
            }
        }
    }

    /**
     * 주어진 배열 내의 데이터 순서를 뒤집는다.
     * 
     * @param array
     */
    public static void reverseArray(byte[] array) {

        if (array != null) {
            // quotient: 몫
            final int quo = array.length / 2;
            final int maxIndex = array.length - 1;

            // b ^= a;
            // a ^= b
            // b ^= a;
            for (int i = 0; i < quo; i++) {
                array[i] ^= array[maxIndex - i];
                array[maxIndex - i] ^= array[i];
                array[i] ^= array[maxIndex - i];
            }
        }
    }

    /**
     * 주어진 배열 내의 데이터 순서를 뒤집는다.
     * 
     * @param array
     */
    public static void reverseArray(char[] array) {

        if (array != null) {
            // quotient: 몫
            final int quo = array.length / 2;
            final int maxIndex = array.length - 1;

            // b ^= a;
            // a ^= b
            // b ^= a;
            for (int i = 0; i < quo; i++) {
                array[i] ^= array[maxIndex - i];
                array[maxIndex - i] ^= array[i];
                array[i] ^= array[maxIndex - i];
            }
        }
    }

    /**
     * 주어진 배열 내의 데이터 순서를 뒤집는다.
     * 
     * @param array
     */
    public static void reverseArray(int[] array) {

        if (array != null) {
            // quotient: 몫
            final int quo = array.length / 2;
            final int maxIndex = array.length - 1;

            // b ^= a;
            // a ^= b
            // b ^= a;
            for (int i = 0; i < quo; i++) {
                array[i] ^= array[maxIndex - i];
                array[maxIndex - i] ^= array[i];
                array[i] ^= array[maxIndex - i];
            }
        }
    }

    /**
     * 주어진 배열 내의 데이터 순서를 뒤집는다.
     * 
     * @param array
     */
    public static void reverseArray(long[] array) {

        if (array != null) {
            // quotient: 몫
            final int quo = array.length / 2;
            final int maxIndex = array.length - 1;

            // b ^= a;
            // a ^= b
            // b ^= a;
            for (int i = 0; i < quo; i++) {
                array[i] ^= array[maxIndex - i];
                array[maxIndex - i] ^= array[i];
                array[i] ^= array[maxIndex - i];
            }
        }
    }

    /**
     * 배열을 주어진 값으로 구분한 배열(2차원) 을 반환합니다.
     * 
     * @param array
     * @param value
     * @return 주어진 값으로 구분된 배열, 모든 값이 주어진 값과 동일한 경우 길이가 0 인 배열
     * 
     *         <BR>
     * @since 2012. 03. 20.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static boolean[][] split(boolean[] array, boolean value) {

        if (array != null) {
            int[][] splitedRanges = new int[array.length][];
            int splitedCount = 0;

            int startPos = 0;
            int i = 0;
            for (; i < array.length; i++) {
                if (array[i] == value) {
                    splitedRanges[splitedCount++] = new int[] { startPos, i - startPos };

                    startPos = i + 1;
                }
            }

            if (array[i - 1] != value) {
                splitedRanges[splitedCount++] = new int[] { startPos, i - startPos };
            }

            if (splitedCount < 1) {
                return new boolean[][] { array };
            } else if (splitedCount == array.length) {
                return new boolean[0][];
            }

            splitedRanges = copyOf(splitedRanges, splitedCount);

            boolean[][] newArray = new boolean[splitedRanges.length][];

            int[] range = null;
            splitedCount = 0;
            i = 0;
            for (; i < newArray.length; i++) {
                range = splitedRanges[i];
                if (range[1] != 0) {
                    newArray[splitedCount++] = copyOfRange(array, range[0], range[0] + range[1]);
                }
            }

            return copyOf(newArray, splitedCount);
        } else {
            throw new IllegalArgumentException("A parameter(boolean[] array) must not be 'null'", new NullPointerException("array=null"));
        }
    }

    /**
     * 배열을 주어진 값으로 구분한 배열(2차원) 을 반환합니다.
     * 
     * @param array
     * @param value
     * @return 주어진 값으로 구분된 배열, 모든 값이 주어진 값과 동일한 경우 길이가 0 인 배열
     * 
     *         <BR>
     * @since 2012. 03. 20.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static byte[][] split(byte[] array, byte value) {

        if (array != null) {
            int[][] splitedRanges = new int[array.length][];
            int splitedCount = 0;

            int startPos = 0;
            int i = 0;
            for (; i < array.length; i++) {
                if (array[i] == value) {
                    splitedRanges[splitedCount++] = new int[] { startPos, i - startPos };

                    startPos = i + 1;
                }
            }

            if (array[i - 1] != value) {
                splitedRanges[splitedCount++] = new int[] { startPos, i - startPos };
            }

            if (splitedCount < 1) {
                return new byte[][] { array };
            } else if (splitedCount == array.length) {
                return new byte[0][];
            }

            splitedRanges = copyOf(splitedRanges, splitedCount);

            byte[][] newArray = new byte[splitedRanges.length][];

            int[] range = null;
            splitedCount = 0;
            i = 0;
            for (; i < newArray.length; i++) {
                range = splitedRanges[i];
                if (range[1] != 0) {
                    newArray[splitedCount++] = copyOfRange(array, range[0], range[0] + range[1]);
                }
            }

            return copyOf(newArray, splitedCount);
        } else {
            throw new IllegalArgumentException("A parameter(byte[] array) must not be 'null'", new NullPointerException("array=null"));
        }
    }

    /**
     * 배열을 주어진 값으로 구분한 배열(2차원) 을 반환합니다.
     * 
     * @param array
     * @param value
     * @return 주어진 값으로 구분된 배열, 모든 값이 주어진 값과 동일한 경우 길이가 0 인 배열
     * 
     *         <BR>
     * @since 2012. 03. 20.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static char[][] split(char[] array, char value) {

        if (array != null) {
            int[][] splitedRanges = new int[array.length][];
            int splitedCount = 0;

            int startPos = 0;
            int i = 0;
            for (; i < array.length; i++) {
                if (array[i] == value) {
                    splitedRanges[splitedCount++] = new int[] { startPos, i - startPos };

                    startPos = i + 1;
                }
            }

            if (array[i - 1] != value) {
                splitedRanges[splitedCount++] = new int[] { startPos, i - startPos };
            }

            if (splitedCount < 1) {
                return new char[][] { array };
            } else if (splitedCount == array.length) {
                return new char[0][];
            }

            splitedRanges = copyOf(splitedRanges, splitedCount);

            char[][] newArray = new char[splitedRanges.length][];

            int[] range = null;
            splitedCount = 0;
            i = 0;
            for (; i < newArray.length; i++) {
                range = splitedRanges[i];
                if (range[1] != 0) {
                    newArray[splitedCount++] = copyOfRange(array, range[0], range[0] + range[1]);
                }
            }

            return copyOf(newArray, splitedCount);
        } else {
            throw new IllegalArgumentException("A parameter(char[] array) must not be 'null'", new NullPointerException("array=null"));
        }
    }

    /**
     * 배열을 주어진 값으로 구분한 배열(2차원) 을 반환합니다.
     * 
     * @param array
     * @param value
     * @return 주어진 값으로 구분된 배열, 모든 값이 주어진 값과 동일한 경우 길이가 0 인 배열
     * 
     *         <BR>
     * @since 2012. 03. 20.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static double[][] split(double[] array, double value) {

        if (array != null) {
            int[][] splitedRanges = new int[array.length][];
            int splitedCount = 0;

            int startPos = 0;
            int i = 0;
            for (; i < array.length; i++) {
                if (array[i] == value) {
                    splitedRanges[splitedCount++] = new int[] { startPos, i - startPos };

                    startPos = i + 1;
                }
            }

            if (array[i - 1] != value) {
                splitedRanges[splitedCount++] = new int[] { startPos, i - startPos };
            }

            if (splitedCount < 1) {
                return new double[][] { array };
            } else if (splitedCount == array.length) {
                return new double[0][];
            }

            splitedRanges = copyOf(splitedRanges, splitedCount);

            double[][] newArray = new double[splitedRanges.length][];

            int[] range = null;
            splitedCount = 0;
            i = 0;
            for (; i < newArray.length; i++) {
                range = splitedRanges[i];
                if (range[1] != 0) {
                    newArray[splitedCount++] = copyOfRange(array, range[0], range[0] + range[1]);
                }
            }

            return copyOf(newArray, splitedCount);
        } else {
            throw new IllegalArgumentException("A parameter(double[] array) must not be 'null'", new NullPointerException("array=null"));
        }
    }

    /**
     * 배열을 주어진 값으로 구분한 배열(2차원) 을 반환합니다.
     * 
     * @param array
     * @param value
     * @return 주어진 값으로 구분된 배열, 모든 값이 주어진 값과 동일한 경우 길이가 0 인 배열
     * 
     *         <BR>
     * @since 2012. 03. 20.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static int[][] split(int[] array, int value) {

        if (array != null) {
            int[][] splitedRanges = new int[array.length][];
            int splitedCount = 0;

            int startPos = 0;
            int i = 0;
            for (; i < array.length; i++) {
                if (array[i] == value) {
                    splitedRanges[splitedCount++] = new int[] { startPos, i - startPos };

                    startPos = i + 1;
                }
            }

            if (array[i - 1] != value) {
                splitedRanges[splitedCount++] = new int[] { startPos, i - startPos };
            }

            if (splitedCount < 1) {
                return new int[][] { array };
            } else if (splitedCount == array.length) {
                return new int[0][];
            }

            splitedRanges = copyOf(splitedRanges, splitedCount);

            int[][] newArray = new int[splitedRanges.length][];

            int[] range = null;
            splitedCount = 0;
            i = 0;
            for (; i < newArray.length; i++) {
                range = splitedRanges[i];
                if (range[1] != 0) {
                    newArray[splitedCount++] = copyOfRange(array, range[0], range[0] + range[1]);
                }
            }

            return copyOf(newArray, splitedCount);
        } else {
            throw new IllegalArgumentException("A parameter(int[] array) must not be 'null'", new NullPointerException("array=null"));
        }
    }

    /**
     * 배열을 주어진 값으로 구분한 배열(2차원) 을 반환합니다.
     * 
     * @param array
     * @param value
     * @return 주어진 값으로 구분된 배열, 모든 값이 주어진 값과 동일한 경우 길이가 0 인 배열
     * 
     *         <BR>
     * @since 2012. 03. 20.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static long[][] split(long[] array, long value) {

        if (array != null) {
            int[][] splitedRanges = new int[array.length][];
            int splitedCount = 0;

            int startPos = 0;
            int i = 0;
            for (; i < array.length; i++) {
                if (array[i] == value) {
                    splitedRanges[splitedCount++] = new int[] { startPos, i - startPos };

                    startPos = i + 1;
                }
            }

            if (array[i - 1] != value) {
                splitedRanges[splitedCount++] = new int[] { startPos, i - startPos };
            }

            if (splitedCount < 1) {
                return new long[][] { array };
            } else if (splitedCount == array.length) {
                return new long[0][];
            }

            splitedRanges = copyOf(splitedRanges, splitedCount);

            long[][] newArray = new long[splitedRanges.length][];

            int[] range = null;
            splitedCount = 0;
            i = 0;
            for (; i < newArray.length; i++) {
                range = splitedRanges[i];
                if (range[1] != 0) {
                    newArray[splitedCount++] = copyOfRange(array, range[0], range[0] + range[1]);
                }
            }

            return copyOf(newArray, splitedCount);
        } else {
            throw new IllegalArgumentException("A parameter(long[] array) must not be 'null'", new NullPointerException("array=null"));
        }
    }

    /**
     * 배열을 주어진 값으로 구분한 배열(2차원) 을 반환합니다.
     * 
     * @param array
     * @param value
     * @return 주어진 값으로 구분된 배열, 모든 값이 주어진 값과 동일한 경우 길이가 0 인 배열
     * 
     *         <BR>
     * @since 2012. 03. 20.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static short[][] split(short[] array, short value) {

        if (array != null) {
            int[][] splitedRanges = new int[array.length][];
            int splitedCount = 0;

            int startPos = 0;
            int i = 0;
            for (; i < array.length; i++) {
                if (array[i] == value) {
                    splitedRanges[splitedCount++] = new int[] { startPos, i - startPos };

                    startPos = i + 1;
                }
            }

            if (array[i - 1] != value) {
                splitedRanges[splitedCount++] = new int[] { startPos, i - startPos };
            }

            if (splitedCount < 1) {
                return new short[][] { array };
            } else if (splitedCount == array.length) {
                return new short[0][];
            }

            splitedRanges = copyOf(splitedRanges, splitedCount);

            short[][] newArray = new short[splitedRanges.length][];

            int[] range = null;
            splitedCount = 0;
            i = 0;
            for (; i < newArray.length; i++) {
                range = splitedRanges[i];
                if (range[1] != 0) {
                    newArray[splitedCount++] = copyOfRange(array, range[0], range[0] + range[1]);
                }
            }

            return copyOf(newArray, splitedCount);
        } else {
            throw new IllegalArgumentException("A parameter(long[] array) must not be 'null'", new NullPointerException("array=null"));
        }
    }

    /**
     * 배열을 주어진 값으로 구분한 배열(2차원) 을 반환합니다.
     * 
     * @param array
     * @param value
     *            구분자로 사용되는 데이타
     * @return 주어진 값으로 구분된 배열, 모든 값이 주어진 값과 동일한 경우 길이가 0 인 배열
     * 
     *         <BR>
     * @since 2012. 03. 20.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <T> T[][] split(T[] array, T value) {
        return split(array, value, null);
    }

    /**
     * 배열을 주어진 값으로 구분한 배열(2차원) 을 반환합니다. <br>
     * 
     * <pre>
     * -- begin of code
     * 
     * int[][] array = { new int[] { 1, 2 }, new int[] { 2, 3 }, new int[] { 3, 4 }, new int[] { 5, 6 },
     * 				new int[] { 2, 3 }, new int[] { 3, 4 }, new int[] { 2, 3 }, new int[] { 23, 5 }, new int[] { 3, 2 } };
     * 
     * 		IEquivalent<int[]> equi = new DefaultEquivalent<int[]>() {
     * 			public boolean equals(int[] t1, int[] t2) {
     * 				if( t1 != null && t2 != null ) {
     * 					if( t1.length != t2.length ) {
     * 						return false;
     * 					} else {
     * 						
     * 						int[] tt1 = copyOf(t1,t1.length);
     * 						int[] tt2 = copyOf(t2, t2.length);
     * 						Arrays.sort(tt1);
     * 						Arrays.sort(tt2);
     * 						for( int i = 0; i < t1.length; i++ ) {
     * 							if( tt1[i] != tt2[i] )
     * 								return false;
     * 						}
     * 						return true;
     * 					}
     * 				} else {
     * 					return nullEquals(t1, t2);
     * 				}
     * 			};
     * 		};
     * 
     * 		for( int[][] arr : split(array, new int[] { 2, 3 }, equi) ) {
     * 			for( int[] ar : arr ) {
     * 				System.out.println(Arrays.toString(ar));
     * 			}
     * 		}
     * 
     * -- end of code --
     * 
     * result:
     * [1, 2]
     * [3, 4]
     * [5, 6]
     * [3, 4]
     * [23, 5]
     * </pre>
     * 
     * @param array
     * @param value
     *            구분자로 사용되는 데이타
     * @param equivalent
     *            클래스 또는 타입 T의 equals(T obj) 메소드가 아닌 다른 비교 기준을 제공하는 객체
     * @return 주어진 값으로 구분된 배열, 모든 값이 주어진 값과 동일한 경우 길이가 0 인 배열
     * 
     *         <BR>
     * @since 2012. 03. 20.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <T> T[][] split(T[] array, T value, IEquivalent<T> equivalent) {

        if (array != null) {

            if (equivalent == null) {
                equivalent = new DefaultEquivalent<T>();
            }

            int[][] splitedRanges = new int[array.length][];
            int splitedCount = 0;

            int startPos = 0;
            int i = 0;
            for (; i < array.length; i++) {
                if (equivalent.equals(array[i], value)) {
                    splitedRanges[splitedCount++] = new int[] { startPos, i - startPos };

                    startPos = i + 1;
                }
            }

            if (!equivalent.equals(array[i - 1], value)) {
                splitedRanges[splitedCount++] = new int[] { startPos, i - startPos };
            }

            if (splitedCount < 1) {
                T[][] newArray = (T[][]) Array.newInstance(array.getClass(), 1);
                newArray[0] = array;
                return newArray;
            } else if (splitedCount == array.length) {
                return (T[][]) Array.newInstance(array.getClass(), 0);
            }

            splitedRanges = copyOf(splitedRanges, splitedCount);

            T[][] newArray = (T[][]) Array.newInstance(array.getClass(), splitedRanges.length);

            int[] range = null;
            splitedCount = 0;
            i = 0;
            for (; i < newArray.length; i++) {
                range = splitedRanges[i];
                if (range[1] != 0) {
                    newArray[splitedCount++] = copyOfRange(array, range[0], range[0] + range[1]);
                }
            }

            return copyOf(newArray, splitedCount);

        } else {
            throw new IllegalArgumentException("A parameter(T[] array) must not be 'null'", new NullPointerException("array=null"));
        }
    }

    /**
     * 조건에 맞는 데이터 이후부터 끝까지 데이터를 새로운 배열로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 6. 24.		박준홍			최초 작성
     * </pre>
     *
     * @param <T>
     * @param array
     * @param at
     *            조건에 맞는지 비교하는 함수. (exclusive)
     * @return
     *
     * @since 2021. 6. 24.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <T> T[] splitAfter(T[] array, Function<T, Boolean> at) {
        T[] newArr = (T[]) Array.newInstance(array.getClass().getComponentType(), array.length);

        int pos = 0;
        boolean found = false;
        for (T elem : array) {
            if (!found) {
                found = at.apply(elem);
                continue;
            }

            newArr[pos++] = elem;
        }

        if (pos < 1) {
            return (T[]) Array.newInstance(array.getClass().getComponentType(), 0);
        } else {
            return ArrayUtils.copyOf(newArr, pos);
        }
    }

    /**
     * 첫 데이터부터 주어진 조건에 맞는 데이터까지 새로운 배열로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 6. 21.     박준홍         최초 작성
     * </pre>
     *
     * @param <T>
     * @param array
     * @param at
     *            조건에 맞는지 비교하는 함수. (inclusive)
     * @param post
     *            조건에 맞는 데이터를 후처리하는 함수.
     * @return
     *
     * @since 2021. 6. 21.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <T> T[] splitAt(T[] array, Function<T, Boolean> at, Function<T, T> post) {

        T[] newArr = (T[]) Array.newInstance(array.getClass().getComponentType(), array.length);
        int pos = 0;

        for (T elem : array) {
            if (at.apply(elem)) {
                if (post != null) {
                    elem = post.apply(elem);
                }
                newArr[pos++] = elem;
                break;
            }
            newArr[pos++] = elem;
        }

        return ArrayUtils.copyOf(newArr, pos);
    }

    /**
     * 첫 데이터부터 주어진 조건에 맞는 데이터 직전까지 새로운 배열로 제공합니다. <br>
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 6. 21.		박준홍			최초 작성
     * </pre>
     *
     * @param <T>
     * @param array
     * @param at
     *            조건에 맞는지 비교하는 함수. (exclusive)
     * @return
     *
     * @since 2021. 6. 21.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <T> T[] splitBefore(T[] array, Function<T, Boolean> at) {
        T[] newArr = (T[]) Array.newInstance(array.getClass().getComponentType(), array.length);
        int pos = 0;
        for (T elem : array) {
            if (at.apply(elem)) {
                break;
            }
            newArr[pos++] = elem;
        }

        return ArrayUtils.copyOf(newArr, pos);
    }

    /**
     * 주어진 배열을 2개의 배열로 분리한 후 반환합니다.
     * 
     * @param array
     *            분리할 배열
     * @param index
     *            분리할 지점
     * @param inclusive
     *            분리할 지점의 데이타 포함 여부. If true, a first array contains it, otherwise it is omitted.
     * @return
     * 
     * @exception IllegalArgumentException
     *                주어진 배열이 <code>null</code>인 경우
     * 
     *                <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static boolean[][] subArrays(boolean[] array, int index, boolean inclusive) {
        if (array != null) {
            if (index < 0 || index > array.length - 1) {
                throw new IllegalArgumentException("A parameter(int index) must be in '0'~'" + (array.length - 1) + "': index=" + index);
            }

            index = inclusive ? index + 1 : index;

            boolean[][] result = new boolean[2][];

            boolean[] first = new boolean[index];
            boolean[] second = new boolean[array.length - index];

            System.arraycopy(array, 0, first, 0, first.length);
            System.arraycopy(array, index, second, 0, second.length);

            result[0] = first;
            result[1] = second;

            return result;
        } else {
            throw new IllegalArgumentException(new NullPointerException("A parameter(boolean[] array) must not be 'null': array=null"));
        }
    }

    /**
     * 주어진 배열을 2개의 배열로 분리한 후 반환합니다.
     * 
     * @param array
     *            분리할 배열
     * @param index
     *            분리할 지점
     * @param inclusive
     *            분리할 지점의 데이타 포함 여부. If true, a first array contains it, otherwise it is omitted.
     * @return
     * 
     * @exception IllegalArgumentException
     *                주어진 배열이 <code>null</code>인 경우
     * 
     *                <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static byte[][] subArrays(byte[] array, int index, boolean inclusive) {

        if (array != null) {
            if (index < 0 || index > array.length - 1) {
                throw new IllegalArgumentException("A parameter(int index) must be in '0'~'" + (array.length - 1) + "': index=" + index);
            }

            index = inclusive ? index + 1 : index;

            byte[][] result = new byte[2][];

            byte[] first = new byte[index];
            byte[] second = new byte[array.length - index];

            System.arraycopy(array, 0, first, 0, first.length);
            System.arraycopy(array, index, second, 0, second.length);

            result[0] = first;
            result[1] = second;

            return result;
        } else {
            throw new IllegalArgumentException(new NullPointerException("A parameter(byte[] array) must not be 'null': array=null"));
        }
    }

    /**
     * 주어진 배열을 2개의 배열로 분리한 후 반환합니다.
     * 
     * @param array
     *            분리할 배열
     * @param index
     *            분리할 지점
     * @param inclusive
     *            분리할 지점의 데이타 포함 여부. If true, a first array contains it, otherwise it is omitted.
     * @return
     * 
     * @exception IllegalArgumentException
     *                주어진 배열이 <code>null</code>인 경우
     * 
     *                <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static char[][] subArrays(char[] array, int index, boolean inclusive) {

        if (array != null) {
            if (index < 0 || index > array.length - 1) {
                throw new IllegalArgumentException("A parameter(int index) must be in '0'~'" + (array.length - 1) + "': index=" + index);
            }

            index = inclusive ? index + 1 : index;

            char[][] result = new char[2][];

            char[] first = new char[index];
            char[] second = new char[array.length - index];

            System.arraycopy(array, 0, first, 0, first.length);
            System.arraycopy(array, index, second, 0, second.length);

            result[0] = first;
            result[1] = second;

            return result;
        } else {
            throw new IllegalArgumentException(new NullPointerException("A parameter(char[] array) must not be 'null': array=null"));
        }
    }

    /**
     * 주어진 배열을 2개의 배열로 분리한 후 반환합니다.
     * 
     * @param array
     *            분리할 배열
     * @param index
     *            분리할 지점
     * @param inclusive
     *            분리할 지점의 데이타 포함 여부. If true, a first array contains it, otherwise it is omitted.
     * @return
     * 
     * @exception IllegalArgumentException
     *                주어진 배열이 <code>null</code>인 경우
     * 
     *                <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static double[][] subArrays(double[] array, int index, boolean inclusive) {

        if (array != null) {
            if (index < 0 || index > array.length - 1) {
                throw new IllegalArgumentException("A parameter(int index) must be in '0'~'" + (array.length - 1) + "': index=" + index);
            }

            index = inclusive ? index + 1 : index;

            double[][] result = new double[2][];

            double[] first = new double[index];
            double[] second = new double[array.length - index];

            System.arraycopy(array, 0, first, 0, first.length);
            System.arraycopy(array, index, second, 0, second.length);

            result[0] = first;
            result[1] = second;

            return result;
        } else {
            throw new IllegalArgumentException(new NullPointerException("A parameter(double[] array) must not be 'null': array=null"));
        }
    }

    /**
     * 주어진 배열을 2개의 배열로 분리한 후 반환합니다.
     * 
     * @param array
     *            분리할 배열
     * @param index
     *            분리할 지점
     * @param inclusive
     *            분리할 지점의 데이타 포함 여부. If true, a first array contains it, otherwise it is omitted.
     * @return
     * 
     * @exception IllegalArgumentException
     *                주어진 배열이 <code>null</code>인 경우
     * 
     *                <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static float[][] subArrays(float[] array, int index, boolean inclusive) {

        if (array != null) {
            if (index < 0 || index > array.length - 1) {
                throw new IllegalArgumentException("A parameter(int index) must be in '0'~'" + (array.length - 1) + "': index=" + index);
            }

            index = inclusive ? index + 1 : index;

            float[][] result = new float[2][];

            float[] first = new float[index];
            float[] second = new float[array.length - index];

            System.arraycopy(array, 0, first, 0, first.length);
            System.arraycopy(array, index, second, 0, second.length);

            result[0] = first;
            result[1] = second;

            return result;
        } else {
            throw new IllegalArgumentException(new NullPointerException("A parameter(float[] array) must not be 'null': array=null"));
        }
    }

    /**
     * 주어진 배열을 2개의 배열로 분리한 후 반환합니다.
     * 
     * @param array
     *            분리할 배열
     * @param index
     *            분리할 지점
     * @param inclusive
     *            분리할 지점의 데이타 포함 여부. If true, a first array contains it, otherwise it is omitted.
     * @return
     * 
     * @exception IllegalArgumentException
     *                주어진 배열이 <code>null</code>인 경우
     * 
     *                <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static int[][] subArrays(int[] array, int index, boolean inclusive) {

        if (array != null) {
            if (index < 0 || index > array.length - 1) {
                throw new IllegalArgumentException("A parameter(int index) must be in '0'~'" + (array.length - 1) + "': index=" + index);
            }

            index = inclusive ? index + 1 : index;

            int[][] result = new int[2][];

            int[] first = new int[index];
            int[] second = new int[array.length - index];

            System.arraycopy(array, 0, first, 0, first.length);
            System.arraycopy(array, index, second, 0, second.length);

            result[0] = first;
            result[1] = second;

            return result;
        } else {
            throw new IllegalArgumentException(new NullPointerException("A parameter(int[] array) must not be 'null': array=null"));
        }
    }

    /**
     * 주어진 배열을 2개의 배열로 분리한 후 반환합니다.
     * 
     * @param array
     *            분리할 배열
     * @param index
     *            분리할 지점
     * @param inclusive
     *            분리할 지점의 데이타 포함 여부. If true, a first array contains it, otherwise it is omitted.
     * @return
     * 
     * @exception IllegalArgumentException
     *                주어진 배열이 <code>null</code>인 경우
     * 
     *                <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static long[][] subArrays(long[] array, int index, boolean inclusive) {

        if (array != null) {
            if (index < 0 || index > array.length - 1) {
                throw new IllegalArgumentException("A parameter(int index) must be in '0'~'" + (array.length - 1) + "': index=" + index);
            }

            index = inclusive ? index + 1 : index;

            long[][] result = new long[2][];

            long[] first = new long[index];
            long[] second = new long[array.length - index];

            System.arraycopy(array, 0, first, 0, first.length);
            System.arraycopy(array, index, second, 0, second.length);

            result[0] = first;
            result[1] = second;

            return result;
        } else {
            throw new IllegalArgumentException(new NullPointerException("A parameter(long[] array) must not be 'null': array=null"));
        }
    }

    /**
     * 주어진 배열을 2개의 배열로 분리한 후 반환합니다.
     * 
     * @param array
     *            분리할 배열
     * @param index
     *            분리할 지점
     * @param inclusive
     *            분리할 지점의 데이타 포함 여부. If true, a first array contains it, otherwise it is omitted.
     * @return
     * 
     * @exception IllegalArgumentException
     *                주어진 배열이 <code>null</code>인 경우
     * 
     *                <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static short[][] subArrays(short[] array, int index, boolean inclusive) {

        if (array != null) {
            if (index < 0 || index > array.length - 1) {
                throw new IllegalArgumentException("A parameter(int index) must be in '0'~'" + (array.length - 1) + "': index=" + index);
            }

            index = inclusive ? index + 1 : index;

            short[][] result = new short[2][];

            short[] first = new short[index];
            short[] second = new short[array.length - index];

            System.arraycopy(array, 0, first, 0, first.length);
            System.arraycopy(array, index, second, 0, second.length);

            result[0] = first;
            result[1] = second;

            return result;
        } else {
            throw new IllegalArgumentException(new NullPointerException("A parameter(short[] array) must not be 'null': array=null"));
        }
    }

    /**
     * 주어진 배열을 2개의 배열로 분리한 후 반환합니다.
     * 
     * @param T
     *            배열의 원소 타입.
     * @param array
     *            분리할 배열
     * @param index
     *            분리할 지점
     * @param inclusive
     *            분리할 지점의 데이타 포함 여부. If true, a first array contains it, otherwise it is omitted.
     * @return
     * 
     * @exception IllegalArgumentException
     *                주어진 배열이 <code>null</code>인 경우
     * 
     *                <BR>
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */

    public static <T> Object[][] subArrays(T[] array, int index, boolean inclusive) {

        if (array != null) {
            if (index < 0 || index > array.length - 1) {
                throw new IllegalArgumentException("A parameter(int index) must be in '0'~'" + (array.length - 1) + "': index=" + index);
            }

            index = inclusive ? index + 1 : index;

            Object[][] result = new Object[2][];

            T[] first = (T[]) Array.newInstance(array.getClass().getComponentType(), index);
            T[] second = (T[]) Array.newInstance(array.getClass().getComponentType(), array.length - index);

            System.arraycopy(array, 0, first, 0, first.length);
            System.arraycopy(array, index, second, 0, second.length);

            result[0] = first;
            result[1] = second;

            return result;
        } else {
            throw new IllegalArgumentException(new NullPointerException("A parameter(T[] array) must not be 'null': array=null"));
        }
    }

    public static boolean[] toPrimitiveArray(Boolean[] arr) {
        boolean[] array = new boolean[arr.length];

        for (int i = 0; i < arr.length; i++) {
            array[i] = arr[i];
        }

        return array;
    }

    public static byte[] toPrimitiveArray(Byte[] arr) {
        byte[] array = new byte[arr.length];

        for (int i = 0; i < arr.length; i++) {
            array[i] = arr[i];
        }

        return array;
    }

    public static char[] toPrimitiveArray(Character[] arr) {
        char[] array = new char[arr.length];

        for (int i = 0; i < arr.length; i++) {
            array[i] = arr[i];
        }

        return array;
    }

    public static double[] toPrimitiveArray(Double[] arr) {
        double[] array = new double[arr.length];

        for (int i = 0; i < arr.length; i++) {
            array[i] = arr[i];
        }

        return array;
    }

    public static float[] toPrimitiveArray(Float[] arr) {
        float[] array = new float[arr.length];

        for (int i = 0; i < arr.length; i++) {
            array[i] = arr[i];
        }

        return array;
    }

    public static int[] toPrimitiveArray(Integer[] arr) {
        int[] array = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            array[i] = arr[i];
        }

        return array;
    }

    public static long[] toPrimitiveArray(Long[] arr) {
        long[] array = new long[arr.length];

        for (int i = 0; i < arr.length; i++) {
            array[i] = arr[i];
        }

        return array;
    }

    public static short[] toPrimitiveArray(Short[] arr) {
        short[] array = new short[arr.length];

        for (int i = 0; i < arr.length; i++) {
            array[i] = arr[i];
        }

        return array;
    }

    /**
     * Returns the result of deep {@link Arrays#toString(args)}
     * 
     * @param array
     * @return
     * 
     * @since 2012. 03. 30.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static String toString(Object[] array) {

        StringBuffer sb = new StringBuffer();

        sb.append('[');
        if (array.length > 0) {
            sb.append(toString0(array[0]));

            int i = 1;
            for (; i < array.length; i++) {
                sb.append(", " + toString0(array[i]));
            }
        }

        sb.append(']');

        return sb.toString();
    }

    private static String toString0(Object obj) {
        if (obj == null) {
            return null;
        }

        if (obj.getClass().isArray()) {
            if (obj.getClass().getComponentType() == boolean.class) {
                return Arrays.toString((boolean[]) obj);
            } else if (obj.getClass().getComponentType() == byte.class) {
                return Arrays.toString((byte[]) obj);
            } else if (obj.getClass().getComponentType() == char.class) {
                return Arrays.toString((char[]) obj);
            } else if (obj.getClass().getComponentType() == double.class) {
                return Arrays.toString((double[]) obj);
            } else if (obj.getClass().getComponentType() == float.class) {
                return Arrays.toString((float[]) obj);
            } else if (obj.getClass().getComponentType() == int.class) {
                return Arrays.toString((int[]) obj);
            } else if (obj.getClass().getComponentType() == long.class) {
                return Arrays.toString((long[]) obj);
            } else {
                return toString((Object[]) obj);
            }
        } else {
            return obj.toString();
        }
    }

    public static Boolean[] toWrapperArray(boolean[] arr) {
        Boolean[] array = new Boolean[arr.length];

        for (int i = 0; i < arr.length; i++) {
            array[i] = arr[i];
        }

        return array;
    }

    public static Byte[] toWrapperArray(byte[] arr) {
        Byte[] array = new Byte[arr.length];

        for (int i = 0; i < arr.length; i++) {
            array[i] = arr[i];
        }

        return array;
    }

    public static Character[] toWrapperArray(char[] arr) {
        Character[] array = new Character[arr.length];

        for (int i = 0; i < arr.length; i++) {
            array[i] = arr[i];
        }

        return array;
    }

    public static Double[] toWrapperArray(double[] arr) {
        Double[] array = new Double[arr.length];

        for (int i = 0; i < arr.length; i++) {
            array[i] = arr[i];
        }

        return array;
    }

    public static Float[] toWrapperArray(float[] arr) {
        Float[] array = new Float[arr.length];

        for (int i = 0; i < arr.length; i++) {
            array[i] = arr[i];
        }

        return array;
    }

    public static Integer[] toWrapperArray(int[] arr) {
        Integer[] array = new Integer[arr.length];

        for (int i = 0; i < arr.length; i++) {
            array[i] = arr[i];
        }

        return array;
    }

    public static Long[] toWrapperArray(long[] arr) {
        Long[] array = new Long[arr.length];

        for (int i = 0; i < arr.length; i++) {
            array[i] = arr[i];
        }

        return array;
    }

    public static <T> T[] toWrapperArray(Object array) {

        Class<?> componentType = array.getClass().getComponentType();

        if (componentType.isPrimitive()) {

            if (boolean.class.equals(componentType)) {
                return (T[]) toWrapperArray((boolean[]) array);
            }

            if (byte.class.equals(componentType)) {
                return (T[]) toWrapperArray((byte[]) array);
            }

            if (char.class.equals(componentType)) {
                return (T[]) toWrapperArray((char[]) array);
            }

            if (int.class.equals(componentType)) {
                return (T[]) toWrapperArray((int[]) array);
            }

            if (long.class.equals(componentType)) {
                return (T[]) toWrapperArray((long[]) array);
            }

            if (float.class.equals(componentType)) {
                return (T[]) toWrapperArray((float[]) array);
            }

            if (double.class.equals(componentType)) {
                return (T[]) toWrapperArray((double[]) array);
            }

            return null;

        } else {
            return (T[]) array;
        }
    }

    public static Short[] toWrapperArray(short[] arr) {
        Short[] array = new Short[arr.length];

        for (int i = 0; i < arr.length; i++) {
            array[i] = arr[i];
        }

        return array;
    }

    /**
     * 데이터를 변환하여 배열로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2023. 7. 25.		박준홍			최초 작성
     * </pre>
     *
     * @param <T>
     * @param <R>
     * @param arr
     *            데이터 배열
     * @param f
     *            데이터 변환 함수
     * @return
     *
     * @since 2023. 7. 25.
     * @version 2.0.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <T, R> R[] transform(T[] arr, Function<T, R> f) {
        if (arr == null) {
            return null;
        }

        return (R[]) Stream.of(arr).map(f).toArray();
    }

    public static class EntryValue<K, V> implements Entry<K, V> {

        private K key;
        private V value;

        /**
         * @param key
         * @param value
         * @since 2021. 8. 15.
         */
        public EntryValue(K key, V value) {
            this.key = key;
            this.value = value;
        }

        /**
         * <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜    	| 작성자	|	내용
         * ------------------------------------------
         * 2021. 8. 15.		박준홍			최초 작성
         * </pre>
         *
         * @return
         *
         * @since 2021. 8. 15.
         * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
         *
         * @see java.util.Map.Entry#getKey()
         */
        @Override
        public K getKey() {
            return this.key;
        }

        /**
         * <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜    	| 작성자	|	내용
         * ------------------------------------------
         * 2021. 8. 15.		박준홍			최초 작성
         * </pre>
         *
         * @return
         *
         * @since 2021. 8. 15.
         * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
         *
         * @see java.util.Map.Entry#getValue()
         */
        @Override
        public V getValue() {
            return this.value;
        }

        /**
         * <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜    	| 작성자	|	내용
         * ------------------------------------------
         * 2021. 8. 15.		박준홍			최초 작성
         * </pre>
         *
         * @param value
         * @return
         *
         * @since 2021. 8. 15.
         * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
         *
         * @see java.util.Map.Entry#setValue(java.lang.Object)
         */
        @Override
        public V setValue(V value) {
            V v = this.value;
            this.value = value;
            return v;
        }

    }
}
