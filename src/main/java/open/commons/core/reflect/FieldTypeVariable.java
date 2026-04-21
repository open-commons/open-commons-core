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
* Date  : 2014. 4. 1. 오전 11:10:17
*
* Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
* 
*/

package open.commons.core.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.TypeVariable;
import java.util.Objects;

import org.jspecify.annotations.Nullable;

/**
 * 
 * @since 2014. 4. 1.
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
public class FieldTypeVariable {

    private @Nullable Field field;

    private @Nullable String fieldName;

    private @Nullable String typeVarName;

    private @Nullable TypeVariable<? extends GenericDeclaration> typeVariable;

    /**
     *
     * @since 2026. 3. 19.
     * @version 3.0.0
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
        FieldTypeVariable other = (FieldTypeVariable) obj;
        return Objects.equals(field, other.field);
    }

    public @Nullable Field getField() {
        return field;
    }

    public @Nullable String getFieldName() {
        return fieldName;
    }

    public @Nullable TypeVariable<? extends GenericDeclaration> getTypeVariable() {
        return typeVariable;
    }

    public @Nullable String getTypeVarName() {
        return typeVarName;
    }

    /**
     *
     * @since 2026. 3. 19.
     * @version 3.0.0
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(field);
    }

    /**
     */
    public void setField(Field field) {
        Objects.requireNonNull(field);

        this.field = field;
    }

    /**
     */
    public void setFieldName(String fieldName) {
        Objects.requireNonNull(fieldName);

        this.fieldName = fieldName;
    }

    /**
     */
    public void setTypeVariable(TypeVariable<? extends GenericDeclaration> typeVariable) {
        Objects.requireNonNull(typeVariable);

        this.typeVariable = typeVariable;
    }

    /**
     */
    public void setTypeVarName(String typeVarName) {
        Objects.requireNonNull(typeVarName);

        this.typeVarName = typeVarName;
    }

    @Override
    public String toString() {
        return "FieldTypeVariable [fieldName=" + fieldName + ", typeVarName=" + typeVarName + ", field=" + field
                + ", typeVariable=" + typeVariable + "]";
    }

}
