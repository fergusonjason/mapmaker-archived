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
package org.jason.mapmaker.server.actionHandler.availableData;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.AbstractActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;
import org.jason.mapmaker.server.service.GenericSettingsService;
import org.jason.mapmaker.shared.action.availableData.GetUsgsFeaturesVersionAction;
import org.jason.mapmaker.shared.model.GenericSettings;
import org.jason.mapmaker.shared.result.availableData.GetUsgsFeaturesVersionResult;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * GWTP ActionHandler for getting the current version of the USGS features
 *
 * @since 0.4.2
 * @author Jason Ferguson
 */
public class GetUsgsFeaturesVersionHandler extends AbstractActionHandler<GetUsgsFeaturesVersionAction, GetUsgsFeaturesVersionResult> {

    private GenericSettingsService genericSettingsService;

    @Autowired
    public void setGenericSettingsService(GenericSettingsService genericSettingsService) {
        this.genericSettingsService = genericSettingsService;
    }

    public GetUsgsFeaturesVersionHandler() {
        super(GetUsgsFeaturesVersionAction.class);
    }

    @Override
    public GetUsgsFeaturesVersionResult execute(GetUsgsFeaturesVersionAction action, ExecutionContext context) throws ActionException {
        GenericSettings gs = genericSettingsService.get();

        return new GetUsgsFeaturesVersionResult(gs.getUsgsVersion());
    }

    @Override
    public void undo(GetUsgsFeaturesVersionAction action, GetUsgsFeaturesVersionResult result, ExecutionContext context) throws ActionException {
        // no-op
    }
}
