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
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.event.MouseEvents;
import com.vaadin.event.ShortcutListener;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.ErrorMessage;
import com.vaadin.server.Resource;
import com.vaadin.shared.MouseEventDetails;
import com.vaadin.ui.Component;
import com.vaadin.ui.Table;
import com.vaadin.util.ReflectTools;
import java.io.Serializable;
import java.lang.reflect.Method;
import org.vaadin.viritin.ListContainer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;
import org.vaadin.viritin.LazyList;
import static org.vaadin.viritin.LazyList.DEFAULT_PAGE_SIZE;
import org.vaadin.viritin.SortableLazyList;
import org.vaadin.viritin.fluency.ui.FluentAbstractField;

/**
 * A better typed version of the Table component in Vaadin. Expects that users
 * are always listing POJOs, which is most often the case in modern Java
 * development. Uses ListContainer to bind data due to its superior performance
 * compared to BeanItemContainer.
 * <p>
 * Note, that MTable don't support "multiselection mode". It is also very little
 * tested in "editable mode".
 * <p>
 * If your "list" of entities is too large to load into memory, there are also
 * constructors for typical "service layers". Then paged requests are used to
 * fetch entities that are visible (or almost visible) in the UI. Behind the
 * scenes LazyList is used to "wrap" your service into list.
 *
 * @param <T> the type of the POJO listed in this Table.
 */
public class MTable<T> extends Table implements FluentAbstractField<MTable<T>, Object> {

    private ListContainer<T> bic;
    private String[] pendingProperties;
    private String[] pendingHeaders;

    private Collection sortableProperties;

    public MTable() {
    }

    /**
     * Constructs a Table with explicit bean type. Handy for example if your
     * beans are JPA proxies or the table is empty when showing it initially.
     *
     * @param type the type of beans that are listed in this table
     */
    public MTable(Class<T> type) {
        bic = createContainer(type);
        setContainerDataSource(bic);
    }

    public MTable(T... beans) {
        this(new ArrayList<T>(Arrays.asList(beans)));
    }

    /**
     * A shorthand to create MTable using LazyList. By default page size of
     * LazyList.DEFAULT_PAGE_SIZE (30) is used.
     *
     * @param pageProvider the interface via entities are fetched
     * @param countProvider the interface via the count of items is detected
     */
    public MTable(LazyList.PagingProvider<T> pageProvider,
            LazyList.CountProvider countProvider) {
        this(new LazyList(pageProvider, countProvider, DEFAULT_PAGE_SIZE));
    }

    /**
     * A shorthand to create MTable using LazyList.
     *
     * @param pageProvider the interface via entities are fetched
     * @param countProvider the interface via the count of items is detected
     * @param pageSize the page size (aka maxResults) that is used in paging.
     */
    public MTable(LazyList.PagingProvider<T> pageProvider,
            LazyList.CountProvider countProvider, int pageSize) {
        this(new LazyList(pageProvider, countProvider, pageSize));
    }

    /**
     * A shorthand to create MTable using SortableLazyList. By default page size
     * of LazyList.DEFAULT_PAGE_SIZE (30) is used.
     *
     * @param pageProvider the interface via entities are fetched
     * @param countProvider the interface via the count of items is detected
     */
    public MTable(SortableLazyList.SortablePagingProvider<T> pageProvider,
            LazyList.CountProvider countProvider) {
        this(new SortableLazyList(pageProvider, countProvider, DEFAULT_PAGE_SIZE));
    }

    /**
     * A shorthand to create MTable using SortableLazyList.
     *
     * @param pageProvider the interface via entities are fetched
     * @param countProvider the interface via the count of items is detected
     * @param pageSize the page size (aka maxResults) that is used in paging.
     */
    public MTable(SortableLazyList.SortablePagingProvider<T> pageProvider,
            LazyList.CountProvider countProvider, int pageSize) {
        this(new SortableLazyList(pageProvider, countProvider, pageSize));
    }

    /**
     * A shorthand to create MTable using LazyList. By default page size of
     * LazyList.DEFAULT_PAGE_SIZE (30) is used.
     *
     * @param rowType the type of entities listed in the table
     * @param pageProvider the interface via entities are fetched
     * @param countProvider the interface via the count of items is detected
     */
    public MTable(Class<T> rowType, LazyList.PagingProvider<T> pageProvider,
            LazyList.CountProvider countProvider) {
        this(rowType, pageProvider, countProvider, DEFAULT_PAGE_SIZE);
    }

    /**
     * A shorthand to create MTable using LazyList.
     *
     * @param rowType the type of entities listed in the table
     * @param pageProvider the interface via entities are fetched
     * @param countProvider the interface via the count of items is detected
     * @param pageSize the page size (aka maxResults) that is used in paging.
     */
    public MTable(Class<T> rowType, LazyList.PagingProvider<T> pageProvider,
            LazyList.CountProvider countProvider, int pageSize) {
        this(rowType);
        lazyLoadFrom(pageProvider, countProvider, pageSize);
    }

    /**
     * A shorthand to create MTable using SortableLazyList. By default page size
     * of LazyList.DEFAULT_PAGE_SIZE (30) is used.
     *
     * @param rowType the type of entities listed in the table
     * @param pageProvider the interface via entities are fetched
     * @param countProvider the interface via the count of items is detected
     */
    public MTable(Class<T> rowType,
            SortableLazyList.SortablePagingProvider<T> pageProvider,
            LazyList.CountProvider countProvider) {
        this(rowType, pageProvider, countProvider, DEFAULT_PAGE_SIZE);
    }

    /**
     * A shorthand to create MTable using SortableLazyList.
     *
     * @param rowType the type of entities listed in the table
     * @param pageProvider the interface via entities are fetched
     * @param countProvider the interface via the count of items is detected
     * @param pageSize the page size (aka maxResults) that is used in paging.
     */
    public MTable(Class<T> rowType,
            SortableLazyList.SortablePagingProvider<T> pageProvider,
            LazyList.CountProvider countProvider, int pageSize) {
        this(rowType);
        lazyLoadFrom(pageProvider, countProvider, pageSize);
    }

    public MTable(Collection<T> beans) {
        this();
        if (beans != null) {
            bic = createContainer(beans);
            setContainerDataSource(bic);
        }
    }

    /**
     * Makes the table lazy load its content with given strategy.
     *
     * @param pageProvider the interface via entities are fetched
     * @param countProvider the interface via the count of items is detected
     * @return this MTable object
     */
    public MTable<T> lazyLoadFrom(LazyList.PagingProvider<T> pageProvider,
            LazyList.CountProvider countProvider) {
        setBeans(new LazyList(pageProvider, countProvider, DEFAULT_PAGE_SIZE));
        return this;
    }

    /**
     * Makes the table lazy load its content with given strategy.
     *
     * @param pageProvider the interface via entities are fetched
     * @param countProvider the interface via the count of items is detected
     * @param pageSize the page size (aka maxResults) that is used in paging.
     * @return this MTable object
     */
    public MTable<T> lazyLoadFrom(LazyList.PagingProvider<T> pageProvider,
            LazyList.CountProvider countProvider, int pageSize) {
        setBeans(new LazyList(pageProvider, countProvider, pageSize));
        return this;
    }

    /**
     * Makes the table lazy load its content with given strategy.
     *
     * @param pageProvider the interface via entities are fetched
     * @param countProvider the interface via the count of items is detected
     * @return this MTable object
     */
    public MTable<T> lazyLoadFrom(
            SortableLazyList.SortablePagingProvider<T> pageProvider,
            LazyList.CountProvider countProvider) {
        setBeans(new SortableLazyList(pageProvider, countProvider,
                DEFAULT_PAGE_SIZE));
        return this;
    }

    /**
     * Makes the table lazy load its content with given strategy.
     *
     * @param pageProvider the interface via entities are fetched
     * @param countProvider the interface via the count of items is detected
     * @param pageSize the page size (aka maxResults) that is used in paging.
     * @return this MTable object
     */
    public MTable<T> lazyLoadFrom(
            SortableLazyList.SortablePagingProvider<T> pageProvider,
            LazyList.CountProvider countProvider, int pageSize) {
        setBeans(new SortableLazyList(pageProvider, countProvider, pageSize));
        return this;
    }

    protected ListContainer<T> createContainer(Class<T> type) {
        return new ListContainer<T>(type);
    }

    protected ListContainer<T> createContainer(Collection<T> beans) {
        return new ListContainer<T>(beans);
    }

    protected ListContainer<T> getContainer() {
        return bic;
    }

    public MTable<T> withProperties(String... visibleProperties) {
        if (isContainerInitialized()) {
            bic.setContainerPropertyIds(visibleProperties);
            setVisibleColumns((Object[]) visibleProperties);
        } else {
            pendingProperties = visibleProperties;
            for (String string : visibleProperties) {
                addContainerProperty(string, String.class, "");
            }
        }
        for (String visibleProperty : visibleProperties) {
            String[] parts = StringUtils.splitByCharacterTypeCamelCase(
                    visibleProperty);
            parts[0] = StringUtils.capitalize(parts[0]);
            for (int i = 1; i < parts.length; i++) {
                parts[i] = parts[i].toLowerCase();
            }
            String saneCaption = StringUtils.join(parts, " ");
            setColumnHeader(visibleProperty, saneCaption);
        }
        return this;
    }

    protected boolean isContainerInitialized() {
        return bic != null;
    }

    public MTable<T> withColumnHeaders(String... columnNamesForVisibleProperties) {
        if (isContainerInitialized()) {
            setColumnHeaders(columnNamesForVisibleProperties);
        } else {
            pendingHeaders = columnNamesForVisibleProperties;
            // Add headers to temporary indexed container, in case table is initially
            // empty
            for (String prop : columnNamesForVisibleProperties) {
                addContainerProperty(prop, String.class, "");
            }
        }
        return this;
    }

    /**
     * Explicitly sets which properties are sortable in the UI.
     *
     * @param sortableProperties the collection of property identifiers/names
     * that should be sortable
     * @return the MTable instance
     */
    public MTable<T> setSortableProperties(Collection sortableProperties) {
        this.sortableProperties = sortableProperties;
        return this;
    }

    /**
     * Explicitly sets which properties are sortable in the UI.
     *
     * @param sortableProperties the collection of property identifiers/names
     * that should be sortable
     * @return the MTable instance
     */
    public MTable<T> setSortableProperties(String... sortableProperties) {
        this.sortableProperties = Arrays.asList(sortableProperties);
        return this;
    }

    public Collection getSortableProperties() {
        return sortableProperties;
    }

    @Override
    public Collection<?> getSortableContainerPropertyIds() {
        if (getSortableProperties() != null) {
            return Collections.unmodifiableCollection(sortableProperties);
        }
        return super.getSortableContainerPropertyIds();
    }

    public void addMValueChangeListener(MValueChangeListener<T> listener) {
        addListener(MValueChangeEvent.class, listener,
                MValueChangeEventImpl.VALUE_CHANGE_METHOD);
        // implicitly consider the table should be selectable
        setSelectable(true);
        // Needed as client side checks only for "real value change listener"
        setImmediate(true);
    }

    public void removeMValueChangeListener(MValueChangeListener<T> listener) {
        removeListener(MValueChangeEvent.class, listener,
                MValueChangeEventImpl.VALUE_CHANGE_METHOD);
        setSelectable(hasListeners(MValueChangeEvent.class));
    }

    @Override
    protected void fireValueChange(boolean repaintIsNotNeeded) {
        super.fireValueChange(repaintIsNotNeeded);
        fireEvent(new MValueChangeEventImpl(this));
    }

    protected void ensureBeanItemContainer(Collection<T> beans) {
        if (!isContainerInitialized()) {
            bic = createContainer(beans);
            if (pendingProperties != null) {
                bic.setContainerPropertyIds(pendingProperties);
                pendingProperties = null;
            }
            setContainerDataSource(bic);
            if (pendingHeaders != null) {
                setColumnHeaders(pendingHeaders);
                pendingHeaders = null;
            }
        }
    }

    @Override
    public T getValue() {
        return (T) super.getValue();
    }

    @Override
    @Deprecated
    public void setMultiSelect(boolean multiSelect) {
        super.setMultiSelect(multiSelect);
    }

    public MTable<T> addBeans(T... beans) {
        addBeans(Arrays.asList(beans));
        return this;
    }

    public MTable<T> addBeans(Collection<T> beans) {
        if (!beans.isEmpty()) {
            if (isContainerInitialized()) {
                bic.addAll(beans);
            } else {
                ensureBeanItemContainer(beans);
            }
        }
        return this;
    }

    public MTable<T> setBeans(T... beans) {
        setBeans(new ArrayList<T>(Arrays.asList(beans)));
        return this;
    }

    public MTable<T> setBeans(Collection<T> beans) {
        if (!isContainerInitialized() && !beans.isEmpty()) {
            ensureBeanItemContainer(beans);
        } else if (isContainerInitialized()) {
            bic.setCollection(beans);
        }
        return this;
    }

    /**
     * Makes the first column of the table a primary column, for which all space
     * left out from other columns is given. The method also makes sure the
     * Table has a width defined (otherwise the setting makes no sense).
     *
     *
     * @return {@link MTable}
     */
    public MTable<T> expandFirstColumn() {
        expand(getContainerPropertyIds().iterator().next().toString());
        if (getWidth() == -1) {
            return withFullWidth();
        }
        return this;
    }

    public MTable<T> withFullWidth() {
        setWidth(100, Unit.PERCENTAGE);
        return this;
    }

    public MTable<T> withFullHeight() {
        return withHeightFull();
    }

    public MTable<T> expand(String... propertiesToExpand) {
        for (String property : propertiesToExpand) {
            setColumnExpandRatio(property, 1);
        }
        return this;
    }

    private ItemClickListener itemClickPiggyback;

    private void ensureTypedItemClickPiggybackListener() {
        if (itemClickPiggyback == null) {
            itemClickPiggyback = new ItemClickListener() {
                @Override
                public void itemClick(ItemClickEvent event) {
                    fireEvent(new RowClickEvent<T>(event));
                }
            };
            addItemClickListener(itemClickPiggyback);
        }
    }

    public static interface SimpleColumnGenerator<T> {

        public Object generate(T entity);
    }

    public MTable<T> withGeneratedColumn(String columnId,
            final SimpleColumnGenerator<T> columnGenerator) {
        addGeneratedColumn(columnId, new ColumnGenerator() {
            @Override
            public Object generateCell(Table source, Object itemId,
                    Object columnId) {
                return columnGenerator.generate((T) itemId);
            }
        });
        return this;
    }

    public static class SortEvent extends Component.Event {

        private boolean preventContainerSort = false;
        private final boolean sortAscending;
        private final String sortProperty;

        public SortEvent(Component source, boolean sortAscending,
                String property) {
            super(source);
            this.sortAscending = sortAscending;
            this.sortProperty = property;
        }

        public String getSortProperty() {
            return sortProperty;
        }

        public boolean isSortAscending() {
            return sortAscending;
        }

        /**
         * By calling this method you can prevent the sort call to the container
         * used by MTable. In this case you most most probably you want to
         * manually sort the container instead.
         */
        public void preventContainerSort() {
            preventContainerSort = true;
        }

        public boolean isPreventContainerSort() {
            return preventContainerSort;
        }

        private final static Method method = ReflectTools.findMethod(
                SortListener.class, "onSort",
                SortEvent.class);

    }

    /**
     * A listener that can be used to track when user sorts table on a column.
     *
     * Via the event user can also prevent the "container sort" done by the
     * Table and implement own sorting logic instead (e.g. get a sorted list of
     * entities from the backend).
     *
     */
    public interface SortListener {

        public void onSort(SortEvent event);

    }

    public MTable addSortListener(SortListener listener) {
        addListener(SortEvent.class, listener, SortEvent.method);
        return this;
    }

    public MTable removeSortListener(SortListener listener) {
        removeListener(SortEvent.class, listener, SortEvent.method);
        return this;
    }

    private boolean isSorting = false;

    @Override
    public void sort(Object[] propertyId, boolean[] ascending) throws UnsupportedOperationException {
        if (isSorting) {
            // hack to avoid recursion
            return;
        }

        boolean refreshingPreviouslyEnabled = disableContentRefreshing();
        boolean defaultTableSortingMethod = false;
        try {
            isSorting = true;

            // create sort event and fire it, allow user to prevent default
            // operation
            final boolean sortAscending = ascending != null && ascending.length > 0 ? ascending[0] : true;
            final String sortProperty = propertyId != null && propertyId.length > 0 ? propertyId[0].
                    toString() : null;

            final SortEvent sortEvent = new SortEvent(this, sortAscending,
                    sortProperty);
            fireEvent(sortEvent);

            if (!sortEvent.isPreventContainerSort()) {
                // if not prevented, do sorting
                if (bic != null && bic.getItemIds() instanceof SortableLazyList) {
                    // Explicit support for SortableLazyList, set sort parameters
                    // it uses to backend services and clear internal buffers
                    SortableLazyList<T> sll = (SortableLazyList) bic.
                            getItemIds();
                    if (ascending == null || ascending.length == 0) {
                        sll.sort(true, null);
                    } else {
                        sll.sort(ascending[0], propertyId[0].toString());
                    }
                    resetPageBuffer();
                } else {
                    super.sort(propertyId, ascending);
                    defaultTableSortingMethod = true;
                }
            }
            if (!defaultTableSortingMethod) {
                // Ensure the values used in UI are set as this method is public
                // and can be called by both UI event and app logic
                setSortAscending(sortAscending);
                setSortContainerPropertyId(sortProperty);

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            isSorting = false;
            if (refreshingPreviouslyEnabled) {
                enableContentRefreshing(true);
            }
        }
    }

    /**
     * A version of ItemClickEvent that is properly typed and named.
     *
     * @param <T>
     */
    public static class RowClickEvent<T> extends MouseEvents.ClickEvent {

        public static final Method TYPED_ITEM_CLICK_METHOD;

        static {
            try {
                TYPED_ITEM_CLICK_METHOD = RowClickListener.class.
                        getDeclaredMethod("rowClick",
                                new Class[]{RowClickEvent.class});
            } catch (final java.lang.NoSuchMethodException e) {
                // This should never happen
                throw new java.lang.RuntimeException();
            }
        }

        private final ItemClickEvent orig;

        public RowClickEvent(ItemClickEvent orig) {
            super(orig.getComponent(), null);
            this.orig = orig;
        }

        /**
         * @return the entity(~row) that was clicked.
         */
        public T getEntity() {
            return (T) orig.getItemId();
        }

        /**
         * @return the entity(~row) that was clicked.
         */
        public T getRow() {
            return getEntity();
        }

        /**
         * @return the identifier of the column on which the row click happened.
         */
        public String getColumnId() {
            return orig.getPropertyId().toString();
        }

        @Override
        public MouseEventDetails.MouseButton getButton() {
            return orig.getButton();
        }

        @Override
        public int getClientX() {
            return orig.getClientX();
        }

        @Override
        public int getClientY() {
            return orig.getClientY();
        }

        @Override
        public int getRelativeX() {
            return orig.getRelativeX();
        }

        @Override
        public int getRelativeY() {
            return orig.getRelativeY();
        }

        @Override
        public boolean isAltKey() {
            return orig.isAltKey();
        }

        @Override
        public boolean isCtrlKey() {
            return orig.isCtrlKey();
        }

        @Override
        public boolean isDoubleClick() {
            return orig.isDoubleClick();
        }

        @Override
        public boolean isMetaKey() {
            return orig.isMetaKey();
        }

        @Override
        public boolean isShiftKey() {
            return orig.isShiftKey();
        }

    }

    /**
     * A better typed version of ItemClickEvent.
     *
     * @param <T> the type of entities listed in the table
     */
    public interface RowClickListener<T> extends Serializable {

        public void rowClick(RowClickEvent<T> event);
    }

    public void addRowClickListener(RowClickListener<T> listener) {
        ensureTypedItemClickPiggybackListener();
        addListener(RowClickEvent.class, listener,
                RowClickEvent.TYPED_ITEM_CLICK_METHOD);
    }

    public void removeRowClickListener(RowClickListener<T> listener) {
        removeListener(RowClickEvent.class, listener,
                RowClickEvent.TYPED_ITEM_CLICK_METHOD);
    }

    /**
     * Clears caches in case the Table is backed by a LazyList implementation.
     * Also resets "pageBuffer" used by table. If you know you have changes in
     * the listing, you can call this method to ensure the UI gets updated.
     */
    public void resetLazyList() {
        if (bic != null && bic.getItemIds() instanceof LazyList) {
            ((LazyList) bic.getItemIds()).reset();
        }
        resetPageBuffer();
    }

    /**
     * Sets the row of given entity as selected. This is practically a better
     * typed version for select(Object) and setValue(Object) methods.
     *
     * @param entity the entity whose row should be selected
     * @return the MTable instance
     */
    public MTable<T> setSelected(T entity) {
        setValue(entity);
        return this;
    }
    
    /* Fluent setters (FluentAbstractComponent): */

    @Override
    public MTable<T> withStyleName(String style, boolean add) {
        setStyleName(style, add);
        return this;
    }

    @Override
    public MTable<T> withCaptionAsHtml(boolean captionAsHtml) {
        setCaptionAsHtml(captionAsHtml);
        return this;
    }

    @Override
    public MTable<T> withLocale(Locale locale) {
        setLocale(locale);
        return this;
    }

    @Override
    public MTable<T> withImmediate(boolean immediate) {
        setImmediate(immediate);
        return this;
    }

    @Override
    public MTable<T> withDescription(String description) {
        setDescription(description);
        return this;
    }

    @Override
    public MTable<T> withComponentError(ErrorMessage componentError) {
        setComponentError(componentError);
        return this;
    }

    @Override
    public MTable<T> withListener(Listener listener) {
        addListener(listener);
        return this;
    }

    @Override
    public MTable<T> withData(Object data) {
        setData(data);
        return this;
    }

    @Override
    public MTable<T> withResponsive(boolean responsive) {
        setResponsive(responsive);
        return this;
    }

    @Override
    public MTable<T> withShortcutListener(ShortcutListener shortcut) {
        addShortcutListener(shortcut);
        return this;
    }

    @Override
    public MTable<T> withCaption(String caption) {
        setCaption(caption);
        return this;
    }

    @Override
    public MTable<T> withEnabled(boolean enabled) {
        setEnabled(enabled);
        return this;
    }

    @Override
    public MTable<T> withIcon(Resource icon) {
        setIcon(icon);
        return this;
    }

    @Override
    public MTable<T> withId(String id) {
        setId(id);
        return this;
    }

    @Override
    public MTable<T> withPrimaryStyleName(String style) {
        setPrimaryStyleName(style);
        return this;
    }

    @Override
    public MTable<T> withReadOnly(boolean readOnly) {
        setReadOnly(readOnly);
        return this;
    }

    @Override
    public MTable<T> withStyleName(String... styles) {
        for (String style : styles) {
            addStyleName(style);
        }
        return this;
    }

    @Override
    public MTable<T> withVisible(boolean visible) {
        setVisible(visible);
        return this;
    }

    @Override
    public MTable<T> withAttachListener(AttachListener listener) {
        addAttachListener(listener);
        return this;
    }

    @Override
    public MTable<T> withDetachListener(DetachListener listener) {
        addDetachListener(listener);
        return this;
    }

    @Override
    public MTable<T> withErrorHandler(ErrorHandler errorHandler) {
        setErrorHandler(errorHandler);
        return this;
    }

    @Override
    public MTable<T> withContextClickListener(ContextClickEvent.ContextClickListener listener) {
        addContextClickListener(listener);
        return this;
    }

    @Override
    public MTable<T> withHeight(String height) {
        setHeight(height);
        return this;
    }

    @Override
    public MTable<T> withHeight(float height, Unit unit) {
        setHeight(height, unit);
        return this;
    }

    @Override
    public MTable<T> withWidth(String width) {
        setWidth(width);
        return this;
    }

    @Override
    public MTable<T> withWidth(float width, Unit unit) {
        setWidth(width, unit);
        return this;
    }

    @Override
    public MTable<T> withSizeFull() {
        setSizeFull();
        return this;
    }
    
    @Override
    public MTable<T> withWidthFull() {
        setWidth(100, Unit.PERCENTAGE);
        return this;
    }

    @Override
    public MTable<T> withHeightFull() {
        setHeight(100, Unit.PERCENTAGE);
        return this;
    }

    @Override
    public MTable<T> withSizeUndefined() {
        setSizeUndefined();
        return this;
    }

    @Override
    public MTable<T> withWidthUndefined() {
        setWidthUndefined();
        return this;
    }

    @Override
    public MTable<T> withHeightUndefined() {
        setHeightUndefined();
        return this;
    }
    
    /* Fluent setters (FluentAbstractField): */

    @Override
    public MTable<T> withInvalidCommitted(boolean isCommitted) {
        setInvalidCommitted(isCommitted);
        return this;
    }

    @Override
    public MTable<T> withBuffered(boolean buffered) {
        setBuffered(buffered);
        return this;
    }

    @Override
    public MTable<T> withConverter(Class<?> datamodelType) {
        setConverter(datamodelType);
        return this;
    }

    @Override
    public MTable<T> withConvertedValue(Object value) {
        setConvertedValue(value);
        return this;
    }

    @Override
    public MTable<T> withInvalidAllowed(boolean invalidAllowed) throws UnsupportedOperationException {
        setInvalidAllowed(invalidAllowed);
        return this;
    }

    @Override
    public MTable<T> withTabIndex(int tabIndex) {
        setTabIndex(tabIndex);
        return this;
    }

    @Override
    public MTable<T> withConversionError(String valueConversionError) {
        setConversionError(valueConversionError);
        return this;
    }

    @Override
    public MTable<T> withValidationVisible(boolean validateAutomatically) {
        setValidationVisible(validateAutomatically);
        return this;
    }

    @Override
    public MTable<T> withCurrentBufferedSourceException(SourceException currentBufferedSourceException) {
        setCurrentBufferedSourceException(currentBufferedSourceException);
        return this;
    }

    @Override
    public MTable<T> withConverter(Converter<Object, ?> converter) {
        setConverter(converter);
        return this;
    }

    @Override
    public MTable<T> withRequired(boolean required) {
        setRequired(required);
        return this;
    }

    @Override
    public MTable<T> withRequiredError(String requiredMessage) {
        setRequiredError(requiredMessage);
        return this;
    }

    @Override
    public MTable<T> withValueChangeListener(ValueChangeListener listener) {
        addValueChangeListener(listener);
        return this;
    }

    @Override
    public MTable<T> withValue(Object newValue) throws ReadOnlyException {
        setValue(newValue);
        return this;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public MTable<T> withPropertyDataSource(Property newDataSource) {
        setPropertyDataSource(newDataSource);
        return this;
    }

    @Override
    public MTable<T> withValidator(Validator validator) {
        addValidator(validator);
        return this;
    }

    @Override
    public MTable<T> withReadOnlyStatusChangeListener(ReadOnlyStatusChangeListener listener) {
        addReadOnlyStatusChangeListener(listener);
        return this;
    }
    
}
