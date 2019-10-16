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
package org.jason.mapmaker.shared.result;

import com.gwtplatform.dispatch.shared.Result;
import org.jason.mapmaker.shared.model.Location;

/**
 * GetLocationByGeoIdResult.java
 *
 * @author Jason Ferguson
 */
public class GetLocationByGeoIdResult implements Result {

    private Location location;

    // serialization only
    public GetLocationByGeoIdResult() {

    }
    
    public GetLocationByGeoIdResult(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }
}
