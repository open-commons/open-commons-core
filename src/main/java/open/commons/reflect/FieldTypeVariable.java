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

package open.commons.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.TypeVariable;

/**
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */
public class FieldTypeVariable {

    private String fieldName;

    private String typeVarName;

    private Field field;

    private TypeVariable<? extends GenericDeclaration> typeVariable;

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        FieldTypeVariable other = (FieldTypeVariable) obj;
        if (field == null) {
            if (other.field != null)
                return false;
        } else if (!field.equals(other.field))
            return false;
        return true;
    }

    public Field getField() {
        return field;
    }

    public String getFieldName() {
        return fieldName;
    }

    public TypeVariable<? extends GenericDeclaration> getTypeVariable() {
        return typeVariable;
    }

    public String getTypeVarName() {
        return typeVarName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((field == null) ? 0 : field.hashCode());
        return result;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public void setTypeVariable(TypeVariable<? extends GenericDeclaration> typeVariable) {
        this.typeVariable = typeVariable;
    }

    public void setTypeVarName(String typeVarName) {
        this.typeVarName = typeVarName;
    }

    @Override
    public String toString() {
        return "FieldTypeVariable [fieldName=" + fieldName + ", typeVarName=" + typeVarName + ", field=" + field + ", typeVariable=" + typeVariable + "]";
    }

}
