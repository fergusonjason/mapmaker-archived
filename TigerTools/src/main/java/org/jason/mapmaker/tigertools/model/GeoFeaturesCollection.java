package org.jason.mapmaker.tigertools.model;

import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jason
 */
public class GeoFeaturesCollection implements Serializable {

    private List<GeoFeature> features = Lists.newArrayList();

    public List<GeoFeature> getFeatures() {
        return features;
    }

    public void setFeatures(List<GeoFeature> features) {
        this.features = features;
    }
}
