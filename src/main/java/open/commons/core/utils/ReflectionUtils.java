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

package open.commons.core.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
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

import org.jspecify.annotations.Nullable;

import open.commons.core.reflect.FieldTypeVariable;
import open.commons.core.reflect.GenericTypeVariable;
import open.commons.core.util.IFilter;

/**
 * 리플렉션(Reflection)을 통한 객체 및 클래스 메타데이터 조작을 지원하는 유틸리티 클래스입니다.
 *
 * @since 2011. 1. 2.
 */
public class ReflectionUtils {

    /**
     * 대상 클래스({@code targetClass})가 주어진 클래스 목록({@code classes})에 포함되어 있는지 확인합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2026. 4. 2.      parkjunhong77@gmail.com         (개선) clazz -> targetClass 네이밍 룰 적용 및 Javadoc 보강
     * </pre>
     *
     * @param targetClass
     *            확인할 대상 클래스
     * @param classes
     *            비교할 클래스 목록
     *
     * @return 포함 여부
     */
    public static boolean containsClass(Class<?> targetClass, Class<@Nullable ?>... classes) {
        Objects.requireNonNull(targetClass);
        Objects.requireNonNull(classes);

        for (Class<?> clz : classes) {
            if (targetClass.equals(clz)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 주어진 {@link Class} 타입에 해당 변수 이름이 선언되어 있는지 여부를 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2012. 2. 14.     parkjunhong77@gmail.com         최초 작성
     * 2026. 4. 2.      parkjunhong77@gmail.com         (개선) clazz -> targetClass 네이밍 룰 적용
     * </pre>
     *
     * @param targetClass
     *            대상 클래스
     * @param fieldName
     *            확인할 변수 이름
     *
     * @return 존재 여부
     *
     * @since 2012. 02. 14.
     *
     * @see Class#getDeclaredFields()
     */
    public static boolean containsDeclaredField(Class<?> targetClass, String fieldName) {
        Objects.requireNonNull(targetClass);
        Objects.requireNonNull(fieldName);

        for (Field field : targetClass.getDeclaredFields()) {
            if (fieldName.equals(field.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 주어진 {@link Class} 타입에 접근 가능한 해당 변수 이름이 존재하는지 여부를 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2012. 2. 14.     parkjunhong77@gmail.com         최초 작성
     * 2026. 4. 2.      parkjunhong77@gmail.com         (개선) clazz -> targetClass 네이밍 룰 적용
     * </pre>
     *
     * @param targetClass
     *            대상 클래스
     * @param fieldName
     *            확인할 변수 이름
     *
     * @return 존재 여부
     *
     * @since 2012. 02. 14.
     *
     * @see Class#getFields()
     */
    public static boolean containsField(Class<?> targetClass, String fieldName) {
        Objects.requireNonNull(targetClass);
        Objects.requireNonNull(fieldName);

        for (Field field : targetClass.getFields()) {
            if (fieldName.equals(field.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 대상 Class의 {@link TypeVariable} 정보와 실제로 사용될 {@link Class} 정보를 조합해서 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2014. 6. 18.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param targetClass
     *            {@link TypeVariable}이 사용된 클래스
     * @param lastCopy
     *            {@code typeVarClasses}의 개수가 {@code targetClass}의 실제 {@link TypeVariable} 개수보다 적은 경우 마지막 값으로 채울지 여부
     * @param typeVarClasses
     *            {@link TypeVariable}로 사용될 클래스 목록
     *
     * @return 조합된 제네릭 타입 변수 목록
     *
     * @since 2014. 6. 18.
     */
    // 아래 내용에 적용됨.
    // - AssertUtils2.notNulls((Object[]) standardTypes);
    // [PATCH] 배열 공변성/가변성에 대한 IDE 분석기의 오탐 우회
    // [TODO] 향후 IDE의 배열 데이터 흐름 분석이 고도화되거나 JSpecify가 완벽히 지원되면 '제거'
    @SuppressWarnings("null")
    public static List<GenericTypeVariable> createGenericTypeVariables(Class<?> targetClass, boolean lastCopy, Class<?>... typeVarClasses) {
        Objects.requireNonNull(targetClass);
        AssertUtils2.notNulls((Object[]) typeVarClasses);

        List<GenericTypeVariable> gTypeVars = new ArrayList<GenericTypeVariable>();
        if (typeVarClasses.length < 1) {
            return gTypeVars;
        }

        TypeVariable<?>[] typeVars = targetClass.getTypeParameters();

        AssertUtils2.isFalse(!lastCopy && typeVars.length > typeVarClasses.length, "The number of TypeVariable MUST be equal to the number of classes used ");

        typeVarClasses = ArrayUtils.adjustByLength(typeVars.length, typeVarClasses);

        for (int i = 0; i < typeVarClasses.length; i++) {
            gTypeVars.add(new GenericTypeVariable(typeVarClasses[i], typeVars[i].getName()));
        }

        return gTypeVars;
    }

    /**
     * 대상 Class의 {@link TypeVariable} 정보와 실제로 사용될 {@link Class} 정보를 조합해서 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2014. 6. 18.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param targetClass
     *            {@link TypeVariable}이 사용된 클래스
     * @param typeVarClass
     *            {@link TypeVariable}로 사용될 클래스
     *
     * @return 조합된 제네릭 타입 변수 객체. 제네릭이 없는 경우 {@code null} 반환.
     *
     * @since 2014. 6. 18.
     */
    public static @Nullable GenericTypeVariable createGenericTypeVariables(Class<?> targetClass, Class<?> typeVarClass) {
        Objects.requireNonNull(targetClass);
        Objects.requireNonNull(typeVarClass);

        TypeVariable<?>[] typeVars = targetClass.getTypeParameters();

        if (typeVars.length < 1) {
            return null;
        }

        return new GenericTypeVariable(typeVarClass, typeVars[0].getName());
    }

    /**
     * 두 멤버({@link Member})의 이름이 동일한지 비교합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2014. 6. 19.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param m1
     *            비교할 첫 번째 멤버
     * @param m2
     *            비교할 두 번째 멤버
     *
     * @return 이름이 같으면 {@code true}, 다르면 {@code false}
     *
     * @since 2014. 6. 19.
     */
    public static boolean equalsName(Member m1, Member m2) {
        Objects.requireNonNull(m1);
        Objects.requireNonNull(m2);

        return m1.getName().equals(m2.getName());
    }

    /**
     * 대상 클래스가 주어진 후보 클래스 목록 중 하나와 일치하는지 확인합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2026. 4. 2.      parkjunhong77@gmail.com         (개선) target -> targetClass 네이밍 룰 적용 및 Javadoc 추가
     * </pre>
     *
     * @param targetClass
     *            비교 대상 클래스
     * @param candidates
     *            후보 클래스 목록
     *
     * @return 일치하는 클래스가 있으면 {@code true}, 없으면 {@code false}
     *
     * @throws IllegalArgumentException
     *             파라미터({@code targetClass})가 {@code null}인 경우 발생.
     */
    public static boolean equalsOneOfClasses(Class<?> targetClass, Class<?>... candidates) {
        AssertUtils2.notNull("targetClass MUST NOT be null. targetClass: " + targetClass);

        for (Class<?> candidate : candidates) {
            if (targetClass.equals(candidate)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 지정된 메소드 서명(Signature)을 기반으로, Generic이 사용된 메소드에서 실제로 사용된 타입 정보를 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2026. 4. 2.      parkjunhong77@gmail.com         (개선) clazz -> targetClass 네이밍 룰 적용
     * </pre>
     *
     * @param targetClass
     *            탐색할 대상 클래스
     * @param methodName
     *            메소드 이름
     * @param paramIndex
     *            찾고자 하는 파라미터 순서 (0-based)
     * @param actualTypeIndex
     *            Actual Type 순서 (0-based)
     * @param parameterTypes
     *            메소드의 파라미터 타입 목록
     *
     * @return 실제 타입 {@link Class}, 찾을 수 없는 경우 {@code null}
     *
     * @throws NoSuchMethodException
     *             지정된 이름과 파라미터 타입을 가진 메소드가 없는 경우 발생.
     * @throws IllegalArgumentException
     *             파라미터({@code parameterTypes}) 배열 내부에 {@code null}이 포함된 경우 발생.
     */
    // 아래 내용에 적용됨.
    // - targetClass.getDeclaredMethod(methodName, parameterTypes)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static @Nullable Class<?> getActualTypeArgument(Class<?> targetClass, String methodName, int paramIndex, int actualTypeIndex, Class<?> @Nullable... parameterTypes)
            throws NoSuchMethodException {
        Objects.requireNonNull(targetClass);
        Objects.requireNonNull(methodName);

        if (parameterTypes != null) {
            AssertUtils2.notNulls((Object[]) parameterTypes);
        }

        Method method = targetClass.getDeclaredMethod(methodName, parameterTypes);

        return getActualTypeArgument(method, paramIndex, actualTypeIndex);
    }

    /**
     * Generic이 사용된 메소드에서 실제로 사용된 타입 정보를 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 6. 17.     parkjunhong77@gmail.com         최초 작성
     * 2026. 2. 26.     parkjunhong77@gmail.com         (3.0.0) JDK 25 마이그레이션: Pattern Matching 적용 및 표준 API 전환
     * </pre>
     *
     * @param method
     *            검사할 메소드
     * @param paramIndex
     *            파라미터 순서 (0-based)
     * @param actualTypeIndex
     *            Actual Type 순서 (0-based)
     *
     * @return 실제 타입 {@link Class}, 찾을 수 없는 경우 {@code null}
     *
     * @since 2019. 6. 17.
     * @version 3.0.0
     */
    public static @Nullable Class<?> getActualTypeArgument(Method method, int paramIndex, int actualTypeIndex) {
        Objects.requireNonNull(method);

        Type[] genericParameterTypes = method.getGenericParameterTypes();

        if (paramIndex < 0 || paramIndex >= genericParameterTypes.length) {
            return null;
        }

        Type paramType = genericParameterTypes[paramIndex];

        if (paramType instanceof ParameterizedType pType) {
            Type[] actualTypes = pType.getActualTypeArguments();

            if (actualTypeIndex < 0 || actualTypeIndex >= actualTypes.length) {
                return null;
            }

            Type actualType = actualTypes[actualTypeIndex];

            if (actualType instanceof Class<?> clazz) {
                return clazz;
            } else if (actualType instanceof ParameterizedType innerPType) {
                return (Class<?>) innerPType.getRawType();
            }
        }

        return null;
    }

    /**
     * 상위 클래스를 포함하여 객체의 계층 구조 전체에서 주어진 어노테이션이 설정된 {@link Field}와 어노테이션 객체를 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2026. 4. 2.      parkjunhong77@gmail.com         (개선) 오타 수정된 오버로딩 메소드 호출로 연계
     * </pre>
     *
     * @param <T>
     *            어노테이션 타입
     * @param object
     *            탐색할 원본 객체 또는 클래스
     * @param annotationClass
     *            찾고자 하는 어노테이션 클래스
     *
     * @return 필드와 어노테이션 매핑 정보
     *
     * @see #getAnnotatedFields(Object, Class, IFilter)
     * @see Class#getDeclaredFields()
     */
    public static final <T extends Annotation> Map<Field, T> getAllAnnotatedFields(Object object, Class<T> annotationClass) {
        return getAllAnnotatedFields(object, annotationClass, new IFilter.TrueFilter<T>());
    }

    /**
     * 상위 클래스를 포함하여 객체의 계층 구조 전체에서 주어진 어노테이션이 설정된 {@link Field}와 어노테이션 객체를 필터링하여 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2026. 4. 2.      parkjunhong77@gmail.com         (개선) 메소드명 오타 수정(Annodated -> Annotated)
     * </pre>
     *
     * @param <T>
     *            어노테이션 타입
     * @param object
     *            탐색할 원본 객체 또는 클래스
     * @param annotationClass
     *            찾고자 하는 어노테이션 클래스
     * @param filter
     *            사용자 정의 필터 (조건부 필터링)
     *
     * @return 필드와 어노테이션 매핑 정보
     *
     * @see #getAnnotatedFields(Object, Class, IFilter)
     * @see Class#getDeclaredFields()
     */
    public static final <T extends Annotation> Map<Field, T> getAllAnnotatedFields(Object object, Class<T> annotationClass, @Nullable IFilter<T> filter) {
        Objects.requireNonNull(object);
        Objects.requireNonNull(annotationClass);

        Map<Field, T> annotatedFields = new HashMap<Field, T>();
        Class<?> implClass = (object instanceof Class<?> clazz) ? clazz : object.getClass();

        while (!implClass.equals(Object.class)) {
            annotatedFields.putAll(getAnnotatedFields(implClass, annotationClass, filter));
            implClass = implClass.getSuperclass();
        }

        return annotatedFields;
    }

    /**
     * 주어진 어노테이션이 설정된 {@link Constructor}와 어노테이션 객체를 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 6. 17.     parkjunhong77@gmail.com         최초 작성
     * 2026. 2. 26.     parkjunhong77@gmail.com         (3.0.0) JDK 25 마이그레이션: Pattern Matching, Stream API 적용
     * </pre>
     *
     * @param <T>
     *            어노테이션 타입
     * @param object
     *            탐색할 원본 객체 또는 클래스
     * @param annotationClass
     *            찾고자 하는 어노테이션 클래스
     *
     * @return 생성자와 어노테이션 매핑 정보
     *
     * @since 2019. 6. 17.
     * @version 3.0.0
     */
    // 아래 내용에 적용됨.
    // - Collectors.toUnmodifiableMap(c -> c, c -> c.getAnnotation(annotationClass))
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static final <T extends Annotation> Map<Constructor<?>, T> getAnnotatedConstructors(Object object, Class<T> annotationClass) {
        Objects.requireNonNull(object);
        Objects.requireNonNull(annotationClass);

        Class<?> implClass = (object instanceof Class<?> clazz) ? clazz : object.getClass();

        return Arrays.stream(implClass.getDeclaredConstructors()) //
                .filter(c -> c.isAnnotationPresent(annotationClass)) //
                .collect( //
                        Collectors.toUnmodifiableMap(c -> c, c -> c.getAnnotation(annotationClass))//
                );
    }

    /**
     * 해당 클래스에 선언된 주어진 어노테이션이 설정된 {@link Field}와 어노테이션 객체를 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2026. 4. 2.      parkjunhong77@gmail.com         (개선) Javadoc 포맷팅 규칙 적용
     * </pre>
     *
     * @param <T>
     *            어노테이션 타입
     * @param object
     *            탐색할 원본 객체 또는 클래스
     * @param annotationClass
     *            찾고자 하는 어노테이션 클래스
     *
     * @return 필드와 어노테이션 매핑 정보
     *
     * @see #getAnnotatedFields(Object, Class, IFilter)
     * @see Class#getDeclaredFields()
     */
    public static final <T extends Annotation> Map<Field, T> getAnnotatedFields(Object object, Class<T> annotationClass) {
        return getAnnotatedFields(object, annotationClass, new IFilter.TrueFilter<T>());
    }

    /**
     * 해당 클래스에 선언된 주어진 어노테이션이 설정된 {@link Field}와 어노테이션 객체를 필터링하여 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 12.    parkjunhong77@gmail.com         최초 작성
     * 2026. 2. 26.     parkjunhong77@gmail.com         JDK 25 마이그레이션: Pattern Matching, Stream API 적용
     * </pre>
     *
     * @param <T>
     *            어노테이션 타입
     * @param object
     *            탐색할 원본 객체 또는 클래스
     * @param annotationClass
     *            찾고자 하는 어노테이션 클래스
     * @param filter
     *            사용자 정의 필터
     *
     * @return 필드와 어노테이션 매핑 정보
     *
     * @see Class#getDeclaredFields()
     */
    // 아래 내용에 적용됨.
    // - Collectors.toUnmodifiableMap(c -> c, c -> c.getAnnotation(annotationClass)) //
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static final <T extends Annotation> Map<Field, T> getAnnotatedFields(Object object, Class<T> annotationClass, @Nullable IFilter<T> filter) {
        Objects.requireNonNull(object);
        Objects.requireNonNull(annotationClass);

        Class<?> implClass = (object instanceof Class<?> clazz) ? clazz : object.getClass();

        return Arrays.stream(implClass.getDeclaredFields()) //
                .filter(f -> {
                    T anno = f.getAnnotation(annotationClass);
                    return anno != null && (filter == null || filter.filter(anno, f));
                }) //
                .collect( //
                        Collectors.toUnmodifiableMap(c -> c, c -> c.getAnnotation(annotationClass)) //
                );
    }

    /**
     * 주어진 어노테이션이 설정되어 있는 'public' 메소드 목록을 반환합니다. 상위 클래스 및 인터페이스의 메소드를 포함합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 6. 17.     parkjunhong77@gmail.com         최초 작성
     * 2026. 4. 2.      parkjunhong77@gmail.com         (개선) dataType -> targetClass 네이밍 룰 적용
     * </pre>
     *
     * @param <A>
     *            어노테이션 타입
     * @param annotationClass
     *            찾고자 하는 어노테이션 클래스
     * @param targetClass
     *            탐색 대상 클래스 (데이터 타입)
     *
     * @return 어노테이션이 설정된 메소드 목록
     *
     * @since 2019. 6. 17.
     *
     * @see Class#getMethods()
     */
    // 아래 내용에 적용됨.
    // - Collectors.toList()
    // - Stream.collect(...)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static <A extends Annotation> Collection<Method> getAnnotatedMethods(Class<A> annotationClass, Class<?> targetClass) {
        Objects.requireNonNull(annotationClass);
        Objects.requireNonNull(targetClass);

        return Arrays.stream(targetClass.getMethods()) //
                .filter(m -> m.getAnnotation(annotationClass) != null) //
                .collect(Collectors.toList());
    }

    /**
     * {@link AccessibleObject}에서 특정 {@link Annotation} 객체를 추출하여 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2026. 4. 2.      parkjunhong77@gmail.com         (개선) Javadoc 포맷팅 규칙 적용
     * </pre>
     *
     * @param <T>
     *            어노테이션 타입
     * @param accessObj
     *            어노테이션이 설정된 접근 가능 객체 (메소드, 필드 등)
     * @param annotationClass
     *            찾고자 하는 어노테이션 클래스
     *
     * @return 추출된 어노테이션 객체. 존재하지 않는 경우 {@code null} 반환.
     */
    public static <T extends Annotation> @Nullable T getAnnotation(AccessibleObject accessObj, Class<T> annotationClass) {
        Objects.requireNonNull(accessObj);
        Objects.requireNonNull(annotationClass);

        return accessObj.getAnnotation(annotationClass);
    }

    /**
     * 클래스 레벨에 설정된 특정 {@link Annotation} 객체를 추출하여 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2026. 4. 2.      parkjunhong77@gmail.com         (개선) clazz -> targetClass 네이밍 룰 적용
     * </pre>
     *
     * @param <T>
     *            어노테이션 타입
     * @param targetClass
     *            탐색할 대상 클래스
     * @param annotationClass
     *            찾고자 하는 어노테이션 클래스
     *
     * @return 추출된 어노테이션 객체. 존재하지 않는 경우 {@code null} 반환.
     */
    public static <T extends Annotation> @Nullable T getAnnotation(Class<?> targetClass, Class<T> annotationClass) {
        Objects.requireNonNull(targetClass);
        Objects.requireNonNull(annotationClass);

        return targetClass.getAnnotation(annotationClass);
    }

    /**
     * 객체에서 주어진 논리적 데이터 형식({@code targetType})과 호환되는 필드 목록을 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 6. 17.     parkjunhong77@gmail.com         최초 작성
     * 2026. 2. 26.     parkjunhong77@gmail.com         (3.0.0) JDK 25 마이그레이션: Pattern Matching, Stream API 적용
     * 2026. 4. 2.      parkjunhong77@gmail.com         (3.0.0) fieldType -> targetType 네이밍 룰 적용 (논리적 형식 기준)
     * </pre>
     *
     * @param instance
     *            탐색 대상 객체 또는 클래스
     * @param targetType
     *            할당 가능 여부를 검증할 기준 데이터 형식(타입)
     *
     * @return 호환되는 필드 목록 (불변 리스트)
     *
     * @since 2019. 6. 17.
     * @version 3.0.0
     *
     * @see Class#isAssignableFrom(Class)
     * @see Class#getDeclaredFields()
     */
    // 아래 내용에 적용됨.
    // - Collectors.toList()
    // - Stream.collect(...)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static List<Field> getDeclaredFields(Object instance, Class<?> targetType) {
        Objects.requireNonNull(instance);
        Objects.requireNonNull(targetType);

        Class<?> clazz = (instance instanceof Class<?> c) ? c : instance.getClass();

        return Arrays.stream(clazz.getDeclaredFields()) //
                .filter(field -> targetType.isAssignableFrom(field.getType())) //
                .collect(Collectors.toList());
    }

    /**
     * 특정 어노테이션이 설정된 필드들 중에서 제네릭 타입 변수({@link TypeVariable})를 사용하는 필드의 메타데이터를 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2026. 4. 2.      parkjunhong77@gmail.com         (개선) class_ -> targetClass 네이밍 룰 적용
     * </pre>
     *
     * @param <T>
     *            어노테이션 타입
     * @param targetClass
     *            탐색할 대상 클래스
     * @param annotationClass
     *            필터링 기준이 될 어노테이션 클래스
     *
     * @return 타입 변수 정보를 담은 컬렉션
     */
    // 아래 내용에 적용됨.
    // - field.getName()
    // - (TypeVariable) type
    // - ((TypeVariable) type).getName()
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings({ "rawtypes", "unchecked", "null" })
    public static <T extends Annotation> Collection<FieldTypeVariable> getTypeVariableName(Class<?> targetClass, Class<T> annotationClass) {
        Collection<FieldTypeVariable> fieldTypeVars = new HashSet<FieldTypeVariable>();

        FieldTypeVariable fieldTypeVar = null;
        Type type = null;
        for (Field field : getAnnotatedFields(targetClass, annotationClass).keySet()) {
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
     * 클래스에 선언된 제네릭 타입 변수명(Literal) 목록을 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2014. 6. 18.     parkjunhong77@gmail.com         최초 작성
     * 2026. 4. 2.      parkjunhong77@gmail.com         (개선) classClass -> targetClass 네이밍 룰 적용
     * </pre>
     *
     * @param targetClass
     *            제네릭이 선언된 대상 클래스
     *
     * @return 타입 변수 이름 목록
     *
     * @since 2014. 6. 18.
     */
    public static List<String> getTypeVariableNames(Class<?> targetClass) {
        Objects.requireNonNull(targetClass);

        ArrayList<String> typeVarNames = new ArrayList<String>();

        for (TypeVariable<?> typeVar : targetClass.getTypeParameters()) {
            typeVarNames.add(typeVar.getName());
        }

        return typeVarNames;
    }

    /**
     * 주어진 객체에서 특정 필드의 값을 추출하여 문자열로 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2014. 6. 18.     parkjunhong77@gmail.com         최초 작성
     * 2026. 2. 26.     parkjunhong77@gmail.com         (3.0.0) JDK 25 마이그레이션: trySetAccessible 적용 및 다중 예외 처리
     * </pre>
     *
     * @param field
     *            대상 필드
     * @param instance
     *            필드 값을 추출할 대상 객체
     *
     * @return 추출된 필드의 문자열 값. 값이 없거나 예외 발생 시 빈 문자열.
     *
     * @since 2014. 6. 18.
     * @version 3.0.0
     */
    public static @Nullable String getValue(Field field, Object instance) {
        Object value = getValue(field, instance, null);
        return Objects.toString(value, null);
    }

    /**
     * 주어진 객체에서 특정 필드의 값을 추출하여 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2014. 6. 18.     parkjunhong77@gmail.com         최초 작성
     * 2026. 2. 26.     parkjunhong77@gmail.com         (3.0.0) JDK 25 마이그레이션: trySetAccessible 적용 및 다중 예외 처리
     * </pre>
     *
     * @param field
     *            대상 필드 (Nullable)
     * @param instance
     *            필드 값을 추출할 대상 객체
     * @param defaultValue
     *            값 추출 실패 시 반환할 기본값
     *
     * @return 추출된 필드의 객체. 예외 발생 시 defaultValue 반환.
     *
     * @since 2014. 6. 18.
     * @version 3.0.0
     */
    public static @Nullable Object getValue(@Nullable Field field, Object instance, @Nullable Object defaultValue) {
        Objects.requireNonNull(instance);

        if (field == null) {
            return defaultValue;
        }

        if (!field.canAccess(instance)) {
            if (!field.trySetAccessible()) {
                return defaultValue;
            }
        }

        try {
            Object value = field.get(instance);
            return (value != null) ? value : defaultValue;
        } catch (IllegalArgumentException | IllegalAccessException e) {
            return defaultValue;
        }
    }

    /**
     * 주어진 객체의 특정 필드 값을 데이터 타입의 기본값으로 초기화합니다.<br>
     * <b>NOTE:</b> 파라미터 {@code field}는 반드시 접근이 허용된 상태여야 합니다.
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
     *            초기화할 대상 필드
     *
     * @throws IllegalArgumentException
     *             필드가 해당 객체에 속하지 않는 경우 발생.
     * @throws IllegalAccessException
     *             필드에 접근할 수 없는 권한일 경우 발생.
     *
     * @since 2014. 4. 2.
     * @version 3.0.0
     */
    public static void resetField(Object object, Field field) throws IllegalArgumentException, IllegalAccessException {
        Objects.requireNonNull(object);
        Objects.requireNonNull(field);

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
     * 주어진 객체의 특정 필드 값을 강제로 접근 권한을 획득한 후 기본값으로 초기화합니다.
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
     *            초기화할 대상 필드
     *
     * @throws IllegalArgumentException
     *             필드가 해당 객체에 속하지 않는 경우 발생.
     * @throws IllegalAccessException
     *             모듈 시스템 등에 의해 필드 접근 권한 획득에 실패한 경우 발생.
     *
     * @since 2014. 4. 2.
     * @version 3.0.0
     */
    public static void resetFieldForced(Object object, Field field) throws IllegalArgumentException, IllegalAccessException {
        Objects.requireNonNull(object);
        Objects.requireNonNull(field);

        if (!field.canAccess(object)) {
            if (!field.trySetAccessible()) {
                throw new IllegalAccessException("필드에 접근할 수 없습니다 (모듈 캡슐화 등): " + field.getName());
            }
        }

        resetField(object, field);
    }

    /**
     * 대상 클래스가 주어진 후보 클래스 목록 중 하나의 하위 클래스(Subclass) 또는 구현체인지 검사합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2026. 4. 2.      parkjunhong77@gmail.com         (개선) target -> targetClass 네이밍 룰 적용 및 가변 인자 내부 널 검증 명세 추가
     * </pre>
     *
     * @param targetClass
     *            검사할 대상 하위 클래스
     * @param candidates
     *            상위 타입(슈퍼클래스/인터페이스) 후보 목록
     *
     * @return 하위 클래스인 경우 {@code true}, 그렇지 않으면 {@code false}
     */
    // 아래 내용에 적용됨.
    // - AssertUtils2.notNulls((Object[]) candidates);
    // [PATCH] 배열 공변성/가변성에 대한 IDE 분석기의 오탐 우회
    // [TODO] 향후 IDE의 배열 데이터 흐름 분석이 고도화되거나 JSpecify가 완벽히 지원되면 '제거'
    @SuppressWarnings("null")
    public static boolean subclassOneOf(Class<?> targetClass, Class<?>... candidates) {
        Objects.requireNonNull(targetClass);
        AssertUtils2.notNulls((Object[]) candidates);

        for (Class<?> candidate : candidates) {
            if (candidate.isAssignableFrom(targetClass)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 대상 객체의 클래스가 주어진 후보 클래스 목록 중 하나의 하위 클래스(Subclass) 또는 구현체인지 검사합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2026. 4. 2.      parkjunhong77@gmail.com         (개선) Javadoc 포맷팅 규칙 적용
     * </pre>
     *
     * @param object
     *            검사할 대상 객체
     * @param candidates
     *            상위 타입(슈퍼클래스/인터페이스) 후보 목록
     *
     * @return 하위 클래스인 경우 {@code true}, 그렇지 않으면 {@code false}
     */
    public static boolean subclassOneOf(Object object, Class<?>... candidates) {
        Objects.requireNonNull(object);

        return subclassOneOf(object.getClass(), candidates);
    }

    /**
     * 대상 객체의 상위 계층을 구성하는 클래스/인터페이스들 중에서, 주어진 후보 목록에 해당하는 모든 상위 타입을 찾아 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2014. 5. 2.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param object
     *            계층 구조를 탐색할 원본 대상 객체
     * @param candidates
     *            찾고자 하는 상위 타입 후보 목록
     *
     * @return 일치하는 상위 타입(클래스/인터페이스) 목록
     *
     * @since 2014. 5. 2.
     */
    // 아래 내용에 적용됨.
    // - AssertUtils2.notNulls((Object[]) candidates);
    // [PATCH] 배열 공변성/가변성에 대한 IDE 분석기의 오탐 우회
    // [TODO] 향후 IDE의 배열 데이터 흐름 분석이 고도화되거나 JSpecify가 완벽히 지원되면 '제거'
    @SuppressWarnings("null")
    public static List<Class<?>> superclasses(Object object, Class<?>... candidates) {
        Objects.requireNonNull(object);
        AssertUtils2.notNulls((Object[]) candidates);

        List<Class<?>> superclasses = new ArrayList<Class<?>>();

        Class<?> targetClass = object.getClass();
        for (Class<?> candidate : candidates) {
            if (candidate.isAssignableFrom(targetClass)) {
                superclasses.add(candidate);
            }
        }

        return superclasses;
    }
}