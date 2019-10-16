package org.jason.mapmaker.api;

import com.google.common.base.Optional;
import org.jason.mapmaker.enums.RecordType;
import org.jason.mapmaker.model.GeoFeature;
import org.opengis.feature.simple.SimpleFeature;

/**
 * @author Jason
 */
public interface ShapefileStrategy {


    boolean supportsMtfcc(String mtfcc);

    GeoFeature getGeoFeature(SimpleFeature simpleFeature, Optional<Double> distanceTolerance, RecordType recordType);
}
