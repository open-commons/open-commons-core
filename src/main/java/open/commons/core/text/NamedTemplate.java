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
* Date  : 2014. 4. 17. 오전 11:47:12
*
* Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
* 
*/

package open.commons.core.text;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;

import org.jspecify.annotations.Nullable;

import open.commons.core.TwoValueObject;
import open.commons.core.utils.AssertUtils2;
import open.commons.core.utils.StreamUtils;

/**
 * The escape character is <b>{@code $}</b>.
 * 
 * <pre>
 *  String pattern = "{my name}: Hi~. My is {my name}. What's your name?\n{your_name}: Hi~. My name is {your_name}. Nice to meet you.\n{my name}: Me too!";
 *  
 *  Map<String, Object> values = new HashMap<String, Object>();
 *  values.put("my name", "Young Su");
 *  values.put("your_name", "Cheol Su");
 *  
 *  System.out.println(NamedTemplate.format(pattern, values));
 *  ------------------
 *  (result): 
 *  Young Su: Hi~. My is Young Su. What's your name?
 *  Cheol Su: Hi~. My name is Cheol Su. Nice to meet you.
 *  Young Su: Me too!
 *  
 *  ---------------------
 *  NamedTemplate getter = new NamedTemplate("public {returnType} get{name}() ${\n\treturn this.{var};\n$}", true);
 *  getter.addValue("returnType", "String");
 *  getter.addValue("name", "Birth");
 *  getter.addValue("var", "birdh");
 *  
 *  System.out.println(getter.format());
 *  --------------------
 *  (result):
 *  {@code
 * 
 * public String getBirth() {
 *     return this.birth;
 * }
 * }
 * </pre>
 * 
 * <br>
 * 
 * <pre>
 * [개정이력]
 *      날짜    	| 작성자			|	내용
 * ------------------------------------------
 * 2014. 4. 8.     parkjunhong77@gmail.com     최초 작성
 * 2014. 11. 10.    parkjunhong77@gmail.com     Support '{?name}'. '?' means that {name} is optional.
 * </pre>
 * 
 * @since 2014. 4. 8.
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
public class NamedTemplate {

    private String pattern;

    private boolean trim;
    /**
     * <b>{@code key}</b>: the name of a location at which a value is<br>
     * <b>{@code value}</b>: value
     */
    private Map<String, Object> values = new ConcurrentHashMap<String, Object>();

    /**
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2014. 4. 17.     parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param pattern
     *            a string represents a pattern
     *
     * @since 2014. 4. 17.
     */
    public NamedTemplate(String pattern) {
        this(pattern, true);
    }

    /**
     * 
     * @param pattern
     *            a string represents a pattern
     * @param trim
     *            whether a name is trimmed or not.
     * 
     * @since 2014. 4. 17.
     */
    public NamedTemplate(String pattern, boolean trim) {
        Objects.requireNonNull(pattern);

        this.pattern = pattern;
        this.trim = trim;
    }

    /**
     * Add a value and its name.
     * 
     * @param name
     *            the name of a location at which a value is.
     * @param value
     *            a value
     * @return
     * 
     * @since 2014. 4. 23.
     */
    public NamedTemplate addValue(String name, Object value) {
        Objects.requireNonNull(name);

        this.values.put(trim ? name.strip() : name, value);

        return this;
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2014. 4. 13.         parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param newValues
     * @return
     *
     * @since 2014. 4. 13.
     */
    public NamedTemplate addValues(Map<String, Object> newValues) {
        Objects.requireNonNull(newValues);

        newValues.forEach((name, value) -> {
            Objects.requireNonNull(name);

            if (trim) {
                name = name.strip();
            }
            this.values.put(name, value);
        });

        return this;
    }

    /**
     * Clear all values for template.
     * 
     * @since 2014. 12. 2.
     */
    public void clear() {
        this.values.clear();
    }

    public String format() {
        return format(this.pattern, this.values, trim);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2014. 4. 13.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param values
     *            새로운 데이터
     * @param attached
     *            기존 데이터에 추가 여부. <b><i>{@code false}</i></b>인 경우, 새로운 데이터로 변경.
     * @return
     *
     * @since 2014. 4. 13.
     * 
     * @see #addValues(Map)
     * @see #setValues(Map)
     */
    public String format(Map<String, Object> values, boolean attached) {
        if (attached) {
            addValues(values);
        } else {
            setValues(values);
        }

        return format(this.pattern, this.values, trim);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2014. 4. 13.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param values
     * @return
     *
     * @since 2014. 4. 13.
     */
    public NamedTemplate setValues(Map<String, Object> values) {
        Objects.requireNonNull(values);

        this.values.clear();

        addValues(values);

        return this;
    }

    /**
     * 
     * @param pattern
     *            a string represents a pattern
     * @param values
     *            data
     * @return
     * 
     * @since 2014. 4. 8.
     * 
     * @see #format(String, Map, boolean)
     */
    public static String format(String pattern, Map<String, Object> values) {
        AssertUtils2.notNulls(pattern, values);

        return format(pattern, values, true);
    }

    /**
     * 
     * @param pattern
     *            a string represents a pattern
     * @param values
     *            data
     * @param trim
     *            whether a name is trimmed or not.
     * @return
     * 
     * @since 2014. 4. 8.
     */
    public static String format(String pattern, Map<String, Object> values, boolean trim) {
        AssertUtils2.notNull(pattern);
        AssertUtils2.notNulls(values.keySet());

        NamingParser parser = new NamingParser(pattern, trim);
        parser.parse();

        ConcurrentMap<String, Object> cvalues = null;
        if (values instanceof ConcurrentMap) {
            cvalues = (ConcurrentMap<String, Object>) values;
        } else {
            cvalues = new ConcurrentHashMap<String, Object>();
            cvalues.putAll(values);
        }

        return parser.format(cvalues);
    }

    /**
     * For just single data.
     * 
     * @param pattern
     *            a string represents a pattern
     * @param name
     * @param value
     * @return
     *
     * @since 2014. 9. 5.
     */
    public static String format(String pattern, String name, Object value) {
        AssertUtils2.notNulls(pattern, name);

        Map<String, Object> values = new ConcurrentSkipListMap<String, Object>();
        values.put(name, value);
        return format(pattern, values, true);
    }

    /**
     * 주어진 패턴에 설정된 '이름' 값을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 26.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param pattern
     * @return
     * @since 2025. 8. 26.
     * @version 2.1.0
     */
    // 아래 내용에 적용됨.
    // - parser.tokens.stream()
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static Set<TwoValueObject<String, Boolean>> getNames(String pattern) {
        Objects.requireNonNull(pattern);

        NamingParser parser = new NamingParser(pattern, true);
        parser.parse();
        return StreamUtils.toSet(parser.tokens.stream() //
                , token -> token != null ? token.isName() : false //
                , t -> new TwoValueObject<>(t.value(), t.isMandatory())//
        );
    }

    static class NamingParser {
        String pattern;

        ArrayList<NamedToken> tokens = new ArrayList<NamedTemplate.NamingParser.NamedToken>();

        boolean trimmed = false;

        boolean parsed = false;

        private StringBuilder nameBuf = new StringBuilder();

        private StringBuilder nonameBuf = new StringBuilder();

        /**
         * @param pattern
         *            문자열 패턴
         * 
         * @since 2014. 4. 8.
         */
        NamingParser(String pattern) {
            this(pattern, true);
        }

        /**
         * 
         * @param pattern
         *            문자열 패턴
         * 
         * @param trim
         *            '데이터 이름'에 trim() 을 적용할지 여부.
         * 
         * @since 2014. 4. 8.
         */
        NamingParser(String pattern, boolean trim) {
            this.pattern = pattern;
            this.trimmed = trim;
        }

        // 아래 내용에 적용됨.
        // - String.join(name, "{", "}")
        // - buf.toString();
        // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
        // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
        @SuppressWarnings("null")
        String format(ConcurrentMap<String, Object> values) {
            if (!parsed) {
                this.tokens.clear();
                parse();
            }

            StringBuffer buf = new StringBuffer();

            String name = null;
            for (NamedToken token : tokens) {
                name = token.value();

                if (token.isName()) {
                    buf.append(value(name, values //
                            , token.isMandatory //
                                    ? String.join(name, "{", "}") //
                                    : ""));
                } else {
                    buf.append(name);
                }
            }

            return buf.toString();
        }

        // 아래 내용에 적용됨.
        // - tokens.iterator();
        // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
        // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
        @SuppressWarnings("null")
        Iterator<NamedToken> names() {
            return tokens.iterator();
        }

        // 아래 내용에 적용됨.
        // - StringBuilder.toString()
        // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
        // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
        @SuppressWarnings("null")
        void parse() {

            nameBuf.setLength(0);
            nonameBuf.setLength(0);

            char[] charArr = pattern.toCharArray();

            boolean opened = false;
            boolean escape = false;

            int index = 0;
            for (char c : charArr) {
                // opened
                if (c == '{') {

                    if (escape) {
                        store(opened, c);

                        escape = false;
                    } else {

                        if (opened) {
                            throw new IllegalStateException("DID NOT CLOSE. But opening... at " + index + " (" + pattern + ")");
                        }

                        if (nonameBuf.length() > 0) {
                            tokens.add(new NamedToken(nonameBuf.toString(), false));
                            nonameBuf.setLength(0);
                        }

                        opened = true;
                    }
                } else
                // closed
                if (c == '}') {
                    if (escape) {
                        store(opened, c);

                        escape = false;
                    } else {
                        if (opened) {
                            if (nameBuf.length() > 0) {
                                tokens.add(new NamedToken(trimmed ? nameBuf.toString().strip() : nameBuf.toString(), true));
                                nameBuf.setLength(0);
                            }
                        } else {
                            throw new IllegalStateException("DID NOT OPEN. But closing.... at " + index + " (" + pattern + ")");
                        }

                        opened = false;
                    }
                } else
                // escape
                if (c == '$') {
                    if (escape) {
                        store(opened, c);

                        escape = false;
                    } else {
                        escape = true;
                    }
                } else
                // name
                {
                    if (opened) {
                        nameBuf.append(c);
                    } else {
                        nonameBuf.append(c);
                    }
                }

                index++;
            }

            if (nonameBuf.length() > 0) {
                tokens.add(new NamedToken(Objects.requireNonNull(nonameBuf.toString()), false));

                nonameBuf.setLength(0);
            }

            if (opened) {
                throw new IllegalStateException("Opend. But DID NOT CLOSE...");
            }

            parsed = true;

        }

        private void store(boolean opened, char c) {
            if (opened) {
                nameBuf.append(c);
            } else {
                nonameBuf.append(c);
            }
        }

        // 아래 내용에 적용됨.
        // - value != null ? value.toString() : defaultValue;
        // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
        // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
        @SuppressWarnings("null")
        private static String value(String key, Map<String, Object> values, String defaultValue) {
            AssertUtils2.notNulls(key, values);

            Object value = values.get(key);

            return value != null ? value.toString() : defaultValue;
        }

        class NamedToken {
            String value;
            boolean isName;
            boolean isMandatory;

            NamedToken(String name, boolean isName) {
                this.value = name;
                this.isName = isName;

                this.isMandatory = name.startsWith("?") ? false : true;
            }

            /**
             *
             * @since 2025. 8. 26.
             * @version 2.1.0
             * 
             * @see java.lang.Object#equals(java.lang.Object)
             */
            @Override
            public boolean equals(@Nullable Object obj) {
                if (this == obj)
                    return true;
                if (obj == null)
                    return false;
                if (getClass() != obj.getClass())
                    return false;
                NamedToken other = (NamedToken) obj;
                if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
                    return false;
                return isName == other.isName && Objects.equals(value, other.value);
            }

            private NamingParser getEnclosingInstance() {
                return NamingParser.this;
            }

            /**
             *
             * @since 2025. 8. 26.
             * @version 2.1.0
             * 
             * @see java.lang.Object#hashCode()
             */
            @Override
            public int hashCode() {
                final int prime = 31;
                int result = 1;
                result = prime * result + getEnclosingInstance().hashCode();
                result = prime * result + Objects.hash(isName, value);
                return result;
            }

            /**
             * @since 2025. 8. 26.
             * @version 2.1.0
             */
            public boolean isMandatory() {
                return isMandatory;
            }

            /**
             * 
             * @return the is
             * 
             * @since 2014. 4. 8.
             */
            public boolean isName() {
                return isName;
            }

            /**
             * @see java.lang.Object#toString()
             */
            @Override
            public String toString() {
                return "NamedToken [value=" + value + ", isName=" + isName + "]";
            }

            /**
             * 
             * @return the name
             * 
             * @since 2014. 4. 8.
             */
            public String value() {
                return value;
            }
        }
    }
}
