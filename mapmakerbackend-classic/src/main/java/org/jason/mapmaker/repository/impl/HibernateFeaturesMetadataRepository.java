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

import org.jason.mapmaker.model.FeaturesMetadata;
import org.jason.mapmaker.repository.FeaturesMetadataRepository;
import org.springframework.stereotype.Repository;

/**
 * Hibernate implementation of FeaturesMetadataRepository
 *
 * @since 0.4
 * @author Jason Ferguson
 */
@Repository("featuresMetadataRepository")
public class HibernateFeaturesMetadataRepository extends HibernateGenericRepository<FeaturesMetadata>
implements FeaturesMetadataRepository {

    public HibernateFeaturesMetadataRepository() {
        super(FeaturesMetadata.class);
    }

}
