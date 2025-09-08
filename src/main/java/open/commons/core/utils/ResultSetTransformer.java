/*
 * Copyright 2025 Park Jun-Hong (parkjunhong77@gmail.com)
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
 * Date  : 2025. 9. 8. 오후 1:39:23
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package open.commons.core.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import open.commons.core.annotation.ColumnDef;

/**
 * 
 * @since 2025. 9. 8.
 * @version 2.1.0
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
public class ResultSetTransformer {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResultSetTransformer.class);

    /** '테이블 데이터 유형'별 함수 캐시 */
    private static final ConcurrentHashMap<RowKey, Function<ResultSet, Object>> ROW_MAPPER_CACHE = new ConcurrentHashMap<>();
    /** '테이블 데이터 유형'을 구성하는 컬럼별 함수 캐시 */
    private static final ConcurrentHashMap<Class<?>, List<ColumnPlan>> COLUMN_PLAN_CACHE = new ConcurrentHashMap<>();

    private ResultSetTransformer() {
    }

    /**
     * <code>(setterMH bound, notNullable, colName, target, value) -> void</code> <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 8.		박준홍			최초 작성
     * </pre>
     *
     * @param setter
     * @param nullable
     * @param columnName
     * @param target
     * @param value
     *
     * @since 2025. 9. 8.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    @SuppressWarnings("unused")
    private static void applyNullPolicy(MethodHandle setter, boolean nullable, String columnName, Object target, Object value) {
        try {
            if (value == null) {
                if (nullable) {
                    return; // nullable=true면 세터 호출 스킵
                }
                throw new SQLException("Column '" + columnName + "' is NULL but @ColumnDef.nullable=" + nullable);
            }
            // value != null이면 세터 호출
            setter.invokeExact(target, value);
        } catch (Throwable t) {
            throw (t instanceof RuntimeException) ? (RuntimeException) t : new RuntimeException(t);
        }
    }

    @SuppressWarnings("unused")
    private static Object bdToDouble(BigDecimal d) {
        return d == null ? null : Double.valueOf(d.doubleValue());
    }

    @SuppressWarnings("unused")
    private static Object bdToFloat(BigDecimal d) {
        return d == null ? null : Float.valueOf(d.floatValue());
    }

    @SuppressWarnings("unused")
    private static Object bdToInteger(BigDecimal d) {
        return d == null ? null : Integer.valueOf(d.intValue());
    }

    @SuppressWarnings("unused")
    private static Object bdToLong(BigDecimal d) {
        return d == null ? null : Long.valueOf(d.longValue());
    }

    @SuppressWarnings("unused")
    private static Object bdToShort(BigDecimal d) {
        return d == null ? null : Short.valueOf(d.shortValue());
    }

    /**
     * Boolean/Byte 래퍼: prim 호출 + wasNull()로 NULL 보존 <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 8.		박준홍			최초 작성
     * </pre>
     *
     * @param rs
     * @param idx
     * @return
     * @throws SQLException
     *
     * @since 2025. 9. 8.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    @SuppressWarnings("unused")
    private static Object boolWasNullBoolean(ResultSet rs, int idx) throws SQLException {
        boolean v = rs.getBoolean(idx);
        return rs.wasNull() ? null : Boolean.valueOf(v);
    }

    @SuppressWarnings("unused")
    private static Object boolWasNullByte(ResultSet rs, int idx) throws SQLException {
        byte v = rs.getByte(idx);
        return rs.wasNull() ? null : Byte.valueOf(v);
    }

    private static Map<String, Integer> buildLabelIndexMap(ResultSetMetaData md) throws SQLException {
        final int n = md.getColumnCount();
        final Map<String, Integer> m = new HashMap<>(n * 2);
        for (int i = 1; i <= n; i++) {
            // getColumnLabel 우선(AS 별칭 보존). 대소문자 정확히 보존해서 저장.
            final String label = md.getColumnLabel(i);
            m.put(label, i);
            // 편의상 대소문자 무시 매핑도 추가(충돌 가능성 없을 때만)
            final String up = label.toUpperCase(Locale.ROOT);
            m.putIfAbsent(up, i);
        }
        return m;
    }

    /**
     * '테이블 데이터 유형'별 함수를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 8.		박준홍			최초 작성
     * </pre>
     *
     * @param entityType
     * @param md
     * @param tags
     * @return
     *
     * @since 2025. 9. 8.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    private static Function<ResultSet, Object> buildRowMapper(Class<?> entityType, ResultSetMetaData md, List<String> tags) {

        try {
            final MethodHandles.Lookup lookup = MethodHandles.lookup();

            // 3-1) 타입별 @ColumnDef 세터 목록 (1회만 스캔/캐시)
            final List<ColumnPlan> allPlans = COLUMN_PLAN_CACHE.computeIfAbsent(entityType, ResultSetTransformer::scanColumnPlans);

            // 3-2) 태그(columns...)가 지정되었으면 그 컬럼만 선택
            final List<ColumnPlan> plans = filterByTags(allPlans, tags);

            // 3-3) 메타데이터에서 컬럼라벨 → 인덱스 맵
            final Map<String, Integer> labelToIndex = buildLabelIndexMap(md);

            // 3-4) 각 스텝 합성: (target, rs) -> void
            final List<MethodHandle> steps = new ArrayList<>(plans.size());

            for (ColumnPlan p : plans) {
                final Integer idx = labelToIndex.get(p.caseSensitive ? p.columnName : p.columnName.toUpperCase());
                if (idx == null) {
                    // 컬럼이 없으면: required=true → 런타임 예외, required=false → 스킵
                    if (p.required) {
                        throw new IllegalStateException("Required column not found: " + p.columnName);
                    } else {
                        continue;
                    }
                }

                // setter: (Object,Object)->void
                if (!p.setter.isAccessible())
                    p.setter.setAccessible(true);

                // #1. reader 준비
                // reader: (ResultSet,int)->Object -> 인덱스 바인딩 → (ResultSet)->Object
                // #1-1. (ResultSet,int)->Object
                MethodHandle reader2 = readerForType(lookup, p.paramType);
                // #1-2. (ResultSet)->Object
                MethodHandle readerBound = MethodHandles.insertArguments(reader2, 1, idx.intValue());

                // #2. setter: (Object,Object)->void
                MethodHandle setter = lookup.unreflect(p.setter);
                setter = MethodHandles.explicitCastArguments(setter, MethodType.methodType(void.class, Object.class, Object.class));

                // #3. null 정책 적용 래퍼: (Object,Object)->void (setter, notNullable, colName 바인딩)
                MethodHandle apply = lookup.findStatic( //
                        ResultSetTransformer.class //
                        , "applyNullPolicy" //
                        , MethodType.methodType(void.class, MethodHandle.class, boolean.class, String.class, Object.class, Object.class) //
                );
                apply = MethodHandles.insertArguments(apply, 0, setter, p.nullable, p.columnName);

                // #4. compose: (target, rs) -> void
                // value = readerBound(rs)
                MethodHandle step = MethodHandles.collectArguments(apply, 1, readerBound);
                step = MethodHandles.explicitCastArguments(step, MethodType.methodType(void.class, Object.class, ResultSet.class));

                // optional 컬럼이면 try/catch 래핑 (SQLException 무시)
                if (!p.required) {
                    // (Object, ResultSet)->void
                    step = wrapIgnoreSQLException(lookup, step);
                }

                steps.add(step);
            }

            // 3-5) 생성자: ()->Object
            // ()->Object
            MethodHandle ctor = constructorMH(lookup, entityType);

            // 3-6) 구현 본체: (ctor, steps[], rs)->Object
            MethodHandle impl = lookup.findStatic(ResultSetTransformer.class //
                    , "materialize" //
                    , MethodType.methodType(Object.class, MethodHandle.class, MethodHandle[].class, ResultSet.class) //
            );

            final MethodHandle[] stepArr = steps.toArray(new MethodHandle[0]);
            // 3-7) LMF로 Function<ResultSet,Object> 생성
            CallSite site = LambdaMetafactory.metafactory( //
                    lookup //
                    , "apply"
                    // invokedType: (ctor, steps) -> Function
                    , MethodType.methodType(Function.class, MethodHandle.class, MethodHandle[].class)
                    // erased SAM: (Object) -> Object
                    , MethodType.methodType(Object.class, Object.class)
                    // implMethod: direct handle (ctor, steps, rs) -> Object
                    , impl
                    // instantiated SAM: (ResultSet) -> Object
                    , MethodType.methodType(Object.class, ResultSet.class));

            Function<ResultSet, Object> mapper = (Function<ResultSet, Object>) site.getTarget().invoke(ctor, stepArr);
            return mapper;
        } catch (Throwable t) {
            LOGGER.warn("buildRowMapper failed for {}, cause={}", entityType, t.getMessage(), t);
            throw new RuntimeException("buildRowMapper failed for: " + entityType, t);
        }
    }

    /**
     * '테이블 데이터 유형' 생성자 함수를 제공합니다. <br>
     * <b>유형: 실행 루틴/헬퍼</b>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 8.		박준홍			최초 작성
     * </pre>
     *
     * @param lookup
     * @param cls
     * @return
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     *
     * @since 2025. 9. 8.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    private static MethodHandle constructorMH(MethodHandles.Lookup lookup, Class<?> cls) throws NoSuchMethodException, IllegalAccessException {
        MethodHandle h = lookup.findConstructor(cls, MethodType.methodType(void.class)); // ()V
        return h.asType(MethodType.methodType(Object.class)); // ()->Object
    }

    private static List<ColumnPlan> filterByTags(List<ColumnPlan> all, List<String> tags) {
        if (tags == null || tags.isEmpty())
            return all;
        final Set<String> allowed = new HashSet<>(tags); // 기존처럼 대소문자 보존 비교
        final List<ColumnPlan> res = new ArrayList<>(all.size());
        for (ColumnPlan p : all)
            if (allowed.contains(p.columnName))
                res.add(p);
        return res;
    }

    @SuppressWarnings("unused")
    private static Object getBooleanOrNull(ResultSet rs, int idx) throws SQLException {
        boolean v = rs.getBoolean(idx);
        return rs.wasNull() ? null : Boolean.valueOf(v);
    }

    @SuppressWarnings("unused")
    private static Object getByteOrNull(ResultSet rs, int idx) throws SQLException {
        byte v = rs.getByte(idx);
        return rs.wasNull() ? null : Byte.valueOf(v);
    }

    @SuppressWarnings("unused")
    private static Object getDoubleOrNull(ResultSet rs, int idx) throws SQLException {
        double v = rs.getDouble(idx);
        return rs.wasNull() ? null : Double.valueOf(v);
    }

    @SuppressWarnings("unused")
    private static Object getFloatOrNull(ResultSet rs, int idx) throws SQLException {
        float v = rs.getFloat(idx);
        return rs.wasNull() ? null : Float.valueOf(v);
    }

    @SuppressWarnings("unused")
    private static Object getIntOrNull(ResultSet rs, int idx) throws SQLException {
        int v = rs.getInt(idx);
        return rs.wasNull() ? null : Integer.valueOf(v);
    }

    @SuppressWarnings("unused")
    private static Object getLongOrNull(ResultSet rs, int idx) throws SQLException {
        long v = rs.getLong(idx);
        return rs.wasNull() ? null : Long.valueOf(v);
    }

    @SuppressWarnings("unused")
    private static Object getShortOrNull(ResultSet rs, int idx) throws SQLException {
        short v = rs.getShort(idx);
        return rs.wasNull() ? null : Short.valueOf(v);
    }

    /**
     * ctor()로 객체 만들고, steps를 순회해 채운 뒤 객체를 반환합니다. <br>
     * <b>유형: 실행 루틴/헬퍼</b>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 8.		박준홍			최초 작성
     * </pre>
     *
     * @param ctor
     * @param steps
     * @param rs
     * @return
     *
     * @since 2025. 9. 8.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    @SuppressWarnings("unused")
    private static Object materialize(MethodHandle ctor, MethodHandle[] steps, ResultSet rs) {
        try {
            final Object dst = ctor.invoke(); // ()->Object
            for (int i = 0; i < steps.length; i++) {
                steps[i].invokeExact(dst, rs); // (Object, ResultSet)->void
            }
            return dst;
        } catch (Throwable t) {
            throw (t instanceof RuntimeException) ? (RuntimeException) t : new RuntimeException(t);
        }
    }

    /**
     * (ResultSet,int)->Object (드라이버가 이미 Object 반환하는 오버로드를 제공) <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 8.		박준홍			최초 작성
     * </pre>
     *
     * @param lookup
     * @param name
     * @param returnType
     * @return
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     *
     * @since 2025. 9. 8.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    private static MethodHandle mhObj(MethodHandles.Lookup lookup, String name, Class<?> returnType) throws NoSuchMethodException, IllegalAccessException {
        MethodHandle h = lookup.findVirtual(ResultSet.class //
                , name //
                , MethodType.methodType(returnType, int.class) //
        );
        // 호출 시에는 항상 Object로 다루기 위해 asType으로 박싱/업캐스트
        return h.asType(MethodType.methodType(Object.class, ResultSet.class, int.class));
    }

    /** (ResultSet,int)->prim → (ResultSet,int)->Object (박싱) */
    private static MethodHandle mhPrimOrNull(MethodHandles.Lookup lookup, String name, Class<?> primRet) throws NoSuchMethodException, IllegalAccessException {
        return lookup.findStatic(ResultSetTransformer.class, name, MethodType.methodType(Object.class, ResultSet.class, int.class));
    }

    /**
     * 래퍼형: getBigDecimal → 변환 (NULL 보존) <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 8.		박준홍			최초 작성
     * </pre>
     *
     * @param lookup
     * @param convStaticName
     * @return
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     *
     * @since 2025. 9. 8.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    private static MethodHandle mhWrapFromBigDecimal(MethodHandles.Lookup lookup, String convStaticName) throws NoSuchMethodException, IllegalAccessException {
        // reader: (ResultSet,int)->Object (BigDecimal)
        MethodHandle reader = mhObj(lookup, "getBigDecimal", BigDecimal.class);
        // converter: (BigDecimal)->Object(wrapper)
        MethodHandle conv = lookup.findStatic(ResultSetTransformer.class, convStaticName, MethodType.methodType(Object.class, BigDecimal.class));
        // collectArguments: conv(reader(rs,i))
        return MethodHandles.collectArguments(conv, 0, reader); // (ResultSet,int)->Object
    }

    /**
     * 래퍼형 중 boolean/byte: prim read + wasNull 검사 <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 8.		박준홍			최초 작성
     * </pre>
     *
     * @param lookup
     * @param staticName
     * @return
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     *
     * @since 2025. 9. 8.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    private static MethodHandle mhWrapFromPrimWasNull(MethodHandles.Lookup lookup, String staticName) throws NoSuchMethodException, IllegalAccessException {
        // 직접 구현
        return lookup.findStatic(ResultSetTransformer.class, staticName, MethodType.methodType(Object.class, ResultSet.class, int.class));
    }

    @SuppressWarnings("unchecked")
    public static <T> T newInstance(Class<T> objectType, ResultSet rs, final String... columns) throws SQLException {
        // 2-1) ResultSet 메타데이터 시그니처
        final ResultSetMetaData md = rs.getMetaData();
        // 컬럼 라벨/타입/개수 기반 시그니처
        final String mdSig = resultSetSignature(md);

        // 2-2) 태그(= columns 인자) 정규화
        // 원래 구현처럼 대소문자/순서 그대로 유지
        final List<String> tags = (columns == null || columns.length == 0) //
                ? Collections.emptyList() //
                : Arrays.asList(columns);

        // 2-3) RowMapper 준비/캐시
        final RowKey key = new RowKey(objectType, tags, mdSig);
        final Function<ResultSet, Object> mapper = ROW_MAPPER_CACHE.computeIfAbsent(key, k -> buildRowMapper(objectType, md, tags));

        // 2-4) 실행
        try {
            return (T) mapper.apply(rs);
        } catch (RuntimeException re) {
            throw re;
        } catch (Throwable t) {
            // 일반적으로 mapper.apply 에서 RuntimeException만 나옵니다.
            throw new SQLException("Row mapping failed for " + objectType, t);
        }
    }

    private static byte[] readAll(InputStream in) throws java.io.IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] b = new byte[8192];
        int n;
        while ((n = in.read(b)) != -1)
            bos.write(b, 0, n);
        return bos.toByteArray();
    }

    /**
     * getBinaryStream(idx)를 모두 읽어 ByteArrayInputStream 으로 감싸 반환 (NULL 안전) <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 8.		박준홍			최초 작성
     * </pre>
     *
     * @param rs
     * @param idx
     * @return
     * @throws SQLException
     *
     * @since 2025. 9. 8.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    @SuppressWarnings("unused")
    private static Object readAsByteArrayInputStream(ResultSet rs, int idx) throws SQLException {
        InputStream in = rs.getBinaryStream(idx);
        if (in == null)
            return null;
        try {
            byte[] buf = readAll(in);
            return new java.io.ByteArrayInputStream(buf);
        } catch (IOException ioe) {
            throw new SQLException("Failed to read binary stream", ioe);
        }
    }

    /**
     * 컬럼 데이터 유형에 맞는 함수를 제공합니다. <br>
     * <br>
     * 팁: (ResultSet, int) -> Object 로 값을 읽는 MH 반환. 부족한 타입은 getObject로 폴백.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 8.		박준홍			최초 작성
     * </pre>
     *
     * @param lookup
     * @param columnType
     * @return
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     *
     * @since 2025. 9. 8.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    private static MethodHandle readerForType(MethodHandles.Lookup lookup, Class<?> columnType) throws NoSuchMethodException, IllegalAccessException {

        final Class<?> pt = unwrap(columnType); // 원시/래퍼 정규화

        // ---------- 1) 원시형 (NULL 구분 불필요) ----------
        if (pt == int.class)
            return mhPrimOrNull(lookup, "getIntOrNull", int.class);
        if (pt == long.class)
            return mhPrimOrNull(lookup, "getLongOrNull", long.class);
        if (pt == double.class)
            return mhPrimOrNull(lookup, "getDoubleOrNull", double.class);
        if (pt == float.class)
            return mhPrimOrNull(lookup, "getFloatOrNull", float.class);
        if (pt == short.class)
            return mhPrimOrNull(lookup, "getShortOrNull", short.class);
        if (pt == byte.class)
            return mhPrimOrNull(lookup, "getByteOrNull", byte.class);
        if (pt == boolean.class)
            return mhPrimOrNull(lookup, "getBooleanOrNull", boolean.class);

        // ---------- 2) 래퍼형 (NULL 보존) ----------
        if (columnType == Integer.class)
            return mhWrapFromBigDecimal(lookup, "bdToInteger"); // getBigDecimal → Integer
        if (columnType == Long.class)
            return mhWrapFromBigDecimal(lookup, "bdToLong");
        if (columnType == Short.class)
            return mhWrapFromBigDecimal(lookup, "bdToShort");
        if (columnType == Float.class)
            return mhWrapFromBigDecimal(lookup, "bdToFloat");
        if (columnType == Double.class)
            return mhWrapFromBigDecimal(lookup, "bdToDouble");
        if (columnType == Byte.class)
            return mhWrapFromPrimWasNull(lookup, "boolWasNullByte"); // getByte + wasNull
        if (columnType == Boolean.class)
            return mhWrapFromPrimWasNull(lookup, "boolWasNullBoolean"); // getBoolean + wasNull

        // ---------- 3) 문자열/숫자/시간 ----------
        if (columnType == String.class)
            return mhObj(lookup, "getString", String.class);
        if (columnType == BigDecimal.class)
            return mhObj(lookup, "getBigDecimal", BigDecimal.class);
        if (columnType == Date.class)
            return mhObj(lookup, "getDate", Date.class);
        if (columnType == Time.class)
            return mhObj(lookup, "getTime", Time.class);
        if (columnType == Timestamp.class)
            return mhObj(lookup, "getTimestamp", Timestamp.class);

        // ---------- 4) LOB/바이너리/문자 스트림 ----------
        if (columnType == byte[].class)
            return mhObj(lookup, "getBytes", byte[].class);
        if (columnType == Blob.class)
            return mhObj(lookup, "getBlob", Blob.class);
        if (columnType == Clob.class)
            return mhObj(lookup, "getClob", Clob.class);
        if (columnType == NClob.class)
            return mhObj(lookup, "getNClob", NClob.class);

        // Streams/Readers
        if (InputStream.class.isAssignableFrom(columnType)) {
            // 기본은 바이너리 스트림 (ASCII가 필요하면 별도 어노테이션 신호를 두는 것을 권장)
            return mhObj(lookup, "getBinaryStream", InputStream.class);
        }
        if (columnType == ByteArrayInputStream.class) {
            // ByteArrayInputStream이 정확히 필요하다면 내부에서 모두 읽어 래핑
            return lookup.findStatic(ResultSetTransformer.class, "readAsByteArrayInputStream", MethodType.methodType(Object.class, ResultSet.class, int.class));
        }
        if (Reader.class.isAssignableFrom(columnType)) {
            return mhObj(lookup, "getCharacterStream", Reader.class);
        }

        // ---------- 5) 기타 ----------
        if (columnType == Array.class)
            return mhObj(lookup, "getArray", Array.class);
        if (columnType == URL.class)
            return mhObj(lookup, "getURL", URL.class);

        // ---------- 6) 마지막 수단: JDBC 4.1+ getObject(int, Class<T>) ----------
        try {
            MethodHandle getObjWithType = lookup.findVirtual(ResultSet.class //
                    , "getObject" //
                    , MethodType.methodType(Object.class, int.class, Class.class) //
            );

            // (ResultSet,int)->Object
            return MethodHandles.insertArguments(getObjWithType, 2, columnType);
        } catch (NoSuchMethodException e) {
            // 폴백: getObject(int)
            return lookup.findVirtual(ResultSet.class, "getObject", MethodType.methodType(Object.class, int.class));
        }
    }

    private static String resultSetSignature(ResultSetMetaData md) {
        try {
            final int n = md.getColumnCount();
            final StringBuilder sb = new StringBuilder(64 + n * 16);
            sb.append(n).append('|');
            for (int i = 1; i <= n; i++) {
                sb.append(md.getColumnLabel(i)).append('#').append(md.getColumnType(i)).append('|');
            }
            return sb.toString();
        } catch (SQLException e) {
            // 시그니처 실패 시, 캐시 효율은 떨어지지만 안전하게 currentTime 기반 대체
            return "ERRSIG|" + System.identityHashCode(md);
        }
    }

    /**
     * '테이블 데이터 유형'을 구성하는 컬럼 함수를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 8.		박준홍			최초 작성
     * </pre>
     *
     * @param type
     * @return
     *
     * @since 2025. 9. 8.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    private static List<ColumnPlan> scanColumnPlans(Class<?> type) {
        final List<ColumnPlan> list = new ArrayList<>();

        for (Method m : AnnotationUtils.getAnnotatedMethodsAll(type, ColumnDef.class)) {
            final ColumnDef def = m.getAnnotation(ColumnDef.class);
            final Class<?> paramType = m.getParameterTypes()[0];

            Class<?> defType = def.type();
            if (defType == Class.class) { // 기존 코드와 동일한 정책
                defType = paramType;
            }

            final String colName = SQLUtils.getColumnName(def, m); // ★ 기존 유틸 재사용(대/소문자/패턴 정책 유지)
            list.add(new ColumnPlan(colName, def.caseSensitive(), def.required(), defType, def.nullable(), m));
        }
        return list;
    }

    @SuppressWarnings("unused")
    private static void tryIgnoreSql(MethodHandle step, Object target, ResultSet rs) {
        try {
            step.invokeExact(target, rs);
        } catch (Throwable t) {
            if (!(t instanceof SQLException)) {
                throw (t instanceof RuntimeException) ? (RuntimeException) t : new RuntimeException(t);
            }
            // SQLException 은 무시 (optional 컬럼)
        }
    }

    /**
     * 래퍼 → 원시 정규화 <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 8.		박준홍			최초 작성
     * </pre>
     *
     * @param c
     * @return
     *
     * @since 2025. 9. 8.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    private static Class<?> unwrap(Class<?> c) {
        if (c == Integer.class)
            return int.class;
        if (c == Long.class)
            return long.class;
        if (c == Double.class)
            return double.class;
        if (c == Float.class)
            return float.class;
        if (c == Short.class)
            return short.class;
        if (c == Boolean.class)
            return boolean.class;
        if (c == Byte.class)
            return byte.class;
        return c;
    }

    /**
     * SQLException만 삼키는 래퍼: (Object, ResultSet)->void <br>
     * <b>유형: 실행 루틴/헬퍼</b>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 8.		박준홍			최초 작성
     * </pre>
     *
     * @param lookup
     * @param step
     * @return
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     *
     * @since 2025. 9. 8.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    private static MethodHandle wrapIgnoreSQLException(MethodHandles.Lookup lookup, MethodHandle step) throws NoSuchMethodException, IllegalAccessException {
        MethodHandle wrapper = lookup.findStatic(ResultSetTransformer.class, "tryIgnoreSql", MethodType.methodType(void.class, MethodHandle.class, Object.class, ResultSet.class));
        // (Object,ResultSet)->void
        wrapper = MethodHandles.insertArguments(wrapper, 0, step);
        return wrapper;
    }

    private static final class ColumnPlan {
        /**
         * {@link ColumnDef#name()}<br>
         * 
         * @see SQLUtils#getColumnName(ColumnDef, Method)
         */
        final String columnName;
        /** {@link ColumnDef#caseSensitive()} */
        final boolean caseSensitive;
        /** {@link ColumnDef#required()} */
        final boolean required;
        /** {@link ColumnDef#type()} */
        final Class<?> paramType;
        /** {@link ColumnDef#nullable()} */
        final boolean nullable;
        final Method setter;

        ColumnPlan(String columnName, boolean caseSensitive, boolean required, Class<?> paramType, boolean nullable, Method setter) {
            this.columnName = columnName;
            this.caseSensitive = caseSensitive;
            this.required = required;
            this.paramType = paramType;
            this.nullable = nullable;
            this.setter = setter;
        }
    }

    private static final class RowKey {
        final Class<?> type;
        final List<String> tags; // columns 인자 (순서/대소문자 보존)
        final String mdSig; // ResultSetMetaData 시그니처

        RowKey(Class<?> type, List<String> tags, String mdSig) {
            this.type = type;
            this.tags = tags;
            this.mdSig = mdSig;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (!(o instanceof RowKey))
                return false;
            RowKey k = (RowKey) o;
            return type == k.type && Objects.equals(tags, k.tags) && Objects.equals(mdSig, k.mdSig);
        }

        @Override
        public int hashCode() {
            return Objects.hash(type, tags, mdSig);
        }
    }
}
