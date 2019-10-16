package org.jason.mapmaker.mapmakerbackend.dao.mongodb;

import org.jason.mapmaker.mapmakerbackend.dao.TigerDataDAO;
import org.jason.mapmaker.mapmakerbackend.model.TigerData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

/**
 * @author Jason
 */
@Repository("tigerDataDAO")
public class MongoTigerDataDAO implements TigerDataDAO{

    @Autowired
    private MongoOperations mongoOperation;


    @Override
    public void save(TigerData item) {
        mongoOperation.save(item);
    }
}
