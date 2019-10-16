package org.jason.mapmaker.mapmakerbackend.dao.mongodb;

import org.jason.mapmaker.mapmakerbackend.dao.GeoJsonItemDAO;
import org.jason.mapmaker.mapmakerbackend.model.GeoJsonItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Jason
 */
@Repository("geoJsonItemDAO")
public class MongoGeoJsonItemDAO implements GeoJsonItemDAO {

    @Autowired
    private MongoOperations mongoOperation;

    @Transactional
    public void save(GeoJsonItem item) {
        mongoOperation.save(item);
    }
}
