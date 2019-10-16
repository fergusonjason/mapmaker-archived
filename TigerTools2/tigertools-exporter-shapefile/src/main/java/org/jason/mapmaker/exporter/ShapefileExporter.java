package org.jason.mapmaker.exporter;

import org.jason.mapmaker.api.Exporter;
import org.jason.mapmaker.enums.GeoFormat;
import org.jason.mapmaker.enums.RecordType;
import org.jason.mapmaker.model.GeoFeaturesCollection;
import org.jason.mapmaker.util.TigerToolsException;

import java.io.File;

/**
 * @author Jason
 */
public class ShapefileExporter implements Exporter {

    @Override
    public GeoFormat supports() {
        return GeoFormat.SHAPEFILE;
    }

    @Override
    public File exportFeatures(GeoFeaturesCollection geoFeatures, RecordType recordType) throws TigerToolsException {
        return null;
    }

    @Override
    public File exportFeatures(GeoFeaturesCollection geoFeatures, RecordType recordType, String... geoIds) throws TigerToolsException {
        return null;
    }

}
