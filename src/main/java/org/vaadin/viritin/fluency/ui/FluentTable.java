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

import com.vaadin.data.Container;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.event.Action;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.server.Resource;
import com.vaadin.shared.ui.MultiSelectMode;
import com.vaadin.shared.ui.table.CollapseMenuContent;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.HasChildMeasurementHint;
import com.vaadin.ui.Table;
import com.vaadin.ui.TableFieldFactory;
import java.util.Collection;

/**
 * A {@link Table} complemented by fluent setters.
 * 
 * @author Max Schuster
 * @param <C> Fluent component type
 * @see Table
 */
public interface FluentTable<C extends Table & FluentTable<C>> extends 
        FluentAbstractField<C, Object> {

    public void setVisibleColumns(Object... visibleColumns);

    public void setColumnHeaders(String... columnHeaders);

    public void setColumnIcons(Resource... columnIcons);

    public void setColumnAlignments(Table.Align... columnAlignments);

    public void setColumnWidth(Object propertyId, int width);

    public void setColumnExpandRatio(Object propertyId, float expandRatio);

    public void setPageLength(int pageLength);

    public void setCacheRate(double cacheRate);

    public void setCurrentPageFirstItemId(Object currentPageFirstItemId);

    public void setColumnIcon(Object propertyId, Resource icon);

    public void setColumnHeader(Object propertyId, String header);

    public void setColumnAlignment(Object propertyId, Table.Align alignment);

    public void setColumnCollapsed(Object propertyId, boolean collapsed) throws IllegalStateException;

    public void setColumnCollapsingAllowed(boolean collapsingAllowed);

    public void setColumnCollapsible(Object propertyId, boolean collapsible);

    public void setColumnReorderingAllowed(boolean columnReorderingAllowed);

    public void setCurrentPageFirstItemIndex(int newIndex);

    public void setSelectable(boolean selectable);

    public void setColumnHeaderMode(Table.ColumnHeaderMode columnHeaderMode);

    public void setRowHeaderMode(Table.RowHeaderMode mode);

    public void setContainerDataSource(Container newDataSource);

    public void setContainerDataSource(Container newDataSource, Collection<?> visibleIds);

    public void addActionHandler(Action.Handler actionHandler);

    public boolean addContainerProperty(Object propertyId, Class<?> type, Object defaultValue) throws UnsupportedOperationException;

    public boolean addContainerProperty(Object propertyId, Class<?> type, Object defaultValue, String columnHeader, Resource columnIcon, Table.Align columnAlignment);

    public void addGeneratedColumn(Object id, Table.ColumnGenerator generatedColumn);

    public void setNewItemsAllowed(boolean allowNewOptions) throws UnsupportedOperationException;

    public void setTableFieldFactory(TableFieldFactory fieldFactory);

    public void setEditable(boolean editable);

    public void setSortContainerPropertyId(Object propertyId);

    public void setSortAscending(boolean ascending);

    public void setSortEnabled(boolean sortEnabled);

    public void setCellStyleGenerator(Table.CellStyleGenerator cellStyleGenerator);

    public void addItemClickListener(ItemClickEvent.ItemClickListener listener);

    public void setDragMode(Table.TableDragMode newDragMode);

    public void setDropHandler(DropHandler dropHandler);

    public void setMultiSelectMode(MultiSelectMode mode);

    public void addHeaderClickListener(Table.HeaderClickListener listener);

    public void setColumnFooter(Object propertyId, String footer);

    public void setFooterVisible(boolean visible);

    public void addColumnResizeListener(Table.ColumnResizeListener listener);
    
    public void addColumnReorderListener(Table.ColumnReorderListener listener);
    
    public void addColumnCollapseListener(Table.ColumnCollapseListener listener);

    public void setItemDescriptionGenerator(AbstractSelect.ItemDescriptionGenerator generator);

    public void setRowGenerator(Table.RowGenerator generator);

    public void setConverter(Object propertyId, Converter<String, ?> converter);

    public void setChildMeasurementHint(HasChildMeasurementHint.ChildMeasurementHint hint);

    public void setCollapseMenuContent(CollapseMenuContent content);
    
}
