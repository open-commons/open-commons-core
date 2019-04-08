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
* This file is generated under this project, "open-commons-core".
*
* Date  : 2014. 7. 10. 오후 12:13:59
*
* Author: Park_Jun_Hong_(fafanmama_at_naver_com)
* 
*/

package open.commons.utils;

/**
 * 
 * @since 2014. 7. 10.
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
public class NumberUtils {

    // Prevet to create a new instance.
    private NumberUtils() {
    }

    /**
     * Return a radix used for <b><code>value</code></b>.
     * 
     * @param value
     * @return
     */
    public static int radix(String value) {

        char token = value.charAt(0);

        // detect 0(zero).
        if (token == '0') {
            // real Zero value.
            if (value.length() < 2) {
                return 10;
            }

            token = value.charAt(1);
            // detect 'Hexa'.
            if (token == 'X' || token == 'x') {
                return 0x10;
            }

            // decide 'Octal'.
            return 010;
        }

        return 10;
    }

    /**
     * 
     * @param value
     * @return
     * @throws NumberFormatException
     * 
     * @since 2014. 7. 10.
     * @see {@link #toDecimal(String, IntegerType)} - Used {@link IntegerType#INTEGER} or {@link IntegerType#Int} at
     *      this.
     */
    public static Number toDecimal(String value) throws NumberFormatException {
        return toDecimal(value, IntegerType.INTEGER);
    }

    /**
     * 
     * @param value
     * @param type
     * @return
     * 
     * @throws NumberFormatException
     * 
     * @since 2014. 7. 10.
     */
    public static Number toDecimal(String value, IntegerType type) throws NumberFormatException {
        if (value == null) {
            throw new IllegalArgumentException("The value MUST NOT be null.");
        }

        int radix = radix(value);
        value = value.replaceAll("(?i)0x", "");

        switch (type) {
            case BYTE:
            case Byte:
                return Byte.parseByte(value, radix);
            case SHORT:
            case Short:
                return Short.parseShort(value, radix);
            case INTEGER:
            case Int:
                return Integer.parseInt(value, radix);
            case LONG:
            case Long:
                return Long.parseLong(value, radix);
            default:
                throw new IllegalArgumentException(type.toString());
        }
    }

    public static enum IntegerType {
        // start - Wrapper Classes : 2014. 7. 10., Park_Jun_Hong_(fafanmama_at_naver_com)
        /** The type is <code>Byte.class</code> */
        BYTE(Byte.class), //
        /** The type is <code>Short.class</code> */
        SHORT(Short.class), //
        /** The type is <code>Integer.class</code> */
        INTEGER(Integer.class), //
        /** The type is <code>Long.class</code> */
        LONG(Long.class) //
        // end - Wrapper Classes : 2014. 7. 10.

        // start - primitive Classes : 2014. 7. 10., Park_Jun_Hong_(fafanmama_at_naver_com)
        /** The type is <code>byte.class</code> */
        ,Byte(byte.class), //
        /** The type is <code>short.class</code> */
        Short(short.class), //
        /** The type is <code>int.class</code> */
        Int(int.class), //
        /** The type is <code>long.class</code> */
        Long(long.class), //
        // end - primitive Classes : 2014. 7. 10.

        ;

        private Class<?> type;

        private IntegerType(Class<?> type) {
            this.type = type;
        }

        public Class<?> getNumberClass() {
            return type;
        }
    }
}
