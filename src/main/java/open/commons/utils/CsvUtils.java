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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import open.commons.Result;
import open.commons.csv.CsvConfig;
import open.commons.csv.CsvFileConfig;
import open.commons.csv.CsvWriteConfig;
import open.commons.csv.ReadAt;
import open.commons.csv.WriteAt;
import open.commons.test.StopWatch;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

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
     * 객체를 {@link String}[]로 변환하는 함수를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 3. 17.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static final <E> Function<E, String[]> defaultCreator() {
        return object -> {
            if (object == null) {
                throw new NullPointerException("객체는 null 일 수 없습니다.");
            }

            Class<?> type = object.getClass();
            @SuppressWarnings("unchecked")
            List<Method> methods = AnnotationUtils.getAnnotatedMethodsAllHierarchy(type, WriteAt.class);

            // 반환할 데이터
            String[] data = ArrayUtils.initArray(methods.size(), "");

            methods.stream() //
                    .forEach(m -> {
                        WriteAt anno = m.getAnnotation(WriteAt.class);
                        final int index = anno.index();
                        boolean accessible = m.isAccessible();
                        try {
                            // 접근 허용
                            m.setAccessible(true);

                            Object value = m.invoke(object);

                            if (value != null) {
                                data[index] = value.toString();
                            } else if (anno.nullIsEmpty()) {
                                data[index] = "";
                            } else {
                                data[index] = null;
                            }
                        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | IndexOutOfBoundsException e) {
                            String errMsg = String.format("'%s' 객체의 값을 배열로 변환하는 도중 에러가 발생하였습니다. 배열크기: %s, 메소드=%s, 위치=%s", type.getName(), data.length, m.getName(), index);
                            logger.error(errMsg, e);
                            throw ExceptionUtils.newException(RuntimeException.class, e, errMsg);
                        } finally {
                            // 접근성 복구
                            m.setAccessible(accessible);
                        }
                    });

            return data;
        };
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
                            String value = null;
                            if (index < arr.length) {
                                boolean accessible = m.isAccessible();
                                try {
                                    if (m.getParameterTypes().length != 1) {
                                        throw new UnsupportedOperationException(String.format("'%s'가 설정된 메소드는 반드시 파라미터가 1개이어야 합니다. 메소드=%s, 파라미터개수=%,d",
                                                ReadAt.class.getCanonicalName(), m, m.getParameterTypes().length));
                                    }

                                    // 접근허용
                                    m.setAccessible(true);

                                    // 데이터 변환
                                    value = FunctionUtils.runIf(arr[index], s -> s != null, (Function<String, String>) s -> s.trim(), (String) null);
                                    Class<?> argType = m.getParameterTypes()[0];
                                    Object arg = null;
                                    if (String.class.equals(argType)) {
                                        arg = value;
                                    } else if (!StringUtils.isNullOrEmptyString(value)) {
                                        arg = ConvertUtils.toPrimitiveTypeValue(argType, value, anno.unsigned());
                                    }

                                    // 객체에 설정
                                    if (arg != null) {
                                        m.invoke(o, arg);
                                    }
                                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | UnsupportedOperationException e) {
                                    String errMsg = String.format("'%s' 객체의 값을 설정하는 도중 에러가 발생하였습니다. 메소드=%s, 값=%s", type.getName(), m.getName(), arr[index]);
                                    logger.error(errMsg, e);
                                    throw ExceptionUtils.newException(RuntimeException.class, e, errMsg);
                                } finally {
                                    // 접근성 복구
                                    m.setAccessible(accessible);
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
     * CSV 데이터를 읽을 {@link Reader}, CSV 설정정보를 이용하여 {@link CSVReader} 객체를 제공합니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 12.        박준홍         최초 작성
     * </pre>
     *
     * @param writer
     *            CSV 데이터 제공자
     * @param config
     *            CSV 설정
     * @return
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    private static CSVWriter newCSVWriter(Writer writer, CsvWriteConfig config) {
        return new CSVWriter(writer, config.getSeparator(), config.getQuotechar(), config.getEscape());
    }

    /**
     * 여러 개의 객체의 데이터를 {@link String}[][]로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 3. 17.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     * @param objects
     *            데이터
     * @return
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * @see #objectsToArray(Collection, Function)
     */
    public static <E> String[][] objectsToArray(Collection<E> objects) {
        return objectsToArray((Collection<E>) objects, defaultCreator());
    }

    /**
     * 여러 개의 객체의 데이터를 {@link String}[][]로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 3. 17.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     * @param objects
     *            데이터
     * @param creator
     *            {@link String}[] 생성 함수
     * @return
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> String[][] objectsToArray(Collection<E> objects, Function<E, String[]> creator) {
        String[][] array2d = new String[objects.size()][];
        AtomicInteger idx = new AtomicInteger(0);
        objects.forEach(object -> {
            array2d[idx.getAndIncrement()] = objectToArray(object, creator);
        });

        return array2d;
    }

    /**
     * 여러 객체의 데이터를 {@link String}[][]로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 3. 17.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     * @param objects
     *            데이터
     * @return
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    @SuppressWarnings("unchecked")
    public static <E> String[][] objectsToArray(E... objects) {
        return objectsToArray(Arrays.asList(objects), defaultCreator());
    }

    /**
     * 여러 개의 객체의 데이터를 {@link String}[][]로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 3. 17.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     * @param creator
     *            {@link String}[] 생성 함수
     * @param objects
     *            데이터
     * @return
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see #objectsToArray(Collection, Function)
     */
    @SuppressWarnings("unchecked")
    public static <E> String[][] objectsToArray(Function<E, String[]> creator, E... objects) {
        return objectsToArray(Arrays.asList(objects), creator);
    }

    /**
     * 주어진 객체의 데이터를 {@link String}[]로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 3. 17.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     * @param object
     *            데이터
     * @return
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see #objectToArray(Object, Function)
     */
    public static <E> String[] objectToArray(E object) {
        return objectToArray(object, defaultCreator());
    }

    /**
     * 주어진 객체의 데이터를 {@link String}[]로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 3. 17.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     * @param object
     *            데이터
     * @param creator
     *            {@link String}[] 생성 함수
     * @return
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> String[] objectToArray(E object, Function<E, String[]> creator) {
        return creator.apply(object);
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
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     *
     * @since 2021. 11. 11.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(CSVReader reader, Class<E> type, boolean close) throws IOException {
        return readAsList(reader, defaultCreator(type), (Consumer<E>) null, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 17.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     *            CSV 파일을 읽어서 생성할 데이터 모델. {@link ReadAt}이 설정된 메소드를 호출하여 데이터를 설정합니다.
     * @param reader
     *            CSV 데이터 (<b><code>NOT nullable</code></b>)
     * @param type
     *            데이터 타입. (<b><code>NOT nullable</code></b>)
     * @param afterCreation
     *            객체 생성 후 작업.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 17.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(CSVReader reader, Class<E> type, Consumer<E> afterCreation, boolean close) throws IOException {
        return readAsList(reader, defaultCreator(type), afterCreation, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.        박준홍         최초 작성
     * </pre>
     *
     * @param <E>
     *            CSV 파일을 읽어서 생성할 데이터 모델. {@link ReadAt}이 설정된 메소드를 호출하여 데이터를 설정합니다.
     * @param reader
     *            CSV 데이터 (<b><code>NOT nullable</code></b>)
     * @param type
     *            데이터 타입. (<b><code>NOT nullable</code></b>)
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(CSVReader reader, Class<E> type, Predicate<E> validator, boolean close) throws IOException {
        return readAsList(reader, defaultCreator(type), validator, null, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     박준홍         최초 작성
     * </pre>
     *
     * @param <E>
     *            CSV 파일을 읽어서 생성할 데이터 모델. {@link ReadAt}이 설정된 메소드를 호출하여 데이터를 설정합니다.
     * @param reader
     *            CSV 데이터 (<b><code>NOT nullable</code></b>)
     * @param type
     *            데이터 타입. (<b><code>NOT nullable</code></b>)
     * @param validator
     *            데이터 검증 함수.
     * @param afterCreation
     *            객체 생성 후 작업.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(CSVReader reader, Class<E> type, Predicate<E> validator, Consumer<E> afterCreation, boolean close) throws IOException {
        return readAsList(reader, defaultCreator(type), validator, afterCreation, close);
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
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
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
        return readAsList(reader, creator, (Consumer<E>) null, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 17.		박준홍			최초 작성
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
     * @param afterCreation
     *            객체 생성 후 작업.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 17.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(CSVReader reader, Function<String[], E> creator, Consumer<E> afterCreation, boolean close) throws IOException {
        return readAsList(reader, creator, (Predicate<E>) null, afterCreation, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.        박준홍         최초 작성
     * </pre>
     *
     * @param <E>
     *            CSV 파일을 읽어서 생성할 데이터 모델. {@link ReadAt}이 설정된 메소드를 호출하여 데이터를 설정합니다.
     * @param reader
     *            CSV 데이터 (<b><code>NOT nullable</code></b>)
     * @param creator
     *            {@link String}[]를 전달받아 데이터를 생성하는 함수.<br>
     *            객체의 <code>setter</code> 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, Predicate, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b><code>NOT nullable</code></b>)</font>
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see ReadAt
     */
    public static <E> Result<List<E>> readAsList(CSVReader reader, Function<String[], E> creator, Predicate<E> validator, boolean close) throws IOException {
        return readAsList(reader, creator, validator, null, close);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     박준홍         최초 작성
     * </pre>
     *
     * @param <E>
     *            CSV 파일을 읽어서 생성할 데이터 모델. {@link ReadAt}이 설정된 메소드를 호출하여 데이터를 설정합니다.
     * @param reader
     *            CSV 데이터 (<b><code>NOT nullable</code></b>)
     * @param creator
     *            {@link String}[]를 전달받아 데이터를 생성하는 함수.<br>
     *            객체의 <code>setter</code> 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, Predicate, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b><code>NOT nullable</code></b>)</font>
     * @param validator
     *            데이터 검증 함수.
     * @param afterCreation
     *            객체 생성 후 작업.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 30.
     * @version _._._
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(CSVReader reader, Function<String[], E> creator, Predicate<E> validator, Consumer<E> afterCreation, boolean close)
            throws IOException {

        StopWatch watch = new StopWatch();
        watch.start();

        List<E> data = new ArrayList<>();
        try {
            String[] readline = null;
            E e = null;
            while ((readline = reader.readNext()) != null) {
                // 객체 생성
                e = creator.apply(readline);
                // 객체 검증.
                if (validator != null && !validator.test(e)) {
                    continue;
                }
                // 객체 후처리.
                if (afterCreation != null) {
                    afterCreation.accept(e);
                }
                // 추가
                data.add(e);
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
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
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
        return readAsList(inputStream, charset, CsvFileConfig.DEFAULT_SKIP_LINE_COUNT, separator, quotechar, escape, strictQuotes, ignoreLeadingWhiteSpace, type, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     박준홍         최초 작성
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
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset //
            , char separator, char quotechar, char escape, boolean strictQuotes, boolean ignoreLeadingWhiteSpace //
            , Class<E> type, Predicate<E> validator, boolean close) throws IOException {
        return readAsList(inputStream, charset, CsvFileConfig.DEFAULT_SKIP_LINE_COUNT, separator, quotechar, escape, strictQuotes, ignoreLeadingWhiteSpace, type, validator, close);
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
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
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
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     박준홍         최초 작성
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
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, Predicate, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b><code>NOT nullable</code></b>)</font>
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset //
            , char separator, char quotechar, char escape, boolean strictQuotes, boolean ignoreLeadingWhiteSpace //
            , Function<String[], E> creator, Predicate<E> validator, boolean close) throws IOException {
        return readAsList(inputStream, charset, 0, separator, quotechar, escape, strictQuotes, ignoreLeadingWhiteSpace, creator, validator, close);
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
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
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
        return readAsList(inputStream, charset, CsvFileConfig.DEFAULT_SKIP_LINE_COUNT, separator, quotechar, escape, strictQuotes, CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE,
                type, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     박준홍         최초 작성
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
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset //
            , char separator, char quotechar, char escape, boolean strictQuotes //
            , Class<E> type, Predicate<E> validator, boolean close) throws IOException {
        return readAsList(inputStream, charset, CsvFileConfig.DEFAULT_SKIP_LINE_COUNT, separator, quotechar, escape, strictQuotes, CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE,
                type, validator, close);
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
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
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
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     박준홍         최초 작성
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
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, Predicate, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b><code>NOT nullable</code></b>)</font>
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset //
            , char separator, char quotechar, char escape, boolean strictQuotes //
            , Function<String[], E> creator, Predicate<E> validator, boolean close) throws IOException {
        return readAsList(inputStream, charset, 0, separator, quotechar, escape, strictQuotes, CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, creator, validator, close);
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
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset, char separator, char quotechar, char escape, Class<E> type, boolean close)
            throws IOException {
        return readAsList(inputStream, charset, CsvFileConfig.DEFAULT_SKIP_LINE_COUNT, separator, quotechar, escape, CsvConfig.DEFAULT_STRICT_QUOTES,
                CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, type, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     박준홍         최초 작성
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
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset, char separator, char quotechar, char escape, Class<E> type, Predicate<E> validator,
            boolean close) throws IOException {
        return readAsList(inputStream, charset, CsvFileConfig.DEFAULT_SKIP_LINE_COUNT, separator, quotechar, escape, CsvConfig.DEFAULT_STRICT_QUOTES,
                CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, type, validator, close);
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
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
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
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     박준홍         최초 작성
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
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, Predicate, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b><code>NOT nullable</code></b>)</font>
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset, char separator, char quotechar, char escape, Function<String[], E> creator,
            Predicate<E> validator, boolean close) throws IOException {
        return readAsList(inputStream, charset, 0, separator, quotechar, escape, CsvConfig.DEFAULT_STRICT_QUOTES, CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, creator, validator,
                close);
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
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset, char separator, char quotechar, Class<E> type, boolean close) throws IOException {
        return readAsList(inputStream, charset, CsvFileConfig.DEFAULT_SKIP_LINE_COUNT, separator, quotechar, CsvConfig.DEFAULT_ESCAPE_CHARACTER, CsvConfig.DEFAULT_STRICT_QUOTES,
                CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, type, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     박준홍         최초 작성
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
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset, char separator, char quotechar, Class<E> type, Predicate<E> validator, boolean close)
            throws IOException {
        return readAsList(inputStream, charset, CsvFileConfig.DEFAULT_SKIP_LINE_COUNT, separator, quotechar, CsvConfig.DEFAULT_ESCAPE_CHARACTER, CsvConfig.DEFAULT_STRICT_QUOTES,
                CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, type, validator, close);
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
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
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
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     박준홍         최초 작성
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
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, Predicate, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b><code>NOT nullable</code></b>)</font>
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset, char separator, char quotechar, Function<String[], E> creator, Predicate<E> validator,
            boolean close) throws IOException {
        return readAsList(inputStream, charset, 0, separator, quotechar, CsvConfig.DEFAULT_ESCAPE_CHARACTER, CsvConfig.DEFAULT_STRICT_QUOTES,
                CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, creator, validator, close);
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
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset, char separator, Class<E> type, boolean close) throws IOException {
        return readAsList(inputStream, charset, CsvFileConfig.DEFAULT_SKIP_LINE_COUNT, separator, CsvConfig.DEFAULT_QUOTE_CHARACTER, CsvConfig.DEFAULT_ESCAPE_CHARACTER,
                CsvConfig.DEFAULT_STRICT_QUOTES, CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, type, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     박준홍         최초 작성
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
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset, char separator, Class<E> type, Predicate<E> validator, boolean close)
            throws IOException {
        return readAsList(inputStream, charset, CsvFileConfig.DEFAULT_SKIP_LINE_COUNT, separator, CsvConfig.DEFAULT_QUOTE_CHARACTER, CsvConfig.DEFAULT_ESCAPE_CHARACTER,
                CsvConfig.DEFAULT_STRICT_QUOTES, CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, type, validator, close);
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
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
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
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     박준홍         최초 작성
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
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, Predicate, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b><code>NOT nullable</code></b>)</font>
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset, char separator, Function<String[], E> creator, Predicate<E> validator, boolean close)
            throws IOException {
        return readAsList(inputStream, charset, 0, separator, CsvConfig.DEFAULT_QUOTE_CHARACTER, CsvConfig.DEFAULT_ESCAPE_CHARACTER, CsvConfig.DEFAULT_STRICT_QUOTES,
                CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, creator, validator, close);
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
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
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
        return readAsList(inputStream, new CsvFileConfig(separator, quotechar, escape, strictQuotes, ignoreLeadingWhiteSpace, skip, charset), type, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 17.		박준홍			최초 작성
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
     * @param afterCreation
     *            객체 생성 후 작업.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 17.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset, int skip //
            , char separator, char quotechar, char escape, boolean strictQuotes, boolean ignoreLeadingWhiteSpace //
            , Class<E> type, Consumer<E> afterCreation, boolean close) throws IOException {
        return readAsList(inputStream, new CsvFileConfig(separator, quotechar, escape, strictQuotes, ignoreLeadingWhiteSpace, skip, charset), type, afterCreation, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     박준홍         최초 작성
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
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset, int skip //
            , char separator, char quotechar, char escape, boolean strictQuotes, boolean ignoreLeadingWhiteSpace //
            , Class<E> type, Predicate<E> validator, boolean close) throws IOException {
        return readAsList(inputStream, new CsvFileConfig(separator, quotechar, escape, strictQuotes, ignoreLeadingWhiteSpace, skip, charset), type, validator, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     박준홍         최초 작성
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
     * @param validator
     *            데이터 검증 함수.
     * @param afterCreation
     *            객체 생성 후 작업.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset, int skip //
            , char separator, char quotechar, char escape, boolean strictQuotes, boolean ignoreLeadingWhiteSpace //
            , Class<E> type, Predicate<E> validator, Consumer<E> afterCreation, boolean close) throws IOException {
        return readAsList(inputStream, new CsvFileConfig(separator, quotechar, escape, strictQuotes, ignoreLeadingWhiteSpace, skip, charset), type, validator, afterCreation,
                close);
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
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
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
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     박준홍         최초 작성
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
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, Predicate, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b><code>NOT nullable</code></b>)</font>
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset, int skip //
            , char separator, char quotechar, char escape, boolean strictQuotes, boolean ignoreLeadingWhiteSpace //
            , Function<String[], E> creator, Predicate<E> validator, boolean close) throws IOException {
        return readAsList(inputStream, new CsvFileConfig(separator, quotechar, escape, strictQuotes, ignoreLeadingWhiteSpace, skip, charset), creator, validator, close);
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
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
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
        return readAsList(inputStream, charset, skip, separator, quotechar, escape, strictQuotes, CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, type, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     박준홍         최초 작성
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
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset, int skip //
            , char separator, char quotechar, char escape, boolean strictQuotes //
            , Class<E> type, Predicate<E> validator, boolean close) throws IOException {
        return readAsList(inputStream, charset, skip, separator, quotechar, escape, strictQuotes, CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, type, validator, close);
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
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
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
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     박준홍         최초 작성
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
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, Predicate, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b><code>NOT nullable</code></b>)</font>
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset, int skip //
            , char separator, char quotechar, char escape, boolean strictQuotes //
            , Function<String[], E> creator, Predicate<E> validator, boolean close) throws IOException {
        return readAsList(inputStream, charset, skip, separator, quotechar, escape, strictQuotes, CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, creator, validator, close);
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
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset, int skip, char separator, char quotechar, char escape, Class<E> type, boolean close)
            throws IOException {
        return readAsList(inputStream, charset, skip, separator, quotechar, escape, CsvConfig.DEFAULT_STRICT_QUOTES, CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, type, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     박준홍         최초 작성
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
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset, int skip, char separator, char quotechar, char escape, Class<E> type,
            Predicate<E> validator, boolean close) throws IOException {
        return readAsList(inputStream, charset, skip, separator, quotechar, escape, CsvConfig.DEFAULT_STRICT_QUOTES, CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, type, validator,
                close);
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
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
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
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     박준홍         최초 작성
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
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, Predicate, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b><code>NOT nullable</code></b>)</font>
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset, int skip, char separator, char quotechar, char escape, Function<String[], E> creator,
            Predicate<E> validator, boolean close) throws IOException {
        return readAsList(inputStream, charset, skip, separator, quotechar, escape, CsvConfig.DEFAULT_STRICT_QUOTES, CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, creator,
                validator, close);
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
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset, int skip, char separator, char quotechar, Class<E> type, boolean close)
            throws IOException {
        return readAsList(inputStream, charset, skip, separator, quotechar, CsvConfig.DEFAULT_ESCAPE_CHARACTER, CsvConfig.DEFAULT_STRICT_QUOTES,
                CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, type, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     박준홍         최초 작성
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
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset, int skip, char separator, char quotechar, Class<E> type, Predicate<E> validator,
            boolean close) throws IOException {
        return readAsList(inputStream, charset, skip, separator, quotechar, CsvConfig.DEFAULT_ESCAPE_CHARACTER, CsvConfig.DEFAULT_STRICT_QUOTES,
                CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, type, validator, close);
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
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
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
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     박준홍         최초 작성
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
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, Predicate, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b><code>NOT nullable</code></b>)</font>
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset, int skip, char separator, char quotechar, Function<String[], E> creator,
            Predicate<E> validator, boolean close) throws IOException {
        return readAsList(inputStream, charset, skip, separator, quotechar, CsvConfig.DEFAULT_ESCAPE_CHARACTER, CsvConfig.DEFAULT_STRICT_QUOTES,
                CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, creator, validator, close);
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
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset, int skip, char separator, Class<E> type, boolean close) throws IOException {
        return readAsList(inputStream, charset, skip, separator, CsvConfig.DEFAULT_QUOTE_CHARACTER, CsvConfig.DEFAULT_ESCAPE_CHARACTER, CsvConfig.DEFAULT_STRICT_QUOTES,
                CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, type, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     박준홍         최초 작성
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
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset, int skip, char separator, Class<E> type, Predicate<E> validator, boolean close)
            throws IOException {
        return readAsList(inputStream, charset, skip, separator, CsvConfig.DEFAULT_QUOTE_CHARACTER, CsvConfig.DEFAULT_ESCAPE_CHARACTER, CsvConfig.DEFAULT_STRICT_QUOTES,
                CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, type, validator, close);
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
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
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
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     박준홍         최초 작성
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
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, Predicate, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b><code>NOT nullable</code></b>)</font>
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset, int skip, char separator, Function<String[], E> creator, Predicate<E> validator,
            boolean close) throws IOException {
        return readAsList(inputStream, charset, skip, separator, CsvConfig.DEFAULT_QUOTE_CHARACTER, CsvConfig.DEFAULT_ESCAPE_CHARACTER, CsvConfig.DEFAULT_STRICT_QUOTES,
                CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, creator, validator, close);
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
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
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
     * 2021. 11. 17.		박준홍			최초 작성
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
     * @param afterCreation
     *            객체 생성 후 작업.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     *
     * @since 2021. 11. 17.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see ReadAt
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, CsvFileConfig config, Class<E> type, Consumer<E> afterCreation, boolean close) throws IOException {
        return readAsList(new InputStreamReader(inputStream, config.getCharset()), config, type, afterCreation, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     박준홍         최초 작성
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
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see ReadAt
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, CsvFileConfig config, Class<E> type, Predicate<E> validator, boolean close) throws IOException {
        return readAsList(new InputStreamReader(inputStream, config.getCharset()), config, type, validator, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     박준홍         최초 작성
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
     * @param validator
     *            데이터 검증 함수.
     * @param afterCreation
     *            객체 생성 후 작업.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see ReadAt
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, CsvFileConfig config, Class<E> type, Predicate<E> validator, Consumer<E> afterCreation, boolean close)
            throws IOException {
        return readAsList(new InputStreamReader(inputStream, config.getCharset()), config, type, validator, afterCreation, close);
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
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
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
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     박준홍         최초 작성
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
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, Predicate, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b><code>NOT nullable</code></b>)</font>
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see ReadAt
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, CsvFileConfig config, Function<String[], E> creator, Predicate<E> validator, boolean close)
            throws IOException {
        return readAsList(new InputStreamReader(inputStream, config.getCharset()), config, creator, validator, close);
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
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
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
        return readAsList(inputStream, Charset.defaultCharset(), skip, separator, quotechar, escape, strictQuotes, ignoreLeadingWhiteSpace, type, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     박준홍         최초 작성
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
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, int skip //
            , char separator, char quotechar, char escape, boolean strictQuotes, boolean ignoreLeadingWhiteSpace //
            , Class<E> type, Predicate<E> validator, boolean close) throws IOException {
        return readAsList(inputStream, Charset.defaultCharset(), skip, separator, quotechar, escape, strictQuotes, ignoreLeadingWhiteSpace, type, validator, close);
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
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
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
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     박준홍         최초 작성
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
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, Predicate, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b><code>NOT nullable</code></b>)</font>
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, int skip //
            , char separator, char quotechar, char escape, boolean strictQuotes, boolean ignoreLeadingWhiteSpace //
            , Function<String[], E> creator, Predicate<E> validator, boolean close) throws IOException {
        return readAsList(inputStream, Charset.defaultCharset(), skip, separator, quotechar, escape, strictQuotes, ignoreLeadingWhiteSpace, creator, validator, close);
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
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, int skip, char separator, char quotechar, char escape, boolean strictQuotes //
            , Class<E> type, boolean close) throws IOException {
        return readAsList(inputStream, Charset.defaultCharset(), skip, separator, quotechar, escape, strictQuotes, CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, type, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     박준홍         최초 작성
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
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @param creator
     *            데이터 타입. (<b><code>NOT nullable</code></b>)
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, int skip, char separator, char quotechar, char escape, boolean strictQuotes //
            , Class<E> type, Predicate<E> validator, boolean close) throws IOException {
        return readAsList(inputStream, Charset.defaultCharset(), skip, separator, quotechar, escape, strictQuotes, CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, type, validator,
                close);
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
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
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
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     박준홍         최초 작성
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
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, Predicate, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b><code>NOT nullable</code></b>)</font>
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, int skip, char separator, char quotechar, char escape, boolean strictQuotes //
            , Function<String[], E> creator, Predicate<E> validator, boolean close) throws IOException {
        return readAsList(inputStream, Charset.defaultCharset(), skip, separator, quotechar, escape, strictQuotes, CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, creator, validator,
                close);
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
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, int skip, char separator, char quotechar, char escape//
            , Class<E> type, boolean close) throws IOException {
        return readAsList(inputStream, Charset.defaultCharset(), skip, separator, quotechar, escape, CsvConfig.DEFAULT_STRICT_QUOTES, CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE,
                type, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     박준홍         최초 작성
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
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, int skip, char separator, char quotechar, char escape//
            , Class<E> type, Predicate<E> validator, boolean close) throws IOException {
        return readAsList(inputStream, Charset.defaultCharset(), skip, separator, quotechar, escape, CsvConfig.DEFAULT_STRICT_QUOTES, CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE,
                type, validator, close);
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
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
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
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     박준홍         최초 작성
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
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, Predicate, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b><code>NOT nullable</code></b>)</font>
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, int skip, char separator, char quotechar, char escape//
            , Function<String[], E> creator, Predicate<E> validator, boolean close) throws IOException {
        return readAsList(inputStream, Charset.defaultCharset(), skip, separator, quotechar, escape, CsvConfig.DEFAULT_STRICT_QUOTES, CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE,
                creator, validator, close);
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
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, int skip, char separator, char quotechar, Class<E> type, boolean close) throws IOException {
        return readAsList(inputStream, Charset.defaultCharset(), skip, separator, quotechar, CsvConfig.DEFAULT_ESCAPE_CHARACTER, CsvConfig.DEFAULT_STRICT_QUOTES,
                CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, type, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     박준홍         최초 작성
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
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, int skip, char separator, char quotechar, Class<E> type, Predicate<E> validator, boolean close)
            throws IOException {
        return readAsList(inputStream, Charset.defaultCharset(), skip, separator, quotechar, CsvConfig.DEFAULT_ESCAPE_CHARACTER, CsvConfig.DEFAULT_STRICT_QUOTES,
                CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, type, validator, close);
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
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
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
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     박준홍         최초 작성
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
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, Predicate, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b><code>NOT nullable</code></b>)</font>
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, int skip, char separator, char quotechar, Function<String[], E> creator, Predicate<E> validator,
            boolean close) throws IOException {
        return readAsList(inputStream, Charset.defaultCharset(), skip, separator, quotechar, CsvConfig.DEFAULT_ESCAPE_CHARACTER, CsvConfig.DEFAULT_STRICT_QUOTES,
                CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, creator, validator, close);
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
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, int skip, char separator, Class<E> type, boolean close) throws IOException {
        readAsList(inputStream, skip, separator, defaultCreator(type), close);
        return readAsList(inputStream, Charset.defaultCharset(), skip, separator, CsvConfig.DEFAULT_QUOTE_CHARACTER, CsvConfig.DEFAULT_ESCAPE_CHARACTER,
                CsvConfig.DEFAULT_STRICT_QUOTES, CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, type, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     박준홍         최초 작성
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
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, int skip, char separator, Class<E> type, Predicate<E> validator, boolean close) throws IOException {
        readAsList(inputStream, skip, separator, defaultCreator(type), validator, close);
        return readAsList(inputStream, Charset.defaultCharset(), skip, separator, CsvConfig.DEFAULT_QUOTE_CHARACTER, CsvConfig.DEFAULT_ESCAPE_CHARACTER,
                CsvConfig.DEFAULT_STRICT_QUOTES, CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, type, validator, close);
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
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
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
     * 2022. 3. 30.     박준홍         최초 작성
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
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, Predicate, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b><code>NOT nullable</code></b>)</font>
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, int skip, char separator, Function<String[], E> creator, Predicate<E> validator, boolean close)
            throws IOException {
        return readAsList(inputStream, Charset.defaultCharset(), skip, separator, CsvConfig.DEFAULT_QUOTE_CHARACTER, CsvConfig.DEFAULT_ESCAPE_CHARACTER,
                CsvConfig.DEFAULT_STRICT_QUOTES, CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, creator, validator, close);
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
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
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
     * 2021. 11. 17.        박준홍         최초 작성
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
     * @param afterCreation
     *            객체 생성 후 작업.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     *
     * @since 2021. 11. 17.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(Reader reader, CsvFileConfig config, Class<E> type, Consumer<E> afterCreation, boolean close) throws IOException {
        return readAsList(newCSVReader(reader, config), type, afterCreation, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.        박준홍         최초 작성
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
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(Reader reader, CsvFileConfig config, Class<E> type, Predicate<E> validator, boolean close) throws IOException {
        return readAsList(newCSVReader(reader, config), type, validator, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.        박준홍         최초 작성
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
     * @param validator
     *            데이터 검증 함수.
     * @param afterCreation
     *            객체 생성 후 작업.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(Reader reader, CsvFileConfig config, Class<E> type, Predicate<E> validator, Consumer<E> afterCreation, boolean close)
            throws IOException {
        return readAsList(newCSVReader(reader, config), type, validator, afterCreation, close);
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
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
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
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.        박준홍         최초 작성
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
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, Predicate, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b><code>NOT nullable</code></b>)</font>
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see ReadAt
     */
    public static <E> Result<List<E>> readAsList(Reader reader, CsvFileConfig config, Function<String[], E> creator, Predicate<E> validator, boolean close) throws IOException {
        return readAsList(newCSVReader(reader, config), creator, validator, close);
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
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
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
        return readAsList(reader, new CsvFileConfig(separator, quotechar, escape, strictQuotes, ignoreLeadingWhiteSpace, skip, Charset.defaultCharset()), type, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 17.		박준홍			최초 작성
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
     * @param afterCreation
     *            객체 생성 후 작업.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 17.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(Reader reader, int skip //
            , char separator, char quotechar, char escape, boolean strictQuotes, boolean ignoreLeadingWhiteSpace //
            , Class<E> type, Consumer<E> afterCreation, boolean close) throws IOException {
        return readAsList(reader, new CsvFileConfig(separator, quotechar, escape, strictQuotes, ignoreLeadingWhiteSpace, skip, Charset.defaultCharset()), type, afterCreation,
                close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     박준홍         최초 작성
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
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(Reader reader, int skip //
            , char separator, char quotechar, char escape, boolean strictQuotes, boolean ignoreLeadingWhiteSpace //
            , Class<E> type, Predicate<E> validator, boolean close) throws IOException {
        return readAsList(reader, new CsvFileConfig(separator, quotechar, escape, strictQuotes, ignoreLeadingWhiteSpace, skip, Charset.defaultCharset()), type, validator, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     박준홍         최초 작성
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
     * @param validator
     *            데이터 검증 함수.
     * @param afterCreation
     *            객체 생성 후 작업.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(Reader reader, int skip //
            , char separator, char quotechar, char escape, boolean strictQuotes, boolean ignoreLeadingWhiteSpace //
            , Class<E> type, Predicate<E> validator, Consumer<E> afterCreation, boolean close) throws IOException {
        return readAsList(reader, new CsvFileConfig(separator, quotechar, escape, strictQuotes, ignoreLeadingWhiteSpace, skip, Charset.defaultCharset()), type, validator,
                afterCreation, close);
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
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
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
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     박준홍         최초 작성
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
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, Predicate, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b><code>NOT nullable</code></b>)</font>
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(Reader reader, int skip //
            , char separator, char quotechar, char escape, boolean strictQuotes, boolean ignoreLeadingWhiteSpace //
            , Function<String[], E> creator, Predicate<E> validator, boolean close) throws IOException {
        return readAsList(reader, new CsvFileConfig(separator, quotechar, escape, strictQuotes, ignoreLeadingWhiteSpace, skip, Charset.defaultCharset()), creator, validator,
                close);
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
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
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
        return readAsList(reader, skip, separator, quotechar, escape, strictQuotes, CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, type, close);
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
     * @param afterCreation
     *            객체 생성 후 작업.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(Reader reader, int skip //
            , char separator, char quotechar, char escape, boolean strictQuotes //
            , Class<E> type, Consumer<E> afterCreation, boolean close) throws IOException {
        return readAsList(reader, skip, separator, quotechar, escape, strictQuotes, CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, type, afterCreation, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     박준홍         최초 작성
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
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(Reader reader, int skip //
            , char separator, char quotechar, char escape, boolean strictQuotes //
            , Class<E> type, Predicate<E> validator, boolean close) throws IOException {
        return readAsList(reader, skip, separator, quotechar, escape, strictQuotes, CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, type, validator, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     박준홍         최초 작성
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
     * @param validator
     *            데이터 검증 함수.
     * @param afterCreation
     *            객체 생성 후 작업.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(Reader reader, int skip //
            , char separator, char quotechar, char escape, boolean strictQuotes //
            , Class<E> type, Predicate<E> validator, Consumer<E> afterCreation, boolean close) throws IOException {
        return readAsList(reader, skip, separator, quotechar, escape, strictQuotes, CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, type, validator, afterCreation, close);
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
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
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
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     박준홍         최초 작성
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
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, Predicate, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b><code>NOT nullable</code></b>)</font>
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(Reader reader, int skip //
            , char separator, char quotechar, char escape, boolean strictQuotes //
            , Function<String[], E> creator, Predicate<E> validator, boolean close) throws IOException {
        return readAsList(reader, skip, separator, quotechar, escape, strictQuotes, CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, creator, validator, close);
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
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(Reader reader, int skip, char separator, char quotechar, char escape, Class<E> type, boolean close) throws IOException {
        return readAsList(reader, skip, separator, quotechar, escape, CsvConfig.DEFAULT_STRICT_QUOTES, CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, type, close);
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
     * @param afterCreation
     *            객체 생성 후 작업.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(Reader reader, int skip, char separator, char quotechar, char escape, Class<E> type, Consumer<E> afterCreation, boolean close)
            throws IOException {
        return readAsList(reader, skip, separator, quotechar, escape, CsvConfig.DEFAULT_STRICT_QUOTES, CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, type, afterCreation, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     박준홍         최초 작성
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
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(Reader reader, int skip, char separator, char quotechar, char escape, Class<E> type, Predicate<E> validator, boolean close)
            throws IOException {
        return readAsList(reader, skip, separator, quotechar, escape, CsvConfig.DEFAULT_STRICT_QUOTES, CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, type, validator, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     박준홍         최초 작성
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
     * @param validator
     *            데이터 검증 함수.
     * @param afterCreation
     *            객체 생성 후 작업.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(Reader reader, int skip, char separator, char quotechar, char escape, Class<E> type, Predicate<E> validator,
            Consumer<E> afterCreation, boolean close) throws IOException {
        return readAsList(reader, skip, separator, quotechar, escape, CsvConfig.DEFAULT_STRICT_QUOTES, CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, type, validator, afterCreation,
                close);
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
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
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
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     박준홍         최초 작성
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
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, Predicate, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b><code>NOT nullable</code></b>)</font>
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(Reader reader, int skip, char separator, char quotechar, char escape, Function<String[], E> creator, Predicate<E> validator,
            boolean close) throws IOException {
        return readAsList(reader, skip, separator, quotechar, escape, CsvConfig.DEFAULT_STRICT_QUOTES, CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, creator, validator, close);
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
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(Reader reader, int skip, char separator, char quotechar, Class<E> type, boolean close) throws IOException {
        return readAsList(reader, skip, separator, quotechar, CsvConfig.DEFAULT_ESCAPE_CHARACTER, CsvConfig.DEFAULT_STRICT_QUOTES, CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE,
                type, close);
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
     * @param afterCreation
     *            객체 생성 후 작업.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(Reader reader, int skip, char separator, char quotechar, Class<E> type, Consumer<E> afterCreation, boolean close)
            throws IOException {
        return readAsList(reader, skip, separator, quotechar, CsvConfig.DEFAULT_ESCAPE_CHARACTER, CsvConfig.DEFAULT_STRICT_QUOTES, CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE,
                type, afterCreation, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     박준홍         최초 작성
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
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(Reader reader, int skip, char separator, char quotechar, Class<E> type, Predicate<E> validator, boolean close) throws IOException {
        return readAsList(reader, skip, separator, quotechar, CsvConfig.DEFAULT_ESCAPE_CHARACTER, CsvConfig.DEFAULT_STRICT_QUOTES, CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE,
                type, validator, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     박준홍         최초 작성
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
     * @param validator
     *            데이터 검증 함수.
     * @param afterCreation
     *            객체 생성 후 작업.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(Reader reader, int skip, char separator, char quotechar, Class<E> type, Predicate<E> validator, Consumer<E> afterCreation,
            boolean close) throws IOException {
        return readAsList(reader, skip, separator, quotechar, CsvConfig.DEFAULT_ESCAPE_CHARACTER, CsvConfig.DEFAULT_STRICT_QUOTES, CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE,
                type, validator, afterCreation, close);
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
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
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
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     박준홍         최초 작성
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
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, Predicate, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b><code>NOT nullable</code></b>)</font>
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(Reader reader, int skip, char separator, char quotechar, Function<String[], E> creator, Predicate<E> validator, boolean close)
            throws IOException {
        return readAsList(reader, skip, separator, quotechar, CsvConfig.DEFAULT_ESCAPE_CHARACTER, CsvConfig.DEFAULT_STRICT_QUOTES, CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE,
                creator, validator, close);
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
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(Reader reader, int skip, char separator, Class<E> type, boolean close) throws IOException {
        return readAsList(reader, skip, separator, CsvConfig.DEFAULT_QUOTE_CHARACTER, CsvConfig.DEFAULT_ESCAPE_CHARACTER, CsvConfig.DEFAULT_STRICT_QUOTES,
                CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, type, close);
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
     * @param afterCreation
     *            객체 생성 후 작업.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(Reader reader, int skip, char separator, Class<E> type, Consumer<E> afterCreation, boolean close) throws IOException {
        return readAsList(reader, skip, separator, CsvConfig.DEFAULT_QUOTE_CHARACTER, CsvConfig.DEFAULT_ESCAPE_CHARACTER, CsvConfig.DEFAULT_STRICT_QUOTES,
                CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, type, afterCreation, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     박준홍         최초 작성
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
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(Reader reader, int skip, char separator, Class<E> type, Predicate<E> validator, boolean close) throws IOException {
        return readAsList(reader, skip, separator, CsvConfig.DEFAULT_QUOTE_CHARACTER, CsvConfig.DEFAULT_ESCAPE_CHARACTER, CsvConfig.DEFAULT_STRICT_QUOTES,
                CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, type, validator, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     박준홍         최초 작성
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
     * @param validator
     *            데이터 검증 함수.
     * @param afterCreation
     *            객체 생성 후 작업.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(Reader reader, int skip, char separator, Class<E> type, Predicate<E> validator, Consumer<E> afterCreation, boolean close)
            throws IOException {
        return readAsList(reader, skip, separator, CsvConfig.DEFAULT_QUOTE_CHARACTER, CsvConfig.DEFAULT_ESCAPE_CHARACTER, CsvConfig.DEFAULT_STRICT_QUOTES,
                CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, type, validator, afterCreation, close);
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
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
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

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     박준홍         최초 작성
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
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, Predicate, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b><code>NOT nullable</code></b>)</font>
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<List<E>> readAsList(Reader reader, int skip, char separator, Function<String[], E> creator, Predicate<E> validator, boolean close) throws IOException {
        return readAsList(reader, skip, separator, CsvConfig.DEFAULT_QUOTE_CHARACTER, CsvConfig.DEFAULT_ESCAPE_CHARACTER, CsvConfig.DEFAULT_STRICT_QUOTES,
                CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, creator, validator, close);
    }

    /**
     * 객체를 CSV 파일로 저장합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 17.     박준홍         최초 작성
     * </pre>
     *
     * @param <E>
     *            CSV 파일을 읽어서 생성할 데이터 모델. {@link WriteAt}이 설정된 메소드를 호출하여 데이터를 설정합니다.
     * @param data
     *            데이터
     * @param writer
     *            CSV 데이터 (<b><code>NOT nullable</code></b>)
     * @param header
     *            헤더
     * @param beforeCreation
     *            String[]로 변환하기 전 작업.
     * @param close
     *            {@link CSVWriter} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<Long> write(Collection<E> data, CSVWriter writer, Consumer<E> beforeCreation, boolean close) throws IOException {
        return write(data, writer, (String[]) null, beforeCreation, close);
    }

    /**
     * 객체를 CSV 파일로 저장합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 17.        박준홍         최초 작성
     * </pre>
     * 
     * @param <E>
     *            CSV 파일을 읽어서 생성할 데이터 모델. {@link WriteAt}이 설정된 메소드를 호출하여 데이터를 설정합니다.
     * @param data
     *            데이터
     * @param writer
     *            CSV 데이터 (<b><code>NOT nullable</code></b>)
     * @param creator
     *            객체를 전달받아 {@link String}[] 데이터를 생성하는 함수.<br>
     *            객체의 <code>getter</code> 메소드에 {@link WriteAt}을 설정한다면, {@link #defaultCreator()} 를 사용하거나
     *            {@link #write(Collection, CSVWriter, String[], Consumer, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b><code>NOT nullable</code></b>)</font>
     * @param beforeCreation
     *            String[]로 변환하기 전 작업.
     * @param close
     *            {@link CSVWriter} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<Long> write(Collection<E> data, CSVWriter writer, Function<E, String[]> creator, Consumer<E> beforeCreation, boolean close) throws IOException {
        return write(data, writer, null, creator, beforeCreation, close);
    }

    /**
     * 객체를 CSV 파일로 저장합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 17.     박준홍         최초 작성
     * </pre>
     *
     * @param <E>
     *            CSV 파일을 읽어서 생성할 데이터 모델. {@link WriteAt}이 설정된 메소드를 호출하여 데이터를 설정합니다.
     * @param data
     *            데이터
     * @param writer
     *            CSV 데이터 (<b><code>NOT nullable</code></b>)
     * @param header
     *            헤더
     * @param beforeCreation
     *            String[]로 변환하기 전 작업.
     * @param close
     *            {@link CSVWriter} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<Long> write(Collection<E> data, CSVWriter writer, String[] header, Consumer<E> beforeCreation, boolean close) throws IOException {
        return write(data, writer, header, defaultCreator(), beforeCreation, close);
    }

    /**
     * 객체를 CSV 파일로 저장합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 17.        박준홍         최초 작성
     * </pre>
     * 
     * @param <E>
     *            CSV 파일을 읽어서 생성할 데이터 모델. {@link WriteAt}이 설정된 메소드를 호출하여 데이터를 설정합니다.
     * @param data
     *            데이터
     * @param writer
     *            CSV 데이터 (<b><code>NOT nullable</code></b>)
     * @param header
     *            헤더
     * @param creator
     *            객체를 전달받아 {@link String}[] 데이터를 생성하는 함수.<br>
     *            객체의 <code>getter</code> 메소드에 {@link WriteAt}을 설정한다면, {@link #defaultCreator()} 를 사용하거나
     *            {@link #write(Collection, CSVWriter, String[], Consumer, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b><code>NOT nullable</code></b>)</font>
     * @param beforeCreation
     *            String[]로 변환하기 전 작업.
     * @param close
     *            {@link CSVWriter} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<Long> write(Collection<E> data, CSVWriter writer, String[] header, Function<E, String[]> creator, Consumer<E> beforeCreation, boolean close)
            throws IOException {

        StopWatch watch = new StopWatch();
        watch.start();

        long count = 0;
        try {
            // 헤더 확인
            if (header != null) {
                writer.writeNext(header);
            }

            for (E d : data) {
                if (beforeCreation != null) {
                    beforeCreation.accept(d);
                }
                writer.writeNext(creator.apply(d));

                count++;
            }

            return Result.success(count);
        } catch (Exception e) {
            String errMsg = String.format("CSV 파일을 읽는 도중 에러가 발생하였습니다. 원인=%s", e.getMessage());
            logger.error(errMsg, e);
            throw new IOException(errMsg, e);
        } finally {
            if (close) {
                IOUtils.close(writer);
            }
            watch.stop();

            logger.trace(String.format("Data=%,d, Elasped=%s", data.size(), watch.getAsPretty()));
        }
    }

    /**
     * 여러 개의 객체를 CSV 파일로 저장합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 17.     박준홍         최초 작성
     * </pre>
     *
     * @param <E>
     * @param data
     *            데이터
     * @param file
     *            CSV 파일
     * @param config
     *            CSV 파일 쓰기 설정
     * @param close
     *            CSV 파일 자동 Close 여부
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<Long> write(Collection<E> data, File file, CsvWriteConfig config, boolean close) throws IOException {
        return write(data, file, config, defaultCreator(), close);
    }

    /**
     * 여러 개의 객체를 CSV 파일로 저장합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 17.     박준홍         최초 작성
     * </pre>
     *
     * @param <E>
     * @param data
     *            데이터
     * @param file
     *            CSV 파일
     * @param config
     *            CSV 파일 쓰기 설정
     * @param beforeCreation
     *            객체 변환하기 전 실행할 함수
     * @param close
     *            CSV 파일 자동 Close 여부
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<Long> write(Collection<E> data, File file, CsvWriteConfig config, Consumer<E> beforeCreation, boolean close) throws IOException {
        return write(data, file, config, defaultCreator(), beforeCreation, close);
    }

    /**
     * 여러 개의 객체를 CSV 파일로 저장합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 17.     박준홍         최초 작성
     * </pre>
     *
     * @param <E>
     * @param data
     *            데이터
     * @param file
     *            CSV 파일
     * @param config
     *            CSV 파일 쓰기 설정
     * @param creator
     *            객체를 {@link String}[]로 변환하는 함수
     * @param close
     *            CSV 파일 자동 Close 여부
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<Long> write(Collection<E> data, File file, CsvWriteConfig config, Function<E, String[]> creator, boolean close) throws IOException {
        return write(data, file, config, null, creator, null, close);
    }

    /**
     * 여러 개의 객체를 CSV 파일로 저장합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 17.     박준홍         최초 작성
     * </pre>
     *
     * @param <E>
     * @param data
     *            데이터
     * @param file
     *            CSV 파일
     * @param config
     *            CSV 파일 쓰기 설정
     * @param creator
     *            객체를 {@link String}[]로 변환하는 함수
     * @param beforeCreation
     *            객체 변환하기 전 실행할 함수
     * @param close
     *            CSV 파일 자동 Close 여부
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<Long> write(Collection<E> data, File file, CsvWriteConfig config, Function<E, String[]> creator, Consumer<E> beforeCreation, boolean close)
            throws IOException {
        return write(data, file, config, null, creator, beforeCreation, close);
    }

    /**
     * 여러 개의 객체를 CSV 파일로 저장합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 17.     박준홍         최초 작성
     * </pre>
     *
     * @param <E>
     * @param data
     *            데이터
     * @param file
     *            CSV 파일
     * @param config
     *            CSV 파일 쓰기 설정
     * @param header
     *            CSV 파일 헤더
     * @param close
     *            CSV 파일 자동 Close 여부
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<Long> write(Collection<E> data, File file, CsvWriteConfig config, String[] header, boolean close) throws IOException {
        return write(data, file, config, header, defaultCreator(), close);
    }

    /**
     * 여러 개의 객체를 CSV 파일로 저장합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 17.     박준홍         최초 작성
     * </pre>
     *
     * @param <E>
     * @param data
     *            데이터
     * @param file
     *            CSV 파일
     * @param config
     *            CSV 파일 쓰기 설정
     * @param header
     *            CSV 파일 헤더
     * @param beforeCreation
     *            객체 변환하기 전 실행할 함수
     * @param close
     *            CSV 파일 자동 Close 여부
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<Long> write(Collection<E> data, File file, CsvWriteConfig config, String[] header, Consumer<E> beforeCreation, boolean close) throws IOException {
        return write(data, file, config, header, defaultCreator(), beforeCreation, close);
    }

    /**
     * 여러 개의 객체를 CSV 파일로 저장합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 17.     박준홍         최초 작성
     * </pre>
     *
     * @param <E>
     * @param data
     *            데이터
     * @param file
     *            CSV 파일
     * @param config
     *            CSV 파일 쓰기 설정
     * @param header
     *            CSV 파일 헤더
     * @param creator
     *            객체를 {@link String}[]로 변환하는 함수
     * @param close
     *            CSV 파일 자동 Close 여부
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<Long> write(Collection<E> data, File file, CsvWriteConfig config, String[] header, Function<E, String[]> creator, boolean close) throws IOException {
        return write(data, file, config, header, creator, null, close);
    }

    /**
     * 여러 개의 객체를 CSV 파일로 저장합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 17.     박준홍         최초 작성
     * </pre>
     *
     * @param <E>
     * @param data
     *            데이터
     * @param file
     *            CSV 파일
     * @param config
     *            CSV 파일 쓰기 설정
     * @param header
     *            CSV 파일 헤더
     * @param creator
     *            객체를 {@link String}[]로 변환하는 함수
     * @param beforeCreation
     *            객체 변환하기 전 실행할 함수
     * @param close
     *            CSV 파일 자동 Close 여부
     * @return
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<Long> write(Collection<E> data, File file, CsvWriteConfig config, String[] header, Function<E, String[]> creator, Consumer<E> beforeCreation,
            boolean close) throws IOException {
        return write(data, new FileOutputStream(file), config, header, creator, beforeCreation, close);
    }

    /**
     * 여러 개의 객체를 CSV 파일로 저장합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 17.     박준홍         최초 작성
     * </pre>
     *
     * @param <E>
     * @param data
     *            데이터
     * @param outputStream
     *            CSV 파일 스트림
     * @param config
     *            CSV 파일 쓰기 설정
     * @param close
     *            CSV 파일 자동 Close 여부
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<Long> write(Collection<E> data, OutputStream outputStream, CsvWriteConfig config, boolean close) throws IOException {
        return write(data, outputStream, config, defaultCreator(), close);
    }

    /**
     * 여러 개의 객체를 CSV 파일로 저장합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 17.     박준홍         최초 작성
     * </pre>
     *
     * @param <E>
     * @param data
     *            데이터
     * @param outputStream
     *            CSV 파일 스트림
     * @param config
     *            CSV 파일 쓰기 설정
     * @param beforeCreation
     *            객체 변환하기 전 실행할 함수
     * @param close
     *            CSV 파일 자동 Close 여부
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<Long> write(Collection<E> data, OutputStream outputStream, CsvWriteConfig config, Consumer<E> beforeCreation, boolean close) throws IOException {
        return write(data, outputStream, config, defaultCreator(), beforeCreation, close);
    }

    /**
     * 여러 개의 객체를 CSV 파일로 저장합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 17.     박준홍         최초 작성
     * </pre>
     *
     * @param <E>
     * @param data
     *            데이터
     * @param outputStream
     *            CSV 파일 스트림
     * @param config
     *            CSV 파일 쓰기 설정
     * @param creator
     *            객체를 {@link String}[]로 변환하는 함수
     * @param close
     *            CSV 파일 자동 Close 여부
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<Long> write(Collection<E> data, OutputStream outputStream, CsvWriteConfig config, Function<E, String[]> creator, boolean close) throws IOException {
        return write(data, outputStream, config, null, creator, null, close);
    }

    /**
     * 여러 개의 객체를 CSV 파일로 저장합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 17.     박준홍         최초 작성
     * </pre>
     *
     * @param <E>
     * @param data
     *            데이터
     * @param outputStream
     *            CSV 파일 스트림
     * @param config
     *            CSV 파일 쓰기 설정
     * @param creator
     *            객체를 {@link String}[]로 변환하는 함수
     * @param beforeCreation
     *            객체 변환하기 전 실행할 함수
     * @param close
     *            CSV 파일 자동 Close 여부
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<Long> write(Collection<E> data, OutputStream outputStream, CsvWriteConfig config, Function<E, String[]> creator, Consumer<E> beforeCreation,
            boolean close) throws IOException {
        return write(data, outputStream, config, null, creator, beforeCreation, close);
    }

    /**
     * 여러 개의 객체를 CSV 파일로 저장합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 17.     박준홍         최초 작성
     * </pre>
     *
     * @param <E>
     * @param data
     *            데이터
     * @param outputStream
     *            CSV 파일 스트림
     * @param config
     *            CSV 파일 쓰기 설정
     * @param header
     *            CSV 파일 헤더
     * @param close
     *            CSV 파일 자동 Close 여부
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<Long> write(Collection<E> data, OutputStream outputStream, CsvWriteConfig config, String[] header, boolean close) throws IOException {
        return write(data, outputStream, config, header, defaultCreator(), close);
    }

    /**
     * 여러 개의 객체를 CSV 파일로 저장합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 17.     박준홍         최초 작성
     * </pre>
     *
     * @param <E>
     * @param data
     *            데이터
     * @param outputStream
     *            CSV 파일 스트림
     * @param config
     *            CSV 파일 쓰기 설정
     * @param header
     *            CSV 파일 헤더
     * @param beforeCreation
     *            객체 변환하기 전 실행할 함수
     * @param close
     *            CSV 파일 자동 Close 여부
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<Long> write(Collection<E> data, OutputStream outputStream, CsvWriteConfig config, String[] header, Consumer<E> beforeCreation, boolean close)
            throws IOException {
        return write(data, outputStream, config, header, defaultCreator(), beforeCreation, close);
    }

    /**
     * 여러 개의 객체를 CSV 파일로 저장합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 17.     박준홍         최초 작성
     * </pre>
     *
     * @param <E>
     * @param data
     *            데이터
     * @param outputStream
     *            CSV 파일 스트림
     * @param config
     *            CSV 파일 쓰기 설정
     * @param header
     *            CSV 파일 헤더
     * @param creator
     *            객체를 {@link String}[]로 변환하는 함수
     * @param close
     *            CSV 파일 자동 Close 여부
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<Long> write(Collection<E> data, OutputStream outputStream, CsvWriteConfig config, String[] header, Function<E, String[]> creator, boolean close)
            throws IOException {
        return write(data, outputStream, config, header, creator, null, close);
    }

    /**
     * 여러 개의 객체를 CSV 파일로 저장합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 17.     박준홍         최초 작성
     * </pre>
     *
     * @param <E>
     * @param data
     *            데이터
     * @param outputStream
     *            CSV 파일 스트림
     * @param config
     *            CSV 파일 쓰기 설정
     * @param header
     *            CSV 파일 헤더
     * @param creator
     *            객체를 {@link String}[]로 변환하는 함수
     * @param beforeCreation
     *            객체 변환하기 전 실행할 함수
     * @param close
     *            CSV 파일 자동 Close 여부
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<Long> write(Collection<E> data, OutputStream outputStream, CsvWriteConfig config, String[] header, Function<E, String[]> creator,
            Consumer<E> beforeCreation, boolean close) throws IOException {
        return write(data, new OutputStreamWriter(outputStream, config.getCharset()), config, header, creator, beforeCreation, close);
    }

    /**
     * 여러 개의 객체를 CSV 파일로 저장합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 03. 22.     박준홍         최초 작성
     * </pre>
     *
     * @param <E>
     * @param data
     *            데이터
     * @param path
     *            CSV 파일
     * @param config
     *            CSV 파일 쓰기 설정
     * @param close
     *            CSV 파일 자동 Close 여부
     * @param options
     *            파일 생성 Option
     * @return
     * @throws IOException
     *
     * @since 2022. 03. 22.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<Long> write(Collection<E> data, Path path, CsvWriteConfig config, boolean close, OpenOption... options) throws IOException {
        return write(data, path, config, defaultCreator(), close, options);
    }

    /**
     * 여러 개의 객체를 CSV 파일로 저장합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 03. 22.     박준홍         최초 작성
     * </pre>
     *
     * @param <E>
     * @param data
     *            데이터
     * @param path
     *            CSV 파일
     * @param config
     *            CSV 파일 쓰기 설정
     * @param beforeCreation
     *            객체 변환하기 전 실행할 함수
     * @param close
     *            CSV 파일 자동 Close 여부
     * @param options
     *            파일 생성 Option
     * @return
     * @throws IOException
     *
     * @since 2022. 03. 22.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<Long> write(Collection<E> data, Path path, CsvWriteConfig config, Consumer<E> beforeCreation, boolean close, OpenOption... options)
            throws IOException {
        return write(data, path, config, defaultCreator(), beforeCreation, close, options);
    }

    /**
     * 여러 개의 객체를 CSV 파일로 저장합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 03. 22.     박준홍         최초 작성
     * </pre>
     *
     * @param <E>
     * @param data
     *            데이터
     * @param path
     *            CSV 파일
     * @param config
     *            CSV 파일 쓰기 설정
     * @param creator
     *            객체를 {@link String}[]로 변환하는 함수
     * @param close
     *            CSV 파일 자동 Close 여부
     * @param options
     *            파일 생성 Option
     * @return
     * @throws IOException
     *
     * @since 2022. 03. 22.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<Long> write(Collection<E> data, Path path, CsvWriteConfig config, Function<E, String[]> creator, boolean close, OpenOption... options)
            throws IOException {
        return write(data, path, config, null, creator, null, close, options);
    }

    /**
     * 여러 개의 객체를 CSV 파일로 저장합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 03. 22.     박준홍         최초 작성
     * </pre>
     *
     * @param <E>
     * @param data
     *            데이터
     * @param path
     *            CSV 파일
     * @param config
     *            CSV 파일 쓰기 설정
     * @param creator
     *            객체를 {@link String}[]로 변환하는 함수
     * @param beforeCreation
     *            객체 변환하기 전 실행할 함수
     * @param close
     *            CSV 파일 자동 Close 여부
     * @param options
     *            파일 생성 Option
     * @return
     * @throws IOException
     *
     * @since 2022. 03. 22.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<Long> write(Collection<E> data, Path path, CsvWriteConfig config, Function<E, String[]> creator, Consumer<E> beforeCreation, boolean close,
            OpenOption... options) throws IOException {
        return write(data, path, config, null, creator, beforeCreation, close, options);
    }

    /**
     * 여러 개의 객체를 CSV 파일로 저장합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 03. 22.     박준홍         최초 작성
     * </pre>
     *
     * @param <E>
     * @param data
     *            데이터
     * @param path
     *            CSV 파일
     * @param config
     *            CSV 파일 쓰기 설정
     * @param header
     *            CSV 파일 헤더
     * @param close
     *            CSV 파일 자동 Close 여부
     * @param options
     *            파일 생성 Option
     * @return
     * @throws IOException
     *
     * @since 2022. 03. 22.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<Long> write(Collection<E> data, Path path, CsvWriteConfig config, String[] header, boolean close, OpenOption... options) throws IOException {
        return write(data, path, config, header, defaultCreator(), close, options);
    }

    /**
     * 여러 개의 객체를 CSV 파일로 저장합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 03. 22.     박준홍         최초 작성
     * </pre>
     *
     * @param <E>
     * @param data
     *            데이터
     * @param path
     *            CSV 파일
     * @param config
     *            CSV 파일 쓰기 설정
     * @param header
     *            CSV 파일 헤더
     * @param beforeCreation
     *            객체 변환하기 전 실행할 함수
     * @param close
     *            CSV 파일 자동 Close 여부
     * @param options
     *            파일 생성 Option
     * @return
     * @throws IOException
     *
     * @since 2022. 03. 22.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<Long> write(Collection<E> data, Path path, CsvWriteConfig config, String[] header, Consumer<E> beforeCreation, boolean close, OpenOption... options)
            throws IOException {
        return write(data, path, config, header, defaultCreator(), beforeCreation, close, options);
    }

    /**
     * 여러 개의 객체를 CSV 파일로 저장합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 03. 22.     박준홍         최초 작성
     * </pre>
     *
     * @param <E>
     * @param data
     *            데이터
     * @param path
     *            CSV 파일
     * @param config
     *            CSV 파일 쓰기 설정
     * @param header
     *            CSV 파일 헤더
     * @param creator
     *            객체를 {@link String}[]로 변환하는 함수
     * @param close
     *            CSV 파일 자동 Close 여부
     * @param options
     *            파일 생성 Option
     * @return
     * @throws IOException
     *
     * @since 2022. 03. 22.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<Long> write(Collection<E> data, Path path, CsvWriteConfig config, String[] header, Function<E, String[]> creator, boolean close, OpenOption... options)
            throws IOException {
        return write(data, path, config, header, creator, null, close, options);
    }

    /**
     * 여러 개의 객체를 CSV 파일로 저장합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 03. 22.     박준홍         최초 작성
     * </pre>
     *
     * @param <E>
     * @param data
     *            데이터
     * @param path
     *            CSV 파일
     * @param config
     *            CSV 파일 쓰기 설정
     * @param header
     *            CSV 파일 헤더
     * @param creator
     *            객체를 {@link String}[]로 변환하는 함수
     * @param beforeCreation
     *            객체 변환하기 전 실행할 함수
     * @param close
     *            CSV 파일 자동 Close 여부
     * @param options
     *            파일 생성 Option
     * @return
     * @return
     * @throws IOException
     *
     * @since 2022. 03. 22.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<Long> write(Collection<E> data, Path path, CsvWriteConfig config, String[] header, Function<E, String[]> creator, Consumer<E> beforeCreation,
            boolean close, OpenOption... options) throws IOException {
        return write(data, Files.newOutputStream(path, options), config, header, creator, beforeCreation, close);
    }

    /**
     * 여러 개의 객체를 CSV 파일로 저장합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 17.     박준홍         최초 작성
     * </pre>
     *
     * @param <E>
     * @param data
     *            데이터
     * @param filepath
     *            CSV 파일 경로
     * @param config
     *            CSV 파일 쓰기 설정
     * @param close
     *            CSV 파일 자동 Close 여부
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<Long> write(Collection<E> data, String filepath, CsvWriteConfig config, boolean close) throws IOException {
        return write(data, filepath, config, defaultCreator(), close);
    }

    /**
     * 여러 개의 객체를 CSV 파일로 저장합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 17.     박준홍         최초 작성
     * </pre>
     *
     * @param <E>
     * @param data
     *            데이터
     * @param filepath
     *            CSV 파일 경로
     * @param config
     *            CSV 파일 쓰기 설정
     * @param beforeCreation
     *            객체 변환하기 전 실행할 함수
     * @param close
     *            CSV 파일 자동 Close 여부
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<Long> write(Collection<E> data, String filepath, CsvWriteConfig config, Consumer<E> beforeCreation, boolean close) throws IOException {
        return write(data, filepath, config, defaultCreator(), beforeCreation, close);
    }

    /**
     * 여러 개의 객체를 CSV 파일로 저장합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 17.     박준홍         최초 작성
     * </pre>
     *
     * @param <E>
     * @param data
     *            데이터
     * @param filepath
     *            CSV 파일 경로
     * @param config
     *            CSV 파일 쓰기 설정
     * @param creator
     *            객체를 {@link String}[]로 변환하는 함수
     * @param close
     *            CSV 파일 자동 Close 여부
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<Long> write(Collection<E> data, String filepath, CsvWriteConfig config, Function<E, String[]> creator, boolean close) throws IOException {
        return write(data, filepath, config, null, creator, null, close);
    }

    /**
     * 여러 개의 객체를 CSV 파일로 저장합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 17.     박준홍         최초 작성
     * </pre>
     *
     * @param <E>
     * @param data
     *            데이터
     * @param filepath
     *            CSV 파일 경로
     * @param config
     *            CSV 파일 쓰기 설정
     * @param creator
     *            객체를 {@link String}[]로 변환하는 함수
     * @param beforeCreation
     *            객체 변환하기 전 실행할 함수
     * @param close
     *            CSV 파일 자동 Close 여부
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<Long> write(Collection<E> data, String filepath, CsvWriteConfig config, Function<E, String[]> creator, Consumer<E> beforeCreation, boolean close)
            throws IOException {
        return write(data, filepath, config, null, creator, beforeCreation, close);
    }

    /**
     * 여러 개의 객체를 CSV 파일로 저장합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 17.     박준홍         최초 작성
     * </pre>
     *
     * @param <E>
     * @param data
     *            데이터
     * @param filepath
     *            CSV 파일 경로
     * @param config
     *            CSV 파일 쓰기 설정
     * @param header
     *            CSV 파일 헤더
     * @param close
     *            CSV 파일 자동 Close 여부
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<Long> write(Collection<E> data, String filepath, CsvWriteConfig config, String[] header, boolean close) throws IOException {
        return write(data, filepath, config, header, defaultCreator(), close);
    }

    /**
     * 여러 개의 객체를 CSV 파일로 저장합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 17.     박준홍         최초 작성
     * </pre>
     *
     * @param <E>
     * @param data
     *            데이터
     * @param filepath
     *            CSV 파일 경로
     * @param config
     *            CSV 파일 쓰기 설정
     * @param header
     *            CSV 파일 헤더
     * @param beforeCreation
     *            객체 변환하기 전 실행할 함수
     * @param close
     *            CSV 파일 자동 Close 여부
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<Long> write(Collection<E> data, String filepath, CsvWriteConfig config, String[] header, Consumer<E> beforeCreation, boolean close)
            throws IOException {
        return write(data, filepath, config, header, defaultCreator(), beforeCreation, close);
    }

    /**
     * 여러 개의 객체를 CSV 파일로 저장합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 17.     박준홍         최초 작성
     * </pre>
     *
     * @param <E>
     * @param data
     *            데이터
     * @param filepath
     *            CSV 파일 경로
     * @param config
     *            CSV 파일 쓰기 설정
     * @param header
     *            CSV 파일 헤더
     * @param creator
     *            객체를 {@link String}[]로 변환하는 함수
     * @param close
     *            CSV 파일 자동 Close 여부
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<Long> write(Collection<E> data, String filepath, CsvWriteConfig config, String[] header, Function<E, String[]> creator, boolean close)
            throws IOException {
        return write(data, filepath, config, header, creator, null, close);
    }

    /**
     * 여러 개의 객체를 CSV 파일로 저장합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 17.     박준홍         최초 작성
     * </pre>
     *
     * @param <E>
     * @param data
     *            데이터
     * @param filepath
     *            CSV 파일 경로
     * @param config
     *            CSV 파일 쓰기 설정
     * @param header
     *            CSV 파일 헤더
     * @param creator
     *            객체를 {@link String}[]로 변환하는 함수
     * @param beforeCreation
     *            객체 변환하기 전 실행할 함수
     * @param close
     *            CSV 파일 자동 Close 여부
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<Long> write(Collection<E> data, String filepath, CsvWriteConfig config, String[] header, Function<E, String[]> creator, Consumer<E> beforeCreation,
            boolean close) throws IOException {
        return write(data, new File(filepath), config, header, creator, beforeCreation, close);
    }

    /**
     * 여러 개의 객체를 CSV 파일로 저장합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 17.     박준홍         최초 작성
     * </pre>
     *
     * @param <E>
     * @param data
     *            데이터
     * @param writer
     *            CSV 파일 쓰기 객체
     * @param config
     *            CSV 파일 쓰기 설정
     * @param close
     *            CSV 파일 자동 Close 여부
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<Long> write(Collection<E> data, Writer writer, CsvWriteConfig config, boolean close) throws IOException {
        return write(data, writer, config, defaultCreator(), close);
    }

    /**
     * 여러 개의 객체를 CSV 파일로 저장합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 17.     박준홍         최초 작성
     * </pre>
     *
     * @param <E>
     * @param data
     *            데이터
     * @param writer
     *            CSV 파일 쓰기 객체
     * @param config
     *            CSV 파일 쓰기 설정
     * @param beforeCreation
     *            객체 변환하기 전 실행할 함수
     * @param close
     *            CSV 파일 자동 Close 여부
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<Long> write(Collection<E> data, Writer writer, CsvWriteConfig config, Consumer<E> beforeCreation, boolean close) throws IOException {
        return write(data, writer, config, defaultCreator(), beforeCreation, close);
    }

    /**
     * 여러 개의 객체를 CSV 파일로 저장합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 17.     박준홍         최초 작성
     * </pre>
     *
     * @param <E>
     * @param data
     *            데이터
     * @param writer
     *            CSV 파일 쓰기 객체
     * @param config
     *            CSV 파일 쓰기 설정
     * @param creator
     *            객체를 {@link String}[]로 변환하는 함수
     * @param close
     *            CSV 파일 자동 Close 여부
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<Long> write(Collection<E> data, Writer writer, CsvWriteConfig config, Function<E, String[]> creator, boolean close) throws IOException {
        return write(data, writer, config, null, creator, null, close);
    }

    /**
     * 여러 개의 객체를 CSV 파일로 저장합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 17.     박준홍         최초 작성
     * </pre>
     *
     * @param <E>
     * @param data
     *            데이터
     * @param writer
     *            CSV 파일 쓰기 객체
     * @param config
     *            CSV 파일 쓰기 설정
     * @param creator
     *            객체를 {@link String}[]로 변환하는 함수
     * @param beforeCreation
     *            객체 변환하기 전 실행할 함수
     * @param close
     *            CSV 파일 자동 Close 여부
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<Long> write(Collection<E> data, Writer writer, CsvWriteConfig config, Function<E, String[]> creator, Consumer<E> beforeCreation, boolean close)
            throws IOException {
        return write(data, writer, config, null, creator, beforeCreation, close);
    }

    /**
     * 여러 개의 객체를 CSV 파일로 저장합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 17.     박준홍         최초 작성
     * </pre>
     *
     * @param <E>
     * @param data
     *            데이터
     * @param writer
     *            CSV 파일 쓰기 객체
     * @param config
     *            CSV 파일 쓰기 설정
     * @param header
     *            CSV 파일 헤더
     * @param close
     *            CSV 파일 자동 Close 여부
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<Long> write(Collection<E> data, Writer writer, CsvWriteConfig config, String[] header, boolean close) throws IOException {
        return write(data, writer, config, header, defaultCreator(), close);
    }

    /**
     * 여러 개의 객체를 CSV 파일로 저장합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 17.     박준홍         최초 작성
     * </pre>
     *
     * @param <E>
     * @param data
     *            데이터
     * @param writer
     *            CSV 파일 쓰기 객체
     * @param config
     *            CSV 파일 쓰기 설정
     * @param header
     *            CSV 파일 헤더
     * @param beforeCreation
     *            객체 변환하기 전 실행할 함수
     * @param close
     *            CSV 파일 자동 Close 여부
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<Long> write(Collection<E> data, Writer writer, CsvWriteConfig config, String[] header, Consumer<E> beforeCreation, boolean close) throws IOException {
        return write(data, writer, config, header, defaultCreator(), beforeCreation, close);
    }

    /**
     * 여러 개의 객체를 CSV 파일로 저장합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 17.     박준홍         최초 작성
     * </pre>
     *
     * @param <E>
     * @param data
     *            데이터
     * @param writer
     *            CSV 파일 쓰기 객체
     * @param config
     *            CSV 파일 쓰기 설정
     * @param header
     *            CSV 파일 헤더
     * @param creator
     *            객체를 {@link String}[]로 변환하는 함수
     * @param close
     *            CSV 파일 자동 Close 여부
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<Long> write(Collection<E> data, Writer writer, CsvWriteConfig config, String[] header, Function<E, String[]> creator, boolean close)
            throws IOException {
        return write(data, writer, config, header, creator, null, close);
    }

    /**
     * 여러 개의 객체를 CSV 파일로 저장합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 17.     박준홍         최초 작성
     * </pre>
     *
     * @param <E>
     * @param data
     *            데이터
     * @param writer
     *            CSV 파일 쓰기 객체
     * @param config
     *            CSV 파일 쓰기 설정
     * @param header
     *            CSV 파일 헤더
     * @param creator
     *            객체를 {@link String}[]로 변환하는 함수
     * @param beforeCreation
     *            객체 변환하기 전 실행할 함수
     * @param close
     *            CSV 파일 자동 Close 여부
     * @return
     * @throws IOException
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static <E> Result<Long> write(Collection<E> data, Writer writer, CsvWriteConfig config, String[] header, Function<E, String[]> creator, Consumer<E> beforeCreation,
            boolean close) throws IOException {
        return write(data, newCSVWriter(writer, config), header, creator, beforeCreation, close);
    }
}
