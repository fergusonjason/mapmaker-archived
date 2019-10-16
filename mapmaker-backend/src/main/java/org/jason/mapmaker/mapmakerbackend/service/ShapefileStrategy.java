package org.jason.mapmaker.mapmakerbackend.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * @author Jason
 */
public class ShapefileStrategy implements GeographyStrategy {

    private byte[] content;

    public ShapefileStrategy(byte[] content) {
        this.content = content;
    }

    public String toGeoJson() {

        InputStream input = new ByteArrayInputStream(content);

        return null;
    }
}
