package org.jason.mapmaker.enums;

/**
 * @author Jason
 */
public enum Mtfcc {

    G4000("G4000","State"),
    G4020("G4020","County");

    private String mtfccCode;
    private String state;

    Mtfcc(String mtfccCode, String state) {
        this.mtfccCode = mtfccCode;
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
