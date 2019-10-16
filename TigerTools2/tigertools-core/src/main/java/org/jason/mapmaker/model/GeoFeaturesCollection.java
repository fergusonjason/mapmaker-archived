package org.jason.mapmaker.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jason
 */
public class GeoFeaturesCollection implements Serializable {


    private String source;

     private List<GeoFeature> features = new ArrayList<>();

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<GeoFeature> getFeatures() {
        return features;
    }

    public void setFeatures(List<GeoFeature> features) {
        this.features = features;
    }
}
