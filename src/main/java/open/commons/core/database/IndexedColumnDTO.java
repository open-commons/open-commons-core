/*
 *
 * This file is generated under this project, "open-commons-core".
 *
 * Date  : 2020. 11. 4. 오후 6:15:42
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.core.database;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import open.commons.core.csv.AbstractCsvData;
import open.commons.core.csv.CsvConfig;
import open.commons.core.csv.MethodBase;
import open.commons.core.database.annotation.AQueryIndex;
import open.commons.core.utils.AnnotationUtils;
import open.commons.core.utils.StringUtils;

/**
 * @since 2020. 11. 4.
 * @version
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public class IndexedColumnDTO extends AbstractCsvData {

    /** 클래스별 리플렉션 메타데이터 캐시 */
    private static final Map<Class<?>, CachedMetadata> METADATA_CACHE = new ConcurrentHashMap<>();

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 6. 18.     parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @since 2020. 11. 4.
     * @version 1.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @see CsvConfig#DEFAULT_SEPARATOR
     * @see CsvConfig.DEFAULT_QUOTE_CHARACTER
     * @see CsvConfig.DEFAULT_ESCAPE_CHARACTER
     */
    public IndexedColumnDTO() {
        this(CsvConfig.DEFAULT_SEPARATOR, CsvConfig.DEFAULT_QUOTE_CHARACTER, CsvConfig.DEFAULT_ESCAPE_CHARACTER);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 4. 7.      parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param separator
     *            구분자
     *
     * @since 2022. 4. 7.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * @see CsvConfig.DEFAULT_QUOTE_CHARACTER
     * @see CsvConfig.DEFAULT_ESCAPE_CHARACTER
     */
    public IndexedColumnDTO(char separator) {
        this(separator, CsvConfig.DEFAULT_QUOTE_CHARACTER, CsvConfig.DEFAULT_ESCAPE_CHARACTER);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 4. 7.      parkjunohng77@gmail.com         최초 작성
     * </pre>
     *
     * @param separator
     *            구분자
     * @param quote
     *            인용구 문자
     * @param escape
     *            이스케이프 문자
     *
     * @since 2022. 4. 7.
     * @version 1.8.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * @see CsvConfig.DEFAULT_ESCAPE_CHARACTER
     */
    public IndexedColumnDTO(char separator, char quote, char escape) {
        super(separator, quote, escape);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 6. 18.     parkjunohng77@gmail.com         최초 작성
     * 2026. 3. 4.      parkjunhong77@gmail.com         (3.0.0) JDK 25 마이그레이션: 클래스 레벨 캐싱 적용
     * </pre>
     *
     * @return 헤더 목록
     *
     * @since 2021. 6. 18.
     * @version 3.0.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see open.commons.core.csv.AbstractCsvData#getHeaders()
     */
    @Override
    public List<String> getHeaders() {
        // 기존 반환 타입의 가변성(Mutability)을 유지하기 위해 새 ArrayList로 감싸서 반환
        return new ArrayList<>(getMetadata().headers());
    }

    /**
     * 현재 클래스의 메타데이터(헤더 및 메서드)를 캐시에서 가져오거나 생성합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2026. 3. 4.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2026. 3. 4.
     * @version 3.0.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    private CachedMetadata getMetadata() {
        return METADATA_CACHE.computeIfAbsent(getClass(), clazz -> {
            // 1. 헤더 추출 로직 (클래스 계층 순회)
            Map<String, Field> headerFields = new HashMap<>();
            Class<?> cls = clazz;

            while (cls != null && !cls.equals(Object.class)) {
                List<Field> fields = AnnotationUtils.getAnnotatedFieldsAll(cls, AQueryIndex.class);
                for (Field f : fields) {
                    Field of = headerFields.get(f.getName());
                    // 하위 클래스의 필드가 상위 클래스의 필드를 섀도잉하는 경우 유지
                    if (of == null || of.getDeclaringClass().isAssignableFrom(f.getDeclaringClass())) {
                        headerFields.put(f.getName(), f);
                    }
                }
                cls = cls.getSuperclass();
            }

            // 인덱스 기준으로 정렬 후 스네이크 케이스 문자열로 변환하여 불변 리스트 생성
            List<String> headers = headerFields.values().stream().sorted(Comparator.comparingInt(f -> f.getAnnotation(AQueryIndex.class).index()))
                    .map(f -> StringUtils.toSnakeCase(f.getName())).toList();

            // 2. 값 추출 메서드 로직
            List<Method> methods = AnnotationUtils.getAnnotatedMethodsAll(clazz, AQueryIndex.class);
            List<Method> valueMethods = methods.stream() //
                    .sorted(Comparator.comparingInt(m -> m.getAnnotation(AQueryIndex.class).index())) //
                    // JDK 25: 호출 성능 극대화를 위해 접근 권한 미리 확보
                    .peek(Method::trySetAccessible) //
                    .toList();

            return new CachedMetadata(headers, valueMethods);
        });
    }

    /**
     * CSV 데이터 제공자를 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 6. 18.     parkjunohng77@gmail.com         최초 작성
     * 2023. 10. 26.    parkjunohng77@gmail.com         메소드 검색 버그 수정.
     * 2026. 3. 4.      parkjunhong77@gmail.com         (3.0.0) JDK 25 마이그레이션: 캐싱 적용 및 메서드 접근 최적화
     * </pre>
     *
     * @return 값 제공자(Supplier) 목록
     *
     * @since 2021. 6. 18.
     * @version 3.0.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public List<Supplier<String>> getValues() {
        // 캐시된 정렬 메서드들을 현재 인스턴스(this)에 바인딩하여 Supplier로 변환
        return getMetadata().valueMethods().stream().map(m -> (Supplier<String>) new MethodBase(this, m)).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * 헤더(필드명) 목록과 값을 추출할 메서드 목록을 보관하는 불변 레코드
     */
    private record CachedMetadata(List<String> headers, List<Method> valueMethods) {
    }
}
