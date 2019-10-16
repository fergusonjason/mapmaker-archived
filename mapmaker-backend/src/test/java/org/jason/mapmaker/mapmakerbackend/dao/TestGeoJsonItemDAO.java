package org.jason.mapmaker.mapmakerbackend.dao;

import org.jason.mapmaker.mapmakerbackend.enumerated.GeometryType;
import org.jason.mapmaker.mapmakerbackend.model.GeoJsonItem;
import org.jason.mapmaker.mapmakerbackend.model.Geometry;
import org.jason.mapmaker.mapmakerbackend.model.TigerData;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Jason
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-test.xml"})
public class TestGeoJsonItemDAO {

    @Autowired
    private GeoJsonItemDAO dao;

    @Autowired
    private TigerDataDAO tigerDataDAO;

    @Autowired
    private MongoOperations mongoOperations;

    @After
    public void tearDown() {
        mongoOperations.dropCollection(GeoJsonItem.class);
    }

    @Test
    public void save_succeeds() {

        try {
            GeoJsonItem item = new GeoJsonItem();
            item.setType(GeometryType.Polygon);

            Geometry geometry = new Geometry();
            geometry.setGeometryType(GeometryType.Point);
            BigDecimal[] coordinates = new BigDecimal[]{new BigDecimal("1.2"),new BigDecimal("3.8")};
            geometry.setCoordinates(new ArrayList<BigDecimal[]>());
            geometry.getCoordinates().add(coordinates);

            item.setGeometry(geometry);
            item.setProperties(new LinkedHashMap<String, Object>());

            dao.save(item);

            Query query = new Query(Criteria.where("type").is(GeometryType.Polygon));

            List<GeoJsonItem> results = mongoOperations.find(query, GeoJsonItem.class);
            Assert.assertTrue("results list should not be empty", results.size() > 0);
            System.out.println("Result list size: " + results.size());
        } catch (Exception e) {
            Assert.fail("save method should not have thrown an exception");
        }
    }

    @Test
    public void find_multipleDocumentsWithAndWithoutTigerData_succeeds() {

        // object with a tiger record in the properties
        GeoJsonItem item1 = new GeoJsonItem();

        Geometry geo1 = new Geometry();
        geo1.setGeometryType(GeometryType.Polygon);
        BigDecimal[] coordinates1 = new BigDecimal[]{new BigDecimal("1.2"),new BigDecimal("3.8")};
        geo1.setCoordinates(new ArrayList<BigDecimal[]>());
        geo1.getCoordinates().add(coordinates1);

        TigerData td = new TigerData();
        td.setGeoId("10101");
        td.setName("Temp Item");

        item1.setProperties(new LinkedHashMap<String, Object>());
        item1.getProperties().put("tigerData", td);

        item1.setGeometry(geo1);
        dao.save(item1);

        // object without a tiger record in the properties
        GeoJsonItem item2 = new GeoJsonItem();

        Geometry geo2 = new Geometry();
        geo2.setGeometryType(GeometryType.MultiPolygon);
        BigDecimal[] coordinates2 = new BigDecimal[]{new BigDecimal("22"),new BigDecimal("43")};
        geo2.setCoordinates(new ArrayList<BigDecimal[]>());
        geo2.getCoordinates().add(coordinates2);

        item2.setGeometry(geo2);
        dao.save(item2);

        Query query = new Query(Criteria.where("properties.tigerData.geoId").is("10101"));
        List<GeoJsonItem> results = mongoOperations.find(query, GeoJsonItem.class);

        Assert.assertTrue("size of list should be > 0", results.size() > 0);

        Query query2 = new Query(Criteria.where("properties.tigerData").is(null));
        List<GeoJsonItem> results2 = mongoOperations.find(query2, GeoJsonItem.class);

        Assert.assertTrue("size of list should be > 0", results2.size() > 0);
    }
}
