/*
 * Copyright 2014 Matti Tahvonen.
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
package org.vaadin.viritin.button;

import com.vaadin.event.ContextClickEvent;
import com.vaadin.event.ShortcutListener;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.ErrorMessage;
import com.vaadin.server.Resource;
import com.vaadin.shared.MouseEventDetails;
import com.vaadin.ui.Button;
import java.util.LinkedHashSet;
import java.util.Locale;
import org.vaadin.viritin.fluency.ui.FluentAbstractComponent;

/**
 * An extension to basic Vaadin button that adds some handy constructors and
 * fluent API.
 */
public class MButton extends Button implements FluentAbstractComponent {

    public MButton() {
    }

    public MButton(Resource icon) {
        setIcon(icon);
    }

    public MButton(Resource icon, ClickListener listener) {
        super(null, listener);
        setIcon(icon);
    }

    public MButton(String caption) {
        super(caption);
    }

    public MButton(String caption, ClickListener listener) {
        super(caption, listener);
    }

    public MButton withListener(ClickListener listener) {
        addClickListener(listener);
        return this;
    }

    /**
     * A parameterless version of ClickListener to make it easier to use method
     * references.
     */
    public interface MClickListener {

        void onClick();
    }

    @Override
    protected void fireClick(MouseEventDetails details) {
        super.fireClick(details);
        if (mClickListeners != null) {
            final MClickListener[] array = mClickListeners.toArray(
                    new MClickListener[mClickListeners.size()]);
            for (MClickListener l : array) {
                l.onClick();
            }
        }
    }

    private LinkedHashSet<MClickListener> mClickListeners;

    public MButton addClickListener(MClickListener listener) {
        if (mClickListeners == null) {
            mClickListeners = new LinkedHashSet<MClickListener>();
        }
        mClickListeners.add(listener);
        return this;
    }

    public MButton removeClickListener(MClickListener listener) {
        if (mClickListeners != null) {
            mClickListeners.remove(listener);
        }
        return this;
    }

    /* Fluent setters (FluentAbstractComponent): */

    @Override
    public MButton withStyleName(String style, boolean add) {
        setStyleName(style, add);
        return this;
    }

    @Override
    public MButton withCaptionAsHtml(boolean captionAsHtml) {
        setCaptionAsHtml(captionAsHtml);
        return this;
    }

    @Override
    public MButton withLocale(Locale locale) {
        setLocale(locale);
        return this;
    }

    @Override
    public MButton withImmediate(boolean immediate) {
        setImmediate(immediate);
        return this;
    }

    @Override
    public MButton withDescription(String description) {
        setDescription(description);
        return this;
    }

    @Override
    public MButton withComponentError(ErrorMessage componentError) {
        setComponentError(componentError);
        return this;
    }

    @Override
    public MButton withListener(Listener listener) {
        addListener(listener);
        return this;
    }

    @Override
    public MButton withData(Object data) {
        setData(data);
        return this;
    }

    @Override
    public MButton withResponsive(boolean responsive) {
        setResponsive(responsive);
        return this;
    }

    @Override
    public MButton withShortcutListener(ShortcutListener shortcut) {
        addShortcutListener(shortcut);
        return this;
    }

    @Override
    public MButton withCaption(String caption) {
        setCaption(caption);
        return this;
    }

    @Override
    public MButton withEnabled(boolean enabled) {
        setEnabled(enabled);
        return this;
    }

    @Override
    public MButton withIcon(Resource icon) {
        setIcon(icon);
        return this;
    }

    @Override
    public MButton withId(String id) {
        setId(id);
        return this;
    }

    @Override
    public MButton withPrimaryStyleName(String style) {
        setPrimaryStyleName(style);
        return this;
    }

    @Override
    public MButton withReadOnly(boolean readOnly) {
        setReadOnly(readOnly);
        return this;
    }

    @Override
    public MButton withStyleName(String... styles) {
        for (String style : styles) {
            addStyleName(style);
        }
        return this;
    }

    @Override
    public MButton withVisible(boolean visible) {
        setVisible(visible);
        return this;
    }

    @Override
    public MButton withAttachListener(AttachListener listener) {
        addAttachListener(listener);
        return this;
    }

    @Override
    public MButton withDetachListener(DetachListener listener) {
        addDetachListener(listener);
        return this;
    }

    @Override
    public MButton withErrorHandler(ErrorHandler errorHandler) {
        setErrorHandler(errorHandler);
        return this;
    }

    @Override
    public MButton withContextClickListener(ContextClickEvent.ContextClickListener listener) {
        addContextClickListener(listener);
        return this;
    }

    @Override
    public MButton withHeight(String height) {
        setHeight(height);
        return this;
    }

    @Override
    public MButton withHeight(float height, Unit unit) {
        setHeight(height, unit);
        return this;
    }

    @Override
    public MButton withWidth(String width) {
        setWidth(width);
        return this;
    }

    @Override
    public MButton withWidth(float width, Unit unit) {
        setWidth(width, unit);
        return this;
    }

    @Override
    public MButton withSizeFull() {
        setSizeFull();
        return this;
    }

    @Override
    public MButton withSizeUndefined() {
        setSizeUndefined();
        return this;
    }
    
    @Override
    public MButton withWidthFull() {
        setWidth(100, Unit.PERCENTAGE);
        return this;
    }

    @Override
    public MButton withHeightFull() {
        setHeight(100, Unit.PERCENTAGE);
        return this;
    }

    @Override
    public MButton withWidthUndefined() {
        setWidthUndefined();
        return this;
    }

    @Override
    public MButton withHeightUndefined() {
        setHeightUndefined();
        return this;
    }
    
}
