package org.vaadin.viritin.fields;

import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.Validator;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.event.ContextClickEvent;
import com.vaadin.event.ShortcutListener;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.ErrorMessage;
import com.vaadin.server.Resource;
import com.vaadin.ui.*;
import org.vaadin.viritin.ListContainer;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import org.vaadin.viritin.fluency.ui.FluentAbstractField;

/**
 * A select implementation with better typed API than in core Vaadin.
 *
 * By default the options toString is used to generate the caption for option.
 * To override this behavior, use setCaptionGenerator or override getCaption(T)
 * to provide your own strategy.
 * <p>
 * Behind the scenes uses Vaadin cores NativeSelect (default) or other cores
 * AbstractSelect implementation, type provided in constructor. Tree and Table
 * are not supported, see MTable.
 * <p>
 * Note, that this select is always in single select mode. See MultiSelectTable
 * for a proper "multiselect".
 *
 * @author mstahv
 * @param <T> the type of selects value
 */
public class TypedSelect<T> extends CustomField implements FluentAbstractField {

    private CaptionGenerator<T> captionGenerator;

    private AbstractSelect select;

    private ListContainer<T> bic;

    private Class<T> fieldType;

    /**
     * The type of element options in the select
     *
     * @param type the type of options in the list
     */
    public TypedSelect(Class<T> type) {
        this.fieldType = type;
        bic = new ListContainer<T>(type);
    }

    /**
     * Note, that with this constructor, you cannot override the select type.
     *
     * @param options options to select from
     */
    public TypedSelect(T... options) {
        setOptions(options);
    }

    public TypedSelect(String caption) {
        setCaption(caption);
    }

    /**
     * {@inheritDoc}
     *
     * Sets the width of the wrapped select component.
     *
     * @param width the new width for this select
     * @param unit the unit of the new width
     */
    @Override
    public void setWidth(float width, Unit unit) {
        if (select != null) {
            select.setWidth(width, unit);
        }
        super.setWidth(width, unit);
    }

    @Override
    public void addStyleName(String style) {
        getSelect().addStyleName(style);
        super.addStyleName(style);
    }
    
    
    @Override
    public void removeStyleName(String style) {
    	getSelect().removeStyleName(style);
    	super.removeStyleName(style);
    }

    @Override
    public void setStyleName(String style) {
        getSelect().setStyleName(style);
        super.setStyleName(style);
    }

    /**
     * Note, that with this constructor, you cannot override the select type.
     *
     * @param caption the caption for the select
     * @param options available options for the select
     */
    public TypedSelect(String caption, Collection<T> options) {
        this(caption);
        setOptions(options);
    }

    public TypedSelect<T> withSelectType(
            Class<? extends AbstractSelect> selectType) {
        if (selectType == ListSelect.class) {
            setSelectInstance(new ListSelect() {
                @SuppressWarnings("unchecked")
                @Override
                public String getItemCaption(Object itemId) {
                    return TypedSelect.this.getCaption((T) itemId);
                }
            });
        } else if (selectType == OptionGroup.class) {
            setSelectInstance(new OptionGroup() {
                @SuppressWarnings("unchecked")
                @Override
                public String getItemCaption(Object itemId) {
                    return TypedSelect.this.getCaption((T) itemId);
                }
            });
        } else if (selectType == ComboBox.class) {
            setSelectInstance(new ComboBox() {
                @SuppressWarnings("unchecked")
                @Override
                public String getItemCaption(Object itemId) {
                    return TypedSelect.this.getCaption((T) itemId);
                }
            });
            LazyComboBox.fixComboBoxVaadinIssue16647((ComboBox) getSelect());
        } else if (selectType == TwinColSelect.class) {
            setSelectInstance(new TwinColSelect() {
                @SuppressWarnings("unchecked")
                @Override
                public String getItemCaption(Object itemId) {
                    return TypedSelect.this.getCaption((T) itemId);
                }
            });
        } else /*if (selectType == null || selectType == NativeSelect.class)*/ {
            setSelectInstance(new NativeSelect() {
                @SuppressWarnings("unchecked")
                @Override
                public String getItemCaption(Object itemId) {
                    return TypedSelect.this.getCaption((T) itemId);
                }
            });
        }
        return this;
    }

    protected void setSelectInstance(AbstractSelect select) {
        if (this.select != null) {
            piggyBackListener = null;
        }
        this.select = select;
    }

    /**
     *
     * @return the backing select instance, overriding this method may be
     * hazardous
     */
    protected AbstractSelect getSelect() {
        if (select == null) {
            withSelectType(null);
            if (bic != null) {
                select.setContainerDataSource(bic);
            }
        }
        ensurePiggybackListener();
        return select;
    }

    /**
     * Sets the input prompt used by the TypedSelect, in case the backing
     * instance supports inputprompt (read: is ComboBox).
     *
     * @param inputPrompt the input prompt
     * @return this TypedSelect instance
     */
    public TypedSelect<T> setInputPrompt(String inputPrompt) {
        if (getSelect() instanceof ComboBox) {
            ((ComboBox) getSelect()).setInputPrompt(inputPrompt);
        }
        return this;
    }

    protected String getCaption(T option) {
        if (captionGenerator != null) {
            return captionGenerator.getCaption(option);
        }
        return option.toString();
    }

    @Override
    public void focus() {
        getSelect().focus();
    }

    public final TypedSelect<T> setOptions(T... values) {
        return setOptions(Arrays.asList(values));
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class getType() {

        if (fieldType == null) {
            try {
                fieldType = (Class<T>) ((Container.Sortable) select
                        .getContainerDataSource()).firstItemId().getClass();
            } catch (Exception e) {
                // If field type isn't set or can't be detected just report
                // Object, should be fine in most cases (vaadin will just
                // assign value without conversion.
                return Object.class;
            }
        }
        return fieldType;
    }

    /**
     * Explicitly sets the element type of the select.
     *
     * @param type the type of options in the select
     * @return this typed select instance
     */
    public TypedSelect setType(Class<T> type) {
        this.fieldType = type;
        return this;
    }

    /**
     * Explicitly sets the element type of the select.
     *
     * @param type the type of options in the select
     * @return this typed select instance
     */
    public TypedSelect setFieldType(Class<T> type) {
        this.fieldType = type;
        return this;
    }

    @Override
    public void setInvalidCommitted(boolean isCommitted) {
        super.setInvalidCommitted(isCommitted);
        getSelect().setInvalidCommitted(isCommitted);
    }

    @Override
    public void commit() throws SourceException, InvalidValueException {
        getSelect().commit();
        super.commit();
    }

    @Override
    public void discard() throws SourceException {
        getSelect().discard();
        super.discard();
    }

    /**
     * Buffering probably doesn't work correctly for this field, but in general
     * you should never use buffering either.
     * https://vaadin.com/web/matti/blog/-/blogs/3-pro-tips-for-vaadin-developers
     *
     * @param buffered true if buffering should be on
     */
    @Override
    public void setBuffered(boolean buffered) {
        getSelect().setBuffered(buffered);
        super.setBuffered(buffered);
    }

    @Override
    public boolean isBuffered() {
        return getSelect().isBuffered();
    }

    @Override
    protected void setInternalValue(Object newValue) {
        super.setInternalValue(newValue);
        getSelect().setValue(newValue);
    }

    public TypedSelect<T> addMValueChangeListener(
            MValueChangeListener<T> listener) {
        addListener(MValueChangeEvent.class, listener,
                MValueChangeEventImpl.VALUE_CHANGE_METHOD);
        return this;
    }

    public TypedSelect<T> removeMValueChangeListener(
            MValueChangeListener<T> listener) {
        removeListener(MValueChangeEvent.class, listener,
                MValueChangeEventImpl.VALUE_CHANGE_METHOD);
        return this;
    }

    @Override
    public int getTabIndex() {
        return getSelect().getTabIndex();
    }

    @Override
    public void setTabIndex(int tabIndex) {
        getSelect().setTabIndex(tabIndex);
    }

    public CaptionGenerator<T> getCaptionGenerator() {
        return captionGenerator;
    }

    public TypedSelect<T> setCaptionGenerator(
            CaptionGenerator<T> captionGenerator) {
        this.captionGenerator = captionGenerator;
        return this;
    }

    public final TypedSelect<T> setOptions(Collection<T> options) {
        if (bic != null) {
            bic.setCollection(options);
        } else {
            bic = new ListContainer<T>(options);
        }
        getSelect().setContainerDataSource(bic);
        return this;
    }

    public final List<T> getOptions() {
        if (bic == null) {
            return Collections.EMPTY_LIST;
        } else {
            return (List<T>) bic.getItemIds();
        }
    }

    public TypedSelect<T> setNullSelectionAllowed(boolean nullAllowed) {
        getSelect().setNullSelectionAllowed(nullAllowed);
        return this;
    }

    public TypedSelect<T> setBeans(Collection<T> options) {
        return setOptions(options);
    }

    @Override
    public void attach() {
        if (bic != null && getSelect().getContainerDataSource() != bic) {
            getSelect().setContainerDataSource(bic);
        }
        super.attach();
    }

    private ValueChangeListener piggyBackListener;

    private void ensurePiggybackListener() {
        if (piggyBackListener == null) {
            piggyBackListener = new ValueChangeListener() {

                @Override
                public void valueChange(Property.ValueChangeEvent event) {
                    setValue(event.getProperty().getValue());
                    fireEvent(new MValueChangeEventImpl<T>(TypedSelect.this));
                }
            };
            getSelect().addValueChangeListener(piggyBackListener);
        }
    }

    @Override
    public T getValue() {
        return (T) super.getValue();
    }

    /**
     * An alias for {@link #withWidthFull()}
     * 
     * @return This component
     * @see #withWidthFull() 
     */
    public TypedSelect<T> withFullWidth() {
        return withWidthFull();
    }

    public void selectFirst() {
        if (bic != null && bic.size() > 0) {
            getSelect().setValue(bic.getIdByIndex(0));
        }
    }

    /**
     *
     * @return gets the ListContainer used by this component
     */
    protected ListContainer<T> getBic() {
        return bic;
    }

    /**
     *
     * @param listContainer sets the ListContainer used by this select. For
     * extensions only, should be set early or will fail.
     */
    protected void setBic(ListContainer<T> listContainer) {
        bic = listContainer;
    }

    @Override
    public void setReadOnly(boolean readOnly) {
        super.setReadOnly(readOnly);
        getSelect().setReadOnly(readOnly);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        getSelect().setEnabled(enabled);
    }

    @Override
    protected Component initContent() {
        return getSelect();
    }
    
    /* Fluent setters (FluentAbstractComponent): */

    @Override
    public TypedSelect withStyleName(String style, boolean add) {
        setStyleName(style, add);
        return this;
    }

    @Override
    public TypedSelect withCaptionAsHtml(boolean captionAsHtml) {
        setCaptionAsHtml(captionAsHtml);
        return this;
    }

    @Override
    public TypedSelect withLocale(Locale locale) {
        setLocale(locale);
        return this;
    }

    @Override
    public TypedSelect withImmediate(boolean immediate) {
        setImmediate(immediate);
        return this;
    }

    @Override
    public TypedSelect withDescription(String description) {
        setDescription(description);
        return this;
    }

    @Override
    public TypedSelect withComponentError(ErrorMessage componentError) {
        setComponentError(componentError);
        return this;
    }

    @Override
    public TypedSelect withListener(Listener listener) {
        addListener(listener);
        return this;
    }

    @Override
    public TypedSelect withData(Object data) {
        setData(data);
        return this;
    }

    @Override
    public TypedSelect withResponsive(boolean responsive) {
        setResponsive(responsive);
        return this;
    }

    @Override
    public TypedSelect withShortcutListener(ShortcutListener shortcut) {
        addShortcutListener(shortcut);
        return this;
    }

    @Override
    public TypedSelect withCaption(String caption) {
        setCaption(caption);
        return this;
    }

    @Override
    public TypedSelect withEnabled(boolean enabled) {
        setEnabled(enabled);
        return this;
    }

    @Override
    public TypedSelect withIcon(Resource icon) {
        setIcon(icon);
        return this;
    }

    @Override
    public TypedSelect withId(String id) {
        setId(id);
        return this;
    }

    @Override
    public TypedSelect withPrimaryStyleName(String style) {
        setPrimaryStyleName(style);
        return this;
    }

    @Override
    public TypedSelect withReadOnly(boolean readOnly) {
        setReadOnly(readOnly);
        return this;
    }

    @Override
    public TypedSelect withStyleName(String... styles) {
        for (String style : styles) {
            addStyleName(style);
        }
        return (TypedSelect) this;
    }

    @Override
    public TypedSelect withVisible(boolean visible) {
        setVisible(visible);
        return this;
    }

    @Override
    public TypedSelect withAttachListener(AttachListener listener) {
        addAttachListener(listener);
        return this;
    }

    @Override
    public TypedSelect withDetachListener(DetachListener listener) {
        addDetachListener(listener);
        return this;
    }

    @Override
    public TypedSelect withErrorHandler(ErrorHandler errorHandler) {
        setErrorHandler(errorHandler);
        return this;
    }

    @Override
    public TypedSelect withContextClickListener(ContextClickEvent.ContextClickListener listener) {
        addContextClickListener(listener);
        return this;
    }

    @Override
    public TypedSelect withHeight(String height) {
        setHeight(height);
        return this;
    }

    @Override
    public TypedSelect withHeight(float height, Unit unit) {
        setHeight(height, unit);
        return this;
    }

    @Override
    public TypedSelect withWidth(String width) {
        setWidth(width);
        return this;
    }

    @Override
    public TypedSelect withWidth(float width, Unit unit) {
        setWidth(width, unit);
        return this;
    }

    @Override
    public TypedSelect withSizeFull() {
        setSizeFull();
        return this;
    }
    
    @Override
    public TypedSelect withWidthFull() {
        setWidth(100, Unit.PERCENTAGE);
        return this;
    }

    @Override
    public TypedSelect withHeightFull() {
        setHeight(100, Unit.PERCENTAGE);
        return this;
    }

    @Override
    public TypedSelect withSizeUndefined() {
        setSizeUndefined();
        return this;
    }

    @Override
    public TypedSelect withWidthUndefined() {
        setWidthUndefined();
        return this;
    }

    @Override
    public TypedSelect withHeightUndefined() {
        setHeightUndefined();
        return this;
    }
    
    /* Fluent setters (FluentAbstractField): */

    @Override
    public TypedSelect withInvalidCommitted(boolean isCommitted) {
        setInvalidCommitted(isCommitted);
        return this;
    }

    @Override
    public TypedSelect withBuffered(boolean buffered) {
        setBuffered(buffered);
        return this;
    }

    @Override
    public TypedSelect withConverter(Class datamodelType) {
        setConverter(datamodelType);
        return this;
    }

    @Override
    public TypedSelect withConvertedValue(Object value) {
        setConvertedValue(value);
        return this;
    }

    @Override
    public TypedSelect withInvalidAllowed(boolean invalidAllowed) throws UnsupportedOperationException {
        setInvalidAllowed(invalidAllowed);
        return this;
    }

    @Override
    public TypedSelect withTabIndex(int tabIndex) {
        setTabIndex(tabIndex);
        return this;
    }

    @Override
    public TypedSelect withConversionError(String valueConversionError) {
        setConversionError(valueConversionError);
        return this;
    }

    @Override
    public TypedSelect withValidationVisible(boolean validateAutomatically) {
        setValidationVisible(validateAutomatically);
        return this;
    }

    @Override
    public TypedSelect withCurrentBufferedSourceException(SourceException currentBufferedSourceException) {
        setCurrentBufferedSourceException(currentBufferedSourceException);
        return this;
    }

    @Override
    public TypedSelect withConverter(Converter converter) {
        setConverter(converter);
        return this;
    }

    @Override
    public TypedSelect withRequired(boolean required) {
        setRequired(required);
        return this;
    }

    @Override
    public TypedSelect withRequiredError(String requiredMessage) {
        setRequiredError(requiredMessage);
        return this;
    }

    @Override
    public TypedSelect withValueChangeListener(ValueChangeListener listener) {
        addValueChangeListener(listener);
        return this;
    }

    @Override
    public TypedSelect withValue(Object newValue) throws ReadOnlyException {
        setValue(newValue);
        return this;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public TypedSelect withPropertyDataSource(Property newDataSource) {
        setPropertyDataSource(newDataSource);
        return this;
    }

    @Override
    public TypedSelect withValidator(Validator validator) {
        addValidator(validator);
        return this;
    }

    @Override
    public TypedSelect withReadOnlyStatusChangeListener(ReadOnlyStatusChangeListener listener) {
        addReadOnlyStatusChangeListener(listener);
        return this;
    }

}
