/*
 * Copyright 2019 Park Jun-Hong_(fafanmama_at_naver_com)
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
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package open.commons.xml;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.function.Function;

import open.commons.utils.AnnotationUtils;
import open.commons.utils.AssertUtils;
import open.commons.utils.CollectionUtils;

/**
 * SAX Element TEXT Data Converter
 * 
 * @since 2019. 1. 24.
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
public class SaxTextConverter {

    /**
     * key: Full Qualified class name.<br>
     * value: converter
     * 
     * @see Class#getName()
     */
    private final ConcurrentSkipListMap<String, Function<String, ?>> CONVERTERS = new ConcurrentSkipListMap<>();

    // default converter
    {

        registerConverter(String.class.getName(), s -> s);

        // primitive type
        registerConverter(char.class.getName(), s -> s.charAt(0));
        registerConverter(byte.class.getName(), s -> s.getBytes()[0]);
        registerConverter(boolean.class.getName(), s -> Boolean.parseBoolean(s));
        registerConverter(int.class.getName(), s -> Integer.parseInt(s));
        registerConverter(long.class.getName(), s -> Long.parseLong(s));
        registerConverter(float.class.getName(), s -> Float.parseFloat(s));
        registerConverter(double.class.getName(), s -> Double.parseDouble(s));

        // primitive wrapper class
        registerConverter(Character.class.getName(), s -> new Character(s.charAt(0)));
        registerConverter(Byte.class.getName(), s -> s.getBytes()[0]);
        registerConverter(Boolean.class.getName(), s -> Boolean.parseBoolean(s));
        registerConverter(Integer.class.getName(), s -> Integer.parseInt(s));
        registerConverter(Long.class.getName(), s -> Long.parseLong(s));
        registerConverter(Float.class.getName(), s -> Float.parseFloat(s));
        registerConverter(Double.class.getName(), s -> Double.parseDouble(s));
    }

    public SaxTextConverter() {
    }

    /**
     * SAX Element의 정보를 주어진 객체에 추가한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 1. 24.     박준홍         최초 작성
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
     *
     * @since 2019. 1. 24.
     */
    public void convert(Object obj, String saxElement, String saxText) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        // #1. 해당 Object의 Annotated 메소드
        Map<String, Method> methods = CollectionUtils.toMapHSV(AnnotationUtils.getAnnotatedMethodsAll(obj, SaxElement.class), m -> m.getAnnotation(SaxElement.class).value(),
                m -> m);

        // #2. SAX Element에 해당하는 메소드
        Method m = methods.get(saxElement);

        if (m == null) {
            throw new UnsupportedOperationException(String.format("Object: %s, sax-element: %s, sax-text: %s", obj.getClass().getName(), saxElement, saxText));
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
     * 데이터 타입을 위한 변환기를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 1. 24.     박준홍         최초 작성
     * </pre>
     *
     * @param fqcn
     *            변환 결과 타입. Full Qualified Class Name.
     * @return
     *
     * @since 2019. 1. 24.
     */
    @SuppressWarnings("unchecked")
    public <R> Function<String, R> getConverter(String fqcn) {
        AssertUtils.assertNull("Full Qualified Class Name MUST NOT BE NULL !!!", fqcn, NullPointerException.class);

        Function<String, R> c = (Function<String, R>) CONVERTERS.get(fqcn);

        AssertUtils.assertNull("No converter for " + fqcn, c, NullPointerException.class);

        return c;
    }

    /**
     * 데이타 타입에 따른 변환기를 등록한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 1. 24.     박준홍         최초 작성
     * </pre>
     *
     * @param fqcn
     *            Full Qualified Class Name
     * @param converter
     * @return
     *
     * @since 2019. 1. 24.
     * 
     * @see Class#getName()
     */
    @SuppressWarnings("unchecked")
    public <R> Function<String, R> registerConverter(String fqcn, Function<String, R> converter) {
        AssertUtils.assertNulls("Class<?> or Converter MUST NOT BE NULL !!!", NullPointerException.class, fqcn, converter);

        return (Function<String, R>) CONVERTERS.put(fqcn, converter);
    }

}
