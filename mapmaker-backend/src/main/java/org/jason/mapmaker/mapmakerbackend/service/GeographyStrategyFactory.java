package org.jason.mapmaker.mapmakerbackend.service;


import org.jason.mapmaker.mapmakerbackend.enumerated.InputFormat;

/**
 * @author Jason
 */
public class GeographyStrategyFactory {

    private GeographyStrategyFactory() {

    }

    public static GeographyStrategy create(InputFormat type, byte[] content) {

        GeographyStrategy result = null;
        switch(type) {
            case GEOJSON:
                result = new GeoJsonStrategy(content);
                break;
            case SHAPEFILE:
                result = new ShapefileStrategy(content);
                break;
        }

        return result;
    }
}
