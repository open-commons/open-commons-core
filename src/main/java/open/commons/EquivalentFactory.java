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
* Date  : 2012. 03. 22. 오후 5:19:39
*
* Author: Park Jun-Hong (parkjunhong77@gmail.com)
* 
*/
package open.commons;

import java.util.HashMap;
import java.util.Map;

/**
 * primitive 타입으로 이루어진 배열들을 비교해주는 {@link IEquivalent} 객체 팩토리 클래스
 * 
 * @since 2012. 03. 22.
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
public class EquivalentFactory {

    private static final IEquivalent<?> DEFAULT_EQUIV = new DefaultEquivalent<Object>();

    /**
     * boolean 으로 이루어진 배열들을 이루는 값들이 동일한 인덱스에 해당하는 값들끼리 모두 동일한지를 비교해준다.
     */
    private static final IEquivalent<boolean[]> booleanEquiv = new IEquivalent<boolean[]>() {

        @Override
        public boolean equals(boolean[] t1, boolean[] t2) {
            if (t1 != null || t2 != null) {
                int l = t1.length;

                if (t2.length != l) {
                    return false;
                }

                for (int i = 0; i < l; i++) {
                    if (t1[i] != t2[i]) {
                        return false;
                    }
                }
                return true;
            } else if (t1 != null || t2 != null) {
                return false;
            } else {
                return true;
            }
        }
    };

    /**
     * byte 로 이루어진 배열들을 이루는 값들이 동일한 인덱스에 해당하는 값들끼리 모두 동일한지를 비교해준다.
     */
    private static final IEquivalent<byte[]> byteEquiv = new IEquivalent<byte[]>() {

        @Override
        public boolean equals(byte[] t1, byte[] t2) {
            if (t1 != null || t2 != null) {
                int l = t1.length;

                if (t2.length != l) {
                    return false;
                }

                for (int i = 0; i < l; i++) {
                    if (t1[i] != t2[i]) {
                        return false;
                    }
                }
                return true;
            } else if (t1 != null || t2 != null) {
                return false;
            } else {
                return true;
            }
        }
    };

    /**
     * char 로 이루어진 배열들을 이루는 값들이 동일한 인덱스에 해당하는 값들끼리 모두 동일한지를 비교해준다.
     */
    private static final IEquivalent<char[]> charEquiv = new IEquivalent<char[]>() {

        @Override
        public boolean equals(char[] t1, char[] t2) {
            if (t1 != null || t2 != null) {
                int l = t1.length;

                if (t2.length != l) {
                    return false;
                }

                for (int i = 0; i < l; i++) {
                    if (t1[i] != t2[i]) {
                        return false;
                    }
                }
                return true;
            } else if (t1 != null || t2 != null) {
                return false;
            } else {
                return true;
            }
        }
    };

    /**
     * double 로 이루어진 배열들을 이루는 값들이 동일한 인덱스에 해당하는 값들끼리 모두 동일한지를 비교해준다.
     */
    private static final IEquivalent<double[]> doubleEquiv = new IEquivalent<double[]>() {

        @Override
        public boolean equals(double[] t1, double[] t2) {
            if (t1 != null || t2 != null) {
                int l = t1.length;

                if (t2.length != l) {
                    return false;
                }

                for (int i = 0; i < l; i++) {
                    if (t1[i] != t2[i]) {
                        return false;
                    }
                }
                return true;
            } else if (t1 != null || t2 != null) {
                return false;
            } else {
                return true;
            }
        }
    };

    private static final Map<Object, IEquivalent<?>> factory = new HashMap<Object, IEquivalent<?>>();

    /**
     * float 으로 이루어진 배열들을 이루는 값들이 동일한 인덱스에 해당하는 값들끼리 모두 동일한지를 비교해준다.
     */
    private static final IEquivalent<float[]> floatEquiv = new IEquivalent<float[]>() {

        @Override
        public boolean equals(float[] t1, float[] t2) {
            if (t1 != null || t2 != null) {
                int l = t1.length;

                if (t2.length != l) {
                    return false;
                }

                for (int i = 0; i < l; i++) {
                    if (t1[i] != t2[i]) {
                        return false;
                    }
                }
                return true;
            } else if (t1 != null || t2 != null) {
                return false;
            } else {
                return true;
            }
        }
    };

    /**
     * int 로 이루어진 배열들을 이루는 값들이 동일한 인덱스에 해당하는 값들끼리 모두 동일한지를 비교해준다.
     */
    private static final IEquivalent<int[]> intEquiv = new IEquivalent<int[]>() {

        @Override
        public boolean equals(int[] t1, int[] t2) {
            if (t1 != null || t2 != null) {
                int l = t1.length;

                if (t2.length != l) {
                    return false;
                }

                for (int i = 0; i < l; i++) {
                    if (t1[i] != t2[i]) {
                        return false;
                    }
                }
                return true;
            } else if (t1 != null || t2 != null) {
                return false;
            } else {
                return true;
            }
        }
    };

    /**
     * long 으로 이루어진 배열들을 이루는 값들이 동일한 인덱스에 해당하는 값들끼리 모두 동일한지를 비교해준다.
     */
    private static final IEquivalent<long[]> longEquiv = new IEquivalent<long[]>() {

        @Override
        public boolean equals(long[] t1, long[] t2) {
            if (t1 != null || t2 != null) {
                int l = t1.length;

                if (t2.length != l) {
                    return false;
                }

                for (int i = 0; i < l; i++) {
                    if (t1[i] != t2[i]) {
                        return false;
                    }
                }
                return true;
            } else if (t1 != null || t2 != null) {
                return false;
            } else {
                return true;
            }
        }
    };

    /**
     * short로 이루어진 배열들을 이루는 값들이 동일한 인덱스에 해당하는 값들끼리 모두 동일한지를 비교해준다.
     */
    private static final IEquivalent<short[]> shortEquiv = new IEquivalent<short[]>() {

        @Override
        public boolean equals(short[] t1, short[] t2) {
            if (t1 != null || t2 != null) {
                int l = t1.length;

                if (t2.length != l) {
                    return false;
                }

                for (int i = 0; i < l; i++) {
                    if (t1[i] != t2[i]) {
                        return false;
                    }
                }
                return true;
            } else if (t1 != null || t2 != null) {
                return false;
            } else {
                return true;
            }

        }
    };

    /**
     * boolean 타입 배열을 비교하는 {@link IEquivalent}객체를 반환합니다.
     * 
     * @return <BR>
     * @since 2012. 03. 22.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static IEquivalent<boolean[]> booleanArrayEquiv() {

        return booleanEquiv;
    }

    /**
     * byte 타입 배열을 비교하는 {@link IEquivalent}객체를 반환합니다.
     * 
     * @return <BR>
     * @since 2012. 03. 22.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static IEquivalent<byte[]> byteArrayEquiv() {

        return byteEquiv;
    }

    /**
     * char 타입 배열을 비교하는 {@link IEquivalent}객체를 반환합니다.
     * 
     * @return <BR>
     * @since 2012. 03. 22.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static IEquivalent<char[]> charArrayEquiv() {

        return charEquiv;
    }

    /**
     * double 타입 배열을 비교하는 {@link IEquivalent}객체를 반환합니다.
     * 
     * @return <BR>
     * @since 2012. 03. 22.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static IEquivalent<double[]> doubleArrayEquiv() {

        return doubleEquiv;
    }

    /**
     * float 타입 배열을 비교하는 {@link IEquivalent}객체를 반환합니다.
     * 
     * @return <BR>
     * @since 2012. 03. 22.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static IEquivalent<float[]> floatArrayEquiv() {

        return floatEquiv;
    }

    /**
     * 주어진 파라미터에 연결된 비교자를 반환합니다.
     * 
     * @param key
     * @return
     * 
     * @since 2012. 03. 30.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static IEquivalent<?> get(Object key) {
        synchronized (factory) {
            return factory.get(key);
        }
    }

    /**
     * 기본 비교자를 반환합니다.
     * 
     * @return
     * 
     * @since 2012. 03. 30.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    @SuppressWarnings("unchecked")
    public static <T> IEquivalent<T> getDefault() {
        return (IEquivalent<T>) DEFAULT_EQUIV;
    }

    /**
     * int 타입 배열을 비교하는 {@link IEquivalent}객체를 반환합니다.
     * 
     * @return <BR>
     * @since 2012. 03. 22.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static IEquivalent<int[]> intArrayEquiv() {

        return intEquiv;
    }

    /**
     * long 타입 배열을 비교하는 {@link IEquivalent}객체를 반환합니다.
     * 
     * @return <BR>
     * @since 2012. 03. 22.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static IEquivalent<long[]> longArrayEquiv() {

        return longEquiv;
    }

    /**
     * 새로운 비교자를 등록합니다.
     * 
     * @param key
     * @param equivalent
     * @return
     * 
     * @since 2012. 03. 30.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static IEquivalent<?> register(Object key, IEquivalent<?> equivalent) {
        synchronized (factory) {
            if (key != null) {
                return factory.put(key, equivalent);
            } else {
                return null;
            }
        }
    }

    /**
     * short 타입 배열을 비교하는 {@link IEquivalent}객체를 반환합니다.
     * 
     * @return <BR>
     * @since 2012. 03. 22.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static IEquivalent<short[]> shortArrayEquiv() {

        return shortEquiv;
    }
}
