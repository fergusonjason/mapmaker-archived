package org.jason.mapmaker.api;

import com.google.common.base.Optional;
import org.jason.mapmaker.enums.GeoFormat;
import org.jason.mapmaker.model.GeoFeaturesCollection;
import org.jason.mapmaker.util.TigerToolsException;

import java.io.File;

/**
 * @author Jason
 */
public interface Importer {


    /**
     * Return the GeoFormat enum stating which format this Importer
     * implementation supports
     *
     * @return
     */
    GeoFormat supports();

    /**
     * Get a List<GeoFeature> from a given file
     * @param file
     * @return
     * @throws TigerToolsException
     */
    GeoFeaturesCollection getFeatures(File file) throws TigerToolsException;

    /**
     * Get a List<GeoFeature> with an optional argument to simplify the
     * geography contained in each member of the list
     *
     * @param file
     * @param distanceTolerance
     * @return
     * @throws TigerToolsException
     */
    GeoFeaturesCollection getFeatures(File file, Optional<Double> distanceTolerance) throws TigerToolsException;
}
