package org.jason.mapmaker.exporter.strategy;

import com.google.common.collect.Lists;
import de.micromata.opengis.kml.v_2_2_0.*;
import org.jason.mapmaker.api.KmlExportStrategy;
import org.jason.mapmaker.model.GeoFeature;
import org.jason.mapmaker.model.GeoFeaturesCollection;
import org.jason.mapmaker.model.GeoGeometry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

/**
 * @author Jason
 */
public class StateKmlExporter implements KmlExportStrategy {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public File doExport(GeoFeaturesCollection featuresCollection) {

        log.debug("Entered doExport()");
        // create the KML object
        Kml kml = new Kml();

        // create the document
        Document kmlDocument = kml.createAndSetDocument();
        kmlDocument.setId("root_doc");

        kmlDocument.getSchema().add(createSchema());
        kmlDocument.getFeature().add(createFolder(featuresCollection));

        StringWriter sw = new StringWriter();
        kml.marshal(sw);

        log.debug(sw.toString());

        return null;
    }

    @Override
    public File doExport(GeoFeaturesCollection featuresCollection, String... geoIds) {

        log.debug("Entered doExport()");
        // create the KML object
        Kml kml = new Kml();

        // create the document
        Document kmlDocument = kml.createAndSetDocument();
        kmlDocument.setId("root_doc");

        kmlDocument.getSchema().add(createSchema());
        kmlDocument.getFeature().add(createFolder(featuresCollection, geoIds));

        StringWriter sw = new StringWriter();
        kml.marshal(sw);

        log.debug(sw.toString());

        return null;
    }

    private Schema createSchema() {

        Schema kmlSchema = new Schema();
        kmlSchema.getSimpleField().add(createSimpleField("REGION", "string"));
        kmlSchema.getSimpleField().add(createSimpleField("DIVISION","string"));
        kmlSchema.getSimpleField().add(createSimpleField("STATEFP","string"));
        kmlSchema.getSimpleField().add(createSimpleField("STATENS","string"));
        kmlSchema.getSimpleField().add(createSimpleField("GEOID","string"));
        kmlSchema.getSimpleField().add(createSimpleField("STUSPS","string"));
        kmlSchema.getSimpleField().add(createSimpleField("LSAD","string"));
        kmlSchema.getSimpleField().add(createSimpleField("MTFCC","string"));
        kmlSchema.getSimpleField().add(createSimpleField("FUNCSTAT","string"));
        kmlSchema.getSimpleField().add(createSimpleField("ALAND","float"));
        kmlSchema.getSimpleField().add(createSimpleField("AWATER","float"));
        kmlSchema.getSimpleField().add(createSimpleField("INTPTLAT","string"));
        kmlSchema.getSimpleField().add(createSimpleField("INTPTLON","string"));

        return kmlSchema;
    }

    private Folder createFolder(GeoFeaturesCollection collection, String... geoIds) {

        log.debug("Entered createFolder()");

        Folder folder = new Folder().withName(collection.getSource());

        List<String> geoIdList = Arrays.asList(geoIds);
        List<GeoFeature> listToOperateOn = Lists.newArrayList(collection.getFeatures());
        ListIterator<GeoFeature> iterator = listToOperateOn.listIterator();
        while (iterator.hasNext()) {
            GeoFeature feature = iterator.next();
            if (geoIdList.contains(feature.getProperties().getGeoId()) == false) {
                log.debug("Filtering geoId: {}", feature.getProperties().getGeoId());
                iterator.remove();
            }
        }
        // start adding placemarks
        for (GeoFeature feature:listToOperateOn) {

            log.debug("Feature geoId: {}", feature.getProperties().getGeoId());


                log.debug("Found geoId: {}", feature.getProperties().getGeoId());
                Placemark placemark = folder.createAndAddPlacemark();
                placemark.setName(feature.getProperties().getName());

                // linestyle

                // extendeddata
                ExtendedData extendedData = placemark.createAndSetExtendedData();
                SchemaData schemaData = extendedData.createAndAddSchemaData();
                schemaData.setSchemaUrl(String.format("#%s", collection.getSource()));

                SimpleData simpleData = new SimpleData("REGION");
                simpleData.setValue(feature.getProperties().getRegion());
                schemaData.getSimpleData().add(simpleData);

                SimpleData sdDivision = new SimpleData("DIVISION");
                sdDivision.setValue(feature.getProperties().getDivision());
                schemaData.getSimpleData().add(sdDivision);

                placemark.setExtendedData(extendedData);

                Polygon polygon = placemark.createAndSetPolygon();

                Boundary boundary = polygon.createAndSetOuterBoundaryIs();
                LinearRing lr = boundary.createAndSetLinearRing();

                GeoGeometry geoGeometry = feature.getGeometry();
                List<BigDecimal[]> coordinates = geoGeometry.getCoordinates().get(0);
                for (BigDecimal[] bdarray : coordinates) {
                    Coordinate c = new Coordinate(bdarray[0].doubleValue(), bdarray[1].doubleValue());
                    lr.getCoordinates().add(c);
                }
            }

        return folder;
    }



    private SimpleField createSimpleField(String name, String type) {

        SimpleField result = new SimpleField();
        result.setName(name);
        result.setType(type);

        return result;
    }
}
