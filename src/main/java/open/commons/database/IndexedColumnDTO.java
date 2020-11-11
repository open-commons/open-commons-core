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
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import open.commons.database.annotation.AQueryIndex;
import open.commons.function.TripleFunction;
import open.commons.utils.AnnotationUtils;
import open.commons.utils.StringUtils;

/**
 * 
 * @since 2020. 11. 4.
 * @version
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
public class IndexedColumnDTO {

    private static final TripleFunction<Object, Method, String, String> VALUE = (o, m, v) -> {
        Object val;
        try {
            val = m.invoke(o);
            return val == null ? v : val.toString();
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new RuntimeException("데이터 추출 중 에러가 발생하였습니다.", e);
        }
    };

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 11. 4.     박준홍         최초 작성
     * </pre>
     *
     * @since 2020. 11. 4.
     */
    public IndexedColumnDTO() {
    }

    /**
     * 객체가 포함한 데이터를 CSV 파일 문자열 형태로 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 11. 4.     박준홍         최초 작성
     * </pre>
     *
     * @param delim
     *            CSV 데이터 구분자
     * @param nullValue
     *            <code>null</code>인 경우 데이터
     * @return
     *
     * @since 2020. 11. 4.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * 
     * @see AQueryIndex
     */
    public String csv(String delim, String nullValue) {
        Map<Integer, Method> fieldMap = new TreeMap<>();
        Class<?> cls = getClass();
        List<Method> methods = null;
        while (!cls.equals(Object.class)) {
            methods = AnnotationUtils.getAnnotatedMethodsAll(cls, AQueryIndex.class);
            methods.forEach(m -> {
                AQueryIndex index = m.getAnnotation(AQueryIndex.class);
                fieldMap.put(index.index(), m);
            });

            cls = cls.getSuperclass();
        }

        List<String> csvStr = new ArrayList<>();
        for (Method m : fieldMap.values()) {
            csvStr.add(VALUE.apply(this, m, nullValue));
        }

        return String.join(delim, csvStr);
    }

    /**
     * 컬럼명을 순서대로 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 11. 4.		박준홍			최초 작성
     * </pre>
     * 
     * @param subType
     * 
     * @return
     *
     * @since 2020. 11. 4.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @see AQueryIndex
     */
    public static List<String> columns(Class<?> subType) {

        Map<Integer, String> fieldMap = new TreeMap<>();
        Class<?> cls = subType;
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
}
