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
* This file is generated under this project, "kr.ymtech.sdn.openiris".
*
* Date  : 2014. 5. 2. 오후 5:23:26
*
* Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
* 
*/

package open.commons.lang;

import open.commons.doc.IndentationFactory;
import open.commons.doc.IndentationFactory.Indentation;

/**
 * A hashcode of {@link Object#toString()} value.
 * 
 * @since 2014. 5. 2.
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public class ToStringHashCode {

    private static StringBuffer hashcodeStr = new StringBuffer();

    private static Indentation indent = null;

    private static String __toHashcode(Class<?>... classes) {

        for (Class<?> clazz : classes) {
            hashcodeStr.append("\n" + indent.toString() + "/** the value of " + clazz.getName() + ".class.toString().hashCode() */\n");
            hashcodeStr.append(indent.toString() + "public static final int " + clazz.getName().replace('.', '_').toUpperCase() + " = " + clazz.toString().hashCode() + ";\n");
        }

        return hashcodeStr.toString();
    }

    private static void _primitives(String type) {
        indent = IndentationFactory.newInstance("    ");

        hashcodeStr.append(indent.toString() + "public static final class " + type + " {\n");

        indent.inc();
        __toHashcode(boolean.class, Boolean.class //
                , byte.class, Byte.class //
                , char.class, Character.class //
                , int.class, Integer.class //
                , long.class, Long.class //
                , float.class, Float.class //
                , double.class, Double.class //
        );

        indent.dec();

        hashcodeStr.append(indent.toString() + "}\n");

    }

    private static void flush() {
        System.out.println(hashcodeStr.toString());
        hashcodeStr.setLength(0);
    }

    public static void main(String[] args) {
        _primitives("Primitives");

        flush();
    }

    public static final class Primitives {

        /** the value of boolean.class.toString().hashCode() */
        public static final int BOOLEAN = 64711720;

        /** the value of java.lang.Boolean.class.toString().hashCode() */
        public static final int JAVA_LANG_BOOLEAN = 1335156652;

        /** the value of byte.class.toString().hashCode() */
        public static final int BYTE = 3039496;

        /** the value of java.lang.Byte.class.toString().hashCode() */
        public static final int JAVA_LANG_BYTE = -1228850172;

        /** the value of char.class.toString().hashCode() */
        public static final int CHAR = 3052374;

        /** the value of java.lang.Character.class.toString().hashCode() */
        public static final int JAVA_LANG_CHARACTER = -1603904083;

        /** the value of int.class.toString().hashCode() */
        public static final int INT = 104431;

        /** the value of java.lang.Integer.class.toString().hashCode() */
        public static final int JAVA_LANG_INTEGER = -1066470206;

        /** the value of long.class.toString().hashCode() */
        public static final int LONG = 3327612;

        /** the value of java.lang.Long.class.toString().hashCode() */
        public static final int JAVA_LANG_LONG = -1228562056;

        /** the value of float.class.toString().hashCode() */
        public static final int FLOAT = 97526364;

        /** the value of java.lang.Float.class.toString().hashCode() */
        public static final int JAVA_LANG_FLOAT = 563652320;

        /** the value of double.class.toString().hashCode() */
        public static final int DOUBLE = -1325958191;

        /** the value of java.lang.Double.class.toString().hashCode() */
        public static final int JAVA_LANG_DOUBLE = 239044557;

    }
}
