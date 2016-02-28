package org.vaadin.viritin.fields;

import com.vaadin.data.Property;
import com.vaadin.data.Validator;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.event.ContextClickEvent;
import com.vaadin.event.ShortcutListener;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.ErrorMessage;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Resource;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.ArrayList;
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Locale;

import org.vaadin.viritin.MBeanFieldGroup;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * A field suitable for editing collection of referenced objects tied to parent
 * object only. E.g. OneToMany/ElementCollection fields in JPA world.
 * <p>
 * Some features/restrictions:
 * <ul>
 * <li>The field is valid when all elements are valid.
 * <li>The field is always non buffered
 * <li>The element type needs to have an empty paremeter constructor or user
 * must provide an Instantiator.
 * </ul>
 *
 * Elements in the edited collection are modified with BeanFieldGroup. Fields
 * should defined in a class. A simple usage example for editing
 * List&gt;Address&lt; adresses:
 * <pre><code>
 *  public static class AddressRow {
 *      EnumSelect type = new EnumSelect();
 *      MTextField street = new MTextField();
 *      MTextField city = new MTextField();
 *      MTextField zipCode = new MTextField();
 *  }
 *
 *  public static class PersonForm&lt;Person&gt; extends AbstractForm {
 *      private final ElementCollectionTable&lt;Address&gt; addresses
 *              = new ElementCollectionTable&lt;Address&gt;(Address.class,
 *                      AddressRow.class).withCaption("Addressess");
 *
 * </code></pre>
 *
 * <p>
 * By default the field contains a button to add new elements. If instances are
 * added with some other method (or UI shouldn't add them at all), you can
 * configure this with setAllowNewItems. Deletions can be configured with
 * setAllowRemovingItems.
 * <p>
 *
 * @param <ET> The type in the entity collection. The type must have empty
 * parameter constructor or you have to provide Instantiator.
 */
public class ElementCollectionTable<ET> extends AbstractElementCollection<ET> {

    public ElementCollectionTable(Class elementType, Class formType) {
        super(elementType, formType);
    }

    public ElementCollectionTable(Class elementType, Instantiator i,
            Class formType) {
        super(elementType, i, formType);
    }

    private MTable table;

    private MButton addButton = new MButton(FontAwesome.PLUS,
            new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {
                    addElement(createInstance());
                }
            });

    private IdentityHashMap<ET, MButton> elementToDelButton = new IdentityHashMap<ET, MButton>();

    boolean inited = false;

    private MVerticalLayout layout = new MVerticalLayout();

    @Override
    public void attach() {
        super.attach();
        ensureInited();
    }

    @Override
    public void addInternalElement(final ET v) {
        ensureInited();
        table.addBeans(v);
    }

    @Override
    public void removeInternalElement(ET v) {
        table.removeItem(v);
        elementToDelButton.remove(v);
    }

    @Override
    public Layout getLayout() {
        return layout;
    }

    public MButton getAddButton() {
        return addButton;
    }

    /**
     * @return the Table used in the implementation. Configure carefully.
     */
    public MTable getTable() {
        return table;
    }

    @Override
    public void setPersisted(ET v, boolean persisted) {
        // NOP
    }

    private void ensureInited() {
        if (!inited) {
            layout.setMargin(false);
            setHeight("300px");
            table = new MTable(getElementType()).withFullWidth();
            for (Object propertyId : getVisibleProperties()) {
                table.addGeneratedColumn(propertyId,
                        new Table.ColumnGenerator() {

                            @Override
                            public Object generateCell(Table source,
                                    Object itemId,
                                    Object columnId) {
                                MBeanFieldGroup<ET> fg = getFieldGroupFor(
                                        (ET) itemId);
                                if (!isAllowEditItems()) {
                                    fg.setReadOnly(true);
                                }
                                Component component = fg.getField(columnId);
                                if(component == null) {
                                    getComponentFor((ET) itemId,
                                            columnId.toString());
                                }
                                return component;
                            }
                        });

            }
            ArrayList<Object> cols = new ArrayList<Object>(
                    getVisibleProperties());

            if (isAllowRemovingItems()) {
                table.addGeneratedColumn("__ACTIONS",
                        new Table.ColumnGenerator() {

                            @Override
                            public Object generateCell(Table source,
                                    final Object itemId,
                                    Object columnId) {

                                MButton b = new MButton(FontAwesome.TRASH_O).
                                withListener(
                                        new Button.ClickListener() {
                                            @Override
                                            public void buttonClick(
                                                    Button.ClickEvent event) {
                                                        removeElement(
                                                                (ET) itemId);
                                                    }
                                        }).withStyleName(
                                        ValoTheme.BUTTON_ICON_ONLY);
                                b.setDescription(getDeleteElementDescription());
                                elementToDelButton.put((ET) itemId, b);
                                return b;
                            }
                        });
                table.setColumnHeader("__ACTIONS", "");                
                cols.add("__ACTIONS");
            }
            
            table.setVisibleColumns(cols.toArray());
            for (Object property : getVisibleProperties()) {
                table.setColumnHeader(property, getPropertyHeader(property.
                        toString()));
            }
            layout.expand(table);
            if (isAllowNewItems()) {
                layout.addComponent(addButton);
            }
            inited = true;
        }
    }

    @Override
    public void clear() {
        if (inited) {
            table.removeAllItems();
            elementToDelButton.clear();
        }
    }

    public String getDisabledDeleteElementDescription() {
        return disabledDeleteThisElementDescription;
    }

    public void setDisabledDeleteThisElementDescription(
            String disabledDeleteThisElementDescription) {
        this.disabledDeleteThisElementDescription = disabledDeleteThisElementDescription;
    }

    private String disabledDeleteThisElementDescription = "Fill this row to add a new element, currently ignored";

    public String getDeleteElementDescription() {
        return deleteThisElementDescription;
    }

    private String deleteThisElementDescription = "Delete this element";

    public void setDeleteThisElementDescription(
            String deleteThisElementDescription) {
        this.deleteThisElementDescription = deleteThisElementDescription;
    }

    @Override
    public void onElementAdded() {
        // NOP
    }

    @Override
    public ElementCollectionTable<ET> setPropertyHeader(String propertyName,
            String propertyHeader) {
        super.setPropertyHeader(propertyName, propertyHeader);
        return this;
    }

    @Override
    public ElementCollectionTable<ET> setVisibleProperties(
            List<String> properties, List<String> propertyHeaders) {
        super.setVisibleProperties(properties, propertyHeaders);
        return this;
    }

    @Override
    public ElementCollectionTable<ET> setVisibleProperties(
            List<String> properties) {
        super.setVisibleProperties(properties);
        return this;
    }

    @Override
    public ElementCollectionTable<ET> setAllowNewElements(
            boolean allowNewItems) {
        super.setAllowNewElements(allowNewItems);
        return this;
    }

    @Override
    public ElementCollectionTable<ET> setAllowRemovingItems(
            boolean allowRemovingItems) {
        super.setAllowRemovingItems(allowRemovingItems);
        return this;
    }

    @Override
    public ElementCollectionTable<ET> removeElementRemovedListener(
            ElementRemovedListener listener) {
        super.removeElementRemovedListener(listener);
        return this;
    }

    @Override
    public ElementCollectionTable<ET> addElementRemovedListener(
            ElementRemovedListener<ET> listener) {
        super.addElementRemovedListener(listener);
        return this;
    }

    @Override
    public ElementCollectionTable<ET> removeElementAddedListener(
            ElementAddedListener listener) {
        super.removeElementAddedListener(listener);
        return this;
    }

    @Override
    public ElementCollectionTable<ET> addElementAddedListener(
            ElementAddedListener<ET> listener) {
        super.addElementAddedListener(listener);
        return this;
    }

    public ElementCollectionTable<ET> withEditorInstantiator(
            Instantiator instantiator) {
        setEditorInstantiator(instantiator);
        return this;
    }

    @Override
    public ElementCollectionTable<ET> withValidator(Validator validator) {
        return (ElementCollectionTable<ET>) super.withValidator(validator);
    }

    @Override
    public ElementCollectionTable<ET> withPropertyDataSource(Property newDataSource) {
        return (ElementCollectionTable<ET>) super.withPropertyDataSource(newDataSource);
    }

    @Override
    public ElementCollectionTable<ET> withValue(Collection newValue) throws ReadOnlyException {
        return (ElementCollectionTable<ET>) super.withValue(newValue);
    }

    @Override
    public ElementCollectionTable<ET> withValueChangeListener(ValueChangeListener listener) {
        return (ElementCollectionTable<ET>) super.withValueChangeListener(listener);
    }

    @Override
    public ElementCollectionTable<ET> withRequiredError(String requiredMessage) {
        return (ElementCollectionTable<ET>) super.withRequiredError(requiredMessage);
    }

    @Override
    public ElementCollectionTable<ET> withRequired(boolean required) {
        return (ElementCollectionTable<ET>) super.withRequired(required);
    }

    @Override
    public ElementCollectionTable<ET> withConverter(Converter<Collection, ?> converter) {
        return (ElementCollectionTable<ET>) super.withConverter(converter);
    }

    @Override
    public ElementCollectionTable<ET> withCurrentBufferedSourceException(SourceException currentBufferedSourceException) {
        return (ElementCollectionTable<ET>) super.withCurrentBufferedSourceException(currentBufferedSourceException);
    }

    @Override
    public ElementCollectionTable<ET> withValidationVisible(boolean validateAutomatically) {
        return (ElementCollectionTable<ET>) super.withValidationVisible(validateAutomatically);
    }

    @Override
    public ElementCollectionTable<ET> withConversionError(String valueConversionError) {
        return (ElementCollectionTable<ET>) super.withConversionError(valueConversionError);
    }

    @Override
    public ElementCollectionTable<ET> withTabIndex(int tabIndex) {
        return (ElementCollectionTable<ET>) super.withTabIndex(tabIndex);
    }

    @Override
    public ElementCollectionTable<ET> withInvalidAllowed(boolean invalidAllowed) throws UnsupportedOperationException {
        return (ElementCollectionTable<ET>) super.withInvalidAllowed(invalidAllowed);
    }

    @Override
    public ElementCollectionTable<ET> withConvertedValue(Object value) {
        return (ElementCollectionTable<ET>) super.withConvertedValue(value);
    }

    @Override
    public ElementCollectionTable<ET> withConverter(Class<?> datamodelType) {
        return (ElementCollectionTable<ET>) super.withConverter(datamodelType);
    }

    @Override
    public ElementCollectionTable<ET> withBuffered(boolean buffered) {
        return (ElementCollectionTable<ET>) super.withBuffered(buffered);
    }

    @Override
    public ElementCollectionTable<ET> withInvalidCommitted(boolean isCommitted) {
        return (ElementCollectionTable<ET>) super.withInvalidCommitted(isCommitted);
    }

    @Override
    public ElementCollectionTable<ET> withHeightUndefined() {
        return (ElementCollectionTable<ET>) super.withHeightUndefined();
    }

    @Override
    public ElementCollectionTable<ET> withWidthUndefined() {
        return (ElementCollectionTable<ET>) super.withWidthUndefined();
    }

    @Override
    public ElementCollectionTable<ET> withSizeUndefined() {
        return (ElementCollectionTable<ET>) super.withSizeUndefined();
    }

    @Override
    public ElementCollectionTable<ET> withHeightFull() {
        return (ElementCollectionTable<ET>) super.withHeightFull();
    }

    @Override
    public ElementCollectionTable<ET> withWidthFull() {
        return (ElementCollectionTable<ET>) super.withWidthFull();
    }

    @Override
    public ElementCollectionTable<ET> withSizeFull() {
        return (ElementCollectionTable<ET>) super.withSizeFull();
    }

    @Override
    public ElementCollectionTable<ET> withWidth(float width, Unit unit) {
        return (ElementCollectionTable<ET>) super.withWidth(width, unit);
    }

    @Override
    public ElementCollectionTable<ET> withWidth(String width) {
        return (ElementCollectionTable<ET>) super.withWidth(width);
    }

    @Override
    public ElementCollectionTable<ET> withHeight(float height, Unit unit) {
        return (ElementCollectionTable<ET>) super.withHeight(height, unit);
    }

    @Override
    public ElementCollectionTable<ET> withHeight(String height) {
        return (ElementCollectionTable<ET>) super.withHeight(height);
    }

    @Override
    public ElementCollectionTable<ET> withContextClickListener(ContextClickEvent.ContextClickListener listener) {
        return (ElementCollectionTable<ET>) super.withContextClickListener(listener);
    }

    @Override
    public ElementCollectionTable<ET> withErrorHandler(ErrorHandler errorHandler) {
        return (ElementCollectionTable<ET>) super.withErrorHandler(errorHandler);
    }

    @Override
    public ElementCollectionTable<ET> withDetachListener(DetachListener listener) {
        return (ElementCollectionTable<ET>) super.withDetachListener(listener);
    }

    @Override
    public ElementCollectionTable<ET> withAttachListener(AttachListener listener) {
        return (ElementCollectionTable<ET>) super.withAttachListener(listener);
    }

    @Override
    public ElementCollectionTable<ET> withVisible(boolean visible) {
        return (ElementCollectionTable<ET>) super.withVisible(visible);
    }

    @Override
    public ElementCollectionTable<ET> withStyleName(String style) {
        return (ElementCollectionTable<ET>) super.withStyleName(style);
    }

    @Override
    public ElementCollectionTable<ET> withReadOnly(boolean readOnly) {
        return (ElementCollectionTable<ET>) super.withReadOnly(readOnly);
    }

    @Override
    public ElementCollectionTable<ET> withPrimaryStyleName(String style) {
        return (ElementCollectionTable<ET>) super.withPrimaryStyleName(style);
    }

    @Override
    public ElementCollectionTable<ET> withId(String id) {
        return (ElementCollectionTable<ET>) super.withId(id);
    }

    @Override
    public ElementCollectionTable<ET> withIcon(Resource icon) {
        return (ElementCollectionTable<ET>) super.withIcon(icon);
    }

    @Override
    public ElementCollectionTable<ET> withEnabled(boolean enabled) {
        return (ElementCollectionTable<ET>) super.withEnabled(enabled);
    }

    @Override
    public ElementCollectionTable<ET> withShortcutListener(ShortcutListener shortcut) {
        return (ElementCollectionTable<ET>) super.withShortcutListener(shortcut);
    }

    @Override
    public ElementCollectionTable<ET> withResponsive(boolean responsive) {
        return (ElementCollectionTable<ET>) super.withResponsive(responsive);
    }

    @Override
    public ElementCollectionTable<ET> withData(Object data) {
        return (ElementCollectionTable<ET>) super.withData(data);
    }

    @Override
    public ElementCollectionTable<ET> withListener(Listener listener) {
        return (ElementCollectionTable<ET>) super.withListener(listener);
    }

    @Override
    public ElementCollectionTable<ET> withComponentError(ErrorMessage componentError) {
        return (ElementCollectionTable<ET>) super.withComponentError(componentError);
    }

    @Override
    public ElementCollectionTable<ET> withDescription(String description) {
        return (ElementCollectionTable<ET>) super.withDescription(description);
    }

    @Override
    public ElementCollectionTable<ET> withImmediate(boolean immediate) {
        return (ElementCollectionTable<ET>) super.withImmediate(immediate);
    }

    @Override
    public ElementCollectionTable<ET> withLocale(Locale locale) {
        return (ElementCollectionTable<ET>) super.withLocale(locale);
    }

    @Override
    public ElementCollectionTable<ET> withCaptionAsHtml(boolean captionAsHtml) {
        return (ElementCollectionTable<ET>) super.withCaptionAsHtml(captionAsHtml);
    }

    @Override
    public ElementCollectionTable<ET> withStyleName(String style, boolean add) {
        return (ElementCollectionTable<ET>) super.withStyleName(style, add);
    }

}
