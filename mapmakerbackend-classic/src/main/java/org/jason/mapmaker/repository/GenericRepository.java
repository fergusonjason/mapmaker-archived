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
package org.jason.mapmaker.repository;

import java.io.Serializable;

/**
 * GenericRepository interface
 *
 * @since 0.4
 * @param <T>   type of object being operated on by extensions of the interface
 * @author Jason Ferguson
 */
public interface GenericRepository<T> {

    /**
     * Persist/save an object
     * 
     * @param object    object to persist
     */
    void persist(T object);

    /**
     * Remove/delete an object
     *
     * @param object    object to remove
     */
    void remove(T object);

    /**
     * Return an object by its unique identifier. Returns a serializable since the GAE datastore doesn't necessarily
     * return an int unless configured to do so.
     *
     * @param uniqueIdentifer   unique identifer of the object to return
     * @return      the object with the given identifier
     */
    T getById(Serializable uniqueIdentifer);


    int getCount();
}
