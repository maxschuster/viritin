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
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.ShortcutListener;
import com.vaadin.server.AbstractErrorMessage;
import com.vaadin.server.CompositeErrorMessage;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.ErrorMessage;
import com.vaadin.server.Resource;
import com.vaadin.ui.PasswordField;
import java.util.EventObject;
import java.util.Locale;
import org.vaadin.viritin.fluency.ui.FluentAbstractTextField;

/**
 * A an extension to basic Vaadin PasswordField. Uses the only sane default for
 * "nullRepresentation" (""), adds support for "eager validation" (~ validate
 * while typing) and adds some fluent APIs.
 */
public class MPasswordField extends PasswordField implements EagerValidateable, 
        FluentAbstractTextField<MPasswordField> {

    private boolean eagerValidation = false;
    private boolean eagerValidationStatus;
    private String lastKnownTextChangeValue;
    private Validator.InvalidValueException eagerValidationError;

    public MPasswordField() {
        configureMaddonStuff();
    }

    private void configureMaddonStuff() {
        setNullRepresentation("");
    }

    public MPasswordField(String caption) {
        super(caption);
        configureMaddonStuff();
    }

    public MPasswordField(Property dataSource) {
        super(dataSource);
        configureMaddonStuff();
    }

    public MPasswordField(String caption, Property dataSource) {
        super(caption, dataSource);
        configureMaddonStuff();
    }

    public MPasswordField(String caption, String value) {
        super(caption, value);
    }

    @Override
    protected void setValue(String newFieldValue, boolean repaintIsNotNeeded) throws ReadOnlyException, Converter.ConversionException, Validator.InvalidValueException {
        lastKnownTextChangeValue = null;
        eagerValidationError = null;
        super.setValue(newFieldValue, repaintIsNotNeeded);
    }

    @Override
    public boolean isEagerValidation() {
        return eagerValidation;
    }

    @Override
    public void setEagerValidation(boolean eagerValidation) {
        this.eagerValidation = eagerValidation;
    }

    @Override
    protected void fireEvent(EventObject event) {
        if (isEagerValidation() && event instanceof TextChangeEvent) {
            lastKnownTextChangeValue = ((TextChangeEvent) event).getText();
            doEagerValidation();
        }
        super.fireEvent(event);
    }

    /**
     *
     * @return the value of the field or if a text change event have sent a
     * value to the server since last value changes, then that.
     */
    public String getLastKnownTextContent() {
        return lastKnownTextChangeValue;
    }

    public MPasswordField withFullWidth() {
        return withWidthFull();
    }

    @Override
    public ErrorMessage getErrorMessage() {

        Validator.InvalidValueException validationError = getValidationError();

        final ErrorMessage superError = getComponentError();

        if (superError == null && validationError == null
                && getCurrentBufferedSourceException() == null) {
            return null;
        }
        // Throw combination of the error types
        return new CompositeErrorMessage(
                new ErrorMessage[]{
                    superError,
                    AbstractErrorMessage
                    .getErrorMessageForException(validationError),
                    AbstractErrorMessage
                    .getErrorMessageForException(
                            getCurrentBufferedSourceException())});
    }

    protected Validator.InvalidValueException getValidationError() {
        if (isEagerValidation() && lastKnownTextChangeValue != null) {
            return eagerValidationError;
        }
        /*
         * Check validation errors only if automatic validation is enabled.
         * Empty, required fields will generate a validation error containing
         * the requiredError string. For these fields the exclamation mark will
         * be hidden but the error must still be sent to the client.
         */
        Validator.InvalidValueException validationError = null;
        if (isValidationVisible()) {
            try {
                validate();
            } catch (Validator.InvalidValueException e) {
                if (!e.isInvisible()) {
                    validationError = e;
                }
            }
        }
        return validationError;
    }

    protected void doEagerValidation() {
        final boolean wasvalid = eagerValidationStatus;
        eagerValidationStatus = true;
        eagerValidationError = null;
        try {
            if (isRequired() && getLastKnownTextContent().isEmpty()) {
                throw new Validator.EmptyValueException(getRequiredError());
            }
            validate(getLastKnownTextContent());
            if (!wasvalid) {
                markAsDirty();
            }
        } catch (Validator.InvalidValueException e) {
            eagerValidationError = e;
            eagerValidationStatus = false;
            markAsDirty();
        }
    }

    @Override
    public boolean isValid() {
        if (isEagerValidation() && lastKnownTextChangeValue != null) {
            return eagerValidationStatus;
        } else {
            return super.isValid();
        }
    }

    @Override
    public void validate() throws Validator.InvalidValueException {
        if (isEagerValidation() && lastKnownTextChangeValue != null) {
            // This is most likely not executed, unless someone, for some weird 
            // reason calls this explicitly
            if (isRequired() && getLastKnownTextContent().isEmpty()) {
                throw new Validator.EmptyValueException(getRequiredError());
            }
            validate(getLastKnownTextContent());
        } else {
            super.validate();
        }
    }
    
    /* Fluent setters (FluentAbstractComponent): */

    @Override
    public MPasswordField withInputPrompt(String inputPrompt) {
        setInputPrompt(inputPrompt);
        return this;
    }

    @Override
    public MPasswordField withNullRepresentation(String nullRepresentation) {
        setNullRepresentation(nullRepresentation);
        return this;
    }

    @Override
    public MPasswordField withStyleName(String style, boolean add) {
        setStyleName(style, add);
        return this;
    }

    @Override
    public MPasswordField withCaptionAsHtml(boolean captionAsHtml) {
        setCaptionAsHtml(captionAsHtml);
        return this;
    }

    @Override
    public MPasswordField withLocale(Locale locale) {
        setLocale(locale);
        return this;
    }

    @Override
    public MPasswordField withImmediate(boolean immediate) {
        setImmediate(immediate);
        return this;
    }

    @Override
    public MPasswordField withDescription(String description) {
        setDescription(description);
        return this;
    }

    @Override
    public MPasswordField withComponentError(ErrorMessage componentError) {
        setComponentError(componentError);
        return this;
    }

    @Override
    public MPasswordField withListener(Listener listener) {
        addListener(listener);
        return this;
    }

    @Override
    public MPasswordField withData(Object data) {
        setData(data);
        return this;
    }

    @Override
    public MPasswordField withResponsive(boolean responsive) {
        setResponsive(responsive);
        return this;
    }

    @Override
    public MPasswordField withShortcutListener(ShortcutListener shortcut) {
        addShortcutListener(shortcut);
        return this;
    }

    @Override
    public MPasswordField withCaption(String caption) {
        setCaption(caption);
        return this;
    }

    @Override
    public MPasswordField withEnabled(boolean enabled) {
        setEnabled(enabled);
        return this;
    }

    @Override
    public MPasswordField withIcon(Resource icon) {
        setIcon(icon);
        return this;
    }

    @Override
    public MPasswordField withId(String id) {
        setId(id);
        return this;
    }

    @Override
    public MPasswordField withPrimaryStyleName(String style) {
        setPrimaryStyleName(style);
        return this;
    }

    @Override
    public MPasswordField withReadOnly(boolean readOnly) {
        setReadOnly(readOnly);
        return this;
    }

    @Override
    public MPasswordField withStyleName(String... styles) {
        for (String style : styles) {
            addStyleName(style);
        }
        return this;
    }

    @Override
    public MPasswordField withVisible(boolean visible) {
        setVisible(visible);
        return this;
    }

    @Override
    public MPasswordField withAttachListener(AttachListener listener) {
        addAttachListener(listener);
        return this;
    }

    @Override
    public MPasswordField withDetachListener(DetachListener listener) {
        addDetachListener(listener);
        return this;
    }

    @Override
    public MPasswordField withErrorHandler(ErrorHandler errorHandler) {
        setErrorHandler(errorHandler);
        return this;
    }

    @Override
    public MPasswordField withContextClickListener(ContextClickEvent.ContextClickListener listener) {
        addContextClickListener(listener);
        return this;
    }

    @Override
    public MPasswordField withHeight(String height) {
        setHeight(height);
        return this;
    }

    @Override
    public MPasswordField withHeight(float height, Unit unit) {
        setHeight(height, unit);
        return this;
    }

    @Override
    public MPasswordField withWidth(String width) {
        setWidth(width);
        return this;
    }

    @Override
    public MPasswordField withWidth(float width, Unit unit) {
        setWidth(width, unit);
        return this;
    }

    @Override
    public MPasswordField withSizeFull() {
        setSizeFull();
        return this;
    }
    
    @Override
    public MPasswordField withWidthFull() {
        setWidth(100, Unit.PERCENTAGE);
        return this;
    }

    @Override
    public MPasswordField withHeightFull() {
        setHeight(100, Unit.PERCENTAGE);
        return this;
    }

    @Override
    public MPasswordField withSizeUndefined() {
        setSizeUndefined();
        return this;
    }

    @Override
    public MPasswordField withWidthUndefined() {
        setWidthUndefined();
        return this;
    }

    @Override
    public MPasswordField withHeightUndefined() {
        setHeightUndefined();
        return this;
    }
    
    /* Fluent setters (FluentAbstractField): */

    @Override
    public MPasswordField withInvalidCommitted(boolean isCommitted) {
        setInvalidCommitted(isCommitted);
        return this;
    }

    @Override
    public MPasswordField withBuffered(boolean buffered) {
        setBuffered(buffered);
        return this;
    }

    @Override
    public MPasswordField withConverter(Class<?> datamodelType) {
        setConverter(datamodelType);
        return this;
    }

    @Override
    public MPasswordField withConvertedValue(Object value) {
        setConvertedValue(value);
        return this;
    }

    @Override
    public MPasswordField withInvalidAllowed(boolean invalidAllowed) throws UnsupportedOperationException {
        setInvalidAllowed(invalidAllowed);
        return this;
    }

    @Override
    public MPasswordField withTabIndex(int tabIndex) {
        setTabIndex(tabIndex);
        return this;
    }

    @Override
    public MPasswordField withConversionError(String valueConversionError) {
        setConversionError(valueConversionError);
        return this;
    }

    @Override
    public MPasswordField withValidationVisible(boolean validateAutomatically) {
        setValidationVisible(validateAutomatically);
        return this;
    }

    @Override
    public MPasswordField withCurrentBufferedSourceException(SourceException currentBufferedSourceException) {
        setCurrentBufferedSourceException(currentBufferedSourceException);
        return this;
    }

    @Override
    public MPasswordField withConverter(Converter<String, ?> converter) {
        setConverter(converter);
        return this;
    }

    @Override
    public MPasswordField withRequired(boolean required) {
        setRequired(required);
        return this;
    }

    @Override
    public MPasswordField withRequiredError(String requiredMessage) {
        setRequiredError(requiredMessage);
        return this;
    }

    @Override
    public MPasswordField withValueChangeListener(ValueChangeListener listener) {
        addValueChangeListener(listener);
        return this;
    }

    @Override
    public MPasswordField withValue(String newValue) throws ReadOnlyException {
        setValue(newValue);
        return this;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public MPasswordField withPropertyDataSource(Property newDataSource) {
        setPropertyDataSource(newDataSource);
        return this;
    }

    @Override
    public MPasswordField withValidator(Validator validator) {
        // setImmediate(true);
        addValidator(validator);
        return this;   
    }
    
    @Override
    public MPasswordField withReadOnlyStatusChangeListener(ReadOnlyStatusChangeListener listener) {
        addReadOnlyStatusChangeListener(listener);
        return this;
    }
    
    /* Fluent setters (FluentAbstractTextField): */
    
    @Override
    public MPasswordField withNullSettingAllowed(boolean nullSettingAllowed) {
        setNullSettingAllowed(true);
        return this;
    }

    @Override
    public MPasswordField withMaxLength(int maxLength) {
        setMaxLength(maxLength);
        return this;
    }

    @Override
    public MPasswordField withColumns(int columns) {
        setColumns(columns);
        return this;
    }

    @Override
    public MPasswordField withTextChangeEventMode(TextChangeEventMode inputEventMode) {
        setTextChangeEventMode(inputEventMode);
        return this;
    }

    @Override
    public MPasswordField withTextChangeTimeout(int timeout) {
        setTextChangeTimeout(timeout);
        return this;
    }

    @Override
    public MPasswordField withSelectionRange(int pos, int length) {
        setSelectionRange(pos, length);
        return this;
    }

    @Override
    public MPasswordField withCursorPosition(int pos) {
        setCursorPosition(pos);
        return this;
    }

    @Override
    public MPasswordField withBlurListener(FieldEvents.BlurListener listener) {
        addBlurListener(listener);
        return this;
    }

    @Override
    public MPasswordField withFocusListener(FieldEvents.FocusListener listener) {
        addFocusListener(listener);
        return this;
    }

    @Override
    public MPasswordField withTextChangeListener(FieldEvents.TextChangeListener listener) {
        addTextChangeListener(listener);
        return this;
    }

}
