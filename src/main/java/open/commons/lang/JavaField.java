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

package open.commons.lang;

import open.commons.utils.StringUtils;

public class JavaField {

    private String accessor = "";
    private String type;
    private String var;
    private String value;
    private String comment = "";

    public JavaField(String type, String var) {
        this(null, type, var, null, null);
    }

    public JavaField(String type, String var, String val) {
        this(null, type, var, val, null);
    }

    public JavaField(String acc, String type, String var, String val, String comment) {
        accessor = acc;
        this.type = type;
        this.var = var;
        this.value = val;
        this.comment = comment;
    }

    /**
     * @return the accessor
     */
    public String getAccessor() {
        return accessor;
    }

    /**
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @return the var
     */
    public String getVar() {
        return var;
    }

    /**
     * @param accessor
     *            the accessor to set
     */
    public void setAccessor(String accessor) {
        this.accessor = accessor;
    }

    /**
     * @param comment
     *            the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @param value
     *            the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @param var
     *            the var to set
     */
    public void setVar(String var) {
        this.var = var;
    }

    /**
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        if (comment != null && !comment.isEmpty()) {
            comment = "/** " + comment + " */\n";
        } else {
            comment = "";
        }
        boolean isValueable = value != null && !value.isEmpty();
        return comment + StringUtils.appendBlank(accessor, type, var, (isValueable ? "=" : null), (isValueable ? value : null)) + ";";
    }
}