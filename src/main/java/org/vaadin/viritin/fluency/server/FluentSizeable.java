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
package org.vaadin.viritin.fluency.server;

import com.vaadin.server.Sizeable;

/**
 * A {@link Sizeable} complemented by fluent setters.
 * 
 * @author Max Schuster
 * @param <C> Fluent component type
 * @see Sizeable
 */
public interface FluentSizeable<C extends FluentSizeable<C>> extends Sizeable {

    /**
     * Sets the height of the component using String presentation.
     *
     * String presentation is similar to what is used in Cascading Style Sheets.
     * Size can be length or percentage of available size.
     *
     * The empty string ("") or null will unset the height and set the units to
     * pixels.
     *
     * See <a
     * href="http://www.w3.org/TR/REC-CSS2/syndata.html#value-def-length">CSS
     * specification</a> for more details.
     *
     * @param height in CSS style string representation
     * @return This component
     * @see #setHeight(java.lang.String)
     */
    public C withHeight(String height);

    /**
     * Sets the height of the object. Negative number implies unspecified size
     * (terminal is free to set the size).
     *
     * @param height the height of the object.
     * @param unit the unit used for the width.
     * @return This component
     * @see #setHeight(float, com.vaadin.server.Sizeable.Unit)
     */
    public C withHeight(float height, Unit unit);

    /**
     * Sets the width of the component using String presentation.
     *
     * String presentation is similar to what is used in Cascading Style Sheets.
     * Size can be length or percentage of available size.
     *
     * The empty string ("") or null will unset the width and set the units to
     * pixels.
     *
     * See <a
     * href="http://www.w3.org/TR/REC-CSS2/syndata.html#value-def-length">CSS
     * specification</a> for more details.
     *
     * @param width in CSS style string representation, null or empty string to
     * reset
     * @return This component
     * @see #setWidth(java.lang.String)
     */
    public C withWidth(String width);

    /**
     * Sets the width of the object. Negative number implies unspecified size
     * (terminal is free to set the size).
     *
     * @param width the width of the object.
     * @param unit the unit used for the width.
     * @return This component
     * @see #setWidth(float, com.vaadin.server.Sizeable.Unit)
     */
    public C withWidth(float width, Unit unit);

    /**
     * Sets the size to 100% x 100%.
     * @return This component
     * @see #setSizeFull()
     */
    public C withSizeFull();

    /**
     * Sets the width to 100%.
     *
     * @return This component
     */
    public C withWidthFull();

    /**
     * Sets the height to 100%.
     *
     * @return This component
     */
    public C withHeightFull();

    /**
     * Clears any size settings.
     * @return
     * @see #setSizeUndefined()
     */
    public C withSizeUndefined();

    /**
     * Clears any defined width
     *
     * @return This component
     * @see #setWidthUndefined()
     */
    public C withWidthUndefined();

    /**
     * Clears any defined height
     *
     * @return This component
     * @see #setHeightUndefined()
     */
    public C withHeightUndefined();

}
