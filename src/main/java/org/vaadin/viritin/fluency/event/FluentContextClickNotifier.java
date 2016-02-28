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
package org.vaadin.viritin.fluency.event;

import com.vaadin.event.ContextClickEvent;

/**
 * A {@link ContextClickEvent.ContextClickNotifier} complemented by fluent
 * setters.
 *
 * @author Max Schuster
 * @param <C> Fluent component type
 * @see ContextClickEvent.ContextClickNotifier
 */
public interface FluentContextClickNotifier<C extends FluentContextClickNotifier<C>>
        extends ContextClickEvent.ContextClickNotifier {

    /**
     * Adds a context click listener that gets notified when a context click
     * happens.
     *
     * @param listener the context click listener to add
     * @return This component
     * @see
     * #addContextClickListener(com.vaadin.event.ContextClickEvent.ContextClickListener)
     */
    public C withContextClickListener(ContextClickEvent.ContextClickListener listener);

}
