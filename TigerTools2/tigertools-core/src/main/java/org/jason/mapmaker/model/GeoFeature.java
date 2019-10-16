package org.jason.mapmaker.model;

import java.io.Serializable;

/**
 * Represents a single feature (State, County, etc) from one of the TIGER shapefiles
 *
 * @author Jason
 */
public class GeoFeature implements Serializable {

    private String type = "Feature";
    private GeoProperties properties;
    private GeoGeometry geometry;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public GeoProperties getProperties() {
        return properties;
    }

    public void setProperties(GeoProperties properties) {
        this.properties = properties;
    }

    public GeoGeometry getGeometry() {
        return geometry;
    }

    public void setGeometry(GeoGeometry geometry) {
        this.geometry = geometry;
    }
}
