package org.jason.mapmaker.exporter.strategy;

import org.jason.mapmaker.api.KmlExportStrategy;

/**
 * @author Jason
 */
public class KmlExporterFactory {

    public static KmlExportStrategy getExportStrategy(String mtfcc) {

        switch(mtfcc) {
            case "G4000":
                return new StateKmlExporter();
            default:
                return null;
        }

    }
}
