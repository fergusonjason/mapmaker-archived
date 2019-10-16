package org.jason.mapmaker.tigertools.importer.strategy.shapefile;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.simplify.DouglasPeuckerSimplifier;
import org.jason.mapmaker.tigertools.model.GeoFeature;
import org.jason.mapmaker.tigertools.model.GeoGeometry;
import org.jason.mapmaker.tigertools.model.GeoProperty;
import org.opengis.feature.simple.SimpleFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;

/**
 * Import a State TIGER/Lines definition. MTFCC must be G4000.
 *
 * @author Jason
 */
public class StateShapefileImportStrategy implements ShapefileImportStrategy{

    private Logger log = LoggerFactory.getLogger(getClass());

    private Optional<Double> distanceTolerance;

    public StateShapefileImportStrategy() {
        distanceTolerance = Optional.absent();
    }

    public StateShapefileImportStrategy(Optional<Double> distanceTolerance) {
        this.distanceTolerance = distanceTolerance;
    }

    @Override
    public GeoFeature createGeoFeature(SimpleFeature simpleFeature) {

        log.trace("Entered createGeoFeature()");

        GeoFeature geoFeature = new GeoFeature();

        GeoProperty geoProperty = new GeoProperty();

        geoProperty.setStateFP((String) simpleFeature.getAttribute("STATEFP"));
        geoProperty.setGeoId((String) simpleFeature.getAttribute("GEOID"));
        geoProperty.setName((String) simpleFeature.getAttribute("NAME"));
        geoProperty.setLsad((String) simpleFeature.getAttribute("LSAD"));
        geoProperty.setMtfcc((String) simpleFeature.getAttribute("MTFCC"));
        geoProperty.setFuncStat((String) simpleFeature.getAttribute("FUNCSTAT"));
        geoProperty.setIntPtLng((String)simpleFeature.getAttribute("INTPTLON"));
        geoProperty.setIntPtLat((String) simpleFeature.getAttribute("INTPTLAT"));
        geoProperty.setLandArea((Long) simpleFeature.getAttribute("ALAND"));
        geoProperty.setWaterArea((Long) simpleFeature.getAttribute("AWATER"));

        geoProperty.setRegion((String) simpleFeature.getAttribute("REGION"));
        geoProperty.setDivision((String) simpleFeature.getAttribute("DIVISION"));
        geoProperty.setStateNS((String) simpleFeature.getAttribute("STATENS"));
        geoProperty.setStusPs((String) simpleFeature.getAttribute("STUSPS"));

        geoFeature.setProperties(geoProperty);

        // use BD. I hate using doubles


        GeoGeometry geoGeometry = new GeoGeometry();
        MultiPolygon multiPolygon = (MultiPolygon) simpleFeature.getDefaultGeometry();
        int geometryCount = multiPolygon.getNumGeometries();
        if (geometryCount > 1) {
            geoGeometry.setType("MultiPolygon");
        } else {
            geoGeometry.setType("Polygon");
        }

        // look at me, having to use a non-foreach loop like a barbarian...
        // anyways, this should properly deal with multipolygons
        for (int i=0; i < geometryCount; i++) {
            List<BigDecimal[]> test = Lists.newArrayList();

            Geometry g = multiPolygon.getGeometryN(i);
            // if a distance tolerance is specified, use the DPS to simplify the geometry
            if (distanceTolerance.isPresent()) {
                if (distanceTolerance.get() > 0.01) {
                    log.warn("WARNING: The larger the distance tolerance, the greater the chance of creating spikes in the output");
                }
                final DouglasPeuckerSimplifier simplifier = new DouglasPeuckerSimplifier(g);
                g = simplifier.getResultGeometry();
            }
            Coordinate[] coordinates = g.getCoordinates();
            for (Coordinate c: coordinates) {
                BigDecimal[] bdArray = new BigDecimal[]{new BigDecimal(c.x), new BigDecimal(c.y)};
                test.add(bdArray);
            }

            geoGeometry.getCoordinates().add(test);
        }

        geoFeature.setGeometry(geoGeometry);
        return geoFeature;
    }

    /**
     * {@inheritDoc}
     */
//    @Override
//    public GeoFeature createGeoFeature(SimpleFeature simpleFeature) {
//
//        log.trace("Entered createGeoFeature()");
//
//        return createGeoFeature(simpleFeature, Optional.<Double>absent());
//
////        GeoFeature geoFeature = new GeoFeature();
////
////        GeoProperty geoProperty = new GeoProperty();
////
////        geoProperty.setStateFP((String) simpleFeature.getAttribute("STATEFP"));
////        geoProperty.setGeoId((String) simpleFeature.getAttribute("GEOID"));
////        geoProperty.setName((String) simpleFeature.getAttribute("NAME"));
////        geoProperty.setLsad((String) simpleFeature.getAttribute("LSAD"));
////        geoProperty.setMtfcc((String) simpleFeature.getAttribute("MTFCC"));
////        geoProperty.setFuncStat((String) simpleFeature.getAttribute("FUNCSTAT"));
////        geoProperty.setIntPtLng((String)simpleFeature.getAttribute("INTPTLON"));
////        geoProperty.setIntPtLat((String) simpleFeature.getAttribute("INTPTLAT"));
////        geoProperty.setLandArea((Long) simpleFeature.getAttribute("ALAND"));
////        geoProperty.setWaterArea((Long) simpleFeature.getAttribute("AWATER"));
////
////        geoProperty.setRegion((String) simpleFeature.getAttribute("REGION"));
////        geoProperty.setDivision((String) simpleFeature.getAttribute("DIVISION"));
////        geoProperty.setStateNS((String) simpleFeature.getAttribute("STATENS"));
////        geoProperty.setStusPs((String) simpleFeature.getAttribute("STUSPS"));
////
////        geoFeature.setProperties(geoProperty);
////
////        // use BD. I hate using doubles
////
////
////        GeoGeometry geoGeometry = new GeoGeometry();
////        MultiPolygon multiPolygon = (MultiPolygon) simpleFeature.getDefaultGeometry();
////        int geometryCount = multiPolygon.getNumGeometries();
////        if (geometryCount > 1) {
////            geoGeometry.setType("MultiPolygon");
////        } else {
////            geoGeometry.setType("Polygon");
////        }
////
////        // look at me, having to use a non-foreach loop like a barbarian...
////        // anyways, this should properly deal with multipolygons
////        for (int i=0; i < geometryCount; i++) {
////            List<BigDecimal[]> test = Lists.newArrayList();
////
////            Geometry g = multiPolygon.getGeometryN(i);
////            Coordinate[] coordinates = g.getCoordinates();
////            for (Coordinate c: coordinates) {
////                BigDecimal[] bdArray = new BigDecimal[]{new BigDecimal(c.x), new BigDecimal(c.y)};
////                test.add(bdArray);
////            }
////
////            geoGeometry.getCoordinates().add(test);
////        }
////
////        geoFeature.setGeometry(geoGeometry);
////        return geoFeature;
//    }
}
