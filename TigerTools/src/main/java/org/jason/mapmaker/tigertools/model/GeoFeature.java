package org.jason.mapmaker.tigertools.model;

import java.io.Serializable;

/**
 * @author Jason
 */
public class GeoFeature implements Serializable {

    private String type = "Feature";
    private GeoProperty properties;
    private GeoGeometry geometry;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public GeoProperty getProperties() {
        return properties;
    }

    public void setProperties(GeoProperty properties) {
        this.properties = properties;
    }

    public GeoGeometry getGeometry() {
        return geometry;
    }

    public void setGeometry(GeoGeometry geometry) {
        this.geometry = geometry;
    }
}
