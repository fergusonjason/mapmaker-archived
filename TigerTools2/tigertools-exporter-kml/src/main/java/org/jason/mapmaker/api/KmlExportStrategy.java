package org.jason.mapmaker.api;

import org.jason.mapmaker.model.GeoFeaturesCollection;

import java.io.File;

/**
 * @author Jason
 */
public interface KmlExportStrategy {

    /**
     * Export an entire GeoFeaturesCollections
     *
     * @param featuresCollection
     * @return
     */
    File doExport(GeoFeaturesCollection featuresCollection);

    /**
     * Export features of a GeoFeaturesCollection with given geoids
     *
     * @param featuresCollection
     * @param geoIds
     * @return
     */
    File doExport(GeoFeaturesCollection featuresCollection, String... geoIds);
}
