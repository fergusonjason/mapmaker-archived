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
package org.jason.mapmaker.repository.impl;

import org.hibernate.Query;
import org.jason.mapmaker.model.ShapefileMetadata;
import org.jason.mapmaker.repository.ShapefileMetadataRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Hibernate implementation of ShapefileMetadataRepository interface
 *
 * @since 0.4
 * @author Jason Ferguson
 */
@Repository("shapefileMetadataRepository")
public class HibernateShapefileMetadataRepository extends HibernateGenericRepository<ShapefileMetadata>
    implements ShapefileMetadataRepository {

    public HibernateShapefileMetadataRepository() {
        super(ShapefileMetadata.class);
    }

    public List<ShapefileMetadata> getByGeoId(String geoId) {

        log.debug("Entered getByGeoId(), geoId: {}", geoId);

        String hql1 = "select SM from ShapefileMetadata SM where SM.geoId = :geoId";

        Query query = sessionFactory.getCurrentSession().createQuery(hql1);
        query.setString("geoId", geoId);

        List<ShapefileMetadata> resultList = query.list();

        return resultList;

    }
}
