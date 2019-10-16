package org.jason.mapmaker.api;

import org.jason.mapmaker.enums.GeoFormat;
import org.jason.mapmaker.enums.RecordType;
import org.jason.mapmaker.model.GeoFeaturesCollection;
import org.jason.mapmaker.util.TigerToolsException;

import java.io.File;

/**
 * @author Jason
 */
public interface Exporter {

    /**
     * Determine the GeoFormat that the Exporter supports
     *
     * @return
     */
    GeoFormat supports();

    /**
     * Export a List<GeoFeature> to a File. Would rather export to an
     * OutputStream, but Geotools likes Files. Maybe I'll figure it out
     * later
     *
     * @param geoFeatures
     * @return
     */
    File exportFeatures(GeoFeaturesCollection geoFeatures, RecordType recordType) throws TigerToolsException;

    /**
     * Export a GeoFeaturesCollection to a File, but filtered to a given list of geoids
     * @param geoFeatures
     * @param geoIds
     * @return
     */
    File exportFeatures(GeoFeaturesCollection geoFeatures, RecordType recordType, String... geoIds) throws TigerToolsException;
}
