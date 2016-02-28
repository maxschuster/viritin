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

import com.vaadin.event.ShortcutListener;
import com.vaadin.server.ErrorMessage;
import com.vaadin.ui.AbstractComponent;
import java.util.Locale;
import org.vaadin.viritin.fluency.server.FluentClientConnector;
import org.vaadin.viritin.fluency.event.FluentContextClickNotifier;

/**
 * A {@link AbstractComponent} complemented by fluent setters.
 *
 * @author Max Schuster
 * @param <C> Fluent component type
 * @see AbstractComponent
 */
public interface FluentAbstractComponent<C extends AbstractComponent & FluentAbstractComponent<C>> 
        extends FluentComponent<C>, FluentClientConnector<C>, 
        FluentContextClickNotifier<C> {

    /**
     * Adds or removes a style name. Multiple styles can be specified as a
     * space-separated list of style names.
     *
     * If the {@code add} parameter is true, the style name is added to the
     * component. If the {@code add} parameter is false, the style name is
     * removed from the component.
     * <p>
     * Functionally this is equivalent to using {@link #addStyleName(String)} or
     * {@link #removeStyleName(String)}
     *
     * @param style the style name to be added or removed
     * @param add <code>true</code> to add the given style, <code>false</code>
     * to remove it
     * @return This component
     * @see AbstractComponent#setStyleName(java.lang.String, boolean)
     * @see #addStyleName(String)
     * @see #removeStyleName(String)
     */
    public C withStyleName(String style, boolean add);

    /**
     * Sets whether the caption is rendered as HTML.
     * <p>
     * If set to true, the captions are rendered in the browser as HTML and the
     * developer is responsible for ensuring no harmful HTML is used. If set to
     * false, the caption is rendered in the browser as plain text.
     * <p>
     * The default is false, i.e. to render that caption as plain text.
     *
     * @param captionAsHtml true if the captions are rendered as HTML, false if
     * rendered as plain text
     * @return This component
     * @see AbstractComponent#setCaptionAsHtml(boolean)
     */
    public C withCaptionAsHtml(boolean captionAsHtml);

    /**
     * Sets the locale of this component.
     *
     * <pre>
     * // Component for which the locale is meaningful
     * InlineDateField date = new InlineDateField(&quot;Datum&quot;);
     *
     * // German language specified with ISO 639-1 language
     * // code and ISO 3166-1 alpha-2 country code.
     * date.setLocale(new Locale(&quot;de&quot;, &quot;DE&quot;));
     *
     * date.setResolution(DateField.RESOLUTION_DAY);
     * layout.addComponent(date);
     * </pre>
     *
     * @param locale the locale to become this component's locale.
     * @return This component
     * @see AbstractComponent#setLocale(java.util.Locale)
     */
    public C withLocale(Locale locale);

    /**
     * Sets the component's immediate mode to the specified status.
     *
     * @param immediate the boolean value specifying if the component should be
     * in the immediate mode after the call.
     * @return This component
     * @see AbstractComponent#setImmediate(boolean)
     */
    public C withImmediate(boolean immediate);

    /**
     * Sets the component's description. See {@link #getDescription()} for more
     * information on what the description is. This method will trigger a
     * {@link RepaintRequestEvent}.
     *
     * The description is displayed as HTML in tooltips or directly in certain
     * components so care should be taken to avoid creating the possibility for
     * HTML injection and possibly XSS vulnerabilities.
     *
     * @param description the new description string for the component.
     * @return This component
     * @see AbstractComponent#setDescription(java.lang.String)
     */
    public C withDescription(String description);

    /**
     * Sets the component's error message. The message may contain certain XML
     * tags, for more information see
     *
     * @link Component.ErrorMessage#ErrorMessage(String, int)
     *
     * @param componentError the new <code>ErrorMessage</code> of the component.
     * @return This component @see
     * AbstractComponent#setComponentError(com.vaadin.server.ErrorMessage)
     */
    public C withComponentError(ErrorMessage componentError);

    /**
     * Sets the data object, that can be used for any application specific data.
     * The component does not use or modify this data.
     *
     * @param data the Application specific data.
     * @return This component
     * @see AbstractComponent#setData(java.lang.Object)
     */
    public C withData(Object data);

    /**
     * Toggles responsiveness of this component.
     *
     * @param responsive boolean enables responsiveness, false disables
     * @return This component
     * @see AbstractComponent#setResponsive(boolean)
     */
    public C withResponsive(boolean responsive);

    /**
     * Registers a new shortcut listener for the component.
     *
     * @param shortcut the new Listener to be registered.
     * @return This component
     * @see
     * AbstractComponent#addShortcutListener(com.vaadin.event.ShortcutListener)
     */
    public C withShortcutListener(ShortcutListener shortcut);

}
