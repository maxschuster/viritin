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
package org.vaadin.viritin.ui;

import com.vaadin.server.Page;
import com.vaadin.server.Resource;
import com.vaadin.shared.Position;
import com.vaadin.ui.Notification;

/**
 * A {@link Notification} with fluent setters.
 * 
 * @author Max Schuster
 * @see Notification
 */
public class MNotification extends Notification {

    public MNotification(String caption) {
        super(caption);
    }

    public MNotification(String caption, Type type) {
        super(caption, type);
    }

    public MNotification(String caption, String description) {
        super(caption, description);
    }

    public MNotification(String caption, String description, Type type) {
        super(caption, description, type);
    }

    public MNotification(String caption, String description, Type type, boolean htmlContentAllowed) {
        super(caption, description, type, htmlContentAllowed);
    }

    public MNotification withHtmlContentAllowed(boolean htmlContentAllowed) {
        setHtmlContentAllowed(htmlContentAllowed);
        return this;
    }

    public MNotification withStyleName(String styleName) {
        setStyleName(styleName);
        return this;
    }

    public MNotification withDelayMsec(int delayMsec) {
        setDelayMsec(delayMsec);
        return this;
    }

    public MNotification withIcon(Resource icon) {
        setIcon(icon);
        return this;
    }

    public MNotification withPosition(Position position) {
        setPosition(position);
        return this;
    }

    public MNotification withDescription(String description) {
        setDescription(description);
        return this;
    }

    public MNotification withCaption(String caption) {
        setCaption(caption);
        return this;
    }
    
    public MNotification display(Page page) {
        show(page);
        return this;
    }
    
    public MNotification display() {
        return display(Page.getCurrent());
    }
    
    public static MNotification display(String caption) {
        return new MNotification(caption).display();
    }
    
    public static MNotification display(String caption, Type type) {
        return new MNotification(caption, type).display();
    }
    
    public static MNotification display(String caption, String description, Type type) {
        return new MNotification(caption, description, type).display();
    }
    
    public static MNotification humanized(String caption) {
        return display(caption, Type.HUMANIZED_MESSAGE);
    }
    
    public static MNotification humanized(String caption, String description) {
        return display(caption, description, Type.HUMANIZED_MESSAGE);
    }
    
    public static MNotification warning(String caption) {
        return display(caption, Type.WARNING_MESSAGE);
    }
    
    public static MNotification warning(String caption, String description) {
        return display(caption, description, Type.WARNING_MESSAGE);
    }
    
    public static MNotification error(String caption) {
        return display(caption, Type.ERROR_MESSAGE);
    }
    
    public static MNotification error(String caption, String description) {
        return display(caption, description, Type.ERROR_MESSAGE);
    }
    
    public static MNotification tray(String caption) {
        return display(caption, Type.TRAY_NOTIFICATION);
    }
    
    public static MNotification tray(String caption, String description) {
        return display(caption, description, Type.TRAY_NOTIFICATION);
    }
    
}
