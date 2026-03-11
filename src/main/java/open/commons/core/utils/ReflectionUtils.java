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
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import open.commons.core.reflect.FieldTypeVariable;
import open.commons.core.reflect.GenericTypeVariable;
import open.commons.core.util.IFilter;

/**
 * 
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 * @since 2011. 1. 2.
 * 
 */
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

        AssertUtils2.isFalse("The number of TypeVariable MUST be equal to the number of classes used ", !lastCopy && typeVars.length > typeVarClasses.length);

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

        AssertUtils2.notNulls(targetClass, typeVarClass);

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
        AssertUtils2.notNulls("Neither m1 and m2 Class MUST be null. m1: " + m1 + ", m2: " + m2, m1, m2);

        return m1.getName().equals(m2.getName());
    }

    public static boolean equalsOneOfClasses(Class<?> target, Class<?>... candidates) {
        AssertUtils2.notNull("target MUST NOT be null. target: " + target);

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
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 6. 17.        parkjunhong77@gmail.com         최초 작성
     * 2026. 02. 26.        parkjunhong77@gmail.com         (3.0.0) JDK 25 마이그레이션: Pattern Matching 적용 및 표준 API 전환
     * </pre>
     *
     * @param method
     *            검사할 메소드
     * @param paramIndex
     *            파라미터 순서 (0-based)
     * @param actualTypeIndex
     *            Actual Type 순서 (0-based)
     * @return 실제 타입 {@link Class}, 찾을 수 없는 경우 null
     * 
     * @since 2019. 6. 17.
     * @version 3.0.0
     */
    public static Class<?> getActualTypeArgument(Method method, int paramIndex, int actualTypeIndex) {
        Type[] genericParameterTypes = method.getGenericParameterTypes();

        // 배열 인덱스 범위 체크 (안전한 코드를 위해 권장)
        if (paramIndex < 0 || paramIndex >= genericParameterTypes.length) {
            return null;
        }

        Type paramType = genericParameterTypes[paramIndex];

        // 1. 내부 구현체 대신 표준 인터페이스인 ParameterizedType 사용
        // 2. Pattern Matching for instanceof (Java 16+) 적용
        if (paramType instanceof ParameterizedType pType) {
            Type[] actualTypes = pType.getActualTypeArguments();

            if (actualTypeIndex < 0 || actualTypeIndex >= actualTypes.length) {
                return null;
            }

            Type actualType = actualTypes[actualTypeIndex];

            // 3. 타입 결정 로직 개선
            if (actualType instanceof Class<?> clazz) {
                return clazz;
            } else if (actualType instanceof ParameterizedType innerPType) {
                // 중첩 제네릭(예: List<Map<String, Integer>>) 대응
                return (Class<?>) innerPType.getRawType();
            }
        }

        return null;
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
        AssertUtils2.notNulls("Neither object and annotationClass MUST be null. object=null." + ", annotationClass: " + annotationClass, object, annotationClass);

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
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 6. 17.        parkjunhong77@gmail.com         최초 작성
     * 2026. 02. 26.        parkjunhong77@gmail.com         (3.0.0) JDK 25 마이그레이션: Pattern Matching, Stream API 적용
     * </pre>
     * 
     * @param object
     * @param annotationClass
     * @return
     * 
     * @since 2019. 6. 17.
     * @version 3.0.0
     *
     */
    public static final <T extends Annotation> Map<Constructor<?>, T> getAnnotatedConstructors(Object object, Class<T> annotationClass) {
        // 1. 유효성 검사
        AssertUtils2.notNulls("object와 annotationClass는 null일 수 없습니다.", object, annotationClass);

        // 2. Pattern Matching for instanceof
        Class<?> implClass = (object instanceof Class<?> clazz) ? clazz : object.getClass();

        // 3. Stream API 및 Reflection API 최적화
        return Arrays.stream(implClass.getDeclaredConstructors())//
                .filter(c -> {
                    return c.isAnnotationPresent(annotationClass);
                }).collect(Collectors.toUnmodifiableMap(c -> c, c -> c.getAnnotation(annotationClass)));
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
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 12.        parkjunhong77@gmail.com         최초 작성
     * 2026. 02. 26.        parkjunhong77@gmail.com         JDK 25 마이그레이션: Pattern Matching, Stream API 적용
     * </pre>
     *
     * @param object
     *            대상 객체 또는 클래스
     * @param annotationClass
     *            찾고자 하는 어노테이션 클래스
     * @param filter
     *            사용자 정의 필터
     * @return 필드와 어노테이션 매핑 정보
     * @see Class#getDeclaredFields()
     */
    public static final <T extends Annotation> Map<Field, T> getAnnotatedFields(Object object, Class<T> annotationClass, IFilter<T> filter) {
        // 1. 파라미터 유효성 검증
        AssertUtils2.notNulls("object와 annotationClass는 null일 수 없습니다.", object, annotationClass);

        // 2. Pattern Matching for instanceof (Java 16+)
        Class<?> implClass = (object instanceof Class<?> clazz) ? clazz : object.getClass();

        // 3. Stream API를 이용한 필터링 및 수집
        return Arrays.stream(implClass.getDeclaredFields()) //
                .filter(f -> {
                    T anno = f.getAnnotation(annotationClass);
                    // 어노테이션 존재 여부 및 필터 조건 확인
                    return anno != null && (filter == null || filter.filter(anno, f));
                }).collect(Collectors.toUnmodifiableMap(f -> f, f -> f.getAnnotation(annotationClass)));
    }

    /**
     * 주어진 어노테이션이 있는 상위 클래스 및 인터페이스를 포함한 'public' 메소드만 제공합니다. <br>
     * 
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 6. 17.		parkjunohng77@gmail.com			최초 작성
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

        AssertUtils2.notNulls(annoType, dataType);

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
        AssertUtils2.notNulls("Neither accessObj and annotationClass MUST be null. accessObj: " + accessObj + ", annotationClass: " + annotationClass, accessObj, annotationClass);

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
        AssertUtils2.notNulls("Neither clazz and annotationClass MUST be null. clazz: " + clazz + ", annotationClass: " + annotationClass, clazz, annotationClass);

        return clazz.getAnnotation(annotationClass);
    }

    /**
     * 객체에서 주어진 타입에 맞는 {@link Field} 목록을 반환합니다. *
     * 
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 06. 17.        parkjunhong77@gmail.com         최초 작성
     * 2026. 02. 26.        parkjunhong77@gmail.com         (3.0.0) JDK 25 마이그레이션: Pattern Matching, Stream API 적용 및 접근 제어 로직 최적화
     * </pre>
     * 
     * * @param instance 객체 또는 클래스
     * 
     * @param fieldType
     *            찾고자 하는 필드 타입
     * @return 필드 목록 (불변 리스트)
     * 
     * @since 2019. 6. 17.
     * @version 3.0.0
     * 
     * @see Class#isAssignableFrom(Class)
     * @see Class#getDeclaredFields()
     */
    public static List<Field> getDeclaredFields(Object instance, Class<?> fieldType) {
        // 1. Pattern Matching for instanceof (Java 16+)
        // 타입 체크와 변수 선언을 동시에 처리하여 캐스팅 코드 제거
        Class<?> clazz = (instance instanceof Class<?> c) ? c : instance.getClass();

        // 2. Stream API 및 List.toList() (Java 16+) 적용
        return Arrays.stream(clazz.getDeclaredFields()).filter(field -> fieldType.isAssignableFrom(field.getType())).toList();
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
     * 주어진 객체에서 {@link Field}의 값을 추출해서 문자열로 반환합니다.
     * 
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2014. 6. 18.        parkjunhong77@gmail.com         최초 작성
     * 2026. 2. 26.        parkjunhong77@gmail.com         (3.0.0) JDK 25 마이그레이션: trySetAccessible 적용 및 다중 예외 처리
     * </pre>
     * 
     * @param field
     *            대상 필드
     * @param instance
     *            대상 객체
     * @return {@link Field}의 값. 값이 없거나 예외가 발생한 경우 빈 문자열.
     * 
     * @since 2014. 6. 18.
     * @version 3.0.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static String getValue(Field field, Object instance) {
        Object value = getValue(field, instance, null);
        return Objects.toString(value, null);
    }

    /**
     * 주어진 객체에서 {@link Field}의 값을 추출해서 반환합니다.
     * 
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2014. 6. 18.        parkjunhong77@gmail.com         최초 작성
     * 2026. 2. 26.        parkjunhong77@gmail.com         (3.0.0) JDK 25 마이그레이션: trySetAccessible 적용 및 다중 예외 처리
     * </pre>
     * 
     * @param field
     *            대상 필드
     * @param instance
     *            대상 객체
     * @param defaultValue
     *            기본값
     * @return {@link Field}의 값. 예외 발생 시 defaultValue 반환.
     * 
     * @since 2014. 6. 18.
     * @version 3.0.0
     * 
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static Object getValue(Field field, Object instance, Object defaultValue) {
        // 필드와 인스턴스가 모두 null인 경우 정적 필드가 아님에도 접근하려 하면 오류가 발생할 수 있음
        if (field == null) {
            return defaultValue;
        }

        // JDK 9+ : canAccess()를 사용하여 현재 접근 가능 여부를 먼저 확인
        if (!field.canAccess(instance)) {
            // JDK 9+ : setAccessible(true) 대신 trySetAccessible() 권장
            // 모듈 경계 등으로 인해 접근이 불가능한 경우 예외를 던지는 대신 false를 반환함
            if (!field.trySetAccessible()) {
                return defaultValue;
            }
        }

        try {
            Object value = field.get(instance);
            return (value != null) ? value : defaultValue;
        } catch (IllegalArgumentException | IllegalAccessException e) {
            // 다중 예외 처리(Multi-catch) 적용
            return defaultValue;
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> newList(Class<?> parameterizedType)
            throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        return ArrayList.class.getConstructor().newInstance();
    }

    /**
     * <b>NOTE:</b> The parameter <code><b>field</b></code> MUST be allowed to access.
     * 
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2026. 2. 27.     parkjunhong77@gmail.com         (3.0.0) JDK 25 마이그레이션: trySetAccessible 적용 및 canAccess 체크 추가
     * </pre>
     * 
     * @param object
     * @param field
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws NullPointerException
     * @throws ExceptionInInitializerError
     * 
     * @since 2014. 4. 2.
     */
    public static void resetField(Object object, Field field) throws IllegalArgumentException, IllegalAccessException, NullPointerException, ExceptionInInitializerError {

        AssertUtils2.notNulls("Neither object and field MUST be null. object: " + object + ", field: " + field, object, field);

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
     * 주어진 객체의 필드 값을 강제로 초기화합니다.
     * 
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2014. 4. 2.      parkjunhong77@gmail.com         최초 작성
     * 2026. 2. 27.     parkjunhong77@gmail.com         (3.0.0) JDK 25 마이그레이션: trySetAccessible 적용 및 canAccess 체크 추가
     * </pre>
     * 
     * @param object
     *            대상 객체
     * @param field
     *            초기화할 필드
     * 
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws SecurityException
     * @throws NullPointerException
     * @throws ExceptionInInitializerError
     * 
     * @since 2014. 4. 2.
     * @version 3.0.0
     */
    public static void resetFieldForced(Object object, Field field)
            throws IllegalArgumentException, IllegalAccessException, SecurityException, NullPointerException, ExceptionInInitializerError {

        AssertUtils2.notNulls("object와 field는 null일 수 없습니다. object: " + object + ", field: " + field, object, field);

        // 1. JDK 9+ : canAccess(object)를 통해 현재 접근 가능 여부 확인
        // 2. trySetAccessible()을 사용하여 안전하게 접근 권한 획득 시도
        // 3. 기존의 isAccessible() 사용 및 복구 로직은 권장되지 않으므로 최신 관례에 따라 처리합니다.
        if (!field.canAccess(object)) {
            if (!field.trySetAccessible()) {
                // 모듈 시스템에 의해 접근이 차단된 경우 예외 발생 또는 로깅
                throw new IllegalAccessException("필드에 접근할 수 없습니다 (모듈 캡슐화 등): " + field.getName());
            }
        }

        resetField(object, field);
    }

    /**
     * @param target
     * @param candidates
     * @return
     */
    public static boolean subclassOneOf(Class<?> target, Class<?>... candidates) {
        AssertUtils2.notNulls("Neither target and candidates MUST be null. target=null, candidates: " + candidates, target, candidates);

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
        AssertUtils2.notNulls("Neither object and candidates MUST be null. object: " + object + ", candidates: " + candidates, object, candidates);

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
        AssertUtils2.notNulls("Neither object and candidates MUST be null. object: " + object + ", candidates: " + candidates, object, candidates);

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
