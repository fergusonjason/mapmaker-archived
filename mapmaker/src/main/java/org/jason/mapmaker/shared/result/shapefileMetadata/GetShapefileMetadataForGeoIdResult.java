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
package org.jason.mapmaker.shared.result.shapefileMetadata;

import com.gwtplatform.dispatch.shared.Result;
import org.jason.mapmaker.shared.model.ShapefileMetadata;

import java.util.List;

/**
 * GWT-Platform Result class for returning the list of persisted ShapefileMetadata objects with a given
 * GeoId.
 *
 * @since 0.4
 * @author Jason Ferguson
 */
public class GetShapefileMetadataForGeoIdResult implements Result {

    private List<ShapefileMetadata> resultList;

    // for serialization only, do not use
    public GetShapefileMetadataForGeoIdResult() {
    }

    public GetShapefileMetadataForGeoIdResult(List<ShapefileMetadata> resultList) {
        this.resultList = resultList;
    }

    public List<ShapefileMetadata> getResultList() {
        return resultList;
    }
}
