/*
 * Copyright 2011 Park Jun-Hong (parkjunhong77/google/com)
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
* Author: Park_Jun_Hong_(fafanmama_at_naver_com)
* 
*/

/**
 * 
 */
package open.commons.database;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;

import open.commons.database.annotation.ColumnConf;
import open.commons.database.annotation.TableDef;
import open.commons.utils.AnnotationUtils;

/**
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */
public abstract class DefaultTableEntity implements ITableEntity {

    protected final String CLASS = getClass().getSimpleName();

    /** whether a Column Count is updated or not. */
    private boolean ccUpdpated = false;

    protected int columnCount = 0x00;

    public DefaultTableEntity() {
    }

    /**
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(ITableEntity o) {
        return toString().compareTo(o.toString());
    }

    /**
     * 
     * @see open.commons.database.ITableEntity#count()
     */
    @Override
    public final int count() {
        int count = 0;

        if (ccUpdpated) {
            count = columnCount;
        } else {

            Class<?> clazz = getClass();

            Field[] fields = clazz.getDeclaredFields();
            ColumnConf cc = null;

            boolean accessible = false;
            for (Field field : fields) {
                accessible = field.isAccessible();

                field.setAccessible(true);
                cc = AnnotationUtils.getAnnotation(field, ColumnConf.class);

                if (cc != null) {
                    count++;
                }

                field.setAccessible(accessible);
            }

            columnCount = count;

            ccUpdpated = true;
        }

        return count;
    }

    /**
     * 
     * @see open.commons.database.ITableEntity#createInsertQuery()
     */
    @Override
    public String createInsertQuery() {
        return createInsertQuery(getTableName());
    }

    /**
     * 
     * @see open.commons.database.ITableEntity#createInsertQuery(java.lang.String)
     */
    @Override
    public String createInsertQuery(String table) {
        StringBuffer query = new StringBuffer();

        query.append("INSERT INTO ");
        query.append(table);
        query.append(" (");
        query.append(serializeColumns(getAllColumns()));
        query.append(')');
        query.append(" VALUES (");
        query.append(serializeValues(getAllValues()));
        query.append(");");

        return query.toString();
    }

    private String createKV(Collection<ColumnConf> columns, Collection<String> values, String concatenator) {
        StringBuffer sb = new StringBuffer();

        Iterator<ColumnConf> itrCols = columns.iterator();
        Iterator<String> itrVals = values.iterator();

        if (itrCols.hasNext()) {
            sb.append(itrCols.next().column() + "=" + itrVals.next());

            while (itrCols.hasNext()) {
                sb.append(" " + concatenator + " " + itrCols.next().column() + "=" + itrVals.next());
            }
        }

        return sb.toString();
    }

    /**
     * 
     * @see open.commons.database.ITableEntity#createSelectQuery(java.util.Collection, int[])
     */
    @Override
    public String createSelectQuery(Collection<Integer> selects, int... where) {
        return createSelectQuery(getTableName(), selects, where);
    }

    /**
     * 
     * @see open.commons.database.ITableEntity#createSelectQuery(int[])
     */
    @Override
    public String createSelectQuery(int... where) {
        return createSelectQuery(getTableName(), getAllIndice(), where);
    }

    /**
     * 
     * @see open.commons.database.ITableEntity#createSelectQuery(java.lang.String, java.util.Collection, int[])
     */
    @Override
    public String createSelectQuery(String table, Collection<Integer> selects, int... where) {
        StringBuffer query = new StringBuffer();

        query.append("SELECT ");
        query.append(serializeColumns(getColumns(selects)));
        if (table != null) {
            query.append(" FROM " + table);
        }

        if (where != null && where.length > 0) {
            query.append(" WHERE ");
            query.append(createKV(getColumns(where), getValues(where), "AND"));
        }

        return query.toString();
    }

    /**
     * 
     * @see open.commons.database.ITableEntity#createSelectQuery(java.lang.String, int[])
     */
    @Override
    public String createSelectQuery(String table, int... where) {
        return createSelectQuery(table, getAllIndice(), where);
    }

    /**
     * 
     * @see open.commons.database.ITableEntity#createUpdateQuery(java.util.Collection, int[])
     */
    @Override
    public String createUpdateQuery(Collection<Integer> updates, int... where) {
        return createUpdateQuery(getTableName(), updates, where);
    }

    /**
     * 
     * @see open.commons.database.ITableEntity#createUpdateQuery(int[])
     */
    @Override
    public String createUpdateQuery(int... where) {

        Collection<Integer> allColumns = getAllIndice();

        for (int i : where) {
            allColumns.remove(i);
        }

        return createUpdateQuery(getTableName(), allColumns, where);
    }

    /**
     * 
     * @see open.commons.database.ITableEntity#createUpdateQuery(java.lang.String, java.util.Collection, int[])
     */
    @Override
    public String createUpdateQuery(String table, Collection<Integer> updates, int... where) {

        Collection<ColumnConf> columns = null;
        Collection<String> values = null;

        StringBuffer query = new StringBuffer();

        query.append("UPDATE " + table + " SET ");
        columns = getColumns(updates);
        values = getValues(updates);
        query.append(createKV(columns, values, ","));

        if (where.length > 0) {
            query.append(" WHERE ");
            columns = getColumns(where);
            values = getValues(where);
            query.append(createKV(columns, values, "AND"));
        }

        return query.toString();
    }

    /**
     * 
     * @see open.commons.database.ITableEntity#createUpdateQuery(java.lang.String, int[])
     */
    @Override
    public String createUpdateQuery(String table, int... where) {
        Collection<Integer> allColumns = getAllIndice();

        for (int i : where) {
            allColumns.remove(i);
        }

        return createUpdateQuery(table, allColumns, where);
    }

    /**
     * 모든 컬럼에 해당하는 값을 반환한다.<br>
     * 
     * @return e.g. col1, col2, col3, ...
     */
    private Collection<ColumnConf> getAllColumns() {
        return getColumns(getAllIndice());
    }

    private Collection<Integer> getAllIndice() {
        TreeSet<Integer> columns = new TreeSet<Integer>();

        Class<?> clazz = getClass();
        Field[] fields = clazz.getDeclaredFields();

        ColumnConf cc = null;

        for (Field field : fields) {
            cc = AnnotationUtils.getAnnotation(field, ColumnConf.class);
            if (cc != null) {
                columns.add(cc.index());
            }
        }

        return columns;
    }

    private Collection<String> getAllValues() {
        return getValues(getAllIndice());
    }

    private Collection<ColumnConf> getColumns(Collection<Integer> indice) {
        TreeMap<Integer, ColumnConf> columns = new TreeMap<Integer, ColumnConf>();

        Class<?> clazz = getClass();
        Field[] fields = clazz.getDeclaredFields();

        ColumnConf cc = null;

        boolean accessible = false;
        for (Field field : fields) {
            accessible = field.isAccessible();

            field.setAccessible(true);
            cc = AnnotationUtils.getAnnotation(field, ColumnConf.class);
            if (cc != null && indice.contains(cc.index())) {
                columns.put(cc.index(), cc);
            }

            field.setAccessible(accessible);
        }

        return columns.values();
    }

    private Collection<ColumnConf> getColumns(int... indice) {
        ArrayList<Integer> indiceList = new ArrayList<Integer>();
        for (int i : indice) {
            indiceList.add(i);
        }

        return getColumns(indiceList);
    }

    private Field getField(int index) {
        Field rtnField = null;

        Class<?> clazz = getClass();

        Field[] fields = clazz.getDeclaredFields();
        ColumnConf cc = null;

        for (Field field : fields) {
            cc = AnnotationUtils.getAnnotation(field, ColumnConf.class);
            if (cc != null && cc.index() == index) {
                rtnField = field;
            }
        }

        return rtnField;
    }

    private String getTableName() {
        String rtnTableName = null;

        Class<?> clazz = getClass();

        TableDef tableDef = AnnotationUtils.getAnnotation(clazz, TableDef.class);

        if (tableDef != null) {
            rtnTableName = tableDef.table();
        }

        return rtnTableName;
    }

    private Collection<String> getValues(Collection<Integer> indice) {
        TreeMap<Integer, String> columnValues = new TreeMap<Integer, String>();

        Class<?> clazz = getClass();
        Field[] fields = clazz.getDeclaredFields();

        ColumnConf cc = null;

        boolean accessible = false;
        for (Field field : fields) {
            accessible = field.isAccessible();

            field.setAccessible(true);
            cc = AnnotationUtils.getAnnotation(field, ColumnConf.class);
            if (cc != null && indice.contains(cc.index())) {

                if (cc.refer()) {
                    columnValues.put(cc.index(), "'" + AnnotationUtils.getValue(field, this) + "'");
                } else {
                    columnValues.put(cc.index(), AnnotationUtils.getValue(field, this));
                }
            }

            field.setAccessible(accessible);
        }

        return columnValues.values();
    }

    private Collection<String> getValues(int... indice) {
        ArrayList<Integer> indiceList = new ArrayList<Integer>();
        for (int i : indice) {
            indiceList.add(i);
        }

        return getValues(indiceList);
    }

    private String serializeColumns(Collection<ColumnConf> columns) {
        StringBuffer columnsString = new StringBuffer();
        Iterator<ColumnConf> itr = columns.iterator();

        if (itr.hasNext()) {
            columnsString.append(itr.next().column());
            while (itr.hasNext()) {
                columnsString.append(", " + itr.next().column());
            }
        }

        return columnsString.toString();
    }

    private String serializeValues(Collection<String> values) {
        StringBuffer valuesString = new StringBuffer();
        Iterator<String> itr = values.iterator();

        if (itr.hasNext()) {
            valuesString.append(itr.next());
            while (itr.hasNext()) {
                valuesString.append(", " + itr.next());
            }
        }

        return valuesString.toString();
    }

    /**
     * @see open.commons.database.ITableEntity#setValue(int, java.lang.Object)
     */
    @Override
    public boolean setValue(int index, Object value) {
        boolean isSet = false;
        Field field = getField(index);
        try {
            if (field != null) {
                boolean accessible = field.isAccessible();

                field.setAccessible(true);
                field.set(this, value);
                field.setAccessible(accessible);

                isSet = true;
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return isSet;
    }

    /**
     * @see open.commons.database.ITableEntity#toParameters()
     */
    @Override
    public String[] toParameters() {

        Class<?> clazz = getClass();

        Field[] fields = clazz.getDeclaredFields();
        ColumnConf cc = null;

        String[] params = new String[count()];

        boolean accessible = false;
        for (Field field : fields) {
            accessible = field.isAccessible();

            field.setAccessible(true);
            cc = AnnotationUtils.getAnnotation(field, ColumnConf.class);

            if (cc != null) {
                params[cc.index()] = AnnotationUtils.getValue(field, this);
            }

            field.setAccessible(accessible);
        }

        return params;
    }
}
