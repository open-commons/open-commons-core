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
*/
package open.commons.core.utils;

import java.lang.annotation.Annotation;
import java.lang.annotation.AnnotationFormatError;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jspecify.annotations.Nullable;

/**
 * @since 2012. 2. 6.
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
public class AnnotationUtils {

    /**
     * 주어진 객체에 찾고자 하는 {@link Annotation}이 모두 설정되어 있는지 여부를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 11. 9.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param accObj
     * @param annoClasses
     *            어노테이션 타입.
     * @return
     *
     * @since 2020. 11. 9.
     * 
     * @see AccessibleObject#isAnnotationPresent(Class)
     */
    // 아래 내용에 적용됨.
    // - AssertUtils2.notNulls((Object[]) annoClasses);
    // [PATCH] 배열 공변성/가변성에 대한 IDE 분석기의 오탐 우회
    // [TODO] 향후 IDE의 배열 데이터 흐름 분석이 고도화되거나 JSpecify가 완벽히 지원되면 '제거'
    @SuppressWarnings("null")
    @SafeVarargs
    public static boolean existAllAnnotations(AccessibleObject accObj, Class<? extends Annotation>... annoClasses) {
        Objects.requireNonNull(accObj);
        AssertUtils2.notNulls((Object[]) annoClasses);

        for (Class<? extends Annotation> annoClass : annoClasses) {
            if (!accObj.isAnnotationPresent(annoClass)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 대상 클래스의 {@link Field} 중에서 특정 {@link Annotation}이 있는 {@link Field}만 제공합니다. <br>
     * {@link Field}는 해당 클래스에서만 정의한 'public, protected, default(package), private' 를 포함하지만, 상위 클래스에서 정의한 {@link Field}를
     * 포함하지 않습니다.
     * 
     * <pre>
     * [개정이력]
     * 날짜       | 작성자   |   내용
     * ------------------------------------------
     * 2019. 5. 29.     parkjunhong77@gmail.com         최초 작성
     * 2026. 3. 4.      parkjunhong77@gmail.com         (3.0.0) JDK 25 마이그레이션: Stream API 적용 및 복원 로직 제거
     * </pre>
     *
     * @param typeClass
     *            대상 클래스
     * @param annoClass
     *            찾고자 하는 어노테이션 클래스
     * @return 어노테이션이 적용된 필드 목록 (불변 리스트)
     *
     * @since 2019. 5. 29.
     * @version 3.0.0
     * 
     * @see Class#getDeclaredFields()
     * @see AccessibleObject#isAnnotationPresent(Class)
     */
    // 아래 내용에 적용됨.
    // - Arrays.stream(...).filter(...).peek(...).toList()
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static List<Field> getAnnotatedFields(Class<?> typeClass, Class<? extends Annotation> annoClass) {
        AssertUtils2.notNulls(typeClass, annoClass);

        return Arrays.stream(typeClass.getDeclaredFields()) //
                .filter(f -> f.isAnnotationPresent(annoClass)) //
                // 호출자의 편의를 위해 미리 접근 권한 확보 시도
                .peek(Field::trySetAccessible) //
                .toList() //
        ;
    }

    /**
     * 대상 클래스의 {@link Field} 중에서 모든 {@link Annotation}이 있는 {@link Field}만 제공합니다. <br>
     * {@link Field}는 해당 클래스에서만 정의한 'public, protected, default(package), private' 를 포함하지만, 상위 클래스에서 정의한 {@link Field}를
     * 포함하지 않습니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2026. 3. 4.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param typeClass
     *            대상 클래스
     * @param annoClasses
     *            찾고자 하는 어노테이션 클래스
     * @return 어노테이션이 적용된 필드 목록 (불변 리스트)
     *
     * @since 2026. 3. 4.
     * @version 3.0.0
     */
    // 아래 내용에 적용됨.
    // - AssertUtils2.notNulls((Object[]) annoClasses);
    // [PATCH] 배열 공변성/가변성에 대한 IDE 분석기의 오탐 우회
    // [TODO] 향후 IDE의 배열 데이터 흐름 분석이 고도화되거나 JSpecify가 완벽히 지원되면 '제거'
    @SuppressWarnings("null")
    @SafeVarargs
    public static List<Field> getAnnotatedFields(Class<?> typeClass, Class<? extends Annotation>... annoClasses) {
        Objects.requireNonNull(typeClass);
        AssertUtils2.notNulls((Object[]) annoClasses);

        return Arrays.stream(typeClass.getDeclaredFields())// create fields stream
                .filter(f -> existAllAnnotations(f, annoClasses)) // check annotation
                .collect(Collectors.toList());
    }

    /**
     * 대상 클래스의 {@link Field} 중에서 특정 {@link Annotation}이 있는 {@link Field}만 제공합니다. <br>
     * {@link Field}는 해당 클래스에서만 정의한 'public, protected, default(package), private' 를 포함하지만, 상위 클래스에서 정의한 {@link Field}를
     * 포함하지 않습니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2012. 2. 6.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param object
     *            대상 객체
     * @param annoClass
     *            찾고자 하는 어노테이션 클래스
     * @return 어노테이션이 적용된 필드 목록 (불변 리스트)
     *
     * @since 2012. 2. 6.
     * @version 3.0.0
     * 
     * @see #getAnnotatedFields(Class, Class)
     */
    public static List<Field> getAnnotatedFields(Object object, Class<? extends Annotation> annoClass) {
        AssertUtils2.notNulls(object, annoClass);

        return getAnnotatedFields(object.getClass(), annoClass);
    }

    /**
     * 대상 클래스의 {@link Field} 중에서 특정 {@link Annotation}이 있는 {@link Field}만 제공합니다. <br>
     * 상위 클래스에서 정의한 {@link Field}를 포함하여 'public' {@link Field}만 제공합니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 5. 29.		parkjunhong77@gmail.com			최초 작성
     * 2026. 3. 4.      parkjunhong77@gmail.com         기존 구현이 {@link #getAnnotatedFields(Class, Class)}와 동일하게 구현되어 기능을 수정.
     * </pre>
     *
     * @param typeClass
     *            대상 클래스
     * @param annoClass
     *            찾고자 하는 어노테이션 클래스
     * @return 어노테이션이 적용된 필드 목록 (불변 리스트)
     *
     * @since 2019. 5. 29.
     * @version 3.0.0
     * 
     * @see Class#getFields()
     * @see AccessibleObject#isAnnotationPresent(Class)
     */
    // apply to 'Collectors.toList()'
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static List<Field> getAnnotatedFieldsAll(Class<?> typeClass, Class<? extends Annotation> annoClass) {
        AssertUtils2.notNulls(typeClass, annoClass);

        return Arrays.stream(typeClass.getFields()) // create fields stream
                .filter(f -> f.isAnnotationPresent(annoClass)) // check annotation
                .collect(Collectors.toList()) //
        ;
    }

    /**
     * 대상 클래스의 {@link Field} 중에서 모든 {@link Annotation}이 있는 {@link Field}만 제공합니다. <br>
     * 상위 클래스에서 정의한 {@link Field}를 포함하여 'public' {@link Field}만 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 11. 9.		parkjunhong77@gmail.com			최초 작성
     * 2026. 3. 4.      parkjunhong77@gmail.com         기존 구현이 {@link #getAnnotatedFields(Class, Class)}와 동일하게 구현되어 기능을 수정.
     * </pre>
     *
     * @param typeClass
     *            대상 클래스
     * @param annoClasses
     *            찾고자 하는 어노테이션 클래스
     * @return 어노테이션이 적용된 필드 목록 (불변 리스트)
     *
     * @since 2020. 11. 9.
     * @version 3.0.0
     * 
     * @see Class#getDeclaredFields()
     * @see #existAllAnnotations(AccessibleObject, Class...)
     */
    // 아래 내용에 적용됨.
    // - AssertUtils2.notNulls((Object[]) annoClasses);
    // [PATCH] 배열 공변성/가변성에 대한 IDE 분석기의 오탐 우회
    // [TODO] 향후 IDE의 배열 데이터 흐름 분석이 고도화되거나 JSpecify가 완벽히 지원되면 '제거'
    @SuppressWarnings("null")
    @SafeVarargs
    public static List<Field> getAnnotatedFieldsAll(Class<?> typeClass, Class<? extends Annotation>... annoClasses) {
        Objects.requireNonNull(typeClass);
        AssertUtils2.notNulls((Object[]) annoClasses);

        return Arrays.stream(typeClass.getFields()) // create fields stream
                .filter(f -> existAllAnnotations(f, annoClasses)) // check annotation
                .collect(Collectors.toList()) //
        ;
    }

    /**
     * 대상 클래스의 {@link Field} 중에서 특정 {@link Annotation}이 있는 {@link Field}만 제공합니다. <br>
     * 상위 클래스에서 정의한 {@link Field}를 포함하여 'public' {@link Field}만 제공합니다. <br>
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2017. 9. 26.		parkjunhong77@gmail.com			최초 작성
     * 2026. 3. 4.      parkjunhong77@gmail.com         기존 구현이 {@link #getAnnotatedFields(Class, Class)}와 동일하게 구현되어 기능을 수정.
     * </pre>
     *
     * @param object
     *            대상 객체
     * @param annoClass
     *            찾고자 하는 어노테이션 클래스
     * @return 어노테이션이 적용된 필드 목록 (불변 리스트)
     *
     * @since 2017. 9. 26.
     * @version 3.0.0
     * 
     * @see Class#getFields()
     * @see #getAnnotatedFieldsAll(Class, Class)
     */
    public static List<Field> getAnnotatedFieldsAll(Object object, Class<? extends Annotation> annoClass) {
        AssertUtils2.notNulls(object, annoClass);

        return getAnnotatedFieldsAll(object.getClass(), annoClass);
    }

    /**
     * 대상 클래스의 {@link Field} 중에서 특정 {@link Annotation}이 있는 {@link Field}만 제공합니다. <br>
     * 상위 클래스에서 정의한 {@link Field}를 포함하여 'public' {@link Field}만 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 11. 9.		parkjunhong77@gmail.com			최초 작성
     * 2026. 3. 4.      parkjunhong77@gmail.com         기존 구현이 {@link #getAnnotatedFields(Class, Class)}와 동일하게 구현되어 기능을 수정.
     * </pre>
     *
     * @param typeClass
     *            대상 클래스
     * @param annoClass
     *            찾고자 하는 어노테이션 클래스
     * @return 어노테이션이 적용된 필드 목록 (불변 리스트)
     *
     * @since 2020. 11. 9.
     * @version 3.0.0
     * 
     * @see Class#getFields()
     * @see AccessibleObject#isAnnotationPresent(Class)
     */
    // 아래 내용에 적용됨.
    // - .filter(f -> f.isAnnotationPresent(annoClass))
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static Stream<Field> getAnnotatedFieldsAllAsStream(Class<?> typeClass, Class<? extends Annotation> annoClass) {
        AssertUtils2.notNulls(typeClass, annoClass);

        return Arrays.stream(typeClass.getFields()) // create fields stream
                .filter(f -> f.isAnnotationPresent(annoClass)) // check annotation
        ;
    }

    /**
     * 대상 클래스의 {@link Field} 중에서 모든 {@link Annotation}이 있는 {@link Field}만 제공합니다. <br>
     * 상위 클래스에서 정의한 {@link Field}를 포함하여 'public' {@link Field}만 제공합니다. <br>
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 11. 9.		parkjunhong77@gmail.com			최초 작성
     * 2026. 3. 4.      parkjunhong77@gmail.com         기존 구현이 {@link #getAnnotatedFields(Class, Class)}와 동일하게 구현되어 기능을 수정.
     * </pre>
     *
     * @param typeClass
     *            대상 클래스
     * @param annoClasses
     *            찾고자 하는 어노테이션 클래스
     * @return 어노테이션이 적용된 필드 목록 (불변 리스트)
     *
     * @since 2020. 11. 9.
     * @version 3.0.0
     * 
     * @see Class#getDeclaredFields()
     * @see #existAllAnnotations(AccessibleObject, Class...)
     */
    // 아래 내용에 적용됨.
    // - AssertUtils2.notNulls((Object[]) annoClasses);
    // [PATCH] 배열 공변성/가변성에 대한 IDE 분석기의 오탐 우회
    // [TODO] 향후 IDE의 배열 데이터 흐름 분석이 고도화되거나 JSpecify가 완벽히 지원되면 '제거'
    @SuppressWarnings("null")
    @SafeVarargs
    public static Stream<Field> getAnnotatedFieldsAllAsStream(Class<?> typeClass, Class<? extends Annotation>... annoClasses) {
        Objects.requireNonNull(typeClass);
        AssertUtils2.notNulls((Object[]) annoClasses);

        return Arrays.stream(typeClass.getDeclaredFields()) // create fields stream
                .filter(f -> existAllAnnotations(f, annoClasses)) // check annotation
        ;
    }

    /**
     * 대상 클래스의 {@link Field} 중에서 특정 {@link Annotation}이 있는 {@link Field}만 제공합니다. <br>
     * 상위 클래스에서 정의한 {@link Field}를 포함하여 'public' {@link Field}만 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 3.		parkjunhong77@gmail.com			최초 작성
     * 2026. 3. 4.      parkjunhong77@gmail.com         기존 구현이 {@link #getAnnotatedFields(Class, Class)}와 동일하게 구현되어 기능을 수정.
     * </pre>
     *
     * @param object
     *            대상 객체
     * @param annoClass
     *            찾고자 하는 어노테이션 클래스
     * @return 어노테이션이 적용된 필드 목록 (불변 리스트)
     *
     * @since 2021. 11. 3.
     * @version 3.0.0
     */
    public static Stream<Field> getAnnotatedFieldsAllAsStream(Object object, Class<? extends Annotation> annoClass) {
        AssertUtils2.notNulls(object, annoClass);

        return getAnnotatedFieldsAllAsStream(object.getClass(), annoClass);
    }

    /**
     * 대상 클래스에 정의된 {@link Field}와 상위 클래스에 정의된 {@link Field}까지 제공합니다. <br>
     * 해당 클래스 및 상위 클래스에서 정의한 'public, protected, default(package), private' 를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 9. 2.		parkjunhong77@gmail.com			최초 작성
     * 2026. 3. 4.      parkjunhong77@gmail.com         기존 구현이 {@link #getAnnotatedFields(Class, Class)}와 동일하게 구현되어 기능을 수정.
     * </pre>
     *
     * @param typeClass
     *            대상 클래스
     * @param annoClasses
     *            찾고자 하는 어노테이션 클래스
     * @return 어노테이션이 적용된 필드 목록 (불변 리스트)
     *
     * @since 2022. 9. 2.
     * @version 3.0.0
     */
    // 아래 내용에 적용됨.
    // - AssertUtils2.notNulls((Object[]) annoClasses);
    // [PATCH] 배열 공변성/가변성에 대한 IDE 분석기의 오탐 우회
    // [TODO] 향후 IDE의 배열 데이터 흐름 분석이 고도화되거나 JSpecify가 완벽히 지원되면 '제거'
    @SuppressWarnings("null")
    @SafeVarargs
    public static List<Field> getAnnotatedFieldsAllHierarchy(Class<?> typeClass, Class<? extends Annotation>... annoClasses) {
        Objects.requireNonNull(typeClass);
        AssertUtils2.notNulls((Object[]) annoClasses);

        Class<?> type = typeClass;

        Set<Field> fields = new HashSet<>();
        while (type != null && !type.equals(Object.class)) {
            fields.addAll(getAnnotatedFields(type, annoClasses));
            type = type.getSuperclass();
        }

        return new ArrayList<>(fields);
    }

    /**
     * 대상 객체에서 정의된 {@link Field}와 상위 클래스에 정의된 {@link Field}까지 제공합니다. <br>
     * 'public, protected, default(package), private' 를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 9. 2.		parkjunhong77@gmail.com			최초 작성
     * 2026. 3. 4.      parkjunhong77@gmail.com         기존 구현이 {@link #getAnnotatedFields(Class, Class)}와 동일하게 구현되어 기능을 수정.
     * </pre>
     *
     * @param object
     *            대상 클래스
     * @param annoClasses
     *            찾고자 하는 어노테이션 클래스
     * @return 어노테이션이 적용된 필드 목록 (불변 리스트)
     * 
     * @since 2022. 9. 2.
     * @version 3.0.0
     */
    // 아래 내용에 적용됨.
    // - AssertUtils2.notNulls((Object[]) annoClasses);
    // [PATCH] 배열 공변성/가변성에 대한 IDE 분석기의 오탐 우회
    // [TODO] 향후 IDE의 배열 데이터 흐름 분석이 고도화되거나 JSpecify가 완벽히 지원되면 '제거'
    @SuppressWarnings("null")
    @SafeVarargs
    public static List<Field> getAnnotatedFieldsAllHierarchy(Object object, Class<? extends Annotation>... annoClasses) {
        Objects.requireNonNull(object);
        AssertUtils2.notNulls((Object[]) annoClasses);

        return getAnnotatedFieldsAllHierarchy(object.getClass(), annoClasses);
    }

    /**
     * 대상 클래스의 {@link Method} 중에서 특정 {@link Annotation}이 있는 {@link Method}만 제공합니다. <br>
     * {@link Method}는 해당 클래스에서만 정의한 'public, protected, default(package), private' 를 포함하지만, 상위 클래스에서 정의한
     * {@link Method}를 포함하지 않습니다.
     * 
     * <pre>
     * [개정이력]
     * 날짜       | 작성자   |   내용
     * ------------------------------------------
     * 2019. 5. 29.     parkjunhong77@gmail.com         최초 작성
     * 2026. 3. 4.      parkjunhong77@gmail.com         (3.0.0) JDK 25 마이그레이션: Stream API 적용 및 복원 로직 제거
     * </pre>
     *
     * @param typeClass
     *            대상 클래스
     * @param annoClass
     *            찾고자 하는 어노테이션 클래스
     * @return 어노테이션이 적용된 메서드 목록
     * 
     * @since 2019. 5. 29.
     * @version 3.0.0 * @see Class#getDeclaredMethods()
     */
    // 아래 내용에 적용됨.
    // - Arrays.stream(...).filter(...).peek(...).toList()
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static List<Method> getAnnotatedMethods(Class<?> typeClass, Class<? extends Annotation> annoClass) {
        AssertUtils2.notNulls(typeClass, annoClass);

        return Arrays.stream(typeClass.getDeclaredMethods()) //
                .filter(m -> m.isAnnotationPresent(annoClass)) //
                // 호출 시 예외를 방지하기 위해 캡슐화 우회 시도
                .peek(Method::trySetAccessible) //
                .toList() //
        ;
    }

    /**
     * 대상 클래스의 {@link Method} 중에서 특정 {@link Annotation}이 있는 {@link Method}만 제공합니다. <br>
     * {@link Method}는 해당 클래스에서만 정의한 'public, protected, default(package), private' 를 포함하지만, 상위 클래스에서 정의한
     * {@link Method}를 포함하지 않습니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2026. 3. 4.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param typeClass
     * @param annoClasses
     * @return
     *
     * @since 2026. 3. 4.
     * @version 3.0.0
     */
    // 아래 내용에 적용됨.
    // - AssertUtils2.notNulls((Object[]) annoClasses);
    // [PATCH] 배열 공변성/가변성에 대한 IDE 분석기의 오탐 우회
    // [TODO] 향후 IDE의 배열 데이터 흐름 분석이 고도화되거나 JSpecify가 완벽히 지원되면 '제거'
    @SuppressWarnings("null")
    @SafeVarargs
    public static List<Method> getAnnotatedMethods(Class<?> typeClass, Class<? extends Annotation>... annoClasses) {
        Objects.requireNonNull(typeClass);
        AssertUtils2.notNulls((Object[]) annoClasses);

        return Arrays.stream(typeClass.getDeclaredMethods()) // create methods stream
                .filter(m -> existAllAnnotations(m, annoClasses)) // check annotation
                .collect(Collectors.toList()) //
        ;
    }

    /**
     * 대상 클래스의 {@link Method} 중에서 특정 {@link Annotation}이 있는 {@link Method}만 제공합니다. <br>
     * {@link Method}는 해당 클래스에서만 정의한 'public, protected, default(package), private' 를 포함하지만, 상위 클래스에서 정의한
     * {@link Method}를 포함하지 않습니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2012. 2. 6.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param object
     *            대상 객체
     * @param annoClass
     *            찾고자 하는 어노테이션 클래스
     * @return 어노테이션이 적용된 메소드 목록
     *
     * @since 2012. 2. 6.
     * 
     * @see Class#getDeclaredMethods()
     */
    public static List<Method> getAnnotatedMethods(Object object, Class<? extends Annotation> annoClass) {
        return getAnnotatedMethods(object.getClass(), annoClass);
    }

    /**
     * 대상 클래스의 "Public" 메소드 중에서 특정 어노테이션이 있는 메소드만 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 5. 29.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param typeClass
     *            대상 클래스
     * @param annoClass
     *            찾고자 하는 어노테이션 클래스
     * @return 어노테이션이 적용된 필드 목록 (불변 리스트)
     *
     * @since 2019. 5. 29.
     * 
     * @see Class#getMethods()
     */
    // 아래 내용에 적용됨.
    // - Collectors.toList()
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static List<Method> getAnnotatedMethodsAll(Class<?> typeClass, Class<? extends Annotation> annoClass) {
        AssertUtils2.notNulls(typeClass, annoClass);

        return Arrays.stream(typeClass.getMethods()) // create methods stream
                .filter(m -> m.isAnnotationPresent(annoClass)) // check annotation
                .collect(Collectors.toList()) //
        ;
    }

    /**
     * 대상 클래스의 "Public" 메소드 중에서 특정 어노테이션이 있는 메소드만 제공합니다. <br>
     * 상위 클래스에서 정의한 메소드를 포함합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 11. 9.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param typeClass
     *            대상 클래스
     * @param annoClasses
     *            찾고자 하는 어노테이션 클래스
     * @return 어노테이션이 적용된 메소드 목록
     *
     * @since 2020. 11. 9.
     * 
     * @see Class#getMethods()
     * @see #existAllAnnotations(AccessibleObject, Class...)
     */
    // 아래 내용에 적용됨.
    // - AssertUtils2.notNulls((Object[]) annoClasses);
    // [PATCH] 배열 공변성/가변성에 대한 IDE 분석기의 오탐 우회
    // [TODO] 향후 IDE의 배열 데이터 흐름 분석이 고도화되거나 JSpecify가 완벽히 지원되면 '제거'
    @SuppressWarnings("null")
    @SafeVarargs
    public static List<Method> getAnnotatedMethodsAll(Class<?> typeClass, Class<? extends Annotation>... annoClasses) {
        Objects.requireNonNull(typeClass);
        AssertUtils2.notNulls((Object[]) annoClasses);

        return Arrays.stream(typeClass.getMethods()) // create methods stream
                .filter(m -> existAllAnnotations(m, annoClasses)) // check annotation
                .collect(Collectors.toList()) //
        ;
    }

    /**
     * 대상 객체 클래스의 "Public" 메소드 중에서 특정 어노테이션이 있는 메소드만 제공합니다. <br>
     * 상위 클래스에서 정의한 메소드를 포함합니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2017. 9. 26.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param object
     *            대상 객체
     * @param annoClass
     *            찾고자 하는 어노테이션 클래스
     * @return 어노테이션이 적용된 필드 목록 (불변 리스트)
     * 
     * @since 2017. 9. 26.
     * 
     * @see Class#getMethods()
     */
    public static List<Method> getAnnotatedMethodsAll(Object object, Class<? extends Annotation> annoClass) {
        AssertUtils2.notNulls(object, annoClass);

        return getAnnotatedMethodsAll(object.getClass(), annoClass);
    }

    /**
     * 대상 클래스의 "Public" 메소드 중에서 특정 어노테이션이 있는 메소드만 제공합니다. <br>
     * 상위 클래스에서 정의한 메소드를 포함합니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 11. 9.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param object
     *            대상 객체
     * @param annoClasses
     *            찾고자 하는 어노테이션 클래스
     * @return 어노테이션이 적용된 메소드 목록
     *
     * @since 2020. 11. 9.
     * 
     * @see Class#getMethods()
     */
    // 아래 내용에 적용됨.
    // - AssertUtils2.notNulls((Object[]) annoClasses);
    // [PATCH] 배열 공변성/가변성에 대한 IDE 분석기의 오탐 우회
    // [TODO] 향후 IDE의 배열 데이터 흐름 분석이 고도화되거나 JSpecify가 완벽히 지원되면 '제거'
    @SuppressWarnings("null")
    @SafeVarargs
    public static List<Method> getAnnotatedMethodsAll(Object object, Class<? extends Annotation>... annoClasses) {
        Objects.requireNonNull(object);
        AssertUtils2.notNulls((Object[]) annoClasses);

        return getAnnotatedMethodsAll(object.getClass(), annoClasses);
    }

    /**
     * 대상 클래스의 "Public" 메소드 중에서 특정 어노테이션이 있는 메소드만 제공합니다. <br>
     * 상위 클래스에서 정의한 메소드를 포함합니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 11. 9.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param typeClass
     *            대상 클래스
     * @param annoClasses
     *            찾고자 하는 어노테이션 클래스
     * @return 어노테이션이 적용된 메소드 목록
     *
     * @since 2020. 11. 9.
     * 
     * @see Class#getDeclaredFields()
     * @see #existAllAnnotations(AccessibleObject, Class...)
     */
    // 아래 내용에 적용됨.
    // - AssertUtils2.notNulls((Object[]) annoClasses);
    // [PATCH] 배열 공변성/가변성에 대한 IDE 분석기의 오탐 우회
    // [TODO] 향후 IDE의 배열 데이터 흐름 분석이 고도화되거나 JSpecify가 완벽히 지원되면 '제거'
    @SuppressWarnings("null")
    @SafeVarargs
    public static Stream<Method> getAnnotatedMethodsAllAsStream(Class<?> typeClass, Class<? extends Annotation>... annoClasses) {
        Objects.requireNonNull(typeClass);
        AssertUtils2.notNulls((Object[]) annoClasses);

        return Arrays.stream(typeClass.getMethods()) // create methods stream
                .filter(m -> existAllAnnotations(m, annoClasses)) // check annotation
        ;
    }

    /**
     * 대상 클래스의 "Public" 메소드 중에서 특정 어노테이션이 있는 메소드만 제공합니다. <br>
     * 상위 클래스에서 정의한 메소드를 포함합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 11. 9.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param typeClass
     *            대상 클래스
     * @param annoClass
     *            찾고자 하는 어노테이션 클래스
     * @return 어노테이션이 적용된 메소드 목록
     *
     * @since 2020. 11. 9.
     */
    // 아래 내용에 적용됨.
    // - Stream.filter(...)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static Stream<Method> getAnnotatedMethodsAllAsStream(Class<?> typeClass, Class<? extends Annotation> annoClass) {
        AssertUtils2.notNulls(typeClass, annoClass);

        return Arrays.stream(typeClass.getMethods()) // create methods stream
                .filter(m -> m.isAnnotationPresent(annoClass)) // check annotation
        ;
    }

    /**
     * 대상 클래스 및 상위 클래스의 {@link Method} 중에서 특정 {@link Annotation}이 있는 {@link Method}만 제공합니다. <br>
     * 'public, protected, default(package), private' 를 포함합니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 11. 9.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param typeClass
     *            대상 클래스
     * @param annoClasses
     *            찾고자 하는 어노테이션 클래스
     * @return 어노테이션이 적용된 메소드 목록
     *
     * @since 2020. 11. 9.
     */
    // 아래 내용에 적용됨.
    // - AssertUtils2.notNulls((Object[]) annoClasses);
    // [PATCH] 배열 공변성/가변성에 대한 IDE 분석기의 오탐 우회
    // [TODO] 향후 IDE의 배열 데이터 흐름 분석이 고도화되거나 JSpecify가 완벽히 지원되면 '제거'
    @SuppressWarnings("null")
    @SafeVarargs
    public static List<Method> getAnnotatedMethodsAllHierarchy(Class<?> typeClass, Class<? extends Annotation>... annoClasses) {
        Objects.requireNonNull(typeClass);
        AssertUtils2.notNulls((Object[]) annoClasses);

        Class<?> type = typeClass;

        Set<Method> methods = new HashSet<>();
        while (type != null && !type.equals(Object.class)) {
            methods.addAll(getAnnotatedMethods(type, annoClasses));
            type = type.getSuperclass();
        }

        return new ArrayList<>(methods);
    }

    /**
     * 대상 클래스 및 상위 클래스의 {@link Method} 중에서 특정 {@link Annotation}이 있는 {@link Method}만 제공합니다. <br>
     * 'public, protected, default(package), private' 를 포함합니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 11. 9.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param object
     *            대상 객체
     * @param annoClasses
     *            찾고자 하는 어노테이션 클래스
     * 
     * @return 어노테이션이 적용된 메소드 목록
     *
     * @since 2020. 11. 9.
     * 
     * @see #getAnnotatedMethodsAllHierarchy(Class, Class...)
     */
    // 아래 내용에 적용됨.
    // - AssertUtils2.notNulls((Object[]) annoClasses);
    // [PATCH] 배열 공변성/가변성에 대한 IDE 분석기의 오탐 우회
    // [TODO] 향후 IDE의 배열 데이터 흐름 분석이 고도화되거나 JSpecify가 완벽히 지원되면 '제거'
    @SuppressWarnings("null")
    @SafeVarargs
    public static List<Method> getAnnotatedMethodsAllHierarchy(Object object, Class<? extends Annotation>... annoClasses) {
        Objects.requireNonNull(object);
        AssertUtils2.notNulls((Object[]) annoClasses);

        return getAnnotatedMethodsAllHierarchy(object.getClass(), annoClasses);
    }

    /**
     * 객체에서 어노테이션 타입에 맞는 객체를 반환합니다.
     * 
     * @param accessObj
     * @param annoClass
     * @return
     */
    public static <T extends Annotation> @Nullable T getAnnotation(AccessibleObject accessObj, Class<T> annoClass) {
        AssertUtils2.notNulls(accessObj, annoClass);

        return accessObj.getAnnotation(annoClass);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2012. 2. 6.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param clazz
     * @param annoClass
     * @return
     *
     * @since 2012. 2. 6.
     */
    public static <T extends Annotation> @Nullable T getAnnotation(Class<?> clazz, Class<T> annoClass) {
        AssertUtils2.notNulls(clazz, annoClass);

        return clazz.getAnnotation(annoClass);
    }

    /**
     * 주어진 객체에서 {@link Field}의 값을 추출해서 반환합니다.
     * 
     * @param field
     * @param instance
     * @return {@link Field}의 값.
     */
    public static @Nullable String getValue(Field field, Object instance) {
        AssertUtils2.notNulls(field, instance);

        try {
            Object value = field.get(instance);
            if (value != null) {
                return value.toString();
            } else {
                return null;
            }
        } catch (IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 
     * 주어진 객체에 찾고자 하는 {@link Annotation}이 존재하는 경우 {@link Annotation}객체를 반환하고 없는 경우 {@code null}을 반환합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 5. 29.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param type
     * @param annoClass
     *            찾고자 하는 {@link Annotation} 타입
     * 
     * @return {@link AnnotationFormatError} 또는 {@code null}
     *
     * @since 2019. 5. 29.
     */
    public static <A extends Annotation> @Nullable A hasAnnotation(Class<?> type, Class<A> annoClass) {
        AssertUtils2.notNulls(type, annoClass);

        return type.getAnnotation(annoClass);
    }

    /**
     * 주어진 객체에 찾고자 하는 {@link Annotation}이 존재하는 경우 {@link Annotation}객체를 반환하고 없는 경우 {@code null}을 반환합니다.
     * 
     * @param object
     * @param annoClass
     *            찾고자 하는 {@link Annotation} 타입
     * @return {@link AnnotationFormatError} 또는 {@code null}
     * 
     * @since 2012. 2. 6.
     */
    public static <A extends Annotation> A hasAnnotation(Object object, Class<A> annoClass) {
        AssertUtils2.notNulls(object, annoClass);

        return object.getClass().getAnnotation(annoClass);
    }

    /**
     * 주어진 객체가 {@link Annotation}의 sub-class 인지 여부를 반환합니다.
     * 
     * @param object
     * @return
     * 
     * @since 2012. 02. 13.
     * 
     * @see Class#isAnnotation()
     */
    public static boolean isAnnotation(Object object) {
        Objects.requireNonNull(object);

        return object.getClass().isAnnotation();
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 5. 29.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param type
     *            {@link Annotation}이 존재하는지 확인하고자 하는 타입
     * @param annoClass
     *            {@link Annotation} 객체
     * @return
     * 
     * @since 2019. 5. 29.
     */
    public static <A extends Annotation> boolean isAnnotationPresent(Class<?> type, Class<A> annoClass) {
        AssertUtils2.notNulls(type, annoClass);

        return type.isAnnotationPresent(annoClass);
    }

    /**
     * 주어진 객체에 {@link Annotation}이 존재하는지 여부를 반환합니다.
     * 
     * @param obj
     *            {@link Annotation}이 존재하는지 확인하고자 하는 객체
     * @param annoClass
     *            {@link Annotation} 객체
     * @return
     * 
     * @since 2012. 02. 13.
     */
    public static <A extends Annotation> boolean isAnnotationPresent(Object obj, Class<A> annoClass) {
        AssertUtils2.notNulls(obj, annoClass);

        return obj.getClass().isAnnotationPresent(annoClass);
    }
}
