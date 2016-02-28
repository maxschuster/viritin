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

import com.vaadin.event.ContextClickEvent;
import com.vaadin.event.ShortcutListener;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.ErrorMessage;
import com.vaadin.server.Resource;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import java.util.Locale;

/**
 * A {@link CustomComponent} complemented by fluent setters.
 * 
 * @author Max Schuster
 * @param <C> Fluent component type
 * @see CustomComponent
 */
@SuppressWarnings("unchecked")
public abstract class FluentCustomComponent<C extends FluentCustomComponent<C>>
        extends CustomComponent implements FluentAbstractComponent<C> {

    public FluentCustomComponent() {
        super();
    }

    public FluentCustomComponent(Component compositionRoot) {
        super(compositionRoot);
    }
    
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
    
}
