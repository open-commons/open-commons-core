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
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Nonnull;

/**
 * 
 * <BR>
 * 
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
     * 2020. 11. 9.		박준홍			최초 작성
     * </pre>
     *
     * @param accObj
     * @param annoClasses
     *            어노테이션 타입.
     * @return
     *
     * @since 2020. 11. 9.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @see AccessibleObject#isAnnotationPresent(Class)
     */
    @SafeVarargs
    public static boolean existAllAnnotations(@Nonnull AccessibleObject accObj, Class<? extends Annotation>... annoClasses) {
        AssertUtils2.notNull("대상 객체는 'null'일 수 없습니다", accObj);

        for (Class<? extends Annotation> annoClass : annoClasses) {
            if (!accObj.isAnnotationPresent(annoClass)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 대상 클래스의 {@link Field} 중에서 특정 {@link Annotation}이 있는 {@link Field}만 제공합니다. <br>
     * 
     * {@link Field}는 해당 클래스에서만 정의한 'public, protected, default(package), private' 를 포함하지만, 상위 클래스에서 정의한 {@link Field}를
     * 포함하지 않습니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 5. 29.		박준홍			최초 작성
     * </pre>
     *
     * @param typeClass
     * @param annotationClass
     * @return
     *
     * @since 2019. 5. 29.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @see Class#getDeclaredFields()
     * @see AccessibleObject#isAnnotationPresent(Class)
     */
    public static List<Field> getAnnotatedFields(@Nonnull Class<?> typeClass, @Nonnull Class<? extends Annotation> annotationClass) {
        AssertUtils2.notNull("클래스는 'null'일 수 없습니다", typeClass);
        AssertUtils2.notNull("어노테이션 클래스는 'null'일 수 없습니다", annotationClass);

        ArrayList<Field> fields = new ArrayList<>();

        Arrays.stream(typeClass.getDeclaredFields()) // create fields stream
                .forEach(f -> {
                    boolean accessible = f.isAccessible();
                    try {
                        f.setAccessible(true);
                        if (f.isAnnotationPresent(annotationClass)) {
                            fields.add(f);
                        }
                    } catch (Throwable ignored) {
                        // ignored
                    } finally {
                        f.setAccessible(accessible);
                    }
                });

        return fields;
    }

    /**
     * 대상 클래스의 {@link Field} 중에서 특정 {@link Annotation}이 있는 {@link Field}만 제공합니다. <br>
     * 
     * {@link Field}는 해당 클래스에서만 정의한 'public, protected, default(package), private' 를 포함하지만, 상위 클래스에서 정의한 {@link Field}를
     * 포함하지 않습니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2012. 2. 6.		박준홍			최초 작성
     * </pre>
     *
     * @param object
     * @param annotationClass
     * @return
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2012. 2. 6.
     * 
     * @see #getAnnotatedFields(Class, Class)
     */
    public static List<Field> getAnnotatedFields(Object object, Class<? extends Annotation> annotationClass) {
        return getAnnotatedFields(object.getClass(), annotationClass);
    }

    /**
     * 대상 클래스의 {@link Field} 중에서 특정 {@link Annotation}이 있는 {@link Field}만 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 5. 29.		박준홍			최초 작성
     * </pre>
     *
     * @param typeClass
     * @param annotationClass
     * @return
     *
     * @since 2019. 5. 29.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @see Class#getDeclaredFields()
     * @see AccessibleObject#isAnnotationPresent(Class)
     */
    public static List<Field> getAnnotatedFieldsAll(Class<?> typeClass, Class<? extends Annotation> annotationClass) {
        return Arrays.stream(typeClass.getDeclaredFields()) // create fields stream
                .filter(f -> f.isAnnotationPresent(annotationClass)) // check annotation
                .collect(Collectors.toList());
    }

    /**
     * 대상 클래스의 {@link Field} 중에서 특정 {@link Annotation}이 있는 {@link Field}만 제공합니다. <br>
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 11. 9.		박준홍			최초 작성
     * </pre>
     *
     * @param typeClass
     * @param annoClasses
     * @return
     *
     * @since 2020. 11. 9.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @see Class#getDeclaredFields()
     * @see #existAllAnnotations(AccessibleObject, Class...)
     */
    @SafeVarargs
    public static List<Field> getAnnotatedFieldsAll(Class<?> typeClass, Class<? extends Annotation>... annoClasses) {
        return Arrays.stream(typeClass.getDeclaredFields()) // create fields stream
                .filter(f -> existAllAnnotations(f, annoClasses)) // check annotation
                .collect(Collectors.toList());
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2017. 9. 26.		박준홍			최초 작성
     * </pre>
     *
     * @param object
     * @param annotationClass
     * @return
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2017. 9. 26.
     * 
     * @see Class#getFields()
     */
    public static List<Field> getAnnotatedFieldsAll(Object object, Class<? extends Annotation> annotationClass) {
        return getAnnotatedFieldsAll(object.getClass(), annotationClass);
    }

    /**
     * 대상 클래스의 {@link Field} 중에서 특정 {@link Annotation}이 있는 {@link Field}만 제공합니다. <br>
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 11. 9.		박준홍			최초 작성
     * </pre>
     *
     * @param typeClass
     * @param annotationClass
     * @return
     *
     * @since 2020. 11. 9.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @see Class#getDeclaredFields()
     * @see AccessibleObject#isAnnotationPresent(Class)
     */
    public static Stream<Field> getAnnotatedFieldsAllAsStream(Class<?> typeClass, Class<? extends Annotation> annotationClass) {
        return Arrays.stream(typeClass.getDeclaredFields()) // create fields stream
                .filter(f -> f.isAnnotationPresent(annotationClass)) // check annotation
        ;
    }

    /**
     * 대상 클래스의 {@link Field} 중에서 특정 {@link Annotation}이 있는 {@link Field}만 제공합니다. <br>
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 11. 9.		박준홍			최초 작성
     * </pre>
     *
     * @param typeClass
     * @param annoClasses
     * @return
     *
     * @since 2020. 11. 9.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @see Class#getDeclaredFields()
     * @see #existAllAnnotations(AccessibleObject, Class...)
     */
    @SafeVarargs
    public static Stream<Field> getAnnotatedFieldsAllAsStream(Class<?> typeClass, Class<? extends Annotation>... annoClasses) {
        return Arrays.stream(typeClass.getDeclaredFields()) // create fields stream
                .filter(f -> existAllAnnotations(f, annoClasses)) // check annotation
        ;
    }

    /**
     * 대상 클래스의 {@link Field} 중에서 특정 {@link Annotation}이 있는 {@link Field}만 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 3.		박준홍			최초 작성
     * </pre>
     *
     * @param object
     * @param annotationClass
     * @return
     *
     * @since 2021. 11. 3.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static Stream<Field> getAnnotatedFieldsAllAsStream(Object object, Class<? extends Annotation> annotationClass) {
        return getAnnotatedFieldsAllAsStream(object.getClass(), annotationClass);
    }

    /**
     * 대상 클래스에 정의된 {@link Field}와 상위 클래스에 정의된 {@link Field}까지 제공합니다. <br>
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 9. 2.		박준홍			최초 작성
     * </pre>
     *
     * @param typeClass
     * @param annoClasses
     * @return
     *
     * @since 2022. 9. 2.
     * @version 2.0.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    @SafeVarargs
    public static List<Field> getAnnotatedFieldsAllHierarchy(Class<?> typeClass, Class<? extends Annotation>... annoClasses) {

        Class<?> type = typeClass;

        Set<Field> fields = new HashSet<>();
        while (type != null && !type.equals(Object.class)) {
            fields.addAll(getAnnotatedFieldsAll(type, annoClasses));
            type = type.getSuperclass();
        }

        return new ArrayList<>(fields);
    }

    /**
     * 대상 객체에 정의된 {@link Field}와 상위 클래스에 정의된 {@link Field}까지 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 9. 2.		박준홍			최초 작성
     * </pre>
     *
     * @param obj
     * @param annoClasses
     * @return
     *
     * @since 2022. 9. 2.
     * @version 2.0.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    @SafeVarargs
    public static List<Field> getAnnotatedFieldsAllHierarchy(Object obj, Class<? extends Annotation>... annoClasses) {
        return getAnnotatedFieldsAllHierarchy(obj.getClass(), annoClasses);
    }

    /**
     * 대상 클래스의 "public" {@link Method} 중에서 특정 {@link Annotation}이 있는 {@link Method}만 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 5. 29.		박준홍			최초 작성
     * </pre>
     *
     * @param typeClass
     * @param annotationClass
     * @return
     *
     * @since 2019. 5. 29.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @see Class#getDeclaredMethods()
     */
    public static List<Method> getAnnotatedMethods(Class<?> typeClass, Class<? extends Annotation> annotationClass) {
        ArrayList<Method> methods = new ArrayList<>();

        Arrays.stream(typeClass.getDeclaredMethods()) // create methods stream
                .forEach(m -> {
                    boolean accessible = false;
                    try {
                        accessible = m.isAccessible();

                        if (m.isAnnotationPresent(annotationClass)) {
                            methods.add(m);
                        }
                    } catch (Throwable ignored) {
                        // ignored
                    } finally {
                        m.setAccessible(accessible);
                    }
                });

        return methods;
    }

    /**
     * 대상 객체 클래스의 "public" {@link Method} 중에서 특정 {@link Annotation}이 있는 {@link Method}만 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2012. 2. 6.		박준홍			최초 작성
     * </pre>
     *
     * @param object
     * @param annotationClass
     * @return
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2012. 2. 6.
     * 
     * @see Class#getDeclaredMethods()
     */
    public static List<Method> getAnnotatedMethods(Object object, Class<? extends Annotation> annotationClass) {
        return getAnnotatedMethods(object.getClass(), annotationClass);
    }

    /**
     * 대상 클래스의 "Public" 메소드 중에서 특정 어노테이션이 있는 메소드만 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 5. 29.		박준홍			최초 작성
     * </pre>
     *
     * @param typeClass
     * @param annotationClass
     * @return
     *
     * @since 2019. 5. 29.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @see Class#getMethods()
     */
    public static List<Method> getAnnotatedMethodsAll(Class<?> typeClass, Class<? extends Annotation> annotationClass) {
        return Arrays.stream(typeClass.getMethods()) // create methods stream
                .filter(m -> m.isAnnotationPresent(annotationClass)) // check annotation
                .collect(Collectors.toList());
    }

    /**
     * 대상 클래스의 "Public" 메소드 중에서 특정 어노테이션이 있는 메소드만 제공합니다. <br>
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 11. 9.		박준홍			최초 작성
     * </pre>
     *
     * @param typeClass
     * @param annoClasses
     * @return
     *
     * @since 2020. 11. 9.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @see Class#getMethods()
     * @see #existAllAnnotations(AccessibleObject, Class...)
     */
    @SafeVarargs
    public static List<Method> getAnnotatedMethodsAll(Class<?> typeClass, Class<? extends Annotation>... annoClasses) {
        return Arrays.stream(typeClass.getMethods()) // create methods stream
                .filter(m -> existAllAnnotations(m, annoClasses)) // check annotation
                .collect(Collectors.toList());
    }

    /**
     * 대상 객체 클래스의 "Public" 메소드 중에서 특정 어노테이션이 있는 메소드만 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2017. 9. 26.		박준홍			최초 작성
     * </pre>
     *
     * @param object
     * @param annotationClass
     * @return
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2017. 9. 26.
     * 
     * @see Class#getMethods()
     */
    public static List<Method> getAnnotatedMethodsAll(Object object, Class<? extends Annotation> annotationClass) {
        return getAnnotatedMethodsAll(object.getClass(), annotationClass);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 11. 9.		박준홍			최초 작성
     * </pre>
     *
     * @param object
     * @param annotationClass
     * @return
     *
     * @since 2020. 11. 9.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @see Class#getMethods()
     */
    @SafeVarargs
    public static List<Method> getAnnotatedMethodsAll(Object object, Class<? extends Annotation>... annoClasses) {
        return getAnnotatedMethodsAll(object.getClass(), annoClasses);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 11. 9.		박준홍			최초 작성
     * </pre>
     *
     * @param typeClass
     * @param annoClasses
     * @return
     *
     * @since 2020. 11. 9.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @see Class#getDeclaredFields()
     * @see #existAllAnnotations(AccessibleObject, Class...)
     */
    @SafeVarargs
    public static Stream<Method> getAnnotatedMethodsAllAsStream(Class<?> typeClass, Class<? extends Annotation>... annoClasses) {
        return Arrays.stream(typeClass.getMethods()) // create methods stream
                .filter(m -> existAllAnnotations(m, annoClasses)) // check annotation
        ;
    }

    /**
     * 대상 클래스의 "Public" 메소드 중에서 특정 어노테이션이 있는 메소드만 제공합니다. <br>
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 11. 9.		박준홍			최초 작성
     * </pre>
     *
     * @param typeClass
     * @param annotationClass
     * @return
     *
     * @since 2020. 11. 9.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static Stream<Method> getAnnotatedMethodsAllAsStream(Class<?> typeClass, Class<? extends Annotation> annotationClass) {
        return Arrays.stream(typeClass.getMethods()) // create methods stream
                .filter(m -> m.isAnnotationPresent(annotationClass)) // check annotation
        ;
    }

    /**
     * 상위 클래스에서 정의된 메소드까지 확인합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 11. 9.		박준홍			최초 작성
     * </pre>
     *
     * @param <T>
     * @param object
     * @param annotationClass
     * @return
     *
     * @since 2020. 11. 9.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    @SafeVarargs
    public static List<Method> getAnnotatedMethodsAllHierarchy(Class<?> typeClass, Class<? extends Annotation>... annoClasses) {

        Class<?> type = typeClass;

        Set<Method> methods = new HashSet<>();
        while (type != null && !type.equals(Object.class)) {
            methods.addAll(getAnnotatedMethodsAll(type, annoClasses));
            type = type.getSuperclass();
        }

        return new ArrayList<>(methods);
    }

    /**
     * 상위 클래스에서 정의된 메소드까지 확인합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 11. 9.		박준홍			최초 작성
     * </pre>
     *
     * @param <T>
     * @param object
     * @param annotationClass
     * @return
     *
     * @since 2020. 11. 9.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @see #getAnnotatedMethodsAllHierarchy(Class, Class...)
     */
    @SafeVarargs
    public static List<Method> getAnnotatedMethodsAllHierarchy(Object object, Class<? extends Annotation>... annoClasses) {
        return getAnnotatedMethodsAllHierarchy(object.getClass(), annoClasses);
    }

    /**
     * 객체에서 어노테이션 타입에 맞는 객체를 반환합니다.
     * 
     * @param accessObj
     * @param annotationClass
     * @return
     */
    public static <T extends Annotation> T getAnnotation(AccessibleObject accessObj, Class<T> annotationClass) {
        T annotation = null;

        try {
            if (accessObj != null //
                    && accessObj.isAnnotationPresent(annotationClass)) {
                annotation = accessObj.getAnnotation(annotationClass);
            }
        } catch (Exception e) {
        }

        return annotation;
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2012. 2. 6.		박준홍			최초 작성
     * </pre>
     *
     * @param clazz
     * @param annotationClass
     * @return
     *
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @since 2012. 2. 6.
     */
    public static <T extends Annotation> T getAnnotation(Class<?> clazz, Class<T> annotationClass) {
        T annotation = null;

        try {
            if (clazz != null //
                    && clazz.isAnnotationPresent(annotationClass)) {
                annotation = clazz.getAnnotation(annotationClass);
            }
        } catch (Exception e) {
        }

        return annotation;
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
            Object value = field.get(instance);
            if (value != null) {
                rtnValue = value.toString();
            }

        } catch (IllegalArgumentException e) {
        } catch (IllegalAccessException e) {
        }
        return rtnValue;
    }

    /**
     * 
     * 주어진 객체에 찾고자 하는 {@link Annotation}이 존재하는 경우 {@link Annotation}객체를 반환하고 없는 경우 <code>null</code>을 반환합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 5. 29.		박준홍			최초 작성
     * </pre>
     *
     * @param obj
     * @param annotationClass
     *            찾고자 하는 {@link Annotation} 타입
     * @return {@link AnnotationFormatError} 또는 <code>null</code>
     *
     * @since 2019. 5. 29.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <A extends Annotation> A hasAnnotation(Class<?> type, Class<A> annotationClass) {
        return type.getAnnotation(annotationClass);
    }

    /**
     * 주어진 객체에 찾고자 하는 {@link Annotation}이 존재하는 경우 {@link Annotation}객체를 반환하고 없는 경우 <code>null</code>을 반환합니다.
     * 
     * @param obj
     * @param annotationClass
     *            찾고자 하는 {@link Annotation} 타입
     * @return {@link AnnotationFormatError} 또는 <code>null</code>
     * @since 2012. 2. 6.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @throws NullPointerException
     */
    public static <A extends Annotation> A hasAnnotation(Object obj, Class<A> annotationClass) {
        return obj.getClass().getAnnotation(annotationClass);
    }

    /**
     * 주어진 객체가 {@link Annotation}의 sub-class 인지 여부를 반환합니다.
     * 
     * @param obj
     * @return
     * @throws NullPointerException
     *             주어진 객체가 <code>null</code>인 경우
     * 
     * @since 2012. 02. 13.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see Class#isAnnotation()
     */
    public static boolean isAnnotation(Object obj) {
        return obj.getClass().isAnnotation();
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 5. 29.		박준홍			최초 작성
     * </pre>
     *
     * @param type
     *            {@link Annotation}이 존재하는지 확인하고자 하는 타입
     * @param annotationClass
     *            {@link Annotation} 객체
     * @return
     * @throws NullPointerException
     *             주어진 객체나, Annotation 타입이 <code>null</code>인 경우
     * 
     * @return
     *
     * @since 2019. 5. 29.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <A extends Annotation> boolean isAnnotationPresent(Class<?> type, Class<A> annotationClass) {
        return type.isAnnotationPresent(annotationClass);
    }

    /**
     * 주어진 객체에 {@link Annotation}이 존재하는지 여부를 반환합니다.
     * 
     * @param obj
     *            {@link Annotation}이 존재하는지 확인하고자 하는 객체
     * @param annotationClass
     *            {@link Annotation} 객체
     * @return
     * @throws NullPointerException
     *             주어진 객체나, Annotation 타입이 <code>null</code>인 경우
     * 
     * @since 2012. 02. 13.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <A extends Annotation> boolean isAnnotationPresent(Object obj, Class<A> annotationClass) {
        return obj.getClass().isAnnotationPresent(annotationClass);
    }
}
