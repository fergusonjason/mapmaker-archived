package org.jason.mapmaker.importer.strategy;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.simplify.DouglasPeuckerSimplifier;
import org.jason.mapmaker.enums.RecordType;
import org.jason.mapmaker.model.GeoGeometry;
import org.jason.mapmaker.model.GeoProperties;
import org.jason.mapmaker.util.ObjectUtils;
import org.jason.mapmaker.util.StringUtils;
import org.opengis.feature.simple.SimpleFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Jason
 */
public abstract class AbstractShapefileStrategy {

    private Logger log = LoggerFactory.getLogger(getClass());

    protected GeoGeometry createGeometry(SimpleFeature simpleFeature, Optional<Double> distanceTolerance) {

        log.trace("Entered createGeometry()");

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
            // if a distance tolerance is specified, use the simplifier
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

        return geoGeometry;

    }

    protected void setFeature(GeoProperties geoProperties, String featureName, SimpleFeature simpleFeature, RecordType recordType) {

        log.trace("Entered setFeature(), featureName: {}", featureName);

        if (StringUtils.isBlank(featureName)) {
            log.warn("Featurename is null or empty!");
            return;
        }
        // I'm paranoid, get over it
        final String fixedFeatureName = featureName.trim().toUpperCase();
        switch (fixedFeatureName) {
            case "ALAND":
                Long landArea = (Long) ObjectUtils.firstNonNull(simpleFeature.getAttribute("ALAND"),
                        simpleFeature.getAttribute("ALAND00"),
                        simpleFeature.getAttribute("ALAND10"),
                        simpleFeature.getAttribute("ALAND20"),
                        simpleFeature.getAttribute("ALANDEC"));
                geoProperties.setLandArea(landArea);
                break;
            case "AWATER":
                Long waterArea = (Long) ObjectUtils.firstNonNull(simpleFeature.getAttribute("AWATER"),
                        simpleFeature.getAttribute("AWATER00"),
                        simpleFeature.getAttribute("AWATER10"),
                        simpleFeature.getAttribute("AWATER20"),
                        simpleFeature.getAttribute("AWATEREC"));
                geoProperties.setWaterArea(waterArea);
                break;
//            case "COUSUBFP":
//                geoProperties.setCouSubFp((String) simpleFeature.getAttribute(String.format("COUSUBFP%s", recordType.getSuffix())));
//                break;
//            case "COUSUBNS":
//                geoProperties.setCouSubNs((String) simpleFeature.getAttribute(String.format("CSAFP%s", recordType.getSuffix())));
//                break;
            case "CSAFP":
                geoProperties.setCsafp((String) simpleFeature.getAttribute(String.format("CSAFP%s", recordType.getSuffix())));
                break;
            case "CBSAFP":
                geoProperties.setCbsafp((String) simpleFeature.getAttribute(String.format("CBSAFP%s", recordType.getSuffix())));
                break;
            case "CLASSFP":
                geoProperties.setClassMp((String) simpleFeature.getAttribute(String.format("CLASSFP%s", recordType.getSuffix())));
                break;
            case "COUNTYFP":
                geoProperties.setCountyFP((String) simpleFeature.getAttribute(String.format("COUNTYFP%s", recordType.getSuffix())));
                break;
            case "COUNTYNS":
                geoProperties.setCountyNs((String) simpleFeature.getAttribute(String.format("COUNTYNS%s", recordType.getSuffix())));
                break;
            case "DIVISION":
                geoProperties.setDivision((String) simpleFeature.getAttribute(String.format("DIVISION%s", recordType.getSuffix())));
                break;
            case "FUNCSTAT":
                geoProperties.setFuncStat((String) simpleFeature.getAttribute(String.format("FUNCSTAT%s", recordType.getSuffix())));
                break;
            case "GEOID":
                geoProperties.setGeoId((String) simpleFeature.getAttribute(String.format("GEOID%s", recordType.getSuffix())));
                break;
            case "LSAD":
                geoProperties.setLsad((String) simpleFeature.getAttribute(String.format("LSAD%s", recordType.getSuffix())));
                break;
            case "INTPTLON":
                geoProperties.setIntPtLng((String) simpleFeature.getAttribute(String.format("INTPTLON%s", recordType.getSuffix())));
                break;
            case "INTPTLAT":
                geoProperties.setIntPtLat((String) simpleFeature.getAttribute(String.format("INTPTLAT%s", recordType.getSuffix())));
                break;
            case "METDIVFP":
                geoProperties.setMetdivfp((String) simpleFeature.getAttribute(String.format("METDIVFP%s", recordType.getSuffix())));
            case "MTFCC":
                geoProperties.setMtfcc((String) simpleFeature.getAttribute(String.format("MTFCC%s", recordType.getSuffix())));
                break;
            case "NAME":
                geoProperties.setName((String) simpleFeature.getAttribute(String.format("NAME%s", recordType.getSuffix())));
                break;
            case "NAMELSAD":
                geoProperties.setNameLsad((String) simpleFeature.getAttribute(String.format("NAMELSAD%s", recordType.getSuffix())));
                break;
            case "REGION":
                geoProperties.setRegion((String) simpleFeature.getAttribute(String.format("REGION%s", recordType.getSuffix())));
                break;
            case "STATEFP":
                geoProperties.setStateFP((String) simpleFeature.getAttribute(String.format("STATEFP%s", recordType.getSuffix())));
                break;
            case "STATENS":
                geoProperties.setStateNS((String) simpleFeature.getAttribute(String.format("STATENS%s", recordType.getSuffix())));
                break;
            case "STUSPS":
                geoProperties.setStusPs((String) simpleFeature.getAttribute(String.format("STUSPS%s", recordType.getSuffix())));
                break;
            default:
                log.warn("WARNING: featurename {} not supported!", featureName);
        }
    }
}
