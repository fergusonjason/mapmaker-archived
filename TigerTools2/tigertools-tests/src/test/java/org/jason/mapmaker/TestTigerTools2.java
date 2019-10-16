package org.jason.mapmaker;

import com.google.common.base.Optional;
import org.jason.mapmaker.enums.GeoFormat;
import org.jason.mapmaker.model.GeoFeature;
import org.jason.mapmaker.model.GeoFeaturesCollection;
import org.jason.mapmaker.model.GeoGeometry;
import org.jason.mapmaker.model.GeoProperties;
import org.jason.mapmaker.util.TigerToolsException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Jason
 */
public class TestTigerTools2 {

    private Logger log = LoggerFactory.getLogger(getClass());

    private final String path = "C:\\Users\\Jason\\Downloads\\tl_2014_us_state";

    private TigerTools tigerTools = new TigerTools();

    @Test
    public void doImport_serviceLoaded() {

        File file = new File(path,
                "tl_2014_us_state.shp");

        try {
            GeoFeaturesCollection features = tigerTools.doImport(file, GeoFormat.SHAPEFILE);
        } catch (TigerToolsException e) {
            fail("Should not have thrown Exception!");

        }
    }

    @Test
    public void doImport_importBlockState_succeeds() {

        File file = new File(path,
                "tl_2014_us_state.shp");

        GeoFeaturesCollection features = null;
        try {
            features = tigerTools.doImport(file, GeoFormat.SHAPEFILE);
        } catch (TigerToolsException e) {
            fail("Should not have thrown Exception!");

        }

        assertNotNull("FeaturesCollection should not be null");
        assertNotNull("FeaturesCollection source should be set", features.getSource());

        List<GeoFeature> featuresList = features.getFeatures();
        for (GeoFeature feature: featuresList) {
            assertNotNull("Feature should not be null", feature);
            assertNotNull("Feature properties should not be null", feature.getProperties());
            assertNotNull("Feature geometry should not be null", feature.getGeometry());
        }

        // test a couple of the GeoProperties to make sure they were populated
        GeoProperties properties = features.getFeatures().get(0).getProperties();
        log.debug(properties.toString());

        assertNotNull("MTFCC should not be null", properties.getMtfcc());
        assertNotNull("STUSPS should not be null", properties.getStusPs());

        // make sure the boundaries got populated
        GeoGeometry geoGeometry = features.getFeatures().get(0).getGeometry();
        assertNotNull("Geometry should not be null", geoGeometry);
        log.debug("Number of geometries: {}", geoGeometry.getCoordinates().size());
        assertTrue("Boundary should not be empty", geoGeometry.getCoordinates().isEmpty() == false);

        log.debug("Number of coordinates inside boundary: {}", geoGeometry.getCoordinates().get(0).size());
        assertFalse("Coordinates inside boundary should not be empty", geoGeometry.getCoordinates().get(0).isEmpty());
    }

    @Test
    public void doImport_simplifyGeometry_succeeds() {

        File file = new File(path,
                "tl_2014_us_state.shp");

        GeoFeaturesCollection features1 = null;
        GeoFeaturesCollection features2 = null;
        try {
            features1 = tigerTools.doImport(file, GeoFormat.SHAPEFILE);
            features2 = tigerTools.doImport(file, GeoFormat.SHAPEFILE, Optional.of(0.003));
        } catch (TigerToolsException e) {
            fail("Should not have thrown Exception!");

        }

        int count1 = features1.getFeatures().get(0).getGeometry().getCoordinates().get(0).size();
        int count2 = features2.getFeatures().get(0).getGeometry().getCoordinates().get(0).size();

        log.debug("Original coordinate count: {}, modified coordinate count: {}", count1, count2);

        assertTrue("Feature should be smaller when simplification is specified", count1 > count2);
    }

    @Test
    public void doExport_validCollection_succeeds() {

        File file = new File(path,
                "tl_2014_us_state.shp");

        GeoFeaturesCollection featuresCollection = null;
        File exportedFile = null;
        try {
            featuresCollection = tigerTools.doImport(file, GeoFormat.SHAPEFILE, Optional.of(0.003));
            exportedFile = tigerTools.doExport(featuresCollection, GeoFormat.KML);
        } catch (TigerToolsException e) {
            log.debug("{}",e);
            fail("export should not have thrown exception!");
        } finally {
            if (exportedFile != null) {
                exportedFile.delete();
            }
        }


    }

    @Test
    public void doExportWithGeoIdFilter_validCollection_succeeds() {

        File file = new File(path,
                "tl_2014_us_state.shp");

        GeoFeaturesCollection featuresCollection = null;
        File exportedFile = null;
        try {
            featuresCollection = tigerTools.doImport(file, GeoFormat.SHAPEFILE, Optional.of(0.003));
            exportedFile = tigerTools.doExport(featuresCollection, GeoFormat.KML, "54");
        } catch (TigerToolsException e) {
            log.debug("{}",e);
            fail("export should not have thrown exception!");
        } finally {
            if (exportedFile != null) {
                exportedFile.delete();
            }
        }


    }
}
