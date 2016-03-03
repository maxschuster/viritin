package org.vaadin.viritin.fields;

import com.vaadin.data.Property;
import com.vaadin.data.Validator;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.event.ContextClickEvent;
import com.vaadin.event.ShortcutListener;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.ErrorMessage;
import com.vaadin.server.Resource;
import java.util.Locale;
import org.vaadin.viritin.util.HtmlElementPropertySetter;

/**
 * Special field type for inputting email address. The type of the field is just
 * basic string, but backing html field is of type "email", making virtual
 * keyboards on mobile devices to be better optimized for typing in email
 * addresses.
 *
 * @author Matti Tahvonen
 */
public class EmailField extends MTextField {

    public EmailField() {
    }

    public EmailField(String caption) {
        super(caption);
    }

    public EmailField(String caption, String value) {
        super(caption, value);
    }

    private HtmlElementPropertySetter heps;

    @Override
    public void beforeClientResponse(boolean initial) {
        super.beforeClientResponse(initial);
        if (heps == null) {
            heps = new HtmlElementPropertySetter(this);
        }
        heps.setProperty("type", "email");
    }

    @Override
    public EmailField  withValidator(Validator validator) {
        return (EmailField) super.withValidator(validator);
    }

    @Override
    public EmailField  withPropertyDataSource(Property newDataSource) {
        return (EmailField) super.withPropertyDataSource(newDataSource);
    }

    @Override
    public EmailField  withValue(String newValue) throws ReadOnlyException {
        return (EmailField) super.withValue(newValue);
    }

    @Override
    public EmailField  withValueChangeListener(ValueChangeListener listener) {
        return (EmailField) super.withValueChangeListener(listener);
    }

    @Override
    public EmailField  withRequiredError(String requiredMessage) {
        return (EmailField) super.withRequiredError(requiredMessage);
    }

    @Override
    public EmailField  withRequired(boolean required) {
        return (EmailField) super.withRequired(required);
    }

    @Override
    public EmailField  withConverter(Converter<String, ?> converter) {
        return (EmailField) super.withConverter(converter);
    }

    @Override
    public EmailField  withCurrentBufferedSourceException(SourceException currentBufferedSourceException) {
        return (EmailField) super.withCurrentBufferedSourceException(currentBufferedSourceException);
    }

    @Override
    public EmailField  withValidationVisible(boolean validateAutomatically) {
        return (EmailField) super.withValidationVisible(validateAutomatically);
    }

    @Override
    public EmailField  withConversionError(String valueConversionError) {
        return (EmailField) super.withConversionError(valueConversionError);
    }

    @Override
    public EmailField  withTabIndex(int tabIndex) {
        return (EmailField) super.withTabIndex(tabIndex);
    }

    @Override
    public EmailField  withInvalidAllowed(boolean invalidAllowed) throws UnsupportedOperationException {
        return (EmailField) super.withInvalidAllowed(invalidAllowed);
    }

    @Override
    public EmailField  withConvertedValue(Object value) {
        return (EmailField) super.withConvertedValue(value);
    }

    @Override
    public EmailField  withConverter(Class<?> datamodelType) {
        return (EmailField) super.withConverter(datamodelType);
    }

    @Override
    public EmailField  withBuffered(boolean buffered) {
        return (EmailField) super.withBuffered(buffered);
    }

    @Override
    public EmailField  withInvalidCommitted(boolean isCommitted) {
        return (EmailField) super.withInvalidCommitted(isCommitted);
    }

    @Override
    public EmailField  withHeightUndefined() {
        return (EmailField) super.withHeightUndefined();
    }

    @Override
    public EmailField  withWidthUndefined() {
        return (EmailField) super.withWidthUndefined();
    }

    @Override
    public EmailField  withSizeUndefined() {
        return (EmailField) super.withSizeUndefined();
    }

    @Override
    public EmailField  withHeightFull() {
        return (EmailField) super.withHeightFull();
    }

    @Override
    public EmailField  withWidthFull() {
        return (EmailField) super.withWidthFull();
    }

    @Override
    public EmailField  withSizeFull() {
        return (EmailField) super.withSizeFull();
    }

    @Override
    public EmailField  withWidth(float width, Unit unit) {
        return (EmailField) super.withWidth(width, unit);
    }

    @Override
    public EmailField  withWidth(String width) {
        return (EmailField) super.withWidth(width);
    }

    @Override
    public EmailField  withHeight(float height, Unit unit) {
        return (EmailField) super.withHeight(height, unit);
    }

    @Override
    public EmailField  withHeight(String height) {
        return (EmailField) super.withHeight(height);
    }

    @Override
    public EmailField  withContextClickListener(ContextClickEvent.ContextClickListener listener) {
        return (EmailField) super.withContextClickListener(listener);
    }

    @Override
    public EmailField  withErrorHandler(ErrorHandler errorHandler) {
        return (EmailField) super.withErrorHandler(errorHandler);
    }

    @Override
    public EmailField  withDetachListener(DetachListener listener) {
        return (EmailField) super.withDetachListener(listener);
    }

    @Override
    public EmailField  withAttachListener(AttachListener listener) {
        return (EmailField) super.withAttachListener(listener);
    }

    @Override
    public EmailField  withVisible(boolean visible) {
        return (EmailField) super.withVisible(visible);
    }

    @Override
    public EmailField  withStyleName(String... styles) {
        return (EmailField) super.withStyleName(styles);
    }

    @Override
    public EmailField  withReadOnly(boolean readOnly) {
        return (EmailField) super.withReadOnly(readOnly);
    }

    @Override
    public EmailField  withPrimaryStyleName(String style) {
        return (EmailField) super.withPrimaryStyleName(style);
    }

    @Override
    public EmailField  withId(String id) {
        return (EmailField) super.withId(id);
    }

    @Override
    public EmailField  withIcon(Resource icon) {
        return (EmailField) super.withIcon(icon);
    }

    @Override
    public EmailField  withEnabled(boolean enabled) {
        return (EmailField) super.withEnabled(enabled);
    }

    @Override
    public EmailField  withCaption(String caption) {
        return (EmailField) super.withCaption(caption);
    }

    @Override
    public EmailField  withShortcutListener(ShortcutListener shortcut) {
        return (EmailField) super.withShortcutListener(shortcut);
    }

    @Override
    public EmailField  withResponsive(boolean responsive) {
        return (EmailField) super.withResponsive(responsive);
    }

    @Override
    public EmailField  withData(Object data) {
        return (EmailField) super.withData(data);
    }

    @Override
    public EmailField  withListener(Listener listener) {
        return (EmailField) super.withListener(listener);
    }

    @Override
    public EmailField  withComponentError(ErrorMessage componentError) {
        return (EmailField) super.withComponentError(componentError);
    }

    @Override
    public EmailField  withDescription(String description) {
        return (EmailField) super.withDescription(description);
    }

    @Override
    public EmailField  withImmediate(boolean immediate) {
        return (EmailField) super.withImmediate(immediate);
    }

    @Override
    public EmailField  withLocale(Locale locale) {
        return (EmailField) super.withLocale(locale);
    }

    @Override
    public EmailField  withCaptionAsHtml(boolean captionAsHtml) {
        return (EmailField) super.withCaptionAsHtml(captionAsHtml);
    }

    @Override
    public EmailField  withStyleName(String style, boolean add) {
        return (EmailField) super.withStyleName(style, add);
    }

    @Override
    public EmailField  withAutoCorrectOff() {
        return (EmailField) super.withAutoCorrectOff();
    }

    @Override
    public EmailField  withAutoCapitalizeOff() {
        return (EmailField) super.withAutoCapitalizeOff();
    }

    @Override
    public EmailField  withAutocompleteOff() {
        return (EmailField) super.withAutocompleteOff();
    }

    @Override
    public EmailField  withSpellCheckOff() {
        return (EmailField) super.withSpellCheckOff();
    }

    @Override
    public EmailField  withNullRepresentation(String nullRepresentation) {
        return (EmailField) super.withNullRepresentation(nullRepresentation);
    }

    @Override
    public EmailField  withInputPrompt(String inputPrompt) {
        return (EmailField) super.withInputPrompt(inputPrompt);
    }

    @Override
    public EmailField  withFullWidth() {
        return (EmailField) super.withFullWidth();
    }

}
