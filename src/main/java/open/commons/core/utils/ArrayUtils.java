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
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 * 
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
     * 2019. 7. 4.      parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param array
     * @param values
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
     * @param value
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
     * 2019. 7. 4.      parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param array
     * @param values
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
     * 2019. 7. 4.      parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param array
     * @param values
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
     * 2019. 7. 4.      parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param array
     * @param values
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
     * 2019. 7. 4.      parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param array
     * @param values
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
     * 2019. 7. 4.      parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param array
     * @param values
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
     * 2019. 7. 4.      parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param array
     * @param values
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
     * 2019. 7. 4.      parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param array
     * @param values
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
            throw new IllegalArgumentException(new NullPointerException("All parameters(T[] array, T value) must not be null: arr=null, value=null"));
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
     * 2019. 7. 4.      parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param array
     * @param values
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
     * 2019. 7. 4.      parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param array
     * @param values
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
     * 2019. 7. 4.      parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param array
     * @param values
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
     * 2019. 7. 4.      parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param array
     * @param values
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
     * 2019. 7. 4.      parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param array
     * @param values
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
     * 2019. 7. 4.      parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param array
     * @param values
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
     * 2019. 7. 4.      parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param array
     * @param values
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
     * 2019. 7. 4.      parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param array
     * @param values
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
     * 2023. 7. 25.     parkjunohng77@gmail.com         최초 작성
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
     * 2023. 7. 25.     parkjunohng77@gmail.com         최초 작성
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
            chars[i] = prepend((array[i] != null ? array[i] : "null").toString().trim().toCharArray(), delimiter);
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
            chars[i] = merge(delim, (array[i] != null ? array[i] : "null").toString().trim().toCharArray());
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
     * 2023. 8. 2.      parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param arr
     *            복사할 배열
     * @param indice
     *            복사할 데이터의 index.
     * @return
     * @throws ArrayIndexOutOfBoundsException
     * @throws NullPointerException
     *             파라미터({@code arr 또는 indice})가 {@code null}인 경우 발생.
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
     * 2023. 8. 2.      parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param arr
     *            복사할 배열
     * @param indice
     *            복사할 데이터의 index.
     * @return
     * @throws ArrayIndexOutOfBoundsException
     * @throws NullPointerException
     *             파라미터({@code arr 또는 indice})가 {@code null}인 경우 발생.
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
     * 2023. 8. 2.      parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param arr
     *            복사할 배열
     * @param indice
     *            복사할 데이터의 index.
     * @return
     * @throws ArrayIndexOutOfBoundsException
     * @throws NullPointerException
     *             파라미터({@code arr 또는 indice})가 {@code null}인 경우 발생.
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
     * 2023. 8. 2.      parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param arr
     *            복사할 배열
     * @param indice
     *            복사할 데이터의 index.
     * @return
     * @throws ArrayIndexOutOfBoundsException
     * @throws NullPointerException
     *             파라미터({@code arr 또는 indice})가 {@code null}인 경우 발생.
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
     * 2023. 8. 2.      parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param arr
     *            복사할 배열
     * @param indice
     *            복사할 데이터의 index.
     * @return
     * @throws ArrayIndexOutOfBoundsException
     * @throws NullPointerException
     *             파라미터({@code arr 또는 indice})가 {@code null}인 경우 발생.
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
     * 2023. 8. 2.      parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param arr
     *            복사할 배열
     * @param indice
     *            복사할 데이터의 index.
     * @return
     * @throws ArrayIndexOutOfBoundsException
     * @throws NullPointerException
     *             파라미터({@code arr 또는 indice})가 {@code null}인 경우 발생.
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
     * 2023. 8. 2.      parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param arr
     *            복사할 배열
     * @param indice
     *            복사할 데이터의 index.
     * @return
     * @throws ArrayIndexOutOfBoundsException
     * @throws NullPointerException
     *             파라미터({@code arr 또는 indice})가 {@code null}인 경우 발생.
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
     * 2023. 8. 2.      parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param arr
     *            복사할 배열
     * @param indice
     *            복사할 데이터의 index.
     * @return
     * @throws ArrayIndexOutOfBoundsException
     * @throws NullPointerException
     *             파라미터({@code arr 또는 indice})가 {@code null}인 경우 발생.
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
     * 2023. 8. 2.      parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param arr
     *            복사할 배열
     * @param indice
     *            복사할 데이터의 index.
     * @return
     * @throws ArrayIndexOutOfBoundsException
     * @throws NullPointerException
     *             파라미터({@code arr 또는 indice})가 {@code null}인 경우 발생.
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
     * 2021. 8. 19.     parkjunohng77@gmail.com         최초 작성
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
     *             파라미터({@code original 또는 newType 또는 clone})가 {@code null}인 경우 발생.
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
     * 2021. 8. 19.     parkjunohng77@gmail.com         최초 작성
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
     * 2021. 8. 19.     parkjunohng77@gmail.com         최초 작성
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
     *             파라미터({@code original 또는 newType 또는 clone})가 {@code null}인 경우 발생.
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
     * 2021. 8. 19.     parkjunohng77@gmail.com         최초 작성
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
     * 2021. 8. 15.     parkjunohng77@gmail.com         최초 작성
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
     * 2022. 10. 26.        parkjunohng77@gmail.com         최초 작성
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
     * 2022. 10. 26.        parkjunohng77@gmail.com         최초 작성
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
     * 2021. 6. 21.     parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param <T>
     * @param array
     * @param c
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code array 또는 c})가 {@code null}인 경우 발생.
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
     * 2021. 6. 21.     parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param <T>
     * @param array
     * @param c
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code array 또는 c})가 {@code null}인 경우 발생.
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
     * 2021. 6. 21.     parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param <T>
     * @param array
     * @param c
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code array 또는 c})가 {@code null}인 경우 발생.
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
     * 2021. 6. 21.     parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param <T>
     * @param array
     * @param c
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code array 또는 c})가 {@code null}인 경우 발생.
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
     * 2021. 6. 21.     parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param <T>
     * @param array
     * @param c
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code array 또는 c})가 {@code null}인 경우 발생.
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
     * 2021. 6. 21.     parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param <T>
     * @param array
     * @param c
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code array 또는 c})가 {@code null}인 경우 발생.
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
     * 2021. 6. 21.     parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param <T>
     * @param array
     * @param c
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code array 또는 c})가 {@code null}인 경우 발생.
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
     * 2021. 6. 21.     parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param <T>
     * @param array
     * @param c
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code array 또는 c})가 {@code null}인 경우 발생.
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
     * 2021. 6. 21.     parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param <T>
     * @param array
     * @param c
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code array 또는 c})가 {@code null}인 경우 발생.
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
     *             파라미터({@code array 또는 values})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 03. 30.
     * 
     */
    public static <T> int[] indiceOf(T[] array, T[] values) {
        return indiceOfArray(array, values, null);
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
    public static <T> int[] indiceOfArray(T[] array, T[] values, @Nullable IEquivalent<T[]> equi) {
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
     *             파라미터({@code array 또는 indice})가 {@code null}인 경우 발생.
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
     *             파라미터({@code array 또는 indice})가 {@code null}인 경우 발생.
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
     *             파라미터({@code array 또는 indice})가 {@code null}인 경우 발생.
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
     *             파라미터({@code array 또는 indice})가 {@code null}인 경우 발생.
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
     *             파라미터({@code array 또는 indice})가 {@code null}인 경우 발생.
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
     *             파라미터({@code array 또는 indice})가 {@code null}인 경우 발생.
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
     *             파라미터({@code array 또는 indice})가 {@code null}인 경우 발생.
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
     *             파라미터({@code array 또는 indice})가 {@code null}인 경우 발생.
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
            return prepend(array, value);
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
     *             파라미터({@code array 또는 indice})가 {@code null}인 경우 발생.
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
            throw new IllegalArgumentException(new NullPointerException("All parameters(boolean[] arr1, boolean[] arr2) must not be null: arr1=null, arr2=null"));
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
    public static byte @Nullable [] merge(byte @Nullable []... arrays) {

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
            throw new IllegalArgumentException(new NullPointerException("All parameters(byte[] arr1, byte[] arr2) must not be null: arr1=null, arr2=null"));
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
            throw new IllegalArgumentException(new NullPointerException("All parameters(char[] arr1, char[] arr2) must not be null: arr1=null, arr2=null"));
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
            throw new IllegalArgumentException(new NullPointerException("All parameters(double[] arr1, double[] arr2) must not be null: arr1=null, arr2=null"));
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
     * @exception NullPointerException
     *                Either of parameters is null
     * 
     * 
     * @since 2012. 3. 9.
     * 
     */
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
            throw new IllegalArgumentException(new NullPointerException("All parameters(float[] arr1, float[] arr2) must not be null: arr1=null, arr2=null"));
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
     * 
     * @since 2012. 3. 9.
     * 
     */
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
            throw new IllegalArgumentException(new NullPointerException("All parameters(int[] arr1, int[] arr2) must not be null: arr1=null, arr2=null"));
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
            throw new IllegalArgumentException(new NullPointerException("All parameters(long[] arr1, long[] arr2) must not be null: arr1=null, arr2=null"));
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
            throw new IllegalArgumentException(new NullPointerException("All parameters(short[] arr1, short[] arr2) must not be null: arr1=null, arr2=null"));
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

    public static <T extends @Nullable Object> T[] merge(T @Nullable [] arr1, T @Nullable [] arr2) {

        if (arr1 != null && arr2 != null) {

            assertComponentType(arr1, arr2);

            T[] merged = ((T[]) Array.newInstance(arr1.getClass().getComponentType(), arr1.length + arr2.length));

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

    /**
     * 타입이 서로 다른 2개의 배열을 하나의 배열로 합쳐서 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 12. 28.        parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param array
     * @param values
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
            throw new IllegalArgumentException(new NullPointerException("All parameters(T[] arr, T[] values) must not be null: arr=null, values=null"));
        }
    }

    /**
     * 타입이 서로 다른 2개의 배열을 하나의 배열로 합쳐서 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 12. 28.        parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param array
     * @param values
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
            throw new IllegalArgumentException(new NullPointerException("All parameters(T[] arr, T[] values) must not be null: arr=null, values=null"));
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
     * 2023. 8. 29.     parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param array
     * @param values
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
     * 2023. 8. 29.     parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param array
     * @param values
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
     * 2023. 8. 29.     parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param array
     * @param values
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
     * 2023. 8. 29.     parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param array
     * @param values
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
     * 2023. 8. 29.     parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param array
     * @param values
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
     * 2023. 8. 29.     parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param array
     * @param values
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
     * 2023. 8. 29.     parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param array
     * @param values
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
     * 2023. 8. 29.     parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param array
     * @param values
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
    public static <T> T[] prepend(T @Nullable [] array, @Nullable T value) {

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
            throw new IllegalArgumentException(new NullPointerException("All parameters(T[] array, T value) must not be null: arr=null, value=null"));
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
     * 2023. 8. 29.     parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param array
     * @param values
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
    public static <T> T[] prepend(T @Nullable [] array, T @Nullable... values) {
        return merge(values, array);
    }

    /**
     * 주어진 배열에서 대상 값과 같은 모든 값을 제거한 후 배열을 반환합니다.
     * 
     * @param array
     * @param value
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 3. 9.
     * 
     */
    public static boolean[] removeAll(boolean[] array, boolean value) {
        Objects.requireNonNull(array);

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

        delIndice = Arrays.copyOf(delIndice, delCount);

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
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 3. 9.
     * 
     */
    public static byte[] removeAll(byte[] array, byte value) {
        Objects.requireNonNull(array);

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

        delIndice = Arrays.copyOf(delIndice, delCount);

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
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 3. 9.
     * 
     */
    public static char[] removeAll(char[] array, char value) {
        Objects.requireNonNull(array);

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

        delIndice = Arrays.copyOf(delIndice, delCount);

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
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 3. 9.
     * 
     */
    public static double[] removeAll(double[] array, double value) {
        Objects.requireNonNull(array);

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

        delIndice = Arrays.copyOf(delIndice, delCount);

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
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 3. 9.
     * 
     */
    public static float[] removeAll(float[] array, float value) {
        Objects.requireNonNull(array);

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

        delIndice = Arrays.copyOf(delIndice, delCount);

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
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 3. 9.
     * 
     */
    public static int[] removeAll(int[] array, int value) {
        Objects.requireNonNull(array);

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

        delIndice = Arrays.copyOf(delIndice, delCount);

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
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 3. 9.
     * 
     */
    public static long[] removeAll(long[] array, long value) {
        Objects.requireNonNull(array);

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

        delIndice = Arrays.copyOf(delIndice, delCount);

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
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 3. 9.
     * 
     */
    public static short[] removeAll(short[] array, short value) {
        Objects.requireNonNull(array);

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

        delIndice = Arrays.copyOf(delIndice, delCount);

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

    /**
     * 주어진 배열에서 대상 값과 같은 모든 값을 제거한 후 배열을 반환합니다.
     * 
     * @param array
     * @param value
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code array 또는 filter})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 3. 9.
     * 
     */
    // apply to 'return newArray'
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static <T> T[] removeAll(@Nullable T[] array, Predicate<T> filter) {
        ObjectUtils.requireNonNulls(array, filter);

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

        delIndice = Arrays.copyOf(delIndice, delCount);

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
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * 
     * 
     * @since 2012. 3. 9.
     * 
     */
    public static <T> T[] removeAll(@Nullable T[] array, @Nullable T value) {
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
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * 
     * 
     * @since 2012. 3. 9.
     * 
     */
    // apply to 'return newArray'
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static <T> T[] removeAll(@Nullable T[] array, @Nullable T value, @Nullable IEquivalent<T> equivalent) {
        Objects.requireNonNull(array);

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

        delIndice = Arrays.copyOf(delIndice, delCount);

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
     * 주어진 배열에서 {@code index}에 해당하는 값을 제거한 배열을 반환합니다.
     * 
     * @param array
     * @param index
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code array})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 3. 9.
     * 
     */
    public static boolean[] removeAt(boolean[] array, int index) {
        Objects.requireNonNull(array);

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
     * 주어진 배열에서 {@code index}에 해당하는 값을 제거한 배열을 반환합니다.
     * 
     * @param array
     * @param index
     * @return
     * @since 2012. 3. 9.
     * 
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
     * 주어진 배열에서 {@code index}에 해당하는 값을 제거한 배열을 반환합니다.
     * 
     * @param array
     * @param index
     * @return
     * @since 2012. 3. 9.
     * 
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
     * 주어진 배열에서 {@code index}에 해당하는 값을 제거한 배열을 반환합니다.
     * 
     * @param array
     * @param index
     * @return
     * @since 2012. 3. 9.
     * 
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
     * 주어진 배열에서 {@code index}에 해당하는 값을 제거한 배열을 반환합니다.
     * 
     * @param array
     * @param index
     * @return
     * @since 2012. 3. 9.
     * 
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
     * 주어진 배열에서 {@code index}에 해당하는 값을 제거한 배열을 반환합니다.
     * 
     * @param array
     * @param index
     * @return
     * @since 2012. 3. 9.
     * 
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
     * 주어진 배열에서 {@code index}에 해당하는 값을 제거한 배열을 반환합니다.
     * 
     * @param array
     * @param index
     * @return
     * @since 2012. 3. 9.
     * 
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
     * 주어진 배열에서 {@code index}에 해당하는 값을 제거한 배열을 반환합니다.
     * 
     * @param array
     * @param index
     * @return
     * @since 2012. 3. 9.
     * 
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
     * 주어진 배열에서 {@code index}에 해당하는 값을 제거한 배열을 반환합니다.
     * 
     * @param array
     * @param index
     * @return
     * @since 2012. 3. 9.
     * 
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
     *                주어진 배열이 {@code null}인 경우
     * 
     * 
     * @since 2012. 3. 9.
     * 
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
     *                주어진 배열이 {@code null}인 경우
     * 
     * 
     * @since 2012. 3. 9.
     * 
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
     *                주어진 배열이 {@code null}인 경우
     * 
     * 
     * @since 2012. 3. 9.
     * 
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
     *                주어진 배열이 {@code null}인 경우
     * 
     * 
     * @since 2012. 3. 9.
     * 
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
     *                주어진 배열이 {@code null}인 경우
     * 
     * 
     * @since 2012. 3. 9.
     * 
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
     *                주어진 배열이 {@code null}인 경우
     * 
     * 
     * @since 2012. 3. 9.
     * 
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
     *                주어진 배열이 {@code null}인 경우
     * 
     * 
     * @since 2012. 3. 9.
     * 
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
     *                주어진 배열이 {@code null}인 경우
     * 
     * 
     * @since 2012. 3. 9.
     * 
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
     *                주어진 배열이 {@code null}인 경우
     * 
     * 
     * @since 2012. 3. 9.
     * 
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
     *                주어진 배열이 {@code null}인 경우
     * 
     * 
     * @since 2012. 3. 9.
     * 
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
     *                주어진 배열이 {@code null}인 경우
     * 
     * 
     * @since 2012. 3. 9.
     * 
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
     *                주어진 배열이 {@code null}인 경우
     * 
     * 
     * @since 2012. 3. 9.
     * 
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
     *                주어진 배열이 {@code null}인 경우
     * 
     * 
     * @since 2012. 3. 9.
     * 
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
     *                주어진 배열이 {@code null}인 경우
     * 
     * 
     * @since 2012. 3. 9.
     * 
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
     *                주어진 배열이 {@code null}인 경우
     * 
     * 
     * @since 2012. 3. 9.
     * 
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
     *                주어진 배열이 {@code null}인 경우
     * 
     * 
     * @since 2012. 3. 9.
     * 
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
     *                주어진 배열이 {@code null}인 경우
     * 
     * 
     * @since 2012. 3. 9.
     * 
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
     *                주어진 배열이 {@code null}인 경우
     * 
     * 
     * @since 2012. 3. 9.
     * 
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
     *                주어진 배열이 {@code null}인 경우
     * 
     * 
     * @since 2012. 3. 9.
     * 
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
     *                주어진 배열이 {@code null}인 경우
     * 
     * 
     * @since 2012. 3. 9.
     * 
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
     * 
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
     * 
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
     * 
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
     * 
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
     * 
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
     * 
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
     * 
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
     * 
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
     * 
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
     * 
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
     * 
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
     * 
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
     * 
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
     * 
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
     * 
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
     * 
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
     * 
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
     * 
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
     * 
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
     * 
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
     * 
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
     * 
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
     * 
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
     * 
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
     * 
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
     * 
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
     * 
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
     * 
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
            throw new IllegalArgumentException(
                    new NullPointerException("One of parameters(T[] array, T[] target, T[] data) must not be 'null': array=" + array + ", target=" + target + ", data=" + data));
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
     *            {@code T}가 동일한지를 판단하는 비교자
     * @return
     * 
     * @since 2012. 03. 30.
     * 
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
     * 
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
     * {@code <b>boolean</b>} 데이타를 포함하고 있는 배열의 순서를 역순으로 변경한 후 새로운 객체로 반환합니다.
     * 
     * @param array
     * @return
     * @since 2012. 02. 22.
     * 
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
     * {@code <b>byte</b>} 데이타를 포함하고 있는 배열의 순서를 역순으로 변경한 후 새로운 객체로 반환합니다.
     * 
     * @param array
     * @return
     * @since 2012. 02. 22.
     * 
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
     * {@code <b>char</b>} 데이타를 포함하고 있는 배열의 순서를 역순으로 변경한 후 새로운 객체로 반환합니다.
     * 
     * @param array
     * @return
     * @since 2012. 02. 22.
     * 
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
     * {@code <b>double</b>} 데이타를 포함하고 있는 배열의 순서를 역순으로 변경한 후 새로운 객체로 반환합니다.
     * 
     * @param array
     * @return
     * @since 2012. 02. 22.
     * 
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
     * {@code <b>float</b>} 데이타를 포함하고 있는 배열의 순서를 역순으로 변경한 후 새로운 객체로 반환합니다.
     * 
     * @param array
     * @return
     * @since 2012. 02. 22.
     * 
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
     * {@code <b>int</b>} 데이타를 포함하고 있는 배열의 순서를 역순으로 변경한 후 새로운 객체로 반환합니다.
     * 
     * @param array
     * @return
     * @since 2012. 02. 22.
     * 
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
     * {@code <b>long</b>} 데이타를 포함하고 있는 배열의 순서를 역순으로 변경한 후 새로운 객체로 반환합니다.
     * 
     * @param array
     * @return
     * @since 2012. 02. 22.
     * 
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
     * {@code <b>long</b>} 데이타를 포함하고 있는 배열의 순서를 역순으로 변경한 후 새로운 객체로 반환합니다.
     * 
     * @param array
     * @return
     * @since 2012. 02. 22.
     * 
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
     * @return
     * @since 2012. 02. 22.
     * 
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
     * 
     * @since 2012. 03. 20.
     * 
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

            splitedRanges = Arrays.copyOf(splitedRanges, splitedCount);

            boolean[][] newArray = new boolean[splitedRanges.length][];

            int[] range = null;
            splitedCount = 0;
            i = 0;
            for (; i < newArray.length; i++) {
                range = splitedRanges[i];
                if (range[1] != 0) {
                    newArray[splitedCount++] = Arrays.copyOfRange(array, range[0], range[0] + range[1]);
                }
            }

            return Arrays.copyOf(newArray, splitedCount);
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
     * 
     * @since 2012. 03. 20.
     * 
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

            splitedRanges = Arrays.copyOf(splitedRanges, splitedCount);

            byte[][] newArray = new byte[splitedRanges.length][];

            int[] range = null;
            splitedCount = 0;
            i = 0;
            for (; i < newArray.length; i++) {
                range = splitedRanges[i];
                if (range[1] != 0) {
                    newArray[splitedCount++] = Arrays.copyOfRange(array, range[0], range[0] + range[1]);
                }
            }

            return Arrays.copyOf(newArray, splitedCount);
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
     * 
     * @since 2012. 03. 20.
     * 
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

            splitedRanges = Arrays.copyOf(splitedRanges, splitedCount);

            char[][] newArray = new char[splitedRanges.length][];

            int[] range = null;
            splitedCount = 0;
            i = 0;
            for (; i < newArray.length; i++) {
                range = splitedRanges[i];
                if (range[1] != 0) {
                    newArray[splitedCount++] = Arrays.copyOfRange(array, range[0], range[0] + range[1]);
                }
            }

            return Arrays.copyOf(newArray, splitedCount);
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
     * 
     * @since 2012. 03. 20.
     * 
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

            splitedRanges = Arrays.copyOf(splitedRanges, splitedCount);

            double[][] newArray = new double[splitedRanges.length][];

            int[] range = null;
            splitedCount = 0;
            i = 0;
            for (; i < newArray.length; i++) {
                range = splitedRanges[i];
                if (range[1] != 0) {
                    newArray[splitedCount++] = Arrays.copyOfRange(array, range[0], range[0] + range[1]);
                }
            }

            return Arrays.copyOf(newArray, splitedCount);
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
     * 
     * @since 2012. 03. 20.
     * 
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

            splitedRanges = Arrays.copyOf(splitedRanges, splitedCount);

            int[][] newArray = new int[splitedRanges.length][];

            int[] range = null;
            splitedCount = 0;
            i = 0;
            for (; i < newArray.length; i++) {
                range = splitedRanges[i];
                if (range[1] != 0) {
                    newArray[splitedCount++] = Arrays.copyOfRange(array, range[0], range[0] + range[1]);
                }
            }

            return Arrays.copyOf(newArray, splitedCount);
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
     * 
     * @since 2012. 03. 20.
     * 
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

            splitedRanges = Arrays.copyOf(splitedRanges, splitedCount);

            long[][] newArray = new long[splitedRanges.length][];

            int[] range = null;
            splitedCount = 0;
            i = 0;
            for (; i < newArray.length; i++) {
                range = splitedRanges[i];
                if (range[1] != 0) {
                    newArray[splitedCount++] = Arrays.copyOfRange(array, range[0], range[0] + range[1]);
                }
            }

            return Arrays.copyOf(newArray, splitedCount);
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
     * 
     * @since 2012. 03. 20.
     * 
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

            splitedRanges = Arrays.copyOf(splitedRanges, splitedCount);

            short[][] newArray = new short[splitedRanges.length][];

            int[] range = null;
            splitedCount = 0;
            i = 0;
            for (; i < newArray.length; i++) {
                range = splitedRanges[i];
                if (range[1] != 0) {
                    newArray[splitedCount++] = Arrays.copyOfRange(array, range[0], range[0] + range[1]);
                }
            }

            return Arrays.copyOf(newArray, splitedCount);
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
     * 
     * @since 2012. 03. 20.
     * 
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
     * @param array
     * @param value
     *            구분자로 사용되는 데이타
     * @param equivalent
     *            클래스 또는 타입 T의 equals(T obj) 메소드가 아닌 다른 비교 기준을 제공하는 객체
     * @return 주어진 값으로 구분된 배열, 모든 값이 주어진 값과 동일한 경우 길이가 0 인 배열
     * 
     * 
     * @since 2012. 03. 20.
     * 
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

            splitedRanges = Arrays.copyOf(splitedRanges, splitedCount);

            T[][] newArray = (T[][]) Array.newInstance(array.getClass(), splitedRanges.length);

            int[] range = null;
            splitedCount = 0;
            i = 0;
            for (; i < newArray.length; i++) {
                range = splitedRanges[i];
                if (range[1] != 0) {
                    newArray[splitedCount++] = Arrays.copyOfRange(array, range[0], range[0] + range[1]);
                }
            }

            return Arrays.copyOf(newArray, splitedCount);

        } else {
            throw new IllegalArgumentException("A parameter(T[] array) must not be 'null'", new NullPointerException("array=null"));
        }
    }

    /**
     * 조건에 맞는 데이터 이후부터 끝까지 데이터를 새로운 배열로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 6. 24.     parkjunohng77@gmail.com         최초 작성
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
     * 
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
            return Arrays.copyOf(newArr, pos);
        }
    }

    /**
     * 첫 데이터부터 주어진 조건에 맞는 데이터까지 새로운 배열로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 6. 21.     parkjunohng77@gmail.com         최초 작성
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
     * 
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

        return Arrays.copyOf(newArr, pos);
    }

    /**
     * 첫 데이터부터 주어진 조건에 맞는 데이터 직전까지 새로운 배열로 제공합니다. <br>
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 6. 21.     parkjunohng77@gmail.com         최초 작성
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
     * 
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

        return Arrays.copyOf(newArr, pos);
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
     *                주어진 배열이 {@code null}인 경우
     * 
     * 
     * @since 2012. 3. 9.
     * 
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
     *                주어진 배열이 {@code null}인 경우
     * 
     * 
     * @since 2012. 3. 9.
     * 
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
     *                주어진 배열이 {@code null}인 경우
     * 
     * 
     * @since 2012. 3. 9.
     * 
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
     *                주어진 배열이 {@code null}인 경우
     * 
     * 
     * @since 2012. 3. 9.
     * 
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
     *                주어진 배열이 {@code null}인 경우
     * 
     * 
     * @since 2012. 3. 9.
     * 
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
     *                주어진 배열이 {@code null}인 경우
     * 
     * 
     * @since 2012. 3. 9.
     * 
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
     *                주어진 배열이 {@code null}인 경우
     * 
     * 
     * @since 2012. 3. 9.
     * 
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
     *                주어진 배열이 {@code null}인 경우
     * 
     * 
     * @since 2012. 3. 9.
     * 
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
     *                주어진 배열이 {@code null}인 경우
     * 
     * 
     * @since 2012. 3. 9.
     * 
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
     * 
     */
    public static String toString(Object[] array) {

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
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2023. 7. 25.     parkjunohng77@gmail.com         최초 작성
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
    public static <T, R> R[] transform(T[] arr, Function<T, R> f) {
        if (arr == null) {
            return null;
        }

        return (R[]) Stream.of(arr).map(f).toArray();
    }

    public static class EntryValue<K, V> implements Entry<K, V> {

        private K key;
        private @Nullable V value;

        /**
         * @param key
         * @param value
         * @since 2021. 8. 15.
         */
        public EntryValue(K key, @Nullable V value) {
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
         * 2021. 8. 15.     parkjunohng77@gmail.com         최초 작성
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
         * 2021. 8. 15.     parkjunohng77@gmail.com         최초 작성
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
        public @Nullable V getValue() {
            return this.value;
        }

        /**
         * <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜      | 작성자   |   내용
         * ------------------------------------------
         * 2021. 8. 15.     parkjunohng77@gmail.com         최초 작성
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
        public V setValue(@Nullable V value) {
            V v = this.value;
            this.value = value;
            return v;
        }

    }
}
