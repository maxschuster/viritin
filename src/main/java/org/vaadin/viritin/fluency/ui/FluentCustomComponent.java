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
 * @param <S> Self-referential generic type
 * @see CustomComponent
 */
@SuppressWarnings("unchecked")
public abstract class FluentCustomComponent<S extends FluentCustomComponent<S>>
        extends CustomComponent implements FluentAbstractComponent<S> {

    public FluentCustomComponent() {
        super();
    }

    public FluentCustomComponent(Component compositionRoot) {
        super(compositionRoot);
    }
    
    /* Fluent setters (FluentAbstractComponent): */

    @Override
    public S withStyleName(String style, boolean add) {
        setStyleName(style, add);
        return (S) this;
    }

    @Override
    public S withCaptionAsHtml(boolean captionAsHtml) {
        setCaptionAsHtml(captionAsHtml);
        return (S) this;
    }

    @Override
    public S withLocale(Locale locale) {
        setLocale(locale);
        return (S) this;
    }

    @Override
    public S withImmediate(boolean immediate) {
        setImmediate(immediate);
        return (S) this;
    }

    @Override
    public S withDescription(String description) {
        setDescription(description);
        return (S) this;
    }

    @Override
    public S withComponentError(ErrorMessage componentError) {
        setComponentError(componentError);
        return (S) this;
    }

    @Override
    public S withListener(Listener listener) {
        addListener(listener);
        return (S) this;
    }

    @Override
    public S withData(Object data) {
        setData(data);
        return (S) this;
    }

    @Override
    public S withResponsive(boolean responsive) {
        setResponsive(responsive);
        return (S) this;
    }

    @Override
    public S withShortcutListener(ShortcutListener shortcut) {
        addShortcutListener(shortcut);
        return (S) this;
    }

    @Override
    public S withCaption(String caption) {
        setCaption(caption);
        return (S) this;
    }

    @Override
    public S withEnabled(boolean enabled) {
        setEnabled(enabled);
        return (S) this;
    }

    @Override
    public S withIcon(Resource icon) {
        setIcon(icon);
        return (S) this;
    }

    @Override
    public S withId(String id) {
        setId(id);
        return (S) this;
    }

    @Override
    public S withPrimaryStyleName(String style) {
        setPrimaryStyleName(style);
        return (S) this;
    }

    @Override
    public S withReadOnly(boolean readOnly) {
        setReadOnly(readOnly);
        return (S) this;
    }

    @Override
    public S withStyleName(String... styles) {
        for (String style : styles) {
            addStyleName(style);
        }
        return (S) this;
    }

    @Override
    public S withVisible(boolean visible) {
        setVisible(visible);
        return (S) this;
    }

    @Override
    public S withAttachListener(AttachListener listener) {
        addAttachListener(listener);
        return (S) this;
    }

    @Override
    public S withDetachListener(DetachListener listener) {
        addDetachListener(listener);
        return (S) this;
    }

    @Override
    public S withErrorHandler(ErrorHandler errorHandler) {
        setErrorHandler(errorHandler);
        return (S) this;
    }

    @Override
    public S withContextClickListener(ContextClickEvent.ContextClickListener listener) {
        addContextClickListener(listener);
        return (S) this;
    }

    @Override
    public S withHeight(String height) {
        setHeight(height);
        return (S) this;
    }

    @Override
    public S withHeight(float height, Unit unit) {
        setHeight(height, unit);
        return (S) this;
    }

    @Override
    public S withWidth(String width) {
        setWidth(width);
        return (S) this;
    }

    @Override
    public S withWidth(float width, Unit unit) {
        setWidth(width, unit);
        return (S) this;
    }

    @Override
    public S withSizeFull() {
        setSizeFull();
        return (S) this;
    }

    @Override
    public S withWidthFull() {
        setWidth(100, Unit.PERCENTAGE);
        return (S) this;
    }

    @Override
    public S withHeightFull() {
        setHeight(100, Unit.PERCENTAGE);
        return (S) this;
    }

    @Override
    public S withSizeUndefined() {
        setSizeUndefined();
        return (S) this;
    }

    @Override
    public S withWidthUndefined() {
        setWidthUndefined();
        return (S) this;
    }

    @Override
    public S withHeightUndefined() {
        setHeightUndefined();
        return (S) this;
    }
    
}
