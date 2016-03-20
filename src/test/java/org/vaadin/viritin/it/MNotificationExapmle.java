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
package org.vaadin.viritin.it;

import com.vaadin.annotations.Theme;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import org.vaadin.addonhelpers.AbstractTest;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.label.MLabel;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;
import org.vaadin.viritin.ui.MNotification;

/**
 *
 * @author Max Schuster
 */
@Theme("valo")
public class MNotificationExapmle extends AbstractTest {

    @Override
    public Component getTestComponent() {
        MVerticalLayout layout = new MVerticalLayout(
                new MLabel("MNotification Examples").withStyleName("h1"),
                new MHorizontalLayout(
                        new MButton("Humanized", new Button.ClickListener() {
                            @Override
                            public void buttonClick(Button.ClickEvent event) {
                                MNotification.humanized("Humanized", "This is a humanized notification!")
                                        .withIcon(FontAwesome.COMMENT);
                            }
                        }).withStyleName("primary").withIcon(FontAwesome.COMMENT),
                        new MButton("Error", new Button.ClickListener() {
                            @Override
                            public void buttonClick(Button.ClickEvent event) {
                                MNotification.error("Error", "This is an error notification!")
                                        .withIcon(FontAwesome.TIMES);
                            }
                        }).withStyleName("danger").withIcon(FontAwesome.TIMES),
                        new MButton("Warning", new Button.ClickListener() {
                            @Override
                            public void buttonClick(Button.ClickEvent event) {
                                MNotification.warning("Warning", "This is a warning notification!")
                                        .withIcon(FontAwesome.EXCLAMATION_TRIANGLE);
                            }
                        }).withIcon(FontAwesome.EXCLAMATION_TRIANGLE),
                        new MButton("Tray", new Button.ClickListener() {
                            @Override
                            public void buttonClick(Button.ClickEvent event) {
                                MNotification.tray("Tray", "This is a tray notification!")
                                        .withIcon(FontAwesome.DOWNLOAD);
                            }
                        }).withStyleName("friendly").withIcon(FontAwesome.DOWNLOAD),
                        new MButton("Assistive", new Button.ClickListener() {
                            @Override
                            public void buttonClick(Button.ClickEvent event) {
                                MNotification.assistive("Assistive", "This is an assistive notification!");
                            }
                        }).withStyleName("quiet").withIcon(FontAwesome.WHEELCHAIR)
                )
        ).withFullWidth();

        return layout;
    }

}
