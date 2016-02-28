/*
 * Copyright 2016 Matti Tahvonen.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.vaadin.viritin.fluency.server;

import com.vaadin.server.ClientConnector;
import com.vaadin.server.ErrorHandler;

/**
 * A {@link ClientConnector} complemented by fluent setters.
 *
 * @author Max Schuster
 * @param <C> Fluent component type
 * @see ClientConnector
 */
public interface FluentClientConnector<C extends FluentClientConnector<C>>
        extends ClientConnector {

    /**
     * Adds an attach listener to this connector.
     * 
     * @param listener The attach listener
     * @return This component
     * @see #addAttachListener(com.vaadin.server.ClientConnector.AttachListener)
     */
    public C withAttachListener(AttachListener listener);

    /**
     * Adds a detach listener to this connector.
     * 
     * @param listener The detach listener
     * @return This component
     * @see #addDetachListener(com.vaadin.server.ClientConnector.DetachListener)
     */
    public C withDetachListener(DetachListener listener);

    /**
     * Sets the error handler for the connector.
     *
     * The error handler is dispatched whenever there is an error processing the
     * data coming from the client for this connector.
     *
     * @param errorHandler The error handler for this connector
     * @return This component
     * @see #setErrorHandler(com.vaadin.server.ErrorHandler)
     */
    public C withErrorHandler(ErrorHandler errorHandler);

}
