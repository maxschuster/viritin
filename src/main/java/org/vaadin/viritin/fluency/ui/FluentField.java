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
package org.vaadin.viritin.fluency.ui;

import com.vaadin.ui.Field;
import org.vaadin.viritin.fluency.data.FluentBufferedValidatable;
import org.vaadin.viritin.fluency.data.FluentProperty;

/**
 * A {@link Field} complemented by fluent setters.
 * 
 * @author Max Schuster
 * @param <C> Fluent component type
 * @param <T> The type of values in the field, which might not be the same type
 * as that of the data source if converters are used
 * @see Field
 */
public interface FluentField<C extends FluentField<C, T>, T> extends Field<T>,
        FluentComponent<C>, FluentBufferedValidatable<C>, 
        FluentComponent.FluentFocusable<C>, 
        FluentProperty<C, T>, FluentProperty.FluentValueChangeNotifier<C>,
        FluentProperty.FluentEditor<C> {

    /**
     * Sets the field required. Required fields must filled by the user.
     *
     * @param required Is the field required.
     * @return This component
     * @see #setRequired(boolean)
     */
    public C withRequired(boolean required);

    /**
     * Sets the error message to be displayed if a required field is empty.
     *
     * @param requiredMessage Error message.
     * @return This component
     * @see #setRequiredError(java.lang.String)
     */
    public C withRequiredError(String requiredMessage);

}
