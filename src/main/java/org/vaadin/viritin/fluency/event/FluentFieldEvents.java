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

import com.vaadin.event.FieldEvents;

/**
 * A {@link FieldEvents} complemented by fluent setters.
 *
 * @author Max Schuster
 * @param <C> Fluent component type
 * @see FieldEvents
 */
public interface FluentFieldEvents<C extends FluentFieldEvents<C>>
        extends FieldEvents {

    /**
     * A {@link FocusNotifier} complemented by fluent setters.
     *
     * @param <C> Fluent component type
     * @see FocusNotifier
     */
    public interface FluentFocusNotifier<C extends FluentFocusNotifier<C>>
            extends FocusNotifier {

        /**
         * Adds a <code>FocusListener</code> to the Component which gets fired
         * when a <code>Field</code> receives keyboard focus.
         *
         * @param listener The focus listener to add
         * @return This component
         * @see #addFocusListener(com.vaadin.event.FieldEvents.FocusListener)
         */
        public C withFocusListener(FocusListener listener);

    }

    /**
     * A {@link BlurNotifier} complemented by fluent setters.
     *
     * @param <C> Fluent component type
     * @see BlurNotifier
     */
    public interface FluentBlurNotifier<C extends FluentBlurNotifier<C>>
            extends BlurNotifier {

        /**
         * Adds a <code>BlurListener</code> to the Component which gets fired
         * when a <code>Field</code> loses keyboard focus.
         *
         * @param listener The blur listener to add
         * @return This component
         * @see #addBlurListener(com.vaadin.event.FieldEvents.BlurListener)
         */
        public C withBlurListener(BlurListener listener);

    }

    /**
     * A {@link TextChangeNotifier} complemented by fluent setters.
     *
     * @param <C> Fluent component type
     * @see TextChangeNotifier
     */
    public interface FluentTextChangeNotifier<C extends FluentTextChangeNotifier<C>>
            extends TextChangeNotifier {

        /**
         * Adds a <code>TextChangeListener</code> to the Component which gets
         * fired when the text of the <code>Field</code> changes.
         *
         * @param listener The text change listener to add
         * @return This component
         * @see
         * #addTextChangeListener(com.vaadin.event.FieldEvents.TextChangeListener)
         */
        public C withTextChangeListener(TextChangeListener listener);

    }

}
