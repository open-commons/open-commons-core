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
package open.commons.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.jspecify.annotations.Nullable;

/**
 * primitive 타입으로 이루어진 배열들을 비교해주는 {@link IEquivalent} 객체 팩토리 클래스
 * 
 * @since 2012. 03. 22.
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */
public class EquivalentFactory {

    private static final IEquivalent<?> DEFAULT_EQUIV = new DefaultEquivalent<@Nullable Object>();

    /**
     * boolean 으로 이루어진 배열들을 이루는 값들이 동일한 인덱스에 해당하는 값들끼리 모두 동일한지를 비교해준다.
     */
    private static final IEquivalent<boolean @Nullable []> booleanEquiv = new IEquivalent<boolean @Nullable []>() {

        @Override
        public boolean equals(boolean @Nullable [] t1, boolean @Nullable [] t2) {
            if (t1 != null && t2 != null) {
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
    private static final IEquivalent<byte @Nullable []> byteEquiv = new IEquivalent<byte @Nullable []>() {

        @Override
        public boolean equals(byte @Nullable [] t1, byte @Nullable [] t2) {
            if (t1 != null && t2 != null) {
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
    private static final IEquivalent<char @Nullable []> charEquiv = new IEquivalent<char @Nullable []>() {

        @Override
        public boolean equals(char @Nullable [] t1, char @Nullable [] t2) {
            if (t1 != null && t2 != null) {
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
    private static final IEquivalent<double @Nullable []> doubleEquiv = new IEquivalent<double @Nullable []>() {

        @Override
        public boolean equals(double @Nullable [] t1, double @Nullable [] t2) {
            if (t1 != null && t2 != null) {
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
    private static final IEquivalent<float @Nullable []> floatEquiv = new IEquivalent<float @Nullable []>() {

        @Override
        public boolean equals(float @Nullable [] t1, float @Nullable [] t2) {
            if (t1 != null && t2 != null) {
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
    private static final IEquivalent<int @Nullable []> intEquiv = new IEquivalent<int @Nullable []>() {

        @Override
        public boolean equals(int @Nullable [] t1, int @Nullable [] t2) {
            if (t1 != null && t2 != null) {
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
    private static final IEquivalent<long @Nullable []> longEquiv = new IEquivalent<long @Nullable []>() {

        @Override
        public boolean equals(long @Nullable [] t1, long @Nullable [] t2) {
            if (t1 != null && t2 != null) {
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
    private static final IEquivalent<short @Nullable []> shortEquiv = new IEquivalent<short @Nullable []>() {

        @Override
        public boolean equals(short @Nullable [] t1, short @Nullable [] t2) {
            if (t1 != null && t2 != null) {
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
     * 
     */
    public static IEquivalent<boolean[]> booleanArrayEquiv() {

        return booleanEquiv;
    }

    /**
     * byte 타입 배열을 비교하는 {@link IEquivalent}객체를 반환합니다.
     * 
     * @return <BR>
     * @since 2012. 03. 22.
     * 
     */
    public static IEquivalent<byte[]> byteArrayEquiv() {

        return byteEquiv;
    }

    /**
     * char 타입 배열을 비교하는 {@link IEquivalent}객체를 반환합니다.
     * 
     * @return <BR>
     * @since 2012. 03. 22.
     * 
     */
    public static IEquivalent<char[]> charArrayEquiv() {

        return charEquiv;
    }

    /**
     * double 타입 배열을 비교하는 {@link IEquivalent}객체를 반환합니다.
     * 
     * @return <BR>
     * @since 2012. 03. 22.
     * 
     */
    public static IEquivalent<double[]> doubleArrayEquiv() {

        return doubleEquiv;
    }

    /**
     * float 타입 배열을 비교하는 {@link IEquivalent}객체를 반환합니다.
     * 
     * @return <BR>
     * @since 2012. 03. 22.
     * 
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
     * 
     */
    public static @Nullable IEquivalent<?> get(Object key) {
        Objects.requireNonNull(key);

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
     * 
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
     * 
     */
    public static IEquivalent<int @Nullable []> intArrayEquiv() {
        return intEquiv;
    }

    /**
     * long 타입 배열을 비교하는 {@link IEquivalent}객체를 반환합니다.
     * 
     * @return <BR>
     * @since 2012. 03. 22.
     * 
     */
    public static IEquivalent<long @Nullable []> longArrayEquiv() {
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
     * 
     */
    public static @Nullable IEquivalent<?> register(@Nullable Object key, IEquivalent<?> equivalent) {
        Objects.requireNonNull(equivalent);

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
     * 
     */
    public static IEquivalent<short[]> shortArrayEquiv() {

        return shortEquiv;
    }
}
