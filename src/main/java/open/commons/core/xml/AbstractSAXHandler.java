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
import java.util.Collection;
import java.util.HashSet;
import java.util.Stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import open.commons.core.utils.ArrayUtils;
import open.commons.core.utils.StringUtils;

/**
 * <br>
 * 
 * <pre>
 * [개정이력]
 *      날짜    	| 작성자	|	내용
 * ------------------------------------------
 * xxxx.xx.xx		xxx			최초작성
 * 2019. 10. 17.		parkjunohng77@gmail.com			Logger 교체. org.apache.logging.log4j.Logger -> org.slf4j.Logger 로 교체
 * </pre>
 * 
 * @since 2019. 1. 25.
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public abstract class AbstractSAXHandler extends DefaultHandler {

    protected Logger logger = LoggerFactory.getLogger(getClass());
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
     * 2019. 1. 25.     parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param logger
     *            일반 로그
     * @param errorLogger
     *            에러 로그
     * @since 2019. 1. 25.
     */
    public AbstractSAXHandler(Logger logger, Logger errorLogger) {
        this(null, null, false);
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
    public AbstractSAXHandler(Logger logger, Logger errorLogger, boolean ignoreadInvalidValue) {
        // #0. Assign loggers.
        this.logger = logger != null ? logger : LoggerFactory.getLogger(getClass());
        this.errorLogger = errorLogger != null ? errorLogger : LoggerFactory.getLogger(getClass());
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
     * 2019. 1. 25.     parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param qName
     * @param object
     * @return
     *
     * @since 2019. 1. 25.
     */
    protected final Object addElementObject(Object object) {
        return elemObjects.add(object);
    }

    /**
     * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (dataQNames.contains(getCurrentQName())) {
            logger.debug(String.format("%s[ELEMENT::characters] start: %,4d, length: %,4d, value: %s" //
                    , indentation(), start, length, new String(ArrayUtils.copyOfRange(ch, start, start + length))));
        }

        // 데이타를 갖지 않는 Element는 패스.
        if (!dataQNames.contains(getCurrentQName())) {
            return;
        }

        String strValue = null, qname = null;
        Object parentObj = null;
        try {
            // #0. SAX Element's TEXT value
            strValue = new String(ArrayUtils.copyOfRange(ch, start, start + length));
            // #1. 현재 상태의 Element 이름
            qname = getCurrentQName();
            // #2. 상위 Element 이름 및 객체
            parentObj = getParentObject(qname);
            // #3. TEXT value 를 설정
            converter.convert(parentObj, qname, strValue);
        } catch (NumberFormatException ignored) {
            if (!ignoreadInvalidValue) {
                errorLogger.warn(String.format("잘못된 형식의 데이타 수신. value: %s, QName: %s, ParentObj: %s, cause: %s", strValue, qname, parentObj, ignored.getMessage()));
            }
            strValue = null;
        } catch (IllegalAccessException | InvocationTargetException | RuntimeException e) {
            String errorMsg = String.format("value: %s, QName: %s, ParentObj: %s, cause: %s", strValue, qname, parentObj, e.getMessage());

            errorLogger.error(errorMsg, e);
            throw new SAXException(errorMsg, e);
        }

        if (logger.isDebugEnabled()) {
            logger.debug(String.format("%s(Object) %s.%s = %s", indentation(), parentObj != null ? parentObj.getClass().getSimpleName() : null, qname, strValue));
        }
    }

    protected final void decIndentation() {
        indent--;
    }

    /**
     * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public final void endElement(String uri, String localName, String qName) throws SAXException {

        // #1. Decrease an indentation.
        decIndentation();

        if (logger.isDebugEnabled()) {
            logger.debug(String.format("%s[ELEMENT::end] qName: %s, uri: %s, localName: %s" //
                    , indentation(), qName, uri, localName));
        }

        if (logger.isDebugEnabled()) {
            logger.debug(String.format("%s<<<<<<<<<< end of '%s'", indentation(), qName));
        }

        // #2. Handle a parsed data.
        endElement0(uri, localName, qName);

        // #3. Remove a name of an element parsed.
        qnames.pop();
    }

    /**
     * Define a specific logic. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 1. 25.     parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param uri
     *            The Namespace URI, or the empty string if the element has no Namespace URI or if Namespace processing
     *            is not being performed.
     * @param localName
     *            The local name (without prefix), or the empty string if Namespace processing is not being performed.
     * @param qName
     *            The qualified name (with prefix), or the empty string if qualified names are not available.
     * @exception org.xml.sax.SAXException
     *                Any SAX exception, possibly wrapping another exception.
     * @see org.xml.sax.ContentHandler#endElement
     *
     * @since 2019. 1. 25.
     * 
     * @see #endElement(String, String, String)
     */
    public abstract void endElement0(String uri, String localName, String qName) throws SAXException;

    protected String getCurrentQName() {
        return qnames.size() > 0 ? qnames.peek() : null;
    }

    /**
     * Parent Element 에 해당하는 객체를 제공합니다. 기본적으로 Element 객체 스택의 최상위 객체를 제공합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 1. 25.     parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param qName
     *            현재 Element 이름
     * @return
     *
     * @since 2019. 1. 25.
     */
    protected Object getParentObject(String qName) {
        return elemObjects.peek();
    }

    protected final void incIndentation() {
        indent++;
    }

    /**
     * Return a string to be used as a indentation. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 1. 25.     parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @return a string to be used as a indentation.
     *
     * @since 2019. 1. 25.
     */
    protected final String indentation() {
        return new StringBuffer(StringUtils.nTimesString(indentStr, indent)).append(" ").toString();
    }

    /**
     * Element에 해당하는 객체를 제공합니다. (Element 객체 스택에서 제거되지 않는다.)<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 1. 25.     parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param qName
     * @return
     *
     * @since 2019. 1. 25.
     * 
     * @see Stack#peek()
     */
    protected final Object peekElementObject() {
        return elemObjects.peek();
    }

    /**
     * Element에 해당하는 객체를 제공합니다. (Element 객체 스택에서 제거된다.)<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 1. 25.     parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2019. 1. 25.
     * 
     * @see Stack#pop()
     */
    protected final Object popElementObject() {
        return elemObjects.pop();
    }

    /**
     * SAX Element TEXT를 변환하는 도구를 제공합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 1. 25.     parkjunohng77@gmail.com         최초 작성
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
     * 2019. 1. 25.     parkjunohng77@gmail.com         최초 작성
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
     * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String,
     *      org.xml.sax.Attributes)
     */
    @Override
    public final void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        // #1. Add a name of an element to be parsed.
        qnames.push(qName);

        if (logger.isDebugEnabled()) {
            logger.debug(String.format("%s>>>>>>>>>> start of '%s'", indentation(), qName));
        }

        if (logger.isDebugEnabled()) {
            logger.debug(String.format("%s[ELEMENT::start] qName: %s, attributes: %s, uri: %s, localName: %s" //
                    , indentation(), qName, attributes.getLength(), uri, localName));
        }

        // #2. Increase an indentation.
        incIndentation();

        // #3.
        startElement0(uri, localName, qName, attributes);
    }

    /**
     * Define a specific logic.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 1. 25.     parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param uri
     *            The Namespace URI, or the empty string if the element has no Namespace URI or if Namespace processing
     *            is not being performed.
     * 
     * @param localName
     *            The local name (without prefix), or the empty string if Namespace processing is not being performed.
     * @param qName
     *            The qualified name (with prefix), or the empty string if qualified names are not available.
     * @param attributes
     *            The attributes attached to the element. If there are no attributes, it shall be an empty Attributes
     *            object.
     * @exception org.xml.sax.SAXException
     *                Any SAX exception, possibly wrapping another exception.
     * @see org.xml.sax.ContentHandler#startElement
     *
     * @since 2019. 1. 25.
     * 
     * @see #startElement(String, String, String, Attributes)
     */
    public abstract void startElement0(String uri, String localName, String qName, Attributes attributes) throws SAXException;

}
