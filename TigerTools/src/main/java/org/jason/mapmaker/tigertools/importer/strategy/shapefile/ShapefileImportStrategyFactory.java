package org.jason.mapmaker.tigertools.importer.strategy.shapefile;

import com.google.common.base.Optional;

/**
 * @author Jason
 */
public class ShapefileImportStrategyFactory {

    public static ShapefileImportStrategy getStrategy(String mtfcc, Optional<Double> distanceTolerance) {

        ShapefileImportStrategy result;
        switch (mtfcc) {
            case "G4000":
                result = new StateShapefileImportStrategy(distanceTolerance);
                break;
            case "G4020":
                result = new CountyShapefileImportStrategy(distanceTolerance);
                break;
            default:
                throw new UnsupportedOperationException("MTFCC Not Supported!");
        }

        return result;

    }
    public static ShapefileImportStrategy getStrategy(String mtfcc) {

        return getStrategy(mtfcc, Optional.<Double>absent());
//        ShapefileImportStrategy result;
//        switch (mtfcc) {
//            case "G4000":
//                result = new StateShapefileImportStrategy();
//                break;
//            case "G4020":
//                result = new CountyShapefileImportStrategy();
//                break;
//            default:
//                throw new UnsupportedOperationException("MTFCC Not Supported!");
//        }
//
//        return result;
    }
}
