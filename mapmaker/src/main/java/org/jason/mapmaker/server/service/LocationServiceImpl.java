/**
 * Copyright 2011 Jason Ferguson.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.jason.mapmaker.server.service;

import com.vividsolutions.jts.geom.*;
import org.apache.commons.lang.StringUtils;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.jason.mapmaker.server.repository.LocationRepository;
import org.jason.mapmaker.server.util.ShapefileUtil;
import org.jason.mapmaker.shared.exceptions.ServiceException;
import org.jason.mapmaker.shared.model.BorderPoint;
import org.jason.mapmaker.shared.model.Location;
import org.jason.mapmaker.shared.model.MTFCC;
import org.jason.mapmaker.shared.model.comparator.BorderPointIdComparator;
import org.jason.mapmaker.shared.util.GeographyUtils;
import org.opengis.feature.simple.SimpleFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Implementation of the LocationService interface
 *
 * @author Jason Ferguson
 * @since 0.1
 */
@Service("locationService")
public class LocationServiceImpl implements LocationService, PersistenceService<Location> {

    private MtfccService mtfccService;
    private LocationRepository locationRepository;
    private ShapefileMetadataService shapefileMetadataService;

    @Autowired
    public void setMtfccService(MtfccService mtfccService) {
        this.mtfccService = mtfccService;
    }

    @Autowired
    public void setLocationRepository(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Autowired
    public void setShapefileMetadataService(ShapefileMetadataService shapefileMetadataService) {
        this.shapefileMetadataService = shapefileMetadataService;
    }

    public void persist(Location object) throws ServiceException {
        try {
            locationRepository.save(object);
        } catch (Exception e) {
            throw new ServiceException("persist() threw an Exception", e);
        }
    }

    public void remove(Location object) throws ServiceException {
        try {
            locationRepository.delete(object);
        } catch (Exception e) {
            throw new ServiceException("remove() threw an Exception", e);
        }
    }

    public int removeByStateGeoIdAndMtfcc(String stateGeoId, String mtfcc) throws ServiceException {

        MTFCC m = mtfccService.get(mtfcc);
        Location example = new Location();
        example.setGeoId(StringUtils.left(stateGeoId, 2)); // the first two digits are always equal to the state's geoid
        example.setMtfcc(m);

        List<Location> resultList = locationRepository.getByExample(example);
        int affectedRows = resultList.size();
        for (Location l : resultList) {
            locationRepository.delete(l);
        }

        return affectedRows;
    }

    public void saveList(List<Location> locationList) throws ServiceException {
        try {
            locationRepository.saveList(locationList);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public Map<String, Long> getLocationCounts() {
        return locationRepository.getLocationCounts();
    }

    public List<Map<String, String>> getStateAndEquivalentListForMtfcc(String mtfccCode) {

        MTFCC m = mtfccService.get(mtfccCode);
        return locationRepository.getStateAndEquivalentListForMtfcc(m);

    }

    public Map<String, String> getLocationsByStateAndMtfcc(String stateFP, String mtfcc) {

        List<Location> locationList = locationRepository.getLocations(mtfcc, stateFP);
        //Collections.sort(locationList, new LocationNameAlphanumComparator());
        Map<String, String> resultMap = new LinkedHashMap<String, String>();
        for (Location l : locationList) {
            resultMap.put(l.getGeoId(), l.getName());
        }

        return resultMap;
    }

    public Map<String, String> getCountyBasedLocations(String mtfccCode, String stateFP, String countyFP) {

        MTFCC mtfcc = mtfccService.get(mtfccCode);

        Location example = new Location();
        example.setStateFP(stateFP);
        example.setCountyFP(countyFP);
        example.setMtfcc(mtfcc);

        List<Location> resultList = locationRepository.getByExample(example);
        Map<String, String> resultMap = new LinkedHashMap<String, String>();
        for (Location l : resultList) {
            resultMap.put(l.getGeoId(), l.getName());
        }

        resultMap.remove(stateFP.concat(countyFP)); // bugfix, the county is showing up in the list of its subfeatures
        // quickfix is to do a quick and dirty generation of the county
        // geoid and remove it
        return resultMap;

    }

    public Location getByGeoIdAndMtfcc(String geoId, String mtfccCode) {
        return locationRepository.getByGeoIdAndMtfcc(geoId, mtfccCode);
    }

    public void populateLocationFromFeature(Location location, SimpleFeature feature) {

        location.setGeoId((String) feature.getAttribute("GEOID10"));
        location.setStateFP((String) feature.getAttribute("STATEFP10")); // always gonna have a statefp

        // these ones are more conditional, but it will either set the result or null
        location.setCountyFP((String) feature.getAttribute("COUNTYFP10"));

        // set the basic information

        // name is conditional, some feature types don't have a NAME10 attribute so we have to use NAMELSAD10
        if (feature.getAttribute("NAME10") != null) {
            location.setName((String) feature.getAttribute("NAME10"));
        } else {
            location.setName((String) feature.getAttribute("NAMELSAD10"));
        }

        location.setLsad((String) feature.getAttribute("LSAD10"));
        location.setUrbanRural((String) feature.getAttribute("UR10"));
        location.setFunctionalStatus((String) feature.getAttribute("FUNCSTAT10"));

        // numeric values
        location.setLandArea((Long) feature.getAttribute("ALAND10"));
        location.setWaterArea((Long) feature.getAttribute("AWATER10"));
        location.setInternalLat(new Double((String) feature.getAttribute("INTPTLAT10")));
        location.setInternalLng(new Double((String) feature.getAttribute("INTPTLON10")));

        // Convert to from the MTFCC String to the actual object
        String featureMtfcc = (String) feature.getAttribute("MTFCC10");
        MTFCC mtfcc = mtfccService.get(featureMtfcc);
        location.setMtfcc(mtfcc);
    }

    public Map<String, String> getCountiesForState(String stateGeoId) {

        MTFCC m = mtfccService.get(GeographyUtils.MTFCC.COUNTY);

        Location example = new Location();
        example.setGeoId(stateGeoId);
        example.setMtfcc(m);

        List<Location> resultList = locationRepository.getByExample(example);
        Map<String, String> resultMap = new LinkedHashMap<String, String>();
        for (Location l : resultList) {
            resultMap.put(l.getCountyFP(), l.getName());
        }

        return resultMap;
    }

    @Override
    public void deleteAll() throws ServiceException {
        try {
            locationRepository.deleteAll();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(Location l) {
        locationRepository.update(l);
    }

    @Override
    public Map<MTFCC, Location> getLocationMapForCoordinates(double lng, double lat) {

        List<Location> locationList = locationRepository.getLocationsByCoordinates(lng, lat);

        // create 2 maps: one to hold the end-state map of one MTFCC to one Location, and one to hold a list of
        // candidate Locations if a set of coordinates can belong to more than one Location.
        Map<MTFCC, Location> locationMap = new LinkedHashMap<MTFCC, Location>();  // end state map
        Map<MTFCC, List<Location>> locationListMap = getEmptyMtfccToLocationListMap(locationList);  // map to flatten into end state

        if (locationList.isEmpty()) {
            return locationMap;
        }

        // assign locations to their slots in the map
        for (Location location : locationList) {
            MTFCC m = location.getMtfcc();
            locationListMap.get(m).add(location);
        }

        // start checking the points and kicking out candidate locations if there is more than one Location per slot
        for (MTFCC m : locationListMap.keySet()) {
            // if we don't have ANY results, just populate w/ null. (This probably shouldn't happen anymore)
            if (locationListMap.get(m).size() == 0) {
                locationMap.put(m, null);
            } else {
                // Even if there is only one potential location, it has to be checked because the user may click on
                // on a location that hasn't been imported, but still falls within the square of another location. If
                // there is more than one candidate, we have to figure out the right one.
                for (Location candidateLocation : locationListMap.get(m)) {
                    if (isCoordinateInLocation(lng, lat, candidateLocation)) {
                        locationMap.put(m, candidateLocation);
                        break;      // short-circuit remaining processing since we found the right one
                    }
                }
            }
        }

        return locationMap;
    }

    private Map<MTFCC, List<Location>> getEmptyMtfccToLocationListMap(List<Location> locationList) {

        Map<MTFCC, List<Location>> locationMap = new LinkedHashMap<MTFCC, List<Location>>();
        for (Location l: locationList) {
            locationMap.put(l.getMtfcc(), new ArrayList<Location>());
        }
//        for (MTFCC m : endResultMap.keySet()) {
//            locationMap.put(m, new ArrayList<Location>());
//        }

        return locationMap;

    }

    private boolean isCoordinateInLocation(double lng, double lat, Location l) {

        // get the geometry factory
        // TODO: make Geometry Factory a singleton
        GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory(null);

        // Create a Point geometry from the given coordinates
        Point point = geometryFactory.createPoint(new Coordinate(lng, lat));

        List<BorderPoint> borderPointList = l.getBorderPointList();
        Collections.sort(borderPointList, new BorderPointIdComparator());

        // close the ring by appending the starting point to the end
        BorderPoint startPoint = borderPointList.get(0);
        borderPointList.add(startPoint);

        // convert the border points to an array of Coordinates (Geotools doesn't like collections)
        Coordinate[] locationCoordinates = ShapefileUtil.getCoordinatesFromBorderPointList(borderPointList);

        // create a linear ring representing the border
        LinearRing border = geometryFactory.createLinearRing(locationCoordinates);

        // create the polygon from the linear ring. Pass null as second argument since we don't have any holes in the polygon
        Polygon polygon = geometryFactory.createPolygon(border, null);

        return polygon.contains(point);

    }
}
