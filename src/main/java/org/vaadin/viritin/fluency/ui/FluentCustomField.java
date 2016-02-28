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

import com.vaadin.data.Property;
import com.vaadin.data.Validator;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.event.ContextClickEvent;
import com.vaadin.event.ShortcutListener;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.ErrorMessage;
import com.vaadin.server.Resource;
import com.vaadin.ui.CustomField;
import java.util.Locale;

/**
 * A {@link CustomField} complemented by fluent setters.
 * 
 * @author Max Schuster
 * @param <C> Fluent component type
 * @param <T> The type of values in the field, which might not be the same type
 * as that of the data source if converters are used
 * @see CustomField
 */
@SuppressWarnings("unchecked")
public abstract class FluentCustomField<C extends FluentCustomField<C, T>, T> extends
        CustomField<T> implements FluentAbstractField<C, T> {
    
    /* Fluent setters (FluentAbstractComponent): */

    @Override
    public C withStyleName(String style, boolean add) {
        setStyleName(style, add);
        return (C) this;
    }

    @Override
    public C withCaptionAsHtml(boolean captionAsHtml) {
        setCaptionAsHtml(captionAsHtml);
        return (C) this;
    }

    @Override
    public C withLocale(Locale locale) {
        setLocale(locale);
        return (C) this;
    }

    @Override
    public C withImmediate(boolean immediate) {
        setImmediate(immediate);
        return (C) this;
    }

    @Override
    public C withDescription(String description) {
        setDescription(description);
        return (C) this;
    }

    @Override
    public C withComponentError(ErrorMessage componentError) {
        setComponentError(componentError);
        return (C) this;
    }

    @Override
    public C withListener(Listener listener) {
        addListener(listener);
        return (C) this;
    }

    @Override
    public C withData(Object data) {
        setData(data);
        return (C) this;
    }

    @Override
    public C withResponsive(boolean responsive) {
        setResponsive(responsive);
        return (C) this;
    }

    @Override
    public C withShortcutListener(ShortcutListener shortcut) {
        addShortcutListener(shortcut);
        return (C) this;
    }

    @Override
    public C withCaption(String caption) {
        setCaption(caption);
        return (C) this;
    }

    @Override
    public C withEnabled(boolean enabled) {
        setEnabled(enabled);
        return (C) this;
    }

    @Override
    public C withIcon(Resource icon) {
        setIcon(icon);
        return (C) this;
    }

    @Override
    public C withId(String id) {
        setId(id);
        return (C) this;
    }

    @Override
    public C withPrimaryStyleName(String style) {
        setPrimaryStyleName(style);
        return (C) this;
    }

    @Override
    public C withReadOnly(boolean readOnly) {
        setReadOnly(readOnly);
        return (C) this;
    }

    @Override
    public C withStyleName(String style) {
        setStyleName(style);
        return (C) this;
    }

    @Override
    public C withVisible(boolean visible) {
        setVisible(visible);
        return (C) this;
    }

    @Override
    public C withAttachListener(AttachListener listener) {
        addAttachListener(listener);
        return (C) this;
    }

    @Override
    public C withDetachListener(DetachListener listener) {
        addDetachListener(listener);
        return (C) this;
    }

    @Override
    public C withErrorHandler(ErrorHandler errorHandler) {
        setErrorHandler(errorHandler);
        return (C) this;
    }

    @Override
    public C withContextClickListener(ContextClickEvent.ContextClickListener listener) {
        addContextClickListener(listener);
        return (C) this;
    }

    @Override
    public C withHeight(String height) {
        setHeight(height);
        return (C) this;
    }

    @Override
    public C withHeight(float height, Unit unit) {
        setHeight(height, unit);
        return (C) this;
    }

    @Override
    public C withWidth(String width) {
        setWidth(width);
        return (C) this;
    }

    @Override
    public C withWidth(float width, Unit unit) {
        setWidth(width, unit);
        return (C) this;
    }

    @Override
    public C withSizeFull() {
        setSizeFull();
        return (C) this;
    }
    
    @Override
    public C withWidthFull() {
        setWidth(100, Unit.PERCENTAGE);
        return (C) this;
    }

    @Override
    public C withHeightFull() {
        setHeight(100, Unit.PERCENTAGE);
        return (C) this;
    }

    @Override
    public C withSizeUndefined() {
        setSizeUndefined();
        return (C) this;
    }

    @Override
    public C withWidthUndefined() {
        setWidthUndefined();
        return (C) this;
    }

    @Override
    public C withHeightUndefined() {
        setHeightUndefined();
        return (C) this;
    }
    
    /* Fluent setters (FluentAbstractField): */

    @Override
    public C withInvalidCommitted(boolean isCommitted) {
        setInvalidCommitted(isCommitted);
        return (C) this;
    }

    @Override
    public C withBuffered(boolean buffered) {
        setBuffered(buffered);
        return (C) this;
    }

    @Override
    public C withConverter(Class<?> datamodelType) {
        setConverter(datamodelType);
        return (C) this;
    }

    @Override
    public C withConvertedValue(Object value) {
        setConvertedValue(value);
        return (C) this;
    }

    @Override
    public C withInvalidAllowed(boolean invalidAllowed) throws UnsupportedOperationException {
        setInvalidAllowed(invalidAllowed);
        return (C) this;
    }

    @Override
    public C withTabIndex(int tabIndex) {
        setTabIndex(tabIndex);
        return (C) this;
    }

    @Override
    public C withConversionError(String valueConversionError) {
        setConversionError(valueConversionError);
        return (C) this;
    }

    @Override
    public C withValidationVisible(boolean validateAutomatically) {
        setValidationVisible(validateAutomatically);
        return (C) this;
    }

    @Override
    public C withCurrentBufferedSourceException(SourceException currentBufferedSourceException) {
        setCurrentBufferedSourceException(currentBufferedSourceException);
        return (C) this;
    }

    @Override
    public C withConverter(Converter<T, ?> converter) {
        setConverter(converter);
        return (C) this;
    }

    @Override
    public C withRequired(boolean required) {
        setRequired(required);
        return (C) this;
    }

    @Override
    public C withRequiredError(String requiredMessage) {
        setRequiredError(requiredMessage);
        return (C) this;
    }

    @Override
    public C withValueChangeListener(ValueChangeListener listener) {
        addValueChangeListener(listener);
        return (C) this;
    }

    @Override
    public C withValue(T newValue) throws ReadOnlyException {
        setValue(newValue);
        return (C) this;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public C withPropertyDataSource(Property newDataSource) {
        setPropertyDataSource(newDataSource);
        return (C) this;
    }

    @Override
    public C withValidator(Validator validator) {
        addValidator(validator);
        return (C) this;
    }

    @Override
    public C withReadOnlyStatusChangeListener(ReadOnlyStatusChangeListener listener) {
        addReadOnlyStatusChangeListener(listener);
        return (C) this;
    }
    
}
