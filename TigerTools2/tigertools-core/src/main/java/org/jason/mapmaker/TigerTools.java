package org.jason.mapmaker;

import com.google.common.base.Optional;
import org.jason.mapmaker.api.Exporter;
import org.jason.mapmaker.api.Importer;
import org.jason.mapmaker.enums.GeoFormat;
import org.jason.mapmaker.model.GeoFeaturesCollection;
import org.jason.mapmaker.util.TigerToolsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author Jason
 */
public class TigerTools {

    private Logger log = LoggerFactory.getLogger(getClass());

    /**
     * Perform the import of whatever file into a List<GeoFeature> based
     * on the input format
     *
     * @param file
     * @param inputFormat
     * @return
     * @throws TigerToolsException
     */
    public GeoFeaturesCollection doImport(File file, GeoFormat inputFormat) throws TigerToolsException {

        log.debug("Entered doImport(), inputFormat: {}", inputFormat);

        ServiceLoader<Importer> serviceLocator = ServiceLoader.load(Importer.class);

        Importer importer = null;
        Iterator<Importer> iterator = serviceLocator.iterator();
        while (iterator.hasNext()) {
            importer = iterator.next();
            if (importer.supports() == inputFormat) {
                break;
            }
        }

        if (importer == null) {
            throw new TigerToolsException("Unable to load Importer implementation (do you have tiger-tools-importer.jar in your classpath?");
        }

        return importer.getFeatures(file);
    }

    public GeoFeaturesCollection doImport(File file, GeoFormat inputFormat, Optional<Double> distanceTolerance) throws TigerToolsException {

        log.debug("Entered doImport(), inputFormat: {}, distanceTolerance: {}", inputFormat, distanceTolerance);

        ServiceLoader<Importer> serviceLocator = ServiceLoader.load(Importer.class);

        Importer importer = null;
        Iterator<Importer> iterator = serviceLocator.iterator();
        while (iterator.hasNext()) {
            importer = iterator.next();
            if (importer.supports() == inputFormat) {
                break;
            }
        }

        if (importer == null) {
            throw new TigerToolsException("Unable to load Importer implementation (do you have tiger-tools-importer.jar in your classpath?");
        }

        log.debug("Using importer implementation: {}", importer.getClass().getName());

        return importer.getFeatures(file, distanceTolerance);
    }

    /**
     * Export a list of GeoFeature objects to a file
     *
     * @param geoFeatures   features to Export
     * @param outputFormat  format to export to
     * @return
     * @throws TigerToolsException
     */
    public File doExport(GeoFeaturesCollection geoFeatures, GeoFormat outputFormat) throws TigerToolsException  {

        log.debug("Entered doExport(), outputFormat: {}", outputFormat);

        ServiceLoader<Exporter> serviceLoader = ServiceLoader.load(Exporter.class);

        File result = null;
        Exporter exporter = null;
        Iterator<Exporter> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            log.debug("loop");
            exporter = iterator.next();
            if (exporter.supports() == outputFormat) {

                break;
            }
        }

        if (exporter == null) {
            throw new TigerToolsException("Unable to load Exporter implementation!");
        }
        log.debug("Using strategy: {}", exporter.getClass().getName());
        result = exporter.exportFeatures(geoFeatures);


        return result;
    }

    public File doExport(GeoFeaturesCollection geoFeatures, GeoFormat outputFormat, String... geoIds) throws TigerToolsException  {

        log.debug("Entered doExport(), outputFormat: {}", outputFormat);

        ServiceLoader<Exporter> serviceLoader = ServiceLoader.load(Exporter.class);

        File result = null;
        Exporter exporter = null;
        Iterator<Exporter> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            exporter = iterator.next();
            if (exporter.supports() == outputFormat) {
                break;
            }
        }

        if (exporter == null) {
            throw new TigerToolsException("Unable to load Exporter implementation!");
        }
        log.debug("Using strategy: {}", exporter.getClass().getName());
        result = exporter.exportFeatures(geoFeatures, geoIds);


        return result;
    }
}
