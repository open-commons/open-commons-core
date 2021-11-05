/*
 *
 * This file is generated under this project, "open-commons-core".
 *
 * Date  : 2020. 11. 4. 오후 6:15:42
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package open.commons.database;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Supplier;

import open.commons.csv.AbstractCsvData;
import open.commons.csv.CsvConfig;
import open.commons.csv.MethodBase;
import open.commons.database.annotation.AQueryIndex;
import open.commons.utils.AnnotationUtils;
import open.commons.utils.StringUtils;

/**
 * 
 * @since 2020. 11. 4.
 * @version
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
public class IndexedColumnDTO extends AbstractCsvData {

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 6. 18.		박준홍			최초 작성
     * </pre>
     *
     *
     * @since 2020. 11.4.
     * @version 1.8.0
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @see CsvConfig#DEFAULT_SEPARATOR
     * @see CsvConfig.DEFAULT_QUOTE_CHARACTER
     * @see CsvConfig.DEFAULT_ESCAPE_CHARACTER
     */
    public IndexedColumnDTO() {
        super();
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 6. 18.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2021. 6. 18.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see open.commons.csv.AbstractCsvData#getHeaders()
     */
    @Override
    public List<String> getHeaders() {
        Map<Integer, String> fieldMap = new TreeMap<>();
        Class<?> cls = getClass();
        List<Field> fields = null;
        while (!cls.equals(Object.class)) {
            fields = AnnotationUtils.getAnnotatedFieldsAll(cls, AQueryIndex.class);
            fields.forEach(f -> {
                AQueryIndex index = f.getAnnotation(AQueryIndex.class);
                fieldMap.put(index.index(), StringUtils.toSnakeCase(f.getName()));
            });

            cls = cls.getSuperclass();
        }

        return new ArrayList<>(fieldMap.values());
    }

    /**
     * CSV 데이터 제공자를 반환한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 6. 18.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2021. 6. 18.
     * @version 1.8.0
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public List<Supplier<String>> getValues() {
        Map<Integer, Supplier<String>> orderedValues = new TreeMap<>();
        Class<?> cls = getClass();
        List<Method> methods = null;
        while (!cls.equals(Object.class)) {
            methods = AnnotationUtils.getAnnotatedMethodsAll(cls, AQueryIndex.class);
            methods.forEach(m -> {
                AQueryIndex index = m.getAnnotation(AQueryIndex.class);
                orderedValues.put(index.index(), new MethodBase(this, m));
            });

            cls = cls.getSuperclass();
        }

        return new ArrayList<>(orderedValues.values());
    }
}
