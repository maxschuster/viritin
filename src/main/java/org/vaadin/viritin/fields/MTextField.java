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
import com.vaadin.data.util.converter.ConverterUtil;
import com.vaadin.event.ContextClickEvent;
import com.vaadin.event.FieldEvents;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.ShortcutListener;
import com.vaadin.server.AbstractErrorMessage;
import com.vaadin.server.CompositeErrorMessage;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.ErrorMessage;
import com.vaadin.server.Resource;
import com.vaadin.ui.TextField;
import java.util.EventObject;
import java.util.Locale;
import org.vaadin.viritin.fluency.ui.FluentAbstractTextField;
import org.vaadin.viritin.util.HtmlElementPropertySetter;

/**
 * A an extension to basic Vaadin TextField. Uses the only sane default for
 * "nullRepresentation" (""), adds support for "eager validation" (~ validate
 * while typing) and adds some fluent APIs.
 */
public class MTextField extends TextField implements EagerValidateable, 
        FluentAbstractTextField<MTextField> {

    private boolean eagerValidation = false;
    private boolean eagerValidationStatus;
    private String lastKnownTextChangeValue;
    private Validator.InvalidValueException eagerValidationError;
    private AutoComplete autocomplete;
    private AutoCapitalize autocapitalize;
    private AutoCorrect autocorrect;
    private Boolean spellcheck;

    public MTextField() {
        configureMaddonStuff();
    }

    private void configureMaddonStuff() {
        setNullRepresentation("");
    }

    public MTextField(String caption) {
        super(caption);
        configureMaddonStuff();
    }

    public MTextField(Property dataSource) {
        super(dataSource);
        configureMaddonStuff();
    }

    public MTextField(String caption, Property dataSource) {
        super(caption, dataSource);
        configureMaddonStuff();
    }

    public MTextField(String caption, String value) {
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

    public MTextField withFullWidth() {
        return withWidthFull();
    }

    public void setSpellcheck(Boolean spellcheck) {
        this.spellcheck = spellcheck;
    }

    public Boolean getSpellcheck() {
        return spellcheck;
    }

    public MTextField withSpellCheckOff() {
        setSpellcheck(false);
        return this;
    }

    public enum AutoComplete {
        on, off
    }

    public enum AutoCorrect {
        on, off
    }

    public enum AutoCapitalize {
        on, off
    }
    
    

    public MTextField withAutocompleteOff() {
        return setAutocomplete(AutoComplete.off);
    }

    public MTextField setAutocomplete(AutoComplete autocomplete) {
        this.autocomplete = autocomplete;
        return this;
    }

    public AutoComplete getAutocomplete() {
        return autocomplete;
    }

    public MTextField withAutoCapitalizeOff() {
        return setAutoCapitalize(AutoCapitalize.off);
    }

    public MTextField setAutoCapitalize(AutoCapitalize autoCapitalize) {
        this.autocapitalize = autoCapitalize;
        return this;
    }

    public AutoCapitalize getAutoCapitalize() {
        return autocapitalize;
    }

    public MTextField withAutoCorrectOff() {
        return setAutoCorrect(AutoCorrect.off);
    }

    public MTextField setAutoCorrect(AutoCorrect autoCorrect) {
        this.autocorrect = autoCorrect;
        return this;
    }

    public AutoCorrect getAutoCorrect() {
        return autocorrect;
    }

    private HtmlElementPropertySetter heps;

    protected HtmlElementPropertySetter getHtmlElementPropertySetter() {
        if (heps == null) {
            heps = new HtmlElementPropertySetter(this);
        }
        return heps;
    }

    @Override
    public void beforeClientResponse(boolean initial) {
        super.beforeClientResponse(initial);
        if (initial) {
            if(spellcheck != null) {
                getHtmlElementPropertySetter().setProperty(
                        "spellcheck", spellcheck);
            }
            if (autocomplete != null) {
                // sending here to keep value if toggling visibility
                getHtmlElementPropertySetter().setProperty("autocomplete",
                        autocomplete.toString());
            }
            if (autocorrect != null) {
                // sending here to keep value if toggling visibility
                getHtmlElementPropertySetter().setProperty("autocorrect",
                        autocorrect.toString());
            }
            if (autocapitalize != null) {
                // sending here to keep value if toggling visibility
                getHtmlElementPropertySetter().setProperty("autocapitalize",
                        autocapitalize.toString());
            }
        }
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
            // Also eagerly pass content to backing bean to make top level 
            // validation eager, but do not listen the value back in value change
            // event
            if (getPropertyDataSource() != null) {
                skipValueChangeEvent = true;
                Object convertedValue = ConverterUtil.convertToModel(
                        getLastKnownTextContent(), getPropertyDataSource().
                        getType(), getConverter(),
                        getLocale());
                getPropertyDataSource().setValue(convertedValue);
                skipValueChangeEvent = false;
            }
        } catch (Validator.InvalidValueException e) {
            eagerValidationError = e;
            eagerValidationStatus = false;
            markAsDirty();
        }
    }

    private boolean skipValueChangeEvent = false;

    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        if (!skipValueChangeEvent) {
            super.valueChange(event);
        } else {
            skipValueChangeEvent = false;
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
    public MTextField withInputPrompt(String inputPrompt) {
        setInputPrompt(inputPrompt);
        return this;
    }

    @Override
    public MTextField withNullRepresentation(String nullRepresentation) {
        setNullRepresentation(nullRepresentation);
        return this;
    }

    @Override
    public MTextField withStyleName(String style, boolean add) {
        setStyleName(style, add);
        return this;
    }

    @Override
    public MTextField withCaptionAsHtml(boolean captionAsHtml) {
        setCaptionAsHtml(captionAsHtml);
        return this;
    }

    @Override
    public MTextField withLocale(Locale locale) {
        setLocale(locale);
        return this;
    }

    @Override
    public MTextField withImmediate(boolean immediate) {
        setImmediate(immediate);
        return this;
    }

    @Override
    public MTextField withDescription(String description) {
        setDescription(description);
        return this;
    }

    @Override
    public MTextField withComponentError(ErrorMessage componentError) {
        setComponentError(componentError);
        return this;
    }

    @Override
    public MTextField withListener(Listener listener) {
        addListener(listener);
        return this;
    }

    @Override
    public MTextField withData(Object data) {
        setData(data);
        return this;
    }

    @Override
    public MTextField withResponsive(boolean responsive) {
        setResponsive(responsive);
        return this;
    }

    @Override
    public MTextField withShortcutListener(ShortcutListener shortcut) {
        addShortcutListener(shortcut);
        return this;
    }

    @Override
    public MTextField withCaption(String caption) {
        setCaption(caption);
        return this;
    }

    @Override
    public MTextField withEnabled(boolean enabled) {
        setEnabled(enabled);
        return this;
    }

    @Override
    public MTextField withIcon(Resource icon) {
        setIcon(icon);
        return this;
    }

    @Override
    public MTextField withId(String id) {
        setId(id);
        return this;
    }

    @Override
    public MTextField withPrimaryStyleName(String style) {
        setPrimaryStyleName(style);
        return this;
    }

    @Override
    public MTextField withReadOnly(boolean readOnly) {
        setReadOnly(readOnly);
        return this;
    }

    @Override
    public MTextField withStyleName(String... styles) {
        for (String style : styles) {
            addStyleName(style);
        }
        return this;
    }

    @Override
    public MTextField withVisible(boolean visible) {
        setVisible(visible);
        return this;
    }

    @Override
    public MTextField withAttachListener(AttachListener listener) {
        addAttachListener(listener);
        return this;
    }

    @Override
    public MTextField withDetachListener(DetachListener listener) {
        addDetachListener(listener);
        return this;
    }

    @Override
    public MTextField withErrorHandler(ErrorHandler errorHandler) {
        setErrorHandler(errorHandler);
        return this;
    }

    @Override
    public MTextField withContextClickListener(ContextClickEvent.ContextClickListener listener) {
        addContextClickListener(listener);
        return this;
    }

    @Override
    public MTextField withHeight(String height) {
        setHeight(height);
        return this;
    }

    @Override
    public MTextField withHeight(float height, Unit unit) {
        setHeight(height, unit);
        return this;
    }

    @Override
    public MTextField withWidth(String width) {
        setWidth(width);
        return this;
    }

    @Override
    public MTextField withWidth(float width, Unit unit) {
        setWidth(width, unit);
        return this;
    }

    @Override
    public MTextField withSizeFull() {
        setSizeFull();
        return this;
    }
    
    @Override
    public MTextField withWidthFull() {
        setWidth(100, Unit.PERCENTAGE);
        return this;
    }

    @Override
    public MTextField withHeightFull() {
        setHeight(100, Unit.PERCENTAGE);
        return this;
    }

    @Override
    public MTextField withSizeUndefined() {
        setSizeUndefined();
        return this;
    }

    @Override
    public MTextField withWidthUndefined() {
        setWidthUndefined();
        return this;
    }

    @Override
    public MTextField withHeightUndefined() {
        setHeightUndefined();
        return this;
    }
    
    /* Fluent setters (FluentAbstractField): */

    @Override
    public MTextField withInvalidCommitted(boolean isCommitted) {
        setInvalidCommitted(isCommitted);
        return this;
    }

    @Override
    public MTextField withBuffered(boolean buffered) {
        setBuffered(buffered);
        return this;
    }

    @Override
    public MTextField withConverter(Class<?> datamodelType) {
        setConverter(datamodelType);
        return this;
    }

    @Override
    public MTextField withConvertedValue(Object value) {
        setConvertedValue(value);
        return this;
    }

    @Override
    public MTextField withInvalidAllowed(boolean invalidAllowed) throws UnsupportedOperationException {
        setInvalidAllowed(invalidAllowed);
        return this;
    }

    @Override
    public MTextField withTabIndex(int tabIndex) {
        setTabIndex(tabIndex);
        return this;
    }

    @Override
    public MTextField withConversionError(String valueConversionError) {
        setConversionError(valueConversionError);
        return this;
    }

    @Override
    public MTextField withValidationVisible(boolean validateAutomatically) {
        setValidationVisible(validateAutomatically);
        return this;
    }

    @Override
    public MTextField withCurrentBufferedSourceException(SourceException currentBufferedSourceException) {
        setCurrentBufferedSourceException(currentBufferedSourceException);
        return this;
    }

    @Override
    public MTextField withConverter(Converter<String, ?> converter) {
        setConverter(converter);
        return this;
    }

    @Override
    public MTextField withRequired(boolean required) {
        setRequired(required);
        return this;
    }

    @Override
    public MTextField withRequiredError(String requiredMessage) {
        setRequiredError(requiredMessage);
        return this;
    }

    @Override
    public MTextField withValueChangeListener(ValueChangeListener listener) {
        addValueChangeListener(listener);
        return this;
    }

    @Override
    public MTextField withValue(String newValue) throws ReadOnlyException {
        setValue(newValue);
        return this;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public MTextField withPropertyDataSource(Property newDataSource) {
        setPropertyDataSource(newDataSource);
        return this;
    }

    @Override
    public MTextField withValidator(Validator validator) {
        setImmediate(true);
        addValidator(validator);
        return this;   
    }

    @Override
    public MTextField withReadOnlyStatusChangeListener(ReadOnlyStatusChangeListener listener) {
        addReadOnlyStatusChangeListener(listener);
        return this;
    }
    
    /* Fluent setters (FluentAbstractTextField): */
    
    @Override
    public MTextField withNullSettingAllowed(boolean nullSettingAllowed) {
        setNullSettingAllowed(true);
        return this;
    }

    @Override
    public MTextField withMaxLength(int maxLength) {
        setMaxLength(maxLength);
        return this;
    }

    @Override
    public MTextField withColumns(int columns) {
        setColumns(columns);
        return this;
    }

    @Override
    public MTextField withTextChangeEventMode(TextChangeEventMode inputEventMode) {
        setTextChangeEventMode(inputEventMode);
        return this;
    }

    @Override
    public MTextField withTextChangeTimeout(int timeout) {
        setTextChangeTimeout(timeout);
        return this;
    }

    @Override
    public MTextField withSelectionRange(int pos, int length) {
        setSelectionRange(pos, length);
        return this;
    }

    @Override
    public MTextField withCursorPosition(int pos) {
        setCursorPosition(pos);
        return this;
    }

    @Override
    public MTextField withBlurListener(FieldEvents.BlurListener listener) {
        addBlurListener(listener);
        return this;
    }

    @Override
    public MTextField withFocusListener(FieldEvents.FocusListener listener) {
        addFocusListener(listener);
        return this;
    }

    @Override
    public MTextField withTextChangeListener(FieldEvents.TextChangeListener listener) {
        addTextChangeListener(listener);
        return this;
    }
    
}
