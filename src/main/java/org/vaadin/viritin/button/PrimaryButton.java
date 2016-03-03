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
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.ErrorMessage;
import com.vaadin.server.Resource;
import java.util.Locale;

/**
 * A primary button, commonly used for e.g. saving an entity. Automatically sets 
 * "primary" class name and hooks click shortcut for ENTER.
 */
public class PrimaryButton extends MButton {

    public PrimaryButton() {
        setupPrimaryButton();
    }

    public PrimaryButton(String caption) {
        super(caption);
        setupPrimaryButton();
    }

    public PrimaryButton(String caption, ClickListener listener) {
        super(caption, listener);
        setupPrimaryButton();
    }

    private void setupPrimaryButton() {
        setStyleName("primary default");
        setClickShortcut(ShortcutAction.KeyCode.ENTER, null);
    }
    
    /* Fluent setters (FluentAbstractComponent): */

    @Override
    public PrimaryButton withHeightUndefined() {
        return (PrimaryButton) super.withHeightUndefined();
    }

    @Override
    public PrimaryButton withWidthUndefined() {
        return (PrimaryButton) super.withWidthUndefined();
    }

    @Override
    public PrimaryButton withSizeUndefined() {
        return (PrimaryButton) super.withSizeUndefined();
    }

    @Override
    public PrimaryButton withSizeFull() {
        return (PrimaryButton) super.withSizeFull();
    }

    @Override
    public PrimaryButton withWidth(float width, Unit unit) {
        return (PrimaryButton) super.withWidth(width, unit);
    }

    @Override
    public PrimaryButton withWidth(String width) {
        return (PrimaryButton) super.withWidth(width);
    }

    @Override
    public PrimaryButton withHeight(float height, Unit unit) {
        return (PrimaryButton) super.withHeight(height, unit);
    }

    @Override
    public PrimaryButton withHeight(String height) {
        return (PrimaryButton) super.withHeight(height);
    }

    @Override
    public PrimaryButton withContextClickListener(ContextClickEvent.ContextClickListener listener) {
        return (PrimaryButton) super.withContextClickListener(listener);
    }

    @Override
    public PrimaryButton withErrorHandler(ErrorHandler errorHandler) {
        return (PrimaryButton) super.withErrorHandler(errorHandler);
    }

    @Override
    public PrimaryButton withDetachListener(DetachListener listener) {
        return (PrimaryButton) super.withDetachListener(listener);
    }

    @Override
    public PrimaryButton withAttachListener(AttachListener listener) {
        return (PrimaryButton) super.withAttachListener(listener);
    }

    @Override
    public PrimaryButton withVisible(boolean visible) {
        return (PrimaryButton) super.withVisible(visible);
    }

    @Override
    public PrimaryButton withStyleName(String... styles) {
        return (PrimaryButton) super.withStyleName(styles);
    }

    @Override
    public PrimaryButton withReadOnly(boolean readOnly) {
        return (PrimaryButton) super.withReadOnly(readOnly);
    }

    @Override
    public PrimaryButton withPrimaryStyleName(String style) {
        return (PrimaryButton) super.withPrimaryStyleName(style);
    }

    @Override
    public PrimaryButton withId(String id) {
        return (PrimaryButton) super.withId(id);
    }

    @Override
    public PrimaryButton withIcon(Resource icon) {
        return (PrimaryButton) super.withIcon(icon);
    }

    @Override
    public PrimaryButton withEnabled(boolean enabled) {
        return (PrimaryButton) super.withEnabled(enabled);
    }

    @Override
    public PrimaryButton withCaption(String caption) {
        return (PrimaryButton) super.withCaption(caption);
    }

    @Override
    public PrimaryButton withShortcutListener(ShortcutListener shortcut) {
        return (PrimaryButton) super.withShortcutListener(shortcut);
    }

    @Override
    public PrimaryButton withResponsive(boolean responsive) {
        return (PrimaryButton) super.withResponsive(responsive);
    }

    @Override
    public PrimaryButton withData(Object data) {
        return (PrimaryButton) super.withData(data);
    }

    @Override
    public PrimaryButton withListener(Listener listener) {
        return (PrimaryButton) super.withListener(listener);
    }

    @Override
    public PrimaryButton withComponentError(ErrorMessage componentError) {
        return (PrimaryButton) super.withComponentError(componentError);
    }

    @Override
    public PrimaryButton withDescription(String description) {
        return (PrimaryButton) super.withDescription(description);
    }

    @Override
    public PrimaryButton withImmediate(boolean immediate) {
        return (PrimaryButton) super.withImmediate(immediate);
    }

    @Override
    public PrimaryButton withLocale(Locale locale) {
        return (PrimaryButton) super.withLocale(locale);
    }

    @Override
    public PrimaryButton withCaptionAsHtml(boolean captionAsHtml) {
        return (PrimaryButton) super.withCaptionAsHtml(captionAsHtml);
    }

    @Override
    public PrimaryButton withStyleName(String style, boolean add) {
        return (PrimaryButton) super.withStyleName(style, add);
    }
    
}
