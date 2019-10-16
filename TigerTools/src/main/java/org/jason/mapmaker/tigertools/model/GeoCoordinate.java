package org.jason.mapmaker.tigertools.model;

import java.math.BigDecimal;

/**
 * @author Jason
 */
public class GeoCoordinate {

    private BigDecimal lng;
    private BigDecimal lat;

    public BigDecimal getLng() {
        return lng;
    }

    public void setLng(BigDecimal lng) {
        this.lng = lng;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }
}
