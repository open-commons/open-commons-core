/*
 * Copyright 2021 Park Jun-Hong_(parkjunhong77@gmail.com)
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
 * Date  : 2021. 11. 11. 오후 6:15:23
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package open.commons.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import open.commons.Result;
import open.commons.csv.CsvConfig;
import open.commons.csv.CsvFileConfig;
import open.commons.csv.ReadAt;
import open.commons.test.StopWatch;

import au.com.bytecode.opencsv.CSVReader;

/**
 * 
 * @since 2021. 11. 11.
 * @version 1.8.0
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
public class CsvUtils {

    private static final Logger logger = LoggerFactory.getLogger(CsvUtils.class);

    // prevent to create an instance.
    private CsvUtils() {
    }

    /**
     * 주어진 타입에 해당하는 객체를 생성한 후, 문자열 배열에서 데이터를 추출하여 값을 설정하는 함수를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 11.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     * @param type
     *            데이터 타입. (<b><code>NOT nullable</code></b>)
     * @return
     *
     * @since 2021. 11. 11.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * @see ReadAt
     */
    @SuppressWarnings("unchecked")
    public static final <E> Function<String[], E> defaultCreator(Class<E> type) {
        return arr -> {
            try {
                E o = type.newInstance();
                AnnotationUtils.getAnnotatedMethodsAllHierarchy(type, ReadAt.class) //
                        .stream() //
                        .collect(Collectors.toMap(m -> m, m -> m.getAnnotation(ReadAt.class))) //
                        .forEach((m, anno) -> {
                            // 파라미터 타입
                            int index = anno.index();
                            if (index < arr.length) {
                                try {
                                    if (m.getParameterTypes().length != 1) {
                                        throw new UnsupportedOperationException(String.format("'%s'가 설정된 메소드는 반드시 파라미터가 1개이어야 합니다. 메소드=%s, 파라미터개수=%,d",
                                                ReadAt.class.getCanonicalName(), m, m.getParameterTypes().length));
                                    }
                                    // 데이터 변환
                                    Class<?> argType = m.getParameterTypes()[0];
                                    Object arg = String.class.equals(argType) //
                                            ? arr[index] //
                                            : ConvertUtils.toPrimitiveTypeValue(argType, arr[index]) //
                                    ;
                                    // 객체에 설정
                                    m.invoke(o, arg);
                                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | UnsupportedOperationException e) {
                                    String errMsg = String.format("'%s' 객체의 값을 설정하는 도중 에러가 발생하였습니다. 메소드=%s, 값=%s", type.getName(), m.getName(), arr[index]);
                                    logger.error(errMsg, e);
                                    throw ExceptionUtils.newException(RuntimeException.class, e, errMsg);
                                }
                            }
                        });
                return o;
            } catch (InstantiationException | IllegalAccessException e) {
                String errMsg = String.format("'%s' 객체를 생성하는 도중 에러가 발생하였습니다.", type.getName());
                logger.error(errMsg, e);
                throw ExceptionUtils.newException(RuntimeException.class, e, errMsg);
            }
        };
    }

    /**
     * CSV 데이터를 읽을 {@link Reader}, CSV 설정정보를 이용하여 {@link CSVReader} 객체를 제공합니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 12.        박준홍         최초 작성
     * </pre>
     *
     * @param reader
     *            CSV 데이터 제공자
     * @param config
     *            CSV 설정
     * @return
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    private static CSVReader newCSVReader(Reader reader, CsvFileConfig config) {
        return new CSVReader(reader, config.getSeparator(), config.getQuotechar(), config.getEscape(), config.getSkip(), config.isStrictQuotes(),
                config.isIgnoreLeadingWhiteSpace());
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 11.        박준홍         최초 작성
     * </pre>
     *
     * @param <E>
     *            CSV 파일을 읽어서 생성할 데이터 모델. {@link ReadAt}이 설정된 메소드를 호출하여 데이터를 설정합니다.
     * @param reader
     *            CSV 데이터 (<b><code>NOT nullable</code></b>)
     * @param type
     *            데이터 타입. (<b><code>NOT nullable</code></b>)
     * @param close
     *            {@link InputStream} 여부
     * @return
     *
     * @since 2021. 11. 11.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(CSVReader reader, Class<E> type, boolean close) throws IOException {
        return readAsList(reader, defaultCreator(type), close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 11.        박준홍         최초 작성
     * </pre>
     *
     * @param <E>
     *            CSV 파일을 읽어서 생성할 데이터 모델. {@link ReadAt}이 설정된 메소드를 호출하여 데이터를 설정합니다.
     * @param reader
     *            CSV 데이터 (<b><code>NOT nullable</code></b>)
     * @param creator
     *            {@link String}[]를 전달받아 데이터를 생성하는 함수.<br>
     *            객체의 <code>setter</code> 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b><code>NOT nullable</code></b>)</font>
     * @param close
     *            {@link InputStream} 자동 종료 여부
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 11.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see ReadAt
     */
    public static <E> Result<List<E>> readAsList(CSVReader reader, Function<String[], E> creator, boolean close) throws IOException {

        StopWatch watch = new StopWatch();
        watch.start();

        List<E> data = new ArrayList<>();
        try {
            String[] readline = null;
            while ((readline = reader.readNext()) != null) {
                data.add(creator.apply(readline));
            }
            return Result.success(data);
        } catch (IOException e) {
            String errMsg = String.format("CSV 파일을 읽는 도중 에러가 발생하였습니다. 원인=%s", e.getMessage());
            logger.error(errMsg, e);
            throw new IOException(errMsg, e);
        } finally {
            if (close) {
                IOUtils.close(reader);
            }
            watch.stop();

            logger.trace(String.format("Data=%,d, Elasped=%s", data.size(), watch.getAsPretty()));
        }
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 12.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     * @param inputStream
     *            데이터 입력
     * @param charset
     *            데이터 문자열 셋
     * @param separator
     *            데이터 구분자
     * @param quotechar
     *            문자열 묶음 문자
     * @param escape
     *            Escape 문자
     * @param strictQuotes
     * @param ignoreLeadingWhiteSpace
     * @param type
     *            데이터 타입. (<b><code>NOT nullable</code></b>)
     * @param close
     *            {@link InputStream} 자동 종료 여부
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset //
            , char separator, char quotechar, char escape, boolean strictQuotes, boolean ignoreLeadingWhiteSpace //
            , Class<E> type, boolean close) throws IOException {
        return readAsList(inputStream, charset, separator, quotechar, escape, strictQuotes, ignoreLeadingWhiteSpace, defaultCreator(type), close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 12.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     * @param inputStream
     *            데이터 입력
     * @param charset
     *            데이터 문자열 셋
     * @param separator
     *            데이터 구분자
     * @param quotechar
     *            문자열 묶음 문자
     * @param escape
     *            Escape 문자
     * @param strictQuotes
     * @param ignoreLeadingWhiteSpace
     * @param creator
     *            {@link String}[]를 전달받아 데이터를 생성하는 함수.<br>
     *            객체의 <code>setter</code> 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b><code>NOT nullable</code></b>)</font>
     * @param close
     *            {@link InputStream} 자동 종료 여부
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset //
            , char separator, char quotechar, char escape, boolean strictQuotes, boolean ignoreLeadingWhiteSpace //
            , Function<String[], E> creator, boolean close) throws IOException {
        return readAsList(inputStream, charset, 0, separator, quotechar, escape, strictQuotes, ignoreLeadingWhiteSpace, creator, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 12.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     * @param inputStream
     *            데이터 입력
     * @param charset
     *            데이터 문자열 셋
     * @param separator
     *            데이터 구분자
     * @param quotechar
     *            문자열 묶음 문자
     * @param escape
     *            Escape 문자
     * @param strictQuotes
     * @param type
     *            데이터 타입. (<b><code>NOT nullable</code></b>)
     * @param close
     *            {@link InputStream} 자동 종료 여부
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset //
            , char separator, char quotechar, char escape, boolean strictQuotes //
            , Class<E> type, boolean close) throws IOException {
        return readAsList(inputStream, charset, separator, quotechar, escape, strictQuotes, defaultCreator(type), close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 12.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     * @param inputStream
     *            데이터 입력
     * @param charset
     *            데이터 문자열 셋
     * @param separator
     *            데이터 구분자
     * @param quotechar
     *            문자열 묶음 문자
     * @param escape
     *            Escape 문자
     * @param strictQuotes
     * @param creator
     *            {@link String}[]를 전달받아 데이터를 생성하는 함수.<br>
     *            객체의 <code>setter</code> 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b><code>NOT nullable</code></b>)</font>
     * @param close
     *            {@link InputStream} 자동 종료 여부
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset //
            , char separator, char quotechar, char escape, boolean strictQuotes //
            , Function<String[], E> creator, boolean close) throws IOException {
        return readAsList(inputStream, charset, 0, separator, quotechar, escape, strictQuotes, CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, creator, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 12.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     * @param inputStream
     *            데이터 입력
     * @param charset
     *            데이터 문자열 셋
     * @param separator
     *            데이터 구분자
     * @param quotechar
     *            문자열 묶음 문자
     * @param escape
     *            Escape 문자
     * @param type
     *            데이터 타입. (<b><code>NOT nullable</code></b>)
     * @param close
     *            {@link InputStream} 자동 종료 여부
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset, char separator, char quotechar, char escape, Class<E> type, boolean close)
            throws IOException {
        return readAsList(inputStream, charset, separator, quotechar, escape, defaultCreator(type), close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 12.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     * @param inputStream
     *            데이터 입력
     * @param charset
     *            데이터 문자열 셋
     * @param separator
     *            데이터 구분자
     * @param quotechar
     *            문자열 묶음 문자
     * @param escape
     *            Escape 문자
     * @param creator
     *            {@link String}[]를 전달받아 데이터를 생성하는 함수.<br>
     *            객체의 <code>setter</code> 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b><code>NOT nullable</code></b>)</font>
     * @param close
     *            {@link InputStream} 자동 종료 여부
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset, char separator, char quotechar, char escape, Function<String[], E> creator,
            boolean close) throws IOException {
        return readAsList(inputStream, charset, 0, separator, quotechar, escape, CsvConfig.DEFAULT_STRICT_QUOTES, CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, creator, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 12.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     * @param inputStream
     *            데이터 입력
     * @param charset
     *            데이터 문자열 셋
     * @param separator
     *            데이터 구분자
     * @param quotechar
     *            문자열 묶음 문자
     * @param type
     *            데이터 타입. (<b><code>NOT nullable</code></b>)
     * @param close
     *            {@link InputStream} 자동 종료 여부
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset, char separator, char quotechar, Class<E> type, boolean close) throws IOException {
        return readAsList(inputStream, charset, separator, quotechar, defaultCreator(type), close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 12.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     * @param inputStream
     *            데이터 입력
     * @param charset
     *            데이터 문자열 셋
     * @param separator
     *            데이터 구분자
     * @param quotechar
     *            문자열 묶음 문자
     * @param creator
     *            {@link String}[]를 전달받아 데이터를 생성하는 함수.<br>
     *            객체의 <code>setter</code> 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b><code>NOT nullable</code></b>)</font>
     * @param close
     *            {@link InputStream} 자동 종료 여부
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset, char separator, char quotechar, Function<String[], E> creator, boolean close)
            throws IOException {
        return readAsList(inputStream, charset, 0, separator, quotechar, CsvConfig.DEFAULT_ESCAPE_CHARACTER, CsvConfig.DEFAULT_STRICT_QUOTES,
                CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, creator, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 12.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     * @param inputStream
     *            데이터 입력
     * @param charset
     *            데이터 문자열 셋
     * @param separator
     *            데이터 구분자
     * @param type
     *            데이터 타입. (<b><code>NOT nullable</code></b>)
     * @param close
     *            {@link InputStream} 자동 종료 여부
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset, char separator, Class<E> type, boolean close) throws IOException {
        return readAsList(inputStream, charset, separator, defaultCreator(type), close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 12.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     * @param inputStream
     *            데이터 입력
     * @param charset
     *            데이터 문자열 셋
     * @param separator
     *            데이터 구분자
     * @param creator
     *            {@link String}[]를 전달받아 데이터를 생성하는 함수.<br>
     *            객체의 <code>setter</code> 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b><code>NOT nullable</code></b>)</font>
     * @param close
     *            {@link InputStream} 자동 종료 여부
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset, char separator, Function<String[], E> creator, boolean close) throws IOException {
        return readAsList(inputStream, charset, 0, separator, CsvConfig.DEFAULT_QUOTE_CHARACTER, CsvConfig.DEFAULT_ESCAPE_CHARACTER, CsvConfig.DEFAULT_STRICT_QUOTES,
                CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, creator, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 12.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     * @param inputStream
     *            데이터 입력
     * @param charset
     *            데이터 문자열 셋
     * @param skip
     *            생략할 라인 개수
     * @param separator
     *            데이터 구분자
     * @param quotechar
     *            문자열 묶음 문자
     * @param escape
     *            Escape 문자
     * @param strictQuotes
     * @param ignoreLeadingWhiteSpace
     * @param type
     *            데이터 타입. (<b><code>NOT nullable</code></b>)
     * @param close
     *            {@link InputStream} 자동 종료 여부
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset, int skip //
            , char separator, char quotechar, char escape, boolean strictQuotes, boolean ignoreLeadingWhiteSpace //
            , Class<E> type, boolean close) throws IOException {
        return readAsList(inputStream, charset, skip, separator, quotechar, escape, strictQuotes, ignoreLeadingWhiteSpace, defaultCreator(type), close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 12.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     * @param inputStream
     *            데이터 입력
     * @param charset
     *            데이터 문자열 셋
     * @param skip
     *            생략할 라인 개수
     * @param separator
     *            데이터 구분자
     * @param quotechar
     *            문자열 묶음 문자
     * @param escape
     *            Escape 문자
     * @param strictQuotes
     * @param ignoreLeadingWhiteSpace
     * @param creator
     *            {@link String}[]를 전달받아 데이터를 생성하는 함수.<br>
     *            객체의 <code>setter</code> 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b><code>NOT nullable</code></b>)</font>
     * @param close
     *            {@link InputStream} 자동 종료 여부
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset, int skip //
            , char separator, char quotechar, char escape, boolean strictQuotes, boolean ignoreLeadingWhiteSpace //
            , Function<String[], E> creator, boolean close) throws IOException {
        return readAsList(inputStream, new CsvFileConfig(separator, quotechar, escape, strictQuotes, ignoreLeadingWhiteSpace, skip, charset), creator, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 12.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     * @param inputStream
     *            데이터 입력
     * @param charset
     *            데이터 문자열 셋
     * @param skip
     *            생략할 라인 개수
     * @param separator
     *            데이터 구분자
     * @param quotechar
     *            문자열 묶음 문자
     * @param escape
     *            Escape 문자
     * @param strictQuotes
     * @param type
     *            데이터 타입. (<b><code>NOT nullable</code></b>)
     * @param close
     *            {@link InputStream} 자동 종료 여부
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset, int skip //
            , char separator, char quotechar, char escape, boolean strictQuotes //
            , Class<E> type, boolean close) throws IOException {
        return readAsList(inputStream, charset, skip, separator, quotechar, escape, strictQuotes, defaultCreator(type), close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 12.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     * @param inputStream
     *            데이터 입력
     * @param charset
     *            데이터 문자열 셋
     * @param skip
     *            생략할 라인 개수
     * @param separator
     *            데이터 구분자
     * @param quotechar
     *            문자열 묶음 문자
     * @param escape
     *            Escape 문자
     * @param strictQuotes
     * @param creator
     *            {@link String}[]를 전달받아 데이터를 생성하는 함수.<br>
     *            객체의 <code>setter</code> 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b><code>NOT nullable</code></b>)</font>
     * @param close
     *            {@link InputStream} 자동 종료 여부
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset, int skip //
            , char separator, char quotechar, char escape, boolean strictQuotes //
            , Function<String[], E> creator, boolean close) throws IOException {
        return readAsList(inputStream, charset, skip, separator, quotechar, escape, strictQuotes, CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, creator, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 12.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     * @param inputStream
     *            데이터 입력
     * @param charset
     *            데이터 문자열 셋
     * @param skip
     *            생략할 라인 개수
     * @param separator
     *            데이터 구분자
     * @param quotechar
     *            문자열 묶음 문자
     * @param escape
     *            Escape 문자
     * @param type
     *            데이터 타입. (<b><code>NOT nullable</code></b>)
     * @param close
     *            {@link InputStream} 자동 종료 여부
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset, int skip, char separator, char quotechar, char escape, Class<E> type, boolean close)
            throws IOException {
        return readAsList(inputStream, charset, skip, separator, quotechar, escape, defaultCreator(type), close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 12.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     * @param inputStream
     *            데이터 입력
     * @param charset
     *            데이터 문자열 셋
     * @param skip
     *            생략할 라인 개수
     * @param separator
     *            데이터 구분자
     * @param quotechar
     *            문자열 묶음 문자
     * @param escape
     *            Escape 문자
     * @param creator
     *            {@link String}[]를 전달받아 데이터를 생성하는 함수.<br>
     *            객체의 <code>setter</code> 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b><code>NOT nullable</code></b>)</font>
     * @param close
     *            {@link InputStream} 자동 종료 여부
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset, int skip, char separator, char quotechar, char escape, Function<String[], E> creator,
            boolean close) throws IOException {
        return readAsList(inputStream, charset, skip, separator, quotechar, escape, CsvConfig.DEFAULT_STRICT_QUOTES, CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, creator, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 12.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     * @param inputStream
     *            데이터 입력
     * @param charset
     *            데이터 문자열 셋
     * @param skip
     *            생략할 라인 개수
     * @param separator
     *            데이터 구분자
     * @param quotechar
     *            문자열 묶음 문자
     * @param type
     *            데이터 타입. (<b><code>NOT nullable</code></b>)
     * @param close
     *            {@link InputStream} 자동 종료 여부
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset, int skip, char separator, char quotechar, Class<E> type, boolean close)
            throws IOException {
        return readAsList(inputStream, charset, skip, separator, quotechar, defaultCreator(type), close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 12.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     * @param inputStream
     *            데이터 입력
     * @param charset
     *            데이터 문자열 셋
     * @param skip
     *            생략할 라인 개수
     * @param separator
     *            데이터 구분자
     * @param quotechar
     *            문자열 묶음 문자
     * @param creator
     *            {@link String}[]를 전달받아 데이터를 생성하는 함수.<br>
     *            객체의 <code>setter</code> 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b><code>NOT nullable</code></b>)</font>
     * @param close
     *            {@link InputStream} 자동 종료 여부
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset, int skip, char separator, char quotechar, Function<String[], E> creator, boolean close)
            throws IOException {
        return readAsList(inputStream, charset, skip, separator, quotechar, CsvConfig.DEFAULT_ESCAPE_CHARACTER, CsvConfig.DEFAULT_STRICT_QUOTES,
                CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, creator, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 12.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     * @param inputStream
     *            데이터 입력
     * @param charset
     *            데이터 문자열 셋
     * @param skip
     *            생략할 라인 개수
     * @param separator
     *            데이터 구분자
     * @param type
     *            데이터 타입. (<b><code>NOT nullable</code></b>)
     * @param close
     *            {@link InputStream} 자동 종료 여부
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset, int skip, char separator, Class<E> type, boolean close) throws IOException {
        return readAsList(inputStream, charset, skip, separator, defaultCreator(type), close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 12.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     * @param inputStream
     *            데이터 입력
     * @param charset
     *            데이터 문자열 셋
     * @param skip
     *            생략할 라인 개수
     * @param separator
     *            데이터 구분자
     * @param creator
     *            {@link String}[]를 전달받아 데이터를 생성하는 함수.<br>
     *            객체의 <code>setter</code> 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b><code>NOT nullable</code></b>)</font>
     * @param close
     *            {@link InputStream} 자동 종료 여부
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset, int skip, char separator, Function<String[], E> creator, boolean close)
            throws IOException {
        return readAsList(inputStream, charset, skip, separator, CsvConfig.DEFAULT_QUOTE_CHARACTER, CsvConfig.DEFAULT_ESCAPE_CHARACTER, CsvConfig.DEFAULT_STRICT_QUOTES,
                CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, creator, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 11.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     *            CSV 파일을 읽어서 생성할 데이터 모델. {@link ReadAt}이 설정된 메소드를 호출하여 데이터를 설정합니다.
     * @param inputStream
     *            CSV 데이터 (<b><code>NOT nullable</code></b>)
     * @param config
     *            CSV 파일을 읽기 위한 설정 (<b><code>NOT nullable</code></b>)
     * @param type
     *            데이터 타입. (<b><code>NOT nullable</code></b>)
     * @param close
     *            {@link InputStream} 여부
     * @return
     *
     * @since 2021. 11. 11.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see ReadAt
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, CsvFileConfig config, Class<E> type, boolean close) throws IOException {
        return readAsList(new InputStreamReader(inputStream, config.getCharset()), config, type, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 11.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     *            CSV 파일을 읽어서 생성할 데이터 모델. {@link ReadAt}이 설정된 메소드를 호출하여 데이터를 설정합니다.
     * @param inputStream
     *            CSV 데이터 (<b><code>NOT nullable</code></b>)
     * @param config
     *            CSV 파일을 읽기 위한 설정 (<b><code>NOT nullable</code></b>)
     * @param creator
     *            {@link String}[]를 전달받아 데이터를 생성하는 함수.<br>
     *            객체의 <code>setter</code> 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b><code>NOT nullable</code></b>)</font>
     * @param close
     *            {@link InputStream} 자동 종료 여부
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 11.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see ReadAt
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, CsvFileConfig config, Function<String[], E> creator, boolean close) throws IOException {
        return readAsList(new InputStreamReader(inputStream, config.getCharset()), config, creator, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 12.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     * @param inputStream
     *            데이터 입력
     * @param skip
     *            생략할 라인 개수
     * @param separator
     *            데이터 구분자
     * @param quotechar
     *            문자열 묶음 문자
     * @param escape
     *            Escape 문자
     * @param strictQuotes
     * @param ignoreLeadingWhiteSpace
     * @param type
     *            데이터 타입. (<b><code>NOT nullable</code></b>)
     * @param close
     *            {@link InputStream} 자동 종료 여부
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, int skip //
            , char separator, char quotechar, char escape, boolean strictQuotes, boolean ignoreLeadingWhiteSpace //
            , Class<E> type, boolean close) throws IOException {
        return readAsList(inputStream, skip, separator, quotechar, escape, strictQuotes, ignoreLeadingWhiteSpace, defaultCreator(type), close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 12.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     * @param inputStream
     *            데이터 입력
     * @param skip
     *            생략할 라인 개수
     * @param separator
     *            데이터 구분자
     * @param quotechar
     *            문자열 묶음 문자
     * @param escape
     *            Escape 문자
     * @param strictQuotes
     * @param ignoreLeadingWhiteSpace
     * @param creator
     *            {@link String}[]를 전달받아 데이터를 생성하는 함수.<br>
     *            객체의 <code>setter</code> 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b><code>NOT nullable</code></b>)</font>
     * @param close
     *            {@link InputStream} 자동 종료 여부
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, int skip //
            , char separator, char quotechar, char escape, boolean strictQuotes, boolean ignoreLeadingWhiteSpace //
            , Function<String[], E> creator, boolean close) throws IOException {
        return readAsList(inputStream, Charset.defaultCharset(), skip, separator, quotechar, escape, strictQuotes, ignoreLeadingWhiteSpace, creator, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 12.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     * @param inputStream
     *            데이터 입력
     * @param skip
     *            생략할 라인 개수
     * @param separator
     *            데이터 구분자
     * @param quotechar
     *            문자열 묶음 문자
     * @param escape
     *            Escape 문자
     * @param strictQuotes
     * @param creator
     *            데이터 타입. (<b><code>NOT nullable</code></b>)
     * @param close
     *            {@link InputStream} 자동 종료 여부
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, int skip, char separator, char quotechar, char escape, boolean strictQuotes //
            , Class<E> type, boolean close) throws IOException {
        return readAsList(inputStream, skip, separator, quotechar, escape, strictQuotes, defaultCreator(type), close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 12.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     * @param inputStream
     *            데이터 입력
     * @param skip
     *            생략할 라인 개수
     * @param separator
     *            데이터 구분자
     * @param quotechar
     *            문자열 묶음 문자
     * @param escape
     *            Escape 문자
     * @param strictQuotes
     * @param creator
     *            {@link String}[]를 전달받아 데이터를 생성하는 함수.<br>
     *            객체의 <code>setter</code> 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b><code>NOT nullable</code></b>)</font>
     * @param close
     *            {@link InputStream} 자동 종료 여부
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, int skip, char separator, char quotechar, char escape, boolean strictQuotes //
            , Function<String[], E> creator, boolean close) throws IOException {
        return readAsList(inputStream, Charset.defaultCharset(), skip, separator, quotechar, escape, strictQuotes, CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, creator, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 12.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     * @param inputStream
     *            데이터 입력
     * @param skip
     *            생략할 라인 개수
     * @param separator
     *            데이터 구분자
     * @param quotechar
     *            문자열 묶음 문자
     * @param escape
     *            Escape 문자
     * @param type
     *            데이터 타입. (<b><code>NOT nullable</code></b>)
     * @param close
     *            {@link InputStream} 자동 종료 여부
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, int skip, char separator, char quotechar, char escape//
            , Class<E> type, boolean close) throws IOException {
        return readAsList(inputStream, skip, separator, quotechar, escape, defaultCreator(type), close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 12.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     * @param inputStream
     *            데이터 입력
     * @param skip
     *            생략할 라인 개수
     * @param separator
     *            데이터 구분자
     * @param quotechar
     *            문자열 묶음 문자
     * @param escape
     *            Escape 문자
     * @param creator
     *            {@link String}[]를 전달받아 데이터를 생성하는 함수.<br>
     *            객체의 <code>setter</code> 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b><code>NOT nullable</code></b>)</font>
     * @param close
     *            {@link InputStream} 자동 종료 여부
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, int skip, char separator, char quotechar, char escape//
            , Function<String[], E> creator, boolean close) throws IOException {
        return readAsList(inputStream, Charset.defaultCharset(), skip, separator, quotechar, escape, CsvConfig.DEFAULT_STRICT_QUOTES, CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE,
                creator, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 12.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     * @param inputStream
     *            데이터 입력
     * @param skip
     *            생략할 라인 개수
     * @param separator
     *            데이터 구분자
     * @param quotechar
     *            문자열 묶음 문자
     * @param type
     *            데이터 타입. (<b><code>NOT nullable</code></b>)
     * @param close
     *            {@link InputStream} 자동 종료 여부
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, int skip, char separator, char quotechar, Class<E> type, boolean close) throws IOException {
        return readAsList(inputStream, skip, separator, quotechar, defaultCreator(type), close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 12.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     * @param inputStream
     *            데이터 입력
     * @param skip
     *            생략할 라인 개수
     * @param separator
     *            데이터 구분자
     * @param quotechar
     *            문자열 묶음 문자
     * @param creator
     *            {@link String}[]를 전달받아 데이터를 생성하는 함수.<br>
     *            객체의 <code>setter</code> 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b><code>NOT nullable</code></b>)</font>
     * @param close
     *            {@link InputStream} 자동 종료 여부
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, int skip, char separator, char quotechar, Function<String[], E> creator, boolean close)
            throws IOException {
        return readAsList(inputStream, Charset.defaultCharset(), skip, separator, quotechar, CsvConfig.DEFAULT_ESCAPE_CHARACTER, CsvConfig.DEFAULT_STRICT_QUOTES,
                CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, creator, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 12.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     * @param inputStream
     *            데이터 입력
     * @param skip
     *            생략할 라인 개수
     * @param separator
     *            데이터 구분자
     * @param type
     *            데이터 타입. (<b><code>NOT nullable</code></b>)
     * @param close
     *            {@link InputStream} 자동 종료 여부
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, int skip, char separator, Class<E> type, boolean close) throws IOException {
        return readAsList(inputStream, skip, separator, defaultCreator(type), close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 12.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     * @param inputStream
     *            데이터 입력
     * @param skip
     *            생략할 라인 개수
     * @param separator
     *            데이터 구분자
     * @param creator
     *            {@link String}[]를 전달받아 데이터를 생성하는 함수.<br>
     *            객체의 <code>setter</code> 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b><code>NOT nullable</code></b>)</font>
     * @param close
     *            {@link InputStream} 자동 종료 여부
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, int skip, char separator, Function<String[], E> creator, boolean close) throws IOException {
        return readAsList(inputStream, Charset.defaultCharset(), skip, separator, CsvConfig.DEFAULT_QUOTE_CHARACTER, CsvConfig.DEFAULT_ESCAPE_CHARACTER,
                CsvConfig.DEFAULT_STRICT_QUOTES, CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, creator, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 11.        박준홍         최초 작성
     * </pre>
     *
     * @param <E>
     *            CSV 파일을 읽어서 생성할 데이터 모델. {@link ReadAt}이 설정된 메소드를 호출하여 데이터를 설정합니다.
     * @param reader
     *            CSV 데이터 (<b><code>NOT nullable</code></b>)
     * @param config
     *            CSV 파일을 읽기 위한 설정 (<b><code>NOT nullable</code></b>)
     * @param type
     *            데이터 타입. (<b><code>NOT nullable</code></b>)
     * @param close
     *            {@link InputStream} 여부
     * @return
     *
     * @since 2021. 11. 11.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(Reader reader, CsvFileConfig config, Class<E> type, boolean close) throws IOException {
        return readAsList(newCSVReader(reader, config), type, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 11.        박준홍         최초 작성
     * </pre>
     *
     * @param <E>
     *            CSV 파일을 읽어서 생성할 데이터 모델. {@link ReadAt}이 설정된 메소드를 호출하여 데이터를 설정합니다.
     * @param reader
     *            CSV 데이터 (<b><code>NOT nullable</code></b>)
     * @param config
     *            CSV 파일을 읽기 위한 설정 (<b><code>NOT nullable</code></b>)
     * @param creator
     *            {@link String}[]를 전달받아 데이터를 생성하는 함수.<br>
     *            객체의 <code>setter</code> 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b><code>NOT nullable</code></b>)</font>
     * @param close
     *            {@link InputStream} 자동 종료 여부
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 11.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see ReadAt
     */
    public static <E> Result<List<E>> readAsList(Reader reader, CsvFileConfig config, Function<String[], E> creator, boolean close) throws IOException {
        return readAsList(newCSVReader(reader, config), creator, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 12.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     * @param reader
     *            데이터 입력
     * @param skip
     *            생략할 라인 개수
     * @param separator
     *            데이터 구분자
     * @param quotechar
     *            문자열 묶음 문자
     * @param escape
     *            Escape 문자
     * @param strictQuotes
     * @param ignoreLeadingWhiteSpace
     * @param type
     *            데이터 타입. (<b><code>NOT nullable</code></b>)
     * @param close
     *            {@link InputStream} 자동 종료 여부
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(Reader reader, int skip //
            , char separator, char quotechar, char escape, boolean strictQuotes, boolean ignoreLeadingWhiteSpace //
            , Class<E> type, boolean close) throws IOException {
        return readAsList(reader, skip, separator, quotechar, escape, strictQuotes, ignoreLeadingWhiteSpace, defaultCreator(type), close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 12.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     * @param reader
     *            데이터 입력
     * @param skip
     *            생략할 라인 개수
     * @param separator
     *            데이터 구분자
     * @param quotechar
     *            문자열 묶음 문자
     * @param escape
     *            Escape 문자
     * @param strictQuotes
     * @param ignoreLeadingWhiteSpace
     * @param creator
     *            {@link String}[]를 전달받아 데이터를 생성하는 함수.<br>
     *            객체의 <code>setter</code> 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b><code>NOT nullable</code></b>)</font>
     * @param close
     *            {@link InputStream} 자동 종료 여부
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(Reader reader, int skip //
            , char separator, char quotechar, char escape, boolean strictQuotes, boolean ignoreLeadingWhiteSpace //
            , Function<String[], E> creator, boolean close) throws IOException {
        return readAsList(reader, new CsvFileConfig(separator, quotechar, escape, strictQuotes, ignoreLeadingWhiteSpace, skip, Charset.defaultCharset()), creator, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 12.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     * @param reader
     *            데이터 입력
     * @param skip
     *            생략할 라인 개수
     * @param separator
     *            데이터 구분자
     * @param quotechar
     *            문자열 묶음 문자
     * @param escape
     *            Escape 문자
     * @param strictQuotes
     * @param type
     *            데이터 타입. (<b><code>NOT nullable</code></b>)
     * @param close
     *            {@link InputStream} 자동 종료 여부
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(Reader reader, int skip //
            , char separator, char quotechar, char escape, boolean strictQuotes //
            , Class<E> type, boolean close) throws IOException {
        return readAsList(reader, skip, separator, quotechar, escape, strictQuotes, defaultCreator(type), close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 12.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     * @param reader
     *            데이터 입력
     * @param skip
     *            생략할 라인 개수
     * @param separator
     *            데이터 구분자
     * @param quotechar
     *            문자열 묶음 문자
     * @param escape
     *            Escape 문자
     * @param strictQuotes
     * @param creator
     *            {@link String}[]를 전달받아 데이터를 생성하는 함수.<br>
     *            객체의 <code>setter</code> 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b><code>NOT nullable</code></b>)</font>
     * @param close
     *            {@link InputStream} 자동 종료 여부
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(Reader reader, int skip //
            , char separator, char quotechar, char escape, boolean strictQuotes //
            , Function<String[], E> creator, boolean close) throws IOException {
        return readAsList(reader, skip, separator, quotechar, escape, strictQuotes, CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, creator, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 12.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     * @param reader
     *            데이터 입력
     * @param skip
     *            생략할 라인 개수
     * @param separator
     *            데이터 구분자
     * @param quotechar
     *            문자열 묶음 문자
     * @param escape
     *            Escape 문자
     * @param type
     *            데이터 타입. (<b><code>NOT nullable</code></b>)
     * @param close
     *            {@link InputStream} 자동 종료 여부
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(Reader reader, int skip, char separator, char quotechar, char escape, Class<E> type, boolean close) throws IOException {
        return readAsList(reader, skip, separator, quotechar, escape, defaultCreator(type), close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 12.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     * @param reader
     *            데이터 입력
     * @param skip
     *            생략할 라인 개수
     * @param separator
     *            데이터 구분자
     * @param quotechar
     *            문자열 묶음 문자
     * @param escape
     *            Escape 문자
     * @param creator
     *            {@link String}[]를 전달받아 데이터를 생성하는 함수.<br>
     *            객체의 <code>setter</code> 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b><code>NOT nullable</code></b>)</font>
     * @param close
     *            {@link InputStream} 자동 종료 여부
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(Reader reader, int skip, char separator, char quotechar, char escape, Function<String[], E> creator, boolean close)
            throws IOException {
        return readAsList(reader, skip, separator, quotechar, escape, CsvConfig.DEFAULT_STRICT_QUOTES, CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, creator, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 12.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     * @param reader
     *            데이터 입력
     * @param skip
     *            생략할 라인 개수
     * @param separator
     *            데이터 구분자
     * @param quotechar
     *            문자열 묶음 문자
     * @param type
     *            데이터 타입. (<b><code>NOT nullable</code></b>)
     * @param close
     *            {@link InputStream} 자동 종료 여부
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(Reader reader, int skip, char separator, char quotechar, Class<E> type, boolean close) throws IOException {
        return readAsList(reader, skip, separator, quotechar, defaultCreator(type), close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 12.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     * @param reader
     *            데이터 입력
     * @param skip
     *            생략할 라인 개수
     * @param separator
     *            데이터 구분자
     * @param quotechar
     *            문자열 묶음 문자
     * @param creator
     *            {@link String}[]를 전달받아 데이터를 생성하는 함수.<br>
     *            객체의 <code>setter</code> 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b><code>NOT nullable</code></b>)</font>
     * @param close
     *            {@link InputStream} 자동 종료 여부
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(Reader reader, int skip, char separator, char quotechar, Function<String[], E> creator, boolean close) throws IOException {
        return readAsList(reader, skip, separator, quotechar, CsvConfig.DEFAULT_ESCAPE_CHARACTER, CsvConfig.DEFAULT_STRICT_QUOTES, CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE,
                creator, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 12.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     * @param reader
     *            데이터 입력
     * @param skip
     *            생략할 라인 개수
     * @param separator
     *            데이터 구분자
     * @param type
     *            데이터 타입. (<b><code>NOT nullable</code></b>)
     * @param close
     *            {@link InputStream} 자동 종료 여부
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(Reader reader, int skip, char separator, Class<E> type, boolean close) throws IOException {
        return readAsList(reader, skip, separator, defaultCreator(type), close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 12.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     * @param reader
     *            데이터 입력
     * @param skip
     *            생략할 라인 개수
     * @param separator
     *            데이터 구분자
     * @param creator
     *            {@link String}[]를 전달받아 데이터를 생성하는 함수.<br>
     *            객체의 <code>setter</code> 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b><code>NOT nullable</code></b>)</font>
     * @param close
     *            {@link InputStream} 자동 종료 여부
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(Reader reader, int skip, char separator, Function<String[], E> creator, boolean close) throws IOException {
        return readAsList(reader, skip, separator, CsvConfig.DEFAULT_QUOTE_CHARACTER, CsvConfig.DEFAULT_ESCAPE_CHARACTER, CsvConfig.DEFAULT_STRICT_QUOTES,
                CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, creator, close);
    }
}
