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
package open.commons.collection;

/**
 * 값과 정렬을 위한 값을 제공하는 클래스. <BR>
 * 
 * @since 2012. 3. 8.
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
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
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
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
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(OrderingKey<T> o) {
        return order.compareTo(o.order);
    }

    /**
     * @param obj
     * @return
     * 
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        OrderingKey<T> other = (OrderingKey<T>) obj;
        if (order == null) {
            if (other.order != null)
                return false;
        } else if (!order.equals(other.order))
            return false;
        if (value == null) {
            if (other.value != null)
                return false;
        } else if (!value.equals(other.value))
            return false;
        return true;
    }

    /**
     * @return the orderValue
     * 
     *         <BR>
     * @since 2012. 3. 8.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public T getOrder() {
        return order;
    }

    /**
     * @return the keyValue
     * 
     *         <BR>
     * @since 2012. 3. 8.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public Object getValue() {
        return value;
    }

    /**
     * @return
     * 
     * @since 2012. 3. 9.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((order == null) ? 0 : order.hashCode());
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
    }

    /**
     * @return
     * 
     * @since 2012. 3. 8.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "value:" + value + "(" + value.getClass() + "), order:" + order + "(" + order.getClass() + ")";
    }
}
