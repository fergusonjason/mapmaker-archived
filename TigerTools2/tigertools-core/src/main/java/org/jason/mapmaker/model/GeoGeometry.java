package org.jason.mapmaker.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Yes, I know the name is redundant. Shut up.
 *
 * @author Jason
 */
public class GeoGeometry {

    private String type = "Polygon";
    //private List<List<Double>> coordinates = Lists.newArrayList();
    private List<List<BigDecimal[]>> coordinates = new ArrayList<>();

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

/*    public List<List<Double>> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<List<Double>> coordinates) {
        this.coordinates = coordinates;
    }*/

    public List<List<BigDecimal[]>> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<List<BigDecimal[]>> coordinates) {
        this.coordinates = coordinates;
    }
}
