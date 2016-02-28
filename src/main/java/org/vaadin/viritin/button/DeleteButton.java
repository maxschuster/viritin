/*
 * Copyright 2015
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
import java.util.Locale;

/**
 * A delete button, commonly used for deleting an entity. Automatically sets
 * "danger" class name and open a confirmation dialog.
 */
public class DeleteButton extends ConfirmButton  {

    public DeleteButton() {
        setupDeleteButton();
    }

    public DeleteButton(String caption) {
        setCaption(caption);
        setupDeleteButton();
    }

    public DeleteButton(String caption, String confirmationText, ClickListener listener) {
        super(caption, confirmationText, listener);
        setupDeleteButton();
    }

    private void setupDeleteButton() {
        setStyleName("danger default");
    }
    
    /* Fluent setters (FluentAbstractComponent): */

    @Override
    public DeleteButton withHeightUndefined() {
        return (DeleteButton) super.withHeightUndefined();
    }

    @Override
    public DeleteButton withWidthUndefined() {
        return (DeleteButton) super.withWidthUndefined();
    }

    @Override
    public DeleteButton withSizeUndefined() {
        return (DeleteButton) super.withSizeUndefined();
    }

    @Override
    public DeleteButton withSizeFull() {
        return (DeleteButton) super.withSizeFull();
    }

    @Override
    public DeleteButton withWidth(float width, Unit unit) {
        return (DeleteButton) super.withWidth(width, unit);
    }

    @Override
    public DeleteButton withWidth(String width) {
        return (DeleteButton) super.withWidth(width);
    }

    @Override
    public DeleteButton withHeight(float height, Unit unit) {
        return (DeleteButton) super.withHeight(height, unit);
    }

    @Override
    public DeleteButton withHeight(String height) {
        return (DeleteButton) super.withHeight(height);
    }

    @Override
    public DeleteButton withContextClickListener(ContextClickEvent.ContextClickListener listener) {
        return (DeleteButton) super.withContextClickListener(listener);
    }

    @Override
    public DeleteButton withErrorHandler(ErrorHandler errorHandler) {
        return (DeleteButton) super.withErrorHandler(errorHandler);
    }

    @Override
    public DeleteButton withDetachListener(DetachListener listener) {
        return (DeleteButton) super.withDetachListener(listener);
    }

    @Override
    public DeleteButton withAttachListener(AttachListener listener) {
        return (DeleteButton) super.withAttachListener(listener);
    }

    @Override
    public DeleteButton withVisible(boolean visible) {
        return (DeleteButton) super.withVisible(visible);
    }

    @Override
    public DeleteButton withStyleName(String style) {
        return (DeleteButton) super.withStyleName(style);
    }

    @Override
    public DeleteButton withReadOnly(boolean readOnly) {
        return (DeleteButton) super.withReadOnly(readOnly);
    }

    @Override
    public DeleteButton withPrimaryStyleName(String style) {
        return (DeleteButton) super.withPrimaryStyleName(style);
    }

    @Override
    public DeleteButton withId(String id) {
        return (DeleteButton) super.withId(id);
    }

    @Override
    public DeleteButton withIcon(Resource icon) {
        return (DeleteButton) super.withIcon(icon);
    }

    @Override
    public DeleteButton withEnabled(boolean enabled) {
        return (DeleteButton) super.withEnabled(enabled);
    }

    @Override
    public DeleteButton withCaption(String caption) {
        return (DeleteButton) super.withCaption(caption);
    }

    @Override
    public DeleteButton withShortcutListener(ShortcutListener shortcut) {
        return (DeleteButton) super.withShortcutListener(shortcut);
    }

    @Override
    public DeleteButton withResponsive(boolean responsive) {
        return (DeleteButton) super.withResponsive(responsive);
    }

    @Override
    public DeleteButton withData(Object data) {
        return (DeleteButton) super.withData(data);
    }

    @Override
    public DeleteButton withListener(Listener listener) {
        return (DeleteButton) super.withListener(listener);
    }

    @Override
    public DeleteButton withComponentError(ErrorMessage componentError) {
        return (DeleteButton) super.withComponentError(componentError);
    }

    @Override
    public DeleteButton withDescription(String description) {
        return (DeleteButton) super.withDescription(description);
    }

    @Override
    public DeleteButton withImmediate(boolean immediate) {
        return (DeleteButton) super.withImmediate(immediate);
    }

    @Override
    public DeleteButton withLocale(Locale locale) {
        return (DeleteButton) super.withLocale(locale);
    }

    @Override
    public DeleteButton withCaptionAsHtml(boolean captionAsHtml) {
        return (DeleteButton) super.withCaptionAsHtml(captionAsHtml);
    }

    @Override
    public DeleteButton withStyleName(String style, boolean add) {
        return (DeleteButton) super.withStyleName(style, add);
    }

}
