package org.jason.mapmaker.mapmakerbackend.service;

/**
 * @author Jason
 */
public class GeoJsonStrategy implements GeographyStrategy {

    private byte[] content;

    public GeoJsonStrategy(byte[] content) {
        this.content = content;
    }

    public String toGeoJson() {
        return new String(content);
    }
}
