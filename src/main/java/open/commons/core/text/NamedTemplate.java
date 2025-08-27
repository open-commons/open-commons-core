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
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;

import open.commons.core.TwoValueObject;
import open.commons.core.text.NamedTemplate.NamingParser.NamedToken;
import open.commons.core.utils.StreamUtils;

/**
 * The escape character is <b><code>$</code></b>.
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
 *  <code>
 *  public String getBirth() {
 *      return this.birth;
 *  }
 *  </code>
 * 
 * </pre>
 * 
 * <b>2014. 11. 10</b>
 * 
 * <pre>
 * </pre>
 * 
 * 
 * @since 2014. 4. 8.
 * @since 2014. 11. 10. Support '{?name}'. '?' means that {name} is optional.
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public class NamedTemplate {

    private String pattern;

    private boolean trim;
    /**
     * <b><code>key</code></b>: the name of a location at which a value is<br>
     * <b><code>value</code></b>: value
     */
    private Map<String, Object> values = new ConcurrentHashMap<String, Object>();

    public NamedTemplate(String pattern) {
        this(pattern, true);
    }

    /**
     * 
     * @param pattern
     *            a string represents a pattern
     * @param trim
     *            whether a name is trimmed or not.
     * @since 2014. 4. 17.
     */
    public NamedTemplate(String pattern, boolean trim) {
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
        this.values.put(trim ? name.trim() : name, value);

        return this;
    }

    public NamedTemplate addValues(Map<String, Object> values) {
        if (trim) {
            for (Entry<String, Object> entry : values.entrySet()) {
                this.values.put(entry.getKey().trim(), entry.getValue());
            }
        } else {
            this.values.putAll(values);

        }

        return this;
    }

    /**
     * Clear all values for template.
     * 
     *
     * @since 2014. 12. 2.
     */
    public void clear() {
        this.values.clear();
    }

    public String format() {
        return format(this.pattern, this.values, trim);
    }

    public String format(Map<String, Object> values, boolean attached) {
        if (attached) {
            addValues(values);
        } else {
            setValues(values);
        }

        return format(this.pattern, this.values, trim);
    }

    public NamedTemplate setValues(Map<String, Object> values) {
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
     * 2025. 8. 26.		박준홍			최초 작성
     * </pre>
     *
     * @param pattern
     * @return
     *
     * @since 2025. 8. 26.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static Set<TwoValueObject<String, Boolean>> getNames(String pattern) {
        NamingParser parser = new NamingParser(pattern, true);
        parser.parse();
        return StreamUtils.toSet(parser.tokens.stream(), NamedToken::isName, t -> new TwoValueObject<>(t.value(), t.isMandatory()));
    }

    static class NamingParser {
        String pattern;

        ArrayList<NamedToken> tokens = new ArrayList<NamedTemplate.NamingParser.NamedToken>();

        boolean trimmed = false;

        boolean parsed = false;

        private StringBuffer nameBuf = new StringBuffer();

        private StringBuffer nonameBuf = new StringBuffer();

        /**
         * 
         * @since 2014. 4. 8.
         */
        NamingParser(String pattern) {
            this(pattern, true);
        }

        /**
         * 
         * @param pattern
         * @param trim
         *            Whether the name of a location which a value is at is trimmed or not.
         * @since 2014. 4. 8.
         */
        NamingParser(String pattern, boolean trim) {
            this.pattern = pattern;
            this.trimmed = trim;
        }

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
                            , token.isMandatory ? new StringBuffer().append('{').append(name).append('}').toString() : ""));
                } else {
                    buf.append(name);
                }
            }

            return buf.toString();
        }

        Iterator<NamedToken> names() {
            return tokens.iterator();
        }

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
                                tokens.add(new NamedToken(trimmed ? nameBuf.toString().trim() : nameBuf.toString(), true));
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
                tokens.add(new NamedToken(nonameBuf.toString(), false));

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

        private static String value(String key, Map<String, Object> values, String defaultValue) {
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
             * @author Park Jun-Hong (parkjunhong77@gmail.com)
             *
             * @see java.lang.Object#equals(java.lang.Object)
             */
            @Override
            public boolean equals(Object obj) {
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
             * @author Park Jun-Hong (parkjunhong77@gmail.com)
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
             * @author Park Jun-Hong (parkjunhong77@gmail.com)
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
