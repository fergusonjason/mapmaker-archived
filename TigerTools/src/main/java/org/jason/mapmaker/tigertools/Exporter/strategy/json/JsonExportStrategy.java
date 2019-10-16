package org.jason.mapmaker.tigertools.exporter.strategy.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jason.mapmaker.tigertools.exporter.strategy.ExportStrategy;
import org.jason.mapmaker.tigertools.model.GeoFeature;
import org.jason.mapmaker.tigertools.model.GeoFeaturesCollection;
import org.jason.mapmaker.tigertools.util.TigerToolsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author Jason
 */
public class JsonExportStrategy implements ExportStrategy<String> {

    private Logger log = LoggerFactory.getLogger(getClass());

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public String doExport(List<GeoFeature> geoFeatures) throws TigerToolsException{

        log.warn("WARNING: You are exporting a list of GeoFeatures to a String as JSON. This will result in a" +
                "huge String and is NOT recommended");

        GeoFeaturesCollection geoFeaturesCollection = new GeoFeaturesCollection();
        geoFeaturesCollection.setFeatures(geoFeatures);

        String result = null;
        try {
            result = mapper.writeValueAsString(geoFeaturesCollection);
        } catch (JsonProcessingException e) {
            throw new TigerToolsException(e);
        }

        return result;
    }
}
