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

import com.vaadin.event.Action;
import com.vaadin.event.ShortcutListener;
import com.vaadin.server.Resource;

/**
 * An {@link Action} complemented by fluent setters.
 * 
 * @author Max Schuster
 * @see Action
 */
public class FluentAction extends Action {

    public FluentAction(String caption) {
        super(caption);
    }

    public FluentAction(String caption, Resource icon) {
        super(caption, icon);
    }

    /**
     * A {@link ShortcutNotifier} complemented by fluent setters.
     * 
     * @param <C> Fluent component type
     * @see ShortcutNotifier
     */
    public interface FluentShortcutNotifier<C extends FluentShortcutNotifier<C>>
            extends ShortcutNotifier {

        public C withShortcutListener(ShortcutListener shortcut);

    }

    /**
     * A {@link Container} complemented by fluent setters.
     * 
     * @param <C> Fluent component type
     * @see Container
     */
    public interface FluentContainer<C extends FluentContainer<C>>
            extends Container {

        /**
         * Registers a new action handler for this container
         *
         * @param actionHandler the new handler to be added.
         * @return This component
         * @see #addActionHandler(com.vaadin.event.Action.Handler)
         */
        public C withActionHandler(Action.Handler actionHandler);

    }

    /**
     * Sets the caption.
     *
     * @param caption the caption to set.
     * @return This action
     * @see #setCaption(java.lang.String) 
     */
    public FluentAction withCaption(String caption) {
        setCaption(caption);
        return this;
    }

    /**
     * Sets the icon.
     *
     * @param icon the icon to set.
     * @return This action
     * @see #setIcon(com.vaadin.server.Resource) 
     */
    public FluentAction withIcon(Resource icon) {
        setIcon(icon);
        return this;
    }

}
