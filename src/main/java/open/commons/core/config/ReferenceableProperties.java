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
package open.commons.core.config;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InvalidPropertiesFormatException;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jspecify.annotations.Nullable;

import open.commons.core.Result;
import open.commons.core.config.exception.PropertyNoSeedException;
import open.commons.core.config.exception.PropertySelfReferenceException;
import open.commons.core.utils.AssertUtils2;
import open.commons.core.utils.OrderingUtils;

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
 * -------------
 * 결과:
 * root = E:\00.Development\00.Code\OpenCommons\.
 * user.dir = E:\00.Development\00.Code\OpenCommons\.
 * test.pkg = E:\00.Development\00.Code\OpenCommons\.\test\open\test
 * test.dir = E:\00.Development\00.Code\OpenCommons\.\test
 * 
 * </pre>
 * 
 * @since 2012. 02. 15.
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
public class ReferenceableProperties extends Properties {
    private static final long serialVersionUID = 1L;

    /** ${key} 형태의 문자열을 처리하는 정규식 */
    private static final String REFERENCING_REG_EX = "^([^$]*)\\$\\{\\s*([^(\\{|\\|\\s|\\})]+)\\s*[}]([^}].*)?$";

    /** {@link #REFERENCING_REG_EX}를 처리하는 패턴 객체 */
    @SuppressWarnings("null")
    private static final Pattern REFERENCING_PATTERN = Pattern.compile(REFERENCING_REG_EX);

    /** 프로퍼티의 {@code key=value} 중에 다른 {@code key}를 참조하지 않는 값들 */
    transient private final Map<String, String> keyReferencing = new HashMap<String, String>();

    /** 프로퍼티의 {@code key=value] 중에 다른 {@code key}를 참조하는 값들 */
    transient private final Map<String, String> keyReferenced = new HashMap<String, String>();

    boolean readyReferencing = true;

    public ReferenceableProperties() {
        super();

        referencing();
    }

    public ReferenceableProperties(@Nullable Properties defaults) {
        super(defaults);

        referencing();
    }

    public ReferenceableProperties(@Nullable ReferenceableProperties defaults) {
        super(defaults);

        referencing();
    }

    /**
     * {@link Properties#entrySet()}를
     * {@link Set}&lt;{@link Entry}&lt;{@link String}, {@link String}&gt;&gt;로
     * 반환합니다.
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

    private boolean isReferencingValue(@Nullable String value) {
        return REFERENCING_PATTERN.matcher(value).matches();
    }

    /**
     * @param inStream
     * @throws IOException
     * 
     * @since 2012. 02. 15.
     * 
     * @see java.util.Properties#load(java.io.InputStream)
     */
    @SuppressWarnings("null")
    @Override
    public synchronized void load(InputStream inStream) throws IOException {
        Objects.requireNonNull(inStream);

        super.load(inStream);

        referencing();
    }

    /**
     * @param inStreams
     * @throws IOException
     * 
     * @since 2012. 02. 15.
     */
    // 아래 내용에 적용됨.
    // - AssertUtils2.notNulls((Object[]) inStreams);
    // [PATCH] 배열 공변성/가변성에 대한 IDE 분석기의 오탐 우회
    // [TODO] 향후 IDE의 배열 데이터 흐름 분석이 고도화되거나 JSpecify가 완벽히 지원되면 '제거'
    @SuppressWarnings("null")
    public synchronized void load(InputStream... inStreams) throws IOException {
        AssertUtils2.notNulls((Object[]) inStreams);

        readyReferencing = false;

        for (InputStream inStream : inStreams) {
            if (inStream != null) {
                load(inStream);
            }
        }

        readyReferencing = true;

        referencing();
    }

    /**
     * @param reader
     * @throws IOException
     * 
     * @since 2012. 02. 15.
     * 
     * @see java.util.Properties#load(java.io.Reader)
     */
    @SuppressWarnings("null")
    @Override
    public synchronized void load(Reader reader) throws IOException {
        Objects.requireNonNull(reader);

        super.load(reader);

        referencing();
    }

    /**
     * @param reader
     * @throws IOException
     * 
     * @since 2012. 02. 15.
     * 
     * @see java.util.Properties#load(java.io.Reader)
     */
    // 아래 내용에 적용됨.
    // - AssertUtils2.notNulls((Object[]) readers);
    // [PATCH] 배열 공변성/가변성에 대한 IDE 분석기의 오탐 우회
    // [TODO] 향후 IDE의 배열 데이터 흐름 분석이 고도화되거나 JSpecify가 완벽히 지원되면 '제거'
    @SuppressWarnings("null")
    public synchronized void load(Reader... readers) throws IOException {
        AssertUtils2.notNulls((Object[]) readers);

        readyReferencing = false;

        for (Reader reader : readers) {
            if (reader != null) {
                load(reader);
            }
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
     */
    @SuppressWarnings("null")
    @Override
    public synchronized void loadFromXML(InputStream in) throws IOException, InvalidPropertiesFormatException {
        Objects.requireNonNull(in);

        super.loadFromXML(in);

        referencing();
    }

    /**
     * @param in
     * @throws IOException
     * @throws InvalidPropertiesFormatException
     * 
     * @since 2012. 02. 15.
     */
    // 아래 내용에 적용됨.
    // - AssertUtils2.notNulls((Object[]) ins);
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [PATCH] 배열 공변성/가변성에 대한 IDE 분석기의 오탐 우회
    // [TODO] 향후 IDE의 배열 데이터 흐름 분석이 고도화되거나 JSpecify가 완벽히 지원되면 '제거'
    @SuppressWarnings("null")
    public synchronized void loadFromXML(InputStream... ins) throws IOException, InvalidPropertiesFormatException {
        AssertUtils2.notNulls((Object[]) ins);

        readyReferencing = false;

        for (InputStream in : ins) {
            if (in != null) {
                loadFromXML(in);
            }
        }

        readyReferencing = true;

        referencing();
    }

    /**
     * 다른 값들을 참조하는 것과 그렇지 않은 것들을 초기화 합니다.
     * 
     * @since 2012. 02. 15.
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
            if (key == null) {
                continue;
            }
            String value = entry.getValue();
            Objects.requireNonNull(value, "'keyReferencing'의 값(value)는 {@code null}을 허용하지 않습니다.");

            result = resolveRef0(key, value);

            if (result.getResult()) {
                keyReferenced.put(key, result.getData());
            } else {
                newKeyReferencing.put(key, result.getData());
            }
        }

        if (keyReferencing.size() != 0 && newKeyReferencing.size() == keyReferencing.size()) {
            throw new PropertyNoSeedException("There is no more SEED key: seeds=" + keyReferenced.keySet()
                    + ", referer=" + keyReferencing.values());
        }

        if (newKeyReferencing.size() > 0) {
            keyReferencing.clear();
            keyReferencing.putAll(newKeyReferencing);

            resolveRef();
        } else {
            keyReferencing.clear();
        }
    }

    // 아래 내용에 적용됨.
    // - input = sb.toString();
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    private Result<String> resolveRef0(String keyOfInput, String input) {
        StringBuilder sb = new StringBuilder();

        Matcher m = REFERENCING_PATTERN.matcher(input);

        if (m.matches()) {
            String g1 = null;
            String varRef = null;
            String value = null;
            String g3 = null;

            int refIndex = 0;

            do {
                refIndex++;
                g1 = m.group(1);
                if (g1 != null && !g1.isBlank()) {
                    sb.append(g1);
                }

                varRef = m.group(2);
                // check 'self-reference'
                if (keyOfInput.equals(varRef)) {
                    throw new PropertySelfReferenceException("'" + OrderingUtils.intToOrdinal(refIndex)
                            + "' reference is invalid: key=" + keyOfInput + ", value=" + input);
                }

                value = keyReferenced.get(varRef);
                if (value != null) {
                    sb.append(value);
                } else {
                    sb.append("${" + varRef + "}");
                }

                g3 = m.group(3);
                if (g3 != null) {
                    m = REFERENCING_PATTERN.matcher(g3);

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
