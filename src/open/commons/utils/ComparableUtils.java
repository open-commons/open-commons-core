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

/**
* 
*/
package open.commons.utils;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import open.commons.annotation.ComparableValue;

/**
 * @author Park Jun-Hong.(mail_to:fafanmama_at_naver_dot_com)
 * 
 * @date
 * 
 */
public class ComparableUtils {

    private static final Comparator<Field> COMPARABLE_VALUE_SORTOR = new Comparator<Field>() {

        @Override
        public int compare(Field field1, Field field2) {
            ComparableValue cv1 = field1.getAnnotation(ComparableValue.class);
            ComparableValue cv2 = field2.getAnnotation(ComparableValue.class);

            return cv1.order() - cv2.order();
        }
    };

    public static <T extends Comparable<T>> int comparable(T[] obj1, T[] obj2) {
        int retValue = 0;
        for (int i = 0; i < obj1.length; i++) {
            if (obj1[i] != null && obj2[i] != null) {
                retValue = obj1[i].compareTo(obj2[i]);
                if (retValue != 0) {
                    return retValue;
                }
            } else if (obj1[i] != null) {
                return 1;
            } else if (obj2[i] != null) {
                return -1;
            }
        }
        return retValue;
    }

    /**
     * {@link ComparableValue}이 사용된 필드만 이용해서 비교 결과를 반환한다. 단, {@link ComparableValue}가 사용된 필드는 {@link Comparable}를
     * implement 해야만 한다.
     * 
     * Example.
     * 
     * <pre>
     * 결과는 -1.
     * 
     * public static void main(String[] args) {
     * 
     *     NormalClass innerNc1 = new NormalClass();
     *     innerNc1.grade = 1;
     *     innerNc1.allowed = false;
     *     innerNc1.name = &quot;a&quot;;
     * 
     *     ComparableClass cc1 = new ComparableClass(innerNc1);
     * 
     *     NormalClass outerNc1 = new NormalClass();
     *     outerNc1.grade = 1;
     *     outerNc1.allowed = false;
     *     outerNc1.name = &quot;a&quot;;
     *     outerNc1.cClass = cc1;
     * 
     *     NormalClass innerNc2 = new NormalClass();
     *     innerNc1.grade = 1;
     *     innerNc1.allowed = false;
     *     innerNc1.name = &quot;b&quot;;
     * 
     *     ComparableClass cc2 = new ComparableClass(innerNc2);
     * 
     *     NormalClass outerNc2 = new NormalClass();
     *     outerNc2.grade = 1;
     *     outerNc2.allowed = true;
     *     outerNc2.name = &quot;a&quot;;
     *     outerNc2.cClass = cc2;
     * 
     *     System.out.println(ComparableUtils.compareTo(outerNc1, outerNc2));
     * 
     * }
     * 
     * static class ComparableClass implements Comparable&lt;ComparableClass&gt; {
     * 
     *     INormalClass normal;
     * 
     *     public ComparableClass(INormalClass normal) {
     *         this.normal = normal;
     *     }
     * 
     *     &#064;Override
     *     public int compareTo(ComparableClass o) {
     *         return this.normal.getGrade() - o.normal.getGrade();
     *     }
     * 
     *     public INormalClass getNormal() {
     *         return normal;
     *     }
     * 
     *     public void setNormal(INormalClass normal) {
     *         this.normal = normal;
     *     }
     * 
     * }
     * 
     * interface INormalClass {
     *     public int getGrade();
     * }
     * 
     * static class NormalClass implements INormalClass {
     *     &#064;ComparableValue(order = 1)
     *     private int grade;
     *     &#064;ComparableValue(order = 2)
     *     private boolean allowed;
     *     &#064;ComparableValue(order = 3)
     *     private String name;
     *     &#064;ComparableValue(order = 4)
     *     private ComparableClass cClass;
     * 
     *     public ComparableClass getcClass() {
     *         return cClass;
     *     }
     * 
     *     &#064;Override
     *     public int getGrade() {
     *         return grade;
     *     }
     * 
     *     public String getName() {
     *         return name;
     *     }
     * 
     *     public boolean isAllowed() {
     *         return allowed;
     *     }
     * 
     *     public void setAllowed(boolean b) {
     *         this.allowed = b;
     *     }
     * 
     *     public void setcClass(ComparableClass cClass) {
     *         this.cClass = cClass;
     *     }
     * 
     *     public void setGrade(int a) {
     *         this.grade = a;
     *     }
     * 
     *     public void setName(String c) {
     *         this.name = c;
     *     }
     * 
     * }
     * </pre>
     * 
     * @param obj1
     * @param obj2
     * @return
     *
     * @since 2014. 11. 7.
     * 
     * @see ComparableValue
     */
    @SuppressWarnings("unchecked")
    public static <T> int compareTo(T obj1, T obj2) {

        List<Field> fields1 = AnnotationUtils.getAnnotatedFields(obj1, ComparableValue.class);
        Collections.sort(fields1, COMPARABLE_VALUE_SORTOR);

        List<Field> fields2 = AnnotationUtils.getAnnotatedFields(obj2, ComparableValue.class);
        Collections.sort(fields2, COMPARABLE_VALUE_SORTOR);

        int c = 0;
        Object value1 = null;
        Object value2 = null;
        try {
            boolean accessible1 = false;
            boolean accessible2 = false;
            Field field1 = null;
            Field field2 = null;
            for (int i = 0; i < fields1.size(); i++) {
                try {
                    field1 = fields1.get(i);
                    field2 = fields2.get(i);

                    accessible1 = field1.isAccessible();
                    accessible2 = field2.isAccessible();

                    field1.setAccessible(true);
                    field2.setAccessible(true);

                    value1 = field1.get(obj1);
                    value2 = field2.get(obj2);

                    if (value1 != null && value2 != null) {
                        c = ((Comparable) value1).compareTo((Comparable) value2);
                    } else {
                        c = value1 == null ? 1 : -1;
                    }

                    if (c != 0) {
                        break;
                    }
                } finally {
                    field1.setAccessible(accessible1);
                    field2.setAccessible(accessible2);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getLocalizedMessage(), e);
        }

        return c;
    }
}
