package by.training.util;

import java.util.List;

/**
 * @author Andrey Kliuchnikov
 */

public class JstlArrayContainsCheck {
    public static boolean contains(List list, Object o) {
        if (list != null) {
            return list.contains(o);
        }
        return false;
    }
}
