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
 * Date  : 2019. 1. 29. 오전 11:38:15
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.core.xml;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import open.commons.core.utils.AssertUtils2;

/**
 * SAX 파싱을 위한 추상 핸들러 클래스입니다.
 *
 * <pre>
 * [개정이력]
 * 날짜        | 작성자                    | 내용
 * ----------------------------------------------------------------------
 * 2019. 1. 25.      parkjunhong77@gmail.com     최초 작성
 * 2019. 10. 17.     parkjunhong77@gmail.com     Logger 교체. (log4j &rarr; slf4j)
 * 2026. 4. 6.       parkjunhong77@gmail.com     (3.0.0) 레거시 Stack 제거(ArrayDeque 교체) 및 characters 더블 할당(O(N)) 최적화
 * </pre>
 *
 * @since 2019. 1. 25.
 */
public abstract class AbstractSAXHandler extends DefaultHandler {

    @SuppressWarnings("null")
    protected Logger logger = LoggerFactory.getLogger(getClass());
    @SuppressWarnings("null")
    protected Logger errorLogger = LoggerFactory.getLogger(getClass());

    private boolean ignoredInvalidValue;

    private String indentStr = "...";
    private int indent = 1;

    /** SAX Element TEXT 데이타 변환기 */
    private final SaxTextConverter converter = new SaxTextConverter();

    /** Element 이름 (레거시 Stack 대신 모던 ArrayDeque 적용) */
    private final Deque<String> qnames = new ArrayDeque<>();
    /** 데이터로 사용되는 Element 이름 */
    private final Set<String> dataQNames = new HashSet<>();
    /** Element Object */
    private final Deque<Object> elemObjects = new ArrayDeque<>();

    /**
     * 객체를 생성합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2019. 1. 29.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param ignoredInvalidValue
     *            잘못된 값 처리 무시 여부
     *
     * @since 2019. 1. 29.
     */
    public AbstractSAXHandler(boolean ignoredInvalidValue) {
        this(null, null, ignoredInvalidValue);
    }

    /**
     * 객체를 생성합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2019. 1. 25.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param logger
     *            일반 로그
     * @param errorLogger
     *            에러 로그
     *
     * @since 2019. 1. 25.
     */
    public AbstractSAXHandler(@Nullable Logger logger, @Nullable Logger errorLogger) {
        this(logger, errorLogger, false);
    }

    /**
     * 객체를 생성합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2019. 1. 29.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param logger
     *            일반 로그
     * @param errorLogger
     *            에러 로그
     * @param ignoredInvalidValue
     *            잘못된 값 처리 무시 여부
     *
     * @since 2019. 1. 29.
     */
    public AbstractSAXHandler(@Nullable Logger logger, @Nullable Logger errorLogger, boolean ignoredInvalidValue) {
        if (logger != null) {
            this.logger = logger;
        }
        if (errorLogger != null) {
            this.errorLogger = errorLogger;
        }
        this.ignoredInvalidValue = ignoredInvalidValue;

        // #1. Register a converter.
        registerDataConverters(this.converter);

        // #2. Register a name of data element
        registerDataQNames(this.dataQNames);
    }

    /**
     * qName에 해당하는 객체를 저장합니다. <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2019. 1. 25.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param object
     *            대상 객체
     *
     * @return 추가 여부 (항상 {@code true})
     *
     * @since 2019. 1. 25.
     */
    protected final boolean addElementObject(Object object) {
        Objects.requireNonNull(object, "Element 객체는 null일 수 없습니다.");
        return elemObjects.add(object);
    }

    /**
     * 
     * {@inheritDoc}
     *
     * @since 2026. 4. 7.
     * @version 3.0.0
     *
     * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
     */
    // 아래 내용에 적용됨.
    // - char[] ch
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        Objects.requireNonNull(ch);

        String currentQName = getCurrentQName();

        if (dataQNames.contains(currentQName)) {
            // [PATCH] 불필요한 배열 복사 방지를 위해 생성자에 직접 파라미터 전달 (Zero-Allocation)
            logger.debug("{} [ELEMENT::characters] start: {}, length: {}, value: {}", indentation(), start, length, new String(ch, start, length));
        } else {
            // 데이터를 갖지 않는 Element는 패스.
            return;
        }

        String strValue = null;
        Object parentObj = null;

        try {
            // #0. SAX Element's TEXT value (Zero-Allocation)
            strValue = new String(ch, start, length);
            // #1. 상위 Element 이름 및 객체
            parentObj = getParentObject(currentQName);
            // #2. TEXT value 를 설정
            this.converter.convert(parentObj, currentQName, strValue);
        } catch (NumberFormatException ignored) {
            if (!this.ignoredInvalidValue) {
                this.errorLogger.warn("잘못된 형식의 데이타 수신. value: {}, QName: {}, ParentObj: {}, cause: {}", strValue, currentQName, parentObj, ignored.getMessage());
            }
        } catch (IllegalAccessException | InvocationTargetException | RuntimeException e) {
            String errorMsg = String.format("value: %s, QName: %s, ParentObj: %s, cause: %s", strValue, currentQName, parentObj, e.getMessage());

            this.errorLogger.error(errorMsg, e);
            throw new SAXException(errorMsg, e);
        }

        if (this.logger.isDebugEnabled()) {
            this.logger.debug("{}(Object) {}.{} = {}", indentation(), parentObj != null ? parentObj.getClass().getSimpleName() : null, currentQName, strValue);
        }
    }

    protected final void decIndentation() {
        this.indent--;
    }

    /**
     */
    // 아래 내용에 적용됨.
    // - public final void endElement(String uri, String localName, String qName) throws SAXException {
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    @Override
    public final void endElement(String uri, String localName, String qName) throws SAXException {
        AssertUtils2.notNulls(uri, localName, qName);

        // #1. Decrease an indentation.
        decIndentation();

        if (this.logger.isDebugEnabled()) {
            this.logger.debug("{}[ELEMENT::end] qName: {}, uri: {}, localName: {}", indentation(), qName, uri, localName);
            this.logger.debug("{}<<<<<<<<<< end of '{}'", indentation(), qName);
        }

        // #2. Handle a parsed data.
        endElement0(uri, localName, qName);

        // #3. Remove a name of an element parsed.
        this.qnames.poll();
    }

    /**
     * 요소(Element) 종료 시 수행할 구체적인 로직을 정의합니다. <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2019. 1. 25.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param uri
     *            네임스페이스 URI. 요소에 네임스페이스 URI가 없거나 네임스페이스 처리를 수행하지 않는 경우 빈 문자열({@code ""})이 전달됩니다.
     * @param localName
     *            로컬 이름 (접두사 제외). 네임스페이스 처리를 수행하지 않는 경우 빈 문자열({@code ""})이 전달됩니다.
     * @param qName
     *            정규화된 이름 (접두사 포함). 정규화된 이름을 사용할 수 없는 경우 빈 문자열({@code ""})이 전달됩니다.
     *
     * @throws SAXException
     *             SAX 처리 중 발생하는 예외. 내부적으로 다른 예외를 래핑(wrapping)할 수 있습니다.
     *
     * @since 2019. 1. 25.
     *
     * @see org.xml.sax.ContentHandler#endElement
     * @see #endElement(String, String, String)
     */
    public abstract void endElement0(String uri, String localName, String qName) throws SAXException;

    /**
     * 현재 파싱 중인 Element의 QName을 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2019. 1. 25.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @return 덱(Deque)의 최상위 객체.
     * 
     * @throws java.util.NoSuchElementException
     *             XML 파싱 컨텍스트 외부에서 호출되어 스택이 비어있는 경우
     * 
     * @since 2019. 1. 25.
     */
    // 아래 내용에 적용됨.
    // - this.qnames.getFirst()
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    protected String getCurrentQName() {
        return this.qnames.getFirst();
    }

    /**
     * Parent Element 에 해당하는 객체를 제공합니다. 기본적으로 Element 객체 스택의 최상위 객체를 제공합니다.<br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2019. 1. 25.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param qName
     *            현재 Element 이름
     *
     * @return 부모 요소 객체
     * 
     * @throws java.util.NoSuchElementException
     *             XML 파싱 컨텍스트 외부에서 호출되어 스택이 비어있는 경우
     *
     * @since 2019. 1. 25.
     */
    // 아래 내용에 적용됨.
    // - this.elemObjects.getFirst()
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    protected Object getParentObject(String qName) {
        return this.elemObjects.getFirst();
    }

    protected final void incIndentation() {
        this.indent++;
    }

    /**
     * 들여쓰기로 사용할 문자열을 반환합니다. <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2019. 1. 25.      parkjunhong77@gmail.com     최초 작성
     * 2026. 4. 6.       parkjunhong77@gmail.com     (3.0.0) 레거시 StringBuffer 제거 및 repeat 적용
     * </pre>
     *
     * @return 들여쓰기 문자열
     *
     * @since 2019. 1. 25.
     */
    protected final String indentation() {
        // [PATCH] StringBuffer 대신 JDK 11+의 최적화된 문자열 반복 처리 활용
        return this.indentStr.repeat(this.indent) + " ";
    }

    /**
     * Element에 해당하는 객체를 제공합니다. (Element 객체 구조에서 제거되지 않습니다.)<br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2019. 1. 25.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @return 요소 객체
     * 
     * @throws java.util.NoSuchElementException
     *             스택이 비어있는 경우
     *
     * @since 2019. 1. 25.
     */
    // 아래 내용에 적용됨.
    // - this.elemObjects.getFirst()
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    protected final Object peekElementObject() {
        return this.elemObjects.getFirst();
    }

    /**
     * Element에 해당하는 객체를 제공합니다. (Element 객체 구조에서 제거됩니다.)<br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2019. 1. 25.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @return 요소 객체. 비어있는 경우 {@code null}을 반환.
     * 
     * @throws java.util.NoSuchElementException
     *             스택이 비어있는 경우
     *
     * @since 2019. 1. 25.
     */
    // 아래 내용에 적용됨.
    // - this.elemObjects.removeFirst()
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    protected final Object popElementObject() {
        return this.elemObjects.removeFirst();
    }

    /**
     * SAX Element TEXT를 변환하는 도구를 제공합니다.<br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2019. 1. 25.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param converter
     *            컨버터 객체
     *
     * @since 2019. 1. 25.
     */
    protected abstract void registerDataConverters(SaxTextConverter converter);

    /**
     * 데이터로 사용되는 Element Name을 제공합니다. <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2019. 1. 25.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param dataQNames
     *            컬렉션 객체
     *
     * @since 2019. 1. 25.
     */
    protected abstract void registerDataQNames(Collection<String> dataQNames);

    protected final void setIndentationStr(String indent) {
        this.indentStr = indent;
    }

    /**
     */
    // 아래 내용에 적용됨.
    // - public final void startElement(String uri, String localName, String qName, Attributes attributes) throws
    // SAXException {
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    @Override
    public final void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        AssertUtils2.notNulls(uri, localName, qName, attributes);

        // #1. Add a name of an element to be parsed.
        this.qnames.push(qName);

        if (this.logger.isDebugEnabled()) {
            this.logger.debug("{}[ELEMENT::start] qName: {}, attributes: {}, uri: {}, localName: {}", indentation(), qName, attributes.getLength(), uri, localName);
            // [오류 수정 완료] 괄호 묶음 오류 수정
            this.logger.debug("{}>>>>>>>>>> start of '{}'", indentation(), qName);
        }

        // #2. Increase an indentation.
        incIndentation();

        // #3.
        startElement0(uri, localName, qName, attributes);
    }

    // ... (중략) ...

    /**
     * 요소(Element) 시작 시 수행할 구체적인 로직을 정의합니다.<br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2019. 1. 25.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param uri
     *            네임스페이스 URI. 요소에 네임스페이스 URI가 없거나 네임스페이스 처리를 수행하지 않는 경우 빈 문자열({@code ""})이 전달됩니다.
     * @param localName
     *            로컬 이름 (접두사 제외). 네임스페이스 처리를 수행하지 않는 경우 빈 문자열({@code ""})이 전달됩니다.
     * @param qName
     *            정규화된 이름 (접두사 포함). 정규화된 이름을 사용할 수 없는 경우 빈 문자열({@code ""})이 전달됩니다.
     * @param attributes
     *            요소에 첨부된 속성(Attributes)들. 지정된 속성이 없는 경우 비어 있는 Attributes 객체가 전달됩니다.
     *
     * @throws SAXException
     *             SAX 처리 중 발생하는 예외. 내부적으로 다른 예외를 래핑(wrapping)할 수 있습니다.
     *
     * @since 2019. 1. 25.
     *
     * @see #startElement(String, String, String, Attributes)
     * @see org.xml.sax.ContentHandler#startElement
     */
    public abstract void startElement0(String uri, String localName, String qName, Attributes attributes) throws SAXException;
}