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
import java.util.Locale;
import org.vaadin.dialogs.ConfirmDialog;

/**
 *
 * @author mattitahvonenitmill
 */
public class ConfirmButton extends MButton {

    private String confirmWindowCaption;
    private String confirmationText = "Are you sure?";
    private String okCaption = "OK";
    private String cancelCaption = "Cancel";

    public ConfirmButton() {
    }

    public ConfirmButton(String buttonCaption, String confirmationText,
            ClickListener listener) {
        super(buttonCaption, listener);
        this.confirmationText = confirmationText;
    }

    public ConfirmButton(Resource icon, String confirmationText,
            ClickListener listener) {
        super(icon, listener);
        this.confirmationText = confirmationText;
    }

    @Override
    protected void fireClick(final MouseEventDetails details) {
        ConfirmDialog.show(getUI(), getConfirmWindowCaption(),
                getConfirmationText(), getOkCaption(), getCancelCaption(),
                new Runnable() {

                    @Override
                    public void run() {
                        doFireClickListener(details);
                    }

                });

    }

    protected void doFireClickListener(final MouseEventDetails details) {
        ConfirmButton.super.fireClick(details);
    }

    public String getConfirmWindowCaption() {
        return confirmWindowCaption;
    }

    public ConfirmButton setConfirmWindowCaption(String confirmWindowCaption) {
        this.confirmWindowCaption = confirmWindowCaption;
        return this;
    }

    public String getConfirmationText() {
        return confirmationText;
    }

    public ConfirmButton setConfirmationText(String confirmationText) {
        this.confirmationText = confirmationText;
        return this;
    }

    public String getOkCaption() {
        return okCaption;
    }

    public ConfirmButton setOkCaption(String okCaption) {
        this.okCaption = okCaption;
        return this;
    }

    public String getCancelCaption() {
        return cancelCaption;
    }

    public ConfirmButton setCancelCaption(String cancelCaption) {
        this.cancelCaption = cancelCaption;
        return this;
    }
    
    /* Fluent setters (FluentAbstractComponent): */

    @Override
    public ConfirmButton withHeightUndefined() {
        return (ConfirmButton) super.withHeightUndefined();
    }

    @Override
    public ConfirmButton withWidthUndefined() {
        return (ConfirmButton) super.withWidthUndefined();
    }

    @Override
    public ConfirmButton withSizeUndefined() {
        return (ConfirmButton) super.withSizeUndefined();
    }

    @Override
    public ConfirmButton withSizeFull() {
        return (ConfirmButton) super.withSizeFull();
    }

    @Override
    public ConfirmButton withWidth(float width, Unit unit) {
        return (ConfirmButton) super.withWidth(width, unit);
    }

    @Override
    public ConfirmButton withWidth(String width) {
        return (ConfirmButton) super.withWidth(width);
    }

    @Override
    public ConfirmButton withHeight(float height, Unit unit) {
        return (ConfirmButton) super.withHeight(height, unit);
    }

    @Override
    public ConfirmButton withHeight(String height) {
        return (ConfirmButton) super.withHeight(height);
    }

    @Override
    public ConfirmButton withContextClickListener(ContextClickEvent.ContextClickListener listener) {
        return (ConfirmButton) super.withContextClickListener(listener);
    }

    @Override
    public ConfirmButton withErrorHandler(ErrorHandler errorHandler) {
        return (ConfirmButton) super.withErrorHandler(errorHandler);
    }

    @Override
    public ConfirmButton withDetachListener(DetachListener listener) {
        return (ConfirmButton) super.withDetachListener(listener);
    }

    @Override
    public ConfirmButton withAttachListener(AttachListener listener) {
        return (ConfirmButton) super.withAttachListener(listener);
    }

    @Override
    public ConfirmButton withVisible(boolean visible) {
        return (ConfirmButton) super.withVisible(visible);
    }

    @Override
    public ConfirmButton withStyleName(String style) {
        return (ConfirmButton) super.withStyleName(style);
    }

    @Override
    public ConfirmButton withReadOnly(boolean readOnly) {
        return (ConfirmButton) super.withReadOnly(readOnly);
    }

    @Override
    public ConfirmButton withPrimaryStyleName(String style) {
        return (ConfirmButton) super.withPrimaryStyleName(style);
    }

    @Override
    public ConfirmButton withId(String id) {
        return (ConfirmButton) super.withId(id);
    }

    @Override
    public ConfirmButton withIcon(Resource icon) {
        return (ConfirmButton) super.withIcon(icon);
    }

    @Override
    public ConfirmButton withEnabled(boolean enabled) {
        return (ConfirmButton) super.withEnabled(enabled);
    }

    @Override
    public ConfirmButton withCaption(String caption) {
        return (ConfirmButton) super.withCaption(caption);
    }

    @Override
    public ConfirmButton withShortcutListener(ShortcutListener shortcut) {
        return (ConfirmButton) super.withShortcutListener(shortcut);
    }

    @Override
    public ConfirmButton withResponsive(boolean responsive) {
        return (ConfirmButton) super.withResponsive(responsive);
    }

    @Override
    public ConfirmButton withData(Object data) {
        return (ConfirmButton) super.withData(data);
    }

    @Override
    public ConfirmButton withListener(Listener listener) {
        return (ConfirmButton) super.withListener(listener);
    }

    @Override
    public ConfirmButton withComponentError(ErrorMessage componentError) {
        return (ConfirmButton) super.withComponentError(componentError);
    }

    @Override
    public ConfirmButton withDescription(String description) {
        return (ConfirmButton) super.withDescription(description);
    }

    @Override
    public ConfirmButton withImmediate(boolean immediate) {
        return (ConfirmButton) super.withImmediate(immediate);
    }

    @Override
    public ConfirmButton withLocale(Locale locale) {
        return (ConfirmButton) super.withLocale(locale);
    }

    @Override
    public ConfirmButton withCaptionAsHtml(boolean captionAsHtml) {
        return (ConfirmButton) super.withCaptionAsHtml(captionAsHtml);
    }

    @Override
    public ConfirmButton withStyleName(String style, boolean add) {
        return (ConfirmButton) super.withStyleName(style, add);
    }

}
