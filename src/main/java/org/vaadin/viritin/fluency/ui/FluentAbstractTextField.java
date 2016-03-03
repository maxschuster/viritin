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

import com.vaadin.ui.AbstractTextField;
import org.vaadin.viritin.fluency.event.FluentFieldEvents;

/**
 * A {@link AbstractTextField} complemented by fluent setters.
 *
 * @author Max Schuster
 * @param <S> Self-referential generic type
 * @see AbstractTextField
 */
public interface FluentAbstractTextField<S extends AbstractTextField & FluentAbstractTextField<S>>
        extends FluentAbstractField<S, String>,
        FluentFieldEvents.FluentBlurNotifier<S>,
        FluentFieldEvents.FluentFocusNotifier<S>,
        FluentFieldEvents.FluentTextChangeNotifier<S> {

    /**
     * Sets the null-string representation.
     *
     * <p>
     * The null-valued strings are represented on the user interface by
     * replacing the null value with this string. If the null representation is
     * set null (not 'null' string), painting null value throws exception.
     * </p>
     *
     * <p>
     * The default value is string 'null'
     * </p>
     *
     * @param nullRepresentation Textual representation for null strings.
     * @return this (for method chaining)
     * @see TextField#setNullSettingAllowed(boolean)
     * @see AbstractTextField#setNullRepresentation(java.lang.String)
     */
    public S withNullRepresentation(String nullRepresentation);

    /**
     * Sets the null conversion mode.
     *
     * <p>
     * If this property is true, writing null-representation string to text
     * field always sets the field value to real null. If this property is
     * false, null setting is not made, but the null values are maintained.
     * Maintenance of null-values is made by only converting the textfield
     * contents to real null, if the text field matches the null-string
     * representation and the current value of the field is null.
     * </p>
     *
     * <p>
     * By default this setting is false.
     * </p>
     *
     * @param nullSettingAllowed Should the null-string representation always be
     * converted to null-values.
     * @return this (for method chaining)
     * @see AbstractTextField#setNullSettingAllowed(boolean)
     * @see TextField#getNullRepresentation()
     */
    public S withNullSettingAllowed(boolean nullSettingAllowed);

    /**
     * Sets the maximum number of characters in the field. Value -1 is
     * considered unlimited. Terminal may however have some technical limits.
     *
     * @param maxLength the maxLength to set
     * @return this (for method chaining)
     * @see AbstractTextField#setMaxLength(int)
     */
    public S withMaxLength(int maxLength);

    /**
     * Sets the number of columns in the editor. If the number of columns is set
     * 0, the actual number of displayed columns is determined implicitly by the
     * adapter.
     *
     * @param columns the number of columns to set.
     * @return this (for method chaining)
     * @see AbstractTextField#setColumns(int)
     */
    public S withColumns(int columns);

    /**
     * Sets the input prompt - a textual prompt that is displayed when the field
     * would otherwise be empty, to prompt the user for input.
     *
     * @param inputPrompt
     * @return this (for method chaining)
     * @see AbstractTextField#setInputPrompt(java.lang.String)
     */
    public S withInputPrompt(String inputPrompt);

    /**
     * Sets the mode how the TextField triggers {@link TextChangeEvent}s.
     *
     * @param inputEventMode the new mode
     * @return this (for method chaining)
     * @see
     * AbstractTextField#setTextChangeEventMode(com.vaadin.ui.AbstractTextField.TextChangeEventMode)
     * @see TextChangeEventMode
     */
    public S withTextChangeEventMode(
            AbstractTextField.TextChangeEventMode inputEventMode);

    /**
     * The text change timeout modifies how often text change events are
     * communicated to the application when {@link #getTextChangeEventMode()} is
     * {@link TextChangeEventMode#LAZY} or {@link TextChangeEventMode#TIMEOUT}.
     *
     * @param timeout the timeout in milliseconds
     * @return this (for method chaining)
     * @see AbstractTextField#setTextChangeTimeout(int)
     * @see #getTextChangeEventMode()
     */
    public S withTextChangeTimeout(int timeout);

    /**
     * Sets the range of text to be selected.
     *
     * As a side effect the field will become focused.
     *
     * @param pos the position of the first character to be selected
     * @param length the number of characters to be selected
     * @return this (for method chaining)
     * @see AbstractTextField#setSelectionRange(int, int)
     */
    public S withSelectionRange(int pos, int length);

    /**
     * Sets the cursor position in the field. As a side effect the field will
     * become focused.
     *
     * @param pos the position for the cursor
     * @return this (for method chaining)
     * @see AbstractTextField#setCursorPosition(int)
     */
    public S withCursorPosition(int pos);

}
