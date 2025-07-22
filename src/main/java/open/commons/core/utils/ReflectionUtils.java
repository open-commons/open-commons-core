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

/**
* 
*/
package open.commons.core.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import open.commons.core.reflect.FieldTypeVariable;
import open.commons.core.reflect.GenericTypeVariable;
import open.commons.core.util.IFilter;

import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

/**
 * 
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 * @since 2011. 1. 2.
 * 
 */
@SuppressWarnings("restriction")
public class ReflectionUtils {

    /**
     * 
     * @param clazz
     * @param classes
     * @return
     */
    public static boolean containsClass(Class<?> clazz, Class<?>... classes) {

        boolean contains = false;

        for (Class<?> clz : classes) {
            if (clazz.equals(clz)) {
                contains = true;

                break;
            }
        }

        return contains;
    }

    /**
     * 주어진 {@link Class} 타입에 해당 변수 이름의 존재여부를 반환합니다.
     * 
     * @param clazz
     * @param fieldname
     * @return <BR>
     * @since 2012. 02. 14.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see Class#getDeclaredFields()
     */
    public static boolean containsDeclaredField(Class<?> clazz, String fieldname) {
        for (Field field : clazz.getDeclaredFields()) {
            if (fieldname.equals(field.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 주어진 {@link Class} 타입에 해당 변수 이름의 존재여부를 반환합니다.
     * 
     * @param clazz
     * @param fieldname
     * @return <BR>
     * @since 2012. 02. 14.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see Class#getFields()
     */
    public static boolean containsField(Class<?> clazz, String fieldname) {
        for (Field field : clazz.getFields()) {
            if (fieldname.equals(field.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 대상 Class의 {@link TypeVariable} 정보와 실제로 사용될 {@link Class} 정보를 조합해서 반환합니다.
     * 
     * @param targetClass
     *            {@link TypeVariable}이 사용된 클래스.
     * @param lastCopy
     *            <b><code>typeVarClasses</code></b>의 개수가 <b><code>targetClass</code></b>의 실제 {@link TypeVariable} 개수보다
     *            적은 경우 마지막 값으로 채울지 여부.
     * @param typeVarClasses
     *            {@link TypeVariable}로 사용될 클래스.
     * @return
     * 
     * @since 2014. 6. 18.
     */
    public static List<GenericTypeVariable> createGenericTypeVariables(Class<?> targetClass, boolean lastCopy, Class<?>... typeVarClasses) {

        List<GenericTypeVariable> gTypeVars = new ArrayList<GenericTypeVariable>();
        if (typeVarClasses.length < 1) {
            return gTypeVars;
        }

        TypeVariable<?>[] typeVars = targetClass.getTypeParameters();

        AssertUtils2.assertFalse("The number of TypeVariable MUST be equal to the number of classes used ", !lastCopy && typeVars.length > typeVarClasses.length);

        typeVarClasses = ArrayUtils.adjustByLength(typeVars.length, typeVarClasses);

        for (int i = 0; i < typeVarClasses.length; i++) {
            gTypeVars.add(new GenericTypeVariable(typeVarClasses[i], typeVars[i].getName()));
        }

        return gTypeVars;
    }

    /**
     * 대상 Class의 {@link TypeVariable} 정보와 실제로 사용될 {@link Class} 정보를 조합해서 반환합니다.
     * 
     * @param targetClass
     *            {@link TypeVariable}이 사용된 클래스.
     * @param typeVarClasses
     *            {@link TypeVariable}로 사용될 클래스.
     * @return
     * 
     * @since 2014. 6. 18.
     */
    public static GenericTypeVariable createGenericTypeVariables(Class<?> targetClass, Class<?> typeVarClass) {

        AssertUtils2.assertNotNulls(targetClass, typeVarClass);

        TypeVariable<?>[] typeVars = targetClass.getTypeParameters();

        if (typeVars.length < 1) {
            return null;
        }

        return new GenericTypeVariable(typeVarClass, typeVars[0].getName());
    }

    /**
     * 
     * @param m1
     * @param m2
     * @return
     * 
     * @since 2014. 6. 19.
     */
    public static boolean equalsName(Member m1, Member m2) {
        AssertUtils2.assertNotNulls("Neither m1 and m2 Class MUST be null. m1: " + m1 + ", m2: " + m2, m1, m2);

        return m1.getName().equals(m2.getName());
    }

    public static boolean equalsOneOfClasses(Class<?> target, Class<?>... candidates) {
        AssertUtils2.assertNotNull("target MUST NOT be null. target: " + target);

        for (Class<?> candidate : candidates) {
            if (target.equals(candidate)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 
     * @param clazz
     * @param methodName
     * @param paramIndex
     *            찾고자 하는 파라미터 순서
     * @param actualTypeIndex
     *            Actual Type 순서
     * @param parameterTypes
     * @return
     */
    public static Class<?> getActualTypeArgument(Class<?> clazz, String methodName, int paramIndex, int actualTypeIndex, Class<?>... parameterTypes) {
        Class<?> rtnClass = null;
        try {
            Method method = clazz.getDeclaredMethod(methodName, parameterTypes);

            rtnClass = getActualTypeArgument(method, paramIndex, actualTypeIndex);

        } catch (NoSuchMethodException e) {
        } catch (SecurityException e) {
        }

        return rtnClass;
    }

    /**
     * Generic이 사용된 메소드에서 실제로 사용된 타입 정보를 반환합니다.
     * 
     * @param method
     * @param paramIndex
     *            찾고자 하는 파라미터 순서
     * @param actualTypeIndex
     *            Actual Type 순서
     * @return
     */
    public static Class<?> getActualTypeArgument(Method method, int paramIndex, int actualTypeIndex) {
        Class<?> rtnClass = null;

        Type paramType = method.getGenericParameterTypes()[paramIndex];

        if (paramType instanceof ParameterizedTypeImpl) {
            ParameterizedTypeImpl paramTypeImpl = (ParameterizedTypeImpl) paramType;

            Type actualType = paramTypeImpl.getActualTypeArguments()[actualTypeIndex];

            if (actualType instanceof Class) {
                rtnClass = (Class<?>) actualType;
            } else {
                rtnClass = actualType.getClass();
            }
        }

        return rtnClass;
    }

    /**
     * 주어진 어노테이션이 설정된 {@link Field}와 어노테이션 객체를 반환합니다.
     * 
     * @param object
     * @param annotationClass
     * @return
     * 
     * @see #getAnnotatedFields(Class, Class, IFilter)
     * @see Class#getDeclaredFields()
     */
    public static final <T extends Annotation> Map<Field, T> getAllAnnodatedFields(Object object, Class<T> annotationClass, IFilter<T> filter) {
        AssertUtils2.assertNotNulls("Neither object and annotationClass MUST be null. object=null." + ", annotationClass: " + annotationClass, object, annotationClass);

        Map<Field, T> annotatedFields = new HashMap<Field, T>();

        Class<?> implClass = null;

        if (object instanceof Class) {
            implClass = (Class<?>) object;
        } else {
            implClass = object.getClass();
        }

        while (!implClass.equals(Object.class)) {
            annotatedFields.putAll(getAnnotatedFields(implClass, annotationClass, filter));

            implClass = implClass.getSuperclass();
        }

        return annotatedFields;
    }

    /**
     * 주어진 어노테이션이 설정된 {@link Field}와 어노테이션 객체를 반환합니다.
     * 
     * @param <T>
     * @param object
     * @param annotationClass
     * @return
     *
     * @see #getAnnotatedFields(Object, Class, IFilter)
     * @see #getAnnotatedFields(Class, Class, IFilter)
     * @see Class#getDeclaredFields()
     */
    public static final <T extends Annotation> Map<Field, T> getAllAnnotatedFields(Object object, Class<T> annotationClass) {
        return getAllAnnodatedFields(object, annotationClass, new IFilter.TrueFilter<T>());
    }

    /**
     * 주어진 어노테이션이 설정된 {@link Constructor}와 어노테이션 객체를 반환합니다.
     * 
     * @param object
     * @param annotationClass
     * @return
     *
     */
    public static final <T extends Annotation> Map<Constructor<?>, T> getAnnotatedConstructors(Object object, Class<T> annotationClass) {
        AssertUtils2.assertNotNulls("Neither object and annotationClass MUST be null. object=null." + ", annotationClass: " + annotationClass, object, annotationClass);

        Map<Constructor<?>, T> annoConstructors = new HashMap<Constructor<?>, T>();

        Class<?> implClass = null;

        if (object instanceof Class) {
            implClass = (Class<?>) object;
        } else {
            implClass = object.getClass();
        }

        Constructor<?>[] constructors = implClass.getDeclaredConstructors();

        boolean oldAccessible = false;
        for (Constructor<?> c : constructors) {
            oldAccessible = c.isAccessible();

            c.setAccessible(true);
            if (c.isAnnotationPresent(annotationClass)) {
                annoConstructors.put(c, c.getAnnotation(annotationClass));
            }
            c.setAccessible(oldAccessible);
        }

        return annoConstructors;
    }

    /**
     * 주어진 어노테이션이 설정된 {@link Field}와 어노테이션 객체를 반환합니다.
     * 
     * @param object
     * @param annotationClass
     * @return
     * 
     * @see #getAnnotatedFields(Object, Class, IFilter)
     * @see Class#getDeclaredFields()
     */
    public static final <T extends Annotation> Map<Field, T> getAnnotatedFields(Object object, Class<T> annotationClass) {
        return getAnnotatedFields(object, annotationClass, new IFilter.TrueFilter<T>());
    }

    /**
     * 주어진 어노테이션이 설정된 {@link Field}와 어노테이션 객체를 반환합니다.
     * 
     * @param object
     * @param annotationClass
     * @return
     * 
     * @see Class#getDeclaredFields()
     */
    public static final <T extends Annotation> Map<Field, T> getAnnotatedFields(Object object, Class<T> annotationClass, IFilter<T> filter) {
        AssertUtils2.assertNotNulls("Neither object and annotationClass MUST be null. object=null." + ", annotationClass: " + annotationClass, object, annotationClass);

        Map<Field, T> annotatedFields = new HashMap<Field, T>();

        Class<?> implClass = null;

        if (object instanceof Class) {
            implClass = (Class<?>) object;
        } else {
            implClass = object.getClass();
        }

        Field[] fields = implClass.getDeclaredFields();

        boolean oldAccessible = false;
        T anno = null;
        for (Field f : fields) {
            oldAccessible = f.isAccessible();

            try {
                f.setAccessible(true);

                anno = f.getAnnotation(annotationClass);

                if (anno != null && filter.filter(anno, f)) {
                    annotatedFields.put(f, anno);
                }

            } finally {
                f.setAccessible(oldAccessible);
            }
        }

        return annotatedFields;
    }

    /**
     * 주어진 어노테이션이 있는 상위 클래스 및 인터페이스를 포함한 'public' 메소드만 제공합니다. <br>
     * 
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 6. 17.		박준홍			최초 작성
     * </pre>
     *
     * @param annoType
     *            어노테이션 타입.
     * @param dataType
     *            데이터 타입.
     * @return
     *
     * @since 2019. 6. 17.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @see Class#getMethods()
     */
    public static <A extends Annotation> Collection<Method> getAnnotatedMethods(Class<A> annoType, Class<?> dataType) {

        AssertUtils2.assertNotNulls(annoType, dataType);

        return Arrays.stream(dataType.getMethods()) //
                .filter(m -> m.getAnnotation(annoType) != null) //
                .collect(Collectors.toList());

    }

    /**
     * {@link AccessibleObject}에서 {@link Annotation}를 얻어온다.
     * 
     * @param accessObj
     * @param annotationClass
     * @return <b><code>nullable</code></b>.
     */
    public static <T extends Annotation> T getAnnotation(AccessibleObject accessObj, Class<T> annotationClass) {
        AssertUtils2.assertNotNulls("Neither accessObj and annotationClass MUST be null. accessObj: " + accessObj + ", annotationClass: " + annotationClass, accessObj,
                annotationClass);

        return accessObj.getAnnotation(annotationClass);
    }

    /**
     * 
     * @param clazz
     *            a class of targeted
     * @param annotationClass
     *            a class of annotation
     * 
     * @return
     */
    public static <T extends Annotation> T getAnnotation(Class<?> clazz, Class<T> annotationClass) {
        AssertUtils2.assertNotNulls("Neither clazz and annotationClass MUST be null. clazz: " + clazz + ", annotationClass: " + annotationClass, clazz, annotationClass);

        return clazz.getAnnotation(annotationClass);
    }

    /**
     * 객체에서 주어진 타입에 맞는 {@link Field} 목록을 반환합니다.
     * 
     * @param instance
     *            객체
     * @param fieldType
     *            찾고자하는 필드 타입
     * @return
     * 
     * @see Class#isAssignableFrom(Class)
     * @see Class#getDeclaredFields()
     */
    public static List<Field> getDeclaredFields(Object instance, Class<?> fieldType) {
        ArrayList<Field> fields = new ArrayList<Field>();

        Class<?> clazz = null;

        if (instance instanceof Class) {
            clazz = (Class<?>) instance;
        } else {
            clazz = instance.getClass();
        }

        boolean accessible = false;
        for (Field field : clazz.getDeclaredFields()) {
            accessible = field.isAccessible();

            if (fieldType.isAssignableFrom(field.getType())) {
                fields.add(field);
            }

            field.setAccessible(accessible);
        }

        return fields;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static <T extends Annotation> Collection<FieldTypeVariable> getTypeVariableName(Class<?> class_, Class<T> annotationClass) {

        Collection<FieldTypeVariable> fieldTypeVars = new HashSet<FieldTypeVariable>();

        FieldTypeVariable fieldTypeVar = null;
        Type type = null;
        for (Field field : getAnnotatedFields(class_, annotationClass).keySet()) {
            type = field.getGenericType();

            if (!(type instanceof TypeVariable)) {
                continue;
            }

            fieldTypeVar = new FieldTypeVariable();
            fieldTypeVar.setFieldName(field.getName());
            fieldTypeVar.setField(field);
            fieldTypeVar.setTypeVariable((TypeVariable) type);
            fieldTypeVar.setTypeVarName(((TypeVariable) type).getName());

            fieldTypeVars.add(fieldTypeVar);
        }

        return fieldTypeVars;
    }

    /**
     * 클래스에 선언된 Generic Type Variable Literal 을 반환합니다.
     * 
     * @param classClass
     * @return
     * 
     * @since 2014. 6. 18.
     */
    public static List<String> getTypeVariableNames(Class<?> classClass) {
        ArrayList<String> typeVarNames = new ArrayList<String>();

        for (TypeVariable<?> typeVar : classClass.getTypeParameters()) {
            typeVarNames.add(typeVar.getName());
        }

        return typeVarNames;
    }

    /**
     * 주어진 객체에서 {@link Field}의 값을 추출해서 반환합니다.
     * 
     * @param field
     * @param instance
     * @return {@link Field}의 값. 값이 없거나 예외가 발생한 경우 빈 문자열.
     */
    public static String getValue(Field field, Object instance) {
        String rtnValue = "";
        try {
            boolean accessible = field.isAccessible();
            field.setAccessible(true);

            Object value = field.get(instance);

            field.setAccessible(accessible);
            if (value != null) {
                rtnValue = value.toString();
            }

        } catch (IllegalArgumentException e) {
        } catch (IllegalAccessException e) {
        }
        return rtnValue;
    }

    /**
     * 주어진 객체에서 {@link Field}의 값을 추출해서 반환합니다.
     * 
     * @param field
     * @param instance
     * @return {@link Field}의 값. 값이 없거나 예외가 발생한 경우 빈 문자열.
     */
    public static Object getValue(Field field, Object instance, Object defaultValue) {
        Object rtnValue = defaultValue;

        try {

            boolean accessible = field.isAccessible();
            field.setAccessible(true);

            rtnValue = field.get(instance);

            field.setAccessible(accessible);

        } catch (IllegalArgumentException e) {
        } catch (IllegalAccessException e) {
        }
        return rtnValue;
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> newList(Class<?> parameterizedType)
            throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        return ArrayList.class.getConstructor().newInstance();
    }

    /**
     * <b>NOTE:</b> The parameter <code><b>field</b></code> MUST be allowed to access.
     * 
     * @param object
     * @param field
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws NullPointerException
     * @throws ExceptionInInitializerError
     */
    public static void resetField(Object object, Field field) throws IllegalArgumentException, IllegalAccessException, NullPointerException, ExceptionInInitializerError {

        AssertUtils2.assertNotNulls("Neither object and field MUST be null. object: " + object + ", field: " + field, object, field);

        Type type = field.getGenericType();

        if (!(type instanceof Class<?>)) {
            field.set(object, null);

            return;
        }

        switch (ConvertUtils.getTypeConst((Class<?>) type)) {
            case ConvertUtils.TYPE_CONST_BOOLEAN:
                field.set(object, false);
                break;
            case ConvertUtils.TYPE_CONST_BYTE:
                field.set(object, (byte) 0);
                break;
            case ConvertUtils.TYPE_CONST_CHAR:
                field.set(object, '\0');
                break;
            case ConvertUtils.TYPE_CONST_INT:
                field.set(object, (int) 0);
                break;
            case ConvertUtils.TYPE_CONST_LONG:
                field.set(object, (long) 0);
                break;
            case ConvertUtils.TYPE_CONST_FLOAT:
                field.set(object, (float) 0.0);
                break;
            case ConvertUtils.TYPE_CONST_DOUBLE:
                field.set(object, (double) 0.0);
                break;
            default:
                field.set(object, null);
                break;
        }
    }

    /**
     * 
     * @param object
     * @param field
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws SecurityException
     * @throws NullPointerException
     * @throws ExceptionInInitializerError
     * 
     * @since 2014. 4. 2.
     */
    public static void resetFieldForced(Object object, Field field)
            throws IllegalArgumentException, IllegalAccessException, SecurityException, NullPointerException, ExceptionInInitializerError {

        AssertUtils2.assertNotNulls("Neither object and field MUST be null. object: " + object + ", field: " + field, object, field);

        boolean accessible = field.isAccessible();

        try {
            field.setAccessible(true);

            resetField(object, field);

        } finally {
            field.setAccessible(accessible);
        }
    }

    /**
     * @param target
     * @param candidates
     * @return
     */
    public static boolean subclassOneOf(Class<?> target, Class<?>... candidates) {
        AssertUtils2.assertNotNulls("Neither target and candidates MUST be null. target=null, candidates: " + candidates, target, candidates);

        for (Class<?> candidate : candidates) {
            if (candidate.isAssignableFrom(target)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Return whether a parameter is subclass of <b><code>candidates</code></b> or not.
     * 
     * @param target
     * @param candidates
     * @return
     */
    public static boolean subclassOneOf(Object object, Class<?>... candidates) {
        AssertUtils2.assertNotNulls("Neither object and candidates MUST be null. object: " + object + ", candidates: " + candidates, object, candidates);

        Class<?> target = object.getClass();

        for (Class<?> candidate : candidates) {
            if (candidate.isAssignableFrom(target)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Return all Class<?> of super-classes of a <b><code>parameter</code></b>.
     * 
     * @param object
     * @param candidates
     * @return
     * 
     * @since 2014. 5. 2.
     */
    public static List<Class<?>> superclasses(Object object, Class<?>... candidates) {
        AssertUtils2.assertNotNulls("Neither object and candidates MUST be null. object: " + object + ", candidates: " + candidates, object, candidates);

        List<Class<?>> superclasses = new ArrayList<Class<?>>();

        Class<?> target = object.getClass();

        for (Class<?> candidate : candidates) {
            if (candidate.isAssignableFrom(target)) {
                superclasses.add(candidate);
            }
        }

        return superclasses;
    }
}
