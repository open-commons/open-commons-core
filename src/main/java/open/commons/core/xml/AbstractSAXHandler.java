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
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Stack;

import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import open.commons.core.utils.ObjectUtils;

/**
 * <br>
 * 
 * <pre>
 * [개정이력]
 *      날짜    	| 작성자	|	내용
 * ------------------------------------------
 * 2019. 1. 25.         parkjunhong77@gmail.com     최초작성
 * 2019. 10. 17.        parkjunhong77@gmail.com     Logger 교체. org.apache.logging.log4j.Logger -> org.slf4j.Logger 로 교체
 * </pre>
 * 
 * @since 2019. 1. 25.
 * 
 */
public abstract class AbstractSAXHandler extends DefaultHandler {

    @SuppressWarnings("null")
    protected Logger logger = LoggerFactory.getLogger(getClass());
    @SuppressWarnings("null")
    protected Logger errorLogger = LoggerFactory.getLogger(getClass());

    private boolean ignoreadInvalidValue;

    private String indentStr = "...";
    private int indent = 1;

    /** SAX Element TEXT 데이타 변환기 */
    private final SaxTextConverter converter = new SaxTextConverter();
    /** Element 이름 */
    private final Stack<String> qnames = new Stack<>();
    /** 데이터로 사용되는 Element 이름 */
    private final HashSet<String> dataQNames = new HashSet<>();
    /** Element Object */
    private final Stack<Object> elemObjects = new Stack<>();

    /**
     * 
     * @param ignoreadInvalidValue
     *            잘못된 값 처리 무시 여부
     * @since 2019. 1. 29.
     */
    public AbstractSAXHandler(boolean ignoreadInvalidValue) {
        this(null, null, ignoreadInvalidValue);
    }

    /**
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 1. 25.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param logger
     *            일반 로그
     * @param errorLogger
     *            에러 로그
     * @since 2019. 1. 25.
     */
    public AbstractSAXHandler(@Nullable Logger logger, @Nullable Logger errorLogger) {
        this(logger, errorLogger, false);
    }

    /**
     * 
     * @param logger
     *            일반 로그
     * @param errorLogger
     *            에러 로그
     * @param ignoreadInvalidValue
     *            잘못된 값 처리 무시 여부
     * @since 2019. 1. 29.
     */
    public AbstractSAXHandler(@Nullable Logger logger, @Nullable Logger errorLogger, boolean ignoreadInvalidValue) {
        if (logger != null) {
            this.logger = logger;
        }
        if (errorLogger != null) {
            this.errorLogger = errorLogger;
        }
        this.ignoreadInvalidValue = ignoreadInvalidValue;

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
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 1. 25.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param qName
     * @param object
     * @return
     *
     * @since 2019. 1. 25.
     */
    protected final Object addElementObject(@Nullable Object object) {
        return elemObjects.add(object);
    }

    /**
     * @throws NullPointerException
     *             파라미터({@code ch})가 {@code null}인 경우 발생.
     * 
     * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
     */
    // 아래 내용에 적용됨.
    // - char[] ch
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        Objects.requireNonNull(ch);

        if (dataQNames.contains(getCurrentQName())) {
            logger.debug(String.format("%s[ELEMENT::characters] start: %,4d, length: %,4d, value: %s" //
                    , indentation(), start, length, new String(Arrays.copyOfRange(ch, start, start + length))));
        }

        // 데이타를 갖지 않는 Element는 패스.
        if (!this.dataQNames.contains(getCurrentQName())) {
            return;
        }

        String strValue = null, qname = null;
        Object parentObj = null;
        try {
            // #0. SAX Element's TEXT value
            strValue = new String(Arrays.copyOfRange(ch, start, start + length));
            // #1. 현재 상태의 Element 이름
            qname = getCurrentQName();
            // #2. 상위 Element 이름 및 객체
            parentObj = getParentObject(qname);
            // #3. TEXT value 를 설정
            this.converter.convert(parentObj, qname, strValue);
        } catch (NumberFormatException ignored) {
            if (!this.ignoreadInvalidValue) {
                this.errorLogger.warn(String.format("잘못된 형식의 데이타 수신. value: %s, QName: %s, ParentObj: %s, cause: %s", strValue, qname, parentObj, ignored.getMessage()));
            }
            strValue = null;
        } catch (IllegalAccessException | InvocationTargetException | RuntimeException e) {
            String errorMsg = String.format("value: %s, QName: %s, ParentObj: %s, cause: %s", strValue, qname, parentObj, e.getMessage());

            this.errorLogger.error(errorMsg, e);
            throw new SAXException(errorMsg, e);
        }

        if (this.logger.isDebugEnabled()) {
            this.logger.debug(String.format("%s(Object) %s.%s = %s", indentation(), parentObj != null ? parentObj.getClass().getSimpleName() : null, qname, strValue));
        }
    }

    protected final void decIndentation() {
        this.indent--;
    }

    /**
     * @throws NullPointerException
     *             파라미터중에 1개라도 {@code null}인 경우 발생.
     * 
     * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
     */
    // 아래 내용에 적용됨.
    // - 파라미터 전체 (String uri, String localName, String qName)
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    @Override
    public final void endElement(String uri, String localName, String qName) throws SAXException {
        ObjectUtils.requireNonNulls(uri, localName, qName);

        // #1. Decrease an indentation.
        decIndentation();

        if (this.logger.isDebugEnabled()) {
            this.logger.debug(String.format("%s[ELEMENT::end] qName: %s, uri: %s, localName: %s" //
                    , indentation(), qName, uri, localName));
        }

        if (this.logger.isDebugEnabled()) {
            this.logger.debug(String.format("%s<<<<<<<<<< end of '%s'", indentation(), qName));
        }

        // #2. Handle a parsed data.
        endElement0(uri, localName, qName);

        // #3. Remove a name of an element parsed.
        this.qnames.pop();
    }

    /**
     * 요소(Element) 종료 시 수행할 구체적인 로직을 정의합니다. <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 1. 25.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param uri
     *            네임스페이스 URI. 요소에 네임스페이스 URI가 없거나 네임스페이스 처리를 수행하지 않는 경우 빈 문자열({@code ""})이 전달됩니다.
     * @param localName
     *            로컬 이름 (접두사 제외). 네임스페이스 처리를 수행하지 않는 경우 빈 문자열({@code ""})이 전달됩니다.
     * @param qName
     *            정규화된 이름 (접두사 포함). 정규화된 이름을 사용할 수 없는 경우 빈 문자열({@code ""})이 전달됩니다.
     * 
     * @throws NullPointerException
     *             파라미터중에 1개라도 {@code null}인 경우 발생.
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
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 1. 25.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     * 
     * @return 스택의 최상위 객체 (없거나 null이 푸시된 경우 null 반환)
     */
    protected @Nullable String getCurrentQName() {
        return this.qnames.isEmpty() ? null : this.qnames.peek();
    }

    /**
     * Parent Element 에 해당하는 객체를 제공합니다. 기본적으로 Element 객체 스택의 최상위 객체를 제공합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 1. 25.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param qName
     *            현재 Element 이름
     * @return
     *
     * @since 2019. 1. 25.
     */
    protected @Nullable Object getParentObject(String qName) {
        return this.elemObjects.isEmpty() ? null : this.elemObjects.peek();
    }

    protected final void incIndentation() {
        this.indent++;
    }

    /**
     * Return a string to be used as a indentation. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 1. 25.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @return a string to be used as a indentation.
     *
     * @since 2019. 1. 25.
     */
    // 아래 내용에 적용됨.
    // - return new StringBuffer(StringUtils.nTimesString(this.indentStr, this.indent)).append(" ").toString();
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    protected final String indentation() {
        return new StringBuffer(this.indentStr.repeat(this.indent)).append(" ").toString();
    }

    /**
     * Element에 해당하는 객체를 제공합니다. (Element 객체 스택에서 제거되지 않는다.)<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 1. 25.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param qName
     * @return
     *
     * @since 2019. 1. 25.
     * 
     * @see Stack#peek()
     */
    protected final @Nullable Object peekElementObject() {
        return this.elemObjects.isEmpty() ? null : this.elemObjects.peek();
    }

    /**
     * Element에 해당하는 객체를 제공합니다. (Element 객체 스택에서 제거된다.)<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 1. 25.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2019. 1. 25.
     * 
     * @see Stack#pop()
     */
    protected final @Nullable Object popElementObject() {
        return this.elemObjects.isEmpty() ? null : this.elemObjects.pop();
    }

    /**
     * SAX Element TEXT를 변환하는 도구를 제공합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 1. 25.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     * 
     * @param converter
     *            TODO
     *
     * @since 2019. 1. 25.
     */
    protected abstract void registerDataConverters(SaxTextConverter converter);

    /**
     * 데이터로 사용되는 Element Name을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 1. 25.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     * 
     * @param dataQNames
     *            TODO
     *
     * @since 2019. 1. 25.
     */
    protected abstract void registerDataQNames(Collection<String> dataQNames);

    protected final void setIndentationStr(String indent) {
        this.indentStr = indent;
    }

    /**
     * @throws NullPointerException
     *             파라미터중에 1개라도 {@code null}인 경우 발생.
     * 
     * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String,
     *      org.xml.sax.Attributes)
     */
    // 아래 내용에 적용됨.
    // - 메소드 파라미터 전체 (String uri, String localName, String qName, Attributes attributes)
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    @Override
    public final void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        ObjectUtils.requireNonNulls(uri, localName, qName, attributes);

        // #1. Add a name of an element to be parsed.
        this.qnames.push(qName);

        if (this.logger.isDebugEnabled()) {
            this.logger.debug(String.format("%s>>>>>>>>>> start of '%s'", indentation(), qName));
        }

        if (this.logger.isDebugEnabled()) {
            this.logger.debug(String.format("%s[ELEMENT::start] qName: %s, attributes: %s, uri: %s, localName: %s" //
                    , indentation(), qName, attributes.getLength(), uri, localName));
        }

        // #2. Increase an indentation.
        incIndentation();

        // #3.
        startElement0(uri, localName, qName, attributes);
    }

    /**
     * 요소(Element) 시작 시 수행할 구체적인 로직을 정의합니다.<br>
     * 
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 1. 25.     parkjunhong77@gmail.com         최초 작성
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
     * 
     * @throws NullPointerException
     *             파라미터중에 1개라도 {@code null}인 경우 발생.
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
