/*
 * Copyright 2019 Park Jun-Hong_(fafanmama_at_naver_com)
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
 * Date  : 2019. 2. 18. 오후 3:25:31
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package open.commons.database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.BiFunction;

import open.commons.Result;
import open.commons.function.TripleFunction;

/**
 * 
 * @since 2019. 1. 14.
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
public abstract class AbstractInsertDao extends AbstractDao {

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 1. 4.      박준홍         최초 작성
     * </pre>
     *
     * @param driver
     * @param url
     * @param username
     * @param password
     * @param autocommit
     * @since 2019. 1. 4.
     */
    public AbstractInsertDao(String driver, String url, String username, String password, boolean autocommit) {
        super(driver, url, username, password, autocommit);
    }

    /**
     * 다중 데이터를 추가하는 쿼리를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 1. 4.     박준홍         최초 작성
     * </pre>
     *
     * @param insertAllQueryPrefix
     *            다중 데이터 추가 쿼리 Prefix. 테이블 정보 및 Column 바인딩
     * @param insertAllQueryValues
     *            다중 데이터 추가 쿼리 suffix. 데이터 바인딩 변수 영역
     * @param dataCount
     *            데이터 개수
     * @return
     *
     * @since 2019. 1. 4.
     */
    protected final String createInsertAllQuery(String insertAllQueryPrefix, String insertAllQueryValues, int dataCount) {

        if (dataCount < 1) {
            throw new IllegalArgumentException("The count of data MUST BE bigger than 0.");
        }

        StringBuffer queryBuf = new StringBuffer(insertAllQueryPrefix);

        queryBuf.append(' ');
        queryBuf.append(insertAllQueryValues);

        for (int i = 1; i < dataCount; i++) {
            queryBuf.append(", ");
            queryBuf.append(insertAllQueryValues);
        }

        return queryBuf.toString();
    }

    /**
     * 한번에 추가할 데이터 개수를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 1. 4.      박준홍         최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2019. 1. 4.
     */
    protected abstract int getMaxCountPerInsert();

    /**
     * 다중 데이터를 추가한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 1. 4.      박준홍         최초 작성
     * </pre>
     *
     * @param data
     *            추가할 데이타.
     * @param timestamp
     *            생성일시
     * @param variableQuery
     *            TODO
     * @param valueQuery
     *            TODO
     * @param setterProvider
     *            TODO
     * @return
     *
     * @since 2019. 1. 4.
     */
    public final <T> Result<Integer> insert(Collection<T> data, long timestamp, String variableQuery, String valueQuery,
            BiFunction<Collection<T>, Long, IConnectionCallbackSetter> setterProvider) {

        Result<Integer> result = new Result<>();

        int inserted = 0;

        ArrayList<T> list = new ArrayList<>(data);
        List<T> partitioned = null;

        final int maxCount = getMaxCountPerInsert();
        int pos = 0;

        int resultInserted = 0;
        String query = null;

        ConnectionCallbackBroker callbackBroker = null;

        try {

            while ((partitioned = read(list, pos, maxCount)).size() > 0) {

                query = createInsertAllQuery(variableQuery, valueQuery, partitioned.size());

                callbackBroker = new ConnectionCallbackBroker(query, setterProvider.apply(partitioned, timestamp));

                resultInserted = executeUpdate(callbackBroker);

                if (resultInserted > -1) {
                    inserted += resultInserted;
                }

                if (logger.isTraceEnabled()) {
                    logger.trace(String.format("%,d  개의 데이터를 추가하였습니다.", inserted));
                }

                if (partitioned.size() < maxCount) {
                    break;
                }

                pos += partitioned.size();
            }

            result.andTrue().setData(inserted);

        } catch (SQLException e) {

            if (logger.isErrorEnabled()) {
                logger.error("데이터 추가시 에러가 발생하였습니다.", e);
            }

            result.setMessage(e.getMessage());

            rollback();

        } finally {
        }

        return result;
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 2. 22.		박준홍			최초 작성
     * </pre>
     *
     * @param data
     * @param timestamp
     * @param variableQuery
     * @param valueQuery
     * @param brokerProvider
     * @return
     *
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @since 2019. 2. 22.
     */
    public final <T, S> Result<Integer> insert(Collection<T> data, long timestamp, String variableQuery, String valueQuery,
            TripleFunction<String, Collection<T>, Long, ConnectionCallbackBroker2<S>> brokerProvider) {

        Result<Integer> result = new Result<>();

        int inserted = 0;

        ArrayList<T> list = new ArrayList<>(data);
        List<T> partitioned = null;

        final int maxCount = getMaxCountPerInsert();
        int pos = 0;

        int resultInserted = 0;
        String query = null;

        ConnectionCallbackBroker2<S> callbackBroker = null;

        try {

            while ((partitioned = read(list, pos, maxCount)).size() > 0) {

                query = createInsertAllQuery(variableQuery, valueQuery, partitioned.size());

                callbackBroker = brokerProvider.apply(query, partitioned, timestamp);

                resultInserted = executeUpdate(callbackBroker);

                if (resultInserted > -1) {
                    inserted += resultInserted;
                }

                if (logger.isTraceEnabled()) {
                    logger.trace(String.format("%,d  개의 데이터를 추가하였습니다.", inserted));
                }

                if (partitioned.size() < maxCount) {
                    break;
                }

                pos += partitioned.size();
            }

            result.andTrue().setData(inserted);

        } catch (SQLException e) {

            if (logger.isErrorEnabled()) {
                logger.error("데이터 추가시 에러가 발생하였습니다.", e);
            }

            result.setMessage(e.getMessage());

            rollback();

        } finally {
        }

        return result;
    }

    /**
     * 
     * 데이터 목록에서 최대개수만큼 읽어 반환한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 1. 4.     박준홍         최초 작성
     * </pre>
     *
     * @param list
     *            데이터
     * @param pos
     *            현재 위치
     * @param maxCount
     *            읽어올 최대 개수
     * @return
     *
     * @since 2019. 1. 4.
     */
    protected static <T> List<T> read(List<T> list, int pos, int maxCount) {

        List<T> read = new ArrayList<>();

        if (pos >= list.size()) {
            return read;
        }

        int readCount = 0;

        for (int i = pos; i < list.size() && readCount < maxCount; i++, readCount++) {
            read.add(list.get(i));
        }

        return read;
    }
}
