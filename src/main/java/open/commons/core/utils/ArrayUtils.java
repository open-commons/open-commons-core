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
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import org.jspecify.annotations.Nullable;

import open.commons.core.DefaultEquivalent;
import open.commons.core.EquivalentFactory;
import open.commons.core.IEquivalent;
import open.commons.core.Result;
import open.commons.core.collection.DefaultContainer;
import open.commons.core.collection.IContainer;

/**
 * 배열에 대한 기능성 메소드를 제공하는 클래스
 * 
 * @since 2012. 02. 21.
 * @version 1.0.0
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */
@SuppressWarnings("unchecked")
public class ArrayUtils {

    /**
     * 기존 배열에 새로운 데이타를 맨 뒤에 추가한 후, 새로운 배열을 반환합니다.
     * 
     * @param array
     *            데이터를 추가할 배열
     * @param value
     *            추가할 새로운 데이터
     * @return
     * 
     * @since 2012. 3. 9.
     * 
     */
    public static boolean[] add(boolean @Nullable [] array, boolean value) {

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
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 7. 4.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param array
     *            데이터를 추가할 배열
     * @param values
     *            추가할 새로운 데이터
     * @return
     *
     * @since 2019. 7. 4.
     * 
     */
    public static boolean[] add(boolean @Nullable [] array, boolean @Nullable... values) {
        return merge(array, values);
    }

    /**
     * 기존 배열에 새로운 데이타를 맨 뒤에 추가한 후, 새로운 배열을 반환합니다.
     * 
     * @param array
     *            데이터를 추가할 배열
     * @param value
     *            새로운 데이터
     * @return
     * 
     * @since 2012. 3. 9.
     * 
     */
    public static byte[] add(byte @Nullable [] array, byte value) {
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
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 7. 4.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param array
     *            데이터를 추가할 배열
     * @param values
     *            새로운 데이터
     * @return
     *
     * @since 2019. 7. 4.
     * 
     */
    public static byte[] add(byte @Nullable [] array, byte @Nullable... values) {
        return merge(array, values);
    }

    /**
     * 기존 배열에 새로운 데이타를 맨 뒤에 추가한 후, 새로운 배열을 반환합니다.
     * 
     * @param array
     * @param value
     * @return
     * 
     * @since 2012. 3. 9.
     * 
     */
    public static char[] add(char @Nullable [] array, char value) {
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
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 7. 4.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param array
     *            데이터를 추가할 배열
     * @param values
     *            새로운 데이터
     * @return
     *
     * @since 2019. 7. 4.
     * 
     */
    public static char[] add(char @Nullable [] array, char @Nullable... values) {
        return merge(array, values);
    }

    /**
     * 기존 배열에 새로운 데이타를 맨 뒤에 추가한 후, 새로운 배열을 반환합니다.
     * 
     * @param array
     * @param value
     * @return
     * 
     * @since 2012. 3. 9.
     * 
     */
    public static double[] add(double @Nullable [] array, double value) {
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
     * 2019. 7. 4.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param array
     *            데이터를 추가할 배열
     * @param values
     *            새로운 데이터
     * @return
     *
     * @since 2019. 7. 4.
     * 
     */
    public static double[] add(double @Nullable [] array, double @Nullable... values) {
        return merge(array, values);
    }

    /**
     * 기존 배열에 새로운 데이타를 맨 뒤에 추가한 후, 새로운 배열을 반환합니다.
     * 
     * @param array
     * @param value
     * @return
     * 
     * @since 2012. 3. 9.
     * 
     */
    public static float[] add(float @Nullable [] array, float value) {
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
     * 2019. 7. 4.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param array
     *            데이터를 추가할 배열
     * @param values
     *            새로운 데이터
     * @return
     *
     * @since 2019. 7. 4.
     * 
     */
    public static float[] add(float @Nullable [] array, float @Nullable... values) {
        return merge(array, values);
    }

    /**
     * 기존 배열에 새로운 데이타를 맨 뒤에 추가한 후, 새로운 배열을 반환합니다.
     * 
     * @param array
     * @param value
     * @return
     * 
     * @since 2012. 3. 9.
     * 
     */
    public static int[] add(int @Nullable [] array, int value) {
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
     * 2019. 7. 4.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param array
     *            데이터를 추가할 배열
     * @param values
     *            새로운 데이터
     * @return
     *
     * @since 2019. 7. 4.
     * 
     */
    public static int[] add(int @Nullable [] array, int @Nullable... values) {
        return merge(array, values);
    }

    /**
     * 기존 배열에 새로운 데이타를 맨 뒤에 추가한 후, 새로운 배열을 반환합니다.
     * 
     * @param array
     * @param value
     * @return
     * 
     * @since 2012. 3. 9.
     * 
     */
    public static long[] add(long @Nullable [] array, long value) {
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
     * 2019. 7. 4.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param array
     *            데이터를 추가할 배열
     * @param values
     *            새로운 데이터
     * @return
     *
     * @since 2019. 7. 4.
     * 
     */
    public static long[] add(long @Nullable [] array, long @Nullable... values) {
        return merge(array, values);
    }

    /**
     * 기존 배열에 새로운 데이타를 맨 뒤에 추가한 후, 새로운 배열을 반환합니다.
     * 
     * @param array
     * @param value
     * @return
     * 
     * @since 2012. 3. 9.
     * 
     */
    public static short[] add(short @Nullable [] array, short value) {
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
     * 2019. 7. 4.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param array
     *            데이터를 추가할 배열
     * @param values
     *            새로운 데이터
     * @return
     *
     * @since 2019. 7. 4.
     * 
     */
    public static short[] add(short @Nullable [] array, short @Nullable... values) {
        return merge(array, values);
    }

    /**
     * 기존 배열에 새로운 데이타를 맨 뒤에 추가한 후, 새로운 배열을 반환합니다.
     * 
     * @param array
     * @param value
     * @return a new array contains new {@code value}
     * 
     * @throws ArrayStoreException
     *             {@code T} is Wrapper Class of primitive types and {@code value} is the primitive type's value.
     *             <p>
     *             e.g. add(new Boolean[] { true }, false);
     *             </p>
     * @throws NullPointerException
     *             파라미터({@code array, value 모두})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 3. 9.
     * 
     */
    // 아래 내용에 적용됨.
    // - (T[]) Array.newInstance(array.getClass().getComponentType(), 1)
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static <T extends @Nullable Object> T[] add(T @Nullable [] array, T value) {
        if (array != null && value != null) {

            assertComponentType(array, value.getClass());

            T[] newArray = (T[]) Array.newInstance(array.getClass().getComponentType(), array.length + 1);
            System.arraycopy(array, 0, newArray, 0, array.length);
            newArray[newArray.length - 1] = value;

            return newArray;
        } else if (array != null) {
            return (T[]) Array.newInstance(array.getClass().getComponentType(), 1);
        } else if (value != null) {
            T[] newArray = (T[]) Array.newInstance(value.getClass(), 1);
            newArray[0] = value;

            return newArray;
        } else {
            throw new NullPointerException("All parameters(T[] array, T value) must not be null: arr=null, value=null");
        }
    }

    /**
     * 기존 배열에 새로운 데이타를 맨 뒤에 추가한 후, 새로운 배열을 반환합니다. <br>
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 7. 4.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param array
     *            데이터를 추가할 배열
     * @param values
     *            새로운 데이터
     * @return
     *
     * @since 2019. 7. 4.
     * 
     */
    public static <T extends @Nullable Object> T[] add(T @Nullable [] array, T @Nullable... values) {
        return ArrayUtils.<T> merge(array, values);
    }

    /**
     * 기존 배열에 새로운 데이타를 맨 뒤에 추가한 후, 새로운 배열을 반환합니다.
     * 
     * @param array
     * @param value
     * @return
     * 
     * @since 2012. 3. 9.
     * 
     */
    // 아래 내용에 적용됨.
    // - Arrays.copyOf(array, array.length)
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static boolean[] addIfAbsent(boolean @Nullable [] array, boolean value) {
        if (contains(array, value)) {
            return Arrays.copyOf(array, array.length);
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
     * @since 2012. 3. 9.
     * 
     */
    // 아래 내용에 적용됨.
    // - Arrays.copyOf(array, array.length)
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static byte[] addIfAbsent(byte @Nullable [] array, byte value) {
        if (contains(array, value)) {
            return Arrays.copyOf(array, array.length);
        }

        return add(array, value);
    }

    /**
     * 기존 배열에 새로운 데이타를 맨 뒤에 추가한 후, 새로운 배열을 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 7. 4.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param array
     *            데이터를 추가할 배열
     * @param values
     *            새로운 데이터
     * @return
     *
     * @since 2019. 7. 4.
     * 
     */
    // 아래 내용에 적용됨.
    // - Arrays.copyOf(array, array.length)
    // - indice.toArray(new Byte[0])
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static byte[] addIfAbsent(byte @Nullable [] array, byte @Nullable... values) {
        if (values == null || values.length < 1) {
            return Arrays.copyOf(array, array.length);
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
     * @since 2012. 3. 9.
     * 
     */
    // 아래 내용에 적용됨.
    // - Arrays.copyOf(array, array.length)
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static char[] addIfAbsent(char @Nullable [] array, char value) {
        if (contains(array, value)) {
            return Arrays.copyOf(array, array.length);
        }

        return add(array, value);
    }

    /**
     * 기존 배열에 새로운 데이타를 맨 뒤에 추가한 후, 새로운 배열을 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 7. 4.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param array
     *            데이터를 추가할 배열
     * @param values
     *            새로운 데이터
     * @return
     *
     * @since 2019. 7. 4.
     * 
     */
    // 아래 내용에 적용됨.
    // - Arrays.copyOf(array, array.length)
    // - indice.toArray(new Character[0])
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static char[] addIfAbsent(char @Nullable [] array, char @Nullable... values) {
        if (values == null || values.length < 1) {
            return Arrays.copyOf(array, array.length);
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
     * @since 2012. 3. 9.
     * 
     */
    // 아래 내용에 적용됨.
    // - Arrays.copyOf(array, array.length)
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static double[] addIfAbsent(double @Nullable [] array, double value) {
        if (contains(array, value)) {
            return Arrays.copyOf(array, array.length);
        }

        return add(array, value);
    }

    /**
     * 기존 배열에 새로운 데이타를 맨 뒤에 추가한 후, 새로운 배열을 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 7. 4.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param array
     *            데이터를 추가할 배열
     * @param values
     *            새로운 데이터
     * @return
     *
     * @since 2019. 7. 4.
     * 
     */
    // 아래 내용에 적용됨.
    // - Arrays.copyOf(array, array.length)
    // - indice.toArray(new Double[0])
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static double[] addIfAbsent(double @Nullable [] array, double @Nullable... values) {
        if (values == null || values.length < 1) {
            return Arrays.copyOf(array, array.length);
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
     * @since 2012. 3. 9.
     * 
     */
    // 아래 내용에 적용됨.
    // - Arrays.copyOf(array, array.length)
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static float[] addIfAbsent(float @Nullable [] array, float value) {
        if (contains(array, value)) {
            return Arrays.copyOf(array, array.length);
        }

        return add(array, value);
    }

    /**
     * 기존 배열에 새로운 데이타를 맨 뒤에 추가한 후, 새로운 배열을 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 7. 4.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param array
     *            데이터를 추가할 배열
     * @param values
     *            새로운 데이터
     * @return
     *
     * @since 2019. 7. 4.
     * 
     */

    // 아래 내용에 적용됨.
    // - Arrays.copyOf(array, array.length)
    // - indice.toArray(new Float[0])
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static float[] addIfAbsent(float @Nullable [] array, float @Nullable... values) {
        if (values == null || values.length < 1) {
            return Arrays.copyOf(array, array.length);
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
     * @since 2012. 3. 9.
     * 
     */
    // 아래 내용에 적용됨.
    // - Arrays.copyOf(array, array.length)
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static int[] addIfAbsent(int @Nullable [] array, int value) {
        if (contains(array, value)) {
            return Arrays.copyOf(array, array.length);
        }

        return add(array, value);
    }

    /**
     * 기존 배열에 새로운 데이타를 맨 뒤에 추가한 후, 새로운 배열을 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 7. 4.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param array
     *            데이터를 추가할 배열
     * @param values
     *            새로운 데이터
     * @return
     *
     * @since 2019. 7. 4.
     * 
     */
    // 아래 내용에 적용됨.
    // - Arrays.copyOf(array, array.length)
    // - indice.toArray(new Integer[0])
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static int[] addIfAbsent(int @Nullable [] array, int @Nullable... values) {
        if (values == null || values.length < 1) {
            return Arrays.copyOf(array, array.length);
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
     * @since 2012. 3. 9.
     * 
     */
    // 아래 내용에 적용됨.
    // - Arrays.copyOf(array, array.length)
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static long[] addIfAbsent(long @Nullable [] array, long value) {
        if (contains(array, value)) {
            return Arrays.copyOf(array, array.length);
        }

        return add(array, value);
    }

    /**
     * 기존 배열에 새로운 데이타를 맨 뒤에 추가한 후, 새로운 배열을 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 7. 4.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param array
     *            데이터를 추가할 배열
     * @param values
     *            새로운 데이터
     * @return
     *
     * @since 2019. 7. 4.
     * 
     */
    // 아래 내용에 적용됨.
    // - Arrays.copyOf(array, array.length)
    // - indice.toArray(new Long[0])
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static long[] addIfAbsent(long @Nullable [] array, long @Nullable... values) {
        if (values == null || values.length < 1) {
            return Arrays.copyOf(array, array.length);
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
     * @since 2012. 3. 9.
     * 
     */
    // 아래 내용에 적용됨.
    // - Arrays.copyOf(array, array.length)
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static short[] addIfAbsent(short @Nullable [] array, short value) {
        if (contains(array, value)) {
            return Arrays.copyOf(array, array.length);
        }

        return add(array, value);
    }

    /**
     * 기존 배열에 새로운 데이타를 맨 뒤에 추가한 후, 새로운 배열을 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 7. 4.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param array
     *            데이터를 추가할 배열
     * @param values
     *            새로운 데이터
     * @return
     *
     * @since 2019. 7. 4.
     * 
     */
    // 아래 내용에 적용됨.
    // - Arrays.copyOf(array, array.length)
    // - indice.toArray(new Short[0])
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static short[] addIfAbsent(short @Nullable [] array, short @Nullable... values) {
        if (values == null || values.length < 1) {
            if (array != null) {
                return Arrays.copyOf(array, array.length);
            } else {
                return new short[0];
            }
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
     * @return a new array contains new {@code value} or null if both of parameters are {@code null}.
     * 
     * @Exception {@link ArrayStoreException} - {@code T} is Wrapper Class of primitive types and {@code value} is the
     *            primitive type's value.
     *            <p>
     *            e.g. add(new Boolean[] { true }, false);
     *            </p>
     * 
     * @since 2012. 3. 9.
     * 
     */
    // 아래 내용에 적용됨.
    // - Arrays.copyOf(array, array.length)
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static <T extends @Nullable Object> T[] addIfAbsent(T @Nullable [] array, T value) {
        if (contains(array, value)) {
            return Arrays.copyOf(array, array.length);
        }

        return add(array, value);
    }

    /**
     * 주어진 길이(<b>{@code length}</b>)만큼 주어진 배열(<b>{@code classes}</b>) 로부터 데이터를 복사하여 새로운 배열을 반환합니다.<br>
     * 주어진 배열(<b>{@code classes}</b>)의 길이가 주어진 길이(<b>{@code length}</b>)보다 작은 경우, 마지막 데이터로 모두 채운다.
     * 
     * @param length
     * @param classes
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code classeses})가 {@code null}이거나 {@code null}을 포함한 경우 발생.
     * 
     * @since 2014. 6. 18.
     */
    // 아래 내용에 적용됨.
    // - ObjectUtils.requireNonNulls((Object[]) classes);
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static Class<?>[] adjustByLength(int length, Class<?>... classes) {
        ObjectUtils.requireNonNulls((Object[]) classes);

        Class<?>[] newArray = new Class<?>[length];
        System.arraycopy(classes, 0, newArray, 0, Math.min(length, classes.length));

        if (length > classes.length) {
            for (int i = classes.length; i < length; i++) {
                newArray[i] = classes[classes.length - 1];
            }
        }

        return newArray;
    }

    /**
     * 배열을 {@link List}로 변환하여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2023. 7. 25.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <T>
     * @param arr
     * @return {@link List} 또는 {@code null}.
     *
     * @since 2023. 7. 25.
     * @version 2.0.0
     * 
     */
    public static <T extends @Nullable Object> @Nullable List<T> asList(T @Nullable [] arr) {
        return arr != null ? Arrays.asList(arr) : null;
    }

    /**
     * 데이터를 변환하여 {@link List}로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2023. 7. 25.     parkjunhong77@gmail.com         최초 작성
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
     * 
     */
    public static <T extends @Nullable Object, R extends @Nullable Object> @Nullable List<R> asList(T @Nullable [] arr, Function<T, R> f) {
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

    // 아래 내용에 적용됨.
    // - arr1.getClass().getComponentType()
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    private static <T> void assertComponentType(T[] arr1, Class<?> valueType) {
        assertComponentType(arr1.getClass().getComponentType(), valueType);
    }

    // 아래 내용에 적용됨.
    // - arr1.getClass().getComponentType()
    // - arr2.getClass().getComponentType()
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
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
     *            값
     * @since 2012. 03. 13.
     * 
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
     *             두 개의 입력값 중 하나라도 {@code null}인 경우.
     */
    public static int comparable(byte[] v1, byte[] v2) {
        ObjectUtils.requireNonNulls(v1, v2);

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
     *             두 개의 입력값 중 하나라도 {@code null}인 경우.
     */
    public static int comparable(char[] v1, char[] v2) {
        ObjectUtils.requireNonNulls(v1, v2);

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
     *             두 개의 입력값 중 하나라도 {@code null}인 경우.
     */
    public static int comparable(double[] v1, double[] v2) {

        ObjectUtils.requireNonNulls(v1, v2);

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
     *             두 개의 입력값 중 하나라도 {@code null}인 경우.
     */
    public static int comparable(float[] v1, float[] v2) {

        ObjectUtils.requireNonNulls(v1, v2);

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
     *             두 개의 입력값 중 하나라도 {@code null}인 경우.
     */
    public static int comparable(int[] v1, int[] v2) {

        ObjectUtils.requireNonNulls(v1, v2);

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
     *             두 개의 입력값 중 하나라도 {@code null}인 경우.
     */
    public static <T extends Comparable<T>> int comparable(T[] v1, T[] v2) {

        ObjectUtils.requireNonNulls(v1, v2);

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

    public static @Nullable String concatenate(byte @Nullable [] array, char delimiter) {
        if (array == null || array.length < 1) {
            return null;
        }

        StringBuilder sb = new StringBuilder();

        int i = 0;

        sb.append(array[i++]);

        for (; i < array.length; i++) {
            sb.append(delimiter);
            sb.append(array[i]);
        }

        return sb.toString();
    }

    public static @Nullable String concatenate(char @Nullable [] array, char delimiter) {
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

    public static @Nullable String concatenate(float @Nullable [] array, char delimiter) {
        if (array == null || array.length < 1) {
            return null;
        }

        StringBuilder sb = new StringBuilder();

        int i = 0;

        sb.append(array[i++]);

        for (; i < array.length; i++) {
            sb.append(delimiter);
            sb.append(array[i]);
        }

        return sb.toString();
    }

    public static @Nullable String concatenate(int @Nullable [] array, char delimiter) {
        if (array == null || array.length < 1) {
            return null;
        }

        StringBuilder sb = new StringBuilder();

        int i = 0;

        sb.append(array[i++]);

        for (; i < array.length; i++) {
            sb.append(delimiter);
            sb.append(array[i]);
        }

        return sb.toString();
    }

    /**
     * Returns a concatenated string of elements of <b>{@code the given array}</b>.
     * 
     * @param array
     * @param delimiter
     * @return
     */
    // apply to '배열 데이터 null-check'
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static <T extends @Nullable Object> @Nullable String concatenate(T @Nullable [] array, char delimiter) {
        if (array == null || array.length < 1) {
            return null;
        }

        char[][] chars = new char[array.length][];

        chars[0] = (array[0] != null ? array[0] : "null").toString().toCharArray();

        for (int i = 1; i < array.length; i++) {
            chars[i] = prepend((array[i] != null ? array[i] : "null").toString().strip().toCharArray(), delimiter);
        }

        char[] merged = merge(chars);
        return merged == null ? null : new String(merged);
    }

    /**
     * Returns a concatenated string of elements of <b>{@code the given array}</b>.
     * 
     * @param array
     * @param delimiter
     * @return
     */
    // apply to '배열 데이터 null-check'
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static <T extends @Nullable Object> @Nullable String concatenate(T @Nullable [] array, String delimiter) {
        if (array == null || array.length < 1) {
            return null;
        }

        char[] delim = delimiter != null ? delimiter.toCharArray() : new char[] {};
        char[][] chars = new char[array.length][];

        chars[0] = (array[0] != null ? array[0] : "null").toString().toCharArray();

        for (int i = 1; i < array.length; i++) {
            chars[i] = merge(delim, (array[i] != null ? array[i] : "null").toString().strip().toCharArray());
        }

        char[] merged = merge(chars);
        return merged == null ? null : new String(merged);
    }

    /**
     * 주어진 배열에 찾고자 하는 값이 있는지 여부를 반환합니다.
     * 
     * @param array
     * @param value
     * @return
     */
    public static boolean contains(boolean @Nullable [] array, boolean value) {
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
    public static boolean contains(byte @Nullable [] array, byte value) {
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
    public static boolean contains(char @Nullable [] array, char value) {
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
    public static boolean contains(double @Nullable [] array, double value) {
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
    public static boolean contains(float @Nullable [] array, float value) {
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
    public static boolean contains(int @Nullable [] array, int value) {
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
    public static boolean contains(long @Nullable [] array, long value) {
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
    public static boolean contains(short @Nullable [] array, short value) {
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
    public static <T extends @Nullable Object> boolean contains(T @Nullable [] array, T value) {
        if (array == null) {
            return false;
        }

        for (T t : array) {
            if (Objects.equals(t, value)) {
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
    public static <T extends @Nullable Object> Result<T> containsPart(T @Nullable [] array, T t, @Nullable IContainer<T> container) {
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
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2023. 8. 2.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param arr
     *            복사할 배열
     * @param indice
     *            복사할 데이터의 index.
     * @return
     * @throws ArrayIndexOutOfBoundsException
     * @throws NullPointerException
     *             파라미터({@code arr, indice 중에 1개라도})가 {@code null}인 경우 발생.
     * 
     * @since 2023. 8. 2.
     * @version 2.0.0
     * 
     */
    public static boolean[] copy(boolean[] arr, int... indice) throws ArrayIndexOutOfBoundsException {
        ObjectUtils.requireNonNulls(arr, indice);

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
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2023. 8. 2.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param arr
     *            복사할 배열
     * @param indice
     *            복사할 데이터의 index.
     * @return
     * @throws ArrayIndexOutOfBoundsException
     * @throws NullPointerException
     *             파라미터({@code arr, indice 중에 1개라도})가 {@code null}인 경우 발생.
     * 
     * @since 2023. 8. 2.
     * @version 2.0.0
     * 
     */
    public static byte[] copy(byte[] arr, int... indice) throws ArrayIndexOutOfBoundsException {
        ObjectUtils.requireNonNulls(arr, indice);

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
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2023. 8. 2.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param arr
     *            복사할 배열
     * @param indice
     *            복사할 데이터의 index.
     * @return
     * @throws ArrayIndexOutOfBoundsException
     * @throws NullPointerException
     *             파라미터({@code arr, indice 중에 1개라도})가 {@code null}인 경우 발생.
     * 
     * @since 2023. 8. 2.
     * @version 2.0.0
     * 
     */
    public static char[] copy(char[] arr, int... indice) throws ArrayIndexOutOfBoundsException {
        ObjectUtils.requireNonNulls(arr, indice);

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
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2023. 8. 2.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param arr
     *            복사할 배열
     * @param indice
     *            복사할 데이터의 index.
     * @return
     * @throws ArrayIndexOutOfBoundsException
     * @throws NullPointerException
     *             파라미터({@code arr, indice 중에 1개라도})가 {@code null}인 경우 발생.
     * 
     * @since 2023. 8. 2.
     * @version 2.0.0
     * 
     */
    public static double[] copy(double[] arr, int... indice) throws ArrayIndexOutOfBoundsException {
        ObjectUtils.requireNonNulls(arr, indice);

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
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2023. 8. 2.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param arr
     *            복사할 배열
     * @param indice
     *            복사할 데이터의 index.
     * @return
     * @throws ArrayIndexOutOfBoundsException
     * @throws NullPointerException
     *             파라미터({@code arr, indice 중에 1개라도})가 {@code null}인 경우 발생.
     * 
     * @since 2023. 8. 2.
     * @version 2.0.0
     * 
     */
    public static float[] copy(float[] arr, int... indice) throws ArrayIndexOutOfBoundsException {
        ObjectUtils.requireNonNulls(arr, indice);

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
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2023. 8. 2.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param arr
     *            복사할 배열
     * @param indice
     *            복사할 데이터의 index.
     * @return
     * @throws ArrayIndexOutOfBoundsException
     * @throws NullPointerException
     *             파라미터({@code arr, indice 중에 1개라도})가 {@code null}인 경우 발생.
     * 
     * @since 2023. 8. 2.
     * @version 2.0.0
     * 
     */
    public static int[] copy(int[] arr, int... indice) throws ArrayIndexOutOfBoundsException {
        ObjectUtils.requireNonNulls(arr, indice);

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
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2023. 8. 2.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param arr
     *            복사할 배열
     * @param indice
     *            복사할 데이터의 index.
     * @return
     * @throws ArrayIndexOutOfBoundsException
     * @throws NullPointerException
     *             파라미터({@code arr, indice 중에 1개라도})가 {@code null}인 경우 발생.
     * 
     * @since 2023. 8. 2.
     * @version 2.0.0
     * 
     */
    public static long[] copy(long[] arr, int... indice) throws ArrayIndexOutOfBoundsException {
        ObjectUtils.requireNonNulls(arr, indice);

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
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2023. 8. 2.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param arr
     *            복사할 배열
     * @param indice
     *            복사할 데이터의 index.
     * @return
     * @throws ArrayIndexOutOfBoundsException
     * @throws NullPointerException
     *             파라미터({@code arr, indice 중에 1개라도})가 {@code null}인 경우 발생.
     * 
     * @since 2023. 8. 2.
     * @version 2.0.0
     * 
     */
    public static short[] copy(short[] arr, int... indice) throws ArrayIndexOutOfBoundsException {
        ObjectUtils.requireNonNulls(arr, indice);

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
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2023. 8. 2.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param arr
     *            복사할 배열
     * @param indice
     *            복사할 데이터의 index.
     * @return
     * @throws ArrayIndexOutOfBoundsException
     * @throws NullPointerException
     *             파라미터({@code arr, indice 중에 1개라도})가 {@code null}인 경우 발생.
     * 
     * @since 2023. 8. 2.
     * @version 2.0.0
     * 
     */
    // apply to 'return newArr'
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static <T extends @Nullable Object> T[] copy(T[] arr, int... indice) throws ArrayIndexOutOfBoundsException {
        ObjectUtils.requireNonNulls(arr, indice);

        T[] newArr = (T[]) Array.newInstance(arr.getClass().getComponentType().getComponentType(), indice.length);

        for (int i = 0; i < indice.length; i++) {
            newArr[i] = arr[indice[i]];
        }

        return newArr;
    }

    /**
     * deep copyl <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 8. 19.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <T>
     * @param <TT>
     * @param original
     * @param newLength
     * @param newType
     * @param clone
     *            deep copy 함수
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code original, newType 중에 1개라도 또는 clone})가 {@code null}인 경우 발생.
     *
     * @since 2021. 8. 19.
     * @version 1.8.0
     * 
     */
    // apply to 'return copy'
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static <T extends @Nullable Object, U extends @Nullable Object> U[] copyOf(T[] original, int newLength, Class<? extends U[]> newType, Function<T, U> clone) {
        ObjectUtils.requireNonNulls(original, newType, clone);

        U[] copy = ((Object) newType == (Object) Object[].class) //
                ? (U[]) new Object[newLength] //
                : (U[]) Array.newInstance(newType.getComponentType(), newLength);

        final int newArrlen = Math.min(original.length, newLength);
        for (int i = 0; i < newArrlen; i++) {
            copy[i] = clone.apply(original[i]);
        }

        return copy;
    }

    /**
     * deep copy <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 8. 19.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <T>
     * @param original
     * @param newLength
     * @param clone
     *            deep copy 함수
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code original})가 {@code null}인 경우 발생.
     *
     * @since 2021. 8. 19.
     * @version 1.8.0
     * 
     */
    public static <T extends @Nullable Object> T[] copyOf(T[] original, int newLength, Function<T, T> clone) {
        Objects.requireNonNull(original);

        return copyOf(original, newLength, (Class<T[]>) original.getClass(), clone);
    }

    /**
     * deep copy. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 8. 19.     parkjunhong77@gmail.com         최초 작성
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
     * @throws NullPointerException
     *             파라미터({@code original, newType 중에 1개라도 또는 clone})가 {@code null}인 경우 발생.
     *
     * @since 2021. 8. 19.
     * @version 1.8.0
     * 
     */
    // apply to 'return copy'
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static <T extends @Nullable Object, U extends @Nullable Object> U[] copyOfRange(T[] original, int from, int to, Class<? extends U[]> newType, Function<T, U> clone) {
        ObjectUtils.requireNonNulls(original, newType, clone);

        int newLength = to - from;
        if (newLength < 0)
            throw new IllegalArgumentException(from + " > " + to);
        U[] copy = ((Object) newType == (Object) Object[].class) //
                ? (U[]) new Object[newLength] //
                : (U[]) Array.newInstance(newType.getComponentType(), newLength);

        final int newArrlen = Math.min(original.length, newLength);
        for (int i = 0; i < newArrlen; i++) {
            copy[i] = clone.apply(original[from + i]);
        }

        return copy;
    }

    /**
     * deep copy <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 8. 19.     parkjunhong77@gmail.com         최초 작성
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
     * @throws NullPointerException
     *             파라미터({@code original})가 {@code null}인 경우 발생.
     *
     * @since 2021. 8. 19.
     * @version 1.8.0
     * 
     */
    public static <T extends @Nullable Object> T[] copyOfRange(T[] original, int from, int to, Function<T, T> clone) {
        Objects.requireNonNull(original);

        return copyOfRange(original, from, to, (Class<T[]>) original.getClass(), clone);
    }

    /**
     * 배열에 주어진 값이 포함되어 있는 개수를 반환합니다.
     * 
     * @param array
     * @param value
     * @return
     * @since 2012. 03. 27.
     * 
     */
    public static int countOf(boolean @Nullable [] array, boolean value) {

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
     * @return
     * @since 2012. 03. 27.
     * 
     */
    public static int countOf(byte @Nullable [] array, byte value) {

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
     * @return
     * @since 2012. 03. 27.
     * 
     */
    public static int countOf(char @Nullable [] array, char value) {

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
     * @return
     * @since 2012. 03. 27.
     * 
     */
    public static int countOf(double @Nullable [] array, double value) {

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
     * @return
     * @since 2012. 03. 27.
     * 
     */
    public static int countOf(float @Nullable [] array, float value) {

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
     * @return
     * @since 2012. 03. 27.
     * 
     */
    public static int countOf(int @Nullable [] array, int value) {

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
     * @return
     * @since 2012. 03. 27.
     * 
     */
    public static int countOf(long @Nullable [] array, long value) {

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
     * @return
     * @since 2012. 03. 27.
     * 
     */
    public static int countOf(short @Nullable [] array, short value) {

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
     * @return
     * @since 2012. 03. 27.
     * 
     */
    public static <T extends @Nullable Object> int countOf(T @Nullable [] array, T value) {
        return ArrayUtils.<T> countOf(array, value, null);
    }

    /**
     * 배열에 주어진 값이 포함되어 있는 개수를 반환합니다.
     * 
     * @param array
     * @param value
     * @return
     * @since 2012. 03. 27.
     * 
     */
    public static <T extends @Nullable Object> int countOf(T @Nullable [] array, T value, @Nullable IEquivalent<T> equivalent) {

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

    /**
     * 
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     */
    // 아래 내용에 적용됨.
    // - sb.toString()
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static <T extends @Nullable Object> String elementString(T[] array, String delimiter) {
        Objects.requireNonNull(array);

        StringBuilder sb = new StringBuilder();
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
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 8. 15.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <T>
     * @param array
     * @return
     *
     * @since 2021. 8. 15.
     * @version 1.8.0
     * 
     */
    public static <T extends @Nullable Object> @Nullable List<Entry<Integer, T>> entrySet(T @Nullable [] array) {
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
     * 
     * @since 2012. 03. 14.
     * 
     */
    public static <T extends @Nullable Object> boolean equals(T @Nullable [] array1, T @Nullable [] array2) {
        return ArrayUtils.<T> equals(array1, array2, null);

    }

    /**
     * 2개의 배열이 동일한지 여부를 확인합니다.
     * 
     * 예제:
     * 
     * <pre>
     * public static void main(String[] args) {
     *      int[] intArr1 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 };
     *      int[] intArr2 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 };
     *      int[] intArr3 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 };
     *      int[] intArr4 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 };
     *      int[] intArr5 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 };
     *      int[] intArr6 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 };
     *      int[] intArr7 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 };
     *      int[] intArr8 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 };
     *      int[] intArr9 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 };
     *      int[] intArr0 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 };
     * 
     *      int[][] intArrAll1 = { intArr1, intArr2, intArr3, intArr4, intArr5, intArr6, intArr7, intArr8, intArr9, intArr0 };
     * 
     *      for( int i = 0; i < intArrAll1.length - 1; i++ ) {
     *          System.out.println(Arrays.equals(intArrAll1[i], intArrAll1[i + 1]) + "\narray1: "
     *                  + Arrays.toString(intArrAll1[i]) + "\narray2: " + Arrays.toString(intArrAll1[i + 1]) + "\n---");
     *      }
     * 
     *      int[][] intArr11 = { intArr1, intArr6 };
     *      int[][] intArr22 = { intArr2, intArr7 };
     *      int[][] intArr33 = { intArr3, intArr8 };
     *      int[][] intArr44 = { intArr4, intArr9 };
     *      int[][] intArr55 = { intArr5, intArr0 };
     * 
     *      int[][][] intArrAll2 = { intArr11, intArr22, intArr33, intArr44, intArr55 };
     *      for( int i = 0; i < intArrAll2.length - 1; i++ ) {
     *          System.out.println(ArrayUtils.equals(intArrAll2[i], intArrAll2[i + 1],
     *                  PrimitiveTypeArrayEquivFactory.intEquiv())
     *                  + "\narray1: "
     *                  + Arrays.toString(intArrAll2[i])
     *                  + "\narray2: "
     *                  + Arrays.toString(intArrAll2[i + 1])
     *                  + "\n---");
     *      }
     * 
     *      int[][][] intArr111 = { intArr11, intArr22 };
     *      int[][][] intArr222 = { intArr33, intArr44 };
     * 
     *      IEquivalent<int[][]> eq = new DefaultEquivalent<int[][]>() {
     *          public boolean equals(int[][] t1, int[][] t2) {
     *              return ArrayUtils.equals(t1, t2, PrimitiveTypeArrayEquivFactory.intEquiv());
     *          }
     *      };
     * 
     *      System.out.println(ArrayUtils.equals(intArr111, intArr222));
     *      
     *      System.out.println(ArrayUtils.equals(intArr111, intArr222, eq));
     *      
     *  }
     *  
     *  결과:
     *      
     *      true
     *      array1: [1, 2, 3, 4, 5, 6, 7, 8, 9, 0]
     *      array2: [1, 2, 3, 4, 5, 6, 7, 8, 9, 0]
     *      ---
     *      true
     *      array1: [1, 2, 3, 4, 5, 6, 7, 8, 9, 0]
     *      array2: [1, 2, 3, 4, 5, 6, 7, 8, 9, 0]
     *      ---
     *      true
     *      array1: [1, 2, 3, 4, 5, 6, 7, 8, 9, 0]
     *      array2: [1, 2, 3, 4, 5, 6, 7, 8, 9, 0]
     *      ---
     *      true
     *      array1: [1, 2, 3, 4, 5, 6, 7, 8, 9, 0]
     *      array2: [1, 2, 3, 4, 5, 6, 7, 8, 9, 0]
     *      ---
     *      true
     *      array1: [1, 2, 3, 4, 5, 6, 7, 8, 9, 0]
     *      array2: [1, 2, 3, 4, 5, 6, 7, 8, 9, 0]
     *      ---
     *      true
     *      array1: [1, 2, 3, 4, 5, 6, 7, 8, 9, 0]
     *      array2: [1, 2, 3, 4, 5, 6, 7, 8, 9, 0]
     *      ---
     *      true
     *      array1: [1, 2, 3, 4, 5, 6, 7, 8, 9, 0]
     *      array2: [1, 2, 3, 4, 5, 6, 7, 8, 9, 0]
     *      ---
     *      true
     *      array1: [1, 2, 3, 4, 5, 6, 7, 8, 9, 0]
     *      array2: [1, 2, 3, 4, 5, 6, 7, 8, 9, 0]
     *      ---
     *      true
     *      array1: [1, 2, 3, 4, 5, 6, 7, 8, 9, 0]
     *      array2: [1, 2, 3, 4, 5, 6, 7, 8, 9, 0]
     *      ---
     *      true
     *      array1: [[I@173a10f, [I@530daa]
     *      array2: [[I@a62fc3, [I@89ae9e]
     *      ---
     *      true
     *      array1: [[I@a62fc3, [I@89ae9e]
     *      array2: [[I@1270b73, [I@60aeb0]
     *      ---
     *      true
     *      array1: [[I@1270b73, [I@60aeb0]
     *      array2: [[I@16caf43, [I@66848c]
     *      ---
     *      true
     *      array1: [[I@16caf43, [I@66848c]
     *      array2: [[I@8813f2, [I@1d58aae]
     *      ---
     *      false
     *      true
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
     * 
     * @since 2012. 03. 14.
     * 
     */
    public static <T extends @Nullable Object> boolean equals(T @Nullable [] array1, T @Nullable [] array2, @Nullable IEquivalent<T> equivalent) {
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
     * 
     * @throws NullPointerException
     *             파라미터({@code bytes})가 {@code null}이거나 {@code null}을 포함한 경우 발생.
     * 
     * 
     * @see ArrayIndexOutOfBoundsException
     */
    // 아래 내용에 적용됨.
    // - ObjectUtils.requireNonNulls((Object[]) bytes);
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static byte[] getDecElements(byte[][] bytes) {
        ObjectUtils.requireNonNulls((Object[]) bytes);

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
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 10. 26.        parkjunhong77@gmail.com         최초 작성
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
     * 
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
     * 
     * @throws NullPointerException
     *             파라미터({@code bytes})가 {@code null}이거나 {@code null}을 포함한 경우 발생.
     * 
     * @see ArrayIndexOutOfBoundsException
     */
    // 아래 내용에 적용됨.
    // - ObjectUtils.requireNonNulls((Object[]) bytes);
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static byte[] getIncElements(byte[][] bytes) {
        ObjectUtils.requireNonNulls((Object[]) bytes);

        byte[] rtnBytes = new byte[bytes.length];

        for (int i = 0; i < bytes.length; i++) {
            rtnBytes[i] = bytes[i][i];
        }

        return rtnBytes;
    }

    /**
     * 배열에서 찾고자하는 데이터의 인덱스를 반환합니다.
     * 
     * @param objects
     * @param obj
     * 
     * @throws NullPointerException
     *             파라미터({@code objs})가 {@code null}인 경우 발생.
     * 
     * @return 데이터의 인덱스, 없는 경우 -1.
     */
    public static int getIndex(Object[] objects, Object obj) {
        Objects.requireNonNull(objects);

        int rtnValue = -1;

        for (int i = 0; i < objects.length; i++) {
            if (objects[i] == obj // check for primitive-type
                    || objects[i].equals(obj) // check for referenced-type
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
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 10. 26.        parkjunhong77@gmail.com         최초 작성
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
     * 
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
     * @throws NullPointerException
     *             파라미터({@code bytes})가 {@code null}이거나 {@code null}을 포함한 경우 발생.
     * 
     * @see ArrayIndexOutOfBoundsException
     */
    // 아래 내용에 적용됨.
    // - ObjectUtils.requireNonNulls((Object[]) bytes);
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static byte[] getPlainElements(byte[][] bytes, int index) {
        ObjectUtils.requireNonNulls((Object[]) bytes);

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
     * 2021. 6. 21.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <T>
     * @param array
     * @param c
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code array, c 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2021. 6. 21.
     * @version 1.8.0
     * 
     */
    public static int indexOf(boolean[] array, Function<Boolean, Boolean> c) {
        ObjectUtils.requireNonNulls(array, c);

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
     * 2021. 6. 21.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <T>
     * @param array
     * @param c
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code array, c 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2021. 6. 21.
     * @version 1.8.0
     * 
     */
    public static int indexOf(byte[] array, Function<Byte, Boolean> c) {
        ObjectUtils.requireNonNulls(array, c);

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
     * 2021. 6. 21.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <T>
     * @param array
     * @param c
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code array, c 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2021. 6. 21.
     * @version 1.8.0
     * 
     */
    public static int indexOf(char[] array, Function<Character, Boolean> c) {
        ObjectUtils.requireNonNulls(array, c);

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
     * 2021. 6. 21.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <T>
     * @param array
     * @param c
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code array, c 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2021. 6. 21.
     * @version 1.8.0
     * 
     */
    public static int indexOf(double[] array, Function<Double, Boolean> c) {
        ObjectUtils.requireNonNulls(array, c);

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
     * 2021. 6. 21.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <T>
     * @param array
     * @param c
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code array, c 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2021. 6. 21.
     * @version 1.8.0
     * 
     */
    public static int indexOf(float[] array, Function<Float, Boolean> c) {
        ObjectUtils.requireNonNulls(array, c);

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
     * 2021. 6. 21.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <T>
     * @param array
     * @param c
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code array, c 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2021. 6. 21.
     * @version 1.8.0
     * 
     */
    public static int indexOf(int[] array, Function<Integer, Boolean> c) {
        ObjectUtils.requireNonNulls(array, c);

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
     * 2021. 6. 21.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <T>
     * @param array
     * @param c
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code array, c 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2021. 6. 21.
     * @version 1.8.0
     * 
     */
    public static int indexOf(long[] array, Function<Long, Boolean> c) {
        ObjectUtils.requireNonNulls(array, c);

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
     * 2021. 6. 21.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <T>
     * @param array
     * @param c
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code array, c 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2021. 6. 21.
     * @version 1.8.0
     * 
     */
    public static int indexOf(short[] array, Function<Short, Boolean> c) {
        ObjectUtils.requireNonNulls(array, c);

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
     * 2021. 6. 21.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <T>
     * @param array
     * @param c
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code array, c 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2021. 6. 21.
     * @version 1.8.0
     * 
     */
    public static <T extends @Nullable Object> int indexOf(T[] array, Function<T, Boolean> c) {
        ObjectUtils.requireNonNulls(array, c);

        for (int i = 0; i < array.length; i++) {
            if (c.apply(array[i])) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 주어진 배열에서 찾고자 하는 값이 존재하는 인덱스 배열을 반환합니다.<br>
     * 배열이 {@code null}인 경우 {@code null}을 반환합니다.
     * 
     * @param array
     * @param value
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 03. 12.
     * 
     */
    // 아래 내용에 적용됨.
    // - Arrays.copyOf(indice, count)
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static int[] indiceOf(boolean[] array, boolean value) {
        Objects.requireNonNull(array);

        int[] indice = new int[array.length];

        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                indice[count++] = i;
            }
        }

        if (count > 0) {
            return Arrays.copyOf(indice, count);
        } else {
            return new int[0];
        }
    }

    /**
     * 주어진 배열에서 찾고자 하는 배열들의 위치 정보를 반환합니다.<br>
     * 반환되는 배열의 길이는 0을 포함한 짝수이며, (홀/짝) 쌍을 이루는 값이 (시작/끝)의 정보를 표현합니다.<br>
     * <b>참고:</b> 검색된 배열이 겹칠 경우 중복해서 찾지 않습니다. (Non-overlapping 매칭)
     * 
     * <pre>
     * [개정이력]
     * 날짜       | 작성자   |   내용
     * ------------------------------------------
     * 2012. 03. 30.    parkjunhong77@gmail.com         최초 작성
     * 2026. 03. 25.    parkjunhong77@gmail.com         무한 루프 방지 및 예외 처리 개선
     * </pre>
     * 
     * @param array
     *            대상 배열
     * @param values
     *            찾고자 하는 배열 (길이가 1 이상이어야 함)
     * @return 찾은 위치의 시작과 끝 인덱스가 쌍으로 담긴 배열 (찾지 못한 경우 길이가 0인 배열)
     * 
     * @throws NullPointerException
     *             파라미터({@code array} 또는 {@code values})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 03. 30.
     * @version 1.8.0
     */
    // 아래 내용에 적용됨.
    // - Arrays.copyOf(indice, indicePos)
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static int[] indiceOf(boolean[] array, boolean[] values) {
        ObjectUtils.requireNonNulls(array, values);

        // 1. [버그 수정] 빈 배열 예외(ArrayIndexOutOfBoundsException) 사전 차단 및 길이 검증
        if (values.length == 0 || array.length < values.length) {
            return new int[0];
        }

        // 최악의 경우(전부 매칭)를 가정한 크기 할당
        int[] indice = new int[(array.length / values.length) * 2];
        int indicePos = 0;

        IEquivalent<boolean[]> equi = EquivalentFactory.booleanArrayEquiv();

        int arrayLength = array.length;
        int targetLength = values.length;

        // 찾고자 하는 첫 번째 데이터 (values 길이가 1 이상임이 보장됨)
        final boolean tfc = values[0];

        int arrayPos = 0;

        // 2. [최적화] 복잡한 remLength 계산 대신 인덱스 한계치로 루프 조건 단순화
        while (arrayPos <= arrayLength - targetLength) {

            // 3. [유지] 원시 타입(byte)이므로 객체 비교가 아닌 == 연산자 사용이 안전하고 빠름
            if (array[arrayPos] == tfc) {

                // 첫 번째 요소가 일치하므로, 전체 부분 배열 추출 후 상세 비교 진행
                if (equi.equals(Arrays.copyOfRange(array, arrayPos, arrayPos + targetLength), values)) {
                    indice[indicePos++] = arrayPos;
                    indice[indicePos++] = arrayPos + targetLength - 1;

                    // 매칭 성공 시 중복(Overlap) 탐색을 피하기 위해 타겟 길이만큼 인덱스 점프
                    arrayPos += targetLength;
                } else {
                    // 4. [치명적 버그 수정] 전체 비교 실패 시 다음 인덱스로 정상 이동 (무한 루프 방지)
                    arrayPos++;
                }
            } else {
                arrayPos++;
            }
        }

        return Arrays.copyOf(indice, indicePos);
    }

    /**
     * 주어진 배열에서 찾고자 하는 값이 존재하는 인덱스 배열을 반환합니다.<br>
     * 배열이 {@code null}인 경우 {@code null}을 반환합니다.
     * 
     * @param array
     * @param value
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 03. 12.
     * 
     */
    // 아래 내용에 적용됨.
    // - Arrays.copyOf(indice, count)
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static int[] indiceOf(byte[] array, byte value) {
        Objects.requireNonNull(array);
        int[] indice = new int[array.length];

        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                indice[count++] = i;
            }
        }

        if (count > 0) {
            return Arrays.copyOf(indice, count);
        } else {
            return new int[0];
        }
    }

    /**
     * 주어진 배열에서 찾고자 하는 배열들의 위치 정보를 반환합니다.<br>
     * 반환되는 배열의 길이는 0을 포함한 짝수이며, (홀/짝) 쌍을 이루는 값이 (시작/끝)의 정보를 표현합니다.<br>
     * <b>참고:</b> 검색된 배열이 겹칠 경우 중복해서 찾지 않습니다. (Non-overlapping 매칭)
     * 
     * <pre>
     * [개정이력]
     * 날짜       | 작성자   |   내용
     * ------------------------------------------
     * 2012. 03. 30.    parkjunhong77@gmail.com         최초 작성
     * 2026. 03. 25.    parkjunhong77@gmail.com         무한 루프 방지 및 예외 처리 개선
     * </pre>
     * 
     * @param array
     *            대상 배열
     * @param values
     *            찾고자 하는 배열 (길이가 1 이상이어야 함)
     * @return 찾은 위치의 시작과 끝 인덱스가 쌍으로 담긴 배열 (찾지 못한 경우 길이가 0인 배열)
     * 
     * @throws NullPointerException
     *             파라미터({@code array} 또는 {@code values})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 03. 30.
     * @version 1.8.0
     */
    // 아래 내용에 적용됨.
    // - Arrays.copyOf(indice, indicePos)
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static int[] indiceOf(byte[] array, byte[] values) {
        ObjectUtils.requireNonNulls(array, values);

        // 1. [버그 수정] 빈 배열 예외(ArrayIndexOutOfBoundsException) 사전 차단 및 길이 검증
        if (values.length == 0 || array.length < values.length) {
            return new int[0];
        }

        // 최악의 경우(전부 매칭)를 가정한 크기 할당
        int[] indice = new int[(array.length / values.length) * 2];
        int indicePos = 0;

        IEquivalent<byte[]> equi = EquivalentFactory.byteArrayEquiv();

        int arrayLength = array.length;
        int targetLength = values.length;

        // 찾고자 하는 첫 번째 데이터 (values 길이가 1 이상임이 보장됨)
        final byte tfc = values[0];

        int arrayPos = 0;

        // 2. [최적화] 복잡한 remLength 계산 대신 인덱스 한계치로 루프 조건 단순화
        while (arrayPos <= arrayLength - targetLength) {

            // 3. [유지] 원시 타입(byte)이므로 객체 비교가 아닌 == 연산자 사용이 안전하고 빠름
            if (array[arrayPos] == tfc) {

                // 첫 번째 요소가 일치하므로, 전체 부분 배열 추출 후 상세 비교 진행
                if (equi.equals(Arrays.copyOfRange(array, arrayPos, arrayPos + targetLength), values)) {
                    indice[indicePos++] = arrayPos;
                    indice[indicePos++] = arrayPos + targetLength - 1;

                    // 매칭 성공 시 중복(Overlap) 탐색을 피하기 위해 타겟 길이만큼 인덱스 점프
                    arrayPos += targetLength;
                } else {
                    // 4. [치명적 버그 수정] 전체 비교 실패 시 다음 인덱스로 정상 이동 (무한 루프 방지)
                    arrayPos++;
                }
            } else {
                arrayPos++;
            }
        }

        return Arrays.copyOf(indice, indicePos);
    }

    /**
     * 주어진 배열에서 찾고자 하는 값이 존재하는 인덱스 배열을 반환합니다.<br>
     * 배열이 {@code null}인 경우 {@code null}을 반환합니다.
     * 
     * @param array
     * @param value
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 03. 12.
     * 
     */
    // 아래 내용에 적용됨.
    // - Arrays.copyOf(indice, count)
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static int[] indiceOf(char[] array, char value) {
        Objects.requireNonNull(array);

        int[] indice = new int[array.length];

        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                indice[count++] = i;
            }
        }

        if (count > 0) {
            return Arrays.copyOf(indice, count);
        } else {
            return new int[0];
        }
    }

    /**
     * 주어진 배열에서 찾고자 하는 배열들의 위치 정보를 반환합니다.<br>
     * 반환되는 배열의 길이는 0을 포함한 짝수이며, (홀/짝) 쌍을 이루는 값이 (시작/끝)의 정보를 표현합니다.<br>
     * <b>참고:</b> 검색된 배열이 겹칠 경우 중복해서 찾지 않습니다. (Non-overlapping 매칭)
     * 
     * <pre>
     * [개정이력]
     * 날짜       | 작성자   |   내용
     * ------------------------------------------
     * 2012. 03. 30.    parkjunhong77@gmail.com         최초 작성
     * 2026. 03. 25.    parkjunhong77@gmail.com         무한 루프 방지 및 예외 처리 개선
     * </pre>
     * 
     * @param array
     *            대상 배열
     * @param values
     *            찾고자 하는 배열 (길이가 1 이상이어야 함)
     * @return 찾은 위치의 시작과 끝 인덱스가 쌍으로 담긴 배열 (찾지 못한 경우 길이가 0인 배열)
     * 
     * @throws NullPointerException
     *             파라미터({@code array} 또는 {@code values})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 03. 30.
     * @version 1.8.0
     */
    // 아래 내용에 적용됨.
    // - Arrays.copyOf(indice, indicePos)
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static int[] indiceOf(char[] array, char[] values) {
        ObjectUtils.requireNonNulls(array, values);

        // 1. [버그 수정] 빈 배열 예외(ArrayIndexOutOfBoundsException) 사전 차단 및 길이 검증
        if (values.length == 0 || array.length < values.length) {
            return new int[0];
        }

        // 최악의 경우(전부 매칭)를 가정한 크기 할당
        int[] indice = new int[(array.length / values.length) * 2];
        int indicePos = 0;

        IEquivalent<char[]> equi = EquivalentFactory.charArrayEquiv();

        int arrayLength = array.length;
        int targetLength = values.length;

        // 찾고자 하는 첫 번째 데이터 (values 길이가 1 이상임이 보장됨)
        final char tfc = values[0];

        int arrayPos = 0;

        // 2. [최적화] 복잡한 remLength 계산 대신 인덱스 한계치로 루프 조건 단순화
        while (arrayPos <= arrayLength - targetLength) {

            // 3. [유지] 원시 타입(byte)이므로 객체 비교가 아닌 == 연산자 사용이 안전하고 빠름
            if (array[arrayPos] == tfc) {

                // 첫 번째 요소가 일치하므로, 전체 부분 배열 추출 후 상세 비교 진행
                if (equi.equals(Arrays.copyOfRange(array, arrayPos, arrayPos + targetLength), values)) {
                    indice[indicePos++] = arrayPos;
                    indice[indicePos++] = arrayPos + targetLength - 1;

                    // 매칭 성공 시 중복(Overlap) 탐색을 피하기 위해 타겟 길이만큼 인덱스 점프
                    arrayPos += targetLength;
                } else {
                    // 4. [치명적 버그 수정] 전체 비교 실패 시 다음 인덱스로 정상 이동 (무한 루프 방지)
                    arrayPos++;
                }
            } else {
                arrayPos++;
            }
        }

        return Arrays.copyOf(indice, indicePos);
    }

    /**
     * 주어진 배열에서 찾고자 하는 값이 존재하는 인덱스 배열을 반환합니다.<br>
     * 배열이 {@code null}인 경우 {@code null}을 반환합니다.
     * 
     * @param array
     * @param value
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 03. 12.
     * 
     */
    // 아래 내용에 적용됨.
    // - Arrays.copyOf(indice, count)
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static int[] indiceOf(double[] array, double value) {
        Objects.requireNonNull(array);
        int[] indice = new int[array.length];

        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                indice[count++] = i;
            }
        }

        if (count > 0) {
            return Arrays.copyOf(indice, count);
        } else {
            return new int[0];
        }
    }

    /**
     * 주어진 배열에서 찾고자 하는 배열들의 위치 정보를 반환합니다.<br>
     * 반환되는 배열의 길이는 0을 포함한 짝수이며, (홀/짝) 쌍을 이루는 값이 (시작/끝)의 정보를 표현합니다.<br>
     * <b>참고:</b> 검색된 배열이 겹칠 경우 중복해서 찾지 않습니다. (Non-overlapping 매칭)
     * 
     * <pre>
     * [개정이력]
     * 날짜       | 작성자   |   내용
     * ------------------------------------------
     * 2012. 03. 30.    parkjunhong77@gmail.com         최초 작성
     * 2026. 03. 25.    parkjunhong77@gmail.com         무한 루프 방지 및 예외 처리 개선
     * </pre>
     * 
     * @param array
     *            대상 배열
     * @param values
     *            찾고자 하는 배열 (길이가 1 이상이어야 함)
     * @return 찾은 위치의 시작과 끝 인덱스가 쌍으로 담긴 배열 (찾지 못한 경우 길이가 0인 배열)
     * 
     * @throws NullPointerException
     *             파라미터({@code array} 또는 {@code values})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 03. 30.
     * @version 1.8.0
     */
    // 아래 내용에 적용됨.
    // - Arrays.copyOf(indice, indicePos)
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static int[] indiceOf(double[] array, double[] values) {
        ObjectUtils.requireNonNulls(array, values);

        // 1. [버그 수정] 빈 배열 예외(ArrayIndexOutOfBoundsException) 사전 차단 및 길이 검증
        if (values.length == 0 || array.length < values.length) {
            return new int[0];
        }

        // 최악의 경우(전부 매칭)를 가정한 크기 할당
        int[] indice = new int[(array.length / values.length) * 2];
        int indicePos = 0;

        IEquivalent<double[]> equi = EquivalentFactory.doubleArrayEquiv();

        int arrayLength = array.length;
        int targetLength = values.length;

        // 찾고자 하는 첫 번째 데이터 (values 길이가 1 이상임이 보장됨)
        final double tfc = values[0];

        int arrayPos = 0;

        // 2. [최적화] 복잡한 remLength 계산 대신 인덱스 한계치로 루프 조건 단순화
        while (arrayPos <= arrayLength - targetLength) {

            // 3. [유지] 원시 타입(byte)이므로 객체 비교가 아닌 == 연산자 사용이 안전하고 빠름
            if (array[arrayPos] == tfc) {

                // 첫 번째 요소가 일치하므로, 전체 부분 배열 추출 후 상세 비교 진행
                if (equi.equals(Arrays.copyOfRange(array, arrayPos, arrayPos + targetLength), values)) {
                    indice[indicePos++] = arrayPos;
                    indice[indicePos++] = arrayPos + targetLength - 1;

                    // 매칭 성공 시 중복(Overlap) 탐색을 피하기 위해 타겟 길이만큼 인덱스 점프
                    arrayPos += targetLength;
                } else {
                    // 4. [치명적 버그 수정] 전체 비교 실패 시 다음 인덱스로 정상 이동 (무한 루프 방지)
                    arrayPos++;
                }
            } else {
                arrayPos++;
            }
        }

        return Arrays.copyOf(indice, indicePos);
    }

    /**
     * 주어진 배열에서 찾고자 하는 값이 존재하는 인덱스 배열을 반환합니다.<br>
     * 배열이 {@code null}인 경우 {@code null}을 반환합니다.
     * 
     * @param array
     * @param value
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 03. 12.
     * 
     */
    // 아래 내용에 적용됨.
    // - Arrays.copyOf(indice, count)
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static int[] indiceOf(float[] array, float value) {
        Objects.requireNonNull(array);

        int[] indice = new int[array.length];

        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                indice[count++] = i;
            }
        }

        if (count > 0) {
            return Arrays.copyOf(indice, count);
        } else {
            return new int[0];
        }
    }

    /**
     * 주어진 배열에서 찾고자 하는 배열들의 위치 정보를 반환합니다.<br>
     * 반환되는 배열의 길이는 0을 포함한 짝수이며, (홀/짝) 쌍을 이루는 값이 (시작/끝)의 정보를 표현합니다.<br>
     * <b>참고:</b> 검색된 배열이 겹칠 경우 중복해서 찾지 않습니다. (Non-overlapping 매칭)
     * 
     * <pre>
     * [개정이력]
     * 날짜       | 작성자   |   내용
     * ------------------------------------------
     * 2012. 03. 30.    parkjunhong77@gmail.com         최초 작성
     * 2026. 03. 25.    parkjunhong77@gmail.com         무한 루프 방지 및 예외 처리 개선
     * </pre>
     * 
     * @param array
     *            대상 배열
     * @param values
     *            찾고자 하는 배열 (길이가 1 이상이어야 함)
     * @return 찾은 위치의 시작과 끝 인덱스가 쌍으로 담긴 배열 (찾지 못한 경우 길이가 0인 배열)
     * 
     * @throws NullPointerException
     *             파라미터({@code array} 또는 {@code values})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 03. 30.
     * @version 1.8.0
     */
    // 아래 내용에 적용됨.
    // - Arrays.copyOf(indice, indicePos)
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static int[] indiceOf(float[] array, float[] values) {
        ObjectUtils.requireNonNulls(array, values);

        // 1. [버그 수정] 빈 배열 예외(ArrayIndexOutOfBoundsException) 사전 차단 및 길이 검증
        if (values.length == 0 || array.length < values.length) {
            return new int[0];
        }

        // 최악의 경우(전부 매칭)를 가정한 크기 할당
        int[] indice = new int[(array.length / values.length) * 2];
        int indicePos = 0;

        IEquivalent<float[]> equi = EquivalentFactory.floatArrayEquiv();

        int arrayLength = array.length;
        int targetLength = values.length;

        // 찾고자 하는 첫 번째 데이터 (values 길이가 1 이상임이 보장됨)
        final float tfc = values[0];

        int arrayPos = 0;

        // 2. [최적화] 복잡한 remLength 계산 대신 인덱스 한계치로 루프 조건 단순화
        while (arrayPos <= arrayLength - targetLength) {

            // 3. [유지] 원시 타입(byte)이므로 객체 비교가 아닌 == 연산자 사용이 안전하고 빠름
            if (array[arrayPos] == tfc) {

                // 첫 번째 요소가 일치하므로, 전체 부분 배열 추출 후 상세 비교 진행
                if (equi.equals(Arrays.copyOfRange(array, arrayPos, arrayPos + targetLength), values)) {
                    indice[indicePos++] = arrayPos;
                    indice[indicePos++] = arrayPos + targetLength - 1;

                    // 매칭 성공 시 중복(Overlap) 탐색을 피하기 위해 타겟 길이만큼 인덱스 점프
                    arrayPos += targetLength;
                } else {
                    // 4. [치명적 버그 수정] 전체 비교 실패 시 다음 인덱스로 정상 이동 (무한 루프 방지)
                    arrayPos++;
                }
            } else {
                arrayPos++;
            }
        }

        return Arrays.copyOf(indice, indicePos);
    }

    /**
     * 주어진 배열에서 찾고자 하는 값이 존재하는 인덱스 배열을 반환합니다.<br>
     * 배열이 {@code null}인 경우 {@code null}을 반환합니다.
     * 
     * @param array
     * @param value
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 03. 12.
     * 
     */
    // 아래 내용에 적용됨.
    // - Arrays.copyOf(indice, count)
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static int[] indiceOf(int[] array, int value) {
        Objects.requireNonNull(array);

        int[] indice = new int[array.length];

        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                indice[count++] = i;
            }
        }

        if (count > 0) {
            return Arrays.copyOf(indice, count);
        } else {
            return new int[0];
        }
    }

    /**
     * 주어진 배열에서 찾고자 하는 배열들의 위치 정보를 반환합니다.<br>
     * 반환되는 배열의 길이는 0을 포함한 짝수이며, (홀/짝) 쌍을 이루는 값이 (시작/끝)의 정보를 표현합니다.<br>
     * <b>참고:</b> 검색된 배열이 겹칠 경우 중복해서 찾지 않습니다. (Non-overlapping 매칭)
     * 
     * <pre>
     * [개정이력]
     * 날짜       | 작성자   |   내용
     * ------------------------------------------
     * 2012. 03. 30.    parkjunhong77@gmail.com         최초 작성
     * 2026. 03. 25.    parkjunhong77@gmail.com         무한 루프 방지 및 예외 처리 개선
     * </pre>
     * 
     * @param array
     *            대상 배열
     * @param values
     *            찾고자 하는 배열 (길이가 1 이상이어야 함)
     * @return 찾은 위치의 시작과 끝 인덱스가 쌍으로 담긴 배열 (찾지 못한 경우 길이가 0인 배열)
     * 
     * @throws NullPointerException
     *             파라미터({@code array} 또는 {@code values})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 03. 30.
     * @version 1.8.0
     */
    // 아래 내용에 적용됨.
    // - Arrays.copyOf(indice, indicePos)
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static int[] indiceOf(int[] array, int[] values) {
        ObjectUtils.requireNonNulls(array, values);

        // 1. [버그 수정] 빈 배열 예외(ArrayIndexOutOfBoundsException) 사전 차단 및 길이 검증
        if (values.length == 0 || array.length < values.length) {
            return new int[0];
        }

        // 최악의 경우(전부 매칭)를 가정한 크기 할당
        int[] indice = new int[(array.length / values.length) * 2];
        int indicePos = 0;

        IEquivalent<int[]> equi = EquivalentFactory.intArrayEquiv();

        int arrayLength = array.length;
        int targetLength = values.length;

        // 찾고자 하는 첫 번째 데이터 (values 길이가 1 이상임이 보장됨)
        final int tfc = values[0];

        int arrayPos = 0;

        // 2. [최적화] 복잡한 remLength 계산 대신 인덱스 한계치로 루프 조건 단순화
        while (arrayPos <= arrayLength - targetLength) {

            // 3. [유지] 원시 타입(byte)이므로 객체 비교가 아닌 == 연산자 사용이 안전하고 빠름
            if (array[arrayPos] == tfc) {

                // 첫 번째 요소가 일치하므로, 전체 부분 배열 추출 후 상세 비교 진행
                if (equi.equals(Arrays.copyOfRange(array, arrayPos, arrayPos + targetLength), values)) {
                    indice[indicePos++] = arrayPos;
                    indice[indicePos++] = arrayPos + targetLength - 1;

                    // 매칭 성공 시 중복(Overlap) 탐색을 피하기 위해 타겟 길이만큼 인덱스 점프
                    arrayPos += targetLength;
                } else {
                    // 4. [치명적 버그 수정] 전체 비교 실패 시 다음 인덱스로 정상 이동 (무한 루프 방지)
                    arrayPos++;
                }
            } else {
                arrayPos++;
            }
        }

        return Arrays.copyOf(indice, indicePos);
    }

    /**
     * 주어진 배열에서 찾고자 하는 값이 존재하는 인덱스 배열을 반환합니다.<br>
     * 배열이 {@code null}인 경우 {@code null}을 반환합니다.
     * 
     * @param array
     * @param value
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 03. 12.
     * 
     */
    // 아래 내용에 적용됨.
    // - Arrays.copyOf(indice, count)
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static int[] indiceOf(long[] array, long value) {
        int[] indice = new int[array.length];

        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                indice[count++] = i;
            }
        }

        if (count > 0) {
            return Arrays.copyOf(indice, count);
        } else {
            return new int[0];
        }
    }

    /**
     * 주어진 배열에서 찾고자 하는 배열들의 위치 정보를 반환합니다.<br>
     * 반환되는 배열의 길이는 0을 포함한 짝수이며, (홀/짝) 쌍을 이루는 값이 (시작/끝)의 정보를 표현합니다.<br>
     * <b>참고:</b> 검색된 배열이 겹칠 경우 중복해서 찾지 않습니다. (Non-overlapping 매칭)
     * 
     * <pre>
     * [개정이력]
     * 날짜       | 작성자   |   내용
     * ------------------------------------------
     * 2012. 03. 30.    parkjunhong77@gmail.com         최초 작성
     * 2026. 03. 25.    parkjunhong77@gmail.com         무한 루프 방지 및 예외 처리 개선
     * </pre>
     * 
     * @param array
     *            대상 배열
     * @param values
     *            찾고자 하는 배열 (길이가 1 이상이어야 함)
     * @return 찾은 위치의 시작과 끝 인덱스가 쌍으로 담긴 배열 (찾지 못한 경우 길이가 0인 배열)
     * 
     * @throws NullPointerException
     *             파라미터({@code array} 또는 {@code values})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 03. 30.
     * @version 1.8.0
     */
    // 아래 내용에 적용됨.
    // - Arrays.copyOf(indice, indicePos)
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static int[] indiceOf(long[] array, long[] values) {
        ObjectUtils.requireNonNulls(array, values);

        // 1. [버그 수정] 빈 배열 예외(ArrayIndexOutOfBoundsException) 사전 차단 및 길이 검증
        if (values.length == 0 || array.length < values.length) {
            return new int[0];
        }

        // 최악의 경우(전부 매칭)를 가정한 크기 할당
        int[] indice = new int[(array.length / values.length) * 2];
        int indicePos = 0;

        IEquivalent<long[]> equi = EquivalentFactory.longArrayEquiv();

        int arrayLength = array.length;
        int targetLength = values.length;

        // 찾고자 하는 첫 번째 데이터 (values 길이가 1 이상임이 보장됨)
        final long tfc = values[0];

        int arrayPos = 0;

        // 2. [최적화] 복잡한 remLength 계산 대신 인덱스 한계치로 루프 조건 단순화
        while (arrayPos <= arrayLength - targetLength) {

            // 3. [유지] 원시 타입(byte)이므로 객체 비교가 아닌 == 연산자 사용이 안전하고 빠름
            if (array[arrayPos] == tfc) {

                // 첫 번째 요소가 일치하므로, 전체 부분 배열 추출 후 상세 비교 진행
                if (equi.equals(Arrays.copyOfRange(array, arrayPos, arrayPos + targetLength), values)) {
                    indice[indicePos++] = arrayPos;
                    indice[indicePos++] = arrayPos + targetLength - 1;

                    // 매칭 성공 시 중복(Overlap) 탐색을 피하기 위해 타겟 길이만큼 인덱스 점프
                    arrayPos += targetLength;
                } else {
                    // 4. [치명적 버그 수정] 전체 비교 실패 시 다음 인덱스로 정상 이동 (무한 루프 방지)
                    arrayPos++;
                }
            } else {
                arrayPos++;
            }
        }

        return Arrays.copyOf(indice, indicePos);
    }

    /**
     * 주어진 배열에서 찾고자 하는 값이 존재하는 인덱스 배열을 반환합니다.<br>
     * 배열이 {@code null}인 경우 {@code null}을 반환합니다.
     * 
     * @param array
     * @param value
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 03. 12.
     * 
     */
    // 아래 내용에 적용됨.
    // - Arrays.copyOf(indice, count)
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static int[] indiceOf(short[] array, short value) {
        int[] indice = new int[array.length];

        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                indice[count++] = i;
            }
        }

        if (count > 0) {
            return Arrays.copyOf(indice, count);
        } else {
            return new int[0];
        }
    }

    /**
     * 주어진 배열에서 찾고자 하는 배열들의 위치 정보를 반환합니다.<br>
     * 반환되는 배열의 길이는 0을 포함한 짝수이며, (홀/짝) 쌍을 이루는 값이 (시작/끝)의 정보를 표현합니다.<br>
     * <b>참고:</b> 검색된 배열이 겹칠 경우 중복해서 찾지 않습니다. (Non-overlapping 매칭)
     * 
     * <pre>
     * [개정이력]
     * 날짜       | 작성자   |   내용
     * ------------------------------------------
     * 2012. 03. 30.    parkjunhong77@gmail.com         최초 작성
     * 2026. 03. 25.    parkjunhong77@gmail.com         무한 루프 방지 및 예외 처리 개선
     * </pre>
     * 
     * @param array
     *            대상 배열
     * @param values
     *            찾고자 하는 배열 (길이가 1 이상이어야 함)
     * @return 찾은 위치의 시작과 끝 인덱스가 쌍으로 담긴 배열 (찾지 못한 경우 길이가 0인 배열)
     * 
     * @throws NullPointerException
     *             파라미터({@code array} 또는 {@code values})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 03. 30.
     * @version 1.8.0
     */
    // 아래 내용에 적용됨.
    // - Arrays.copyOf(indice, indicePos)
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static int[] indiceOf(short[] array, short[] values) {
        ObjectUtils.requireNonNulls(array, values);

        // 1. [버그 수정] 빈 배열 예외(ArrayIndexOutOfBoundsException) 사전 차단 및 길이 검증
        if (values.length == 0 || array.length < values.length) {
            return new int[0];
        }

        // 최악의 경우(전부 매칭)를 가정한 크기 할당
        int[] indice = new int[(array.length / values.length) * 2];
        int indicePos = 0;

        IEquivalent<short[]> equi = EquivalentFactory.shortArrayEquiv();

        int arrayLength = array.length;
        int targetLength = values.length;

        // 찾고자 하는 첫 번째 데이터 (values 길이가 1 이상임이 보장됨)
        final short tfc = values[0];

        int arrayPos = 0;

        // 2. [최적화] 복잡한 remLength 계산 대신 인덱스 한계치로 루프 조건 단순화
        while (arrayPos <= arrayLength - targetLength) {

            // 3. [유지] 원시 타입(byte)이므로 객체 비교가 아닌 == 연산자 사용이 안전하고 빠름
            if (array[arrayPos] == tfc) {

                // 첫 번째 요소가 일치하므로, 전체 부분 배열 추출 후 상세 비교 진행
                if (equi.equals(Arrays.copyOfRange(array, arrayPos, arrayPos + targetLength), values)) {
                    indice[indicePos++] = arrayPos;
                    indice[indicePos++] = arrayPos + targetLength - 1;

                    // 매칭 성공 시 중복(Overlap) 탐색을 피하기 위해 타겟 길이만큼 인덱스 점프
                    arrayPos += targetLength;
                } else {
                    // 4. [치명적 버그 수정] 전체 비교 실패 시 다음 인덱스로 정상 이동 (무한 루프 방지)
                    arrayPos++;
                }
            } else {
                arrayPos++;
            }
        }

        return Arrays.copyOf(indice, indicePos);
    }

    /**
     * 주어진 배열에서 찾고자 하는 값이 존재하는 인덱스 배열을 반환합니다.<br>
     * 배열이 {@code null}인 경우 {@code null}을 반환합니다.
     * 
     * @param array
     * @param value
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 03. 12.
     * 
     */
    public static <T extends @Nullable Object> int[] indiceOf(T[] array, T value) {
        return ArrayUtils.<T> indiceOf(array, value, null);
    }

    /**
     * 주어진 배열에서 찾고자 하는 값이 존재하는 인덱스 배열을 반환합니다.<br>
     * 배열이 {@code null}인 경우 {@code null}을 반환합니다.
     * 
     * @param array
     * @param value
     * @param equivalent
     *            클래스 또는 타입 T의 equals(T obj) 메소드가 아닌 다른 비교 기준을 제공하는 객체
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 03. 12.
     * 
     */
    // 아래 내용에 적용됨.
    // - Arrays.copyOf(indice, count)
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static <T extends @Nullable Object> int[] indiceOf(T[] array, T value, @Nullable IEquivalent<T> equivalent) {
        Objects.requireNonNull(array);

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
            return Arrays.copyOf(indice, count);
        } else {
            return new int[0];
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
     * @throws NullPointerException
     *             파라미터({@code array, values 중에 1개라도})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 03. 30.
     * 
     */
    public static <T extends @Nullable Object> int[] indiceOf(T[] array, T[] values) {
        return ArrayUtils.<T> indiceOfArray(array, values, null);
    }

    /**
     * 주어진 배열에서 찾고자 하는 배열들의 위치 정보를 반환합니다.<br>
     * 반환되는 배열의 길이는 0을 포함한 짝수이며, (홀/짝) 쌍을 이루는 값이 (시작/끝)의 정보를 표현합니다.<br>
     * <b>참고:</b> 검색된 배열이 겹칠 경우 중복해서 찾지 않습니다. (Non-overlapping 매칭) *
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 03. 30.    parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 25.    parkjunhong77@gmail.com     무한 루프 방지 및 객체 비교 안전성 개선
     * </pre>
     * 
     * @param <T>
     *            배열 요소의 타입
     * @param array
     *            검색 대상 원본 배열
     * @param values
     *            찾고자 하는 타겟 배열 (길이가 1 이상이어야 함)
     * @param equi
     *            배열 동등성 비교 인터페이스 ({@code null}인 경우 기본 제공 팩토리 사용)
     * @return 찾은 위치의 시작과 끝 인덱스가 쌍으로 담긴 배열 (찾지 못한 경우 길이가 0인 배열)
     * 
     * @throws NullPointerException
     *             파라미터({@code array} 또는 {@code values})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 03. 30.
     * @version 1.8.0
     */
    // apply to 'return Arrays.copyOf(indice, indicePos);'
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static <T extends @Nullable Object> int[] indiceOfArray(T[] array, T[] values, @Nullable IEquivalent<T[]> equi) {
        ObjectUtils.requireNonNulls(array, values);

        // 1. [버그 수정] 빈 배열 예외(ArrayIndexOutOfBoundsException) 사전 차단
        if (values.length == 0 || array.length < values.length) {
            return new int[0];
        }

        // 최악의 경우(전부 매칭)를 가정한 크기 할당
        int[] indice = new int[(array.length / values.length) * 2];
        int indicePos = 0;

        if (equi == null) {
            equi = EquivalentFactory.getDefault();
        }

        int arrayLength = array.length;
        int targetLength = values.length;

        // 찾고자 하는 첫 번째 데이터 (values 길이가 1 이상임이 보장됨)
        final T tfc = values[0];

        int arrayPos = 0;

        // 2. [최적화] 남은 길이가 targetLength보다 작으면 더 이상 검색할 필요가 없으므로 루프 종료
        while (arrayPos <= arrayLength - targetLength) {

            // 3. [버그 수정] 제네릭 객체의 메모리 참조 비교(==) 대신 안전한 동등성 비교(Objects.equals) 사용
            if (Objects.equals(array[arrayPos], tfc)) {

                // 첫 번째 요소가 일치하므로, 전체 부분 배열 추출 후 상세 비교 진행
                if (equi.equals(Arrays.copyOfRange(array, arrayPos, arrayPos + targetLength), values)) {
                    indice[indicePos++] = arrayPos;
                    indice[indicePos++] = arrayPos + targetLength - 1;

                    // 매칭 성공 시 중복(Overlap) 탐색을 피하기 위해 타겟 길이만큼 인덱스 점프
                    arrayPos += targetLength;
                } else {
                    // 4. [치명적 버그 수정] 전체 비교 실패 시 다음 인덱스로 정상 이동 (무한 루프 방지)
                    arrayPos++;
                }
            } else {
                arrayPos++;
            }
        }

        return Arrays.copyOf(indice, indicePos);
    }

    /**
     * 주어진 값으로 채워지는, 길이 <b>{@code l}</b>인 배열을 생성합니다.
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
     * 주어진 값으로 채워지는, 길이 <b>{@code l}</b>인 배열을 생성합니다.
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
     * 주어진 값으로 채워지는, 길이 <b>{@code l}</b>인 배열을 생성합니다.
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
     * 주어진 값으로 채워지는, 길이 <b>{@code l}</b>인 배열을 생성합니다.
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
     * 주어진 값으로 채워지는, 길이 <b>{@code l}</b>인 배열을 생성합니다.
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
     * 주어진 값으로 채워지는, 길이 <b>{@code l}</b>인 배열을 생성합니다.
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
     * 주어진 값으로 채워지는, 길이 <b>{@code l}</b>인 배열을 생성합니다.
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
     * 주어진 값으로 채워지는, 길이 <b>{@code l}</b>인 배열을 생성합니다.
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
     * 주어진 값으로 채워지는, 길이 <b>{@code l}</b>인 배열을 생성합니다.
     * 
     * @param length
     *            배열의 길이
     * @param initValue
     *            초기값
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code initValue})가 {@code null}인 경우 발생.
     */
    // apply to 'return array'
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static <T> T[] initArray(int length, T initValue) {
        Objects.requireNonNull(initValue);

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
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 03. 13.
     * 
     */
    public static boolean[] insert(boolean[] array, boolean value, int index) {
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
    }

    /**
     * 새로운 값을 원하는 여러 위치에 삽입합니다.
     * 
     * @param array
     * @param value
     * @param indice
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code array, indice 중에 1개라도})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 03. 13.
     * 
     */
    public static boolean[] insert(boolean[] array, boolean value, int... indice) {
        ObjectUtils.requireNonNulls(array, indice);

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
    }

    /**
     * 새로운 값을 원하는 위치에 삽입합니다.
     * 
     * @param array
     * @param value
     * @param index
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 03. 13.
     * 
     */
    public static byte[] insert(byte[] array, byte value, int index) {
        Objects.requireNonNull(array);

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
    }

    /**
     * 새로운 값을 원하는 여러 위치에 삽입합니다.
     * 
     * @param array
     * @param value
     * @param indice
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code array, indice 중에 1개라도})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 03. 13.
     * 
     */
    public static byte[] insert(byte[] array, byte value, int... indice) {
        ObjectUtils.requireNonNulls(array, indice);

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
    }

    /**
     * 새로운 값을 원하는 위치에 삽입합니다.
     * 
     * @param array
     * @param value
     * @param index
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 03. 13.
     * 
     */
    public static char[] insert(char[] array, char value, int index) {
        Objects.requireNonNull(array);

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
    }

    /**
     * 새로운 값을 원하는 여러 위치에 삽입합니다.
     * 
     * @param array
     * @param value
     * @param indice
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code array, indice 중에 1개라도})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 03. 13.
     * 
     */
    public static char[] insert(char[] array, char value, int... indice) {
        ObjectUtils.requireNonNulls(array, indice);

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
    }

    /**
     * 새로운 값을 원하는 위치에 삽입합니다.
     * 
     * @param array
     * @param value
     * @param index
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 03. 13.
     * 
     */
    public static double[] insert(double[] array, double value, int index) {
        Objects.requireNonNull(array);

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
    }

    /**
     * 새로운 값을 원하는 여러 위치에 삽입합니다.
     * 
     * @param array
     * @param value
     * @param indice
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code array, indice 중에 1개라도})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 03. 13.
     * 
     */
    public static double[] insert(double[] array, double value, int... indice) {
        ObjectUtils.requireNonNulls(array, indice);

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
    }

    /**
     * 새로운 값을 원하는 위치에 삽입합니다.
     * 
     * @param array
     * @param value
     * @param index
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 03. 13.
     * 
     */
    public static float[] insert(float[] array, float value, int index) {
        Objects.requireNonNull(array);

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
    }

    /**
     * 새로운 값을 원하는 여러 위치에 삽입합니다.
     * 
     * @param array
     * @param value
     * @param indice
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code array, indice 중에 1개라도})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 03. 13.
     * 
     */
    public static float[] insert(float[] array, float value, int... indice) {
        ObjectUtils.requireNonNulls(array, indice);

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
    }

    /**
     * 새로운 값을 원하는 위치에 삽입합니다.
     * 
     * @param array
     * @param value
     * @param index
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 03. 13.
     * 
     */
    public static int[] insert(int[] array, int value, int index) {
        Objects.requireNonNull(array);

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
    }

    /**
     * 새로운 값을 원하는 여러 위치에 삽입합니다.
     * 
     * @param array
     * @param value
     * @param indice
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code array, indice 중에 1개라도})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 03. 13.
     * 
     */
    public static int[] insert(int[] array, int value, int... indice) {
        ObjectUtils.requireNonNulls(array, indice);

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
    }

    /**
     * 새로운 값을 원하는 위치에 삽입합니다.
     * 
     * @param array
     * @param value
     * @param index
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 03. 13.
     * 
     */
    public static long[] insert(long[] array, long value, int index) {
        Objects.requireNonNull(array);

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
    }

    /**
     * 새로운 값을 원하는 여러 위치에 삽입합니다.
     * 
     * @param array
     * @param value
     * @param indice
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code array, indice 중에 1개라도})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 03. 13.
     * 
     */
    public static long[] insert(long[] array, long value, int... indice) {
        ObjectUtils.requireNonNulls(array, indice);

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
    }

    /**
     * 새로운 값을 원하는 여러 위치에 삽입합니다.
     * 
     * @param array
     * @param value
     * @param indice
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code array, indice 중에 1개라도})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 03. 13.
     * 
     */
    public static short[] insert(short[] array, short value, int... indice) {
        ObjectUtils.requireNonNulls(array, indice);

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
    }

    /**
     * 새로운 값을 원하는 위치에 삽입합니다.
     * 
     * @param array
     * @param value
     * @param index
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 03. 13.
     * 
     */
    public static short[] insert(short[] array, short value, int index) {
        Objects.requireNonNull(array);

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
    }

    /**
     * 새로운 값을 원하는 위치에 삽입합니다.
     * 
     * @param array
     * @param value
     * @param index
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 03. 13.
     * 
     */
    public static <T extends @Nullable Object> T[] insert(T[] array, T value, int index) {
        Objects.requireNonNull(array);

        if (index < 0 || index > array.length) {
            throw new IllegalArgumentException("An index(int index) must be between 0 to " + array.length);
        } else if (index == 0) {
            return ArrayUtils.<T> prepend(array, value);
        } else if (index == array.length) {
            return ArrayUtils.<T> add(array, value);
        } else {
            T[] newArray = (T[]) Array.newInstance(array.getClass().getComponentType(), array.length + 1);

            System.arraycopy(array, 0, newArray, 0, index);

            // insert a new value
            newArray[index] = value;

            System.arraycopy(array, index, newArray, index + 1, array.length - index);

            return newArray;
        }
    }

    /**
     * 새로운 값을 원하는 여러 위치에 삽입합니다.
     * 
     * @param array
     * @param value
     * @param indice
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code array, indice 중에 1개라도})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 03. 13.
     * 
     */
    // apply to 'return newArray'
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static <T> T[] insert(T[] array, T value, int... indice) {
        ObjectUtils.requireNonNulls(array, indice);

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
    }

    /**
     * 여러 개의 배열을 하나의 배열로 합쳐서 반환합니다.
     * 
     * @param arrays
     * @return 합쳐진 배열. 모든 배열이 {@code null}인 경우 {@code null}을 반환합니다.
     * 
     * @throws NullPointerException
     *             파라미터({@code arrays})가 {@code null}인 경우 발생.
     * 
     * 
     * @since 2012. 03. 12.
     * 
     */
    public static boolean @Nullable [] merge(boolean @Nullable []... arrays) {

        Objects.requireNonNull(arrays);

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
     * @throws NullPointerException
     *             파라미터({@code arr1, arr2 모두})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 3. 9.
     * 
     */
    // 아래 내용에 적용됨.
    // - Arrays.copyOf(arr1, arr1.length)
    // - Arrays.copyOf(arr2, arr1.length)
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static boolean[] merge(boolean @Nullable [] arr1, boolean @Nullable [] arr2) {
        if (arr1 != null && arr2 != null) {
            boolean[] merged = new boolean[arr1.length + arr2.length];

            System.arraycopy(arr1, 0, merged, 0, arr1.length);
            System.arraycopy(arr2, 0, merged, arr1.length, arr2.length);

            return merged;
        } else if (arr1 != null) {
            return Arrays.copyOf(arr1, arr1.length);
        } else if (arr2 != null) {
            return Arrays.copyOf(arr2, arr2.length);
        } else {
            throw new NullPointerException("All parameters(boolean[] arr1, boolean[] arr2) must not be null: arr1=null, arr2=null");
        }
    }

    /**
     * 여러 개의 배열을 하나의 배열로 합쳐서 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 03. 12.     parkjunhong77@gmail.com     최초 작성
     * 2026. 4. 3.       parkjunhong77@gmail.com     (3.0.0) 불필요한 중간 배열 제거 및 빈 배열 병합 버그 수정
     * </pre>
     *
     * @param arrays
     *            배열 가변 인자
     *
     * @return 합쳐진 배열. 모든 배열이 {@code null}인 경우 {@code null}을 반환합니다.
     *
     * @throws NullPointerException
     *             파라미터({@code arrays})가 {@code null}인 경우 발생.
     *
     * @since 2012. 03. 12.
     */
    public static byte @Nullable [] merge(byte @Nullable []... arrays) {
        Objects.requireNonNull(arrays);

        boolean allNull = true;
        int totalLength = 0;

        // 1차 순회: 전체 null 여부 확인 및 병합할 총 길이 계산
        for (byte[] array : arrays) {
            if (array != null) {
                allNull = false;
                totalLength += array.length;
            }
        }

        // 모든 배열이 null인 경우 Javadoc 명세에 따라 null 반환
        if (allNull) {
            return null;
        }

        // 모든 배열의 길이가 0인 경우 빈 배열 반환 (기존 버그 수정)
        if (totalLength == 0) {
            return new byte[0];
        }

        byte[] mergedArray = new byte[totalLength];
        int destPos = 0;

        // 2차 순회: 실제 데이터 병합 복사
        for (byte[] array : arrays) {
            if (array != null && array.length > 0) {
                System.arraycopy(array, 0, mergedArray, destPos, array.length);
                destPos += array.length;
            }
        }

        return mergedArray;
    }

    /**
     * 2개의 배열을 하나의 배열로 합쳐서 반환합니다.
     * 
     * @param arr1
     * @param arr2
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code arr1, arr2 모두})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 3. 9.
     * 
     */
    // 아래 내용에 적용됨.
    // - Arrays.copyOf(arr1, arr1.length)
    // - Arrays.copyOf(arr2, arr1.length)
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static byte[] merge(byte @Nullable [] arr1, byte @Nullable [] arr2) {

        if (arr1 != null && arr2 != null) {
            byte[] merged = new byte[arr1.length + arr2.length];

            System.arraycopy(arr1, 0, merged, 0, arr1.length);
            System.arraycopy(arr2, 0, merged, arr1.length, arr2.length);

            return merged;
        } else if (arr1 != null) {
            return Arrays.copyOf(arr1, arr1.length);
        } else if (arr2 != null) {
            return Arrays.copyOf(arr2, arr2.length);
        } else {
            throw new NullPointerException("All parameters(byte[] arr1, byte[] arr2) must not be null: arr1=null, arr2=null");
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
     * @throws NullPointerException
     *             파라미터({@code arr1, arr2 모두})가 {@code null}인 경우 발생.
     * 
     * 
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (fafanmama@naver.com)
     */
    // 아래 내용에 적용됨.
    // - Arrays.copyOf(arr1, arr1.length)
    // - Arrays.copyOf(arr2, arr1.length)
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static byte[] merge(byte @Nullable [] arr1, byte @Nullable [] arr2, int length) {
        if (arr1 != null && arr2 != null) {
            byte[] merged = new byte[arr1.length + length];

            System.arraycopy(arr1, 0, merged, 0, arr1.length);
            System.arraycopy(arr2, 0, merged, arr1.length, length);

            return merged;
        } else if (arr1 != null) {
            return Arrays.copyOf(arr1, arr1.length);
        } else if (arr2 != null) {
            return Arrays.copyOf(arr2, arr2.length);
        } else {
            throw new NullPointerException("All parameters(byte[] arr1, byte[] arr2) must not be null: arr1=null, arr2=null");
        }
    }

    /**
     * 여러 개의 배열을 하나의 배열로 합쳐서 반환합니다.
     * 
     * @param arrays
     * @return 합쳐진 배열. 모든 배열이 {@code null}인 경우 {@code null}을 반환합니다.
     * 
     * @throws NullPointerException
     *             파라미터({@code arrays})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 03. 12.
     * 
     */
    public static char @Nullable [] merge(char @Nullable []... arrays) {

        Objects.requireNonNull(arrays);

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
     * @throws NullPointerException
     *             파라미터({@code arr1, arr2 모두})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 3. 9.
     * 
     */
    // 아래 내용에 적용됨.
    // - Arrays.copyOf(arr1, arr1.length)
    // - Arrays.copyOf(arr2, arr1.length)
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static char[] merge(char @Nullable [] arr1, char @Nullable [] arr2) {
        if (arr1 != null && arr2 != null) {
            char[] merged = new char[arr1.length + arr2.length];

            System.arraycopy(arr1, 0, merged, 0, arr1.length);
            System.arraycopy(arr2, 0, merged, arr1.length, arr2.length);

            return merged;
        } else if (arr1 != null) {
            return Arrays.copyOf(arr1, arr1.length);
        } else if (arr2 != null) {
            return Arrays.copyOf(arr2, arr2.length);
        } else {
            throw new NullPointerException("All parameters(char[] arr1, char[] arr2) must not be null: arr1=null, arr2=null");
        }
    }

    /**
     * 여러 개의 배열을 하나의 배열로 합쳐서 반환합니다.
     * 
     * @param arrays
     * @return 합쳐진 배열. 모든 배열이 {@code null}인 경우 {@code null}을 반환합니다.
     * 
     * @throws NullPointerException
     *             파라미터({@code arrays})가 {@code null}인 경우 발생.
     * 
     * 
     * @since 2012. 03. 12.
     * 
     */
    public static double @Nullable [] merge(double @Nullable []... arrays) {

        Objects.requireNonNull(arrays);

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
     * 
     * @throws NullPointerException
     *             파라미터({@code arr1, arr2 모두})가 {@code null}인 경우 발생.
     * 
     * 
     * @since 2012. 3. 9.
     * 
     */
    // 아래 내용에 적용됨.
    // - Arrays.copyOf(arr1, arr1.length)
    // - Arrays.copyOf(arr2, arr1.length)
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static double[] merge(double @Nullable [] arr1, double @Nullable [] arr2) {
        if (arr1 != null && arr2 != null) {
            double[] merged = new double[arr1.length + arr2.length];

            System.arraycopy(arr1, 0, merged, 0, arr1.length);
            System.arraycopy(arr2, 0, merged, arr1.length, arr2.length);

            return merged;
        } else if (arr1 != null) {
            return Arrays.copyOf(arr1, arr1.length);
        } else if (arr2 != null) {
            return Arrays.copyOf(arr2, arr2.length);
        } else {
            throw new NullPointerException("All parameters(double[] arr1, double[] arr2) must not be null: arr1=null, arr2=null");
        }
    }

    /**
     * 여러 개의 배열을 하나의 배열로 합쳐서 반환합니다.
     * 
     * @param arrays
     * @return 합쳐진 배열. 모든 배열이 {@code null}인 경우 {@code null}을 반환합니다.
     * 
     * @throws NullPointerException
     *             파라미터({@code arrays})가 {@code null}인 경우 발생.
     * 
     * 
     * @since 2012. 03. 12.
     * 
     */
    public static float @Nullable [] merge(float @Nullable []... arrays) {

        Objects.requireNonNull(arrays);

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
     * @throws NullPointerException
     *             파라미터({@code arr1, arr2 모두})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 3. 9.
     * 
     */
    // 아래 내용에 적용됨.
    // - Arrays.copyOf(arr1, arr1.length)
    // - Arrays.copyOf(arr2, arr1.length)
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static float[] merge(float @Nullable [] arr1, float @Nullable [] arr2) {
        if (arr1 != null && arr2 != null) {
            float[] merged = new float[arr1.length + arr2.length];

            System.arraycopy(arr1, 0, merged, 0, arr1.length);
            System.arraycopy(arr2, 0, merged, arr1.length, arr2.length);

            return merged;
        } else if (arr1 != null) {
            return Arrays.copyOf(arr1, arr1.length);
        } else if (arr2 != null) {
            return Arrays.copyOf(arr2, arr2.length);
        } else {
            throw new NullPointerException("All parameters(float[] arr1, float[] arr2) must not be null: arr1=null, arr2=null");
        }
    }

    /**
     * 여러 개의 배열을 하나의 배열로 합쳐서 반환합니다.
     * 
     * @param arrays
     * @return 합쳐진 배열. 모든 배열이 {@code null}인 경우 {@code null}을 반환합니다.
     * 
     * @throws NullPointerException
     *             파라미터({@code arrays})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 03. 12.
     * 
     */
    public static int @Nullable [] merge(int @Nullable []... arrays) {
        Objects.requireNonNull(arrays);

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
     * 
     * @throws NullPointerException
     *             파라미터({@code arr1, arr2 모두})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 3. 9.
     * 
     */
    // 아래 내용에 적용됨.
    // - Arrays.copyOf(arr1, arr1.length)
    // - Arrays.copyOf(arr2, arr1.length)
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static int[] merge(int @Nullable [] arr1, int @Nullable [] arr2) {
        if (arr1 != null && arr2 != null) {
            int[] merged = new int[arr1.length + arr2.length];

            System.arraycopy(arr1, 0, merged, 0, arr1.length);
            System.arraycopy(arr2, 0, merged, arr1.length, arr2.length);

            return merged;
        } else if (arr1 != null) {
            return Arrays.copyOf(arr1, arr1.length);
        } else if (arr2 != null) {
            return Arrays.copyOf(arr2, arr2.length);
        } else {
            throw new NullPointerException("All parameters(int[] arr1, int[] arr2) must not be null: arr1=null, arr2=null");
        }
    }

    /**
     * 여러 개의 배열을 하나의 배열로 합쳐서 반환합니다.
     * 
     * @param arrays
     * @return 합쳐진 배열. 모든 배열이 {@code null}인 경우 {@code null}을 반환합니다.
     * 
     * @throws NullPointerException
     *             파라미터({@code arrays})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 03. 12.
     * 
     */
    public static long @Nullable [] merge(long @Nullable []... arrays) {
        Objects.requireNonNull(arrays);

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
     * 
     * @throws NullPointerException
     *             파라미터({@code arr1, arr2 모두})가 {@code null}인 경우 발생.
     * 
     * 
     * @since 2012. 3. 9.
     * 
     */
    // 아래 내용에 적용됨.
    // - Arrays.copyOf(arr1, arr1.length)
    // - Arrays.copyOf(arr2, arr1.length)
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static long[] merge(long @Nullable [] arr1, long @Nullable [] arr2) {
        if (arr1 != null && arr2 != null) {
            long[] merged = new long[arr1.length + arr2.length];

            System.arraycopy(arr1, 0, merged, 0, arr1.length);
            System.arraycopy(arr2, 0, merged, arr1.length, arr2.length);

            return merged;
        } else if (arr1 != null) {
            return Arrays.copyOf(arr1, arr1.length);
        } else if (arr2 != null) {
            return Arrays.copyOf(arr2, arr2.length);
        } else {
            throw new NullPointerException("All parameters(long[] arr1, long[] arr2) must not be null: arr1=null, arr2=null");
        }
    }

    /**
     * 여러 개의 배열을 하나의 배열로 합쳐서 반환합니다.
     * 
     * @param arrays
     * @return 합쳐진 배열. 모든 배열이 {@code null}인 경우 {@code null}을 반환합니다.
     * 
     * @throws NullPointerException
     *             파라미터({@code arrays})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 03. 12.
     * 
     */
    public static short @Nullable [] merge(short @Nullable []... arrays) {
        Objects.requireNonNull(arrays);

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
     * @throws NullPointerException
     *             파라미터({@code arr1, arr2 모두})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 3. 9.
     */
    // 아래 내용에 적용됨.
    // - Arrays.copyOf(arr1, arr1.length)
    // - Arrays.copyOf(arr2, arr1.length)
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static short[] merge(short @Nullable [] arr1, short @Nullable [] arr2) {
        if (arr1 != null && arr2 != null) {
            short[] merged = new short[arr1.length + arr2.length];

            System.arraycopy(arr1, 0, merged, 0, arr1.length);
            System.arraycopy(arr2, 0, merged, arr1.length, arr2.length);

            return merged;
        } else if (arr1 != null) {
            return Arrays.copyOf(arr1, arr1.length);
        } else if (arr2 != null) {
            return Arrays.copyOf(arr2, arr2.length);
        } else {
            throw new NullPointerException("All parameters(short[] arr1, short[] arr2) must not be null: arr1=null, arr2=null");
        }
    }

    /**
     * 여러 개의 배열을 하나의 배열로 합쳐서 반환합니다.
     * 
     * @param arrays
     * @return 합쳐진 배열. 모든 배열이 {@code null}인 경우 {@code null}을 반환합니다.
     * 
     * @throws NullPointerException
     *             파라미터({@code arrays})가 {@code null}인 경우 발생.
     * 
     * 
     * @since 2012. 03. 12.
     * 
     */
    public static <T> T @Nullable [] merge(T @Nullable []... arrays) {
        Objects.requireNonNull(arrays);

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
     * 
     * @throws NullPointerException
     *             파라미터({@code arr1, arr2 모두})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 3. 9.
     * 
     * @see {@link Array#newInstance(Class, int)}
     */

    // 아래 내용에 적용됨.
    // - return merged;
    // - Arrays.copyOf(arr1, arr1.length)
    // - Arrays.copyOf(arr2, arr1.length)
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static <T extends @Nullable Object> T[] merge(T @Nullable [] arr1, T @Nullable [] arr2) {

        if (arr1 != null && arr2 != null) {

            assertComponentType(arr1, arr2);

            T[] merged = (T[]) Array.newInstance(arr1.getClass().getComponentType(), arr1.length + arr2.length);

            System.arraycopy(arr1, 0, merged, 0, arr1.length);
            System.arraycopy(arr2, 0, merged, arr1.length, arr2.length);

            return merged;
        } else if (arr1 != null) {
            return Arrays.copyOf(arr1, arr1.length);
        } else if (arr2 != null) {
            return Arrays.copyOf(arr2, arr2.length);
        } else {
            throw new NullPointerException("All parameters(T[] arr1, T[] arr2) must not be null: arr1=null, arr2=null");
        }
    }

    // 아래 내용에 적용됨.
    // - return (T[][]) Array.newInstance(type, size);
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    private static <T extends @Nullable Object> T[] newArray(Class<T> type, int size) {
        return (T[]) Array.newInstance(type, size);
    }

    /**
     * 타입이 서로 다른 2개의 배열을 하나의 배열로 합쳐서 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 12. 28.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param array
     *            데이터를 추가할 배열
     * @param values
     *            새로운 데이터
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code value, array 모두})가 {@code null}인 경우 발생.
     *
     * @since 2021. 12. 28.
     * @version 1.8.0
     */
    public static Object[] objectArray(@Nullable Object value, Object @Nullable [] array) {

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
            throw new NullPointerException("All parameters(T[] arr, T[] values) must not be null: arr=null, values=null");
        }
    }

    /**
     * 타입이 서로 다른 2개의 배열을 하나의 배열로 합쳐서 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 12. 28.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param array
     *            데이터를 추가할 배열
     * @param values
     *            새로운 데이터
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code array, values 모두})가 {@code null}인 경우 발생.
     *
     * @since 2021. 12. 28.
     * @version 1.8.0
     * 
     * 
     */
    public static Object[] objectArray(Object @Nullable [] array, Object @Nullable... values) {

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
            throw new NullPointerException("All parameters(T[] arr, T[] values) must not be null: arr=null, values=null");
        }
    }

    /**
     * 기존 배열에 새로운 데이터를 맨 앞에 추가한 후, 새로운 배열을 반환합니다.<br>
     * 배열이 {@code null}인 경우 새로운 배열을 생성한 후 추가합니다.
     * 
     * @param array
     * @param value
     * 
     * @return
     * @since 2012. 03. 12.
     * 
     */
    public static boolean[] prepend(boolean @Nullable [] array, boolean value) {
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
     * 배열이 {@code null}인 경우 새로운 배열을 생성한 후 추가합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2023. 8. 29.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param array
     *            데이터를 추가할 배열
     * @param values
     *            새로운 데이터
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code array, values 모두})가 {@code null}인 경우 발생.
     *
     * @since 2023. 8. 29.
     * @version 2.0.0
     * 
     */
    public static boolean[] prepend(boolean @Nullable [] array, boolean @Nullable... values) {
        return merge(values, array);
    }

    /**
     * 기존 배열에 새로운 데이터를 맨 앞에 추가한 후, 새로운 배열을 반환합니다.<br>
     * 배열이 {@code null}인 경우 새로운 배열을 생성한 후 추가합니다.
     * 
     * @param array
     * @param value
     * 
     * @return
     * @since 2012. 03. 12.
     * 
     */
    public static byte[] prepend(byte @Nullable [] array, byte value) {
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
     * 배열이 {@code null}인 경우 새로운 배열을 생성한 후 추가합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2023. 8. 29.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param array
     *            데이터를 추가할 배열
     * @param values
     *            새로운 데이터
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code array, values 모두})가 {@code null}인 경우 발생.
     *
     * @since 2023. 8. 29.
     * @version 2.0.0
     * 
     */
    public static byte[] prepend(byte @Nullable [] array, byte @Nullable... values) {
        return merge(values, array);
    }

    /**
     * 기존 배열에 새로운 데이터를 맨 앞에 추가한 후, 새로운 배열을 반환합니다.<br>
     * 배열이 {@code null}인 경우 새로운 배열을 생성한 후 추가합니다.
     * 
     * @param array
     * @param value
     * 
     * @return
     * @since 2012. 03. 12.
     * 
     */
    public static char[] prepend(char @Nullable [] array, char value) {
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
     * 배열이 {@code null}인 경우 새로운 배열을 생성한 후 추가합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2023. 8. 29.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param array
     *            데이터를 추가할 배열
     * @param values
     *            새로운 데이터
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code array, values 모두})가 {@code null}인 경우 발생.
     *
     * @since 2023. 8. 29.
     * @version 2.0.0
     * 
     */
    public static char[] prepend(char @Nullable [] array, char @Nullable... values) {
        return merge(values, array);
    }

    /**
     * 기존 배열에 새로운 데이터를 맨 앞에 추가한 후, 새로운 배열을 반환합니다.<br>
     * 배열이 {@code null}인 경우 새로운 배열을 생성한 후 추가합니다.
     * 
     * @param array
     * @param value
     * 
     * @return
     * @since 2012. 03. 12.
     * 
     */
    public static double[] prepend(double @Nullable [] array, double value) {
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
     * 배열이 {@code null}인 경우 새로운 배열을 생성한 후 추가합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2023. 8. 29.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param array
     *            데이터를 추가할 배열
     * @param values
     *            새로운 데이터
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code array, values 모두})가 {@code null}인 경우 발생.
     *
     * @since 2023. 8. 29.
     * @version 2.0.0
     * 
     */
    public static double[] prepend(double @Nullable [] array, double @Nullable... values) {
        return merge(values, array);
    }

    /**
     * 기존 배열에 새로운 데이터를 맨 앞에 추가한 후, 새로운 배열을 반환합니다.<br>
     * 배열이 {@code null}인 경우 새로운 배열을 생성한 후 추가합니다.
     * 
     * @param array
     * @param value
     * 
     * @return
     * @since 2012. 03. 12.
     * 
     */
    public static float[] prepend(float @Nullable [] array, float value) {
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
     * 배열이 {@code null}인 경우 새로운 배열을 생성한 후 추가합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2023. 8. 29.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param array
     *            데이터를 추가할 배열
     * @param values
     *            새로운 데이터
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code array, values 모두})가 {@code null}인 경우 발생.
     *
     * @since 2023. 8. 29.
     * @version 2.0.0
     * 
     */
    public static float[] prepend(float @Nullable [] array, float @Nullable... values) {
        return merge(values, array);
    }

    /**
     * 기존 배열에 새로운 데이터를 맨 앞에 추가한 후, 새로운 배열을 반환합니다.<br>
     * 배열이 {@code null}인 경우 새로운 배열을 생성한 후 추가합니다.
     * 
     * @param array
     * @param value
     * 
     * @return
     * @since 2012. 03. 12.
     * 
     */
    public static int[] prepend(int @Nullable [] array, int value) {
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
     * 배열이 {@code null}인 경우 새로운 배열을 생성한 후 추가합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2023. 8. 29.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param array
     *            데이터를 추가할 배열
     * @param values
     *            새로운 데이터
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code array, values 모두})가 {@code null}인 경우 발생.
     *
     * @since 2023. 8. 29.
     * @version 2.0.0
     * 
     */
    public static int[] prepend(int @Nullable [] array, int @Nullable... values) {
        return merge(values, array);
    }

    /**
     * 기존 배열에 새로운 데이터를 맨 앞에 추가한 후, 새로운 배열을 반환합니다.<br>
     * 배열이 {@code null}인 경우 새로운 배열을 생성한 후 추가합니다.
     * 
     * @param array
     * @param value
     * 
     * @return
     * @since 2012. 03. 12.
     * 
     */
    public static long[] prepend(long @Nullable [] array, long value) {
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
     * 배열이 {@code null}인 경우 새로운 배열을 생성한 후 추가합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2023. 8. 29.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param array
     *            데이터를 추가할 배열
     * @param values
     *            새로운 데이터
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code array, values 모두})가 {@code null}인 경우 발생.
     *
     * @since 2023. 8. 29.
     * @version 2.0.0
     * 
     */
    public static long[] prepend(long @Nullable [] array, long @Nullable... values) {
        return merge(values, array);
    }

    /**
     * 기존 배열에 새로운 데이터를 맨 앞에 추가한 후, 새로운 배열을 반환합니다.<br>
     * 배열이 {@code null}인 경우 새로운 배열을 생성한 후 추가합니다.
     * 
     * @param array
     * @param value
     * 
     * @return
     * @since 2012. 03. 12.
     * 
     */
    public static short[] prepend(short @Nullable [] array, short value) {
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
     * 배열이 {@code null}인 경우 새로운 배열을 생성한 후 추가합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2023. 8. 29.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param array
     *            데이터를 추가할 배열
     * @param values
     *            새로운 데이터
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code array, values 모두})가 {@code null}인 경우 발생.
     *
     * @since 2023. 8. 29.
     * @version 2.0.0
     * 
     */
    public static short[] prepend(short @Nullable [] array, short @Nullable... values) {
        return merge(values, array);
    }

    /**
     * 기존 배열에 새로운 데이터를 맨 앞에 추가한 후, 새로운 배열을 반환합니다.<br>
     * 배열이 {@code null}인 경우 새로운 배열을 생성한 후 추가합니다.
     * 
     * @param array
     * @param value
     * 
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code array, value 모두})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 03. 12.
     * 
     */
    // 아래 내용에 적용됨.
    // - return (T[]) Array.newInstance(array.getClass().getComponentType(), 1);
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static <T extends @Nullable Object> T[] prepend(T @Nullable [] array, @Nullable T value) {

        if (array != null && value != null) {

            assertComponentType(array, value.getClass());

            T[] newArray = (T[]) Array.newInstance(array.getClass().getComponentType(), array.length + 1);
            System.arraycopy(array, 0, newArray, 1, array.length);
            newArray[0] = value;

            return newArray;
        } else if (array != null) {
            return (T[]) Array.newInstance(array.getClass().getComponentType(), 1);
        } else if (value != null) {
            T[] newArray = (T[]) Array.newInstance(value.getClass(), 1);
            newArray[0] = value;
            return newArray;
        } else {
            throw new NullPointerException("All parameters(T[] array, T value) must not be null: arr=null, value=null");
        }
    }

    /**
     * 기존 배열에 새로운 데이터를 맨 앞에 추가한 후, 새로운 배열을 반환합니다.<br>
     * 배열이 {@code null}인 경우 새로운 배열을 생성한 후 추가합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2023. 8. 29.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param array
     *            데이터를 추가할 배열
     * @param values
     *            새로운 데이터
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code array, values 모두})가 {@code null}인 경우 발생.
     *
     * @since 2023. 8. 29.
     * @version 2.0.0
     * 
     */
    @SafeVarargs
    public static <T extends @Nullable Object> T[] prepend(T @Nullable [] array, T @Nullable... values) {
        return ArrayUtils.<T> merge(values, array);
    }

    /**
     * 주어진 배열에서 대상 값과 같은 모든 값을 제거한 후 새로운 배열을 반환합니다.<br>
     * 제거할 값이 없는 경우 원본 배열을 그대로 반환합니다.
     * 
     * <pre>
     * [개정이력]
     * 날짜       | 작성자   |   내용
     * ------------------------------------------
     * 2012. 03. 09.    parkjunhong77@gmail.com         최초 작성
     * 2026. 03. 25.    parkjunhong77@gmail.com         Two-Pass 알고리즘 적용 및 메모리 최적화
     * </pre>
     * 
     * @param array
     *            대상 배열
     * @param value
     *            제거할 값
     * @return 지정된 값이 제거된 새로운 배열 (제거할 값이 없으면 원본 배열)
     * 
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 03. 09.
     */
    public static boolean[] removeAll(boolean[] array, boolean value) {
        Objects.requireNonNull(array, "array must not be null");

        // 1. Pass: 유지할(삭제하지 않을) 데이터의 개수를 카운트 (O(1) 메모리 공간)
        int keepCount = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != value) {
                keepCount++;
            }
        }

        // 2. 삭제할 대상이 없으면 배열의 복사 없이 원본을 그대로 반환 (조기 종료)
        if (keepCount == array.length) {
            return array;
        }

        // 3. 유지할 데이터의 개수만큼만 정확히 새 배열 할당
        boolean[] newArray = new boolean[keepCount];
        int idx = 0;

        // 4. Pass: 유지할 데이터만 새 배열에 복사 (복잡한 인덱스 계산 제거)
        for (int i = 0; i < array.length; i++) {
            if (array[i] != value) {
                newArray[idx++] = array[i];
            }
        }

        return newArray;
    }

    /**
     * 주어진 배열에서 대상 값과 같은 모든 값을 제거한 후 새로운 배열을 반환합니다.<br>
     * 제거할 값이 없는 경우 원본 배열을 그대로 반환합니다.
     * 
     * <pre>
     * [개정이력]
     * 날짜       | 작성자   |   내용
     * ------------------------------------------
     * 2012. 03. 09.    parkjunhong77@gmail.com         최초 작성
     * 2026. 03. 25.    parkjunhong77@gmail.com         Two-Pass 알고리즘 적용 및 메모리 최적화
     * </pre>
     * 
     * @param array
     *            대상 배열
     * @param value
     *            제거할 값
     * @return 지정된 값이 제거된 새로운 배열 (제거할 값이 없으면 원본 배열)
     * 
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 03. 09.
     */
    public static byte[] removeAll(byte[] array, byte value) {
        Objects.requireNonNull(array, "array must not be null");

        // 1. Pass: 유지할(삭제하지 않을) 데이터의 개수를 카운트 (O(1) 메모리 공간)
        int keepCount = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != value) {
                keepCount++;
            }
        }

        // 2. 삭제할 대상이 없으면 배열의 복사 없이 원본을 그대로 반환 (조기 종료)
        if (keepCount == array.length) {
            return array;
        }

        // 3. 유지할 데이터의 개수만큼만 정확히 새 배열 할당
        byte[] newArray = new byte[keepCount];
        int idx = 0;

        // 4. Pass: 유지할 데이터만 새 배열에 복사 (복잡한 인덱스 계산 제거)
        for (int i = 0; i < array.length; i++) {
            if (array[i] != value) {
                newArray[idx++] = array[i];
            }
        }

        return newArray;
    }

    /**
     * 주어진 배열에서 대상 값과 같은 모든 값을 제거한 후 새로운 배열을 반환합니다.<br>
     * 제거할 값이 없는 경우 원본 배열을 그대로 반환합니다.
     * 
     * <pre>
     * [개정이력]
     * 날짜       | 작성자   |   내용
     * ------------------------------------------
     * 2012. 03. 09.    parkjunhong77@gmail.com         최초 작성
     * 2026. 03. 25.    parkjunhong77@gmail.com         Two-Pass 알고리즘 적용 및 메모리 최적화
     * </pre>
     * 
     * @param array
     *            대상 배열
     * @param value
     *            제거할 값
     * @return 지정된 값이 제거된 새로운 배열 (제거할 값이 없으면 원본 배열)
     * 
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 03. 09.
     */
    public static char[] removeAll(char[] array, char value) {
        Objects.requireNonNull(array, "array must not be null");

        // 1. Pass: 유지할(삭제하지 않을) 데이터의 개수를 카운트 (O(1) 메모리 공간)
        int keepCount = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != value) {
                keepCount++;
            }
        }

        // 2. 삭제할 대상이 없으면 배열의 복사 없이 원본을 그대로 반환 (조기 종료)
        if (keepCount == array.length) {
            return array;
        }

        // 3. 유지할 데이터의 개수만큼만 정확히 새 배열 할당
        char[] newArray = new char[keepCount];
        int idx = 0;

        // 4. Pass: 유지할 데이터만 새 배열에 복사 (복잡한 인덱스 계산 제거)
        for (int i = 0; i < array.length; i++) {
            if (array[i] != value) {
                newArray[idx++] = array[i];
            }
        }

        return newArray;
    }

    /**
     * 주어진 배열에서 대상 값과 같은 모든 값을 제거한 후 새로운 배열을 반환합니다.<br>
     * 제거할 값이 없는 경우 원본 배열을 그대로 반환합니다.
     * 
     * <pre>
     * [개정이력]
     * 날짜       | 작성자   |   내용
     * ------------------------------------------
     * 2012. 03. 09.    parkjunhong77@gmail.com         최초 작성
     * 2026. 03. 25.    parkjunhong77@gmail.com         Two-Pass 알고리즘 적용 및 메모리 최적화
     * </pre>
     * 
     * @param array
     *            대상 배열
     * @param value
     *            제거할 값
     * @return 지정된 값이 제거된 새로운 배열 (제거할 값이 없으면 원본 배열)
     * 
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 03. 09.
     */
    public static double[] removeAll(double[] array, double value) {
        Objects.requireNonNull(array, "array must not be null");

        // 1. Pass: 유지할(삭제하지 않을) 데이터의 개수를 카운트 (O(1) 메모리 공간)
        int keepCount = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != value) {
                keepCount++;
            }
        }

        // 2. 삭제할 대상이 없으면 배열의 복사 없이 원본을 그대로 반환 (조기 종료)
        if (keepCount == array.length) {
            return array;
        }

        // 3. 유지할 데이터의 개수만큼만 정확히 새 배열 할당
        double[] newArray = new double[keepCount];
        int idx = 0;

        // 4. Pass: 유지할 데이터만 새 배열에 복사 (복잡한 인덱스 계산 제거)
        for (int i = 0; i < array.length; i++) {
            if (array[i] != value) {
                newArray[idx++] = array[i];
            }
        }

        return newArray;
    }

    /**
     * 주어진 배열에서 대상 값과 같은 모든 값을 제거한 후 새로운 배열을 반환합니다.<br>
     * 제거할 값이 없는 경우 원본 배열을 그대로 반환합니다.
     * 
     * <pre>
     * [개정이력]
     * 날짜       | 작성자   |   내용
     * ------------------------------------------
     * 2012. 03. 09.    parkjunhong77@gmail.com         최초 작성
     * 2026. 03. 25.    parkjunhong77@gmail.com         Two-Pass 알고리즘 적용 및 메모리 최적화
     * </pre>
     * 
     * @param array
     *            대상 배열
     * @param value
     *            제거할 값
     * @return 지정된 값이 제거된 새로운 배열 (제거할 값이 없으면 원본 배열)
     * 
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 03. 09.
     */
    public static float[] removeAll(float[] array, float value) {
        Objects.requireNonNull(array, "array must not be null");

        // 1. Pass: 유지할(삭제하지 않을) 데이터의 개수를 카운트 (O(1) 메모리 공간)
        int keepCount = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != value) {
                keepCount++;
            }
        }

        // 2. 삭제할 대상이 없으면 배열의 복사 없이 원본을 그대로 반환 (조기 종료)
        if (keepCount == array.length) {
            return array;
        }

        // 3. 유지할 데이터의 개수만큼만 정확히 새 배열 할당
        float[] newArray = new float[keepCount];
        int idx = 0;

        // 4. Pass: 유지할 데이터만 새 배열에 복사 (복잡한 인덱스 계산 제거)
        for (int i = 0; i < array.length; i++) {
            if (array[i] != value) {
                newArray[idx++] = array[i];
            }
        }

        return newArray;
    }

    /**
     * 주어진 배열에서 대상 값과 같은 모든 값을 제거한 후 새로운 배열을 반환합니다.<br>
     * 제거할 값이 없는 경우 원본 배열을 그대로 반환합니다.
     * 
     * <pre>
     * [개정이력]
     * 날짜       | 작성자   |   내용
     * ------------------------------------------
     * 2012. 03. 09.    parkjunhong77@gmail.com         최초 작성
     * 2026. 03. 25.    parkjunhong77@gmail.com         Two-Pass 알고리즘 적용 및 메모리 최적화
     * </pre>
     * 
     * @param array
     *            대상 배열
     * @param value
     *            제거할 값
     * @return 지정된 값이 제거된 새로운 배열 (제거할 값이 없으면 원본 배열)
     * 
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 03. 09.
     */
    public static int[] removeAll(int[] array, int value) {
        Objects.requireNonNull(array, "array must not be null");

        // 1. Pass: 유지할(삭제하지 않을) 데이터의 개수를 카운트 (O(1) 메모리 공간)
        int keepCount = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != value) {
                keepCount++;
            }
        }

        // 2. 삭제할 대상이 없으면 배열의 복사 없이 원본을 그대로 반환 (조기 종료)
        if (keepCount == array.length) {
            return array;
        }

        // 3. 유지할 데이터의 개수만큼만 정확히 새 배열 할당
        int[] newArray = new int[keepCount];
        int idx = 0;

        // 4. Pass: 유지할 데이터만 새 배열에 복사 (복잡한 인덱스 계산 제거)
        for (int i = 0; i < array.length; i++) {
            if (array[i] != value) {
                newArray[idx++] = array[i];
            }
        }

        return newArray;
    }

    /**
     * 주어진 배열에서 대상 값과 같은 모든 값을 제거한 후 새로운 배열을 반환합니다.<br>
     * 제거할 값이 없는 경우 원본 배열을 그대로 반환합니다.
     * 
     * <pre>
     * [개정이력]
     * 날짜       | 작성자   |   내용
     * ------------------------------------------
     * 2012. 03. 09.    parkjunhong77@gmail.com         최초 작성
     * 2026. 03. 25.    parkjunhong77@gmail.com         Two-Pass 알고리즘 적용 및 메모리 최적화
     * </pre>
     * 
     * @param array
     *            대상 배열
     * @param value
     *            제거할 값
     * @return 지정된 값이 제거된 새로운 배열 (제거할 값이 없으면 원본 배열)
     * 
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 03. 09.
     */
    public static long[] removeAll(long[] array, long value) {
        Objects.requireNonNull(array, "array must not be null");

        // 1. Pass: 유지할(삭제하지 않을) 데이터의 개수를 카운트 (O(1) 메모리 공간)
        int keepCount = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != value) {
                keepCount++;
            }
        }

        // 2. 삭제할 대상이 없으면 배열의 복사 없이 원본을 그대로 반환 (조기 종료)
        if (keepCount == array.length) {
            return array;
        }

        // 3. 유지할 데이터의 개수만큼만 정확히 새 배열 할당
        long[] newArray = new long[keepCount];
        int idx = 0;

        // 4. Pass: 유지할 데이터만 새 배열에 복사 (복잡한 인덱스 계산 제거)
        for (int i = 0; i < array.length; i++) {
            if (array[i] != value) {
                newArray[idx++] = array[i];
            }
        }

        return newArray;
    }

    /**
     * 주어진 배열에서 대상 값과 같은 모든 값을 제거한 후 새로운 배열을 반환합니다.<br>
     * 제거할 값이 없는 경우 원본 배열을 그대로 반환합니다.
     * 
     * <pre>
     * [개정이력]
     * 날짜       | 작성자   |   내용
     * ------------------------------------------
     * 2012. 03. 09.    parkjunhong77@gmail.com         최초 작성
     * 2026. 03. 25.    parkjunhong77@gmail.com         Two-Pass 알고리즘 적용 및 메모리 최적화
     * </pre>
     * 
     * @param array
     *            대상 배열
     * @param value
     *            제거할 값
     * @return 지정된 값이 제거된 새로운 배열 (제거할 값이 없으면 원본 배열)
     * 
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 03. 09.
     */
    public static short[] removeAll(short[] array, short value) {
        Objects.requireNonNull(array, "array must not be null");

        // 1. Pass: 유지할(삭제하지 않을) 데이터의 개수를 카운트 (O(1) 메모리 공간)
        int keepCount = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != value) {
                keepCount++;
            }
        }

        // 2. 삭제할 대상이 없으면 배열의 복사 없이 원본을 그대로 반환 (조기 종료)
        if (keepCount == array.length) {
            return array;
        }

        // 3. 유지할 데이터의 개수만큼만 정확히 새 배열 할당
        short[] newArray = new short[keepCount];
        int idx = 0;

        // 4. Pass: 유지할 데이터만 새 배열에 복사 (복잡한 인덱스 계산 제거)
        for (int i = 0; i < array.length; i++) {
            if (array[i] != value) {
                newArray[idx++] = array[i];
            }
        }

        return newArray;
    }

    /**
     * 주어진 배열에서 대상 조건(filter)을 만족하는 모든 값을 제거한 후 새로운 배열을 반환합니다.
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * -------------------------------------------------------------------------
     * 2012. 03. 09.    parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.    parkjunhong77@gmail.com     Two-Pass 알고리즘 적용 및 메모리 최적화
     * </pre>
     * 
     * @param <T>
     *            배열 요소의 타입 (파라메트릭 널성 지원)
     * @param array
     *            대상 배열
     * @param filter
     *            제거할 요소를 판별하는 조건 (true 반환 시 제거)
     * @return 조건에 맞는 값이 제거된 새로운 배열 (제거할 값이 없으면 원본 배열 그대로 반환)
     * 
     * @throws NullPointerException
     *             파라미터({@code array} 또는 {@code filter})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 03. 09.
     */
    // 아래 내용에 적용됨.
    // - return Arrays.copyOf(tempArray, keepCount);
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static <T extends @Nullable Object> T[] removeAll(T[] array, Predicate<T> filter) {
        ObjectUtils.requireNonNulls(array, filter);

        // 1. 최대 크기(원본 배열과 동일)로 임시 배열 생성
        T[] tempArray = (T[]) Array.newInstance(array.getClass().getComponentType(), array.length);
        int keepCount = 0;

        // 2. Predicate 평가는 요소당 정확히 1번 실행
        for (int i = 0; i < array.length; i++) {
            if (!filter.test(array[i])) {
                // 검사를 통과한 요소만 순차적으로 임시 배열에 적재
                tempArray[keepCount++] = array[i];
            }
        }

        // 3. 삭제된 대상이 단 하나도 없다면, 임시 배열을 버리고 원본을 그대로 반환 (조기 종료)
        if (keepCount == array.length) {
            return array;
        }

        // 4. 유효한 데이터 개수(keepCount)만큼만 잘라서 최종 반환
        // (Arrays.copyOf 내부에서 가장 빠르고 안전하게 System.arraycopy가 1번만 수행됨)
        return Arrays.copyOf(tempArray, keepCount);
    }

    /**
     * 주어진 배열에서 대상 값과 같은 모든 값을 제거한 후 새로운 배열을 반환합니다.
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * -------------------------------------------------------------------------
     * 2012. 03. 09.    parkjunhong77@gmail.com     최초 작성
     * </pre>
     * 
     * @param <T>
     *            배열 요소의 타입
     * @param array
     *            대상 배열
     * @param value
     *            제거할 대상 값
     * @return 지정된 값이 제거된 새로운 배열 (제거할 값이 없으면 원본 배열 그대로 반환)
     * 
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 03. 09.
     */
    public static <T extends @Nullable Object> T[] removeAll(T[] array, T value) {
        return ArrayUtils.<T> removeAll(array, value, null);
    }

    /**
     * 주어진 배열에서 대상 값과 같은 모든 값을 제거한 후 새로운 배열을 반환합니다.
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * -------------------------------------------------------------------------
     * 2012. 03. 09.    parkjunhong77@gmail.com     최초 작성
     * </pre>
     * 
     * @param <T>
     *            배열 요소의 타입
     * @param array
     *            대상 배열
     * @param value
     *            제거할 대상 값
     * @param equivalent
     *            클래스 또는 타입 T의 equals(T obj) 메소드가 아닌 다른 비교 기준을 제공하는 객체
     * @return 지정된 값이 제거된 새로운 배열 (제거할 값이 없으면 원본 배열 그대로 반환)
     * 
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 03. 09.
     */
    public static <T extends @Nullable Object> T[] removeAll(T[] array, T value, @Nullable IEquivalent<T> equivalent) {
        Objects.requireNonNull(array);

        final IEquivalent<T> equi = (equivalent == null) ? new DefaultEquivalent<T>() : equivalent;

        // IEquivalent의 비교 로직을 Predicate로 래핑하여 핵심 엔진(Method 1)으로 위임 &rarr; 중복 코드 완벽 제거
        return ArrayUtils.<T> removeAll(array, v -> equi.equals(v, value));
    }

    /**
     * 주어진 배열에서 지정된 인덱스({@code index})에 해당하는 값을 제거한 새로운 배열을 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * -------------------------------------------------------------------------
     * 2012. 03. 09.    parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.    parkjunhong77@gmail.com     인덱스 초과 시 예외 발생(Fail-Fast) 정책 적용
     * </pre>
     *
     * @param array
     *            대상 배열
     * @param index
     *            제거할 값의 인덱스
     * @return 지정된 인덱스의 값이 제거된 새로운 배열
     *
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * @throws ArrayIndexOutOfBoundsException
     *             인덱스가 배열의 범위를 벗어난 경우 ({@code index < 0 || index >= array.length}) 발생.
     *
     * @since 2012. 03. 09.
     */
    public static boolean[] removeAt(boolean[] array, int index) {
        Objects.requireNonNull(array, "array must not be null");

        // Guard Clause: 유효하지 않은 인덱스인 경우 즉시 예외 발생 (Fail-Fast)
        if (index < 0 || index >= array.length) {
            throw new ArrayIndexOutOfBoundsException("Index: " + index + ", Length: " + array.length);
        }

        boolean[] newArray = new boolean[array.length - 1];

        // 앞부분 복사 (0 ~ index 직전까지)
        System.arraycopy(array, 0, newArray, 0, index);
        // 뒷부분 복사 (index 바로 다음부터 ~ 끝까지)
        System.arraycopy(array, index + 1, newArray, index, newArray.length - index);

        return newArray;
    }

    /**
     * 주어진 배열에서 지정된 인덱스({@code index})에 해당하는 값을 제거한 새로운 배열을 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * -------------------------------------------------------------------------
     * 2012. 03. 09.    parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.    parkjunhong77@gmail.com     인덱스 초과 시 예외 발생(Fail-Fast) 정책 적용
     * </pre>
     *
     * @param array
     *            대상 배열
     * @param index
     *            제거할 값의 인덱스
     * @return 지정된 인덱스의 값이 제거된 새로운 배열
     *
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * @throws ArrayIndexOutOfBoundsException
     *             인덱스가 배열의 범위를 벗어난 경우 ({@code index < 0 || index >= array.length}) 발생.
     *
     * @since 2012. 03. 09.
     */
    public static byte[] removeAt(byte[] array, int index) {
        Objects.requireNonNull(array, "array must not be null");

        // Guard Clause: 유효하지 않은 인덱스인 경우 즉시 예외 발생 (Fail-Fast)
        if (index < 0 || index >= array.length) {
            throw new ArrayIndexOutOfBoundsException("Index: " + index + ", Length: " + array.length);
        }

        byte[] newArray = new byte[array.length - 1];

        // 앞부분 복사 (0 ~ index 직전까지)
        System.arraycopy(array, 0, newArray, 0, index);
        // 뒷부분 복사 (index 바로 다음부터 ~ 끝까지)
        System.arraycopy(array, index + 1, newArray, index, newArray.length - index);

        return newArray;
    }

    /**
     * 주어진 배열에서 지정된 인덱스({@code index})에 해당하는 값을 제거한 새로운 배열을 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * -------------------------------------------------------------------------
     * 2012. 03. 09.    parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.    parkjunhong77@gmail.com     인덱스 초과 시 예외 발생(Fail-Fast) 정책 적용
     * </pre>
     *
     * @param array
     *            대상 배열
     * @param index
     *            제거할 값의 인덱스
     * @return 지정된 인덱스의 값이 제거된 새로운 배열
     *
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * @throws ArrayIndexOutOfBoundsException
     *             인덱스가 배열의 범위를 벗어난 경우 ({@code index < 0 || index >= array.length}) 발생.
     *
     * @since 2012. 03. 09.
     */
    public static char[] removeAt(char[] array, int index) {
        Objects.requireNonNull(array, "array must not be null");

        // Guard Clause: 유효하지 않은 인덱스인 경우 즉시 예외 발생 (Fail-Fast)
        if (index < 0 || index >= array.length) {
            throw new ArrayIndexOutOfBoundsException("Index: " + index + ", Length: " + array.length);
        }

        char[] newArray = new char[array.length - 1];

        // 앞부분 복사 (0 ~ index 직전까지)
        System.arraycopy(array, 0, newArray, 0, index);
        // 뒷부분 복사 (index 바로 다음부터 ~ 끝까지)
        System.arraycopy(array, index + 1, newArray, index, newArray.length - index);

        return newArray;
    }

    /**
     * 주어진 배열에서 지정된 인덱스({@code index})에 해당하는 값을 제거한 새로운 배열을 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * -------------------------------------------------------------------------
     * 2012. 03. 09.    parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.    parkjunhong77@gmail.com     인덱스 초과 시 예외 발생(Fail-Fast) 정책 적용
     * </pre>
     *
     * @param array
     *            대상 배열
     * @param index
     *            제거할 값의 인덱스
     * @return 지정된 인덱스의 값이 제거된 새로운 배열
     *
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * @throws ArrayIndexOutOfBoundsException
     *             인덱스가 배열의 범위를 벗어난 경우 ({@code index < 0 || index >= array.length}) 발생.
     *
     * @since 2012. 03. 09.
     */
    public static double[] removeAt(double[] array, int index) {
        Objects.requireNonNull(array, "array must not be null");

        // Guard Clause: 유효하지 않은 인덱스인 경우 즉시 예외 발생 (Fail-Fast)
        if (index < 0 || index >= array.length) {
            throw new ArrayIndexOutOfBoundsException("Index: " + index + ", Length: " + array.length);
        }

        double[] newArray = new double[array.length - 1];

        // 앞부분 복사 (0 ~ index 직전까지)
        System.arraycopy(array, 0, newArray, 0, index);
        // 뒷부분 복사 (index 바로 다음부터 ~ 끝까지)
        System.arraycopy(array, index + 1, newArray, index, newArray.length - index);

        return newArray;
    }

    /**
     * 주어진 배열에서 지정된 인덱스({@code index})에 해당하는 값을 제거한 새로운 배열을 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * -------------------------------------------------------------------------
     * 2012. 03. 09.    parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.    parkjunhong77@gmail.com     인덱스 초과 시 예외 발생(Fail-Fast) 정책 적용
     * </pre>
     *
     * @param array
     *            대상 배열
     * @param index
     *            제거할 값의 인덱스
     * @return 지정된 인덱스의 값이 제거된 새로운 배열
     *
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * @throws ArrayIndexOutOfBoundsException
     *             인덱스가 배열의 범위를 벗어난 경우 ({@code index < 0 || index >= array.length}) 발생.
     *
     * @since 2012. 03. 09.
     */
    public static float[] removeAt(float[] array, int index) {
        Objects.requireNonNull(array, "array must not be null");

        // Guard Clause: 유효하지 않은 인덱스인 경우 즉시 예외 발생 (Fail-Fast)
        if (index < 0 || index >= array.length) {
            throw new ArrayIndexOutOfBoundsException("Index: " + index + ", Length: " + array.length);
        }

        float[] newArray = new float[array.length - 1];

        // 앞부분 복사 (0 ~ index 직전까지)
        System.arraycopy(array, 0, newArray, 0, index);
        // 뒷부분 복사 (index 바로 다음부터 ~ 끝까지)
        System.arraycopy(array, index + 1, newArray, index, newArray.length - index);

        return newArray;
    }

    /**
     * 주어진 배열에서 지정된 인덱스({@code index})에 해당하는 값을 제거한 새로운 배열을 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * -------------------------------------------------------------------------
     * 2012. 03. 09.    parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.    parkjunhong77@gmail.com     인덱스 초과 시 예외 발생(Fail-Fast) 정책 적용
     * </pre>
     *
     * @param array
     *            대상 배열
     * @param index
     *            제거할 값의 인덱스
     * @return 지정된 인덱스의 값이 제거된 새로운 배열
     *
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * @throws ArrayIndexOutOfBoundsException
     *             인덱스가 배열의 범위를 벗어난 경우 ({@code index < 0 || index >= array.length}) 발생.
     *
     * @since 2012. 03. 09.
     */
    public static int[] removeAt(int[] array, int index) {
        Objects.requireNonNull(array, "array must not be null");

        // Guard Clause: 유효하지 않은 인덱스인 경우 즉시 예외 발생 (Fail-Fast)
        if (index < 0 || index >= array.length) {
            throw new ArrayIndexOutOfBoundsException("Index: " + index + ", Length: " + array.length);
        }

        int[] newArray = new int[array.length - 1];

        // 앞부분 복사 (0 ~ index 직전까지)
        System.arraycopy(array, 0, newArray, 0, index);
        // 뒷부분 복사 (index 바로 다음부터 ~ 끝까지)
        System.arraycopy(array, index + 1, newArray, index, newArray.length - index);

        return newArray;
    }

    /**
     * 주어진 배열에서 지정된 인덱스({@code index})에 해당하는 값을 제거한 새로운 배열을 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * -------------------------------------------------------------------------
     * 2012. 03. 09.    parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.    parkjunhong77@gmail.com     인덱스 초과 시 예외 발생(Fail-Fast) 정책 적용
     * </pre>
     *
     * @param array
     *            대상 배열
     * @param index
     *            제거할 값의 인덱스
     * @return 지정된 인덱스의 값이 제거된 새로운 배열
     *
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * @throws ArrayIndexOutOfBoundsException
     *             인덱스가 배열의 범위를 벗어난 경우 ({@code index < 0 || index >= array.length}) 발생.
     *
     * @since 2012. 03. 09.
     */
    public static long[] removeAt(long[] array, int index) {
        Objects.requireNonNull(array, "array must not be null");

        // Guard Clause: 유효하지 않은 인덱스인 경우 즉시 예외 발생 (Fail-Fast)
        if (index < 0 || index >= array.length) {
            throw new ArrayIndexOutOfBoundsException("Index: " + index + ", Length: " + array.length);
        }

        long[] newArray = new long[array.length - 1];

        // 앞부분 복사 (0 ~ index 직전까지)
        System.arraycopy(array, 0, newArray, 0, index);
        // 뒷부분 복사 (index 바로 다음부터 ~ 끝까지)
        System.arraycopy(array, index + 1, newArray, index, newArray.length - index);

        return newArray;
    }

    /**
     * 주어진 배열에서 지정된 인덱스({@code index})에 해당하는 값을 제거한 새로운 배열을 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * -------------------------------------------------------------------------
     * 2012. 03. 09.    parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.    parkjunhong77@gmail.com     인덱스 초과 시 예외 발생(Fail-Fast) 정책 적용
     * </pre>
     *
     * @param array
     *            대상 배열
     * @param index
     *            제거할 값의 인덱스
     * @return 지정된 인덱스의 값이 제거된 새로운 배열
     *
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * @throws ArrayIndexOutOfBoundsException
     *             인덱스가 배열의 범위를 벗어난 경우 ({@code index < 0 || index >= array.length}) 발생.
     *
     * @since 2012. 03. 09.
     */
    public static short[] removeAt(short[] array, int index) {
        Objects.requireNonNull(array, "array must not be null");

        // Guard Clause: 유효하지 않은 인덱스인 경우 즉시 예외 발생 (Fail-Fast)
        if (index < 0 || index >= array.length) {
            throw new ArrayIndexOutOfBoundsException("Index: " + index + ", Length: " + array.length);
        }

        short[] newArray = new short[array.length - 1];

        // 앞부분 복사 (0 ~ index 직전까지)
        System.arraycopy(array, 0, newArray, 0, index);
        // 뒷부분 복사 (index 바로 다음부터 ~ 끝까지)
        System.arraycopy(array, index + 1, newArray, index, newArray.length - index);

        return newArray;
    }

    /**
     * 주어진 배열에서 지정된 인덱스({@code index})에 해당하는 값을 제거한 새로운 배열을 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * -------------------------------------------------------------------------
     * 2012. 03. 09.    parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.    parkjunhong77@gmail.com     인덱스 초과 시 예외 발생(Fail-Fast) 정책 적용
     * </pre>
     *
     * @param array
     *            대상 배열
     * @param index
     *            제거할 값의 인덱스
     * @return 지정된 인덱스의 값이 제거된 새로운 배열
     *
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * @throws ArrayIndexOutOfBoundsException
     *             인덱스가 배열의 범위를 벗어난 경우 ({@code index < 0 || index >= array.length}) 발생.
     *
     * @since 2012. 03. 09.
     */
    public static <T> T[] removeAt(T[] array, int index) {
        Objects.requireNonNull(array, "array must not be null");

        // Guard Clause: 유효하지 않은 인덱스인 경우 즉시 예외 발생 (Fail-Fast)
        if (index < 0 || index >= array.length) {
            throw new ArrayIndexOutOfBoundsException("Index: " + index + ", Length: " + array.length);
        }

        T[] newArray = (T[]) Array.newInstance(array.getClass().getComponentType(), array.length - 1);

        // 앞부분 복사 (0 ~ index 직전까지)
        System.arraycopy(array, 0, newArray, 0, index);
        // 뒷부분 복사 (index 바로 다음부터 ~ 끝까지)
        System.arraycopy(array, index + 1, newArray, index, newArray.length - index);

        return newArray;
    }

    /**
     * 주어진 배열에서 대상 값과 같은 첫 번째 값을 제거한 후 배열을 반환합니다.<br>
     * 대상 값이 배열에 없는 경우 원본 배열을 그대로 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜               작성자                 내용
     * ------------------------------------------------------------------------
     * 2012. 03. 09.    parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.    parkjunhong77@gmail.com     예외 처리 방식 변경 및 value 미존재 시 원본 반환 처리
     * </pre>
     *
     * @param array
     *            대상 배열
     * @param value
     *            제거할 값
     * @return 첫 번째로 일치하는 값이 제거된 새로운 배열. 일치하는 값이 없으면 원본 배열.
     *
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     *
     * @since 2012. 03. 09.
     */
    public static boolean[] removeFirst(boolean[] array, boolean value) {
        Objects.requireNonNull(array, "array must not be null");

        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return removeAt(array, i);
            }
        }

        // 일치하는 값 없음 → 원본 반환
        return array;
    }

    /**
     * 주어진 배열에서 대상 값과 같은 첫 번째 값을 제거한 후 배열을 반환합니다.<br>
     * 대상 값이 배열에 없는 경우 원본 배열을 그대로 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜               작성자                 내용
     * ------------------------------------------------------------------------
     * 2012. 03. 09.    parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.    parkjunhong77@gmail.com     예외 처리 방식 변경 및 value 미존재 시 원본 반환 처리
     * </pre>
     *
     * @param array
     *            대상 배열
     * @param value
     *            제거할 값
     * @return 첫 번째로 일치하는 값이 제거된 새로운 배열. 일치하는 값이 없으면 원본 배열.
     *
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     *
     * @since 2012. 03. 09.
     */
    public static byte[] removeFirst(byte[] array, byte value) {
        Objects.requireNonNull(array, "array must not be null");

        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return removeAt(array, i);
            }
        }

        // 일치하는 값 없음 → 원본 반환
        return array;
    }

    /**
     * 주어진 배열에서 대상 값과 같은 첫 번째 값을 제거한 후 배열을 반환합니다.<br>
     * 대상 값이 배열에 없는 경우 원본 배열을 그대로 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜               작성자                 내용
     * ------------------------------------------------------------------------
     * 2012. 03. 09.    parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.    parkjunhong77@gmail.com     예외 처리 방식 변경 및 value 미존재 시 원본 반환 처리
     * </pre>
     *
     * @param array
     *            대상 배열
     * @param value
     *            제거할 값
     * @return 첫 번째로 일치하는 값이 제거된 새로운 배열. 일치하는 값이 없으면 원본 배열.
     *
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     *
     * @since 2012. 03. 09.
     */
    public static char[] removeFirst(char[] array, char value) {
        Objects.requireNonNull(array, "array must not be null");

        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return removeAt(array, i);
            }
        }

        // 일치하는 값 없음 → 원본 반환
        return array;
    }

    /**
     * 주어진 배열에서 대상 값과 같은 첫 번째 값을 제거한 후 배열을 반환합니다.<br>
     * 대상 값이 배열에 없는 경우 원본 배열을 그대로 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜               작성자                 내용
     * ------------------------------------------------------------------------
     * 2012. 03. 09.    parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.    parkjunhong77@gmail.com     예외 처리 방식 변경 및 value 미존재 시 원본 반환 처리
     * </pre>
     *
     * @param array
     *            대상 배열
     * @param value
     *            제거할 값
     * @return 첫 번째로 일치하는 값이 제거된 새로운 배열. 일치하는 값이 없으면 원본 배열.
     *
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     *
     * @since 2012. 03. 09.
     */
    public static double[] removeFirst(double[] array, double value) {
        Objects.requireNonNull(array, "array must not be null");

        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return removeAt(array, i);
            }
        }

        // 일치하는 값 없음 → 원본 반환
        return array;
    }

    /**
     * 주어진 배열에서 대상 값과 같은 첫 번째 값을 제거한 후 배열을 반환합니다.<br>
     * 대상 값이 배열에 없는 경우 원본 배열을 그대로 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜               작성자                 내용
     * ------------------------------------------------------------------------
     * 2012. 03. 09.    parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.    parkjunhong77@gmail.com     예외 처리 방식 변경 및 value 미존재 시 원본 반환 처리
     * </pre>
     *
     * @param array
     *            대상 배열
     * @param value
     *            제거할 값
     * @return 첫 번째로 일치하는 값이 제거된 새로운 배열. 일치하는 값이 없으면 원본 배열.
     *
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     *
     * @since 2012. 03. 09.
     */
    public static float[] removeFirst(float[] array, float value) {
        Objects.requireNonNull(array, "array must not be null");

        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return removeAt(array, i);
            }
        }

        // 일치하는 값 없음 → 원본 반환
        return array;
    }

    /**
     * 주어진 배열에서 대상 값과 같은 첫 번째 값을 제거한 후 배열을 반환합니다.<br>
     * 대상 값이 배열에 없는 경우 원본 배열을 그대로 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜               작성자                 내용
     * ------------------------------------------------------------------------
     * 2012. 03. 09.    parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.    parkjunhong77@gmail.com     예외 처리 방식 변경 및 value 미존재 시 원본 반환 처리
     * </pre>
     *
     * @param array
     *            대상 배열
     * @param value
     *            제거할 값
     * @return 첫 번째로 일치하는 값이 제거된 새로운 배열. 일치하는 값이 없으면 원본 배열.
     *
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     *
     * @since 2012. 03. 09.
     */
    public static int[] removeFirst(int[] array, int value) {
        Objects.requireNonNull(array, "array must not be null");

        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return removeAt(array, i);
            }
        }

        // 일치하는 값 없음 → 원본 반환
        return array;
    }

    /**
     * 주어진 배열에서 대상 값과 같은 첫 번째 값을 제거한 후 배열을 반환합니다.<br>
     * 대상 값이 배열에 없는 경우 원본 배열을 그대로 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜               작성자                 내용
     * ------------------------------------------------------------------------
     * 2012. 03. 09.    parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.    parkjunhong77@gmail.com     예외 처리 방식 변경 및 value 미존재 시 원본 반환 처리
     * </pre>
     *
     * @param array
     *            대상 배열
     * @param value
     *            제거할 값
     * @return 첫 번째로 일치하는 값이 제거된 새로운 배열. 일치하는 값이 없으면 원본 배열.
     *
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     *
     * @since 2012. 03. 09.
     */
    public static long[] removeFirst(long[] array, long value) {
        Objects.requireNonNull(array, "array must not be null");

        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return removeAt(array, i);
            }
        }

        // 일치하는 값 없음 → 원본 반환
        return array;
    }

    /**
     * 주어진 배열에서 대상 값과 같은 첫 번째 값을 제거한 후 배열을 반환합니다.<br>
     * 대상 값이 배열에 없는 경우 원본 배열을 그대로 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜               작성자                 내용
     * ------------------------------------------------------------------------
     * 2012. 03. 09.    parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.    parkjunhong77@gmail.com     예외 처리 방식 변경 및 value 미존재 시 원본 반환 처리
     * </pre>
     *
     * @param array
     *            대상 배열
     * @param value
     *            제거할 값
     * @return 첫 번째로 일치하는 값이 제거된 새로운 배열. 일치하는 값이 없으면 원본 배열.
     *
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     *
     * @since 2012. 03. 09.
     */
    public static short[] removeFirst(short[] array, short value) {
        Objects.requireNonNull(array, "array must not be null");

        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return removeAt(array, i);
            }
        }

        // 일치하는 값 없음 → 원본 반환
        return array;
    }

    /**
     * 주어진 배열에서 대상 값과 같은 첫 번째 값을 제거한 후 배열을 반환합니다.<br>
     * 대상 값이 배열에 없는 경우 원본 배열을 그대로 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜               작성자                 내용
     * ------------------------------------------------------------------------
     * 2012. 03. 09.    parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param array
     *            대상 배열
     * @param value
     *            제거할 값
     * @return 첫 번째로 일치하는 값이 제거된 새로운 배열. 일치하는 값이 없으면 원본 배열.
     *
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     *
     * @since 2012. 03. 09.
     */
    public static <T extends @Nullable Object> T[] removeFirst(T[] array, T value) {
        return ArrayUtils.<T> removeFirst(array, value, null);
    }

    /**
     * 주어진 배열에서 대상 값과 같은 첫 번째 값을 제거한 후 배열을 반환합니다.<br>
     * 대상 값이 배열에 없는 경우 원본 배열을 그대로 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜               작성자                 내용
     * ------------------------------------------------------------------------
     * 2012. 03. 09.    parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.    parkjunhong77@gmail.com     예외 처리 방식 변경 및 value 미존재 시 원본 반환 처리
     * </pre>
     *
     * @param array
     *            대상 배열
     * @param value
     *            제거할 값
     * @return 첫 번째로 일치하는 값이 제거된 새로운 배열. 일치하는 값이 없으면 원본 배열.
     *
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     *
     * @since 2012. 03. 09.
     */
    public static <T extends @Nullable Object> T[] removeFirst(T[] array, T value, @Nullable IEquivalent<T> equivalent) {
        Objects.requireNonNull(array, "array must not be null");

        if (equivalent == null) {
            equivalent = new DefaultEquivalent<T>();
        }

        for (int i = 0; i < array.length; i++) {
            if (equivalent.equals(array[i], value)) {
                return removeAt(array, i);
            }
        }

        return array;
    }

    /**
     * 주어진 배열에서 대상 값과 같은 마지막 값을 제거한 후 배열을 반환합니다.<br>
     * 대상 값이 배열에 없는 경우 원본 배열을 그대로 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜               작성자                 내용
     * ------------------------------------------------------------------------
     * 2012. 03. 09.    parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.    parkjunhong77@gmail.com     예외 처리 방식 변경 및 value 미존재 시 원본 반환 처리
     * </pre>
     *
     * @param array
     *            대상 배열
     * @param value
     *            제거할 값
     * @return 마지막으로 일치하는 값이 제거된 새로운 배열. 일치하는 값이 없으면 원본 배열.
     *
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     *
     * @since 2012. 03. 09.
     */
    public static boolean[] removeLast(boolean[] array, boolean value) {
        Objects.requireNonNull(array, "array must not be null");

        for (int i = array.length - 1; i > -1; i--) {
            if (array[i] == value) {
                return removeAt(array, i);
            }
        }

        // 일치하는 값 없음 → 원본 반환
        return array;
    }

    /**
     * 주어진 배열에서 대상 값과 같은 마지막 값을 제거한 후 배열을 반환합니다.<br>
     * 대상 값이 배열에 없는 경우 원본 배열을 그대로 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜               작성자                 내용
     * ------------------------------------------------------------------------
     * 2012. 03. 09.    parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.    parkjunhong77@gmail.com     예외 처리 방식 변경 및 value 미존재 시 원본 반환 처리
     * </pre>
     *
     * @param array
     *            대상 배열
     * @param value
     *            제거할 값
     * @return 마지막으로 일치하는 값이 제거된 새로운 배열. 일치하는 값이 없으면 원본 배열.
     *
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     *
     * @since 2012. 03. 09.
     */
    public static byte[] removeLast(byte[] array, byte value) {
        Objects.requireNonNull(array, "array must not be null");

        for (int i = array.length - 1; i > -1; i--) {
            if (array[i] == value) {
                return removeAt(array, i);
            }
        }

        // 일치하는 값 없음 → 원본 반환
        return array;
    }

    /**
     * 주어진 배열에서 대상 값과 같은 마지막 값을 제거한 후 배열을 반환합니다.<br>
     * 대상 값이 배열에 없는 경우 원본 배열을 그대로 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜               작성자                 내용
     * ------------------------------------------------------------------------
     * 2012. 03. 09.    parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.    parkjunhong77@gmail.com     예외 처리 방식 변경 및 value 미존재 시 원본 반환 처리
     * </pre>
     *
     * @param array
     *            대상 배열
     * @param value
     *            제거할 값
     * @return 마지막으로 일치하는 값이 제거된 새로운 배열. 일치하는 값이 없으면 원본 배열.
     *
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     *
     * @since 2012. 03. 09.
     */
    public static char[] removeLast(char[] array, char value) {
        Objects.requireNonNull(array, "array must not be null");

        for (int i = array.length - 1; i > -1; i--) {
            if (array[i] == value) {
                return removeAt(array, i);
            }
        }

        // 일치하는 값 없음 → 원본 반환
        return array;
    }

    /**
     * 주어진 배열에서 대상 값과 같은 마지막 값을 제거한 후 배열을 반환합니다.<br>
     * 대상 값이 배열에 없는 경우 원본 배열을 그대로 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜               작성자                 내용
     * ------------------------------------------------------------------------
     * 2012. 03. 09.    parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.    parkjunhong77@gmail.com     예외 처리 방식 변경 및 value 미존재 시 원본 반환 처리
     * </pre>
     *
     * @param array
     *            대상 배열
     * @param value
     *            제거할 값
     * @return 마지막으로 일치하는 값이 제거된 새로운 배열. 일치하는 값이 없으면 원본 배열.
     *
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     *
     * @since 2012. 03. 09.
     */
    public static double[] removeLast(double[] array, double value) {
        Objects.requireNonNull(array, "array must not be null");

        for (int i = array.length - 1; i > -1; i--) {
            if (array[i] == value) {
                return removeAt(array, i);
            }
        }

        // 일치하는 값 없음 → 원본 반환
        return array;
    }

    /**
     * 주어진 배열에서 대상 값과 같은 마지막 값을 제거한 후 배열을 반환합니다.<br>
     * 대상 값이 배열에 없는 경우 원본 배열을 그대로 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜               작성자                 내용
     * ------------------------------------------------------------------------
     * 2012. 03. 09.    parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.    parkjunhong77@gmail.com     예외 처리 방식 변경 및 value 미존재 시 원본 반환 처리
     * </pre>
     *
     * @param array
     *            대상 배열
     * @param value
     *            제거할 값
     * @return 마지막으로 일치하는 값이 제거된 새로운 배열. 일치하는 값이 없으면 원본 배열.
     *
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     *
     * @since 2012. 03. 09.
     */
    public static float[] removeLast(float[] array, float value) {
        Objects.requireNonNull(array, "array must not be null");

        for (int i = array.length - 1; i > -1; i--) {
            if (array[i] == value) {
                return removeAt(array, i);
            }
        }

        // 일치하는 값 없음 → 원본 반환
        return array;
    }

    /**
     * 주어진 배열에서 대상 값과 같은 마지막 값을 제거한 후 배열을 반환합니다.<br>
     * 대상 값이 배열에 없는 경우 원본 배열을 그대로 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜               작성자                 내용
     * ------------------------------------------------------------------------
     * 2012. 03. 09.    parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.    parkjunhong77@gmail.com     예외 처리 방식 변경 및 value 미존재 시 원본 반환 처리
     * </pre>
     *
     * @param array
     *            대상 배열
     * @param value
     *            제거할 값
     * @return 마지막으로 일치하는 값이 제거된 새로운 배열. 일치하는 값이 없으면 원본 배열.
     *
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     *
     * @since 2012. 03. 09.
     */
    public static int[] removeLast(int[] array, int value) {
        Objects.requireNonNull(array, "array must not be null");

        for (int i = array.length - 1; i > -1; i--) {
            if (array[i] == value) {
                return removeAt(array, i);
            }
        }

        // 일치하는 값 없음 → 원본 반환
        return array;
    }

    /**
     * 주어진 배열에서 대상 값과 같은 마지막 값을 제거한 후 배열을 반환합니다.<br>
     * 대상 값이 배열에 없는 경우 원본 배열을 그대로 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜               작성자                 내용
     * ------------------------------------------------------------------------
     * 2012. 03. 09.    parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.    parkjunhong77@gmail.com     예외 처리 방식 변경 및 value 미존재 시 원본 반환 처리
     * </pre>
     *
     * @param array
     *            대상 배열
     * @param value
     *            제거할 값
     * @return 마지막으로 일치하는 값이 제거된 새로운 배열. 일치하는 값이 없으면 원본 배열.
     *
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     *
     * @since 2012. 03. 09.
     */
    public static long[] removeLast(long[] array, long value) {
        Objects.requireNonNull(array, "array must not be null");

        for (int i = array.length - 1; i > -1; i--) {
            if (array[i] == value) {
                return removeAt(array, i);
            }
        }

        // 일치하는 값 없음 → 원본 반환
        return array;
    }

    /**
     * 주어진 배열에서 대상 값과 같은 마지막 값을 제거한 후 배열을 반환합니다.<br>
     * 대상 값이 배열에 없는 경우 원본 배열을 그대로 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜               작성자                 내용
     * ------------------------------------------------------------------------
     * 2012. 03. 09.    parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.    parkjunhong77@gmail.com     예외 처리 방식 변경 및 value 미존재 시 원본 반환 처리
     * </pre>
     *
     * @param array
     *            대상 배열
     * @param value
     *            제거할 값
     * @return 마지막으로 일치하는 값이 제거된 새로운 배열. 일치하는 값이 없으면 원본 배열.
     *
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     *
     * @since 2012. 03. 09.
     */
    public static short[] removeLast(short[] array, short value) {
        Objects.requireNonNull(array, "array must not be null");

        for (int i = array.length - 1; i > -1; i--) {
            if (array[i] == value) {
                return removeAt(array, i);
            }
        }

        // 일치하는 값 없음 → 원본 반환
        return array;
    }

    /**
     * 주어진 배열에서 대상 값과 같은 마지막 값을 제거한 후 배열을 반환합니다.<br>
     * 대상 값이 배열에 없는 경우 원본 배열을 그대로 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜               작성자                 내용
     * ------------------------------------------------------------------------
     * 2012. 03. 09.    parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param array
     *            대상 배열
     * @param value
     *            제거할 값
     * @return 마지막으로 일치하는 값이 제거된 새로운 배열. 일치하는 값이 없으면 원본 배열.
     *
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     *
     * @since 2012. 03. 09.
     */
    public static <T extends @Nullable Object> T[] removeLast(T[] array, T value) {
        return ArrayUtils.<T> removeLast(array, value, null);
    }

    /**
     * 주어진 배열에서 대상 값과 같은 마지막 값을 제거한 후 배열을 반환합니다.<br>
     * 대상 값이 배열에 없는 경우 원본 배열을 그대로 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜               작성자                 내용
     * ------------------------------------------------------------------------
     * 2012. 03. 09.    parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.    parkjunhong77@gmail.com     예외 처리 방식 변경 및 value 미존재 시 원본 반환 처리
     * </pre>
     *
     * @param array
     *            대상 배열
     * @param value
     *            제거할 값
     * @return 마지막으로 일치하는 값이 제거된 새로운 배열. 일치하는 값이 없으면 원본 배열.
     *
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     *
     * @since 2012. 03. 09.
     */
    public static <T extends @Nullable Object> T[] removeLast(T[] array, T value, @Nullable IEquivalent<T> equivalent) {

        Objects.requireNonNull(array, "array must not be null");

        if (equivalent == null) {
            equivalent = new DefaultEquivalent<T>();
        }

        for (int i = array.length - 1; i > -1; i--) {
            if (equivalent.equals(array[i], value)) {
                return removeAt(array, i);
            }
        }

        // 일치하는 값 없음 → 원본 반환
        return array;
    }

    /**
     * 주어진 배열에서 {@code target} 값을 모두 찾아 {@code data} 배열로 교체한 새로운 배열을 반환합니다.<br>
     * 교체 대상이 없는 경우 원본 배열을 그대로 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜               작성자                 내용
     * ------------------------------------------------------------------------
     * 2012. 03. 30.    parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.    parkjunhong77@gmail.com     핵심 구현을 replaceAll(boolean[], boolean[], boolean[])에 위임
     * </pre>
     *
     * @param array
     *            대상 배열
     * @param target
     *            교체될 값
     * @param data
     *            교체할 새로운 값 배열
     * @return {@code target}이 {@code data}로 교체된 새로운 배열. 교체 대상 없으면 원본 배열.
     *
     * @throws NullPointerException
     *             파라미터({@code array} 또는 {@code data})가 {@code null}인 경우 발생.
     *
     * @since 2012. 03. 30.
     */
    public static boolean[] replaceAll(boolean[] array, boolean target, boolean[] data) {
        ObjectUtils.requireNonNullsWithMessage("'array or data' must not be null", array, data);

        return replaceAll(array, new boolean[] { target }, data);
    }

    /**
     * 주어진 배열에서 {@code target} 배열과 일치하는 구간을 모두 찾아 {@code data} 배열로 교체한 새로운 배열을 반환합니다.<br>
     * 교체 대상이 없는 경우 원본 배열을 그대로 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜               작성자                 내용
     * ------------------------------------------------------------------------
     * 2012. 03. 30.    parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.    parkjunhong77@gmail.com     matchingIndice.length > 1 조건 버그 수정,
     *                                              newArray 크기 계산 오정확화, Javadoc 보강
     * </pre>
     *
     * @param array
     *            대상 배열
     * @param target
     *            교체될 값 배열
     * @param data
     *            교체할 새로운 값 배열
     * @return {@code target}이 {@code data}로 교체된 새로운 배열. 교체 대상 없으면 원본 배열.
     *
     * @throws NullPointerException
     *             파라미터({@code array}, {@code target} 또는 {@code data})가 {@code null}인 경우 발생.
     *
     * @since 2012. 03. 30.
     */
    public static boolean[] replaceAll(boolean[] array, boolean[] target, boolean... data) {
        ObjectUtils.requireNonNullsWithMessage("'array or target or data' must not be null", array, target, data);

        // matchingIndice: [시작인덱스, 끝인덱스] 쌍으로 저장
        int[] matchingIndice = indiceOf(array, target);

        // 교체 대상 없음 → 원본 반환 (쌍이 0개)
        if (matchingIndice.length < 2) {
            return array;
        }

        // 실제 매칭 횟수
        int matchCount = matchingIndice.length / 2;

        // 정확한 newArray 크기
        // = array.length - matchCount * target.length + matchCount * data.length
        boolean[] newArray = new boolean[array.length + matchCount * (data.length - target.length)];

        int srcPos = 0;
        int desPos = 0;
        int dataLength = data.length;

        for (int i = 0; i < matchingIndice.length - 1; i += 2) {
            // target 이전 구간 복사
            int copyLength = matchingIndice[i] - srcPos;
            System.arraycopy(array, srcPos, newArray, desPos, copyLength);
            srcPos = matchingIndice[i + 1] + 1;
            desPos += copyLength;

            // 교체 데이터 복사
            System.arraycopy(data, 0, newArray, desPos, dataLength);
            desPos += dataLength;
        }

        // 마지막 교체 이후 잔여 구간 복사
        if (srcPos < array.length) {
            System.arraycopy(array, srcPos, newArray, desPos, array.length - srcPos);
        }

        return newArray;
    }

    /**
     * 주어진 배열에서 {@code target} 값을 모두 찾아 {@code data} 배열로 교체한 새로운 배열을 반환합니다.<br>
     * 교체 대상이 없는 경우 원본 배열을 그대로 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜               작성자                 내용
     * ------------------------------------------------------------------------
     * 2012. 03. 30.    parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.    parkjunhong77@gmail.com     핵심 구현을 replaceAll(byte[], byte[], byte[])에 위임
     * </pre>
     *
     * @param array
     *            대상 배열
     * @param target
     *            교체될 값
     * @param data
     *            교체할 새로운 값 배열
     * @return {@code target}이 {@code data}로 교체된 새로운 배열. 교체 대상 없으면 원본 배열.
     *
     * @throws NullPointerException
     *             파라미터({@code array} 또는 {@code data})가 {@code null}인 경우 발생.
     *
     * @since 2012. 03. 30.
     */
    public static byte[] replaceAll(byte[] array, byte target, byte[] data) {
        ObjectUtils.requireNonNullsWithMessage("'array or data' must not be null", array, data);

        return replaceAll(array, new byte[] { target }, data);
    }

    /**
     * 주어진 배열에서 {@code target} 배열과 일치하는 구간을 모두 찾아 {@code data} 배열로 교체한 새로운 배열을 반환합니다.<br>
     * 교체 대상이 없는 경우 원본 배열을 그대로 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜               작성자                 내용
     * ------------------------------------------------------------------------
     * 2012. 03. 30.    parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.    parkjunhong77@gmail.com     matchingIndice.length > 1 조건 버그 수정,
     *                                              newArray 크기 계산 오정확화, Javadoc 보강
     * </pre>
     *
     * @param array
     *            대상 배열
     * @param target
     *            교체될 값 배열
     * @param data
     *            교체할 새로운 값 배열
     * @return {@code target}이 {@code data}로 교체된 새로운 배열. 교체 대상 없으면 원본 배열.
     *
     * @throws NullPointerException
     *             파라미터({@code array}, {@code target} 또는 {@code data})가 {@code null}인 경우 발생.
     *
     * @since 2012. 03. 30.
     */
    public static byte[] replaceAll(byte[] array, byte[] target, byte... data) {
        ObjectUtils.requireNonNullsWithMessage("'array or target or data' must not be null", array, target, data);

        // matchingIndice: [시작인덱스, 끝인덱스] 쌍으로 저장
        int[] matchingIndice = indiceOf(array, target);

        // 교체 대상 없음 → 원본 반환 (쌍이 0개)
        if (matchingIndice.length < 2) {
            return array;
        }

        // 실제 매칭 횟수
        int matchCount = matchingIndice.length / 2;

        // 정확한 newArray 크기
        // = array.length - matchCount * target.length + matchCount * data.length
        byte[] newArray = new byte[array.length + matchCount * (data.length - target.length)];

        int srcPos = 0;
        int desPos = 0;
        int dataLength = data.length;

        for (int i = 0; i < matchingIndice.length - 1; i += 2) {
            // target 이전 구간 복사
            int copyLength = matchingIndice[i] - srcPos;
            System.arraycopy(array, srcPos, newArray, desPos, copyLength);
            srcPos = matchingIndice[i + 1] + 1;
            desPos += copyLength;

            // 교체 데이터 복사
            System.arraycopy(data, 0, newArray, desPos, dataLength);
            desPos += dataLength;
        }

        // 마지막 교체 이후 잔여 구간 복사
        if (srcPos < array.length) {
            System.arraycopy(array, srcPos, newArray, desPos, array.length - srcPos);
        }

        return newArray;
    }

    /**
     * 주어진 배열에서 {@code target} 값을 모두 찾아 {@code data} 배열로 교체한 새로운 배열을 반환합니다.<br>
     * 교체 대상이 없는 경우 원본 배열을 그대로 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜               작성자                 내용
     * ------------------------------------------------------------------------
     * 2012. 03. 30.    parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.    parkjunhong77@gmail.com     핵심 구현을 replaceAll(char[], char[], char[])에 위임
     * </pre>
     *
     * @param array
     *            대상 배열
     * @param target
     *            교체될 값
     * @param data
     *            교체할 새로운 값 배열
     * @return {@code target}이 {@code data}로 교체된 새로운 배열. 교체 대상 없으면 원본 배열.
     *
     * @throws NullPointerException
     *             파라미터({@code array} 또는 {@code data})가 {@code null}인 경우 발생.
     *
     * @since 2012. 03. 30.
     */
    public static char[] replaceAll(char[] array, char target, char[] data) {
        ObjectUtils.requireNonNullsWithMessage("'array or data' must not be null", array, data);

        return replaceAll(array, new char[] { target }, data);
    }

    /**
     * 주어진 배열에서 {@code target} 배열과 일치하는 구간을 모두 찾아 {@code data} 배열로 교체한 새로운 배열을 반환합니다.<br>
     * 교체 대상이 없는 경우 원본 배열을 그대로 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜               작성자                 내용
     * ------------------------------------------------------------------------
     * 2012. 03. 30.    parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.    parkjunhong77@gmail.com     matchingIndice.length > 1 조건 버그 수정,
     *                                              newArray 크기 계산 오정확화, Javadoc 보강
     * </pre>
     *
     * @param array
     *            대상 배열
     * @param target
     *            교체될 값 배열
     * @param data
     *            교체할 새로운 값 배열
     * @return {@code target}이 {@code data}로 교체된 새로운 배열. 교체 대상 없으면 원본 배열.
     *
     * @throws NullPointerException
     *             파라미터({@code array}, {@code target} 또는 {@code data})가 {@code null}인 경우 발생.
     *
     * @since 2012. 03. 30.
     */
    public static char[] replaceAll(char[] array, char[] target, char... data) {
        ObjectUtils.requireNonNullsWithMessage("'array or target or data' must not be null", array, target, data);

        // matchingIndice: [시작인덱스, 끝인덱스] 쌍으로 저장
        int[] matchingIndice = indiceOf(array, target);

        // 교체 대상 없음 → 원본 반환 (쌍이 0개)
        if (matchingIndice.length < 2) {
            return array;
        }

        // 실제 매칭 횟수
        int matchCount = matchingIndice.length / 2;

        // 정확한 newArray 크기
        // = array.length - matchCount * target.length + matchCount * data.length
        char[] newArray = new char[array.length + matchCount * (data.length - target.length)];

        int srcPos = 0;
        int desPos = 0;
        int dataLength = data.length;

        for (int i = 0; i < matchingIndice.length - 1; i += 2) {
            // target 이전 구간 복사
            int copyLength = matchingIndice[i] - srcPos;
            System.arraycopy(array, srcPos, newArray, desPos, copyLength);
            srcPos = matchingIndice[i + 1] + 1;
            desPos += copyLength;

            // 교체 데이터 복사
            System.arraycopy(data, 0, newArray, desPos, dataLength);
            desPos += dataLength;
        }

        // 마지막 교체 이후 잔여 구간 복사
        if (srcPos < array.length) {
            System.arraycopy(array, srcPos, newArray, desPos, array.length - srcPos);
        }

        return newArray;
    }

    /**
     * 주어진 배열에서 {@code target} 값을 모두 찾아 {@code data} 배열로 교체한 새로운 배열을 반환합니다.<br>
     * 교체 대상이 없는 경우 원본 배열을 그대로 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜               작성자                 내용
     * ------------------------------------------------------------------------
     * 2012. 03. 30.    parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.    parkjunhong77@gmail.com     핵심 구현을 replaceAll(double[], double[], double[])에 위임
     * </pre>
     *
     * @param array
     *            대상 배열
     * @param target
     *            교체될 값
     * @param data
     *            교체할 새로운 값 배열
     * @return {@code target}이 {@code data}로 교체된 새로운 배열. 교체 대상 없으면 원본 배열.
     *
     * @throws NullPointerException
     *             파라미터({@code array} 또는 {@code data})가 {@code null}인 경우 발생.
     *
     * @since 2012. 03. 30.
     */
    public static double[] replaceAll(double[] array, double target, double[] data) {
        ObjectUtils.requireNonNullsWithMessage("'array or data' must not be null", array, data);

        return replaceAll(array, new double[] { target }, data);
    }

    /**
     * 주어진 배열에서 {@code target} 배열과 일치하는 구간을 모두 찾아 {@code data} 배열로 교체한 새로운 배열을 반환합니다.<br>
     * 교체 대상이 없는 경우 원본 배열을 그대로 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜               작성자                 내용
     * ------------------------------------------------------------------------
     * 2012. 03. 30.    parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.    parkjunhong77@gmail.com     matchingIndice.length > 1 조건 버그 수정,
     *                                              newArray 크기 계산 오정확화, Javadoc 보강
     * </pre>
     *
     * @param array
     *            대상 배열
     * @param target
     *            교체될 값 배열
     * @param data
     *            교체할 새로운 값 배열
     * @return {@code target}이 {@code data}로 교체된 새로운 배열. 교체 대상 없으면 원본 배열.
     *
     * @throws NullPointerException
     *             파라미터({@code array}, {@code target} 또는 {@code data})가 {@code null}인 경우 발생.
     *
     * @since 2012. 03. 30.
     */
    public static double[] replaceAll(double[] array, double[] target, double... data) {
        ObjectUtils.requireNonNullsWithMessage("'array or target or data' must not be null", array, target, data);

        // matchingIndice: [시작인덱스, 끝인덱스] 쌍으로 저장
        int[] matchingIndice = indiceOf(array, target);

        // 교체 대상 없음 → 원본 반환 (쌍이 0개)
        if (matchingIndice.length < 2) {
            return array;
        }

        // 실제 매칭 횟수
        int matchCount = matchingIndice.length / 2;

        // 정확한 newArray 크기
        // = array.length - matchCount * target.length + matchCount * data.length
        double[] newArray = new double[array.length + matchCount * (data.length - target.length)];

        int srcPos = 0;
        int desPos = 0;
        int dataLength = data.length;

        for (int i = 0; i < matchingIndice.length - 1; i += 2) {
            // target 이전 구간 복사
            int copyLength = matchingIndice[i] - srcPos;
            System.arraycopy(array, srcPos, newArray, desPos, copyLength);
            srcPos = matchingIndice[i + 1] + 1;
            desPos += copyLength;

            // 교체 데이터 복사
            System.arraycopy(data, 0, newArray, desPos, dataLength);
            desPos += dataLength;
        }

        // 마지막 교체 이후 잔여 구간 복사
        if (srcPos < array.length) {
            System.arraycopy(array, srcPos, newArray, desPos, array.length - srcPos);
        }

        return newArray;
    }

    /**
     * 주어진 배열에서 {@code target} 값을 모두 찾아 {@code data} 배열로 교체한 새로운 배열을 반환합니다.<br>
     * 교체 대상이 없는 경우 원본 배열을 그대로 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜               작성자                 내용
     * ------------------------------------------------------------------------
     * 2012. 03. 30.    parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.    parkjunhong77@gmail.com     핵심 구현을 replaceAll(float[], float[], float[])에 위임
     * </pre>
     *
     * @param array
     *            대상 배열
     * @param target
     *            교체될 값
     * @param data
     *            교체할 새로운 값 배열
     * @return {@code target}이 {@code data}로 교체된 새로운 배열. 교체 대상 없으면 원본 배열.
     *
     * @throws NullPointerException
     *             파라미터({@code array} 또는 {@code data})가 {@code null}인 경우 발생.
     *
     * @since 2012. 03. 30.
     */
    public static float[] replaceAll(float[] array, float target, float[] data) {
        ObjectUtils.requireNonNullsWithMessage("'array or data' must not be null", array, data);

        return replaceAll(array, new float[] { target }, data);
    }

    /**
     * 주어진 배열에서 {@code target} 배열과 일치하는 구간을 모두 찾아 {@code data} 배열로 교체한 새로운 배열을 반환합니다.<br>
     * 교체 대상이 없는 경우 원본 배열을 그대로 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜               작성자                 내용
     * ------------------------------------------------------------------------
     * 2012. 03. 30.    parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.    parkjunhong77@gmail.com     matchingIndice.length > 1 조건 버그 수정,
     *                                              newArray 크기 계산 오정확화, Javadoc 보강
     * </pre>
     *
     * @param array
     *            대상 배열
     * @param target
     *            교체될 값 배열
     * @param data
     *            교체할 새로운 값 배열
     * @return {@code target}이 {@code data}로 교체된 새로운 배열. 교체 대상 없으면 원본 배열.
     *
     * @throws NullPointerException
     *             파라미터({@code array}, {@code target} 또는 {@code data})가 {@code null}인 경우 발생.
     *
     * @since 2012. 03. 30.
     */
    public static float[] replaceAll(float[] array, float[] target, float... data) {
        ObjectUtils.requireNonNullsWithMessage("'array or target or data' must not be null", array, target, data);

        // matchingIndice: [시작인덱스, 끝인덱스] 쌍으로 저장
        int[] matchingIndice = indiceOf(array, target);

        // 교체 대상 없음 → 원본 반환 (쌍이 0개)
        if (matchingIndice.length < 2) {
            return array;
        }

        // 실제 매칭 횟수
        int matchCount = matchingIndice.length / 2;

        // 정확한 newArray 크기
        // = array.length - matchCount * target.length + matchCount * data.length
        float[] newArray = new float[array.length + matchCount * (data.length - target.length)];

        int srcPos = 0;
        int desPos = 0;
        int dataLength = data.length;

        for (int i = 0; i < matchingIndice.length - 1; i += 2) {
            // target 이전 구간 복사
            int copyLength = matchingIndice[i] - srcPos;
            System.arraycopy(array, srcPos, newArray, desPos, copyLength);
            srcPos = matchingIndice[i + 1] + 1;
            desPos += copyLength;

            // 교체 데이터 복사
            System.arraycopy(data, 0, newArray, desPos, dataLength);
            desPos += dataLength;
        }

        // 마지막 교체 이후 잔여 구간 복사
        if (srcPos < array.length) {
            System.arraycopy(array, srcPos, newArray, desPos, array.length - srcPos);
        }

        return newArray;
    }

    /**
     * 주어진 배열에서 {@code target} 값을 모두 찾아 {@code data} 배열로 교체한 새로운 배열을 반환합니다.<br>
     * 교체 대상이 없는 경우 원본 배열을 그대로 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜               작성자                 내용
     * ------------------------------------------------------------------------
     * 2012. 03. 30.    parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.    parkjunhong77@gmail.com     핵심 구현을 replaceAll(int[], int[], int[])에 위임
     * </pre>
     *
     * @param array
     *            대상 배열
     * @param target
     *            교체될 값
     * @param data
     *            교체할 새로운 값 배열
     * @return {@code target}이 {@code data}로 교체된 새로운 배열. 교체 대상 없으면 원본 배열.
     *
     * @throws NullPointerException
     *             파라미터({@code array} 또는 {@code data})가 {@code null}인 경우 발생.
     *
     * @since 2012. 03. 30.
     */
    public static int[] replaceAll(int[] array, int target, int[] data) {
        ObjectUtils.requireNonNullsWithMessage("'array or data' must not be null", array, data);

        return replaceAll(array, new int[] { target }, data);
    }

    /**
     * 주어진 배열에서 {@code target} 배열과 일치하는 구간을 모두 찾아 {@code data} 배열로 교체한 새로운 배열을 반환합니다.<br>
     * 교체 대상이 없는 경우 원본 배열을 그대로 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜               작성자                 내용
     * ------------------------------------------------------------------------
     * 2012. 03. 30.    parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.    parkjunhong77@gmail.com     matchingIndice.length > 1 조건 버그 수정,
     *                                              newArray 크기 계산 오정확화, Javadoc 보강
     * </pre>
     *
     * @param array
     *            대상 배열
     * @param target
     *            교체될 값 배열
     * @param data
     *            교체할 새로운 값 배열
     * @return {@code target}이 {@code data}로 교체된 새로운 배열. 교체 대상 없으면 원본 배열.
     *
     * @throws NullPointerException
     *             파라미터({@code array}, {@code target} 또는 {@code data})가 {@code null}인 경우 발생.
     *
     * @since 2012. 03. 30.
     */
    public static int[] replaceAll(int[] array, int[] target, int... data) {
        ObjectUtils.requireNonNullsWithMessage("'array or target or data' must not be null", array, target, data);

        // matchingIndice: [시작인덱스, 끝인덱스] 쌍으로 저장
        int[] matchingIndice = indiceOf(array, target);

        // 교체 대상 없음 → 원본 반환 (쌍이 0개)
        if (matchingIndice.length < 2) {
            return array;
        }

        // 실제 매칭 횟수
        int matchCount = matchingIndice.length / 2;

        // 정확한 newArray 크기
        // = array.length - matchCount * target.length + matchCount * data.length
        int[] newArray = new int[array.length + matchCount * (data.length - target.length)];

        int srcPos = 0;
        int desPos = 0;
        int dataLength = data.length;

        for (int i = 0; i < matchingIndice.length - 1; i += 2) {
            // target 이전 구간 복사
            int copyLength = matchingIndice[i] - srcPos;
            System.arraycopy(array, srcPos, newArray, desPos, copyLength);
            srcPos = matchingIndice[i + 1] + 1;
            desPos += copyLength;

            // 교체 데이터 복사
            System.arraycopy(data, 0, newArray, desPos, dataLength);
            desPos += dataLength;
        }

        // 마지막 교체 이후 잔여 구간 복사
        if (srcPos < array.length) {
            System.arraycopy(array, srcPos, newArray, desPos, array.length - srcPos);
        }

        return newArray;
    }

    /**
     * 주어진 배열에서 {@code target} 값을 모두 찾아 {@code data} 배열로 교체한 새로운 배열을 반환합니다.<br>
     * 교체 대상이 없는 경우 원본 배열을 그대로 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜               작성자                 내용
     * ------------------------------------------------------------------------
     * 2012. 03. 30.    parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.    parkjunhong77@gmail.com     핵심 구현을 replaceAll(long[], long[], long[])에 위임
     * </pre>
     *
     * @param array
     *            대상 배열
     * @param target
     *            교체될 값
     * @param data
     *            교체할 새로운 값 배열
     * @return {@code target}이 {@code data}로 교체된 새로운 배열. 교체 대상 없으면 원본 배열.
     *
     * @throws NullPointerException
     *             파라미터({@code array} 또는 {@code data})가 {@code null}인 경우 발생.
     *
     * @since 2012. 03. 30.
     */
    public static long[] replaceAll(long[] array, long target, long[] data) {
        ObjectUtils.requireNonNullsWithMessage("'array or data' must not be null", array, data);

        return replaceAll(array, new long[] { target }, data);
    }

    /**
     * 주어진 배열에서 {@code target} 배열과 일치하는 구간을 모두 찾아 {@code data} 배열로 교체한 새로운 배열을 반환합니다.<br>
     * 교체 대상이 없는 경우 원본 배열을 그대로 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜               작성자                 내용
     * ------------------------------------------------------------------------
     * 2012. 03. 30.    parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.    parkjunhong77@gmail.com     matchingIndice.length > 1 조건 버그 수정,
     *                                              newArray 크기 계산 오정확화, Javadoc 보강
     * </pre>
     *
     * @param array
     *            대상 배열
     * @param target
     *            교체될 값 배열
     * @param data
     *            교체할 새로운 값 배열
     * @return {@code target}이 {@code data}로 교체된 새로운 배열. 교체 대상 없으면 원본 배열.
     *
     * @throws NullPointerException
     *             파라미터({@code array}, {@code target} 또는 {@code data})가 {@code null}인 경우 발생.
     *
     * @since 2012. 03. 30.
     */
    public static long[] replaceAll(long[] array, long[] target, long... data) {
        ObjectUtils.requireNonNullsWithMessage("'array or target or data' must not be null", array, target, data);

        // matchingIndice: [시작인덱스, 끝인덱스] 쌍으로 저장
        int[] matchingIndice = indiceOf(array, target);

        // 교체 대상 없음 → 원본 반환 (쌍이 0개)
        if (matchingIndice.length < 2) {
            return array;
        }

        // 실제 매칭 횟수
        int matchCount = matchingIndice.length / 2;

        // 정확한 newArray 크기
        // = array.length - matchCount * target.length + matchCount * data.length
        long[] newArray = new long[array.length + matchCount * (data.length - target.length)];

        int srcPos = 0;
        int desPos = 0;
        int dataLength = data.length;

        for (int i = 0; i < matchingIndice.length - 1; i += 2) {
            // target 이전 구간 복사
            int copyLength = matchingIndice[i] - srcPos;
            System.arraycopy(array, srcPos, newArray, desPos, copyLength);
            srcPos = matchingIndice[i + 1] + 1;
            desPos += copyLength;

            // 교체 데이터 복사
            System.arraycopy(data, 0, newArray, desPos, dataLength);
            desPos += dataLength;
        }

        // 마지막 교체 이후 잔여 구간 복사
        if (srcPos < array.length) {
            System.arraycopy(array, srcPos, newArray, desPos, array.length - srcPos);
        }

        return newArray;
    }

    /**
     * 주어진 배열에서 {@code target} 값을 모두 찾아 {@code data} 배열로 교체한 새로운 배열을 반환합니다.<br>
     * 교체 대상이 없는 경우 원본 배열을 그대로 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜               작성자                 내용
     * ------------------------------------------------------------------------
     * 2012. 03. 30.    parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.    parkjunhong77@gmail.com     핵심 구현을 replaceAll(short[], short[], short[])에 위임
     * </pre>
     *
     * @param array
     *            대상 배열
     * @param target
     *            교체될 값
     * @param data
     *            교체할 새로운 값 배열
     * @return {@code target}이 {@code data}로 교체된 새로운 배열. 교체 대상 없으면 원본 배열.
     *
     * @throws NullPointerException
     *             파라미터({@code array} 또는 {@code data})가 {@code null}인 경우 발생.
     *
     * @since 2012. 03. 30.
     */
    public static short[] replaceAll(short[] array, short target, short[] data) {
        ObjectUtils.requireNonNullsWithMessage("'array or data' must not be null", array, data);

        return replaceAll(array, new short[] { target }, data);
    }

    /**
     * 주어진 배열에서 {@code target} 배열과 일치하는 구간을 모두 찾아 {@code data} 배열로 교체한 새로운 배열을 반환합니다.<br>
     * 교체 대상이 없는 경우 원본 배열을 그대로 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜               작성자                 내용
     * ------------------------------------------------------------------------
     * 2012. 03. 30.    parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.    parkjunhong77@gmail.com     matchingIndice.length > 1 조건 버그 수정,
     *                                              newArray 크기 계산 오정확화, Javadoc 보강
     * </pre>
     *
     * @param array
     *            대상 배열
     * @param target
     *            교체될 값 배열
     * @param data
     *            교체할 새로운 값 배열
     * @return {@code target}이 {@code data}로 교체된 새로운 배열. 교체 대상 없으면 원본 배열.
     *
     * @throws NullPointerException
     *             파라미터({@code array}, {@code target} 또는 {@code data})가 {@code null}인 경우 발생.
     *
     * @since 2012. 03. 30.
     */
    public static short[] replaceAll(short[] array, short[] target, short... data) {
        ObjectUtils.requireNonNullsWithMessage("'array or target or data' must not be null", array, target, data);

        // matchingIndice: [시작인덱스, 끝인덱스] 쌍으로 저장
        int[] matchingIndice = indiceOf(array, target);

        // 교체 대상 없음 → 원본 반환 (쌍이 0개)
        if (matchingIndice.length < 2) {
            return array;
        }

        // 실제 매칭 횟수
        int matchCount = matchingIndice.length / 2;

        // 정확한 newArray 크기
        // = array.length - matchCount * target.length + matchCount * data.length
        short[] newArray = new short[array.length + matchCount * (data.length - target.length)];

        int srcPos = 0;
        int desPos = 0;
        int dataLength = data.length;

        for (int i = 0; i < matchingIndice.length - 1; i += 2) {
            // target 이전 구간 복사
            int copyLength = matchingIndice[i] - srcPos;
            System.arraycopy(array, srcPos, newArray, desPos, copyLength);
            srcPos = matchingIndice[i + 1] + 1;
            desPos += copyLength;

            // 교체 데이터 복사
            System.arraycopy(data, 0, newArray, desPos, dataLength);
            desPos += dataLength;
        }

        // 마지막 교체 이후 잔여 구간 복사
        if (srcPos < array.length) {
            System.arraycopy(array, srcPos, newArray, desPos, array.length - srcPos);
        }

        return newArray;
    }

    /**
     * 주어진 배열의 데이타를 새로운 데이타 배열로 변경한 새로운 배열을 반환합니다.
     * 
     * @param <T>
     *            배열 요소의 타입
     * @param array
     *            원본 배열
     * @param target
     *            변경될 대상 데이타
     * @param data
     *            새로운 데이타 배열
     * @return 치환이 완료된 새로운 배열
     * @throws NullPointerException
     *             파라미터({@code array} 또는 {@code data})가 {@code null}인 경우 발생.
     * @since 2012. 03. 30.
     */
    public static <T extends @Nullable Object> T[] replaceAll(T[] array, T target, T[] data) {
        return ArrayUtils.<T> replaceAllToArray(array, target, data, null);
    }

    /**
     * 주어진 배열의 데이타 배열을 새로운 데이타로 변경한 새로운 배열을 반환합니다.
     * 
     * @param <T>
     *            배열 요소의 타입
     * @param array
     *            원본 배열
     * @param target
     *            변경될 대상 데이타 배열
     * @param data
     *            새로운 데이타
     * @return 치환이 완료된 새로운 배열
     * @throws NullPointerException
     *             파라미터({@code array} 또는 {@code target})가 {@code null}인 경우 발생.
     * @since 2012. 03. 30.
     */
    public static <T extends @Nullable Object> T[] replaceAll(T[] array, T[] target, T data) {
        return ArrayUtils.<T> replaceAllToData(array, target, data, null);
    }

    /**
     * 주어진 배열의 데이타 배열을 새로운 데이타 배열로 변경한 새로운 배열을 반환합니다.
     * 
     * @param <T>
     *            배열 요소의 타입
     * @param array
     *            원본 배열
     * @param target
     *            변경될 대상 데이타 배열
     * @param data
     *            새로운 데이타 배열
     * @return 치환이 완료된 새로운 배열
     * @throws NullPointerException
     *             파라미터({@code array}, {@code target} 또는 {@code data})가 {@code null}인 경우 발생.
     * @since 2012. 03. 30.
     */
    public static <T extends @Nullable Object> T[] replaceAll(T[] array, T[] target, T[] data) {
        return ArrayUtils.<T> replaceAll(array, target, data, null);
    }

    /**
     * 주어진 배열의 데이타 배열을 새로운 데이타 배열로 변경한 새로운 배열을 반환합니다.
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 03. 30.    parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.    parkjunhong77@gmail.com     조기 반환(Early Return) 적용 및 들여쓰기 감소
     * </pre>
     * 
     * @param <T>
     *            배열 요소의 타입
     * @param array
     *            원본 배열
     * @param target
     *            변경될 대상 데이타 배열
     * @param data
     *            새로운 데이타 배열
     * @param equivalent
     *            배열 구간의 동등성을 판단하는 비교자
     * @return 치환이 완료된 새로운 배열
     * @throws NullPointerException
     *             파라미터({@code array}, {@code target} 또는 {@code data})가 {@code null}인 경우 발생.
     * @since 2012. 03. 30.
     */
    @SuppressWarnings("null")
    public static <T extends @Nullable Object> T[] replaceAll(T[] array, T[] target, T[] data, @Nullable IEquivalent<T[]> equivalent) {
        ObjectUtils.requireNonNulls(array, target, data);

        int[] matchingIndice = indiceOfArray(array, target, equivalent);

        // [아키텍처 개선] 매칭되는 값이 없으면 로직 없이 바로 원본 배열 return (else 제거)
        if (matchingIndice.length < 2) {
            return array;
        }

        // else 없이 바로 메인 로직 전개 (들여쓰기 1단계 감소)
        int matchCount = matchingIndice.length / 2;
        int newLength = array.length + matchCount * (data.length - target.length);

        T[] newArray = (T[]) Array.newInstance(array.getClass().getComponentType(), newLength);

        int srcPos = 0;
        int desPos = 0;
        int dataLength = data.length;

        for (int i = 0; i < matchingIndice.length - 1; i += 2) {
            int copiedLength = matchingIndice[i] - srcPos;
            System.arraycopy(array, srcPos, newArray, desPos, copiedLength);
            srcPos = matchingIndice[i + 1] + 1;
            desPos += copiedLength;

            System.arraycopy(data, 0, newArray, desPos, dataLength);
            desPos += dataLength;
        }

        if (srcPos < array.length) {
            System.arraycopy(array, srcPos, newArray, desPos, array.length - srcPos);
        }

        return newArray;
    }

    /**
     * 주어진 배열의 데이타를 새로운 데이타 배열로 변경한 새로운 배열을 반환합니다.
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 03. 30.    parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.    parkjunhong77@gmail.com     조기 반환(Early Return) 적용 및 예외 처리 단일화
     * </pre>
     * 
     * @param <T>
     *            배열 요소의 타입
     * @param array
     *            원본 배열
     * @param target
     *            변경될 대상 데이타
     * @param data
     *            새로운 데이타 배열
     * @param equivalent
     *            {@code T}가 동일한지를 판단하는 비교자
     * @return 치환이 완료된 새로운 배열
     * @throws NullPointerException
     *             파라미터({@code array} 또는 {@code data})가 {@code null}인 경우 발생.
     * @since 2012. 03. 30.
     */
    // 아래 내용에 적용됨.
    // - return newArray;
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static <T extends @Nullable Object> T[] replaceAllToArray(T[] array, T target, T[] data, @Nullable IEquivalent<T> equivalent) {
        ObjectUtils.requireNonNulls(array, data);

        int[] indice = indiceOf(array, target, equivalent);

        // [아키텍처 개선] 로직 없이 바로 return 하여 들여쓰기 감소
        if (indice.length < 1) {
            return array;
        }

        int newLength = array.length + indice.length * (data.length - 1);
        T[] newArray = (T[]) Array.newInstance(array.getClass().getComponentType(), newLength);

        int srcPos = 0;
        int desPos = 0;
        int dataLength = data.length;

        for (int i = 0; i < indice.length; i++) {
            int copiedLength = indice[i] - srcPos;
            System.arraycopy(array, srcPos, newArray, desPos, copiedLength);
            srcPos = indice[i] + 1;
            desPos += copiedLength;

            System.arraycopy(data, 0, newArray, desPos, dataLength);
            desPos += dataLength;
        }

        if (srcPos < array.length) {
            System.arraycopy(array, srcPos, newArray, desPos, array.length - srcPos);
        }

        return newArray;
    }

    /**
     * 주어진 배열의 데이타 배열을 새로운 데이타로 변경한 새로운 배열을 반환합니다.
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 03. 30.    parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.    parkjunhong77@gmail.com     단일 데이터를 임시 배열로 래핑하여 마스터 메소드로 위임(Delegation)
     * </pre>
     * 
     * @param <T>
     *            배열 요소의 타입
     * @param array
     *            원본 배열
     * @param target
     *            변경될 대상 데이타 배열
     * @param data
     *            새로운 데이타
     * @param equivalent
     *            배열 구간의 동등성을 판단하는 비교자
     * @return 치환이 완료된 새로운 배열
     * @throws NullPointerException
     *             파라미터({@code array} 또는 {@code target})가 {@code null}인 경우 발생.
     * @since 2012. 03. 30.
     */
    public static <T extends @Nullable Object> T[] replaceAllToData(T[] array, T[] target, T data, @Nullable IEquivalent<T[]> equivalent) {
        ObjectUtils.requireNonNulls(array, target);

        T[] dataArray = (T[]) new Object[] { data };

        return ArrayUtils.<T> replaceAll(array, target, dataArray, equivalent);
    }

    /**
     * {@code boolean} 데이타를 포함하고 있는 배열의 순서를 역순으로 변경한 후 새로운 객체로 반환합니다.
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 02. 22.    parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.    parkjunhong77@gmail.com     가드 클로즈 적용 및 루프 최적화
     * </pre>
     * 
     * @param array
     *            원본 배열
     * @return 순서가 역순으로 변경된 새로운 배열
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * @since 2012. 02. 22.
     */
    public static boolean[] reverse(boolean[] array) {
        // [1] 가드 클로즈: 로직 없이 바로 예외 발생 또는 빈 배열 처리
        Objects.requireNonNull(array, "A parameter(boolean[] array) must not be 'null'.");

        // [2] 빈 배열인 경우 로직 수행 없이 바로 빈 배열 반환
        if (array.length == 0) {
            return new boolean[0];
        }

        boolean[] reversed = new boolean[array.length];

        // [3] 인덱스 포인터 최적화: 뺄셈 연산 최소화
        for (int src = 0, dest = array.length - 1; src < array.length; src++, dest--) {
            reversed[dest] = array[src];
        }

        return reversed;
    }

    /**
     * {@code byte} 데이타를 포함하고 있는 배열의 순서를 역순으로 변경한 후 새로운 객체로 반환합니다.
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 02. 22.    parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.    parkjunhong77@gmail.com     가드 클로즈 적용 및 루프 최적화
     * </pre>
     * 
     * @param array
     *            원본 배열
     * @return 순서가 역순으로 변경된 새로운 배열
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * @since 2012. 02. 22.
     */
    public static byte[] reverse(byte[] array) {
        // [1] 가드 클로즈: 로직 없이 바로 예외 발생 또는 빈 배열 처리
        Objects.requireNonNull(array, "A parameter(byte[] array) must not be 'null'.");

        // [2] 빈 배열인 경우 로직 수행 없이 바로 빈 배열 반환
        if (array.length == 0) {
            return new byte[0];
        }

        byte[] reversed = new byte[array.length];

        // [3] 인덱스 포인터 최적화: 뺄셈 연산 최소화
        for (int src = 0, dest = array.length - 1; src < array.length; src++, dest--) {
            reversed[dest] = array[src];
        }

        return reversed;
    }

    /**
     * {@code char} 데이타를 포함하고 있는 배열의 순서를 역순으로 변경한 후 새로운 객체로 반환합니다.
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 02. 22.    parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.    parkjunhong77@gmail.com     가드 클로즈 적용 및 루프 최적화
     * </pre>
     * 
     * @param array
     *            원본 배열
     * @return 순서가 역순으로 변경된 새로운 배열
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * @since 2012. 02. 22.
     */
    public static char[] reverse(char[] array) {
        // [1] 가드 클로즈: 로직 없이 바로 예외 발생 또는 빈 배열 처리
        Objects.requireNonNull(array, "A parameter(char[] array) must not be 'null'.");

        // [2] 빈 배열인 경우 로직 수행 없이 바로 빈 배열 반환
        if (array.length == 0) {
            return new char[0];
        }

        char[] reversed = new char[array.length];

        // [3] 인덱스 포인터 최적화: 뺄셈 연산 최소화
        for (int src = 0, dest = array.length - 1; src < array.length; src++, dest--) {
            reversed[dest] = array[src];
        }

        return reversed;
    }

    /**
     * {@code double} 데이타를 포함하고 있는 배열의 순서를 역순으로 변경한 후 새로운 객체로 반환합니다.
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 02. 22.    parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.    parkjunhong77@gmail.com     가드 클로즈 적용 및 루프 최적화
     * </pre>
     * 
     * @param array
     *            원본 배열
     * @return 순서가 역순으로 변경된 새로운 배열
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * @since 2012. 02. 22.
     */
    public static double[] reverse(double[] array) {
        // [1] 가드 클로즈: 로직 없이 바로 예외 발생 또는 빈 배열 처리
        Objects.requireNonNull(array, "A parameter(double[] array) must not be 'null'.");

        // [2] 빈 배열인 경우 로직 수행 없이 바로 빈 배열 반환
        if (array.length == 0) {
            return new double[0];
        }

        double[] reversed = new double[array.length];

        // [3] 인덱스 포인터 최적화: 뺄셈 연산 최소화
        for (int src = 0, dest = array.length - 1; src < array.length; src++, dest--) {
            reversed[dest] = array[src];
        }

        return reversed;
    }

    /**
     * {@code float} 데이타를 포함하고 있는 배열의 순서를 역순으로 변경한 후 새로운 객체로 반환합니다.
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 02. 22.    parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.    parkjunhong77@gmail.com     가드 클로즈 적용 및 루프 최적화
     * </pre>
     * 
     * @param array
     *            원본 배열
     * @return 순서가 역순으로 변경된 새로운 배열
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * @since 2012. 02. 22.
     */
    public static float[] reverse(float[] array) {
        // [1] 가드 클로즈: 로직 없이 바로 예외 발생 또는 빈 배열 처리
        Objects.requireNonNull(array, "A parameter(float[] array) must not be 'null'.");

        // [2] 빈 배열인 경우 로직 수행 없이 바로 빈 배열 반환
        if (array.length == 0) {
            return new float[0];
        }

        float[] reversed = new float[array.length];

        // [3] 인덱스 포인터 최적화: 뺄셈 연산 최소화
        for (int src = 0, dest = array.length - 1; src < array.length; src++, dest--) {
            reversed[dest] = array[src];
        }

        return reversed;
    }

    /**
     * {@code int} 데이타를 포함하고 있는 배열의 순서를 역순으로 변경한 후 새로운 객체로 반환합니다.
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 02. 22.    parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.    parkjunhong77@gmail.com     가드 클로즈 적용 및 루프 최적화
     * </pre>
     * 
     * @param array
     *            원본 배열
     * @return 순서가 역순으로 변경된 새로운 배열
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * @since 2012. 02. 22.
     */
    public static int[] reverse(int[] array) {
        // [1] 가드 클로즈: 로직 없이 바로 예외 발생 또는 빈 배열 처리
        Objects.requireNonNull(array, "A parameter(int[] array) must not be 'null'.");

        // [2] 빈 배열인 경우 로직 수행 없이 바로 빈 배열 반환
        if (array.length == 0) {
            return new int[0];
        }

        int[] reversed = new int[array.length];

        // [3] 인덱스 포인터 최적화: 뺄셈 연산 최소화
        for (int src = 0, dest = array.length - 1; src < array.length; src++, dest--) {
            reversed[dest] = array[src];
        }

        return reversed;
    }

    /**
     * {@code long} 데이타를 포함하고 있는 배열의 순서를 역순으로 변경한 후 새로운 객체로 반환합니다.
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 02. 22.    parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.    parkjunhong77@gmail.com     가드 클로즈 적용 및 루프 최적화
     * </pre>
     * 
     * @param array
     *            원본 배열
     * @return 순서가 역순으로 변경된 새로운 배열
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * @since 2012. 02. 22.
     */
    public static long[] reverse(long[] array) {
        // [1] 가드 클로즈: 로직 없이 바로 예외 발생 또는 빈 배열 처리
        Objects.requireNonNull(array, "A parameter(long[] array) must not be 'null'.");

        // [2] 빈 배열인 경우 로직 수행 없이 바로 빈 배열 반환
        if (array.length == 0) {
            return new long[0];
        }

        long[] reversed = new long[array.length];

        // [3] 인덱스 포인터 최적화: 뺄셈 연산 최소화
        for (int src = 0, dest = array.length - 1; src < array.length; src++, dest--) {
            reversed[dest] = array[src];
        }

        return reversed;
    }

    /**
     * {@code short} 데이타를 포함하고 있는 배열의 순서를 역순으로 변경한 후 새로운 객체로 반환합니다.
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 02. 22.    parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.    parkjunhong77@gmail.com     가드 클로즈 적용 및 루프 최적화
     * </pre>
     * 
     * @param array
     *            원본 배열
     * @return 순서가 역순으로 변경된 새로운 배열
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * @since 2012. 02. 22.
     */
    public static short[] reverse(short[] array) {
        // [1] 가드 클로즈: 로직 없이 바로 예외 발생 또는 빈 배열 처리
        Objects.requireNonNull(array, "A parameter(short[] array) must not be 'null'.");

        // [2] 빈 배열인 경우 로직 수행 없이 바로 빈 배열 반환
        if (array.length == 0) {
            return new short[0];
        }

        short[] reversed = new short[array.length];

        // [3] 인덱스 포인터 최적화: 뺄셈 연산 최소화
        for (int src = 0, dest = array.length - 1; src < array.length; src++, dest--) {
            reversed[dest] = array[src];
        }

        return reversed;
    }

    /**
     * 배열의 순서를 역순으로 변경한 후 새로운 배열로 반환합니다.
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 02. 22.    parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.    parkjunhong77@gmail.com     가드 클로즈 적용 및 루프 최적화
     * </pre>
     * 
     * @param <T>
     *            배열 요소의 타입
     * @param array
     *            원본 배열
     * @return 순서가 역순으로 변경된 새로운 배열
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * @since 2012. 02. 22.
     */
    // 아래 내용에 적용됨.
    // - return reversed;
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static <T extends @Nullable Object> T[] reverse(T[] array) {
        Objects.requireNonNull(array, "A parameter(T[] array) must not be 'null'.");

        T[] reversed = (T[]) Array.newInstance(array.getClass().getComponentType(), array.length);

        for (int src = 0, dest = array.length - 1; src < array.length; src++, dest--) {
            reversed[dest] = array[src];
        }

        return reversed;
    }

    /**
     * {@code boolean} 데이타를 포함하고 있는 배열의 순서를 역순으로 변경합니다.<br>
     * 새로운 배열을 생성하지 않고 원본 배열의 요소를 직접 교환(Swap)하여 메모리 효율을 극대화합니다.
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2026. 03. 26.    parkjunhong77@gmail.com     최초 작성 (In-Place 방식 적용)
     * </pre>
     * 
     * @param array
     *            원본 배열 (메소드 실행 후 내부 요소의 순서가 변경됨)
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * @since 2026. 03. 26.
     */
    public static void reverseInPlace(boolean[] array) {
        // [1] 가드 클로즈: null 체크 및 조기 반환
        Objects.requireNonNull(array, "A parameter(boolean[] array) must not be 'null'.");

        // 요소가 0개 또는 1개인 경우 뒤집을 필요가 없으므로 즉시 종료
        if (array.length < 2) {
            return;
        }

        // [2] 양 끝에서부터 중앙으로 오며 Swap 수행
        // 배열 길이의 절반(length / 2)까지만 반복하면 모든 요소의 교환이 완료됩니다.
        int left = 0;
        int right = array.length - 1;
        boolean temp;

        while (left < right) {
            // 요소 교환 (Swap)
            temp = array[left];
            array[left] = array[right];
            array[right] = temp;

            // 포인터 이동
            left++;
            right--;
        }
    }

    /**
     * {@code byte} 데이타를 포함하고 있는 배열의 순서를 역순으로 변경합니다.<br>
     * 새로운 배열을 생성하지 않고 원본 배열의 요소를 직접 교환(Swap)하여 메모리 효율을 극대화합니다.
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2026. 03. 26.    parkjunhong77@gmail.com     최초 작성 (In-Place 방식 적용)
     * </pre>
     * 
     * @param array
     *            원본 배열 (메소드 실행 후 내부 요소의 순서가 변경됨)
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * @since 2026. 03. 26.
     */
    public static void reverseInPlace(byte[] array) {
        // [1] 가드 클로즈: null 체크 및 조기 반환
        Objects.requireNonNull(array, "A parameter(byte[] array) must not be 'null'.");

        // 요소가 0개 또는 1개인 경우 뒤집을 필요가 없으므로 즉시 종료
        if (array.length < 2) {
            return;
        }

        // [2] 양 끝에서부터 중앙으로 오며 Swap 수행
        // 배열 길이의 절반(length / 2)까지만 반복하면 모든 요소의 교환이 완료됩니다.
        int left = 0;
        int right = array.length - 1;
        byte temp;

        while (left < right) {
            // 요소 교환 (Swap)
            temp = array[left];
            array[left] = array[right];
            array[right] = temp;

            // 포인터 이동
            left++;
            right--;
        }
    }

    /**
     * {@code char} 데이타를 포함하고 있는 배열의 순서를 역순으로 변경합니다.<br>
     * 새로운 배열을 생성하지 않고 원본 배열의 요소를 직접 교환(Swap)하여 메모리 효율을 극대화합니다.
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2026. 03. 26.    parkjunhong77@gmail.com     최초 작성 (In-Place 방식 적용)
     * </pre>
     * 
     * @param array
     *            원본 배열 (메소드 실행 후 내부 요소의 순서가 변경됨)
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * @since 2026. 03. 26.
     */
    public static void reverseInPlace(char[] array) {
        // [1] 가드 클로즈: null 체크 및 조기 반환
        Objects.requireNonNull(array, "A parameter(char[] array) must not be 'null'.");

        // 요소가 0개 또는 1개인 경우 뒤집을 필요가 없으므로 즉시 종료
        if (array.length < 2) {
            return;
        }

        // [2] 양 끝에서부터 중앙으로 오며 Swap 수행
        // 배열 길이의 절반(length / 2)까지만 반복하면 모든 요소의 교환이 완료됩니다.
        int left = 0;
        int right = array.length - 1;
        char temp;

        while (left < right) {
            // 요소 교환 (Swap)
            temp = array[left];
            array[left] = array[right];
            array[right] = temp;

            // 포인터 이동
            left++;
            right--;
        }
    }

    /**
     * {@code double} 데이타를 포함하고 있는 배열의 순서를 역순으로 변경합니다.<br>
     * 새로운 배열을 생성하지 않고 원본 배열의 요소를 직접 교환(Swap)하여 메모리 효율을 극대화합니다.
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2026. 03. 26.    parkjunhong77@gmail.com     최초 작성 (In-Place 방식 적용)
     * </pre>
     * 
     * @param array
     *            원본 배열 (메소드 실행 후 내부 요소의 순서가 변경됨)
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * @since 2026. 03. 26.
     */
    public static void reverseInPlace(double[] array) {
        // [1] 가드 클로즈: null 체크 및 조기 반환
        Objects.requireNonNull(array, "A parameter(double[] array) must not be 'null'.");

        // 요소가 0개 또는 1개인 경우 뒤집을 필요가 없으므로 즉시 종료
        if (array.length < 2) {
            return;
        }

        // [2] 양 끝에서부터 중앙으로 오며 Swap 수행
        // 배열 길이의 절반(length / 2)까지만 반복하면 모든 요소의 교환이 완료됩니다.
        int left = 0;
        int right = array.length - 1;
        double temp;

        while (left < right) {
            // 요소 교환 (Swap)
            temp = array[left];
            array[left] = array[right];
            array[right] = temp;

            // 포인터 이동
            left++;
            right--;
        }
    }

    /**
     * {@code float} 데이타를 포함하고 있는 배열의 순서를 역순으로 변경합니다.<br>
     * 새로운 배열을 생성하지 않고 원본 배열의 요소를 직접 교환(Swap)하여 메모리 효율을 극대화합니다.
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2026. 03. 26.    parkjunhong77@gmail.com     최초 작성 (In-Place 방식 적용)
     * </pre>
     * 
     * @param array
     *            원본 배열 (메소드 실행 후 내부 요소의 순서가 변경됨)
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * @since 2026. 03. 26.
     */
    public static void reverseInPlace(float[] array) {
        // [1] 가드 클로즈: null 체크 및 조기 반환
        Objects.requireNonNull(array, "A parameter(float[] array) must not be 'null'.");

        // 요소가 0개 또는 1개인 경우 뒤집을 필요가 없으므로 즉시 종료
        if (array.length < 2) {
            return;
        }

        // [2] 양 끝에서부터 중앙으로 오며 Swap 수행
        // 배열 길이의 절반(length / 2)까지만 반복하면 모든 요소의 교환이 완료됩니다.
        int left = 0;
        int right = array.length - 1;
        float temp;

        while (left < right) {
            // 요소 교환 (Swap)
            temp = array[left];
            array[left] = array[right];
            array[right] = temp;

            // 포인터 이동
            left++;
            right--;
        }
    }

    /**
     * {@code int} 데이타를 포함하고 있는 배열의 순서를 역순으로 변경합니다.<br>
     * 새로운 배열을 생성하지 않고 원본 배열의 요소를 직접 교환(Swap)하여 메모리 효율을 극대화합니다.
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2026. 03. 26.    parkjunhong77@gmail.com     최초 작성 (In-Place 방식 적용)
     * </pre>
     * 
     * @param array
     *            원본 배열 (메소드 실행 후 내부 요소의 순서가 변경됨)
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * @since 2026. 03. 26.
     */
    public static void reverseInPlace(int[] array) {
        // [1] 가드 클로즈: null 체크 및 조기 반환
        Objects.requireNonNull(array, "A parameter(int[] array) must not be 'null'.");

        // 요소가 0개 또는 1개인 경우 뒤집을 필요가 없으므로 즉시 종료
        if (array.length < 2) {
            return;
        }

        // [2] 양 끝에서부터 중앙으로 오며 Swap 수행
        // 배열 길이의 절반(length / 2)까지만 반복하면 모든 요소의 교환이 완료됩니다.
        int left = 0;
        int right = array.length - 1;
        int temp;

        while (left < right) {
            // 요소 교환 (Swap)
            temp = array[left];
            array[left] = array[right];
            array[right] = temp;

            // 포인터 이동
            left++;
            right--;
        }
    }

    /**
     * {@code long} 데이타를 포함하고 있는 배열의 순서를 역순으로 변경합니다.<br>
     * 새로운 배열을 생성하지 않고 원본 배열의 요소를 직접 교환(Swap)하여 메모리 효율을 극대화합니다.
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2026. 03. 26.    parkjunhong77@gmail.com     최초 작성 (In-Place 방식 적용)
     * </pre>
     * 
     * @param array
     *            원본 배열 (메소드 실행 후 내부 요소의 순서가 변경됨)
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * @since 2026. 03. 26.
     */
    public static void reverseInPlace(long[] array) {
        // [1] 가드 클로즈: null 체크 및 조기 반환
        Objects.requireNonNull(array, "A parameter(long[] array) must not be 'null'.");

        // 요소가 0개 또는 1개인 경우 뒤집을 필요가 없으므로 즉시 종료
        if (array.length < 2) {
            return;
        }

        // [2] 양 끝에서부터 중앙으로 오며 Swap 수행
        // 배열 길이의 절반(length / 2)까지만 반복하면 모든 요소의 교환이 완료됩니다.
        int left = 0;
        int right = array.length - 1;
        long temp;

        while (left < right) {
            // 요소 교환 (Swap)
            temp = array[left];
            array[left] = array[right];
            array[right] = temp;

            // 포인터 이동
            left++;
            right--;
        }
    }

    /**
     * {@code short} 데이타를 포함하고 있는 배열의 순서를 역순으로 변경합니다.<br>
     * 새로운 배열을 생성하지 않고 원본 배열의 요소를 직접 교환(Swap)하여 메모리 효율을 극대화합니다.
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2026. 03. 26.    parkjunhong77@gmail.com     최초 작성 (In-Place 방식 적용)
     * </pre>
     * 
     * @param array
     *            원본 배열 (메소드 실행 후 내부 요소의 순서가 변경됨)
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * @since 2026. 03. 26.
     */
    public static void reverseInPlace(short[] array) {
        // [1] 가드 클로즈: null 체크 및 조기 반환
        Objects.requireNonNull(array, "A parameter(short[] array) must not be 'null'.");

        // 요소가 0개 또는 1개인 경우 뒤집을 필요가 없으므로 즉시 종료
        if (array.length < 2) {
            return;
        }

        // [2] 양 끝에서부터 중앙으로 오며 Swap 수행
        // 배열 길이의 절반(length / 2)까지만 반복하면 모든 요소의 교환이 완료됩니다.
        int left = 0;
        int right = array.length - 1;
        short temp;

        while (left < right) {
            // 요소 교환 (Swap)
            temp = array[left];
            array[left] = array[right];
            array[right] = temp;

            // 포인터 이동
            left++;
            right--;
        }
    }

    /**
     * 배열의 순서를 역순으로 변경합니다.<br>
     * 새로운 배열을 생성하지 않고 원본 배열의 요소를 직접 교환(Swap)하여 메모리 효율을 극대화합니다.
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2026. 03. 26.    parkjunhong77@gmail.com     최초 작성 (In-Place 방식 적용)
     * </pre>
     * 
     * @param <T>
     *            배열 요소의 타입
     * @param array
     *            원본 배열 (메소드 실행 후 내부 요소의 순서가 변경됨)
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * @since 2026. 03. 26.
     */
    public static <T extends @Nullable Object> void reverseInPlace(T[] array) {
        Objects.requireNonNull(array, "A parameter(T[] array) must not be 'null'.");

        if (array.length < 2) {
            return;
        }

        int left = 0;
        int right = array.length - 1;
        T temp;

        while (left < right) {
            temp = array[left];
            array[left] = array[right];
            array[right] = temp;
            left++;
            right--;
        }
    }

    /**
     * 배열을 주어진 값({@code value})으로 구분하여 분할된 2차원 배열을 반환합니다.
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 03. 20.    parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.    parkjunhong77@gmail.com     단일 패스 로직으로 최적화 및 가드 클로즈 적용
     * </pre>
     * 
     * @param array
     *            원본 배열
     * @param value
     *            구분자로 사용할 값
     * @return 주어진 값으로 구분된 2차원 배열. 모든 요소가 구분자와 일치하는 경우 길이가 0인 배열을 반환합니다.
     *
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * @since 2012. 03. 20.
     */
    // 아래 내용에 적용됨.
    // - return splitedList.toArray(new boolean[splitedList.size()][]);
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static boolean[][] split(boolean[] array, boolean value) {
        // [1] 가드 클로즈
        Objects.requireNonNull(array, "A parameter(boolean[] array) must not be 'null'.");

        if (array.length == 0) {
            return new boolean[0][];
        }

        // 분할된 배열들을 임시로 담을 리스트 (최대 개수는 array.length를 넘지 않음)
        List<boolean[]> splitedList = new ArrayList<>(array.length);

        int startPos = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                // 구분자를 만났을 때, 이전까지의 구간이 유효(길이가 1 이상)하면 추가
                if (i > startPos) {
                    splitedList.add(Arrays.copyOfRange(array, startPos, i));
                }
                startPos = i + 1;
            }
        }

        // [2] 마지막 남은 구간 처리
        if (startPos < array.length) {
            splitedList.add(Arrays.copyOfRange(array, startPos, array.length));
        }

        // [3] 모든 값이 구분자와 동일하여 리스트가 비어있는 경우
        if (splitedList.isEmpty()) {
            // 원본 배열이 모두 구분자라면 빈 2차원 배열 반환,
            // 만약 원본에 데이터가 하나도 없었는데 여기까지 왔다면(length=0 케이스는 위에서 처리됨) 로직 유지
            return new boolean[0][];
        }

        // [4] 결과 반환
        return splitedList.toArray(new boolean[splitedList.size()][]);
    }

    /**
     * 배열을 주어진 값({@code value})으로 구분하여 분할된 2차원 배열을 반환합니다.
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 03. 20.    parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.    parkjunhong77@gmail.com     단일 패스 로직으로 최적화 및 가드 클로즈 적용
     * </pre>
     * 
     * @param array
     *            원본 배열
     * @param value
     *            구분자로 사용할 값
     * @return 주어진 값으로 구분된 2차원 배열. 모든 요소가 구분자와 일치하는 경우 길이가 0인 배열을 반환합니다.
     *
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * @since 2012. 03. 20.
     */
    // 아래 내용에 적용됨.
    // - return splitedList.toArray(new byte[splitedList.size()][]);
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static byte[][] split(byte[] array, byte value) {
        // [1] 가드 클로즈
        Objects.requireNonNull(array, "A parameter(byte[] array) must not be 'null'.");

        if (array.length == 0) {
            return new byte[0][];
        }

        // 분할된 배열들을 임시로 담을 리스트 (최대 개수는 array.length를 넘지 않음)
        List<byte[]> splitedList = new ArrayList<>(array.length);

        int startPos = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                // 구분자를 만났을 때, 이전까지의 구간이 유효(길이가 1 이상)하면 추가
                if (i > startPos) {
                    splitedList.add(Arrays.copyOfRange(array, startPos, i));
                }
                startPos = i + 1;
            }
        }

        // [2] 마지막 남은 구간 처리
        if (startPos < array.length) {
            splitedList.add(Arrays.copyOfRange(array, startPos, array.length));
        }

        // [3] 모든 값이 구분자와 동일하여 리스트가 비어있는 경우
        if (splitedList.isEmpty()) {
            // 원본 배열이 모두 구분자라면 빈 2차원 배열 반환,
            // 만약 원본에 데이터가 하나도 없었는데 여기까지 왔다면(length=0 케이스는 위에서 처리됨) 로직 유지
            return new byte[0][];
        }

        // [4] 결과 반환
        return splitedList.toArray(new byte[splitedList.size()][]);
    }

    /**
     * 배열을 주어진 값({@code value})으로 구분하여 분할된 2차원 배열을 반환합니다.
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 03. 20.    parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.    parkjunhong77@gmail.com     단일 패스 로직으로 최적화 및 가드 클로즈 적용
     * </pre>
     * 
     * @param array
     *            원본 배열
     * @param value
     *            구분자로 사용할 값
     * @return 주어진 값으로 구분된 2차원 배열. 모든 요소가 구분자와 일치하는 경우 길이가 0인 배열을 반환합니다.
     *
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * @since 2012. 03. 20.
     */

    // 아래 내용에 적용됨.
    // - return splitedList.toArray(new char[splitedList.size()][]);
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static char[][] split(char[] array, char value) {
        // [1] 가드 클로즈
        Objects.requireNonNull(array, "A parameter(char[] array) must not be 'null'.");

        if (array.length == 0) {
            return new char[0][];
        }

        // 분할된 배열들을 임시로 담을 리스트 (최대 개수는 array.length를 넘지 않음)
        List<char[]> splitedList = new ArrayList<>(array.length);

        int startPos = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                // 구분자를 만났을 때, 이전까지의 구간이 유효(길이가 1 이상)하면 추가
                if (i > startPos) {
                    splitedList.add(Arrays.copyOfRange(array, startPos, i));
                }
                startPos = i + 1;
            }
        }

        // [2] 마지막 남은 구간 처리
        if (startPos < array.length) {
            splitedList.add(Arrays.copyOfRange(array, startPos, array.length));
        }

        // [3] 모든 값이 구분자와 동일하여 리스트가 비어있는 경우
        if (splitedList.isEmpty()) {
            // 원본 배열이 모두 구분자라면 빈 2차원 배열 반환,
            // 만약 원본에 데이터가 하나도 없었는데 여기까지 왔다면(length=0 케이스는 위에서 처리됨) 로직 유지
            return new char[0][];
        }

        // [4] 결과 반환
        return splitedList.toArray(new char[splitedList.size()][]);
    }

    /**
     * 배열을 주어진 값({@code value})으로 구분하여 분할된 2차원 배열을 반환합니다.
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 03. 20.    parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.    parkjunhong77@gmail.com     단일 패스 로직으로 최적화 및 가드 클로즈 적용
     * </pre>
     * 
     * @param array
     *            원본 배열
     * @param value
     *            구분자로 사용할 값
     * @return 주어진 값으로 구분된 2차원 배열. 모든 요소가 구분자와 일치하는 경우 길이가 0인 배열을 반환합니다.
     *
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * @since 2012. 03. 20.
     */

    // 아래 내용에 적용됨.
    // - return splitedList.toArray(new double[splitedList.size()][]);
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static double[][] split(double[] array, double value) {
        // [1] 가드 클로즈
        Objects.requireNonNull(array, "A parameter(double[] array) must not be 'null'.");

        if (array.length == 0) {
            return new double[0][];
        }

        // 분할된 배열들을 임시로 담을 리스트 (최대 개수는 array.length를 넘지 않음)
        List<double[]> splitedList = new ArrayList<>(array.length);

        int startPos = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                // 구분자를 만났을 때, 이전까지의 구간이 유효(길이가 1 이상)하면 추가
                if (i > startPos) {
                    splitedList.add(Arrays.copyOfRange(array, startPos, i));
                }
                startPos = i + 1;
            }
        }

        // [2] 마지막 남은 구간 처리
        if (startPos < array.length) {
            splitedList.add(Arrays.copyOfRange(array, startPos, array.length));
        }

        // [3] 모든 값이 구분자와 동일하여 리스트가 비어있는 경우
        if (splitedList.isEmpty()) {
            // 원본 배열이 모두 구분자라면 빈 2차원 배열 반환,
            // 만약 원본에 데이터가 하나도 없었는데 여기까지 왔다면(length=0 케이스는 위에서 처리됨) 로직 유지
            return new double[0][];
        }

        // [4] 결과 반환
        return splitedList.toArray(new double[splitedList.size()][]);
    }

    /**
     * 배열을 주어진 값({@code value})으로 구분하여 분할된 2차원 배열을 반환합니다.
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 03. 20.    parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.    parkjunhong77@gmail.com     단일 패스 로직으로 최적화 및 가드 클로즈 적용
     * </pre>
     * 
     * @param array
     *            원본 배열
     * @param value
     *            구분자로 사용할 값
     * @return 주어진 값으로 구분된 2차원 배열. 모든 요소가 구분자와 일치하는 경우 길이가 0인 배열을 반환합니다.
     *
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * @since 2012. 03. 20.
     */
    // 아래 내용에 적용됨.
    // - return splitedList.toArray(new float[splitedList.size()][]);
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static float[][] split(float[] array, float value) {
        // [1] 가드 클로즈
        Objects.requireNonNull(array, "A parameter(float[] array) must not be 'null'.");

        if (array.length == 0) {
            return new float[0][];
        }

        // 분할된 배열들을 임시로 담을 리스트 (최대 개수는 array.length를 넘지 않음)
        List<float[]> splitedList = new ArrayList<>(array.length);

        int startPos = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                // 구분자를 만났을 때, 이전까지의 구간이 유효(길이가 1 이상)하면 추가
                if (i > startPos) {
                    splitedList.add(Arrays.copyOfRange(array, startPos, i));
                }
                startPos = i + 1;
            }
        }

        // [2] 마지막 남은 구간 처리
        if (startPos < array.length) {
            splitedList.add(Arrays.copyOfRange(array, startPos, array.length));
        }

        // [3] 모든 값이 구분자와 동일하여 리스트가 비어있는 경우
        if (splitedList.isEmpty()) {
            // 원본 배열이 모두 구분자라면 빈 2차원 배열 반환,
            // 만약 원본에 데이터가 하나도 없었는데 여기까지 왔다면(length=0 케이스는 위에서 처리됨) 로직 유지
            return new float[0][];
        }

        // [4] 결과 반환
        return splitedList.toArray(new float[splitedList.size()][]);
    }

    /**
     * 배열을 주어진 값({@code value})으로 구분하여 분할된 2차원 배열을 반환합니다.
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 03. 20.    parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.    parkjunhong77@gmail.com     단일 패스 로직으로 최적화 및 가드 클로즈 적용
     * </pre>
     * 
     * @param array
     *            원본 배열
     * @param value
     *            구분자로 사용할 값
     * @return 주어진 값으로 구분된 2차원 배열. 모든 요소가 구분자와 일치하는 경우 길이가 0인 배열을 반환합니다.
     *
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * @since 2012. 03. 20.
     */
    // 아래 내용에 적용됨.
    // - return splitedList.toArray(new int[splitedList.size()][]);
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static int[][] split(int[] array, int value) {
        // [1] 가드 클로즈
        Objects.requireNonNull(array, "A parameter(int[] array) must not be 'null'.");

        if (array.length == 0) {
            return new int[0][];
        }

        // 분할된 배열들을 임시로 담을 리스트 (최대 개수는 array.length를 넘지 않음)
        List<int[]> splitedList = new ArrayList<>(array.length);

        int startPos = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                // 구분자를 만났을 때, 이전까지의 구간이 유효(길이가 1 이상)하면 추가
                if (i > startPos) {
                    splitedList.add(Arrays.copyOfRange(array, startPos, i));
                }
                startPos = i + 1;
            }
        }

        // [2] 마지막 남은 구간 처리
        if (startPos < array.length) {
            splitedList.add(Arrays.copyOfRange(array, startPos, array.length));
        }

        // [3] 모든 값이 구분자와 동일하여 리스트가 비어있는 경우
        if (splitedList.isEmpty()) {
            // 원본 배열이 모두 구분자라면 빈 2차원 배열 반환,
            // 만약 원본에 데이터가 하나도 없었는데 여기까지 왔다면(length=0 케이스는 위에서 처리됨) 로직 유지
            return new int[0][];
        }

        // [4] 결과 반환
        return splitedList.toArray(new int[splitedList.size()][]);
    }

    /**
     * 배열을 주어진 값({@code value})으로 구분하여 분할된 2차원 배열을 반환합니다.
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 03. 20.    parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.    parkjunhong77@gmail.com     단일 패스 로직으로 최적화 및 가드 클로즈 적용
     * </pre>
     * 
     * @param array
     *            원본 배열
     * @param value
     *            구분자로 사용할 값
     * @return 주어진 값으로 구분된 2차원 배열. 모든 요소가 구분자와 일치하는 경우 길이가 0인 배열을 반환합니다.
     *
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * @since 2012. 03. 20.
     */
    // 아래 내용에 적용됨.
    // - return splitedList.toArray(new long[splitedList.size()][]);
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static long[][] split(long[] array, long value) {
        // [1] 가드 클로즈
        Objects.requireNonNull(array, "A parameter(long[] array) must not be 'null'.");

        if (array.length == 0) {
            return new long[0][];
        }

        // 분할된 배열들을 임시로 담을 리스트 (최대 개수는 array.length를 넘지 않음)
        List<long[]> splitedList = new ArrayList<>(array.length);

        int startPos = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                // 구분자를 만났을 때, 이전까지의 구간이 유효(길이가 1 이상)하면 추가
                if (i > startPos) {
                    splitedList.add(Arrays.copyOfRange(array, startPos, i));
                }
                startPos = i + 1;
            }
        }

        // [2] 마지막 남은 구간 처리
        if (startPos < array.length) {
            splitedList.add(Arrays.copyOfRange(array, startPos, array.length));
        }

        // [3] 모든 값이 구분자와 동일하여 리스트가 비어있는 경우
        if (splitedList.isEmpty()) {
            // 원본 배열이 모두 구분자라면 빈 2차원 배열 반환,
            // 만약 원본에 데이터가 하나도 없었는데 여기까지 왔다면(length=0 케이스는 위에서 처리됨) 로직 유지
            return new long[0][];
        }

        // [4] 결과 반환
        return splitedList.toArray(new long[splitedList.size()][]);
    }

    /**
     * 배열을 주어진 값({@code value})으로 구분하여 분할된 2차원 배열을 반환합니다.
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 03. 20.    parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.    parkjunhong77@gmail.com     단일 패스 로직으로 최적화 및 가드 클로즈 적용
     * </pre>
     * 
     * @param array
     *            원본 배열
     * @param value
     *            구분자로 사용할 값
     * @return 주어진 값으로 구분된 2차원 배열. 모든 요소가 구분자와 일치하는 경우 길이가 0인 배열을 반환합니다.
     *
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * @since 2012. 03. 20.
     */
    // 아래 내용에 적용됨.
    // - return splitedList.toArray(new short[splitedList.size()][]);
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static short[][] split(short[] array, short value) {
        // [1] 가드 클로즈
        Objects.requireNonNull(array, "A parameter(short[] array) must not be 'null'.");

        if (array.length == 0) {
            return new short[0][];
        }

        // 분할된 배열들을 임시로 담을 리스트 (최대 개수는 array.length를 넘지 않음)
        List<short[]> splitedList = new ArrayList<>(array.length);

        int startPos = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                // 구분자를 만났을 때, 이전까지의 구간이 유효(길이가 1 이상)하면 추가
                if (i > startPos) {
                    splitedList.add(Arrays.copyOfRange(array, startPos, i));
                }
                startPos = i + 1;
            }
        }

        // [2] 마지막 남은 구간 처리
        if (startPos < array.length) {
            splitedList.add(Arrays.copyOfRange(array, startPos, array.length));
        }

        // [3] 모든 값이 구분자와 동일하여 리스트가 비어있는 경우
        if (splitedList.isEmpty()) {
            // 원본 배열이 모두 구분자라면 빈 2차원 배열 반환,
            // 만약 원본에 데이터가 하나도 없었는데 여기까지 왔다면(length=0 케이스는 위에서 처리됨) 로직 유지
            return new short[0][];
        }

        // [4] 결과 반환
        return splitedList.toArray(new short[splitedList.size()][]);
    }

    /**
     * 배열을 주어진 값({@code value})으로 구분한 2차원 배열을 반환합니다.
     * 
     * @param <T>
     *            배열 요소의 타입
     * @param array
     *            원본 배열
     * @param value
     *            구분자로 사용되는 데이타
     * @return 주어진 값으로 구분된 배열, 모든 값이 주어진 값과 동일한 경우 길이가 0 인 배열
     * 
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * @since 2012. 03. 20.
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
     *              new int[] { 2, 3 }, new int[] { 3, 4 }, new int[] { 2, 3 }, new int[] { 23, 5 }, new int[] { 3, 2 } };
     * 
     *      IEquivalent<int[]> equi = new DefaultEquivalent<int[]>() {
     *          public boolean equals(int[] t1, int[] t2) {
     *              if( t1 != null && t2 != null ) {
     *                  if( t1.length != t2.length ) {
     *                      return false;
     *                  } else {
     *                      
     *                      int[] tt1 = copyOf(t1,t1.length);
     *                      int[] tt2 = copyOf(t2, t2.length);
     *                      Arrays.sort(tt1);
     *                      Arrays.sort(tt2);
     *                      for( int i = 0; i < t1.length; i++ ) {
     *                          if( tt1[i] != tt2[i] )
     *                              return false;
     *                      }
     *                      return true;
     *                  }
     *              } else {
     *                  return nullEquals(t1, t2);
     *              }
     *          };
     *      };
     * 
     *      for( int[][] arr : split(array, new int[] { 2, 3 }, equi) ) {
     *          for( int[] ar : arr ) {
     *              System.out.println(Arrays.toString(ar));
     *          }
     *      }
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
     * @param <T>
     *            배열 요소의 타입
     * @param array
     *            원본 배열
     * @param value
     *            구분자로 사용되는 데이타
     * @param equivalent
     *            클래스 또는 타입 T의 equals(T obj) 메소드가 아닌 다른 비교 기준을 제공하는 객체
     * @return 주어진 값으로 구분된 배열, 모든 값이 주어진 값과 동일한 경우 길이가 0 인 배열
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * @since 2012. 03. 20.
     */
    // 아래 내용에 적용됨.
    // - return splitedList.toArray(result);
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static <T> T[][] split(T[] array, T value, @Nullable IEquivalent<T> equivalent) {
        // [1] 가드 클로즈
        Objects.requireNonNull(array, "A parameter(T[] array) must not be 'null'.");

        if (array.length == 0) {
            return (T[][]) newArray(array.getClass(), 0);
        }

        // 비교자 설정
        if (equivalent == null) {
            equivalent = new DefaultEquivalent<T>();
        }

        // 분할 결과를 담을 임시 리스트
        List<T[]> splitedList = new ArrayList<>(array.length);

        int startPos = 0;
        for (int i = 0; i < array.length; i++) {
            if (equivalent.equals(array[i], value)) {
                // 구분자 이전까지의 구간이 존재하면 결과에 추가
                if (i > startPos) {
                    splitedList.add(Arrays.copyOfRange(array, startPos, i));
                }
                startPos = i + 1;
            }
        }

        // [2] 마지막 구간 처리
        if (startPos < array.length) {
            splitedList.add(Arrays.copyOfRange(array, startPos, array.length));
        }

        // [3] 모든 요소가 구분자였거나 비어있는 경우 (조기 반환)
        if (splitedList.isEmpty()) {
            return (T[][]) newArray(array.getClass(), 0);
        }

        // [4] 결과 반환 (2차원 배열 생성)
        T[][] result = (T[][]) newArray(array.getClass(), splitedList.size());
        return splitedList.toArray(result);
    }

    /**
     * 조건에 맞는 데이터 이후부터 끝까지 데이터를 새로운 배열로 제공합니다. (Exclusive)
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2021. 6. 24.    parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.    parkjunhong77@gmail.com     Predicate 적용 및 메모리 할당 최적화
     * </pre>
     * 
     * @param <T>
     *            배열 요소의 타입
     * @param array
     *            원본 배열
     * @param at
     *            조건에 맞는지 비교하는 함수 (해당 데이터는 제외됨)
     * @return 조건 일치 데이터 이후의 새로운 배열. 일치하는 데이터가 없거나 마지막 요소인 경우 빈 배열 반환.
     * 
     * @throws NullPointerException
     *             파라미터({@code array} 또는 {@code at})가 {@code null}인 경우 발생.
     * @since 2021. 6. 24.
     */
    // 아래 내용에 적용됨.
    // - return (T[]) Array.newInstance(array.getClass().getComponentType(), 0);
    // - return Arrays.copyOfRange(array, splitIdx + 1, array.length);
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static <T> T[] splitAfter(T[] array, Predicate<T> at) {
        Objects.requireNonNull(array, "A parameter(T[] array) must not be 'null'.");
        Objects.requireNonNull(at, "A parameter(Predicate<T> at) must not be 'null'.");

        int splitIdx = -1;
        for (int i = 0; i < array.length; i++) {
            if (at.test(array[i])) {
                splitIdx = i;
                break;
            }
        }

        // 일치하는 데이터가 없거나, 마지막 데이터인 경우 빈 배열 반환
        if (splitIdx < 0 || splitIdx == array.length - 1) {
            return (T[]) Array.newInstance(array.getClass().getComponentType(), 0);
        }

        return Arrays.copyOfRange(array, splitIdx + 1, array.length);
    }

    /**
     * 첫 데이터부터 주어진 조건에 맞는 데이터까지 새로운 배열로 제공합니다. (Inclusive)
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2021. 6. 21.    parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.    parkjunhong77@gmail.com     Predicate 적용 및 조기 반환 최적화
     * </pre>
     * 
     * @param <T>
     *            배열 요소의 타입
     * @param array
     *            원본 배열
     * @param at
     *            조건에 맞는지 비교하는 함수 (해당 데이터 포함됨)
     * @param post
     *            조건에 맞는 데이터를 후처리하는 함수 ({@code null} 가능)
     * @return 조건 일치 데이터까지의 새로운 배열. 일치하는 데이터가 없으면 전체 배열 반환.
     * 
     * @throws NullPointerException
     *             파라미터({@code array} 또는 {@code at})가 {@code null}인 경우 발생.
     * @since 2021. 6. 21.
     */
    // 아래 내용에 적용됨.
    // - return Arrays.copyOf(array, array.length);
    // - return newArr;
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static <T> T[] splitAt(T[] array, Predicate<T> at, @Nullable UnaryOperator<T> post) {
        Objects.requireNonNull(array, "A parameter(T[] array) must not be 'null'.");
        Objects.requireNonNull(at, "A parameter(Predicate<T> at) must not be 'null'.");

        int splitIdx = -1;
        for (int i = 0; i < array.length; i++) {
            if (at.test(array[i])) {
                splitIdx = i;
                break;
            }
        }

        // 일치하는 데이터가 없는 경우 원본 복사본 반환
        if (splitIdx < 0) {
            return Arrays.copyOf(array, array.length);
        }

        T[] newArr = Arrays.copyOfRange(array, 0, splitIdx + 1);

        // 마지막 데이터 후처리
        if (post != null) {
            newArr[splitIdx] = post.apply(newArr[splitIdx]);
        }

        return newArr;
    }

    /**
     * 첫 데이터부터 주어진 조건에 맞는 데이터 직전까지 새로운 배열로 제공합니다. (Exclusive)
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2021. 6. 21.    parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.    parkjunhong77@gmail.com     Predicate 적용 및 가드 클로즈 최적화
     * </pre>
     * 
     * @param <T>
     *            배열 요소의 타입
     * @param array
     *            원본 배열
     * @param at
     *            조건에 맞는지 비교하는 함수 (해당 데이터는 제외됨)
     * @return 조건 일치 데이터 직전까지의 새로운 배열. 일치하는 데이터가 없으면 전체 배열 반환.
     * 
     * @throws NullPointerException
     *             파라미터({@code array} 또는 {@code at})가 {@code null}인 경우 발생.
     * @since 2021. 6. 21.
     */
    // 아래 내용에 적용됨.
    // - return Arrays.copyOf(array, array.length);
    // - return Arrays.copyOfRange(array, 0, splitIdx);
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static <T> T[] splitBefore(T[] array, Predicate<T> at) {
        Objects.requireNonNull(array, "A parameter(T[] array) must not be 'null'.");
        Objects.requireNonNull(at, "A parameter(Predicate<T> at) must not be 'null'.");

        int splitIdx = -1;
        for (int i = 0; i < array.length; i++) {
            if (at.test(array[i])) {
                splitIdx = i;
                break;
            }
        }

        // 일치하는 데이터가 없는 경우 원본 복사본 반환, 첫 데이터인 경우 빈 배열 반환
        if (splitIdx < 0) {
            return Arrays.copyOf(array, array.length);
        }

        return Arrays.copyOfRange(array, 0, splitIdx);
    }

    /**
     * 주어진 배열을 지정된 위치({@code index})를 기준으로 2개의 배열로 분할하여 반환합니다.
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 3. 9.      parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.     parkjunhong77@gmail.com     가드 클로즈 적용 및 인덱스 보정 로직 최적화
     * </pre>
     * 
     * @param array
     *            분리할 배열
     * @param index
     *            분리할 지점
     * @param inclusive
     *            분리 지점 데이타의 첫 번째 배열 포함 여부. {@code true}인 경우 첫 번째 배열에 포함되며, {@code false}인 경우 제외(Omitted)됩니다.
     *
     * @return 분리된 2개의 배열을 담은 2차원 배열
     *
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * @throws IllegalArgumentException
     *             분리 지점({@code index})이 배열 범위를 벗어난 경우 발생.
     *
     * @since 2012. 3. 9.
     */
    public static boolean[][] subArrays(boolean[] array, int index, boolean inclusive) {
        // [1] 가드 클로즈: null 체크
        Objects.requireNonNull(array, "A parameter(boolean[] array) must not be 'null'.");

        // [2] 가드 클로즈: 인덱스 범위 체크
        if (index < 0 || index >= array.length) {
            throw new IllegalArgumentException("A parameter(int index) must be in '0' ~ '" + (array.length - 1) + "': index=" + index);
        }

        // [3] 분리 지점 보정 (inclusive가 true면 index 뒤에서 절단, false면 index 위치에서 절단하여 index 데이터 누락)
        int secondStart = inclusive ? index + 1 : index;

        boolean[][] result = new boolean[2][];

        // 첫 번째 배열: 0부터 index까지 (inclusive 여부에 따라 포함 여부 결정)
        result[0] = Arrays.copyOfRange(array, 0, inclusive ? index + 1 : index);

        // 두 번째 배열: 보정된 지점부터 끝까지
        result[1] = Arrays.copyOfRange(array, secondStart, array.length);

        return result;
    }

    /**
     * 주어진 배열을 지정된 위치({@code index})를 기준으로 2개의 배열로 분할하여 반환합니다.
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 3. 9.      parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.     parkjunhong77@gmail.com     가드 클로즈 적용 및 인덱스 보정 로직 최적화
     * </pre>
     * 
     * @param array
     *            분리할 배열
     * @param index
     *            분리할 지점
     * @param inclusive
     *            분리 지점 데이타의 첫 번째 배열 포함 여부. {@code true}인 경우 첫 번째 배열에 포함되며, {@code false}인 경우 제외(Omitted)됩니다.
     *
     * @return 분리된 2개의 배열을 담은 2차원 배열
     *
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * @throws IllegalArgumentException
     *             분리 지점({@code index})이 배열 범위를 벗어난 경우 발생.
     *
     * @since 2012. 3. 9.
     */
    public static byte[][] subArrays(byte[] array, int index, boolean inclusive) {
        // [1] 가드 클로즈: null 체크
        Objects.requireNonNull(array, "A parameter(byte[] array) must not be 'null'.");

        // [2] 가드 클로즈: 인덱스 범위 체크
        if (index < 0 || index >= array.length) {
            throw new IllegalArgumentException("A parameter(int index) must be in '0' ~ '" + (array.length - 1) + "': index=" + index);
        }

        // [3] 분리 지점 보정 (inclusive가 true면 index 뒤에서 절단, false면 index 위치에서 절단하여 index 데이터 누락)
        int secondStart = inclusive ? index + 1 : index;

        byte[][] result = new byte[2][];

        // 첫 번째 배열: 0부터 index까지 (inclusive 여부에 따라 포함 여부 결정)
        result[0] = Arrays.copyOfRange(array, 0, inclusive ? index + 1 : index);

        // 두 번째 배열: 보정된 지점부터 끝까지
        result[1] = Arrays.copyOfRange(array, secondStart, array.length);

        return result;
    }

    /**
     * 주어진 배열을 지정된 위치({@code index})를 기준으로 2개의 배열로 분할하여 반환합니다.
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 3. 9.      parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.     parkjunhong77@gmail.com     가드 클로즈 적용 및 인덱스 보정 로직 최적화
     * </pre>
     * 
     * @param array
     *            분리할 배열
     * @param index
     *            분리할 지점
     * @param inclusive
     *            분리 지점 데이타의 첫 번째 배열 포함 여부. {@code true}인 경우 첫 번째 배열에 포함되며, {@code false}인 경우 제외(Omitted)됩니다.
     *
     * @return 분리된 2개의 배열을 담은 2차원 배열
     *
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * @throws IllegalArgumentException
     *             분리 지점({@code index})이 배열 범위를 벗어난 경우 발생.
     *
     * @since 2012. 3. 9.
     */
    public static char[][] subArrays(char[] array, int index, boolean inclusive) {
        // [1] 가드 클로즈: null 체크
        Objects.requireNonNull(array, "A parameter(char[] array) must not be 'null'.");

        // [2] 가드 클로즈: 인덱스 범위 체크
        if (index < 0 || index >= array.length) {
            throw new IllegalArgumentException("A parameter(int index) must be in '0' ~ '" + (array.length - 1) + "': index=" + index);
        }

        // [3] 분리 지점 보정 (inclusive가 true면 index 뒤에서 절단, false면 index 위치에서 절단하여 index 데이터 누락)
        int secondStart = inclusive ? index + 1 : index;

        char[][] result = new char[2][];

        // 첫 번째 배열: 0부터 index까지 (inclusive 여부에 따라 포함 여부 결정)
        result[0] = Arrays.copyOfRange(array, 0, inclusive ? index + 1 : index);

        // 두 번째 배열: 보정된 지점부터 끝까지
        result[1] = Arrays.copyOfRange(array, secondStart, array.length);

        return result;
    }

    /**
     * 주어진 배열을 지정된 위치({@code index})를 기준으로 2개의 배열로 분할하여 반환합니다.
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 3. 9.      parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.     parkjunhong77@gmail.com     가드 클로즈 적용 및 인덱스 보정 로직 최적화
     * </pre>
     * 
     * @param array
     *            분리할 배열
     * @param index
     *            분리할 지점
     * @param inclusive
     *            분리 지점 데이타의 첫 번째 배열 포함 여부. {@code true}인 경우 첫 번째 배열에 포함되며, {@code false}인 경우 제외(Omitted)됩니다.
     *
     * @return 분리된 2개의 배열을 담은 2차원 배열
     *
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * @throws IllegalArgumentException
     *             분리 지점({@code index})이 배열 범위를 벗어난 경우 발생.
     *
     * @since 2012. 3. 9.
     */
    public static double[][] subArrays(double[] array, int index, boolean inclusive) {
        // [1] 가드 클로즈: null 체크
        Objects.requireNonNull(array, "A parameter(double[] array) must not be 'null'.");

        // [2] 가드 클로즈: 인덱스 범위 체크
        if (index < 0 || index >= array.length) {
            throw new IllegalArgumentException("A parameter(int index) must be in '0' ~ '" + (array.length - 1) + "': index=" + index);
        }

        // [3] 분리 지점 보정 (inclusive가 true면 index 뒤에서 절단, false면 index 위치에서 절단하여 index 데이터 누락)
        int secondStart = inclusive ? index + 1 : index;

        double[][] result = new double[2][];

        // 첫 번째 배열: 0부터 index까지 (inclusive 여부에 따라 포함 여부 결정)
        result[0] = Arrays.copyOfRange(array, 0, inclusive ? index + 1 : index);

        // 두 번째 배열: 보정된 지점부터 끝까지
        result[1] = Arrays.copyOfRange(array, secondStart, array.length);

        return result;
    }

    /**
     * 주어진 배열을 지정된 위치({@code index})를 기준으로 2개의 배열로 분할하여 반환합니다.
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 3. 9.      parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.     parkjunhong77@gmail.com     가드 클로즈 적용 및 인덱스 보정 로직 최적화
     * </pre>
     * 
     * @param array
     *            분리할 배열
     * @param index
     *            분리할 지점
     * @param inclusive
     *            분리 지점 데이타의 첫 번째 배열 포함 여부. {@code true}인 경우 첫 번째 배열에 포함되며, {@code false}인 경우 제외(Omitted)됩니다.
     *
     * @return 분리된 2개의 배열을 담은 2차원 배열
     *
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * @throws IllegalArgumentException
     *             분리 지점({@code index})이 배열 범위를 벗어난 경우 발생.
     *
     * @since 2012. 3. 9.
     */
    public static float[][] subArrays(float[] array, int index, boolean inclusive) {
        // [1] 가드 클로즈: null 체크
        Objects.requireNonNull(array, "A parameter(float[] array) must not be 'null'.");

        // [2] 가드 클로즈: 인덱스 범위 체크
        if (index < 0 || index >= array.length) {
            throw new IllegalArgumentException("A parameter(int index) must be in '0' ~ '" + (array.length - 1) + "': index=" + index);
        }

        // [3] 분리 지점 보정 (inclusive가 true면 index 뒤에서 절단, false면 index 위치에서 절단하여 index 데이터 누락)
        int secondStart = inclusive ? index + 1 : index;

        float[][] result = new float[2][];

        // 첫 번째 배열: 0부터 index까지 (inclusive 여부에 따라 포함 여부 결정)
        result[0] = Arrays.copyOfRange(array, 0, inclusive ? index + 1 : index);

        // 두 번째 배열: 보정된 지점부터 끝까지
        result[1] = Arrays.copyOfRange(array, secondStart, array.length);

        return result;
    }

    /**
     * 주어진 배열을 지정된 위치({@code index})를 기준으로 2개의 배열로 분할하여 반환합니다.
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 3. 9.      parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.     parkjunhong77@gmail.com     가드 클로즈 적용 및 인덱스 보정 로직 최적화
     * </pre>
     * 
     * @param array
     *            분리할 배열
     * @param index
     *            분리할 지점
     * @param inclusive
     *            분리 지점 데이타의 첫 번째 배열 포함 여부. {@code true}인 경우 첫 번째 배열에 포함되며, {@code false}인 경우 제외(Omitted)됩니다.
     *
     * @return 분리된 2개의 배열을 담은 2차원 배열
     *
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * @throws IllegalArgumentException
     *             분리 지점({@code index})이 배열 범위를 벗어난 경우 발생.
     *
     * @since 2012. 3. 9.
     */
    public static int[][] subArrays(int[] array, int index, boolean inclusive) {
        // [1] 가드 클로즈: null 체크
        Objects.requireNonNull(array, "A parameter(int[] array) must not be 'null'.");

        // [2] 가드 클로즈: 인덱스 범위 체크
        if (index < 0 || index >= array.length) {
            throw new IllegalArgumentException("A parameter(int index) must be in '0' ~ '" + (array.length - 1) + "': index=" + index);
        }

        // [3] 분리 지점 보정 (inclusive가 true면 index 뒤에서 절단, false면 index 위치에서 절단하여 index 데이터 누락)
        int secondStart = inclusive ? index + 1 : index;

        int[][] result = new int[2][];

        // 첫 번째 배열: 0부터 index까지 (inclusive 여부에 따라 포함 여부 결정)
        result[0] = Arrays.copyOfRange(array, 0, inclusive ? index + 1 : index);

        // 두 번째 배열: 보정된 지점부터 끝까지
        result[1] = Arrays.copyOfRange(array, secondStart, array.length);

        return result;
    }

    /**
     * 주어진 배열을 지정된 위치({@code index})를 기준으로 2개의 배열로 분할하여 반환합니다.
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 3. 9.      parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.     parkjunhong77@gmail.com     가드 클로즈 적용 및 인덱스 보정 로직 최적화
     * </pre>
     * 
     * @param array
     *            분리할 배열
     * @param index
     *            분리할 지점
     * @param inclusive
     *            분리 지점 데이타의 첫 번째 배열 포함 여부. {@code true}인 경우 첫 번째 배열에 포함되며, {@code false}인 경우 제외(Omitted)됩니다.
     *
     * @return 분리된 2개의 배열을 담은 2차원 배열
     *
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * @throws IllegalArgumentException
     *             분리 지점({@code index})이 배열 범위를 벗어난 경우 발생.
     * 
     * @since 2012. 3. 9.
     */
    public static long[][] subArrays(long[] array, int index, boolean inclusive) {
        // [1] 가드 클로즈: null 체크
        Objects.requireNonNull(array, "A parameter(long[] array) must not be 'null'.");

        // [2] 가드 클로즈: 인덱스 범위 체크
        if (index < 0 || index >= array.length) {
            throw new IllegalArgumentException("A parameter(int index) must be in '0' ~ '" + (array.length - 1) + "': index=" + index);
        }

        // [3] 분리 지점 보정 (inclusive가 true면 index 뒤에서 절단, false면 index 위치에서 절단하여 index 데이터 누락)
        int secondStart = inclusive ? index + 1 : index;

        long[][] result = new long[2][];

        // 첫 번째 배열: 0부터 index까지 (inclusive 여부에 따라 포함 여부 결정)
        result[0] = Arrays.copyOfRange(array, 0, inclusive ? index + 1 : index);

        // 두 번째 배열: 보정된 지점부터 끝까지
        result[1] = Arrays.copyOfRange(array, secondStart, array.length);

        return result;
    }

    /**
     * 주어진 배열을 지정된 위치({@code index})를 기준으로 2개의 배열로 분할하여 반환합니다.
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 3. 9.      parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.     parkjunhong77@gmail.com     가드 클로즈 적용 및 인덱스 보정 로직 최적화
     * </pre>
     * 
     * @param array
     *            분리할 배열
     * @param index
     *            분리할 지점
     * @param inclusive
     *            분리 지점 데이타의 첫 번째 배열 포함 여부. {@code true}인 경우 첫 번째 배열에 포함되며, {@code false}인 경우 제외(Omitted)됩니다.
     *
     * @return 분리된 2개의 배열을 담은 2차원 배열
     *
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * @throws IllegalArgumentException
     *             분리 지점({@code index})이 배열 범위를 벗어난 경우 발생.
     * 
     * @since 2012. 3. 9.
     */
    public static short[][] subArrays(short[] array, int index, boolean inclusive) {
        // [1] 가드 클로즈: null 체크
        Objects.requireNonNull(array, "A parameter(short[] array) must not be 'null'.");

        // [2] 가드 클로즈: 인덱스 범위 체크
        if (index < 0 || index >= array.length) {
            throw new IllegalArgumentException("A parameter(int index) must be in '0' ~ '" + (array.length - 1) + "': index=" + index);
        }

        // [3] 분리 지점 보정 (inclusive가 true면 index 뒤에서 절단, false면 index 위치에서 절단하여 index 데이터 누락)
        int secondStart = inclusive ? index + 1 : index;

        short[][] result = new short[2][];

        // 첫 번째 배열: 0부터 index까지 (inclusive 여부에 따라 포함 여부 결정)
        result[0] = Arrays.copyOfRange(array, 0, inclusive ? index + 1 : index);

        // 두 번째 배열: 보정된 지점부터 끝까지
        result[1] = Arrays.copyOfRange(array, secondStart, array.length);

        return result;
    }

    /**
     * 주어진 배열을 지정된 위치({@code index})를 기준으로 2개의 배열로 분할하여 반환합니다.
     * 
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2012. 3. 9.      parkjunhong77@gmail.com     최초 작성
     * 2026. 03. 26.     parkjunhong77@gmail.com     반환 타입 최적화(T[][]) 및 가드 클로즈 적용
     * </pre>
     * 
     * @param <T>
     *            배열의 원소 타입
     * @param array
     *            분리할 배열
     * @param index
     *            분리할 지점
     * @param inclusive
     *            분리 지점 데이타의 첫 번째 배열 포함 여부. {@code true}인 경우 첫 번째 배열에 포함되며, {@code false}인 경우 제외(Omitted)됩니다.
     * 
     * @return 분리된 2개의 배열을 담은 2차원 배열
     * 
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * @throws IllegalArgumentException
     *             분리 지점({@code index})이 배열 범위를 벗어난 경우 발생.
     * 
     * @since 2012. 3. 9.
     */
    public static <T extends @Nullable Object> T[][] subArrays(T[] array, int index, boolean inclusive) {
        // [1] 가드 클로즈: null 체크
        Objects.requireNonNull(array, "A parameter(T[] array) must not be 'null'.");

        // [2] 가드 클로즈: 인덱스 범위 체크
        if (index < 0 || index >= array.length) {
            throw new IllegalArgumentException("A parameter(int index) must be in '0' ~ '" + (array.length - 1) + "': index=" + index);
        }

        // [3] 분리 지점 보정
        int secondStart = inclusive ? index + 1 : index;
        int firstLength = inclusive ? index + 1 : index;

        // [4] 결과 배열 타입 정의 (T[][])
        T[][] result = (T[][]) Array.newInstance(array.getClass(), 2);

        // 첫 번째 배열 생성 및 복사
        result[0] = (T[]) Array.newInstance(array.getClass().getComponentType(), firstLength);
        System.arraycopy(array, 0, result[0], 0, firstLength);

        // 두 번째 배열 생성 및 복사
        int secondLength = array.length - secondStart;
        result[1] = (T[]) Array.newInstance(array.getClass().getComponentType(), secondLength);
        System.arraycopy(array, secondStart, result[1], 0, secondLength);

        return result;
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
     */
    public static @Nullable String toString(Object @Nullable [] array) {
        if (array == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();

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

    private static @Nullable String toString0(@Nullable Object obj) {
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

    public static <T extends @Nullable Object> T @Nullable [] toWrapperArray(@Nullable Object array) {
        if (array == null) {
            return null;
        }

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
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2023. 7. 25.     parkjunhong77@gmail.com         최초 작성
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
     * 
     */
    public static <T extends @Nullable Object, R extends @Nullable Object> R @Nullable [] transform(T @Nullable [] arr, Function<T, R> f) {
        if (arr == null) {
            return null;
        }

        return (R[]) Stream.of(arr).map(f).toArray();
    }

    public static class EntryValue<K, V extends @Nullable Object> implements Entry<K, V> {

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
         *      날짜      | 작성자   |   내용
         * ------------------------------------------
         * 2021. 8. 15.     parkjunhong77@gmail.com         최초 작성
         * </pre>
         *
         * @return
         *
         * @since 2021. 8. 15.
         * 
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
         *      날짜      | 작성자   |   내용
         * ------------------------------------------
         * 2021. 8. 15.     parkjunhong77@gmail.com         최초 작성
         * </pre>
         *
         * @return
         *
         * @since 2021. 8. 15.
         * 
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
         *      날짜      | 작성자   |   내용
         * ------------------------------------------
         * 2021. 8. 15.     parkjunhong77@gmail.com         최초 작성
         * </pre>
         *
         * @param value
         * @return
         *
         * @since 2021. 8. 15.
         * 
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
