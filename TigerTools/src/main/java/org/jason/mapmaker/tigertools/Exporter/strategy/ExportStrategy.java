package org.jason.mapmaker.tigertools.exporter.strategy;

import org.jason.mapmaker.tigertools.model.GeoFeature;
import org.jason.mapmaker.tigertools.util.TigerToolsException;

import java.util.List;

/**
 * @author Jason
 */
public interface ExportStrategy<T> {

    T doExport(List<GeoFeature> geoFeatures) throws TigerToolsException;
}
