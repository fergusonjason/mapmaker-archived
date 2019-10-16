package org.jason.mapmaker.tigertools.importer;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import org.geotools.data.FeatureSource;
import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.jason.mapmaker.tigertools.importer.strategy.shapefile.ShapefileImportStrategy;
import org.jason.mapmaker.tigertools.importer.strategy.shapefile.ShapefileImportStrategyFactory;
import org.jason.mapmaker.tigertools.model.GeoFeature;
import org.jason.mapmaker.tigertools.util.StringUtils;
import org.jason.mapmaker.tigertools.util.TigerToolsException;
import org.opengis.feature.simple.SimpleFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author Jason
 */
public class ShapefileImporter {

    private Logger log = LoggerFactory.getLogger(getClass());

    public List<GeoFeature> doImport(File shapeFile, Optional<Double> distanceTolerance) throws TigerToolsException {

        List<GeoFeature> resultList = Lists.newArrayList();

        FileDataStore dataStore = null;
        try {
            dataStore = FileDataStoreFinder.getDataStore(shapeFile);
            if (dataStore == null) {
                throw new TigerToolsException("Datastore is null");
            }

            String[] typeNames = dataStore.getTypeNames();
            for (String typeName: typeNames) {
                FeatureSource featureSource = dataStore.getFeatureSource(typeName);
                FeatureCollection featureCollection = featureSource.getFeatures();

                log.debug("Features collection contains {} elements", featureCollection.size());

                // this is where we need to populate the GeoFeatures collection
                FeatureIterator<SimpleFeature> iterator = featureCollection.features();
                while (iterator.hasNext()) {
                    SimpleFeature f = iterator.next();
                    final String mtfcc = getMtfcc(f);
                    ShapefileImportStrategy strategy = ShapefileImportStrategyFactory.getStrategy(mtfcc, distanceTolerance);
                    GeoFeature geoFeature = strategy.createGeoFeature(f);

                    resultList.add(geoFeature);
                }
                iterator.close();
            }

        } catch (IOException e) {
            // I hate catching RuntimeExceptions. Also not a fan of catch-and-throw
            throw new TigerToolsException(e);
        } finally {
            dataStore.dispose();
        }

        return resultList;
    }

    private String getMtfcc(SimpleFeature feature) {

        return (String) StringUtils.firstNonNull(feature.getAttribute("MTFCC"),
                feature.getAttribute("MTFCC00"),
                feature.getAttribute("MTFCC10"),
                feature.getAttribute("MTFCC20"));
    }
}
