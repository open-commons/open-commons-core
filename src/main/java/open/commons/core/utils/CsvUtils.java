/*
 * Copyright 2021 Park Jun-Hong (parkjunhong77@gmail.com)
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

package open.commons.core.utils;

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
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import open.commons.core.Result;
import open.commons.core.csv.CsvConfig;
import open.commons.core.csv.CsvFileConfig;
import open.commons.core.csv.CsvWriteConfig;
import open.commons.core.csv.ReadAt;
import open.commons.core.csv.WriteAt;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.CSVWriterBuilder;
import com.opencsv.ICSVParser;
import com.opencsv.ICSVWriter;
import com.opencsv.exceptions.CsvException;

/**
 * 
 * @since 2021. 11. 11.
 * @version 1.8.0
 * 
 */
public class CsvUtils {

    @SuppressWarnings("null")
    private static final Logger logger = LoggerFactory.getLogger(CsvUtils.class);

    // 리플렉션 메타데이터 캐싱 (Thread-Safe)
    private static final Map<Class<?>, List<MethodInfo<WriteAt>>> WRITE_CACHE = new ConcurrentHashMap<>();
    private static final Map<Class<?>, List<MethodInfo<ReadAt>>> READ_CACHE = new ConcurrentHashMap<>();

    // prevent to create an instance.
    private CsvUtils() {
    }

    /**
     * 문자열셋을 객체를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2026. 3. 16.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param charset
     *            문자열 셋.
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code charset})가 {@code null}인 경우 발생.
     *
     * @since 2026. 3. 16.
     * @version 3.0.0
     * 
     * @see Charset#forName(String)
     */
    public static Charset charset(String charset) {
        Objects.requireNonNull(charset);
        return Objects.requireNonNull(Charset.forName(charset));
    }

    /**
     * 기본 문자열셋을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2026. 3. 27.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2026. 3. 27.
     * @version 3.0.0
     * 
     * @see Charset#defaultCharset()
     */
    public static Charset defaultCharset() {
        return Objects.requireNonNull(Charset.defaultCharset());
    }

    /**
     * 객체를 {@link String}[]로 변환하는 함수를 제공합니다. <br>
     * *
     * 
     * <pre>
     * [개정이력]
     * 날짜       | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 17.     parkjunohng77@gmail.com         최초 작성
     * 2026. 3. 9.      parkjunhong77@gmail.com         (3.0.0) JDK 25 마이그레이션: 리플렉션 캐싱 및 가변 상태 제거
     * </pre>
     *
     * @param <E>
     *            데이터 타입
     * @return 객체를 문자열 배열로 변환하는 함수 (Function&lt;E, String[]&gt;)
     *
     * @since 2022. 3. 17.
     * @version 3.0.0
     */
    public static final <E> Function<E, String[]> defaultCreator() {
        return object -> {
            if (object == null) {
                throw new NullPointerException("객체는 null 일 수 없습니다.");
            }

            Class<?> type = object.getClass();

            // 캐시에서 정렬된 메소드 정보 획득
            @SuppressWarnings("null")
            List<MethodInfo<WriteAt>> methods = WRITE_CACHE.computeIfAbsent(type,
                    k -> AnnotationUtils.getAnnotatedMethodsAllHierarchy(k, WriteAt.class).stream()
                            .map(m -> new MethodInfo<>(m, m.getAnnotation(WriteAt.class), m.getAnnotation(WriteAt.class).index()))
                            .sorted(Comparator.comparingInt(info -> info.index)).toList());

            // 최대 인덱스 기반 배열 크기 결정
            int maxIdx = methods.stream().mapToInt(info -> info.index).max().orElse(-1);
            String[] data = ArrayUtils.initArray(maxIdx + 1, "");

            for (MethodInfo<WriteAt> info : methods) {
                try {
                    Object value = info.method.invoke(object);
                    if (value != null) {
                        data[info.index] = value.toString();
                    } else if (info.annotation.nullIsEmpty()) {
                        data[info.index] = "";
                    } else {
                        data[info.index] = null;
                    }
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    throw ExceptionUtils.newException(RuntimeException.class, e, "'%s' 객체 변환 에러. 메소드=%s, 위치=%s", type.getName(), info.method.getName(), info.index);
                }
            }

            return data;
        };
    }

    /**
     * 주어진 타입에 해당하는 객체를 생성한 후, 문자열 배열에서 데이터를 추출하여 값을 설정하는 함수를 제공합니다. <br>
     * *
     * 
     * <pre>
     * [개정이력]
     * 날짜       | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 11.    parkjunohng77@gmail.com         최초 작성
     * 2026. 3. 9.      parkjunhong77@gmail.com         (3.0.0) JDK 25 마이그레이션: newInstance() 교체 및 캐싱 적용
     * </pre>
     *
     * @param <E>
     *            데이터 타입
     * @param type
     *            {Class&lt;E&gt;} 데이터 타입 (NOT nullable)
     * @return 배열에서 객체로 데이터를 복원하는 함수 (Function&lt;String[], E&gt;)
     * 
     * @throws NullPointerException
     *             파라미터({@code type})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 11.
     * @version 3.0.0
     * 
     */
    public static final <E> Function<String[], E> defaultCreator(Class<E> type) {
        Objects.requireNonNull(type);

        // 메소드 정보 캐싱
        @SuppressWarnings("null")
        List<MethodInfo<ReadAt>> readMethods = READ_CACHE.computeIfAbsent(type, k -> AnnotationUtils.getAnnotatedMethodsAllHierarchy(k, ReadAt.class).stream().peek(m -> {
            if (m.getParameterTypes().length != 1) {
                throw new UnsupportedOperationException("파라미터는 1개여야 합니다: " + m.getName());
            }
        }).map(m -> new MethodInfo<>(m, m.getAnnotation(ReadAt.class), m.getAnnotation(ReadAt.class).index())).toList());

        return arr -> {
            try {
                // JDK 9+ 표준 생성 방식
                E o = type.getDeclaredConstructor().newInstance();

                for (MethodInfo<ReadAt> info : readMethods) {
                    if (info.index < arr.length) {
                        String value = arr[info.index];
                        if (value != null)
                            value = value.trim();

                        Class<?> argType = info.method.getParameterTypes()[0];
                        Object arg = null;

                        if (String.class.equals(argType)) {
                            arg = value;
                        } else if (!StringUtils.isNullOrEmptyString(value)) {
                            if (argType != null && value != null) {
                                arg = ConvertUtils.toPrimitiveTypeValue(argType, value, info.annotation.unsigned());
                            }
                        }

                        if (arg != null) {
                            info.method.invoke(o, arg);
                        }
                    }
                }
                return o;
            } catch (Exception e) {
                throw ExceptionUtils.newException(RuntimeException.class, e, "'%s' 객체 복원 에러", type.getName());
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
     * 2021. 11. 12.        parkjunohng77@gmail.com         최초 작성
     * 2026. 2. 26.         parkjunhong77@gmail.com         (3.0.0) OpenCSV 5.9 Builder 패턴 적용
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
     * 
     */
    // 아래 내용에 적용됨.
    // - CSVreaderBuilder.build()
    // [PATCH] 외부 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    private static CSVReader newCSVReader(Reader reader, CsvFileConfig config) {
        // 1. 파싱 관련 상세 설정 (구분자, 인용구, 이스케이프 등)
        ICSVParser parser = new CSVParserBuilder() //
                .withSeparator(config.getSeparator()) //
                .withQuoteChar(config.getQuotechar()) //
                .withEscapeChar(config.getEscape()) //
                .withStrictQuotes(config.isStrictQuotes()) //
                .withIgnoreLeadingWhiteSpace(config.isIgnoreLeadingWhiteSpace()) //
                .build();

        // 2. Reader 생성 및 Skip 라인 설정
        return new CSVReaderBuilder(reader) //
                .withSkipLines(config.getSkip()) //
                .withCSVParser(parser) //
                .build();
    }

    /**
     * CSV 데이터를 읽을 {@link Reader}, CSV 설정정보를 이용하여 {@link CSVReader} 객체를 제공합니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 12.        parkjunohng77@gmail.com         최초 작성
     * 2026. 2. 26.         parkjunhong77@gmail.com         (3.0.0) OpenCSV 5.9 Builder 패턴 적용 및 LineEnd 설정 추가 및 리턴타입 변경 ({@link CSVWriter} -> {@link ICSVWriter})
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
     * 
     */
    // 아래 내용에 적용됨.
    // - CSVreaderBuilder.build()
    // [PATCH] 외부 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    private static ICSVWriter newCSVWriter(Writer writer, CsvWriteConfig config) {
        return new CSVWriterBuilder(writer) //
                .withSeparator(config.getSeparator()) //
                .withQuoteChar(config.getQuotechar()) //
                .withEscapeChar(config.getEscape()) //
                .withLineEnd(config.getLineEnd()) // 사용자가 추가하신 줄바꿈 문자열 설정
                .build();
    }

    /**
     * 여러 개의 객체의 데이터를 {@link String}[][]로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 3. 17.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     *
     * @param <E>
     * @param objects
     *            데이터
     * @return
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * 
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
     * 2022. 3. 17.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     *
     * @param <E>
     * @param objects
     *            데이터
     * @param creator
     *            {@link String}[] 생성 함수
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code objects})가 {@code null}이거나 {@code null}을 포함 또는 {@code creator}가 {@code null}인 경우 발생.
     * 
     * @since 2022. 3. 17.
     * @version 1.8.0
     * 
     */
    public static <E> String[][] objectsToArray(Collection<E> objects, Function<E, String[]> creator) {
        Objects.requireNonNull(creator);
        AssertUtils2.collectionNotNull(objects);

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
     * 2022. 3. 17.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     *
     * @param <E>
     * @param objects
     *            데이터
     * @return
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * 
     */
    // 아래 내용에 적용됨.
    // - Arrays.asList(objects)
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    @SafeVarargs
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
     * 2022. 3. 17.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     *
     * @param <E>
     * @param creator
     *            {@link String}[] 생성 함수
     * @param objects
     *            데이터
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code objects})가 {@code null}이거나 {@code null}을 포함, 또는 {@code creator}가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * 
     * 
     * @see #objectsToArray(Collection, Function)
     */
    // 아래 내용에 적용됨.
    // - Arrays.asList(objects)
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    @SafeVarargs
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
     * 2022. 3. 17.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     *
     * @param <E>
     * @param object
     *            데이터
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code object})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * 
     * 
     * @see #objectToArray(Object, Function)
     */
    public static <E> String[] objectToArray(E object) {
        Objects.requireNonNull(object);

        return objectToArray(object, defaultCreator());
    }

    /**
     * 주어진 객체의 데이터를 {@link String}[]로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 3. 17.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     *
     * @param <E>
     * @param object
     *            데이터
     * @param creator
     *            {@link String}[] 생성 함수
     * @return
     * 
     * @throws NullPointerException
     *             파라미터({@code object 또는 creator})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * 
     */
    @SuppressWarnings("null")
    public static <E> String[] objectToArray(E object, Function<E, String[]> creator) {
        ObjectUtils.requireNonNulls(object, creator);

        return creator.apply(object);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 11.        parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param <E>
     *            CSV 파일을 읽어서 생성할 데이터 모델. {@link ReadAt}이 설정된 메소드를 호출하여 데이터를 설정합니다.
     * @param reader
     *            CSV 데이터 (<b>{@code NOT nullable}</b>)
     * @param type
     *            데이터 타입. (<b>{@code NOT nullable}</b>)
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws NullPointerException
     *             파라미터({@code reader, type 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 11.
     * @version 1.8.0
     * 
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
     * 2021. 11. 17.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     *
     * @param <E>
     *            CSV 파일을 읽어서 생성할 데이터 모델. {@link ReadAt}이 설정된 메소드를 호출하여 데이터를 설정합니다.
     * @param reader
     *            CSV 데이터 (<b>{@code NOT nullable}</b>)
     * @param type
     *            데이터 타입. (<b>{@code NOT nullable}</b>)
     * @param afterCreation
     *            객체 생성 후 작업.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code reader, type 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 17.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(CSVReader reader, Class<E> type, @Nullable Consumer<E> afterCreation, boolean close) throws IOException {
        return readAsList(reader, defaultCreator(type), afterCreation, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.        parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param <E>
     *            CSV 파일을 읽어서 생성할 데이터 모델. {@link ReadAt}이 설정된 메소드를 호출하여 데이터를 설정합니다.
     * @param reader
     *            CSV 데이터 (<b>{@code NOT nullable}</b>)
     * @param type
     *            데이터 타입. (<b>{@code NOT nullable}</b>)
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws NullPointerException
     *             파라미터({@code reader, type 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(CSVReader reader, Class<E> type, @Nullable Predicate<E> validator, boolean close) throws IOException {
        return readAsList(reader, defaultCreator(type), validator, null, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param <E>
     *            CSV 파일을 읽어서 생성할 데이터 모델. {@link ReadAt}이 설정된 메소드를 호출하여 데이터를 설정합니다.
     * @param reader
     *            CSV 데이터 (<b>{@code NOT nullable}</b>)
     * @param type
     *            데이터 타입. (<b>{@code NOT nullable}</b>)
     * @param validator
     *            데이터 검증 함수.
     * @param afterCreation
     *            객체 생성 후 작업.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code reader, type 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(CSVReader reader, Class<E> type, @Nullable Predicate<E> validator, @Nullable Consumer<E> afterCreation, boolean close)
            throws IOException {
        return readAsList(reader, defaultCreator(type), validator, afterCreation, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 11.        parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param <E>
     *            CSV 파일을 읽어서 생성할 데이터 모델. {@link ReadAt}이 설정된 메소드를 호출하여 데이터를 설정합니다.
     * @param reader
     *            CSV 데이터 (<b>{@code NOT nullable}</b>)
     * @param creator
     *            {@link String}[]를 전달받아 데이터를 생성하는 함수.<br>
     *            객체의 {@code setter} 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b>{@code NOT nullable}</b>)</font>
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code reader, creator 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 11.
     * @version 1.8.0
     * 
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
     * 2021. 11. 17.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     *
     * @param <E>
     *            CSV 파일을 읽어서 생성할 데이터 모델. {@link ReadAt}이 설정된 메소드를 호출하여 데이터를 설정합니다.
     * @param reader
     *            CSV 데이터 (<b>{@code NOT nullable}</b>)
     * @param creator
     *            {@link String}[]를 전달받아 데이터를 생성하는 함수.<br>
     *            객체의 {@code setter} 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b>{@code NOT nullable}</b>)</font>
     * @param afterCreation
     *            객체 생성 후 작업.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code reader, creator 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 17.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(CSVReader reader, Function<String[], E> creator, @Nullable Consumer<E> afterCreation, boolean close) throws IOException {
        return readAsList(reader, creator, (Predicate<E>) null, afterCreation, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.        parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param <E>
     *            CSV 파일을 읽어서 생성할 데이터 모델. {@link ReadAt}이 설정된 메소드를 호출하여 데이터를 설정합니다.
     * @param reader
     *            CSV 데이터 (<b>{@code NOT nullable}</b>)
     * @param creator
     *            {@link String}[]를 전달받아 데이터를 생성하는 함수.<br>
     *            객체의 {@code setter} 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, Predicate, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b>{@code NOT nullable}</b>)</font>
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code reader, creator 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * 
     * 
     * @see ReadAt
     */
    public static <E> Result<List<E>> readAsList(CSVReader reader, Function<String[], E> creator, @Nullable Predicate<E> validator, boolean close) throws IOException {
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
     * 2022. 3. 30.     parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param <E>
     *            CSV 파일을 읽어서 생성할 데이터 모델. {@link ReadAt}이 설정된 메소드를 호출하여 데이터를 설정합니다.
     * @param reader
     *            CSV 데이터 (<b>{@code NOT nullable}</b>)
     * @param creator
     *            {@link String}[]를 전달받아 데이터를 생성하는 함수.<br>
     *            객체의 {@code setter} 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, Predicate, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b>{@code NOT nullable}</b>)</font>
     * @param validator
     *            데이터 검증 함수.
     * @param afterCreation
     *            객체 생성 후 작업.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * 
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code reader, creator 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 30.
     */
    public static <E> Result<List<E>> readAsList(CSVReader reader, Function<String[], E> creator, @Nullable Predicate<E> validator, @Nullable Consumer<E> afterCreation,
            boolean close) throws IOException {
        ObjectUtils.requireNonNulls(reader, creator);

        List<E> data = new ArrayList<>();
        try {
            String[] readline = null;
            while ((readline = reader.readNext()) != null) {
                // 객체 생성
                E e = creator.apply(readline);
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
        } catch (IOException | CsvException e) {
            String errMsg = String.format("CSV 파일을 읽는 도중 에러가 발생하였습니다. 원인=%s", e.getMessage());
            logger.error(errMsg, e);
            throw new IOException(errMsg, e);
        } finally {
            if (close) {
                IOUtils.close(reader);
            }
        }
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 12.		parkjunohng77@gmail.com			최초 작성
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
     *            데이터 타입. (<b>{@code NOT nullable}</b>)
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inputStream, charset, type 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset //
            , char separator, char quotechar, char escape, boolean strictQuotes, boolean ignoreLeadingWhiteSpace, Class<E> type, boolean close) throws IOException {
        return readAsList(inputStream, charset, CsvFileConfig.DEFAULT_SKIP_LINE_COUNT, separator, quotechar, escape, strictQuotes, ignoreLeadingWhiteSpace, type, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     parkjunohng77@gmail.com         최초 작성
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
     *            데이터 타입. (<b>{@code NOT nullable}</b>)
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inputStream, charset, type 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset //
            , char separator, char quotechar, char escape, boolean strictQuotes, boolean ignoreLeadingWhiteSpace, Class<E> type, @Nullable Predicate<E> validator, boolean close)
            throws IOException {
        return readAsList(inputStream, charset, CsvFileConfig.DEFAULT_SKIP_LINE_COUNT, separator, quotechar, escape, strictQuotes, ignoreLeadingWhiteSpace, type, validator, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 12.		parkjunohng77@gmail.com			최초 작성
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
     *            객체의 {@code setter} 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b>{@code NOT nullable}</b>)</font>
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inputStream, charset, creator 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset //
            , char separator, char quotechar, char escape, boolean strictQuotes, boolean ignoreLeadingWhiteSpace, Function<String[], E> creator, boolean close) throws IOException {
        return readAsList(inputStream, charset, 0, separator, quotechar, escape, strictQuotes, ignoreLeadingWhiteSpace, creator, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     parkjunohng77@gmail.com         최초 작성
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
     *            객체의 {@code setter} 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, Predicate, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b>{@code NOT nullable}</b>)</font>
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inputStream, charset, creator 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset //
            , char separator, char quotechar, char escape, boolean strictQuotes, boolean ignoreLeadingWhiteSpace, Function<String[], E> creator, @Nullable Predicate<E> validator,
            boolean close) throws IOException {
        return readAsList(inputStream, charset, 0, separator, quotechar, escape, strictQuotes, ignoreLeadingWhiteSpace, creator, validator, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 12.		parkjunohng77@gmail.com			최초 작성
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
     *            데이터 타입. (<b>{@code NOT nullable}</b>)
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inputStream, charset, type 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset //
            , char separator, char quotechar, char escape, boolean strictQuotes, Class<E> type, boolean close) throws IOException {
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
     * 2022. 3. 30.     parkjunohng77@gmail.com         최초 작성
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
     *            데이터 타입. (<b>{@code NOT nullable}</b>)
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inputStream, charset, type 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset //
            , char separator, char quotechar, char escape, boolean strictQuotes, Class<E> type, @Nullable Predicate<E> validator, boolean close) throws IOException {
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
     * 2021. 11. 12.		parkjunohng77@gmail.com			최초 작성
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
     *            객체의 {@code setter} 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b>{@code NOT nullable}</b>)</font>
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inputStream, charset, creator 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset //
            , char separator, char quotechar, char escape, boolean strictQuotes, Function<String[], E> creator, boolean close) throws IOException {
        return readAsList(inputStream, charset, 0, separator, quotechar, escape, strictQuotes, CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, creator, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     parkjunohng77@gmail.com         최초 작성
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
     *            객체의 {@code setter} 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, Predicate, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b>{@code NOT nullable}</b>)</font>
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inputStream, charset, creator 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset //
            , char separator, char quotechar, char escape, boolean strictQuotes, Function<String[], E> creator, @Nullable Predicate<E> validator, boolean close)
            throws IOException {
        return readAsList(inputStream, charset, 0, separator, quotechar, escape, strictQuotes, CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, creator, validator, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 12.		parkjunohng77@gmail.com			최초 작성
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
     *            데이터 타입. (<b>{@code NOT nullable}</b>)
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inputStream, charset, type 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * 
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
     * 2022. 3. 30.     parkjunohng77@gmail.com         최초 작성
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
     *            데이터 타입. (<b>{@code NOT nullable}</b>)
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     *
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inputStream, charset, type 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset, char separator, char quotechar, char escape, Class<E> type,
            @Nullable Predicate<E> validator, boolean close) throws IOException {
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
     * 2021. 11. 12.		parkjunohng77@gmail.com			최초 작성
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
     *            객체의 {@code setter} 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b>{@code NOT nullable}</b>)</font>
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inputStream, charset, creator 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * 
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
     * 2022. 3. 30.     parkjunohng77@gmail.com         최초 작성
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
     *            객체의 {@code setter} 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, Predicate, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b>{@code NOT nullable}</b>)</font>
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inputStream, charset, creator 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * 
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
     * 2021. 11. 12.		parkjunohng77@gmail.com			최초 작성
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
     *            데이터 타입. (<b>{@code NOT nullable}</b>)
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inputStream, charset, type 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * 
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
     * 2022. 3. 30.     parkjunohng77@gmail.com         최초 작성
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
     *            데이터 타입. (<b>{@code NOT nullable}</b>)
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inputStream, charset, type 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset, char separator, char quotechar, Class<E> type, @Nullable Predicate<E> validator,
            boolean close) throws IOException {
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
     * 2021. 11. 12.		parkjunohng77@gmail.com			최초 작성
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
     *            객체의 {@code setter} 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b>{@code NOT nullable}</b>)</font>
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inputStream, charset, creator 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * 
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
     * 2022. 3. 30.     parkjunohng77@gmail.com         최초 작성
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
     *            객체의 {@code setter} 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, Predicate, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b>{@code NOT nullable}</b>)</font>
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inputStream, charset, creator 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset, char separator, char quotechar, Function<String[], E> creator,
            @Nullable Predicate<E> validator, boolean close) throws IOException {
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
     * 2021. 11. 12.		parkjunohng77@gmail.com			최초 작성
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
     *            데이터 타입. (<b>{@code NOT nullable}</b>)
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inputStream, charset, type 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * 
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
     * 2022. 3. 30.     parkjunohng77@gmail.com         최초 작성
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
     *            데이터 타입. (<b>{@code NOT nullable}</b>)
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inputStream, charset, type 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset, char separator, Class<E> type, @Nullable Predicate<E> validator, boolean close)
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
     * 2021. 11. 12.		parkjunohng77@gmail.com			최초 작성
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
     *            객체의 {@code setter} 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b>{@code NOT nullable}</b>)</font>
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inputStream, charset, creator 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * 
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
     * 2022. 3. 30.     parkjunohng77@gmail.com         최초 작성
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
     *            객체의 {@code setter} 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, Predicate, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b>{@code NOT nullable}</b>)</font>
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inputStream, charset, creator 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset, char separator, Function<String[], E> creator, @Nullable Predicate<E> validator,
            boolean close) throws IOException {
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
     * 2021. 11. 12.		parkjunohng77@gmail.com			최초 작성
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
     *            데이터 타입. (<b>{@code NOT nullable}</b>)
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inputStream, charset, type 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset, int skip, char separator, char quotechar, char escape, boolean strictQuotes,
            boolean ignoreLeadingWhiteSpace, Class<E> type, boolean close) throws IOException {
        Objects.requireNonNull(charset);

        return readAsList(inputStream, new CsvFileConfig(separator, quotechar, escape, strictQuotes, ignoreLeadingWhiteSpace, skip, charset), type, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 17.		parkjunohng77@gmail.com			최초 작성
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
     *            데이터 타입. (<b>{@code NOT nullable}</b>)
     * @param afterCreation
     *            객체 생성 후 작업.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inputStream, charset, type 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 17.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset, int skip, char separator, char quotechar, char escape, boolean strictQuotes,
            boolean ignoreLeadingWhiteSpace, Class<E> type, @Nullable Consumer<E> afterCreation, boolean close) throws IOException {
        return readAsList(inputStream, new CsvFileConfig(separator, quotechar, escape, strictQuotes, ignoreLeadingWhiteSpace, skip, charset), type, afterCreation, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     parkjunohng77@gmail.com         최초 작성
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
     *            데이터 타입. (<b>{@code NOT nullable}</b>)
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inputStream, charset, type 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset, int skip, char separator, char quotechar, char escape, boolean strictQuotes,
            boolean ignoreLeadingWhiteSpace, Class<E> type, @Nullable Predicate<E> validator, boolean close) throws IOException {
        return readAsList(inputStream, new CsvFileConfig(separator, quotechar, escape, strictQuotes, ignoreLeadingWhiteSpace, skip, charset), type, validator, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     parkjunohng77@gmail.com         최초 작성
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
     *            데이터 타입. (<b>{@code NOT nullable}</b>)
     * @param validator
     *            데이터 검증 함수.
     * @param afterCreation
     *            객체 생성 후 작업.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inputStream, charset, type 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset, int skip, char separator, char quotechar, char escape, boolean strictQuotes,
            boolean ignoreLeadingWhiteSpace, Class<E> type, @Nullable Predicate<E> validator, @Nullable Consumer<E> afterCreation, boolean close) throws IOException {
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
     * 2021. 11. 12.		parkjunohng77@gmail.com			최초 작성
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
     *            객체의 {@code setter} 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b>{@code NOT nullable}</b>)</font>
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inputStream, charset, creator 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset, int skip, char separator, char quotechar, char escape, boolean strictQuotes,
            boolean ignoreLeadingWhiteSpace, Function<String[], E> creator, boolean close) throws IOException {
        return readAsList(inputStream, new CsvFileConfig(separator, quotechar, escape, strictQuotes, ignoreLeadingWhiteSpace, skip, charset), creator, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     parkjunohng77@gmail.com         최초 작성
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
     *            객체의 {@code setter} 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, Predicate, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b>{@code NOT nullable}</b>)</font>
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inputStream, charset, creator 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset, int skip, char separator, char quotechar, char escape, boolean strictQuotes,
            boolean ignoreLeadingWhiteSpace, Function<String[], E> creator, @Nullable Predicate<E> validator, boolean close) throws IOException {
        return readAsList(inputStream, new CsvFileConfig(separator, quotechar, escape, strictQuotes, ignoreLeadingWhiteSpace, skip, charset), creator, validator, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 12.		parkjunohng77@gmail.com			최초 작성
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
     *            데이터 타입. (<b>{@code NOT nullable}</b>)
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inputStream, charset, type 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset, int skip, char separator, char quotechar, char escape, boolean strictQuotes,
            Class<E> type, boolean close) throws IOException {
        return readAsList(inputStream, charset, skip, separator, quotechar, escape, strictQuotes, CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, type, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     parkjunohng77@gmail.com         최초 작성
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
     *            데이터 타입. (<b>{@code NOT nullable}</b>)
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inputStream, charset, type 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset, int skip, char separator, char quotechar, char escape, boolean strictQuotes,
            Class<E> type, @Nullable Predicate<E> validator, boolean close) throws IOException {
        return readAsList(inputStream, charset, skip, separator, quotechar, escape, strictQuotes, CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, type, validator, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 12.		parkjunohng77@gmail.com			최초 작성
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
     *            객체의 {@code setter} 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b>{@code NOT nullable}</b>)</font>
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inputStream, charset, creator 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset, int skip, char separator, char quotechar, char escape, boolean strictQuotes,
            Function<String[], E> creator, boolean close) throws IOException {
        return readAsList(inputStream, charset, skip, separator, quotechar, escape, strictQuotes, CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, creator, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     parkjunohng77@gmail.com         최초 작성
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
     *            객체의 {@code setter} 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, Predicate, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b>{@code NOT nullable}</b>)</font>
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inputStream, charset, creator 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset, int skip, char separator, char quotechar, char escape, boolean strictQuotes,
            Function<String[], E> creator, @Nullable Predicate<E> validator, boolean close) throws IOException {
        return readAsList(inputStream, charset, skip, separator, quotechar, escape, strictQuotes, CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, creator, validator, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 12.		parkjunohng77@gmail.com			최초 작성
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
     *            데이터 타입. (<b>{@code NOT nullable}</b>)
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inputStream, charset, type 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * 
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
     * 2022. 3. 30.     parkjunohng77@gmail.com         최초 작성
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
     *            데이터 타입. (<b>{@code NOT nullable}</b>)
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inputStream, charset, type 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * 
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
     * 2021. 11. 12.		parkjunohng77@gmail.com			최초 작성
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
     *            객체의 {@code setter} 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b>{@code NOT nullable}</b>)</font>
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inputStream, charset, creator 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * 
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
     * 2022. 3. 30.     parkjunohng77@gmail.com         최초 작성
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
     *            객체의 {@code setter} 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, Predicate, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b>{@code NOT nullable}</b>)</font>
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inputStream, charset, creator 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * 
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
     * 2021. 11. 12.		parkjunohng77@gmail.com			최초 작성
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
     *            데이터 타입. (<b>{@code NOT nullable}</b>)
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inputStream, charset, type 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * 
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
     * 2022. 3. 30.     parkjunohng77@gmail.com         최초 작성
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
     *            데이터 타입. (<b>{@code NOT nullable}</b>)
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inputStream, charset, type 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset, int skip, char separator, char quotechar, Class<E> type,
            @Nullable Predicate<E> validator, boolean close) throws IOException {
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
     * 2021. 11. 12.		parkjunohng77@gmail.com			최초 작성
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
     *            객체의 {@code setter} 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b>{@code NOT nullable}</b>)</font>
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inputStream, charset, creator 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * 
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
     * 2022. 3. 30.     parkjunohng77@gmail.com         최초 작성
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
     *            객체의 {@code setter} 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, Predicate, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b>{@code NOT nullable}</b>)</font>
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inputStream, charset, creator 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * 
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
     * 2021. 11. 12.		parkjunohng77@gmail.com			최초 작성
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
     *            데이터 타입. (<b>{@code NOT nullable}</b>)
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inputStream, charset, type 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * 
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
     * 2022. 3. 30.     parkjunohng77@gmail.com         최초 작성
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
     *            데이터 타입. (<b>{@code NOT nullable}</b>)
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inputStream, charset, type 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset, int skip, char separator, Class<E> type, @Nullable Predicate<E> validator, boolean close)
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
     * 2021. 11. 12.		parkjunohng77@gmail.com			최초 작성
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
     *            객체의 {@code setter} 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b>{@code NOT nullable}</b>)</font>
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inputStream, charset, creator 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * 
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
     * 2022. 3. 30.     parkjunohng77@gmail.com         최초 작성
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
     *            객체의 {@code setter} 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, Predicate, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b>{@code NOT nullable}</b>)</font>
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inputStream, charset, creator 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, Charset charset, int skip, char separator, Function<String[], E> creator,
            @Nullable Predicate<E> validator, boolean close) throws IOException {
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
     * 2021. 11. 11.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     *
     * @param <E>
     *            CSV 파일을 읽어서 생성할 데이터 모델. {@link ReadAt}이 설정된 메소드를 호출하여 데이터를 설정합니다.
     * @param inputStream
     *            CSV 데이터 (<b>{@code NOT nullable}</b>)
     * @param config
     *            CSV 파일을 읽기 위한 설정 (<b>{@code NOT nullable}</b>)
     * @param type
     *            데이터 타입. (<b>{@code NOT nullable}</b>)
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws NullPointerException
     *             파라미터({@code inputStream, type 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 11.
     * @version 1.8.0
     * 
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
     * 2021. 11. 17.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     *
     * @param <E>
     *            CSV 파일을 읽어서 생성할 데이터 모델. {@link ReadAt}이 설정된 메소드를 호출하여 데이터를 설정합니다.
     * @param inputStream
     *            CSV 데이터 (<b>{@code NOT nullable}</b>)
     * @param config
     *            CSV 파일을 읽기 위한 설정 (<b>{@code NOT nullable}</b>)
     * @param type
     *            데이터 타입. (<b>{@code NOT nullable}</b>)
     * @param afterCreation
     *            객체 생성 후 작업.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws NullPointerException
     *             파라미터({@code inputStream, type 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 17.
     * @version 1.8.0
     * 
     * 
     * @see ReadAt
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, CsvFileConfig config, Class<E> type, @Nullable Consumer<E> afterCreation, boolean close)
            throws IOException {
        return readAsList(new InputStreamReader(inputStream, config.getCharset()), config, type, afterCreation, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param <E>
     *            CSV 파일을 읽어서 생성할 데이터 모델. {@link ReadAt}이 설정된 메소드를 호출하여 데이터를 설정합니다.
     * @param inputStream
     *            CSV 데이터 (<b>{@code NOT nullable}</b>)
     * @param config
     *            CSV 파일을 읽기 위한 설정 (<b>{@code NOT nullable}</b>)
     * @param type
     *            데이터 타입. (<b>{@code NOT nullable}</b>)
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws NullPointerException
     *             파라미터({@code inputStream, type 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * 
     * 
     * @see ReadAt
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, CsvFileConfig config, Class<E> type, @Nullable Predicate<E> validator, boolean close) throws IOException {
        return readAsList(new InputStreamReader(inputStream, config.getCharset()), config, type, validator, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param <E>
     *            CSV 파일을 읽어서 생성할 데이터 모델. {@link ReadAt}이 설정된 메소드를 호출하여 데이터를 설정합니다.
     * @param inputStream
     *            CSV 데이터 (<b>{@code NOT nullable}</b>)
     * @param config
     *            CSV 파일을 읽기 위한 설정 (<b>{@code NOT nullable}</b>)
     * @param type
     *            데이터 타입. (<b>{@code NOT nullable}</b>)
     * @param validator
     *            데이터 검증 함수.
     * @param afterCreation
     *            객체 생성 후 작업.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws NullPointerException
     *             파라미터({@code inputStream, type 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * 
     * 
     * @see ReadAt
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, CsvFileConfig config, Class<E> type, @Nullable Predicate<E> validator,
            @Nullable Consumer<E> afterCreation, boolean close) throws IOException {
        return readAsList(new InputStreamReader(inputStream, config.getCharset()), config, type, validator, afterCreation, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 11.		parkjunohng77@gmail.com			최초 작성
     * </pre>
     *
     * @param <E>
     *            CSV 파일을 읽어서 생성할 데이터 모델. {@link ReadAt}이 설정된 메소드를 호출하여 데이터를 설정합니다.
     * @param inputStream
     *            CSV 데이터 (<b>{@code NOT nullable}</b>)
     * @param config
     *            CSV 파일을 읽기 위한 설정 (<b>{@code NOT nullable}</b>)
     * @param creator
     *            {@link String}[]를 전달받아 데이터를 생성하는 함수.<br>
     *            객체의 {@code setter} 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b>{@code NOT nullable}</b>)</font>
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inputStream, creator 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 11.
     * @version 1.8.0
     * 
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
     * 2022. 3. 30.     parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param <E>
     *            CSV 파일을 읽어서 생성할 데이터 모델. {@link ReadAt}이 설정된 메소드를 호출하여 데이터를 설정합니다.
     * @param inputStream
     *            CSV 데이터 (<b>{@code NOT nullable}</b>)
     * @param config
     *            CSV 파일을 읽기 위한 설정 (<b>{@code NOT nullable}</b>)
     * @param creator
     *            {@link String}[]를 전달받아 데이터를 생성하는 함수.<br>
     *            객체의 {@code setter} 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, Predicate, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b>{@code NOT nullable}</b>)</font>
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inputStream, creator 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * 
     * 
     * @see ReadAt
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, CsvFileConfig config, Function<String[], E> creator, @Nullable Predicate<E> validator, boolean close)
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
     * 2021. 11. 12.		parkjunohng77@gmail.com			최초 작성
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
     *            데이터 타입. (<b>{@code NOT nullable}</b>)
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inputStream, type 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, int skip, char separator, char quotechar, char escape, boolean strictQuotes,
            boolean ignoreLeadingWhiteSpace, Class<E> type, boolean close) throws IOException {
        return readAsList(inputStream, defaultCharset(), skip, separator, quotechar, escape, strictQuotes, ignoreLeadingWhiteSpace, type, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     parkjunohng77@gmail.com         최초 작성
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
     *            데이터 타입. (<b>{@code NOT nullable}</b>)
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inputStream, type 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, int skip, char separator, char quotechar, char escape, boolean strictQuotes,
            boolean ignoreLeadingWhiteSpace, Class<E> type, @Nullable Predicate<E> validator, boolean close) throws IOException {
        return readAsList(inputStream, defaultCharset(), skip, separator, quotechar, escape, strictQuotes, ignoreLeadingWhiteSpace, type, validator, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 12.		parkjunohng77@gmail.com			최초 작성
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
     *            객체의 {@code setter} 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b>{@code NOT nullable}</b>)</font>
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inputStream, creator 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, int skip, char separator, char quotechar, char escape, boolean strictQuotes,
            boolean ignoreLeadingWhiteSpace, Function<String[], E> creator, boolean close) throws IOException {
        return readAsList(inputStream, defaultCharset(), skip, separator, quotechar, escape, strictQuotes, ignoreLeadingWhiteSpace, creator, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     parkjunohng77@gmail.com         최초 작성
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
     *            객체의 {@code setter} 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, Predicate, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b>{@code NOT nullable}</b>)</font>
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inputStream, creator 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, int skip, char separator, char quotechar, char escape, boolean strictQuotes,
            boolean ignoreLeadingWhiteSpace, Function<String[], E> creator, @Nullable Predicate<E> validator, boolean close) throws IOException {
        return readAsList(inputStream, defaultCharset(), skip, separator, quotechar, escape, strictQuotes, ignoreLeadingWhiteSpace, creator, validator, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 12.		parkjunohng77@gmail.com			최초 작성
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
     *            데이터 타입. (<b>{@code NOT nullable}</b>)
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inputStream, type 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, int skip, char separator, char quotechar, char escape, boolean strictQuotes, Class<E> type, boolean close)
            throws IOException {
        return readAsList(inputStream, defaultCharset(), skip, separator, quotechar, escape, strictQuotes, CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, type, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     parkjunohng77@gmail.com         최초 작성
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
     *            데이터 타입. (<b>{@code NOT nullable}</b>)
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inputStream, type 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, int skip, char separator, char quotechar, char escape, boolean strictQuotes, Class<E> type,
            Predicate<E> validator, boolean close) throws IOException {
        return readAsList(inputStream, defaultCharset(), skip, separator, quotechar, escape, strictQuotes, CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, type, validator, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 12.		parkjunohng77@gmail.com			최초 작성
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
     *            객체의 {@code setter} 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b>{@code NOT nullable}</b>)</font>
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inputStream, creator 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, int skip, char separator, char quotechar, char escape, boolean strictQuotes,
            Function<String[], E> creator, boolean close) throws IOException {
        return readAsList(inputStream, defaultCharset(), skip, separator, quotechar, escape, strictQuotes, CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, creator, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     parkjunohng77@gmail.com         최초 작성
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
     *            객체의 {@code setter} 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, Predicate, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b>{@code NOT nullable}</b>)</font>
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inputStream, creator 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, int skip, char separator, char quotechar, char escape, boolean strictQuotes,
            Function<String[], E> creator, @Nullable Predicate<E> validator, boolean close) throws IOException {
        return readAsList(inputStream, defaultCharset(), skip, separator, quotechar, escape, strictQuotes, CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, creator, validator, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 12.		parkjunohng77@gmail.com			최초 작성
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
     *            데이터 타입. (<b>{@code NOT nullable}</b>)
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inputStream, type 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, int skip, char separator, char quotechar, char escape//
            , Class<E> type, boolean close) throws IOException {
        return readAsList(inputStream, defaultCharset(), skip, separator, quotechar, escape, CsvConfig.DEFAULT_STRICT_QUOTES, CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, type,
                close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     parkjunohng77@gmail.com         최초 작성
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
     *            데이터 타입. (<b>{@code NOT nullable}</b>)
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inputStream, type 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, int skip, char separator, char quotechar, char escape//
            , Class<E> type, @Nullable Predicate<E> validator, boolean close) throws IOException {
        return readAsList(inputStream, defaultCharset(), skip, separator, quotechar, escape, CsvConfig.DEFAULT_STRICT_QUOTES, CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, type,
                validator, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 12.		parkjunohng77@gmail.com			최초 작성
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
     *            객체의 {@code setter} 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b>{@code NOT nullable}</b>)</font>
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inputStream, creator 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, int skip, char separator, char quotechar, char escape//
            , Function<String[], E> creator, boolean close) throws IOException {
        return readAsList(inputStream, defaultCharset(), skip, separator, quotechar, escape, CsvConfig.DEFAULT_STRICT_QUOTES, CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, creator,
                close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     parkjunohng77@gmail.com         최초 작성
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
     *            객체의 {@code setter} 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, Predicate, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b>{@code NOT nullable}</b>)</font>
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inputStream, creator 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, int skip, char separator, char quotechar, char escape//
            , Function<String[], E> creator, @Nullable Predicate<E> validator, boolean close) throws IOException {
        return readAsList(inputStream, defaultCharset(), skip, separator, quotechar, escape, CsvConfig.DEFAULT_STRICT_QUOTES, CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, creator,
                validator, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 12.		parkjunohng77@gmail.com			최초 작성
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
     *            데이터 타입. (<b>{@code NOT nullable}</b>)
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inputStream, type 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, int skip, char separator, char quotechar, Class<E> type, boolean close) throws IOException {
        return readAsList(inputStream, defaultCharset(), skip, separator, quotechar, CsvConfig.DEFAULT_ESCAPE_CHARACTER, CsvConfig.DEFAULT_STRICT_QUOTES,
                CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, type, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     parkjunohng77@gmail.com         최초 작성
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
     *            데이터 타입. (<b>{@code NOT nullable}</b>)
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inputStream, type 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, int skip, char separator, char quotechar, Class<E> type, @Nullable Predicate<E> validator, boolean close)
            throws IOException {
        return readAsList(inputStream, defaultCharset(), skip, separator, quotechar, CsvConfig.DEFAULT_ESCAPE_CHARACTER, CsvConfig.DEFAULT_STRICT_QUOTES,
                CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, type, validator, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 12.		parkjunohng77@gmail.com			최초 작성
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
     *            객체의 {@code setter} 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b>{@code NOT nullable}</b>)</font>
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inputStream, creator 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, int skip, char separator, char quotechar, Function<String[], E> creator, boolean close)
            throws IOException {
        return readAsList(inputStream, defaultCharset(), skip, separator, quotechar, CsvConfig.DEFAULT_ESCAPE_CHARACTER, CsvConfig.DEFAULT_STRICT_QUOTES,
                CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, creator, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     parkjunohng77@gmail.com         최초 작성
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
     *            객체의 {@code setter} 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, Predicate, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b>{@code NOT nullable}</b>)</font>
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inputStream, creator 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, int skip, char separator, char quotechar, Function<String[], E> creator, @Nullable Predicate<E> validator,
            boolean close) throws IOException {
        return readAsList(inputStream, defaultCharset(), skip, separator, quotechar, CsvConfig.DEFAULT_ESCAPE_CHARACTER, CsvConfig.DEFAULT_STRICT_QUOTES,
                CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, creator, validator, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 12.		parkjunohng77@gmail.com			최초 작성
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
     *            데이터 타입. (<b>{@code NOT nullable}</b>)
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inputStream, type 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, int skip, char separator, Class<E> type, boolean close) throws IOException {
        readAsList(inputStream, skip, separator, defaultCreator(type), close);
        return readAsList(inputStream, defaultCharset(), skip, separator, CsvConfig.DEFAULT_QUOTE_CHARACTER, CsvConfig.DEFAULT_ESCAPE_CHARACTER, CsvConfig.DEFAULT_STRICT_QUOTES,
                CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, type, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     parkjunohng77@gmail.com         최초 작성
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
     *            데이터 타입. (<b>{@code NOT nullable}</b>)
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inputStream, type 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, int skip, char separator, Class<E> type, @Nullable Predicate<E> validator, boolean close)
            throws IOException {
        readAsList(inputStream, skip, separator, defaultCreator(type), validator, close);
        return readAsList(inputStream, defaultCharset(), skip, separator, CsvConfig.DEFAULT_QUOTE_CHARACTER, CsvConfig.DEFAULT_ESCAPE_CHARACTER, CsvConfig.DEFAULT_STRICT_QUOTES,
                CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, type, validator, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 12.		parkjunohng77@gmail.com			최초 작성
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
     *            객체의 {@code setter} 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b>{@code NOT nullable}</b>)</font>
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inputStream, creator 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, int skip, char separator, Function<String[], E> creator, boolean close) throws IOException {
        return readAsList(inputStream, defaultCharset(), skip, separator, CsvConfig.DEFAULT_QUOTE_CHARACTER, CsvConfig.DEFAULT_ESCAPE_CHARACTER, CsvConfig.DEFAULT_STRICT_QUOTES,
                CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, creator, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     parkjunohng77@gmail.com         최초 작성
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
     *            객체의 {@code setter} 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, Predicate, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b>{@code NOT nullable}</b>)</font>
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code inputStream, creator 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(InputStream inputStream, int skip, char separator, Function<String[], E> creator, @Nullable Predicate<E> validator, boolean close)
            throws IOException {
        return readAsList(inputStream, defaultCharset(), skip, separator, CsvConfig.DEFAULT_QUOTE_CHARACTER, CsvConfig.DEFAULT_ESCAPE_CHARACTER, CsvConfig.DEFAULT_STRICT_QUOTES,
                CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, creator, validator, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 11.        parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param <E>
     *            CSV 파일을 읽어서 생성할 데이터 모델. {@link ReadAt}이 설정된 메소드를 호출하여 데이터를 설정합니다.
     * @param reader
     *            CSV 데이터 (<b>{@code NOT nullable}</b>)
     * @param config
     *            CSV 파일을 읽기 위한 설정 (<b>{@code NOT nullable}</b>)
     * @param type
     *            데이터 타입. (<b>{@code NOT nullable}</b>)
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws NullPointerException
     *             파라미터({@code reader, type 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 11.
     * @version 1.8.0
     * 
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
     * 2021. 11. 17.        parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param <E>
     *            CSV 파일을 읽어서 생성할 데이터 모델. {@link ReadAt}이 설정된 메소드를 호출하여 데이터를 설정합니다.
     * @param reader
     *            CSV 데이터 (<b>{@code NOT nullable}</b>)
     * @param config
     *            CSV 파일을 읽기 위한 설정 (<b>{@code NOT nullable}</b>)
     * @param type
     *            데이터 타입. (<b>{@code NOT nullable}</b>)
     * @param afterCreation
     *            객체 생성 후 작업.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws NullPointerException
     *             파라미터({@code reader, type 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 17.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(Reader reader, CsvFileConfig config, Class<E> type, @Nullable Consumer<E> afterCreation, boolean close) throws IOException {
        return readAsList(newCSVReader(reader, config), type, afterCreation, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.        parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param <E>
     *            CSV 파일을 읽어서 생성할 데이터 모델. {@link ReadAt}이 설정된 메소드를 호출하여 데이터를 설정합니다.
     * @param reader
     *            CSV 데이터 (<b>{@code NOT nullable}</b>)
     * @param config
     *            CSV 파일을 읽기 위한 설정 (<b>{@code NOT nullable}</b>)
     * @param type
     *            데이터 타입. (<b>{@code NOT nullable}</b>)
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws NullPointerException
     *             파라미터({@code reader, type 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(Reader reader, CsvFileConfig config, Class<E> type, @Nullable Predicate<E> validator, boolean close) throws IOException {
        return readAsList(newCSVReader(reader, config), type, validator, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.        parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param <E>
     *            CSV 파일을 읽어서 생성할 데이터 모델. {@link ReadAt}이 설정된 메소드를 호출하여 데이터를 설정합니다.
     * @param reader
     *            CSV 데이터 (<b>{@code NOT nullable}</b>)
     * @param config
     *            CSV 파일을 읽기 위한 설정 (<b>{@code NOT nullable}</b>)
     * @param type
     *            데이터 타입. (<b>{@code NOT nullable}</b>)
     * @param validator
     *            데이터 검증 함수.
     * @param afterCreation
     *            객체 생성 후 작업.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws NullPointerException
     *             파라미터({@code reader, type 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(Reader reader, CsvFileConfig config, Class<E> type, @Nullable Predicate<E> validator, @Nullable Consumer<E> afterCreation,
            boolean close) throws IOException {
        return readAsList(newCSVReader(reader, config), type, validator, afterCreation, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 11.        parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param <E>
     *            CSV 파일을 읽어서 생성할 데이터 모델. {@link ReadAt}이 설정된 메소드를 호출하여 데이터를 설정합니다.
     * @param reader
     *            CSV 데이터 (<b>{@code NOT nullable}</b>)
     * @param config
     *            CSV 파일을 읽기 위한 설정 (<b>{@code NOT nullable}</b>)
     * @param creator
     *            {@link String}[]를 전달받아 데이터를 생성하는 함수.<br>
     *            객체의 {@code setter} 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b>{@code NOT nullable}</b>)</font>
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code reader, creator 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 11.
     * @version 1.8.0
     * 
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
     * 2022. 3. 30.        parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param <E>
     *            CSV 파일을 읽어서 생성할 데이터 모델. {@link ReadAt}이 설정된 메소드를 호출하여 데이터를 설정합니다.
     * @param reader
     *            CSV 데이터 (<b>{@code NOT nullable}</b>)
     * @param config
     *            CSV 파일을 읽기 위한 설정 (<b>{@code NOT nullable}</b>)
     * @param creator
     *            {@link String}[]를 전달받아 데이터를 생성하는 함수.<br>
     *            객체의 {@code setter} 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, Predicate, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b>{@code NOT nullable}</b>)</font>
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code reader, creator 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * 
     * 
     * @see ReadAt
     */
    public static <E> Result<List<E>> readAsList(Reader reader, CsvFileConfig config, Function<String[], E> creator, @Nullable Predicate<E> validator, boolean close)
            throws IOException {
        return readAsList(newCSVReader(reader, config), creator, validator, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 12.		parkjunohng77@gmail.com			최초 작성
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
     *            데이터 타입. (<b>{@code NOT nullable}</b>)
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code reader, type 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(Reader reader, int skip, char separator, char quotechar, char escape, boolean strictQuotes, boolean ignoreLeadingWhiteSpace,
            Class<E> type, boolean close) throws IOException {
        return readAsList(reader, new CsvFileConfig(separator, quotechar, escape, strictQuotes, ignoreLeadingWhiteSpace, skip, defaultCharset()), type, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 17.		parkjunohng77@gmail.com			최초 작성
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
     *            데이터 타입. (<b>{@code NOT nullable}</b>)
     * @param afterCreation
     *            객체 생성 후 작업.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code reader, type 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 17.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(Reader reader, int skip, char separator, char quotechar, char escape, boolean strictQuotes, boolean ignoreLeadingWhiteSpace,
            Class<E> type, @Nullable Consumer<E> afterCreation, boolean close) throws IOException {
        return readAsList(reader, new CsvFileConfig(separator, quotechar, escape, strictQuotes, ignoreLeadingWhiteSpace, skip, defaultCharset()), type, afterCreation, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     parkjunohng77@gmail.com         최초 작성
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
     *            데이터 타입. (<b>{@code NOT nullable}</b>)
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code reader, type 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(Reader reader, int skip, char separator, char quotechar, char escape, boolean strictQuotes, boolean ignoreLeadingWhiteSpace,
            Class<E> type, @Nullable Predicate<E> validator, boolean close) throws IOException {
        return readAsList(reader, new CsvFileConfig(separator, quotechar, escape, strictQuotes, ignoreLeadingWhiteSpace, skip, defaultCharset()), type, validator, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     parkjunohng77@gmail.com         최초 작성
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
     *            데이터 타입. (<b>{@code NOT nullable}</b>)
     * @param validator
     *            데이터 검증 함수.
     * @param afterCreation
     *            객체 생성 후 작업.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code reader, type 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(Reader reader, int skip, char separator, char quotechar, char escape, boolean strictQuotes, boolean ignoreLeadingWhiteSpace,
            Class<E> type, @Nullable Predicate<E> validator, @Nullable Consumer<E> afterCreation, boolean close) throws IOException {
        return readAsList(reader, new CsvFileConfig(separator, quotechar, escape, strictQuotes, ignoreLeadingWhiteSpace, skip, defaultCharset()), type, validator, afterCreation,
                close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 12.		parkjunohng77@gmail.com			최초 작성
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
     *            객체의 {@code setter} 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b>{@code NOT nullable}</b>)</font>
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code reader, creator 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(Reader reader, int skip, char separator, char quotechar, char escape, boolean strictQuotes, boolean ignoreLeadingWhiteSpace,
            Function<String[], E> creator, boolean close) throws IOException {
        return readAsList(reader, new CsvFileConfig(separator, quotechar, escape, strictQuotes, ignoreLeadingWhiteSpace, skip, defaultCharset()), creator, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     parkjunohng77@gmail.com         최초 작성
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
     *            객체의 {@code setter} 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, Predicate, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b>{@code NOT nullable}</b>)</font>
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code reader, creator 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(Reader reader, int skip, char separator, char quotechar, char escape, boolean strictQuotes, boolean ignoreLeadingWhiteSpace,
            Function<String[], E> creator, @Nullable Predicate<E> validator, boolean close) throws IOException {
        return readAsList(reader, new CsvFileConfig(separator, quotechar, escape, strictQuotes, ignoreLeadingWhiteSpace, skip, defaultCharset()), creator, validator, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 12.		parkjunohng77@gmail.com			최초 작성
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
     *            데이터 타입. (<b>{@code NOT nullable}</b>)
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code reader, type 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(Reader reader, int skip, char separator, char quotechar, char escape, boolean strictQuotes, Class<E> type, boolean close)
            throws IOException {
        return readAsList(reader, skip, separator, quotechar, escape, strictQuotes, CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, type, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 12.		parkjunohng77@gmail.com			최초 작성
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
     *            데이터 타입. (<b>{@code NOT nullable}</b>)
     * @param afterCreation
     *            객체 생성 후 작업.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code reader, type 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(Reader reader, int skip, char separator, char quotechar, char escape, boolean strictQuotes, Class<E> type,
            Consumer<E> afterCreation, boolean close) throws IOException {
        return readAsList(reader, skip, separator, quotechar, escape, strictQuotes, CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, type, afterCreation, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     parkjunohng77@gmail.com         최초 작성
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
     *            데이터 타입. (<b>{@code NOT nullable}</b>)
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code reader, type 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(Reader reader, int skip, char separator, char quotechar, char escape, boolean strictQuotes, Class<E> type,
            @Nullable Predicate<E> validator, boolean close) throws IOException {
        return readAsList(reader, skip, separator, quotechar, escape, strictQuotes, CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, type, validator, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     parkjunohng77@gmail.com         최초 작성
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
     *            데이터 타입. (<b>{@code NOT nullable}</b>)
     * @param validator
     *            데이터 검증 함수.
     * @param afterCreation
     *            객체 생성 후 작업.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code reader, type 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(Reader reader, int skip, char separator, char quotechar, char escape, boolean strictQuotes, Class<E> type,
            @Nullable Predicate<E> validator, Consumer<E> afterCreation, boolean close) throws IOException {
        return readAsList(reader, skip, separator, quotechar, escape, strictQuotes, CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, type, validator, afterCreation, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 12.		parkjunohng77@gmail.com			최초 작성
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
     *            객체의 {@code setter} 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b>{@code NOT nullable}</b>)</font>
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code reader, creator 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(Reader reader, int skip, char separator, char quotechar, char escape, boolean strictQuotes, Function<String[], E> creator,
            boolean close) throws IOException {
        return readAsList(reader, skip, separator, quotechar, escape, strictQuotes, CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, creator, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     parkjunohng77@gmail.com         최초 작성
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
     *            객체의 {@code setter} 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, Predicate, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b>{@code NOT nullable}</b>)</font>
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code reader, creator 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(Reader reader, int skip, char separator, char quotechar, char escape, boolean strictQuotes, Function<String[], E> creator,
            Predicate<E> validator, boolean close) throws IOException {
        return readAsList(reader, skip, separator, quotechar, escape, strictQuotes, CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, creator, validator, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 12.		parkjunohng77@gmail.com			최초 작성
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
     *            데이터 타입. (<b>{@code NOT nullable}</b>)
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code reader, type 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * 
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
     * 2021. 11. 12.		parkjunohng77@gmail.com			최초 작성
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
     *            데이터 타입. (<b>{@code NOT nullable}</b>)
     * @param afterCreation
     *            객체 생성 후 작업.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code reader, type 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(Reader reader, int skip, char separator, char quotechar, char escape, Class<E> type, @Nullable Consumer<E> afterCreation,
            boolean close) throws IOException {
        return readAsList(reader, skip, separator, quotechar, escape, CsvConfig.DEFAULT_STRICT_QUOTES, CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, type, afterCreation, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     parkjunohng77@gmail.com         최초 작성
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
     *            데이터 타입. (<b>{@code NOT nullable}</b>)
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code reader, type 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(Reader reader, int skip, char separator, char quotechar, char escape, Class<E> type, @Nullable Predicate<E> validator,
            boolean close) throws IOException {
        return readAsList(reader, skip, separator, quotechar, escape, CsvConfig.DEFAULT_STRICT_QUOTES, CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, type, validator, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 30.     parkjunohng77@gmail.com         최초 작성
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
     *            데이터 타입. (<b>{@code NOT nullable}</b>)
     * @param validator
     *            데이터 검증 함수.
     * @param afterCreation
     *            객체 생성 후 작업.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code reader, type 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(Reader reader, int skip, char separator, char quotechar, char escape, Class<E> type, @Nullable Predicate<E> validator,
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
     * 2021. 11. 12.		parkjunohng77@gmail.com			최초 작성
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
     *            객체의 {@code setter} 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b>{@code NOT nullable}</b>)</font>
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code reader, creator 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * 
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
     * 2022. 3. 30.     parkjunohng77@gmail.com         최초 작성
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
     *            객체의 {@code setter} 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, Predicate, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b>{@code NOT nullable}</b>)</font>
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code reader, creator 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(Reader reader, int skip, char separator, char quotechar, char escape, Function<String[], E> creator,
            @Nullable Predicate<E> validator, boolean close) throws IOException {
        return readAsList(reader, skip, separator, quotechar, escape, CsvConfig.DEFAULT_STRICT_QUOTES, CsvConfig.DEFAULT_IGNORE_LEADING_WHITESPACE, creator, validator, close);
    }

    /**
     * CSV 파일을 읽어서 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 12.		parkjunohng77@gmail.com			최초 작성
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
     *            데이터 타입. (<b>{@code NOT nullable}</b>)
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code reader, type 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * 
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
     * 2021. 11. 12.		parkjunohng77@gmail.com			최초 작성
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
     *            데이터 타입. (<b>{@code NOT nullable}</b>)
     * @param afterCreation
     *            객체 생성 후 작업.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code reader, type 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(Reader reader, int skip, char separator, char quotechar, Class<E> type, @Nullable Consumer<E> afterCreation, boolean close)
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
     * 2022. 3. 30.     parkjunohng77@gmail.com         최초 작성
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
     *            데이터 타입. (<b>{@code NOT nullable}</b>)
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code reader, type 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(Reader reader, int skip, char separator, char quotechar, Class<E> type, @Nullable Predicate<E> validator, boolean close)
            throws IOException {
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
     * 2022. 3. 30.     parkjunohng77@gmail.com         최초 작성
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
     *            데이터 타입. (<b>{@code NOT nullable}</b>)
     * @param validator
     *            데이터 검증 함수.
     * @param afterCreation
     *            객체 생성 후 작업.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code reader, type 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(Reader reader, int skip, char separator, char quotechar, Class<E> type, @Nullable Predicate<E> validator,
            @Nullable Consumer<E> afterCreation, boolean close) throws IOException {
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
     * 2021. 11. 12.		parkjunohng77@gmail.com			최초 작성
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
     *            객체의 {@code setter} 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b>{@code NOT nullable}</b>)</font>
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code reader, creator 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * 
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
     * 2022. 3. 30.     parkjunohng77@gmail.com         최초 작성
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
     *            객체의 {@code setter} 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, Predicate, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b>{@code NOT nullable}</b>)</font>
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code reader, creator 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(Reader reader, int skip, char separator, char quotechar, Function<String[], E> creator, @Nullable Predicate<E> validator,
            boolean close) throws IOException {
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
     * 2021. 11. 12.		parkjunohng77@gmail.com			최초 작성
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
     *            데이터 타입. (<b>{@code NOT nullable}</b>)
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code reader, type 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * 
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
     * 2021. 11. 12.		parkjunohng77@gmail.com			최초 작성
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
     *            데이터 타입. (<b>{@code NOT nullable}</b>)
     * @param afterCreation
     *            객체 생성 후 작업.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code reader, type 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(Reader reader, int skip, char separator, Class<E> type, @Nullable Consumer<E> afterCreation, boolean close) throws IOException {
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
     * 2022. 3. 30.     parkjunohng77@gmail.com         최초 작성
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
     *            데이터 타입. (<b>{@code NOT nullable}</b>)
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code reader, type 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(Reader reader, int skip, char separator, Class<E> type, @Nullable Predicate<E> validator, boolean close) throws IOException {
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
     * 2022. 3. 30.     parkjunohng77@gmail.com         최초 작성
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
     *            데이터 타입. (<b>{@code NOT nullable}</b>)
     * @param validator
     *            데이터 검증 함수.
     * @param afterCreation
     *            객체 생성 후 작업.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code reader, type 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(Reader reader, int skip, char separator, Class<E> type, @Nullable Predicate<E> validator, @Nullable Consumer<E> afterCreation,
            boolean close) throws IOException {
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
     * 2021. 11. 12.		parkjunohng77@gmail.com			최초 작성
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
     *            객체의 {@code setter} 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b>{@code NOT nullable}</b>)</font>
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code reader, creator 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2021. 11. 12.
     * @version 1.8.0
     * 
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
     * 2022. 3. 30.     parkjunohng77@gmail.com         최초 작성
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
     *            객체의 {@code setter} 메소드에 {@link ReadAt}을 설정한다면, {@link #defaultCreator(Class)} 를 사용하거나
     *            {@link #readAsList(InputStream, CsvFileConfig, Class, Predicate, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b>{@code NOT nullable}</b>)</font>
     * @param validator
     *            데이터 검증 함수.
     * @param close
     *            {@link InputStream} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code reader, creator 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 30.
     * @version 1.8.0
     * 
     */
    public static <E> Result<List<E>> readAsList(Reader reader, int skip, char separator, Function<String[], E> creator, @Nullable Predicate<E> validator, boolean close)
            throws IOException {
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
     * 2022. 3. 17.     parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param <E>
     *            CSV 파일을 읽어서 생성할 데이터 모델. {@link WriteAt}이 설정된 메소드를 호출하여 데이터를 설정합니다.
     * @param data
     *            데이터
     * @param writer
     *            CSV 데이터 (<b>{@code NOT nullable}</b>)
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
     * 
     */
    public static <E> Result<Long> write(Collection<E> data, CSVWriter writer, @Nullable Consumer<E> beforeCreation, boolean close) throws IOException {
        return write(data, writer, (String[]) null, beforeCreation, close);
    }

    /**
     * 객체를 CSV 파일로 저장합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 17.        parkjunohng77@gmail.com         최초 작성
     * </pre>
     * 
     * @param <E>
     *            CSV 파일을 읽어서 생성할 데이터 모델. {@link WriteAt}이 설정된 메소드를 호출하여 데이터를 설정합니다.
     * @param data
     *            데이터
     * @param writer
     *            CSV 데이터 (<b>{@code NOT nullable}</b>)
     * @param creator
     *            객체를 전달받아 {@link String}[] 데이터를 생성하는 함수.<br>
     *            객체의 {@code getter} 메소드에 {@link WriteAt}을 설정한다면, {@link #defaultCreator()} 를 사용하거나
     *            {@link #write(Collection, CSVWriter, String[], Consumer, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b>{@code NOT nullable}</b>)</font>
     * @param beforeCreation
     *            String[]로 변환하기 전 작업.
     * @param close
     *            {@link CSVWriter} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code data, writer, creator 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * 
     */
    public static <E> Result<Long> write(Collection<E> data, CSVWriter writer, Function<E, String[]> creator, @Nullable Consumer<E> beforeCreation, boolean close)
            throws IOException {
        return write(data, writer, null, creator, beforeCreation, close);
    }

    /**
     * 객체를 CSV 파일로 저장합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 17.     parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param <E>
     *            CSV 파일을 읽어서 생성할 데이터 모델. {@link WriteAt}이 설정된 메소드를 호출하여 데이터를 설정합니다.
     * @param data
     *            데이터
     * @param writer
     *            CSV 데이터 (<b>{@code NOT nullable}</b>)
     * @param header
     *            헤더
     * @param beforeCreation
     *            String[]로 변환하기 전 작업.
     * @param close
     *            {@link CSVWriter} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code data 또는 writer})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * 
     */
    public static <E> Result<Long> write(Collection<E> data, CSVWriter writer, String @Nullable [] header, @Nullable Consumer<E> beforeCreation, boolean close) throws IOException {
        return write(data, writer, header, defaultCreator(), beforeCreation, close);
    }

    /**
     * 여러 개의 객체를 CSV 파일로 저장합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 17.     parkjunohng77@gmail.com         최초 작성
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
     * @throws NullPointerException
     *             파라미터({@code data, file, config 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * 
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
     * 2022. 3. 17.     parkjunohng77@gmail.com         최초 작성
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
     * @throws NullPointerException
     *             파라미터({@code data, file, config 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * 
     */
    public static <E> Result<Long> write(Collection<E> data, File file, CsvWriteConfig config, @Nullable Consumer<E> beforeCreation, boolean close) throws IOException {
        return write(data, file, config, defaultCreator(), beforeCreation, close);
    }

    /**
     * 여러 개의 객체를 CSV 파일로 저장합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 17.     parkjunohng77@gmail.com         최초 작성
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
     * @throws NullPointerException
     *             파라미터({@code data, file, config, creator 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * 
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
     * 2022. 3. 17.     parkjunohng77@gmail.com         최초 작성
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
     * @throws NullPointerException
     *             파라미터({@code data, file, config, creator 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * 
     */
    public static <E> Result<Long> write(Collection<E> data, File file, CsvWriteConfig config, Function<E, String[]> creator, @Nullable Consumer<E> beforeCreation, boolean close)
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
     * 2022. 3. 17.     parkjunohng77@gmail.com         최초 작성
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
     * @throws NullPointerException
     *             파라미터({@code data, file, config 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * 
     */
    public static <E> Result<Long> write(Collection<E> data, File file, CsvWriteConfig config, String @Nullable [] header, boolean close) throws IOException {
        return write(data, file, config, header, defaultCreator(), close);
    }

    /**
     * 여러 개의 객체를 CSV 파일로 저장합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 17.     parkjunohng77@gmail.com         최초 작성
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
     * @throws NullPointerException
     *             파라미터({@code data, file, config 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * 
     */
    public static <E> Result<Long> write(Collection<E> data, File file, CsvWriteConfig config, String @Nullable [] header, @Nullable Consumer<E> beforeCreation, boolean close)
            throws IOException {
        return write(data, file, config, header, defaultCreator(), beforeCreation, close);
    }

    /**
     * 여러 개의 객체를 CSV 파일로 저장합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 17.     parkjunohng77@gmail.com         최초 작성
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
     * @throws NullPointerException
     *             파라미터({@code data, file, config, creator 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * 
     */
    public static <E> Result<Long> write(Collection<E> data, File file, CsvWriteConfig config, String @Nullable [] header, Function<E, String[]> creator, boolean close)
            throws IOException {
        return write(data, file, config, header, creator, null, close);
    }

    /**
     * 여러 개의 객체를 CSV 파일로 저장합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 17.     parkjunohng77@gmail.com         최초 작성
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
     * @throws NullPointerException
     *             파라미터({@code data, file, config, creator 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * 
     */
    public static <E> Result<Long> write(Collection<E> data, File file, CsvWriteConfig config, String @Nullable [] header, Function<E, String[]> creator,
            @Nullable Consumer<E> beforeCreation, boolean close) throws IOException {
        Objects.requireNonNull(file);

        return write(data, new FileOutputStream(file), config, header, creator, beforeCreation, close);
    }

    /**
     * 객체를 CSV 파일로 저장합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 17.     parkjunohng77@gmail.com     최초 작성
     * 2026. 2. 26.     parkjunhong77@gmail.com     (3.0.0) CSVWriter 데이터 유형 변경 ({@link CSVWriter} -> {@link ICSVWriter})
     * </pre>
     * 
     * @param <E>
     *            CSV 파일을 읽어서 생성할 데이터 모델. {@link WriteAt}이 설정된 메소드를 호출하여 데이터를 설정합니다.
     * @param data
     *            데이터
     * @param writer
     *            CSV 데이터 (<b>{@code NOT nullable}</b>)
     * @param header
     *            헤더
     * @param creator
     *            객체를 전달받아 {@link String}[] 데이터를 생성하는 함수.<br>
     *            객체의 {@code getter} 메소드에 {@link WriteAt}을 설정한다면, {@link #defaultCreator()} 를 사용하거나
     *            {@link #write(Collection, CSVWriter, String[], Consumer, boolean)}를 호출하여도 됨.<br>
     *            <font color="red">(<b>{@code NOT nullable}</b>)</font>
     * @param beforeCreation
     *            String[]로 변환하기 전 작업.
     * @param close
     *            {@link ICSVWriter} close 여부.( see {@link AutoCloseable#close()})
     * @return
     * @throws IOException
     * @throws NullPointerException
     *             파라미터({@code data, writer, creator 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * 
     */
    public static <E> Result<Long> write(Collection<E> data, ICSVWriter writer, String @Nullable [] header, Function<E, String[]> creator, @Nullable Consumer<E> beforeCreation,
            boolean close) throws IOException {
        ObjectUtils.requireNonNulls(data, writer, creator);

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
        }
    }

    /**
     * 여러 개의 객체를 CSV 파일로 저장합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 17.     parkjunohng77@gmail.com         최초 작성
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
     * @throws NullPointerException
     *             파라미터({@code data, outputStream, config 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * 
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
     * 2022. 3. 17.     parkjunohng77@gmail.com         최초 작성
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
     * @throws NullPointerException
     *             파라미터({@code data, outputStream, config 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * 
     */
    public static <E> Result<Long> write(Collection<E> data, OutputStream outputStream, CsvWriteConfig config, @Nullable Consumer<E> beforeCreation, boolean close)
            throws IOException {
        return write(data, outputStream, config, defaultCreator(), beforeCreation, close);
    }

    /**
     * 여러 개의 객체를 CSV 파일로 저장합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 17.     parkjunohng77@gmail.com         최초 작성
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
     * @throws NullPointerException
     *             파라미터({@code data, outputStream, config, creator 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * 
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
     * 2022. 3. 17.     parkjunohng77@gmail.com         최초 작성
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
     * @throws NullPointerException
     *             파라미터({@code data, outputStream, config, creator 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * 
     */
    public static <E> Result<Long> write(Collection<E> data, OutputStream outputStream, CsvWriteConfig config, Function<E, String[]> creator, @Nullable Consumer<E> beforeCreation,
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
     * 2022. 3. 17.     parkjunohng77@gmail.com         최초 작성
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
     * @throws NullPointerException
     *             파라미터({@code data, outputStream, config 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * 
     */
    public static <E> Result<Long> write(Collection<E> data, OutputStream outputStream, CsvWriteConfig config, String @Nullable [] header, boolean close) throws IOException {
        return write(data, outputStream, config, header, defaultCreator(), close);
    }

    /**
     * 여러 개의 객체를 CSV 파일로 저장합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 17.     parkjunohng77@gmail.com         최초 작성
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
     * @throws NullPointerException
     *             파라미터({@code data, outputStream, config 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * 
     */
    public static <E> Result<Long> write(Collection<E> data, OutputStream outputStream, CsvWriteConfig config, String @Nullable [] header, @Nullable Consumer<E> beforeCreation,
            boolean close) throws IOException {
        return write(data, outputStream, config, header, defaultCreator(), beforeCreation, close);
    }

    /**
     * 여러 개의 객체를 CSV 파일로 저장합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 17.     parkjunohng77@gmail.com         최초 작성
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
     * @throws NullPointerException
     *             파라미터({@code data, outputStream, config, creator 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * 
     */
    public static <E> Result<Long> write(Collection<E> data, OutputStream outputStream, CsvWriteConfig config, String @Nullable [] header, Function<E, String[]> creator,
            boolean close) throws IOException {
        return write(data, outputStream, config, header, creator, null, close);
    }

    /**
     * 여러 개의 객체를 CSV 파일로 저장합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 17.     parkjunohng77@gmail.com         최초 작성
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
     * @throws NullPointerException
     *             파라미터({@code data, outputStream, config, creator 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * 
     */
    public static <E> Result<Long> write(Collection<E> data, OutputStream outputStream, CsvWriteConfig config, String @Nullable [] header, Function<E, String[]> creator,
            @Nullable Consumer<E> beforeCreation, boolean close) throws IOException {
        Objects.requireNonNull(outputStream);

        return write(data, new OutputStreamWriter(outputStream, config.getCharset()), config, header, creator, beforeCreation, close);
    }

    /**
     * 여러 개의 객체를 CSV 파일로 저장합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 03. 22.     parkjunohng77@gmail.com         최초 작성
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
     * @throws NullPointerException
     *             파라미터({@code data, path, config 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 03. 22.
     * @version 1.8.0
     * 
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
     * 2022. 03. 22.     parkjunohng77@gmail.com         최초 작성
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
     * @throws NullPointerException
     *             파라미터({@code data, path, config 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 03. 22.
     * @version 1.8.0
     * 
     */
    public static <E> Result<Long> write(Collection<E> data, Path path, CsvWriteConfig config, @Nullable Consumer<E> beforeCreation, boolean close, OpenOption... options)
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
     * 2022. 03. 22.     parkjunohng77@gmail.com         최초 작성
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
     * @throws NullPointerException
     *             파라미터({@code data, path, config, creator 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 03. 22.
     * @version 1.8.0
     * 
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
     * 2022. 03. 22.     parkjunohng77@gmail.com         최초 작성
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
     * @throws NullPointerException
     *             파라미터({@code data, path, config, creator 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 03. 22.
     * @version 1.8.0
     * 
     */
    public static <E> Result<Long> write(Collection<E> data, Path path, CsvWriteConfig config, Function<E, String[]> creator, @Nullable Consumer<E> beforeCreation, boolean close,
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
     * 2022. 03. 22.     parkjunohng77@gmail.com         최초 작성
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
     * @throws NullPointerException
     *             파라미터({@code data, path, config 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 03. 22.
     * @version 1.8.0
     * 
     */
    public static <E> Result<Long> write(Collection<E> data, Path path, CsvWriteConfig config, String @Nullable [] header, boolean close, OpenOption... options)
            throws IOException {
        return write(data, path, config, header, defaultCreator(), close, options);
    }

    /**
     * 여러 개의 객체를 CSV 파일로 저장합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 03. 22.     parkjunohng77@gmail.com         최초 작성
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
     * @throws NullPointerException
     *             파라미터({@code data, path, config 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 03. 22.
     * @version 1.8.0
     * 
     */
    public static <E> Result<Long> write(Collection<E> data, Path path, CsvWriteConfig config, String @Nullable [] header, @Nullable Consumer<E> beforeCreation, boolean close,
            OpenOption... options) throws IOException {
        return write(data, path, config, header, defaultCreator(), beforeCreation, close, options);
    }

    /**
     * 여러 개의 객체를 CSV 파일로 저장합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 03. 22.     parkjunohng77@gmail.com         최초 작성
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
     * @throws NullPointerException
     *             파라미터({@code data, path, config, creator 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 03. 22.
     * @version 1.8.0
     * 
     */
    public static <E> Result<Long> write(Collection<E> data, Path path, CsvWriteConfig config, String @Nullable [] header, Function<E, String[]> creator, boolean close,
            OpenOption... options) throws IOException {
        return write(data, path, config, header, creator, null, close, options);
    }

    /**
     * 여러 개의 객체를 CSV 파일로 저장합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 03. 22.     parkjunohng77@gmail.com         최초 작성
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
     * @throws NullPointerException
     *             파라미터({@code data, path, config 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 03. 22.
     * @version 1.8.0
     * 
     */
    // 아래 내용에 적용됨.
    // - Files.newOutputStream(path, options)
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static <E> Result<Long> write(Collection<E> data, Path path, CsvWriteConfig config, String @Nullable [] header, Function<E, String[]> creator,
            @Nullable Consumer<E> beforeCreation, boolean close, OpenOption... options) throws IOException {
        Objects.requireNonNull(path);

        return write(data, Files.newOutputStream(path, options), config, header, creator, beforeCreation, close);
    }

    /**
     * 여러 개의 객체를 CSV 파일로 저장합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 17.     parkjunohng77@gmail.com         최초 작성
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
     * @throws NullPointerException
     *             파라미터({@code data, filepath, config 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * 
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
     * 2022. 3. 17.     parkjunohng77@gmail.com         최초 작성
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
     * @throws NullPointerException
     *             파라미터({@code data, filepath, config 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * 
     */
    public static <E> Result<Long> write(Collection<E> data, String filepath, CsvWriteConfig config, @Nullable Consumer<E> beforeCreation, boolean close) throws IOException {
        return write(data, filepath, config, defaultCreator(), beforeCreation, close);
    }

    /**
     * 여러 개의 객체를 CSV 파일로 저장합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 17.     parkjunohng77@gmail.com         최초 작성
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
     * @throws NullPointerException
     *             파라미터({@code data, filepath, config, creator 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * 
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
     * 2022. 3. 17.     parkjunohng77@gmail.com         최초 작성
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
     * @throws NullPointerException
     *             파라미터({@code data, filepath, config, creator 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * 
     */
    public static <E> Result<Long> write(Collection<E> data, String filepath, CsvWriteConfig config, Function<E, String[]> creator, @Nullable Consumer<E> beforeCreation,
            boolean close) throws IOException {
        return write(data, filepath, config, null, creator, beforeCreation, close);
    }

    /**
     * 여러 개의 객체를 CSV 파일로 저장합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 17.     parkjunohng77@gmail.com         최초 작성
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
     * @throws NullPointerException
     *             파라미터({@code data, filepath, config 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * 
     */
    public static <E> Result<Long> write(Collection<E> data, String filepath, CsvWriteConfig config, String @Nullable [] header, boolean close) throws IOException {
        return write(data, filepath, config, header, defaultCreator(), close);
    }

    /**
     * 여러 개의 객체를 CSV 파일로 저장합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 17.     parkjunohng77@gmail.com         최초 작성
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
     * @throws NullPointerException
     *             파라미터({@code data, filepath, config 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * 
     */
    public static <E> Result<Long> write(Collection<E> data, String filepath, CsvWriteConfig config, String @Nullable [] header, @Nullable Consumer<E> beforeCreation,
            boolean close) throws IOException {
        return write(data, filepath, config, header, defaultCreator(), beforeCreation, close);
    }

    /**
     * 여러 개의 객체를 CSV 파일로 저장합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 17.     parkjunohng77@gmail.com         최초 작성
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
     * @throws NullPointerException
     *             파라미터({@code data, config, creator 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * 
     */
    public static <E> Result<Long> write(Collection<E> data, String filepath, CsvWriteConfig config, String @Nullable [] header, Function<E, String[]> creator, boolean close)
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
     * 2022. 3. 17.     parkjunohng77@gmail.com         최초 작성
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
     * @throws NullPointerException
     *             파라미터({@code data, filepath, config, creator 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * 
     */
    public static <E> Result<Long> write(Collection<E> data, String filepath, CsvWriteConfig config, String @Nullable [] header, Function<E, String[]> creator,
            @Nullable Consumer<E> beforeCreation, boolean close) throws IOException {
        return write(data, new File(filepath), config, header, creator, beforeCreation, close);
    }

    /**
     * 여러 개의 객체를 CSV 파일로 저장합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 17.     parkjunohng77@gmail.com         최초 작성
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
     * @throws NullPointerException
     *             파라미터({@code data, writer, config 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * 
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
     * 2022. 3. 17.     parkjunohng77@gmail.com         최초 작성
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
     * @throws NullPointerException
     *             파라미터({@code data, writer, config 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * 
     */
    public static <E> Result<Long> write(Collection<E> data, Writer writer, CsvWriteConfig config, @Nullable Consumer<E> beforeCreation, boolean close) throws IOException {
        return write(data, writer, config, defaultCreator(), beforeCreation, close);
    }

    /**
     * 여러 개의 객체를 CSV 파일로 저장합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 17.     parkjunohng77@gmail.com         최초 작성
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
     * @throws NullPointerException
     *             파라미터({@code data, writer, config, creator 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * 
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
     * 2022. 3. 17.     parkjunohng77@gmail.com         최초 작성
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
     * @throws NullPointerException
     *             파라미터({@code data, writer, config, creator 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * 
     */
    public static <E> Result<Long> write(Collection<E> data, Writer writer, CsvWriteConfig config, Function<E, String[]> creator, @Nullable Consumer<E> beforeCreation,
            boolean close) throws IOException {
        return write(data, writer, config, null, creator, beforeCreation, close);
    }

    /**
     * 여러 개의 객체를 CSV 파일로 저장합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 17.     parkjunohng77@gmail.com         최초 작성
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
     * @throws NullPointerException
     *             파라미터({@code data, writer, config 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * 
     */
    public static <E> Result<Long> write(Collection<E> data, Writer writer, CsvWriteConfig config, String @Nullable [] header, boolean close) throws IOException {
        return write(data, writer, config, header, defaultCreator(), close);
    }

    /**
     * 여러 개의 객체를 CSV 파일로 저장합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 17.     parkjunohng77@gmail.com         최초 작성
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
     * @throws NullPointerException
     *             파라미터({@code data, writer, config 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * 
     */
    public static <E> Result<Long> write(Collection<E> data, Writer writer, CsvWriteConfig config, String @Nullable [] header, @Nullable Consumer<E> beforeCreation, boolean close)
            throws IOException {
        return write(data, writer, config, header, defaultCreator(), beforeCreation, close);
    }

    /**
     * 여러 개의 객체를 CSV 파일로 저장합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 3. 17.     parkjunohng77@gmail.com         최초 작성
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
     * @throws NullPointerException
     *             파라미터({@code data, writer, config, creator 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * 
     */
    public static <E> Result<Long> write(Collection<E> data, Writer writer, CsvWriteConfig config, String @Nullable [] header, Function<E, String[]> creator, boolean close)
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
     * 2022. 3. 17.     parkjunohng77@gmail.com         최초 작성
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
     * @throws NullPointerException
     *             파라미터({@code data, writer, config, creator 중에 1개라도})가 {@code null}인 경우 발생.
     *
     * @since 2022. 3. 17.
     * @version 1.8.0
     * 
     */
    public static <E> Result<Long> write(Collection<E> data, Writer writer, CsvWriteConfig config, String @Nullable [] header, Function<E, String[]> creator,
            @Nullable Consumer<E> beforeCreation, boolean close) throws IOException {
        return write(data, newCSVWriter(writer, config), header, creator, beforeCreation, close);
    }

    private static class MethodInfo<A> {
        final Method method;
        final A annotation;
        final int index;

        MethodInfo(Method method, A annotation, int index) {
            this.method = method;
            this.annotation = annotation;
            this.index = index;
            // 캐싱 시점에 접근 권한을 획득하여 런타임 동시성 장애 방지
            method.trySetAccessible();
        }
    }
}
