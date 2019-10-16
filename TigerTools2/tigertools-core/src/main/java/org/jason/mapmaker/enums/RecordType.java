package org.jason.mapmaker.enums;

/**
 * @author Jason
 */
public enum RecordType {

    NORMAL(""),
    ECONOMIC("EC"),
    CENSUS1992(""),
    CENSUS2000("00"),
    CENSUS2010("10"),
    CENSUS2020("20");

    private String suffix;

    RecordType(String suffix) {
        this.suffix = suffix;
    }

    public String getSuffix() {
        return this.suffix;
    }
}
