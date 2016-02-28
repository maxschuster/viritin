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

import com.vaadin.data.util.converter.Converter;
import com.vaadin.ui.AbstractField;
import org.vaadin.viritin.fluency.data.FluentProperty;

/**
 * A {@link AbstractField} complemented by fluent setters.
 *
 * @author Max Schuster
 * @param <C> Fluent component type
 * @param <T> The type of values in the field, which might not be the same type
 * as that of the data source if converters are used
 * @see AbstractField
 */
public interface FluentAbstractField<C extends AbstractField<T> & FluentAbstractField<C, T>, T> 
        extends FluentAbstractComponent<C>, FluentField<C, T>, 
        FluentProperty.FluentReadOnlyStatusChangeNotifier<C> {

    /**
     * Retrieves a converter for the field from the converter factory defined
     * for the application. Clears the converter if no application reference is
     * available or if the factory returns null.
     *
     * @param datamodelType The type of the data model that we want to be able
     * to convert from
     * @return This component
     * @see AbstractField#setConverter(java.lang.Class)
     */
    public C withConverter(Class<?> datamodelType);

    /**
     * Sets the value of the field using a value of the data source type. The
     * value given is converted to the field type and then assigned to the
     * field. This will update the property data source in the same way as when
     * {@link #setValue(Object)} is called.
     *
     * @param value The value to set. Must be the same type as the data source.
     * @return This component
     * @see AbstractField#setConvertedValue(java.lang.Object)
     */
    public C withConvertedValue(Object value);

    /**
     * Sets the error that is shown if the field value cannot be converted to
     * the data source type. If {0} is present in the message, it will be
     * replaced by the simple name of the data source type. If {1} is present in
     * the message, it will be replaced by the ConversionException message.
     *
     * @param valueConversionError Message to be shown when conversion of the
     * value fails
     * @return This component
     * @see AbstractField#setConversionError(java.lang.String)
     */
    public C withConversionError(String valueConversionError);

    /**
     * Enable or disable automatic, visible validation.
     *
     * If automatic validation is enabled, any validators connected to this
     * component are evaluated while painting the component and potential error
     * messages are sent to client. If the automatic validation is turned off,
     * isValid() and validate() methods still work, but one must show the
     * validation in their own code.
     *
     * @param validateAutomatically True, if automatic validation is enabled.
     * @return This component
     * @see AbstractField#setValidationVisible(boolean)
     */
    public C withValidationVisible(boolean validateAutomatically);

    /**
     * Sets the current buffered source exception.
     *
     * @param currentBufferedSourceException
     * @return This component
     * @see
     * AbstractField#setCurrentBufferedSourceException(com.vaadin.data.Buffered.SourceException)
     */
    public C withCurrentBufferedSourceException(SourceException currentBufferedSourceException);

    /**
     * Sets the converter used to convert the field value to property data
     * source type. The converter must have a presentation type that matches the
     * field type.
     *
     * @param converter The new converter to use.
     * @return This component
     * @see AbstractField#setConverter(com.vaadin.data.util.converter.Converter)
     */
    public C withConverter(Converter<T, ?> converter);

}
