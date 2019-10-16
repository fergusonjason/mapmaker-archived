package org.jason.mapmaker.importer.strategy;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;
import org.jason.mapmaker.api.ShapefileStrategy;
import org.jason.mapmaker.enums.RecordType;
import org.jason.mapmaker.model.GeoFeature;
import org.jason.mapmaker.model.GeoProperties;
import org.opengis.feature.simple.SimpleFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Jason
 */
public class CountyShapefileStrategy extends AbstractShapefileStrategy implements ShapefileStrategy {

    private Logger log = LoggerFactory.getLogger(getClass());

    private String mtfcc = "G4020";

    private ImmutableSet<String> fields = ImmutableSet.of("STATEFP", "GEOID", "LSAD", "MTFCC", "FUNCSTAT", "INTPTLON", "INTPTLAT",
            "ALAND","AWATER","REGION","DIVISION","STATENS","STUSPS","NAMELSAD","CLASSFP","COUNTYFP","COUNTYNS","CSAFP","CBSAFP",
            "METDIVFP","NAME");

    @Override
    public boolean supportsMtfcc(String mtfcc) {
        return mtfcc.equals(this.mtfcc);
    }

    @Override
    public GeoFeature getGeoFeature(SimpleFeature simpleFeature, Optional<Double> distanceTolerance, RecordType recordType) {
        log.trace("Entered getGeoFeature(), recordType: {}", recordType);

        GeoFeature result = new GeoFeature();

        GeoProperties geoProperties = new GeoProperties();

        for (String field: fields) {
            setFeature(geoProperties, field, simpleFeature, recordType);
        }

        result.setProperties(geoProperties);

        return result;

    }
}
