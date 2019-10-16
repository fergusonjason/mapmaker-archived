package org.jason.mapmaker.exporter;

import org.jason.mapmaker.api.Exporter;
import org.jason.mapmaker.enums.GeoFormat;
import org.jason.mapmaker.enums.RecordType;
import org.jason.mapmaker.model.GeoFeaturesCollection;
import org.jason.mapmaker.util.TigerToolsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @author Jason
 */
public class GeoJsonExporter implements Exporter {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public GeoFormat supports() {
        return GeoFormat.GEOJSON;
    }

    @Override
    public File exportFeatures(GeoFeaturesCollection geoFeatures, RecordType recordType, String... geoIds) throws TigerToolsException {
        return null;
    }

    @Override
    public File exportFeatures(GeoFeaturesCollection geoFeatures, RecordType recordType) {
        return null;
    }


}
