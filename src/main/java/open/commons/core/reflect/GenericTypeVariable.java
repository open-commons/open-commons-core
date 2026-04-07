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
* Date  : 2014. 3. 28. 오전 11:51:28
*
* Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
* 
*/

package open.commons.core.reflect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.jspecify.annotations.Nullable;

import open.commons.core.concurrent.Mutex;
import open.commons.core.utils.AssertUtils2;
import open.commons.core.utils.ClassUtils;
import open.commons.core.utils.ConvertUtils;
import open.commons.core.utils.ObjectUtils;

/**
 * 클래스 객체 생성시 적용되는 Generic을 설정하는 클래스.
 * 
 * @since 2014. 3. 28.
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 * 
 */
public class GenericTypeVariable implements Cloneable {

    protected final String SCN = ClassUtils.getSimpleName(getClass());

    /** 클래스 생성시 실제 적용된 Generic Parameter Class */
    private Class<?> typeClass;

    /** 클래스 생성시 적용된 Generic Literal */
    private @Nullable String typeVarName;

    /**
     * 하위 Generic 지원하기 위한, 클래스 선언에 적용된 Generic 타입에 적용된 Generic 설정.<br>
     */
    private ConcurrentLinkedQueue<GenericTypeVariable> genericTypeVars = new ConcurrentLinkedQueue<GenericTypeVariable>();

    private Mutex mutex = new Mutex("genericTypeVars");

    /**
     * @param paramClass
     *            클래스 생성시 실제 적용된 Generic Parameter Class
     * @throws NullPointerException
     *             파라미터({@code paramClass})가 {@code null}인 경우 발생.
     */
    public GenericTypeVariable(Class<?> paramClass) {
        this(paramClass, null);
    }

    /**
     * @param paramClass
     *            클래스 생성시 실제 적용된 Generic Parameter Class
     * @param typeVarName
     *            클래스 생성시 적용된 Generic Literal
     * 
     * @throws NullPointerException
     *             파라미터({@code paramClass})가 {@code null}인 경우 발생.
     */
    public GenericTypeVariable(Class<?> paramClass, @Nullable String typeVarName) {
        AssertUtils2.notNull(paramClass, null, "paramClass MUST NOT be null. paramClass: null");

        this.typeClass = paramClass;
        this.typeVarName = typeVarName;
    }

    /**
     * @throws NullPointerException
     *             파라미터({@code typeVar})가 {@code null}인 경우 발생.
     */
    public void addGenericParamType(GenericTypeVariable typeVar) {
        AssertUtils2.notNull(typeVar, "paramType MUST NOT be null. typeVar: null");

        synchronized (mutex) {
            genericTypeVars.add(typeVar);
        }
    }

    /**
     * 
     * @param typeVars
     * 
     * @throws NullPointerException
     *             파라미터({@code typeVars})가 {@code null}인 경우 발생.
     * 
     * @since 2014. 6. 18.
     */
    // 아래 내용에 적용됨.
    // - ObjectUtils.requireNonNullsWithMessage("paramType MUST NOT be null", (Object[]) typeVars);
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public void addGenericParamType(GenericTypeVariable... typeVars) {
        ObjectUtils.requireNonNullsWithMessage("paramType MUST NOT be null", (Object[]) typeVars);

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

    // 아래 내용에 적용됨.
    // - Collections.unmodifiableList(gpts);
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
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

    public @Nullable String getTypeVarName() {
        return typeVarName;
    }

    public boolean hasChildren() {
        return genericTypeVars.size() > 0;
    }

    /**
     * @throws NullPointerException
     *             파라미터({@code paramClass})가 {@code null}인 경우 발생.
     */
    public void reset(Class<?> paramClass) {
        AssertUtils2.notNull(paramClass, "paramClass MUST NOT be null. paramClass: null");

        synchronized (mutex) {
            this.typeClass = paramClass;
            this.genericTypeVars.clear();
        }
    }

    public void setTypeVarName(@Nullable String genericName) {
        this.typeVarName = genericName;
    }

    // 아래 내용에 적용됨.
    // - StringBuilder.toString()()
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(SCN);
        sb.append(" [genericNameClass=");
        sb.append(typeVarName);
        sb.append(", paramClass=");
        sb.append(typeClass);
        sb.append(", genericParamTypes=");
        sb.append(genericTypeVars);
        sb.append(']');

        return sb.toString();
    }

    /**
     * 주어진 클래스에 맞는 {@link GenericTypeVariable} 객체를 반환합니다.
     * 
     * @param paramClass
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code paramClass})가 {@code null}인 경우 발생.
     * 
     * @see ConvertUtils#getWrapperClass(Class)
     */
    public static GenericTypeVariable getParamType(Class<?> paramClass) {
        Objects.requireNonNull(paramClass);

        return new GenericTypeVariable(ConvertUtils.getWrapperClass(paramClass));
    }

    /**
     * 주어진 클래스에 맞는 {@link GenericTypeVariable} 객체를 반환합니다.
     * 
     * @param paramClass
     * @param typeVarName
     *            TypeVariable Literal
     * 
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code paramClass})가 {@code null}인 경우 발생.
     * 
     * @see ConvertUtils#getWrapperClass(Class)
     */
    public static GenericTypeVariable getParamType(Class<?> paramClass, String typeVarName) {
        Objects.requireNonNull(paramClass);

        GenericTypeVariable typeVar = new GenericTypeVariable(ConvertUtils.getWrapperClass(paramClass));
        typeVar.setTypeVarName(typeVarName);
        return typeVar;
    }
}
