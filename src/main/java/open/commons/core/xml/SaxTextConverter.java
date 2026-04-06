/*
 * Copyright 2019 Park Jun-Hong (parkjunhong77@gmail.com)
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
 * This file is generated under this project, "open-commons-core".
 *
 * Date  : 2019. 1. 29. 오전 11:37:25
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.core.xml;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;

import org.jspecify.annotations.Nullable;

import open.commons.core.utils.AnnotationUtils;
import open.commons.core.utils.CollectionUtils;

/**
 * SAX Element TEXT Data Converter
 *
 * <pre>
 * [개정이력]
 * 날짜        | 작성자                    | 내용
 * ----------------------------------------------------------------------
 * 2019. 1. 24.      parkjunhong77@gmail.com     최초 작성
 * 2026. 4. 6.       parkjunhong77@gmail.com     (3.0.0) Byte 파싱 논리 버그 수정, 리플렉션 캐싱(O(1)) 도입 및 ConcurrentHashMap 적용
 * </pre>
 *
 * @since 2019. 1. 24.
 */
// 아래 내용에 적용됨.
// - 대부분의 JDK 표준 API
// [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
// [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
@SuppressWarnings("null")
public class SaxTextConverter {

    /**
     * 리플렉션 스캔 오버헤드를 제거하기 위한 정적 메소드 캐시. (Zero-Allocation 최적화)
     * <li>키: 클래스 타입
     * <li>값: 해당 클래스의 SaxElement 어노테이션 메소드 맵
     */
    private static final ConcurrentMap<Class<?>, Map<String, Method>> ANNOTATED_METHOD_CACHE = new ConcurrentHashMap<>();

    /**
     * <li>키: Full Qualified class name.<br>
     * <li>값: converter
     *
     * @see Class#getName()
     */
    // [PATCH] O(log N) 인 SkipListMap 대신 정렬이 불필요하므로 O(1) 인 ConcurrentHashMap 사용
    private final ConcurrentMap<String, Function<String, ?>> CONVERTERS = new ConcurrentHashMap<>();

    // default converter
    {
        registerConverter(String.class.getName(), s -> s);

        // primitive type
        registerConverter(char.class.getName(), s -> s.charAt(0));
        registerConverter(byte.class.getName(), Byte::parseByte);
        registerConverter(boolean.class.getName(), Boolean::parseBoolean);
        registerConverter(int.class.getName(), Integer::parseInt);
        registerConverter(long.class.getName(), Long::parseLong);
        registerConverter(float.class.getName(), Float::parseFloat);
        registerConverter(double.class.getName(), Double::parseDouble);

        // primitive wrapper class
        registerConverter(Character.class.getName(), s -> s.charAt(0));
        registerConverter(Byte.class.getName(), Byte::valueOf);
        registerConverter(Boolean.class.getName(), Boolean::valueOf);
        registerConverter(Integer.class.getName(), Integer::valueOf);
        registerConverter(Long.class.getName(), Long::valueOf);
        registerConverter(Float.class.getName(), Float::valueOf);
        registerConverter(Double.class.getName(), Double::valueOf);
    }

    public SaxTextConverter() {
    }

    /**
     * SAX Element의 정보를 주어진 객체에 추가합니다. <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2019. 1. 24.      parkjunhong77@gmail.com     최초 작성
     * 2026. 4. 6.       parkjunhong77@gmail.com     (3.0.0) 리플렉션 병목 제거를 위한 정적 캐싱 로직 도입 및 Null 가드 클로즈 추가
     * </pre>
     *
     * @param obj
     *            SAX Element 문자열 값을 받는 객체.
     * @param saxElement
     *            SAX Element 이름.
     * @param saxText
     *            SAX Element 문자열 값
     *
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws NullPointerException
     *             파라미터({@code obj}, {@code saxElement} 중에 1개라도)가 {@code null}인 경우 발생.
     *
     * @since 2019. 1. 24.
     * @version 3.0.0
     */
    public void convert(Object obj, String saxElement, @Nullable String saxText) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Objects.requireNonNull(obj);
        Objects.requireNonNull(saxElement);

        // #1. 해당 Object의 Annotated 메소드 (캐싱을 통해 SAX 파싱의 리플렉션 병목 완벽 제거)
        Map<String, Method> methods = ANNOTATED_METHOD_CACHE.computeIfAbsent(obj.getClass() //
                , clazz -> CollectionUtils.toMapHSV(AnnotationUtils.getAnnotatedMethodsAll(clazz, SaxElement.class), m -> m.getAnnotation(SaxElement.class).value(), m -> m));

        // #2. SAX Element에 해당하는 메소드
        Method m = methods.get(saxElement);

        if (saxText != null && !saxText.isBlank() && m == null) {
            throw new UnsupportedOperationException(String.format("Object: %s, sax-element: %s, sax-text: %s", obj.getClass().getName(), saxElement, saxText));
        } else if (m == null) {
            return;
        }

        // #3. 데이타 변환
        String fqcn = m.getParameters()[0].getType().getName();
        Function<String, ?> converter = this.CONVERTERS.get(fqcn);

        if (converter == null) {
            throw new UnsupportedOperationException(String.format("NO CONVERTER !!! Object: %s, sax-element: %s, sax-text: %s", obj.getClass().getName(), saxElement, saxText));
        }

        m.invoke(obj, converter.apply(saxText));
    }

    /**
     * 데이터 타입을 위한 변환기를 제공합니다. <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2019. 1. 24.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param fqcn
     *            변환 결과 타입. Full Qualified Class Name.
     *
     * @return 등록된 변환기 함수 객체
     *
     * @throws NullPointerException
     *             파라미터({@code fqcn})가 {@code null}인 경우 발생하거나, 해당 클래스명에 등록된 변환기가 없는 경우 발생.
     *
     * @since 2019. 1. 24.
     */
    @SuppressWarnings("unchecked")
    public <R> Function<String, R> getConverter(String fqcn) {
        Objects.requireNonNull(fqcn, "Full Qualified Class Name MUST NOT BE NULL !!!");

        Function<String, R> c = (Function<String, R>) CONVERTERS.get(fqcn);

        Objects.requireNonNull(c, "No converter for " + fqcn);

        return c;
    }

    /**
     * 데이타 타입에 따른 변환기를 등록합니다. <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2019. 1. 24.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param fqcn
     *            Full Qualified Class Name
     * @param converter
     *            등록할 변환기 함수
     *
     * @return 이전에 등록되어 있던 변환기. (없을 경우 {@code null})
     *
     * @throws NullPointerException
     *             파라미터({@code fqcn}, {@code converter} 중에 1개라도)가 {@code null}인 경우 발생.
     *
     * @since 2019. 1. 24.
     *
     * @see Class#getName()
     */
    @SuppressWarnings("unchecked")
    public <R> @Nullable Function<String, R> registerConverter(String fqcn, Function<String, R> converter) {
        Objects.requireNonNull(fqcn, "Class<?> MUST NOT BE NULL !!!");
        Objects.requireNonNull(converter, "Converter MUST NOT BE NULL !!!");

        return (Function<String, R>) CONVERTERS.put(fqcn, converter);
    }

}