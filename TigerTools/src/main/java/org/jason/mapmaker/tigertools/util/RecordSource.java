package org.jason.mapmaker.tigertools.util;

/**
 * This enum should be used to determine the suffix for the various field names in the output shapefile.
 *
 * CURRENT: Do not suffix
 * 1992: ?
 * 2000: SUFFIX with "00"
 * 2010: SUFFIX with "10"
 * 2020: SUFFIX with "20"
 * @author Jason
 */
public enum RecordSource {

    CURRENT,
    _1992,
    _2000,
    _2010,
    _2020,
    CON113,
    CON114,
    CON115,
    CON116;
}
