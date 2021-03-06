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
import org.jason.mapmaker.model.BorderPoint;
import org.jason.mapmaker.model.Location;
import org.jason.mapmaker.repository.BorderPointRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Hibernate implementation of BorderPointRepository
 *
 * @since 0.4
 * @author Jason Ferguson
 */
@Repository("borderPointRepository")
public class HibernateBorderPointRepository extends HibernateGenericRepository<BorderPoint>
        implements BorderPointRepository {

    public HibernateBorderPointRepository() {
        super(BorderPoint.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Double> getBoundsByLocation(Location l) {

        log.debug("Entered getBoundsByLocation()");

        String strquery = "select new map(max(bp.lat) as MAXLAT, min(bp.lat) as MINLAT, max(bp.lng) as MAXLNG, "
                + "min(bp.lng) as MINLNG) from BorderPoint bp where bp.location = :Location";
        Query query = sessionFactory.getCurrentSession().createQuery(strquery);
        query.setParameter("Location", l);
        List results = query.list();

        if (results.size() == 0) {
            return null;
        }

        return (Map<String, Double>) results.get(0);

    }

}
