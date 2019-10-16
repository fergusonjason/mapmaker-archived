package org.jason.mapmaker.mapmakerbackend.model;

import org.jason.mapmaker.mapmakerbackend.enumerated.GeometryType;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Jason
 */
public class Geometry {

    private GeometryType geometryType;
    private List<BigDecimal[]> coordinates;

    public GeometryType getGeometryType() {
        return geometryType;
    }

    public void setGeometryType(GeometryType geometryType) {
        this.geometryType = geometryType;
    }

    public List<BigDecimal[]> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<BigDecimal[]> coordinates) {
        this.coordinates = coordinates;
    }
}
