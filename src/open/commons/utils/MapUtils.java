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
*
* This file is generated under this project, "open-commons-core".
*
* Date  : 2015. 1. 6. 오후 4:00:34
*
* Author: Park_Jun_Hong_(fafanmama_at_naver_com)
* 
*/

package open.commons.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 
 * @since 2015. 1. 6.
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
public class MapUtils {

    @SuppressWarnings("rawtypes")
    public static void clear(Map... maps) {
        for (Map map : maps) {
            if (map != null) {
                map.clear();
            }
        }
    }

    /**
     * 주어진 {@link List}에서 정해진 개수만큼 데이터를 반환한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2017. 10. 18.        박준홍         최초 작성
     * </pre>
     *
     * @param map
     * @param readCount
     *            읽어올 개수
     * @return
     *
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @since 2017. 10. 18.
     */
    public static <K, V> Map<K, V> read(Map<K, V> map, int readCount) {

        Map<K, V> read = new HashMap<>();

        // #1. 데이터의 개수가 읽어올 개수보다 작거나 같은 경우
        // 모든 데이터 읽은 후 데이터 삭제
        if (readCount >= map.size()) {
            read.putAll(map);
            map.clear();

            return read;
        }

        // 읽혀진 데이터 키 저장
        List<K> keys = new ArrayList<>();

        // #2. 정해진 개수만큼 데이터 읽기
        int i = 0;
        for (Entry<K, V> entry : map.entrySet()) {

            read.put(entry.getKey(), entry.getValue());
            keys.add(entry.getKey());

            i++;

            if (i >= readCount) {
                break;
            }
        }

        // #3. 읽혀진 데이터 삭제
        for (K key : keys) {
            map.remove(key);
        }

        return read;
    }
}
