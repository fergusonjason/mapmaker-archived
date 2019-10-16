package org.jason.mapmaker.util;

/**
 * @author Jason
 */
public class ObjectUtils {

    // lifted this from Apache Commons-Lang ObjectUtils
    public static <T> T firstNonNull(final T... values) {
        if (values != null) {
            for (final T val : values) {
                if (val != null) {
                    return val;
                }
            }
        }
        return null;
    }
}
