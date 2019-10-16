package org.jason.mapmaker.importer;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import org.geotools.data.FeatureSource;
import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.jason.mapmaker.api.Importer;
import org.jason.mapmaker.api.ShapefileStrategy;
import org.jason.mapmaker.enums.GeoFormat;
import org.jason.mapmaker.enums.RecordType;
import org.jason.mapmaker.importer.strategy.ShapefileStrategyFactory;
import org.jason.mapmaker.model.GeoFeature;
import org.jason.mapmaker.model.GeoFeaturesCollection;
import org.jason.mapmaker.util.StringUtils;
import org.jason.mapmaker.util.TigerToolsException;
import org.opengis.feature.simple.SimpleFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author Jason
 */
public class ShapefileImporter implements Importer {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public GeoFormat supports() {
        return GeoFormat.SHAPEFILE;
    }

    /**
     * Return a GeoFeatures collection WITHOUT the geometry of the GeoFeatures simplified
     *
     * @param file
     * @return
     * @throws TigerToolsException
     */
    @Override
    public GeoFeaturesCollection getFeatures(File file) throws TigerToolsException{

        return getFeatures(file, Optional.<Double>absent());
    }

    /**
     * Return a GeoFeaturesCollection where the geometries of the generated GeoFeature objects
     * are simplified
     *
     * @param file
     * @param distanceTolerance
     * @return
     * @throws TigerToolsException
     */
    @Override
    public GeoFeaturesCollection getFeatures(File file, Optional<Double> distanceTolerance) throws TigerToolsException{

        log.debug("Entered getFeatures()");

        GeoFeaturesCollection geoFeaturesCollection = new GeoFeaturesCollection();
        geoFeaturesCollection.setSource(file.getName());

        List<GeoFeature> resultList = Lists.newArrayList();

        FileDataStore dataStore = null;
        try {
            dataStore = FileDataStoreFinder.getDataStore(file);
            if (dataStore == null) {
                throw new TigerToolsException("Datastore is null");
            }

            String[] typeNames = dataStore.getTypeNames();
            for (String typeName: typeNames) {
                FeatureSource featureSource = dataStore.getFeatureSource(typeName);
                FeatureCollection featureCollection = featureSource.getFeatures();

                log.debug("Features collection contains {} elements", featureCollection.size());
                resultList = Lists.newArrayListWithCapacity(featureCollection.size());

                // this is where we need to populate the GeoFeatures collection
                FeatureIterator<SimpleFeature> iterator = featureCollection.features();
                ShapefileStrategy strategy = null;
                RecordType recordType = RecordType.NORMAL;
                while (iterator.hasNext()) {
                    SimpleFeature f = iterator.next();
                    // not thrilled with this, but I guess it can work. The MTFCC is contained in
                    // the SimpleFeature, not the FeaturesCollection. Luckily, the MTFCC _shouldn't_
                    // change within a single shapefile
                    if (strategy == null) {
                        final String mtfcc = StringUtils.firstNonNull((String) f.getAttribute("MTFCC"),
                                (String) f.getAttribute("MTFCC00"),
                                (String) f.getAttribute("MTFCC10"),
                                (String) f.getAttribute("MTFCCEC"));

                        strategy = ShapefileStrategyFactory.getShapefileStrategy(mtfcc);

                        if (f.getAttribute("MTFCC00") != null) {
                            recordType = RecordType.CENSUS2000;
                        } else if (f.getAttribute("MTFCC10") != null) {
                            recordType = RecordType.CENSUS2010;
                        } else if (f.getAttribute("MTFCC20") != null) {
                            recordType = RecordType.CENSUS2020;
                        } else if (f.getAttribute("MTFCCEC") != null) {
                            recordType = RecordType.ECONOMIC;
                        }
                    }

                    GeoFeature geoFeature = strategy.getGeoFeature(f, distanceTolerance, recordType);

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

        geoFeaturesCollection.setFeatures(resultList);

        return geoFeaturesCollection;
    }

}
