/*
 *
 * This file is generated under this project, "open-commons-core".
 *
 * Date  : 2020. 8. 29. 오전 1:20:35
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package open.commons.function;

import java.util.function.Supplier;

/**
 * Represents a function that run without any parameter.
 * 
 * @since 2020. 8. 29.
 * @version 1.7.0
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
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
        return () -> {
            run();
            run.run();
        };
    }

    void run() throws RuntimeException;
}
