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
package org.jason.mapmaker.server.service;

import org.jason.mapmaker.shared.exceptions.ServiceException;

/**
 * PersistenceService.java
 *
 * Defines operations common to any Service class that needs to interact with the JPA repository classes
 *
 * @author Jason Ferguson
 */
public interface PersistenceService<T> {

    /**
     * Persist an object
     *
     * @param object    object to persist
     */
    void persist(T object) throws ServiceException;

    /**
     * Remove (detach) an object
     *
     * @param object    object to remove
     */
    void remove(T object) throws ServiceException;
}
