package org.jason.mapmaker.tigertools.importer.strategy;

import org.jason.mapmaker.tigertools.model.GeoFeature;
import org.jason.mapmaker.tigertools.util.TigerToolsException;

import java.io.File;
import java.util.List;

/**
 * @author Jason
 */
public interface ImportStrategy {

    /**
     * Import from a
     * @param file
     * @return
     */
    List<GeoFeature> doImport(File file) throws TigerToolsException;

}
