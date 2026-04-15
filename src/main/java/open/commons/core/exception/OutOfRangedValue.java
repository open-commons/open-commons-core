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

package open.commons.core.exception;

import org.jspecify.annotations.Nullable;

import open.commons.core.utils.AssertUtils2;

/**
 * 
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
public class OutOfRangedValue extends RuntimeException {

    private static final long serialVersionUID = 7291505276103651026L;

    private Object min;

    private Object max;

    private Object value;

    /**
     */
    public OutOfRangedValue(Object value, Object min, Object max) {
        this(value, min, max, null, null);
    }

    /**
     */
    public OutOfRangedValue(Object value, Object min, Object max, @Nullable String message) {
        this(value, min, max, message, null);
    }

    /**
     */
    /**
     */
    public OutOfRangedValue(Object value, Object min, Object max, @Nullable String message, @Nullable Throwable cause) {
        AssertUtils2.notNulls(value, min, max);

        super(message + "[value: " + value + ", max: " + max + ", min: " + min + "]", cause);

        this.value = value;
        this.min = min;
        this.max = max;
    }

    public Object getMax() {
        return max;
    }

    public Object getMin() {
        return min;
    }

    public Object getValue() {
        return value;
    }

}