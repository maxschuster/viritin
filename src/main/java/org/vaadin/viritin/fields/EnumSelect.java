package org.vaadin.viritin.fields;


import com.vaadin.data.Property;
import com.vaadin.data.Validator;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.event.ContextClickEvent;
import com.vaadin.event.ShortcutListener;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.ErrorMessage;
import com.vaadin.server.Resource;
import com.vaadin.ui.NativeSelect;
import java.util.Collection;
import java.util.Locale;

public class EnumSelect<T> extends TypedSelect<T> {

    public EnumSelect() {
        withSelectType(NativeSelect.class);
        setWidthUndefined();
    }

    public EnumSelect(String caption) {
        super(caption);
    }

    @Override
    public void setPropertyDataSource(Property newDataSource) {
        if (newDataSource != null) {
            Class<T> type = newDataSource.getType();
            setOptions(type.getEnumConstants());
        }
        super.setPropertyDataSource(newDataSource);
    }

    @Override
    public EnumSelect setBeans(Collection options) {
        return (EnumSelect) super.setBeans(options);
    }

    @Override
    public EnumSelect setFieldType(Class type) {
        return (EnumSelect) super.setFieldType(type);
    }

    @Override
    public EnumSelect withWidth(String width) {
        return (EnumSelect) super.withWidth(width);
    }

    @Override
    public EnumSelect withWidth(float width, Unit unit) {
        return (EnumSelect) super.withWidth(width, unit);
    }

    @Override
    public EnumSelect withValidator(Validator validator) {
        return (EnumSelect) super.withValidator(validator);
    }

    @Override
    public TypedSelect withReadOnly(boolean readOnly) {
        return super.withReadOnly(readOnly);
    }

    @Override
    public EnumSelect withFullWidth() {
        return (EnumSelect) super.withFullWidth();
    }

    @Override
    public EnumSelect addMValueChangeListener(MValueChangeListener listener) {
        return (EnumSelect) super.addMValueChangeListener(listener);
    }

    @Override
    public EnumSelect withSelectType(Class selectType) {
        return (EnumSelect) super.withSelectType(selectType);
    }

    @Override
    public EnumSelect withCaption(String caption) {
        return (EnumSelect) super.withCaption(caption);
    }

    public EnumSelect withNullSelection(boolean allowNullSelection) {
        getSelect().setNullSelectionAllowed(allowNullSelection);
        return this;
    }

    @Override
    public EnumSelect<T> withPropertyDataSource(Property newDataSource) {
        return (EnumSelect<T>) super.withPropertyDataSource(newDataSource);
    }

    @Override
    public EnumSelect<T> withValue(Object newValue) throws ReadOnlyException {
        return (EnumSelect<T>) super.withValue(newValue);
    }

    @Override
    public EnumSelect<T> withValueChangeListener(ValueChangeListener listener) {
        return (EnumSelect<T>) super.withValueChangeListener(listener);
    }

    @Override
    public EnumSelect<T> withRequiredError(String requiredMessage) {
        return (EnumSelect<T>) super.withRequiredError(requiredMessage);
    }

    @Override
    public EnumSelect<T> withRequired(boolean required) {
        return (EnumSelect<T>) super.withRequired(required);
    }

    @Override
    public EnumSelect<T> withConverter(Converter converter) {
        return (EnumSelect<T>) super.withConverter(converter);
    }

    @Override
    public EnumSelect<T> withCurrentBufferedSourceException(SourceException currentBufferedSourceException) {
        return (EnumSelect<T>) super.withCurrentBufferedSourceException(currentBufferedSourceException);
    }

    @Override
    public EnumSelect<T> withValidationVisible(boolean validateAutomatically) {
        return (EnumSelect<T>) super.withValidationVisible(validateAutomatically);
    }

    @Override
    public EnumSelect<T> withConversionError(String valueConversionError) {
        return (EnumSelect<T>) super.withConversionError(valueConversionError);
    }

    @Override
    public EnumSelect<T> withTabIndex(int tabIndex) {
        return (EnumSelect<T>) super.withTabIndex(tabIndex);
    }

    @Override
    public EnumSelect<T> withInvalidAllowed(boolean invalidAllowed) throws UnsupportedOperationException {
        return (EnumSelect<T>) super.withInvalidAllowed(invalidAllowed);
    }

    @Override
    public EnumSelect<T> withConvertedValue(Object value) {
        return (EnumSelect<T>) super.withConvertedValue(value);
    }

    @Override
    public EnumSelect<T> withConverter(Class datamodelType) {
        return (EnumSelect<T>) super.withConverter(datamodelType);
    }

    @Override
    public EnumSelect<T> withBuffered(boolean buffered) {
        return (EnumSelect<T>) super.withBuffered(buffered);
    }

    @Override
    public EnumSelect<T> withInvalidCommitted(boolean isCommitted) {
        return (EnumSelect<T>) super.withInvalidCommitted(isCommitted);
    }

    @Override
    public EnumSelect<T> withHeightUndefined() {
        return (EnumSelect<T>) super.withHeightUndefined();
    }

    @Override
    public EnumSelect<T> withWidthUndefined() {
        return (EnumSelect<T>) super.withWidthUndefined();
    }

    @Override
    public EnumSelect<T> withSizeUndefined() {
        return (EnumSelect<T>) super.withSizeUndefined();
    }

    @Override
    public EnumSelect<T> withHeightFull() {
        return (EnumSelect<T>) super.withHeightFull();
    }

    @Override
    public EnumSelect<T> withWidthFull() {
        return (EnumSelect<T>) super.withWidthFull();
    }

    @Override
    public EnumSelect<T> withSizeFull() {
        return (EnumSelect<T>) super.withSizeFull();
    }

    @Override
    public EnumSelect<T> withHeight(float height, Unit unit) {
        return (EnumSelect<T>) super.withHeight(height, unit);
    }

    @Override
    public EnumSelect<T> withHeight(String height) {
        return (EnumSelect<T>) super.withHeight(height);
    }

    @Override
    public EnumSelect<T> withContextClickListener(ContextClickEvent.ContextClickListener listener) {
        return (EnumSelect<T>) super.withContextClickListener(listener);
    }

    @Override
    public EnumSelect<T> withErrorHandler(ErrorHandler errorHandler) {
        return (EnumSelect<T>) super.withErrorHandler(errorHandler);
    }

    @Override
    public EnumSelect<T> withDetachListener(DetachListener listener) {
        return (EnumSelect<T>) super.withDetachListener(listener);
    }

    @Override
    public EnumSelect<T> withAttachListener(AttachListener listener) {
        return (EnumSelect<T>) super.withAttachListener(listener);
    }

    @Override
    public EnumSelect<T> withVisible(boolean visible) {
        return (EnumSelect<T>) super.withVisible(visible);
    }

    @Override
    public EnumSelect<T> withStyleName(String... styles) {
        return (EnumSelect<T>) super.withStyleName(styles);
    }

    @Override
    public EnumSelect<T> withPrimaryStyleName(String style) {
        return (EnumSelect<T>) super.withPrimaryStyleName(style);
    }

    @Override
    public EnumSelect<T> withId(String id) {
        return (EnumSelect<T>) super.withId(id);
    }

    @Override
    public EnumSelect<T> withIcon(Resource icon) {
        return (EnumSelect<T>) super.withIcon(icon);
    }

    @Override
    public EnumSelect<T> withEnabled(boolean enabled) {
        return (EnumSelect<T>) super.withEnabled(enabled);
    }

    @Override
    public EnumSelect<T> withShortcutListener(ShortcutListener shortcut) {
        return (EnumSelect<T>) super.withShortcutListener(shortcut);
    }

    @Override
    public EnumSelect<T> withResponsive(boolean responsive) {
        return (EnumSelect<T>) super.withResponsive(responsive);
    }

    @Override
    public EnumSelect<T> withData(Object data) {
        return (EnumSelect<T>) super.withData(data);
    }

    @Override
    public EnumSelect<T> withListener(Listener listener) {
        return (EnumSelect<T>) super.withListener(listener);
    }

    @Override
    public EnumSelect<T> withComponentError(ErrorMessage componentError) {
        return (EnumSelect<T>) super.withComponentError(componentError);
    }

    @Override
    public EnumSelect<T> withDescription(String description) {
        return (EnumSelect<T>) super.withDescription(description);
    }

    @Override
    public EnumSelect<T> withImmediate(boolean immediate) {
        return (EnumSelect<T>) super.withImmediate(immediate);
    }

    @Override
    public EnumSelect<T> withLocale(Locale locale) {
        return (EnumSelect<T>) super.withLocale(locale);
    }

    @Override
    public EnumSelect<T> withCaptionAsHtml(boolean captionAsHtml) {
        return (EnumSelect<T>) super.withCaptionAsHtml(captionAsHtml);
    }

    @Override
    public EnumSelect<T> withStyleName(String style, boolean add) {
        return (EnumSelect<T>) super.withStyleName(style, add);
    }
    
}
