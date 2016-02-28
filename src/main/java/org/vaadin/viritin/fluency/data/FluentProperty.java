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
package org.vaadin.viritin.fluency.data;

import com.vaadin.data.Property;
import org.vaadin.viritin.fluency.ui.FluentComponent;

/**
 * A {@link Property} complemented by fluent setters.
 *
 * @author Max Schuster
 * @param <C> Fluent component type
 * @param <T> type of values of the property
 * @see Property
 */
public interface FluentProperty<C extends FluentProperty<C, T>, T>
        extends Property<T> {

    /**
     * Sets the value of the Property.
     * <p>
     * Implementing this functionality is optional. If the functionality is
     * missing, one should declare the Property to be in read-only mode and
     * throw <code>Property.ReadOnlyException</code> in this function.
     * </p>
     *
     * Note : Since Vaadin 7.0, setting the value of a non-String property as a
     * String is no longer supported.
     *
     * @param newValue New value of the Property. This should be assignable to
     * the type returned by getType
     *
     * @throws Property.ReadOnlyException if the object is in read-only mode
     * @return This component
     * @see #setValue(java.lang.Object)
     */
    public C withValue(T newValue) throws Property.ReadOnlyException;

    /**
     * Sets the Property's read-only mode to the specified status.
     *
     * This functionality is optional, but all properties must implement the
     * <code>isReadOnly</code> mode query correctly.
     *
     * @param newStatus new read-only status of the Property
     * @return This component
     * @see #setReadOnly(boolean)
     */
    public C withReadOnly(boolean newStatus);

    /**
     * A {@link ValueChangeNotifier} complemented by fluent setters.
     *
     * @param <C> Fluent component type
     * @see ValueChangeNotifier
     */
    public interface FluentValueChangeNotifier<C extends FluentComponent<C> & FluentValueChangeNotifier<C>> 
            extends ValueChangeNotifier {

        /**
         * Registers a new value change listener for this Property.
         *
         * @param listener the new Listener to be registered
         * @return This component
         */
        public C withValueChangeListener(Property.ValueChangeListener listener);

    }
    
    /**
     * A {@link Editor} complemented by fluent setters.
     *
     * @param <C> Fluent component type
     * @see Editor
     */
    public interface FluentEditor<C extends FluentComponent<C> & FluentEditor<C>>
            extends Editor, FluentViewer<C> {
        
    }

    /**
     * A {@link Viewer} complemented by fluent setters.
     *
     * @param <C> Fluent component type
     * @see Viewer
     */
    public interface FluentViewer<C extends FluentComponent<C> & FluentViewer<C>>
            extends Viewer {

        /**
         * Sets the Property that serves as the data source of the viewer.
         *
         * @param newDataSource the new data source Property
         * @return This component
         * @see #setPropertyDataSource(com.vaadin.data.Property) 
         */
        @SuppressWarnings("rawtypes")
        public C withPropertyDataSource(Property newDataSource);

    }

    /**
     * A {@link ProperReadOnlyStatusChangeNotifierty} complemented by fluent
     * setters.
     *
     * @param <C> Fluent component type
     * @see ReadOnlyStatusChangeNotifier
     */
    public interface FluentReadOnlyStatusChangeNotifier<C extends FluentReadOnlyStatusChangeNotifier<C>> 
            extends ReadOnlyStatusChangeNotifier {

        /**
         * Registers a new read-only status change listener for this Property.
         *
         * @param listener the new Listener to be registered
         * @return This component
         * @see
         * #addReadOnlyStatusChangeListener(com.vaadin.data.Property.ReadOnlyStatusChangeListener)
         */
        public C withReadOnlyStatusChangeListener(
                Property.ReadOnlyStatusChangeListener listener);

    }

}
