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
package open.commons.config;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InvalidPropertiesFormatException;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import open.commons.Result;
import open.commons.config.exception.PropertyNoSeedException;
import open.commons.config.exception.PropertySelfReferenceException;
import open.commons.utils.OrderingUtils;

/**
 * 
 * 프로퍼티 파일(.properties)에서 ${key} 형태의 참조기능을 지원하는 클래스 <BR>
 * 
 * <pre>
 * 입력: filepath_test.properties
 * 
 * root=.
 * user.dir=${root}
 * test.dir=${root}/test
 * test.pkg=${test.dir}/open/test
 * 
 * 테스트 코드:
 * 
 * String file = "filepath_test.properties";
 * 
 * ReferenceableProperties prop = new ReferenceableProperties();
 * prop.load(ReferenceableProperties.class.getResourceAsStream("/open/commons/" + file));
 * 
 * for( Entry<Object, Object> entry : prop.entrySet() ) {
 * 	println(entry.getKey() + " = " + new File((String) entry.getValue()).getAbsolutePath());
 * }
 * 		
 * 		
 * -------------
 * 결과:
 * root = E:\00.Development\00.Code\OpenCommons\.
 * user.dir = E:\00.Development\00.Code\OpenCommons\.
 * test.pkg = E:\00.Development\00.Code\OpenCommons\.\test\open\test
 * test.dir = E:\00.Development\00.Code\OpenCommons\.\test
 * 
 * 
 * 
 * </pre>
 * 
 * @since 2012. 02. 15.
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
public class ReferenceableProperties extends Properties {
    /** ${key} 형태의 문자열을 처리하는 정규식 */
    private static final String referencingRegEx = "^([^$]*)\\$\\{\\s*([^(\\{|\\|\\s|\\})]+)\\s*[}]([^}].*)?$";

    /** {@link #referencingRegEx}를 처리하는 패턴 객체 */
    private static final Pattern referencingPattern = Pattern.compile(referencingRegEx);

    /** 프로퍼티의 {@code key=value} 중에 다른 {@code key}를 참조하지 않는 값들 */
    transient private final Map<String, String> keyReferencing = new HashMap<String, String>();
    /** 프로퍼티의 {@code key=value] 중에 다른 {@code key}를 참조하는 값들 */
    transient private final Map<String, String> keyReferenced = new HashMap<String, String>();

    /** */
    private static final long serialVersionUID = 1L;

    boolean readyReferencing = true;

    public ReferenceableProperties() {
        super();

        referencing();
    }

    public ReferenceableProperties(Properties defaults) {
        super(defaults);

        referencing();
    }

    public ReferenceableProperties(ReferenceableProperties defaults) {
        super(defaults);

        referencing();
    }

    /**
     * {@link Properties#entrySet()}를 {@link Set}&lt;{@link Entry}&lt;{@link String}, {@link String}&gt;&gt;로 반환한다.
     * 
     * @return
     */
    public Set<java.util.Map.Entry<String, String>> entryString() {
        Set<java.util.Map.Entry<String, String>> entrySet = new HashSet<java.util.Map.Entry<String, String>>();

        for (java.util.Map.Entry<Object, Object> srcEntry : entrySet()) {
            entrySet.add(new EntryInstance<String, String>((String) srcEntry.getKey(), (String) srcEntry.getValue()));
        }

        return entrySet;
    }

    private boolean isReferencingValue(String value) {
        return referencingPattern.matcher(value).matches();
    }

    /**
     * @param inStream
     * @throws IOException
     * 
     * @since 2012. 02. 15.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see java.util.Properties#load(java.io.InputStream)
     */
    @Override
    public synchronized void load(InputStream inStream) throws IOException {
        super.load(inStream);

        referencing();
    }

    public synchronized void load(InputStream... inStreams) throws IOException {
        readyReferencing = false;

        for (InputStream inStream : inStreams) {
            load(inStream);
        }

        readyReferencing = true;

        referencing();
    }

    /**
     * @param reader
     * @throws IOException
     * 
     * @since 2012. 02. 15.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see java.util.Properties#load(java.io.Reader)
     */
    @Override
    public synchronized void load(Reader reader) throws IOException {
        super.load(reader);

        referencing();
    }

    public synchronized void load(Reader... readers) throws IOException {
        readyReferencing = false;

        for (Reader reader : readers) {
            load(reader);
        }

        readyReferencing = true;

        referencing();
    }

    /**
     * @param in
     * @throws IOException
     * @throws InvalidPropertiesFormatException
     * 
     * @since 2012. 02. 15.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see java.util.Properties#loadFromXML(java.io.InputStream)
     */
    @Override
    public synchronized void loadFromXML(InputStream in) throws IOException, InvalidPropertiesFormatException {
        super.loadFromXML(in);

        referencing();
    }

    public synchronized void loadFromXML(InputStream... ins) throws IOException, InvalidPropertiesFormatException {
        readyReferencing = false;

        for (InputStream in : ins) {
            loadFromXML(in);
        }

        readyReferencing = true;

        referencing();
    }

    /**
     * 다른 값들을 참조하는 것과 그렇지 않은 것들을 초기화 한다.
     * 
     * 
     * @since 2012. 02. 15.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    private void readyResolveRef() {
        keyReferencing.clear();
        keyReferenced.clear();

        Set<java.util.Map.Entry<Object, Object>> entries = entrySet();

        String key = null;
        String value = null;
        for (java.util.Map.Entry<Object, Object> entry : entries) {
            key = (String) entry.getKey();
            value = (String) entry.getValue();

            if (isReferencingValue(value)) {
                keyReferencing.put(key, value);
            } else {
                keyReferenced.put(key, value);
            }
        }
    }

    private void referencing() {
        if (readyReferencing) {

            readyResolveRef();

            resolveRef();

            for (java.util.Map.Entry<String, String> entry : keyReferenced.entrySet()) {
                super.put(entry.getKey(), entry.getValue());
            }
        }
    }

    private void resolveRef() {
        Map<String, String> newKeyReferencing = new HashMap<String, String>();

        Result<String> result = null;
        for (java.util.Map.Entry<String, String> entry : keyReferencing.entrySet()) {
            String key = entry.getKey();
            result = resolveRef0(key, entry.getValue());

            if (result.getResult()) {
                keyReferenced.put(key, result.getData());
            } else {
                newKeyReferencing.put(key, result.getData());
            }
        }

        if (keyReferencing.size() != 0 && newKeyReferencing.size() == keyReferencing.size()) {
            throw new PropertyNoSeedException("There is no more SEED key: seeds=" + keyReferenced.keySet() + ", referer=" + keyReferencing.values());
        }

        if (newKeyReferencing.size() > 0) {
            keyReferencing.clear();
            keyReferencing.putAll(newKeyReferencing);

            resolveRef();
        } else {
            keyReferencing.clear();
        }
    }

    private Result<String> resolveRef0(String keyOfInput, String input) {

        StringBuffer sb = new StringBuffer();

        Matcher m = referencingPattern.matcher(input);

        if (m.matches()) {
            String g1 = null;
            String varRef = null;
            String value = null;
            String g3 = null;

            int refIndex = 0;

            do {
                refIndex++;
                g1 = m.group(1);
                if (g1 != null && !g1.trim().isEmpty()) {
                    sb.append(g1);
                }

                varRef = m.group(2);
                // check 'self-reference'
                if (keyOfInput.equals(varRef)) {

                    throw new PropertySelfReferenceException("'" + OrderingUtils.intToOrdinal(refIndex) + "' reference is invalid: key=" + keyOfInput + ", value=" + input);
                }

                value = keyReferenced.get(varRef);
                if (value != null) {
                    sb.append(value);
                } else {
                    sb.append("${" + varRef + "}");
                }

                g3 = m.group(3);
                if (g3 != null) {
                    m = referencingPattern.matcher(g3);

                    if (!m.matches()) {
                        sb.append(g3);
                        break;
                    }
                } else {
                    break;
                }
            } while (true);

            input = sb.toString();

            return new Result<String>(input, !isReferencingValue(input));
        } else {
            return new Result<String>(input, true);
        }
    }
}
