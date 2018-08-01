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
* Date  : 2014. 3. 28. 오전 11:51:28
*
* Author: Park_Jun_Hong_(fafanmama_at_naver_com)
* 
*/

package open.commons.reflect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import open.commons.concurrent.Mutex;
import open.commons.utils.AssertUtils;
import open.commons.utils.ConvertUtils;

/**
 * 클래스 객체 생성시 적용되는 Generic을 설정하는 클래스.
 * 
 * 
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */
public class GenericTypeVariable implements Cloneable {

    protected final String SCN = getClass().getSimpleName();

    /** 클래스 생성시 적용된 Generic Literal */
    private String typeVarName;

    /** 클래스 생성시 실제 적용된 Generic Parameter Class */
    private Class<?> typeClass;

    /**
     * 하위 Generic 지원하기 위한, 클래스 선언에 적용된 Generic 타입에 적용된 Generic 설정.<br>
     */
    private ConcurrentLinkedQueue<GenericTypeVariable> genericTypeVars = new ConcurrentLinkedQueue<GenericTypeVariable>();

    private Mutex mutex = new Mutex("genericTypeVars");

    public GenericTypeVariable(Class<?> paramClass) {
        this(paramClass, null);
    }

    public GenericTypeVariable(Class<?> paramClass, String typeVarName) {
        AssertUtils.assertNull("paramClass MUST NOT be null. paramClass: null", paramClass);

        this.typeClass = paramClass;
        this.typeVarName = typeVarName;
    }

    public void addGenericParamType(GenericTypeVariable typeVar) {
        AssertUtils.assertNull("paramType MUST NOT be null. typeVar: null", typeVar);

        synchronized (mutex) {
            genericTypeVars.add(typeVar);
        }
    }

    /**
     * 
     * @param typeVars
     * 
     * @since 2014. 6. 18.
     */
    public void addGenericParamType(GenericTypeVariable... typeVars) {
        AssertUtils.assertNulls("paramType MUST NOT be null. paramType: null", (Object[]) typeVars);

        synchronized (mutex) {
            genericTypeVars.addAll(Arrays.asList(typeVars));
        }
    }

    /**
     * EXPERIMENTAL
     * 
     * @see java.lang.Object#clone()
     */
    @Override
    public Object clone() throws CloneNotSupportedException {

        GenericTypeVariable clone = new GenericTypeVariable(this.typeClass);
        clone.typeVarName = this.typeVarName;

        for (GenericTypeVariable c : this.genericTypeVars) {
            this.genericTypeVars.add((GenericTypeVariable) c.clone());
        }

        return clone;
    }

    public List<GenericTypeVariable> getGenericTypeVars() {

        synchronized (mutex) {
            List<GenericTypeVariable> gpts = new ArrayList<GenericTypeVariable>();

            for (GenericTypeVariable gpt : genericTypeVars) {
                gpts.add(gpt);
            }

            return Collections.unmodifiableList(gpts);
        }
    }

    public Class<?> getTypeClass() {
        return this.typeClass;
    }

    public String getTypeVarName() {
        return typeVarName;
    }

    public boolean hasChildren() {
        return genericTypeVars.size() > 0;
    }

    public void reset(Class<?> paramClass) {
        AssertUtils.assertNull("paramClass MUST NOT be null. paramClass: null", paramClass);

        synchronized (mutex) {
            this.typeClass = paramClass;
            this.genericTypeVars.clear();
        }
    }

    public void setTypeVarName(String genericName) {
        this.typeVarName = genericName;
    }

    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();
        str.append(SCN);
        str.append(" [genericNameClass=");
        str.append(typeVarName);
        str.append(", paramClass=");
        str.append(typeClass);
        str.append(", genericParamTypes=");
        str.append(genericTypeVars);
        str.append(']');

        return str.toString();
    }

    /**
     * 주어진 클래스에 맞는 {@link GenericTypeVariable} 객체를 반환한다.
     * 
     * @param class_
     * @return
     * 
     * @see ConvertUtils#getWrapperClass(Class)
     */
    public static GenericTypeVariable getParamType(Class<?> class_) {
        return new GenericTypeVariable(ConvertUtils.getWrapperClass(class_));
    }

    /**
     * 주어진 클래스에 맞는 {@link GenericTypeVariable} 객체를 반환한다.
     * 
     * @param typeVarName
     *            TypeVariable Literal
     * @param class_
     * @return
     * 
     * @see ConvertUtils#getWrapperClass(Class)
     */
    public static GenericTypeVariable getParamType(String typeVarName, Class<?> class_) {
        GenericTypeVariable typeVar = new GenericTypeVariable(ConvertUtils.getWrapperClass(class_));
        typeVar.setTypeVarName(typeVarName);
        return typeVar;
    }
}
