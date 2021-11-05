/*
 * Copyright 2020 Park Jun-Hong_(parkjunhong77@gmail.com)
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
 * Date  : 2020. 8. 29. 오전 1:20:35
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.function;

import java.util.function.Supplier;

/**
 * Represents a function that run without any parameter.
 * 
 * @since 2020. 8. 29.
 * @version 1.7.0
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
@FunctionalInterface
public interface Runner {
    default Runner andIfThen(Supplier<Boolean> p, Runner run) {
        return () -> {
            run();
            if (p.get()) {
                run.run();
            }
        };
    }

    default Runner andThen(Runner run) {
        return andIfThen(() -> true, run);
    }

    void run() throws RuntimeException;
}
