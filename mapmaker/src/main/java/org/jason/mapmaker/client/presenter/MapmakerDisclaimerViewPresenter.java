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
package org.jason.mapmaker.client.presenter;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

/**
 * <code>PresenterWidget</code> for the Disclaimer section of the application interface
 *
 * @since 0.1
 * @author Jason Ferguson
 * @see org.jason.mapmaker.client.view.MapmakerDisclaimerViewImpl
 */
public class MapmakerDisclaimerViewPresenter extends PresenterWidget<MapmakerDisclaimerViewPresenter.MyView> {

    public interface MyView extends View {
    }

    @Inject
    public MapmakerDisclaimerViewPresenter(EventBus eventBus, MyView view) {
        super(eventBus, view);
    }

}
