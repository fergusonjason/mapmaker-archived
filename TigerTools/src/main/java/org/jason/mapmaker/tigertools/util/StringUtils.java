package org.jason.mapmaker.tigertools.util;

/**
 * @author Jason
 */
public class StringUtils {

    public static String removeEnd(String s, String... suffixes) {

        for (String suffix: suffixes) {
            s = s.endsWith(suffix) ? s.substring(0, s.length() - suffix.length()) : s;
        }

        return s;
    }

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
