package org.jason.mapmaker.tigertools.importer;

import com.google.common.base.Optional;
import org.jason.mapmaker.tigertools.model.GeoFeature;
import org.jason.mapmaker.tigertools.util.GeoFormat;
import org.jason.mapmaker.tigertools.util.TigerToolsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;

/**
 * Import from various formats (probably SHP though) to a List<GeoFeature>
 *
 * @author Jason
 */
public class Importer {

    private Logger log = LoggerFactory.getLogger(getClass());

    public List<GeoFeature> getGeoFeatures(String location, String file, GeoFormat inputType) {

        List<GeoFeature> resultList = getGeoFeatures(location, file, inputType, Optional.<Double>absent());


        // this if statement is temporary, to be replaced by a switch
//        if (inputType != GeoFormat.SHAPEFILE) {
//            throw new UnsupportedOperationException("Input Type not yet supported");
//        } else {
//            File shapefile = new File(location, file);
//            ShapefileImporter importer = new ShapefileImporter();
//
//            try {
//                resultList = importer.doImport(shapefile, Optional.<Double>absent());
//            } catch (TigerToolsException e) {
//                log.debug("Caught TigerToolsException: {}", e);
//            }
//        }

        return resultList;
    }

    public List<GeoFeature> getGeoFeatures(String location, String file,
                                           GeoFormat inputType,
                                           Optional<Double> distanceTolerance) {

        List<GeoFeature> resultList = null;

        // this if statement is temporary, to be replaced by a switch
        if (inputType != GeoFormat.SHAPEFILE) {
            throw new UnsupportedOperationException("Input Type not yet supported");
        } else {
            File shapefile = new File(location, file);
            ShapefileImporter importer = new ShapefileImporter();

            try {
                resultList = importer.doImport(shapefile, distanceTolerance);
            } catch (TigerToolsException e) {
                log.debug("Caught TigerToolsException: {}", e);
            }
        }

        return resultList;
    }









}
