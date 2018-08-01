/*
 * Copyright 2011 Park Jun-Hong_(fafanmama_at_naver_com)
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
* 
* This file is generated under this project, "open-commons-core".
*
* Date  : 2013. 7. 1. 오전 12:09:56
*
* Author: Park_Jun_Hong_(fafanmama_at_naver_com)
* 
*/
package open.commons;

import java.util.Arrays;
import java.util.Map;

import open.commons.utils.ArrayUtils;
import open.commons.utils.CheckUtils;

/**
 * 객체를 생성하는 시점에 입력하는 이름으로 여러 개의 데이터를 사용할 수 있는 클래스.<br>
 * {@link Map}과 유사한 성격을 갖는다.
 * 
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */
public class NValues {
    private static final String CLASS = NValues.class.getSimpleName();

    private String[] names;
    private Object[] values;

    /**
     * 기본 생성자.
     * 
     * @param names
     *            데이터 이름들.
     */
    public NValues(String... names) {
        if (names.length < 1 || CheckUtils.containsNull((Object[]) names)) {
            throw new IllegalArgumentException("Arguments must not contain null. argument: " + (names != null ? Arrays.toString(names) : null));
        }

        this.names = names;
        this.values = new Object[names.length];
    }

    /**
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        NValues other = (NValues) obj;
        if (!Arrays.equals(names, other.names))
            return false;
        if (!Arrays.equals(values, other.values))
            return false;
        return true;
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String name) {
        T rtnValue = null;

        int index = ArrayUtils.getIndex(names, name);
        if (index > -1) {
            rtnValue = (T) values[index];
        }

        return rtnValue;
    }

    /**
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(names);
        result = prime * result + Arrays.hashCode(values);
        return result;
    }

    /**
     * 객체에 저장된 이름을 반환한다.
     * 
     * @return
     */
    public String[] names() {
        return Arrays.copyOf(names, names.length);
    }

    /**
     * 이름에 해당하는 데이터를 설정한다.
     * 
     * @param name
     *            데이터 이름. 객체를 생성하는 시점에 입력한 이름이 아닌 경우 설정되지 않는다.
     * @param value
     *            설정하려는 데이터.
     */
    public void set(String name, Object value) {
        int index = ArrayUtils.getIndex(names, name);
        if (index > -1) {
            values[index] = value;
        }
    }

    /**
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(CLASS);
        sb.append(' ');

        sb.append(names[0]);
        sb.append('=');
        sb.append(values[0]);

        for (int i = 1; i < names.length; i++) {
            sb.append(", ");
            sb.append(names[i]);
            sb.append('=');
            sb.append(values[i]);
        }

        return sb.toString();
    }

}