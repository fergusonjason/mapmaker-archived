package org.jason.mapmaker.tigertools.importer.strategy.shapefile;

import org.jason.mapmaker.tigertools.model.GeoFeature;
import org.opengis.feature.simple.SimpleFeature;

/**
 * Interface for all Shapefile imports
 *
 * @author Jason
 */
public interface ShapefileImportStrategy {

    /**
     * Create a GeoFeature while simplifying the Polygon(s) inside the original
     * shapefile
     *
     * @param simpleFeature     the SimpleFeature to convert to a GeoFeature
     * @param distanceTolerance Guava Optional which may contain a double, it may not... who knows? Anyways, I
     *                          can't use the Java 8 optional because I'm writing this at the Java 7 level
     * @return
     */
    //GeoFeature createGeoFeature(SimpleFeature simpleFeature, Optional<Double> distanceTolerance);

    /**
     * Create a single GeoFeature from a single SimpleFeature
     *
     * @param simpleFeature
     * @return
     */
    GeoFeature createGeoFeature(SimpleFeature simpleFeature);
}
