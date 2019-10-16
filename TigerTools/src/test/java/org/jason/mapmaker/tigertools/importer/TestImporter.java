package org.jason.mapmaker.tigertools.importer;

import com.google.common.base.Optional;
import org.jason.mapmaker.tigertools.model.GeoFeature;
import org.jason.mapmaker.tigertools.model.GeoGeometry;
import org.jason.mapmaker.tigertools.util.GeoFormat;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Jason
 */
public class TestImporter {

    private Logger log = LoggerFactory.getLogger(getClass());

    private Importer importer;

    @Before
    public void setUp() {
        importer = new Importer();
    }

//   @Test(expected = TigerToolsException.class)
//    public void shapeFileToShapeFileExport_throwsException() throws Exception {
//
//       List<GeoFeature> result = importer.getGeoFeatures("C:\\Users\\Jason\\Downloads\\tl_2014_us_state",
//               "tl_2014_us_state.shp",
//               GeoFormat.SHAPEFILE);
//
//
//    }

    @Test
    public void importGeoFeatures_validShapefile_succeeds() {

        List<GeoFeature> result = importer.getGeoFeatures("C:\\Users\\Jason\\Downloads\\tl_2014_us_state",
                "tl_2014_us_state.shp",
                GeoFormat.SHAPEFILE);

        log.debug("Result had {} entries", result.size());

        assertNotNull("result should not be null", result);
        assertFalse("result should not be empty", result.isEmpty());

        for (GeoFeature gf: result) {
            assertNotNull("Properties should not be null", gf.getProperties());
            assertNotNull("StateFP should not be null", gf.getProperties().getStateFP());
            assertNotNull("GeoID should not be null", gf.getProperties().getGeoId());

            GeoGeometry gg = gf.getGeometry();
            assertFalse("Coordinates list should not be empty", gg.getCoordinates().isEmpty());
            log.debug("Geometry Count for {}: {}", gf.getProperties().getStusPs(), gg.getCoordinates().size());

        }
    }

    @Test
    public void importGeoFeatures_checkThatNumberOfPointsIsReducedWhenToleranceIsSpecified_succeeds() {

        List<GeoFeature> resultList1 = importer.getGeoFeatures("C:\\Users\\Jason\\Downloads\\tl_2014_us_state",
                "tl_2014_us_state.shp",
                GeoFormat.SHAPEFILE);

        GeoFeature testResult1 = resultList1.get(0);

        List<GeoFeature> resultList2 =  importer.getGeoFeatures("C:\\Users\\Jason\\Downloads\\tl_2014_us_state",
                "tl_2014_us_state.shp",
                GeoFormat.SHAPEFILE, Optional.of(0.001));

        GeoFeature testResult2 = resultList2.get(0);

        int count1 = testResult1.getGeometry().getCoordinates().get(0).size();
        int count2 = testResult2.getGeometry().getCoordinates().get(0).size();

        log.debug("Original coordinate count: {}, modified coordinate count: {}", count1, count2);

        assertTrue("Feature should be smaller when simplification is specified", count1 > count2);

    }

}
