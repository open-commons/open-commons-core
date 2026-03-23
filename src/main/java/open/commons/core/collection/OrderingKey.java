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
* 
* 
* This file is generated under this project, "open-commons-core".
*
* Date  : 2012. 3. 8. 오전 11:20:52
*
* Author: Park Jun-Hong (parkjunhong77@gmail.com)
* 
*/
package open.commons.core.collection;

import java.util.Objects;

import org.jspecify.annotations.Nullable;

/**
 * 값과 정렬을 위한 값을 제공하는 클래스. <BR>
 * 
 * @since 2012. 3. 8.
 * @author Park Jun-Hong (parkjunhong77@gmail.com) *
 * 
 */
public class OrderingKey<T extends Comparable<T>> implements Comparable<OrderingKey<T>> {

    /** 실제 데이타 */
    private final Object value;

    /** 정렬을 위해서 사용하는 데이타 */
    private final T order;

    /**
     * 
     * @param v
     * @param o
     *            정렬에 사용되는 값 <BR>
     * @since 2012. 3. 8.
     * 
     */
    public OrderingKey(Object v, T o) {
        value = v;
        order = o;
    }

    /**
     * @param o
     * @return
     * 
     * @since 2012. 3. 8.
     * 
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @SuppressWarnings("null")
    @Override
    public int compareTo(OrderingKey<T> o) {
        return order.compareTo(o.order);
    }

    /**
     *
     * @since 2026. 3. 13.
     * @version 3.0.0
     * 
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        OrderingKey<T> other = (OrderingKey<T>) obj;
        return Objects.equals(order, other.order) && Objects.equals(value, other.value);
    }

    /**
     * @return the orderValue
     * 
     *         <BR>
     * @since 2012. 3. 8.
     * 
     */
    public T getOrder() {
        return order;
    }

    /**
     * @return the keyValue
     * 
     *         <BR>
     * @since 2012. 3. 8.
     * 
     */
    public Object getValue() {
        return value;
    }

    /**
     *
     * @since 2026. 3. 13.
     * @version 3.0.0
     * 
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(order, value);
    }

    /**
     * @return
     * 
     * @since 2012. 3. 8.
     * 
     * 
     * @see java.lang.Object#toString()
     */
    @SuppressWarnings("null")
    @Override
    public String toString() {
        return "value:" + value + "(" + value.getClass() + "), order:" + order + "(" + order.getClass() + ")";
    }
}
