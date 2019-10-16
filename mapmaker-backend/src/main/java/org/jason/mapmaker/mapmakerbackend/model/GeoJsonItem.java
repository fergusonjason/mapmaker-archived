package org.jason.mapmaker.mapmakerbackend.model;

import org.jason.mapmaker.mapmakerbackend.enumerated.GeometryType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

/**
 * Class representing a single GeoJSON item.
 *
 * Mongo only supports a subset of GeoJSON, so we're going to do this the hard
 * way and not take advantage of the GeoJSON support.
 *
 * @author Jason
 */
@Document(collection = "geographies")
public class GeoJsonItem {

    @Id
    private String id;
    private GeometryType type;
    private Geometry geometry;
    private Map<String, Object> properties;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public GeometryType getType() {
        return type;
    }

    public void setType(GeometryType type) {
        this.type = type;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }
}
