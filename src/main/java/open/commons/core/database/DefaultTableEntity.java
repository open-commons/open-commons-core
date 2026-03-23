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
* This file is generated under this project, "open-commons-core".
*
* Date  : 2013. 7. 12. 오후 5:04:26
*
* Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
* 
*/

/**
 * 
 */
package open.commons.core.database;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.jspecify.annotations.Nullable;

import open.commons.core.database.annotation.ColumnConf;
import open.commons.core.database.annotation.TableDef;
import open.commons.core.utils.ObjectUtils;

/**
 * 데이터베이스 테이블 엔티티를 위한 기본 구현체입니다.
 * 
 * <pre>
 * [개정이력]
 * 날짜      | 작성자   |   내용
 * ------------------------------------------
 * 2026. 3. 4.          parkjunhong77@gmail.com         (3.0.0) JDK 25 마이그레이션: 클래스 레벨 캐싱 적용 및 reflection 최적화
 * </pre>
 * 
 * @since 2013. 7. 12.
 * @author Park Jun-Hong (parkjunhong77@gmail.com) *
 * 
 */
public abstract class DefaultTableEntity implements ITableEntity {

    /** 메타데이터 생성 함수. */
    private static Function<Class<?>, TableMetadata> TABLE_METADATA_GEN = clazz -> {
        // 1. 테이블 이름 조회
        @Nullable
        TableDef tableDef = clazz.getAnnotation(TableDef.class);
        @SuppressWarnings("null")
        String tableName = tableDef != null ? tableDef.table() : null;

        // 2. 어노테이션된 모든 필드 추출 및 접근 권한 설정
        List<ColumnMeta> all = Arrays.stream(clazz.getDeclaredFields()) //
                .filter(f -> f.isAnnotationPresent(ColumnConf.class)) //
                .peek(f -> f.trySetAccessible()) //
                .map(f -> new ColumnMeta(f, f.getAnnotation(ColumnConf.class))) //
                .toList();

        // 3. 인덱스 맵 구성 (중복 허용)
        Map<Integer, List<ColumnMeta>> indexMap = all.stream().collect(Collectors.groupingBy(m -> m.config().index()));

        // 4. 모든 인덱스 셋 (정렬됨)
        Set<Integer> allIndices = new TreeSet<>(indexMap.keySet());

        return new TableMetadata(tableName, all, indexMap, allIndices);
    };

    /** 클래스별 테이블 메타데이터 캐시 */
    private static final Map<Class<?>, TableMetadata> CACHE_TABLE_METADATA = new ConcurrentHashMap<>();

    protected final String CLASS = Objects.requireNonNull(getClass().getSimpleName());

    public DefaultTableEntity() {
    }

    @Override
    public int compareTo(@Nullable ITableEntity o) {
        if (o == null) {
            return -1;
        } else {
            return toString().compareTo(o.toString());
        }
    }

    /**
     * 설정된 컬럼의 총 개수를 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 6. 18.         parkjunhong77@gmail.com         최초 작성
     * 2026. 3. 4.          parkjunhong77@gmail.com         (3.0.0) JDK 25 마이그레이션: 클래스 레벨 캐싱 적용 및 reflection 최적화
     * </pre>
     *
     * @return 컬럼 개수
     *
     * @since 2021. 6. 18.
     * @version 2.0.0
     * 
     */
    @Override
    public final int count() {
        return getMetadata().allColumns().size();
    }

    @Override
    public String createInsertQuery() {
        return createInsertQuery(getTableName());
    }

    /**
     * @throws NullPointerException
     *             파라미터({@code table})가 {@code null}인 경우 발생.
     */
    @Override
    public String createInsertQuery(String table) {
        Objects.requireNonNull(table, "테이블 이름이 설정되지 않았습니다.");

        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO ").append(table).append(" (").append(serializeColumns(getAllColumns())).append(") VALUES (").append(serializeValues(getAllValues())).append(");");
        return Objects.requireNonNull(query.toString());
    }

    private String createKV(Collection<ColumnConf> columns, Collection<String> values, String concatenator) {
        StringBuilder sb = new StringBuilder();
        Iterator<ColumnConf> itrCols = columns.iterator();
        Iterator<String> itrVals = values.iterator();

        if (itrCols.hasNext()) {
            sb.append(itrCols.next().column()).append("=").append(itrVals.next());
            while (itrCols.hasNext()) {
                sb.append(" ").append(concatenator).append(" ").append(itrCols.next().column()).append("=").append(itrVals.next());
            }
        }
        return Objects.requireNonNull(sb.toString());
    }

    /**
     * 
     * @throws NullPointerException
     *             파라미터({@code selects, where})가 {@code null}인 경우 발생.
     *
     * @see open.commons.core.database.ITableEntity#createSelectQuery(java.util.Collection, int[])
     */
    @Override
    public String createSelectQuery(Collection<Integer> selects, int... where) {
        ObjectUtils.requireNonNulls(selects, where);

        return createSelectQuery(getTableName(), selects, where);
    }

    /**
     * @throws NullPointerException
     *             파라미터({@code where})가 {@code null}인 경우 발생.
     */
    @Override
    public String createSelectQuery(int... where) {
        Objects.requireNonNull(where);

        return createSelectQuery(getTableName(), getAllIndice(), where);
    }

    /**
     * @throws NullPointerException
     *             파라미터({@code table, selects, where})가 {@code null}인 경우 발생.
     */
    @Override
    public String createSelectQuery(String table, Collection<Integer> selects, int... where) {
        ObjectUtils.requireNonNullsWithMessage(table, selects, where);

        StringBuilder query = new StringBuilder();
        query.append("SELECT ").append(serializeColumns(getColumns(selects)));
        query.append(" FROM ").append(table);

        if (where.length > 0) {
            query.append(" WHERE ").append(createKV(getColumns(where), getValues(where), "AND"));
        }
        return Objects.requireNonNull(query.toString());
    }

    /**
     * @throws NullPointerException
     *             파라미터({@code table, where})가 {@code null}인 경우 발생.
     */
    @Override
    public String createSelectQuery(String table, int... where) {
        ObjectUtils.requireNonNullsWithMessage(table, where);

        return createSelectQuery(table, getAllIndice(), where);
    }

    /**
     * @throws NullPointerException
     *             파라미터({@code updates, where})가 {@code null}인 경우 발생.
     */
    @Override
    public String createUpdateQuery(Collection<Integer> updates, int... where) {
        ObjectUtils.requireNonNulls(updates, where);

        return createUpdateQuery(getTableName(), updates, where);
    }

    /**
     * @throws NullPointerException
     *             파라미터({@code where})가 {@code null}인 경우 발생.
     */
    @Override
    public String createUpdateQuery(int... where) {
        Objects.requireNonNull(where);

        Collection<Integer> allColumns = new TreeSet<>(getAllIndice());
        for (int i : where) {
            allColumns.remove(i);
        }
        return createUpdateQuery(getTableName(), allColumns, where);
    }

    /**
     * @throws NullPointerException
     *             파라미터({@code table, updates, where})가 {@code null}인 경우 발생.
     */
    @Override
    public String createUpdateQuery(String table, Collection<Integer> updates, int... where) {
        ObjectUtils.requireNonNullsWithMessage(table, updates, where);

        StringBuilder query = new StringBuilder();
        query.append("UPDATE ").append(table).append(" SET ").append(createKV(getColumns(updates), getValues(updates), ","));

        if (where.length > 0) {
            query.append(" WHERE ").append(createKV(getColumns(where), getValues(where), "AND"));
        }
        return Objects.requireNonNull(query.toString());
    }

    /**
     * @throws NullPointerException
     *             파라미터({@code table, where})가 {@code null}인 경우 발생.
     */
    @Override
    public String createUpdateQuery(String table, int... where) {
        ObjectUtils.requireNonNullsWithMessage(table, where);

        Collection<Integer> allColumns = new TreeSet<>(getAllIndice());
        for (int i : where) {
            allColumns.remove(i);
        }
        return createUpdateQuery(table, allColumns, where);
    }

    private Collection<ColumnConf> getAllColumns() {
        return getColumns(getAllIndice());
    }

    private Collection<Integer> getAllIndice() {
        return getMetadata().allIndices();
    }

    private Collection<String> getAllValues() {
        return getValues(getAllIndice());
    }

    private Collection<ColumnConf> getColumns(Collection<Integer> indice) {
        TableMetadata meta = getMetadata();

        return Objects.requireNonNull(indice.stream() //
                .map(i -> meta.indexMap().get(i)) //
                .filter(Objects::nonNull) //
                .flatMap(List::stream) //
                .map(ColumnMeta::config) //
                .filter(Objects::nonNull) //
                .toList());
    }

    private Collection<ColumnConf> getColumns(int... indice) {
        return getColumns(Objects.requireNonNull(Arrays.stream(indice).boxed().toList()));
    }

    /**
     * 현재 클래스의 메타데이터를 가져오거나 생성합니다.
     * 
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2026. 3. 4.          parkjunhong77@gmail.com         (3.0.0) JDK 25 마이그레이션: 클래스 레벨 캐싱 적용 및 reflection 최적화
     * </pre>
     *
     * @return {@link TableMetadata} 객체
     */
    private TableMetadata getMetadata() {
        TableMetadata cached = Objects.requireNonNull(
                // [PATCH[ JDK 표준 API의 JSpecify 미지원 우회용 임시 널 체크.
                // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 requireNonNull 래핑 제거.
                CACHE_TABLE_METADATA.computeIfAbsent(getClass(), TABLE_METADATA_GEN) //
        );

        return cached;
    }

    private String getTableName() {
        return getMetadata().tableName();
    }

    private Collection<String> getValues(Collection<Integer> indice) {
        TableMetadata metaData = getMetadata();
        List<String> values = new ArrayList<>();

        for (Integer index : indice) {
            List<ColumnMeta> metas = metaData.indexMap().get(index);
            if (metas != null) {
                for (ColumnMeta meta : metas) {
                    String val = String.valueOf(getValueSafe(meta.field()));
                    values.add(meta.config().refer() ? "'" + val + "'" : val);
                }
            }
        }
        return values;
    }

    private Collection<String> getValues(int... indice) {
        return getValues(Objects.requireNonNull( //
                Arrays.stream(indice).boxed().toList() //
        ));
    }

    private Object getValueSafe(Field field) {
        try {
            return Objects.requireNonNull(//
                    field.get(this) //
            );
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @SuppressWarnings("null")
    private String serializeColumns(Collection<ColumnConf> columns) {
        return Objects.requireNonNull(
                // [PATCH[ JDK 표준 API의 JSpecify 미지원 우회용 임시 널 체크.
                // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 requireNonNull 래핑 제거.
                columns.stream().map(ColumnConf::column).collect(Collectors.joining(", ")) //
        );
    }

    private String serializeValues(Collection<String> values) {
        return Objects.requireNonNull(
                // [PATCH[ JDK 표준 API의 JSpecify 미지원 우회용 임시 널 체크.
                // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 requireNonNull 래핑 제거.
                String.join(", ", values) //
        );
    }

    /**
     * 
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2026. 3. 4.          parkjunhong77@gmail.com         (3.0.0) JDK 25 마이그레이션: 클래스 레벨 캐싱 적용 및 reflection 최적화
     * </pre>
     * 
     * @see open.commons.core.database.ITableEntity#setValue(int, java.lang.Object)
     */
    @Override
    public boolean setValue(int index, Object value) {
        // 1. 캐시된 인덱스 맵에서 필드 리스트를 가져옵니다.
        List<ColumnMeta> metas = getMetadata().indexMap().get(index);

        // 해당 인덱스가 존재하지 않으면 실패 반환
        if (metas == null || metas.isEmpty()) {
            return false;
        }

        boolean isSet = false;
        for (ColumnMeta meta : metas) {
            try {
                // 2. 이미 trySetAccessible()이 완료된 필드를 사용하여 값을 설정합니다.
                meta.field().set(this, value);
                isSet = true;
            } catch (IllegalArgumentException | IllegalAccessException ignored) {
            }
        }

        return isSet;
    }

    /**
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2026. 3. 4.          parkjunhong77@gmail.com         (3.0.0) JDK 25 마이그레이션: 클래스 레벨 캐싱 적용 및 reflection 최적화
     * </pre>
     *
     * 
     *
     * @see open.commons.core.database.ITableEntity#toParameters()
     */
    @Override
    public String[] toParameters() {
        TableMetadata metaData = getMetadata();
        String[] params = new String[count()];

        for (ColumnMeta meta : metaData.allColumns()) {
            int idx = meta.config().index();
            if (idx >= 0 && idx < params.length) {
                params[idx] = String.valueOf(getValueSafe(meta.field()));
            }
        }
        return params;
    }

    /**
     * 필드와 설정 정보를 담는 불변 레코드입니다.
     */
    private record ColumnMeta(Field field, ColumnConf config) {
    }

    /**
     * 클래스 전체의 메타데이터를 관리하는 레코드입니다.
     */
    private record TableMetadata(String tableName, List<ColumnMeta> allColumns, Map<Integer, List<ColumnMeta>> indexMap, Set<Integer> allIndices) {
    }
}