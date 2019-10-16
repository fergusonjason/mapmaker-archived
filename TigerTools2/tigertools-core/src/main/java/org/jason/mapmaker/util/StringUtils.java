package org.jason.mapmaker.util;

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

    // lifted the next two from Commons-lang StringUtils
    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }
}
