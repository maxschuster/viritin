/*
 * Copyright 2014 mattitahvonenitmill.
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
package org.vaadin.viritin.fields;

import com.vaadin.data.Property;
import com.vaadin.data.Validator;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.event.ContextClickEvent;
import com.vaadin.event.FieldEvents;
import com.vaadin.event.ShortcutListener;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.ErrorMessage;
import com.vaadin.server.Resource;
import com.vaadin.ui.TextArea;
import java.util.Locale;
import org.vaadin.viritin.fluency.ui.FluentAbstractTextField;

/**
 *
 * @author mattitahvonenitmill
 */
public class MTextArea extends TextArea implements
        FluentAbstractTextField<MTextArea> {

    public MTextArea() {
        configureMaddonStuff();
    }

    private void configureMaddonStuff() {
        setNullRepresentation("");
        setWidth("100%");
    }

    public MTextArea(String caption) {
        super(caption);
        configureMaddonStuff();
    }

    public MTextArea(Property dataSource) {
        super(dataSource);
        configureMaddonStuff();
    }

    public MTextArea(String caption, Property dataSource) {
        super(caption, dataSource);
        configureMaddonStuff();
    }

    public MTextArea(String caption, String value) {
        super(caption, value);
    }

    public MTextArea withRows(int rows) {
        setRows(rows);
        return this;
    }

    public MTextArea withFullWidth() {
        setWidth("100%");
        return this;
    }

    public MTextArea withWordwrap(boolean wordwrap) {
        setWordwrap(wordwrap);
        return this;
    }
    
    /* Fluent setters (FluentAbstractComponent): */

    @Override
    public MTextArea withInputPrompt(String inputPrompt) {
        setInputPrompt(inputPrompt);
        return this;
    }

    @Override
    public MTextArea withNullRepresentation(String nullRepresentation) {
        setNullRepresentation(nullRepresentation);
        return this;
    }

    @Override
    public MTextArea withStyleName(String style, boolean add) {
        setStyleName(style, add);
        return this;
    }

    @Override
    public MTextArea withCaptionAsHtml(boolean captionAsHtml) {
        setCaptionAsHtml(captionAsHtml);
        return this;
    }

    @Override
    public MTextArea withLocale(Locale locale) {
        setLocale(locale);
        return this;
    }

    @Override
    public MTextArea withImmediate(boolean immediate) {
        setImmediate(immediate);
        return this;
    }

    @Override
    public MTextArea withDescription(String description) {
        setDescription(description);
        return this;
    }

    @Override
    public MTextArea withComponentError(ErrorMessage componentError) {
        setComponentError(componentError);
        return this;
    }

    @Override
    public MTextArea withListener(Listener listener) {
        addListener(listener);
        return this;
    }

    @Override
    public MTextArea withData(Object data) {
        setData(data);
        return this;
    }

    @Override
    public MTextArea withResponsive(boolean responsive) {
        setResponsive(responsive);
        return this;
    }

    @Override
    public MTextArea withShortcutListener(ShortcutListener shortcut) {
        addShortcutListener(shortcut);
        return this;
    }

    @Override
    public MTextArea withCaption(String caption) {
        setCaption(caption);
        return this;
    }

    @Override
    public MTextArea withEnabled(boolean enabled) {
        setEnabled(enabled);
        return this;
    }

    @Override
    public MTextArea withIcon(Resource icon) {
        setIcon(icon);
        return this;
    }

    @Override
    public MTextArea withId(String id) {
        setId(id);
        return this;
    }

    @Override
    public MTextArea withPrimaryStyleName(String style) {
        setPrimaryStyleName(style);
        return this;
    }

    @Override
    public MTextArea withReadOnly(boolean readOnly) {
        setReadOnly(readOnly);
        return this;
    }

    @Override
    public MTextArea withStyleName(String... styles) {
        for (String style : styles) {
            addStyleName(style);
        }
        return this;
    }

    @Override
    public MTextArea withVisible(boolean visible) {
        setVisible(visible);
        return this;
    }

    @Override
    public MTextArea withAttachListener(AttachListener listener) {
        addAttachListener(listener);
        return this;
    }

    @Override
    public MTextArea withDetachListener(DetachListener listener) {
        addDetachListener(listener);
        return this;
    }

    @Override
    public MTextArea withErrorHandler(ErrorHandler errorHandler) {
        setErrorHandler(errorHandler);
        return this;
    }

    @Override
    public MTextArea withContextClickListener(ContextClickEvent.ContextClickListener listener) {
        addContextClickListener(listener);
        return this;
    }

    @Override
    public MTextArea withHeight(String height) {
        setHeight(height);
        return this;
    }

    @Override
    public MTextArea withHeight(float height, Unit unit) {
        setHeight(height, unit);
        return this;
    }

    @Override
    public MTextArea withWidth(String width) {
        setWidth(width);
        return this;
    }

    @Override
    public MTextArea withWidth(float width, Unit unit) {
        setWidth(width, unit);
        return this;
    }

    @Override
    public MTextArea withSizeFull() {
        setSizeFull();
        return this;
    }
    
    @Override
    public MTextArea withWidthFull() {
        setWidth(100, Unit.PERCENTAGE);
        return this;
    }

    @Override
    public MTextArea withHeightFull() {
        setHeight(100, Unit.PERCENTAGE);
        return this;
    }

    @Override
    public MTextArea withSizeUndefined() {
        setSizeUndefined();
        return this;
    }

    @Override
    public MTextArea withWidthUndefined() {
        setWidthUndefined();
        return this;
    }

    @Override
    public MTextArea withHeightUndefined() {
        setHeightUndefined();
        return this;
    }
    
    /* Fluent setters (FluentAbstractField): */

    @Override
    public MTextArea withInvalidCommitted(boolean isCommitted) {
        setInvalidCommitted(isCommitted);
        return this;
    }

    @Override
    public MTextArea withBuffered(boolean buffered) {
        setBuffered(buffered);
        return this;
    }

    @Override
    public MTextArea withConverter(Class<?> datamodelType) {
        setConverter(datamodelType);
        return this;
    }

    @Override
    public MTextArea withConvertedValue(Object value) {
        setConvertedValue(value);
        return this;
    }

    @Override
    public MTextArea withInvalidAllowed(boolean invalidAllowed) throws UnsupportedOperationException {
        setInvalidAllowed(invalidAllowed);
        return this;
    }

    @Override
    public MTextArea withTabIndex(int tabIndex) {
        setTabIndex(tabIndex);
        return this;
    }

    @Override
    public MTextArea withConversionError(String valueConversionError) {
        setConversionError(valueConversionError);
        return this;
    }

    @Override
    public MTextArea withValidationVisible(boolean validateAutomatically) {
        setValidationVisible(validateAutomatically);
        return this;
    }

    @Override
    public MTextArea withCurrentBufferedSourceException(SourceException currentBufferedSourceException) {
        setCurrentBufferedSourceException(currentBufferedSourceException);
        return this;
    }

    @Override
    public MTextArea withConverter(Converter<String, ?> converter) {
        setConverter(converter);
        return this;
    }

    @Override
    public MTextArea withRequired(boolean required) {
        setRequired(required);
        return this;
    }

    @Override
    public MTextArea withRequiredError(String requiredMessage) {
        setRequiredError(requiredMessage);
        return this;
    }

    @Override
    public MTextArea withValueChangeListener(ValueChangeListener listener) {
        addValueChangeListener(listener);
        return this;
    }

    @Override
    public MTextArea withValue(String newValue) throws ReadOnlyException {
        setValue(newValue);
        return this;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public MTextArea withPropertyDataSource(Property newDataSource) {
        setPropertyDataSource(newDataSource);
        return this;
    }

    @Override
    public MTextArea withValidator(Validator validator) {
        setImmediate(true);
        addValidator(validator);
        return this;   
    }

    @Override
    public MTextArea withReadOnlyStatusChangeListener(ReadOnlyStatusChangeListener listener) {
        addReadOnlyStatusChangeListener(listener);
        return this;
    }
    
    /* Fluent setters (FluentAbstractTextField): */
    
    @Override
    public MTextArea withNullSettingAllowed(boolean nullSettingAllowed) {
        setNullSettingAllowed(true);
        return this;
    }

    @Override
    public MTextArea withMaxLength(int maxLength) {
        setMaxLength(maxLength);
        return this;
    }

    @Override
    public MTextArea withColumns(int columns) {
        setColumns(columns);
        return this;
    }

    @Override
    public MTextArea withTextChangeEventMode(TextChangeEventMode inputEventMode) {
        setTextChangeEventMode(inputEventMode);
        return this;
    }

    @Override
    public MTextArea withTextChangeTimeout(int timeout) {
        setTextChangeTimeout(timeout);
        return this;
    }

    @Override
    public MTextArea withSelectionRange(int pos, int length) {
        setSelectionRange(pos, length);
        return this;
    }

    @Override
    public MTextArea withCursorPosition(int pos) {
        setCursorPosition(pos);
        return this;
    }

    @Override
    public MTextArea withBlurListener(FieldEvents.BlurListener listener) {
        addBlurListener(listener);
        return this;
    }

    @Override
    public MTextArea withFocusListener(FieldEvents.FocusListener listener) {
        addFocusListener(listener);
        return this;
    }

    @Override
    public MTextArea withTextChangeListener(FieldEvents.TextChangeListener listener) {
        addTextChangeListener(listener);
        return this;
    }
    
}
