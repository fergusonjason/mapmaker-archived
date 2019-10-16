package org.jason.mapmaker.exporter;

import org.jason.mapmaker.api.Exporter;
import org.jason.mapmaker.api.KmlExportStrategy;
import org.jason.mapmaker.enums.GeoFormat;
import org.jason.mapmaker.enums.RecordType;
import org.jason.mapmaker.exporter.strategy.KmlExporterFactory;
import org.jason.mapmaker.model.GeoFeature;
import org.jason.mapmaker.model.GeoFeaturesCollection;
import org.jason.mapmaker.util.TigerToolsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @author Jason
 */
public class KmlExporter implements Exporter {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public GeoFormat supports() {
        return GeoFormat.KML;
    }

    @Override
    public File exportFeatures(GeoFeaturesCollection geoFeatures, RecordType recordType) throws TigerToolsException {

        log.debug("Entered exportFeatures()");

        if (geoFeatures == null) {
            throw new TigerToolsException("GeoFeaturesCollection is null!");
        }

        if (geoFeatures.getFeatures().isEmpty()) {
            throw new TigerToolsException("GeoFeaturesCollection does not contain any GeoFeature objects");
        }

        if (!geoFeatures.getFeatures().isEmpty()) {
            GeoFeature sample = geoFeatures.getFeatures().get(0);

            KmlExportStrategy strategy = KmlExporterFactory.getExportStrategy(sample.getProperties().getMtfcc());

            File result = strategy.doExport(geoFeatures);
        }



        return null;
    }

    @Override
    public File exportFeatures(GeoFeaturesCollection geoFeatures, RecordType recordType, String... geoIds) throws TigerToolsException {
        log.debug("Entered exportFeatures()");

        if (geoFeatures == null) {
            throw new TigerToolsException("GeoFeaturesCollection is null!");
        }

        if (geoFeatures.getFeatures().isEmpty()) {
            throw new TigerToolsException("GeoFeaturesCollection does not contain any GeoFeature objects");
        }

        if (!geoFeatures.getFeatures().isEmpty()) {
            GeoFeature sample = geoFeatures.getFeatures().get(0);

            KmlExportStrategy strategy = KmlExporterFactory.getExportStrategy(sample.getProperties().getMtfcc());

            File result = strategy.doExport(geoFeatures, geoIds);
        }



        return null;
    }
}
