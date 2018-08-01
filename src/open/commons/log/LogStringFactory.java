/*
 * Copyright 2011 Park Jun-Hong_(fafanmama_at_naver_com)
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
* Date  : 2012. 7. 24. 오후 1:49:01
*
* Author: Park_Jun_Hong_(fafanmama_at_naver_com)
*
* File  : LogString.java 
* 
*/
package open.commons.log;

import java.util.List;
import java.util.Vector;

import open.commons.utils.ArrayUtils;

public class LogStringFactory {

    /**
     * Returns a {@link LogStringContainer} with the given parameters.
     * 
     * @param cf
     *            the max width of 'category' format. Zero(0) means no ordering.
     * @param pf
     *            the max width of 'property' format. Zero(0) means no ordering.
     * @param vf
     *            the max width of 'value' format. Zero(0) means no ordering.
     * @return
     */
    public static LogStringContainer getContainer(int cf, int pf, int vf) {
        return new LogStringContainer(cf, pf, vf);
    }

    /**
     * Returns a {@link LogStringContainer} with the given parameters.
     * 
     * @param cf
     *            the max width of 'category' format. Zero(0) means no ordering.
     * @param pf
     *            the max width of 'property' format. Zero(0) means no ordering.
     * @param vf
     *            the max width of 'value' format. Zero(0) means no ordering.
     * @param delim
     *            the delimiter between log-strings.
     * @return
     */
    public static LogStringContainer getContainer(int cf, int pf, int vf, String delim) {
        return new LogStringContainer(cf, pf, vf, delim);
    }

    /**
     * Returns a {@link LogStringContainer} with the given parameter.
     * 
     * @param delim
     *            the delimiter between log-strings.
     * @return
     */
    public static LogStringContainer LogStringContainer(String delim) {
        return new LogStringContainer(delim);
    }

    /**
     * Log String Element Container
     * 
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * 
     */
    public static class LogStringContainer {

        private List<LogStringElement> elems = new Vector<LogStringFactory.LogStringElement>();

        private String delimiter = "";

        /** format of <b><code>category</code></b> */
        private String categoryFormat = "";
        /** format of <b><code>proeprty</code></b> */
        private String propertyFormat = "";
        /** format of <b><code>value</code></b> */
        private String valueFormat = "";

        private String format;

        // start - Original Values : 2012. 7. 27.
        private final String originDelimiter;
        /** format of <b><code>category</code></b> */
        private final String originCategoryFormat;
        /** format of <b><code>proeprty</code></b> */
        private final String originPropertyFormat;
        /** format of <b><code>value</code></b> */
        private final String originValueFormat;

        private final String originFormat;
        // end - Original Values : 2012. 7. 27.

        private Object mtxFormat = new Object();

        // private Object mtx

        private boolean isSetCategory = false;

        private LogStringContainer(int cf, int pf, int vf) {
            this(cf, pf, vf, "");
        }

        private LogStringContainer(int cf, int pf, int vf, String delim) {
            categoryFormat = cf < 1 ? "%s" : "%" + String.valueOf(cf + 2) + "s";
            propertyFormat = pf < 1 ? "%s" : "%" + String.valueOf(pf) + "s";
            valueFormat = vf < 1 ? "%s" : "%" + String.valueOf(vf) + "s";
            delimiter = delim;

            updateFormat();

            originCategoryFormat = categoryFormat;
            originPropertyFormat = propertyFormat;
            originValueFormat = valueFormat;
            originFormat = format;
            originDelimiter = delimiter;
        }

        private LogStringContainer(String delim) {
            this(0, 0, 0, delim);
        }

        public LogStringContainer add(LogStringElement lse) {

            if (lse != null) {
                elems.add(lse);
            }

            return this;
        }

        /**
         * 
         * @param p
         *            property
         * @param v
         *            value
         * @return
         */
        public LogStringContainer add(String p, Object v) {
            if (p != null) {
                elems.add(new LogStringElement("", p, v));
            }

            return this;
        }

        /**
         * 
         * @param c
         *            category
         * @param p
         *            property
         * @param v
         *            value
         * @return
         */
        public LogStringContainer add(String c, String p, Object v) {
            if (c != null && p != null) {
                elems.add(new LogStringElement(c, p, v));
            }

            return this;
        }

        public void clear() {
            elems.clear();
        }

        public String flush() {
            int ls = elems.size();

            char[] formatsCharArray = null;
            String[] lseCharArray = new String[3 * ls];

            LogStringElement elem = null;
            for (int i = 0; i < ls; i++) {
                elem = elems.get(i);
                System.arraycopy(elem.values(), 0, lseCharArray, 3 * i, 3);

                setEnableCategory(elem.isCategory());

                formatsCharArray = ArrayUtils.merge(formatsCharArray // latest format
                        , (formatsCharArray != null ? delimiter + format : format).toCharArray());
            }

            elems.clear();

            return String.format(String.valueOf(formatsCharArray), lseCharArray);
        }

        /**
         * @return the cformat
         */
        public String getCategoryFormat() {
            return categoryFormat;
        }

        /**
         * @return the delimiter
         */
        public String getDelimiter() {
            return delimiter;
        }

        /**
         * @return the lses
         */
        public List<LogStringElement> getLogStrings() {
            Vector<LogStringElement> cloned = new Vector<LogStringFactory.LogStringElement>();

            for (LogStringElement el : elems) {
                try {
                    cloned.add(el.clone());
                } catch (CloneNotSupportedException ignored) {
                    ignored.printStackTrace();
                }
            }

            return cloned;
        }

        /**
         * @return the pformat
         */
        public String getPropertyFormat() {
            return propertyFormat;
        }

        /**
         * @return the vformat
         */
        public String getValueFormat() {
            return valueFormat;
        }

        public LogStringContainer remove(LogStringElement lse) {
            if (lse != null) {
                int hashCode = lse.hashCode();
                for (LogStringElement l : elems) {
                    if (hashCode == l.hashCode()) {
                        elems.remove(l);
                        break;
                    }
                }
            }

            return this;
        }

        public void reset() {
            categoryFormat = originCategoryFormat;
            propertyFormat = originPropertyFormat;
            valueFormat = originValueFormat;
            delimiter = originDelimiter;
            format = originFormat;

            isSetCategory = false;
        }

        /**
         * @param cf
         *            format for category to set
         */
        public void setCategoryFormat(int cf) {
            categoryFormat = toFormat(cf);

            updateFormat();
        }

        /**
         * @param delimiter
         *            the delimiter to set
         */
        public void setDelimiter(String delimiter) {
            this.delimiter = delimiter;

            updateFormat();
        }

        private void setEnableCategory(boolean enable) {

            if (isSetCategory) {
                if (!enable) {

                    String cf = categoryFormat.replaceAll("[%|s|\\[|\\]]", "");

                    if (cf.trim().length() < 1) {
                        categoryFormat = "%s";
                    } else {
                        int cfInt = Integer.parseInt(cf);
                        categoryFormat = "%" + (cfInt += cfInt > 0 ? 2 : -2) + "s";
                    }

                    isSetCategory = enable;
                    updateFormat();
                }
            } else {
                if (enable) {

                    String cf = categoryFormat.replaceAll("[%|s]", "");

                    if (cf.trim().length() < 1) {
                        categoryFormat = "[%2s]";
                    } else {
                        int cfInt = Integer.parseInt(cf);
                        categoryFormat = "[%" + (cfInt += cfInt > 0 ? -2 : 2) + "s]";
                    }

                    isSetCategory = enable;
                    updateFormat();
                }
            }
        }

        /**
         * @param propertyFormat
         *            format for property to set
         */
        public void setPropertyFormat(int pf) {
            propertyFormat = toFormat(pf);

            updateFormat();
        }

        /**
         * @param valueFormat
         *            format for value to set
         */
        public void setValueFormat(int vf) {
            valueFormat = toFormat(vf);

            updateFormat();
        }

        private String toFormat(int l) {
            return l < 1 ? "%s" : "%" + String.valueOf(l) + "s";
        }

        /**
         * update the format string.
         * 
         * @return
         */
        private String updateFormat() {
            synchronized (mtxFormat) {
                return format = categoryFormat + "." + propertyFormat + ": " + valueFormat;
            }
        }
    }

    /**
     * Log String Element
     * 
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * 
     */
    public static class LogStringElement implements Cloneable {
        public final String category;
        public final String property;
        public final String value;

        /**
         * 
         * @param c
         *            category
         * @param p
         *            property
         * @param v
         *            value
         */
        public LogStringElement(String c, String p, Object v) {
            if (c != null && p != null) {
                category = c.trim();
                property = p.trim();
                value = v != null ? v.toString().trim() : "NULL_STRING";
            } else {
                throw new IllegalArgumentException("All of arguments must not be 'null'. c=" + c + ", p=" + p + ", v=" + v);
            }
        }

        /**
         * 
         * @see java.lang.Object#clone()
         */
        @Override
        protected LogStringElement clone() throws CloneNotSupportedException {
            return new LogStringElement(category, property, value);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            LogStringElement other = (LogStringElement) obj;
            if (category == null) {
                if (other.category != null)
                    return false;
            } else if (!category.equals(other.category))
                return false;
            if (property == null) {
                if (other.property != null)
                    return false;
            } else if (!property.equals(other.property))
                return false;
            if (value == null) {
                if (other.value != null)
                    return false;
            } else if (!value.equals(other.value))
                return false;
            return true;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((category == null) ? 0 : category.hashCode());
            result = prime * result + ((property == null) ? 0 : property.hashCode());
            result = prime * result + ((value == null) ? 0 : value.hashCode());
            return result;
        }

        public boolean isCategory() {
            return category.length() < 1 ? false : true;
        }

        @Override
        public String toString() {
            return "category: " + category + ", property: " + property + ", value: " + value;
        }

        public String[] values() {
            return new String[] { category, property, value };
        }
    }
}
