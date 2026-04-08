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
 * Date  : 2025. 8. 12. 오후 3:38:08
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package open.commons.core.function;

import java.util.function.Supplier;

/**
 * 
 * @since 2025. 8. 12.
 * @version 2.1.0
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
@FunctionalInterface
public interface ThrowableRunner {

    default ThrowableRunner andIfThen(Supplier<Boolean> p, ThrowableRunner run) {
        return () -> {
            run();
            if (p.get()) {
                run.run();
            }
        };
    }

    default ThrowableRunner andThen(ThrowableRunner run) {
        return andIfThen(() -> true, run);
    }

    void run() throws Throwable;

}
