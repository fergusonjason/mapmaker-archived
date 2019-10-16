package org.jason.mapmaker.importer.strategy;

import org.jason.mapmaker.api.ShapefileStrategy;

/**
 * @author Jason
 */
public class ShapefileStrategyFactory {

    public static ShapefileStrategy getShapefileStrategy(String mtfcc) {

        switch(mtfcc) {
            case "G4000":
                return new StateShapefileStrategy();
            default:
        }

        return null;
    }
}
